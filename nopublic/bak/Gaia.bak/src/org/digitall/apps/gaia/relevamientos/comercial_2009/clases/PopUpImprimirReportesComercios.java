package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormComercios;

public class PopUpImprimirReportesComercios extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miRC  = new JMenuItem("Imprimir Censo Comercial 2009 con datos del Negocio");
    private JMenuItem miRCxCat  = new JMenuItem("Imprimir Censo Comercial 2009 con datos Catastrales");
    private JMenuItem miAuditoria   = new JMenuItem("Imprimir Auditoria Censo Comercial 2009");
    private JMenuItem miExit = new JMenuItem("Salir");
    

    public PopUpImprimirReportesComercios() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
	this.add(miRC);
	this.add(miRCxCat);
	this.add(miAuditoria);
	this.add(miExit);
	miRC.setForeground(Color.white);
	miRCxCat.setForeground(Color.white);
	miAuditoria.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miRC.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickRC();
		}
	    });
	miRCxCat.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickRCxCat();
		}
	    });
	miAuditoria.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    clickAuditoria();
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
    
    private void clickRCxCat(){
	BasicReport report = new BasicReport(FormComercios.class.getResource("xml/EncuestasComercialesDCReport.xml"));
	report.addTableModel("gea_salta.xmlGetEncuestasComercialesByDC", "");
	report.doReport();
    }

    private void clickRC() {
	BasicReport report = new BasicReport(FormComercios.class.getResource("xml/EncuestasComercialesReport.xml"));
	report.addTableModel("gea_salta.xmlGetEncuestasComerciales", "");
	report.doReport();
    }

    private void clickAuditoria(){
	BasicReport report = new BasicReport(FormComercios.class.getResource("xml/AuditoriaCensoComercialReport.xml"));
	report.addTableModel("gea_salta.xmlGetAuditoriaCensoComercial", "");
	report.doReport();
    }

    private void clickExit(MouseEvent e){
	
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }

}

