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
 * TaxesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.apps.taxes.classes.Moratoria;
import org.digitall.apps.taxes.classes.TipoPlanDePago;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.sql.LibSQL;


public class TaxesMgmt extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    
    private BasicPanel content = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel discountPanel = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel totalPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel southPanel = new BasicPanel();
    private BasicPanel wizardPanel = new BasicPanel();
    private Color alertColor = Color.red;
    private Timer alertTimer;
    private BasicPanel panelAlerta;

    private int[] sizeColumnList = { 23 , 38 , 85 , 88 , 52 , 58 , 67 , 57 , 70 , 84 , 73 , 74 , 74};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Anticipos Adeudados", dataRow){
	public void finishLoading() {
	    setComboAnticipos();
	}
    };
    /*private GridPanel02 listPanel = new GridPanel02(50000, sizeColumnList, "Anticipos Adeudados", dataRow){
	public void finishLoading() {
	    setComboAnticipos();
	}
    };*/
    private Vector headerList = new Vector();
    
    //private HashMap<String,String> header = new HashMap();//utilizamos un hashmap para prueba, reemplazaria al vector headerList. (Matias)
    
    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"Amount",false);
    private TFInput tfAccruedInterest = new TFInput(DataTypes.MONEY,"Interés",false);
    private TFInput tfAccruedDiscount = new TFInput(DataTypes.MONEY,"DiscountAmount",false);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY,"AmountToPay",false);
    
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    private PrintButton btnPrint = new PrintButton();

    private BasicRadioButton rbtnTgs = new BasicRadioButton();
    private BasicRadioButton rbtnInmob = new BasicRadioButton();
    private BasicRadioButton rbtnAutomotor = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    
    private JLabel lblMoratoriaAutomotor = new JLabel();
    
    private Coordinador coordinador = new Coordinador();
    private TipoPlanDePago tipoPlanDePago = new TipoPlanDePago();
    private Cadastral catastro;
    private Car automotor;

    private CBInput cbBonificaciones = new CBInput(0,"Bonus",false);
    private BasicCheckBox chkVerTodosAnticipos = new BasicCheckBox("Mostrar adelantos");
    private CBInput cbCantAnticipos = new CBInput(0, "(#) Cantidad", false);
    private BasicTextArea taDescripcionProblema = new BasicTextArea();

    public TaxesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        panelAlerta = new BasicPanel() {
    
            @Override
            public void paint(Graphics _graphics) {
                _graphics.setColor(alertColor);
                _graphics.fillOval(15, 05, 40, 40);
                _graphics.setColor(Color.black);
                _graphics.drawOval(15, 05, 40, 40);
                super.paintComponents(_graphics);
            }
        };
        alertTimer = new Timer(200, new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                alertColor = (alertColor==Color.red?Color.yellow:Color.red);
                panelAlerta.repaint();
                panelAlerta.getParent().repaint();
            }
            
        });
	setComboAnticipos();
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Anticipos");
	listPanel.setSize(new Dimension(860, 350));
	centralPanel.setSize(new Dimension(860, 400));
	btnNext.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNext_actionPerformed(e);
				 }

			     }
	);
	btnBack.setBounds(new Rectangle(40, 15, 25, 20));
	btnBack.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnBack_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	centralPanel.setLayout(borderLayout2);
	northPanel.setLayout(borderLayout3);
	findPanel.setLayout(null);
	discountPanel.setLayout(null);
	totalPanel.setLayout(null);
	southPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(580, 70));
	discountPanel.setPreferredSize(new Dimension(315, 70));
	northPanel.setPreferredSize(new Dimension(860, 75));
        totalPanel.setPreferredSize(new Dimension(860, 45));
	southPanel.setPreferredSize(new Dimension(860, 50));
        btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
        tfAmount.setBounds(new Rectangle(340, 5, 130, 35));
        tfAccruedInterest.setBounds(new Rectangle(480, 5, 130, 35));
        tfTotalAmount.setBounds(new Rectangle(760, 5, 130, 35));
        tfAccruedDiscount.setBounds(new Rectangle(620, 5, 130, 35));
        findPanel.add(lblMoratoriaAutomotor, null);
	findPanel.add(rbtnAutomotor, null);
	findPanel.add(rbtnInmob, null);
	findPanel.add(rbtnTgs, null);
	wizardPanel.add(btnFirst, null);
	wizardPanel.add(btnNext, null);
	wizardPanel.add(btnBack, null);
	totalPanel.add(chkVerTodosAnticipos, null);
	totalPanel.add(tfAccruedDiscount, null);
	totalPanel.add(tfAmount, null);
	totalPanel.add(cbCantAnticipos, null);
	totalPanel.add(tfAccruedInterest, null);
	totalPanel.add(tfTotalAmount, null);
	cbCantAnticipos.setBounds(new Rectangle(170, 5, 80, 35));
	cbCantAnticipos.autoSize();
	cbCantAnticipos.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange() == ItemEvent.SELECTED){
				calcTotalAmount();
			}
		}
	});
        panelAlerta.setBounds(new Rectangle(0, 0, 785, 50));
        northPanel.add(findPanel, BorderLayout.WEST);
	discountPanel.add(cbBonificaciones, null);
	northPanel.add(discountPanel, BorderLayout.EAST);
	listPanel.setSortable(false);
	centralPanel.add(listPanel,BorderLayout.CENTER);
	centralPanel.add(totalPanel,BorderLayout.SOUTH);
	content.add(northPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
        panelAlerta.add(taDescripcionProblema, null);
        southPanel.add(panelAlerta, null);
        southPanel.add(wizardPanel, null);
        content.add(southPanel, BorderLayout.SOUTH);
	this.add(content, null);
	addButton(btnPrint);
        setHeaderList();
	//setHeaderHash();//agregamos este metodo a modo prueba, reemplazaria al metodo setHeaderList(); (Matias)
	btnNext.setToolTipText("Ir a la pestaña siguiente");
	btnBack.setToolTipText("Volver a la pestaña anterior");
	btnBack.setSize(new Dimension(25, 20));
	btnNext.setBounds(new Rectangle(80, 15, 25, 20));
        btnPrint.setToolTipText("Imprimir Estado de Deuda");
        rbtnTgs.setText("TGS");
        rbtnTgs.setBounds(new Rectangle(50, 30, 110, 20));
        rbtnInmob.setText("Inmobiliario");
        rbtnInmob.setBounds(new Rectangle(225, 30, 110, 20));
        rbtnInmob.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            rbtnInmob_actionPerformed(e);
                                        }
                                    }
        );
        rbtnAutomotor.setText("Automotor");
        rbtnAutomotor.setBounds(new Rectangle(425, 30, 110, 20));
        rbtnAutomotor.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                rbtnAutomotor_actionPerformed(e);
                                            }
                                        }
        );
        lblMoratoriaAutomotor.setText("M");
        lblMoratoriaAutomotor.setBounds(new Rectangle(580, 30, 20, 20));
        lblMoratoriaAutomotor.setForeground(Color.red);
        lblMoratoriaAutomotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoratoriaAutomotor.setVisible(false);
	findPanel.setBorder(BorderPanel.getBorderPanel("Seleccionar el impuesto a pagar"));
	discountPanel.setBorder(BorderPanel.getBorderPanel("Seleccionar Descuento"));
	totalPanel.setBorder(BorderPanel.getBorderPanel(""));
        
        grupo.add(rbtnTgs);
        grupo.add(rbtnInmob);
        grupo.add(rbtnAutomotor);
        rbtnTgs.setSelected(true);
        rbtnTgs.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          rbtnTgs_actionPerformed(e);
                                      }
                                  }
        );
        tfAccruedDiscount.setEnabled(false);
        tfAccruedInterest.setEnabled(false);
        tfAmount.setEnabled(false);
        tfTotalAmount.setEnabled(false);
	cbBonificaciones.setBounds(new Rectangle(25, 25, 265, 35));
	cbBonificaciones.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cbBonificaciones_itemStateChanged(e);
		}
	    }
	);
	wizardPanel.setBounds(new Rectangle(790, 0, 110, 50));
	wizardPanel.setPreferredSize(new Dimension(1, 1));
	wizardPanel.setBackground(new Color(185, 96, 0));
	wizardPanel.setLayout(null);
	wizardPanel.setSize(new Dimension(110, 50));
	btnPrint.setEnabled(false);
	loadCombo(0);
	cbBonificaciones.autoSize();
	cbBonificaciones.setSelectedID(0);
	chkVerTodosAnticipos.setBounds(new Rectangle(5, 10, 135, 30));
	btnFirst.setBounds(new Rectangle(10, 15, 25, 20));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
	chkVerTodosAnticipos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
			refresh();
			((TaxesMain)getTabContainer()).getPayFeesMgmt().setChkVerAdelantos(chkVerTodosAnticipos.isSelected());
		    }
	}
	);
	chkVerTodosAnticipos.setSelected(false);
	listPanel.setSortable(false);
	listPanel.getTable().setEnabled(false);
        panelAlerta.setLayout(null);
        taDescripcionProblema.setBounds(new Rectangle(70, 5, 710, 40));
        panelAlerta.setVisible(false);
        taDescripcionProblema.setVisible(false);
        taDescripcionProblema.setEditable(false);
    }

    private void loadCombo(int _idTipoImpuesto){
	cbBonificaciones.loadJCombo(LibSQL.exFunction("taxes.getAllBonificaciones",""+ _idTipoImpuesto));
    }
    
    public void setComboAnticipos() {
	String params = coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + chkVerTodosAnticipos.isSelected();
	int cantAnticipos = LibSQL.getInt("taxes.getCantAnticiposAPagar", "" + params);
	cbCantAnticipos.removeAllItems();
	if (cantAnticipos > 0) {
	    for (int i = 1; i <= cantAnticipos; i++) {
		cbCantAnticipos.getCombo().addItem(i);
	    }
	} else {
	    cbCantAnticipos.getCombo().addItem(0);
	}
	calcTotalAmount();
	cbCantAnticipos.setEnabled(true);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
        headerList.removeAllElements();
        headerList.addElement("*");
        headerList.addElement("Nº");
        headerList.addElement("Año");
        headerList.addElement("Fecha");
        headerList.addElement("Vence");
        headerList.addElement("Valor");
        headerList.addElement("% Mora");
        headerList.addElement("$ Mora");
        headerList.addElement("% Desc.");
        headerList.addElement("$ Desc.");
        headerList.addElement("Fº Act.");
        headerList.addElement("$ SubTotal");
        headerList.addElement("% Pagado");
	headerList.addElement("$ Total");
	headerList.addElement("*");
        headerList.addElement("*");
        
	listPanel.getTable().addMouseListener(new MouseListener() {

		     public void mouseClicked(MouseEvent e) {
			 //Esto estaba dentro del IF
			//checkRows();
			 //IF Vacío
			 if (/*e.getClickCount() == 1 && */e.getButton() == e.BUTTON1) {
			 }
		     }

		    public void mousePressed(MouseEvent mouseEvent) {
			//checkRows();
		    }

		    public void mouseReleased(MouseEvent mouseEvent) {
			//checkRows();
		    }

		    public void mouseEntered(MouseEvent mouseEvent) {
			//checkRows();
		    }

		    public void mouseExited(MouseEvent mouseEvent) {
			//checkRows();
		    }

		});
	listPanel.getTable().addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
			    e.consume();
			    checkRows();
			}
			
			public void keyTyped(KeyEvent e) {
			    e.consume();
			    checkRows();
			}
			
			public void keyPressed(KeyEvent e) {
			    e.consume();
			    checkRows();
			}

		});
        listPanel.setParams("taxes.getcuotas", "-1,-1,false", headerList);
	chkVerTodosAnticipos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
			refresh();
			((TaxesMain)getTabContainer()).getPayFeesMgmt().setChkVerAdelantos(chkVerTodosAnticipos.isSelected());
		    }
	}
	);
	chkVerTodosAnticipos.setSelected(false);
    }	
    
    /**
     * (Matias)
     * 2009-09-28
     */
    private void checkRows() {
	for (int i = 0 ;i < listPanel.getTable().getRowCount() ; i++)  {
	    listPanel.getTable().setValueAt(false,i,0);
	}        
    }

    /**
     * 2009-09-25 (Matias)
     * Método que calcula los interese y descuento que conforman el total a pagar.
     */
    private void calcTotalAmount(){
	if (cbCantAnticipos.getSelectedIndex() >= 0) {
	    int cantidadAnticiposAPagar = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	    BigDecimal totalIntereses = new BigDecimal("0");
	    BigDecimal totalValor = new BigDecimal("0");
	    BigDecimal totalDescuento = new BigDecimal("0");
	    BigDecimal subTotalDeuda = new BigDecimal("0");
	    BigDecimal porcentajeDescuento = new BigDecimal("0");
	    listPanel.selectAllItems(true);
	    if (listPanel.getTable().getRowCount() > 0) {
		Vector valorCuota = listPanel.getValuesInTableAt("Valor");
		Vector valorIntereses = listPanel.getValuesInTableAt("$ Mora");
		Vector porcentajePagado = listPanel.getValuesInTableAt("% Pagado");
		Vector valorTotal = listPanel.getValuesInTableAt("$ Total");
		for (int i = 0; i < cantidadAnticiposAPagar; i++) {
		    totalValor = totalValor.add(Format.toDouble(new Double(valorCuota.elementAt(i).toString()) * (1 - new Double((porcentajePagado.elementAt(i).toString())) / 100)));
		    totalIntereses = totalIntereses.add(Format.toDouble(new Double(valorIntereses.elementAt(i).toString()) * (1 - new Double((porcentajePagado.elementAt(i).toString())) / 100)));
		    subTotalDeuda = subTotalDeuda.add(new BigDecimal(valorTotal.elementAt(i).toString()));
		}
	    }

	    tfAmount.setValue(totalValor);
	    tfAccruedInterest.setValue(totalIntereses);
	    porcentajeDescuento = porcentajeDescuento.add(Format.toDouble(cbBonificaciones.getSelectedValueRef().toString()));
	    totalDescuento = Format.toDouble(totalValor.add(totalIntereses).doubleValue() * porcentajeDescuento.doubleValue());
	    tfAccruedDiscount.setValue(totalDescuento);
	    tfTotalAmount.setValue(subTotalDeuda.subtract(totalDescuento));
	}
    }

    
    private double setDecimales(String _texto){
	return(Double.parseDouble(_texto));
    }

    public void refresh() {
	cbCantAnticipos.setEnabled(false);
	String params = ""+ coordinador.getIdTipoImpuesto() +","+ coordinador.getIdBien() +","+ chkVerTodosAnticipos.isSelected();
	listPanel.refresh(params);
        clearFields();
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().clearFields();
	setPlanDePago();
    }

    /* 2009-09-24 (santiago) Mensaje para el codificador
     * TODA ESTA TAREA la debería realizar el coordinador
     * dado que ese es su rol, y no solamente almacenar datos
     * él debería activar pestañas, mostrar mensajes y hacer preguntas
     * y las pestañas enviarle los datos seleccionados por el usuario
     * y nada más...
     * */
    public void loadMoratoria() {
	coordinador.setTipoDescuento(Integer.parseInt(cbBonificaciones.getSelectedValue().toString()) );
	coordinador.setDescuento(cbBonificaciones.getSelectedItem().toString());
	coordinador.setPorcentajeDto(Double.parseDouble(cbBonificaciones.getSelectedValueRef().toString()));
	
	if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2) {
	    catastro = new Cadastral();
	    catastro.setIdCatastro(coordinador.getIdBien());
	    catastro.retrieveData();
	    catastro.retrieveApoderadoData();
	    coordinador.setCatastro(catastro);
	} else if (coordinador.getIdTipoImpuesto() == 3) {
	    automotor = new Car();
	    automotor.setIdAutomotor(coordinador.getIdBien());
	    automotor.retrieveIdDominio();
	    automotor.retrieveData();
	    coordinador.setAutomotor(automotor);
	}


 /* 2009-09-24 (santiago) Mensaje para el codificador
  * No se entiende nada del algoritmo
  * Creo que debería ser así:

    si el bien tiene plan de pago
	cargar plan de pago correspondiente
	habilitar la pestaña de pago de cuotas del plan de pago
	si el plan de pago está caido
	    no permitir el pago de cuotas
	si no
	    permitir el pago de cuotas
	fin si
    si no
	si existe un plan de pago (activo) para el impuesto
	    cargar tipo de plan de pago correspondiente
	    habilitar la pestaña de generación de planes de pago
	si no
	    habilitar la pestaña de pago de anticipos regulares
	fin si
    fin si
  * */    
/*
	if (coordinador.getMoratoria().getIdPlanDePago() > 0)  {
	    loadTipoPlanDepago();
	    ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setCoordinador(coordinador);
	    ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().refresh();
	    ((TaxesMain)getTabContainer()).setEnabledPanels(3);
	} else  {


 /* 2009-09-24 (santiago) Mensaje para el codificador
  * No se entiende qué se está evaluando en esta pregunta
 * Además, con preguntar if (coordinador.getTipoPlanDePago().getIdTipoPlanDePago() <= 0) no es suficiente???
 * 
	    if ( (coordinador.getTipoPlanDePago().getIdTipoPlanDePago() == -1) || (coordinador.getTipoPlanDePago().getIdTipoPlanDePago() == 0))  {
		((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
		((TaxesMain)getTabContainer()).setEnabledPanels(4);
	     /* 2009-09-24 (santiago) Mensaje para el codificador
	      * No se entiende qué se evalúa en esta pregunta
	    } else  if ( (coordinador.getTipoPlanDePago().getIdTipoPlanDePago() != -1) && (coordinador.getTipoPlanDePago().getMoratoria().getIdPlanDePago() == -1) )  {
		if (listPanel.getTable().getRowCount() > 0 && coordinador.getTipoPlanDePago().isActivo() )  {
		    ((TaxesMain)getTabContainer()).plansOfPaymentMgmt().setCoordinador(coordinador);
		    ((TaxesMain)getTabContainer()).setEnabledPanels(2);    
		} else  {
		    coordinador.setMoratoria(new Moratoria());
		    ((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
		    ((TaxesMain)getTabContainer()).setEnabledPanels(4);
		}
	    }   
	}
*/	
	
	 /* 2009-09-24 (santiago) Mensaje para el codificador
	  * PARCHE por Santiago - 2009/09/24
	  * Aquí necesito saber si el bien tiene un plan de pago no caido
	  * para pasarlo a la pestaña de pago de cuotas de planes de pago
	  * */

	int _idPlanDePago = LibSQL.getInt("taxes.tieneplandepago", coordinador.getIdTipoImpuesto() + ", " + coordinador.getIdBien());

	boolean _existePosibilidadPlanPago = (coordinador.getTipoPlanDePago().getIdTipoPlanDePago() != -1) 
			    && (coordinador.getTipoPlanDePago().getMoratoria().getIdPlanDePago() == -1)
			    && (listPanel.getTable().getRowCount() > 0)
			    && coordinador.getTipoPlanDePago().isActivo();

	if (_idPlanDePago > 0)  {
	    Moratoria _plandepago = new Moratoria();
	    _plandepago.setIdPlanDePago(_idPlanDePago);
	    _plandepago.retrieveDataByPrimaryKey();
	    coordinador.setMoratoria(_plandepago);
	    if (_plandepago.isPlanDePagoCaido()) {
	        if (Advisor.question("Contrato dado de baja", "¡¡ El Contrato de Moratoria/Plan de Pago ha sido dado de baja por falta de pago !!\n¿Desea ver el detalle?")) {
		    habilitarPestaniaPagoCuotasPlanDePago();
		} else {
		    if ( _existePosibilidadPlanPago )  {
		        /* 2009-09-24 (santiago) Mensaje para el codificador
		         * El metodo setEnabledPanels(int _index) debería llamarse activateTab(int _index),
			 * enableTab(int _index) o activarPestania(int _index)
		         * */
			 habilitarPestaniaConfiguracionPlanDePago();
		    } else  {
			habilitarPestaniaPagoAnticiposRegulares();
		    }
		}
	    } else {
		habilitarPestaniaPagoCuotasPlanDePago();
	    }
	} else  {
	    if (_existePosibilidadPlanPago)  {
		habilitarPestaniaConfiguracionPlanDePago();
	    } else  {
		habilitarPestaniaPagoAnticiposRegulares();
	    }
	}
    }
    
    private void loadTipoPlanDepago(){
	tipoPlanDePago = new TipoPlanDePago();
	tipoPlanDePago.setIdTipoImpuesto(coordinador.getIdTipoImpuesto());
	tipoPlanDePago.retrieveIdTipoPlanDePago();
	tipoPlanDePago.setIdBien(coordinador.getIdBien());
	tipoPlanDePago.retrieveData();
	coordinador.setTipoPlanDePago(tipoPlanDePago);
    }

    private void btnNext_actionPerformed(ActionEvent e) {
	loadMoratoria();
    }

    private void btnFin_actionPerformed(ActionEvent e) {
	refresh();
    }

/** 2009-09-11 (cesar) */
    private void btnPrint_actionPerformed(ActionEvent e) {
	System.out.println("IDS de la grilla: " +  listPanel.getSelectedsID());
	if (coordinador.getIdTipoImpuesto() != 4) {
	    int anticipoDesde = 1;
	    int anioDesde = 2000;
	    int anticipoHasta = 12;
	    int anioHasta = 2009;
	    int cantAnticipos = 1;
	    String bonificacion = "";
	    double montoDescuento = 0.0;
	    double totalInteres = 0.0;
	    double deudaParcial = 0.0;
	    double subtotal = 0.0;
	    double totalDeuda = 0.0;
	    
	    if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2) { /** Caso Impuesto TGS o Inmobiliario */
	    
		BasicReport report  = new BasicReport(PlansOfPaymentMgmt.class.getResource("xml/EstadoDeCuentaCatastral.xml"));
	        ResultSet result = LibSQL.exFunction("taxes.xmlGetEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
	        try {
	            if (result.next()) {
	                anticipoDesde = result.getInt("anticipodesde");
	                anioDesde = result.getInt("aniodesde");
	                anticipoHasta = result.getInt("anticipohasta");
	                anioHasta = result.getInt("aniohasta");
	                cantAnticipos = result.getInt("cantanticipos");
	                bonificacion = result.getString("bonificacion");
	                montoDescuento = result.getDouble("totaldescuento");
	                totalInteres = result.getDouble("totalinteres");
	                deudaParcial = result.getDouble("deudaparcial");
	                subtotal = result.getDouble("subtotal");
	                totalDeuda = result.getDouble("totaldeuda");
	            }
	            
		    /** Datos Encabezado */
	            if (coordinador.getIdTipoImpuesto() == 1) {
	                report.setProperty("impuesto", "TASA GENERAL DE SERVICIOS");
	            } else if (coordinador.getIdTipoImpuesto() == 2) {
	                report.setProperty("impuesto", "IMPUESTO INMOBILIARIO");
	            } else {
		        report.setProperty("impuesto", "");
		    }
		    
	            /** Datos del Catastro */
	            coordinador.getCatastro().retrieveApoderadoData();
	            report.setProperty("cuentacorriente", coordinador.getCatastro().getNroCuenta());
	            report.setProperty("titulobien", "Catastro:");
	            report.setProperty("titulovalorfiscal", "Val. Fisc.:");
	            report.setProperty("valorfiscal", Double.parseDouble(coordinador.getCatastro().getValfis()));
	            report.setProperty("bien", coordinador.getCatastro().getCatastro());
	            report.setProperty("apoderado", coordinador.getCatastro().getApoderadoLastName() + " " + coordinador.getCatastro().getApoderadoName());
	            report.setProperty("domicilio", coordinador.getCatastro().getDcalle() + " Nº " + coordinador.getCatastro().getDnumro());
		    
	            /** Datos del Resumen */
	            if (cantAnticipos > 0)  {
	                report.setProperty("anticipos", anticipoDesde + "/" + anioDesde + " - " + anticipoHasta + "/" + anioHasta);
	            } else  {
	                report.setProperty("anticipos", "No adeuda períodos");
	            }
	            report.setProperty("bonificacion", bonificacion);
	            report.setProperty("montoBonificacion", montoDescuento);
	            report.setProperty("totalInteres", totalInteres);
	            report.setProperty("cantAnticipos", cantAnticipos);
	            report.setProperty("deudaParcial", deudaParcial);
	            report.setProperty("totalDeuda", totalDeuda);
	            report.setProperty("totaldeudatext", "( Son Pesos " + Format.NumberToText.numberToText(totalDeuda) + " .-)");
	            report.setProperty("subtotal", subtotal);

	            /** Datos del Detalle */
		    report.setProperty("contribuyente", coordinador.getContribuyente());
	            report.addTableModel("taxes.xmlGetDetalleEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
	            report.doReport();

	        } catch (SQLException x) {
	            // TODO
	            System.out.println("error");
	        }
		
	    } else if (coordinador.getIdTipoImpuesto() == 3) {
	    
	        BasicReport report = new BasicReport(PlansOfPaymentMgmt.class.getResource("xml/EstadoDeCuentaAutomotor.xml"));   
	        ResultSet result = LibSQL.exFunction("taxes.xmlGetEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
	        try {
	            if (result.next()) {
	                anticipoDesde = result.getInt("anticipodesde");
	                anioDesde = result.getInt("aniodesde");
	                anticipoHasta = result.getInt("anticipohasta");
	                anioHasta = result.getInt("aniohasta");
	                cantAnticipos = result.getInt("cantanticipos");
	                bonificacion = result.getString("bonificacion");
	                montoDescuento = result.getDouble("totaldescuento");
	                totalInteres = result.getDouble("totalinteres");
	                deudaParcial = result.getDouble("deudaparcial");
	                subtotal = result.getDouble("subtotal");
	                totalDeuda = result.getDouble("totaldeuda");
	            }
		    
	            /** Datos Encabezado */
		    report.setProperty("impuesto", "IMPUESTO AUTOMOTOR");
		    
	            /** Datos del Dominio */
	            report.setProperty("cuentacorriente", "-");
	            report.setProperty("titulobien", "Dominio:");
	            report.setProperty("bien", coordinador.getAutomotor().getDominio());
	            report.setProperty("apoderado", "-");
	            report.setProperty("domicilio", coordinador.getAutomotor().getDomicilio());
		    
	            /** Datos del Resumen */ 
		    if (cantAnticipos > 0)  {
		        report.setProperty("anticipos", anticipoDesde + "/" + anioDesde + " - " + anticipoHasta + "/" + anioHasta);
		    } else  {
		        report.setProperty("anticipos", "No adeuda períodos");
		    }
	            report.setProperty("bonificacion", bonificacion);
	            report.setProperty("montoBonificacion", montoDescuento);
	            report.setProperty("totalInteres", totalInteres);
	            report.setProperty("cantAnticipos", cantAnticipos);
	            report.setProperty("deudaParcial", deudaParcial);
	            report.setProperty("totalDeuda", totalDeuda);
	            report.setProperty("totaldeudatext", "( Son Pesos " + Format.NumberToText.numberToText(totalDeuda) + " .-)");
	            report.setProperty("subtotal", subtotal);
	            report.setProperty("contribuyente", coordinador.getContribuyente());
		    
	            /** Datos del Detalle */
		    report.addTableModel("taxes.xmlGetDetalleEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
	            report.doReport();
	        } catch (SQLException x) {
	            // TODO
	            System.out.println("error");
	        }
	    } else {
	        Advisor.messageBox("Funcionalidad no disponible", "Mensaje");
	    }
	} else {
	    Advisor.messageBox("Funcionalidad no disponible", "Mensaje");
	}
    }

    public void setCoordinador(Coordinador _coordinador){
	coordinador = _coordinador;
	btnPrint.setEnabled(true);
	setForm();
    }

    private void setPlanDePago() {
	loadCombo(coordinador.getIdTipoImpuesto());
	coordinador.loadTipoPlanDePago();
	if (coordinador.getIdTipoImpuesto() == 3)  { //Impuesto Automotor
	    if (coordinador.getAutomotor().isDiscapacitado())  {
	        cbBonificaciones.setSelectedID(5);
	    } else  {
	        cbBonificaciones.setSelectedID(0);
	    }
	    coordinador.setMoratoria(coordinador.getTipoPlanDePago().getMoratoria());
	} else  {
	    cbBonificaciones.setSelectedID(coordinador.getTipoPlanDePago().getMoratoria().getIdBonificacion());
	    if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2) {
		coordinador.setMoratoria(coordinador.getTipoPlanDePago().getMoratoria());
	    }
	}
	if (coordinador.getMoratoria() != null)  {
	    if (coordinador.getMoratoria().getIdPlanDePago() != -1)  {
	        cbBonificaciones.setEnabled(false);
	    } else  { 
	        if (coordinador.getTipoPlanDePago().getMoratoria().getIdPlanDePago() != -1)  {
	            cbBonificaciones.setEnabled(false);
	        } else  {
	            cbBonificaciones.setEnabled(true);
	        }
	    }
	}
	
    }

    private void setForm() {
	refresh();
        mostrarProblema();
	if (coordinador.getIdTipoImpuesto() == 1)  {
	    listPanel.setTitle("Anticipos adeudados del Catastro Nº "+ coordinador.getNroBien() +"  ("+ coordinador.getContribuyente() +" - TGS)");
	    rbtnTgs.setEnabled(true);
	    rbtnInmob.setEnabled(true);
	    rbtnAutomotor.setEnabled(false);
	    rbtnTgs.setSelected(true);
	} else if (coordinador.getIdTipoImpuesto() == 2) {
            listPanel.setTitle("Anticipos adeudados del Catastro Nº "+ coordinador.getNroBien() +"  ("+ coordinador.getContribuyente() +" - Inmobiliario)");
	    rbtnTgs.setEnabled(true);
	    rbtnInmob.setEnabled(true); 
	    rbtnAutomotor.setEnabled(false);
	    rbtnInmob.setSelected(true);
	} else if (coordinador.getIdTipoImpuesto() == 3) {
            listPanel.setTitle("Anticipos adeudados del Dominio "+ coordinador.getNroBien() +"  ("+ coordinador.getContribuyente() +" - Automotor)");
	    rbtnTgs.setEnabled(false);
	    rbtnInmob.setEnabled(false);
	    rbtnAutomotor.setEnabled(true);
	    rbtnAutomotor.setSelected(true);
        }
	
    }

    private void rbtnTgs_actionPerformed(ActionEvent e) {
        /** Cargar la Grilla con los anticipos del Impuesto TGS*/
	coordinador.setImpuesto("TASA GENERAL DE SERVICIOS");
	coordinador.setIdTipoImpuesto(1);
        setForm();
    }

    private void rbtnInmob_actionPerformed(ActionEvent e) {
        /** Cargar la Grilla con los anticipos del Impuesto Inmob*/
	coordinador.setImpuesto("IMPUESTO INMOBILIARIO");
	coordinador.setIdTipoImpuesto(2);
        setForm();
    }

    private void rbtnAutomotor_actionPerformed(ActionEvent e) {
        /** Cargar la Grilla con los anticipos del Impuesto Automotor*/
	 coordinador.setImpuesto("IMPUESTO AUTOMOTOR");
	coordinador.setIdTipoImpuesto(3);
        setForm();
    }
    
    public void clearFields() {
        tfAccruedDiscount.setValue(0.0);
        tfAccruedInterest.setValue(0.0);
        tfAmount.setValue(0.0);
        tfTotalAmount.setValue(0.0);
	cbBonificaciones.setSelectedID(0);
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
	back();
    }

    private void cbBonificaciones_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    calcTotalAmount();
	}
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	back();
    }
    
    private void back(){
	rbtnTgs.setSelected(true);
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().clearFields();
	clearFields();
	chkVerTodosAnticipos.setSelected(false);
	((TaxesMain)getTabContainer()).setEnabledPanels(0);
    }
    
    public void setChkVerAdelantos(boolean _valor) {
	chkVerTodosAnticipos.setSelected(_valor);
    }
    
    /**2009-09-17 (Matias)*/
    private Vector getAnticipos(){
	Vector resultado = new Vector();
	int cantCuotas = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	if (cantCuotas > 0) {
	    listPanel.selectAllItems(true);
	    Vector selecteds = listPanel.getSelectedsVector();
	    for (int i = 0; i < cantCuotas; i++) {
		Vector aux = (Vector)selecteds.elementAt(i);
		resultado.add(aux.elementAt(1).toString());
	    }
	}
	return resultado;
    }
    
    /**2009-09-17 (Matias)*/
    private Vector getAnios(){
	Vector resultado = new Vector();
	int cantCuotas = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	if (cantCuotas > 0) {
	    listPanel.selectAllItems(true);
	    Vector selecteds = listPanel.getSelectedsVector();
	    for (int i = 0; i < cantCuotas; i++) {
		Vector aux = (Vector)selecteds.elementAt(i);
		resultado.add(aux.elementAt(2).toString());
	    }
	}
	return resultado;
    }
    
    /**2009-09-17 (Matias)*/
    private Vector getSelectedsVector(){
	Vector resultado = new Vector();
	int cantCuotas = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	if (cantCuotas > 0) {
	    listPanel.selectAllItems(true);
	    Vector selecteds = listPanel.getSelectedsVector();
	    for (int i = 0; i < cantCuotas; i++) {
		Vector aux = (Vector)selecteds.elementAt(i);
		resultado.add(aux);
	    }
	}
	return resultado;
    }

    private void habilitarPestaniaPagoCuotasPlanDePago() {
	((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setCoordinador(coordinador);
	((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().refresh();
	((TaxesMain)getTabContainer()).setEnabledPanels(3);
    }

    private void habilitarPestaniaConfiguracionPlanDePago() {
	((TaxesMain)getTabContainer()).plansOfPaymentMgmt().setCoordinador(coordinador);
	((TaxesMain)getTabContainer()).setEnabledPanels(2);    
    }

    private void habilitarPestaniaPagoAnticiposRegulares() {
	coordinador.setMoratoria(new Moratoria());
	((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
	((TaxesMain)getTabContainer()).setEnabledPanels(4);
    }
    
    //método que muestra información del problema del CATASTRO seleccionado
    private void mostrarProblema(){
        panelAlerta.setVisible(false);
        alertTimer.stop();
        taDescripcionProblema.setVisible(false);
        if ((coordinador.getIdTipoImpuesto() == 1) || (coordinador.getIdTipoImpuesto() == 2)) {
            ResultSet result = LibSQL.exFunction("taxes.getUltimoProblemaxCatastro", coordinador.getIdBien());
            try {
                if (result.next()) {
                    if (result.getBoolean("esproblema")) {
                        taDescripcionProblema.setText(result.getString("problema"));
                        panelAlerta.setVisible(true);
                        alertTimer.start();
                        alertTimer.setDelay(400);
                        taDescripcionProblema.setVisible(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }    
        }
    }
    
}
