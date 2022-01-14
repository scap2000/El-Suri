/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * ParseMap.java
 *
 * */
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