package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassTurismo {

    private int idTurismo = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idTipoInstTur = 0;
    private String nombre = "";
    private int idCatTur = 0;
    private int habitaciones = 0;
    private String especialidad = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String fax = "";

    public GaiaClassTurismo() {
    }

    public void retrieveData() {
        if (idTurismo != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getTurismoObject", idTurismo);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdTipoInstTur(result.getInt("idtipoinsttur"));
                    setNombre(result.getString("nombre"));
                    setIdCatTur(result.getInt("idcattur"));
                    setHabitaciones(result.getInt("habitaciones"));
                    setEspecialidad(result.getString("especialidad"));
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
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delTurismoObject", idTurismo);
    }

    public int saveData() {
        int _result = -1;
        String params = idTurismo + ", " + x + ", " + y + ", " + catastro + ", " + idTipoInstTur + ",'" + 
                        nombre +"'," + idCatTur + ", " + habitaciones + ", '" + especialidad + "',' " + telefono1 + "', '" + telefono2 + "','" + fax + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setTurismoObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idTurismo = _result;
        }
        return _result;
    }

    public void setIdTurismo(int idTurismo) {
        this.idTurismo = idTurismo;
    }

    public int getIdTurismo() {
        return idTurismo;
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

    public void setIdTipoInstTur(int idTipoInstTur) {
        this.idTipoInstTur = idTipoInstTur;
    }

    public int getIdTipoInstTur() {
        return idTipoInstTur;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdCatTur(int idCatTur) {
        this.idCatTur = idCatTur;
    }

    public int getIdCatTur() {
        return idCatTur;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
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
