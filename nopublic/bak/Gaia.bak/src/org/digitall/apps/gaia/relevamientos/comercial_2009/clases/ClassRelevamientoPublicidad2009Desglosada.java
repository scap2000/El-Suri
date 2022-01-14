package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import org.digitall.lib.sql.LibSQL;

public class ClassRelevamientoPublicidad2009Desglosada {
    
    private int idrelevamientopublicidad_desglosada = -1;
    private int iddetallerelevamiento = -1;
    
    private String texto = "";
    private double ancho = 0.0;
    private double alto = 0.0;
    private int faz = -1;
    private double superficie = 0.0;
    private String iluminacion = "";
    private String forma = "";
    private int idempresa = -1;
    private double x = 0;
    private double y = 0;
    
    public ClassRelevamientoPublicidad2009Desglosada() {
        super();
    }

    public void setIdrelevamientopublicidad_desglosada(int idrelevamientopublicidad_desglosada) {
        this.idrelevamientopublicidad_desglosada = idrelevamientopublicidad_desglosada;
    }

    public int getIdrelevamientopublicidad_desglosada() {
        return idrelevamientopublicidad_desglosada;
    }

    public void setIddetallerelevamiento(int iddetallerelevamiento) {
        this.iddetallerelevamiento = iddetallerelevamiento;
    }

    public int getIddetallerelevamiento() {
        return iddetallerelevamiento;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAlto() {
        return alto;
    }

    public void setFaz(int faz) {
        this.faz = faz;
    }

    public int getFaz() {
        return faz;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
    
    public int saveData() {
        int result = -1;
        String params = ""+iddetallerelevamiento + "," + x + "," + y + ",'" + texto + "'," + ancho + "," + alto + "," + faz + "," + superficie + ",'" + iluminacion + "','" + forma + "'," + idempresa;
        if (idrelevamientopublicidad_desglosada == -1)  {
            result = LibSQL.getInt("gea_salta.addpublicidad_propaganda", params);
        } else if (idrelevamientopublicidad_desglosada > 0){
            params = "";
            //result = LibSQL.getInt("gea_salta.addrelevamientopublicidad_2009_desglosada", params);
        }
        return result;
    }
}
