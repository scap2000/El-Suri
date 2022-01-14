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
 * PopupMenuResourcesAsigned.java
 *
 * */
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
