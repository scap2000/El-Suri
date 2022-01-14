package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Tbc {
    
    private int idTbc = -1;
    private int idPersona = -1;
    private String apellido = "";
    private String nombres = "";
    private String nroDocumento = "";
    private String fechaNacimiento = "";
    private String barrio = "";
    private String centroSalud = "";
    private String estado = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String fechaAlta = "";
    
    public Tbc() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idTbc == -1) {
            params = "" + idTbc + "," + idPersona + ",'" + barrio + "','" + centroSalud + "','" + fechaAlta + "'";
            result = LibSQL.getInt("accionsocial.addtbc", params);
        } else {
            result = LibSQL.getInt("accionsocial.settbc", params);
        }
        return result;
    }
    
    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.gettbc", ""+ idTbc);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                apellido = rs.getString("apellido");
                nombres = rs.getString("nombre");
                nroDocumento = rs.getString("nrodocumento");
                fechaNacimiento = rs.getString("fechanacimiento");
                barrio = rs.getString("barrio");
                centroSalud = rs.getString("centrosalud");
                estado = rs.getString("estado");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                fechaAlta = rs.getString("fechaalta");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdTbc(int idTbc) {
        this.idTbc = idTbc;
    }

    public int getIdTbc() {
        return idTbc;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setCentroSalud(String centroSalud) {
        this.centroSalud = centroSalud;
    }

    public String getCentroSalud() {
        return centroSalud;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
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

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }
}
