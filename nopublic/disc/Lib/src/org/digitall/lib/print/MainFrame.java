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
 * MainFrame.java
 *
 * */
package org.digitall.lib.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;

import java.text.AttributedString;

import javax.swing.JComboBox;

/**
 * MainFrame.java
 *
 *
 * Created: Wed Aug 16 13:34:37 2000
 *
 * @author <a href="mailto: "</a>
 * @version
 */

/**
 * MainFrame.java
 *
 *
 * Created: Wed Aug 16 13:34:37 2000
 *
 * @author <a href="mailto: "</a>
 * @version
 */
public class MainFrame {

    public MainFrame() {
	super();
	//      init ();
	//testUnits ();
	init2();
    }

    private void init2() {
	PFDocument document = new PFDocument();
	PFPage page = new PFPage();
	PFFrame rectangle = new PFFrame();
	PFParagraph header = new PFParagraph();
	PFFrame pageFrame = new PFFrame();
	PFFrame footer = new PFFrame();
	PFFrame testRectangle = new PFFrame();
	PFFrame childRectangle = new PFFrame();
	PFPageFormat pageFormat = page.getPageFormat();
	pageFormat.setPageOrientation(PFPageFormat.PORTRAIT);
	pageFormat.setLeftMargin(new PFInchUnit(1.5));
	pageFormat.setRightMargin(new PFInchUnit(0.5));
	page.setPageFormat(pageFormat);
	page.add(pageFrame);
	pageFrame.setRelativeMode(false);
	pageFrame.setPosition(new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0)));
	pageFrame.setSize(page.getPageFormat().getPageSize());
	pageFrame.setLineColor(Color.blue);
	header.setText("--- Header Test --- Page: <<pageNo>>");
	header.setPosition(new PFPoint(new PFInchUnit(0.0), new PFInchUnit(0.0)));
	header.setHorizontalFill(true);
	header.setVerticalFill(true);
	header.setHorizontalAlignment(PFParagraph.CENTER_JUSTIFIED);
	header.setVerticalSticky(PFPrintObject.STICKY_CENTER);
	header.setTextColor(Color.white);
	//header.setTopMargin (new PFInchUnit (1.5));
	//header.add (rectangle);
	header.setHeight(new PFInchUnit(0.5));
	//page.add (header);
	footer.setHorizontalFill(true);
	footer.setHeight(new PFInchUnit(0.5));
	//footer.setFillColor (Color.black);
	page.setFooter(footer);
	rectangle.setHeight(new PFInchUnit(1.0));
	rectangle.setHorizontalFill(true);
	rectangle.setRightMargin(new PFInchUnit(2.0));
	rectangle.add(header);
	//rectangle.setFillColor (Color.lightGray);
	rectangle.setLineColor(Color.red);
	page.setHeader(rectangle);
	testRectangle.setSize(new PFSize(new PFInchUnit(3.0), new PFInchUnit(3.0)));
	testRectangle.setHorizontalSticky(PFPrintObject.STICKY_CENTER);
	childRectangle.setRightMargin(new PFInchUnit(0.25));
	page.add(testRectangle);
	childRectangle.setHorizontalSticky(PFPrintObject.STICKY_RIGHT);
	childRectangle.setVerticalSticky(PFPrintObject.STICKY_TOP);
	childRectangle.setLineColor(Color.blue);
	testRectangle.add(childRectangle);
	String text = new String();
	text += "Manipulating raw fonts would be too complicated to render paragraphs of ";
	text += "text. Trying to write an algorithm to fully justify text using ";
	text += "proportional fonts is not trivial. Adding support for international ";
	text += "characters adds to the complexity. That's why we will use the ";
	text += "<code>TextLayout</code> and the <code>LineBreakMeasurer<code> class to ";
	text += "render text. The <code>TextLayout<code> class offers a lot of ";
	text += "functionality to render high quality text. This class is capable of ";
	text += "rendering bidirectional text such as Japanese text where the alignment ";
	text += "is from right to left instead of the North American style which is left ";
	text += "to right. The <code>TextLayout<code> class offers some additional ";
	text += "functionalities that we will not use in the course of this ";
	text += "series. Features such as text input, caret positionning and hit ";
	text += "testing will not be of much use when printing documents, but it's good ";
	text += "to know that this functionality exists. ";
	String text1 = new String();
	text1 += "The <code>TextLayout</code> class will be used to layout ";
	text1 += "paragraphs. The <code>TextLayout</code> class does not work alone. To ";
	text1 += "layout text within a specified width it needs the help of the ";
	text1 += "<code>LineBreakMeasurer</code> class. This class will wrap a string of ";
	text1 += "text to fit a predefined width. Since it's a multi-lingual class, it ";
	text1 += "knows exactly where to break a line of text according to the rules ";
	text1 += "of the language.  Then again the <code>LineBreakMeasurer</code> does ";
	text1 += "not work alone. It needs information from the ";
	text1 += "<code>FontRenderContext</code> class. This class' main function is to ";
	text1 += "return accurate font metrics. To measure text effectively, this class ";
	text1 += "needs to know the rendering hints for the targeted device and the font ";
	text1 += "type being used. ";
	text1 += text;
	text1 += text;
	text1 += text;
	text1 += text;
	text1 += text;
	text1 += text;
	text1 += text;
	//PFParagraph paragraph = new PFParagraph ();
	PFParagraph paragraph1 = new PFParagraph();
	//page.add (paragraph);
	//paragraph.setText (text);
	//paragraph.setHorizontalAlignment (PFParagraph.FULL_JUSTIFIED);
	//paragraph.setSize (new PFSize (page.getPrintableAreaSize ().getWidth (), new PFInchUnit (6)));
	page.add(paragraph1);
	AttributedString as = new AttributedString(text1);
	as.addAttribute(TextAttribute.FONT, new Font("serif", Font.PLAIN, 18), 10, 30);
	as.addAttribute(TextAttribute.FOREGROUND, Color.red, 50, 80);
	paragraph1.setText(new PFMacro().expandMacro(as));
	paragraph1.setHorizontalAlignment(PFParagraph.FULL_JUSTIFIED);
	paragraph1.setSize(new PFSize(page.getPrintableAreaSize().getWidth(), new PFInchUnit(6)));
	//paragraph1.setPosition (paragraph.getNextParagraphPosition ());
	PFLine line = new PFLine();
	line.setTickness(30);
	line.setCap(BasicStroke.CAP_ROUND);
	line.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(1.0)));
	line.setEndPoint(new PFPoint(new PFInchUnit(5.0), new PFInchUnit(7.0)));
	//page.add (line);
	PFPage page2 = new PFPage();
	//document.addPage (page2);
	PFVisualComponent vc = new PFVisualComponent();
	JComboBox c = new JComboBox(new String[] { "aaaa", "bbbbb", "cccc", "dddd", "eeee", "ffff", "ggggg" });
	//c.setBackground (Color.gray);
	vc.setComponent(c);
	vc.setSize(new PFSize(new PFInchUnit(3.0), new PFInchUnit(0.75)));
	//     vc.setPosition (new PFPoint (new PFInchUnit (1.0), new PFInchUnit (1.0)));
	page2.add(vc);
	/*int linesPerPage = ((int)pageHeight)/lineHeight);
	int numBreaks = (textLines.length-1)/linesPerPage;
	int[] pageBreaks = new int[numBreaks];
	for (int b=0; b < numBreaks; b++) {
	    pageBreaks[b] = (b+1)*linesPerPage; 
	}*/	

	document.showPageDialog(true);
	document.addPage(page);

	//Prueba para pageBreaks
	double pagePrintableHeight = page.getPageFormat().getPageFormat().getImageableHeight();
	double pageHeight = page.getPrintableContentSize();
	double paragraphHeight = paragraph1.getTextHeight().getPoints();

	System.out.println(pagePrintableHeight);
	System.out.println(pageHeight);
	System.out.println(paragraphHeight);

	document.printPreview();
    }

    private void init() {
	PFDocument document = new PFDocument();
	PFPage page = new PFPage();
	PFPage page2 = new PFPage();
	PFPage page3 = new PFPage();
	PFParagraph paragraph = new PFParagraph();
	PFFrame rectangle = new PFFrame();
	PFImage image = new PFImage();
	PFLine line = new PFLine();
	PFParagraph paragraph2 = new PFParagraph();
	PFFrame rectangle2 = new PFFrame();
	PFVisualComponent component = new PFVisualComponent();
	//--- Create a string and assign the text
	String text = new String();
	text += "Manipulating raw fonts would be too complicated to render paragraphs of ";
	text += "text. Trying to write an algorithm to fully justify text using ";
	text += "proportional fonts is not trivial. Adding support for international ";
	text += "characters adds to the complexity. That's why we will use the ";
	text += "<code>TextLayout</code> and the <code>LineBreakMeasurer<code> class to ";
	text += "render text. The <code>TextLayout<code> class offers a lot of ";
	text += "functionality to render high quality text. This class is capable of ";
	text += "rendering bidirectional text such as Japanese text where the alignment ";
	text += "is from right to left instead of the North American style which is left ";
	text += "to right. The <code>TextLayout<code> class offers some additional ";
	text += "functionalities that we will not use in the course of this ";
	text += "series. Features such as text input, caret positionning and hit ";
	text += "testing will not be of much use when printing documents, but it's good ";
	text += "to know that this functionality exists. ";
	text += "The <code>TextLayout</code> class will be used to layout ";
	text += "paragraphs. The <code>TextLayout</code> class does not work alone. To ";
	text += "layout text within a specified width it needs the help of the ";
	text += "<code>LineBreakMeasurer</code> class. This class will wrap a string of ";
	text += "text to fit a predefined width. Since it's a multi-lingual class, it ";
	text += "knows exactly where to break a line of text according to the rules ";
	text += "of the language.  Then again the <code>LineBreakMeasurer</code> does ";
	text += "not work alone. It needs information from the ";
	text += "<code>FontRenderContext</code> class. This class' main function is to ";
	text += "return accurate font metrics. To measure text effectively, this class ";
	text += "needs to know the rendering hints for the targeted device and the font ";
	text += "type being used. ";
	paragraph.setText(text);
	paragraph.setHorizontalAlignment(PFParagraph.FULL_JUSTIFIED);
	paragraph.setSize(new PFSize(new PFInchUnit(6), new PFInchUnit(6)));
	rectangle.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(1.0)));
	rectangle.setSize(page.getPageFormat().getPageSize());
	rectangle.setVerticalSticky(PFPrintObject.STICKY_TOP);
	rectangle.setHorizontalSticky(PFPrintObject.STICKY_LEFT);
	rectangle.setTickness(1);
	paragraph.add(rectangle);
	page.add(paragraph);
	image.setURL("file:///c:/softdev/java/articles/javaworld/printing/part_2/ss2.jpg");
	image.setSize(new PFSize(new PFInchUnit(6), new PFInchUnit(6)));
	image.setVerticalSticky(PFPrintObject.STICKY_CENTER);
	image.setHorizontalSticky(PFPrintObject.STICKY_CENTER);
	page3.add(image);
	/*
      JTree combo = new JTree ();
      
      component.setComponent (combo);
      component.setSize (new PFSize (new PFInchUnit (6), new PFInchUnit (6)));
      component.setVerticalSticky (PFPrintObject.STICKY_CENTER);
      component.setHorizontalSticky (PFPrintObject.STICKY_CENTER);
      page.add (component);
      */
	//      page.add (line);
	paragraph2.setText(text);
	paragraph2.setHorizontalAlignment(PFParagraph.LEFT_JUSTIFIED);
	rectangle2.setPosition(paragraph2.getPosition());
	rectangle2.setSize(new PFSize(new PFInchUnit(1), new PFInchUnit(1)));
	rectangle2.setVerticalSticky(PFPrintObject.STICKY_CENTER);
	rectangle2.setTickness(8);
	page2.add(paragraph2);
	paragraph2.add(rectangle2);
	document.addPage(page);
	document.addPage(page2);
	document.addPage(page3);
	document.showPrintDialog(false);
	document.printPreview();
    }

}// MainFrame









