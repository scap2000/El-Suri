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
 * Resources.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Resources {    
    private int idResource = -1;	
    private String name = "";   
    private int idUnit = -1;		    
    private int idSkillToProvider = -1;	
    private int idAccount = -1;
    private boolean composite = false;      
    private String description = "";    
    private int lifeTime = -1;
    private int idLifeTimeType = -1;      
    private int barCode = -1;  
    private double totalStock = 0.0;

    public Resources() {

    }

    public int getIdResource() {
	return idResource;
    }

    public void setIdResource(int _idResource) {
	this.idResource = _idResource;
    }

    public String getName() {
	return name;
    }

    public void setName(String _name) {
	this.name = _name;
    }

    public int getIdUnit() {
	return idUnit;
    }

    public void setIdUnit(int _idUnit) {
	this.idUnit = _idUnit;
    }

    public int getIdSkillToProvider() {
	return idSkillToProvider;
    }

    public void setIdSkillToProvider(int _idSkillToProvider) {
	this.idSkillToProvider = _idSkillToProvider;
    }

    public int getIdAccount() {
	return idAccount;
    }

    public void setIdAccount(int _idAccount) {
	this.idAccount = _idAccount;
    }

    public boolean isComposite() {
	return composite;
    }

    public void setComposite(boolean _composite) {
	this.composite = _composite;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String _description) {
	this.description = _description;
    }

    public int getLifeTime() {
	return lifeTime;
    }

    public void setLifeTime(int _lifeTime) {
	this.lifeTime = _lifeTime;
    }

    public int getIdLifeTimeType() {
	return idLifeTimeType;
    }

    public void setIdLifeTimeType(int _idLifeTimeType) {
	this.idLifeTimeType = _idLifeTimeType;
    }

    public int getBarCode() {
	return barCode;
    }

    public void setBarCode(int _barCode) {
	this.barCode = _barCode;
    }

    public double getTotalStock() {
	return totalStock;
    }

    public void setTotalStock(double _totalStock) {
	this.totalStock = _totalStock;
    }
    
    public int saveData() {     		
	String params = "'" + name + "'," + idUnit + "," + idSkillToProvider 
	+ "," + idAccount + "," + composite + ",'" + description + "'," + lifeTime + ","
	+ idLifeTimeType + "," + barCode + "," + totalStock;
	int result = -1;
	if (idResource == -1){
	    result = LibSQL.getInt("personalfiles.addResouce",params);  
	    idResource = result;
	}else{      
	    params = idResource +","+ params;
	    result = LibSQL.getInt("personalfiles.setResource",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getResouces", idResource);
	try {
	    if (data.next()) {  	       
	        name = data.getString("name");    
	        idUnit = data.getInt("idunit");     
	        idSkillToProvider = data.getInt("idskilltoprovider");
	        idAccount = data.getInt("idaccount");
	        composite = data.getBoolean("composite");
	        description = data.getString("description");    
	        lifeTime = data.getInt("lifetime");
	        idLifeTimeType = data.getInt("idlifetimetype");
	        barCode = data.getInt("barcode");  
	        totalStock = data.getDouble("totalstock");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
