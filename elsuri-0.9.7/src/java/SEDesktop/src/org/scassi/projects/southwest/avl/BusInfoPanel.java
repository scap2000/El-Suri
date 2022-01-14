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
 * BusInfoPanel.java
 * Written by Santiago Cassina - SCasSI
 * July, 2013
 * */
package org.scassi.projects.southwest.avl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class BusInfoPanel extends GaiaConfigPanel {

    private BasicPanel jpFichaTecnica = new BasicPanel();
    private BasicLabel lblFichaTecnica = new BasicLabel();

    private BasicPanel jpEmpresa = new BasicPanel(new BorderLayout());
    private BasicLabel lblEmpresa = new BasicLabel();

    private BasicPanel jpDatosPrincipales = new BasicPanel();
    private BasicLabel lblEquipoTitle = new BasicLabel();
    private BasicLabel lblEquipo = new BasicLabel();
    private BasicLabel lblRutaTitle = new BasicLabel();
    private BasicLabel lblRuta = new BasicLabel();
    private BasicLabel lblEtapaTitle = new BasicLabel();
    private BasicLabel lblEtapa = new BasicLabel();
    private BasicLabel lblServicioTitle = new BasicLabel();
    private BasicLabel lblServicio = new BasicLabel();

    private BasicLabel lblBoletosTitle = new BasicLabel();
    private BasicLabel lblBoletos = new BasicLabel();

    private BasicPanel jpChofer = new BasicPanel(new BorderLayout());
    private BasicLabel lblChofer = new BasicLabel();
    private BasicPanel jpInspector = new BasicPanel(new BorderLayout());
    private BasicLabel lblInspector = new BasicLabel();

    public BusInfoPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(new BorderLayout());
	this.setBounds(new Rectangle(10, 10, 448, 200));
	this.setPreferredSize(new Dimension(246, 198));
	this.setSize(new Dimension(448, 204));
	jpDatosPrincipales.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDatosPrincipales.setBackground(Color.white);
	jpDatosPrincipales.setLayout(null);

	jpDatosPrincipales.setMinimumSize(new Dimension(1, 100));
	jpDatosPrincipales.setPreferredSize(new Dimension(1, 180));
	jpFichaTecnica.setBounds(new Rectangle(5, 5, 430, 25));
	jpFichaTecnica.setBackground(new Color(198, 198, 255));
	jpFichaTecnica.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpFichaTecnica.setLayout(new BorderLayout());

	jpEmpresa.setBounds(new Rectangle(5, 5, 430, 25));
	jpEmpresa.setBackground(new Color(198, 198, 255));
	jpEmpresa.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	jpChofer.setBounds(new Rectangle(5, 105, 430, 25));
	jpChofer.setBackground(new Color(198, 198, 255));
	jpChofer.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	jpInspector.setBounds(new Rectangle(5, 135, 430, 25));
	jpInspector.setBackground(new Color(198, 198, 255));
	jpInspector.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	lblEmpresa.setText("Empresa: ");
	lblEmpresa.setFont(new Font("Dialog", 1, 12));
	lblEmpresa.setForeground(new Color(0, 99, 148));
	lblEmpresa.setHorizontalTextPosition(SwingConstants.CENTER);
	lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
	lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	lblFichaTecnica.setFont(new Font("Dialog", 1, 12));
	lblFichaTecnica.setForeground(new Color(0, 99, 148));
	lblFichaTecnica.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFichaTecnica.setHorizontalAlignment(SwingConstants.CENTER);
	lblEquipoTitle.setText("ID Equipo:");
	lblEquipoTitle.setBounds(new Rectangle(5, 35, 90, 30));
	lblEquipoTitle.setFont(new Font("Dialog", 1, 14));
	lblEquipoTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblEquipoTitle.setForeground(Color.black);
	lblEquipo.setBounds(new Rectangle(100, 35, 100, 30));
	lblEquipo.setFont(new Font("Dialog", 1, 8));
	lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
	lblEquipo.setForeground(Color.red);
	lblEquipo.setBackground(new Color(255, 255, 181));
	lblEquipo.setOpaque(true);
	lblEquipo.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblRuta.setBounds(new Rectangle(100, 70, 100, 30));
	lblRuta.setFont(new Font("Dialog", 1, 11));
	lblRuta.setHorizontalAlignment(SwingConstants.CENTER);
	lblRuta.setForeground(new Color(255, 255, 181));
	lblRuta.setBackground(new Color(255, 90, 33));
	lblRuta.setOpaque(true);
	lblRuta.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblRutaTitle.setText("Ruta:");
	lblRutaTitle.setBounds(new Rectangle(15, 70, 80, 30));
	lblRutaTitle.setFont(new Font("Dialog", 1, 14));
	lblRutaTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblRutaTitle.setForeground(Color.black);
	lblEtapaTitle.setText("Etapa");
	lblEtapaTitle.setFont(new Font("Dialog", 0, 13));
	lblEtapaTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblEtapaTitle.setOpaque(true);
	lblEtapaTitle.setBackground(new Color(255, 181, 99));
	lblEtapaTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblEtapaTitle.setForeground(Color.black);
	lblEtapaTitle.setBounds(new Rectangle(215, 35, 55, 20));
	lblEtapa.setFont(new Font("Dialog", 1, 32));
	lblEtapa.setHorizontalAlignment(SwingConstants.CENTER);
	lblEtapa.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblEtapa.setBackground(new Color(255, 132, 0));
	lblEtapa.setOpaque(true);
	lblEtapa.setForeground(Color.black);
	lblEtapa.setBounds(new Rectangle(215, 55, 55, 45));
	lblBoletosTitle.setText("Boletos");
	lblBoletosTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblBoletosTitle.setFont(new Font("Dialog", 0, 13));
	lblBoletosTitle.setOpaque(true);
	lblBoletosTitle.setBackground(new Color(255, 181, 99));
	lblBoletosTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblBoletosTitle.setForeground(Color.black);
	lblBoletosTitle.setBounds(new Rectangle(355, 35, 80, 20));
	lblServicioTitle.setText("Servicio");
	lblServicioTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblServicioTitle.setFont(new Font("Dialog", 0, 13));
	lblServicioTitle.setOpaque(true);
	lblServicioTitle.setBackground(new Color(255, 181, 99));
	lblServicioTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblServicioTitle.setForeground(Color.black);
	lblServicioTitle.setBounds(new Rectangle(270, 35, 85, 20));
	lblServicio.setFont(new Font("Dialog", 1, 24));
	lblServicio.setHorizontalAlignment(SwingConstants.CENTER);
	lblServicio.setBounds(new Rectangle(270, 55, 85, 45));
	lblServicio.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblServicio.setBackground(new Color(255, 132, 0));
	lblServicio.setOpaque(true);
	lblServicio.setForeground(Color.black);
	lblBoletos.setFont(new Font("Dialog", 1, 24));
	lblBoletos.setHorizontalAlignment(SwingConstants.CENTER);
	lblBoletos.setBounds(new Rectangle(355, 55, 80, 45));
	lblBoletos.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblBoletos.setBackground(new Color(255, 132, 0));
	lblBoletos.setOpaque(true);
	lblBoletos.setForeground(Color.black);

	lblChofer.setText("Chofer: ");
	lblChofer.setFont(new Font("Dialog", 1, 12));
	lblChofer.setForeground(new Color(0, 99, 148));
	lblChofer.setHorizontalTextPosition(SwingConstants.CENTER);
	lblChofer.setHorizontalAlignment(SwingConstants.CENTER);

	lblInspector.setText("Inspector: No hay un inspector en este momento");
	lblInspector.setFont(new Font("Dialog", 1, 12));
	lblInspector.setForeground(new Color(0, 99, 148));
	lblInspector.setHorizontalTextPosition(SwingConstants.CENTER);
	lblInspector.setHorizontalAlignment(SwingConstants.CENTER);

	jpDatosPrincipales.add(lblRutaTitle, null);
	jpDatosPrincipales.add(lblRuta, null);
	jpDatosPrincipales.add(lblEquipo, null);
	jpDatosPrincipales.add(lblEquipoTitle, null);
	jpEmpresa.add(lblEmpresa, BorderLayout.CENTER);
	jpDatosPrincipales.add(jpEmpresa, null);
	jpDatosPrincipales.add(lblEtapaTitle, null);
	jpDatosPrincipales.add(lblEtapa, null);
	jpDatosPrincipales.add(lblServicioTitle, null);
	jpDatosPrincipales.add(lblServicio, null);
	jpDatosPrincipales.add(lblBoletosTitle, null);
	jpDatosPrincipales.add(lblBoletos, null);
	jpDatosPrincipales.add(jpChofer, null);
	jpDatosPrincipales.add(jpInspector, null);
	jpFichaTecnica.add(lblFichaTecnica, BorderLayout.NORTH);
	jpChofer.add(lblChofer, BorderLayout.CENTER);
	jpInspector.add(lblInspector, BorderLayout.CENTER);
	this.add(jpFichaTecnica, BorderLayout.NORTH);
	jpFichaTecnica.add(jpDatosPrincipales, BorderLayout.CENTER);

    }

    public void setContentObject(Object _contentObject) {
	super.setContentObject(_contentObject);
	if (_contentObject instanceof ESRIPoint) {
	    loadBus(((ESRIPoint)_contentObject).getIdPoint());
	}
    }
    
    private void loadBus(int _idBus) {
	lblFichaTecnica.setText("");
	lblEmpresa.setText("");
	lblEquipo.setText("");
	lblRuta.setText("");
	lblEtapa.setText("");
	lblServicio.setText("");
	lblBoletos.setText("");
	lblChofer.setText("Chofer:");
	lblInspector.setText("Inspector: No hay un inspector en este momento");
	ResultSet _bus = LibSQL.exFunction("reportes.getAVLBusInfo", _idBus);

	try {
	    if (_bus.next()) {
	        lblEmpresa.setText("Empresa: " + _bus.getString("empresa"));
		lblEquipo.setText(_bus.getString("idequipo"));
		lblRuta.setText(_bus.getString("ruta"));
		lblEtapa.setText(_bus.getString("etapa"));
		lblServicio.setText(_bus.getString("servicio"));
	        lblBoletos.setText(_bus.getString("boleto"));
	        lblChofer.setText(_bus.getString("chofer"));
	        lblInspector.setText(!_bus.getString("inspector").equals("")?"Inspector: " + _bus.getString("inspector"):"Inspector: No hay un inspector en este momento");
		lblFichaTecnica.setText("Bus: " + _bus.getString("idcoche") + " (" + Proced.setFormatDate(_bus.getString("fecha"), true) + " - " +_bus.getString("hora") + ")");
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}

    }    

}
