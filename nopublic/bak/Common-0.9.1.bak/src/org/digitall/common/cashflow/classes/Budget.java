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
