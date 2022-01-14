package jms;
import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;  // for IOException and Input/OutputStream

public class JMSServerThread {

  public static void main(String[] args) throws IOException {

/*    if (args.length != 1)  // Test for correct # of args
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int echoServPort = Integer.parseInt(args[0]);  // Server port
*/
    // Create a server socket to accept client connection requests
//    ServerSocket servSock = new ServerSocket(echoServPort);

      ServerSocket servSock = new ServerSocket(5433);

    //Instancio un mapa
    MapObject map = new MapObject();
    map = map.ParseMe(map);
    LayerDraw drawing = new LayerDraw(map);
//    PanelFeatures panel = new PanelFeatures(drawing);
    
    // Run forever, accepting and spawning threads to service each connection
    for (;;) {
      try {
        Socket clntSock = servSock.accept();  // Block waiting for connection
        JMSProtocol protocol = new JMSProtocol(clntSock, drawing, map);
        Thread thread = new Thread(protocol);
        thread.start();
        System.out.println("Created and started Thread = " + thread.getName());
      } catch (IOException e) {
        System.out.println("Exception = " + e.getMessage());
      }
    }
    /* NOT REACHED */
  }
}
