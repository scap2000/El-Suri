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
 * JMSServer.java
 *
 * */
package jms;
import java.util.*;
import java.io.*;
import java.net.*;


public class JMSServer 
{
  final static int SERVER_PORT = 5433; // puerto de escucha de nuestro servidor

  public static void main (String args[]) 
  {
    Server server;
    String clientRequest;
    BufferedReader reader;
    PrintWriter writer;
    
    // creamos el servidor y los canales de entrada y salida
    
    server = new Server (SERVER_PORT);
    reader = new BufferedReader (new InputStreamReader (server.in));
    writer = new PrintWriter (new OutputStreamWriter (server.out), true);
    
    // En cuanto se establece una conexión por parte del cliente, enviamos un saludo
    writer.println ("Bienvenido al Servidor: " + new Date() + "/n");
    
    while (true) 
    {
      try 
      {
        // leemos del canal de entrada la petición del cliente
        clientRequest = reader.readLine();

        // Sacamos por pantalla la peticion del cliente
        System.out.println ("Recibido :" + clientRequest);
      
        // El protocolo de nuestro servidor solo acepta ordenes : HELP, QUIT,NAME,DATE
        if (clientRequest.startsWith ("HELP")) 
        {
          writer.println ("Órdenes: HELP QUIT NAME DATE");
        } else {
          if (clientRequest.startsWith ("QUIT")) 
          {
            //System.exit(0);
          } else {
            if (clientRequest.startsWith("NAME"))
            {
              InetAddress host;
              host=InetAddress.getLocalHost();
              writer.println("Nombre del host :"+ host.getHostName());
            } else if (clientRequest.startsWith("DATE")) (writer.println("Fecha del sistema :"+ new Date()));
              else {
                writer.println ("ERROR: Comando :'" + clientRequest +"' no reconocido, use HELP");
              }
          }
        }
      } catch (IOException e) 
      {
        System.out.println ("Excepción en el servidor " + e);
        System.exit(0);
      }
    }
  }
}


  //-------------------------------------------------------------------
  
  // Esta clase es la que implementa el socket del servidor (ServerSocket)
  class Server 
  {
    private ServerSocket server;
    private Socket socket;
    
    public InputStream in;
    public OutputStream out;
    
    public Server (int port) 
    {
      try 
      {
        server = new ServerSocket (port);
        System.out.println ("Servidor Java Activo! \n");
        System.out.println(""+server+"\n");
        // Espera suspendido hasta que un cliente establece una conexión
        socket = server.accept();
        System.out.println(""+socket.getRemoteSocketAddress()+"\n");
        
        in = socket.getInputStream();
        out = socket.getOutputStream();
      } catch (IOException e) {
        System.out.println ("Excepción en el constructor server: " + e);
      }
    }
  }