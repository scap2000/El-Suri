package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassEducacion {

        private int idEducacion = -1;
        private double x;
        private double y;
        private int catastro = 0;
        private String nroestablecimiento = "";
        private String nombre = "";
        private int docentes = 0;
        private int alumnos = 0;
        private int idniveleducativo = 0;
        private int idOrientacionEducativa = 0;
        private String cantegresados = "";

    public GaiaClassEducacion() {
    }

    public void retrieveData() {
        if (idEducacion != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getEducacionObject", idEducacion);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setNroestablecimiento(result.getString("nroestablecimiento"));
                    setNombre(result.getString("nombre"));
                    setDocentes(result.getInt("docentes"));
                    setAlumnos(result.getInt("alumnos"));
                    setIdniveleducativo(result.getInt("idniveleducativo"));
                    setIdOrientacionEducativa(result.getInt("idorientacioneducativa"));
                    setCantegresados(result.getString("cantegresados"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delEducacionObject", idEducacion);
    }

    public int saveData() {
        int _result = -1;
        String params = idEducacion + ", " + x + ", " + y + ", " + catastro + ",'" + nroestablecimiento + "','" +
                        nombre +"',"+ docentes + ", " + alumnos +" , "+ idniveleducativo +" , "+ idOrientacionEducativa + ", '" + cantegresados + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setEducacionObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idEducacion = _result;  
        }
        return _result;
    }

    public void setIdEducacion(int idEducacion) {
        this.idEducacion = idEducacion;
    }

    public int getIdEducacion() {
        return idEducacion;
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

    public void setNroestablecimiento(String nroestablecimiento) {
        this.nroestablecimiento = nroestablecimiento;
    }

    public String getNroestablecimiento() {
        return nroestablecimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDocentes(int docentes) {
        this.docentes = docentes;
    }

    public int getDocentes() {
        return docentes;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setIdniveleducativo(int idniveleducativo) {
        this.idniveleducativo = idniveleducativo;
    }

    public int getIdniveleducativo() {
        return idniveleducativo;
    }

    public void setIdOrientacionEducativa(int idOrientacionEducativa) {
        this.idOrientacionEducativa = idOrientacionEducativa;
    }

    public int getIdOrientacionEducativa() {
        return idOrientacionEducativa;
    }

    public void setCantegresados(String cantegresados) {
        this.cantegresados = cantegresados;
    }

    public String getCantegresados() {
        return cantegresados;
    }
}
