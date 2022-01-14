package org.digitall.projects.gea.salta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

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
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;

public class GEARecaudacion extends BasicPrimitivePanel {

    BasicDrawEngine cityMap = new BasicDrawEngine("Recaudacion", new BasicLabel());

    public GEARecaudacion() {
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
	    GaiaEnvironment.initialize();//línea agregada para cargar la configuración de GAIA 
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    cityMap.setMapExtents(3548172.1937, 7249881.0068, 3571654.0741, 7268573.5261);
	    CoordinateViewer coordinateViewer = new CoordinateViewer();
	    coordinateViewer.setClosable(false);
	    cityMap.setCoordinateViewer(coordinateViewer);
	    coordinateViewer.setTitle("");

	    cityMap.setBounds(Environment.getActiveDesktop().getBounds());
	    cityMap.setVisible(true);
            
	    if (!GaiaEnvironment.gaiaEngine.initialize()) {//línea agregada para cargar la configuración de GAIA
                GeometrySet gsetAccionSocialEntregas = new GeometrySet("accionsocial", "entregas", "the_geom", "1=1", "identrega");
                GeometrySet gsetAccionSocialPersonas = new GeometrySet("accionsocial", "personas", "the_geom", "1=1", "idpersona");
    
                GeometrySet gsetPoligonos = new GeometrySet("gis", "_poligonos", "the_geom", "1=1", "gid");
                GeometrySet gsetPuntos = new GeometrySet("gis", "_puntos", "the_geom", "1=1", "gid");
                GeometrySet gsetPolilineas = new GeometrySet("gis", "_polilineas", "the_geom", "1=1", "gid");
    
                GeometrySet gsetSecciones = new GeometrySet("gis_salta", "secciones", "the_geom", "1=1", "gid");
                GeometrySet gsetParcelas = new GeometrySet("gis_salta", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
                GeometrySet gsetBarrios = new GeometrySet("gis_salta", "barrios", "the_geom", "1=1", "gid");
                GeometrySet gsetCalles = new GeometrySet("gis_salta", "calles", "the_geom", "1=1", "idcalle");
                GeometrySet gsetManzanas = new GeometrySet("gis_salta", "manzanas_vinculadas", "the_geom", "1=1", "gid");
                GeometrySet gsetEstructuras = new GeometrySet("gis_salta", "estructuras", "the_geom", "1=1", "gid");
                GeometrySet gsetAutopistasLineas2009 = new GeometrySet("gis_salta", "autopistas_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetAutopistasProyectadas2009 = new GeometrySet("gis_salta", "autopistas_proyectadas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCanalesLineas2009 = new GeometrySet("gis_salta", "canales_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCastanaresGruposPoligonos2009 = new GeometrySet("gis_salta", "castanares_grupos_poligonos_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCastanaresGruposTextos2009 = new GeometrySet("gis_salta", "castanares_grupos_textos_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCerrillosCallesTextos2009 = new GeometrySet("gis_salta", "cerrillos_calles_texto_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCerrillosInformatTexto2009 = new GeometrySet("gis_salta", "cerrillos_informat_texto_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetCerrillosManzanasLineas2009 = new GeometrySet("gis_salta", "cerrillos_manzanas_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetEspaciosVerdesPoligono2009 = new GeometrySet("gis_salta", "espacios_verdes_poligono_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetFFCC2009 = new GeometrySet("gis_salta", "ffcc_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetInfraestructuraTextos2009 = new GeometrySet("gis_salta", "infraestructura_textos_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetLimiteDeptoLineas2009 = new GeometrySet("gis_salta", "limite_dpto_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetLimiteDeptoTextos2009 = new GeometrySet("gis_salta", "limite_dpto_texto_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetLimiteEjidoMunicipalLineas2009 = new GeometrySet("gis_salta", "limite_ejido_municipal_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetLimiteEjidoMunicipalTextos2009 = new GeometrySet("gis_salta", "limite_ejido_municipal_texto_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetRutasNacionalesLineas2009 = new GeometrySet("gis_salta", "rutas_nacionales_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetRutasProvincialesLineas009 = new GeometrySet("gis_salta", "rutas_provinciales_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetSanLorenzoLineas2009 = new GeometrySet("gis_salta", "san_lorenzo_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetSanLorenzoTextos2009 = new GeometrySet("gis_salta", "san_lorenzo_textos_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetVaquerosLineas2009 = new GeometrySet("gis_salta", "vaqueros_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetViasRapidasLineas2009 = new GeometrySet("gis_salta", "vias_rapidas_lineas_2009", "the_geom", "1=1", "gid");
                GeometrySet gsetDirecciones = new GeometrySet("gis_salta", "direcciones", "the_geom", "1=1", "gid");
                GeometrySet gsetPavimentacion = new GeometrySet("gis_salta", "pavimentacion", "the_geom", "1=1", "gid");
                GeometrySet gsetZonasReparto = new GeometrySet("gis_salta", "zonas_reparto", "the_geom", "1=1", "gid");
    
                GeometrySet gsetComerciosHis2002 = new GeometrySet("gis_salta", "comercios_his_2002", "the_geom", "1=1", "idparcela");
                GeometrySet gsetComerciosHis2009 = new GeometrySet("gis_salta", "comercios_his_2009", "the_geom", "1=1", "idparcela");
                GeometrySet gsetCensoComercial2009 = new GeometrySet("gea_salta", "censo_comercial_2009_seccion1", "the_geom", "1=1/**/", "idencuestacomercial", false);
                GeometrySet gsetRelevamientoPublicidad2009 = new GeometrySet("gea_salta", "relevamientopublicidad_2009_detalle", "the_geom", "1=1/**/", "iddetallerelevamiento", false);
                GeometrySet gsetPublicidadPropaganda = new GeometrySet("gea_salta", "publicidad_propaganda", "the_geom", "1=1/**/", "iddetallerelevamiento", false);
                GeometrySet gsetParcelas_MKD = new GeometrySet("gis_salta", "parcelas_mkd", "the_geom", "1=1", "gid");
                GeometrySet gsetTextos_MKD = new GeometrySet("gis_salta", "txt_mkd", "the_geom", "1=1", "gid");
    
                GeometrySet gsetPuntoPrueba = new GeometrySet("gea_salta", "puntoprueba", "the_geom", "1=1/**/", "idpuntoprueba", false);
                GeometrySet gsetEYC_NBI = new GeometrySet("eyc_salta", "nbi_1", "the_geom", "1=1", "gid");
                /*Este código comentado ya no es necesario porque los grupos de geometrias se cargan en el citymap mediante el método cityMap.initialize()
                cityMap.addGeometrySet(gsetAccionSocialEntregas);
                cityMap.addGeometrySet(gsetAccionSocialPersonas);
    
                cityMap.addGeometrySet(gsetPoligonos);
                cityMap.addGeometrySet(gsetPuntos);
                cityMap.addGeometrySet(gsetPolilineas);
    
                cityMap.addGeometrySet(gsetSecciones);
                cityMap.addGeometrySet(gsetParcelas);
                cityMap.addGeometrySet(gsetBarrios);
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
                cityMap.addGeometrySet(gsetPavimentacion);
                cityMap.addGeometrySet(gsetZonasReparto);
                cityMap.addGeometrySet(gsetComerciosHis2002); 
                cityMap.addGeometrySet(gsetComerciosHis2009);
                cityMap.addGeometrySet(gsetCensoComercial2009);
                cityMap.addGeometrySet(gsetRelevamientoPublicidad2009);
                cityMap.addGeometrySet(gsetPublicidadPropaganda);
                cityMap.addGeometrySet(gsetParcelas_MKD);
                cityMap.addGeometrySet(gsetTextos_MKD);
    
                cityMap.addGeometrySet(gsetPuntoPrueba);
                cityMap.addGeometrySet(gsetEYC_NBI);
                */
                Layer puntoPrueba = new Layer("Punto Prueba", gsetPuntoPrueba);
                puntoPrueba.setColor(Color.BLACK);
                puntoPrueba.setMouseOverColor(Color.CYAN);
                puntoPrueba.setQueryable(true);
                puntoPrueba.setToolTipLabel("Nombre:");
                ExtendedInternalFrame puntoPruebaPanelContainer = new ExtendedInternalFrame("Ventana de Información");
                GaiaPuntoPruebaPanel puntoPruebaPanel = new GaiaPuntoPruebaPanel();
                puntoPruebaPanelContainer.setCentralPanel(puntoPruebaPanel);
                puntoPrueba.setInfoPanel(puntoPruebaPanelContainer);
    
                Layer comercios_his_2002 = new Layer("Comercios (Historico 2002)", gsetComerciosHis2002, "parcela", "", "nomcomercio");
                comercios_his_2002.setFillColor(new Color(204, 0, 0));
                comercios_his_2002.setMouseOverColor(Color.CYAN);
                comercios_his_2002.setQueryable(true);
                comercios_his_2002.setToolTipLabel("Valor Fiscal:");
                Layer comercios_his_2009 = new Layer("Comercios (Historico 2009)", gsetComerciosHis2009, "numero", "", "nombrefantasia");
                comercios_his_2009.setFillColor(new Color(153, 153, 255));
                comercios_his_2009.setMouseOverColor(Color.CYAN);
                comercios_his_2009.setQueryable(true);
                comercios_his_2009.getLayerConfig().getStyleConfig().setTransparency(100-30); //20% de opacidad
                comercios_his_2009.setToolTipLabel("Valor Fiscal:");
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
    
                Layer publicidad_propaganda = new Layer("Publicidad y Propaganda Municipalidad", gsetPublicidadPropaganda);
                publicidad_propaganda.setColor(Color.BLACK);
                publicidad_propaganda.setMouseOverColor(Color.CYAN);
                publicidad_propaganda.setQueryable(true);
                publicidad_propaganda.setEditable(true);
                publicidad_propaganda.setToolTipLabel("Nombre:");
                RevisionPublicidadPropaganda formRevisionPyP = new RevisionPublicidadPropaganda();
                formRevisionPyP.setLayer(publicidad_propaganda);
    
                LayerGroup relevamientoGroup = new LayerGroup("Relevamiento");
                relevamientoGroup.add(comercios_his_2002);
                relevamientoGroup.add(comercios_his_2009);
                relevamientoGroup.add(comercios_2009);
                relevamientoGroup.add(publicidad_2009);
                relevamientoGroup.add(publicidad_propaganda);
    
                Layer parcelas_mkd = new Layer("Parcelas [MKD]", gsetParcelas_MKD);
                parcelas_mkd.setColor(Color.BLACK);
                parcelas_mkd.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
                parcelas_mkd.setMouseOverColor(Color.MAGENTA);
    
                Layer textos_mkd = new Layer("Textos [MKD]", gsetTextos_MKD, "textstring");
                textos_mkd.setColor(Color.BLACK);
                textos_mkd.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial",Font.PLAIN,2));
                textos_mkd.setMouseOverColor(Color.MAGENTA);
    
                // Censo Social - Estadísticas y Censos
                Layer eycNBI = new Layer("EYC [NBI]", gsetEYC_NBI);
                //eycNBI.getLayerConfig().setDrawGeometries(false);
                eycNBI.setColor(Color.BLACK);
                eycNBI.setMouseOverColor(Color.MAGENTA);
                eycNBI.setQueryable(true);
                LayerGroup eycGroup = new LayerGroup("Estadísticas y Censos (PRUEBA)");
                eycGroup.add(eycNBI);
                /*
                GeometrySet gsetEYCAlumbrado = new GeometrySet("eyc_salta", "alumbrado", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCBarrios = new GeometrySet("eyc_salta", "barrios2009_dpe9", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCCobSalud = new GeometrySet("eyc_salta", "cob_salud_no", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCDiscapacidad = new GeometrySet("eyc_salta", "discapacidad", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCNBI1 = new GeometrySet("eyc_salta", "nbi_1", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCNBI2 = new GeometrySet("eyc_salta", "nbi_2", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCNBI3 = new GeometrySet("eyc_salta", "nbi_3", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCNBI4 = new GeometrySet("eyc_salta", "nbi_4", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCNBI5 = new GeometrySet("eyc_salta", "nbi_5", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCOnceAnios = new GeometrySet("eyc_salta", "once_anios", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCPanojocano = new GeometrySet("eyc_salta", "panojocano", "the_geom", "1=1", "gid");
                GeometrySet gsetEYCVacunacion = new GeometrySet("eyc_salta", "vacunacion", "the_geom", "1=1", "gid");
                cityMap.addGeometrySet(gsetEYCAlumbrado);
                cityMap.addGeometrySet(gsetEYCBarrios);
                cityMap.addGeometrySet(gsetEYCCobSalud);
                cityMap.addGeometrySet(gsetEYCDiscapacidad);
                cityMap.addGeometrySet(gsetEYCNBI1);
                cityMap.addGeometrySet(gsetEYCNBI2);
                cityMap.addGeometrySet(gsetEYCNBI3);
                cityMap.addGeometrySet(gsetEYCNBI4);
                cityMap.addGeometrySet(gsetEYCNBI5);
                cityMap.addGeometrySet(gsetEYCOnceAnios);
                cityMap.addGeometrySet(gsetEYCPanojocano);
                cityMap.addGeometrySet(gsetEYCVacunacion);
                Layer eycAlumbrado = new Layer("Alumbrado (EYC)", gsetEYCAlumbrado);
                eycAlumbrado.getLayerConfig().setDrawGeometries(false);
                eycAlumbrado.setColor(Color.BLACK);
                eycAlumbrado.setMouseOverColor(Color.MAGENTA);
                eycAlumbrado.setQueryable(true);
                Layer eycBarrios = new Layer("Barrios (EYC)", gsetEYCBarrios);
                eycBarrios.getLayerConfig().setDrawGeometries(false);
                eycBarrios.setColor(Color.BLACK);
                eycBarrios.setMouseOverColor(Color.MAGENTA);
                eycBarrios.setQueryable(true);
                Layer eycCobSalud = new Layer("CobSalud (EYC)", gsetEYCCobSalud);
                eycCobSalud.getLayerConfig().setDrawGeometries(false);
                eycCobSalud.setColor(Color.BLACK);
                eycCobSalud.setMouseOverColor(Color.MAGENTA);
                eycCobSalud.setQueryable(true);
                Layer eycDiscapacidad = new Layer("Discapacidad (EYC)", gsetEYCDiscapacidad);
                eycDiscapacidad.getLayerConfig().setDrawGeometries(false);
                eycDiscapacidad.setColor(Color.BLACK);
                eycDiscapacidad.setMouseOverColor(Color.MAGENTA);
                eycDiscapacidad.setQueryable(true);
                Layer eycNBI1 = new Layer("NBI 1 (EYC)", gsetEYCNBI1);
                eycNBI1.getLayerConfig().setDrawGeometries(false);
                eycNBI1.setColor(Color.BLACK);
                eycNBI1.setMouseOverColor(Color.MAGENTA);
                eycNBI1.setQueryable(true);
                Layer eycNBI2 = new Layer("NBI 2 (EYC)", gsetEYCNBI2);
                eycNBI2.getLayerConfig().setDrawGeometries(false);
                eycNBI2.setColor(Color.BLACK);
                eycNBI2.setMouseOverColor(Color.MAGENTA);
                eycNBI2.setQueryable(true);
                Layer eycNBI3 = new Layer("NBI 3 (EYC)", gsetEYCNBI3);
                eycNBI3.getLayerConfig().setDrawGeometries(false);
                eycNBI3.setColor(Color.BLACK);
                eycNBI3.setMouseOverColor(Color.MAGENTA);
                eycNBI3.setQueryable(true);
                Layer eycNBI4 = new Layer("NBI 4 (EYC)", gsetEYCNBI4);
                eycNBI4.getLayerConfig().setDrawGeometries(false);
                eycNBI4.setColor(Color.BLACK);
                eycNBI4.setMouseOverColor(Color.MAGENTA);
                eycNBI4.setQueryable(true);
                Layer eycNBI5 = new Layer("NBI 5 (EYC)", gsetEYCNBI5);
                eycNBI5.getLayerConfig().setDrawGeometries(false);
                eycNBI5.setColor(Color.BLACK);
                eycNBI5.setMouseOverColor(Color.MAGENTA);
                eycNBI5.setQueryable(true);
                Layer eycOnceAnios = new Layer("Once Años (EYC)", gsetEYCOnceAnios);
                eycOnceAnios.getLayerConfig().setDrawGeometries(false);
                eycOnceAnios.setColor(Color.BLACK);
                eycOnceAnios.setMouseOverColor(Color.MAGENTA);
                eycOnceAnios.setQueryable(true);
                Layer eycPanojocano = new Layer("Panojocano (EYC)", gsetEYCPanojocano);
                eycPanojocano.getLayerConfig().setDrawGeometries(false);
                eycPanojocano.setColor(Color.BLACK);
                eycPanojocano.setMouseOverColor(Color.MAGENTA);
                eycPanojocano.setQueryable(true);
                Layer eycVacunacion = new Layer("Vacunación (EYC)", gsetEYCVacunacion);
                eycVacunacion.getLayerConfig().setDrawGeometries(false);
                eycVacunacion.setColor(Color.BLACK);
                eycVacunacion.setMouseOverColor(Color.MAGENTA);
                eycVacunacion.setQueryable(true);
                LayerGroup eycGroup = new LayerGroup("Estadísticas y Censos");
                eycGroup.add(eycAlumbrado);
                eycGroup.add(eycBarrios);
                eycGroup.add(eycCobSalud);
                eycGroup.add(eycDiscapacidad);
                eycGroup.add(eycNBI1);
                eycGroup.add(eycNBI2);
                eycGroup.add(eycNBI3);
                eycGroup.add(eycNBI4);
                eycGroup.add(eycNBI5);
                eycGroup.add(eycOnceAnios);
                eycGroup.add(eycPanojocano);
                eycGroup.add(eycVacunacion);
                */
    
                LayerGroup catastralGroup = new LayerGroup("Catastral");
                LayerGroup infraestructuraGroup = new LayerGroup("Infraestructura");
                //LayerGroup socialGroup = new LayerGroup("Social");
                LayerGroup vialGroup = new LayerGroup("Vial");
                LayerGroup municipiosGroup = new LayerGroup("Municipios");
                //LayerGroup topografiaGroup = new LayerGroup("Topografía");
                LayerGroup limitesGroup = new LayerGroup("Límites");
                LayerGroup recaudacionGroup = new LayerGroup("Recaudación");
                //LayerGroup ordenanzasGroup = new LayerGroup("Ordenanzas");
                LayerGroup consultasComerciosGroup = new LayerGroup("Consultas Comercios");
                LayerGroup consultasPublicidadesGroup = new LayerGroup("Consultas Publicidades");
    
                LayerGroup experimentalGroup = new LayerGroup("Experimental");
    
                LayerGroup accionSocialGroup = new LayerGroup("Acción Social y Comunitaria");
    
                Layer accionSocialPersonas = new Layer("Personas", gsetAccionSocialPersonas);
                accionSocialPersonas.setColor(Color.GREEN);
                accionSocialPersonas.setFillColor(Color.BLUE);
                accionSocialPersonas.setMouseOverColor(Color.MAGENTA);
                accionSocialPersonas.setPointDiameter(8);
                accionSocialPersonas.setQueryable(true);
                accionSocialPersonas.setModifiable(true);
                accionSocialPersonas.setDrawGeometries(false);
    
                Layer accionSocialEntregas = new Layer("Entregas", gsetAccionSocialEntregas);
                accionSocialEntregas.setColor(Color.GREEN);
                accionSocialEntregas.setFillColor(Color.BLUE);
                accionSocialEntregas.setMouseOverColor(Color.MAGENTA);
                accionSocialEntregas.setPointDiameter(8);
                accionSocialEntregas.setQueryable(true);
                accionSocialEntregas.setModifiable(true);
                accionSocialEntregas.setDrawGeometries(false);
    
                Layer _poligonos = new Layer("Poligonos", gsetPoligonos, "nombre");
                _poligonos.setColor(new Color(204, 0, 204));
                _poligonos.getLayerConfig().getStyleConfig().setLineWidth(3);
                _poligonos.getLayerConfig().getStyleConfig().setLabelFont(new Font("Arial", Font.BOLD, 50));
                _poligonos.setMouseOverColor(Color.ORANGE);
    
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
                manzanas_vinculadas.setEditable(true);
    
                Layer zonas_reparto = new Layer("Zonas de Reparto", gsetZonasReparto, "idrepartidor");
                zonas_reparto.setColor(new Color(153, 153, 153));
                zonas_reparto.setFillColor(new Color(255, 255, 0));
                zonas_reparto.getLayerConfig().getStyleConfig().setTransparency(100-70);
                zonas_reparto.setMouseOverColor(Color.MAGENTA);
    
                Layer parcelas_catastral = new Layer("Parcelas (Catastral)", gsetParcelas);
                parcelas_catastral.getLayerConfig().setDrawGeometries(false);
                parcelas_catastral.setColor(Color.BLACK);
                parcelas_catastral.setMouseOverColor(Color.MAGENTA);
                parcelas_catastral.setQueryable(true);
                GaiaEnvironment.setCadastralLayer(parcelas_catastral.getAlias());
                ExtendedInternalFrame parcelPanelContainer = new ExtendedInternalFrame("Ventana de Información");
                GaiaParcelPanel parcelPanel = new GaiaParcelPanel();
                parcelPanelContainer.setCentralPanel(parcelPanel);
                parcelPanel.setDrawEngine(cityMap);
                parcelas_catastral.setInfoPanel(parcelPanelContainer);
    
                Layer parcelas_tgi = new Layer("Parcelas (TGI)", gsetParcelas, "categoria");
                //parcelas_tgi.getLayerConfig().setDrawGeometries(true);
                parcelas_tgi.setColor(Color.BLACK);
                parcelas_tgi.setMouseOverColor(Color.MAGENTA);
                parcelas_tgi.addFilter("TGI N/A", "categoria", "(categoria = 0)", Color.black, new Color(0, 0, 0), "0");
                parcelas_tgi.addFilter("TGI 1", "categoria", "(categoria = 1)", Color.black, new Color(255, 0, 0), "1");
                parcelas_tgi.addFilter("TGI 2", "categoria", "(categoria = 2)", Color.black, new Color(0, 220, 0), "2");
                parcelas_tgi.addFilter("TGI 3", "categoria", "(categoria = 3)", Color.black, new Color(200, 0, 200), "3");
                parcelas_tgi.addFilter("TGI 4", "categoria", "(categoria = 4)", Color.black, new Color(255, 159, 128), "4");
                parcelas_tgi.addFilter("TGI 5", "categoria", "(categoria = 5)", Color.black, new Color(150, 150, 255), "5");
                parcelas_tgi.addFilter("TGI 6", "categoria", "(categoria = 6)", Color.black, new Color(150, 250, 150), "6");
    
                Layer parcelas_impinm = new Layer("Parcelas (ImpInm)", gsetParcelas, "impinm");
                //parcelas_impinm.getLayerConfig().setDrawGeometries(true);
                parcelas_impinm.setColor(Color.BLACK);
                parcelas_impinm.setMouseOverColor(Color.MAGENTA);
                parcelas_impinm.addFilter("ImpInm N/A", "impinm", "(impinm = 0)", Color.black, new Color(0, 0, 1), "Categoría I.I. 0");
                parcelas_impinm.addFilter("ImpInm 10", "impinm", "(impinm = 10)", Color.black, new Color(255, 0, 1), "Categoría I.I. 10");
                parcelas_impinm.addFilter("ImpInm 11", "impinm", "(impinm = 11)", Color.black, new Color(253, 253, 1), "Categoría I.I. 11");
                parcelas_impinm.addFilter("ImpInm 2", "impinm", "(impinm = 2)", Color.black, new Color(0, 220, 1), "Categoría I.I. 2");
                parcelas_impinm.addFilter("ImpInm 3", "impinm", "(impinm = 3)", Color.black, new Color(0, 0, 201), "Categoría I.I. 3");
    
                Layer parcelas_tgi_itron = new Layer("Parcelas (TGI - ITRON)", gsetParcelas, "tgi2");
                //parcelas_tgi_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_tgi_itron.setColor(Color.BLACK);
                parcelas_tgi_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_tgi_itron.addFilter("TGI N/A", "tgi2", "(tgi2 = 0)", Color.black, new Color(0, 0, 0), "0");
                parcelas_tgi_itron.addFilter("TGI 1A", "tgi2", "(tgi2 = 10)", Color.black, new Color(255, 0, 0), "10");
                parcelas_tgi_itron.addFilter("TGI 1B", "tgi2", "(tgi2 = 11)", Color.black, new Color(255, 0, 0), "11");
                parcelas_tgi_itron.addFilter("TGI 2", "tgi2", "(tgi2 = 2)", Color.black, new Color(0, 220, 0), "2");
                parcelas_tgi_itron.addFilter("TGI 3", "tgi2", "(tgi2 = 3)", Color.black, new Color(200, 0, 200), "3");
                parcelas_tgi_itron.addFilter("TGI 4", "tgi2", "(tgi2 = 4)", Color.black, new Color(255, 159, 128), "4");
                parcelas_tgi_itron.addFilter("TGI 5", "tgi2", "(tgi2 = 5)", Color.black, new Color(150, 150, 255), "5");
                parcelas_tgi_itron.addFilter("TGI 6", "tgi2", "(tgi2 = 6)", Color.black, new Color(150, 250, 150), "6");
    
                Layer parcelas_deuda_tgi_itron = new Layer("Parcelas (Deuda TGI - ITRON)", gsetParcelas, "deuda_tgi_itron");
                //parcelas_deuda_tgi_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_deuda_tgi_itron.setColor(Color.BLACK);
                parcelas_deuda_tgi_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_deuda_tgi_itron.addFilter("NO SE SABE", "deuda_tgi_itron", "(deuda_tgi_itron < 0)", null, Color.black, "No se sabe");
                parcelas_deuda_tgi_itron.addFilter("DEUDA CERO", "deuda_tgi_itron", "(deuda_tgi_itron = 0)", null, Color.green, "Deuda Cero");
                parcelas_deuda_tgi_itron.addFilter("Menos de 25", "deuda_tgi_itron", "(deuda_tgi_itron > 0  AND deuda_tgi_itron <= 25)", null, new Color(253, 253, 0), "Menos de $ 25,00");
                parcelas_deuda_tgi_itron.addFilter("Entre 25 y 50", "deuda_tgi_itron", "(deuda_tgi_itron > 25 AND deuda_tgi_itron <= 50)", null, new Color(0, 253, 253), "Entre $ 25,00 y $ 50,00");
                parcelas_deuda_tgi_itron.addFilter("Entre 50 y 100", "deuda_tgi_itron", "(deuda_tgi_itron > 50 AND deuda_tgi_itron <= 100)", null, new Color(252, 0, 253), "Entre $ 50,00 y $ 100,00");
                parcelas_deuda_tgi_itron.addFilter("Entre 100 y 200", "deuda_tgi_itron", "(deuda_tgi_itron > 100 AND deuda_tgi_itron <= 200)", null, new Color(152, 0, 153), "Entre $ 100,00 y $ 200,00");
                parcelas_deuda_tgi_itron.addFilter("Entre 200 y 500", "deuda_tgi_itron", "(deuda_tgi_itron > 200 AND deuda_tgi_itron <= 500)", null, new Color(0, 171, 0), "Entre $ 200,00 y $ 500,00");
                parcelas_deuda_tgi_itron.addFilter("Entre 500 y 1000", "deuda_tgi_itron", "(deuda_tgi_itron > 500 AND deuda_tgi_itron <= 1000)", null, new Color(0, 171, 171), "Entre $ 500,00 y $ 1.000,00");
                parcelas_deuda_tgi_itron.addFilter("Más de 1000", "deuda_tgi_itron", "(deuda_tgi_itron > 1000)", null, new Color(253, 0, 0), "Más de $ 1.000,00");
    
                Layer parcelas_periodos_tgi_itron = new Layer("Parcelas (Periodos TGI - ITRON)", gsetParcelas, "periodos_tgi_itron");
                //parcelas_periodos_tgi_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_periodos_tgi_itron.setColor(Color.BLACK);
                parcelas_periodos_tgi_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_periodos_tgi_itron.addFilter("NO SE SABE", "periodos_tgi_itron", "(periodos_tgi_itron < 0)", null, Color.black, "No se sabe");
                parcelas_periodos_tgi_itron.addFilter("DEUDA CERO", "periodos_tgi_itron", "(periodos_tgi_itron = 0)", null, Color.green, "Deuda Cero");
                parcelas_periodos_tgi_itron.addFilter("Hasta 3", "periodos_tgi_itron", "(periodos_tgi_itron > 0  AND periodos_tgi_itron <= 3)", null, new Color(0, 253, 0), "Hasta 3 periodos");
                parcelas_periodos_tgi_itron.addFilter("Entre 4 y 12", "periodos_tgi_itron", "(periodos_tgi_itron > 3 AND periodos_tgi_itron <= 12)", null, new Color(253, 253, 0), "Entre 4 y 12 periodos");
                parcelas_periodos_tgi_itron.addFilter("Más de 12", "periodos_tgi_itron", "(periodos_tgi_itron > 12)", null, new Color(252, 0, 0), "Más de 12 periodos");
    
                Layer parcelas_deuda_impinm_itron = new Layer("Parcelas (Deuda ImpInm - ITRON)", gsetParcelas, "deuda_impinm_itron");
                //parcelas_deuda_impinm_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_deuda_impinm_itron.setColor(Color.BLACK);
                parcelas_deuda_impinm_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_deuda_impinm_itron.addFilter("NO SE SABE", "deuda_impinm_itron", "(deuda_impinm_itron < 0)", null, Color.black, "No se sabe");
                parcelas_deuda_impinm_itron.addFilter("DEUDA CERO", "deuda_impinm_itron", "(deuda_impinm_itron = 0)", null, Color.green, "Deuda Cero");
                parcelas_deuda_impinm_itron.addFilter("Menos de 25", "deuda_impinm_itron", "(deuda_impinm_itron > 0  AND deuda_impinm_itron <= 25)", null, new Color(253, 253, 0), "Menos de $ 25,00");
                parcelas_deuda_impinm_itron.addFilter("Entre 25 y 50", "deuda_impinm_itron", "(deuda_impinm_itron > 25 AND deuda_impinm_itron <= 50)", null, new Color(0, 253, 253), "Entre $ 25,00 y $ 50,00");
                parcelas_deuda_impinm_itron.addFilter("Entre 50 y 100", "deuda_impinm_itron", "(deuda_impinm_itron > 50 AND deuda_impinm_itron <= 100)", null, new Color(252, 0, 253), "Entre $ 50,00 y $ 100,00");
                parcelas_deuda_impinm_itron.addFilter("Entre 100 y 200", "deuda_impinm_itron", "(deuda_impinm_itron > 100 AND deuda_impinm_itron <= 200)", null, new Color(152, 0, 153), "Entre $ 100,00 y $ 200,00");
                parcelas_deuda_impinm_itron.addFilter("Entre 200 y 500", "deuda_impinm_itron", "(deuda_impinm_itron > 200 AND deuda_impinm_itron <= 500)", null, new Color(0, 171, 0), "Entre $ 200,00 y $ 500,00");
                parcelas_deuda_impinm_itron.addFilter("Entre 500 y 1000", "deuda_impinm_itron", "(deuda_impinm_itron > 500 AND deuda_impinm_itron <= 1000)", null, new Color(0, 171, 171), "Entre $ 500,00 y $ 1.000,00");
                parcelas_deuda_impinm_itron.addFilter("Más de 1000", "deuda_impinm_itron", "(deuda_impinm_itron > 1000)", null, new Color(253, 0, 0), "Más de $ 1.000,00");
    
                Layer parcelas_periodos_impinm_itron = new Layer("Parcelas (Periodos ImpInm - ITRON)", gsetParcelas, "periodos_impinm_itron");
                //parcelas_periodos_impinm_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_periodos_impinm_itron.setColor(Color.BLACK);
                parcelas_periodos_impinm_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_periodos_impinm_itron.addFilter("NO SE SABE", "periodos_impinm_itron", "(periodos_impinm_itron < 0)", null, Color.black, "No se sabe");
                parcelas_periodos_impinm_itron.addFilter("DEUDA CERO", "periodos_impinm_itron", "(periodos_impinm_itron = 0)", null, Color.green, "Deuda Cero");
                parcelas_periodos_impinm_itron.addFilter("Hasta 3", "periodos_impinm_itron", "(periodos_impinm_itron > 0  AND periodos_impinm_itron <= 3)", null, new Color(0, 253, 0), "Hasta 3 periodos");
                parcelas_periodos_impinm_itron.addFilter("Entre 4 y 12", "periodos_impinm_itron", "(periodos_impinm_itron > 3 AND periodos_impinm_itron <= 12)", null, new Color(253, 253, 0), "Entre 4 y 12 periodos");
                parcelas_periodos_impinm_itron.addFilter("Más de 12", "periodos_impinm_itron", "(periodos_impinm_itron > 12)", null, new Color(252, 0, 0), "Más de 12 periodos");
    
                Layer parcelas_condonados_itron = new Layer("Parcelas (Condonados - ITRON)", gsetParcelas, "condonado");
                //parcelas_condonados_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_condonados_itron.setColor(Color.BLACK);
                parcelas_condonados_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_condonados_itron.addFilter("CONDONADOS", "condonado", "condonado", null, Color.black, "Condonado");
    
                Layer parcelas_eximidos_itron = new Layer("Parcelas (Eximidos - ITRON)", gsetParcelas, "eximido");
                //parcelas_eximidos_itron.getLayerConfig().setDrawGeometries(true);
                parcelas_eximidos_itron.setColor(Color.BLACK);
                parcelas_eximidos_itron.setMouseOverColor(Color.MAGENTA);
                parcelas_eximidos_itron.addFilter("EXIMIDOS", "eximido", "eximido", null, Color.black, "Eximido");
                
                ExtendedInternalFrame streetsPanelContainer = new ExtendedInternalFrame("Calles");
                GaiaStreetsPanel streetsPanel = new GaiaStreetsPanel();
                streetsPanelContainer.setCentralPanel(streetsPanel);
                calles.setConfigPanel(streetsPanelContainer);
                
                Layer pavimentacion = new Layer("Pavimentación", gsetPavimentacion);
                pavimentacion.setColor(Color.BLACK);
                pavimentacion.setMouseOverColor(Color.CYAN);
                pavimentacion.setQueryable(true);
                //pavimentacion.addFilter("", "idtipo", "(idtipo = 1)", new Color(255, 0, 0), 3, "Pavimento 1");
                //pavimentacion.addFilter("", "idtipo", "(idtipo = 2)", new Color(70, 70, 70), 3, "Pavimento 2");
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
                //formsInternalFrame.show();
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
    
                Layer comercios_consultas_1 = new Layer("Relevamiento Comercial 2009 [1]", gsetCensoComercial2009); 
                comercios_consultas_1.getLayerConfig().setDrawGeometries(true);
                comercios_consultas_1.setColor(Color.BLACK);
                comercios_consultas_1.setMouseOverColor(Color.CYAN);
                comercios_consultas_1.setQueryable(true);
                comercios_consultas_1.setQueryable(true);
                comercios_consultas_1.setToolTipLabel("Nombre:");
    
                Layer comercios_consultas_2 = new Layer("Relevamiento Comercial 2009 [2]", gsetCensoComercial2009); 
                comercios_consultas_2.getLayerConfig().setDrawGeometries(true);
                comercios_consultas_2.setColor(Color.BLACK);
                comercios_consultas_2.setMouseOverColor(Color.CYAN);
                comercios_consultas_2.setQueryable(true);
                comercios_consultas_2.setQueryable(true);
                comercios_consultas_2.setToolTipLabel("Nombre:");
    
                Layer comercios_consultas_3 = new Layer("Relevamiento Comercial 2009 [3]", gsetCensoComercial2009); 
                comercios_consultas_3.getLayerConfig().setDrawGeometries(true);
                comercios_consultas_3.setColor(Color.BLACK);
                comercios_consultas_3.setMouseOverColor(Color.CYAN);
                comercios_consultas_3.setQueryable(true);
                comercios_consultas_3.setQueryable(true);
                comercios_consultas_3.setToolTipLabel("Nombre:");
    
                Layer comercios_consultas_4 = new Layer("Relevamiento Comercial 2009 [4]", gsetCensoComercial2009); 
                comercios_consultas_4.getLayerConfig().setDrawGeometries(true);
                comercios_consultas_4.setColor(Color.BLACK);
                comercios_consultas_4.setMouseOverColor(Color.CYAN);
                comercios_consultas_4.setQueryable(true);
                comercios_consultas_4.setQueryable(true);
                comercios_consultas_4.setToolTipLabel("Nombre:");
    
                Layer comercios_habilitacion = new Layer("Relevamiento Comercial [Habilitación]", gsetCensoComercial2009); 
                comercios_habilitacion.getLayerConfig().setDrawGeometries(true);
                comercios_habilitacion.setColor(Color.BLACK);
                comercios_habilitacion.setMouseOverColor(Color.CYAN);
                comercios_habilitacion.setQueryable(true);
                comercios_habilitacion.setQueryable(true);
                comercios_habilitacion.setToolTipLabel("Nombre:");
                comercios_habilitacion.addFilter("HAB. EN TRÁMITE", "", "(SELECT habilitacion FROM gea_salta.censo_comercial_2009_habilitacion WHERE censo_comercial_2009_habilitacion.idencuestacomercial = _id) = 'HABILITACIÓN EN TRÁMITE'", Color.black, Color.yellow, "HABILITACIÓN EN TRÁMITE");
                comercios_habilitacion.addFilter("HAB. PROVISORIA", "", "(SELECT habilitacion FROM gea_salta.censo_comercial_2009_habilitacion WHERE censo_comercial_2009_habilitacion.idencuestacomercial = _id) = 'HABILITACIÓN PROVISORIA'", Color.black, Color.blue, "HABILITACIÓN PROVISORIA");
                comercios_habilitacion.addFilter("SIN HABILITACIÓN", "", "(SELECT habilitacion FROM gea_salta.censo_comercial_2009_habilitacion WHERE censo_comercial_2009_habilitacion.idencuestacomercial = _id) = 'SIN HABILITACIÓN'", Color.black, Color.red, "SIN HABILITACIÓN");
    
                Layer comercios_local = new Layer("Relevamiento Comercial [Tipo Local]", gsetCensoComercial2009); 
                comercios_local.getLayerConfig().setDrawGeometries(true);
                comercios_local.setColor(Color.BLACK);
                comercios_local.setMouseOverColor(Color.CYAN);
                comercios_local.setQueryable(true);
                comercios_local.setQueryable(true);
                comercios_local.setToolTipLabel("Nombre:");
                comercios_local.addFilter("ALQUILADO", "", "(SELECT f2g FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) LIKE 'ALQUILADO%'", Color.black, Color.gray, "ALQUILADO");
                comercios_local.addFilter("CON PERMISO DE USO", "", "(SELECT f2g FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'CON PERMISO DE USO'", Color.cyan, Color.blue, "CON PERMISO DE USO");
                comercios_local.addFilter("PROPIO", "", "(SELECT f2g FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) LIKE 'PROPIO%'", Color.black, Color.magenta, "PROPIO");
                comercios_local.addFilter("NP/NS/NC", "", "(SELECT f2g FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'NP/NS/NC'", Color.black, Color.orange, "NP/NS/NC");
    
                Layer comercios_tiporubro = new Layer("Relevamiento Comercial [Tipo Rubro]", gsetCensoComercial2009); 
                comercios_tiporubro.getLayerConfig().setDrawGeometries(true);
                comercios_tiporubro.setColor(Color.BLACK);
                comercios_tiporubro.setMouseOverColor(Color.CYAN);
                comercios_tiporubro.setQueryable(true);
                comercios_tiporubro.setQueryable(true);
                comercios_tiporubro.setToolTipLabel("Nombre:");
                comercios_tiporubro.addFilter("COMERCIAL", "", "(SELECT f2h FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'COMERCIAL'", Color.black, Color.gray, "COMERCIAL");
                comercios_tiporubro.addFilter("DE SERVICIOS", "", "(SELECT f2h FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'DE SERVICIOS'", Color.cyan, Color.blue, "DE SERVICIOS");
                comercios_tiporubro.addFilter("COMERCIAL, DE SERVICIOS", "", "(SELECT f2h FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'COMERCIAL, DE SERVICIOS'", Color.black, Color.magenta, "COMERCIAL, DE SERVICIOS");
                comercios_tiporubro.addFilter("INDUSTRIAL", "", "(SELECT f2h FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) LIKE '%INDUSTRIAL%'", Color.black, Color.red, "INDUSTRIAL");
                comercios_tiporubro.addFilter("NP/NS/NC", "", "(SELECT f2h FROM gea_salta.censo_comercial_2009_seccion2_a WHERE censo_comercial_2009_seccion2_a.idencuestacomercial = _id) = 'NP/NS/NC'", Color.black, Color.green, "NP/NS/NC");
    
                Layer publicidad_formato = new Layer("Relevamiento Publicidad [Formato]", gsetRelevamientoPublicidad2009); 
                publicidad_formato.getLayerConfig().setDrawGeometries(true);
                publicidad_formato.setColor(Color.BLACK);
                publicidad_formato.setMouseOverColor(Color.CYAN);
                publicidad_formato.setQueryable(true);
                publicidad_formato.setQueryable(true);
                publicidad_formato.setToolTipLabel("Nombre:");
                publicidad_formato.addFilter("CUADRADO", "f3a", "f3a = 'CUADRADO'", Color.black, Color.gray, "CUADRADO");
                publicidad_formato.addFilter("OTRO", "f3a", "f3a = 'OTRO'", Color.cyan, Color.blue, "OTRO");
                publicidad_formato.addFilter("RECTANGULAR", "f3a", "f3a = 'RECTANGULAR'", Color.black, Color.magenta, "RECTANGULAR");
                publicidad_formato.addFilter("REDONDO", "f3a", "f3a = 'REDONDO'", Color.black, Color.yellow, "REDONDO");
                publicidad_formato.addFilter("TRIANGULAR", "f3a", "f3a = 'TRIANGULAR'", Color.black, Color.red, "TRIANGULAR");
    
                Layer publicidad_iluminacion = new Layer("Relevamiento Publicidad [Iluminación]", gsetRelevamientoPublicidad2009); 
                publicidad_iluminacion.getLayerConfig().setDrawGeometries(true);
                publicidad_iluminacion.setColor(Color.BLACK);
                publicidad_iluminacion.setMouseOverColor(Color.CYAN);
                publicidad_iluminacion.setQueryable(true);
                publicidad_iluminacion.setQueryable(true);
                publicidad_iluminacion.setToolTipLabel("Nombre:");
                publicidad_iluminacion.addFilter("ILUMINADO", "f3b", "f3b = 'ILUMINADO'", Color.black, Color.red, "ILUMINADO");
                publicidad_iluminacion.addFilter("LUMINOSO", "f3b", "f3b = 'LUMINOSO'", Color.cyan, Color.magenta, "LUMINOSO");
                publicidad_iluminacion.addFilter("PROYECTADO, DINÁMICO O ANIMADO", "f3b", "f3b = 'PROYECTADO, DINÁMICO O ANIMADO'", Color.black, Color.cyan, "PROYECTADO, DINÁMICO O ANIMADO");
                publicidad_iluminacion.addFilter("SIMPLE", "f3b", "f3b = 'SIMPLE'", Color.black, Color.yellow, "SIMPLE");
    
                Layer publicidades_consultas_1 = new Layer("Relevamiento Publicidad 2009 [1]", gsetRelevamientoPublicidad2009);
                publicidades_consultas_1.setColor(Color.BLACK);
                publicidades_consultas_1.setMouseOverColor(Color.CYAN);
                publicidades_consultas_1.setQueryable(true);
                publicidades_consultas_1.setEditable(true);
                publicidades_consultas_1.setToolTipLabel("Nombre:");
                
                Layer publicidades_consultas_2 = new Layer("Relevamiento Publicidad 2009 [2]", gsetRelevamientoPublicidad2009);
                publicidades_consultas_2.setColor(Color.BLACK);
                publicidades_consultas_2.setMouseOverColor(Color.CYAN);
                publicidades_consultas_2.setQueryable(true);
                publicidades_consultas_2.setEditable(true);
                publicidades_consultas_2.setToolTipLabel("Nombre:");
    
                Layer publicidades_consultas_3 = new Layer("Relevamiento Publicidad 2009 [3]", gsetRelevamientoPublicidad2009);
                publicidades_consultas_3.setColor(Color.BLACK);
                publicidades_consultas_3.setMouseOverColor(Color.CYAN);
                publicidades_consultas_3.setQueryable(true);
                publicidades_consultas_3.setEditable(true);
                publicidades_consultas_3.setToolTipLabel("Nombre:");
    
                Layer publicidades_consultas_4 = new Layer("Relevamiento Publicidad 2009 [4]", gsetRelevamientoPublicidad2009);
                publicidades_consultas_4.setColor(Color.BLACK);
                publicidades_consultas_4.setMouseOverColor(Color.CYAN);
                publicidades_consultas_4.setQueryable(true);
                publicidades_consultas_4.setEditable(true);
                publicidades_consultas_4.setToolTipLabel("Nombre:");
    
                Layer publicidad_propaganda_consultas_1 = new Layer("Publicidad y Propaganda Municipalidad [2]", gsetPublicidadPropaganda);
                publicidad_propaganda_consultas_1.setColor(Color.BLACK);
                publicidad_propaganda_consultas_1.setMouseOverColor(Color.CYAN);
                publicidad_propaganda_consultas_1.setQueryable(true);
                publicidad_propaganda_consultas_1.setEditable(true);
                publicidad_propaganda_consultas_1.setToolTipLabel("Nombre:");
    
                Layer publicidad_propaganda_consultas_2 = new Layer("Publicidad y Propaganda Municipalidad [2]", gsetPublicidadPropaganda);
                publicidad_propaganda_consultas_2.setColor(Color.BLACK);
                publicidad_propaganda_consultas_2.setMouseOverColor(Color.CYAN);
                publicidad_propaganda_consultas_2.setQueryable(true);
                publicidad_propaganda_consultas_2.setEditable(true);
                publicidad_propaganda_consultas_2.setToolTipLabel("Nombre:");
    
                consultasComerciosGroup.add(comercios_local);
                consultasComerciosGroup.add(comercios_habilitacion);
                consultasComerciosGroup.add(comercios_tiporubro);
    
                consultasPublicidadesGroup.add(publicidad_formato);
                consultasPublicidadesGroup.add(publicidad_iluminacion);
    
                secciones.setOn();
                parcelas_catastral.setOn();
                vias_rapidas_lineas_2009.setOn();
    
                GaiaEnvironment.layerGroups.add(accionSocialGroup);
                GaiaEnvironment.layerGroups.add(catastralGroup);//linea agregada para probar funcionamiento de carga de configuración de GAIA
                GaiaEnvironment.layerGroups.add(experimentalGroup);
                GaiaEnvironment.layerGroups.add(infraestructuraGroup);
                //GaiaEnvironment.layerGroups.add(socialGroup);
                GaiaEnvironment.layerGroups.add(vialGroup);
                GaiaEnvironment.layerGroups.add(municipiosGroup);
                //GaiaEnvironment.layerGroups.add(topografiaGroup);
                GaiaEnvironment.layerGroups.add(limitesGroup);
                GaiaEnvironment.layerGroups.add(recaudacionGroup);
                GaiaEnvironment.layerGroups.add(relevamientoGroup);
                GaiaEnvironment.layerGroups.add(consultasComerciosGroup);
                GaiaEnvironment.layerGroups.add(consultasPublicidadesGroup);
    
                accionSocialGroup.add(accionSocialPersonas);
                accionSocialGroup.add(accionSocialEntregas);
                
                experimentalGroup.add(_poligonos);
                experimentalGroup.add(_puntos);
                experimentalGroup.add(_polilineas);
                
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
                catastralGroup.add(puntoPrueba);
    
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
                infraestructuraGroup.add(zonas_reparto);
                recaudacionGroup.add(pavimentacion);
                recaudacionGroup.add(parcelas_tgi);
                recaudacionGroup.add(parcelas_tgi_itron);
                recaudacionGroup.add(parcelas_impinm);
                //recaudacionGroup.add(parcelas_impinm_itron);
                recaudacionGroup.add(parcelas_deuda_tgi_itron);
                recaudacionGroup.add(parcelas_periodos_tgi_itron);
                recaudacionGroup.add(parcelas_deuda_impinm_itron);
                recaudacionGroup.add(parcelas_periodos_impinm_itron);
                recaudacionGroup.add(parcelas_condonados_itron);
                recaudacionGroup.add(parcelas_eximidos_itron);
                //recaudacionGroup.add(impinm_parcelas_sa);
                //recaudacionGroup.add(valfis_parcelas_sa);
                //recaudacionGroup.add(comercios);
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
    
                /*Este código comentado ya no es necesario porque los grupos de layers se cargan en el citymap mediante el método cityMap.initialize()
                cityMap.addLayerGroup(accionSocialGroup);
    
                cityMap.addLayerGroup(experimentalGroup);
                cityMap.addLayerGroup(eycGroup);
                cityMap.addLayerGroup(catastralGroup);
                cityMap.addLayerGroup(infraestructuraGroup);
                //cityMap.addLayerGroup(recaudacionGroup);
                cityMap.addLayerGroup(relevamientoGroup);
                //cityMap.addLayerGroup(socialGroup);
                cityMap.addLayerGroup(vialGroup);
                cityMap.addLayerGroup(municipiosGroup);
                //cityMap.addLayerGroup(topografiaGroup);
                cityMap.addLayerGroup(limitesGroup);
                //cityMap.addLayerGroup(ordenanzasGroup);
                cityMap.addLayerGroup(consultasComerciosGroup);
                cityMap.addLayerGroup(consultasPublicidadesGroup);
                
                //cityMap.addLayerGroup(eycGroup);
                */
                GaiaEnvironment.nomencladorLayers.add(manzanas_vinculadas);
                GaiaEnvironment.nomencladorLayers.add(calles);
                GaiaEnvironment.nomencladorLayers.add(parcelas_catastral);
    
                GaiaEnvironment.cadastralLayers.add(parcelas_catastral);
                GaiaEnvironment.cadastralLayers.add(manzanas_vinculadas);
                GaiaEnvironment.cadastralLayers.add(calles);    
                
                //líneas agregadas para cargar la configuración de GAIA
                GaiaEnvironment.gaiaEngine.setLayerGroupList(GaiaEnvironment.layerGroups);
                Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
                geometrySets.add(gsetAccionSocialEntregas);
                geometrySets.add(gsetAccionSocialPersonas);
                
                geometrySets.add(gsetPoligonos);
                geometrySets.add(gsetPuntos);
                geometrySets.add(gsetPolilineas);
                
                geometrySets.add(gsetSecciones);
                geometrySets.add(gsetParcelas);
                geometrySets.add(gsetBarrios);
                geometrySets.add(gsetCalles);
                geometrySets.add(gsetManzanas);
                geometrySets.add(gsetEstructuras);
                geometrySets.add(gsetAutopistasLineas2009);
                geometrySets.add(gsetAutopistasProyectadas2009);
                geometrySets.add(gsetCanalesLineas2009);
                geometrySets.add(gsetCastanaresGruposPoligonos2009);
                geometrySets.add(gsetCastanaresGruposTextos2009);
                geometrySets.add(gsetCerrillosCallesTextos2009);
                geometrySets.add(gsetCerrillosInformatTexto2009);
                geometrySets.add(gsetCerrillosManzanasLineas2009);
                geometrySets.add(gsetEspaciosVerdesPoligono2009);
                geometrySets.add(gsetFFCC2009);
                geometrySets.add(gsetInfraestructuraTextos2009);
                geometrySets.add(gsetLimiteDeptoLineas2009);
                geometrySets.add(gsetLimiteDeptoTextos2009);
                geometrySets.add(gsetLimiteEjidoMunicipalLineas2009);
                geometrySets.add(gsetLimiteEjidoMunicipalTextos2009);
                geometrySets.add(gsetRutasNacionalesLineas2009);
                geometrySets.add(gsetRutasProvincialesLineas009);
                geometrySets.add(gsetSanLorenzoLineas2009);
                geometrySets.add(gsetSanLorenzoTextos2009);
                geometrySets.add(gsetVaquerosLineas2009);
                geometrySets.add(gsetViasRapidasLineas2009);
                geometrySets.add(gsetDirecciones);
                geometrySets.add(gsetPavimentacion);
                geometrySets.add(gsetZonasReparto);
                geometrySets.add(gsetComerciosHis2002); 
                geometrySets.add(gsetComerciosHis2009);
                geometrySets.add(gsetCensoComercial2009);
                geometrySets.add(gsetRelevamientoPublicidad2009);
                geometrySets.add(gsetPublicidadPropaganda);
                geometrySets.add(gsetParcelas_MKD);
                geometrySets.add(gsetTextos_MKD);
                
                geometrySets.add(gsetPuntoPrueba);
                geometrySets.add(gsetEYC_NBI);

                GaiaEnvironment.geometrySets = geometrySets;

                for (int i = 0; i < GaiaEnvironment.layerGroups.size(); i++) {
                    LayerGroup _layerGroup = GaiaEnvironment.layerGroups.elementAt(i);
                    for (int j = 0; j < _layerGroup.size(); j++) {
                        GaiaEnvironment.gaiaEngine.getLayerProfileList().add(new LayerProfile(_layerGroup.elementAt(j).getGeometrySetConfig().getName(), _layerGroup.getName(), _layerGroup.elementAt(j).getName()));
                    }
                }
                //fin de líneas agregadas para cargar la configuración de GAIA
            }
	    cityMap.initialize();//línea agregada para cargar la configuración de GAIA
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
	    //mapBasicToolsFrame.addTool(MapBasicToolsPanel.ADDRESSES_TOOL);
	    //mapBasicToolsFrame.addTool(MapBasicToolsPanel.STREETS_EDITION_TOOL);
	    //mapBasicToolsFrame.addTool(MapBasicToolsPanel.INFRASTRUCTURES_EDITION_TOOL);
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
	/*Vector<String> list = new Vector<String>();
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/family.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/home.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/dates.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/realestate.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/friends.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/girlfriend.png");
	list.add("/tmp/icons/mapicons/Friends, Family & Real Estate/apartment.png");
	list.add("/tmp/icons/mapicons/Stores/butcher.png");
	list.add("/tmp/icons/mapicons/Stores/shoes.png");
	list.add("/tmp/icons/mapicons/Stores/newsagent.png");
	list.add("/tmp/icons/mapicons/Stores/music.png");
	list.add("/tmp/icons/mapicons/Stores/videogames.png");
	list.add("/tmp/icons/mapicons/Stores/convenience.png");
	list.add("/tmp/icons/mapicons/Stores/phones.png");
	list.add("/tmp/icons/mapicons/Stores/liquor.png");
	list.add("/tmp/icons/mapicons/Stores/toys.png");
	list.add("/tmp/icons/mapicons/Stores/shoppingmall.png");
	list.add("/tmp/icons/mapicons/Stores/bookstore.png");
	list.add("/tmp/icons/mapicons/Stores/pets.png");
	list.add("/tmp/icons/mapicons/Stores/clothes.png");
	list.add("/tmp/icons/mapicons/Stores/textiles.png");
	list.add("/tmp/icons/mapicons/Stores/jewelry.png");
	list.add("/tmp/icons/mapicons/Stores/fishingshop.png");
	list.add("/tmp/icons/mapicons/Stores/movierental.png");
	list.add("/tmp/icons/mapicons/Stores/supermarket.png");
	list.add("/tmp/icons/mapicons/Stores/grocery.png");
	list.add("/tmp/icons/mapicons/Stores/bread.png");
	list.add("/tmp/icons/mapicons/Stores/gifts.png");
	list.add("/tmp/icons/mapicons/Stores/tools.png");
	list.add("/tmp/icons/mapicons/Stores/computer.png");
	list.add("/tmp/icons/mapicons/Stores/flowers.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/agriculture.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/windmill.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/watermill.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/wineyard.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/farm.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Agriculture/corral.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/riparian.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/animals.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/beach.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/forest.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/rattlesnake.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/seals.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/beautiful.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/drinkingwater.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/fossils.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Natural Marvels/wetland.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/bigcity.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/cave.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/historicalquarter.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/cruise.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/campingsite.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/country.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/park.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/world.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/smallcity.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/picnic.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/jewishquarter.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/tent.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/olympicsite.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/camping.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Miscellaneous/panoramic180.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/synagogue.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/headstonejewish.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/cathedral2.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/cemetary.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/monastery.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/church2.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/chapel.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/headstone.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Cult & Religion/mosque.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/bridge.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/bridgemodern.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/fountain.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/lighthouse.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/ancienttemple.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/monument.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/modernmonument.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/arch.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/ancienttempleruin.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/moderntower.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/worldheritagesite.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Monuments & Structures/statue.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/palace.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/castle.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/gateswalls.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/fortress.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/tower.png");
	list.add("/tmp/icons/mapicons/Tourism & Nature/Old Defensive Buildings/ruins.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/rightthenup.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/upthenleft.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/down.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/upright.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/leftthenup.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/downthenleft.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/downleft.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/right.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/rightthendown.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/leftthendown.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/downthenright.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/downright.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/upleft.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/up.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/upthenright.png");
	list.add("/tmp/icons/mapicons/Transportation/Directions/left.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/cycling.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/tollstation.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/bicycleparking.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/taxi.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/truck.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/carrental.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/carrepair.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/vespa.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/stoplight.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/gazstation.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/carwash.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/car.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/highway.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/parking.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/tunnel.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/bus.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Transportation/parkandride.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/pedestriancrossing.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/port.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/train.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/crossingguard.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/steamtrain.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/subway.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/helicopter.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/cablecar.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/sailboat.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/hotairballoon.png");
	list.add("/tmp/icons/mapicons/Transportation/Other Types of Transportation/airport.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/mainroad.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed90.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed60.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed100.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/stop.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed50.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed80.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed110.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed70.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed30.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed120.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed130.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/fallingrocks.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed40.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/accident.png");
	list.add("/tmp/icons/mapicons/Transportation/Road Signs/speed20.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/telephone.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/escalator-up.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/recycle.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/info.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/smokingarea.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/regroup.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/escalator-down.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/toilets.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/construction.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/shower.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/disability.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/wifi.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/trash.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/elevator.png");
	list.add("/tmp/icons/mapicons/Miscellaneous/cluster.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/basketball.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/surfing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/spelunking.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/windsurfing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/hiking.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingmountain4.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/snowshoeing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/skilift.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/diving.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/massage.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/gym.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingsport.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/soccer.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingmountainnotrated.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/racing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/pool.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/skijump.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/snowboarding.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/kayak.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/karting.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/bobsleigh.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/crosscountryskiing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/stadium.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/boxing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/waterskiing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/rugby.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingsprint.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingmountain3.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/karate.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/baseball.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/australianfootball.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/horseriding.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingmountain2.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/usfootball.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/jogging.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingfeedarea.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/iceskating.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/climbing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/hanggliding.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/motorbike.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/scubadiving.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/snowmobiling.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/archery.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/personalwatercraft.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/skiing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/suv.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/fitnesscenter.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/atv.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/yoga.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/golf.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/tennis.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/hunting.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/icehockey.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/skateboarding.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclingmountain1.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/judo.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/cyclinguncategorized.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/rowboat.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Sports/fishing.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/hospital.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/dentist.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/hairsalon.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/firstaid.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/drugs.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/doctor.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/vet.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/schrink.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/ophthalmologist.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/aestheticscenter.png");
	list.add("/tmp/icons/mapicons/Sports, Health & Beauty/Health/sauna.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black17.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/orange10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown16.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink07.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown19.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/darkblue11.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green01.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal03.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/pink13.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green02.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal04.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/teal20.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red06.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/white09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/red10.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/blue12.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/yellow15.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/brown14.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/green08.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/gray18.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/black05.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/numeric/purple09.png");
	list.add("/tmp/icons/mapicons/Numbers, Letters & Symbols/shadow.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/museum-archeological.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/billiard.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/music-live.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/aquarium.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/magicshow.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/casino.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/club.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/museum-historical.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/poker.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/dancinghall.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/bowling.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/museum-naval.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/anniversary.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/museum-war.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/party.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/museum.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/theater.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Indoor Activities/cinema.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/fireworks.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/publicart.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/themepark.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/circus.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/zoo.png");
	list.add("/tmp/icons/mapicons/Culture & Entertainment/Outdoor Activities/bullfight.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Education/school.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Education/university.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Education/library.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Kids/nanny.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Kids/nursery.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Kids/playground.png");
	list.add("/tmp/icons/mapicons/Education & Kids/Kids/daycare.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Lodging/youthhostel.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Lodging/hotel.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Lodging/hostel.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Lodging/resort.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantturkish.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantchinese.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantafrican.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/pizza.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/fastfood.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurant.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantgourmet.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantitalian.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantfishchips.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantjapanese.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/tapas.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantkebab.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantindian.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantthai.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantmexican.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/gourmet.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantgreek.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantmediterranean.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Restaurants/restaurantkorean.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Other/icecream.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Drinks/winery.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Drinks/bar.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Drinks/cocktail.png");
	list.add("/tmp/icons/mapicons/Restaurants & Hotels/Drinks/coffee.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photoup.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photodownright.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photoright.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photodown.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photoupleft.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photoleft.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photodownleft.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photoupright.png");
	list.add("/tmp/icons/mapicons/Media/Photo/photo.png");
	list.add("/tmp/icons/mapicons/Media/Other/text.png");
	list.add("/tmp/icons/mapicons/Media/Other/audio.png");
	list.add("/tmp/icons/mapicons/Media/Other/video.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/clouds.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/sun.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/rain.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/snow.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/cloudsun.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Weather/thunder.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/radiation.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/fire.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/wedding.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/gun.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/bomb.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/revolution.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/planecrash.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/flood.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/strike.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Events/explosion.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-vie.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-seg.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-ven.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-mer.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-jeu.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/friday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-sex.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/satursday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-lun.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-mie.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-qui.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-dim.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-jue.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/thursday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-dom.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/monday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-ter.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-sab.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-sam.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/sunday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-qua.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/tuesday.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/days-mar.png");
	list.add("/tmp/icons/mapicons/Events & Weather/Days of the Week/wednesday.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/mine.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/prison.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/police.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/administration.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/seniorsite.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/dam.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/windturbine.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/postal.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/firemen.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/customs.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/watertower.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/court.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/powerplant.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/City Services/military.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/conference.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/bank.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/workoffice.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/amphitheater.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/lockerrental.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/bankeuro.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/laundromat.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/factory.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/laboratory.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/findajob.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/bankpound.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/company.png");
	list.add("/tmp/icons/mapicons/Administration, Office & Industry/Private Offices/currencyexchange.png");
	for (int i = 0; i < list.size(); i++) {
	    String name = list.elementAt(i);
	    int idType = -1;
	    File symbolFile = new File(name);
	    if (symbolFile != null) {
		Environment.cfg.setProperty("symbolsdirectory", symbolFile.getPath().replaceAll(symbolFile.getName(), ""));
		//LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
		if (symbolFile.length() < 65535) {
		    BufferedImage symbolImage = ImageIO.read(symbolFile);
		    //System.out.println("Width, height: " + img.getWidth() + "," + img.getHeight());
		    if ((symbolImage.getWidth() <= 100) && (symbolImage.getHeight() <= 100)) {
			int _result = -1;
			String params = idType + ",'" + name + "'";
			_result = LibSQL.getInt("tabs.setInfrastructureType", params);
			if (_result == -1) {
			    Advisor.messageBox("Error al grabar los datos", "Error");
			} else {
			    idType = _result;
			    if (symbolImage != null) {
				if (!LibIMG.saveImage(symbolImage, "tabs.infrastructuretype_tabs", "symbol", "WHERE idtype = " + idType)) {
				    Advisor.messageBox("Error al grabar la imagen", "Error");
				}
			    }
			}
		    } else {
			Advisor.messageBox("Photo width/height not allowed. Can't be greater than " + 100 + "x" + 100 + " pixels", "Messsage");
		    }
		} else {
		    Advisor.messageBox("File length not allowed. Cant' be greater than " + (65535 / 1024.0) + " Kbytes", "Messsage");
		}
	    }
	}
	
	UPDATE tabs.infrastructuretype_tabs SET name = replace(name,'/tmp/icons/mapicons/','');
	UPDATE tabs.infrastructuretype_tabs SET name =  replace(name, 'Numbers','Z01-Numbers') WHERE name like 'Number%'
	*/
