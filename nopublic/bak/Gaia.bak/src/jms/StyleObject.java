package jms;
import java.awt.Color;

/** Parámetros para simbolización, se pueden aplicar varios en una clase */

public class StyleObject 
{
  private String antialias; /** TrueType antialias (true/on;false/off) */
  private MapColor backgroundcolor = new MapColor(); /** Color a utilizar para los símbolos no transparentes */
  private MapColor color = new MapColor(); /** Color a utilizar para los objetos */
  private int maxsize; /** Tamaño máximo (en píxels) para dibujar un símbolo */
  private int minsize; /** Tamaño minimo (en píxels) para dibujar un símbolo */
  private Size offset = new Size(); /** Valor de offset */
  private MapColor outlinecolor = new MapColor(); /** Color para las líneas de los polígonos y algunos símbolos */
  private int size; /** Alto (en píxels) del símbolo o patron, solamente para símbolos escalables */
  private String symbol; /** Nombre o número del símbolo a usar (Ver el Manual de MapServer 4) */

  public StyleObject()
  {

  }

  public String generaClase() 
  {
    String clase = "";
    String parte = this.getMSAntiAlias()
                   + this.getMSBackGroundColor()
                   + this.getMSColor()
                   + this.getMSMaxSize()
                   + this.getMSMinSize()
                   + this.getMSOffSet()
                   + this.getMSOutlineColor()
                   + this.getMSSize()
                   + this.getMSSymbol();

    if (!parte.equals("")) 
    {
      clase = "STYLE\n" 
              + parte
              + "END\n";
    }
    return clase;
  }
  
  public void setAntiAlias(String a) 
  {
    this.antialias = a;
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
  
  public void setOffSet(Size s) 
  {
    this.offset = s;
  }
  
  public void setOffSet(String w, String h) 
  {
    this.setOffSet(Integer.parseInt("0" + w),Integer.parseInt("0" + h));
  }
  
  public void setOffSet(String o) 
  {
    String w = o.substring(0,o.indexOf(" ")).trim();
    String h = o.substring(o.indexOf(" ")).trim();
    this.setOffSet(w,h);
  }
  
  public void setOffSet(int w, int h) 
  {
    Size n = new Size();
    n.setHeight(h);
    n.setWidth(w);
    this.setOffSet(n);
//    this.offset.setWidth(w);
//    this.offset.setHeight(h);
//    .setSize(w,h);
  }
  
  public void setOutLineColor(String c)
  {
    this.outlinecolor.setColor(c);
  }

  public void setOutLineColor(Color c)
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
  
  public String getAntiAlias() 
  {
    return this.antialias;
  }
  
  public String getMSAntiAlias() 
  {
    try 
    {
      if (!this.antialias.equals("")) 
      {
        return "  ANTIALIAS " + this.antialias + "\n";
      } else 
      {
        return "";
      }
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
  
  public Color getColor()
  {
    if (color!=null)
      return this.color.getColor();
    else return null;
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

  public void setAttribute(String nombre, String valor) 
  {
    if (nombre.toLowerCase().equals("antialias")) 
    {
      setAntiAlias(valor);
    }
    if (nombre.toLowerCase().equals("backgroundcolor")) 
    {
      setBackGroundColor(valor);
    }
    if (nombre.toLowerCase().equals("color")) 
    {
      setColor(valor);
    }
    if (nombre.toLowerCase().equals("maxsize")) 
    {
      setMaxSize(valor);
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
    if (nombre.toLowerCase().equals("size")) 
    {
      setSize(valor);
    }
    if (nombre.toLowerCase().equals("symbol")) 
    {
      setSymbol(valor);
    }
  }
  
}