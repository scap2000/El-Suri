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
 * DDManGIS.java
 *
 * */
package org.digitall.projects.ddmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.util.Vector;

import javax.swing.JInternalFrame;

import org.digitall.apps.gaia.components.GaiaPuntoPruebaPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaCadastralFinderPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaParcelPanel;
import org.digitall.apps.gaia.entities.streets.GaiaAddressReenumerationPanel;
import org.digitall.apps.gaia.entities.streets.GaiaStreetsPanel;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoComercios;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.RevisionPublicidadPropaganda;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.common.geo.mapping.components.LayerTree;
import org.digitall.common.geo.mapping.components.MapBasicTools;
import org.digitall.common.geo.mapping.components.MapBasicToolsPanel;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.GaiaEngine;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerConfigList;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;

public class DDManGIS extends BasicPrimitivePanel {

    BasicDrawEngine cityMap = new BasicDrawEngine("Recaudacion", new BasicLabel());

    public DDManGIS() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	Thread _thread = new Thread(new Runnable() {
	       public void run() {
		    loadGaia(Environment.getActiveDesktop());
	       }
	});
	try {
	    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(true);
	    Environment.jpStatusBar.setAction("Cargando Histórico Recaudación");
	    _thread.start();
	} catch (Exception x) {
	    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(false);
	    Environment.jpStatusBar.setAction("Listo...");
	    x.printStackTrace();
	}
    }

    private boolean loadGaia(BasicDesktop _desktop) {
	boolean returns = false;
	try {
	    long _time = System.currentTimeMillis();
	    GaiaEnvironment.initialize();
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    cityMap.setMapExtents(3548172.1937, 7249881.0068, 3571654.0741, 7268573.5261);
	    CoordinateViewer coordinateViewer = new CoordinateViewer();
	    coordinateViewer.setClosable(false);
	    cityMap.setCoordinateViewer(coordinateViewer);
	    coordinateViewer.setTitle("");

	    cityMap.setBounds(Environment.getActiveDesktop().getBounds());
	    cityMap.setVisible(true);
            
            if (!GaiaEnvironment.gaiaEngine.initialize()) {
		GeometrySet gsetSecciones = new GeometrySet("gis_salta", "secciones", "the_geom", "1=1", "gid");
		GeometrySet gsetBarrios = new GeometrySet("gis_salta", "barrios", "the_geom", "1=1", "gid");
		GeometrySet gsetCalles = new GeometrySet("gis_salta", "calles", "the_geom", "1=1", "idcalle");
		GeometrySet gsetManzanas = new GeometrySet("gis_salta", "manzanas_vinculadas", "the_geom", "1=1", "gid");
		new GeometrySet("gis_salta", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
		new GeometrySet("gis_salta", "estructuras", "the_geom", "1=1", "gid");
		new GeometrySet("gis_salta", "direcciones", "the_geom", "1=1", "gid");
		new GeometrySet("gis_salta", "zonas_reparto", "the_geom", "1=1", "gid");
		new GeometrySet("gea_salta", "censo_comercial_2009_seccion1", "the_geom", "1=1/**/", "idencuestacomercial", false);

		LayerGroup baseMapsGroup = new LayerGroup("Cartografía Base");
		
		Layer secciones = new Layer("Secciones", gsetSecciones);
		secciones.setColor(new Color(204, 0, 204));
		secciones.getLayerConfig().getStyleConfig().setLineWidth(3);
		secciones.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
		secciones.setMouseOverColor(Color.ORANGE);
		secciones.setOn();
		
		Layer manzanas = new Layer("Manzanas", gsetManzanas, "nombre");
		manzanas.setColor(new Color(153, 153, 153));
		manzanas.setFillColor(new Color(255, 255, 0));
		manzanas.getLayerConfig().getStyleConfig().setTransparency(100 - 70);
		manzanas.setMouseOverColor(Color.MAGENTA);
		manzanas.setEditable(true);
		
		Layer barrios = new Layer("Barrios", gsetBarrios, "nombre");
		barrios.setColor(new Color(0, 0, 255));
		barrios.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
		barrios.getLayerConfig().getStyleConfig().setLabelColor(new Color(51, 51, 255));
		barrios.setFillColor(new Color(153, 153, 255));
		barrios.getLayerConfig().getStyleConfig().setLineWidth(3);
		barrios.getLayerConfig().getStyleConfig().setTransparency(100 - 80); //20% de opacidad
		barrios.setMouseOverColor(Color.CYAN);
		barrios.setQueryable(true);
		
		Layer calles = new Layer("Calles", gsetCalles, "nombre");
		calles.setColor(Color.lightGray);
		calles.setMouseOverColor(Color.CYAN);
		calles.setQueryable(true);
		
		GeometrySet gsetPoligonos = new GeometrySet("gis", "_poligonos", "the_geom", "1=1", "gid");
		GeometrySet gsetPuntos = new GeometrySet("gis", "_puntos", "the_geom", "1=1", "gid");
		GeometrySet gsetPolilineas = new GeometrySet("gis", "_polilineas", "the_geom", "1=1", "gid");
		
		Layer _puntos = new Layer("Puntos", gsetPuntos, "nombre");
		_puntos.setColor(Color.GREEN);
		_puntos.setFillColor(Color.BLUE);
		_puntos.setMouseOverColor(Color.MAGENTA);
		_puntos.setPointDiameter(4);
		_puntos.setQueryable(true);
		_puntos.setModifiable(true);
		
		Layer _polilineas = new Layer("Polilíneas", gsetPolilineas, "nombre");
		_polilineas.setColor(Color.BLACK);
		_polilineas.setMouseOverColor(Color.CYAN);
		_polilineas.setQueryable(true);
		
		Layer _poligonos = new Layer("Poligonos", gsetPoligonos, "nombre");
		_poligonos.setColor(new Color(204, 0, 204));
		_poligonos.getLayerConfig().getStyleConfig().setLineWidth(3);
		_poligonos.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
		_poligonos.setMouseOverColor(Color.ORANGE);
		
		LayerGroup experimentalGroup = new LayerGroup("Experimental");
		
		GaiaEnvironment.layerGroups.add(baseMapsGroup);
		GaiaEnvironment.layerGroups.add(experimentalGroup);
		GaiaEnvironment.gaiaEngine.setLayerGroupList(GaiaEnvironment.layerGroups);
		
		baseMapsGroup.add(secciones);
		baseMapsGroup.add(manzanas);
		baseMapsGroup.add(barrios);
		baseMapsGroup.add(calles);
		
		experimentalGroup.add(_poligonos);
		experimentalGroup.add(_puntos);
		experimentalGroup.add(_polilineas);
		
		Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
		geometrySets.add(gsetSecciones);
		//geometrySets.add(gsetParcelas);
		geometrySets.add(gsetBarrios);
		geometrySets.add(gsetCalles);
		geometrySets.add(gsetManzanas);
		//geometrySets.add(gsetEstructuras);
		
		GaiaEnvironment.geometrySets = geometrySets;

		for (int i = 0; i < GaiaEnvironment.layerGroups.size(); i++) {
		    LayerGroup _layerGroup = GaiaEnvironment.layerGroups.elementAt(i);
		    for (int j = 0; j < _layerGroup.size(); j++) {
			GaiaEnvironment.gaiaEngine.getLayerProfileList().add(new LayerProfile(_layerGroup.elementAt(j).getGeometrySetConfig().getName(), _layerGroup.getName(), _layerGroup.elementAt(j).getName()));
		    }
		}
            }
            
	    cityMap.initialize();

	    /*
	    GaiaEnvironment.nomencladorLayers.add(manzanas_vinculadas);
	    GaiaEnvironment.nomencladorLayers.add(calles);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_catastral);

	    GaiaEnvironment.cadastralLayers.add(parcelas_catastral);
	    GaiaEnvironment.cadastralLayers.add(manzanas_vinculadas);
	    GaiaEnvironment.cadastralLayers.add(calles);
            */
	    JInternalFrame layerListFrame = new JInternalFrame("Listado de Layers");
	    layerListFrame.setClosable(false);
	    LayerTree layerTree = new LayerTree();
	    layerListFrame.getContentPane().add(layerTree, null);
	    layerListFrame.setBounds(0, 0,  350, 150);
	    layerListFrame.setResizable(true);
	    layerListFrame.setVisible(false);
	    layerTree.setDrawPanel(cityMap);

	    MapBasicTools mapBasicToolsFrame = new MapBasicTools();
	    mapBasicToolsFrame.setVertical();
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.ZOOM_IN_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.ZOOM_OUT_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.ZOOM_EXTENTS_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.RULE_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.QUERY_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.MULTIQUERY_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.FIXED_POLYGON_QUERY_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.PRINT_TOOL);
	    
	    mapBasicToolsFrame.setClosable(false);

	    RuleViewer ruleViewer = new RuleViewer();
	    ruleViewer.setClosable(false);
	    cityMap.setRuleViewer(ruleViewer);
	    ruleViewer.setTitle("");

	    //_desktop.setActive(true);
	    _desktop.add(coordinateViewer);
	    _desktop.add(ruleViewer);
	    _desktop.add(cityMap);
	    _desktop.add(layerListFrame);
	    _desktop.add(mapBasicToolsFrame);

	    ComponentsManager.setConfigurable(mapBasicToolsFrame);
	    ComponentsManager.setConfigurable(layerListFrame);
	    ComponentsManager.setConfigurable(coordinateViewer);

	    layerListFrame.show();
	    mapBasicToolsFrame.setDrawPanel(cityMap);
	    mapBasicToolsFrame.show();
    
	    coordinateViewer.setVisible(false);

	    GaiaCadastralFinderPanel cadastralFinderPanel = new GaiaCadastralFinderPanel();
	    cadastralFinderPanel.setDesktop(_desktop);
	    cadastralFinderPanel.setDrawEngine(cityMap);
	    cadastralFinderPanel.setVisible(false);
	    cadastralFinderPanel.start();
	    _desktop.setBottomRightComponent(cadastralFinderPanel);
	    cityMap.setBottomRightComponent(cadastralFinderPanel);
	    cityMap.setBottomLeftComponent(layerListFrame);
	    cityMap.setTopRightComponent(mapBasicToolsFrame);

	    //Restore configuration
	    //cityMap.setOperation(cityMap.getEngineConfig().getCurrentOperation());
	    coordinateViewer.setVisible(cityMap.getEngineConfig().isCoordinateViewerVisible());

	    System.out.println( ((System.currentTimeMillis()-_time) / 1000) + " segundos de demora");
	    returns = true;
	} catch (Exception x) {
	    Advisor.printException(x);
	}
	_desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	Environment.jpStatusBar.setIndeterminate(false);
	Environment.jpStatusBar.setAction("Listo...");
	return returns;
    }
}
