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
 * LiquidacionSueldos.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.util.Vector;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.lib.sql.LibSQL;

public class LiquidacionSueldos {

    /* 2009-09-18 (santiago) Mensaje para el codificador
     * Un poco tosca la clase, 3 métodos y no devuelve muchos resultados
     * ¿Cuál es el objetivo de la misma?
     * */

    public LiquidacionSueldos() {
    
    }
    
    public int asignarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
	int resultado = -1;
	Vector idsHaberes = LibSQL.getVector("sueldos.getLisIdHaberes","'"+vectorToParams(_haberes)+"'");
	Vector idsDescuentos = LibSQL.getVector("sueldos.getLisIdDescuentos","'"+vectorToParams(_descuentos)+"'");
	String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(idsHaberes)+"','"+vectorToParams(idsDescuentos)+"'";
	resultado = LibSQL.getInt("sueldos.asignarconceptoslegajo",params);
	return resultado;
    }
    
    public int quitarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
        int resultado = -1;
        /*
        Vector idsHaberes = LibSQL.getVector("sueldos.getLisIdHaberes","'"+vectorToParams(_haberes)+"'");
        Vector idsDescuentos = LibSQL.getVector("sueldos.getLisIdDescuentos","'"+vectorToParams(_descuentos)+"'");
        */
        String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(_haberes)+"','"+vectorToParams(_descuentos)+"'";
        resultado = LibSQL.getInt("sueldos.quitarConceptosLegajo",params);
        return resultado;
    }
    
    public int agregarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
        int resultado = -1;
        String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(_haberes)+"','"+vectorToParams(_descuentos)+"'";
        resultado = LibSQL.getInt("sueldos.agregarconceptoslegajo",params);
        return resultado;
    }
    
    /* 2009-09-18 (santiago) Mensaje para el codificador
     * Al final del ciclo, queda siempre una coma suelta
     * */
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	/* 2009-09-18 (santiago) Mensaje para el codificador
	 * return es una palabra reservada y no necesita parámetros,
	 * por lo tanto el resultado no debe estar (o no es necesario que esté) entre paréntesis
	 * */
	return resultado;
    }
    
    public boolean grabarLiquidacionMensualForLegajo(Legajo _legajo, String _fecha){
	boolean resultado = false;
	int idLegajo = _legajo.getidLegajo();
	int idLiquidacionParcial = LibSQL.getInt("sueldos.getIdLiquidacionParcial",idLegajo + ",'" +_fecha+"'");
	String params = ""+idLiquidacionParcial + ",'"+_fecha+"'";
	resultado = LibSQL.getBoolean("sueldos.pasarALiquidacionMensual",params);
	return resultado;
    }
    
    public boolean puedeHacerseLiquidacionMensual(String _fecha){
	if (LibSQL.getBoolean("sueldos.sepuedehacerliquidacion","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public boolean estanTodasLiquidacionesParciales(String _fecha){
	if (LibSQL.getBoolean("sueldos.estanrealizadastodaslasliquidaciones","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public boolean grabarLiquidacionesFinales(String _fecha){
	if (LibSQL.getBoolean("sueldos.grabarLiquidacionFinal","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
}
