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
 * CashMovementType.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class CashMovementType {

    private int idCashMovementType = -1;
    private String Name;
    private int idParent;
    private int idCashMovementTypeRef;
    private int Sign;
    private String Description;
			
    public CashMovementType() {

    }
    
    public CashMovementType(int _idCashMovementType) {
	idCashMovementType = _idCashMovementType;
    }

    public int saveData(){
	String params = idCashMovementType +",'"+ Name +"',"+ idParent +","+ idCashMovementTypeRef +","+ Sign +",'"+ Description +"'";
	int result = LibSQL.getInt("cashflow.saveCashMovementType", params);
	if (result > 0){
	    idCashMovementType = result;
	}
	return result;
    }
    
    public void retrieveData() {
        ResultSet data = LibSQL.exFunction("cashflow.getCashMovementTypes","" + idCashMovementType);
        try  {
            if (data.next())  {
                Name = data.getString("name");
                idParent = data.getInt("idparent");
                idCashMovementTypeRef = data.getInt("idcashmovementtyperef");
                Sign = data.getInt("sign");
                Description = data.getString("description");
            }
               
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    public void setIdCashMovementType(int idCashMovementType) {
	this.idCashMovementType = idCashMovementType;
    }

    public int getIdCashMovementType() {
	return idCashMovementType;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public String getName() {
	return Name;
    }

    public void setIdParent(int idParent) {
	this.idParent = idParent;
    }

    public int getIdParent() {
	return idParent;
    }

    public void setIdCashMovementTypeRef(int idCashMovementTypeRef) {
	this.idCashMovementTypeRef = idCashMovementTypeRef;
    }

    public int getIdCashMovementTypeRef() {
	return idCashMovementTypeRef;
    }

    public void setSign(int sign) {
	this.Sign = sign;
    }

    public int getSign() {
	return Sign;
    }

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }

}
