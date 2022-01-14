package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassSalud {

    private int idSalud = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idTipoEstablecimiento = 0;
    private String zonaHospital = "";
    private String nombre = "";
    private String complejidad = "";
    private String responsable = "";
    private int camas = 0;
    private String telefono1 = "";
    private String telefono2 = "";
    private String fax = "";
    private String enfermedades = "";
    private String profesionales = "";

    public GaiaClassSalud() {
    }

    public void retrieveData() {
        if (idSalud != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getSaludObject", idSalud);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdTipoEstablecimiento(result.getInt("idtipoestablecimiento"));
                    setZonaHospital(result.getString("zonahospital"));
                    setNombre(result.getString("nombre"));
                    setComplejidad(result.getString("complejidad"));
                    setResponsable(result.getString("responsable"));
                    setCamas(result.getInt("camas"));
                    setTelefono1(result.getString("telefono1"));
                    setTelefono2(result.getString("telefono2"));
                    setFax(result.getString("fax"));
                    setEnfermedades(result.getString("enfermedades"));
                    setProfesionales(result.getString("profesionales"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delSaludObject", idSalud);
    }

    public int saveData() {
        int _result = -1;
        String params = idSalud + ", " + x + ", " + y + ", " + catastro + ", " + idTipoEstablecimiento + ",'" + zonaHospital + "','" +
                        nombre +"','"+ complejidad + "', '" + responsable +"' , "+ camas +" , '"+ telefono1 + "', '" + telefono2 + "','" + 
                        fax + "','"+ enfermedades +"','"+ profesionales +"'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setSaludObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idSalud = _result;
        }
        return _result;
    }

    public void setIdSalud(int idSalud) {
        this.idSalud = idSalud;
    }

    public int getIdSalud() {
        return idSalud;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setIdTipoEstablecimiento(int idTipoEstablecimiento) {
        this.idTipoEstablecimiento = idTipoEstablecimiento;
    }

    public int getIdTipoEstablecimiento() {
        return idTipoEstablecimiento;
    }

    public void setZonaHospital(String zonaHospital) {
        this.zonaHospital = zonaHospital;
    }

    public String getZonaHospital() {
        return zonaHospital;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    public String getComplejidad() {
        return complejidad;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public int getCamas() {
        return camas;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setProfesionales(String profesionales) {
        this.profesionales = profesionales;
    }

    public String getProfesionales() {
        return profesionales;
    }
}
