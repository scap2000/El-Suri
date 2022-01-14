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
 * DBRole.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;

import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;

public class DBRole extends BasicCheckableListItem {

    private int privilege = -1;
    private Color background = Color.WHITE;
    private Color foreGround = Color.GRAY;
    private String text;

    public DBRole(String _text) {
	super(-1, _text);
	setText(_text);
    }

    public Color getBackground() {
	return background;
    }

    public void setBackground(Color _color) {
	background = _color;
    }

    public Color getForeground() {
	return foreGround;
    }

    public void setForeground(Color _color) {
	foreGround = _color;
    }

    public int getPrivilege() {
	return privilege;
    }

    public void setPrivilege(int _privilege) {
	privilege = _privilege;
	switch (privilege) {
	    case SystemConfiguration.ADMIN_PRIV :
		setBackground(Color.RED);
		setForeground(Color.BLACK);
		break;
	    case SystemConfiguration.USER_PRIV :
		setBackground(Color.YELLOW);
		setForeground(Color.BLACK);
		break;
	    case SystemConfiguration.QUERY_PRIV :
	        setBackground(Color.GREEN);
	        setForeground(Color.BLACK);
	        break;
	    case SystemConfiguration.EXEC_PRIV :
	        setBackground(Color.GREEN);
	        setForeground(Color.BLACK);
	        break;
	    default :
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		break;
	}
    }

    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void incPrivilege() {
	if (privilege >= 2) {
	    setPrivilege(-1);
	} else {
	    setPrivilege(privilege + 1);
	}
    }

}
