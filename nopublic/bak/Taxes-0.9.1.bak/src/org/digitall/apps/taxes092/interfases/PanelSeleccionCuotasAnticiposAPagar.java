package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PanelSeleccionCuotasAnticiposAPagar extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelTipoImpuesto = new BasicPanel();
    private BasicPanel panelBonificacion = new BasicPanel();
    private BasicCheckBox chkVerAdelantos = new BasicCheckBox();
    private CBInput cbCantidadAnticipos = new CBInput(0, "(#) Cant." , false);
    private CBInput cbCantidadCuotas = new CBInput(0,"(#) Cant.",false);
    private CBInput cbBonificacion = new CBInput(0, "Bonificación", false);
    private BasicTextArea taAyuda = new BasicTextArea();
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Monto",false);
    private TFInput tfInteres = new TFInput(DataTypes.MONEY,"Interés",false);
    private TFInput tfBonificacion = new TFInput(DataTypes.MONEY,"Bonificación",false);
    private TFInput tfTotalCuotas = new TFInput(DataTypes.MONEY,"($) Total Cuotas",false);
    private TFInput tfTotalAnticipos = new TFInput(DataTypes.MONEY,"($) Total Anticipos",false);
    private JMoneyEntry tfTotal = new JMoneyEntry();
    private TFInput tfEntrega = new TFInput(DataTypes.MONEY,"Entrega",false);
    private TFInput tfParcial = new TFInput(DataTypes.PERCENT,"Parcial",false);
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblTotal = new BasicLabel();

    private int[] sizeColumnList = { 41, 87, 95};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel grillaCuotas = new GridPanel(1000, sizeColumnList, "Cuotas", dataRow) {
	public void finishLoading() {
	    calcAmountToPay();
	}
    };
    
    private int[] sizeColumnList2 = { 23 , 38 , 85 , 88 , 52 , 58 , 67 , 57 , 70 , 84 , 73 , 74 , 74};
    private Vector dataRow2 = new Vector();
    private Vector headerList2 = new Vector();
    private GridPanel grillaAnticiposRegulares = new GridPanel(50000, sizeColumnList2, "Anticipos Adeudados", dataRow2) {

	    public void finishLoading() {
		loadComboBonificacion(coordinador.getImpuesto().getTipoDeImpuesto());
		setComboAnticipos();
	    }
	}
    ;
    private JSeparator jSeparator2 = new JSeparator();
    private BasicRadioButton rbtnTgs = new BasicRadioButton();
    private BasicRadioButton rbtnInmob = new BasicRadioButton();
    private BasicRadioButton rbtnAutomotor = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    private Coordinador coordinador;


    public PanelSeleccionCuotasAnticiposAPagar(Coordinador _coordinador) {
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
        lblTitulo.setText("<html><center>Selección de <br> Cuotas y Anticipos a Pagar</center><html>");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 30));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
        lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 40, 205, 430));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	grillaCuotas.setBounds(new Rectangle(5, 75, 270, 265));
	grillaCuotas.setBorder(BorderPanel.getBorderPanel("Cuotas P.P. Nº xxxx"));
	chkVerAdelantos.setText("Ver Adelantos");
	chkVerAdelantos.setBounds(new Rectangle(285, 340, 115, 15));
	cbCantidadAnticipos.setBounds(new Rectangle(290, 359, 80, 35));
	tfTotalAnticipos.setBounds(new Rectangle(505, 359, 110, 35));
	cbCantidadAnticipos.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent evt){
			if(evt.getStateChange() == ItemEvent.SELECTED){
			    tfEntrega.setValue(0.0);    
			    tfParcial.setValue(0.0);
			    calcTotalAmount();
			}
		}
	});
	tfMonto.setBounds(new Rectangle(290, 400, 85, 35));
	tfMonto.setEditable(false);
	tfInteres.setBounds(new Rectangle(415, 400, 85, 35));
	tfInteres.setEditable(false);
	tfBonificacion.setBounds(new Rectangle(530, 400, 85, 35));
	tfBonificacion.setEditable(false);
	tfTotal.setBounds(new Rectangle(505, 445, 110, 25));
	tfTotal.setEditable(false);
	tfTotal.setFont(new Font("Dialog", 1, 12));
	cbCantidadCuotas.setBounds(new Rectangle(10, 359, 70, 35));
	tfTotalCuotas.setBounds(new Rectangle(160, 359, 110, 35));
        tfTotalCuotas.setEditable(false);
        tfTotalAnticipos.setEditable(false);
	cbCantidadCuotas.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			calcAmountToPay();
		    }
		}

	    }
	);
	grillaAnticiposRegulares.setBorder(BorderPanel.getBorderPanel("Anticipos Regulares"));
	grillaAnticiposRegulares.setBounds(new Rectangle(285, 75, 335, 265));
	tfEntrega.setBounds(new Rectangle(380, 365, 95, 35));
	tfParcial.setBounds(new Rectangle(575, 365, 45, 35));
	tfParcial.setText("50 %");
	jSeparator2.setBounds(new Rectangle(5, 440, 620, 2));
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
	rbtnTgs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    rbtn_actionPerformed(e);
					}
				    }
	);
	rbtnInmob.setText("Inmobiliario");
	rbtnInmob.setBounds(new Rectangle(120, 30, 130, 20));
	rbtnInmob.setSize(new Dimension(130, 20));
	rbtnInmob.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    rbtn_actionPerformed(e);
					}
				    }
	);
	rbtnAutomotor.setText("Automotor");
	rbtnAutomotor.setBounds(new Rectangle(270, 30, 110, 20));
	rbtnAutomotor.setSize(new Dimension(110, 20));
	rbtnAutomotor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    rbtn_actionPerformed(e);
					}
				    }
	);
	lblTotal.setText("Total: ");
	lblTotal.setBounds(new Rectangle(385, 445, 110, 25));
	lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	cbBonificacion.setBounds(new Rectangle(10, 20, 190, 30));
        cbBonificacion.setSize(new Dimension(190, 35));
	cbBonificacion.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    cbBonificacion_itemStateChanged(e);
		}
	    }
	);
	cbBonificacion.setSelectedID(0);
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelBonificacion.add(cbBonificacion, null);
	panelCentral.add(lblTotal, null);
	panelCentral.add(tfTotalAnticipos, null);
	panelCentral.add(tfTotalCuotas, null);
	panelCentral.add(panelBonificacion, null);
	panelTipoImpuesto.add(rbtnAutomotor, null);
	panelTipoImpuesto.add(rbtnInmob, null);
	panelTipoImpuesto.add(rbtnTgs, null);
	panelCentral.add(panelTipoImpuesto, null);
	panelCentral.add(jSeparator2, null);
	panelCentral.add(tfParcial, null);
	panelCentral.add(tfEntrega, null);
	panelCentral.add(grillaAnticiposRegulares, null);
	panelCentral.add(cbCantidadCuotas, null);
	panelCentral.add(tfTotal, null);
	panelCentral.add(tfBonificacion, null);
	panelCentral.add(tfInteres, null);
	panelCentral.add(tfMonto, null);
	panelCentral.add(cbCantidadAnticipos, null);
	panelCentral.add(chkVerAdelantos, null);
	panelCentral.add(grillaCuotas, null);
	setHeaderList2();
	this.add(panelCentral, null);
	this.add(panelAyuda, null);
	taAyuda.setText("Ayuda");
	taAyuda.setEditable(false);
	grupo.add(rbtnTgs);
	grupo.add(rbtnInmob);
	grupo.add(rbtnAutomotor);
	rbtnTgs.setSelected(true);
	setHeaderList();
	chkVerAdelantos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e){
			refresh();
		    }
	}
	);
	chkVerAdelantos.setSelected(false);
	grillaCuotas.setSortable(false);
	grillaAnticiposRegulares.setSortable(false);
	grillaCuotas.getTable().setEnabled(false);
	grillaAnticiposRegulares.getTable().setEnabled(false);
	loadComboBonificacion(0);
	tfEntrega.setVisible(false);
	tfParcial.setVisible(false);
        taAyuda.setEditable(false);
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.removeAllElements();
	headerList.addElement("Nº");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Fecha Vto");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("$ Monto");
	headerList.addElement("*");
	headerList.addElement("*");
	
	grillaCuotas.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {

						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							//checkRows();
						     }
						 }

					     }
	);
	grillaCuotas.setParams("taxes.getCuotasPlanDePago", "-1", headerList);
    }
    
    private void setHeaderList2() {
	headerList2.removeAllElements();
	headerList2.removeAllElements();
	headerList2.addElement("*");
	headerList2.addElement("Nº");
	headerList2.addElement("Año");
	headerList2.addElement("Fecha");
	headerList2.addElement("Vence");
	headerList2.addElement("Valor");
	headerList2.addElement("% Mora");
	headerList2.addElement("$ Mora");
	headerList2.addElement("% Desc.");
	headerList2.addElement("$ Desc.");
	headerList2.addElement("Fº Act.");
	headerList2.addElement("$ SubTotal");
	headerList2.addElement("% Pagado");
	headerList2.addElement("$ Total");
	headerList2.addElement("*");
	headerList2.addElement("*");
	
	grillaAnticiposRegulares.getTable().addMouseListener(new MouseListener() {

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
	grillaAnticiposRegulares.getTable().addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
			    e.consume();
			    //checkRows();
			}
			
			public void keyTyped(KeyEvent e) {
			    e.consume();
			    //checkRows();
			}
			
			public void keyPressed(KeyEvent e) {
			    e.consume();
			    //checkRows();
			}

		});
	grillaAnticiposRegulares.setParams("taxes.getcuotas", "-1,-1,false", headerList2);
	chkVerAdelantos.setSelected(false);
    }
    
    public void refresh() {
	cbCantidadAnticipos.setEnabled(false);
	String params = ""+ coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() +","+ coordinador.getBien().getIdBien() +","+ chkVerAdelantos.isSelected();
	grillaAnticiposRegulares.refresh(params);
	clearFields();
    }
    
    public void clearFields() {
	tfEntrega.setValue(0.0);
	tfInteres.setValue(0.0);
	tfMonto.setValue(0.0);
	tfParcial.setValue(0.0);
	tfTotal.setValue(0.0);
        tfBonificacion.setValue(0.0);
        tfTotalAnticipos.setValue(0.0);
    }

    public void iniciarPanel(){
	coordinador.setOpcion(1);
	if (coordinador.tipoPlanDePagoSeleccionado == coordinador.TIPO_PLAN_DE_PAGO_TGS) {
	    rbtnTgs.setEnabled(true);
	    rbtnInmob.setEnabled(true);
	    rbtnTgs.setSelected(true);
	    rbtnAutomotor.setEnabled(false);
	}else{
	    if (coordinador.tipoPlanDePagoSeleccionado == coordinador.TIPO_PLAN_DE_PAGO_INMOB) {
	        rbtnTgs.setEnabled(true);
	        rbtnInmob.setEnabled(true);
	        rbtnInmob.setSelected(true);
	        rbtnAutomotor.setEnabled(false);
	    }else{
	        rbtnAutomotor.setSelected(true);
	        rbtnAutomotor.setEnabled(true);
	        rbtnInmob.setEnabled(false);
	        rbtnTgs.setEnabled(false);
	    }
	}
	loadComboBonificacion(coordinador.getImpuesto().getTipoDeImpuesto());
	cargarSeccionPlanPago();
	cargarSeccionAnticiposRegulares();
    }
    
    public void setComboCuotas() {
	int cantCuotas = LibSQL.getInt("taxes.getCantCuotasAPagar",""+ coordinador.getPagoCuotasPlanPago().getPlanDePago().getIdplandepago());
	cbCantidadCuotas.removeAllItems();
	cbCantidadCuotas.getCombo().addItem(0);
	if (cantCuotas > 0)  {
	    for (int i = 1 ; i <= cantCuotas ; i++ )  {
		cbCantidadCuotas.getCombo().addItem(i);
	    }    
	} 
	calcAmountToPay();
	if (coordinador.getPagoCuotasPlanPago().getPlanDePago().isPlandepagocaido())  {
	    //lblMoratoriaCaida.setVisible(true);
	    //btnSaveData.setEnabled(false);
	    cbCantidadCuotas.setEnabled(false);
	} else{
	    //lblMoratoriaCaida.setVisible(false);
	    if ( Integer.parseInt(cbCantidadCuotas.getSelectedItem().toString()) > 0) {
		//btnSaveData.setEnabled(true);
		//cbCantCuotas.setEnabled(true);
	    } else {
		//btnSaveData.setEnabled(false);
		//cbCantCuotas.setEnabled(false);
	    }
	}
    }
    
    private void calcularTotal(){
	BigDecimal total = new BigDecimal("0");
	total = total.add(new BigDecimal(tfTotalCuotas.getValue().toString()));
	total = total.add(new BigDecimal(tfTotalAnticipos.getValue().toString()));
	tfTotal.setValue(total);
    }
    
    private void cargarSeccionPlanPago(){
	//Setear el titulo de la grilla
	 grillaCuotas.setBorder(BorderPanel.getBorderPanel("Cuotas P.P. Nº: " + coordinador.getPlanDePagoActual().getIdplandepago()));
	//cargar la grilla con las cuotas del plan de pago correspondiente
	grillaCuotas.refresh("" + coordinador.getPagoCuotasPlanPago().getPlanDePago().getIdplandepago());
	//cargar el combo con la cantidad de cuotas que puede pagar el contribuyente
	setComboCuotas();
    }
    
    private void cargarSeccionAnticiposRegulares(){
	//cargar la grilla con los anticipos adeudados por el bien en cuestion
	refresh();
	//cargar el combo con la cantidad de anticipos que puede pagar el contribuyente
	setComboAnticipos();
    }
    
    public void setComboAnticipos() {
	String params = coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() + "," + coordinador.getBien().getIdBien() + "," + chkVerAdelantos.isSelected();
	int cantAnticipos = LibSQL.getInt("taxes.getCantAnticiposAPagar", "" + params);
	cbCantidadAnticipos.removeAllItems();
	cbCantidadAnticipos.getCombo().addItem(0);
	if (cantAnticipos > 0) {
	    for (int i = 1; i <= cantAnticipos; i++) {
		cbCantidadAnticipos.getCombo().addItem(i);
	    }
	} 
	calcTotalAmount();
	cbCantidadAnticipos.setEnabled(true);
    }
    
    private void calcTotalAmount(){
	if(grillaAnticiposRegulares.getTable().getRowCount() > 0){
	    if (tfEntrega.getAmount() == 0.0) {
		calcularTotalSinEntrega();
	    } else{
                //Por ahora lo mismo
		calcularTotalSinEntrega();
	    }
	    
	    if(!cbCantidadAnticipos.getSelectedItem().toString().equals("0")){
		coordinador.getPagoAnticipos().setBonificacion(Integer.parseInt(cbBonificacion.getSelectedValue().toString()));
		coordinador.getPagoAnticipos().setMonto(tfMonto.getAmount());
		coordinador.getPagoAnticipos().setInteres(tfInteres.getAmount());
		coordinador.getPagoAnticipos().setDescuento(tfBonificacion.getAmount());
		coordinador.getPagoAnticipos().setMontoTotal(tfTotalAnticipos.getAmount());
		if(Double.parseDouble(cbBonificacion.getSelectedValueRef().toString()) == 0){
		    coordinador.getPagoAnticipos().setTotalAnticiposAdeudados(tfTotalAnticipos.getAmount());// Cambiar para usar con bigdecimal    
		}else{
		    coordinador.getPagoAnticipos().setTotalAnticiposAdeudados((tfTotalAnticipos.getAmount()  * 100) / (100 * Double.parseDouble(cbBonificacion.getSelectedValueRef().toString())));// Cambiar para usar con bigdecimal    
		}
		int periodoDesde = Integer.parseInt(grillaAnticiposRegulares.getTable().getModel().getValueAt(0,2).toString());
		int periodoHasta = Integer.parseInt(grillaAnticiposRegulares.getTable().getModel().getValueAt(Integer.parseInt(cbCantidadAnticipos.getSelectedItem().toString()) - 1,2).toString());
		int anioDesde = Integer.parseInt(grillaAnticiposRegulares.getTable().getModel().getValueAt(0,3).toString());
		int anioHasta = Integer.parseInt(grillaAnticiposRegulares.getTable().getModel().getValueAt(Integer.parseInt(cbCantidadAnticipos.getSelectedItem().toString()) - 1,3).toString());
		int cantidadPeriodos = Integer.parseInt(cbCantidadAnticipos.getSelectedItem().toString());
		coordinador.getPagoAnticipos().setCantidadAnticipos(cantidadPeriodos);
		coordinador.getPagoAnticipos().setPeriodoDesde(periodoDesde);
		coordinador.getPagoAnticipos().setPeriodoHasta(periodoHasta);
		coordinador.getPagoAnticipos().setAnioDesde(anioDesde);
		coordinador.getPagoAnticipos().setAnioHasta(anioHasta);
	    } else{
		coordinador.getPagoAnticipos().clear();
	    }
	}else{
	    coordinador.getPagoAnticipos().clear();
	}
    }
    
    private void calcularTotalSinEntrega(){
	if (cbCantidadAnticipos.getSelectedIndex() > 0) {
	    /*
            int cantidadAnticiposAPagar = Integer.parseInt(cbCantidadAnticipos.getSelectedItem().toString());
	    BigDecimal totalIntereses = new BigDecimal("0");
	    BigDecimal totalValor = new BigDecimal("0");
	    BigDecimal totalDescuento = new BigDecimal("0");
	    BigDecimal subTotalDeuda = new BigDecimal("0");
	    BigDecimal porcentajeDescuento = new BigDecimal("0");
	    grillaAnticiposRegulares.selectAllItems(true);
	    if (grillaAnticiposRegulares.getTable().getRowCount() > 0) {
		Vector valorCuota = grillaAnticiposRegulares.getValuesInTableAt("Valor");
		Vector valorIntereses = grillaAnticiposRegulares.getValuesInTableAt("$ Mora");
		Vector porcentajePagado = grillaAnticiposRegulares.getValuesInTableAt("% Pagado");
		Vector valorTotal = grillaAnticiposRegulares.getValuesInTableAt("$ Total");
		for (int i = 0; i < cantidadAnticiposAPagar; i++) {
		    totalValor = totalValor.add(Format.toDouble(new Double(valorCuota.elementAt(i).toString()) * (1 - new Double((porcentajePagado.elementAt(i).toString())) / 100)));
		    totalIntereses = totalIntereses.add(Format.toDouble(new Double(valorIntereses.elementAt(i).toString()) * (1 - new Double((porcentajePagado.elementAt(i).toString())) / 100)));
		    subTotalDeuda = subTotalDeuda.add(new BigDecimal(valorTotal.elementAt(i).toString()));
		}
	    }

	    tfMonto.setValue(totalValor);
	    tfInteres.setValue(totalIntereses);
	    porcentajeDescuento = porcentajeDescuento.add(Format.toDouble(cbBonificacion.getSelectedValueRef().toString()));
	    totalDescuento = Format.toDouble(totalValor.add(totalIntereses).doubleValue() * porcentajeDescuento.doubleValue());
	    tfBonificacion.setValue(totalDescuento);
	    tfTotalAnticipos.setValue(subTotalDeuda.subtract(totalDescuento));
	    calcularTotal();
            */
	    int cantidadAnticiposAPagar = 0;
	    if (grillaAnticiposRegulares.hasItems()) {
	        cantidadAnticiposAPagar = grillaAnticiposRegulares.getItemCount();
	    }
	    
	    String params =     coordinador.getImpuesto().getTipoImpuesto().getIdTipoImpuesto() + "," 
	                        + coordinador.getBien().getIdBien() + "," 
	                        + cbCantidadAnticipos.getSelectedItem() + ","
	                        + Double.parseDouble(cbBonificacion.getSelectedValueRef().toString());
	    ResultSet rs = LibSQL.exFunction("taxes.getMontosDeuda",params);
	    try {
	        while(rs.next()){
	            tfMonto.setValue(rs.getDouble("montoPuro"));
	            tfInteres.setValue(rs.getDouble("interesPuro"));
	            tfTotalAnticipos.setValue(rs.getDouble("totalDescontado"));
	            tfBonificacion.setValue(rs.getDouble("Descuento"));
	        }   
	    } catch (SQLException e) {
	        tfMonto.setValue(0.00);
	        tfInteres.setValue(0.00);
	        tfTotal.setValue(0.00);
	        tfBonificacion.setValue(0.00);
	    }
	    calcularTotal();
	}else{
	    clearFields();
	}
    }
    
    private void calcularTotalConEntrega(){
	calcularTotal();
    }
    
    private void calcAmountToPay(){
	if (grillaCuotas.getValuesInTableAt(0).size() > 0)  {
	    int _cantCuotas = Integer.parseInt(cbCantidadCuotas.getSelectedItem().toString());
	    BigDecimal totalAPagar = new BigDecimal("0.0");
	    Vector _aux = grillaCuotas.getValuesInTableAt(3);
	    Vector _fechasDeVto = grillaCuotas.getValuesInTableAt(2);
	    /*
	     for (int i = 0; i < _cantCuotas; i++)  {
		totalAPagar = totalAPagar.add(new BigDecimal(_aux.elementAt(i).toString()));
	    }
	    */
	    int inicio = 0;
	    int fin = grillaCuotas.getTable().getRowCount() - 1;
            int i = 0;
	    int visadas = 0;
	    while (visadas < _cantCuotas) {
		if (Proced.compareDates(Proced.setFormatDate(_fechasDeVto.elementAt(i).toString(), false),Environment.currentDate) == 2) {
                    totalAPagar = totalAPagar.add(new BigDecimal(_aux.elementAt(fin).toString()));
                    fin--;
                } else {
                    totalAPagar = totalAPagar.add(new BigDecimal(_aux.elementAt(inicio).toString()));
                    inicio++;
                }
	        visadas++;
		i++;
	    }
	    tfTotalCuotas.setValue(totalAPagar); 
	    calcularTotal();
	    coordinador.getPagoCuotasPlanPago().setMonto(totalAPagar.doubleValue());
	    coordinador.getPagoCuotasPlanPago().setImporte(totalAPagar.doubleValue());
	    coordinador.getPagoCuotasPlanPago().setDeduccion(0.0);
	    coordinador.getPagoCuotasPlanPago().setRecargoPorMora(0.0);
	    coordinador.getPagoCuotasPlanPago().setCantidadCuotas(_cantCuotas);
	} else  {
	    clearFields();
	}
    }
    
    private void cbBonificacion_itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    calcTotalAmount();
	}
    }
    
    private void loadComboBonificacion(int _idTipoImpuesto){
	cbBonificacion.loadJCombo(LibSQL.exFunction("taxes.getAllBonificaciones","" + _idTipoImpuesto));
	cbBonificacion.setSelectedID(0);
    }
    
    private void rbtn_actionPerformed(ActionEvent e) {
	//Setear la opcion al coordinador
	//Decirle al coordinador que avance
	coordinador.setPaso(1);
	coordinador.setOpcion(0);
	coordinador.setSubOpcion(0);
	if(rbtnInmob.isSelected()){
	    coordinador.setVerPlanDePagoInmob(true);
	    coordinador.setVerPlanDePagoTgs(false);
	}else{
	    if(rbtnTgs.isSelected()){
		coordinador.setVerPlanDePagoTgs(true);
	        coordinador.setVerPlanDePagoInmob(false);
	    }
	}
	coordinador.siguiente();
    }
}
