package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.border.BevelBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassDetalleRelevamientoPublicidad;

public class ReparaFotosCarteles extends BasicPrimitivePanel {
    //int numeroFuncion = -1;
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();

    private SaveDataButton btnGuardarFoto = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private BasicPanel contentPanel = new BasicPanel("");

    public static final int DIRECCION = 1;
    public static final int COMERCIO = 2;
    public static final int PUBLICIDAD = 3;

    private TFInput tfNroEncuestador = new TFInput(DataTypes.STRING, "N?? de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona N??", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.STRING, "N?? de Encuesta", false);
    private TFInput tfNombreFoto = new TFInput(DataTypes.STRING, "Nombre", false);

    private int tipoObservacion = 0;

    private ImageLabel lblFoto = new ImageLabel();

    private int[] sizeColumnList = { 346 , 111};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Anuncios", dataRow){
	public void calculate() {
	    //controlBotones();
	}
    }
    ;

    private BufferedImage photoImage = null;
    
    private File fotoFile;
    private final int fotoWidthLimit = 640;
    private final int fotoHeightLimit = 480;
    private final long fotoLengthLimit = 65536;
    private ClassDetalleRelevamientoPublicidad detalleRelevamientoPublicidad;

    public ReparaFotosCarteles() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(792, 352));
	this.setTitle("Privilegios");
	//taDescripcion.setEditable(false);

	//spFoto = new BasicScrollPane(lblFoto);
	contentPanel.add(tfNombreFoto, null);
	contentPanel.add(listPanel, null);
	contentPanel.add(tfNroEncuesta, null);
	contentPanel.add(tfZonaNro, null);

	contentPanel.add(tfNroEncuestador, null);
	contentPanel.add(lblFoto, null);
	addButton(btnClose);
	this.add(contentPanel, null);
	addButton(btnGuardarFoto);
	contentPanel.setBounds(new Rectangle(0, 0, 655, 345));
	contentPanel.setLayout(null);

	tfNroEncuestador.setBounds(new Rectangle(15, 265, 115, 35));
	tfZonaNro.setBounds(new Rectangle(155, 265, 115, 35));
	tfNroEncuesta.setBounds(new Rectangle(295, 265, 115, 35));
	btnGuardarFoto.setBounds(new Rectangle(585, 360, 40, 25));
	btnGuardarFoto.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGuardarFoto_actionPerformed(e);
		}
	    }
	);
	listPanel.setBounds(new Rectangle(10, 10, 520, 250));
	tfNombreFoto.setBounds(new Rectangle(535, 265, 205, 35));
	btnClose.setBounds(new Rectangle(615, 360, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);

	lblFoto.setBounds(new Rectangle(535, 10, 240, 250));
	lblFoto.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	lblFoto.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
			loadImage();
		    }
		}

	    }
	);
	setHeaderList();
	refreshGrilla();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Texto del Anuncio");
	headerList.addElement("Foto");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObject();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   
					       }
					   }

				       }
	);
	
	listPanel.getTable().addKeyListener(new KeyAdapter() {
	     public void keyReleased(KeyEvent key) {
		 if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
			    loadObject();
		 }
	     }
	 });
	listPanel.setParams("gea_salta.getAllCarteles", "", headerList);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	this.getParentInternalFrame().close();
    }
    
    private void btnGuardarFoto_actionPerformed(ActionEvent e) {
	if (photoImage != null) {
	    LibIMG.saveImage(photoImage, "gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + detalleRelevamientoPublicidad.getIddetallerelevamiento());
	    String params = ""+detalleRelevamientoPublicidad.getIddetallerelevamiento()+",'"+tfNombreFoto.getValue()+"'";
	    if (LibSQL.getInt("gea_salta.setfotocartel", params) > 0) {
		
	    } else {
	        Advisor.messageBox("Ocurri?? un error al grabar los datos", "Error");
	    }
	     //refreshGrilla();
	}
    }
    
    private void refreshGrilla(){
	listPanel.refresh("");
    }
    
    private void loadObject(){
	tfNombreFoto.setValue("");
	//String id = dataRow.elementAt(3).toString();
	detalleRelevamientoPublicidad = new ClassDetalleRelevamientoPublicidad();
	detalleRelevamientoPublicidad.setIddetallerelevamiento(Integer.parseInt(dataRow.elementAt(0).toString()));
	detalleRelevamientoPublicidad.retrieveData();
	tfNroEncuestador.setValue(detalleRelevamientoPublicidad.getNroEncuestador());
	tfZonaNro.setValue(detalleRelevamientoPublicidad.getNroZona());
	tfNroEncuesta.setValue(detalleRelevamientoPublicidad.getNroEncuesta());
	photoImage = LibIMG.getImage("gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + detalleRelevamientoPublicidad.getIddetallerelevamiento());
	lblFoto.setImage(photoImage);
    }
    
    private void loadImage() {
	/*ImageCropper cropper = new ImageCropper(lbl3h_foto, true);
	ExtendedInternalFrame k = new ExtendedInternalFrame("Foto");
	k.setCentralPanel(cropper);
	k.show();
	tf3h.setValue(cropper.getFileName());*/
	 try {
	     //String filename = File.separator + "jpg";
	     JFileChooser fc = new JFileChooser();
	     if (!Environment.cfg.getProperty("pictures").equalsIgnoreCase(ConfigFile.nullProperty))  {
		 fc.setCurrentDirectory(new File(Environment.cfg.getProperty("pictures")));
	     }
	     //Proced.getLastPath());
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
	     int seleccion = fc.showOpenDialog(this);
	     if(seleccion == JFileChooser.APPROVE_OPTION){
		 fotoFile = fc.getSelectedFile();
		 if (fotoFile != null) {
		     Environment.cfg.setProperty("pictures", fotoFile.getPath().replaceAll(fotoFile.getName(),""));
		     //LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
		     if (fotoFile.length() < fotoLengthLimit) {
			 photoImage = ImageIO.read(fotoFile);
			 if ((photoImage.getWidth() <= fotoWidthLimit) && (photoImage.getHeight() <= fotoHeightLimit)) {
			     LibIMG.escalaImg(photoImage, lblFoto);
			     /*if (person != null) { 
				 person.setPhotoImage(photo);
			     }*/
			     tfNombreFoto.setValue(fotoFile.getName());
			     /*if (LibSQL.saveImageSQL(fotoFile, "org.persons", "photo", "WHERE idperson = " + person.getIdPerson())) {
				 //Advisor.messageBox("Update Success!!","Message");
				 //dispose();
			     } else {
				 //Advisor.messageBox("Can't save image", "Error");
				 Advisor.messageBox("No se pudo grabar la imagen", "Error");
			     }*/
			 } else
			     //Advisor.messageBox("Photo width/height not allowed. Can't be greater than " + fotoWidthLimit + "x" + fotoHeightLimit + " pixels", "Messsage");
			     Advisor.messageBox("Tama??o de imagen no permitido. Las dimensiones m??ximas son " + fotoWidthLimit + "x" + fotoHeightLimit + " p??xeles", "Aviso");
		     } else
			 //Advisor.messageBox("File length not allowed. Cant' be greater than " + (fotoLengthLimit / 1024.0) + " Kbytes", "Messsage");
			 Advisor.messageBox("Tama??o de archivo no permitido. El tama??o m??ximo es " + (fotoLengthLimit / 1024.0) + " Kbytes", "Aviso");
		 }
	     }
	 } catch (IOException x) {
	     Advisor.messageBox(x.getMessage(), "Error");
	     x.printStackTrace();
	 }
    }
}
