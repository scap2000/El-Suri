package jms;

public class Clases 
{
  public Clases()
  {
  }
  
  public int getClase(String c) 
  {
    int numero = -1;
    if (c.toUpperCase().equals("#"))
    {
      numero = 0;
    }
    if (c.toUpperCase().equals("MAP"))
    {
      numero = 1;
    }
    if (c.toUpperCase().equals("LABEL"))
    {
      numero = 2;
    }
    if (c.toUpperCase().equals("LAYER"))
    {
      numero = 3;
    }
    if (c.toUpperCase().equals("CLASS"))
    {
      numero = 4;
    }
    if (c.toUpperCase().equals("STYLE"))
    {
      numero = 5;
    }
    if (c.toUpperCase().equals("FEATURE"))
    {
      numero = 6;
    }
    if (c.toUpperCase().equals("LEGEND"))
    {
      numero = 7;
    }
    if (c.toUpperCase().equals("QUERYMAP"))
    {
      numero = 8;
    }
    if (c.toUpperCase().equals("JOIN"))
    {
      numero = 9;
    }
    if (c.toUpperCase().equals("REFERENCE"))
    {
      numero = 10;
    }
    if (c.toUpperCase().equals("SCALEBAR"))
    {
      numero = 11;
    }
    if (c.toUpperCase().equals("WEB"))
    {
      numero = 12;
    }
    if (c.toUpperCase().equals("PROJECTION"))
    {
      numero = 13;
    }
    if (c.toUpperCase().equals("OUTPUTFORMAT"))
    {
      numero = 14;
    }
    if (c.toUpperCase().equals("END"))
    {
      numero = 15;
    }
    return numero;
  }
  
  public String getClase(int c) 
  {
    switch (c)
    {
      case 0: return "END";
      case 1: return "MAP";
      case 2: return "LABEL";
      case 3: return "LAYER";
      case 4: return "CLASS";
      case 5: return "STYLE";
      case 6: return "FEATURE";
      case 7: return "LEGEND";
      case 8: return "QUERYMAP";
      case 9: return "JOIN";
      case 10: return "REFERENCE";
      case 11: return "SCALEBAR";
      case 12: return "WEB";
      case 13: return "PROJECTION";
      case 14: return "OUTPUTFORMAT";
      case 15: return "END";
      default: return "";
    }
  }
}