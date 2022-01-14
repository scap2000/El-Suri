/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * PlanDePago.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class PlanDePago {

    private int idplandepago = -1;
    private int idtipoplandepago = -1;
    private int idbien = -1;
    private String fechainicio = "";
    private String fechafin = "";
    private int cantcuotas = -1;
    private String nombre = "";
    private String categoria = "";
    private int anticipodesde = -1;
    private int aniodesde = -1;
    private int anticipohasta = -1;
    private int aniohasta = -1;
    private String estado = "";
    private double montototal = 0.0;
    private boolean contado = false;
    private String titular = "";
    private boolean plandepagocaido = false;
    private boolean vigente = false;
    private double porcentajeIntFin = 0.0;
    private double montoInteresFinanciacion = 0.0;

    public PlanDePago() {
    }

    public void setIdplandepago(int _idplandepago) {
	idplandepago = _idplandepago;
    }

    public int getIdplandepago() {
	return idplandepago;
    }

    public void setIdtipoplandepago(int _idtipoplandepago) {
	idtipoplandepago = _idtipoplandepago;
    }

    public int getIdtipoplandepago() {
	return idtipoplandepago;
    }

    public void setIdbien(int _idbien) {
	idbien = _idbien;
    }

    public int getIdbien() {
	return idbien;
    }

    public void setFechainicio(String _fechainicio) {
	fechainicio = _fechainicio;
    }

    public String getFechainicio() {
	return fechainicio;
    }

    public void setFechafin(String _fechafin) {
	fechafin = _fechafin;
    }

    public String getFechafin() {
	return fechafin;
    }

    public void setCantcuotas(int _cantcuotas) {
	cantcuotas = _cantcuotas;
    }

    public int getCantcuotas() {
	return cantcuotas;
    }

    public void setNombre(String _nombre) {
	nombre = _nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setCategoria(String _categoria) {
	categoria = _categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setAnticipodesde(int _anticipodesde) {
	anticipodesde = _anticipodesde;
    }

    public int getAnticipodesde() {
	return anticipodesde;
    }

    public void setAniodesde(int _aniodesde) {
	aniodesde = _aniodesde;
    }

    public int getAniodesde() {
	return aniodesde;
    }

    public void setAnticipohasta(int _anticipohasta) {
	anticipohasta = _anticipohasta;
    }

    public int getAnticipohasta() {
	return anticipohasta;
    }

    public void setAniohasta(int _aniohasta) {
	aniohasta = _aniohasta;
    }

    public int getAniohasta() {
	return aniohasta;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setMontototal(double _montototal) {
	montototal = _montototal;
    }

    public double getMontototal() {
	return montototal;
    }

    public void setContado(boolean _contado) {
	contado = _contado;
    }

    public boolean isContado() {
	return contado;
    }

    public void setTitular(String titular) {
	this.titular = titular;
    }

    public String getTitular() {
	return titular;
    }
    
    public boolean tienePlanPagoTgs(){
	boolean resultado = false;
	boolean cancelado = false;
	if ((idtipoplandepago == 1) || (idtipoplandepago == 4)){
	    cancelado = LibSQL.getBoolean("taxes.esplandepagocancelado","" + idplandepago);
	    System.out.println("cancelado : "+cancelado);
	    if(!cancelado && isVigente()){
		resultado = true;	
	    }
	}
	return resultado;
    }
    
    public boolean tienePlanPagoInmob(){
	boolean resultado = false;
	boolean cancelado = false;
	if ((idtipoplandepago == 2) || (idtipoplandepago == 5)){
	    cancelado = LibSQL.getBoolean("taxes.esplandepagocancelado","" + idplandepago);
	    if(!cancelado && isVigente()){
	        resultado = true;       
	    }
	}
	return resultado;
    }
    
    public boolean tienePlanPagoAutomotor(){
	boolean resultado = false;
	boolean cancelado = false;
	if ((idtipoplandepago == 3) || (idtipoplandepago == 6)){
	    cancelado = LibSQL.getBoolean("taxes.esplandepagocancelado","" + idplandepago);
	    if(!cancelado && isVigente()){
	        resultado = true;       
	    }
	}
	return resultado;
    }
    
    public void retrieveData(){
	ResultSet planDePago = LibSQL.exFunction("taxes.getPlanDePago", idplandepago);
	try {
	    if (planDePago.next()) {
		idtipoplandepago = planDePago.getInt("idtipoplandepago");
	        idbien = planDePago.getInt("idbien");
	        fechainicio = planDePago.getString("fechainicio");
	        fechafin = planDePago.getString("fechafin");
	        cantcuotas = planDePago.getInt("cantcuotas");
	        nombre = planDePago.getString("nombre");
	        categoria = planDePago.getString("categoria");
	        anticipodesde = planDePago.getInt("anticipodesde");
	        aniodesde = planDePago.getInt("aniodesde");
	        anticipohasta = planDePago.getInt("anticipohasta");
	        aniohasta = planDePago.getInt("aniohasta");
	        montototal = planDePago.getDouble("montototal");
	        contado = planDePago.getBoolean("contado");
	        //titular = planDePago.getString("titular");
	        plandepagocaido = planDePago.getBoolean("plandepagocaido");
	        vigente = planDePago.getBoolean("vigente");
		porcentajeIntFin = planDePago.getDouble("porcentajeintfin");
	        montoInteresFinanciacion = planDePago.getDouble("montointeresfinanciacion");
	    }
	} catch (SQLException e) {
	    // TODO
	} catch (NullPointerException e) {
	    // TODO
	}
    }
    
    public boolean esPlanDePagoTGS(){
	boolean resultado = false;
	if ((idtipoplandepago == 1) || (idtipoplandepago == 4)){
	    resultado = true;
	}
	return resultado;
    }
    
    public boolean esPlanDePagoInmob(){
	boolean resultado = false;
	if ((idtipoplandepago == 2) || (idtipoplandepago == 5)){
	    resultado = true;
	}
	return resultado;
    }
    
    public boolean esPlanDePagoAutomotor(){
	boolean resultado = false;
	if ((idtipoplandepago == 3) || (idtipoplandepago == 6)){
	    resultado = true;
	}
	return resultado;
    }

    public void setPlandepagocaido(boolean plandepagocaido) {
	this.plandepagocaido = plandepagocaido;
    }

    public boolean isPlandepagocaido() {
	return plandepagocaido;
    }

    public void setVigente(boolean vigente) {
	this.vigente = vigente;
    }

    public boolean isVigente() {
	return vigente;
    }
    
    public int getCantCuotasAdeudadasAlCorriente(){
	return LibSQL.getInt("taxes.getCantidadCuotasAdeudadas",""+idplandepago);
    }

    public void setPorcentajeIntFin(double porcentajeIntFin) {
	this.porcentajeIntFin = porcentajeIntFin;
    }

    public double getPorcentajeIntFin() {
	return porcentajeIntFin;
    }

    public void setMontoInteresFinanciacion(double montoInteresFinanciacion) {
	this.montoInteresFinanciacion = montoInteresFinanciacion;
    }

    public double getMontoInteresFinanciacion() {
	return montoInteresFinanciacion;
    }
}
