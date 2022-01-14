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
 * Boot.java
 * Modified by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.southwest.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;

import org.digitall.apps.calendar.interfaces.CoordinatorStickyNote;
import org.digitall.apps.calendar.interfaces.DCalendar;
import org.digitall.apps.calendar.interfaces.NewsList;
import org.digitall.apps.calendar.interfaces.StickyNote;
import org.digitall.apps.chwy.ChWYChatPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaParcelPanel;
import org.digitall.apps.jpgadmin.sqleditor.FunctionsList;
import org.digitall.common.components.basic.BasicCard;
import org.digitall.common.data.PanelDictionary;
import org.digitall.common.geo.mapping.components.LayerTree;
import org.digitall.common.geo.mapping.components.MapBasicTools;
import org.digitall.common.geo.mapping.components.MapBasicToolsPanel;
import org.digitall.common.mapper.CoordinateCalculator;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.common.systemmanager.interfaces.SystemInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.DesktopButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Debug;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.sql.SQLImport;
import org.digitall.projects.apps.dbadmin_091.interfases.DBAdminPanel;
import org.scassi.projects.southwest.avl.DiagramaBuses;
import org.scassi.projects.southwest.avl.ServiciosMain;

import org.scassi.projects.southwest.avl.BusInfoPanel;
import org.scassi.projects.southwest.reportes.ReporteCoches;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;

import org.scassi.projects.southwest.reportes.ReporteChoferes;

import tim.prune.App;
import tim.prune.I18nManager;
import tim.prune.UpdateMessageBroker;
import tim.prune.gui.Viewport;
import tim.prune.gui.map.MapCanvas;

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
    public static int REPORTS_MODULE = 1;
    public static int AVL_MODULE = 2;
    public static int GAIA_MODULE = 3;
    public static int STICKYNOTES_MODULE = 4;

    /**
     * Estado de los modulos
     * */
    public static int MAIN_MODULE_STATUS = -1;
    public static int AVL_MODULE_STATUS = -1;
    public static int GAIA_MODULE_STATUS = -1;
    public static int REPORTS_MODULE_STATUS = -1;
    public static int STICKYNOTES_MODULE_STATUS = -1;

    public static void rcAVL(int _action, final BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		Debug.println("Booting AVL");
		Environment.jpStatusBar.setAction("Loading AVL");
		Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		/**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		/**
		  * Boton TEST
		  * */
		loadAVL(_desktop);
		/*
		final DesktopButton btnTest = new DesktopButton("Cargar Seguimiento en Tiempo Real", IconTypes.gaia);
		btnTest.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
				if (AVL_MODULE_STATUS != STATUS_RUNNING) {
				    loadAVL(_desktop);
				    btnTest.setEnabled(false);
				    btnTest.setVisible(false);
				    AVL_MODULE_STATUS = STATUS_RUNNING;
				}
			    }

			});
		_desktop.addButton(btnTest);
		*/
		/**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		Environment.jpStatusBar.setAction("AVL loaded");
		Debug.println("AVL boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		break;
	    case INIT_HALT:
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcGaia(int _action, final BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		Debug.println("Booting GAIA");
		Environment.jpStatusBar.setAction("Loading Gaia");
		Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		/**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		/**
		  * Boton TEST
		  * */
		loadGaia(_desktop);
		/*final DesktopButton btnTest = new DesktopButton("Cargar versión de\nPrueba del GIS", IconTypes.gaia);
		btnTest.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
				if (GAIA_MODULE_STATUS != STATUS_RUNNING) {
				    loadGaia(_desktop);
				    btnTest.setEnabled(false);
				    btnTest.setVisible(false);
				    GAIA_MODULE_STATUS = STATUS_RUNNING;
				}
			    }

			});
		_desktop.addButton(btnTest);*/
		/**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		Environment.jpStatusBar.setAction("Gaia loaded");
		Debug.println("GAIA boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		break;
	    case INIT_HALT:
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rc3rdpartyApps(int _action, BasicDesktop _desktop) {
	/** REPORTING PENTAHO 3.6.1 */
	 Thread _thread = new Thread(new Runnable() {
		public void run() {
		    ClassicEngineBoot.getInstance().start();
		}
	 });
	 _thread.start();
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
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    try {
			//Environment.mp3Player = new StandalonePlayer();
			//Environment.mp3Player.setDesktop(_desktop);
		    } catch (NoClassDefFoundError x) {
			x.printStackTrace();
		    }
		    /**
		     * CHAT
		     * */
		    ExtendedInternalFrame chwy = new ExtendedInternalFrame("Chat", IconTypes.chat_32x32);
		    ChWYChatPanel chwyPanel = new ChWYChatPanel(Environment.sessionUser, Environment.jpStatusBar);
		    chwy.setClosable(false);
		    chwy.setCentralPanel(chwyPanel);
		    chwy.setDesktop(_desktop);
		    chwy.setIcon(true);
		    /**
		      * CALENDAR
		      * */
		    Environment.jpStatusBar.setAction("Loading Calendar");
		    DCalendar calendarIFrame = new DCalendar();
		    calendarIFrame.setVisible(false);
		    calendarIFrame.start();
		    _desktop.setBottomRightComponent(calendarIFrame);
		    //player.setIcon(true);
		    /*JCTermSwingFrame x = new JCTermSwingFrame("SSH TERM");
		     x.setSize(800,600);
		     x.setDesktop(_desktop);
		     x.setClosable(false);
		     x.setIconifiable(true);
		     x.setIcon(true);*/
		    /*Boot.rcCashFlowClientModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_CASHFLOW));
		     Boot.rcNewsModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_NEWS));
		     Boot.rcChatModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_CHAT));*/
		    /**BOTONES*/

		    /**
		     * Boton del WebMail
		     * */
		    /*DesktopButton btnWebMail = new DesktopButton("WebMail");
		    btnWebMail.setRolloverEnabled(true);
		    btnWebMail.setIcon(IconTypes.webmail_32x32);
		    btnWebMail.setRolloverIcon(IconTypes.webmail_ro_32x32);
		    btnWebMail.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   BrowserLauncher.openURL("http://www.digitallsh.com.ar/");
				       }

				   }
		    );
		    _desktop.addButton(btnWebMail);*/
		    /**
		     * INFORMACION DEL SISTEMA
		     * */
		    ExtendedInternalFrame systemInfo = new ExtendedInternalFrame("Info del Sistema", IconTypes.info_del_sistema);
		    SystemInfo jpSystemInfo = new SystemInfo();
		    systemInfo.setCentralPanel(jpSystemInfo);
		    systemInfo.setResizable(true);
		    systemInfo.setClosable(false);
		    systemInfo.setDesktop(_desktop);
		    systemInfo.setIcon(true);

		    boolean superUser = false;
		    try {
			ResultSet _result = LibSQL.exQuery("SELECT rolsuper FROM pg_roles WHERE rolname = session_user");
			if (_result.next()) {
			    superUser = _result.getBoolean("rolsuper");
			}
		    } catch (SQLException e) {
		    }
		    if (superUser) {
		        /**
		          * DICCIONARIO DEL SISTEMA
		          * */
		        new ExtendedInternalFrame("Dictionary", IconTypes.diccionario, PanelDictionary.class, true, _desktop);
		        /**
		         * DBAdmin
		         * */
		        new ExtendedInternalFrame("DBAdmin", IconTypes.info_del_sistema, DBAdminPanel.class, true, _desktop);
		        /**
		         * JPGAdmin
		         * */
		        new ExtendedInternalFrame("JPGAdmin", IconTypes.info_del_sistema, FunctionsList.class, true, _desktop);
		        /**
		         * SQL IMPORTER
		         * */
		        new ExtendedInternalFrame("SQLImport", IconTypes.info_del_sistema, SQLImport.class, true, _desktop);
		    }

		    /**
		     * TaskMan
		     * */
		    //Se clava?
		    //new ExtendedInternalFrame("TaskMan", IconTypes.info_del_sistema, TaskManList.class, true, _desktop);
		    
		    /**
		     * Instancio la fabulosa calculadora de coordenadas
		     * */
		    ExtendedInternalFrame geoCalc = new ExtendedInternalFrame("Calculadora geografica", IconTypes.geocalc_32x32);
		    CoordinateCalculator geoCalcPanel = new CoordinateCalculator();
		    geoCalc.setClosable(false);
		    geoCalc.setCentralPanel(geoCalcPanel);
		    geoCalc.setDesktop(_desktop);
		    geoCalc.setIcon(true);
		    /**
		     * NOVEDADES
		     * */
		    new ExtendedInternalFrame("Novedades", IconTypes.inbox_32x32, NewsList.class, true, _desktop);

		    //new ExtendedInternalFrame("Sistema Expedientes", IconTypes.sistema_expedientes, principal_simex.class, true, _desktop);

		    //new ExtendedInternalFrame("Acción Social", IconTypes.administrar_catastros, AccionSocialPrincipal.class, true, _desktop);
		    
		    //new ExtendedInternalFrame("Administrar Entregas", IconTypes.administrar_catastros, EntregasList.class, true, _desktop);

		    DesktopButton _btnReadme = new DesktopButton("LÉAME");
		    _btnReadme.setRolloverEnabled(true);
		    _btnReadme.setIcon(IconTypes.webmail_32x32);
		    _btnReadme.setRolloverIcon(IconTypes.webmail_ro_32x32);
		    _btnReadme.addActionListener(new ActionListener() {

		                       public void actionPerformed(ActionEvent e) {

		                           BrowserLauncher.openURL(Environment.mainClass.getResource("README").toString());

		                           final BasicDialog _readmeDialog = new BasicDialog();
					   _readmeDialog.setTitle("Archivo README");

		                           JArea _jaReadme = new JArea();
		                           _jaReadme.setContentType("text/html");
		                           _jaReadme.setPage(Environment.mainClass.getResource("README"));
		                           _jaReadme.setEditable(false);
		                           _readmeDialog.setLayout(new BorderLayout());
		                           _readmeDialog.add(_jaReadme, BorderLayout.CENTER);
		                           _readmeDialog.setModal(true);

		                           JButton _ok = new JButton("CERRAR VENTANA");

		                           _ok.addActionListener(new ActionListener() {

		                                   public void actionPerformed(ActionEvent actionEvent) {
		                                       _readmeDialog.closeBasicDialog();                      
		                                   }
		                               });

		                           _readmeDialog.add(_ok, BorderLayout.SOUTH);

		                           _readmeDialog.setSize(new Dimension(550,300));
		                           ComponentsManager.centerWindow(_readmeDialog);
		                           _readmeDialog.setVisible(true);
		                       }

		                   }
		    );
		    //_desktop.addButton(_btnReadme);

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

    public static void rcReportsModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (REPORTS_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting REPORTS");
		    Environment.jpStatusBar.setAction("Loading Reports");
		    REPORTS_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    try {
			ResultSet cards = LibSQL.exQuery("SELECT idcard FROM org.cards ORDER BY idcard");
			while (cards.next()) {
			    BasicCard _card = new BasicCard(cards.getInt("idcard"));
			    _card.setClosable(false);
			    _card.setDesktop(_desktop);
			    _card.show();
			    ComponentsManager.setConfigurable(_card);
			}
		    } catch (Exception e) {
			Advisor.messageBox("Error fetching cards", "");
		    }

		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    REPORTS_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Reports loaded");
		    Debug.println("REPORTS boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("REPORTS already running!");
		}
		break;
	    case INIT_HALT:
		if (REPORTS_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("REPORTS halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
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
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    DesktopButton btnNewStickyNote = new DesktopButton("Nueva nota");
		    btnNewStickyNote.setRolloverEnabled(true);
		    btnNewStickyNote.setIcon(IconTypes.stickynotes_32x32);
		    btnNewStickyNote.setRolloverIcon(IconTypes.stickynotes_ro_32x32);
		    btnNewStickyNote.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    StickyNote newStickyNote = new StickyNote();
				    newStickyNote.setDate(Environment.currentDate + " " + Environment.currentTime);
				    newStickyNote.setIcon(true);
				    newStickyNote.show();
				}

			    });
		    _desktop.addButton(btnNewStickyNote);
		    CoordinatorStickyNote coordinatorStickyNote = new CoordinatorStickyNote();
		    coordinatorStickyNote.retrieveData();
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
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
	//Color del texto en todos los tabs, el BasicTabbedPane tiene un metodo
	//que le pone otro color al texto del tab seleccionado
	UIManager.put("TabbedPane.foreground", BasicConfig.TABBEDPANE_FOREGROUND);
	//Color del borde del tabbedpane, el que se dibuja entre el tab y el container, quizá
	//sea preferible poner "TabbedPane.contentOpaque", false
	UIManager.put("TabbedPane.contentOpaque", false);
	//UIManager.put("TabbedPane.contentAreaColor", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Este no da bola
	UIManager.put("TabbedPane.background", BasicConfig.TABBEDPANE_TAB_AREA_BACKGROUND);
	//Los tabs sobreescribiron el borde?
	//UIManager.put("TabbedPane.tabsOverlapBorder", false);
	//No se para que sirve, pero le saco todos los espacios
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
	//Color de fondo del menuitem (el que esta enganchado con un JMenu)
	UIManager.put("MenuItem.background", BasicConfig.RESALTADOR_COLOR_UIRESOURCE);
	//Color de fondo del menuitem cuando esta seleccionado (el que esta enganchado con un JMenu)
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
	Advisor.closeApplication();
    }

    public static void initGraphics() {
	Environment.organization = "SouthWest Electronics.";
	BasicConfig.PANELCONTAINER_BACKGROUND_COLOR = new Color(184, 184, 184);
	BasicConfig.PANEL_GRADIENT_START_COLOR = new Color(225, 225, 225);
	BasicConfig.PANEL_GRADIENT_END_COLOR = new Color(184, 184, 184);
	BasicConfig.LABEL_BACKGROUND_COLOR = new Color(115, 115, 115);
	BasicConfig.PANEL_BACKGROUND_COLOR = new Color(115, 115, 115);
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

    private static void loadGaia(final BasicDesktop _desktop) {
	Thread _thread = new Thread(new Runnable() {

	    public void run() {
		try {
		    I18nManager.init(null);

		    final App APP = new App(Environment.mainFrame);
		    MapCanvas mapDisp = new MapCanvas(APP, APP.getTrackInfo());
		    UpdateMessageBroker.addSubscriber(mapDisp);
		    Viewport viewport = new Viewport(mapDisp);
		    APP.setViewport(viewport);
		    UpdateMessageBroker.informSubscribers("GAIA");

		    UpdateMessageBroker.informSubscribers();
		    LayerGroup serviciosGroup = new LayerGroup("Internos");
		    mapDisp.getLayerGroups().add(serviciosGroup);
		    Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();

		    mapDisp.setGeometrySets(geometrySets);

		    mapDisp.setMapExtents(-76.652222, -11.910083, -77.315083, -12.715638);

		    JInternalFrame layerListFrame = new JInternalFrame("Listado de Layers");
		    layerListFrame.setClosable(false);
		    LayerTree layerTree = new LayerTree();
		    layerListFrame.getContentPane().add(layerTree, null);
		    layerListFrame.setBounds(0, 0, 350, 150);
		    layerListFrame.setResizable(true);
		    layerListFrame.setVisible(false);
		    layerTree.setLayerGroups(mapDisp.getLayerGroups());

		    //_desktop.setActive(true);

		    RuleViewer ruleViewer = new RuleViewer();
		    ruleViewer.setClosable(false);
		    ruleViewer.setTitle("");

		    _desktop.add(mapDisp);
		    //Necesito darle tamaño para el ZoomToFit!!!
		    mapDisp.setBounds(_desktop.getBounds());
		    //Ahora puedo hacer el initialize --> ZoomToFit
		    mapDisp.initialize();
		    _desktop.add(layerListFrame);
		    layerListFrame.show();

		    ExtendedInternalFrame serviciosMainFrame = new ExtendedInternalFrame("Servicios");
		    ServiciosMain serviciosMain = new ServiciosMain(mapDisp, layerTree);
		    serviciosMainFrame.setClosable(false);
		    serviciosMainFrame.setIconifiable(false);
		    serviciosMainFrame.setCentralPanel(serviciosMain);
		    serviciosMainFrame.setDesktop(Environment.getDesktop(Environment.MODULE_GAIA));
		    serviciosMainFrame.show();
		    ComponentsManager.setConfigurable(serviciosMainFrame);

		    ComponentsManager.setConfigurable(layerListFrame);
		    ComponentsManager.setConfigurable(ruleViewer);

		    ExtendedInternalFrame reportesCochesMainFrame = new ExtendedInternalFrame("Reportes por Coches", IconTypes.info_del_sistema);
		    ReporteCoches reportesCochesMain = new ReporteCoches(mapDisp, layerTree, serviciosMain.getLinkedTable());
		    reportesCochesMainFrame.setClosable(false);
		    reportesCochesMainFrame.setCentralPanel(reportesCochesMain);
		    reportesCochesMainFrame.setMaximizable(true);
		    reportesCochesMainFrame.setDesktop(Environment.getDesktop(Environment.MODULE_REPORTS));
		    reportesCochesMainFrame.setIcon(true);

		    ExtendedInternalFrame reportesChoferesMainFrame = new ExtendedInternalFrame("Reportes por Choferes", IconTypes.info_del_sistema);
		    ReporteChoferes reportesChoferesMain = new ReporteChoferes();
		    reportesChoferesMainFrame.setClosable(false);
		    reportesChoferesMainFrame.setCentralPanel(reportesChoferesMain);
		    reportesChoferesMainFrame.setMaximizable(true);
		    reportesChoferesMainFrame.setDesktop(Environment.getDesktop(Environment.MODULE_REPORTS));
		    reportesChoferesMainFrame.setIcon(true);

		    ExtendedInternalFrame diagramaCochesMainFrame = new ExtendedInternalFrame("Diagramación de Franjas Horarias", IconTypes.info_del_sistema);
		    DiagramaBuses diagramaCochesMain = new DiagramaBuses();
		    diagramaCochesMainFrame.setClosable(false);
		    diagramaCochesMainFrame.setCentralPanel(diagramaCochesMain);
		    diagramaCochesMainFrame.setMaximizable(true);
		    diagramaCochesMainFrame.setDesktop(Environment.getDesktop(Environment.MODULE_REPORTS));
		    diagramaCochesMainFrame.setIcon(true);

		} catch (Exception x) {
		    Advisor.printException(x);
		}
		_desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	});
	try {
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(true);
	    Environment.jpStatusBar.setAction("Cargando GIS");
	    _thread.start();
	} catch (Exception x) {
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(false);
	    Environment.jpStatusBar.setAction("Listo...");
	    x.printStackTrace();
	}
    }

    private static void loadAVL(final BasicDesktop _desktop) {
	Thread _thread = new Thread(new Runnable() {

	    public void run() {
		try {

		    I18nManager.init(null);

		    JFrame frame = Environment.mainFrame;
		    final App APP = new App(frame);

		    MapCanvas mapCanvas = new MapCanvas(APP, APP.getTrackInfo());
		    UpdateMessageBroker.addSubscriber(mapCanvas);
		    Viewport viewport = new Viewport(mapCanvas);
		    APP.setViewport(viewport);
		    UpdateMessageBroker.informSubscribers("AVL");
		    UpdateMessageBroker.informSubscribers();

		    LayerGroup serviciosGroup = new LayerGroup("Internos");
		    mapCanvas.getLayerGroups().add(serviciosGroup);
		    Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();

		    mapCanvas.setGeometrySets(geometrySets);

		    mapCanvas.setMapExtents(-76.652222, -11.910083, -77.315083, -12.715638);

		    JInternalFrame layerListFrame = new JInternalFrame("Listado de Layers");
		    layerListFrame.setClosable(false);
		    LayerTree layerTree = new LayerTree();
		    layerListFrame.getContentPane().add(layerTree, null);
		    layerListFrame.setBounds(0, 0, 350, 150);
		    layerListFrame.setResizable(true);
		    layerListFrame.setVisible(false);
		    layerTree.setLayerGroups(mapCanvas.getLayerGroups());

		    //_desktop.setActive(true);

		    RuleViewer ruleViewer = new RuleViewer();
		    ruleViewer.setClosable(false);
		    ruleViewer.setTitle("");

		    _desktop.add(mapCanvas);

		    //Necesito darle tamaño para el ZoomToFit!!!
		    mapCanvas.setBounds(_desktop.getBounds());
		    //Ahora puedo hacer el initialize --> ZoomToFit
		    mapCanvas.initialize();
		    _desktop.add(layerListFrame);
		    layerListFrame.show();

		    /****************** AVL ****************************/
		    
		    /*
		    Random rand = new Random();
		    int r = rand.nextInt(255);
		    int g = rand.nextInt(100);
		    int b = rand.nextInt(100);
		    Color randomColor = new Color(r, g, b);
		    
		    layer_test02.setColor(randomColor);
		    layer_test02.setMouseOverColor(Color.ORANGE);
		    */

		    Vector<LayerGroup> layerGroups = mapCanvas.getLayerGroups();
		    
		    geometrySets.removeAllElements();

		    layerGroups.get(0).removeAllElements();

		    ResultSet coches = LibSQL.exFunction("reportes.getallbusesparaavl", "");
		    while (coches.next()) {
		        Random rand = new Random();
		        int r = rand.nextInt(255);
		        int g = rand.nextInt(100);
		        int b = rand.nextInt(100);
		        Color randomColor = new Color(r, g, b);
			String coche = coches.getString("idcoche");
		        /*layer_test02.addFilter("Coche " + coche, "idcoche", "(idcoche = " + coche + ")", Color.black, randomColor, "Coche " + coche);*/

		        GeometrySet busGset = new GeometrySet("reportes","posicion_gps", "the_geom", "idcoche = " + coche, "idcoche");
		        busGset.getGeometrySetConfig().getGeometryTypeFromSQL(null);
		        busGset.setParent(mapCanvas);
		        busGset.reload();

		        geometrySets.add(busGset);

		        Layer busLayer = new Layer("Coche " + coche, busGset);
		        busLayer.setPointDiameter(12);
		        busLayer.getLayerConfig().setAutoUpdateRateInSeconds(25); //o lo que diga la tabla de coches
		        busLayer.setColor(Color.black);
		        busLayer.setFillColor(randomColor);
		        busLayer.setOff();

		        layerGroups.get(0).add(busLayer);
		        mapCanvas.addLayer(busLayer);

		        ExtendedInternalFrame busInfoPanelContainer = new ExtendedInternalFrame("Ventana de Información para el Coche " + coche);
		        BusInfoPanel busInfoPanel = new BusInfoPanel();
		        busInfoPanelContainer.setCentralPanel(busInfoPanel);
		        busLayer.setInfoPanel(busInfoPanelContainer);
		    }
		    
		    layerTree.setLayerGroups(layerGroups);
		    layerTree.rebuildJTree();
		    layerTree.expandAll();

		    /********************* FIN AVL **********************/

		    ComponentsManager.setConfigurable(layerListFrame);
		    ComponentsManager.setConfigurable(ruleViewer);

		} catch (Exception x) {
		    Advisor.printException(x);
		}
		_desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	});
	try {
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(true);
	    Environment.jpStatusBar.setAction("Cargando AVL");
	    _thread.start();
	} catch (Exception x) {
	    _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    Environment.jpStatusBar.setIndeterminate(false);
	    Environment.jpStatusBar.setAction("Listo...");
	    x.printStackTrace();
	}
    }

}
