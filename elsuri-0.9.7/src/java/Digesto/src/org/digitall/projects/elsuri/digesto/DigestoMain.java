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
 * DigestoMain.java
 *
 * */

package org.digitall.projects.elsuri.digesto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;

import oracle.jdeveloper.layout.PaneLayout;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.image.AlbumUtils;
import org.digitall.lib.sql.LibSQL;

public class DigestoMain extends BasicPrimitivePanel {

    private CBInput cbTipo = new CBInput(0,"Tipo",false);
    private TFInput tfNumero = new TFInput(DataTypes.INTEGER, "Nro.", false);
    private TFInput tfAnio = new TFInput(DataTypes.INTEGER, "Año", false);
    private TFInput tfTexto = new TFInput(DataTypes.STRING, "Palabras", false);

    private BasicDialog parentContainer;
    private FindButton btnFind = new FindButton();
    private BasicButton btnAyuda = new BasicButton("Ayuda (F1)");
    private BasicButton btnImprimir = new BasicButton("Imprimir Documento (F5)");
    private BasicButton btnBrowser = new BasicButton("Visor Externo (F9)");

    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpDocumento = new BasicPanel();
    private BasicList jlSearchResults = new BasicList();
    private JArea jtDocumento = new JArea();

    private Documento selectedDocument = new Documento();
    private BasicPanel jpNorth = new BasicPanel();

    private BasicScrollPane jsSearchResults = new BasicScrollPane();
    private JTextField jtTitulo = new JTextField();
    private Vector allDocuments = new Vector();
    private Vector filteredDocuments = new Vector();
    private boolean searching = false;
    private boolean collecting = false;
    private BasicPanel jpNorthWest = new BasicPanel();
    private BasicPanel jpNorthEast = new BasicPanel();

    public DigestoMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(new BorderLayout(20,0));
	jpCenter.setBorder(BorderPanel.getBorderPanel("Documentos del Digesto Legislativo"));
	jpCenter.setPreferredSize(new Dimension(1, 300));
	jpCenter.setMinimumSize(new Dimension(1, 300));
	this.setSize(new Dimension(774, 374));
	tfNumero.setBounds(new Rectangle(50, 5, 40, 35));
	tfAnio.setBounds(new Rectangle(5, 5, 40, 35));
	tfTexto.setBounds(new Rectangle(95, 5, 180, 35));
	cbTipo.setBounds(new Rectangle(280, 5, 140, 35));
	btnFind.setBounds(new Rectangle(425, 15, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	jpNorthEast.add(btnImprimir, null);
	jpNorthEast.add(btnBrowser, null);
	jpNorthEast.add(btnAyuda, null);
	jpNorthWest.add(tfAnio, null);
	jpNorthWest.add(tfNumero, null);

	jpNorthWest.add(tfTexto, null);
	jpNorthWest.add(cbTipo, null);
	jpNorthWest.add(btnFind, null);
	jpNorth.setLayout(new BorderLayout(5,0));
	jpNorth.add(jpNorthWest, BorderLayout.CENTER);
	jpNorth.add(jpNorthEast, BorderLayout.EAST);
	jpCenter.add(jpDocumento, BorderLayout.CENTER);
	jpDocumento.setLayout(new BorderLayout(0, 5));
	jpDocumento.add(jtDocumento, BorderLayout.CENTER);
	jpCenter.add(jsSearchResults, BorderLayout.WEST);
	jtTitulo.setEditable(false);
	jtTitulo.setHorizontalAlignment(JTextField.CENTER);
	jtTitulo.setToolTipText("T\u00edtulo del Documento");
	jtTitulo.setBackground(new Color(255, 181, 99));
	jtTitulo.setFont(new Font("Droid Sans", 1, 11));
	jpNorthWest.setLayout(null);
	jpNorthWest.setPreferredSize(new Dimension(464, 50));
	jpNorthEast.setLayout(null);
	jpNorthEast.setPreferredSize(new Dimension(310, 50));
	jpDocumento.add(jtTitulo, BorderLayout.NORTH);
	jsSearchResults.getViewport().add(jlSearchResults);
	btnAyuda.setOpaque(true);
	btnAyuda.setFont(new Font("Dialog", 1, 10));
	btnAyuda.setForeground(Color.black);
	btnAyuda.setBackground(new Color(0, 132, 0));
	btnAyuda.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAyuda.setBounds(new Rectangle(205, 10, 95, 35));
	btnAyuda.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAyuda_actionPerformed(e);
		}
	    });
	btnImprimir.setOpaque(true);
	btnImprimir.setFont(new Font("Dialog", 1, 10));
	btnImprimir.setForeground(Color.black);
	btnImprimir.setBackground(new Color(198, 49, 0));
	btnImprimir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnImprimir.setBounds(new Rectangle(5, 10, 95, 35));
	btnImprimir.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    //imprimir();
		    btnImprimir_actionPerformed(e);
		}
	    });

	btnBrowser.setOpaque(true);
	btnBrowser.setFont(new Font("Dialog", 1, 10));
	btnBrowser.setForeground(Color.black);
	btnBrowser.setBackground(new Color(0, 82, 255));
	btnBrowser.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnBrowser.setBounds(new Rectangle(105, 10, 95, 35));
	btnBrowser.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBrowser_actionPerformed(e);
		}
	    });

	this.add(jpCenter, BorderLayout.CENTER);
	this.add(jpNorth, BorderLayout.NORTH);
	cbTipo.setGeneralItem(true);
	tfNumero.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    }
	);

	tfAnio.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);

	tfTexto.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}
	    }
	);
	
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    showHelp();
		}
	    },
	    "Ayuda",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
	

	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    imprimir();
		}
	    },
	    "Imprimir",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);

	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    showOnBrowser();
		}
	    },
	    "Visor Externo",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F9, 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);

	jtDocumento.setEditable(false);

	jlSearchResults.setCellRenderer(new DocumentsRenderer());
	jsSearchResults.setPreferredSize(new Dimension(240, 0));
	jlSearchResults.addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (isEnabled() && e.getButton() == MouseEvent.BUTTON1) {
			int index = jlSearchResults.locationToIndex(e.getPoint());
			selectedDocument = (Documento)jlSearchResults.getModel().getElementAt(index);
			loadDocument();
			Rectangle rect = jlSearchResults.getCellBounds(index, index);
			repaint(rect);
		    }
		}

	    });

	jlSearchResults.addKeyListener(new KeyAdapter() {

			  public void keyPressed(KeyEvent e) {
			  }

			  public void keyReleased(KeyEvent e) {
			      try {
			          if (jlSearchResults.getSelectedIndex() != -1) {
			              selectedDocument = (Documento)jlSearchResults.getModel().getElementAt(jlSearchResults.getSelectedIndex());
			              loadDocument();
			          }
			      } catch (ArrayIndexOutOfBoundsException x) {
			          e.consume();
			      }
			  }

			  public void keyTyped(KeyEvent e) {
			  }

		      }
	);

	if (!Environment.cfg.getProperty("showhelp").equals("false")) {
	    showHelp();
	    Environment.cfg.setProperty("showhelp", "false");
	}

	collectData();

    }

    private void collectData() {
	if (!collecting) {
	    Thread threadTask = new Thread(new Runnable() {
		public void run() {
		    collecting = true;
		    BasicLabel busyLabel = new BasicLabel(IconTypes.busy);
		    busyLabel.setBounds(Environment.getActiveDesktop().getBounds());
		    Environment.getActiveDesktop().add(busyLabel, 0);
		    Environment.setIndeterminate(true);
		    cbTipo.removeAllItems();
		    allDocuments.removeAllElements();
		    FileFilter directoryFilter = new FileFilter() {
			public boolean accept(File file) {
			    return file.isDirectory();
			}
		    };
	    
		    FileFilter htmlFilter = new FileFilter() {
			public boolean accept(File file) {
			    return file.isFile() && (file.getName().endsWith(".html") || file.getName().endsWith(".HTML"));
			}
		    };
	    
		    File baseDir = new File("data");  
		    File[] documentTypes = baseDir.listFiles();
		    // This filter only returns directories
		    documentTypes = baseDir.listFiles(directoryFilter);
		    if (documentTypes.length>0) {
			int _docsQty = 0;
			cbTipo.addItem("Todos");
			jlSearchResults.setListData(new Vector());
			Vector[] documents = new Vector[documentTypes.length];
			for (int i = 0; i < documentTypes.length; i++) {
			    cbTipo.addItem(documentTypes[i].getName());
			    documents[i] = new Vector();
			    File[] documentFolders = documentTypes[i].listFiles(directoryFilter);
			    Arrays.sort(documentFolders, new Comparator<File>() {
				public int compare(File s1, File s2) {
				    return s1.compareTo(s2);
				}
			    });
			    for (int j = 0; j < documentFolders.length; j++) {
				File[] htmlList = documentFolders[j].listFiles(htmlFilter);
				Arrays.sort(htmlList, new Comparator<File>() {
				    public int compare(File s1, File s2) {
					return s1.compareTo(s2);
				    }
				});
				if (htmlList.length > 0) {
				    Documento _doc = new Documento(documentTypes[i].getName(), documentFolders[j].getName(), htmlList[0].getAbsolutePath());
				    String[] _props = documentFolders[j].getName().split("-");
				    if (_props.length == 2) {
					_doc.setAnio(_props[0].trim() );
					_doc.setNumero(_props[1].trim());
				    } else {
					_doc.setAnio(documentFolders[j].getName());
					_doc.setNumero(documentFolders[j].getName());
				    }
				    documents[i].add(_doc);
				    _docsQty++;
				    jpCenter.setBorder(BorderPanel.getBorderPanel("Actualizando colección - (" + _docsQty + " documentos encontrados)"));
				}
			    }
			    allDocuments.addAll(documents[i]);
			    jlSearchResults.setListData(allDocuments);
			    updateListData();
			}
		    }
		    collecting = false;
		    Environment.setIndeterminate(false);
		    Environment.getActiveDesktop().remove(busyLabel);
		}
	    });
	    threadTask.start();
	}
    }

    private void generateHTMLFromImages(String _dir) {
	FileFilter directoryFilter = new FileFilter() {
	    public boolean accept(File file) {
		return file.isDirectory();
	    }
	};

	FileFilter imageFilter = new FileFilter() {
	    public boolean accept(File f) {
		String extension = AlbumUtils.getExtension(f);
		if (extension != null) {
		    if (f.isFile() && (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif) || extension.equals(AlbumUtils.gif) || extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg) || extension.equals(AlbumUtils.png))) {
			return true;
		    } else {
			return false;
		    }
		}
		return false;
	    }
	};
	File baseDir = new File(_dir);  
	File[] _directories = baseDir.listFiles();
	_directories = baseDir.listFiles(directoryFilter);
	FileFilter forbiddenFilter = new FileFilter() {
	    public boolean accept(File f) {
		String extension = AlbumUtils.getExtension(f);
		if (extension != null) {
		    if (f.isFile() && (extension.equals("html") || extension.equals("doc"))) {
			return true;
		    } else {
			return false;
		    }
		}
		return false;
	    }
	};
	for (int i = 0; i < _directories.length; i++) {
	    File[] _files = _directories[i].listFiles(forbiddenFilter);
	    if (_files.length == 0) {
	        System.out.println(_directories[i].getAbsolutePath() + " --> si");
	        generateHTMLFromImages(_directories[i].getAbsolutePath());
	    } else {
	        System.out.println(_directories[i].getAbsolutePath() + " --> no");
	    }

	}
	File[] _files = baseDir.listFiles();
	_files = baseDir.listFiles(imageFilter);
	Arrays.sort(_files, new Comparator<File>() {
	    public int compare(File s1, File s2) {
		return s1.compareTo(s2);
	    }
	});
	if (_files.length > 0) {
	    System.out.println("Procesando: " + _files[0]);
	    String _fileName = _files[0].getParent() + File.separator + _files[0].getName().substring(0, _files[0].getName().indexOf(".")) + ".html";
	    try {
		FileOutputStream _htmlFile = new FileOutputStream(_fileName, false);
		String _header = "<html>\n" +
					    "   <body>\n" +
					    "           <p align='center' style='margin-bottom: 0cm'>\n";
		String _tail = "           </p>\n" +
					    "   </body>\n" +
					    "</html>";
	        _htmlFile.write(_header.getBytes());
		for (int i = 0; i < _files.length; i++) {
		    try {
		        String _imgsrc = "";
			try {
			    BufferedImage bufferedImage;
			    bufferedImage = ImageIO.read(_files[i]);
			    double width = bufferedImage.getWidth();
			    double height = bufferedImage.getHeight();
			    double proporcion = 800.0 / width;
			    width = proporcion * width;
			    height = proporcion * height;
			    _imgsrc = "                        <img src='" + _files[i].getName() + "' align=bottom width=" + (int)width + " height=" + (int)height + " border=0><br>\n";
			} catch (Exception e) {
			    // TODO: Add catch code
			    //e.printStackTrace();
			    _imgsrc = "                        <img src='" + _files[i].getName() + "' align=bottom width=800 height=1100 border=0><br>\n";
			}
			//ratio = MIN( maxWidth / width, maxHeight/ height );
			_htmlFile.write(_imgsrc.getBytes());
		    } catch (Exception e) {
			//e.printStackTrace();
		        System.err.println(_files[i]);
			e.printStackTrace();
			continue;
		    }
		}
	        _htmlFile.write(_tail.getBytes());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    public void refresh() {
	if (!collecting && !searching) {
	    findInFiles();
	} else {
	    Advisor.messageBox("Espere que termine la operación actual", "Error");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	//doSomethingUseful();
	//collectData();
	refresh();
    }
    
    private void doSomethingUseful() {
	convertDirectoriesJPG2HTML();
    }

    private void findInFiles() {
	// Create a pattern to match comments
	if (!searching) {
	    Thread threadTask = new Thread(new Runnable() {
		public void run() {
		    jpCenter.setBorder(BorderPanel.getBorderPanel("Documentos del Digesto Legislativo - (Buscando documentos...)"));
		    String test = tfTexto.getString();
		    test = test.replaceAll("\\b\\s{2,}\\b", " ").trim().replaceAll(" ", "|"); // Replaces multiple spaces
		    String[] _words = test.split("\\+");
		    if (_words.length > 1) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < _words.length; i++) {
			    _words[i] = "(?=.*" + _words[i] + ")";
			    builder.append("").append(_words[i]);
			}
			test = builder.toString();
		    }
		    if (test.length() >= 5 || tfAnio.getInteger() > 1900 || tfNumero.getInteger() > 0 || cbTipo.getSelectedIndex() > 0) {
		        searching = true;
		        BasicLabel busyLabel = new BasicLabel(IconTypes.busy);
		        busyLabel.setBounds(Environment.getActiveDesktop().getBounds());
		        Environment.getActiveDesktop().add(busyLabel, 0);
			Environment.setIndeterminate(true);
		        filteredDocuments.removeAllElements();
			String _numero = new DecimalFormat("#0000").format(tfNumero.getInteger());
		        for (int i = 0; i < allDocuments.size(); i++) {
		            boolean _valid = true;
			    Documento _documento = (Documento)allDocuments.elementAt(i);
		            if (_valid && tfNumero.getInteger() != 0) {
				if (!_documento.getNumero().equalsIgnoreCase(_numero)) {
		                    _valid = false;
				}
			    }
			    if (_valid && tfAnio.getInteger() > 1900) {
				if (!_documento.getAnio().equalsIgnoreCase(tfAnio.getString())) {
				    _valid = false;
				}
			    }
			    if (_valid && cbTipo.getSelectedIndex() > 0) {
				if (!_documento.getTipo().equalsIgnoreCase(cbTipo.getSelectedItem().toString())) {
				    _valid = false;
				}
			    }
		            File _file = new File(_documento.getArchivo());
		            if (test.length() >= 5) {
		                Pattern p = Pattern.compile(test, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		                //Pattern p = Pattern.compile(test + ".*$", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		                //Pattern p = Pattern.compile("//.*$", Pattern.MULTILINE);
		                // Get a Channel for the source file
		                try {
		                    FileInputStream fis = new FileInputStream(_file);
		                    FileChannel fc = fis.getChannel();
		    
		                    // Get a CharBuffer from the source file
		                    ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int)fc.size());
		                    Charset cs = Charset.forName("8859_1");
		                    CharsetDecoder cd = cs.newDecoder();
		                    CharBuffer cb = cd.decode(bb);
		    
		                    // Run some matches
		                    Matcher m = p.matcher(cb);
		                    if (m.find() && _valid) {
		                        filteredDocuments.add(_documento);
		                    }
				    fis.close();
		                } catch (Exception e) {
		                    searching = false;
		                    Environment.setIndeterminate(false);
		                    Environment.getActiveDesktop().remove(busyLabel);
		                    Advisor.messageBox(e.getMessage(), "Error");
		                }
		            } else if (_valid) {
		                filteredDocuments.add(_documento);
		            }
		        }
			if (filteredDocuments.size() != 0) {
			    jlSearchResults.setListData(filteredDocuments);
			} else {
			    Advisor.messageBox("No se han encontrado documentos con los datos solicitados", "Error");
			}
		        searching = false;
		        Environment.setIndeterminate(false);
		        Environment.getActiveDesktop().remove(busyLabel);
		    } else {
		        jlSearchResults.setListData(allDocuments);
		        //Advisor.messageBox("Para que la búsqueda se realice debe cumplirse AL MENOS UNA de las siguientes condiciones:\n* El año debe ser mayor que 1900\n* El número debe ser distinto de cero\n* Al menos 5 caracteres en la búsqueda por palabras\n* El Tipo debe ser distinto de \"Todos\"", "Error");
		    }
		    updateListData();
		}
	    });
	    threadTask.start();
	}
    }
    
    private void updateListData() {
	int _qty = jlSearchResults.getModel().getSize();
	jlSearchResults.updateUI();
	if (_qty == 0) {
	    jpCenter.setBorder(BorderPanel.getBorderPanel("Documentos del Digesto Legislativo - (No se han encontrado documentos)"));
	} else if (_qty == 1) {
	    jpCenter.setBorder(BorderPanel.getBorderPanel("Documentos del Digesto Legislativo - (1 documento encontrado)"));
	} else {
	    jpCenter.setBorder(BorderPanel.getBorderPanel("Documentos del Digesto Legislativo - (" + _qty + " documentos encontrados)"));
	}
    }

    private void convertDirectoriesJPG2HTML () {
	JFileChooser chooser = new JFileChooser(Environment.cfg.getProperty("JPG2HTMLPath") + File.separator);
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF Directory Selected
	    try {
	        Environment.cfg.setProperty("JPG2HTMLPath", chooser.getSelectedFile().getAbsolutePath());
		/*String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.endsWith("/")) {
		    path += "/";
		}*/
		generateHTMLFromImages(chooser.getSelectedFile().getAbsolutePath());
	    } catch (Exception x) {
		Advisor.printException(x);
	    }
	}
	refresh();
    }

    public void loadDocument() {
	String _documentPath = selectedDocument.getArchivo();
	try {
	    if (_documentPath.endsWith(".html")) {
		jtDocumento.setPage(new URL("file:///" + _documentPath));
	    } else if (_documentPath.endsWith(".jpg")) {
		String imgsrc = (new URL("file:///" + _documentPath)).toExternalForm();
	        String html = "<img src='" + imgsrc + "' alt='' name='captura' width='800' height='600' /><br />";
		jtDocumento.setText(html);
	    }
	    jtTitulo.setText((new File(_documentPath)).getName());
	} catch (MalformedURLException e) {
	    jtDocumento.setText("Error al cargar el archivo");
	    e.printStackTrace();
	}
    }

    private void btnImprimir_actionPerformed(ActionEvent e) {
	imprimir();
    }
    
    private void imprimir() {
	if (selectedDocument.getArchivo().length() > 0) {
	    DocumentRenderer _doc = new DocumentRenderer();
	    _doc.print(jtDocumento.getTextArea());
	}
    }

    private void btnAyuda_actionPerformed(ActionEvent e) {
	showHelp();
    }

    private void btnBrowser_actionPerformed(ActionEvent e) {
	showOnBrowser();
    }
    
    private void showOnBrowser() {
	if (selectedDocument.getArchivo().length() > 0) {
	    BrowserLauncher.openURL(selectedDocument.getArchivo());
	}
    }

    class DocumentsRenderer extends JTextField implements ListCellRenderer {
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    if (value instanceof Documento) {
		Documento _doc = (Documento)value;
		if (_doc.getNombre().indexOf(" - ") != -1) {
		    setText(_doc.getTipo() + " -> " + _doc.getNombre().substring(_doc.getNombre().indexOf(" - ")+3) + "/" + _doc.getNombre().substring(0, _doc.getNombre().indexOf(" - ")) + ": " + (new File(_doc.getArchivo())).getName());
		    setToolTipText(_doc.getTipo() + " -> " + _doc.getNombre().substring(_doc.getNombre().indexOf(" - ")+3) + "/" + _doc.getNombre().substring(0, _doc.getNombre().indexOf(" - ")) + ": " + (new File(_doc.getArchivo())).getName());
		} else {
		    setText(_doc.getTipo() + " -> " + _doc.getNombre() + ": " + (new File(_doc.getArchivo())).getName());
		    setToolTipText(_doc.getTipo() + " -> " + _doc.getNombre() + ": " + (new File(_doc.getArchivo())).getName());
		}
	    }
	    if (isSelected) {
	      setBackground(list.getSelectionBackground());
	      setForeground(list.getSelectionForeground());
	    } else {
	      setBackground(list.getBackground());
	      setForeground(list.getForeground());
	    }
	    return this;
	}
    }

    private void showHelp() {
	String _ayuda = "<html><p align=center><font size=5><b>¿Un poco de ayuda?</b></font></p>" + 
			"<br><p align=center><b>Para la búsqueda</b>:</p>" +
			"<p align=left>* El campo de búsqueda <b>\"Año\"</b> debe ser mayor que 1900 para ser tenido en cuenta<br>" +
			"* El campo de búsqueda <b>\"Número\"</b> debe ser distinto de 0 (cero) para ser tenido en cuenta<br>" +
			"* El campo de búsqueda <b>\"Palabras\"</b> debe tener al menos 5 (cinco) caracteres para ser tenido en cuenta, si en este campo colocamos palabras separadas" + 
			" por espacios entonces la búsqueda será no excluyente (ejemplo: con la búsqueda <i>\"dieta conce\"</i> el sistema arrojará un listado" +
			" de los documentos que contengan CUALQUIERA de las palabras \"dieta\" y \"conce\", en cambio con la búsqueda <i>\"dieta+conce\"</i> el sistema arrojará un listado" +
			" de los documentos que contengan AMBAS PALABRAS, por lo tanto seguramente se obtendrán menos resultados)<br>" +
			"* El campo de búsqueda <b>\"Tipo\"</b> debe ser distinto del valor <b>\"Todos\"</b> para ser tenido en cuenta<br>" +
			"* Los campos de búsqueda actúan en conjunto, por lo tanto la búsqueda evaluará los datos de todos los campos de búsqueda" +
			"  (siempre que cumplan la condición necesaria para ser tenidos en cuenta)</p>" +
			"<br><p align=center><b>Para la Impresión</b>:</p>" +
			"<p align=left>* Si el documento mostrado en pantalla es del tipo TEXTO entonces la impresión podrá ser realizada desde el sistema." + 
			"<br>* Si el documento mostrado en pantalla es del tipo IMAGEN entonces se recomienda una vista previa o bien usar el visor externo para imprimir." +
			"</p>" +
			"</html>";
	Advisor.showInternalMessageDialog(_ayuda);
    }

}

