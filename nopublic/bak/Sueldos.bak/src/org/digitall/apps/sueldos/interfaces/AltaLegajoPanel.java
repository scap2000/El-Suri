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
