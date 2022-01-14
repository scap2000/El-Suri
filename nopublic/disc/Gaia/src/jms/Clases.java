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
 * Clases.java
 *
 * */
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