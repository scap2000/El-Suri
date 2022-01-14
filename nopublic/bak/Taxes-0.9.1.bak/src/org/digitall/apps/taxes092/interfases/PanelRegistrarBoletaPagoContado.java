package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;

public class PanelRegistrarBoletaPagoContado extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelPeriodos = new BasicPanel();
    
    private BasicLabel lblInteres = new BasicLabel();
    private BasicLabel lblCondonacionIntereses = new BasicLabel();
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblCapital = new BasicLabel();
    private BasicLabel lblTotalAPagar = new BasicLabel();
    private BasicLabel lblTotalPeriodosAdeudados = new BasicLabel();
    private BasicLabel lblBonificacionPagoCtdo = new BasicLabel();
    private BasicLabel lblTotalAFinanciar = new BasicLabel();
    
    private BasicTextArea taAyuda = new BasicTextArea();
    
    private TFInput tfPeriodo = new TFInput(DataTypes.STRING,"Desde - Hasta", false);
    private TFInput tfCantAnticipos = new TFInput(DataTypes.STRING,"Periodos", false);
    private TFInput tfBonificacionAplicada = new TFInput(DataTypes.STRING,"Bonificación Aplicada", false);
    private TFInput tfPorcentajeBonif = new TFInput(DataTypes.STRING,"% Bonif.", false);
    private JMoneyEntry tfCapital = new JMoneyEntry();
    private JMoneyEntry tfInteres = new JMoneyEntry();
    private JMoneyEntry tfTotalAPagar = new JMoneyEntry();
    private JMoneyEntry tfSubtotal = new JMoneyEntry();
    private JMoneyEntry tfTotalPeriodosAdeudados = new JMoneyEntry();
    private JMoneyEntry tfSubtotal2 = new JMoneyEntry();
    private JMoneyEntry tfBonificacionPagoCtdo = new JMoneyEntry();
    private JMoneyEntry tfBonificacion = new JMoneyEntry();
    private JMoneyEntry tfTotalAFinanciar = new JMoneyEntry();
    private JSeparator jSeparator1 = new JSeparator();
    private BasicButton btnGenerar = new BasicButton();
    private String leyendaBonificacionPagoCtdo = "Bonificación Pago Contado";
    
    private Coordinador coordinador;

    public PanelRegistrarBoletaPagoContado(Coordinador _coordinador) {
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
	lblTitulo.setText("<html><center>Registrar Boleta <br> Pago de Contado</center></html>");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 30));
	lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 40, 205, 430));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setBounds(new Rectangle(5, 5, 620, 55));
	panelPeriodos.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setLayout(null);
	lblInteres.setText("Interés:");
	lblInteres.setBounds(new Rectangle(5, 170, 80, 30));
	lblInteres.setFont(new Font("Dialog", 0, 18));
	lblCondonacionIntereses.setText("Bonificación");
	lblCondonacionIntereses.setBounds(new Rectangle(5, 255, 305, 35));
	lblCondonacionIntereses.setFont(new Font("Dialog", 0, 18));
	tfPeriodo.setBounds(new Rectangle(5, 10, 145, 35));
	tfPeriodo.setEditable(false);
	tfCantAnticipos.setBounds(new Rectangle(175, 10, 65, 35));
	tfCantAnticipos.setEditable(false);
	tfBonificacionAplicada.setBounds(new Rectangle(270, 10, 225, 35));
	tfBonificacionAplicada.setEditable(false);
	tfPorcentajeBonif.setBounds(new Rectangle(515, 10, 100, 35));
	tfPorcentajeBonif.setEditable(false);
	lblCapital.setText("Capital:");
	lblCapital.setBounds(new Rectangle(5, 135, 80, 30));
	lblCapital.setFont(new Font("Dialog", 0, 18));
	tfCapital.setBounds(new Rectangle(320, 135, 140, 30));
	tfCapital.setEditable(false);
	tfCapital.setText("$ 500,00");
	tfCapital.setFont(new Font("Dialog", 0, 20));
	tfCapital.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfCapital.setForeground(new Color(82, 132, 0));
	tfInteres.setBounds(new Rectangle(320, 170, 140, 30));
	tfInteres.setEditable(false);
	tfInteres.setFont(new Font("Dialog", 0, 20));
	tfInteres.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfInteres.setText("$ 200,00");
	tfInteres.setForeground(new Color(82, 132, 0));
	tfTotalAPagar.setBounds(new Rectangle(365, 425, 255, 45));
	tfTotalAPagar.setEditable(false);
	tfTotalAPagar.setFont(new Font("Dialog", 1, 30));
	tfTotalAPagar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalAPagar.setText("$ 315,00");
	tfTotalAPagar.setForeground(Color.red);
	tfSubtotal.setBounds(new Rectangle(480, 255, 140, 30));
	tfSubtotal.setEditable(false);
	tfSubtotal.setFont(new Font("Dialog", 0, 20));
	tfSubtotal.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfSubtotal.setText("$ 350,00");
	tfSubtotal.setForeground(new Color(82, 132, 0));
	lblTotalAPagar.setText("Total a Pagar:");
	lblTotalAPagar.setBounds(new Rectangle(180, 425, 185, 45));
	lblTotalAPagar.setFont(new Font("Dialog", 1, 20));
	tfTotalPeriodosAdeudados.setBounds(new Rectangle(480, 65, 140, 30));
	tfTotalPeriodosAdeudados.setEditable(false);
	tfTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 21));
	tfTotalPeriodosAdeudados.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalPeriodosAdeudados.setText("$ 700,00");
	tfTotalPeriodosAdeudados.setForeground(new Color(0, 165, 0));
	tfSubtotal2.setBounds(new Rectangle(480, 350, 140, 30));
	tfSubtotal2.setEditable(false);
	tfSubtotal2.setFont(new Font("Dialog", 0, 20));
	tfSubtotal2.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfSubtotal2.setText("$ 315,00");
	tfSubtotal2.setForeground(new Color(82, 132, 0));
	lblTotalPeriodosAdeudados.setText("Total Períodos Adeudados");
	lblTotalPeriodosAdeudados.setBounds(new Rectangle(5, 65, 265, 30));
	lblTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 18));
	tfBonificacionPagoCtdo.setBounds(new Rectangle(320, 350, 140, 30));
	tfBonificacionPagoCtdo.setEditable(false);
	tfBonificacionPagoCtdo.setFont(new Font("Dialog", 0, 20));
	tfBonificacionPagoCtdo.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfBonificacionPagoCtdo.setText("$ 35,00");
	tfBonificacionPagoCtdo.setForeground(new Color(82, 132, 0));
	lblBonificacionPagoCtdo.setText(leyendaBonificacionPagoCtdo);
	lblBonificacionPagoCtdo.setBounds(new Rectangle(5, 350, 310, 30));
	lblBonificacionPagoCtdo.setFont(new Font("Dialog", 0, 18));
	jSeparator1.setBounds(new Rectangle(5, 385, 615, 5));
	tfBonificacion.setBounds(new Rectangle(320, 255, 140, 30));
	tfBonificacion.setEditable(false);
	tfBonificacion.setFont(new Font("Dialog", 0, 20));
	tfBonificacion.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfBonificacion.setText("$ 350,00");
	tfBonificacion.setForeground(new Color(82, 132, 0));
	tfTotalAFinanciar.setBounds(new Rectangle(480, 210, 140, 30));
	tfTotalAFinanciar.setEditable(false);
	tfTotalAFinanciar.setFont(new Font("Dialog", 1, 21));
	tfTotalAFinanciar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalAFinanciar.setText("$ 700,00");
	tfTotalAFinanciar.setForeground(Color.red);
	lblTotalAFinanciar.setText("Total");
	lblTotalAFinanciar.setBounds(new Rectangle(5, 210, 200, 30));
	lblTotalAFinanciar.setFont(new Font("Dialog", 1, 18));
	btnGenerar.setText("Registrar");
	btnGenerar.setBounds(new Rectangle(30, 435, 75, 30));
	btnGenerar.setToolTipText("Registrar Pago de Contado");
	btnGenerar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGenerar_actionPerformed(e);
		}
	    }
	);
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
        panelCentral.add(btnGenerar, null);
        panelCentral.add(lblTotalAFinanciar, null);
	panelCentral.add(tfTotalAFinanciar, null);
	panelCentral.add(tfBonificacion, null);
	panelCentral.add(jSeparator1, null);
	panelCentral.add(lblBonificacionPagoCtdo, null);
	panelCentral.add(tfBonificacionPagoCtdo, null);
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
	taAyuda.setEditable(false);
    }
    
    public void iniciarPanel(){
	coordinador.getTipoPlanDePago().setIdTipoImpuesto(coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto());
	coordinador.getTipoPlanDePago().retrieveData2();
        tfPeriodo.setValue(coordinador.getPagoContado().getPeriodoDesdeVista() + "/" + coordinador.getPagoContado().getAnioDesde() + " AL " + coordinador.getPagoContado().getPeriodoHastaVista() + "/" +coordinador.getPagoContado().getAnioHasta());
	tfCantAnticipos.setValue(coordinador.getPagoContado().getCantidadPeriodos());
	tfBonificacionAplicada.setValue(coordinador.getBonificacion().getNombre());
	tfPorcentajeBonif.setValue(coordinador.getBonificacion().getPorcentaje() * 100);
	tfTotalPeriodosAdeudados.setValue(coordinador.getPagoContado().getTotalPuro());
	tfCapital.setValue(coordinador.getPagoContado().getCapitalPuro());
	tfInteres.setValue(coordinador.getPagoContado().getInteresPuro());
        tfTotalAFinanciar.setValue(coordinador.getPagoContado().getTotalPuro());
	tfBonificacion.setValue(coordinador.getPagoContado().getDescuento());
	tfSubtotal.setValue(coordinador.getPagoContado().getTotal());
	tfSubtotal2.setValue(coordinador.getPagoContado().getTotal());
	tfTotalAPagar.setValue(coordinador.getPagoContado().getTotal());
    }
    
    private void calcularTotalAPagar() {
	/**
	 * 2009-09-18 (cesar)
	 * Descrip.: Calcula el porcentaje de interés a cobrar y el monto de cada cuota (con el incremento del porcentaje de Int. por Financiación )
	 * */
	String textoCuotas = "";
	int cuotas = 1;
	double porcentajeIntFin = 0.0;
	porcentajeIntFin = (cuotas * coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion()/100.0);
	BigDecimal montoIntFin = new BigDecimal("0");
	BigDecimal montoDeuda = new BigDecimal("0");
	BigDecimal montoPagoCuotas = new BigDecimal("0");
	BigDecimal montoCuota = new BigDecimal("0");
	BigDecimal montoTotalCuotasRestantes = new BigDecimal("0");
	BigDecimal montoPrimerCuota = new BigDecimal("0");
	BigDecimal subTotal2 = new BigDecimal("0");
	BigDecimal totalAPagar = new BigDecimal("0");

	montoDeuda = montoDeuda.add(new BigDecimal(tfSubtotal.getValue().toString()));
	montoIntFin = Format.toDouble(montoDeuda.doubleValue() * porcentajeIntFin);
	montoPagoCuotas = montoDeuda.add(montoIntFin);
	textoCuotas = "Anticipo $ "+ coordinador.getConfiguracionPlanDePago().getPagoAnticipado() + " + ";
	int cantCuotasEnteras = (cuotas - 1);

	if (cantCuotasEnteras > 0)  {
	    montoCuota =  Format.toDouble(Math.floor(montoPagoCuotas.doubleValue() / cuotas));
	    montoTotalCuotasRestantes = montoCuota.multiply(Format.toDouble(cantCuotasEnteras));
	    montoPrimerCuota =  montoPagoCuotas.subtract(montoTotalCuotasRestantes);
	    
	    if (montoPrimerCuota.doubleValue() > 0)  {
		textoCuotas = textoCuotas + " 1/c de $ "+ Format.money(montoPrimerCuota.doubleValue()) +" + "+ cantCuotasEnteras +"/c de $ "+ Format.money(montoCuota.doubleValue());
	    } else  {
		textoCuotas = textoCuotas + " 1/c de $ "+ Format.money(montoCuota.doubleValue()) ;
	    }
	} else  {
	    montoCuota = Format.toDouble(montoPagoCuotas.doubleValue() / cuotas);
	    textoCuotas = textoCuotas + " 1/c de $ "+ Format.money(montoCuota.doubleValue()) ;
	}
	subTotal2 = subTotal2.add(new BigDecimal(tfSubtotal.getValue().toString()));
	subTotal2 = subTotal2.add(montoIntFin);
	totalAPagar = totalAPagar.add(montoPrimerCuota);
	totalAPagar = totalAPagar.add(new BigDecimal(String.valueOf(coordinador.getConfiguracionPlanDePago().getValorAnticipoActual())));
	tfSubtotal2.setValue(subTotal2);
	tfBonificacionPagoCtdo.setValue(montoIntFin);
	tfTotalAPagar.setValue(subTotal2);
	//cargamos los datos que faltaban al objeto ConfiguracionPlanDePago
	coordinador.getConfiguracionPlanDePago().setCantidadCuotas(cuotas);
	coordinador.getConfiguracionPlanDePago().setMontoInteresFinanciacion(montoIntFin.doubleValue());
	coordinador.getConfiguracionPlanDePago().setValorPrimerCuota(montoPrimerCuota.doubleValue());
	coordinador.getConfiguracionPlanDePago().setValorCuotasRestantes(montoCuota.doubleValue());
	coordinador.getConfiguracionPlanDePago().setDiaVencimiento(15);
	coordinador.getConfiguracionPlanDePago().setTotalAPagar(totalAPagar.doubleValue());
	coordinador.getConfiguracionPlanDePago().setTotal(subTotal2.doubleValue());
    }

    private void btnGenerar_actionPerformed(ActionEvent e) {
        if(Advisor.question("Aviso","¿Está seguro de generar boleta para " + coordinador.getPagoContado().getCantidadPeriodos()+" anticipos por un total de $ "+Format.money(coordinador.getPagoAnticipos().getMontoTotal())+"?")){
            if(coordinador.registrarBoletaAnticipos2()){
               coordinador.clearPagoAnticipo();
               coordinador.clearPagoContado();
               coordinador.inicio();
            }else{
                Advisor.messageBox("Ocurrió un error al grabar los datos del pago de contado.","Aviso!");
            }
        }
    }
    
    private void registrarBoleta(){
            coordinador.setSubOpcion(coordinador.OPCION_REGISTRAR_BOLETA);
            coordinador.siguiente();
    }
    
    private void registrarPagoContado(){
	if (coordinador.getTipoPlanDePago().isObligatorio()) {
	    if (Advisor.question("Aviso","¿Está seguro de registrar la Moratoria configurada?"))  {
	       if(coordinador.registrarMoratoria()){
		   Advisor.messageBox("Se grabó correctamente la Moratoria","Aviso");
	       }else{
		   Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
	       }
	    }
	} else {
	    /** 
	     * Plan de pago normal
	     * */
	     if (Advisor.question("Aviso","¿Quiere registrar el Plan de Pago configurado?"))  {
		 if(coordinador.registrarPlanDePago()){
		     Advisor.messageBox("Se grabó correctamente el Plan de Pago","Aviso");
		     cargarPagoCuota();
		     if(coordinador.registrarBoletaCuotasPlanDePago()){
			coordinador.inicio();
		     }
		 }else{
		     Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
		 }
	     } else {
		//por ahora no va
	     }
	}
    }
    
     private void cargarPagoCuota(){
	 coordinador.getPagoCuotasPlanPago().setMonto(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota());
	 coordinador.getPagoCuotasPlanPago().setImporte(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota());
	 coordinador.getPagoCuotasPlanPago().setDeduccion(0.0);
	 coordinador.getPagoCuotasPlanPago().setRecargoPorMora(0.0);
	 coordinador.getPagoCuotasPlanPago().setCantidadCuotas(1);
     }
}

