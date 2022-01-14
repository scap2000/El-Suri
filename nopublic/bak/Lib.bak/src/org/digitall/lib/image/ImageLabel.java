package org.digitall.lib.image;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.digitall.lib.components.basic.BasicLabel;

public class ImageLabel extends BasicLabel {

    private BufferedImage image;
    private double width = 320.0;
    private double height = 320.0;
    private int previousWidth = 0;
    private int previousHeight = 0;
    
    public ImageLabel() {
	this(320.0, 320.0);
    }
    
    public ImageLabel(double _width, double _height) {
	width = _width;
	height = _height;
	this.addComponentListener(new ComponentAdapter() {
		public void componentResized(ComponentEvent e) {
		    if (e.getID() == ComponentEvent.COMPONENT_RESIZED &&  (previousWidth != getWidth() && previousHeight != getHeight())) {
			previousWidth = getWidth();
			previousHeight = getHeight();
		        setImage(image);
		    }
		}
	});
    }

    public void setImage(BufferedImage _image) {
	//La imagen SIEMPRE se escalará a lo ancho
	//entre el parámetro seteado y su propia anchura
	//y se guarda en memoria con ese tamaño
	if (_image != null) {
	    image = LibIMG.scale((double)width/(double)_image.getWidth(), _image);
	    updateImage();
	} else {
	    image = null;
	    setIcon(null);
	}
    }

    public BufferedImage getImage() {
	return image;
    }
    
    private void updateImage() {
	//La imagen SIEMPRE se escalará al menor de los tamaños entre width y heigth
	//para asegurarse de mostrar la imagen completa, incluso dentro de un scrollpane
	//además de auto-ajustar la imagen en caso de un componente que cambia de tamaño
	if (getWidth() > 0) {
	    setIcon(new ImageIcon(LibIMG.scale(Math.min((double)getWidth() / (double)image.getWidth(),(double)getHeight() / (double)image.getHeight()), image)));
	} else {
	    setIcon(new ImageIcon(LibIMG.scale(1, image)));
	}
    }

}
