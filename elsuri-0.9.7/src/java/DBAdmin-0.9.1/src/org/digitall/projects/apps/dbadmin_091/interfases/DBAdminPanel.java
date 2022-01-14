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
 * DBAdminPanel.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.interfases.DBRole;
import org.digitall.projects.apps.dbadmin_091.interfases.FunctionManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupFunctionsManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupTableManager;
import org.digitall.projects.apps.dbadmin_091.interfases.OptionsMgmt;
import org.digitall.projects.apps.dbadmin_091.interfases.PublicAccessManager;
import org.digitall.projects.apps.dbadmin_091.interfases.SystemFunctionUsersManager;
import org.digitall.projects.apps.dbadmin_091.interfases.SystemFunctionsGroupsManager;
import org.digitall.projects.apps.dbadmin_091.interfases.TableManager;
import org.digitall.projects.apps.dbadmin_091.interfases.UserManager;
import org.digitall.projects.apps.dbadmin_091.interfases.UsersSystemFunctionsManager;


public class DBAdminPanel extends BasicPrimitivePanel {

    private BasicTabbedPane tpMain = new BasicTabbedPane();
    private JCombo cbDatabases = new JCombo();
    private BasicButton dbConnect = new BasicButton();
    private Vector dbVector = new Vector();
    private Vector schemeVector = new Vector();
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private Vector userVector = new Vector();
    private Vector tableVector = new Vector();
    private Vector functionVector = new Vector();
    private UserManager userManager = new UserManager(this);
    private GroupManager groupManager = new GroupManager(this);
    
    private TableManager tableManager = new TableManager(this);
    private GroupTableManager groupTableManager = new GroupTableManager(this);
    private FunctionManager functionManager = new FunctionManager(this);
    private GroupFunctionsManager groupFunctionsManager = new GroupFunctionsManager(this);
    //private GroupFunctionManager groupFunctionManager = new GroupFunctionManager(this);
    private SystemFunctionsGroupsManager functionGroupManager = new SystemFunctionsGroupsManager(this);
    private SystemFunctionUsersManager functionUsersManager = new SystemFunctionUsersManager(this);
    private UsersSystemFunctionsManager usersFunctionsManager = new UsersSystemFunctionsManager(this);
    private PublicAccessManager publicAccessManager = new PublicAccessManager(this);
    //private StoredProceduresManager storedProcedureManager = new StoredProceduresManager(this);
    private OptionsMgmt optionsPanel = new OptionsMgmt();
    
    /*private StoredBySystemFunctionsManager storedByFunction = new StoredBySystemFunctionsManager(this);
    private SystemFunctionsByStoredManager functionsByStore = new SystemFunctionsByStoredManager(this);*/
    private BasicLabel lblBD = new BasicLabel();
    private ResultSet schemes = null;

    private JProgressBar pbCarga = new JProgressBar();
    private BasicLabel lblBarraEstado = new BasicLabel();
    private BasicPanel pContenedorBarraEstado = new BasicPanel();

    private BasicPanel pEncabezado = new BasicPanel();

    public DBAdminPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	setSize(new Dimension(675, 611));
	setTitle("DataBase Administrator");
	this.setBackground(Color.black);
	tpMain.setBounds(new Rectangle(0, 50, 675, 510));
	tpMain.setSize(new Dimension(675, 510));
	tpMain.addTab("Usuarios-Grupos", userManager);
	tpMain.addTab("Grupos-Usuarios", groupManager);
	//tpMain.addTab("Grupos-Funciones",groupFunctionManager);
	tpMain.addTab("Tablas-Grupos", tableManager);
        tpMain.addTab("Grupos-Tablas", groupTableManager);
	tpMain.addTab("Funciones-Grupos", functionManager);
	tpMain.addTab("Grupos-Funciones",groupFunctionsManager);
	tpMain.addTab("Public Manager",publicAccessManager);
	tpMain.addTab("Funciones del Sistema",functionGroupManager);

	//tpMain.addTab("Stored Manager",storedProcedureManager);
	
	//tpMain.addTab("Stored by Function",storedByFunction);
	//tpMain.addTab("Functions by Stored",functionsByStore);
	tpMain.addTab("Funciones Usuarios",functionUsersManager);
	tpMain.addTab("Usuarios Funciones",usersFunctionsManager);
	tpMain.addTab("Opciones",optionsPanel);
	pContenedorBarraEstado.add(lblBarraEstado, null);
        pContenedorBarraEstado.add(pbCarga, null);
        pEncabezado.add(cbDatabases, null);
	pEncabezado.add(dbConnect, null);
	pEncabezado.add(lblBD, null);
	this.add(tpMain, null);
	this.add(pEncabezado, null);
	this.add(pContenedorBarraEstado, null);
	//ComponentsManager.centerWindow(this);
	cbDatabases.setBounds(new Rectangle(105, 10, 200, 20));
	dbConnect.setText("Conectar");
	dbConnect.setBounds(new Rectangle(305, 10, 95, 20));
	//dbConnect.setForeground(Color.black);
	dbConnect.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e){
				     dbConnect_actionPerformed(e);
				 }
			     }
	);
	lblBD.setText("Base de Datos");
	lblBD.setBounds(new Rectangle(10, 15, 100, 15));
	//lblBD.setForeground(Color.black);
	lblBD.setFont(new Font("Lucida Sans", 1, 12));
	pbCarga.setBounds(new Rectangle(495, 3, 170, 20));
	pbCarga.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	lblBarraEstado.setBounds(new Rectangle(5, 0, 410, 20));
	pContenedorBarraEstado.setBounds(new Rectangle(0, 560, 675, 25));
	pContenedorBarraEstado.setLayout(null);
	pEncabezado.setBounds(new Rectangle(0, 0, 675, 50));
	pEncabezado.setLayout(null);
	pEncabezado.setBorder(BorderPanel.getBorderPanel(""));
	pContenedorBarraEstado.setBorder(BorderPanel.getBorderPanel(""));
	pEncabezado.setSize(new Dimension(675, 52));
	loadStuff();
    }

    private void dbConnect_actionPerformed(ActionEvent e) {
	LibSQL.setDataBase(cbDatabases.getSelectedItem().toString());
	LibSQL.closeConnection();
	if (LibSQL.isConnected()) {
	    loadStuff();
	} else {
	    Advisor.messageBox("Error al intentar conectar a la base de datos " + cbDatabases.getSelectedItem().toString(), "Error");
	}
    }

    /**2009-12-30(moraless)*/
    private void loadStuff() {
	Thread _thread = new Thread(new Runnable() {
	       public void run() {
		   dbConnect.setEnabled(false);
	           setEnabledTabs(false);
	           pbCarga.setVisible(true);
	           pbCarga.setStringPainted(true);
	           // Obteniendo listado de bases de datos
		   lblBarraEstado.setText("Obteniendo listado de Bases de Datos");
	           cbDatabases.loadJCombo("SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn ORDER BY datname");
	           cbDatabases.setSelectedItem(LibSQL.getDataBase().split("//")[1].split("/")[1]);
	           dbVector = cbDatabases.getItemsVector();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Obteniendo listado de esquemas
		   lblBarraEstado.setText("Obteniendo listado de Esquemas");
	           schemes = LibSQL.exQuery("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Inicializando la base de datos
		   lblBarraEstado.setText("Inicializando la Base de Datos");
	           ejecutarScript();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando usuarios
		   lblBarraEstado.setText("Cargando Usuarios");
	           userManager.boot();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando grupos
		   lblBarraEstado.setText("Cargando Grupos");
	           groupManager.boot(getSchemes());
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando tablas
		   lblBarraEstado.setText("Cargando Tablas");
	           tableManager.boot(getSchemes());
	           pbCarga.setValue(pbCarga.getValue()+8);
                   // Cargando tablas por grupo
                   lblBarraEstado.setText("Cargando Tablas por Grupo");
                   groupTableManager.boot(getSchemes());
                   pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando procedimientos
		   lblBarraEstado.setText("Cargando Grupos por Procedimiento");
	           functionManager.boot(getSchemes());
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando procedimientos por grupo
		   lblBarraEstado.setText("Cargando Procedimientos por Grupo");
	           groupFunctionsManager.boot(getSchemes());
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando tablas y funciones públicas
		   lblBarraEstado.setText("Cargando Tablas y Funciones Públicas");
	           publicAccessManager.boot(getSchemes());
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando funcionalidades por grupo
		   lblBarraEstado.setText("Cargando Funcionalidades por Grupo");
	           functionGroupManager.boot();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando usuarios por funcionalidad
		   lblBarraEstado.setText("Cargando Usuarios por Funcionalidad");
	           functionUsersManager.boot();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           // Cargando usuarios y sus funcionalidades
		   lblBarraEstado.setText("Cargando Funcionalidades por Usuario");
	           usersFunctionsManager.boot();
	           pbCarga.setValue(pbCarga.getValue()+8);
	           pbCarga.setValue(0);
		   pbCarga.setVisible(false);
	           setEnabledTabs(true);
	           dbConnect.setEnabled(true);
	           lblBarraEstado.setText("Listo...");
	       }
	});
	_thread.start();
    }
    
    private void setEnabledTabs(boolean _enabled){
	userManager.setEnabledTab(_enabled);
	groupManager.setEnabledTab(_enabled);
	tableManager.setEnabledTab(_enabled);
	functionManager.setEnabledTab(_enabled);
	groupFunctionsManager.setEnabledTab(_enabled);
	publicAccessManager.setEnabledTab(_enabled);
	functionGroupManager.setEnabledTab(_enabled);
    }

    /**2009-12-30(moraless)*/
    public void setGroupVector(Vector<DBRole> _groupVector) {
	groupVector = _groupVector;
	userManager.setGroupVector(_groupVector);
	//tableManager.setGroupVector(groupVector);
	functionManager.setGroupVector(_groupVector);
	//groupFunctionManager.setGroupVector(_groupVector);
    }

    public Vector getGroupVector() {
	return groupVector;
    }

    public void setUserVector(Vector _userVector) {
	userVector = _userVector;
	groupManager.setUserVector(_userVector);
	groupManager.setDBVector(dbVector);
    }

    public Vector getUserVector() {
	return userVector;
    }

    private void btnEjecutarScript_actionPerformed(ActionEvent e) {
	ejecutarScript();
    }
    
    /**2009-12-07(moraless)*/
    private void ejecutarScript(){
	String script = getScript();
	if(script != ""){
	    if (Advisor.question("DBAdmin", "¿Desea que el Sistema cree las estructuras necesarias para su correcta ejecución?")) {
	        if(LibSQL.exActualizar('a',script)) {
	            Advisor.messageBox("Se generaron con éxito las estructuras necesarias para la ejecución de DBAdmin", "Mensaje");
	        }else{
	            Advisor.messageBox("Error al ejecutar el script", "Error");
	        }    
	    }
	}
    }
    
    /**2009-12-07(moraless)*/
    private String getScript(){
	String script = "";
	if(!DBControl.existTable("admin","modulos")){
	    script += "CREATE TABLE admin.modulos (\n" + 
	    "    idmodulo integer NOT NULL,\n" + 
	    "    nombre character varying NOT NULL,\n" + 
	    "    estado character(1) NOT NULL\n" + 
	    ");\n" + 
	    "\n" + 
	    "\n" + 
	    "ALTER TABLE admin.modulos OWNER TO postgres;\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: modulos_pkey; Type: CONSTRAINT; Schema: admin; Owner: postgres; Tablespace: \n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.modulos\n" + 
	    "    ADD CONSTRAINT modulos_pkey PRIMARY KEY (idmodulo);\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: modulos; Type: ACL; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "REVOKE ALL ON TABLE admin.modulos FROM PUBLIC;\n" + 
	    "REVOKE ALL ON TABLE admin.modulos FROM postgres;\n" + 
	    "GRANT ALL ON TABLE admin.modulos TO postgres;\n";
	    /*"\n" + 
	    "\n";*/
	    //"\n" + 
	    //"INSERT INTO admin.modulos (idmodulo, nombre, estado) VALUES ('0', 'Todos', ' ');\n";
	}
	if(!DBControl.existTable("admin","funciones")){
	    script += "CREATE TABLE admin.funciones (\n" + 
	    "    idfuncion integer NOT NULL,\n" + 
	    "    idmodulo integer NOT NULL,\n" + 
	    "    numerofuncion integer NOT NULL,\n" + 
	    "    nombre character varying NOT NULL,\n" + 
	    "    descripcion character varying,\n" + 
	    "    grupo character varying,\n" + 
	    "    estado character(1)\n" + 
	    ");\n" + 
	    "\n" + 
	    "\n" + 
	    "ALTER TABLE admin.funciones OWNER TO postgres;\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: funciones_pkey; Type: CONSTRAINT; Schema: admin; Owner: postgres; Tablespace: \n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.funciones\n" + 
	    "    ADD CONSTRAINT funciones_pkey PRIMARY KEY (idfuncion);\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: funciones_idmodulo_fkey; Type: FK CONSTRAINT; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.funciones\n" + 
	    "    ADD CONSTRAINT funciones_idmodulo_fkey FOREIGN KEY (idmodulo) REFERENCES admin.modulos(idmodulo) ON UPDATE CASCADE ON DELETE RESTRICT;\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: funciones; Type: ACL; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "REVOKE ALL ON TABLE admin.funciones FROM PUBLIC;\n" + 
	    "REVOKE ALL ON TABLE admin.funciones FROM postgres;\n" + 
	    "GRANT ALL ON TABLE admin.funciones TO postgres;\n";
	}
	if(!DBControl.existTable("admin","storedbyfunction")){
	    script += "CREATE TABLE admin.storedbyfunction (\n" + 
	    "    idstoredbyfunction integer NOT NULL,\n" + 
	    "    idfuncion integer NOT NULL,\n" + 
	    "    nombrestored character varying NOT NULL,\n" + 
	    "    estado character(1) NOT NULL\n" + 
	    ");\n" + 
	    "\n" + 
	    "\n" + 
	    "ALTER TABLE admin.storedbyfunction OWNER TO postgres;\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: storedbyfuntion_pkey; Type: CONSTRAINT; Schema: admin; Owner: postgres; Tablespace: \n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.storedbyfunction\n" + 
	    "    ADD CONSTRAINT storedbyfuntion_pkey PRIMARY KEY (idstoredbyfunction);\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: storedbyfuntion_idfuncion_fkey; Type: FK CONSTRAINT; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.storedbyfunction\n" + 
	    "    ADD CONSTRAINT storedbyfuntion_idfuncion_fkey FOREIGN KEY (idfuncion) REFERENCES admin.funciones(idfuncion) ON UPDATE CASCADE ON DELETE RESTRICT;\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: storedbyfunction; Type: ACL; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "REVOKE ALL ON TABLE admin.storedbyfunction FROM PUBLIC;\n" + 
	    "REVOKE ALL ON TABLE admin.storedbyfunction FROM postgres;\n" + 
	    "GRANT ALL ON TABLE admin.storedbyfunction TO postgres;\n";
	}
	/*if(!DBControl.existTable("admin","publicaccess")){
	    script += "CREATE TABLE admin.publicaccess (\n" + 
	    "    idpublic integer NOT NULL,\n" + 
	    "    nombre character varying NOT NULL,\n" + 
	    "    tipo character varying NOT NULL,\n" + 
	    "    estado character(1) NOT NULL\n" + 
	    ");\n" + 
	    "\n" + 
	    "\n" + 
	    "ALTER TABLE admin.publicaccess OWNER TO postgres;\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: publicaccess_pkey; Type: CONSTRAINT; Schema: admin; Owner: postgres; Tablespace: \n" + 
	    "--\n" + 
	    "\n" + 
	    "ALTER TABLE ONLY admin.publicaccess\n" + 
	    "    ADD CONSTRAINT publicaccess_pkey PRIMARY KEY (idpublic);\n" + 
	    "\n" + 
	    "\n" + 
	    "--\n" + 
	    "-- Name: publicaccess; Type: ACL; Schema: admin; Owner: postgres\n" + 
	    "--\n" + 
	    "\n" + 
	    "REVOKE ALL ON TABLE admin.publicaccess FROM PUBLIC;\n" + 
	    "REVOKE ALL ON TABLE admin.publicaccess FROM postgres;\n" + 
	    "GRANT ALL ON TABLE admin.publicaccess TO postgres;\n";
	}*/
	
	if(!DBControl.existFunction("org","getpersonnamebyusername","character varying")){
	    script += "CREATE OR REPLACE FUNCTION org.getpersonnamebyusername(_username character varying) RETURNS character varying AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	     _idpersona integer;\n" + 
	    "	     _user character varying;\n" + 
	    "     BEGIN\n" + 
	    "     /*\n" + 
	    "	     #version = 1.10\n" + 
	    "	     #groups = 0\n" + 
	    "	     #Description = Funcion pública que devuelve el nombre completo o el nombre de usuario de la persona con el usuario por parámetro\n" + 
	    "     */\n" + 
	    "	     _user := _username;\n" + 
	    "\n" + 
	    "	     SELECT INTO _idpersona idperson FROM org.users WHERE username = _username;\n" + 
	    "\n" + 
	    "	     IF (_idpersona is not null ) THEN\n" + 
	    "		     IF (_idpersona != -1 ) THEN\n" + 
	    "			     SELECT INTO _user (lastname ||', '|| name) AS person FROM org.persons WHERE idperson = _idpersona;\n" + 
	    "		     END IF;\n" + 
	    "	     END IF;\n" + 
	    "	     \n" + 
	    "	     RETURN _user;\n" + 
	    "\n" + 
	    "     END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	if(!DBControl.existFunction("admin","getallfunctionsbyfilter","character varying, integer, boolean, integer, integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getallfunctionsbyfilter(_nombrefuncion character varying, _idmodulo integer, _congrupo boolean, _limit integer, _offset integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "        filter character varying = '';\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion publica que devuelve listado de funciones de acuerdo a los filtros\n" + 
	    "*/\n" + 
	    "        IF (_nombrefuncion != '') THEN\n" + 
	    "                  filter = filter || ' AND UPPER(funciones.nombre) LIKE UPPER(''%'|| _nombrefuncion ||'%'')  ';\n" + 
	    "        END IF;\n" + 
	    "\n" + 
	    "        IF (_idmodulo != -1) THEN\n" + 
	    "                  filter = filter || ' AND funciones.idmodulo = '||_idmodulo;\n" + 
	    "        END IF;\n" + 
	    "\n" + 
	    "        IF (NOT _congrupo) THEN\n" + 
	    "                  filter = filter || ' AND funciones.grupo = '''' ';\n" + 
	    "        ELSE\n" + 
	    "                  filter = filter || ' AND funciones.grupo <> '''' ';\n" + 
	    "        END IF;\n" + 
	    "\n" + 
	    "	query := 'SELECT \n" + 
	    "                            funciones.idfuncion AS id,\n" + 
	    "                            funciones.nombre AS funcion,\n" + 
	    "                            funciones.descripcion AS descripcion,\n" + 
	    "                            funciones.grupo, \n" + 
	    "                            modulos.nombre AS modulo \n" + 
	    "                  FROM \n" + 
	    "                            admin.funciones \n" + 
	    "                            INNER JOIN admin.modulos ON modulos.idmodulo = funciones.idmodulo \n" + 
	    "                  WHERE \n" + 
	    "                            funciones.estado <> ''*''\n" + 
	    "                            '|| filter ||'\n" + 
	    "                  ORDER BY  \n" + 
	    "                            funciones.nombre';\n" + 
	    "\n" + 
	    "	          IF (_limit != 0) THEN\n" + 
	    "		            query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;\n" + 
	    "	          END IF;\n" + 
	    "\n" + 
	    "--        RAISE EXCEPTION 'consultaa %',query;\n" + 
	    "	OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","addfuncion","integer, integer, integer, character varying, character varying, character varying, character varying")){
	    script += "CREATE OR REPLACE FUNCTION admin.addfuncion(_idfuncion integer, _idmodulo integer, _numerofuncion integer, _nombre character varying, _descripcion character varying, _grupo character varying, _estado character varying) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	_id integer;\n" + 
	    "	query character varying;\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#description = funcionalidad que inserta o modifica los datos de una funcion de sistema\n" + 
	    "*/\n" + 
	    "	IF (_idfuncion = -1) THEN \n" + 
	    "	\n" + 
	    "      --SELECT INTO _id (max(idfuncion) + 1) FROM admin.funciones;\n" + 
	    "        SELECT INTO _id public.ISNULL(max(idfuncion),0)::integer + 1 FROM admin.funciones;\n" + 
	    "\n" + 
	    "		query:='INSERT INTO admin.funciones\n" + 
	    "				(\n" + 
	    "				idfuncion,\n" + 
	    "				idmodulo,\n" + 
	    "				numerofuncion,\n" + 
	    "				nombre,\n" + 
	    "				descripcion,\n" + 
	    "				grupo,\n" + 
	    "				estado\n" + 
	    "				)\n" + 
	    "			VALUES (	\n" + 
	    "                               '||_id||',\n" + 
	    "				'|| _idmodulo ||',\n" + 
	    "				'|| _numerofuncion ||',\n" + 
	    "				'''|| _nombre ||''',\n" + 
	    "				'''|| _descripcion ||''',\n" + 
	    "				'''|| _grupo ||''',\n" + 
	    "				'''|| _estado ||'''\n" + 
	    "				)';\n" + 
	    "	ELSE \n" + 
	    "		query = ' UPDATE 	admin.funciones\n" + 
	    "			SET	idfuncion = '||_idfuncion||',\n" + 
	    "                                idmodulo = '||_idmodulo||',\n" + 
	    "                                numerofuncion = '||_numerofuncion||',\n" + 
	    "                                nombre = '''||_nombre||''',\n" + 
	    "                                descripcion = '''||_descripcion||''',\n" + 
	    "                                grupo = '''||_grupo||''',\n" + 
	    "                                estado = '''||_estado||'''\n" + 
	    "			WHERE	idfuncion = '|| _idfuncion;\n" + 
	    "	END IF;	\n" + 
	    "\n" + 
	    "	EXECUTE  query;\n" + 
	    "	RETURN _idfuncion;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	if(!DBControl.existFunction("admin","getfuncion","integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getfuncion(_idfuncion integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion publica que devuelve datos de una funcion del sistema \n" + 
	    "*/\n" + 
	    "	query := 'SELECT \n" + 
	    "                            * \n" + 
	    "                  FROM \n" + 
	    "                             admin.funciones \n" + 
	    "                  WHERE \n" + 
	    "                             funciones.idfuncion = '||_idFuncion||' \n" + 
	    "                             AND estado <> ''*''';\n" + 
	    "\n" + 
	    "	OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	if(!DBControl.existFunction("admin","getmaxnumberfunction","integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getmaxnumberfunction(_idmodulo integer) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	maximo integer;\n" + 
	    "	query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion publica que devuelve maximo de un campo de tabla especificada\n" + 
	    "*/\n" + 
	    "	SELECT INTO maximo\n" + 
	    "                          CASE WHEN (MAX(funciones.numerofuncion) IS NULL) \n" + 
	    "                                THEN \n" + 
	    "                                       1\n" + 
	    "                                ELSE \n" + 
	    "                                       (MAX(funciones.numerofuncion)+1)\n" + 
	    "                                END\n" + 
	    "        FROM\n" + 
	    "                          admin.funciones \n" + 
	    "        WHERE \n" + 
	    "                          idmodulo = _idmodulo;\n" + 
	    "	RETURN maximo;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","addmodulo","integer, character varying, character varying")){
	    script += "CREATE OR REPLACE FUNCTION admin.addmodulo(_idmodulo integer, _nombre character varying, _estado character varying) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	_id integer;\n" + 
	    "	query character varying;\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#description = funcionalidad que inserta o modifica los datos de un modulo del sistema\n" + 
	    "*/\n" + 
	    "	IF (_idmodulo = -1) THEN \n" + 
	    "	\n" + 
	    //"        SELECT INTO _id (max(idmodulo) + 1) FROM admin.modulos;\n" + 
            "        SELECT INTO _id public.ISNULL(max(idmodulo),0)::integer + 1 FROM admin.modulos;\n"+
	    "\n" + 
	    "		query:='INSERT INTO admin.modulos\n" + 
	    "				(\n" + 
	    "				idmodulo,\n" + 
	    "				nombre,\n" + 
	    "				estado\n" + 
	    "				)\n" + 
	    "			VALUES (	\n" + 
	    "                               '||_id||',\n" + 
	    "				'''|| _nombre ||''',\n" + 
	    "				'''|| _estado ||'''\n" + 
	    "				)';\n" + 
	    "	ELSE \n" + 
	    "		query = ' UPDATE 	admin.modulos\n" + 
	    "			SET	nombre = '''||_nombre||''',\n" + 
	    "                                estado = '''||_estado||'''\n" + 
	    "			WHERE	idmodulo = '|| _idmodulo;\n" + 
	    "	END IF;	\n" + 
	    "\n" + 
	    "	EXECUTE  query;\n" + 
	    "	RETURN _idmodulo;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","getmodulo","integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getmodulo(_idmodulo integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion publica que devuelve datos de un modulo determinado\n" + 
	    "*/\n" + 
	    "	query := 'SELECT \n" + 
	    "                            * \n" + 
	    "                  FROM \n" + 
	    "                            admin.modulos \n" + 
	    "                  WHERE \n" + 
	    "                            modulos.idmodulo = '||_idmodulo||' \n" + 
	    "                            AND modulos.estado <>''*''';\n" + 
	    "\n" + 
	    "	OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","getallmodulos","boolean")){
	    script += "CREATE OR REPLACE FUNCTION admin.getallmodulos(_all boolean) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "   returnCursor REFCURSOR;\n" + 
	    "   query TEXT;\n" + 
	    "        filter character varying = '';\n" + 
	    " \n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "   #version = 1.0\n" + 
	    "   #groups = 0\n" + 
	    "   #Description = Funcion publica que devuelve listado de funciones que pertenecen a un modulo determinado\n" + 
	    "*/\n" + 
	    "        IF (NOT _all) THEN\n" + 
	    "                  filter = filter || ' AND modulos.idmodulo > 0 ';\n" + 
	    "        END IF; \n" + 
	    "\n" + 
	    "   query := 'SELECT \n" + 
	    "                            modulos.idmodulo, \n" + 
	    "                            modulos.nombre ,\n" + 
	    "                            0 \n" + 
	    "                  FROM \n" + 
	    "                            admin.modulos \n" + 
	    "                  WHERE \n" + 
	    "                            modulos.estado<>''*'' \n" + 
	    "                            '|| filter ||'\n" + 
	    "\n" + 
	    "                  ORDER BY nombre';\n" + 
	    "\n" + 
	    "   OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "   RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","getallmodulos","integer, integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getallmodulos(_limit integer, _offset integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "   returnCursor REFCURSOR;\n" + 
	    "   query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "   #version = 1.0\n" + 
	    "   #groups = 0\n" + 
	    "   #Description = Funcion publica que devuelve listado de modulos del sistema para una tabla\n" + 
	    "*/\n" + 
	    "   query := 'SELECT \n" + 
	    "                            modulos.idmodulo, \n" + 
	    "                            modulos.nombre ,\n" + 
	    "                            0 \n" + 
	    "                  FROM \n" + 
	    "                            admin.modulos \n" + 
	    "                  WHERE \n" + 
	    "                            modulos.estado<>''*'' \n" + 
	    "                            AND modulos.idmodulo > 0  \n" + 
	    "                  ORDER BY nombre';\n" + 
	    "\n" + 
	    "        If (_limit != 0) THEN\n" + 
	    "           query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;\n" + 
	    "   END IF;\n" + 
	    "\n" + 
	    "   OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "   RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","getfunctionsassigned","integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getfunctionsassigned(_idmodulo integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "        filter character varying = '';\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion que devuelve listado de funciones que pertenecen a un modulo determinado y tienen un grupo asignado \n" + 
	    "*/\n" + 
	    "        IF (_idmodulo != -1) THEN\n" + 
	    "                  filter = filter || ' AND funciones.idmodulo = '||_idmodulo;\n" + 
	    "        END IF;\n" + 
	    "\n" + 
	    "	query := 'SELECT     \n" + 
	    "                            funciones.idfuncion, \n" + 
	    "                            funciones.nombre, \n" + 
	    "                            funciones.idmodulo \n" + 
	    "                  FROM      \n" + 
	    "                            admin.funciones\n" + 
	    "		  WHERE     \n" + 
	    "                            funciones.grupo <> '''' \n" + 
	    "                            AND funciones.estado <> ''*'' \n" + 
	    "                            '|| filter ||'\n" + 
	    "                  ORDER BY funciones.nombre';\n" + 
	    "\n" + 
	    "\n" + 
	    "	OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","addstoredbyfunction","integer, integer, character varying, character")){
	    script += "CREATE OR REPLACE FUNCTION admin.addstoredbyfunction(_idstoredbyfunction integer, _idfuncion integer, _nombrestored character varying, _estado character) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	_id integer;\n" + 
	    "	query character varying;\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#description = funcionalidad que inserta o modifica los datos de stored por funcion del sistema\n" + 
	    "*/\n" + 
	    "	IF (_idstoredbyfunction = -1) THEN \n" + 
	    "	\n" + 
	    "        SELECT INTO _id public.ISNULL(max(idstoredbyfunction),0)::integer + 1 FROM admin.storedbyfunction;\n" + 
	    "\n" + 
	    "		query:='INSERT INTO admin.storedbyfunction\n" + 
	    "				(\n" + 
	    "				idstoredbyfunction,\n" + 
	    "                                idfuncion,\n" + 
	    "				nombrestored,\n" + 
	    "				estado\n" + 
	    "				)\n" + 
	    "			VALUES (	\n" + 
	    "                               '||_id||',\n" + 
	    "                               '||_idfuncion||',\n" + 
	    "				'''|| _nombrestored ||''',\n" + 
	    "				'''|| _estado ||'''\n" + 
	    "				)';\n" + 
	    "	ELSE \n" + 
	    "		query = ' UPDATE 	admin.storedbyfunction\n" + 
	    "			SET	idfuncion = '||_idfuncion||',\n" + 
	    "                                nombrestored = '''||_nombrestored||''',\n" + 
	    "                                estado = '''||_estado||'''\n" + 
	    "			WHERE	idstoredbyfunction = '|| _idstoredbyfunction;\n" + 
	    "	END IF;	\n" + 
	    "\n" + 
	    "	EXECUTE  query;\n" + 
	    "	RETURN _idstoredbyfunction;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("admin","getstoredbyfunction","integer")){
	    script += "CREATE OR REPLACE FUNCTION admin.getstoredbyfunction(_idstoredbyfuncion integer) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "/*\n" + 
	    "	#version = 1.0\n" + 
	    "	#groups = 0\n" + 
	    "	#Description = Funcion publica que devuelve nombres de los stored que pertenecen a una funcion del sistema\n" + 
	    "*/\n" + 
	    "	query := 'SELECT     \n" + 
	    "                            storedbyfunction.idstoredbyfunction, \n" + 
	    "                            storedbyfunction.idfuncion, \n" + 
	    "                            storedbyfunction.nombrestored,\n" + 
	    "                            storedbyfunction.estado\n" + 
	    "                  FROM      \n" + 
	    "                            admin.storedbyfunction\n" + 
	    "		  WHERE     \n" + 
	    "                            storedbyfunction.idstoredbyfunction = '||_idstoredbyfuncion||'\n" + 
	    "                            AND storedbyfunction.estado <> ''*''\n" + 
	    "                  ORDER BY storedbyfunction.nombrestored';\n" + 
	    "\n" + 
	    "	OPEN returnCursor FOR EXECUTE query;\n" + 
	    "\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("public","isnull","integer, integer")){
	    script += "CREATE OR REPLACE FUNCTION public.isnull (IN arg01 integer,IN arg02 integer) RETURNS integer AS 'SELECT (CASE (SELECT $1 is null) WHEN true THEN $2 ELSE $1 END) AS RESULT' LANGUAGE sql;\n";
	}
	
	/*
	if(!DBControl.existFunction("org","getpersonbyusername","character varying")){
	    script += "CREATE OR REPLACE FUNCTION org.getpersonbyusername(_username character varying) RETURNS refcursor AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	returnCursor REFCURSOR;\n" + 
	    "	query TEXT;\n" + 
	    "\n" + 
	    "BEGIN\n" + 
	    "       \n" + 
	    "	query :='SELECT \n" + 
	    "			public.ISNULL(users.idperson, -1)::integer AS idperson,\n" + 
	    "			public.ISNULL(users.iduser, -1)::integer AS iduser,\n" + 
	    "			--public.ISNULL(users.username, '''')::character varying AS username,\n" + 
	    "			''' || _username || '''::character varying AS username,\n" + 
	    "			persons.ididentificationtype, \n" + 
	    "			persons.identificationnumber, \n" + 
	    "			persons.name, \n" + 
	    "			persons.lastname,\n" + 
	    "			persons.estado,\n" + 
	    "			idcostscentre,\n" + 
	    "			(SELECT rolcanlogin FROM pg_roles WHERE rolname = '''|| _username||''') AS canlogin
	    "		 FROM  \n" + 
	    "			org.persons\n" + 
	    "			LEFT JOIN org.users ON users.idperson = persons.idperson\n" + 
	    "		 WHERE \n" + 
	    "			users.username = '''|| _username ||''' OR persons.idperson = -1 ORDER BY idperson DESC LIMIT 1'; \n" + 
	    "\n" + 
	    "        OPEN returnCursor FOR EXECUTE query;\n" + 
	    "	RETURN returnCursor;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("org","adduser","integer, character varying")){
	    script += "CREATE OR REPLACE FUNCTION org.adduser(_idperson integer, _username character varying) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	id int;\n" + 
	    "BEGIN\n" + 
	    "	SELECT INTO id COUNT(*) FROM org.users WHERE username = _username;\n" + 
	    "\n" + 
	    "	IF (id = 0) THEN\n" + 
	    "		BEGIN\n" + 
	    "			SELECT INTO id public.ISNULL(MAX(iduser),0)::integer + 1 FROM org.users;\n" + 
	    "\n" + 
	    "			INSERT INTO org.users (iduser, idperson, username, estado) VALUES (id, _idperson, _username, '');\n" + 
	    "		\n" + 
	    "			RETURN id;\n" + 
	    "		END;\n" + 
	    "	ELSE\n" + 
	    "		RETURN -1;\n" + 
	    "	END IF;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("org","setuser","integer, integer, integer, character varying")){
	    script += "CREATE OR REPLACE FUNCTION org.setuser(_iduser integer, _idperson integer, _idcostscentre integer, _username character varying) RETURNS integer AS $BODY$\n" + 
	    "DECLARE\n" + 
	    "	_qty int;\n" + 
	    "BEGIN\n" + 
	    "	SELECT INTO _qty COUNT(*) FROM org.users WHERE iduser = _iduser;\n" + 
	    "\n" + 
	    "	IF (_qty > 0) THEN\n" + 
	    "		BEGIN\n" + 
	    "			UPDATE org.users SET username = _username, idperson = _idperson, idcostscentre = _idcostscentre WHERE iduser = _iduser;		\n" + 
	    "			RETURN _qty;\n" + 
	    "		END;\n" + 
	    "	ELSE\n" + 
	    "		SELECT INTO _qty COUNT(*) FROM org.users WHERE username = _username;\n" + 
	    "\n" + 
	    "		IF (_qty > 0) THEN\n" + 
	    "			BEGIN\n" + 
	    "				UPDATE org.users SET iduser = _iduser, idperson = _idperson, idcostscentre = _idcostscentre WHERE username = _username;\n" + 
	    "				RETURN _qty;\n" + 
	    "			END;\n" + 
	    "		ELSE\n" + 
	    "			SELECT INTO _qty COUNT(*) FROM org.users WHERE idperson = _idperson;\n" + 
	    "			IF (_qty > 0) THEN\n" + 
	    "				BEGIN\n" + 
	    "					UPDATE org.users SET username = _username, iduser = _iduser, idcostscentre = _idcostscentre WHERE idperson = _idperson;\n" + 
	    "					RETURN _qty;\n" + 
	    "				END;\n" + 
	    "			ELSE\n" + 
	    "				SELECT INTO _qty PUBLIC.ISNULL(MAX(iduser),0)::integer + 1 FROM org.users;\n" + 
	    "				INSERT INTO org.users (iduser, idperson, username, estado, idcostscentre) VALUES (_qty, _idperson, _username, '', _idcostscentre);\n" + 
	    "				RETURN _qty;\n" + 
	    "			END IF;\n" + 
	    "		END IF;\n" + 
	    "	END IF;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;\n";
	}
	
	if(!DBControl.existFunction("org","userexists","character varying")){
	    script += "CREATE OR REPLACE FUNCTION org.userexists(_username character varying) RETURNS boolean AS $BODY$\n" + 
	    "BEGIN\n" + 
	    "	RETURN (COUNT(*)>0) FROM pg_roles\n" + 
	    "			WHERE rolname = _username;\n" + 
	    "END;$BODY$\n" + 
	    "    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;";
	}
	*/
	return script;
    }

    public void setSchemes(ResultSet schemes) {
	this.schemes = schemes;
    }

    public ResultSet getSchemes() {
	try {
	    schemes.beforeFirst();
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	return schemes;
    }
}

