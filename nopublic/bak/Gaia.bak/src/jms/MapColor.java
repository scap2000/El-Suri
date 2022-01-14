package jms;
import java.awt.Color;

public class MapColor
{
  private int red = 0;
  private int green = 0;
  private int blue = 0;
//  private Color color = new Color(red,green,blue);

  public MapColor()
  {
  
  }

  public MapColor(int r, int g, int b)
  {
  
  }
  
  public void setColor(String c) 
  {
    this.red = Integer.parseInt(c.substring(0,c.indexOf(" ")).trim());
    c = c.substring(c.indexOf(" ")).trim();
    this.green = Integer.parseInt(c.substring(0,c.indexOf(" ")).trim());
    c = c.substring(c.indexOf(" ")).trim();
    this.blue = Integer.parseInt(c);
    Color tmp = new Color(red,green,blue);
//    this.color = tmp;
  }

  public void setColor(int r, int g, int b) 
  {
    this.red = r;
    this.green = g;
    this.blue = b;
    Color tmp = new Color(red,green,blue);
//    this.color = tmp;
  }

  public void setColor(Color c) 
  {
    red = c.getRed();
    green = c.getGreen();
    blue = c.getBlue();
  }

  public Color getColor() 
  {
    return new Color(red,green,blue);
  }

}