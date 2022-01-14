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
 * PayAnnualFeesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.AlicuotaContribucion;
import org.digitall.apps.taxes.classes.BoletaAutomotor;
import org.digitall.apps.taxes.classes.BoletaContribucion;
import org.digitall.apps.taxes.classes.BoletaInmob;
import org.digitall.apps.taxes.classes.BoletaTgs;
import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PayAnnualFeesMgmt extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel totalPanel = new BasicPanel();
    private BasicPanel southPanel = new BasicPanel();
    private BasicPanel wizardPanel = new BasicPanel();

    private int[] sizeColumnList = { 37 , 48 , 92 , 91 , 52 , 71 , 67 , 70 , 70 , 94 , 73 , 70 , 75};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Anticipos Adeudados", dataRow){
	public void finishLoading() {
	    setComboAnticipos();
	}
    }
    ;
    private Vector headerList = new Vector();

    private TFInput tfAmount = new TFInput(DataTypes.MONEY,"Amount",false);
    private TFInput tfAccruedInterest = new TFInput(DataTypes.MONEY,"Interés",false);
    private TFInput tfAccruedDiscount = new TFInput(DataTypes.MONEY,"DiscountAmount",false);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY,"AmountToPay",false);
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"TaxPayer",false);
    private TFInput tfCatastro = new TFInput(DataTypes.STRING,"Catastro/Dominio",false);
    private TFInput tfTipoImpuesto = new TFInput(DataTypes.STRING,"TaxesType",false);
    private TFInput tfDescuentoAplicado = new TFInput(DataTypes.STRING,"Descuento",false);
    private TFInput tfDescuentoAnual = new TFInput(DataTypes.MONEY,"Dto. Anual",false);

    private UnAssignButton btnBack = new UnAssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private AssignButton btnNext = new AssignButton();
    private SaveDataButton btnSaveData = new SaveDataButton();

    private JLabel lblMoratoriaAutomotor = new JLabel();
    
    private BoletaTgs boletaTgs;
    private BoletaInmob boletaInmob;
    private BoletaAutomotor boletaAutomotor;
    private Coordinador coordinador = new Coordinador();
    private Cadastral catastro;
    private BigDecimal dtoAnual = new BigDecimal("0.30");
    private BasicCheckBox chkVerTodosAnticipos = new BasicCheckBox("Mostrar adelantos");

    private CBInput cbCantAnticipos = new CBInput(0, "(#) Cantidad", false);

    public PayAnnualFeesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Pago Anual");
	listPanel.setSize(new Dimension(860, 350));
	listPanel.setBounds(new Rectangle(0, 0, 895, 280));
	listPanel.setSortable(false);
	centralPanel.setSize(new Dimension(860, 400));
	btnBack.setBounds(new Rectangle(40, 15, 25, 20));
	btnBack.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnBack_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	centralPanel.setLayout(borderLayout2);
	findPanel.setLayout(null);
        totalPanel.setLayout(null);
	southPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
        totalPanel.setPreferredSize(new Dimension(860, 45));
	southPanel.setPreferredSize(new Dimension(860, 50));
        btnSaveData.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSaveData_actionPerformed(e);
				}

			    }
	);
        tfAmount.setBounds(new Rectangle(280, 5, 105, 35));
        tfAccruedInterest.setBounds(new Rectangle(395, 5, 105, 35));
        tfTotalAmount.setBounds(new Rectangle(760, 5, 130, 35));
        tfAccruedDiscount.setBounds(new Rectangle(510, 5, 105, 35));
	findPanel.add(tfDescuentoAplicado, null);
	findPanel.add(tfTipoImpuesto, null);
	findPanel.add(tfCatastro, null);
	findPanel.add(tfContribuyente, null);
	findPanel.add(lblMoratoriaAutomotor, null);
	tfContribuyente.setEditable(false);
	tfCatastro.setEditable(false);
	tfTipoImpuesto.setEditable(false);
	tfDescuentoAplicado.setEditable(false);
	totalPanel.add(tfDescuentoAnual, null);
	totalPanel.add(cbCantAnticipos, null);
	totalPanel.add(chkVerTodosAnticipos, null);
	totalPanel.add(tfAccruedDiscount, null);
	totalPanel.add(tfAmount, null);
	totalPanel.add(tfAccruedInterest, null);
	totalPanel.add(tfTotalAmount, null);
	centralPanel.add(listPanel, BorderLayout.CENTER);
	centralPanel.add(totalPanel, BorderLayout.SOUTH);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
	content.add(southPanel, BorderLayout.SOUTH);
	wizardPanel.add(btnNext, null);
	wizardPanel.add(btnFirst, null);
	wizardPanel.add(btnBack, null);
	southPanel.add(wizardPanel, null);
	this.add(content, null);
	addButton(btnSaveData);
        setHeaderList();
	btnBack.setToolTipText("Volver a la pestaña anterior");
	btnNext.setToolTipText("");
	btnBack.setSize(new Dimension(25, 20));
	btnSaveData.setToolTipText("Registrar e Imprimir Boleta");
	lblMoratoriaAutomotor.setText("M");
        lblMoratoriaAutomotor.setBounds(new Rectangle(580, 30, 20, 20));
        lblMoratoriaAutomotor.setForeground(Color.red);
        lblMoratoriaAutomotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoratoriaAutomotor.setVisible(false);
	chkVerTodosAnticipos.setBounds(new Rectangle(5, 10, 135, 30));
	btnFirst.setBounds(new Rectangle(10, 15, 25, 20));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
	btnNext.setBounds(new Rectangle(80, 15, 25, 20));
	wizardPanel.setBounds(new Rectangle(790, 0, 110, 50));
	wizardPanel.setLayout(null);
	wizardPanel.setSize(new Dimension(110, 50));
	wizardPanel.setBackground(new Color(185, 96, 0));
	tfDescuentoAplicado.setBounds(new Rectangle(620, 20, 255, 35));
	findPanel.setBorder(BorderPanel.getBorderPanel("Contribuyente - Impuesto"));
	totalPanel.setBorder(BorderPanel.getBorderPanel(""));
	tfAccruedDiscount.setEnabled(false);
        tfAccruedInterest.setEnabled(false);
        tfAmount.setEnabled(false);
        tfTotalAmount.setEnabled(false);
	tfContribuyente.setBounds(new Rectangle(35, 20, 275, 35));
	tfCatastro.setBounds(new Rectangle(335, 20, 110, 35));
	tfTipoImpuesto.setBounds(new Rectangle(470, 20, 130, 35));
	btnNext.setEnabled(false);
	cbCantAnticipos.setBounds(new Rectangle(170, 5, 80, 35));
	cbCantAnticipos.autoSize();
	cbCantAnticipos.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange() == ItemEvent.SELECTED){
				calcTotalAmount();
			}
		}
	}
	);
	tfDescuentoAnual.setBounds(new Rectangle(625, 5, 105, 35));
	listPanel.getTable().setEnabled(false);
	cbCantAnticipos.setEnabled(false);
	tfDescuentoAnual.setEnabled(false);
    }

    public void setComboAnticipos() {
	String params = coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + chkVerTodosAnticipos.isSelected();
	int cantAnticipos = LibSQL.getInt("taxes.getCantAnticiposAPagar", "" + params);
	cbCantAnticipos.removeAllItems();
	if (cantAnticipos > 0) {
	    btnSaveData.setEnabled(true);
	    for (int i = 1; i <= cantAnticipos; i++) {
		cbCantAnticipos.getCombo().addItem(i);
	    }
	} else {
	    cbCantAnticipos.getCombo().addItem(0);
	    btnSaveData.setEnabled(false);
	}
	cbCantAnticipos.getCombo().setSelectedItem(cantAnticipos);
	calcTotalAmount();
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
        listPanel.setParams("taxes.getcuotas", "-1,-1, false", headerList);
	chkVerTodosAnticipos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
			refresh();
		        ((TaxesMain)getTabContainer()).getTaxesMgmt().setChkVerAdelantos(chkVerTodosAnticipos.isSelected());
		    }
	}
	);
	chkVerTodosAnticipos.setSelected(false);
    }
    
    /* Código optimizado, además puedo deseleccionar un anticipo al hacerle click a él y no a su predecesor */
    private void checkRows() {
	int fila = listPanel.getTable().getSelectedRow();
	for (int i = 0 ;i < listPanel.getTable().getRowCount() ; i++)  {
	    listPanel.getTable().setValueAt(false,i,0);
	}        
    }
    
    /**
     * 2009-09-25 (Matias)
     * Método que calcula los interese y descuento que conforman el total a pagar.
     */
    private void calcTotalAmount(){// calcTotalAmount
	 if (cbCantAnticipos.getSelectedIndex() >= 0) {
	     int cantidadAnticiposAPagar = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	     BigDecimal totalIntereses = new BigDecimal("0");
	     BigDecimal totalValor = new BigDecimal("0");
	     BigDecimal subTotalDescuento = new BigDecimal("0");
	     BigDecimal totalDescuento = new BigDecimal("0");
	     BigDecimal subTotal = new BigDecimal("0");
	     BigDecimal subTotalDeuda = new BigDecimal("0");
	     BigDecimal descuentoAnual = new BigDecimal("0");
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
	     subTotalDescuento = Format.toDouble(totalValor.add(totalIntereses).doubleValue() * coordinador.getPorcentajeDto());
	     tfAccruedDiscount.setValue(subTotalDescuento);
	     
	     subTotal = totalValor.add(totalIntereses).subtract(subTotalDescuento);
	     
	     descuentoAnual = subTotal.multiply(dtoAnual);
	     
	     tfDescuentoAnual.setValue(descuentoAnual);
	     totalDescuento = subTotalDescuento.subtract(descuentoAnual);
	     
	     tfTotalAmount.setValue(subTotal.subtract(descuentoAnual));
	 }
    }


    public void refresh() {
	String params = ""+ coordinador.getIdTipoImpuesto() +","+ coordinador.getIdBien() +","+ chkVerTodosAnticipos.isSelected();
	listPanel.refresh(params);
        clearFields();
	setComboAnticipos();
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
	
    }

    private void generarLibreDeuda(int _idAlicuotaContribucion, int _idTipoImpuesto){
	AlicuotaContribucion alicuotaContribucion = new AlicuotaContribucion();
	alicuotaContribucion.setIdalicuotacontribucion(_idAlicuotaContribucion);
	BoletaContribucion boletaContribucion = new BoletaContribucion();
	boletaContribucion.setConcepto(alicuotaContribucion.getNombre());
	boletaContribucion.setIdBien(coordinador.getIdBien());
	if(_idTipoImpuesto == 1 || _idTipoImpuesto == 2){
	    boletaContribucion.setContribuyente(coordinador.getCatastro().getDomape());
	    boletaContribucion.setNroBien(""+ coordinador.getCatastro().getCatastro());
	    boletaContribucion.setTipoBien("CATASTRO");
	}else{
	    boletaContribucion.setContribuyente(coordinador.getAutomotor().getTitular());
	    boletaContribucion.setNroBien(coordinador.getAutomotor().getDominio());
	    boletaContribucion.setTipoBien(coordinador.getAutomotor().getTipo());
	}
	boletaContribucion.setIdTipoImpuesto(_idTipoImpuesto);
	boletaContribucion.setIdAlicuotaContribucion(alicuotaContribucion.getIdalicuotacontribucion());
	if (boletaContribucion.saveData() > 0)  {	
	   boletaContribucion.retrieveData();
	   Advisor.messageBox("Se generó correctamete el libre deuda con la boleta Nº "+ boletaContribucion.getNumeroFormateado() ,"Aviso");
	} else  {
	    System.out.println("error");
	} 
	
    }

    /**2009-09-17 (Matias)*/
    private void btnSaveData_actionPerformed(ActionEvent e) {
	int cantCuotas = Integer.parseInt(cbCantAnticipos.getSelectedItem().toString());
	System.out.println("REGISTRAR BOLETA PAGO ANUAL"+ cantCuotas);
	if ( cantCuotas > 0)  {
		if(Advisor.question("Aviso","¿Está seguro de generar la boleta por Pago Anual por "+ cantCuotas +" anticipos que hacen un total de $"+ tfTotalAmount.getValue()+"?")){
		    if (coordinador.getIdTipoImpuesto() == 1 && coordinador.getIdBien() > 0) {
			loadBoletaTGS(cantCuotas);
			if (boletaTgs.saveData() > 0)  {
			    refresh();
			    boletaTgs.retrieveData();
			    Advisor.messageBox("Se grabó correctamente la boleta con el número: "+ boletaTgs.getAnio() + "-"+ boletaTgs.getNumero(),"Mensaje");
			} else  {
			    Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!","Error");
			}
		    } else if (coordinador.getIdTipoImpuesto() == 2 && coordinador.getIdBien() > 0) {
			loadBoletaInmob(cantCuotas);
			if (boletaInmob.saveData() > 0)  {
			    refresh();
			    boletaInmob.retrieveData(); 
			    Advisor.messageBox("Se grabó correctamente la boleta con el número: "+ boletaInmob.getAnio() + "-" + boletaInmob.getNumero(),"Mensaje");
			} else  {
			    Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!","Error");
			}
		    } else if (coordinador.getIdTipoImpuesto() == 3 && coordinador.getIdBien() > 0) {
			loadBoletaAutomotor(cantCuotas);
			if (boletaAutomotor.saveData() > 0)  {
			    refresh();
			    boletaAutomotor.retrieveData(); 
			    Advisor.messageBox("Se grabó correctamente la boleta con el número: "+ boletaAutomotor.getAnio() + "-" + boletaAutomotor.getNumero(),"Mensaje");
			} else  {
			    Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!","Error");
			}
		    }
	    }
	} else  {
	    Advisor.messageBox("Debe seleccionar por lo menos un anticipo!","Mensaje");
	}
	
    }
    

    public void clearFields() {
        tfAccruedDiscount.setValue(0.0);
        tfAccruedInterest.setValue(0.0);
        tfAmount.setValue(0.0);
        tfTotalAmount.setValue(0.0);
	if (listPanel.getTable().getRowCount() > 0)  {
	    btnSaveData.setEnabled(true);
	} else  {
	    btnSaveData.setEnabled(false);
	}
	
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
	clearFields();
	((TaxesMain)getTabContainer()).getPayFeesMgmt().refresh();
	((TaxesMain)getTabContainer()).setEnabledPanels(4);
    }
    
    public void setCoordinador(Coordinador _coordinador){
	coordinador = _coordinador;
	setForm();
    }

    private void setForm() {
	listPanel.setTitle("Anticipos");
	if (coordinador.getIdTipoImpuesto() == 1) {
	    tfContribuyente.setValue(coordinador.getContribuyente());
	    tfCatastro.setValue(coordinador.getCatastro().getCatastro());
	    tfTipoImpuesto.setValue("TGS");
	    tfDescuentoAplicado.setValue(coordinador.getDescuento());
	    refresh();
	} else if (coordinador.getIdTipoImpuesto() == 2) {
	    tfContribuyente.setValue(coordinador.getContribuyente());
	    tfCatastro.setValue(coordinador.getCatastro().getCatastro());
	    tfTipoImpuesto.setValue("INMOBILIARIO");
	    tfDescuentoAplicado.setValue(coordinador.getDescuento());
	    refresh();
	} else if (coordinador.getIdTipoImpuesto() == 3) {
	    tfContribuyente.setValue(coordinador.getContribuyente());
	    tfCatastro.setValue(coordinador.getAutomotor().getDominio());
	    tfTipoImpuesto.setValue("AUTOMOTOR");
	    tfDescuentoAplicado.setValue(coordinador.getDescuento());
	    refresh();
	}
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	clearFields();
	((TaxesMain)getTabContainer()).setEnabledPanels(0);
    }

    /**2009-09-17 (Matias)*/
    private void loadBoletaTGS(int _cantCuotas) {
	boletaTgs = new BoletaTgs();
	catastro = new Cadastral();
	catastro.setIdCatastro(coordinador.getIdBien());
	catastro.retrieveData();
	catastro.retrieveApoderadoData();
	boletaTgs.setIdCatastro(catastro.getIdCatastro());
	String concepto = "";
	Vector anticipos = getAnticipos();
	Vector anioanticipos = getAnios();
	
	boletaTgs.setContribuyente(catastro.getDomape());
	boletaTgs.setSeccion(catastro.getTecsec());
	boletaTgs.setManzana(catastro.getTecman());
	boletaTgs.setParcela(catastro.getTecpar());
	boletaTgs.setZona("");
	boletaTgs.setCatastro(catastro.getCatastro());
	boletaTgs.setNrocuenta(catastro.getNroCuenta());
	if (_cantCuotas > 1) {
	 concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(_cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(_cantCuotas-1).toString();
	} else {
	 concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
	}
	boletaTgs.setConcepto(concepto);
	boletaTgs.setImporte( new BigDecimal("" + Double.parseDouble(tfAmount.getValue().toString())).doubleValue());
	boletaTgs.setRecargo(new BigDecimal( "" + Double.parseDouble(tfAccruedInterest.getValue().toString())).doubleValue());
	boletaTgs.setDeducciones(new BigDecimal( "" + Double.parseDouble(tfAccruedDiscount.getValue().toString())).doubleValue());
	/** 
	 * (2009-12-16) Cesar
	 * las siguientes 3 lineas fueron agregadas para cargar el descuento,
	 * el porcentaje de descuento y el estado pagoAnual = true en la boleta
	 * */
	boletaTgs.setDtoPagoAnual(new BigDecimal( "" + Double.parseDouble(tfDescuentoAnual.getValue().toString())).doubleValue());
	boletaTgs.setPorcentajeDtoPagoAnual(new BigDecimal( "" + Double.parseDouble(dtoAnual.toString())).doubleValue());
	boletaTgs.setPagoAnual(true);

	boletaTgs.setTotal(new BigDecimal( "" + Double.parseDouble(tfTotalAmount.getValue().toString())).doubleValue());//cambiar
	boletaTgs.setNroimpresiones(1);
	boletaTgs.setLocalidad(catastro.getLocalidad());
	String nro="";
	if (catastro.getDnumro().trim().equals("0") || catastro.getDnumro().trim().equals(""))  {
	 nro = "S/N";
	} else  {
	 nro = "Nº "+ catastro.getDnumro();
	}
	
	boletaTgs.setIdDescuento(coordinador.getTipoDescuento());
	if (coordinador.getTipoDescuento() > 0) {
	    boletaTgs.setNombreDescuento(coordinador.getDescuento());
	} else {
	    boletaTgs.setNombreDescuento("");
	}
	boletaTgs.setDomicilio(catastro.getDcalle() +" "+ nro);
	boletaTgs.setTerreno(catastro.getTerreno());
	boletaTgs.setValedificacion(new BigDecimal( "" + Double.parseDouble(catastro.getTervmej().toString())).doubleValue());
	boletaTgs.setValfiscal(new BigDecimal( "" + Double.parseDouble(catastro.getValfis().toString())).doubleValue());
	boletaTgs.setUsuario(Environment.sessionUser);
	boletaTgs.setNrocuenta(catastro.getNroCuenta());
	String apoderado = "";
	if (!catastro.getApoderadoLastName().trim().equals(""))  {
	 apoderado = catastro.getApoderadoLastName() +" "+ catastro.getApoderadoName();
	}
	boletaTgs.setApoderado(apoderado);
	
	Vector vec = getSelectedsVector();
	Vector desde = (Vector)vec.elementAt(0);
	Vector hasta = (Vector)vec.elementAt(vec.size() -1);
	boletaTgs.setAnioDesde(Integer.parseInt(desde.elementAt(2).toString()));
	boletaTgs.setAnticipoDesde(Integer.parseInt(desde.elementAt(1).toString()));
	boletaTgs.setAnioHasta(Integer.parseInt(hasta.elementAt(2).toString()));
	boletaTgs.setAnticipoHasta(Integer.parseInt(hasta.elementAt(1).toString()));
	
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento", coordinador.getIdTipoImpuesto() +","+ coordinador.getCatastro().getIdCatastro() +","+ hasta.elementAt(1).toString() +","+ hasta.elementAt(2).toString());
	boletaTgs.setFechaProximoVto(proximovto);
    }
    
    /**2009-09-17 (Matias)*/
    private void loadBoletaInmob(int _cantCuotas) {
	boletaInmob = new BoletaInmob();
	catastro = new Cadastral();
	catastro.setIdCatastro(coordinador.getIdBien());
	catastro.retrieveData();
	catastro.retrieveApoderadoData();
	String concepto = "";
	
	Vector anticipos = getAnticipos();
	Vector anioanticipos = getAnios();
	
	boletaInmob.setContribuyente(catastro.getDomape());
	boletaInmob.setSeccion(catastro.getTecsec());
	boletaInmob.setManzana(catastro.getTecman());
	boletaInmob.setParcela(catastro.getTecpar());
	boletaInmob.setZona("");
	boletaInmob.setCatastro(catastro.getCatastro());
	boletaInmob.setNrocuenta(catastro.getNroCuenta());
	if (_cantCuotas > 1) {
	    concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(_cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(_cantCuotas-1).toString();
	} else {
	    concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
	}
	boletaInmob.setConcepto(concepto);
	boletaInmob.setImporte(new BigDecimal( "" + Double.parseDouble(tfAmount.getValue().toString())).doubleValue());
	boletaInmob.setRecargo(new BigDecimal( "" + Double.parseDouble(tfAccruedInterest.getValue().toString())).doubleValue());
	boletaInmob.setDeducciones(new BigDecimal( "" + Double.parseDouble(tfAccruedDiscount.getValue().toString())).doubleValue());
	/** 
	 * (2009-12-16) Cesar
	 * las siguientes 3 lineas fueron agregadas para cargar el descuento,
	 * el porcentaje de descuento  y el estado pagoAnual = true en la boleta
	 * */
	boletaInmob.setDtoPagoAnual(new BigDecimal( "" + Double.parseDouble(tfDescuentoAnual.getValue().toString())).doubleValue());
	boletaInmob.setPorcentajeDtoPagoAnual(new BigDecimal( "" + Double.parseDouble(dtoAnual.toString())).doubleValue());
	boletaInmob.setPagoAnual(true);

	boletaInmob.setTotal(new BigDecimal( "" + Double.parseDouble(tfTotalAmount.getValue().toString())).doubleValue());//cambiar
	boletaInmob.setNroimpresiones(1);
	boletaInmob.setLocalidad(catastro.getLocalidad());
	String nro="";
	if (catastro.getDnumro().trim().equals("0") ||catastro.getDnumro().trim().equals(""))  {
	    nro = "S/N";
	} else  {
	    nro = "Nº "+ catastro.getDnumro();
	}
	boletaInmob.setIdDescuento(coordinador.getTipoDescuento());
	if (coordinador.getTipoDescuento() > 0) {
	    boletaInmob.setNombreDescuento(coordinador.getDescuento());
	} else {
	    boletaInmob.setNombreDescuento("");
	}
	boletaInmob.setDomicilio(catastro.getDcalle() +" "+ nro);
	boletaInmob.setTerreno(catastro.getTerreno());
	boletaInmob.setValedificacion(new BigDecimal( "" + Double.parseDouble(catastro.getTervmej().toString())).doubleValue());
	boletaInmob.setValfiscal(new BigDecimal( "" + Double.parseDouble(catastro.getValfis().toString())).doubleValue());
	boletaInmob.setUsuario(Environment.sessionUser);
	String apoderado = "";
	if (!catastro.getApoderadoLastName().trim().equals(""))  {
	    apoderado = catastro.getApoderadoLastName().toUpperCase() +" "+ catastro.getApoderadoName().toUpperCase();
	}
	boletaInmob.setApoderado(apoderado);
	boletaInmob.setIdCatastro(catastro.getIdCatastro());
	Vector vec = getSelectedsVector();
	Vector desde = (Vector)vec.elementAt(0);
	Vector hasta = (Vector)vec.elementAt(vec.size() -1);
	boletaInmob.setAnioDesde(Integer.parseInt(desde.elementAt(2).toString()));
	boletaInmob.setAnticipoDesde(Integer.parseInt(desde.elementAt(1).toString()));
	boletaInmob.setAnioHasta(Integer.parseInt(hasta.elementAt(2).toString()));
	boletaInmob.setAnticipoHasta(Integer.parseInt(hasta.elementAt(1).toString()));
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento", coordinador.getIdTipoImpuesto() +","+ coordinador.getCatastro().getIdCatastro() +","+ hasta.elementAt(1).toString() +","+ hasta.elementAt(2).toString());
	boletaInmob.setFechaProximoVto(proximovto);
    }
    
    /**2009-09-17 (Matias)*/
    private void loadBoletaAutomotor(int _cantCuotas) {
	boletaAutomotor = new BoletaAutomotor();
	String concepto = "";

	Vector anticipos = getAnticipos();
	Vector anioanticipos = getAnios();
	
	boletaAutomotor.setIdAutomotor(coordinador.getAutomotor().getIdAutomotor());
	boletaAutomotor.setTitular(coordinador.getAutomotor().getTitular());
	boletaAutomotor.setTipo(coordinador.getAutomotor().getTipo());
	boletaAutomotor.setMarca(coordinador.getAutomotor().getMarca());
	boletaAutomotor.setMotor(coordinador.getAutomotor().getNromotor());
	boletaAutomotor.setCategoria(coordinador.getAutomotor().getIdtipocategoria());
	boletaAutomotor.setModelo(""+ coordinador.getAutomotor().getModelo());
	boletaAutomotor.setCuota(coordinador.getAutomotor().getCuota());
	boletaAutomotor.setIdDominio(coordinador.getAutomotor().getIddominio());
	boletaAutomotor.setDominio(coordinador.getAutomotor().getDominio());
	boletaAutomotor.setDomicilio(coordinador.getAutomotor().getDomicilio());
	boletaAutomotor.setNrocuenta(0);
	if (_cantCuotas > 1) {
	    concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString() +" al "+ anticipos.elementAt(_cantCuotas-1).toString() +"/"+ anioanticipos.elementAt(_cantCuotas-1).toString();
	} else {
	    concepto = anticipos.elementAt(0).toString() +"/"+ anioanticipos.elementAt(0).toString();
	}
	boletaAutomotor.setConcepto(concepto);
	boletaAutomotor.setInformacion("");
	boletaAutomotor.setValorAnual((new BigDecimal("" + coordinador.getAutomotor().getValoranual())).doubleValue());
	boletaAutomotor.setImporte((new BigDecimal("" + Double.parseDouble(tfAmount.getValue().toString()))).doubleValue());
	boletaAutomotor.setRecargo(new BigDecimal("" + Double.parseDouble(tfAccruedInterest.getValue().toString())).doubleValue());
	boletaAutomotor.setDeducciones(new BigDecimal("" + Double.parseDouble(tfAccruedDiscount.getValue().toString())).doubleValue());
	/** 
	 * (2009-12-16) Cesar
	 * las siguientes 3 lineas fueron agregadas para cargar el descuento,
	 * el porcentaje de descuento  y el estado pagoAnual = true en la boleta
	 * */
	boletaAutomotor.setDtoPagoAnual(new BigDecimal("" + Double.parseDouble(tfDescuentoAnual.getValue().toString())).doubleValue());
	boletaAutomotor.setPorcentajeDtoPagoAnual(new BigDecimal("" + Double.parseDouble(dtoAnual.toString())).doubleValue());
	boletaAutomotor.setPagoAnual(true);

	boletaAutomotor.setTotal(Double.parseDouble(tfTotalAmount.getValue().toString()));//cambiar
	boletaAutomotor.setNroimpresiones(1);
	boletaAutomotor.setLocalidad("");
	boletaAutomotor.setIdDescuento(coordinador.getTipoDescuento());
	boletaAutomotor.setNombreDescuento(coordinador.getDescuento());
	if (coordinador.getTipoDescuento() > 0) {
	    boletaAutomotor.setNombreDescuento(coordinador.getDescuento());
	} else {
	    boletaAutomotor.setNombreDescuento("");
	}
	boletaAutomotor.setUsuario(Environment.sessionUser);
	Vector vec = getSelectedsVector();
	Vector desde = (Vector)vec.elementAt(0);
	Vector hasta = (Vector)vec.elementAt(vec.size() -1);
	boletaAutomotor.setAnioDesde(Integer.parseInt(desde.elementAt(2).toString()));
	boletaAutomotor.setAnticipoDesde(Integer.parseInt(desde.elementAt(1).toString()));
	boletaAutomotor.setAnioHasta(Integer.parseInt(hasta.elementAt(2).toString()));
	boletaAutomotor.setAnticipoHasta(Integer.parseInt(hasta.elementAt(1).toString()));
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento",  coordinador.getIdTipoImpuesto() +","+ coordinador.getAutomotor().getIdAutomotor() +","+ hasta.elementAt(1).toString() +","+ hasta.elementAt(2).toString());
	boletaAutomotor.setFechaProximoVto(proximovto);
    }
    
    private void controlBotones(){
	btnSaveData.setEnabled(false);
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

}

