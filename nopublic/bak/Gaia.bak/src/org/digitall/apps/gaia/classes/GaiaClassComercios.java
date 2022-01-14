package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassComercios {

    private int idComercio = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idCategoriaComercial = 0;
    private int idRubroComercial = 0;
    private String nombre = "";
    private String actividad = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String fax = "";

    public GaiaClassComercios() {
    }

    public void retrieveData() {
        if (idComercio != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getComercioObject", idComercio);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdCategoriaComercial(result.getInt("idcategoriacomercial"));
                    setIdRubroComercial(result.getInt("idrubrocomercial"));
                    setNombre(result.getString("nombre"));
                    setActividad(result.getString("actividad"));
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
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delComercioObject", idComercio);
    }

    public int saveData() {
        int _result = -1;
        String params = idComercio + ", " + x + ", " + y + ", " + catastro + ", " + idCategoriaComercial + ", " + idRubroComercial + ",'" + 
                        nombre +"','"+ actividad + "',' " + telefono1 + "', '" + telefono2 + "','" + fax + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setComercioObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idComercio = _result;
        }
        return _result;
    }

    public void setIdComercio(int idComercio) {
        this.idComercio = idComercio;
    }

    public int getIdComercio() {
        return idComercio;
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

    public void setIdCategoriaComercial(int idCategoriaComercial) {
        this.idCategoriaComercial = idCategoriaComercial;
    }

    public int getIdCategoriaComercial() {
        return idCategoriaComercial;
    }

    public void setIdRubroComercial(int idRubroComercial) {
        this.idRubroComercial = idRubroComercial;
    }

    public int getIdRubroComercial() {
        return idRubroComercial;
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
