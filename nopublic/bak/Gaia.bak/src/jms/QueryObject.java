package jms;
import java.awt.Color;

/** Define el mecanismo para mostrar los resultados de una consulta */

public class QueryObject 
{
  private Color color; /** Color de resalte de los objetos consultados */
  private Size size; /** Tamaño (en píxels) del mapa */
  private boolean status;  /** Estado: true/on;false/off) */
  private String style; /** Cómo se manipularán los objetos consultados: normal - hilite - selected */
 
  public QueryObject()
  {
  }
}