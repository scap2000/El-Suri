package jms;
import java.awt.Color;

/** Define cómo se construirá una leyenda */

public class LegendObject 
{
  private Color imagecolor; /** Color de la leyenda */
  private boolean interlace; /** (true/on;false/off) */
  private Color outlinecolor; /** Color de la línea de las cajas de símbolos */
  private String position; /** Posición para insertar la leyenda: ul - uc - ur - ll - lc - lr */
  private Size keysize; /** Tamaño (en píxels) de las cajas de símbolos */
  private Size keyspacing; /** Espaciado (en píxels) entre las cajas de símbolos (Y) y las etiquetas (X) */
  private boolean postlabelcache; /** Cache de la leyenda (Ver el Manual de MapServer 4) */
  private String status; /** Estado de la leyenda: on - off - embed */
  private boolean transparent; /** Transparencia del color de fondo de la leyenda: true/on;false/off) */
  

  public LegendObject()
  {
  }
}