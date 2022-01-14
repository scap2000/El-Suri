package org.digitall.lib.print;

public class PFmmUnit extends PFUnit {

    //--- Private constants declarations
    private static final double POINTS_PER_MM = 2.83465;

    /**
    * Constructor: PFCmUnit <p>
    *
    */
    public PFmmUnit() {

    }

    /**
    * Constructor: PFCmUnit <p>
    *
    * @param parValue a value of type double
    */
    public PFmmUnit(double parValue) {
	super(parValue);
    }

    /**
    * Method: getPoints <p>
    *
    * Return the result of the conversion from
    * centimeters to points.
    *
    * @return a value of type double
    */
    public double getPoints() {
	return (getUnits() * POINTS_PER_MM);
    }

    /**
    * Method: setPoints <p>
    *
    * @param parPoints a value of type double
    */
    public void setPoints(double parPoints) {
	setUnits(parPoints / POINTS_PER_MM);
    }

}// PFCmUnit
