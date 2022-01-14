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
 * TallerMain.java
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

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.ordenservicio.PanelOrdenServicio;

public class TallerMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[]{70, 162, 275 }, "Órdenes de Servicio", dataRow) {

	@Override
	public void finishLoading() {
	    refreshDetail();
	}
    };

    private TFInput tfNumero = new TFInput(DataTypes.STRING, "Nro. Orden", false);
    private TFInput tfCliente = new TFInput(DataTypes.STRING, "Cliente", false);

    private Vector mecanicosDataRow = new Vector();
    private GridPanel mecanicosListPanel = new GridPanel(5000, new int[]{300, 108, 108}, "Mecánicos", mecanicosDataRow);
    private BasicDialog parentContainer;
    private boolean blank = true;
    private FindButton btnFind = new FindButton();

    private TFInput tfBuscarMecanico = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbMecanicos = new CBInput(0, "Mecánico");
    private AssignButton btnAssignMecanico = new AssignButton(true);

    private BasicPanel jpCenter = new BasicPanel();

    private int idOrdenServicio = -1;
    
    public TallerMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(null);
	jpCenter.setBorder(BorderPanel.getBorderPanel("Asignar mecánicos"));
	this.setSize(new Dimension(591, 376));
	listPanel.setBounds(new Rectangle(10, 60, 575, 150));
	mecanicosListPanel.setBounds(new Rectangle(10, 255, 575, 105));
	tfCliente.setBounds(new Rectangle(110, 20, 220, 35));
	tfCliente.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    });
	tfNumero.setBounds(new Rectangle(10, 20, 95, 35));
	tfNumero.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			refresh();
		    }
		}

	    });
	btnAssignMecanico.setBounds(new Rectangle(420, 225, 30, 25));
	btnAssignMecanico.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAssignMecanico_actionPerformed(e);
		}
	    });
	btnFind.setBounds(new Rectangle(340, 30, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	jpCenter.add(tfCliente, null);
	jpCenter.add(btnFind, null);
	jpCenter.add(tfNumero, null);
	jpCenter.add(listPanel, null);
	jpCenter.add(mecanicosListPanel, null);
	jpCenter.add(tfBuscarMecanico, null);
	jpCenter.add(cbMecanicos, null);
	jpCenter.add(btnAssignMecanico, null);
	tfBuscarMecanico.setBounds(new Rectangle(10, 215, 120, 35));
	tfBuscarMecanico.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadComboMecanicos(tfBuscarMecanico.getStringForSQL());
		    }
		}

	    });

	cbMecanicos.setBounds(new Rectangle(135, 215, 280, 35));

	this.add(jpCenter, BorderLayout.CENTER);
	mecanicosListPanel.removeControls();
	loadComboMecanicos("''");
	setHeaderList();
	refresh();
    }

    private void loadComboMecanicos(String _filtro) {
	cbMecanicos.loadJCombo("taller.getAllMecanicos", _filtro + ",0,0");
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("* @ O. Serv.");
	headerList.addElement("Número");
	headerList.addElement("* idestado");
	headerList.addElement("* Estado");
	headerList.addElement("* idcliente");
	headerList.addElement("Cliente");
	headerList.addElement("* Teléfono");
	headerList.addElement("* ingresovehiculo");
	headerList.addElement("* egresovehiculo");
	headerList.addElement("* Iniciada");
	headerList.addElement("* fechafin");
	headerList.addElement("* tiempoestimado");
	headerList.addElement("* $ Estimado");
	headerList.addElement("* numerofactura");
	headerList.addElement("* $ Facturado");
	headerList.addElement("* idvehiculo");
	headerList.addElement("* idtipovehiculo");
	headerList.addElement("Modelo");
	headerList.addElement("* # Cuadro");
	headerList.addElement("* Marca");
	headerList.addElement("* Anio");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    refreshDetail();
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    PanelOrdenServicio _panelOrdenServicio = new PanelOrdenServicio();
		    ExtendedInternalFrame newOrdenServicio = new ExtendedInternalFrame("Nueva Orden de Servicio", _panelOrdenServicio);
		    _panelOrdenServicio.setOrdenServicio(new OrdenServicio(new Integer(dataRow.elementAt(0).toString())));
		    newOrdenServicio.setClosable(true);
		    newOrdenServicio.show();
		}
	    }
	});
	listPanel.setParams("taller.getAllOrdenesServicio", "'', '', 1, '', ''", headerList);

	Vector mecanicosHeaderList = new Vector();
	mecanicosHeaderList.addElement("* ID Orden de Servicio");
	mecanicosHeaderList.addElement("* ID Mecánico");
	mecanicosHeaderList.addElement("Nombre");
	mecanicosHeaderList.addElement("Inicio");
	mecanicosHeaderList.addElement("Fin");
	mecanicosListPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    if (mecanicosDataRow.size() > 0 && dataRow.size() > 0) {
			if (mecanicosDataRow.elementAt(4).equals("")) {
			    if (Advisor.question("Fin del trabajo", "¿Está seguro de finalizar el trabajo seleccionado?")) {
				if (LibSQL.getBoolean("taller.setfinmecanicoporordenservicio", mecanicosDataRow.elementAt(0) + "," + mecanicosDataRow.elementAt(1))) {
				    refreshDetail();
				}
			    }
			}
		    }
		}
	    }
	});
	mecanicosListPanel.setParams("taller.getAllMecanicosPorOrdenServicio", "-1", mecanicosHeaderList);
    }

    public void refresh() {
	idOrdenServicio = -1;
	listPanel.refresh(tfNumero.getStringForSQL() + "," + tfCliente.getStringForSQL() + ", 1, '', ''");
    }
    
    private void refreshDetail() {
	idOrdenServicio = -1;
	if (dataRow.size() > 0) {
	    idOrdenServicio = new Integer(dataRow.elementAt(0).toString());
	}
	mecanicosListPanel.refresh(idOrdenServicio);
    }

    private void clearData() {
	tfCliente.setValue("");
	tfNumero.setValue("");
	blank = true;
    }
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	boolean _continue = true;
	if (!blank){
	    //_continue = Advisor.question("Agregar/Modificar mecánico", "¿Está seguro de modificar el mecánico \"" + mecanico.getNombre() + "\"?");
	} else {
	}
	if (_continue) {
	}
	return _returns;
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnAssignMecanico_actionPerformed(ActionEvent e) {
	if (idOrdenServicio == -1) {
	    Advisor.messageBox("Debe seleccionar una Orden de Servicio", "Error");
	} else if (cbMecanicos.getSelectedIndex() == -1) {
	    Advisor.messageBox("Debe seleccionar un mecánico", "Error");
	} else {
	    if (Advisor.question("Asignar mecánico", "¿Desea asignar a " + cbMecanicos.getSelectedItem() + " a la Orden Nº " + dataRow.elementAt(1) + "?")) {
		if (LibSQL.getBoolean("taller.addmecanicoporordenservicio", idOrdenServicio + "," + cbMecanicos.getSelectedValue())) {
		    refreshDetail();
		} else {
		    Advisor.messageBox("Ocurrió un error al intentar asignar el mecánico", "Error");
		}
	    }
	}
    }
}
