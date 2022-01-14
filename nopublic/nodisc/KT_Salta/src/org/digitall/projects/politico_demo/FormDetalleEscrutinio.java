package org.digitall.projects.politico_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.apps.gaia.classes.GaiaClassAdminyServ;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.sql.LibSQL;

public class FormDetalleEscrutinio extends GaiaPrimitiveForm {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel totalPanel = new BasicPanel();
    private int[] sizeColumnList = { 208, 66, 68, 71, 60, 46, 57, 57, 46, 64, 62 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(1000, sizeColumnList, "Resultados", dataRow);
    private Vector headerList = new Vector();
    private TFInput tfTotal1 = new TFInput(DataTypes.DOUBLE, "Total 1", false);
    private TFInput tfTotal2 = new TFInput(DataTypes.DOUBLE, "Total 2", false);
    private TFInput tfTotal3 = new TFInput(DataTypes.DOUBLE, "Total 3", false);
    private TFInput tfTotal4 = new TFInput(DataTypes.DOUBLE, "Total 4", false);
    private CBInput cbCircuito = new CBInput(0, "Circuito", false);
    private CBInput cbMesa = new CBInput(0, "Mesa", false);
    private CBInput cbPartido = new CBInput(0, "Partido", false);
    private FindButton btnBuscar = new FindButton();
    private ESRIPolygon polygon;

    public FormDetalleEscrutinio() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Escrutinio Salta-2005");
	listPanel.setSize(new Dimension(860, 350));
	listPanel.setBounds(new Rectangle(0, 0, 895, 280));
	centralPanel.setSize(new Dimension(860, 400));
	content.setLayout(borderLayout1);
	centralPanel.setLayout(borderLayout2);
	findPanel.setLayout(null);
	totalPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
	totalPanel.setPreferredSize(new Dimension(860, 45));
	btnBuscar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnBuscar_actionPerformed(e);
		    }

		});
	tfTotal1.setBounds(new Rectangle(80, 5, 130, 35));
	tfTotal2.setBounds(new Rectangle(245, 5, 130, 35));
	tfTotal4.setBounds(new Rectangle(670, 5, 130, 35));
	tfTotal3.setBounds(new Rectangle(415, 5, 130, 35));
	findPanel.add(btnBuscar, null);
	findPanel.add(cbPartido, null);
	findPanel.add(cbMesa, null);
	findPanel.add(cbCircuito, null);
	totalPanel.add(tfTotal3, null);
	totalPanel.add(tfTotal1, null);
	totalPanel.add(tfTotal2, null);
	totalPanel.add(tfTotal4, null);
	centralPanel.add(listPanel, BorderLayout.CENTER);
	centralPanel.add(totalPanel, BorderLayout.SOUTH);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
	this.add(content, null);
	setHeaderList();
	btnBuscar.setToolTipText("Buscar");
	btnBuscar.setBounds(new Rectangle(845, 30, 35, 25));
	findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
	totalPanel.setBorder(BorderPanel.getBorderPanel(""));
	tfTotal3.setEnabled(false);
	tfTotal2.setEnabled(false);
	tfTotal1.setEnabled(false);
	tfTotal4.setEnabled(false);
	cbCircuito.setBounds(new Rectangle(200, 20, 90, 35));
	cbMesa.setBounds(new Rectangle(325, 20, 100, 35));
	cbPartido.setBounds(new Rectangle(480, 20, 325, 35));
	cbCircuito.setGeneralItem(true);
	cbPartido.setGeneralItem(true);
	cbMesa.setGeneralItem(true);
	cbCircuito.autoSize();
	cbMesa.autoSize();
	cbPartido.autoSize();
	loadCombos();
	cbCircuito.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    refresh();
			}
		    }

		});
	cbMesa.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    refresh();
			}
		    }

		});
	cbPartido.getCombo().addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    refresh();
			}
		    }

		});
    }

    private void loadCombos() {
	cbCircuito.getCombo().loadJCombo("escrutinio_salta.getSectores", "");
	cbMesa.getCombo().loadJCombo("escrutinio_salta.getMesas", "");
	cbPartido.getCombo().loadJCombo("escrutinio_salta.getPartidos", "");
	cbCircuito.getCombo().setSelectedIndex(0);
	cbMesa.getCombo().setSelectedIndex(0);
	cbPartido.getCombo().setSelectedIndex(0);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Partido");
	headerList.addElement("Dip. Nac.");
	headerList.addElement("Sen. Prov.");
	headerList.addElement("Dip. Prov.");
	headerList.addElement("Concejal");
	headerList.addElement("Mesa");
	headerList.addElement("Seccion");
	headerList.addElement("*");
	headerList.addElement("Circuito");
	headerList.addElement("Sector");
	headerList.addElement("Total Vot.");
	headerList.addElement("% Vot.");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			//loadObjectSelected();
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    //getCuotas();
			} else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    checkRows();
			}
		    }

		});
	listPanel.setParams("escrutinio_salta.getDetalleElecciones", "'','',''", headerList);
    }

    private void checkRows() {
	calcTotalAmount();
    }

    private void calcTotalAmount() {
	/*
        double totalIntereses = 0;
        double totalValor = 0;
        double totalDescuento = 0;
        double amount = 0;
        Vector vector = (Vector)listPanel.getSelectedsVector();
        if (listPanel.getSelectedsVector().size() > 1)  {
            for (int i = 0 ;i <= listPanel.getSelectedsVector().size() -1 ; i++)  {
                Vector aux = (Vector)vector.elementAt(i);
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }
        } else {
            if (listPanel.getSelectedsVector().size() == 1) {
                Vector aux = (Vector)vector.elementAt(0);
                totalValor += Double.parseDouble(aux.elementAt(5).toString());
                totalIntereses += Double.parseDouble(aux.elementAt(7).toString());
                amount += Double.parseDouble(aux.elementAt(11).toString());
            }
        }
	
        tfTotal1.setValue(totalValor);
        tfTotal2.setValue(totalIntereses);
	BigDecimal dto;
	dto = new BigDecimal(""+ totalDescuento).setScale(2,BigDecimal.ROUND_DOWN);
	 tfTotal3.setValue(Double.parseDouble(""+ dto));
        tfTotal4.setValue( (totalValor + totalIntereses) - Double.parseDouble(""+ dto));
	*/
    }

    @Override
    public void setContentObject(Object _contentObject) {
	if (ESRIPolygon.class.isInstance(_contentObject)) {
	    polygon = (ESRIPolygon)_contentObject;
	    System.out.println(polygon.getIdPolygon());
	    ResultSet _result = LibSQL.exQuery("SELECT sector FROM escrutinio_salta.circuito_3_poligonos WHERE idsector = " + polygon.getIdPolygon());
	    if (_result != null) {
		try {
		    if (_result.next()) {
			cbCircuito.getCombo().setSelectedItem(_result.getString("sector"));
		    }
		}
		catch (SQLException e) {
		    //ignore
		}
	    }
	    //adminyServ.setIdAdminyServ(point.getIdPoint());
	    //setCircuito();
	    //tfCatastro.fetchCatastro(point);
	}
    }
    /*     @Override
     public Object getContentObject() {
	 return adminyServ;
     }
*/

    public void refresh() {
	String params = "'" + cbCircuito.getSelectedItem() + "','" + cbMesa.getSelectedItem() + "','" + cbPartido.getSelectedItem() + "'";
	listPanel.refresh(params);
	clearFields();
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void clearFields() {
	tfTotal3.setValue(0.0);
	tfTotal2.setValue(0.0);
	tfTotal1.setValue(0.0);
	tfTotal4.setValue(0.0);
    }

}
