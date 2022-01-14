package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaActVarias {

    private int idboletaactvarias = -1;
    private int idaccountingentry = -1;
    private String fechapago = "";
    private String fechaimpresion = "";
    private String contribuyente = "";
    private String padron = "";
    private String dni = "";
    private String rubro = "";
    private String razonsocial = "";
    private String expte = "";
    private String nrocuenta = "-1";
    private String concepto = "";
    private String vencimiento = "";
    private double importe = 0;
    private double recargo = 0;
    private double deducciones = 0;
    private double total = 0;
    private int idusuario = -1;
    private int nroimpresiones = -1;
    private String estado = "";
    private String localidad = "";
    private String domicilio = "";
    private double cuotamensual = 0;
    private String usuario = "";
    private String barCode = "";    
    private String cuit = "";
    private String categoria = "";
    private String numero = "";
    private int anio = 0;
    private int anticipo = 0;
    private int idcomercio = -1;
    private boolean pagada = false;
    private int iddescuento = -1;
    private String nombredescuento = "";
    
    public BoletaActVarias() {
    }

    public void setIdaccountingentry(int _idaccountingentry) {
        idaccountingentry = _idaccountingentry;
    }

    public int getIdaccountingentry() {
        return idaccountingentry;
    }

    public void setFechapago(String _fechapago) {
        fechapago = _fechapago;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechaimpresion(String _fechaimpresion) {
        fechaimpresion = _fechaimpresion;
    }

    public String getFechaimpresion() {
        return fechaimpresion;
    }

    public void setContribuyente(String _contribuyente) {
        contribuyente = _contribuyente;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setPadron(String _padron) {
        padron = _padron;
    }

    public void setDni(String _dni) {
        dni = _dni;
    }

    public String getManzana() {
        return dni;
    }

    public void setRubro(String _rubro) {
        rubro = _rubro;
    }

    public String getParcela() {
        return rubro;
    }

    public void setExpte(String _expte) {
        expte = _expte;
    }

    public String getExpte() {
        return expte;
    }

    public void setNrocuenta(String _nrocuenta) {
        nrocuenta = _nrocuenta;
    }

    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setConcepto(String _concepto) {
        concepto = _concepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setVencimiento(String _vencimiento) {
        vencimiento = _vencimiento;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setImporte(double _importe) {
        importe = _importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setRecargo(double _recargo) {
        recargo = _recargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setDeducciones(double _deducciones) {
        deducciones = _deducciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setTotal(double _total) {
        total = _total;
    }

    public double getTotal() {
        return total;
    }

    public void setIdusuario(int _idusuario) {
        idusuario = _idusuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setNroimpresiones(int _nroimpresiones) {
        nroimpresiones = _nroimpresiones;
    }

    public int getNroimpresiones() {
        return nroimpresiones;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setLocalidad(String _localidad) {
        localidad = _localidad;
    }

    public String getLocalidad() {
        return localidad;
    }
    
    public void setDomicilio(String _domicilio) {
        domicilio = _domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }
    
    public void setCuotaMensual(double _cuotaMensual) {
        cuotamensual = _cuotaMensual;
    }

    public double getCuotaMensual() {
        return cuotamensual;
    }

    public void setUsuario(String _usuario) {
        usuario = _usuario;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public void setBarCode(String _barCode) {
        barCode = _barCode;
    }

    public String getBarCode() {
        return barCode;
    }
   
    public void setCuit(String _cuit) {
        cuit = _cuit;
    }

    public String getCuit() {
        return cuit;
    }
    
    public int saveData(){ 
        String params = "";
        int result = -1;
        params = idcomercio +","+ idaccountingentry +",'"+ fechapago + "','"+ fechaimpresion +"','" + 
                 contribuyente + "','" + padron + "','" + dni + "','" + rubro + "','"+ razonsocial +"','"+ 
                 expte +"','"+ nrocuenta +"','"+ concepto +"','"+ vencimiento +"',"+ importe +","+ 
                 recargo +","+ deducciones +","+ total +","+ idusuario +","+ nroimpresiones +",'"+ 
                 estado +"','"+ localidad +"','"+ domicilio +"',"+ cuotamensual +",'"+ usuario +"','"+ cuit +"'";
        if (idboletaactvarias == -1)  {
            result = LibSQL.getInt("taxes.addBoletaActVarias",params);
            idboletaactvarias = result; 
        } else  {
            params = idboletaactvarias +","+ params;
            result = LibSQL.getInt("taxes.setBoletaActVarias",params); //falta crearla (verificar si es necesario)
        }
        return result;
    }
    
    public void retrieveData() {
        ResultSet result = LibSQL.exFunction("taxes.getBoletaActVarias", idboletaactvarias);
        try {
            if (result.next()) {
                idboletaactvarias = result.getInt("idboletaactvarias");
                idaccountingentry = result.getInt("idaccountingentry");
                fechapago = result.getString("fechapago");
                fechaimpresion = result.getString("fechaimpresion");
                contribuyente = result.getString("contribuyente");
                padron = result.getString("padron");
                dni = result.getString("dni");
                rubro = result.getString("rubro");
                razonsocial = result.getString("razonsocial");
                expte = result.getString("expte");
                nrocuenta = result.getString("nrocuenta");
                concepto = result.getString("concepto");
                vencimiento = result.getString("vencimiento"); 
                importe = result.getDouble("importe");
                recargo = result.getDouble("recargo");
                deducciones = result.getDouble("deducciones");
                total = result.getDouble("total");
                idusuario = result.getInt("idusuario");
                nroimpresiones = result.getInt("nroimpresiones");
                estado = result.getString("estado");
                localidad = result.getString("localidad");
                domicilio = result.getString("domicilio");
                cuotamensual = result.getDouble("cuotamensual");
                usuario = result.getString("usuario");
                barCode = result.getString("barcode");
                cuit = result.getString("cuit");
                anio = result.getInt("anio");
                numero = result.getString("numero");
                iddescuento = result.getInt("iddescuento");
                nombredescuento = result.getString("nombredescuento");
            } 
        } catch (SQLException e) {
            // TODO
            System.out.println("error");
        } catch (NullPointerException e) {
            // TODO
        }
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setIdcomercio(int idcomercio) {
        this.idcomercio = idcomercio;
    }

    public int getIdcomercio() {
        return idcomercio;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setIddescuento(int iddescuento) {
        this.iddescuento = iddescuento;
    }

    public int getIddescuento() {
        return iddescuento;
    }

    public void setNombredescuento(String nombredescuento) {
        this.nombredescuento = nombredescuento;
    }

    public String getNombredescuento() {
        return nombredescuento;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnticipo(int anticipo) {
        this.anticipo = anticipo;
    }

    public int getAnticipo() {
        return anticipo;
    }
    
    public int delBoletaActVarias(){
        return LibSQL.getInt("taxes.delBoletaActVarias",""+ idboletaactvarias);
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public String getPadron() {
        return padron;
    }

    public void setRazonsocial(String _razonsocial) {
        razonsocial = _razonsocial;
    }

    public void setIdboletaactvarias(int idboletaactvarias) {
        this.idboletaactvarias = idboletaactvarias;
    }

    public int getIdboletaactvarias() {
        return idboletaactvarias;
    }
}
