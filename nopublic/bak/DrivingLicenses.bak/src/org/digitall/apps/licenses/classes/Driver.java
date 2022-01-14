package org.digitall.apps.licenses.classes;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;


public class Driver {

    private int idDriver = -1;
    private int idIdentificationType = -1;
    private int identificationNumber = -1;
    private String name = "";
    private String lastname = "";
    private String address = "";
    private String birthdate = "";
    private String sex = "";
    private String nationality = "";
    private int kind = -1;
    private String bloodgroup = "";
    private String rhfactor = "";
    private byte[] photo = null;
    private String estado = "";
    
    private BufferedImage photoImage = null;
    private BufferedImage logoImage = null;
    
    private DriverForm driverForm;

    public Driver() {
    }

    public void setIdDriver(int _idDriver) {
        idDriver = _idDriver;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdIdentificationType(int _idIdentificationType) {
        idIdentificationType = _idIdentificationType;
    }

    public int getIdIdentificationType() {
        return idIdentificationType;
    }

    public void setIdentificationNumber(int _identificationNumber) {
        identificationNumber = _identificationNumber;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
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

    public void setPhoto(byte[] _photo) {
        photo = _photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setDriverForm(DriverForm _driverForm) {
        this.driverForm = _driverForm;
    }

    public DriverForm getDriverForm() {
        return driverForm;
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
    
    public void retrieveData() {
        ResultSet data = LibSQL.exFunction("licenses.getDriver","" + idDriver);
        try  {
            if (data.next()) {
                idDriver = data.getInt("iddriver");
                idIdentificationType = data.getInt("ididentificationtype");
                identificationNumber = data.getInt("identificationnumber");
                name = data.getString("name");
                lastname = data.getString("lastname");
                address = data.getString("address");
                birthdate = data.getString("birthdate");
                sex = data.getString("sex");
                nationality = data.getString("nationality");
                kind = data.getInt("kind");
                bloodgroup = data.getString("bloodgroup");
                rhfactor = data.getString("rhfactor");
                photo = null;
                driverForm = new DriverForm();
                driverForm.setIddriver(idDriver);
                driverForm.retrieveDataWithIdDriver();
            }
            
        } catch (SQLException ex)  {
            ex.printStackTrace();
        } 
        
    }

    public int saveData() {
        int result = -1;
        String params =  "";
        if (idDriver == -1)  {
            params = ""+ idIdentificationType +","+ identificationNumber;
            int radiogramQty = LibSQL.getInt("licenses.existRadiogramsByDriver","" + params);
            if (radiogramQty == 0) {
                params =  "'"+ lastname + "','"+ name + "'," + idIdentificationType + ","+ identificationNumber +",'" + address 
                                    + "','"+ birthdate +"','" + sex + "','"+ nationality +"',"+ kind +",'"+ bloodgroup +"','"+ rhfactor +"'";
                result = LibSQL.getInt("licenses.addDriver","" + params);    
                if (result != -1 && photoImage != null) {
		    LibIMG.saveImage(photoImage, "licenses.drivers", "photo", "WHERE iddriver = " + idDriver);
		    LibIMG.saveImage(photoImage, "licenses.driverforms", "photo", "WHERE iddriverform = " + driverForm.getIddriverform());
                }
            } else {
                result = -2;
            }
        } else  {
            params =  idDriver + ",'"+ lastname + "','"+ name + "'," + idIdentificationType + ","+ identificationNumber +",'" + address 
                                + "','"+ birthdate +"','" + sex + "','"+ nationality +"',"+ kind +",'"+ bloodgroup +"','"+ rhfactor +"'";
            result = LibSQL.getInt("licenses.setDriver","" + params);
            if (result != -1 && photoImage != null) {
		LibIMG.saveImage(photoImage, "licenses.drivers", "photo", "WHERE iddriver = " + idDriver);
		LibIMG.saveImage(photoImage, "licenses.driverforms", "photo", "WHERE iddriverform = " + driverForm.getIddriverform());
            }
        }
        return result;
    }

    public void retrievePicture() {
        photoImage = LibIMG.getImage("licenses.drivers", "photo", "WHERE iddriver = " + idDriver);
    }
   
}
