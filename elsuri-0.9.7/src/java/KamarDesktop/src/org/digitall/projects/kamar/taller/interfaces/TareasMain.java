/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * TareasMain.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.Tarea;

public class TareasMain extends BasicPrimitivePanel {

    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(100, new int[] {348, 134}, "Tareas", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfCodigo = new TFInput(DataTypes.STRING, "Código", false);
     
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicDialog parentContainer;
    private TFInput tfBuscarTipoMecanica = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbTiposMecanica = new CBInput(0, "Tipo de Mecánica");
    private Tarea tarea;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();
    
    public TareasMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(549, 323));
	//listPanel.setBounds(new Rectangle(5, 80, 340, 235));
	panelData.setPreferredSize(new Dimension(340, 120));
	panelData.setLayout(null);
	tfNombre.setBounds(new Rectangle(95, 25, 440, 35));
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			listPanel.refresh(tfNombre.getStringForSQL());
		    }
		}

	    }
	);
	tfCodigo.setBounds(new Rectangle(10, 25, 80, 35));
	tfCodigo.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			listPanel.refresh(tfCodigo.getStringForSQL());
		    }
		}

	    }
	);
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnSave.setBounds(new Rectangle(445, 80, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSave_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(475, 80, 30, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(415, 80, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	btnDelete.setBounds(new Rectangle(505, 80, 30, 25));
	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	panelData.add(tfNombre, null);
	panelData.add(tfCodigo, null);
	panelData.add(tfBuscarTipoMecanica, null);
	panelData.add(cbTiposMecanica, null);
	panelData.add(btnFind, null);
	panelData.add(btnSave, null);
	panelData.add(btnCancel, null);
	panelData.add(btnDelete, null);
	tfBuscarTipoMecanica.setBounds(new Rectangle(10, 70, 85, 35));
	tfBuscarTipoMecanica.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboTiposMecanica(tfBuscarTipoMecanica.getStringForSQL());
		    }
		}

	    }
	);

	cbTiposMecanica.setBounds(new Rectangle(100, 70, 310, 35));

	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar tarea", Color.blue, Color.black));
	cbTiposMecanica.setGeneralItem(true);
	loadComboTiposMecanica("''");
	setHeaderList();
    }

    private void loadComboTiposMecanica(String _filtro) {
	cbTiposMecanica.loadJCombo("taller.getAllTiposMecanica", _filtro + ",0,0");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("* IdTarea");
	headerList.addElement("Código - Nombre");
	headerList.addElement("* Código");
	headerList.addElement("* Nombre"); //Codigo
	headerList.addElement("* IdTipoMecanica");
	headerList.addElement("Tipo Mec.");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("taller.getAllTareas", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfNombre.getStringForSQL() + "," + cbTiposMecanica.getSelectedValue());
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    tarea = new Tarea(new Integer(dataRow.elementAt(0).toString()), dataRow.elementAt(3).toString(), dataRow.elementAt(2).toString(), new Integer(dataRow.elementAt(4).toString()));
	    tfNombre.setValue(tarea.getNombre());
	    tfCodigo.setValue(tarea.getCodigo());
	    loadComboTiposMecanica("''");
	    cbTiposMecanica.setSelectedID(tarea.getIdTipoMecanica());
	}
	blank = false;
    }

    private void clearData() {
	tfNombre.setValue("");
	tfCodigo.setValue("");
	blank = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    };
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	boolean _continue = true;
	if (blank){
	    tarea = new Tarea();
	} else {
	    _continue = Advisor.question("Agregar/Modificar tarea", "¿Está seguro de modificar la tarea \"" + tarea.getNombre() + "\"?");
	}
	if (_continue) {
	    if (cbTiposMecanica.getSelectedIndex() != -1) {
		tarea.setNombre(tfNombre.getString());
		tarea.setCodigo(tfCodigo.getString());
		tarea.setIdTipoMecanica(new Integer(cbTiposMecanica.getSelectedValue().toString()));
		    if (tarea.saveData() != -1) {
			clearData();
			refresh();
			_returns = true;
		    } else {
			Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Agregar/Modificar tarea");
		    }
	    } else {
		Advisor.messageBox("No ha seleccionado un tipo de mecánica", "Agregar/Modificar Tipo de Vehículo");
	    }
	}
	return _returns;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    private void deleteItem() {
	if (dataRow.size() > 0) {
	    if (Advisor.question("Remover Tarea", "¿Está seguro de remover la Tarea \"" + dataRow.elementAt(1) + "\"?")) {
		if (LibSQL.getBoolean("taller.removetarea", dataRow.elementAt(0))) {
		    clearData();
		    refresh();
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar remover la tarea", "Remover tarea");
		}
	    }
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	deleteItem();
    }
}
