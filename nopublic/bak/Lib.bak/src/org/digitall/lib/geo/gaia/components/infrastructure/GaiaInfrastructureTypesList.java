package org.digitall.lib.geo.gaia.components.infrastructure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.components.infrastructure.GaiaInfrastructureType;
import org.digitall.lib.image.ImageCropper;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;

public class GaiaInfrastructureTypesList extends BasicPrimitivePanel {
    private int[] sizeColumnList = {41, 278};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Infraestructuras", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
     
    private CloseButton btnClose = new CloseButton();
    private AssignButton btnAdd = new AssignButton(true);
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicDialog parentContainer;
    private GaiaInfrastructureType infrastructureType;
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    private ImageLabel lblSymbol = new ImageLabel();

    public GaiaInfrastructureTypesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(360, 322));
	listPanel.setBounds(new Rectangle(0, 70, 340, 310));
	panelData.setBounds(new Rectangle(0, 0, 340, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(85, 30, 165, 35));
	//new Rectangle(0, 400, 315, 2));
	//setSize(new Dimension(350, 2));
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAdd.setBounds(new Rectangle(250, 40, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(285, 40, 40, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(320, 40, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	lblSymbol.setBounds(new Rectangle(10, 25, 65, 65));
	//this.add(btnClose, null);
	panelData.add(lblSymbol, null);
	panelData.add(btnFind, null);
	panelData.add(btnCancel, null);
	panelData.add(tfName, null);
	panelData.add(btnAdd, null);
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, null);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nueva Infraestructura"));
	panelData.setSize(new Dimension(360, 11));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 100));

	lblSymbol.addMouseListener(new MouseAdapter() {

			       public void mouseClicked(MouseEvent e) {
				   if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				   } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
				       loadImage();
				   }
			       }

			   }
	);

	setHeaderList();
    }

    private void loadImage() {
	    try {
		JFileChooser fc = new JFileChooser();
		if (!Environment.cfg.getProperty("driverspics").equalsIgnoreCase("N/A"))  {
		    fc.setCurrentDirectory(new File(Environment.cfg.getProperty("driverspics")));
		}
		
		/** REVISAR LA OBTENCION DE LASTPATH */
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		//Add custom icons for file types.
		fc.setFileView(new ImageFileView());
		//Add the preview pane.
		fc.setAccessory(new ImagePreview(fc));
		fc.setMultiSelectionEnabled(false);
		// Show open dialog; this method does not return until the dialog is closed
		fc.showOpenDialog(this);
		File fotoFile = fc.getSelectedFile();
		if (fotoFile != null) {
		    Environment.cfg.setProperty("infrastructurespics", fotoFile.getPath().replaceAll(fotoFile.getName(),""));
		    //LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
		    if (fotoFile.length() < 65535) {
			BufferedImage img = ImageIO.read(fotoFile);
			//System.out.println("Width, height: " + img.getWidth() + "," + img.getHeight());
			if ((img.getWidth() <= 100) && (img.getHeight() <= 100)) {
			    BufferedImage img1 = LibIMG.scale((double)getWidth() / (double)img.getWidth(), img);
			    setSize(img1.getWidth(this), img1.getHeight(this));
			    lblSymbol.setIcon(new ImageIcon(img1));
			    lblSymbol.setImage(img1);
			} else {
			    Advisor.messageBox("Photo width/height not allowed. Can't be greater than " + 100 + "x" + 100 + " pixels", "Messsage");
			}
		    } else {
			Advisor.messageBox("File length not allowed. Cant' be greater than " + (65535 / 1024.0) + " Kbytes", "Messsage");
		    }
		}
	    } catch (IOException x) {
		x.printStackTrace();
	    }
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("ID");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadInfrastructureType();
		    loadData();
		}
	    }
	});
	listPanel.setParams("tabs.getAllInfrastructureTypes", "''", headerList);
    }

    public void refresh() {
	String params = "''";
	listPanel.refresh(params);
    }
    
    private void loadInfrastructureType(){
	if (infrastructureType == null){
	    infrastructureType = new GaiaInfrastructureType();
	} else {
	    infrastructureType.setIdType(Integer.parseInt(""+ dataRow.elementAt(0)));
	    infrastructureType.setName(dataRow.elementAt(1).toString());
	    infrastructureType.retrieveSymbol();
	}
    }
    
    private void loadData(){
	tfName.setValue(infrastructureType.getName());
	lblSymbol.setImage(infrastructureType.getSymbol());
	addAction = false;
    }

    private void clearData() {
	tfName.setValue("");
	lblSymbol.setImage(null);
	addAction = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	if (addAction){
	    infrastructureType = new GaiaInfrastructureType();
	}
	infrastructureType.setName(tfName.getString());
	infrastructureType.setSymbol(lblSymbol.getImage());
	infrastructureType.saveData();
	
	refresh();
	clearData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
	
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
}
