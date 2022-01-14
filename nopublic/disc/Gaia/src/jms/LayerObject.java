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
 * LayerObject.java
 *
 * */
package jms;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;

/** Layer (capa) del mapa */

public class LayerObject
{
  private String attribute; /** Nombre del ítem en la tabla de atributos */
  private jmsConnection connection = new jmsConnection(); /** Conexión a la Base de Datos */
  private String connectiontype=""; /** Tipo de Conexión: local - sde - ogr - postgis - oraclespatial - wms */
  private String data; /** Nombre del archivo.shp (sin la extensión) o cadena de consulta a la base de datos (Ver el Manual de MapServer 4) */
  private String debug; /** No utilizada (Ver el Manual de MapServer 4) */
  private boolean dump; /** Permite a MapServer devolver la información en formato GML */
  private String filter; /** Expresión de filtro sobre la información obtenida de la base de datos */
  private String filteritem; /** Atributo de la tabla para filtrar (OGR y ShapeFiles solamente) */
  private String footer; /** Plantilla a utilizar después de los resultados de un layer (Solamente para modelos de consulta con múltiples resultados) */
  private String group; /** Nombre del grupo al que pertenece el layer */
  private String header; /** Plantilla a utilizar antes de los resultados de un layer (Solamente para modelos de consulta con múltiples resultados) */
  private String labelangleitem; /** Atributo de la tabla para usar como ángulo de la etiqueta (en grados) */
  private boolean labelcache; /** Cache de la etiqueta: true/on;false/off */
  private String labelitem; /** Atributo de la tabla para usar como etiqueta */
  private double labelmaxscale; /** Escala máxima en la cual el layer será etiquetado */
  private double labelminscale; /** Escala minima en la cual el layer será etiquetado */
  private String labelrequires; /** Condición para que sea etiquetado el layer */
  private String labelsizeitem; /** Atributo de la tabla para usar como tamaño de la etiqueta (en píxels) */
  private int maxfeatures; /** Especifica el número máximo de objetos a ser dibujados en el layer en la vista actual */
  private double maxscale; /** Máxima escala en la cual el layer será dibujado */
  private double minscale; /** Minima escala en la cual el layer será dibujado */
  private String name; /** Nombre del layer */
  private MapColor offsite = new MapColor(); /** Color transparente para layers del tipo Raster */
  private boolean postlabelcache; /** Cache del layer: true/on;false/off */
  private String processing; /** (Ver el Manual de MapServer 4) */
  private String requires; /** Condición para que sea dibujado el layer */
  private String sizeunits; /** Unidades del tamaño del objeto: pixels - feet - inches - kilometers - meters - miles */
  private String status;   /** Estado del layer true/on;false/off) */
  private String styleitem; /** Experimental (Ver el Manual de MapServer 4) */
  private double symbolscale; /** Escala en la cual los símbolos y/o textos aparecerán a tamaño total */
  private String template; /** Plantilla alternativa */
  private String tileindex; /** No utilizada */
  private String tileitem; /** No utilizada */
  private double tolerance; /** Sensibilidad para las consultas basadas en punto (via mouse o coordenadas) */
  private String toleranceunits; /** Unidades de tolerancia: pixels - feet - inches - kilometers - meters - miles - dd */
  private int transparency; /** Nivel de Transparencia de 0 a 100 (0 es transparente, 100 es opaco) */
  private boolean transform; /** Ver el Manual de MapServer 4 */
  private String type; /** Tipo de layer: 1: point - 2: line - 3: polygon - 4: circle - 5: annotation - 6: raster - 7: query */
  private int classsize = 0;
  private ClassObject[] classobject = new ClassObject[50];
  private MapObject mapa = new MapObject();
  private boolean onoff = true;
  private String Query = "", KeyRef = "";
  
  public LayerObject()
  {

  }

  public void addClassObject()
  {
    addClassObject(new ClassObject());
  }
  
  public void addClassObject(ClassObject s)
  {
    if (getClassObject(s) != null) 
    {
      if (classsize<50) 
      {
        s.setParent(this);
        classobject[classsize] = s;
//        System.out.println("Agregado el classobject nº: " + classsize + " - " + classobject[classsize]);
        classsize++;
      } else 
      {
        System.out.println("Límite excedido, máximo 50 clases por layer");
      }
    }
  }

  public ClassObject getClassObject(String nombre) 
  {
    ClassObject clase = null;
    for (int i=0; i<classsize; i++) 
    {
      if (classobject[i].getName().toLowerCase().equals(nombre.toLowerCase())) 
      {
        clase = classobject[i];
      }
    }
    return clase;
  }
  
  public ClassObject getClassObject(ClassObject clase) 
  {
    for (int i=0; i<classsize; i++) 
    {
      if (classobject[i] == clase) 
      {
        clase =  null;
      }
    }
    return clase;
  }
  
  public void delClassObject(ClassObject s)
  {
    for (int i=0; i<classsize; i++) 
    {
      if (classobject[i] == s)
      {
        delClassObject(i);
      }
    }
  }

  public void delClassObject(int o) 
  {
    for (int i=o; i<(classsize-1); i++) 
    {
      classobject[i] = classobject[i+1];
    }
    classsize--;
  }
  
  public ClassObject getClassObject(int o) 
  {
    return classobject[o];
  }
  
  
  public ClassObject getLastClassObject() 
  {
    return classobject[classsize-1];
  }
  
  public String generaClase() 
  {
    String clase = "";
    String parte = this.getMSName()
                   + this.getMSConnection()
                   + this.getMSConnectionType()
                   + this.getMSData()
                   + this.getMSDump()
                   + this.getMSFilter()
                   + this.getMSFilterItem()
                   + this.getMSFooter()
                   + this.getMSHeader()
                   + this.getMSGroup()
                   + this.getMSLabelItem()
                   + this.getMSLabelCache()
                   + this.getMSLabelMaxScale()
                   + this.getMSLabelMinScale()
                   + this.getMSMaxScale()
                   + this.getMSMinScale()
                   + this.getMSOffsite()
                   + this.getMSPostLabelCache()
                   + this.getMSProcessing()
                   + this.getMSRequires()
                   + this.getMSSizeUnits()
                   + this.getMSStatus()
                   + this.getMSStyleItem()
                   + this.getMSSymbolScale()
                   + this.getMSTemplate()
                   + this.getMSTolerance()
                   + this.getMSToleranceUnits()
                   + this.getMSTransform()
                   + this.getMSTransparency()
                   + this.getMSType();
    for (int i=0; i<classsize; i++) 
    {
      parte = parte + classobject[i].generaClase();
    }

//                    + this. Classes???
    if (!parte.equals("")) 
    {
      clase = "LAYER\n" 
               + parte
               + "END\n";
    }
    return clase;
  }

  
  public void setAttribute(String a) 
  {
    this.attribute = a;
  }

  public void setConnection(jmsConnection c) 
  {
    connection.setUser(c.getUser());
    connection.setPassword(c.getPassword());
    connection.setDbName(c.getDbName());
    connection.setHost(c.getHost());
    connection.setPort(c.getPort());
  }
  
  public void setConnection(String c) 
  {
    //connection.setStrConnection(c);
  }
  
  public void setConnectionType(String c) 
  {
    this.connectiontype = c;
  }
  
  public void setData(String d) 
  {
    System.out.println(d);
    this.data = d;
  }
  
  public void setDebug(String d) 
  {
    this.debug = d;
  }
  
  public void setDump(boolean d) 
  {
    this.dump = d;
  }
  
  public void setDump(String d) 
  {
    if (d.toLowerCase().equals("true")) 
    {
      this.dump = true;
    } else 
    {
      this.dump = false;
    }
  }
  
  public void setFilter(String f) 
  {
    this.filter = f;
  }

  public void setFilterItem(String f) 
  {
    this.filteritem = f;
  }
  
  public void setFooter(String f) 
  {
    this.footer = f;
  }

  public void setGroup(String g) 
  {
    this.group = g;
  }
  
  public void setHeader(String h) 
  {
    this.header = h;
  }
  public void setLabelAngleItem(String l) 
  {
    this.labelangleitem = l;
  }
  
  public void setLabelCache(boolean l) 
  {
    this.labelcache = l;
  }

  public void setLabelCache(String l) 
  {
    if (l.toLowerCase().equals("true")) 
    {
      this.labelcache = true;
    } else 
    {
      this.labelcache = false;
    }
  }
  
  public void setLabelItem(String l) 
  {
    this.labelitem = l;
  }

  public void setLabelMaxScale(double m) 
  {
    this.labelmaxscale = m;
  }

  public void setLabelMaxScale(String m) 
  {
    this.labelmaxscale = Double.parseDouble(m);
  }

  public void setLabelMinScale(double m) 
  {
    this.labelminscale = m;
  }
  
  public void setLabelMinScale(String m) 
  {
    this.labelminscale = Double.parseDouble(m);
  }
  
  public void setLabelRequires(String l) 
  {
    this.labelrequires = l;
  }

  public void setLabelSizeItem(String l) 
  {
    this.labelsizeitem = l;
  }

  public void setMaxFeatures(int m) 
  {
    this.maxfeatures = m;
  }
  
  public void setMaxFeatures(String m) 
  {
    this.maxfeatures = Integer.parseInt(m);
  }
  
  public void setMaxScale(double m) 
  {
    this.maxscale = m;
  }
  
  public void setMaxScale(String m) 
  {
    this.maxscale = Double.parseDouble(m);
  }
  
  public void setMinScale(double m) 
  {
    this.minscale = m;
  }
  
  public void setMinScale(String m) 
  {
    this.minscale = Double.parseDouble(m);
  }
  
  public void setName(String n)
  {
    if (!n.equals(""))
    {
      this.name = n;
    } else 
    {
      this.name = "Layer Nuevo";
    }
  }
  
  public void setOffSite(Color o) 
  {
    this.offsite.setColor(o);
  }

  public void setOffSite(String o) 
  {
    this.offsite.setColor(o);
  }

  public void setPostLabelCache(boolean p) 
  {
    this.postlabelcache = p;
  }
  
  public void setPostLabelCache(String p) 
  {
    if (p.toLowerCase().equals("true")) 
    {
      this.postlabelcache = true;
    } else 
    {
      this.postlabelcache = false;
    }
  }
  
  public void setProcessing(String p) 
  {
    this.processing = p;
  }

  public void setRequires(String r) 
  {
    this.requires = r;
  }
  
  public void setSizeUnits(String s) 
  {
    this.sizeunits = s;
  }
  
  public void setStatus(String s) 
  {
    this.status = s;
    if (this.status.equalsIgnoreCase("on")) 
    {
      setonoff(true);
    } else 
    {
      setonoff(false);
    }
  }

  public void setStyleItem(String s) 
  {
    this.styleitem = s;
  }
  
  public void setSymbolScale(double s) 
  {
    this.symbolscale = s;
  }
  
  public void setSymbolScale(String s) 
  {
    this.symbolscale = Double.parseDouble(s);
  }
  
  public void setTemplate(String t) 
  {
    this.template = t;
  }
  
  public void setTileIndex(String t) 
  {
    this.tileindex = t;
  }

  public void setTileItem(String t) 
  {
    this.tileitem = t;
  }

  public void setTolerance(double t) 
  {
    this.tolerance = t;
  }

  public void setTolerance(String t) 
  {
    this.tolerance = Double.parseDouble(t);
  }
  
  public void setToleranceUnits(String t) 
  {
    this.toleranceunits = t;
  }

  public void setTransparency(int t) 
  {
    this.transparency = t;
  }
  
  public void setTransparency(String t) 
  {
    this.transparency = Integer.parseInt(t);
  }
  
  public void setTransform(boolean t) 
  {
    this.transform = t;
  }

  public void setTransform(String t) 
  {
    if (t.toLowerCase().equals("true")) 
    {
      this.transform = true;
    } else 
    {
      this.transform = false;
    }
  }

  public void setType(String t) 
  {
    this.type = t;
  }

  public jmsConnection getConnection() 
  {
    return this.connection;
  }

  public String getMSConnection() 
  {
    try
    {
      if (!this.connection.getStrConnection().equals("")) 
      {
        return "  CONNECTION " + this.connection.getStrConnection() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)
    {
      return "";
    }
    
  }

  public String getConnectionType() 
  {
    return this.connectiontype;
  }

  public String getMSConnectionType() 
  {
    try 
    {
      if (!this.connectiontype.equals("")) 
      {
        return "  CONNECTIONTYPE " + this.connectiontype + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getData() 
  {
    return data;
  }
  
  public String getMSData() 
  {
    try 
    {
      if (!this.data.equals("")) 
      {
        return "  DATA \"" + this.data.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getDebug() 
  {
    return this.debug;
  }
  
  public boolean getDump() 
  {
    return this.dump;
  }
  
  public String getMSDump() 
  {
    try 
    {
      return "  DUMP " + this.dump + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getFilter() 
  {
    return this.filter;
  }

  public String getMSFilter() 
  {
    try 
    {
      if (!this.filter.equals("")) 
      {
        return "  FILTER \"" + this.filter.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getFilterItem() 
  {
    return this.filteritem;
  }
  
  public String getMSFilterItem() 
  {
    try 
    {
      if (!this.filteritem.equals("")) 
      {
        return "  FILTERITEM " + this.filteritem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getFooter() 
  {
    return this.footer;
  }

  public String getMSFooter()
  {
    try 
    {
      if (!this.footer.equals("")) 
      {
        return "  FOOTER \"" + this.footer.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getGroup() 
  {
    return this.group;
  }
  
  public String getMSGroup()
  {
    try 
    {
      if (!this.group.equals("")) 
      {
        return "  GROUP " + this.group + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getHeader() 
  {
    return this.header;
  }
  
  public String getMSHeader()
  {
    try 
    {
      if (!this.header.equals("")) 
      {
        return "  HEADER " + this.header + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getLabelAngleItem() 
  {
    return this.labelangleitem;
  }
  
  public String getMSLabelAngleItem() 
  {
    try 
    {
      if (!this.labelangleitem.equals("")) 
      {
        return "  LABELANGLEITEM " + this.labelangleitem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public boolean getLabelCache() 
  {
    return this.labelcache;
  }

  public String getMSLabelCache() 
  {
    try 
    {
      return "  LABELCACHE " + this.labelcache + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getLabelItem() 
  {
    if (labelitem != null)
      return this.labelitem;
    else  return "";
  }

  public String getMSLabelItem() 
  {
    try 
    {
      if (!this.labelitem.equals("")) 
      {
        return "  LABELITEM " + this.labelitem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getLabelMaxScale() 
  {
    return String.valueOf(this.labelmaxscale);
  }

  public String getMSLabelMaxScale() 
  {
    try 
    {
      if (!String.valueOf(this.labelmaxscale).equals("0.0")) 
      {
        return "  LABELMAXSCALE " + this.labelmaxscale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getLabelMinScale() 
  {
    return String.valueOf(this.labelminscale);
  }
  
  public String getMSLabelMinScale() 
  {
    try 
    {
      if (!String.valueOf(this.labelminscale).equals("0.0")) 
      {
        return "  LABELMINSCALE " + this.labelminscale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getLabelRequires() 
  {
    return this.labelrequires;
  }

  public String getMSLabelRequires() 
  {
    try 
    {
      if (!this.labelrequires.equals("")) 
      {
        return "  LABELREQUIRES (" + this.labelrequires + ")\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getLabelSizeItem() 
  {
    return this.labelsizeitem;
  }

  public String getMSLabelSizeItem() 
  {
    try 
    {
      if (!this.labelsizeitem.equals("")) 
      {
        return "  LABELSIZEITEM " + this.labelsizeitem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getMaxFeatures() 
  {
    return String.valueOf(this.maxfeatures);
  }
  
  public String getMSMaxFeatures()
  {
    try 
    {
      if (!String.valueOf(this.maxfeatures).equals("0")) 
      {
        return "  MAXFEATURES " + this.maxfeatures + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getMaxScale() 
  {
    return String.valueOf(this.maxscale);
  }
  
  public String getMSMaxScale() 
  {
    try 
    {
      if (!String.valueOf(this.maxscale).equals("0.0")) 
      {
        return "  MAXSCALE " + this.maxscale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getMinScale() 
  {
    return String.valueOf(this.minscale);
  }
  
  public String getMSMinScale() 
  {
    try 
    {
      if (!String.valueOf(this.minscale).equals("0.0")) 
      {
        return "  MINSCALE " + this.minscale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getName()
  {
    return this.name.replaceAll("\"","");
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
  
  public Color getOffsite() 
  {
    return this.offsite.getColor();
  }
  
  public String getMSOffsite() 
  {
    try 
    {
      if (!(String.valueOf(getOffsite().getRed()).equals("0") && String.valueOf(getOffsite().getGreen()).equals("0") && String.valueOf(getOffsite().getBlue()).equals("0")))
      {
        return "  OFFSITE " + getOffsite().getRed() + " " + getOffsite().getGreen() + " " + getOffsite().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public boolean getPostLabelCache() 
  {
    return this.postlabelcache;
  }

  public String getMSPostLabelCache() 
  {
    try 
    {
      return "  POSTLABELCACHE " + this.postlabelcache + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getProcessing() 
  {
    return this.processing;
  }

  public String getMSProcessing()
  {
    try 
    {
      if (!this.processing.equals("")) 
      {
        return "  PROCESSING \"" + this.processing.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getRequires() 
  {
    return this.requires;
  }

  public String getMSRequires() 
  {
    try 
    {
      if (!this.requires.equals("")) 
      {
        return "  REQUIRES " + this.requires + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getSizeUnits() 
  {
    return this.sizeunits;
  }

  public String getMSSizeUnits() 
  {
    try 
    {
      if (!this.sizeunits.equals("")) 
      {
        return "  SIZEUNITS " + this.sizeunits + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getStatus() 
  {
    return this.status;
  }

  public String getMSStatus() 
  {
    try 
    {
      return "  STATUS " + this.status + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getStyleItem() 
  {
    return this.styleitem;
  }

  public String getMSStyleItem() 
  {
    try 
    {
      if (!this.styleitem.equals("")) 
      {
        return "  STYLEITEM " + this.styleitem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
    
  public String getSymbolScale() 
  {
    return String.valueOf(this.symbolscale);
  }
  
  public String getMSSymbolScale() 
  {
    try 
    {
      if (!String.valueOf(this.symbolscale).equals("0.0")) 
      {
        return "  SYMBOLSCALE " + this.symbolscale + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getTemplate() 
  {
    return this.template;
  }

  public String getMSTemplate() 
  {
    try 
    {
      if (!this.template.equals("")) 
      {
        return "  TEMPLATE \"" + this.template.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getTileIndex() 
  {
    return this.tileindex;
  }

  public String getMSTileIndex() 
  {
    try 
    {
      if (!this.tileindex.equals("")) 
      {
        return "  TILEINDEX \"" + this.tileindex.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getTileItem() 
  {
    return this.tileitem;
  }

  public String getMSTileItem() 
  {
    try 
    {
      if (!this.tileitem.equals("")) 
      {
        return "  TILEITEM " + this.tileitem + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getTolerance() 
  {
    return String.valueOf(this.tolerance);
  }
  
  public String getMSTolerance() 
  {
    try 
    {
      if (!String.valueOf(this.tolerance).equals("0.0")) 
      {
        return "  TOLERANCE " + this.tolerance + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getToleranceUnits() 
  {
    return this.toleranceunits;
  }

  public String getMSToleranceUnits() 
  {
    try 
    {
      if (!this.toleranceunits.equals("")) 
      {
        return "  TOLERANCEUNITS " + this.toleranceunits + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getTransparency() 
  {
    return String.valueOf(this.transparency);
  }
  
  public String getMSTransparency() 
  {
    try 
    {
      if (!String.valueOf(this.transparency).equals("0")) 
      {
        return "  TRANSPARENCY " + this.transparency + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public boolean getTransform() 
  {
    return this.transform;
  }
  
  public String getMSTransform() 
  {
    try 
    {
      return "  TRANSFORM " + this.transform + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getType() 
  {
    return this.type;
  }

  public int getTypeInt() 
  {
    int tipo = 0;
    if (this.type.equalsIgnoreCase("point")) tipo = 1;
    if (this.type.equalsIgnoreCase("line")) tipo = 2;
    if (this.type.equalsIgnoreCase("polygon")) tipo = 3;
    if (this.type.equalsIgnoreCase("circle")) tipo = 4;
    if (this.type.equalsIgnoreCase("annotation")) tipo = 5;
    if (this.type.equalsIgnoreCase("raster")) tipo = 6;
    if (this.type.equalsIgnoreCase("query")) tipo = 7;
    return tipo;
  }

  public String getMSType() 
  {
    try 
    {
      if (!this.type.equals("")) 
      {
        return "  TYPE " + this.type + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public void setAttribute(String nombre, String valor) 
  {
    if (nombre.toLowerCase().equals("attribute")) 
    {
      setAttribute(valor);
    }
    if (nombre.toLowerCase().equals("connection")) 
    {
      setConnection(valor);
    }
    if (nombre.toLowerCase().equals("connectiontype")) 
    {
      setConnectionType(valor);
    }
    if (nombre.toLowerCase().equals("data")) 
    {
      setData(valor);
    }
    if (nombre.toLowerCase().equals("debug")) 
    {
      setDebug(valor);
    }
    if (nombre.toLowerCase().equals("dump")) 
    {
      setDump(valor);
    }
    if (nombre.toLowerCase().equals("filter")) 
    {
      setFilter(valor);
    }
    if (nombre.toLowerCase().equals("filteritem")) 
    {
      setFilterItem(valor);
    }
    if (nombre.toLowerCase().equals("footer"))
    {
      setFooter(valor);
    }
    if (nombre.toLowerCase().equals("group"))
    {
      setGroup(valor);
    }
    if (nombre.toLowerCase().equals("header"))
    {
      setHeader(valor);
    }
    if (nombre.toLowerCase().equals("labelangleitem"))
    {
      setLabelAngleItem(valor);
    }
    if (nombre.toLowerCase().equals("labelcache"))
    {
      setLabelCache(valor);
    }
    if (nombre.toLowerCase().equals("labelitem"))
    {
      setLabelItem(valor);
    }
    if (nombre.toLowerCase().equals("labelmaxscale")) 
    {
      setLabelMaxScale(valor);
    }
    if (nombre.toLowerCase().equals("labelminscale"))
    {
      setLabelMinScale(valor);
    }
    if (nombre.toLowerCase().equals("labelrequires"))
    {
      setLabelRequires(valor);
    }
    if (nombre.toLowerCase().equals("labelsizeitem"))
    {
      setLabelSizeItem(valor);
    }
    if (nombre.toLowerCase().equals("maxfeatures"))
    {
      setMaxFeatures(valor);
    }
    if (nombre.toLowerCase().equals("maxscale"))
    {
      setMaxScale(valor);
    }
    if (nombre.toLowerCase().equals("minscale"))
    {
      setMinScale(valor);
    }
    if (nombre.toLowerCase().equals("name"))
    {
      setName(valor); 
    }
    if (nombre.toLowerCase().equals("offsite"))
    {
      setOffSite(valor);
    }
    if (nombre.toLowerCase().equals("postlabelcache"))
    {
      setPostLabelCache(valor);
    }
    if (nombre.toLowerCase().equals("processing"))
    {
      setProcessing(valor);
    }
    if (nombre.toLowerCase().equals("requires"))
    {
      setRequires(valor);
    }
    if (nombre.toLowerCase().equals("sizeunits"))
    {
      setSizeUnits(valor);
    }
    if (nombre.toLowerCase().equals("status"))
    {
      setStatus(valor);
    }
    if (nombre.toLowerCase().equals("styleitem"))
    {
      setStyleItem(valor);
    }
    if (nombre.toLowerCase().equals("symbolscale"))
    {
      setSymbolScale(valor);
    }
    if (nombre.toLowerCase().equals("template"))
    {
      setTemplate(valor);
    }
    if (nombre.toLowerCase().equals("tileindex"))
    {
      setTileIndex(valor);
    }
    if (nombre.toLowerCase().equals("tileitem"))
    {
      setTileItem(valor);
    }
    if (nombre.toLowerCase().equals("tolerance"))
    {
      setTolerance(valor);
    }
    if (nombre.toLowerCase().equals("toleranceunits"))
    {
      setToleranceUnits(valor);
    }
    if (nombre.toLowerCase().equals("transparency"))
    {
      setTransparency(valor);
    }
    if (nombre.toLowerCase().equals("transform"))
    {
      setTransform(valor);
    }
    if (nombre.toLowerCase().equals("type"))
    {
      setType(valor);
    }
    if (nombre.toLowerCase().equals("query"))
    {
      setQuery(valor);
    }
    if (nombre.toLowerCase().equals("keyref"))
    {
      setKeyRef(valor);
    }
  }
 
  public void setParent(MapObject map) 
  {
   this.mapa = map;
  }
  
  public MapObject getParent() 
  {
   return this.mapa;
  }
  
  public int getClassCount() 
  {
   return classsize;
  }
  
  public void deleteMe() 
  {
   this.mapa.delLayerObject(this);
  }
  
  public MapColor getFillColorByClass(ResultSet result) 
  {
    MapColor color = new MapColor();
    for (int i=0; i<classsize; i++) 
    {
      if (classobject[i].getExpression().evaluar(result)) 
      {
        color.setColor(classobject[i].getStyleObject().getColor());
      } else 
      {
       // color.setColor(getClassObject("color").getColor());
      }
    }
    return color;
  }
  
  public String getExpressions() 
  {
    String expressions = "";
    for (int i=0; i<classsize; i++) 
    {
      String operando = classobject[i].getExpression().getOperando(1);
      {
        if (expressions.indexOf(operando) == -1) 
        {
          expressions += ", " + operando + " " ;
        }
      }
    }
    return expressions;
  }
  
  public void setonoff(boolean estado) 
  {
    onoff = estado;
  }
  
  public boolean getonoff() 
  {
    return onoff;
  }
  
  public void setQuery(String query) 
  {
    Query = query;
  }
  
  public String getQuery() 
  {
    return Query;
  }

  public void setKeyRef(String keyref) 
  {
    KeyRef = keyref;
  }
  
  public String getKeyRef() 
  {
    return KeyRef;
  }

}