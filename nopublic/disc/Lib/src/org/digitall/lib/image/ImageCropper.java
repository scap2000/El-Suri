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
 * ImageCropper.java
 *
 * */
package org.digitall.lib.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

import javax.swing.JFileChooser;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.environment.Environment;

public class ImageCropper extends BasicLabel implements MouseListener, MouseMotionListener {

    private long fotoLengthLimit = 1024 * 1024; //1MB 
    private int fotoWidthLimit = 640; //px
    private int fotoHeightLimit = 480; //px
    protected Point2D[] mPoints = new Point2D.Double[5];
    protected int selectedPoint;
    private int side = 16;
    private double rectSide = 0;
    private Rectangle2D rect = new Rectangle2D.Double();
    private BufferedImage image = null;
    private BufferedImage originalImage = null;
    private Dimension size = null;
    private double factor = 1;
    private ImageLabel destination;
    private String fileName = "";

    public ImageCropper(ImageLabel _destination, boolean _autoSelect) {
	setSize(500, 500);
	setPreferredSize(new Dimension(500, 500));
	// rectangle
	selectedPoint = -1;
	// Listen for mouse events. 
	addMouseListener(this);
	addMouseMotionListener(this);
	setVisible(true);
	//rect.setRect(0, 0, Math.min(getWidth(), getHeight()), Math.min(getWidth(), getHeight()));
	setOpaque(false);
	repaint();
	destination = _destination;
	if (_autoSelect) {
	    readImageFromFile();
	}
    }

    public void setImage(BufferedImage _image) {
	image = _image;
	size = new Dimension(image.getWidth(), image.getHeight());
	factor = Math.max((double)size.width / (double)getWidth(), (double)size.height / (double)getHeight());
	if (factor < 1) {
	    factor = 1;
	}
	rect.setRect(10, 10, Math.min(size.width / factor, size.height / factor)-20, Math.min(size.width / factor, size.height / factor)-20);
	mPoints[0] = new Point2D.Double(rect.getX(), rect.getY());
	mPoints[1] = new Point2D.Double(rect.getX() + rect.getWidth(), rect.getY());
	mPoints[2] = new Point2D.Double(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
	mPoints[3] = new Point2D.Double(rect.getX(), rect.getHeight() + rect.getY());
	mPoints[4] = new Point2D.Double(rect.getCenterX(), rect.getCenterY());
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	// Draw the rectangle.
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if (image != null) {
	    g2.drawImage(image, (int)(size.width / factor), (int)(size.height / factor), this);
	    double x = 0;
	    double y = 0;
	    switch (selectedPoint) {
		case 0 :
		    //TopLeft
		    //mPoints[3].setLocation(mPoints[selectedPoint].getX(), mPoints[3].getY());
		    //mPoints[1].setLocation(mPoints[1].getX(), mPoints[selectedPoint].getY());
		    //No dejo que salga de la pantalla
		    x = mPoints[0].getX() > image.getMinX() ? mPoints[0].getX() : 0;
		    y = mPoints[0].getY() > image.getMinY() ? mPoints[0].getY() : 0;
		    //No dejo que sobrepase su punto opuesto
		    x = x > mPoints[2].getX() ? mPoints[2].getX() - 10 : x;
		    y = y > mPoints[2].getY() ? mPoints[2].getY() - 10 : y;
		    mPoints[0].setLocation(x, y);
		    rectSide = Math.min(Math.abs(mPoints[2].getX() - mPoints[0].getX()), Math.abs(mPoints[2].getY() - mPoints[0].getY()));
		    rect.setRect(mPoints[2].getX() - rectSide, mPoints[2].getY() - rectSide, rectSide, rectSide);
		    mPoints[0].setLocation(rect.getX(), rect.getY());
		    mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
		    mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
		    mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
		    break;
		case 1 :
		    //TopRight
		    /*mPoints[0].setLocation(mPoints[0].getX(), mPoints[selectedPoint].getY());
		    mPoints[2].setLocation(mPoints[selectedPoint].getX(), mPoints[2].getY());*/
		    //No dejo que salga de la pantalla
		    x = mPoints[1].getX() < getWidth() ? mPoints[1].getX() : getWidth();
		    y = mPoints[1].getY() < 0 ? 0 : mPoints[1].getY();
		    //No dejo que sobrepase su punto opuesto
		    x = x < mPoints[3].getX() ? mPoints[3].getX() + 10 : x;
		    y = y > mPoints[3].getY() ? mPoints[3].getY() - 10 : y;
		    mPoints[1].setLocation(x, y);
		    rectSide = Math.min(Math.abs(mPoints[1].getX() - mPoints[3].getX()), Math.abs(mPoints[1].getY() - mPoints[3].getY()));
		    rect.setRect(mPoints[3].getX(), mPoints[3].getY() - rectSide, rectSide, rectSide);
		    mPoints[0].setLocation(rect.getX(), rect.getY());
		    mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
		    mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
		    mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
		    break;
		case 2 :
		    //BottomRight
		    /*mPoints[1].setLocation(mPoints[selectedPoint].getX(), mPoints[1].getY());
		    mPoints[3].setLocation(mPoints[3].getX(), mPoints[selectedPoint].getY());*/
		    //No dejo que salga de la pantalla
		    x = mPoints[2].getX() < getWidth() ? mPoints[2].getX() : getWidth();
		    y = mPoints[2].getY() < getHeight() ? mPoints[2].getY() : getHeight();
		    //No dejo que sobrepase su punto opuesto
		    x = x < mPoints[0].getX() ? mPoints[0].getX() + 10 : x;
		    y = y < mPoints[0].getY() ? mPoints[0].getY() + 10 : y;
		    mPoints[2].setLocation(x, y);
		    rectSide = Math.min(Math.abs(mPoints[2].getX() - mPoints[0].getX()), Math.abs(mPoints[2].getY() - mPoints[0].getY()));
		    rect.setRect(mPoints[0].getX(), mPoints[0].getY(), rectSide, rectSide);
		    mPoints[0].setLocation(rect.getX(), rect.getY());
		    mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
		    mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
		    mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
		    break;
		case 3 :
		    //BottomLeft
		    /*mPoints[2].setLocation(mPoints[2].getX(), mPoints[selectedPoint].getY());
		    mPoints[0].setLocation(mPoints[selectedPoint].getX(), mPoints[0].getY());*/
		    //No dejo que salga de la pantalla
		    x = mPoints[3].getX() > 0 ? mPoints[3].getX() : 0;
		    y = mPoints[3].getY() < getHeight() ? mPoints[3].getY() : getHeight();
		    //No dejo que sobrepase su punto opuesto
		    x = x > mPoints[1].getX() ? mPoints[1].getX() - 10 : x;
		    y = y < mPoints[1].getY() ? mPoints[1].getY() + 10 : y;
		    mPoints[3].setLocation(x, y);
		    rectSide = Math.min(Math.abs(mPoints[1].getX() - mPoints[3].getX()), Math.abs(mPoints[1].getY() - mPoints[3].getY()));
		    rect.setRect(mPoints[1].getX() - rectSide, mPoints[1].getY(), rectSide, rectSide);
		    mPoints[0].setLocation(rect.getX(), rect.getY());
		    mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
		    mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
		    mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
		    break;
		    /*default :
		//rectSide = Math.abs(Math.max(mPoints[2].getX() - mPoints[0].getX(), mPoints[2].getY() - mPoints[0].getY()));
		rect.setRect(mPoints[2].getX() - rectSide, mPoints[2].getY() - rectSide, rectSide, rectSide);
		break;*/
		case 4 :
		    //BottomLeft
		    /*mPoints[2].setLocation(mPoints[2].getX(), mPoints[selectedPoint].getY());
		    mPoints[0].setLocation(mPoints[selectedPoint].getX(), mPoints[0].ogetY());*/
		    //No dejo que salga de la pantalla
		     rectSide = Math.min(Math.abs(mPoints[1].getX() - mPoints[3].getX()), Math.abs(mPoints[1].getY() - mPoints[3].getY()));
		    rect.setRect(mPoints[4].getX() - rectSide / 2, mPoints[4].getY() - rectSide / 2, rectSide, rectSide);
		    mPoints[0].setLocation(rect.getX(), rect.getY());
		    mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
		    mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
		    mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
		    x = mPoints[0].getX() > image.getMinX() ? mPoints[0].getX() : image.getMinX();
		    y = mPoints[0].getY() > image.getMinY() ? mPoints[0].getY() : image.getMinY();
		    mPoints[0].setLocation(x, y);
		    x = mPoints[1].getX() < getWidth() ? mPoints[1].getX() : getWidth();
		    y = mPoints[1].getY() < 0 ? 0 : mPoints[1].getY();
		    mPoints[1].setLocation(x, y);
		    x = mPoints[2].getX() < getWidth() ? mPoints[2].getX() : getWidth();
		    y = mPoints[2].getY() < getHeight() ? mPoints[2].getY() : getHeight();
		    mPoints[2].setLocation(x, y);
		    x = mPoints[3].getX() > 0 ? mPoints[3].getX() : 0;
		    y = mPoints[3].getY() < getHeight() ? mPoints[3].getY() : getHeight();
		    mPoints[3].setLocation(x, y);
		    break;
	    }
	    mPoints[4] = new Point2D.Double(rect.getCenterX(), rect.getCenterY());
	    g2.setPaint(Color.black);
	    g2.draw(rect);
	    for (int i = 0; i < mPoints.length; i++) {
		// If the point is selected, use the selected color. 
		if (i == selectedPoint)
		    g2.setPaint(Color.red);
		else
		    g2.setPaint(Color.blue);
		// Draw the point. 
		g2.fill(getControlPoint(mPoints[i]));
	    }
	    crop();
	}
    }

    protected Shape getControlPoint(Point2D p) {
	// Create a small square around the given point. 
	return new Rectangle2D.Double(p.getX() - side / 2, p.getY() - side / 2, side, side);
    }

    public void mouseClicked(MouseEvent me) {
	if (me.getClickCount() == 1 && me.getButton() == MouseEvent.BUTTON3) {
	    readImageFromFile();
	}
    }

    public void readImageFromFile() {
	try {
	    JFileChooser fc = new JFileChooser();
	    if (!Environment.cfg.getProperty("pictures").equalsIgnoreCase(ConfigFile.nullProperty))  {
	        fc.setCurrentDirectory(new File(Environment.cfg.getProperty("pictures")));
	    }
	    
	    /** REVISAR LA OBTENCION DE LASTPATH */
	    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    fc.addChoosableFileFilter(new ImageFilter());
	    fc.setAcceptAllFileFilterUsed(false);
	    //Add custom icons for file types.
	    fc.setFileView(new ImageFileView());
	    //Add the preview pane.
	    fc.setAccessory(new ImagePreview(fc));
	    fc.setMultiSelectionEnabled(false);
	    // Show open dialog; this method does not return until the dialog is closed
	    fc.showOpenDialog(this);
	    File fotoFile = fc.getSelectedFile();
	    if (fotoFile != null) {
		Environment.cfg.setProperty("pictures", fotoFile.getPath().replaceAll(fotoFile.getName(),""));
		//LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
		if (fotoFile.length() < fotoLengthLimit) {
		    //BufferedImage img = ImageIO.read(fotoFile);
		    originalImage = ImageIO.read(fotoFile);
		    BufferedImage img = originalImage.getSubimage(0, 0, originalImage.getWidth(), originalImage.getHeight());
		    //System.out.println("Width, height: " + img.getWidth() + "," + img.getHeight());
		    if ((img.getWidth() <= fotoWidthLimit) && (img.getHeight() <= fotoHeightLimit)) {
			BufferedImage img1 = LibIMG.scale((double)getWidth() / (double)img.getWidth(), img);
			setSize(img1.getWidth(this), img1.getHeight(this));
			setIcon(new ImageIcon(img1));
			setImage(img1);
		        fileName = fotoFile.getName();
		    } else {
			Advisor.messageBox("Resolución de foto no permitido. No puede superar los " + fotoWidthLimit + "x" + fotoHeightLimit + " píxels", "Advertencia");
		        fileName = "";
		    }
		} else {
		    Advisor.messageBox("Tamaño de archivo no permitido. No puede superar los " + (fotoLengthLimit / 1024.0) + " KBytes", "Advertencia");
		    fileName = "";
		}
	    }
	} catch (IOException x) {
	    x.printStackTrace();
	}
    }

    public void mousePressed(MouseEvent me) {
	selectedPoint = -1;
	for (int i = 0; i < mPoints.length; i++) {
	    Shape s = getControlPoint(mPoints[i]);
	    if (s.contains(me.getPoint())) {
		selectedPoint = i;
		break;
	    }
	}
	repaint();
    }

    public void mouseReleased(MouseEvent me) {
	selectedPoint = -1;
	mPoints[0].setLocation(rect.getX(), rect.getY());
	mPoints[1].setLocation(rect.getX() + rect.getWidth(), rect.getY());
	mPoints[2].setLocation(rect.getX() + rect.getWidth(), rect.getHeight() + rect.getY());
	mPoints[3].setLocation(rect.getX(), rect.getHeight() + rect.getY());
	mPoints[4].setLocation(rect.getCenterX(), rect.getCenterY());
    }

    public void mouseMoved(MouseEvent me) {

    }

    public void mouseDragged(MouseEvent me) {
	if (selectedPoint != -1) {
	    mPoints[selectedPoint].setLocation(me.getPoint());
	    repaint();
	}
    }

    public void mouseEntered(MouseEvent me) {

    }

    public void mouseExited(MouseEvent me) {

    }

    public void crop() {
	try {
	    //setImage(createImage(new FilteredImageSource(image.getSource(), new CropImageFilter((int)rect.getX(),(int)rect.getX(),(int)rect.getWidth(),(int)rect.getHeight()))));
	    //setImage(image.getSubimage((int)rect.getX(),(int)rect.getX(),(int)rect.getWidth(),(int)rect.getHeight()));
	    int w = (int)rect.getWidth();
	    int h = (int)rect.getHeight();
	    double x0 = (getWidth() - size.width) / factor;
	    double y0 = (getHeight() - size.height) / factor;
	    double x = (int)rect.getX() - x0;
	    double y = (int)rect.getY() - y0;
	    /*BufferedImage img = image.getSubimage((int)x, (int)y, w, h);
	    BufferedImage img1 = LibIMG.scale((double)destination.getWidth() / (double)img.getWidth(), img);
	    destination.setImage(img);
	    destination.setIcon(new ImageIcon(img1));
	    //setImage(image.getSubimage((int)x, (int)y, w, h));
	    //setIcon(new ImageIcon(image));*/
	    //BufferedImage img = ;
	    //BufferedImage img1 = LibIMG.scale((double)destination.getWidth() / (double)img.getWidth(), img);
	    //destination.setImage(image.getSubimage((int)x, (int)y, w, h));
	    double _factor = size.getWidth()/(double)originalImage.getWidth();
	    destination.setImage(originalImage.getSubimage((int)(x/_factor), (int)(y/_factor), (int)(w/_factor), (int)(h/_factor)));
	    //System.out.println(rect);
	    //System.out.println(_factor);
	    //destination.setIcon(new ImageIcon(img1));
	} catch (Exception e) {
	    //e.printStackTrace();
	}
    }

    public String getFileName() {
	return fileName;
    }

    public void setFotoLengthLimit(long fotoLengthLimit) {
	this.fotoLengthLimit = fotoLengthLimit;
    }

    public long getFotoLengthLimit() {
	return fotoLengthLimit;
    }

    public void setFotoWidthLimit(int fotoWidthLimit) {
	this.fotoWidthLimit = fotoWidthLimit;
    }

    public int getFotoWidthLimit() {
	return fotoWidthLimit;
    }

    public void setFotoHeightLimit(int fotoHeightLimit) {
	this.fotoHeightLimit = fotoHeightLimit;
    }

    public int getFotoHeightLimit() {
	return fotoHeightLimit;
    }
}
