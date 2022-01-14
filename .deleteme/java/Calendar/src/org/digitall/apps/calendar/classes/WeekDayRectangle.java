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
 * WeekDayRectangle.java
 *
 * */
package org.digitall.apps.calendar.classes;

import java.util.Calendar;

import org.digitall.lib.geo.esri.ESRIPolygon;

public class WeekDayRectangle {

    private ESRIPolygon polygon;
    private int dayOfWeek = -1;
    private int month = -1;
    private boolean toweek = false;
    private String dayString = "";
    private String dayShortString = "";

    public WeekDayRectangle(ESRIPolygon _polygon, int _dayOfWeek) {
	dayOfWeek = _dayOfWeek;
	switch (dayOfWeek) {
	    case Calendar.SUNDAY :
		dayString = "Domingo";
		dayShortString = "Dom";
		break;
	    case Calendar.MONDAY :
		dayString = "Lunes";
		dayShortString = "Lun";
		break;
	    case Calendar.TUESDAY :
		dayString = "Martes";
		dayShortString = "Mar";
		break;
	    case Calendar.WEDNESDAY :
		dayString = "Miércoles";
		dayShortString = "Mié";
		break;
	    case Calendar.THURSDAY :
		dayString = "Jueges";
		dayShortString = "Jue";
		break;
	    case Calendar.FRIDAY :
		dayString = "Viernes";
		dayShortString = "Vie";
		break;
	    case Calendar.SATURDAY :
		dayString = "Sábado";
		dayShortString = "Sáb";
		break;
	}
	polygon = _polygon;
    }

    public int getDayOfWeek() {
	return dayOfWeek;
    }

    public ESRIPolygon getPolygon() {
	return polygon;
    }

    public void setPolygon(ESRIPolygon polygon) {
	this.polygon = polygon;
    }

    public void setMonth(int month) {
	this.month = month;
    }

    public int getMonth() {
	return month;
    }

    public void setToweek() {
	toweek = true;
    }

    public boolean isToweek() {
	return toweek;
    }

    public String getDayString() {
	return dayString;
    }

    public String getDayShortString() {
	return dayShortString;
    }

}
