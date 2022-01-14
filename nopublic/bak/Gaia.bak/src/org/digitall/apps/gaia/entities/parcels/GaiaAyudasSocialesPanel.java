package org.digitall.apps.gaia.entities.parcels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Rectangle2D;

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;

public class GaiaAyudasSocialesPanel extends GaiaConfigPanel {

    private int[] sizeColumnList = { 62, 87, 72, 58, 74, 76, 53, 55, 62, 61, 63, 56, 54, 66 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Ayudas Sociales", dataRow);
    private Vector headerList = new Vector();
    private PrintButton btnReport = new PrintButton();
    private JPanel jPanel1 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();

    public GaiaAyudasSocialesPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(760, 274));
	this.setPreferredSize(new Dimension(760, 512));
        listPanel.setPreferredSize(new Dimension(400, 200));
        jPanel1.setLayout(borderLayout1);
	jPanel1.add(listPanel, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.CENTER);
	setHeaderList();
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	//addButton(btnReport);
	btnReport.setToolTipText("Imprimir los resultados");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("Catastro");
	headerList.addElement("*");
	headerList.addElement("Ayuda Econ.");
	headerList.addElement("Combust.");
	headerList.addElement("Pasajes");
	headerList.addElement("Can. Fliar.");
	headerList.addElement("Útiles Esc.");
	headerList.addElement("Medic.");
	headerList.addElement("Bolson");
	headerList.addElement("Ladrillos");
	headerList.addElement("Ladr. Hº");
	headerList.addElement("Bloques");
	headerList.addElement("Chapas");
	headerList.addElement("Ripiosa");
	headerList.addElement("Cemento");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObjectSelected();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);
	String params = "-1";
	listPanel.setParams(GaiaEnvironment.getScheme() + ".getAllAyudasSociales", params, headerList);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	}
    }

    public int getTableRowsCount() {
	return listPanel.getTable().getRowCount();
    }

    private void btnReport_actionPerformed(ActionEvent e) {

    }

    public void setContentObject(Object _contentObject) {
	super.setContentObject(_contentObject);
	if (_contentObject instanceof ESRIPolygon) {
	    String params = "" + ((ESRIPolygon)_contentObject).getIdPolygon();
	    listPanel.refresh(params);
	} else {
	    //TODO cuando especifique una region o un punto
	}
    }

     public void setContentExtents(Rectangle2D.Double _contentExtents) {
	 super.setContentExtents(_contentExtents);
	 if (_contentExtents != null) {
	     String params = _contentExtents.getMinX() + ", " + _contentExtents.getMinY() + ", " + _contentExtents.getMaxX() + ", " + _contentExtents.getMaxY();;
	     listPanel.refresh(params);
	 } else {
	     //TODO cuando especifique una region o un punto
	 }
     }
}
