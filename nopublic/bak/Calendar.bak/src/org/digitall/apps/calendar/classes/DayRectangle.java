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
