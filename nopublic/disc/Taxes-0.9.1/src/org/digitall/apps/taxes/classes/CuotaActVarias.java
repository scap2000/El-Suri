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
 * CuotaActVarias.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class CuotaActVarias {

    private int number = -1;
    private int year = -1;
    private int idcomercio = -1;
    private int feetype = -1;
    private String date = "";
    private String expiration = "";
    private double basicamount = 0;
    private double interestrate = 0;
    private double accruedinterest = 0;
    private double discountrate = 0;
    private double accrueddiscount = 0;
    private String updated = "";
    private double totalamount = 0;
    private double paidpercentage = 0;
    private int idvoucher = -1;
    private double interesMensual = 0;
    private int diasMora = 0;
    
    public CuotaActVarias() {
    
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setIdcomercio(int idcomercio) {
        this.idcomercio = idcomercio;
    }

    public int getIdcomercio() {
        return idcomercio;
    }

    public void setFeetype(int feetype) {
        this.feetype = feetype;
    }

    public int getFeetype() {
        return feetype;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setBasicamount(double basicamount) {
        this.basicamount = basicamount;
    }

    public double getBasicamount() {
        return basicamount;
    }

    public void setInterestrate(double interestrate) {
        this.interestrate = interestrate;
    }

    public double getInterestrate() {
        return interestrate;
    }

    public void setAccruedinterest(double accruedinterest) {
        this.accruedinterest = accruedinterest;
    }

    public double getAccruedinterest() {
        return accruedinterest;
    }

    public void setDiscountrate(double discountrate) {
        this.discountrate = discountrate;
    }

    public double getDiscountrate() {
        return discountrate;
    }

    public void setAccrueddiscount(double accrueddiscount) {
        this.accrueddiscount = accrueddiscount;
    }

    public double getAccrueddiscount() {
        return accrueddiscount;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdated() {
        return updated;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setPaidpercentage(double paidpercentage) {
        this.paidpercentage = paidpercentage;
    }

    public double getPaidpercentage() {
        return paidpercentage;
    }

    public void setIdvoucher(int idvoucher) {
        this.idvoucher = idvoucher;
    }

    public int getIdvoucher() {
        return idvoucher;
    }
    
    public void setInteresMensual(double _interesMensual) {
        interesMensual = _interesMensual;
    }

    public double getInteresMensual() {
        return interesMensual;
    }

    public void setDiasMora(int _diasMora) {
        diasMora = _diasMora;
    }

    public int getDiasMora() {
        return diasMora;
    }
    
    public void retrieveData(){
        try {
            ResultSet rs = LibSQL.exFunction("taxes.getCuotaActVarias", ""+ idcomercio +","+ number + ","+ year);
            if(rs.next()){
                number = rs.getInt("number");
                year = rs.getInt("year");
                idcomercio = rs.getInt("idcomercio");
                feetype = rs.getInt("feetype");
                date = rs.getString("date");
                expiration = rs.getString("expiration");
                basicamount = rs.getDouble("basicamount");
                interestrate = rs.getDouble("interestrate");
                accruedinterest = rs.getDouble("accruedinterest");
                discountrate = rs.getDouble("discountrate");
                accrueddiscount = rs.getDouble("accrueddiscount");
                updated = rs.getString("updated");
                totalamount = rs.getDouble("totalamount");
                paidpercentage = rs.getDouble("paidpercentage");
                idvoucher = rs.getInt("idvoucher");
                interesMensual = rs.getDouble("interesmensual");
                diasMora = rs.getInt("diasdemora");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
