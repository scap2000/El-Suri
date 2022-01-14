package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class PersonaEscasosRecursos {

    private int idPersonaEscasosRecursos = -1;
    private int idPersona;
    private int orden;
    private String nroDocumento;
    private String cuil;
    private String apellido;
    private String nombres;
    private String fechaNacimiento;
    private int edad;
    private String domicilio;
    private String barrio;
    private double monto;
    private int ordenBarrio;
    private String workstation;
    private String usuario;
    private String fechaAlta;
    private String fechaBaja;
    private String motivoBaja;
    private String estado;

    public PersonaEscasosRecursos() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "" + idPersonaEscasosRecursos + "," + idPersona + "," + orden + ",'" + domicilio + "','" + barrio + "','" + fechaAlta + "'";
        if (idPersonaEscasosRecursos == -1) {
            result = LibSQL.getInt("accionsocial.addpersonaescasosrecursos", params);
        } else {
            result = LibSQL.getInt("accionsocial.setpersonaescasosrecursos", params);
        }
        return result;
    }
    
    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getpersonaescasosrecursos", ""+ idPersonaEscasosRecursos);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                orden = rs.getInt("orden");
                nroDocumento = rs.getString("nrodocumento");
                cuil = rs.getString("cuil");
                apellido = rs.getString("apellido");
                nombres = rs.getString("nombres");
                fechaNacimiento = rs.getString("fechanacimiento");
                edad = rs.getInt("edad");
                domicilio = rs.getString("domicilio");
                barrio = rs.getString("barrio");
                monto = rs.getDouble("monto");
                ordenBarrio = rs.getInt("ordenbarrio");
                workstation = rs.getString("workstation");
                usuario = rs.getString("usuario");
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

    public void setIdPersonaEscasosRecursos(int idPersonaEscasosRecursos) {
        this.idPersonaEscasosRecursos = idPersonaEscasosRecursos;
    }

    public int getIdPersonaEscasosRecursos() {
        return idPersonaEscasosRecursos;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
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

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setOrdenBarrio(int ordenBarrio) {
        this.ordenBarrio = ordenBarrio;
    }

    public int getOrdenBarrio() {
        return ordenBarrio;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
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
}
