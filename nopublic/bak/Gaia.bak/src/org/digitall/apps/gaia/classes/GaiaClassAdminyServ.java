package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassAdminyServ {

    private int idAdminyServ = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idTipoAdminyServ = 0;
    private String nombre = "";
    private String responsable = "";
    private int idTipoActividad = 0;
    private String telefono1 = "";
    private String telefono2 = "";
    private String estadoCapacidad = "";
    private String fax = "";
    
    public GaiaClassAdminyServ() {
    }
    
    public void retrieveData() {
        if (idAdminyServ != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getAdminyServObject", idAdminyServ);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdTipoAdminyServ(result.getInt("idtipoadminyserv"));
                    setNombre(result.getString("nombre"));
                    setResponsable(result.getString("responsable"));
                    setIdTipoActividad(result.getInt("idtipoactividad"));
                    setTelefono1(result.getString("telefono1"));
                    setTelefono2(result.getString("telefono2"));
                    setEstadoCapacidad(result.getString("estadocapacidad"));
                    setFax(result.getString("fax"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delAdminyServObject", idAdminyServ);
    }

    public int saveData() {
        int _result = -1;
        String params = idAdminyServ + ", " + x + ", " + y + ", " + catastro + ", " + idTipoAdminyServ + ", '" + nombre +"','"+ 
                        responsable + "',"+ idTipoActividad +",' " + telefono1 + "', '" + telefono2 + "', '" + estadoCapacidad + "','" + fax + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setAdminyServObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idAdminyServ = _result;
        }
        return _result;
    }

    public void setIdAdminyServ(int idAdminyServ) {
        this.idAdminyServ = idAdminyServ;
    }

    public int getIdAdminyServ() {
        return idAdminyServ;
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

    public void setIdTipoAdminyServ(int idTipoAdminyServ) {
        this.idTipoAdminyServ = idTipoAdminyServ;
    }

    public int getIdTipoAdminyServ() {
        return idTipoAdminyServ;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
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

    public void setEstadoCapacidad(String estadoCapacidad) {
        this.estadoCapacidad = estadoCapacidad;
    }

    public String getEstadoCapacidad() {
        return estadoCapacidad;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }
}
