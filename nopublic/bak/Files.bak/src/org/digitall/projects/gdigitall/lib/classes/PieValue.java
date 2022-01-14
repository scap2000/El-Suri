package org.digitall.projects.gdigitall.lib.classes;

import java.awt.Color;

public class PieValue 
{

  private double value;
  private Color color;
  
  public PieValue(double _value, Color _color) {
      value = _value;
      color = _color;
  }
  
  public double getValue() 
  {
    return value;
  }
  
  public Color getColor() 
  {
    return color;
  }
        
}