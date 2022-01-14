package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JScrollList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.sql.LibSQL;

public class PanelReciboPreview extends BasicPrimitivePanel{

    private BasicPanel content = new BasicPanel();
    private JScrollList listHaberes = new JScrollList();
    private JScrollList listDescuentos = new JScrollList();
    private JLabel lbHaberes = new JLabel();
    private JLabel lbDescuentos = new JLabel();
    private AcceptButton btnAcept = new AcceptButton();
    private Vector resguadoHaberes = new Vector();
    private Vector resguadoDescuentos = new Vector();
    private Legajo legajo;

    public PanelReciboPreview() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(371, 309));
	content.setLayout(null);
	content.setSize(new Dimension(320, 375));
	content.setPreferredSize(new Dimension(320, 305));
	listHaberes.setBounds(new Rectangle(10, 20, 160, 280));
	listDescuentos.setBounds(new Rectangle(205, 20, 160, 280));
	lbHaberes.setText("Haberes");
	lbHaberes.setBounds(new Rectangle(10, 0, 160, 20));
	lbHaberes.setForeground(Color.white);
	lbHaberes.setFont(new Font("Dialog", 1, 11));
	lbHaberes.setHorizontalTextPosition(SwingConstants.CENTER);
	lbHaberes.setHorizontalAlignment(SwingConstants.CENTER);
	lbDescuentos.setText("Descuentos");
	lbDescuentos.setBounds(new Rectangle(205, 0, 160, 20));
	lbDescuentos.setForeground(Color.white);
	lbDescuentos.setFont(new Font("Dialog", 1, 11));
	lbDescuentos.setHorizontalTextPosition(SwingConstants.CENTER);
	lbDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
	btnAcept.setBounds(new Rectangle(175, 265, 25, 35));
	btnAcept.setToolTipText("Guardar Modelo");
	btnAcept.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnAccept_actionPerformed(e);
			       }
			   }
	);
	content.add(btnAcept, null);
	content.add(lbDescuentos, null);
	content.add(lbHaberes, null);
	content.add(listDescuentos, null);
	content.add(listHaberes, null);
	cargarListasForUser();
	this.add(content,BorderLayout.CENTER);
    }
    
    public void cargarListasForUser(){
	resguadoHaberes = LibSQL.getVector("sueldos.getHaberesForUser","");
	resguadoDescuentos = LibSQL.getVector("sueldos.getDescuentosForUser","");
	listHaberes.cargarLista(resguadoHaberes);
	listDescuentos.cargarLista(resguadoDescuentos);
    }
    
    public void cargarListasPorLegajo(){
        resguadoHaberes = LibSQL.getVector("sueldos.getAllHaberesForLegajo","" +  legajo.getidLegajo());
        resguadoDescuentos = LibSQL.getVector("sueldos.getAllDescuentosForLegajo","" +  legajo.getidLegajo());
        listHaberes.cargarLista(resguadoHaberes);
        listDescuentos.cargarLista(resguadoDescuentos);
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {       
	//grabar modelo para el usuario
	if(grabarModelo()){
	    Advisor.messageBox("Se grabó con éxito el modelo.","Modelo grabado");
	}else{
	    Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
	}
    }
    
    public boolean grabarModelo(){
	String params = "'" + vectorToParams(suma(listHaberes.getAllElementsToString(),listDescuentos.getAllElementsToString())) + "'";
	boolean result = LibSQL.getBoolean("sueldos.saveConceptModel",params);
	if(result){
	    resguadoHaberes = listHaberes.getAllElementsToString();
	    resguadoDescuentos = listDescuentos.getAllElementsToString();
	}
	return(result);
    }
    
    public JList getListHaberes(){
	return(listHaberes.getList());
    }
    
    public JList getListDescuentos(){
	return(listDescuentos.getList());
    }
    
    private Vector suma(Vector _vec1, Vector _vec2){
	Vector resultado = new Vector();
	for(int i = 0; i < _vec1.size(); i++){
	    resultado.add(_vec1.elementAt(i).toString());
	}
	for(int i = 0; i < _vec2.size(); i++){
	    resultado.add(_vec2.elementAt(i).toString());
	}
	return(resultado);
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	return(resultado);
    }

    public Vector getResguadoHaberes() {
	return resguadoHaberes;
    }

    public Vector getResguadoDescuentos() {
	return resguadoDescuentos;
    }

    public JScrollList get_listHaberes() {
	return listHaberes;
    }

    public JScrollList get_listDescuentos() {
	return listDescuentos;
    }
    
    public boolean hayCambios(){
	boolean resultado = false;
	Vector actualHaberes = listHaberes.getAllElementsToString();
	Vector actualDescuentos = listDescuentos.getAllElementsToString();
	if((!equalsVector(actualHaberes,resguadoHaberes))||(!equalsVector(actualDescuentos,resguadoDescuentos))){
	    resultado = true;
	}
	return(resultado);
    }
    
    private boolean equalsVector(Vector vector1, Vector vector2){
	boolean hayDiferencia = false;
	int i = 0;
	int fin = 0;
	if(vector1.size() != vector2.size()){
	    hayDiferencia = true;
	}
	fin = vector1.size();
	while((i < fin)&&(!hayDiferencia)){
	    if(!vector1.elementAt(i).toString().equals(vector2.elementAt(i).toString())){
		hayDiferencia = true;
	    }
	    i++;
	}
	return(!hayDiferencia);
    }
    
    public Vector getActualHaberes(){
	return(listHaberes.getAllElementsToString());
    }
    
    public Vector getActualDescuentos(){
	return(listDescuentos.getAllElementsToString());
    }

    public void setLegajo(Legajo legajo) {
        this.legajo = legajo;
    }
}
