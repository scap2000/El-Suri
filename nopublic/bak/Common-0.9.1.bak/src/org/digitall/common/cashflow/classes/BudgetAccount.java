package org.digitall.common.cashflow.classes;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.lib.sql.LibSQL;

public class BudgetAccount {

    private Budget budget;
    private BankAccount bankAccount;
    private double AmountP;
    private int valueBlock;
    
    public BudgetAccount() {

    }

    public void setBudget(Budget budget) {
	this.budget = budget;
    }

    public Budget getBudget() {
	return budget;
    }

    public void setBankAccount(BankAccount _bankAccount) {
	this.bankAccount = _bankAccount;
    }

    public BankAccount getBankAccount() {
	return bankAccount;
    }

    public void setAmountP(double amountP) {
	this.AmountP = amountP;
    }

    public double getAmountP() {
	return AmountP;
    }

    public int saveData(){
	String params = budget.getIdBudget() +","+ bankAccount.getIDBankAccount() +","+ AmountP +","+ valueBlock;
	return LibSQL.getInt("cashflow.saveBudgetBankAccount", params);
    }

    public void setValueBlock(int valueBlock) {
	this.valueBlock = valueBlock;
    }

    public int getValueBlock() {
	return valueBlock;
    }

}
