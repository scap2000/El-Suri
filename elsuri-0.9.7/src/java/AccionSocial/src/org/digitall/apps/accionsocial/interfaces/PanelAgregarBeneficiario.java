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
 * PanelAgregarBeneficiario.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorAccionSocial;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class PanelAgregarBeneficiario extends BasicPanel {

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelSeleccionPrestacion = new BasicPanel();
    
    private BasicTextArea taAyuda = new BasicTextArea();
    
    private BasicLabel lblTitulo = new BasicLabel();
    
    private TFInput tfFechaAlta = new TFInput(DataTypes.SIMPLEDATE,"Fecha de Alta",false);

    private CBInput cbCentroSalud = new CBInput(0, "Centro de Salud", true);
    private CBInput cbPlanesSociales = new CBInput(0, "Plan Social", false);
    private CBInput cbPrestaciones = new CBInput(0, "Prestación", false);
    private CBInput cbSector = new CBInput(0, "Sector", true);
    
    private AssignButton btnAsignar = new AssignButton(true);
    
    private int[] sizeColumnList = { 112, 213, 248};
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel grillaPersonas = new GridPanel(30, sizeColumnList, "Prestaciones Asignadas", dataRow){
         public void finishLoading() {
         }
     };
    private JSeparator jsSeparador = new JSeparator();
    private Coordinador coordinador;
    private BasicButton btnDarBaja = new BasicButton();

    public PanelAgregarBeneficiario(Coordinador _coordinador) {
	coordinador = _coordinador;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout( null );
        this.setSize(new Dimension(845, 475));
        this.setPreferredSize(new Dimension(845, 475));
        panelAyuda.setBounds(new Rectangle(0, 0, 215, 475));
        panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelAyuda.setLayout(null);
        panelAyuda.setSize(new Dimension(215, 475));
        lblTitulo.setText("Selección de Prestación");
        lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
        lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        tfFechaAlta.setBounds(new Rectangle(10, 20, 105, 35));
        cbCentroSalud.setBounds(new Rectangle(125, 20, 110, 35));
        cbPlanesSociales.setBounds(new Rectangle(365, 20, 235, 35));
        cbPrestaciones.setBounds(new Rectangle(10, 65, 435, 35));
        btnAsignar.setBounds(new Rectangle(570, 75, 40, 25));
        btnAsignar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAsignar_actionPerformed(e);
                }
            });
        btnAsignar.setToolTipText("Asignar prestación");
        taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
        taAyuda.setSize(new Dimension(205, 440));
        grillaPersonas.setBounds(new Rectangle(220, 130, 615, 295));
        grillaPersonas.getTable().setSelectionForeground(new Color(255, 247, 214));
        jsSeparador.setBounds(new Rectangle(220, 435, 615, 2));
        btnDarBaja.setText("Dar de Baja");
        btnDarBaja.setBounds(new Rectangle(220, 445, 90, 20));
        btnDarBaja.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnDarBaja_actionPerformed(e);
                }
            });
        cbSector.setBounds(new Rectangle(245, 20, 110, 35));
        panelSeleccionPrestacion.setBounds(new Rectangle(220, 5, 615, 120));
        panelSeleccionPrestacion.setBorder(BorderPanel.getBorderPanel("Datos Asignar Prestación"));
        panelSeleccionPrestacion.setLayout(null);
        panelAyuda.add(taAyuda, null);
        panelAyuda.add(lblTitulo, null);
        panelSeleccionPrestacion.add(cbSector, null);
        panelSeleccionPrestacion.add(btnAsignar, null);
        panelSeleccionPrestacion.add(cbPrestaciones, null);
        panelSeleccionPrestacion.add(cbPlanesSociales, null);
        panelSeleccionPrestacion.add(cbCentroSalud, null);
        panelSeleccionPrestacion.add(tfFechaAlta, null);
        this.add(btnDarBaja, null);
        this.add(panelSeleccionPrestacion, null);
        this.add(jsSeparador, null);
        this.add(grillaPersonas, null);
        this.add(panelAyuda, null);
        taAyuda.setEditable(false);
        cbCentroSalud.autoSize();
        cbPlanesSociales.autoSize();
        cbPrestaciones.autoSize();
        setHeaderList();
        tfFechaAlta.setValue(Proced.setFormatDate(Environment.currentDate, true));
        cbPlanesSociales.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPlanesSociales.getSelectedIndex() > -1) {
                                                    loadPlanSocial();
                                                    loadComboPrestaciones();
                                                }
                                            }
                                        }

                                    }
        );
        cbPrestaciones.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            //if (cbPrestaciones.getSelectedIndex() > -1) {
                                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                                        loadPrestacion();
                                                        //btnAsignar.setEnabled(true);
                                                }   
                                            /*} else {
                                                //btnAsignar.setEnabled(false);
                                            }*/
                                        }

                                    }
        );
        taAyuda.setText(  "" +
                        "  * Seleccione el Centro de Salud del combo \"Centro de Salud\".\n " +
                        "\n* Seleccione el Sector del combo \"Sector\".\n " +
                        "\n* Seleccione el Plan Social del combo \"Plan Social\", del cual quiere asignar una prestación.\n " +
                        "\n* Selecione la Prestación del combo \"Prestación\", que desea asignar.\n"+
                        "\n* Para asignar una prestación al beneficiario actual haga click en el botón \"Asignar Prestación\".\n" 
                        );
        btnDarBaja.setEnabled(false);
    }
    
    public void refresh() {
        String params = "" + coordinador.getPersona().getIdPersona() + "";
        grillaPersonas.refresh(params);
    }
    
    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.removeAllElements();
        headerList.addElement("*");
        headerList.addElement("Fecha de Alta");
        headerList.addElement("*");
        headerList.addElement("Plan Social");
        headerList.addElement("Prestación");
        
        grillaPersonas.getTable().addMouseListener(new MouseListener() {

                     public void mouseClicked(MouseEvent e) {
                         if (e.getButton() == e.BUTTON1) {
                         }
                     }

                    public void mousePressed(MouseEvent mouseEvent) {
                    }

                    public void mouseReleased(MouseEvent mouseEvent) {
                    }

                    public void mouseEntered(MouseEvent mouseEvent) {
                    }

                    public void mouseExited(MouseEvent mouseEvent) {
                    }

                });
        grillaPersonas.getTable().addKeyListener(new KeyListener() {

                        public void keyReleased(KeyEvent e) {
                            e.consume();
                        }
                        
                        public void keyTyped(KeyEvent e) {
                            e.consume();
                        }
                        
                        public void keyPressed(KeyEvent e) {
                            e.consume();
                        }

                });
        grillaPersonas.setParams("accionsocial.getallasignaciones", "0", headerList);
    }
    
    public void iniciarPanel() {
        coordinador.permitirAvance(false);
        loadCombos();
        refresh();
        limpiarCampos();
    }
    
    private void loadCombos() {
        loadComboCentroSalud();
        loadComboSector();
        loadComboPlanSocial();
        loadPlanSocial();
        loadComboPrestaciones();
        loadPrestacion();
    }
    
    private void loadComboCentroSalud() {
        cbCentroSalud.removeAllItems();
        String params = "";
        cbCentroSalud.loadJCombo(LibSQL.exFunction("accionsocial.getallcentrossalud", params));
    }
    
    private void loadComboSector() {
        cbSector.removeAllItems();
        String params = "";
        cbSector.loadJCombo(LibSQL.exFunction("accionsocial.getallsectores", params));
    }
    
    private void loadComboPlanSocial() {
        cbPlanesSociales.removeAllItems();
        String params = "" + coordinador.getPersona().getIdPersona();
        cbPlanesSociales.loadJCombo(LibSQL.exFunction("accionsocial.getallposiblesplanessocialesporpersona", params));
        loadComboPrestaciones();
    }
    
    private void loadComboPrestaciones() {
        cbPrestaciones.removeAllItems();
        if (cbPlanesSociales.getCombo().getItemCount() > 0) {
            String params = "" + coordinador.getPersona().getIdPersona() + "," + coordinador.getPlanSocial().getIdPlanSocial();
            cbPrestaciones.loadJCombo(LibSQL.exFunction("accionsocial.getallprestacionesfaltantes", params));
            if (cbPrestaciones.getCombo().getItemCount() > 0) {
                btnAsignar.setEnabled(true);
            } else {
                btnAsignar.setEnabled(false);
            }
        } else {
            btnAsignar.setEnabled(false);
        }
    }
    
    private void loadPlanSocial() {
        if ( cbPlanesSociales.getCombo().getItemCount() > 0 ) {
            coordinador.getPlanSocial().setIdPlanSocial(Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()));
            coordinador.getPlanSocial().retrieveData();
        }
    }
    
    private void loadPrestacion() {
        if ( cbPrestaciones.getCombo().getItemCount() > 0 ) {
            coordinador.getPrestacion().setIdPrestacion(Integer.parseInt(cbPrestaciones.getSelectedValue().toString()));
            coordinador.getPrestacion().retrieveData();    
        }
    }

    private void btnAsignar_actionPerformed(ActionEvent e) {
        cargar();  
    }
    
    private void cargar() {
        if (control()) {
            if (Advisor.question("Aviso", "¿Desea registrar la asignación de la prestación \n\"" + cbPrestaciones.getSelectedItem() + "\"?")) {
                cargarBeneficiario();
                if( coordinador.getBeneficiario().saveData() > 0) {
                    loadCombos();
                    refresh();
                } else {
                    Advisor.messageBox("Ocurrió un error al guardar los datos", "Error");
                }
            } 
            
        } else {
            ErrorAccionSocial.showMessage();
        }
    }
    
    private boolean control() {
        boolean resultado = true;
        if (tfFechaAlta.getDate().equals("")) {
            ErrorAccionSocial.setError(ErrorAccionSocial.FECHA_ALTA);
            resultado = false;
        } else if (cbPrestaciones.getSelectedIndex() == -1) {
            ErrorAccionSocial.setError(ErrorAccionSocial.PRESTACION_SELECCIONADA);
            resultado = false;
        } else if (cbCentroSalud.getSelectedIndex() == -1) {
            ErrorAccionSocial.setError(ErrorAccionSocial.CENTRO_SALUD_SELECCIONADO);
            resultado = false;
        } else if (cbSector.getSelectedIndex() == -1) {
            ErrorAccionSocial.setError(ErrorAccionSocial.SECTOR_SELECCIONADO);
            resultado = false;
        }
        return resultado;
    }
    
    private void limpiarCampos() {
        coordinador.permitirAvance(false);
        tfFechaAlta.setValue(Proced.setFormatDate(Environment.currentDate, true));
    }
    
    private void cargarBeneficiario() {
        loadPrestacion();
        coordinador.clearBeneficiario();
        coordinador.getBeneficiario().setIdBeneficiario(-1);
        coordinador.getBeneficiario().setIdPersona(coordinador.getPersona().getIdPersona());
        coordinador.getBeneficiario().setIdPlanSocial(coordinador.getPlanSocial().getIdPlanSocial());
        coordinador.getBeneficiario().setFechaAlta(tfFechaAlta.getDate());
        coordinador.getBeneficiario().setFechaNacimiento(coordinador.getPersona().getFechaNacimiento());
        coordinador.getBeneficiario().setEdad(0);
        coordinador.getBeneficiario().setIdTutor(coordinador.getTutor().getIdPersona());
        coordinador.getBeneficiario().setDomicilio(coordinador.getPersona().getDomicilio());
        coordinador.getBeneficiario().setTutor(coordinador.getTutor().getApellidos() + " " +coordinador.getTutor().getNombres());
        coordinador.getBeneficiario().setNumeroDocumento(coordinador.getPersona().getNroDocumento());
        coordinador.getBeneficiario().setBeneficiario(coordinador.getPersona().getApellidos() + " " + coordinador.getPersona().getNombres());
        coordinador.getBeneficiario().setNumeroDocumentoTutor(coordinador.getTutor().getNroDocumento());
        coordinador.getBeneficiario().setIdPrestacion(coordinador.getPrestacion().getIdPrestacion());
        coordinador.getBeneficiario().setCentroSalud(cbCentroSalud.getSelectedItem().toString());
        coordinador.getBeneficiario().setIdSector(Integer.parseInt(cbSector.getSelectedItem().toString()));
    }

    private void btnDarBaja_actionPerformed(ActionEvent e) {
        mostrarPanelBaja();
    }

    private void mostrarPanelBaja() {
        ExtendedInternalFrame personasMgmtContainer = new ExtendedInternalFrame("Dar de Baja");
        PanelBajaBeneficiario panelBajaBeneficio = new PanelBajaBeneficiario(coordinador);
        personasMgmtContainer.setCentralPanel(panelBajaBeneficio);
        personasMgmtContainer.show();
    }
}
