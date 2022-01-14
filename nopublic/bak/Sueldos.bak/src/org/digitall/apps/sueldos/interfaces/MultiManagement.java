package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Types;
import java.util.Vector;
import javax.swing.JPanel;

import org.digitall.apps.sueldos.classes.ConceptosLegajos;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class MultiManagement extends BasicPrimitivePanel {

    private JPanel jPanel1 = new JPanel();
    private BasicPanel searchPanel = new BasicPanel("Buscar");
    private BorderLayout borderLayout1 = new BorderLayout();

    private int[] sizeColumnList = { 75, 387, 129};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Conceptos", dataRow);
    private Vector headerList = new Vector();
    
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Persons", false);
    private CBInput cbTiposConceptos = new CBInput(0,"Conceptos",false);
    private FindButton btnSearch = new FindButton();
    
    private SaveDataButton btnSaveEmpleado = new SaveDataButton();
    private DeleteButton btnClear = new DeleteButton();
    private CloseButton btnClose = new CloseButton();
    
    private String mensaje = "Seleccione el Tipo de Concepto";    
    

    public MultiManagement() {
	try {
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
	searchPanel.add(btnSearch, null);
	searchPanel.add(tfSearch, null);
	searchPanel.add(cbTiposConceptos, null);
	jPanel1.add(searchPanel, BorderLayout.NORTH);
        jPanel1.add(listPanel, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.CENTER);
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(1, 70));
	cbTiposConceptos.setBounds(new Rectangle(380, 20, 265, 35));
        tfSearch.setBounds(new Rectangle(10, 20, 295, 35));
	tfSearch.getTextField().addKeyListener(new KeyAdapter() {
					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    buscar();
					    }
					}
	);      
        btnSearch.setBounds(new Rectangle(310, 30, 35, 30));
	btnSearch.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }
			     }
	);
	setHeaderList();
        addButton(btnClose);
	addButton(btnClear);
        addButton(btnSaveEmpleado);
        btnSaveEmpleado.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnSaveEmpleado_actionPerformed(e);
                                }
                            }
        );
	btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClear_actionPerformed(e);
				}
			    }
	);
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }
                            }
        );
	cbTiposConceptos.autoSize();
	loadComboTipos();
	cbTiposConceptos.setSelectedValue(mensaje);
	cbTiposConceptos.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    if(!cbTiposConceptos.getCombo().getSelectedItem().toString().toUpperCase().equals(mensaje.toUpperCase())){
			cbTiposConceptos.getCombo().removeItem(mensaje);
			buscar();
		    }
		}
	    }
	});
        btnSaveEmpleado.setEnabled(true);
        btnClose.setEnabled(true);
	listPanel.addEditableColumn(3, Types.DOUBLE);
	listPanel.getTable().addKeyListener(new KeyAdapter() {

			  public void keyPressed(KeyEvent e) {
			      keyTyped(e, listPanel.getTable().getSelectedRow());
			  }

			  public void keyReleased(KeyEvent e) {
			      keyTyped(e, listPanel.getTable().getSelectedRow());
			  }

			  public void keyTyped(KeyEvent e, int _selectedRow) {
			      try {
				  if (_selectedRow != -1) {
				      if (listPanel.getTable().isEditing()) {
					    listPanel.getTable().getCellEditor().stopCellEditing();
				      } else if ((e.getKeyCode() == KeyEvent.VK_DELETE) || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					  if (listPanel.getTable().isCellEditable(_selectedRow,listPanel.getTable().getSelectedColumn())) {
					    listPanel.getTable().setValueAt("", _selectedRow, listPanel.getTable().getSelectedColumn());
					  }
				      }
				  }
				  
			      } catch (ArrayIndexOutOfBoundsException x) {
				  e.consume();
			      }
			  }

		      }
	);
    }
    
    private void loadComboTipos(){
	cbTiposConceptos.loadJCombo("sueldos.loadcombotiposcargas","");
	cbTiposConceptos.addItem(mensaje);
    }

    private void setHeaderList() {
	String params = "-1,-1,''";
	headerList.removeAllElements();
	headerList.addElement("*");             //idConceptoLegajo
	headerList.addElement("*");             //idLegajo
	headerList.addElement("*");             //idConcepto
	headerList.addElement("Legajo");        //Numero de Legajo
	headerList.addElement("Empleado");     //Empleado
	headerList.addElement("$ Importe");     //valor
	headerList.addElement("*");             //idtablareferenciada
	headerList.addElement("*");             //idtipoconcepto
	listPanel.setParams("sueldos.getAllLegajosForActualConcept", params, headerList);

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

	//listPanel.refresh(params);
    }
    
    public void refresh() {
	String search = tfSearch.getValue().toString();
	String params = ""+cbTiposConceptos.getSelectedValueRef() + "," + cbTiposConceptos.getSelectedValue() + ",'"+search+"'";
	listPanel.refresh(params);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	buscar();
    }
    
    private void buscar(){
	if(!cbTiposConceptos.getSelectedValue().equals("")){
	    refresh();   
	}else{
	    Advisor.messageBox("Debe seleccionar un tipo de concepto","Aviso");
	}
    }

    private void btnSaveEmpleado_actionPerformed(ActionEvent e) {
        saveEmpleados();
    }
    
    private void btnClear_actionPerformed(ActionEvent e) {
	clear();
    }
    
    private void clear(){
	boolean error = false;
	Vector seleccionados = listPanel.getSelectedsVector();
	listPanel.selectAllItems(false);
	listPanel.selectAllItems(true);
	if(seleccionados.size() > 0){
	    if(Advisor.question("Limpiar valores", "¿Desea limpiar los valores de "+ cbTiposConceptos.getSelectedItem()+ " ?")){
		String params = "" +cbTiposConceptos.getSelectedValueRef() + "," + cbTiposConceptos.getSelectedValue() + ",''";
		listPanel.refresh(params);
		listPanel.selectAllItems(false);
		listPanel.selectAllItems(true);
		if(seleccionados.size() > 0){
		    for(int i = 0; i < seleccionados.size(); i++){
			Vector selectedFila = (Vector)seleccionados.elementAt(i);
			ConceptosLegajos conceptoLegajo = new ConceptosLegajos();
			conceptoLegajo.setIdconceptolegajo(Integer.parseInt(selectedFila.elementAt(0).toString()));
			conceptoLegajo.retrieveData();
			conceptoLegajo.setValor(0.0);
			if(conceptoLegajo.saveData() == -1){
			    error = true;
			}
		    }
		    if(!error){
			Advisor.messageBox("Se grabaron los datos exitosamente.","Datos grabados");    
			refresh();
		    }else{
			Advisor.messageBox("Se produjo un error al grabar los datos.","Error");    
		    }
		}else{
		    Advisor.messageBox("No existen datos para modificar.","Sin datos");    
		}
	    }
	}
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        this.getParentInternalFrame().close();
    }
    
    private void saveEmpleados(){
	confirmSave();
    }
    
    private void confirmSave(){
	boolean error = false;
	Vector seleccionados = listPanel.getSelectedsVector();
	listPanel.selectAllItems(false);
	listPanel.selectAllItems(true);
	if(seleccionados.size() > 0){
	    if(Advisor.question("Guardar Cambios","¿Desea guardar los cambios?")){
		for(int i = 0; i < seleccionados.size(); i++){
		    Vector selectedFila = (Vector)seleccionados.elementAt(i);
		    ConceptosLegajos conceptoLegajo = new ConceptosLegajos();
		    conceptoLegajo.setIdconceptolegajo(Integer.parseInt(selectedFila.elementAt(0).toString()));
		    conceptoLegajo.retrieveData();
		    conceptoLegajo.setValor(Double.parseDouble(selectedFila.elementAt(5).toString()));
		    if(conceptoLegajo.saveData() == -1){
			error = true;
		    }
		}
		if(!error){
		    Advisor.messageBox("Se grabaron los datos exitosamentes.","Datos grabados");    
		    refresh();
		}else{
		    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");    
		}
	    }
	}
	listPanel.selectAllItems(false);
    }

}
