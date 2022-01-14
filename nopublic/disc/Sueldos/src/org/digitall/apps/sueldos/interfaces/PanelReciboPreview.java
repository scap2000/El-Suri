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
 * PanelReciboPreview.java
 *
 * */
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
