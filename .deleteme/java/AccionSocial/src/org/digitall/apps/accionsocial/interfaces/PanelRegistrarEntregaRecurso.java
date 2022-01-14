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
 * PanelRegistrarEntregaRecurso.java
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.math.BigDecimal;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorAccionSocial;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class PanelRegistrarEntregaRecurso extends BasicPanel {

    private Coordinador coordinador;
    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelSeleccionPrestacion = new BasicPanel();

    private AssignButton btnAddRecurso = new AssignButton(true);
    private BasicButton btnAgregarBeneficiario = new BasicButton();
    
    private CBInput cbPlanesSociales = new CBInput(0, "Plan Social", false);
    private CBInput cbDespachante = new CBInput(0, "Despachante", true);
    private CBInput cbPrestaciones = new CBInput(0, "Prestación", false);
    
    private TFInput tfCantidad = new TFInput(DataTypes.DOUBLE, "Cantidad", false);
    private CBInput cbMeses = new CBInput(CachedCombo.MONTHS, "Mes de Entr.", false);
    private CBInput cbAnios = new CBInput(0, "Año de Entr.", false);
    private TFInput tfFechaEntrega = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Entrega", false);
    private TFInput tfStock =  new TFInput(DataTypes.DOUBLE, "Stock", false);
    
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblFormaEntrega = new BasicLabel("Forma de entrega");
    
    private BasicTextArea taAyuda = new BasicTextArea();
    
    private BasicRadioButton rbtnEnDomicilio = new BasicRadioButton();
    private BasicRadioButton rbtnEnEstablecimiento = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    
    private JSeparator separador1 = new JSeparator();
    
    private int[] sizeColumnList = { 117,86,137,162,70,105,80 };
    private Vector dataRow = new Vector();
    private Vector headerList = new Vector();
    private GridPanel grillaEntregasRealizadas = new GridPanel(50000, sizeColumnList, "Entregas Realizadas", dataRow){
    
	public void finishLoading() {
	    
	}
    };

    private String despachanteSeleccionado = "";
    private String beneficiariosConProblema01 = "";
    private String beneficiariosConProblema02 = "";
    
    public PanelRegistrarEntregaRecurso(Coordinador _coordinador) {
	coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(845, 475));
	panelAyuda.setBounds(new Rectangle(0, 0, 215, 475));
	panelAyuda.setLayout(null);
	panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelAyuda.setSize(new Dimension(215, 475));
	lblTitulo.setText("Selecc. y Entr. de Prestaciones");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
	lblTitulo.setBackground(Color.black);
	lblTitulo.setOpaque(true);
	lblTitulo.setFont(new Font("Dialog", 1, 11));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelSeleccionPrestacion.setBounds(new Rectangle(5, 5, 615, 160));
	panelSeleccionPrestacion.setBorder(BorderPanel.getBorderPanel("Datos de Entrega de las Prestaciones"));
	panelSeleccionPrestacion.setLayout(null);
	cbPrestaciones.setBounds(new Rectangle(10, 65, 255, 35));
	cbPrestaciones.setSize(new Dimension(255, 35));
	cbPrestaciones.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbPrestaciones.getSelectedIndex() > -1) {
						    loadPrestacion();
                                                    loadData();
						}
					    }
					}

				    }
	);
	rbtnEnDomicilio.setText("A Domicilio");
	rbtnEnDomicilio.setBounds(new Rectangle(275, 125, 110, 20));
	rbtnEnEstablecimiento.setText("En Establecimiento");
	rbtnEnEstablecimiento.setBounds(new Rectangle(125, 125, 150, 20));
        grillaEntregasRealizadas.setBounds(new Rectangle(5, 170, 615, 255));
        separador1.setBounds(new Rectangle(5, 435, 615, 5));
        cbPlanesSociales.setBounds(new Rectangle(345, 20, 255, 35));
        cbPlanesSociales.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbPlanesSociales.getSelectedIndex() > -1) {
                                                    loadPlanSocial();
						}
					    }
					}

				    }
	);
        
        cbMeses.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbMeses.getSelectedIndex() > -1) {
                                                    //loadPrestacion();
                                                    //loadData();
                                                }
                                            }
                                        }

                                    }
        );
        
        cbAnios.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbAnios.getSelectedIndex() > -1) {
                                                    loadPrestacion();
                                                    loadData();
                                                }
                                            }
                                        }

                                    }
        );
        tfCantidad.setBounds(new Rectangle(275, 65, 60, 35));
        tfFechaEntrega.setBounds(new Rectangle(225, 20, 110, 35));
        cbDespachante.setBounds(new Rectangle(405, 65, 200, 35));
        btnAddRecurso.setBounds(new Rectangle(580, 125, 30, 20));
        btnAddRecurso.setBounds(new Rectangle(580, 125, 30, 20));
        btnAddRecurso.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddRecurso_actionPerformed(e);
                }
            });
        btnAddRecurso.setToolTipText("Registrar Entrega");
        lblFormaEntrega.setBounds(new Rectangle(10, 110, 120, 15));
        panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
        panelCentral.add(btnAgregarBeneficiario, null);
        panelCentral.add(separador1, null);
        panelCentral.add(grillaEntregasRealizadas, null);
        panelSeleccionPrestacion.add(cbAnios, null);
        panelSeleccionPrestacion.add(cbMeses, null);
        panelSeleccionPrestacion.add(tfStock, null);
        panelSeleccionPrestacion.add(lblFormaEntrega, null);
        panelSeleccionPrestacion.add(btnAddRecurso, null);
        panelSeleccionPrestacion.add(cbDespachante, null);
        panelSeleccionPrestacion.add(tfFechaEntrega, null);
        panelSeleccionPrestacion.add(tfCantidad, null);
        panelSeleccionPrestacion.add(cbPlanesSociales, null);
        panelSeleccionPrestacion.add(cbPrestaciones, null);
        panelSeleccionPrestacion.add(rbtnEnEstablecimiento, null);
        panelSeleccionPrestacion.add(rbtnEnDomicilio, null);
        panelCentral.add(panelSeleccionPrestacion, null);
        this.add(panelCentral, null);
	this.add(panelAyuda, null);
	grupo.add(rbtnEnDomicilio);
	grupo.add(rbtnEnEstablecimiento);
        taAyuda.setEditable(false);
	grillaEntregasRealizadas.setSortable(false);
	grillaEntregasRealizadas.getTable().setEnabled(false);
        setHeaderList();
	grillaEntregasRealizadas.setSortable(false);
        cbAnios.setBounds(new Rectangle(135, 20, 80, 35));
        cbMeses.setBounds(new Rectangle(10, 20, 115, 35));
        btnAgregarBeneficiario.setText("Agregar Prestación");
        btnAgregarBeneficiario.setBounds(new Rectangle(15, 445, 140, 20));
        btnAgregarBeneficiario.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAgregarBeneficiario_actionPerformed(e);
                }
            });
        tfStock.setBounds(new Rectangle(345, 65, 50, 35));
        tfStock.setEditable(false);
        tfCantidad.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           cargarAsignacion();
                                                       }
                                                   }

                                               }
        );
        rbtnEnEstablecimiento.setSelected(true);
        cbPlanesSociales.autoSize();
        cbDespachante.autoSize();
        cbPrestaciones.autoSize();
        taAyuda.setText(  "" +
                        "  * Seleccione el mes y el año correspondiente al período del cual quiere asignar recursos de prestaciones.\n" +
                        "\n* Para insertar o modificar la fecha de entrega, haga doble click en el campo \"Fecha de Entrega\" y seleccione la fecha en el calendario que le aparecerá.\n " +
                        "\n* Seleccione el Plan Social del combo \"Plan Social\", del cual quiere asignar recursos de prestaciones.\n" +
                        "\n* Seleccione la Prestación del combo \"Prestación\", de la cual quiere asignar recursos.\n" + 
                        "\n* Seleccione un despachante del combo \"Despachante\".\n"+
                        "\n* Para agregar un despachante presione el boton \"Agregar\" que se encuentra al lado del combo \"Despachante\" y agregue el despachante.\n" + 
                        "\n* Seleccione la forma de entrega haciendo click en las opciones \"En Establecimiento\" o \"A Domicilio\".\n"+
                        "\n* Para agregar una entrega complete todos los datos y haga click en el botón \"Registrar Entrega\".\n" + 
                        "\n* Para agregar un Plan Social o una Prestación al beneficiario actual, haga click en el botón \"Agregar Prestación\".\n"
                        );
        cbAnios.setSelectedValue(Environment.currentYear);
        cbMeses.setSelectedID(Environment.currentMonth);
    }
    
    public void iniciarPanel() {
        loadCombos();
        loadComboAnios();
        loadData();
        tfFechaEntrega.setValue(Proced.setFormatDate(Environment.currentDate, true));
        //cbMeses.setValue(Proced.setFormatDate(Environment.currentDate, true));
        limpiarCampos();
	refresh();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.removeAllElements();
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("Período");
        headerList.addElement("Fecha Entr.");
	headerList.addElement("Plan Social");
        headerList.addElement("Prestación");
        headerList.addElement("Cantidad");
	headerList.addElement("Despachante");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("A Domicilio");
	
	grillaEntregasRealizadas.getTable().addMouseListener(new MouseListener() {

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
	grillaEntregasRealizadas.getTable().addKeyListener(new KeyListener() {

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
	grillaEntregasRealizadas.setParams("accionsocial.getallentregasbyfilter", "0,0,0", headerList);
    }
    
    public void refresh() {
        String params = "" + coordinador.getPersona().getIdPersona() + ",0,0";
        grillaEntregasRealizadas.refresh(params);
	clearFields();
    }
    
    public void clearFields() {
	
    }
    
    public void loadCombos() {
        loadComboPlanesSociales();
        loadComboDespachantes();
        loadPlanSocial();
        loadComboPrestaciones();
        loadPrestacion();
        loadData();
    }
    
    public void loadData() {
        if (coordinador.getPrestacion().getIdPrestacion() > -1) {
            tfStock.setValue(coordinador.getPrestacion().getCantDisponible());
            habilitarComponentes();
        }
    }
    
    private void habilitarComponentes() {
        if (tfStock.getDouble() == 0) {
            btnAddRecurso.setEnabled(false);
            tfStock.getTextField().setBackground(Color.red);
        } else {
            btnAddRecurso.setEnabled(true);
            tfStock.getTextField().setBackground(Color.white);
        }
    }
    
    public void loadComboPlanesSociales() {
        coordinador.getBeneficiario().retrieveData(coordinador.getPersona());
        String params = "" + coordinador.getPersona().getIdPersona();
        cbPlanesSociales.loadJCombo(LibSQL.exFunction("accionsocial.getallplanessocialesbyfilter", params));
        cbPlanesSociales.getCombo().setSelectedIndex(0);
    }
    
    public void loadComboPrestaciones() {
        String params = "" + coordinador.getPersona().getIdPersona() + ","+ coordinador.getPlanSocial().getIdPlanSocial();
        cbPrestaciones.loadJCombo(LibSQL.exFunction("accionsocial.getallprestacionesbyfilter", params));
        loadPrestacion();
    }
    
    public void loadComboAnios() {
        cbAnios.removeAllItems();
        int anioActual = Integer.parseInt(Environment.currentYear);
        for (int i = 2009; i <= anioActual+1; i++) {
            cbAnios.addItem("" + i);
        }
    }
    
    public void loadComboDespachantes() {
        cbDespachante.loadJCombo(LibSQL.exFunction("accionsocial.getalldespachantes", ""));
    }
    
    private void loadPlanSocial() {
        if( cbPlanesSociales.getCombo().getItemCount() > 0 ) {
            coordinador.getPlanSocial().setIdPlanSocial(Integer.parseInt(cbPlanesSociales.getSelectedValue().toString()));
            coordinador.getPlanSocial().retrieveData();
            loadComboPrestaciones();
        }
    }
    
    private void loadPrestacion() {
        if( cbPrestaciones.getCombo().getItemCount() > 0 ) {
            coordinador.getPrestacion().setIdPrestacion(Integer.parseInt(cbPrestaciones.getSelectedValue().toString()));
            coordinador.getPrestacion().retrieveData();
            loadData();
        }
    }
    
    private void btnAddRecurso_actionPerformed(ActionEvent e) {
        cargarAsignacion();
    }
    
    private void cargarAsignacion() {
        if (controlEntrega()) {
            if (Advisor.question("Aviso", "¿Desea registrar la entrega de la prestación \n\"" + cbPrestaciones.getSelectedItem() + "\"?")) {
                boolean aDomicilio = false;
                if(rbtnEnDomicilio.isSelected()) {
                    aDomicilio = true;
                }
                int _idTutor = 0;
                if (coordinador.getBeneficiario().getIdTutor() < 1) {//revisar si la condición es menor que 1 o basta con que sea menor que 0 
                    //id = coordinador.getPersona().getIdPersona();
                    _idTutor = 0;
                } else {
                    _idTutor = coordinador.getBeneficiario().getIdTutor();
                }
                String params = ""  + coordinador.getBeneficiario().getIdBeneficiario() + "," 
                                    + _idTutor + ","
                                    + coordinador.getPrestacion().getIdPrestacion() + ",'"
                                    + cbMeses.getSelectedValue() + "','" 
                                    + cbAnios.getSelectedItem() + "','" 
                                    + tfFechaEntrega.getDate() + "','" 
                                    + cbDespachante.getSelectedItem() + "',"
                                    + tfCantidad.getDouble() + ",0,''," 
                                    + aDomicilio + ",0,0";
                if( LibSQL.getInt("accionsocial.addentrega", params) > 0) {
                    setDespachanteSeleccionado(cbDespachante.getSelectedItem().toString());
                    limpiarCampos();
                    loadCombos();
                    loadData();
                    refresh();
                    cbDespachante.setSelectedValue(getDespachanteSeleccionado());
                } else {
                    Advisor.messageBox("Ocurrió un error al guardar los datos", "Error");
                }
            } 
            
        } else {
            ErrorAccionSocial.showMessage();
        }
    }
    
    private boolean controlEntrega() {
        boolean retorno = true;
        System.out.println(Environment.currentMonth);
        if (Integer.parseInt(cbAnios.getSelectedItem().toString()) > Integer.parseInt(Environment.currentYear)) {
            ErrorAccionSocial.setError(ErrorAccionSocial.PERIODO_ENTREGA);
            retorno = false;
        } else if ((Integer.parseInt(cbAnios.getSelectedItem().toString()) == Integer.parseInt(Environment.currentYear))&&(Integer.parseInt(cbMeses.getSelectedValue().toString()) > Integer.parseInt(Environment.currentMonth))) {
            ErrorAccionSocial.setError(ErrorAccionSocial.PERIODO_ENTREGA);
            retorno = false;
        } else if ( tfCantidad.getAmount() > coordinador.getPrestacion().getCantMaxima() ) {
                ErrorAccionSocial.setCantidadMinima(coordinador.getPrestacion().getCantMinima());
                ErrorAccionSocial.setCantidadMaxima(coordinador.getPrestacion().getCantMaxima());
                ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
                ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MAYOR_MAXIMO);
                retorno = false;
            } else if ( tfCantidad.getAmount() < coordinador.getPrestacion().getCantMinima() ) {
                    ErrorAccionSocial.setCantidadMinima(coordinador.getPrestacion().getCantMinima());
                    ErrorAccionSocial.setCantidadMaxima(coordinador.getPrestacion().getCantMaxima());
                    ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MENOR_MINIMO);
                    retorno = false;
                } else if ((coordinador.getBeneficiario().getCantidadPrestaciones(coordinador.getPrestacion(), Integer.parseInt(cbMeses.getSelectedValue().toString()), Integer.parseInt(cbAnios.getSelectedItem().toString())) + tfCantidad.getDouble()) > coordinador.getPrestacion().getCantMaxima()) {
                    BigDecimal cantidadPosible = new BigDecimal(""+ coordinador.getPrestacion().getCantMaxima());
                    cantidadPosible = cantidadPosible.subtract(new BigDecimal("" + coordinador.getBeneficiario().getCantidadPrestaciones(coordinador.getPrestacion(), Integer.parseInt(cbMeses.getSelectedValue().toString()), Integer.parseInt(cbAnios.getSelectedItem().toString()))));
                    ErrorAccionSocial.setCantidadPosibleAAsignar(cantidadPosible.doubleValue()); 
                    ErrorAccionSocial.setError(ErrorAccionSocial.MAXIMO_PRESTACIONES_ASIGNADAS);
                    retorno = false;
                    } else if (tfCantidad.getAmount() > coordinador.getPrestacion().getCantDisponible()) {
                        ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_MAYOR_STOCK);
                        retorno = false;
                        } else if (tfFechaEntrega.getDate().equals("")) {
                                ErrorAccionSocial.setError(ErrorAccionSocial.FECHA_ENTREGA);
                                retorno = false;
                            } else if (Proced.compareDates(Proced.setFormatDate(tfFechaEntrega.getString(),false),Environment.currentDate) == 2) {
                                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_FECHA_ASIGNACION_INVALIDA);
                                    retorno = false;
                                } else if (cbDespachante.getSelectedIndex() < 0) {
                                        ErrorAccionSocial.setError(ErrorAccionSocial.DESPACHANTE_SELECCIONADO);
                                        retorno = false;
                                }
        return retorno;
    }
    
    /*
    private boolean control() {
        boolean error = true;
        if ( tfCantidad.getAmount() > coordinador.getPrestacion().getCantMaxima() ) {
            ErrorAccionSocial.setCantidadMinima(coordinador.getPrestacion().getCantMinima());
            ErrorAccionSocial.setCantidadMaxima(coordinador.getPrestacion().getCantMaxima());
            ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
            ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MAYOR_MAXIMO);
            error = false;
        } else {
            if ( tfCantidad.getAmount() < coordinador.getPrestacion().getCantMinima() ) {
                ErrorAccionSocial.setCantidadMinima(coordinador.getPrestacion().getCantMinima());
                ErrorAccionSocial.setCantidadMaxima(coordinador.getPrestacion().getCantMaxima());
                ErrorAccionSocial.setCantidadAEntregar(tfCantidad.getAmount());
                ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_ASIGNADA_MENOR_MINIMO);
                error = false;
            }
        }
        if (error) {
            if ((coordinador.getBeneficiario().getCantidadPrestaciones(coordinador.getPrestacion(), tfFechaEntrega.getDate()) + tfCantidad.getDouble()) > coordinador.getPrestacion().getCantMaxima()) {
                BigDecimal cantidadPosible = new BigDecimal(""+ coordinador.getPrestacion().getCantMaxima());
                cantidadPosible = cantidadPosible.subtract(new BigDecimal("" + coordinador.getBeneficiario().getCantidadPrestaciones(coordinador.getPrestacion(), tfFechaEntrega.getDate())));
                ErrorAccionSocial.setCantidadPosibleAAsignar(cantidadPosible.doubleValue()); 
                ErrorAccionSocial.setError(ErrorAccionSocial.MAXIMO_PRESTACIONES_ASIGNADAS);
                error = false;
            } else {
                if (tfCantidad.getAmount() > coordinador.getPrestacion().getCantDisponible()) {
                    ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_MAYOR_STOCK);
                    error = false;
                } else {
                    if (Proced.compareDates(Proced.setFormatDate(tfFechaEntrega.getString(),false),Environment.currentDate) == 2) {
                        ErrorAccionSocial.setError(ErrorAccionSocial.CANTIDAD_FECHA_ASIGNACION_INVALIDA);
                        error = false;
                    } else {
                        if (cbDespachante.getSelectedIndex() < 0) {
                            ErrorAccionSocial.setError(ErrorAccionSocial.DESPACHANTE_SELECCIONADO);
                            error = false;
                        }
                    }
                }    
            }
        }
        return error;
    }*/

    private void cbPlanesSociales_itemStateChanged(ItemEvent e) {
    }

    private void limpiarCampos() {
        tfCantidad.setValue("");
        rbtnEnEstablecimiento.setSelected(true);
        coordinador.permitirAvance(false);
    }

    private void btnAgregarBeneficiario_actionPerformed(ActionEvent e) {
        coordinador.setPaso(2);
        coordinador.setOpcion(1);
        coordinador.setPreguntarDireccion(false);
        coordinador.siguiente();
    }

    public void setDespachanteSeleccionado(String despachanteSeleccionado) {
        this.despachanteSeleccionado = despachanteSeleccionado;
    }

    public String getDespachanteSeleccionado() {
        return despachanteSeleccionado;
    }
    
    private void mostrarMensaje() {
        String mensaje = "";
        if (!beneficiariosConProblema01.equals("")) {
            mensaje = "Ocurrió un error al guardar los datos de los siguientes beneficiarios: \n" + beneficiariosConProblema01 ;
        }
        if (!beneficiariosConProblema02.equals("")) {
            mensaje = "\nNo se pueden entregar mas recursos de la prestación\n seleccionada a los siguientes beneficiarios: \n" + beneficiariosConProblema02 ;
        }
        if (!mensaje.equals("")) {
            Advisor.messageBox(mensaje, "Error");
        }
    }
}

