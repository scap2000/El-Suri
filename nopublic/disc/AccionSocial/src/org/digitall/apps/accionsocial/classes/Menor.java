package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Menor {

    private int idMenor = -1;
    private int idPersona = 0;
    private String apellido = "";
    private String nombres = "";
    private String nroDocumento = "";
    private String fechaNacimiento = "";
    private int edad = 0;
    private String estadoNutricional = "";
    private int idTutor = -1;
    private String tutor = "";
    private String nroDocumentoTutor = "";
    private String domicilioTutor = "";
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String estado = "";

    public Menor() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idMenor == -1) {
            params = "" + idMenor + "," + idPersona + ",'" + fechaNacimiento + "','" + estadoNutricional + "'," + idTutor + ",'" + tutor + "','" + nroDocumentoTutor + "','" + domicilioTutor + "','" + fechaAlta + "'";
            result = LibSQL.getInt("accionsocial.addmenor", params);
        } else {
            result = LibSQL.getInt("accionsocial.setmenor", params);
        }
        return result;
    }
    
    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getMenor", ""+ idMenor);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                apellido = rs.getString("apellido");
                nombres = rs.getString("nombres");
                nroDocumento = rs.getString("nrodocumento");
                fechaNacimiento = rs.getString("fechanacimiento");
                edad = rs.getInt("edad");
                estadoNutricional = rs.getString("estadonutricional");
                idTutor = rs.getInt("idtutor");
                tutor = rs.getString("tutor");
                nroDocumentoTutor = rs.getString("nrodocumentotutor");
                domicilioTutor = rs.getString("domiciliotutor");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                estado = rs.getString("estado");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEstadoNutricional(String estadoNutricional) {
        this.estadoNutricional = estadoNutricional;
    }

    public String getEstadoNutricional() {
        return estadoNutricional;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setNroDocumentoTutor(String nroDocumentoTutor) {
        this.nroDocumentoTutor = nroDocumentoTutor;
    }

    public String getNroDocumentoTutor() {
        return nroDocumentoTutor;
    }

    public void setDomicilioTutor(String domicilioTutor) {
        this.domicilioTutor = domicilioTutor;
    }

    public String getDomicilioTutor() {
        return domicilioTutor;
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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdMenor(int idMenor) {
        this.idMenor = idMenor;
    }

    public int getIdMenor() {
        return idMenor;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }
}
