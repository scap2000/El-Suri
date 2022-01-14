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
 * EntregasList.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.math.BigDecimal;

import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.apps.accionsocial.classes.Beneficiario;
import org.digitall.apps.accionsocial.classes.PlanSocial;
import org.digitall.apps.accionsocial.classes.Prestacion;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorAccionSocial;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class EntregasList extends BasicPrimitivePanel {
    
    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private int[] sizeColumnList = {396, 79,78};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel grillaPersonas = new GridPanel(50000, sizeColumnList, "Listado de Entregas por Beneficiario ", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };

    private SaveDataButton btnSaveData = new SaveDataButton();
    private ModifyButton btnEdit = new ModifyButton();
    private DeleteButton btnDel = new DeleteButton();
    
    private Beneficiario beneficiario = new Beneficiario();
    private PlanSocial planSocial = new PlanSocial();
    private Prestacion prestacion = new Prestacion();
    
    private CBInput cbPlanesSociales = new CBInput(0, "Plan Social", false);
    private CBInput cbPrestaciones = new CBInput(0, "Prestación", false);
    private TFInput tfCantidad = new TFInput(DataTypes.DOUBLE, "Cantidad",false);
    private TFInput tfStock = new TFInput(DataTypes.DOUBLE, "Stock",false);
    private CBInput cbMeses = new CBInput(CachedCombo.MONTHS, "Mes de Entrega.", false);
    private CBInput cbAnios = new CBInput(0, "Año de Entrega.", false);
    private TFInput tfFechaEntrega = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Entrega",false);
    private CBInput cbDespachantes = new CBInput(0, "Despachante", true);
    private BasicLabel lblFormaEntrega = new BasicLabel("Forma de Entrega");
    private BasicRadioButton rbtnEnDomicilio = new BasicRadioButton();
    private BasicRadioButton rbtnEnEstablecimiento = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    
    private String despachanteSeleccionado = "";
    private double cantidadPosibleAAsignar = 0;
    
    private String beneficiariosConProblema01 = "";
    private String beneficiariosConProblema02 = "";

    public EntregasList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(631, 524));
	this.setPreferredSize(new Dimension(710, 515));
        grillaPersonas.setBounds(new Rectangle(5, 165, 615, 315));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 615, 160));
	searchPanel.setLayout(null);
        searchPanel.setBorder(BorderPanel.getBorderPanel("Datos de Entrega de las Prestaciones"));
        btnSaveData.setBounds(new Rectangle(560, 525, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSaveData_actionPerformed(e);
			      }

			  }
	);

        searchPanel.add(cbAnios, null);
        searchPanel.add(cbMeses, null);
        searchPanel.add(rbtnEnEstablecimiento, null);
        searchPanel.add(rbtnEnDomicilio, null);
        searchPanel.add(lblFormaEntrega, null);
        searchPanel.add(cbDespachantes, null);
        searchPanel.add(tfFechaEntrega, null);
        searchPanel.add(tfStock, null);
        searchPanel.add(tfCantidad, null);
        searchPanel.add(cbPrestaciones, null);
        searchPanel.add(cbPlanesSociales, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.setToolTipText("Modificar");

        contentPanel.add(grillaPersonas, null);
        contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
	addButton(btnSaveData); 
	grillaPersonas.getTable().setDragEnabled(true);
	grillaPersonas.getTable().setTransferHandler(new TableTransferHandler());
        btnEdit.setEnabled(false);
	btnDel.setEnabled(false);
        cbPlanesSociales.setBounds(new Rectangle(385, 20, 215, 35));
        cbPlanesSociales.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPlanesSociales.getSelectedIndex() > -1) {
                                                    loadPlanSocial();
                                                }
                                            }
                                        }

                                    }
        );
        cbPrestaciones.setBounds(new Rectangle(10, 60, 240, 35));
        cbPrestaciones.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPrestaciones.getSelectedIndex() > -1) {
                                                    loadPrestacion();
                                                    loadData();
                                                    refresh();
                                                }
                                            }
                                        }

                                    }
        );
        
        cbMeses.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbMeses.getSelectedIndex() > -1) {
                                                    loadPrestacion();
                                                    loadData();
                                                    refresh();
                                                }
                                            }
                                        }

                                    }
        );
        
        cbAnios.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbAnios.getSelectedIndex() > -1) {
                                                    loadPrestacion();
                                                    loadData();
                                                    refresh();
                                                }
                                            }
                                        }

                                    }
        );
        tfCantidad.setBounds(new Rectangle(495, 60, 50, 35));
        tfStock.setBounds(new Rectangle(555, 60, 50, 35));
        tfFechaEntrega.setBounds(new Rectangle(10, 105, 105, 35));
        tfCantidad.setBounds(new Rectangle(260, 60, 60, 35));
        tfStock.setBounds(new Rectangle(330, 60, 50, 35));
        tfFechaEntrega.setBounds(new Rectangle(260, 20, 115, 35));
        cbDespachantes.setBounds(new Rectangle(390, 60, 215, 35));
        lblFormaEntrega.setText("Forma de Entrega");
        lblFormaEntrega.setBounds(new Rectangle(10, 105, 110, 15));
        rbtnEnDomicilio.setText("A Domicilio");
        rbtnEnDomicilio.setBounds(new Rectangle(280, 125, 96, 18));
        rbtnEnEstablecimiento.setText("En Establecimiento");
        rbtnEnEstablecimiento.setBounds(new Rectangle(130, 125, 135, 20));
        cbAnios.setBounds(new Rectangle(135, 20, 115, 35));
        cbMeses.setBounds(new Rectangle(10, 20, 115, 35));
        grupo.add(rbtnEnDomicilio);
        grupo.add(rbtnEnEstablecimiento);
        rbtnEnEstablecimiento.setSelected(true);
        tfFechaEntrega.setValue(Proced.setFormatDate(Environment.currentDate, true));
        setHeaderList();
        loadComboAnios();
        loadCombos();
        tfStock.setEditable(false);
        cbAnios.setSelectedValue(Environment.currentYear);
        cbMeses.setSelectedID(Environment.currentMonth);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.addElement("*");    //idbeneficiario
        headerList.addElement("*");    //idpersona
        headerList.addElement("Apellido y Nombres");
        headerList.addElement("D.N.I.");
        headerList.addElement("Cant. Entr.");
        grillaPersonas.getTable().addMouseListener(new MouseAdapter() { 
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            
                        } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                            
                        }
                    }
                });
                
        String params = "-1,-1,0,0";
        grillaPersonas.setParams("accionsocial.getallbeneficiariosbyfilter", params, headerList);
    }
    
    public void refresh() {
        String params = "" + cbPlanesSociales.getSelectedValue() + "," + cbPrestaciones.getSelectedValue() + "," + cbMeses.getSelectedValue() + "," + cbAnios.getSelectedItem();
        grillaPersonas.refresh(params);
    }
    
    public void loadCombos() {
        loadComboPlanesSociales();
        loadComboDespachantes();
        loadPlanSocial();
        loadComboPrestaciones();
        loadPrestacion();
    }
    
    public void loadComboAnios() {
        cbAnios.removeAllItems();
        int anioActual = Integer.parseInt(Environment.currentYear);
        for (int i = 2009; i <= anioActual+1; i++) {
            cbAnios.addItem("" + i);
        }
    }
    
    public void loadComboPlanesSociales() {
        String params = "";
        cbPlanesSociales.loadJCombo(LibSQL.exFunction("accionsocial.getallplanessociales", params));
        cbPlanesSociales.getCombo().setSelectedIndex(0);
    }
    
    public void loadComboPrestaciones() {
        String params = "" + cbPlanesSociales.getSelectedValue();
        cbPrestaciones.loadJCombo(LibSQL.exFunction("accionsocial.getallprestacionesbyfilter", params));
        if (cbPrestaciones.getCombo().getItemCount() > 0) {
            loadPrestacion();
            loadData();
            habilitarComponentes();
            refresh();
        }
    }
    
    public void loadComboDespachantes() {
        cbDespachantes.loadJCombo(LibSQL.exFunction("accionsocial.getalldespachantes", ""));
    }
    
    private void loadPlanSocial() {
        if( cbPlanesSociales.getCombo().getItemCount() > 0 ) {
            planSocial = new PlanSocial();
            planSocial.setIdPlanSocial(Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()));
            planSocial.retrieveData();
            loadComboPrestaciones();
        }
    }
    
    private void loadPrestacion() {
        if( cbPrestaciones.getCombo().getItemCount() > 0 ) {
            prestacion = new Prestacion();
            prestacion.setIdPrestacion(Integer.parseInt(cbPrestaciones.getSelectedValue().toString()));
            prestacion.retrieveData();
        }
    }
    
    private void loadData() {
        tfStock.setValue(prestacion.getCantDisponible());
    }
    
    private boolean controlBeneficiario() {
        boolean retorno = true;
        if ((beneficiario.getCantidadPrestaciones(prestacion, Integer.parseInt(cbMeses.getSelectedValue().toString()), Integer.parseInt(cbAnios.getSelectedItem().toString())) + tfCantidad.getDouble()) > prestacion.getCantMaxima()) {
            BigDecimal cantidadPosible = new BigDecimal(""+ prestacion.getCantMaxima());
            cantidadPosible = cantidadPosible.subtract(new BigDecimal("" + beneficiario.getCantidadPrestaciones(prestacion, Integer.parseInt(cbMeses.getSelectedValue().toString()), Integer.parseInt(cbAnios.getSelectedItem().toString()))));
            cantidadPosibleAAsignar = cantidadPosible.doubleValue();
            retorno = false;
        }
        return retorno;
    }
    
    
    private boolean controlEntrega() {
        boolean retorno = true;
            if (Integer.parseInt(cbAnios.getSelectedItem().toString()) > Integer.parseInt(Environment.currentYear)) {
                ErrorAccionSocial.setError(ErrorAccionSocial.PERIODO_ENTREGA);
                retorno = false;
            } else if ((Integer.parseInt(cbAnios.getSelectedItem().toString()) == Integer.parseInt(Environment.currentYear))&&(Integer.parseInt(cbMeses.getSelectedValue().toString()) > Integer.parseInt(Environment.currentMonth))) {
                ErrorAccionSocial.setError(ErrorAccionSocial.PERIODO_ENTREGA);
                retorno = false;
            } else if ( tfCantidad.getAmount() > prestacion.getCantMaxima() ) {
                    ErrorAccionSocial.setCantidadMinima(prestacion.getCantMinima());
                    ErrorAccionSocial.setCantidadMaxima(prestacion.getCantMaxima());
                    ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MAYOR_MAXIMO);
                    retorno = false;
            } else if ( tfCantidad.getAmount() < prestacion.getCantMinima() ) {
                    ErrorAccionSocial.setCantidadMinima(prestacion.getCantMinima());
                    ErrorAccionSocial.setCantidadMaxima(prestacion.getCantMaxima());
                    ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MENOR_MINIMO);
                    retorno = false;
            } else if (tfCantidad.getAmount() > prestacion.getCantDisponible()) {
                ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_MAYOR_STOCK);
                retorno = false;
            } else if (tfFechaEntrega.getDate().equals("")) {
                    ErrorAccionSocial.setError(ErrorAccionSocial.FECHA_ENTREGA);
                    retorno = false;
            } else if (Proced.compareDates(Proced.setFormatDate(tfFechaEntrega.getDate(),false),Environment.currentDate) == 2) {
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_FECHA_ASIGNACION_INVALIDA);
                    retorno = false;
            } else if (cbDespachantes.getSelectedIndex() < 0) {
                    ErrorAccionSocial.setError(ErrorAccionSocial.DESPACHANTE_SELECCIONADO);
                    retorno = false;
            } else if (grillaPersonas.getSelectedsVector().size() == 0) {
                retorno = false;
                ErrorAccionSocial.setError(ErrorAccionSocial.BENEFICIARIO_SELECCIONADO);
            } else {
                double cantidadTotal = grillaPersonas.getSelectedsVector().size() * tfCantidad.getDouble();
                if (cantidadTotal > prestacion.getCantDisponible()) {
                    retorno = false;
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_TOTAL_MAYOR_STOCK);
                }
            }
        return retorno;
    }
    
    private void controlBotones(){
        if (grillaPersonas.getTable().getRowCount() > 0) {
            btnSaveData.setEnabled(true);
        } else {
            btnSaveData.setEnabled(false);
        }
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
        boolean retorno = true;
        cantidadPosibleAAsignar = 0;
        beneficiariosConProblema01 = "";
        beneficiariosConProblema02 = "";
        if (controlEntrega()) {
            if (Advisor.question("Aviso", "¿Desea registrar la entrega de la prestación \n\"" + cbPrestaciones.getSelectedItem() + "\" \npara los beneficiarios seleccionados?")) {
                Vector beneficiariosSeleccionados = grillaPersonas.getSelectedsVector();
                for (int i = 0; i < beneficiariosSeleccionados.size(); i++) {
                    Vector beneficiarioSel = (Vector)beneficiariosSeleccionados.elementAt(i);
                    beneficiario = new Beneficiario();
                    beneficiario.setIdBeneficiario(Integer.parseInt(beneficiarioSel.elementAt(0).toString()));
                    beneficiario.retrieveData();
                    if (controlBeneficiario()) {
                        boolean aDomicilio = false;
                        if(rbtnEnDomicilio.isSelected()) {
                            aDomicilio = true;
                        }
                        int id = 0;
                        if (beneficiario.getIdTutor() < 1) {//revisar si la condición es menor que 1 o basta con que sea menor que 0 
                            id = 0;
                        } else {
                            id = beneficiario.getIdTutor();
                        }
                        String params = ""+ beneficiario.getIdBeneficiario() + "," 
                                          + id + ","
                                          + cbPrestaciones.getSelectedValue() + ",'"
                                          + cbMeses.getSelectedValue() + "','" 
                                          + cbAnios.getSelectedItem() + "','" 
                                          + tfFechaEntrega.getDate() + "','" 
                                          + cbDespachantes.getSelectedItem() + "',"
                                          + tfCantidad.getDouble() + ",0,''," 
                                          + aDomicilio + ",0,0";
                        if( LibSQL.getInt("accionsocial.addentrega", params) > 0) {
                            
                        } else {
                            beneficiariosConProblema01 += "- " + beneficiarioSel.elementAt(2) + ": \n";
                        }
                    } else {
                        retorno = false;
                        beneficiariosConProblema02 += "" + beneficiarioSel.elementAt(2) + "\n";
                    }
                }
                mostrarMensaje();
                loadComboDespachantes();
                cbDespachantes.setSelectedValue(despachanteSeleccionado);
                loadPrestacion();
                loadData();
                habilitarComponentes();
                refresh();
            }    
        } else {
            retorno = false;
            ErrorAccionSocial.showMessage();
        }
        return retorno;
    }
    
    private void habilitarComponentes() {
        if (tfStock.getDouble() == 0) {
            tfStock.getTextField().setBackground(Color.red);
            btnSaveData.setEnabled(false);
        } else {
            tfStock.getTextField().setBackground(Color.white);
            btnSaveData.setEnabled(true);
        }
    }

    private void mostrarMensaje() {
        String mensaje = "";
        if (!beneficiariosConProblema01.equals("")) {
            mensaje = "Ocurrió un error al guardar los datos de los siguientes beneficiarios: \n" + beneficiariosConProblema01 ;
        }
        if (!beneficiariosConProblema02.equals("")) {
            mensaje = "\nNo se pueden entregar mas recursos de la prestación\n seleccionada a los siguientes beneficiarios: \n" + beneficiariosConProblema02 ;
        }
        if (!mensaje.equals("")) {
            Advisor.messageBox(mensaje, "Error");
        }
    }
}
