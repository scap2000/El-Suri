package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.apps.taxes092.classes.EstadoCuenta;
import org.digitall.apps.taxes092.classes.ResumenPlanDePago;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;


public class PanelPrepararPlanDePagos extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelPeriodos = new BasicPanel();
    
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblInteres = new BasicLabel();
    private BasicLabel lblCondonacionIntereses = new BasicLabel();
    private BasicLabel lblCapital = new BasicLabel();
    private BasicLabel lblTotalAPagar = new BasicLabel();
    private BasicLabel lblTotalPeriodosAdeudados = new BasicLabel();
    private BasicLabel lblCantidadCuotas = new BasicLabel();
    private BasicLabel lblInteresFinanciacion = new BasicLabel();
    private BasicLabel lblPagoAnticipado = new BasicLabel();
    private BasicLabel lblTotalAPagarCuotas = new BasicLabel();

    private BasicTextArea taAyuda = new BasicTextArea();
    private TFInput tfPeriodo = new TFInput(DataTypes.STRING, "Desde - Hasta", false);
    private TFInput tfCantAnticipos = new TFInput(DataTypes.STRING, "Períodos", false);
    private TFInput tfBonificacionAplicada = new TFInput(DataTypes.STRING, "Bonificación Aplicada", false);
    private TFInput tfPorcentajeBonif = new TFInput(DataTypes.STRING, "% Bonif.", false);
    private JMoneyEntry tfCapital = new JMoneyEntry();
    private JMoneyEntry tfInteres = new JMoneyEntry();
    private JMoneyEntry tfTotalAPagar = new JMoneyEntry();
    private JMoneyEntry tfSubtotal = new JMoneyEntry();
    private JMoneyEntry tfTotalPeriodosAdeudados = new JMoneyEntry();
    private JMoneyEntry tfSubtotal2 = new JMoneyEntry();
    private JMoneyEntry tfInteresFinanciacion = new JMoneyEntry();
    private JMoneyEntry tfCondonacionIntereses = new JMoneyEntry();
    private JMoneyEntry tfPagoAnticipado = new JMoneyEntry();
    private JMoneyEntry tfTotalAFinanciar = new JMoneyEntry();
    private JComboBox cbCuotas = new JComboBox();
    private JComboBox cbVtos = new JComboBox();
    private JSeparator jSeparator1 = new JSeparator();
    private BasicButton btnImprimir = new BasicButton();
    private BasicButton btnVer = new BasicButton();
    private String leyendaCondonacionIntereses = "Condonación Intereses";
    private String leyendaInteresPorFin = "Interés Financiación";
    private Coordinador coordinador;
    private BasicLabel lblCuotas = new BasicLabel();
    private BasicLabel lblVencimientos = new BasicLabel();
    private boolean verItemChange = false;

    public PanelPrepararPlanDePagos(Coordinador _coordinador) {
	coordinador = _coordinador;
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
	lblTitulo.setText("Preparar Plan de Pago");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
	lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setBounds(new Rectangle(5, 5, 620, 55));
	panelPeriodos.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setLayout(null);
	lblInteres.setText("Interés Adeudado:");
	lblInteres.setBounds(new Rectangle(5, 180, 195, 30));
	lblInteres.setFont(new Font("Dialog", 0, 18));
	lblCondonacionIntereses.setText(leyendaCondonacionIntereses);
	lblCondonacionIntereses.setBounds(new Rectangle(5, 245, 305, 35));
	lblCondonacionIntereses.setFont(new Font("Dialog", 0, 18));
	tfPeriodo.setBounds(new Rectangle(5, 10, 145, 35));
	tfPeriodo.setEditable(false);
	tfCantAnticipos.setBounds(new Rectangle(175, 10, 70, 35));
	tfCantAnticipos.setEditable(false);
	tfBonificacionAplicada.setBounds(new Rectangle(270, 10, 255, 35));
	tfBonificacionAplicada.setEditable(false);
	tfPorcentajeBonif.setBounds(new Rectangle(545, 10, 70, 35));
	tfPorcentajeBonif.setEditable(false);
	lblCapital.setText("Capital Adeudado:");
	lblCapital.setBounds(new Rectangle(5, 145, 195, 30));
	lblCapital.setFont(new Font("Dialog", 0, 18));
	tfCapital.setBounds(new Rectangle(320, 145, 140, 30));
	tfCapital.setEditable(false);
	tfCapital.setFont(new Font("Dialog", 0, 20));
	tfCapital.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfCapital.setForeground(new Color(82, 132, 0));
	tfInteres.setBounds(new Rectangle(320, 180, 140, 30));
	tfInteres.setEditable(false);
	tfInteres.setFont(new Font("Dialog", 0, 20));
	tfInteres.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfInteres.setForeground(new Color(82, 132, 0));
	tfTotalAPagar.setBounds(new Rectangle(365, 425, 255, 45));
	tfTotalAPagar.setEditable(false);
	tfTotalAPagar.setFont(new Font("Dialog", 1, 30));
	tfTotalAPagar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalAPagar.setForeground(Color.red);
	tfSubtotal.setBounds(new Rectangle(480, 245, 140, 30));
	tfSubtotal.setEditable(false);
	tfSubtotal.setFont(new Font("Dialog", 0, 20));
	tfSubtotal.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfSubtotal.setForeground(new Color(82, 132, 0));
	lblTotalAPagar.setText("Total a Pagar:");
	lblTotalAPagar.setBounds(new Rectangle(180, 425, 185, 45));
	lblTotalAPagar.setFont(new Font("Dialog", 1, 20));
	tfTotalPeriodosAdeudados.setBounds(new Rectangle(480, 65, 140, 30));
	tfTotalPeriodosAdeudados.setEditable(false);
	tfTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 21));
	tfTotalPeriodosAdeudados.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalPeriodosAdeudados.setForeground(new Color(0, 165, 0));
	tfSubtotal2.setBounds(new Rectangle(480, 340, 140, 30));
	tfSubtotal2.setEditable(false);
	tfSubtotal2.setFont(new Font("Dialog", 0, 20));
	tfSubtotal2.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfSubtotal2.setForeground(new Color(82, 132, 0));
	lblTotalPeriodosAdeudados.setText("Deuda Total");
	lblTotalPeriodosAdeudados.setBounds(new Rectangle(5, 65, 265, 30));
	lblTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 18));
	tfInteresFinanciacion.setBounds(new Rectangle(320, 340, 140, 30));
	tfInteresFinanciacion.setEditable(false);
	tfInteresFinanciacion.setFont(new Font("Dialog", 0, 20));
	tfInteresFinanciacion.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfInteresFinanciacion.setForeground(new Color(82, 132, 0));
	lblCantidadCuotas.setText("Cantidad de  Cuotas");
	lblCantidadCuotas.setBounds(new Rectangle(5, 300, 305, 30));
	lblCantidadCuotas.setFont(new Font("Dialog", 0, 18));
	lblInteresFinanciacion.setText(leyendaInteresPorFin);
	lblInteresFinanciacion.setBounds(new Rectangle(5, 340, 305, 30));
	lblInteresFinanciacion.setFont(new Font("Dialog", 0, 18));
	cbCuotas.setBounds(new Rectangle(320, 300, 60, 30));
	cbCuotas.setFont(new Font("Dialog", 1, 20));
	cbCuotas.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			if((evt.getStateChange() == ItemEvent.SELECTED) && (verItemChange)){
			    calcularTotalAPagar();
                            setLeyendaInteresFinanciacion();
			}
		}
	});
	cbVtos.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			if((evt.getStateChange() == ItemEvent.SELECTED) && (verItemChange)){
			    setDiasVencimiento();
			}
		}
	});
	cbVtos.setBounds(new Rectangle(400, 300, 60, 30));
	cbVtos.setFont(new Font("Dialog", 1, 20));
	jSeparator1.setBounds(new Rectangle(5, 420, 615, 5));
	tfCondonacionIntereses.setBounds(new Rectangle(320, 245, 140, 30));
	tfCondonacionIntereses.setEditable(false);
	tfCondonacionIntereses.setFont(new Font("Dialog", 0, 20));
	tfCondonacionIntereses.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfCondonacionIntereses.setForeground(new Color(82, 132, 0));
	tfPagoAnticipado.setBounds(new Rectangle(320, 110, 140, 30));
	tfPagoAnticipado.setFont(new Font("Dialog", 0, 20));
        tfPagoAnticipado.setEditable(false);
	tfPagoAnticipado.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfPagoAnticipado.setForeground(new Color(82, 132, 0));
	lblPagoAnticipado.setText("Entrega");
	lblPagoAnticipado.setBounds(new Rectangle(5, 110, 195, 35));
	lblPagoAnticipado.setFont(new Font("Dialog", 0, 18));
	btnImprimir.setText("Imprimir");
	btnImprimir.setBounds(new Rectangle(10, 435, 65, 30));
	btnImprimir.setToolTipText("Imprimir Plan de Pago");
	btnImprimir.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnImprimir_actionPerformed(e);
		}
	    }
	);
	tfTotalAFinanciar.setBounds(new Rectangle(480, 110, 140, 30));
	tfTotalAFinanciar.setEditable(false);
	tfTotalAFinanciar.setFont(new Font("Dialog", 1, 21));
	tfTotalAFinanciar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalAFinanciar.setForeground(Color.red);
	lblTotalAPagarCuotas.setBounds(new Rectangle(5, 390, 615, 30));
	lblTotalAPagarCuotas.setFont(new Font("Dialog", 1, 18));
	lblTotalAPagarCuotas.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalAPagarCuotas.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTotalAPagarCuotas.setForeground(Color.yellow);
	btnVer.setText("Ver");
	btnVer.setBounds(new Rectangle(85, 435, 65, 30));
	btnVer.setToolTipText("Ver configuración de Plan de Pago");
	btnVer.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnVer_actionPerformed(e);
		}
	    }
	);
	lblCuotas.setText("Cuotas:");
	lblCuotas.setBounds(new Rectangle(320, 280, 60, 15));
	lblVencimientos.setText("Vtos.:");
	lblVencimientos.setBounds(new Rectangle(400, 280, 60, 15));
        taAyuda.setEditable(false);
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelCentral.add(lblVencimientos, null);
	panelCentral.add(lblCuotas, null);
	panelCentral.add(btnVer, null);
	panelCentral.add(lblTotalAPagarCuotas, null);
	panelCentral.add(tfTotalAFinanciar, null);
	panelCentral.add(btnImprimir, null);
	panelCentral.add(lblPagoAnticipado, null);
	panelCentral.add(tfPagoAnticipado, null);
	panelCentral.add(tfCondonacionIntereses, null);
	panelCentral.add(jSeparator1, null);
	panelCentral.add(cbVtos, null);
	panelCentral.add(cbCuotas, null);
	panelCentral.add(lblInteresFinanciacion, null);
	panelCentral.add(lblCantidadCuotas, null);
	panelCentral.add(tfInteresFinanciacion, null);
	panelCentral.add(lblTotalPeriodosAdeudados, null);
	panelCentral.add(tfSubtotal2, null);
	panelCentral.add(tfTotalPeriodosAdeudados, null);
	panelCentral.add(lblTotalAPagar, null);
	panelCentral.add(tfSubtotal, null);
	panelCentral.add(tfTotalAPagar, null);
	panelCentral.add(tfInteres, null);
	panelCentral.add(tfCapital, null);
	panelCentral.add(lblCapital, null);
	panelCentral.add(lblCondonacionIntereses, null);
	panelCentral.add(lblInteres, null);
	panelCentral.add(panelPeriodos, null);
	panelPeriodos.add(tfBonificacionAplicada, null);
	panelPeriodos.add(tfPorcentajeBonif, null);
	panelPeriodos.add(tfCantAnticipos, null);
	panelPeriodos.add(tfPeriodo, null);
	this.add(panelCentral, null);
	this.add(panelAyuda, null);
	taAyuda.setText("* Seleccione la cantidad de cuotas del Plan de pagos y el dia de vto. de cada cuota");	
	taAyuda.setEditable(false);
	loadCombos();
    }
    
    private void loadCombos() {
	cbCuotas.removeAllItems();
	for (int i = 2; i < 30; i++)  {
	    cbCuotas.addItem(i);
	}
	cbVtos.removeAllItems();
	for (int i = 1; i < 28; i++)  {
	    cbVtos.addItem(i);
	}
	verItemChange = true;
    }
    
    private void setDiasVencimiento(){
	coordinador.getConfiguracionPlanDePago().setDiaVencimiento(Integer.parseInt(cbVtos.getSelectedItem().toString()));
    }

    private void btnVer_actionPerformed(ActionEvent e) {
	coordinador.setSubOpcion(1);
	coordinador.siguiente();
    }
    
    private void calcularTotalAPagar() {
	/**
	 * 2009-09-18 (cesar)
	 * Descrip.: Calcula el porcentaje de interés a cobrar y el monto de cada cuota (con el incremento del porcentaje de Int. por Financiación )
	 * */
	String textoCuotas = "";
	int cuotas = Integer.parseInt(cbCuotas.getSelectedItem().toString());
        BigDecimal porcentajeIntFin = new BigDecimal("0");
        porcentajeIntFin = porcentajeIntFin.add(new BigDecimal("" + cuotas));
        porcentajeIntFin = porcentajeIntFin.multiply(new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion()));
        porcentajeIntFin = porcentajeIntFin.divide(new BigDecimal("100"));
        
	BigDecimal montoIntFin = new BigDecimal("0");
	BigDecimal montoDeuda = new BigDecimal("0");
	BigDecimal montoPagoCuotas = new BigDecimal("0");
	BigDecimal montoCuota = new BigDecimal("0");
	BigDecimal montoTotalCuotasRestantes = new BigDecimal("0");
	BigDecimal montoPrimerCuota = new BigDecimal("0");
	BigDecimal subTotal2 = new BigDecimal("0");
	BigDecimal totalAPagar = new BigDecimal("0");

	montoDeuda = montoDeuda.add(new BigDecimal(tfSubtotal.getValue().toString()));
        
        montoIntFin = montoIntFin.add(new BigDecimal("" + montoDeuda.doubleValue()));
        montoIntFin = montoIntFin.multiply(new BigDecimal("" + porcentajeIntFin.doubleValue()));
        
	montoPagoCuotas = montoDeuda.add(montoIntFin);
	textoCuotas = "Entrega "+ Format.money(coordinador.getConfiguracionPlanDePago().getPagoAnticipado()) + " + ";
	int cantCuotasEnteras = (cuotas - 1);

	if (cantCuotasEnteras > 0)  {
	    montoCuota =  Format.toDouble(Math.floor(montoPagoCuotas.doubleValue() / cuotas));
	    montoTotalCuotasRestantes = montoCuota.multiply(Format.toDouble(cantCuotasEnteras));
	    montoPrimerCuota =  montoPagoCuotas.subtract(montoTotalCuotasRestantes);
	    
	    if (montoPrimerCuota.doubleValue() > 0)  {
		textoCuotas = textoCuotas + " 1/c de  "+ Format.money(montoPrimerCuota.doubleValue()) +" + "+ cantCuotasEnteras +"/c de  "+ Format.money(montoCuota.doubleValue());
	    } else  {
		textoCuotas = textoCuotas + " 1/c de  "+ Format.money(montoCuota.doubleValue()) ;
	    }
	} else  {
	    montoCuota = Format.toDouble(montoPagoCuotas.doubleValue() / cuotas);
	    textoCuotas = textoCuotas + " 1/c de $ "+ Format.money(montoCuota.doubleValue()) ;
	}
	subTotal2 = subTotal2.add(new BigDecimal(tfSubtotal.getValue().toString()));
	subTotal2 = subTotal2.add(montoIntFin);
        
	totalAPagar = totalAPagar.add(montoPrimerCuota);
	totalAPagar = totalAPagar.add(new BigDecimal(String.valueOf(coordinador.getConfiguracionPlanDePago().getValorAnticipoActual())));
	tfSubtotal2.setValue(subTotal2.doubleValue());
	tfInteresFinanciacion.setValue(montoIntFin);
	lblTotalAPagarCuotas.setText(textoCuotas);
	tfTotalAPagar.setValue(subTotal2);
	//cargamos los datos que faltaban al objeto ConfiguracionPlanDePago
	coordinador.getConfiguracionPlanDePago().setCantidadCuotas(cuotas);
	coordinador.getConfiguracionPlanDePago().setMontoInteresFinanciacion(montoIntFin.doubleValue());
	coordinador.getConfiguracionPlanDePago().setValorPrimerCuota(montoPrimerCuota.doubleValue());
	coordinador.getConfiguracionPlanDePago().setValorCuotasRestantes(montoCuota.doubleValue());
	coordinador.getConfiguracionPlanDePago().setDiaVencimiento(Integer.parseInt(cbVtos.getSelectedItem().toString()));
	coordinador.getConfiguracionPlanDePago().setTotalAPagar(totalAPagar.doubleValue());
	coordinador.getConfiguracionPlanDePago().setTotal(subTotal2.doubleValue());
        coordinador.getConfiguracionPlanDePago().setMontoTotalCuotas(subTotal2.doubleValue());
    }
    
    public void iniciarPanel(){
        String periodo = "";
        BigDecimal subTotalAux = new BigDecimal("0");
        if (coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0) { 
            coordinador.getConfiguracionPlanDePago().retrieveData(coordinador.getTipoPlanDePago(),coordinador.getBien(), coordinador.getBonificacion(),coordinador.getConfiguracionPlanDePago().getPagoAnticipado() );
            if (coordinador.getConfiguracionPlanDePago().isGenerado()) {
                if (coordinador.getConfiguracionPlanDePago().getCantidadAnticipos() > 0) {
                    if (coordinador.getBien().esAutomotor()) {
                        periodo = ((coordinador.getConfiguracionPlanDePago().getMenorAnticipoPPEntrega() +1 )/2) + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioPPEntrega() + " AL " + ((coordinador.getConfiguracionPlanDePago().getMayorAnticipoPPEntrega() + 1 ) / 2) + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnioPPEntrega();
                        periodo = ((coordinador.getConfiguracionPlanDePago().getMenorAnticipoPPEntrega() +1 )/2) + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioPPEntrega() + " AL " + ((coordinador.getConfiguracionPlanDePago().getMayorAnticipoPPEntrega() + 1 ) / 2) + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnioPPEntrega();
                    } else {
                        periodo = coordinador.getConfiguracionPlanDePago().getMenorAnticipoPPEntrega() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioPPEntrega() + " AL " + coordinador.getConfiguracionPlanDePago().getMayorAnticipoPPEntrega() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnioPPEntrega();
                    }
                    /***** [2010-05-05] Modificado por Cesar *****/
                    //tfPagoAnticipado.setValue(coordinador.getConfiguracionPlanDePago().getPagoAnticipado());
                    tfPagoAnticipado.setValue(coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
                    /*******************************************/
                    tfCantAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
                    tfBonificacionAplicada.setValue(coordinador.getBonificacion().getNombre());
                    tfPorcentajeBonif.setValue(coordinador.getBonificacion().getPorcentaje() * 100);
                    /***** [2010-05-05] Modificado por Cesar *****/
                    //tfTotalPeriodosAdeudados.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudadosEntrega());
                    tfTotalPeriodosAdeudados.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados());
                    /*******************************************/
                    if (coordinador.getBonificacion().getPorcentaje() > 0.00) {
                        subTotalAux = subTotalAux.add(new BigDecimal ("" + coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados()));
                        subTotalAux = subTotalAux.subtract(new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses()));
                        tfTotalAFinanciar.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados());
                        tfCapital.setValue(coordinador.getConfiguracionPlanDePago().getCapital());
                        tfInteres.setValue(coordinador.getConfiguracionPlanDePago().getInteres());
                        tfTotalPeriodosAdeudados.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados() + coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
                    } else {
                        subTotalAux = subTotalAux.add(new BigDecimal ("" + coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudadosEntrega()));
                        subTotalAux = subTotalAux.subtract(new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses()));
                        tfTotalAFinanciar.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudadosEntrega());
                        tfTotalPeriodosAdeudados.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados() + coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
                        tfCapital.setValue(coordinador.getConfiguracionPlanDePago().getMontoBasePPEntrega());
                        tfInteres.setValue(coordinador.getConfiguracionPlanDePago().getInteresBasePPEntrega());    
                    }
                    lblCondonacionIntereses.setText(leyendaCondonacionIntereses + " (" + coordinador.getConfiguracionPlanDePago().getPorcentajeCondonacionIntereses() + "%)");
                    tfCondonacionIntereses.setValue(coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses());
                    
                    
                    
                    tfSubtotal.setValue(subTotalAux.doubleValue());
                    
                    setLeyendaInteresFinanciacion();
                    cbCuotas.setSelectedItem(5);
                    cbCuotas.setSelectedItem(2);
                    tfPeriodo.setText(periodo);
                } else {
                    Advisor.messageBox("No se puede hacer un plan de pago sin periodos cubiertos.", "Aviso");
                    coordinador.atras();
                }
            } else {
                Advisor.messageBox("No se puede hacer un plan de pago con la entrega ingresada.", "Aviso");
                coordinador.atras();
            }
            
        } else {
            coordinador.getConfiguracionPlanDePago().retrieveData(coordinador.getTipoPlanDePago(),coordinador.getBien(), coordinador.getBonificacion(),0 );
            if (coordinador.getConfiguracionPlanDePago().isGenerado()) {
                if (coordinador.getConfiguracionPlanDePago().getCantidadAnticipos() > 0) {
                    if (coordinador.getBien().esAutomotor()) {
                        periodo = coordinador.getConfiguracionPlanDePago().getmenorAnticipo()   + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo()  + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnio();
                    }else {
                        periodo = coordinador.getConfiguracionPlanDePago().getmenorAnticipo()  + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnio();
                    }
                    tfPeriodo.setText(periodo);
                    tfCantAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
                    tfBonificacionAplicada.setValue(coordinador.getBonificacion().getNombre());
                    tfPorcentajeBonif.setValue(coordinador.getBonificacion().getPorcentaje() * 100);
                    tfTotalPeriodosAdeudados.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados());
                    tfTotalAFinanciar.setValue(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados());
                    tfCapital.setValue(coordinador.getConfiguracionPlanDePago().getCapital());
                    tfInteres.setValue(coordinador.getConfiguracionPlanDePago().getInteres());
                    lblCondonacionIntereses.setText(leyendaCondonacionIntereses + " (" + coordinador.getConfiguracionPlanDePago().getPorcentajeCondonacionIntereses() + "%)");
                    tfCondonacionIntereses.setValue(coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses());
                    subTotalAux = subTotalAux.add(new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudados()));
                    subTotalAux = subTotalAux.subtract(new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses()));
                    tfSubtotal.setValue(subTotalAux.doubleValue());
                    
                    lblInteresFinanciacion.setText(leyendaInteresPorFin + " (" 
                                                    + coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion() 
                                                    * Integer.parseInt(cbCuotas.getSelectedItem().toString()) +"%)");
                    cbCuotas.setSelectedItem(5);
                    cbCuotas.setSelectedItem(2);
                    tfPagoAnticipado.setValue(0.00);    
                } else {
                    Advisor.messageBox("No se puede hacer un plan de pago sin periodos cubiertos.", "Aviso");
                    coordinador.atras();
                }
            } else {
                Advisor.messageBox("No se puede hacer un plan de pago con la entrega ingresada.", "Aviso");
                coordinador.atras();
            }
        }   
    }

    private void btnImprimir_actionPerformed(ActionEvent e) {
        imprimirConfiguracionPlanPago();
    }

    private void imprimirConfiguracionPlanPago() {
        if (coordinador.getConfiguracionPlanDePago().getTotal() > 0.0) {
            EstadoCuenta estadoCuenta = new EstadoCuenta(coordinador.getBien(),coordinador.getBonificacion(),coordinador.getImpuesto(),true);
            estadoCuenta.retrieveData();
            EstadoCuenta estadoCuentaContado = new EstadoCuenta(coordinador.getBien(),coordinador.getBonificacion(),coordinador.getImpuesto(),true);
            estadoCuentaContado.retrieveData();
            ResumenPlanDePago resumen = new ResumenPlanDePago();
            cargarResumen(resumen);
            //Falta crear la clase ResumenPagoContado
            BasicReport report =
                new BasicReport(PanelPrepararPlanDePagos.class.getResource("xml/EstadoCuentaYMoratoriaReport.xml"));
            TipoImpuesto tipoImpuesto = new TipoImpuesto();
            tipoImpuesto.setIdTipoImpuesto(coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto());
            tipoImpuesto.retrieveData();

            /** DATOS DEL ENCABEZADO */
            report.setProperty("impuesto",
                               tipoImpuesto.getNombre().toString().toUpperCase());
            if (coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() ==
                1 ||
                coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() ==
                2) {
                // Caso de Impuesto TGS ó Inmob
                report.setProperty("cuentacorriente",
                                   coordinador.getBien().getCatastro().getNroCuenta());
                report.setProperty("tituloapoderado", "Apoderado:");
                report.setProperty("titulobien", "Catastro:");
                report.setProperty("titulovalorfiscal", "Val. Fisc.:");
                report.setProperty("valorFiscal",
                                   Double.parseDouble(coordinador.getBien().getCatastro().getValfis()));
                report.setProperty("bien",
                                   coordinador.getBien().getCatastro().getCatastro());
                report.setProperty("apoderado",
                                   coordinador.getBien().getCatastro().getApoderadoLastName() +
                                   " " +
                                   coordinador.getBien().getCatastro().getApoderadoName());
                report.setProperty("domicilio",
                                   coordinador.getBien().getCatastro().getDcalle() +
                                   " Nº " +
                                   coordinador.getBien().getCatastro().getDnumro());

            } else {
                // Caso de Impuesto Automotor
                report.setProperty("cuentacorriente", "");
                report.setProperty("tituloapoderado", "");
                report.setProperty("titulobien", "Dominio:");
                report.setProperty("titulovalorfiscal", "");
                report.setProperty("valorFiscal", "");
                report.setProperty("bien",
                                   coordinador.getBien().getAutomotor().getDominio());
                report.setProperty("apoderado", "");
                report.setProperty("domicilio",
                                   coordinador.getBien().getAutomotor().getDomicilio());
            }
            report.setProperty("contribuyente",
                               coordinador.getBien().getCatastro().getDomape() +
                               " " +
                               coordinador.getBien().getCatastro().getDomapeAux());

            /** DATOS DEL RESUMEN DEL ESTADO DE CUENTA */
            if (coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() == 3) {
                report.setProperty("periodos",
                                   (estadoCuenta.getPeriodoInicial() + 1)/2 + "/" +
                                   estadoCuenta.getAnioInicial() + " al " +
                                   (estadoCuenta.getPeriodoFinal() + 1)/2 + "/" +
                                   estadoCuenta.getAnioFinal());    
            } else {
                report.setProperty("periodos",
                                   estadoCuenta.getPeriodoInicial() + "/" +
                                   estadoCuenta.getAnioInicial() + " al " +
                                   estadoCuenta.getPeriodoFinal() + "/" +
                                   estadoCuenta.getAnioFinal());
            }
            
            report.setProperty("cantperiodos",
                               estadoCuenta.getCantPeriodos());
            report.setProperty("deudaparcial",
                               estadoCuenta.getDeudaParcial());
            report.setProperty("interespormora",
                               estadoCuenta.getInteresParcial());
            report.setProperty("subtotaldeuda",
                               estadoCuenta.getSubTotal());
            report.setProperty("bonificacionespecialoriginal",
                               estadoCuenta.getMontoBonificacion());
            report.setProperty("montototalconintereses",
                               estadoCuentaContado.getTotal());

            if (coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() !=
                0) {
                report.setProperty("titulodtoespecial",
                                   estadoCuenta.getBonificacion().getNombre());
            } else {
                report.setProperty("titulodtoespecial", "");
            }

            /** FIN DATOS DEL RESUMEN DEL ESTADO DE CUENTA */

            /** INICIO DATOS DE MORATORIA/PLAN DE CONTADO */
            //Cambiar la cargar de valores de la clase ResumenPagoContado
            if (coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() == 3) {
                report.setProperty("periodosCtdo",
                                (estadoCuentaContado.getPeriodoInicial() + 1) / 2 + "/" +
                                estadoCuentaContado.getAnioInicial() + " al " +
                                (estadoCuenta.getPeriodoFinal() + 1 ) / 2 + "/" +
                                estadoCuentaContado.getAnioFinal());
            } else {
                report.setProperty("periodosCtdo",
                            estadoCuentaContado.getPeriodoInicial() + "/" +
                            estadoCuentaContado.getAnioInicial() + " al " +
                            estadoCuenta.getPeriodoFinal()  + "/" +
                            estadoCuentaContado.getAnioFinal());
            }
            report.setProperty("cantperiodosCtdo",
                               estadoCuentaContado.getCantPeriodos());
            report.setProperty("lblbonifintereses",
                               "0.00 %");
            report.setProperty("deudaparcialctdo",
                               estadoCuentaContado.getDeudaParcial());
            report.setProperty("interespormoractdo",
                               estadoCuentaContado.getInteresParcial());
            report.setProperty("subtotaldeudactdo",
                               estadoCuentaContado.getSubTotal());
            report.setProperty("condonacionintereses",
                               0.00);
            report.setProperty("titulodtoespecialcontado",
                               estadoCuentaContado.getBonificacion().getNombre());
            report.setProperty("bonificacionespecialcontado",
                               estadoCuentaContado.getMontoBonificacion());
            report.setProperty("bonificacioncontadoplandepago",
                               0.00);
            report.setProperty("lblbonifcontado",
                               " 0.00 %");
            report.setProperty("montocontado",
                               estadoCuentaContado.getTotal());

            /** FIN DATOS DE MORATORIA/PLAN DE CONTADO */

            /** INICIO DATOS DE MORATORIA/PLAN EN CUOTAS */

            report.setProperty("entrega",resumen.getEntrega());
            report.setProperty("deudaparcialpp",resumen.getDeudaParcial());
            report.setProperty("interespormorapp",resumen.getInteresPorMora());
            report.setProperty("subtotaldeudapp",resumen.getSubTotalDeuda());
            report.setProperty("titulodtoespecialpp",(resumen.getBonificacionEspecial() + " ( "+ resumen.getPorcentajeBonificacionEspecial() * 100 +" % )") );
            report.setProperty("bonificacionespecialplandepago",resumen.getMontoBonificacionEspecial());
            report.setProperty("subtotalpp",resumen.getSubTotal());
            report.setProperty("cubreentrega",resumen.getMontoCubiertoEntrega());
            if (resumen.getEntrega() > 0) {
                report.setProperty("periodoanticipos","(" + resumen.getPeriodoAnticipos()+ ")");    
            } else {
                report.setProperty("periodoanticipos","");    
            }
            
            report.setProperty("saldoafinanciar",resumen.getSaldoAFinanciar());
            report.setProperty("periodoplandepago","(" + resumen.getPeriodoPlanDePago() + ")");
            report.setProperty("lblbonifintereses","("+ resumen.getPorcentajeCondIntereses()+" %)");
            report.setProperty("condonacionintereses",resumen.getMontoCondIntereses());
            report.setProperty("lblporcentajeinteresporfinanciacion", "" +(resumen.getPorcentajeIntXFinanciacion() * resumen.getCantidadCuotas()) 
                                                                         +" % ( "+ resumen.getPorcentajeIntXFinanciacion() 
                                                                         +" % por cada cuota)");
            report.setProperty("porcentajeinteresporfinanciacion",resumen.getMontoIntXFinanciacion());
            report.setProperty("totalpp",resumen.getTotal());
            report.setProperty("leyendacuotas",resumen.getLeyendaCuotas());
            
            String param =
                "" + coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() +
                "," + coordinador.getBien().getIdBien() + "," +
                coordinador.getConfiguracionPlanDePago().getMayorAnticipo() +
                "," + coordinador.getConfiguracionPlanDePago().getMayorAnio();
            /** 2009-09-17 (cesar)
        +            * nota: se modificó el nombre del S.P. y se borro el de nombre incorrecto
             * */
            //report.addTableModel("taxes.xmlGetFeesByMoratoriumMF", param);
            report.addTableModel("taxes.xmlGetFeesByPlanOfPayment", param);
            report.doReport();
        } else {
            Advisor.messageBox("El Monto a pagar debe ser mayor que cero",
                               "Aviso");
        }
    }
    
    private void cargarResumen(ResumenPlanDePago _resumen){
        String periodoAnticipos = "";
        String textoCuotas = "";
        EstadoCuenta estadoCuenta = new EstadoCuenta(coordinador.getBien(),coordinador.getBonificacion(),coordinador.getImpuesto(),false);
        estadoCuenta.retrieveData();
        _resumen.setEntrega(coordinador.getConfiguracionPlanDePago().getPagoAnticipado());
        _resumen.setDeudaParcial(estadoCuenta.getDeudaParcial());
        _resumen.setInteresPorMora(estadoCuenta.getInteresParcial());
        _resumen.setSubTotalDeuda(estadoCuenta.getSubTotal());
        _resumen.setBonificacionEspecial(estadoCuenta.getBonificacion().getNombre());
        _resumen.setMontoBonificacionEspecial(estadoCuenta.getMontoBonificacion());
        _resumen.setLeyendaBonificacionEspecial("Bonificacion especial        (" +estadoCuenta.getBonificacion().getNombre() +")     " + estadoCuenta.getMontoBonificacion());
        _resumen.setSubTotal(estadoCuenta.getTotal());
        
        if (coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0.00) {
            _resumen.setMontoCubiertoEntrega(tfPagoAnticipado.getAmount());
        } else {
            _resumen.setMontoCubiertoEntrega(0.00);
        }
        
        if(coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0.00) {
            if (coordinador.getBien().esAutomotor()) {
                periodoAnticipos = ((coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega() + 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioEntrega() + " AL " + ((coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega()+ 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMayorAnioEntrega() ;
            }else {
                periodoAnticipos = coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioEntrega() + " AL " + coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnioEntrega();
            }
        } else {
            periodoAnticipos = "--";
        }
        _resumen.setPeriodoAnticipos(periodoAnticipos);
        _resumen.setSaldoAFinanciar(tfTotalAFinanciar.getAmount());
        _resumen.setPeriodoPlanDePago(tfPeriodo.getTextField().getText());
        _resumen.setCantidadAnticiposPP(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
        _resumen.setCapital(tfCapital.getAmount());
        _resumen.setInteres(tfInteres.getAmount());
        _resumen.setPorcentajeCondIntereses(coordinador.getConfiguracionPlanDePago().getPorcentajeCondonacionIntereses());
        _resumen.setMontoCondIntereses(tfCondonacionIntereses.getAmount());
        _resumen.setPorcentajeIntXFinanciacion(coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion());
        _resumen.setMontoIntXFinanciacion(tfInteresFinanciacion.getAmount());
        _resumen.setTotal(tfTotalAPagar.getAmount());
        if (coordinador.getConfiguracionPlanDePago().getValorCuotasRestantes() > 0.00) {
            textoCuotas = "(1 cuota de $ " + Format.toDouble(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota()) + " + " + (coordinador.getConfiguracionPlanDePago().getCantidadCuotas() - 1) + " cuotas de $ " + coordinador.getConfiguracionPlanDePago().getValorCuotasRestantes() + ").";
        } else {
            textoCuotas = "(" + coordinador.getConfiguracionPlanDePago().getCantidadCuotas() + " cuotas de $ " + coordinador.getConfiguracionPlanDePago().getValorPrimerCuota() + ").";
        }
        _resumen.setCantidadCuotas(coordinador.getConfiguracionPlanDePago().getCantidadCuotas());
        _resumen.setLeyendaCuotas(textoCuotas);
        _resumen.setPorcentajeBonificacionEspecial(estadoCuenta.getBonificacion().getPorcentaje());  
    }
    
    private void setLeyendaInteresFinanciacion(){
        lblInteresFinanciacion.setText(leyendaInteresPorFin + " (" 
                                        + coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion() 
                                        * Integer.parseInt(cbCuotas.getSelectedItem().toString()) +"%)");
    }
}

