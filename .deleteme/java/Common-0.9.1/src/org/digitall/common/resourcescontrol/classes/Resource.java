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
 * Resource.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.sql.LibSQL;

public class Resource extends Entity {

    private int idResource = -1;
    private String name = "";
    private int idUnit = -1;
    /** quitar **/
    private Skills skillToProvider;
    private boolean composite = false;
    private String description = "";
    private String estado = "";
    private int lifeTime;
    private int idLifeTimeType = 0;
    private String barcode = "";
    private double totalStock = 0;
    private double availableStock = 0;
    private boolean distinguishable = false;
    private String destined = "p";
    //----------------------------------
    //OBJECTS
    private Units unit;
    private ExpenditureAccount expenditureAccount;

    public Resource() {
	this(EntityTypes.RESOURCE, -1);
    }

    public Resource(int _entityType) {
	this(_entityType, -1);
    }

    public Resource(int _entityType, int _idResource) {
	this(_entityType, _idResource, 0);
    }

    public Resource(int _entityType, int _idResource, double _quantity) {
	super(_entityType);
	idResource = _idResource;
	produce(_quantity);
    }
    //ACCESSORS

    public void setIdResource(int _idResource) {
	idResource = _idResource;
    }

    public int getIdResource() {
	return idResource;
    }

    public void setDescription(String _description) {
	description = _description;
    }

    public String getDescription() {
	return description;
    }

    public void setIdUnit(int _idUnit) {
	unit = new Units(_idUnit,"");
	idUnit = _idUnit;
    }

    public int getIdUnit() {
	return unit.getIdUnit();
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return this.name;
    }

    public void setComposite(boolean composite) {
	this.composite = composite;
    }

    public boolean getComposite() {
	return composite;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setLifetime(int lifetime) {
	this.lifeTime = lifetime;
    }

    public int getLifetime() {
	return lifeTime;
    }

    public void setIdLifeTimeType(int _idLifeTimetype) {
	idLifeTimeType = _idLifeTimetype;
    }

    public int getIdLifeTimeType() {
	return idLifeTimeType;
    }

    public void setBarcode(String _barcode) {
	barcode = _barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	this.expenditureAccount = _expenditureAccount;
    }

    public ExpenditureAccount getExpenditureAccount() {
	return expenditureAccount;
    }

    public int saveData() {
	if (!name.trim().equals("")){
	    String params = "'" + name + "'," + unit.getIdUnit() + "," + skillToProvider.getIdSkill() + "," + expenditureAccount.getIDExpenditureAccount() 
            + ",'false','" + description + "'," + lifeTime + "," + idLifeTimeType + ",0" + barcode +","+ distinguishable +",'"+ destined +"'";
	    if (idResource == -1) {
		idResource = LibSQL.getInt("resourcescontrol.addResource", params);
		return idResource;
	    } else {
		params = idResource + "," + params;
		return LibSQL.getInt("resourcescontrol.setResource", params);
	    }
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el Recurso", "Nombre no válido");
	    return -1;
	}
    }

    public int updateExpenditureAccount(){
	if (Advisor.question("Aviso", "No se debería modificar la Cuenta de egresos de un recurso\n¿Está seguro de continuar?")) {
	    //Advisor.messageBox("No se puede modificar la Cuenta de egresos del recurso","Aviso");
	    String params = idResource + "," + expenditureAccount.getIDExpenditureAccount();
	    return LibSQL.getInt("resourcescontrol.setResourceExpenditureAccount", params);
	} else {
	    return -1;
	}
    }
    
    public double updateTotalStock(){
	String params = ""+ idResource;
	return LibSQL.getDouble("resourcescontrol.setResourceTotalStock", params);
    }
    
    /*public double updateStock(){
	String params = ""+ idResource;
	return LibSQL.getDouble("resourcescontrol.setResourceTotalStock", params);
    }*/
    
    public void retrieveData() {	
	String params = String.valueOf(idResource);
	ResultSet data = LibSQL.exFunction("resourcescontrol.getResources", params);
	try {
	    if (data.next()) {
		this.name = data.getString("name");
		
		//idUnit = data.getInt("idunit");
		if (unit == null) {
		    unit = new Units(data.getInt("idunit"), data.getString("unit"));
		} else {
		    unit.setIdUnit(data.getInt("idunit"));
		    unit.setName(data.getString("unit"));
		}
		skillToProvider = new Skills(data.getInt("idskilltoprovider"));
		composite = data.getBoolean("composite");
		description = data.getString("description");
		lifeTime = data.getInt("lifetime");
		idLifeTimeType = data.getInt("idlifetimetype");
		barcode = data.getString("barcode");
		totalStock = data.getDouble("totalstock");
                destined = data.getString("destined");
                distinguishable = data.getBoolean("distinguishable");
		
		expenditureAccount = new ExpenditureAccount(data.getInt("idaccount"));
	        expenditureAccount.setName(data.getString("accountsname"));
	    }else{
	        idResource = -1;
	    }
	} catch (Exception ex) {
	    idResource = -1;
	    ex.printStackTrace();
	}
    }

    public void setUnit(Units unit) {
	this.unit = unit;
    }

    public Units getUnit() {
	return unit;
    }

    public void setTotalStock(double totalStock) {
	this.totalStock = totalStock;
    }

    public double getTotalStock() {
	return totalStock;
    }

    public void setSkillToProvider(Skills skillToProvider) {
	this.skillToProvider = skillToProvider;
    }

    public Skills getSkillToProvider() {
	return skillToProvider;
    }

    public void setAvailableStock(double availableStock) {
	this.availableStock = availableStock;
    }

    public double getAvailableStock() {
	return availableStock;
    }

    public boolean delete() {
	return LibSQL.getBoolean("resourcesControl.delResource", idResource);
    }

    public void setDistinguishable(boolean _distinguishable) {
        distinguishable = _distinguishable;
    }

    public boolean isDistinguishable() {
        return distinguishable;
    }

    public void setDestined(String _destined) {
        destined = _destined;
    }

    public String getDestined() {
        return destined;
    }
}
