package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class PlanSocial {
    
    private int idPlanSocial = -1;
    private String nombre = "";
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String tipoPlanSocial = "";
    private String numeroInstrumento = "";
    private double costoEstimado = 0.0;
    private String observaciones = "";
    private String estado = "";
        
    public PlanSocial() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idPlanSocial == -1)  {
            params = "'"  + nombre + "','" + fechaAlta + "','" + fechaBaja  + "','" + motivoBaja + "','"
                          + tipoPlanSocial + "','" + numeroInstrumento + "'," + costoEstimado + ",'"
                          + observaciones + "', ''";
            result = LibSQL.getInt("accionsocial.addPlanSocial", params);
            idPlanSocial = result; 
        } else {
            params = "'"  + idPlanSocial + "," + nombre + "','" + fechaAlta + "','" + fechaBaja  + "','" + motivoBaja + "','"
                          + tipoPlanSocial + "','" + numeroInstrumento + "'," + costoEstimado + ",'"
                          + observaciones + "','" + estado + "'";
            result = LibSQL.getInt("accionsocial.setPlanSocial", params);
        }
         return result;
    }

    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delPlanSocial", ""+ idPlanSocial);
    }


    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getPlanSocial", ""+ idPlanSocial);
            if (rs.next()) {
                idPlanSocial = rs.getInt("idplansocial");
                nombre = rs.getString("nombre");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                tipoPlanSocial = rs.getString("tipoplansocial");
                numeroInstrumento = rs.getString("numeroinstrumento");
                costoEstimado = rs.getDouble("costoestimado");
                observaciones = rs.getString("observaciones");
                estado = rs.getString("estado");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdPlanSocial(int idPlanSocial) {
        this.idPlanSocial = idPlanSocial;
    }

    public int getIdPlanSocial() {
        return idPlanSocial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

    public void setIdTipoPlanSocial(String tipoPlanSocial) {
        this.tipoPlanSocial = tipoPlanSocial;
    }

    public String getIdTipoPlanSocial() {
        return tipoPlanSocial;
    }

    public void setNumeroInstrumento(String numeroInstrumento) {
        this.numeroInstrumento = numeroInstrumento;
    }

    public String getNumeroInstrumento() {
        return numeroInstrumento;
    }

    public void setCostoEstimado(double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    public double getCostoEstimado() {
        return costoEstimado;
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
}
