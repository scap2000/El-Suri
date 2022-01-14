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
 * OutputFormatObject.java
 *
 * */
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