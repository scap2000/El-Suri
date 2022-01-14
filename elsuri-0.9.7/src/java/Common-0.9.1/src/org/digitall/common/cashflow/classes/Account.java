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
 * Account.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Account {

    private int idAccount = -1;
    private int code;
    private String name;
    private int idParent = 0;
    private int idAccountType = -1;
    private String initialAmountcolor;
    private String assignedAmountToACColor;
    private String assignedAmountToCCColor;
    private String description;
    private boolean isImputable;
    private boolean isHeritage;
    private boolean isDeduction;
    private boolean isCash;
    private boolean isValues;
    private int childrenNumber = 0;
    private String fullName;
    private boolean subAccount = false;
    

    public Account() {

    }

    public Account(int _idAccount) {
	idAccount = _idAccount;
    }
    
    public Account(int _idAccount, String _Name, int _idAccountType) {
	idAccount = _idAccount;
	name = _Name;
	idAccountType = _idAccountType;
    }
    
    public void setIDAccount(int _idAccount) {
	this.idAccount = _idAccount;
    }

    public int getIDAccount() {
	return idAccount;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public int getCode() {
	return code;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setIdParent(int idParent) {
	this.idParent = idParent;
    }

    public int getIdParent() {
	return idParent;
    }

    public void setIdAccountType(int _idAccountType) {
	this.idAccountType = _idAccountType;
    }

    public int getIdAccountType() {
	return idAccountType;
    }

    public void setInitialAmountcolor(String initialAmountcolor) {
	this.initialAmountcolor = initialAmountcolor;
    }

    public String getInitialAmountcolor() {
	return initialAmountcolor;
    }

    public void setAssignedAmountToACColor(String assignedAmountToACColor) {
	this.assignedAmountToACColor = assignedAmountToACColor;
    }

    public String getAssignedAmountToACColor() {
	return assignedAmountToACColor;
    }

    public void setAssignedAmountToCCColor(String assignedAmountToCCColor) {
	this.assignedAmountToCCColor = assignedAmountToCCColor;
    }

    public String getAssignedAmountToCCColor() {
	return assignedAmountToCCColor;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setIsImputable(boolean isImputable) {
	this.isImputable = isImputable;
    }

    public boolean isIsImputable() {
	return isImputable;
    }

    public int saveData(){
	String params = code +",'"+ name +"',"+ idParent +","+ idAccountType +",'"+ initialAmountcolor 
			+"','"+ assignedAmountToACColor +"','"+ assignedAmountToCCColor +"','"+ description 
			+"','"+ isImputable +"','"+ isDeduction +"','"+ isCash +"','"+ isValues +"','"+ subAccount +"'";
	int result = -1;
	if (idAccount == -1) {
	    result = LibSQL.getInt("accounting.addAccount", params);    
	    idAccount = result;
	} else {
	    params = idAccount +","+ params;
	    result = LibSQL.getInt("accounting.setAccount", params);    
	}	
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("accounting.getAccount", idAccount);
	try {
	    if (data.next()) {
		code = data.getInt("code");
		name = data.getString("name");
		idParent  = data.getInt("idparent");
		idAccountType  = data.getInt("idaccounttype");
		initialAmountcolor  = data.getString("initialamountcolor");
		assignedAmountToACColor = data.getString("assignedamounttoetcolor");
		assignedAmountToCCColor = data.getString("assignedamounttocccolor");
		description = data.getString("description");
		isImputable  = data.getBoolean("isimputable");
		isHeritage = data.getBoolean("isheritage");
		isDeduction = data.getBoolean("isdeduction");
		isCash = data.getBoolean("iscash");
		isValues = data.getBoolean("isvalues");
		fullName = data.getString("name");
                subAccount = data.getBoolean("subaccount");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();   
	}
    }

    public void retrieveChildrenNumber(){
        childrenNumber = LibSQL.getInt("accounting.getChildrenByAccount","" + idAccount);
    }

    public int saveColor(){
	String params = idAccount +",'"+ initialAmountcolor +"'";
	return LibSQL.getInt("accounting.setAccountColor", params);    
    }

    public void setIsHeritage(boolean isHeritage) {
	this.isHeritage = isHeritage;
    }

    public boolean isIsHeritage() {
	return isHeritage;
    }

    public void setIsDeduction(boolean isDeduction) {
	this.isDeduction = isDeduction;
    }

    public boolean isIsDeduction() {
	return isDeduction;
    }

    public void setIsCash(boolean isCash) {
	this.isCash = isCash;
    }

    public boolean isIsCash() {
	return isCash;
    }

    public void setIsValues(boolean isValues) {
	this.isValues = isValues;
    }

    public boolean isIsValues() {
	return isValues;
    }

    public void setChildrenNumber(int childrenNumber) {
	this.childrenNumber = childrenNumber;
    }

    public int getChildrenNumber() {
	return childrenNumber;
    }

    public int getNewCode(){
	return LibSQL.getInt("accounting.getAccountNewCode", ""+ idParent);
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getFullName() {
	return fullName;
    }

    public void setSubAccount(boolean subAccount) {
        this.subAccount = subAccount;
    }

    public boolean isSubAccount() {
        return subAccount;
    }

}
