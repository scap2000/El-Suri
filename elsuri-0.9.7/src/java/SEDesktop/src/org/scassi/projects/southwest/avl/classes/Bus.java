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
 * Bus.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */

package org.scassi.projects.southwest.avl.classes;

import java.sql.ResultSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class Bus {

    private int idBus = -1;
    private int idGrupo = -1;
    private int idEmpresa = -1;
    private String marca = "";
    private int idTipoBus = 0;
    private String fechaFabricacion = "";
    private String placa = "";
    private int idEstadoBus = 0;
    private String fechaAlta = "";
    private String fechaBaja = "";
    private int interno = -1;
    private Vector<DiagramaServicio> serviciosAsignados = new Vector<DiagramaServicio>();
    
    
    public Bus() {
    }
    
    public Bus(int _idBus) {
	idBus = _idBus;
    }
    
    public Bus(int _idBus, int _idGrupo, int _idEmpresa, String _marca, int _idTipoBus, String _fechaFabricacion, String _placa, int _idEstadoBus, String _fechaAlta, String _fechaBaja, int _interno) {
	idBus = _idBus;
	idGrupo = _idGrupo;
	idEmpresa = _idEmpresa;
	marca = _marca;
	idTipoBus = _idTipoBus;
	fechaFabricacion = _fechaFabricacion;
	placa = _placa;
	idEstadoBus = _idEstadoBus;
	fechaAlta = _fechaAlta;
	fechaBaja = _fechaBaja;
	interno = _interno;
    }

    public void setIdBus(int _idBus) {
	idBus = _idBus;
    }

    public Bus(int _idBus, int _interno) {
	idBus = _idBus;
	interno = _interno;
    }

    public int getIdBus() {
	return idBus;
    }

    public void loadData(int _idBus){
	idBus = _idBus;
	ResultSet data = LibSQL.exFunction("reportes.getBus", idBus);
	try {
	    if (data.next()) {
	        idGrupo = data.getInt("idgrupo");
	        idEmpresa = data.getInt("idempresa");
	        marca = data.getString("marca");
	        idTipoBus = data.getInt("idtipobus");
	        fechaFabricacion = data.getString("fechafabricacion");
	        placa = data.getString("placa");
	        idEstadoBus = data.getInt("idestadobus");
	        fechaAlta = data.getString("fechaalta");
	        fechaBaja = data.getString("fechabaja");
	        interno = data.getInt("interno");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    /* NO IMPLEMENTADO
    public int saveData(){
	if (!placa.trim().equals("")){
	    int result = LibSQL.getInt("reportes.setBus", idBus + "," + idGrupo + "," + idEmpresa + ",'" + marca + "'," + idTipoBus + ",'" + fechaFabricacion + "','" + placa + "'," + idEstadoBus + ",'" + fechaAlta + "','" + fechaBaja + "'," + interno);
	    if (idBus == -1){
		idBus = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar la placa para guardar los datos del bus", "Bus no válido");
	    return -1;
	}
    }
    */
    
    public void setPlaca(String _placa) {
	placa = _placa;
    }

    public String getPlaca() {
	return placa;
    }

    public void setMarca(String _marca) {
	marca = _marca;
    }

    public String getMarca() {
	return marca;
    }

    public void setIdTipoBus(int _idTipoBus) {
	idTipoBus = _idTipoBus;
    }

    public int getIdTipoBus() {
	return idTipoBus;
    }

    public void setIdGrupo(int _idGrupo) {
	idGrupo = _idGrupo;
    }

    public int getIdGrupo() {
	return idGrupo;
    }

    public void setIdEmpresa(int _idEmpresa) {
	idEmpresa = _idEmpresa;
    }

    public int getIdEmpresa() {
	return idEmpresa;
    }

    public void setFechaFabricacion(String _fechaFabricacion) {
	fechaFabricacion = _fechaFabricacion;
    }

    public String getFechaFabricacion() {
	return fechaFabricacion;
    }

    public void setIdEstadoBus(int _idEstadoBus) {
	idEstadoBus = _idEstadoBus;
    }

    public int getIdEstadoBus() {
	return idEstadoBus;
    }

    public void setFechaAlta(String _fechaAlta) {
	fechaAlta = _fechaAlta;
    }

    public String getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaBaja(String _fechaBaja) {
	fechaBaja = _fechaBaja;
    }

    public String getFechaBaja() {
	return fechaBaja;
    }

    public void setInterno(int _interno) {
	interno = _interno;
    }

    public int getInterno() {
	return interno;
    }
    
    public boolean asignarServicio(DiagramaServicio _servicio) {
	int i = 0;
	boolean _overlap = false;
	boolean _added = false;
	try {
	    while (i < serviciosAsignados.size() && !_overlap) {
		SimpleDateFormat rangeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		_overlap = rangeFormatter.parse(_servicio.getFechaHoraSalida()).before(rangeFormatter.parse(serviciosAsignados.elementAt(i).getFechaHoraDisponible()));
		i++;
	    }
	    if (!_overlap) {
		_added = serviciosAsignados.add(_servicio);
		_servicio.setIdBus(idBus);
	    }
	} catch (ParseException x) {
	    x.printStackTrace();
	}
	return _added;
    }

    public void desasignarTodo() {
	serviciosAsignados.removeAllElements();
    }
    
    public boolean disponiblePara() {
	return true;
    }
    
}
