package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Embarazo {

    private int idEmbarazo = -1;
    private int idPersona = -1;
    private String apellido = "";
    private String nombres = "";
    private String nroDocumento = "0";
    private String barrio = "";
    private String fechaNacimiento = "";
    private String fpp = "";
    private String fum = "";
    private double importe = 0;
    private int sector = 0;
    private int orden = 0;
    private int centroSalud = 0;
    private int ordenSector = 0;
    private String estado = "";
    private boolean vigente = false;
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String fechaAlta = "";

    public Embarazo() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idEmbarazo == -1) {
            params = "" + idEmbarazo + "," + idPersona + ",'" + barrio + "','" + fpp + "','" + fum + "','" + fechaAlta + "'";
            result = LibSQL.getInt("accionsocial.addembarazo", params);
        } else {
            result = LibSQL.getInt("accionsocial.setembarazo", params);
        }
        return result;
    }
    
    public void retrieveData() {
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getembarazo", ""+ idEmbarazo);
            if (rs.next()) {
                idPersona = rs.getInt("idpersona");
                apellido = rs.getString("apellido");
                nombres = rs.getString("nombres");
                nroDocumento = rs.getString("nrodocumento");
                barrio = rs.getString("barrio");
                fechaNacimiento = rs.getString("fechanacimiento");
                fpp = rs.getString("fpp");
                fum = rs.getString("fum");
                importe = rs.getDouble("importe");
                sector = rs.getInt("sector");
                orden = rs.getInt("orden");
                centroSalud = rs.getInt("centrosalud");
                ordenSector = rs.getInt("ordensector");
                estado = rs.getString("estado");
                vigente = rs.getBoolean("vigente");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                fechaAlta = rs.getString("fechaalta");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdEmbarazo(int idEmbarazo) {
        this.idEmbarazo = idEmbarazo;
    }

    public int getIdEmbarazo() {
        return idEmbarazo;
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

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setFpp(String fpp) {
        this.fpp = fpp;
    }

    public String getFpp() {
        return fpp;
    }

    public void setFum(String fum) {
        this.fum = fum;
    }

    public String getFum() {
        return fum;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public int getSector() {
        return sector;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setCentroSalud(int centroSalud) {
        this.centroSalud = centroSalud;
    }

    public int getCentroSalud() {
        return centroSalud;
    }

    public void setOrdenSector(int ordenSector) {
        this.ordenSector = ordenSector;
    }

    public int getOrdenSector() {
        return ordenSector;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
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

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
