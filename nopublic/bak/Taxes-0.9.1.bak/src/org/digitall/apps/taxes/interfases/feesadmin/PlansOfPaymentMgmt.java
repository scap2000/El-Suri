package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.math.BigDecimal;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.apps.taxes.classes.Moratoria;
import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.apps.taxes.classes.TipoPlanDePago;
import org.digitall.apps.taxes.interfases.feesadmin.TaxesMain;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;

public class PlansOfPaymentMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel headerPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel southPanel = new BasicPanel();
    private BasicPanel anticiposPanel = new BasicPanel();
    private BasicPanel wizardPanel = new BasicPanel();

    private BorderLayout borderLayout1 = new BorderLayout();

    private TFInput tfAnticipoDesde = new TFInput(DataTypes.STRING,"Desde",false);
    private TFInput tfAnticipoHasta = new TFInput(DataTypes.STRING,"Hasta",false);
    private TFInput tfCantidadAnticipos = new TFInput(DataTypes.INTEGER,"Cant. Ant.",false);
    private TFInput tfDescuentoAplicado = new TFInput(DataTypes.STRING,"Descuento",false);

    private UnAssignButton btnBack = new UnAssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private PrintSaveButton btnSave = new PrintSaveButton();
    private PrintButton btnPrint = new PrintButton();
    private AssignButton btnNext = new AssignButton();
    
    private BasicRadioButton rbtnContado = new BasicRadioButton();
    private BasicRadioButton rbtnCuotas = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    
    private BasicLabel lblTitleDeuda = new BasicLabel();
    private BasicLabel lblTitleInteres = new BasicLabel();
    private BasicLabel lblTitleMontoTotal = new BasicLabel();

    private BasicLabel lblDeudaMoratoria = new BasicLabel();
    private BasicLabel lblCondIntereses = new BasicLabel();
    private BasicLabel lblInteresPorFinanciacion = new BasicLabel();
    private BasicLabel lblTitleMontoMoratoria = new BasicLabel();
    
    private JMoneyEntry tfPorcentajeCondInt = new JMoneyEntry();
    private JMoneyEntry tfDeuda = new JMoneyEntry();
    private JMoneyEntry tfMontoPagoContado = new JMoneyEntry();
    private JMoneyEntry tfMontoPagoCuotas = new JMoneyEntry();
    private JMoneyEntry tfMontoIntFin = new JMoneyEntry();
    private JMoneyEntry tfMontoTotal = new JMoneyEntry();

    private JMoneyEntry tfMontoDeuda = new JMoneyEntry();
    private JMoneyEntry tfMontoInteres = new JMoneyEntry();
    private JMoneyEntry tfMontoTotalCI = new JMoneyEntry();

    private JComboBox jcbCuotas = new JComboBox();
    private JComboBox jcbVtos = new JComboBox();

    private BasicLabel lblCuotas = new BasicLabel();
    private BasicLabel lblVtos = new BasicLabel();
    
    private Coordinador coordinador;
    private TipoPlanDePago tipoPlanDePago;
    private Moratoria moratoria;
    
    private BigDecimal deudaPura = new BigDecimal("0");
    private BigDecimal interesPuro = new BigDecimal("0");
    private BigDecimal montoTotalPuro = new BigDecimal("0");
    private BigDecimal bonificacionEspecial = new BigDecimal("0");
    
    private BigDecimal deuda = new BigDecimal("0");
    private BigDecimal interes = new BigDecimal("0");
    private BigDecimal montoTotal = new BigDecimal("0");
    
    private BigDecimal condIntereses = new BigDecimal("0");
    private BigDecimal montoDeuda = new BigDecimal("0");
    private BigDecimal porcentajeContado = new BigDecimal("0");
    private BigDecimal montoPagoContado = new BigDecimal("0");
    private BigDecimal montoIntFin = new BigDecimal("0");
    private BigDecimal montoPagoCuotas = new BigDecimal("0");
    private BigDecimal montoCuota = new BigDecimal("0");
    
    private BigDecimal montoTotalCuotasRestantes = new BigDecimal("0");
    private BigDecimal montoPrimerCuota = new BigDecimal("0");
    
    double porcentajeIntFin = 0.0;

    private BasicLabel lblMontoCuotas = new BasicLabel();

    public PlansOfPaymentMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Moratoria/Plan de Pago");
	content.setBounds(new Rectangle(5, 0, 900, 500));
	content.setLayout(borderLayout1);
	content.setSize(new Dimension(900, 500));
	btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSave_actionPerformed(e);
				 }

			     }
	);
	btnPrint.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnPrint_actionPerformed(e);
				 }

			     }
	);
	southPanel.setLayout(null);
	southPanel.setPreferredSize(new Dimension(860, 50));
	centralPanel.setLayout(null);
	anticiposPanel.add(tfDescuentoAplicado, null);
	anticiposPanel.add(tfCantidadAnticipos, null);
	anticiposPanel.add(tfAnticipoHasta, null);
	anticiposPanel.add(tfAnticipoDesde, null);
	centralPanel.add(lblMontoCuotas, null);
	centralPanel.add(tfMontoIntFin, null);
	centralPanel.add(tfPorcentajeCondInt, null);
	centralPanel.add(lblVtos, null);
	centralPanel.add(jcbVtos, null);
	centralPanel.add(lblCuotas, null);
	centralPanel.add(jcbCuotas, null);
	centralPanel.add(anticiposPanel, null);
	centralPanel.add(lblTitleMontoMoratoria, null);
	centralPanel.add(tfMontoTotal, null);
	centralPanel.add(tfMontoPagoCuotas, null);
	centralPanel.add(lblInteresPorFinanciacion, null);
	centralPanel.add(lblCondIntereses, null);
	centralPanel.add(lblDeudaMoratoria, null);
	centralPanel.add(tfMontoPagoContado, null);
	centralPanel.add(rbtnCuotas, null);
	centralPanel.add(rbtnContado, null);
	centralPanel.add(tfDeuda, null);
	centralPanel.setSize(new Dimension(900, 333));
	headerPanel.setLayout(null);
	headerPanel.setPreferredSize(new Dimension(970, 70));
	headerPanel.add(tfMontoInteres, null);
	headerPanel.add(lblTitleInteres, null);
	headerPanel.add(lblTitleMontoTotal, null);
	headerPanel.add(lblTitleDeuda, null);
	headerPanel.add(tfMontoDeuda, null);
	headerPanel.add(tfMontoTotalCI, null);
	content.add(headerPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
	wizardPanel.add(btnFirst, null);
	wizardPanel.add(btnNext, null);
	wizardPanel.add(btnBack, null);
	southPanel.add(wizardPanel, null);
	content.add(southPanel, BorderLayout.SOUTH);
	centralPanel.add(rbtnCuotas, null);
	centralPanel.add(rbtnContado, null);
	btnNext.setToolTipText("Ir a la pestaña siguiente");
	btnBack.setToolTipText("Ir a la pestaña anterior");
	btnFirst.setToolTipText("Inicio");
	this.add(content, null);
	this.addButton(btnSave);
	this.addButton(btnPrint);
	tfDeuda.setEditable(false);
	tfMontoInteres.setEditable(false);
	tfMontoTotalCI.setEditable(false);
	tfMontoDeuda.setEditable(false);
	tfMontoPagoContado.setEditable(false);
	tfMontoPagoCuotas.setEditable(false);
	tfMontoTotal.setEditable(false);
	tfPorcentajeCondInt.setEditable(false);
	tfMontoIntFin.setEditable(false);
	btnBack.setBounds(new Rectangle(40, 15, 25, 20));
	btnBack.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBack_actionPerformed(e);
		}

	    }
	);
	btnNext.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnNext_actionPerformed(e);
		}

	    }
	);
	//btnBack.setToolTipText("Volver al panel Catastros/Dominios");
	btnBack.setToolTipText("Volver a la pestaña anterior");
	btnBack.setSize(new Dimension(25, 20));
	btnSave.setToolTipText("Grabar e Imprimir Moratoria/Plan de Pago");
	btnPrint.setToolTipText("Imprimir Moratoria/Plan de Pago y Estado de Deuda");
	headerPanel.setBorder(BorderPanel.getBorderPanel("Deuda"));
	centralPanel.setBorder(BorderPanel.getBorderPanel("Moratoria/Plan de Pago"));
	centralPanel.setPreferredSize(new Dimension(1, 333));
	rbtnContado.setBounds(new Rectangle(345, 113, 315, 40));
	rbtnContado.setFont(new Font("Lucida Sans", 0, 25));
	rbtnCuotas.setBounds(new Rectangle(345, 162, 305, 40));
	grupo.add(rbtnContado);
	grupo.add(rbtnCuotas);
	rbtnCuotas.setSelected(true);
	rbtnCuotas.setText("Pago en Cuotas");
	rbtnCuotas.setFont(new Font("Lucida Sans", 0, 25));
	tfDeuda.setBounds(new Rectangle(675, 40, 145, 40));
	tfDeuda.setFont(new Font("Dialog", 1, 30));
	tfDeuda.setForeground(new Color(0, 125, 0));
	tfMontoPagoContado.setBounds(new Rectangle(675, 105, 145, 40));
	tfMontoPagoContado.setFont(new Font("Dialog", 1, 30));
	tfMontoPagoContado.setForeground(new Color(0, 125, 0));
	tfMontoPagoContado.setEditable(false);
	tfMontoPagoContado.setBounds(new Rectangle(670, 113, 215, 40));
	lblDeudaMoratoria.setText("Deuda");
	lblDeudaMoratoria.setBounds(new Rectangle(370, 64, 295, 40));
	lblDeudaMoratoria.setFont(new Font("Lucida Sans", 0, 25));
	lblCondIntereses.setBounds(new Rectangle(370, 15, 295, 40));
	lblCondIntereses.setFont(new Font("Lucida Sans", 0, 23));
	lblCondIntereses.setText("Cond. Int.");
	lblInteresPorFinanciacion.setText("Int. Fin.");
	lblInteresPorFinanciacion.setBounds(new Rectangle(370, 210, 295, 40));
	lblInteresPorFinanciacion.setFont(new Font("Lucida Sans", 0, 25));
	tfMontoPagoCuotas.setBounds(new Rectangle(675, 175, 145, 40));
	tfMontoPagoCuotas.setFont(new Font("Dialog", 1, 30));
	tfMontoPagoCuotas.setForeground(new Color(0, 125, 0));
	tfMontoPagoCuotas.setEditable(false);
	tfMontoPagoCuotas.setBounds(new Rectangle(670, 162, 215, 40));
	tfMontoTotal.setBounds(new Rectangle(675, 245, 145, 40));
	tfMontoTotal.setFont(new Font("Dialog", 1, 30));
	tfMontoTotal.setForeground(Color.red);
	tfMontoTotal.setEditable(false);
	tfMontoTotal.setBounds(new Rectangle(670, 260, 215, 40));
	tfMontoDeuda.setBounds(new Rectangle(115, 15, 120, 40));
	tfMontoDeuda.setFont(new Font("Dialog", 1, 20));
	tfMontoDeuda.setForeground(new Color(0, 125, 0));
	tfMontoDeuda.setBounds(new Rectangle(100, 15, 165, 40));
	tfMontoInteres.setBounds(new Rectangle(445, 15, 120, 40));
	tfMontoInteres.setFont(new Font("Dialog", 1, 20));
	tfMontoInteres.setForeground(new Color(255, 132, 0));
	tfMontoInteres.setBounds(new Rectangle(410, 15, 165, 40));
	tfMontoTotalCI.setBounds(new Rectangle(745, 15, 120, 40));
	tfMontoTotalCI.setFont(new Font("Dialog", 1, 20));
	tfMontoTotalCI.setForeground(Color.red);
	tfMontoTotalCI.setBounds(new Rectangle(720, 15, 165, 40));
	wizardPanel.setBounds(new Rectangle(790, 0, 110, 50));
	wizardPanel.setBackground(new Color(185, 96, 0));
	wizardPanel.setLayout(null);
	wizardPanel.setSize(new Dimension(110, 50));
	tfAnticipoDesde.setBounds(new Rectangle(10, 20, 70, 35));
	tfAnticipoHasta.setBounds(new Rectangle(105, 20, 70, 35));
	tfAnticipoDesde.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfAnticipoHasta.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfCantidadAnticipos.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfDescuentoAplicado.getTextField().setHorizontalAlignment(JTextField.CENTER);
	tfCantidadAnticipos.setBounds(new Rectangle(10, 60, 70, 35));
	anticiposPanel.setBounds(new Rectangle(10, 85, 185, 145));
	btnNext.setBounds(new Rectangle(80, 15, 25, 20));
	btnNext.setToolTipText("Ir a la pestaña siguiente");
	lblTitleMontoMoratoria.setText("Total a Pagar");
	lblTitleMontoMoratoria.setBounds(new Rectangle(370, 260, 295, 40));
	lblTitleMontoMoratoria.setFont(new Font("Lucida Sans", 0, 25));
	lblTitleDeuda.setText("Deuda:");
	lblTitleDeuda.setBounds(new Rectangle(15, 25, 85, 25));
	lblTitleDeuda.setFont(new Font("Lucida Sans", 0, 20));
	lblTitleDeuda.setHorizontalTextPosition(SwingConstants.RIGHT);
	lblTitleDeuda.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTitleMontoTotal.setText("Total:");
	lblTitleMontoTotal.setBounds(new Rectangle(640, 25, 80, 25));
	lblTitleMontoTotal.setFont(new Font("Lucida Sans", 0, 20));
	lblTitleMontoTotal.setHorizontalTextPosition(SwingConstants.RIGHT);
	lblTitleMontoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTitleInteres.setText("Interés:");
	lblTitleInteres.setBounds(new Rectangle(305, 25, 105, 25));
	lblTitleInteres.setFont(new Font("Lucida Sans", 0, 20));
	lblTitleInteres.setHorizontalTextPosition(SwingConstants.RIGHT);
	lblTitleInteres.setHorizontalAlignment(SwingConstants.RIGHT);
	jcbCuotas.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			//calcularCuotas();
			calcularTotalAPagar();
		    }
		}

	    }
	);
	rbtnContado.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rbtnContado_actionPerformed(e);
		}
	    }
	);
	rbtnCuotas.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    rbtnCuotas_actionPerformed(e);
		}
	    }
	);
	btnSave.setEnabled(false);
	tfDeuda.setBounds(new Rectangle(670, 64, 215, 40));
	anticiposPanel.setBorder(BorderPanel.getBorderPanel("Anticipos a Pagar"));
	anticiposPanel.setLayout(null);
	tfAnticipoDesde.setEditable(false);
	tfAnticipoHasta.setEditable(false);
	tfCantidadAnticipos.setEditable(false);
	tfDescuentoAplicado.setEditable(false);
	tfDescuentoAplicado.setBounds(new Rectangle(10, 100, 165, 35));
	jcbCuotas.setBounds(new Rectangle(205, 165, 60, 30));
	jcbCuotas.setFont(new Font("Dialog", 1, 20));
	lblCuotas.setText("Cuotas:");
	lblCuotas.setBounds(new Rectangle(205, 145, 70, 15));
	lblCuotas.setFont(new Font("Lucida Sans", 0, 15));
	jcbVtos.setBounds(new Rectangle(275, 165, 60, 30));
	jcbVtos.setFont(new Font("Dialog", 1, 20));
	lblVtos.setText("Vtos:");
	lblVtos.setBounds(new Rectangle(275, 145, 60, 15));
	lblVtos.setFont(new Font("Lucida Sans", 0, 15));
	lblMontoCuotas.setBounds(new Rectangle(10, 265, 350, 30));
	lblMontoCuotas.setFont(new Font("Lucida Sans", 1, 14));
	lblMontoCuotas.setForeground(Color.yellow);
	tfMontoPagoCuotas.setBackground(Color.yellow);
	tfPorcentajeCondInt.setBounds(new Rectangle(670, 15, 215, 40));
	tfPorcentajeCondInt.setFont(new Font("Dialog", 1, 30));
	tfPorcentajeCondInt.setForeground(new Color(0, 125, 0));
	tfMontoIntFin.setBounds(new Rectangle(670, 211, 215, 40));
	tfMontoIntFin.setFont(new Font("Dialog", 1, 30));
	tfMontoIntFin.setForeground(new Color(0, 125, 0));
	btnFirst.setBounds(new Rectangle(10, 15, 25, 20));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
    
	loadComboVtos();
	jcbVtos.setEnabled(true);
	btnSave.setVisible(false);
	lblMontoCuotas.setVisible(false);
    }
    
    private void loadComboVtos() {
	for (int i = 1 ; i <= 28 ; i++)  {
	    jcbVtos.addItem(i);
	}
    }
    
    private void loadCombo() {
	/** Cargar el combo con las cantidades de cuotas permitidas por la Moratoria */
	jcbCuotas.removeAllItems();
	for (int i = coordinador.getTipoPlanDePago().getMinCuota() ; i <= coordinador.getTipoPlanDePago().getMaxCuotas() ; i++)  {
	    jcbCuotas.addItem(i);
	}
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

   
    private void btnSave_actionPerformed(ActionEvent e) {
	
    }

    private void btnPrint_actionPerformed(ActionEvent e) {	
	if (tfMontoTotal.getAmount() > 0.0) {
	    
	    BasicReport report = new BasicReport(PlansOfPaymentMgmt.class.getResource("xml/EstadoCuentaYMoratoriaReport.xml"));
	    TipoImpuesto tipoImpuesto = new TipoImpuesto();
	    tipoImpuesto.setIdTipoImpuesto(coordinador.getIdTipoImpuesto());
	    tipoImpuesto.retrieveData();

	    /** DATOS DEL ENCABEZADO */
	     report.setProperty("impuesto", tipoImpuesto.getNombre().toString().toUpperCase());
	    if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2) {
		// Caso de Impuesto TGS ó Inmob
		report.setProperty("cuentacorriente", coordinador.getCatastro().getNroCuenta());
	        report.setProperty("tituloapoderado", "Apoderado:");
		report.setProperty("titulobien", "Catastro:");
	        report.setProperty("titulovalorfiscal", "Val. Fisc.:");
	        report.setProperty("valorFiscal",Double.parseDouble(coordinador.getCatastro().getValfis()));
		report.setProperty("bien", coordinador.getCatastro().getCatastro());
		report.setProperty("apoderado", coordinador.getCatastro().getApoderadoLastName() + " " + coordinador.getCatastro().getApoderadoName());
		report.setProperty("domicilio", coordinador.getCatastro().getDcalle() + " Nº " + coordinador.getCatastro().getDnumro());
		
	    } else {
		 // Caso de Impuesto Automotor
		report.setProperty("cuentacorriente", "");
	        report.setProperty("tituloapoderado", "");
		report.setProperty("titulobien", "Dominio:");
	        report.setProperty("titulovalorfiscal", "");
	        report.setProperty("valorFiscal","");
		report.setProperty("bien", coordinador.getAutomotor().getDominio());
		report.setProperty("apoderado", "");
		report.setProperty("domicilio", coordinador.getAutomotor().getDomicilio());
	    }
	    report.setProperty("contribuyente", coordinador.getContribuyente());
	    
	    /** DATOS DEL RESUMEN DEL ESTADO DE CUENTA */
	    report.setProperty("periodos", coordinador.getAnticipoDesde() + "/" + coordinador.getAnioDesde() + "  al  " + coordinador.getAnticipoHasta() + "/" + coordinador.getAnioHasta());
	    report.setProperty("cantperiodos", coordinador.getCantPeriodos());
	    report.setProperty("deudaparcial", deudaPura);
	    report.setProperty("interespormora", interesPuro);
	    report.setProperty("subtotaldeuda", montoTotalPuro);
	    report.setProperty("bonificacionespecialoriginal", bonificacionEspecial);
	    report.setProperty("montototalconintereses", montoTotal);
	    
	    if (coordinador.getTipoDescuento() != 0) {
	        report.setProperty("titulodtoespecial", coordinador.getDescuento());
	    } else {
	        report.setProperty("titulodtoespecial", "");
	    }
	    
	    /** FIN DATOS DEL RESUMEN DEL ESTADO DE CUENTA */

	    /** INICIO DATOS DE MORATORIA/PLAN DE CONTADO */

	    report.setProperty("lblbonifintereses", Format.toDouble(coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()) + " %");
	    report.setProperty("condonacionintereses", condIntereses);
	    report.setProperty("bonificacionespecialplandepago", bonificacionEspecial );
	    report.setProperty("bonificacioncontadoplandepago", porcentajeContado );
	    report.setProperty("lblbonifcontado",  Format.toDouble(coordinador.getTipoPlanDePago().getPorcentDtoPagoContado()) + " %");
	    report.setProperty("montocontado", montoPagoContado );

	     /** FIN DATOS DE MORATORIA/PLAN DE CONTADO */
	     
	    /** INICIO DATOS DE MORATORIA/PLAN EN CUOTAS */

	    report.setProperty("totalapagarencuotas", montoPagoCuotas);
	    report.setProperty("lblporcentajeinteresporfinanciacion", Format.toDouble(coordinador.getTipoPlanDePago().getPorcentajeInteresCuota()) + " % (por cada cuota)");
	    report.setProperty("porcentajeinteresporfinanciacion", montoIntFin);
	    //report.setProperty("cantcuotas", jcbCuotas.getSelectedItem() + " cuotas de");
	    report.setProperty("cantcuotas", lblMontoCuotas.getText());
	    report.setProperty("montocuotas", montoCuota);
	    
	    String param = "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + coordinador.getAnticipoHasta() + "," + coordinador.getAnioHasta();
	    /** 2009-09-17 (cesar) 
+	     * nota: se modificó el nombre del S.P. y se borro el de nombre incorrecto
	     * */
	    //report.addTableModel("taxes.xmlGetFeesByMoratoriumMF", param);
	    report.addTableModel("taxes.xmlGetFeesByPlanOfPayment", param);
	    report.doReport();
	} else {
	    Advisor.messageBox("El Monto a pagar debe ser mayor que cero", "Aviso");
	}

    }

    public void setCoordinador(Coordinador _coordinador) {
	/**
	 * 18/09/2009 (cesar)
	 * Descrip.: Setéa los valores fijos de acuerdo a la configuración del P.P. seleccionado
	 * */
	coordinador = _coordinador;
	loadTipoPlanDepago();
	coordinador.setTipoPlanDePago(tipoPlanDePago);
	coordinador.retrieveData();
	
	deudaPura = Format.toDouble(coordinador.getMonto());
	interesPuro = Format.toDouble(coordinador.getInteres());
	montoTotalPuro = deudaPura.add(interesPuro);
	
	deuda = Format.toDouble(deudaPura.doubleValue() * (1 -  coordinador.getPorcentajeDto()));
	interes = Format.toDouble(interesPuro.doubleValue() * (1 - coordinador.getPorcentajeDto()));
	montoTotal = deuda.add(interes);
	bonificacionEspecial = montoTotalPuro.subtract(Format.toDouble(montoTotalPuro.doubleValue() * (1 -  coordinador.getPorcentajeDto())));
	
	tfMontoDeuda.setValue(deuda);
	tfMontoInteres.setValue(interes);
	tfMontoTotalCI.setValue(montoTotal);
	
	if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2)  { // Inmobiliario y TGS
	    centralPanel.setBorder(BorderPanel.getBorderPanel("Config. de " + coordinador.getTipoPlanDePago().getDescripcion() + ", Catastro Nº "+ coordinador.getCatastro().getCatastro() +" ("+ coordinador.getCatastro().getDomape() +")" ));
	} else if (coordinador.getIdTipoImpuesto() == 3)  { //Automotor
	    centralPanel.setBorder(BorderPanel.getBorderPanel("Config. de " + coordinador.getTipoPlanDePago().getDescripcion() + ", Dominio Nº "+ coordinador.getAutomotor().getDominio() +" ("+ coordinador.getAutomotor().getTitular() +")" ));
	} else {
	    centralPanel.setBorder(BorderPanel.getBorderPanel("Config. de " + coordinador.getTipoPlanDePago().getDescripcion() + ", Impuesto no reconocido" ));
	}
	
	tfAnticipoDesde.setValue(coordinador.getAnticipoDesde() +" / "+ coordinador.getAnioDesde());
	tfAnticipoHasta.setValue(coordinador.getAnticipoHasta() +" / "+ coordinador.getAnioHasta());
	tfCantidadAnticipos.setValue(coordinador.getCantPeriodos());
	tfDescuentoAplicado.setValue(coordinador.getDescuento());

	lblCondIntereses.setText("Cond. Int. (" + Format.toDouble(coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()) + " %)");
	condIntereses = Format.toDouble(interes.doubleValue() * (coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()/100.0));
	
	tfPorcentajeCondInt.setValue(condIntereses);

	montoDeuda = deuda.add(interes).subtract(condIntereses);
	tfDeuda.setValue(montoDeuda);
	rbtnContado.setText("Pago Contado (" + Format.toDouble(coordinador.getTipoPlanDePago().getPorcentDtoPagoContado()) + " %)");
	porcentajeContado = Format.toDouble(montoDeuda.multiply(new BigDecimal(String.valueOf(coordinador.getTipoPlanDePago().getPorcentDtoPagoContado()/100.0))).doubleValue());
	
	montoPagoContado = Format.toDouble(montoDeuda.subtract(porcentajeContado).doubleValue());
	tfMontoPagoContado.setValue(montoPagoContado);

	/*double porcentajeInteresFinanciacion = coordinador.getTipoPlanDePago().getPorcentajeInteresCuota() * Integer.parseInt(jcbCuotas.getSelectedItem().toString()) ;
	*/
	lblInteresPorFinanciacion.setText("Int. Fin. (" + Format.toDouble(coordinador.getTipoPlanDePago().getPorcentajeInteresCuota()) + " % x cuota)");
	loadCombo();
	calcularTotalAPagar();
    }
    
    private void loadTipoPlanDepago(){
	tipoPlanDePago = new TipoPlanDePago();
	tipoPlanDePago.setIdBien(coordinador.getIdBien());
	tipoPlanDePago.setIdTipoImpuesto(coordinador.getIdTipoImpuesto());
	tipoPlanDePago.retrieveIdTipoPlanDePago();
	tipoPlanDePago.retrieveData();
    }
    
    public void clearFields() {
	tfMontoDeuda.setValue(0.0);
	tfMontoInteres.setValue(0.0);
	tfMontoTotalCI.setValue(0.0);
	tfDeuda.setValue(0.0);
	tfMontoPagoContado.setValue(0.0);
	tfMontoPagoCuotas.setValue(0.0);
	tfMontoTotal.setValue(0.0);
	tfAnticipoDesde.setValue("");
	tfAnticipoHasta.setValue("");
	tfDescuentoAplicado.setValue("");
	tfCantidadAnticipos.setValue(0);
	rbtnCuotas.setSelected(true);
	jcbCuotas.setEnabled(true);
	jcbCuotas.setSelectedItem(5);
	jcbVtos.setSelectedItem(1);
	jcbVtos.setEnabled(true);
    }

    private void rbtnContado_actionPerformed(ActionEvent e) {
	setTotalAPagar();
	jcbCuotas.setEnabled(false);
	jcbVtos.setEnabled(false);
	lblMontoCuotas.setVisible(false);
    }
    
    private void rbtnCuotas_actionPerformed(ActionEvent e) {
	setTotalAPagar();
	jcbCuotas.setEnabled(true);
	jcbVtos.setEnabled(true);
	lblMontoCuotas.setVisible(true);
    }
    
    private void chkEmpleados_actionPerformed(ActionEvent e) {
	calcularTotalAPagar();
    }
    
    private void chkJubilados_actionPerformed(ActionEvent e) {
	calcularTotalAPagar();
    }
    
    private void chkNinguno_actionPerformed(ActionEvent e) {
	calcularTotalAPagar();
    }


    /*private void calcularTotalAPagar() {
	if (coordinador.getTipoDescuento() == 1 || coordinador.getTipoDescuento() == 2 || coordinador.getTipoDescuento() == 3 || coordinador.getTipoDescuento() == 4) {         
	    // Descuento por Emp. Municipal o por Jubilado y Pens. para TGS e Inmob                                                                                                                                                                                                                                                                                                                                                              
	    tfDeuda.setValue(coordinador.getMonto() - (coordinador.getMonto() * 0.5));
	    tfMontoPagoContado.setValue(tfDeuda.getAmount() - (tfDeuda.getAmount() * 0.3));
	    if(rbtnCuotas.isSelected()){
		tfMontoPagoCuotas.setValue(tfDeuda.getAmount() / (Integer.parseInt(jcbCuotas.getSelectedItem().toString()) * 1.0));     
	    }
	} else if (coordinador.getTipoDescuento() == 5) {
	    // Descuento por Discapacidad para impuesto Automotor 
	    
	} else {
	    // Sin Descuento 
	    tfDeuda.setValue(coordinador.getMonto());
	    tfMontoPagoContado.setValue(coordinador.getMonto() * (1 - coordinador.getPorcentajeDtoContado()));
	    if(rbtnCuotas.isSelected()){
		tfMontoPagoCuotas.setValue(tfDeuda.getAmount() / (Integer.parseInt(jcbCuotas.getSelectedItem().toString()) * 1.0));
	    }
	}
	if (rbtnContado.isSelected()) {
	    if(rbtnCuotas.isSelected()){
		tfMontoPagoCuotas.setValue(tfMontoPagoContado.getAmount());
	    }
	    tfMontoTotal.setValue(tfMontoPagoContado.getAmount());
	} else {
	    tfMontoTotal.setValue(tfDeuda.getAmount());
	}
    }*/

    /* private void calcularTotalAPagar() {
	 double montoDeuda = (tfMontoTotalCI.getAmount()
		     - (tfMontoInteres.getAmount() * coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()/100)
		     );
	 if (coordinador.getTipoDescuento() == 1 || coordinador.getTipoDescuento() == 2 || coordinador.getTipoDescuento() == 3 || coordinador.getTipoDescuento() == 4) {         
	     /** Descuento por Emp. Municipal o por Jubilado y Pens. para TGS e Inmob 
	     //montoDeuda = montoDeuda * 0.5; // linea reemplazada por la linea de abajao
	      montoDeuda = montoDeuda * coordinador.getPorcentajeDto();
	 } else if (coordinador.getTipoDescuento() == 5) {
	     /** Descuento por Discapacidad para impuesto Automotor 
	     montoDeuda = montoDeuda * 1.0;
	 }
	 
	 double montoDeudaContado = montoDeuda - montoDeuda * coordinador.getTipoPlanDePago().getPorcentDtoPagoContado()/100.0;
	 tfMontoPagoContado.setValue(montoDeudaContado);
	 tfMontoPagoCuotas.setValue(montoDeuda / (Integer.parseInt(jcbCuotas.getSelectedItem().toString()) * 1.0));

	 tfDeuda.setValue(montoDeuda);
	 tfMontoTotal.setValue(rbtnContado.isSelected()?montoDeudaContado:montoDeuda);
     }*/

    private void calcularTotalAPagar() {
	/**
	 * 2009-09-18 (cesar)
	 * Descrip.: Calcula el porcentaje de interés a cobrar y el monto de cada cuota (con el incremento del porcentaje de Int. por Financiación )
	 * */
	String textoCuotas = "";
	int cuotas = Integer.parseInt(jcbCuotas.getSelectedItem().toString());
	porcentajeIntFin = (cuotas * coordinador.getTipoPlanDePago().getPorcentajeInteresCuota()/100.0);
	montoIntFin = Format.toDouble(montoDeuda.doubleValue() * porcentajeIntFin);
	
	tfMontoIntFin.setValue(montoIntFin);
	montoPagoCuotas = montoDeuda.add(montoIntFin);

	int cantCuotasEnteras = (cuotas - 1);

	if (cantCuotasEnteras > 0)  {
	    montoCuota =  Format.toDouble(Math.floor(montoPagoCuotas.doubleValue() / cuotas));
	    montoTotalCuotasRestantes = montoCuota.multiply(Format.toDouble(cantCuotasEnteras));
	    montoPrimerCuota =  montoPagoCuotas.subtract(montoTotalCuotasRestantes);
	    
	    if (montoPrimerCuota.doubleValue() > 0)  {
		textoCuotas = "1 cuota de "+ Format.money(montoPrimerCuota.doubleValue()) +" y "+ cantCuotasEnteras +" cuota(s) de "+ Format.money(montoCuota.doubleValue());
	    } else  {
	        textoCuotas = "1 cuota de "+ Format.money(montoCuota.doubleValue()) ;
	    }
	} else  {
	    montoCuota = Format.toDouble(montoPagoCuotas.doubleValue() / cuotas);
	    textoCuotas = "1 cuota de "+ Format.money(montoCuota.doubleValue()) ;
	}
	lblMontoCuotas.setText(textoCuotas);
	if (rbtnCuotas.isSelected()) { 
	    lblMontoCuotas.setVisible(true);
	} else {
	    lblMontoCuotas.setVisible(false);  
	}
	tfMontoPagoCuotas.setValue(montoCuota);
	setTotalAPagar();
    }

    private void setTotalAPagar() {
	/** 2009-09-18 (cesar)	*/
	tfMontoTotal.setValue(rbtnContado.isSelected()?montoPagoContado:montoPagoCuotas);
    }

    private void btnBack_actionPerformed(ActionEvent e) {
	clearFields();
	((TaxesMain)getTabContainer()).setEnabledPanels(1);
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
	if (coordinador.getTipoPlanDePago().isObligatorio()) {
	    if (Advisor.question("Aviso","¿Está seguro de registrar la Moratoria configurada?"))  {
	       setMoratoria();
	       coordinador.getTipoPlanDePago().setMoratoria(moratoria);
	       coordinador.setMoratoria(moratoria);
	       if (coordinador.getMoratoria().saveData()> 0)  {
	            ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setCoordinador(coordinador);
	            ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().refresh();
	            ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setComboCuotas();
	            ((TaxesMain)getTabContainer()).setEnabledPanels(3);
	       } else  {
	            Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
	       }
	    }
	} else {
	    /** 
	     * Plan de pago normal
	     * */
	     if (Advisor.question("Aviso","¿Quiere registrar el Plan de Pago configurado?"))  {
	        setMoratoria();
	        coordinador.getTipoPlanDePago().setMoratoria(moratoria);
	        coordinador.setMoratoria(moratoria);
	        if (coordinador.getMoratoria().saveData()> 0)  {
	             ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setCoordinador(coordinador);
	             ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().refresh();
	             ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setComboCuotas();
	             ((TaxesMain)getTabContainer()).setEnabledPanels(3);
	        } else  {
	             Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
	        }
	     } else {
	         ((TaxesMain)getTabContainer()).getPayFeesMgmt().setCoordinador(coordinador);
	         ((TaxesMain)getTabContainer()).setEnabledPanels(4);
	     }
	}
    }

    private void setMoratoria() {
	moratoria = new Moratoria();
	moratoria.setIdBien(coordinador.getIdBien());
	moratoria.setIdTipoPlanDePago(coordinador.getTipoPlanDePago().getIdTipoPlanDePago());
	moratoria.setIdTipoImpuesto(coordinador.getIdTipoImpuesto());
	/*
	moratoria.setMontoBase(tfMontoDeuda.getAmount());
	moratoria.setInteres(tfMontoInteres.getAmount());
	moratoria.setSubtotal(tfMontoTotalCI.getAmount());
	moratoria.setBonificacionMoratoria(tfMontoInteres.getAmount() * coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()/100);
	*/
	moratoria.setMontoBase(deudaPura.doubleValue());
	moratoria.setInteres(interesPuro.doubleValue());
	moratoria.setSubtotal(montoTotalPuro.doubleValue());
	moratoria.setBonificacionMoratoria(condIntereses.doubleValue());
	
	moratoria.setCantAnticipos(Integer.parseInt(tfCantidadAnticipos.getValue().toString()));
	//moratoria.setBonificacionContado((tfDeuda.getAmount() * coordinador.getTipoPlanDePago().getPorcentDtoPagoContado()/100.0));
	//moratoria.setBonificacionContado(montoPagoContado.doubleValue());
	moratoria.setBonificacionContado(porcentajeContado.doubleValue());
	moratoria.setNombreBonificacionMoratoria("Bonificación de Intereses");
	moratoria.setBonificacion(bonificacionEspecial.doubleValue());
	
	if (coordinador.getTipoDescuento() == 1 || coordinador.getTipoDescuento() == 3)  {
	    //moratoria.setBonificacion(coordinador.getMonto() - (coordinador.getMonto() * 0.5));
	    moratoria.setNombreBonificacion("Empleado Munic. (50 %)");
	} else if (coordinador.getTipoDescuento() == 2 || coordinador.getTipoDescuento() == 4)  {
	    //moratoria.setBonificacion(coordinador.getMonto() - (coordinador.getMonto() * 0.5));
	    moratoria.setNombreBonificacion("Jubilados y Pens. (50 %)");
	} else if (coordinador.getTipoDescuento() == 0)  {
	    //moratoria.setBonificacion(0.0);
	    moratoria.setNombreBonificacion("");
	}else{
	    //moratoria.setBonificacion(0.0);
	    moratoria.setNombreBonificacion("");
	}
	
	moratoria.setIdBonificacion(coordinador.getTipoDescuento());
	
	if (rbtnCuotas.isSelected())  {
	    moratoria.setCantCuotas(Integer.parseInt(jcbCuotas.getSelectedItem().toString()));
	    moratoria.setContado(false);
	} else  {
	    moratoria.setCantCuotas(1);
	    moratoria.setContado(true);
	}
	
	moratoria.setNombre(coordinador.getTipoPlanDePago().getNombre());
	moratoria.setAnticipoDesde(coordinador.getAnticipoDesde());
	moratoria.setAnioDesde(coordinador.getAnioDesde());
	moratoria.setAnticipoHasta(coordinador.getAnticipoHasta());
	moratoria.setAnioHasta(coordinador.getAnioHasta());
	moratoria.setMaxCuotasVencidas(tipoPlanDePago.getMaxCuotasVencidas());
	moratoria.setDiaVencimiento(Integer.parseInt(jcbVtos.getSelectedItem().toString()));
	/*if (moratoria.getCantCuotas() == 1)  {
	    moratoria.setMontoCuotas(tfMontoTotal.getAmount());
	}else{
	    moratoria.setMontoCuotas(tfMontoPagoCuotas.getAmount());            
	}*/
	moratoria.setMontoCuotas(montoCuota.doubleValue());            
	moratoria.setMontoTotal(tfMontoTotal.getAmount());
	if (rbtnContado.isSelected())  {
	    moratoria.setPorcentajeInteresFinanciacion(0.0);
	    moratoria.setMontoInteresFinanciacion(0.0);
	} else  {
	    moratoria.setPorcentajeInteresFinanciacion(porcentajeIntFin);
	    moratoria.setMontoInteresFinanciacion(montoIntFin.doubleValue());
	    moratoria.setPorcentDtoPagoContado(0.0);
	}
	moratoria.setPorcentajeCondonacionIntereses((coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()/100.0));
	moratoria.setMontoCondonacionIntereses(Format.toDouble(interes.doubleValue() * (coordinador.getTipoPlanDePago().getPorcentajeCondonacionIntereses()/100.0)).doubleValue());
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	clearFields();
	((TaxesMain)getTabContainer()).setEnabledPanels(0);
    }
}

