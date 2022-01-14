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
 * RecepcionarPrestacionesMgmt.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.apps.accionsocial.classes.Prestacion;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;


public class RecepcionarPrestacionesMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("");
    
    private TFInput tfStock = new TFInput(DataTypes.DOUBLE,"Stock",false);
    private TFInput tfCantidadARecibir = new TFInput(DataTypes.DOUBLE,"Cantidad a Recibir",false);

    private CBInput cbPlanesSociales = new CBInput(0,"Planes Sociales", false);
    private CBInput cbPrestaciones = new CBInput(0,"Prestaciones",false);
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnRecepcionarRecurso = new SaveDataButton();
    private BasicPrimitivePanel parentList;
    private Prestacion prestacion = new Prestacion();
    
    
    public RecepcionarPrestacionesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(384, 223));
	this.setPreferredSize(new Dimension(565, 300));
	btnClose.setBounds(new Rectangle(540, 535, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        
        btnRecepcionarRecurso.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnRecepcionarRecurso_actionPerformed(e);
                                }

                            }
        );
        
        dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
        cbPlanesSociales.setBounds(new Rectangle(20, 35, 345, 35));
        //dataPanel.add(btnRecepcionarRecurso, null);
        dataPanel.add(cbPrestaciones, null);
        dataPanel.add(cbPlanesSociales, null);
        dataPanel.add(tfStock, null);
        dataPanel.add(tfCantidadARecibir, null);
        this.add(dataPanel, BorderLayout.CENTER);
        this.addButton(btnClose);
        this.addButton(btnRecepcionarRecurso);
        cbPlanesSociales.autoSize();
	tfStock.setBounds(new Rectangle(20, 125, 115, 35));
        
        tfCantidadARecibir.setBounds(new Rectangle(205, 125, 115, 35));

        tfStock.setEnabled(false);
        cbPlanesSociales.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPlanesSociales.getSelectedIndex() > -1) {
                                                    loadComboPrestaciones();
                                                }
                                            }
                                        }

                                    }
        );
        
        cbPrestaciones.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPrestaciones.getSelectedIndex() > -1) {
                                                    loadPrestacion();
                                                }
                                            }
                                        }

                                    }
        );
	cbPrestaciones.setBounds(new Rectangle(20, 80, 345, 35));
	cbPrestaciones.autoSize();
	cbPrestaciones.setVisible(true);
        btnRecepcionarRecurso.setToolTipText("Registrar cantidad recibida");
        btnRecepcionarRecurso.setBounds(new Rectangle(340, 135, 30, 25));
        btnRecepcionarRecurso.setSize(new Dimension(30, 25));
        btnRecepcionarRecurso.setVisible(true);
        dataPanel.setBorder(BorderPanel.getBorderPanel("Recibir un Recurso"));
        tfCantidadARecibir.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           saveData();
                                                       }
                                                   }

                                               }
        );
    }
    
     public void setParentInternalFrame(ExtendedInternalFrame _e) {
	 super.setParentInternalFrame(_e);
     }
    
     private void loadComboPlanesSociales(){
         String params = "";
         cbPlanesSociales.loadJCombo(LibSQL.exFunction("accionsocial.getallplanessociales", params));
         loadComboPrestaciones();
     }
    
    private void loadComboPrestaciones() {
        String params = "" + cbPlanesSociales.getSelectedValue();
        cbPrestaciones.loadJCombo(LibSQL.exFunction("accionsocial.getallprestacionesbyfilter", params));
        loadPrestacion();
    }
    
    private void loadPrestacion() {
        prestacion.setIdPrestacion(Integer.parseInt(cbPrestaciones.getSelectedValue().toString()));
        prestacion.retrieveData();
        tfStock.setValue(prestacion.getCantDisponible());
        tfCantidadARecibir.setValue(0.0);
        if (cbPrestaciones.getSelectedIndex() != -1) {
            btnRecepcionarRecurso.setEnabled(true);
            tfCantidadARecibir.setEditable(true);
        } else {
            btnRecepcionarRecurso.setEnabled(false);
            tfCantidadARecibir.setEditable(false);
        }
    }

    public void load(){
	loadData();
    }

    private void loadData(){
	loadComboPlanesSociales();
        loadComboPrestaciones();
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public boolean saveData() {
        boolean returns = false;
	if (tfCantidadARecibir.getDouble() > 0) {
            if (Advisor.question("Aviso", "¿Está seguro de registrar el ingreso de \n" + tfCantidadARecibir.getDouble() + " unidades de la prestación \n\""+ cbPrestaciones.getSelectedItem() + "\"?")) { 
                if (prestacion.actualizarStock(tfCantidadARecibir.getDouble())) {
                    Advisor.messageBox("Se actualizó el Stock", "Aviso");
                    loadPrestacion();
                    returns = true;
                } else {
                    Advisor.messageBox("Ocurrió un error al actualizar los datos", "Aviso");
                }
            }
	} else {
	    Advisor.messageBox("Debe ingresar una cantidad mayor que cero ", "Aviso");
	}
        return returns;
    }
    
    private void btnRecepcionarRecurso_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    public void setParentList(BasicPrimitivePanel _parentList) {
        parentList = _parentList;
    }
    
    public void recargarFormulario(){
        loadData();
    }
}
