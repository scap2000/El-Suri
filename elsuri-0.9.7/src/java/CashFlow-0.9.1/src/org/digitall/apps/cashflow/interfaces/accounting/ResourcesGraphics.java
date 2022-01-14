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
 * ResourcesGraphics.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accounting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;


public class ResourcesGraphics extends BasicPrimitivePanel{

    private BasicPanel findPanel = new BasicPanel();
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE,"From",false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE,"To",false);
    private TFInput tfFindResource = new TFInput(DataTypes.STRING,"Buscar Recurso",false);
    private CBInput cbResources = new CBInput(0,"Recursos",false);
    private CBInput cbSelectedResources = new CBInput(0,"Recursos seleccionados",false);
    private PrintButton btnResourcesGraphics = new PrintButton();
    private AddComboButton btnAddResource = new AddComboButton();
    private DeleteButton btnDeleteResource = new DeleteButton();
    private Vector idRecursosFavoritos = new Vector();

    public ResourcesGraphics() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(537, 204));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 115));
	findPanel.setSize(new Dimension(739, 70));
	tfStartDate.setBounds(new Rectangle(15, 25, 95, 35));
	btnResourcesGraphics.setBounds(new Rectangle(500, 170, 20, 20));
	tfEndDate.setBounds(new Rectangle(170, 25, 95, 35));
	tfFindResource.setBounds(new Rectangle(15, 75, 140, 35));
	cbResources.setBounds(new Rectangle(170, 75, 320, 35));
	cbSelectedResources.setBounds(new Rectangle(170, 115, 320, 35));
        btnAddResource.setBounds(new Rectangle(500, 90, 20, 20));
	btnAddResource.setSize(new Dimension(20, 20));
        btnAddResource.setToolTipText("Agregar recurso para reporte gráfico");
	btnAddResource.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAddResource_actionPerformed(e);
				 }

			     }
	);
	btnDeleteResource.setBounds(new Rectangle(500, 130, 20, 20));
        //btnDeleteResource.setToolTipText("");
	btnDeleteResource.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDeleteResource_actionPerformed(e);
				 }

			     }
	);
        findPanel.add(btnDeleteResource, null);
        findPanel.add(btnAddResource, null);
        findPanel.add(cbSelectedResources, null);
        findPanel.add(cbResources, null);
        findPanel.add(tfFindResource, null);
        findPanel.add(tfEndDate, null);
	findPanel.add(tfStartDate, null);
	//findPanel.add(btnResourcesGraphics, null);
	this.add(findPanel, BorderLayout.CENTER);
        this.addButton(btnResourcesGraphics);
	cbResources.autoSize();
	cbSelectedResources.autoSize();
	
	tfFindResource.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 loadComboResources(tfFindResource.getString(), cbResources);
		     }
		 }

	     }
	);

        tfStartDate.setValue(Proced.setFormatDate(Environment.currentYear +"-01-01", true));
	tfEndDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	btnAddResource.setToolTipText("Agregar recurso");
	btnDeleteResource.setToolTipText("Borrar recurso seleccionado");
        btnResourcesGraphics.setToolTipText("Imprimir Gráfico de recurso(s)");
	btnResourcesGraphics.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnResourcesGraphics_actionPerformed(e);
		}
	    });
	findPanel.setBorder(BorderPanel.getBorderPanel("Gráfico de recurso(s)"));
        btnAddResource.setEnabled(false);
        btnDeleteResource.setEnabled(false);
        cargarRecursosFavoritos();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede generar gráficos de uno o varios ítems combinados");
    }
    
    private void loadComboResources(String _text, CBInput _combo){
	String param = "'" + _text + "',0";
	_combo.loadJCombo(LibSQL.exFunction("resourcescontrol.getallrecursosbyfilter", param)); 
        if (_combo.getCombo().getItemCount() > 0) {
            btnAddResource.setEnabled(true);
        } else {
            btnAddResource.setEnabled(false);
        }
    }
    
    private void btnAddResource_actionPerformed(ActionEvent e) {
	agregarRecursoFavorito(e);
    }

    private void btnDeleteResource_actionPerformed(ActionEvent e) {
        borrarRecursoSeleccionado(e);
    }

    private void btnResourcesGraphics_actionPerformed(ActionEvent e) {
	generarGraficoRecursos();
    }
    
    private void generarGraficoRecursos() {
        idRecursosFavoritos = cbSelectedResources.getCombo().getValuesVector();
        if (tfStartDate.getDate().length()>0 && tfEndDate.getDate().length()>0) {
            if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false),Proced.setFormatDate(tfEndDate.getDate(),false)) == 2) {
                Advisor.messageBox("La fecha desde no puede ser mayor a la fecha hasta","Error");
            } else {     
                if (idRecursosFavoritos.size() > 0) {
                    ExtendedInternalFrame _multiQueryDialog = new ExtendedInternalFrame("Gráfico de recurso(s)");
                    _multiQueryDialog.setSize(400, 300);
                    JArea _multiQuery = new JArea();
                    _multiQuery.setContentType("text/html");
                    _multiQuery.setEditable(false);
                    //guardar cuentas favoritas
                    TimeSeriesCollection _dataset = new TimeSeriesCollection();
                    for (int i = 0; i < idRecursosFavoritos.size();i++) {
                        String _params =  idRecursosFavoritos.elementAt(i) + ",'" + Proced.setFormatDate(tfStartDate.getDate(), false) +"','"+ Proced.setFormatDate(tfEndDate.getDate(), false) +"', 0, 0";
                        ResultSet _results = LibSQL.exFunction("resourcescontrol.getmonthlyresource", _params);
                        cbSelectedResources.getCombo().setSelectedIndex(i);
                        TimeSeries _serie = new TimeSeries(cbSelectedResources.getSelectedItem().toString(), Month.class);
                        try {
                            while (_results.next()) {
                                _serie.add(new Month(_results.getInt("month"), _results.getInt("year")), _results.getInt("monto"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                        }
                        _dataset.addSeries(_serie);
                    }
                    JFreeChart _timeSeriesChart = ChartFactory.createTimeSeriesChart("Gráfico de recursos(s)", "Mes", "Monto", _dataset, true, true, false);
                    ChartPanel _chartPanel = new ChartPanel(_timeSeriesChart);
                    XYPlot plot = (XYPlot) _timeSeriesChart.getPlot();
                    plot.setDomainGridlinePaint(Color.black);
                    //plot.setRangeGridlinePaint(Color.black);
                    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
                    plot.setDomainCrosshairVisible(true);
                    plot.setRangeCrosshairVisible(true);

                    XYItemRenderer r = plot.getRenderer();
                    if (r instanceof XYLineAndShapeRenderer) {
                        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
                        renderer.setShapesVisible(true);
                        renderer.setShapesFilled(true);
                    }
                    _multiQueryDialog.setCentralPanel(_chartPanel);
                    _multiQueryDialog.setMaximizable(true);
                    _multiQueryDialog.setClosable(true);
                    _multiQueryDialog.setResizable(true);
                    _multiQueryDialog.setVisible(true);

                } else {
                    Advisor.messageBox("Recurso(s) requerido(s)","Aviso");
                }
            }
            
        } else{
            Advisor.messageBox("Rango de fechas requerido","Aviso");
        }
    }

    public void setidRecursosFavoritos(Vector idRecursosFavoritos) {
        this.idRecursosFavoritos = idRecursosFavoritos;
    }

    public Vector getidRecursosFavoritos() {
        return idRecursosFavoritos;
    }

    private void agregarRecursoFavorito(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            if (Advisor.question("Aviso", "¿Está seguro de agregar todos los recursos?")) {
                cbSelectedResources.removeAllItems();
                idRecursosFavoritos = cbResources.getCombo().getValuesVector();
                Vector _refRecursosFavoritos = cbResources.getCombo().getRefValuesVector();
                Vector _recursosFavoritos = cbResources.getCombo().getItemsVector();
                for (int i = 0; i < cbResources.getCombo().getItemCount(); i++) {
                    cbSelectedResources.getCombo().addItem(_recursosFavoritos.elementAt(i), idRecursosFavoritos.elementAt(i), _refRecursosFavoritos.elementAt(i));
                }
                guardarRecursosFavoritos();
                btnDeleteResource.setEnabled(true);
                btnResourcesGraphics.setEnabled(true);
            }
        } else {
            if (!cbSelectedResources.getCombo().getItemsVector().contains(cbResources.getSelectedItem())) {
                cbSelectedResources.getCombo().addItem(cbResources.getSelectedItem(), cbResources.getSelectedValue(), cbResources.getSelectedValueRef());
                btnDeleteResource.setEnabled(true);
                btnResourcesGraphics.setEnabled(true);
                idRecursosFavoritos = cbSelectedResources.getCombo().getValuesVector();
                guardarRecursosFavoritos();
            } else {
                Advisor.messageBox("El recurso \"" + cbResources.getSelectedItem() +  "\" ya fue agregado","Aviso");
            }
        }
    }

    private void borrarRecursoSeleccionado(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            if (Advisor.question("Aviso", "¿Está seguro de borrar todos los recursos seleccionados?")) {
                cbSelectedResources.removeAllItems();
                guardarRecursosFavoritos();
            }
        } else {
            cbSelectedResources.removeItemAt(cbSelectedResources.getSelectedIndex());
        }
        if (cbSelectedResources.getCombo().getItemCount() <= 0) {
            btnDeleteResource.setEnabled(false);
            btnResourcesGraphics.setEnabled(false);
            guardarRecursosFavoritos();
        }
    }

    private void guardarRecursosFavoritos() {
        String _recursosFavoritos = "";
        for (int i = 0; i < idRecursosFavoritos.size();i++) {
            _recursosFavoritos += idRecursosFavoritos.elementAt(i).toString() + ",";
        }
        Environment.cfg.setProperty("recursosfavoritos",_recursosFavoritos);
    }

    private void cargarRecursosFavoritos() {
	if (Environment.cfg.hasProperty("recursosfavoritos")) {
	    String _recursosFavoritos = Environment.cfg.getProperty("recursosfavoritos");
	    if (_recursosFavoritos.length() > 0) {
		String[] _idRecursosFavoritos = _recursosFavoritos.split(",");
		for (int i=0; i < _idRecursosFavoritos.length; i++) {
		    idRecursosFavoritos.add(_idRecursosFavoritos[i]);
                    Resource recurso = new Resource();
                    recurso.setIdResource(Integer.parseInt(idRecursosFavoritos.elementAt(i).toString()));
                    recurso.retrieveData();
                    cbSelectedResources.getCombo().addItem( recurso.getName(), recurso.getIdResource(), 0);
		}
		btnDeleteResource.setEnabled(true);
	    }
	}
    }
}
