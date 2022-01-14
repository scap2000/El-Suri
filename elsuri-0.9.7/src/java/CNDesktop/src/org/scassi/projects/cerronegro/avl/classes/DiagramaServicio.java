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
 * DiagramaServicio.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.cerronegro.avl.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Vector;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class DiagramaServicio {

    private int idDiagramaServicio = -1;
    private FranjaHoraria franjaHoraria;
    private int idBus = -1;
    private String fechaHoraSalida = "";
    private String fechaHoraLlegada = "";
    private String fechaHoraDisponible = "";
    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private Vector<Etapa> etapas = new Vector<Etapa>();

    public DiagramaServicio(FranjaHoraria _franjaHoraria, int _idDiagramaServicio) {
	franjaHoraria = _franjaHoraria;
	idDiagramaServicio = _idDiagramaServicio;
    }

    public void setIdDiagramaServicio(int idDiagramaServicio) {
	this.idDiagramaServicio = idDiagramaServicio;
    }

    public int getIdDiagramaServicio() {
	return idDiagramaServicio;
    }

    public void setIdBus(int idBus) {
	this.idBus = idBus;
    }

    public int getIdBus() {
	return idBus;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
	this.fechaHoraSalida = fechaHoraSalida;
    }

    public void calcularFechaHoraLlegada(int _minutos) {
	Calendar _calendar = Calendar.getInstance();
	try {
	    _calendar.setTime(dateTimeFormatter.parse(getFechaHoraSalida()));
	    _calendar.add(Calendar.MINUTE, _minutos);
	    setFechaHoraLlegada(dateTimeFormatter.format(_calendar.getTime()));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    public void calcularFechaHoraLlegada() {
	int _minutos = 0;
	Calendar _calendar = Calendar.getInstance();
	try {
	    _calendar.setTime(dateTimeFormatter.parse(getFechaHoraSalida()));
	    for (int i= 0; i < etapas.size(); i++) {
	        _calendar.add(Calendar.MINUTE, etapas.elementAt(i).getDuracion());
		etapas.elementAt(i).setFechaHoraLlegadaDiagramada(dateTimeFormatter.format(_calendar.getTime()));
	    }
	    setFechaHoraLlegada(dateTimeFormatter.format(_calendar.getTime()));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    public void calcularFechaHoraDisponible(int _minutos) {
	Calendar _calendar = Calendar.getInstance();
	try {
	    _calendar.setTime(dateTimeFormatter.parse(getFechaHoraLlegada()));
	    _calendar.add(Calendar.MINUTE, _minutos);
	    setFechaHoraDisponible(dateTimeFormatter.format(_calendar.getTime()));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    public String getFechaHoraSalida() {
	return fechaHoraSalida;
    }

    public void setFechaHoraLlegada(String fechaHoraLlegada) {
	this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public String getFechaHoraLlegada() {
	return fechaHoraLlegada;
    }

    public void setFechaHoraDisponible(String fechaHoraDisponible) {
	this.fechaHoraDisponible = fechaHoraDisponible;
    }

    public String getFechaHoraDisponible() {
	return fechaHoraDisponible;
    }
    
    public Etapa getEtapa(int _index) {
	return etapas.elementAt(_index);
    }
    
    public Vector<Etapa> getEtapas() {
	return etapas;
    }

    public void addEtapa(Etapa _etapa) {
	etapas.add(_etapa);
    }

    public int saveData(){
	if (idBus > 0) {
	    String _etapasString = "";
	    for (Etapa _etapa : etapas) {
		_etapasString += "'" + _etapa.getFechaHoraLlegadaDiagramada() + "'::timestamp without time zone,";
	    }
	    _etapasString = "ARRAY[ " + _etapasString.substring(0, _etapasString.length()-1) + " ]"; //saco la última coma
	    int result = LibSQL.getInt("reportes.setDiagramaServicio", idDiagramaServicio + "," + franjaHoraria.getIdFranjaHoraria() + "," + idBus + ",'" + fechaHoraSalida + "','" + fechaHoraLlegada+ "','" + fechaHoraDisponible+ "'," + _etapasString + "");
	    if (idDiagramaServicio == -1){
		idDiagramaServicio = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("El servicio debe tener un bus asignado", "Servicio no válido");
	    return -1;
	}
    }

    public FranjaHoraria getFranjaHoraria() {
	return franjaHoraria;
    }
}
