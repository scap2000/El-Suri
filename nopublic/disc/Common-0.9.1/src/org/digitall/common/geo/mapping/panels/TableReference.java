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
 * TableReference.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class TableReference {

    private boolean backward = false;
    private String id = "";
    private String sourceScheme = "";
    private String sourceTable = "";
    private String sourceColumn = "";
    private String destScheme = "";
    private String destTable = "";
    private String destColumn = "";
    private Vector<String> columns = new Vector<String>();

    public TableReference(String _id) {
	id = _id;
    }

    public void setBackward() {
	this.backward = true;
    }

    public void setForward() {
	this.backward = false;
    }

    public boolean isBackward() {
	return backward;
    }

    public boolean isForward() {
	return !backward;
    }

    public void setDestScheme(String _destScheme) {
	this.destScheme = _destScheme;
    }

    public String getDestScheme() {
	return destScheme;
    }

    public void setDestTable(String _destTable) {
	this.destTable = _destTable;
    }

    public String getDestTable() {
	return destTable;
    }

    public void setDestColumn(String _destColumn) {
	this.destColumn = _destColumn;
    }

    public String getDestColumn() {
	return destColumn;
    }

    public String getId() {
	return id;
    }

    public String getReference() {
	return destScheme + "." + destTable + "." + destColumn;
    }

    public String getReferenceCondition() {
	return sourceScheme + "." + sourceTable + "." + sourceColumn + " = " + destScheme + "." + destTable + "." + destColumn;
    }

    public void setSourceColumn(String _sourceColumn) {
	this.sourceColumn = _sourceColumn;
    }

    public String getSourceColumn() {
	return sourceColumn;
    }

    public void setSourceScheme(String _sourceScheme) {
	this.sourceScheme = _sourceScheme;
    }

    public String getSourceScheme() {
	return sourceScheme;
    }

    public void setSourceTable(String _sourceTable) {
	this.sourceTable = _sourceTable;
    }

    public String getSourceTable() {
	return sourceTable;
    }
    
    public void loadColumns() {
	columns = LibSQL.getVector("SELECT attname /*AS columna*/ || '&' || typname /*AS tipodatos*/ || '&' || " +
	"CASE WHEN " + 
	"    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
	"    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
	"    ELSE col_description(ta.attrelid,ta.attnum) " + 
	"END " + 
	"    FROM pg_class bc, pg_attribute ta, pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0  and not ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" + destScheme + "' and relam = 0 AND bc.relname = '" + destTable + "' " + " and nspname not like 'pg_%' and ta.atttypid = ty.oid order by attnum");
    }

    public Vector<String> getColumns() {
	return columns;
    }

}
