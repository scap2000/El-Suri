/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * CoordinateCalculator.java
 *
 * */
package org.digitall.common.mapper;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import javax.swing.SwingConstants;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems.BadCoordinatesException;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;

public class CoordinateCalculator extends BasicPrimitivePanel {

    private BasicLabel jLabel6 = new BasicLabel();
    private BasicLabel jLabel5 = new BasicLabel();
    private BasicCombo gk_faja = new BasicCombo();
    private BasicLabel jLabel4 = new BasicLabel();
    private BasicLabel jLabel3 = new BasicLabel();
    private BasicLabel jLabel2 = new BasicLabel();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicCombo utm_zone = new BasicCombo();
    private BasicTextField jtgkX = new BasicTextField();
    private BasicTextField jtgkY = new BasicTextField();
    private BasicTextField jtutmNorth = new BasicTextField();
    private BasicTextField jtutmEast = new BasicTextField();
    private BasicLabel jLabel7 = new BasicLabel();
    private BasicLabel jLabel8 = new BasicLabel();
    private BasicTextField jtLongD = new BasicTextField();
    private BasicTextField jtLatD = new BasicTextField();
    private BasicPanel jpLatLong = new BasicPanel();
    private BasicPanel jpUTM = new BasicPanel();
    private BasicPanel jpGK = new BasicPanel();
    private BasicTextField jtLongM = new BasicTextField();
    private BasicTextField jtLatM = new BasicTextField();
    private BasicTextField jtLatS = new BasicTextField();
    private BasicTextField jtLongS = new BasicTextField();
    private BasicLabel jLabel9 = new BasicLabel();
    private BasicLabel jLabel10 = new BasicLabel();
    private BasicLabel jLabel11 = new BasicLabel();
    private BasicLabel jLabel12 = new BasicLabel();
    private BasicLabel jLabel13 = new BasicLabel();
    private BasicLabel jLabel14 = new BasicLabel();
    private BasicButton bLL2UTM = new BasicButton();
    private BasicButton bLL2GK = new BasicButton();
    private BasicButton bUTM2GK = new BasicButton();
    private BasicButton bUTM2LL = new BasicButton();
    private BasicButton bGK2LL = new BasicButton();
    private BasicButton bGK2UTM = new BasicButton();
    private BasicCheckBox autoStrip = new BasicCheckBox();

    public CoordinateCalculator() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(315, 380));
	jLabel6.setText("North");
	jLabel6.setBounds(new Rectangle(270, 50, 40, 15));
	jLabel6.setFont(new Font("Dialog", 1, 11));
	jLabel5.setText("East");
	jLabel5.setBounds(new Rectangle(270, 25, 40, 15));
	jLabel5.setFont(new Font("Dialog", 1, 11));
	gk_faja.setBounds(new Rectangle(45, 45, 50, 20));
	jLabel4.setText("Strip:");
	jLabel4.setBounds(new Rectangle(10, 48, 35, 15));
	jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel4.setFont(new Font("Dialog", 1, 11));
	jLabel3.setText("North");
	jLabel3.setBounds(new Rectangle(265, 55, 35, 15));
	jLabel3.setFont(new Font("Dialog", 1, 11));
	jLabel2.setText("East");
	jLabel2.setBounds(new Rectangle(265, 30, 35, 15));
	jLabel2.setFont(new Font("Dialog", 1, 11));
	jLabel1.setText("Zone:");
	jLabel1.setBounds(new Rectangle(40, 30, 35, 10));
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel1.setFont(new Font("Dialog", 1, 11));
	utm_zone.setBounds(new Rectangle(25, 50, 65, 20));
	jtgkX.setBounds(new Rectangle(100, 20, 165, 20));
	jtgkY.setBounds(new Rectangle(100, 45, 165, 20));
	jtutmNorth.setBounds(new Rectangle(95, 50, 165, 20));
	jtutmEast.setBounds(new Rectangle(95, 25, 165, 20));
	jLabel7.setText("Longitude");
	jLabel7.setBounds(new Rectangle(20, 50, 60, 20));
	jLabel7.setFont(new Font("Dialog", 1, 11));
	jLabel8.setText("Latitude");
	jLabel8.setBounds(new Rectangle(20, 25, 50, 20));
	jLabel8.setFont(new Font("Dialog", 1, 11));
	jtLongD.setBounds(new Rectangle(85, 50, 40, 20));
	jtLatD.setBounds(new Rectangle(85, 25, 40, 20));
	jpLatLong.setBounds(new Rectangle(0, 0, 315, 120));
	jpLatLong.setBorder(BorderPanel.getBorderPanel("Geodesic Coordinates"));
	jpLatLong.setLayout(null);
	jpUTM.setBounds(new Rectangle(0, 125, 315, 125));
	jpUTM.setLayout(null);
	jpUTM.setBorder(BorderPanel.getBorderPanel("UTM Coordinates"));
	jpGK.setBounds(new Rectangle(0, 255, 315, 125));
	jpGK.setLayout(null);
	jpGK.setBorder(BorderPanel.getBorderPanel("Gauss-Krugger Coordinates"));
	jtLongM.setBounds(new Rectangle(145, 50, 35, 20));
	jtLatM.setBounds(new Rectangle(145, 25, 35, 20));
	jtLatS.setBounds(new Rectangle(205, 25, 65, 20));
	jtLongS.setBounds(new Rectangle(205, 50, 65, 20));
	jLabel9.setText("d");
	jLabel9.setBounds(new Rectangle(130, 30, 15, 15));
	jLabel9.setFont(new Font("Dialog", 1, 11));
	jLabel10.setText("m");
	jLabel10.setBounds(new Rectangle(185, 30, 15, 15));
	jLabel10.setFont(new Font("Dialog", 1, 11));
	jLabel11.setText("s");
	jLabel11.setBounds(new Rectangle(275, 30, 15, 15));
	jLabel11.setFont(new Font("Dialog", 1, 11));
	jLabel12.setText("d");
	jLabel12.setBounds(new Rectangle(130, 55, 15, 15));
	jLabel12.setFont(new Font("Dialog", 1, 11));
	jLabel13.setText("s");
	jLabel13.setBounds(new Rectangle(275, 55, 15, 15));
	jLabel13.setFont(new Font("Dialog", 1, 11));
	jLabel14.setText("m");
	jLabel14.setBounds(new Rectangle(185, 55, 15, 15));
	jLabel14.setFont(new Font("Dialog", 1, 11));
	bLL2UTM.setText("TO UTM");
	bLL2UTM.setBounds(new Rectangle(80, 85, 77, 22));
	bLL2UTM.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bLL2UTM_actionPerformed(e);
		    }

		});
	bLL2GK.setText("TO GK");
	bLL2GK.setBounds(new Rectangle(165, 85, 77, 22));
	bLL2GK.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bLL2GK_actionPerformed(e);
		    }

		});
	bUTM2GK.setText("TO GK");
	bUTM2GK.setBounds(new Rectangle(170, 90, 75, 20));
	bUTM2GK.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUTM2GK_actionPerformed(e);
		    }

		});
	bUTM2LL.setText("TO LL");
	bUTM2LL.setBounds(new Rectangle(85, 90, 75, 20));
	bUTM2LL.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUTM2LL_actionPerformed(e);
		    }

		});
	bGK2LL.setText("TO LL");
	bGK2LL.setBounds(new Rectangle(170, 85, 75, 20));
	bGK2LL.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bGK2LL_actionPerformed(e);
		    }

		});
	bGK2UTM.setText("TO UTM");
	bGK2UTM.setBounds(new Rectangle(85, 85, 75, 20));
	bGK2UTM.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bGK2UTM_actionPerformed(e);
		    }

		});
	autoStrip.setText("Auto");
	autoStrip.setBounds(new Rectangle(15, 20, 80, 20));
	autoStrip.setHorizontalAlignment(SwingConstants.CENTER);
	autoStrip.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			autoStrip_actionPerformed(e);
		    }

		});
	jpLatLong.add(bLL2UTM, null);
	jpLatLong.add(jtLatD, null);
	jpLatLong.add(jtLongD, null);
	jpLatLong.add(jLabel8, null);
	jpLatLong.add(jLabel7, null);
	jpLatLong.add(jtLongM, null);
	jpLatLong.add(jtLatM, null);
	jpLatLong.add(jtLongS, null);
	jpLatLong.add(jtLatS, null);
	jpLatLong.add(jLabel9, null);
	jpLatLong.add(jLabel10, null);
	jpLatLong.add(jLabel11, null);
	jpLatLong.add(jLabel14, null);
	jpLatLong.add(jLabel12, null);
	jpLatLong.add(jLabel13, null);
	jpLatLong.add(bLL2GK, null);
	jpUTM.add(jtutmEast, null);
	jpUTM.add(jtutmNorth, null);
	jpUTM.add(utm_zone, null);
	jpUTM.add(jLabel1, null);
	jpUTM.add(jLabel2, null);
	jpUTM.add(jLabel3, null);
	jpUTM.add(bUTM2GK, null);
	jpUTM.add(bUTM2LL, null);
	jpGK.add(jtgkX, null);
	jpGK.add(jtgkY, null);
	jpGK.add(jLabel4, null);
	jpGK.add(gk_faja, null);
	jpGK.add(jLabel5, null);
	jpGK.add(jLabel6, null);
	jpGK.add(bGK2LL, null);
	jpGK.add(bGK2UTM, null);
	jpGK.add(autoStrip, null);
	this.add(jpGK);
	this.add(jpUTM, null);
	this.add(jpLatLong, null);
	for (int i = 1; i <= 60; i++) {
	    utm_zone.addItem(String.valueOf(i) + "N");
	    utm_zone.addItem(String.valueOf(i) + "S");
	}
	for (int i = 1; i <= 7; i++) {
	    gk_faja.addItem(String.valueOf(i));
	}
    }

    void fileExit_ActionPerformed(ActionEvent e) {
	System.exit(0);
    }

    private void bLL2UTM_actionPerformed(ActionEvent e) {
	double latD = Double.parseDouble(jtLatD.getText());
	double latM = Double.parseDouble(jtLatM.getText());
	double latS = Double.parseDouble(jtLatS.getText().replaceAll(",", "."));
	double longD = Double.parseDouble(jtLongD.getText());
	double longM = Double.parseDouble(jtLongM.getText());
	double longS = Double.parseDouble(jtLongS.getText().replaceAll(",", "."));
	LatLongCoord geod = new LatLongCoord(latD, latM, latS, longD, longM, longS);
	UTMCoord utm = CoordinateSystems.geo2utm(geod.getLatitude(), geod.getLongitude());
	jtutmEast.setText(String.valueOf(utm.getEasting()));
	jtutmNorth.setText(String.valueOf(utm.getNorthing()));
	String zone = String.valueOf(utm.getZone()) + ((utm.getBand() < 0) ? "S" : "N");
	utm_zone.setSelectedItem(zone);
    }

    private void bLL2GK_actionPerformed(ActionEvent e) {
	double latD = Double.parseDouble(jtLatD.getText());
	double latM = Double.parseDouble(jtLatM.getText());
	double latS = Double.parseDouble(jtLatS.getText().replaceAll(",", "."));
	double longD = Double.parseDouble(jtLongD.getText());
	double longM = Double.parseDouble(jtLongM.getText());
	double longS = Double.parseDouble(jtLongS.getText().replaceAll(",", "."));
	LatLongCoord geod = new LatLongCoord(latD, latM, latS, longD, longM, longS);
	GKCoord gk = CoordinateSystems.geo2gk(geod.getLatitude(), geod.getLongitude());
	jtgkX.setText(String.valueOf(gk.getX()));
	jtgkY.setText(String.valueOf(gk.getY()));
	gk_faja.setSelectedItem(String.valueOf(gk.getFaja()));
    }

    private void bUTM2LL_actionPerformed(ActionEvent e) {
	double utmeast = Double.parseDouble(jtutmEast.getText());
	double utmnorth = Double.parseDouble(jtutmNorth.getText());
	double utmzone = Double.parseDouble(utm_zone.getSelectedItem().toString().replaceAll("N", "").replaceAll("S", ""));
	LatLongCoord geod = CoordinateSystems.utm2geo(utmeast, utmnorth, utmzone);
	jtLatD.setText(String.valueOf((int)geod.getLatD()));
	jtLatM.setText(String.valueOf((int)geod.getLatM()));
	jtLatS.setText(decimalFormat(geod.getLatS(), 4));
	jtLongD.setText(String.valueOf((int)geod.getLongD()));
	jtLongM.setText(String.valueOf((int)geod.getLongM()));
	jtLongS.setText(decimalFormat(geod.getLongS(), 4));
    }

    private void bUTM2GK_actionPerformed(ActionEvent e) {
	double utmeast = Double.parseDouble(jtutmEast.getText());
	double utmnorth = Double.parseDouble(jtutmNorth.getText());
	double utmzone = Double.parseDouble(utm_zone.getSelectedItem().toString().replaceAll("N", "").replaceAll("S", ""));
	LatLongCoord geod = CoordinateSystems.utm2geo(utmeast, utmnorth, utmzone);
	GKCoord gk = CoordinateSystems.geo2gk(geod.getLatitude(), geod.getLongitude());
	jtgkX.setText(String.valueOf(gk.getX()));
	jtgkY.setText(String.valueOf(gk.getY()));
	gk_faja.setSelectedItem(String.valueOf(gk.getFaja()));
    }

    private void bGK2UTM_actionPerformed(ActionEvent e) {
	double gkx = Double.parseDouble(jtgkX.getText());
	double gky = Double.parseDouble(jtgkY.getText());
	try {
	    LatLongCoord geod = CoordinateSystems.gk2geo(gkx, gky, (autoStrip.isSelected() ? 0 : Integer.parseInt(gk_faja.getSelectedItem().toString())));
	    if (autoStrip.isSelected()) {
	        gk_faja.setSelectedItem(String.valueOf(CoordinateSystems.geo2gk(geod.getLatitude(), geod.getLongitude()).getFaja()));
	    }
	    UTMCoord utm = CoordinateSystems.geo2utm(geod.getLatitude(), geod.getLongitude());
	    jtutmEast.setText(String.valueOf(utm.getEasting()));
	    jtutmNorth.setText(String.valueOf(utm.getNorthing()));
	    String zone = String.valueOf(utm.getZone()) + ((utm.getBand() < 0) ? "S" : "N");
	    utm_zone.setSelectedItem(zone);
	} catch (BadCoordinatesException f) {
	    f.printStackTrace();
	}
    }

    private void bGK2LL_actionPerformed(ActionEvent e) {
	double gkx = Double.parseDouble(jtgkX.getText());
	double gky = Double.parseDouble(jtgkY.getText());
	try {
	    LatLongCoord geod = CoordinateSystems.gk2geo(gkx, gky, (autoStrip.isSelected() ? 0 : Integer.parseInt(gk_faja.getSelectedItem().toString())));
	    if (autoStrip.isSelected()) {
		gk_faja.setSelectedItem(String.valueOf(CoordinateSystems.geo2gk(geod.getLatitude(), geod.getLongitude()).getFaja()));
	    }
	    jtLatD.setText(String.valueOf((int)geod.getLatD()));
	    jtLatM.setText(String.valueOf((int)geod.getLatM()));
	    jtLatS.setText(decimalFormat(geod.getLatS(), 4));
	    jtLongD.setText(String.valueOf((int)geod.getLongD()));
	    jtLongM.setText(String.valueOf((int)geod.getLongM()));
	    jtLongS.setText(decimalFormat(geod.getLongS(), 4));
	} catch (BadCoordinatesException f) {
	    f.printStackTrace();
	}
    }

    public String decimalFormat(double _numero, int _decimales) {
	String df = "0.";
	for (int i = 0; i < _decimales; i++) {
	    df += "0";
	}
	return (new DecimalFormat(df)).format(_numero);
    }

    private void autoStrip_actionPerformed(ActionEvent e) {
	gk_faja.setEnabled(!autoStrip.isSelected());
    }

}
