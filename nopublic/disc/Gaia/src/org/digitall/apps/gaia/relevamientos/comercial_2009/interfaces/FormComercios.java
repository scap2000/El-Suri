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
 * FormComercios.java
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

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassEncabezadoEncuesta;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses.ErrComercios;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.sql.LibSQL;


public class FormComercios extends GaiaPrimitiveForm {
    
    private BasicPanel contentPanel = new BasicPanel();
    private BasicTabbedPane panelData = new BasicTabbedPane();
    private BasicPanel jpCabecera = new BasicPanel();
    private ESRIPoint point = null;
    private TFInput tfNroEncuestador = new TFInput(DataTypes.INTEGER, "Nº de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.INTEGER, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.INTEGER, "Nº de Encuesta", false);
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Fecha", false);

    /** 1. Ubicación geográfica */
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
    //private TFInput tf1k = new TFInput(DataTypes.INTEGER, "1k. Nº CÉDULA PARCELARIA", false);
    private GaiaCatastroInputPanel tf1k  = new GaiaCatastroInputPanel();
    private TFInput tf1l = new TFInput(DataTypes.STRING, "1l. (Si aplica) NOMBRE DEL CENTRO COMERCIAL U OTRO", false);

    /** 2. Datos del negocio */
    private BasicPanel jpDatosNegocio = new BasicPanel();
    private TFInput tf2a = new TFInput(DataTypes.STRING, "2a. NOMBRE DE FANTASÍA", false);
    private BasicCheckBox chk2a = new BasicCheckBox("NS/NC");
    private TFInput tf2b = new TFInput(DataTypes.STRING, "2b. RAZÓN SOCIAL", false);
    private BasicCheckBox chk2b = new BasicCheckBox("NS/NC");
    private TFInput tf2c = new TFInput(DataTypes.STRING, "2c. Nº de C.U.I.T.", false);
    private BasicCheckBox chk2c = new BasicCheckBox("NS/NC");
    private TFInput tf2d = new TFInput(DataTypes.STRING, "2d. Nº DE PADRÓN COMERCIAL", false);
    private BasicRadioButton rb2d_a = new BasicRadioButton("HAB. EN TRÁMITE");
    private BasicRadioButton rb2d_b = new BasicRadioButton("HAB. PROVISORIA");
    private BasicRadioButton rb2d_c = new BasicRadioButton("SIN HABILITACIÓN");
    private BasicRadioButton rb2d = new BasicRadioButton("NS/NC");
    //private ButtonGroup gruporb2d = new ButtonGroup();
    private ButtonGroup gruporb2j = new ButtonGroup();
    private TFInput tf2e = new TFInput(DataTypes.STRING, "2e. TELÉFONO FIJO", false);
    private TFInput tf2f = new TFInput(DataTypes.STRING, "2f. FAX", false);
    private TFInput tf2g = new TFInput(DataTypes.STRING, "2g. CELULAR", false);
    private BasicCheckBox chkefg = new BasicCheckBox("NS/NC");
    private TFInput tf2h = new TFInput(DataTypes.STRING, "2h. E-MAIL", false);
    private BasicCheckBox chk2h = new BasicCheckBox("NS/NC");
    private BasicLabel lbl2i = new BasicLabel("2i. CARACTERÍSTICAS DEL LOCAL:");
    private BasicRadioButton rb2i_a = new BasicRadioButton("PROPIO");
    private BasicRadioButton rb2i_b = new BasicRadioButton("ALQUILADO");
    private BasicRadioButton rb2i_c = new BasicRadioButton("CON PERMISO DE USO");
    private BasicRadioButton rb2i = new BasicRadioButton("NS/NC");
    private ButtonGroup gruporb2i = new ButtonGroup();
    private BasicLabel lbl2j = new BasicLabel("2j. RUBRO PRINCIPAL:");
    private BasicRadioButton rb2j_a = new BasicRadioButton("COMERCIAL");
    private BasicRadioButton rb2j_b = new BasicRadioButton("DE SERVICIOS");
    private BasicRadioButton rb2j_c = new BasicRadioButton("INDUSTRIAL");
    private BasicRadioButton rb2j = new BasicRadioButton("NS/NC");
    private BasicLabel lbl2k = new BasicLabel("2k. DESCRIPCIÓN DEL RUBRO PRINCIPAL");
    private JArea ta2k = new JArea();
    private BasicLabel lbl2l = new BasicLabel("2l. DESCRIPCIÓN DEL/LOS RUBRO/S SECUNDARIO/S");
    private JArea ta2l = new JArea();
    private TFInput tfCoordenadas = new TFInput(DataTypes.STRING,"COORDENADAS",false);


    /** 3. Datos del Propietario */
    private BasicPanel jpDatosPropietario = new BasicPanel();
    private TFInput tf3a = new TFInput(DataTypes.STRING, "3a. NOMBRE/S DEL PROPIETARIO O SOCIO PRINCIPAL", false);
    private BasicCheckBox chk3a = new BasicCheckBox("NS/NC");
    private TFInput tf3b = new TFInput(DataTypes.STRING, "3b. APELLIDO/S DEL PROPIETARIO O SOCIO PRINCIPAL", false);
    private BasicCheckBox chk3b = new BasicCheckBox("NS/NC");
    private BasicLabel lbl3c = new BasicLabel("3c. SEXO:");
    private BasicRadioButton rb3c_a = new BasicRadioButton("MASCULINO");
    private BasicRadioButton rb3c_b = new BasicRadioButton("FEMENINO");
    private BasicRadioButton rb3c = new BasicRadioButton("NS/NC");
    private ButtonGroup grupo3c = new ButtonGroup();
    private TFInput tf3d = new TFInput(DataTypes.STRING, "3d. TIPO DE DOCUMENTO", false);
    private TFInput tf3e = new TFInput(DataTypes.STRING, "3e. Nº DE DOCUMENTO", false);
    private BasicCheckBox chk3de = new BasicCheckBox("NS/NC");
    private TFInput tf3f = new TFInput(DataTypes.STRING, "3f. TELÉFONO FIJO", false);
    private TFInput tf3g = new TFInput(DataTypes.STRING, "3g. FAX", false);
    private TFInput tf3h = new TFInput(DataTypes.STRING, "3h. CELULAR", false);
    private BasicCheckBox chk3fgh = new BasicCheckBox("NS/NC");
    private TFInput tf3iFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3i = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf3i = new TFInput(DataTypes.STRING, "3i. BARRIO", false);
    private TFInput tf3jFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3j = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf3j = new TFInput(DataTypes.STRING, "3j. CALLE", false);
    private TFInput tf3k = new TFInput(DataTypes.STRING, "3k. CÓD. CALLE", false);
    private TFInput tf3l = new TFInput(DataTypes.STRING, "3l. NUMERACIÓN", false);
    private TFInput tf3m = new TFInput(DataTypes.STRING, "3m. PISO", false);
    private TFInput tf3n = new TFInput(DataTypes.STRING, "3n. Nº o LETRA", false);
    private TFInput tf3o = new TFInput(DataTypes.STRING, "3o. SECT/BLOQ", false);
    private TFInput tf3p = new TFInput(DataTypes.STRING, "3p. MANZANA", false);
    private TFInput tf3q = new TFInput(DataTypes.STRING, "3q. CASA", false);
    private TFInput tf3r = new TFInput(DataTypes.STRING, "3r. MEDIDOR", false);

    /** 4. Datos de la persona que suministra la información */
    private BasicPanel jpDatosSuministrador = new BasicPanel();
    private TFInput tf4a = new TFInput(DataTypes.STRING, "4a. NOMBRE/S DEL SUMINISTRADOR", false);
    private BasicCheckBox chk4a = new BasicCheckBox("NS/NC");
    private TFInput tf4b = new TFInput(DataTypes.STRING, "4b. APELLIDO/S DEL SUMINISTRADOR", false);
    private BasicCheckBox chk4b = new BasicCheckBox("NS/NC");
    private TFInput tf4c = new TFInput(DataTypes.STRING, "4c. TIPO DE DOCUMENTO", false);
    private TFInput tf4d = new TFInput(DataTypes.STRING, "4d. Nº DE DOCUMENTO", false);
    private BasicCheckBox chk4cd = new BasicCheckBox("NS/NC");
    private TFInput tf4e = new TFInput(DataTypes.STRING, "4e. RELACIÓN CON EL PROPIETARIO DEL NEGOCIO", false);
    private BasicCheckBox chk4e = new BasicCheckBox("NS/NC");
    private BasicLabel lbl5a = new BasicLabel("5a. OBSERVACIONES");
    private JArea ta5a = new JArea();

    private SaveDataButton btnSaveData = new SaveDataButton();
    private SaveDataButton btnSaveDataF1 = new SaveDataButton();
    private SaveDataButton btnSaveDataF2 = new SaveDataButton();
    private SaveDataButton btnSaveDataF3 = new SaveDataButton();
    private SaveDataButton btnSaveDataF4 = new SaveDataButton();
    
    private ClassEncabezadoEncuesta encabezadoEncuesta;
    private ErrComercios errorForm;
    private TFInput tfBarcode = new TFInput(DataTypes.INTEGER, "Código de Barras", false);

    public FormComercios() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("CENSO COMERCIAL 2009");
	contentPanel.setBounds(new Rectangle(10, 10, 750, 595));
	contentPanel.setPreferredSize(new Dimension(710, 595));
	contentPanel.setSize(new Dimension(710, 595));
	this.setSize(new Dimension(734, 655));
	this.setPreferredSize(new Dimension(734, 655));
	tfNroEncuestador.setBounds(new Rectangle(10, 5, 120, 35));
	tfZonaNro.setBounds(new Rectangle(141, 5, 80, 35));
        tfZonaNro.setSize(new Dimension(80, 35));
        tfNroEncuesta.setBounds(new Rectangle(233, 5, 105, 35));
	tfFecha.setBounds(new Rectangle(349, 5, 95, 35));
	jpCabecera.setLayout(null);
        jpCabecera.add(tfBarcode, null);
        jpCabecera.add(btnSaveData, null);
	jpCabecera.add(tfNroEncuestador, null);
        jpCabecera.add(tfZonaNro, null);
        jpCabecera.add(tfNroEncuesta, null);
        jpCabecera.add(tfFecha, null);
        jpCabecera.setPreferredSize(new Dimension(1, 50));
	tf1aFind.setBounds(new Rectangle(10, 10, 105, 35));
	cb1a.setBounds(new Rectangle(120, 10, 270, 35));
	tf1bFind.setBounds(new Rectangle(10, 50, 105, 35));
	tf1b.setBounds(new Rectangle(380, 50, 140, 35));
	tf1c.setBounds(new Rectangle(525, 50, 100, 35));
	tf1d.setBounds(new Rectangle(10, 90, 110, 35));
	tf1e.setBounds(new Rectangle(125, 90, 55, 35));
	tf1f.setBounds(new Rectangle(185, 90, 95, 35));
	tf1g.setBounds(new Rectangle(285, 90, 95, 35));
	tf1h.setBounds(new Rectangle(385, 90, 90, 35));
	tf1i.setBounds(new Rectangle(480, 90, 60, 35));
	tf1j.setBounds(new Rectangle(545, 90, 80, 35));
	tf1k.setBounds(new Rectangle(10, 130, 170, 35));
	tf1l.setBounds(new Rectangle(185, 130, 440, 35));
	jpUbicacionGeografica.setLayout(null);
	jpUbicacionGeografica.add(tfCoordenadas, null);
	jpUbicacionGeografica.add(btnSaveDataF1, null);
	jpUbicacionGeografica.add(tf1a, null);
	jpUbicacionGeografica.add(tf1aFind, null);
	jpUbicacionGeografica.add(cb1a, null);
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
	jpUbicacionGeografica.add(tf1l, null);
	jpUbicacionGeografica.add(cb1b, null);
	tf2a.setBounds(new Rectangle(10, 10, 505, 35));
	chk2a.setBounds(new Rectangle(555, 25, 70, 20));
	chk2a.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		chk2a_actionPerformed(e);
	    }
	}
	);
	
	tf2b.setBounds(new Rectangle(10, 50, 505, 35));
	chk2b.setBounds(new Rectangle(555, 65, 70, 20));
	chk2b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk2b_actionPerformed(e);
		}
	}
	);
	tf2c.setBounds(new Rectangle(10, 90, 190, 35));
	chk2c.setBounds(new Rectangle(555, 105, 70, 20));
	chk2c.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       chk2c_actionPerformed(e);
				   }

			       }
	);
	tf2d.setBounds(new Rectangle(10, 130, 170, 35));
	/*tf2d.addMouseListener(new MouseListener() {
		public void 
	}
	);*/
	tf2d.getTextField().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    tf2d_mouseClicked(e);
		}
	    }
	);
	rb2d.setBounds(new Rectangle(555, 145, 70, 20));
	rb2d.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_actionPerformed(e);
		}
	}
	);
	tf2e.setBounds(new Rectangle(10, 170, 120, 35));
	tf2f.setBounds(new Rectangle(135, 170, 120, 35));
	tf2g.setBounds(new Rectangle(260, 170, 200, 35));
	chkefg.setBounds(new Rectangle(555, 185, 70, 20));
	chkefg.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk2efg_actionPerformed(e);
		}
	}
	);
	tf2h.setBounds(new Rectangle(10, 210, 505, 35));
	chk2h.setBounds(new Rectangle(555, 225, 70, 20));
	chk2h.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk2h_actionPerformed(e);
		}
	}
	);
	ta2k.setBounds(new Rectangle(10, 345, 615, 65));
	ta2l.setBounds(new Rectangle(10, 435, 615, 65));
	rb2d_a.setBounds(new Rectangle(210, 145, 135, 20));
	rb2d_a.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_a_actionPerformed(e);
		}
	}
	);
	rb2d_a.setFont(new Font("Lucida Sans", 0, 9));
	rb2d_a.setBounds(new Rectangle(185, 145, 115, 20));
	rb2d_b.setBounds(new Rectangle(325, 145, 135, 20));
	rb2d_b.setFont(new Font("Lucida Sans", 0, 9));
	rb2d_b.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_b_actionPerformed(e);
		}
	}
	);
	rb2d_b.setBounds(new Rectangle(305, 145, 120, 20));
	rb2d_c.setBounds(new Rectangle(435, 145, 110, 20));
	rb2d_c.setFont(new Font("Lucida Sans", 0, 9));
	rb2d_c.setBounds(new Rectangle(430, 145, 120, 20));
	rb2d_c.addActionListener(new ActionListener (){
		public void actionPerformed(ActionEvent e){
		    rb2d_c_actionPerformed(e);
		}
	}
	);
	gruporb2j.add(rb2j);
	gruporb2j.add(rb2j_a);
	gruporb2j.add(rb2j_b);
	gruporb2j.add(rb2j_c);
	rb2i_a.setBounds(new Rectangle(215, 260, 75, 20));
	rb2i_a.setFont(new Font("Lucida Sans", 0, 9));
	rb2i_a.setBounds(new Rectangle(210, 260, 80, 20));
	rb2i_b.setBounds(new Rectangle(295, 260, 100, 20));
	rb2i_b.setFont(new Font("Lucida Sans", 0, 9));
	rb2i_b.setBounds(new Rectangle(295, 260, 95, 20));
	rb2i_c.setBounds(new Rectangle(395, 260, 160, 20));
	rb2i_c.setFont(new Font("Lucida Sans", 0, 9));
	rb2i_c.setBounds(new Rectangle(395, 260, 135, 20));
	rb2i.setBounds(new Rectangle(555, 260, 70, 20));
	gruporb2i.add(rb2i);
	gruporb2i.add(rb2i_a);
	gruporb2i.add(rb2i_b);
	gruporb2i.add(rb2i_c);
	rb2j_a.setBounds(new Rectangle(150, 300, 100, 20));
	rb2j_a.setFont(new Font("Lucida Sans", 0, 9));
	rb2j_a.setBounds(new Rectangle(150, 300, 90, 20));
	rb2j_b.setBounds(new Rectangle(255, 300, 110, 20));
	rb2j_b.setFont(new Font("Lucida Sans", 0, 9));
	rb2j_b.setBounds(new Rectangle(255, 300, 95, 20));
	rb2j_c.setBounds(new Rectangle(370, 300, 135, 20));
	rb2j_c.setFont(new Font("Lucida Sans", 0, 9));
	rb2j_c.setBounds(new Rectangle(370, 300, 100, 20));
	rb2j.setBounds(new Rectangle(555, 300, 70, 20));
	lbl2i.setBounds(new Rectangle(10, 260, 200, 20));
	lbl2j.setBounds(new Rectangle(10, 300, 130, 20));
	lbl2k.setBounds(new Rectangle(10, 330, 240, 15));
	lbl2l.setBounds(new Rectangle(10, 420, 305, 15));
	jpDatosNegocio.setLayout(null);
	jpDatosNegocio.add(btnSaveDataF2, null);
	jpDatosNegocio.add(lbl2i, null);
	jpDatosNegocio.add(lbl2j, null);
	jpDatosNegocio.add(lbl2k, null);
	jpDatosNegocio.add(lbl2l, null);
	jpDatosNegocio.add(rb2d_a, null);
	jpDatosNegocio.add(rb2d_b, null);
	jpDatosNegocio.add(rb2d_c, null);
	jpDatosNegocio.add(tf2a, null);
	jpDatosNegocio.add(chk2a, null);
	jpDatosNegocio.add(tf2b, null);
	jpDatosNegocio.add(chk2b, null);
	jpDatosNegocio.add(tf2c, null);
	jpDatosNegocio.add(chk2c, null);
	jpDatosNegocio.add(tf2d, null);
	jpDatosNegocio.add(rb2d, null);
	jpDatosNegocio.add(tf2e, null);
	jpDatosNegocio.add(tf2f, null);
	jpDatosNegocio.add(tf2g, null);
	jpDatosNegocio.add(chkefg, null);
	jpDatosNegocio.add(tf2h, null);
	jpDatosNegocio.add(chk2h, null);
	jpDatosNegocio.add(ta2k, null);
	jpDatosNegocio.add(ta2l, null);
	jpDatosNegocio.add(rb2i_a, null);
	jpDatosNegocio.add(rb2i_b, null);
	jpDatosNegocio.add(rb2i_c, null);
	jpDatosNegocio.add(rb2i, null);
	jpDatosNegocio.add(rb2j_a, null);
	jpDatosNegocio.add(rb2j_b, null);
	jpDatosNegocio.add(rb2j_c, null);
	jpDatosNegocio.add(rb2j, null);
	panelData.addTab("jpUbicacionGeografica", jpUbicacionGeografica);
	panelData.addTab("2. DATOS DEL NEGOCIO", jpDatosNegocio);
	tf3a.setBounds(new Rectangle(10, 10, 505, 35));
	chk3a.setBounds(new Rectangle(555, 25, 70, 20));
	chk3a.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3a_actionPerformed(e);
		}
	}
	);
	tf3b.setBounds(new Rectangle(10, 50, 505, 35));
	chk3b.setBounds(new Rectangle(555, 65, 70, 20));
	chk3b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3b_actionPerformed(e);
		}
	}
	);
	lbl3c.setBounds(new Rectangle(10, 90, 60, 35));
	rb3c_b.setBounds(new Rectangle(180, 90, 90, 35));
	rb3c_a.setBounds(new Rectangle(75, 90, 100, 35));
	rb3c_b.setBounds(new Rectangle(180, 90, 100, 35));
	rb3c.setBounds(new Rectangle(555, 105, 70, 20));
	grupo3c.add(rb3c);
	grupo3c.add(rb3c_a);
	grupo3c.add(rb3c_b);
	rb3c_a.setSelected(true);
	tf3d.setBounds(new Rectangle(10, 130, 160, 35));
	tf3e.setBounds(new Rectangle(175, 130, 165, 35));
	chk3de.setBounds(new Rectangle(555, 145, 70, 20));
	chk3de.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk3de_actionPerformed(e);
		}
	
	}
	);
	tf3f.setBounds(new Rectangle(10, 170, 115, 35));
	tf3g.setBounds(new Rectangle(130, 170, 115, 35));
	tf3h.setBounds(new Rectangle(250, 170, 140, 35));
	chk3fgh.setBounds(new Rectangle(555, 185, 70, 20));
	chk3fgh.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3fgh_actionPerformed(e);
		}
	}
	);
	tf3i.setBounds(new Rectangle(395, 210, 230, 35));
	tf3i.setSize(new Dimension(230, 35));
	tf3j.setBounds(new Rectangle(380, 250, 140, 35));
	tf3j.setSize(new Dimension(140, 35));
	tf3k.setBounds(new Rectangle(525, 250, 100, 35));
	tf3l.setBounds(new Rectangle(10, 290, 105, 35));
	tf3m.setBounds(new Rectangle(120, 290, 60, 35));
	tf3n.setBounds(new Rectangle(185, 290, 95, 35));
	tf3o.setBounds(new Rectangle(285, 290, 95, 35));
	tf3p.setBounds(new Rectangle(385, 290, 90, 35));
	tf3q.setBounds(new Rectangle(480, 290, 60, 35));
	tf3r.setBounds(new Rectangle(545, 290, 80, 35));
	tf3iFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboBarriosForm3();
			    
		    }
		});
	tf3jFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboCallesForm3();
			    
		    }
		});
	jpDatosPropietario.setLayout(null);
	jpDatosPropietario.add(cb3j, null);
	jpDatosPropietario.add(cb3i, null);
	jpDatosPropietario.add(tf3jFind, null);
	jpDatosPropietario.add(tf3iFind, null);
	jpDatosPropietario.add(btnSaveDataF3, null);
	jpDatosPropietario.add(tf3a, null);
	jpDatosPropietario.add(chk3a, null);
	jpDatosPropietario.add(tf3b, null);
	jpDatosPropietario.add(chk3b, null);
	jpDatosPropietario.add(lbl3c, null);
	jpDatosPropietario.add(rb3c_a, null);
	jpDatosPropietario.add(rb3c_b, null);
	jpDatosPropietario.add(rb3c, null);
	jpDatosPropietario.add(tf3d, null);
	jpDatosPropietario.add(tf3e, null);
	jpDatosPropietario.add(chk3de, null);
	jpDatosPropietario.add(tf3f, null);
	jpDatosPropietario.add(tf3g, null);
	jpDatosPropietario.add(tf3h, null);
	jpDatosPropietario.add(chk3fgh, null);
	jpDatosPropietario.add(tf3i, null);
	jpDatosPropietario.add(tf3j, null);
	jpDatosPropietario.add(tf3k, null);
	jpDatosPropietario.add(tf3l, null);
	jpDatosPropietario.add(tf3m, null);
	jpDatosPropietario.add(tf3n, null);
	jpDatosPropietario.add(tf3o, null);
	jpDatosPropietario.add(tf3p, null);
	jpDatosPropietario.add(tf3q, null);
	jpDatosPropietario.add(tf3r, null);
	panelData.addTab("3. DATOS DEL PROPIETARIO", jpDatosPropietario);
	tf4a.setBounds(new Rectangle(10, 10, 505, 35));
	chk4a.setBounds(new Rectangle(555, 25, 70, 20));
	chk4a.addActionListener(new ActionListener (){
	            public void actionPerformed(ActionEvent e){
	                chk4a_actionPerformed(e);
	            }
	    }
	    );
	tf4b.setBounds(new Rectangle(10, 50, 505, 35));
	chk4b.setBounds(new Rectangle(555, 65, 70, 20));
	chk4b.addActionListener(new ActionListener (){
		    public void actionPerformed(ActionEvent e){
			chk4b_actionPerformed(e);
		    }
	    }
	    );
	tf4c.setBounds(new Rectangle(10, 90, 160, 35));
	chk4cd.addActionListener(new ActionListener (){
		    public void actionPerformed(ActionEvent e){
			xhk4cd_actionPerformed(e);
		    }
	    }
	    );
	tf4d.setBounds(new Rectangle(175, 90, 175, 35));
	chk4e.addActionListener(new ActionListener (){
		    public void actionPerformed(ActionEvent e){
			chk4e_actionPerformed(e);
		    }
	    }
	    );
	chk4cd.setBounds(new Rectangle(555, 105, 70, 20));
	tf4e.setBounds(new Rectangle(10, 130, 505, 35));
	chk4e.setBounds(new Rectangle(555, 145, 70, 20));
	lbl5a.setBounds(new Rectangle(10, 160, 125, 35));
	ta5a.setBounds(new Rectangle(10, 185, 615, 65));
	btnSaveDataF1.setToolTipText("Grabar");
	btnSaveDataF1.setBounds(new Rectangle(670, 475, 30, 25));
	btnSaveDataF1.setSize(new Dimension(30, 25));
	btnSaveDataF1.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF1_actionPerformed(e);
		}
	    }
	);
	btnSaveData.setToolTipText("Grabar");
	btnSaveData.setBounds(new Rectangle(670, 15, 30, 25));
	btnSaveData.setSize(new Dimension(30, 25));
	btnSaveData.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveData_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF2.setToolTipText("Grabar");
	btnSaveDataF2.setBounds(new Rectangle(670, 475, 30, 25));
	btnSaveDataF2.setSize(new Dimension(30, 25));
	btnSaveDataF2.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF2_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF3.setToolTipText("Grabar");
	btnSaveDataF3.setBounds(new Rectangle(670, 475, 30, 25));
	btnSaveDataF3.setSize(new Dimension(30, 25));
	btnSaveDataF3.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF3_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF4.setToolTipText("Grabar");
	btnSaveDataF4.setBounds(new Rectangle(670, 475, 30, 25));
	btnSaveDataF4.setSize(new Dimension(30, 25));
	btnSaveDataF4.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF4_actionPerformed(e);
		}
	    }
	);
        tfBarcode.setBounds(new Rectangle(455, 5, 135, 35));
        tfBarcode.setSize(new Dimension(135, 35));
        tfCoordenadas.setBounds(new Rectangle(10, 170, 255, 35));
	cb3i.setBounds(new Rectangle(135, 210, 250, 35));
	cb3i.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb3i_itemStateChanged(e);
		}
	    }
	);
	cb3j.setBounds(new Rectangle(135, 250, 240, 35));
	cb3j.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb3j_itemStateChanged(e);
		}
	    }
	);
	tf3iFind.setBounds(new Rectangle(10, 210, 115, 35));
	tf3jFind.setBounds(new Rectangle(10, 250, 115, 35));
	cb1b.setBounds(new Rectangle(120, 50, 255, 35));
	tf1a.setBounds(new Rectangle(395, 10, 230, 35));
	jpDatosSuministrador.setLayout(null);
	jpDatosSuministrador.setSize(new Dimension(745, 500));
	jpDatosSuministrador.setBounds(new Rectangle(2, 38, 745, 500));
	jpDatosSuministrador.add(btnSaveDataF4, null);
	jpDatosSuministrador.add(tf4a, null);
	jpDatosSuministrador.add(chk4a, null);
	jpDatosSuministrador.add(tf4b, null);
	jpDatosSuministrador.add(chk4b, null);
	jpDatosSuministrador.add(tf4c, null);
	jpDatosSuministrador.add(tf4d, null);
	jpDatosSuministrador.add(chk4cd, null);
	jpDatosSuministrador.add(tf4e, null);
	jpDatosSuministrador.add(chk4e, null);
	jpDatosSuministrador.add(lbl5a, null);
	jpDatosSuministrador.add(ta5a, null);
	panelData.addTab("4. DATOS DE LA PERSONA QUE SUMINISTRA LA INFORMACIÓN", jpDatosSuministrador);
	contentPanel.setLayout(new BorderLayout());
	panelData.setSize(new Dimension(750, 550));
	panelData.setBounds(new Rectangle(0, 0, 800, 550));
	panelData.setPreferredSize(new Dimension(800, 550));
	
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
				//checkExistPoll();
			     }

			 }
	);
	
	
	tfCoordenadas.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfCoordenadas.getTextField().setForeground(Color.red);
	tfCoordenadas.setEditable(false);
	tfCoordenadas.setValue("0 ; 0");
	loadCombos();
	initForm();
	rb2i_a.setSelected(true);
	rb2j_a.setSelected(true);
	tfFecha.setEditable(false);
	tfFecha.setOpaque(true);
	setRbtn2d(0);
	setEnabledSaveButton(false);
	setEnabledDeleteButton(false);
	
	getPrintButton().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickPrint(e);
		}
	    }
	});
	getPrintButton().setToolTipText("Imprimir Informes");
    }

    private void setRbtn2d(int _rbtn) {
	switch (_rbtn)  {
	    case 0: {
		    rb2d.setSelected(false);
		    rb2d_a.setSelected(false);
		    rb2d_b.setSelected(false);
		    rb2d_c.setSelected(false);
		}
		break;
	
	    case 1: {
	            rb2d.setSelected(false);
	            rb2d_a.setSelected(true);
	            rb2d_b.setSelected(false);
	            rb2d_c.setSelected(false);
	        }
	        break;
	    case 2: {
	            rb2d.setSelected(false);
	            rb2d_a.setSelected(false);
	            rb2d_b.setSelected(true);
	            rb2d_c.setSelected(false);
	        }
	        break;
	    case 3: {
	            rb2d.setSelected(false);
	            rb2d_a.setSelected(false);
	            rb2d_b.setSelected(false);
	            rb2d_c.setSelected(true);
	        }
	        break;
	    case 4: {
	            rb2d.setSelected(true);
	            rb2d_a.setSelected(false);
	            rb2d_b.setSelected(false);
	            rb2d_c.setSelected(false);
	        }
	        break;
	}
	
    }

    private void loadCombos(){
	loadComboBarrios();
	loadComboCalles();
	loadComboBarriosForm3();
	loadComboCallesForm3();
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
    
    private void loadComboBarriosForm3() {
	String param = "'" + tf3iFind.getString() + "'";
	cb3i.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb3i.getCombo().updateUI();
    }
    
    private void loadComboCalles(){
	String param = "'" + tf1bFind.getString() + "'";
	cb1b.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb1b.getCombo().updateUI();
    }

    private void loadComboCallesForm3(){
	String param = "'" + tf3jFind.getString() + "'";
	cb3j.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb3j.getCombo().updateUI();
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
	encabezadoEncuesta = new ClassEncabezadoEncuesta();
	setTabs(0);
    }

    private void clearForms(){
	/* Encabezado */
	tfNroEncuestador.setValue(""); tfZonaNro.setValue("");
	tfNroEncuesta.setValue(""); tfFecha.setValue("");
        tfBarcode.setValue("");
	/* Seccion 1 */
	tf1aFind.setValue(""); 	tf1bFind.setValue(""); 	tf1c.setValue("");
	tf1d.setValue("");	tf1e.setValue(""); 	tf1f.setValue(""); tf1g.setValue("");
	tf1h.setValue("");	tf1i.setValue(""); 	tf1j.setValue(""); tf1k.setValue("");
	tf1l.setValue(""); 	tfCoordenadas.setValue("0 ; 0");
	/* Seccion 2 */
	tf2a.setValue(""); 	chk2a.setSelected(false);
	tf2b.setValue(""); 	chk2b.setSelected(false);
	tf2c.setValue(""); 	chk2c.setSelected(false);
	tf1d.setValue(""); 	rb2d.setSelected(false);
	rb2d_a.setSelected(false); rb2d_b.setSelected(false); rb2d_c.setSelected(false); 
	tf2e.setValue(""); tf2f.setValue(""); tf2g.setValue(""); chkefg.setSelected(false); 
	tf2h.setValue(""); 	chk2h.setSelected(false);
	rb2i.setSelected(false); rb2i_a.setSelected(true); rb2i_b.setSelected(false); rb2i_c.setSelected(false); 
	rb2j.setSelected(false); rb2j_a.setSelected(true); rb2j_b.setSelected(false); rb2j_c.setSelected(false); 
	ta2k.setText(""); 	ta2l.setText("");
	/* Seccion 3 */
	tf3a.setValue(""); 	chk3a.setSelected(false);
	tf3b.setValue(""); 	chk3b.setSelected(false);
	rb3c_a.setSelected(true); rb3c_b.setSelected(false); rb3c.setSelected(false);
	tf3d.setValue(""); 	tf3e.setValue(""); chk3de.setSelected(false); 
	tf3f.setValue(""); 	tf3g.setValue(""); tf3h.setValue(""); chk3fgh.setSelected(false); 
	tf3iFind.setValue(""); 
	tf3jFind.setValue("");	tf3k.setValue("");
	tf3l.setValue("");	tf3m.setValue("");
	tf3n.setValue("");	tf3o.setValue("");
	tf3p.setValue("");	tf3q.setValue("");
	tf3r.setValue("");
	/* Seccion 4 */
	tf4a.setValue(""); chk4a.setSelected(false);
	tf4b.setValue(""); chk4b.setSelected(false);
	tf4c.setValue(""); tf4d.setValue(""); chk4cd.setSelected(false);
	tf4e.setValue(""); chk4e.setSelected(false);
	ta5a.setText("");
    }

    private void setTabs(int _tab) {
	switch (_tab)  {
	    case 0: {/* dehabilitar todas las pestañas */
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,false);
		    clearForms();
		    setEnabledForms(0);
		    panelData.setSelectedIndex(0);
		    tfNroEncuestador.getTextField().requestFocus();
		    
		}
		break;
	    case 1: {/* dehabilitar todas las pestañas y dejar habilitada la pestaña 1*/
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);
		    panelData.setEnabledAt(0,true);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,false);
	            setEnabledForms(1);
	            panelData.setSelectedIndex(0);
	        }
	        break;
	    case 2: {/* dehabilitar todas las pestañas y dejar habilitada la pestaña 2 */
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,true);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,false);
	            setEnabledForms(2);
		    panelData.setSelectedIndex(1);
	        }
	        break;
	    case 3: {/* dehabilitar todas las pestañas  y dejar habilitada la pestaña 3*/
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,true);
		    panelData.setEnabledAt(3,false);	    
	            setEnabledForms(3);
	            panelData.setSelectedIndex(2);
	        }
	        break;
	    case 4: {/* dehabilitar todas las pestañas  y dejar habilitada la pestaña 4*/
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);	    
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,true);
	            setEnabledForms(4);
	            panelData.setSelectedIndex(3);
	        }
	        break;
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
		    btnSaveData.setEnabled(true);
		    tfNroEncuesta.setEditable(true);
		    tfZonaNro.setEditable(true);
		    tfNroEncuestador.setEditable(true);
		    tfFecha.setEditable(false);
		    
		    /* SECCION 1 */
		    tf1aFind.setEnabled(false); cb1a.setEnabled(false);
		    tf1a.setEnabled(false); tf1bFind.setEnabled(false);
		    cb1b.setEnabled(false); tf1b.setEnabled(false); 
		    tf1c.setEnabled(false); tf1d.setEnabled(false); 
		    tf1e.setEnabled(false); tf1f.setEnabled(false); 
		    tf1g.setEnabled(false); tf1h.setEnabled(false);
		    tf1i.setEnabled(false); tf1j.setEnabled(false);
		    tf1k.setEnabled(false); tf1l.setEnabled(false);
		    btnSaveDataF1.setEnabled(false);
		}
		break;
	    case 1: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEnabled(false);
	            tfZonaNro.setEnabled(false);
	            tfNroEncuestador.setEnabled(false);
	            tfFecha.setEnabled(false);
	            btnSaveData.setEnabled(false);
	            /* SECCION 1 */
	            tf1aFind.setEnabled(true); cb1a.setEnabled(true);
	            tf1a.setEnabled(true); tf1bFind.setEnabled(true);
	            cb1b.setEnabled(true); tf1b.setEnabled(true); 
		    tf1c.setEnabled(true); tf1d.setEnabled(true);
		    tf1e.setEnabled(true); tf1f.setEnabled(true);
		    tf1g.setEnabled(true); tf1h.setEnabled(true);
		    tf1i.setEnabled(true); tf1j.setEnabled(true);
		    tf1k.setEnabled(true); tf1l.setEnabled(true);
		    btnSaveDataF1.setEnabled(true);
	        }
	        break;
	    case 2: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);
	            btnSaveData.setEnabled(false);
	        }
	        break;
	    case 3: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);
	            btnSaveData.setEnabled(false);
	        }
	        break;
	    case 4: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);
	            btnSaveData.setEnabled(false);
	        }
	        break;
	}
	
    }

    private boolean checkExistPoll() {
	return LibSQL.getBoolean("gea_salta.existeEncuestaComercial",""+ tfNroEncuestador.getInteger() +","+ tfZonaNro.getInteger() +","+ tfNroEncuesta.getInteger());
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (headerDataControl()) {
	        if (!checkExistPoll())  {
		    String params = + tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getInteger() +"','"+ tfNroEncuesta.getInteger() 
                                    +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) +"','"+ tfBarcode.getValue().toString() +"'";
		    encabezadoEncuesta.setIdencuestacomercial(LibSQL.getInt("gea_salta.addEncuestaComercialEncabezado", params));
		    if ( encabezadoEncuesta.getIdencuestacomercial() > 0)  {
			setTabs(1);
		    } else  {
			Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
		    }
		} else {
		    Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +"\nen la zona Nº "+ tfZonaNro.getInteger() + " ya fué registrada", "Error");
		    tfNroEncuestador.getTextField().requestFocus();
		}
	} else {
	    errorForm.showMessage();
	    setFocus(0);
	}
    }

    private boolean headerDataControl() {
	errorForm = new ErrComercios();
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
	} /*else if (tfBarcode.getSize() > 15)  {
	    valor = false;
	    errorForm.setError(errorForm.F0d);
	}*/
	return valor;
    }

    private void btnSaveDataF1_actionPerformed(ActionEvent e) {
	if (seccion1DataControl()) {
	    String params = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf1a.getString() + "','" + tf1b.getString() + "','" + 
			    tf1c.getString() + "','" + tf1d.getString() + "','"+ tf1e.getString() + "','"+ tf1f.getString() + "','"+ tf1g.getString() + "','"+
			    tf1h.getString() + "','"+ tf1i.getString() + "','"+ tf1j.getString() + "','"+ tf1k.getString() + "','"+ tf1l.getString() + "', " + 
			    point.getX() + ", " + point.getY();
	    if (LibSQL.getInt("gea_salta.addEncuestaComercialSeccion1", params) > 0) {
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
    
    private boolean seccion1DataControl() {
	errorForm = new ErrComercios();
	boolean valor = true;
	if (tf1a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1a);
	} else if (tf1b.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1b);
	}/* else if (tf1d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1d);
	} else if (tf1h.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1h);
	}*/
	return valor;
    }

    private String getParam2(){
	String rb2iText = "";
	String rb2jText = "";
	
	if (rb2i.isSelected())  {
	    rb2iText = rb2i.getText();
	} else if (rb2i_a.isSelected())  {
	    rb2iText = rb2i_a.getText();
	} else if (rb2i_b.isSelected())  {
	    rb2iText = rb2i_b.getText();
	} else if (rb2i_c.isSelected())  {
	    rb2iText = rb2i_c.getText();
	}
	
	if (rb2j.isSelected())  {
	    rb2jText = rb2i.getText();
	} else if (rb2j_a.isSelected())  {
	    rb2jText = rb2j_a.getText();
	} else if (rb2j_b.isSelected())  {
	    rb2jText = rb2j_b.getText();
	} else if (rb2j_c.isSelected())  {
	    rb2jText = rb2j_c.getText();
	}
	
	String params2 = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf2a.getString() + "','" + tf2b.getString() + "','" + 
			tf2c.getString() + "','" + tf2d.getString() + "','"+ tf2e.getString() + "','"+ tf2f.getString() + "','"+ tf2g.getString() + "','"+
			tf2h.getString() + "','"+ rb2iText + "','"+ rb2jText + "','"+ ta2k.getText() + "','"+ ta2l.getText() + "'";
	return params2;
    }

    private void btnSaveDataF2_actionPerformed(ActionEvent e) {
	if (seccion2DataControl()) {
	    String params = getParam2();
	    if (LibSQL.getInt("gea_salta.addEncuestaComercialSeccion2", params) > 0) {
		setTabs(3);
	    } else {
		Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	    }
	} else {
	    errorForm.showMessage();
	    setFocus(2);
	}
    }
    
    private boolean seccion2DataControl() {
	errorForm = new ErrComercios();
	boolean valor = true;
	if (tf2a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2a);
	} else if (tf2b.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2b);
	} else if (tf2c.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2c);
	} else if (tf2d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2d);
	} else if (tf2e.getString().equals("") && !chkefg.isSelected())  {
	    valor = false;
	    errorForm.setError(errorForm.F2e);
	} else if (tf2h.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2h);
	}
	return valor;
    }
    
    private void chk2a_actionPerformed(ActionEvent e) {
	if (chk2a.isSelected())  {
	    tf2a.setValue(chk2a.getText());
	} else  {
	    tf2a.setValue("");
	}
    }
    
    private void chk2b_actionPerformed(ActionEvent e){
	if (chk2b.isSelected())  {
	    tf2b.setValue(chk2b.getText());
	} else  {
	    tf2b.setValue("");
	}
    }
    
    private void chk2c_actionPerformed(ActionEvent e){
	if (chk2c.isSelected())  {
	    tf2c.setValue(chk2c.getText());
	} else  {
	    tf2c.setValue("");
	}
    }
    
    private void chk2h_actionPerformed(ActionEvent e){
	if (chk2h.isSelected())  {
	    tf2h.setValue(chk2h.getText());
	} else  {
	    tf2h.setValue("");
	}
    }
    
    private void rb2d_a_actionPerformed(ActionEvent e){
	if (rb2d_a.isSelected())  {
	    tf2d.setValue(rb2d_a.getText());
	    setRbtn2d(1);
	} else  {
	    tf2d.setValue("");
	}
    }
    
    private void rb2d_b_actionPerformed(ActionEvent e){
	if (rb2d_b.isSelected())  {
	    tf2d.setValue(rb2d_b.getText());
	    setRbtn2d(2);
	} else  {
	    tf2d.setValue("");
	}
    }
    
    private void rb2d_c_actionPerformed(ActionEvent e){
	if (rb2d_c.isSelected())  {
	    tf2d.setValue(rb2d_c.getText());
	    setRbtn2d(3);
	} else  {
	    tf2d.setValue("");
	}
    }
    
    private void rb2d_actionPerformed(ActionEvent e){
	if (rb2d.isSelected())  {
	    tf2d.setValue(rb2d.getText());
	    setRbtn2d(4);
	} else  {
	    tf2d.setValue("");
	}
    }
    
    private void chk2efg_actionPerformed(ActionEvent e){
	if (chkefg.isSelected())  {
	    tf2e.setValue(chkefg.getText());
	    tf2f.setValue(chkefg.getText());
	    tf2g.setValue(chkefg.getText());
	} else  {
	    tf2e.setValue("");
	    tf2f.setValue("");
	    tf2g.setValue("");
	}
    }
    
    private void chk3a_actionPerformed(ActionEvent e){
	if (chk3a.isSelected())  {
	    tf3a.setValue(chk3a.getText());
	} else  {
	    tf3a.setValue("");
	}
    }
    
    private void chk3b_actionPerformed(ActionEvent e){
	if (chk3b.isSelected())  {
	    tf3b.setValue(chk3b.getText());
	} else  {
	    tf3b.setValue("");
	}
    }
    
    private void chk3de_actionPerformed(ActionEvent e){
	if (chk3de.isSelected())  {
	    tf3d.setValue(chk3de.getText());
	    tf3e.setValue(chk3de.getText());
	} else  {
	    tf3d.setValue("");
	    tf3e.setValue("");
	}
    }
    
    private void chk3fgh_actionPerformed(ActionEvent e){
	if (chk3fgh.isSelected())  {
	    tf3f.setValue(chk3fgh.getText());
	    tf3g.setValue(chk3fgh.getText());
	    tf3h.setValue(chk3fgh.getText());
	} else  {
	    tf3f.setValue("");
	    tf3g.setValue("");
	    tf3h.setValue("");
	}
    }
    private void cb3i_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf3i.setValue(cb3i.getSelectedItem().toString());
	}
    }
    
    private void cb3j_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf3j.setValue(cb3j.getSelectedItem().toString());
	}
    }
    
    private String getParam3(){
	String rb3cText = "";
	
	if (rb3c.isSelected())  {
	    rb3cText = rb3c.getText();
	} else if (rb3c_a.isSelected())  {
	    rb3cText = rb3c_a.getText();
	} else if (rb3c_b.isSelected())  {
	    rb3cText = rb3c_b.getText();
	}
	
	String params3 = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf3a.getString() + "','" + tf3b.getString() + "','" + 
			rb3cText + "','" + tf3d.getString() + "','"+ tf3e.getString() + "','"+ tf3f.getString() + "','"+ tf3g.getString() + "','"+
			tf3h.getString() + "','"+ tf3i.getString() + "','"+ tf3j.getString() +"','"+  tf3k.getString() + "','"+ tf3l.getString() + "','"+
			tf3m.getString() + "','"+ tf3n.getString() + "','"+ tf3o.getString() +"','"+  tf3p.getString() + "','"+ tf3q.getString() + "','"+
			tf3r.getString() + "'";
			
	return params3;
    }


    private void btnSaveDataF3_actionPerformed(ActionEvent e) {
	if (seccion3DataControl()) {
	    String params = getParam3();
	    if (LibSQL.getInt("gea_salta.addEncuestaComercialSeccion3", params) > 0) {
		setTabs(4);
	    } else {
		Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	    }
	} else {
	    errorForm.showMessage();
	    setFocus(3);
	}
    }
    
    private boolean seccion3DataControl() {
	errorForm = new ErrComercios();
	boolean valor = true;
	if (tf3a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3a);
	} else if (tf3b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F3b);
	} else if (tf3d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3d);
	} else if (tf3e.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3e);
	} else if (tf3f.getString().equals("") && !chk3fgh.isSelected())  {
	    valor = false;
	    errorForm.setError(errorForm.F3f);
	} else if (tf3i.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3i);
	} else if (tf3j.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3j);
	}/* else if (tf3l.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3l);
	}*/
	return valor;
    }
    
    private void chk4a_actionPerformed(ActionEvent e){
	if (chk4a.isSelected())  {
	    tf4a.setValue(chk4a.getText());
	} else  {
	    tf4a.setValue("");
	}
    }
    
    private void chk4b_actionPerformed(ActionEvent e){
	if (chk4b.isSelected())  {
	    tf4b.setValue(chk4b.getText());
	} else  {
	    tf4a.setValue("");
	}
    }
    
    private void xhk4cd_actionPerformed(ActionEvent e){
	if (chk4cd.isSelected())  {
	    tf4c.setValue(chk4cd.getText());
	    tf4d.setValue(chk4cd.getText());
	} else  {
	    tf4c.setValue(chk4cd.getText());
	    tf4d.setValue(chk4cd.getText());
	}
    }
    
    private void chk4e_actionPerformed(ActionEvent e){
	if (chk4e.isSelected())  {
	    tf4e.setValue(chk4e.getText());
	} else  {
	    tf4e.setValue("");
	}
    }
    
    private String getParam4(){

	String params4 = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf4a.getString() + "','" + tf4b.getString() + "','" + 
			tf4c.getString() + "','"+ tf4d.getString() + "','"+ tf4e.getString() + "','"+ ta5a.getText() + "'";
	return params4;
    }


    private void btnSaveDataF4_actionPerformed(ActionEvent e) {
	if (seccion4DataControl()) {
	    String params = getParam4();
	    if (LibSQL.getInt("gea_salta.addEncuestaComercialSeccion4", params) > 0) {
		encabezadoEncuesta = new ClassEncabezadoEncuesta();
		setTabs(0);
	    } else {
		Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	    }
	} else {
	    errorForm.showMessage();
	    setFocus(4);
	}
    }
    
    private boolean seccion4DataControl() {
	errorForm = new ErrComercios();
	boolean valor = true;
	if (tf4a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F4a);
	} else if (tf4b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F4b);
	} else if (tf4c.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F4c);
	} else if (tf4d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F4d);
	} else if (tf4e.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F4e);
	}
	return valor;
    }

    private void tf2d_mouseClicked(MouseEvent e) {
	setRbtn2d(0);
	tf2d.setValue("");
    }
    
    
    
    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    point = (ESRIPoint)_contentObject;
	    tfCoordenadas.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	    tf1k.fetchCatastro(point);
	} else {
	    point = null;
	}
    }

/*    @Override
    public Object getContentObject() {
	return adminyServ;
    }
*/

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
		    } else if (errorForm.getError() == errorForm.F1l) {
		        tf1l.getTextField().requestFocus();
		    }
		}
		break;
	    case 2: {
	            if (errorForm.getError() == errorForm.F2a)  {
	                tf2a.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2b) {
	                tf2b.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2c) {
	                tf2c.getTextField().requestFocus();
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
	                ta2k.getTextArea().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2l) {
	                ta2l.getTextArea().requestFocus();
	            }
	        }
	        break;
	    case 3: {
	            if (errorForm.getError() == errorForm.F3a)  {
	                tf3a.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3b) {
	                tf3b.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3d) {
	                tf3d.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3e) {
	                tf3e.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3f) {
	                tf3f.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3g) {
	                tf3g.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3h) {
	                tf3h.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3i) {
	                tf3iFind.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3j) {
	                tf3jFind.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3k) {
	                tf3k.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3l) {
	                tf3l.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3m) {
	                tf3m.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3n) {
	                tf3n.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3o) {
	                tf3o.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3p) {
	                tf3p.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3q) {
	                tf3q.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3r) {
	                tf3r.getTextField().requestFocus();
	            }
	        }
	        break;
	    case 4: {
	            if (errorForm.getError() == errorForm.F4a)  {
	                tf4a.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F4b) {
	                tf4b.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F4d) {
	                tf4d.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F4e) {
	                tf4e.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F5a) {
	                ta5a.getTextArea().requestFocus();
	            }
	        }
	        break;
	}
    }
    
    public void printReport() {
	/*BasicReport report = new BasicReport(FormComercios.class.getResource("xml/EncuestasComercialesReport.xml"));
	report.addTableModel("gea_salta.xmlGetEncuestasComerciales", "");
	report.doReport();*/
	
    }
    
    private void clickPrint(MouseEvent e){
	/*PopUpImprimirReportesComercios popupPrint = new  PopUpImprimirReportesComercios();
	int x = (int)e.getPoint().getX();
	int y = (int)e.getPoint().getY() - popupPrint.getAlto();
	popupPrint.show(getPrintButton(),x,y);*/
	//Grabar el contenido del storedProc en un archivo:
	//gea_salta.xmlGetEncuestasComerciales
	super.doCSVReport("Encuestas Comerciales", "gea_salta.xmlGetEncuestasComerciales()");
    }


}
