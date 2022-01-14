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
 * ProcedureClass.java
 *
 * */
package org.digitall.apps.jpgadmin.sqleditor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;

public class ProcedureClass {

    private int id = -1;
    private String schemeName = "";
    private String procedureName = "";
    private String returns = "";
    private String content = "";
    private String originalContent = "";
    private String[] argNames;
    private String[] argTypes;
    private String header = "";
    private String tail = "";
    private String comment = "";

    private void retrieveData() {
	ResultSet src = SQLExecutor.exQuery("SELECT proname, nspname, prosrc as source, "
	+ " pg_catalog.format_type(prorettype, null) as returns, array_to_string(proargnames, ', ') as args, "
	+ " pg_catalog.oidvectortypes(proargtypes) as argtypes, "
	+ " CASE WHEN pg_catalog.obj_description(pg_proc.oid, 'pg_proc') IS NULL THEN '' " +
				"ELSE pg_catalog.obj_description(pg_proc.oid, 'pg_proc') END AS procomment "
	+ " FROM pg_proc, pg_namespace WHERE pronamespace = pg_namespace.oid and pg_proc.oid = " + id, false);
	try {
	    if (src.next()) {
		schemeName = src.getString("nspname");
		procedureName = src.getString("proname");
		returns = src.getString("returns");
		String _argNames = src.getString("args");
	        String _argTypes = src.getString("argtypes");
		if (_argNames != null) {
		    argNames = _argNames.split(", ");
		    argTypes = _argTypes.split(", ");
		}
		originalContent = src.getString("source").trim();
		content = originalContent;
	        comment = src.getString("procomment");
	    }
	} catch (SQLException x) {
	    //x.printStackTrace();
	    Advisor.messageBox(x.getMessage(), "Error");
	}
	header = "CREATE OR REPLACE FUNCTION " + schemeName + "." + procedureName + "(";
	int i = 0;
	if (argNames != null) {
	    while (i < argNames.length) {
		header += argNames[i] + " " + argTypes[i];
		i++;
		if (i < argNames.length) {
		    header += ", ";
		}
	    }
	}
	header += ") RETURNS " + returns + " AS $BODY$\n";
	tail = "$BODY$\n    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
    }

    public void setID(int _id) {
	id = _id;
	retrieveData();
    }

    public int getID() {
	return id;
    }

    public void setContent(String _content) {
	content = _content;
    }

    public void cancelEdition() {
	content = originalContent;
    }

    public String getContent() {
	return content;
    }

    public String getHeader() {
	return header;
    }

    public String getTail() {
	return tail;
    }

    public String getProcedureString() {
	return header + content + tail;
    }

    public String getReturns() {
	return returns;
    }

    public String getSchemeName() {
	return schemeName;
    }

    public String getProcedureName() {
	return procedureName;
    }

    public String[] getArgNames() {
	return argNames;
    }

    public String[] getArgTypes() {
	return argTypes;
    }

    public String getDeclaration() {
	String declaration = schemeName + "." + procedureName + "(";
	if (argNames != null) {
	    for (int i = 0; i < argNames.length - 1; i++) {
		declaration += argNames[i] + " " + argTypes[i] + ", ";
	    }
	    declaration += argNames[argNames.length - 1] + " " + argTypes[argNames.length - 1];
	}
	declaration += ")";
	return declaration;
    }

    public String getComment() {
	return comment;
    }
    public String getCommentDeclaration() {
	return "COMMENT ON FUNCTION " + getDeclaration() + " IS '" + comment + "';\n";
    }
}
