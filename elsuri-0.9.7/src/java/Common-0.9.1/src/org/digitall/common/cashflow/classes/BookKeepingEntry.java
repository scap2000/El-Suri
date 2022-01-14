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
 * BookKeepingEntry.java
 *
 * */
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
