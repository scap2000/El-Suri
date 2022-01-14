package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

public class ClassEncabezadoEncuesta {

    private int idencuestacomercial = -1;
    private String nroencuestador = "";
    private String nrozona = "";
    private String nroencuesta = "";
    private String fecha = "";
    private String usuario = "";
    private String fechacarga = "";
    private String estado = "";

    public ClassEncabezadoEncuesta() {
    
    }

    public void setIdencuestacomercial(int idencuestacomercial) {
	this.idencuestacomercial = idencuestacomercial;
    }

    public int getIdencuestacomercial() {
	return idencuestacomercial;
    }

    public void setNroencuestador(String nroencuestador) {
	this.nroencuestador = nroencuestador;
    }

    public String getNroencuestador() {
	return nroencuestador;
    }

    public void setNrozona(String nrozona) {
	this.nrozona = nrozona;
    }

    public String getNrozona() {
	return nrozona;
    }

    public void setNroencuesta(String nroencuesta) {
	this.nroencuesta = nroencuesta;
    }

    public String getNroencuesta() {
	return nroencuesta;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setFechacarga(String fechacarga) {
	this.fechacarga = fechacarga;
    }

    public String getFechacarga() {
	return fechacarga;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
