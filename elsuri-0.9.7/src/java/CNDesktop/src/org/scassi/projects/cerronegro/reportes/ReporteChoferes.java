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
 * ReporteChoferes.java
 * Written by Santiago Cassina - SCasSI
 * August, 2013
 * */

 package org.scassi.projects.cerronegro.reportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

import org.scassi.projects.cerronegro.avl.classes.Servicio;

public class ReporteChoferes extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(500, new int[]{ 68, 56, 140, 145, 62, 583, 53, 48, 41}, "Servicios", dataRow) {

	@Override
	public void finishLoading() {
	}
    };

    private TFInput tfDesde = new TFInput(DataTypes.SIMPLEDATE, "Desde", false);
    private TFInput tfHasta = new TFInput(DataTypes.SIMPLEDATE, "Hasta", false);
    private AssignButton btnUpdateChoferes = new AssignButton();
    private CBInput cbChoferes = new CBInput(0,"Choferes",false);

    private BasicDialog parentContainer;
    private Servicio servicio;
    private FindButton btnFind = new FindButton();

    private TFInput tfServicios = new TFInput(DataTypes.INTEGER, "Servicios", false);
    private TFInput tfTiempoTotal = new TFInput(DataTypes.STRING, "Tiempo", false);
    private TFInput tfRecaudacion = new TFInput(DataTypes.MONEY, "Recaudación", false);
    private TFInput tfBoletos = new TFInput(DataTypes.INTEGER, "Boletos", false);
    private TFInput tfInspecciones = new TFInput(DataTypes.INTEGER, "Inspecciones", false);

    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpSouth = new BasicPanel();
    
    private BasicPanel jpFilters = new BasicPanel();
    private BasicPanel jpTotals = new BasicPanel();
    private BasicPanel jpListPanel = new BasicPanel();

    public ReporteChoferes() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	jpCenter.setLayout(new BorderLayout());
	jpSouth.setLayout(null);
	jpSouth.setPreferredSize(new Dimension(1, 40));
	jpFilters.setLayout(null);
	jpFilters.setPreferredSize(new Dimension(1, 50));
	jpTotals.setLayout(null);
	jpTotals.setPreferredSize(new Dimension(1, 50));
	jpListPanel.setLayout(new BorderLayout());
	jpCenter.setBorder(BorderPanel.getBorderPanel("Servicio"));
	this.setSize(new Dimension(682, 540));
	tfDesde.setBounds(new Rectangle(5, 5, 80, 35));
	tfHasta.setBounds(new Rectangle(90, 5, 80, 35));
	btnFind.setBounds(new Rectangle(510, 15, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	btnUpdateChoferes.setBounds(new Rectangle(175, 15, 30, 25));
	btnUpdateChoferes.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnUpdateChoferes_actionPerformed(e);
			       }

			   }
	);
	cbChoferes.setBounds(new Rectangle(210, 5, 300, 35));

	tfServicios.setBounds(new Rectangle(5, 10, 90, 35));
	tfServicios.setEditable(false);
	tfTiempoTotal.setBounds(new Rectangle(100, 10, 280, 35));
	tfTiempoTotal.setEditable(false);
	tfRecaudacion.setBounds(new Rectangle(385, 10, 90, 35));
	tfRecaudacion.setEditable(false);
	tfBoletos.setBounds(new Rectangle(480, 10, 90, 35));
	tfBoletos.setEditable(false);
	tfInspecciones.setBounds(new Rectangle(575, 10, 90, 35));
	tfInspecciones.setEditable(false);

	jpListPanel.add(listPanel, BorderLayout.CENTER);
	jpCenter.add(jpListPanel, BorderLayout.CENTER);
	jpFilters.add(tfHasta, null);
	jpFilters.add(btnFind, null);
	jpFilters.add(cbChoferes, null);
	jpFilters.add(btnUpdateChoferes, null);
	jpFilters.add(tfDesde, null);
	jpCenter.add(jpFilters, BorderLayout.NORTH);
	jpTotals.add(tfServicios, null);
	jpTotals.add(tfTiempoTotal, null);
	jpTotals.add(tfRecaudacion, null);
	jpTotals.add(tfBoletos, null);
	jpTotals.add(tfInspecciones, null);
	jpCenter.add(jpTotals, BorderLayout.SOUTH);
	this.add(jpCenter, BorderLayout.CENTER);
	this.add(jpSouth, BorderLayout.SOUTH);
	servicio = new Servicio();
	//cbBuses.setGeneralItem(true);
	//tfDesde.setValue("");
	//tfHasta.setValue("");
	tfDesde.setToday();
	tfHasta.setToday();

	setHeaderList();

	listPanel.getTable().setDefaultRenderer(Number.class, new DCellRenderer());
	listPanel.getTable().setDefaultRenderer(Double.class, new DCellRenderer());
	listPanel.getTable().setDefaultRenderer(Object.class, new DCellRenderer());

    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("ID Servicio");
	headerList.addElement("* ID Bus");
	headerList.addElement("Interno");
	headerList.addElement("* ID Equipo");
	headerList.addElement("* ID Línea");
	headerList.addElement("* ID Ruta");
	headerList.addElement("* Legajo Chofer");
	headerList.addElement("Apertura");
	headerList.addElement("Cierre");
	headerList.addElement("Tiempo");
	headerList.addElement("Nº Serv.");
	headerList.addElement("Boletos");
	headerList.addElement("Recaudación");
	headerList.addElement("Inspecciones");
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    //loadData();
		} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    loadData();
		}
	    }
	});
	listPanel.setParams("reportes.getReporteServiciosPorChofer", "-1, '', ''", headerList);
    }

    public void refresh() {
	clearData();
	listPanel.refresh(cbChoferes.getSelectedValue() + "," + tfDesde.getDateForSQL() + "," + tfHasta.getDateForSQL());
	ResultSet _totals = LibSQL.exFunction("reportes.getresumenserviciosporchofer", cbChoferes.getSelectedValue() + "," + tfDesde.getDateForSQL() + "," + tfHasta.getDateForSQL());
	try {
	    if (_totals.next()) {
	        tfServicios.setValue(_totals.getInt("servicios"));
	        tfTiempoTotal.setValue(_totals.getString("tiempo"));
		tfTiempoTotal.setHorizontalAlignment(TFInput.CENTER);
	        tfRecaudacion.setValue(_totals.getDouble("recaudacion"));
	        tfBoletos.setValue(_totals.getInt("boletos"));
	        tfInspecciones.setValue(_totals.getInt("inspecciones"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
    }
    
    private void loadData(){
	if (dataRow.size() > 0) {
	}
    }

    private void clearData() {
	servicio = new Servicio();
	tfServicios.setValue("");
	tfTiempoTotal.setValue("");
	tfRecaudacion.setValue("");
	tfBoletos.setValue("");
	tfInspecciones.setValue("");
    }
    
    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnUpdateChoferes_actionPerformed(ActionEvent e) {
	cbChoferes.loadJCombo("reportes.getAllChoferesConServicio", tfDesde.getDateForSQL() + "," + tfHasta.getDateForSQL());
    }

    private class DCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
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
	    } else {
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		setHorizontalAlignment(LEFT);
	    }
	    // Set the background color
	    if (selected) {
		setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	    } else {
		setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	    }

	    // Set the background color
	    if (column  == 6) {
	        return new EtapasCell(value.toString());
	    }
	    
	    /*if (true) {
	    setValue("personas");
	    } else {
	    setValue("inspecciones");
	    }*/
	    
	    return this;
	}
    }

    private class EtapasCell extends JPanel {

	/* # Etapa, # Boletos, # Inspecciones, ID Color: 0=Sin Info; 1=Verde; 2=Amarillo; 3=Rojo */

	private Color[] colors = { Color.white, Color.green, Color.yellow, Color.red };

	private String test = "1,108,10,1; " +
					" 2,109,9,2; " +
					" 3,108,10,3; " +
					" 4,108,10,3; " +
					" 5,108,10,2; " +
					" 6,108,10,0; " +
					" 7,108,10,0; " +
					" 8,108,10,1";

	public EtapasCell(String params) {
	    String[] etapas = test.split(";");
	    int cantEtapas = etapas.length;
	    this.setLayout(new GridLayout(1, cantEtapas));
	    for (int i = 0; i < cantEtapas; i++) {
		String[] datosEtapa = etapas[i].split(",");
		JLabel etapa = new JLabel(" " + Math.abs((new Random().nextInt(255))) + " ");
		etapa.setHorizontalAlignment(JLabel.RIGHT);
		etapa.setOpaque(true);
		etapa.setBorder(new LineBorder(Color.black, 1));
		etapa.setBackground(colors[Integer.parseInt(datosEtapa[3].toString())]);
		add(etapa);
	    }
	}
    }
}
