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
 * BookKeepingEntryDetail.java
 *
 * */
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
