package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.lib.sql.LibSQL;

public class BookkKeepingEntryPaymentWay {

    private int idBookKeepingEntryPaymentWay = -1;
    private int idBookKeepingEntry = -1;
    private Account accounting; 
    private double amount;
    private Check check;
    private int idPaymentType;
    private String concept = "";
    private String refNumber = "";
    private int noteType = 1; //nota de ingreso
    private int idbudget = -1;
    private int idcostcentre = -1;

    public BookkKeepingEntryPaymentWay() {
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAccount(Account _accounting) {
        accounting = _accounting;
    }

    public Account getAccount() {
        return accounting;
    }
    
    public void setCheck(Check _check) {
        check = _check;
    }

    public Check getCheck() {
        return check;
    }
    
    public void setIdPaymentType(int idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    public int getIdPaymentType() {
        return idPaymentType;
    }
    
    public int saveData(){
        String params = idBookKeepingEntry +","+ accounting.getIDAccount() +","+ check.getIdCheck()
                        +","+ amount  +","+ idPaymentType +",'"+ concept +"','"+ refNumber 
                        +"',"+ noteType +","+ idbudget +","+ idcostcentre;
        int result = -1;
        if (idBookKeepingEntryPaymentWay == -1){
            result = LibSQL.getInt("accounting.addBookKeepingEntryPaymentWay",params);
            idBookKeepingEntryPaymentWay = result;
        } else {
            params = idBookKeepingEntryPaymentWay +","+ params;
            result = LibSQL.getInt("accounting.setBookKeepingEntryPaymentWay",params); // No creada
        }
        return result;
    }
    
    public int delete(){
        if (check != null) {
            check.rollBack();
        }
        return LibSQL.getInt("accounting.deleteBookKeepingEntryPaymentWay", ""+ idBookKeepingEntryPaymentWay);  // No creada
    }

    public void setIdBookKeepingEntryPaymentWay(int _idBookKeepingEntryPaymentWay) {
	idBookKeepingEntryPaymentWay = _idBookKeepingEntryPaymentWay;
    }

    public int getIdBookKeepingEntryPaymentWay() {
	return idBookKeepingEntryPaymentWay;
    }

    public void setIdBookKeepingEntry(int _idBookKeepingEntry) {
	idBookKeepingEntry = _idBookKeepingEntry;
    }

    public int getIdBookKeepingEntry() {
	return idBookKeepingEntry;
    }

    public void setConcept(String _concept) {
        concept = _concept;
    }

    public String getConcept() {
        return concept;
    }

    public void setRefNumber(String _refNumber) {
        refNumber = _refNumber;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setNoteType(int _noteType) {
        noteType = _noteType;
    }

    public int getNoteType() {
        return noteType;
    }

    public void setIdbudget(int _idbudget) {
        idbudget = _idbudget;
    }

    public int getIdbudget() {
        return idbudget;
    }

    public void setIdcostcentre(int _idcostcentre) {
        idcostcentre = _idcostcentre;
    }

    public int getIdcostcentre() {
        return idcostcentre;
    }
}
