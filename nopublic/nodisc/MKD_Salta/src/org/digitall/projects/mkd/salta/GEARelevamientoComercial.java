package org.digitall.projects.mkd.salta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JInternalFrame;

import org.digitall.apps.gaia.entities.parcels.GaiaCadastralFinderPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaParcelPanel;
import org.digitall.apps.gaia.entities.streets.GaiaAddressReenumerationPanel;
import org.digitall.apps.gaia.entities.streets.GaiaStreetsPanel;
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
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormCensoComercial2009;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoComercios;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoPublicidad;

public class GEARelevamientoComercial extends BasicPrimitivePanel {

    BasicDrawEngine cityMap = new BasicDrawEngine("Relevamiento Comercial", new BasicLabel());

    public GEARelevamientoComercial() {
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
	    Environment.jpStatusBar.setAction("Cargando Relevamiento Comercial");
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
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    cityMap.setMapExtents(3548172.1937, 7249881.0068, 3571654.0741, 7268573.5261);
	    CoordinateViewer coordinateViewer = new CoordinateViewer();
	    coordinateViewer.setClosable(false);
	    cityMap.setCoordinateViewer(coordinateViewer);
	    coordinateViewer.setTitle("");

	    cityMap.setBounds(Environment.getActiveDesktop().getBounds());
	    cityMap.setVisible(true);

	    GeometrySet gsetSecciones = new GeometrySet("gis_salta", "secciones", "the_geom", "1=1", "gid");
	    GeometrySet gsetParcelas = new GeometrySet("gis_salta", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetBarrios = new GeometrySet("gis_salta", "barrios", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetComercios = new GeometrySet("gea_salta", "censo_comercial_2009_seccion1", "the_geom", "1=1/* AND st_isvalid(the_geom)*/", "idencuestacomercial");
	    GeometrySet gsetCalles = new GeometrySet("gis_salta", "calles", "the_geom", "1=1", "idcalle");
	    GeometrySet gsetManzanas = new GeometrySet("gis_salta", "manzanas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
	    GeometrySet gsetEstructuras = new GeometrySet("gis_salta", "estructuras", "the_geom", "1=1", "gid");
	    GeometrySet gsetAutopistasLineas2009 = new GeometrySet("gis_salta", "autopistas_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetAutopistasProyectadas2009 = new GeometrySet("gis_salta", "autopistas_proyectadas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCanalesLineas2009 = new GeometrySet("gis_salta", "canales_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCastanaresGruposPoligonos2009 = new GeometrySet("gis_salta", "castanares_grupos_poligonos_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCastanaresGruposTextos2009 = new GeometrySet("gis_salta", "castanares_grupos_textos_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCerrillosCallesTextos2009 = new GeometrySet("gis_salta", "cerrillos_calles_texto_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCerrillosInformatTexto2009 = new GeometrySet("gis_salta", "cerrillos_informat_texto_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetCerrillosManzanasLineas2009 = new GeometrySet("gis_salta", "cerrillos_manzanas_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetEspaciosVerdesPoligono2009 = new GeometrySet("gis_salta", "espacios_verdes_poligono_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetFFCC2009 = new GeometrySet("gis_salta", "ffcc_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetInfraestructuraTextos2009 = new GeometrySet("gis_salta", "infraestructura_textos_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetLimiteDeptoLineas2009 = new GeometrySet("gis_salta", "limite_dpto_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetLimiteDeptoTextos2009 = new GeometrySet("gis_salta", "limite_dpto_texto_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetLimiteEjidoMunicipalLineas2009 = new GeometrySet("gis_salta", "limite_ejido_municipal_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetLimiteEjidoMunicipalTextos2009 = new GeometrySet("gis_salta", "limite_ejido_municipal_texto_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetRutasNacionalesLineas2009 = new GeometrySet("gis_salta", "rutas_nacionales_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetRutasProvincialesLineas009 = new GeometrySet("gis_salta", "rutas_provinciales_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetSanLorenzoLineas2009 = new GeometrySet("gis_salta", "san_lorenzo_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetSanLorenzoTextos2009 = new GeometrySet("gis_salta", "san_lorenzo_textos_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetVaquerosLineas2009 = new GeometrySet("gis_salta", "vaqueros_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetViasRapidasLineas2009 = new GeometrySet("gis_salta", "vias_rapidas_lineas_2009", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
	    GeometrySet gsetDirecciones = new GeometrySet("gis_salta", "direcciones", "the_geom", "1=1", "gid");
	    GeometrySet gsetParcelasSA = new GeometrySet("gis_salta", "parcelas_sa", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSB = new GeometrySet("gis_salta", "parcelas_sb", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSC = new GeometrySet("gis_salta", "parcelas_sc", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSD = new GeometrySet("gis_salta", "parcelas_sd", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSE = new GeometrySet("gis_salta", "parcelas_se", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSF = new GeometrySet("gis_salta", "parcelas_sf", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSG = new GeometrySet("gis_salta", "parcelas_sg", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSH = new GeometrySet("gis_salta", "parcelas_sh", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSJ = new GeometrySet("gis_salta", "parcelas_sj", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSK = new GeometrySet("gis_salta", "parcelas_sk", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSL = new GeometrySet("gis_salta", "parcelas_sl", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSM = new GeometrySet("gis_salta", "parcelas_sm", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSN = new GeometrySet("gis_salta", "parcelas_sn", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSO = new GeometrySet("gis_salta", "parcelas_so", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSP = new GeometrySet("gis_salta", "parcelas_sp", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSQ = new GeometrySet("gis_salta", "parcelas_sq", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSR = new GeometrySet("gis_salta", "parcelas_sr", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSS = new GeometrySet("gis_salta", "parcelas_ss", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasST = new GeometrySet("gis_salta", "parcelas_st", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSU = new GeometrySet("gis_salta", "parcelas_su", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetParcelasSV = new GeometrySet("gis_salta", "parcelas_sv", "the_geom", "1=1 AND st_isvalid(the_geom)", "idparcela");
	    GeometrySet gsetPavimentacion = new GeometrySet("gis_salta", "pavimentacion", "the_geom", "1=1", "gid");
	    GeometrySet gsetCensoComercial2009 = new GeometrySet("gea_salta", "censo_comercial_2009_seccion1", "the_geom", "1=1/* AND st_isvalid(the_geom)*/", "idencuestacomercial");
	    GeometrySet gsetRelevamientoPublicidad2009 = new GeometrySet("gea_salta", "relevamientopublicidad_2009_detalle", "the_geom", "1=1/* AND st_isvalid(the_geom)*/", "iddetallerelevamiento");

	    GeometrySet gsetParcelas_MKD = new GeometrySet("gis_salta", "parcelas_mkd", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
	    GeometrySet gsetTextos_MKD = new GeometrySet("gis_salta", "txt_mkd", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");

	    cityMap.addGeometrySet(gsetSecciones);
	    cityMap.addGeometrySet(gsetParcelas);
	    cityMap.addGeometrySet(gsetBarrios);
	    cityMap.addGeometrySet(gsetComercios);
	    cityMap.addGeometrySet(gsetCalles);
	    cityMap.addGeometrySet(gsetManzanas);
	    cityMap.addGeometrySet(gsetEstructuras);
	    cityMap.addGeometrySet(gsetAutopistasLineas2009);
	    cityMap.addGeometrySet(gsetAutopistasProyectadas2009);
	    cityMap.addGeometrySet(gsetCanalesLineas2009);
	    cityMap.addGeometrySet(gsetCastanaresGruposPoligonos2009);
	    cityMap.addGeometrySet(gsetCastanaresGruposTextos2009);
	    cityMap.addGeometrySet(gsetCerrillosCallesTextos2009);
	    cityMap.addGeometrySet(gsetCerrillosInformatTexto2009);
	    cityMap.addGeometrySet(gsetCerrillosManzanasLineas2009);
	    cityMap.addGeometrySet(gsetEspaciosVerdesPoligono2009);
	    cityMap.addGeometrySet(gsetFFCC2009);
	    cityMap.addGeometrySet(gsetInfraestructuraTextos2009);
	    cityMap.addGeometrySet(gsetLimiteDeptoLineas2009);
	    cityMap.addGeometrySet(gsetLimiteDeptoTextos2009);
	    cityMap.addGeometrySet(gsetLimiteEjidoMunicipalLineas2009);
	    cityMap.addGeometrySet(gsetLimiteEjidoMunicipalTextos2009);
	    cityMap.addGeometrySet(gsetRutasNacionalesLineas2009);
	    cityMap.addGeometrySet(gsetRutasProvincialesLineas009);
	    cityMap.addGeometrySet(gsetSanLorenzoLineas2009);
	    cityMap.addGeometrySet(gsetSanLorenzoTextos2009);
	    cityMap.addGeometrySet(gsetVaquerosLineas2009);
	    cityMap.addGeometrySet(gsetViasRapidasLineas2009);
	    cityMap.addGeometrySet(gsetDirecciones);
	    cityMap.addGeometrySet(gsetParcelasSA);
	    cityMap.addGeometrySet(gsetParcelasSB);
	    cityMap.addGeometrySet(gsetParcelasSC);
	    cityMap.addGeometrySet(gsetParcelasSD);
	    cityMap.addGeometrySet(gsetParcelasSE);
	    cityMap.addGeometrySet(gsetParcelasSF);
	    cityMap.addGeometrySet(gsetParcelasSG);
	    cityMap.addGeometrySet(gsetParcelasSH);
	    cityMap.addGeometrySet(gsetParcelasSJ);
	    cityMap.addGeometrySet(gsetParcelasSK);
	    cityMap.addGeometrySet(gsetParcelasSL);
	    cityMap.addGeometrySet(gsetParcelasSM);
	    cityMap.addGeometrySet(gsetParcelasSN);
	    cityMap.addGeometrySet(gsetParcelasSO);
	    cityMap.addGeometrySet(gsetParcelasSP);
	    cityMap.addGeometrySet(gsetParcelasSQ);
	    cityMap.addGeometrySet(gsetParcelasSR);
	    cityMap.addGeometrySet(gsetParcelasSS);
	    cityMap.addGeometrySet(gsetParcelasST);
	    cityMap.addGeometrySet(gsetParcelasSU);
	    cityMap.addGeometrySet(gsetParcelasSV);
	    cityMap.addGeometrySet(gsetPavimentacion);
	    cityMap.addGeometrySet(gsetCensoComercial2009);
	    cityMap.addGeometrySet(gsetRelevamientoPublicidad2009);
	    cityMap.addGeometrySet(gsetParcelas_MKD);
	    cityMap.addGeometrySet(gsetTextos_MKD);

	    LayerGroup catastralGroup = new LayerGroup("Catastral");
	    LayerGroup parcelasVinculadasGroup = new LayerGroup("Parcelas Vinculadas");
	    LayerGroup infraestructuraGroup = new LayerGroup("Infraestructura");
	    LayerGroup relevamientoGroup = new LayerGroup("Relevamiento");
	    LayerGroup vialGroup = new LayerGroup("Vial");
	    LayerGroup municipiosGroup = new LayerGroup("Municipios");
	    LayerGroup limitesGroup = new LayerGroup("Límites");
	    LayerGroup consultasGroup = new LayerGroup("Consultas");

	    Layer secciones = new Layer("Secciones", gsetSecciones);
	    secciones.setColor(new Color(204, 0, 204));
	    secciones.getLayerConfig().getStyleConfig().setLineWidth(3);
	    secciones.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
	    secciones.setMouseOverColor(Color.ORANGE);

	    Layer calles = new Layer("Calles", gsetCalles, "nombre");
	    calles.setColor(Color.lightGray);
	    calles.setMouseOverColor(Color.CYAN);
	    calles.setQueryable(true);
	    GaiaEnvironment.setStreetsLayer(calles.getAlias());
	    GaiaEnvironment.setScheme("gis_salta");

	    Layer manzanas_vinculadas = new Layer("Manzanas", gsetManzanas, "nombre");
	    manzanas_vinculadas.setColor(new Color(153, 153, 153));
	    manzanas_vinculadas.setFillColor(new Color(255, 255, 0));
	    manzanas_vinculadas.getLayerConfig().getStyleConfig().setTransparency(100-70);
	    manzanas_vinculadas.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_catastral = new Layer("Parcelas (Catastral)", gsetParcelas);
	    parcelas_catastral.getLayerConfig().setDrawGeometries(false);
	    parcelas_catastral.setColor(Color.BLACK);
	    parcelas_catastral.setMouseOverColor(Color.MAGENTA);
	    parcelas_catastral.setQueryable(true);
	    GaiaEnvironment.setCadastralLayer(parcelas_catastral.getAlias());
	    ExtendedInternalFrame parcelPanelContainer = new ExtendedInternalFrame("Ventana de Información Parcelaria");
	    GaiaParcelPanel parcelPanel = new GaiaParcelPanel();
	    parcelPanelContainer.setCentralPanel(parcelPanel);
	    parcelas_catastral.setInfoPanel(parcelPanelContainer);

	    ExtendedInternalFrame streetsPanelContainer = new ExtendedInternalFrame("Calles");
	    GaiaStreetsPanel streetsPanel = new GaiaStreetsPanel();
	    streetsPanelContainer.setCentralPanel(streetsPanel);
	    calles.setConfigPanel(streetsPanelContainer);
	    
	    Layer pavimentacion = new Layer("Pavimentación", gsetPavimentacion);
	    pavimentacion.setColor(Color.BLACK);
	    pavimentacion.setMouseOverColor(Color.CYAN);
	    pavimentacion.setQueryable(true);
	    /*pavimentacion.addFilter("", "idtipo", "(idtipo = 1)", new Color(255, 0, 0), 3, "Pavimento 1");
	    pavimentacion.addFilter("", "idtipo", "(idtipo = 2)", new Color(70, 70, 70), 3, "Pavimento 2");*/
	    pavimentacion.addFilter("Todos los pavimentos", "idtipo", "(idtipo = 3)", new Color(102, 102, 102), 3, "Todos los pavimentos");
	    pavimentacion.addFilter("Cordón Cuneta", "idtipo", "(idtipo = 4)", new Color(255, 0, 0), 3, "Cordón Cuneta");
	    pavimentacion.addFilter("Tierra", "idtipo", "(idtipo = 5)", new Color(180, 100, 0), 3, "Tierra");

	    Layer estructuras_viales = new Layer("Estructuras", gsetEstructuras);
	    estructuras_viales.setColor(Color.BLACK);
	    estructuras_viales.setMouseOverColor(Color.CYAN);
	    estructuras_viales.setQueryable(true);
	    estructuras_viales.setToolTipLabel("Tipo de Estructura Vial");
	    estructuras_viales.addFilter("Ferrocarril", "idtipo", "(idtipo = 1)", new Color(0, 125, 0), 2, "Ferrocarril");
	    estructuras_viales.addFilter("Platabanda", "idtipo", "(idtipo = 2)", new Color(207, 207, 207), 1, "Platabanda");
	    estructuras_viales.addFilter("Calzada", "idtipo", "(idtipo = 3)", new Color(207, 207, 207), 1, "Calzada");
	    estructuras_viales.addFilter("Cordón Vereda", "idtipo", "(idtipo = 4)", new Color(207, 207, 207), 1, "Cordón Vereda");
	    estructuras_viales.addFilter("Canal", "idtipo", "(idtipo = 5)", new Color(160, 160, 200), 3, "Canal");
	    estructuras_viales.addFilter("Río", "idtipo", "(idtipo = 6 OR idtipo = 0)", new Color(70, 190, 230), 1, "Río");
	    estructuras_viales.addFilter("Espacio Verde", "idtipo", "(idtipo = 7)", new Color(0, 180, 90), 1, "Espacio Verde");
	    estructuras_viales.getLayerConfig().getFilters().elementAt(0).getStyleConfig().setOutlineColor(null);

	    Layer barrios = new Layer("Barrios", gsetBarrios, "nombre");
	    barrios.setColor(new Color(0, 0, 255));
	    barrios.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
	    barrios.getLayerConfig().getStyleConfig().setLabelColor(new Color(51, 51, 255));
	    barrios.setFillColor(new Color(153, 153, 255));
	    barrios.getLayerConfig().getStyleConfig().setLineWidth(3);
	    barrios.getLayerConfig().getStyleConfig().setTransparency(100-80); //20% de opacidad
	    barrios.setMouseOverColor(Color.CYAN);
	    barrios.setQueryable(true);

	    ExtendedInternalFrame formsInternalFrame = new ExtendedInternalFrame("Formularios");
	    formsInternalFrame.setDesktop(_desktop);
	    BasicPanel formPanel = new BasicPanel();
	    formsInternalFrame.setClosable(false);
	    formsInternalFrame.setIconifiable(false);
	    formPanel.setLayout(new BorderLayout());
	    formPanel.setSize(new Dimension(300,420));
	    GaiaEnvironment.formPanel = formPanel;
	    formsInternalFrame.setCentralPanel(formPanel);
	    GaiaEnvironment.formsFrame = formsInternalFrame;

	    Layer autopistas_lineas_2009 = new Layer("Autopistas (Líneas 2009)", gsetAutopistasLineas2009);
	    autopistas_lineas_2009.setColor(new Color(255, 153, 0));
	    autopistas_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(3);
	    autopistas_lineas_2009.setMouseOverColor(Color.CYAN);
	    autopistas_lineas_2009.setQueryable(true);

	    Layer autopistas_proyectadas_2009 = new Layer("Autopistas (Proyectadas 2009)", gsetAutopistasProyectadas2009);
	    autopistas_proyectadas_2009.setColor(new Color(204,204,204));
	    autopistas_proyectadas_2009.setMouseOverColor(Color.CYAN);
	    autopistas_proyectadas_2009.setQueryable(true);

	    Layer canales_lineas_2009 = new Layer("Canales (Líneas 2009)", gsetCanalesLineas2009);
	    canales_lineas_2009.setColor(new Color(0, 204, 255));
	    canales_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(2);
	    canales_lineas_2009.setMouseOverColor(Color.CYAN);
	    canales_lineas_2009.setQueryable(true);

	    Layer castanares_grupos_poligonos_2009 = new Layer("Castanares (Grupos Polígonos 2009)", gsetCastanaresGruposPoligonos2009);
	    castanares_grupos_poligonos_2009.setColor(new Color(255, 153, 51));
	    castanares_grupos_poligonos_2009.setFillColor(new Color(255, 0, 51, 154));
	    castanares_grupos_poligonos_2009.setMouseOverColor(Color.CYAN);
	    castanares_grupos_poligonos_2009.setQueryable(true);

	    Layer castanares_grupos_textos_2009 = new Layer("Castanares (Grupos Textos 2009)", gsetCastanaresGruposTextos2009);
	    castanares_grupos_textos_2009.setColor(new Color(0, 0, 255));
	    castanares_grupos_textos_2009.setMouseOverColor(Color.CYAN);
	    castanares_grupos_textos_2009.setQueryable(true);

	    Layer cerrillos_calles_texto_2009 = new Layer("Cerrillos (Calles Textos 2009)", gsetCerrillosCallesTextos2009);
	    cerrillos_calles_texto_2009.setColor(new Color(0, 0, 255));
	    cerrillos_calles_texto_2009.setMouseOverColor(Color.CYAN);
	    cerrillos_calles_texto_2009.setQueryable(true);

	    Layer cerrillos_informat_texto_2009 = new Layer("Cerrillos (Informat Texto 2009)", gsetCerrillosInformatTexto2009);
	    cerrillos_informat_texto_2009.setColor(new Color(0, 0, 255));
	    cerrillos_informat_texto_2009.setMouseOverColor(Color.CYAN);
	    cerrillos_informat_texto_2009.setQueryable(true);

	    Layer cerrillos_manzanas_lineas_2009 = new Layer("Cerrillos (Manzanas Líneas 2009)", gsetCerrillosManzanasLineas2009);
	    cerrillos_manzanas_lineas_2009.setColor(new Color(204, 204, 0));
	    cerrillos_manzanas_lineas_2009.setMouseOverColor(Color.CYAN);
	    cerrillos_manzanas_lineas_2009.setQueryable(true);

	    Layer espacios_verdes_poligono_2009 = new Layer("Espacios verdes (Polígono 2009)", gsetEspaciosVerdesPoligono2009);
	    espacios_verdes_poligono_2009.setColor(new Color(0, 153, 51));
	    espacios_verdes_poligono_2009.setFillColor(new Color(0, 153, 51));
	    espacios_verdes_poligono_2009.setMouseOverColor(Color.CYAN);
	    espacios_verdes_poligono_2009.setQueryable(true);

	    Layer ffcc_2009 = new Layer("FFCC (2009)", gsetFFCC2009);
	    ffcc_2009.setColor(new Color(0, 204, 153));
	    ffcc_2009.getLayerConfig().getStyleConfig().setLineWidth(2);
	    ffcc_2009.getLayerConfig().getStyleConfig().setStrokeStyle(1);
	    ffcc_2009.setMouseOverColor(Color.CYAN);
	    ffcc_2009.setQueryable(true);

	    Layer infraestructura_textos_2009 = new Layer("Infraestructura (Textos 2009)", gsetInfraestructuraTextos2009);
	    infraestructura_textos_2009.setColor(new Color(0, 0, 255));
	    infraestructura_textos_2009.setMouseOverColor(Color.CYAN);
	    infraestructura_textos_2009.setQueryable(true);

	    Layer limite_dpto_lineas_2009 = new Layer("Límite depto. (Líneas 2009)", gsetLimiteDeptoLineas2009);
	    limite_dpto_lineas_2009.setColor(new Color(0, 0, 0));
	    limite_dpto_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(4);
	    limite_dpto_lineas_2009.getLayerConfig().getStyleConfig().setStrokeStyle(6);
	    limite_dpto_lineas_2009.setMouseOverColor(Color.CYAN);
	    limite_dpto_lineas_2009.setQueryable(true);

	    Layer limite_dpto_texto_2009 = new Layer("Límite depto. (Texto 2009)", gsetLimiteDeptoTextos2009);
	    limite_dpto_texto_2009.setColor(new Color(0, 0, 255));
	    limite_dpto_texto_2009.setMouseOverColor(Color.CYAN);
	    limite_dpto_texto_2009.setQueryable(true);

	    Layer limite_ejido_municipal_lineas_2009 = new Layer("Límite Ejido Municipal (Líneas 2009)", gsetLimiteEjidoMunicipalLineas2009);
	    limite_ejido_municipal_lineas_2009.setColor(new Color(255, 0, 0));
	    limite_ejido_municipal_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(4);
	    limite_ejido_municipal_lineas_2009.getLayerConfig().getStyleConfig().setStrokeStyle(5);
	    limite_ejido_municipal_lineas_2009.setMouseOverColor(Color.CYAN);
	    limite_ejido_municipal_lineas_2009.setQueryable(true);

	    Layer limite_ejido_municipal_texto_2009 = new Layer("Límite Ejido Municipal (Textos 2009)", gsetLimiteEjidoMunicipalTextos2009);
	    limite_ejido_municipal_texto_2009.setColor(new Color(0, 0, 255));
	    limite_ejido_municipal_texto_2009.setMouseOverColor(Color.CYAN);
	    limite_ejido_municipal_texto_2009.setQueryable(true);

	    Layer rutas_nacionales_lineas_2009 = new Layer("Rutas_nacionales_lineas_2009", gsetRutasNacionalesLineas2009);
	    rutas_nacionales_lineas_2009.setColor(new Color(204, 0, 0));
	    rutas_nacionales_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(3);
	    rutas_nacionales_lineas_2009.setMouseOverColor(Color.CYAN);
	    rutas_nacionales_lineas_2009.setQueryable(true);

	    Layer rutas_provinciales_lineas_2009 = new Layer("rutas_provinciales_lineas_2009", gsetRutasProvincialesLineas009);
	    rutas_provinciales_lineas_2009.setColor(new Color(0, 102, 0));
	    rutas_provinciales_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(3);
	    rutas_provinciales_lineas_2009.setMouseOverColor(Color.CYAN);
	    rutas_provinciales_lineas_2009.setQueryable(true);

	    Layer san_lorenzo_lineas_2009 = new Layer("san_lorenzo_lineas_2009", gsetSanLorenzoLineas2009);
	    san_lorenzo_lineas_2009.setColor(new Color(204, 255, 204));
	    san_lorenzo_lineas_2009.setMouseOverColor(Color.CYAN);
	    san_lorenzo_lineas_2009.setQueryable(true);

	    Layer san_lorenzo_textos_2009 = new Layer("san_lorenzo_textos_2009", gsetSanLorenzoTextos2009);
	    san_lorenzo_textos_2009.setColor(new Color(0, 0, 255));
	    san_lorenzo_textos_2009.setMouseOverColor(Color.CYAN);
	    san_lorenzo_textos_2009.setQueryable(true);

	    Layer vaqueros_lineas_2009 = new Layer("vaqueros_lineas_2009", gsetVaquerosLineas2009);
	    vaqueros_lineas_2009.setColor(new Color(204, 204, 204));
	    vaqueros_lineas_2009.setMouseOverColor(Color.CYAN);
	    vaqueros_lineas_2009.setQueryable(true);

	    Layer vias_rapidas_lineas_2009 = new Layer("vias_rapidas_lineas_2009", gsetViasRapidasLineas2009);
	    vias_rapidas_lineas_2009.setColor(new Color(255, 255, 0));
	    vias_rapidas_lineas_2009.getLayerConfig().getStyleConfig().setLineWidth(3);
	    vias_rapidas_lineas_2009.setMouseOverColor(Color.CYAN);
	    vias_rapidas_lineas_2009.setQueryable(true);

	    //Layer comercios_2009 = new Layer("Relevamiento Comercial 2009");
	    
	    Layer direcciones = new Layer("Direcciones", gsetDirecciones);
	    direcciones.setColor(Color.BLACK);
	    direcciones.setMouseOverColor(Color.CYAN);
	    direcciones.setPointDiameter(4);
	    direcciones.setQueryable(true);
	    direcciones.setModifiable(true);
	    direcciones.setDrawGeometries(false);
	    ExtendedInternalFrame direccionesPanelContainer = new ExtendedInternalFrame("Direcciones");
	    GaiaAddressReenumerationPanel direccionesPanel = new GaiaAddressReenumerationPanel();
	    direccionesPanelContainer.setCentralPanel(direccionesPanel);
	    direcciones.setConfigPanel(direccionesPanelContainer);
	    direccionesPanel.setLayer(direcciones);
	    GaiaEnvironment.setAddressLayer(direcciones.getAlias());

	    Layer comercios_2009 = new Layer("Relevamiento Comercial 2009", gsetCensoComercial2009); 
	    comercios_2009.setColor(Color.BLACK);
	    comercios_2009.setMouseOverColor(Color.CYAN);
	    comercios_2009.setQueryable(true);
	    comercios_2009.setEditable(true);
	    comercios_2009.setToolTipLabel("Nombre:");
	    ExtendedInternalFrame infoComercialContainer = new ExtendedInternalFrame("Ventana de Información Comercial");
	    InfoComercios infoComerciosPanel = new InfoComercios();
	    infoComercialContainer.setCentralPanel(infoComerciosPanel);
	    comercios_2009.setInfoPanel(infoComercialContainer);
	    FormCensoComercial2009 formComercios = new FormCensoComercial2009();
	    formComercios.setLayer(comercios_2009);

	    Layer publicidad_2009 = new Layer("Relevamiento Publicidad 2009", gsetRelevamientoPublicidad2009);
	    publicidad_2009.setColor(Color.BLACK);
	    publicidad_2009.setMouseOverColor(Color.CYAN);
	    publicidad_2009.setQueryable(true);
	    publicidad_2009.setEditable(true);
	    publicidad_2009.setToolTipLabel("Nombre:");
	    ExtendedInternalFrame infoPublicidadContainer = new ExtendedInternalFrame("Ventana de Información Publicitaria");
	    InfoPublicidad infoPublicidadPanel = new InfoPublicidad();
	    infoPublicidadContainer.setCentralPanel(infoPublicidadPanel);
	    publicidad_2009.setInfoPanel(infoPublicidadContainer);
	    FormPublicidad formPublicidad = new FormPublicidad();
	    formPublicidad.setLayer(publicidad_2009);

	    Layer parcelas_mkd = new Layer("Parcelas [MKD]", gsetParcelas_MKD);
	    parcelas_mkd.setColor(Color.BLACK);
	    parcelas_mkd.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_mkd.setMouseOverColor(Color.MAGENTA);

	    Layer textos_mkd = new Layer("Textos [MKD]", gsetTextos_MKD, "textstring");
	    textos_mkd.setColor(Color.BLACK);
	    textos_mkd.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    textos_mkd.setMouseOverColor(Color.MAGENTA);

	    secciones.setOn();
	    //parcelas_catastral.setOn();
	    vias_rapidas_lineas_2009.setOn();

	    GaiaEnvironment.layerGroups.add(infraestructuraGroup);
	    //GaiaEnvironment.layerGroups.add(socialGroup);
	    GaiaEnvironment.layerGroups.add(vialGroup);
	    GaiaEnvironment.layerGroups.add(municipiosGroup);
	    //GaiaEnvironment.layerGroups.add(topografiaGroup);
	    GaiaEnvironment.layerGroups.add(limitesGroup);
	    GaiaEnvironment.layerGroups.add(consultasGroup);
	    
	    catastralGroup.add(secciones);
	    catastralGroup.add(manzanas_vinculadas);
	    catastralGroup.add(calles);
	    catastralGroup.add(parcelas_catastral);
	    //catastralGroup.add(castanares_grupos_poligonos_2009);
	    catastralGroup.add(castanares_grupos_textos_2009);
	    //catastralGroup.add(numeros_catastro_textos_2009);
	    catastralGroup.add(direcciones);
	    catastralGroup.add(parcelas_mkd);
	    catastralGroup.add(textos_mkd);

	    Layer parcelas_sa = new Layer("Sección A", gsetParcelasSA, "parcela");
	    parcelas_sa.setColor(Color.BLACK);
	    parcelas_sa.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sa.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sb = new Layer("Sección B", gsetParcelasSB, "parcela");
	    parcelas_sb.setColor(Color.BLACK);
	    parcelas_sb.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sb.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sc = new Layer("Sección C", gsetParcelasSC, "parcela");
	    parcelas_sc.setColor(Color.BLACK);
	    parcelas_sc.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sc.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sd = new Layer("Sección D", gsetParcelasSD, "parcela");
	    parcelas_sd.setColor(Color.BLACK);
	    parcelas_sd.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sd.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_se = new Layer("Sección E", gsetParcelasSE, "parcela");
	    parcelas_se.setColor(Color.BLACK);
	    parcelas_se.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_se.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sf = new Layer("Sección F", gsetParcelasSF, "parcela");
	    parcelas_sf.setColor(Color.BLACK);
	    parcelas_sf.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sf.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sg = new Layer("Sección G", gsetParcelasSG, "parcela");
	    parcelas_sg.setColor(Color.BLACK);
	    parcelas_sg.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sg.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sh = new Layer("Sección H", gsetParcelasSH, "parcela");
	    parcelas_sh.setColor(Color.BLACK);
	    parcelas_sh.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sh.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sj = new Layer("Sección J", gsetParcelasSJ, "parcela");
	    parcelas_sj.setColor(Color.BLACK);
	    parcelas_sj.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sj.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sk = new Layer("Sección K", gsetParcelasSK, "parcela");
	    parcelas_sk.setColor(Color.BLACK);
	    parcelas_sk.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sk.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sl = new Layer("Sección L", gsetParcelasSL, "parcela");
	    parcelas_sl.setColor(Color.BLACK);
	    parcelas_sl.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sl.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sm = new Layer("Sección M", gsetParcelasSM, "parcela");
	    parcelas_sm.setColor(Color.BLACK);
	    parcelas_sm.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sm.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sn = new Layer("Sección N", gsetParcelasSN, "parcela");
	    parcelas_sn.setColor(Color.BLACK);
	    parcelas_sn.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sn.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_so = new Layer("Sección O", gsetParcelasSO, "parcela");
	    parcelas_so.setColor(Color.BLACK);
	    parcelas_so.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_so.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sp = new Layer("Sección P", gsetParcelasSP, "parcela");
	    parcelas_sp.setColor(Color.BLACK);
	    parcelas_sp.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sp.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sq = new Layer("Sección Q", gsetParcelasSQ, "parcela");
	    parcelas_sq.setColor(Color.BLACK);
	    parcelas_sq.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sq.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sr = new Layer("Sección R", gsetParcelasSR, "parcela");
	    parcelas_sr.setColor(Color.BLACK);
	    parcelas_sr.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sr.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_ss = new Layer("Sección S", gsetParcelasSS, "parcela");
	    parcelas_ss.setColor(Color.BLACK);
	    parcelas_ss.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_ss.setMouseOverColor(Color.MAGENTA);
	    
	    Layer parcelas_st = new Layer("Sección T", gsetParcelasST, "parcela");
	    parcelas_st.setColor(Color.BLACK);
	    parcelas_st.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_st.setMouseOverColor(Color.MAGENTA);
	    
	    Layer parcelas_su = new Layer("Sección U", gsetParcelasSU, "parcela");
	    parcelas_su.setColor(Color.BLACK);
	    parcelas_su.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_su.setMouseOverColor(Color.MAGENTA);

	    Layer parcelas_sv = new Layer("Sección V", gsetParcelasSV, "parcela");
	    parcelas_sv.setColor(Color.BLACK);
	    parcelas_sv.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
	    parcelas_sv.setMouseOverColor(Color.MAGENTA);

	    parcelasVinculadasGroup.add(parcelas_sa);
	    parcelasVinculadasGroup.add(parcelas_sb);
	    parcelasVinculadasGroup.add(parcelas_sc);
	    parcelasVinculadasGroup.add(parcelas_sd);
	    parcelasVinculadasGroup.add(parcelas_se);
	    parcelasVinculadasGroup.add(parcelas_sf);
	    parcelasVinculadasGroup.add(parcelas_sg);
	    parcelasVinculadasGroup.add(parcelas_sh);
	    parcelasVinculadasGroup.add(parcelas_sj);
	    parcelasVinculadasGroup.add(parcelas_sk);
	    parcelasVinculadasGroup.add(parcelas_sl);
	    parcelasVinculadasGroup.add(parcelas_sm);
	    parcelasVinculadasGroup.add(parcelas_sn);
	    parcelasVinculadasGroup.add(parcelas_so);
	    parcelasVinculadasGroup.add(parcelas_sp);
	    parcelasVinculadasGroup.add(parcelas_sq);
	    parcelasVinculadasGroup.add(parcelas_sr);
	    parcelasVinculadasGroup.add(parcelas_ss);
	    parcelasVinculadasGroup.add(parcelas_st);
	    parcelasVinculadasGroup.add(parcelas_su);
	    parcelasVinculadasGroup.add(parcelas_sv);

	    Layer parcelas_catastral_1 = new Layer("Parcelas (Catastral 1)", gsetParcelas);
	    parcelas_catastral_1.getLayerConfig().setDrawGeometries(false);
	    parcelas_catastral_1.setColor(Color.BLACK);
	    parcelas_catastral_1.setMouseOverColor(Color.MAGENTA);
	    parcelas_catastral_1.setQueryable(true);

	    Layer parcelas_catastral_2 = new Layer("Parcelas (Catastral 2)", gsetParcelas);
	    parcelas_catastral_2.getLayerConfig().setDrawGeometries(false);
	    parcelas_catastral_2.setColor(Color.BLACK);
	    parcelas_catastral_2.setMouseOverColor(Color.MAGENTA);
	    parcelas_catastral_2.setQueryable(true);

	    Layer parcelas_catastral_3 = new Layer("Parcelas (Catastral 3)", gsetParcelas);
	    parcelas_catastral_3.getLayerConfig().setDrawGeometries(false);
	    parcelas_catastral_3.setColor(Color.BLACK);
	    parcelas_catastral_3.setMouseOverColor(Color.MAGENTA);
	    parcelas_catastral_3.setQueryable(true);

	    infraestructuraGroup.add(estructuras_viales);
	    infraestructuraGroup.add(barrios);
	    //infraestructuraGroup.add(farolas);
	    //infraestructuraGroup.add(redcloacal_bocasderegistro);
	    //infraestructuraGroup.add(redcloacal_cojinetes);
	    //infraestructuraGroup.add(redcloacal);
	    //infraestructuraGroup.add(reddeaguapotable);
	    //infraestructuraGroup.add(reddegasnatural);
	    infraestructuraGroup.add(canales_lineas_2009);
	    //infraestructuraGroup.add(escuelas_puntos_2009);
	    //infraestructuraGroup.add(escuelas_textos_2009);
	    //infraestructuraGroup.add(espacios_verdes_futuro_poligono_2009);
	    infraestructuraGroup.add(espacios_verdes_poligono_2009);
	    infraestructuraGroup.add(ffcc_2009);
	    infraestructuraGroup.add(infraestructura_textos_2009);
	    //infraestructuraGroup.add(lineas_alta_tension_lineas_2009);
	    //socialGroup.add(ayudas_parcelas_sa);
	    //socialGroup.add(asentamientos_poligono_2009);
	    //socialGroup.add(asentamientos_textos_2009);
	    //socialGroup.add(casosEnfermedades);
	    //socialGroup.add(casosDelitos);
	    vialGroup.add(autopistas_lineas_2009);
	    vialGroup.add(autopistas_proyectadas_2009);
	    vialGroup.add(rutas_nacionales_lineas_2009);
	    vialGroup.add(rutas_provinciales_lineas_2009);
	    vialGroup.add(vias_rapidas_lineas_2009);
	    municipiosGroup.add(cerrillos_calles_texto_2009);
	    municipiosGroup.add(cerrillos_informat_texto_2009);
	    municipiosGroup.add(cerrillos_manzanas_lineas_2009);
	    municipiosGroup.add(san_lorenzo_lineas_2009);
	    municipiosGroup.add(san_lorenzo_textos_2009);
	    municipiosGroup.add(vaqueros_lineas_2009);
	    //topografiaGroup.add(cota25_linea_2009);
	    //topografiaGroup.add(cota50_linea_2009);
	    //topografiaGroup.add(cota100_linea_2009);
	    //topografiaGroup.add(cota_cima_punto_2009);
	    //topografiaGroup.add(cota_cima_texto_2009);
	    //topografiaGroup.add(cota_numeros_texto_2009);
	    //topografiaGroup.add(cota1225_linea_2009);
	    //topografiaGroup.add(cota1225_texto_2009);
	    limitesGroup.add(limite_ejido_municipal_lineas_2009);
	    limitesGroup.add(limite_ejido_municipal_texto_2009);
	    limitesGroup.add(limite_dpto_lineas_2009);
	    limitesGroup.add(limite_dpto_texto_2009);
	    //ordenanzasGroup.add(retiros150_poligono_2009);
	    //ordenanzasGroup.add(retiros150_300_poligono_2009);
	    //ordenanzasGroup.add(retiros300_poligono_2009);

	    relevamientoGroup.add(comercios_2009);
	    relevamientoGroup.add(publicidad_2009);

	    consultasGroup.add(parcelas_catastral_1);
	    consultasGroup.add(parcelas_catastral_2);
	    consultasGroup.add(parcelas_catastral_3);
    
	    cityMap.addLayerGroup(catastralGroup);
	    cityMap.addLayerGroup(parcelasVinculadasGroup);
	    cityMap.addLayerGroup(infraestructuraGroup);
	    cityMap.addLayerGroup(relevamientoGroup);
	    cityMap.addLayerGroup(vialGroup);
	    cityMap.addLayerGroup(municipiosGroup);
	    cityMap.addLayerGroup(limitesGroup);
	    cityMap.addLayerGroup(consultasGroup);

	    GaiaEnvironment.nomencladorLayers.add(manzanas_vinculadas);
	    GaiaEnvironment.nomencladorLayers.add(calles);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sa);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sb);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sc);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sd);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_se);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sf);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sg);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sh);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sj);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sk);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sl);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sm);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sn);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_so);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sp);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sr);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_ss);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_st);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_su);
	    GaiaEnvironment.nomencladorLayers.add(parcelas_sv);

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
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.ADDRESSES_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.STREETS_EDITION_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.INFRASTRUCTURES_EDITION_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.PRINT_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.MULTIQUERY_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.FIXED_POLYGON_QUERY_TOOL);
	    mapBasicToolsFrame.addTool(MapBasicToolsPanel.LAYER_EDITION_TOOL);
	    
	    mapBasicToolsFrame.setClosable(false);

	    RuleViewer ruleViewer = new RuleViewer();
	    ruleViewer.setClosable(false);
	    cityMap.setRuleViewer(ruleViewer);
	    ruleViewer.setTitle("");

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
	
	    coordinateViewer.setVisible(cityMap.getEngineConfig().isCoordinateViewerVisible());

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
