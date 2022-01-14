package jms;

/** Definición y Selección de los formatos de salida */

public class OutputFormatObject 
{
  private String name; /** Nombre del tipo de imagen a utilizar en el atributo imagetype del mapobject */
  private String driver; /** Nombre del driver: "GD/GIF" - "GD/PNG" - "GD/WBMP" - "GD/JPEG" - "SWF" - "GDAL/GTiff" */
  private String imagemode; /** Modo de la imagen: PC256 - RGB - RGBA - INT16 - FLOAT32 */
  private String mimetype; /** Extensión para el explorador web */
  private String extension; /** Extensión para el sistema operativo */
  private boolean transparent; /** true/on; false/off */
  private String formatoption; /** Se describe abajo */

  /** FORMATOPTION
   * GD/JPEG: QUALITY=n (0 a 100)
   * GD/PNG y GD/GIF: INTERLACE=[ON/OFF]
   * GDAL/GTiff: TILED=YES, BLOCKXSIZE=n, BLOCKYSIZE=n, INTERLEAVE=[PIXEL/BAND], COMPRESS=[NONE/PACKBITS/JPEG/LZW/DEFLATE]
   * GDAL/*: Las opciones para GDAL están descriptas en la documentación detallada para cada formato GDAL */

  public OutputFormatObject()
  {
  }
  
  public void setGif() 
  {
    name = "gif";
    driver = "GD/GIF";
    mimetype = "image/gif";
    imagemode = "PC256";
    extension = "gif";
  }

  public void setPng() 
  {
    name = "png";
    driver = "GD/PNG";
    mimetype = "image/png";
    imagemode = "PC256";
    extension = "png";
  }
  
  public void setJpeg() 
  {
    name = "jpeg";
    driver = "GD/JPEG";
    mimetype = "image/jpeg";
    imagemode = "RGB";
    extension = "jpg";
  }

  public void setWbmp() 
  {
    name = "wbmp";
    driver = "GD/WBMP";
    mimetype = "image/wbmp";
    imagemode = "PC256";
    extension = "wbmp";
  }

  public void setSwf() 
  {
    name = "swf";
    driver = "SWF";
    mimetype = "application/x-shockwave-flash";
    imagemode = "PC256";
    extension = "swf";
  }

  public void setGtiff() 
  {
    name = "GTiff";
    driver = "GDAL/GTiff";
    mimetype = "image/tiff";
    imagemode = "RGB";
    extension = "tif";
  }
  
  public String[] getFormat()
  {
    String[] format = new String[5];
    format[0] = name;
    format[1] = driver;
    format[2] = mimetype;
    format[3] = imagemode;
    format[4] = extension;
    return format;
  }

  public String generaClase() 
  {
    String clase = "OUTPUTFORMAT\n" + 
                   "  NAME " + name + "\n" + 
                   "  DRIVER " + driver + "\n" + 
                   "  MIMETYPE " + mimetype + "\n" +
                   "  IMAGEMODE " + imagemode + "\n" + 
                   "  EXTENSION " + extension + "\n" + 
                   "END";
    return clase;
  }


}