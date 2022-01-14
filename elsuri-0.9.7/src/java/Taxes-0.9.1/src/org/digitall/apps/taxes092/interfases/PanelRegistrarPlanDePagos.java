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
 * PanelRegistrarPlanDePagos.java
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
import javax.swing.JButton;
import javax.swing.JSeparator;
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
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;


public class PanelRegistrarPlanDePagos extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelPeriodos = new BasicPanel();

    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblTotalAPagar = new BasicLabel();
    private BasicLabel lblCuotaAPagar = new BasicLabel();
    private BasicLabel lblAnticipoActual = new BasicLabel();
    
    private BasicLabel lblEntrega = new BasicLabel();
    private BasicLabel lblDesdeHasta = new BasicLabel();
    private BasicLabel lblCapital = new BasicLabel();
    private BasicLabel lblInteres = new BasicLabel();
    private BasicLabel lblTotal = new BasicLabel();
    
    private BasicLabel lblPeriodoPP = new BasicLabel();
    private BasicLabel lblCuotasPP = new BasicLabel();
    private BasicLabel lblTextoCuotasPP = new BasicLabel();
    private BasicLabel lblTotalPP = new BasicLabel();
    private String textoCuotas = "";

    private BasicTextArea taAyuda = new BasicTextArea();
    
    private TFInput tfPeriodo = new TFInput(DataTypes.STRING,"Desde - Hasta", false);
    private TFInput tfCantAnticipos = new TFInput(DataTypes.STRING,"Períodos", false);
    private TFInput tfBonificacionAplicada = new TFInput(DataTypes.STRING,"Bonificacion Aplicada", false);
    private TFInput tfPorcentajeBonif = new TFInput(DataTypes.STRING,"% Bonif.", false);
    private JMoneyEntry tfTotalAPagar = new JMoneyEntry(); 
    private JMoneyEntry tfCuotaAPagar = new JMoneyEntry();
    private JMoneyEntry tfAnticipoActual = new JMoneyEntry();
    
    
    private JMoneyEntry tfEntrega = new JMoneyEntry();
    private BasicTextField tfPeriodoAnticipos = new BasicTextField();
    private JMoneyEntry tfCapitalAnticipos = new JMoneyEntry();
    private JMoneyEntry tfInteresesAnticipos = new JMoneyEntry();
    private JMoneyEntry tfTotalAnticipos = new JMoneyEntry();
    
    private JMoneyEntry tfTotalPP = new JMoneyEntry();
    
    private BasicTextField tfPeriodoPP = new BasicTextField();
    
    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private JSeparator jSeparator3 = new JSeparator();
    private BasicButton btnGenerarPlan = new BasicButton();
    private Coordinador coordinador;

    public PanelRegistrarPlanDePagos(Coordinador _coordinador) {
	coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(845, 483));
	panelAyuda.setBounds(new Rectangle(0, 0, 215, 510));
	panelAyuda.setLayout(null);
	panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelAyuda.setSize(new Dimension(215, 475));
	lblTitulo.setText("Registrar Plan de Pago");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
        lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 445));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 480));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setBounds(new Rectangle(5, 5, 620, 55));
	panelPeriodos.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setLayout(null);
	tfPeriodo.setBounds(new Rectangle(5, 10, 145, 35));
	tfPeriodo.setEditable(false);
	tfPeriodo.setHorizontalAlignment(TFInput.CENTER);
	tfCantAnticipos.setBounds(new Rectangle(175, 10, 65, 35));
	tfCantAnticipos.setEditable(false);
	tfCantAnticipos.setHorizontalAlignment(TFInput.CENTER);
	tfBonificacionAplicada.setBounds(new Rectangle(270, 10, 225, 35));
	tfBonificacionAplicada.setEditable(false);
	tfBonificacionAplicada.setHorizontalAlignment(TFInput.CENTER);
	tfPorcentajeBonif.setBounds(new Rectangle(515, 10, 100, 35));
	tfPorcentajeBonif.setEditable(false);
	tfPorcentajeBonif.setHorizontalAlignment(TFInput.CENTER);
	tfTotalAPagar.setBounds(new Rectangle(365, 425, 255, 45));
	tfTotalAPagar.setEditable(false);
	tfTotalAPagar.setFont(new Font("Dialog", 1, 30));
	tfTotalAPagar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfTotalAPagar.setForeground(Color.red);
	lblTotalAPagar.setText("Total a Pagar:");
	lblTotalAPagar.setBounds(new Rectangle(180, 430, 170, 45));
	lblTotalAPagar.setFont(new Font("Dialog", 1, 20));
	tfCuotaAPagar.setBounds(new Rectangle(480, 235, 140, 30));
	tfCuotaAPagar.setEditable(false);
	tfCuotaAPagar.setFont(new Font("Dialog", 1, 21));
	tfCuotaAPagar.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfCuotaAPagar.setForeground(new Color(82, 132, 0));
        
        tfEntrega.setBounds(new Rectangle(480, 65, 140, 30));
        tfEntrega.setEditable(false);
        tfEntrega.setFont(new Font("Dialog", 1, 21));
        tfEntrega.setHorizontalAlignment(JMoneyEntry.RIGHT);
        tfEntrega.setForeground(new Color(82, 132, 0));
        
        tfPeriodoAnticipos.setBounds(new Rectangle(375, 98, 245, 30));
        tfPeriodoAnticipos.setEditable(false);
        tfPeriodoAnticipos.setFont(new Font("Dialog", 1, 21));
        tfPeriodoAnticipos.setForeground(new Color(82, 132, 0));
        
        tfCapitalAnticipos.setBounds(new Rectangle(480, 130, 140, 30));
        tfCapitalAnticipos.setEditable(false);
        tfCapitalAnticipos.setFont(new Font("Dialog", 1, 21));
        tfCapitalAnticipos.setHorizontalAlignment(JMoneyEntry.RIGHT);
        tfCapitalAnticipos.setForeground(new Color(82, 132, 0));
        
        tfInteresesAnticipos.setBounds(new Rectangle(480, 163, 140, 30));
        tfInteresesAnticipos.setEditable(false);
        tfInteresesAnticipos.setFont(new Font("Dialog", 1, 21));
        tfInteresesAnticipos.setHorizontalAlignment(JMoneyEntry.RIGHT);
        tfInteresesAnticipos.setForeground(new Color(82, 132, 0));
        
        tfTotalAnticipos.setBounds(new Rectangle(480, 195, 140, 30));
        tfTotalAnticipos.setEditable(false);
        tfTotalAnticipos.setFont(new Font("Dialog", 1, 21));
        tfTotalAnticipos.setHorizontalAlignment(JMoneyEntry.RIGHT);
        tfTotalAnticipos.setForeground(Color.red);
        
        tfPeriodoPP.setBounds(new Rectangle(375, 270, 245, 30));
        tfPeriodoPP.setEditable(false);
        tfPeriodoPP.setFont(new Font("Dialog", 1, 21));
        tfPeriodoPP.setForeground(new Color(82, 132, 0));

        tfTotalPP.setBounds(new Rectangle(480, 335, 140, 30));
        tfTotalPP.setEditable(false);
        tfTotalPP.setFont(new Font("Dialog", 1, 21));
        tfTotalPP.setHorizontalAlignment(JMoneyEntry.RIGHT);
        tfTotalPP.setForeground(Color.red);
        
        /** [2010-05-05] Modificado por Cesar */
	//lblCuotaAPagar.setText("Cuota a Pagar (entrega)");
        lblCuotaAPagar.setText("Plan de Pago");
        /*************************************/
	lblCuotaAPagar.setBounds(new Rectangle(10, 235, 265, 30));
	lblCuotaAPagar.setFont(new Font("Dialog", 1, 18));
	jSeparator1.setBounds(new Rectangle(5, 420, 615, 5));
        jSeparator2.setBounds(new Rectangle(0, 230, 615, 5));
        jSeparator3.setBounds(new Rectangle(0, 375, 615, 5));
	tfAnticipoActual.setBounds(new Rectangle(480, 385, 140, 30));
	tfAnticipoActual.setEditable(false);
	tfAnticipoActual.setFont(new Font("Dialog", 1, 21));
	tfAnticipoActual.setHorizontalAlignment(JMoneyEntry.RIGHT);
	tfAnticipoActual.setForeground(new Color(82, 132, 0));
	lblAnticipoActual.setText("Mes Actual");
	lblAnticipoActual.setBounds(new Rectangle(10, 385, 440, 30));
	lblAnticipoActual.setFont(new Font("Dialog", 1, 18));
        
        lblEntrega.setText("Entrega");
        lblEntrega.setBounds(new Rectangle(5, 65, 120, 30));
        lblEntrega.setFont(new Font("Dialog", 1, 18));
        lblDesdeHasta.setText("Desde - Hasta");
        lblDesdeHasta.setBounds(new Rectangle(5, 100, 265, 30));
        lblDesdeHasta.setFont(new Font("Dialog", 1, 18));
        lblCapital.setText("Capital");
        lblCapital.setBounds(new Rectangle(5, 130, 120, 30));
        lblCapital.setFont(new Font("Dialog", 1, 18));
        lblInteres.setText("Interes");
        lblInteres.setBounds(new Rectangle(5, 163, 120, 30));
        lblInteres.setFont(new Font("Dialog", 1, 18));
        lblTotal.setText("Total");
        lblTotal.setBounds(new Rectangle(5, 195, 120, 30));
        lblTotal.setFont(new Font("Dialog", 1, 18));
        
        lblPeriodoPP.setText("Desde - Hasta");
        lblPeriodoPP.setBounds(new Rectangle(10, 268, 265, 30));
        lblPeriodoPP.setFont(new Font("Dialog", 1, 18));
        
        lblCuotasPP.setText("Cuotas");
        lblCuotasPP.setBounds(new Rectangle(10, 302, 120, 30));
        lblCuotasPP.setFont(new Font("Dialog", 1, 18));
        
        lblTextoCuotasPP.setBounds(new Rectangle(145, 305, 475, 25));
        lblTextoCuotasPP.setFont(new Font("Dialog", 1, 18));
        
        lblTextoCuotasPP.setForeground(Color.yellow);

        lblTextoCuotasPP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalPP.setText("Total");
        lblTotalPP.setBounds(new Rectangle(10, 335, 120, 30));
        lblTotalPP.setFont(new Font("Dialog", 1, 18));
        
	btnGenerarPlan.setText("Generar Plan");
	btnGenerarPlan.setOpaque(true);
	btnGenerarPlan.setFont(new Font("Dialog", 1, 10));
	btnGenerarPlan.setForeground(Color.black);
	btnGenerarPlan.setBackground(new Color(165, 165, 0));
	btnGenerarPlan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnGenerarPlan.setBounds(new Rectangle(10, 440, 110, 30));
	btnGenerarPlan.setToolTipText("Generar Plan de Pago");
	btnGenerarPlan.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGenerar_actionPerformed(e);
		}
	    }
	);
	panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
        panelCentral.add(btnGenerarPlan, null);
        panelCentral.add(lblAnticipoActual, null);
        panelCentral.add(lblEntrega, null);
        panelCentral.add(lblDesdeHasta, null);
        panelCentral.add(lblPeriodoPP, null);
        panelCentral.add(lblTextoCuotasPP, null);
        panelCentral.add(lblTotalPP, null);
        panelCentral.add(lblCuotasPP, null);
        panelCentral.add(lblCapital, null);
        panelCentral.add(lblInteres, null);
        panelCentral.add(lblTotal, null);
        panelCentral.add(jSeparator1, null);
        panelCentral.add(jSeparator2, null);
        panelCentral.add(jSeparator3, null);
        panelCentral.add(lblCuotaAPagar, null);
        panelCentral.add(tfCuotaAPagar, null);
        panelCentral.add(tfAnticipoActual, null);
        panelCentral.add(tfEntrega, null);
        panelCentral.add(tfPeriodoAnticipos, null);
        panelCentral.add(tfCapitalAnticipos, null);
        panelCentral.add(tfInteresesAnticipos, null);
        panelCentral.add(tfTotalAnticipos, null);
        panelCentral.add(tfTotalPP, null);
        panelCentral.add(tfPeriodoPP, null);
        panelCentral.add(lblTotalAPagar, null);
        panelCentral.add(tfTotalAPagar, null);
        panelCentral.add(panelPeriodos, null);
	panelPeriodos.add(tfBonificacionAplicada, null);
	panelPeriodos.add(tfPorcentajeBonif, null);
	panelPeriodos.add(tfCantAnticipos, null);
	panelPeriodos.add(tfPeriodo, null);
	this.add(panelCentral, null);
	this.add(panelAyuda, null);
	taAyuda.setEditable(false);
        /** [2010-05-05] Modificado por Cesar */
        tfCuotaAPagar.setVisible(false);
        tfEntrega.setVisible(false);
        /**************************************/
    }


    private void btnGenerar_actionPerformed(ActionEvent e) {
	generarPlanDePagoMoratoria();
    }
    
    private void generarPlanDePagoMoratoria(){
	if (coordinador.getTipoPlanDePago().isObligatorio()) {
	    if (Advisor.question("Aviso","¿Está seguro de registrar la Moratoria configurada?"))  {
	       if(coordinador.registrarMoratoria()){
		   Advisor.messageBox("Se grabó correctamente la Moratoria","Aviso");
		   cargarPagoAnticipo();
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
		     /*
                      * NO SE PAGA LA PRIMER CUOTA CUANDO SE HACE PLAN DE PAGO CON ENTREGA
                      * PERO SI SE DEBE GENERAR EL CONTRATO DEL PLAN DE PAGO
                     cargarPagoCuota();   
		     if(coordinador.registrarBoletaCuotasPlanDePago()){
                         Advisor.messageBox("Se grabó correctamente el Plan de Pago","Aviso");
                     } else {
                         Advisor.messageBox("Ocurrió un error al grabar la boleta del Plan de Pago","Aviso");
                     }*/
                     if (coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0) {
                         //Cargar el contrato de plan de pago
                         cargarContratoPlanDePago(); 
                         coordinador.generarContratoPlanDePago(); 
                        
                        // Generar y registrar la boleta por el pago de anticipos regulares que cubrió la entrega
                        cargarPagoAnticipos();
                         if(coordinador.registrarBoletaAnticipos()){
                         // Generar el pago del anticipo actual
                             cargarPagoAnticipo();
                             if(coordinador.registrarBoletaAnticipos()){
                                coordinador.clearConfiguracionPlanDePago();
                                coordinador.inicio();
                             }else{
                                 Advisor.messageBox("Ocurrió un error al grabar los datos del anticipo actual","Aviso!");
                             }
                         }else{
                             Advisor.messageBox("Ocurrió un error al grabar los datos de anticipos ","Aviso!");
                         }
                        
                     } else {
                         cargarPagoCuota();   
                         if(coordinador.registrarBoletaCuotasPlanDePago()){
                             cargarPagoAnticipo();
                             if(coordinador.registrarBoletaAnticipos()){
                                coordinador.inicio();
                             }else{
                                 Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
                             }
                         }
                     }
		     
		 }else{
		     Advisor.messageBox("Ocurrió un error al grabar los datos del plan de pago.","Aviso!");
		 }
	     } else {
		//por ahora no va
	     }
	}
    }
    
    /**
     * Debe registrar la boleta por el pago de anticipos que cubrió la entrega anticipada
     */
    private void cargarPagoAnticipos(){
        // seria lo mismo que el método cargarPagoAnticipo pero para los anticipos que fueron cubiertos por la entrega
        // refinar este método
        coordinador.clearPagoAnticipo();
        coordinador.getPagoAnticipos().setBonificacion(coordinador.getBonificacion().getIdBonificacion());
        coordinador.getPagoAnticipos().setMonto(coordinador.getConfiguracionPlanDePago().getMontoBaseCubiertoEntregaSinDto());
        coordinador.getPagoAnticipos().setInteres(coordinador.getConfiguracionPlanDePago().getInteresBaseCubiertoEntregaSinDto());
        BigDecimal monto = new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getMontoBaseCubiertoEntregaSinDto());
        BigDecimal interes = new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getInteresBaseCubiertoEntregaSinDto());
        BigDecimal total = new BigDecimal("" + coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
        
        BigDecimal descuento = new BigDecimal("0");  
        descuento = descuento.add(monto);
        descuento = descuento.add(interes);
        descuento = descuento.subtract(total);
        coordinador.getPagoAnticipos().setDescuento(descuento.doubleValue());
        coordinador.getPagoAnticipos().setMontoTotal(coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
        
        if(coordinador.getBonificacion().getPorcentaje() == 0){
            coordinador.getPagoAnticipos().setTotalAnticiposAdeudados(coordinador.getConfiguracionPlanDePago().getTotalAnticiposAdeudadosEntrega());// Cambiar para usar con bigdecimal    
        }else{
            coordinador.getPagoAnticipos().setTotalAnticiposAdeudados(coordinador.getConfiguracionPlanDePago().getMontoBaseCubiertoEntrega());// Cambiar para usar con bigdecimal    
        }
        int periodoDesde = coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega();
        int periodoHasta = coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega();
        int anioDesde = coordinador.getConfiguracionPlanDePago().getMenorAnioEntrega();
        int anioHasta = coordinador.getConfiguracionPlanDePago().getMayorAnioEntrega();
        if ((coordinador.getBien().esAutomotor())) {
            periodoDesde = (coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega() + 1)/2;
            periodoHasta = (coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega() + 1)/2;
        }
        int cantidadPeriodos = coordinador.getConfiguracionPlanDePago().getCantAnticiposCubiertosEntrega();
        coordinador.getPagoAnticipos().setPeriodoDesde(periodoDesde);
        coordinador.getPagoAnticipos().setPeriodoHasta(periodoHasta);
        coordinador.getPagoAnticipos().setAnioDesde(anioDesde);
        coordinador.getPagoAnticipos().setAnioHasta(anioHasta);
        coordinador.getPagoAnticipos().setCantidadAnticipos(cantidadPeriodos); 
    }
    
    private void cargarPagoCuota(){
	coordinador.getPagoCuotasPlanPago().setMonto(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota());
	coordinador.getPagoCuotasPlanPago().setImporte(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota());
	coordinador.getPagoCuotasPlanPago().setDeduccion(0.0);
	coordinador.getPagoCuotasPlanPago().setRecargoPorMora(0.0);
	coordinador.getPagoCuotasPlanPago().setCantidadCuotas(1);
    }
    
    private void cargarPagoAnticipo(){
        coordinador.clearPagoAnticipo();
	coordinador.getPagoAnticipos().setBonificacion(coordinador.getBonificacion().getIdBonificacion());
	coordinador.getPagoAnticipos().setMonto(coordinador.getConfiguracionPlanDePago().getValorAnticipoActual());
	coordinador.getPagoAnticipos().setInteres(0.0);
	coordinador.getPagoAnticipos().setDescuento(coordinador.getConfiguracionPlanDePago().getValorDescuentoAnticipoActual());
	coordinador.getPagoAnticipos().setMontoTotal(coordinador.getConfiguracionPlanDePago().getValorNetoAnticipoActual());
	
	if(coordinador.getBonificacion().getPorcentaje() == 0){
	    coordinador.getPagoAnticipos().setTotalAnticiposAdeudados(coordinador.getConfiguracionPlanDePago().getValorAnticipoActual() - coordinador.getConfiguracionPlanDePago().getValorDescuentoAnticipoActual());// Cambiar para usar con bigdecimal    
	}else{
	    coordinador.getPagoAnticipos().setTotalAnticiposAdeudados(coordinador.getConfiguracionPlanDePago().getValorNetoAnticipoActual());// Cambiar para usar con bigdecimal    
	}
        
        //corregir la forma en que se pasan los periodos en caso de ser automotor
        int periodoDesde = 0;
        int periodoHasta = 0;
        int anioDesde = 0;
        int anioHasta = 0;
        int cantidadPeriodos = 1;
        
	//if (coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0) {
	    if ((coordinador.getBien().esAutomotor())) {
		periodoDesde = (coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega() + 1)/ 2 ;
		periodoHasta = periodoDesde;
		anioDesde = coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega();
		anioHasta = anioDesde;    
	    } else {
		/*
		periodoDesde = coordinador.getConfiguracionPlanDePago().getMenorAnticipo();
		periodoHasta = coordinador.getConfiguracionPlanDePago().getMayorAnticipo();
		anioDesde = coordinador.getConfiguracionPlanDePago().getMayorAnio();
		anioHasta = coordinador.getConfiguracionPlanDePago().getMenorAnio();
		*//*
		periodoDesde = coordinador.getConfiguracionPlanDePago().getMenorAnticipo();
		periodoHasta = periodoDesde;
		anioDesde = coordinador.getConfiguracionPlanDePago().getMenorAnio();
		anioHasta = anioDesde;*/
		periodoDesde = coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega();
		anioDesde = coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega();
		periodoHasta = periodoDesde;
		anioHasta = anioDesde;
	    }
	//}
	//coordinador.getPagoAnticipos().setCantidadAnticipos(cantidadPeriodos);
        coordinador.getPagoAnticipos().setCantidadAnticipos(1);
	coordinador.getPagoAnticipos().setPeriodoDesde(periodoDesde);
	coordinador.getPagoAnticipos().setPeriodoHasta(periodoHasta);
	coordinador.getPagoAnticipos().setAnioDesde(anioDesde);
	coordinador.getPagoAnticipos().setAnioHasta(anioHasta);
    }
    
    
    private int getPeriodoDesde(int _ultimoAnticipo){
	int resultado = 0;
	if(coordinador.getBien().getTipoBien() == coordinador.getBien().TIPO_CATASTRO){
	    if(_ultimoAnticipo == 12){
		resultado = 1;
	    }else{
	        resultado = _ultimoAnticipo + 1;
	    }
	}else{
	    if(_ultimoAnticipo == 6){
	        resultado = 1;
	    }else{
	        resultado = _ultimoAnticipo + 1;
	    }
	}
	return resultado;
    }
    
    private int getAnioDesde(int _ultimoAnio, int _periododDesde){
	int resultado = 0;
	if((_periododDesde == 12) || (_periododDesde == 6)){
	    resultado = _ultimoAnio + 1;
	}else{
	    resultado = _ultimoAnio;
	}
	return resultado;
    }
    
    public void iniciarPanel(){
        String periodo = "";
        String periodoAnticipos = "";
        if(coordinador.getConfiguracionPlanDePago().getPagoAnticipado() > 0) {
            if (coordinador.getBien().esAutomotor()) {
                periodo = ((coordinador.getConfiguracionPlanDePago().getmenorAnticipo() + 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + ((coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMayorAnio();
                periodoAnticipos = ((coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega() + 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioEntrega() + " AL " + ((coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega()+ 1)/2) + "/" + coordinador.getConfiguracionPlanDePago().getMayorAnioEntrega() ;
                lblAnticipoActual.setText("Bimestre Actual ("+ (coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega() + 1)/2 +"/"+ coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega() +")");
            }else {
                periodo = coordinador.getConfiguracionPlanDePago().getmenorAnticipo() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnio();
                periodoAnticipos = coordinador.getConfiguracionPlanDePago().getMenorAnticipoEntrega() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnioEntrega() + " AL " + coordinador.getConfiguracionPlanDePago().getMayorAnticipoEntrega() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnioEntrega();
                lblAnticipoActual.setText("Mes Actual ("+ coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega() +"/"+ coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega() +")");
            }
            //Cargar los valores correspondiente al plan de pago y cancelación de anticipos regulares que cubra la entrega
            //Datos del Plan de Pago
            tfPeriodo.setValue(periodo);
            tfPeriodoAnticipos.setText(periodoAnticipos);
            tfCapitalAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getMontoBaseCubiertoEntrega());
            tfInteresesAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getInteresBaseCubiertoEntrega());
            tfTotalAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntrega());
            tfCantAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
            tfBonificacionAplicada.setValue(coordinador.getBonificacion().getNombre());
            tfPorcentajeBonif.setValue(coordinador.getBonificacion().getPorcentaje() * 100);
            tfCuotaAPagar.setValue(0);
            tfPeriodoPP.setText(periodo);
            if (coordinador.getConfiguracionPlanDePago().getValorPrimerCuota() > 0)  {
                textoCuotas =  " 1/c de "+ Format.money((new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota())).doubleValue()) +" + "+ (coordinador.getConfiguracionPlanDePago().getCantidadCuotas() - 1) +"/c de "+ Format.money( (new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorCuotasRestantes())).doubleValue());
            } else  {
                textoCuotas =  " 1/c de "+ Format.money((new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota())).doubleValue());
            }
            lblTextoCuotasPP.setText(textoCuotas);
            tfAnticipoActual.setValue(coordinador.getConfiguracionPlanDePago().getValorNetoAnticipoActual());
            tfTotalPP.setValue(coordinador.getConfiguracionPlanDePago().getTotal());
            tfTotalAPagar.setValue(coordinador.getConfiguracionPlanDePago().getTotalAPagarConEntrega());    

            /** [2010-05-05] Modificado por Cesar */
            tfEntrega.setValue(coordinador.getConfiguracionPlanDePago().getTotalBaseCubiertoEntregaSinDto());
            /**************************************/            
            
        } else {
            if (coordinador.getBien().esAutomotor()) {
                periodo = coordinador.getConfiguracionPlanDePago().getmenorAnticipo()  + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + "/" + coordinador.getConfiguracionPlanDePago().getMayorAnio();
                lblAnticipoActual.setText("Bimestre Actual ("+ ((coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega() + 1) /2 )  +"/"+ coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega() +")");
            }else {
                periodo = coordinador.getConfiguracionPlanDePago().getmenorAnticipo() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnio();
                lblAnticipoActual.setText("Mes Actual ("+ coordinador.getConfiguracionPlanDePago().getAnticipoActualEntrega() +"/"+ coordinador.getConfiguracionPlanDePago().getAnioAnticipoActualEntrega() +")");
            }
            tfPeriodoAnticipos.setText("");
            tfCapitalAnticipos.setValue(0.0);
            tfInteresesAnticipos.setValue(0.0);
            tfTotalAnticipos.setValue(0.0);
            tfEntrega.setValue(0.00);
            tfPeriodo.setValue(coordinador.getConfiguracionPlanDePago().getmenorAnticipo() + "/" + coordinador.getConfiguracionPlanDePago().getMenorAnio() + " AL " + coordinador.getConfiguracionPlanDePago().getmayorAnticipo() + "/" +coordinador.getConfiguracionPlanDePago().getMayorAnio());
            tfCantAnticipos.setValue(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
            tfBonificacionAplicada.setValue(coordinador.getBonificacion().getNombre());
            tfPorcentajeBonif.setValue(coordinador.getBonificacion().getPorcentaje() * 100);
            tfCuotaAPagar.setValue(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota());
            tfPeriodoPP.setText(periodo);
            if (coordinador.getConfiguracionPlanDePago().getValorPrimerCuota() > 0)  {
                textoCuotas =  " 1/c de "+ Format.money((new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota())).doubleValue()) +" + "+ (coordinador.getConfiguracionPlanDePago().getCantidadCuotas() - 1) +"/c de "+ Format.money( (new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorCuotasRestantes())).doubleValue());
            } else  {
                textoCuotas =  " 1/c de "+ Format.money((new BigDecimal(coordinador.getConfiguracionPlanDePago().getValorPrimerCuota())).doubleValue());
            }
            lblTextoCuotasPP.setText(textoCuotas);
            tfAnticipoActual.setValue(coordinador.getConfiguracionPlanDePago().getValorNetoAnticipoActual());
            tfTotalPP.setValue(coordinador.getConfiguracionPlanDePago().getTotal());
            tfTotalAPagar.setValue(coordinador.getConfiguracionPlanDePago().getTotalAPagarSinEntrega());
        }
    }
    
    public void cargarContratoPlanDePago(){
        
        coordinador.getContratoPlanDePago().setCantPeriodos(coordinador.getConfiguracionPlanDePago().getCantidadAnticipos());
        /*if (coordinador.getBien().esAutomotor()) {
            coordinador.getContratoPlanDePago().setConceptoPeriodos("" +
                                                                    (coordinador.getConfiguracionPlanDePago().getMenorAnticipoPPEntrega() + 1) / 2 +
                                                                    "/" +
                                                                    coordinador.getConfiguracionPlanDePago().getMenorAnioPPEntrega() +
                                                                    " AL " +
                                                                    (coordinador.getConfiguracionPlanDePago().getMayorAnticipoPPEntrega() + 1) / 2 +
                                                                    "/" +
                                                                    coordinador.getConfiguracionPlanDePago().getMayorAnioPPEntrega());
        } else {
            coordinador.getContratoPlanDePago().setConceptoPeriodos("" +
                                                                    coordinador.getConfiguracionPlanDePago().getMenorAnticipoPPEntrega() +
                                                                    "/" +
                                                                    coordinador.getConfiguracionPlanDePago().getMenorAnioPPEntrega() +
                                                                    " AL " +
                                                                    coordinador.getConfiguracionPlanDePago().getMayorAnticipoPPEntrega() +
                                                                    "/" +
                                                                    coordinador.getConfiguracionPlanDePago().getMayorAnioPPEntrega());
        }
        */
        coordinador.getContratoPlanDePago().setConceptoPeriodos(coordinador.getMoratoria().getNombre() + " Nº " + coordinador.getMoratoria().getIdPlanDePago());
        if ( (Math.abs(coordinador.getConfiguracionPlanDePago().getMontoBonificacion()) > 0.00) && (true)) {
            coordinador.getContratoPlanDePago().setImporte(coordinador.getConfiguracionPlanDePago().getMontoBasePPEntregaSinDto());
            coordinador.getContratoPlanDePago().setInteres(coordinador.getConfiguracionPlanDePago().getInteresBasePPEntregaSinDto());
            coordinador.getContratoPlanDePago().setMontoTotal(coordinador.getConfiguracionPlanDePago().getTotalBasePPEntregaSinDto());
        } else {
            coordinador.getContratoPlanDePago().setImporte(coordinador.getConfiguracionPlanDePago().getMontoBasePPEntrega());
            coordinador.getContratoPlanDePago().setInteres(coordinador.getConfiguracionPlanDePago().getInteresBasePPEntrega());
            coordinador.getContratoPlanDePago().setMontoTotal(coordinador.getConfiguracionPlanDePago().getTotal());
        }
        coordinador.getContratoPlanDePago().setMontoTotal(coordinador.getConfiguracionPlanDePago().getTotal());

        coordinador.getContratoPlanDePago().setBonificacionMoratoria(coordinador.getConfiguracionPlanDePago().getMontoBonificacion());
        coordinador.getContratoPlanDePago().setBonificacionespecial(coordinador.getConfiguracionPlanDePago().getMontoBonificacion());
        coordinador.getContratoPlanDePago().setCantcuotas(coordinador.getConfiguracionPlanDePago().getCantidadCuotas());
        coordinador.getContratoPlanDePago().setMontoCuotas(coordinador.getConfiguracionPlanDePago().getValorCuotasRestantes());
        coordinador.getContratoPlanDePago().setPorcentajeIntFin(coordinador.getConfiguracionPlanDePago().getPorcentajeInteresFinanciacion() * coordinador.getConfiguracionPlanDePago().getCantidadCuotas() / 100);
        coordinador.getContratoPlanDePago().setMontoIntFin(coordinador.getConfiguracionPlanDePago().getMontoInteresFinanciacion());
        coordinador.getContratoPlanDePago().setBonificacionMoratoria(coordinador.getConfiguracionPlanDePago().getMontoCondonacionIntereses());
    }

}



