package org.scassi.projects.southwest.avl.classes;

import java.sql.ResultSet;
import java.util.Vector;
import org.digitall.lib.sql.LibSQL;

public class Ruta {

    private int idRuta = -1;
    private String nombre = "";
    private double longitud = 0.0;
    private int cantEtapas = 0;
    private int idCuadroTarifario = -1;
    private Vector<Etapa> etapas = new Vector<Etapa>();
    private FranjaHoraria franjaHoraria;

    public Ruta(FranjaHoraria _franjaHoraria, int _idRuta) {
	franjaHoraria = _franjaHoraria;
	idRuta = _idRuta;
    }

    public int getIdRuta() {
	return idRuta;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setLongitud(double longitud) {
	this.longitud = longitud;
    }

    public double getLongitud() {
	return longitud;
    }

    public void setCantEtapas(int cantEtapas) {
	this.cantEtapas = cantEtapas;
    }

    public int getCantEtapas() {
	return cantEtapas;
    }

    public void setIdCuadroTarifario(int idCuadroTarifario) {
	this.idCuadroTarifario = idCuadroTarifario;
    }

    public int getIdCuadroTarifario() {
	return idCuadroTarifario;
    }

    public int getDuracion() {
	int _duracion = 0;
	// for (int i = 0; i < etapas.size(); i++)
	for (Etapa _etapa : etapas) {
	    _duracion += _etapa.getDuracion();
	}
	return  _duracion;
    }

    public void loadData() {
	etapas.removeAllElements();
	ResultSet _etapas = LibSQL.exFunction("reportes.getAllEtapas", idRuta);
	try {
	    while (_etapas.next()) {
		Etapa _etapa = new Etapa(franjaHoraria, _etapas.getInt("idetapa"));
		_etapa.setNombre(_etapas.getString("nombre"));
		_etapa.setNumero(_etapas.getInt("numero"));
	        _etapa.setLatlong(_etapas.getDouble("latitudfin"), _etapas.getDouble("longitudfin"));
		_etapa.setDuracion(_etapas.getInt("duracion"));
		etapas.add(_etapa);
	    }
	    setCantEtapas(etapas.size());
	    ResultSet _ruta = LibSQL.exFunction("reportes.getRuta", idRuta);
	    if (_ruta.next()) {
		setNombre(_ruta.getString("nombre"));
	        setLongitud(_ruta.getDouble("longitud"));
		setIdCuadroTarifario(_ruta.getInt("idcuadrotarifario"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public Etapa getEtapa(int _index) {
	return etapas.elementAt(_index);
    }

}

