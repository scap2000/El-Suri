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
	        monthString = "January";
	        monthShortString = "Jan";
	        break;
	    case Calendar.FEBRUARY :
	        monthString = "February";
	        monthShortString = "Feb";
	        break;
	    case Calendar.MARCH :
	        monthString = "March";
	        monthShortString = "Mar";
	        break;
	    case Calendar.APRIL :
	        monthString = "April";
	        monthShortString = "Apr";
	        break;
	    case Calendar.MAY :
	        monthString = "May";
	        monthShortString = "May";
	        break;
	    case Calendar.JULY :
	        monthString = "July";
	        monthShortString = "July";
	        break;
	    case Calendar.JUNE :
	        monthString = "June";
	        monthShortString = "June";
	        break;
	    case Calendar.AUGUST :
	        monthString = "August";
	        monthShortString = "Aug";
	        break;
	    case Calendar.SEPTEMBER :
	        monthString = "September";
	        monthShortString = "Sep";
	        break;
	    case Calendar.OCTOBER :
	        monthString = "October";
	        monthShortString = "Oct";
	        break;
	    case Calendar.NOVEMBER :
	        monthString = "November";
	        monthShortString = "nov";
	        break;
	    case Calendar.DECEMBER :
	        monthString = "December";
	        monthShortString = "Dec";
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
