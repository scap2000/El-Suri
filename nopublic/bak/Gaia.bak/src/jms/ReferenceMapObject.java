package jms;
import java.awt.Color;

/** Define cómo se crearán los mapas de referencia */

public class ReferenceMapObject 
{
  private Color color; /** Color de la caja */
  private Extent extent; /** Extensión espacial de la imagen */
  private String image; /** Nombre completo de la imagen (debe ser GIF) */
  private String marker; /** Define un símbolo a usar cuando la caja sea muy pequeña (Ver el Manual de MapServer 4) */
  private int markersize; /** Define el tamaño del símbolo cuande sea utilizado en vez de la caja */
  private int maxboxsize; /** Si la caja es mayor que éste tamaño, no se dibujará nada */
  private int minboxsize; /** Si la caja es menor que éste tamaño, se dibujará el símbolo definido en el atributo marker */
  private Color outlinecolor; /** Color de la línea de la caja de referencia */
  private Size size; /** Tamaño (en píxels) de la imagen de referencia */
  private boolean status; /** Define si será creado o no el mapa de referencia: true/on;false/off */
  
  public ReferenceMapObject()
  {
  }
}