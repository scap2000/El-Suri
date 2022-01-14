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
 * CostsCentre.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JTree;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.lib.sql.LibSQL;

//

public class CostsCentre extends Entity {

    private static final int entityType = EntityTypes.COSTS_CENTRE;
    //PRODUCTION

    /**
     * @associates <{org.sermaq.apps.entities.Money}>
     */
    private Money money;
    //CONSUMPTION

    /**
     * @associates <{org.sermaq.apps.entities.ServiceHours}>
     */
    private Vector serviceHour;

    /**
     * @associates <{org.sermaq.apps.entities.ManHours}>
     */
    private Vector manHour;

    /**
     * @associates <{org.sermaq.apps.entities.ConsumerGood}>
     */
    private Vector consumerGood;

    /**
     * @associates <{org.sermaq.apps.entities.UserGood}>
     */
    private Vector userGood;
    //REQUESTING

    /**
     * @associates <{org.sermaq.apps.entities.Budget}>
     */
    private Budget budget;

    /**
     * @associates <{org.sermaq.apps.entities.Service}>
     */
    private Vector service;

    /**
     * @associates <{org.sermaq.apps.entities.People}>
     */
    private Vector people;

    /**
     * @associates <{org.sermaq.apps.entities.Task}>
     */
    private Vector task;

    /** ATTRIBUTES */
    private int idCostCentre = -1;
    private String code;
    private String name;
    private String description;
    private CCExpenditureAccountsGroup expensesVector = new CCExpenditureAccountsGroup();
    private double initialAmount;
    private double spentAmount;
    private double spentAmountP;
    private double availableAmount;
    private double modifiedAmount;
    private boolean provisionOrder = false;

    public CostsCentre() {
	super(entityType);
    }
    
    public CostsCentre(double _amount) {
	super(entityType);
	money = new Money(_amount);
    }

    public CostsCentre(int _idCostCentre) {
	super(entityType);
	idCostCentre = _idCostCentre;
    }
    
    public CostsCentre(String _userName) {
	super(entityType);
	//getDataByUserName(_userName);
    }

    public JTree getExpenditureAccountsTree() {
	try {
	    expensesVector = new CCExpenditureAccountsGroup(this);
	    return expensesVector.createTree();
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }
    
    private boolean produceMoney(double _amount) {
	boolean _produce = false;
	if (doProduction(EntityTypes.MONEY, _amount)) {
	    _produce = money.produce(_amount);
	}
	return _produce;
    }

    private boolean consumeMoney(double _amount) {
	boolean _consume = false;
	if (doConsumption(EntityTypes.MONEY, _amount)) {
	    _consume = money.consume(_amount);
	}
	return _consume;
    }

    private boolean requestBudget(double _amount) {
	boolean _request = false;
	if (doRequest(EntityTypes.MONEY, _amount)) {
	    _request = budget.request(_amount);
	}
	return _request;
    }

    private boolean requestMoney(double _amount) {
	boolean _request = false;
	if (doRequest(EntityTypes.MONEY, _amount)) {
	    _request = money.request(_amount);
	}
	return _request;
    }

    private boolean consumeEntity(Entity _entity, double _amount) {
	boolean _consume = false;
	if (doConsumption(_entity.getEntityType(), _amount)) {
	    int index = getEntityIndex(_entity);
	    if (index >= 0) {
		_consume = ((Entity)getContainerVector(_entity).elementAt(index)).consume(_amount);
	    }
	}
	return _consume;
    }

    private boolean requestEntity(Entity _entity, double _amount) {
	boolean _request = false;
	if (doRequest(_entity.getEntityType(), _amount)) {
	    int index = getEntityIndex(_entity);
	    if (index >= 0) {
		_request = ((Entity)getContainerVector(_entity).elementAt(index)).request(_amount);
	    }
	}
	return _request;
    }

    public Vector getContainerVector(Entity _entity) {
	Vector _entitiesVector = new Vector();
	switch (_entity.getEntityType()) {
	    case EntityTypes.USE_GOOD:
		_entitiesVector = userGood;
		break;
	    case EntityTypes.CONSUMPTION_GOOD:
		_entitiesVector = consumerGood;
		break;
	    case EntityTypes.SERVICE:
		_entitiesVector = service;
		break;
	    case EntityTypes.SERVICE_HOUR:
		_entitiesVector = serviceHour;
		break;
	    case EntityTypes.PERSON:
		_entitiesVector = people;
		break;
	    case EntityTypes.MAN_HOUR:
		_entitiesVector = manHour;
		break;
	    case EntityTypes.TASK:
		_entitiesVector = task;
		break;
	}
	return _entitiesVector;
    }

    /**
     * Returns the index of the Entity in any Vector
     * @param _entity
     * @return
     */
    private int getEntityIndex(Entity _entity) {
	int index = -1;
	if (haveEntity(_entity)) {
	    Vector _entitiesVector = getContainerVector(_entity);
	    int i = 0;
	    boolean found = false;
	    while (i < _entitiesVector.size() && !found) {
		if (_entity == (Entity)_entitiesVector.elementAt(i)) {
		    index = i;
		    found = true;
		}
		i++;
	    }
	}
	return index;
    }

    private boolean haveEntity(Entity _entity) {
	return getContainerVector(_entity).contains(_entity);
    }

    public boolean addEntity(Entity _entity) {
	boolean _add = false;
	if (!haveEntity(_entity)) {
	    _add = getContainerVector(_entity).add(_entity);
	}
	return _add;
    }

    private boolean useEntity(Entity _entity, double _amount) {
	boolean _use = false;
	if (doUse(_entity.getEntityType(), _amount)) {
	    int index = getEntityIndex(_entity);
	    if (index >= 0) {
		_use = ((Entity)getContainerVector(_entity).elementAt(index)).use(_amount);
	    }
	}
	return _use;
    }

    public void setIdCostCentre(int idCostCentre) {
	this.idCostCentre = idCostCentre;
    }

    public int getIdCostCentre() {
	return idCostCentre;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    private void loadData(ResultSet _rs){
	try {
	    if (_rs.next()){
		idCostCentre = _rs.getInt("idcostcentre");
		name = _rs.getString("name");
		code = _rs.getString("code");
		description = _rs.getString("description");
		initialAmount = _rs.getDouble("initialamount");
		spentAmount = _rs.getDouble("spentamount");
		spentAmountP = _rs.getDouble("spentamountp");
		availableAmount = _rs.getDouble("availableamount");
		modifiedAmount = _rs.getDouble("modifiedamount");
	    } else {
	        idCostCentre = 1;
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public void getDataByID(int _idCostCentre){
	loadData(LibSQL.exFunction("cashflow.getCostsCentre",""+ _idCostCentre));
    }
    
    /*public void getDataByUserName(String _userName){
	loadData(LibSQL.exFunction("cashflow.getCostsCentreByUserName","'"+ _userName +"'"));
    }*/
    
    public int addData() {
	String params = "'" + name + "','" + description + "','" + code.toUpperCase() + "'";
	idCostCentre = LibSQL.getInt("cashflow.addCostCentre",params);
	//LibSQL.getInt("cashflow.addCostsCentreAmounts",""+ idCostCentre);	
	return idCostCentre;
    }

    public int setData() {
	String params = idCostCentre + ",'" + name + "','" + description + "','" + code.toUpperCase() + "'";
	return LibSQL.getInt("cashflow.setCostCentre", params);
    }

    public boolean assignBudget(ExpenditureAccount _expenditureAccount, boolean _isNew, boolean _isAssigned) {
	int result = -1;
	if (budget.getStartDate() == null)
	    budget.setStartDate("");
	
	if (budget.getEndDate() == null)
	    budget.setEndDate("");
	    
	String params = "";
	int idBudgetCostCentre = 0;
	if (_isNew){
	    params = budget.getIdBudget() +","+ idCostCentre +",'"+ budget.getStartDate() +"','"+ budget.getEndDate() +"',"+ 
		     budget.getIdPeriodType() +","+ budget.getInitialAmount() +","+ budget.getInitialAmountP() +","+ 
		     budget.getAvailableAmount()  +","+ budget.getModifiedAmount()  +","+ 
		     budget.getSpentAmount() +","+ budget.getSpentAmountP() +",'"+ budget.isClosed() +"','"+ _isAssigned +"'";
		     
	    result = idBudgetCostCentre = LibSQL.getInt("cashflow.addBudgetCostCentre", params);
	    
	    if (_expenditureAccount != null){
		params = idBudgetCostCentre +","+ _expenditureAccount.getIDExpenditureAccount() +",'"+ 
			_expenditureAccount.getStartDate() +"','"+ _expenditureAccount.getEndDate() +"',"+ 
			_expenditureAccount.getIdPeriodType() +","+ _expenditureAccount.getInitialAmount() +","+ 
			_expenditureAccount.getInitialAmountP() +","+ _expenditureAccount.getInitialAmount() +","+ 
			_expenditureAccount.getModifiedAmount() +",0,0,'"+ _expenditureAccount.isClosed() +"',"+ 
			_expenditureAccount.getValueBlock();
		result = LibSQL.getInt("cashflow.addBudgetCostCentreDetail", params);    
	    }
	} else {
	    idBudgetCostCentre = budget.getIdBudgetCostCentre();
	    if (_expenditureAccount == null){
	        params = idBudgetCostCentre +",'"+ budget.getStartDate() +"','"+ budget.getEndDate() +"',"+ budget.getIdPeriodType() +","+ 
			 budget.getInitialAmount() +","+ budget.getInitialAmountP();
	        result = LibSQL.getInt("cashflow.setBudgetCostCentre",params);    
	    } else {
		params = idBudgetCostCentre +","+ _expenditureAccount.getIDExpenditureAccount() +",'"+ 
			_expenditureAccount.getStartDate() +"','"+ 
			_expenditureAccount.getEndDate() +"',"+ _expenditureAccount.getIdPeriodType() +","+ 
			_expenditureAccount.getInitialAmount() +","+ _expenditureAccount.getInitialAmountP() +","+_expenditureAccount.getModifiedAmount()+",0,0,0,'"+ 
			_expenditureAccount.isClosed() +"',"+ _expenditureAccount.getValueBlock();
		result = LibSQL.getInt("cashflow.setBudgetCostCentreDetail",params);    
	    }
	}	
	
	if (params.length()>0 && _expenditureAccount != null && result != -1){
	    String idParent = String.valueOf(_expenditureAccount.getIDExpenditureAccount());
	    while (!idParent.equals("0")) {		
	        /**Actualiza el monto total asignado por Tipo de Gasto**/
	        //LibSQL.getInt("cashflow.setCostsCentreAssignedAmountDetail",idCostCentre + ","+ idParent);

	        /**Actualiza el monto total asignado por CC en los tipos de gastos segun la partida en cuestion**/
	        LibSQL.getInt("cashflow.setBudgetExpenditureAccountTotalToCC",budget.getIdBudget() +","+ idParent);
		
		if (_isNew){
		    params = idBudgetCostCentre +","+ idParent +",'"+ _expenditureAccount.getStartDate() +"','"+ 
			    _expenditureAccount.getEndDate() +"',"+ _expenditureAccount.getIdPeriodType() +",0"+ 
			    _expenditureAccount.getInitialAmount() +",0"+ _expenditureAccount.getInitialAmountP() +",0"+ 
			    _expenditureAccount.getInitialAmount() +",0"+ _expenditureAccount.getModifiedAmount()
			    +",0,0,'"+ _expenditureAccount.isClosed() +"',"+ budget.getIdBudget() +","+ _expenditureAccount.getValueBlock();    
		    idParent = String.valueOf(LibSQL.getInt("cashflow.addBudgetCostCentreDetailForParent",params));
		} else {
		    params = idBudgetCostCentre +","+ idParent +","+ budget.getIdBudget() +","+ _expenditureAccount.getValueBlock();        
		    idParent = String.valueOf(LibSQL.getInt("cashflow.setBudgetCostCentreDetailForParent",params));
		}
	    }
	}
	LibSQL.getInt("cashflow.setBudgetCostCentreAmount", idBudgetCostCentre +","+ budget.getIdBudget());    
	//LibSQL.getInt("cashflow.setCostsCentreAssignedAmount",""+ idCostCentre);
	LibSQL.getInt("cashflow.setBudgetTotalToCC",""+ budget.getIdBudget());
	
	return true;
    }

    public void setBudget(Budget budget) {
	this.budget = budget;
    }

    public Budget getBudget() {
	return budget;
    }

    public void loadBudget(int _idBudget) {
	budget = new Budget(_idBudget);
	//budget.retrieveData();
    }

    public void setInitialAmount(double initialAmount) {
	this.initialAmount = initialAmount;
    }

    public double getInitialAmount() {
	return initialAmount;
    }

    public void setSpentAmount(double spentAmount) {
	this.spentAmount = spentAmount;
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

    public void setSpentAmountP(double spentAmountP) {
	this.spentAmountP = spentAmountP;
    }

    public double getSpentAmountP() {
	return spentAmountP;
    }

    public void setModifiedAmount(double _modifiedAmount) {
	this.modifiedAmount = _modifiedAmount;
    }

    public double getModifiedAmount() {
	return modifiedAmount;
    }

    /*public int setUser(int _idPerson) {
	String params = _idPerson +","+ idCostCentre; 
	return LibSQL.getInt("cashflow.setCostsCentreUser", params);
    }*/
    
    public int setProvisionOrder(){
        int result = -1;
        result = LibSQL.getInt("cashflow.setCostCentreByProvisionOrder","" + idCostCentre + "," + provisionOrder);
        return result;
    }

    public void setIsProvisionOrder(boolean _provisionOrder) {
        this.provisionOrder = _provisionOrder;
    }

    public boolean isProvisionOrder() {
        return provisionOrder;
    }
}
