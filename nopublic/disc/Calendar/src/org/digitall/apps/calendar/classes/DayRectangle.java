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
 * DayRectangle.java
 *
 * */
package org.digitall.apps.calendar.classes;

import java.awt.event.MouseEvent;

import org.digitall.apps.calendar.interfaces.NewsMgmtOld;
import org.digitall.lib.geo.esri.ESRIPolygon;

public class DayRectangle {

    private int dayOfMonth;
    private ESRIPolygon polygon;
    private int dayOfWeek = -1;
    private int weekOfYear = -1;
    private int tomonth = -1;
    private int toyear = -1;
    private boolean holiday = false;
    private String ephemerides = "";
    private boolean today = false;

    public DayRectangle(ESRIPolygon _polygon, int _dayOfMonth) {
	dayOfMonth = _dayOfMonth;
	polygon = _polygon;
    }

    public int getDay() {
	return dayOfMonth;
    }

    public ESRIPolygon getPolygon() {
	return polygon;
    }

    public void setDayOfMonth(int dayOfMonth) {
	this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfMonth() {
	return dayOfMonth;
    }

    public void setPolygon(ESRIPolygon polygon) {
	this.polygon = polygon;
    }

    public void setDayOfWeek(int dayOfWeek) {
	this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfWeek() {
	return dayOfWeek;
    }

    public void setWeekOfYear(int weekOfYear) {
	this.weekOfYear = weekOfYear;
    }

    public int getWeekOfYear() {
	return weekOfYear;
    }

    public void setTomonth(int month) {
	this.tomonth = month;
    }

    public int getTomonth() {
	return tomonth;
    }

    public void setHoliday() {
	holiday = true;
    }

    public void setToday() {
	today = true;
    }

    public boolean isHoliday() {
	return holiday;
    }

    public boolean isToday() {
	return today;
    }

    public void setEphemerides(String ephemerides) {
	this.ephemerides = ephemerides;
    }

    public String getEphemerides() {
	return ephemerides;
    }

    public void fireClick(MouseEvent me) {
	/*if (me.getButton() == me.BUTTON1) {
	    System.out.println("hiciste click en el dÃ­a: " + dayOfMonth + "/" + (tomonth+1) + "/" + toyear);
	} else if (me.getButton() == me.BUTTON2) {
	    System.out.println("Ese botÃ³n no hace nada: " + dayOfMonth + "/" + (tomonth+1) + "/" + toyear);
	} else if (me.getButton() == me.BUTTON3) {
	    System.out.println("Â¿QuerÃ©s mÃ¡s info del dÃ­a: " + dayOfMonth + "/" + (tomonth+1) + "/" + toyear + "?");
	    NewsMgmtOld news = new NewsMgmtOld();
	    news.setModal(true);
	    news.show();
	}*/
    }

    public void setToyear(int _toyear) {
	toyear = _toyear;
    }

    public int getToyear() {
	return toyear;
    }

}
