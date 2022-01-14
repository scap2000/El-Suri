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
 * TaxesMain.java
 *
 * */
package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.Dimension;

import org.digitall.apps.taxes.interfases.feesadmin.TaxPayerList;
import org.digitall.apps.taxes.interfases.feesadmin.TaxesMgmt;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;


public class TaxesMain extends BasicTabContainer {

    private TaxPayerList taxPayerList = new TaxPayerList();
    private TaxesMgmt taxesMgmt = new TaxesMgmt();
    private PlansOfPaymentMgmt plansOfPaymentMgmt = new PlansOfPaymentMgmt();
    private PayMoratoriumFeesMgmt payMoratoriumFeesMgmt = new PayMoratoriumFeesMgmt();
    private PayFeesMgmt payFeesMgmt = new PayFeesMgmt();
    private PayAnnualFeesMgmt payAnnualFeesMgmt = new PayAnnualFeesMgmt();

    public TaxesMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	addTab(taxPayerList);
	addTab(taxesMgmt);
        addTab(plansOfPaymentMgmt);
	addTab(payMoratoriumFeesMgmt);
	addTab(payFeesMgmt);
	addTab(payAnnualFeesMgmt);
	setTitle("");
	getTabbedPane().setEnabledAt(0,true);
	getTabbedPane().setEnabledAt(1,false);
	getTabbedPane().setEnabledAt(2,false);
	getTabbedPane().setEnabledAt(3,false);
	getTabbedPane().setEnabledAt(4,false);
	getTabbedPane().setEnabledAt(5,false);
	setSelectedTab(0);
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Haga doble click sobre un Catastro/Dominio");
	    } else if (getSelectedTab() == 1) {
	        /* 2009-09-24 (santiago) Mensaje para el codificador
	         * No hay un botón "Config. Moratoria" ni "Grabar Boleta"
	         * o activarPestania(int _index)
	         * */
		getParentInternalFrame().setInfo("Seleccione los anticipos a pagar y presione el botón \"Config. Moratoria/Plan de Pago\" o \"Grabar Boleta\"");
	    } else if (getSelectedTab() == 2) {
	        /* 2009-09-24 (santiago) Mensaje para el codificador
	         * Debería decir Moratoria/Plan de Pago
	         * */
		getParentInternalFrame().setInfo("Seleccione la forma de Pago y los descuentos y presione el botón \"Grabar Moratoria/Plan de Pago\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Haga doble click sobre Catastro/Dominio");
    }

    public TaxPayerList getTaxPayerList() {
	return taxPayerList;
    }

    public TaxesMgmt getTaxesMgmt() {
	return taxesMgmt;
    }

    public PlansOfPaymentMgmt plansOfPaymentMgmt() {
        return plansOfPaymentMgmt;
    }
    
    public PayMoratoriumFeesMgmt getPayMoratoriumFeesMgmt() {
	return payMoratoriumFeesMgmt;
    }
    
    public PayFeesMgmt getPayFeesMgmt() {
	return payFeesMgmt;
    }
    
    public PayAnnualFeesMgmt getPayAnnualFeesMgmt() {
	return payAnnualFeesMgmt;
    }

    
    public void setEnabledPanels(int _solapa) {
	switch (_solapa)  {
	    case 0: {	
		    getTabbedPane().setEnabledAt(0,true);
		    getTabbedPane().setEnabledAt(1,false);
		    getTabbedPane().setEnabledAt(2,false);
		    getTabbedPane().setEnabledAt(3,false);
		    getTabbedPane().setEnabledAt(4,false);
		    getTabbedPane().setEnabledAt(5,false);
		    setSelectedTab(0);
		}
		break;
	    case 1: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,true);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,false);
	            getTabbedPane().setEnabledAt(5,false);
	            setSelectedTab(1);
	        }
	        break;
	    case 2: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,true);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,false);
	            getTabbedPane().setEnabledAt(5,false);
	            setSelectedTab(2);
	        }
	        break;
	    case 3: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,true);
	            getTabbedPane().setEnabledAt(4,false);
	            getTabbedPane().setEnabledAt(5,false);
	            setSelectedTab(3);
	        }
	        break;
	    case 4: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,true);
	            getTabbedPane().setEnabledAt(5,false);
	            setSelectedTab(4);
	        }
	        break;
	    case 5: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,false);
	            getTabbedPane().setEnabledAt(5,true);
	            setSelectedTab(5);
	        }
	        break;
	}
    }
}



