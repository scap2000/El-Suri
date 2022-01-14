package org.digitall.apps.cashflow.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.Login;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.environment.Environment;

//

public class Principal extends JFrame {

    private JDesktopPane desktop = new JDesktopPane();
    private BasicPanel panelCenter = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout layoutMain = new BorderLayout();

    public Principal() {
	try {
	    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Environment.graphicsDevice = env.getScreenDevices()[0];
	    Login inicio = new Login("Login", "CashFlow", true, false);
	    org.digitall.lib.components.ComponentsManager.centerWindow(inicio);
	    inicio.setModal(true);
	    inicio.show();	    
	    if (inicio.getValidado()) {
		jbInit();
	    } else {
		System.exit(0);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getContentPane().setLayout(layoutMain);
	this.setSize(new Dimension(1000, 650));
	this.setTitle("Desktop");
	panelCenter.setLayout(borderLayout1);
	panelCenter.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	desktop.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelCenter.add(desktop, BorderLayout.CENTER);
	this.getContentPane().add(panelCenter, BorderLayout.CENTER);
	
	
    }

}
