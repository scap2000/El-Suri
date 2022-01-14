package org.digitall.lib.image;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter {
    //Accept all directories and all gif, jpg, tiff, or png files.

    public boolean accept(File f) {
	if (f.isDirectory()) {
	    return true;
	}
	String extension = AlbumUtils.getExtension(f);
	if (extension != null) {
	    if (extension.equals(AlbumUtils.tiff) || extension.equals(AlbumUtils.tif) || extension.equals(AlbumUtils.gif) || extension.equals(AlbumUtils.jpeg) || extension.equals(AlbumUtils.jpg) || extension.equals(AlbumUtils.png)) {
		return true;
	    } else {
		return false;
	    }
	}
	return false;
    }
    //The description of this filter

    public String getDescription() {
	return "Sólo imágenes";
    }

}
