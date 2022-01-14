package org.digitall.apps.calendar.classes;

import org.digitall.lib.geo.esri.ESRIPolygon;

public class WeekRectangle {

    private ESRIPolygon polygon;
    private int weekOfMonth = -1;
    private int weekOfYear = -1;
    private int tomonth = -1;

    public WeekRectangle(ESRIPolygon _polygon, int _weekOfYear) {
	weekOfYear = _weekOfYear;
	polygon = _polygon;
    }

    public ESRIPolygon getPolygon() {
	return polygon;
    }

    public void setPolygon(ESRIPolygon polygon) {
	this.polygon = polygon;
    }
    public int getWeekOfYear() {
	return weekOfYear;
    }

    public void setTomonth(int _tomonth) {
	tomonth = _tomonth;
    }

    public int getTomonth() {
	return tomonth;
    }

    public void setWeekOfMonth(int _weekOfMonth) {
	weekOfMonth = _weekOfMonth;
    }

    public int getWeekOfMonth() {
	return weekOfMonth;
    }

}
