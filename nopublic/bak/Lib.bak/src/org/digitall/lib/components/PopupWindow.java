package org.digitall.lib.components;

import java.applet.Applet;

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
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.resources.ResourcesManager;

//

public class PopupWindow extends BasicContainerPanel {

    private BasicLabel label = new BasicLabel();
    private String mensaje = "";
    private Timer timerShow;
    private Timer timerHide;
    private int delay = 0;
    // delay for 5 sec.
    private int period = 70;
    private int location = 0;
    // repeat every 1/10 sec.
    private boolean show = false;
    private boolean showNov = false;
    private boolean showing = false;
    private boolean hiding = false;
    private BasicDesktop desktop;

    public PopupWindow() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PopupWindow(String _mensaje) {
	try {
	    mensaje = _mensaje;
	    label.setText(mensaje);
	    label.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
				    hideWindow();
				}

			    }
	    );
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    /* public PopupWindow(JFrame _parent, String _mensaje) {
	super(_parent);
	setLocationRelativeTo(_parent);
	try {
	    mensaje = _mensaje;
	    label.setText(mensaje);
	    label.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
			    hideWindow();
			}

		    });
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }*/

    public PopupWindow(String _mensaje, final String _archivo) {
	try {
	    mensaje = _mensaje;
	    label.setText(mensaje);
	    label.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
				    hideWindow();
				    if (_archivo.length()>0) {
					abreArchivo(_archivo);
				    }
				}

			    }
	    );
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PopupWindow(String _mensaje, final BasicDialog _ventana) {
	try {
	    mensaje = _mensaje;
	    label.setText(mensaje);
	    label.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
				    hideWindow();
				    ComponentsManager.centerWindow(_ventana);
				    _ventana.setModal(true);
				    _ventana.setVisible(true);
				}

			    }
	    );
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PopupWindow(String _mensaje, final BasicInternalFrame _ventana) {
	try {
	    mensaje = _mensaje;
	    label.setText(mensaje);
	    label.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
				    hideWindow();
				    _ventana.setVisible(true);
				    _ventana.show();
				    _ventana.transferToDesktop(Environment.getActiveDesktop());
				}

			    }
	    );
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() {

    }

    public void activarPopupWindow() {
	this.setBounds(new Rectangle(0, 0, 355, 37));
	this.add(label, null);
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setBounds(new Rectangle(0, 0, 355, 37));
	//this.toFront();
	this.setForeground(Color.white);
	//setBackground(Color.white);
	setBackground(new Color(0, 0, 0, 0));
	this.setLayout(null);
	//pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension labelSize = getPreferredSize();
	label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	label.setOpaque(true);
	label.setBackground(new Color(0, 0, 0, 0.65f));
	this.addFocusListener(new java.awt.event.FocusAdapter() {

			   public void focusLost(FocusEvent e) {
			       this_focusLost(e);
			   }

		       }
	);
	//		this.setLocation(screenSize.width - (label.getWidth()), screenSize.height - (label.getHeight()));
	//		this.setLocation(screenSize.width - (getWidth()), screenSize.height - (getHeight()));
	this.setOpaque(false);
	setVisible(true);
	//Para mostrar la ventana ï¿½nicamente si hay un mensaje
	if (mensaje.length() > 0)
	    show = true;
	else
	    show = false;
    }

    private void this_focusLost(FocusEvent e) {
	System.out.println("focuslost");
    }

    private void l_mouseClicked(MouseEvent e) {
	System.out.println("mouseclicked");
    }

    public void showMensaje(String _mensaje) {
	mensaje = _mensaje;
	label.setText(mensaje);
	showWindow();
    }

    public void showWindow() {
	if (!showing) {
	    Environment.popupCounter++;
	    notificar();
	    desktop = Environment.getDesktops()[Environment.mainTabbedPane.getSelectedIndex()];
	    desktop.add(this, 0);
	    this.setVisible(true);
	    setLocation((int)desktop.getBounds().getMaxX() - this.getWidth(), (int)desktop.getBounds().getMinY());
	    location = (int)getLocation().getY();
	    timerShow = new Timer(period, new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   if (!hiding) {
				       if (location < (35 /*37*/ * (Environment.popupCounter - 1))) {
					   setLocation(getLocation().x, getLocation().y + 2);
					   location += 2;
					   showing = true;
					   //              System.out.println("show running");
				       } else {
					   showing = false;
					   timerShow.stop();
					   //              System.out.println("show terminated");
				       }
				   }
			       }

			   }
		);
	    timerShow.start();
	}
    }

    public void hideWindow() {
	if (!hiding) {
	    Environment.popupCounter--;
	    if (Environment.popupCounter < 0) { 
		Environment.popupCounter = 0;
	    }
	    if (showing) {
		showing = false;
		timerShow.stop();
	    }
	    timerHide = new Timer(period, new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   if (!showing) {
				       if (location > -35 /*-37*/) {
					   //System.out.println(getLocation().y);
					   setLocation(getLocation().x, getLocation().y - 2);
					   location -= 2;
					   hiding = true;
					   //              System.out.println("hide running");
				       } else {
					   hiding = false;
					   timerHide.stop();
					   //              System.out.println("hide terminated");
					   setVisible(false);
					   dispose();
				       }
				   }
			       }

			   }
		);
	    timerHide.start();
	}
    }

    private void dispose() {
	if (desktop != null) {
	    desktop.remove(this);
	}
    }

    private void abreArchivo(String _archivo) {
	try {
	    BrowserLauncher.openURL(_archivo);
	    if (System.getProperty("os.name").equals("Linux")) {
		//org.digitall.lib.components.Advisor.messageBox("Caracterï¿½stica sï¿½lo disponible en Windows", "No disponible");
	    } else {
		/*String command = "";
        String[] cmd = new String[4];
        cmd[0] = "cmd";
        cmd[1] = "/C";
        cmd[2] = "start";
        cmd[3] = "file:/C:" + _archivo.replaceAll(" ","%20").replace('\\','/');
        System.out.println(cmd[0] + cmd[1] + cmd[2] + cmd[3]);
    		Process process = Runtime.getRuntime().exec(cmd);*/
		Process process = Runtime.getRuntime().exec("explorer " + _archivo);
		//        org.digitall.lib.components.Advisor.messageBox("Funciï¿½n no imprementada","No disponible");
	    }
	} catch (EmptyStackException x) {
	    org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error EmptyStackException");
	} catch (IOException x) {
	    org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error E/S");
	}
    }

    public void setShowNov(boolean _showNov) {
	showNov = _showNov;
    }

    public void notificar() {
	//System.out.println(ResourcesManager.class.getResource("sounds/pop.wav"));
	//Applet.newAudioClip(ResourcesManager.class.getResource("sounds/pop.wav")).play();
    }
    /*  public TimerTask tareaShow()
  {
    //this.setAlwaysOnTop(true); //Reciï¿½n en Java 1.5
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
    // Not showing in taskbar //En Windows funciona asï¿½ por defecto
    // Extend to other applications //Supongo que mandando un JFrame o BasicDialog por parï¿½metro

}
