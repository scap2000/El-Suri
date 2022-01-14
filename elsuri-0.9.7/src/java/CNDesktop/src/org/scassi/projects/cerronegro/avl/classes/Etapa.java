package org.scassi.projects.cerronegro.avl.classes;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Etapa {

    private int idEtapa = -1;
    private String nombre = "";
    private int numero = 0;
    private Point.Double latlong = new Point.Double(0.0,0.0);
    private int duracion = 0;
    private FranjaHoraria franjaHoraria;
    private String fechaHoraLlegadaDiagramada = "";

    public Etapa(FranjaHoraria _franjaHoraria, int _idEtapa) {
	franjaHoraria = _franjaHoraria;
	idEtapa = _idEtapa;
    }

    public int getIdEtapa() {
	return idEtapa;
    }

    public int getIdRuta() {
	return franjaHoraria.getRuta().getIdRuta();
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public int getNumero() {
	return numero;
    }

    public void setLatlong(Point2D.Double latlong) {
	this.latlong = latlong;
    }

    public Point2D.Double getLatlong() {
	return latlong;
    }

    public void setDuracion(int duracion) {
	this.duracion = duracion;
    }

    public int getDuracion() {
	return duracion;
    }

    public void setLatlong(double _lat, double _long) {
	latlong = new Point.Double(_lat, _long);
    }

    public int getIdFranjaHoraria() {
	return franjaHoraria.getIdFranjaHoraria();
    }

    public void setFechaHoraLlegadaDiagramada(String fechaHoraLlegadaDiagramada) {
	this.fechaHoraLlegadaDiagramada = fechaHoraLlegadaDiagramada;
    }

    public String getFechaHoraLlegadaDiagramada() {
	return fechaHoraLlegadaDiagramada;
    }

    public FranjaHoraria getFranjaHoraria() {
	return franjaHoraria;
    }
}
