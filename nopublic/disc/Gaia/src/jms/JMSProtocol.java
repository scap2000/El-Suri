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
 * JMSProtocol.java
 *
 * */
package jms;
import java.net.*;   // for Socket
import java.io.*;    // for IOException and Input/OutputStream
import java.util.*;  // for ArrayList

class JMSProtocol implements Runnable 
{

  static public final int BUFSIZE = 32;   // Size (in bytes) of I/O buffer

  private Socket clntSock;  // Connection socket
  private LayerDraw drawing;
  private Extent extents;
  private MapObject map;
  public JMSProtocol(Socket clntSock, LayerDraw _drawing, MapObject _map) 
  {
    this.clntSock = clntSock;
    drawing = _drawing;
    map = _map;
  }

  public void run() {
    ArrayList entry = new ArrayList();
    entry.add("Client address and port = " +
      clntSock.getInetAddress().getHostAddress() + ":" +
      clntSock.getPort());
    entry.add("Thread = " + Thread.currentThread().getName());

    try {
      // Get the input and output I/O streams from socket
      InputStream in = clntSock.getInputStream();
      OutputStream out = clntSock.getOutputStream();

      int recvMsgSize;                        // Size of received message
      int totalBytesEchoed = 0;               // Bytes received from client
      byte[] echoBuffer = new byte[BUFSIZE];  // Receive Buffer
      // Receive until client closes connection, indicated by -1
      /*while ((recvMsgSize = in.read(echoBuffer)) != -1) {
        out.write(echoBuffer, 0, recvMsgSize);
        totalBytesEchoed += recvMsgSize;
      }*/
      
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      boolean working = true;
      String comando = "";
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(out), true);
      writer.println("LISTO");
      while(clntSock.isConnected()) 
      {
        try {
          comando = reader.readLine().toUpperCase();
        } catch (NullPointerException n) 
        {
          clntSock.close();
          System.out.println("Socket Closed");
          break;
        }
        if (comando.startsWith("AYUDA"))
        {
          writer.println("Órdenes: AYUDA SALIR NOMBRE FECHA");
        } else if (comando.startsWith("SALIR"))
        {
          working = false;
        } else if (comando.startsWith("GETIMAGE")) 
        {
          writer.println(drawing.getImageURL());
        } else if (comando.startsWith("SETCOORDS:")) 
        {
          comando = comando.replaceAll("SETCOORDS:","");
          drawing.setStrExtents(comando);
          writer.println("OK, COORDS RECEIVED");
        } else if (comando.startsWith("GETCOORDS")) 
        {
          writer.println(map.getExtent().getStrExtension());
        } else 
        {
          writer.println("Comando no reconocido: " + comando);
        }
      }
      entry.add("Client finished; echoed " + totalBytesEchoed + " bytes.");
    } catch (IOException e) {
      entry.add("Exception = " +  e.getMessage());
    }

    try {  // Close socket
      clntSock.close();
      //System.exit(0);
      System.out.println("Socket Closed");
    } catch (IOException e) {
      entry.add("Exception = " +  e.getMessage());
    }

    System.out.println(entry);
  }
  
  public void initMap()
  {
  }
}