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
 * Check.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Check {

    private int idCheck = -1;
    private int number;
    private String letter;
    private BankAccount bankAccount;
    private String date;
    private String expiredDate;
    private int idEntity = -1;
    private int idEntityType = 0;
    private double amount;
    private int idCheckStatus;
    private int idCheckType;
    private String description;
    private String fullNumber;
    private String estado = "";
    public static final int NOT_ISSUED = 0;
    public static final int DEBITED = 1;
    public static final int CANCELED = 2;
    public static final int HANDED = 3;
    public static final int TO_DEBIT = 4;
    private boolean enabled = false;

    public Check(BankAccount _account) {
	bankAccount = _account;
    }

    public Check() {
    }

    public Check(int _idCheck) {
	idCheck = _idCheck;
    }

    public void setIdCheck(int idCheck) {
	this.idCheck = idCheck;
    }

    public int getIdCheck() {
	return idCheck;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public int getNumber() {
	return number;
    }

    public void setLetter(String letter) {
	this.letter = letter;
    }

    public String getLetter() {
	return letter;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    public void setExpiredDate(String expiredDate) {
	this.expiredDate = expiredDate;
    }

    public String getExpiredDate() {
	return expiredDate;
    }

    public void setIdEntity(int idEntity) {
	this.idEntity = idEntity;
    }

    public int getIdEntity() {
	return idEntity;
    }

    public void setIdEntitytype(int idEntitytype) {
	this.idEntityType = idEntitytype;
    }

    public int getIdEntitytype() {
	return idEntityType;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setIdCheckStatus(int idCheckStatus) {
	this.idCheckStatus = idCheckStatus;
    }

    public int getIdCheckStatus() {
	return idCheckStatus;
    }

    public void setIdCheckType(int idCheckType) {
	this.idCheckType = idCheckType;
    }

    public int getIdCheckType() {
	return idCheckType;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public int addData() {
	String params = number + ",'" + letter + "'," + bankAccount.getIDBankAccount() + ",'" + date + "','" + expiredDate + "'," + idEntity + "," + idEntityType + "," + amount + "," + idCheckStatus + "," + idCheckType + ",'" + description + "','" + estado + "'";
	idCheck = LibSQL.getInt("cashflow.addCheck", params);
	return idCheck;
    }

    public boolean generate(int _qty) {
	String params = _qty + "," + number + ",'" + letter + "'," + bankAccount.getIDBankAccount() + "," + idEntity + "," + idEntityType + "," + idCheckStatus + "," + idCheckType + ",'" + description + "'";
	return LibSQL.getBoolean("cashflow.addChecks", params);
    }

    public int setData() {
	String params = idCheck + "," + 
                        number + ",'" + 
                        letter + "'," + 
                        bankAccount.getIDBankAccount() + ",'" + 
                        date + "','" + 
                        expiredDate + "'," + 
                        idEntity + "," + 
                        idEntityType + "," + 
                        amount + "," + 
                        idCheckStatus + "," + 
                        idCheckType + ",'" + 
                        description + "'";
	return LibSQL.getInt("cashflow.setCheck", params);
    }

    public int updateAmount() {
	String params = idCheck + "," + amount;
	return LibSQL.getInt("cashflow.updateCheckAmount", params);
    }

    public void setAccount(BankAccount account) {
	this.bankAccount = account;
    }

    public BankAccount getAccount() {
	return bankAccount;
    }

    public int updateStatus(int _type) {
	String params = idCheck + "," + _type;
	return LibSQL.getInt("cashflow.setCheckStatus", params);
    }

    public void retrieveData() {
	try {
	    String params = "" + idCheck;
	    ResultSet rs = LibSQL.exFunction("cashflow.getCheck", params);
	    if (rs.next()) {
		number = rs.getInt("number");
		letter = rs.getString("letter");
		bankAccount = new BankAccount(rs.getInt("idbankaccount"));
		bankAccount.setAmount(rs.getDouble("accountamount"));
		bankAccount.setAccount(new Account(rs.getInt("idaccount")));
		date = rs.getString("date");
		expiredDate = rs.getString("expireddate");
		idEntity = rs.getInt("identity");
		idEntityType = rs.getInt("identitytype");
		amount = rs.getDouble("amount");
		idCheckStatus = rs.getInt("idcheckstatus");
		idCheckType = rs.getInt("idchecktype");
		description = rs.getString("description");
		enabled = rs.getBoolean("enabled");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public boolean rollBack() {
        return LibSQL.getBoolean("cashflow.rollBackCheck",""+ idCheck);
    }

    public void setFullNumber(String fullNumber) {
	this.fullNumber = fullNumber;
    }

    public String getFullNumber() {
	return fullNumber;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public boolean isEnabled() {
	return enabled;
    }

}
