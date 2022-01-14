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
 * GaiaPrimitiveForm.java
 *
 * */
package org.digitall.lib.geo.gaia;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.SwingConstants;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.sql.LibSQL;

public class GaiaPrimitiveForm extends BasicPanel {

    private int buttonsTopBorder = 4;
    private Layer layer;
    private Object contentObject;
    private Rectangle2D.Double contentExtents;
    private BasicPanel jpButtons = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private PrintButton btnPrint = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private BasicScrollPane jsScroll = new BasicScrollPane();
    private BasicPanel jpTitulo = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicLabel lblForm = new BasicLabel();
    private BasicLabel lblTitulo = new BasicLabel();
    private ConfigFile cfg = new ConfigFile("ddesktop.conf");

    public GaiaPrimitiveForm() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setLayer(Layer layer) {
	this.layer = layer;
	layer.setEditionForm(this);
    }

    public Layer getLayer() {
	return layer;
    }

    public void setContentObject(Object _contentObject) {
	contentObject = _contentObject;
    }

    public void setContentExtents(Rectangle2D.Double _contentExtents) {
	contentExtents = _contentExtents;
    }

    public Object getContentObject() {
	return contentObject;
    }

    public PrintButton getPrintButton(){
	return btnPrint;
    }

    private void jbInit() throws Exception {
	setLayout(new BorderLayout());
	this.add(jpButtons, BorderLayout.SOUTH);

	jpButtons.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.LINE_AXIS));
	jpButtons.setBorder(BorderFactory.createMatteBorder(buttonsTopBorder, 0, 0, 0, BasicConfig.PANELCONTAINER_BACKGROUND_COLOR));

	btnDelete.setMaximumSize(btnDelete.getPreferredSize());
	btnSave.setMaximumSize(btnSave.getPreferredSize());
	btnPrint.setMaximumSize(btnPrint.getPreferredSize());
	btnClose.setMaximumSize(btnDelete.getPreferredSize());
	
	addButton(btnClose);
	addButton(btnDelete);
	addButton(btnSave);
        addButton(btnPrint);
	btnSave.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				    saveData();
				 }

			     }
	);
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				    confirmDeletion();
				 }

			     }
	);
        btnPrint.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                    printReport();
                                 }

                             }
        );
	btnClose.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				    closeForm();
				 }

			     }
	);

	jpTitulo.setPreferredSize(new Dimension(10, 40));
	jpTitulo.setLayout(borderLayout1);
	lblForm.setText("Formulario   ");
	lblForm.setPreferredSize(new Dimension(38, 20));
	lblForm.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTitulo.setText("SIN TITULO");
	lblTitulo.setPreferredSize(new Dimension(38, 20));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setFont(new Font("Lucida Sans", 1, 12));
	btnSave.setToolTipText("Agregar/Modificar");
	btnDelete.setToolTipText("Limpiar campos");
	btnClose.setToolTipText("Cerrar Ventana");
	jpTitulo.add(lblForm, BorderLayout.NORTH);
	jpTitulo.add(lblTitulo, BorderLayout.CENTER);
	this.add(jpTitulo, BorderLayout.NORTH);
	this.add(jsScroll, BorderLayout.CENTER);
	setEnabledDeleteButton(false);
	setEnabledCloseButton(false);
    }
    
    public void saveData() {
	System.out.println("Se necesita override en \"saveData()\"");
    }
    
    public void delete() {
	System.out.println("Se necesita override en \"delete()\"");
    }
    
    public void close() {
	System.out.println("Se necesita override en \"close()\"");
    }

    private void confirmDeletion() {
	if (Advisor.question("Aviso", "¿Está seguro de borrar la entidad?\n¡Esta operación no se puede deshacer!")) {
	    delete();
	}
    }
    
    private void closeForm(){
	close();
    }

    public void printReport() {
        System.out.println("Se necesita override en \"printReport()\"");
    }

    protected void setCentralPanel(BasicPanel _panel) {
	_panel.setPreferredSize(_panel.getSize());
	jsScroll.getViewport().removeAll();
	jsScroll.getViewport().add(_panel, null);
        jsScroll.setViewportView(_panel);
	jsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
    public void showForm() {
	if (GaiaEnvironment.formsFrame != null) {
	    GaiaEnvironment.formPanel.setSize(getPreferredSize());
	    GaiaEnvironment.formsFrame.setSize(GaiaEnvironment.formPanel.getSize());
	    GaiaEnvironment.formsFrame.show();
	}
	GaiaEnvironment.formPanel.removeAll();
	GaiaEnvironment.formPanel.add(this, BorderLayout.CENTER);
	GaiaEnvironment.formPanel.repaint();
    }

    public void hideForm() {
	try {
	    if (GaiaEnvironment.formsFrame != null) {
		GaiaEnvironment.formsFrame.close();
	    }
	    GaiaEnvironment.formPanel.remove(this);
	    GaiaEnvironment.formPanel.repaint();
	} catch (NullPointerException x) {
	    
	}
    }
    
    public void setTitle(String _title) {
	lblTitulo.setText(_title);
    }
    
    public void setEnabledCloseButton(boolean _valor){
	btnClose.setEnabled(_valor);
    }
    
    public void setEnabledDeleteButton(boolean _valor){
	btnDelete.setEnabled(_valor);
    }

    public void setEnabledPrintButton(boolean _valor){
	btnPrint.setEnabled(_valor);
    }

    public void setEnabledSaveButton(boolean _valor){
	btnSave.setEnabled(_valor);
    }
    
    public void setVisibleCloseButton(boolean _valor){
        btnClose.setVisible(_valor);
    }
    
    public void setVisibleDeleteButton(boolean _valor){
        btnDelete.setVisible(_valor);
    }

    public void setVisiblePrintButton(boolean _valor){
        btnPrint.setVisible(_valor);
    }

    public void setVisibleSaveButton(boolean _valor){
        btnSave.setVisible(_valor);
    }
    
    private FileWriter openFile(String _reportName) {
	String _path = cfg.getProperty(_reportName) + File.separator;
	JFileChooser chooser = new JFileChooser(_path);
	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF File Selected
	    try {
		String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.toLowerCase().endsWith(".csv")) {
		    path += ".csv";
		}
		cfg.setProperty(_reportName, chooser.getSelectedFile().getParent());
		return new FileWriter(path, false);
	    } catch (IOException x) {
		Advisor.messageBox("Error de E/S", "Error " + _reportName);
		x.printStackTrace();
		return null;
	    }
	} else {
	    return null;
	}
    }

    public void doCSVReport(String _reportName, String _sqlFunction) {
	if (_sqlFunction.length() > 0) {
	    FileWriter _reportFile = openFile(_reportName);
	    if (_reportFile != null) {
		ResultSet _resultSet = LibSQL.exFunction(_sqlFunction);
		try {
		    if (_resultSet.next()) {
			ResultSetMetaData _resultSetMetaData = _resultSet.getMetaData();
			try {
			    for (int i = 0; i < _resultSetMetaData.getColumnCount(); i++) {
				_reportFile.write("\"" + _resultSetMetaData.getColumnName(i+1) + "\"\t");
			    }
			    _reportFile.write("\n");
			    do {
				for (int i = 0; i < _resultSetMetaData.getColumnCount(); i++) {
				    if (_resultSet.getString(i+1) != null) {
					String _field = _resultSet.getString(i+1);
				        //_field = _field.replaceAll("\"", "\\\"");
				        //_field = _field.replaceAll("\n", "\\\n");
				        _field = _field.replaceAll("[\\r\\n\"\t]", "");
				        //_field = _field.replaceAll("\t", "\\\t");
				        _reportFile.write("\"" + _field + "\"\t");
				    } else {
				        _reportFile.write("\"\"\t");
				    }
				}
			        _reportFile.write("\n");
			    } while (_resultSet.next());
			    _reportFile.close();
		        } catch (Exception e) {
		            // TODO
		            Advisor.printException(e);
		        }
		    }
		} catch (SQLException x) {
		    Advisor.printException(x);
		}
	    }
	}
    }

    protected void addButton(BasicButton _button) {
	jpButtons.add(_button);
    }

}

