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
 * Budget.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;

import org.digitall.lib.sql.LibSQL;

//

public class Budget extends Entity {

    private static final int entityType = EntityTypes.BUDGET;

    /**
     * @associates <{org.sermaq.apps.entities.Money}>
     */
    private Money money;

    /** ATTRIBUTES */
    private int idBudget = -1;
    private String name;
    private String startDate;
    private String endDate;
    private double initialAmount;
    private double initialAmountP;
    private double availableAmount;
    private double spentAmount;
    private double modifiedAmount;
    private double spentAmountP;
    private double assignedAmountToET;
    private double assignedAmountToETp;
    private double assignedAmountToCC;
    private double assignedAmountToCCp;
    private int idPeriodType;    
    private boolean closed;
    private boolean isAssigned;
    private double wOutAssigned;
    private String description;
    private ExpenditureAccountsGroup expenditureAccountsVector = new ExpenditureAccountsGroup();
    private int idBudgetCostCentre;
    private double accountsAmount;

    public JTree getExpenditureAccountTree(boolean _fixed) {
	try {
	    expenditureAccountsVector = new ExpenditureAccountsGroup(this);
	    long prev = System.currentTimeMillis();
	    JTree k = expenditureAccountsVector.getTree(_fixed);
	    System.out.println("Tree loaded in " + (System.currentTimeMillis()-prev) + " ms");
	    return k;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public void addExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	expenditureAccountsVector.add(_expenditureAccount);
    }

    public ExpenditureAccount getExpenditureAccount(int _idExpenditureAccount) {
	return expenditureAccountsVector.getElement(_idExpenditureAccount);
    }

    public ExpenditureAccount getSelectedExpenditureAccount() {
	return (ExpenditureAccount)expenditureAccountsVector.elementAt(expenditureAccountsVector.getIndexSelected());
    }

    public void sort() {
	expenditureAccountsVector.sort();
    }

    public Budget() {
	super(entityType);
    }
    
    public Budget(double _amount) {
	super(entityType);
	money = new Money(_amount);
    }

    public Budget(int _idBudget) {
	super(entityType);
	idBudget = _idBudget;
    }

    public void setIdBudget(int _idBudget) {
	idBudget = _idBudget;
    }

    public int getIdBudget() {
	return idBudget;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setStartDate(String _startDate) {
	startDate = _startDate;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setEndDate(String _endDate) {
	endDate = _endDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setIdPeriodType(int _idPeriodType) {
	idPeriodType = _idPeriodType;
    }

    public int getIdPeriodType() {
	return idPeriodType;
    }

    public void closeBudget() {
	closed = true;
    }


    public void setClosed(boolean _closed) {
	this.closed = _closed;
    }

    public boolean isClosed() {
	return closed;
    }

    public void setDescription(String _description) {
	description = _description;
    }

    public String getDescription() {
	return description;
    }

    public int addData() {
	modifiedAmount = 0;
	String params = "'" + name + "','" + startDate + "','" + endDate + "',0" + 
			initialAmount + ",0" + ((initialAmount + modifiedAmount) - spentAmount) +",0" + modifiedAmount + ",0" + spentAmount + ",0" + spentAmountP +",0"+ 
			assignedAmountToET + ",0" + assignedAmountToETp +",0"+ assignedAmountToCC +",0"+ assignedAmountToCCp +","+ 
			idPeriodType +",'"+ String.valueOf(closed) +"','"+ description + "'";
	idBudget = LibSQL.getInt("cashflow.addBudget",params);
	return idBudget;
    }

    public int setData() {
	String params = idBudget +",'"+ name +"','"+ startDate +"','"+ endDate + "',0" + 
			initialAmount + ",0" + ((initialAmount + modifiedAmount) - spentAmount) + ",0" + modifiedAmount + ",0" + spentAmount + ",0" + spentAmountP +",0"+ 
			assignedAmountToET + ",0" + assignedAmountToETp +",0"+ assignedAmountToCC +",0"+ assignedAmountToCCp +","+ 
			idPeriodType +",'"+ String.valueOf(closed) +"','"+ description + "'";
	return LibSQL.getInt("cashflow.setBudget",params);
    }

    public void retrieveData() {
	try {
	    ResultSet rs = LibSQL.exFunction("cashflow.getBudget",""+ idBudget);
	    if (rs.next()){
		name = rs.getString("name");
		startDate = rs.getString("startdate");
		endDate = rs.getString("enddate");
		initialAmount = rs.getDouble("initialamount");
		availableAmount = rs.getDouble("availableamount");
	        modifiedAmount = rs.getDouble("modifiedamount");
		spentAmount = rs.getDouble("spentamount");
		spentAmountP = rs.getDouble("spentamountp");
		assignedAmountToET = rs.getDouble("assignedamounttoet");
		assignedAmountToETp = rs.getDouble("assignedamounttoetp");
		assignedAmountToCC = rs.getDouble("assignedamounttocc");
		assignedAmountToCCp = rs.getDouble("assignedamounttoccp");
		idPeriodType = rs.getInt("idperiodtype");
		closed = rs.getBoolean("actual");
		description = rs.getString("description"); 
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();   
	}
    }

    public void setIdBudgetCostCentre(int _idBudgetCostCentre) {
	idBudgetCostCentre = _idBudgetCostCentre;
    }

    public int getIdBudgetCostCentre() {
	return idBudgetCostCentre;
    }

    public ExpenditureAccountsGroup getExpenditureAccountsVector() {
	return expenditureAccountsVector;
    }

    public void setExpenditureAccountsVector(ExpenditureAccountsGroup _expenditureAccountsVector) {
	this.expenditureAccountsVector = _expenditureAccountsVector;
    }

    public boolean saveAssignmentToExpenditureAccount() {
	ExpenditureAccount obj;
	for (int i=0; i<expenditureAccountsVector.size(); i++)  {
	    obj = (ExpenditureAccount)expenditureAccountsVector.elementAt(i);
	    if (obj.getInitialAmount() != 0 || obj.getModifiedAmount() != 0){
		String params = idBudget +","+ obj.getIDExpenditureAccount() +",'"+ obj.getStartDate() +"','"+ obj.getEndDate() 
				 +"',"+ obj.getIdPeriodType() 
				 +",0"+ (obj.getInitialAmount()) +",0"+ obj.getInitialAmountP() 
				 +",0"+ obj.getAssignedAmountToET() +",0"+ obj.getAssignedAmountToETp()
				 +",0"+ obj.getAssignedAmountToCC() +",0"+ obj.getAssignedAmountToCCp() 
				 +",'"+ obj.isClosed() +"',"+ obj.getValueBlock() + ",0" + obj.getModifiedAmount();
				 
		LibSQL.getInt("cashflow.saveBudgetExpenditureAccount",params);	    
	    }
	}
	
	return (setData() != -1); /** es llamado para actualizar las cantidades totales de los montos asignados a los tipos de gastos**/
    }

    public void setSpentAmountP(double spentAmountP) {
	this.spentAmountP = spentAmountP;
    }

    public double getSpentAmountP() {
	return spentAmountP;
    }

    public void setSpentAmount(double _spentAmount) {
	spentAmount = _spentAmount;
    }

    public double getSpentAmount() {
	return spentAmount;
    }
    
    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

    public double getAvailableAmount() {
	return availableAmount;
    }

    public void setModifiedAmount(double _modifiedAmount) {
	this.modifiedAmount = _modifiedAmount;
    }

    public double getModifiedAmount() {
	return modifiedAmount;
    }

    public void setAssignedAmountToET(double _assignedAmountToET) {
	assignedAmountToET = _assignedAmountToET;
    }

    public double getAssignedAmountToET() {
	return assignedAmountToET;
    }
    
    public void setAssignedAmountToETp(double assignedAmountToETp) {
	this.assignedAmountToETp = assignedAmountToETp;
    }

    public double getAssignedAmountToETp() {
	return assignedAmountToETp;
    }

    public void setAssignedAmountToCC(double assignedAmountToCC) {
	this.assignedAmountToCC = assignedAmountToCC;
    }

    public double getAssignedAmountToCC() {
	return assignedAmountToCC;
    }

    public void setAssignedAmountToCCp(double assignedAmountToCCp) {
	this.assignedAmountToCCp = assignedAmountToCCp;
    }

    public double getAssignedAmountToCCp() {
	return assignedAmountToCCp;
    }

    public void setInitialAmount(double _initialAmount) {
	initialAmount = _initialAmount;
    }

    public double getInitialAmount() {
	return initialAmount;
    }

    public void setInitialAmountP(double initialAmountP) {
	this.initialAmountP = initialAmountP;
    }

    public double getInitialAmountP() {
	return initialAmountP;
    }

    public void setAccountsAmount(double accountsAmount) {
	this.accountsAmount = accountsAmount;
    }

    public double getAccountsAmount() {
	return accountsAmount;
    }

    public void setIsAssigned(boolean isAssigned) {
	this.isAssigned = isAssigned;
    }

    public boolean isIsAssigned() {
	return isAssigned;
    }

    public void setWOutAssigned(double wOutAssigned) {
	this.wOutAssigned = wOutAssigned;
    }

    public double getWOutAssigned() {
	return wOutAssigned;
    }

    public double updateAccountsAmount(){
	return LibSQL.getDouble("cashflow.setBudgetInitialAmount",""+ idBudget);        
    }

}
