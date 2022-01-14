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
 * ResumenPagoContado.java
 *
 * */
package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.lib.sql.LibSQL;

public class ResumenPagoContado {
    
    private int anticipoDesde = -1;
    private int anticipoHasta = -1;
    private int anioDesde = -1;
    private int anioHasta = -1;
    private int cantAnticipos = -1;
    private double deudaParcial = 0.00;
    private double interesPorMora = 0.00;
    private double subTotalDeuda = 0.00;
    private String bonificacionEspecial = "";
    private double montoBonificacionEspecial = 0.00;
    private String leyendaBonificacionEspecial = "";
    private double porcentajeBonificacionEspecial = 0.00;
    private double total = 0.00;
    
    private Bien bien;
    private Bonificacion bonificacion;
    private TipoImpuesto tipoImpuesto;
    
    public ResumenPagoContado(Bien _bien, Bonificacion _bonificacion, TipoImpuesto _tipoImpuesto) {
        super();
        bien = _bien;
        bonificacion = _bonificacion;
        tipoImpuesto = _tipoImpuesto;
    }
    
    public void retrieveData(){
        try {
            String params = "" + bien.getIdBien() + "," + bonificacion.getPorcentaje() + "," + tipoImpuesto.getIdTipoImpuesto();
            ResultSet rs = LibSQL.exFunction("taxes.getResumenPagoContado",params);
            if (rs.next()){
                
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResumenPagoContado() {
        super();
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

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
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

    public void setAnticipoDesde(int anticipoDesde) {
        this.anticipoDesde = anticipoDesde;
    }

    public int getAnticipoDesde() {
        return anticipoDesde;
    }

    public void setAnticipoHasta(int anticipoHasta) {
        this.anticipoHasta = anticipoHasta;
    }

    public int getAnticipoHasta() {
        return anticipoHasta;
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

    public void setCantAnticipos(int cantAnticipos) {
        this.cantAnticipos = cantAnticipos;
    }

    public int getCantAnticipos() {
        return cantAnticipos;
    }
}
