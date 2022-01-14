package org.digitall.lib.image;

import com.sun.image.codec.jpeg.ImageFormatException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.sql.LibSQL;

public abstract class LibIMG {

    /**
     * * ESTE METODO SE REPITE EN EL FORMULARIO 'PERSONAS.JAVA'
     * @param ImgDestino
     * @param ImgOrigen
     */
    public static void escalaImg(BufferedImage ImgOrigen, BasicLabel ImgDestino) {
	if (ImgOrigen != null) {
	    Image img1 = null;
	    if (ImgOrigen.getWidth() > ImgDestino.getWidth()) {
		img1 = ImgOrigen.getScaledInstance(ImgDestino.getWidth(), ImgDestino.getWidth() * ImgOrigen.getHeight() / ImgOrigen.getWidth(), Image.SCALE_SMOOTH);
	    } else {
		img1 = ImgOrigen;
	    }
	    if (img1 != null) {
		ImgDestino.setIcon(new ImageIcon(img1));
	    } else
		ImgDestino.setIcon(null);
	}
    }

    /**
     *
     * @param imgBytes
     * @param nombreImg
     * @return
     */
    public static BufferedImage getImageFromBytes(byte[] imgBytes, String nombreImg) {
	File file = new File(nombreImg + ".jpg");
	try {
	    FileOutputStream fis = new FileOutputStream(file);
	    AffineTransform transform = new AffineTransform();
	    //AffineTransformOp scaleimg = new AffineTransformOp(transform, null);
	    BufferedOutputStream output = new BufferedOutputStream(fis);
	    output.write(imgBytes);
	    output.close();
	    BufferedImage imagen = ImageIO.read(file);
	    return imagen;
	} catch (Exception x) {
	    System.out.println("error!");
	    x.printStackTrace();
	    return null;
	}
    }

    public static byte[] getBytesFromImage(BufferedImage img) {
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	try {
	    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
	    encoder.encode(img);
	} catch (ImageFormatException e) {
	    Advisor.printException(e);
	} catch (IOException e) {
	    Advisor.printException(e);
	}
	return os.toByteArray();
    }

    public static BufferedImage getImageFromBytes(byte[] imgBytes) {
	try {
	    ByteArrayInputStream input = new ByteArrayInputStream(imgBytes);
	    BufferedImage image = ImageIO.read(input);
	    return image;
	} catch (Exception x) {
	    return null;
	}
    }

    public static BufferedImage scale(double scale, BufferedImage srcImg) {
	if (scale == 1) {
	    return srcImg;
	}
	AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale), null);
	return op.filter(srcImg, null);
    }

    public static BufferedImage getImage(String table, String imageField, String condition) {
	System.out.println("Getting image from " + table + " at field " + imageField + " " + condition);
	byte[] _result = null;
	    try {
		LibSQL.getConnection().setAutoCommit(false);
		Statement ps = LibSQL.getConnection().createStatement();
		ResultSet rs = ps.executeQuery("SELECT " + imageField + " FROM " + table + " " + condition);
		try {
		    if (rs.next()) {
		    _result = rs.getBytes(imageField);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		ps.close();
		LibSQL.getConnection().setAutoCommit(true);
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	return getImageFromBytes(_result);
    }

    public static boolean saveImage(BufferedImage image, String table, String imageField, String condition) {
	    if (image != null) {
		try {
		    System.out.println("Saving image to " + table + " at field " + imageField + " " + condition);
		    byte[] imageBytes = getBytesFromImage(image);
		    ByteArrayInputStream fis = new ByteArrayInputStream(imageBytes);
		    PreparedStatement ps = LibSQL.getConnection().prepareStatement("UPDATE " + table + " SET " + imageField + " =? " + condition);
		    ps.setBinaryStream(1, fis, (int)imageBytes.length);
		    if (ps.executeUpdate() != 0) {
			fis.close();
			return true;
		    } else {
			fis.close();
			return false;
		    }
		} catch (Exception x) {
		    x.printStackTrace();
		    return false;
		}
	    } else {
		Advisor.messageBox("No se pudo leer el archivo", "Error");
		return false;
	    }
    }

    public static String getBase64FromImage(BufferedImage _image) {
        return new String(Base64Coder.encode(LibIMG.getBytesFromImage(_image)));
    }

    public static BufferedImage getImageFromBase64(String _base64) {
        return LibIMG.getImageFromBytes(Base64Coder.decode(_base64).getBytes());
    }
}
