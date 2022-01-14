package jms;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JApplet;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class JMSClient// extends JApplet
{

	/*public void init() {
		super.init();

		//Create and set up the window.
		final Frame frame = new Frame();

		final Button button = new Button("Request Permission");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkWebPermission();
			}
		});
		frame.add(button);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	void checkWebPermission() {
		final SecurityManager sm = System.getSecurityManager();
		
		if (null == sm)
			throw new RuntimeException("Security manager is null"); 
		
		sm.checkPermission(new SocketPermission("www.google.com", "connect"));
		System.out.println("Got permission to connect.");
	}

}*/




  public static void pausa()
  {
    try {
      Thread.sleep(1000);
    } catch (Exception ignored) {}
  }

  public JMSClient()
  {
    try
    {
      //jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

//  private void jbInit() throws Exception
  public static void main(String[] args) throws Exception
  {
    //this.getContentPane().setLayout(null);
    JLabel StatusBar = new JLabel();
    
    String welcome, response;
    Client client;
    BufferedReader reader;
    PrintWriter writer;
    String host = "192.168.10.220";
    client = new Client (host, 5433); //la clase Client Implementa el socket cliente al que se le pasa el argumento 0 que contendrá la dirección o nombre del servidor
    StatusBar.setBounds(new Rectangle(0, 275, 400, 25));
    StatusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    
    try {
      //creamos los canales de entrada/salida para comunicarnos con el servidor
      reader = new BufferedReader (new InputStreamReader (client.in));
      writer = new PrintWriter (new OutputStreamWriter (client.out), true);
      //leemos la bienvenida que nos da el servidor
      welcome = reader.readLine();
      pausa();
      System.out.println("Mensaje procedente del servidor: '"+ welcome +"'");
      //para ver el funcionamiento, enviamos los comandos
      System.out.println("Enviando comando NOMBRE");
      writer.println("NOMBRE");
      response = reader.readLine();
      System.out.println("Respuesta del servidor: '"+ response +"'");
      //pausa
      pausa();
      
      System.out.println("Enviando comando AYUDA");
      writer.println ("AYUDA");
      response = reader.readLine();
      System.out.println("Respuesta del servidor: '"+ response +"'");
      pausa();
      
      System.out.println("Enviando comando FECHA");
      writer.println("FECHA");
      response = reader.readLine();
      System.out.println("Respuesta del servidor: '"+ response +"'");
      pausa();
      
      //el siguiente comando no es entendido por el protocolo implementado por el servidor//como vemos en la respuesta que nos devuelve
      
      System.out.println("Enviando comando xl");
      writer.println("xl");
      response = reader.readLine();
      System.out.println("Respuesta del servidor: '"+ response +"'");
      pausa();
      
      System.out.println("Enviando comando SALIR");
      writer.println("SALIR");
    } catch (IOException e) {
      System.out.println ("IOException en client.in.readln()");
      System.out.println(e);
    }
    try {
      Thread.sleep(2000);
    } catch (Exception ignored) {}
    //this.getContentPane().add(StatusBar, null);
    
  }

}

//-------------------------------------------------------------------
class Client {
  
  // establece los canales de entrada y de salida a disposicion de las clases de usuario
  public InputStream in;
  public OutputStream out;
  
  // El socket cliente
  private Socket client;
  
  public Client (String host, int port) throws IOException {
      client = new Socket (host, port);
      System.out.println ("Datos del socket: " + client);
      in = client.getInputStream();
      out= client.getOutputStream();
  }
}

