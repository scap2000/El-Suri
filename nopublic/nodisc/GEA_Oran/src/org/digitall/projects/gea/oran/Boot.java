
package org.digitall.projects.gea.oran;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;

import org.digitall.apps.gaia.forms.GaiaFormAdminyServ;
import org.digitall.apps.gaia.forms.GaiaFormCasosEnfermedad;
import org.digitall.apps.gaia.forms.GaiaFormComercios;
import org.digitall.apps.gaia.forms.GaiaFormCultoyCultura;
import org.digitall.apps.gaia.forms.GaiaFormEducacion;
import org.digitall.apps.gaia.forms.GaiaFormIndustria;
import org.digitall.apps.gaia.forms.GaiaFormProduccion;
import org.digitall.apps.gaia.forms.GaiaFormSalud;
import org.digitall.apps.gaia.forms.GaiaFormSeguridad;
import org.digitall.apps.gaia.forms.GaiaFormServUrbanos;
import org.digitall.apps.gaia.forms.GaiaFormTurismo;
import org.digitall.common.components.basic.BasicCard;
import org.digitall.common.data.PanelDictionary;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.common.geo.mapping.components.LayerListPanel;
import org.digitall.common.geo.mapping.components.MapBasicToolsPanel;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.common.systemmanager.ChangePassword;
import org.digitall.common.systemmanager.interfaces.SystemInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.DesktopButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Debug;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

import org.jfree.report.JFreeReportBoot;

public class Boot {

    public static final int INIT_START = 0;
    //RESERVED = -1;
    public static final int INIT_RESTART = -2;
    public static final int INIT_RESET = -3;
    public static final int INIT_HALT = -4;
    public static final int STATUS_STARTING = -5;
    public static final int STATUS_RESTARTING = -6;
    public static final int STATUS_RESETTING = -7;
    public static final int STATUS_SHUTTING_DOWN = -8;
    public static final int STATUS_RUNNING = -9;
    public static final int STATUS_STOPPED = -10;
    /**
     * Modulos
     * */
    public static int MAIN_MODULE = 0;
    public static int CASHFLOWADMIN_MODULE = 1;
    public static int RESOURCESREQUESTS_MODULE = 2;
    public static int RESOURCESCONTROL_MODULE = 3;
    public static int LOGISTICS_MODULE = 4;
    public static int TASKS_MODULE = 5;
    public static int STICKYNOTES_MODULE = 6;
    public static int COMMANDCENTER_MODULE = 7;
    public static int CASHFLOW_MODULE = -1;
    public static int CHAT_MODULE = -1;
    /**
     * Estado de los modulos
     * */
    public static int MAIN_MODULE_STATUS = -1;
    public static int LOGISTICS_MODULE_STATUS = -1;
    public static int RESOURCESCONTROL_MODULE_STATUS = -1;
    public static int RESOURCESREQUESTS_MODULE_STATUS = -1;
    public static int CASHFLOWADMIN_MODULE_STATUS = -1;
    public static int CASHFLOW_MODULE_STATUS = -1;
    public static int CHAT_MODULE_STATUS = -1;
    public static int STICKYNOTES_MODULE_STATUS = -1;
    public static int COMMANDCENTER_MODULE_STATUS = -1;
    public static int TASKS_MODULE_STATUS = -1;
    public static int SYSTEMMAP_MODULE_STATUS = -1;
    private static Color allModulesEndColor = new Color(30, 30, 30);
    private static Color allModulesStartColor[] = { new Color(17, 27, 56), new Color(44, 78, 28), new Color(66, 17, 60), new Color(90, 18, 18), new Color(180, 91, 23), new Color(131, 110, 11), new Color(81, 72, 42) };

    public static void rc3rdpartyApps(int _action, BasicDesktop _desktop) {
	/**
	 * REPORTING
	 * JFREE 0.8.2
	 *  
	 * */
	JFreeReportBoot.getInstance().start();
    }

    public static void rcMainModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (MAIN_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting MAIN");
		    Environment.jpStatusBar.setAction("Loading Main");
		    MAIN_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setEnabledAt(_desktop.getIdDesktop(), true);
		    Environment.mainTabbedPane.setIconAt(_desktop.getIdDesktop(), IconTypes.close_session_32x32);
		    Environment.mainTabbedPane.setOpaque(false);
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), allModulesStartColor[_desktop.getIdDesktop()]);
		    _desktop.setStartColor(allModulesStartColor[_desktop.getIdDesktop()]);
		    _desktop.setEndColor(allModulesEndColor);
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    try {
			//Environment.mp3Player = new StandalonePlayer();
			//Environment.mp3Player.setDesktop(_desktop);
		    } catch (NoClassDefFoundError x) {
			x.printStackTrace();
		    }

		    /**BOTONES*/
		    /**
		      * BOTON SALIR
		      * */
		    DesktopButton btnExit = new DesktopButton("Salir");
		    btnExit.setRolloverEnabled(true);
		    btnExit.setIcon(IconTypes.exit_32x32);
		    btnExit.setRolloverIcon(IconTypes.exit_ro_32x32);
		    btnExit.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					closeApplication();
				    }

				}
		    );
		    _desktop.addButton(btnExit);
		    /**
		     * Boton del WebMail
		     * */
		    DesktopButton btnWebMail = new DesktopButton("WebMail");
		    btnWebMail.setRolloverEnabled(true);
		    btnWebMail.setIcon(IconTypes.webmail_32x32);
		    btnWebMail.setRolloverIcon(IconTypes.webmail_ro_32x32);
		    btnWebMail.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   BrowserLauncher.openURL("http://www.digitallsh.com.ar/");
				       }

				   }
		    );
		    _desktop.addButton(btnWebMail);
		    /**
		     * Boton TEST
		     * */
		    DesktopButton btnTest = new DesktopButton("TEST");
		    btnTest.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					// TODO
					// CAMBIAR EL CONSTRUCTOR!!!
					/*FileManTransfersPanel fileTran = new FileManTransfersPanel(Environment.sessionUser, Environment.jpStatusBar);
				        Environment.mainDesktop.add(fileTran);
				        fileTran.setVisible(true);
				        DInternalFrame _temp = new DInternalFrame();
				        Environment.mainDesktop.add(_temp);
				        _temp.setVisible(true);*/
					/*String[] m = new String[1];
				        m[0] = "santiago";
				        Mail.postMail(m, "asunto","texto", "santiago@digitallsh.com.ar", false, "");*/
					//Mail.fetchMail();
					/*StickyNote note1 = new StickyNote("prueba1", 0, 0);
				        StickyNote note2 = new StickyNote("prueba2", 20, 20);
				        StickyNote note3 = new StickyNote("prueba3", 50, 100);*/
					/*Editor editor = new Editor("Doc. nuevo", "Pag. nueva", false, "", true, "");
				        editor.setModal(true);
				        editor.setVisible(true);*/
				    }

				}
		    );
		    //_desktop.addButton(btnTest);
		    /**
		      * DICCIONARIO DEL SISTEMA
		      * */
		    ExtendedInternalFrame dictionary = new ExtendedInternalFrame("Dictionary");
		    PanelDictionary panelDictionary = new PanelDictionary();
		    dictionary.setCentralPanel(panelDictionary);
		    dictionary.setClosable(false);
		    dictionary.setDesktop(_desktop);
		    dictionary.setIcon(true);
		    /**
		     * INFORMACION DEL SISTEMA
		     * */
		    ExtendedInternalFrame systemInfo = new ExtendedInternalFrame("Info del Sistema");
		    SystemInfo jpSystemInfo = new SystemInfo();
		    systemInfo.setCentralPanel(jpSystemInfo);
		    systemInfo.setClosable(false);
		    systemInfo.setDesktop(_desktop);
		    systemInfo.setIcon(true);
		    /**
		     * SQL IMPORTER
		     * */
		    if (Environment.sessionUser.equals("admin")) {
			ExtendedInternalFrame sqlImport = new ExtendedInternalFrame("SQLImport");
			SQLImport jpSQLImport = new SQLImport();
			sqlImport.setCentralPanel(jpSQLImport);
			sqlImport.setClosable(false);
			sqlImport.setDesktop(_desktop);
			sqlImport.setIcon(true);
		    }
		    /**
		     * BOTON CERRAR SESION
		     * */
		    DesktopButton btnCloseSession = new DesktopButton("Cerrar<br>Sesion");
		    btnCloseSession.setRolloverEnabled(true);
		    btnCloseSession.setIcon(IconTypes.close_session_32x32);
		    btnCloseSession.setRolloverIcon(IconTypes.close_session_ro_32x32);
		    btnCloseSession.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						if (e.getModifiers() == 17) {
						    ChangePassword chgPasswd = new ChangePassword(Environment.sessionUser);
						    chgPasswd.setModal(true);
						    chgPasswd.setVisible(true);
						}
						/*else {
					            try {
					                if (Advisor.question("Cerrar Sesion", "¿Esta seguro que desea cerrar su sesion?")) {
					                    //Close all modules and connections
					                    endDesktop();
					                    Login inicio = new Login(this, Environment.organization, Environment.developer, true);
					                    ComponentsManager.centerWindow(inicio);
					                    inicio.setModal(true);
					                    inicio.show();
					                    if (inicio.getValidado()) {
					                        startDesktop();
					                    } else {
					                        System.exit(0);
					                    }
					                }
					            } catch (Exception x) {
					                x.printStackTrace();
					            }
					        }*/
					    }

					}
		    );
		    //_desktop.addButton(btnCloseSession);
		    /**
		     * Instancio la fabulosa calculadora de coordenadas
		     * */
		    /*ExtendedInternalFrame geoCalc = new ExtendedInternalFrame("Calculadora geografica", IconTypes.geocalc_32x32);
		    CoordinateCalculator geoCalcPanel = new CoordinateCalculator();
		    geoCalc.setClosable(false);
		    geoCalc.setCentralPanel(geoCalcPanel);
		    geoCalc.setDesktop(_desktop);
		    geoCalc.setIcon(true);*/

		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    MAIN_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Main loaded");
		    Debug.println("MAIN boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("MAIN already running!");
		}
		break;
	    case INIT_HALT:
		if (MAIN_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("MAIN halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcChatModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (CHAT_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting CHAT");
		    Environment.jpStatusBar.setAction("Loading ChatWithYou");
		    CHAT_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setEnabledAt(_desktop.getIdDesktop(), true);
		    Environment.mainTabbedPane.setIconAt(_desktop.getIdDesktop(), IconTypes.chat_32x32);
		    //Environment.mainTabbedPane.updateUI();
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    /*ChWYChatPanel chatPanel = new ChWYChatPanel(Environment.sessionUser, Environment.jpStatusBar);
		    chatPanel.setDesktop(_desktop);
		    chatPanel.show();*/
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    CHAT_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("ChatWithYou loaded");
		    Debug.println("CHAT boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("CHAT already running!");
		}
		break;
	    case INIT_HALT:
		if (CHAT_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("CHAT halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcStickyNotesModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (STICKYNOTES_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting STICKYNOTES");
		    Environment.jpStatusBar.setAction("Loading StickyNotes");
		    STICKYNOTES_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setEnabledAt(_desktop.getIdDesktop(), true);
		    Environment.mainTabbedPane.setIconAt(_desktop.getIdDesktop(), IconTypes.stickynotes_32x32);
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), allModulesStartColor[_desktop.getIdDesktop()]);
		    _desktop.setStartColor(allModulesStartColor[_desktop.getIdDesktop()]);
		    _desktop.setEndColor(allModulesEndColor);
		    STICKYNOTES_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("StickyNotes loaded");
		    Debug.println("STICKYNOTES boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("STICKYNOTES already running!");
		}
		break;
	    case INIT_HALT:
		if (STICKYNOTES_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("STICKYNOTES halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcCommandCenterModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (COMMANDCENTER_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting COMMAND CENTER");
		    Environment.jpStatusBar.setAction("Loading Command Center");
		    COMMANDCENTER_MODULE_STATUS = STATUS_STARTING;
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    try {
			ResultSet cards = LibSQL.exQuery("SELECT idcard FROM org.cards ORDER BY idcard");
			while (cards.next()) {
			    BasicCard _card = new BasicCard(cards.getInt("idcard"));
			    //, PurchaseOrdersList.class.getResource("xml/purchaseOrdersListReport.xml"));
			    _card.setClosable(false);
			    _card.setDesktop(_desktop);
			    _card.show();
			    Environment.addUnfinishedTask(_card);
			}
		    } catch (Exception e) {
			Advisor.messageBox("Error fetching cards", "");
		    }
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    COMMANDCENTER_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Command Center loaded");
		    Debug.println("COMMAND CENTER boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("COMMAND CENTER already running!");
		}
		break;
	    case INIT_HALT:
		if (COMMANDCENTER_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("COMMAND CENTER halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcSystemMapModule(int _action, final BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		Debug.println("Booting GEA ORAN");
		Environment.jpStatusBar.setAction("Loading GIS");

		GaiaEnvironment.scheme = "gis_oran";

		LayerGroup mainGroup = new LayerGroup("Grupo Principal");
		LayerGroup queryGroup = new LayerGroup("Consultas");
		LayerGroup cordonGroup = new LayerGroup("Grupo Cordón Vereda");
		LayerGroup barriosGroup = new LayerGroup("Grupo Barrios");
		LayerGroup canalesGroup = new LayerGroup("Grupo Canales");
		LayerGroup curvasGroup = new LayerGroup("Grupo Curvas");
		LayerGroup fraccionesGroup = new LayerGroup("Grupo Fracciones");
		LayerGroup tipoSueloGroup = new LayerGroup("Grupo Tipo de Suelo");
		LayerGroup formsGroup = new LayerGroup("Grupo Formularios");
		LayerGroup capas_2009_06_30 = new LayerGroup("Capas agregadas el 30/06/2009");

		GaiaEnvironment.layerGroups.add(mainGroup);
		GaiaEnvironment.layerGroups.add(queryGroup);
		GaiaEnvironment.layerGroups.add(cordonGroup);
		GaiaEnvironment.layerGroups.add(barriosGroup);
		GaiaEnvironment.layerGroups.add(canalesGroup);
		GaiaEnvironment.layerGroups.add(curvasGroup);
		GaiaEnvironment.layerGroups.add(fraccionesGroup);
		GaiaEnvironment.layerGroups.add(tipoSueloGroup);
		GaiaEnvironment.layerGroups.add(formsGroup);

		BasicDrawEngine cityMap = new BasicDrawEngine("GIS Oran", new BasicLabel());

		GaiaEnvironment.drawEngine = cityMap;
	    
		cityMap.setMapExtents(4359208.5097, 7437021.4128, 4374708.0850, 7450585.4036);
		cityMap.setProjectionType(CoordinateSystems.GK);
		CoordinateViewer coordinateViewer = new CoordinateViewer();
		coordinateViewer.setClosable(false);
		cityMap.setCoordinateViewer(coordinateViewer);
		coordinateViewer.setTitle("");

		cityMap.setBounds(_desktop.getBounds());
		cityMap.setVisible(true);
	
		GeometrySet gsetCurvas_nivel_1m = new GeometrySet("gis_oran", "curvas_de_nivel_1m", "the_geom", "1=1", "gid");
		GeometrySet gsetCurvas_nivel_1m_textos = new GeometrySet("gis_oran", "curvas_de_nivel_1m_txt", "the_geom", "1=1", "gid");
		GeometrySet gsetCurvas_nivel_20m = new GeometrySet("gis_oran", "curvas_de_nivel_020m", "the_geom", "1=1", "gid");
		GeometrySet gsetAreas_deportivas = new GeometrySet("gis_oran", "areas_deportivas_poligono", "the_geom", "1=1", "gid");
		GeometrySet gsetDesmontes = new GeometrySet("gis_oran", "desmontes", "the_geom", "1=1", "gid");
		GeometrySet gsetCalles = new GeometrySet("gis_oran", "calles", "the_geom", "1=1", "gid");
		GeometrySet gsetCordon_vereda = new GeometrySet("gis_oran", "cordon_vereda", "the_geom", "1=1", "gid");
		GeometrySet gsetManzanas = new GeometrySet("gis_oran", "manzanas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetManzanas_numeros = new GeometrySet("gis_oran", "manzanas_numeros", "the_geom", "1=1", "gid");
		GeometrySet gsetParcelas_numeros = new GeometrySet("gis_oran", "parcelas_numeros", "the_geom", "1=1", "gid");
		GeometrySet gsetCatastro_numeros = new GeometrySet("gis_oran", "catastro_numeros", "the_geom", "1=1", "gid");
		GeometrySet gsetParcelas = new GeometrySet("gis_oran", "parcelas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetParcelas_vinculadas = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetPavimento = new GeometrySet("gis_oran", "pavimento", "the_geom", "1=1", "gid");
		GeometrySet gsetBarrios_limites = new GeometrySet("gis_oran", "barrios_limites", "the_geom", "1=1", "gid");
		GeometrySet gsetBarrios_nombres = new GeometrySet("gis_oran", "barrios_nombres", "the_geom", "1=1", "gid");
		GeometrySet gsetCaminos_vecinales_tierra = new GeometrySet("gis_oran", "caminos_vecinales_tierra", "the_geom", "1=1", "gid");
		GeometrySet gsetCloacas_puntos = new GeometrySet("gis_oran", "cloacas_puntos", "the_geom", "1=1", "gid");
		GeometrySet gsetCota_cloacas_puntos = new GeometrySet("gis_oran", "cota_cloacas_puntos", "the_geom", "1=1", "gid");
		GeometrySet gsetCota_cloacas_textos = new GeometrySet("gis_oran", "cota_cloacas_textos", "the_geom", "1=1", "gid");
		GeometrySet gsetReddeaguapotable = new GeometrySet("gis_oran", "agua_canos", "the_geom", "1=1", "gid");
		GeometrySet gsetReddeaguapotable_textos = new GeometrySet("gis_oran", "agua_textos", "the_geom", "1=1", "gid");
		GeometrySet gsetCanales_existentes = new GeometrySet("gis_oran", "canales_existentes", "the_geom", "1=1", "gid");
		GeometrySet gsetCanales_existentes_nombres = new GeometrySet("gis_oran", "canales_existentes_nombre", "the_geom", "1=1", "gid");
		GeometrySet gsetCanales_proyectados = new GeometrySet("gis_oran", "canales_proyectados", "the_geom", "1=1", "gid");
		GeometrySet gsetCanales_proyectados_nombres = new GeometrySet("gis_oran", "canales_proyectados_nombre", "the_geom", "1=1", "gid");
		GeometrySet gsetEspacios_verdes = new GeometrySet("gis_oran", "espacios_verdes", "the_geom", "1=1", "gid");
		GeometrySet gsetFracciones_rurales = new GeometrySet("gis_oran", "fracciones_rurales", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetFracciones_rurales_catastro = new GeometrySet("gis_oran", "fracciones_rurales_catastro", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetFracciones_rurales_nombre = new GeometrySet("gis_oran", "fracciones_rurales_nombre", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetRuta50_alambrados = new GeometrySet("gis_oran", "ruta50_alambrados", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetRuta50_ejeytraza = new GeometrySet("gis_oran", "ruta50_ejeytraza", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_a = new GeometrySet("gis_oran", "tipo_de_suelo_categoria_a", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_b = new GeometrySet("gis_oran", "tipo_de_suelo_calificacion_b", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_c = new GeometrySet("gis_oran", "tipo_de_suelo_calificacion_c", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_d = new GeometrySet("gis_oran", "tipo_de_suelo_calificacion_d", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_arcilloso = new GeometrySet("gis_oran", "tipo_de_suelo_contorno_suelo_arcilloso", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_arcilloso_hatch = new GeometrySet("gis_oran", "tipo_de_suelo_contorno_suelo_arcilloso_hatch", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_arenoso = new GeometrySet("gis_oran", "tipo_de_suelo_contorno_suelo_arenoso_poligono", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetTipo_suelo_arenoso_hatch = new GeometrySet("gis_oran", "tipo_de_suelo_contorno_suelo_arenoso_hatch", "the_geom", "1=1 AND isvalid(the_geom)", "gid");
		GeometrySet gsetAdminyserv = new GeometrySet("gis_oran", "administracionyservicios", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetComercios = new GeometrySet("gis_oran", "comercios", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetCultoycultura = new GeometrySet("gis_oran", "cultosycultura", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetEducacion = new GeometrySet("gis_oran", "educacion", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetIndustria = new GeometrySet("gis_oran", "industrias", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetProduccion = new GeometrySet("gis_oran", "produccion", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetSalud = new GeometrySet("gis_oran", "salud", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetCasosEnfermedades = new GeometrySet("gis_oran", "casosenfermedades", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetSeguridad = new GeometrySet("gis_oran", "seguridad", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetServurbanos = new GeometrySet("gis_oran", "serviciosurbanos", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetTurismo = new GeometrySet("gis_oran", "turismo", "the_geom", "estado != '*'", "gid");
		GeometrySet gsetConsultas_01 = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetConsultas_02 = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetConsultas_03 = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetConsultas_04 = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetConsultas_05 = new GeometrySet("gis_oran", "parcelas_vinculadas", "the_geom", "1=1 AND st_isvalid(the_geom)", "gid");
		GeometrySet gsetAeropuerto = new GeometrySet("gis_oran", "aeropuerto", "the_geom", "1=1", "gid");
		GeometrySet gsetArea_de_estudio = new GeometrySet("gis_oran", "area_de_estudio", "the_geom", "1=1", "gid");
		GeometrySet gsetBarrios_ipv = new GeometrySet("gis_oran", "barrios_ipv", "the_geom", "1=1", "gid");
		GeometrySet gsetBasural_municipal = new GeometrySet("gis_oran", "basural_municipal", "the_geom", "1=1", "gid");
		GeometrySet gsetCaminos_vecinales = new GeometrySet("gis_oran", "caminos_vecinales", "the_geom", "1=1", "gid");
		GeometrySet gsetCamping_el_cedral = new GeometrySet("gis_oran", "camping_el_cedral", "the_geom", "1=1", "gid");
		GeometrySet gsetCasa_divina_providencia = new GeometrySet("gis_oran", "casa_divina_providencia", "the_geom", "1=1", "gid");
		GeometrySet gsetConstruccion_ipv = new GeometrySet("gis_oran", "construccion_ipv", "the_geom", "1=1", "gid");
		GeometrySet gsetConstrucciones_area_suburbana = new GeometrySet("gis_oran", "construcciones_area_suburbana", "the_geom", "1=1", "gid");
		GeometrySet gsetCortadora_de_ladrillos = new GeometrySet("gis_oran", "cortadora_de_ladrillos", "the_geom", "1=1", "gid");
		GeometrySet gsetFfcc = new GeometrySet("gis_oran", "ffcc", "the_geom", "1=1", "gid");
		GeometrySet gsetFutura_urbanizacion_ipv = new GeometrySet("gis_oran", "futura_urbanizacion_ipv", "the_geom", "1=1", "gid");
		GeometrySet gsetLaguna_ptlc = new GeometrySet("gis_oran", "laguna_ptlc", "the_geom", "1=1", "gid");
		GeometrySet gsetMatadero_municipal = new GeometrySet("gis_oran", "matadero_municipal", "the_geom", "1=1", "gid");
		GeometrySet gsetNombre_fincas = new GeometrySet("gis_oran", "nombre_fincas", "the_geom", "1=1", "gid");
		GeometrySet gsetParcelas_con_cultivo = new GeometrySet("gis_oran", "parcelas_con_cultivo", "the_geom", "1=1", "gid");
		GeometrySet gsetTextos_nuevos = new GeometrySet("gis_oran", "textos_nuevos", "the_geom", "1=1", "gid");

		cityMap.addGeometrySet(gsetCurvas_nivel_1m);
		cityMap.addGeometrySet(gsetCurvas_nivel_1m_textos);
		cityMap.addGeometrySet(gsetCurvas_nivel_20m);
		cityMap.addGeometrySet(gsetAreas_deportivas);
		cityMap.addGeometrySet(gsetDesmontes);
		cityMap.addGeometrySet(gsetCalles);
		cityMap.addGeometrySet(gsetCordon_vereda);
		cityMap.addGeometrySet(gsetManzanas);
		cityMap.addGeometrySet(gsetManzanas_numeros);
		cityMap.addGeometrySet(gsetParcelas_numeros);
		cityMap.addGeometrySet(gsetCatastro_numeros);
		cityMap.addGeometrySet(gsetParcelas);
		cityMap.addGeometrySet(gsetParcelas_vinculadas);
		cityMap.addGeometrySet(gsetPavimento);
		cityMap.addGeometrySet(gsetBarrios_limites);
		cityMap.addGeometrySet(gsetBarrios_nombres);
		cityMap.addGeometrySet(gsetCaminos_vecinales_tierra);
		cityMap.addGeometrySet(gsetCloacas_puntos);
		cityMap.addGeometrySet(gsetCota_cloacas_puntos);
		cityMap.addGeometrySet(gsetCota_cloacas_textos);
		cityMap.addGeometrySet(gsetReddeaguapotable);
		cityMap.addGeometrySet(gsetReddeaguapotable_textos);
		cityMap.addGeometrySet(gsetCanales_existentes);
		cityMap.addGeometrySet(gsetCanales_existentes_nombres);
		cityMap.addGeometrySet(gsetCanales_proyectados);
		cityMap.addGeometrySet(gsetCanales_proyectados_nombres);
		cityMap.addGeometrySet(gsetEspacios_verdes);
		cityMap.addGeometrySet(gsetFracciones_rurales);
		cityMap.addGeometrySet(gsetFracciones_rurales_catastro);
		cityMap.addGeometrySet(gsetFracciones_rurales_nombre);
		cityMap.addGeometrySet(gsetRuta50_alambrados);
		cityMap.addGeometrySet(gsetRuta50_ejeytraza);
		cityMap.addGeometrySet(gsetTipo_suelo_a);
		cityMap.addGeometrySet(gsetTipo_suelo_b);
		cityMap.addGeometrySet(gsetTipo_suelo_c);
		cityMap.addGeometrySet(gsetTipo_suelo_d);
		cityMap.addGeometrySet(gsetTipo_suelo_arcilloso);
		cityMap.addGeometrySet(gsetTipo_suelo_arcilloso_hatch);
		cityMap.addGeometrySet(gsetTipo_suelo_arenoso);
		cityMap.addGeometrySet(gsetTipo_suelo_arenoso_hatch);
		cityMap.addGeometrySet(gsetAdminyserv);
		cityMap.addGeometrySet(gsetComercios);
		cityMap.addGeometrySet(gsetCultoycultura);
		cityMap.addGeometrySet(gsetEducacion);
		cityMap.addGeometrySet(gsetIndustria);
		cityMap.addGeometrySet(gsetProduccion);
		cityMap.addGeometrySet(gsetSalud);
		cityMap.addGeometrySet(gsetCasosEnfermedades);
		cityMap.addGeometrySet(gsetSeguridad);
		cityMap.addGeometrySet(gsetServurbanos);
		cityMap.addGeometrySet(gsetTurismo);
		cityMap.addGeometrySet(gsetConsultas_01);
		cityMap.addGeometrySet(gsetConsultas_02);
		cityMap.addGeometrySet(gsetConsultas_03);
		cityMap.addGeometrySet(gsetConsultas_04);
		cityMap.addGeometrySet(gsetConsultas_05);
		cityMap.addGeometrySet(gsetAeropuerto);
		cityMap.addGeometrySet(gsetArea_de_estudio);
		cityMap.addGeometrySet(gsetBarrios_ipv);
		cityMap.addGeometrySet(gsetBasural_municipal);
		cityMap.addGeometrySet(gsetCaminos_vecinales);
		cityMap.addGeometrySet(gsetCamping_el_cedral);
		cityMap.addGeometrySet(gsetCasa_divina_providencia);
		cityMap.addGeometrySet(gsetConstruccion_ipv);
		cityMap.addGeometrySet(gsetConstrucciones_area_suburbana);
		cityMap.addGeometrySet(gsetCortadora_de_ladrillos);
		cityMap.addGeometrySet(gsetFfcc);
		cityMap.addGeometrySet(gsetFutura_urbanizacion_ipv);
		cityMap.addGeometrySet(gsetLaguna_ptlc);
		cityMap.addGeometrySet(gsetMatadero_municipal);
		cityMap.addGeometrySet(gsetNombre_fincas);
		cityMap.addGeometrySet(gsetParcelas_con_cultivo);
		cityMap.addGeometrySet(gsetTextos_nuevos);

		Layer curvas_nivel_1m = new Layer("Curvas de nivel (1m)", gsetCurvas_nivel_1m);
		curvas_nivel_1m.setColor(new Color(29, 85, 226));
		curvas_nivel_1m.setMouseOverColor(Color.CYAN);

		Layer curvas_nivel_1m_textos = new Layer("Curvas de nivel (1m - Textos)", gsetCurvas_nivel_1m_textos, "maptext");
		curvas_nivel_1m_textos.setColor(new Color(29, 85, 226));
		curvas_nivel_1m_textos.setPointDiameter(50);
		curvas_nivel_1m_textos.setDrawGeometries(false);
		curvas_nivel_1m_textos.setMouseOverColor(Color.CYAN);

		Layer curvas_nivel_20m = new Layer("Curvas de nivel (20m)", gsetCurvas_nivel_20m);
		curvas_nivel_20m.setColor(new Color(152, 152, 152));
		curvas_nivel_20m.setMouseOverColor(Color.CYAN);

		Layer areas_deportivas = new Layer("Áreas deportivas", gsetAreas_deportivas);
		areas_deportivas.setColor(Color.BLUE);
		areas_deportivas.setFillColor(Color.BLUE);
		areas_deportivas.setMouseOverColor(Color.ORANGE);

		Layer desmontes = new Layer("Desmontes", gsetDesmontes);
		desmontes.setColor(Color.RED);
		desmontes.setMouseOverColor(Color.ORANGE);

	        Layer calles = new Layer("Calles", gsetCalles, "nombre");
	        calles.setColor(Color.lightGray);
	        calles.setMouseOverColor(Color.CYAN);
	        calles.setQueryable(true);
		calles.setLabelFontSize(16);
		GaiaEnvironment.streetsLayer = calles.getAlias();
	        //ExtendedInternalFrame streetsPanelContainer = new ExtendedInternalFrame("Calles");
	        //GaiaStreetsPanel streetsPanel = new GaiaStreetsPanel();
	        //streetsPanelContainer.setCentralPanel(streetsPanel);
	        //calles.setConfigPanel(streetsPanelContainer);

		Layer cordon_vereda = new Layer("Cordón Vereda", gsetCordon_vereda);
		cordon_vereda.setColor(Color.lightGray);
		cordon_vereda.setMouseOverColor(Color.CYAN);

		Layer manzanas = new Layer("Manzanas", gsetManzanas);
		manzanas.setColor(Color.RED);
		manzanas.setMouseOverColor(Color.MAGENTA);

		Layer manzanas_numeros = new Layer("Nº Manzanas", gsetManzanas_numeros, "maptext");
		manzanas_numeros.setColor(Color.RED);
		manzanas_numeros.setMouseOverColor(Color.CYAN);
		manzanas_numeros.setPointDiameter(6);

		Layer parcelas_numeros = new Layer("Nº Parcelas", gsetParcelas_numeros, "maptext");
		parcelas_numeros.setColor(Color.MAGENTA);
		parcelas_numeros.setMouseOverColor(Color.CYAN);
		parcelas_numeros.setPointDiameter(6);

		Layer catastro_numeros = new Layer("Nº Catastros", gsetCatastro_numeros, "maptext");
		catastro_numeros.setColor(Color.BLACK);
		catastro_numeros.setMouseOverColor(Color.CYAN);
		catastro_numeros.setDrawGeometries(false);
		catastro_numeros.setPointDiameter(6);

		Layer parcelas = new Layer("Parcelas", gsetParcelas);
		parcelas.setColor(Color.BLACK);
		parcelas.setMouseOverColor(Color.MAGENTA);
	    
		Layer parcelas_vinculadas = new Layer("Parcelas Vinculadas", gsetParcelas_vinculadas);
		parcelas_vinculadas.setColor(Color.MAGENTA);
		parcelas_vinculadas.setMouseOverColor(Color.MAGENTA);

		Layer pavimento = new Layer("Pavimento", gsetPavimento, "idtipopavi");
		pavimento.setColor(Color.BLACK);
		pavimento.setMouseOverColor(Color.CYAN);
		pavimento.setQueryable(true);
		pavimento.addFilter("Pavimento 0", "idtipopavi", "(idtipopavi = 0)", new Color(255, 0, 0), 3, "Pavimento 0");
		pavimento.addFilter("Pavimento 1", "idtipopavi", "(idtipopavi = 1)", new Color(70, 70, 70), 3, "Pavimento 1");
		pavimento.addFilter("Pavimento 2", "idtipopavi", "(idtipopavi = 2)", new Color(180, 100, 0), 3, "Pavimento 2");
		pavimento.addFilter("Pavimento 3", "idtipopavi", "(idtipopavi = 3)", new Color(170, 170, 170), 3, "Pavimento 3");
		pavimento.addFilter("Pavimento 4", "idtipopavi", "(idtipopavi = 4)", new Color(180, 100, 0), 3, "Pavimento 4");

		Layer barrios_limites = new Layer("Barrios (Límites)", gsetBarrios_limites);
		barrios_limites.setColor(Color.BLACK);
		barrios_limites.setMouseOverColor(Color.CYAN);
		barrios_limites.setQueryable(true);

		Layer barrios_nombres = new Layer("Barrios (Nombres)", gsetBarrios_nombres, "maptext");
		barrios_nombres.setColor(Color.BLACK);
		barrios_nombres.setMouseOverColor(Color.CYAN);
		barrios_nombres.setDrawGeometries(false);
		barrios_nombres.setPointDiameter(48);

		Layer caminos_vecinales_tierra = new Layer("Caminos vecinales (Tierra)", gsetCaminos_vecinales_tierra);
		caminos_vecinales_tierra.setColor(Color.RED);
		caminos_vecinales_tierra.setMouseOverColor(Color.CYAN);
		caminos_vecinales_tierra.setQueryable(true);

		Layer cloacas_puntos = new Layer("Red Cloacal (Registros)", gsetCloacas_puntos);
		cloacas_puntos.setColor(Color.BLACK);
		cloacas_puntos.setMouseOverColor(Color.CYAN);
		cloacas_puntos.setQueryable(true);
		cloacas_puntos.setPointDiameter(1);
		cloacas_puntos.addFilter("Tipo 0", "idtipo", "(idtipo = 0)", new Color(0, 255, 0), 2, "Tipo 0");
		cloacas_puntos.addFilter("Tipo 1", "idtipo", "(idtipo = 1)", new Color(255, 220, 168), 2, "Tipo 1");

		Layer cota_cloacas_puntos = new Layer("Red Cloacal (Cotas)", gsetCota_cloacas_puntos);
		cota_cloacas_puntos.setColor(Color.RED);
		cota_cloacas_puntos.setMouseOverColor(Color.CYAN);
		cota_cloacas_puntos.setQueryable(true);
		cota_cloacas_puntos.setPointDiameter(1);

		Layer cota_cloacas_textos = new Layer("Red Cloacal (Cotas_Textos)", gsetCota_cloacas_textos, "maptext", "", "maptext");
		cota_cloacas_textos.setColor(Color.RED);
		cota_cloacas_textos.setMouseOverColor(Color.CYAN);
		cota_cloacas_textos.setQueryable(true);
		cota_cloacas_textos.setDrawGeometries(false);
		cota_cloacas_textos.setPointDiameter(20);

		Layer reddeaguapotable = new Layer("Red de Agua Potable", gsetReddeaguapotable);
		reddeaguapotable.setColor(Color.BLACK);
		reddeaguapotable.setMouseOverColor(Color.CYAN);
		reddeaguapotable.setQueryable(true);
		reddeaguapotable.addFilter("ID Diámetro 0", "iddiametro", "(iddiametro = 0)", new Color(0, 255, 0), 2, "ID Diámetro 0");
		reddeaguapotable.addFilter("ID Diámetro 1", "iddiametro", "(iddiametro = 1)", new Color(255, 220, 168), 2, "ID Diámetro 1");
		reddeaguapotable.addFilter("ID Diámetro 2", "iddiametro", "(iddiametro = 2)", new Color(255, 168, 88), 2, "ID Diámetro 2");
		reddeaguapotable.addFilter("ID Diámetro 3", "iddiametro", "(iddiametro = 3)", new Color(255, 128, 0), 2, "ID Diámetro 3");
		reddeaguapotable.addFilter("ID Diámetro 4", "iddiametro", "(iddiametro = 4)", new Color(255, 192, 255), 2, "ID Diámetro 4");
		reddeaguapotable.addFilter("ID Diámetro 5", "iddiametro", "(iddiametro = 5)", new Color(255, 0, 255), 2, "ID Diámetro 5");
		reddeaguapotable.addFilter("ID Diámetro 6", "iddiametro", "(iddiametro = 6)", new Color(192, 0, 192), 2, "ID Diámetro 6");
		reddeaguapotable.addFilter("ID Diámetro 7", "iddiametro", "(iddiametro = 7)", new Color(0, 255, 255), 2, "ID Diámetro 7");
		reddeaguapotable.addFilter("ID Diámetro 8", "iddiametro", "(iddiametro = 8)", new Color(0, 192, 192), 2, "ID Diámetro 8");
		reddeaguapotable.addFilter("ID Diámetro 9", "iddiametro", "(iddiametro = 9)", new Color(0, 128, 128), 2, "ID Diámetro 9");
		reddeaguapotable.addFilter("ID Diámetro 10", "iddiametro", "(iddiametro = 10)", new Color(255, 155, 192), 2, "ID Diámetro 10");
		reddeaguapotable.addFilter("ID Diámetro 11", "iddiametro", "(iddiametro = 11)", new Color(255, 255, 0), 2, "ID Diámetro 11");
		reddeaguapotable.addFilter("ID Diámetro 12", "iddiametro", "(iddiametro = 12)", new Color(192, 192, 0), 2, "ID Diámetro 12");
		reddeaguapotable.addFilter("ID Diámetro 13", "iddiametro", "(iddiametro = 13)", new Color(192, 192, 255), 2, "ID Diámetro 13");

		Layer reddeaguapotable_textos = new Layer("Red de Agua Potable (Textos)", gsetReddeaguapotable_textos, "maptext", "", "maptext");
		reddeaguapotable_textos.setColor(Color.BLACK);
		reddeaguapotable_textos.setMouseOverColor(Color.CYAN);
		reddeaguapotable_textos.setQueryable(true);
		reddeaguapotable_textos.addFilter("ID Diámetro 0", "iddiametro", "(iddiametro = 0)", new Color(0, 255, 0), 2, "ID Diámetro 0");
		reddeaguapotable_textos.addFilter("ID Diámetro 1", "iddiametro", "(iddiametro = 1)", new Color(255, 220, 168), 2, "ID Diámetro 1");
		reddeaguapotable_textos.addFilter("ID Diámetro 2", "iddiametro", "(iddiametro = 2)", new Color(255, 168, 88), 2, "ID Diámetro 2");
		reddeaguapotable_textos.addFilter("ID Diámetro 3", "iddiametro", "(iddiametro = 3)", new Color(255, 128, 0), 2, "ID Diámetro 3");
		reddeaguapotable_textos.addFilter("ID Diámetro 4", "iddiametro", "(iddiametro = 4)", new Color(255, 192, 255), 2, "ID Diámetro 4");
		reddeaguapotable_textos.addFilter("ID Diámetro 5", "iddiametro", "(iddiametro = 5)", new Color(255, 0, 255), 2, "ID Diámetro 5");
		reddeaguapotable_textos.addFilter("ID Diámetro 6", "iddiametro", "(iddiametro = 6)", new Color(192, 0, 192), 2, "ID Diámetro 6");
		reddeaguapotable_textos.addFilter("ID Diámetro 7", "iddiametro", "(iddiametro = 7)", new Color(0, 255, 255), 2, "ID Diámetro 7");
		reddeaguapotable_textos.addFilter("ID Diámetro 8", "iddiametro", "(iddiametro = 8)", new Color(0, 192, 192), 2, "ID Diámetro 8");
		reddeaguapotable_textos.addFilter("ID Diámetro 9", "iddiametro", "(iddiametro = 9)", new Color(0, 128, 128), 2, "ID Diámetro 9");
		reddeaguapotable_textos.addFilter("ID Diámetro 10", "iddiametro", "(iddiametro = 10)", new Color(255, 155, 192), 2, "ID Diámetro 10");
		reddeaguapotable_textos.addFilter("ID Diámetro 11", "iddiametro", "(iddiametro = 11)", new Color(255, 255, 0), 2, "ID Diámetro 11");
		reddeaguapotable_textos.addFilter("ID Diámetro 12", "iddiametro", "(iddiametro = 12)", new Color(192, 192, 0), 2, "ID Diámetro 12");
		reddeaguapotable_textos.addFilter("ID Diámetro 13", "iddiametro", "(iddiametro = 13)", new Color(192, 192, 255), 2, "ID Diámetro 13");

		Layer canales_existentes = new Layer("Canales existentes", gsetCanales_existentes);
		canales_existentes.setColor(Color.BLACK);
		canales_existentes.setMouseOverColor(Color.CYAN);
		canales_existentes.setQueryable(true);

		Layer canales_existentes_nombres = new Layer("Canales existentes (Nombres)", gsetCanales_existentes_nombres, "maptext");
		canales_existentes_nombres.setColor(Color.BLACK);
		canales_existentes_nombres.setMouseOverColor(Color.CYAN);
		canales_existentes_nombres.setQueryable(true);

		Layer canales_proyectados = new Layer("Canales proyectados", gsetCanales_proyectados);
		canales_proyectados.setColor(Color.GRAY);
		canales_proyectados.setMouseOverColor(Color.CYAN);
		canales_proyectados.setQueryable(true);

		Layer canales_proyectados_nombres = new Layer("Canales proyectados (Nombres)", gsetCanales_proyectados_nombres, "maptext");
		canales_proyectados_nombres.setColor(Color.GRAY);
		canales_proyectados_nombres.setMouseOverColor(Color.CYAN);
		canales_proyectados_nombres.setQueryable(true);

		Layer espacios_verdes = new Layer("Espacios Verdes", gsetEspacios_verdes);
		espacios_verdes.setColor(new Color(0, 153, 51));
		espacios_verdes.setFillColor(new Color(0, 153, 51));
		espacios_verdes.setMouseOverColor(Color.ORANGE);

		Layer fracciones_rurales = new Layer("Fracciones Rurales", gsetFracciones_rurales);
		fracciones_rurales.setColor(new Color(0, 100, 255));
		fracciones_rurales.setMouseOverColor(Color.CYAN);
		fracciones_rurales.setQueryable(true);

		Layer fracciones_rurales_catastro = new Layer("Fracciones Rurales (Catastros)", gsetFracciones_rurales_catastro, "maptext");
		fracciones_rurales_catastro.setColor(new Color(0, 100, 255));
		fracciones_rurales_catastro.setPointDiameter(40);
		fracciones_rurales_catastro.setDrawGeometries(false);
		fracciones_rurales_catastro.setMouseOverColor(Color.CYAN);
		fracciones_rurales_catastro.setQueryable(true);

		Layer fracciones_rurales_nombre = new Layer("Fracciones Rurales (Nombres)", gsetFracciones_rurales_nombre, "maptext");
		fracciones_rurales_nombre.setPointDiameter(40);
		fracciones_rurales_nombre.setDrawGeometries(false);
		fracciones_rurales_nombre.setColor(new Color(0, 100, 255));
		fracciones_rurales_nombre.setMouseOverColor(Color.CYAN);
		fracciones_rurales_nombre.setQueryable(true);

		Layer ruta50_alambrados = new Layer("Ruta 50 (Alambrados)", gsetRuta50_alambrados);
		ruta50_alambrados.setColor(new Color(0, 100, 255));
		ruta50_alambrados.setMouseOverColor(Color.CYAN);
		ruta50_alambrados.setQueryable(true);

		Layer ruta50_ejeytraza = new Layer("Ruta 50 (Eje y traza)", gsetRuta50_ejeytraza);
		ruta50_ejeytraza.setColor(new Color(0, 100, 255));
		ruta50_ejeytraza.setMouseOverColor(Color.CYAN);
		ruta50_ejeytraza.setQueryable(true);

		Layer tipo_suelo_a = new Layer("Tipo de Suelo (A)", gsetTipo_suelo_a, "maptext");
		tipo_suelo_a.setPointDiameter(40);
		tipo_suelo_a.setColor(new Color(100, 100, 255));
		tipo_suelo_a.setMouseOverColor(Color.CYAN);
		tipo_suelo_a.setQueryable(true);

		Layer tipo_suelo_b = new Layer("Tipo de Suelo (B)", gsetTipo_suelo_b, "maptext");
		tipo_suelo_b.setPointDiameter(40);
		tipo_suelo_b.setColor(new Color(120, 120, 255));
		tipo_suelo_b.setMouseOverColor(Color.CYAN);
		tipo_suelo_b.setQueryable(true);

		Layer tipo_suelo_c = new Layer("Tipo de Suelo (C)", gsetTipo_suelo_c, "maptext");
		tipo_suelo_c.setPointDiameter(40);
		tipo_suelo_c.setColor(new Color(140, 140, 255));
		tipo_suelo_c.setMouseOverColor(Color.CYAN);
		tipo_suelo_c.setQueryable(true);

		Layer tipo_suelo_d = new Layer("Tipo de Suelo (D)", gsetTipo_suelo_d, "maptext");
		tipo_suelo_d.setPointDiameter(40);
		tipo_suelo_d.setColor(new Color(160, 160, 255));
		tipo_suelo_d.setMouseOverColor(Color.CYAN);
		tipo_suelo_d.setQueryable(true);

		Layer tipo_suelo_arcilloso = new Layer("Tipo de Suelo (Arcilloso)", gsetTipo_suelo_arcilloso);
		tipo_suelo_arcilloso.setColor(new Color(255, 204, 0));
		tipo_suelo_arcilloso.setFillColor(new Color(255, 204, 0));
		tipo_suelo_arcilloso.setMouseOverColor(Color.CYAN);
		tipo_suelo_arcilloso.setQueryable(true);

		Layer tipo_suelo_arcilloso_hatch = new Layer("Tipo de Suelo (Arcilloso - Hatch)", gsetTipo_suelo_arcilloso_hatch);
		tipo_suelo_arcilloso_hatch.setColor(new Color(255, 204, 0));
		tipo_suelo_arcilloso_hatch.setFillColor(new Color(255, 204, 0));
		tipo_suelo_arcilloso_hatch.setMouseOverColor(Color.CYAN);
		tipo_suelo_arcilloso_hatch.setQueryable(true);

		Layer tipo_suelo_arenoso = new Layer("Tipo de Suelo (Arenoso)", gsetTipo_suelo_arenoso);
		tipo_suelo_arenoso.setColor(new Color(255, 255, 0));
		tipo_suelo_arenoso.setFillColor(new Color(255, 255, 0));
		tipo_suelo_arenoso.setMouseOverColor(Color.CYAN);
		tipo_suelo_arenoso.setQueryable(true);

		Layer tipo_suelo_arenoso_hatch = new Layer("Tipo de Suelo (Arenoso - Hatch)", gsetTipo_suelo_arenoso_hatch);
		tipo_suelo_arenoso_hatch.setColor(new Color(255, 255, 0));
		tipo_suelo_arenoso_hatch.setFillColor(new Color(255, 255, 0));
		tipo_suelo_arenoso_hatch.setMouseOverColor(Color.CYAN);
		tipo_suelo_arenoso_hatch.setQueryable(true);

                Layer adminyserv = new Layer("Administración y Servicios", gsetAdminyserv, "nombre", "", "nombre");
                adminyserv.setColor(new Color(255, 0, 0));
                adminyserv.setMouseOverColor(Color.CYAN);
                adminyserv.setPointDiameter(2);
                adminyserv.setEditable(true);
                GaiaFormAdminyServ formAdminyServ = new GaiaFormAdminyServ();
                formAdminyServ.setLayer(adminyserv);
            
                Layer comercios = new Layer("Comercios", gsetComercios, "nombre", "", "nombre");
                comercios.setColor(new Color(255, 0, 0));
                comercios.setMouseOverColor(Color.CYAN);
                comercios.setPointDiameter(2);
                comercios.setEditable(true);
                GaiaFormComercios formComercios = new GaiaFormComercios();
                formComercios.setLayer(comercios);
            
		Layer cultoycultura = new Layer("Cultos y Cultura", gsetCultoycultura, "nombre", "", "nombre");
		cultoycultura.setColor(new Color(255, 0, 0));
		cultoycultura.setMouseOverColor(Color.CYAN);
		cultoycultura.setPointDiameter(2);
		cultoycultura.setEditable(true);
		GaiaFormCultoyCultura formCultoyCultura = new GaiaFormCultoyCultura();
		formCultoyCultura.setLayer(cultoycultura);
                
                Layer educacion = new Layer("Educación", gsetEducacion, "nombre", "", "nombre");
                educacion.setColor(new Color(255, 0, 0));
                educacion.setMouseOverColor(Color.CYAN);
                educacion.setPointDiameter(2);
                educacion.setEditable(true);
                GaiaFormEducacion formEducacion = new GaiaFormEducacion();
                formEducacion.setLayer(educacion);
                
                Layer industria = new Layer("Industria", gsetIndustria, "nombre", "", "nombre");
                industria.setColor(new Color(255, 0, 0));
                industria.setMouseOverColor(Color.CYAN);
                industria.setPointDiameter(2);
                industria.setEditable(true);
                GaiaFormIndustria formIndustria = new GaiaFormIndustria();
                formIndustria.setLayer(industria);
                
                Layer produccion = new Layer("Produccion", gsetProduccion, "nombre", "", "nombre");
                produccion.setColor(new Color(255, 0, 0));
                produccion.setMouseOverColor(Color.CYAN);
                produccion.setPointDiameter(2);
                produccion.setEditable(true);
                GaiaFormProduccion formProduccion = new GaiaFormProduccion();
                formProduccion.setLayer(produccion);
                
                Layer salud = new Layer("Salud", gsetSalud, "nombre", "", "nombre");
                salud.setColor(new Color(255, 0, 0));
                salud.setMouseOverColor(Color.CYAN);
                salud.setPointDiameter(2);
                salud.setEditable(true);
                GaiaFormSalud formSalud = new GaiaFormSalud();
                formSalud.setLayer(salud);
               
		Layer casosEnfermedades = new Layer("Control y Prev. de Enfermedades endémicas", gsetCasosEnfermedades, "nombre", "", "nombre");
		casosEnfermedades.setColor(new Color(255, 0, 0));
		casosEnfermedades.setMouseOverColor(Color.CYAN);
		casosEnfermedades.setPointDiameter(2);
		casosEnfermedades.setEditable(true);
		GaiaFormCasosEnfermedad formCasosEnfermedad = new GaiaFormCasosEnfermedad();
		formCasosEnfermedad.setLayer(casosEnfermedades);
		
                Layer seguridad = new Layer("Seguridad", gsetSeguridad, "nombre", "", "nombre");
                seguridad.setColor(new Color(255, 0, 0));
                seguridad.setMouseOverColor(Color.CYAN);
                seguridad.setPointDiameter(2);
                seguridad.setEditable(true);
                GaiaFormSeguridad formSeguridad = new GaiaFormSeguridad();
                formSeguridad.setLayer(seguridad);
	    
                Layer servurbanos = new Layer("Servicios Urbanos", gsetServurbanos, "nombre", "", "nombre");
                servurbanos.setColor(new Color(255, 0, 0));
                servurbanos.setMouseOverColor(Color.CYAN);
                servurbanos.setPointDiameter(2);
                servurbanos.setEditable(true);
                GaiaFormServUrbanos formServUrbanos = new GaiaFormServUrbanos();
                formServUrbanos.setLayer(servurbanos);
                
                Layer turismo = new Layer("Turismo", gsetTurismo, "nombre", "", "nombre");
                turismo.setColor(new Color(255, 0, 0));
                turismo.setMouseOverColor(Color.CYAN);
                turismo.setPointDiameter(2);
                turismo.setEditable(true);
                GaiaFormTurismo formTurismo = new GaiaFormTurismo();
                formTurismo.setLayer(turismo);

		Layer consultas_01 = new Layer("Consultas 01", gsetParcelas_vinculadas, "gid");
		consultas_01.setColor(Color.MAGENTA);
		consultas_01.setMouseOverColor(Color.MAGENTA);

		Layer consultas_02 = new Layer("Consultas 02", gsetParcelas_vinculadas, "gid");
		consultas_02.setColor(Color.MAGENTA);
		consultas_02.setMouseOverColor(Color.MAGENTA);
    
		Layer consultas_03 = new Layer("Consultas 03", gsetParcelas_vinculadas, "gid");
		consultas_03.setColor(Color.MAGENTA);
		consultas_03.setMouseOverColor(Color.MAGENTA);
    
		Layer consultas_04 = new Layer("Consultas 04", gsetParcelas_vinculadas, "gid");
		consultas_04.setColor(Color.MAGENTA);
		consultas_04.setMouseOverColor(Color.MAGENTA);
    
		Layer consultas_05 = new Layer("Consultas 05", gsetParcelas_vinculadas, "gid");
		consultas_05.setColor(Color.MAGENTA);
		consultas_05.setMouseOverColor(Color.MAGENTA);
                
		cordon_vereda.setOn();
		calles.setOn();

		queryGroup.add(consultas_01);
		queryGroup.add(consultas_02);
		queryGroup.add(consultas_03);
		queryGroup.add(consultas_04);
		queryGroup.add(consultas_05);

		cordonGroup.add(areas_deportivas);
		cordonGroup.add(espacios_verdes);
		cordonGroup.add(cordon_vereda);
		cordonGroup.add(ruta50_alambrados);
		cordonGroup.add(ruta50_ejeytraza);

		barriosGroup.add(barrios_limites);
		barriosGroup.add(barrios_nombres);

		canalesGroup.add(canales_existentes);
		canalesGroup.add(canales_existentes_nombres);
		canalesGroup.add(canales_proyectados);
		canalesGroup.add(canales_proyectados_nombres);

		curvasGroup.add(curvas_nivel_1m);
		curvasGroup.add(curvas_nivel_1m_textos);
		curvasGroup.add(curvas_nivel_20m);
	    
		fraccionesGroup.add(fracciones_rurales);
		fraccionesGroup.add(fracciones_rurales_catastro);
		fraccionesGroup.add(fracciones_rurales_nombre);
		
		tipoSueloGroup.add(tipo_suelo_a);
		tipoSueloGroup.add(tipo_suelo_b);
		tipoSueloGroup.add(tipo_suelo_c);
		tipoSueloGroup.add(tipo_suelo_d);
		tipoSueloGroup.add(tipo_suelo_arcilloso);
		//tipoSueloGroup.add(tipo_suelo_arcilloso_hatch);
		tipoSueloGroup.add(tipo_suelo_arenoso);
		//tipoSueloGroup.add(tipo_suelo_arenoso_hatch);

		formsGroup.add(adminyserv);
		formsGroup.add(comercios);
		formsGroup.add(cultoycultura);
		formsGroup.add(educacion);
		formsGroup.add(industria);
		formsGroup.add(produccion);
		formsGroup.add(servurbanos);
		formsGroup.add(turismo);
		formsGroup.add(seguridad);
		formsGroup.add(casosEnfermedades);
		formsGroup.add(salud);

		mainGroup.add(cloacas_puntos);
		mainGroup.add(cota_cloacas_puntos);
		mainGroup.add(cota_cloacas_textos);
		mainGroup.add(calles);
		mainGroup.add(pavimento);
		mainGroup.add(reddeaguapotable);
		mainGroup.add(manzanas);
		mainGroup.add(parcelas);
		mainGroup.add(parcelas_vinculadas);
		mainGroup.add(manzanas_numeros);
		mainGroup.add(parcelas_numeros);
		mainGroup.add(catastro_numeros);
		mainGroup.add(reddeaguapotable_textos);
		mainGroup.add(caminos_vecinales_tierra);
		mainGroup.add(desmontes);

		Layer aeropuerto = new Layer("Aeropuerto", gsetAeropuerto);
		aeropuerto.setColor(Color.BLACK);
		aeropuerto.setMouseOverColor(Color.CYAN);
		aeropuerto.setQueryable(true);
		
		Layer area_de_estudio = new Layer("Área de estudio", gsetArea_de_estudio);
		area_de_estudio.setColor(Color.BLACK);
		area_de_estudio.setMouseOverColor(Color.CYAN);
		area_de_estudio.setQueryable(true);
		
		Layer barrios_ipv = new Layer("Barrios (IPV)", gsetBarrios_ipv);
		barrios_ipv.setColor(Color.BLACK);
		barrios_ipv.setMouseOverColor(Color.CYAN);
		barrios_ipv.setQueryable(true);
		
		Layer basural_municipal = new Layer("Basural Municipal", gsetBasural_municipal);
		basural_municipal.setColor(Color.BLACK);
		basural_municipal.setMouseOverColor(Color.CYAN);
		basural_municipal.setQueryable(true);
		
		Layer caminos_vecinales = new Layer("Caminos vecinales", gsetCaminos_vecinales);
		caminos_vecinales.setColor(Color.BLACK);
		caminos_vecinales.setMouseOverColor(Color.CYAN);
		caminos_vecinales.setQueryable(true);
		
		Layer camping_el_cedral = new Layer("Camping El Cedral", gsetCamping_el_cedral);
		camping_el_cedral.setColor(Color.BLACK);
		camping_el_cedral.setMouseOverColor(Color.CYAN);
		camping_el_cedral.setQueryable(true);
		
		Layer casa_divina_providencia = new Layer("Casa Divina Providencia", gsetCasa_divina_providencia);
		casa_divina_providencia.setColor(Color.BLACK);
		casa_divina_providencia.setMouseOverColor(Color.CYAN);
		casa_divina_providencia.setQueryable(true);
		
		Layer construccion_ipv = new Layer("Construcción IPV", gsetConstruccion_ipv);
		construccion_ipv.setColor(Color.BLACK);
		construccion_ipv.setMouseOverColor(Color.CYAN);
		construccion_ipv.setQueryable(true);
		
		Layer construcciones_area_suburbana = new Layer("Construcciones área suburbana", gsetConstrucciones_area_suburbana);
		construcciones_area_suburbana.setColor(Color.BLACK);
		construcciones_area_suburbana.setMouseOverColor(Color.CYAN);
		construcciones_area_suburbana.setQueryable(true);
		
		Layer cortadora_de_ladrillos = new Layer("Cortadora de ladrillos", gsetCortadora_de_ladrillos);
		cortadora_de_ladrillos.setColor(Color.BLACK);
		cortadora_de_ladrillos.setMouseOverColor(Color.CYAN);
		cortadora_de_ladrillos.setQueryable(true);
		
		Layer ffcc = new Layer("FFCC", gsetFfcc);
		ffcc.setColor(Color.BLACK);
		ffcc.setMouseOverColor(Color.CYAN);
		ffcc.setQueryable(true);
		
		Layer futura_urbanizacion_ipv = new Layer("Futura urbanización IPV", gsetFutura_urbanizacion_ipv);
		futura_urbanizacion_ipv.setColor(Color.BLACK);
		futura_urbanizacion_ipv.setMouseOverColor(Color.CYAN);
		futura_urbanizacion_ipv.setQueryable(true);
		
		Layer laguna_ptlc = new Layer("Laguna P.T.L.C.", gsetLaguna_ptlc);
		laguna_ptlc.setColor(Color.BLACK);
		laguna_ptlc.setMouseOverColor(Color.CYAN);
		laguna_ptlc.setQueryable(true);
		
		Layer matadero_municipal = new Layer("Matadero Municipal", gsetMatadero_municipal);
		matadero_municipal.setColor(Color.BLACK);
		matadero_municipal.setMouseOverColor(Color.CYAN);
		matadero_municipal.setQueryable(true);
		
		Layer nombre_fincas = new Layer("Nombre Fincas", gsetNombre_fincas, "mapkey");
		nombre_fincas.setColor(Color.BLACK);
		nombre_fincas.setMouseOverColor(Color.CYAN);
		nombre_fincas.setQueryable(true);
		
		Layer parcelas_con_cultivo = new Layer("Parcelas con cultivo", gsetParcelas_con_cultivo, "gid");
		parcelas_con_cultivo.setColor(Color.BLACK);
		parcelas_con_cultivo.setMouseOverColor(Color.CYAN);
		parcelas_con_cultivo.setQueryable(true);
		
		Layer textos_nuevos = new Layer("Textos nuevos", gsetTextos_nuevos, "mapkey");
		textos_nuevos.setColor(Color.BLACK);
		textos_nuevos.setMouseOverColor(Color.CYAN);
		textos_nuevos.setQueryable(true);

		capas_2009_06_30.add(aeropuerto);
		capas_2009_06_30.add(area_de_estudio);
		capas_2009_06_30.add(barrios_ipv);
		capas_2009_06_30.add(basural_municipal);
		capas_2009_06_30.add(caminos_vecinales);
		capas_2009_06_30.add(camping_el_cedral);
		capas_2009_06_30.add(casa_divina_providencia);
		capas_2009_06_30.add(construccion_ipv);
		capas_2009_06_30.add(construcciones_area_suburbana);
		capas_2009_06_30.add(cortadora_de_ladrillos);
		capas_2009_06_30.add(ffcc);
		capas_2009_06_30.add(futura_urbanizacion_ipv);
		capas_2009_06_30.add(laguna_ptlc);
		capas_2009_06_30.add(matadero_municipal);
		capas_2009_06_30.add(nombre_fincas);
		capas_2009_06_30.add(parcelas_con_cultivo);
		capas_2009_06_30.add(textos_nuevos);
		
		cityMap.addLayerGroup(formsGroup);
		cityMap.addLayerGroup(queryGroup);
		cityMap.addLayerGroup(mainGroup);
		cityMap.addLayerGroup(cordonGroup);
		cityMap.addLayerGroup(barriosGroup);
		cityMap.addLayerGroup(canalesGroup);
		cityMap.addLayerGroup(curvasGroup);
		cityMap.addLayerGroup(fraccionesGroup);
		cityMap.addLayerGroup(tipoSueloGroup);
		cityMap.addLayerGroup(capas_2009_06_30);

		RuleViewer ruleViewer = new RuleViewer();
		ruleViewer.setClosable(false);
		cityMap.setRuleViewer(ruleViewer);
		ruleViewer.setTitle("");
		_desktop.add(ruleViewer);

		LayerListPanel layerListPanel = (LayerListPanel)GaiaEnvironment.layerListPanel;
		layerListPanel.setDrawPanel(cityMap);

		SYSTEMMAP_MODULE_STATUS = STATUS_RUNNING;
		_desktop.add(coordinateViewer);
		_desktop.add(cityMap);
		
		((MapBasicToolsPanel)GaiaEnvironment.mapBasicToolsPanel).setDrawPanel(cityMap);

		coordinateViewer.setVisible(true);

		/**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		/**
		  * Boton TEST
		  * */
		final DesktopButton btnTest = new DesktopButton("Cargar versión de\nPrueba del GIS");
		btnTest.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

				}

			    }
		);
		//_desktop.addButton(btnTest);
		/**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		Environment.jpStatusBar.setAction("System Map loaded");
		Debug.println("SYSTEM MAP boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		break;
	    case INIT_HALT:
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void initUIManagerKeys() {
	/**
	 * LOOK AND FEEL
	 * */
	ToolTipManager.sharedInstance().setDismissDelay(5000);
	ToolTipManager.sharedInstance().setInitialDelay(0);
	try {
	    //Elijo el LookAndFeel segun el OS
	    /*if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
	    } else {
	        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	    }*/
	    //Con este LookAndFeel en Windows no se ve como deberia ser
	    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    //Le meto siempre el MetalLookAndFeel y consigo que se vea igual en Windows y Linux :)
	    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    e.printStackTrace();
	}
	/**
	 * TABBEDPANE
	 * */
	//Color del tab seleccionado
	UIManager.put("TabbedPane.selected", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Color del tab no seleccionado
	UIManager.put("TabbedPane.unselectedBackground", BasicConfig.TABBEDPANE_UNSELECTED_BACKGROUND);
	//Color del texto en todos los tabs, el BasicTabbedPane tiene un método
	//que le pone otro color al texto del tab seleccionado
	UIManager.put("TabbedPane.foreground", BasicConfig.TABBEDPANE_FOREGROUND);
	//Color del borde del tabbedpane, el que se dibuja entre el tab y el container, quizá
	//sea preferible poner "TabbedPane.contentOpaque", false
	UIManager.put("TabbedPane.contentOpaque", false);
	//UIManager.put("TabbedPane.contentAreaColor", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Este no da bola
	UIManager.put("TabbedPane.background", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Los tabs sobreescribirán el borde?
	//UIManager.put("TabbedPane.tabsOverlapBorder", false);
	//No se para qué sirve, pero le saco todos los espacios
	UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
	//Hago que el TAB se funda con el contenido
	UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, -2, 0, 0));
	//Este no da bola
	//UIManager.put("TabbedPane.selectedTabPadInsets", new Insets(20, 20, 20, 20));
	//Color de la luz del borde del tab seleccionado y su contenido
	//UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(229,124,10));
	//No se para que sirve
	//UIManager.put("TabbedPane.tabAreaBackground", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Luz de abajo hacia arriba, solo se ve en las flechas
	//UIManager.put("TabbedPane.highlight", new ColorUIResource(0,0,0));
	//Luz de arriba hacia abajo
	//UIManager.put("TabbedPane.light", new ColorUIResource(0,0,0));
	//Sombra inferior derecha de las flechas y de sus botones
	//UIManager.put("TabbedPane.shadow", new ColorUIResource(229,124,10));
	//Color de las flechas, de los bordes de sus botonoes y de los bordes de los tabs no seleccionados
	//UIManager.put("TabbedPane.darkShadow", new ColorUIResource(229,124,10));
	//Color del foco del tab
	//UIManager.put("TabbedPane.focus", new ColorUIResource(229,124,10));
	//Color del borde superior izquierdo del tab seleccionado
	//UIManager.put("TabbedPane.selectHighlight", new ColorUIResource(229,124,10));
	/**
	 * TOOLBAR
	 * */
	//Color del fondo de la barra de herramientas y del panelcito para hacerla flotante
	UIManager.put("ToolBar.background", BasicConfig.RESALTADOR_COLOR_UIRESOURCE);
	//Color del fondo de la barra de herramientas cuando esta lista para acoplamiento
	//UIManager.put("ToolBar.dockingBackground", new ColorUIResource(229,124,10));
	//Color del fondo de la barra de herramientas cuando esta siendo arrastrada
	//UIManager.put("ToolBar.floatingBackground", new ColorUIResource(229,124,10));
	/**
	  * MENUBAR
	  * */
	//Este no da bola
	UIManager.put("Menu.background", BasicConfig.RESALTADOR_COLOR_UIRESOURCE);
	//Color de fondo del menuitem (el que está enganchado con un JMenu)
	UIManager.put("MenuItem.background", BasicConfig.RESALTADOR_COLOR_UIRESOURCE);
	//Color de fondo del menuitem cuando está seleccionado (el que está enganchado con un JMenu)
	UIManager.put("MenuItem.selectionBackground", BasicConfig.RESALTADOR_COLOR_UIRESOURCE);
	//Este no da bola
	UIManager.put("Menu.margin", new InsetsUIResource(0, 0, 0, 0));
	//Este no da bola
	UIManager.put("MenuItem.margin", new InsetsUIResource(0, 0, 0, 0));
	/**
	  * OPTIONPANE
	  * */
	/*//Fondo del JOptionPane
	UIManager.put("OptionPane.background", new ColorUIResource(new Color(229, 124, 10)));
	//Fondo del Panel por defecto
	UIManager.put("Panel.background", new ColorUIResource(new Color(229, 124, 10)));
	ArrayList<Object> gradients = new ArrayList<Object>(5);
	gradients.add(0.28f);
	gradients.add(0.00f);
	gradients.add(new Color(0x33FF00));
	gradients.add(new Color(0x33CC00));
	gradients.add(new Color(0x339900));
	UIManager.put("Button.gradient", gradients);*/
	//JOptionPane.showOptionDialog(null, "PRUEBA!", "PRUEBA!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
	/**
	 * ComboBox
	 * */
	UIManager.put("ComboBox.disabledForeground", BasicConfig.LABEL_FOREGROUND_COLOR);
	//BasicConfig.LABEL_FOREGROUND_COLOR ) ;
	UIManager.put("ComboBox.disabledBackground", BasicConfig.PANEL_BACKGROUND_COLOR);
	//BasicConfig.LABEL_FOREGROUND_COLOR ) ;
	/**
	  * ToolTips
	  * */
	UIManager.put("ToolTip.background", new ColorUIResource(51, 51, 51));
	UIManager.put("ToolTip.foreground", new ColorUIResource(255, 132, 0));
	UIManager.put("ToolTip.font", new FontUIResource("Dialog", FontUIResource.BOLD, 12));
	/**
	 * SCROLL BAR
	 * */
	ArrayList<Object> gradients = new ArrayList<Object>(5);
	gradients.add(0.28f);
	gradients.add(0.00f);
	gradients.add(BasicConfig.PANEL_GRADIENT_START_COLOR);
	gradients.add(BasicConfig.PANEL_BACKGROUND_COLOR);
	gradients.add(BasicConfig.TABBEDPANE_UNSELECTED_BACKGROUND);
	UIManager.put("ScrollBar.gradient", gradients);
    }

    public static void closeApplication() {
	int answer = JOptionPane.showConfirmDialog(Environment.mainDesktop, "¿Desea cerrar el sistema?", "Cerrar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (answer == JOptionPane.YES_OPTION) {
	    //AVISAR A LOS MODULOS QUE SE VA A CERRAR TODO!!!
	    /**
	     * GRABAR OPCIONES
	     * */
	    Environment.cfg.setProperty("SelectedTab", String.valueOf(Environment.mainTabbedPane.getSelectedIndex()));
	    System.exit(0);
	}
    }

    public static void initGraphics() {
	Environment.organization = "DIGITALL S.H.";
	BasicConfig.PANELCONTAINER_BACKGROUND_COLOR = new Color(0, 141, 205);
	BasicConfig.PANEL_GRADIENT_START_COLOR = new Color(225, 225, 225);
	BasicConfig.PANEL_GRADIENT_END_COLOR = new Color(184, 184, 184);
	BasicConfig.LABEL_BACKGROUND_COLOR = new Color(115, 115, 115);
	BasicConfig.PANEL_BACKGROUND_COLOR = new Color(0, 141, 205);
	BasicConfig.INTERNALFRAME_NORTH_PANE_GRADIENT_START_COLOR = new Color(225, 225, 225);
	BasicConfig.INTERNALFRAME_NORTH_PANE_GRADIENT_END_COLOR = new Color(184, 184, 184);
	BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_END_COLOR = new Color(184, 184, 184);
	BasicConfig.GENERALBUTTONS_CONTAINER_GRADIENT_START_COLOR = new Color(225, 225, 225);
	BasicConfig.GENERALBUTTONS_BORDER_COLOR = new Color(96, 96, 96);
	BasicConfig.TABBEDPANE_UNSELECTED_BACKGROUND = new ColorUIResource(66, 66, 66);
	BasicConfig.TABBEDPANE_FOREGROUND = new ColorUIResource(255, 255, 255);
	BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND = new ColorUIResource(115, 115, 115);
	BasicConfig.TITLELABEL_FOREGROUND_COLOR = Color.BLACK;
	initUIManagerKeys();
    }

}
