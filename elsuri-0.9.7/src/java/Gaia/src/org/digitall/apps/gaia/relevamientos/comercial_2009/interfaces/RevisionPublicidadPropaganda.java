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
 * RevisionPublicidadPropaganda.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassDetalleRelevamientoPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassRelevamientoPublicidad2009Desglosada;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassRelevamientoPublicidad2009Encabezado;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses.ErrDesgloseCarteles;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class RevisionPublicidadPropaganda extends GaiaPrimitiveForm {
    //int numeroFuncion = -1;
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();

    private FindButton btnSearch = new FindButton();
    private AddComboButton btnDesglosarCartelSel = new AddComboButton();

    private BasicPanel contentPanel = new BasicPanel("");

    public static final int DIRECCION = 1;
    public static final int COMERCIO = 2;
    public static final int PUBLICIDAD = 3;

    private TFInput tfNombreFoto = new TFInput(DataTypes.STRING, "Foto", false);
    private TFInput tfAncho = new TFInput(DataTypes.DOUBLE, "Ancho", false);
    private TFInput tfAlto = new TFInput(DataTypes.DOUBLE, "Alto", false);
    private TFInput tfSuperficie = new TFInput(DataTypes.DOUBLE, "Superficie", false);

    private TFInput tfBuscarZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfBuscarTexto = new TFInput(DataTypes.STRING, "Texto", false);
    
    private BasicRadioButton rbConFotos = new BasicRadioButton();
    private BasicRadioButton rbSinFotos = new BasicRadioButton();
    private BasicRadioButton rbTodos = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    
    //private int tipoObservacion = 0;

    private ImageLabel lblFoto = new ImageLabel();

    private int[] sizeColumnList = { 212 , 77, 81, 87};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel listPanel = new GridPanel(50, sizeColumnList, "Anuncios", dataRow){
	public void calculate() {
	    setEnabledButton(false);
	    limpiarCampos();
	}
    }
    ;

    private BufferedImage photoImage = null;
    
    private File fotoFile;
    private final int fotoWidthLimit = 640;
    private final int fotoHeightLimit = 480;
    private final long fotoLengthLimit = 65536;
    private ClassDetalleRelevamientoPublicidad detalleRelevamientoPublicidad;
    
    /**
     * Componentes que eran de FormCarteles
     * */
    private CBInput cbTextoCartel = new CBInput(0, "Marca/Anuncio", true);
    private CBInput cbEmpresas = new CBInput(0, "Empresa", false);
    private ErrDesgloseCarteles errorForm;
    private ClassRelevamientoPublicidad2009Desglosada relevamientoDesglosado = new ClassRelevamientoPublicidad2009Desglosada();
    private CBInput cbFormas = new CBInput(0, "Forma", false);
    private CBInput cbIluminacion = new CBInput(0, "Iluminación", false);
    private CBInput cbFaz = new CBInput(0, "Faz", false);
    private AddComboButton btnAddEmpresa = new AddComboButton();
    private Timer timerCalcularSuperficie;

    public RevisionPublicidadPropaganda() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(814, 593));
	this.setSize(new Dimension(814, 593));
        contentPanel.setPreferredSize(new Dimension(782, 484));
        contentPanel.setSize(new Dimension(782, 442));
	this.setTitle("Revisión de carteles de Publicidad y Propaganda");
	//taDescripcion.setEditable(false);

	//spFoto = new BasicScrollPane(lblFoto);
	btnSearch.setBounds(new Rectangle(495, 10, 35, 35));
	btnSearch.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSearch_actionPerformed(e);
		}
	    }
	);
        contentPanel.add(tfNombreFoto, null);
        contentPanel.add(tfSuperficie, null);
        contentPanel.add(tfAncho, null);
        contentPanel.add(tfAlto, null);
        contentPanel.add(rbConFotos, null);
        contentPanel.add(rbTodos, null);
        contentPanel.add(listPanel, null);
        contentPanel.add(lblFoto, null);
        contentPanel.add(tfBuscarTexto, null);
        contentPanel.add(tfBuscarZonaNro, null);
        contentPanel.add(btnSearch, null);

        contentPanel.add(btnDesglosarCartelSel,null);
        contentPanel.add(rbSinFotos, BorderLayout.CENTER);
        contentPanel.add(cbTextoCartel,null);
        contentPanel.add(cbFormas,null);
        contentPanel.add(cbIluminacion,null);
        contentPanel.add(cbFaz, null);
        contentPanel.add(cbEmpresas, null);

	contentPanel.add(btnAddEmpresa,null);
	btnDesglosarCartelSel.setEnabled(false);
        btnDesglosarCartelSel.setToolTipText("Desglosar cartel");
	btnDesglosarCartelSel.setBounds(new Rectangle(745, 310, 35, 35));
	btnDesglosarCartelSel.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDesglosarCartelSel_actionPerformed(e);
		}
	    }
	);
	//this.add(contentPanel, BorderLayout.CENTER);
	this.setCentralPanel(contentPanel);
	//contentPanel.setBounds(new Rectangle(0, 0, 655, 345));
	contentPanel.setLayout(null);
	//contentPanel.setLayout(new BorderLayout());


        tfSuperficie.setEditable(false);
        tfAncho.setEditable(false);
        tfAlto.setEditable(false);
        tfNombreFoto.setEditable(false);

        tfBuscarTexto.setBounds(new Rectangle(145, 5, 340, 35));
        tfBuscarZonaNro.setBounds(new Rectangle(10, 5, 115, 35));

        tfBuscarTexto.getTextField().addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        buscarCarteles();
                    }
                }
            }
        );
        tfBuscarZonaNro.getTextField().addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        buscarCarteles();
                    }
                }
            }
        );

        listPanel.setBounds(new Rectangle(10, 50, 520, 250));
	cbEmpresas.setBounds(new Rectangle(15, 390, 350, 35));
	tfNombreFoto.setBounds(new Rectangle(535, 305, 205, 35));
        tfSuperficie.setBounds(new Rectangle(200, 345, 70, 35));
        tfAncho.setBounds(new Rectangle(15, 345, 70, 35));
        tfAlto.setBounds(new Rectangle(110, 345, 70, 35));
        
        rbConFotos.setBounds(new Rectangle(535, 10, 85, 35));
        rbTodos.setBounds(new Rectangle(705, 10, 85, 35));
        
        rbConFotos.setText("Con Foto");
        rbSinFotos.setText("Sin Foto");
        rbTodos.setText("Todos");
        rbConFotos.setSelected(true);
        grupo.add(rbConFotos);
        grupo.add(rbSinFotos);
        grupo.add(rbTodos);
        rbSinFotos.setBounds(new Rectangle(620, 10, 85, 35));

	lblFoto.setBounds(new Rectangle(535, 50, 240, 250));
	lblFoto.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	lblFoto.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
			//loadImage();
		    }
		}

	    }
	);
        rbConFotos.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          refreshGrilla();
                                      }
                                  }
        );
        rbSinFotos.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          refreshGrilla();
                                      }
                                  }
        );
        rbTodos.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          refreshGrilla();
                                      }
                                  }
        );
        cbTextoCartel.setBounds(new Rectangle(15, 305, 350, 35));
        cbTextoCartel.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    cbTextoCartel_itemStateChanged(e);
                }
            });
        cbEmpresas.setBounds(new Rectangle(15, 390, 350, 55));
        cbFormas.setBounds(new Rectangle(390, 345, 165, 35));
        cbIluminacion.setBounds(new Rectangle(575, 345, 165, 35));
        cbFaz.setBounds(new Rectangle(295, 345, 70, 35));
        btnAddEmpresa.setBounds(new Rectangle(370, 405, 25, 20));
        btnAddEmpresa.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddEmpresa_actionPerformed(e);
                }
            });
        btnAddEmpresa.setToolTipText("Agregar Empresa");
        
        tfAncho.getTextField().addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                        calcularSuperficie();
                }

            }
        );        
        tfAlto.getTextField().addKeyListener(new KeyAdapter() {
                
                public void keyTyped(KeyEvent e) {
                        calcularSuperficie();
                }
            }
        );


        tfAncho.getTextField().addFocusListener(new FocusAdapter() {
                
                public void focusLost(FocusEvent e) {
                        calcularSuperficie();
                }

            }
        );        

        tfAlto.getTextField().addFocusListener(new FocusAdapter() {
                
                public void focusLost(FocusEvent e) {
                        calcularSuperficie();
                }

            }
        );        
        cbEmpresas.autoSize();
        cbFormas.autoSize();
        cbIluminacion.autoSize();
        cbTextoCartel.autoSize();
        cbFaz.autoSize();
	setHeaderList();
        this.setVisibleCloseButton(false);
        this.setVisibleDeleteButton(false);
        this.setVisiblePrintButton(false);
        btnDesglosarCartelSel.setEnabled(false);
        loadCombos();
        seleccionarEmpresa();
	//refreshGrilla();
	timerCalcularSuperficie = new Timer(500, new ActionListener() {
	                       public void actionPerformed(ActionEvent e) {
                                    calcularSuperficie();
                                }
            }
        );
        timerCalcularSuperficie.start();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	headerList.addElement("Texto del Anuncio");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("Revisado");
        headerList.addElement("Reportado");
        headerList.addElement("Desglosado");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObject();
                                                   checkButtons();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   
					       }
					   }

				       }
	);
	
	listPanel.getTable().addKeyListener(new KeyAdapter() {
	     public void keyReleased(KeyEvent key) {
		 if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
			    loadObject();
                            checkButtons();
		 }
	     }
	 });
	listPanel.setParams("gea_salta.getAllCartelesByFilter" , "'','',"+rbConFotos.isSelected() + "," +rbSinFotos.isSelected() + "," + rbTodos.isSelected(), headerList);
    }
    
    private void refreshGrilla(){
	listPanel.refresh("'"+tfBuscarZonaNro.getString()+"','" + tfBuscarTexto.getString()+"',"+rbConFotos.isSelected() + "," +rbSinFotos.isSelected() + "," + rbTodos.isSelected());
        btnDesglosarCartelSel.setEnabled(false);
    }
    
    private void cbTextoCartel_itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            seleccionarEmpresa();
        }
    }
    
    private void loadObject(){
	tfNombreFoto.setValue("");
	detalleRelevamientoPublicidad = new ClassDetalleRelevamientoPublicidad();
	detalleRelevamientoPublicidad.setIddetallerelevamiento(Integer.parseInt(dataRow.elementAt(3).toString()));
	detalleRelevamientoPublicidad.retrieveData();
        
        /* 
	ClassRelevamientoPublicidad2009Encabezado encabezado = new ClassRelevamientoPublicidad2009Encabezado();
	encabezado.setIdRelevamientoPublicidad(detalleRelevamientoPublicidad.getIdrelevamientopublicidad());
	encabezado.retrieveData();
        */
        //Cargo los campos de textos con los valores del objeto detalleRelevamientoPublicidad
	tfNombreFoto.setText(detalleRelevamientoPublicidad.getF3h());
        tfAncho.setValue(detalleRelevamientoPublicidad.getF3d());
        tfAlto.setValue(detalleRelevamientoPublicidad.getF3c());
        tfSuperficie.setValue(detalleRelevamientoPublicidad.getF3f());
        cbFaz.setSelectedValue(("" + detalleRelevamientoPublicidad.getF3e()).toUpperCase());
        cbFormas.setSelectedValue(("" + detalleRelevamientoPublicidad.getF3a()).toUpperCase());
        cbIluminacion.setSelectedValue(("" + detalleRelevamientoPublicidad.getF3b()).toUpperCase());
        cbTextoCartel.setSelectedValue(detalleRelevamientoPublicidad.getF3g());
        if(!cbTextoCartel.getSelectedItem().toString().equals(detalleRelevamientoPublicidad.getF3g())){
            cbTextoCartel.setSelectedValue("");
        } 
	photoImage = LibIMG.getImage("gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + detalleRelevamientoPublicidad.getIddetallerelevamiento());
	lblFoto.setImage(photoImage);
        btnDesglosarCartelSel.setEnabled(true);
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
			     Advisor.messageBox("Tamaño de imagen no permitido. Las dimensiones máximas son " + fotoWidthLimit + "x" + fotoHeightLimit + " píxeles", "Aviso");
		     } else
			 //Advisor.messageBox("File length not allowed. Cant' be greater than " + (fotoLengthLimit / 1024.0) + " Kbytes", "Messsage");
			 Advisor.messageBox("Tamaño de archivo no permitido. El tamaño máximo es " + (fotoLengthLimit / 1024.0) + " Kbytes", "Aviso");
		 }
	     }
	 } catch (IOException x) {
	     Advisor.messageBox(x.getMessage(), "Error");
	     x.printStackTrace();
	 }
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
        buscarCarteles();
    }
    
    private void buscarCarteles(){
        //listPanel.refresh(""+tfBuscarNroEncuestador.getInteger()+",'"+tfBuscarZonaNro.getString()+"',"+tfBuscarNroEncuesta.getInteger() + ", '"+tfBuscarTexto.getString() + "',"+rbConFotos.isSelected() + "," +rbSinFotos.isSelected() + "," + rbTodos.isSelected());
        refreshGrilla();
        btnDesglosarCartelSel.setEnabled(false);
    }

    private void btnDesglosarCartelSel_actionPerformed(ActionEvent e) {
        inicializarCamposDesglose();
    }
    
    private void setEnabledButton(boolean _enabled){
        btnDesglosarCartelSel.setEnabled(_enabled);
        setEnabledSaveButton(_enabled);
    }
    
    
    private void seleccionarEmpresa(){
        if (cbTextoCartel.getCombo().getItemCount() > 0) {
            if (cbTextoCartel.getCombo().getValuesVector().size() > 0) {
                if (cbTextoCartel.getSelectedValueRef() != "" && cbTextoCartel.getSelectedValueRef() != "0") {
                    cbEmpresas.setSelectedID(cbTextoCartel.getSelectedValueRef());
                }
            }
        }
        cbEmpresas.getCombo().updateUI();
    }
    
    private boolean control(){
        boolean valor = true;
        errorForm = new ErrDesgloseCarteles();
        if (cbTextoCartel.getSelectedItem() != null) {
            if (cbTextoCartel.getSelectedItem().equals("")) {
                valor = false;
                errorForm.setError(errorForm.textoCartel);
            } else {
                if (tfSuperficie.getDouble() == 0) {
                    valor = false;
                    errorForm.setError(errorForm.superficie);
                }   
            }
        } else {
            valor = false;
            errorForm.setError(errorForm.textoCartel);
        }
        return valor;
    }
    
    private void setData(){
        relevamientoDesglosado.setIddetallerelevamiento(detalleRelevamientoPublicidad.getIddetallerelevamiento());
        relevamientoDesglosado.setTexto(cbTextoCartel.getSelectedItem().toString());
        relevamientoDesglosado.setAncho(tfAncho.getDouble());
        relevamientoDesglosado.setAlto(tfAlto.getDouble());
        relevamientoDesglosado.setFaz(Integer.parseInt(cbFaz.getSelectedItem().toString()));
        relevamientoDesglosado.setForma(cbFormas.getSelectedItem().toString());
        relevamientoDesglosado.setSuperficie(tfSuperficie.getDouble());
        relevamientoDesglosado.setIluminacion(cbIluminacion.getSelectedItem().toString());
        relevamientoDesglosado.setIdempresa(Integer.parseInt(cbEmpresas.getSelectedValue().toString()));
        relevamientoDesglosado.setX(detalleRelevamientoPublicidad.getX());
        relevamientoDesglosado.setY(detalleRelevamientoPublicidad.getY());
    }
    
    private void loadComboForma() {
        cbFormas.loadJCombo(LibSQL.exFunction("gea_salta.getallformasbyfilter", ""));
        cbFormas.getCombo().updateUI();
    }
    
    private void loadComboIluminacion() {
        cbIluminacion.loadJCombo(LibSQL.exFunction("gea_salta.getallcaracteristicasanunciobyfilter", ""));
        cbIluminacion.getCombo().updateUI();
    }
    
    private void loadComboFaz() {
        cbFaz.loadJCombo(LibSQL.exFunction("gea_salta.getallfazbyfilter", ""));
        cbFaz.getCombo().updateUI();
    }
    
    private void loadComboEmpresas() {
        cbEmpresas.loadJCombo(" SELECT companies.idcompany, companies.name, 0 FROM org.companies" +
                              " WHERE companies.estado<>'*' " 
                             +" ORDER BY companies.name");
        cbEmpresas.getCombo().updateUI();
    }
    
    private void loadComboTextoCarteles() {
        cbTextoCartel.loadJCombo(LibSQL.exFunction("gea_salta.getalltextoanunciobyfilter", ""));
        cbTextoCartel.addItem("");
        cbTextoCartel.getCombo().updateUI();
    }
    
    private void btnAddEmpresa_actionPerformed(ActionEvent e) {
        ProvidersMain formEmpresas = new ProvidersMain();
        ExtendedInternalFrame empresaContainer = new ExtendedInternalFrame("Empresas");
        empresaContainer.setCentralPanel(formEmpresas);
        empresaContainer.show();
    }
    
    private void loadCombos() {
        loadComboForma();
        loadComboIluminacion();
        loadComboFaz();
        loadComboEmpresas();
        loadComboTextoCarteles();
    }
    
    private void checkButtons(){
    //Mejorar este código
        //System.out.println("revisado = " + dataRow.elementAt(11).toString().toUpperCase());
        if (dataRow.elementAt(11).toString().toUpperCase().equals("SI")) {
            this.setEnabledSaveButton(false);
            btnDesglosarCartelSel.setEnabled(true);
            
        } else {
            this.setEnabledSaveButton(true);
            btnDesglosarCartelSel.setEnabled(false);
            setEditableComponent(true);
        }
    }
    
    private void setEditableComponent(boolean _enabled){
        tfAlto.setEditable(_enabled);
        tfAncho.setEditable(_enabled);
        //tfSuperficie.setEditable(_enabled);
    }
    
    public void saveData() {
        calcularSuperficie();
        if(control()){
            setData();
            if(relevamientoDesglosado.saveData() > -1 ){
                //Ver que hacer..
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
            }
        } else {
            errorForm.showMessage();
        }
    }
    
    private void calcularSuperficie(){
        tfSuperficie.setValue((tfAncho.getAmount() * tfAlto.getAmount()));
    }
    
    private void inicializarCamposDesglose(){
        //tfAlto.setValue("");
        //tfAncho.setValue("");
        //tfSuperficie.setValue("");
        btnDesglosarCartelSel.setEnabled(false);
        setEnabledSaveButton(true);
        setEditableComponent(true);
    }
    
    private void limpiarCampos(){
        tfAlto.setValue("0.00");
        tfAncho.setValue("0.00");
        tfSuperficie.setValue("0.00");
        tfNombreFoto.setValue("");
        cbEmpresas.getCombo().setSelectedItem(0);
        cbFaz.getCombo().setSelectedIndex(0);
        cbFormas.getCombo().setSelectedIndex(0);
        cbIluminacion.getCombo().setSelectedIndex(0);
        cbTextoCartel.getCombo().setSelectedIndex(0);
    }
}
