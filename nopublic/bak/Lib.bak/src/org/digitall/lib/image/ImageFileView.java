package org.digitall.lib.image;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class ImageFileView extends FileView {

    ImageIcon jpgIcon = AlbumUtils.createImageIcon("iconos/images/jpgIcon.gif");
    ImageIcon gifIcon = AlbumUtils.createImageIcon("iconos/images/gifIcon.gif");
    ImageIcon tiffIcon = AlbumUtils.createImageIcon("iconos/images/tiffIcon.gif");
    ImageIcon pngIcon = AlbumUtils.createImageIcon("iconos/images/pngIcon.png");

    public String getName(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public String getDescription(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
	return null;
	//let the L&F FileView figure this out
    }

    public String getTypeDescription(File f) {
	String extension = AlbumUtils.getExtension(f);
	String type = null;
	if (extension != null) {
	    if (extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg)) {
		type = "JPEG Image";
	    } else if (extension.equals(AlbumUtils.gif)) {
		type = "GIF Image";
	    } else if (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif)) {
		type = "TIFF Image";
	    } else if (extension.equals(AlbumUtils.png)) {
		type = "PNG Image";
	    }
	}
	return type;
    }

    public Icon getIcon(File f) {
	String extension = AlbumUtils.getExtension(f);
	Icon icon = null;
	if (extension != null) {
	    if (extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg)) {
		icon = jpgIcon;
	    } else if (extension.equals(AlbumUtils.gif)) {
		icon = gifIcon;
	    } else if (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif)) {
		icon = tiffIcon;
	    } else if (extension.equals(AlbumUtils.png)) {
		icon = pngIcon;
	    }
	}
	return icon;
    }

}
