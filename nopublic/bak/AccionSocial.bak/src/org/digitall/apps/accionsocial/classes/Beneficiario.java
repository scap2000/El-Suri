package org.digitall.apps.accionsocial.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Beneficiario {
    
    private int idBeneficiario  = -1;
    private int idPersona = 0;
    private int idPlanSocial = 0;
    private String fechaAlta = "";
    private String fechaBaja = "";
    private String motivoBaja = "";
    private String fechaNacimiento = "";
    private int edad = 0;
    private int idTutor = 0;
    private String domicilio = "";
    private String centroSalud = "";
    private String estado = "";
    private int idSector = 0;
    private String tutor = "";
    private String estadoNutricional = "";
    private int orden = 0;
    private String numeroDocumento = "0";
    private String beneficiario = "";
    private String numeroDocumentoTutor = "0";
    private int firma = 0;
    private int idPrestacion = 0;

    public Beneficiario() {
        super();
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        
        if (this.fechaNacimiento == null) {
            this.fechaNacimiento = "";
        }
        
        if (idBeneficiario == -1)  {
            params = "" + idPersona + "," + idPlanSocial + ",'" + fechaAlta + "','" + fechaBaja + "','" + motivoBaja + "','"
                        + fechaNacimiento + "'," + edad + "," + idTutor + ",'" + domicilio + "','" + centroSalud + "',"
                        + idSector + ",'" + tutor + "','" + estadoNutricional + "'," + orden + ",'" + numeroDocumento + "','"
                        + beneficiario + "','" + numeroDocumentoTutor + "'," + firma + "," + idPrestacion;
            result = LibSQL.getInt("accionsocial.addBeneficiario", params);
            idBeneficiario = result; 
        } else {
            
            result = LibSQL.getInt("accionsocial.setBeneficiario", params);
        }
         return result;
    }
 
    public boolean deleteCar() {
        return LibSQL.getBoolean("accionsocial.delBeneficiario", ""+ idBeneficiario);
    }

    public void retrieveData(Persona _persona) {
        String params = "" + _persona.getIdPersona();
        idBeneficiario = LibSQL.getInt("accionsocial.getidbeneficiariobypersona",""+ params);
        if (idBeneficiario > 0) {
            retrieveData();
        }
    }

    public void retrieveData() {
        
        try {
            ResultSet rs = LibSQL.exFunction("accionsocial.getBeneficiario", ""+ idBeneficiario);
            if (rs.next()) {
                idBeneficiario  = rs.getInt("idbeneficiario");
                idPersona = rs.getInt("idpersona");
                idPlanSocial = rs.getInt("idplansocial");
                fechaAlta = rs.getString("fechaalta");
                fechaBaja = rs.getString("fechabaja");
                motivoBaja = rs.getString("motivobaja");
                fechaNacimiento = rs.getString("fechanacimiento");
                edad = rs.getInt("edad");
                idTutor = rs.getInt("idtutor");
                domicilio = rs.getString("domicilio");
                centroSalud = rs.getString("centrosalud");
                estado = rs.getString("estado");
                idSector = rs.getInt("idsector");
                tutor = rs.getString("tutor");
                estadoNutricional = rs.getString("estadonutricional");
                orden = rs.getInt("orden");
                numeroDocumento = rs.getString("numerodocumento");
                beneficiario = rs.getString("beneficiario");
                numeroDocumentoTutor = rs.getString("numerodocumentotutor");
                firma = rs.getInt("firma");
                idPrestacion = rs.getInt("idprestacion");       
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getCantidadPrestaciones(Prestacion _prestacion, int _idmes, int _anio) {
        String params = "" + idPersona + "," + _prestacion.getIdPrestacion() + ","+ _idmes + "," + _anio;
        return LibSQL.getDouble("accionsocial.getCantidadPrestacionSeleccionadaPorBeneficiario", params);
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
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

    public String getfechaAlta() {
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

    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento == "null") {
            this.fechaNacimiento = "";
        } else {
            this.fechaNacimiento = fechaNacimiento;
        }
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

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
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

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setEstadoNutricional(String estadoNutricional) {
        this.estadoNutricional = estadoNutricional;
    }

    public String getEstadoNutricional() {
        return estadoNutricional;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setNumeroDocumentoTutor(String numeroDocumentoTutor) {
        this.numeroDocumentoTutor = numeroDocumentoTutor;
    }

    public String getNumeroDocumentoTutor() {
        return numeroDocumentoTutor;
    }

    public void setFirma(int firma) {
        this.firma = firma;
    }

    public int getFirma() {
        return firma;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }
    
    public void cargarse(Persona _persona) {
        idBeneficiario = LibSQL.getInt("accionsocial.getidbeneficiariobypersona", _persona.getIdPersona());
        retrieveData();
    }
    
}
