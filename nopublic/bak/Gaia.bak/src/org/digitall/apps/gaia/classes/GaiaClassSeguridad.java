package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassSeguridad {

    private int idSeguridad = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idtiposeguridad = 0;
    private String nombre = "";
    private String tipoSeguridad = "";
    private String responsable = "";
    private int cantefectivos = 0;
    private String telefono1 = "";
    private String telefono2 = "";
    private String email = "";
    private String fax = "";

    public GaiaClassSeguridad() {
	super();
    }

    public void retrieveData() {
        if (idSeguridad != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getSeguridadObject", idSeguridad);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdtiposeguridad(result.getInt("idtiposeguridad"));
                    setNombre(result.getString("nombre"));
                    setResponsable(result.getString("responsable"));
                    setCantefectivos(result.getInt("cantefectivos"));
                    setTelefono1(result.getString("telefono1"));
                    setTelefono2(result.getString("telefono2"));
                    setEmail(result.getString("email"));
                    setFax(result.getString("fax"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
	return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delSeguridadObject", idSeguridad);
    }

    public int saveData() {
	int _result = -1;
	String params = idSeguridad + ", " + x + ", " + y + ", " + catastro + ", " + idtiposeguridad
		    + ", '" + nombre + "', '" + responsable + "', " + cantefectivos + ",' " + telefono1
		    + "', '" + telefono2 + "', '" + email + "', '" + fax + "'";
	_result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setSeguridadObject", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idSeguridad = _result;
	}
	return _result;
    }


    public void setIdSeguridad(int idSeguridad) {
	this.idSeguridad = idSeguridad;
    }

    public int getIdSeguridad() {
	return idSeguridad;
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

    public void setIdtiposeguridad(int idtiposeguridad) {
	this.idtiposeguridad = idtiposeguridad;
    }

    public int getIdtiposeguridad() {
	return idtiposeguridad;
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

    public void setCantefectivos(int cantefectivos) {
	this.cantefectivos = cantefectivos;
    }

    public int getCantefectivos() {
	return cantefectivos;
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

    public void setEmail(String email) {
	this.email = email;
    }

    public String getEmail() {
	return email;
    }

    public void setFax(String fax) {
	this.fax = fax;
    }

    public String getFax() {
	return fax;
    }

    public void setTipoSeguridad(String tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }

    public String getTipoSeguridad() {
        return tipoSeguridad;
    }
}
