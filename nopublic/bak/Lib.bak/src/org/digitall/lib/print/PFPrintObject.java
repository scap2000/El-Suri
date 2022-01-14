package org.digitall.lib.print;

import java.awt.Graphics2D;

import java.util.Vector;

/**
 * Class: PFPrintObject <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */

/**
 * Class: PFPrintObject <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */
public abstract class PFPrintObject {

    //--- Public constants declarations
    public static final int STICKY_LEFT = 0;
    public static final int STICKY_RIGHT = 1;
    public static final int STICKY_CENTER = 2;
    public static final int STICKY_TOP = 3;
    public static final int STICKY_BOTTOM = 4;
    public static final int STICKY_NONE = 5;
    //--- Private instances declarations
    private PFPoint position = new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0));
    private PFSize size = new PFSize(new PFInchUnit(1.0), new PFInchUnit(1.0));
    private Vector printObjectCollection = new Vector();
    private PFPrintObject parentObject = null;
    private PFPage page = null;
    private int verticalSticky = STICKY_NONE;
    private int horizontalSticky = STICKY_NONE;
    private boolean horizontalFill = false;
    private boolean verticalFill = false;
    private PFPoint drawOrigin = new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0));
    private PFSize drawSize = new PFSize(new PFInchUnit(0.0), new PFInchUnit(0.0));
    private boolean relativeMode = true;
    private PFUnit leftMargin = new PFInchUnit(0.0);
    private PFUnit rightMargin = new PFInchUnit(0.0);
    private PFUnit topMargin = new PFInchUnit(0.0);
    private PFUnit bottomMargin = new PFInchUnit(0.0);

    /**
    * Constructor: PFPrintObject <p>
    *
    */
    public PFPrintObject() {
    }

    /**
     * Method: print <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    public abstract void print(Graphics2D parG);

    /**
     * Method: printChilds <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    public void printChilds(Graphics2D parG) {
	int i;
	PFPrintObject tempPrintObject;
	//--- Draw child objects
	for (i = 0; i < printObjectCollection.size(); i++) {
	    tempPrintObject = (PFPrintObject)printObjectCollection.get(i);
	    tempPrintObject.print(parG);
	}
    }

    /**
     * Method: getDrawingOrigin <p>
     * 
     * @return a value of shapeTypeName PFPoint
     */
    public PFPoint getDrawingOrigin() {
	return (drawOrigin);
    }

    /**
     * Method: getDrawingSize <p>
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFSize getDrawingSize() {
	return (drawSize);
    }

    /**
    * Method: computePositionAndSize <p>
    *
    */
    public void computePositionAndSize() {
	PFPoint location;
	PFInchUnit parentCenter;
	PFInchUnit childCenter;
	PFInchUnit objectPosition;
	PFPoint parentLocation;
	PFSize parentSize;
	//--- Get parent infos
	if (isChild()) {
	    parentLocation = getParent().getDrawingOrigin();
	    parentSize = getParent().getDrawingSize();
	} else {
	    parentLocation = getPage().getPrintableAreaOrigin();
	    parentSize = getPage().getPrintableAreaSize();
	}
	//--- Validate relative mode and set the draw origin
	if (relativeMode) {
	    drawOrigin = (PFPoint)position.clone();
	    drawOrigin.add(parentLocation);
	} else {
	    drawOrigin = (PFPoint)position.clone();
	}
	//--- Initialiaze the size
	drawSize = (PFSize)size.clone();
	//--- Set the margins
	drawOrigin.setX(drawOrigin.getX().add(leftMargin));
	drawOrigin.setY(drawOrigin.getY().add(topMargin));
	drawSize.setWidth(drawSize.getWidth().substract(leftMargin.add(rightMargin)));
	drawSize.setHeight(drawSize.getHeight().substract(topMargin.add(bottomMargin)));
	//--- Compute the fills
	if (horizontalFill) {
	    drawSize = new PFSize(parentSize.getWidth(), drawSize.getHeight());
	}
	if (verticalFill) {
	    drawSize = new PFSize(drawSize.getWidth(), parentSize.getHeight());
	}
	//--- Compute vertical sticky options
	switch (verticalSticky) {
	    case STICKY_TOP :
		drawOrigin.setLocation(drawOrigin.getX(), parentLocation.getY());
		break;
	    case STICKY_CENTER :
		parentCenter = (PFInchUnit)parentSize.getHeight().divide(2.0);
		childCenter = (PFInchUnit)this.getSize().getHeight().divide(2.0);
		objectPosition = (PFInchUnit)parentCenter.substract(childCenter);
		drawOrigin.setLocation(drawOrigin.getX(), objectPosition.add(drawOrigin.getY()));
		break;
	    case STICKY_BOTTOM :
		objectPosition = (PFInchUnit)parentSize.getHeight().substract(this.getSize().getHeight());
		drawOrigin.setLocation(drawOrigin.getX(), objectPosition.add(drawOrigin.getY()));
		break;
	}
	//--- Compute horizontal sticky
	switch (horizontalSticky) {
	    case STICKY_LEFT :
		drawOrigin.setLocation(drawOrigin.getX(), drawOrigin.getY());
		break;
	    case STICKY_CENTER :
		parentCenter = (PFInchUnit)parentSize.getWidth().divide(2.0);
		childCenter = (PFInchUnit)this.getSize().getWidth().divide(2.0);
		objectPosition = (PFInchUnit)parentCenter.substract(childCenter);
		drawOrigin.setLocation(objectPosition.add(drawOrigin.getX()), drawOrigin.getY());
		break;
	    case STICKY_RIGHT :
		objectPosition = (PFInchUnit)parentSize.getWidth().substract(this.getSize().getWidth());
		drawOrigin.setLocation(objectPosition.add(drawOrigin.getX()), drawOrigin.getY());
		break;
	}
    }

    /**
     * Method: setVerticalSticky <p>
     * 
     * @param parSticky a value of shapeTypeName int
     */
    public void setVerticalSticky(int parSticky) {
	verticalSticky = parSticky;
    }

    /**
     * Method: getVerticalSticky <p>
     * 
     * @return a value of shapeTypeName int
     */
    public int getVerticalSticky() {
	return (verticalSticky);
    }

    /**
     * Method: setHorizontalSticky <p>
     * 
     * @param parSticky a value of shapeTypeName int
     */
    public void setHorizontalSticky(int parSticky) {
	horizontalSticky = parSticky;
    }

    /**
     * Method: getHorizontalSticky <p>
     * 
     * @return a value of shapeTypeName int
     */
    public int getHorizontalSticky() {
	return (horizontalSticky);
    }

    /**
     * Method: setHorizontalFill <p>
     * 
     * @param parFill a value of shapeTypeName boolean
     */
    public void setHorizontalFill(boolean parFill) {
	horizontalFill = parFill;
    }

    /**
     * Method: getHorizontalFill <p>
     * 
     * @return a value of shapeTypeName boolean
     */
    public boolean getHorizontalFill() {
	return (horizontalFill);
    }

    /**
     * Method: setVerticalFill <p>
     * 
     * @param parFill a value of shapeTypeName boolean
     */
    public void setVerticalFill(boolean parFill) {
	verticalFill = parFill;
    }

    /**
     * Method: getVerticalFill <p>
     * 
     * @return a value of shapeTypeName boolean
     */
    public boolean getVerticalFill() {
	return (verticalFill);
    }

    /**
     * Method: add <p>
     * 
     * @param parPrintObject a value of shapeTypeName PFPrintObject
     */
    public void add(PFPrintObject parPrintObject) {
	printObjectCollection.add(parPrintObject);
	parPrintObject.setParent(this);
    }

    /**
     * Method: setParent <p>
     * 
     * @param parPrintObject a value of shapeTypeName PFPrintObject
     */
    private void setParent(PFPrintObject parPrintObject) {
	parentObject = parPrintObject;
    }

    /**
     * Method: getParent <p>
     * 
     * @return a value of shapeTypeName PFPrintObject
     */
    public PFPrintObject getParent() {
	return (parentObject);
    }

    /**
     * Method: hasParent <p>
     * 
     * @return a value of shapeTypeName boolean
     */
    public boolean isChild() {
	if (parentObject != null)
	    return (true);
	else
	    return (false);
    }

    /**
     * Method: remove <p>
     * 
     * @param parPrintObject a value of shapeTypeName PFPrintObject
     */
    public void remove(PFPrintObject parPrintObject) {
	printObjectCollection.remove(parPrintObject);
	parPrintObject.setParent(null);
    }

    /**
     * Method: setPage <p>
     * 
     * @param parPage a value of shapeTypeName PFPage
     */
    public void setPage(PFPage parPage) {
	int i;
	PFPrintObject tempPrintObject;
	//--- Set this object parent page
	page = parPage;
	//--- Set childs parent page
	for (i = 0; i < printObjectCollection.size(); i++) {
	    tempPrintObject = (PFPrintObject)printObjectCollection.get(i);
	    tempPrintObject.setPage(parPage);
	}
    }

    /**
     * Method: getPage <p>
     * 
     * @return a value of shapeTypeName PFPage
     */
    public PFPage getPage() {
	return (page);
    }

    /**
     * Method: setPosition <p>
     * 
     * @param parPosition a value of shapeTypeName PFPoint
     */
    public void setPosition(PFPoint parPosition) {
	position = parPosition;
    }

    /**
     * Method: getPosition <p>
     * 
     * @return a value of shapeTypeName PFPoint
     */
    public PFPoint getPosition() {
	return (position);
    }

    /**
     * Method: setSize <p>
     * 
     * @param parSize a value of shapeTypeName PFSize
     */
    public void setSize(PFSize parSize) {
	size = parSize;
    }

    /**
     * Method: getSize <p>
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFSize getSize() {
	return (size);
    }

    /**
     * Method: setWidth <p>
     * 
     * @param parWidth a value of shapeTypeName PFUnit
     */
    public void setWidth(PFUnit parWidth) {
	size.setWidth(parWidth);
    }

    /**
     * Method: setHeight <p>
     * 
     * @param parHeight a value of shapeTypeName PFUnit
     */
    public void setHeight(PFUnit parHeight) {
	size.setHeight(parHeight);
    }

    /**
     * Method: setRelativeMode <p>
     * 
     * @param parMode a value of shapeTypeName boolean
     */
    public void setRelativeMode(boolean parMode) {
	relativeMode = parMode;
    }

    /**
     * Method: setLeftMargin <p>
     * 
     * @param parLeftMargin a value of shapeTypeName PFUnit
     */
    public void setLeftMargin(PFUnit parLeftMargin) {
	leftMargin = parLeftMargin;
    }

    /**
     * Method: getLeftMargin <p>
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFUnit getLeftMargin() {
	return (leftMargin);
    }

    /**
     * Method: setRightMargin <p>
     * 
     * @param parRightMargin a value of shapeTypeName PFUnit
     */
    public void setRightMargin(PFUnit parRightMargin) {
	rightMargin = parRightMargin;
    }

    /**
     * Method: getRightMargin <p>
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFUnit getRightMargin() {
	return (rightMargin);
    }

    /**
     * Method: setTopMargin <p>
     * 
     * @param parTopMargin a value of shapeTypeName PFUnit
     */
    public void setTopMargin(PFUnit parTopMargin) {
	topMargin = parTopMargin;
    }

    /**
     * Method: getTopMargin <p>
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getTopMargin() {
	return (topMargin);
    }

    /**
     * Method: setBottomMargin <p>
     * 
     * @param parBottomMargin a value of shapeTypeName PFUnit
     */
    public void setBottomMargin(PFUnit parBottomMargin) {
	bottomMargin = parBottomMargin;
    }

    /**
     * Method: getBottomMargin <p>
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getBottomMargin() {
	return (bottomMargin);
    }

}// PFPrintObject














