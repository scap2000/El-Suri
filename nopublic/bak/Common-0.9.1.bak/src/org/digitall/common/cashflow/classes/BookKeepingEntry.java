package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BookKeepingEntry {

    private int idBookKeepingEntry = -1;
    private String date;
    private String concept;
    private double debitAmount = 0;
    private double creditAmount = 0;
    private double availableAmount = 0;
    private int number = -1;
    private int noteType = -1;
    
    public BookKeepingEntry() {

    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    public void setConcept(String concept) {
	this.concept = concept;
    }

    public String getConcept() {
	return concept;
    }

    public void setDebitAmount(double debitAmount) {
	this.debitAmount = debitAmount;
    }

    public double getDebitAmount() {
	return debitAmount;
    }

    public void setCreditAmount(double creditAmount) {
	this.creditAmount = creditAmount;
    }

    public double getCreditAmount() {
	return creditAmount;
    }

    public int saveData(){
	String params = "'"+ date +"','"+ concept +"',"+ debitAmount +","+ creditAmount;
	int result = -1;
	if (idBookKeepingEntry == -1){
	    result = LibSQL.getInt("accounting.addBookKeepingEntry", params);
	    idBookKeepingEntry = result;
	}
	return result;
    }

    public int saveDataByModels() {
        String params = "'"+ date +"','"+ concept +"',"+ debitAmount +","+ creditAmount +","+ noteType;
        int result = -1;
        if (idBookKeepingEntry == -1) {
            result = LibSQL.getInt("accounting.addBookKeepingEntryByModel", params);
            idBookKeepingEntry = result;
        }
        return result;
    }


    /**S.P. creado para ser utilizado desde las clases CredidtNotesMgmt minY DebitNotesMgmt
     */
    public int saveDataByNotes(){
        String params = "'"+ date +"','"+ concept +"',"+ debitAmount +","+ creditAmount +","+ noteType ;
        int result = -1;
        if (idBookKeepingEntry == -1) {
            result = LibSQL.getInt("accounting.addBookKeepingEntryByNotes", params);
            idBookKeepingEntry = result;
        }
        return result;
    }


    /**S.P. creado para ser utilizado desde las clases CredidtNotesAdmin minY DebitNotesAdmin
     */
    public int saveDataAdmin() {
        String params = "'"+ date +"','"+ concept +"',"+ debitAmount +","+ creditAmount +","+ noteType +","+ number;
        int result = -1;
        if (idBookKeepingEntry == -1) {
            result = LibSQL.getInt("accounting.addBookKeepingEntryByNotes", params);
            idBookKeepingEntry = result;
        }
        return result;
    }


    public void setIDBookKeepingEntry(int _idBookKeepingEntry) {
	idBookKeepingEntry = _idBookKeepingEntry;
    }

    public int getIDBookKeepingEntry() {
	return idBookKeepingEntry;
    }

    public int updateAmount(){
	return LibSQL.getInt("accounting.updateBookKeepingEntryAmount", ""+ idBookKeepingEntry);
    }

    public void getData(){
	try {
	    ResultSet rs = LibSQL.exFunction("accounting.getBookKeepingEntry", ""+ idBookKeepingEntry);
	    if (rs.next()){
		date = rs.getString("date");
		concept = rs.getString("concept");
		debitAmount = rs.getDouble("debitamount");
		creditAmount = rs.getDouble("creditamount");
		availableAmount = debitAmount - creditAmount;
		number = rs.getInt("number");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

    public double getAvailableAmount() {
	return availableAmount;
    }

    public void setNumber(int _number) {
	number = _number;
    }

    public int getNumber() {
	return number;
    }

    public boolean rollBack(){
	return LibSQL.getBoolean("accounting.rollBackBookKeepingEntry",""+ idBookKeepingEntry);
    }
    
    public boolean approve(){
	return LibSQL.getBoolean("accounting.approveBookKeepingEntry",""+ idBookKeepingEntry);
    }
    
    public int delete(){
	return LibSQL.getInt("accounting.delBookKeepingEntry",""+ idBookKeepingEntry);
    }

    public void setNoteType(int _noteType) {
        noteType = _noteType;
    }

    public int getNoteType() {
        return noteType;
    }
}
