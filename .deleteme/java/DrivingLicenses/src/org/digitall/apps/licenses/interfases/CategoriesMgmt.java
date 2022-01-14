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
 * CategoriesMgmt.java
 *
 * */
package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.apps.licenses.classes.Category;


public class CategoriesMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel("Agregar/Modificar Categoría");
    
    private TFInput tfName = new TFInput(DataTypes.STRING,"Category",false);
    private TFInput tfPrice = new TFInput(DataTypes.DOUBLE,"Price",false);

    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    private LicensesConfigMgmt parentMgmt;
    private Category category;
    private int error = 0;
    
    public CategoriesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(371, 109));
	this.setPreferredSize(new Dimension(605, 215));
        tfPrice.setBounds(new Rectangle(260, 30, 95, 35));
        btnSaveData.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSaveData_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnClose_actionPerformed(e);
				 }

			     }
	);
	tfName.setBounds(new Rectangle(10, 30, 230, 35));
	content.setBounds(new Rectangle(5, 0, 595, 175));
	content.setLayout(null);
        content.add(tfPrice, null);
        content.add(tfName, null);
        this.add(content, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSaveData);

    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar o modificar una Categoría");
    }

    
    private void loadData(){
	tfName.setValue(category.getName());
        tfPrice.setValue(category.getPrice());
    }
    
    private boolean control(){
        boolean result = true;
        if (tfName.getString().equals(""))  {
            error = 1;
            result = false;
        } else if(tfPrice.getAmount() <= 0) {
            error = 2;
            result = false;
        }
        return result;
    }
    
    public boolean saveData(){
        if (control())  {
            category.setName(tfName.getString());
            category.setPrice(tfPrice.getAmount());
            if (category.saveData() > 0)  {
                return true;    
            } else  {
                error = 3;
                return false;
            }
        } else  { 
            return false;    
        }
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
        if (saveData())  {
            parentMgmt.refresh();
            getParentInternalFrame().close();
        } else {
            switch (error)  {
                case 1: Advisor.messageBox("El campo Categoría está vacio","Aviso");
                    break;
                case 2: Advisor.messageBox("El valor del campo Precio no es correcto","Aviso");
                    break;
                case 3: Advisor.messageBox("Ocurrió un erro al grabar los datos","Error");
                    break;
            }
        }
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    public void setCategory(Category _category) {
	category = _category;
        if (category.getIdcategory() != -1)  {
            loadData();
        }
    }

    public void setParentList(LicensesConfigMgmt _parentMgmt) {
	this.parentMgmt = _parentMgmt;
    }

}
