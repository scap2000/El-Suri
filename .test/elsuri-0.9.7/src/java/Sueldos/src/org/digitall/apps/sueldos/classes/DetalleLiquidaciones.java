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
 * DetalleLiquidaciones.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class DetalleLiquidaciones {

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * El atributo "isParcial" debería llamarse "parcial"
     * y sus accesores "isParcial()" y "setParcial(boolean)"
     * El resto de los atributos deberían llamarse (respectivamente)
     * "idDetalleLiquidacion", "idLiquidacion", "idConcepto" y "debeHaber"
     * El campo estado creo que no hace falta ni sus accesores
     * */
    private boolean isParcial = false;
    private int iddetalleliquidacion = -1; 
    private int idliquidacion = -1;
    private int idconcepto = -1;
    private double valor = 0.0;
    private int debehaber = -1;
    private String estado = "";

    public DetalleLiquidaciones() {
    }
    
    public int saveData() {             
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * No hace falta enviar el campo estado, ni tenerlo en cuenta desde el Stored Proc.
	 * */
	String params = iddetalleliquidacion+","+idliquidacion+","+idconcepto+","+valor+","+ debehaber+",'"+estado+"'";
	int result = -1;
	if (iddetalleliquidacion == -1){
	    /* 2009-09-17 (santiago) Mensaje para el codificador
	     * En estos casos, es conveniente llamar al accesor
	     * por las dudas el valor varie de acuerdo al accesor y no al atributo
	     * Por lo tanto quedaria "if (isIsParcial()) {"
	     * */
	    if(isParcial){
		result = LibSQL.getInt("sueldos.addDetalleLiquidacionParcial",params);                 
	    }else{
		result = LibSQL.getInt("sueldos.addDetalleLiquidacion",params);                
	    }
	    
	} else {            
	    if(isParcial){
		result = LibSQL.getInt("sueldos.setDetalleLiquidacionParcial",params);        
	    }else{
		result = LibSQL.getInt("sueldos.setDetalleLiquidacion",params);                
	    }
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idliquidacion;
	ResultSet data;
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * En estos casos, es conveniente llamar al accesor
	 * por las dudas el valor varie de acuerdo al accesor y no al atributo
	 * Por lo tanto quedaria "if (isIsParcial()) {"
	 * */
	if(isParcial){
	     data = LibSQL.exFunction("sueldos.getDetalleLiquidacionParcial", params);
	}else{
	     data = LibSQL.exFunction("sueldos.getDetalleLiquidacion", params);
	}
	
	try {
	    if (data.next()) {
		iddetalleliquidacion  = data.getInt("iddetalleliquidacion");
		idliquidacion = data.getInt("idliquidacion");
		idconcepto = data.getInt("idconcepto");
		valor = data.getInt("valor");
		debehaber  = data.getInt("debehaber");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIsParcial(boolean isParcial) {
	this.isParcial = isParcial;
    }

    public boolean isIsParcial() {
	return isParcial;
    }

    public void setIddetalleliquidacion(int iddetalleliquidacion) {
	this.iddetalleliquidacion = iddetalleliquidacion;
    }

    public int getIddetalleliquidacion() {
	return iddetalleliquidacion;
    }

    public void setIdliquidacion(int idliquidacion) {
	this.idliquidacion = idliquidacion;
    }

    public int getIdliquidacion() {
	return idliquidacion;
    }

    public void setIdconcepto(int idconcepto) {
	this.idconcepto = idconcepto;
    }

    public int getIdconcepto() {
	return idconcepto;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public double getValor() {
	return valor;
    }

    public void setDebehaber(int debehaber) {
	this.debehaber = debehaber;
    }

    public double getDebehaber() {
	return debehaber;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
