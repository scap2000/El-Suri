package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassIndustria {

    private int idIndustria = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idTipoIndustria = 0;
    private String nombre = "";
    private String actividad = "";
    private String responsable = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String fax = "";

    public GaiaClassIndustria() {
    }

    public void retrieveData() {
        if (idIndustria != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getIndustriaObject", idIndustria);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdTipoIndustria(result.getInt("idtipoindustria"));
                    setNombre(result.getString("nombre"));
                    setActividad(result.getString("actividad"));
                    setResponsable(result.getString("responsable"));
                    setTelefono1(result.getString("telefono1"));
                    setTelefono2(result.getString("telefono2"));
                    setFax(result.getString("fax"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delIndustriaObject", idIndustria);
    }

    public int saveData() {
        int _result = -1;
        String params = idIndustria + ", " + x + ", " + y + ", " + catastro + ", " + idTipoIndustria + ",'" + 
                        nombre +"','"+ actividad + "','"+ responsable +"',' " + telefono1 + "', '" + telefono2 + "','" + fax + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setIndustriaObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idIndustria = _result;
        }
        return _result;
    }


    public void setIdIndustria(int idIndustria) {
        this.idIndustria = idIndustria;
    }

    public int getIdIndustria() {
        return idIndustria;
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

    public void setIdTipoIndustria(int idTipoIndustria) {
        this.idTipoIndustria = idTipoIndustria;
    }

    public int getIdTipoIndustria() {
        return idTipoIndustria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getResponsable() {
        return responsable;
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
}
