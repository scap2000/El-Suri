package org.digitall.lib.components.basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyVetoException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ReloadButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

public class BasicInternalFrameNorthPane extends BasicPanel {

    private ExtendedInternalFrame parent;
    private Point location;
    private Point pressed;
    private boolean isPressed = false;
    private Timer relocateTimer;
    private BasicLabel btnDeploy;
    /**
     * esto me sirve para mostrar el popup
     * */
    private JPopupMenu popup;
    private BasicMenuItem menuIconify;
    private JMenu menuTransfer;
    private BasicRadioButton[] desktopMenuItems;
    private ImageIcon icon;
    private BasicPanel rightButtons = new BasicPanel();
    private BasicButton btnClose;
    private ReloadButton btnReload;
    private BasicButton btnIconify;
    private BasicPanel jpTopRight = new BasicPanel();
    private JLabel lblTopRight = new JLabel(IconTypes.ext_iframe_top_right);
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BoxLayout borderLayout5 = new BoxLayout(rightButtons, BoxLayout.LINE_AXIS);
    private BasicPanel jpTitle = new BasicPanel();
    private BoxLayout boxLayout21;
    private BasicTitleLabel lblTitle = new BasicTitleLabel("", icon, JLabel.LEFT);
    private boolean closable = true;
    private boolean iconifiable = true;

    public BasicInternalFrameNorthPane(ExtendedInternalFrame _parent, ImageIcon _icon) {
	super();
	icon = _icon;
	parent = _parent;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(false);
	menuIconify = new BasicMenuItem("Iconificar");
	menuTransfer = new JMenu("Transfer to another desktop");
	popup = new JPopupMenu();
	popup.add(menuIconify);
	popup.add(menuTransfer);
	btnDeploy = new BasicLabel();
	/*patch comento lo siguiente porque no es un button
	btnDeploy.setRolloverEnabled(true);
	btnDeploy.setRolloverIcon(IconTypes.unAssign_left_ro_16x16);
	btnDeploy.setPressedIcon(IconTypes.unAssign_left_pr_16x16);
	btnDeploy.setDisabledIcon(IconTypes.assignRight_ne_16x16);
	btnDeploy.setDisabledSelectedIcon(IconTypes.unAssign_left_ne_16x16);
	btnDeploy.setSelectedIcon(IconTypes.unAssign_left_16x16);
	btnDeploy.setRolloverSelectedIcon(IconTypes.assignRight_ro_16x16);*/
	jpTitle.setOpaque(false);
	lblTitle.setText("  " + parent.getTitle());
	lblTitle.setToolTipText(parent.getTitle());
	btnDeploy.setIcon(IconTypes.ext_iframe_deploy_22x22);
	btnClose = new BasicButton();
	btnClose.setOpaque(false);
	btnClose.setIcon(IconTypes.ext_iframe_close_22x22);
	btnClose.setRolloverIcon(IconTypes.ext_iframe_close_ro_22x22);
	btnClose.setPressedIcon(IconTypes.ext_iframe_close_pr_22x22);
	btnClose.setVerticalAlignment(CloseButton.CENTER);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnClose.setToolTipText("Cerrar ventana");
	btnIconify = new BasicButton();
	btnIconify.setOpaque(false);
	btnIconify.setIcon(IconTypes.ext_iframe_iconify_22x22);
	btnIconify.setRolloverIcon(IconTypes.ext_iframe_iconify_ro_22x22);
	btnIconify.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAccept_actionPerformed(e);
				  }

			      }
	);
	btnIconify.setToolTipText("Iconificar ventana");
	jpTopRight.setOpaque(false);
	//lblTopRight.setPreferredSize(new Dimension((lblTopRight.getIcon().getIconWidth()<32?32:lblTopRight.getIcon().getIconWidth()), lblTopRight.getIcon().getIconHeight()));
	jpTopRight.setLayout(borderLayout4);
	boxLayout21 = new BoxLayout(jpTitle, BoxLayout.X_AXIS);
	jpTitle.setLayout(boxLayout21);
	lblTitle.setText(parent.getTitle());
	btnReload = new ReloadButton();
	btnReload.setOpaque(false);
	btnReload.setVerticalAlignment(AcceptButton.CENTER);
	btnReload.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReload_actionPerformed(e);
				 }

			     }
	);
	this.setLayout(borderLayout3);
	//this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	this.setPreferredSize(new Dimension(400, 27));
	this.setSize(new Dimension(400, 27));
	this.add(jpTopRight, BorderLayout.EAST);
	this.add(btnDeploy, BorderLayout.WEST);
	jpTitle.add(lblTitle, BorderLayout.CENTER);
	this.add(jpTitle, BorderLayout.CENTER);
	relocateTimer = new Timer(10, new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   if (isPressed && !parent.isMaximum()) {
				       try {
					   if (MouseInfo.getPointerInfo().getLocation() != null) {
					       //parent.setLocation(MouseInfo.getPointerInfo().getLocation());
					       location = parent.getLocation();
					       int x = location.x - pressed.x + MouseInfo.getPointerInfo().getLocation().x;
					       int y = location.y - pressed.y + MouseInfo.getPointerInfo().getLocation().y;
					       if (!((x >= 0 || Math.abs(x) <= parent.getWidth() - 20) && x <= (parent.getDesktop().getBounds().getMaxX() - parent.getDesktop().getBounds().getMinX()) - 20)) {
						   x = parent.getLocation().x;
					       }
					       if (!(y >= 0 && y <= (parent.getDesktop().getBounds().getMaxY() - parent.getDesktop().getBounds().getMinY()) - 20)) {
						   y = parent.getLocation().y;
					       }
					       parent.setLocation(x, y);
					       pressed.setLocation(MouseInfo.getPointerInfo().getLocation());
					   }
				       } catch (NullPointerException x) {
					   parent.setLocation(location);
				       }
				   }
			       }

			   }
	    );
	MouseAdapter locateMouseAdapter = new MouseAdapter() {

			   public void mousePressed(MouseEvent me) {
			       pressed = MouseInfo.getPointerInfo().getLocation();
			       isPressed = true;
			       parent.toFront();
			       relocateTimer.start();
			   }

			   public void mouseReleased(MouseEvent me) {
			       pressed = null;
			       isPressed = false;
			       relocateTimer.stop();
			   }

			   public void mouseClicked(MouseEvent me) {
			       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 1) {
				   parent.toFront();
				   showPopup(me);
			       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
				   if (parent.isMaximizable()) {
				       parent.setMaximum(!parent.isMaximum());
				   }
			       }
			   }

		       };
	this.addMouseListener(locateMouseAdapter);
	lblTitle.addMouseListener(locateMouseAdapter);
	btnDeploy.addMouseListener(new MouseAdapter() {

				public void mouseEntered(MouseEvent me) {
				    btnDeploy.setIcon(IconTypes.ext_iframe_iconify_22x22);
				    parent.showButtons();
				}

				public void mouseExited(MouseEvent me) {
				    btnDeploy.setIcon(IconTypes.ext_iframe_deploy_22x22);
				    parent.hideButtons();
				}

			    }
	);
	rightButtons.setOpaque(false);
	rightButtons.setLayout(borderLayout5);
	parent.addMouseListener(new MouseAdapter() {

			     public void mousePressed(MouseEvent mouseEvent) {
				 pressed = MouseInfo.getPointerInfo().getLocation();
				 isPressed = true;
				 relocateTimer.start();
			     }

			     public void mouseReleased(MouseEvent mouseEvent) {
				 pressed = null;
				 isPressed = false;
				 relocateTimer.stop();
			     }

			     public void mouseExited(MouseEvent me) {
				 parent.hideButtons();
			     }

			 }
	);
	jpTopRight.add(rightButtons, BorderLayout.CENTER);
	jpTopRight.add(lblTopRight, BorderLayout.EAST);
	rightButtons.add(btnReload);
	//rightButtons.add(btnIconify);
	//rightButtons.add(btnClose);
	setClosable(closable);
	setIconifiable(iconifiable);
	btnDeploy.setToolTipText("Mostrar panel de tareas");
    }

    private void showPopup(MouseEvent me) {
	BasicDesktop[] desktops = Environment.getDesktops();
	desktopMenuItems = new BasicRadioButton[desktops.length];
	menuTransfer.removeAll();
	for (int i = 0; i < desktops.length; i++) {
	    desktopMenuItems[i] = new BasicRadioButton(i, desktops[i].getName(), desktops[i].getName() == parent.getDesktop().getName() ? true : false);
	    desktopMenuItems[i].setForeground(Color.black);
	    menuTransfer.add(desktopMenuItems[i]);
	    desktopMenuItems[i].addActionListener(new ActionListener() {

					       public void actionPerformed(ActionEvent e) {
						   parent.transferToDesktop(Environment.getDesktops()[((BasicRadioButton)e.getSource()).getIdRadioButton()]);
					       }

					   }
	    );
	}
	popup.show(me.getComponent(), me.getX(), me.getY());
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parent.close(true);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (parent.isMaximizable() && (e.getModifiers() & ActionEvent.SHIFT_MASK) != 0) {
	    parent.setMaximum(!parent.isMaximum());
	} else {
	    if (parent.isIconifiable() || iconifiable) {
		parent.setIcon(true);
	    }
	}
    }

    private void btnReload_actionPerformed(ActionEvent e) {
	parent.reload();
    }

    public void setClosable(boolean _closable) {
	rightButtons.removeAll();
	rightButtons.setOpaque(false);
	rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.X_AXIS));
	rightButtons.add(btnReload);
	if (iconifiable) {
	    rightButtons.add(btnIconify);
	}
	if (_closable) {
	    rightButtons.add(btnClose);
	}
	closable = _closable;
	//this.add(rightButtons, BorderLayout.EAST);
    }

     public void setIconifiable(boolean _iconifiable) {
	 rightButtons.removeAll();
	 rightButtons.setOpaque(false);
	 rightButtons.setLayout(new BoxLayout(rightButtons, BoxLayout.X_AXIS));
	 rightButtons.add(btnReload);
	 if (_iconifiable) {
	     rightButtons.add(btnIconify);
	 }
	 if (closable) {
	     rightButtons.add(btnClose);
	 }
	 iconifiable = _iconifiable;
	 //this.add(rightButtons, BorderLayout.EAST);
     }

    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(0, 0, BasicConfig.INTERNALFRAME_NORTH_PANE_GRADIENT_START_COLOR, 0, h, BasicConfig.INTERNALFRAME_NORTH_PANE_GRADIENT_END_COLOR, true);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
    }

    protected void setSecurityLevel(int _level) {
	jpTitle.removeAll();
	for ( int i = 0; i < _level ; i++)  {
	    jpTitle.add(new BasicLabel(IconTypes.lock_16x16));
	}
	jpTitle.add(lblTitle);
    }
    
    public void setTitle(String _title) {
	 lblTitle.setText(_title);
    }

    public void addMenu(JMenu _menu) {
	popup.add(_menu);
    }

    public void resetMenu() {
	popup = new JPopupMenu();
	popup.add(menuIconify);
	popup.add(menuTransfer);
    }

}
