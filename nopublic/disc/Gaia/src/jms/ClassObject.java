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
 * ClassObject.java
 *
 * */
package jms;
import java.awt.Color;

/** Define las clases temáticas para cada layer y cada layer debe tener por lo menos una clase del tipo ClassObject */

public class ClassObject 
{
  private MapColor backgroundcolor = new MapColor(); /** Color para los símbolos no transparentes */
  private MapColor color = new MapColor(); /** Color para los dibujos */
  private Expression expression = new Expression(""); /** Expresión que diferencia la clase (Ver el Manual de MapServer 4) */
  private int maxsize; /** Tamaño máximo (en píxels) para dibujar un símbolo */
  private int minsize; /** Tamaño minimo (en píxels) para dibujar un símbolo */
  private String name = null; /** Nombre de la clase para usar en leyendas, si no existe no se mostrará */
  private MapColor outlinecolor = new MapColor(); /** Color para las líneas de los polígonos y algunos símbolos */
  private int size; /** Alto (en píxels) del símbolo o patron, solamente para símbolos escalables */
  private String symbol = null; /** Nombre o número del símbolo a usar (Ver el Manual de MapServer 4) */
  private String template = null; /** Plantilla a usar para los resultados de la consulta */
  private String text = null; /** Ver el manual de MapServer 4 */
  private StyleObject style = new StyleObject();
  private LabelObject label = new LabelObject();
  private LayerObject layer = new LayerObject();

  public ClassObject()
  {
  
  }
 
  public String generaClase() 
  {
    String clase = "";
    String parte = this.getMSBackGroundColor()
                   + this.getMSColor()
                   + this.getMSExpression()
                   + this.getMSMaxSize()
                   + this.getMSMinSize()
                   + this.getMSName()
                   + this.getMSOutlineColor()
                   + this.getMSSize()
                   + this.getMSSymbol()
                   + this.getMSTemplate()
                   + this.getMSText()
                   + this.style.generaClase()
                   + this.label.generaClase();
                   
    if (!parte.equals("")) 
    {
      clase = "CLASS\n" 
               + parte
               + "END\n";
    }
    return clase;
  }
 
  public void setBackGroundColor(Color c)
  {
    this.backgroundcolor.setColor(c);    
  }
 
  public void setBackGroundColor(String c)
  {
    this.backgroundcolor.setColor(c);
  }

  public void setColor(Color c)
  {
    this.color.setColor(c);
  }

  public void setColor(String c)
  {
    this.color.setColor(c);
  }
  
  public void setExpression(Expression e)
  {
    this.expression = e;
  }
  
  public void setExpression(String e)
  {
    this.expression = new Expression(e);
  }
  
  public void setMaxSize(int m) 
  {
    this.maxsize = m;
  }
  
  public void setMaxSize(String m) 
  {
    if (!m.equals("")) this.maxsize = Integer.parseInt(m);
  }
  
  public void setMinSize(int m) 
  {
    this.minsize = m;
  }
  
  public void setMinSize(String m) 
  {
    if (!m.equals("")) this.minsize = Integer.parseInt(m);
  }
  
  public void setName(String n)
  {
    if (!n.equals(""))
    {
      this.name = n;
    } else 
    {
      this.name = "Clase Nueva";
    }
  }

  public void setOutLineColor(Color c)
  {
    this.outlinecolor.setColor(c);
  }
   
  public void setOutLineColor(String c)
  {
    this.outlinecolor.setColor(c);
  }
   
  public void setSize(int s) 
  {
    this.size = s;
  }
  
  public void setSize(String s) 
  {
    if (!s.equals("")) this.size = Integer.parseInt(s);
  }
  
  public void setSymbol(int s)
  {
    this.symbol = String.valueOf(s);
  }
  
  public void setSymbol(String s)
  {
    this.symbol = s;
  }
  
  public void setTemplate(String t)
  {
    this.template = t;
  }
  
  public void setText(String t)
  {
    this.text = t;
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

  public Expression getExpression()
  {
    return this.expression;
  }

  public String getMSExpression()
  {
    try
    {
      if (!this.expression.equals("")) 
      {
        return "  EXPRESSION " + this.expression.getText() + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)
    {
      return "";
    }
  }

  public String getMaxSize() 
  {
    return String.valueOf(this.maxsize);
  }
  
  public String getMSMaxSize() 
  {
    try 
    {
      if (!String.valueOf(maxsize).equals("0")) 
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
  
  public String getMinSize() 
  {
    return String.valueOf(this.minsize);
  }
  
  public String getMSMinSize() 
  {
    try 
    {
      if (!String.valueOf(minsize).equals("0")) 
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
        return "  NAME " + this.name + "\n";
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
   
  public String getMSOutlineColor()
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

  public String getSize() 
  {
    return String.valueOf(this.size);
  }
  
  public String getMSSize() 
  {
    try 
    {
      if (!String.valueOf(this.size).equals("0")) 
      {
        return "  SIZE " + this.size + "\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public String getMSSymbol() 
  {
    try 
    {
      if (!this.symbol.equals("0")) 
      {
        return "  SYMBOL " + this.symbol + "\n";
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
        return "  TEMPLATE \"" + this.template + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public String getMSText() 
  {
    try 
    {
      if (!this.text.equals("")) 
      {
        return "  TEXT \"" + this.text + "\"\n";
      } else 
      {
        return "";
      }
    } catch (NullPointerException x)  
    {
      return ""; 
    }
  }
  
  public StyleObject getStyleObject()
  {
    return this.style;
  }

  public LabelObject getLabelObject()
  {
    return this.label;
  }
  
  public void setAttribute(String nombre, String valor) 
  {
    if (nombre.toLowerCase().equals("backgroundcolor"))
    {
      setBackGroundColor(valor);
    }
    if (nombre.toLowerCase().equals("color"))
    {
      setColor(valor);
    }
    if (nombre.toLowerCase().equals("expression"))
    {
      setExpression(valor);
    }
    if (nombre.toLowerCase().equals("maxsize"))
    {
      setMaxSize(valor);
    }
    if (nombre.toLowerCase().equals("minsize"))
    {
      setMinSize(valor);
    }
    if (nombre.toLowerCase().equals("name"))
    {
      setName(valor);
    }
    if (nombre.toLowerCase().equals("outlinecolor"))
    {
      setOutLineColor(valor);
    }
    if (nombre.toLowerCase().equals("size"))
    {
      setSize(valor);
    }
    if (nombre.toLowerCase().equals("symbol"))
    {
      setSymbol(valor);
    }
    if (nombre.toLowerCase().equals("template"))
    {
      setTemplate(valor);
    }
    if (nombre.toLowerCase().equals("text"))
    {
      setText(valor);
    }
  }
  
  public void setParent(LayerObject l) 
  {
   this.layer = l;
  }
  
  public LayerObject getParent() 
  {
   return this.layer;
  }

  public void deleteMe() 
  {
    this.layer.delClassObject(this);
  }
  
  public void setStyleObject(StyleObject s)
  {
    this.style = s;
  }
  
}