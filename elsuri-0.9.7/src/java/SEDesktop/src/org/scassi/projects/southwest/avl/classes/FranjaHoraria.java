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
 * FranjaHoraria.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.southwest.avl.classes;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class FranjaHoraria {

    private int idFranjaHoraria = -1;
    private Diagramacion diagramacion;
    private int numero = -1;
    private String fechaHoraApertura = "";
    private int duracion = 0;
    private int buses = 0;
    private int frecuenciaMinima = 0;
    private Ruta ruta;
    private int descanso = 0;
    private int imprevistos = 0;

    public FranjaHoraria(Diagramacion _diagramacion, int _numero) {
	diagramacion = _diagramacion;
	numero = _numero;
    }

    public FranjaHoraria(Diagramacion _diagramacion, int _numero, int _idruta) {
	diagramacion = _diagramacion;
	numero = _numero;
	ruta = new Ruta(this, _idruta);
    }

    public int getNumero() {
	return numero;
    }

    public void setFechaHoraApertura(String fechaHoraApertura) {
	this.fechaHoraApertura = fechaHoraApertura;
    }

    public String getFechaHoraApertura() {
	return fechaHoraApertura;
    }

    public void setDuracion(int duracion) {
	this.duracion = duracion;
    }

    public int getDuracion() {
	return duracion;
    }

    public void setBuses(int buses) {
	this.buses = buses;
    }

    public int getBuses() {
	return buses;
    }

    public void setFrecuenciaMinima(int frecuenciaMinima) {
	this.frecuenciaMinima = frecuenciaMinima;
    }

    public int getFrecuenciaMinima() {
	return frecuenciaMinima;
    }

    public void setDescanso(int descanso) {
	this.descanso = descanso;
    }

    public int getDescanso() {
	return descanso;
    }

    public void setImprevistos(int imprevistos) {
	this.imprevistos = imprevistos;
    }

    public int getImprevistos() {
	return imprevistos;
    }
    
    public int getMinBuses() {
	return ((ruta.getDuracion() + descanso + imprevistos)/frecuenciaMinima)+1;
    }

    public void setRuta(Ruta ruta) {
	this.ruta = ruta;
    }

    public Ruta getRuta() {
	return ruta;
    }
    
    public void prepararDatosRuta() {
	ruta.loadData();
    }

    public void setNumero(int _numero) {
	numero = _numero;
    }
    
    public int saveData(){
	if (diagramacion.getIdDiagramacion() > 0){
	    int result = LibSQL.getInt("reportes.setFranjaHoraria", idFranjaHoraria + "," + diagramacion.getIdDiagramacion() + "," + numero + ",'" + fechaHoraApertura + "'," + duracion + "," + buses + "," + frecuenciaMinima + "," + ruta.getIdRuta() + "," + descanso);
	    if (idFranjaHoraria == -1){
		idFranjaHoraria = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("La franja horaria debe tener un ID de diagramación asignado", "Franja horaria no válida");
	    return -1;
	}
    }

    public int getIdFranjaHoraria() {
	return idFranjaHoraria;
    }

    public void delete() {
	boolean result = LibSQL.getBoolean("reportes.delFranjaHoraria", idFranjaHoraria);
	if (result) idFranjaHoraria = -1;
    }

    public void setRutaByID(int _idRuta) {
	setRuta(new Ruta(this, _idRuta));
    }
}
