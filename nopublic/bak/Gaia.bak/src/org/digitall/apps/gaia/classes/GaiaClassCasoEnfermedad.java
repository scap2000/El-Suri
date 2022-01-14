package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassCasoEnfermedad {

    private int idCaso = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idEnfermedad = 0;
    private int idTipoCaso = 0;
    private int idTipoPrevencion = 0;
    private String zona = "";
    private String nombre = "";
    private String observaciones = "";

    public GaiaClassCasoEnfermedad() {
    }

    public void retrieveData() {
        if (idCaso != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getCasoEnfermedadObject", idCaso);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
		    setIdTipoCaso(result.getInt("idtipocaso"));
		    setIdEnfermedad(result.getInt("idenfermedad"));
		    setIdTipoPrevencion(result.getInt("idtipoprevencion"));
                    setZona(result.getString("zona"));
                    setNombre(result.getString("nombre"));
                    setObservaciones(result.getString("observaciones"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delCasoEnfermedadObject", idCaso);
    }

    public int saveData() {
        int _result = -1;
        String params = idCaso + ", " + x + ", " + y + ", " + catastro + ", " + idTipoCaso + ", " + idTipoPrevencion + ", " + idEnfermedad + ",'" + zona + "','" +
                        nombre +"','"+ observaciones +"'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setCasoEnfermedadObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idCaso = _result;
        }
        return _result;
    }

    public void setIdCaso(int _idCaso) {
        this.idCaso = _idCaso;
    }

    public int getIdCaso() {
        return idCaso;
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

    public void setIdEnfermedad(int idTipoEstablecimiento) {
        this.idEnfermedad = idTipoEstablecimiento;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setZona(String zonaHospital) {
        this.zona = zonaHospital;
    }

    public String getZona() {
        return zona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setObservaciones(String enfermedades) {
        this.observaciones = enfermedades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setIdTipoCaso(int idTipoCaso) {
	this.idTipoCaso = idTipoCaso;
    }

    public int getIdTipoCaso() {
	return idTipoCaso;
    }

    public void setIdTipoPrevencion(int idTipoPrevencion) {
	this.idTipoPrevencion = idTipoPrevencion;
    }

    public int getIdTipoPrevencion() {
	return idTipoPrevencion;
    }

}
