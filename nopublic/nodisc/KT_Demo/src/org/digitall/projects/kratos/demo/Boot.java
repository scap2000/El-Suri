package org.digitall.projects.kratos.demo;

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

import javax.swing.JInternalFrame;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;

import org.digitall.apps.accionsocial.interfaces.AccionSocialPrincipal;
import org.digitall.apps.calendar.interfaces.CoordinatorStickyNote;
import org.digitall.apps.calendar.interfaces.DCalendar;
import org.digitall.apps.calendar.interfaces.NewsList;
import org.digitall.apps.calendar.interfaces.StickyNote;
import org.digitall.apps.cashflow.interfaces.accounting.AccountsAvailableAmountList;
import org.digitall.apps.cashflow.interfaces.accounting.JournalList;
import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryByModelMgmt;
import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryList;
import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryModelsList;
import org.digitall.apps.cashflow.interfaces.accountingentry.CreditNotesMgmt;
import org.digitall.apps.cashflow.interfaces.accountingentry.DebitNotesMgmt;
import org.digitall.apps.cashflow.interfaces.paymentorder.PaymentOrderList;
import org.digitall.apps.cashflow.interfaces.paymentorder.PaymentOrderManualMgmt;
import org.digitall.apps.cashflow.interfaces.paymentorder.PaymentOrderMgmt;
import org.digitall.apps.cashflow.interfaces.voucher.InvoiceTypeB;
import org.digitall.apps.cashflow.interfaces.voucher.MarkVoucherMgmt;
import org.digitall.apps.cashflow.interfaces.voucher.PagosDeTercerosMain;
import org.digitall.apps.cashflow.interfaces.voucher.VoucherToInvoiceMain;
import org.digitall.apps.cashflow.interfaces.voucher.VoucherToProvisionOrderMain;
import org.digitall.apps.chwy.ChWYChatPanel;
import org.digitall.apps.gaia.entities.infrastructure.GaiaInfrastructuresPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaCadastralFinderPanel;
import org.digitall.apps.gaia.entities.parcels.GaiaParcelPanel;
import org.digitall.apps.gaia.entities.streets.GaiaAddressReenumerationPanel;
import org.digitall.apps.gaia.entities.streets.GaiaStreetsPanel;
import org.digitall.apps.gaia.forms.GaiaFormCasosEnfermedad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormCensoComercial2009;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormPublicidad;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoComercios;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.InfoPublicidad;
import org.digitall.apps.personalfiles.PersonsList;
import org.digitall.apps.personalfiles.interfaces.DependenciaTree;
import org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin.DistinguishableResourcesList;
import org.digitall.apps.resourcesrequests.interfaces.resourcesmovements.DespatchNotesList;
import org.digitall.apps.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsMain;
import org.digitall.apps.sueldos.interfaces.AdministracionHoras;
import org.digitall.apps.sueldos.interfaces.ConfiguracionBaseMain;
import org.digitall.apps.sueldos.interfaces.CredentialList;
import org.digitall.apps.sueldos.interfaces.LegajosPanelMain;
import org.digitall.apps.sueldos.interfaces.LiquidacionSueldosMain;
import org.digitall.apps.taxes.interfases.cadastraladmin.CadastralList;
import org.digitall.apps.taxes.interfases.carsadmin.CarsList;
import org.digitall.apps.taxes.interfases.cashier.CashierMain;
import org.digitall.apps.taxes.interfases.clearfees.ClearCommerceTaxes;
import org.digitall.apps.taxes.interfases.clearfees.ClearRentTaxes;
import org.digitall.apps.taxes.interfases.commercesadmin.CommercesList;
import org.digitall.apps.taxes.interfases.feesadmin.TaxesMain;
import org.digitall.apps.taxes.interfases.rentsadmin.RentsList;
import org.digitall.apps.taxes092.interfases.TaxesMain092;
import org.digitall.common.cashflow.interfaces.account.BankAccountsList;
import org.digitall.common.cashflow.interfaces.accounting.AccountsMain;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementsList;
import org.digitall.common.cashflow.interfaces.costscentre.CCList;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.components.basic.BasicCard;
import org.digitall.common.data.PanelDictionary;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.common.geo.mapping.components.LayerTree;
import org.digitall.common.geo.mapping.components.MapBasicTools;
import org.digitall.common.geo.mapping.components.MapBasicToolsPanel;
import org.digitall.common.mapper.CoordinateCalculator;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.common.resourcesrequests.interfaces.provisionorder.ProvisionOrderMain;
import org.digitall.common.resourcesrequests.interfaces.purchaseorder.PurchaseOrderGenerateList;
import org.digitall.common.resourcesrequests.interfaces.purchaseorder.PurchaseOrdersList;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverList;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveMain;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsApproveMgmt;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMain;
import org.digitall.common.systemmanager.ChangePassword;
import org.digitall.common.systemmanager.interfaces.SystemInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.DesktopButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Debug;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.sql.SQLImport;
import org.digitall.projects.gdigitall.expedientes.principal_simex;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.report.JFreeReportBoot;

import org.postgresql.util.PSQLException;

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
    public static int ASSETS_MODULE = 1;
    public static int CASHFLOW_MODULE = 2;
    public static int GAIA_MODULE = 3;
    public static int REPORTS_MODULE = 4;
    public static int RESOURCES_MODULE = 5;
    public static int STICKYNOTES_MODULE = 6;
    public static int TAXES_MODULE = 7;

    /**
     * Estado de los modulos
     * */
    public static int MAIN_MODULE_STATUS = -1;
    public static int ASSETS_MODULE_STATUS = -1;
    public static int CASHFLOW_MODULE_STATUS = -1;
    public static int GAIA_MODULE_STATUS = -1;
    public static int REPORTS_MODULE_STATUS = -1;
    public static int RESOURCES_MODULE_STATUS = -1;
    public static int STICKYNOTES_MODULE_STATUS = -1;
    public static int TAXES_MODULE_STATUS = -1;

    public static void rcAssets(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (ASSETS_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting ASSETS");
		    Environment.jpStatusBar.setAction("Loading Assets");
		    ASSETS_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		     new ExtendedInternalFrame("Recursos\nhumanos", IconTypes.recursos_humanos, PersonsList.class, true, _desktop);
		     new ExtendedInternalFrame("Dependencias", IconTypes.dependencias, DependenciaTree.class, true, _desktop);
		     //new ExtendedInternalFrame("Compañías", null/*IconTypes.providers*/, CompanyTreePanel.class, true, _desktop);
		     new ExtendedInternalFrame("Habilidades", IconTypes.habilidades, SkillList.class, true, _desktop);
		     new ExtendedInternalFrame("Configuración Base\nde Sueldos", IconTypes.configuracion_base_de_sueldos, ConfiguracionBaseMain.class, true, _desktop);
		     new ExtendedInternalFrame("Liquidación de Sueldos", IconTypes.liquidacion_de_sueldos, LiquidacionSueldosMain.class, true, _desktop);
		     new ExtendedInternalFrame("Legajos", IconTypes.legajos, LegajosPanelMain.class, true, _desktop);
		     new ExtendedInternalFrame("Credenciales Municipales", IconTypes.info_del_sistema, CredentialList.class, true, _desktop);
		     new ExtendedInternalFrame("Administración Horas ", IconTypes.info_del_sistema, AdministracionHoras.class, true, _desktop);
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    ASSETS_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Assets loaded");
		    Debug.println("ASSETS boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("ASSETS already running!");
		}
		break;
	    case INIT_HALT:
		if (ASSETS_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("ASSETS halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
	    default:
	}
	LibSQL.setVerboseMode(true);
    }

    public static void rcCashFlowModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (CASHFLOW_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting CASHFLOW");
		    Environment.jpStatusBar.setAction("Loading CashFlow");
		    CASHFLOW_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    /*CashFlowToolBar tbCashFlowMgmt = new CashFlowToolBar(_desktop);
		    tbCashFlowMgmt.setFloatable(true);
		    tbCashFlowMgmt.setLocation(0, 300);
		    _desktop.add(tbCashFlowMgmt);*/
		    //1)
		    new ExtendedInternalFrame("Plan de Cuentas", IconTypes.plan_de_cuentas, AccountsMain.class, true, _desktop);
		    //2)
		    new ExtendedInternalFrame("Centros\nde Costos", IconTypes.centros_de_costos, CCList.class, true, _desktop);
		    //3)
		    new ExtendedInternalFrame("Administración de\nPartidas\nPresupuestarias", IconTypes.administracion_de_partidas_presupuestarias, BudgetList.class, true, _desktop);
		    //4)
		    new ExtendedInternalFrame("Cuentas\nbancarias", IconTypes.cuentas_bancarias, BankAccountsList.class, true, _desktop);
		    //5)
		    new ExtendedInternalFrame("Pagos de\nTerceros", IconTypes.pagos_de_terceros, PagosDeTercerosMain.class, true, _desktop);
		    //6)
		    new ExtendedInternalFrame("Nueva Nota\nde Ingreso", IconTypes.nota_de_ingreso, CreditNotesMgmt.class, true, _desktop);
		    //7)
		    new ExtendedInternalFrame("Nueva Nota\nde Egreso", IconTypes.nota_de_egreso, DebitNotesMgmt.class, true, _desktop);
		    //8)
		    new ExtendedInternalFrame("Nueva Orden\nde Pago", IconTypes.nueva_orden_de_pago, PaymentOrderMgmt.class, true, _desktop);
		    //8.1)
		    //new ExtendedInternalFrame("Nueva Orden\nde Pago Manual", IconTypes.nueva_orden_de_pago, PaymentOrderManualMgmt.class, true, _desktop);
		    new ExtendedInternalFrame("Nueva Orden\nde Pago Manual", IconTypes.nueva_orden_de_pago, PaymentOrderManualMgmt.class, true, _desktop);
		    //8.2)
		    new ExtendedInternalFrame("Marcar Facturas\nPagadas", IconTypes.nueva_orden_de_pago, MarkVoucherMgmt.class, true, _desktop);
		    //9)
		    new ExtendedInternalFrame("Listado de\nÓrdenes de Pago", IconTypes.orden_de_pago, PaymentOrderList.class, true, _desktop);
		    //10)
		    new ExtendedInternalFrame("Comprobantes", IconTypes.comprobantes, VouchersList.class, true, _desktop);
		    //11)
		    new ExtendedInternalFrame("Registrar\nComprobante a Pagar", IconTypes.registrar_comprobantes_a_pagar, InvoiceTypeB.class, true, _desktop);
		    //12)
		    new ExtendedInternalFrame("Facturar\nNotas de Recepción", IconTypes.facturar_notas_de_recepcion, VoucherToInvoiceMain.class, true, _desktop);
		    //13)
		    new ExtendedInternalFrame("Facturar\nOrdenes de Provisión", IconTypes.facturar_orden_de_provision, VoucherToProvisionOrderMain.class, true, _desktop);
		    //14)
		    new ExtendedInternalFrame("Asientos", IconTypes.asientos, BookKeepingEntryList.class, true, _desktop);
		    //15)
		    new ExtendedInternalFrame("Nuevo Asiento\nmediante Modelos", IconTypes.nuevo_asiento_mediante_modelo, BookKeepingEntryByModelMgmt.class, true, _desktop);
		    //16)
		    new ExtendedInternalFrame("Modelos de\nAsientos Contables", IconTypes.modelos_de_asientos, BookKeepingEntryModelsList.class, true, _desktop);
		    //17)
		    new ExtendedInternalFrame("Libro Mayor", IconTypes.libro_mayor, JournalList.class, true, _desktop);
		    //18)
		    new ExtendedInternalFrame("Balance de\nsumas y saldos", IconTypes.balance_de_sumas_y_saldos, AccountsAvailableAmountList.class, true, _desktop);
		    //19)
		    new ExtendedInternalFrame("Notas de\nIngreso y Egreso", IconTypes.nota_de_egreso, CashMovementsList.class, true, _desktop);
		    /**
		    * BOTON PARA EDICIÓN DE MODELOS DE ASIENTOS CONTABLES
		    */
		    /**
		      * BOTON PARA AGREGAR UN ASIENTO CONTABLE
		      * A TRAVÉS DE MODELOS
		      */
		    /**
		     * BOTON PARA AGREGAR UNA NOTA DE INGRESO
		     * MODULO SOLO VALIDO PARA UN ADMINISTRADOR
		     */
		    /*
		    ExtendedInternalFrame creditNotesAdminMgmtContainer = new ExtendedInternalFrame("Nueva Nota\nde Ingreso (Admin)", IconTypes.budgets);
		    CreditNotesAdmin creditNotesAdminMgmt = new CreditNotesAdmin();
		    creditNotesAdminMgmtContainer.setClosable(false);
		    creditNotesAdminMgmtContainer.setCentralPanel(creditNotesAdminMgmt);
		    creditNotesAdminMgmtContainer.setDesktop(_desktop);
		    creditNotesAdminMgmtContainer.setIcon(true);
		    */
		    /**
		    * BOTON PARA AGREGAR UNA NOTA DE EGRESO
		    * MODULO SOLO VALIDO PARA UN ADMINISTRADOR
		    */
		    /*
		    ExtendedInternalFrame DebitNotesAdminContainer = new ExtendedInternalFrame("Nueva Nota\nde Egreso (Admin)", IconTypes.budgets);
		    DebitNotesAdmin DebitNotesAdmin = new DebitNotesAdmin();
		    DebitNotesAdminContainer.setClosable(false);
		    DebitNotesAdminContainer.setCentralPanel(DebitNotesAdmin);
		    DebitNotesAdminContainer.setDesktop(_desktop);
		    DebitNotesAdminContainer.setIcon(true);
		    */
		    /*
		    ExtendedInternalFrame bookKeepingEntryContainer = new ExtendedInternalFrame("Nuevo asiento", IconTypes.budgets);
		    BookKeepingEntryMgmt bookKeepingEntryMgmt = new BookKeepingEntryMgmt();
		    bookKeepingEntryContainer.setClosable(false);
		    bookKeepingEntryContainer.setCentralPanel(bookKeepingEntryMgmt);
		    bookKeepingEntryContainer.setDesktop(_desktop);
		    bookKeepingEntryContainer.setIcon(true);*/
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    CASHFLOW_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("CashFlow loaded");
		    Debug.println("CASHFLOW boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("CASHFLOW already running!");
		}
		break;
	    case INIT_HALT:
		if (CASHFLOW_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("CASHFLOW halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		}
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
		new ExtendedInternalFrame("GIS 2010\n(Beta)", IconTypes.gaia, GEADemo.class, true, _desktop);
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
	/** REPORTING JFREE 0.8.2 */
	 Thread _thread = new Thread(new Runnable() {
	        public void run() {
		    JFreeReportBoot.getInstance().start();
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
			/*Environment.mp3Player = new StandalonePlayer();
			Environment.mp3Player.setDesktop(_desktop);*/
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
		    /*
		    JCTermSwingFrame x = new JCTermSwingFrame("SSH TERM");
		    x.setSize(800,600);
		    x.setDesktop(_desktop);
		    x.setClosable(false);
		    x.setIconifiable(true);
		    x.setIcon(true);
		    */
		    /*Boot.rcCashFlowClientModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_CASHFLOW));
		     Boot.rcNewsModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_NEWS));
		     Boot.rcChatModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_CHAT));*/
		    /**BOTONES*/
		    /**
		      * BOTON SALIR
		      * */
		    /*DesktopButton btnExit = new DesktopButton("Salir");
		    btnExit.setRolloverEnabled(true);
		    btnExit.setIcon(IconTypes.exit_32x32);
		    btnExit.setRolloverIcon(IconTypes.exit_ro_32x32);
		    btnExit.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					closeApplication();
				    }

				}
		    );
		    _desktop.addButton(btnExit);*/
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
		      * DICCIONARIO DEL SISTEMA
		      * */
		    new ExtendedInternalFrame("Dictionary", IconTypes.diccionario, PanelDictionary.class, true, _desktop);
		    /**
		     * INFORMACION DEL SISTEMA
		     * */
		    ExtendedInternalFrame systemInfo = new ExtendedInternalFrame("Info del Sistema", IconTypes.info_del_sistema);
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
		    /**
		     * ORGANIGRAMA
		     * */
		    new ExtendedInternalFrame("Sistema Expedientes", IconTypes.sistema_expedientes, principal_simex.class, true, _desktop);

		    new ExtendedInternalFrame("Acción Social", IconTypes.administrar_catastros, AccionSocialPrincipal.class, true, _desktop);

		    DesktopButton btnPiramidePoblacional = new DesktopButton("Pirámide Poblacional (Prueba)");
		    btnPiramidePoblacional.setRolloverEnabled(true);
		    btnPiramidePoblacional.setIcon(IconTypes.recursos_humanos);
		    btnPiramidePoblacional.setRolloverIcon(IconTypes.recursos_humanos);
		    btnPiramidePoblacional.addActionListener(new ActionListener() {

		                    public void actionPerformed(ActionEvent e) {
		                        generarPiramidePoblacional();
		                    }
			}
		    );
		    _desktop.addButton(btnPiramidePoblacional);

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
			    //, PurchaseOrdersList.class.getResource("xml/purchaseOrdersListReport.xml"));
			    _card.setClosable(false);
			    _card.setDesktop(_desktop);
			    _card.show();
			    ComponentsManager.setConfigurable(_card);
			    //Environment.addInternalFrameForSaving(_card);
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

    public static void rcResourcesModule(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (RESOURCES_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting RESOURCES");
		    Environment.jpStatusBar.setAction("Loading Resources");
		    RESOURCES_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    new ExtendedInternalFrame("Ver/crear\npedidos", IconTypes.ver_crear_pedidos_de_materiales, ResourcesRequestsMain.class, true, _desktop);
		    new ExtendedInternalFrame("Autorizar\nPedidos de Materiales", IconTypes.autorizar_pedidos_de_materiales, ResourcesRequestsAuthMain.class, true, _desktop);
		    /**
		     * BOTON PARA APROBAR LOS PEDIDOS DE MATERIALES
		     * AUTORIZADOS
		     */
		    new ExtendedInternalFrame("Aprobar Pedidos\nde Materiales", IconTypes.aprobar_pedidos_de_materiales, ResourcesRequestsApproveMgmt.class, true, _desktop);
		    new ExtendedInternalFrame("Generar\nOrden de Compra", IconTypes.generar_orden_de_compra, PurchaseOrderGenerateList.class, true, _desktop);
		    new ExtendedInternalFrame("Órdenes de Compra\nexistentes", IconTypes.ordenes_de_compra_existentes, PurchaseOrdersList.class, true, _desktop);
		    new ExtendedInternalFrame("Generar\nOrden de Provisión", IconTypes.generar_orden_de_provision, ProvisionOrderMain.class, true, _desktop);
		    new ExtendedInternalFrame("Listado de\nRemitos Externos", IconTypes.listado_de_remitos_externos, DespatchNotesList.class, true, _desktop);
		    new ExtendedInternalFrame("Entrega de Recursos\n(Interno)", IconTypes.entrega_de_recursos, ResourcesDeliverList.class, true, _desktop);
		    new ExtendedInternalFrame("Recursos\nmateriales", IconTypes.recursos_materiales, ResourcesList.class, true, _desktop);
		    new ExtendedInternalFrame("Proveedores", IconTypes.proveedores, ProvidersMain.class, true, _desktop);
		    new ExtendedInternalFrame("Lista de\nBienes de Uso", IconTypes.bienes_de_uso, DistinguishableResourcesList.class, true, _desktop);
		    new ExtendedInternalFrame("Ingresos de Recursos\npor Compras (Externos)", IconTypes.ingreso_de_recursos_por_compras, ResourcesReceiveMain.class, true, _desktop);

		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    RESOURCES_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Resources loaded");
		    Debug.println("RESOURCES boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("RESOURCES already running!");
		}
		break;
	    case INIT_HALT:
		if (RESOURCES_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("RESOURCES halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
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

    public static void rcTaxes(int _action, BasicDesktop _desktop) {
	LibSQL.setVerboseMode(false);
	long initTime = System.currentTimeMillis();
	switch (_action) {
	    case INIT_START:
		if (TAXES_MODULE_STATUS != STATUS_RUNNING) {
		    Debug.println("Booting TAXES");
		    Environment.jpStatusBar.setAction("Loading Taxes");
		    TAXES_MODULE_STATUS = STATUS_STARTING;
		    Environment.mainTabbedPane.setBackgroundAt(_desktop.getIdDesktop(), _desktop.getStartColor());
		    /**
		     * CODIGO DE INICIO DEL MODULO
		     * */
		    new ExtendedInternalFrame("Administrar\nCatastros", IconTypes.administrar_catastros, CadastralList.class, true, _desktop);
		    new ExtendedInternalFrame("Administrar\nAutomotores", IconTypes.administrar_automotores, CarsList.class, true, _desktop);
		    new ExtendedInternalFrame("Administrar\nComercios", IconTypes.administrar_comercios, CommercesList.class, true, _desktop);
		    new ExtendedInternalFrame("Administrar\nAlquileres", IconTypes.administrar_alquileres, RentsList.class, true, _desktop);
		    //new ExtendedInternalFrame("Limpiar Anticipos\nTGS/Imp. Inmob.", IconTypes.limpiar_anticipos_tgs, ClearTaxesTGS.class, true, _desktop);
		    new ExtendedInternalFrame("Limpiar Anticipos\nAct. Varias", IconTypes.limpiar_anticipos_act_varias, ClearCommerceTaxes.class, true, _desktop);
		    //new ExtendedInternalFrame("Limpiar Anticipos\nImp. Automotor", IconTypes.limpiar_anticipos_automotores, ClearCarsTaxes.class, true, _desktop);
		    new ExtendedInternalFrame("Limpiar Anticipos\nde Alquileres", IconTypes.limpiar_anticipos_de_alquileres, ClearRentTaxes.class, true, _desktop);
		    //new ExtendedInternalFrame("Registrar\nPago de Impuestos", IconTypes.registrar_pago_de_impuestos, PayTaxesVoucher.class, true, _desktop);
		    /* DESCOMENTAR
		    new ExtendedInternalFrame("Configurar\nImpuestos", IconTypes.limpiar_anticipos_de_alquileres, TaxesAdminMgmt.class, true, _desktop);
		    new ExtendedInternalFrame("Configurar\nPlanes de Pago", IconTypes.limpiar_anticipos_de_alquileres, PlanOfPaymentsAdminMgmt.class, true, _desktop);
		    new ExtendedInternalFrame("Registrar\nContribuciones", IconTypes.limpiar_anticipos_de_alquileres, ContributionsMgmt.class, true, _desktop);
		    new ExtendedInternalFrame("Configurar\nContribuciones - Alicuotas", IconTypes.limpiar_anticipos_de_alquileres, ContribucionAlicuotasAdminMgmt.class, true, _desktop);
		     */
		    /**
		      * BOTON PARA LA ADMINISTRACION DE PAGO DE LOS IMPUESTOS
		      * TGS, INMOBILIARIO Y AUTOMOTOR
		      * */
		    new ExtendedInternalFrame("Administración\nde Impuestos\n(Versión anterior)", IconTypes.administracion_de_impuestos, TaxesMain.class, true, _desktop);
		    /**
		      * BOTON DE ADMINISTRACION DE CAJA
		      * */
		    ExtendedInternalFrame cashierMainContainer = new ExtendedInternalFrame("Cajero", IconTypes.cajero, CashierMain.class, true, _desktop);
		    cashierMainContainer.setMaximizable(true);
		    /**
		       * MODULO DE LICENCIA DE CONDUCIR
		       * */
		    //new ExtendedInternalFrame("Configuración\nde Licencias", IconTypes.configuracion_de_licencias, LicensesConfigMgmt.class, true, _desktop);
		    //new ExtendedInternalFrame("Conductores", IconTypes.conductores, DriversList.class, true, _desktop);
		    //new ExtendedInternalFrame("Radiogramas", IconTypes.radiogramas, RadiogramsList.class, true, _desktop);
		    
		     /**
		       * BOTON PARA LA ADMINISTRACION DE PAGO DE LOS IMPUESTOS 02
		       * TGS, INMOBILIARIO Y AUTOMOTOR
		       * */
		     new ExtendedInternalFrame("Administración\nde Impuestos\n(Nueva versión)", IconTypes.administracion_de_impuestos, TaxesMain092.class, true, _desktop);
		     
		    /**
		     * FIN CODIGO DE INICIO DEL MODULO
		     * */
		    TAXES_MODULE_STATUS = STATUS_RUNNING;
		    Environment.jpStatusBar.setAction("Taxes loaded");
		    Debug.println("TAXES boot time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
		} else {
		    Debug.println("TAXES already running!");
		}
		break;
	    case INIT_HALT:
		if (TAXES_MODULE_STATUS != STATUS_STOPPED) {
		    //Shutdown module
		    //Debug.println("TAXES halt time: " + (System.currentTimeMillis() - initTime) + " milliseconds");
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
	Environment.organization = "DIGITALL S.H.";
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

    private static void generarPiramidePoblacional() {
	
	try {
	    DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
	    ResultSet _population = LibSQL.exFunction("accionsocial.getAllPoblacion", "''");
	    while (_population.next()) {
	        //data.addValue(-_population.getDouble(3)*.18, "Analfabetos", _population.getString(1));
	        //data.addValue(-_population.getDouble(3)*.82, "Hombres", _population.getString(1));
	        if (_population.getBoolean(2)) {
	            data.addValue(-_population.getDouble(3), "Hombres", _population.getString(1));
	            System.out.println((-_population.getDouble(3)) + ", " + "Hombres" + ", " + _population.getString(1));
	        } else {
	            data.addValue(_population.getDouble(3), "Mujeres", _population.getString(1));
	            System.out.println(_population.getDouble(3) + ", " + "Mujeres" + ", " + _population.getString(1));
	        }
	    }
	    CategoryDataset dataset = data;

	           // create the chart...
	           JFreeChart chart = ChartFactory.createStackedBarChart(
	               "Pirámide Poblacional",
	               "Edades",     // domain axis label
	               "Población", // range axis label
	               dataset,         // data
	               PlotOrientation.HORIZONTAL,
	               true,            // include legend
	               true,            // tooltips
	               false            // urls
	           );

	           // add the chart to a panel...
	           ChartPanel chartPanel = new ChartPanel(chart);
		   chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		   chartPanel.setSize(new java.awt.Dimension(500, 270));
	           ExtendedInternalFrame _populationChart = new ExtendedInternalFrame("Población");
		   _populationChart.setClosable(true);
		   _populationChart.setMaximizable(true);
		   _populationChart.setCentralPanel(chartPanel);
		   _populationChart.setDesktop(Environment.getActiveDesktop());
		   _populationChart.show();
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
    }

}
