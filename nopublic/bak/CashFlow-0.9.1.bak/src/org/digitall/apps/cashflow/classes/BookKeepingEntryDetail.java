package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.lib.sql.LibSQL;

public class BookKeepingEntryDetail {

    private int idBookKeepingEntryDetail = -1;
    private BookKeepingEntry bookKeepingEntry;
    private Account account;
    private Voucher voucher;
    private double debitAmount;
    private double creditAmount;
    private String observations;
    private String refNumber = "";
    private int idmodel = -1;
    private int idbudget = -1;
    private int idcostcentre = -1;

    public BookKeepingEntryDetail() {

    }

    public void setIDBookKeepingEntryDetail(int _idBookKeepingEntryDetail) {
	idBookKeepingEntryDetail = _idBookKeepingEntryDetail;
    }

    public int getIDBookKeepingEntryDetail() {
	return idBookKeepingEntryDetail;
    }

    public void setBookKeepingEntry(BookKeepingEntry _bookKeepingEntry) {
	bookKeepingEntry = _bookKeepingEntry;
    }

    public BookKeepingEntry getBookKeepingEntry() {
	return bookKeepingEntry;
    }

    public void setAccounting(Account accounting) {
	account = accounting;
    }

    public Account getAccounting() {
	return account;
    }

    public void setVoucher(Voucher _voucher) {
	voucher = _voucher;
    }

    public Voucher getVoucher() {
	return voucher;
    }

    public void setObservations(String _observations) {
	observations = _observations;
    }

    public String getObservations() {
	return observations;
    }

    public int saveData(){
	String params = ""+ bookKeepingEntry.getIDBookKeepingEntry() +","+ account.getIDAccount() 
			+","+ voucher.getIdVoucher() +","+ debitAmount +","+ creditAmount 
                            +",'"+ observations +"','"+ refNumber +"',"+ idbudget + ","+ idcostcentre ;
	int result = -1;
	if (idBookKeepingEntryDetail == -1){
	    result = LibSQL.getInt("accounting.addBookKeepingEntryDetail", params);
	    idBookKeepingEntryDetail = result;
	} else {
	    
	}
	return result;
    }

    public int deleteData() {
        int result = LibSQL.getInt("accounting.delBookKeepingEntryDetail", "" + idBookKeepingEntryDetail);
        return result;
    }

    public void setDebitAmount(double _debitAmount) {
	debitAmount = _debitAmount;
    }

    public double getDebitAmount() {
	return debitAmount;
    }

    public void setCredtAmount(double _creditAmount) {
	creditAmount = _creditAmount;
    }

    public double getCredtAmount() {
	return creditAmount;
    }

    public void setRefNumber(String refNumber) {
	this.refNumber = refNumber;
    }

    public String getRefNumber() {
	return refNumber;
    }

    public void setIdmodel(int _idmodel) {
        idmodel = _idmodel;
    }

    public int getIdmodel() {
        return idmodel;
    }
    
    public int saveDataByModel(){
        String params = ""+ bookKeepingEntry.getIDBookKeepingEntry() +","+ account.getIDAccount() 
                        +","+ voucher.getIdVoucher() +","+ debitAmount +","+ creditAmount 
                        +",'"+ observations +"','"+ refNumber +"',"+ idmodel +","+ idbudget + ","+ idcostcentre ;
        int result = -1;
        if (idBookKeepingEntryDetail == -1){
            result = LibSQL.getInt("accounting.addBookKeepingEntryDetail", params);
            idBookKeepingEntryDetail = result;
        } else {
            
        }
        return result;
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
