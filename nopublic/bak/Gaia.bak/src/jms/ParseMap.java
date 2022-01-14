package jms;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ParseMap 
{

  private BufferedReader in;
  private String linea;
  private Clases clases = new Clases();
  private MapObject mapa = new MapObject();

  public ParseMap(MapObject map)
  {
    mapa = map;
    int current = -1;
    try 
    {
      in = new BufferedReader(new FileReader("/home/santiago/.maps/demo/plantilla_u.map"));
//      in = new BufferedReader(new FileReader("C:\\temp\\plantilla.map"));
      try 
      {
        while ((linea = in.readLine()) != null) 
        {
          if (linea.indexOf("#")>-1) 
          {
//            linea = linea.substring(0,linea.indexOf("#")).trim();
          } else 
          {
            linea = linea.trim();
            int space = linea.indexOf(" ");
            if (space > 0)
            {
              /**ATRIBUTO DE CLASE*/
              String atributo = linea.substring(0,space).trim();
              String valortmp = linea.substring(space).trim();
              String valor = "";
              if (atributo.equalsIgnoreCase("data")) 
              {
                boolean findata = false;
                if (valortmp.indexOf("\"") == valortmp.lastIndexOf("\"")) //Significa que la consulta está en múltiples líneas
                {
                  while (((linea = in.readLine()) != null) && !findata)
                  {
                    if (linea.indexOf("#")>-1) 
                    {
//                      linea = linea.substring(0,linea.indexOf("#")).trim();
                    } else 
                    {
                      valortmp += " " + linea.trim();
                      if (linea.indexOf("\"")>-1) 
                      {
                        findata = true;
                        break;
                      } 
                    }
                  }
                }
                valor = valortmp.trim().replaceAll("\"","");
              } else 
              {
                valor = linea.substring(space).trim().replaceAll("\"","");
              }
              switch (current) 
              {
                case 0: //"END"
    
                  break; 
                case 1: //"MAP";
                  mapa.setAttribute(atributo,valor);
                  break; 
                case 2: //"LABEL";
                  LabelObject label = mapa.getLastLayerObject().getLastClassObject().getLabelObject();
                  System.out.println("Atributos del labelobject: " + atributo + ": " + valor);
                  label.setAttribute(atributo,valor);
                  break; 
                case 3: //"LAYER";
                  LayerObject layer = mapa.getLastLayerObject();
                  layer.setAttribute(atributo,valor);
                  break; 
                case 4: //"CLASS";
                  ClassObject clase = mapa.getLastLayerObject().getLastClassObject();
                  clase.setAttribute(atributo,valor);
                  break; 
                case 5: //"STYLE";
                  StyleObject style = mapa.getLastLayerObject().getLastClassObject().getStyleObject();
                  System.out.println("Atributos del styleobject: " + atributo + ": " + valor);
                  style.setAttribute(atributo,valor);
                  break; 
                case 6: //"FEATURE";
    
                  break; 
                case 7: //"LEGEND";
    
                  break; 
                case 8: //"QUERYMAP";
    
                  break; 
                case 9: //"JOIN";
    
                  break; 
                case 10: //"REFERENCE";
    
                  break; 
                case 11: //"SCALEBAR";
    
                  break; 
                case 12: //"WEB";
    
                  break; 
                case 13: //"PROJECTION";
    
                  break; 
                case 14: //"OUTPUTFORMAT";
    
                  break; 
                case 15: //"END";
    
                  break; 
              }
            } else 
            {
              /** INICIO DE CLASE */
              int clase = clases.getClase(linea);
              switch (clase) 
              {
                case 0: //"END"
    
                  break; 
                case 1: //"MAP";
    
                  break; 
                case 2: //"LABEL";
    
                  break; 
                case 3: //"LAYER";
                  mapa.addLayerObject();
                  break;
                case 4: //"CLASS";
                  mapa.getLastLayerObject().addClassObject();
                  break; 
                case 5: //"STYLE";
    
                  break; 
                case 6: //"FEATURE";
    
                  break; 
                case 7: //"LEGEND";
    
                  break; 
                case 8: //"QUERYMAP";
    
                  break; 
                case 9: //"JOIN";
    
                  break; 
                case 10: //"REFERENCE";
    
                  break; 
                case 11: //"SCALEBAR";
    
                  break; 
                case 12: //"WEB";
    
                  break; 
                case 13: //"PROJECTION";
    
                  break; 
                case 14: //"OUTPUTFORMAT";
    
                  break; 
                case 15: //"END";
    
                  break; 
              }
              current = clase;
            }
          }
        }
      } catch (IOException x) 
      {
        System.out.println("Error de E/S");
      }
    } catch (FileNotFoundException x) 
    {
      System.out.println("Archivo no encontrado");
    }
  }
  
  public MapObject getMapa() 
  {
    return mapa;
  }


/*  
    String filename = File.separator+"tmp";
    JFileChooser fc = new JFileChooser(new File(filename));
    
    // Show open dialog; this method does not return until the dialog is closed
    fc.showOpenDialog(this);
    File selFile = fc.getSelectedFile();
    try 
    {
      jLabel1.setText(selFile.getAbsolutePath());
    } catch (NullPointerException x) 
    {
      
    }
*/

}