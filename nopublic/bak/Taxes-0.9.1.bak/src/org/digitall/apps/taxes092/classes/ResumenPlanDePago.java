package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.apps.taxes.classes.TipoImpuesto;
import org.digitall.lib.sql.LibSQL;

public class ResumenPlanDePago {
    
    private double entrega = 0.00;
    private double deudaParcial = 0.00;
    private double interesPorMora = 0.00;
    private double subTotalDeuda = 0.00;
    private String bonificacionEspecial = "";
    private double montoBonificacionEspecial = 0.00;
    private String leyendaBonificacionEspecial = "";
    private double subTotal = 0.00;
    private double montoCubiertoEntrega = 0.00;
    private String periodoAnticipos = "";
    private double saldoAFinanciar = 0.00;
    private String periodoPlanDePago = "";
    private int cantidadAnticiposPP = 0;
    private double capital = 0.00;
    private double interes = 0.00;
    private double porcentajeCondIntereses = 0.00;
    private double montoCondIntereses = 0.00;
    private double porcentajeIntXFinanciacion = 0.00;
    private double montoIntXFinanciacion = 0.00;
    private double total = 0.00;
    private String leyendaCuotas = "";
    private double porcentajeBonificacionEspecial = 0.00;
    private int cantidadCuotas = 0;
    

    public void setEntrega(double entrega) {
        this.entrega = entrega;
    }

    public double getEntrega() {
        return entrega;
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

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setMontoCubiertoEntrega(double montoCubiertoEntrega) {
        this.montoCubiertoEntrega = montoCubiertoEntrega;
    }

    public double getMontoCubiertoEntrega() {
        return montoCubiertoEntrega;
    }

    public void setPeriodoAnticipos(String periodoAnticipos) {
        this.periodoAnticipos = periodoAnticipos;
    }

    public String getPeriodoAnticipos() {
        return periodoAnticipos;
    }

    public void setSaldoAFinanciar(double saldoAFinanciar) {
        this.saldoAFinanciar = saldoAFinanciar;
    }

    public double getSaldoAFinanciar() {
        return saldoAFinanciar;
    }

    public void setPeriodoPlanDePago(String periodoPlanDePago) {
        this.periodoPlanDePago = periodoPlanDePago;
    }

    public String getPeriodoPlanDePago() {
        return periodoPlanDePago;
    }

    public void setCantidadAnticiposPP(int cantidadAnticiposPP) {
        this.cantidadAnticiposPP = cantidadAnticiposPP;
    }

    public int getCantidadAnticiposPP() {
        return cantidadAnticiposPP;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getCapital() {
        return capital;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getInteres() {
        return interes;
    }

    public void setPorcentajeCondIntereses(double porcentajeCondIntereses) {
        this.porcentajeCondIntereses = porcentajeCondIntereses;
    }

    public double getPorcentajeCondIntereses() {
        return porcentajeCondIntereses;
    }

    public void setMontoCondIntereses(double montoCondIntereses) {
        this.montoCondIntereses = montoCondIntereses;
    }

    public double getMontoCondIntereses() {
        return montoCondIntereses;
    }

    public void setPorcentajeIntXFinanciacion(double porcentajeIntXFinanciacion) {
        this.porcentajeIntXFinanciacion = porcentajeIntXFinanciacion;
    }

    public double getPorcentajeIntXFinanciacion() {
        return porcentajeIntXFinanciacion;
    }

    public void setMontoIntXFinanciacion(double montoIntXFinanciacion) {
        this.montoIntXFinanciacion = montoIntXFinanciacion;
    }

    public double getMontoIntXFinanciacion() {
        return montoIntXFinanciacion;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setLeyendaCuotas(String leyendaCuotas) {
        this.leyendaCuotas = leyendaCuotas;
    }

    public String getLeyendaCuotas() {
        return leyendaCuotas;
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

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }
}
