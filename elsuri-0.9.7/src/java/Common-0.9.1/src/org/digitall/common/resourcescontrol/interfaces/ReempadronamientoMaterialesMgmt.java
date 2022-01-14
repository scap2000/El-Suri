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
 * ReempadronamientoMaterialesMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

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
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ReempadronamientoMaterialesMgmt extends BasicPrimitivePanel {

    private BasicPanel jpBuscar = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel jpAsignarCuenta = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    
    private int[] sizeColumnList = { 516 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Materiales sin Cuentas Asignadas", dataRow) {
	@Override
	public void finishLoading() {
	    btnAsignarCuenta.setEnabled(false);
	}

        @Override
        public void calculate() {
            controlBotones();
        }
    };

    private TFInput tfMaterial = new TFInput(DataTypes.STRING, "Material", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    
    private AcceptButton btnAsignarCuenta = new AcceptButton();
    private FindButton btnBuscar = new FindButton();
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);

    public ReempadronamientoMaterialesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setSize(new Dimension(780, 600));
	this.setPreferredSize(new Dimension(580, 420));

        this.setSize(new Dimension(580, 420));
        listPanel.setPreferredSize(new Dimension(400, 200));

        tfMaterial.setBounds(new Rectangle(100, 20, 365, 35));
        tfFindAccount.setBounds(new Rectangle(20, 20, 135, 35));
        tfMaterial.setBounds(new Rectangle(85, 20, 365, 35));
        tfFindAccount.setBounds(new Rectangle(20, 20, 135, 35));
        tfMaterial.setBounds(new Rectangle(20, 20, 365, 35));
        tfMaterial.setBounds(new Rectangle(95, 20, 365, 35));
        tfFindAccount.setBounds(new Rectangle(20, 15, 135, 35));
        btnAsignarCuenta.setEnabled(false);

        jpBuscar.setBounds(new Rectangle(0, 0, 750, 70));
	jpBuscar.setPreferredSize(new Dimension(1, 70));
	jpBuscar.setSize(new Dimension(780, 70));
        jpBuscar.add(btnBuscar, null);
        jpBuscar.add(tfMaterial, null);
        jpBuscar.setLayout(null);

        panelCentro.setLayout(new BorderLayout());
        panelCentro.add(listPanel, BorderLayout.CENTER);

        jpAsignarCuenta.setSize(new Dimension(780, 80));
        jpAsignarCuenta.setPreferredSize(new Dimension(1, 70));
        jpAsignarCuenta.add(tfFindAccount, null);
        jpAsignarCuenta.add(cbAccounting, null);
        jpAsignarCuenta.add(btnAsignarCuenta);
        jpAsignarCuenta.setLayout(null);

        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBounds(new Rectangle(0, 0, 580, 420));
        panelContenedor.setPreferredSize(new Dimension(580, 420));
        panelContenedor.setSize(new Dimension(580, 420));
        panelContenedor.add(jpBuscar, BorderLayout.NORTH);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);

        panelContenedor.add(jpAsignarCuenta, BorderLayout.SOUTH);
        this.setContent(panelContenedor);
	btnBuscar.setBounds(new Rectangle(630, 30, 30, 25));
	btnBuscar.setSize(new Dimension(30, 25));
        btnBuscar.setBounds(new Rectangle(475, 30, 30, 25));
        btnBuscar.setBounds(new Rectangle(390, 30, 30, 25));
        btnBuscar.setBounds(new Rectangle(475, 30, 30, 25));
        btnBuscar.setBounds(new Rectangle(530, 30, 30, 25));
        btnBuscar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

        }  
	);
        cbAccounting.setBounds(new Rectangle(170, 20, 355, 35));
        cbAccounting.setBounds(new Rectangle(175, 20, 345, 35));
        cbAccounting.setBounds(new Rectangle(165, 15, 345, 35));
	btnAsignarCuenta.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnAsingarCuenta_actionPerformed(e);
					}

				    }
	);
	btnAsignarCuenta.setToolTipText("Asignar Cuenta a los Materiales seleccionados");
        btnAsignarCuenta.setBounds(new Rectangle(535, 35, 30, 20));
        btnAsignarCuenta.setBounds(new Rectangle(535, 35, 30, 20));
        btnAsignarCuenta.setBounds(new Rectangle(520, 30, 30, 20));

        jpBuscar.setBorder(BorderPanel.getBorderPanel("Buscar"));
        jpAsignarCuenta.setBorder(BorderPanel.getBorderPanel("Asignar Cuenta"));
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
                    controlBotones();
		    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    }
		}

	    });
        
        tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {

                    public void keyReleased(KeyEvent e) {
                        if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                            loadComboAccounting();
                        }
                    }

                });
        
        tfMaterial.getTextField().addKeyListener(new KeyAdapter() {

                    public void keyReleased(KeyEvent e) {
                        if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                            refresh();
                        }
                    }

                });
        
        setHeaderList();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Nombre del Material"));
        
	listPanel.setParams("resourcescontrol.getAllRecursosSinCuentas", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfMaterial.getStringForSQL());
    }

    private void btnFind_actionPerformed(ActionEvent e) {
        refresh();
    }
    
    private void btnAsingarCuenta_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    public boolean saveData() {
	String _selectedIDS = "";
        boolean _return = false;
	if (listPanel.getSelectedsIDSeparatedByComma().length() > 0 ) {
	    if (cbAccounting.getSelectedIndex() != -1) { 
                if (Advisor.question("Asignar Cuenta","<html><p align=center>¿Está seguro de asignar la Cuenta<br><u>" + cbAccounting.getSelectedItem() + "</u><br>a los Materiales seleccionados?</html>")) {
                    _selectedIDS = listPanel.getSelectedsIDSeparatedByComma(); 
                    if (LibSQL.getBoolean("resourcescontrol.setCuentaxRecursos","'" + _selectedIDS + "'," + cbAccounting.getSelectedValue())) {
                        refresh();
                        _return = true;
                    } else { 
                        Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
                    }
                }
            } else {
                Advisor.messageBox("El combo \"Cuenta\" no debe estar vacío", "Datos Requeridos");
            }
	} else {
	    Advisor.messageBox("Debe seleccionar por lo menos un Material", "Datos Requeridos");
	}
        return _return;
    }
    
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione uno o varios Materiales, seleccione la Cuenta y presione el botón \"Aceptar\"");
    }

    private void loadComboAccounting() {
        String param = "5000,'" + tfFindAccount.getString() + "'";
        cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByFilter", param));
    }
    
    private void controlBotones() {
        btnAsignarCuenta.setEnabled(listPanel.getSelectedsID().size() > 0);
    }
}
