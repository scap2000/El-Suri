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
 * ProveedoresMain.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces;

import java.awt.BorderLayout;
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
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.Proveedor;

public class ProveedoresMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{278, 109, 63}, "Proveedores", dataRow);
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name", false);
    private TFInput tfDomicilio = new TFInput(DataTypes.STRING, "Domicilio", false);
    private TFInput tfTelefono = new TFInput(DataTypes.STRING, "Teléfono", false);
    private TFInput tfEmail = new TFInput(DataTypes.STRING, "E-mail", false);
    private TFInput tfRentabilidad = new TFInput(DataTypes.DOUBLE, "% Rta.", false);

    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private BasicDialog parentContainer;
    private Proveedor proveedor;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();

    private BasicPanel jpCenter = new BasicPanel();
    
    public ProveedoresMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar proveedores"));
	this.setSize(new Dimension(543, 306));
	listPanel.setBounds(new Rectangle(10, 105, 520, 185));
	tfNombre.setBounds(new Rectangle(10, 20, 235, 35));
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    });
	tfDomicilio.setBounds(new Rectangle(250, 20, 275, 35));
	tfTelefono.setBounds(new Rectangle(10, 60, 120, 35));
	tfEmail.setBounds(new Rectangle(135, 60, 200, 35));
	tfRentabilidad.setBounds(new Rectangle(340, 60, 65, 35));
	tfRentabilidad.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveData();
		    }
		}

	    });
	btnSave.setBounds(new Rectangle(440, 70, 30, 25));
	btnSave.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnSave_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(470, 70, 30, 25));
	btnDelete.setBounds(new Rectangle(500, 70, 30, 25));
	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(410, 70, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	jpCenter.add(tfNombre, null);
	jpCenter.add(btnFind, null);
	jpCenter.add(tfDomicilio, null);
	jpCenter.add(tfTelefono, null);
	jpCenter.add(tfEmail, null);
	jpCenter.add(tfRentabilidad, null);
	jpCenter.add(btnSave, null);
	jpCenter.add(btnCancel, null);
	jpCenter.add(btnDelete, null);
	jpCenter.add(listPanel, null);
	this.add(jpCenter, BorderLayout.CENTER);
	proveedor = new Proveedor();
	setHeaderList();
	refresh();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	headerList.addElement("Teléfono");
	headerList.addElement("*");
	headerList.addElement("% Rta.");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("taller.getAllProveedores", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfNombre.getStringForSQL());
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	    proveedor = new Proveedor(new Integer(dataRow.elementAt(0).toString()), dataRow.elementAt(1).toString(), dataRow.elementAt(2).toString(), dataRow.elementAt(3).toString(), dataRow.elementAt(4).toString(), new Double(dataRow.elementAt(5).toString()));
	    tfNombre.setValue(proveedor.getNombre());
	    tfDomicilio.setValue(proveedor.getDomicilio());
	    tfTelefono.setValue(proveedor.getTelefono());
	    tfEmail.setValue(proveedor.getEmail());
	    tfRentabilidad.setValue(proveedor.getRentabilidad());
	    blank = false;
	}
    }

    private void clearData() {
	proveedor = new Proveedor();
	tfNombre.setValue("");
	tfDomicilio.setValue("");
	tfTelefono.setValue("");
	tfEmail.setValue("");
	tfRentabilidad.setValue(0.0);
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
	if (!blank){
	    _continue = Advisor.question("Agregar/Modificar proveedor", "¿Está seguro de modificar el proveedor \"" + proveedor.getNombre() + "\"?");
	} else {
	    proveedor = new Proveedor();
	}
	if (_continue) {
	    if (tfNombre.getString().length() == 0) {
	        Advisor.messageBox("Debe ingresar un nombre para el proveedor", "Nombre vacío");
	    } else {
		proveedor.setNombre(tfNombre.getString());
		proveedor.setDomicilio(tfDomicilio.getString());
		proveedor.setTelefono(tfTelefono.getString());
		proveedor.setEmail(tfEmail.getString());
		proveedor.setRentabilidad(tfRentabilidad.getDouble());
		if (proveedor.saveData() != -1) {
		    clearData();
		    refresh();
		    _returns = true;
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar grabar los datos", "Agregar/Modificar proveedor");
		}
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

    private void btnDelete_actionPerformed(ActionEvent e) {
	deleteItem();
    }
    
    private void deleteItem() {
	if (dataRow.size() > 0) {
	    if (Advisor.question("Remover Proveedor", "¿Está seguro de remover el Proveedor \"" + dataRow.elementAt(1) + "\"?")) {
		if (LibSQL.getBoolean("taller.removeproveedor", dataRow.elementAt(0))) {
		    clearData();
		    refresh();
		} else {
		    Advisor.messageBox("Ha ocurrido un error al intentar remover el proveedor", "Remover proveedor");
		}
	    }
	}
    }
}
