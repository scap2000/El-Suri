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
 * PopupWindow.java
 *
 * */
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
