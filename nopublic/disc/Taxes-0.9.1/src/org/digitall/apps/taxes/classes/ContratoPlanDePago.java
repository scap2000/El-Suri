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
 * ContratoPlanDePago.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ContratoPlanDePago {

    private int idContrato = -1;
    private int idBoletaPlanDePago = -1;
    private int cantPeriodos = 0;
    private String nroContrato = "";
    private int cantcuotas = -1;
    private double montoCuotas = 0.0;
    private String contribuyente = "";
    private double importe = 0.0;
    private double interes = 0.0;
    private double bonificacionMoratoria = 0.0;
    private double bonificacionespecial = 0.0;
    private double montoTotal = 0.0;
    private String nombreBonificacionEspecial = "";
    private String fechaImpresion = "";
    private String fechaAceptacion = "";
    private String domicilio = "";
    private String conceptoPeriodos = "";
    private String tipoImpuesto = "";
    private String barcode = "";
    
    private int idPlandePago = -1;
    private String rescision = "";
    private String ordenanza = "";
    
    private double porcentajeIntFin = 0.0;
    private double montoIntFin = 0.0;
    private boolean contado = false;
    private double porcentajeBonificacionContado = 0.0;
    private double bonificacionContado = 0.0;
    
    private Moratoria moratoria;
    private BoletaPlanesDePago boletaMoratoria;
    
    private boolean impreso = false;
    

    public ContratoPlanDePago() {
    }

    public void setidContrato(int idContrato) {
	this.idContrato = idContrato;
    }

    public int getidContrato() {
	return idContrato;
    }

    public void setCantPeriodos(int cantPeriodos) {
	this.cantPeriodos = cantPeriodos;
    }

    public int getCantPeriodos() {
	return cantPeriodos;
    }

    public void setCantcuotas(int cantcuotas) {
	this.cantcuotas = cantcuotas;
    }

    public int getCantcuotas() {
	return cantcuotas;
    }

    public void setMontoCuotas(double montoCuotas) {
	this.montoCuotas = montoCuotas;
    }

    public double getMontoCuotas() {
	return montoCuotas;
    }

    public void setContribuyente(String contribuyente) {
	this.contribuyente = contribuyente;
    }

    public String getContribuyente() {
	return contribuyente;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public double getImporte() {
	return importe;
    }

    public void setInteres(double interes) {
	this.interes = interes;
    }

    public double getInteres() {
	return interes;
    }

    public void setBonificacionMoratoria(double bonificacionMoratoria) {
	this.bonificacionMoratoria = bonificacionMoratoria;
    }

    public double getBonificacionMoratoria() {
	return bonificacionMoratoria;
    }

    public void setBonificacionespecial(double bonificacionespecial) {
	this.bonificacionespecial = bonificacionespecial;
    }

    public double getBonificacionespecial() {
	return bonificacionespecial;
    }

    public void setMontoTotal(double montoTotal) {
	this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public void setNombreBonificacionEspecial(String nombreBonificacionEspecial) {
	this.nombreBonificacionEspecial = nombreBonificacionEspecial;
    }

    public String getNombreBonificacionEspecial() {
	return nombreBonificacionEspecial;
    }

    public void setIdBoletaPlanDePago(int idBoletaPlanDePago) {
	this.idBoletaPlanDePago = idBoletaPlanDePago;
	boletaMoratoria = new BoletaPlanesDePago();
	boletaMoratoria.setIdBoletaPlanDePago(idBoletaPlanDePago);
    }

    public int getIdBoletaPlanDePago() {
	return idBoletaPlanDePago;
    }

    public void setMoratoria(Moratoria moratoria) {
	this.moratoria = moratoria;
    }

    public Moratoria getMoratoria() {
	return moratoria;
    }

    public void setBoletaMoratoria(BoletaPlanesDePago boletaMoratoria) {
	this.boletaMoratoria = boletaMoratoria;
    }

    public BoletaPlanesDePago getBoletaMoratoria() {
	return boletaMoratoria;
    }
    
    public int saveData() {
	String params = "";
	int result = -1;
	    params = idBoletaPlanDePago +","+ nroContrato +",'"+ contribuyente +"','"+ domicilio + "'," + cantPeriodos + ",'" + 
		     conceptoPeriodos + "'," + importe + ","+ interes +","+ bonificacionMoratoria +","+ bonificacionespecial +","+ 
		     montoTotal +","+ cantcuotas +","+ montoCuotas +",'"+ tipoImpuesto +"',"+ porcentajeIntFin +","+ montoIntFin;
	if (idContrato == -1)  {
	    result = LibSQL.getInt("taxes.addContratoMoratoria",params);
	    idContrato = result;
	} else  {
	    params = idContrato +","+ params + "," + impreso;
            System.out.println("params = " + params);
	    result = LibSQL.getInt("taxes.setContratoMoratoria",params); 
	}
	return result;
    }

    public void setNroContrato(String nroContrato) {
	this.nroContrato = nroContrato;
    }

    public String getNroContrato() {
	return nroContrato;
    }

    public void setFechaImpresion(String fechaImpresion) {
	this.fechaImpresion = fechaImpresion;
    }

    public String getFechaImpresion() {
	return fechaImpresion;
    }

    public void setFechaAceptacion(String fechaAceptacion) {
	this.fechaAceptacion = fechaAceptacion;
    }

    public String getFechaAceptacion() {
	return fechaAceptacion;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setConceptoPeriodos(String conceptoPeriodos) {
	this.conceptoPeriodos = conceptoPeriodos;
    }

    public String getConceptoPeriodos() {
	return conceptoPeriodos;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
	this.tipoImpuesto = tipoImpuesto;
    }

    public String getTipoImpuesto() {
	return tipoImpuesto;
    }
    
    public void loadContractData() {
	//setidContrato = -1;
	//idBoletaPlanDePago = -1;
	setCantPeriodos(moratoria.getCantAnticipos());
	setNroContrato(""+ moratoria.getIdPlanDePago());
	setCantcuotas(boletaMoratoria.getCantCuotas());
	setMontoCuotas(0.0); 
	setContribuyente(boletaMoratoria.getContribuyente());
	setImporte(boletaMoratoria.getImporte());
	setInteres(boletaMoratoria.getRecargo());
	setBonificacionMoratoria(boletaMoratoria.getDeducciones());
	setBonificacionespecial(boletaMoratoria.getDescuentoEspecial());
	setMontoTotal(boletaMoratoria.getTotal());
	setNombreBonificacionEspecial(boletaMoratoria.getNombreDescuentoEspecial());
	setDomicilio(boletaMoratoria.getDomicilio());
	setConceptoPeriodos(boletaMoratoria.getConcepto());
	setTipoImpuesto("");
    }

    public void retrieveData() {
    /** 
     * 2009-09-21 (cesar)
     * Se modifico el nombre del S.P. y se agregó dos vbles. (porcentajeIntFin y montoIntFin)
     * */
    
//	ResultSet result = LibSQL.exFunction("taxes.getContratoMoratoria", idContrato);
	ResultSet result = LibSQL.exFunction("taxes.getContratoPlanDePago", idContrato);
	try {
	    if (result.next()) {
		idContrato = result.getInt("idcontrato");
	        idBoletaPlanDePago = result.getInt("idboletaplandepago");
		nroContrato = result.getString("numero");
		contribuyente = result.getString("contribuyente");
		domicilio = result.getString("domicilio");
		fechaImpresion = result.getString("fechaimpresion");
		fechaAceptacion = result.getString("fechaaceptacion");
		cantPeriodos = result.getInt("cantperiodos");
		conceptoPeriodos = result.getString("conceptoperiodos");
		importe = result.getDouble("monto");
		interes = result.getDouble("interes");
		bonificacionMoratoria = result.getDouble("bonificacionmoratoria");
		bonificacionespecial = result.getDouble("bonificacionespecial");
		montoTotal = result.getDouble("montototal");
		cantcuotas = result.getInt("cantcuotas");
		montoCuotas = result.getDouble("montocuotas");
		tipoImpuesto = result.getString("tipoimpuesto");
		barcode = result.getString("barcode");
	        idPlandePago = result.getInt("idplandepago");
		rescision = result.getString("rescision");
	        ordenanza = result.getString("ordenanza");
                impreso = result.getBoolean("impreso");
		setPorcentajeIntFin(result.getDouble("porcentajeinteresfinanciacion"));
	        setMontoIntFin(result.getDouble("montointeresfinanciacion"));
		setContado(result.getBoolean("contado"));
		setPorcentajeBonificacionContado(result.getDouble("PorcentajeBonificacionContado"));
		setBonificacionContado(result.getDouble("BonificacionContado"));
	    } else {
	        idContrato = -1;
	    }
	} catch (SQLException e) {
	    // TODO
	    idContrato = -1;
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setIdPlandePago(int idPlandePago) {
	this.idPlandePago = idPlandePago;
    }

    public int getIdPlandePago() {
	return idPlandePago;
    }

    public void setRescision(String rescision) {
	this.rescision = rescision;
    }

    public String getRescision() {
	return rescision;
    }

    public void setOrdenanza(String ordenanza) {
	this.ordenanza = ordenanza;
    }

    public String getOrdenanza() {
	return ordenanza;
    }
    
    public void retrieveIdContratoByIdBoletaMoratoria() {
	idContrato = LibSQL.getInt("taxes.getIdContratoMoratoriaByIdBoletaMoratoria",""+ idBoletaPlanDePago);
    }
    
    public void retrieveIdContratoByBoleta(int _idBoleta , int _idTipoImpuesto) {
        idContrato = LibSQL.getInt("taxes.getIdContratoMoratoriaByBoleta",""+ _idBoleta + " ," + _idTipoImpuesto);
    }

    public void setPorcentajeIntFin(double porcentajeIntFin) {
	this.porcentajeIntFin = porcentajeIntFin;
    }

    public double getPorcentajeIntFin() {
	return porcentajeIntFin;
    }

    public void setMontoIntFin(double montoIntFin) {
	this.montoIntFin = montoIntFin;
    }

    public double getMontoIntFin() {
	return montoIntFin;
    }

    public void setContado(boolean contado) {
	this.contado = contado;
    }

    public boolean isContado() {
	return contado;
    }

    public void setPorcentajeBonificacionContado(double porcentajeBonificacionContado) {
	this.porcentajeBonificacionContado = porcentajeBonificacionContado;
    }

    public double getPorcentajeBonificacionContado() {
	return porcentajeBonificacionContado;
    }

    public void setBonificacionContado(double bonificacionContado) {
	this.bonificacionContado = bonificacionContado;
    }

    public double getBonificacionContado() {
	return bonificacionContado;
    }

    public void setImpreso(boolean impreso) {
        this.impreso = impreso;
    }

    public boolean isImpreso() {
        return impreso;
    }
}
