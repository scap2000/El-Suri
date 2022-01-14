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
 * EstadoCuenta.java
 *
 * */
package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class EstadoCuenta {
    
    private double deudaParcial = 0.0;
    private double interesParcial = 0.0;
    private double subTotal = 0.0;
    private double montoBonificacion = 0.0;
    private int cantPeriodos = 0;
    private int periodoInicial = 0;
    private int anioInicial = 0;
    private int periodoFinal = 0;
    private int anioFinal = 0;
    private double total = 0.0;
    private Bien bien;
    private Bonificacion bonificacion;
    private Impuesto impuesto;
    private boolean cubreAnticipoActual;
                             
    public EstadoCuenta(Bien _bien, Bonificacion _bonificacion, Impuesto _impuesto,boolean _cubreAnticipoActual) {
        super();
        bien = _bien;
        bonificacion = _bonificacion;
        impuesto = _impuesto;
        cubreAnticipoActual = _cubreAnticipoActual;
    }

    public void retrieveData() {
        ResultSet result =
            LibSQL.exFunction("taxes.getEstadoCuenta", bien.getIdBien() + "," +
                              impuesto.getTipoImpuesto().getIdTipoImpuesto() +
                              "," + bonificacion.getPorcentaje() + "," +
                              cubreAnticipoActual);
        try {
            if (result.next()) {
                deudaParcial = result.getDouble("deudaParcial");
                interesParcial = result.getDouble("interesParcial");
                subTotal = result.getDouble("subTotal");
                montoBonificacion = result.getDouble("montoBonificacion");
                cantPeriodos = result.getInt("cantPeriodos");
                periodoInicial = result.getInt("periodoInicial");
                anioInicial = result.getInt("anioInicial");
                periodoFinal = result.getInt("periodoFinal");
                anioFinal = result.getInt("anioFinal");
                total = result.getDouble("total");
            }

        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO
            e.printStackTrace();
        }
    }
    
    public void setDeudaParcial(double deudaParcial) {
        this.deudaParcial = deudaParcial;
    }

    public double getDeudaParcial() {
        return deudaParcial;
    }

    public void setInteresParcial(double interesParcial) {
        this.interesParcial = interesParcial;
    }

    public double getInteresParcial() {
        return interesParcial;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setMontoBonificacion(double montoBonificacion) {
        this.montoBonificacion = montoBonificacion;
    }

    public double getMontoBonificacion() {
        return montoBonificacion;
    }

    public void setCantPeriodos(int cantPeriodos) {
        this.cantPeriodos = cantPeriodos;
    }

    public int getCantPeriodos() {
        return cantPeriodos;
    }

    public void setPeriodoInicial(int periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public int getPeriodoInicial() {
        return periodoInicial;
    }

    public void setAnioInicial(int anioInicial) {
        this.anioInicial = anioInicial;
    }

    public int getAnioInicial() {
        return anioInicial;
    }

    public void setPeriodoFinal(int periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public int getPeriodoFinal() {
        return periodoFinal;
    }

    public void setAnioFinal(int anioFinal) {
        this.anioFinal = anioFinal;
    }

    public int getAnioFinal() {
        return anioFinal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }
}
