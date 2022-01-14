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
 * MonthRectangle.java
 *
 * */
package org.digitall.apps.calendar.classes;

import java.util.Calendar;

import org.digitall.lib.geo.esri.ESRIPolygon;

public class MonthRectangle {

    private int monthOfYear;
    private int toyear;
    private ESRIPolygon polygon;
    private boolean tomonth = false;
    private String monthString = "";
    private String monthShortString = "";

    public MonthRectangle(ESRIPolygon _polygon, int _monthOfYear) {
	monthOfYear = _monthOfYear;
	switch (monthOfYear) {
	    case Calendar.JANUARY :
	        monthString = "Enero";
	        monthShortString = "Ene";
	        break;
	    case Calendar.FEBRUARY :
	        monthString = "Febrero";
	        monthShortString = "Feb";
	        break;
	    case Calendar.MARCH :
	        monthString = "Marzo";
	        monthShortString = "Mar";
	        break;
	    case Calendar.APRIL :
	        monthString = "Abril";
	        monthShortString = "Abr";
	        break;
	    case Calendar.MAY :
	        monthString = "Mayo";
	        monthShortString = "May";
	        break;
	    case Calendar.JUNE :
	        monthString = "Junio";
	        monthShortString = "Jun";
	        break;
	    case Calendar.JULY :
	        monthString = "Julio";
	        monthShortString = "Jul";
	        break;
	    case Calendar.AUGUST :
	        monthString = "Agosto";
	        monthShortString = "Ago";
	        break;
	    case Calendar.SEPTEMBER :
	        monthString = "Septiembre";
	        monthShortString = "Sep";
	        break;
	    case Calendar.OCTOBER :
	        monthString = "Octubre";
	        monthShortString = "Oct";
	        break;
	    case Calendar.NOVEMBER :
	        monthString = "Noviembre";
	        monthShortString = "Nov";
	        break;
	    case Calendar.DECEMBER :
	        monthString = "Diciembre";
	        monthShortString = "Dic";
	        break;
	}
	polygon = _polygon;
    }

    public int getMonth() {
	return monthOfYear;
    }

    public ESRIPolygon getPolygon() {
	return polygon;
    }

    public int getMonthOfYear() {
	return monthOfYear;
    }

    public void setPolygon(ESRIPolygon polygon) {
	this.polygon = polygon;
    }
    public void setTomonth() {
	tomonth = true;
    }

    public boolean isTomonth() {
	return tomonth;
    }

    public String getMonthString() {
	return monthString;
    }

    public String getMonthShortString() {
	return monthShortString;
    }

    public void setToyear(int _toyear) {
	toyear = _toyear;
    }

    public int getToyear() {
	return toyear;
    }

}
