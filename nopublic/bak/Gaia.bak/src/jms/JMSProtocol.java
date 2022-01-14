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