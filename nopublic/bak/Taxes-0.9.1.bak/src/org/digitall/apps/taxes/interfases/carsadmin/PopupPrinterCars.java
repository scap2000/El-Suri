package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;

public class PopupPrinterCars extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miAutomotoresVigentes  = new JMenuItem("Imprimir Padrón Automotor");
    private JMenuItem miAutomotoresBaja  = new JMenuItem("Imprimir Dominios con Baja");
    private JMenuItem miAutomotoresApartirDe   = new JMenuItem("Imprimir Dominios registrados a partir de...");
    private JMenuItem miExit = new JMenuItem("Salir");
    
    public PopupPrinterCars() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
	this.add(miAutomotoresVigentes);
	this.add(miAutomotoresBaja);
	this.add(miAutomotoresApartirDe);
	this.add(miExit);
	miAutomotoresVigentes.setForeground(Color.white);
	miAutomotoresBaja.setForeground(Color.white);
	miAutomotoresApartirDe.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miAutomotoresVigentes.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresVigentes();
		}
	    });
	miAutomotoresBaja.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresBaja();
		}
	    });
	miAutomotoresApartirDe.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAutomotoresApartirDe();
		}
	    });
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
    }
    
    private void clickAutomotoresVigentes(){
	getReporteAutomotores(true);
    }
    
    private void clickAutomotoresBaja(){
	getReporteAutomotores(false);
    }
    
    private void clickAutomotoresApartirDe() {
	String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "A partir del año ", "Informe", JOptionPane.QUESTION_MESSAGE);
	if (_title != null) {
	    try {
		int anio = Integer.parseInt(_title);
	        BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotorApartirDe.xml"));
	        report.addTableModel("taxes.xmlgetpadronautomotorapartirde", ""+ anio );
	        report.setProperty("title", "Dominios registrados a partir del año "+ anio);
	        report.doReport();	
	    } catch(NumberFormatException e) {
	        Advisor.messageBox("El año ingresado debe ser un número entero","Error");
	    }
	    
	}
    }
    
    private void getReporteAutomotores(boolean _vigente){
	BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotor.xml"));
	report.addTableModel("taxes.xmlGetPadronAutomotor", ""+ _vigente );
	if (_vigente) {
	    report.setProperty("title", "Padrón Automotor Vigente");
	} else {
	    report.setProperty("title", "Padrón Automotor con baja");
	}
	report.doReport();
    }
    
    private void clickExit(MouseEvent e){
	
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }
}

