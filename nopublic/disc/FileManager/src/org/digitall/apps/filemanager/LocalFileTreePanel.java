/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * LocalFileTreePanel.java
 *
 * */
package org.digitall.apps.filemanager;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.io.File;

import java.util.Arrays;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.digitall.common.filemanager.ExpandTreePathThread;
import org.digitall.common.filemanager.RemoteFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicScrollPane;

/*

    Copyright (C) 2005  Havard Rast Blok
    http://hblok.sourceforge.net

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
//

/**
 * Example which displays the directory structure of the system
 * in a JTree coponent. The nodes are expandable, and will list
 * the sub directories of the selected node.
 *
 * @author Havard Rast Blok
 *
 */
public class LocalFileTreePanel extends BasicContainerPanel implements DropTargetListener {

    /** The model which holds the nodes */
    protected TreeModel model;

    /** The tree GUI */
    protected JTree tree;
    private GridLayout gridLayout = new GridLayout(1, 2);
    private String currentDirectory = "";
    private LocalFileList jlFileList = new LocalFileList();
    private Object dragSource;
    private FileManTransfersPanel fileMan;
    private TreePath dropPath = null;
    private TreePath prevDropPath = null;
    private BasicScrollPane scrollFileList;
    private BasicScrollPane scrollTree;
    private ExpandTreePathThread expandThread;

    /**
     * Constructs the jpCenter which holds a JTree with the file structure.
     */
    public LocalFileTreePanel(FileManTransfersPanel _fileMan) {
	try {
	    fileMan = _fileMan;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setLayout(gridLayout);
	//we only need one component
	//create the top node
	this.setSize(new Dimension(542, 403));
	MutableTreeNode root = new DefaultMutableTreeNode("Local Folders");
	//get all nodes for top file systems or disks
	//On Linux/Unix this will only be '/'
	//while on Window there will typically be more: 'A:', 'C:' etc.
	File roots[] = File.listRoots();
	//loop through all these nodes and add them to the root Computer node
	for (int i = 0; i < roots.length; i++) {
	    File f = roots[i];
	    DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getAbsoluteFile().toString());
	    root.insert(node, i);
	}
	//create a tree model with the Computer node as root
	model = new DefaultTreeModel(root);
	//create a JTree GUI component with the model to display
	tree = new JTree(model);
	//add a selection listener, to listen for selected nodes
	tree.addTreeSelectionListener(new MyTreeSelectionListener());
	//create scrollbars
	scrollTree = new BasicScrollPane(tree);
	//and finally add to the panel
	add(scrollTree);
	scrollFileList = new BasicScrollPane(jlFileList);
	add(scrollFileList);
	DropTarget target = new DropTarget(tree, this);
	jlFileList.setDragEnabled(true);
	jlFileList.addMouseMotionListener(new MouseMotionListener() {

		    public void mouseMoved(MouseEvent e) {
		    }

		    public void mouseDragged(MouseEvent e) {
			fileMan.setDragSource(jlFileList);
		    }

		});
	tree.setAutoscrolls(true);
    }

    /**
     * Adds the sub directories, if any, of the given path to the given node.
     *
     * @param node tree node to add sub directory nodes to
     * @param path file system path to search for sub directories
     */
    protected void addChildren(MutableTreeNode node, String path) {
	//create filter for directories only
	DirectoryFileFilter filter = new DirectoryFileFilter();
	//get directories
	File dir = new File(path);
	File subDirs[] = dir.listFiles(filter);
	//add sub nodes to given nodes
	if (subDirs != null) {
	    Arrays.sort(subDirs);
	    for (int i = 0; i < subDirs.length; i++) {
		File f = subDirs[i];
		MutableTreeNode child = new DefaultMutableTreeNode(f.getName());
		node.insert(child, i);
	    }
	}
    }

    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
    }

    private TreePath getNodeForEvent(DropTargetDragEvent dtde) {
	Point p = dtde.getLocation();
	DropTargetContext dtc = dtde.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	return tree.getClosestPathForLocation(p.x, p.y);
    }

    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
	if (dragSource == null) {
	    dropTargetDragEvent.rejectDrag();
	} else {
	    dropPath = getNodeForEvent(dropTargetDragEvent);
	    if (dropPath != prevDropPath) {
		expandThread = new ExpandTreePathThread(tree);
		expandThread.setPath(dropPath);
		expandThread.start();
		tree.setSelectionPath(dropPath);
		prevDropPath = dropPath;
	    }
	    dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
	}
    }

    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
    }

    public void drop(DropTargetDropEvent dropTargetDropEvent) {
	Point pt = dropTargetDropEvent.getLocation();
	DropTargetContext dtc = dropTargetDropEvent.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	DefaultMutableTreeNode parent = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
	//String node = parentpath.getLastPathComponent().toString();
	try {
	    Transferable tr = dropTargetDropEvent.getTransferable();
	    DataFlavor[] flavors = tr.getTransferDataFlavors();
	    for (int i = 0; i < flavors.length; i++) {
		if (tr.isDataFlavorSupported(flavors[i])) {
		    if (RemoteFileList.class.isInstance(dragSource)) {
			dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
			RemoteFileList fileList = (RemoteFileList)dragSource;
			RemoteFile[] selectedFiles = fileList.getSelectedFiles();
			for (int j = 0; j < selectedFiles.length; j++) {
			    //DO SOMETHING WITH FILES!!!
			    Object[] nodes = dropPath.getPath();
			    String dir = "";
			    for (int k = 1; k < nodes.length; k++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)nodes[k];
				//the user object will hold the directory name
				dir += (String)node.getUserObject();
				//add the platform independent directory separator character
				dir += File.separator;
			    }
			    dir = dir.replaceAll("//", "/");
			    String destination = dir;
			    selectedFiles[j].setDestinationDir(destination);
			    boolean canContinue = true;
			    File outputFile = new File(selectedFiles[j].getDestinationDir() + selectedFiles[j].getName());
			    if (outputFile.exists()) {
				if (!Advisor.question("Archivo existente", "El archivo " + selectedFiles[j].getName() + " ya existe,\n¿desea sobreescribirlo?")) {
				    canContinue = false;
				}
			    }
			    if (canContinue) {
				System.out.println("Downloading file: " + selectedFiles[j].getDestinationDir() + selectedFiles[j].getName());
				//Download from remote host
				fileMan.getFile(selectedFiles[j]);
			    }
			}
			dropTargetDropEvent.dropComplete(true);
			return;
		    }
		}
	    }
	    dropTargetDropEvent.rejectDrop();
	} catch (Exception e) {
	    e.printStackTrace();
	    dropTargetDropEvent.rejectDrop();
	}
    }

    public void dragExit(DropTargetEvent dropTargetEvent) {
	dropPath = null;
	if (expandThread != null) {
	    expandThread.setPath(dropPath);
	}
    }

    public void setDragSource(RemoteFileList _remoteFileList) {
	dragSource = _remoteFileList;
    }

    /**
     * Inner class which listens for selection events on the tree.
     * When one is received, it will get the path of the selected
     * node and add sub directory nodes, if any, to it.
     */
    class MyTreeSelectionListener implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent e) {
	    //get the selected tree path
	    TreePath path = e.getNewLeadSelectionPath();
	    //check that a path is in fact selected
	    if (path == null) {
		return;
	    }
	    //get the selected node
	    DefaultMutableTreeNode curNode = (DefaultMutableTreeNode)path.getLastPathComponent();
	    //get all the nodes in the selected tree path
	    Object nodes[] = path.getPath();
	    String dir = "";
	    //loop through all the tree nodes getting the directory name
	    //we start from node one, since node 0, the top node, is the Computer node,
	    //which will have no meaning in the file system path
	    for (int i = 1; i < nodes.length; i++) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)nodes[i];
		//the user object will hold the directory name
		dir += (String)node.getUserObject();
		//add the platform independent directory separator character
		dir += File.separator;
	    }
	    //add the sub directory nodes to the selected one
	    addChildren(curNode, dir);
	    setCurrentDirectory(dir.replaceAll("//", "/"));
	    //refresh file list
	    //refresh the GUI
	    repaint();
	}

	private void setCurrentDirectory(String _currentDirectory) {
	    currentDirectory = _currentDirectory;
	    jlFileList.setCurrentDirectory(currentDirectory);
	}

    }

}
