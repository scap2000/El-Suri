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
 * EncuestasSinCargar.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;

import javax.swing.border.EtchedBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
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
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class EncuestasSinCargar extends BasicPrimitivePanel {
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();

    private SaveDataButton btnGuardarObservaciones = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private BasicScrollPane spObservacion = new BasicScrollPane();
    private BasicPanel contentPanel = new BasicPanel("");
    private BasicPanel pEncabezadoObservaciones = new BasicPanel();
    private BasicPanel pOpcionesObservaciones = new BasicPanel();

    private BasicLabel lblDescripcion = new BasicLabel();
    private BasicLabel lblBorrarEncuesta = new BasicLabel();
    private BasicLabel lblObservaciones = new BasicLabel("Observaciones");

    private TFInput tfNroEncuestador = new TFInput(DataTypes.STRING, "Nº de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.STRING, "Nº de Encuesta", false);
    
    private CBInput cbEstado = new CBInput(0, "Estado", false);
    private CBInput cbTipoObservacion = new CBInput(0, "Tipo de Observación", false);
    
    private BasicCheckBox chkObs01 = new BasicCheckBox();
    private BasicCheckBox chkObs02 = new BasicCheckBox();
    private BasicCheckBox chkObs03 = new BasicCheckBox();
    private BasicCheckBox chkObs04 = new BasicCheckBox();
    private BasicCheckBox chkObs05 = new BasicCheckBox();
    private BasicCheckBox chkObs06 = new BasicCheckBox();
    private BasicCheckBox chkObs07 = new BasicCheckBox();
    private BasicCheckBox chkObs08 = new BasicCheckBox();
    private BasicCheckBox chkObs09 = new BasicCheckBox();
    private BasicCheckBox chkObs10 = new BasicCheckBox();
    private BasicCheckBox chkObs11 = new BasicCheckBox();
    private BasicCheckBox chkObs12 = new BasicCheckBox();
    private BasicCheckBox chkObs13 = new BasicCheckBox();
    
    private JArea taDescripcion = new JArea();
    
    public static final int DIRECCION = 0;
    public static final int COMERCIO = 1;
    public static final int PUBLICIDAD = 2;
    private int tipoObservacion = 0;
    //int numeroFuncion = -1;

    public EncuestasSinCargar(int _tipoObservacion) {
	tipoObservacion = _tipoObservacion;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public EncuestasSinCargar(int _tipoObservacion, int _nroencuestador, String _nrozona, String _nroencuesta) {
	tipoObservacion = _tipoObservacion;
	tfNroEncuestador.setValue(_nroencuestador);
	tfZonaNro.setValue(_nrozona);
	tfNroEncuesta.setValue(_nroencuesta);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(646, 505));
	this.setTitle("Privilegios");
	//taDescripcion.setEditable(false);
	pEncabezadoObservaciones.add(tfNroEncuestador, null);
	pEncabezadoObservaciones.add(cbEstado, null);
	pEncabezadoObservaciones.add(cbTipoObservacion, null);
	pEncabezadoObservaciones.add(tfNroEncuesta, null);
	pEncabezadoObservaciones.add(tfZonaNro, null);
	pOpcionesObservaciones.add(lblObservaciones, null);
	pOpcionesObservaciones.add(lblDescripcion, null);
	pOpcionesObservaciones.add(taDescripcion, null);
	pOpcionesObservaciones.add(chkObs12, null);
	pOpcionesObservaciones.add(chkObs11, null);
	pOpcionesObservaciones.add(chkObs10, null);
	pOpcionesObservaciones.add(chkObs09, null);
	pOpcionesObservaciones.add(chkObs08, null);
	pOpcionesObservaciones.add(chkObs07, null);
	pOpcionesObservaciones.add(chkObs06, null);
	pOpcionesObservaciones.add(chkObs05, null);
	pOpcionesObservaciones.add(chkObs04, null);
	pOpcionesObservaciones.add(chkObs03, null);
	pOpcionesObservaciones.add(chkObs02, null);
	pOpcionesObservaciones.add(chkObs01, null);
	pOpcionesObservaciones.add(chkObs13, null);
	contentPanel.add(pOpcionesObservaciones, null);
	contentPanel.add(pEncabezadoObservaciones, null);
	contentPanel.add(lblBorrarEncuesta, null);

	//spObservacion.getViewport().add(taDescripcion, null);
	addButton(btnClose);
	this.add(contentPanel, null);
	addButton(btnGuardarObservaciones);
	contentPanel.setBounds(new Rectangle(0, 0, 655, 345));
	contentPanel.setLayout(null);
	
	cbTipoObservacion.setBounds(new Rectangle(10, 60, 230, 35));
	cbTipoObservacion.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbTipoObservacion.getSelectedIndex() > -1) {
						    habilitarOpciones();
						}
					    }
					}

				    }
	);
	chkObs01.setBounds(new Rectangle(10, 20, 370, 20));
	chkObs02.setBounds(new Rectangle(10, 45, 370, 20));
	chkObs03.setBounds(new Rectangle(10, 70, 370, 20));
	tfNroEncuestador.setBounds(new Rectangle(10, 20, 115, 35));
	tfZonaNro.setBounds(new Rectangle(150, 20, 115, 35));
	tfNroEncuesta.setBounds(new Rectangle(290, 20, 115, 35));
	btnGuardarObservaciones.setBounds(new Rectangle(585, 360, 40, 25));
	btnGuardarObservaciones.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGuardarObservaciones_actionPerformed(e);
		}
	    }
	);
	cbEstado.setBounds(new Rectangle(270, 60, 125, 35));
	chkObs04.setBounds(new Rectangle(10, 95, 370, 15));
	chkObs05.setBounds(new Rectangle(10, 120, 370, 15));
	chkObs06.setBounds(new Rectangle(10, 145, 370, 15));
	chkObs07.setBounds(new Rectangle(10, 170, 370, 15));
	lblObservaciones.setBounds(new Rectangle(100, 10, 160, 15));
	chkObs09.setBounds(new Rectangle(10, 220, 370, 15));
	chkObs10.setBounds(new Rectangle(10, 245, 370, 15));
	chkObs11.setBounds(new Rectangle(10, 270, 370, 15));
	chkObs12.setBounds(new Rectangle(10, 295, 370, 15));
	chkObs08.setBounds(new Rectangle(10, 195, 370, 15));
	btnClose.setBounds(new Rectangle(615, 360, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	lblDescripcion.setText("Descripción");
	lblDescripcion.setBounds(new Rectangle(375, 20, 145, 15));
	//spObservacion.setBounds(new Rectangle(385, 130, 250, 295));
	taDescripcion.setBounds(new Rectangle(375, 35, 240, 295));
	pEncabezadoObservaciones.setBounds(new Rectangle(5, 5, 630, 105));
	pEncabezadoObservaciones.setLayout(null);
	pEncabezadoObservaciones.setBorder(BorderPanel.getBorderPanel("Datos de Encuesta"));
	pOpcionesObservaciones.setBounds(new Rectangle(5, 115, 630, 345));
	pOpcionesObservaciones.setLayout(null);
	pOpcionesObservaciones.setBorder(BorderPanel.getBorderPanel("Observaciones"));
	lblBorrarEncuesta.setBounds(new Rectangle(125, 435, 40, 25));
	chkObs13.setBounds(new Rectangle(10, 325, 120, 20));
	chkObs13.setText("Borrar Encuesta");
	chkObs13.setEnabled(false);
	lblBorrarEncuesta.setIcon(IconTypes.delete_16x16);
	lblBorrarEncuesta.setSize(new Dimension(40, 25));
	lblBorrarEncuesta.setHorizontalAlignment(SwingConstants.CENTER);
	lblBorrarEncuesta.setHorizontalTextPosition(SwingConstants.CENTER);
	loadTipoObservacion();
	loadEstadoEncuesta();
	loadObservaciones();
	chkObs13.setVisible(false);
	lblBorrarEncuesta.setVisible(false);
	lblObservaciones.setVisible(false);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	this.getParentInternalFrame().close();
    }

    private void loadTipoObservacion() {
	cbTipoObservacion.addItem("Dirección");
	cbTipoObservacion.addItem("Comercio");
	cbTipoObservacion.addItem("Publicidad");
    }
    
    private void loadEstadoEncuesta(){
	cbEstado.addItem("Incompleto");
	cbEstado.addItem("No cargado");
    }
    
    private void setObservaciones(){
	chkObs01.setText("Direcciones pares-impares");
	chkObs02.setText("Direcciones repetidas");
	chkObs03.setText("Faltan direcciones en el plano de la manzana");
	chkObs04.setText("Faltan calles en el GIS");
	chkObs05.setText("Parcela/s sin vincular");
	chkObs06.setText("Calle/s en encuesta no coincide/n con nombre/s en el GIS");
	chkObs07.setText("El Nº de catastro en la encuesta no coincide con el Nº en el GIS");
	chkObs08.setText("No se puede ubicar el local en el plano de la manzana");
	chkObs09.setText("No se encontró foto del cartel en los archivos");
	chkObs10.setText("No se relevó texto del anuncio");
	chkObs11.setText("Letra ilegible");
	chkObs13.setText("Borrar Encuesta");
	chkObs12.setText("Otra");
	
	taDescripcion.setEditable(true);
	if(tipoObservacion==PUBLICIDAD){
	    chkObs01.setEnabled(false);
	    chkObs02.setEnabled(false);     
	    chkObs03.setEnabled(false);
	    chkObs04.setEnabled(false);
	    chkObs05.setEnabled(false);
	    chkObs06.setEnabled(false);
	    chkObs07.setEnabled(true);
	    chkObs08.setEnabled(true);
	    chkObs09.setEnabled(true);
	    chkObs10.setEnabled(true);
	    chkObs11.setEnabled(true);
	    chkObs12.setEnabled(true);
	    chkObs13.setEnabled(true);
	    cbTipoObservacion.setSelectedValue("Publicidad");
	}
	else{
	    chkObs01.setEnabled(true);
	    chkObs02.setEnabled(true);     
	    chkObs03.setEnabled(true);
	    chkObs04.setEnabled(true);
	    chkObs05.setEnabled(true);
	    chkObs06.setEnabled(true);
	    chkObs07.setEnabled(true);
	    chkObs08.setEnabled(true);
	    chkObs09.setEnabled(false);
	    chkObs10.setEnabled(false);
	    chkObs11.setEnabled(true);
	    chkObs12.setEnabled(true);
	    chkObs13.setEnabled(true);
	    cbTipoObservacion.setSelectedValue("Comercio");
	}
    }
    
    private void setObservacionesDirecciones(){
	chkObs01.setVisible(true);
	chkObs02.setVisible(true);
	chkObs03.setVisible(true);
	chkObs04.setVisible(true);
	chkObs05.setVisible(true);
	chkObs06.setVisible(true);
	chkObs07.setVisible(true);
	chkObs08.setVisible(true);
	chkObs01.setText("Direcciones pares-impares");
	chkObs02.setText("Direcciones repetidas");
	chkObs03.setText("Faltan direcciones en el plano de la manzana");
	chkObs04.setText("Faltan calles en el GIS");
	chkObs05.setText("Parcela/s sin vincular");
	chkObs06.setText("Calle/s en encuesta no coincide/n con nombre/s en el GIS");
	//chkObs07.setText("Letra ilegible");
	//chkObs08.setText("Otra");
    }
    
    private void setObservacionesComercios(){
	chkObs01.setVisible(true);
	chkObs02.setVisible(true);
	chkObs03.setVisible(true);
	chkObs04.setVisible(true);
	chkObs05.setVisible(false);
	chkObs06.setVisible(false);
	chkObs07.setVisible(false);
	chkObs08.setVisible(false);
	chkObs01.setText("El Nº de catastro en la encuesta no coincide con el Nº en el GIS");
	chkObs02.setText("No se puede ubicar el local en el plano de la manzana");
	chkObs03.setText("Letra ilegible");
	chkObs04.setText("Otra");
    }
    
    private void setObservacionesPublicidades(){
	chkObs01.setVisible(true);
	chkObs02.setVisible(true);
	chkObs03.setVisible(true);
	chkObs04.setVisible(true);
	chkObs05.setVisible(false);
	chkObs06.setVisible(false);
	chkObs07.setVisible(false);
	chkObs08.setVisible(false);
	chkObs01.setText("No se encontró foto del cartel en los archivos");
	chkObs02.setText("No se relevó texto del anuncio");
	chkObs03.setText("Letra ilegible");
	chkObs04.setText("Otra");
    }

    private void loadObservaciones() {
	setObservaciones();
	/*switch(tipoObservacion){
	    case DIRECCION: setObservacionesDirecciones();
	    break;
	    case COMERCIO: setObservacionesComercios();
	    break;
	    case PUBLICIDAD: setObservacionesPublicidades();
	    break;
	}*/
    }

    /**2009-12-22(moraless)*/
    private void btnGuardarObservaciones_actionPerformed(ActionEvent e) {
	//if(!tfNroEncuesta.getString().equals("")){
	if (tfZonaNro.getString().equals("") || tfZonaNro.getString().equals("0")) {
	    Advisor.messageBox("El campo \"Zona Nº.\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	}else{
	    if ((!tfNroEncuesta.getString().equals("")) && (!tfNroEncuesta.getString().equals("0"))) {
		String params = getParams();
		if (LibSQL.getInt("gea_salta.addobservaciones", params) > 0) {
		    this.getParentInternalFrame().close();
		    /*if(chkObs13.isSelected()){
			if(borrarEncuesta() > 0){
			    this.getParentInternalFrame().close();
			}else{
			    if(cbTipoObservacion.getSelectedItem().equals("Dirección")){
				Advisor.messageBox("No se puede borrar una dirección", "Error");
			    }else{
				Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
			    }
			}    
		    }else{
			this.getParentInternalFrame().close();
		    }*/
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		}    
	    }
	    else{
		Advisor.messageBox("El campo \"Nro. de Encuesta\" no debe estar vacío y debe ser mayor que cero (0)", "Mensaje");
	    }
	}	
    }
    
    private int borrarEncuesta(){
	int retorno = -1;
	String params = ""+tfNroEncuestador.getValue()+",'"+tfZonaNro.getValue()+"','"+tfNroEncuesta.getValue()+"'";
	if(chkObs13.isSelected()){
	    if(cbTipoObservacion.getSelectedItem().equals("Comercio")){
	        retorno = LibSQL.getInt("gea_salta.delcensocomercial2009", params);
	    }else{
	        if(cbTipoObservacion.getSelectedItem().equals("Publicidad")){
	            retorno = LibSQL.getInt("gea_salta.delrelevamientopublicidad2009", params);
	        }
	    }
	}
	//System.out.println("borraren = "+retorno);
	return retorno;
    }

    private String getParams(){
	//String paramObs = "";
	//switch(tipoObservacion){
	    /*case DIRECCION: paramObs = getParamsDirecciones();
		break;
	    case COMERCIO: paramObs =  "''," + "''," + "''," + "''," + "''," + "'','" + chkObs04.getText() + "','" + chkObs05.getText()+ "','" + chkObs01.getText() + "','"+chkObs02.getText() + "','',"+"''";
	        break;
	    case PUBLICIDAD: paramObs = "''," + "''," + "''," + "''," + "''," + "'','" + chkObs04.getText() + "','" + chkObs05.getText()+ "'," + "'',"+"''" + chkObs01.getText() + "','"+chkObs02.getText();
	        break;
	}	*/
	//String params = "'"+tfNroEncuestador.getString() + "','" + tfZonaNro.getString() + "','" + tfNroEncuesta.getString() + "','" + cbTipoObservacion.getSelectedItem() + "','" + cbEstado.getSelectedItem() + "'," + getParamsObservaciones() + ",'" + taDescripcion.getText() + "',''";
	String params = "'"+tfNroEncuestador.getString() + "','" + tfZonaNro.getString() + "','" + tfNroEncuesta.getString() + "','" + cbTipoObservacion.getSelectedItem() + "','" + cbEstado.getSelectedItem() + "'," + getParamsObservaciones() + ",'" + taDescripcion.getText() + "',''";
	return params;
    }

    private String getParamsObservaciones() {
	String paramDir = "";
	if(chkObs01.isSelected()){
	    paramDir = paramDir + "'" + chkObs01.getText() + "'";
	}
	else{
	    paramDir = paramDir + "''";
	}
	
	if(chkObs02.isSelected()){
	    paramDir = paramDir + ",'" + chkObs02.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	
	if(chkObs03.isSelected()){
	    paramDir = paramDir + ",'" + chkObs03.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs04.isSelected()){
	    paramDir = paramDir + ",'" + chkObs04.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs05.isSelected()){
	    paramDir = paramDir + ",'" + chkObs05.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs06.isSelected()){
	    paramDir = paramDir + ",'" + chkObs06.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs11.isSelected()){
	    paramDir = paramDir + ",'" + chkObs11.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs12.isSelected()){
	    paramDir = paramDir + ",'" + chkObs12.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	
	if(chkObs07.isSelected()){
	    paramDir = paramDir + ",'" + chkObs07.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs08.isSelected()){
	    paramDir = paramDir + ",'" + chkObs08.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs09.isSelected()){
	    paramDir = paramDir + ",'" + chkObs09.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs10.isSelected()){
	    paramDir = paramDir + ",'" + chkObs10.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	if(chkObs13.isSelected()){
	    paramDir = paramDir + ",'" + chkObs13.getText() + "'";
	}
	else{
	    paramDir = paramDir + ",''";
	}
	return paramDir;
    }
    
    private void limpiarCampos(){
	chkObs01.setSelected(false);
	chkObs01.setSelected(false);
	chkObs02.setSelected(false);
	chkObs03.setSelected(false);
	chkObs04.setSelected(false);
	chkObs05.setSelected(false);
	chkObs06.setSelected(false);
	chkObs07.setSelected(false);
	chkObs08.setSelected(false);
	chkObs09.setSelected(false);
	chkObs10.setSelected(false);
	chkObs11.setSelected(false);
	chkObs12.setSelected(false);
    }
    
    /**2009-12-22(moraless)*/
    private void habilitarOpciones(){
	switch (cbTipoObservacion.getSelectedIndex())  {
	    case DIRECCION: {
		    chkObs01.setEnabled(true);
		    chkObs02.setEnabled(true);
		    chkObs03.setEnabled(true);
		    chkObs04.setEnabled(true);
		    chkObs05.setEnabled(true);
		    chkObs06.setEnabled(true);
		    chkObs07.setEnabled(false);
		    chkObs08.setEnabled(false);
		    chkObs09.setEnabled(false);
		    chkObs10.setEnabled(false);
		    chkObs11.setEnabled(true);
		    chkObs12.setEnabled(true);
		}
		break;
	    case COMERCIO: {
	            chkObs01.setEnabled(false);
	            chkObs02.setEnabled(false);
	            chkObs03.setEnabled(false);
	            chkObs04.setEnabled(false);
	            chkObs05.setEnabled(false);
	            chkObs06.setEnabled(false);
	            chkObs07.setEnabled(true);
	            chkObs08.setEnabled(true);
	            chkObs09.setEnabled(false);
	            chkObs10.setEnabled(false);
	            chkObs11.setEnabled(true);
	            chkObs12.setEnabled(true);
	        }
		break;
	    case PUBLICIDAD: {
	            chkObs01.setEnabled(false);
	            chkObs02.setEnabled(false);
	            chkObs03.setEnabled(false);
	            chkObs04.setEnabled(false);
	            chkObs05.setEnabled(false);
	            chkObs06.setEnabled(false);
	            chkObs07.setEnabled(true);
	            chkObs08.setEnabled(true);
	            chkObs09.setEnabled(true);
	            chkObs10.setEnabled(true);
	            chkObs11.setEnabled(true);
	            chkObs12.setEnabled(true);
	        }
		break;
	}
	
    }
}
