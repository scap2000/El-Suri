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
 *
 * */
    package org.digitall.projects.kratos.mosconi;
    
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
    import java.util.Vector;
    
    import javax.swing.JInternalFrame;
    import javax.swing.ToolTipManager;
    import javax.swing.UIManager;
    import javax.swing.UnsupportedLookAndFeelException;
    import javax.swing.plaf.ColorUIResource;
    import javax.swing.plaf.FontUIResource;
    import javax.swing.plaf.InsetsUIResource;
    
    import org.digitall.apps.accionsocial.interfaces.AccionSocialPrincipal;
    import org.digitall.apps.accionsocial.interfaces.EntregasList;
    import org.digitall.apps.calendar.interfaces.CoordinatorStickyNote;
    import org.digitall.apps.calendar.interfaces.DCalendar;
    import org.digitall.apps.calendar.interfaces.NewsList;
    import org.digitall.apps.calendar.interfaces.StickyNote;
    import org.digitall.apps.cashflow.interfaces.accounting.AccountsAvailableAmountList;
    import org.digitall.apps.cashflow.interfaces.accounting.GraphicsMain;
    import org.digitall.apps.cashflow.interfaces.accounting.JournalList;
    import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryByModelMgmt;
    import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryList;
    import org.digitall.apps.cashflow.interfaces.accountingentry.BookKeepingEntryModelsList;
    import org.digitall.apps.cashflow.interfaces.accountingentry.CreditNotesMgmt;
    import org.digitall.apps.cashflow.interfaces.accountingentry.DebitNotesMgmt;
    import org.digitall.apps.cashflow.interfaces.paymentorder.PaymentOrderList;
    import org.digitall.apps.cashflow.interfaces.paymentorder.PaymentOrderManualMgmt;
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
    import org.digitall.apps.sueldos.interfaces.ConceptosList;
    import org.digitall.apps.sueldos.interfaces.ConfiguracionBaseMain;
    import org.digitall.apps.sueldos.interfaces.CredentialList;
    import org.digitall.apps.sueldos.interfaces.LegajosPanelMain;
    import org.digitall.apps.sueldos.interfaces.LiquidacionSueldosMain;
    import org.digitall.apps.sueldos.interfaces.PanelAnticiposHaberes;
    import org.digitall.apps.taxes.interfases.cadastraladmin.CadastralList;
    import org.digitall.apps.taxes.interfases.carsadmin.CarsList;
    import org.digitall.apps.taxes.interfases.cashier.CashierMain;
    import org.digitall.apps.taxes.interfases.clearfees.ClearCommerceTaxes;
    import org.digitall.apps.taxes.interfases.clearfees.ClearRentTaxes;
    import org.digitall.apps.taxes.interfases.commercesadmin.CommercesList;
    import org.digitall.apps.taxes.interfases.contribution.ContributionsMgmt;
    import org.digitall.apps.taxes.interfases.rentsadmin.RentsList;
    import org.digitall.apps.taxes.interfases.taxesadmin.TaxesAdminMgmt;
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
    import org.digitall.common.mapper.CoordinateViewer;
    import org.digitall.common.mapper.RuleViewer;
import org.digitall.common.resourcescontrol.interfaces.ReempadronamientoMaterialesMgmt;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
    import org.digitall.common.resourcescontrol.interfaces.SkillList;
    import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
    import org.digitall.common.resourcesrequests.interfaces.provisionorder.ProvisionOrderMain;
import org.digitall.common.resourcesrequests.interfaces.purchaseorder.GeneratePurchaseOrder;
import org.digitall.common.resourcesrequests.interfaces.purchaseorder.PurchaseOrderGenerateList;
    import org.digitall.common.resourcesrequests.interfaces.purchaseorder.PurchaseOrdersList;
    import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverList;
    import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveMain;
    import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsApproveMgmt;
    import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMain;
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
    import org.digitall.lib.geo.mapping.classes.LayerProfile;
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
    
    import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
    
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
                         new ExtendedInternalFrame("Credenciales Municipales", IconTypes.configuracion_base_de_sueldos, CredentialList.class, true, _desktop);
                         new ExtendedInternalFrame("Administración Horas ", IconTypes.configuracion_base_de_sueldos, AdministracionHoras.class, true, _desktop);
                         new ExtendedInternalFrame("Administración de Conceptos", IconTypes.configuracion_base_de_sueldos, ConceptosList.class, true, _desktop);
                         new ExtendedInternalFrame("Anticipos de Haberes", IconTypes.configuracion_base_de_sueldos, PanelAnticiposHaberes.class, true, _desktop);
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
                        //new ExtendedInternalFrame("Nueva Orden\nde Pago", IconTypes.nueva_orden_de_pago, PaymentOrderMgmt.class, true, _desktop);
                        //8.1)
                        new ExtendedInternalFrame("Nueva Orden\nde Pago\n(Nueva versión)", IconTypes.nueva_orden_de_pago, PaymentOrderManualMgmt.class, true, _desktop);
                        //8.2)
                        new ExtendedInternalFrame("Marcar Facturas\nPagadas", IconTypes.nueva_orden_de_pago, MarkVoucherMgmt.class, true, _desktop);
                        //9)
                        new ExtendedInternalFrame("Listado de\nÓrdenes de Pago", IconTypes.orden_de_pago, PaymentOrderList.class, true, _desktop);
                        //10)
                        new ExtendedInternalFrame("Comprobantes", IconTypes.comprobantes, VouchersList.class, true, _desktop);
                        //11)
                        new ExtendedInternalFrame("Registrar\nComprobante a Pagar", IconTypes.registrar_comprobantes_a_pagar, InvoiceTypeB.class, true, _desktop);
                        //12)
                        new ExtendedInternalFrame("Facturar Materiales\npor Notas de Recepción", IconTypes.facturar_notas_de_recepcion, VoucherToInvoiceMain.class, true, _desktop);
                        //13)
                        new ExtendedInternalFrame("Facturar\nÓrdenes de Provisión", IconTypes.facturar_orden_de_provision, VoucherToProvisionOrderMain.class, true, _desktop);
                        //14)
                        new ExtendedInternalFrame("Asientos", IconTypes.asientos, BookKeepingEntryList.class, true, _desktop);
                        //15)
                        new ExtendedInternalFrame("Nuevo Asiento\nmediante Modelos", IconTypes.nuevo_asiento_mediante_modelo, BookKeepingEntryByModelMgmt.class, true, _desktop);
                        //16)
                        new ExtendedInternalFrame("Modelos de\nAsientos Contables", IconTypes.modelos_de_asientos, BookKeepingEntryModelsList.class, true, _desktop);
                        //17)
                        new ExtendedInternalFrame("Libro Mayor", IconTypes.libro_mayor, JournalList.class, true, _desktop);
                        //17)
                        new ExtendedInternalFrame("Gráficos", IconTypes.libro_mayor, GraphicsMain.class, true, _desktop);
                        //18)
                        new ExtendedInternalFrame("Balance de\nsumas y saldos", IconTypes.balance_de_sumas_y_saldos, AccountsAvailableAmountList.class, true, _desktop);
                        //19)
                        //20)
                        //21)
                        //22)
                        //23)
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
                    /**
                      * Boton TEST
                      * */
                    final DesktopButton btnTest = new DesktopButton("Cargar versión de\nPrueba del GIS", IconTypes.gaia);
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
                    _desktop.addButton(btnTest);
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
                        /*ExtendedInternalFrame geoCalc = new ExtendedInternalFrame("Calculadora geografica", IconTypes.geocalc_32x32);
                        CoordinateCalculator geoCalcPanel = new CoordinateCalculator();
                        geoCalc.setClosable(false);
                        geoCalc.setCentralPanel(geoCalcPanel);
                        geoCalc.setDesktop(_desktop);
                        geoCalc.setIcon(true);*/
                        /**
                         * NOVEDADES
                         * */
                        new ExtendedInternalFrame("Novedades", IconTypes.inbox_32x32, NewsList.class, true, _desktop);
    
                        new ExtendedInternalFrame("Sistema Expedientes", IconTypes.sistema_expedientes, principal_simex.class, true, _desktop);
    
                        new ExtendedInternalFrame("Acción Social", IconTypes.administrar_catastros, AccionSocialPrincipal.class, true, _desktop);
                        
                        new ExtendedInternalFrame("Administrar Entregas", IconTypes.administrar_catastros, EntregasList.class, true, _desktop);
    
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
                                Environment.addUnfinishedTask(_card);
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
                        new ExtendedInternalFrame("Ver/crear\nPedidos de Materiales", IconTypes.ver_crear_pedidos_de_materiales, ResourcesRequestsMain.class, true, _desktop);
                        new ExtendedInternalFrame("Autorizar\nPedidos de Materiales", IconTypes.autorizar_pedidos_de_materiales, ResourcesRequestsAuthMain.class, true, _desktop);
                        new ExtendedInternalFrame("Aprobar Pedidos\nde Materiales", IconTypes.aprobar_pedidos_de_materiales, ResourcesRequestsApproveMgmt.class, true, _desktop);
			new ExtendedInternalFrame("Generar Orden de Compra\npor un Pedido de Materiales", IconTypes.generar_orden_de_compra, PurchaseOrderGenerateList.class, true, _desktop);
			new ExtendedInternalFrame("Generar Orden de Compra\npor varios Pedidos de Materiales", IconTypes.generar_orden_de_compra, GeneratePurchaseOrder.class, true, _desktop);
                        new ExtendedInternalFrame("Órdenes de Compra\nexistentes", IconTypes.ordenes_de_compra_existentes, PurchaseOrdersList.class, true, _desktop);
			new ExtendedInternalFrame("Recepción de Materiales\npor Órdenes de Compra", IconTypes.ingreso_de_recursos_por_compras, ResourcesReceiveMain.class, true, _desktop);
                        new ExtendedInternalFrame("Ver/crear\nÓrdenes de Provisión", IconTypes.generar_orden_de_provision, ProvisionOrderMain.class, true, _desktop);
                        new ExtendedInternalFrame("Listado de\nRemitos Externos", IconTypes.listado_de_remitos_externos, DespatchNotesList.class, true, _desktop);
                        new ExtendedInternalFrame("Entrega de Recursos\n(Interno)", IconTypes.entrega_de_recursos, ResourcesDeliverList.class, true, _desktop);
                        new ExtendedInternalFrame("Administración\nde Materiales", IconTypes.recursos_materiales, ResourcesList.class, true, _desktop);
                        new ExtendedInternalFrame("Reempadronamiento\nde Materiales", IconTypes.recursos_materiales, ReempadronamientoMaterialesMgmt.class, true, _desktop);
                        new ExtendedInternalFrame("Proveedores", IconTypes.proveedores, ProvidersMain.class, true, _desktop);
                        new ExtendedInternalFrame("Lista de\nBienes de Uso", IconTypes.bienes_de_uso, DistinguishableResourcesList.class, true, _desktop);
    
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
                        
                        new ExtendedInternalFrame("Configurar\nImpuestos", IconTypes.limpiar_anticipos_de_alquileres, TaxesAdminMgmt.class, true, _desktop);
                        
                        //new ExtendedInternalFrame("Configurar\nContribuciones - Alicuotas", IconTypes.limpiar_anticipos_de_alquileres, ContribucionAlicuotasAdminMgmt.class, true, _desktop);
                        new ExtendedInternalFrame("Registrar\nContribuciones", IconTypes.limpiar_anticipos_de_alquileres, ContributionsMgmt.class, true, _desktop);
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
                        //new ExtendedInternalFrame("Administración\nde Impuestos\n(Versión anterior)", IconTypes.administracion_de_impuestos, TaxesMain.class, true, _desktop);
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
                         
                        /*
                        /**
                          * BOTON PARA LA VISUALIZACION DE PAGOS REALIZADOS POR TODOS LOS BIENES
                          * TGS, INMOBILIARIO Y AUTOMOTOR, PLAN DE PAGO
                          * */
                        /*
                        new ExtendedInternalFrame("Últimos Pagos", IconTypes.administracion_de_impuestos, PanelUltimosPagos.class, true, _desktop);
                        */ 
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
    
        private static void loadGaia(final BasicDesktop _desktop) {
            Thread _thread = new Thread(new Runnable() {
                   public void run() {
                       try {
                           GaiaEnvironment.initialize();//línea agregada para cargar la configuración de GAIA
    
                           
                           BasicDrawEngine cityMap = new BasicDrawEngine("GIS Mosconi", new BasicLabel());
                           cityMap.setMapExtents(4410143.1624, 7497979.7965, 4419985.4601, 7508161.8277);
                           CoordinateViewer coordinateViewer = new CoordinateViewer();
                           coordinateViewer.setClosable(false);
                           cityMap.setCoordinateViewer(coordinateViewer);
                           coordinateViewer.setTitle("");
                           cityMap.setBounds(_desktop.getBounds());
                           cityMap.setVisible(true);
                           
                           if (!GaiaEnvironment.gaiaEngine.initialize()) {//línea agregada para cargar la configuración de GAIA
                                GaiaEnvironment.setScheme("gis_mosconi");
                                GeometrySet gsetSecciones = new GeometrySet("gis_mosconi", "secciones", "the_geom", "1=1", "gid");
                                GeometrySet gsetManzanasDesvinculadas = new GeometrySet("gis_mosconi", "manzanas_desvinculadas", "the_geom", "1=1", "gid");
                                GeometrySet gsetFraccionesDesvinculadas = new GeometrySet("gis_mosconi", "fracciones_desvinculadas", "the_geom", "1=1", "gid");
                                GeometrySet gsetNrosFracciones = new GeometrySet("gis_mosconi", "nros_fracciones", "the_geom", "1=1", "gid");
                                GeometrySet gsetCanales = new GeometrySet("gis_mosconi", "canales", "the_geom", "1=1", "gid");
                                GeometrySet gsetAlturaCalles = new GeometrySet("gis_mosconi", "altura_de_calles", "the_geom", "1=1", "gid");
                                GeometrySet gsetTramosCalles = new GeometrySet("gis_mosconi", "tramos_calles", "the_geom", "1=1", "gid");
                                GeometrySet gsetCalles = new GeometrySet("gis_mosconi", "calles", "the_geom", "1=1", "gid");
                                GeometrySet gsetAgua = new GeometrySet("gis_mosconi", "agua", "the_geom", "1=1", "gid");
                                GeometrySet gsetEspaciosVerdes = new GeometrySet("gis_mosconi", "espacios_verdes", "the_geom", "1=1", "gid");
                                GeometrySet gsetPlatabandas = new GeometrySet("gis_mosconi", "platabandas", "the_geom", "1=1", "gid");
                                GeometrySet gsetRutas = new GeometrySet("gis_mosconi", "rutas", "the_geom", "1=1", "gid");
                                GeometrySet gsetVeredas = new GeometrySet("gis_mosconi", "veredas", "the_geom", "1=1", "gid");
                                GeometrySet gsetManzanasVinculadas = new GeometrySet("gis_mosconi", "manzanas_vinculadas", "the_geom", "idmanzana<100000", "idmanzana");
                                GeometrySet gsetNrosManzanas = new GeometrySet("gis_mosconi", "nros_manzanas", "the_geom", "1=1", "gid");
                                GeometrySet gsetParcelasVinculadas = new GeometrySet("gis_mosconi", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
                                GeometrySet gsetParcelasDesvinculadas = new GeometrySet("gis_mosconi", "parcelas_desvinculadas", "the_geom", "1=1", "idparcela");
                                GeometrySet gsetNrosParcelas = new GeometrySet("gis_mosconi", "nros_parcelas", "the_geom", "1=1", "gid");
                                GeometrySet gsetInfraestructura = new GeometrySet("gis_mosconi", "infraestructura", "the_geom", "1=1", "gid");
                                GeometrySet gsetDirecciones = new GeometrySet("gis_mosconi", "direcciones", "the_geom", "1=1", "gid");
                                GeometrySet gsetCasosEnfermedades = new GeometrySet("gis_mosconi", "casosenfermedades", "the_geom", "estado != '*'", "gid");
                                GeometrySet gsetCensoComercial2009 = new GeometrySet("gea_salta", "censo_comercial_2009_seccion1", "the_geom", "estado != '*'", "idencuestacomercial");
                                GeometrySet gsetRelevamientoPublicidad2009 = new GeometrySet("gea_salta", "relevamientopublicidad_2009_detalle", "the_geom", "estado != '*'", "iddetallerelevamiento");
                                
                                GeometrySet gsetTartagalEspaciosVerdes = new GeometrySet("gis_tartagal", "espacios_verdes", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalFracciones = new GeometrySet("gis_tartagal", "fracciones", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalInfraestructura = new GeometrySet("gis_tartagal", "infraestructura", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalManzanas = new GeometrySet("gis_tartagal", "manzanas", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalNombreCalles = new GeometrySet("gis_tartagal", "nombre_calles", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalNombreFracciones = new GeometrySet("gis_tartagal", "nombre_fracciones", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalNombreManzanas = new GeometrySet("gis_tartagal", "nombre_manzanas", "the_geom", "isvalid(the_geom)", "gid");
                                GeometrySet gsetTartagalNombreMatricula = new GeometrySet("gis_tartagal", "nombre_matricula", "the_geom", "isvalid(the_geom)", "gid");
                                
                                GeometrySet gsetAccionSocialEntregas = new GeometrySet("accionsocial", "entregas", "the_geom", "1=1", "identrega");
                                GeometrySet gsetAccionSocialPersonas = new GeometrySet("accionsocial", "personas", "the_geom", "1=1", "idpersona");
                                /*Este código comentado ya no es necesario porque los grupos de geometrias se cargan en el citymap mediante el método cityMap.initialize()
                                cityMap.addGeometrySet(gsetSecciones);
                                cityMap.addGeometrySet(gsetManzanasDesvinculadas);
                                cityMap.addGeometrySet(gsetFraccionesDesvinculadas);
                                cityMap.addGeometrySet(gsetNrosFracciones);
                                cityMap.addGeometrySet(gsetCanales);
                                cityMap.addGeometrySet(gsetAlturaCalles);
                                cityMap.addGeometrySet(gsetCalles);
                                cityMap.addGeometrySet(gsetAgua);
                                cityMap.addGeometrySet(gsetEspaciosVerdes);
                                cityMap.addGeometrySet(gsetPlatabandas);
                                cityMap.addGeometrySet(gsetRutas);
                                cityMap.addGeometrySet(gsetVeredas);
                                cityMap.addGeometrySet(gsetManzanasVinculadas);
                                cityMap.addGeometrySet(gsetNrosManzanas);
                                cityMap.addGeometrySet(gsetParcelasVinculadas);
                                cityMap.addGeometrySet(gsetParcelasDesvinculadas);
                                cityMap.addGeometrySet(gsetNrosParcelas);
                                cityMap.addGeometrySet(gsetInfraestructura);
                                cityMap.addGeometrySet(gsetDirecciones);
                                cityMap.addGeometrySet(gsetCasosEnfermedades);
                                cityMap.addGeometrySet(gsetCensoComercial2009);
                                cityMap.addGeometrySet(gsetRelevamientoPublicidad2009);
                                // Tartagal
                                cityMap.addGeometrySet(gsetTartagalEspaciosVerdes);
                                cityMap.addGeometrySet(gsetTartagalFracciones);
                                cityMap.addGeometrySet(gsetTartagalInfraestructura);
                                cityMap.addGeometrySet(gsetTartagalManzanas);
                                cityMap.addGeometrySet(gsetTartagalNombreCalles);
                                cityMap.addGeometrySet(gsetTartagalNombreFracciones);
                                cityMap.addGeometrySet(gsetTartagalNombreManzanas);
                                cityMap.addGeometrySet(gsetTartagalNombreMatricula);
                                
                                cityMap.addGeometrySet(gsetAccionSocialEntregas);
                                cityMap.addGeometrySet(gsetAccionSocialPersonas);*/
                                
                                Layer secciones = new Layer("Secciones", gsetSecciones);
                                secciones.setColor(Color.MAGENTA);
                                secciones.setMouseOverColor(Color.ORANGE);
                                Layer manzanas_desvinculadas = new Layer("Manzanas (Desvinculadas)", gsetManzanasDesvinculadas);
                                manzanas_desvinculadas.setColor(Color.RED);
                                manzanas_desvinculadas.setMouseOverColor(Color.MAGENTA);
                                Layer fracciones_desvinculadas = new Layer("Fracciones (Desvinculadas)", gsetFraccionesDesvinculadas);
                                fracciones_desvinculadas.setColor(Color.GRAY);
                                fracciones_desvinculadas.setMouseOverColor(Color.GRAY.darker());
                                Layer nros_fracciones = new Layer("Nº Fracciones", gsetNrosFracciones, "maptext");
                                nros_fracciones.setColor(Color.BLACK);
                                nros_fracciones.setMouseOverColor(Color.CYAN);
                                nros_fracciones.setPointDiameter(16);
                                Layer canales = new Layer("Canales", gsetCanales);
                                canales.setColor(Color.BLUE);
                                canales.setMouseOverColor(Color.CYAN);
                                Layer altura_de_calles = new Layer("Altura de Calles", gsetAlturaCalles, "maptext");
                                altura_de_calles.setColor(Color.MAGENTA);
                                altura_de_calles.setMouseOverColor(Color.CYAN);
                                altura_de_calles.setPointDiameter(6);
                                altura_de_calles.setDrawGeometries(false);
    
                                Layer tramos_calles = new Layer("Tramos de Calles", gsetTramosCalles, "altura");
                                tramos_calles.setColor(Color.lightGray);
                                tramos_calles.setMouseOverColor(Color.CYAN);
                                tramos_calles.setQueryable(true);
    
                                Layer calles = new Layer("Calles", gsetCalles, "nombre");
                                calles.setColor(Color.lightGray);
                                calles.setMouseOverColor(Color.CYAN);
                                calles.setQueryable(true);
                                GaiaEnvironment.setStreetsLayer(calles.getAlias());
                                Layer agua = new Layer("Agua", gsetAgua);
                                agua.setColor(Color.CYAN);
                                agua.setMouseOverColor(Color.CYAN);
                                Layer espacios_verdes = new Layer("Espacios verdes", gsetEspaciosVerdes);
                                espacios_verdes.setColor(Color.GREEN.darker());
                                espacios_verdes.setMouseOverColor(Color.GREEN.darker());
                                Layer platabandas = new Layer("Platabandas", gsetPlatabandas);
                                platabandas.setColor(Color.GREEN.brighter());
                                platabandas.setMouseOverColor(Color.GREEN.brighter());
                                Layer rutas = new Layer("Rutas", gsetRutas);
                                rutas.setColor(Color.ORANGE);
                                rutas.setMouseOverColor(Color.ORANGE);
                                Layer veredas = new Layer("Veredas", gsetVeredas);
                                veredas.setColor(Color.PINK);
                                veredas.setMouseOverColor(Color.PINK);
                                Layer manzanas_vinculadas = new Layer("Manzanas", gsetManzanasVinculadas);
                                manzanas_vinculadas.setColor(Color.RED);
                                manzanas_vinculadas.setMouseOverColor(Color.MAGENTA);
                                Layer nros_manzanas = new Layer("Nº Manzanas", gsetNrosManzanas, "maptext");
                                nros_manzanas.setColor(Color.RED);
                                nros_manzanas.setMouseOverColor(Color.CYAN);
                                nros_manzanas.setPointDiameter(4);
                                Layer parcelas_vinculadas = new Layer("Parcelas", gsetParcelasVinculadas, "", "", "catastros");
                                parcelas_vinculadas.setColor(Color.BLACK);
                                parcelas_vinculadas.setMouseOverColor(Color.CYAN);
                                parcelas_vinculadas.setQueryable(true);
                                parcelas_vinculadas.setToolTipLabel("Catastros");
                                
                                Layer parcelas_desvinculadas = new Layer("Parcelas Desvinculadas", gsetParcelasDesvinculadas);
                                parcelas_desvinculadas.setColor(Color.BLACK);
                                parcelas_desvinculadas.setMouseOverColor(Color.CYAN);
                                parcelas_desvinculadas.setQueryable(true);
                                parcelas_desvinculadas.setToolTipLabel("Catastros");
                                
                                ExtendedInternalFrame parcelPanelContainer = new ExtendedInternalFrame("Ventana de Información");
                                GaiaParcelPanel parcelPanel = new GaiaParcelPanel();
                                parcelPanelContainer.setCentralPanel(parcelPanel);
                                parcelas_vinculadas.setInfoPanel(parcelPanelContainer);
                                GaiaEnvironment.setCadastralLayer(parcelas_vinculadas.getAlias());
                                Layer parcelas_categorias_tgs = new Layer("Categoría TGS", gsetParcelasVinculadas, "categoria");
                                parcelas_categorias_tgs.setColor(Color.BLACK);
                                parcelas_categorias_tgs.setMouseOverColor(Color.CYAN);
                                parcelas_categorias_tgs.setQueryable(true);
                                parcelas_categorias_tgs.addFilter("Categoría 1", "categoria", "(categoria = 1)", Color.black, Color.GREEN, "Categoría 1 TGS");
                                parcelas_categorias_tgs.addFilter("Categoría 2", "categoria", "(categoria = 2)", Color.black, Color.YELLOW, "Categoría 2 TGS");
                                parcelas_categorias_tgs.addFilter("Categoría 3", "categoria", "(categoria = 3)", Color.black, Color.RED, "Categoría 3 TGS");
                                
                                Layer parcelas_periodos_deuda_tgs = new Layer("Periodos Deuda TGS", gsetParcelasVinculadas);
                                parcelas_periodos_deuda_tgs.setColor(Color.BLACK);
                                parcelas_periodos_deuda_tgs.setMouseOverColor(Color.CYAN);
                                parcelas_periodos_deuda_tgs.setQueryable(true);
                                parcelas_periodos_deuda_tgs.addFilter("Hasta 3 anticipos", "", "(SELECT anticipostgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) <= 3", Color.black, Color.GREEN, "Hasta 3 anticipos");
                                parcelas_periodos_deuda_tgs.addFilter("Entre 3 y 12 anticipos", "", "(SELECT anticipostgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 4 AND 12", Color.black, Color.YELLOW, "Entre 3 y 12 anticipos");
                                parcelas_periodos_deuda_tgs.addFilter("Entre 1 y 2 años", "", "(SELECT anticipostgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 13 AND 24", Color.black, Color.RED, "Entre 1 y 2 años");
                                parcelas_periodos_deuda_tgs.addFilter("Más de 2 años", "", "(SELECT anticipostgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) > 24", Color.black, Color.BLUE, "Más de 2 años");
                                
                                Layer parcelas_periodos_deuda_inmob = new Layer("Periodos Deuda Imp. Inmob.", gsetParcelasVinculadas);
                                parcelas_periodos_deuda_inmob.setColor(Color.BLACK);
                                parcelas_periodos_deuda_inmob.setMouseOverColor(Color.CYAN);
                                parcelas_periodos_deuda_inmob.setQueryable(true);
                                parcelas_periodos_deuda_inmob.addFilter("Hasta 3 anticipos", "", "(SELECT anticiposinmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) <= 3", Color.black, Color.GREEN, "Hasta 3 anticipos");
                                parcelas_periodos_deuda_inmob.addFilter("Entre 3 y 12 anticipos", "", "(SELECT anticiposinmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 4 AND 12", Color.black, Color.YELLOW, "Entre 3 y 12 anticipos");
                                parcelas_periodos_deuda_inmob.addFilter("Entre 1 y 2 años", "", "(SELECT anticiposinmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 13 AND 24", Color.black, Color.RED, "Entre 1 y 2 años");
                                parcelas_periodos_deuda_inmob.addFilter("Más de 2 años", "", "(SELECT anticiposinmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) > 24", Color.black, Color.BLUE, "Más de 2 años");
                                
                                /*
                                Layer parcelas_deuda_tgs = new Layer("Deuda TGS", "gis_mosconi", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
                                parcelas_deuda_tgs.setColor(Color.BLACK);
                                parcelas_deuda_tgs.setMouseOverColor(Color.CYAN);
                                parcelas_deuda_tgs.setQueryable(true);
                                parcelas_deuda_tgs.addFilter("Hasta $ 100,00", "deudatgs", "(SELECT deudatgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) <= 100", Color.black, Color.GREEN, "Hasta $ 100,00");
                                parcelas_deuda_tgs.addFilter("Entre $ 100,00 y $ 500,00", "deudatgs", "(SELECT deudatgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 100.01 AND 500", Color.black, Color.YELLOW, "Entre $ 100,00 y $ 500,00");
                                parcelas_deuda_tgs.addFilter("Entre $ 500,00 y $ 1000,00", "deudatgs", "(SELECT deudatgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 500.01 AND 1000", Color.black, Color.RED, "Entre $ 500,00 y $ 1000,00");
                                parcelas_deuda_tgs.addFilter("Más de $ 1000,00", "deudatgs", "(SELECT deudatgs FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) > 1000", Color.black, Color.BLUE, "Más de $ 1000,00");
                                
                                Layer parcelas_deuda_inmob = new Layer("Deuda Imp. Inmob.", "gis_mosconi", "parcelas_vinculadas", "the_geom", "1=1", "idparcela");
                                parcelas_deuda_inmob.setColor(Color.BLACK);
                                parcelas_deuda_inmob.setMouseOverColor(Color.CYAN);
                                parcelas_deuda_inmob.setQueryable(true);
                                parcelas_deuda_tgs.addFilter("Hasta $ 100,00", "deudainmob", "(SELECT deudainmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) <= 100", Color.black, Color.GREEN, "Hasta $ 100,00");
                                parcelas_deuda_tgs.addFilter("Entre $ 100,00 y $ 500,00", "deudainmob", "(SELECT deudainmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 100.01 AND 500", Color.black, Color.YELLOW, "Entre $ 100,00 y $ 500,00");
                                parcelas_deuda_tgs.addFilter("Entre $ 500,00 y $ 1000,00", "deudainmob", "(SELECT deudainmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) BETWEEN 500.01 AND 1000", Color.black, Color.RED, "Entre $ 500,00 y $ 1000,00");
                                parcelas_deuda_tgs.addFilter("Más de $ 1000,00", "deudainmob", "(SELECT deudainmob FROM gis_mosconi.deudasxidparcela WHERE deudasxidparcela.idparcela = _id) > 1000", Color.black, Color.BLUE, "Más de $ 1000,00");
                                */
                                Layer parcelas_deuda_moratoria = new Layer("Estado de Cuenta y Moratoria", gsetParcelasVinculadas);
                                parcelas_deuda_moratoria.setColor(Color.BLACK);
                                parcelas_deuda_moratoria.setMouseOverColor(Color.CYAN);
                                parcelas_deuda_moratoria.setQueryable(true);
                                parcelas_deuda_moratoria.getLayerConfig().setAutoUpdateRateInSeconds(60);
                                parcelas_deuda_moratoria.addFilter("Tiene una moratoria/Se encuentra al día", "", GaiaEnvironment.getScheme() + ".getConsultaMoratoria(_id) > 0", Color.black, Color.GREEN, "Tiene una moratoria/Se encuentra al día");
                                parcelas_deuda_moratoria.addFilter("Averiguó sobre la moratoria", "", GaiaEnvironment.getScheme() + ".getConsultaMoratoria(_id) = 0", Color.black, Color.YELLOW, "Averiguó sobre la moratoria");
                                parcelas_deuda_moratoria.addFilter("No averiguó sobre la moratoria", "", GaiaEnvironment.getScheme() + ".getConsultaMoratoria(_id) < 0", Color.black, Color.RED, "No averiguó sobre la moratoria");
                                
                                Layer nros_parcelas = new Layer("Nº Parcelas", gsetNrosParcelas, "maptext");
                                nros_parcelas.setColor(Color.BLACK);
                                nros_parcelas.setMouseOverColor(Color.CYAN);
                                nros_parcelas.setPointDiameter(4);
                                nros_parcelas.setColor(Color.GREEN);
                                Layer infraestructura = new Layer("Infraestructura", gsetInfraestructura, "nombre", "tipo");
                                infraestructura.setColor(Color.BLACK);
                                infraestructura.setMouseOverColor(Color.CYAN);
                                infraestructura.setPointDiameter(4);
                                infraestructura.setQueryable(true);
                                infraestructura.setModifiable(true);
                                infraestructura.setTolerance(25);
                                //infraestructura.setSymbolsQuery("SELECT idtype, symbol FROM tabs.infrastructuretype_tabs ORDER BY idtype");
                                ExtendedInternalFrame infraestructuraPanelContainer = new ExtendedInternalFrame("Infraestructura Urbana");
                                GaiaInfrastructuresPanel infraestructuraPanel = new GaiaInfrastructuresPanel();
                                infraestructuraPanelContainer.setCentralPanel(infraestructuraPanel);
                                infraestructura.setConfigPanel(infraestructuraPanelContainer);
                                infraestructuraPanel.setLayer(infraestructura);
                                GaiaEnvironment.setInfrastructuresLayer(infraestructura.getAlias());
                                Layer direcciones = new Layer("Direcciones", gsetDirecciones, "numero");
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
                                Layer casosEnfermedades = new Layer("Control y Prev. de Enfermedades endémicas", gsetCasosEnfermedades, "nombre", "", "nombre");
                                casosEnfermedades.setColor(new Color(255, 0, 0));
                                casosEnfermedades.setMouseOverColor(Color.CYAN);
                                casosEnfermedades.setPointDiameter(2);
                                casosEnfermedades.setEditable(true);
                                ExtendedInternalFrame formsInternalFrame = new ExtendedInternalFrame("Formularios");
                                formsInternalFrame.setDesktop(_desktop);
                                BasicPanel formPanel = new BasicPanel();
                                formsInternalFrame.setClosable(false);
                                formsInternalFrame.setIconifiable(false);
                                formPanel.setLayout(new BorderLayout());
                                formPanel.setSize(new Dimension(300, 420));
                                GaiaEnvironment.formPanel = formPanel;
                                formsInternalFrame.setCentralPanel(formPanel);
                                GaiaEnvironment.formsFrame = formsInternalFrame;
                                GaiaFormCasosEnfermedad formCasosEnfermedades = new GaiaFormCasosEnfermedad();
                                formCasosEnfermedades.setLayer(casosEnfermedades);
                                ExtendedInternalFrame streetsPanelContainer = new ExtendedInternalFrame("Calles");
                                GaiaStreetsPanel streetsPanel = new GaiaStreetsPanel();
                                streetsPanelContainer.setCentralPanel(streetsPanel);
                                calles.setConfigPanel(streetsPanelContainer);
                                
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
                                
                                secciones.setOn();
                                
                                Layer consultas_01 = new Layer("Consultas 01", gsetParcelasVinculadas, "", "", "catastros");
                                consultas_01.setColor(Color.MAGENTA);
                                consultas_01.setMouseOverColor(Color.MAGENTA);
                                
                                Layer consultas_02 = new Layer("Consultas 02", gsetParcelasVinculadas, "", "", "catastros");
                                consultas_02.setColor(Color.MAGENTA);
                                consultas_02.setMouseOverColor(Color.MAGENTA);
                                
                                Layer consultas_03 = new Layer("Consultas 03", gsetParcelasVinculadas, "", "", "catastros");
                                consultas_03.setColor(Color.MAGENTA);
                                consultas_03.setMouseOverColor(Color.MAGENTA);
                                
                                Layer consultas_04 = new Layer("Consultas 04", gsetParcelasVinculadas, "", "", "catastros");
                                consultas_04.setColor(Color.MAGENTA);
                                consultas_04.setMouseOverColor(Color.MAGENTA);
                                
                                Layer consultas_05 = new Layer("Consultas 05", gsetParcelasVinculadas, "", "", "catastros");
                                consultas_05.setColor(Color.MAGENTA);
                                consultas_05.setMouseOverColor(Color.MAGENTA);
                                
                                LayerGroup catastralGroup = new LayerGroup("Catastral");
                                LayerGroup infraestructuraGroup = new LayerGroup("Infraestructura");
                                LayerGroup recaudacionGroup = new LayerGroup("Recaudación");
                                LayerGroup queryGroup = new LayerGroup("Consultas");
                                LayerGroup relevamientoGroup = new LayerGroup("Relevamiento");
                                LayerGroup tartagalGroup = new LayerGroup("Tartagal");
                                
                                Layer tartagal_manzanas = new Layer("Manzanas [Tartagal]", gsetTartagalManzanas);
                                tartagal_manzanas.setColor(Color.RED);
                                tartagal_manzanas.setMouseOverColor(Color.MAGENTA);
                                Layer tartagal_nros_manzanas = new Layer("Nº Manzanas [Tartagal]", gsetTartagalNombreManzanas, "textstring");
                                tartagal_nros_manzanas.setColor(Color.RED);
                                tartagal_nros_manzanas.setMouseOverColor(Color.CYAN);
                                tartagal_nros_manzanas.setPointDiameter(4);
                                Layer tartagal_espacios_verdes = new Layer("Espacios verdes [Tartagal]", gsetTartagalEspaciosVerdes);
                                tartagal_espacios_verdes.setColor(Color.GREEN.darker());
                                tartagal_espacios_verdes.setMouseOverColor(Color.GREEN.darker());
                                Layer tartagal_fracciones = new Layer("Fracciones [Tartagal]", gsetTartagalFracciones);
                                tartagal_fracciones.setColor(Color.GRAY);
                                tartagal_fracciones.setMouseOverColor(Color.GRAY.darker());
                                Layer tartagal_nros_fracciones = new Layer("Nº Fracciones [Tartagal]", gsetTartagalNombreFracciones, "textstring");
                                tartagal_nros_fracciones.setColor(Color.BLACK);
                                tartagal_nros_fracciones.setMouseOverColor(Color.CYAN);
                                tartagal_nros_fracciones.setPointDiameter(16);
                                Layer tartagal_nros_matricula = new Layer("Matrículas [Tartagal]", gsetTartagalNombreMatricula, "textstring");
                                tartagal_nros_matricula.setColor(Color.BLACK);
                                tartagal_nros_matricula.setMouseOverColor(Color.CYAN);
                                tartagal_nros_matricula.setPointDiameter(4);
                                tartagal_nros_matricula.setColor(Color.GREEN);
                                Layer tartagal_calles = new Layer("Calles [Tartagal]", gsetTartagalNombreCalles, "textstring");
                                tartagal_calles.setColor(Color.MAGENTA);
                                tartagal_calles.setMouseOverColor(Color.CYAN);
                                tartagal_calles.setPointDiameter(6);
                                tartagal_calles.setDrawGeometries(false);
                                Layer tartagal_infraestructura = new Layer("Infraestructura [Tartagal]", gsetTartagalInfraestructura);
                                tartagal_infraestructura.setColor(Color.BLACK);
                                tartagal_infraestructura.setMouseOverColor(Color.CYAN);
                                tartagal_infraestructura.setPointDiameter(4);
                                tartagal_infraestructura.setTolerance(25);
                                
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
                                
                                tartagalGroup.add(tartagal_manzanas);
                                tartagalGroup.add(tartagal_nros_manzanas);
                                tartagalGroup.add(tartagal_espacios_verdes);
                                tartagalGroup.add(tartagal_fracciones);
                                tartagalGroup.add(tartagal_nros_fracciones);
                                tartagalGroup.add(tartagal_nros_matricula);
                                tartagalGroup.add(tartagal_calles);
                                tartagalGroup.add(tartagal_infraestructura);
                                
                                GaiaEnvironment.layerGroups.add(catastralGroup);
                                GaiaEnvironment.layerGroups.add(infraestructuraGroup);
                                GaiaEnvironment.layerGroups.add(recaudacionGroup);
                                GaiaEnvironment.layerGroups.add(accionSocialGroup);
                                GaiaEnvironment.layerGroups.add(queryGroup);
                                GaiaEnvironment.layerGroups.add(relevamientoGroup);
                                GaiaEnvironment.layerGroups.add(tartagalGroup);
                                
                                accionSocialGroup.add(accionSocialPersonas);
                                accionSocialGroup.add(accionSocialEntregas);
                                
                                queryGroup.add(consultas_01);
                                queryGroup.add(consultas_02);
                                queryGroup.add(consultas_03);
                                queryGroup.add(consultas_04);
                                queryGroup.add(consultas_05);
                                
                                infraestructuraGroup.add(secciones);
                                catastralGroup.add(manzanas_desvinculadas);
                                catastralGroup.add(parcelas_desvinculadas);
                                catastralGroup.add(fracciones_desvinculadas);
                                infraestructuraGroup.add(canales);
                                infraestructuraGroup.add(agua);
                                infraestructuraGroup.add(espacios_verdes);
                                infraestructuraGroup.add(platabandas);
                                infraestructuraGroup.add(rutas);
                                infraestructuraGroup.add(veredas);
                                infraestructuraGroup.add(casosEnfermedades);
                                catastralGroup.add(manzanas_vinculadas);
                                catastralGroup.add(parcelas_vinculadas);
                                catastralGroup.add(tramos_calles);
                                catastralGroup.add(calles);
                                catastralGroup.add(altura_de_calles);
                                catastralGroup.add(direcciones);
                                infraestructuraGroup.add(infraestructura);
                                recaudacionGroup.add(parcelas_categorias_tgs);
                                recaudacionGroup.add(parcelas_periodos_deuda_tgs);
                                recaudacionGroup.add(parcelas_periodos_deuda_inmob);
                                //recaudacionGroup.add(parcelas_deuda_tgs);
                                //recaudacionGroup.add(parcelas_deuda_inmob);
                                recaudacionGroup.add(parcelas_deuda_moratoria);
                                catastralGroup.add(nros_fracciones);
                                catastralGroup.add(nros_parcelas);
                                catastralGroup.add(nros_manzanas);
                                
                                relevamientoGroup.add(comercios_2009);
                                relevamientoGroup.add(publicidad_2009);
                                
                                /*Este código comentado ya no es necesario porque los grupos de layers se cargan en el citymap mediante el método cityMap.initialize()
                                cityMap.addLayerGroup(accionSocialGroup);
                                cityMap.addLayerGroup(catastralGroup);
                                cityMap.addLayerGroup(infraestructuraGroup);
                                cityMap.addLayerGroup(recaudacionGroup);
                                cityMap.addLayerGroup(queryGroup);
                                cityMap.addLayerGroup(relevamientoGroup);
                                cityMap.addLayerGroup(tartagalGroup);
                                */
                                GaiaEnvironment.nomencladorLayers.add(manzanas_vinculadas);
                                GaiaEnvironment.nomencladorLayers.add(calles);
                                GaiaEnvironment.nomencladorLayers.add(parcelas_vinculadas);
                                GaiaEnvironment.nomencladorLayers.add(parcelas_desvinculadas);
                                GaiaEnvironment.nomencladorLayers.add(direcciones); 
                                
                                
                                //líneas agregadas para cargar la configuración de GAIA
                                GaiaEnvironment.gaiaEngine.setLayerGroupList(GaiaEnvironment.layerGroups);
                                Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
                                
                                geometrySets.add(gsetSecciones);
                                geometrySets.add(gsetManzanasDesvinculadas);
                                geometrySets.add(gsetFraccionesDesvinculadas);
                                geometrySets.add(gsetNrosFracciones);
                                geometrySets.add(gsetCanales);
                                geometrySets.add(gsetAlturaCalles);
                                geometrySets.add(gsetTramosCalles);
                                geometrySets.add(gsetCalles);
                                geometrySets.add(gsetAgua);
                                geometrySets.add(gsetEspaciosVerdes);
                                geometrySets.add(gsetPlatabandas);
                                geometrySets.add(gsetRutas);
                                geometrySets.add(gsetVeredas);
                                geometrySets.add(gsetManzanasVinculadas);
                                geometrySets.add(gsetNrosManzanas);
                                geometrySets.add(gsetParcelasVinculadas);
                                geometrySets.add(gsetParcelasDesvinculadas);
                                geometrySets.add(gsetNrosParcelas);
                                geometrySets.add(gsetInfraestructura);
                                geometrySets.add(gsetDirecciones);
                                geometrySets.add(gsetCasosEnfermedades);
                                geometrySets.add(gsetCensoComercial2009);
                                geometrySets.add(gsetRelevamientoPublicidad2009);
                                // Tartagal
                                geometrySets.add(gsetTartagalEspaciosVerdes);
                                geometrySets.add(gsetTartagalFracciones);
                                geometrySets.add(gsetTartagalInfraestructura);
                                geometrySets.add(gsetTartagalManzanas);
                                geometrySets.add(gsetTartagalNombreCalles);
                                geometrySets.add(gsetTartagalNombreFracciones);
                                geometrySets.add(gsetTartagalNombreManzanas);
                                geometrySets.add(gsetTartagalNombreMatricula);
                                
                                geometrySets.add(gsetAccionSocialEntregas);
                                geometrySets.add(gsetAccionSocialPersonas);
                                
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
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.ADDRESSES_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.STREETS_EDITION_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.INFRASTRUCTURES_EDITION_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.LAYER_EDITION_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.PRINT_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.MULTIQUERY_TOOL);
                            mapBasicToolsFrame.addTool(MapBasicToolsPanel.FIXED_POLYGON_QUERY_TOOL);
                            mapBasicToolsFrame.setClosable(false);
                            //_desktop.setActive(true);
                            
                            RuleViewer ruleViewer = new RuleViewer();
                            ruleViewer.setClosable(false);
                            cityMap.setRuleViewer(ruleViewer);
                            ruleViewer.setTitle("");
                            
                            _desktop.add(coordinateViewer);
                            _desktop.add(ruleViewer);
                            _desktop.add(cityMap);
                            _desktop.add(layerListFrame);
                            _desktop.add(mapBasicToolsFrame);
                            layerListFrame.show();
                            mapBasicToolsFrame.setDrawPanel(cityMap);
                            mapBasicToolsFrame.show();
                            coordinateViewer.setVisible(false);
                            ruleViewer.setVisible(false);
                            
                            ComponentsManager.setConfigurable(layerListFrame);
                            ComponentsManager.setConfigurable(coordinateViewer);
                            ComponentsManager.setConfigurable(ruleViewer);
                            ComponentsManager.setConfigurable(mapBasicToolsFrame);
                            
                            GaiaCadastralFinderPanel calendarIFrame = new GaiaCadastralFinderPanel();
                            calendarIFrame.setDesktop(_desktop);
                            calendarIFrame.setDrawEngine(cityMap);
                            calendarIFrame.setVisible(false);
                            calendarIFrame.start();
                            _desktop.setBottomRightComponent(calendarIFrame);
                       } catch (Exception x) {
                           Advisor.printException(x);
                       }
                       _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                   }
            });
            try {
                _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Environment.jpStatusBar.setIndeterminate(true);
                Environment.jpStatusBar.setAction("Cargando versión de\n Prueba del GIS");
                _thread.start();
            } catch (Exception x) {
                _desktop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Environment.jpStatusBar.setIndeterminate(false);
                Environment.jpStatusBar.setAction("Listo...");
                x.printStackTrace();
            }
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
