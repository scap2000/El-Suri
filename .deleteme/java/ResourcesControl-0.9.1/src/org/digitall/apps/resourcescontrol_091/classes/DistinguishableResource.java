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
 * DistinguishableResource.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.lib.org.Persons;
import org.digitall.lib.sql.LibSQL;

/**La clase DistinguishableResource representa un recurso que
 * por definición DEBE SER DISTINGUIBLE = TRUE
 * y su STOCK UNICAMENTE 1 (UNO)
 * @see DistinguishableResource ()
 */
public class DistinguishableResource extends ResourceBrands {

    private int idDistinguishableResource = -1;
    private String acquisitionDate = "";
    private String dropOffDate = "";
    private Depot depot;
    private Persons person;
    private double waste = 0;
    private int idWasteUnit = -1;
    private int idIdentificationType = -1;
    private String identificationType = "";
    private String identificationTypeNumber = "";
    private int idLifeTimeType = -1;
    private Double price = 0.0;
    private int idResourceCondition = 0;
    private String resourceCondition = "";
    private String dateOfLoad = "";

    public DistinguishableResource() {
	//Al construirlo siempre le debo decir que es distinguible
	//Y por consiguiente, su cantidad es UNICAMENTE 1 (UNO)
	super();
	setDistinguishable(true); 
	produce(1);
    }
    
    public boolean transfer(Depot _destinationDepot) {
	boolean _transfer = false;
	if (depot != null) {
	    if (depot.unStore(this)) {
	        if (_destinationDepot.store(this)) {
	            setDepot(_destinationDepot);
	            _transfer = true;
	        } else {
	            depot.store(this);
	        }
	    }
	} else {
	    if (_destinationDepot.store(this)) {
	        setDepot(_destinationDepot);
	        _transfer = true;
	    }
	}
	return _transfer;
    }

    public boolean transfer(Persons _destinationPerson) {
	setPerson(_destinationPerson);
	return true;
    }

    public void setIdDistinguishableResource(int idDistinguishableResource) {
	this.idDistinguishableResource = idDistinguishableResource;
    }

    public int getIdDistinguishableResource() {
	return idDistinguishableResource;
    }

    public void setAcquisitionDate(String _acquisitionDate) {
	acquisitionDate = _acquisitionDate;
    }

    public String getAcquisitionDate() {
	return acquisitionDate;
    }

    public void setDropOffDate(String _dropOffDate) {
	dropOffDate = _dropOffDate;
    }

    public String getDropOffDate() {
	return dropOffDate;
    }

    public void setDepot(Depot _depot) {
	depot = _depot;
    }

    public Depot getDepot() {
	return depot;
    }

    public void setPerson(Persons _person) {
	person = _person;
    }

    public Persons getPerson() {
	return person;
    }

    public void setWaste(double waste) {
	this.waste = waste;
    }

    public double getWaste() {
	return waste;
    }

    public void setIdWasteUnit(int idWasteUnit) {
	this.idWasteUnit = idWasteUnit;
    }

    public int getIdWasteUnit() {
	return idWasteUnit;
    }
    
    public void setIdIdentificationType(int _idIdentificationType) {
        idIdentificationType = _idIdentificationType;
    }

    public int getIdIdentificationType() {
        return idIdentificationType;
    }

    public void setIdentificationTypeNumber(String _identificationTypeNumber) {
        identificationTypeNumber = _identificationTypeNumber;
    }

    public String getIdentificationTypeNumber() {
        return identificationTypeNumber;
    }
    
    
    public void setIdLifeTimeType(int _idLifeTimeType) {
        idLifeTimeType = _idLifeTimeType;
    }

    public int getIdLifeTimeType() {
        return idLifeTimeType;
    }
    
    public void setPrice(double _price) {
        price = _price;
    }

    public double getPrice() {
        return price;
    }
    
    public void setIdResourceCondition(int _idResourceCondition) {
        idResourceCondition = _idResourceCondition;
    }

    public int getIdResourceCondition() {
        return idResourceCondition;
    }

    public void setResourceCondition(String _resourceCondition) {
        resourceCondition = _resourceCondition;
    }

    public String getResourceCondition() {
        return resourceCondition;
    }

    public void setDateOfLoad(String _dateOfLoad) {
        dateOfLoad = _dateOfLoad;
    }

    public String getDateOfLoad() {
        return dateOfLoad;
    }
    
    public void setIdentificationType(String _identificationType) {
        identificationType = _identificationType;
    }

    public String getIdentificationType() {
        return identificationType;
    }
    
    public void retrieveData() {
        depot = new Depot();
        person = new Persons();
        String params = ""+ idDistinguishableResource;
        ResultSet rsData = LibSQL.exFunction("resourcescontrol.getDistinguishableResource", params);
        try {
            if (rsData.next()) {
                getResource().setIdResource(rsData.getInt("idresource"));
                getBrands().setIdBrand(rsData.getInt("idbrand"));
                getBrands().setName("name");
                acquisitionDate = rsData.getString("aquisitiondate");
                dropOffDate = rsData.getString("dropoffdate");
                getDepot().setIdDepot(rsData.getInt("iddepot"));
                getPerson().setIdPerson(rsData.getInt("idperson"));
                waste = rsData.getInt("waste");
                idLifeTimeType = rsData.getInt("idlifetimetype");
                price = rsData.getDouble("price");
                idIdentificationType = rsData.getInt("ididentificationtype");
                //identificationType = rsData.getString("identificationtype");
                identificationTypeNumber = rsData.getString("identificationnumber");
                idResourceCondition = rsData.getInt("idresourcecondition");
                resourceCondition = rsData.getString("resourcecondition");
		setBarcode(rsData.getString("barcode"));
            } else {
                /*idResourceBrand = -1;
                setMinStock(0);
                setInitiated(false);
                setStock(0);
                price = 0;
                resource.setUnit(new Units(-1,"N/A"));*/
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int saveData() {
        int result = -1;
        String params = getResource().getIdResource() +","+ getBrands().getIdBrand() +",'"+ acquisitionDate +"','"+ dropOffDate +"',"+ 
                        getDepot().getIdDepot() +","+ getPerson().getIdPerson() +","+ waste +","+ idLifeTimeType +","+ 
                        idIdentificationType +",'"+ identificationTypeNumber +"',"+  price +","+ idResourceCondition + ",'" + getBarcode() + "'";
        
        if (idDistinguishableResource == -1)  {
            //result = LibSQL.getInt("resourcescontrol.addDistinguishableResource",params);
        } else  {
            params = idDistinguishableResource +","+ params;
            result = LibSQL.getInt("resourcescontrol.setDistinguishableResource",params);
        }
        
        
        return result;
    }

    
}
