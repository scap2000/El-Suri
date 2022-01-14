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
 * CuotasTGS.java
 *
 * */
package org.digitall.apps.taxes.classes;

public class CuotasTGS {

    private int cantCuotas = -1;
    private int primerAnticipo = -1;
    private int anioPrimerAnticipo = -1;
    private int ultimoAnticipo = -1;
    private int anioUltimoAnticipo = -1;
    private double importe = 0;
    private double intereses = 0;
    private int idcatastro = -1;
    private int catastro = -1;
    private String seccion = "";
    private String manzana = "";
    private String parcela = "";

    public CuotasTGS() {
    }

    public void setCantCuotas(int cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public int getCantCuotas() {
        return cantCuotas;
    }

    public void setPrimerAnticipo(int primerAnticipo) {
        this.primerAnticipo = primerAnticipo;
    }

    public int getPrimerAnticipo() {
        return primerAnticipo;
    }

    public void setAnioPrimerAnticipo(int anioPrimerAnticipo) {
        this.anioPrimerAnticipo = anioPrimerAnticipo;
    }

    public int getAnioPrimerAnticipo() {
        return anioPrimerAnticipo;
    }

    public void setUltimoAnticipo(int ultimoAnticipo) {
        this.ultimoAnticipo = ultimoAnticipo;
    }

    public int getUltimoAnticipo() {
        return ultimoAnticipo;
    }

    public void setAnioUltimoAnticipo(int anioUltimoAnticipo) {
        this.anioUltimoAnticipo = anioUltimoAnticipo;
    }

    public int getAnioUltimoAnticipo() {
        return anioUltimoAnticipo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIdcatastro(int idcatastro) {
        this.idcatastro = idcatastro;
    }

    public int getIdcatastro() {
        return idcatastro;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getManzana() {
        return manzana;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getParcela() {
        return parcela;
    }
}
