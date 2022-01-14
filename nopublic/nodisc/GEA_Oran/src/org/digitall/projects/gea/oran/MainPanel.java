package org.digitall.projects.gea.oran;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.digitall.common.geo.mapping.components.LayerListPanel;
import org.digitall.common.geo.mapping.components.MapBasicToolsPanel;
import org.digitall.common.systemmanager.ChangePassword;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;

public class MainPanel extends BasicPanel {

    private JFrame parent;
    private ImageIcon dateContainerImage = IconTypes.getIcon("iconos/ui/desktoppanel/datecontainer.png");
    private ImageIcon btnContainerCenterImage = IconTypes.getIcon("iconos/ui/desktoppanel/btncontainercenter.png");
    private ImageIcon btnContainerRightImage = IconTypes.getIcon("iconos/ui/desktoppanel/btncontainerright.png");
    private ImageIcon btnContainerLeftImage = IconTypes.getIcon("iconos/ui/desktoppanel/btncontainerleft.png");
    private ImageIcon bottomRightImage = IconTypes.getIcon("iconos/ui/desktoppanel/bottomrightlogo.png");
    private ImageIcon topDividerImage = IconTypes.getIcon("iconos/ui/desktoppanel/topdivider.png");
    private ImageIcon bottomDividerImage = IconTypes.getIcon("iconos/ui/desktoppanel/bottomdivider.png");
    private ImageIcon bottomCenterImage = IconTypes.getIcon("iconos/ui/desktoppanel/poweredby.png");
    private ImageIcon btnPlayerImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnplayer.png");
    private ImageIcon btnNextImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnnext.png");
    private ImageIcon btnPreviousImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnprevious.png");
    private ImageIcon btnExitImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnexit.png");
    private ImageIcon btnMinimizeImage = IconTypes.getIcon("iconos/ui/desktoppanel/btnminimize.png");
    private ImageIcon bottomLeftLogo = IconTypes.getIcon("iconos/ui/desktoppanel/bottomleftlogo.png");
    private BasicPanel jpBottom = new BasicPanel();
    private BasicPanel jpTop = new BasicPanel();
    private BasicPanel jpCenter = new BasicPanel();
    private BorderLayout blMain = new BorderLayout();
    private BorderLayout blCenter = new BorderLayout();
    private BorderLayout blCentralPanel = new BorderLayout();
    private BorderLayout blRight = new BorderLayout();
    private BorderLayout blLeft = new BorderLayout();
    private GridBagLayout gblBtnContainer = new GridBagLayout();
    private GridBagLayout gblTopRight = new GridBagLayout();
    private GridBagLayout gblBottomRight = new GridBagLayout();
    private GridBagLayout gblTopLeft = new GridBagLayout();
    private GridBagLayout gblBottomLeft = new GridBagLayout();
    private BasicButton btnPlayer = new BasicButton(btnPlayerImage);
    private BasicButton btnPrevious = new BasicButton(btnPreviousImage);
    private BasicButton btnNext = new BasicButton(btnNextImage);
    private BasicPanel jpCentralPanel = new BasicPanel();
    private BasicPanel jpTopRight = new BasicPanel();
    private BasicPanel jpDesktopButtons = new BasicPanel();
    private BasicPanel jpTopLeft = new BasicPanel();
    private BasicPanel jpBottomRight = new BasicPanel();
    private BasicLabel lblDateContainer = new BasicLabel();
    private BasicPanel jpBtnContainer = new BasicPanel();
    private BasicLabel lblBottomLeft = new BasicLabel(bottomLeftLogo);
    private BasicPanel jpButtonsContainer = new BasicPanel();
    private BasicLabel lblCenterContainer = new BasicLabel();//btnContainerCenterImage);
    private BasicLabel lblRightContainer = new BasicLabel();//btnContainerRightImage);
    private BasicLabel lblLeftContainer = new BasicLabel();//btnContainerLeftImage);
    private BasicButton btnExit = new BasicButton(btnExitImage);
    private BasicButton btnMinimize = new BasicButton(btnMinimizeImage);
    private BoxLayout blButtonsContainer;
    private BasicLabel lblLeftFiller = new BasicLabel();
    private BasicLabel lblBottomRight = new BasicLabel(bottomRightImage);
    private BasicLabel lblTopRight = new BasicLabel();//rightUpImage);
    private BasicButton lblBottomCenter = new BasicButton(bottomCenterImage);
    //private BasicButton btnLogistics = new BasicButton(IconTypes.logistics_32x32);
    private Separator lblSeparator4 = new Separator();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private JLabel lblTopEmpty = new JLabel();
    private LayerListPanel layerListPanel = new LayerListPanel();
    private BasicPanel formPanel = new BasicPanel();
    private MapBasicToolsPanel mapBasicToolsPanel = new MapBasicToolsPanel();
    private BasicLabel topDivider = new BasicLabel(topDividerImage);
    private BasicLabel lblLayers = new BasicLabel();
    private BasicPanel jpBottomNorth = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicLabel lblCredits = new BasicLabel();
    private BasicLabel lblBottomDivider = new BasicLabel(bottomDividerImage);
    private BasicLabel lblDateTimeText = new BasicLabel();
    private BorderLayout borderLayout2 = new BorderLayout();

    public MainPanel(JFrame _parent) {
	try {
	    parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	GaiaEnvironment.layerListPanel = layerListPanel;
	GaiaEnvironment.formPanel = formPanel;
	GaiaEnvironment.mapBasicToolsPanel = mapBasicToolsPanel;
	mapBasicToolsPanel.setHorizontal();
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.ZOOM_IN_TOOL);
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.ZOOM_OUT_TOOL);
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.ZOOM_EXTENTS_TOOL);
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.RULE_TOOL);
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.LAYER_EDITION_TOOL);
	mapBasicToolsPanel.addTool(MapBasicToolsPanel.PRINT_TOOL);
	this.setLayout(blMain);
	this.setSize(new Dimension(300, 600));
	jpBottom.setPreferredSize(new Dimension(150, 100));
	jpBottom.setLayout(blLeft);
	jpBottom.setBackground(Color.white);
	jpBottom.setOpaque(false);
	jpTop.setPreferredSize(new Dimension(150, 140));
	jpTop.setLayout(blRight);
	jpTop.setOpaque(true);
	jpCenter.setLayout(blCenter);
	jpCenter.setOpaque(false);
	jpCenter.setSize(new Dimension(300, 450));
	jpCentralPanel.setLayout(blCentralPanel);
	jpCentralPanel.setOpaque(false);
	lblDateContainer.setIcon(dateContainerImage);
	lblDateContainer.setHorizontalAlignment(SwingConstants.CENTER);
	lblDateContainer.setPreferredSize(new Dimension(365, 36));
	lblDateContainer.setSize(new Dimension(359, 26));
	lblDateContainer.setIconTextGap(0);
	lblDateContainer.setVerticalAlignment(SwingConstants.TOP);
	lblDateContainer.setHorizontalTextPosition(SwingConstants.CENTER);
	lblDateContainer.setBounds(new Rectangle(0, 0, 300, 26));
	lblDateContainer.setForeground(Color.WHITE);
	lblDateContainer.setLayout(borderLayout2);
	jpBtnContainer.setLayout(gblBtnContainer);
	jpBtnContainer.setPreferredSize(new Dimension(114, 60));
	jpBtnContainer.setOpaque(false);
	jpBottomRight.setLayout(gblBottomRight);
	jpBottomRight.setOpaque(false);
	jpBottomRight.setMaximumSize(new Dimension(75, 32));
	jpBottomRight.setMinimumSize(new Dimension(75, 32));
	jpBottomRight.setPreferredSize(new Dimension(85, 32));
	lblBottomLeft.setPreferredSize(new Dimension(70, 32));
	//jpBottomLeft.setLayout(gblBottomLeft);
	lblBottomLeft.setOpaque(true);
	lblBottomLeft.setBackground(Color.white);
	btnPlayer.setHorizontalAlignment(SwingConstants.CENTER);
	btnPlayer.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnPlayer_actionPerformed(e);
				 }

			     }
	);
	btnPrevious.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnPrevious_actionPerformed(e);
				   }

			       }
	);
	btnNext.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnNext_actionPerformed(e);
			       }

			   }
	);
	lblCenterContainer.setHorizontalAlignment(SwingConstants.CENTER);
	lblCenterContainer.setPreferredSize(new Dimension(234, 24));
	lblRightContainer.setPreferredSize(new Dimension(80, 24));
	lblLeftContainer.setHorizontalAlignment(SwingConstants.RIGHT);
	lblLeftContainer.setPreferredSize(new Dimension(80, 24));
	btnExit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnExit_actionPerformed(e);
			       }

			   }
	);
	btnMinimize.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnMinimize_actionPerformed(e);
				   }

			       }
	);
	jpButtonsContainer.setBounds(new Rectangle(29, 44, 394, 20));
	jpButtonsContainer.setPreferredSize(new Dimension(10, 51));
	jpButtonsContainer.setOpaque(false);
	blButtonsContainer = new BoxLayout(jpButtonsContainer, BoxLayout.X_AXIS);
	jpButtonsContainer.setLayout(blButtonsContainer);
	jpButtonsContainer.setSize(new Dimension(577, 51));
	jpButtonsContainer.setMaximumSize(new Dimension(82, 51));
	lblLeftFiller.setMaximumSize(new Dimension(20, 22));
	lblLeftFiller.setMinimumSize(new Dimension(20, 22));
	lblLeftFiller.setPreferredSize(new Dimension(0, 22));
	lblBottomRight.setIconTextGap(0);
	lblBottomRight.setPreferredSize(new Dimension(85, 29));
	lblBottomRight.setOpaque(true);
	lblBottomRight.setBackground(Color.white);
	lblTopRight.setLayout(gridBagLayout1);
	lblTopRight.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTopRight.setPreferredSize(new Dimension(129, 70));
	lblBottomCenter.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      lblTopLeft_actionPerformed(e);
				  }

			      }
	);
	/*btnLogistics.setRolloverEnabled(true);
	btnLogistics.setRolloverIcon(IconTypes.logistics_ro_32x32);
	btnLogistics.setHorizontalAlignment(SwingConstants.CENTER);
	btnLogistics.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnLogistics_actionPerformed(e);
				    }

				}
	);*/
	layerListPanel.setPreferredSize(new Dimension(452, 175));
	layerListPanel.setOpaque(false);
	jpTopRight.setLayout(gblTopRight);
	jpTopRight.setOpaque(false);
	jpTopRight.setMinimumSize(new Dimension(150, 50));
	jpTopRight.setPreferredSize(new Dimension(150, 50));
	jpTopRight.setSize(new Dimension(300, 50));
	jpTopRight.setMaximumSize(new Dimension(150, 50));
	jpTopRight.setBounds(new Rectangle(0, 32, 300, 45));
	jpDesktopButtons.setOpaque(false);
	jpTopLeft.setLayout(gblTopLeft);
	//jpBottomLeft.add(btnPlayer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 60), 0, 0));
	jpTopLeft.setBackground(Color.white);
	jpBottom.add(lblBottomLeft, BorderLayout.WEST);
	jpTopLeft.add(lblBottomCenter, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jpBottom.add(jpTopLeft, BorderLayout.CENTER);
	this.add(jpBottom, BorderLayout.SOUTH);
	jpDesktopButtons.add(btnPrevious, null);
	jpDesktopButtons.add(btnNext, null);
	//jpBottomRight.add(jpDesktopButtons, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 53, 0, 0), 40, 7));
	jpBottom.add(lblBottomRight, BorderLayout.EAST);
	jpBottom.add(jpBottomNorth, BorderLayout.NORTH);
	lblDateContainer.add(lblDateTimeText, BorderLayout.CENTER);
	jpBottomRight.add(lblDateContainer, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	lblTopRight.add(btnMinimize, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	lblTopRight.add(btnExit, new GridBagConstraints(3, 0, 1, 1, 0.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
	lblTopRight.add(lblTopEmpty, new GridBagConstraints(1, 0, 1, 1, 3.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	lblTopRight.add(topDivider, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jpTopRight.add(lblTopRight, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jpTop.add(jpTopRight, BorderLayout.CENTER);
	this.add(jpTop, BorderLayout.NORTH);
	jpTop.add(jpBtnContainer, BorderLayout.SOUTH);
	jpTop.add(jpBottomRight, BorderLayout.NORTH);
	//jpCentralPanel.add(, BorderLayout.SOUTH);
	jpCentralPanel.add(layerListPanel, BorderLayout.NORTH);
	formPanel.setLayout(new BorderLayout());
	formPanel.setSize(new Dimension(300, 200));
	formPanel.setOpaque(false);
	formPanel.setPreferredSize(new Dimension(300, 100));
	lblLayers.setText("Layers");
	lblLayers.setHorizontalAlignment(SwingConstants.RIGHT);
	jpBottomNorth.setPreferredSize(new Dimension(10, 50));
	jpBottomNorth.setLayout(borderLayout1);
	jpBottomNorth.add(lblCredits, BorderLayout.SOUTH);
	jpBottomNorth.add(lblBottomDivider, BorderLayout.CENTER);
	jpBottomNorth.setOpaque(true);
	lblCredits.setText("Créditos   ");
	lblCredits.setPreferredSize(new Dimension(46, 20));
	lblCredits.setHorizontalAlignment(SwingConstants.RIGHT);
	lblDateTimeText.setForeground(Color.white);
	lblDateTimeText.setHorizontalAlignment(SwingConstants.CENTER);
	Environment.lblCalendar = lblDateTimeText;
	jpCentralPanel.add(formPanel, BorderLayout.CENTER);
	jpButtonsContainer.add(lblLeftFiller, null);
	//jpButtonsContainer.add(lblSeparator4, null);
	jpButtonsContainer.add(mapBasicToolsPanel, null);
	jpBtnContainer.add(jpButtonsContainer, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(-22, 0, 8, 0), 450, 0));
	jpBtnContainer.add(lblCenterContainer, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 0, 0), 0, 0));
	jpBtnContainer.add(lblRightContainer, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(12, 0, 0, 0), 0, 0));
	jpBtnContainer.add(lblLeftContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(12, 0, 0, 0), 0, 0));
	jpBtnContainer.add(lblLayers, new GridBagConstraints(0, 2, 3, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 20), 0, 0));
	jpCenter.add(jpCentralPanel, BorderLayout.CENTER);
	this.add(jpCenter, BorderLayout.CENTER);
	lblBottomCenter.setToolTipText("http://www.digitallsh.com.ar");
	lblBottomCenter.setBackground(Color.white);
	btnExit.setToolTipText("Salir");
	btnMinimize.setToolTipText("Minimizar");
    }
    //JPANEL with Gradient Background

    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(0, 0, BasicConfig.PANEL_GRADIENT_START_COLOR, 0, h, BasicConfig.PANEL_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
    }

    private void btnExit_actionPerformed(ActionEvent e) {
	if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
	    ChangePassword pass = new ChangePassword(Environment.sessionUser);
	    pass.setModal(true);
	    pass.setVisible(true);
	} else {
	    closeApplication();
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
	    if (Environment.saveAll()) {
		System.exit(0);
	    } else {
		Advisor.messageBox("No se puede salir del programa hasta que no se hayan completado o cancelado las operaciones pendientes", "Aviso");
	    }
	}
    }

    private void btnStickyNotes_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_STICKYNOTES));
    }

    private void btnMain_actionPerformed(ActionEvent e) {
	Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_MAIN));
    }

    private void btnCashflowAdmin_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_CASHFLOWADMIN));
    }

    private void btnResourcesRequests_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_RESOURCESREQUESTS));
    }

    private void btnResourcesControl_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_RESOURCES));
    }

    private void btnLogistics_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_LOGISTICS));
    }

    private void btnTasks_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_COMMANDCENTER));
    }

    private void btnPrevious_actionPerformed(ActionEvent e) {
    
    }

    private void btnNext_actionPerformed(ActionEvent e) {

    }

    private void btnPlayer_actionPerformed(ActionEvent e) {
	//Environment.mp3Player.setVisible(!Environment.mp3Player.isVisible());
    }

    private void lblTopLeft_actionPerformed(ActionEvent e) {
	BrowserLauncher.openURL("http://www.digitallsh.com.ar/");
    }

    private void btnSystemMap_actionPerformed(ActionEvent e) {
	//Environment.mainTabbedPane.setSelectedIndex(Environment.getDesktopIndex(Environment.MODULE_SYSTEMMAP));
    }

    private void btnMinimize_actionPerformed(ActionEvent e) {
	parent.setState(JFrame.ICONIFIED);
    }

    private void lblSeparator1_mouseClicked(MouseEvent e) {
	if ((e.getModifiers() & (MouseEvent.SHIFT_MASK | MouseEvent.CTRL_MASK | MouseEvent.ALT_MASK)) == (MouseEvent.SHIFT_MASK | MouseEvent.CTRL_MASK | MouseEvent.ALT_MASK)) {
	    //DBAdminApp dbadmin = new DBAdminApp();
	}
    }

    private class Separator extends BasicLabel {

	private int width = 15;

	public Separator() {
	    super(IconTypes.getIcon("iconos/ui/desktoppanel/btnseparator.png"));
	    setPreferredSize(new Dimension(width, 24));
	    setSize(new Dimension(width, 24));
	    setMinimumSize(new Dimension(width, 24));
	    setMaximumSize(new Dimension(width, 24));
	}

    }

}
