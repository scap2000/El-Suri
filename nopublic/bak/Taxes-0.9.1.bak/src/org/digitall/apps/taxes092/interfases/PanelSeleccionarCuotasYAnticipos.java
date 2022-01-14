package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PanelSeleccionarCuotasYAnticipos extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelTipoImpuesto = new BasicPanel();
    private BasicPanel panelBonificacion = new BasicPanel();
    
    private BasicTextArea taAyuda = new BasicTextArea();

    private BasicLabel lblTitulo = new BasicLabel();
    private JLabel lblCantidadAnticipos = new JLabel();
    private JLabel lblBonificacion = new JLabel();
    private JLabel lblCantidadCuotas = new JLabel();

    private JTable grillaCuotas = new JTable();
    private JTable grillaAnticiposRegulares = new JTable();
    
    private BasicCheckBox chkVerAdelantos = new BasicCheckBox();
    
    private JComboBox cbCantidadAnticipos = new JComboBox();
    private JComboBox cbCantidadCuotas = new JComboBox();
    private JComboBox cbBonificacion = new JComboBox();
    
    private TFInput tfMonto = new TFInput(DataTypes.DOUBLE,"($) Monto",false);
    private TFInput tfInteres = new TFInput(DataTypes.DOUBLE,"($) Interés",false);
    private TFInput tfBonificacion = new TFInput(DataTypes.DOUBLE,"($) Bonificación",false);
    private TFInput tfTotal = new TFInput(DataTypes.DOUBLE,"($) Total",false);
    private TFInput tfEntrega = new TFInput(DataTypes.DOUBLE,"($) Entrega",false);
    private TFInput tfParcial = new TFInput(DataTypes.PERCENT,"($) Parcial",false);
    
    private JSeparator jSeparator2 = new JSeparator();
    
    private BasicRadioButton rbtnTgs = new BasicRadioButton();
    private BasicRadioButton rbtnInmob = new BasicRadioButton();
    private BasicRadioButton rbtnAutomotor = new BasicRadioButton();
    

    public PanelSeleccionarCuotasYAnticipos() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(845, 475));
	panelAyuda.setBounds(new Rectangle(0, 0, 215, 475));
	panelAyuda.setLayout(null);
	panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelAyuda.setSize(new Dimension(215, 475));
	lblTitulo.setText("Selec. Cuotas y Anticipos a Pagar");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
	lblTitulo.setFont(new Font("Dialog", 1, 11));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	grillaCuotas.setBounds(new Rectangle(5, 75, 270, 265));
	grillaCuotas.setBorder(BorderFactory.createTitledBorder("Cuotas P.P. Nº xxxx"));
	chkVerAdelantos.setText("Ver Adelantos");
	chkVerAdelantos.setBounds(new Rectangle(285, 345, 115, 15));
	cbCantidadAnticipos.setBounds(new Rectangle(470, 380, 70, 25));
	lblCantidadAnticipos.setText("(#) Cantidad:");
	lblCantidadAnticipos.setBounds(new Rectangle(470, 365, 70, 15));
	lblCantidadAnticipos.setForeground(Color.white);
	tfMonto.setBounds(new Rectangle(10, 425, 100, 40));
	tfMonto.setEditable(false);
	tfInteres.setBounds(new Rectangle(125, 425, 100, 40));
	tfInteres.setEditable(false);
	tfBonificacion.setBounds(new Rectangle(240, 425, 100, 40));
	tfBonificacion.setEditable(false);
	tfTotal.setBounds(new Rectangle(495, 425, 125, 40));
	tfTotal.setEditable(false);
	lblBonificacion.setBounds(new Rectangle(240, 425, 100, 15));
	cbCantidadCuotas.setBounds(new Rectangle(10, 375, 70, 25));
	lblCantidadCuotas.setText("(#) Cantidad:");
	lblCantidadCuotas.setBounds(new Rectangle(10, 360, 70, 15));
	lblCantidadCuotas.setForeground(Color.white);
	grillaAnticiposRegulares.setBorder(BorderFactory.createTitledBorder("Anticipos Regulares"));
	grillaAnticiposRegulares.setBounds(new Rectangle(285, 75, 335, 265));
	tfEntrega.setBounds(new Rectangle(365, 365, 95, 40));
	tfEntrega.setSize(new Dimension(95, 25));
	tfParcial.setBounds(new Rectangle(550, 365, 70, 40));
	tfParcial.setSize(new Dimension(45, 25));
	tfParcial.setText("50 %");
	jSeparator2.setBounds(new Rectangle(5, 415, 620, 2));
	panelTipoImpuesto.setBounds(new Rectangle(5, 5, 380, 50));
	panelTipoImpuesto.setLayout(null);
	panelTipoImpuesto.setBorder(BorderPanel.getBorderPanel("Seleccionar Impuesto"));
	panelTipoImpuesto.setSize(new Dimension(405, 65));
	panelBonificacion.setBounds(new Rectangle(415, 5, 210, 65));
	panelBonificacion.setLayout(null);
	panelBonificacion.setBorder(BorderPanel.getBorderPanel("Seleccionar Bonificación"));
	panelBonificacion.setSize(new Dimension(210, 65));
	rbtnTgs.setText("TGS");
	rbtnTgs.setBounds(new Rectangle(15, 30, 75, 20));
	rbtnTgs.setSize(new Dimension(75, 20));
	rbtnInmob.setText("Inmobiliario");
	rbtnInmob.setBounds(new Rectangle(125, 30, 130, 20));
	rbtnInmob.setSize(new Dimension(130, 20));
	rbtnAutomotor.setText("Automotor");
	rbtnAutomotor.setBounds(new Rectangle(270, 30, 110, 20));
	rbtnAutomotor.setSize(new Dimension(110, 20));
	cbBonificacion.setBounds(new Rectangle(10, 35, 190, 25));
	lblBonificacion.setText("Bonificación");
	lblBonificacion.setBounds(new Rectangle(10, 20, 190, 15));
	lblBonificacion.setForeground(Color.white);
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelBonificacion.add(lblBonificacion, null);
	panelBonificacion.add(cbBonificacion, null);
	panelCentral.add(panelBonificacion, null);
	panelTipoImpuesto.add(rbtnAutomotor, null);
	panelTipoImpuesto.add(rbtnInmob, null);
	panelTipoImpuesto.add(rbtnTgs, null);
	panelCentral.add(panelTipoImpuesto, null);
	panelCentral.add(jSeparator2, null);
	panelCentral.add(tfParcial, null);
	panelCentral.add(tfEntrega, null);
	panelCentral.add(grillaAnticiposRegulares, null);
	panelCentral.add(lblCantidadCuotas, null);
	panelCentral.add(cbCantidadCuotas, null);
	panelCentral.add(tfTotal, null);
	panelCentral.add(tfBonificacion, null);
	panelCentral.add(tfInteres, null);
	panelCentral.add(tfMonto, null);
	panelCentral.add(lblCantidadAnticipos, null);
	panelCentral.add(cbCantidadAnticipos, null);
	panelCentral.add(chkVerAdelantos, null);
	panelCentral.add(grillaCuotas, null);
	this.add(panelCentral, null);
	this.add(panelAyuda, null);
	loadComboCantidad();
	loadComboBonificacion();
	taAyuda.setEditable(false);
	taAyuda.setText("* Seleccione el Tipo de Impuesto que desea pagar.\n \n * Seleccione la cantidad de Cuotas del Plan de pago y la cantidad de Anticipos Regulares que el Contribuyente desea pagar.\n \n * Presione el botón siguiente para ir a Registrar Boletas a Pagar.");
    }

    private void loadComboBonificacion() {
	cbBonificacion.removeAllItems();
	cbBonificacion.addItem("Jubilados y Pensionados");
	cbBonificacion.addItem("Empleado Municipal");
    }
    

    private void loadComboCantidad() {
	cbCantidadAnticipos.removeAllItems();
	cbCantidadAnticipos.addItem("0");
	cbCantidadAnticipos.addItem("1");
	cbCantidadAnticipos.addItem("2");
	cbCantidadAnticipos.addItem("3");
	cbCantidadAnticipos.addItem("4");
	cbCantidadAnticipos.addItem("5");
	cbCantidadAnticipos.addItem("6");
	cbCantidadAnticipos.addItem("7");
	cbCantidadAnticipos.addItem("8");
	cbCantidadAnticipos.addItem("9");
	cbCantidadAnticipos.addItem("10");
	
	cbCantidadCuotas.removeAllItems();
	cbCantidadCuotas.addItem("0");
	cbCantidadCuotas.addItem("1");
	cbCantidadCuotas.addItem("2");
	cbCantidadCuotas.addItem("3");
	cbCantidadCuotas.addItem("4");
	cbCantidadCuotas.addItem("5");
	cbCantidadCuotas.addItem("6");
    }
}



