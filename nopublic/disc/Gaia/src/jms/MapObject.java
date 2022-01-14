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
 * MapObject.java
 *
 * */
package jms;
import java.awt.Color;

/**Este es el Objeto Maestro del Archivo Mapa, contiene todos los otros objetos, 
 * además define los parámetros globales de la aplicación/mapa.
 * */
 
public class MapObject 
{
  private String datapattern; /** No utilizada */
  private String debug; /** No utilizada */
  private Extent extent = new Extent();
  private String fontset; /** Nombre completo del archivo FontSet*/
  private MapColor imagecolor = new MapColor(); /** Color de fondo: Red - Green - Blue */
  private String imagetype; /** Tipo de Imagen: GIF - PNG - JPG - JPEG - WBMP - GTIFF - SWF - Definida por el usuario */
  private String name; /** Nombre del Mapa */
  private int resolution; /** Pixels por pulgada */
  private double scale; /** Escala calculada del mapa, generalmente seteada por la aplicación */
  private String shapepath; /** Ruta del directorio que contiene los archivos Shape */
  private Size size; /** Tamaño en píxels de la imagen de salida */
  private boolean status; /** Estado del mapa (true/on;false/off) */
  private String symbolset; /** Nombre completo del archivo SymbolSet*/
  private String templatepattern; /** No utilizada */
  private String units; /** Unidades del mapa: feet - inches - kilometers - meters - miles - dd */
  private int layersize = 0;
  private LayerObject[] layerobject = new LayerObject[50];
  
  public MapObject()
  {
  }

  public void addLayerObject()
  {
    addLayerObject(new LayerObject());
  }
  
  public void addLayerObject(LayerObject s)
  {
    if (getLayerObject(s) != null) 
    {
      if (layersize<50) 
      {
        s.setParent(this);
        layerobject[layersize] = s;
        System.out.println("Agregado el layerobject nº: " + layersize + " - " + layerobject[layersize]);
        layersize++;
      } else 
      {
        System.out.println("Límite excedido, máximo 50 layers por mapa");
      }
    }
  }
  
  public void delLayerObject(LayerObject s)
  {
    for (int i=0; i<layersize; i++) 
    {
      if (layerobject[i] == s)
      {
        delLayerObject(i);
      }
    }
  }

  public void delLayerObject(int o) 
  {
    for (int i=o; i<(layersize-1); i++) 
    {
      layerobject[i] = layerobject[i+1];
    }
    layersize--;
  }
  
  public LayerObject getLayerObject(int o) 
  {
    return layerobject[o];
  }
  
  public LayerObject getLayerObject(LayerObject layer) 
  {
    for (int i=0; i<layersize; i++) 
    {
      if (layerobject[i] == layer) 
      {
        layer =  null;
      }
    }
    return layer;
  }
  
  public LayerObject getLastLayerObject() 
  {
    return layerobject[layersize-1];
  }
 
  public void setDataPattern(String d) 
  {
    this.datapattern = d;
  }
 
  public void setDebug(String d) 
  {
    this.debug = d;
  }
 
  public void setExtent(String e) 
  {
    this.extent.setStrExtension(e);
  }
 
  public void setFontSet(String f) 
  {
    this.fontset = f;
  }

  public void setImageColor(String c) 
  {
    //this.imagecolor = c;
  }

  public void setImageColor(Color c) 
  {
    this.imagecolor.setColor(c);
  }
  
  public void setImageType(String i) 
  {
    this.imagetype = i;
  }
  
  public void setName(String n) 
  {
    this.name = n;
  }
  
  public void setResolution(String r) 
  {
    this.resolution = Integer.parseInt("0" + r);
  }
  
  public void setScale(String s) 
  {
    this.scale = Double.parseDouble("0" + s);
  }
  
  public void setShapePath(String s) 
  {
    this.shapepath = s;
  }
  
  public void setSize(String s) 
  {
    //this.size = s;
  }

  public void setStatus(String s) 
  {
    if (s.toLowerCase().equals("on")) 
    {
      this.status = true;
    } else 
    {
      this.status = false;      
    }
  }
  
  public void setStatus(boolean s) 
  {
    this.status = s;
  }
  
  public void setSymbolSet(String s)
  {
    this.symbolset = s;
  }

  public void setTemplatePattern(String t) 
  {
    this.templatepattern = t;
  }
  
  public void setUnits(String u)
  {
    this.units = u;
  }

  public String getDataPattern() 
  {
    return this.datapattern;
  }
  
  public String getDebug()
  {
    return this.debug;
  }
  
  public Extent getExtent() 
  {
    return this.extent;
  }
  
  public String getFontSet() 
  {
    return this.fontset;
  }
  public Color getImageColor()
  {
    return this.imagecolor.getColor();
  }
  public String getImageType() 
  {
    return this.imagetype;
  }
  
  public String getName()
  {
    return this.name.replaceAll("\"","");
  }
  
  public int getResolution()
  {
    return this.resolution;
  }
  
  public double getScale()
  {
    return this.scale;
  }
  
  public String getShapePath() 
  {
    return this.shapepath;
  }

  public Size getSize() 
  {
    return this.size;
  }

  public boolean getStatus() 
  {
    return this.status;
  }
  
  public String getSymbolSet()
  {
    return this.symbolset;
  }
  
  public String getTemplatePattern()
  {
    return this.templatepattern;
  }
  
  public String getUnits() 
  {
    return this.units;
  }

  public void setAttribute(String nombre, String valor) 
  {
    if (nombre.toLowerCase().equals("datapattern"))
    {
      setDataPattern(valor);
    }
    if (nombre.toLowerCase().equals("debug"))
    {
      setDebug(valor);
    }
    if (nombre.toLowerCase().equals("extent"))
    {
      setExtent(valor);
    }
    if (nombre.toLowerCase().equals("fontset"))
    {
      setFontSet(valor);
    }
    if (nombre.toLowerCase().equals("imagecolor"))
    {
      setImageColor(valor);
    }
    if (nombre.toLowerCase().equals("imagetype"))
    {
      setImageType(valor);
    }
    if (nombre.toLowerCase().equals("name"))
    {
      setName(valor);
    }
    if (nombre.toLowerCase().equals("resolution"))
    {
      setResolution(valor);
    }
    if (nombre.toLowerCase().equals("scale"))
    {
      setScale(valor);
    }
    if (nombre.toLowerCase().equals("shapepath"))
    {
      setShapePath(valor);
    }
    if (nombre.toLowerCase().equals("size"))
    {
      setSize(valor);
    }
    if (nombre.toLowerCase().equals("status"))
    {
      setStatus(valor);
    }
    if (nombre.toLowerCase().equals("symbolset"))
    {
      setSymbolSet(valor);
    }
    if (nombre.toLowerCase().equals("templatepattern"))
    {
      setTemplatePattern(valor);
    }
    if (nombre.toLowerCase().equals("units"))
    {
      setUnits(valor);
    }

  }
  
  public String getMSDataPattern()
  {
    try 
    {
      if (!this.datapattern.equals("")) 
      {
        return "  DATAPATTERN " + this.datapattern + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSDebug()
  {
    try 
    {
      if (!this.debug.equals("")) 
      {
        return "  DEBUGS " + this.debug + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSExtent()
  {
    if (!extent.getStrExtension().trim().equals("")) 
    {
      return "  EXTENT " + this.extent.getStrExtension() + "\n";
    } else 
    {
      return "";
    }
  }

  public String getMSFontSet()
  {
    try 
    {
      if (!this.fontset.equals("")) 
      {
        return "  FONTSET " + this.fontset + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSImageColor()
  {
    try 
    {
      if (!(String.valueOf(getImageColor().getRed()).equals("0") && String.valueOf(getImageColor().getGreen()).equals("0") && String.valueOf(getImageColor().getBlue()).equals("0")))
      {
        return "  IMAGECOLOR " + getImageColor().getRed() + " " + getImageColor().getGreen() + " " + getImageColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }

  public String getMSImageType()
  {
    try 
    {
      if (!this.imagetype.equals("")) 
      {
        return "  IMAGETYPE " + this.imagetype + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSName() 
  {
    try 
    {
      if (!this.name.equals("")) 
      {
        return "  NAME \"" + this.name.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSResolution()
  {
    try 
    {
      if (!String.valueOf(this.resolution).equals("0")) 
      {
        return "  RESOLUTION " + this.resolution + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSScale()
  {
    try 
    {
      if (!String.valueOf(this.scale).equals("0.0")) 
      {
        return "  SCALE " + this.scale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSShapePath() 
  {
    try 
    {
      if (!this.shapepath.equals("")) 
      {
        return "  SHAPEPATH " + this.shapepath + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSSize()
  {
    try 
    {
      if (!size.getStrSize().trim().equals("")) 
      {
        return "  SIZE " + this.size.getStrSize() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)
    {
      return "";
    }
  }

  public String getMSStatus()
  {
    try 
    {
      if (this.status == true) 
      {
        return "  STATUS " + "ON" + "\n";
      } else 
      {
        return "  STATUS " + "OFF" + "\n";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getMSSymbolSet() 
  {
    try 
    {
      if (!this.symbolset.equals("")) 
      {
        return "  SYMBOLSET " + this.symbolset + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSTemplatePattern()
  {
    try 
    {
      if (!this.templatepattern.equals("")) 
      {
        return "  TEMPLATEPATTERN " + this.templatepattern + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String getMSUnits()
  {
    try 
    {
      if (!this.units.equals("")) 
      {
        return "  UNITS " + this.units + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }    
  }

  public String generaClase() 
  {
    String clase = "";
    String parte = this.getMSDataPattern()
                    + this.getMSDebug()
                    + this.getMSExtent()
                    + this.getMSFontSet()
                    + this.getMSImageColor()
                    + this.getMSImageType()
                    + this.getMSName()
                    + this.getMSResolution()
                    + this.getMSScale()
                    + this.getMSShapePath()
                    + this.getMSSize()
                    + this.getMSStatus()
                    + this.getMSSymbolSet()
                    + this.getMSTemplatePattern()
                    + this.getMSUnits();
                    
    for (int i=0; i<layersize; i++) 
    {
      parte = parte + layerobject[i].generaClase();
    }
    
//                    + this. Classes???
    if (!parte.equals("")) 
    {
      clase = "MAP\n" 
               + parte
               + "END\n";
    }
    return clase;
  }
  
  public MapObject ParseMe(MapObject map) 
  {
    ParseMap parser = new ParseMap(map);
    return parser.getMapa();
  }
  
  public int getLayerCount() 
  {
    return layersize;
  }

}