package org.digitall.apps.logistics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JDesktopPane;

import org.digitall.lib.components.Grilla;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

//

public class InventoryPanel extends BasicContainerPanel {

    private AddButton bAdd = new AddButton();
    private CloseButton bClose = new CloseButton();
    private Component parent;
    private boolean firstLoad = true;
    private int[] tcol = { };
    private int[] tamCol = { 100, 125, 209, 75 };
    private Grilla grilla = new Grilla(30, tcol, tamCol, false, false);
    private Vector cabecera = new Vector();
    private Vector datos = new Vector();
    private JDesktopPane desktop;
    private int mode = Environment.UNSETMODE;

    public InventoryPanel(BasicDialog _parent) {
	try {
	    parent = _parent;
	    mode = Environment.STANDALONEMODE;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public InventoryPanel(BasicInternalFrame _parent, JDesktopPane _desktop) {
	try {
	    parent = _parent;
	    desktop = _desktop;
	    mode = Environment.DESKTOPMODE;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(543, 266));
	this.setBounds(new Rectangle(10, 10, 545, 266));
	bAdd.setBounds(new Rectangle(505, 240, 40, 25));
	bClose.setBounds(new Rectangle(455, 240, 40, 25));
	bClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bClose_actionPerformed(e);
		    }

		});
	bAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAdd_actionPerformed(e);
		    }

		});
	grilla.setSize(new Dimension(615, 374));
	grilla.setBounds(new Rectangle(-1, 0, 545, 240));
	this.add(grilla, BorderLayout.CENTER);
	this.add(bAdd, null);
	this.add(bClose, null);
	grilla.Redimensiona();
	grilla.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    datos = grilla.VDatos();
			    if (mode == Environment.STANDALONEMODE) {
				BasicDialog _temp = new BasicDialog();
				_temp.setModal(true);
				_temp.getContentPane().add(new InventoryDataPanel(_temp, datos.elementAt(0).toString()));
				_temp.setSize(580, 500);
				org.digitall.lib.components.ComponentsManager.centerWindow(_temp);
				_temp.show();
				refreshPanel();
			    } else if (mode == Environment.DESKTOPMODE) {
				BasicInternalFrame _temp = new BasicInternalFrame();
				_temp.getContentPane().add(new InventoryDataPanel(_temp, datos.elementAt(0).toString()));
				Rectangle rect = parent.getBounds();
				int x = (int)(rect.getX() + rect.getWidth());
				int y = (int)(rect.getY() + rect.getHeight());
				_temp.setBounds(x, y, 580, 500);
				_temp.setClosable(false);
				_temp.setResizable(true);
				_temp.setIconifiable(true);
				desktop.add(_temp);
				_temp.show();
			    }
			}
		    }

		});
	refreshPanel();
    }

    private void bOk_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void refreshPanel() {
	String consulta = "SELECT idresource, name, code, description, amount FROM inventory.resources WHERE estado <> '*' ORDER BY idresource";
	String consultaCount = "SELECT COUNT(*) FROM (" + consulta + ") AS foo";
	cabecera.removeAllElements();
	cabecera.add("*");
	cabecera.add("Nombre");
	cabecera.add("Código");
	cabecera.add("Descripción");
	cabecera.add("Cantidad");
	grilla.ActualizaTabla(parent, consulta, consultaCount, cabecera);
	firstLoad = false;
    }

    private void bAdd_actionPerformed(ActionEvent e) {
	if (mode == Environment.STANDALONEMODE) {
	    BasicDialog _temp = new BasicDialog();
	    _temp.setModal(true);
	    _temp.getContentPane().add(new InventoryDataPanel(_temp));
	    _temp.setSize(580, 500);
	    org.digitall.lib.components.ComponentsManager.centerWindow(_temp);
	    _temp.show();
	    refreshPanel();
	} else if (mode == Environment.DESKTOPMODE) {
	    BasicInternalFrame _temp = new BasicInternalFrame();
	    _temp.getContentPane().add(new InventoryDataPanel(_temp));
	    Rectangle rect = parent.getBounds();
	    int x = (int)(rect.getX() + rect.getWidth());
	    int y = (int)(rect.getY() + rect.getHeight());
	    _temp.setBounds(x, y, 580, 500);
	    _temp.setClosable(false);
	    _temp.setResizable(true);
	    _temp.setIconifiable(true);
	    desktop.add(_temp);
	    _temp.show();
	}
    }

    private void dispose() {
	if (mode == Environment.STANDALONEMODE) {
	    ((BasicDialog)parent).dispose();
	} else if (mode == Environment.DESKTOPMODE) {
	    ((BasicInternalFrame)parent).dispose();
	}
    }

    private void bClose_actionPerformed(ActionEvent e) {
	dispose();
    }

}
