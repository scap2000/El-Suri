package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Rectangle;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.BasicTabbedPane;

public class AsignacionTabsPanel extends BasicTabbedPane{
    
    private PanelReciboPreview pReciboPreview = new PanelReciboPreview();
    private PanelAsignedHaberes pAsignedHaberes = new PanelAsignedHaberes();
    private PanelAsignedDescuentos pAsignedDescuentos = new PanelAsignedDescuentos();
    private Asignador asignador = new Asignador(pReciboPreview,pAsignedHaberes,pAsignedDescuentos);

    public AsignacionTabsPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(360, 347));
	this.setPreferredSize(new Dimension(360, 347));
	pReciboPreview.setBorder(BorderPanel.getBorderPanel(""));
	pAsignedDescuentos.setBorder(BorderPanel.getBorderPanel(""));
	pAsignedHaberes.setBorder(BorderPanel.getBorderPanel(""));
	addTab("Recibo",pReciboPreview);
	addTab("Haberes",pAsignedHaberes);
	addTab("Descuentos",pAsignedDescuentos);
    }

    public PanelReciboPreview getPReciboPreview() {
	return pReciboPreview;
    }

    public PanelAsignedHaberes getPAsignedHaberes() {
	return pAsignedHaberes;
    }

    public PanelAsignedDescuentos getPAsignedDescuentos() {
	return pAsignedDescuentos;
    }
}
