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
 * CashierAnticiposDeHaberesEmitidosMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.digitall.common.personalfiles.classes.AnticipoDeHaberes;
import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CashierAnticiposDeHaberesEmitidosMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpBuscar = new BasicPanel();
    private BasicPanel jpFechaPago = new BasicPanel();

    private int[] anticiposColumnSize = {  109, 108, 200, 62, 82 };
    private Vector anticiposDataRow = new Vector();
    private GridPanel anticiposListPanel = new GridPanel(1000, anticiposColumnSize, "Anticipos de Haberes emitidos", anticiposDataRow){
        public void finishLoading() {
            habilitarBotones();   
        }
    };

    private TFInput tfFechaInicio = new TFInput(DataTypes.SIMPLEDATE,"Fecha Inicio",false);
    private TFInput tfFechaFin = new TFInput(DataTypes.SIMPLEDATE,"Fecha Fin",false);
    private TFInput tfEmpleado = new TFInput(DataTypes.STRING,"Empleado/a",false);
    private TFInput tfLegajo = new TFInput(DataTypes.INTEGER,"Legajo",false);
    private TFInput tfFechaPago = new TFInput(DataTypes.SIMPLEDATE,"Fecha de Pago",true);
    
    private FindButton btnBuscar = new FindButton();
    private AcceptButton btnConfirmarAnticiposDeHaberes = new AcceptButton();
    private DeleteButton btnBorrarAnticiposDeHaberes = new DeleteButton();
    
    private BasicLabel lblInformacion = new BasicLabel();
    
    private Legajo legajo;
    private CashierMain parentMain;
    private AnticipoDeHaberes anticipoDeHaberes;

    public CashierAnticiposDeHaberesEmitidosMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(659, 535));
	this.setPreferredSize(new Dimension(614, 415));
	this.setTitle("Buscar Anticipo de Haberes");
	content.setLayout(null);
	content.setSize(new Dimension(664, 499));
        jpBuscar.setLayout(null);
        jpFechaPago.setLayout(null);
        jpBuscar.add(tfFechaInicio, null);
        jpBuscar.add(tfFechaFin, null);
        jpBuscar.add(tfEmpleado, null);
        jpBuscar.add(tfLegajo, null);
        jpBuscar.add(btnBuscar, null);
        anticiposListPanel.setBounds(new Rectangle(5, 75, 655, 340));
        tfFechaInicio.setBounds(new Rectangle(10, 25, 85, 35));
	tfFechaInicio.setPreferredSize(new Dimension(110, 35));
        tfFechaFin.setBounds(new Rectangle(110, 25, 85, 35));
	tfFechaFin.setPreferredSize(new Dimension(110, 35));
        tfEmpleado.setBounds(new Rectangle(215, 25, 300, 35));
	tfEmpleado.setFont(new Font("Dialog", 0, 11));
	tfEmpleado.setPreferredSize(new Dimension(104, 35));
        tfLegajo.setBounds(new Rectangle(535, 25, 70, 35));
        jpBuscar.setBounds(new Rectangle(0, 0, 660, 70));
	jpBuscar.setBorder(BorderPanel.getBorderPanel("Buscar"));
        jpFechaPago.setBorder(BorderPanel.getBorderPanel("Asignar Fecha de Pago"));
        btnBorrarAnticiposDeHaberes.setToolTipText("Borrar Anticipos de Haberes seleccionados");
        btnBorrarAnticiposDeHaberes.setBounds(new Rectangle(455, 95, 40, 25));
        btnBorrarAnticiposDeHaberes.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnBorrarAnticiposDeHaberes_actionPerformed(e);
                }

            }
        );
        btnConfirmarAnticiposDeHaberes.setToolTipText("Confirmar Anticipos de Haberes");
        btnConfirmarAnticiposDeHaberes.setBounds(new Rectangle(625, 30, 25,25));
        btnConfirmarAnticiposDeHaberes.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnConfirmarAnticiposDeHaberes_actionPerformed(e);
                }

            }
        );
        addButton(btnBorrarAnticiposDeHaberes);
        //addButton(btnConfirmarAnticiposDeHaberes);
        tfEmpleado.getTextField().addKeyListener(new KeyAdapter() {

                                           public void keyTyped(KeyEvent e) {
                                               char caracter = e.getKeyChar();
                                               if ((caracter == KeyEvent.VK_ENTER)) {
                                                   refreshAnticipos();
                                               }
                                           }

                                       }
        );
        tfLegajo.getTextField().setHorizontalAlignment(tfLegajo.getTextField().CENTER);
        tfLegajo.getTextField().addKeyListener(new KeyAdapter() {

                                           public void keyTyped(KeyEvent e) {
                                               char caracter = e.getKeyChar();
                                               if ((caracter == KeyEvent.VK_ENTER)) {
                                                   refreshAnticipos();
                                               }
                                           }

                                       }
        );
        lblInformacion.setBounds(new Rectangle(10, 30, 485, 20));
        lblInformacion.setText("Recuerde que la fecha de Pago no puede ser menor que la fecha de Emisión");
        lblInformacion.setBackground(new Color(255, 132, 0));
        lblInformacion.setOpaque(true);
        lblInformacion.setForeground(Color.black);
        lblInformacion.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblInformacion.setFont(new Font("Lucida Sans", 1, 12));
        lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
        jpFechaPago.add(lblInformacion, null);
        jpFechaPago.add(tfFechaPago, null);
        jpFechaPago.add(btnConfirmarAnticiposDeHaberes, null);
        content.add(jpFechaPago, null);
        content.add(jpBuscar, null);
        content.add(anticiposListPanel, null);
        this.setContent(content);
	anticiposListPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     habilitarBotones();
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							  //refreshDetallePagos();
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     }
						 }

					     } 
	);
	btnBorrarAnticiposDeHaberes.setEnabled(false);
        tfFechaPago.setBounds(new Rectangle(505, 15, 110, 35));
        jpFechaPago.setBounds(new Rectangle(5, 415, 655, 65));
        btnBuscar.setBounds(new Rectangle(620, 35, 30, 25));
        btnBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBuscar_actionPerformed(e);
                }
            });
        btnConfirmarAnticiposDeHaberes.setEnabled(false);
        tfFechaPago.setEditable(false);
        setAnticiposHeader();
    }

    public void setParentMain(CashierMain parentMain) {
	this.parentMain = parentMain;
    }

    private void setAnticiposHeader() {
        Vector anticiposHeader = new Vector();
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("Fecha Emisión"); 
        anticiposHeader.addElement("Número");
        anticiposHeader.addElement("Empleado/a");
        anticiposHeader.addElement("Legajo");
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("($) Monto"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        
        String params = tfFechaInicio.getDateForSQL() +","+ tfFechaFin.getDateForSQL() +",'"+ tfEmpleado.getString() +"',"+ tfLegajo.getInteger() ;
        anticiposListPanel.setParams("sueldos.getallAnticiposDeHaberesEmitidos", params, anticiposHeader);
    }

    private void refreshAnticipos() {
        String params = tfFechaInicio.getDateForSQL() +","+ tfFechaFin.getDateForSQL() +",'"+ tfEmpleado.getString() +"',"+ tfLegajo.getInteger() ;
        anticiposListPanel.refresh(params);
    }

    private void habilitarBotones(){
        if (anticiposListPanel.getSelectedsID().size() > 0) {
            btnBorrarAnticiposDeHaberes.setEnabled(true);
            btnConfirmarAnticiposDeHaberes.setEnabled(true);
            tfFechaPago.setEditable(true);
        } else {
            btnBorrarAnticiposDeHaberes.setEnabled(false);
            btnConfirmarAnticiposDeHaberes.setEnabled(false);
            tfFechaPago.setEditable(false);
        }
    }
        
    /** MÉTODO PARA BORRAR ANTICIPOS DE HABERES **/
    private void btnBorrarAnticiposDeHaberes_actionPerformed(ActionEvent e) {
        Vector vector = anticiposListPanel.getSelectedsID();
        if (vector.size() > 0) {
            if (Advisor.question("Borrar Anticipos de Haberes", "¿Está seguro de borrar los Anticipos de Haberes seleccionados?")) {
                if (LibSQL.getBoolean("sueldos.deleteAnticiposDeHaberes", "'" + anticiposListPanel.getSelectedsID().toString().replace("[", "{").replace("]", "}") + "',"+ 
                                                                                 vector.size())) {
                    Advisor.messageBox("Se borraron con éxito los Anticipos de Haberes seleccionados","Éxito");
                    refreshAnticipos();
                } else {
                    Advisor.messageBox("Se produjo un error al grabar los datos","Error");
                }
            }
        } else {
            Advisor.messageBox("Debe seleccionar al menos un anticipo de haber", "Aviso");
        }
    }
    
    /** MÉTODO PARA DAR COMO PAGOS LOS ANTICIPOS DE HABERES EMITIDOS  **/
    private void btnConfirmarAnticiposDeHaberes_actionPerformed(ActionEvent e) {
        Vector vector = anticiposListPanel.getSelectedsID();
        if (control()) {
            if (Advisor.question("Pagar Anticipos de Haberes", "¿Está seguro marcar como pagados los Anticipos de Haberes seleccionados?")) {

                if (LibSQL.getBoolean("sueldos.pagarAnticiposDeHaberes", "'" + anticiposListPanel.getSelectedsID().toString().replace("[","{").replace("]","}") +
                                      "'," + vector.size() + "," + tfFechaPago.getDateForSQL())) {
                    Advisor.messageBox("Se registrarón como pagos los Anticipos de Haberes seleccionados","Éxito");
                    refreshAnticipos();
                } else {
                    Advisor.messageBox("Se produjo un error al grabar los datos", "Error");
                }
            }
        }
    }

    private boolean control() {
        boolean _returns = false;
        if (tfFechaPago.getDate().equals("")) {
            Advisor.messageBox("La fecha de Pago no puede ser vacía","Error");
        } else if (Proced.compareDates(Proced.setFormatDate(tfFechaPago.getDate(), false), Environment.currentDate) == 2) {
            Advisor.messageBox("La fecha de Pago no debe ser mayor a la fecha Actual","Error");
        } else {
            _returns = true;
        }
        return _returns;
    }
    
    private void cargarAnticipoDeHaberes() {
        anticipoDeHaberes = new AnticipoDeHaberes();
        anticipoDeHaberes.setIdPersona(legajo.getidPerson());
        anticipoDeHaberes.setIdLegajo(legajo.getidLegajo());
        anticipoDeHaberes.setLegajo(legajo.getNumero()); 
        anticipoDeHaberes.setApellido(legajo.getPerson().getLastName());
        anticipoDeHaberes.setNombres(legajo.getPerson().getFirstName());
        anticipoDeHaberes.setFechaEmision(Proced.setFormatDate(tfFechaInicio.getDate(),false));
        anticipoDeHaberes.setFechaDescuento(Proced.setFormatDate(tfFechaFin.getDate(),false));
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        refreshAnticipos();
    }
}
