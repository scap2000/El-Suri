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
 * QuerySecurityDefiner.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;

public class QuerySecurityDefiner extends BasicDialog {
//public class ExportFunctionsTablesPublic extends JDialog {

    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnClear = new AddButton();
    private BasicButton dbConnect = new BasicButton();
    private BasicButton btnGetQuery = new BasicButton();
    
    private BasicScrollPane spDescripcion = new BasicScrollPane();
    
    private BasicPanel jPanel2 = new BasicPanel("Exportar Privilegios");

    private BasicLabel lblDescripcion = new BasicLabel();

    private JTextArea taDescripcion = new JTextArea();
    
    private Vector dbVector = new Vector();
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();
    private String _dbName = "";
    private String selectedDB = "";
    int numeroFuncion = -1;
    int opcion = -1;

    public QuerySecurityDefiner(){
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public QuerySecurityDefiner(int _opcion){
	opcion = _opcion;
	if(opcion==0){
	    this.setTitle("Exportar Tablas Públicas");
	}
	else{
	    this.setTitle("Exportar Funciones Públicas");
	}
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(665, 501));
	//this.setTitle("Exportar Funciones Públicas");
	this.setBackground(Color.black);
	btnAccept.setBounds(new Rectangle(570, 440, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	spDescripcion.getViewport().add(taDescripcion, null);
	taDescripcion.setEditable(false);
	jPanel2.add(dbConnect, null);
	jPanel2.add(spDescripcion, null);
	jPanel2.add(lblDescripcion, null);
	this.getContentPane().add(btnGetQuery, null);
	this.getContentPane().add(btnClose, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(btnClear, null);
	jPanel2.setBounds(new Rectangle(0, 0, 655, 425));
	jPanel2.setLayout(null);
	dbConnect.setText("Conectar");
	dbConnect.setBounds(new Rectangle(245, 40, 80, 20));
	dbConnect.setToolTipText("null");
	dbConnect.setSize(new Dimension(80, 20));
	btnGetQuery.setText("GetQuery");
	btnGetQuery.setBounds(new Rectangle(275, 440, 140, 30));
	btnGetQuery.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGetQuery_actionPerformed(e);
		}
	    }
	);
	btnClear.setBounds(new Rectangle(510, 440, 65, 25));
	btnClear.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClear_actionPerformed(e);
		}
	    }
	);
	btnClose.setBounds(new Rectangle(615, 440, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	lblDescripcion.setText("Consulta");
	lblDescripcion.setBounds(new Rectangle(10, 25, 145, 15));

	spDescripcion.setBounds(new Rectangle(10, 45, 635, 370));
	loadStuff();
    }

    private void loadStuff() {
	/*cbDatabases.loadJCombo("SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn");
	dbVector = cbDatabases.getItemsVector();
	cbDatabases.setSelectedItem(LibSQL.getDataBase().split("/")[3]);*/
	loadQuery();
    }
       
    private void btnAccept_actionPerformed(ActionEvent e) {
	if(!taDescripcion.getText().equals("")){
	    if(LibSQL.exActualizar('a',taDescripcion.getText())){
		Advisor.messageBox("Consulta ejecutada exitosamente", "Aviso");
	    }
	}
	else{
	    Advisor.messageBox("Consulta vacía", "Aviso");
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void limpiarCampos() {
	taDescripcion.setText("");
    }
    
   private void btnClear_actionPerformed(ActionEvent e) {
	taDescripcion.setText("");
    }

    private void btnGetQuery_actionPerformed(ActionEvent e) {
	loadQuery();
    }

    private void loadQuery() {
	StringBuilder query = new StringBuilder();
	//ResultSet _securityDefiner = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname) AS foo;");
	ResultSet _securityDefiner = LibSQL.exQuery("SELECT 'ALTER FUNCTION '|| n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ') SECURITY DEFINER' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname;");
	
	try {
	    while (_securityDefiner.next()) {
		//query.append("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
		query.append(_securityDefiner.getString("declaration")+";\n");
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	taDescripcion.setText(query.toString());
    }
}
