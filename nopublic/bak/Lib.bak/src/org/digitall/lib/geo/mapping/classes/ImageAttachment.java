package org.digitall.lib.geo.mapping.classes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.io.Serializable;

import javax.imageio.ImageIO;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImagePreview;

public class ImageAttachment implements Serializable {

    private static final long serialVersionUID = 10000095L;
    
    private String filePath = "";
    private String fileName = "";
    private long fileLength = 0;
    private final int imageWidthLimit = 640;
    private final int imageHeightLimit = 480;
    private final long fileLengthLimit = 65536;
    private double angle = 0.0;
    private double scale = 1.0;
    private double minX = -1;
    private double minY = -1;
    private boolean on = true;
    private double maxX = 0.0;
    private double maxY = 0.0;
    private int controlPointSize = 8;

    public ImageAttachment() {

    }

    private boolean isAvailable(File _file) {
	return (_file.exists() && _file.isFile() && _file.canRead());
    }

    public BufferedImage getImage() {
	BufferedImage _image = null;
	try {
	    File _imageFile = new File(filePath + File.separator + fileName);
	    if (isAvailable(_imageFile)){
		fileLength = _imageFile.length();
	        System.out.println("Leyendo imagen del disco...");
		_image = ImageIO.read(_imageFile);
	        System.out.println("Imagen leída con éxito...");
		/*if (_imageFile.length() < fileLengthLimit) {
		    if ((_image.getWidth() <= imageWidthLimit) && (_image.getHeight() <= imageHeightLimit)) {
			//LibIMG.escalaImg(photo, lblPhoto);
		    } else {
			Advisor.messageBox("Tamaño de imagen no permitido. Las dimensiones máximas son " + imageWidthLimit + "x" + imageHeightLimit + " píxeles", "Aviso");
		    }
		} else {
		    Advisor.messageBox("Tamaño de archivo no permitido. El tamaño máximo es " + (fileLengthLimit / 1024.0) + " Kbytes", "Aviso");
		}*/
	    } else {
	    }
	} catch (Exception x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    _image = null;
	}
	return _image;
    }

    public boolean selectImageFile() {
	boolean returns = false;
	JFileChooser fc = new JFileChooser();
	/** REVISAR LA OBTENCION DE LASTPATH */
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fc.addChoosableFileFilter(new ImageFilter());
	fc.setAcceptAllFileFilterUsed(false);
	fc.setFileView(new ImageFileView());
	//Add the preview pane.
	fc.setAccessory(new ImagePreview(fc));
	fc.setMultiSelectionEnabled(false);
	fc.showOpenDialog(new JFrame());
	File _imageFile = fc.getSelectedFile();
	if (_imageFile != null) {
	    fileName = _imageFile.getName();
	    filePath = _imageFile.getPath().substring(0, _imageFile.getPath().length()-(fileName.length()+1));
	    if (isAvailable(_imageFile)){

	        BufferedImage _image = null;
	        try {
		    fileLength = _imageFile.length();
		    _image = ImageIO.read(_imageFile);
		    if (_imageFile.length() < fileLengthLimit) {
			if ((_image.getWidth() <= imageWidthLimit) && (_image.getHeight() <= imageHeightLimit)) {
			    returns = true;
			} else {
			    returns = Advisor.question("Aviso","Tamaño de imagen no permitido. Las dimensiones máximas son " + imageWidthLimit + "x" + imageHeightLimit + " píxeles\n¿Desea usarla de todos modos?");
			}
		    } else {
			returns = Advisor.question("Aviso", "Tamaño de archivo no permitido. El tamaño máximo es " + (fileLengthLimit / 1024.0) + " Kbytes\n¿Desea usarlo de todos modos?");
		    }
	        } catch (IOException x) {
	            Advisor.messageBox(x.getMessage(), "Error");
	        }
	    } else {
		Advisor.messageBox("El archivo no se puede leer", "Error");
	    }
	}


	return returns;
    }

    public void setAngle(double angle) {
	this.angle = angle;
    }

    public double getAngle() {
	return angle;
    }

    public void setScale(double scale) {
	this.scale = scale;
    }

    public double getScale() {
	return scale;
    }

    public void setMinX(double x) {
	this.minX = x;
    }

    public double getMinX() {
	return minX;
    }

    public void setMinY(double y) {
	this.minY = y;
    }

    public double getMinY() {
	return minY;
    }

    public void setOn(boolean on) {
	this.on = on;
    }

    public boolean isOn() {
	return on;
    }

    public void setSpaceBounds(Rectangle2D _bounds) {
	minX = _bounds.getMinX();
	minY = _bounds.getMinY();
	maxX = _bounds.getMaxX();
	maxY = _bounds.getMaxY();
    }

    
    public Point getCentroid(BasicDrawEngineConfig _engineConfig) {
	double _minX = _engineConfig.xtoPixel(minX);
	double _minY = _engineConfig.ytoPixel(minY);
	double _maxX = _minX + (int)(getImage().getWidth() * scale * _engineConfig.getDrawScale());
	double _maxY = _minY + (int)(getImage().getHeight() * scale * _engineConfig.getDrawScale());
	return new Point((int)((_minX + _maxX) / 2.0), (int)((_minY + _maxY) / 2.0));
    }
    
    public Rectangle2D getBounds(BasicDrawEngineConfig _engineConfig) {
	return new Rectangle2D.Double(_engineConfig.xtoPixel(minX),
	_engineConfig.ytoPixel(minY),
	(int)(getImage().getWidth() * scale * _engineConfig.getDrawScale()),
	(int)(getImage().getHeight() * scale * _engineConfig.getDrawScale())
	    );
    }
    
    public Rectangle2D[] getControlPoints(BasicDrawEngineConfig _engineConfig) {
	Rectangle2D _controlPoints[] = new Rectangle2D[4];
	double _minX = _engineConfig.xtoPixel(minX);
	double _minY = _engineConfig.ytoPixel(minY);
	double _maxX = _minX + (int)(getImage().getWidth() * scale * _engineConfig.getDrawScale());
	double _maxY = _minY + (int)(getImage().getHeight() * scale * _engineConfig.getDrawScale());
	_controlPoints[0] = new Rectangle2D.Double(_minX, _minY, controlPointSize, controlPointSize);
	_controlPoints[1] = new Rectangle2D.Double(_minX, _maxY - (controlPointSize-1), controlPointSize, controlPointSize);
	_controlPoints[2] = new Rectangle2D.Double(_maxX - (controlPointSize-1), _maxY - (controlPointSize-1), controlPointSize, controlPointSize);
	_controlPoints[3] = new Rectangle2D.Double(_maxX - (controlPointSize-1), _minY, controlPointSize, controlPointSize);
	return _controlPoints;
    }
    
}
