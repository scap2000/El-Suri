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
 * PanelRegistrarBoletaCuotasAnticipos.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
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

public class PanelRegistrarBoletaCuotasAnticipos extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelPeriodos = new BasicPanel();
    private BasicTextArea taAyuda = new BasicTextArea();

    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblInteres = new BasicLabel();
    private BasicLabel lblCondonacionIntereses = new BasicLabel();
    private BasicLabel lblCapital = new BasicLabel();
    private BasicLabel lblTotalAPagar = new BasicLabel();
    private BasicLabel lblTotalPeriodosAdeudados = new BasicLabel();
    private BasicLabel lblCantidadCuotas = new BasicLabel();
    private BasicLabel lblInteresFinanciacion = new BasicLabel();
    private BasicLabel lblTotalAFinanciar = new BasicLabel();
    private BasicLabel lblCondonacionIntereses1 = new BasicLabel();
    private BasicLabel lblTotalAFinanciar1 = new BasicLabel();
    private BasicLabel lblInteresFinanciacion1 = new BasicLabel();
    
    private TFInput tfPlanDePagoNro = new TFInput(DataTypes.STRING,"Plan de Pago Nº", false);
    private TFInput tfBonificacionAplicada = new TFInput(DataTypes.STRING,"Bonificación Aplicada", false);;
    private TFInput tfPorcentajeBonif = new TFInput(DataTypes.PERCENT,"% Bonif.", false);;
    private JMoneyEntry tfCapital = new JMoneyEntry();
    private JMoneyEntry tfInteres = new JMoneyEntry();
    private JMoneyEntry tfTotalAPagar = new JMoneyEntry();
    private JMoneyEntry tfSubtotal2 = new JMoneyEntry();
    private JMoneyEntry tfTotalPeriodosAdeudados = new JMoneyEntry();
    private JMoneyEntry tfInteresCuotas = new JMoneyEntry();
    private JMoneyEntry tfCapitalCuotas = new JMoneyEntry();
    private JTextField tfCantCuotasAPagar = new JTextField();
    private JMoneyEntry tfSubTotalAPagar = new JMoneyEntry();
    private JMoneyEntry tfTotalAnticipos = new JMoneyEntry();
    private JMoneyEntry tfTotalCuotas = new JMoneyEntry();

    private JTextField tfCantPeriodosAPagar = new JTextField();
    private JMoneyEntry tfSubtotal1 = new JMoneyEntry();
    private JMoneyEntry tfCondonacionIntereses1 = new JMoneyEntry();

    private JSeparator jSeparator1 = new JSeparator();
    private BasicButton btnRegistrarBoleta = new BasicButton();
    private JSeparator jSeparator2 = new JSeparator();
    private Coordinador coordinador;

    public PanelRegistrarBoletaCuotasAnticipos(Coordinador _coordinador) {
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
	lblTitulo.setText("Registrar Boletas a Pagar");
        lblTitulo.setBounds(new Rectangle(5, 5, 205, 25));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
        lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 35, 205, 435));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setBounds(new Rectangle(5, 5, 620, 50));
	panelPeriodos.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setLayout(null);
	lblInteres.setText("Interés:");
	lblInteres.setBounds(new Rectangle(5, 130, 80, 30));
	lblInteres.setFont(new Font("Dialog", 0, 18));
	lblCondonacionIntereses.setText("Cant. Cuotas a Pagar");
	lblCondonacionIntereses.setBounds(new Rectangle(5, 275, 305, 30));
	lblCondonacionIntereses.setFont(new Font("Dialog", 1, 18));
	tfPlanDePagoNro.setBounds(new Rectangle(10, 5, 105, 35));
	tfPlanDePagoNro.setEditable(false);
	tfPlanDePagoNro.setHorizontalAlignment(TFInput.CENTER);
	tfBonificacionAplicada.setBounds(new Rectangle(135, 5, 360, 35));
	tfBonificacionAplicada.setEditable(false);
	tfBonificacionAplicada.setHorizontalAlignment(TFInput.CENTER);
	tfPorcentajeBonif.setBounds(new Rectangle(515, 5, 100, 35));
	tfPorcentajeBonif.setEditable(false);
	tfPorcentajeBonif.setHorizontalAlignment(TFInput.CENTER);
	lblCapital.setText("Capital:");
	lblCapital.setBounds(new Rectangle(5, 95, 80, 30));
	lblCapital.setFont(new Font("Dialog", 0, 18));
	tfCapital.setBounds(new Rectangle(320, 95, 140, 30));
	tfCapital.setEditable(false);
	tfCapital.setFont(new Font("Dialog", 0, 20));
	tfCapital.setHorizontalAlignment(JTextField.RIGHT);
	tfCapital.setForeground(new Color(82, 132, 0));
	tfInteres.setBounds(new Rectangle(320, 130, 140, 30));
	tfInteres.setEditable(false);
	tfInteres.setFont(new Font("Dialog", 0, 20));
	tfInteres.setHorizontalAlignment(JTextField.RIGHT);
	tfInteres.setForeground(new Color(82, 132, 0));
	tfTotalAPagar.setBounds(new Rectangle(365, 425, 255, 45));
	tfTotalAPagar.setEditable(false);
	tfTotalAPagar.setFont(new Font("Dialog", 1, 30));
	tfTotalAPagar.setHorizontalAlignment(JTextField.RIGHT);
	tfTotalAPagar.setForeground(Color.red);
	tfSubtotal2.setBounds(new Rectangle(480, 275, 140, 30));
	tfSubtotal2.setEditable(false);
	tfSubtotal2.setFont(new Font("Dialog", 0, 20));
	tfSubtotal2.setHorizontalAlignment(JTextField.RIGHT);
	tfSubtotal2.setForeground(new Color(82, 132, 0));
	lblTotalAPagar.setText("Total a Pagar:");
	lblTotalAPagar.setBounds(new Rectangle(180, 425, 185, 45));
	lblTotalAPagar.setFont(new Font("Dialog", 1, 20));
	tfTotalPeriodosAdeudados.setBounds(new Rectangle(480, 60, 140, 30));
	tfTotalPeriodosAdeudados.setEditable(false);
	tfTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 21));
	tfTotalPeriodosAdeudados.setHorizontalAlignment(JTextField.RIGHT);
	tfTotalPeriodosAdeudados.setForeground(new Color(0, 165, 0));
	tfInteresCuotas.setBounds(new Rectangle(320, 345, 140, 30));
	tfInteresCuotas.setEditable(false);
	tfInteresCuotas.setFont(new Font("Dialog", 0, 20));
	tfInteresCuotas.setHorizontalAlignment(JTextField.RIGHT);
	tfInteresCuotas.setForeground(new Color(82, 132, 0));
	lblTotalPeriodosAdeudados.setText("Cant. Períodos a Pagar");
	lblTotalPeriodosAdeudados.setBounds(new Rectangle(5, 60, 290, 30));
	lblTotalPeriodosAdeudados.setFont(new Font("Dialog", 1, 18));
	tfCapitalCuotas.setBounds(new Rectangle(320, 310, 140, 30));
	tfCapitalCuotas.setEditable(false);
	tfCapitalCuotas.setFont(new Font("Dialog", 0, 20));
	tfCapitalCuotas.setHorizontalAlignment(JTextField.RIGHT);
	tfCapitalCuotas.setForeground(new Color(82, 132, 0));
	lblCantidadCuotas.setText("Capital");
	lblCantidadCuotas.setBounds(new Rectangle(5, 310, 305, 30));
	lblCantidadCuotas.setFont(new Font("Dialog", 0, 18));
	lblInteresFinanciacion.setText("Interés");
	lblInteresFinanciacion.setBounds(new Rectangle(5, 345, 300, 30));
	lblInteresFinanciacion.setFont(new Font("Dialog", 0, 18));
	jSeparator1.setBounds(new Rectangle(10, 270, 615, 5));
	tfCantCuotasAPagar.setBounds(new Rectangle(320, 275, 70, 30));
	tfCantCuotasAPagar.setEditable(false);
	tfCantCuotasAPagar.setFont(new Font("Dialog", 0, 20));
	tfCantCuotasAPagar.setHorizontalAlignment(JTextField.CENTER);
	tfCantCuotasAPagar.setText("0");
	tfCantCuotasAPagar.setForeground(new Color(82, 132, 0));
	tfSubTotalAPagar.setBounds(new Rectangle(480, 165, 140, 30));
	tfSubTotalAPagar.setEditable(false);
	tfSubTotalAPagar.setFont(new Font("Dialog", 1, 21));
	tfSubTotalAPagar.setHorizontalAlignment(JTextField.RIGHT);
	tfSubTotalAPagar.setForeground(Color.red);
	tfSubTotalAPagar.setToolTipText("null");
	lblTotalAFinanciar.setText("SubTotal a Pagar");
	lblTotalAFinanciar.setBounds(new Rectangle(5, 165, 200, 30));
	lblTotalAFinanciar.setFont(new Font("Dialog", 1, 18));
	btnRegistrarBoleta.setText("Registrar");
	btnRegistrarBoleta.setOpaque(true);
	btnRegistrarBoleta.setFont(new Font("Dialog", 1, 10));
	btnRegistrarBoleta.setForeground(Color.black);
	btnRegistrarBoleta.setBackground(new Color(165, 165, 0));
	btnRegistrarBoleta.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnRegistrarBoleta.setBounds(new Rectangle(10, 435, 70, 30));
	btnRegistrarBoleta.setToolTipText("Registrar Pago");
	btnRegistrarBoleta.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGenerar_actionPerformed(e);
		}
	    }
	);
	tfCantPeriodosAPagar.setBounds(new Rectangle(320, 60, 70, 30));
	tfCantPeriodosAPagar.setEditable(false);
	tfCantPeriodosAPagar.setText("0");
	tfCantPeriodosAPagar.setFont(new Font("Dialog", 1, 20));
	tfCantPeriodosAPagar.setForeground(new Color(82, 132, 0));
	tfCantPeriodosAPagar.setHorizontalAlignment(JTextField.CENTER);
	tfSubtotal1.setBounds(new Rectangle(480, 200, 140, 30));
	tfSubtotal1.setEditable(false);
	tfSubtotal1.setFont(new Font("Dialog", 0, 20));
	tfSubtotal1.setHorizontalAlignment(JTextField.RIGHT);
	tfSubtotal1.setForeground(new Color(82, 132, 0));
	tfCondonacionIntereses1.setBounds(new Rectangle(320, 200, 140, 30));
	tfCondonacionIntereses1.setEditable(false);
	tfCondonacionIntereses1.setFont(new Font("Dialog", 0, 20));
	tfCondonacionIntereses1.setHorizontalAlignment(JTextField.RIGHT);
	tfCondonacionIntereses1.setForeground(new Color(82, 132, 0));
	lblCondonacionIntereses1.setText("Bonificación Aplicada");
	lblCondonacionIntereses1.setBounds(new Rectangle(5, 200, 305, 30));
	lblCondonacionIntereses1.setFont(new Font("Dialog", 0, 18));
	tfTotalAnticipos.setBounds(new Rectangle(480, 235, 140, 30));
	tfTotalAnticipos.setEditable(false);
	tfTotalAnticipos.setFont(new Font("Dialog", 1, 21));
	tfTotalAnticipos.setHorizontalAlignment(JTextField.RIGHT);
	tfTotalAnticipos.setForeground(Color.red);
	lblTotalAFinanciar1.setText("Total Anticipos a Pagar");
	lblTotalAFinanciar1.setBounds(new Rectangle(5, 235, 290, 30));
	lblTotalAFinanciar1.setFont(new Font("Dialog", 1, 18));
	jSeparator2.setBounds(new Rectangle(5, 415, 625, 5));
	tfTotalCuotas.setBounds(new Rectangle(480, 380, 140, 30));
	tfTotalCuotas.setEditable(false);
	tfTotalCuotas.setFont(new Font("Dialog", 1, 20));
	tfTotalCuotas.setHorizontalAlignment(JTextField.RIGHT);
	tfTotalCuotas.setForeground(Color.red);
	lblInteresFinanciacion1.setText("Total cuotas a Pagar");
	lblInteresFinanciacion1.setBounds(new Rectangle(5, 380, 300, 30));
	lblInteresFinanciacion1.setFont(new Font("Dialog", 1, 18));
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelCentral.add(lblInteresFinanciacion1, null);
	panelCentral.add(tfTotalCuotas, null);
	panelCentral.add(jSeparator2, null);
	panelCentral.add(lblTotalAFinanciar1, null);
	panelCentral.add(tfTotalAnticipos, null);
	panelCentral.add(lblCondonacionIntereses1, null);
	panelCentral.add(tfCondonacionIntereses1, null);
	panelCentral.add(tfSubtotal1, null);
	panelCentral.add(tfCantPeriodosAPagar, null);
	panelCentral.add(btnRegistrarBoleta, null);
	panelCentral.add(lblTotalAFinanciar, null);
	panelCentral.add(tfSubTotalAPagar, null);
	panelCentral.add(tfCantCuotasAPagar, null);
	panelCentral.add(jSeparator1, null);
	panelCentral.add(lblInteresFinanciacion, null);
	panelCentral.add(lblCantidadCuotas, null);
	panelCentral.add(tfCapitalCuotas, null);
	panelCentral.add(lblTotalPeriodosAdeudados, null);
	panelCentral.add(tfInteresCuotas, null);
	panelCentral.add(tfTotalPeriodosAdeudados, null);
	panelCentral.add(lblTotalAPagar, null);
	panelCentral.add(tfSubtotal2, null);
	panelCentral.add(tfTotalAPagar, null);
	panelCentral.add(tfInteres, null);
	panelCentral.add(tfCapital, null);
	panelCentral.add(lblCapital, null);
	panelCentral.add(lblCondonacionIntereses, null);
	panelCentral.add(lblInteres, null);
	panelCentral.add(panelPeriodos, null);
	panelPeriodos.add(tfBonificacionAplicada, null);
	panelPeriodos.add(tfPorcentajeBonif, null);
        panelPeriodos.add(tfPlanDePagoNro, null);
        this.add(panelCentral, null);
	this.add(panelAyuda, null);
	taAyuda.setText("Ayuda");	
	taAyuda.setEditable(false);
    }

    
    private void btnGenerar_actionPerformed(ActionEvent e) {
	registrarBoletas();
    }
    
    public void registrarBoletas(){
	boolean exito = true;
	String pregunta = "<html>¿Está seguro de generar la/s boleta/s?<br><ul>";
	int cantAnticipos = coordinador.getPagoAnticipos().getCantidadAnticipos();
	int cantCuotas = coordinador.getPagoCuotasPlanPago().getCantidadCuotas();
	if (cantAnticipos > 0){
	    pregunta += "<br> <li> Boleta Anticipos ("+ coordinador.getImpuesto().getTipoImpuesto().getNombre()+") Monto Total: " + Format.money(coordinador.getPagoAnticipos().getMontoTotal()) + "</li>";
	}
	if(cantCuotas > 0){
	    pregunta += "<br> <li> Boleta Plan de Pago ("+ coordinador.getImpuesto().getTipoImpuesto().getNombre()+") Monto Total: " + Format.money(coordinador.getPagoCuotasPlanPago().getMontoTotal()) + "</li>";
	}
	pregunta += "</ul></html>";
	if(Advisor.question("Aviso",pregunta)){
	    if (cantAnticipos > 0){
	        exito = (exito && coordinador.registrarBoletaAnticipos());
	    }
	    if (cantCuotas > 0){
	        exito = (exito && coordinador.registrarBoletaCuotasPlanDePago());
	    }   
	    if(exito){
	        coordinador.inicio();
	    }
	}
    }
    
    public void iniciarPanel(){
	BigDecimal totalAPagar = new BigDecimal("0.0");
	BigDecimal subTotalAPagarAnticipos = new BigDecimal("0.0");
	System.out.println("****************** RESUMEN PAGO ANTICIPOS*****************");
	System.out.println("PERIODO: " + coordinador.getPagoAnticipos().getPeriodoDesde() + "/" +coordinador.getPagoAnticipos().getAnioDesde() + " - " + coordinador.getPagoAnticipos().getPeriodoHasta() + "/" +coordinador.getPagoAnticipos().getAnioHasta() );
	System.out.println("CANTIDAD PERIODOS: " + coordinador.getPagoAnticipos().getCantidadAnticipos());
	System.out.println("BONIFICACION APLICADA: " + coordinador.getPagoAnticipos().getBonificacion().getDescripcion());
	System.out.println("% BONIFICACION: " + (coordinador.getPagoAnticipos().getBonificacion().getPorcentaje() * 100) );
	System.out.println("TOTAL PERIODOS ADEUDADOS: "+coordinador.getPagoAnticipos().getMontoTotal());
	System.out.println("CAPITAL: "+coordinador.getPagoAnticipos().getMonto());
	System.out.println("INTERES: "+coordinador.getPagoAnticipos().getInteres());
	System.out.println("TOTAL: "+coordinador.getPagoAnticipos().getMontoTotal());
	System.out.println("BONIFICACION: "+coordinador.getPagoAnticipos().getDescuento());
	System.out.println("% BONIFICACION: "+(coordinador.getPagoAnticipos().getBonificacion().getPorcentaje()) * 100);
	System.out.println("****************** RESUMEN PAGO CUOTAS*****************");
	System.out.println("CANTIDAD CUOTAS: " + coordinador.getPagoCuotasPlanPago().getCantidadCuotas());
	System.out.println("IMPORTE: " + coordinador.getPagoCuotasPlanPago().getImporte());
	System.out.println("CAPITAL: " + coordinador.getPagoCuotasPlanPago().getImporte());
	System.out.println("INTERES: " + coordinador.getPagoCuotasPlanPago().getRecargoPorMora());
	System.out.println("TOTAL CUOTAS A PAGAR: " + coordinador.getPagoCuotasPlanPago().getMontoTotal());
	System.out.println("****************** TOTAL A PAGAR*****************");
	totalAPagar = totalAPagar.add(new BigDecimal(String.valueOf(coordinador.getPagoAnticipos().getMontoTotal())));
	totalAPagar = totalAPagar.add(new BigDecimal(String.valueOf(coordinador.getPagoCuotasPlanPago().getMontoTotal())));
	System.out.println("TOTAL A PAGAR: $ " + totalAPagar);
	
	tfPlanDePagoNro.setValue(coordinador.getPlanDePagoActual().getIdplandepago());
	if(coordinador.getPagoAnticipos().getCantidadAnticipos() > 0){
	    tfBonificacionAplicada.setValue(coordinador.getPagoAnticipos().getBonificacion().getDescripcion());
	    tfPorcentajeBonif.setValue((coordinador.getPagoAnticipos().getBonificacion().getPorcentaje()) * 100);
	    tfCantPeriodosAPagar.setText("" + coordinador.getPagoAnticipos().getCantidadAnticipos());
	    tfTotalPeriodosAdeudados.setValue(coordinador.getPagoAnticipos().getMontoTotal());
	    tfCapital.setValue(coordinador.getPagoAnticipos().getMonto());
	    tfInteres.setValue(coordinador.getPagoAnticipos().getInteres());
	    subTotalAPagarAnticipos = subTotalAPagarAnticipos.add(new BigDecimal(String.valueOf(coordinador.getPagoAnticipos().getMonto())));
	    subTotalAPagarAnticipos = subTotalAPagarAnticipos.add(new BigDecimal(String.valueOf(coordinador.getPagoAnticipos().getInteres())));
	    tfSubTotalAPagar.setValue(subTotalAPagarAnticipos);
	    tfCondonacionIntereses1.setValue(coordinador.getPagoAnticipos().getDescuento());
	    tfSubtotal1.setValue(coordinador.getPagoAnticipos().getMontoTotal());
	    tfTotalAnticipos.setValue(coordinador.getPagoAnticipos().getMontoTotal());
	}else{
	    clearFieldsPagoAnticipos();
	}
	
	if(coordinador.getPagoCuotasPlanPago().getCantidadCuotas() > 0){
	    tfCantCuotasAPagar.setText("" + coordinador.getPagoCuotasPlanPago().getCantidadCuotas());
	    tfSubtotal2.setValue(coordinador.getPagoCuotasPlanPago().getImporte());
	    tfCapitalCuotas.setValue(coordinador.getPagoCuotasPlanPago().getImporte());
	    tfInteresCuotas.setValue(coordinador.getPagoCuotasPlanPago().getRecargoPorMora());
	    tfTotalCuotas.setValue(coordinador.getPagoCuotasPlanPago().getMontoTotal());
	}else{
	    clearFieldsPagoCuotas();
	}
	tfTotalAPagar.setValue(totalAPagar);
    }
    
    public void clearFieldsPagoAnticipos(){
	tfBonificacionAplicada.setValue("");
	tfPorcentajeBonif.setValue(0.0);
	tfCantPeriodosAPagar.setText("0");
	tfTotalPeriodosAdeudados.setValue(0.0);
	tfCapital.setValue(0.0);
	tfInteres.setValue(0.0);
	tfSubTotalAPagar.setValue(0.0);
	tfCondonacionIntereses1.setValue(0.0);
	tfSubtotal1.setValue(0.0);
	tfTotalAnticipos.setValue(0.0);
    }
    
    public void clearFieldsPagoCuotas(){
	tfCantCuotasAPagar.setText("");
	tfSubtotal2.setValue(0.0);
	tfCapitalCuotas.setValue(0.0);
	tfInteresCuotas.setValue(0.0);
	tfTotalCuotas.setValue(0.0);
    }

    
}

