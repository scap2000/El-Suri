package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.util.Vector;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class SeleccionPeriodoPanel extends BasicPrimitivePanel{
    
    private BasicPanel content = new BasicPanel();
    private CBInput cbMesesLiquidaciones = new CBInput(0,"Mes",false);
    private CBInput cbAniosLiquidaciones= new CBInput(0,"Año",false);
    private PrintButton btnPrintNota = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private Vector mesesVector = new Vector();
    private Vector aniosVector = new Vector();


    public SeleccionPeriodoPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(310, 97));
	content.setLayout(null);
	content.setBorder(BorderPanel.getBorderPanel(""));
	cbMesesLiquidaciones.setBounds(new Rectangle(25, 10, 135, 40));
        cargarVectorAnios();
	cargarAnios();
        cargarVectorMeses();
        cargarMeses();
	cbMesesLiquidaciones.autoSize();
	
	btnPrintNota.setToolTipText("Imprimir Liquidación");   
	btnClose.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}

				    }
	);
	cbAniosLiquidaciones.setBounds(new Rectangle(190, 10, 95, 40));
	btnPrintNota.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnPrintNota_actionPerformed(e);
					}

				    }
	);
	content.add(cbAniosLiquidaciones, null);
	content.add(cbMesesLiquidaciones, null);
	addButton(btnClose);
	addButton(btnPrintNota);
    	this.add(content, BorderLayout.CENTER);
	loadDefaultDate();
        cbAniosLiquidaciones.getCombo().addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    cargarMeses();
                }
            }
        });
    }
    
    private void cargarMeses(){
        cbMesesLiquidaciones.loadJCombo("sueldos.getMesesLiquidaciones","" + cbAniosLiquidaciones.getSelectedItem());
    }
    
    private void cargarAnios(){
        for (int i = 0; i < aniosVector.size(); i++) {
            cbAniosLiquidaciones.addItem(aniosVector.elementAt(i).toString());
        }
    }
    
    private void cargarVectorAnios(){
        aniosVector = LibSQL.getVector("sueldos.getAniosLiquidaciones","");
    }
    
    private void loadDefaultDate (){
        cbAniosLiquidaciones.getCombo().setSelectedIndex(cbAniosLiquidaciones.getCombo().getItemCount() - 1);
	cbMesesLiquidaciones.getCombo().setSelectedIndex(cbMesesLiquidaciones.getCombo().getItemCount() - 1);
    }
    
    private void cargarVectorMeses(){
        //cbMesesLiquidaciones.loadJCombo("sueldos.getMesesLiquidaciones","" + cbAniosLiquidaciones.getSelectedItem());
    }
    
    private void btnPrintNota_actionPerformed(ActionEvent e) {
	imprimir();
	this.getParentInternalFrame().dispose();
    }
    
    private void imprimir(){
	BasicReport report = new BasicReport(LiquidacionSueldosMain.class.getResource("xml/RecibosDeSueldo.xml"));
	int mesSelected = Integer.parseInt(cbMesesLiquidaciones.getSelectedValue().toString());
	int anio = Integer.parseInt(cbAniosLiquidaciones.getSelectedItem().toString());
	String params = ""+ anio +","+ mesSelected;
	report.addTableModel("sueldos.xmlGetLiquidacionMensual", params);
	report.setProperty("periodo",cbMesesLiquidaciones.getSelectedItem().toString()  + " " + cbAniosLiquidaciones.getSelectedItem().toString());
	report.doReport();
    }
    
    private int mesSeleccionado(){
	boolean encontrado = false;
	int i = 0;
	while ((!encontrado) && (i < mesesVector.size())){
	    if (mesesVector.elementAt(i).toString().toUpperCase().equals(cbMesesLiquidaciones.getSelectedItem().toString().toUpperCase())) {
		encontrado = true;
	    } else {
		i++;
	    }
	}
	
	if (!encontrado) {
	    i = -2;
	}
	
	return (i + 1);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
}


