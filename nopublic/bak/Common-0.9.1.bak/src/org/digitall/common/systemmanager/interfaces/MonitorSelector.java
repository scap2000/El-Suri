package org.digitall.common.systemmanager.interfaces;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicContainer;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicMenu;
import org.digitall.lib.components.basic.BasicMenuBar;
import org.digitall.lib.components.basic.BasicMenuItem;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class MonitorSelector extends JDialog {

    private BasicCombo cbMonitor;
    private GraphicsDevice[] devices;
    private BasicTitleLabel lblTitle = new BasicTitleLabel();
    private BasicCheckBox chkRemember = new BasicCheckBox();
    private AcceptButton btnAccept = new AcceptButton();
    private BasicMenuBar jMenuBar1 = new BasicMenuBar();
    private BasicMenu jMenu1 = new BasicMenu();
    private BasicMenuItem jMenuItem1 = new BasicMenuItem();
    private BasicMenu jMenu2 = new BasicMenu();
    private JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();

    public MonitorSelector(GraphicsDevice[] _devices) {
	super(new JDialog(), "Selección de pantalla", true);
	devices = _devices;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setContentPane(new BasicContainer());
	setResizable(false);
	cbMonitor = new BasicCombo();
	for (int i = 0; i < devices.length; i++) {
	    cbMonitor.addItem("Pantalla[" + i + "]:" + devices[i].getDefaultConfiguration().getBounds().width + "x" + devices[i].getConfigurations()[0].getBounds().height + "@" + devices[i].getDefaultConfiguration().getColorModel().getPixelSize() + " bit");
	}
	Environment.graphicsDevice = devices[0];
	setDefaultCloseOperation(BasicDialog.DISPOSE_ON_CLOSE);
	cbMonitor.setSelectedIndex(0);
	cbMonitor.setBounds(new Rectangle(30, 40, 335, 20));
	lblTitle.setText("Seleccione monitor");
	lblTitle.setIcon(IconTypes.RDBMS_ICON);
	lblTitle.setBounds(new Rectangle(30, 5, 335, 30));
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	chkRemember.setText("Recordar preferencia");
	//chkRemember.setSelected(true);
	chkRemember.setBounds(new Rectangle(30, 65, 175, 30));
	chkRemember.setHorizontalAlignment(SwingConstants.LEFT);
	btnAccept.setBounds(new Rectangle(325, 68, 40, 25));
	btnAccept.setHorizontalAlignment(SwingConstants.RIGHT);
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	jMenu1.setText("KKK");
	jMenuItem1.setText("JJJ");
	jMenu2.setText("jMenuItem2");
	jCheckBoxMenuItem1.setText("asdasd");
	cbMonitor.addItemListener(new ItemListener() {

			       public void itemStateChanged(ItemEvent itemEvent) {
				    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					Environment.graphicsDevice = devices[cbMonitor.getSelectedIndex()];
				    }
			       }

			   });
	cbMonitor.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
	        Environment.graphicsDevice = devices[cbMonitor.getSelectedIndex()];
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    dispose();
		}
	    }
	});
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(400, 125));
	//this.setJMenuBar(jMenuBar1);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(chkRemember, null);
	this.getContentPane().add(cbMonitor,null);
	this.getContentPane().add(lblTitle, null);
	jMenu1.add(jMenuItem1);
	jMenu2.add(jCheckBoxMenuItem1);
	jMenu1.add(jMenu2);
	jMenu1.addSeparator();
	jMenuBar1.add(jMenu1);
	//setJMenuBar(jMenuBar1);
	//setLocation((devices[0].getDefaultConfiguration().getBounds().width - getWidth()) / 2, (devices[0].getDefaultConfiguration().getBounds().height - getHeight()) / 2);
	ComponentsManager.centerWindow(this);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	dispose();
    }
    
    public void dispose() {
	if (chkRemember.isSelected()) {
	    Environment.cfg.setProperty("Screen", String.valueOf(cbMonitor.getSelectedIndex()));
	}
	System.out.println("Pantalla seleccionada: " + cbMonitor.getSelectedIndex() + " --> " + Environment.graphicsDevice.getDefaultConfiguration().getBounds());
	super.dispose();
    }

}
