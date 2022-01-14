package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.swing.JDialog;

import javax.swing.JTextField;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.classes.Funciones;
import org.digitall.projects.apps.dbadmin_091.classes.errClasses.ErrDBAdminMain;

public class FuncionesMgmt extends BasicDialog {
//public class FuncionesMgmt extends JDialog {

    private AcceptButton btnAccept = new AcceptButton();
    private AddButton btnNuevoModulo = new AddButton();
    private CloseButton btnClose = new CloseButton();
    
    private BasicScrollPane spDescripcion = new BasicScrollPane();
    private BasicPanel jPanel2 = new BasicPanel("Datos de Funcionalidad");
    
    private BasicLabel lblGrupo = new BasicLabel();
    private BasicLabel lblDescripcion = new BasicLabel();
    private BasicLabel lblModulo = new BasicLabel();
    
    private TFInput tfNumeroFuncion = new TFInput(DataTypes.STRING, "Número", false);
    private TFInput tfNombreFuncion = new TFInput(DataTypes.STRING,"Nombre",false);
    
    private JCombo cbGrupos = new JCombo();
    private JCombo cbModulos = new JCombo();
    
    private JTArea taDescripcion = new JTArea();
    
    private Funciones funciones = new Funciones();
    private ErrDBAdminMain errorMessage = new ErrDBAdminMain();
    private boolean newFunction = true;
    private String nombreGrupo = "";
    
    public FuncionesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public FuncionesMgmt(boolean _newFunction) {
	newFunction = _newFunction;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(415, 316));
	this.setTitle("Nueva Funcionalidad");
	btnAccept.setBounds(new Rectangle(325, 260, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	spDescripcion.getViewport().add(taDescripcion, null);
	jPanel2.add(spDescripcion, null);
        jPanel2.add(btnNuevoModulo, null);
        jPanel2.add(cbModulos, null);
	jPanel2.add(lblModulo, null);
	jPanel2.add(lblDescripcion, null);
	jPanel2.add(tfNumeroFuncion, null);
	jPanel2.add(cbGrupos, null);
	jPanel2.add(lblGrupo, null);
        jPanel2.add(tfNombreFuncion, null);
        this.getContentPane().add(btnClose, null);
        this.getContentPane().add(jPanel2, null);
        this.getContentPane().add(btnAccept, null);
        jPanel2.setBounds(new Rectangle(0, 0, 415, 260));
	jPanel2.setLayout(null);
	lblGrupo.setText("Grupo:");
	lblGrupo.setBounds(new Rectangle(15, 190, 75, 20));
	tfNombreFuncion.setBounds(new Rectangle(115, 70, 280, 35));
        cbGrupos.setBounds(new Rectangle(15, 210, 250, 20));
        cbGrupos.setNotAssignedItem(true);
	btnClose.setBounds(new Rectangle(370, 260, 40, 25));
	btnClose.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	tfNumeroFuncion.setBounds(new Rectangle(15, 70, 90, 35));
	tfNumeroFuncion.getTextField().setHorizontalAlignment(JTextField.RIGHT);
	tfNumeroFuncion.setEditable(false);
	//tfNumeroFuncion.setComponentName("Nº de Fu");
	lblDescripcion.setText("Descripción");
	lblDescripcion.setBounds(new Rectangle(15, 115, 145, 15));
	lblModulo.setText("Módulo");
	lblModulo.setBounds(new Rectangle(15, 20, 75, 20));
	cbModulos.setBounds(new Rectangle(15, 40, 345, 20));
	cbModulos.setSelectedID(-1);
	cbModulos.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if ((cbModulos.getSelectedIndex() > -1)&&(newFunction)) {
						    loadNumberFunctionData();
						}
					    }
					}

				    }
	);
	btnNuevoModulo.setBounds(new Rectangle(370, 35, 25, 25));
	btnNuevoModulo.setSize(new Dimension(25, 25));
	btnNuevoModulo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnNuevoModulo_actionPerformed(e);
		}
	    }
	);
	spDescripcion.setBounds(new Rectangle(15, 135, 380, 50));
	boot();
	tfNumeroFuncion.getTextField().setFocusable(false);
    }

    //************************ 
    
    private void boot(){
	//if(DBControl.existFunction("admin.getallmodulos","true")){
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	    if(newFunction){
		loadNumberFunctionData();	
	    }
	    loadComboGrupos();
	    if(cbModulos.getModel().getSize()==0){
		btnAccept.setEnabled(false);
	    }
	//}
    }
      
    private boolean saveFunctionData(){
	if (control()){
	    if (funciones == null){
		funciones = new Funciones();
	    }
	    setData();
	    if (funciones.saveData() >= 0) {
	        desvincularUsuarios();
		return true;
	    } else {
		return false;
	    }   
	} else {
	    errorMessage.showMessage();
	    return false;
	}
    }
    
    private boolean control() {
	boolean retorno = true;
	errorMessage = new ErrDBAdminMain();
	String query = "";
	if(newFunction){
	    try {
		if(tfNombreFuncion.getValue().equals("")){
		    errorMessage.setError(errorMessage.SINNOMBREFUNCION);
		    retorno = false;
		    setFocus(0);
		}
		else{
		    query = "SELECT * FROM admin.funciones WHERE nombre = '"+ tfNombreFuncion.getValue().toString()+"' AND funciones.idmodulo = "+cbModulos.getSelectedValue() + "AND funciones.estado<>'*'";
		    if (LibSQL.exQuery(query).next()) {
			errorMessage.setError(errorMessage.EXISTEFUNCION);
			retorno = false;
			setFocus(0);
		    }
		}
	    } catch (SQLException e) {
		// TODO
	    }
	}
	return retorno;
    }
    
    /**2010-01-18(moraless)*/
    private void setData(){
	funciones.setIdModulo(Integer.parseInt(cbModulos.getSelectedValue().toString()));
	funciones.setNumeroFuncion(getNumeroFuncion());
	funciones.setNombre(tfNombreFuncion.getValue().toString());
	funciones.setDescripcion(taDescripcion.getText());
	//System.out.println("SELECTED VALUE = "+cbGrupos.getSelectedItem());
	//System.out.println("SELECTED VALUE = "+cbGrupos.getSelectedValue());
	//if(cbGrupos.getSelectedValue().equals("0")){
	if(cbGrupos.getSelectedItem().equals("Sin Asignar")){
	    funciones.setGrupo("");
	}
	else{
	    funciones.setGrupo(cbGrupos.getSelectedItem().toString());
	}
    }
    
    private void desvincularUsuarios(){
	if(!cbGrupos.getSelectedItem().equals(nombreGrupo)){
	    String consultaSelect = "SELECT usename as name, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = '" + nombreGrupo + "' order by usename";
	    String consultaRevoke = "";
	    ResultSet result = LibSQL.exQuery(consultaSelect);
	    try {
		while(result.next()){
		    if(result.getBoolean("belongs")){
			consultaRevoke += "REVOKE " + nombreGrupo + " FROM " + result.getString("name") + ";\n";
		    }
		}
		if (LibSQL.exActualizar('a', consultaRevoke)) {
			
		} else {
		    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
		}
	    } catch (SQLException e) {
		// TODO
	    }
	}
    }
    
    private int getNumeroFuncion(){
	int retorno = 0;
	String numero = tfNumeroFuncion.getValue().toString();
	int posicion = numero.indexOf(".");
	retorno = Integer.parseInt(numero.substring(posicion+1));
	return retorno;
    }
    
    public boolean loadData(int _idFuncion){
	String nombreModulo = "";
	funciones.setIdFuncion(_idFuncion);
	funciones.retrieveData();
	if(funciones.getIdFuncion()!=-1){
	    this.setTitle("Modificar datos de Funcionalidad");
	    tfNombreFuncion.setValue(funciones.getNombre());
	    nombreModulo = funciones.getNombreModulo(funciones.getIdModulo());
	    tfNumeroFuncion.setValue(""+funciones.getIdModulo()+"."+funciones.getNumeroFuncion());
	    cbModulos.setSelectedValue(nombreModulo);
	    taDescripcion.setText(funciones.getDescripcion());
	    loadComboGrupos();
	    cbGrupos.setSelectedItem(funciones.getGrupo());
	    if(!funciones.getGrupo().equals("")){
	        cbGrupos.addItem(funciones.getGrupo(),"0","0");
	        cbGrupos.setSelectedIndex(cbGrupos.getModel().getSize()-1);	
	    }
	    nombreGrupo = funciones.getGrupo();
	    btnNuevoModulo.setEnabled(false);
	    cbModulos.setEnabled(false);
	    return true;
	} else {
	    return false;
	}
    }

    private void limpiarCampos() {
	tfNombreFuncion.setValue("");
	tfNumeroFuncion.setValue("");
	taDescripcion.setText("");
    }

    /**2010-01-18(moraless)*/
    private void loadComboGrupos() {
	String query = "";
	//if(newFunction){
	    query += "NOT";
	//}
	cbGrupos.loadJCombo("SELECT distinct grosysid as gid, groname as name, 0 FROM pg_group WHERE pg_group.groname " + query + " IN (SELECT admin.funciones.grupo FROM admin.funciones WHERE funciones.estado <> '*') order by groname");
        cbGrupos.setSelectedIndex(0);
	//cbGrupos.setSelectedIndex(cbGrupos.getModel().getSize()-1);
    }
    
    private void loadNumberFunctionData(){
	int idModulo = Integer.parseInt(cbModulos.getSelectedValue().toString());
	if(idModulo>-1){
	    tfNumeroFuncion.setValue(""+idModulo+"."+funciones.getSiguienteNumeroFuncion(idModulo));    
	}
    }

    private void setFocus(int _form) {
	switch (_form){
	    case 0: 
		  tfNombreFuncion.getTextField().requestFocus();
	    break;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if(saveFunctionData()){
	    if(newFunction){
		limpiarCampos();
		loadComboGrupos();
		loadNumberFunctionData();       
		tfNombreFuncion.getTextField().requestFocus();
	    }
	    else{
		this.dispose();
	    }
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void btnNuevoModulo_actionPerformed(ActionEvent e) {
	ModulosList nuevoModulo = new ModulosList();
	ComponentsManager.centerWindow(nuevoModulo);
	nuevoModulo.setModal(true);
	nuevoModulo.setVisible(true);
	cbModulos.loadJCombo("admin.getallmodulos","false");
	if(cbModulos.getModel().getSize()==0){
	    btnAccept.setEnabled(false);
	}
	else{
	    btnAccept.setEnabled(true);
	    loadNumberFunctionData();
	}
    }
}
