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
 * PlanOfPaymentsAdminMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.taxesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;

import org.digitall.apps.taxes.classes.TipoPlanDePago;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PlanOfPaymentsAdminMgmt extends BasicPrimitivePanel{

    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel centerPanel = new BasicPanel();
    private BasicPanel panelContainer = new BasicPanel();
    
    private TFInput tfBuscarCuentaDebe = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaHaber = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaDeducciones = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaIntreres = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfNombre = new TFInput(DataTypes.STRING,"Nombre",false);
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING,"Descripción",false);
    private TFInput tfFechaInicio = new TFInput(DataTypes.DATE,"Fecha Inicio",false);
    private TFInput tfFechaFin = new TFInput(DataTypes.DATE,"Fecha Fin",false);
    private TFInput tfFechaInicioInsc = new TFInput(DataTypes.DATE,"Fecha Inicio Inscr.",false);
    private TFInput tfFechaFinInsc = new TFInput(DataTypes.DATE,"Fecha Fin Inscr.",false);
    private TFInput tfOrdenanza = new TFInput(DataTypes.STRING,"Ordenanza",false);
    private TFInput tfMinCuotas = new TFInput(DataTypes.INTEGER,"Ctas. Min.",false);
    private TFInput tfMaxCuotas = new TFInput(DataTypes.INTEGER,"Ctas. Max.",false);
    private TFInput tfMaxCuotasVencidas = new TFInput(DataTypes.INTEGER,"Max. ctas. vencidas",false);
    private TFInput tfPorcentajeDtoPagoContado = new TFInput(DataTypes.PERCENT,"% dto. pago contado",false);
    private TFInput tfPorcentajeDtoCancelacion = new TFInput(DataTypes.PERCENT,"% dto. cancelación",false);
    private TFInput tfPorcentajeInteresPorMora = new TFInput(DataTypes.PERCENT,"% interés por mora",false);
    private TFInput tfBuscarCuentaBonifCtdo = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarPlandDePago = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarTipoimpuesto = new TFInput(DataTypes.STRING,"Buscar Tipo Impuesto",false);
    private TFInput tfRescision = new TFInput(DataTypes.STRING,"Rescisión",false);
    private TFInput tfPorcentajeCondonacionIntereses = new TFInput(DataTypes.PERCENT,"% condonación de intereses",false);
    private BasicCheckBox chkObligatorio = new BasicCheckBox("¿Es obligatorio inscribirse?");
    
    private CBInput cbCuentaDebe = new CBInput(0,"Cuenta Debe", false);
    private CBInput cbCuentaHaber = new CBInput(0,"Cuenta Haber", false);
    private CBInput cbCuentaDeducciones = new CBInput(0,"Cuenta Deducciones", false);
    private CBInput cbCuentaIntereses = new CBInput(0,"Cuenta Intereses", false);
    private CBInput cbCuentaBonifCtdo = new CBInput(0,"Cuenta Bonificación Pago cdo.", false);
    private CBInput cbCuentaPlanDePago = new CBInput(0,"Cuenta Plan de Pago", false);
    private CBInput cbTipoImpuesto = new CBInput(0,"Tipo de Impuesto", false);
    
    private int[] sizeColumnList = {46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46};
    private Vector grillaHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel grilla = new GridPanel(50000, sizeColumnList, "Listado de Planes de Pago", dataRow){
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private CloseButton btnClose = new CloseButton();
    private AssignButton btnSaveData = new AssignButton(true);
    private AddButton btnClearFields = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private TipoPlanDePago tipoPlanDePago = new TipoPlanDePago();
    private int error = -1;
    private static int ERROR_CUENTA_DEBE = 1;
    private static int ERROR_CUENTA_HABER = 2;
    private static int ERROR_CUENTA_DEDUCCIONES = 3;
    private static int ERROR_CUENTA_INTERESES = 4;
    private static int ERROR_NOMBRE = 5;
    private static int ERROR_FECHA_INICIO = 6;
    private static int ERROR_FECHA_INICIO_INSC = 7;
    private static int ERROR_FECHA_FIN_INSC = 8;
    private static int ERROR_ORDENANZA = 9;
    private static int ERROR_CUENTA_BONIFICACION_PAGO_CDO = 10;
    private static int ERROR_CUENTA_PLANPAGO = 11;
    private static int ERROR_TIPO_IMPUESTO = 12;
    private static int ERROR_RESCISION = 13;
    private static int ERROR_FIN_MENOR_INICIO = 14;
    private static int ERROR_FIN_INS_MENOR_INICIO_INS = 15;
    private static int ERROR_INICIO_INS_MENOR_INICIO = 16;

    public PlanOfPaymentsAdminMgmt() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(768, 644));
	northPanel.setSize(new Dimension(451, 80));
	northPanel.setPreferredSize(new Dimension(750, 340));
	northPanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	northPanel.setLayout(null);
	tfBuscarCuentaDebe.setBounds(new Rectangle(10, 10, 75, 35));
	tfBuscarCuentaHaber.setBounds(new Rectangle(385, 10, 75, 35));
	cbCuentaDebe.setBounds(new Rectangle(95, 10, 275, 35));
	cbCuentaHaber.setBounds(new Rectangle(470, 10, 275, 35));
	tfBuscarCuentaDeducciones.setBounds(new Rectangle(10, 50, 75, 35));
	tfBuscarCuentaIntreres.setBounds(new Rectangle(385, 50, 75, 35));
	cbCuentaDeducciones.setBounds(new Rectangle(93, 50, 275, 35));
	cbCuentaIntereses.setBounds(new Rectangle(470, 50, 275, 35));
	tfNombre.setBounds(new Rectangle(10, 85, 360, 35));
	tfDescripcion.setBounds(new Rectangle(385, 85, 360, 35));
	tfFechaInicio.setBounds(new Rectangle(10, 120, 115, 35));
	tfFechaFin.setBounds(new Rectangle(255, 120, 115, 35));
	tfFechaInicioInsc.setBounds(new Rectangle(385, 120, 115, 35));
	tfFechaFinInsc.setBounds(new Rectangle(610, 120, 135, 35));
	tfOrdenanza.setBounds(new Rectangle(10, 155, 360, 35));
	tfMinCuotas.setBounds(new Rectangle(385, 155, 65, 35));
	tfMaxCuotas.setBounds(new Rectangle(498, 155, 65, 35));
	tfMaxCuotasVencidas.setBounds(new Rectangle(610, 155, 135, 35));
	tfPorcentajeDtoPagoContado.setBounds(new Rectangle(10, 190, 130, 35));
	tfPorcentajeDtoCancelacion.setBounds(new Rectangle(240, 190, 130, 35));
	tfPorcentajeInteresPorMora.setBounds(new Rectangle(385, 190, 115, 35));
	tfBuscarCuentaBonifCtdo.setBounds(new Rectangle(10, 225, 75, 35));
	tfBuscarPlandDePago.setBounds(new Rectangle(385, 225, 75, 35));
	tfBuscarTipoimpuesto.setBounds(new Rectangle(10, 260, 130, 35));
	tfRescision.setBounds(new Rectangle(10, 295, 695, 35));
	tfPorcentajeCondonacionIntereses.setBounds(new Rectangle(10, 295, 695, 35));
	chkObligatorio.setBounds(new Rectangle(10, 295, 695, 35));
	
	tfBuscarCuentaDebe.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaDebe.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarCuentaDebe.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarCuentaHaber.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaHaber.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarCuentaHaber.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarCuentaDeducciones.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaDeducciones.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarCuentaDeducciones.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarCuentaIntreres.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaIntereses.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarCuentaIntreres.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarCuentaBonifCtdo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaBonifCtdo.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarCuentaBonifCtdo.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarPlandDePago.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbCuentaPlanDePago.loadJCombo("accounting.getAllAccountsForBookKeepingEntry", "-1,'"+ tfBuscarPlandDePago.getString() +"'");
		    }
		}

	    }
	);
	tfBuscarTipoimpuesto.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbTipoImpuesto.loadJCombo("taxes.loadcombotiposimpuestos", "'"+ tfBuscarTipoimpuesto.getString() +"'");
		    }
		}

	    }
	);
	cbCuentaBonifCtdo.setBounds(new Rectangle(93, 225, 275, 35));
	cbCuentaPlanDePago.setBounds(new Rectangle(470, 225, 275, 35));
	cbTipoImpuesto.setBounds(new Rectangle(150, 260, 220, 35));
	grilla.setPreferredSize(new Dimension(750, 250));
	setgrillaHeader();
	btnClose.setToolTipText("Cerrar");
	btnClose.setToolTipText("Grabar Tipo Plan de Pago");
	btnClose.setToolTipText("Borrar Tipo Plan de Pago");
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}
			    }
	);
	btnSaveData.setBounds(new Rectangle(715, 305, 30, 25));
	btnSaveData.setPreferredSize(new Dimension(30, 25));
	btnSaveData.setSize(new Dimension(30, 25));
	btnClearFields.setBounds(new Rectangle(715, 270, 30, 25));
	btnClearFields.setPreferredSize(new Dimension(30, 25));
	btnClearFields.setSize(new Dimension(30, 25));
	btnSaveData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnSaveData_actionPerformed(e);
				}
			    }
	);
	btnClearFields.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClearFields_actionPerformed(e);
				}
			    }
	);
	btnDelete.setEnabled(false);
	btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnDelete_actionPerformed(e);
				}
			    }
	);
	addButton(btnClose);
	addButton(btnDelete);
	northPanel.add(cbCuentaHaber, null);
	northPanel.add(cbCuentaDebe, null);
	northPanel.add(cbCuentaDeducciones, null);
	northPanel.add(cbCuentaIntereses, null);
	northPanel.add(tfBuscarCuentaDebe, null);
	northPanel.add(tfBuscarCuentaHaber, null);
	northPanel.add(tfBuscarCuentaDeducciones, null);
	northPanel.add(tfBuscarCuentaIntreres, null);
	northPanel.add(tfNombre, null);
	northPanel.add(tfDescripcion, null);
	northPanel.add(tfFechaInicio, null);
	northPanel.add(tfFechaFin, null);
	northPanel.add(tfFechaInicioInsc, null);
	northPanel.add(tfFechaFinInsc, null);
	northPanel.add(tfOrdenanza, null);
	northPanel.add(tfMinCuotas, null);
	northPanel.add(tfMaxCuotas, null);
	northPanel.add(tfMaxCuotasVencidas, null);
	northPanel.add(tfPorcentajeDtoPagoContado, null);
	northPanel.add(tfPorcentajeDtoCancelacion, null);
	northPanel.add(tfPorcentajeInteresPorMora, null);
	northPanel.add(tfBuscarCuentaBonifCtdo, null);
	northPanel.add(cbCuentaBonifCtdo, null);
	northPanel.add(cbCuentaPlanDePago, null);
	northPanel.add(cbTipoImpuesto, null);
	northPanel.add(tfBuscarPlandDePago, null);
	northPanel.add(tfBuscarTipoimpuesto, null);
	northPanel.add(tfRescision, null);
	northPanel.add(tfPorcentajeCondonacionIntereses, null);
	northPanel.add(chkObligatorio, null);
	northPanel.add(btnSaveData, null);
	northPanel.add(btnClearFields, null);
	centerPanel.add(grilla, null);
	panelContainer.add(northPanel, BorderLayout.NORTH);
	panelContainer.add(centerPanel, BorderLayout.CENTER);
	this.add(panelContainer, BorderLayout.CENTER);
	grilla.refresh("");
	loadCombos();
    }
    
    private void loadCombos(){
	cbCuentaDebe.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaHaber.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaDeducciones.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaIntereses.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaBonifCtdo.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaPlanDePago.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbTipoImpuesto.loadJCombo("taxes.getAllTiposImpuestosByFilter","''");
    }
    
    private void setgrillaHeader() {
	grillaHeader.removeAllElements();
	grillaHeader.addElement("*"); // idtipoplandepago
	grillaHeader.addElement("Nombre");
	grillaHeader.addElement("Descripción");
	grillaHeader.addElement("Fecha de Inicio");
	grillaHeader.addElement("Fecha de Fin");
	grillaHeader.addElement("Fecha Inicio Insc.");
	grillaHeader.addElement("Fecha Fin Insc.");
	grillaHeader.addElement("Ordenanza");
	
	grillaHeader.addElement("Min. Ctas.");
	grillaHeader.addElement("Max. Ctas.");
	grillaHeader.addElement("Max. Ctas. Vdas.");
	
	grillaHeader.addElement("% dto. pago contado");
	grillaHeader.addElement("% dto. cancelación");
	grillaHeader.addElement("% interes mora");
	
	grillaHeader.addElement("*");//idcuentacaja
	grillaHeader.addElement("*");//idcuentaimpuesto
	grillaHeader.addElement("*");//idcuentabonificacionmoratoria
	grillaHeader.addElement("*");//idcuentainteresxmora
	grillaHeader.addElement("*");//idtipoimpuesto
	
	grillaHeader.addElement("Impuesto");
	grillaHeader.addElement("Debe");
	grillaHeader.addElement("Haber");
	grillaHeader.addElement("Deducción");
	grillaHeader.addElement("Interes");
	 
     grilla.getTable().addMouseListener(new MouseAdapter() {

					      public void mouseClicked(MouseEvent e) {
						  //loadObjectSelected();
						  if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							  //loadMgmt();
						  } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						    loadTipoPlanDePago();
						    btnDelete.setEnabled(true);
						  }
					      }

					  }
     );
     //String params = "'" + tfPersonName.getString() + "','" + tfIdentificationNumber.getString() + "',0" + tfCadastral.getInteger();
     String params = "";
     grilla.setParams("taxes.getAllPlanOfPayments", params, grillaHeader);
    }
    
    private void loadTipoPlanDePago(){
	clearFields();
	tipoPlanDePago = new TipoPlanDePago();
	tipoPlanDePago.setIdTipoPlanDePago(Integer.parseInt(dataRow.elementAt(0).toString()));
	tipoPlanDePago.retrieveData();
	cbCuentaDebe.setSelectedID(tipoPlanDePago.getIdCuentaCaja());
	cbCuentaHaber.setSelectedID(tipoPlanDePago.getIdCuentaImpuesto());
	cbCuentaDeducciones.setSelectedID(tipoPlanDePago.getIdCuentaBonificacionMoratoria());
	cbCuentaIntereses.setSelectedID(tipoPlanDePago.getIdCuentaInteresxMora());
	tfNombre.setValue(tipoPlanDePago.getNombre());
	tfDescripcion.setValue(tipoPlanDePago.getDescripcion());
	tfFechaInicio.setValue("" + Proced.setFormatDate(tipoPlanDePago.getFechaInicio(),true));
	tfFechaFin.setValue("" + Proced.setFormatDate(tipoPlanDePago.getFechaFin(),true));
	tfFechaInicioInsc.setValue("" + Proced.setFormatDate(tipoPlanDePago.getFechaInicioInscripcion(),true));
	tfFechaFinInsc.setValue("" + Proced.setFormatDate(tipoPlanDePago.getFechaFinInscripcion(),true));
	tfOrdenanza.setValue(tipoPlanDePago.getOrdenanza());
	tfMinCuotas.setValue(tipoPlanDePago.getMinCuota());
	tfMaxCuotas.setValue(tipoPlanDePago.getMaxCuotas());
	tfMaxCuotasVencidas.setValue(tipoPlanDePago.getMaxCuotasVencidas());
	tfPorcentajeDtoPagoContado.setValue(tipoPlanDePago.getPorcentDtoPagoContado());
	tfPorcentajeDtoCancelacion.setValue(tipoPlanDePago.getPorcentDtoCancelacion());
	tfPorcentajeInteresPorMora.setValue(tipoPlanDePago.getPorcentInteresMora());
	cbCuentaBonifCtdo.setSelectedID(tipoPlanDePago.getIdCuentaBonificacionContado());
	cbCuentaPlanDePago.setSelectedID(tipoPlanDePago.getIdCuentaPlanDePago());
	cbTipoImpuesto.setSelectedID(tipoPlanDePago.getIdTipoImpuesto());
	tfRescision.setValue(tipoPlanDePago.getRescision());
	tfPorcentajeCondonacionIntereses.setValue(tipoPlanDePago.getPorcentajeCondonacionIntereses());
	chkObligatorio.setSelected(tipoPlanDePago.isObligatorio());
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
	saveDataTipoPlanPago();
    }
    
    private void btnClearFields_actionPerformed(ActionEvent e) {
	clearFields();
    }
    
    private void btnDelete_actionPerformed(ActionEvent e) {
	removeTipoPlanDePago();
    }
    
    private void removeTipoPlanDePago(){
	if(tipoPlanDePago.getIdTipoPlanDePago() != -1){
	    if(Advisor.question("Borrar Tipo Plan de Pago","Está seguro de borrar el tipo plan de pago "+tipoPlanDePago.getNombre())){
		if (!tipoPlanDePago.tienePlanDePago()){
		    if(tipoPlanDePago.delete() != -1){
			Advisor.messageBox("Se borró el tipo de plan de pago con éxito","Exito");
			clearFields();
			grilla.refresh("");
		    } else{
			   Advisor.messageBox("Se produjo un error al grabar los datos","Error");
		    }
		}else{
		    Advisor.messageBox("No puede borrar el tipo de plan depago /n por que está asociado a un plan de pago vigente y/o con moratoria","Error");
		}
	    }
	}
    }
    
    private void clearFields(){
	btnDelete.setEnabled(false);
	tipoPlanDePago = new TipoPlanDePago();
	tfBuscarCuentaDebe.setValue("");
	tfBuscarCuentaHaber.setValue("");
	tfBuscarCuentaDeducciones.setValue("");
	tfBuscarCuentaIntreres.setValue("");
	tfNombre.setValue("");
	tfDescripcion.setValue("");
	tfFechaInicio.setValue("");
	tfFechaFin.setValue("");
	tfFechaInicioInsc.setValue("");
	tfFechaFinInsc.setValue("");
	tfOrdenanza.setValue("");
	tfMinCuotas.setValue(0);
	tfMaxCuotas.setValue(0);
	tfMaxCuotasVencidas.setValue(0);
	tfPorcentajeDtoPagoContado.setValue(0.0);
	tfPorcentajeDtoCancelacion.setValue(0.0);
	tfPorcentajeInteresPorMora.setValue(0.0);
	tfBuscarCuentaBonifCtdo.setValue("");
	tfBuscarPlandDePago.setValue("");
	tfBuscarTipoimpuesto.setValue("");
	tfRescision.setValue("");
	tfPorcentajeCondonacionIntereses.setValue("");
	chkObligatorio.setSelected(false);
	loadCombos();
    }
    
    private void saveDataTipoPlanPago(){
	if(pasoControles()){
	    if(tipoPlanDePago.getIdTipoPlanDePago() == -1){
		tipoPlanDePago = new TipoPlanDePago();
	    }
	    loadData(tipoPlanDePago);
	    if(tipoPlanDePago.saveData() != -1){
		Advisor.messageBox("Se grabó correctamente el tipo de plan de pago","Exito");
		grilla.refresh("");
		clearFields();
	    }else{
	        Advisor.messageBox("Se produjo un error al grabar los datos","Error");
	    }
	}else{
	    showMessage();
	}
    }
    
    private void showMessage(){
	    if(error == ERROR_CUENTA_DEBE){ 
		Advisor.messageBox("Debe seleccionar una cuenta para el Debe", "Mensaje");
	    } else
	    if(error == ERROR_CUENTA_HABER){ 
		Advisor.messageBox("Debe seleccionar una cuenta para el Haber", "Mensaje");
	    } else
	    if(error == ERROR_CUENTA_DEDUCCIONES){ 
		Advisor.messageBox("Debe seleccionar una cuenta para Deducciones", "Mensaje");
	    } else
	    if(error == ERROR_CUENTA_INTERESES){ 
		Advisor.messageBox("Debe seleccionar una cuenta para Intereses", "Mensaje");
	    } else
	    if(error == ERROR_NOMBRE){ 
		Advisor.messageBox("El campo \"Nombre\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_FECHA_INICIO){ 
		Advisor.messageBox("El campo \"Fecha Inicio\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_FECHA_INICIO_INSC){ 
	        Advisor.messageBox("El campo \"Fecha Inicio Inscr.\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_FECHA_FIN_INSC){ 
	        Advisor.messageBox("El campo \"Fecha Fin Inscr.\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_ORDENANZA){ 
	        Advisor.messageBox("El campo \"Ordenanza\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_CUENTA_BONIFICACION_PAGO_CDO){ 
		Advisor.messageBox("Debe seleccionar una cuenta para Bonificación Dto. Pago Contado", "Mensaje");
	    } else
	    if(error == ERROR_CUENTA_PLANPAGO){ 
	        Advisor.messageBox("Debe seleccionar una cuenta para Plan de Pago", "Mensaje");
	    } else
	    if(error == ERROR_TIPO_IMPUESTO){ 
	        Advisor.messageBox("Debe seleccionar un Tipo de impuesto", "Mensaje");
	    } else
	    if(error == ERROR_RESCISION){ 
		Advisor.messageBox("El campo \"Rescisión\" no puede estar vacio", "Mensaje");
	    } else
	    if(error == ERROR_FIN_MENOR_INICIO){ 
	        Advisor.messageBox("La fecha de fin no puede ser menor a la fecha de inicio ", "Mensaje");
	    } else
	    if(error == ERROR_FIN_INS_MENOR_INICIO_INS){ 
	        Advisor.messageBox("La fecha de fin de inscripción no puede ser menor a la fecha de inicio de inscripción ", "Mensaje");
	    } else
	    if(error == ERROR_INICIO_INS_MENOR_INICIO){ 
	        Advisor.messageBox("La fecha de inicio de inscripción no puede ser menor a la fecha de inicio", "Mensaje");
	    } 
    }
    
    private boolean pasoControles(){
	boolean resultado = true;
	    if(cbCuentaDebe.getSelectedIndex() == -1){
	        resultado = false;
	        error = ERROR_CUENTA_DEBE;
	    } else if (cbCuentaHaber.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_CUENTA_HABER;
	    } else if (cbCuentaDeducciones.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_CUENTA_DEDUCCIONES;
	    } else if (cbCuentaIntereses.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_CUENTA_INTERESES;
	    } else if (tfNombre.getValue().equals(""))  {
		resultado = false;
		error = ERROR_NOMBRE;
	    } else if (tfFechaInicio.getString().equals(""))  {
		resultado = false;
		error = ERROR_FECHA_INICIO;
	    } else if (tfFechaInicioInsc.getString().equals(""))  {
		resultado = false;
		error = ERROR_FECHA_INICIO_INSC;
	    } else if (tfFechaFinInsc.getString().equals(""))  {
		resultado = false;
		error = ERROR_FECHA_FIN_INSC;
	    } else if (Proced.compareDates(Proced.setFormatDate(tfFechaInicio.getString(),false),Proced.setFormatDate(tfFechaFin.getString(),false)) == 2) {
	        resultado = false;
	        error = ERROR_FIN_MENOR_INICIO;
	    } else if (Proced.compareDates(Proced.setFormatDate(tfFechaInicioInsc.getString(),false),Proced.setFormatDate(tfFechaFinInsc.getString(),false)) == 2) {
	        resultado = false;
	        error = ERROR_FIN_INS_MENOR_INICIO_INS;
	    } else if (Proced.compareDates(Proced.setFormatDate(tfFechaInicio.getString(),false),Proced.setFormatDate(tfFechaFin.getString(),false)) == 2) {
	        resultado = false;
	        error = ERROR_INICIO_INS_MENOR_INICIO;
	    } else if (tfOrdenanza.getValue().equals(""))  {
		resultado = false;
		error = ERROR_ORDENANZA;
	    } else if (cbCuentaBonifCtdo.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_CUENTA_BONIFICACION_PAGO_CDO;
	    } else if (cbCuentaPlanDePago.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_CUENTA_PLANPAGO;
	    } else if (cbTipoImpuesto.getSelectedIndex() == -1){
		resultado = false;
		error = ERROR_TIPO_IMPUESTO;
	    } else if (tfRescision.getValue().equals(""))  {
		resultado = false;
		error = ERROR_RESCISION;
	    } 
	return(resultado);
    }
    
    private void loadData(TipoPlanDePago _tipoPlanDePago){
	_tipoPlanDePago.setNombre(tfNombre.getString());
	_tipoPlanDePago.setDescripcion(tfDescripcion.getString());
	_tipoPlanDePago.setFechaInicio("" + Proced.setFormatDate(tfFechaInicio.getDate(),false));
	_tipoPlanDePago.setFechaFin("" + Proced.setFormatDate(tfFechaFin.getDate(),false));
	_tipoPlanDePago.setFechaInicioInscripcion("" + Proced.setFormatDate(tfFechaInicioInsc.getDate(),false));
	_tipoPlanDePago.setFechaFinInscripcion("" + Proced.setFormatDate(tfFechaFinInsc.getDate(),false));
	_tipoPlanDePago.setIdTipoImpuesto(Integer.parseInt("" +cbTipoImpuesto.getSelectedValue()));
	_tipoPlanDePago.setIdCuentaCaja(Integer.parseInt("" +cbCuentaDebe.getSelectedValue()));
	_tipoPlanDePago.setIdCuentaImpuesto(Integer.parseInt("" +cbCuentaHaber.getSelectedValue()));
	_tipoPlanDePago.setIdCuentaBonificacionMoratoria(Integer.parseInt("" +cbCuentaDeducciones.getSelectedValue()));
	_tipoPlanDePago.setIdCuentaInteresxMora(Integer.parseInt("" +cbCuentaIntereses.getSelectedValue()));
	_tipoPlanDePago.setOrdenanza(tfOrdenanza.getString());
	_tipoPlanDePago.setMinCuota(tfMinCuotas.getInteger());
	_tipoPlanDePago.setMaxCuotas(tfMaxCuotas.getInteger());
	_tipoPlanDePago.setMaxCuotasVencidas(tfMaxCuotasVencidas.getInteger());
	_tipoPlanDePago.setPorcentDtoPagoContado(tfPorcentajeDtoPagoContado.getDouble());
	_tipoPlanDePago.setPorcentDtoCancelacion(tfPorcentajeDtoCancelacion.getDouble());
	_tipoPlanDePago.setPorcentInteresMora(tfPorcentajeInteresPorMora.getDouble());
	_tipoPlanDePago.setIdCuentaBonificacionContado(Integer.parseInt("" +cbCuentaBonifCtdo.getSelectedValue()));
	_tipoPlanDePago.setIdCuentaPlanDePago(Integer.parseInt("" +cbCuentaPlanDePago.getSelectedValue()));
	_tipoPlanDePago.setRescision(tfRescision.getString());
	_tipoPlanDePago.setPorcentajeCondonacionIntereses(tfPorcentajeCondonacionIntereses.getAmount());
	_tipoPlanDePago.setObligatorio(chkObligatorio.isSelected());
    }
    
    private void controlBotones(){
	clearFields();
    }
}
