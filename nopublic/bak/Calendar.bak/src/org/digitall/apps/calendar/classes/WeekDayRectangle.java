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
		dayString = "Sunday";
		dayShortString = "Sun";
		break;
	    case Calendar.MONDAY :
		dayString = "Monday";
		dayShortString = "Mon";
		break;
	    case Calendar.TUESDAY :
		dayString = "Tuesday";
		dayShortString = "Tue";
		break;
	    case Calendar.WEDNESDAY :
		dayString = "Wednesday";
		dayShortString = "Wed";
		break;
	    case Calendar.THURSDAY :
		dayString = "Thursday";
		dayShortString = "Thu";
		break;
	    case Calendar.FRIDAY :
		dayString = "Friday";
		dayShortString = "Fri";
		break;
	    case Calendar.SATURDAY :
		dayString = "Saturday";
		dayShortString = "Sat";
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
