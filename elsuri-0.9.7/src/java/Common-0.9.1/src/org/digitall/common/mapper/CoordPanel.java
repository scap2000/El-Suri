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
 * CoordPanel.java
 *
 * */
package org.digitall.common.mapper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems.BadCoordinatesException;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;

public class CoordPanel extends JPanel {

    private GKCoord gkCoord = new GKCoord();
    private UTMCoord utmCoord = new UTMCoord();
    private LatLongCoord llCoord = new LatLongCoord();
    private BasicPanel jpGK = new BasicPanel();
    private BasicLabel lblGKTitle = new BasicLabel();
    private BasicLabel lblGKX = new BasicLabel();
    private BasicLabel lblGKY = new BasicLabel();
    private BasicLabel lblGKStrip = new BasicLabel();
    private BasicLabel lblGKXCoord = new BasicLabel();
    private BasicLabel lblGKYCoord = new BasicLabel();
    private BasicLabel lblGKStripCoord = new BasicLabel();
    private BasicPanel jpUTM = new BasicPanel();
    private BasicLabel lblUTMTitle = new BasicLabel();
    private BasicLabel lblUTME = new BasicLabel();
    private BasicLabel lblUTMN = new BasicLabel();
    private BasicLabel lblUTMZone = new BasicLabel();
    private BasicLabel lblUTMECoord = new BasicLabel();
    private BasicLabel lblUTMNCoord = new BasicLabel();
    private BasicLabel lblUTMZoneCoord = new BasicLabel();
    private BasicPanel jpLL = new BasicPanel();
    private BasicLabel lblLLTitle = new BasicLabel();
    private BasicLabel lblLat = new BasicLabel();
    private BasicLabel lblLon = new BasicLabel();
    private BasicLabel lblLatCoord = new BasicLabel();
    private BasicLabel lblLonCoord = new BasicLabel();

    public CoordPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(165, 246));
	jpGK.setBounds(new Rectangle(0, 0, 165, 86));
	jpGK.add(lblGKStripCoord, null);
	jpGK.add(lblGKYCoord, null);
	jpGK.add(lblGKXCoord, null);
	jpGK.add(lblGKStrip, null);
	jpGK.add(lblGKY, null);
	jpGK.add(lblGKX, null);
	jpGK.add(lblGKTitle, null);
	jpGK.setBackground(new Color(232, 229, 226));
	jpGK.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpGK.setLayout(null);
	lblGKTitle.setText(" Gauss-Krüger ");
	lblGKTitle.setBounds(new Rectangle(0, 0, 165, 25));
	lblGKTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblGKTitle.setForeground(Color.white);
	lblGKTitle.setBackground(Color.black);
	lblGKTitle.setOpaque(true);
	lblGKTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblGKX.setText("X: ");
	lblGKX.setBounds(new Rectangle(1, 25, 55, 20));
	lblGKX.setHorizontalAlignment(BasicLabel.RIGHT);
	lblGKX.setForeground(Color.white);
	lblGKX.setBackground(new Color(222, 130, 11));
	lblGKX.setOpaque(true);
	lblGKX.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKY.setText("Y: ");
	lblGKY.setBounds(new Rectangle(1, 45, 55, 20));
	lblGKY.setHorizontalAlignment(BasicLabel.RIGHT);
	lblGKY.setForeground(Color.white);
	lblGKY.setBackground(new Color(222, 130, 11));
	lblGKY.setOpaque(true);
	lblGKY.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKStrip.setText("Strip: ");
	lblGKStrip.setBounds(new Rectangle(1, 65, 55, 20));
	lblGKStrip.setHorizontalAlignment(BasicLabel.RIGHT);
	lblGKStrip.setForeground(Color.white);
	lblGKStrip.setBackground(new Color(222, 130, 11));
	lblGKStrip.setOpaque(true);
	lblGKStrip.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKXCoord.setText("");
	lblGKXCoord.setBounds(new Rectangle(60, 25, 100, 20));
	lblGKXCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblGKXCoord.setForeground(Color.black);
	lblGKXCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKYCoord.setText("");
	lblGKYCoord.setBounds(new Rectangle(60, 45, 100, 20));
	lblGKYCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblGKYCoord.setForeground(Color.black);
	lblGKYCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	lblGKStripCoord.setText("");
	lblGKStripCoord.setBounds(new Rectangle(60, 65, 100, 20));
	lblGKStripCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblGKStripCoord.setForeground(Color.black);
	lblGKStripCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	jpUTM.setBounds(new Rectangle(0, 90, 165, 86));
	jpUTM.add(lblUTMZoneCoord, null);
	jpUTM.add(lblUTMNCoord, null);
	jpUTM.add(lblUTMECoord, null);
	jpUTM.add(lblUTMZone, null);
	jpUTM.add(lblUTMN, null);
	jpUTM.add(lblUTME, null);
	jpUTM.add(lblUTMTitle, null);
	jpUTM.setBackground(new Color(232, 229, 226));
	jpUTM.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpUTM.setLayout(null);
	lblUTMTitle.setText(" UTM ");
	lblUTMTitle.setBounds(new Rectangle(0, 0, 165, 25));
	lblUTMTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblUTMTitle.setForeground(Color.white);
	lblUTMTitle.setBackground(Color.red);
	lblUTMTitle.setOpaque(true);
	lblUTMTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblUTME.setText("Easting: ");
	lblUTME.setBounds(new Rectangle(1, 25, 55, 20));
	lblUTME.setHorizontalAlignment(BasicLabel.RIGHT);
	lblUTME.setForeground(Color.white);
	lblUTME.setBackground(new Color(176, 102, 29));
	lblUTME.setOpaque(true);
	lblUTME.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMN.setText("Northing: ");
	lblUTMN.setBounds(new Rectangle(1, 45, 55, 20));
	lblUTMN.setHorizontalAlignment(BasicLabel.RIGHT);
	lblUTMN.setForeground(Color.white);
	lblUTMN.setBackground(new Color(176, 102, 29));
	lblUTMN.setOpaque(true);
	lblUTMN.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMZone.setText("Zone: ");
	lblUTMZone.setBounds(new Rectangle(1, 65, 55, 20));
	lblUTMZone.setHorizontalAlignment(BasicLabel.RIGHT);
	lblUTMZone.setForeground(Color.white);
	lblUTMZone.setBackground(new Color(176, 102, 29));
	lblUTMZone.setOpaque(true);
	lblUTMZone.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMECoord.setText("");
	lblUTMECoord.setBounds(new Rectangle(65, 25, 95, 20));
	lblUTMECoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblUTMECoord.setForeground(Color.black);
	lblUTMECoord.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMNCoord.setText("");
	lblUTMNCoord.setBounds(new Rectangle(65, 45, 95, 20));
	lblUTMNCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblUTMNCoord.setForeground(Color.black);
	lblUTMNCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	lblUTMZoneCoord.setText("");
	lblUTMZoneCoord.setBounds(new Rectangle(65, 65, 95, 20));
	lblUTMZoneCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblUTMZoneCoord.setForeground(Color.black);
	lblUTMZoneCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	jpLL.setBounds(new Rectangle(0, 180, 165, 66));
	jpLL.add(lblLonCoord, null);
	jpLL.add(lblLatCoord, null);
	jpLL.add(lblLon, null);
	jpLL.add(lblLat, null);
	jpLL.add(lblLLTitle, null);
	jpLL.setBackground(new Color(232, 229, 226));
	jpLL.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpLL.setLayout(null);
	lblLLTitle.setText(" Lat/Lon ");
	lblLLTitle.setBounds(new Rectangle(0, 0, 165, 25));
	lblLLTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblLLTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblLLTitle.setForeground(Color.white);
	lblLLTitle.setBackground(new Color(0, 102, 0));
	lblLLTitle.setOpaque(true);
	lblLLTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblLat.setText("Lat: ");
	lblLat.setBounds(new Rectangle(1, 25, 40, 20));
	lblLat.setHorizontalAlignment(BasicLabel.RIGHT);
	lblLat.setForeground(Color.white);
	lblLat.setBackground(new Color(68, 69, 67));
	lblLat.setOpaque(true);
	lblLat.setFont(new Font("Dialog", Font.BOLD, 10));
	lblLon.setText("Lon: ");
	lblLon.setBounds(new Rectangle(1, 45, 40, 20));
	lblLon.setHorizontalAlignment(BasicLabel.RIGHT);
	lblLon.setForeground(Color.white);
	lblLon.setBackground(new Color(68, 69, 67));
	lblLon.setOpaque(true);
	lblLon.setFont(new Font("Dialog", Font.BOLD, 10));
	lblLatCoord.setText("");
	lblLatCoord.setBounds(new Rectangle(50, 25, 110, 20));
	lblLatCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblLatCoord.setForeground(Color.black);
	lblLatCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	lblLonCoord.setText("");
	lblLonCoord.setBounds(new Rectangle(50, 45, 110, 20));
	lblLonCoord.setHorizontalAlignment(BasicLabel.LEFT);
	lblLonCoord.setForeground(Color.black);
	lblLonCoord.setFont(new Font("Dialog", Font.BOLD, 10));
	this.add(jpLL, null);
	this.add(jpUTM, null);
	this.add(jpGK, null);
    }

    public void setGKCoord(GKCoord _gkCoord) {
	try {
	    llCoord = CoordinateSystems.gk2geo(_gkCoord);
	} catch (BadCoordinatesException e) {
	    // TODO
	}
	utmCoord = CoordinateSystems.geo2utm(llCoord);
	gkCoord = _gkCoord;
	updateLabels();
    }

    public void setUTMCoord(UTMCoord _utmCoord) {
	utmCoord = _utmCoord;
	llCoord = CoordinateSystems.utm2geo(_utmCoord);
	gkCoord = CoordinateSystems.geo2gk(llCoord);
	updateLabels();
    }

    public void setLLCoord(LatLongCoord _llCoord) {
	llCoord = _llCoord;
	gkCoord = CoordinateSystems.geo2gk(_llCoord);
	utmCoord = CoordinateSystems.geo2utm(_llCoord);
	updateLabels();
    }

    private void updateLabels() {
	lblGKXCoord.setText(CoordinateSystems.decimalFormat(gkCoord.getX(), 4));
	lblGKYCoord.setText(CoordinateSystems.decimalFormat(gkCoord.getY(), 4));
	lblGKStripCoord.setText(String.valueOf(gkCoord.getFaja()));
	lblUTMECoord.setText(CoordinateSystems.decimalFormat(utmCoord.getEasting(), 4));
	lblUTMNCoord.setText(CoordinateSystems.decimalFormat(utmCoord.getNorthing(), 4));
	lblUTMZoneCoord.setText(String.valueOf(utmCoord.getZone()));
	lblLatCoord.setText(CoordinateSystems.dec2gms(llCoord.getLatitude(), 4));
	lblLonCoord.setText(CoordinateSystems.dec2gms(llCoord.getLongitude(), 4));
    }

}
