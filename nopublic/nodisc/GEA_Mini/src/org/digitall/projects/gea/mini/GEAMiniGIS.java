package org.digitall.projects.gea.mini;

import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;

import java.util.Vector;

import javax.swing.JInternalFrame;

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
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;

public class GEAMiniGIS extends BasicPrimitivePanel {

    BasicDrawEngine cityMap = new BasicDrawEngine("Recaudacion", new BasicLabel());

    public GEAMiniGIS() {
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
	    Environment.jpStatusBar.setAction("Cargando Mini GIS Demo");
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
	_desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	try {
	    long _time = System.currentTimeMillis();
	    GaiaEnvironment.initialize();
	    cityMap.setMapExtents(3548172.1937, 7249881.0068, 3571654.0741, 7268573.5261);
	    CoordinateViewer coordinateViewer = new CoordinateViewer();
	    coordinateViewer.setClosable(false);
	    cityMap.setCoordinateViewer(coordinateViewer);
	    coordinateViewer.setTitle("");

	    cityMap.setBounds(Environment.getActiveDesktop().getBounds());
	    cityMap.setVisible(true);

	    if (!GaiaEnvironment.gaiaEngine.initialize()) {
		GeometrySet gsetSecciones = new GeometrySet("gis_salta", "secciones", "the_geom", "1=1", "gid");
		//GeometrySet gsetParcelas = new GeometrySet("gis_salta", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
		GeometrySet gsetBarrios = new GeometrySet("gis_salta", "barrios", "the_geom", "1=1", "gid");
		GeometrySet gsetCalles = new GeometrySet("gis_salta", "calles", "the_geom", "1=1", "idcalle");
		GeometrySet gsetManzanas = new GeometrySet("gis_salta", "manzanas_vinculadas", "the_geom", "1=1", "gid");
		//GeometrySet gsetEstructuras = new GeometrySet("gis_salta", "estructuras", "the_geom", "1=1", "gid");

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
		_puntos.setDrawGeometries(false);
    
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

	    JInternalFrame layerListFrame = new JInternalFrame("Listado de Layers");
	    layerListFrame.setClosable(false);
	    LayerTree layerTree = new LayerTree();
	    layerListFrame.getContentPane().add(layerTree, null);
	    layerListFrame.setBounds(0, 0, 350, 150);
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
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.LAYER_EDITION_TOOL);
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

	    cityMap.setBottomLeftComponent(layerListFrame);
	    cityMap.setTopRightComponent(mapBasicToolsFrame);

	    //Restore configuration
	    //cityMap.setOperation(cityMap.getEngineConfig().getCurrentOperation());
	    coordinateViewer.setVisible(cityMap.getEngineConfig().isCoordinateViewerVisible());

	    System.out.println(((System.currentTimeMillis() - _time) / 1000) + " segundos de demora");
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
