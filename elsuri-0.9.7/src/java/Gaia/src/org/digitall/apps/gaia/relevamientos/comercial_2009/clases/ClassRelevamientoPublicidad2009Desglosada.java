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
 * ClassRelevamientoPublicidad2009Desglosada.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import org.digitall.lib.sql.LibSQL;

public class ClassRelevamientoPublicidad2009Desglosada {
    
    private int idrelevamientopublicidad_desglosada = -1;
    private int iddetallerelevamiento = -1;
    
    private String texto = "";
    private double ancho = 0.0;
    private double alto = 0.0;
    private int faz = -1;
    private double superficie = 0.0;
    private String iluminacion = "";
    private String forma = "";
    private int idempresa = -1;
    private double x = 0;
    private double y = 0;
    
    public ClassRelevamientoPublicidad2009Desglosada() {
        super();
    }

    public void setIdrelevamientopublicidad_desglosada(int idrelevamientopublicidad_desglosada) {
        this.idrelevamientopublicidad_desglosada = idrelevamientopublicidad_desglosada;
    }

    public int getIdrelevamientopublicidad_desglosada() {
        return idrelevamientopublicidad_desglosada;
    }

    public void setIddetallerelevamiento(int iddetallerelevamiento) {
        this.iddetallerelevamiento = iddetallerelevamiento;
    }

    public int getIddetallerelevamiento() {
        return iddetallerelevamiento;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAlto() {
        return alto;
    }

    public void setFaz(int faz) {
        this.faz = faz;
    }

    public int getFaz() {
        return faz;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
    
    public int saveData() {
        int result = -1;
        String params = ""+iddetallerelevamiento + "," + x + "," + y + ",'" + texto + "'," + ancho + "," + alto + "," + faz + "," + superficie + ",'" + iluminacion + "','" + forma + "'," + idempresa;
        if (idrelevamientopublicidad_desglosada == -1)  {
            result = LibSQL.getInt("gea_salta.addpublicidad_propaganda", params);
        } else if (idrelevamientopublicidad_desglosada > 0){
            params = "";
            //result = LibSQL.getInt("gea_salta.addrelevamientopublicidad_2009_desglosada", params);
        }
        return result;
    }
}
