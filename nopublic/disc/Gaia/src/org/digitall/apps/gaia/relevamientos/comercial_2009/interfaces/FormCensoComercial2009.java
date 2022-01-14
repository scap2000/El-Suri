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
 * FormCensoComercial2009.java
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
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.sql.LibSQL;


public class FormCensoComercial2009 extends GaiaPrimitiveForm {
    
    private BasicPanel contentPanel = new BasicPanel();
    private BasicTabbedPane panelData = new BasicTabbedPane();
    private BasicPanel jpCabecera = new BasicPanel();
    private ESRIPoint point = null;
    private TFInput tfNroEncuestador = new TFInput(DataTypes.INTEGER, "Nº de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.INTEGER, "Nº de Encuesta", false);
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Fecha", false);
    private TFInput tfBarcode = new TFInput(DataTypes.STRING, "Código de Barras", false);

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
    private TFInput tfCoordenadas = new TFInput(DataTypes.STRING,"COORDENADAS",false);

    /** 2.1. Datos del Local - Primera parte */
    private BasicPanel jpDatosLocal = new BasicPanel();
    
    private TFInput tf2a = new TFInput(DataTypes.STRING, "2a. NOMBRE FANTASÍA", false);
    private BasicCheckBox chk2a = new BasicCheckBox("NP/NS/NC");

    private TFInput tf2b = new TFInput(DataTypes.STRING, "2b. C.U.I.T.", false);
    private BasicCheckBox chk2b = new BasicCheckBox("NP/NS/NC");
    private BasicLabel lbl2b = new BasicLabel();
    
    private TFInput tf2c = new TFInput(DataTypes.STRING, "2c. Nº DE PADRÓN COMERCIAL", false);
    private BasicCheckBox chk2c = new BasicCheckBox("NP/NS/NC");
    
    private BasicCheckBox rb2d = new BasicCheckBox("2d. HABILITACIÓN EN TRÁMITE");
    private BasicCheckBox rb2e = new BasicCheckBox("2e. HABILITACIÓN PROVISORIA");
    private BasicCheckBox rb2f = new BasicCheckBox("2f. SIN HABILITACIÓN");
    //private ButtonGroup grupo2def = new ButtonGroup();
    
    private BasicLabel lbl2g = new BasicLabel("2g. CARACTERÍSTICAS DEL LOCAL:");
    private BasicCheckBox rb2g_a = new BasicCheckBox("PROPIO");
    private BasicCheckBox rb2g_b = new BasicCheckBox("ALQUILADO");
    private BasicCheckBox rb2g_c = new BasicCheckBox("CON PERMISO DE USO");
    private BasicCheckBox chk2g = new BasicCheckBox("NP/NS/NC");
    //private ButtonGroup gruporb2g = new ButtonGroup();
    
    private BasicLabel lbl2h = new BasicLabel("2h. RUBRO PRINCIPAL:");
    private BasicCheckBox rb2h_a = new BasicCheckBox("COMERCIAL");
    private BasicCheckBox rb2h_b = new BasicCheckBox("DE SERVICIOS");
    private BasicCheckBox rb2h_c = new BasicCheckBox("INDUSTRIAL");
    private BasicCheckBox chk2h = new BasicCheckBox("NP/NS/NC");
    //private ButtonGroup grupochk2h = new ButtonGroup();
    
    private TFInput tf2i1_1 = new TFInput(DataTypes.STRING,"2i. DESCRIPCIÓN DEL RUBRO PRINCIPAL",false);
    private TFInput tf2i1_2 = new TFInput(DataTypes.STRING,"CÓDIGO DE ACTIVIDAD",false);
    
    private TFInput tf2j1_1 = new TFInput(DataTypes.STRING,"2j. DESCRIPCIÓN DEL/LOS RUBRO/S SECUNDARIO/S",false);
    private BasicTextField tf2j2_1 = new BasicTextField();
    private BasicTextField tf2j3_1 = new BasicTextField();
    private TFInput tf2j1_2 = new TFInput(DataTypes.STRING,"CÓDIGO DE ACTIVIDAD",false);
    private BasicTextField tf2j2_2 = new BasicTextField();
    private BasicTextField tf2j3_2 = new BasicTextField();
    
    private TFInput tf2j2 = new TFInput(DataTypes.SIMPLEDATE,"2j_1. FECHA DE HABILITACIÓN",false);
    private TFInput tf2j3 = new TFInput(DataTypes.SIMPLEDATE,"2j_2. FECHA DE CIERRE",false);
    private BasicLabel lbl2J4 = new BasicLabel("2j_3. HABILITADO:");
    private BasicCheckBox chk2j4_1 = new BasicCheckBox("SI");
    private BasicCheckBox chk2j4_2 = new BasicCheckBox("NO");
    private ButtonGroup grupo2j = new ButtonGroup();

    
    
    /** 2.2 Datos del Local - Continuacion*/
    private BasicPanel jpDatosLocalContinuacion = new BasicPanel();
    
    private BasicCheckBox chkSelTodos2_2 = new BasicCheckBox("Sel. todos");
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
    private BasicCheckBox chk2l = new BasicCheckBox("NP/NS/NC");
    private ButtonGroup grupo2l = new ButtonGroup();

    private TFInput tf2m = new TFInput(DataTypes.STRING,"2m. TELÉFONO 1",false);
    private TFInput tf2n = new TFInput(DataTypes.STRING,"2n. TELÉFONO 2",false);
    private TFInput tf2nn = new TFInput(DataTypes.STRING,"2ñ. FAX",false);
    private BasicCheckBox chk2mnnn = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf2o = new TFInput(DataTypes.STRING,"2o. E-MAIL",false);
    private BasicCheckBox chk2o = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf2pFind = new TFInput(DataTypes.STRING,"BUSCAR",false);
    private CBInput cb2p = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf2p = new TFInput(DataTypes.STRING,"2p. BARRIO",false);
    private BasicCheckBox chk2p = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf2qFind = new TFInput(DataTypes.STRING,"BUSCAR",false);
    private CBInput cb2q = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf2q = new TFInput(DataTypes.STRING,"2q. CALLE",false);
    private BasicCheckBox chk2q = new BasicCheckBox("NP/NS/NC");
    
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
    private BasicCheckBox chkSelTodos = new BasicCheckBox("Sel. todos");
    
    private TFInput tf3a = new TFInput(DataTypes.STRING, "3a. APELLIDO/S", false);
    private BasicCheckBox chk3b = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf3b = new TFInput(DataTypes.STRING, "3b. NOMBRE/S", false);
    private BasicCheckBox chk3a = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf3c = new TFInput(DataTypes.STRING, "3c. TIPO DE DOCUMENTO", false);
    
    private TFInput tf3d = new TFInput(DataTypes.STRING, "3d. Nº", false);
    private BasicCheckBox chk3cd = new BasicCheckBox("NP/NS/NC");
    
    private BasicLabel lbl3e = new BasicLabel("3e. SEXO (F/M)");
    private BasicTextField tf3e = new BasicTextField();
    
    private TFInput tf3f = new TFInput(DataTypes.STRING, "3f. NACIONALIDAD (País de Origen)", false);
    private BasicCheckBox chk3f = new BasicCheckBox("NP/NS/NC");
    
    private BasicLabel lbl3g = new BasicLabel("3g. VIVE EN EL MISMO DOMICILIO CONSIGNADO:");
    private BasicRadioButton rb3g_a = new BasicRadioButton("SI");
    private BasicRadioButton rb3g_b = new BasicRadioButton("NO");
    private BasicCheckBox chk3g = new BasicCheckBox("NP/NS/NC");
    private ButtonGroup grupo3g = new ButtonGroup();
        
    private TFInput tf3h = new TFInput(DataTypes.STRING, "3h. TELÉFONO FIJO", false);
    private TFInput tf3i = new TFInput(DataTypes.STRING, "3i. FAX", false);
    
    private TFInput tf3j = new TFInput(DataTypes.STRING, "3j. CELULAR", false);
    
    private BasicCheckBox chk3hij = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf3k = new TFInput(DataTypes.STRING, "3k. E-MAIL", false);
    private BasicCheckBox chk3k = new BasicCheckBox("NP/NS/NC");
        
    private TFInput tf3lFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3l = new CBInput(0, "BUSCAR BARRIO", false);
    private TFInput tf3l = new TFInput(DataTypes.STRING, "3l. BARRIO", false);
    private BasicCheckBox chk3l = new BasicCheckBox("NP/NS/NC");
    
    private TFInput tf3mFind = new TFInput(DataTypes.STRING, "BUSCAR", false);
    private CBInput cb3m = new CBInput(0, "BUSCAR CALLE", false);
    private TFInput tf3m = new TFInput(DataTypes.STRING, "3m. CALLE", false);
    private BasicCheckBox chk3m = new BasicCheckBox("NP/NS/NC");

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
    private SaveDataButton btnSaveDataF2a = new SaveDataButton();
    private SaveDataButton btnSaveDataF2b = new SaveDataButton();
    private SaveDataButton btnSaveDataF3 = new SaveDataButton();
    private SaveDataButton btnSaveDataF4 = new SaveDataButton();
    private SaveDataButton btnSaveDataF5 = new SaveDataButton();
    
    private BasicButton btnObservacion = new BasicButton("Error");
    
    private ClassEncabezadoEncuesta encabezadoEncuesta;
    private ErrCensoComercial2009 errorForm;
    private ClassCensoComercial2009Encabezado encabezadoCenso;
    private boolean isEdit = false;
    private int idEncuestaComercial = -1;
    private String datosEncabezadoOriginal = "";

    private BasicButton btnEliminarEncuesta = new BasicButton("Encuestas");

    public FormCensoComercial2009() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public FormCensoComercial2009(int _idEncuestaComercial) {
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
	setTitle("CENSO COMERCIAL 2009");
        chk2j4_1.setSelected(true); 
        grupo2j.add(chk2j4_1);
        grupo2j.add(chk2j4_2);
	contentPanel.setBounds(new Rectangle(10, 10, 750, 595));
	contentPanel.setPreferredSize(new Dimension(710, 595));
	contentPanel.setSize(new Dimension(710, 595));
	this.setSize(new Dimension(734, 655));
	this.setPreferredSize(new Dimension(734, 655));
	tfNroEncuestador.setBounds(new Rectangle(10, 5, 120, 35));
	tfZonaNro.setBounds(new Rectangle(140, 5, 80, 35));
        tfZonaNro.setSize(new Dimension(80, 35));
        tfNroEncuesta.setBounds(new Rectangle(230, 5, 105, 35));
        tfNroEncuesta.setSize(new Dimension(105, 35));
        tfFecha.setBounds(new Rectangle(345, 5, 95, 35));
	jpCabecera.setLayout(null);
        jpCabecera.add(tfBarcode, null);
        jpCabecera.add(btnEliminarEncuesta, null);
	jpCabecera.add(btnSaveData, null);
	jpCabecera.add(tfNroEncuestador, null);
        jpCabecera.add(tfZonaNro, null);
        jpCabecera.add(tfNroEncuesta, null);
        jpCabecera.add(tfFecha, null);
        jpCabecera.add(btnObservacion, null);
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
	chk2a.setBounds(new Rectangle(555, 25, 95, 20));
	chk2a.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2a_actionPerformed(e);
		}
	    }
	);

	tf2b.setBounds(new Rectangle(10, 50, 190, 35));
	chk2b.setBounds(new Rectangle(555, 65, 95, 20));
	chk2b.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2b_actionPerformed(e);
		}

	    }
	);
	tf2c.setBounds(new Rectangle(10, 90, 235, 35));
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
	chk2c.setBounds(new Rectangle(555, 105, 95, 20));
	chk2c.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2c_actionPerformed(e);
		}
	    }
	);
	tf2i1_2.setBounds(new Rectangle(545, 235, 145, 35));
	tf2j1_2.setBounds(new Rectangle(545, 290, 145, 35));
	rb2d.setBounds(new Rectangle(210, 145, 135, 20));
	/*rb2d.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rb2d_a_actionPerformed(e);
		}
	    }
	);*/
	rb2d.setFont(new Font("Lucida Sans", 0, 9));
	rb2d.setBounds(new Rectangle(10, 135, 175, 20));
	rb2d.setText("2d. HABILITACIÓN EN TRÁMITE");
	rb2e.setBounds(new Rectangle(325, 145, 135, 20));
	rb2e.setFont(new Font("Lucida Sans", 0, 9));
	/*rb2e.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rb2d_b_actionPerformed(e);
		}
	    }
	);*/
	rb2e.setBounds(new Rectangle(195, 135, 175, 20));
	rb2e.setText("2e. HABILITACIÓN PROVISORIA");
	rb2f.setBounds(new Rectangle(435, 145, 110, 20));
	rb2f.setFont(new Font("Lucida Sans", 0, 9));
	rb2f.setBounds(new Rectangle(380, 135, 135, 20));
	rb2f.setText("2.f SIN HABILITACIÓN");
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
	rb2g_a.setBounds(new Rectangle(215, 260, 75, 20));
	rb2g_a.setFont(new Font("Lucida Sans", 0, 9));
	rb2g_a.setBounds(new Rectangle(210, 165, 80, 20));
	rb2g_b.setBounds(new Rectangle(295, 260, 100, 20));
	rb2g_b.setFont(new Font("Lucida Sans", 0, 9));
	rb2g_b.setBounds(new Rectangle(295, 165, 95, 20));
	rb2g_c.setBounds(new Rectangle(395, 260, 160, 20));
	rb2g_c.setFont(new Font("Lucida Sans", 0, 9));
	rb2g_c.setBounds(new Rectangle(395, 165, 135, 20));
	chk2g.setBounds(new Rectangle(555, 165, 95, 20));
	/*gruporb2g.add(chk2g);
	gruporb2g.add(rb2g_a);
	gruporb2g.add(rb2g_b);
	gruporb2g.add(rb2g_c);*/
	chk2g.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2g_actionPerformed(e);
		}
	    }
	);
	grupo2l.add(rb2l_a);
	grupo2l.add(rb2l_b);
	grupo2l.add(chk2l);
	grupo3g.add(rb3g_a);
	grupo3g.add(rb3g_b);
	grupo3g.add(chk3g);
	grupo5g.add(rb5g_a);
	grupo5g.add(rb5g_b);
	grupo5g.add(chk5g);
	rb2h_a.setBounds(new Rectangle(150, 300, 100, 20));
	rb2h_a.setFont(new Font("Lucida Sans", 0, 9));
	rb2h_a.setBounds(new Rectangle(150, 195, 90, 20));
	rb2h_b.setBounds(new Rectangle(255, 300, 110, 20));
	rb2h_b.setFont(new Font("Lucida Sans", 0, 9));
	rb2h_b.setBounds(new Rectangle(255, 195, 95, 20));
	rb2h_c.setBounds(new Rectangle(370, 300, 135, 20));
	rb2h_c.setFont(new Font("Lucida Sans", 0, 9));
	rb2h_c.setBounds(new Rectangle(370, 195, 100, 20));
	chk2h.setBounds(new Rectangle(555, 195, 95, 20));
	chk2h.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2h_actionPerformed(e);
		}
	    }
	);
	lbl2g.setBounds(new Rectangle(10, 165, 200, 20));
	lbl2h.setBounds(new Rectangle(10, 195, 130, 20));
	//lbl2i.setBounds(new Rectangle(10, 235, 240, 15));
	jpDatosLocal.setLayout(null);
	jpDatosLocal.add(tf2j3_1, null);
	jpDatosLocal.add(tf2j2_1, null);
	jpDatosLocal.add(tf2j3_2, null);
	jpDatosLocal.add(tf2j2_2, null);
	jpDatosLocal.add(tf2j1_1, null);
	jpDatosLocal.add(tf2i1_1, null);
	jpDatosLocal.add(lbl2b, null);
        jpDatosLocal.add(btnSaveDataF2a, null);
        jpDatosLocal.add(lbl2g, null);
	jpDatosLocal.add(lbl2h, null);


        //jpDatosLocal.add(lbl2i, null);
	//jpDatosLocal.add(lbl2j, null);
        jpDatosLocal.add(tf2j2, null);
        jpDatosLocal.add(tf2j3, null);
        jpDatosLocal.add(lbl2J4, null);
        jpDatosLocal.add(chk2j4_1, null);

        jpDatosLocal.add(chk2j4_2, null);
        jpDatosLocal.add(rb2d, null);
	jpDatosLocal.add(rb2e, null);
	jpDatosLocal.add(rb2f, null);
	jpDatosLocal.add(tf2a, null);
	jpDatosLocal.add(chk2a, null);
	jpDatosLocal.add(tf2b, null);
	jpDatosLocal.add(chk2b, null);
	jpDatosLocal.add(tf2c, null);
	jpDatosLocal.add(chk2c, null);
	jpDatosLocal.add(tf2i1_2, null);
	jpDatosLocal.add(tf2j1_2, null);
	jpDatosLocal.add(rb2g_a, null);
	jpDatosLocal.add(rb2g_b, null);
	jpDatosLocal.add(rb2g_c, null);
	jpDatosLocal.add(chk2g, null);

	jpDatosLocal.add(rb2h_a, null);
	jpDatosLocal.add(rb2h_b, null);
	jpDatosLocal.add(rb2h_c, null);
	jpDatosLocal.add(chk2h, null);
	jpDatosLocalContinuacion.setLayout(null);
	jpDatosLocalContinuacion.add(chkSelTodos2_2, null);
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
	jpDatosLocalContinuacion.add(cb2p, null);
	jpDatosLocalContinuacion.add(tf2qFind, null);
	jpDatosLocalContinuacion.add(tf2pFind, null);
	jpDatosLocalContinuacion.add(chk2q, null);
	jpDatosLocalContinuacion.add(chk2p, null);
	jpDatosLocalContinuacion.add(chk2o, null);
	jpDatosLocalContinuacion.add(chk2mnnn, null);
	jpDatosLocalContinuacion.add(chk2l, null);
	jpDatosLocalContinuacion.add(lbl2k_c, null);
	jpDatosLocalContinuacion.add(lbl2k_b, null);
	jpDatosLocalContinuacion.add(lbl2k_a, null);

	jpDatosLocalContinuacion.add(rb2l_b, null);
	jpDatosLocalContinuacion.add(rb2l_a, null);
	jpDatosLocalContinuacion.add(lbl2l, null);
	jpDatosLocalContinuacion.add(lbl2lEncabezado, null);
	jpDatosLocalContinuacion.add(tf2q, null);
	jpDatosLocalContinuacion.add(tf2p, null);
	jpDatosLocalContinuacion.add(tf2o, null);
	jpDatosLocalContinuacion.add(tf2nn, null);
	jpDatosLocalContinuacion.add(tf2n, null);
	jpDatosLocalContinuacion.add(tf2m, null);
	jpDatosLocalContinuacion.add(lbl2k, null);
	tf3b.setBounds(new Rectangle(10, 45, 505, 35));
	
	chkSelTodos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    chkSelTodos_actionPerformed(e);
		}
	    }
	);
	
	chkSelTodos2_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    chkSelTodos2_2_actionPerformed(e);
		}
	    }
	);
	
	chkSelTodos5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    chkSelTodos5_actionPerformed(e);
		}
	    }
	);
	
	chk3b.setBounds(new Rectangle(600, 60, 95, 20));
	chk3b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3b_actionPerformed(e);
		}
	}
	);
	chk3b.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3b_itemStateChanged(e);
		}
	    }
	);
	tf3a.setBounds(new Rectangle(10, 5, 505, 35));
	chk3a.setBounds(new Rectangle(600, 20, 95, 20));
	chk3a.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3a_actionPerformed(e);
		}
	}
	);
	chk3a.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3a_itemStateChanged(e);
		}
	    }
	);
	tf3c.setBounds(new Rectangle(10, 90, 160, 35));
	tf3d.setBounds(new Rectangle(175, 90, 165, 35));
	chk3cd.setBounds(new Rectangle(360, 105, 95, 20));
	chk3cd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		    chk3cd_actionPerformed(e);
		}
	
	}
	);
	chk3cd.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3cd_itemStateChanged(e);
		}
	    }
	);
	tf3h.setBounds(new Rectangle(10, 210, 125, 35));
	tf3i.setBounds(new Rectangle(140, 210, 125, 35));
	tf3j.setBounds(new Rectangle(270, 210, 145, 35));
	chk3hij.setBounds(new Rectangle(600, 225, 95, 20));
	chk3hij.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk3hij_actionPerformed(e);
		}
	}
	);
	chk3hij.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3hij_itemStateChanged(e);
		}
	    }
	);
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
	jpDatosPersonaFisica.add(chkSelTodos, null);
	jpDatosPersonaFisica.add(chk3m, null);
	jpDatosPersonaFisica.add(chk3l, null);
	jpDatosPersonaFisica.add(chk3g, null);
	jpDatosPersonaFisica.add(chk3k, null);
	jpDatosPersonaFisica.add(tf3k, null);
	jpDatosPersonaFisica.add(rb3g_b, null);
	jpDatosPersonaFisica.add(rb3g_a, null);
	jpDatosPersonaFisica.add(lbl3g, null);
	jpDatosPersonaFisica.add(chk3f, null);
	jpDatosPersonaFisica.add(tf3f, null);
	jpDatosPersonaFisica.add(lbl3e, null);
	jpDatosPersonaFisica.add(tf3e, null);
	jpDatosPersonaFisica.add(cb3m, null);
	jpDatosPersonaFisica.add(cb3l, null);
	jpDatosPersonaFisica.add(tf3mFind, null);
	jpDatosPersonaFisica.add(tf3lFind, null);
	jpDatosPersonaFisica.add(btnSaveDataF3, null);
	jpDatosPersonaFisica.add(tf3b, null);
	jpDatosPersonaFisica.add(chk3b, null);
	jpDatosPersonaFisica.add(tf3a, null);
	jpDatosPersonaFisica.add(chk3a, null);
	jpDatosPersonaFisica.add(tf3c, null);
	jpDatosPersonaFisica.add(tf3d, null);
	jpDatosPersonaFisica.add(chk3cd, null);
	jpDatosPersonaFisica.add(tf3h, null);
	jpDatosPersonaFisica.add(tf3i, null);
	jpDatosPersonaFisica.add(tf3j, null);
	jpDatosPersonaFisica.add(chk3hij, null);
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
	chk4a.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    chk4a_actionPerformed(e);
		}
	}
	);
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
	chk5a.addActionListener(new ActionListener (){
	            public void actionPerformed(ActionEvent e){
		    chk5a_actionPerformed(e);
		}
	    }
	    );
	chk5a.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk5a_itemStateChanged(e);
		}
	    }
	);
	tf5a.setBounds(new Rectangle(10, 10, 505, 35));
	chk5b.setBounds(new Rectangle(585, 70, 100, 20));
	chk5b.addActionListener(new ActionListener (){
		    public void actionPerformed(ActionEvent e){
		    chk5b_actionPerformed(e);
		}
	    }
	    );
	chk5b.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk5b_itemStateChanged(e);
		}
	    }
	);
	tf5c.setBounds(new Rectangle(10, 95, 155, 35));
	chk5cd.addActionListener(new ActionListener (){
		    public void actionPerformed(ActionEvent e){
			chk5cd_actionPerformed(e);
		    }
	    }
	    );
	tf5d.setBounds(new Rectangle(175, 95, 155, 35));
	chk5cd.setBounds(new Rectangle(340, 110, 95, 20));
	chk5cd.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk5cd_itemStateChanged(e);
		}
	    }
	);
	lbl6a.setBounds(new Rectangle(10, 200, 125, 35));
	ta6a.setBounds(new Rectangle(10, 225, 615, 65));
	btnSaveDataF1.setToolTipText("Grabar");
	btnSaveDataF1.setBounds(new Rectangle(670, 400, 30, 25));
	btnSaveDataF1.setSize(new Dimension(30, 25));
	btnSaveDataF1.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF1_actionPerformed(e);
		}
	    }
	);
	btnSaveData.setToolTipText("Grabar");
	btnSaveData.setBounds(new Rectangle(590, 15, 30, 25));
	btnSaveData.setSize(new Dimension(30, 25));
	btnSaveData.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveData_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF2a.setToolTipText("Grabar");
	btnSaveDataF2a.setBounds(new Rectangle(670, 465, 30, 25));
	btnSaveDataF2a.setSize(new Dimension(30, 25));
	btnSaveDataF2a.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF2a_actionPerformed(e);
		}
	    }
	);
	btnSaveDataF3.setToolTipText("Grabar");
	btnSaveDataF3.setBounds(new Rectangle(670, 410, 30, 25));
	btnSaveDataF3.setSize(new Dimension(30, 25));
	btnSaveDataF3.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF3_actionPerformed(e);
		}
	    }
	);
	
	btnSaveDataF4.setToolTipText("Grabar");
	btnSaveDataF4.setBounds(new Rectangle(670, 400, 30, 25));
	btnSaveDataF4.setSize(new Dimension(30, 25));
	btnSaveDataF4.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF4_actionPerformed(e);
		}
	    }
	);
	
	btnSaveDataF5.setToolTipText("Grabar");
	btnSaveDataF5.setBounds(new Rectangle(670, 400, 30, 25));
	btnSaveDataF5.setSize(new Dimension(30, 25));
	btnSaveDataF5.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF5_actionPerformed(e);
		}
	    }
	);
	btnObservacion.setBounds(new Rectangle(655, 5, 55, 15));
	btnObservacion.setMnemonic('e');
	btnObservacion.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnObservacion_actionPerformed(e);
		}
	    }
	);
	chkSelTodos5.setBounds(new Rectangle(585, 10, 100, 18));
	chkSelTodos2_2.setBounds(new Rectangle(590, 190, 105, 18));
	chkSelTodos.setBounds(new Rectangle(600, 0, 95, 18));
	btnSaveDataF2b.setBounds(new Rectangle(655, 460, 30, 25));
	btnSaveDataF2b.setSize(new Dimension(30, 25));
	btnSaveDataF2b.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveDataF2b_actionPerformed(e);
		}
	    }
	);
	tf2k2_a.setBounds(new Rectangle(40, 100, 390, 20));
	tf2k2_a.setFont(new Font("Dialog", 1, 11));
	tf2k3_a.setBounds(new Rectangle(40, 125, 390, 20));
	tf2k3_a.setFont(new Font("Dialog", 1, 11));
	tf2k4_a.setBounds(new Rectangle(40, 150, 390, 20));
	tf2k4_a.setFont(new Font("Dialog", 1, 11));
	jSeparator3.setBounds(new Rectangle(5, 180, 685, 2));
	tf5f.setBounds(new Rectangle(10, 135, 415, 35));
	chk5f.setBounds(new Rectangle(585, 150, 100, 18));
	chk5f.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    chk5f_actionPerformed(e);
		}
	    }
	);
	chk5f.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk5f_itemStateChanged(e);
		}
	    }
	);
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
	chk3g.setBounds(new Rectangle(600, 180, 95, 35));
	chk3l.setBounds(new Rectangle(600, 305, 95, 18));
	chk3l.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk3l_actionPerformed(e);
		}
	    }
	);
	chk3l.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3l_itemStateChanged(e);
		}
	    }
	);
	chk3m.setBounds(new Rectangle(600, 345, 95, 18));
	chk3m.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk3m_actionPerformed(e);
		}
	    }
	);
	chk3m.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3m_itemStateChanged(e);
		}
	    }
	);
	lbl3e.setBounds(new Rectangle(470, 110, 90, 15));
	tf3f.setBounds(new Rectangle(10, 135, 460, 35));
	chk3f.setBounds(new Rectangle(600, 145, 95, 25));
	chk3f.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk3f_actionPerformed(e);
		}
	    }
	);
	chk3f.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3f_itemStateChanged(e);
		}
	    }
	);
	lbl3g.setBounds(new Rectangle(10, 175, 285, 35));
	rb3g_a.setBounds(new Rectangle(305, 175, 50, 35));
	rb3g_b.setBounds(new Rectangle(355, 175, 55, 35));
	tf3k.setBounds(new Rectangle(10, 250, 380, 35));
	chk3k.setBounds(new Rectangle(600, 260, 95, 25));
	chk3k.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk3k_actionPerformed(e);
		}
	    }
	);
	chk3k.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk3k_itemStateChanged(e);
		}
	    }
	);
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
	cb2p.setBounds(new Rectangle(120, 330, 225, 35));
	cb2q.setBounds(new Rectangle(120, 375, 225, 35));
	chk2o.setBounds(new Rectangle(590, 295, 105, 18));
	chk2o.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2o_actionPerformed(e);
		}
	    }
	);
	chk2o.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk2o_itemStateChanged(e);
		}
	    }
	);
	chk2p.setBounds(new Rectangle(590, 345, 105, 18));
	chk2p.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2p_actionPerformed(e);
		}
	    }
	);
	chk2p.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk2p_itemStateChanged(e);
		}
	    }
	);
	chk2q.setBounds(new Rectangle(590, 390, 105, 18));
	chk2q.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2q_actionPerformed(e);
		}
	    }
	);
	chk2q.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk2q_itemStateChanged(e);
		}
	    }
	);
	chk2mnnn.setBounds(new Rectangle(590, 255, 105, 18));
	chk2mnnn.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chk2mnnn_actionPerformed(e);
		}
	    }
	);
	chk2mnnn.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    chk2mnnn_itemStateChanged(e);
		}
	    }
	);
	chk2l.setBounds(new Rectangle(590, 210, 105, 20));
	tf2i1_1.setBounds(new Rectangle(10, 235, 530, 35));
	tf2j1_1.setBounds(new Rectangle(10, 290, 530, 35));
	tf2j2_2.setBounds(new Rectangle(545, 330, 145, 20));
	tf2j2_2.setFont(new Font("Dialog", 1, 11));
	tf2j3_2.setBounds(new Rectangle(545, 355, 145, 20));
	tf2j3_2.setFont(new Font("Dialog", 1, 11));
        tf2j2.setBounds(new Rectangle(10, 395, 185, 35));
        tf2j3.setBounds(new Rectangle(215, 395, 171, 35));
        lbl2J4.setBounds(new Rectangle(425, 405, 115, 15));
        chk2j4_1.setBounds(new Rectangle(545, 400, 55, 25));
        chk2j4_2.setBounds(new Rectangle(635, 400, 55, 25));
        tf2j2_1.setBounds(new Rectangle(10, 330, 530, 20));
	tf2j2_1.setFont(new Font("Dialog", 1, 11));
	tf2j3_1.setBounds(new Rectangle(10, 355, 530, 20));
	tf2j3_1.setFont(new Font("Dialog", 1, 11));
	lbl2k.setBounds(new Rectangle(10, 15, 480, 15));
	tf2m.setBounds(new Rectangle(10, 240, 155, 35));
	tf2m.setSize(new Dimension(155, 35));
	tf2n.setBounds(new Rectangle(185, 240, 155, 35));
	tf2nn.setBounds(new Rectangle(360, 240, 155, 35));
	tf2o.setBounds(new Rectangle(10, 285, 365, 35));
	tf2o.setSize(new Dimension(365, 35));
	tf2p.setBounds(new Rectangle(360, 330, 210, 35));
	tf2p.setSize(new Dimension(210, 35));
	tf2q.setBounds(new Rectangle(360, 375, 210, 35));
	tf2q.setSize(new Dimension(210, 35));
	lbl2lEncabezado.setBounds(new Rectangle(10, 195, 120, 15));
	lbl2l.setBounds(new Rectangle(10, 215, 270, 15));
	rb2l_a.setBounds(new Rectangle(285, 210, 55, 20));
	rb2l_b.setBounds(new Rectangle(340, 210, 55, 20));
	lbl2k_a.setBounds(new Rectangle(15, 40, 25, 30));
	lbl2k_b.setBounds(new Rectangle(110, 40, 270, 30));
	lbl2k_c.setBounds(new Rectangle(435, 40, 145, 30));
	lbl2b.setText("(No Posee / No Sabe / No Contesta)");
	lbl2b.setBounds(new Rectangle(330, 70, 225, 15));
	tfCoordenadas.setBounds(new Rectangle(10, 170, 255, 35));
	cb3l.setBounds(new Rectangle(120, 290, 230, 35));
	cb3l.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb3l_itemStateChanged(e);
		}
	    }
	);
	cb3m.setBounds(new Rectangle(120, 330, 230, 35));
	cb3m.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cb3m_itemStateChanged(e);
		}
	    }
	);
	tf3lFind.setBounds(new Rectangle(10, 290, 105, 35));
	tf3mFind.setBounds(new Rectangle(10, 330, 105, 35));

	cb1b.setBounds(new Rectangle(120, 50, 255, 35));
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
	tf1a.setBounds(new Rectangle(395, 10, 230, 35));
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
	panelData.addTab("1. UBICACIÓN GEOGRÁFICA", jpUbicacionGeografica);
	panelData.addTab("2.1 Datos del Local", jpDatosLocal);
	panelData.addTab("2.2 Datos del Local - Cont.", jpDatosLocalContinuacion);
	panelData.addTab("3. DATOS DEL TITULAR - PERSONA FÍSICA", jpDatosPersonaFisica);
	panelData.addTab("4. DATOS DEL TITULAR - PERSONA JURIDICA", jpDatosPersonaJuridica);
	panelData.addTab("5. DATOS DE LA PERSONA QUE SUMINISTRA LA INFORMACIÓN", jpDatosSuministrador);
	loadCombos();
	loadComboNatJuridica();
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
		    clickPrint(e);
		}
	    }
	});
	getPrintButton().setToolTipText("Imprimir Informes");
	tf3f.getTextField().setValue("Argentina");
	tf5f.getTextField().setValue("Argentina");
	tfNroEncuestador.getTextField().requestFocus();
	tf1k.setEditable(true);
	loadDatosFormularios(idEncuestaComercial);
	btnEliminarEncuesta.setBounds(new Rectangle(630, 25, 77, 22));
	btnEliminarEncuesta.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnEliminarEncuesta_actionPerformed(e);
		}
	    }
	);
        tfBarcode.setBounds(new Rectangle(450, 5, 135, 35));
    }

    private void setRbtn2d(int _rbtn) {
	switch (_rbtn)  {
	    case 0: {
		    rb2d.setSelected(true);
		    rb2e.setSelected(false);
		    rb2f.setSelected(false);
		}
		break;
	
	    case 1: {
	            rb2d.setSelected(true);
	            rb2e.setSelected(false);
	            rb2f.setSelected(false);
	        }
	        break;
	    case 2: {
	            rb2d.setSelected(false);
	            rb2e.setSelected(true);
	            rb2f.setSelected(false);
	        }
	        break;
	    case 3: {
	            rb2d.setSelected(false);
	            rb2e.setSelected(false);
	            rb2f.setSelected(true);
	        }
	        break;
	    case 5: {
	            rb2d.setSelected(false);
	            rb2e.setSelected(false);
	            rb2f.setSelected(false);
	        }
	        break;
	}
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
        report.setProperty("description",OrganizationInfo.getDescription().toUpperCase());
        report.setProperty("cuitmunicipal",OrganizationInfo.getCuit());
        report.setProperty("direccionmunicipal",OrganizationInfo.getAddress());
        report.setProperty("direccionweb",OrganizationInfo.getWebAddress());
        report.setProperty("phonenumber1",OrganizationInfo.getPhoneNumber1());
        report.setProperty("phonenumber2",OrganizationInfo.getPhoneNumber2());
        report.setProperty("cuit",OrganizationInfo.getCuit());	
        report.setProperty("fechaimpresion",Proced.setFormatDate(Environment.currentDate,true));
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
    
    private void loadComboBarriosForm2b() {
	String param = "'" + tf2pFind.getString() + "'";
	cb2p.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb2p.getCombo().updateUI();
    }
    
    private void loadComboBarriosForm3() {
	String param = "'" + tf3lFind.getString() + "'";
	cb3l.loadJCombo(LibSQL.exFunction("tabs.getAllNeighborhoodByFilter", param));
	cb3l.getCombo().updateUI();
    }
    
    private void loadComboCalles(){
	String param = "'" + tf1bFind.getString() + "'";
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
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf1a.setValue(cb1a.getSelectedItem().toString());
	}
    }
    
    private void cb1b_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf1b.setValue(cb1b.getSelectedItem().toString());
	}
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
	setTabs(0);
    }

    private void clearForms(){
	/* Encabezado */
	tfNroEncuestador.setValue(""); tfZonaNro.setValue("");
	tfNroEncuesta.setValue(""); //tfFecha.setValue("");
        tfBarcode.setValue("");
	/* Seccion 1 */
	tf1aFind.setValue(""); 	tf1bFind.setValue(""); 	tf1c.setValue("");
	tf1d.setValue("");	tf1e.setValue(""); 	tf1f.setValue(""); tf1g.setValue("");
	tf1h.setValue("");	tf1i.setValue(""); 	tf1j.setValue(""); tf1k.setValue("");
	tf1l.setValue(""); 	tfCoordenadas.setValue("0 ; 0");
	
	/* Seccion 2a*/
	chkSelTodos2_2.setSelected(false);
	tf2a.setValue(""); 	chk2a.setSelected(false);
	///tf2b.setValue(""); 	chk2b.setSelected(false);
	tf2b.setValue(""); 	chk2b.setSelected(false);
	tf2c.setValue("");	chk2c.setSelected(false);
	rb2d.setSelected(false); rb2e.setSelected(false); rb2f.setSelected(false); 
	chk2g.setSelected(false); rb2g_a.setSelected(false); rb2g_b.setSelected(false); rb2g_c.setSelected(false); 
	chk2h.setSelected(false); rb2h_a.setSelected(false); rb2h_b.setSelected(false); rb2h_c.setSelected(false); 
	tf2i1_2.setValue(""); 	tf2i1_1.setValue("");
	tf2j1_2.setValue("");    tf2j1_1.setValue("");
	tf2j2_1.setText("");    tf2j2_2.setText("");
	tf2j3_1.setText("");    tf2j3_2.setText("");
	
	/* Seccion 2b */
	tf2k1_a.setText("");    tf2k1_b.setText("");	tf2k1_c.setText("");
	tf2k2_a.setText("");    tf2k2_b.setText("");    tf2k2_c.setText("");
	tf2k3_a.setText("");    tf2k3_b.setText("");    tf2k3_c.setText("");
	tf2k4_a.setText("");    tf2k4_b.setText("");    tf2k4_c.setText("");
	chk2l.setSelected(false); rb2l_a.setSelected(true); rb2l_b.setSelected(false); 
	chk2mnnn.setSelected(false); tf2m.setValue("");    tf2n.setValue("");    tf2nn.setValue(""); 
	chk2o.setSelected(false);	tf2o.setValue("");
	chk2p.setSelected(false);       tf2p.setValue("");
	chk2q.setSelected(false);       tf2q.setValue("");
	tf2r.setValue(""); tf2s.setValue(""); tf2t.setValue(""); tf2u.setValue(""); tf2v.setValue(""); tf2w.setValue(""); tf2x.setValue("");
	
	/* Seccion 3 */
	chkSelTodos.setSelected(false);
	tf3a.setValue("");      chk3a.setSelected(false);
	tf3b.setValue(""); 	chk3b.setSelected(false);
	tf3c.setValue(""); 	tf3d.setValue(""); chk3cd.setSelected(false); 
	tf3e.setText("");      
	tf3f.setValue("Argentina");      chk3f.setSelected(false); 
	chk3g.setSelected(false); rb3g_a.setSelected(true); rb3g_b.setSelected(false); 
	chk3hij.setSelected(false);  tf3h.setValue("");	tf3i.setValue(""); tf3j.setValue(""); 
	tf3k.setValue("");      chk3k.setSelected(false);
	tf3l.setValue("");      chk3l.setSelected(false);
	tf3m.setValue("");      chk3m.setSelected(false);
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

    /**2009-12-09(moraless)*/
    private void setTabs(int _tab){
	switch (_tab) {
	    case 0: {/* dehabilitar todas las pestañas */
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,false);
		    panelData.setEnabledAt(4,false);
		    panelData.setEnabledAt(5,false);
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
	            panelData.setEnabledAt(4,false);
	            panelData.setEnabledAt(5,false);
	            setEnabledForms(1);
	            panelData.setSelectedIndex(0);
		    tf1aFind.getTextField().requestFocus();
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
	            panelData.setEnabledAt(4,false);
	            panelData.setEnabledAt(5,false);
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
	            panelData.setEnabledAt(4,false);
	            panelData.setEnabledAt(5,false);
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
	            panelData.setEnabledAt(4,false);
	            panelData.setEnabledAt(5,false);
		    
	            setEnabledForms(4);
	            panelData.setSelectedIndex(3);
	        }
	        break;
	    case 5: {/* dehabilitar todas las pestañas  y dejar habilitada la pestaña 5*/
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);	    
		    panelData.setEnabledAt(0,false);
		    panelData.setEnabledAt(1,false);
		    panelData.setEnabledAt(2,false);
		    panelData.setEnabledAt(3,false);
	            panelData.setEnabledAt(4,true);
	            panelData.setEnabledAt(5,false);
	            setEnabledForms(5);
	            panelData.setSelectedIndex(4);
	        }
	        break;
	    case 6: {/* dehabilitar todas las pestañas  y dejar habilitada la pestaña 6*/
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);     
	            panelData.setEnabledAt(0,false);
	            panelData.setEnabledAt(1,false);
	            panelData.setEnabledAt(2,false);
	            panelData.setEnabledAt(3,false);
	            panelData.setEnabledAt(4,false);
	            panelData.setEnabledAt(5,true);
	            setEnabledForms(6);
	            panelData.setSelectedIndex(5);
	        }
	        break;
	    case 7: {/* habilitar todas las pestañas para modificar datos recuperados de la bd*/
	            tfNroEncuesta.setEditable(true);
	            tfZonaNro.setEditable(true);
	            tfNroEncuestador.setEditable(true);
	            tfFecha.setEditable(true);     
	            panelData.setEnabledAt(0,true);
	            panelData.setEnabledAt(1,true);
	            panelData.setEnabledAt(2,true);
	            panelData.setEnabledAt(3,true);
	            panelData.setEnabledAt(4,true);
	            panelData.setEnabledAt(5,true);
	            setEnabledForms(7);
	            //panelData.setSelectedIndex(5);
	        }
	        break;
	}
	
    }

    /**2009-12-09(moraless)*/
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
		    //tf1k.setEnabled(false); tf1l.setEnabled(false);
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
	            btnSaveDataF2a.setEnabled(true);
	        }
	        break;
	    case 3: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);
	            btnSaveData.setEnabled(false);
	            btnSaveDataF2b.setEnabled(true);
	        }
	        break;
	    case 4: {
		    /* ENCABEZADO */
		    tfNroEncuesta.setEditable(false);
		    tfZonaNro.setEditable(false);
		    tfNroEncuestador.setEditable(false);
		    tfFecha.setEditable(false);
		    btnSaveData.setEnabled(false);
		    btnSaveDataF3.setEnabled(true);
		    }
	    break;
	    case 5: {
	            /* ENCABEZADO */
	            tfNroEncuesta.setEditable(false);
	            tfZonaNro.setEditable(false);
	            tfNroEncuestador.setEditable(false);
	            tfFecha.setEditable(false);
	            btnSaveData.setEnabled(false);
	            btnSaveDataF4.setEnabled(true);
	            btnSaveDataF5.setEnabled(true);
	        }
	        break;
	    case 7: { //habilitar campos de la seccion1 para su modificación
	            /* ENCABEZADO */
	            tfNroEncuesta.setEnabled(true);
	            tfZonaNro.setEnabled(true);
	            tfNroEncuestador.setEnabled(true);
	            tfFecha.setEnabled(true);
	            //btnSaveData.setEnabled(true);
	            /* SECCION 1 */
	            tf1aFind.setEnabled(true); cb1a.setEnabled(true);
	            tf1a.setEnabled(true); tf1bFind.setEnabled(true);
	            cb1b.setEnabled(true); tf1b.setEnabled(true); 
	            tf1c.setEnabled(true); tf1d.setEnabled(true);
	            tf1e.setEnabled(true); tf1f.setEnabled(true);
	            tf1g.setEnabled(true); tf1h.setEnabled(true);
	            tf1i.setEnabled(true); tf1j.setEnabled(true);
	            tf1k.setEnabled(true); tf1l.setEnabled(true);
	            //btnSaveDataF1.setEnabled(true);
	        }
	        break;
		
	}
	
    }

    private boolean checkExistPoll() {
	return LibSQL.getBoolean("gea_salta.existeEncuestaComercial2009",""+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"',"+ tfNroEncuesta.getInteger());
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (headerDataControl()) {
	            if (!checkExistPoll())  {
	                String params = + tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"','"
                                        + tfNroEncuesta.getInteger() +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) +"','"+ tfBarcode.getValue().toString() +"'";
	                encabezadoEncuesta.setIdencuestacomercial(LibSQL.getInt("gea_salta.addEncuestaComercial2009Encabezado", params));
	                if ( encabezadoEncuesta.getIdencuestacomercial() > 0){
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
	}else{
	    if (headerDataControl()) {
		//if (!checkExistPoll())  {
	                String params = + idEncuestaComercial+","+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"','"
                                        + tfNroEncuesta.getInteger() +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) +"','"+ tfBarcode.getValue().toString() +"'" ;
			if (LibSQL.getInt("gea_salta.setENcuestaComercial2009Encabezado", params) > 0) {
			    Advisor.messageBox("Las modificaciones realizadas en el encabezado de la encuesta se guardaron exitosamente","Mensaje");
			} else  {
			    Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
			}
	    } else {
	            Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +"\nen la zona Nº "+ tfZonaNro.getValue() + " ya fué registrada", "Error");
	            tfNroEncuestador.getTextField().requestFocus();
	    }
		/*} else {
		    errorForm.showMessage();
		    setFocus(0);
		}*/
	}
    }

    private boolean headerDataControl() {
	errorForm = new ErrCensoComercial2009();
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
	if(!isEdit){
	    if (seccion1DataControl()) {
		String params = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf1a.getString() + "','" + tf1b.getString() + "','" + 
				tf1c.getString() + "','" + tf1d.getString() + "','"+ tf1e.getString() + "','"+ tf1f.getString() + "','"+ tf1g.getString() + "','"+
				tf1h.getString() + "','"+ tf1i.getString() + "','"+ tf1j.getString() + "','"+ tf1k.getString() + "','"+ tf1l.getString() + "', " + 
				point.getX() + ", " + point.getY();
		if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion1", params) > 0) {
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
	errorForm = new ErrCensoComercial2009();
	boolean valor = true;
	if (tf1a.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F1a);
	} else if (tf1b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F1b);
	} else if (tfCoordenadas.getString().equals("0 ; 0")) {
	    valor = false;
	    errorForm.setError(errorForm.Coordenadas);
	}
	/* else if (tf1d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1d);
	} else if (tf1h.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F1h);
	}*/
	return valor;
    }

    private String getParam2a(){
	String rb2dText = "";
	String rb2eText = "";
	String rb2fText = "";
	String rb2gText = "";
	String rb2hText = "";
	
	if(rb2d.isSelected()){
	    rb2dText = rb2d.getText();
	    rb2dText = rb2dText.substring(4,rb2dText.length());    
	}
	if(rb2e.isSelected()){
	    rb2eText = rb2e.getText();
	    rb2eText = rb2eText.substring(4,rb2eText.length());
	}
	if(rb2f.isSelected()){
	    rb2fText = rb2f.getText();
	    rb2fText = rb2fText.substring(4,rb2fText.length());
	}
	
	
	if (chk2g.isSelected()) {
	    rb2gText = chk2g.getText();
	}else{
	    if (rb2g_a.isSelected()) {
		rb2gText = rb2g_a.getText();
	    } 
	    if (rb2g_b.isSelected()) {
		if(!rb2gText.equals("")){
		    rb2gText +=", ";    
		}
		rb2gText += rb2g_b.getText();
	    }
	    if (rb2g_c.isSelected()) {
	        if(!rb2gText.equals("")){
		    rb2gText +=", ";
		}
		rb2gText += rb2g_c.getText();
	    }
	}
	
	
	if (chk2h.isSelected()) {
	    rb2hText = chk2h.getText();
	} else {
	    if (rb2h_a.isSelected()) {
		rb2hText = rb2h_a.getText();
	    } 
	    if (rb2h_b.isSelected()) {
	        if(!rb2hText.equals("")){
		    rb2hText +=", ";
		}
		rb2hText += rb2h_b.getText();
	    } 
	    if (rb2h_c.isSelected()) {
	        if(!rb2hText.equals("")){
		    rb2hText +=", ";
		}
		rb2hText += rb2h_c.getText();
	    }
	}
	
	/*if (rb2d.isSelected()) {
	    rb2dText = rb2d.getText();
	    rb2dText = rb2dText.substring(4,rb2dText.length());
	} else if (rb2e.isSelected()) {
	    rb2eText = rb2e.getText();
	    rb2eText = rb2eText.substring(4,rb2eText.length());
	} else if (rb2f.isSelected()) {
	    rb2fText = rb2f.getText();
	    rb2fText = rb2fText.substring(4,rb2fText.length());
	}*/

	/*if (chk2g.isSelected()) {
	    rb2gText = chk2g.getText();
	} else if (rb2g_a.isSelected())  {
	    rb2gText = rb2g_a.getText();
	} else if (rb2g_b.isSelected())  {
	    rb2gText = rb2g_b.getText();
	} else if (rb2g_c.isSelected())  {
	    rb2gText = rb2g_c.getText();
	}*/
	
	
	/*if (chk2h.isSelected())  {
	    rb2hText = chk2h.getText();
	} else if (rb2h_a.isSelected())  {
	    rb2hText = rb2h_a.getText();
	} else if (rb2h_b.isSelected())  {
	    rb2hText = rb2h_b.getText();
	} else if (rb2h_c.isSelected())  {
	    rb2hText = rb2h_c.getText();
	}*/
	String params2 = "";  
	if(!isEdit){
	    params2 = "false,"+ encabezadoEncuesta.getIdencuestacomercial() +",'" + tf2a.getString() + "','" + tf2b.getString() + "','" + tf2c.getString() + "','"+ rb2dText + "','"+ rb2eText + "','"+ rb2fText + "','" + rb2gText+ "','" + rb2hText + "','" + tf2i1_1.getString() + "','" + tf2i1_2.getString() + "','" + tf2j1_1.getString() + "','" + tf2j1_2.getString() + "','" + tf2j2_1.getText() + "','" + tf2j2_2.getText() + "','" + tf2j3_1.getText() + "','" + tf2j3_2.getText() + "'," + tf2j2.getDateForSQL() + "," + tf2j3.getDateForSQL() + "," +chk2j4_1.isSelected();
	}else{
	    params2 = "false,"+ encabezadoCenso.getIdEncuestaComercial() +",'" + tf2a.getString() + "','" + tf2b.getString() + "','" + tf2c.getString() + "','"+ rb2dText + "','"+ rb2eText + "','"+ rb2fText + "','" + rb2gText+ "','" + rb2hText + "','" + tf2i1_1.getString() + "','" + tf2i1_2.getString() + "','" + tf2j1_1.getString() + "','" + tf2j1_2.getString() + "','" + tf2j2_1.getText() + "','" + tf2j2_2.getText() + "','" + tf2j3_1.getText() + "','" + tf2j3_2.getText() + "'," + tf2j2.getDateForSQL() + "," + tf2j3.getDateForSQL() + "," +chk2j4_1.isSelected();
	}
	return params2;
    }

    private void btnSaveDataF2a_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion2aDataControl()) {
		String params = getParam2a();
		if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion2a", params) > 0) {
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
    
    private boolean seccion2aDataControl() {
	errorForm = new ErrCensoComercial2009();
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
	} else if ( (!tf2j2.getDate().equals("") && !tf2j3.getDate().equals("")) && (Proced.compareDates(Proced.setFormatDate(tf2j3.getDate(),false),Proced.setFormatDate(tf2j2.getDate(),false)) == 1) ) {
                errorForm.setError(errorForm.F2j2);
                    valor = false;
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
    
    /*private void chk2b_actionPerformed(ActionEvent e){
	if (chk2b.isSelected())  {
	    tf2b.setValue(chk2b.getText());
	} else  {
	    tf2b.setValue("");
	}
    }*/
    
    private void chk2b_actionPerformed(ActionEvent e){
	if (chk2b.isSelected())  {
	    tf2b.setValue(chk2b.getText());
	} else  {
	    tf2b.setValue("");
	}
    }
    
    private void rb2d_a_actionPerformed(ActionEvent e){
	if (rb2d.isSelected())  {
	    tf2c.setValue(rb2d.getText());
	    setRbtn2d(1);
	} else  {
	    tf2c.setValue("");
	}
    }
    
    private void rb2d_b_actionPerformed(ActionEvent e){
	if (rb2e.isSelected())  {
	    tf2c.setValue(rb2e.getText());
	    setRbtn2d(2);
	} else  {
	    tf2c.setValue("");
	}
    }
    
    private void rb2d_c_actionPerformed(ActionEvent e){
	if (rb2f.isSelected())  {
	    tf2c.setValue(rb2f.getText());
	    setRbtn2d(3);
	} else  {
	    tf2c.setValue("");
	}
    }
    
    private void chk2c_actionPerformed(ActionEvent e){
	if (chk2c.isSelected())  {
	    tf2c.setValue(chk2c.getText());
	    //setRbtn2d(5);
	} else  {
	    tf2c.setValue("");
	}
    }
    
    private void chk3a_actionPerformed(ActionEvent e){
	if (chk3a.isSelected())  {
	    tf3a.setValue(chk3a.getText());
	} else  {
	    tf3a.setValue("");
	}
    }
    
    
    private void chkSelTodos_actionPerformed(ActionEvent e){
	if(chkSelTodos.isSelected()){
	    chk3a.setSelected(true);
	    chk3b.setSelected(true);
	    chk3cd.setSelected(true);
	    chk3f.setSelected(true);
	    chk3g.setSelected(true);	    
	    chk3hij.setSelected(true);
	    chk3k.setSelected(true);
	    chk3l.setSelected(true);
	    chk3m.setSelected(true);
	}
	else{
	    chk3a.setSelected(false);
	    chk3b.setSelected(false);
	    chk3cd.setSelected(false);
	    chk3f.setSelected(false);
	    rb3g_a.setSelected(true);
	    //chk3g.setSelected(false);        
	    chk3hij.setSelected(false);
	    chk3k.setSelected(false);
	    chk3l.setSelected(false);
	    chk3m.setSelected(false);
	}
    }
    
    private void chkSelTodos2_2_actionPerformed(ActionEvent e){
	if(chkSelTodos2_2.isSelected()){
	    chk2l.setSelected(true);
	    chk2mnnn.setSelected(true);
	    chk2o.setSelected(true);
	    chk2p.setSelected(true);
	    chk2q.setSelected(true);        
	}
	else{
	    //chk2l.setSelected(false);
	    rb2l_a.setSelected(true);
	    chk2mnnn.setSelected(false);
	    chk2o.setSelected(false);
	    chk2p.setSelected(false);
	    chk2q.setSelected(false);
	}
    }
    
    private void chkSelTodos5_actionPerformed(ActionEvent e){
	if(chkSelTodos5.isSelected()){
	    chk5a.setSelected(true);
	    chk5b.setSelected(true);
	    chk5cd.setSelected(true);
	    chk5f.setSelected(true);
	    chk5g.setSelected(true);        
	}
	else{
	     chk5a.setSelected(false);
	     chk5b.setSelected(false);
	     chk5cd.setSelected(false);
	     chk5f.setSelected(false);
	     //chk5g.setSelected(false);
	     rb5g_a.setSelected(true);
	}
    }
    
    private void chk3b_actionPerformed(ActionEvent e){
	if (chk3b.isSelected())  {
	    tf3b.setValue(chk3b.getText());
	} else  {
	    tf3b.setValue("");
	}
    }
    
    private void chk3cd_actionPerformed(ActionEvent e){
	if (chk3cd.isSelected())  {
	    tf3c.setValue(chk3cd.getText());
	    tf3d.setValue(chk3cd.getText());
	} else  {
	    tf3c.setValue("");
	    tf3d.setValue("");
	}
    }
    
    private void chk3hij_actionPerformed(ActionEvent e){
	if (chk3hij.isSelected())  {
	    tf3h.setValue(chk3hij.getText());
	    tf3i.setValue(chk3hij.getText());
	    tf3j.setValue(chk3hij.getText());
	} else  {
	    tf3h.setValue("");
	    tf3i.setValue("");
	    tf3j.setValue("");
	}
    }
    private void cb3l_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf3l.setValue(cb3l.getSelectedItem().toString());
	}
    }
    
    private void cb3m_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    tf3m.setValue(cb3m.getSelectedItem().toString());
	}
    }
    
    private String getParam3(){
	String rb3gText = "";
	
	if(chk3g.isSelected()){
	    rb3gText = chk3g.getText();
	}
	else{
	    if(rb3g_a.isSelected()){
	        rb3gText = rb3g_a.getText();
	    }
	    else{
	        rb3gText = rb3g_b.getText();
	    }
	}
	
	int idEncuesta = 0;
	if(!isEdit){
	    idEncuesta = encabezadoEncuesta.getIdencuestacomercial();
	}else{
	    idEncuesta = encabezadoCenso.getIdEncuestaComercial();
	}
	String params3 = "false,"+ 
	idEncuesta +",'" + 
	tf3a.getString() + "','" + 
	tf3b.getString() + "','" + 
	tf3c.getString() + "','" + 
	tf3d.getString() + "','" + 
	tf3e.getText().trim() + "','" + 
	tf3f.getString() + "','" + 
	rb3gText + "','" + 
	tf3h.getString()  + "','" + 
	
	tf3i.getString() + "','" + 
	tf3j.getString() + "','" + 
	tf3k.getString() + "','" + 
	tf3l.getString() + "','" + 
	tf3m.getString() + "','" + 
	tf3n.getString() + "','" + 
	tf3nn.getString() + "','" + 
	tf3o.getString() + "','" + 
	tf3p.getString() + "','" + 
	tf3q.getString() + "','" + 
	
	tf3r.getString() + "','" + 
	tf3s.getString() + "'";
	return params3;
    }


    private void btnSaveDataF3_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion3DataControl()) {
		String params = getParam3();
		if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion3", params) > 0) {
		    setTabs(5);
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
	errorForm = new ErrCensoComercial2009();
	boolean valor = true;
	if (tf3a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3a);
	} else if (tf3b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F3b);
	} else if (tf3c.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3c);
	} else if (tf3d.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F3d);
	/*} else if (tf3e.getText().trim().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F3e);*/
	} else if (tf3f.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F3f);
	} else if (tf3h.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3h);
	} else if (tf3k.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3k);
	} else if (tf3l.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3l);
	} else if (tf3m.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F3m);
	}
	return valor;
    }
    
    
    private void chk4a_actionPerformed(ActionEvent e){
	if (chk4a.isSelected())  {
	    tf4a.setValue(chk4a.getText());
	} else  {
	    tf4a.setValue("");
	}
    }
    
    private String getParam4(){
	String naturalezaJur = "";
	if(tf4b.getString().equals("SIN ASIGNAR")){
	    naturalezaJur = "";
	}
	else{
	    naturalezaJur = tf4b.getString();
	}
	
	int idEncuesta = 0;
	if(!isEdit){
	    idEncuesta = encabezadoEncuesta.getIdencuestacomercial();
	}else{
	    idEncuesta = encabezadoCenso.getIdEncuestaComercial();
	}
	
	String params4 = "false,"+ 
	idEncuesta + ",'" + 
	tf4a.getString() + "','" + 
	//tf4b.getString() + "','" + 
	naturalezaJur + "','" + 
	tf4c1_a.getText() + "','" + 
	tf4c1_b.getText() + "','" + 
	tf4c1_c.getText() + "','" + 
	tf4c1_d.getText() + "','" + 
	tf4c1_e.getText() + "','" + 
	tf4c1_f.getText() + "','" + 
	tf4c1_g.getText() + "','" + 
	tf4c2_a.getText() + "','" + 
	tf4c2_b.getText() + "','" + 
	tf4c2_c.getText() + "','" + 
	tf4c2_d.getText() + "','" + 
	tf4c2_e.getText() + "','" + 
	tf4c2_f.getText() + "','" + 
	tf4c2_g.getText() + "','" + 
	tf4c3_a.getText() + "','" + 
	tf4c3_b.getText() + "','" + 
	tf4c3_c.getText() + "','" + 
	tf4c3_d.getText() + "','" + 
	tf4c3_e.getText() + "','" + 
	tf4c3_f.getText() + "','" + 
	tf4c3_g.getText() + "','" + 
	tf4c4_a.getText() + "','" + 
	tf4c4_b.getText() + "','" + 
	tf4c4_c.getText() + "','" + 
	tf4c4_d.getText() + "','" + 
	tf4c4_e.getText() + "','" + 
	tf4c4_f.getText() + "','" + 
	tf4c4_g.getText() + "','" + 
	tf4c5_a.getText() + "','" + 
	tf4c5_b.getText() + "','" + 
	tf4c5_c.getText() + "','" + 
	tf4c5_d.getText() + "','" + 
	tf4c5_e.getText() + "','" + 
	tf4c5_f.getText() + "','" + 
	tf4c5_g.getText() + "'";
	return params4;
    }


    private void btnSaveDataF4_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion4DataControl()) {
		String params = getParam4();
		if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion4", params) > 0) {
		    setTabs(6);
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}
	    } else {
		errorForm.showMessage();
		setFocus(4);
	    }
	}
    }
    
    private boolean seccion4DataControl() {
	errorForm = new ErrCensoComercial2009();
	boolean valor = true;
	if (tf4a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F4a);
	}else if(tf4b.getString().equals("")){
	    valor = false;
	    errorForm.setError(errorForm.F4b);
	}
	return valor;
    }
    
    
    private void chk5a_actionPerformed(ActionEvent e){
	if (chk5a.isSelected())  {
	    tf5a.setValue(chk5a.getText());
	} else  {
	    tf5a.setValue("");
	}
    }
    
    private void chk5b_actionPerformed(ActionEvent e){
	if (chk5b.isSelected())  {
	    tf5b.setValue(chk5b.getText());
	} else  {
	    tf5b.setValue("");
	}
    }
    
    private void chk5cd_actionPerformed(ActionEvent e){
	if (chk5cd.isSelected())  {
	    tf5c.setValue(chk5cd.getText());
	    tf5d.setValue(chk5cd.getText());
	} else  {
	    /*tf5c.setValue(chk5cd.getText());
	    tf5d.setValue(chk5cd.getText());*/
	    tf5c.setValue("");
	    tf5d.setValue("");
	}
    }
    
    private String getParam5(){
	String rb5gText = "";
	if(chk5g.isSelected()){
	    rb5gText = chk5g.getText();
	}
	else{
	    if(rb5g_a.isSelected()){
	        rb5gText = rb5g_a.getText();
	    }
	    else{
	        rb5gText = rb5g_b.getText();
	    }
	}
	
	int idEncuesta = 0;
	if(!isEdit){
	    idEncuesta = encabezadoEncuesta.getIdencuestacomercial();
	}else{
	    idEncuesta = encabezadoCenso.getIdEncuestaComercial();
	}
	String params5 = "false,"+ 
	idEncuesta +",'" + 
	tf5a.getString() + "','" + 
	tf5b.getString() + "','" + 
	tf5c.getString() + "','"+ 
	tf5d.getString() + "','"+ 
	tf5e.getText().trim() + "','"+ 
	tf5f.getString() + "','"+ 
	rb5gText + "','"+ 
	ta6a.getText() + "'";
	return params5;
    }


    private void btnSaveDataF5_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion5DataControl()) {
		String params = getParam5();
		int retorno = LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion5", params);
		if ( retorno > 0) {
		    //encabezadoEncuesta = new ClassEncabezadoEncuesta();
		    
		    setTabs(0);
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}
	    } else {
		errorForm.showMessage();
		setFocus(5);
	    }
	}
    }
    
    private boolean seccion5DataControl() {
	errorForm = new ErrCensoComercial2009();
	boolean valor = true;
	if (tf5a.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F5a);
	} else if (tf5b.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F5b);
	} else if (tf5c.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F5c);
	} else if (tf5d.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F5d);
	/*} else if (tf5e.getText().trim().equals(""))  {
	    tf5e.setText("");
	    valor = false;
	    errorForm.setError(errorForm.F5e);*/
	} else if (tf5f.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F5f);
	}
	return valor;
    }

    private void tf2d_mouseClicked(MouseEvent e) {
	setRbtn2d(0);
	tf2c.setValue("");
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
	            } else if (errorForm.getError() == errorForm.F2m) {
	                tf2m.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2o) {
	                tf2o.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2p) {
	                tf2pFind.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F2q) {
	                tf2qFind.getTextField().requestFocus();
	            } 
	        }
	        break;
	    case 3: {
	            if (errorForm.getError() == errorForm.F3a)  {
	                tf3a.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3b) {
	                tf3b.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3c) {
	                tf3c.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3d) {
			tf3d.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3e) {
	                tf3e.requestFocus();
	            } else if (errorForm.getError() == errorForm.F3f) {
	                tf3f.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3h) {
	                tf3h.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3k) {
			tf3k.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3l) {
			tf3lFind.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F3m) {
			tf3mFind.getTextField().requestFocus();
	            /*} else if (errorForm.getError() == errorForm.F3l) {
	                tf3n.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3m) {
	                tf3nn.getTextField().requestFocus();*/
	            } else if (errorForm.getError() == errorForm.F3n) {
	                tf3o.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3o) {
	                tf3p.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3p) {
	                tf3q.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3q) {
	                tf3r.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F3r) {
	                tf3s.getTextField().requestFocus();
	            }
	        }
	        break;
	    case 4: {
	            if (errorForm.getError() == errorForm.F4a)  {
	                tf4a.getTextField().requestFocus();
	            }else if(errorForm.getError() == errorForm.F4b){
		        tf4b.getTextField().requestFocus();
		    }
	        }
	        break;
	    case 5: {
	            if (errorForm.getError() == errorForm.F5a)  {
	                tf5a.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F5b) {
	                tf5b.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F5c) {
			tf5c.getTextField().requestFocus();
	            } else if (errorForm.getError() == errorForm.F5d) {
	                tf5d.getTextField().requestFocus();
		    } else if (errorForm.getError() == errorForm.F5e) {
			tf5e.requestFocus();
		    } else if (errorForm.getError() == errorForm.F5f) {
			tf5f.getTextField().requestFocus();
		    }
	            /*} else if (errorForm.getError() == errorForm.F6a) {
	                ta6a.getTextArea().requestFocus();
	            }*/
	        }
	        break;
	}
    }
    
    public void printReport() {
	/*BasicReport report = new BasicReport(FormComercios.class.getResource("xml/EncuestasComercialesReport.xml"));
	report.addTableModel("gea_salta.xmlGetEncuestasComerciales", "");
	report.setProperty("organismo",OrganizationInfo.getOrgName().toUpperCase()); 
	report.setProperty("description",OrganizationInfo.getDescription().toUpperCase()); 
	report.setProperty("provinciapais",OrganizationInfo.getProvinceCountry().toUpperCase()); 
	report.setProperty("cuitmunicipal",OrganizationInfo.getCuit()); 
	report.setProperty("direccionmunicipal",OrganizationInfo.getAddress());
	report.setProperty("direccionweb",OrganizationInfo.getWebAddress());
	report.setProperty("phonenumber1",OrganizationInfo.getPhoneNumber1());
	report.setProperty("phonenumber2",OrganizationInfo.getPhoneNumber2());
	report.setProperty("cuit",OrganizationInfo.getCuit());
	report.setProperty("fechaimpresion",Proced.setFormatDate(Environment.currentDate,true));
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
    
    private String getParam2b(){
	String rb2lText = "";
	
	if(chk2l.isSelected()){
	    rb2lText = chk2l.getText();
	}
	else{
	    if(rb2l_a.isSelected()){
		rb2lText = rb2l_a.getText();
	    }
	    else{
		rb2lText = rb2l_b.getText();
	    }
	}
	
	int idEncuesta = -1;
	if(!isEdit){
	    idEncuesta = encabezadoEncuesta.getIdencuestacomercial();
	}else{
	    idEncuesta = encabezadoCenso.getIdEncuestaComercial();
	}
	
	String params2b = "false,"+ 
	idEncuesta + ",'" + 
	tf2k1_a.getText() + "','" + 
	tf2k1_b.getText() + "','" + 
	tf2k1_c.getText() + "','" + 
	tf2k2_a.getText() + "','" + 
	tf2k2_b.getText() + "','" + 
	tf2k2_c.getText() + "','" + 
	tf2k3_a.getText() + "','" + 
	tf2k3_b.getText() + "','" + 
	
	tf2k3_c.getText() + "','" + 
	tf2k4_a.getText() + "','" + 
	tf2k4_b.getText() + "','" + 
	tf2k4_c.getText() + "','" + 
	rb2lText + "','" + 
	tf2m.getString() + "','" + 
	tf2n.getString() + "','" + 
	tf2nn.getString() + "','" + 
	tf2o.getString() + "','" + 
	tf2p.getString() + "','" + 
	
	tf2q.getString() + "','" + 
	tf2r.getString() + "','" + 
	tf2s.getString() + "','" + 
	tf2t.getString() + "','" + 
	tf2u.getString() + "','" + 
	tf2v.getString() + "','" + 
	tf2w.getString() + "','" + 
	tf2x.getString() + "'";
			
	return params2b;
    }

    private void btnSaveDataF2b_actionPerformed(ActionEvent e) {
	if(!isEdit){
	    if (seccion2bDataControl()) {
		String params = getParam2b();
		if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion2b", params) > 0) {
		    //encabezadoEncuesta = new ClassEncabezadoEncuesta();
		    setTabs(4);
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}
	    } else {
		errorForm.showMessage();
		setFocus(2);
	    }
	}
    }
    
    private boolean seccion2bDataControl() {
	errorForm = new ErrCensoComercial2009();
	boolean valor = true;
	
	if (tf2m.getString().equals(""))  {
	    valor = false;
	    errorForm.setError(errorForm.F2m);
	} else if (tf2o.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F2o);
	}else if (tf2p.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F2p);
	}else if (tf2q.getString().equals("")) {
	    valor = false;
	    errorForm.setError(errorForm.F2q);
	}
	return valor;
    }

    private void chk2mnnn_actionPerformed(ActionEvent e) {
	if (chk2mnnn.isSelected())  {
	    tf2m.setValue(chk2mnnn.getText());
	    tf2n.setValue(chk2mnnn.getText());
	    tf2nn.setValue(chk2mnnn.getText());
	} else  {
	    tf2m.setValue("");
	    tf2n.setValue("");
	    tf2nn.setValue("");
	}
    }

    private void chk2o_actionPerformed(ActionEvent e) {
	if (chk2o.isSelected())  {
	    tf2o.setValue(chk2o.getText());
	} else  {
	    tf2o.setValue("");
	}
    }

    private void chk2p_actionPerformed(ActionEvent e) {
	if (chk2p.isSelected())  {
	    tf2p.setValue(chk2p.getText());
	} else  {
	    tf2p.setValue("");
	}
    }

    private void chk2q_actionPerformed(ActionEvent e) {
	if (chk2q.isSelected())  {
	    tf2q.setValue(chk2q.getText());
	} else  {
	    tf2q.setValue("");
	}
    }

    private void chk3f_actionPerformed(ActionEvent e) {
	if (chk3f.isSelected())  {
	    tf3f.setValue(chk3f.getText());
	} else  {
	    tf3f.setValue("Argentina");
	}
    }

    private void chk3k_actionPerformed(ActionEvent e) {
	if (chk3k.isSelected())  {
	    tf3k.setValue(chk3k.getText());
	} else  {
	    tf3k.setValue("");
	}
    }

    private void chk3l_actionPerformed(ActionEvent e) {
	if (chk3l.isSelected())  {
	    tf3l.setValue(chk3l.getText());
	} else  {
	    tf3l.setValue("");
	}
    }

    private void chk3m_actionPerformed(ActionEvent e) {
	if (chk3m.isSelected())  {
	    tf3m.setValue(chk3m.getText());
	} else  {
	    tf3m.setValue("");
	}
    }

    private void chk5f_actionPerformed(ActionEvent e) {
	if (chk5f.isSelected())  {
	    tf5f.setValue(chk5f.getText());
	} else  {
	    tf5f.setValue("Argentina");
	}
    }

    private void loadComboNatJuridica() {
	cb4b.addItem("UNIPERSONAL");
	cb4b.addItem("S.A");
	cb4b.addItem("S.R.L");
	cb4b.addItem("C. POR ACC.");
	cb4b.addItem("CAP. E INDUST.");
	cb4b.addItem("SOC. COLEC.");
	cb4b.addItem("S.H.");
	cb4b.addItem("ASOCIAC.");
	cb4b.addItem("S. E. MIXTA");
	cb4b.addItem("COOPERATIVA");
	cb4b.addItem("U.T.E.");
	cb4b.addItem("FUNDACIÓN");
	cb4b.addItem("ORG. PÚBLICO");
	cb4b.addItem("OTRA");
	cb4b.addItem("SIN ASIGNAR");
	cb4b.setSelectedValue("SIN ASIGNAR");
	tf4b.setText("SIN ASIGNAR");
    }

    private void chk3a_itemStateChanged(ItemEvent e) {
	if (chk3a.isSelected())  {
	    tf3a.setValue(chk3a.getText());
	} else  {
	    tf3a.setValue("");
	}
    }

    private void chk3b_itemStateChanged(ItemEvent e) {
	if (chk3b.isSelected())  {
	    tf3b.setValue(chk3b.getText());
	} else  {
	    tf3b.setValue("");
	}
    }

    private void chk3f_itemStateChanged(ItemEvent e) {
	if (chk3f.isSelected())  {
	    tf3f.setValue(chk3f.getText());
	} else  {
	    tf3f.setValue("Argentina");
	}
    }

    private void chk3cd_itemStateChanged(ItemEvent e) {
	if (chk3cd.isSelected())  {
	    tf3c.setValue(chk3cd.getText());
	    tf3d.setValue(chk3cd.getText());
	} else  {
	    tf3c.setValue("");
	    tf3d.setValue("");
	}
    }

    private void chk3hij_itemStateChanged(ItemEvent e) {
	if (chk3hij.isSelected())  {
	    tf3h.setValue(chk3hij.getText());
	    tf3i.setValue(chk3hij.getText());
	    tf3j.setValue(chk3hij.getText());
	} else  {
	    tf3h.setValue("");
	    tf3i.setValue("");
	    tf3j.setValue("");
	}
    }

    private void chk3k_itemStateChanged(ItemEvent e) {
	if (chk3k.isSelected())  {
	    tf3k.setValue(chk3k.getText());
	} else  {
	    tf3k.setValue("");
	}
    }

    private void chk3l_itemStateChanged(ItemEvent e) {
	if (chk3l.isSelected())  {
	    tf3l.setValue(chk3l.getText());
	} else  {
	    tf3l.setValue("");
	}
    }

    private void chk3m_itemStateChanged(ItemEvent e) {
	if (chk3m.isSelected())  {
	    tf3m.setValue(chk3m.getText());
	} else  {
	    tf3m.setValue("");
	}
    }

    private void chk2g_actionPerformed(ActionEvent e) {
	if(chk2g.isSelected()){
	    rb2g_a.setSelected(false);
	    rb2g_b.setSelected(false);
	    rb2g_c.setSelected(false);
	}
    }

    private void chk2h_actionPerformed(ActionEvent e) {
	if(chk2h.isSelected()){
	    rb2h_a.setSelected(false);
	    rb2h_b.setSelected(false);
	    rb2h_c.setSelected(false);
	}
    }

    private void btnObservacion_actionPerformed(ActionEvent e) {
	//EncuestasSinCargar observaciones = new EncuestasSinCargar(EncuestasSinCargar.COMERCIO);
	EncuestasSinCargar observaciones = new EncuestasSinCargar(EncuestasSinCargar.COMERCIO,tfNroEncuestador.getInteger(),tfZonaNro.getValue().toString(),tfNroEncuesta.getValue().toString());
	ExtendedInternalFrame conceptosContainer = new ExtendedInternalFrame("Observaciones");
	conceptosContainer.setCentralPanel(observaciones);
	conceptosContainer.show();
	//ComponentsManager.centerWindow(observaciones);
	//observaciones.setModal(true);
	observaciones.setVisible(true);
    }
    
    /**2009-12-09(moraless)*/
    private void loadDatosFormularios(int _idencuesta){
	//if(_idencuesta !=-1){
	 if(isEdit){
	    btnSaveData.setEnabled(false);
	    btnSaveDataF1.setEnabled(false);
	    btnSaveDataF2a.setEnabled(false);
	    btnSaveDataF2b.setEnabled(false);
	    btnSaveDataF3.setEnabled(false);
	    btnSaveDataF4.setEnabled(false);
	    btnSaveDataF5.setEnabled(false);
	    setEnabledSaveButton(true);
	    setEnabledDeleteButton(true);
	    setEnabledCloseButton(true);
	    point = new ESRIPoint(0,0);
	    setTabs(7);
	    encabezadoCenso = new ClassCensoComercial2009Encabezado();
	    encabezadoCenso.setIdEncuestaComercial(_idencuesta);
	    encabezadoCenso.retrieveData();
	    //isEdit = true;
	    //GaiaEnvironment.formsFrame.setClosable(false);
	    tfNroEncuestador.setValue(encabezadoCenso.getNroEncuestador());
	    tfNroEncuestador.setEnabled(true);
	    tfZonaNro.setValue(encabezadoCenso.getNroZona());
	    tfZonaNro.setEnabled(true);
	    tfNroEncuesta.setValue(encabezadoCenso.getNroEncuesta());
	    tfNroEncuesta.setEnabled(true);
	    tfFecha.setValue(Proced.setFormatDate(encabezadoCenso.getFecha(),true));
	    tfFecha.setEditable(false);
            tfBarcode.setValue(encabezadoCenso.getBarcode());
	    tf1a.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1a());
	    tf1b.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1b());
	    tf1c.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1c());
	    tf1d.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1d());      
	    tf1e.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1e());
	    tf1f.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1f());
	    tf1g.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1g());
	    tf1h.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1h()); 
	    tf1i.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1i());
	    tf1j.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1j()); 
	    tf1k.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1k());
	    tf1l.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getF1l());      
	    point.setLocation(encabezadoCenso.getClassCensoComercial2009Seccion1().getX(),encabezadoCenso.getClassCensoComercial2009Seccion1().getY());
	    tfCoordenadas.setValue("("+ (new DecimalFormat("0.0000")).format(point.getX()) +"; "+ (new DecimalFormat("0.0000")).format(point.getY()) +")");
	    //tfCoordenadas.setValue(encabezadoCenso.getClassCensoComercial2009Seccion1().getX()+" ; "+encabezadoCenso.getClassCensoComercial2009Seccion1().getY());
	    
	    /* Seccion 2a*/
	    tf2a.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2a());
	    ///tf2b.setValue("");   chk2b.setSelected(false);
	    tf2b.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2b());
	    tf2c.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2c());
	    if(!encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2d().equals("")){
	        rb2d.setSelected(true);
	    }
	    else{
	        rb2d.setSelected(false);
	    }
	    if(!encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2e().equals("")){
	        rb2e.setSelected(true);
	    }
	    else{
	        rb2e.setSelected(false);
	    }
	    if(!encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2f().equals("")){
	        rb2f.setSelected(true);
	    }
	    else{
	        rb2f.setSelected(false);
	    }
	    
	    //System.out.println("substring = "+encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("FF"));
	    //if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().equals("PROPIO")){
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("PROPIO")> -1 ){
	         rb2g_a.setSelected(true);
	     }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("ALQUILADO")> -1 ){
	        rb2g_b.setSelected(true);
	    }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("CON PERMISO DE USO")> -1 ){
	        rb2g_c.setSelected(true);
	    }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("NP/NS/NC")> -1 ){
	        chk2g.setSelected(true);
	    }
	    
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().indexOf("COMERCIAL") > -1){
	        rb2h_a.setSelected(true);
	    }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().indexOf("DE SERVICIOS") > -1){
	        rb2h_b.setSelected(true);
	    }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().indexOf("INDUSTRIAL") > -1){
	        rb2h_c.setSelected(true);
	    }
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().indexOf("NP/NS/NC") > -1){
	        chk2h.setSelected(true);
	    }
	    
	    /*if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("PROPIO")> -1 ){
	        rb2g_a.setSelected(true);
	    }else{
	        if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("ALQUILADO") > -1){
	            rb2g_b.setSelected(true);
	        }else{
	            if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2g().indexOf("CON PERMISO DE USO") > -1){
	                rb2g_c.setSelected(true);
	            }else{
	                chk2g.setSelected(true); rb2g_a.setSelected(false); rb2g_b.setSelected(false); rb2g_c.setSelected(false); 
	            }
	        }
	    }*/
	    
	    /*if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().equals("COMERCIAL")){
	        rb2h_a.setSelected(true);
	    }else{
	        if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().equals("DE SERVICIOS")){
	            rb2h_b.setSelected(true);
	        }else{
	            if(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2h().equals("INDUSTRIAL")){
	                rb2h_c.setSelected(true);
	            }else{
	                chk2h.setSelected(true); rb2h_a.setSelected(false); rb2h_b.setSelected(false); rb2h_c.setSelected(false); 
	            }
	        }
	    }*/
	    tf2i1_1.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2i_a());
	    tf2i1_2.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2i_b());   
	    tf2j1_1.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j1_a());
	    tf2j1_2.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j1_b());    
	    tf2j2_1.setText(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j2_a());
	    tf2j2_2.setText(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j2_b());
	    tf2j3_1.setText(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j3_a());
	    tf2j3_2.setText(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j3_b());
            tf2j2.setValue(Proced.setFormatDate(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j4(),true));
	    tf2j3.setValue(Proced.setFormatDate(encabezadoCenso.getClassCensoComercial2009Seccion2a().getF2j5(),true));
            chk2j4_1.setSelected(encabezadoCenso.getClassCensoComercial2009Seccion2a().isF2j6());
	    chk2j4_2.setSelected(!encabezadoCenso.getClassCensoComercial2009Seccion2a().isF2j6());
	    
	    /* Seccion 2b */
	    tf2k1_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k1_a());
	    tf2k1_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k1_b());    
	    tf2k1_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k1_c());
	    tf2k2_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k2_a());
	    tf2k2_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k2_b());
	    tf2k2_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k2_c());
	    tf2k3_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k3_a());
	    tf2k3_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k3_b());
	    tf2k3_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k3_c());
	    tf2k4_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k4_a());
	    tf2k4_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k4_b());
	    tf2k4_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2k4_c());
	    
	    if(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2l().equals("SI")){
	        rb2l_a.setSelected(true);
	    }else{
	        if(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2l().equals("NO")){
	            rb2l_b.setSelected(true);
	        }else{
	            chk2l.setSelected(true); rb2l_a.setSelected(false); rb2l_b.setSelected(false);  
	        }
	    }
	    
	    tf2m.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2m());
	    tf2n.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2n());
	    tf2nn.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2nn());
	    
	    tf2o.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2o());
	    tf2p.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2p());
	    tf2q.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2q());
	    tf2r.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2r()); 
	    tf2s.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2s()); 
	    tf2t.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2t()); 
	    tf2u.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2u()); 
	    tf2v.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2v()); 
	    tf2w.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2w()); 
	    tf2x.setValue(encabezadoCenso.getClassCensoComercial2009Seccion2b().getF2x());
	    
	    /* Seccion 3 */
	    tf3a.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3a());
	    tf3b.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3b());
	    tf3c.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3c());
	    tf3d.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3d());
	    tf3e.setText(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3e());
	    tf3f.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3f());
	    if(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3g().equals("SI")){
	        rb3g_a.setSelected(true);
	    }else{
	        if(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3g().equals("NO")){
	            rb3g_b.setSelected(true);
	        }else{
	            chk3g.setSelected(true); rb3g_a.setSelected(false); rb3g_b.setSelected(false);  
	        }
	    }
	    
	    tf3h.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3h());
	    tf3i.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3i());
	    tf3j.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3j());
	    
	    tf3k.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3k());
	    tf3l.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3l());
	    tf3m.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3m());
	    tf3n.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3n());
	    tf3nn.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3nn());
	    tf3o.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3o());
	    tf3p.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3p());
	    tf3q.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3q());
	    tf3r.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3r());
	    tf3s.setValue(encabezadoCenso.getClassCensoComercial2009Seccion3().getF3s());
	    
	    /* Seccion 4 */
	    tf4a.setValue(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4a());
	    if(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4b().equals("")){
		cb4b.setSelectedValue("SIN ASIGNAR");
	    }else{
		cb4b.setSelectedValue(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4b());
		//SI NATURALEZA JURÍDICA NO ESTA EN EL LISTADO DEL COMBO 
		if(cb4b.getSelectedItem().equals("SIN ASIGNAR")){
		    cb4b.setSelectedValue("OTRA");
		    tf4b.setValue(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4b());
		}
	    }
	    
	    tf4c1_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_a());
	    tf4c1_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_b()); 
	    tf4c1_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_c()); 
	    tf4c1_d.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_d());
	    tf4c1_e.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_e());
	    tf4c1_f.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_f());
	    tf4c1_g.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c1_g());
	    tf4c2_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_a());
	    tf4c2_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_b()); 
	    tf4c2_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_c()); 
	    tf4c2_d.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_d());
	    tf4c2_e.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_e());
	    tf4c2_f.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_f());
	    tf4c2_g.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c2_g());
	    tf4c3_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_a());
	    tf4c3_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_b());
	    tf4c3_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_c());
	    tf4c3_d.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_d());
	    tf4c3_e.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_e());
	    tf4c3_f.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_f()); 
	    tf4c3_g.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c3_g());
	    tf4c4_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_a());
	    tf4c4_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_b()); 
	    tf4c4_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_c());
	    tf4c4_d.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_d());
	    tf4c4_e.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_e());
	    tf4c4_f.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_f());
	    tf4c4_g.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c4_g());
	    tf4c5_a.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_a());
	    tf4c5_b.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_b()); 
	    tf4c5_c.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_c());
	    tf4c5_d.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_d());
	    tf4c5_e.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_e());
	    tf4c5_f.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_f());
	    tf4c5_g.setText(encabezadoCenso.getClassCensoComercial2009Seccion4().getF4c5_g());
	    
	    /* Seccion 5 */
	    tf5a.setValue(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5a());
	    tf5b.setValue(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5b());
	    tf5c.setValue(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5c());
	    tf5d.setValue(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5d());
	    tf5e.setText(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5e()); 
	    tf5f.setValue(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5f());
	    
	    if(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5g().equals("SI")){
	        rb5g_a.setSelected(true);
	    }else{
	        if(encabezadoCenso.getClassCensoComercial2009Seccion5().getF5g().equals("NO")){
	            rb5g_b.setSelected(true);
	        }else{
	            chk5g.setSelected(true); rb5g_a.setSelected(false); rb5g_b.setSelected(false);
	        }
	    }
	    ta6a.setText(encabezadoCenso.getClassCensoComercial2009Seccion5().getF6());
	    datosEncabezadoOriginal = ""+tfNroEncuestador.getValue()+tfZonaNro.getValue()+tfNroEncuesta.getValue();
     } else {
         tf2j2.setValue("");
         tf2j3.setValue("");
     }
    }

    private void chk2mnnn_itemStateChanged(ItemEvent e) {
	if (chk2mnnn.isSelected())  {
	    tf2m.setValue(chk2mnnn.getText());
	    tf2n.setValue(chk2mnnn.getText());
	    tf2nn.setValue(chk2mnnn.getText());
	} else  {
	    tf2m.setValue("");
	    tf2n.setValue("");
	    tf2nn.setValue("");
	}
    }

    private void chk2o_itemStateChanged(ItemEvent e) {
	if (chk2o.isSelected())  {
	    tf2o.setValue(chk2o.getText());
	} else  {
	    tf2o.setValue("");
	}
    }

    private void chk2p_itemStateChanged(ItemEvent e) {
	if (chk2p.isSelected())  {
	    tf2p.setValue(chk2p.getText());
	} else  {
	    tf2p.setValue("");
	}
    }

    private void chk2q_itemStateChanged(ItemEvent e) {
	if (chk2q.isSelected())  {
	    tf2q.setValue(chk2q.getText());
	} else  {
	    tf2q.setValue("");
	}
    }

    private void chk5a_itemStateChanged(ItemEvent e) {
	if (chk5a.isSelected())  {
	    tf5a.setValue(chk5a.getText());
	} else  {
	    tf5a.setValue("");
	}
    }

    private void chk5b_itemStateChanged(ItemEvent e) {
	if (chk5b.isSelected())  {
	    tf5b.setValue(chk5b.getText());
	} else  {
	    tf5b.setValue("");
	}
    }

    private void chk5cd_itemStateChanged(ItemEvent e) {
	if (chk5cd.isSelected())  {
	    tf5c.setValue(chk5cd.getText());
	    tf5d.setValue(chk5cd.getText());
	} else  {
	    /*tf5c.setValue(chk5cd.getText());
	    tf5d.setValue(chk5cd.getText());*/
	    tf5c.setValue("");
	    tf5d.setValue("");
	}
    }

    private void chk5f_itemStateChanged(ItemEvent e) {
	if (chk5f.isSelected())  {
	    tf5f.setValue(chk5f.getText());
	} else  {
	    tf5f.setValue("Argentina");
	}
    }
    
    private void modoEdicion(boolean _isEdit){
	isEdit = _isEdit;
    }
    
    public void saveData() {
	guardarDatosFormulario();
    }
    
    /**2009-12-15(moraless)*/
    private boolean controlFormularios(){
	boolean retorno = true;
	//datos de encabezado
	if((headerDataControl())){
	    if((retorno)&&(seccion1DataControl())){
	        if((retorno)&&(seccion2aDataControl())){
	            if((retorno)&&(seccion2bDataControl())){
	                if((retorno)&&(seccion3DataControl())){
	                    if((retorno)&&(seccion4DataControl())){
	                        if((retorno)&&(seccion5DataControl())){
	                            
	                        }else{
	                            retorno = false;
	                        }
	                    }else{
	                        retorno = false;
	                    }
	                }else{
	                    retorno = false;
	                }
	            }else{
	                retorno = false;
	            }
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
    private void guardarDatosFormulario(){
	//encabezado
	boolean datosModificados = true;
	if(controlFormularios()){
	    String encabezadoActual = ""+tfNroEncuestador.getValue()+tfZonaNro.getValue()+tfNroEncuesta.getValue();
	    //control para determinar si el encabezado a guardar existe
	    if ((encabezadoActual.toUpperCase().equals(datosEncabezadoOriginal.toUpperCase()))||(!checkExistPoll())) {
	        String params = + idEncuestaComercial+","+ tfNroEncuestador.getInteger() +",'"+ tfZonaNro.getValue().toString().toUpperCase() +"','"
                                + tfNroEncuesta.getInteger() +"','"+ Proced.setFormatDate(tfFecha.getDate(),false) + "','" + tfBarcode.getValue() +"'";
	        if (LibSQL.getInt("gea_salta.setEncuestaComercial2009Encabezado", params) > 0) {
	       
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
	        String params = "false,"+ encabezadoCenso.getIdEncuestaComercial() +",'" + tf1a.getString() + "','" + tf1b.getString() + "','" + 
	                        tf1c.getString() + "','" + tf1d.getString() + "','"+ tf1e.getString() + "','"+ tf1f.getString() + "','"+ tf1g.getString() + "','"+
	                        tf1h.getString() + "','"+ tf1i.getString() + "','"+ tf1j.getString() + "','"+ tf1k.getString() + "','"+ tf1l.getString() + "', " + 
	                        point.getX() + ", " + point.getY();
	        if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion1", params) > 0) {
	            //getLayer().getGeometrySet().addGeometry(point);
	            
	        } else {
	            datosModificados = false;
	            Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	        }
	    }
	    
	    //seccion2a
	    if(datosModificados){
	       String params = getParam2a();
	       if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion2a", params) > 0) {
	           
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	    
	    //seccion2b
	    if(datosModificados){
	       String params = getParam2b();
	       if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion2b", params) > 0) {
	           
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	    
	    //seccion3
	    if(datosModificados){
	       String params = getParam3();
	       if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion3", params) > 0) {
	           
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	    
	    //seccion4
	    if(datosModificados){
	       String params = getParam4();
	       if (LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion4", params) > 0) {
	           
	       } else {
	           datosModificados = false;
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	    
	    //seccion5
	    if(datosModificados){
	       String params = getParam5();
	       int retorno = LibSQL.getInt("gea_salta.addEncuestaComercial2009Seccion5", params);
	       if ( retorno > 0) {
	           Advisor.messageBox("Las modificaciones realizadas se guardaron exitosamente", "Mensaje");
	           super.hideForm();            
	       } else {
	           Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
	       }
	    }
	} else{
	    errorForm.showMessage();
	    //setFocus(5);
	}
    }
    
     public void delete(){
	 String params = ""+tfNroEncuestador.getValue()+",'"+tfZonaNro.getValue()+"','"+tfNroEncuesta.getValue()+"'";
	 //if(LibSQL.getInt("gea_salta.delcensocomercial2009", params) > 0){
	 if(LibSQL.getInt("gea_salta.delcensocomercial2009", ""+encabezadoCenso.getIdEncuestaComercial()) > 0){
	     params = "'"+tfNroEncuestador.getValue()+"','"+tfZonaNro.getValue()+"','"+tfNroEncuesta.getValue()+"','Comercio','Eliminado','','','','','','','','','','','','','ENCUESTA ELIMINADA','',''";
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
	PanelBorrarEncuestas encuestasEliminadas = new PanelBorrarEncuestas(true);
	ExtendedInternalFrame conceptosContainer = new ExtendedInternalFrame("Buscar Encuesta");
	conceptosContainer.setCentralPanel(encuestasEliminadas);
	conceptosContainer.show();
	//ComponentsManager.centerWindow(observaciones);
	//observaciones.setModal(true);
	encuestasEliminadas.setVisible(true);
    }
}

