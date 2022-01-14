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
 * ResumenPlanDePago.java
 *
 * */
package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.lib.sql.LibSQL;

public class ResumenPlanDePago {
    
    private double entrega = 0.00;
    private double deudaParcial = 0.00;
    private double interesPorMora = 0.00;
    private double subTotalDeuda = 0.00;
    private String bonificacionEspecial = "";
    private double montoBonificacionEspecial = 0.00;
    private String leyendaBonificacionEspecial = "";
    private double subTotal = 0.00;
    private double montoCubiertoEntrega = 0.00;
    private String periodoAnticipos = "";
    private double saldoAFinanciar = 0.00;
    private String periodoPlanDePago = "";
    private int cantidadAnticiposPP = 0;
    private double capital = 0.00;
    private double interes = 0.00;
    private double porcentajeCondIntereses = 0.00;
    private double montoCondIntereses = 0.00;
    private double porcentajeIntXFinanciacion = 0.00;
    private double montoIntXFinanciacion = 0.00;
    private double total = 0.00;
    private String leyendaCuotas = "";
    private double porcentajeBonificacionEspecial = 0.00;
    private int cantidadCuotas = 0;
    

    public void setEntrega(double entrega) {
        this.entrega = entrega;
    }

    public double getEntrega() {
        return entrega;
    }

    public void setDeudaParcial(double deudaParcial) {
        this.deudaParcial = deudaParcial;
    }

    public double getDeudaParcial() {
        return deudaParcial;
    }

    public void setInteresPorMora(double interesPorMora) {
        this.interesPorMora = interesPorMora;
    }

    public double getInteresPorMora() {
        return interesPorMora;
    }

    public void setSubTotalDeuda(double subTotalDeuda) {
        this.subTotalDeuda = subTotalDeuda;
    }

    public double getSubTotalDeuda() {
        return subTotalDeuda;
    }

    public void setBonificacionEspecial(String bonificacionEspecial) {
        this.bonificacionEspecial = bonificacionEspecial;
    }

    public String getBonificacionEspecial() {
        return bonificacionEspecial;
    }

    public void setMontoBonificacionEspecial(double montoBonificacionEspecial) {
        this.montoBonificacionEspecial = montoBonificacionEspecial;
    }

    public double getMontoBonificacionEspecial() {
        return montoBonificacionEspecial;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setMontoCubiertoEntrega(double montoCubiertoEntrega) {
        this.montoCubiertoEntrega = montoCubiertoEntrega;
    }

    public double getMontoCubiertoEntrega() {
        return montoCubiertoEntrega;
    }

    public void setPeriodoAnticipos(String periodoAnticipos) {
        this.periodoAnticipos = periodoAnticipos;
    }

    public String getPeriodoAnticipos() {
        return periodoAnticipos;
    }

    public void setSaldoAFinanciar(double saldoAFinanciar) {
        this.saldoAFinanciar = saldoAFinanciar;
    }

    public double getSaldoAFinanciar() {
        return saldoAFinanciar;
    }

    public void setPeriodoPlanDePago(String periodoPlanDePago) {
        this.periodoPlanDePago = periodoPlanDePago;
    }

    public String getPeriodoPlanDePago() {
        return periodoPlanDePago;
    }

    public void setCantidadAnticiposPP(int cantidadAnticiposPP) {
        this.cantidadAnticiposPP = cantidadAnticiposPP;
    }

    public int getCantidadAnticiposPP() {
        return cantidadAnticiposPP;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getCapital() {
        return capital;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }

    public void setPorcentajeCondIntereses(double porcentajeCondIntereses) {
        this.porcentajeCondIntereses = porcentajeCondIntereses;
    }

    public double getPorcentajeCondIntereses() {
        return porcentajeCondIntereses;
    }

    public void setMontoCondIntereses(double montoCondIntereses) {
        this.montoCondIntereses = montoCondIntereses;
    }

    public double getMontoCondIntereses() {
        return montoCondIntereses;
    }

    public void setPorcentajeIntXFinanciacion(double porcentajeIntXFinanciacion) {
        this.porcentajeIntXFinanciacion = porcentajeIntXFinanciacion;
    }

    public double getPorcentajeIntXFinanciacion() {
        return porcentajeIntXFinanciacion;
    }

    public void setMontoIntXFinanciacion(double montoIntXFinanciacion) {
        this.montoIntXFinanciacion = montoIntXFinanciacion;
    }

    public double getMontoIntXFinanciacion() {
        return montoIntXFinanciacion;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setLeyendaCuotas(String leyendaCuotas) {
        this.leyendaCuotas = leyendaCuotas;
    }

    public String getLeyendaCuotas() {
        return leyendaCuotas;
    }

    public void setLeyendaBonificacionEspecial(String leyendaBonificacionEspecial) {
        this.leyendaBonificacionEspecial = leyendaBonificacionEspecial;
    }

    public String getLeyendaBonificacionEspecial() {
        return leyendaBonificacionEspecial;
    }

    public void setPorcentajeBonificacionEspecial(double porcentajeBonificacionEspecial) {
        this.porcentajeBonificacionEspecial = porcentajeBonificacionEspecial;
    }

    public double getPorcentajeBonificacionEspecial() {
        return porcentajeBonificacionEspecial;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }
}
