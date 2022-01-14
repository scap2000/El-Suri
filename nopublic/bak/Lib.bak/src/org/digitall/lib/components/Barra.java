package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

//

public class Barra extends BasicDialog {

    private JProgressBar jbarra = new JProgressBar();
    private Component x = null;
    private BasicLabel imagen2 = new BasicLabel(IconTypes.statusBarIcon_16x16);
    private BasicLabel jLabel1 = new BasicLabel();
    private CloseButton bcancelar = new CloseButton();
    private Thread tarea;
    protected boolean cancelar = false;
    private BasicPanel jPanel1 = new BasicPanel();

    public Barra(Component jd) {
	try {
	    x = jd;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	x.setEnabled(false);
	imagen2.setBounds(new Rectangle(13, 5, 40, 35));
	jLabel1.setText("Realizando busqueda, espere por favor...");
	jLabel1.setBounds(new Rectangle(66, 10, 252, 15));
	bcancelar.setText("Cancelar");
	bcancelar.setBounds(new Rectangle(229, 75, 96, 25));
	bcancelar.setMargin(new Insets(2, 3, 2, 14));
	bcancelar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcancelar_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(10, 40, 315, 20));
	jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jPanel1.setLayout(null);
	//x.setVisible(false);
	this.setSize(new Dimension(349, 134));
	this.getContentPane().setLayout(null);
	this.setTitle("Buscando...");
	jbarra.setBounds(new Rectangle(5, 5, 305, 10));
	jbarra.setForeground(new Color(41, 13, 151));
	jbarra.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	jbarra.setBackground(Color.white);
	jbarra.setToolTipText("Buscando....");
	jPanel1.add(jbarra, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bcancelar, null);
	this.getContentPane().add(jLabel1, null);
	this.getContentPane().add(imagen2, null);
	jbarra.setIndeterminate(true);
	// tarea.start();
    }

    public void disposeme() {
	x.setEnabled(true);
	//x.setVisible(true);
	this.dispose();
    }

    public void Inicia(Thread Tarea) {
	tarea = Tarea;
	tarea.start();
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
	tarea.stop();
	cancelar = true;
	disposeme();
    }

    public boolean getCancelar() {
	return cancelar;
    }

}
