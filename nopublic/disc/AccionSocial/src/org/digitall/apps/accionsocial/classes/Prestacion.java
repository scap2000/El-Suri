package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Prestacion {
    
    private int idPrestacion = -1;
    private int idPlanSocial = 0;
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String recurso = "";
    private double cantMinima = 0.0;
    private double cantMaxima = 0.0;
    private String observaciones = "";
    private String estado = "";
    private double cantDisponible = 0.0;
        
    public Prestacion() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idPrestacion == -1)  {
            params =   ""  + idPlanSocial +",'"+ fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                           + recurso + "'," + cantMinima + "," + cantMaxima + ",'" + observaciones + "',"
                           + cantDisponible + ",''";
            
            result = LibSQL.getInt("accionsocial.addPrestacion", params);
            idPrestacion = result; 
        } else {
            params = "" + idPrestacion + "," + idPlanSocial + ",'" + fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                        + recurso + "'," + cantMinima + "," + cantMaxima + ",'" + observaciones + "',"
                        + cantDisponible + ",'" + estado + "'";
            result = LibSQL.getInt("accionsocial.setPrestacion", params);
        }
         return result;
    }

    public boolean deletePrestacion() {
        return LibSQL.getBoolean("accionsocial.delPrestacion", ""+ idPrestacion);
    }

    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getPrestacion", ""+ idPrestacion);
            if (rs.next()) {
                idPrestacion = rs.getInt("idprestacion");
                idPlanSocial = rs.getInt("idprestacion");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                recurso = rs.getString("recurso");
                cantMinima = rs.getDouble("cantminima");
                cantMaxima = rs.getDouble("cantmaxima");
                observaciones = rs.getString("observaciones");
                estado = rs.getString("estado");
                cantDisponible = rs.getDouble("cantdisponible");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean actualizarStock(double _cantidad){
        boolean result = false;
        String params = "" + idPrestacion + "," + _cantidad;
        result = LibSQL.getBoolean("accionsocial.setStockPrestacion", params);
        return result;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPlanSocial(int idPlanSocial) {
        this.idPlanSocial = idPlanSocial;
    }

    public int getIdPlanSocial() {
        return idPlanSocial;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setCantMinima(double cantMinima) {
        this.cantMinima = cantMinima;
    }

    public double getCantMinima() {
        return cantMinima;
    }

    public void setCantMaxima(double cantMaxima) {
        this.cantMaxima = cantMaxima;
    }

    public double getCantMaxima() {
        return cantMaxima;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setCantDisponible(double cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public double getCantDisponible() {
        return cantDisponible;
    }
}
