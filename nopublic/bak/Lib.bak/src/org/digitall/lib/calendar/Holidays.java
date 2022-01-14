package org.digitall.lib.calendar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class Holidays extends BasicDialog {

    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel etiqueta = new BasicLabel();
    private AcceptButton banio = new AcceptButton();
    private AcceptButton bmes = new AcceptButton();
    private CloseButton bcancelar = new CloseButton();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicLabel jLabel3 = new BasicLabel();
    private String desde, hasta, fecha;
    private JProgressBar pbar = new JProgressBar(0, 100);
    private int CantDia = 0, Cant = 0;
    private Cursor wait = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor def = new Cursor(Cursor.DEFAULT_CURSOR);

    public Holidays(String Fecha) {
	try {
	    desde = Fecha;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(502, 218));
	this.getContentPane().setLayout(null);
	this.setTitle("Cargar Sabados y Domingos");
	jLabel1.setText("Presione el boton correspondiente");
	jLabel1.setBounds(new Rectangle(5, 10, 450, 15));
	jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel1.setVerticalAlignment(SwingConstants.TOP);
	jLabel1.setVerticalTextPosition(SwingConstants.TOP);
	jLabel1.setFont(new Font("Dialog", 1, 13));
	jLabel1.setForeground(Color.blue);
	etiqueta.setText("Mes: ");
	etiqueta.setBounds(new Rectangle(18, 10, 385, 15));
	banio.setText("AÃ±o en Curso");
	banio.setBounds(new Rectangle(15, 155, 130, 25));
	banio.setMnemonic('a');
	banio.setMargin(new Insets(2, 5, 2, 14));
	banio.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			banio_actionPerformed(e);
		    }

		});
	bmes.setText("Mes Seleccionado");
	bmes.setBounds(new Rectangle(155, 155, 154, 25));
	bmes.setMnemonic('m');
	bmes.setMargin(new Insets(2, 5, 2, 14));
	bmes.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bmes_actionPerformed(e);
		    }

		});
	bcancelar.setText("Cancelar");
	bcancelar.setBounds(new Rectangle(377, 155, 98, 25));
	bcancelar.setMnemonic('c');
	bcancelar.setMargin(new Insets(2, 5, 2, 14));
	bcancelar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcancelar_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(15, 80, 460, 50));
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel1.setLayout(null);
	jPanel2.setBounds(new Rectangle(15, 15, 460, 50));
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel2.setLayout(null);
	jLabel3.setText("segun como desee cargar los Sabados y Domingos");
	jLabel3.setBounds(new Rectangle(10, 25, 445, 16));
	jLabel3.setHorizontalTextPosition(SwingConstants.LEFT);
	jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel3.setVerticalAlignment(SwingConstants.TOP);
	jLabel3.setVerticalTextPosition(SwingConstants.TOP);
	jLabel3.setFont(new Font("Dialog", 1, 13));
	jLabel3.setForeground(Color.blue);
	pbar.setBounds(new Rectangle(20, 30, 430, 10));
	jPanel2.add(jLabel1, null);
	jPanel2.add(jLabel3, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(bcancelar, null);
	this.getContentPane().add(bmes, null);
	this.getContentPane().add(banio, null);
	this.getContentPane().add(jPanel1, null);
	jPanel1.add(etiqueta, null);
	jPanel1.add(pbar, null);
	//pbar = null;
	//  pbar.setValue(1);
	pbar.setStringPainted(true);
    }

    public static String ObtieneCantDia(String FechaDesde, String FechaHasta) throws SQLException {
	return LibSQL.getCampo("Select date '" + FechaHasta + "' - date '" + FechaDesde + "'");
    }

    private void Cargar(String FechaDesde, String FechaHasta, Component c) {
	try {
	    String dia = "0";
	    String mes = "", mesactual = "";
	    CantDia = 1;
	    int TotalDia = Integer.parseInt(ObtieneCantDia(FechaDesde, FechaHasta)) + 1;
	    pbar.setMinimum(0);
	    pbar.setMaximum(TotalDia);
	    c.setCursor(wait);
	    this.setCursor(wait);
	    while (CantDia != TotalDia) {
		pbar.setValue(CantDia);
		pbar.repaint();
		mesactual = Environment.currentMonth;
		if (!mes.equals(mesactual)) {
		    etiqueta.setText("Mes: " + mesactual);
		    etiqueta.updateUI();
		    mes = mesactual;
		}
		dia = LibSQL.getCampo("SELECT EXTRACT(DOW FROM TIMESTAMP '" + FechaDesde + "')");
		//System.out.println(dia);
		if (dia.equals("6") | dia.equals("0")) {
		    if (dia.equals("6")) {
			LibSQL.exActualizar('a', "Insert into calendario values('" + FechaDesde + "','Sabado','')");
		    } else {
			LibSQL.exActualizar('a', "Insert into calendario values('" + FechaDesde + "','Domingo','')");
		    }
		}
		FechaDesde = LibSQL.getCampo("Select date '" + FechaDesde + "' + Interval '1 day' as string");
		SwingUtilities.invokeLater(new Thread() {

			    public void run() {
				pbar.setValue(CantDia);
				pbar.repaint();
				pbar.updateUI();
			    }

			});
		CantDia += 1;
	    }
	    pbar.setValue(CantDia);
	    pbar.repaint();
	    this.setCursor(def);
	    c.setCursor(def);
	    //this.dispose();
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    private void banio_actionPerformed(ActionEvent e) {
	try {
	    desde = LibSQL.getCampo("SELECT EXTRACT(YEAR FROM TIMESTAMP '" + desde + "')") + "-01-01";
	    fecha = desde;
	    hasta = Proced.SumResFechaHora(fecha, true, "+", "12", "month", false);
	    Cargar(desde, hasta, this);
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    private void bmes_actionPerformed(ActionEvent e) {
	try {
	    Cargar(desde, Proced.setFormatDate(Proced.SumResFechaHora(desde, true, "+", "1", "month", false), false), this);
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

}
