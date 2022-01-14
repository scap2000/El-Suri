package org.digitall.projects.gea.salta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import org.digitall.common.systemmanager.interfaces.MonitorSelector;
import org.digitall.lib.common.SplashWindow;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.Login;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicMenuBar;
import org.digitall.lib.components.basic.BasicMenuItem;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.BasicToolBarButton;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class MainFrame extends JFrame {

    private BorderLayout layoutMain = new BorderLayout();
    private BasicPanel jpCenterContainer = new BasicPanel(new BorderLayout());
    private BasicMenuBar menuBar = new BasicMenuBar();
    private JMenu menuFile = new JMenu();
    private BasicMenuItem menuFileExit = new BasicMenuItem();
    private JMenu menuHelp = new JMenu();
    private BasicMenuItem menuHelpAbout = new BasicMenuItem();
    private BasicDesktop mainDesktop = new BasicDesktop(-1, "MAIN DESKTOP");
    private BasicDesktop[] desktops;
    private boolean active = true;
    private MainPanel jpSouthContainer = new MainPanel(this);
    private boolean fullScreen = false;
    private BasicTabbedPane mainTabbedPane = new BasicTabbedPane(BasicTabbedPane.LEFT, BasicTabbedPane.SCROLL_TAB_LAYOUT) {

	    public void setSelectedIndex(int _index) {
		if (getSelectedIndex() != -1) {
		    mainDesktop.switchDesktops(Environment.desktops[getSelectedIndex()], Environment.desktops[_index]);
		} else {
		    Environment.desktops[_index].setVisible(true);
		    Environment.desktops[_index].setActive(true);
		    mainDesktop.relocateDesktops();
		}
		Environment.addDesktopIndexToHistory(_index);
		super.setSelectedIndex(_index);
	    }

	}
    ;

    public MainFrame() {
	try {
	    Environment.SYSTEM_VERSION = "GEA [Salta] V.20100429-RC_2.ø";
	    setUndecorated(true);
	    setIconImage(IconTypes.lock_16x16.getImage());
	    Login loginWindow = new Login(Environment.organization, Environment.developer, true, false);
	    ComponentsManager.centerWindow(loginWindow);
	    loginWindow.setModal(true);
	    loginWindow.setVisible(true);
	    if (loginWindow.getValidado()) {
		//LibSQL.setDateTime();
		jbInit();
	    } else {
		System.exit(0);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds());
	Environment.mainFrame = this;
	startDesktop();
    }

    private void startDesktop() {
	/**
	 * Muestro la pantalla de bienvenida SplashWindow
	 * */
	setVisible(false);
	SplashWindow sw = new SplashWindow();
	ComponentsManager.centerWindow(sw);
	sw.setVisible(true);
	sw.setAlwaysOnTop(true);
	sw.setModal(true);
	this.getContentPane().setLayout(layoutMain);
	this.getContentPane().add(jpSouthContainer, BorderLayout.SOUTH);
	jpSouthContainer.setPreferredSize(new Dimension(jpSouthContainer.getWidth(), 100));
	jpCenterContainer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	this.setTitle(Environment.organization + " - " + Environment.developer);
	menuHelp.add(new BasicToolBarButton("BasicToolBarButton!"));
	menuHelp.add(new AcceptButton());
	menuHelp.add(new BasicRadioButton("Radio", true));
	menuHelp.add(new BasicCheckBox("Check", true));
	menuFile.add(menuFileExit);
	menuBar.add(menuFile);
	menuHelp.addSeparator();
	menuHelp.add(menuHelpAbout);
	menuBar.add(menuHelp);
	jpCenterContainer.add(mainDesktop, BorderLayout.CENTER);
	jpCenterContainer.add(Environment.jpStatusBar, BorderLayout.NORTH);
	this.getContentPane().add(jpCenterContainer, BorderLayout.CENTER);
	menuFile.setModel(new DefaultButtonModel());
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {

		       public void windowClosing(WindowEvent e) {
			   closeApplication();
		       }

		       public void windowOpened(WindowEvent windowEvent) {

		       }

		       public void windowClosed(WindowEvent windowEvent) {

		       }

		       public void windowIconified(WindowEvent windowEvent) {
		       }

		       public void windowDeiconified(WindowEvent windowEvent) {
		           setBounds(Environment.graphicsDevice.getDefaultConfiguration().getBounds());
		       }

		       public void windowActivated(WindowEvent windowEvent) {

		       }

		       public void windowDeactivated(WindowEvent windowEvent) {

		       }

		   }
	);
	desktops = new BasicDesktop[8];
	desktops[0] = new BasicDesktop(Boot.MAIN_MODULE, Environment.MODULE_MAIN);
	desktops[1] = new BasicDesktop(Boot.TAXES_MODULE, Environment.MODULE_TAXES);
	desktops[2] = new BasicDesktop(Boot.CASHFLOW_MODULE, Environment.MODULE_CASHFLOW);
	desktops[3] = new BasicDesktop(Boot.RESOURCES_MODULE, Environment.MODULE_RESOURCES);
	desktops[4] = new BasicDesktop(Boot.ASSETS_MODULE, Environment.MODULE_ASSETS);
	desktops[5] = new BasicDesktop(Boot.REPORTS_MODULE, Environment.MODULE_REPORTS);
	desktops[6] = new BasicDesktop(Boot.GAIA_MODULE, Environment.MODULE_GAIA);
	desktops[7] = new BasicDesktop(Boot.STICKYNOTES_MODULE, Environment.MODULE_STICKYNOTES);
	Environment.setMainTabbedPane(mainTabbedPane);
	Environment.setDesktops(desktops);
	
	desktops[0].setStartColor(new Color(81, 72, 42));
	desktops[0].setEndColor(new Color(30, 30, 30));
	desktops[1].setStartColor(new Color(17, 27, 58));
	desktops[1].setEndColor(new Color(30, 30, 30));
	desktops[2].setStartColor(new Color(44, 78, 28));
	desktops[2].setEndColor(new Color(30, 30, 30));
	desktops[3].setStartColor(new Color(66, 17, 60));
	desktops[3].setEndColor(new Color(30, 30, 30));
	desktops[4].setStartColor(new Color(90, 18, 18));
	desktops[4].setEndColor(new Color(30, 30, 30));
	desktops[5].setStartColor(new Color(182, 162, 94));
	desktops[5].setEndColor(new Color(30, 30, 30));
	desktops[6].setStartColor(new Color(180, 91, 23));
	desktops[6].setEndColor(new Color(30, 30, 30));
	desktops[7].setStartColor(new Color(81, 72, 42));
	desktops[7].setEndColor(new Color(30, 30, 30));
	
	for (int i = 0; i < desktops.length; i++) {
	    desktops[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	    mainTabbedPane.addTab(desktops[i].getName(), null, null, "Click y selecciona el escritorio de trabajo");
	    mainDesktop.add(desktops[i]);
	    desktops[i].setOpaco(false);
	    desktops[i].setVisible(false);
	}
	try {
	    mainTabbedPane.setSelectedIndex(Integer.parseInt(Environment.cfg.getProperty("SelectedTab")));
	    if (mainTabbedPane.getSelectedIndex() < 0) {
		mainTabbedPane.setSelectedIndex(0);
	    }
	} catch (Exception x) {
	    mainTabbedPane.setSelectedIndex(0);
	}
	//startDesktop();
	/*patch para JDesktop con Scrollbars
	JScrollPane xk = new JScrollPane(desktops[0]);
	desktops[0].setPreferredSize(new Dimension(10000,10000));
	mainTabbedPane.add(xk, "KK");
	endpatch*/
	setBounds(Environment.graphicsDevice.getDefaultConfiguration().getBounds());
	validate();
	if (active) {
	    Environment.jpStatusBar.setAction("Loading environment");
	    Environment.jpStatusBar.setIndeterminate(true);
	    if (Environment.debugMode) {
		//Console.hookStandards();
		//Console.println("Debugging...");
	    }
	    Boot.rc3rdpartyApps(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_MAIN));
	    Boot.rcMainModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_MAIN));
	    //Boot.rcReportsModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_REPORTS));
	    Boot.rcStickyNotesModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_STICKYNOTES));
	    //Boot.rcGEARecaudacion(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_REPORTS));
	    //Boot.rcGEARelevamientoComercial(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_GAIA));
	    Boot.rcGEARecaudacion(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_GAIA));
	    Environment.jpStatusBar.setAction("All modules loaded");
	    Environment.jpStatusBar.setIndeterminate(false);
	    setVisible(true);
	    sw.dispose();
	    Advisor.messagePopupWindow("Bienvenido usuario " + Environment.sessionUser, "");
	    Environment.jpStatusBar.setUser(Environment.sessionUser);

	    KeyStroke fullScreenSwitch = KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0);
	    JRootPane rootPane = getRootPane();
	    ActionListener fullScreenListener = new ActionListener()  {

	            public void actionPerformed(ActionEvent actionEvent) {
	                switchFullScreen();
	            }

	        };
	    rootPane.registerKeyboardAction(fullScreenListener, "fullScreenSwitch", fullScreenSwitch, JComponent.WHEN_IN_FOCUSED_WINDOW );
	}
    }

    public void switchFullScreen() {
	jpSouthContainer.setVisible(fullScreen);
	Environment.jpStatusBar.setVisible(fullScreen);
	fullScreen = !fullScreen;
    }

    public void closeApplication() {
	Advisor.closeApplication();
    }

}
