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
 * Servicio.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.cerronegro.avl.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Servicio {

    private int idServicio = -1;
    private int idBus = -1;
    private int idGrupo = -1;
    private int idEmpresa = -1;
    private int idEquipo = -1;
    private int idLinea = -1;
    private int idRuta = -1;
    private int legajoChofer = -1;
    private String fechaHoraApertura = "";
    private String fechaHoraCierre = "";
    private long numeroServicio = 0;
    private int boletos = 0;
    private double recaudacion = 0.0;
    private int inspecciones = 0;
    private Bus bus;

    public Servicio() {
	this(-1);
    }
    
    public Servicio(int _idServicio) {
	super();
	idServicio = _idServicio;
	bus = new Bus();
    }

    public int getIdServicio() {
	return idServicio;
    }


    /* NO IMPLEMENTADO
    public int saveData(){
	int _returns = -1;
	_returns = LibSQL.getInt("reportes.setServicio", idServicio);
	if (idServicio == -1){
	    idServicio = _returns;
	}
	return _returns;
    }
    */

    public void retrieveBus() {
	bus =new Bus();
	if (idBus != -1) {
	    bus.loadData(idBus);
	} else {
	    bus.loadData(LibSQL.getInt("reportes.getbusporservicio", idServicio));
	}
    }

    public Bus getBus() {
	return bus;
    }

    public void retrieveData() {
	if (idServicio != -1) {
	    ResultSet result = LibSQL.exFunction("reportes.getServicio", idServicio);
	    try {
	        if (result.next()) {
	            idBus = result.getInt("idbus");
	            idGrupo = result.getInt("idgrupo");
	            idEmpresa = result.getInt("idempresa");
	            idEquipo = result.getInt("idequipo");
	            idLinea = result.getInt("idlinea");
	            idRuta = result.getInt("idruta");
	            legajoChofer = result.getInt("legajochofer");
	            fechaHoraApertura = result.getString("fechahora_apertura");
	            fechaHoraCierre = result.getString("fechahora_cierre");
	            numeroServicio = result.getLong("numeroservicio");
	            boletos = result.getInt("boletos");
	            recaudacion = result.getDouble("recaudacion");
	            inspecciones = result.getInt("inspecciones");
		    retrieveBus();
	        }
	    } catch (SQLException e) {
	        // TODO
	        e.printStackTrace();
	    } catch (NullPointerException e) {
	        // TODO
	    }
	}
    }

}
