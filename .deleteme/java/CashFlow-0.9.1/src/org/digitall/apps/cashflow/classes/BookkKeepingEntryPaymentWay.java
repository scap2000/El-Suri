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
 * BookkKeepingEntryPaymentWay.java
 *
 * */
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
