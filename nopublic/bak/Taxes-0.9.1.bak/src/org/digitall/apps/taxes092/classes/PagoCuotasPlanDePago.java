package org.digitall.apps.taxes092.classes;

import org.digitall.apps.taxes.classes.PlanDePago;

public class PagoCuotasPlanDePago {

    private int cantidadCuotas = 0;
    private double montoTotal = 0.0;
    private PlanDePago planDePago = new PlanDePago();
    private double importe = 0.0;
    private double recargoPorMora = 0.0;
    private double deduccion = 0.0;
    private String fecha = "";

    public PagoCuotasPlanDePago() {
    }

    public void setCantidadCuotas(int cantidadCuotas) {
	this.cantidadCuotas = cantidadCuotas;
    }

    public int getCantidadCuotas() {
	return cantidadCuotas;
    }

    public void setMonto(double montoTotal) {
	this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
	return montoTotal;
    }

    public void setPlanDePago(PlanDePago planDePago) {
	this.planDePago = planDePago;
    }

    public PlanDePago getPlanDePago() {
	return planDePago;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public double getImporte() {
	return importe;
    }

    public void setRecargoPorMora(double recargoPorMora) {
	this.recargoPorMora = recargoPorMora;
    }

    public double getRecargoPorMora() {
	return recargoPorMora;
    }

    public void setDeduccion(double deduccion) {
	this.deduccion = deduccion;
    }

    public double getDeduccion() {
	return deduccion;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }
}
