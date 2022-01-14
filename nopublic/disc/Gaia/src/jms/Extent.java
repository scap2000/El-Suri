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
 * Extent.java
 *
 * */
package jms;

/** La extensión espacial del mapa creado
 * MinX, MaxX, MinY, MaxY (Mínimo Oeste, Máximo Este, Mínimo Sur, Máximo Norte respectivamente)
 * */
 
public class Extent 
{
  private double minx;
  private double maxx;
  private double miny;
  private double maxy;
  
  public Extent()
  {
  }

  public Extent(String e)
  {
    setStrExtension(e);
  }

  public Extent(double xmi, double xma, double ymi, double yma)
  {
    setExtension(xmi, xma, ymi, yma);
  }
  
  public String getStrExtension()
  {
    return String.valueOf(minx) + " " + String.valueOf(miny) + " " + String.valueOf(maxx) + " " + String.valueOf(maxy);
  }
  
  public void setStrExtension(String e)
  {
    minx = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    miny = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    maxx = Double.parseDouble(e.substring(0,e.indexOf(" ")).trim());
    e = e.substring(e.indexOf(" ")).trim();
    maxy = Double.parseDouble(e);
    if (minx > maxx) 
    {
      double x = minx;
      minx = maxx;
      maxx = x;
    }
    if (miny > maxy) 
    {
      double y = miny;
      miny = maxy;
      maxy = y;
    }
  }

  public void setExtension(double xmin, double xmax, double ymin, double ymax)
  {
    minx = xmin; 
    miny = ymin;
    maxx = xmax;
    maxy = ymax;
    if (minx > maxx) 
    {
      double x = minx;
      minx = maxx;
      maxx = x;
    }
    if (miny > maxy) 
    {
      double y = miny;
      miny = maxy;
      maxy = y;
    }
  }
  
  public double getMinX() 
  {
    return this.minx;
  }
  
  public double getMaxX() 
  {
    return this.maxx;
  }
  
  public double getMinY() 
  {
    return this.miny;
  }
  
  public double getMaxY() 
  {
    return this.maxy;
  }
  
  public Extent clonar() 
  {
    Extent clon = null;
    try 
    {
      clon = (Extent)clone();
    } catch (CloneNotSupportedException e) 
    {
      System.out.println("No se pudo clonar");
    }
    return clon;
  }
  
  public String getBox3D() 
  {
    return "geometryfromtext('BOX3D(" + minx + " " + miny + " 0," + maxx + " " + maxy + " 0)',-1)";
  }
}