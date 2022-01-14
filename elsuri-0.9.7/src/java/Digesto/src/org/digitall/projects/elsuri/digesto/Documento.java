package org.digitall.projects.elsuri.digesto;

public class Documento {

    private String tipo = "";
    private String nombre = "";
    private String archivo = "";
    private String numero = "";
    private String anio = "";
    private String descripcion = "";

    public Documento() {
    }

    public Documento(String _tipo, String _nombre, String _archivo) {
	tipo = _tipo;
	nombre = _nombre;
	archivo = _archivo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public String getTipo() {
	return tipo;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void setAnio(String anio) {
	this.anio = anio;
    }

    public String getAnio() {
	return anio;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setArchivo(String archivo) {
	this.archivo = archivo;
    }

    public String getArchivo() {
	return archivo;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }
}
