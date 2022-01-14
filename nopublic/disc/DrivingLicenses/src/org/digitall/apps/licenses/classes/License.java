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
 * License.java
 *
 * */
package org.digitall.apps.licenses.classes;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class License {

    private int idlicense = -1;
    private int iddriverform = -1;
    private boolean current = false;
    private String datefrom = "";
    private String dateto = "";
    private int licensenumber = -1;
    private String name = "";
    private String lastname = "";
    private String birthday = "";
    private String identificationtype = "";
    private int identificationnumber = -1;
    private String address = "";
    private int idcategory = -1;   
    private String category = "";
    private String nationality = "";
    private int kind = -1;   
    private String bloodgroup = "";
    private String rhfactor = "";
    private String authorizedby = "";
    private String estado = "";
    private int years = -1;
    private double price = 0;
    private double amount = 0;
    private int idlicensestatus = -1;
    private String licensestatus = "";
    private boolean hasGlasses = false;
    private boolean hasHearingAid = false;
    private boolean organDonor = false;
    private boolean adaptedVehicle = false;
    private String allergies = "";
    private String disqualifications = "";
    private String barCode = "";


    private BufferedImage photoImage = null;
    private BufferedImage logoImage = null;

    public License() {
    }
    
    public License(int _idlicense) {
        idlicense = _idlicense;
    }

    public void setIdlicense(int _idlicense) {
        idlicense = _idlicense;
    }

    public int getIdlicense() {
        return idlicense;
    }

    public void setIddriverform(int _iddriverform) {
        iddriverform = _iddriverform;
    }

    public int getIddriverform() {
        return iddriverform;
    }

    public void setCurrent(boolean _current) {
        current = _current;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setDatefrom(String _datefrom) {
        datefrom = _datefrom;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDateto(String _dateto) {
        dateto = _dateto;
    }

    public String getDateto() {
        return dateto;
    }

    public void setLicensenumber(int _licensenumber) {
        licensenumber = _licensenumber;
    }

    public int getLicensenumber() {
        return licensenumber;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setLastname(String _lastname) {
        lastname = _lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setBirthday(String _birthday) {
        birthday = _birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setIdentificationtype(String _identificationtype) {
        identificationtype = _identificationtype;
    }

    public String getIdentificationtype() {
        return identificationtype;
    }

    public void setIdentificationnumber(int _identificationnumber) {
        identificationnumber = _identificationnumber;
    }

    public int getIdentificationnumber() {
        return identificationnumber;
    }

    public void setAddress(String _address) {
        address = _address;
    }

    public String getAddress() {
        return address;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public String getCategory() {
        return category;
    }

    public void setNationality(String _nationality) {
        nationality = _nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setKind(int _kind) {
        kind = _kind;
    }

    public int getKind() {
        return kind;
    }

    public void setBloodgruop(String _bloodgruop) {
        bloodgroup = _bloodgruop;
    }

    public String getBloodgruop() {
        return bloodgroup;
    }

    public void setRhfactor(String _rhfactor) {
        rhfactor = _rhfactor;
    }

    public String getRhfactor() {
        return rhfactor;
    }

    public void setAuthorizedby(String _authorizedby) {
        authorizedby = _authorizedby;
    }

    public String getAuthorizedby() {
        return authorizedby;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setYears(int _years) {
        years = _years;
    }

    public int getYears() {
        return years;
    }

    public void setPrice(double _price) {
        price = _price;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(Double _amount) {
        amount = _amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public int getIdcategory() {
        return idcategory;
    }
    
    public void retrieveData() {
        ResultSet data = LibSQL.exFunction("licenses.getLicense","" + idlicense);
        try  {
            if (data.next()) {
                idlicense = data.getInt("idlicense");
                iddriverform = data.getInt("iddriverform");
                current = data.getBoolean("current");
                datefrom = data.getString("datefrom");
                dateto = data.getString("dateto");
                licensenumber = data.getInt("licensenumber");
                name = data.getString("name");
                lastname = data.getString("lastname");
                birthday = data.getString("birthday");
                identificationtype = data.getString("identificationtype");
                identificationnumber = data.getInt("identificationnumber");
                address = data.getString("address");
                category = data.getString("category");
                nationality = data.getString("nationality");
                kind = data.getInt("class");
                bloodgroup = data.getString("bloodgroup");
                rhfactor = data.getString("rhfactor");
                authorizedby = data.getString("authorizedby");
                years = data.getInt("years");
                price = data.getDouble("price");
                amount = data.getDouble("amount");
                idlicensestatus = data.getInt("idlicensestatus");
		licensestatus = data.getString("licensestatus");
		hasGlasses = data.getBoolean("hasglasses");
		hasHearingAid = data.getBoolean("hashearingaid");
		organDonor = data.getBoolean("organdonor");
		adaptedVehicle = data.getBoolean("adaptedvehicle");
		allergies = data.getString("allergies");
		disqualifications = data.getString("disqualifications");
		barCode = data.getString("barcode");
            }
            
        } catch (SQLException ex)  {
            ex.printStackTrace();
        } 
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idlicense == -1)  {
            params = "1,"+ identificationnumber;
            int radiogramQty = LibSQL.getInt("licenses.existRadiogramsByDriver","" + params);
            
            if (radiogramQty == 0) {
                params = ""+ iddriverform +",'"+ datefrom +"','"+ dateto +"',"+ licensenumber +",'"+ name +"','"+ lastname +"','"+ birthday +"','"
                        + identificationtype +"',"+ identificationnumber +",'"+ address +"','"+ category +"','"+ nationality +"',"+ kind + ",'"
                        + bloodgroup +"','"+ rhfactor +"','"+ authorizedby +"',"+ years +","+ price +","+ amount
			+ "," + hasGlasses + "," + hasHearingAid + "," + organDonor	
			+ "," + adaptedVehicle + ",'" + allergies + "','" + disqualifications + "'";
                idlicense = LibSQL.getInt("licenses.addLicense",params);
                result = idlicense;
                if (result != -1 && photoImage != null) {
		    LibIMG.saveImage(photoImage, "licenses.licenses", "photo", "WHERE idlicense = " + idlicense);
                }
            } else {
                result = -2;  
            }
                                    
        } else  {
             params = ""+ idlicense +","+ iddriverform +",'"+ datefrom +"','"+ dateto +"',"+ licensenumber +",'"+ name +"','"+ lastname +"','"+ birthday +"','"
		    + identificationtype +"',"+ identificationnumber +",'"+ address +"','"+ category +"','"+ nationality +"',"+ kind + ",'"
                    + bloodgroup +"','"+ rhfactor +"','"+ authorizedby +"',"+ years +","+ price +","+ amount
		    + "," + hasGlasses + "," + hasHearingAid + "," + organDonor     
		    + "," + adaptedVehicle + ",'" + allergies + "','" + disqualifications + "'";
            result = LibSQL.getInt("licenses.setlicense",params);
	    if (result != -1 && photoImage != null) {
		LibIMG.saveImage(photoImage, "licenses.licenses", "photo", "WHERE idlicense = " + idlicense);
	    }
        }
        return result;
    }


    public void setPhotoImage(BufferedImage photoImage) {
        this.photoImage = photoImage;
    }

    public BufferedImage getPhotoImage() {
        return photoImage;
    }

    public void setLogoImage(BufferedImage logoImage) {
        this.logoImage = logoImage;
    }

    public BufferedImage getLogoImage() {
        return logoImage;
    }
    
    public void retrievePicture() {
        photoImage = LibIMG.getImage("licenses.licenses", "photo", "WHERE idlicense = " + idlicense);
    }

    public void setIdlicensestatus(int idlicensestatus) {
        this.idlicensestatus = idlicensestatus;
    }

    public int getIdlicensestatus() {
        return idlicensestatus;
    }

    public void setLicensestatus(String licensestatus) {
        this.licensestatus = licensestatus;
    }

    public String getLicensestatus() {
        return licensestatus;
    }

    public void setHasGlasses(boolean _hasGlasses) {
	hasGlasses = _hasGlasses;
    }

    public boolean hasGlasses() {
	return hasGlasses;
    }

    public void setOrganDonor(boolean _organDonor) {
	organDonor = _organDonor;
    }

    public boolean isOrganDonor() {
	return organDonor;
    }

    public void setAdaptedVehicle(boolean _adaptedVehicle) {
	adaptedVehicle = _adaptedVehicle;
    }

    public boolean isAdaptedVehicle() {
	return adaptedVehicle;
    }

    public void setAllergies(String _allergies) {
	allergies = _allergies;
    }

    public String getAllergies() {
	return allergies;
    }

    public void setDisqualifications(String _disqualifications) {
	disqualifications = _disqualifications;
    }

    public String getDisqualifications() {
	return disqualifications;
    }

    public void setHasHearingAid(boolean _hasHearingAid) {
	hasHearingAid = _hasHearingAid;
    }

    public boolean hasHearingAid() {
	return hasHearingAid;
    }

    public String getBarCode() {
	return barCode;
    }

}
