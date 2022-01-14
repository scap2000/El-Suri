package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;


public class AnticipoDeHaberes {
    
    private int idAnticipoDeHaberes = -1;
    private int anio = 2000;
    private int numero = 0;
    private String barcode = "";
    private String fechaEmision = "";
    private String fechaPago = "";
    private int idPersona = -1;
    private int idLegajo = -1;
    private int legajo = -1;
    private String apellido = "";
    private String nombres = "";
    private String fechaDescuento = "";
    private double monto = 0.0;
    private int idBookKeepingEntry = -1;
    private String usuario = "";
    private String estadoAnticipo = "";
    private String estado = "";
    private boolean anulado = false;
    
    public AnticipoDeHaberes() {
        
    }
    
    public AnticipoDeHaberes(int _idAnticipoDeHaberes) {
        idAnticipoDeHaberes = _idAnticipoDeHaberes;
    }

    public void setIdAnticipoDeHaberes(int idAnticipoDeHaberes) {
        this.idAnticipoDeHaberes = idAnticipoDeHaberes;
    }

    public int getIdAnticipoDeHaberes() {
        return idAnticipoDeHaberes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdLegajo(int idLegajo) {
        this.idLegajo = idLegajo;
    }

    public int getIdLegajo() {
        return idLegajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getLegajo() {
        return legajo;
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

    public void setFechaDescuento(String fechaDescuento) {
        this.fechaDescuento = fechaDescuento;
    }

    public String getFechaDescuento() {
        return fechaDescuento;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setIdBookKeepingEntry(int idBookKeepingEntry) {
        this.idBookKeepingEntry = idBookKeepingEntry;
    }

    public int getIdBookKeepingEntry() {
        return idBookKeepingEntry;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setEstadoAnticipo(String estadoAnticipo) {
        this.estadoAnticipo = estadoAnticipo;
    }

    public String getEstadoAnticipo() {
        return estadoAnticipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public int saveData() {             
        String params = idPersona + ","+ idLegajo + ","+ legajo +",'"+
                        apellido +"','"+ nombres +"','"+ fechaEmision +"','"+ fechaDescuento +"',"+ monto ;
        int result = -1;
        if (idAnticipoDeHaberes == -1){
            result = LibSQL.getInt("sueldos.addAnticipoDeHaberes",params);              
            idAnticipoDeHaberes = result;      
        } else {            
            params = idLegajo+","+ params;
            result = LibSQL.getInt("sueldos.setAnticipoDeHaberes",params);
        }               
        return result;
    }
    
    public void retrieveData() {
        String params = "" + idAnticipoDeHaberes;
        ResultSet data = LibSQL.exFunction("sueldos.getAnticipoDehaberes", params);
        try {
            if (data.next()) {
                setIdAnticipoDeHaberes(data.getInt("idanticipodehaberes"));
                setAnio(data.getInt("anio"));
                setNumero(data.getInt("numero"));
                setBarcode(data.getString("barcode"));
                setFechaEmision(data.getString("fechaemision"));
                setFechaPago(data.getString("fechapago"));
                setIdPersona(data.getInt("idpersona"));
                setIdLegajo(data.getInt("idlegajo"));
                setLegajo(data.getInt("legajo"));
                setApellido(data.getString("apellido"));
                setNombres(data.getString("nombres"));
                setFechaDescuento(data.getString("fechadescuento"));
                setMonto(data.getDouble("monto"));
                setIdBookKeepingEntry(data.getInt("idbookkeepingentry"));
                setUsuario(data.getString("usuario"));
                setEstadoAnticipo(data.getString("estadoanticipo"));
                setEstado(data.getString("estado"));
                setAnulado(data.getBoolean("anulado"));
            } else {
                idLegajo = -1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public boolean isAnulado() {
        return anulado;
    }
}


