package jms;

/** La extensión espacial del mapa creado
 * MinX, MaxX, MinY, MaxY (Mínimo Oeste, Máximo Este, Mínimo Sur, Máximo Norte respectivamente)
 * */
 
public class Extent 
{
  private double minx;
  private double maxx;
  private double miny;
  private double maxy;
  
  public Extent()
  {
  }

  public Extent(String e)
  {
    setStrExtension(e);
  }

  public Extent(double xmi, double xma, double ymi, double yma)
  {
    setExtension(xmi, xma, ymi, yma);
  }
  
  public String getStrExtension()
  {
    return String.valueOf(minx) + " " + String.valueOf(miny) + " " + String.valueOf(maxx) + " " + String.valueOf(maxy);
  }
  
  public void setStrExtension(String e)
  {
    minx = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    miny = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    maxx = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    maxy = Double.parseDouble(e);
    if (minx > maxx) 
    {
      double x = minx;
      minx = maxx;
      maxx = x;
    }
    if (miny > maxy) 
    {
      double y = miny;
      miny = maxy;
      maxy = y;
    }
  }

  public void setExtension(double xmin, double xmax, double ymin, double ymax)
  {
    minx = xmin; 
    miny = ymin;
    maxx = xmax;
    maxy = ymax;
    if (minx > maxx) 
    {
      double x = minx;
      minx = maxx;
      maxx = x;
    }
    if (miny > maxy) 
    {
      double y = miny;
      miny = maxy;
      maxy = y;
    }
  }
  
  public double getMinX() 
  {
    return this.minx;
  }
  
  public double getMaxX() 
  {
    return this.maxx;
  }
  
  public double getMinY() 
  {
    return this.miny;
  }
  
  public double getMaxY() 
  {
    return this.maxy;
  }
  
  public Extent clonar() 
  {
    Extent clon = null;
    try 
    {
      clon = (Extent)clone();
    } catch (CloneNotSupportedException e) 
    {
      System.out.println("No se pudo clonar");
    }
    return clon;
  }
  
  public String getBox3D() 
  {
    return "geometryfromtext('BOX3D(" + minx + " " + miny + " 0," + maxx + " " + maxy + " 0)',-1)";
  }
}