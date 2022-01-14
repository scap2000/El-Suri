package jms;

public class Size 
{
  private int width; //x
  private int height; //y
  public Size()
  {
  
  }
  
  public void setHeight(int h)
  {
    height = h;
  }
  
  public void setWidth(int w)
  {
    width = w;
  }

  public void setSize(Size s) 
  {
    setSize(s.getWidth(),s.getHeight());  
  }

  public void setSize(int w, int h) 
  {
    this.width = w;
    this.height = h;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public Size getSize() 
  {
    return this;
  }
  
  public String getStrSize() 
  {
    return String.valueOf(width) + "  " + String.valueOf(height);
  }
}