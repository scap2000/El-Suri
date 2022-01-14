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
 * DriverForm.java
 *
 * */
package org.digitall.apps.licenses.classes;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;


public class DriverForm {

    private int iddriverform = -1;
    private int iddriver = -1;
    private int ididentificationtype = -1;
    private String identificationtype = "";
    private int identificationnumber = -1;
    private String name = "";
    private String lastname = "";
    private String address = "";
    private String birthdate = "";
    private String sex = "";
    private String nationality = "";
    private int kind = -1;
    private String bloodgroup = "";
    private String rhfactor = "";
    private Byte[] photo = null;
    private String estado = "";
    
    private BufferedImage photoImage = null;
    private BufferedImage logoImage = null;
    
    private License license;
    
    public DriverForm() {
    
    }

    public DriverForm(int _iddriver) {
        iddriver = _iddriver;
    }

    public void setIddriverform(int _iddriverform) {
        iddriverform = _iddriverform;
    }

    public int getIddriverform() {
        return iddriverform;
    }

    public void setIddriver(int _iddriver) {
        iddriver = _iddriver;
    }

    public int getIddriver() {
        return iddriver;
    }

    public void setIdidentificationtype(int _ididentificationtype) {
        ididentificationtype = _ididentificationtype;
    }

    public int getIdidentificationtype() {
        return ididentificationtype;
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

    public void setAddress(String _address) {
        address = _address;
    }

    public String getAddress() {
        return address;
    }

    public void setBirthdate(String _birthdate) {
        birthdate = _birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setSex(String _sex) {
        sex = _sex;
    }

    public String getSex() {
        return sex;
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

    public void setBloodgroup(String _bloodgroup) {
        bloodgroup = _bloodgroup;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setRhfactor(String _rhfactor) {
        rhfactor = _rhfactor;
    }

    public String getRhfactor() {
        return rhfactor;
    }

    public void setPhoto(Byte[] _photo) {
        photo = _photo;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setLicense(License _license) {
        license = _license;
    }

    public License getLicense() {
        return license;
    }
    
    public void retrieveDataWithIdDriver() {
        ResultSet data = LibSQL.exFunction("licenses.getDriverForm","" + iddriver);
        try  {
            if (data.next()) {
                 iddriverform = data.getInt("iddriverform");
                 iddriver = data.getInt("iddriver");
                 ididentificationtype = data.getInt("ididentificationtype");
                 identificationtype = data.getString("identificationtype");
                 identificationnumber = data.getInt("identificationnumber");
                 name = data.getString("name");
                 lastname = data.getString("lastname");
                 address = data.getString("address");
                 birthdate = data.getString("birthdate");
                 sex = data.getString("sex");
                 nationality = data.getString("nationality");
                 kind = data.getInt("kind");
                 bloodgroup = data.getString("bloodgroup");
                 rhfactor = data.getString("rhfactor");
                 license = new License(iddriverform);
                 license.retrieveData();
            }
            
        } catch (SQLException ex)  {
            ex.printStackTrace();
        } 
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
        photoImage = LibIMG.getImage("licenses.driverforms", "photo", "WHERE iddriverform = " + iddriverform);
    }
}
