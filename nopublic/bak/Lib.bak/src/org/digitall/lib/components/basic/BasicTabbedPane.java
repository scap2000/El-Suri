package org.digitall.lib.components.basic;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class BasicTabbedPane extends JTabbedPane {

    public BasicTabbedPane() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTabbedPane(int _tabPlacement, int _tabLayoutPolicy) {
	super(_tabPlacement, _tabLayoutPolicy);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setUI(new BasicTabbedPaneUI() {

	   protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	       g.setColor(!isSelected ? getBackgroundAt(tabIndex).darker() : getBackgroundAt(tabIndex));
	       switch (tabPlacement) {
		   case LEFT :
		       g.fillRect(x + 1, y + 1, w - 1, h - 3);
		       //g.fillRect(x, y, w, h);
		       break;
		   case RIGHT :
		       g.fillRect(x, y + 1, w - 2, h - 3);
		       break;
		   case BOTTOM :
		       g.fillRect(x + 1, y, w - 3, h - 1);
		       break;
		   case TOP :
		   default :
		       g.fillRect(x + 1, y + 1, w - 3, h - 1);
	       }
	   }

	   protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	       //g.setColor(lightHighlight);  
	       g.setColor(isSelected ? getBackgroundAt(tabIndex).brighter() : getBackgroundAt(tabIndex).darker());
	       switch (tabPlacement) {
		   case LEFT :
		       // bottom-left highlight
		       g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2);
		       // left highlight
		       g.drawLine(x, y + 2, x, y + h - 3);
		       // top-left highlight
		       g.drawLine(x + 1, y + 1, x + 1, y + 1);
		       // top highlight
		       g.drawLine(x + 2, y, x + w - 1, y);
		       // bottom shadow
		       g.setColor(g.getColor().darker());
		       g.drawLine(x + 2, y + h - 2, x + w - 1, y + h - 2);
		       // bottom dark shadow
		       g.setColor(g.getColor().darker());
		       g.drawLine(x + 2, y + h - 1, x + w - 1, y + h - 1);
		       break;
		   case RIGHT :
		       g.drawLine(x, y, x + w - 3, y);
		       // top highlight
		       g.setColor(shadow);
		       g.drawLine(x, y + h - 2, x + w - 3, y + h - 2);
		       // bottom shadow
		       g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 3);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1);
		       // top-right dark shadow
		       g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2);
		       // bottom-right dark shadow
		       g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 3);
		       // right dark shadow
		       g.drawLine(x, y + h - 1, x + w - 3, y + h - 1);
		       // bottom dark shadow
		       break;
		   case BOTTOM :
		       g.drawLine(x, y, x, y + h - 3);
		       // left highlight
		       g.drawLine(x + 1, y + h - 2, x + 1, y + h - 2);
		       // bottom-left highlight
		       g.setColor(shadow);
		       g.drawLine(x + 2, y + h - 2, x + w - 3, y + h - 2);
		       // bottom shadow
		       g.drawLine(x + w - 2, y, x + w - 2, y + h - 3);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + 2, y + h - 1, x + w - 3, y + h - 1);
		       // bottom dark shadow
		       g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2);
		       // bottom-right dark shadow
		       g.drawLine(x + w - 1, y, x + w - 1, y + h - 3);
		       // right dark shadow
		       break;
		   case TOP :
		   default :
		       g.drawLine(x, y + 2, x, y + h - 1);
		       // left highlight
		       g.drawLine(x + 1, y + 1, x + 1, y + 1);
		       // top-left highlight
		       g.drawLine(x + 2, y, x + w - 3, y);
		       // top highlight              
		       g.setColor(shadow);
		       g.drawLine(x + w - 2, y + 2, x + w - 2, y + h - 1);
		       // right shadow
		       g.setColor(darkShadow);
		       g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 1);
		       // right dark-shadow
		       g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1);
		       // top-right shadow
	       }
	   }

       }
	);
	setAlignmentY(LEFT_ALIGNMENT);
	setFocusable(false);
	setOpaque(false);
	setFont(new Font("Lucida Sans", Font.PLAIN, 10));
    }

}
