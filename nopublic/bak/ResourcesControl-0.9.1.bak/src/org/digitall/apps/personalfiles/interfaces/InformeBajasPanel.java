package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.environment.Environment;

public class InformeBajasPanel extends BasicPrimitivePanel{
    
    private BasicPanel content = new BasicPanel();
    private CBInput meses = new CBInput(0,"Mes",false);
    private CBInput cbAnios = new CBInput(0,"Año",false);
    private PrintButton btnPrintNota = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private Vector mesesVector = new Vector();


    public InformeBajasPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(310, 139));
	content.setLayout(null);
	content.setBorder(BorderPanel.getBorderPanel(""));
	meses.setBounds(new Rectangle(25, 10, 265, 35));
	cargarVectorMeses();
	cargarMeses();
	cargarAnios();
	meses.autoSize();
	
	btnPrintNota.setToolTipText("Imprimir Informe de bajas");   
	btnClose.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}

				    }
	);
	cbAnios.setBounds(new Rectangle(25, 50, 265, 35));
	btnPrintNota.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnPrintNota_actionPerformed(e);
					}

				    }
	);
	content.add(cbAnios, null);
	content.add(meses, null);
	addButton(btnClose);
	addButton(btnPrintNota);
    	this.add(content, BorderLayout.CENTER);
	cbAnios.autoSize();
    }
    
    private void cargarMeses(){
	for(int i = 0; i < mesesVector.size(); i++){
	    meses.addItem(mesesVector.elementAt(i).toString());
	}
    }
    
    private void cargarAnios(){
	for(int i = 1; i < 120; i++){
	    cbAnios.addItem(""+(2008 + i));
	}
    }
    
    private void cargarVectorMeses(){
	mesesVector.add("Enero");
	mesesVector.add("Febrero");
	mesesVector.add("Marzo");
	mesesVector.add("Abril");
	mesesVector.add("Mayo");
	mesesVector.add("Junio");
	mesesVector.add("Julio");
	mesesVector.add("Agosto");
	mesesVector.add("Septiembre");
	mesesVector.add("Octubre");
	mesesVector.add("Noviembre");
	mesesVector.add("Diciembre");
    }
    private void btnPrintNota_actionPerformed(ActionEvent e) {
	imprimir();
    }
    
    private void imprimir(){
	BasicReport report = new BasicReport(DependenciaTree.class.getResource("xml/InformeBajas.xml"));
	report.setProperty("anio",cbAnios.getSelectedItem().toString());
	report.setProperty("mes",meses.getSelectedItem().toString());
	int mesSelected = meses.getSelectedIndex() + 1;
	int anio = Integer.parseInt(cbAnios.getSelectedItem().toString());
	String params = ""+mesSelected + ","+anio;
	report.addTableModel("resourcescontrol.xmlgetrecursosbaja",params);
	report.doReport();
    }
    
    
    private String getNameIntendente(){
	String resultado = "";
	Persona persona = new Persona();
	persona.setIdPerson(290);
	persona.retrieveData();
	resultado = "" + persona.getFirstName() + " " + persona.getLastName();
	System.out.println("intendente: "+resultado);
	return(resultado);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
}
