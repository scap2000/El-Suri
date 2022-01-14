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
 * TiposMecanicaMain.java
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

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.TipoMecanica;

public class TiposMecanicaMain extends BasicPrimitivePanel {

    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[] {260, 65, 65}, "Tipos de Mecánica", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfValorHora = new TFInput(DataTypes.MONEY, "$ Hora", false);
     
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    private BasicDialog parentContainer;
    private TipoMecanica tipoMecanica;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();
    
    public TiposMecanicaMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(464, 323));
	panelData.setPreferredSize(new Dimension(340, 70));
	panelData.setLayout(null);
	tfNombre.setBounds(new Rectangle(15, 25, 220, 35));
	tfValorHora.setBounds(new Rectangle(240, 25, 60, 35));
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnSave.setBounds(new Rectangle(345, 35, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSave_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(380, 35, 30, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnDelete.setBounds(new Rectangle(415, 35, 30, 25));
	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	btnFind.setBounds(new Rectangle(310, 35, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	panelData.add(btnFind, null);
	panelData.add(btnCancel, null);
	panelData.add(btnDelete, null);
	panelData.add(tfNombre, null);
	panelData.add(tfValorHora, null);
	panelData.add(btnSave, null);
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    });
	tfValorHora.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveData();
		    }
		}

	    });
	this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar tipo de Mecánica", Color.blue, Color.black));
	setHeaderList();
	refresh();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Nombre");
	headerList.addElement("$ Hora");
	headerList.addElement("$ Minuto");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    clearData();
	        } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
	            loadData();
	        }
	    }
	});
	listPanel.setParams("taller.getAllTiposMecanica", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfNombre.getStringForSQL());
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    tipoMecanica = new TipoMecanica(new Integer(dataRow.elementAt(0).toString()), dataRow.elementAt(1).toString(), new Double(dataRow.elementAt(2).toString()));
	    tfNombre.setValue(tipoMecanica.getNombre());
	    tfValorHora.setValue(tipoMecanica.getValorHora());
	}
	blank = false;
    }

    private void deleteItem() {
	if (dataRow.size() > 0) {
	    if (Advisor.question("Remover Tipo de Mecánica", "¿Está seguro de remover el Tipo de Mecánica \"" + dataRow.elementAt(1) + "\"?")) {
		if (LibSQL.getBoolean("taller.removetipomecanica", dataRow.elementAt(0))) {
		    clearData();
		    refresh();
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar remover el tipo de mecánica", "Remover tipo de mecánica");
		}
	    }
	}
    }

    private void clearData() {
	tfNombre.setValue("");
	tfValorHora.setValue(0);
	blank = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
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

    @Override
    public boolean saveData() {
	boolean _returns = false;
	boolean _continue = true;
	if (blank){
	    tipoMecanica = new TipoMecanica();
	} else {
	    _continue = Advisor.question("Agregar/Modificar Tipo de Mecánica", "¿Está seguro de modificar el tipo de mecánica " + tipoMecanica.getNombre() + "?");
	}
	if (_continue) {
	    if (tfNombre.getString().length() == 0) {
	        Advisor.messageBox("Debe ingresar un nombre para el tipo de mecánica", "Nombre vacío");
	    } else if (tfValorHora.getAmount() <= 0) {
	        Advisor.messageBox("El valor de la hora debe ser mayor a $ 0,00", "Valor hora");
	    } else {
		tipoMecanica.setNombre(tfNombre.getString());
		tipoMecanica.setValorHora(tfValorHora.getAmount());
		if (tipoMecanica.saveData() != -1) {
		    clearData();
		    refresh();
		    _returns = true;
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Agregar/Modificar Tipo de Mecánica");
		}
	    }
	}
	return _returns;
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	deleteItem();
    }
}
