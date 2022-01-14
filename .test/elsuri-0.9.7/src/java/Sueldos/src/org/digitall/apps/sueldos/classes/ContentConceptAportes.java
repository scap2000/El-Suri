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
 * ContentConceptAportes.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class ContentConceptAportes {

    /* 2009-09-11 (santiago) Mensaje para el codificador
     * tipoConcepto debería estar en una clase Abstracta Estática de configuración
     * Ejemplo: org.digitall.common.cashflow.classes.EntityTypes
	    //PARTIDA PRESUPUESTARIA
	    public static final int BUDGET = 3;
	    //DINERO
	    public static final int MONEY = 4;
	    //CENTRO DE COSTOS
	    public static final int COSTS_CENTRE = 5
     * Y debería ser una clase única que administre todos los ContentConcept (que no se entiende qué son)
     * porque todas son iguales, sólo cambia la Clase y el valor de "tipoConcepto"
     * En este caso, también podría hacerse un vector de ConceptosAportes de la siguiente manera
     * Vector<ConceptosAportes> conceptos = new Vector<ConceptosAportes>();
     * para que el método addConcepto(Object _concepto) cambie a addConcepto(ConceptosAportes _concepto)
     * */
    private Vector conceptos = new Vector();
    private int tipoConcepto = 8;

    public ContentConceptAportes() {
	/* 2009-09-11 (santiago) Mensaje para el codificador
	 * No hace falta, porque el objeto acaba de instanciarse vacío
	 * */
	conceptos.removeAllElements();
    }
    
    public void addConcepto(Object _concepto){
	conceptos.add(_concepto);
    }
    
    public boolean equals(ContentConceptAportes _contentConcept){
	boolean hayDistinto = false;
	int i = 0;
	Vector conceptosCompare = _contentConcept.getConceptos();
	if(conceptos.size() != conceptosCompare.size()){
	    hayDistinto = true;
	}else{
	    while((i < conceptos.size())&&(!hayDistinto)){

	        /* 2009-09-11 (santiago) Mensaje para el codificador
	         * No hace falta instanciar, se puede llamar de esta manera:
		 * if (!equals((ConceptosAportes)(conceptos.elementAt(i)), (ConceptosAportes)(conceptosCompare.elementAt(i))) {}
		 * Si no se usan nunca más las líneas "System.out.println", borrarlas
		 * Se ha visto propagado este error en el proyecto Sueldos.jpr
	         * */

	        ConceptosAportes conceptoAporte = (ConceptosAportes)(conceptos.elementAt(i));
	        ConceptosAportes conceptoAporteA = (ConceptosAportes)(conceptosCompare.elementAt(i));
		if(!equals(conceptoAporte,conceptoAporteA)){
		    hayDistinto = true;    
		}
		i++;
	    }
	}
	return(!hayDistinto);
    }
    
    /* 2009-09-11 (santiago) Mensaje para el codificador
     * Supuestamente, el campo "idconceptoreferencia" es Clave Primaria en la base de datos
     * por lo tanto, para saber si _conceptA es igual a _conceptB, ¿No bastaría con comparar dicho campo?
     * */    
    private boolean equals(ConceptosAportes _conceptA, ConceptosAportes _conceptB){
	boolean testId = (_conceptA.getIdconceptoreferencia() == _conceptB.getIdconceptoreferencia());
	boolean testName = (_conceptA.getName().equals(_conceptB.getName()));
	boolean testValue = (_conceptA.getValue() == _conceptB.getValue());
	boolean testPorcentaje = (_conceptA.getPercentage() == _conceptB.getPercentage());
	boolean testDebeHaber  = (_conceptA.getDebehaber() == _conceptB.getDebehaber());
	boolean testEstado = (_conceptA.getEstado().equals(_conceptB.getEstado()));
	boolean testOrden = (_conceptA.getOrden() == _conceptB.getOrden());
	return ((testId) && (testName) && (testValue) && (testPorcentaje) && (testDebeHaber) && (testEstado) && (testOrden));
    }
    
    public boolean save(){
	/* 2009-09-11 (santiago) Mensaje para el codificador
	 * Propuesta diferente para el método, para evitar seguir grabando en caso de un error
	 * También se puede preguntar para continuar y/o mostrar el error específico
	 boolean resultado = true;
	 int i = 0;
	 while (i < conceptos.size() && resultado){
	     ConceptosAportes conceptoAporte = (ConceptosAportes)(conceptos.elementAt(i));
	     if(conceptoAporte.isSetgeneral()){
	         resultado = (conceptoAporte.saveData() != -1);
		 if (resultado) {
		    saveValuesToLegajo(conceptoAporte.getIdconceptoreferencia(), conceptoAporte.getValue());
		 } else {
		    //Mostrar Mensaje de error y posibilidad de continuar, cancelar o revertir la situación
		 }
	     }
	     i++;
	 }
	 return(resultado);
	 * */    
	 boolean resultado = true;
	 for(int i = 0; i < conceptos.size(); i++){
	     ConceptosAportes conceptoAporte = (ConceptosAportes)(conceptos.elementAt(i));
	     if(conceptoAporte.isSetgeneral()){
	         resultado = (conceptoAporte.saveData() != -1);
	         saveValuesToLegajo(conceptoAporte.getIdconceptoreferencia(), conceptoAporte.getValue());
	     }
	 }
	 return(resultado);
    }
	    
    private boolean saveValuesToLegajo(int _idConceptoReference, double _value){
	String params = "" + tipoConcepto + "," + _idConceptoReference;
	int idConcepto = LibSQL.getInt("sueldos.getIdConceptFromReference",params);
	params = ""+ idConcepto + "," + _value;
	/* 2009-09-11 (santiago) Mensaje para el codificador
	 * Se podría hacer directamente un return LibSQL.getBoolean("sueldos.updateValuesConcept",params);
	 * para evitar la instanciación de un objeto
	 * */
	/*
	 * 2009-09-17 (santiago) Mensaje para el codificador
	 * ¿Es necesario llamar a ambos stored procedures?
	 * upDate está mal escrito, es "update"
	 * */
	boolean resultado =  LibSQL.getBoolean("sueldos.upDateValuesConcept",params);
	return (resultado);
    }
    
    public Vector getConceptos(){
	return(conceptos);
    }
    
    public void clear(){
	conceptos.removeAllElements();
    }
}
