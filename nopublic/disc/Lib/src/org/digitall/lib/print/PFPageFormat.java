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
 * PFPageFormat.java
 *
 * */
package org.digitall.lib.print;

import java.awt.print.PageFormat;
import java.awt.print.Paper;

/**
 * Class: PFPageFormat <p>
 *
 * Represent a page parameters such as margins, gutter,
 * page orientation and page size. This class has also a
 * method to obtain a <code>java.awt.print.PageFormat</code>
 * object used by print API. This class will also compute
 * the page area origin and the page area size. 
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */

/**
 * Class: PFPageFormat <p>
 *
 * Represent a page parameters such as margins, gutter,
 * page orientation and page size. This class has also a
 * method to obtain a <code>java.awt.print.PageFormat</code>
 * object used by print API. This class will also compute
 * the page area origin and the page area size. 
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */
public class PFPageFormat implements Cloneable {

    //WATCH OUT!!!
    //Minimum paper sizes!!!
    //HP1220C from 4x6" to 13x50"
    //HP1220C from 101.6x152.4mm to 330.2x1270mm
    //HP1018 from 3x5" to 8.5x14"
    //HP1018 from 76x127mm to 216x356mm
    //--- Public constants declarations
    public static final int PORTRAIT = 0;
    public static final int LANDSCAPE = 1;
    public static final int PAGE_LETTER = 0;
    public static final int PAGE_LEGAL = 1;
    public static final int PAGE_A4 = 2;
    public static final int PAGE_CUSTOM = 3;
    //--- Private instances declarations
    //----------------------------------
    //--- Dimensions
    private PFUnit leftMargin = new PFInchUnit(1.0);
    private PFUnit rightMargin = new PFInchUnit(1.0);
    private PFUnit topMargin = new PFInchUnit(1.0);
    private PFUnit bottomMargin = new PFInchUnit(1.0);
    private PFUnit gutter = new PFInchUnit(0.0);
    private PFSize pageSize = new PFSize(new PFInchUnit(8.5), new PFInchUnit(11));
    private int pageSizeIndex = PAGE_A4;
    private int pageOrientation = PORTRAIT;

    /**
    * Constructor: PFPageFormat <p>
    *
    */
    public PFPageFormat() {
    }

    /**
     * Method: getPageFormat <p>
     * 
     * Return a page format for this page. This method is
     * responsible for creating a <code>PageFormat</code>
     * object that will be used by the Java Print API to
     * define this page specifications
     * 
     * @return a value of shapeTypeName PageFormat
     * @see PageFormat
     * @see Paper
     */
    public PageFormat getPageFormat() {
	PageFormat pageFormat = new PageFormat();
	Paper paper = new Paper();
	//--- Set the page orientation
	switch (pageOrientation) {
	    case PORTRAIT :
		pageFormat.setOrientation(PageFormat.PORTRAIT);
		paper.setSize(pageSize.getWidth().getPoints(), pageSize.getHeight().getPoints());
		paper.setImageableArea(leftMargin.add(gutter).getPoints(), topMargin.getPoints(), (pageSize.getWidth().substract(leftMargin.add(rightMargin).add(gutter))).getPoints(), (pageSize.getHeight().substract(topMargin.add(bottomMargin))).getPoints());
		break;
	    case LANDSCAPE :
		pageFormat.setOrientation(PageFormat.LANDSCAPE);
		paper.setSize(pageSize.getHeight().getPoints(), pageSize.getWidth().getPoints());
		paper.setImageableArea(topMargin.getPoints(), leftMargin.add(gutter).getPoints(), (pageSize.getHeight().substract(topMargin.add(bottomMargin))).getPoints(), (pageSize.getWidth().substract(leftMargin.add(rightMargin).add(gutter))).getPoints());
		break;
	}
	//--- Set the page size
	pageFormat.setPaper(paper);
	return (pageFormat);
    }

    /**
     * Method: setLeftMargin <p>
     * 
     * Set the left margin for this page
     * 
     * @param parLeftMargin a value of shapeTypeName PFUnit
     */
    public void setLeftMargin(PFUnit parLeftMargin) {
	leftMargin = parLeftMargin;
    }

    /**
     * Method: getLeftMargin <p>
     * 
     * Get the left margin for this page
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFUnit getLeftMargin() {
	return (leftMargin);
    }

    /**
     * Method: setRightMargin <p>
     * 
     * Set the right margin for this page
     * 
     * @param parRightMargin a value of shapeTypeName PFUnit
     */
    public void setRightMargin(PFUnit parRightMargin) {
	rightMargin = parRightMargin;
    }

    /**
     * Method: getRightMargin <p>
     * 
     * Get the right margin for this page
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFUnit getRightMargin() {
	return (rightMargin);
    }

    /**
     * Method: setTopMargin <p>
     * 
     * Set the top margin for this page
     * 
     * @param parTopMargin a value of shapeTypeName PFUnit
     */
    public void setTopMargin(PFUnit parTopMargin) {
	topMargin = parTopMargin;
    }

    /**
     * Method: getTopMargin <p>
     * 
     * Get the top margin for this page
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getTopMargin() {
	return (topMargin);
    }

    /**
     * Method: setBottomMargin <p>
     * 
     * Set the bottom margin for this page
     * 
     * @param parBottomMargin a value of shapeTypeName PFUnit
     */
    public void setBottomMargin(PFUnit parBottomMargin) {
	bottomMargin = parBottomMargin;
    }

    /**
     * Method: getBottomMargin <p>
     * 
     * Get the bottom margin for this page
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getBottomMargin() {
	return (bottomMargin);
    }

    /**
     * Method: getPageAreaOrigin <p>
     * 
     * Return the page area origin.
     * 
     * @return a value of shapeTypeName PFPoint
     */
    public PFPoint getPageAreaOrigin() {
	return (new PFPoint(leftMargin.add(gutter), topMargin));
    }

    /**
     * Method: getPageAreaSize <p>
     * 
     * Return the page area size including margins
     * and gutter but excluding the header ans footer heights.
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFSize getPageAreaSize() {
	return (new PFSize(pageSize.getWidth().substract(leftMargin.add(gutter)).substract(rightMargin), pageSize.getHeight().substract(topMargin).substract(bottomMargin)));
    }

    /**
     * Method: setGutter <p>
     * 
     * Set the gutter for this page
     * 
     * @param parGutter a value of shapeTypeName PFUnit
     */
    public void setGutter(PFUnit parGutter) {
	gutter = parGutter;
    }

    /**
     * Method: getGutter <p>
     * 
     * Return the gutter for this page
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getGutter() {
	return (gutter);
    }

    /**
     * Method: setPageSize <p>
     * 
     * @param parPageSize a value of shapeTypeName int
     */
    public void setPageSize(int parPageSize) {
	pageSizeIndex = parPageSize;
	switch (pageSizeIndex) {
	    case PAGE_LETTER :
		setPageSize(new PFSize(new PFInchUnit(8.5), new PFInchUnit(11.0)));
		break;
	    case PAGE_LEGAL :
		setPageSize(new PFSize(new PFInchUnit(8.5), new PFInchUnit(14.0)));
		break;
	    case PAGE_A4 :
		//setPageSize (new PFSize (new PFInchUnit (8.5), new PFInchUnit (11.0)));
		break;
	}
    }

    /**
     * Method: getPageSizeIndex <p>
     * 
     * @return a value of shapeTypeName int
     */
    public int getPageSizeIndex() {
	return (pageSizeIndex);
    }

    /**
     * Method: setPageSize <p>
     * 
     * Set this page size.
     * 
     * @param parPageSize a value of shapeTypeName PFSize
     */
    public void setPageSize(PFSize parPageSize) {
	pageSize = parPageSize;
    }

    /**
     * Method: getPageSize <p>
     * 
     * Return this page size 
     * 
     * @return a value of shapeTypeName PFSize
     */
    public PFSize getPageSize() {
	return (pageSize);
    }

    /**
     * Method: setPageOrientation <p>
     * 
     * Set this page orientation. The orientation
     * can be either PORTRAIT or LANDSCAPE<p>
     * 
     * When switching from one orientation to another
     * it's important to switch the height and width
     * od the page
     * 
     * @param parPageOrientation a value of shapeTypeName int
     */
    public void setPageOrientation(int parPageOrientation) {
	PFUnit tempUnit;
	if (pageOrientation != parPageOrientation) {
	    pageOrientation = parPageOrientation;
	    tempUnit = pageSize.getWidth();
	    pageSize.setWidth(pageSize.getHeight());
	    pageSize.setHeight(tempUnit);
	}
    }

    /**
     * Method: getPageOrientation <p>
     * 
     * Return this page orientation
     * 
     * @return a value of shapeTypeName int
     */
    public int getPageOrientation() {
	return (pageOrientation);
    }

    /**
     * Method: [] <p>
     * 
     * @return a value of shapeTypeName String
     */
    public String[] getPageSizeDefinition() {
	String[] pageName = { "Letter", "Legal", "A4", "Custom" };
	return (pageName);
    }

    /**
     * Method: [] <p>
     * 
     * @return a value of shapeTypeName String
     */
    public String[] getPageOrientationDefinition() {
	String[] orientation = { "Portrait", "Landscape" };
	return (orientation);
    }

    /**
     * Method: clone <p>
     * 
     * @return a value of shapeTypeName Object
     */
    public Object clone() {
	try {
	    return ((PFPageFormat)super.clone());
	} catch (CloneNotSupportedException cnse) {
	    cnse.printStackTrace();
	    return (null);
	}
    }

}// PFPageFormat










