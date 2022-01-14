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
 * LabelObject.java
 *
 * */
package jms;
import java.awt.Color;

/** Clase Etiqueta */

public class LabelObject 
{
  private String angle; /** Angulo (en grados) para dibujar la Etiqueta, o AUTO para dejar que el software elija */
  private boolean antialias; /** Texto antialias? */
  private MapColor backgroundcolor = new MapColor(); /** Color de fondo del rectangulo de la etiqueta: Red - Green - Blue */
  private MapColor backgroundshadowcolor = new MapColor(); /** Color de la sombra del rectangulo de la etiqueta: Red - Green - Blue */
  private Size backgroundshadowsize; /** Qué tan lejos debe ser dibujada la sombra? */
  private int buffer; /** Relleno en píxels alrededor de las etiquetas para mejor lectura */
  private MapColor color = new MapColor(); /** Color del texto */
  private String font; /** Alias de la fuente (del archivo fontset) para etiquetar */
  private boolean force; /** Forzar a que se dibujen todas las etiquetas (true/on;false/off); */
  private int maxsize; /** Tamaño máximo del tipo de letra */
  private int mindistance; /** Distancia mínima (en píxels) entre etiquetas duplicadas */
  private String minfeaturesize; /** Tamaño mínimo (en píxels) requerido para etiquetar, o AUTO para que el software etiquete los objetos que son más grandes que su etiqueta */
  private int minsize; /** Tamaño minimo del tipo de letra */
  private Size offset; /** Offset para las etiquetas */
  private MapColor outlinecolor = new MapColor(); /** Color de OutLine de la etiqueta */
  private boolean partials; /** Puede el texto dibujarse parcialmente dentro del mapa? */
  private String position; /** Posición de la etiqueta: ul - uc - ur - cl - cc - cr - ll - lc - lr - auto */
  private MapColor shadowcolor = new MapColor(); /** Color de la sombra */
  private Size shadowsize; /** Tamaño (en píxels) de la sombra */
  private String size; /** Tamaño del texto: [entero] - tiny - small - medium - large - giant */
  private String type; /** Tipo de font a utilizar: bitmap - truetype */
  private String wrap; /** Caracter de condición EOL (Fin de línea) en etiquetas multilínea */
  
  public LabelObject()
  {
  
  }
  
  public String generaClase() 
  {
    String clase = "";
    String parte = this.getMSAngle()
                   + this.getMSAntiAlias()
                   + this.getMSBackGroundColor()
                   + this.getMSBackGroundShadowColor()
                   + this.getMSBackGroundShadowSize()
                   + this.getMSBuffer()
                   + this.getMSColor()
                   + this.getMSFont()
                   + this.getMSForce()
                   + this.getMSMaxSize()
                   + this.getMSMinDistance()
                   + this.getMSMinFeatureSize()
                   + this.getMSMinSize()
                   + this.getMSOffSet()
                   + this.getMSOutLineColor()
                   + this.getMSPartials()
                   + this.getMSPosition()
                   + this.getMSShadowColor()
                   + this.getMSShadowSize()
                   + this.getMSSize()
                   + this.getMSType()
                   + this.getMSWrap();

    if (!parte.equals("")) 
    {
      clase = "LABEL\n" 
              + parte
              + "END\n";
    }
    return clase;
  }
  

  public void setAngle(String a) 
  {
    this.angle = a;
  }

  public void setAntiAlias(boolean a) 
  {
    this.antialias = a;
  }
  
  public void setAntiAlias(String a) 
  {
    if (a.toLowerCase().equals("true")) 
    {
      this.antialias = true;
    } else 
    {
      this.antialias = false;
    }
  }

  public void setBackGroundColor(Color c) 
  {
    this.backgroundcolor.setColor(c);
  }
  
  public void setBackGroundColor(String c)
  {
    this.backgroundcolor.setColor(c);
  }

  public void setBackGroudShadowColor(Color c) 
  {
    this.backgroundshadowcolor.setColor(c);
  }
  
  public void setBackGroundShadowColor(String c)
  {
    this.backgroundshadowcolor.setColor(c);
  }

  public void setBackGroundShadowSize(Size s) 
  {
    this.backgroundshadowsize = s;
  }

  public void setBackGroundShadowSize(String s) 
  {
    //this.backgroundshadowsize = s;
  }
  
  public Size setBackGroundShadowSize() 
  {
    return this.backgroundshadowsize;
  }
  
  public void setBuffer(int b) 
  {
    this.buffer = b;
  }
  
  public void setBuffer(String b) 
  {
    this.buffer = Integer.parseInt(b);
  }
  
  public void setColor(Color c)
  {
    this.color.setColor(c);
  }

  public void setColor(String c)
  {
    this.backgroundcolor.setColor(c);
  }

  public void setFont(String f) 
  {
    this.font = f;
  }
  
  public void setForce(boolean f) 
  {
    this.force = f;
  }

  public void setForce(String s) 
  {
    if (s.toLowerCase().equals("on")) 
    {
      this.force = true;
    } else 
    {
      this.force = false;      
    }
  }
  
  public void setMaxSize(int m) 
  {
    this.maxsize = m;
  }
  
  public void setMaxSize(String m) 
  {
    this.maxsize = Integer.parseInt(m);
  }
  
  public void setMinSize(int m) 
  {
    this.minsize = m;
  }
  
  public void setMinSize(String m) 
  {
    this.minsize = Integer.parseInt(m);
  }
  
  public void setMinDistance(int d) 
  {
    this.mindistance = d;
  }
  
  public void setMinDistance(String m) 
  {
    this.mindistance = Integer.parseInt(m);
  }
  
  public void setMinFeatureSize(String m) 
  {
    this.minfeaturesize = m;
  }
  
  public void setOffSet(Size o) 
  {
    this.offset = o;
  }
  
  public void setOffSet(String o) 
  {
    //this.offset = o;
  }
  
  public void setOutLineColor(Color c) 
  {
    this.outlinecolor.setColor(c);
  }
  
  public void setOutLineColor(String c)
  {
    this.outlinecolor.setColor(c);
  }

  public void setPartiasl(boolean p) 
  {
    this.partials = p;
  }
  
  public void setPartials(String p) 
  {
    if (p.toLowerCase().equals("true")) 
    {
      this.partials = true;
    } else 
    {
      this.partials = false;
    }
  }

  public void setPosition(String p) 
  {
    this.position = p;
  }
  
  public void setShadowColor(Color c) 
  {
    this.shadowcolor.setColor(c);
  }
  
  public void setShadowColor(String c)
  {
    this.shadowcolor.setColor(c);
  }

  public void setShadowSize(Size s) 
  {
    this.shadowsize = s;
  }

  public void setShadowSize(String s) 
  {
    //this.shadowsize = s;
  }
  
  public void setSize(String s) 
  {
    this.size = s;
  }
  
  public void setType(String t) 
  {
    this.type = t;
  }
  
  public void setWrap(String w) 
  {
    this.wrap = w;
  }
  
  public String getAngle() 
  {
    return this.angle;
  }
  
  public String getMSAngle() 
  {
    try 
    {
      if (!this.angle.equals("")) 
      {
        return "  ANGLE \"" + this.angle.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public boolean getAntiAlias() 
  {
    return this.antialias;
  }

  public String getMSAntiAlias() 
  {
    try 
    {
      return "  ANTIALIAS " + this.antialias + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public Color getBackGroundColor() 
  {
    return this.backgroundcolor.getColor();
  }
  
  public String getMSBackGroundColor() 
  {
    try 
    {
      if (!(String.valueOf(getBackGroundColor().getRed()).equals("0") && String.valueOf(getBackGroundColor().getGreen()).equals("0") && String.valueOf(getBackGroundColor().getBlue()).equals("0")))
      {
        return "  BACKGROUNDCOLOR " + getBackGroundColor().getRed() + " " + getBackGroundColor().getGreen() + " " + getBackGroundColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }

  public Color getBackGroundShadowColor() 
  {
    return this.backgroundshadowcolor.getColor();
  }
  
  public String getMSBackGroundShadowColor() 
  {
    try 
    {
      if (!(String.valueOf(getBackGroundShadowColor().getRed()).equals("0") && String.valueOf(getBackGroundShadowColor().getGreen()).equals("0") && String.valueOf(getBackGroundShadowColor().getBlue()).equals("0")))
      {
        return "  BACKGROUNDSHADOWCOLOR " + getBackGroundShadowColor().getRed() + " " + getBackGroundShadowColor().getGreen() + " " + getBackGroundShadowColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }

  public Size getBackGroundShadowSize() 
  {
    return this.backgroundshadowsize;
  }

  public String getMSBackGroundShadowSize() 
  {
    try 
    {
      if (!String.valueOf(backgroundshadowsize.getHeight()).equals("0") && !String.valueOf(backgroundshadowsize.getWidth()).equals("0")) 
      {
        return "  BACKGROUNDSHADOWSIZE " + backgroundshadowsize.getHeight() + " " + backgroundshadowsize.getWidth() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public int getBuffer() 
  {
    return this.buffer;
  }
  
  public String getMSBuffer()
  {
    try 
    {
      if (!String.valueOf(this.buffer).equals("0")) 
      {
        return "  BUFFER " + this.buffer + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public Color getColor() 
  {
    return this.color.getColor();
  }
  
  public String getMSColor() 
  {
    try 
    {
      if (!(String.valueOf(getColor().getRed()).equals("0") && String.valueOf(getColor().getGreen()).equals("0") && String.valueOf(getColor().getBlue()).equals("0")))
      {
        return "  COLOR " + getColor().getRed() + " " + getColor().getGreen() + " " + getColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public String getFont() 
  {
    return this.font;
  }
  
  public String getMSFont() 
  {
    try 
    {
      if (!this.font.equals("")) 
      {
        return "  FONT \"" + this.font.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public boolean getForce() 
  {
    return this.force;
  }
  
  public String getMSForce()
  {
    try 
    {
      if (this.force == true) 
      {
        return "  FORCE " + "ON" + "\n";
      } else 
      {
        return "  FORCE " + "OFF" + "\n";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public int getMaxSize() 
  {
    return this.maxsize;
  }
  
  public String getMSMaxSize()
  {
    try 
    {
      if (!String.valueOf(this.maxsize).equals("0")) 
      {
        return "  MAXSIZE " + this.maxsize + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public int getMinSize() 
  {
    return this.minsize;
  }
  
  public String getMSMinSize()
  {
    try 
    {
      if (!String.valueOf(this.minsize).equals("0")) 
      {
        return "  MINSIZE " + this.minsize + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public int getMinDistance() 
  {
    return this.mindistance;
  }
  
  public String getMSMinDistance()
  {
    try 
    {
      if (!String.valueOf(this.mindistance).equals("0")) 
      {
        return "  MAXFEATURES " + this.mindistance + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getMinFeatureSize() 
  {
    return this.minfeaturesize;
  }
  
  public String getMSMinFeatureSize() 
  {
    try 
    {
      if (!this.minfeaturesize.equals("")) 
      {
        return "  MINFEATURESIZE \"" + this.minfeaturesize.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public Size getOffSet()
  {
    return this.offset;
  }
  
  public String getMSOffSet() 
  {
    try 
    {
      if (!String.valueOf(offset.getHeight()).equals("0") && !String.valueOf(offset.getWidth()).equals("0")) 
      {
        return "  OFFSET " + offset.getHeight() + " " + offset.getWidth() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public Color getOutLineColor() 
  {
    return this.outlinecolor.getColor();
  }
  
  public String getMSOutLineColor() 
  {
    try 
    {
      if (!(String.valueOf(getOutLineColor().getRed()).equals("0") && String.valueOf(getOutLineColor().getGreen()).equals("0") && String.valueOf(getOutLineColor().getBlue()).equals("0")))
      {
        return "  OUTLINECOLOR " + getOutLineColor().getRed() + " " + getOutLineColor().getGreen() + " " + getOutLineColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public boolean getpartials() 
  {
    return this.partials;
  }

  public String getMSPartials() 
  {
    try 
    {
      return "  PARTIALS " + this.partials + "\n";
    } catch (NullPointerException x) 
    {
      return "";
    }
  }

  public String getPosition() 
  {
    return this.position;
  }
  
  public String getMSPosition() 
  {
    try 
    {
      if (!this.position.equals("")) 
      {
        return "  POSITION \"" + this.position.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public Color getShadowColor() 
  {
    return this.shadowcolor.getColor();
  }
  
  public String getMSShadowColor() 
  {
    try 
    {
      if (!(String.valueOf(getShadowColor().getRed()).equals("0") && String.valueOf(getShadowColor().getGreen()).equals("0") && String.valueOf(getShadowColor().getBlue()).equals("0")))
      {
        return "  SHADOWCOLOR " + getShadowColor().getRed() + " " + getShadowColor().getGreen() + " " + getShadowColor().getBlue() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }

  public Size getShadowSize()
  {
    return this.shadowsize;
  }

  public String getMSShadowSize() 
  {
    try 
    {
      if (!String.valueOf(shadowsize.getHeight()).equals("0") && !String.valueOf(shadowsize.getWidth()).equals("0")) 
      {
        return "  SHADOWSIZE " + shadowsize.getHeight() + " " + shadowsize.getWidth() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public String getSize() 
  {
    return this.size;
  }
  
  public String getMSSize() 
  {
    try 
    {
      if (!this.size.equals("")) 
      {
        return "  SIZE \"" + this.size.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getType() 
  {
    return this.type;
  }
  
  public String getMSType() 
  {
    try 
    {
      if (!this.type.equals("")) 
      {
        return "  TYPE \"" + this.type.replaceAll("\"","") + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x) 
    {
      return "";
    }
  }
  
  public String getWrap() 
  {
    return this.wrap;
  }

  public String getMSWrap() 
  {
    try 
    {
      if (!this.wrap.equals("")) 
      {
        return "  WRAP \"" + this.wrap.replaceAll("\"","") + "\"\n";
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
    if (nombre.toLowerCase().equals("angle")) 
    {
       setAngle(valor);
    } 
    if (nombre.toLowerCase().equals("antialias")) 
    {
       setAntiAlias(valor);
    }
    if (nombre.toLowerCase().equals("backgroundcolor")) 
    {
       setBackGroundColor(valor);
    }
    if (nombre.toLowerCase().equals("backgroundshadowcolor")) 
    {
       setBackGroundShadowColor(valor);
    }
    if (nombre.toLowerCase().equals("backgroundshadowsize")) 
    {
       setBackGroundShadowSize(valor);
    } 
    if (nombre.toLowerCase().equals("buffer")) 
    {
       setBuffer(valor);
    }
    if (nombre.toLowerCase().equals("color")) 
    {
       setColor(valor);
    }
    if (nombre.toLowerCase().equals("font")) 
    {
       setFont(valor);
    }
    if (nombre.toLowerCase().equals("force")) 
    {
       setForce(valor);
    }
    if (nombre.toLowerCase().equals("maxsize")) 
    {
       setMaxSize(valor);
    }
    if (nombre.toLowerCase().equals("mindistance")) 
    {
       setMinDistance(valor);
    }
    if (nombre.toLowerCase().equals("minfeaturesize")) 
    {
       setMinFeatureSize(valor);
    }
    if (nombre.toLowerCase().equals("minsize")) 
    {
       setMinSize(valor);
    }
    if (nombre.toLowerCase().equals("offset")) 
    {
       setOffSet(valor);
    }
    if (nombre.toLowerCase().equals("outlinecolor")) 
    {
       setOutLineColor(valor);
    }
    if (nombre.toLowerCase().equals("partials")) 
    {
       setPartials(valor);
    }
    if (nombre.toLowerCase().equals("position")) 
    {
       setPosition(valor);
    }
    if (nombre.toLowerCase().equals("shadowcolor")) 
    {
       setShadowColor(valor);
    }
    if (nombre.toLowerCase().equals("shadowsize")) 
    {
       setShadowSize(valor);
    }
    if (nombre.toLowerCase().equals("size")) 
    {
       setSize(valor);
    }
    if (nombre.toLowerCase().equals("type")) 
    {
       setType(valor);
    }
    if (nombre.toLowerCase().equals("wrap")) 
    {
       setWrap(valor);
    }
  }
}
