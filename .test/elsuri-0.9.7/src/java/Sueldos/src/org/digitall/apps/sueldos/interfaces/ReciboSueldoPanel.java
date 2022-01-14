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
 * ReciboSueldoPanel.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Types;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;

import org.digitall.apps.sueldos.classes.Conceptos;
import org.digitall.apps.sueldos.classes.LiquidacionSueldos;
import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.apps.sueldos.classes.Liquidaciones;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ReciboSueldoPanel extends BasicPanel{
    
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel panelSur = new BasicPanel();
    private TFInput tfLegajo = new TFInput(DataTypes.INTEGER, "Legajo",false);
    private TFInput tfEmpleado = new TFInput(DataTypes.STRING, "Empleado",false);
    private TFInput tfCargo = new TFInput(DataTypes.STRING, "Cargo",false);
    private TFInput tfCategoria = new TFInput(DataTypes.STRING, "Cat.",false);
    private TFInput tfIngreso = new TFInput(DataTypes.DATE, "Ingreso",false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING, "Cuil",false);

    private TFInput tfFechaLiquidacion = new TFInput(DataTypes.SIMPLEDATE, "Fecha Liquidación",false);
    private TFInput tfTotalHaberes = new TFInput(DataTypes.MONEY, "Total Haberes",false);
    private TFInput tfTotalDescuentos = new TFInput(DataTypes.MONEY, "Total Descuentos",false);
    private TFInput tfNeto = new TFInput(DataTypes.MONEY, "Neto a Cobrar",false);
    private int[] sizeColumnList = {243,95,84};
    private Vector haberHeader = new Vector();
    private Vector descuentoHeader = new Vector();
    private Vector haberesDataRow = new Vector();
    private Vector descuentosDataRow = new Vector();
    
    private GridPanel grillaHaberes = new GridPanel(1000, sizeColumnList, "", haberesDataRow){
	public void calculate(){
	    calcularTotal();
	}
        public void finishLoading() {
            btnBorrarItemDescuento.setEnabled(false);
            btnBorrarItemHaber.setEnabled(false);
        }
    };
    private GridPanel grillaDescuentos = new GridPanel(1000, sizeColumnList, "", descuentosDataRow){
	public void calculate(){
	    calcularTotal();
	}
        
        public void finishLoading() {
            btnBorrarItemDescuento.setEnabled(false);
            btnBorrarItemHaber.setEnabled(false);
        }
    };
    private JButton btnAccept = new JButton();
    private LegajosSearchPanel legajosSearchPanel = new LegajosSearchPanel();
    private Legajo legajo = new Legajo();
    
    private double totalHaberes = 0;
    private double totalDescuentos = 0;
    private double total = 0;

    private JMyMoneyEntry tfMoney = new JMyMoneyEntry();
    private DeleteButton btnBorrarItemHaber = new DeleteButton();
    private DeleteButton btnBorrarItemDescuento = new DeleteButton();
    private UnAssignButton btnAgregarItemHaber = new UnAssignButton(true);
    private UnAssignButton btnAgregarItemDescuento = new UnAssignButton(true);
    private CBInput cbHaberesNoAsignados = new CBInput(0,"Haberes no asignados");
    private CBInput cbDescuentosNoAsignados = new CBInput(0,"Descuentos no asignados");
    
    private LiquidacionSueldos liquidacionSueldos = new LiquidacionSueldos();

    public ReciboSueldoPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	tfLegajo.setBounds(new Rectangle(200, 5, 45, 35));
	tfEmpleado.setBounds(new Rectangle(260, 5, 265, 35));
	tfCargo.setBounds(new Rectangle(535, 5, 140, 35));
	tfCategoria.setBounds(new Rectangle(685, 5, 30, 35));
	tfIngreso.setBounds(new Rectangle(725, 5, 80, 35));
	tfCuil.setBounds(new Rectangle(815, 5, 110, 35));
	tfFechaLiquidacion.setBounds(new Rectangle(5, 55, 120, 35));
	tfFechaLiquidacion.setValue(Proced.setFormatDate(Environment.currentDate,true));
        tfFechaLiquidacion.setEnabled(false);
	tfTotalHaberes.setBounds(new Rectangle(325, 5, 135, 35));
	tfTotalDescuentos.setBounds(new Rectangle(790, 5, 135, 35));
	tfNeto.setBounds(new Rectangle(790, 55, 135, 35));
	tfLegajo.setEnabled(true);
	tfLegajo.setEditable(true);
	tfEmpleado.setEnabled(false);
	tfCargo.setEnabled(false);
	tfCategoria.setEnabled(false);
	
	tfLegajo.getTextField().addKeyListener(new KeyListener(){
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			keyPress(e);
		}

		public void keyReleased(KeyEvent e) {
		}
	    });
	tfIngreso.setEnabled(false);
	tfCuil.setEnabled(false);
	tfTotalHaberes.setEnabled(false);
	tfTotalDescuentos.setEnabled(false);
	tfNeto.setEnabled(false);
        btnAccept.setText("<html><p align=center>Grabar liquidación <br><p align=center>parcial</html>");
	btnAccept.setBounds(new Rectangle(395, 45, 145, 45));
	btnAccept.setSize(new Dimension(145, 45));
	btnAccept.setPreferredSize(new Dimension(30, 20));
        btnAccept.setOpaque(true);
        btnAccept.setFont(new Font("Dialog", 1, 12));
        btnAccept.setForeground(Color.white);
        btnAccept.setBackground(new Color(165, 41, 0));
        btnAccept.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAccept.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnAccept_actionPerformed(e);
			       }
			   }
	);
        btnBorrarItemHaber.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnBorrarItemHaber_actionPerformed(e);
                               }
                           }
        );
        btnBorrarItemDescuento.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {
                                   btnBorrarItemDescuento_actionPerformed(e);
                               }
                           }
        );
	btnAccept.setToolTipText("Grabar liquidación parcial del Recibo");
	legajosSearchPanel.setPreferredSize(new Dimension(185, 35));
	legajosSearchPanel.setBounds(new Rectangle(5, 5, 185, 35));
	legajosSearchPanel.setBounds(new Rectangle(5, 5, 185, 35));
	panelNorte.setBorder(BorderPanel.getBorderPanel(""));
	panelSur.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setLayout(null);
	panelSur.setLayout(null);
	panelNorte.setLayout(null);
	panelNorte.setMinimumSize(new Dimension(932, 45));
	panelNorte.setPreferredSize(new Dimension(932, 45));
	panelCentro.setMinimumSize(new Dimension(932, 385));
	panelCentro.setPreferredSize(new Dimension(932, 385));
	panelSur.setMinimumSize(new Dimension(932, 95));
	panelSur.setPreferredSize(new Dimension(932, 95));
	panelNorte.add(legajosSearchPanel, null);
	panelNorte.add(tfLegajo,null);
	panelNorte.add(tfEmpleado,null);
	panelNorte.add(tfCargo,null);
	panelNorte.add(tfCategoria,null);
	panelNorte.add(tfIngreso,null);
	panelNorte.add(tfCuil,null);
	//panelSur.add(tfMoney, null);
        panelSur.add(tfFechaLiquidacion,null);
        panelSur.add(tfTotalHaberes,null);
        panelSur.add(tfTotalDescuentos,null);
        panelSur.add(tfNeto,null);
        panelSur.add(btnAccept,null);
        panelSur.add(btnBorrarItemHaber,null);
        panelSur.add(btnBorrarItemDescuento,null);
        panelSur.add(btnAgregarItemHaber,null);
        panelSur.add(btnAgregarItemDescuento,null);
        panelSur.add(cbHaberesNoAsignados,null);
        panelSur.add(cbDescuentosNoAsignados,null);
        panelSur.add(btnAgregarItemDescuento,null);
        grillaHaberes.setBounds(new Rectangle(5, 5, 460, 390));
	grillaDescuentos.setBounds(new Rectangle(470, 5, 460, 390));
	this.setSize(new Dimension(935, 545));
	this.setPreferredSize(new Dimension(935, 545));
	grillaHaberes.getTable().setDragEnabled(true);
	grillaDescuentos.getTable().setDragEnabled(true);
	setHaberesHeader();
	setDescuentosHeader();
	grillaHaberes.setCellEditor(Types.DOUBLE, 2);
	grillaDescuentos.setCellEditor(Types.DOUBLE, 2);
	grillaHaberes.getTable().addKeyListener(new KeyAdapter() {

			  public void keyReleased(KeyEvent e) {
			      try {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
				      grillaHaberes.getTable().hasFocus();
				      int filaSeleccionada = 0;
				      if(grillaDescuentos.getTable().getSelectedRow() > -1){
					  filaSeleccionada = grillaDescuentos.getTable().getSelectedRow();
				      }
				      grillaDescuentos.getTable().getSelectionModel().setSelectionInterval(1,filaSeleccionada);
				      grillaDescuentos.getTable().setColumnSelectionInterval(1,1);
				      grillaDescuentos.getTable().requestFocus();
				}
			      } catch (ArrayIndexOutOfBoundsException x) {
			          e.consume();
			      }
			  }

	});
	grillaDescuentos.getTable().addKeyListener(new KeyAdapter() {
			  
			  public void keyReleased(KeyEvent e) {
			      keyTyped(e);
			  }
			  public void keyTyped(KeyEvent e) {
			     try {
			      if (e.getKeyCode() == KeyEvent.VK_TAB) {
				 grillaDescuentos.getTable().hasFocus();
				 int filaSeleccionada = 0;
				 if(grillaHaberes.getTable().getSelectedRow() > -1){
				    filaSeleccionada = grillaHaberes.getTable().getSelectedRow();
			          }
				  grillaHaberes.getTable().getSelectionModel().setSelectionInterval(1,filaSeleccionada);
				  grillaHaberes.getTable().setColumnSelectionInterval(1,1);
				  grillaHaberes.getTable().requestFocus();
				}
			      } catch (ArrayIndexOutOfBoundsException x) {
				  e.consume();
			      }
			  }
	});
	panelCentro.add(grillaHaberes, null);
	panelCentro.add(grillaDescuentos, null);
	grillaHaberes.setBorder(null);
	grillaDescuentos.setBorder(null);
	
	grillaHaberes.removeControls();
	grillaDescuentos.removeControls();
        
	this.add(panelNorte,BorderLayout.NORTH);
	this.add(panelCentro,BorderLayout.CENTER);
	this.add(panelSur,BorderLayout.SOUTH);
	legajosSearchPanel.setRecibosPanel(this);
	tfMoney.setBounds(new Rectangle(255, 45, 205, 25));
        btnBorrarItemHaber.setBounds(new Rectangle(280, 15, 40, 25));
        btnBorrarItemDescuento.setBounds(new Rectangle(750, 15, 40, 25));
        btnAgregarItemHaber.setBounds(new Rectangle(235, 15, 40, 25));
        btnAgregarItemDescuento.setBounds(new Rectangle(705, 15, 40, 25));
        cbHaberesNoAsignados.setBounds(new Rectangle(5, 5, 230, 35));
        cbDescuentosNoAsignados.setBounds(new Rectangle(470, 5, 230, 35));
        legajosSearchPanel.pressFirst();
	cargarGrillas();
        btnBorrarItemHaber.setEnabled(false);      
        btnBorrarItemDescuento.setEnabled(false);      
        cbHaberesNoAsignados.autoSize();
        cbDescuentosNoAsignados.autoSize();
        btnAgregarItemHaber.setEnabled(true);
        btnAgregarItemHaber.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAgregarItemHaber_actionPerformed(e);
                }
            });
        btnAgregarItemDescuento.setEnabled(true);
        btnAgregarItemDescuento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAgregarItemDescuento_actionPerformed(e);
                }
            });
        cbHaberesNoAsignados.getCombo().addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    btnAgregarItemHaber.setEnabled(true);
                } else {
                    btnAgregarItemHaber.setEnabled(false);
                }
            }
        });
        
        cbDescuentosNoAsignados.getCombo().addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    btnAgregarItemDescuento.setEnabled(true);
                } else {
                    btnAgregarItemDescuento.setEnabled(false);
                }
            }
        });
        btnAgregarItemHaber.setToolTipText("Agregar concepto haberes a recibo");
        btnAgregarItemDescuento.setToolTipText("Agregar concepto descuentos a recibo");
        btnBorrarItemHaber.setToolTipText("Borrar concepto haberes a recibo");
        btnBorrarItemDescuento.setToolTipText("Borrar concepto descuentos a recibo");
        
    }
    
    private void setHaberesHeader() {
	haberHeader.removeAllElements();
	haberHeader.addElement("*"); //idconceptolegajo
	haberHeader.addElement("*"); //idconcepto
	haberHeader.addElement("*"); //idlegajo
	haberHeader.addElement("Haber");
	haberHeader.addElement("Cantidad");
	haberHeader.addElement("Monto");
	 
	grillaHaberes.getTable().addMouseListener(new MouseAdapter() {
					      public void mouseClicked(MouseEvent e) {
						  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                      if (!haberesDataRow.isEmpty()) {
                                                          btnBorrarItemHaber.setEnabled(true);      
                                                      } else {
                                                          btnBorrarItemHaber.setEnabled(false);      
                                                      }
						  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						  }
					      }
					  }
	);
	String params = "" + legajo.getidLegajo();
	//grillaHaberes.setParams("sueldos.gethaberesforlegajo", params, haberHeader);
	grillaHaberes.setParams("sueldos.getultimoshaberesporlegajo", params, haberHeader);
    }
    
    private void setDescuentosHeader() {
	descuentoHeader.removeAllElements();
	descuentoHeader.addElement("*"); //idconceptolegajo
	descuentoHeader.addElement("*"); //idconcepto
	descuentoHeader.addElement("*"); //idlegajo
	descuentoHeader.addElement("Descuento");
	descuentoHeader.addElement("Cantidad");
	descuentoHeader.addElement("Monto");
	 
	grillaDescuentos.getTable().addMouseListener(new MouseAdapter() {
					      public void mouseClicked(MouseEvent e) {
						  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						      if (!descuentosDataRow.isEmpty()) {
						          btnBorrarItemDescuento.setEnabled(true);      
						      } else {
						          btnBorrarItemDescuento.setEnabled(false);      
						      }
						  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                      
						  }
					      }
					  }
	);
	String params = ""+legajo.getidLegajo();
	grillaDescuentos.setParams("sueldos.getultimosdescuentosporlegajo", params, descuentoHeader);
    }
    
    public void refreshHaberes() {
	String params = ""+legajo.getidLegajo();
	grillaHaberes.refresh(params);
    }
    
    public void refreshDescuentos() {
	String params = ""+legajo.getidLegajo();
	grillaDescuentos.refresh(params);
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	// Grabar Recibo de sueldo parcial
	grabarLiquidacionParcial(true,true);
    }
    
    private void btnBorrarItemHaber_actionPerformed(ActionEvent e) {
        quitarConceptos(1);
    }
    
    private void btnBorrarItemDescuento_actionPerformed(ActionEvent e) {
        quitarConceptos(-1);
    }
    
    private void btnAgregarItemHaber_actionPerformed(ActionEvent e) {
        agregarConceptos(1);
    }

    private void btnAgregarItemDescuento_actionPerformed(ActionEvent e) {
        agregarConceptos(-1);
    }
    
    private void quitarConceptos(int _debehaber){
        String nombreConcepto = "";
        Vector _idsPersons = new Vector();
        Vector _conceptos = new Vector();
        _idsPersons.add(legajo.getidPerson());
        if (_debehaber == 1) {
            _conceptos.add(haberesDataRow.elementAt(1).toString());
            nombreConcepto = haberesDataRow.elementAt(3).toString();
        } else {
            _conceptos.add(descuentosDataRow.elementAt(1).toString());
            nombreConcepto = descuentosDataRow.elementAt(3).toString();
        }
        if (Advisor.question("Pregunta", "¿Está seguro de eliminar el concepto " + nombreConcepto+ "?\nSe guardarán los cambios como liquidación parcial!.")) {
            //Quitar por stored al legajo el concepto seleccionado
             LibSQL.getInt("sueldos.deleteConceptoALegajo", "" + legajo.getidLegajo() + "," + _conceptos.elementAt(0).toString());
            //Grabar la liquidacion parcial dando aviso y sin refrescar
            grabarLiquidacionParcial(true,false);
            // Agregar con un stored una tupla al detalle de liquidacion y recalcular los totales
            LibSQL.getInt("sueldos.deleteDetalleLiquidacionParcial", "" + legajo.getidLegajo() + "," + _conceptos.elementAt(0).toString());
            cargarGrillas();
            cargarCombos();
            
        }
    }
    
    private void agregarConceptos(int _debehaber){
        String nombreConcepto = "";
        Vector _idsPersons = new Vector();
        Vector _conceptos = new Vector();
        _idsPersons.add(legajo.getidPerson());
        if (_debehaber == 1) {
            _conceptos.add(cbHaberesNoAsignados.getSelectedValue());
            nombreConcepto = cbHaberesNoAsignados.getSelectedItem().toString();
        } else {
            _conceptos.add(cbDescuentosNoAsignados.getSelectedValue());
            nombreConcepto = cbDescuentosNoAsignados.getSelectedItem().toString();
        }
        if (Advisor.question("Pregunta", "¿Está seguro de agregar el concepto " + nombreConcepto+ "?\nSe guardarán los cambios como liquidación parcial!.")) {
            //Agregar por stored al legajo el concepto seleccionado
             LibSQL.getInt("sueldos.addConceptoALegajo", "" + legajo.getidLegajo() + "," + _conceptos.elementAt(0).toString());
            //Grabar la liquidacion parcial dando aviso y sin refrescar
            grabarLiquidacionParcial(true,false);
            // Agregar con un stored una tupla al detalle de liquidacion y recalcular los totales
            LibSQL.getInt("sueldos.addDetalleLiquidacionParcial", "" + legajo.getidLegajo() + "," + _conceptos.elementAt(0).toString());
            cargarGrillas();
            cargarCombos();
        }
    }
    
    
    private void grabarLiquidacionParcial(boolean _aviso, boolean _refresh){
	Vector haberesSeleccionados = grillaHaberes.getAllValues();
	Vector descuentosSeleccionados = grillaDescuentos.getAllValues();
	Vector haberesForDetail = toDetail(haberesSeleccionados);
	Vector descuentosForDetail = toDetail(descuentosSeleccionados);
	if((haberesSeleccionados.size() > 0)||(descuentosSeleccionados.size() > 0)){
	    Liquidaciones encabezado = new Liquidaciones();
	    encabezado.setIsParcial(true);
	    encabezado.setIdlegajo(legajo.getidLegajo());
	    encabezado.setImporteDebe(Double.parseDouble(tfTotalDescuentos.getValue().toString()));
	    encabezado.setImporteHaber(Double.parseDouble(tfTotalHaberes.getValue().toString()));
	    encabezado.setTotal(Double.parseDouble(tfNeto.getValue().toString()));
	    encabezado.setFechaliquidacion(Proced.setFormatDate(tfFechaLiquidacion.getDate(), false));
	    encabezado.setDetalleHaberes(haberesForDetail);
	    encabezado.setDetalleDescuentos(descuentosForDetail);
	    if(encabezado.saveData() != -1){
                if (_aviso) {
                    Advisor.messageBox("Se grabó con éxito la liquidación parcial.","Aviso");
                    if (_refresh) {
                        cargarGrillas();
                        cargarCombos();
                    }
                }
	    }else{
	        Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
	    }
	}
    }
    
    private Vector toDetail(Vector _concept){
	Vector resultado = new Vector();
	for(int i = 0; i < _concept.size(); i++){
	    Vector _element = (Vector)_concept.elementAt(i);
	    Vector aux = new Vector();
	    aux.add(_element.elementAt(2).toString());
	    aux.add(_element.elementAt(4).toString());
	    aux.add(_element.elementAt(6).toString());
	    resultado.add(aux);
	}
	return(resultado);
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	return(resultado);
    }
    
    public String getFecha(){
	return (Proced.setFormatDate(tfFechaLiquidacion.getDate(), false));
    }

    private void keyPress(KeyEvent e) {
        if (!tfLegajo.getValue().toString().equals("")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int numeroLegajo = -1;
                numeroLegajo =
                        legajosSearchPanel.setActualLegajo(Integer.parseInt(tfLegajo.getTextField().getText().toString().trim()));
                if (numeroLegajo != tfLegajo.getInteger()) {
                    tfLegajo.getTextField().setText("" + numeroLegajo);
                }
            }
        } else {
            Advisor.messageBox("Debe ingresar un número de legajo", "Error");
        }
    }

    public void setLegajo(Legajo legajo) {
	this.legajo = legajo;
	tfLegajo.setValue(legajo.getNumero());
	tfEmpleado.setValue(legajo.getApellidoPersona() +", "+ legajo.getNombresPersona());
	tfCuil.setValue(legajo.getIdentifactionNumber());
	tfCargo.setValue(legajo.getCargo());
	tfCategoria.setValue(legajo.getCategoria());
	cargarGrillas();
        cargarCombos();
    }
    
    private void cargarCombos(){
        cbHaberesNoAsignados.loadJCombo("sueldos.getAllHaberesNoAsignados", "" + legajo.getidLegajo());
        cbDescuentosNoAsignados.loadJCombo("sueldos.getAllDescuentosNoAsignados", "" + legajo.getidLegajo());
        
        if (cbHaberesNoAsignados.getCombo().getItemCount() > 0) {
            cbHaberesNoAsignados.getCombo().setSelectedIndex(1);
            btnAgregarItemHaber.setEnabled(true);
        } else {
            btnAgregarItemHaber.setEnabled(false);
        }
        
        if (cbDescuentosNoAsignados.getCombo().getItemCount() > 0) {
            cbDescuentosNoAsignados.getCombo().setSelectedIndex(1);
            btnAgregarItemDescuento.setEnabled(true);
        } else {
            btnAgregarItemDescuento.setEnabled(false);
        }
    }
    
    private void cargarGrillas(){
	refreshHaberes();
	refreshDescuentos();
    }
    
    private void calcularTotal(){
	//Calcular Total Haberes
	calcularTotalHaberes();
	//Calcular Total Descuentos
	calcularTotalDescuentos();
	//Calcular Neto a Cobrar
	 total = totalHaberes - totalDescuentos;
	 tfNeto.setValue(total);
    }
    
    private void calcularTotalHaberes(){
	totalHaberes = 0;
	Vector values = grillaHaberes.getValuesInTableAt("Monto");
	for(int i = 0; i < values.size(); i++){
	    double valor = Double.parseDouble(values.elementAt(i).toString());
	    if (valor >= 0) {
		totalHaberes += valor;
	    } else {
		System.err.println("Error, valor negativo");
	    }
	}
	tfTotalHaberes.setValue(totalHaberes);
    }
    private void calcularTotalDescuentos(){
        //poner la condicion que  no puede ingresar un valor mayor a la diferencia de anticipos de haberes
	totalDescuentos = 0;
	Vector values = grillaDescuentos.getValuesInTableAt("Monto");
	for(int i = 0; i < values.size(); i++){
	    double valor = Double.parseDouble(values.elementAt(i).toString());
	    if (valor >= 0) {
	        totalDescuentos += valor;
	    } else {
	        System.err.println("Error, valor negativo");
	    }
	}
	tfTotalDescuentos.setValue(totalDescuentos);
    }
    
    public Legajo getLegajo() {
	return legajo;
    }

    
    /*
     * El resto del código lo dejo por si alguna vez haya que importar conceptos desde archivos 
     * preguntar si debo sacarlo definitivamente
     */
    private void code() {
	//boolean initTipos = LibSQL.execute("INSERT INTO tabs.concepto_tabs(idtipo, descripcion,estado) VALUES (-1, 'Sin Asignar', '')");
	boolean initTipos = LibSQL.executeQuery("DELETE FROM sueldos.conceptos WHERE idconcepto > 0");
	boolean initModelos = LibSQL.executeQuery("DELETE FROM sueldos.modelosasignacionconceptos");
	boolean initConceptosLegajos = LibSQL.executeQuery("DELETE FROM sueldos.conceptoslegajos");
	String textArchivo = "";
	    Advisor.messageBox("CARGAR HABERES, ELIJA ARCHIVO","HABERES");
	    textArchivo = abrirArchivo();
	    importar(textArchivo,1,0);
	    Advisor.messageBox("CARGAR DESCUENTOS, ELIJA ARCHIVO","DESCUENTOS");
	    textArchivo = abrirArchivo();
	    importar(textArchivo,-1,100);
    }
    
    private void importar(String _textImport, int _dh,int _order){
	String text = _textImport;
	int desde = 0;
	int orden = _order;
	String[] lineas = _textImport.split("\n");
	int i = 0;
	while(i < lineas.length) {
	    guardarLineaDB(lineas[i],_dh,orden);
	    i++;
	    orden++;
	}
	
    }
    
    private void guardarLineaDB(String _linea, int _dh, int _orden){
	Conceptos concepto = new Conceptos();
	concepto.setComentario(_linea);
	concepto.setDebehaber(_dh);
	concepto.setOrden(_orden);
	concepto.saveData();
    }
    
    private String abrirArchivo() {
        String resultado = "";
        String Text = "";
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            int opcion = fc.showOpenDialog(this);
            if (opcion != fc.CANCEL_OPTION) {
                File Abrir =
                    fc.getSelectedFile(); //Devuelve el File que vamos a leerName
                if (Abrir != null) {
                    FileReader Fichero = new FileReader(Abrir);
                    BufferedReader leer = new BufferedReader(Fichero);
                    while ((Text = leer.readLine()) != null) {
                        resultado +=
                                (Text + "\n"); //append Concatena la linea leida
                    }
                    leer.close();
                    Fichero.close();
                }
            }
        } catch (IOException ioe) {
        }
        return resultado;
    }

}

