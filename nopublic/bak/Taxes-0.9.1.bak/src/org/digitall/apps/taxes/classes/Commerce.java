package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Commerce {
    
    private int idpadron = -1;
    private String padron = "";
    private String contribuyente = "";
    private String dni = "";
    private String fechainicio = "";
    private String rubro = "";
    private String razonsocial = "";
    private String domicilio = "";
    private String expte = "";
    private String estado = "";
    private double cuotamensual = 0;
    private String cuit = "";
    private String fechaBaja = "";
     private String nroCuenta ="";
    private int idComercio = -1;

    public Commerce() {
    }

    public void setIdpadron(int _idpadron) {
        idpadron = _idpadron;
    }

    public int getIdpadron() {
        return idpadron;
    }

    public void setPadron(String _padron) {
        padron = _padron;
    }

    public String getPadron() {
        return padron;
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

    public void setFechainicio(String _fechainicio) {
        fechainicio = _fechainicio;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setRubro(String _rubro) {
        rubro = _rubro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRazonsocial(String _razonsocial) {
        razonsocial = _razonsocial;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setDomicilio(String _domicilio) {
        domicilio = _domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setExpte(String _expte) {
        expte = _expte;
    }

    public String getExpte() {
        return expte;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setCuotamensual(double _cuotamensual) {
        cuotamensual = _cuotamensual;
    }

    public double getCuotamensual() {
        return cuotamensual;
    }
    
    public void setCuit(String _cuit) {
        cuit = _cuit;
    }

    public String getCuit() {
        return cuit;
    }
    

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }
    
    public int saveData(){
        int result = -1;
        String params = "";
        if (idpadron == -1)  {
            params = "'"+ padron +"','"+ contribuyente +"','"+ dni +"','"+ fechainicio +"','"+ rubro +"','"+ 
                          razonsocial +"','"+ domicilio +"','" + expte +"'," + cuotamensual +",'"+ cuit +"','"+
                          fechaBaja +"'";
            result = LibSQL.getInt("taxes.addCommerce", params);
            idpadron = result;
        } else {
            params = ""+ idpadron +",'"+ padron +"','"+ contribuyente +"','"+ dni +"','"+ fechainicio +"','"+ rubro +"','"+ 
                         razonsocial +"','"+ domicilio +"','" + expte +"'," + cuotamensual +",'"+ cuit +"','"+
                         fechaBaja +"'";
            result = LibSQL.getInt("taxes.setCommerce", params);
        }
         return result;
    }
    
    public void retrieveData(){
        try {
            ResultSet rs = LibSQL.exFunction("taxes.getCommerce", ""+ idpadron);
            if(rs.next()){
                idpadron = rs.getInt("idpadron");
                padron = rs.getString("padron");
                contribuyente = rs.getString("contribuyente");
                dni = rs.getString("dni");
                fechainicio = rs.getString("fechainicio");
                rubro = rs.getString("rubro");
                razonsocial = rs.getString("razonsocial");
                domicilio = rs.getString("domicilio");
                expte = rs.getString("expte");
                estado = rs.getString("estado");
                cuotamensual = rs.getDouble("cuotamensual");
                cuit = rs.getString("cuit");
                fechaBaja = rs.getString("fechabaja");
                nroCuenta = rs.getString("barcode");
		idComercio = rs.getInt("idcomercio");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int setIdboletaActVariasOnCuotaActVarias(int _anticipo, int _anio, int _idBoletaActVarias){
        int result = LibSQL.getInt("taxes.setCuotasActVariasByIdboletaActVarias",idpadron +","+ _anticipo +","+ _anio +","+ _idBoletaActVarias);
        return result;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }
    
    public boolean deleteComercio(){
	return LibSQL.getBoolean("taxes.delCommerce", ""+ idpadron);
    }

    public void setIdComercio(int idComercio) {
	this.idComercio = idComercio;
    }

    public int getIdComercio() {
	return idComercio;
    }
}
