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
 * GaiaCatastroInputPanel.java
 *
 * */
package org.digitall.apps.gaia.components;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaCatastroInputPanel extends TFInput {

    private ESRIPoint point = null;

    public GaiaCatastroInputPanel() {
	super(DataTypes.INTEGER,"Cadastral", false);
	setEditable(false);
	setValue(0);
	getTextField().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
		    setValue(0);
		} else if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && point != null) {
		    fetchCatastro(point, true);
		}
	    }
	});
    }

    public void fetchCatastro(ESRIPoint _point) {
	fetchCatastro(_point, _point.getIdPoint() == -1);
    }

    public void fetchCatastro(ESRIPoint _point, boolean _force) {
	point = _point;
	if (_force) {
	    Vector catastros = LibSQL.getVector(GaiaEnvironment.getScheme() + ".getCatastrosAtPoint", point.getX() + "," + point.getY());
	    if (catastros.size() > 0) {
		catastros.insertElementAt("0",0);
		String _catastro = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione 0 para omitir", "Seleccione el Nº de catastro", JOptionPane.QUESTION_MESSAGE, null, catastros.toArray(), String.valueOf(getValue()));
		if (_catastro != null) {
		    setValue(_catastro);
		}
	    } else {
		setValue(0);
	    }
	}
    }
    
}
