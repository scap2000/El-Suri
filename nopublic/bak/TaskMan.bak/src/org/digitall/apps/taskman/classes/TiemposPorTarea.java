package org.digitall.apps.taskman.classes;

import java.sql.ResultSet;

import org.digitall.apps.taskman.TaskManEnvironment;
import org.digitall.lib.environment.Environment;

public class TiemposPorTarea {
    
    private int idTiempoPorTarea = -1;
    private int idTarea = -1;
    private String fechaHoraInicio = "";
    private String fechaHoraFin = "";
    private String usuario = "";
    private double porcentaje = 0;
    private String estado = "";
    private Tareas tarea = new Tareas();
    
    public TiemposPorTarea() {
        super();
    }
    
    public int saveData() {
        int resultado = -1;
	String params = "" + idTiempoPorTarea + "," + idTarea + ",'" + Environment.sessionUser + "'," + porcentaje + ",''";
	resultado = TaskManEnvironment.libSQLMini.getInt("taskman.addtiempoportarea",params);
        idTiempoPorTarea = resultado;
        return resultado;
    }
        
    public void retrieveData() {
        String params = ""+idTiempoPorTarea;
        ResultSet data = TaskManEnvironment.libSQLMini.exFunction("taskman.gettiempoportarea",params);
        try {
            if (data.next()) {
                idTarea  = data.getInt("idtarea");
                fechaHoraInicio = data.getString("fechahorainicio");
                fechaHoraFin = data.getString("fechahorafin");
                usuario = data.getString("usuario");
                porcentaje = data.getDouble("porcentaje");
                estado  = data.getString("estado");
                //tarea.setIdtarea(idTarea);
                //tarea.retrieveData();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setidTiempoPorTarea(int idTiempoPorTarea) {
        this.idTiempoPorTarea = idTiempoPorTarea;
    }

    public int getidTiempoPorTarea() {
        return idTiempoPorTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public Tareas getTarea() {
        return tarea;
    }
}
