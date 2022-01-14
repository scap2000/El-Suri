package org.digitall.lib.misc;

import java.io.IOException;

import java.util.Properties;

import javax.imageio.ImageIO;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.digitall.lib.components.basic.BasicLabel;

public class Mail {

    /*
  Some SMTP servers require a username and password authentication before you
  can use their Server for Sending mail. This is most common with couple
  of ISP's who provide SMTP Address to Send Mail.

  This Program gives any example on how to do SMTP Authentication
  (User and Password verification)

  This is a free source code and is provided as it is without any warranties and
  it can be used in any your code for free.

  Author : Sudhir Ancha
  */
    /*private final String SMTP_AUTH_USER = ""; //Usuario del SMTP (sï¿½lo si se requiere autorizaciï¿½n!!!)
  private final String SMTP_AUTH_PWD  = ""; //Clave del SMTP (sï¿½lo si se requiere autorizaciï¿½n!!!)*/
    /* private String emailMsgTxt      = "";
  private String emailSubjectTxt  = "";
  private String emailFromAddress = "";*/
    // Add List of Email address to who email needs to be sent to
    //  private String[] emailList = {"ctqeo@yahoo.com", "ctqeo@hotmail.com"};
    // private String[] emailList = new String[99];
    //  private BasicTextField mailfrom = new BasicTextField();
    //  private BasicCombo tipomsg = new BasicCombo();
    // private BasicCombo compania = new BasicCombo();

    public Mail() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {

    }
    /*  private void btenviar_actionPerformed(ActionEvent e)
  {
    try
    {
      emailFromAddress = "\"" + namefrom.getText() + "\" <" + mailfrom.getText() + ">";
      emailSubjectTxt = subject.getText();
      emailMsgTxt = msgtext.getText();
      String lista = rcptto.getText();
      int j=0;
      while (lista.indexOf(";")>0)
      {
        emailList[j] = lista.substring(0,lista.indexOf(";"));
        j++;
        lista = lista.substring(lista.indexOf(";")+1);
      }
      emailList[j] = lista;
      j++;

      for (int l=0; l<j; l++)
      {
        switch (compania.getSelectedIndex())
        {
          case 1:
            emailList[l] = emailList[l] + "@personal-net.com.ar";
            break;
          case 2:
            emailList[l] = emailList[l] + "@infotext.cti.com.ar";
            break;
          case 3:
            emailList[l] = emailList[l] + "@emocion.net.ar";
            break;
          case 4:
            emailList[l] = emailList[l] + "@pcs.movi.com.ar";
            break;
          case 5:
            emailList[l] = emailList[l] + "@nextel.net.ar";
            break;
          case 6:
            emailList[l] = emailList[l] + "@skytel.net.ar";
            break;
        }
        System.out.println("Mail[" + l + "]: " + emailList[l]);
      }
      postMail( emailList, j, emailSubjectTxt, emailMsgTxt, emailFromAddress);
      System.out.println("El correo ha sido enviado con ï¿½xito");
    } catch (MessagingException x)
    {
      System.out.println(x.getMessage());
    }
  }*/
    /*    public static void postMail(String[] VeMailDestinatarios, String Asunto, String TextoeMail, String eMailRemitente, boolean Archivo, String UbicacionArchivo_Texto) throws MessagingException {
	boolean debug = false;
	String SMTP_HOST_NAME = "digitallsh.digitallsh.com.ar";
	//Set the host smtp address
	Properties props = new Properties();
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	//Acï¿½ seteo si el SMTP necesita autorizar el usuario (poner "true" y setear SMTP_AUTH_USER y SMTP_AUTH_PWD)
	props.put("mail.smtp.auth", "false");
	Authenticator auth = new SMTPAuthenticator();
	//Authenticator auth = new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
	Session session = Session.getDefaultInstance(props, auth);
	session.setDebug(debug);
	// create a message
	Message msg = new MimeMessage(session);
	// set the from and to address
	InternetAddress addressFrom = new InternetAddress(eMailRemitente);
	msg.setFrom(addressFrom);
	InternetAddress[] addressTo = new InternetAddress[VeMailDestinatarios.length];
	for (int i = 0; i < VeMailDestinatarios.length; i++) {
	    addressTo[i] = new InternetAddress(VeMailDestinatarios[i]);
	}
	msg.setRecipients(Message.RecipientType.TO, addressTo);
	// Setting the Subject and Content Type
	msg.setSubject(Asunto);
	if (Archivo) {
	    msg.setContent(LeeArchivo(UbicacionArchivo_Texto), "text/html");
	} else {
	    msg.setText(TextoeMail);
	    //msg.setContent(UbicacionArchivo_Texto, "text/plain");
	}
	Transport t = session.getTransport();
	t.sendMessage(msg, msg.getAllRecipients());
	Advisor.messageBox("El correo ha sido enviado con ï¿½xito", "Estado del Correo");
    }
*/

    public static void fetchMail() {
	Properties prop = new Properties();
	// Deshabilitamos TLS
	prop.setProperty("mail.pop3.starttls.enable", "false");
	// Hay que usar SSL
	//prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	prop.setProperty("mail.pop3.socketFactory.fallback", "false");
	// Puerto 110 para conectarse.
	prop.setProperty("mail.pop3.port", "110");
	prop.setProperty("mail.pop3.socketFactory.port", "110");
	Session sesion = Session.getInstance(prop);
	sesion.setDebug(false);
	Message[] mensajes = new Message[0];
	try {
	    Store store = sesion.getStore("pop3");
	    store.connect("digitallsh.com.ar", "santiago", "password");
	    Folder folder = store.getFolder("INBOX");
	    folder.open(Folder.READ_ONLY);
	    mensajes = folder.getMessages();
	    for (int i = 0; i < mensajes.length; i++) {
		System.out.println("From: " + mensajes[i].getFrom()[0].toString());
		System.out.println("Subject: " + mensajes[i].getSubject());
		System.out.println("MimeType: " + mensajes[i].getContentType());
		if (mensajes[i].isMimeType("text/*")) {
		    System.out.println("mensaje de texto simple");
		    /*try {
			System.out.println("Content: " + mensajes[i].getContent());
		    } catch (IOException e) {
			// TODO
			e.printStackTrace();
		    }*/
		} else if (mensajes[i].isMimeType("multipart/*")) {
		    // Obtenemos el contenido, que es de tipo MultiPart.
		    Multipart multi;
		    // Extraemos cada una de las partes.
		    try {
			multi = (Multipart)mensajes[i].getContent();
			for (int j = 0; j < multi.getCount(); j++) {
			    Part part = multi.getBodyPart(j);
			    // Volvemos a analizar cada parte de la MultiParte
			    System.out.println("PartContentType: " + part.getContentType());
			    if (part.isMimeType("image/*")) {
				JFrame v = new JFrame();
				ImageIcon icono = new ImageIcon(ImageIO.read(part.getInputStream()));
				BasicLabel l = new BasicLabel(icono);
				v.getContentPane().add(l);
				v.pack();
				v.setVisible(true);
				//get Image
			    }
			}
		    } catch (IOException e) {
			// TODO
		    }
		}
		if (mensajes[i].isMimeType("multipart/*")) {
		    System.out.println("mensaje compuesto");
		}
	    }
	    store.close();
	} catch (MessagingException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

}
