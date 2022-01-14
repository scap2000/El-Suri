package org.digitall.lib.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class SplashWindow extends BasicDialog {

    private BasicTitleLabel lblLoading = new BasicTitleLabel();
    private BasicTitleLabel lblImage = new BasicTitleLabel(IconTypes.splash_background);
    private BasicTitleLabel lblLine1 = new BasicTitleLabel();
    private BasicTitleLabel lblLine2 = new BasicTitleLabel();
    private BasicTitleLabel lblLine3 = new BasicTitleLabel();
    private BasicTitleLabel lblLine4 = new BasicTitleLabel();

    public SplashWindow() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() {
	setUndecorated(true);
	setLayout(new BorderLayout());
	lblImage.add(lblLine1, null);
	lblImage.add(lblLine2, null);
	lblImage.add(lblLine3, null);
	lblImage.add(lblLine4, null);
	this.getContentPane().add(lblLoading, null);
	this.getContentPane().add(lblImage, BorderLayout.CENTER);
	addMouseListener(new MouseAdapter() {

		    public void mousePressed(MouseEvent e) {
			setVisible(false);
			dispose();
		    }

		});
	//		final int pause = waitTime;
	/*final int pause = 15000;
	final Runnable closerRunner = new Runnable() {

		public void run() {
		    setVisible(false);
		    //dispose();
		}

	    };
	Runnable waitRunner = new Runnable() {

		public void run() {
		    try {
			Thread.sleep(pause);
			SwingUtilities.invokeAndWait(closerRunner);
		    } catch (Exception e) {
			e.printStackTrace();
			// can catch InvocationTargetException
			// can catch InterruptedException
		    }
		}

	    };
	Thread splashThread = new Thread(waitRunner, "SplashThread");*/
	lblLoading.setFont(new Font("Lucida Sans", Font.PLAIN, 10));
	lblLoading.setBounds(new Rectangle(255, 275, 200, 15));
	lblLoading.setHorizontalAlignment(SwingConstants.RIGHT);
	lblLoading.setText("Loading . . .    ");
	lblLine4.setText("Version: " + Environment.SYSTEM_VERSION);
	this.setForeground(Color.white);
	setBackground(Color.white);
	getRootPane().setBackground(Color.white);
	//lblImage.setOpaque(true);
	//lblImage.setBackground(Color.white);
	lblImage.setBounds(new Rectangle(0, 0, 460, 310));
	this.getContentPane().setLayout(null);
	//pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//Dimension labelSize = lblImage.getPreferredSize();
	lblImage.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			lblImage_mouseClicked(e);
		    }

		});
	lblLine1.setText("2007 - 2010 DIGITALL S.H.");
	lblLine2.setText("Todos los derechos reservados");
	lblLine3.setText("DIGITALL, DESKTOP y sus respectivos logos son propiedad de DIGITALL S.H.");
	lblLine1.setBounds(new Rectangle(10, 260, 265, 15));
	lblLine2.setBounds(new Rectangle(10, 275, 265, 15));
	lblLine3.setBounds(new Rectangle(10, 290, 375, 15));
	lblLine4.setBounds(new Rectangle(255, 255, 190, 15));
	lblLine1.setFont(new Font("Lucida Sans", 0, 9));
	lblLine2.setFont(new Font("Lucida Sans", 0, 9));
	lblLine3.setFont(new Font("Lucida Sans", 0, 9));
	lblLine4.setFont(new Font("Lucida Sans", 0, 9));
	lblLine4.setHorizontalAlignment(SwingConstants.RIGHT);
	this.addFocusListener(new FocusAdapter() {

		    public void focusLost(FocusEvent e) {
			this_focusLost(e);
		    }

		});
	//setVisible(true);
	//this.setBounds(new Rectangle(10, 10, 460, 310));
	this.setSize(new Dimension(460, 310));
	//splashThread.start();
    }

    private void this_focusLost(FocusEvent e) {
	//System.out.println("focuslost");
    }

    private void lblImage_mouseClicked(MouseEvent e) {
	setVisible(false);
    }

}
