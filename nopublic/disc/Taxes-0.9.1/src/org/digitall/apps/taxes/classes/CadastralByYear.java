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
 * CadastralByYear.java
 *
 * */
package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class CadastralByYear {

    private int idCatastroxanio = -1;
    private int idCatastro = -1; 
    private String fecha = "null";
    private double vf = 0;
    private double mf  = 0;
    private double tgs = -1;
    private double impinm = -1;
    private double tasaanual = -1;  
    private double tasadiaria = -1;
    private int nrocuenta = -1;
    private String estado = "";
    private String titular = "";
    private int catastro = -1;

    public CadastralByYear() {

    }

    public int saveData(){
	int result = -1;
	String params = "";
	if (idCatastroxanio == -1)  {
	    params = ""+ catastro +",'"+ fecha +"',"+ vf +","+ mf +","+ tgs +","+ 
			    impinm +","+ tasaanual +"," + tasadiaria +"," + nrocuenta;
	    result = LibSQL.getInt("impuestos.addCatastrosxAnio", params);
	    idCatastroxanio = result;
	} else {
	    params = ""+ idCatastroxanio + "," + idCatastro +",'"+ fecha +"',"+ vf +","+ mf ;
	    result = LibSQL.getInt("impuestos.setCatastrosxAnio", params);
	}
	 return result;
    }


    public void setIdCatastroxanio(int idCatastroxanio) {
	this.idCatastroxanio = idCatastroxanio;
    }

    public int getIdCatastroxanio() {
	return idCatastroxanio;
    }

    public void setIdCatastro(int idCatastro) {
	this.idCatastro = idCatastro;
    }

    public int getIdCatastro() {
	return idCatastro;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setVf(double vf) {
	this.vf = vf;
    }

    public double getVf() {
	return vf;
    }

    public void setMf(double mf) {
	this.mf = mf;
    }

    public double getMf() {
	return mf;
    }

    public void setTgs(double tgs) {
	this.tgs = tgs;
    }

    public double getTgs() {
	return tgs;
    }

    public void setImpinm(double impinm) {
	this.impinm = impinm;
    }

    public double getImpinm() {
	return impinm;
    }

    public void setTasaanual(double tasaanual) {
	this.tasaanual = tasaanual;
    }

    public double getTasaanual() {
	return tasaanual;
    }

    public void setTasadiaria(double tasadiaria) {
	this.tasadiaria = tasadiaria;
    }

    public double getTasadiaria() {
	return tasadiaria;
    }

    public void setNrocuenta(int nrocuenta) {
	this.nrocuenta = nrocuenta;
    }

    public int getNrocuenta() {
	return nrocuenta;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setTitular(String titular) {
	this.titular = titular;
    }

    public String getTitular() {
	return titular;
    }

    public void setCatastro(int catastro) {
	this.catastro = catastro;
    }

    public int getCatastro() {
	return catastro;
    }

}
