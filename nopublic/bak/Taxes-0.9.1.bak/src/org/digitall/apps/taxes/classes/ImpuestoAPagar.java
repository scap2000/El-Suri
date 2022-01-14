package org.digitall.apps.taxes.classes;

public class ImpuestoAPagar {

    private int idTipoImpuesto = -1;
    private int idBien = -1;
    private String nroBien = "";
    private String contribuyente = "";
    private String dni = "";

    public ImpuestoAPagar() {
    }

    public void setIdTipoImpuesto(int _idTipoImpuesto) {
        idTipoImpuesto = _idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    public void setContribuyente(String _contribuyente) {
        contribuyente = _contribuyente;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setDni(String _dni) {
        dni = _dni;
    }

    public String getDni() {
        return dni;
    }

    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    public int getIdBien() {
        return idBien;
    }

    public void setNroBien(String nroBien) {
        this.nroBien = nroBien;
    }

    public String getNroBien() {
        return nroBien;
    }
}
