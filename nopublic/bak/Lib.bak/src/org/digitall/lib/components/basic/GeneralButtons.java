package org.digitall.lib.components.basic;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.digitall.lib.environment.Environment;

public class GeneralButtons extends BasicInternalFrame implements MouseListener {

    private BasicPanel jpButtons;
    private ExtendedInternalFrame parent;
    private Timer timerShow;
    private Timer timerHide;
    private int period = 10;
    private int border = 5;
    private boolean showing = false;
    private boolean hiding = false;
    private int location = 0;
    private long prevSystemTime = System.currentTimeMillis();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private Vector buttons = new Vector();

    public GeneralButtons(ExtendedInternalFrame _parent) {
	super();
	parent = _parent;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setContentPane(new GeneralButtonsContainer());
	((BasicInternalFrameUI)getUI()).setNorthPane(null);
	setBorder(BorderFactory.createLineBorder(BasicConfig.GENERALBUTTONS_BORDER_COLOR));
	jpButtons = new BasicPanel();
	jpButtons.setOpaque(false);
	this.getContentPane().setLayout(gridBagLayout1);
	jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.Y_AXIS));
	//jpButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
	jpButtons.setBorder(BorderFactory.createLineBorder(BasicConfig.GENERALBUTTONS_BORDER_COLOR));
	//new Color(62,91,104)));
	this.getContentPane().add(jpButtons, new GridBagConstraints(0, 0, 0, 0, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(18, 16, 16, 0), getWidth(), getHeight()));
	//getContentPane().add(jpButtons, BorderLayout.CENTER);
	addMouseListener(this);
	jpButtons.addMouseListener(this);
	timerShow = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       if (!hiding) {
				   //setLayer(parent.getLayer() - 1);
				   setVisible(true);
				   show();
				   //setLayer(parent.getLayer());
				   toFront();
				   parent.toFront();
				   location = getLocation().x;
				   if (location - Environment.animationStep > parent.getLocation().x - getBounds().getWidth()) {
				       if (getLocation().x - Environment.animationStep >= 0) {
					   setLocation(getLocation().x - Environment.animationStep, parent.getLocation().y);
				       } else {
					   parent.moveTo(parent.getLocation().x + Environment.animationStep, parent.getLocation().y);
				       }
				       location -= Environment.animationStep;
				       showing = true;
				       //System.out.println("show running");
				   } else {
				       timerShow.stop();
				       timerHide.stop();
				       setLocation(parent.getLocation().x - (int)getBounds().getWidth() + 2, parent.getLocation().y);
				       location = getLocation().x;
				       showing = false;
				       prevSystemTime = System.currentTimeMillis();
				       //System.out.println("show terminated");
				   }
			       }
			   }

		       }
	    );
	timerHide = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       //setLayer(parent.getLayer() - 1);
			       if (!showing) {
				   if (System.currentTimeMillis() - prevSystemTime > 1000) {
				       if (location + Environment.animationStep < parent.getLocation().x) {
					   setLocation(getLocation().x + Environment.animationStep, parent.getLocation().y);
					   location += Environment.animationStep;
					   hiding = true;
					   //System.out.println("hide running");
				       } else {
					   timerHide.stop();
					   timerShow.stop();
					   setLocation(parent.getLocation().x, parent.getLocation().y);
					   location = getLocation().x;
					   setVisible(false);
					   close();
					   hiding = false;
					   //System.out.println("hide terminated");
				       }
				   }
			       }
			   }

		       }
	    );
    }

    public void relocate() {
	close();
	setLocation(parent.getLocation().x, parent.getLocation().y);
	location = getLocation().x;
    }

    public void showButtons() {
	if (buttons.size() > 0) {
	    timerHide.stop();
	    hiding = false;
	    if (!showing) {
		timerShow.start();
	    }
	}
    }

    public void hideButtons() {
	timerShow.stop();
	showing = false;
	timerHide.start();
    }

    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void mousePressed(MouseEvent mouseEvent) {

    }

    public void mouseReleased(MouseEvent mouseEvent) {

    }

    public void mouseEntered(MouseEvent mouseEvent) {
	prevSystemTime = System.currentTimeMillis();
	showButtons();
    }

    public void mouseExited(MouseEvent mouseEvent) {
	hideButtons();
    }

    public void addButton(BasicButton _button) {
	_button.setVerticalTextPosition(SwingConstants.BOTTOM);
	_button.setHorizontalTextPosition(SwingConstants.CENTER);
	_button.setAlignmentX(Component.CENTER_ALIGNMENT);
	_button.addMouseListener(this);
	_button.setShowText(true);
	_button.setHorizontalAlignment(SwingConstants.CENTER);
	jpButtons.add(_button);
	buttons.add(_button);
    }

    public BasicButton getButton(int _idButton) {
	BasicButton _button = new BasicButton();
	int i = 0;
	boolean found = false;
	while (i < buttons.size() && !found) {
	    _button = (BasicButton)buttons.elementAt(i);
	    found = _button.getIdButton() == _idButton;
	    i++;
	}
	return (found ? _button : null);
    }

}
