package jms;

/** Define la operación de la interfaz web */

public class WebObject 
{
  private String empty; /** URL para abrir si la consulta falla */
  private String error; /** URL para abrir si ocurre un error */
  private String footer; /** Plantilla a usar después de que algo haya sido enviado (Solamente para modelos de consulta con múltiples resultados) */
  private String header; /** Plantilla a usar antes de que algo haya sido enviado (Solamente para modelos de consulta con múltiples resultados) */
  private String imagepath; /** Ruta al directorio temporal para escribir archivos e imágenes temporales */
  private String imageurl; /** Ruta base del atributo imagepath */
  private String log; /** Nombre completo del archivo log */
  private double maxscale; /** Escala máxima en la cual esta interfaz es válida */
  private String maxtemplate; /** Plantilla a utilizar si se pasa de la escala máxima */
  private double minscale; /** Escala minima en la cual esta interfaz es válida */
  private String mintemplate; /** Plantilla a utilizar si se pasa de la escala minima */
  private String template; /** Plantilla */
  
  public WebObject()
  {
  }
}