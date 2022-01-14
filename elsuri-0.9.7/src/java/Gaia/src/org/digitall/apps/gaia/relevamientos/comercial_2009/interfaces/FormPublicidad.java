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
 * FormPublicidad.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.ParseException;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassEncabezadoRelevamientoPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassRelevamientoPublicidad2009Encabezado;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses.ErrPublicidad;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class FormPublicidad extends GaiaPrimitiveForm {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicTabbedPane panelData = new BasicTabbedPane();
    private BasicPanel jpCabecera = new BasicPanel();
    private ESRIPoint point = null;
    private TFInput tfNroEncuestador = new TFInput(DataTypes.INTEGER, "Nº de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.INTEGER, "Nº de Encuesta", false);
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Fecha", false);
    private BasicLabel lblAnuncioAsociado = new BasicLabel();
    private BasicCheckBox chkAnuncioAsociado = new BasicCheckBox();
    
    /** 1. Ubicación del anuncio */
    private BasicPanel jpUbicacionGeografica = new BasicPanel();
    private TFInput tf1aFind = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cb1a = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf1a = new TFInput(DataTypes.STRING, "1a. BARRIO", false);
    private TFInput tf1bFind = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cb1b = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf1b = new TFInput(DataTypes.STRING, "1b. CALLE", false);
    private TFInput tf1c = new TFInput(DataTypes.STRING, "1c. CÓD. CALLE", false);
    private TFInput tf1d = new TFInput(DataTypes.STRING, "1d. NUMERACIÓN", false);
    private TFInput tf1e = new TFInput(DataTypes.STRING, "1e. PISO", false);
    private TFInput tf1f = new TFInput(DataTypes.STRING, "1f. Nº o LETRA", false);
    private TFInput tf1g = new TFInput(DataTypes.STRING, "1g. SECT/BLOQ", false);
    private TFInput tf1h = new TFInput(DataTypes.STRING, "1h. MANZANA", false);
    private TFInput tf1i = new TFInput(DataTypes.STRING, "1i. CASA", false);
    private TFInput tf1j = new TFInput(DataTypes.STRING, "1j. MEDIDOR", false);
    private GaiaCatastroInputPanel tf1k  = new GaiaCatastroInputPanel();
    private TFInput tfCoordenadas = new TFInput(DataTypes.STRING,"COORDENADAS",false);

    /** 2. Datos del responsable o responsable solidario */
    private BasicPanel jpDatosResponsable = new BasicPanel();
    private TFInput tf2a = new TFInput(DataTypes.STRING, "2a. NOMBRE/S DEL PROPIETARIO, SOCIO PRINCIPAL O CONSORCIONISTA", false);
    private BasicCheckBox chk2a = new BasicCheckBox("NS/NC");
    private TFInput tf2b = new TFInput(DataTypes.STRING, "2b. APELLIDO/S DEL PROPIETARIO, SOCIO PRINCIPAL O CONSORCIONISTA", false);
    private BasicCheckBox chk2b = new BasicCheckBox("NS/NC");
    private BasicLabel lbl2c = new BasicLabel("2c. Sexo:");
    private BasicRadioButton rb2c_a = new BasicRadioButton("MASCULINO");
    private BasicRadioButton rb2c_b = new BasicRadioButton("FEMENINO");
    private BasicRadioButton rb2c = new BasicRadioButton("NS/NC");
    private ButtonGroup grupo2c = new ButtonGroup();
    private TFInput tf2d = new TFInput(DataTypes.STRING, "2d. TIPO DE DOCUMENTO", false);
    private TFInput tf2e = new TFInput(DataTypes.STRING, "2e. Nº DE DOCUMENTO", false);
    private BasicCheckBox chk2de = new BasicCheckBox("NS/NC");
    private TFInput tf2f = new TFInput(DataTypes.STRING, "2f. TELÉFONO FIJO", false);
    private TFInput tf2g = new TFInput(DataTypes.STRING, "2g. FAX", false);
    private TFInput tf2h = new TFInput(DataTypes.STRING, "2h. CELULAR", false);
    private BasicCheckBox chk2fgh = new BasicCheckBox("NS/NC");
    private BasicLabel lbl2i = new BasicLabel("2i. OBSERVACIONES:");
    private JArea ta2i = new JArea();

    /** 3. Caracteristicas del anuncio */
    private BasicPanel jpCaracteristicasAnuncio = new BasicPanel();
    private BasicPanel jpContentCaracteristicasAnuncio = new BasicPanel();
    private BasicScrollPane jsScroll = new BasicScrollPane();
    private BasicLabel lbl4a = new BasicLabel("3a. FORMA BÁSICA DEL ANUNCIO:");
    private BasicCheckBox rb3a_a = new BasicCheckBox("CUADRADO");
    private BasicCheckBox rb3a_b = new BasicCheckBox("RECTANGULAR");
    private BasicCheckBox rb3a_c = new BasicCheckBox("TRIANGULAR");
    private BasicCheckBox rb3a_d = new BasicCheckBox("REDONDO");
    private BasicCheckBox rb3a_e = new BasicCheckBox("OTRO");
    //private ButtonGroup gruporb3a = new ButtonGroup();
    
    private BasicLabel lbl3b = new BasicLabel("3b. CARACTERÍSTICAS DEL ANUNCIO:");
    private BasicCheckBox rb3b_a = new BasicCheckBox("SIMPLE");
    private BasicCheckBox rb3b_b = new BasicCheckBox("ILUMINADO");
    private BasicCheckBox rb3b_c = new BasicCheckBox("LUMINOSO");
    private BasicCheckBox rb3b_d = new BasicCheckBox("PROYECTADO, DINÁMICO O ANIMADO");
    //private ButtonGroup gruporb3b = new ButtonGroup();
    private BasicLabel lbl3c = new BasicLabel("MEDIDAS APROXIMADAS EN METROS");
    private TFInput tf3c = new TFInput(DataTypes.DOUBLE, "3c. ALTO", false);
    private BasicLabel lbl3c_x = new BasicLabel("X");
    private TFInput tf3d = new TFInput(DataTypes.DOUBLE, "3d. ANCHO", false);
    private BasicLabel lbl3d_x = new BasicLabel("X");
    private BasicLabel lbl3e = new BasicLabel("3e. FAZ:");
    private BasicCheckBox rb3e_1 = new BasicCheckBox("1");
    private BasicCheckBox rb3e_2 = new BasicCheckBox("2");
    private BasicCheckBox rb3e_3 = new BasicCheckBox("3");
    private BasicCheckBox rb3e_4 = new BasicCheckBox("4");
    //private ButtonGroup gruporb3e = new ButtonGroup();
    private BasicLabel lbl2f_igual = new BasicLabel("=");
    private TFInput tf3f = new TFInput(DataTypes.DOUBLE, "3f. TOTAL", false);
    private BasicLabel lbl3g = new BasicLabel("3g. TEXTO DEL ANUNCIO:");
    private JArea ta3g = new JArea();
    private TFInput tf3h = new TFInput(DataTypes.STRING, "3h. CORRESPONDE FOTOGRAFÍA", false);
    private ImageLabel lbl3h_foto = new ImageLabel();
    private BufferedImage photoImage = null;
    private GaiaCatastroInputPanel tf3i_Catastro = new GaiaCatastroInputPanel();
    private TFInput tfCoordenadaCartel = new TFInput(DataTypes.STRING,"COORDENADAS",false);
    private int[] sizeColumnList = { 86 , 111 , 52 , 58 , 44 , 67 , 215 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Anuncios", dataRow){
	public void calculate() {
	    //controlBotones();
	}
    }
    ;
    private Vector headerList = new Vector();
    
    private SaveDataButton btnSaveData = new SaveDataButton();
    private SaveDataButton btnSaveDataF1 = new SaveDataButton();
    private SaveDataButton btnSaveDataF2 = new SaveDataButton();
    private AssignButton btnSaveDataF3 = new AssignButton(true);
    private SaveDataButton btnFinishF3 = new SaveDataButton();
    
    private ClassEncabezadoRelevamientoPublicidad encabezadoRelevamientoPublicidad;
    private ErrPublicidad errorForm;
    private ClassRelevamientoPublicidad2009Encabezado encabezadoRelevamiento;

    private BasicButton btnObservaciones = new BasicButton("Error");
    
    private File fotoFile;
    private final int fotoWidthLimit = 640;
    private final int fotoHeightLimit = 480;
    private final long fotoLengthLimit = 65536;

    private BasicButton btnVerFotos = new BasicButton("Ver fotos");
    //private BufferedImage photo = null;
    private boolean isEdit = false;//indica si el modo de carga de datos es de edición o inserción
    private int idDetalleRelevamiento = -1;
    private String datosEncabezadoOriginal = "";

    private BasicButton btnEliminarEncuesta = new BasicButton("Encuestas");

    public FormPublicidad() {
				try {
						jbInit();
				} catch (Exception e) {
						e.printStackTrace();
				}
    }
    
    public FormPublicidad(int _idDetalleRelevamiento) {
				isEdit = true;
				idDetalleRelevamiento = _idDetalleRelevamiento;
				try {
						jbInit();
				} catch (Exception e) {
						e.printStackTrace();
				}
    }

    private void jbInit() throws Exception {
	contentPanel.setBounds(new Rectangle(10, 10, 750, 500));
	contentPanel.setPreferredSize(new Dimension(710, 500));
	contentPanel.setSize(new Dimension(710, 637));
	setTitle("RELEVAMIENTO PUBLICIDAD Y PROPAGANDA");
	this.setSize(new Dimension(734, 635));
	this.setPreferredSize(new Dimension(734, 600));
	tfNroEncuestador.setBounds(new Rectangle(10, 5, 120, 35));
	tfZonaNro.setBounds(new Rectangle(160, 5, 110, 35));
	tfNroEncuesta.setBounds(new Rectangle(300, 5, 110, 35));
	tfFecha.setBounds(new Rectangle(440, 5, 110, 35));
	jpCabecera.setLayout(null);
        jpCabecera.add(btnEliminarEncuesta, null);
        jpCabecera.add(btnVerFotos, null);
        jpCabecera.add(btnObservaciones, null);
        jpCabecera.add(btnSaveData, null);
        jpCabecera.add(tfNroEncuestador, null);
        jpCabecera.add(tfZonaNro, null);
        jpCabecera.add(tfNroEncuesta, null);
        jpCabecera.add(tfFecha, null);
        jpCabecera.add(lblAnuncioAsociado, null);
	jpCabecera.add(chkAnuncioAsociado, null);
	jpCabecera.setPreferredSize(new Dimension(1, 70));
	tf1aFind.setBounds(new Rectangle(10, 35, 105, 35));
	cb1a.setBounds(new Rectangle(120, 35, 255, 35));
	tf1a.setBounds(new Rectangle(380, 35, 245, 35));
	cb1b.setBounds(new Rectangle(120, 75, 255, 35));
	tf1bFind.setBounds(new Rectangle(10, 75, 105, 35));
	tf1b.setBounds(new Rectangle(380, 75, 140, 35));
	tf1c.setBounds(new Rectangle(525, 75, 100, 35));
	tf1d.setBounds(new Rectangle(10, 115, 110, 35));
	tf1e.setBounds(new Rectangle(125, 115, 55, 35));
	tf1f.setBounds(new Rectangle(185, 115, 95, 35));
	tf1g.setBounds(new Rectangle(285, 115, 95, 35));
	tf1h.setBounds(new Rectangle(385, 115, 90, 35));
	tf1i.setBounds(new Rectangle(480, 115, 60, 35));
	tf1j.setBounds(new Rectangle(545, 115, 80, 35));
	tf1k.setBounds(new Rectangle(10, 155, 170, 35));
	jpUbicacionGeografica.setLayout(null);
	jpUbicacionGeografica.add(tfCoordenadas, null);
	jpUbicacionGeografica.add(btnSaveDataF1, null);
	jpUbicacionGeografica.add(tf1a, null);
	jpUbicacionGeografica.add(tf1aFind, null);
	jpUbicacionGeografica.add(tf1b, null);
	jpUbicacionGeografica.add(tf1bFind, null);
	jpUbicacionGeografica.add(tf1c, null);
	jpUbicacionGeografica.add(tf1d, null);
	jpUbicacionGeografica.add(tf1e, null);
	jpUbicacionGeografica.add(tf1f, null);
	jpUbicacionGeografica.add(tf1g, null);
	jpUbicacionGeografica.add(tf1h, null);
	jpUbicacionGeografica.add(tf1i, null);
	jpUbicacionGeografica.add(tf1j, null);
	jpUbicacionGeografica.add(tf1k, null);
	jpUbicacionGeografica.add(cb1a, null);
	jpUbicacionGeografica.add(cb1b, null);
	tf3c.setBounds(new Rectangle(10, 90, 75, 35));
	tf3d.setBounds(new Rectangle(120, 90, 75, 35));
	rb3a_e.setBounds(new Rectangle(615, 7, 70, 20));
	rb3a_e.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_actionPerformed(e);
		}
	}
	);
	
	tf3f.setBounds(new Rectangle(420, 90, 100, 35));
	tf3h.setBounds(new Rectangle(475, 240, 195, 35));
	ta3g.setBounds(new Rectangle(10, 155, 415, 120));
	rb3a_a.setBounds(new Rectangle(210, 145, 135, 20));
	/*rb3a_a.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_a_actionPerformed(e);
		}
	}
	);*/
	rb3a_a.setFont(new Font("Lucida Sans", 0, 9));
	rb3a_a.setBounds(new Rectangle(215, 7, 85, 20));
	rb3a_b.setBounds(new Rectangle(325, 145, 135, 20));
	rb3a_b.setFont(new Font("Lucida Sans", 0, 9));
	/*rb3a_b.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_b_actionPerformed(e);
		}
	}
	);*/
	rb3a_b.setBounds(new Rectangle(310, 7, 100, 20));
	rb3a_c.setBounds(new Rectangle(435, 145, 110, 20));
	rb3a_c.setFont(new Font("Lucida Sans", 0, 9));
	rb3a_c.setBounds(new Rectangle(415, 7, 95, 20));
	/*rb3a_c.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_c_actionPerformed(e);
		}
	}
	);*/
	rb3b_a.setBounds(new Rectangle(215, 260, 75, 20));
	rb3b_a.setFont(new Font("Lucida Sans", 0, 9));
	rb3b_a.setBounds(new Rectangle(235, 37, 65, 20));
	rb3b_b.setBounds(new Rectangle(295, 260, 100, 20));
	rb3b_b.setFont(new Font("Lucida Sans", 0, 9));
	rb3b_b.setBounds(new Rectangle(310, 37, 90, 20));
	rb3b_c.setBounds(new Rectangle(395, 260, 160, 20));
	rb3b_c.setFont(new Font("Lucida Sans", 0, 9));
	rb3b_c.setBounds(new Rectangle(415, 37, 85, 20));
	rb3b_d.setBounds(new Rectangle(515, 37, 165, 20));
	/*gruporb3b.add(rb3b_d);
	gruporb3b.add(rb3b_a);
	gruporb3b.add(rb3b_b);
	gruporb3b.add(rb3b_c);*/
	
	/*gruporb3e.add(rb3e_1);
	gruporb3e.add(rb3e_2);
	gruporb3e.add(rb3e_3);
	gruporb3e.add(rb3e_4);*/
	//rb3e_1.setSelected(true);
	lbl3b.setBounds(new Rectangle(10, 40, 220, 15));
	lbl4a.setBounds(new Rectangle(10, 10, 200, 15));
	lbl3g.setBounds(new Rectangle(10, 140, 165, 15));
	jpCaracteristicasAnuncio.setLayout(null);
	
	jpCaracteristicasAnuncio.setLayout(null);
	jpContentCaracteristicasAnuncio.setSize(new Dimension(705, 510));
	jpContentCaracteristicasAnuncio.setPreferredSize(new Dimension(705, 510));
	jpContentCaracteristicasAnuncio.setLayout(null);
	jsScroll.getViewport().add(jpContentCaracteristicasAnuncio, BorderLayout.CENTER);
	jpCaracteristicasAnuncio.add(jsScroll, BorderLayout.CENTER);
	//jsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	jsScroll.setBounds(new Rectangle(0, 0, 705, 530));
	jsScroll.setSize(new Dimension(705, 530));
	jpContentCaracteristicasAnuncio.add(tfCoordenadaCartel, null);
	jpContentCaracteristicasAnuncio.add(tf3i_Catastro, null);
	jpContentCaracteristicasAnuncio.add(btnFinishF3, null);
	jpContentCaracteristicasAnuncio.add(lbl3h_foto, null);
	jpContentCaracteristicasAnuncio.add(listPanel, null);
	jpContentCaracteristicasAnuncio.add(lbl2f_igual, null);
	jpContentCaracteristicasAnuncio.add(lbl3e, null);
	jpContentCaracteristicasAnuncio.add(rb3e_4, null);
	jpContentCaracteristicasAnuncio.add(rb3e_3, null);
	jpContentCaracteristicasAnuncio.add(rb3e_2, null);
	jpContentCaracteristicasAnuncio.add(rb3e_1, null);
	jpContentCaracteristicasAnuncio.add(lbl3d_x, null);
	jpContentCaracteristicasAnuncio.add(lbl3c_x, null);
	jpContentCaracteristicasAnuncio.add(lbl3c, null);
	jpContentCaracteristicasAnuncio.add(rb3a_d, null);
	jpContentCaracteristicasAnuncio.add(btnSaveDataF3, null);
	jpContentCaracteristicasAnuncio.add(lbl3b, null);
	jpContentCaracteristicasAnuncio.add(lbl4a, null);

	jpContentCaracteristicasAnuncio.add(lbl3g, null);
	jpContentCaracteristicasAnuncio.add(rb3a_a, null);
	jpContentCaracteristicasAnuncio.add(rb3a_b, null);
	jpContentCaracteristicasAnuncio.add(rb3a_c, null);
	jpContentCaracteristicasAnuncio.add(tf3c, null);
	jpContentCaracteristicasAnuncio.add(tf3d, null);
	jpContentCaracteristicasAnuncio.add(rb3a_e, null);
	jpContentCaracteristicasAnuncio.add(tf3f, null);
	jpContentCaracteristicasAnuncio.add(tf3h, null);

	jpContentCaracteristicasAnuncio.add(ta3g, null);
	jpContentCaracteristicasAnuncio.add(rb3b_a, null);
	jpContentCaracteristicasAnuncio.add(rb3b_b, null);
	jpContentCaracteristicasAnuncio.add(rb3b_c, null);
	jpContentCaracteristicasAnuncio.add(rb3b_d, null);
	tf2a.setBounds(new Rectangle(10, 10, 505, 35));
	chk2a.setBounds(new Rectangle(555, 25, 70, 20));
	chk2a.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3a_actionPerformed(e);
		}
	}
	);
	tf2b.setBounds(new Rectangle(10, 50, 505, 35));
	chk2b.setBounds(new Rectangle(555, 65, 70, 20));
	chk2b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3b_actionPerformed(e);
		}
	}
	);
	lbl2c.setBounds(new Rectangle(10, 90, 60, 35));
	
	rb2c_b.setBounds(new Rectangle(180, 90, 90, 35));
	rb2c_a.setBounds(new Rectangle(75, 90, 100, 35));
	rb2c_b.setBounds(new Rectangle(180, 90, 100, 35));
	rb2c.setBounds(new Rectangle(555, 105, 70, 20));
	grupo2c.add(rb2c);
	grupo2c.add(rb2c_a);
	grupo2c.add(rb2c_b);
	rb2c_a.setSelected(true);
	tf2d.setBounds(new Rectangle(10, 130, 160, 35));
	tf2e.setBounds(new Rectangle(175, 130, 165, 35));
	chk2de.setBounds(new Rectangle(555, 145, 70, 20));
	chk2de.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk3de_actionPerformed(e);
		}
	
	}
	);
	tf2f.setBounds(new Rectangle(10, 170, 115, 35));
	tf2g.setBounds(new Rectangle(130, 170, 115, 35));
	tf2h.setBounds(new Rectangle(250, 170, 140, 35));
	chk2fgh.setBounds(new Rectangle(555, 185, 70, 20));
	chk2fgh.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3fgh_actionPerformed(e);
		}
	}
	);
	ta2i.setBounds(new Rectangle(10, 235, 605, 105));
	jpDatosResponsable.setLayout(null);
	jpDatosResponsable.add(lbl2i, null);
	jpDatosResponsable.add(btnSaveDataF2, null);
	jpDatosResponsable.add(tf2a, null);
	jpDatosResponsable.add(chk2a, null);
	jpDatosResponsable.add(tf2b, null);
	jpDatosResponsable.add(chk2b, null);
	jpDatosResponsable.add(lbl2c, null);
	jpDatosResponsable.add(rb2c_a, null);
	jpDatosResponsable.add(rb2c_b, null);
	jpDatosResponsable.add(rb2c, null);
	jpDatosResponsable.add(tf2d, null);
	jpDatosResponsable.add(tf2e, null);
	jpDatosResponsable.add(chk2de, null);
	jpDatosResponsable.add(tf2f, null);
	jpDatosResponsable.add(tf2g, null);
	jpDatosResponsable.add(tf2h, null);

	jpDatosResponsable.add(chk2fgh, null);
	jpDatosResponsable.add(ta2i, null);
	panelData.addTab("1. UBICACIÓN DEL ANUNCIO", jpUbicacionGeografica);
	panelData.addTab("2. DATOS DEL RESPONSABLE O RESPONSABLE SOLIDARIO", jpDatosResponsable);
	panelData.addTab("3. CARACTERÍSTICAS DEL ANUNCIO", jpCaracteristicasAnuncio);
	btnSaveDataF1.setToolTipText("Grabar");
	btnSaveDataF1.setBounds(new Rectangle(665, 320, 30, 25));
	btnSaveDataF1.setSize(new Dimension(30, 25));
	btnSaveDataF1.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF1_actionPerformed(e);
		}
	    }
	);
	btnSaveData.setToolTipText("Grabar");
	btnSaveData.setBounds(new Rectangle(560, 15, 30, 25));
	btnSaveData.setSize(new Dimension(30, 25));
	btnSaveData.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveData_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF3.setToolTipText("Grabar");
	btnSaveDataF3.setBounds(new Rectangle(640, 300, 30, 25));
	btnSaveDataF3.setSize(new Dimension(30, 25));
	btnSaveDataF3.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF3_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF2.setToolTipText("Grabar");
	btnSaveDataF2.setBounds(new Rectangle(665, 320, 30, 25));
	btnSaveDataF2.setSize(new Dimension(30, 25));
	btnSaveDataF2.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF2_actionPerformed(e);
		}
	    }
	);
	btnFinishF3.setBounds(new Rectangle(650, 485, 30, 25));
	btnFinishF3.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFinishF3_actionPerformed(e);
		}
	    }
	);

	btnObservaciones.setBounds(new Rectangle(640, 5, 65, 20));
	btnObservaciones.setMnemonic('e');
	btnObservaciones.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnObservaciones_actionPerformed(e);
		}
	    }
	);
	btnVerFotos.setBounds(new Rectangle(555, 45, 77, 22));
	btnVerFotos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVerFotos_actionPerformed(e);
		}
	    }
	);
	tf3i_Catastro.setBounds(new Rectangle(10, 285, 135, 35));
	tfCoordenadaCartel.setBounds(new Rectangle(215, 285, 210, 35));
	lbl2i.setBounds(new Rectangle(10, 215, 135, 25));
	listPanel.setBounds(new Rectangle(5, 330, 680, 150));
	lbl3h_foto.setBounds(new Rectangle(520, 135, 105, 105));
	lbl3h_foto.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	lbl3h_foto.setSize(new Dimension(105, 105));
	lbl2f_igual.setBounds(new Rectangle(395, 105, 10, 15));
	lbl3e.setBounds(new Rectangle(225, 105, 60, 15));
	rb3e_1.setBounds(new Rectangle(285, 90, 45, 20));
	rb3e_2.setBounds(new Rectangle(345, 90, 45, 20));
	rb3e_3.setBounds(new Rectangle(285, 115, 45, 20));
	rb3e_4.setBounds(new Rectangle(345, 115, 45, 20));
	lbl3c_x.setBounds(new Rectangle(210, 105, 10, 15));
	lbl3d_x.setBounds(new Rectangle(100, 105, 10, 15));
	lbl3c.setBounds(new Rectangle(10, 65, 230, 15));
	rb3a_d.setBounds(new Rectangle(515, 7, 95, 20));
	lblAnuncioAsociado.setText("EL ANUNCIO NO SE ENCUENTRA ASOCIADO AL LOCAL COMERCIAL");
	lblAnuncioAsociado.setBounds(new Rectangle(10, 45, 390, 20));
	chkAnuncioAsociado.setBounds(new Rectangle(410, 45, 25, 20));
	tfCoordenadas.setBounds(new Rectangle(10, 195, 255, 35));
	panelData.setBounds(new Rectangle(0, 70, 710, 410));
	panelData.setSize(new Dimension(710, 400));
	panelData.setPreferredSize(new Dimension(710, 400));
	contentPanel.setLayout(new BorderLayout());
	contentPanel.add(jpCabecera, BorderLayout.NORTH);
	contentPanel.add(panelData, BorderLayout.CENTER);
	this.setCentralPanel(contentPanel);
	tf1aFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboBarrios();
			    
		    }
		});
	tf1bFind.getTextField().addKeyListener(new KeyAdapter() {

		public void keyReleased(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER)
			loadComboCalles();

		}
	    }
	);
	cb1a.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb1a_itemStateChanged(e);
		}
	    }
	);
	cb1b.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb1b_itemStateChanged(e);
		}
	    }
	);
	
	tfNroEncuestador.getTextField().addFocusListener(new FocusListener() {

			     public void focusGained(FocusEvent focusEvent) {
				
			     }

			     public void focusLost(FocusEvent focusEvent) {
				 try {
				     tfNroEncuestador.getTextField().commitEdit();
				 } catch (ParseException e) {
				     // TODO
				 }
			     }

			 }
	);

	tfZonaNro.getTextField().addFocusListener(new FocusListener() {

		public void focusGained(FocusEvent focusEvent) {

		}

		public void focusLost(FocusEvent focusEvent) {
		    try {
			tfZonaNro.getTextField().commitEdit();
		    } catch (ParseException e) {
			// TODO
		    }
		}

	    }
	);
				      
	tfNroEncuesta.getTextField().addFocusListener(new FocusListener() {

			     public void focusGained(FocusEvent focusEvent) {
				
			     }

			     public void focusLost(FocusEvent focusEvent) {
			         try {
			             tfNroEncuesta.getTextField().commitEdit();
			         } catch (ParseException e) {
			             // TODO
			         }
			     }

			 }
	);

	lbl3h_foto.addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
			loadImage();
		    }
		}

	    }
	);

	/*gruporb3a.add(rb3a_a);
	gruporb3a.add(rb3a_b);
	gruporb3a.add(rb3a_c);
	gruporb3a.add(rb3a_d);
	gruporb3a.add(rb3a_e);	*/
	//rb3a_e.setSelected(true);
	tfCoordenadas.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfCoordenadas.getTextField().setForeground(Color.red);
	tfCoordenadas.setEditable(false);
	tfCoordenadas.setValue("0 ; 0");
	tfCoordenadaCartel.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfCoordenadaCartel.getTextField().setForeground(Color.red);
	tfCoordenadaCartel.setEditable(false);
	tfCoordenadaCartel.setValue("0 ; 0");
	loadCombos();
	initForm();
	//rb3b_a.setSelected(true);
	setRbtn2d(0);
	tfFecha.setEditable(false);
	tfFecha.setOpaque(true);
	tfFecha.setOpaque(true);
	setEnabledSaveButton(false);
	setEnabledDeleteButton(false);
	setEnabledCloseButton(false);
	setHeaderList();
	tf1k.setEditable(true);
	tf3i_Catastro.setEditable(true);
	loadDatosFormularios(idDetalleRelevamiento);
	btnVerFotos.setVisible(false);
	btnEliminarEncuesta.setBounds(new Rectangle(630, 25, 77, 22));
	btnEliminarEncuesta.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnEliminarEncuesta_actionPerformed(e);
		}
	    }
	);

        getPrintButton().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
                    clickPrint(e);
                }
            }
        });
    }

    private void setRbtn2d(int _rbtn) {
	switch (_rbtn)  {
	    case 0: {
		    rb3a_e.setSelected(false);
		    rb3a_a.setSelected(false);
		    rb3a_b.setSelected(false);
		    rb3a_c.setSelected(false);
		}
		break;
	
	    case 1: {
	            rb3a_e.setSelected(false);
	            rb3a_a.setSelected(true);
	            rb3a_b.setSelected(false);
	            rb3a_c.setSelected(false);
	        }
	        break;
	    case 2: {
	            rb3a_e.setSelected(false);
	            rb3a_a.setSelected(false);
	            rb3a_b.setSelected(true);
	            rb3a_c.setSelected(false);
	        }
	        break;
	    case 3: {
	            rb3a_e.setSelected(false);
	            rb3a_a.setSelected(false);
	            rb3a_b.setSelected(false);
	            rb3a_c.setSelected(true);
	        }
	        break;
	}
    }

    private void loadCombos(){
	loadComboBarrios();
	loadComboCalles();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Forma");
	headerList.addElement("Característica");
	headerList.addElement("Alto");
	headerList.addElement("Ancho");
	headerList.addElement("Faz");
	headerList.addElement("Total");
	headerList.addElement("Texto del Anuncio");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						    if(isEdit){
							loadCartel();
						    }
					       }
					   }

				       }
	);
	listPanel.setParams("gea_salta.getAllDetalleRelevamientoPublicidad", "-1", headerList);
    }

    public void refresh() {
	if(!isEdit){
	    listPanel.refresh(""+ encabezadoRelevamientoPublicidad.getIdRelevamientoPublicidad());    
	}else{
	    listPanel.refresh(""+ encabezadoRelevamiento.getIdRelevamientoPublicidad());
	}
	
    }


    private void clearData() {

    }
    /*
    public void delete() {
	    if (comercio.delete()) {
		//getLayer().removeGeometry(point);
		clearData();
	    }
    }

    public void saveData() {
	boolean _isnew = comercio.getIdComercio() == -1;
	comercio.setIdCategoriaComercial(Integer.parseInt(cbCatComerciales.getSelectedValue().toString()));
        comercio.setCatastro(Integer.parseInt(tfCatastro.getValue().toString()));
        comercio.setNombre(tfNombre.getValue().toString());
        comercio.setActividad(tfActividad.getValue().toString());
        comercio.setIdRubroComercial(Integer.parseInt(cbRubrosComerciales.getSelectedValue().toString()));
        comercio.setTelefono1(tfTelefono1.getValue().toString());
        comercio.setTelefono2(tfTelefono2.getValue().toString());
        comercio.setFax(tfFax.getValue().toString());
	if (comercio.saveData() > 0) {
	    point.setLabel(comercio.getNombre());
	    point.setIdPoint(comercio.getIdComercio());
	    point.setSymbol(comercio.getIdCategoriaComercial());
	    clearData();
	    if (_isnew) {
		//getLayer().addGeometry(point);
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }


    public void setComercioObject(ClassComercio _comercio) {
	comercio = _comercio;
	cbCatComerciales.setSelectedID(comercio.getIdCategoriaComercial());
        cbRubrosComerciales.setSelectedID(comercio.getIdRubroComercial());
	loadData();
    }

    private void loadData() {
	cbCatComerciales.setSelectedID(comercio.getIdCategoriaComercial());
        cbRubrosComerciales.setSelectedID(comercio.getIdRubroComercial());
        tfNombre.setValue(comercio.getNombre());
        tfCatastro.setValue(comercio.getCatastro());
        tfActividad.setValue(comercio.getActividad());
        tfTelefono1.setValue(comercio.getTelefono1());
        tfTelefono2.setValue(comercio.getTelefono2());
        tfFax.setValue(comercio.getFax());
        if (comercio.getIdComercio() != -1)  {
            //setEnabledDeleteButton(true);
        } else {
            //setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    comercio = new GaiaClassComercios();
	    point = (ESRIPoint)_contentObject;
	    comercio.setIdComercio(point.getIdPoint());
	    comercio.retrieveData();
	    comercio.setX(point.getX());
	    comercio.setY(point.getY());
	    setComercioObject(comercio);
	    tfCatastro.fetchCatastro(point);
	}
    }

    @Override
    public Object getContentObject() {
	return comercio;
    }

    @Override
    public void printReport() {
        BasicReport report = new BasicReport(FormComercios.class.getResource("xml/ComerciosReport.xml"));
        String param = "0,0";
        report.addTableModel("gis_oran.xmlGetComercios", param);
        report.doReport();
    }*/
    
    
    private void loadPunto(){
	//tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(adminyServ.getX()) +"; "+ (new DecimalFormat("0.0000")).format(adminyServ.getY()) +")");
    }

    private void loadComboBarrios() {
	String param = "'" + tf1aFind.getString() + "'";
	cb1a.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb1a.getCombo().updateUI();
    }
    
    
    private void loadComboCalles(){
	String param = "'" + tf1bFind.getString() + "'";
	cb1b.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb1b.getCombo().updateUI();
    }

    private void cb1a_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf1a.setValue(cb1a.getSelectedItem().toString());
	}
    }
    
    private void cb1b_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf1b.setValue(cb1b.getSelectedItem().toString());
	}
    }

    
    private void initForm() {
	encabezadoRelevamientoPublicidad = new ClassEncabezadoRelevamientoPublicidad();
	setTabs(0);
    }

    private void clearForms(){
	/* Encabezado */
	tfNroEncuestador.setValue(""); tfZonaNro.setValue("");
	tfNroEncuesta.setValue(""); //tfFecha.setValue("");
	chkAnuncioAsociado.setEnabled(false);
	/* Seccion 1 */
	tf1aFind.setValue("");	tf1bFind.setValue("");
	tf1c.setValue("");	tf1d.setValue(""); tf1e.setValue("");
	tf1f.setValue("");	tf1g.setValue("");
	tf1h.setValue("");	tf1i.setValue("");
	tf1j.setValue(""); 	tf1k.setValue("");
	tfCoordenadas.setValue("0 ; 0");
	/* Seccion 2 */
	tf2a.setValue("");      chk2a.setSelected(false);
	tf2b.setValue("");      chk2b.setSelected(false);
	rb2c_a.setSelected(true); rb2c_b.setSelected(false); rb2c.setSelected(false);
	tf2d.setValue(""); tf2e.setValue(""); chk2de.setSelected(false);
	tf2f.setValue(""); tf2g.setValue(""); tf2h.setValue(""); chk2fgh.setSelected(false); 
	ta2i.setText("");	
	/* Seccion 3 */
	tf3c.setValue("");      tf3d.setValue(""); 
	tf1d.setValue("");      rb3a_e.setSelected(false);
	rb3a_a.setSelected(false); rb3a_b.setSelected(false); rb3a_c.setSelected(false); 
	tf3f.setValue("");      tf3h.setValue(""); 
	rb3b_d.setSelected(false); rb3b_a.setSelected(false); rb3b_b.setSelected(false); rb3b_c.setSelected(false);
	ta3g.setText("");
        tf3i_Catastro.setValue("");  tfCoordenadaCartel.setValue("0 ; 0");
	photoImage = null;
	lbl3h_foto.setImage(null);
    }
    
    private void setTabs(int _tab) {
	switch (_tab)  {
	    case 0: {/* dehabilitar todas las pestañas */
		    clearForms();
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    setEnabledForms(0);
		    panelData.setSelectedIndex(0);
		    tf3i_Catastro.getTextField().requestFocus(false);
		    tfNroEncuesta.getTextField().requestFocus();
		}
		break;
	    case 1: {/* dehabilitar todas las pestañas y dejar habilitada la pestaña 1*/
		    panelData.setEnabledAt(0,true);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
	            setEnabledForms(1);
	            panelData.setSelectedIndex(0);
	        }
	        break;
	    case 2: {/* dehabilitar todas las pestañas y dejar habilitada la pestaña 2 */
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,true);
		    panelData.setEnabledAt(2,false);
	            setEnabledForms(2);
		    panelData.setSelectedIndex(1);
	        }
	        break;
	    case 3: {/* dehabilitar todas las pestañas  y dejar habilitada la pestaña 3*/
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,true);
	            setEnabledForms(3);
	            panelData.setSelectedIndex(2);
	        }
	        break;
	    case 4: { /* habilitar todas las pestañas para modificación de los datos*/
		 panelData.setEnabledAt(0,true);
		 panelData.setEnabledAt(1,true);
		 panelData.setEnabledAt(2,true);
		 setEnabledForms(4);
		 //panelData.setSelectedIndex(2);
		break;
	    }
	}
    }

    private void setEnabledForms(int _form) {
	switch (_form)  {
	    case 0: {
		    /* ENCABEZADO */
		    tfNroEncuesta.setEnabled(true);
		    tfZonaNro.setEnabled(true);
		    tfNroEncuestador.setEnabled(true);
		    tfFecha.setEnabled(true); 
		    chkAnuncioAsociado.setEnabled(true);
		    btnSaveData.setEnabled(true);
		    tfFecha.setEditable(false);
		    chkAnuncioAsociado.setSelected(false);
		    
		    /* SECCION 1 */
		    tf1aFind.setEnabled(false); cb1a.setEnabled(false);
		    tf1a.setEnabled(false); tf1bFind.setEnabled(false);
		    cb1b.setEnabled(false); tf1b.setEnabled(false); 
		    tf1c.setEnabled(false); tf1d.setEnabled(false); 
		    tf1e.setEnabled(false); tf1f.setEnabled(false); 
		    tf1g.setEnabled(false); tf1h.setEnabled(false);
		    tf1i.setEnabled(false); tf1j.setEnabled(false);
		    //tf1k.setEnabled(false); 
		    btnSaveDataF1.setEnabled(false);
		}
		break;
	    case 1: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEnabled(false);
	            tfZonaNro.setEnabled(false);
	            tfNroEncuestador.setEnabled(false);
	            tfFecha.setEnabled(false);
	            chkAnuncioAsociado.setEnabled(false);
	            btnSaveData.setEnabled(false);
	            /* SECCION 1 */
	            tf1aFind.setEnabled(true); cb1a.setEnabled(true);
	            tf1a.setEnabled(true); tf1bFind.setEnabled(true);
	            cb1b.setEnabled(true); tf1b.setEnabled(true); 
		    tf1c.setEnabled(true); tf1d.setEnabled(true);
		    tf1e.setEnabled(true); tf1f.setEnabled(true);
		    tf1g.setEnabled(true); tf1h.setEnabled(true);
		    tf1i.setEnabled(true); tf1j.setEnabled(true);
		    tf1k.setEnabled(true); 
		    btnSaveDataF1.setEnabled(true);
	        }
	        break;
	    case 2: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEnabled(false);
	            tfZonaNro.setEnabled(false);
	            tfNroEncuestador.setEnabled(false);
	            tfFecha.setEnabled(false);
	            chkAnuncioAsociado.setEnabled(false);
	            btnSaveData.setEnabled(false);
	            btnSaveDataF2.setEnabled(true);
	        }
	        break;
	    case 3: {
	            /* ENCABEZADO */
	             tfNroEncuesta.setEnabled(false);
	             tfZonaNro.setEnabled(false);
	             tfNroEncuestador.setEnabled(false);
	             tfFecha.setEnabled(false);
	             chkAnuncioAsociado.setEnabled(false);
	            btnSaveData.setEnabled(false);
	            btnSaveDataF3.setEnabled(true);
		    btnFinishF3.setEnabled(true);
	        }
	        break;
	    case 4: {
	        /* habilitar para modificación de datos */
		    /* ENCABEZADO */
		    tfNroEncuesta.setEnabled(true);
		    tfZonaNro.setEnabled(true);
		    tfNroEncuestador.setEnabled(true);
		    tfFecha.setEnabled(true); 
		    chkAnuncioAsociado.setEnabled(true);
		    btnSaveData.setEnabled(true);
		    tfFecha.setEditable(true);
		    chkAnuncioAsociado.setSelected(true);
		    /* SECCION 1 */
		    tf1aFind.setEnabled(true); cb1a.setEnabled(true);
		    tf1a.setEnabled(true); tf1bFind.setEnabled(true);
		    cb1b.setEnabled(true); tf1b.setEnabled(true); 
		    tf1c.setEnabled(true); tf1d.setEnabled(true); 
		    tf1e.setEnabled(true); tf1f.setEnabled(true); 
		    tf1g.setEnabled(true); tf1h.setEnabled(true);
		    tf1i.setEnabled(true); tf1j.setEnabled(true);
		    //tf1k.setEnabled(false); 
		    //btnSaveDataF1.setEnabled(true);
		     btnSaveData.setEnabled(false);
		     btnSaveDataF1.setEnabled(false);
		     btnSaveDataF2.setEnabled(false);
		     //btnSaveDataF3.setEnabled(false);
		     btnFinishF3.setEnabled(false);
		break;
	    }
	}
	
    }
    
    /**209-12-17(moraless)*/
    public void loadDatosFormularios(int _iddetalleencuesta){
	int idencuesta = -1;
	//if(_iddetalleencuesta !=-1){
	if(isEdit){
	    setEnabledSaveButton(true);
	    setEnabledDeleteButton(true);
	    setEnabledCloseButton(true);
	    idencuesta = LibSQL.getInt("gea_salta.getidrelevamientopublicidad", _iddetalleencuesta);
	    point = new ESRIPoint(0,0);
	    setTabs(4);
	    encabezadoRelevamiento = new ClassRelevamientoPublicidad2009Encabezado();
	    encabezadoRelevamiento.setIdRelevamientoPublicidad(idencuesta);
	    encabezadoRelevamiento.retrieveData();
	    /* Encabezado */
	    tfNroEncuestador.setValue(encabezadoRelevamiento.getNroEncuestador());
	    tfNroEncuestador.setEnabled(true);
	    tfZonaNro.setValue(encabezadoRelevamiento.getNroZona());
	    tfZonaNro.setEnabled(true);
	    tfNroEncuesta.setValue(encabezadoRelevamiento.getNroEncuesta());
	    tfNroEncuesta.setEnabled(true);
	    tfFecha.setValue(Proced.setFormatDate(encabezadoRelevamiento.getFecha(),true));
	    tfFecha.setEditable(false);
	    //chkAnuncioAsociado.setEnabled(true);
			//System.out.println("anuncio asociado = "+encabezadoRelevamiento.isEncuestaAsociada());
	    if(encabezadoRelevamiento.isEncuestaAsociada()){
	        chkAnuncioAsociado.setSelected(true);
	    }else{
	        chkAnuncioAsociado.setSelected(false);
	    }
	    /* Seccion 1 */
	    tf1a.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1a());
	    tf1b.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1b());
	    tf1c.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1c());
	    tf1d.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1d());
	    tf1e.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1e());
	    tf1f.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1f());
	    tf1g.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1g());
	    tf1h.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1h());
	    tf1i.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1i());
	    tf1j.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getF1j());
	    tf1k.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3i());
	    tf3i_Catastro.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3i());
	    //System.out.println("valor = "+encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3i());
	    point.setLocation(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getX(),encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion1().getY());
	    tfCoordenadas.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	    tfCoordenadaCartel.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	    //tfCoordenadas.setValue("0 ; 0");
	    /* Seccion 2 */
	    tf2a.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2a());
	    chk2a.setSelected(false);
	    tf2b.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2b());
	    chk2b.setSelected(false);
	    
	    if(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2c().equals("MASCULINO")){
	        rb2c_a.setSelected(true);
	    }else{
	        if(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2c().equals("FEMENINO")){
	            rb2c_b.setSelected(true);
	        }else{
	            rb2c.setSelected(true); rb2c_a.setSelected(false); rb2c_b.setSelected(false);
	        }
	    }
	    
	    tf2d.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2d());
	    tf2e.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2e());
	    chk2de.setSelected(false);
	    tf2f.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2f());
	    tf2g.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2g());
	    tf2h.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2h());
	    chk2fgh.setSelected(false); 
	    ta2i.setText(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2i());
	    refresh();
	    datosEncabezadoOriginal = ""+tfNroEncuestador.getValue()+tfZonaNro.getValue()+tfNroEncuesta.getValue();
	    //encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().setIddetallerelevamiento(-1);
	    encabezadoRelevamiento.inicializarCartel();
	}
    }
    
    /**209-12-10(moraless)*/
    private void limpiarCamposCartel(){
	tf3c.setValue("");      tf3d.setValue(""); 
	tf1d.setValue("");      rb3a_e.setSelected(false);
	rb3a_a.setSelected(false); rb3a_b.setSelected(false); rb3a_c.setSelected(false); 
	tf3f.setValue("");      tf3h.setValue(""); 
	rb3b_d.setSelected(false); rb3b_a.setSelected(false); rb3b_b.setSelected(false); rb3b_c.setSelected(false);
	rb3e_1.setSelected(false); rb3e_2.setSelected(false); rb3e_3.setSelected(false); rb3e_4.setSelected(false);
	ta3g.setText("");
	tf3i_Catastro.setValue("");  tfCoordenadaCartel.setValue("0 ; 0");
	photoImage = null;
	lbl3h_foto.setImage(null);
    }
    
    /**209-12-10(moraless)*/
    private void loadCartel(){
	limpiarCamposCartel();
	int iddetalle = Integer.parseInt(dataRow.elementAt(0).toString());
	encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().setIddetallerelevamiento(iddetalle);
	encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().retrieveData();
	/* Seccion 3 */
	
	if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3a().equals("CUADRADO")){
	    rb3a_a.setSelected(true);
	}else{
	    if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3a().equals("RECTANGULAR")){
		rb3a_b.setSelected(true);
	    }else{
		if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3a().equals("TRIANGULAR")){
		    rb3a_c.setSelected(true);
		}else{
		    if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3a().equals("REDONDO")){
			rb3a_d.setSelected(true);
		    }else{
			if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3a().equals("OTRO")){
			    rb3a_e.setSelected(true);
			}else{
			    rb3a_a.setSelected(false); rb3a_b.setSelected(false); rb3a_c.setSelected(false); rb3a_d.setSelected(false); rb3a_e.setSelected(false);
			}
		    }
		}
	    }
	}
	
	if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3b().equals("SIMPLE")){
	    rb3b_a.setSelected(true);
	}else{
	    if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3b().equals("ILUMINADO")){
		rb3b_b.setSelected(true);
	    }else{
		if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3b().equals("LUMINOSO")){
		    rb3b_c.setSelected(true);
		}else{
		    if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3b().equals("PROYECTADO, DINÁMICO O ANIMADO")){
			rb3b_d.setSelected(true);
		    }else{
			rb3b_a.setSelected(false); rb3b_b.setSelected(false); rb3b_c.setSelected(false); rb3b_d.setSelected(false); 
		    }
		}
	    }
	}
	
	tf3c.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3c());
	tf3d.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3d());
	//tf1d.setValue(encabezadoRelevamiento.getClassRelevamientoPublicidad2009Seccion2().getF2e());
	
	 if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3e()== 1){
	     rb3e_1.setSelected(true);
	 }else{
	     if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3e()== 2){
		 rb3e_2.setSelected(true);
	     }else{
		 if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3e()== 3){
		     rb3e_3.setSelected(true);
		 }else{
		     if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3e()== 4){
			 rb3e_4.setSelected(true);
		     }else{
			 rb3e_1.setSelected(false); rb3e_2.setSelected(false); rb3e_3.setSelected(false); rb3e_4.setSelected(false);
		     }
		 }
	     }
	 }
	
	tf3f.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3f());
	ta3g.setText(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3g());
	tf3h.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3h());
	tf3i_Catastro.setValue(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getF3i());
	point.setLocation(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getX(),encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getY());
	tfCoordenadaCartel.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	photoImage = LibIMG.getImage("gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getIddetallerelevamiento());
	lbl3h_foto.setImage(photoImage);
	//tfCoordenadaCartel.setValue("0 ; 0");
	//photoImage = null;
	//lbl3h_foto.setImage(null);
    }

    /**2009-10-08(moraless)*/
    private void btnSaveData_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (headerDataControl()) {
	        if (!checkExistPoll()) {
	            String params = ""+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"','"+ tfNroEncuesta.getInteger() +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) +"',"+ chkAnuncioAsociado.isSelected();
	            encabezadoRelevamientoPublicidad.setIdRelevamientoPublicidad(LibSQL.getInt("gea_salta.addRelevamientoPublicidadEncabezado", params));
	            if ( encabezadoRelevamientoPublicidad.getIdRelevamientoPublicidad() > 0) {
	                if(LibSQL.getBoolean("gea_salta.existeEncuestaComercial2009",""+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"',"+ tfNroEncuesta.getInteger())){
	                    loadData(""+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"',"+ tfNroEncuesta.getInteger());             
	                }
	                
	                setTabs(1);
	            } else  {
	                Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
	            }
	        } else {
	            Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +"\nen la zona Nº "+ tfZonaNro.getValue() + " ya fué registrada", "Error");
	            tfNroEncuestador.getTextField().requestFocus(); 
	        }
	    } else {
	        errorForm.showMessage();
	        setFocus(0);
	    }    
	}
    }

    /**2009-10-08(moraless)*/    
    //método que recupera los datos del censo comercial.
    private void loadData(String _params){
	ResultSet ubicacionGeografica = LibSQL.exFunction("gea_salta.getubicaciongeograficacenso",_params);
	ResultSet personaFisica = LibSQL.exFunction("gea_salta.getdatospersonafisicacenso",_params);
	point = new ESRIPoint(0,0);
	try {
	    if(ubicacionGeografica.next()){
		tf1a.setValue(ubicacionGeografica.getString("f1a"));
		
		tf1b.setValue(ubicacionGeografica.getString("f1b"));
	        tf1c.setValue(ubicacionGeografica.getString("f1c"));
	        tf1d.setValue(ubicacionGeografica.getString("f1d"));
	        tf1e.setValue(ubicacionGeografica.getString("f1e"));
	        tf1f.setValue(ubicacionGeografica.getString("f1f"));
	        tf1g.setValue(ubicacionGeografica.getString("f1g"));
	        tf1h.setValue(ubicacionGeografica.getString("f1h"));
	        tf1i.setValue(ubicacionGeografica.getString("f1i"));
	        tf1j.setValue(ubicacionGeografica.getString("f1j"));
	        tf1k.setValue(ubicacionGeografica.getString("f1k"));
		tf3i_Catastro.setValue(ubicacionGeografica.getString("f1k"));
	       // System.out.println("x = "+ubicacionGeografica.getDouble("x"));
	        //System.out.println("y = "+ubicacionGeografica.getDouble("y"));
		point.setLocation(ubicacionGeografica.getDouble("x"),ubicacionGeografica.getDouble("y"));
		tfCoordenadas.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	    } else{
	        point.setLocation(0,0);
	    }
	    if(personaFisica.next()){
	        tf2a.setValue(personaFisica.getString("f3b"));
	        tf2b.setValue(personaFisica.getString("f3a"));
		if(personaFisica.getString("f3e").equals("F")){
		    rb2c_b.setSelected(true);
		}
		else{
		    if(personaFisica.getString("f3e").equals("M")){
			rb2c_a.setSelected(true);	
		    }
		    else{
		        rb2c.setSelected(true);
		    }
		    
		}
	        tf2d.setValue(personaFisica.getString("f3c"));
	        tf2e.setValue(personaFisica.getString("f3d"));
	        tf2f.setValue(personaFisica.getString("f3h"));
	        tf2g.setValue(personaFisica.getString("f3i"));
	        tf2h.setValue(personaFisica.getString("f3j"));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    private boolean checkExistPoll() {
	return LibSQL.getBoolean("gea_salta.existeRelevamientoPublicidad",""+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"',"+ tfNroEncuesta.getInteger());
    }

    private boolean headerDataControl() {
	errorForm = new ErrPublicidad();
	boolean valor = true;
	if (tfNroEncuestador.getInteger() < 1)  {
	    valor = false;
	    errorForm.setError(errorForm.F0a);
	} else if (tfZonaNro.getString().equals("") || tfZonaNro.getString().equals("0"))  {
	    valor = false;
	    errorForm.setError(errorForm.F0b);
	} else if (tfNroEncuesta.getString().equals("") || tfNroEncuesta.getString().equals("0"))  {
	    valor = false;
	    errorForm.setError(errorForm.F0c);
	} else if (tfFecha.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F0d);
	}
	return valor;
    }

    private void btnSaveDataF1_actionPerformed(ActionEvent e) {
        panelData.setEnabledAt(0,true);
        panelData.setEnabledAt(1,true);
        panelData.setEnabledAt(2,true);
	if(!isEdit){
	    if (seccion1DataControl()) {
		String params = "false,"+ encabezadoRelevamientoPublicidad.getIdRelevamientoPublicidad() +",'" + tf1a.getValue() + "','" + tf1b.getString() + "','" + 
				tf1c.getString() + "','" + tf1d.getString() + "','"+ tf1e.getString() + "','"+ tf1f.getString() + "','"+ tf1g.getString() + "','"+
				tf1h.getString() + "','"+ tf1i.getString() + "','"+ tf1j.getString() + "','"+ tf1k.getString() + "',"+ 
				point.getX() + ", " + point.getY();
		if (LibSQL.getInt("gea_salta.addRelevamientoPublicidadSeccion1", params) > 0) {
		    getLayer().getGeometrySet().addGeometry(point);
		    setTabs(2);
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}
	    } else {
		errorForm.showMessage();
		setFocus(1);
	    }
	}
    }
    
    private boolean seccion1DataControl() {
	errorForm = new ErrPublicidad();
	boolean valor = true;
	if (tf1a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1a);
	} else if (tf1b.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1b);
	} else if (tfCoordenadas.getValue().toString().equals("0 ; 0"))  {
	    valor = false;
	    errorForm.setError(errorForm.Coordenadas);
	}
	return valor;
    }

    private String getParam2(){
	String rb2cText = "";
	if (rb2c.isSelected())  {
	    rb2cText = rb2c.getText();
	} else if (rb2c_a.isSelected())  {
	    rb2cText = rb2c_a.getText();
	} else if (rb2c_b.isSelected())  {
	    rb2cText = rb2c_b.getText();
	}

	int idRelevamiento = -1;
	if(!isEdit){
	    idRelevamiento = encabezadoRelevamientoPublicidad.getIdRelevamientoPublicidad();
	}else{
	    idRelevamiento = encabezadoRelevamiento.getIdRelevamientoPublicidad();
	}
	String params2 = "false,"+ idRelevamiento +",'" + tf2a.getString() + "','" + tf2b.getString() + "','" + 
			rb2cText + "','" + tf2d.getString() + "','"+ tf2e.getString() + "','"+ tf2f.getString() + "','"+ tf2g.getString() + "','"+
			tf2h.getString() + "','"+ ta2i.getText() + "',''";
	return params2;
    }

    private void btnSaveDataF2_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion2DataControl()) {
	        String params = getParam2();
	        if (LibSQL.getInt("gea_salta.addRelevamientoPublicidadSeccion2", params) > 0) {
	            setTabs(3);
	        } else {
	            Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	        }
	    } else {
	        errorForm.showMessage();
	        setFocus(2);
	    }    
	}
    }
    
    private boolean seccion2DataControl() {
	
	errorForm = new ErrPublicidad();
	boolean valor = true;
	if (tf2a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2a);
	} else if (tf2b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F2b);
	} else if (tf2d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2d);
	} else if (tf2e.getString().equals("") && !chk2fgh.isSelected())  {
	    valor = false;
	    errorForm.setError(errorForm.F2e);
	} else if (tf2f.getString().equals("") && !chk2fgh.isSelected())  {
	    valor = false;
	    errorForm.setError(errorForm.F2f);
	} 
	return valor;
    }
    
    
    private void rb2d_a_actionPerformed(ActionEvent e){
	if (rb3a_a.isSelected())  {
	    setRbtn2d(1);
	} else  {
	}
    }
    
    private void rb2d_b_actionPerformed(ActionEvent e){
	if (rb3a_b.isSelected())  {
	    setRbtn2d(2);
	} else  {
	}
    }
    
    private void rb2d_c_actionPerformed(ActionEvent e){
	if (rb3a_c.isSelected())  {
	    setRbtn2d(3);
	} else  {
	}
    }
    
    private void rb2d_actionPerformed(ActionEvent e){
	if (rb3a_e.isSelected())  {
	    setRbtn2d(4);
	} else  {
	}
    }
    
    
    private void chk3a_actionPerformed(ActionEvent e){
	if (chk2a.isSelected())  {
	    tf2a.setValue(chk2a.getText());
	} else  {
	    tf2a.setValue("");
	}
    }
    
    private void chk3b_actionPerformed(ActionEvent e){
	if (chk2b.isSelected())  {
	    tf2b.setValue(chk2b.getText());
	} else  {
	    tf2b.setValue("");
	}
    }
    
    private void chk3de_actionPerformed(ActionEvent e){
	if (chk2de.isSelected())  {
	    tf2d.setValue(chk2de.getText());
	    tf2e.setValue(chk2de.getText());
	} else  {
	    tf2d.setValue("");
	    tf2e.setValue("");
	}
    }
    
    private void chk3fgh_actionPerformed(ActionEvent e){
	if (chk2fgh.isSelected())  {
	    tf2f.setValue(chk2fgh.getText());
	    tf2g.setValue(chk2fgh.getText());
	    tf2h.setValue(chk2fgh.getText());
	} else  {
	    tf2f.setValue("");
	    tf2g.setValue("");
	    tf2h.setValue("");
	}
    }
    
    private void clearPanel3() {
	tf3c.setValue("0.0");
	tf3d.setValue("0.0");
	tf3f.setValue("0.0");
	ta3g.setText("");
	tf3h.setValue("");
	rb3e_1.setSelected(false); 
	rb3e_2.setSelected(false); 
	rb3e_3.setSelected(false); 
	rb3e_4.setSelected(false); 
	
	rb3a_a.setSelected(false);
	rb3a_b.setSelected(false);
	rb3a_c.setSelected(false);
	rb3a_d.setSelected(false);
	rb3a_e.setSelected(false);
	
	rb3b_a.setSelected(false);
	rb3b_b.setSelected(false);
	rb3b_c.setSelected(false);
	rb3b_d.setSelected(false);
	
	lbl3h_foto.setImage(null);
	photoImage = null;
    }
    
    private void btnSaveDataF3_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion3DataControl()) {
		String params = getParam3();
		int _iddetallerelevamiento = LibSQL.getInt("gea_salta.addRelevamientoPublicidadDetalle", params);
		if ( _iddetallerelevamiento > 0) {
		    if (photoImage != null) {
			 LibIMG.saveImage(photoImage, "gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + _iddetallerelevamiento);
		    }
		    refresh();
		    clearPanel3();
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}
	    } else {
		errorForm.showMessage();
		setFocus(3);
	    }
	}else{
	    if (seccion3DataControl()) {
	        String params = getParam3();
	        int _iddetallerelevamiento = LibSQL.getInt("gea_salta.addRelevamientoPublicidadDetalle", params);
		//int _iddetallerelevamiento = encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getIddetallerelevamiento();
	        if ( _iddetallerelevamiento > 0) {
	            if (photoImage != null) {
	                 LibIMG.saveImage(photoImage, "gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + _iddetallerelevamiento);
	            }
	            refresh();
	            clearPanel3();
	            //Advisor.messageBox("Las modificaciones realizadas en la pestaña \n\"3. Características del Anuncio\" \nse guardaron exitosamente", "Mensaje");
		    encabezadoRelevamiento.inicializarCartel();
	        } else {
	            Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	        }
	    } else {
	        errorForm.showMessage();
	        setFocus(3);
	    }
	}
    }
    
    private boolean seccion3DataControl() {
	errorForm = new ErrPublicidad();
	boolean valor = true;
	if (tf3c.getDouble() < 0.0 )  {
	    valor = false;
	    errorForm.setError(errorForm.F3c);
	} else if (tf3d.getDouble() < 0.0)  {
	    valor = false;
	    errorForm.setError(errorForm.F3d);
	} else if (tf3f.getDouble() < 0.0)  {
	    valor = false;
	    errorForm.setError(errorForm.F3f);
	} else if (ta3g.getText().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3g);
	} else if (tfCoordenadaCartel.getValue().equals("0 ; 0")){
	    valor = false;
	    errorForm.setError(errorForm.CoordenadasCartel);
	}
	
	return valor;
    }
    
    private String getParam3(){
	String rb3aText = "";
	String rb3bText = "";
	String rb3eText = "0";
	
	if (rb3a_a.isSelected())  {
	    rb3aText = rb3a_a.getText();
	} else if (rb3a_b.isSelected())  {
	    rb3aText = rb3a_b.getText();
	} else if (rb3a_c.isSelected())  {
	    rb3aText = rb3a_c.getText();
	} else if (rb3a_d.isSelected())  {
	    rb3aText = rb3a_d.getText();
	} else if (rb3a_e.isSelected())  {
	    rb3aText = rb3a_e.getText();
	}
	
	if (rb3b_a.isSelected())  {
	    rb3bText = rb3b_a.getText();
	} else if (rb3b_b.isSelected())  {
	    rb3bText = rb3b_b.getText();
	} else if (rb3b_c.isSelected())  {
	    rb3bText = rb3b_c.getText();
	} else if (rb3b_d.isSelected())  {
	    rb3bText = rb3b_d.getText();
	} 
	
	if (rb3e_1.isSelected())  {
	    rb3eText = rb3e_1.getText();
	} else if (rb3e_2.isSelected())  {
	    rb3eText = rb3e_2.getText();
	} else if (rb3e_3.isSelected())  {
	    rb3eText = rb3e_3.getText();
	} else if (rb3e_4.isSelected())  {
	    rb3eText = rb3e_4.getText();
	}
	
	//System.out.println("imagen" + photoImage);
	/*if (lbl3h_foto.getIcon() != null)  {
	//if (lbl3h_foto.getImage() != null)  {
	    photoImage = lbl3h_foto.getImage();    
	} else  {
	    photoImage = null;
	    System.out.println("no tiene imagen");
	}*/
	
	String insercion = "true";
	int idrelevamiento = -1;
	int iddetalle = -1;
	if(!isEdit){
	    idrelevamiento = encabezadoRelevamientoPublicidad.getIdRelevamientoPublicidad();
	}else{
	    if(encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getIddetallerelevamiento()==-1){
		//insertar un nuevo cartel correspondiente relevamiento existente
	        idrelevamiento = encabezadoRelevamiento.getIdRelevamientoPublicidad();
	    }else{
		//modificar un cartel existente
	        insercion = "false";
	        idrelevamiento = encabezadoRelevamiento.getIdRelevamientoPublicidad();
	        iddetalle = encabezadoRelevamiento.getClassDetalleRelevamientoPublicidad().getIddetallerelevamiento();    
	    }
	}
	String params3 = insercion+","+iddetalle+", "+ idrelevamiento +",'" + rb3aText + "','"+ rb3bText + "',"+ 
			tf3c.getString() + "," + tf3d.getString() + "," + rb3eText + ","+ tf3f.getString() + ",'"+ ta3g.getText() + "','"+
			tf3h.getString() + "','','','"+ tf3i_Catastro.getValue() +"',"+  point.getX() + ", " + point.getY();
	return params3;
    }

    private void tf2d_mouseClicked(MouseEvent e) {
	setRbtn2d(0);
    }
    
    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    point = (ESRIPoint)_contentObject;
	    if (panelData.getSelectedIndex() == 0)  {
	    
	    //point.setLocation();
	        tfCoordenadas.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	        tf1k.fetchCatastro(point);	
	    } else  {
	        tfCoordenadaCartel.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	        tf3i_Catastro.fetchCatastro(point);
	    }
	} else {
	    point = null;
	}
    }

    private void setFocus(int _form) {
	switch (_form)  {
	    case 0: {
		    if (errorForm.getError() == errorForm.F0a)  {
			tfNroEncuestador.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F0b) {
			tfZonaNro.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F0c) {
			tfNroEncuesta.getTextField().requestFocus();
		    }
		}
		break;
	    case 1: {
		    if (errorForm.getError() == errorForm.F1a)  {
			tf1aFind.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1b) {
			tf1bFind.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1c) {
			tf1c.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1d) {
			tf1d.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1e) {
			tf1e.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1f) {
			tf1f.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1g) {
			tf1g.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1h) {
			tf1h.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1i) {
			tf1i.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1j) {
			tf1j.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F1k) {
			tf1k.getTextField().requestFocus();
		    } 
		}
		break;
	    case 2: {
		    if (errorForm.getError() == errorForm.F2a)  {
			tf2a.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2b) {
			tf2b.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2c) {
			//tf2c.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2d) {
			tf2d.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2e) {
			tf2e.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2f) {
			tf2f.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2g) {
			tf2g.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2h) {
			tf2h.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2k) {
			//ta2k.getTextArea().requestFocus();
		    } else if (errorForm.getError() == errorForm.F2l) {
			//ta2l.getTextArea().requestFocus();
		    }
		}
		break;
	    case 3: {
		    if (errorForm.getError() == errorForm.F3c)  {
			tf3c.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3d) {
			tf3d.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3f) {
			tf3f.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3g) {
			ta3g.getTextArea().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3h) {
			tf3h.getTextField().requestFocus();
		    }
		}
		break;
	}
    }

/*    @Override
    public Object getContentObject() {
	return adminyServ;
    }
*/

    private void btnFinishF3_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if(listPanel.getTable().getRowCount()>0){
	        encabezadoRelevamientoPublicidad = new ClassEncabezadoRelevamientoPublicidad();
	        refresh(); 
	        setTabs(0);
	    }
	    else{
	        Advisor.messageBox("Debe ingresar características del anuncio", "Mensaje");
	    }    
	}
	
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
			     LibIMG.escalaImg(photoImage, lbl3h_foto);
			     /*if (person != null) { 
				 person.setPhotoImage(photo);
			     }*/
			     tf3h.setValue(fotoFile.getName());
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

    private void btnObservaciones_actionPerformed(ActionEvent e) {
	//EncuestasSinCargar observaciones = new EncuestasSinCargar(EncuestasSinCargar.PUBLICIDAD);
	EncuestasSinCargar observaciones = new EncuestasSinCargar(EncuestasSinCargar.PUBLICIDAD,tfNroEncuestador.getInteger(),tfZonaNro.getValue().toString(),tfNroEncuesta.getValue().toString());
	ExtendedInternalFrame observacionesContainer = new ExtendedInternalFrame("Observaciones");
	observacionesContainer.setCentralPanel(observaciones);
	observacionesContainer.show();
	//ComponentsManager.centerWindow(observaciones);
	//observaciones.setModal(true);
	observaciones.setVisible(true);
    }

    private void btnVerFotos_actionPerformed(ActionEvent e) {
	ReparaFotosCarteles observaciones = new ReparaFotosCarteles();
	ExtendedInternalFrame DesgloseContainer = new ExtendedInternalFrame("Ver fotos");
	DesgloseContainer.setCentralPanel(observaciones);
	DesgloseContainer.show();
	//ComponentsManager.centerWindow(observaciones);
	//observaciones.setModal(true);
	observaciones.setVisible(true);
    }
    
    public void saveData() {
	guardarDatos();
    }
    
    /**2009-12-15(moraless)*/
    private boolean controlFormularios(){
	boolean retorno = true;
	//datos de encabezado
	if((headerDataControl())){
	    if((retorno)&&(seccion1DataControl())){
		if((retorno)&&(seccion2DataControl())){
		    
		}else{
		    retorno = false;
		}
	    }else{
		retorno = false;
	    }
	}else{
	    retorno = false;
	}
	return retorno;
    }
    
    /**2009-12-15(moraless)*/
    private void guardarDatos(){
	boolean datosModificados = true;
	//encabezado
	if(controlFormularios()){
	    String encabezadoActual = ""+tfNroEncuestador.getValue()+tfZonaNro.getValue()+tfNroEncuesta.getValue();
	    if ((encabezadoActual.toUpperCase().equals(datosEncabezadoOriginal.toUpperCase()))||(!checkExistPoll())) {
	        String params = ""+encabezadoRelevamiento.getIdRelevamientoPublicidad()+","+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"','"+ tfNroEncuesta.getInteger() +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) +"',"+ chkAnuncioAsociado.isSelected();
	        if (LibSQL.getInt("gea_salta.setrelevamientopublicidad2009encabezado", params) > 0) {
	            //Advisor.messageBox("Las modificaciones realizadas en el encabezado de la encuesta se guardaron exitosamente","Mensaje");
	       
	        } else  {
	            datosModificados = false;
	            Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
	        }
	    } else {
	        datosModificados = false;
	        Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +"\nen la zona Nº "+ tfZonaNro.getValue() + " ya fué registrada", "Error");
	        tfNroEncuestador.getTextField().requestFocus(); 
	    }
	    //seccion1
	    if(datosModificados){
	       String params = "false,"+ encabezadoRelevamiento.getIdRelevamientoPublicidad() +",'" + tf1a.getValue() + "','" + tf1b.getString() + "','" + 
	                       tf1c.getString() + "','" + tf1d.getString() + "','"+ tf1e.getString() + "','"+ tf1f.getString() + "','"+ tf1g.getString() + "','"+
	                       tf1h.getString() + "','"+ tf1i.getString() + "','"+ tf1j.getString() + "','"+ tf1k.getString() + "',"+ 
	                       point.getX() + ", " + point.getY();
	       if (LibSQL.getInt("gea_salta.addRelevamientoPublicidadSeccion1", params) > 0) {
		params = ""+encabezadoRelevamiento.getIdRelevamientoPublicidad()+",'"+tf1k.getString()+"'";
		 /*if (LibSQL.getInt("gea_salta.setcatastrocartel", params) > 0) {
		     
		 }else{
		     datosModificados = false;
		     Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		 }*/
	           //getLayer().getGeometrySet().addGeometry(point);
	           //setTabs(2);
	            //Advisor.messageBox("Las modificaciones realizadas en la pestaña \n\"1. Ubicación del anuncio\" \nse guardaron exitosamente", "Mensaje");
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	    
	    //seccion2
	    if(datosModificados){
	       String params = getParam2();
	       if (LibSQL.getInt("gea_salta.addRelevamientoPublicidadSeccion2", params) > 0) {
	           //Advisor.messageBox("Las modificaciones realizadas en la pestaña \n\"2. Datos del Responsable o Responsable Solidario\" \nse guardaron exitosamente", "Mensaje");
	           Advisor.messageBox("Las modificaciones realizadas se guardaron exitosamente", "Mensaje");
	           super.hideForm();
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }   
	}else{
	    errorForm.showMessage();
	}
    }
    
    public void delete(){
	String params = ""+tfNroEncuestador.getValue()+",'"+tfZonaNro.getValue()+"','"+tfNroEncuesta.getValue()+"'";
	//if(LibSQL.getInt("gea_salta.delrelevamientopublicidad2009", params) > 0){
	if(LibSQL.getInt("gea_salta.delrelevamientopublicidad2009", ""+encabezadoRelevamiento.getIdRelevamientoPublicidad()) > 0){
	    params = "'"+tfNroEncuestador.getValue()+"','"+tfZonaNro.getValue()+"','"+tfNroEncuesta.getValue()+"','Publicidad','Eliminado','','','','','','','','','','','','','ENCUESTA ELIMINADA','',''";
	    if(LibSQL.getInt("gea_salta.addobservaciones",params) > 0){
	        //Advisor.messageBox("La encuesta Nº "+tfNroEncuesta.getValue()+" se eliminó exitosamente", "Mensaje");
	         Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +"\nen la zona Nº "+ tfZonaNro.getValue() + " se eliminó exitosamente", "Mensaje");
	    }else{
	        
	    }
	    super.hideForm();
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	}
    }
    
    public void close(){
	super.hideForm();
    }

    private void btnEliminarEncuesta_actionPerformed(ActionEvent e) {
	PanelBorrarEncuestas encuestasEliminadas = new PanelBorrarEncuestas(false);
	ExtendedInternalFrame DesgloseContainer = new ExtendedInternalFrame("Buscar Encuesta");
	DesgloseContainer.setCentralPanel(encuestasEliminadas);
	DesgloseContainer.show();
	//ComponentsManager.centerWindow(observaciones);
	//observaciones.setModal(true);
	encuestasEliminadas.setVisible(true);
    }
    
    private void clickPrint(MouseEvent e){
        super.doCSVReport("Encuestas de Publicidad y Propaganda", "gea_salta.xmlgetencuestaspublicidad()");
    }

}

