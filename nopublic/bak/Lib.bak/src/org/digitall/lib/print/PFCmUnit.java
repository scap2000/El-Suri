package org.digitall.lib.print;

/**
 * Class: PFCmUnit <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFUnit
 */
public class PFCmUnit extends PFUnit {

    //--- Private constants declarations
    private static final double POINTS_PER_CM = 28.3465;

    /**
    * Constructor: PFCmUnit <p>
    *
    */
    public PFCmUnit() {
    }

    /**
     * Constructor: PFCmUnit <p>
     * 
     * @param parValue a value of shapeTypeName double
     */
    public PFCmUnit(double parValue) {
	super(parValue);
    }

    /**
     * Method: getPoints <p>
     * 
     * Return the result of the conversion from
     * centimeters to points.
     * 
     * @return a value of shapeTypeName double
     */
    public double getPoints() {
	return (getUnits() * POINTS_PER_CM);
    }

    /**
     * Method: setPoints <p>
     * 
     * @param parPoints a value of shapeTypeName double
     */
    public void setPoints(double parPoints) {
	setUnits(parPoints / POINTS_PER_CM);
    }

}// PFCmUnit



















