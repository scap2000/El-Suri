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
 * OP_LibSQL.java
 *
 * */
package org.digitall.projects.gdigitall.lib.misc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class OP_LibSQL 
{

  public static void GuardaImagen(File _imgFile, String _query)
  {
    if (_imgFile != null)
    try 
    {
      FileInputStream fis = new FileInputStream(_imgFile); 
      PreparedStatement ps;
      Connection conn = OP_Proced.CreateConnection();
      ps = conn.prepareStatement(_query);
      ps.setBinaryStream(1, fis, (int)_imgFile.length());
      ps.executeUpdate();
      ps.close();
      fis.close();
    } catch (Exception x) 
    {
      x.printStackTrace();
    }
  }

  private static String toHexadecimal( byte[] datos )
  {
    String resultado = "";
    ByteArrayInputStream input = new ByteArrayInputStream( datos );
    String cadAux;
    int leido = input.read();
    while( leido != -1 )
    {
      cadAux = Integer.toHexString( leido );
      if ( cadAux.length() < 2 ) //Hay que añadir un 0
        resultado += "0";
      resultado += cadAux;
      leido = input.read();
    }
    return resultado;
  }

  public static String getMD5(String _pwd) 
  {
    try 
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(_pwd.getBytes());
      return toHexadecimal(new String(md.digest()).getBytes());
    } catch (NoSuchAlgorithmException x) 
    {
      x.printStackTrace();
      return "";
    }
  }

}