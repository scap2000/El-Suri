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
 * PanelBorrarEncuestas.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.border.BevelBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageFileView;
import org.digitall.lib.image.ImageFilter;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.ImagePreview;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;
import org.digitall.apps.gaia.relevamientos.comercial_2009.clases.ClassDetalleRelevamientoPublicidad;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;

public class PanelBorrarEncuestas extends BasicPrimitivePanel {

    private DeleteButton btnBorrarEncuesta = new DeleteButton();
    private CloseButton btnClose = new CloseButton();
    private FindButton btnBuscar = new FindButton();
    
    private BasicPanel contentPanel = new BasicPanel("");
    private BasicPanel jPanel1 = new BasicPanel();
    
    private TFInput tfNroEncuestador = new TFInput(DataTypes.INTEGER, "Nº de Encuestador", false);
    private TFInput tfZonaNro = new TFInput(DataTypes.STRING, "Zona Nº", false);
    private TFInput tfNroEncuesta = new TFInput(DataTypes.INTEGER, "Nº de Encuesta", false);

    private int[] sizeColumnList = { 62, 69, 89, 102, 111, 144};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Encuestas", dataRow){
	public void calculate() {
	    //controlBotones();
	}
    }
    ;
    
    private boolean tipoEncuesta = true;
    private static final boolean COMERCIO = true;
    private static final boolean PUBLICIDAD = false;

    public PanelBorrarEncuestas(boolean _tipoEncuesta) {
	tipoEncuesta = _tipoEncuesta;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(649, 352));
	this.setTitle("Borrar Encuesta");
	//taDescripcion.setEditable(false);

	//spFoto = new BasicScrollPane(lblFoto);

	jPanel1.add(btnBuscar, null);
	jPanel1.add(tfNroEncuestador, null);
	jPanel1.add(tfNroEncuesta, null);
	jPanel1.add(tfZonaNro, null);
	contentPanel.add(jPanel1, null);
	contentPanel.add(listPanel, null);
	addButton(btnClose);
	this.add(contentPanel, null);
	addButton(btnBorrarEncuesta);
	contentPanel.setBounds(new Rectangle(0, 0, 655, 345));
	contentPanel.setLayout(null);

	tfNroEncuestador.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  refreshGrilla();
						      }
						  }

					      }
	);
	tfZonaNro.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  refreshGrilla();
						      }
						  }

					      }
	);
	tfNroEncuesta.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  refreshGrilla();
						      }
						  }

					      }
	);
	tfNroEncuestador.setBounds(new Rectangle(10, 20, 115, 35));
	tfZonaNro.setBounds(new Rectangle(150, 20, 115, 35));
	tfNroEncuesta.setBounds(new Rectangle(290, 20, 115, 35));
	btnBorrarEncuesta.setBounds(new Rectangle(585, 360, 40, 25));
	btnBorrarEncuesta.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBorrarEncuesta_actionPerformed(e);
		}
	    }
	);
	listPanel.setBounds(new Rectangle(5, 70, 635, 235));
	jPanel1.setBounds(new Rectangle(5, 0, 635, 65));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createTitledBorder("Datos de Encuesta"));
	btnBuscar.setBounds(new Rectangle(425, 35, 20, 20));
	btnBuscar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBuscar_actionPerformed(e);
		}
	    }
	);
	btnClose.setBounds(new Rectangle(615, 360, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	setHeaderList();
	//refreshGrilla();
	loadName();
	btnBorrarEncuesta.setEnabled(false);
    }
    
    private void loadName(){
	if (tipoEncuesta)  {
	    jPanel1.setBorder(BorderPanel.getBorderPanel("Datos de Encabezado - Censo Comercial"));
	} else  {
	    jPanel1.setBorder(BorderPanel.getBorderPanel("Datos de Encabezado - Relevamiento de Publicidad"));
	}
	
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Nro. Enc.");
	headerList.addElement("Nro. Zona");
	headerList.addElement("Nro. Encuesta");
	headerList.addElement("Usuario");
	headerList.addElement("Fecha de Carga");
	headerList.addElement("(X,Y)");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   btnBorrarEncuesta.setEnabled(true);
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   
					       }
					   }

				       }
	);
	
	listPanel.getTable().addKeyListener(new KeyAdapter() {
	     public void keyReleased(KeyEvent key) {
		 if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
			    
		 }
	     }
	 });
	 
	String params = ""+tipoEncuesta+","+tfNroEncuesta.getValue()+",'"+tfZonaNro.getValue()+"',"+tfNroEncuestador.getValue();
	listPanel.setParams("gea_salta.getdatosencuesta", params, headerList);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	this.getParentInternalFrame().close();
    }
    
    private void btnBorrarEncuesta_actionPerformed(ActionEvent e) {
	String params = ""+dataRow.elementAt(0);
	if (Advisor.question("DBAdmin", "¿Confirma la eliminación de la encuesta seleccionada?")) {
	    String nombreStored = "";
	    if(tipoEncuesta){
		nombreStored = "gea_salta.delcensocomercial2009";
	    }else{
	        nombreStored = "gea_salta.delrelevamientopublicidad2009";
	    }
	    if(LibSQL.getInt(nombreStored,params) > 0) {
	        Advisor.messageBox("La encuesta seleccionada se eliminó exitosamente", "Mensaje");
		tfNroEncuestador.setValue("");
	        tfNroEncuesta.setValue("");
	        tfZonaNro.setValue("");
		refreshGrilla();
		//Advisor.messageBox("La encuesta Nº "+ tfNroEncuesta.getInteger() + ", realizada por el encuestador Nº "+ tfNroEncuestador.getInteger() +\"\\nen la zona Nº \"+ tfZonaNro.getValue() + \" ya fué registrada", "Mensaje");
	    }else{
		Advisor.messageBox("Error al eliminar encuesta", "Error");
	    }    
	}
    }
    
    private void refreshGrilla(){
	String params = ""+tipoEncuesta+","+tfNroEncuesta.getValue()+",'"+tfZonaNro.getValue()+"',"+tfNroEncuestador.getValue();
	listPanel.refresh(params);
	btnBorrarEncuesta.setEnabled(false);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
	refreshGrilla();
    }
}

