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
 * PFParagraph.java
 *
 * */
package org.digitall.lib.print;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;

import java.text.AttributedString;

import java.util.Vector;

/**
 * Class: PFParagraph <p>
 *
 * Render a paragraph of text.<p>
 *
 * Using this class you will be able to render a paragraph of text
 * with the following attributes:
 *
 * <ul>
 * <li>Set the font of the text. If an <code>AttributedString</code> is used
 * you must then specify the font in the <code>AttributedStrin</code> itself</li>
 *
 * <li>Set the text color, again if an <code>AttributedString</code> is used,
 * then you must specify the text color in the <code>AttributedString</code></li>
 *
 * <li>Justify the text using the following attribute: Left, Right, Center and
 * full justification</li>
 *
 * <li>Provide paragraph metrics, using the getTextHeight () will return the height
 * in <code>PFUnit</code> of the rendered text</li>
 *
 * <li>Provide the position of the next paragraph with the
 * <code>getNextParagraphPosition</code> method
 * 
 * <li>Automatic height adjustement in relation with the paragraph
 * height</li>
 * </ul>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */

/**
 * Class: PFParagraph <p>
 *
 * Render a paragraph of text.<p>
 *
 * Using this class you will be able to render a paragraph of text
 * with the following attributes:
 *
 * <ul>
 * <li>Set the font of the text. If an <code>AttributedString</code> is used
 * you must then specify the font in the <code>AttributedStrin</code> itself</li>
 *
 * <li>Set the text color, again if an <code>AttributedString</code> is used,
 * then you must specify the text color in the <code>AttributedString</code></li>
 *
 * <li>Justify the text using the following attribute: Left, Right, Center and
 * full justification</li>
 *
 * <li>Provide paragraph metrics, using the getTextHeight () will return the height
 * in <code>PFUnit</code> of the rendered text</li>
 *
 * <li>Provide the position of the next paragraph with the
 * <code>getNextParagraphPosition</code> method
 * 
 * <li>Automatic height adjustement in relation with the paragraph
 * height</li>
 * </ul>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */
public class PFParagraph extends PFPrintObject {

    //--- Public constants declarations
    public static final int LEFT_JUSTIFIED = 0;
    public static final int RIGHT_JUSTIFIED = 1;
    public static final int CENTER_JUSTIFIED = 2;
    public static final int FULL_JUSTIFIED = 3;
    public static final int BOTH_CENTER_JUSTIFIED = 4;
    //--- Private instances declarations
    private AttributedString text;
    //Default config
    private Font font = new Font("serif", Font.PLAIN, 12);
    private int horizontalAlignment = LEFT_JUSTIFIED;
    private Color textColor = Color.black;
    private boolean autoAdjustHeight = false;
    private PFPoint center = null;

    /**
    * Constructor: PFParagraph <p>
    *
    */
    public PFParagraph() {
    }

    /**
     * Constructor: PFParagraph <p>
     * 
     * Create a paragraph object using an <code>AttributedString</code>
     * 
     * @param parText a value of shapeTypeName AttributedText
     */
    public PFParagraph(AttributedString parText) {
	text = parText;
    }

    /**
     * Method: setText <p>
     * 
     * Set the paragraph text by providing an
     * <code>AttributedString</code> class
     * 
     * Upon setting the paragraph text, this method
     * will check if the autoAdjustHeight flag is set.
     * If <code>true</code> then the height of the
     * object is adjusted appropriatly. Else the text
     * will be clipped in the rendering process.
     * 
     * @param parText a value of shapeTypeName AttributedString
     */
    public void setText(AttributedString parText) {
	//--- Set text content
	text = parText;
	//--- Auto adjust height
	if (autoAdjustHeight)
	    setHeight(getTextHeight());
    }

    /**
     * Method: setText <p>
     * 
     * @param parText a value of shapeTypeName String
     */
    public void setText(String parText) {
	text = new AttributedString(parText);
	text.addAttribute(TextAttribute.FONT, new Font("serif", Font.PLAIN, 12));
	setText(text);
    }

    /**
     * Method: autoAdjustHeight <p>
     * 
     * @param parAutoAdjust a value of shapeTypeName boolean
     */
    public void setAutoAdjustHeight(boolean parAutoAdjust) {
	autoAdjustHeight = parAutoAdjust;
    }

    /**
     * Method: isAutoAdjustHeight <p>
     * 
     * @return a value of shapeTypeName boolean
     */
    public boolean isAutoAdjustHeight() {
	return (autoAdjustHeight);
    }

    /**
     * Method: setHorizontalAligment <p>
     * 
     * @param parAlignment a value of shapeTypeName int
     */
    public void setHorizontalAlignment(int parAlignment) {
	horizontalAlignment = parAlignment;
    }

    /**
     * Method: getHorizontalAlignment <p>
     * 
     * @return a value of shapeTypeName int
     */
    public int getHorizontalAlignment() {
	return (horizontalAlignment);
    }

    /**
     * Method: draw <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    public void print(Graphics2D parG) {
	//--- Compute position and size
	computePositionAndSize();
	//--- Draw paragraph
	parG.setColor(textColor);
	parG.setFont(font);
	switch (horizontalAlignment) {
	    case LEFT_JUSTIFIED :
		renderLeftJustified(parG);
		break;
	    case RIGHT_JUSTIFIED :
		renderRightJustified(parG);
		break;
	    case CENTER_JUSTIFIED :
		renderCenterJustified(parG);
		break;
	    case BOTH_CENTER_JUSTIFIED :
		renderBothCenterJustified(parG);
		break;
	    case FULL_JUSTIFIED :
		renderFullyJustified(parG);
		break;
	}
	//--- Print childs
	parG.setColor(Color.white);
	parG.drawRect((int)getPosition().getX().getPoints(), (int)getPosition().getY().getPoints(), (int)getSize().getWidth().getPoints(), (int)getSize().getHeight().getPoints());
	printChilds(parG);
    }

    /**
     * Method: getTextHeight <p>
     * 
     * @return a value of shapeTypeName PFUnit
     */
    public PFUnit getTextHeight() {
	PFInchUnit textHeight = new PFInchUnit(0.0);
	//--- Compute position and size
	computePositionAndSize();
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = new Point2D.Double(0.0, 0.0);
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	//--- LineBreakMeasurer will wrap each line to correct length and
	//--- return it as a TextLayout object
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent() + layout.getDescent() + layout.getLeading();
	}
	textHeight.setPoints(pen.y);
	return (textHeight);
    }

    /**
     * Method: getNextParagraphPosition <p>
     * 
     * @return a value of shapeTypeName PFPoint
     */
    public PFPoint getNextParagraphPosition() {
	double lastAscent = 0.0;
	//--- Compute position and size
	computePositionAndSize();
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = new Point2D.Double(0.0, 0.0);
	//getDrawingOrigin ().getPoint2D ();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	//--- LineBreakMeasurer will wrap each line to correct length and
	//--- return it as a TextLayout object
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent() + layout.getDescent() + layout.getLeading();
	    lastAscent = layout.getAscent();
	}
	PFInchUnit y = new PFInchUnit();
	y.setPoints(pen.y);
	return (new PFPoint(getPosition().getX(), y));
    }

    /**
     * Method: renderCenterJustified <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    private void renderCenterJustified(Graphics2D parG) {
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = getDrawingOrigin().getPoint2D();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	float layoutX;
	//--- LineBreakMeasurer will wrap each line to correct length and
	//--- return it as a TextLayout object
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent();
	    //--- Align the X pen to right justified the line
	    layoutX = ((float)(pen.x + (width / 2))) - (layout.getAdvance() / 2);
	    //--- Draw the line of text
	    layout.draw(parG, layoutX, (float)pen.y);
	    //--- Move the pen to the next position adding the descent and
	    //--- the leading of the font
	    pen.y += layout.getDescent() + layout.getLeading();
	}
    }

    private void renderBothCenterJustified(Graphics2D parG) {
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = getDrawingOrigin().getPoint2D();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	float layoutX;
	/**TRY TO RENDER VERTICALLY CENTERED*/
	double height = getSize().getHeight().getPoints();
	int i = 0;
	double px = 0.0;
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    i++;
	    //FontMetrics x = parG.getFontMetrics((Font)text.getIterator().getAttribute(TextAttribute.FONT));
	    //px += x.getHeight() + layout.getDescent() + layout.getLeading();
	    px += layout.getBounds().getHeight();
	}
	lineBreaker.setPosition(0);
	/**CREO QUE ESTA FORMULA ES LA CORRECTA*/
	pen.y = ((height / 2.0) + getPosition().getY().getPoints()) - (px / (2.0));
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent();
	    //--- Align the X pen to right justified the line
	    layoutX = ((float)(pen.x + (width / 2))) - (layout.getAdvance() / 2);
	    //--- Draw the line of text
	    layout.draw(parG, layoutX, (float)pen.y);
	    //--- Move the pen to the next position adding the descent and
	    //--- the leading of the font
	    pen.y += layout.getDescent() + layout.getLeading();
	}
    }

    /**
     * Method: renderRightJustified <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    private void renderRightJustified(Graphics2D parG) {
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = getDrawingOrigin().getPoint2D();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	float layoutX;
	//--- LineBreakMeasurer will wrap each line to correct length and
	//--- return it as a TextLayout object
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent();
	    //--- Align the X pen to right justified the line
	    layoutX = ((float)(pen.x + width)) - layout.getAdvance();
	    //--- Draw the line of text
	    layout.draw(parG, layoutX, (float)pen.y);
	    //--- Move the pen to the next position adding the descent and
	    //--- the leading of the font
	    pen.y += layout.getDescent() + layout.getLeading();
	}
    }

    /**
     * Method: renderLeftJustified <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    private void renderLeftJustified(Graphics2D parG) {
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = getDrawingOrigin().getPoint2D();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayout object
	TextLayout layout;
	//--- LineBreakMeasurer will wrap each line to correct length and
	//--- return it as a TextLayout object
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += layout.getAscent();
	    //--- Draw the line of text
	    layout.draw(parG, (float)pen.x, (float)pen.y);
	    //--- Move the pen to the next position adding the descent and
	    //--- the leading of the font
	    pen.y += layout.getDescent() + layout.getLeading();
	}
    }

    /**
     * Method: renderFullJustification <p>
     * 
     * @param parG a value of shapeTypeName Graphics2D
     */
    private void renderFullyJustified(Graphics2D parG) {
	//--- Create a point object to set the top left corner of the TextLayout object
	Point2D.Double pen = getDrawingOrigin().getPoint2D();
	//--- Set the width of the TextLayout box
	double width = getDrawingSize().getWidth().getPoints();
	//--- LineBreakMeasurer
	LineBreakMeasurer lineBreaker = new LineBreakMeasurer(text.getIterator(), new FontRenderContext(null, true, true));
	//--- Create the TextLayouts object
	TextLayout layout;
	TextLayout justifyLayout;
	//--- Create a Vector to temporaly store each line of text
	Vector lines = new Vector();
	//--- Get the output of the LineBreakMeasurer and store it in a Vector
	while ((layout = lineBreaker.nextLayout((float)width)) != null) {
	    lines.add(layout);
	}
	//--- Scan each line of the paragraph and justify it except for the last line
	for (int i = 0; i < lines.size(); i++) {
	    //--- Get the line from the vector
	    layout = (TextLayout)lines.get(i);
	    //--- Check for the last line. When found print it 
	    //--- without justification
	    if (i != lines.size() - 1)
		justifyLayout = layout.getJustifiedLayout((float)width);
	    else
		justifyLayout = layout;
	    //--- Align the Y pen to the ascend of the font, remember that
	    //--- the ascend is origin (0, 0) of a font. Refer to figure 1
	    pen.y += justifyLayout.getAscent();
	    //--- Draw the line of text
	    justifyLayout.draw(parG, (float)pen.x, (float)pen.y);
	    //--- Move the pen to the next position adding the descent and
	    //--- the leading of the font
	    pen.y += justifyLayout.getDescent() + justifyLayout.getLeading();
	}
    }

    /**
     * Method: setFont <p>
     * 
     * @param parFont a value of shapeTypeName Font
     */
    public void setFont(Font parFont) {
	if (parFont != null)
	    font = parFont;
    }

    /**
     * Method: getFont <p>
     * 
     * @return a value of shapeTypeName Font
     */
    public Font getFont() {
	return (font);
    }

    /**
     * Method: setTextColor <p>
     * 
     * @param parTextColor a value of shapeTypeName Color
     */
    public void setTextColor(Color parTextColor) {
	//--- Require 
	if (parTextColor != null) {
	    textColor = parTextColor;
	}
    }

    /**
     * Method: getTextColor <p>
     * 
     * @return a value of shapeTypeName Color
     */
    public Color getTextColor() {
	return (textColor);
    }

    public void setCenter(PFPoint _center) {
	center = _center;
	PFUnit width = getSize().getWidth();
	PFUnit height = getSize().getHeight();
	setPosition(new PFPoint(new PFmmUnit(_center.getX().getUnits() - (width.getUnits() / 2.0)), new PFmmUnit(_center.getY().getUnits() - (height.getUnits() / 2.0))));
    }

    public PFPoint getCenter() {
	return center;
    }

}
// PFParagraph
