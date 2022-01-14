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

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class FormDetalleEscrutinioPorMesa extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel centralPanel = new BasicPanel();
    private BasicPanel totalPanel = new BasicPanel();

    private int[] sizeColumnList = { 64, 64, 170, 257, 210, 75 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(1000, sizeColumnList, "Resultados ganadores por Mesa", dataRow);
    private Vector headerList = new Vector();

    private TFInput tfTotal1 = new TFInput(DataTypes.DOUBLE,"Total 1",false);
    private TFInput tfTotal2 = new TFInput(DataTypes.DOUBLE,"Total 2",false);
    private TFInput tfTotal3 = new TFInput(DataTypes.DOUBLE,"Total 3",false);
    private TFInput tfTotal4 = new TFInput(DataTypes.DOUBLE,"Total 4",false);
    
    private CBInput cbMesa = new CBInput(0,"Mesa",false);
    private CBInput cbPartido = new CBInput(0,"Partido",false);
    private CBInput cbCargo = new CBInput(0,"Cargo",false);

    private FindButton btnBuscar = new FindButton();

    public FormDetalleEscrutinioPorMesa() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 470));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Escrutinio Salta-2005 - Por Circuito");
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

			    }
	);
        tfTotal1.setBounds(new Rectangle(80, 5, 130, 35));
        tfTotal2.setBounds(new Rectangle(245, 5, 130, 35));
        tfTotal4.setBounds(new Rectangle(670, 5, 130, 35));
        tfTotal3.setBounds(new Rectangle(415, 5, 130, 35));
	findPanel.add(cbCargo, null);
	findPanel.add(btnBuscar, null);
	findPanel.add(cbPartido, null);
	findPanel.add(cbMesa, null);
	totalPanel.add(tfTotal3, null);
	totalPanel.add(tfTotal1, null);
	totalPanel.add(tfTotal2, null);
	totalPanel.add(tfTotal4, null);
	centralPanel.add(listPanel,BorderLayout.CENTER);
	centralPanel.add(totalPanel,BorderLayout.SOUTH);
	content.add(findPanel, BorderLayout.NORTH);
	content.add(centralPanel, BorderLayout.CENTER);
	this.add(content, null);
        setHeaderList();
	btnBuscar.setToolTipText("Buscar");
	btnBuscar.setBounds(new Rectangle(855, 30, 35, 25));
	cbCargo.setBounds(new Rectangle(290, 20, 145, 35));
	findPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
	totalPanel.setBorder(BorderPanel.getBorderPanel(""));
	tfTotal3.setEnabled(false);
        tfTotal2.setEnabled(false);
        tfTotal1.setEnabled(false);
        tfTotal4.setEnabled(false);
	cbMesa.setBounds(new Rectangle(145, 20, 80, 35));
	cbPartido.setBounds(new Rectangle(490, 20, 260, 35));
	cbMesa.autoSize();
	cbPartido.autoSize();
	cbCargo.autoSize();
	cbMesa.setGeneralItem(true);
	cbPartido.setGeneralItem(true);
	loadCombos();
	cbMesa.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			refresh();      
		}
	    }
	});
	cbPartido.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			refresh();      
		}
	    }
	});
	
	cbCargo.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			refresh();      
		}
	    }
	});

    }

    private void loadCombos() {
	//cbCircuito.getCombo().loadJCombo("escrutinio_salta.getCircuitos", "");
	cbMesa.getCombo().loadJCombo("escrutinio_salta.getMesas", "");
	cbPartido.getCombo().loadJCombo("escrutinio_salta.getPartidos", "");
	cbCargo.getCombo().addItem("DIPUTADO NAC.", 0);
	cbCargo.getCombo().addItem("SENADOR PROV.", 1);
	cbCargo.getCombo().addItem("DIPUTADO PROV.", 2);
	cbCargo.getCombo().addItem("CONCEJAL", 3);
	cbCargo.getCombo().addItem("TODOS", 4);
	cbMesa.getCombo().setSelectedIndex(0);
	cbPartido.getCombo().setSelectedIndex(0);
	cbCargo.getCombo().setSelectedItem("TODOS");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
        headerList.addElement("Sector");
	headerList.addElement("Mesa");
	headerList.addElement("Cargo");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("Ganador");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Segundo");
        headerList.addElement("Porcentaje");

        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {
                                                     //loadObjectSelected();
                                                     
                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                         //getCuotas();
                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        checkRows();
                                                     }
                                                 }

                                             }
        );
        listPanel.setParams("escrutinio_salta.getDetalleEleccionesPorMesa", "4,'',''", headerList);
    }

    private void checkRows(){
        calcTotalAmount();
    }

    private void calcTotalAmount(){
	
    }

    public void refresh() {
        String params = ""+ cbCargo.getSelectedIndex() +",'"+ cbMesa.getSelectedItem() +"','"+ cbPartido.getSelectedItem() +"'" ;
	listPanel.refresh(params);
        clearFields();
    }
    
    private void btnBuscar_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    private void clearFields() {
        tfTotal3.setValue(0.0);
        tfTotal2.setValue(0.0);
        tfTotal1.setValue(0.0);
        tfTotal4.setValue(0.0);
    }
    
}

