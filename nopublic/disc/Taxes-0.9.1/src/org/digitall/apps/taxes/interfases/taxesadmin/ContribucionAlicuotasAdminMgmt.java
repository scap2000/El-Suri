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
 * ContribucionAlicuotasAdminMgmt.java
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

import org.digitall.apps.taxes.classes.AlicuotaContribucion;
import org.digitall.apps.taxes.classes.Contribucion;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class ContribucionAlicuotasAdminMgmt extends BasicPrimitivePanel{

    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel centerPanel = new BasicPanel();
    private BasicPanel panelContainer = new BasicPanel();
    
    /**Para Contribuciones*/
    private TFInput tfBuscarTipoImpuesto = new TFInput(DataTypes.STRING,"Buscar Impuesto",false);
    private TFInput tfNombreContribucion = new TFInput(DataTypes.STRING,"Nombre",false);
    private TFInput tfDescripcion = new TFInput(DataTypes.STRING,"Descripción",false);
    private TFInput tfFechaInicio = new TFInput(DataTypes.DATE,"Fecha de Inicio",false);
    private TFInput tfFechaFin = new TFInput(DataTypes.DATE,"Fecha de Fin",false);
    private TFInput tfOrdenanza = new TFInput(DataTypes.STRING,"Ordenanza",false);
    private CBInput cbTipoDeImpuesto = new CBInput(0,"Tipo de impuesto", false);
    
    private AssignButton btnSaveData = new AssignButton(true);
    private AddButton btnClearFields = new AddButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private int[] sizeColumnList = {310,100,100,100,100,100};
    private Vector grillaContribucionesHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel grillaContribuciones = new GridPanel(50000, sizeColumnList, "Listado de Contribuciones", dataRow){
	public void finishLoading() {
	    controlBotonesContribuciones();
	}
    };
    
    /**Para alicuotas*/
    private TFInput tfBuscarCuentaDebe = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaHaber = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaDeducciones = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private TFInput tfBuscarCuentaIntreres = new TFInput(DataTypes.STRING,"Buscar Cta.",false);
    private CBInput cbCuentaDebe = new CBInput(0,"Cuenta Debe", false);
    private CBInput cbCuentaHaber = new CBInput(0,"Cuenta Haber", false);
    private CBInput cbCuentaDeducciones = new CBInput(0,"Cuenta Deducciones", false);
    private CBInput cbCuentaIntereses = new CBInput(0,"Cuenta Intereses", false);
    private TFInput tfNombreAlicuota = new TFInput(DataTypes.STRING,"Nombre",false);
    private TFInput tfValorPorMil = new TFInput(DataTypes.DOUBLE,"Valor por mil",false);
    private TFInput tfModulo = new TFInput(DataTypes.DOUBLE,"Valor Módulo",false);
    private TFInput tfPorcentaje = new TFInput(DataTypes.PERCENT,"Porcentaje",false);
    private TFInput tfMultiplicador = new TFInput(DataTypes.INTEGER,"Multiplicador",false);
    private TFInput tfMontoFijo = new TFInput(DataTypes.DOUBLE,"Monto Fijo",false);
    private BasicCheckBox chkMontoFijo = new BasicCheckBox();
    private BasicCheckBox chkLibreDeuda = new BasicCheckBox();
    private BasicCheckBox chkBaja = new BasicCheckBox();
    private BasicCheckBox chkMulta = new BasicCheckBox();
    private TFInput tfPathXml = new TFInput(DataTypes.STRING,"Dirección xml",false);
    
    private AssignButton btnSaveDataAlicuota = new AssignButton(true);
    private AddButton btnClearFieldsAlicuotas = new AddButton();
    private DeleteButton btnDeleteAlicuotas = new DeleteButton();
    
    private int[] sizeColumnListActas = {50,50,50,50,50,50,50,50,50,50,50,50,50,50};
    private Vector grillaAlicuotasHeader = new Vector();
    private Vector dataRowAlicuotas = new Vector();
    private GridPanel grillaAlicuotas = new GridPanel(50000, sizeColumnListActas, "Listado de Alicuotas por Contribución", dataRowAlicuotas){
	public void finishLoading() {
	    controlBotonesAlicuotasContribucion();
	}
    };
    
    private Contribucion contribucion = new Contribucion();
    private AlicuotaContribucion alicuotaContrbucion = new AlicuotaContribucion();

    public ContribucionAlicuotasAdminMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(768, 687));
	/**Para Contribuciones*/
	northPanel.setSize(new Dimension(451, 80));
	northPanel.setPreferredSize(new Dimension(750, 305));
	northPanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	northPanel.setLayout(null);
	tfBuscarTipoImpuesto.setBounds(new Rectangle(10, 90, 125, 35));
	tfNombreContribucion.setBounds(new Rectangle(10, 10, 355, 35));
	tfDescripcion.setBounds(new Rectangle(385, 10, 355, 35));
	tfFechaInicio.setBounds(new Rectangle(10, 50, 125, 35));
	tfFechaFin.setBounds(new Rectangle(240, 50, 125, 35));
	tfBuscarTipoImpuesto.getTextField().addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			cbTipoDeImpuesto.loadJCombo("taxes.getAllTiposImpuestosByFilter","'"+ tfBuscarTipoImpuesto.getString()+"'");
		    }
		}
	    }
	);
	tfOrdenanza.setBounds(new Rectangle(385, 50, 125, 35));
	cbTipoDeImpuesto.setBounds(new Rectangle(150, 90, 360, 35));
	btnSaveData.setBounds(new Rectangle(585, 105, 40, 25));
        btnSaveData.setToolTipText("Agregar contribución");
	btnClearFields.setBounds(new Rectangle(640, 105, 40, 25));
	btnDelete.setBounds(new Rectangle(695, 105, 40, 25));
	grillaContribuciones.setBounds(new Rectangle(10, 130, 735, 170));
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
	northPanel.add(grillaContribuciones, null);
	northPanel.add(tfBuscarTipoImpuesto, null);
	northPanel.add(tfNombreContribucion, null);
	northPanel.add(tfDescripcion, null);
	northPanel.add(tfFechaInicio, null);
	northPanel.add(tfFechaFin, null);
	northPanel.add(tfOrdenanza, null);
	/**Para alicuotas*/
        northPanel.add(btnClearFields, null);
        northPanel.add(btnDelete, null);
        northPanel.add(btnSaveData, null);
        northPanel.add(cbTipoDeImpuesto, null);
	centerPanel.setSize(new Dimension(451, 80));
	centerPanel.setPreferredSize(new Dimension(750, 355));
	centerPanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	centerPanel.setLayout(null);
	tfBuscarCuentaDebe.setBounds(new Rectangle(10, 10, 75, 35));
	tfBuscarCuentaHaber.setBounds(new Rectangle(385, 10, 75, 35));
	cbCuentaDebe.setBounds(new Rectangle(95, 10, 275, 35));
	cbCuentaHaber.setBounds(new Rectangle(470, 10, 275, 35));
	tfBuscarCuentaDeducciones.setBounds(new Rectangle(10, 50, 75, 35));
	tfBuscarCuentaIntreres.setBounds(new Rectangle(385, 50, 75, 35));
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
	btnSaveDataAlicuota.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnSaveDataAlicuotas_actionPerformed(e);
				}
			    }
	);
	btnClearFieldsAlicuotas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClearFieldsAlicuotas_actionPerformed(e);
				}
			    }
	);
	btnDeleteAlicuotas.setEnabled(false);
	btnDeleteAlicuotas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnDeleteAlicuotas_actionPerformed(e);
				}
			    }
	);
	cbCuentaDeducciones.setBounds(new Rectangle(93, 50, 275, 35));
	cbCuentaIntereses.setBounds(new Rectangle(470, 50, 275, 35));
	tfNombreAlicuota.setBounds(new Rectangle(10, 90, 171, 35));
	tfValorPorMil.setBounds(new Rectangle(186, 90, 85, 35));
	tfModulo.setBounds(new Rectangle(276, 90, 85, 35));
	tfPorcentaje.setBounds(new Rectangle(366, 90, 85, 35));
	tfMultiplicador.setBounds(new Rectangle(455, 90, 85, 35));
	tfMontoFijo.setBounds(new Rectangle(545, 90, 85, 35));
	tfPathXml.setBounds(new Rectangle(370, 130, 170, 35));
	chkMontoFijo.setText("Es Monto Fijo");
	chkLibreDeuda.setText("Es Libre Deuda");
	chkBaja.setText("Es Baja");
	chkMulta.setText("Es Multa");
	chkMontoFijo.setBounds(new Rectangle(640, 90, 105, 35));
	chkLibreDeuda.setBounds(new Rectangle(10, 130, 115, 35));
	chkBaja.setBounds(new Rectangle(168, 130, 75, 35));
	chkMulta.setBounds(new Rectangle(275, 130, 85, 35));
	btnSaveDataAlicuota.setBounds(new Rectangle(590, 140, 40, 25));
	btnClearFieldsAlicuotas.setBounds(new Rectangle(645, 140, 40, 25));
	btnDeleteAlicuotas.setBounds(new Rectangle(700, 140, 40, 25));
	grillaAlicuotas.setBounds(new Rectangle(10, 170, 730, 175));
	centerPanel.add(grillaAlicuotas, null);
	centerPanel.add(cbCuentaHaber, null);
	centerPanel.add(cbCuentaDebe, null);
	centerPanel.add(cbCuentaDeducciones, null);
	centerPanel.add(cbCuentaIntereses, null);
	centerPanel.add(tfBuscarCuentaDebe, null);
	centerPanel.add(tfBuscarCuentaHaber, null);
	centerPanel.add(tfBuscarCuentaDeducciones, null);

	centerPanel.add(tfBuscarCuentaIntreres, null);
	centerPanel.add(tfNombreAlicuota, null);
	centerPanel.add(tfValorPorMil, null);
	centerPanel.add(tfModulo, null);
	centerPanel.add(tfPorcentaje, null);

	centerPanel.add(tfMultiplicador, null);
	centerPanel.add(tfPathXml, null);
	centerPanel.add(tfMontoFijo, null);
	centerPanel.add(chkMontoFijo, null);

	centerPanel.add(chkLibreDeuda, null);
	centerPanel.add(chkBaja, null);
	centerPanel.add(chkMulta, null);
	centerPanel.add(btnClearFieldsAlicuotas, null);
	centerPanel.add(btnDeleteAlicuotas, null);
	centerPanel.add(btnSaveDataAlicuota, null);
	panelContainer.add(northPanel, BorderLayout.NORTH);
	panelContainer.add(centerPanel, BorderLayout.CENTER);
	this.add(panelContainer, BorderLayout.CENTER);
	loadCombosAlicuotas();
	loadCombosContribuciones();
	setgrillaContribucionesHeader();
	setgrillaAlicuotasContribucionesHeader();
	grillaContribuciones.refresh("");
	grillaAlicuotas.refresh("");
    }
    
    private void setgrillaContribucionesHeader() {
	grillaContribucionesHeader.removeAllElements();
	grillaContribucionesHeader.addElement("*"); // idcontribucion
	grillaContribucionesHeader.addElement("Nombre");
	grillaContribucionesHeader.addElement("Descripción");
	grillaContribucionesHeader.addElement("Fecha de Inicio");
	grillaContribucionesHeader.addElement("Fecha de Fin");
	grillaContribucionesHeader.addElement("Ordenanza");
	grillaContribucionesHeader.addElement("Tipo de impuesto"); 
	 
     grillaContribuciones.getTable().addMouseListener(new MouseAdapter() {

					      public void mouseClicked(MouseEvent e) {
						  //loadObjectSelected();
						  if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							  //loadMgmt();
						  } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						    loadContribucion();
						    btnDelete.setEnabled(true);
						  }
					      }

					  }
     );
     String params = "";
     grillaContribuciones.setParams("taxes.getAllContribuciones", params, grillaContribucionesHeader);
    }
    
    private void setgrillaAlicuotasContribucionesHeader() {
	grillaAlicuotasHeader.removeAllElements();
	grillaAlicuotasHeader.addElement("*"); // idalicuotacontribucion
	grillaAlicuotasHeader.addElement("*"); // idcontribucion
	grillaAlicuotasHeader.addElement("Nombre");
	grillaAlicuotasHeader.addElement("Valor x mil");
	grillaAlicuotasHeader.addElement("Valor Modulo");
	grillaAlicuotasHeader.addElement("porcentaje");
	grillaAlicuotasHeader.addElement("*"); // estado
	grillaAlicuotasHeader.addElement("Es fijo");
	grillaAlicuotasHeader.addElement("Monto Fijo");
	grillaAlicuotasHeader.addElement("Debe");
	grillaAlicuotasHeader.addElement("Haber");
	grillaAlicuotasHeader.addElement("Deducciones");
	grillaAlicuotasHeader.addElement("Interes");
	grillaAlicuotasHeader.addElement("Es Libre Deuda");
	grillaAlicuotasHeader.addElement("Es Baja");
	grillaAlicuotasHeader.addElement("Es Multa");
	grillaAlicuotasHeader.addElement("xml");
	grillaAlicuotasHeader.addElement("*"); // idcuentadebe
	grillaAlicuotasHeader.addElement("*"); // idcuentahaber
	grillaAlicuotasHeader.addElement("*"); // idcuentadeducciones
	grillaAlicuotasHeader.addElement("*"); // idintereses
	 
     grillaAlicuotas.getTable().addMouseListener(new MouseAdapter() {

					      public void mouseClicked(MouseEvent e) {
						  //loadObjectSelected();
						  if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							  //loadMgmt();
						  } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						    loadAlicuotaContribucion();
						    btnDeleteAlicuotas.setEnabled(true);
						  }
					      }

					  }
     );
     String params = "";
     grillaAlicuotas.setParams("taxes.getAllAlicuotasContribuciones", params, grillaAlicuotasHeader);
    }
    
    private void loadContribucion(){
	clearFieldsContribucion();
	contribucion = new Contribucion();
	contribucion.setIdContribucion(Integer.parseInt(dataRow.elementAt(0).toString()));
	contribucion.retrieveData();
	tfNombreContribucion.setValue(contribucion.getNombre());
	tfDescripcion.setValue(contribucion.getDescripcion());
	tfFechaInicio.setValue("" + Proced.setFormatDate(contribucion.getDesde(),true));
	tfFechaFin.setValue("" + Proced.setFormatDate(contribucion.getHasta(),true));
	tfOrdenanza.setValue(contribucion.getOrdenanza());
	cbTipoDeImpuesto.setSelectedID(contribucion.getIdtipoimpuesto());
	
    }
    
     private void loadAlicuotaContribucion(){
	 clearFieldsAlicuota();
	 alicuotaContrbucion = new AlicuotaContribucion();
	 alicuotaContrbucion.setIdalicuotacontribucion(Integer.parseInt(dataRowAlicuotas.elementAt(0).toString()));
	 alicuotaContrbucion.retrieveData();
	 cbCuentaDebe.setSelectedID(alicuotaContrbucion.getIdcuentadebe());
	 cbCuentaHaber.setSelectedID(alicuotaContrbucion.getIdcuentahaber());
	 cbCuentaDeducciones.setSelectedID(alicuotaContrbucion.getIdCuentaDeduccion());
	 cbCuentaIntereses.setSelectedID(alicuotaContrbucion.getIdCuentaInteres());
	 tfNombreAlicuota.setValue(alicuotaContrbucion.getNombre());
	 tfValorPorMil.setValue(alicuotaContrbucion.getValorxmil());
	 tfModulo.setValue(alicuotaContrbucion.getValormodulo());
	 tfPorcentaje.setValue(alicuotaContrbucion.getPorcentaje());
	 tfMultiplicador.setValue(alicuotaContrbucion.getMultiplicador());
	 tfMontoFijo.setValue(alicuotaContrbucion.getMontofijo());
	 tfPathXml.setValue(alicuotaContrbucion.getXml());
	 chkMontoFijo.setSelected(alicuotaContrbucion.isFijo());
	 chkLibreDeuda.setSelected(alicuotaContrbucion.isLibredeuda());
	 chkBaja.setSelected(alicuotaContrbucion.isBaja());
	 chkMulta.setSelected(alicuotaContrbucion.isMulta());
     }
     
      private void clearFieldsContribucion(){
	  btnDelete.setEnabled(false);
	  contribucion = new Contribucion();
	  tfNombreContribucion.setValue("");
	  tfDescripcion.setValue("");
	  tfFechaInicio.setValue("");
	  tfFechaFin.setValue("");
	  tfOrdenanza.setValue("");
	  tfBuscarTipoImpuesto.setValue("");
	  loadCombosContribuciones();
      }
      
    private void clearFieldsAlicuota(){
	btnDeleteAlicuotas.setEnabled(false);
	alicuotaContrbucion = new AlicuotaContribucion();
	tfNombreAlicuota.setValue("");
	tfBuscarCuentaDebe.setValue("");
	tfBuscarCuentaHaber.setValue("");
	tfBuscarCuentaDeducciones.setValue("");
	tfBuscarCuentaIntreres.setValue("");
	tfValorPorMil.setValue(0.0);
	tfModulo.setValue(0.0);
	tfPorcentaje.setValue(0.0);
	tfMultiplicador.setValue(0);
	tfMontoFijo.setValue(0.0);
	tfPathXml.setValue("");
	chkBaja.setSelected(false);
	chkLibreDeuda.setSelected(false);
	chkMontoFijo.setSelected(false);
	chkMulta.setSelected(false);
	loadCombosAlicuotas();
    }
    
    private void loadCombosAlicuotas(){
	cbCuentaDebe.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaHaber.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaDeducciones.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
	cbCuentaIntereses.loadJCombo("accounting.getAllAccountsForBookKeepingEntryAll", "-1,''");
    }
    
    private void loadCombosContribuciones(){
	cbTipoDeImpuesto.loadJCombo("taxes.getAllTiposImpuestosByFilter","''");
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
	saveDataContribucion();
    }
    
    private void btnClearFields_actionPerformed(ActionEvent e) {
	clearFieldsContribucion();
    }
    
    private void btnDelete_actionPerformed(ActionEvent e) {
	removeContribucion();
    }
    
    private void btnSaveDataAlicuotas_actionPerformed(ActionEvent e) {
	saveDataAlicuota();
    }
    
    private void btnClearFieldsAlicuotas_actionPerformed(ActionEvent e) {
	clearFieldsAlicuota();
    }
    
    private void btnDeleteAlicuotas_actionPerformed(ActionEvent e) {
	removeAlicuotaContribucion();
    }
    
    private void removeAlicuotaContribucion(){
	if(alicuotaContrbucion.getIdalicuotacontribucion() != -1){
	    if(Advisor.question("Borrar Tipo Plan de Pago","Está seguro de borrar la alicuota "+alicuotaContrbucion.getNombre())){
		if(alicuotaContrbucion.delete() != -1){
		    Advisor.messageBox("Se borró la alicuota con éxito","Exito");
		    clearFieldsContribucion();
		    grillaAlicuotas.refresh("");
		} else{
		       Advisor.messageBox("Se produjo un error al grabar los datos","Error");
		}
	    }
	}
    }
    
    private void removeContribucion(){
	if(contribucion.getIdContribucion() != -1){
	    if(Advisor.question("Aviso","Está seguro de borrar la contribución \n\""+contribucion.getNombre() + "\"?")){
		if(contribucion.delete() != -1){
		    Advisor.messageBox("Se borró la contribución con éxito","Exito");
		    clearFieldsContribucion();
		    grillaContribuciones.refresh("");
		} else{
		       Advisor.messageBox("Se produjo un error al grabar los datos","Error");
		}
	    }
	}
    }
    
    private void saveDataAlicuota(){
	if(pasoControlesAlicuota()){
	    if(alicuotaContrbucion.getIdalicuotacontribucion() == -1){
		alicuotaContrbucion = new AlicuotaContribucion();
	    }
	    loadDataAlicuota(alicuotaContrbucion);
	    if(alicuotaContrbucion.saveData() != -1){
		Advisor.messageBox("Se grabó correctamente la Alicuota","Exito");
		grillaAlicuotas.refresh("");
		clearFieldsAlicuota();
	    }else{
		Advisor.messageBox("Se produjo un error al grabar los datos","Error");
	    }
	}else{
	    showMessageAlicuota();
	}
    }
    
    private void loadDataAlicuota(AlicuotaContribucion _alicuota){
	_alicuota.setNombre(tfNombreAlicuota.getString());
	_alicuota.setValorxmil(tfValorPorMil.getDouble());
	_alicuota.setValormodulo(tfModulo.getAmount());
	_alicuota.setPorcentaje(tfPorcentaje.getAmount());
	_alicuota.setMultiplicador(tfMultiplicador.getInteger());
	_alicuota.setFijo(chkMontoFijo.isSelected());
	_alicuota.setMontofijo(tfMontoFijo.getDouble());
	_alicuota.setIdcuentadebe(Integer.parseInt("" +cbCuentaDebe.getSelectedValue()));
	_alicuota.setIdcuentahaber(Integer.parseInt("" +cbCuentaHaber.getSelectedValue()));
	_alicuota.setIdCuentaDeduccion(Integer.parseInt("" +cbCuentaDeducciones.getSelectedValue()));
	_alicuota.setIdCuentaInteres(Integer.parseInt("" +cbCuentaIntereses.getSelectedValue()));
	_alicuota.setLibredeuda(chkLibreDeuda.isSelected());
	_alicuota.setBaja(chkBaja.isSelected());
	_alicuota.setMulta(chkMulta.isSelected());
	_alicuota.setXml(tfPathXml.getString());
    }
    
    private void showMessageAlicuota(){
	    System.out.println("ERROR.......");
	   /* if(error == ERROR_CUENTA_DEBE){ 
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
	    } */
    }
    
    private boolean pasoControlesAlicuota(){
	boolean resultado = true;
	    /*if(cbCuentaDebe.getSelectedIndex() == -1){
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
	    } else if (tfResicion.getValue().equals(""))  {
		resultado = false;
		error = ERROR_RESCISION;
	    } */
	return(resultado);
    }
    
    private void saveDataContribucion() {
	if(pasoControlesContribuciones()) {
	    if(contribucion.getIdContribucion() == -1) {
		contribucion = new Contribucion();
	    }
	    loadData(contribucion);
	    if(contribucion.saveData() != -1) {
		Advisor.messageBox("Se grabó correctamente la contribución","Exito");
		grillaContribuciones.refresh("");
		clearFieldsContribucion();
	    }else{
		Advisor.messageBox("Se produjo un error al grabar los datos","Error");
	    }
	}else{
	    showMessageContribucion();
	}
    }
    
    private void loadData(Contribucion _contribucion){
	_contribucion.setNombre(tfNombreContribucion.getString());
	_contribucion.setDescripcion(tfDescripcion.getString());
	_contribucion.setDesde("" + Proced.setFormatDate(tfFechaInicio.getDate(),false));
	_contribucion.setHasta("" + Proced.setFormatDate(tfFechaFin.getDate(),false));
	_contribucion.setOrdenanza(tfOrdenanza.getString());
	_contribucion.setIdtipoimpuesto(Integer.parseInt("" +cbTipoDeImpuesto.getSelectedValue()));
    }
    
    private boolean pasoControlesContribuciones(){
	boolean resultado = true;
	/*    if(cbCuentaDebe.getSelectedIndex() == -1){
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
	    } else if (tfResicion.getValue().equals(""))  {
		resultado = false;
		error = ERROR_RESCISION;
	    } */
	return(resultado);
    }
    
    private void showMessageContribucion(){
	    System.out.println("ERROR..............");
	    /*if(error == ERROR_CUENTA_DEBE){ 
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
	    } */
    }
    
     private void controlBotonesContribuciones(){
	 clearFieldsContribucion();
     }
     
    private void controlBotonesAlicuotasContribucion(){
	clearFieldsAlicuota();
    }
}
