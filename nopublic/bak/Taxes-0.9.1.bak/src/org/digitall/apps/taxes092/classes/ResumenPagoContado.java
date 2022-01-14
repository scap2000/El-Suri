package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.lib.sql.LibSQL;

public class ResumenPagoContado {
    
    private int anticipoDesde = -1;
    private int anticipoHasta = -1;
    private int anioDesde = -1;
    private int anioHasta = -1;
    private int cantAnticipos = -1;
    private double deudaParcial = 0.00;
    private double interesPorMora = 0.00;
    private double subTotalDeuda = 0.00;
    private String bonificacionEspecial = "";
    private double montoBonificacionEspecial = 0.00;
    private String leyendaBonificacionEspecial = "";
    private double porcentajeBonificacionEspecial = 0.00;
    private double total = 0.00;
    
    private Bien bien;
    private Bonificacion bonificacion;
    private TipoImpuesto tipoImpuesto;
    
    public ResumenPagoContado(Bien _bien, Bonificacion _bonificacion, TipoImpuesto _tipoImpuesto) {
        super();
        bien = _bien;
        bonificacion = _bonificacion;
        tipoImpuesto = _tipoImpuesto;
    }
    
    public void retrieveData(){
        try {
            String params = "" + bien.getIdBien() + "," + bonificacion.getPorcentaje() + "," + tipoImpuesto.getIdTipoImpuesto();
            ResultSet rs = LibSQL.exFunction("taxes.getResumenPagoContado",params);
            if (rs.next()){
                
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResumenPagoContado() {
        super();
    }

    public void setDeudaParcial(double deudaParcial) {
        this.deudaParcial = deudaParcial;
    }

    public double getDeudaParcial() {
        return deudaParcial;
    }

    public void setInteresPorMora(double interesPorMora) {
        this.interesPorMora = interesPorMora;
    }

    public double getInteresPorMora() {
        return interesPorMora;
    }

    public void setSubTotalDeuda(double subTotalDeuda) {
        this.subTotalDeuda = subTotalDeuda;
    }

    public double getSubTotalDeuda() {
        return subTotalDeuda;
    }

    public void setBonificacionEspecial(String bonificacionEspecial) {
        this.bonificacionEspecial = bonificacionEspecial;
    }

    public String getBonificacionEspecial() {
        return bonificacionEspecial;
    }

    public void setMontoBonificacionEspecial(double montoBonificacionEspecial) {
        this.montoBonificacionEspecial = montoBonificacionEspecial;
    }

    public double getMontoBonificacionEspecial() {
        return montoBonificacionEspecial;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setLeyendaBonificacionEspecial(String leyendaBonificacionEspecial) {
        this.leyendaBonificacionEspecial = leyendaBonificacionEspecial;
    }

    public String getLeyendaBonificacionEspecial() {
        return leyendaBonificacionEspecial;
    }

    public void setPorcentajeBonificacionEspecial(double porcentajeBonificacionEspecial) {
        this.porcentajeBonificacionEspecial = porcentajeBonificacionEspecial;
    }

    public double getPorcentajeBonificacionEspecial() {
        return porcentajeBonificacionEspecial;
    }

    public void setAnticipoDesde(int anticipoDesde) {
        this.anticipoDesde = anticipoDesde;
    }

    public int getAnticipoDesde() {
        return anticipoDesde;
    }

    public void setAnticipoHasta(int anticipoHasta) {
        this.anticipoHasta = anticipoHasta;
    }

    public int getAnticipoHasta() {
        return anticipoHasta;
    }

    public void setAnioDesde(int anioDesde) {
        this.anioDesde = anioDesde;
    }

    public int getAnioDesde() {
        return anioDesde;
    }

    public void setAnioHasta(int anioHasta) {
        this.anioHasta = anioHasta;
    }

    public int getAnioHasta() {
        return anioHasta;
    }

    public void setCantAnticipos(int cantAnticipos) {
        this.cantAnticipos = cantAnticipos;
    }

    public int getCantAnticipos() {
        return cantAnticipos;
    }
}
