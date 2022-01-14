package org.digitall.apps.taskman.classes;

import java.sql.ResultSet;

import org.digitall.apps.taskman.TaskManEnvironment;
import org.digitall.lib.environment.Environment;

public class Tareas {
    
    private int idtarea = -1;
    private String nombreTarea = "";
    private String usuario = "";
    private String fechaCreacion = "";
    private String fechaInicio = "";
    private String fechaFin = "";
    private double tiempoEstimado = 0;
    private double tiempoEjecutado = 0;
    private double porcentaje = 0;
    private String observaciones = "";
    private String estado = "";
    
    public Tareas() {
        super();
    }
    
    public int saveData() {
        int resultado = -1;
	String params = "" + idtarea + ",'" + nombreTarea + "', '" + Environment.sessionUser + "'," + tiempoEstimado + "," + tiempoEjecutado + "," + porcentaje + ",'" + observaciones + "',''";
	resultado = TaskManEnvironment.libSQLMini.getInt("taskman.addtarea",params);
        return resultado;
    }
        
    public void retrieveData() {
        String params = ""+idtarea;
        ResultSet data = TaskManEnvironment.libSQLMini.exFunction("taskman.gettarea",params);
        try {
            if (data.next()) {
                idtarea  = data.getInt("idtarea");
                nombreTarea = data.getString("nombretarea");
                usuario = data.getString("usuario");
                fechaCreacion = data.getString("fechacreacion");
                fechaInicio = data.getString("fechainicio");
                fechaFin = data.getString("fechafin");
                tiempoEstimado = data.getDouble("tiempoestimado");
                tiempoEjecutado = data.getDouble("tiempoejecutado");
                porcentaje = data.getDouble("porcentaje");
                observaciones = data.getString("observaciones");
                estado  = data.getString("estado");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setIdtarea(int idtarea) {
        this.idtarea = idtarea;
    }

    public int getIdtarea() {
        return idtarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
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

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setTiempoEjecutado(double tiempoEjecutado) {
        this.tiempoEjecutado = tiempoEjecutado;
    }

    public double getTiempoEjecutado() {
        return tiempoEjecutado;
    }
}
