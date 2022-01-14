package org.digitall.apps.taxes.classes;

public class CuotasTGS {

    private int cantCuotas = -1;
    private int primerAnticipo = -1;
    private int anioPrimerAnticipo = -1;
    private int ultimoAnticipo = -1;
    private int anioUltimoAnticipo = -1;
    private double importe = 0;
    private double intereses = 0;
    private int idcatastro = -1;
    private int catastro = -1;
    private String seccion = "";
    private String manzana = "";
    private String parcela = "";

    public CuotasTGS() {
    }

    public void setCantCuotas(int cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public int getCantCuotas() {
        return cantCuotas;
    }

    public void setPrimerAnticipo(int primerAnticipo) {
        this.primerAnticipo = primerAnticipo;
    }

    public int getPrimerAnticipo() {
        return primerAnticipo;
    }

    public void setAnioPrimerAnticipo(int anioPrimerAnticipo) {
        this.anioPrimerAnticipo = anioPrimerAnticipo;
    }

    public int getAnioPrimerAnticipo() {
        return anioPrimerAnticipo;
    }

    public void setUltimoAnticipo(int ultimoAnticipo) {
        this.ultimoAnticipo = ultimoAnticipo;
    }

    public int getUltimoAnticipo() {
        return ultimoAnticipo;
    }

    public void setAnioUltimoAnticipo(int anioUltimoAnticipo) {
        this.anioUltimoAnticipo = anioUltimoAnticipo;
    }

    public int getAnioUltimoAnticipo() {
        return anioUltimoAnticipo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIdcatastro(int idcatastro) {
        this.idcatastro = idcatastro;
    }

    public int getIdcatastro() {
        return idcatastro;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getManzana() {
        return manzana;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getParcela() {
        return parcela;
    }
}
