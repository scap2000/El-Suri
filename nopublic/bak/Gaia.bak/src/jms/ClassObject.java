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