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
 * NewGeometrySetPanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.geo.mapping.components.LayerTree;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.GoButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.sql.LibSQLMini;


public class NewGeometrySetPanel extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    private CBInput cbComentarioTablas = new CBInput(0, "Descripción", false);
    private TFInput tfEsquemasTablas = new TFInput(DataTypes.STRING, "Tabla", false);
    private SaveDataButton btnSave = new SaveDataButton();
    private Vector<Vector> possibleGeometrySets = new Vector<Vector>();
    private TFInput tfColumnaGeom = new TFInput(DataTypes.STRING, "Columna", false);
    private TFInput tfColumnaClave = new TFInput(DataTypes.STRING, "Clave primaria", false);
    private TFInput tfTipoDatos = new TFInput(DataTypes.STRING, "Tipo de datos", false);
    private GoButton btnConnection = new GoButton();
    private LayerTree layerTree;
    private LibSQLMini libSQLMini;
    
    private String serverURL = "";
    private String database = "";
    private String user = "";
    private String password = "";
    
    private boolean connection = false;

    public NewGeometrySetPanel(LayerTree _layerTre) {
        layerTree = _layerTre;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(360, 207));
        setOpaque(true);
        cbComentarioTablas.setBounds(new Rectangle(10, 5, 315, 35));
        cbComentarioTablas.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        loadData();
                    }
                }
            });
        cbComentarioTablas.autoSize();
        btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSave_actionPerformed(e);
				 }

			     }
	);
        btnConnection.setBounds(new Rectangle(325, 20, 35, 20));
        btnConnection.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConnection_actionPerformed(e);
                }
            });
        btnConnection.setToolTipText("Configurar conexión");
        tfEsquemasTablas.setBounds(new Rectangle(10, 45, 315, 35));
        tfColumnaGeom.setBounds(new Rectangle(10, 85, 155, 35));
        tfColumnaClave.setBounds(new Rectangle(10, 125, 155, 35));
        tfTipoDatos.setBounds(new Rectangle(170, 85, 155, 35));
        centralPanel.setLayout(null);
        centralPanel.add(btnConnection, null);
        centralPanel.add(tfTipoDatos, null);
        centralPanel.add(tfColumnaClave, null);
        centralPanel.add(tfColumnaGeom, null);
        centralPanel.add(tfEsquemasTablas, null);
        centralPanel.add(cbComentarioTablas, null);
        this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnSave);
        loadPossibleGeometrySets();
	loadComboComentarioTablas();
        loadData();
        tfColumnaClave.setEditable(false);
        tfColumnaGeom.setEditable(false);
        tfEsquemasTablas.setEditable(false);
        tfTipoDatos.setEditable(false);
        loadServerData();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
        saveData();
    }
    
    private void btnConnection_actionPerformed(ActionEvent e) {
        loadPossibleGeometrySetFromServer();
    }

    private void loadPossibleGeometrySets() {
        possibleGeometrySets = new Vector<Vector>();
        ResultSet result = null;
        if (libSQLMini != null) {
            result = libSQLMini.exFunction("gis.getpossiblegeometrysets","''");
            connection = true;
        } else {
            result = LibSQL.exFunction("gis.getpossiblegeometrysets","''");
        }
        try {
            while (result.next()) {
                Vector<String> geometrySetData = new Vector<String>();
                geometrySetData.add(result.getString("schema"));
                geometrySetData.add(result.getString("table"));
                geometrySetData.add(result.getString("column"));
                geometrySetData.add(result.getString("datatype"));
                geometrySetData.add(result.getString("keycolumn"));
                geometrySetData.add(result.getString("primarykeyscount"));
                geometrySetData.add(result.getString("tabledescription"));
                geometrySetData.add(""+result.getBoolean("privilege"));
                possibleGeometrySets.add(geometrySetData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadComboComentarioTablas() {
        cbComentarioTablas.removeAllItems();
        for (int i = 0; i < possibleGeometrySets.size(); i++) {
            //si tiene privilegios SELECT se agrega al combo
            if (possibleGeometrySets.elementAt(i).elementAt(7).toString().equals("true")) {
                cbComentarioTablas.getCombo().addItem(possibleGeometrySets.elementAt(i).elementAt(6), i);    
            }
        }
    }
    
    private void loadData() {
        clearFields();
        if (cbComentarioTablas.getSelectedIndex() > -1) {
            tfEsquemasTablas.setValue(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(0) + "." +possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(1));
            tfColumnaGeom.setValue(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(2));
            tfTipoDatos.setValue(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(3));
            tfColumnaClave.setValue(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(4));
        }
    }
    
    private void clearFields() {
        tfEsquemasTablas.setValue("");
        tfColumnaClave.setValue("");
        tfTipoDatos.setValue("");
        tfColumnaGeom.setValue("");
    }

    public boolean saveData() {
        if (control()) {
            if (!connection) {
                layerTree.getDrawPanel().addGeometrySet(GaiaEnvironment.gaiaEngine.loadNewGeometrySet(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(0).toString(), possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(1).toString(), possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(2).toString(), tfColumnaClave.getString(), true));
            } else {
                layerTree.getDrawPanel().addGeometrySet(GaiaEnvironment.gaiaEngine.loadNewGeometrySet(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(0).toString(), possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(1).toString(), possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(2).toString(), tfColumnaClave.getString(), true, getServerURL(), getDatabase(), getUser(), getPassword()));
            }
            getParentInternalFrame().close();
        }
	return true;
    }
    
    private boolean control() {
        boolean retorno = true;
        int keyCount = Integer.parseInt(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(5).toString());
        if (keyCount > 1) {
            Advisor.messageBox("La tabla selecionada tiene mas de una clave Primaria ", "Mensaje");
            return false;
        } else if (GaiaEnvironment.gaiaEngine.getGeometrySetIndex(possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(0).toString(),possibleGeometrySets.elementAt(Integer.parseInt(cbComentarioTablas.getSelectedValue().toString())).elementAt(1).toString()) > -1) {
            Advisor.messageBox("Ya existe un Grupo de Geometrías que referencia a la tabla seleccionada", "Mensaje");
            return false;
        }
        return retorno;
    }
    
    private void configurePropietaryData() {
        //Solicitamos los datos del servidor propietario
        serverURL = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el URL o Nombre del Servidor", "Nombre del Servidor", JOptionPane.QUESTION_MESSAGE, null, null, getServerURL());
        if (!(serverURL == null)) {
            database = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el nombre de la Base de Datos", "Base de Datos", JOptionPane.QUESTION_MESSAGE, null, null, getDatabase());
            if (!(database == null)) {
                user = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Nombre de Usuario", "Usuario", JOptionPane.QUESTION_MESSAGE, null, null, getUser());
                if (!(user == null)) {
                    JPasswordField _jtPassword = new JPasswordField();
                    _jtPassword.setText(password);
                    if (Environment.cfg.getProperty("RequestFocus").equalsIgnoreCase("True")) {
                        _jtPassword.requestFocus();
                    }
                    JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), _jtPassword, "Contraseña", JOptionPane.OK_CANCEL_OPTION);
                    password = new String(_jtPassword.getPassword());
                }
            }
        }
    }
    
    private boolean tryToConnect() {
        boolean connect = true;
        libSQLMini = null;
        if (controlServerData()) {
            libSQLMini = new LibSQLMini(serverURL, database, user, password);
        }
        try {
            if ((libSQLMini == null) || (libSQLMini.CreateConnection() == null)) {
                connect = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
    
    private ResultSet exQuery(String _sqlStat) {
        if (libSQLMini != null) {
            return libSQLMini.exQuery(_sqlStat);
        } else {
            return LibSQL.exQuery(_sqlStat);
        }
    }

    private void closeConnection() {
        if (libSQLMini != null) {
            libSQLMini.closeConnection();
        }
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    private void loadServerData() {
        serverURL = layerTree.getDrawPanel().getEngineConfig().getPropietaryServerURL();
        database = layerTree.getDrawPanel().getEngineConfig().getPropietaryDatabase();
        user = layerTree.getDrawPanel().getEngineConfig().getPropietaryUser();
        password = layerTree.getDrawPanel().getEngineConfig().getPropietaryPassword();
    }

    private void loadPossibleGeometrySetFromServer() {
        configurePropietaryData();
        if (tryToConnect()) {
            loadPossibleGeometrySets();
            loadComboComentarioTablas();
            loadData();
            closeConnection();
        } else {
            Advisor.messageBox("<html>Error al intentar conectarse al servidor de geometrías.\nPor favor revise la <u>configuración</u> y el <u>acceso a internet</u></html>.", "Error");
        }
    }
    
    private boolean controlServerData() {
        boolean retorno = true;
        if ((serverURL == null) || (serverURL.equals(""))) {
            Advisor.messageBox("No especificó el URL o Nombre del Servidor", "Mensaje");
            retorno = false;
        } else if ((database == null) || (database.equals(""))) {
            Advisor.messageBox("No especificó el nombre de la Base de Datos", "Mensaje");
            retorno = false;
            } else if ((user == null) || (user.equals(""))) {
                Advisor.messageBox("No especificó el nombre de Usuario", "Mensaje");
                retorno = false;
                } else if ((password == null) || (password.equals(""))) {
                    Advisor.messageBox("No especificó una contraseña", "Mensaje");
                    retorno = false;
        }
        return retorno;
    }
}
