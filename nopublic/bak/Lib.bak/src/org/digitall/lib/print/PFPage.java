package org.digitall.lib.print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import java.util.Vector;

/**
 * Class: PFPage <p>
 * 
 * The page class act as a container of <code>PFPrintObject</code>. 
 * Each page in a <code>PFDocument</code> has to be an instance
 * of this class. This class implement the <code>Printable</code>
 * interface
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see Printable
 */

/**
 * Class: PFPage <p>
 * 
 * The page class act as a container of <code>PFPrintObject</code>. 
 * Each page in a <code>PFDocument</code> has to be an instance
 * of this class. This class implement the <code>Printable</code>
 * interface
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see Printable
 */
public class PFPage implements Printable {

    private double rotation = 0.0;
    private double xtranslation = 0.0;
    private double ytranslation = 0.0;

    //WATCH OUT!!!
    //Minimum paper sizes!!!
    //HP1220C from 4x6" to 13x50"
    //HP1220C from 101.6x152.4mm to 330.2x1270mm
    //HP1018 from 3x5" to 8.5x14"
    //HP1018 from 76x127mm to 216x356mm
    //--- Private instances declarations
    //----------------------------------
    //--- Page format
    private PFPageFormat pageFormat = new PFPageFormat();
    //--- Page object container
    private Vector<PFPrintObject> pageObjectCollection = new Vector();
    //--- Header and footer
    private PFPrintObject header = null;
    private PFPrintObject footer = null;
    //--- Reference to parent document
    private PFDocument document = null;

    /**
    * Constructor: PFPage <p>
    *
    * Default constructor
    */
    public PFPage() {
    }

    /**
     * Method: setPageFormat <p>
     * 
     * @param parPageFormat a value of shapeTypeName PFPageFormat
     */
    public void setPageFormat(PFPageFormat parPageFormat) {
	if (parPageFormat != null) {
	    pageFormat = parPageFormat;
	}
    }

    /**
     * Method: getPageFormat <p>
     * 
     * @return a value of shapeTypeName PFPageFormat
     */
    public PFPageFormat getPageFormat() {
	return ((PFPageFormat)pageFormat.clone());
    }

    /**
     * Method: print <p>
     * 
     * Print header/footer and the page content. This method 
     * implement the <code>Printable</code> interface.
     * 
     * @param parG a value of shapeTypeName Graphics
     * @param parPageFormat a value of shapeTypeName PageFormat
     * @param parPage a value of shapeTypeName int
     */
    public int print(Graphics parG, PageFormat parPageFormat, int parPage) {
	PFPrintObject printObject;
	Graphics2D g2d;
	PFPrintObject printHeader;
	PFPrintObject printFooter;
	//--- Convert to Graphics 2D
	g2d = (Graphics2D)parG;
	//ROTATE
	g2d.rotate(rotation*Math.PI/180);
	g2d.translate(xtranslation,ytranslation);
	//--- Render the header
	printHeader = getHeader();
	if (printHeader != null) {
	    printHeader.setRelativeMode(false);
	    printHeader.setPosition(new PFPoint(pageFormat.getLeftMargin(), pageFormat.getTopMargin()));
	    printHeader.print(g2d);
	}
	//--- Render the footer
	printFooter = getFooter();
	if (printFooter != null) {
	    printFooter.setRelativeMode(false);
	    PFSize tempSize = pageFormat.getPageAreaSize();
	    PFPoint tempPoint = new PFPoint(pageFormat.getLeftMargin(), pageFormat.getPageAreaSize().getHeight().substract(printFooter.getSize().getHeight()).add(pageFormat.getBottomMargin()));
	    printFooter.setPosition(tempPoint);
	    printFooter.print(g2d);
	}
	//--- Draw page content
	for (int i = 0; i < pageObjectCollection.size(); i++) {
	    printObject = (PFPrintObject)pageObjectCollection.get(i);
	    printObject.print(g2d);
	}
	return (PAGE_EXISTS);
    }

    /**
     * Method: add <p>
     * 
     * Add a print object (<code>PFPrintObject</code>) in 
     * this page.
     * 
     * @param parPrintObject a value of shapeTypeName PFPrintObject
     */
    public void add(PFPrintObject parPrintObject) {
	pageObjectCollection.add(parPrintObject);
	parPrintObject.setPage(this);
    }

    /**
     * Method: remove <p>
     * 
     * Remove a print object from this page
     * 
     * @param parPrintObject a value of shapeTypeName PFPrintObject
     */
    public void remove(PFPrintObject parPrintObject) {
	pageObjectCollection.remove(parPrintObject);
    }

    /**
     * Method: setDocument <p>
     * 
     * Set the parent document object.
     * 
     * @param parDocument a value of shapeTypeName PFDocument
     */
    public void setDocument(PFDocument parDocument) {
	document = parDocument;
    }

    /**
     * Method: getPrintableAreaOrigin <p>
     * 
     * Return the printable area origin. The print
     * area origin is defined as the top left corner
     * of the page including margins and gutters but
     * excluding the header and footers.
     * 
     * @return a value of shapeTypeName PFPoint
     */
    public PFPoint getPrintableAreaOrigin() {
	PFPoint origin;
	if (getHeader() != null)
	    origin = new PFPoint(pageFormat.getLeftMargin().add(pageFormat.getGutter()), pageFormat.getTopMargin().add(getHeader().getSize().getHeight()));
	else
	    origin = new PFPoint(pageFormat.getLeftMargin().add(pageFormat.getGutter()), pageFormat.getTopMargin());
	return (origin);
    }

    /**
     * Method: getPrintableAreaSize <p>
     * 
     * Return the printable area size including the
     * margins and gutter including the header and
     * footer heights.
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFSize getPrintableAreaSize() {
	PFSize areaSize = new PFSize(new PFInchUnit(0.0), new PFInchUnit(0.0));
	areaSize.setWidth(pageFormat.getPageSize().getWidth().substract(pageFormat.getLeftMargin()).substract(pageFormat.getGutter()).substract(pageFormat.getRightMargin()));
	areaSize.setHeight(pageFormat.getPageSize().getHeight().substract(pageFormat.getTopMargin()).substract(pageFormat.getBottomMargin()));
	if (header != null)
	    areaSize.setHeight(areaSize.getHeight().substract(header.getSize().getHeight()));
	if (footer != null)
	    areaSize.setHeight(areaSize.getHeight().substract(footer.getSize().getHeight()));
	return (areaSize);
    }

    /**
     * Method: setHeader <p>
     * 
     * Set the header for this page. This 
     * header will override the header specified in
     * the document
     * 
     * @param parHeader a value of shapeTypeName PFPrintObject
     */
    public void setHeader(PFPrintObject parHeader) {
	header = parHeader;
	header.setPage(this);
    }

    /**
     * Method: getHeader <p>
     * 
     * Return the header for this page
     * 
     * @return a value of shapeTypeName PFPrintObject
     */
    public PFPrintObject getHeader() {
	if (document != null) {
	    if (header == null && document.getHeader(this) != null)
		return (document.getHeader());
	}
	return (header);
    }

    /**
     * Method: setFooter <p>
     * 
     * Set the footer for this page. This footer setting
     * will override the footer setting in the document.
     * 
     * @param parFooter a value of shapeTypeName PFPrintObject
     */
    public void setFooter(PFPrintObject parFooter) {
	footer = parFooter;
	footer.setPage(this);
    }

    /**
     * Method: getFooter <p>
     * 
     * Return the footer for this page
     * 
     * @return a value of shapeTypeName PFPrintObject
     */
    public PFPrintObject getFooter() {
	if (document != null) {
	    if (footer == null && document.getFooter(this) != null)
		return (document.getFooter());
	}
	return (footer);
    }

    /**
     * Method: getPageNo <p>
     * 
     * Return the page number for this page.
     * 
     * @return a value of shapeTypeName int
     */
    public int getPageNo() {
	return (document.getPageNo(this));
    }
    /*NO SIRVE TODAVIA*/

    public double getPrintableContentSize() {
	double printableContentSize = 0.0;
	for (int i = 0; i < pageObjectCollection.size(); i++) {
	    printableContentSize += pageObjectCollection.get(i).getSize().getHeight().getPoints();
	    System.out.println(pageObjectCollection.get(i).getSize().getHeight().getPoints());
	}
	return printableContentSize;
    }

    public void setRotation(double rotation) {
	this.rotation = rotation;
    }

    public void setXtranslation(double xtranslation) {
	this.xtranslation = xtranslation;
    }

    public void setYtranslation(double ytranslation) {
	this.ytranslation = ytranslation;
    }

}
// PFPage
