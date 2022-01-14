package org.digitall.projects.gdigitall.lib.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

import java.util.EmptyStackException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//import org.opsalta.sistemas.Novedades.Novedade;

// EJECUTAR LA SIGUIENTE LINEA PA PROBAR
//      SplashWindow sw = new SplashWindow("/tmp/SistemaOPSalta/iconos/foto.jpg",null, 15000);

public class PopupWindow extends JWindow 
{

  private JLabel label = new JLabel();
  private String mensaje = "";
  private Timer timerShow;
  private Timer timerHide;
  private int delay = 0;   // delay for 5 sec.
  private int period = 70;  // repeat every 1/10 sec.
  private boolean show = false;
  private boolean showNov = false;
  private boolean showing = false;
  private boolean hiding = false;
  
  public PopupWindow() 
  {
    try 
    {
      jbInit();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

	public PopupWindow(String _mensaje)
  {
    try 
    {
      mensaje = _mensaje;
      label.setText(mensaje);
      label.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          hideWindow();
        }
      });
      jbInit();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

	public PopupWindow(String _mensaje, final String _archivo)
  {
    try 
    {
      mensaje = _mensaje;
      label.setText(mensaje);
      label.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          hideWindow();
          abreArchivo(_archivo);
        }
      });
      jbInit();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

	public PopupWindow(String _mensaje, final JDialog _ventana)
  {
    try 
    {
      mensaje = _mensaje;
      label.setText(mensaje);
      label.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          hideWindow();
                                           OP_Proced.CentraVentana(_ventana);
          _ventana.setModal(true);
          _ventana.setVisible(true);
        }
      });
      jbInit();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void jbInit() 
  {
    
  }
  
  public void activarPopupWindow() 
  {
    this.setBounds(new Rectangle(0,0,355, 37));
    this.getContentPane().add(label, null);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBounds(new Rectangle(0, 0, 355, 37));
    this.setForeground(Color.white);
    setBackground(Color.white);
    getRootPane().setBackground(new Color(182, 208, 255));
    this.getContentPane().setLayout(null);
		//pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = getPreferredSize();
    label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    label.setOpaque(true);
    label.setBackground(new Color(182, 208, 255));
    this.addFocusListener(new java.awt.event.FocusAdapter()
      {
        public void focusLost(FocusEvent e)
        {
          this_focusLost(e);
        }
      });
//		this.setLocation(screenSize.width - (label.getWidth()), screenSize.height - (label.getHeight()));
//		this.setLocation(screenSize.width - (getWidth()), screenSize.height - (getHeight()));
    this.getRootPane().setOpaque(false);
    setVisible(true);
		setLocation(screenSize.width-355,0-37);

    //Para mostrar la ventana únicamente si hay un mensaje
    if (mensaje.length()>0) show = true; else show = false;
	}

  private void this_focusLost(FocusEvent e)
  {
    System.out.println("focuslost");
  }

  private void l_mouseClicked(MouseEvent e)
  {
    System.out.println("mouseclicked");
  }
  
	public void showMensaje(String _mensaje)
  {
    mensaje = _mensaje;
    label.setText(mensaje);
    showWindow();
  }

	public void showWindow()
  {
        OP_Proced.setCantPopups(OP_Proced.getCantPopups()+1);
    final int cantPopups = OP_Proced.getCantPopups();
    notificar();
    timerShow = new Timer(period, new ActionListener ()
    {
        public void actionPerformed(ActionEvent e)
        {
          if (!hiding) 
          {
            if (getLocation().y<(37*(cantPopups-1)))
            {
              setLocation(getLocation().x,getLocation().y+2);
              showing = true;
//              System.out.println("show running");
            } else 
            {
              showing = false;
              timerShow.stop();

//              System.out.println("show terminated");
            }
          }
        }
    });
    timerShow.start();
//    timer.

    /*Timer timer = new Timer();
    timer.scheduleAtFixedRate(tareaShow(),delay,period);*/
  }

	public void hideWindow()
  {
        OP_Proced.setCantPopups(OP_Proced.getCantPopups()-1);
    if (showing) 
    {
      showing = false;
      timerShow.stop();
    }
    timerHide = new Timer(period, new ActionListener ()
    {
        public void actionPerformed(ActionEvent e)
        {
          if (!showing)
          {
            if (getLocation().y>-37)
            {
              setLocation(getLocation().x,getLocation().y-2);
              hiding = true;
//              System.out.println("hide running");
            } else 
            {
              hiding = false;
              timerHide.stop();
//              System.out.println("hide terminated");
              dispose();
            }
          }
        }
    });
    timerHide.start();
  }
  
  private void abreArchivo(String _archivo)
  {
    try 
    {
      if (System.getProperty("os.name").equals("Linux"))
      {
                OP_Proced
                .Mensaje("Característica sólo disponible en Windows","No disponible");
      } else 
      {
        /*String command = "";
        String[] cmd = new String[4];
        cmd[0] = "cmd";
        cmd[1] = "/C";
        cmd[2] = "start";
        cmd[3] = "file:/C:" + _archivo.replaceAll(" ","%20").replace('\\','/');
        System.out.println(cmd[0] + cmd[1] + cmd[2] + cmd[3]);
    		Process process = Runtime.getRuntime().exec(cmd);*/
    		Process process = Runtime.getRuntime().exec("explorer " + _archivo);
//        Proced.Mensaje("Función no imprementada","No disponible");
      }
    } catch (EmptyStackException x)
    {
            OP_Proced.Mensaje(x.getMessage(), "Error EmptyStackException");
    } catch (IOException x) 
    {
            OP_Proced.Mensaje(x.getMessage(), "Error E/S");
    } 
  }


  public void setShowNov(boolean _showNov) 
  {
    showNov = _showNov;
  }

  public void notificar() 
  {
    //Applet.newAudioClip(Proced.class.getResource("sounds/pop.wav")).play();
  }

/*  public TimerTask tareaShow() 
  {
    //this.setAlwaysOnTop(true); //Recién en Java 1.5
    toFront();
    TimerTask tareaShow = new TimerTask() {
  		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      public void run() {
        try 
        {
          setVisible(true);
          if (!hiding) 
          {
            if (getLocation().y<0)
            {
              setLocation(getLocation().x,getLocation().y+2);
              showing = true;
              System.out.println("show running");
            } else 
            {
              showing = false;
              timer.cancel();
//              cancel();
              Timer ocultar = new Timer();
              ocultar.scheduleAtFixedRate(tareaHide(),10000,period);
              System.out.println("show terminated");
            }
          }
        } catch (Exception x)
        {
           System.out.println(x.getMessage());
        }
      }
    };
    return tareaShow;
  }*/

/*  public TimerTask tareaHide() 
  {
    toFront();
    TimerTask tareaHide = new TimerTask() {
  		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      public void run() {
        try 
        {
          if (!showing)
          {
            if (getLocation().y>-37)
            {
              setLocation(getLocation().x,getLocation().y-2);
              hiding = true;
              System.out.println("hide running");
            } else 
            {
              hiding = false;
              setVisible(false);
              timer.cancel();
//              cancel();
              System.out.println("hide terminated");
            }
          }
        } catch (Exception x)
        {
           System.out.println(x.getMessage());
        }
      }
    };
    return tareaHide;
  }
*/

/*	public void hideWindow()
  {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(tareaHide(),delay,period);
  }*/
//TODO
// Bring to front //HECHO
// Always on top //HECHO pero para Java 1.5
// Not showing in taskbar //En Windows funciona así por defecto
// Extend to other applications //Supongo que mandando un JFrame o JDialog por parámetro

}
