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
 * ResourcesAdminList.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ResourcesAdminList extends BasicPrimitivePanel{

    private int[] sizeColumnList = {207, 54, 69, 80, 119, 82, 75, 174};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Stock de Recursos Existentes", dataRow);
    private CBInput cbSkillToProvider = new CBInput(CachedCombo.SKILLPROVIDER_TABS, "ResourceTypes");
    private TFInput tfFind = new TFInput(DataTypes.STRING, "FindResource",false);
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar Recurso");
    private FindButton btnFind = new FindButton();
    private ResourcesAdminMain parentMain;
    private Resource resource;

    public ResourcesAdminList(ResourcesAdminMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(790, 390));
	this.setPreferredSize(new Dimension(790, 390));
	setTitle("Recursos Existentes");
	listPanel.setBounds(new Rectangle(5, 80, 780, 305));
	content.setBounds(new Rectangle(5, 5, 660, 425));
	content.setLayout(null);
	findPanel.setBounds(new Rectangle(5, 5, 780, 70));
	findPanel.setLayout(null);
	tfFind.setBounds(new Rectangle(120, 25, 190, 35));
	cbSkillToProvider.setBounds(new Rectangle(325, 25, 235, 35));
	btnFind.setBounds(new Rectangle(570, 35, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	content.add(listPanel, null);
	content.add(findPanel, null);
	findPanel.add(btnFind, null);
	findPanel.add(tfFind, null);
	findPanel.add(cbSkillToProvider, null);
	this.add(content, null);
	
	cbSkillToProvider.autoSize();
	
	cbSkillToProvider.getCombo().addItem("Todos","-1");
	cbSkillToProvider.setSelectedID("-1");
	
	tfFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
		
	setHeaderList();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("ReceivedQty"));
	headerList.addElement(Environment.lang.getProperty("DeliveredQty"));
	headerList.addElement(Environment.lang.getProperty("TotalInReserveQty"));
	headerList.addElement(Environment.lang.getProperty("AvailableQty"));
	headerList.addElement(Environment.lang.getProperty("TotalStock"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		loadObject();
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    
		}
	    }
	});
	
	listPanel.setParams("resourcescontrol.getAllResourcesExist", "'',-1", headerList);
    }

    public void refresh() {
	String params = "'"+ tfFind.getString() +"',"+ cbSkillToProvider.getSelectedValue();
	listPanel.refresh(params);
    }

    private void loadObject(){
	if (!dataRow.isEmpty()){
	    resource = new Resource();
	    resource.setIdResource(Integer.parseInt(""+ dataRow.elementAt(0)));
	    resource.setName(""+ dataRow.elementAt(1));
	    resource.setSkillToProvider(new Skills(Integer.parseInt(""+ dataRow.elementAt(9))));
	    resource.setIdUnit(Integer.parseInt(""+ dataRow.elementAt(2)));
	    
	    parentMain.setResource(resource);
	    setHeaderTitle();
	}
    }
    
    private void setHeaderTitle(){
	String title = Environment.lang.getProperty("Resource") +": "+ resource.getName();
	parentMain.setTitle(title);
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setParentMain(ResourcesAdminMain parentMain) {
	this.parentMain = parentMain;
    }

}
