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
 * FormularioComercios.java
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

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.apps.gaia.components.GaiaCatastroInputPanel;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassCensoComercial2009Encabezado;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassEncabezadoEncuesta;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses.ErrCensoComercial2009;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.sql.LibSQL;


public class FormularioComercios extends GaiaPrimitiveForm {
    
    private BasicPanel contentPanel = new BasicPanel();
    private BasicTabbedPane panelData = new BasicTabbedPane();
    private BasicPanel jpCabecera = new BasicPanel();
    private ESRIPoint point = null;
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.INTEGER, "Nº Formulario", false);
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Fecha", false);
    private TFInput tfBarcode = new TFInput(DataTypes.STRING, "Código de Barras", false);

    /** 1. Ubicación geográfica */
    private BasicPanel jpUbicacionGeografica = new BasicPanel();
    private CBInput cb1a = new CBInput(0, "BARRIO", false);
    private CBInput cb1b = new CBInput(0, "CALLE", false);
    private TFInput tf1c = new TFInput(DataTypes.STRING, "CÓDIGO POSTAL", false);
    private TFInput tf1d = new TFInput(DataTypes.STRING, "1d. NUMERACIÓN", false);
    private TFInput tf1e = new TFInput(DataTypes.STRING, "1e. PISO", false);
    private TFInput tf1f = new TFInput(DataTypes.STRING, "DPTO.", false);
    private TFInput tf1g = new TFInput(DataTypes.STRING, "1g. SECT/BLOQ", false);
    private TFInput tf1h = new TFInput(DataTypes.STRING, "1h. MANZANA", false);
    private TFInput tf1i = new TFInput(DataTypes.STRING, "1i. CASA", false);
    private TFInput tf1j = new TFInput(DataTypes.STRING, "1j. MEDIDOR", false);
    //private TFInput tf1k = new TFInput(DataTypes.INTEGER, "1k. Nº CÉDULA PARCELARIA", false);
    private GaiaCatastroInputPanel tf1k  = new GaiaCatastroInputPanel();
    private TFInput tf1l = new TFInput(DataTypes.STRING, "1l. (Si aplica) NOMBRE DEL CENTRO COMERCIAL U OTRO", false);
    private TFInput tfCoordenadas = new TFInput(DataTypes.STRING,"COORDENADAS",false);

    /** 2.1. Datos del Local - Primera parte */
    private BasicPanel jpDatosLocal = new BasicPanel();
    
    private TFInput tf2a = new TFInput(DataTypes.STRING, "2a. NOMBRE FANTASÍA", false);

    private TFInput tf2b = new TFInput(DataTypes.STRING, "2b. C.U.I.T.", false);

    private TFInput tf2c = new TFInput(DataTypes.STRING, "2c. Nº DE PADRÓN COMERCIAL", false);

    private CBInput cbEstadoHabilitacion = new CBInput(0, "ESTADO DE HABILITACIÓN", false);
    private CBInput cbCaracteristicasLocal = new CBInput(0, "CARACTERÍSTICAS DEL LOCAL", false);
    private CBInput cbTipoRubro = new CBInput(0, "RUBRO", false);
    private CBInput cbDescripcionRubro = new CBInput(0, "DESCRIPCIÓN DEL RUBRO", false);
    private CBInput cbActividad = new CBInput(0, "ACTIVIDAD", false);

    //private ButtonGroup grupo2def = new ButtonGroup();

    //private ButtonGroup gruporb2g = new ButtonGroup();

    //private ButtonGroup grupochk2h = new ButtonGroup();

    private TFInput tf2i1_2 = new TFInput(DataTypes.STRING,"CÓDIGO DE ACTIVIDAD",false);
    
    private int[] sizeColumnList = { 86 , 111 , 52 , 58 , 44 , 67 , 215 };
    private Vector dataRow = new Vector();

    private Vector headerList = new Vector();


    /** 2.2 Datos del Local - Continuacion*/
    private BasicPanel jpDatosLocalContinuacion = new BasicPanel();

    private BasicLabel lbl2k = new BasicLabel("2k. DOMICILIOS DE SUCURSALES, DEPOSITOS, ADMINISTRACION Y/O OTROS");
    private BasicLabel lbl2k_a = new BasicLabel("ID");
    private BasicLabel lbl2k_b = new BasicLabel("DOMICILIO - CALLE, NÚMERO, BARRIO, ETC");
    private BasicLabel lbl2k_c = new BasicLabel("<html><p align=center>Nº PADRÓN COMERCIAL U OTRO</html>");
    private BasicLabel lbl2k_d = new BasicLabel("<html><p align=center>FECHA INICIO ACT. COMERC.</html>");
    private BasicLabel lbl2k_1 = new BasicLabel("2k1");
    private BasicTextField tf2k1_a = new BasicTextField();
    private BasicTextField tf2k1_b = new BasicTextField();
    private BasicTextField tf2k1_c = new BasicTextField();
    private BasicLabel lbl2k_2 = new BasicLabel("2k2");
    private BasicTextField tf2k2_a = new BasicTextField();
    private BasicTextField tf2k2_b = new BasicTextField();
    private BasicTextField tf2k2_c = new BasicTextField();
    private BasicLabel lbl2k_3 = new BasicLabel("2k3");
    private BasicTextField tf2k3_a = new BasicTextField();
    private BasicTextField tf2k3_b = new BasicTextField();
    private BasicTextField tf2k3_c = new BasicTextField();
    private BasicLabel lbl2k_4 = new BasicLabel("2k4");
    private BasicTextField tf2k4_a = new BasicTextField();
    private BasicTextField tf2k4_b = new BasicTextField();
    private BasicTextField tf2k4_c = new BasicTextField();
    
    private BasicLabel lbl2lEncabezado = new BasicLabel("SUCURSAL CENTRAL");
    private BasicLabel lbl2l = new BasicLabel("2 l. EN EL MISMO DOMICILIO CONSIGNADO");
    private BasicRadioButton rb2l_a = new BasicRadioButton("SI");
    private BasicRadioButton rb2l_b = new BasicRadioButton("NO");
    private ButtonGroup grupo2l = new ButtonGroup();

    private TFInput tf2m = new TFInput(DataTypes.STRING,"TELÉFONO",false);
    private TFInput tf2n = new TFInput(DataTypes.STRING,"CELULAR",false);
    private TFInput tf2nn = new TFInput(DataTypes.STRING,"FAX",false);

    private TFInput tf2o = new TFInput(DataTypes.STRING,"E-MAIL",false);

    private TFInput tf2pFind = new TFInput(DataTypes.STRING,"BUSCAR",false);
    private CBInput cb2p = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf2p = new TFInput(DataTypes.STRING,"2p. BARRIO",false);

    private TFInput tf2qFind = new TFInput(DataTypes.STRING,"BUSCAR",false);
    private CBInput cb2q = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf2q = new TFInput(DataTypes.STRING,"2q. CALLE",false);

    private TFInput tf2r = new TFInput(DataTypes.STRING,"2r. NUMERACIÓN",false);
    private TFInput tf2s = new TFInput(DataTypes.STRING,"2s. PISO",false);
    private TFInput tf2t = new TFInput(DataTypes.STRING,"2t. Nº/LETRA",false);
    private TFInput tf2u = new TFInput(DataTypes.STRING,"2u. SECT/BLOQ",false);
    private TFInput tf2v = new TFInput(DataTypes.STRING,"2v. MANZANA",false);
    private TFInput tf2w = new TFInput(DataTypes.STRING,"2w. CASA",false);
    private TFInput tf2x = new TFInput(DataTypes.STRING,"2x MEDIDOR",false);
    
    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private JSeparator jSeparator3 = new JSeparator();
    

     /** 3. Datos del Titular - Persona Fisica */
    private BasicPanel jpDatosPersonaFisica = new BasicPanel();

    private TFInput tf3a = new TFInput(DataTypes.STRING, "3a. APELLIDO/S", false);

    private TFInput tf3b = new TFInput(DataTypes.STRING, "3b. NOMBRE/S", false);

    private TFInput tf3c = new TFInput(DataTypes.STRING, "3c. TIPO DE DOCUMENTO", false);
    
    private TFInput tf3d = new TFInput(DataTypes.STRING, "3d. Nº", false);

    private BasicLabel lbl3e = new BasicLabel("3e. SEXO (F/M)");
    private BasicTextField tf3e = new BasicTextField();
    
    private TFInput tf3f = new TFInput(DataTypes.STRING, "3f. NACIONALIDAD (País de Origen)", false);

    private BasicLabel lbl3g = new BasicLabel("3g. VIVE EN EL MISMO DOMICILIO CONSIGNADO:");
    private BasicRadioButton rb3g_a = new BasicRadioButton("SI");
    private BasicRadioButton rb3g_b = new BasicRadioButton("NO");
    private ButtonGroup grupo3g = new ButtonGroup();
        
    private TFInput tf3h = new TFInput(DataTypes.STRING, "3h. TELÉFONO FIJO", false);
    private TFInput tf3i = new TFInput(DataTypes.STRING, "3i. FAX", false);
    
    private TFInput tf3j = new TFInput(DataTypes.STRING, "3j. CELULAR", false);


    private TFInput tf3k = new TFInput(DataTypes.STRING, "3k. E-MAIL", false);

    private TFInput tf3lFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3l = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf3l = new TFInput(DataTypes.STRING, "3l. BARRIO", false);

    private TFInput tf3mFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3m = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf3m = new TFInput(DataTypes.STRING, "3m. CALLE", false);

    private TFInput tf3n = new TFInput(DataTypes.STRING, "3n. NUMERACIÓN", false);
    private TFInput tf3nn = new TFInput(DataTypes.STRING, "3ñ. PISO", false);
    private TFInput tf3o = new TFInput(DataTypes.STRING, "3o. Nº o LETRA", false);
    private TFInput tf3p = new TFInput(DataTypes.STRING, "3p. SECT/BLOQ", false);
    private TFInput tf3q = new TFInput(DataTypes.STRING, "3q. MANZANA", false);
    private TFInput tf3r = new TFInput(DataTypes.STRING, "3r. CASA", false);
    private TFInput tf3s = new TFInput(DataTypes.STRING, "3s. MEDIDOR", false);
    
    
    /** 4. Datos del Titular - Persona Juridica */
    private BasicPanel jpDatosPersonaJuridica = new BasicPanel();
    private TFInput tf4a = new TFInput(DataTypes.STRING, "4a. RAZÓN SOCIAL", false);
    private BasicCheckBox chk4a = new BasicCheckBox("NP/NS/NC");
    private BasicLabel lbl4b = new BasicLabel("4b. NATURALEZA JURÍDICA DE LA ENTIDAD (Marcar con una cruz)");
    private CBInput cb4b = new CBInput(0, "", false);
    private TFInput tf4b = new TFInput(DataTypes.STRING, "", false);
    private BasicLabel lbl4c = new BasicLabel("4c. COMPONENTES DE LA SOCIEDAD O AUTORIDAD EN EJERCICIO");
    private BasicLabel lbl4c_a = new BasicLabel("Apellido/s");
    private BasicLabel lbl4c_b = new BasicLabel("Nombre/s");
    private BasicLabel lbl4c_c = new BasicLabel("Carácter");
    private BasicLabel lbl4c_d = new BasicLabel("Resid. en Arg.");
    private BasicLabel lbl4c_e = new BasicLabel("T. de Doc.");
    private BasicLabel lbl4c_f = new BasicLabel("Número");
    private BasicLabel lbl4c_g = new BasicLabel("C.U.I.T.");
    
    private BasicTextField tf4c1_a = new BasicTextField();
    private BasicTextField tf4c1_b = new BasicTextField();
    private BasicTextField tf4c1_c = new BasicTextField();
    private BasicTextField tf4c1_d = new BasicTextField();
    private BasicTextField tf4c1_e = new BasicTextField();
    private BasicTextField tf4c1_f = new BasicTextField();
    private BasicTextField tf4c1_g = new BasicTextField();
    
    private BasicTextField tf4c2_a = new BasicTextField();
    private BasicTextField tf4c2_b = new BasicTextField();
    private BasicTextField tf4c2_c = new BasicTextField();
    private BasicTextField tf4c2_d = new BasicTextField();
    private BasicTextField tf4c2_e = new BasicTextField();
    private BasicTextField tf4c2_f = new BasicTextField();
    private BasicTextField tf4c2_g = new BasicTextField();
    
    private BasicTextField tf4c3_a = new BasicTextField();
    private BasicTextField tf4c3_b = new BasicTextField();
    private BasicTextField tf4c3_c = new BasicTextField();
    private BasicTextField tf4c3_d = new BasicTextField();
    private BasicTextField tf4c3_e = new BasicTextField();
    private BasicTextField tf4c3_f = new BasicTextField();
    private BasicTextField tf4c3_g = new BasicTextField();
    
    private BasicTextField tf4c4_a = new BasicTextField();
    private BasicTextField tf4c4_b = new BasicTextField();
    private BasicTextField tf4c4_c = new BasicTextField();
    private BasicTextField tf4c4_d = new BasicTextField();
    private BasicTextField tf4c4_e = new BasicTextField();
    private BasicTextField tf4c4_f = new BasicTextField();
    private BasicTextField tf4c4_g = new BasicTextField();
    
    private BasicTextField tf4c5_a = new BasicTextField();
    private BasicTextField tf4c5_b = new BasicTextField();
    private BasicTextField tf4c5_c = new BasicTextField();
    private BasicTextField tf4c5_d = new BasicTextField();
    private BasicTextField tf4c5_e = new BasicTextField();
    private BasicTextField tf4c5_f = new BasicTextField();
    private BasicTextField tf4c5_g = new BasicTextField();
    
    private JSeparator jSeparator4 = new JSeparator();
    private JSeparator jSeparator5 = new JSeparator();
    

    /** 5. Datos de la persona que suministra la información */
    private BasicPanel jpDatosSuministrador = new BasicPanel();
    
    private BasicCheckBox chkSelTodos5 = new BasicCheckBox("Sel. todos");
    private TFInput tf5a = new TFInput(DataTypes.STRING, "5a. APELLIDO/S", false);
    private BasicCheckBox chk5a = new BasicCheckBox("NP/NS/NC");
    private TFInput tf5b = new TFInput(DataTypes.STRING, "5b. NOMBRE/S", false);
    private BasicCheckBox chk5b = new BasicCheckBox("NP/NS/NC");
    private TFInput tf5c = new TFInput(DataTypes.STRING, "5c. TIPO DE DOCUMENTO", false);
    private TFInput tf5d = new TFInput(DataTypes.STRING, "5d. Nº", false);
    private BasicCheckBox chk5cd = new BasicCheckBox("NP/NS/NC");
    private BasicLabel lbl5e = new BasicLabel("5e. SEXO (F/M)");
    private BasicTextField tf5e = new BasicTextField();
    private TFInput tf5f = new TFInput(DataTypes.STRING, "5f. NACIONALIDAD (País de Origen)", false);
    private BasicCheckBox chk5f = new BasicCheckBox("NP/NS/NC");
    private BasicLabel lbl5g = new BasicLabel("5g. VIVE EN EL MISMO DOMICILIO CONSIGNADO:");
    private BasicRadioButton rb5g_a = new BasicRadioButton("SI");
    private BasicRadioButton rb5g_b = new BasicRadioButton("NO");
    private BasicCheckBox chk5g = new BasicCheckBox("NP/NS/NC");
    private ButtonGroup grupo5g = new ButtonGroup();
    private BasicLabel lbl6a = new BasicLabel("6. OBSERVACIONES");
    private JArea ta6a = new JArea();
    
    private SaveDataButton btnSaveData = new SaveDataButton();
    private SaveDataButton btnSaveDataF1 = new SaveDataButton();
    private SaveDataButton btnSaveDataF2b = new SaveDataButton();
    private SaveDataButton btnSaveDataF3 = new SaveDataButton();
    private SaveDataButton btnSaveDataF4 = new SaveDataButton();
    private SaveDataButton btnSaveDataF5 = new SaveDataButton();


    private ClassEncabezadoEncuesta encabezadoEncuesta;
    private ErrCensoComercial2009 errorForm;
    private ClassCensoComercial2009Encabezado encabezadoCenso;
    private boolean isEdit = false;
    private int idEncuestaComercial = -1;
    private String datosEncabezadoOriginal = "";

    private JButton jButton1 = new JButton();

    public FormularioComercios() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public FormularioComercios(int _idEncuestaComercial) {
	isEdit = true;
	idEncuestaComercial = _idEncuestaComercial;
	GaiaEnvironment.formsFrame.setClosable(false);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("Comercios");
	contentPanel.setBounds(new Rectangle(10, 10, 750, 595));
	contentPanel.setPreferredSize(new Dimension(710, 595));
	contentPanel.setSize(new Dimension(712, 595));
	this.setSize(new Dimension(805, 655));
	this.setPreferredSize(new Dimension(734, 655));
        tfZonaNro.setBounds(new Rectangle(10, 5, 80, 35));
        tfZonaNro.setSize(new Dimension(80, 35));
        tfNroEncuesta.setBounds(new Rectangle(100, 5, 105, 35));
        tfNroEncuesta.setSize(new Dimension(105, 35));
        tfFecha.setBounds(new Rectangle(215, 5, 95, 35));
	jpCabecera.setLayout(null);
        jpCabecera.add(tfBarcode, null);
        jpCabecera.add(btnSaveData, null);
        jpCabecera.add(tfZonaNro, null);
        jpCabecera.add(tfNroEncuesta, null);
        jpCabecera.add(tfFecha, null);
        jpCabecera.setPreferredSize(new Dimension(1, 50));
        cb1a.setBounds(new Rectangle(10, 165, 270, 35));
        tf1c.setBounds(new Rectangle(515, 205, 105, 35));
	tf1d.setBounds(new Rectangle(545, 165, 110, 35));
	tf1e.setBounds(new Rectangle(10, 205, 55, 35));
	tf1f.setBounds(new Rectangle(70, 205, 95, 35));
	tf1g.setBounds(new Rectangle(170, 205, 95, 35));
	tf1h.setBounds(new Rectangle(270, 205, 90, 35));
	tf1i.setBounds(new Rectangle(365, 205, 60, 35));
	tf1j.setBounds(new Rectangle(430, 205, 80, 35));
	tf1k.setBounds(new Rectangle(10, 245, 170, 35));
	tf1l.setBounds(new Rectangle(185, 245, 440, 35));
	jpUbicacionGeografica.setLayout(null);
        jpUbicacionGeografica.add(tfCoordenadas, null);
        jpUbicacionGeografica.add(btnSaveDataF1, null);
        jpUbicacionGeografica.add(cb1a, null);
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
        jpUbicacionGeografica.add(jButton1, null);
        jpUbicacionGeografica.add(cbActividad, null);
        jpUbicacionGeografica.add(cbDescripcionRubro, null);
        jpUbicacionGeografica.add(cbTipoRubro, null);
        jpUbicacionGeografica.add(cbCaracteristicasLocal, null);
        jpUbicacionGeografica.add(cbEstadoHabilitacion, null);
        jpUbicacionGeografica.add(tf2a, null);
        jpUbicacionGeografica.add(tf2b, null);
        jpUbicacionGeografica.add(tf2c, null);

        jpUbicacionGeografica.add(tf2i1_2, null);
        jpUbicacionGeografica.add(tf2o, null);
        jpUbicacionGeografica.add(tf2nn, null);
        jpUbicacionGeografica.add(tf2n, null);
        jpUbicacionGeografica.add(tf2m, null);
        jpUbicacionGeografica.add(lbl2lEncabezado, null);
        jpUbicacionGeografica.add(cb2p, null);
        tf2a.setBounds(new Rectangle(10, 10, 505, 35));

        tf2b.setBounds(new Rectangle(520, 10, 140, 35));
        tf2c.setBounds(new Rectangle(10, 50, 195, 35));
        /*tf2d.addMouseListener(new MouseListener() {
		public void
	}
	);*/
        /*tf2d.getTextField().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    tf2d_mouseClicked(e);
		}
	    }
	);*/
        tf2i1_2.setBounds(new Rectangle(385, 90, 145, 35));
        /*rb2d.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rb2d_a_actionPerformed(e);
		}
	    }
	);*/
        /*rb2e.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rb2d_b_actionPerformed(e);
		}
	    }
	);*/
        /*rb2f.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    rb2d_c_actionPerformed(e);
		}
	    }
	);*/
        /*grupo2def.add(rb2d);
	grupo2def.add(rb2e);
	grupo2def.add(rb2f);*/
        /*grupochk2h.add(chk2h);
	grupochk2h.add(rb2h_a);
	grupochk2h.add(rb2h_b);
	grupochk2h.add(rb2h_c);*/
        /*gruporb2g.add(chk2g);
	gruporb2g.add(rb2g_a);
	gruporb2g.add(rb2g_b);
	gruporb2g.add(rb2g_c);*/
        grupo2l.add(rb2l_a);
        grupo2l.add(rb2l_b);
        grupo3g.add(rb3g_a);
        grupo3g.add(rb3g_b);
        grupo5g.add(rb5g_a);
        grupo5g.add(rb5g_b);
        grupo5g.add(chk5g);
        //lbl2i.setBounds(new Rectangle(10, 235, 240, 15));


        jpUbicacionGeografica.setLayout(null);
        //jpDatosLocal.add(lbl2i, null);
        //jpDatosLocal.add(lbl2j, null);


        /*
	jpDatosLocal.setLayout(null);
        jpDatosLocal.add(jButton1, null);
        jpDatosLocal.add(listPanelRubro, null);
        jpDatosLocal.add(cbActividad, null);
        jpDatosLocal.add(cbDescripcionRubro, null);
        jpDatosLocal.add(cbTipoRubro, null);
        jpDatosLocal.add(cbCaracteristicasLocal, null);
        jpDatosLocal.add(cbEstadoHabilitacion, null);
        //jpDatosLocal.add(lbl2i, null);
	//jpDatosLocal.add(lbl2j, null);

        jpDatosLocal.add(btnSaveDataF2a, null);
        jpDatosLocal.add(tf2a, null);
        jpDatosLocal.add(tf2b, null);
        jpDatosLocal.add(tf2c, null);
        jpDatosLocal.add(tf2i1_2, null);*/
        jpDatosLocalContinuacion.setLayout(null);
        jpDatosLocalContinuacion.add(btnSaveDataF2b, null);
	jpDatosLocalContinuacion.add(tf2k4_c, null);
	jpDatosLocalContinuacion.add(tf2k3_c, null);
	jpDatosLocalContinuacion.add(tf2k2_c, null);
	jpDatosLocalContinuacion.add(tf2k4_b, null);
	jpDatosLocalContinuacion.add(tf2k3_b, null);
	jpDatosLocalContinuacion.add(tf2k2_b, null);
	jpDatosLocalContinuacion.add(jSeparator3, null);
	jpDatosLocalContinuacion.add(tf2k4_a, null);
	jpDatosLocalContinuacion.add(tf2k3_a, null);
	jpDatosLocalContinuacion.add(tf2k2_a, null);
	jpDatosLocalContinuacion.add(tf2k1_b, null);
	jpDatosLocalContinuacion.add(tf2k1_c, null);
	jpDatosLocalContinuacion.add(tf2k1_a, null);
	jpDatosLocalContinuacion.add(lbl2k_4, null);
	jpDatosLocalContinuacion.add(lbl2k_3, null);
	jpDatosLocalContinuacion.add(lbl2k_2, null);
	jpDatosLocalContinuacion.add(lbl2k_1, null);
	jpDatosLocalContinuacion.add(jSeparator2, null);
	jpDatosLocalContinuacion.add(jSeparator1, null);
	jpDatosLocalContinuacion.add(lbl2k_d, null);
	jpDatosLocalContinuacion.add(tf2s, null);
	jpDatosLocalContinuacion.add(tf2r, null);
	jpDatosLocalContinuacion.add(tf2x, null);
	jpDatosLocalContinuacion.add(tf2w, null);
	jpDatosLocalContinuacion.add(tf2v, null);
	jpDatosLocalContinuacion.add(tf2u, null);
	jpDatosLocalContinuacion.add(tf2t, null);
	jpDatosLocalContinuacion.add(cb2q, null);
        jpDatosLocalContinuacion.add(tf2qFind, null);
	jpDatosLocalContinuacion.add(tf2pFind, null);
        jpDatosLocalContinuacion.add(lbl2k_c, null);
	jpDatosLocalContinuacion.add(lbl2k_b, null);
	jpDatosLocalContinuacion.add(lbl2k_a, null);

	jpDatosLocalContinuacion.add(rb2l_b, null);
	jpDatosLocalContinuacion.add(rb2l_a, null);
	jpDatosLocalContinuacion.add(lbl2l, null);
        jpDatosLocalContinuacion.add(tf2q, null);
	jpDatosLocalContinuacion.add(tf2p, null);
        /*
        jpDatosLocalContinuacion.add(tf2o, null);
        jpDatosLocalContinuacion.add(tf2nn, null);
        jpDatosLocalContinuacion.add(tf2n, null);
        jpDatosLocalContinuacion.add(tf2m, null);
        */
        jpDatosLocalContinuacion.add(lbl2k, null);
	tf3b.setBounds(new Rectangle(10, 45, 505, 35));

        tf3a.setBounds(new Rectangle(10, 5, 505, 35));
        tf3c.setBounds(new Rectangle(10, 90, 160, 35));
	tf3d.setBounds(new Rectangle(175, 90, 165, 35));
        tf3h.setBounds(new Rectangle(10, 210, 125, 35));
	tf3i.setBounds(new Rectangle(140, 210, 125, 35));
	tf3j.setBounds(new Rectangle(270, 210, 145, 35));
        tf3l.setBounds(new Rectangle(360, 290, 230, 35));
	tf3l.setSize(new Dimension(230, 35));
	tf3m.setBounds(new Rectangle(360, 330, 140, 35));
	tf3m.setSize(new Dimension(140, 35));
	tf3n.setBounds(new Rectangle(10, 375, 110, 35));
	tf3nn.setBounds(new Rectangle(125, 375, 60, 35));
	tf3o.setBounds(new Rectangle(190, 375, 95, 35));
	tf3p.setBounds(new Rectangle(290, 375, 95, 35));
	tf3q.setBounds(new Rectangle(390, 375, 90, 35));
	tf3r.setBounds(new Rectangle(485, 375, 60, 35));
	tf3s.setBounds(new Rectangle(550, 375, 85, 35));
	tf3lFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboBarriosForm3();
			    
		    }
		});
	tf3mFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboCallesForm3();
			    
		    }
		});
	jpDatosPersonaFisica.setLayout(null);
        jpDatosPersonaFisica.add(tf3k, null);
	jpDatosPersonaFisica.add(rb3g_b, null);
	jpDatosPersonaFisica.add(rb3g_a, null);
	jpDatosPersonaFisica.add(lbl3g, null);
        jpDatosPersonaFisica.add(tf3f, null);
	jpDatosPersonaFisica.add(lbl3e, null);
	jpDatosPersonaFisica.add(tf3e, null);
	jpDatosPersonaFisica.add(cb3m, null);
	jpDatosPersonaFisica.add(cb3l, null);
	jpDatosPersonaFisica.add(tf3mFind, null);
	jpDatosPersonaFisica.add(tf3lFind, null);
	jpDatosPersonaFisica.add(btnSaveDataF3, null);
	jpDatosPersonaFisica.add(tf3b, null);
        jpDatosPersonaFisica.add(tf3a, null);
        jpDatosPersonaFisica.add(tf3c, null);
	jpDatosPersonaFisica.add(tf3d, null);
        jpDatosPersonaFisica.add(tf3h, null);
	jpDatosPersonaFisica.add(tf3i, null);
	jpDatosPersonaFisica.add(tf3j, null);
        jpDatosPersonaFisica.add(tf3l, null);
	jpDatosPersonaFisica.add(tf3m, null);
	jpDatosPersonaFisica.add(tf3n, null);
	jpDatosPersonaFisica.add(tf3nn, null);
	jpDatosPersonaFisica.add(tf3o, null);
	jpDatosPersonaFisica.add(tf3p, null);
	jpDatosPersonaFisica.add(tf3q, null);
	jpDatosPersonaFisica.add(tf3r, null);
	jpDatosPersonaFisica.add(tf3s, null);
	
	tf4a.setBounds(new Rectangle(10, 10, 505, 35));
	chk4a.setBounds(new Rectangle(555, 25, 95, 20));

	jpDatosPersonaJuridica.setLayout(null);
	jpDatosPersonaJuridica.add(lbl4b, null);
	jpDatosPersonaJuridica.add(jSeparator5, null);
	jpDatosPersonaJuridica.add(jSeparator4, null);
	jpDatosPersonaJuridica.add(tf4c5_g, null);
	jpDatosPersonaJuridica.add(tf4c5_f, null);
	jpDatosPersonaJuridica.add(tf4c5_e, null);
	jpDatosPersonaJuridica.add(tf4c5_d, null);
	jpDatosPersonaJuridica.add(tf4c5_c, null);
	jpDatosPersonaJuridica.add(tf4c5_b, null);
	jpDatosPersonaJuridica.add(tf4c5_a, null);
	jpDatosPersonaJuridica.add(tf4c4_g, null);
	jpDatosPersonaJuridica.add(tf4c4_f, null);
	jpDatosPersonaJuridica.add(tf4c4_e, null);
	jpDatosPersonaJuridica.add(tf4c4_d, null);
	jpDatosPersonaJuridica.add(tf4c4_c, null);
	jpDatosPersonaJuridica.add(tf4c4_b, null);
	jpDatosPersonaJuridica.add(tf4c4_a, null);
	jpDatosPersonaJuridica.add(tf4c3_g, null);
	jpDatosPersonaJuridica.add(tf4c3_f, null);
	jpDatosPersonaJuridica.add(tf4c3_e, null);
	jpDatosPersonaJuridica.add(tf4c3_d, null);
	jpDatosPersonaJuridica.add(tf4c3_c, null);
	jpDatosPersonaJuridica.add(tf4c3_b, null);
	jpDatosPersonaJuridica.add(tf4c3_a, null);
	jpDatosPersonaJuridica.add(tf4c2_g, null);
	jpDatosPersonaJuridica.add(tf4c2_f, null);
	jpDatosPersonaJuridica.add(tf4c2_e, null);
	jpDatosPersonaJuridica.add(tf4c2_d, null);
	jpDatosPersonaJuridica.add(tf4c2_c, null);
	jpDatosPersonaJuridica.add(tf4c2_b, null);
	jpDatosPersonaJuridica.add(tf4c2_a, null);
	jpDatosPersonaJuridica.add(tf4c1_g, null);
	jpDatosPersonaJuridica.add(tf4c1_f, null);
	jpDatosPersonaJuridica.add(tf4c1_e, null);
	jpDatosPersonaJuridica.add(tf4c1_d, null);
	jpDatosPersonaJuridica.add(tf4c1_c, null);
	jpDatosPersonaJuridica.add(tf4c1_b, null);
	jpDatosPersonaJuridica.add(tf4c1_a, null);
	jpDatosPersonaJuridica.add(lbl4c_g, null);
	jpDatosPersonaJuridica.add(lbl4c_f, null);
	jpDatosPersonaJuridica.add(lbl4c_e, null);
	jpDatosPersonaJuridica.add(lbl4c_d, null);
	jpDatosPersonaJuridica.add(lbl4c_c, null);
	jpDatosPersonaJuridica.add(lbl4c_b, null);
	jpDatosPersonaJuridica.add(lbl4c_a, null);
	jpDatosPersonaJuridica.add(lbl4c, null);
	jpDatosPersonaJuridica.add(tf4b, null);
	jpDatosPersonaJuridica.add(cb4b, null);
	jpDatosPersonaJuridica.add(btnSaveDataF4, null);
	jpDatosPersonaJuridica.add(tf4a, null);
	jpDatosPersonaJuridica.add(chk4a, null);
	tf5b.setBounds(new Rectangle(10, 55, 505, 35));
	chk5a.setBounds(new Rectangle(585, 25, 100, 20));
	tf5c.setBounds(new Rectangle(10, 95, 155, 35));
	tf5d.setBounds(new Rectangle(175, 95, 155, 35));
	chk5cd.setBounds(new Rectangle(340, 110, 95, 20));

	lbl6a.setBounds(new Rectangle(10, 200, 125, 35));
	ta6a.setBounds(new Rectangle(10, 225, 615, 65));
	btnSaveDataF1.setToolTipText("Grabar");
	btnSaveDataF1.setBounds(new Rectangle(670, 475, 30, 25));
	btnSaveDataF1.setSize(new Dimension(30, 25));
	btnSaveData.setToolTipText("Grabar");
	btnSaveData.setBounds(new Rectangle(475, 15, 30, 25));
	btnSaveData.setSize(new Dimension(30, 25));
        btnSaveDataF3.setToolTipText("Grabar");
	btnSaveDataF3.setBounds(new Rectangle(670, 410, 30, 25));
	btnSaveDataF3.setSize(new Dimension(30, 25));

	
	btnSaveDataF4.setToolTipText("Grabar");
	btnSaveDataF4.setBounds(new Rectangle(670, 400, 30, 25));
	btnSaveDataF4.setSize(new Dimension(30, 25));
	
	btnSaveDataF5.setToolTipText("Grabar");
	btnSaveDataF5.setBounds(new Rectangle(670, 400, 30, 25));
	btnSaveDataF5.setSize(new Dimension(30, 25));
        chkSelTodos5.setBounds(new Rectangle(585, 10, 100, 18));
        btnSaveDataF2b.setBounds(new Rectangle(655, 460, 30, 25));
	btnSaveDataF2b.setSize(new Dimension(30, 25));

	tf2k2_a.setBounds(new Rectangle(40, 100, 390, 20));
	tf2k2_a.setFont(new Font("Dialog", 1, 11));
	tf2k3_a.setBounds(new Rectangle(40, 125, 390, 20));
	tf2k3_a.setFont(new Font("Dialog", 1, 11));
	tf2k4_a.setBounds(new Rectangle(40, 150, 390, 20));
	tf2k4_a.setFont(new Font("Dialog", 1, 11));
	jSeparator3.setBounds(new Rectangle(5, 180, 685, 2));
	tf5f.setBounds(new Rectangle(10, 135, 415, 35));
	chk5f.setBounds(new Rectangle(585, 150, 100, 18));
	lbl5g.setBounds(new Rectangle(10, 180, 295, 15));
	rb5g_a.setBounds(new Rectangle(335, 180, 45, 20));
	rb5g_b.setBounds(new Rectangle(385, 180, 55, 20));
	chk5g.setBounds(new Rectangle(585, 180, 100, 18));
	tf2k2_b.setBounds(new Rectangle(435, 100, 145, 20));
	tf2k2_b.setFont(new Font("Dialog", 1, 11));
	tf2k3_b.setBounds(new Rectangle(435, 125, 145, 20));
	tf2k3_b.setFont(new Font("Dialog", 1, 11));
	tf2k4_b.setBounds(new Rectangle(435, 150, 145, 20));
	tf2k4_b.setFont(new Font("Dialog", 1, 11));
	tf2k2_c.setBounds(new Rectangle(585, 100, 95, 20));
	tf2k2_c.setFont(new Font("Dialog", 1, 11));
	tf2k3_c.setBounds(new Rectangle(585, 125, 95, 20));
	tf2k3_c.setFont(new Font("Dialog", 1, 11));
	tf2k4_c.setBounds(new Rectangle(585, 150, 95, 20));
	tf2k4_c.setFont(new Font("Dialog", 1, 11));
	tf3e.setBounds(new Rectangle(565, 105, 20, 20));
	tf3e.setFont(new Font("Dialog", 1, 11));
	tf4c1_a.setBounds(new Rectangle(10, 175, 120, 20));
	tf4c1_a.setFont(new Font("Dialog", 1, 11));
	tf4c1_b.setBounds(new Rectangle(135, 175, 120, 20));
	tf4c1_b.setFont(new Font("Dialog", 1, 11));
	tf4c1_c.setBounds(new Rectangle(260, 175, 75, 20));
	tf4c1_c.setFont(new Font("Dialog", 1, 11));
	tf4c1_d.setBounds(new Rectangle(340, 175, 85, 20));
	tf4c1_d.setFont(new Font("Dialog", 1, 11));
	tf4c1_e.setBounds(new Rectangle(430, 175, 70, 20));
	tf4c1_e.setFont(new Font("Dialog", 1, 11));
	tf4c1_f.setBounds(new Rectangle(505, 175, 80, 20));
	tf4c1_f.setFont(new Font("Dialog", 1, 11));
	tf4c1_g.setBounds(new Rectangle(590, 175, 105, 20));
	tf4c1_g.setFont(new Font("Dialog", 1, 11));
	tf4c2_a.setBounds(new Rectangle(10, 200, 120, 20));
	tf4c2_a.setFont(new Font("Dialog", 1, 11));
	tf4c2_b.setBounds(new Rectangle(135, 200, 120, 20));
	tf4c2_b.setFont(new Font("Dialog", 1, 11));
	tf4c2_c.setBounds(new Rectangle(260, 200, 75, 20));
	tf4c2_c.setFont(new Font("Dialog", 1, 11));
	tf4c2_d.setBounds(new Rectangle(340, 200, 85, 20));
	tf4c2_d.setFont(new Font("Dialog", 1, 11));
	tf4c2_e.setBounds(new Rectangle(430, 200, 70, 20));
	tf4c2_e.setFont(new Font("Dialog", 1, 11));
	tf4c2_f.setBounds(new Rectangle(505, 200, 80, 20));
	tf4c2_f.setFont(new Font("Dialog", 1, 11));
	tf4c2_g.setBounds(new Rectangle(590, 200, 105, 20));
	tf4c2_g.setFont(new Font("Dialog", 1, 11));
	tf4c3_a.setBounds(new Rectangle(10, 225, 120, 20));
	tf4c3_a.setFont(new Font("Dialog", 1, 11));
	tf4c3_b.setBounds(new Rectangle(135, 225, 120, 20));
	tf4c3_b.setFont(new Font("Dialog", 1, 11));
	tf4c3_c.setBounds(new Rectangle(260, 225, 75, 20));
	tf4c3_c.setFont(new Font("Dialog", 1, 11));
	tf4c3_d.setBounds(new Rectangle(340, 225, 85, 20));
	tf4c3_d.setFont(new Font("Dialog", 1, 11));
	tf4c3_e.setBounds(new Rectangle(430, 225, 70, 20));
	tf4c3_e.setFont(new Font("Dialog", 1, 11));
	tf4c3_f.setBounds(new Rectangle(505, 225, 80, 20));
	tf4c3_f.setFont(new Font("Dialog", 1, 11));
	tf4c3_g.setBounds(new Rectangle(590, 225, 105, 20));
	tf4c3_g.setFont(new Font("Dialog", 1, 11));
	tf4c4_a.setBounds(new Rectangle(10, 250, 120, 20));
	tf4c4_a.setFont(new Font("Dialog", 1, 11));
	tf4c4_b.setBounds(new Rectangle(135, 250, 120, 20));
	tf4c4_b.setFont(new Font("Dialog", 1, 11));
	tf4c4_c.setBounds(new Rectangle(260, 250, 75, 20));
	tf4c4_c.setFont(new Font("Dialog", 1, 11));
	tf4c4_d.setBounds(new Rectangle(340, 250, 85, 20));
	tf4c4_d.setFont(new Font("Dialog", 1, 11));
	tf4c4_e.setBounds(new Rectangle(430, 250, 70, 20));
	tf4c4_e.setFont(new Font("Dialog", 1, 11));
	tf4c4_f.setBounds(new Rectangle(505, 250, 80, 20));
	tf4c4_f.setFont(new Font("Dialog", 1, 11));
	tf4c4_g.setBounds(new Rectangle(590, 250, 105, 20));
	tf4c4_g.setFont(new Font("Dialog", 1, 11));
	tf4c5_a.setBounds(new Rectangle(10, 275, 120, 20));
	tf4c5_a.setFont(new Font("Dialog", 1, 11));
	tf4c5_b.setBounds(new Rectangle(135, 275, 120, 20));
	tf4c5_b.setFont(new Font("Dialog", 1, 11));
	tf4c5_c.setBounds(new Rectangle(260, 275, 75, 20));
	tf4c5_c.setFont(new Font("Dialog", 1, 11));
	tf4c5_d.setBounds(new Rectangle(340, 275, 85, 20));
	tf4c5_d.setFont(new Font("Dialog", 1, 11));
	tf4c5_e.setBounds(new Rectangle(430, 275, 70, 20));
	tf4c5_e.setFont(new Font("Dialog", 1, 11));
	tf4c5_f.setBounds(new Rectangle(505, 275, 80, 20));
	tf4c5_f.setFont(new Font("Dialog", 1, 11));
	tf4c5_g.setBounds(new Rectangle(590, 275, 105, 20));
	tf4c5_g.setFont(new Font("Dialog", 1, 11));
	jSeparator4.setBounds(new Rectangle(5, 140, 695, 5));
	jSeparator5.setBounds(new Rectangle(5, 165, 695, 5));
	lbl5e.setBounds(new Rectangle(465, 110, 90, 20));
	tf5e.setBounds(new Rectangle(560, 110, 20, 20));
	tf5e.setFont(new Font("Dialog", 1, 11));
	lbl4c.setBounds(new Rectangle(10, 110, 425, 14));
	lbl4c_a.setBounds(new Rectangle(10, 150, 125, 15));
	lbl4c_a.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_b.setBounds(new Rectangle(140, 150, 115, 15));
	lbl4c_b.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_c.setBounds(new Rectangle(260, 150, 75, 15));
	lbl4c_c.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_d.setBounds(new Rectangle(340, 150, 85, 15));
	lbl4c_d.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_e.setBounds(new Rectangle(430, 150, 70, 15));
	lbl4c_e.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_f.setBounds(new Rectangle(505, 150, 80, 15));
	lbl4c_f.setHorizontalAlignment(SwingConstants.CENTER);
	lbl4c_g.setBounds(new Rectangle(590, 150, 105, 14));
	lbl4c_g.setHorizontalAlignment(SwingConstants.CENTER);
	cb4b.setBounds(new Rectangle(10, 60, 190, 35));
	tf4b.setBounds(new Rectangle(210, 60, 190, 35));
	lbl4b.setBounds(new Rectangle(10, 55, 425, 15));
        lbl3e.setBounds(new Rectangle(470, 110, 90, 15));
	tf3f.setBounds(new Rectangle(10, 135, 460, 35));
        lbl3g.setBounds(new Rectangle(10, 175, 285, 35));
	rb3g_a.setBounds(new Rectangle(305, 175, 50, 35));
	rb3g_b.setBounds(new Rectangle(355, 175, 55, 35));
	tf3k.setBounds(new Rectangle(10, 250, 380, 35));
        jSeparator1.setBounds(new Rectangle(5, 70, 685, 2));
	jSeparator2.setBounds(new Rectangle(5, 37, 685, 2));
	lbl2k_1.setBounds(new Rectangle(10, 80, 38, 14));
	lbl2k_2.setBounds(new Rectangle(10, 106, 38, 14));
	lbl2k_3.setBounds(new Rectangle(10, 131, 38, 14));
	lbl2k_4.setBounds(new Rectangle(10, 156, 38, 14));
	tf2k1_a.setBounds(new Rectangle(40, 75, 390, 20));
	tf2k1_a.setFont(new Font("Dialog", 1, 11));
	tf2k1_c.setBounds(new Rectangle(585, 75, 95, 20));
	tf2k1_c.setFont(new Font("Dialog", 1, 11));
	tf2k1_b.setBounds(new Rectangle(435, 75, 145, 20));
	tf2k1_b.setFont(new Font("Dialog", 1, 11));
	lbl2k_d.setBounds(new Rectangle(580, 40, 105, 30));
	tf2t.setBounds(new Rectangle(185, 420, 95, 35));
	tf2u.setBounds(new Rectangle(285, 420, 95, 35));
	tf2v.setBounds(new Rectangle(385, 420, 90, 35));
	tf2w.setBounds(new Rectangle(480, 420, 60, 35));
	tf2x.setBounds(new Rectangle(545, 420, 80, 35));
	tf2r.setBounds(new Rectangle(10, 420, 110, 35));
	tf2s.setBounds(new Rectangle(125, 420, 55, 35));
	tf2pFind.setBounds(new Rectangle(10, 330, 105, 35));
	tf2qFind.setBounds(new Rectangle(10, 375, 105, 35));
	cb2p.setBounds(new Rectangle(395, 330, 225, 35));
	cb2q.setBounds(new Rectangle(120, 375, 225, 35));
        lbl2k.setBounds(new Rectangle(10, 15, 480, 15));
	tf2m.setBounds(new Rectangle(10, 285, 155, 35));
	tf2m.setSize(new Dimension(155, 35));
	tf2n.setBounds(new Rectangle(170, 285, 155, 35));
	tf2nn.setBounds(new Rectangle(330, 285, 155, 35));
	tf2o.setBounds(new Rectangle(10, 325, 365, 35));
	tf2o.setSize(new Dimension(365, 35));
	tf2p.setBounds(new Rectangle(360, 330, 210, 35));
	tf2p.setSize(new Dimension(210, 35));
	tf2q.setBounds(new Rectangle(360, 375, 210, 35));
	tf2q.setSize(new Dimension(210, 35));
	lbl2lEncabezado.setBounds(new Rectangle(175, 480, 120, 15));
	lbl2l.setBounds(new Rectangle(10, 215, 270, 15));
	rb2l_a.setBounds(new Rectangle(285, 210, 55, 20));
	rb2l_b.setBounds(new Rectangle(340, 210, 55, 20));
	lbl2k_a.setBounds(new Rectangle(15, 40, 25, 30));
	lbl2k_b.setBounds(new Rectangle(110, 40, 270, 30));
	lbl2k_c.setBounds(new Rectangle(435, 40, 145, 30));
        tfCoordenadas.setBounds(new Rectangle(5, 430, 255, 35));
	cb3l.setBounds(new Rectangle(120, 290, 230, 35));
	cb3m.setBounds(new Rectangle(120, 330, 230, 35));
	
	tf3lFind.setBounds(new Rectangle(10, 290, 105, 35));
	tf3mFind.setBounds(new Rectangle(10, 330, 105, 35));

	cb1b.setBounds(new Rectangle(285, 165, 255, 35));
	cb4b.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    //if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cb4b.getSelectedIndex() > -1) {
						    tf4b.setValue(cb4b.getSelectedItem());
						     if(cb4b.getSelectedItem().equals("OTRA")){
							 tf4b.setValue("");
							 tf4b.setEditable(true);
						     }
						     else{
						         tf4b.setEditable(false);
						     }
						}
					    //}
					}

				    }
	);
        jpDatosSuministrador.setLayout(null);
	jpDatosSuministrador.setSize(new Dimension(745, 500));
	jpDatosSuministrador.setBounds(new Rectangle(2, 38, 745, 500));
	jpDatosSuministrador.add(chkSelTodos5, null);
	jpDatosSuministrador.add(chk5g, null);
	jpDatosSuministrador.add(rb5g_b, null);
	jpDatosSuministrador.add(rb5g_a, null);
	jpDatosSuministrador.add(lbl5g, null);
	jpDatosSuministrador.add(chk5f, null);
	jpDatosSuministrador.add(tf5f, null);
	jpDatosSuministrador.add(tf5e, null);
	jpDatosSuministrador.add(lbl5e, null);
	jpDatosSuministrador.add(btnSaveDataF5, null);
	jpDatosSuministrador.add(tf5b, null);
	jpDatosSuministrador.add(chk5a, null);
	jpDatosSuministrador.add(tf5a, null);
	jpDatosSuministrador.add(chk5b, null);
	jpDatosSuministrador.add(tf5c, null);
	jpDatosSuministrador.add(tf5d, null);
	jpDatosSuministrador.add(chk5cd, null);
	jpDatosSuministrador.add(lbl6a, null);
	jpDatosSuministrador.add(ta6a, null);
	
	contentPanel.setLayout(new BorderLayout());
	panelData.setSize(new Dimension(750, 550));
	panelData.setBounds(new Rectangle(0, 0, 800, 550));
	panelData.setPreferredSize(new Dimension(800, 550));

        contentPanel.add(jpCabecera, BorderLayout.NORTH);
        contentPanel.add(panelData, BorderLayout.CENTER);
	this.setCentralPanel(contentPanel);

        tf2pFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboBarriosForm2b();
			    
		    }
		});
	tf2qFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    loadComboCallesForm2b();
			    
		    }
		});
	tf2pFind.getTextField().addKeyListener(new KeyAdapter() {

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
	
	cb2p.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    cb2p_itemStateChanged(e);
		}
	    }
	);
	
	cb2q.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    cb2q_itemStateChanged(e);
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
        panelData.addTab("1. UBICACIÓN GEOGRÁFICA", jpUbicacionGeografica);
        panelData.addTab("2.1 DATOS DEL LOCAL", jpDatosLocal);
	panelData.addTab("2.2 DATOS DEL LOCAL - Cont.", jpDatosLocalContinuacion);
	panelData.addTab("3. DATOS DEL TITULAR - PERSONA FÍSICA", jpDatosPersonaFisica);
	panelData.addTab("4. DATOS DEL TITULAR - PERSONA JURIDICA", jpDatosPersonaJuridica);
	panelData.addTab("5. DATOS DE LA PERSONA QUE SUMINISTRA LA INFORMACIÓN", jpDatosSuministrador);
	loadCombos();
	initForm();
	//rb2g_a.setSelected(true);
	//rb2h_a.setSelected(true);
	tf4b.setEditable(false);
	tfFecha.setEditable(false);
	tfFecha.setOpaque(true);
	//setRbtn2d(0);
	setEnabledSaveButton(false);
	setEnabledDeleteButton(false);
	setEnabledCloseButton(false);
	getPrintButton().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    
		}
	    }
	});
	getPrintButton().setToolTipText("Imprimir Informes");
	tf3f.getTextField().setValue("Argentina");
	tf5f.getTextField().setValue("Argentina");
        tf1k.setEditable(true);
        jButton1.setText("jButton1");
        jButton1.setBounds(new Rectangle(1310, 210, 30, 25));

        cbActividad.setBounds(new Rectangle(315, 465, 120, 35));
        
        cbTipoRubro.setBounds(new Rectangle(10, 90, 110, 35));
        
        cbDescripcionRubro.setBounds(new Rectangle(130, 90, 245, 35));
        cbCaracteristicasLocal.setBounds(new Rectangle(450, 50, 210, 35));
        cbEstadoHabilitacion.setBounds(new Rectangle(215, 50, 225, 35));
        tfBarcode.setBounds(new Rectangle(320, 5, 135, 35));
    }

    private void loadCombos(){
	loadComboBarrios();
	loadComboCalles();
	loadComboBarriosForm2b();
	loadComboCallesForm2b();
	loadComboBarriosForm3();
	loadComboCallesForm3();
    }

    private void clearData() {

    }
    
    private void loadPunto(){
	//tfPunto.setValue("("+ (new DecimalFormat("0.0000")).format(adminyServ.getX()) +"; "+ (new DecimalFormat("0.0000")).format(adminyServ.getY()) +")");
    }

    private void loadComboBarrios() {
	String param = "''";
	cb1a.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb1a.getCombo().updateUI();
    }
    
    private void loadComboBarriosForm2b() {
        String param = "''";
	cb2p.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb2p.getCombo().updateUI();
    }
    
    private void loadComboBarriosForm3() {
        String param = "''";
	cb3l.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb3l.getCombo().updateUI();
    }
    
    private void loadComboCalles(){
        String param = "''";
	cb1b.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb1b.getCombo().updateUI();
    }
    
    private void loadComboCallesForm2b(){
	String param = "'" + tf2qFind.getString() + "'";
	cb2q.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb2q.getCombo().updateUI();
    }

    private void loadComboCallesForm3(){
	String param = "'" + tf3mFind.getString() + "'";
	cb3m.loadJCombo(LibSQL.exFunction("tabs.getAllStreetsByFilter", param));
	cb3m.getCombo().updateUI();
    }
    
    private void cb1a_itemStateChanged(ItemEvent e) {
	
    }
    
    private void cb1b_itemStateChanged(ItemEvent e) {
	
    }
    
    private void cb2p_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf2p.setValue(cb2p.getSelectedItem().toString());
	}
    }
    
    private void cb2q_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf2q.setValue(cb2q.getSelectedItem().toString());
	}
    }

    
    private void initForm() {
	encabezadoEncuesta = new ClassEncabezadoEncuesta();
    }

    private void clearForms(){
	/* Encabezado */
	tfZonaNro.setValue("");
	tfNroEncuesta.setValue(""); //tfFecha.setValue("");
        tfBarcode.setValue("");
	/* Seccion 1 */
	tf1c.setValue("");
	tf1d.setValue("");	tf1e.setValue(""); 	tf1f.setValue(""); tf1g.setValue("");
	tf1h.setValue("");	tf1i.setValue(""); 	tf1j.setValue(""); tf1k.setValue("");
	tf1l.setValue(""); 	tfCoordenadas.setValue("0 ; 0");
	
	/* Seccion 2a*/
	
	tf2a.setValue(""); 	
	///tf2b.setValue(""); 	chk2b.setSelected(false);
	tf2b.setValue(""); 	
	tf2c.setValue("");	
	
	tf2i1_2.setValue(""); 	
	
	
	/* Seccion 2b */
	tf2k1_a.setText("");    tf2k1_b.setText("");	tf2k1_c.setText("");
	tf2k2_a.setText("");    tf2k2_b.setText("");    tf2k2_c.setText("");
	tf2k3_a.setText("");    tf2k3_b.setText("");    tf2k3_c.setText("");
	tf2k4_a.setText("");    tf2k4_b.setText("");    tf2k4_c.setText("");
	rb2l_a.setSelected(true); rb2l_b.setSelected(false); 
	tf2m.setValue("");    tf2n.setValue("");    tf2nn.setValue(""); 
	tf2o.setValue("");
	tf2p.setValue("");
	tf2q.setValue("");
	tf2r.setValue(""); tf2s.setValue(""); tf2t.setValue(""); tf2u.setValue(""); tf2v.setValue(""); tf2w.setValue(""); tf2x.setValue("");
	
	/* Seccion 3 */
	
	tf3a.setValue("");      
	tf3b.setValue(""); 	
	tf3c.setValue(""); 	tf3d.setValue(""); 
	tf3e.setText("");      
	tf3f.setValue("Argentina");      
	rb3g_a.setSelected(true); rb3g_b.setSelected(false); 
	tf3h.setValue("");	tf3i.setValue(""); tf3j.setValue(""); 
	tf3k.setValue("");      
	tf3l.setValue("");      
	tf3m.setValue("");      
	tf3lFind.setValue(""); 
	tf3mFind.setValue("");	
	tf3n.setValue("");	tf3nn.setValue("");
	tf3o.setValue("");	tf3p.setValue("");
	tf3q.setValue("");	tf3r.setValue("");
	tf3s.setValue("");
	
	/* Seccion 4 */
	 tf4a.setValue("");      chk4a.setSelected(false);
	cb4b.setSelectedValue("SIN ASIGNAR");
	tf4b.setValue("SIN ASIGNAR");
	
	tf4c1_a.setText(""); tf4c1_b.setText(""); tf4c1_c.setText(""); tf4c1_d.setText(""); tf4c1_e.setText(""); tf4c1_f.setText(""); tf4c1_g.setText(""); 
	tf4c2_a.setText(""); tf4c2_b.setText(""); tf4c2_c.setText(""); tf4c2_d.setText(""); tf4c2_e.setText(""); tf4c2_f.setText(""); tf4c2_g.setText(""); 
	tf4c3_a.setText(""); tf4c3_b.setText(""); tf4c3_c.setText(""); tf4c3_d.setText(""); tf4c3_e.setText(""); tf4c3_f.setText(""); tf4c3_g.setText(""); 
	tf4c4_a.setText(""); tf4c4_b.setText(""); tf4c4_c.setText(""); tf4c4_d.setText(""); tf4c4_e.setText(""); tf4c4_f.setText(""); tf4c4_g.setText(""); 
	tf4c5_a.setText(""); tf4c5_b.setText(""); tf4c5_c.setText(""); tf4c5_d.setText(""); tf4c5_e.setText(""); tf4c5_f.setText(""); tf4c5_g.setText(""); 
	
	/* Seccion 5 */
	chkSelTodos5.setSelected(false);
	tf5a.setValue(""); chk5a.setSelected(false);
	tf5b.setValue(""); chk5b.setSelected(false);
	tf5c.setValue(""); tf5d.setValue(""); chk5cd.setSelected(false);
	tf5e.setText(""); 
	tf5f.setValue("Argentina"); chk5f.setSelected(false);
	chk5g.setSelected(false); rb5g_a.setSelected(true); rb5g_b.setSelected(false);
	ta6a.setText("");
    }
}

