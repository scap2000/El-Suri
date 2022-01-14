package org.digitall.apps.taxes092.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class EstadoCuenta {
    
    private double deudaParcial = 0.0;
    private double interesParcial = 0.0;
    private double subTotal = 0.0;
    private double montoBonificacion = 0.0;
    private int cantPeriodos = 0;
    private int periodoInicial = 0;
    private int anioInicial = 0;
    private int periodoFinal = 0;
    private int anioFinal = 0;
    private double total = 0.0;
    private Bien bien;
    private Bonificacion bonificacion;
    private Impuesto impuesto;
    private boolean cubreAnticipoActual;
                             
    public EstadoCuenta(Bien _bien, Bonificacion _bonificacion, Impuesto _impuesto,boolean _cubreAnticipoActual) {
        super();
        bien = _bien;
        bonificacion = _bonificacion;
        impuesto = _impuesto;
        cubreAnticipoActual = _cubreAnticipoActual;
    }

    public void retrieveData() {
        ResultSet result =
            LibSQL.exFunction("taxes.getEstadoCuenta", bien.getIdBien() + "," +
                              impuesto.getTipoImpuesto().getIdTipoImpuesto() +
                              "," + bonificacion.getPorcentaje() + "," +
                              cubreAnticipoActual);
        try {
            if (result.next()) {
                deudaParcial = result.getDouble("deudaParcial");
                interesParcial = result.getDouble("interesParcial");
                subTotal = result.getDouble("subTotal");
                montoBonificacion = result.getDouble("montoBonificacion");
                cantPeriodos = result.getInt("cantPeriodos");
                periodoInicial = result.getInt("periodoInicial");
                anioInicial = result.getInt("anioInicial");
                periodoFinal = result.getInt("periodoFinal");
                anioFinal = result.getInt("anioFinal");
                total = result.getDouble("total");
            }

        } catch (SQLException e) {
            // TODO
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO
            e.printStackTrace();
        }
    }
    
    public void setDeudaParcial(double deudaParcial) {
        this.deudaParcial = deudaParcial;
    }

    public double getDeudaParcial() {
        return deudaParcial;
    }

    public void setInteresParcial(double interesParcial) {
        this.interesParcial = interesParcial;
    }

    public double getInteresParcial() {
        return interesParcial;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setMontoBonificacion(double montoBonificacion) {
        this.montoBonificacion = montoBonificacion;
    }

    public double getMontoBonificacion() {
        return montoBonificacion;
    }

    public void setCantPeriodos(int cantPeriodos) {
        this.cantPeriodos = cantPeriodos;
    }

    public int getCantPeriodos() {
        return cantPeriodos;
    }

    public void setPeriodoInicial(int periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public int getPeriodoInicial() {
        return periodoInicial;
    }

    public void setAnioInicial(int anioInicial) {
        this.anioInicial = anioInicial;
    }

    public int getAnioInicial() {
        return anioInicial;
    }

    public void setPeriodoFinal(int periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public int getPeriodoFinal() {
        return periodoFinal;
    }

    public void setAnioFinal(int anioFinal) {
        this.anioFinal = anioFinal;
    }

    public int getAnioFinal() {
        return anioFinal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }
}
