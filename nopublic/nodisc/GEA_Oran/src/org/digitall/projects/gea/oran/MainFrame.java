package org.digitall.projects.gea.oran;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

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
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.org.Companies;
import org.digitall.lib.sql.LibSQL;

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
    //private CashFlowToolBar tbCashFlowMgmt;
    //private CashFlowClientToolBar tbCashFlowClient;
    private MainPanel jpRightContainer = new MainPanel(this);
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
    private JSplitPane splitPanel = new JSplitPane();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();

    public MainFrame() {
	try {
	    Environment.SYSTEM_VERSION = "GEA Oran 2010.02.28-ß";
	    setUndecorated(true);
	    setIconImage(IconTypes.lock_16x16.getImage());
	    Login loginWindow = new Login(Environment.organization, Environment.developer, true, false);
	    ComponentsManager.centerWindow(loginWindow);
	    //inicio.setLocation((int)inicio.getLocation().getX(), (int)(sw.getLocation().getY() + sw.getBounds().getHeight()));
	    loginWindow.setModal(true);
	    loginWindow.setVisible(true);
	    if (loginWindow.getValidado()) {
		//LibSQL.setDateTime();

		jbInit();
		//setUndecorated(true);
		//setResizable(false);
		//sw.dispose();
	    } else {
		System.exit(0);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
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
	//this.setJMenuBar(menuBar);
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	this.getContentPane().setLayout(layoutMain);
	//this.getContentPane().add(jpNorthContainer, BorderLayout.EAST);
	jpRightContainer.setPreferredSize(new Dimension(300, jpRightContainer.getHeight()));
	jpCenterContainer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds());
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
	//para poner un menú alineado a la derecha
	//menuBar.add(Box.createHorizontalGlue());
	//menuBar.add(subMenu);
	this.getContentPane().add(Environment.jpStatusBar, BorderLayout.SOUTH);
	//jpCenterContainer.add(mainTabbedPane, BorderLayout.CENTER);

	//jSplitPane1.setOneTouchExpandable(true);
	splitPanel.add(mainDesktop, JSplitPane.TOP);
	splitPanel.add(jpRightContainer, JSplitPane.BOTTOM);
	splitPanel.setContinuousLayout(false);
	jpRightContainer.setMinimumSize(new Dimension(0, getHeight()));
	jpRightContainer.setMaximumSize(new Dimension(300, getHeight()));
	mainDesktop.setMinimumSize(new Dimension(300, getHeight()));
	mainDesktop.setMaximumSize(new Dimension(getWidth()-300, getHeight()));
	this.getContentPane().add(splitPanel, BorderLayout.CENTER);
	splitPanel.setDividerLocation(getWidth()-jpRightContainer.getWidth());


	for (int i = 0; i < splitPanel.getComponents().length; i++) {
	    if (splitPanel.getComponents()[i].getClass().toString().contains("Divider")) {
	        splitPanel.getComponents()[i].addMouseListener(new MouseAdapter() {

				 public void mouseClicked(MouseEvent e) {
				    if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
				        splitPanel.setDividerLocation((int)(getWidth()-300));
				    }
				 }

		
	    });
	    }
	}

	//jpCenterContainer.add(mainDesktop, BorderLayout.CENTER);
	jpCenterContainer.add(Environment.jpStatusBar, BorderLayout.SOUTH);
	//this.getContentPane().add(tbModules, BorderLayout.WEST);
	//this.getContentPane().add(jpCenterContainer, BorderLayout.CENTER);
	menuFile.setModel(new DefaultButtonModel());
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {

		       public void windowClosing(WindowEvent e) {
			   closeApplication();
		       }

		   }
	);
	desktops = new BasicDesktop[1];
	desktops[0] = new BasicDesktop(0, Environment.MODULE_GAIA);
	Environment.setMainTabbedPane(mainTabbedPane);
	Environment.setDesktops(desktops);
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
	    //if (Environment.sessionUser.equals("admin")) {
	    //--Boot.rcCommandCenterModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_COMMANDCENTER));
	    //--Boot.rcMainModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_MAIN));
	    //--Boot.rcCashFlowAdminModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_CASHFLOWADMIN));
	    //--Boot.rcResourcesRequestsModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_RESOURCESREQUESTS));
	    //--Boot.rcResourcesControlModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_RESOURCES));
	    //Boot.rcLogisticsModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_LOGISTICS));
	    //Boot.rcTasksModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_TASKS));
            Boot.rc3rdpartyApps(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_MAIN));
	    Boot.rcSystemMapModule(Boot.INIT_START, Environment.getDesktop(Environment.MODULE_GAIA));

	    Environment.jpStatusBar.setAction("All modules loaded");
	    Environment.jpStatusBar.setIndeterminate(false);
	    setVisible(true);
	    sw.dispose();
	    Advisor.messagePopupWindow("Bienvenido usuario " + Environment.sessionUser, "");
	    Environment.jpStatusBar.setUser(Environment.sessionUser);
	}
    }

    public void closeApplication() {
	int answer = JOptionPane.showConfirmDialog(this, "¿Desea cerrar el sistema?", "Cerrar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (answer == JOptionPane.YES_OPTION) {
	    //AVISAR A LOS MODULOS QUE SE VA A CERRAR TODO!!!
	    /**
	     * GRABAR OPCIONES
	     * */
	    Environment.cfg.setProperty("SelectedTab", String.valueOf(Environment.mainTabbedPane.getSelectedIndex()));
	    Environment.saveAll();
	    System.exit(0);
	}
    }

    /*private void endDesktop() {
	System.exit(0);
    }*/
}
