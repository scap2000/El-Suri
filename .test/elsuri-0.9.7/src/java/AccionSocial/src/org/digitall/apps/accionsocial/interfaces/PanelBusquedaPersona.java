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
 * PanelBusquedaPersona.java
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.Persona;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class PanelBusquedaPersona extends BasicPanel {

    private BasicPanel jpAyuda = new BasicPanel();
    private BasicPanel jpCentroBusqueda = new BasicPanel();
    
    private JSeparator jsSeparador = new JSeparator();
    
    private BasicButton btnAgregarPersona = new BasicButton();
    private FindButton btnBuscar = new FindButton();
    private BasicButton btnImprimir = new BasicButton();
    private BasicButton btnModificarPersona = new BasicButton();
    private BasicButton btnAltaGrupos = new BasicButton();
    private BasicButton btnGrupoFamiliar = new BasicButton();
    
    private TFInput tfNombrePersona = new TFInput(DataTypes.STRING,"Nombre",false);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER,"D.N.I.",false);
    
    private BasicCheckBox chkTienePlanSocial = new BasicCheckBox("Plan Social");
    private BasicTextArea taAyuda = new BasicTextArea();
    private BasicLabel lblTitulo = new BasicLabel();
    
    private int[] sizeColumnList = { 372, 90, 97};
    private Vector dataRow = new Vector();
    private GridPanel grillaPersonas = new GridPanel(1000, sizeColumnList, "Personas", dataRow){
         public void finishLoading() {
             coordinador.permitirAvance(false);
             habilitarBotones(false);
             coordinador.clearBeneficiario();
             coordinador.clearPersona();
         }
     };
    
    private Coordinador coordinador;
    private PersonasMgmt personasMgmt;
    private boolean primeraVez = true;


    public PanelBusquedaPersona(Coordinador _coordinador) {
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
        jpAyuda.setBounds(new Rectangle(0, 0, 215, 475));
        jpAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jpAyuda.setLayout(null);
        jpAyuda.setSize(new Dimension(215, 475));
        lblTitulo.setText("Selección de Personas");
        lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
        lblTitulo.setBackground(Color.black);
        lblTitulo.setOpaque(true);
        lblTitulo.setFont(new Font("Dialog", 1, 11));
        lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
        taAyuda.setSize(new Dimension(205, 440));
        jpCentroBusqueda.setBounds(new Rectangle(220, 0, 620, 75));
        jpCentroBusqueda.setLayout(null);
        tfNombrePersona.setBounds(new Rectangle(15, 20, 290, 35));
        tfDni.setBounds(new Rectangle(315, 20, 125, 35));
        grillaPersonas.setBounds(new Rectangle(220, 80, 620, 345));
        btnGrupoFamiliar.setText("Grupo Familiar");
        btnGrupoFamiliar.setBounds(new Rectangle(670, 445, 110, 25));
        btnGrupoFamiliar.setVisible(false);
        btnModificarPersona.setText("Modificar Persona");
        btnModificarPersona.setToolTipText("Modificar Persona");
        btnModificarPersona.setBounds(new Rectangle(340, 445, 125, 25));
        btnModificarPersona.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnModificarPersona_actionPerformed(e);
                }
            });
        
        btnAltaGrupos.setText("Alta en Grupos");
        btnAltaGrupos.setBounds(new Rectangle(470, 445, 115, 25));
        btnAltaGrupos.setToolTipText("Alta en Grupos");
        btnImprimir.setText("Reportes");
        btnImprimir.setBounds(new Rectangle(585, 445, 80, 25));
        btnImprimir.setToolTipText("Imprimir Reportes");
        btnAltaGrupos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                    if (btnAltaGrupos.isEnabled()) {
                        clickAltaGrupos(e);    
                    }
                }
            }
        });
        
        btnImprimir.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                    clickImprimir(e);    
                }
            }
        });
        grillaPersonas.getTable().setSelectionForeground(new Color(255, 247, 214));
        btnBuscar.setBounds(new Rectangle(550, 30, 30, 25));
        btnBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBuscar_actionPerformed(e);
                }
            }
        );
        btnAgregarPersona.setText("Nueva Persona");
        btnAgregarPersona.setBounds(new Rectangle(220, 445, 110, 25));
        btnAgregarPersona.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAgregarPersona_actionPerformed(e);
                }
            }
        );
        btnAgregarPersona.setToolTipText("Agregar Persona");
        jsSeparador.setBounds(new Rectangle(220, 435, 615, 2));
        chkTienePlanSocial.setBounds(new Rectangle(450, 35, 95, 20));
        chkTienePlanSocial.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    chkTienePlanSocial_itemStateChanged(e);
                }
            });
        jpAyuda.add(taAyuda, null);
        jpAyuda.add(lblTitulo, null);
        jpCentroBusqueda.add(chkTienePlanSocial, null);
        jpCentroBusqueda.add(btnBuscar, null);
        jpCentroBusqueda.add(tfDni, null);
        jpCentroBusqueda.add(tfNombrePersona, null);
        this.add(btnGrupoFamiliar, null);
        this.add(btnAltaGrupos, null);
        this.add(btnModificarPersona, null);
        this.add(btnImprimir, null);
        this.add(jsSeparador, null);
        this.add(grillaPersonas, null);
        this.add(jpCentroBusqueda, null);
        this.add(jpAyuda, null);
        this.add(btnAgregarPersona, null);
                jpCentroBusqueda.setBorder(BorderPanel.getBorderPanel("Buscar Personas"));
        grillaPersonas.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   //Cargar persona
                                                   setPersona();
                                                   coordinador.setCabecera();
                                                   coordinador.permitirAvance(true);
                                                   habilitarBotones(true);
                                               } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                               //Cargar persona y decirle al coordinador que pase al siguiente paso
                                               setPersona();
                                               coordinador.setCabecera();
                                               coordinador.siguiente();
                                               }
                                               
                                           }

                                       }
        );
        grillaPersonas.getTable().addKeyListener(new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    seleccionarPersona();
                }

                public void keyPressed(KeyEvent e) {
                    seleccionarPersona();
                }

                public void keyReleased(KeyEvent e) {
                    seleccionarPersona();
                }
                
                public void seleccionarPersona() {
                    setPersona();
                    coordinador.setCabecera();
                }
            }
        );
        taAyuda.setEditable(false);
        setHeaderList();
        taAyuda.setText(  "* Para buscar una persona ingrese una pista de búsqueda en el campo \"Nombre\" o en el campo \"D.N.I.\" y presione la tecla ENTER o haga click en el botón \"Buscar\".\n" +
                        "\n* Para buscar personas con Planes Sociales asignados tilde la opción \"Plan Social\".\n" + 
                        "\n* Seleccione la persona buscada en la grilla Personas y presione el botón siguiente o haga doble click en la fila seleccionada de la grilla.\n" + 
                        "\n* Para agregar una persona, haga click en el botón \"Nueva Persona\".\n" +
                        "\n* Para modificar una persona, seleccione una fila de la grilla y haga click en el botón \"Modificar Persona\".\n" +
                        "\n* Para dar de alta a una persona en un grupo determinado haga click en el botón \"Alta en Grupos \" y seleccione la opción deseada.\n" +
                        "\n* Para imprimir los distintos reportes haga clic en el botón \"Reportes\" y seleccione la opción deseada");
        tfNombrePersona.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           refresh();
                                                       }
                                                   }

                                               }
        );
        tfDni.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           refresh();
                                                       }
                                                   }

                                               }
        );
        habilitarBotones(false);
    }
    
    public void refresh() {
        boolean soloConPlanSocial = false;
        if (chkTienePlanSocial.isSelected()) {
            soloConPlanSocial = true;    
        } 
        String params = "'" + tfNombrePersona.getString() + "','" + tfDni.getInteger() + "'," + soloConPlanSocial; 
        grillaPersonas.refresh(params);
    }
    
    private void setHeaderList() {
        Vector headerList = new Vector();
        headerList.addElement("*");//idPersona
        headerList.addElement("Apellido y Nombres");
        headerList.addElement("*");//Tipo de documento
        headerList.addElement("DNI");
        headerList.addElement("Plan Social");
        
        grillaPersonas.setParams("accionsocial.getAllPersonas", "'','',false", headerList);
    }
    
    public void setPersona() {
        int idPersona = Integer.parseInt(dataRow.elementAt(0).toString());
        coordinador.getPersona().cargarse(idPersona);
        if (coordinador.getPersona().tienePlanesSociales()) {
            coordinador.getBeneficiario().cargarse(coordinador.getPersona());
        } else {
            coordinador.clearBeneficiario();
        }
    }
    
    /** Funcion: Búsqueda de personas según filtros aplicados **/
    private void btnBuscar_actionPerformed(ActionEvent e) {
        refresh();
    }
    
    private void btnAgregarPersona_actionPerformed(ActionEvent e) {
        initAddPanel();
    }
    
    private void initAddPanel() {
        ExtendedInternalFrame personasMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar Persona");
        personasMgmt = new PersonasMgmt(coordinador);
        personasMgmtContainer.setCentralPanel(personasMgmt);
        personasMgmtContainer.show();
        personasMgmt.setPersona(new Persona());
        
    }

    private void habilitarBotones(boolean _enabled) {
        btnModificarPersona.setEnabled(_enabled);
        btnAltaGrupos.setEnabled(_enabled);
    }

    public void iniciarPanel() {
        if (!primeraVez) {
            refresh();
        } else {
            primeraVez = false;
        }
        coordinador.permitirAvance(false);
        tfNombrePersona.getTextField().requestFocus();
    }

    private void chkTienePlanSocial_itemStateChanged(ItemEvent e) {
        refresh();
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    public boolean isPrimeraVez() {
        return primeraVez;
    }
    
    /** Funcionalidad que permite desplegar una lista de opciones de Impresión de Reportes */
    private void clickImprimir(MouseEvent e) {
        PopupImprimirReportes popupImprimirReportes = new  PopupImprimirReportes(coordinador);
        int x = (int)e.getPoint().getX();
        int y = (int)e.getPoint().getY() - popupImprimirReportes.getAlto();
        popupImprimirReportes.show(btnImprimir,x,y);
    }
    
    /** Funcionalidad que permite dar de alta a una persona seleccionada en un grupo específico */
    private void clickAltaGrupos(MouseEvent e) {
        PopupAltaGrupos popupAltaGrupos = new  PopupAltaGrupos(coordinador);
        int x = (int)e.getPoint().getX();
        int y = (int)e.getPoint().getY() - popupAltaGrupos.getAlto();
        popupAltaGrupos.show(btnAltaGrupos,x,y);
    }

    private void btnModificarPersona_actionPerformed(ActionEvent e) {
        mostrarPersonasMgmt(coordinador.getPersona());
    }
    
    /** Funcionalidad que permite modificar los datos de una persona seleccionada 
     *  y obtener la coordenada geográfica de la ubicación de la misma            */
    private void mostrarPersonasMgmt(Persona _persona) {
        ExtendedInternalFrame personasMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar Persona");
        personasMgmt = new PersonasMgmt(coordinador);
        personasMgmtContainer.setCentralPanel(personasMgmt);
        personasMgmtContainer.show();
        personasMgmt.setPersona(_persona);
    }
}
