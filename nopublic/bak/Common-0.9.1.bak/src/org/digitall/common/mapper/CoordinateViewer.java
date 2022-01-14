package org.digitall.common.mapper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;

import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;

public class CoordinateViewer extends JInternalFrame {

    private CoordPanel coordPanel = new CoordPanel();

    public CoordinateViewer() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setTitle("Coordenadas");
	setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	setClosable(true);
	setSize(new Dimension(167, 269));
	getContentPane().add(coordPanel, BorderLayout.CENTER);
	setBorder(BorderFactory.createLineBorder(Color.black, 1));
	coordPanel.setBounds(new Rectangle(2, 2, 165, 246));
    }

    public void setGKCoord(GKCoord _gkCoord) {
	coordPanel.setGKCoord(_gkCoord);
    }

    public void setUTMCoord(UTMCoord _utmCoord) {
	coordPanel.setUTMCoord(_utmCoord);
    }

    public void setLLCoord(LatLongCoord _llCoord) {
	coordPanel.setLLCoord(_llCoord);
    }

}
