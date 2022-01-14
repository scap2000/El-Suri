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
 * JCalendario.java
 *
 * */
package org.digitall.lib.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BotonItemListener;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;

public final class JCalendario extends BasicContainerPanel {

    private int days[][];
    private BasicPanel daysPanel;
    private BasicPanel datePanel;
    private BasicPanel currentDatePanel;
    private String daySelected;
    private String monthSelected;
    private String yearSelected;
    private BasicPanel centrePanel;
    public BasicCombo yearCombo;
    public BasicCombo monthCombo;
    private Calendar calendar;
    protected BotonItemListener botonItemListener;
    private int showForYear;
    private int showForMonth;
    protected ComboItemListener comboBoxListener;
    private boolean showCalendarForDateSelected;
    private boolean showCurrentDate;
    private int firstDay;
    private BasicLabel currentDateLabel;
    private int startYear;
    private int endYear;

    public JCalendario() {
	days = new int[7][6];
	startYear = 1901;
	endYear = 2099;
	firstDay = 0;
	showCurrentDate = false;
	initializeVariables();
    }

    public JCalendario(int firstDay, boolean showCurrentDate) {
	days = new int[7][6];
	startYear = 1901;
	endYear = 2099;
	this.firstDay = firstDay;
	this.showCurrentDate = showCurrentDate;
	initializeVariables();
    }

    public JCalendario(int firstDay, boolean showCurrentDate, int startYear, int endYear) {
	days = new int[7][6];
	this.startYear = startYear;
	this.endYear = endYear;
	this.firstDay = firstDay;
	this.showCurrentDate = showCurrentDate;
	initializeVariables();
    }

    private void initializeVariables() {
	//botonItemListener = new BotonItemListener(this);
	//comboBoxListener = new ComboItemListener(this);
	calendar = Calendar.getInstance();
	showForYear = calendar.get(1);
	showForMonth = calendar.get(2);
	daySelected = (new Integer(calendar.get(5))).toString();
	monthSelected = (new Integer(calendar.get(2) + 1)).toString();
	yearSelected = (new Integer(calendar.get(1))).toString();
	datePanel = new BasicPanel();
	datePanel.setLayout(new BorderLayout());
	yearCombo = createYearCombo();
	yearCombo.setFont(yearCombo.getFont().deriveFont(1, 11F));
	monthCombo = createMonthCombo();
	monthCombo.setFont(monthCombo.getFont().deriveFont(1, 11F));
	datePanel.add(yearCombo, "East");
	datePanel.add(monthCombo, "Center");
	centrePanel = new BasicPanel();
	centrePanel.setLayout(new BorderLayout(5, 5));
	centrePanel.add(datePanel, "North");
	if (showCurrentDate) {
	    String Fecha = "";
	    try {
		Fecha = Proced.setFormatDate(yearSelected + "-" + monthSelected + "-" + daySelected, true);
	    } catch (Exception x) {
		x.printStackTrace();
	    }
	    currentDateLabel = new BasicLabel("Fecha de Hoy : " + Fecha);
	    currentDateLabel.setFont(currentDateLabel.getFont().deriveFont(1, 11F));
	    currentDatePanel = new BasicPanel();
	    currentDatePanel.setLayout(new FlowLayout(1));
	    currentDatePanel.setBorder(BorderFactory.createEtchedBorder());
	    currentDatePanel.add(currentDateLabel);
	    centrePanel.add(currentDatePanel, "South");
	}
	add(centrePanel);
	yearCombo.addItemListener(comboBoxListener);
	monthCombo.addItemListener(comboBoxListener);
    }

    public final void initializeCalendar() {
	monthCombo.removeItemListener(comboBoxListener);
	yearCombo.removeItemListener(comboBoxListener);
	if (showCalendarForDateSelected)
	    monthCombo.setSelectedIndex(showForMonth - 1);
	else
	    monthCombo.setSelectedIndex(showForMonth);
	yearCombo.setSelectedItem(new Integer(showForYear));
	if (showCalendarForDateSelected)
	    days = createCalendar(showForYear, showForMonth - 1);
	else
	    days = createCalendar(showForYear, showForMonth);
	if (daysPanel != null)
	    centrePanel.remove(daysPanel);
	createDaysPanel();
	centrePanel.add(daysPanel, "Center");
	setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	monthCombo.addItemListener(comboBoxListener);
	yearCombo.addItemListener(comboBoxListener);
    }

    private final int[][] createCalendar(int year, int month) {
	boolean calendarCompleted = false;
	days = new int[7][6];
	Calendar calendar = Calendar.getInstance();
	calendar.set(year, month, 1);
	int number = calendar.get(7);
	int k = 1;
	for (int j = 0; j < 6; j++) {
	    int i = 0;
	    if (j == 0)
		if (firstDay == 0) {
		    i = number - 1;
		} else {
		    i = number - 2;
		    if (i < 0)
			i = 6;
		}
	    for (; i < 7; i++) {
		days[i][j] = k;
		if (month <= 6) {
		    if (month % 2 == 0) {
			if (k == 31) {
			    calendarCompleted = true;
			    break;
			}
		    } else if (month == 1) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			    if (k == 29) {
				calendarCompleted = true;
				break;
			    }
			} else if (k == 28) {
			    calendarCompleted = true;
			    break;
			}
		    } else if (k == 30) {
			calendarCompleted = true;
			break;
		    }
		} else if (month % 2 == 0) {
		    if (k == 30) {
			calendarCompleted = true;
			break;
		    }
		} else if (k == 31) {
		    calendarCompleted = true;
		    break;
		}
		k++;
	    }
	    if (calendarCompleted)
		break;
	}
	return days;
    }

    private final BasicCombo createYearCombo() {
	BasicCombo combo = new BasicCombo();
	for (int i = startYear; i <= endYear; i++)
	    combo.addItem(new Integer(i));
	return combo;
    }

    private final BasicCombo createMonthCombo() {
	BasicCombo list = new BasicCombo();
	list.addItem("Enero");
	list.addItem("Febrero");
	list.addItem("Marzo");
	list.addItem("Abril");
	list.addItem("Mayo");
	list.addItem("Junio");
	list.addItem("Julio");
	list.addItem("Agosto");
	list.addItem("Septiembre");
	list.addItem("Octubre");
	list.addItem("Noviembre");
	list.addItem("Diciembre");
	return list;
    }

    private final void createDaysPanel() {
	daysPanel = new BasicPanel();
	daysPanel.setLayout(new GridLayout(7, 7));
	CalendarDayLabel sunday = new CalendarDayLabel("Domingo");
	CalendarDayLabel monday = new CalendarDayLabel("Lunes");
	CalendarDayLabel tuesday = new CalendarDayLabel("Martes");
	CalendarDayLabel wednesday = new CalendarDayLabel("Miercoles");
	CalendarDayLabel thursday = new CalendarDayLabel("Jueves");
	CalendarDayLabel friday = new CalendarDayLabel("Viernes");
	CalendarDayLabel saturday = new CalendarDayLabel("Sabado");
	if (firstDay == 0) {
	    daysPanel.add(sunday);
	    daysPanel.add(monday);
	    daysPanel.add(tuesday);
	    daysPanel.add(wednesday);
	    daysPanel.add(thursday);
	    daysPanel.add(friday);
	    daysPanel.add(saturday);
	} else {
	    daysPanel.add(monday);
	    daysPanel.add(tuesday);
	    daysPanel.add(wednesday);
	    daysPanel.add(thursday);
	    daysPanel.add(friday);
	    daysPanel.add(saturday);
	    daysPanel.add(sunday);
	}
	ButtonGroup buttonGroup = new ButtonGroup();
	for (int i = 0; i < 6; i++) {
	    for (int k = 0; k < 7; k++)
		if (days[k][i] == 0) {
		    CalendarDayButton dayButton = new CalendarDayButton();
		    daysPanel.add(dayButton);
		} else {
		    CalendarDayButton dayButton = new CalendarDayButton((new Integer(days[k][i])).toString());
		    Integer selectedYear = (Integer)yearCombo.getSelectedItem();
		    if (days[k][i] == (new Integer(daySelected)).intValue() && monthCombo.getSelectedIndex() == (new Integer(monthSelected)).intValue() - 1 && selectedYear.intValue() == (new Integer(yearSelected)).intValue())
			dayButton.setSelected(true);
		    dayButton.addItemListener(botonItemListener);
		    buttonGroup.add(dayButton);
		    daysPanel.add(dayButton);
		}
	}
    }

    public void showCalendarForDate(int year, int month) {
	showForMonth = month;
	showForYear = year;
	showCalendarForDateSelected = true;
    }

    public void setDay(String daySelected) {
	this.daySelected = daySelected;
    }

    public void setMonth(String monthSelected) {
	this.monthSelected = monthSelected;
    }

    public void setYear(String yearSelected) {
	this.yearSelected = yearSelected;
    }

    public String getDay() {
	//System.out.println(daySelected);
	return daySelected;
    }

    public String getMonth() {
	return monthSelected;
    }

    public String getYear() {
	return yearSelected;
    }

    public BotonItemListener getBotonItemListener() {
	return botonItemListener;
    }

    public ComboItemListener getComboBoxListener() {
	return comboBoxListener;
    }

}
