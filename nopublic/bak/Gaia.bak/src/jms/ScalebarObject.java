package jms;
import java.awt.Color;

/** Define cómo se creará la barra de escala */

public class ScalebarObject 
{
  private Color backgroundcolor; /** Color de fondo de la barra, no de la imagen */
  private Color color; /** Color de la barra */
  private Color imagecolor; /** Color de inicialización de la barra */
  private boolean interlace; /** */
  private int intervals; /** Número de intervalos que dividirán la barra */
  private Color outlinecolor; /** Color de la línea de los intervalos */
  private String position; /** Posición de la barra (sólo si está incrustada/embedded): ul - uc - ur - ll - lc - lr */
  private boolean postlabelcache; /** Cache (sólo si está incrustada/embedded): true/on;false/off */
  private Size size; /** Tamaño (en píxels) de la barra */
  private String status; /** Estado: on - off - embed */
  private boolean style; /** Estilo: true/1;false/0 */
  private String units; /** Unidad de la barra: feet - inches - kilometers - meters - miles */
 
  public ScalebarObject()
  {
  }
}