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
 * DiagramaBuses.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */

package org.scassi.projects.southwest.avl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;

import org.scassi.projects.southwest.avl.classes.Bus;
import org.scassi.projects.southwest.avl.classes.DiagramaServicio;
import org.scassi.projects.southwest.avl.classes.Diagramacion;
import org.scassi.projects.southwest.avl.classes.Etapa;
import org.scassi.projects.southwest.avl.classes.FranjaHoraria;
import org.scassi.projects.southwest.avl.classes.Ruta;

public class DiagramaBuses extends BasicPrimitivePanel {

    private TFInput tfDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private CBInput cbDesdeHora = new CBInput(0,"HH",false);
    private CBInput cbDesdeMinuto = new CBInput(0,"MM",false);
    private TFInput tfDuracion = new TFInput(DataTypes.INTEGER, "Duración", false);
    private TFInput tfBuses = new TFInput(DataTypes.INTEGER,"Buses",false);
    private TFInput tfFrecuencia= new TFInput(DataTypes.INTEGER,"Frecuencia",false);
    private TFInput tfDescanso = new TFInput(DataTypes.INTEGER,"Descanso",false);
    private TFInput tfCantFranjas = new TFInput(DataTypes.INTEGER, "Cantidad de franjas", false);

    private CBInput cbRutas = new CBInput(0,"Ruta",false);

    private BasicDialog parentContainer;

    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpSouth = new BasicPanel();
    
    private BasicPanel jpFilters = new BasicPanel();
    private BasicPanel jpListPanel = new BasicPanel();

    private BasicButton btnIniciarDiagramacion = new BasicButton("Iniciar Diagramación");
    private BasicButton btnAgregarFranja = new BasicButton("Agregar Franja");
    private BasicButton btnAsignarBuses = new BasicButton("Reasignar Buses");
    private BasicButton btnSimularDiagramacion = new BasicButton("Simular Diagramación");
    private BasicButton btnGrabarDiagramacion = new BasicButton("Grabar Diagramación");
    
    private Diagramacion diagramacion;
    private Vector<FranjaHoraria> franjasHorarias = new Vector<FranjaHoraria>();
    private Vector<Bus> busesSeleccionados = new Vector<Bus>();
    Vector<DiagramaServicio> serviciosDiagramados = new Vector<DiagramaServicio>();

    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
    
    private BasicScrollPane scpFranjasHorarias = new BasicScrollPane();
    private DefaultTableModel  modelFranjasHorarias = new DefaultTableModel();
    private BasicTable tblFranjasHorarias = new BasicTable(modelFranjasHorarias) {
	public boolean isCellEditable(int row, int col) {
	    return false;
	}
    };

    private BasicScrollPane scpDiagramacion = new BasicScrollPane();
    private DefaultTableModel  modelDiagramacion = new DefaultTableModel();
    private BasicTable tblDiagramacion = new BasicTable(modelDiagramacion) {
	public boolean isCellEditable(int row, int col) {
	    return false;
	}
    };

    private final int FECHAINICIO = 1;
    private final int HORAINICIO = 2;
    private final int DURACION = 3;
    private final int FRECUENCIA = 4;
    private final int DESCANSO = 5;
    private final int RUTA = 6;
    private String fechaInicio = "";
    

    public DiagramaBuses() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	
	clearData();
	
	jpCenter.setLayout(new BorderLayout());
	jpSouth.setLayout(null);
	jpSouth.setPreferredSize(new Dimension(1, 40));
	jpFilters.setLayout(new BorderLayout());
	jpFilters.setPreferredSize(new Dimension(1, 150));
	jpListPanel.setLayout(new BorderLayout());
	jpCenter.setBorder(BorderPanel.getBorderPanel("Franjas Horarias"));
	this.setSize(new Dimension(951, 540));
	tfDesde.setBounds(new Rectangle(5, 5, 80, 35));
	tfDuracion.setBounds(new Rectangle(5, 45, 80, 35));
	cbRutas.setBounds(new Rectangle(5, 85, 95, 35));
	cbDesdeHora.setBounds(new Rectangle(90, 5, 55, 35));
	cbDesdeMinuto.setBounds(new Rectangle(150, 5, 55, 35));
	
	for (int i = 0; i <=23; i++) {
	    if (i < 10) {
		cbDesdeHora.addItem("0" + i);
	    } else {
		cbDesdeHora.addItem("" + i);
	    }
	}

	for (int i = 0; i <=59; i++) {
	    if (i < 10) {
		cbDesdeMinuto.addItem("0" + i);
	    } else {
		cbDesdeMinuto.addItem("" + i);
	    }
	}

	tfBuses.setBounds(new Rectangle(90, 45, 45, 35));
	tfFrecuencia.setBounds(new Rectangle(140, 45, 75, 35));
	tfDescanso.setBounds(new Rectangle(105, 85, 75, 35));
	scpDiagramacion.getViewport().add(tblDiagramacion, null);
	jpListPanel.add(scpDiagramacion, BorderLayout.CENTER);
	jpCenter.add(jpListPanel, BorderLayout.CENTER);
	scpFranjasHorarias.getViewport().add(tblFranjasHorarias, null);
	jpFilters.add(scpFranjasHorarias, BorderLayout.CENTER);

	jpCenter.add(jpFilters, BorderLayout.NORTH);
	this.add(jpCenter, BorderLayout.CENTER);
	jpSouth.add(btnIniciarDiagramacion, null);
	jpSouth.add(btnAgregarFranja, null);
	jpSouth.add(btnSimularDiagramacion, null);
	jpSouth.add(btnGrabarDiagramacion, null);
	jpSouth.add(btnAsignarBuses, null);
	this.add(jpSouth, BorderLayout.SOUTH);
	tfDesde.setToday();
	cbRutas.loadJCombo("reportes.getAllRutasConDuracion", "");

	btnIniciarDiagramacion.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnIniciarDiagramacion_actionPerformed(e);
		}
	    });
	btnIniciarDiagramacion.setOpaque(true);
	btnIniciarDiagramacion.setFont(new Font("Dialog", 1, 10));
	btnIniciarDiagramacion.setForeground(Color.black);
	btnIniciarDiagramacion.setBackground(new Color(0, 195, 27));
	btnIniciarDiagramacion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnIniciarDiagramacion.setBounds(new Rectangle(5, 5, 130, 25));

	btnAgregarFranja.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAgregarFranja_actionPerformed(e);
		}

	    });
	
	btnAgregarFranja.setOpaque(true);
	btnAgregarFranja.setFont(new Font("Dialog", 1, 10));
	btnAgregarFranja.setForeground(Color.black);
	btnAgregarFranja.setBackground(new Color(197, 197, 0));
	btnAgregarFranja.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAgregarFranja.setBounds(new Rectangle(140, 5, 130, 25));
	
	btnSimularDiagramacion.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSimularDiagramacion_actionPerformed(e);
		}
	    });

	btnSimularDiagramacion.setOpaque(true);
	btnSimularDiagramacion.setFont(new Font("Dialog", 1, 10));
	btnSimularDiagramacion.setForeground(Color.black);
	btnSimularDiagramacion.setBackground(new Color(225, 70, 7));
	btnSimularDiagramacion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnSimularDiagramacion.setBounds(new Rectangle(525, 5, 130, 25));

	btnGrabarDiagramacion.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGrabarDiagramacion_actionPerformed(e);
		}
	    });
	btnGrabarDiagramacion.setOpaque(true);
	btnGrabarDiagramacion.setFont(new Font("Dialog", 1, 10));
	btnGrabarDiagramacion.setForeground(Color.black);
	btnGrabarDiagramacion.setBackground(new Color(240, 23, 2));
	btnGrabarDiagramacion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnGrabarDiagramacion.setBounds(new Rectangle(815, 5, 130, 25));

	btnAsignarBuses.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAsignarBuses_actionPerformed(e);
		}
	    });
	btnAsignarBuses.setOpaque(true);
	btnAsignarBuses.setFont(new Font("Dialog", 1, 10));
	btnAsignarBuses.setForeground(Color.black);
	btnAsignarBuses.setBackground(new Color(0, 120, 170));
	btnAsignarBuses.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	btnAsignarBuses.setBounds(new Rectangle(275, 5, 130, 25));


	/*
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    btnCargarFranjas_actionPerformed(actionEvent);
		}
	    },
	    "CargarFranjas",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0),
	    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT 
	);

	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    btnDiagramar_actionPerformed(actionEvent);
		}
	    },
	    "Diagramar",
	    KeyStroke.getKeyStroke( KeyEvent.VK_F8, 0),
	    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT 
	);
	*/

	tfDesde.getTextField().addAncestorListener( new RequestFocusListener() );
	tfBuses.getTextField().addAncestorListener( new RequestFocusListener() );

	tfDuracion.getTextField().addAncestorListener( new RequestFocusListener() );
	tfFrecuencia.getTextField().addAncestorListener( new RequestFocusListener() );
	tfDescanso.getTextField().addAncestorListener( new RequestFocusListener() );
	tfCantFranjas.getTextField().addAncestorListener( new RequestFocusListener() );

	setHeaderList();

    }


    private void setHeaderList() {
	modelDiagramacion.addColumn("FH");
	modelDiagramacion.addColumn("Bus");
	modelDiagramacion.addColumn("Sale");
	modelDiagramacion.addColumn("Llega");
	modelDiagramacion.addColumn("Listo");
	modelDiagramacion.addColumn("Horarios de llegada por etapa");

	tblDiagramacion.setDefaultRenderer(Number.class, new DCellRenderer());
	tblDiagramacion.setDefaultRenderer(Double.class, new DCellRenderer());
	tblDiagramacion.setDefaultRenderer(Object.class, new DCellRenderer());

	int[] _sizes = new int[]{ 34, 34, 125, 59, 46, 906 };
	for (int i = 0; i < _sizes.length; ++i) {
	    TableColumn col = tblDiagramacion.getColumnModel().getColumn(i);
	    col.setPreferredWidth(_sizes[i]);
	}

	modelFranjasHorarias.addColumn("Nº");
	modelFranjasHorarias.addColumn("Desde");
	modelFranjasHorarias.addColumn("Dura");
	modelFranjasHorarias.addColumn("Ruta");
	modelFranjasHorarias.addColumn("Frec.");
	modelFranjasHorarias.addColumn("Pausa");
	tblFranjasHorarias.setDefaultRenderer(Number.class, new DCellRenderer());
	tblFranjasHorarias.setDefaultRenderer(Double.class, new DCellRenderer());
	tblFranjasHorarias.setDefaultRenderer(Object.class, new DCellRenderer());
	
	_sizes = new int[]{ 34, 34, 125, 59, 46, 906 };
	for (int i = 0; i < _sizes.length; ++i) {
	    TableColumn col = tblDiagramacion.getColumnModel().getColumn(i);
	    col.setPreferredWidth(_sizes[i]);
	}

    }

    private void clearData() {
	diagramacion = new Diagramacion();
	btnIniciarDiagramacion.setEnabled(true);
	btnAgregarFranja.setEnabled(true);
	btnAsignarBuses.setEnabled(false);
	btnSimularDiagramacion.setEnabled(false);
	btnGrabarDiagramacion.setEnabled(false);
    }
    
    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnIniciarDiagramacion_actionPerformed(ActionEvent e) {
	iniciarFranjas();
    }
    
    private void iniciarFranjas() {
	/** Condiciones (por ahora se cumplen)
	 * 1) La cantidad de etapas es la misma para cada franja
	 * 2) La cantidad de buses disponibles es invariable a lo largo de la diagramación
	 * 3) La cantidad mínima de buses depende de la razón (cociente) entre
	 *     la duración de la ruta más larga y la longitud de la franja horaria
	*/

	int _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfCantFranjas, "Cantidad de franjas", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	if (_answer == JOptionPane.OK_OPTION) {
	    int _cantFranjas = tfCantFranjas.getInteger();
	    System.out.println("Cantidad: " + _cantFranjas);
	    boolean cancelled = false;
	    int i = 0;
	    FranjaHoraria _franjas[] = new FranjaHoraria[_cantFranjas];
	    Calendar _calendar = Calendar.getInstance();

	    while (!cancelled && i < _cantFranjas) {
	        _franjas[i] = new FranjaHoraria(diagramacion, i+1);
	        if (i == 0) { //primera y única vez, pregunto fecha/hora de apertura

		    cancelled = !askForField(FECHAINICIO, _franjas[i], _calendar);
		    if (cancelled) break;

	            cancelled = !askForField(HORAINICIO, _franjas[i], _calendar);
	            if (cancelled) break;
	        }
		
	        cancelled = !askForField(DURACION, _franjas[i], _calendar);
	        if (cancelled) break;

	        cancelled = !askForField(FRECUENCIA, _franjas[i], _calendar);
	        if (cancelled) break;

	        cancelled = !askForField(DESCANSO, _franjas[i], _calendar);
	        if (cancelled) break;

	        cancelled = !askForField(RUTA, _franjas[i], _calendar);
	        if (cancelled) break;
		
		if (!cancelled) {
		    _franjas[i].prepararDatosRuta();

		    // Importante, se carga SIEMPRE automáticamente la fechaHoraApertura,
		    // la que depende de la duración de la franja anterior que se carga al grabar la franja
		    _franjas[i].setFechaHoraApertura(dateTimeFormatter.format(_calendar.getTime()));

		    if (saveFranja(_franjas[i])) {
		        _calendar.add(Calendar.MINUTE, _franjas[i].getDuracion());
			i++;
		    }
		}
	    }
	    
	    if (!cancelled) {
	        franjasHorarias.removeAllElements();
		for (FranjaHoraria _franja : _franjas) {
		    franjasHorarias.add(_franja);
		}
		setBuses();
	        updateTablaFranjas();
	    }
	}
    }

    private boolean askForField(int _field, FranjaHoraria _franja, Calendar _calendar) {
	boolean _returns = false;
	boolean _cancelled = false;
	int _answer;
	switch (_field) {
	    case FECHAINICIO:
		_answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfDesde, "Fecha a diagramar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (_answer == JOptionPane.OK_OPTION) {
		    fechaInicio = tfDesde.getDate();
		}
		_returns = true;
		break;
	    case HORAINICIO:
		BasicPanel jpDesdeHora = new BasicPanel(new GridLayout(1,2));
		jpDesdeHora.add(cbDesdeHora);
		jpDesdeHora.add(cbDesdeMinuto);
		_answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), jpDesdeHora, "Horario de inicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (_answer == JOptionPane.OK_OPTION) {
		    //armo fecha/hora INICIAL
		    String _fechaHoraInicial = fechaInicio + " " + cbDesdeHora.getSelectedItem() + ":" + cbDesdeMinuto.getSelectedItem();
		    try {
			_calendar.setTime(dateTimeFormatter.parse(_fechaHoraInicial));
		    } catch (ParseException f) {
			f.printStackTrace();
		    }
		}
		_returns = true;
		break;
	    case DURACION:
		tfDuracion.setName("Duración (en minutos) de la franja " + _franja.getNumero());
		int _duracion = tfDuracion.getInteger();
		tfDuracion.setValue(0);
		while (tfDuracion.getInteger() <= 0 && !_cancelled) {
		    tfDuracion.setValue(_duracion);
		    _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfDuracion, "Franja Nº " + _franja.getNumero() + " - Duración", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		    if (_answer == JOptionPane.OK_OPTION) {
			_franja.setDuracion(tfDuracion.getInteger());
			_returns = true;
		    } else {
			_cancelled = true;
		    }
		}
		break;
	    case FRECUENCIA:
		tfFrecuencia.setName("Frecuencia (en minutos) de la franja " + _franja.getNumero());
		int _frecuencia = tfFrecuencia.getInteger();
		tfFrecuencia.setValue(0);
		while (tfFrecuencia.getInteger() <= 0 && !_cancelled) {
		    tfFrecuencia.setValue(_frecuencia);
		    _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfFrecuencia, "Franja Nº " + _franja.getNumero() + " - Frecuencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		    if (_answer == JOptionPane.OK_OPTION) {
			_franja.setFrecuenciaMinima(tfFrecuencia.getInteger());
			_returns = true;
		    } else {
			_cancelled = true;
		    }
		}
		break;
	    case DESCANSO:
		tfDescanso.setName("Descanso (en minutos) de la franja " + _franja.getNumero());
		int _descanso = tfDescanso.getInteger();
		tfDescanso.setValue(0);
		while (tfDescanso.getInteger() <= 0 && !_cancelled) {
		    tfDescanso.setValue(_descanso);
		    _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfDescanso, "Franja Nº " + _franja.getNumero() + " - Descanso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		    if (_answer == JOptionPane.OK_OPTION) {
			_franja.setDescanso(tfDescanso.getInteger());
			_returns = true;
		    } else {
			_cancelled = true;
		    }
		}
		break;
	    case RUTA:
		cbRutas.setName("Ruta de la franja " + _franja.getNumero());
		_answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), cbRutas, "Franja Nº " + _franja.getNumero() + " - Ruta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (_answer == JOptionPane.OK_OPTION) {
		    _franja.setRutaByID(Integer.parseInt(cbRutas.getSelectedValue().toString()));
		    _returns = true;
		}
		break;
	}
	return _returns;
    }
    
    private void simulate() {
	/** Simulación */
	modelDiagramacion.setRowCount(0);

	for (Bus _bus : busesSeleccionados) {
	    _bus.desasignarTodo();
	}

	int _cantBuses = franjasHorarias.elementAt(0).getBuses();

	int _busIdx = 0;
	
	int _cantEtapas = franjasHorarias.elementAt(0).getRuta().getCantEtapas();
	
	
	boolean _error = false;
	int _franjaIdx = 0; 
	
	serviciosDiagramados.removeAllElements();
	
	while (_franjaIdx < franjasHorarias.size() && !_error) {
	    try {
		Calendar _start = Calendar.getInstance();
		_start.setTime(dateTimeFormatter.parse(franjasHorarias.elementAt(_franjaIdx).getFechaHoraApertura()));
		System.out.println("Inicio Franja[" + _franjaIdx + "]: " + dateTimeFormatter.format(_start.getTime()));
		Calendar _finish = (Calendar)_start.clone();
		Calendar _serviceStart = (Calendar)_start.clone();
		_finish.add(Calendar.MINUTE, franjasHorarias.elementAt(_franjaIdx).getDuracion());
		if (_franjaIdx == franjasHorarias.size()-1) {
		    _finish.add(Calendar.MILLISECOND, 1);
		}

		System.out.println("Fin Franja[" + _franjaIdx + "]: " + dateTimeFormatter.format(_finish.getTime()));
		System.out.println("Duración de la ruta estimada: " + franjasHorarias.elementAt(_franjaIdx).getRuta().getDuracion() + " minutos");

		int i = 1;
		while (_serviceStart.before(_finish) && !_error) {
		    DiagramaServicio _servicio = new DiagramaServicio(franjasHorarias.elementAt(_franjaIdx), i);
		    _servicio.setFechaHoraSalida(dateTimeFormatter.format(_serviceStart.getTime()));
		    /************ Aquí simular tiempos de etapas !!!!!!! **************/
		    
		    int _duracionRuta = 0;
		    Calendar _horarioEtapa = (Calendar) _serviceStart.clone();
		    for (int _etapaIdx = 0; _etapaIdx < _cantEtapas; _etapaIdx++) {
			int _franjaActualIdx = franjasHorarias.size()-1;
			boolean _enFranja = false;
			while (_franjaActualIdx >= 0 && !_enFranja) {
			    Calendar _position = (Calendar)_serviceStart.clone();
			    _position.add(Calendar.MINUTE, _duracionRuta);
			    Calendar _apertura = Calendar.getInstance();
			    _apertura.setTime(dateTimeFormatter.parse(franjasHorarias.elementAt(_franjaActualIdx).getFechaHoraApertura()));
			    _apertura.add(Calendar.MILLISECOND, -1);
			    _enFranja = _position.after(_apertura);
			    if (!_enFranja) _franjaActualIdx--;
			}
			_duracionRuta += franjasHorarias.elementAt(_franjaActualIdx).getRuta().getEtapa(_etapaIdx).getDuracion();

			Etapa _etapa = new Etapa(franjasHorarias.elementAt(_franjaActualIdx), franjasHorarias.elementAt(_franjaActualIdx).getRuta().getEtapa(_etapaIdx).getIdEtapa());
			_etapa.setDuracion(franjasHorarias.elementAt(_franjaActualIdx).getRuta().getEtapa(_etapaIdx).getDuracion());
		        _horarioEtapa.add(Calendar.MINUTE, _etapa.getDuracion());
		        _etapa.setFechaHoraLlegadaDiagramada(timeFormatter.format(_horarioEtapa.getTime()));
			_servicio.addEtapa(_etapa);
		    }
		    _servicio.calcularFechaHoraLlegada();
		    _servicio.calcularFechaHoraDisponible(franjasHorarias.elementAt(_franjaIdx).getDescanso());
		    boolean _asignado = false;
		    int _errorIdx = _busIdx;
		    while (!_asignado && !_error) {
			Bus _bus = busesSeleccionados.elementAt(_busIdx);
			_asignado = _bus.asignarServicio(_servicio);
		        if (_asignado) {
		            System.out.println("El servicio " + _servicio.getIdDiagramaServicio() + " con el bus " + _bus.getInterno() + 
		                " sale: " + _servicio.getFechaHoraSalida() + ", " + 
		                "llega: " + _servicio.getFechaHoraLlegada() + " (" + _duracionRuta + " minutos)" +
		                " y se libera: " + _servicio.getFechaHoraDisponible());
		            _serviceStart.add(Calendar.MINUTE, franjasHorarias.elementAt(_franjaIdx).getFrecuenciaMinima());

		            Vector row = new Vector();
		            row.add(franjasHorarias.elementAt(_franjaIdx).getNumero());
		            row.add(_bus.getInterno());
		            row.add(_servicio.getFechaHoraSalida());
		            row.add(timeFormatter.format(dateTimeFormatter.parse(_servicio.getFechaHoraLlegada())));
		            if (dateFormatter.parse(_servicio.getFechaHoraSalida()).before(dateFormatter.parse(_servicio.getFechaHoraLlegada()))) {
		                row.add("*" + timeFormatter.format(dateTimeFormatter.parse(_servicio.getFechaHoraDisponible())));
		            } else {
		                row.add(timeFormatter.format(dateTimeFormatter.parse(_servicio.getFechaHoraDisponible())));
		            }
		            row.add(_servicio);
			    
			    serviciosDiagramados.add(_servicio);
		            modelDiagramacion.addRow(row);

			    i++;
			} else {
			    Advisor.messageBox("No se puede asignar el Bus " + _bus.getInterno() + " ya que no estará disponible a tiempo", "Error grave");
			    _error = true;
			    System.out.println("Error grave, no hay vehículos disponibles para el siguiente servicio");
			    Advisor.messageBox("Error grave, no hay vehículos disponibles para el siguiente servicio\n"
			                       + "\nCantidad necesaria de buses: " + (_cantBuses+1)
			                       + "\nCantidad disponible de buses: " + _cantBuses, "Buses insuficientes");
			}
		        _busIdx++;
		        if (_busIdx > _cantBuses-1) {
		            _busIdx = 0;
		        }
		        /*if (_busIdx == _errorIdx) {
		            _error = true;
		            System.out.println("Error grave, no hay vehículos disponibles para el siguiente servicio");
			    Advisor.messageBox("Error grave, no hay vehículos disponibles para el siguiente servicio\n"
					       + "\nCantidad necesaria de buses: " + franjasHorarias.elementAt(_franjaIdx).getMinBuses()
					       + "\nCantidad disponible de buses: " + _cantBuses, "Buses insuficientes");
		        }*/
		    }
		}
	    } catch (ParseException f) {
		f.printStackTrace();
	    }
	    _franjaIdx++;
	}
	if (!_error) {
	    btnGrabarDiagramacion.setEnabled(true);
	}
    }

    private void btnSimularDiagramacion_actionPerformed(ActionEvent e) {
	simulate();
    }

    private void btnGrabarDiagramacion_actionPerformed(ActionEvent e) {
	Thread _thread = new Thread(new Runnable() {
	       public void run() {
	           saveData();
	       }
	});
	_thread.start();
    }
    
    private void btnAsignarBuses_actionPerformed(ActionEvent e) {
	setBuses();
    }

    private void btnAgregarFranja_actionPerformed(ActionEvent e) {
	// Preguntar la posición de la franja
	int _rowAt = -1;
	if (franjasHorarias.size() > 0) {
	    CBInput _cbFranjas = new CBInput(0, "Insertar en la posición Nº", false);
	    for (FranjaHoraria _franja : franjasHorarias) {
		_cbFranjas.addItem(_franja.getNumero());
	    }
	    _cbFranjas.addItem(franjasHorarias.size()+1);
	    int _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), _cbFranjas, "Selección de posición", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    if (_answer == JOptionPane.OK_OPTION) {
		_rowAt = Integer.parseInt(_cbFranjas.getSelectedItem().toString())-1;
	    } else {
		_rowAt = -1;
	    }
	} else {
	    _rowAt = 0;
	}
	if (_rowAt >= 0) {
	    FranjaHoraria _franja = new FranjaHoraria(diagramacion, _rowAt+1);
	    boolean cancelled = false;
	    Calendar _calendar = Calendar.getInstance();
	    while (!cancelled) {
		if (_rowAt == 0) {
		    // Debo pedir la Hora inicial
		    cancelled = !askForField(FECHAINICIO, _franja, _calendar);
		    if (cancelled) break;
    
		    cancelled = !askForField(HORAINICIO, _franja, _calendar);
		    if (cancelled) break;
		} else {
		    // Debo calcular la Hora inicial
		    FranjaHoraria _ref = franjasHorarias.elementAt(_rowAt - 1);
		    try {
		        _calendar.setTime(dateTimeFormatter.parse(_ref.getFechaHoraApertura()));
		        _calendar.add(Calendar.MINUTE, _ref.getDuracion());
		        _franja.setFechaHoraApertura(dateTimeFormatter.format(_calendar.getTime()));
		    } catch (ParseException x) {
		        x.printStackTrace();
		    }
		}
		cancelled = !askForField(DURACION, _franja, _calendar);
		if (cancelled) break;
    
		cancelled = !askForField(FRECUENCIA, _franja, _calendar);
		if (cancelled) break;
    
		cancelled = !askForField(DESCANSO, _franja, _calendar);
		if (cancelled) break;
    
		cancelled = !askForField(RUTA, _franja, _calendar);
		if (cancelled) break;
		
		if (!cancelled) {
		    _franja.prepararDatosRuta();
    
		    // Importante, se carga SIEMPRE automáticamente la fechaHoraApertura,
		    // la que depende de la duración de la franja anterior que se carga al grabar la franja
		    _franja.setFechaHoraApertura(dateTimeFormatter.format(_calendar.getTime()));
    
		    if (saveFranja(_franja)) {
			_calendar.add(Calendar.MINUTE, _franja.getDuracion());
			if (_rowAt == franjasHorarias.size()) {
			    //agrego la franja al final
			    franjasHorarias.add(_franja);
			} else {
			    //desplazar el resto de las franjas!!!
			    franjasHorarias.insertElementAt(_franja, _rowAt);
			    shiftFranjas(_rowAt);
			}
		        setBuses();
			updateTablaFranjas();
		    }
		}
		break;
	    }
	}
    }

    private boolean saveFranja(FranjaHoraria _franja) {
	String _message = "<p align=left>Franja Horaria Nº " + _franja.getNumero()
	    + "\nDesde: " + _franja.getFechaHoraApertura()
	    + "\nDuración: " + _franja.getDuracion()
	    + "\nFrecuencia: " +_franja.getFrecuenciaMinima()
	    + "\nPausa: " + _franja.getDescanso()
	    + "\nBuses necesarios: " + _franja.getMinBuses();
	
	return (Advisor.question("¿Datos correctos?", "¿Son correctos los siguientes datos?\n\n"  + _message)); //¿acepta los datos presentados?
    }

    private int getMinBuses() {
	int _minBuses = 0;
	for (FranjaHoraria _franja : franjasHorarias) {
	    if (_franja.getMinBuses() > _minBuses) {
		_minBuses = _franja.getMinBuses();
	    }
	}
	return _minBuses;
    }

    private void setBuses() {
	int _minBuses = getMinBuses();
	tfBuses.setValue(_minBuses);
	int _cantBuses = 0;
	while (_cantBuses < _minBuses) {
	    int _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), tfBuses, "Cantidad de buses", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    if (_answer == JOptionPane.OK_OPTION) {
		_cantBuses = tfBuses.getInteger();
		if (_cantBuses < _minBuses) {
		    Advisor.messageBox("El valor mínimo es " + _minBuses + " buses", "Error");
		    tfBuses.setValue(_minBuses);
		}
	    } else {
	        _cantBuses = _minBuses;
		Advisor.messageBox("Se ha seleccionado el valor mínimo: " + _minBuses + " buses", "Aviso");
	    }
	}

	for (FranjaHoraria _franja : franjasHorarias) {
	    _franja.setBuses(_cantBuses);
	}
	
	CBInput _cbBuses = new CBInput(0, "Buses", false);
	_cbBuses.loadJCombo("reportes.getAllBuses", "");

	boolean cancelled = false;
	Vector<Bus> _busesSeleccionados = new Vector<Bus>();
	
	while (_busesSeleccionados.size() < _cantBuses && !cancelled) {
	    int _answer = JOptionPane.showInternalOptionDialog(Environment.getActiveDesktop(), _cbBuses, "Bus " + (_busesSeleccionados.size()+1) + "/" + _cantBuses, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    if (_answer == JOptionPane.OK_OPTION) {
	        _busesSeleccionados.add(new Bus(Integer.parseInt(_cbBuses.getSelectedValue().toString()), Integer.parseInt(_cbBuses.getSelectedItem().toString())));
		_cbBuses.removeItemAt(_cbBuses.getSelectedIndex());
	    } else {
		cancelled = true;
	        break;
	    }
	}
	if (!cancelled) {
	    busesSeleccionados.removeAllElements();
	    for (Bus _bus : _busesSeleccionados) {
		busesSeleccionados.add(_bus);
	    }
	    modelDiagramacion.setRowCount(0);
	    btnAsignarBuses.setEnabled(true);
	    btnSimularDiagramacion.setEnabled(true);
	}
    }

    private void updateTablaFranjas() {
	modelFranjasHorarias.setRowCount(0);
	for (FranjaHoraria _franja : franjasHorarias) {
	    modelFranjasHorarias.addRow(new Object[] {_franja.getNumero(), _franja.getFechaHoraApertura(), _franja.getDuracion(), _franja.getRuta().getNombre() + " (" + _franja.getRuta().getDuracion() + " min.)", _franja.getFrecuenciaMinima(), _franja.getDescanso()});
	}
	modelDiagramacion.setRowCount(0);
    }

    private void shiftFranjas(int _index) {
	int i = _index+1;
	while (i < franjasHorarias.size()) {
	    FranjaHoraria _ref = franjasHorarias.elementAt(i-1);
	    Calendar _calendar = Calendar.getInstance();
	    try {
		_calendar.setTime(dateTimeFormatter.parse(_ref.getFechaHoraApertura()));
		_calendar.add(Calendar.MINUTE, _ref.getDuracion());
	        franjasHorarias.elementAt(i).setFechaHoraApertura(dateTimeFormatter.format(_calendar.getTime()));
	        franjasHorarias.elementAt(i).setNumero(_ref.getNumero()+1);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    i++;
	}
    }

    public boolean saveData() {
	boolean _error = false;
	boolean _returns = false;
	if (Advisor.question("Grabar diagramación", "¿Está seguro de grabar esta diagramación?")) {
	    btnIniciarDiagramacion.setEnabled(false);
	    btnAgregarFranja.setEnabled(false);
	    btnAsignarBuses.setEnabled(false);
	    btnSimularDiagramacion.setEnabled(false);
	    btnGrabarDiagramacion.setEnabled(false);
	    //Primero debo grabar las franjas
	    Environment.setIndeterminate(true);
	    Environment.jpStatusBar.setAction("Grabando diagramación, aguarde...");
	    diagramacion.setFechaHoraApertura(franjasHorarias.elementAt(0).getFechaHoraApertura());
	    if (diagramacion.saveData() > 0) {
		for (FranjaHoraria _franja : franjasHorarias) {
		    if (_franja.saveData() < 0) {
			_error = true;
			break;
		    }
		}
	    //Luego grabo los servicios diagramados
		if (!_error) {
		    for (DiagramaServicio _servicio : serviciosDiagramados) {
			int _id = _servicio.saveData();
			if (_id > 0) {
			    //_savedIds.add(_id);
			} else  {
			    _error = true;
			    break;
			    //revert!!!!!
			}
		    }
		}
	        if (_error) {
	            for (FranjaHoraria _franja : franjasHorarias) {
	                _franja.delete();
	            }
		    diagramacion.delete();
	            Advisor.messageBox("Ha ocurrido un error al intengar grabar la diagramación\nRevise los datos y ejecute la simulación nuevamente", "Error");
	            Environment.jpStatusBar.setAction("Error al grabar la diagramación...");
	            btnIniciarDiagramacion.setEnabled(true);
	            btnAgregarFranja.setEnabled(true);
	            btnAsignarBuses.setEnabled(true);
	            btnSimularDiagramacion.setEnabled(true);
	            btnGrabarDiagramacion.setEnabled(false);
	        } else {
	            _returns = true;
		    Advisor.messageBox("La diagramación se ha grabado exitosamente con el ID Nº " + diagramacion.getIdDiagramacion(), "Diagramación grabada");
	            Environment.jpStatusBar.setAction("Diagramación grabada con éxito...");
		    clearData();
	        }
	    } else {
		//error al grabar la diagramación
	    }
	}
	Environment.setIndeterminate(false);
	return _returns;
    }

    private class DCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
	    // Set the background color
	    if (selected) {
	        setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
	        setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	    } else {
	        setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
	        setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	    }

	    if (value instanceof Long) {
		Long in = (Long) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Integer) {
		Integer in = (Integer) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Double) {
		Double in = (Double) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Number) {
		Number in = (Number) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof String) {
		String in = (String)value;
		if (in.startsWith("*")) {
		    in = in.substring(2);
		    //setForeground(Color.RED);
		    setBackground(new Color(0,100,250));
		}
	        super.getTableCellRendererComponent(table, in, selected, focused, row, column);
	        setHorizontalAlignment(CENTER);
	    } else {
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		setHorizontalAlignment(LEFT);
	    }

	    // Set the background color
	    
	    if (value instanceof DiagramaServicio) {
		return new ServiciosCell((DiagramaServicio)value);
	    }
	    
	    /*if (true) {
	    setValue("personas");
	    } else {
	    setValue("inspecciones");
	    }*/
	    
	    return this;
	}
    }

    private class ServiciosCell extends JPanel {

	/* # Etapa, # Boletos, # Inspecciones, ID Color: 0=Sin Info; 1=Verde; 2=Amarillo; 3=Rojo */

	private Color[] colors = { Color.white, Color.green, Color.yellow, Color.red };

	public ServiciosCell(DiagramaServicio _servicio) {
	    Vector<Etapa> _etapas = _servicio.getEtapas();
	    if (_etapas.size() > 0) {
		this.setLayout(new GridLayout(1, _etapas.size()));
		for (int i = 0; i < _etapas.size(); i++) {
		    JLabel etapa = new JLabel("  " /*+ _etapas.elementAt(i).getDuracion() + " - " */ + _etapas.elementAt(i).getFechaHoraLlegadaDiagramada().split(" ")[1] + " ");
		    etapa.setHorizontalAlignment(JLabel.RIGHT);
		    etapa.setOpaque(true);
		    etapa.setBorder(new LineBorder(Color.black, 1));
		    etapa.setBackground(colors[_etapas.elementAt(i).getFranjaHoraria().getNumero() % colors.length]);
		    add(etapa);
		}
	    }
	}
    }

    /**
     *  Convenience class to request focus on a component.
     *
     *  When the component is added to a realized Window then component will
     *  request focus immediately, since the ancestorAdded event is fired
     *  immediately.
     *
     *  When the component is added to a non realized Window, then the focus
     *  request will be made once the window is realized, since the
     *  ancestorAdded event will not be fired until then.
     *
     *  Using the default constructor will cause the listener to be removed
     *  from the component once the AncestorEvent is generated. A second constructor
     *  allows you to specify a boolean value of false to prevent the
     *  AncestorListener from being removed when the event is generated. This will
     *  allow you to reuse the listener each time the event is generated.
     */
    public class RequestFocusListener implements AncestorListener
    {
	    private boolean removeListener;

	    /*
	     *  Convenience constructor. The listener is only used once and then it is
	     *  removed from the component.
	     */
	    public RequestFocusListener()
	    {
		    this(true);
	    }

	    /*
	     *  Constructor that controls whether this listen can be used once or
	     *  multiple times.
	     *
	     *  @param removeListener when true this listener is only invoked once
	     *                        otherwise it can be invoked multiple times.
	     */
	    public RequestFocusListener(boolean removeListener)
	    {
		    this.removeListener = removeListener;
	    }

	    @Override
	    public void ancestorAdded(AncestorEvent e)
	    {
		    JComponent component = e.getComponent();
		    component.requestFocusInWindow();

		    if (removeListener)
			    component.removeAncestorListener( this );
	    }

	    @Override
	    public void ancestorMoved(AncestorEvent e) {}

	    @Override
	    public void ancestorRemoved(AncestorEvent e) {}
    }

}
