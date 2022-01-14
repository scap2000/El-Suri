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
 * Coordinador.java
 *
 * */
package org.digitall.apps.taxes092.classes;

import java.math.BigDecimal;

import org.digitall.apps.taxes.classes.BoletaAutomotor;
import org.digitall.apps.taxes.classes.BoletaInmob;
import org.digitall.apps.taxes.classes.BoletaPlanesDePago;
import org.digitall.apps.taxes.classes.BoletaTgs;
import org.digitall.apps.taxes.classes.ContratoPlanDePago;
import org.digitall.apps.taxes.classes.Moratoria;
import org.digitall.apps.taxes.classes.PlanDePago;
import org.digitall.apps.taxes.classes.TipoPlanDePago;
import org.digitall.apps.taxes092.interfases.PagoContado;
import org.digitall.apps.taxes092.interfases.PanelBusquedaBien;
import org.digitall.apps.taxes092.interfases.PanelPagoAnticipos;
import org.digitall.apps.taxes092.interfases.PanelPrepararPlanDePagos;
import org.digitall.apps.taxes092.interfases.PanelRegistrarBoletaAnticiposRegulares;
import org.digitall.apps.taxes092.interfases.PanelRegistrarBoletaCuotasAnticipos;
import org.digitall.apps.taxes092.interfases.PanelRegistrarBoletaPagoContado;
import org.digitall.apps.taxes092.interfases.PanelRegistrarPlanDePagos;
import org.digitall.apps.taxes092.interfases.PanelSeleccionCuotasAnticiposAPagar;
import org.digitall.apps.taxes092.interfases.TaxesMain092;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class Coordinador {
   
    private int paso = 0;
    private int opcion = 0;
    private int subOpcion = 0;
    private boolean verPlanDePagoInmob = true;
    private boolean verPlanDePagoTgs = true;
    
    public static int OPCION_REGISTRAR_BOLETA = 1;
    public static int OPCION_PAGO_CONTADO = 2;
    public static int OPCION_LIBRE_DEUDA = 3;
    public static int OPCION_PLAN_DE_PAGO = 4;
    
    
    public static int TIPO_PLAN_DE_PAGO_TGS = 1;
    public static int TIPO_PLAN_DE_PAGO_INMOB = 2;
    public static int TIPO_PLAN_DE_PAGO_AUTMOTOR = 3;
    
    public int tipoPlanDePagoSeleccionado = 0;
    
    
    /**
     * Objetos que se utilizarán para la administración de objetos
     */
     
    private Bien bien = new Bien();
    private PlanesPago planesPago = new PlanesPago();
    private Impuesto impuesto = new Impuesto();
    private PagoAnticipos pagoAnticipos = new PagoAnticipos();
    private PagoCuotasPlanDePago pagoCuotasPlanPago = new PagoCuotasPlanDePago();
    private ConfiguracionPlanDePago configuracionPlanDePago = new ConfiguracionPlanDePago();
    private TipoPlanDePago tipoPlanDePago = new TipoPlanDePago();
    private Moratoria moratoria = new Moratoria();
    private ContratoPlanDePago contratoPlanDePago = new ContratoPlanDePago();
    private PagoContado pagoContado = new PagoContado();

    /*
    private Boleta boleta = new Boleta();
    private PagoCuotas pagoCuotas = PagoCuotas();
    */
    
    private BoletaTgs boletaTgs;                  //Esta clase se reemplazará para ponerla como dentro de una mas general como por ejemplo la clase BOLETA
    private BoletaInmob boletaInmob;              //Esta clase se reemplazará para ponerla como dentro de una mas general como por ejemplo la clase BOLETA
    private BoletaAutomotor boletaAutomotor;      //Esta clase se reemplazará para ponerla como dentro de una mas general como por ejemplo la clase BOLETA
    private BoletaPlanesDePago boletaPlanesDePago;//Esta clase se reemplazará para ponerla como dentro de una mas general como por ejemplo la clase BOLETA
    private Bonificacion bonificacion = new Bonificacion(); //???
    private BoletaPlanesDePago boletaPlanDePago;

    
    /**
     * Paneles que se utilizarán para la administración de impuestos
     */
     
    private TaxesMain092 taxesMgmt;
    private PanelBusquedaBien panelSeleccionBien = new PanelBusquedaBien(this);
    private PanelPagoAnticipos panelPagoAnticipos = new PanelPagoAnticipos(this);
    private PanelRegistrarBoletaAnticiposRegulares panelRegsitroAnticiposReg = new PanelRegistrarBoletaAnticiposRegulares(this);
    private PanelSeleccionCuotasAnticiposAPagar panelSeleccionCuotasAnticiposAPagar= new PanelSeleccionCuotasAnticiposAPagar(this);
    private PanelRegistrarBoletaCuotasAnticipos panelRegistrarCuotasAnticiposAPagar= new PanelRegistrarBoletaCuotasAnticipos(this);
    private PanelPrepararPlanDePagos panelPrepararPlanDePago = new PanelPrepararPlanDePagos(this);
    private PanelRegistrarPlanDePagos panelRegistrarPlanDePagos = new PanelRegistrarPlanDePagos(this);
    private PanelRegistrarBoletaPagoContado panelRegistrarBoletaPagoContado = new PanelRegistrarBoletaPagoContado(this);
    

    public Coordinador() {
    
    }
    
    public void start(){
	paso = 1;
	addPaneles();
	panelSeleccionBien.setBien(bien);
	setPanelActivo(panelSeleccionBien);
	panelSeleccionBien.iniciarPanel();
	permitirAvance(false);
	permitirRetroceso(false);
	permitirInicio(false);
    }
    
    public void setPanelActivo(BasicPanel _panel){
	setVisibilidadPaneles(false);
	taxesMgmt.setPanelCental(_panel);
	setCabecera();
    }
    
    /**
     * Todos los paneles que maneje el coordinador son agregados al panel central
     */
    private void addPaneles(){
	setVisibilidadPaneles(false);
	taxesMgmt.addPanelCental(panelSeleccionBien);
	taxesMgmt.addPanelCental(panelPagoAnticipos);
	taxesMgmt.addPanelCental(panelRegsitroAnticiposReg);
	taxesMgmt.addPanelCental(panelSeleccionCuotasAnticiposAPagar);
	taxesMgmt.addPanelCental(panelRegistrarCuotasAnticiposAPagar);
	taxesMgmt.addPanelCental(panelPrepararPlanDePago);
	taxesMgmt.addPanelCental(panelRegistrarPlanDePagos);
	taxesMgmt.addPanelCental(panelRegistrarBoletaPagoContado);
	taxesMgmt.repaint();
    }
    
    /**
     * A todos los paneles que maneje el coordinador se los setea visible o no según el parámetro
     * @param _visibles
     */
    private void setVisibilidadPaneles(boolean _visibles){
	panelSeleccionBien.setVisible(_visibles);
	panelPagoAnticipos.setVisible(_visibles);
	panelRegsitroAnticiposReg.setVisible(_visibles);
	panelSeleccionCuotasAnticiposAPagar.setVisible(_visibles);
	panelRegistrarCuotasAnticiposAPagar.setVisible(_visibles);
	panelPrepararPlanDePago.setVisible(_visibles);
	panelRegistrarPlanDePagos.setVisible(_visibles);
	panelRegistrarBoletaPagoContado.setVisible(_visibles);
    }

    public void setTaxesMgmt(TaxesMain092 taxesMgmt) {
	this.taxesMgmt = taxesMgmt;
    }

    public TaxesMain092 getTaxesMgmt() {
	return taxesMgmt;
    }
    
    /**
     * Setea el bien que se está utilizando ya sea un automotor o un catastro
     * @param _idBien
     * @param _tipoBien
     */
    public void setBien(int _idBien, int _tipoBien){ //_tipoBien 1:Catastro - 2: Automotor
	bien = new Bien();
	bien.cargarse(_idBien,_tipoBien,false);
	planesPago = bien.getPlanesPago();
    }
    
    public void siguiente(){	
	System.out.println("PASO: "+paso + " - OPCION: "+opcion + " - SUBOPCION: "+subOpcion);
	switch (paso)  {
	    case 1: {
		    paso = 2;
		    switch (opcion)  {
			case 0: {
			        //Si tiene NO tiene plan de pago pasar a la ventana de selección de anticipos a pagar
			        //En caso de tener un plan de pago se deberá pasar a la ventana Registrar Plan de Pago
			        if (bien.esCatastro()) {
			              if (bien.tienePlanDePagoTgs() && verPlanDePagoTgs) {
			                  //Ir a la ventana de selección de cuotas y anticipos a pagar
			                  impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_TGS);
			                  tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_TGS;
			                  pagoCuotasPlanPago.setPlanDePago(bien.getPlanPagoTGS());
			                  paso = 1;
			                  opcion = 1;
			                  siguiente();
			              } else {
			                  if(verPlanDePagoInmob){
			                      //ver si tiene plan de pago inmob
			                      if(bien.tienePlanDePagoInmob()){
			                           //Ir a la ventana de selección de cuotas y anticipos a pagar
			                          impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_INMOB);
			                          tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_INMOB;
			                          pagoCuotasPlanPago.setPlanDePago(bien.getPlanPagoInmob());
			                          paso = 1;
			                          opcion = 1;
			                          siguiente();
			                      }else{
						  if(verPlanDePagoTgs){
						      impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_TGS);
						      tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_TGS;   
						  }else{
						      impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_INMOB);
						      tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_INMOB;
						  }
			                          opcion = 2;
			                          permitirAvance(false);
			                          permitirRetroceso(true);
			                          permitirInicio(true);
			                          setPanelActivo(panelPagoAnticipos);
			                          panelPagoAnticipos.iniciarPanel();		    
			                      }
			                  }else{
			                      opcion = 2;
			                      impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_TGS);
			                      tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_TGS;
			                      permitirAvance(false);
			                      permitirRetroceso(true);
			                      permitirInicio(true);
			                      setPanelActivo(panelPagoAnticipos);
			                      panelPagoAnticipos.iniciarPanel();      
			                  }
			              }
			          }else{
			              impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_AUTOMOTOR);
			              if(bien.tienePlanDePagoAutomotor()){
			                  //Ir a la ventana de selección de cuotas y anticipos a pagar
			                  impuesto.setTipoDeImpuesto(impuesto.TIPO_IMPUESTO_AUTOMOTOR);
			                  tipoPlanDePagoSeleccionado = TIPO_PLAN_DE_PAGO_AUTMOTOR;
			                  pagoCuotasPlanPago.setPlanDePago(bien.getPlanPagoAutomotor());
					  paso = 1;
			                  opcion = 1;
			                  siguiente();
			              }else{
			                  opcion = 2;
			                  permitirAvance(false);
			                  permitirRetroceso(true);
			                  permitirInicio(true);
			                  setPanelActivo(panelPagoAnticipos);
			                  panelPagoAnticipos.iniciarPanel();
			              }
			          }
			    }
			    break;
			    
			    case 1:{
			        setPanelActivo(panelSeleccionCuotasAnticiposAPagar);
				panelSeleccionCuotasAnticiposAPagar.iniciarPanel();
				permitirAvance(true);
			        permitirRetroceso(true);
			        permitirInicio(true);
			    }
			    break;
			
		    }
		    
		  
		}
		 break;
		
	    case 2: {
	            paso = 3;
		    switch (opcion) {
			case 1 :
			    {
				//Registrar Pago de cuotas de plan de pago y anticipos regulares
				if((pagoAnticipos.getCantidadAnticipos() > 0) || (pagoCuotasPlanPago.getCantidadCuotas() > 0)){
				    if(pagoAnticipos.getCantidadAnticipos() > 0){
				        int cantCuotasAdeudadas = getPlanDePagoActual().getCantCuotasAdeudadasAlCorriente();
				        int cantCuotasAPagar = pagoCuotasPlanPago.getCantidadCuotas();
				        if(cantCuotasAPagar >= cantCuotasAdeudadas){
				            subOpcion = 3;
				            setPanelActivo(panelRegistrarCuotasAnticiposAPagar);
				            panelRegistrarCuotasAnticiposAPagar.iniciarPanel();
				            permitirAvance(false);
				            permitirRetroceso(true);
				            permitirInicio(true);
				        }else{
				            Advisor.messageBox("Adeuda cuotas del plan de pago y al pagar " + cantCuotasAPagar + " cuotas no cubre la deuda de "+cantCuotasAdeudadas + " cuotas adeudadas.","Aviso");
				            paso = 2;
				            opcion = 1;
				        }	
				    }else{
				        subOpcion = 3;
				        setPanelActivo(panelRegistrarCuotasAnticiposAPagar);
				        panelRegistrarCuotasAnticiposAPagar.iniciarPanel();
				        permitirAvance(false);
				        permitirRetroceso(true);
				        permitirInicio(true);
				    }
				    
				}else{
				    Advisor.messageBox("Debe seleccionar cuotas del plan de pago o anticipos a pagar","Aviso");
				    paso = 2;
				    opcion = 1;
				}
			    }
			    break;

			case 2 :
			    {
				//Pago de anticipos regulares
				switch (subOpcion) {
				    case 1 :
					{
					    setPanelActivo(panelRegsitroAnticiposReg);
					    panelRegsitroAnticiposReg.iniciarPanel();
					    permitirRetroceso(true);
					    permitirInicio(true);
					    permitirAvance(false);
					}
					break;
				}
			    }
			    break;
			    
		        case 3 :
		            {
		                //Configuración del plan de pago
		                 setPanelActivo(panelPrepararPlanDePago);
		                 panelPrepararPlanDePago.iniciarPanel();
		                 permitirRetroceso(true);
		                 permitirInicio(true);
		                 permitirAvance(false);
		            }
		            break;
		    }
	        }
	        break;
		
	    case 3: {
	            paso = 4;
		    switch (subOpcion)  {
			case 1: {
			        setPanelActivo(panelRegistrarPlanDePagos);
			        panelRegistrarPlanDePagos.iniciarPanel();
			        permitirRetroceso(true);
			        permitirInicio(true);
			        permitirAvance(false);
			    }
			    break;
		        case 2: {
				//Pago de contado
		                setPanelActivo(panelRegistrarBoletaPagoContado);
		                panelRegistrarBoletaPagoContado.iniciarPanel();
		                permitirRetroceso(true);
		                permitirInicio(true);
		                permitirAvance(false);
		            }
		            break;
		    }
		    
	        }
	        break;
	}
	
    }
    
    public void atras(){
	System.out.println("PASO: "+paso + " - OPCION: "+opcion + " - SUBOPCION: "+subOpcion);
	switch (paso)  {
	    case 2: {
		    bien.recargarse(); // Esto lo hago por las dudas despues lo veo con mas detalle (matias)
		    paso = 1;
		    opcion = 0;
		    verPlanDePagoInmob = true;
		    verPlanDePagoTgs = true;
		    setPanelActivo(panelSeleccionBien);
		    clearImpuesto();
                    clearPagoAnticipo();
                    clearConfiguracionPlanDePago();
		    permitirAvance(true);
		    permitirRetroceso(false);
		    permitirInicio(false);
		}
		break;
	    case 3: {
	            paso = 2;
		    switch (subOpcion)  {
			case 1: {
				opcion = 2;
			        permitirAvance(true);
			        permitirRetroceso(true);
			        permitirInicio(true);
			        setPanelActivo(panelPagoAnticipos);
			        panelPagoAnticipos.iniciarPanel();
				permitirAvance(false);
			    }
			    break;
		        case 2: {
		                 opcion = 1;
		                 setPanelActivo(panelPagoAnticipos);
		                 panelPagoAnticipos.iniciarPanel();
		                 permitirAvance(false);
		                 permitirRetroceso(true);
		                 permitirInicio(true);
		            }
		            break;
		        case 3: {
		                opcion = 1;
		                setPanelActivo(panelSeleccionCuotasAnticiposAPagar);
		                panelSeleccionCuotasAnticiposAPagar.iniciarPanel();
		                permitirAvance(true);
		                permitirRetroceso(true);
		                permitirInicio(true);
		            }
		            break;
			    
		        case 4: {
		                opcion = 2;
		                setPanelActivo(panelPagoAnticipos);
		                panelPagoAnticipos.iniciarPanel();
		                permitirAvance(false);
		                permitirRetroceso(true);
		                permitirInicio(true);
		            }
		            break;
		    }
	        }
		break;
	    case 4: {
		    paso = 3;
		    switch (subOpcion)  {
			case 1: {
			        opcion = 3;
			        subOpcion = 4;
			        setPanelActivo(panelPrepararPlanDePago);
			        panelPrepararPlanDePago.iniciarPanel();
			        permitirAvance(false);
			        permitirRetroceso(true);
			        permitirInicio(true);		
			    }
			    break;
			    
		        case 2: {
		                paso = 2;
		                opcion = 2;
				subOpcion = 1;
		                setPanelActivo(panelPagoAnticipos);
		                panelPagoAnticipos.iniciarPanel();
		                permitirAvance(false);
		                permitirRetroceso(true);
		                permitirInicio(true);
		            }
		            break;
			
		    }
		    
	            
	        }
	        break;
	}
    }
    
    public void inicio(){
	paso = 1;
	opcion = 0;
	subOpcion = 0;
	bien.recargarse();//Esto lo hago por las dudas despues lo veo con mas detalle
	verPlanDePagoInmob = true;
	verPlanDePagoTgs = true;
	
        clearData();
	setPanelActivo(panelSeleccionBien);
	permitirAvance(true);
	permitirRetroceso(false);
	permitirInicio(false);
	//verPlanDePagoInmob = false;
    }
    
    private void clearData(){
        clearImpuesto();
        clearBonificacion();
        clearConfiguracionPlanDePago();
        clearContratoPlanDePago();
        impuesto = new Impuesto();
        pagoAnticipos = new PagoAnticipos();
        pagoCuotasPlanPago = new PagoCuotasPlanDePago();
        configuracionPlanDePago = new ConfiguracionPlanDePago();
        tipoPlanDePago = new TipoPlanDePago();
        moratoria = new Moratoria();
    }
    
    public void setCabecera(){
	if (bien.esCatastro()){
	    taxesMgmt.getTfContribuyente().setValue(bien.getCatastro().getDomape());
	    taxesMgmt.getTfCatastroDominio().setValue(bien.getCatastro().getCatastro());
	} else{
	    if (bien.esAutomotor()) {
	        taxesMgmt.getTfContribuyente().setValue(bien.getAutomotor().getTitular());  
	        taxesMgmt.getTfCatastroDominio().setValue(bien.getAutomotor().getDominio());	
	    } else {
	        taxesMgmt.getTfContribuyente().setValue("");  
	        taxesMgmt.getTfCatastroDominio().setValue("");
	    }
	}
	if (impuesto.getTipoDeImpuesto() != -1) {
	    taxesMgmt.getTfImpuesto().setValue(impuesto.getTipoImpuesto().getNombre());
	} else {
	    taxesMgmt.getTfImpuesto().setValue("");
	}
    }
    
    private void clearBonificacion(){
        bonificacion = new Bonificacion();    
    }
    
    public Impuesto getImpuesto(){
	return impuesto;
    }

    public Bien getBien() {
	return bien;
    }
    
    public void setBien(Bien _bien){
	bien = _bien;
    }

    public PagoAnticipos getPagoAnticipos() {
	return pagoAnticipos;
    }
    
    public void clearPagoAnticipo(){
        pagoAnticipos = new PagoAnticipos();
    }

    public void setOpcion(int opcion) {
	this.opcion = opcion;
    }

    public int getOpcion() {
	return opcion;
    }
    
    public boolean registrarBoletaAnticipos() {
        boolean grabado = false;
        if (impuesto.getTipoDeImpuesto() == 1 && bien.getIdBien() > 0) {
            loadBoletaTGS(pagoAnticipos.getCantidadAnticipos());
            if (boletaTgs.saveData() > 0) {
                boletaTgs.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipos ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaTgs.getAnio() + "-" +
                                   boletaTgs.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        } else if (impuesto.getTipoDeImpuesto() == 2 && bien.getIdBien() > 0) {
            loadBoletaInmob(pagoAnticipos.getCantidadAnticipos());
            if (boletaInmob.saveData() > 0) {
                boletaInmob.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipos ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaInmob.getAnio() + "-" +
                                   boletaInmob.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        } else if (impuesto.getTipoDeImpuesto() == 3 && bien.getIdBien() > 0) {
            loadBoletaAutomotor(pagoAnticipos.getCantidadAnticipos());
            if (boletaAutomotor.saveData() > 0) {
                boletaAutomotor.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipos ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaAutomotor.getAnio() + "-" +
                                   boletaAutomotor.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        }
        return grabado;
    }
    
    public boolean registrarBoletaAnticipos2() {
        boolean grabado = false;
        if (impuesto.getTipoDeImpuesto() == 1 && bien.getIdBien() > 0) {
            loadBoletaTGS(pagoAnticipos.getCantidadAnticipos());
            if (boletaTgs.saveData() > 0) {
                boletaTgs.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipos ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaTgs.getAnio() + "-" +
                                   boletaTgs.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        } else if (impuesto.getTipoDeImpuesto() == 2 && bien.getIdBien() > 0) {
            loadBoletaInmob(pagoAnticipos.getCantidadAnticipos());
            if (boletaInmob.saveData() > 0) {
                boletaInmob.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipos ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaInmob.getAnio() + "-" +
                                   boletaInmob.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        } else if (impuesto.getTipoDeImpuesto() == 3 && bien.getIdBien() > 0) {
            loadBoletaAutomotor2(pagoAnticipos.getCantidadAnticipos());
            if (boletaAutomotor.saveData() > 0) {
                boletaAutomotor.retrieveData();
                Advisor.messageBox("Se grabó correctamente la boleta anticipoas ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " +
                                   boletaAutomotor.getAnio() + "-" +
                                   boletaAutomotor.getNumero(), "Mensaje");
                grabado = true;
            } else {
                Advisor.messageBox("Ocurrió un problema, los datos no se grabaron!",
                                   "Error");
            }
        }
        return grabado;
    }
    
    private void loadBoletaTGS(int _cantCuotas) {
	boletaTgs = new BoletaTgs();
	boletaTgs.setIdCatastro(bien.getIdBien());
	String concepto = "";
	
	boletaTgs.setContribuyente(bien.getCatastro().getDomape());
	boletaTgs.setSeccion(bien.getCatastro().getTecsec());
	boletaTgs.setManzana(bien.getCatastro().getTecman());
	boletaTgs.setParcela(bien.getCatastro().getTecpar());
	boletaTgs.setZona("");
	boletaTgs.setCatastro(bien.getCatastro().getCatastro());
	boletaTgs.setNrocuenta(bien.getCatastro().getNroCuenta());
	if (_cantCuotas > 1) {
	 concepto = pagoAnticipos.getPeriodoDesde() + "/" +pagoAnticipos.getAnioDesde() + " al " + pagoAnticipos.getPeriodoHasta() + "/" +pagoAnticipos.getAnioHasta();
	} else {
	    concepto = pagoAnticipos.getPeriodoDesde() + "/" +pagoAnticipos.getAnioDesde();
	}
	boletaTgs.setConcepto(concepto);
	boletaTgs.setImporte(new BigDecimal( "" + pagoAnticipos.getMonto()).doubleValue());
	boletaTgs.setRecargo(new BigDecimal( "" + pagoAnticipos.getInteres()).doubleValue());
	boletaTgs.setDeducciones(new BigDecimal( "" + pagoAnticipos.getDescuento()).doubleValue());
	boletaTgs.setTotal(new BigDecimal( "" + pagoAnticipos.getMontoTotal()).doubleValue());//cambiar
	boletaTgs.setNroimpresiones(1);
	boletaTgs.setLocalidad(bien.getCatastro().getLocalidad());
	String nro = "";
	if (bien.getCatastro().getDnumro().trim().equals("0") || bien.getCatastro().getDnumro().trim().equals(""))  {
	 nro = "S/N";
	} else  {
	 nro = "Nº "+ bien.getCatastro().getDnumro();
	}
	
	boletaTgs.setIdDescuento(pagoAnticipos.getBonificacion().getIdBonificacion());
	if (pagoAnticipos.getBonificacion().getIdBonificacion() > 0) {
	    boletaTgs.setNombreDescuento(pagoAnticipos.getBonificacion().getDescripcion());
	} else {
	    boletaTgs.setNombreDescuento("");
	}
	boletaTgs.setDomicilio(bien.getCatastro().getDcalle() +" "+ nro);
	boletaTgs.setTerreno(bien.getCatastro().getTerreno());
	boletaTgs.setValedificacion(new BigDecimal( "" + Double.parseDouble(bien.getCatastro().getTervmej().toString())).doubleValue());
	boletaTgs.setValfiscal(new BigDecimal( "" + Double.parseDouble(bien.getCatastro().getValfis().toString())).doubleValue());
	boletaTgs.setUsuario(Environment.sessionUser);
	boletaTgs.setNrocuenta(bien.getCatastro().getNroCuenta());
	String apoderado = "";
	if (! bien.getCatastro().getApoderadoLastName().trim().equals(""))  {
	 apoderado = bien.getCatastro().getApoderadoLastName() +" "+ bien.getCatastro().getApoderadoName();
	}
	boletaTgs.setApoderado(apoderado);
	
	boletaTgs.setAnioDesde(pagoAnticipos.getAnioDesde());
	boletaTgs.setAnticipoDesde(pagoAnticipos.getPeriodoDesde());
	boletaTgs.setAnioHasta(pagoAnticipos.getAnioHasta());
	boletaTgs.setAnticipoHasta(pagoAnticipos.getPeriodoHasta());
	
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento", impuesto.getTipoImpuesto().getIdTipoImpuesto() +","+ bien.getCatastro().getIdCatastro() +","+ pagoAnticipos.getPeriodoHasta() +","+ pagoAnticipos.getAnioHasta());
	boletaTgs.setFechaProximoVto(proximovto);
    }

    private void loadBoletaInmob(int _cantCuotas) {
	boletaInmob = new BoletaInmob();
	String concepto = "";

	boletaInmob.setContribuyente(bien.getCatastro().getDomape());
	boletaInmob.setSeccion(bien.getCatastro().getTecsec());
	boletaInmob.setManzana(bien.getCatastro().getTecman());
	boletaInmob.setParcela(bien.getCatastro().getTecpar());
	boletaInmob.setZona("");
	boletaInmob.setCatastro(bien.getCatastro().getCatastro());
	boletaInmob.setNrocuenta(bien.getCatastro().getNroCuenta());
	if (_cantCuotas > 1) {
	 concepto = pagoAnticipos.getPeriodoDesde() + "/" +pagoAnticipos.getAnioDesde() + " al " + pagoAnticipos.getPeriodoHasta() + "/" +pagoAnticipos.getAnioHasta();
	} else {
	    concepto = pagoAnticipos.getPeriodoDesde() + "/" +pagoAnticipos.getAnioDesde();
	}
	boletaInmob.setConcepto(concepto);
	boletaInmob.setImporte(new BigDecimal( "" + pagoAnticipos.getMonto()).doubleValue());
	boletaInmob.setRecargo(new BigDecimal( "" + pagoAnticipos.getInteres()).doubleValue());
	boletaInmob.setDeducciones(new BigDecimal( "" + pagoAnticipos.getDescuento()).doubleValue());
	boletaInmob.setTotal(new BigDecimal( "" + pagoAnticipos.getMontoTotal()).doubleValue());
	boletaInmob.setNroimpresiones(1);
	boletaInmob.setLocalidad(bien.getCatastro().getLocalidad());
	String nro = "";
	if (bien.getCatastro().getDnumro().trim().equals("0") || bien.getCatastro().getDnumro().trim().equals("")) {
	    nro = "S/N";
	} else {
	    nro = "Nº " + bien.getCatastro().getDnumro();
	}
	boletaInmob.setIdDescuento(pagoAnticipos.getBonificacion().getIdBonificacion());
	if (pagoAnticipos.getBonificacion().getIdBonificacion() > 0) {
	    boletaInmob.setNombreDescuento(pagoAnticipos.getBonificacion().getDescripcion());
	} else {
	    boletaInmob.setNombreDescuento("");
	}
	boletaInmob.setDomicilio(bien.getCatastro().getDcalle() + " " + nro);
	boletaInmob.setTerreno(bien.getCatastro().getTerreno());
	boletaInmob.setValedificacion(new BigDecimal( "" + Double.parseDouble(bien.getCatastro().getTervmej().toString())).doubleValue());
	boletaInmob.setValfiscal(new BigDecimal( "" + Double.parseDouble(bien.getCatastro().getValfis().toString())).doubleValue());
	boletaInmob.setUsuario(Environment.sessionUser);
	String apoderado = "";
	if (!bien.getCatastro().getApoderadoLastName().trim().equals("")) {
	    apoderado = bien.getCatastro().getApoderadoLastName().toUpperCase() + " " + bien.getCatastro().getApoderadoName().toUpperCase();
	}
	boletaInmob.setApoderado(apoderado);
	boletaInmob.setIdCatastro(bien.getCatastro().getIdCatastro());
	boletaInmob.setAnioDesde(pagoAnticipos.getAnioDesde());
	boletaInmob.setAnticipoDesde(pagoAnticipos.getPeriodoDesde());
	boletaInmob.setAnioHasta(pagoAnticipos.getAnioHasta());
	boletaInmob.setAnticipoHasta(pagoAnticipos.getPeriodoHasta());
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento", impuesto.getTipoImpuesto().getIdTipoImpuesto() +","+ bien.getCatastro().getIdCatastro() +","+ pagoAnticipos.getPeriodoHasta() +","+ pagoAnticipos.getAnioHasta());
	boletaInmob.setFechaProximoVto(proximovto);
    }

    private void loadBoletaAutomotor(int _cantCuotas) {
	boletaAutomotor = new BoletaAutomotor();
	String concepto = "";

	boletaAutomotor.setIdAutomotor(bien.getAutomotor().getIdAutomotor());
	boletaAutomotor.setTitular(bien.getAutomotor().getTitular());
	boletaAutomotor.setTipo(bien.getAutomotor().getTipo());
	boletaAutomotor.setMarca(bien.getAutomotor().getMarca());
	boletaAutomotor.setMotor(bien.getAutomotor().getNromotor());
	boletaAutomotor.setCategoria(bien.getAutomotor().getIdtipocategoria());
	boletaAutomotor.setModelo("" + bien.getAutomotor().getModelo());
	boletaAutomotor.setCuota(bien.getAutomotor().getCuota());
	boletaAutomotor.setIdDominio(bien.getAutomotor().getIddominio());
	boletaAutomotor.setDominio(bien.getAutomotor().getDominio());
	boletaAutomotor.setDomicilio(bien.getAutomotor().getDomicilio());
	boletaAutomotor.setNrocuenta(0);
	
        //corregir para la boleta
        
	if (_cantCuotas > 1) {
	 //concepto = ((pagoAnticipos.getPeriodoDesde() + 1)/2) + "/" +pagoAnticipos.getAnioDesde() + " al " + ((pagoAnticipos.getPeriodoHasta() + 1) /2) + "/" +pagoAnticipos.getAnioHasta();
	    concepto = (pagoAnticipos.getPeriodoDesde()) + "/" +pagoAnticipos.getAnioDesde() + " al " + (pagoAnticipos.getPeriodoHasta() ) + "/" +pagoAnticipos.getAnioHasta();
	} else {
	   // concepto = ((pagoAnticipos.getPeriodoDesde() + 1)/2) + "/" +pagoAnticipos.getAnioDesde();
	   concepto = (pagoAnticipos.getPeriodoDesde() ) + "/" +pagoAnticipos.getAnioDesde();
	}
        
	boletaAutomotor.setConcepto(concepto);
        boletaAutomotor.setCantAnticipos(_cantCuotas);
	boletaAutomotor.setInformacion("");
	boletaAutomotor.setValorAnual(new BigDecimal("" + bien.getAutomotor().getValoranual()).doubleValue());
	boletaAutomotor.setImporte(new BigDecimal("" + pagoAnticipos.getMonto()).doubleValue());
	boletaAutomotor.setRecargo(new BigDecimal("" + pagoAnticipos.getInteres()).doubleValue());
	boletaAutomotor.setDeducciones(new BigDecimal("" + pagoAnticipos.getDescuento()).doubleValue());
	boletaAutomotor.setTotal(new BigDecimal("" + pagoAnticipos.getMontoTotal()).doubleValue());
	boletaAutomotor.setNroimpresiones(1);
	boletaAutomotor.setLocalidad("");
	
	boletaAutomotor.setIdDescuento(pagoAnticipos.getBonificacion().getIdBonificacion());
	if (pagoAnticipos.getBonificacion().getIdBonificacion() > 0) {
	    boletaAutomotor.setNombreDescuento(pagoAnticipos.getBonificacion().getDescripcion());
	} else {
	    boletaAutomotor.setNombreDescuento("");
	}
	boletaAutomotor.setUsuario(Environment.sessionUser);
	boletaAutomotor.setAnioDesde(pagoAnticipos.getAnioDesde());
	//boletaAutomotor.setAnticipoDesde((pagoAnticipos.getPeriodoDesde() + 1) / 2);
	boletaAutomotor.setAnticipoDesde(pagoAnticipos.getPeriodoDesde());
	boletaAutomotor.setAnioHasta(pagoAnticipos.getAnioHasta());
	//boletaAutomotor.setAnticipoHasta((pagoAnticipos.getPeriodoHasta() + 1) / 2);
	boletaAutomotor.setAnticipoHasta(pagoAnticipos.getPeriodoHasta());
	String proximovto = LibSQL.getString("taxes.getProximoVencimiento", impuesto.getTipoImpuesto().getIdTipoImpuesto() +","+ bien.getAutomotor().getIdAutomotor() +","+ ((pagoAnticipos.getPeriodoHasta() * 2) - 1) +","+ pagoAnticipos.getAnioHasta());
	boletaAutomotor.setFechaProximoVto(proximovto);
    }
    
    private void loadBoletaAutomotor2(int _cantCuotas) {
        boletaAutomotor = new BoletaAutomotor();
        String concepto = "";

        boletaAutomotor.setIdAutomotor(bien.getAutomotor().getIdAutomotor());
        boletaAutomotor.setTitular(bien.getAutomotor().getTitular());
        boletaAutomotor.setTipo(bien.getAutomotor().getTipo());
        boletaAutomotor.setMarca(bien.getAutomotor().getMarca());
        boletaAutomotor.setMotor(bien.getAutomotor().getNromotor());
        boletaAutomotor.setCategoria(bien.getAutomotor().getIdtipocategoria());
        boletaAutomotor.setModelo("" + bien.getAutomotor().getModelo());
        boletaAutomotor.setCuota(bien.getAutomotor().getCuota());
        boletaAutomotor.setIdDominio(bien.getAutomotor().getIddominio());
        boletaAutomotor.setDominio(bien.getAutomotor().getDominio());
        boletaAutomotor.setDomicilio(bien.getAutomotor().getDomicilio());
        boletaAutomotor.setNrocuenta(0);
        
        //corregir para la boleta
        
        if (_cantCuotas > 1) {
          concepto = ((pagoAnticipos.getPeriodoDesde() + 1)/2) + "/" +pagoAnticipos.getAnioDesde() + " al " + ((pagoAnticipos.getPeriodoHasta() + 1) /2) + "/" +pagoAnticipos.getAnioHasta();
        } else {
          concepto = ((pagoAnticipos.getPeriodoDesde() + 1)/2) + "/" +pagoAnticipos.getAnioDesde();
        }
        
        boletaAutomotor.setConcepto(concepto);
        boletaAutomotor.setCantAnticipos(_cantCuotas);
        boletaAutomotor.setInformacion("");
        boletaAutomotor.setValorAnual(new BigDecimal("" + bien.getAutomotor().getValoranual()).doubleValue());
        boletaAutomotor.setImporte(new BigDecimal("" + pagoAnticipos.getMonto()).doubleValue());
        boletaAutomotor.setRecargo(new BigDecimal("" + pagoAnticipos.getInteres()).doubleValue());
        boletaAutomotor.setDeducciones(new BigDecimal("" + pagoAnticipos.getDescuento()).doubleValue());
        boletaAutomotor.setTotal(new BigDecimal("" + pagoAnticipos.getMontoTotal()).doubleValue());
        boletaAutomotor.setNroimpresiones(1);
        boletaAutomotor.setLocalidad("");
        
        boletaAutomotor.setIdDescuento(pagoAnticipos.getBonificacion().getIdBonificacion());
        if (pagoAnticipos.getBonificacion().getIdBonificacion() > 0) {
            boletaAutomotor.setNombreDescuento(pagoAnticipos.getBonificacion().getDescripcion());
        } else {
            boletaAutomotor.setNombreDescuento("");
        }
        boletaAutomotor.setUsuario(Environment.sessionUser);
        boletaAutomotor.setAnioDesde(pagoAnticipos.getAnioDesde());
        boletaAutomotor.setAnticipoDesde((pagoAnticipos.getPeriodoDesde() + 1) / 2);
        boletaAutomotor.setAnioHasta(pagoAnticipos.getAnioHasta());
        boletaAutomotor.setAnticipoHasta((pagoAnticipos.getPeriodoHasta() + 1) / 2);
        String proximovto = LibSQL.getString("taxes.getProximoVencimiento", impuesto.getTipoImpuesto().getIdTipoImpuesto() +","+ bien.getAutomotor().getIdAutomotor() +","+ pagoAnticipos.getPeriodoHasta() +","+ pagoAnticipos.getAnioHasta());
        boletaAutomotor.setFechaProximoVto(proximovto);
    }
    
    public boolean registrarBoletaCuotasPlanDePago(){   
	boolean grabado = false;
	int cantCuotas = pagoCuotasPlanPago.getCantidadCuotas();
	if (cantCuotas > 0) {
	    loadBoletaMoratoria(cantCuotas);
	    if (boletaPlanesDePago.saveData2() > 0) {
	        boletaPlanesDePago.retrieveData();
 		Advisor.messageBox("Se grabó correctamente la boleta P.P ("+ this.getImpuesto().getTipoImpuesto().getNombre()+") con el número: " + boletaPlanesDePago.getAnio() + "-" + boletaPlanesDePago.getNumero(), "Mensaje");
		grabado = true;
	    } else {
		Advisor.messageBox("Ocurrió un error al grabar los datos!", "Error");
	    }
	} else {
	    Advisor.messageBox("No hay cuotas a pagar", "Aviso");
	}
	return grabado;
    }
    
    private void loadBoletaMoratoria(int _cantCuotas) {
	boletaPlanesDePago = new BoletaPlanesDePago();
	String concepto = "";
	String nro="";
	String apoderado = "";
	boletaPlanesDePago.setCantCuotas(_cantCuotas);
	boletaPlanesDePago.setIdTipoImpuesto(impuesto.getTipoDeImpuesto());
        tipoPlanDePagoSeleccionado = impuesto.getTipoDeImpuesto();
	boletaPlanesDePago.setIdPlanDePago(getPlanDePagoActual().getIdplandepago());
	boletaPlanesDePago.setPorcentajeIntFin(getPlanDePagoActual().getPorcentajeIntFin());
	boletaPlanesDePago.setMontoIntFin(getPlanDePagoActual().getMontoInteresFinanciacion());
	if (impuesto.getTipoDeImpuesto() == 1 || impuesto.getTipoDeImpuesto() == 2)  {    // Impuesto TGS ó Inmobiliario
	    boletaPlanesDePago.setIdBien(bien.getCatastro().getIdCatastro());
	    boletaPlanesDePago.setContribuyente(bien.getCatastro().getDomape());
	    boletaPlanesDePago.setSeccion(bien.getCatastro().getTecsec());
	    boletaPlanesDePago.setManzana(bien.getCatastro().getTecman());
	    boletaPlanesDePago.setParcela(bien.getCatastro().getTecpar());
	    boletaPlanesDePago.setLocalidad(bien.getCatastro().getLocalidad());
	    boletaPlanesDePago.setZona("");
	    boletaPlanesDePago.setDomicilio(bien.getCatastro().getDcalle() +" "+ bien.getCatastro().getDnumro());
	    boletaPlanesDePago.setTerreno(bien.getCatastro().getTerreno());
	    boletaPlanesDePago.setCatastro(bien.getCatastro().getCatastro());
	    boletaPlanesDePago.setCategoria("" + bien.getCatastro().getIdCategoria());
	    
	    String tervMej = bien.getCatastro().getTervmej().toString();
	    if (!tervMej.equals(""))  {
		boletaPlanesDePago.setValedificacion(Double.parseDouble(bien.getCatastro().getTervmej().toString()));     
	    } else  {
		boletaPlanesDePago.setValedificacion(0.0);
	    }
	    String valFis = bien.getCatastro().getValfis().toString();
	    if (!tervMej.equals(""))  {
		boletaPlanesDePago.setValfiscal(Double.parseDouble(valFis));
	    } else  {
		boletaPlanesDePago.setValfiscal(0.0);
	    }
	    
	    boletaPlanesDePago.setNroCuenta(bien.getCatastro().getNroCuenta());
	    
	     if (impuesto.getTipoDeImpuesto() == 1)  {
		concepto = getPlanDePagoActual().getNombre();
	    } else  {
	        concepto = getPlanDePagoActual().getNombre();
	    }
	    
	     
	    if (!bien.getCatastro().getApoderadoLastName().trim().equals(""))  {
	     apoderado = bien.getCatastro().getApoderadoLastName() +" "+ bien.getCatastro().getApoderadoName();
	    }
	    if (bien.getCatastro().getDnumro().trim().equals("0") || bien.getCatastro().getDnumro().trim().equals(""))  {
		nro = "S/N";
	    } else  {
		nro = "Nº "+ bien.getCatastro().getDnumro();
	    }   

	} else  { /** Cargar datos para Automotor */
	    boletaPlanesDePago.setIdBien(bien.getAutomotor().getIdAutomotor());
	    boletaPlanesDePago.setContribuyente(bien.getAutomotor().getTitular());
	    boletaPlanesDePago.setDomicilio(bien.getAutomotor().getDomicilio());
	    boletaPlanesDePago.setDominio(bien.getAutomotor().getDominio());
	    boletaPlanesDePago.setCategoria(bien.getAutomotor().getCategoria());
	    boletaPlanesDePago.setDni(bien.getAutomotor().getDni());
	    boletaPlanesDePago.setMarca(bien.getAutomotor().getMarca());
	    boletaPlanesDePago.setModelo(bien.getAutomotor().getModelo());
	    boletaPlanesDePago.setNromotor(bien.getAutomotor().getNromotor());
	    boletaPlanesDePago.setTipoautomotor(bien.getAutomotor().getTipo());
	    boletaPlanesDePago.setDni(bien.getAutomotor().getDni());
	    concepto = getPlanDePagoActual().getNombre();
	}
	
	boletaPlanesDePago.setConcepto(concepto);
	boletaPlanesDePago.setImporte(pagoCuotasPlanPago.getMontoTotal());
	boletaPlanesDePago.setUsuario(Environment.sessionUser);
	boletaPlanesDePago.setApoderado(apoderado);
    }
    
    public boolean registrarPlanDePago(){
	boolean resultado = false;
	setMoratoria();
	if (moratoria.saveData2()> 0)  {
	     bien.recargarse();
	     resultado = true;
	} else  {
	     resultado = false;
	}
	return resultado;
    }
    
    private void setMoratoria(){
	moratoria = new Moratoria();
        //Datos independiente si el plan de pago se hizo con una entrega previa
        moratoria.setIdBien(bien.getIdBien());
        moratoria.setIdTipoPlanDePago(tipoPlanDePago.getIdTipoPlanDePago());
        moratoria.setIdTipoImpuesto(impuesto.getTipoImpuesto().getIdTipoImpuesto());
        moratoria.setBonificacionContado(0.0);
        moratoria.setNombreBonificacionMoratoria("Bonificación de Intereses");
        
        BigDecimal descuento = new BigDecimal("0");
        descuento = descuento.add(new BigDecimal("" + configuracionPlanDePago.getTotalAnticiposAdeudadosPuro()));
        descuento = descuento.subtract(new BigDecimal("" + configuracionPlanDePago.getTotalAnticiposAdeudados()));
        moratoria.setBonificacion(Math.abs(descuento.doubleValue()));
        
        moratoria.setNombreBonificacion(bonificacion.getNombre());
        moratoria.setIdBonificacion(bonificacion.getIdBonificacion());
        moratoria.setContado(configuracionPlanDePago.isPagoContado());
        moratoria.setBonificacionContado(configuracionPlanDePago.getMontoDescuentoPagoContado());
        moratoria.setNombre(tipoPlanDePago.getNombre());
        moratoria.setBonificacionMoratoria(configuracionPlanDePago.getMontoCondonacionIntereses()); // no se bien si es comun para los dos casos
        moratoria.setCantAnticipos(configuracionPlanDePago.getCantidadAnticipos()); // no se bien si es comun para los dos casos
        moratoria.setVigente(false);
        
        if (configuracionPlanDePago.getPagoAnticipado() > 0) { 
            //Cargo los valores según la configuración del plan de pago con entrega
            descuento = new BigDecimal("0");
            descuento = descuento.add(new BigDecimal("" + configuracionPlanDePago.getTotalAnticiposAdeudados()));
            descuento = descuento.subtract(new BigDecimal("" + configuracionPlanDePago.getTotalAnticiposAdeudadosPuro()));
            descuento = descuento.subtract(new BigDecimal("" + configuracionPlanDePago.getTotalBaseCubiertoEntrega()));
            moratoria.setBonificacion(descuento.doubleValue());
            
            moratoria.setEntrega(configuracionPlanDePago.getPagoAnticipado());
            
            if (Math.abs(descuento.doubleValue()) > 0.0 ) {
                moratoria.setMontoBase(configuracionPlanDePago.getMontoBasePPEntregaSinDto());
                moratoria.setInteres(configuracionPlanDePago.getInteresBasePPEntregaSinDto());
                moratoria.setSubtotal(configuracionPlanDePago.getTotalBasePPEntregaSinDto());    
                
                descuento = new BigDecimal("0");
                descuento = descuento.add(new BigDecimal("" + configuracionPlanDePago.getTotalBasePPEntregaSinDto()));
                descuento = descuento.add(new BigDecimal("" + configuracionPlanDePago.getMontoInteresFinanciacion()));
                descuento = descuento.subtract(new BigDecimal("" + configuracionPlanDePago.getTotal()));
                descuento = descuento.subtract(new BigDecimal("" + configuracionPlanDePago.getMontoCondonacionIntereses()));
                moratoria.setBonificacion(descuento.doubleValue());
                configuracionPlanDePago.setMontoBonificacion(descuento.doubleValue());
                
                System.out.println("MONTO PURO =  " +  configuracionPlanDePago.getMontoBasePPEntregaSinDto() );
                System.out.println("INTERES PURO =  " +  configuracionPlanDePago.getInteresBasePPEntregaSinDto() );
                System.out.println("MONTO PURO =  " +  configuracionPlanDePago.getTotalBasePPEntregaSinDto() );
            } else {
                moratoria.setMontoBase(configuracionPlanDePago.getMontoBasePPEntrega());
                moratoria.setInteres(configuracionPlanDePago.getInteresBasePPEntrega());
                moratoria.setSubtotal(configuracionPlanDePago.getTotalBasePPEntrega());    
            }
            
            moratoria.setCantCuotas(configuracionPlanDePago.getCantidadCuotas());
            if (bien.esAutomotor()) {
                moratoria.setAnticipoDesde((configuracionPlanDePago.getMenorAnticipoPPEntrega() + 1) /2 );    
                moratoria.setAnticipoHasta((configuracionPlanDePago.getMayorAnticipoPPEntrega() + 1 ) / 2);
            } else {
                moratoria.setAnticipoDesde(configuracionPlanDePago.getMenorAnticipoPPEntrega());    
                moratoria.setAnticipoHasta(configuracionPlanDePago.getMayorAnticipoPPEntrega());
            }
            
            moratoria.setAnioDesde(configuracionPlanDePago.getMenorAnioPPEntrega());
            moratoria.setAnioHasta(configuracionPlanDePago.getMayorAnioPPEntrega());
            moratoria.setMaxCuotasVencidas(0);
            moratoria.setDiaVencimiento(configuracionPlanDePago.getDiaVencimiento());
            moratoria.setMontoCuotas(configuracionPlanDePago.getValorPrimerCuota());            
            moratoria.setMontoTotal(configuracionPlanDePago.getTotal()); // Ver si es el valor correcto a levantar
            moratoria.setPorcentajeInteresFinanciacion(configuracionPlanDePago.getPorcentajeInteresFinanciacion());
            moratoria.setMontoInteresFinanciacion(configuracionPlanDePago.getMontoInteresFinanciacion());
            moratoria.setPorcentDtoPagoContado(0.0);
            moratoria.setPorcentajeCondonacionIntereses(configuracionPlanDePago.getPorcentajeCondonacionIntereses() / 100);
            moratoria.setMontoCondonacionIntereses(configuracionPlanDePago.getMontoCondonacionIntereses());
            moratoria.setMontoPrimerCuota(configuracionPlanDePago.getValorPrimerCuota());
            moratoria.setMontoCuotasRestantes(configuracionPlanDePago.getValorCuotasRestantes());
        } else {
            //Cargo los valores según la configuración del plan de pago sin entrega
            moratoria.setMontoBase(configuracionPlanDePago.getCapitalPuro());
            moratoria.setInteres(configuracionPlanDePago.getInteresPuro());
            moratoria.setSubtotal(configuracionPlanDePago.getTotalAnticiposAdeudadosPuro());
            moratoria.setBonificacionMoratoria(configuracionPlanDePago.getMontoCondonacionIntereses());
            moratoria.setCantAnticipos(configuracionPlanDePago.getCantidadAnticipos());
            moratoria.setCantCuotas(configuracionPlanDePago.getCantidadCuotas());
            moratoria.setAnticipoDesde(configuracionPlanDePago.getmenorAnticipo());
            moratoria.setAnioDesde(configuracionPlanDePago.getMenorAnio());
            moratoria.setAnticipoHasta(configuracionPlanDePago.getmayorAnticipo());
            moratoria.setAnioHasta(configuracionPlanDePago.getMayorAnio());
            moratoria.setMaxCuotasVencidas(tipoPlanDePago.getMaxCuotasVencidas());
            moratoria.setDiaVencimiento(configuracionPlanDePago.getDiaVencimiento());
            moratoria.setMontoCuotas(configuracionPlanDePago.getValorPrimerCuota());            
            moratoria.setMontoTotal(configuracionPlanDePago.getTotal());
            moratoria.setPorcentajeInteresFinanciacion(configuracionPlanDePago.getPorcentajeInteresFinanciacion());
            moratoria.setMontoInteresFinanciacion(configuracionPlanDePago.getMontoInteresFinanciacion());
            moratoria.setPorcentDtoPagoContado(0.0);
            moratoria.setPorcentajeCondonacionIntereses(configuracionPlanDePago.getPorcentajeCondonacionIntereses() / 100);
            moratoria.setMontoCondonacionIntereses(configuracionPlanDePago.getMontoCondonacionIntereses());
            moratoria.setMontoPrimerCuota(configuracionPlanDePago.getValorPrimerCuota());
            moratoria.setMontoCuotasRestantes(configuracionPlanDePago.getValorCuotasRestantes());
        }
    }
    
    public boolean registrarMoratoria(){
	boolean resultado = false;
	/*setMoratoria();
	coordinador.getTipoPlanDePago().setMoratoria(moratoria);
	coordinador.setMoratoria(moratoria);
	if (coordinador.getMoratoria().saveData()> 0)  {
	     ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setCoordinador(coordinador);
	     ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().refresh();
	     ((TaxesMain)getTabContainer()).getPayMoratoriumFeesMgmt().setComboCuotas();
	     ((TaxesMain)getTabContainer()).setEnabledPanels(3);
	} else  {
	     Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso!");
	}*/
	return resultado;
    }

    public void setSubOpcion(int subOpcion) {
	this.subOpcion = subOpcion;
    }

    public int getSubOpcion() {
	return subOpcion;
    }

    public void setPaso(int paso) {
	this.paso = paso;
    }

    public int getPaso() {
	return paso;
    }

    public void setVerPlanDePagoInmob(boolean verPlanDePagoInmob) {
	this.verPlanDePagoInmob = verPlanDePagoInmob;
    }

    public boolean isVerPlanDePagoInmob() {
	return verPlanDePagoInmob;
    }
    
    public void permitirAvance(boolean _permiso){
	taxesMgmt.getBtnNext().setEnabled(_permiso);
    }
    
    public void permitirRetroceso(boolean _permiso){
	taxesMgmt.getBtnBack().setEnabled(_permiso);
    }
    
    public void permitirInicio(boolean _permiso){
	taxesMgmt.getBtnFirst().setEnabled(_permiso);
    }

    public PagoCuotasPlanDePago getPagoCuotasPlanPago() {
	return pagoCuotasPlanPago;
    }
    
    public void clearImpuesto(){
	impuesto = new Impuesto();
	setCabecera();
    }
    
    public void clearConfiguracionPlanDePago(){
        configuracionPlanDePago = new ConfiguracionPlanDePago();
    }
    
    public void clearContratoPlanDePago(){
        contratoPlanDePago = new ContratoPlanDePago();
    }
    
    public void clearPagoContado(){
        pagoContado = new PagoContado();
    }

    public void setBonificacion(Bonificacion bonificacion) {
	this.bonificacion = bonificacion;
    }

    public Bonificacion getBonificacion() {
	return bonificacion;
    }

    public void setBoletaPlanDePago(BoletaPlanesDePago boletaPlanDePago) {
	this.boletaPlanDePago = boletaPlanDePago;
    }

    public BoletaPlanesDePago getBoletaPlanDePago() {
	return boletaPlanDePago;
    }
    
    public PlanDePago getPlanDePagoActual(){
	PlanDePago planDePago = null;
	if (tipoPlanDePagoSeleccionado == TIPO_PLAN_DE_PAGO_TGS){
	    planDePago = bien.getPlanPagoTGS();
	}else{
	    if (tipoPlanDePagoSeleccionado == TIPO_PLAN_DE_PAGO_INMOB){
	        planDePago = bien.getPlanPagoInmob();
	    }else{
	        planDePago = bien.getPlanPagoAutomotor();
	    }
	}
	return planDePago;
    }

    public void setVerPlanDePagoTgs(boolean verPlanDePagoTgs) {
	this.verPlanDePagoTgs = verPlanDePagoTgs;
    }

    public boolean isVerPlanDePagoTgs() {
	return verPlanDePagoTgs;
    }

    public void setConfiguracionPlanDePago(ConfiguracionPlanDePago configuracionPlanDePago) {
	this.configuracionPlanDePago = configuracionPlanDePago;
    }

    public ConfiguracionPlanDePago getConfiguracionPlanDePago() {
	return configuracionPlanDePago;
    }

    public void setTipoPlanDePago(TipoPlanDePago tipoPlanDePago) {
	this.tipoPlanDePago = tipoPlanDePago;
    }

    public TipoPlanDePago getTipoPlanDePago() {
	return tipoPlanDePago;
    }

    public void setContratoPlanDePago(ContratoPlanDePago contratoPlanDePago) {
        this.contratoPlanDePago = contratoPlanDePago;
    }

    public ContratoPlanDePago getContratoPlanDePago() {
        return contratoPlanDePago;
    }
    
    public void generarContratoPlanDePago(){
        contratoPlanDePago.setBoletaMoratoria(boletaPlanDePago);
        contratoPlanDePago.setNroContrato("" + moratoria.getIdPlanDePago());
        if (bien.esCatastro()) {
            contratoPlanDePago.setContribuyente("" + bien.getCatastro().getDomape());
            contratoPlanDePago.setDomicilio("" + bien.getCatastro().getDcalle() + " " + bien.getCatastro().getDnumro());
        } else {
            contratoPlanDePago.setContribuyente("" + bien.getAutomotor().getTitular());
            contratoPlanDePago.setDomicilio("" + bien.getAutomotor().getDomicilio());
        }
        contratoPlanDePago.setTipoImpuesto("" + impuesto.getTipoImpuesto().getIdTipoImpuesto());
        contratoPlanDePago.saveData();
    }

    public void setPagoContado(PagoContado pagoContado) {
        this.pagoContado = pagoContado;
    }

    public PagoContado getPagoContado() {
        return pagoContado;
    }
    
    public void mostrarProblema(){
        taxesMgmt.mostrarProblema();
    }

    public Moratoria getMoratoria() {
        return moratoria;
    }
}
