package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Contribucion {

    private int idContribucion = -1;
    private String nombre = "";
    private String descripcion = "";
    private String desde = "";
    private String hasta = "";
    private String ordenanza = "";
    private int idtipoimpuesto = -1;

    public Contribucion() {
    }

    public Contribucion(int _idcontribucion) {
	setIdContribucion(_idcontribucion);
    }

    public void setIdContribucion(int idContribucion) {
	this.idContribucion = idContribucion;
    }

    public int getIdContribucion() {
	return idContribucion;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDesde(String desde) {
	this.desde = desde;
    }

    public String getDesde() {
	return desde;
    }

    public void setHasta(String hasta) {
	this.hasta = hasta;
    }

    public String getHasta() {
	return hasta;
    }

    public void setOrdenanza(String ordenanza) {
	this.ordenanza = ordenanza;
    }

    public String getOrdenanza() {
	return ordenanza;
    }		
    public int saveData(){
	int result = -1;
	String params = "";
	    params = idContribucion + ",'"+nombre +"','"+descripcion+"',"+"'"+desde+"',"+"'"+hasta+"',"+"'"+ordenanza+"',"+idtipoimpuesto;
	    result = LibSQL.getInt("taxes.addContribucion", params);
	    idContribucion = result;
	return (result) ;
    }
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getContribucion",""+ idContribucion);
	try  {
	    if (result.next())  {
		setNombre(result.getString("nombre"));
		setDescripcion(result.getString("descripcion"));
		setDesde(result.getString("desde"));
		setHasta(result.getString("hasta"));
		setOrdenanza(result.getString("ordenanza"));
	        setIdtipoimpuesto(result.getInt("idtipoimpuesto"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	}
	
    }
    
    public int delete(){
	return LibSQL.getInt("taxes.deleteContribucion",""+ idContribucion);
    }

    public void setIdtipoimpuesto(int idtipoimpuesto) {
	this.idtipoimpuesto = idtipoimpuesto;
    }

    public int getIdtipoimpuesto() {
	return idtipoimpuesto;
    }
}
