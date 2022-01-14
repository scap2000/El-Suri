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
 * AltaLegajoPanel.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class AltaLegajoPanel extends BasicPrimitivePanel{

    private CBInput cbPersonas = new CBInput(0,"Persons");
    private TFInput tfLegajo = new TFInput(DataTypes.INTEGER, "Nº Legajo",false);
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Legajo legajo = new Legajo();
    private LegajosSearchPanel legajosSearchPanel = null;

    public AltaLegajoPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public AltaLegajoPanel(LegajosSearchPanel _legajosSearchPanel) {
	legajosSearchPanel = _legajosSearchPanel;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	content.setLayout(null);
	btnSave.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }
			   }
	);
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
	this.setSize(new Dimension(452, 104));
	tfLegajo.setPreferredSize(new Dimension(61, 35));
	tfLegajo.setBounds(new Rectangle(10, 15, 70, 35));
	tfLegajo.setSize(new Dimension(70, 35));
	tfLegajo.setValue((legajo.getLastNum() + 1));
	cbPersonas.setBounds(new Rectangle(100, 15, 340, 35));
	cbPersonas.setPreferredSize(new Dimension(250, 50));
	cbPersonas.autoSize();
	loadComboPersonas();
	content.add(cbPersonas,null);
	content.add(tfLegajo, null);
	addButton(btnClose);
	addButton(btnSave);
	this.add(content,null);
    }
    
    private void loadComboPersonas(){
	cbPersonas.loadJCombo("personalfiles.getAllPersonal","true");
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {       
	grabarLegajo(Integer.parseInt(tfLegajo.getTextField().getText().trim()));
    }
    
    private void grabarLegajo(int _numero) {
	if(!legajo.existNumber(_numero)){
	    int idPerson = Integer.parseInt(cbPersonas.getSelectedValue().toString());
	    if(!legajo.tieneUnicoLegajoActivo(idPerson)){
	        legajo.setidPerson(idPerson);
	        legajo.setNumero(_numero);
	        legajo.setesActivo(true);
	        if(legajo.saveData() != -1){
	            if(legajosSearchPanel != null){
	               legajosSearchPanel.upDate(); 
	               legajosSearchPanel.setActualLegajo(_numero);
	            }
		    tfLegajo.setValue((legajo.getLastNum() + 1));		
	            Advisor.messageBox("Se grabó con éxito el legajo Nº: "+_numero,"Legajo grabado");
	        }else{
	            Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
	        }	
	    }else{
	        Advisor.messageBox("La persona : "+cbPersonas.getSelectedItem()+" ya tiene un legajo  activo.","Error");
	    }
	}else{
	    Advisor.messageBox("Ya existe el legajo numero: "+_numero,"Error");
	}
    }
    private void btnClose_actionPerformed(ActionEvent e) {      
	getParentInternalFrame().close();
    }

    public void setLegajosSearchPanel(LegajosSearchPanel legajosSearchPanel) {
	this.legajosSearchPanel = legajosSearchPanel;
    }

    public LegajosSearchPanel getLegajosSearchPanel() {
	return legajosSearchPanel;
    }
}
