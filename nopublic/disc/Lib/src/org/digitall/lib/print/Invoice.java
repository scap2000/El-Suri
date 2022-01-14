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
 * Invoice.java
 *
 * */
package org.digitall.lib.print;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;

import java.text.AttributedString;

import org.digitall.lib.environment.Environment;

public class Invoice {

    //WATCH OUT!!!
    //Minimum paper sizes!!!
    //HP1220C from 4x6" to 13x50"
    //HP1220C from 101.6x152.4mm to 330.2x1270mm
    //HP1018 from 3x5" to 8.5x14"
    //HP1018 from 76x127mm to 216x356mm
    private double width = 210.0;
    private double height = 297.0;
    private boolean rotate = false;
    public Font small = new Font("serif", Font.PLAIN, 8);
    public Font normal = new Font("serif", Font.PLAIN, 10);
    public Font large = new Font("serif", Font.PLAIN, 14);
    private InvoiceFieldsList fields = new InvoiceFieldsList();
    private boolean testing = false;
    private int idBookKeepingEntry = -1;

    public Invoice() {
    }

    public void doInvoice() {
	//Document
	PFDocument document = new PFDocument();
	document.setPreferredSize(new PFSize(new PFmmUnit(width), new PFmmUnit(height)));
	//Page & format
	PFPage page = new PFPage();
	PFPageFormat pageFormat = page.getPageFormat();
	//WATCH OUT!!!
	//Minimum paper sizes!!!
	//HP1220C from 4x6" to 13x50"
	//HP1220C from 101.6x152.4mm to 330.2x1270mm
	//HP1018 from 3x5" to 8.5x14"
	//HP1018 from 76x127mm to 216x356mm
	//pageFormat.setPageSize(new PFSize(new PFmmUnit(210), new PFmmUnit(297)));
	//pageFormat.setPageOrientation(PFPageFormat.PORTRAIT);
	//pageFormat.setLeftMargin(new PFmmUnit(0.0));
	pageFormat.setLeftMargin(new PFmmUnit(0.0));
	pageFormat.setRightMargin(new PFmmUnit(0.0));
	pageFormat.setTopMargin(new PFmmUnit(0.0));
	pageFormat.setBottomMargin(new PFmmUnit(0.0));
	page.setPageFormat(pageFormat);
	//ORIGINAL
	PFFrame testFrame = new PFFrame();
	testFrame.setPosition(new PFPoint(new PFmmUnit(0.0), new PFmmUnit(0.0)));
	testFrame.setSize(new PFSize(new PFmmUnit(width), new PFmmUnit(height)));
	//testFrame.setLineColor(Color.black);
	//page.add(testFrame);
	if (rotate) {
	    page.setRotation(-90.0);
	    String xTranslation = Environment.cfg.getProperty("xTranslation");
	    String yTranslation = Environment.cfg.getProperty("yTranslation");	    
	    int xTrans = xTranslation.equals("N/A")?108:Integer.parseInt(xTranslation);
	    int yTrans = yTranslation.equals("N/A")?197:Integer.parseInt(yTranslation);
	    page.setXtranslation(-new PFmmUnit(width).getPoints()-xTrans);
	    page.setYtranslation(yTrans);
	}
	//FONTS
	//Paragraphs
	for (int i = 0; i < fields.size(); i++) {
	    //Paragraph's sizes
	    InvoiceField field = (InvoiceField)fields.elementAt(i);
	    if (testing) {
		System.out.println(field.getName() + ": " + field.getText());
	    }
	    //pars.elementAt(i).setSize(new PFSize(new PFmmUnit(28), new PFmmUnit(5.5)));
	    //Paragraph's fonts and texts //Override paragraph's AttributedString
	    AttributedString _attStr = new AttributedString(field.getText());
	    _attStr.addAttribute(TextAttribute.FONT, field.getFont());
	    //Paragraph's colors
	    _attStr.addAttribute(TextAttribute.FOREGROUND, field.getForeground());
	    _attStr.addAttribute(TextAttribute.BACKGROUND, field.getBackground());

	    PFParagraph _original = new PFParagraph(_attStr);
	    _original.setText(new PFMacro().expandMacro(_attStr));
	    //Paragraph's centers
	    _original.setPosition(field.getPosition());
	    _original.setSize(field.getSize());
	    
	    _original.setCenter(field.getCenter());

	    PFParagraph _duplicado = new PFParagraph(_attStr);
	    _duplicado.setText(new PFMacro().expandMacro(_attStr));
	    //Paragraph's centers
	     field.setOffset(true);
	    _duplicado.setPosition(field.getPosition());
	    _duplicado.setSize(field.getSize());
	    _duplicado.setCenter(field.getCenter());
	    
	    //Paragraph's alignments
	    _original.setHorizontalAlignment(field.getHorizontalAlignment());
	    _duplicado.setHorizontalAlignment(field.getHorizontalAlignment());
	    //Adding paragraphs to page
	    page.add(_original);
	    page.add(_duplicado);
	}
	//End of page
	//document.showPageDialog(true);
	document.showPrintDialog(false);
	document.addPage(page);
	if (testing) {
	    document.printPreview();
	} else {
	    document.print();
	}
    }

    public void setFields(InvoiceFieldsList _fields) {
	fields = _fields;
    }

    public void setRotation(boolean _rotate) {
	rotate = _rotate;
    }

    public void setDocumentSize(double _width, double _height) {
	width = _width;
	height = _height;
    }

    public int saveData() {
	return -1;
    }

    public void setIDBookKeepingEntry(int _idBookKeepingEntry) {
	this.idBookKeepingEntry = _idBookKeepingEntry;
    }

    public int getIDBookKeepingEntry() {
	return idBookKeepingEntry;
    }

    public void setTesting(boolean _testing) {
	testing = _testing;
    }

}
