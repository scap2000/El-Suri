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
