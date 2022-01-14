package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;

import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.Patrimonio;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JScrollList;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class PersonInError extends BasicPrimitivePanel{
    private String defaultMje = "Error las personas siguientes no cumplen la condicion esperada.";
    private String mensaje = defaultMje;
    private BasicPanel content = new BasicPanel();
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicLabel label = new BasicLabel(IconTypes.close_16x16);
    private JTArea taMesajeError = new JTArea();
    private CloseButton btnClose = new CloseButton();
    private JScrollList listPerson = new JScrollList();
    private Vector listado = new Vector();

    public PersonInError(String _mje,Vector _listado) {
	mensaje = _mje;
	listado = _listado;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public PersonInError() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public PersonInError(Vector _listado) {
	try {
	    listado = _listado;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	content.setLayout(new BorderLayout());
	content.setSize(new Dimension(500, 300));
	content.setPreferredSize(new Dimension(500, 300));
	panelNorte.setSize(new Dimension(500, 100));
	panelNorte.setBounds(new Rectangle(0, 0, 500, 100));
	panelNorte.setPreferredSize(new Dimension(500, 100));
	panelNorte.setLayout(null);
	panelCentro.setLayout(null);
	panelNorte.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setBorder(BorderPanel.getBorderPanel(""));
	panelCentro.setSize(new Dimension(500, 209));
	label.setBounds(new Rectangle(5, 30, 40, 35));
	label.setText("");
	taMesajeError.setText(mensaje);
	taMesajeError.setBounds(new Rectangle(50, 10, 490, 75));
	taMesajeError.setBackground(new Color(64, 64, 64));
	taMesajeError.setFont(new Font("Dialog", 1, 12));
	taMesajeError.setForeground(Color.white);
	taMesajeError.setEditable(false);
	listPerson.setBounds(new Rectangle(10, 5, 530, 195));
	panelNorte.add(taMesajeError, null);
	panelNorte.add(label,null);
	btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}
				    }
	);
	this.setSize(new Dimension(553, 346));
	content.add(panelNorte,BorderLayout.NORTH);
	panelCentro.add(listPerson, null);
	content.add(panelCentro, BorderLayout.CENTER);
	this.add(content, BorderLayout.CENTER);
	if(listado.size() > 0){
	    cargarListaPersonas(listado);   
	}
	
	addButton(btnClose);
    }
    
    public void cargarListaPersonas(Vector _listado){
	listPerson.cargarLista(_listado);
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
	taMesajeError.setText(mensaje);
    }

    public String getMensaje() {
	return mensaje;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    
}
