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
 * DistinguishableResources.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import java.util.Date;

import org.digitall.lib.sql.LibSQL;

public class DistinguishableResources {

    private int idDistinguishableResource = -1;
    private int idResource = -1;
    private int idBrand = -1;
    private String aquisitionDate = "";
    private String dropOffDate = "";
    private int idDepot = -1;
    private int idPerson = -1;
    private double waste = 0.0;
    private int idLifeTimeType = -1;
    private int idIdentificationType = -1;
    private String identificationNumber = "";
    private double price = 0.0;

    public DistinguishableResources() {

    }

    public int getIdDistinguishableResource() {
	return idDistinguishableResource;
    }

    public void setIdDistinguishableResource(int _idDistinguishableResource) {
	this.idDistinguishableResource = _idDistinguishableResource;
    }

    public int getIdResource() {
	return idResource;
    }

    public void setIdResource(int _idResource) {
	this.idResource = _idResource;
    }

    public int getIdBrand() {
	return idBrand;
    }

    public void setIdBrand(int _idBrand) {
	this.idBrand = _idBrand;
    }

    public String getAquisitionDate() {
	return aquisitionDate;
    }

    public void setAquisitionDate(String _aquisitionDate) {
	this.aquisitionDate = _aquisitionDate;
    }

    public String getDropOffDate() {
	return dropOffDate;
    }

    public void setDropOffDate(String _dropOffDate) {
	this.dropOffDate = _dropOffDate;
    }

    public int getIdDepot() {
	return idDepot;
    }

    public void setIdDepot(int _idDepot) {
	this.idDepot = _idDepot;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }

    public double getWaste() {
	return waste;
    }

    public void setWaste(double _waste) {
	this.waste = _waste;
    }

    public int getIdLifeTimeType() {
	return idLifeTimeType;
    }

    public void setIdLifeTimeType(int _idLifeTimeType) {
	this.idLifeTimeType = _idLifeTimeType;
    }

    public int getIdIdentificationType() {
	return idIdentificationType;
    }

    public void setIdIdentificationType(int _idIdentificationType) {
	this.idIdentificationType = _idIdentificationType;
    }

    public String getIdentificationNumber() {
	return identificationNumber;
    }

    public void setIdentificationNumber(String _identificationNumber) {
	this.identificationNumber = _identificationNumber;
    }

    public int saveData() {     
	String params = idResource + "," + idBrand + ",'" + aquisitionDate 
	+ "','" + dropOffDate + "'," + idDepot + "," + idPerson + "," + waste + ","
	+ idLifeTimeType + "," + idIdentificationType + ",'" + identificationNumber+"',"+price;
	int result = -1;
	if (idDistinguishableResource == -1){
		result = LibSQL.getInt("resourcescontrol.addDistinguishableResources",params);  
	    idDistinguishableResource = result;
	}else{      
	    params = idDistinguishableResource +","+ params;
	    result = LibSQL.getInt("resourcescontrol.setDistinguishableResources",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("resourcescontrol.getrecursodistinguible", idDistinguishableResource);
	System.out.println("iddistinguishableresources: "+idDistinguishableResource);
	try {
	    if (data.next()) {  
	        idDistinguishableResource = data.getInt("iddistinguishableresource");        
	        idResource = data.getInt("idresource");        
	        idBrand = data.getInt("idbrand");        
	        aquisitionDate = data.getString("aquisitiondate");
	        dropOffDate = data.getString("dropoffdate");
	        idDepot = data.getInt("iddepot");        
	        idPerson = data.getInt("idperson");        
	        waste = data.getDouble("waste");   
	        idLifeTimeType = data.getInt("idlifetimetype");        
	        idIdentificationType = data.getInt("ididentificationtype");        
	        identificationNumber = data.getString("identificationnumber");         
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double _price) {
	this.price = _price;
    }

}
