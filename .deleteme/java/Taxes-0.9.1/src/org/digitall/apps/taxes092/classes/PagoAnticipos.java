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
 * PagoAnticipos.java
 *
 * */
package org.digitall.apps.taxes092.classes;

public class PagoAnticipos {

    private int cantidadAnticipos = 0;
    private int periodoDesde = -1;
    private int periodoHasta = -1;
    private int anioDesde = -1;
    private int anioHasta = -1;
    private double montoTotal = 0.0;
    private double monto = 0.0;
    private double interes = 0.0;
    private double descuento = 0.0;
    private double entrega = 0.0;
    private double porcentajeParcial = 0.0;
    private double totalAnticiposAdeudados = 0.0;
    private Bonificacion bonificacion = new Bonificacion();
    
    public static int SIN_DESCUENTOS = 0;
    public static int EMPLEADO_MUNICIPAL_TGS = 1;
    public static int JUBILADOS_Y_PENSIONADOS_TGS = 2;
    public static int EMPLEADO_MUNICIPAL = 3;
    public static int JUBILADOS_Y_PENSIONADOS = 4;
    public static int DISCAPACITADO = 5;
    
    private int tipoBonificacion = -1;
    
    public PagoAnticipos() {
    
    }

    public void setPeriodoDesde(int periodoDesde) {
	this.periodoDesde = periodoDesde;
    }

    public int getPeriodoDesde() {
	return periodoDesde;
    }

    public void setPeriodoHasta(int periodoHasta) {
	this.periodoHasta = periodoHasta;
    }

    public int getPeriodoHasta() {
	return periodoHasta;
    }

    public void setAnioDesde(int anioDesde) {
	this.anioDesde = anioDesde;
    }

    public int getAnioDesde() {
	return anioDesde;
    }

    public void setAnioHasta(int anioHasta) {
	this.anioHasta = anioHasta;
    }

    public int getAnioHasta() {
	return anioHasta;
    }

    public void setMontoTotal(double montoTotal) {
	this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setInteres(double interes) {
	this.interes = interes;
    }

    public double getInteres() {
	return interes;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

    public void setEntrega(double entrega) {
	this.entrega = entrega;
    }

    public double getEntrega() {
	return entrega;
    }

    public void setPorcentajeParcial(double porcentajeParcial) {
	this.porcentajeParcial = porcentajeParcial;
    }

    public double getPorcentajeParcial() {
	return porcentajeParcial;
    }

    public void setBonificacion(Bonificacion bonificacion) {
	this.bonificacion = bonificacion;
    }

    public Bonificacion getBonificacion() {
	return bonificacion;
    }
    
    public void setBonificacion(int _tipoBonificacion){
	tipoBonificacion = _tipoBonificacion;
	bonificacion.setIdBonificacion(tipoBonificacion);
	bonificacion.retrieveData();
    }

    public void setCantidadAnticipos(int cantidadAnticipos) {
	this.cantidadAnticipos = cantidadAnticipos;
    }

    public int getCantidadAnticipos() {
	return cantidadAnticipos;
    }

    public void setTotalAnticiposAdeudados(double totalAnticiposAdeudados) {
	this.totalAnticiposAdeudados = totalAnticiposAdeudados;
    }

    public double getTotalAnticiposAdeudados() {
	return totalAnticiposAdeudados;
    }
    
    public void clear(){
	cantidadAnticipos = 0;
	periodoDesde = -1;
	periodoHasta = -1;
	anioDesde = -1;
	anioHasta = -1;
	montoTotal = 0.0;
	monto = 0.0;
	interes = 0.0;
	descuento = 0.0;
	entrega = 0.0;
	porcentajeParcial = 0.0;
	totalAnticiposAdeudados = 0.0;
	bonificacion = new Bonificacion();
	tipoBonificacion = -1;
    }
}
