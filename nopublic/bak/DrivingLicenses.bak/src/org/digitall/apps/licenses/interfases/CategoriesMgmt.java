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
