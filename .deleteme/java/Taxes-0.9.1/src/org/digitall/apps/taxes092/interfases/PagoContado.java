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
 * PagoContado.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.apps.taxes.classes.TipoPlanDePago;
import org.digitall.apps.taxes092.classes.Bien;
import org.digitall.apps.taxes092.classes.Bonificacion;
import org.digitall.lib.sql.LibSQL;

public class PagoContado {
    private double capital = 0.0;
    private double interes = 0.0;
    private double capitalPuro = 0.0;
    private double interesPuro = 0.0;
    private double descuento = 0.0;
    private double descuentoPagoContado = 0.0;
    private double total = 0.0;
    private double totalPuro = 0.0;
    private int periodoDesdePuro = 0;
    private int periodoHastaPuro = 0;
    private int periodoDesdeVista = 0;
    private int periodoHastaVista = 0;
    private int anioDesde = 0;
    private int anioHasta = 0;
    private int cantidadPeriodos = 0;
    
    private Bonificacion bonificacion = new Bonificacion();
    private Bien bien = new Bien();
    private TipoImpuesto tipoImpuesto = new TipoImpuesto();
    
    public PagoContado() {
        super();
    }
    
    public void retrieveData(TipoPlanDePago _tipoPlanDePago, Bien _bien, Bonificacion _bonificacion, TipoImpuesto _tipoImpuesto){
        bien = _bien;
        bonificacion = _bonificacion;
        tipoImpuesto = _tipoImpuesto;
        ResultSet configPagoContado = LibSQL.exFunction("taxes.getConfiguracionPagoContado", "'" + _tipoPlanDePago.getFechaInicio() + "','" + _tipoPlanDePago.getFechaFin() + "'," + _tipoPlanDePago.getIdTipoImpuesto() + "," +bien.getIdBien()+"," + bien.getTipoBien()+ ","  + bonificacion.getPorcentaje() );
        try {
            if (configPagoContado.next()) {
                capital = configPagoContado.getDouble("capital");
                interes = configPagoContado.getDouble("interes");
                capitalPuro = configPagoContado.getDouble("capitalpuro");
                interesPuro = configPagoContado.getDouble("interespuro");
                descuento = configPagoContado.getDouble("descuento");
                descuentoPagoContado = configPagoContado.getDouble("descuentoPagoContado");
                total = configPagoContado.getDouble("total");
                totalPuro = configPagoContado.getDouble("totalpuro");
                periodoDesdePuro = configPagoContado.getInt("periodoDesdePuro");
                periodoHastaPuro = configPagoContado.getInt("periodoHastaPuro");
                periodoDesdeVista = configPagoContado.getInt("periodoDesdeVista");
                periodoHastaVista = configPagoContado.getInt("periodoHastaVista");
                anioDesde = configPagoContado.getInt("anioDesde");
                anioHasta = configPagoContado.getInt("anioHasta");
                cantidadPeriodos = configPagoContado.getInt("cantidadPeriodos");
            }
        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO
            e.printStackTrace();
        }
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

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuentoPagoContado(double descuentoPagoContado) {
        this.descuentoPagoContado = descuentoPagoContado;
    }

    public double getDescuentoPagoContado() {
        return descuentoPagoContado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setPeriodoDesdePuro(int periodoDesdePuro) {
        this.periodoDesdePuro = periodoDesdePuro;
    }

    public int getPeriodoDesdePuro() {
        return periodoDesdePuro;
    }

    public void setPeriodoHastaPuro(int periodoHastaPuro) {
        this.periodoHastaPuro = periodoHastaPuro;
    }

    public int getPeriodoHastaPuro() {
        return periodoHastaPuro;
    }

    public void setPeriodoDesdeVista(int periodoDesdeVista) {
        this.periodoDesdeVista = periodoDesdeVista;
    }

    public int getPeriodoDesdeVista() {
        return periodoDesdeVista;
    }

    public void setPeriodoHastaVista(int periodoHastaVista) {
        this.periodoHastaVista = periodoHastaVista;
    }

    public int getPeriodoHastaVista() {
        return periodoHastaVista;
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

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Bien getBien() {
        return bien;
    }

    public void setCapitalPuro(double capitalPuro) {
        this.capitalPuro = capitalPuro;
    }

    public double getCapitalPuro() {
        return capitalPuro;
    }

    public void setInteresPuro(double interesPuro) {
        this.interesPuro = interesPuro;
    }

    public double getInteresPuro() {
        return interesPuro;
    }

    public void setTotalPuro(double totalPuro) {
        this.totalPuro = totalPuro;
    }

    public double getTotalPuro() {
        return totalPuro;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setCantidadPeriodos(int cantidadPeriodos) {
        this.cantidadPeriodos = cantidadPeriodos;
    }

    public int getCantidadPeriodos() {
        return cantidadPeriodos;
    }
}
