package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.digitall.lib.components.basic.BasicInternalFrame;

public class BasicDialog extends JDialog {

    private boolean needSaving = false;

    public BasicDialog() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(BasicDialog _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Frame _parent, String _title) {
	super(_parent, _title);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(JFrame _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Frame _parent, String _title, boolean _modal) {
	super(_parent, _title, _modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicDialog(Dialog _parent, String _title, boolean _modal) {
	super(_parent, _title, _modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//getContentPane().setBackground(BasicConfig.INTERNALFRAME_BACKGROUND_COLOR);
	setContentPane(new BasicContainer());
	setDefaultCloseOperation(BasicInternalFrame.DO_NOTHING_ON_CLOSE);
	//setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	//setUndecorated(true);
	getLayeredPane().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	addWindowListener(new WindowAdapter() {

			    public void windowClosing(WindowEvent e) {
				closeBasicDialog(true);
			    }

			}
	);
    }

    public void closeBasicDialog() {
	dispose();///???
    }

    public void closeBasicDialog(boolean _askSaveData) {
	if (_askSaveData && needSaving) {
	    int answer = JOptionPane.showConfirmDialog(this, "Â¿Desea guardar los cambios?", "Cerrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (answer == JOptionPane.OK_OPTION) {
		if (saveData()) {
		    closeBasicDialog();
		}
	    } else if (answer == JOptionPane.NO_OPTION) {
		closeBasicDialog();
	    }
	} else {
	    closeBasicDialog();
	}
    }

    public boolean saveData() {
	//GRABAR LA INFO
	return true;
    }

    public void setNeedSaving(boolean _needSaving) {
	needSaving = _needSaving;
    }

    /* protected void paintComponent(Graphics g) {
	 Graphics2D g2 = (Graphics2D)g;
	 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	 int w = getWidth();
	 int h = getHeight();
	 GradientPaint gradient = new GradientPaint(20, 0, BasicConfig.PANEL_GRADIENT_START_COLOR, 20, h, BasicConfig.PANEL_GRADIENT_END_COLOR, false);
	 g2.setPaint(gradient);
	 g2.fillRect(0, 0, w, h);
	 super.paintComponent(g);
     }*/

}
