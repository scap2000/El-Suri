package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class AlicuotaContribucion {

    private int idalicuotacontribucion = -1;
    private int idcontribucion = 0;
    private String nombre = "";
    private double valorxmil = 0.0;
    private double valormodulo = 0.0;
    private double porcentaje = 0.0;
    private int multiplicador = 0;
    private String estado = "";
    private boolean fijo = false;
    private double montofijo = 0.0;
    private int idcuentadebe = -1;
    private int idcuentahaber = -1;
    private int idCuentaDeduccion = -1;
    private int idCuentaInteres = -1;
    private boolean libredeuda = false; //true: libre deuda; false: no es libre deuda
    private boolean baja = false; //true: baja; false: no es baja
    private boolean multa= false; //true: multa; false: no es multa
    private String xml = ""; //path del informe xml
    private String textoOrdenado = ""; 
    private int ultimoNumero = 0; 
    
     public int saveData(){
	 int result = -1;
	 String params = "";
	     params = idalicuotacontribucion +","+idcontribucion +",'"+nombre +"',"+valorxmil+","+""+valormodulo+","+""+porcentaje+","+multiplicador+",'"+estado+"','"+fijo+
		     "'," + montofijo +", "+idcuentadebe + ","+""+idcuentahaber+","+""+idCuentaDeduccion+", "+idCuentaInteres + ",'" + libredeuda + "', '"+ baja + "', '" + multa +
		     "', '"+xml+ "'";
	     result = LibSQL.getInt("taxes.addAlicuotaContribucion", params);
	     idalicuotacontribucion = result;
	 return (result) ;
     }
     
    public int delete(){
	return LibSQL.getInt("taxes.deleteAlicuotaContribucion",""+ idalicuotacontribucion);
    }

    public AlicuotaContribucion() {
    }

    public void setIdalicuotacontribucion(int idalicuotacontribucion) {
	this.idalicuotacontribucion = idalicuotacontribucion;
    }

    public int getIdalicuotacontribucion() {
	return idalicuotacontribucion;
    }

    public void setIdcontribucion(int idcontribucion) {
	this.idcontribucion = idcontribucion;
    }

    public int getIdcontribucion() {
	return idcontribucion;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setValorxmil(double valorxmil) {
	this.valorxmil = valorxmil;
    }

    public double getValorxmil() {
	return valorxmil;
    }

    public void setValormodulo(double valormodulo) {
	this.valormodulo = valormodulo;
    }

    public double getValormodulo() {
	return valormodulo;
    }

    public void setPorcentaje(double porcentaje) {
	this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
	return porcentaje;
    }

    public void setMultiplicador(int multiplicador) {
	this.multiplicador = multiplicador;
    }

    public int getMultiplicador() {
	return multiplicador;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setFijo(boolean fijo) {
	this.fijo = fijo;
    }

    public boolean isFijo() {
	return fijo;
    }

    public void setMontofijo(double montofijo) {
	this.montofijo = montofijo;
    }

    public double getMontofijo() {
	return montofijo;
    }

    public void setIdcuentadebe(int idcuentadebe) {
	this.idcuentadebe = idcuentadebe;
    }

    public int getIdcuentadebe() {
	return idcuentadebe;
    }

    public void setIdcuentahaber(int idcuentahaber) {
	this.idcuentahaber = idcuentahaber;
    }

    public int getIdcuentahaber() {
	return idcuentahaber;
    }

    public void setIdcuentabonificacion(int idcuentabonificacion) {
	this.idCuentaDeduccion = idcuentabonificacion;
    }

    public int getIdcuentabonificacion() {
	return idCuentaDeduccion;
    }

    public void setIdcuentainteres(int idcuentainteresxmora) {
	this.idCuentaInteres = idcuentainteresxmora;
    }

    public int getIdcuentainteresxmora() {
	return idCuentaInteres;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getAlicuotaContribucionSeleccionada",""+ idalicuotacontribucion);
	try  {
	    if (result.next())  {
		setNombre(result.getString("nombre"));
		setIdalicuotacontribucion(result.getInt("idalicuotacontribucion"));
		setIdcontribucion(result.getInt("idcontribucion"));
		setValorxmil(result.getDouble("valorxmil"));
		setValormodulo(result.getDouble("valormodulo"));
		setPorcentaje(result.getDouble("porcentaje"));
		setMultiplicador(result.getInt("multiplicador"));
		setEstado(result.getString("estado"));
		setFijo(result.getBoolean("fijo"));
		setMontofijo(result.getDouble("montofijo"));
		setIdcuentadebe(result.getInt("idcuentadebe"));
	        setIdcuentahaber(result.getInt("idcuentahaber"));
	        setIdcuentabonificacion(result.getInt("idcuentadeduccion"));
	        setIdcuentainteres(result.getInt("idcuentainteres"));
	        setLibredeuda(result.getBoolean("libredeuda"));
		setBaja(result.getBoolean("baja"));
	        setMulta(result.getBoolean("multa"));
		setXml(result.getString("xml"));
                setTextoOrdenado(result.getString("textoordenado"));
	        setUltimoNumero(result.getInt("ultimonumero"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	}
	
    }

    public void setIdCuentaDeduccion(int idCuentaDeduccion) {
	this.idCuentaDeduccion = idCuentaDeduccion;
    }

    public int getIdCuentaDeduccion() {
	return idCuentaDeduccion;
    }

    public void setIdCuentaInteres(int idCuentaInteres) {
	this.idCuentaInteres = idCuentaInteres;
    }

    public int getIdCuentaInteres() {
	return idCuentaInteres;
    }

    public void setLibredeuda(boolean libredeuda) {
	this.libredeuda = libredeuda;
    }

    public boolean isLibredeuda() {
	return libredeuda;
    }

    public void setBaja(boolean baja) {
	this.baja = baja;
    }

    public boolean isBaja() {
	return baja;
    }

    public void setMulta(boolean multa) {
	this.multa = multa;
    }

    public boolean isMulta() {
	return multa;
    }

    public void setXml(String xml) {
	this.xml = xml;
    }

    public String getXml() {
	return xml;
    }

    public void setTextoOrdenado(String textoOrdenado) {
        this.textoOrdenado = textoOrdenado;
    }

    public String getTextoOrdenado() {
        return textoOrdenado;
    }

    public void setUltimoNumero(int ultimoNumero) {
        this.ultimoNumero = ultimoNumero;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }
}
