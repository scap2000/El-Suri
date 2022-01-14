/**
 * RemoteFileTreePanel.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.digitall.common.filemanager.ExpandTreePathThread;
import org.digitall.common.filemanager.LocalFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.sql.LibSQL;

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
public class RemoteFileTreePanel extends BasicContainerPanel implements DropTargetListener {

    /** The model which holds the nodes */
    protected TreeModel model;

    /** The tree GUI */
    protected JTree tree;
    private GridLayout gridLayout = new GridLayout(1, 2);
    private int currentDirectory = -1;
    private int destinationDirID = -1;
    private RemoteFileList jlFileList = new RemoteFileList();
    private Object dragSource;
    private FileManTransfersPanel fileMan;
    private TreePath dropPath = null;
    private TreePath prevDropPath = null;
    private ExpandTreePathThread expandThread;

    /**
     * Constructs the jpCenter which holds a JTree with the file structure.
     */
    public RemoteFileTreePanel(FileManTransfersPanel _fileMan) {
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
	MutableTreeNode root = new DefaultMutableTreeNode("Remote Folders");
	//get all nodes for top file systems or disks
	//On Linux/Unix this will only be '/'
	//while on Window there will typically be more: 'A:', 'C:' etc.
	DefaultMutableTreeNode node = new DefaultMutableTreeNode("/ - (0)");
	root.insert(node, 0);
	//create a tree model with the Computer node as root
	model = new DefaultTreeModel(root);
	//create a JTree GUI component with the model to display
	tree = new JTree(model);
	//add a selection listener, to listen for selected nodes
	tree.addTreeSelectionListener(new MyTreeSelectionListener());
	//create scrollbars
	BasicScrollPane scroll = new BasicScrollPane(tree);
	//and finally add to the panel
	add(scroll);
	BasicScrollPane scrollFileList = new BasicScrollPane(jlFileList);
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
    }

    /**
     * Adds the sub directories, if any, of the given path to the given node.
     *
     * @param node tree node to add sub directory nodes to
     * @param path file system path to search for sub directories
     */
    protected void addChildren(MutableTreeNode node) {
	ResultSet subDirList = org.digitall.lib.sql.LibSQL.exQuery("SELECT name, iddirectory FROM admin.virtual_filesystem_dirs where idparent = 0" + currentDirectory);
	Vector subDirs = new Vector();
	try {
	    while (subDirList.next()) {
		subDirs.add("" + subDirList.getString("name") + " - (" + subDirList.getString("iddirectory") + ")");
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}
	//add sub nodes to given nodes
	if (subDirs.size() > 0) {
	    Collections.sort(subDirs);
	    for (int i = 0; i < subDirs.size(); i++) {
		String f = subDirs.elementAt(i).toString();
		MutableTreeNode child = new DefaultMutableTreeNode(f);
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
	//TreeNode node = getNodeForEvent(dropTargetDragEvent);
	if (dragSource == null) {
	    dropTargetDragEvent.rejectDrag();
	} else {
	    dropPath = getNodeForEvent(dropTargetDragEvent);
	    if (dropPath != prevDropPath) {
		expandThread = new ExpandTreePathThread(tree);
		expandThread.setPath(dropPath);
		expandThread.start();
		//tree.setSelectionPath(dropPath);
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
	String node = parentpath.getLastPathComponent().toString();
	try {
	    Transferable tr = dropTargetDropEvent.getTransferable();
	    DataFlavor[] flavors = tr.getTransferDataFlavors();
	    for (int i = 0; i < flavors.length; i++) {
		if (tr.isDataFlavorSupported(flavors[i])) {
		    if (LocalFileList.class.isInstance(dragSource)) {
			if (parentpath.getPath().length > 1) {
			    setDestinationDirID(Integer.parseInt("0" + node.split("-")[1].replace('(', ' ').replace(')', ' ').trim()));
			    dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
			    LocalFileList fileList = (LocalFileList)dragSource;
			    LocalFile[] selectedFiles = fileList.getSelectedFiles();
			    for (int j = 0; j < selectedFiles.length; j++) {
				//DO SOMETHING WITH FILES!!!
				selectedFiles[i].setDestinationDir(destinationDirID);
				boolean canContinue = true;
				String params = "'" + selectedFiles[j].getName() + "', " + selectedFiles[j].getDestinationDir();
				if (LibSQL.getBoolean("admin.fileexists", params)) {
				    if (!Advisor.question("Archivo existente", "El archivo " + selectedFiles[j].getName() + " ya existe,\n¿desea sobreescribirlo?")) {
					canContinue = false;
				    }
				}
				if (canContinue) {
				    System.out.println("Uploading file: " + selectedFiles[j].getDestinationDir() + "/" + selectedFiles[j].getName());
				    //Upload to remote host
				    fileMan.putFile(selectedFiles[j]);
				}
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

    public void setDragSource(LocalFileList _localFileList) {
	dragSource = _localFileList;
    }

    private void setDestinationDirID(int _destinationDirID) {
	destinationDirID = _destinationDirID;
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
	    if (nodes.length > 1) {
		setCurrentDirectory(Integer.parseInt("0" + nodes[nodes.length - 1].toString().split("-")[1].replace('(', ' ').replace(')', ' ').trim()));
	    }
	    //loop through all the tree nodes getting the directory name
	    //we start from node one, since node 0, the top node, is the Computer node,
	    //which will have no meaning in the file system path
	    //add the sub directory nodes to the selected one
	    addChildren(curNode);
	    //refresh file list
	    //refreshFileList(currentDirectory);
	    //refresh the GUI
	    repaint();
	}

	private void setCurrentDirectory(int _currentDirectory) {
	    currentDirectory = _currentDirectory;
	    jlFileList.setCurrentDirectory(currentDirectory);
	}

    }

}
