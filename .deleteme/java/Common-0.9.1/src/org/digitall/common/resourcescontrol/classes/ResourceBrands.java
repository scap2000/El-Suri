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
 * ResourceBrands.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.sql.LibSQL;
import org.digitall.common.resourcescontrol.classes.Brands;

public class ResourceBrands extends Resource {

    private int idResourceBrand = -1;
    private double price = 0;
    private double qty = 0;
    private int idBrand = 0;
    
    //OBJECTS
    private Brands brands = new Brands();
    private Resource resource = new Resource();
    
    public ResourceBrands() {
    }
    
    public ResourceBrands(int _idResourceBrand) {
	idResourceBrand = _idResourceBrand;
    }
    
    public ResourceBrands(Resource _resource, Brands _brand) {
	resource = _resource;
	brands = _brand;
    }
    
    public ResourceBrands(Resource _resource) {
	resource = _resource;
    }

    public void setBrands(Brands _brands) {
	brands = _brands;
    }

    public Brands getBrands() {
	return brands;
	
    }

    public void retrieveData() {
	String params = resource.getIdResource() + "," + brands.getIdBrand();
	ResultSet rsData = LibSQL.exFunction("resourcescontrol.getResourceBrands", params);
	try {
	    if (rsData.next()) {
		idResourceBrand = rsData.getInt("idresourcebrand");
		setMinStock(rsData.getDouble("minstock"));
	        setInitiated(false);
		setStock(rsData.getDouble("stock"));
		price = rsData.getDouble("price");
		resource.setUnit(new Units(rsData.getInt("idunit"), rsData.getString("unit")));
	    }else {
	        idResourceBrand = -1;
	        setMinStock(0);
	        setInitiated(false);
	        setStock(0);
	        price = 0;
	        resource.setUnit(new Units(-1,"N/A"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int saveData(){
	String params = resource.getIdResource() +","+ brands.getIdBrand() +",0"+ getMinStock() +",0"+ getStock() +",0"+ price;
	
	int result = -1;
	if (idResourceBrand == -1){
	    idResourceBrand = LibSQL.getInt("resourcescontrol.addResourceBrand", params);
	    result = idResourceBrand;
	} else {
	    params = idResourceBrand +","+ params;	    
	    result = LibSQL.getInt("resourcescontrol.setResourceBrand", params);
	}
	if (result != -1){
	    resource.updateTotalStock();
	}
	return result;
    }
    
    public int updateStock(){
	String params = "" + resource.getIdResource() + "," + brands.getIdBrand() + "," + getStock();
	int result = -1;
	//System.out.println("params to update: " + params);
	result = LibSQL.getInt("resourcescontrol.setResourceBrandStock", params);
	return result;
    }
    public void setIdResourceBrand(int _idResourceBrand) {
	idResourceBrand = _idResourceBrand;
    }

    public int getIdResourceBrand() {
	return idResourceBrand;
    }

    public void setPrice(double _price) {
	price = _price;
    }

    public double getPrice() {
	return price;
    }

    public void setResource(Resource _resource) {
	resource = _resource;
    }

    public Resource getResource() {
	return resource;
    }

    public void setQty(double _qty) {
	qty = _qty;
    }

    public double getQty() {
	return qty;
    }

    /**
     * @deprecated
     * @param _idBrand
     */
    public void setIdBrand(int _idBrand) {
	idBrand = _idBrand;
    }

    /**
     * @deprecated
     * @return
     */
    public int getIdBrand() {
	return idBrand;
    }

}
