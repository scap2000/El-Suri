package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.icons.IconTypes;
import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.apps.personalfiles.interfaces.DependenciaTree;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class PopupMenuResourcesAsigned extends JPopupMenu{
    // se ocupan items para cada menu o columna

    private JMenuItem miReasigned = new JMenuItem("Reasignar");
    private JMenuItem miLiberate = new JMenuItem("Liberar");
    private JMenuItem miExit = new JMenuItem("Cancelar");
    private Resource recurso;
    private Persona persona;
    private Dependencia dependencia;

    public PopupMenuResourcesAsigned() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
	this.add(miReasigned);
	this.add(miLiberate);
	this.add(miExit);
	miReasigned.setForeground(Color.white);
	miLiberate.setForeground(Color.white);
	miExit.setForeground(Color.white);
	miReasigned.setIcon(IconTypes.assignRight_16x16);
	miLiberate.setIcon(IconTypes.stepIcon_On_16x16);
	miExit.setIcon(IconTypes.close_16x16);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miReasigned.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickReasigned();
		}
	    });
	miLiberate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickLiberate();
		}
	    });
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
    }
    
    private void clickReasigned(){
	ReasignedPanel reasignedPanel = new ReasignedPanel(recurso,persona,dependencia);
	ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Reasignación de Patrimonio");
	stockMgmtContainer.setCentralPanel(reasignedPanel);
	stockMgmtContainer.show();              
    }
    
    private void clickLiberate(){
	ResourcesReleasePanel libertyPanel = new ResourcesReleasePanel(recurso,persona,dependencia);
	ExtendedInternalFrame stockMgmtContainer = new ExtendedInternalFrame("Liberación de Patrimonio");
	stockMgmtContainer.setCentralPanel(libertyPanel);
	stockMgmtContainer.show();              
    }
    
    private void clickExit(MouseEvent e){
	
    }

    public void setRecurso(Resource recurso) {
	this.recurso = recurso;
    }

    public Resource getRecurso() {
	return recurso;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }

}
