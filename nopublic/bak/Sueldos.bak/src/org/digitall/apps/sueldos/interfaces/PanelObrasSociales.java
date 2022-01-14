package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Types;
import java.util.Vector;
import javax.swing.JPanel;
import org.digitall.apps.sueldos.classes.ConceptosObrasSociales;
import org.digitall.apps.sueldos.classes.ContentConceptObrasSociales;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PanelObrasSociales extends BasicPrimitivePanel {

    private JPanel jPanel1 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private int[] sizeColumnList = { 383, 129, 129};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "SearchButton", false);
    private ContentConceptObrasSociales contentResguardo = new ContentConceptObrasSociales();
    private ContentConceptObrasSociales contentActual = new ContentConceptObrasSociales();
    private GridPanel listPanel = new GridPanel(1000, sizeColumnList, "Obras Sociales", dataRow){
	public void finishLoading(){
	    config.checkReady();
	}
    };
    private ConfiguracionBase config;

    public PanelObrasSociales(ConfiguracionBase _config) {
	try {
	    config = _config;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(660, 354));
	this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        jPanel1.setLayout(borderLayout1);
        jPanel1.add(listPanel, BorderLayout.CENTER);
	setHeaderList();
        this.add(jPanel1, BorderLayout.CENTER);
        tfNumber.setBounds(new Rectangle(75, 15, 295, 35));
	listPanel.addEditableColumn(2, Types.DOUBLE);
	listPanel.addEditableColumn(3, Types.DOUBLE);
	//cargarContent(contentResguardo,false);
	
    }

    private void setHeaderList() {
	String params = "";
	headerList.removeAllElements();
	headerList.addElement("*"); 		//idconceptoreferencias
	headerList.addElement("Nombre");	//name
	headerList.addElement("$ Importe");	//value
	headerList.addElement("% Porcentaje");	//porcentaje
	headerList.addElement("*");		//debehaber
	headerList.addElement("*");		//orden
	headerList.addElement("*");             //isSetForLegajo
	listPanel.setParams("sueldos.getAllObrasSociales", params, headerList);

	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);

	listPanel.refresh(params);
    }
    
    public void started(){
	cargarContent(contentResguardo,false);
    }
    
    private void cargarContent(ContentConceptObrasSociales _contentConcept, boolean _fromGrid){
	listPanel.selectAllItems(false);
	listPanel.selectAllItems(true);
	Vector seleccionados = listPanel.getSelectedsVector();
	_contentConcept.clear();
	if(_fromGrid){
	    for(int i = 0; i < seleccionados.size(); i++){
		Vector seleccionado = (Vector)seleccionados.elementAt(i);
		ConceptosObrasSociales conceptoObraSocial = new ConceptosObrasSociales();
		conceptoObraSocial.setIdconceptoreferencia(Integer.parseInt(seleccionado.elementAt(0).toString()));
		conceptoObraSocial.retrieveData();
		conceptoObraSocial.setValue(Double.parseDouble(seleccionado.elementAt(2).toString()));
		conceptoObraSocial.setPercentage(Double.parseDouble(seleccionado.elementAt(3).toString()));
		_contentConcept.addConcepto(conceptoObraSocial);
	    }   
	}else{
	    for(int i = 0; i < seleccionados.size(); i++){
		Vector seleccionado = (Vector)seleccionados.elementAt(i);
		ConceptosObrasSociales conceptoObraSocial = new ConceptosObrasSociales();
		conceptoObraSocial.setIdconceptoreferencia(Integer.parseInt(seleccionado.elementAt(0).toString()));
		conceptoObraSocial.retrieveData();
		_contentConcept.addConcepto(conceptoObraSocial);
	    }   
	}
	listPanel.selectAllItems(false);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnAddObraSocial_actionPerformed(ActionEvent e) {
	
    }

    private void btnEditObraSocial_actionPerformed(ActionEvent e) {
        
    }
    private void btnDeleteObraSocial_actionPerformed(ActionEvent e) {
        
    }
    
    private void btnSaveObraSocial_actionPerformed(ActionEvent e) {
	//saveObrasSociales();
    }
    private void btnClose_actionPerformed(ActionEvent e) {
	cargarContent(contentResguardo,false);
	//confirmSave();
    }
    
    public void saveObrasSociales(boolean _exit){
	confirmSave(_exit);
    }
    
    private void confirmSave(boolean _exit){
	cargarContent(contentActual,true);
	if(!contentResguardo.equals(contentActual)){
	    if(Advisor.question("Guardar cambios","¿desea guardar los cambios de OBRAS SOCIALES?")){
		if(contentActual.save()){
		    refresh();
		    cargarContent(contentResguardo,false);
		    Advisor.messageBox("Se guardaron los cambios de la pestaña OBRAS SOCIALES","Datos grabados");
		}else{
		    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
		}
	    }else{
	        refresh();
	        cargarContent(contentResguardo,false);
	    }
	}else{
	    if(!_exit){
		Advisor.messageBox("No se realizaron cambios en la pestaña OBRAS SOCIALES","Sin cambios");
	    }
	}
    }

}
