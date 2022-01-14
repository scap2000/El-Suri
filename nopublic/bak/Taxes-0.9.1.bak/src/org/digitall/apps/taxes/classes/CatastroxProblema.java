package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class CatastroxProblema {
    
    int idcatastrosxproblemas = -1;
    int idcatastro = -1;
    String fechacarga = "";
    String problema = "";
    String usuario = "";
    String estado = "";
    boolean esproblema = false;
    
    public CatastroxProblema() {
        super();
    }

    public int saveData() {
        int resultado = -1;
        String params = "";
        if(idcatastrosxproblemas == -1){
            params = ""+idcatastro +",'"+ problema +"','"+ estado +"',"+ esproblema +"";
            resultado = LibSQL.getInt("taxes.addproblemaxcatastro",params);
        } else {
            params = ""+idcatastrosxproblemas + "," + idcatastro + ",'"+problema + "','" + estado + "'," + esproblema + "";
            resultado = LibSQL.getInt("taxes.setproblemaxcatastro",params);    
        }
        return resultado;
    } 
    
    public void retrieveData() {
        String params = ""+idcatastrosxproblemas;
        ResultSet data = LibSQL.exFunction("taxes.getcatastroxproblema",params);
        try {
            if (data.next()) {
                idcatastrosxproblemas  = data.getInt("idcatastrosxproblemas");
                idcatastro = data.getInt("idcatastro");
                fechacarga = data.getString("fechacarga");
                problema = data.getString("problema");
                usuario = data.getString("usuario");
                estado  = data.getString("estado");
                esproblema = data.getBoolean("esproblema");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setIdcatastrosxproblemas(int idcatastrosxproblemas) {
        this.idcatastrosxproblemas = idcatastrosxproblemas;
    }

    public int getIdcatastrosxproblemas() {
        return idcatastrosxproblemas;
    }

    public void setIdcatastro(int idcatastro) {
        this.idcatastro = idcatastro;
    }

    public int getIdcatastro() {
        return idcatastro;
    }

    public void setFechacarga(String fechacarga) {
        this.fechacarga = fechacarga;
    }

    public String getFechacarga() {
        return fechacarga;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getProblema() {
        return problema;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEsproblema(boolean esproblema) {
        this.esproblema = esproblema;
    }

    public boolean esproblema() {
        return esproblema;
    }
}
