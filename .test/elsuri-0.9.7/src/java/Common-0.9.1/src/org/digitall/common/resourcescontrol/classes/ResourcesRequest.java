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
 * ResourcesRequest.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.sql.LibSQL;

public class ResourcesRequest {

    private int idresourcesrequest = -1;
    private String date = "";
    private double estimateamount = 0;
    private String description = "";
    private int idcostcentre = -1;
    private String estado = "";
    private double estimatedamountauth = 0;
    private int idsolicitor = -1;
    private int idrequeststatus = -1;
    private int number = -1;
    private int idpriorityrequest = -1;
    private String approved = "";
    private String dateapproved = "";
    private String liberationdate = "";
    
    public ResourcesRequest() {
    }

    public void setIdresourcesrequest(int idresourcesrequest) {
        this.idresourcesrequest = idresourcesrequest;
    }

    public int getIdresourcesrequest() {
        return idresourcesrequest;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setEstimateamount(double estimateamount) {
        this.estimateamount = estimateamount;
    }

    public double getEstimateamount() {
        return estimateamount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIdcostcentre(int idcostcentre) {
        this.idcostcentre = idcostcentre;
    }

    public int getIdcostcentre() {
        return idcostcentre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstimatedamountauth(double estimatedamountauth) {
        this.estimatedamountauth = estimatedamountauth;
    }

    public double getEstimatedamountauth() {
        return estimatedamountauth;
    }

    public void setIdsolicitor(int idsolicitor) {
        this.idsolicitor = idsolicitor;
    }

    public int getIdsolicitor() {
        return idsolicitor;
    }

    public void setIdrequeststatus(int idrequeststatus) {
        this.idrequeststatus = idrequeststatus;
    }

    public int getIdrequeststatus() {
        return idrequeststatus;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setIdpriorityrequest(int idpriorityrequest) {
        this.idpriorityrequest = idpriorityrequest;
    }

    public int getIdpriorityrequest() {
        return idpriorityrequest;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApproved() {
        return approved;
    }

    public void setDateapproved(String dateapproved) {
        this.dateapproved = dateapproved;
    }

    public String getDateapproved() {
        return dateapproved;
    }

    public void setLiberationdate(String liberationdate) {
        this.liberationdate = liberationdate;
    }

    public String getLiberationdate() {
        return liberationdate;
    }
    
    public void retrieveData(){
        ResultSet data = LibSQL.exFunction("resourcescontrol.getResourcesRequest","" + idresourcesrequest);
        try  {
            if (data.next()) {
                idresourcesrequest = data.getInt("idpurchaseorder");
                date = data.getString("date");
                estimateamount = data.getDouble("estimateamount");
                description = data.getString("description");
                idcostcentre = data.getInt("idcostcentre");
                estado = data.getString("estado");
                estimatedamountauth = data.getDouble("estimatedamountauth");
                idsolicitor = data.getInt("idsolicitor");
                idrequeststatus = data.getInt("idrequeststatus");
                number = data.getInt("number");
                idpriorityrequest = data.getInt("idpriorityrequest");
                approved = data.getString("approved");
                dateapproved = data.getString("dateapproved");
                liberationdate = data.getString("liberationdate");
            }
            
        } catch (SQLException ex)  {
            ex.printStackTrace();
        }
    }
        
    
}
