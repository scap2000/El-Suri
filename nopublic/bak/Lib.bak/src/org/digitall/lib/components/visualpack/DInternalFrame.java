package org.digitall.lib.components.visualpack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.digitall.lib.components.basic.BasicContainerPanel;

public class DInternalFrame extends BasicContainerPanel {

    public DInternalFrame() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    //public void paint(Graphics _g) {
    //super.paintComponents(_g);
    //_g.drawRoundRect(0,0,100,100,2,5);
    //}

    private void jbInit() throws Exception {
	setLayout(null);
	setSize(200, 200);
	this.setDoubleBuffered(true);
	this.setOpaque(false);
	addMouseMotionListener(new MouseMotionListener() {

			    public void mouseDragged(MouseEvent mouseEvent) {
				setLocation(mouseEvent.getPoint());
			    }

			    public void mouseMoved(MouseEvent mouseEvent) {

			    }

			}
	);
    }

}
