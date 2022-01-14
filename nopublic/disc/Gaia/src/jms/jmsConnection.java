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
 * jmsConnection.java
 *
 * */
package jms;

/** Conexión a PostGIS para JavaMapServer */

public class jmsConnection 
{
  private String user=""; /** Usuario */
  private String password=""; /** Clave */
  private String dbname=""; /** Nombre de la BD */
  private String host=""; /** Host: IP o localhost */
  private String port=""; /** Puerto de conexión, en PostgreSQL generalmente es 5432 */

  public jmsConnection()
  {
  
  }
  
  public void setUser(String u) 
  {
    this.user = u;
  }
  
  public void setPassword(String p) 
  {
    this.password = p;
  }
  
  public void setDbName(String d) 
  {
    this.dbname = d;
  }
  
  public void setHost(String h) 
  {
    this.host = h;
  }
  
  public void setPort(String p) 
  {
    this.port = p;
  }
  
  public String getUser() 
  {
    return this.user;
  }
  
  public String getPassword() 
  {
    return this.password;
  }
  
  public String getDbName() 
  {
    return this.dbname;
  }
  
  public String getHost() 
  {
    return this.host;
  }
  
  public String getPort() 
  {
    return this.port;
  }
  
  public String getStrConnection() 
  {
    return "user=" + this.user + " password=" + this.password + " dbname=" + this.dbname + " host=" + this.host + " port=" + this.port;
  }

  public void setStrConnection(String c) 
  {
    user = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    password = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    dbname = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    host = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    port = c;
  }
}