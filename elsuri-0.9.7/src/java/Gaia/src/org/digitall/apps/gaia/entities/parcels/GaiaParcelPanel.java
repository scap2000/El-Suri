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
 * GaiaParcelPanel.java
 *
 * */
package org.digitall.apps.gaia.entities.parcels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Coordinador;
import org.digitall.apps.taxes.interfases.feesadmin.PlansOfPaymentMgmt;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class GaiaParcelPanel extends GaiaConfigPanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jpDatosPrincipales = new BasicPanel();
    private BasicPanel jpDatosParcelarios = new BasicPanel();
    private BasicPanel jpFichaTecnica = new BasicPanel();
    private BasicLabel lblFichaTecnica = new BasicLabel();
    private BasicPanel jpLocalidad = new BasicPanel();
    private BasicPanel jpDeudaTitle = new BasicPanel();
    private BasicLabel lblLocalidad = new BasicLabel();
    private BasicLabel lblCatastroTitle = new BasicLabel();
    private BasicLabel lblCatastro = new BasicLabel();
    private BasicLabel lblCuenta = new BasicLabel();
    private BasicLabel lblCuentaTitle = new BasicLabel();
    private BasicLabel lblSeccionTitle = new BasicLabel();
    private BasicLabel lblSeccion = new BasicLabel();
    private BasicLabel lblDeudaTitle = new BasicLabel();
    private BasicLabel lblTotalDeuda = new BasicLabel();
    private BasicLabel lblTgsTitle = new BasicLabel();
    private BasicLabel lblInmobTitle = new BasicLabel();
    private BasicLabel lblPeriodoTitle = new BasicLabel();
    private BasicLabel lblMontoTitle = new BasicLabel();
    private BasicLabel lblDesdeTitle = new BasicLabel();
    private BasicLabel lblHastaTitle = new BasicLabel();
    private BasicPanel jpDatosParcela = new BasicPanel();
    private BasicPanel jpSeccion = new BasicPanel();
    private BasicPanel jpParcela = new BasicPanel();
    private BasicPanel jpManzana = new BasicPanel();
    private BasicPanel jpDesdeTgs = new BasicPanel();
    private BasicPanel jpDesdeInmob = new BasicPanel();
    private BasicPanel jpHastaTgs = new BasicPanel();
    private BasicPanel jpHastaInmob = new BasicPanel();
    private BasicPanel jpMontoTgs = new BasicPanel();
    private BasicPanel jpMontoInmob = new BasicPanel();
    private BorderLayout borderLayout5 = new BorderLayout();
    private BasicLabel lblParcelaTitle = new BasicLabel();
    private BorderLayout borderLayout6 = new BorderLayout();
    private BasicLabel lblManzanaTitle = new BasicLabel();
    private BasicLabel lblManzana = new BasicLabel();
    private BasicLabel lblParcela = new BasicLabel();
    private BasicScrollPane spPropietarios = new BasicScrollPane();
    private BasicScrollPane spDirecciones = new BasicScrollPane();
    private BasicList jlPropietarios = new BasicList();
    private BasicList jlDirecciones = new BasicList();
    private BorderLayout borderLayout7 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicScrollPane spDomicilios = new BasicScrollPane();
    private BasicList jlDomicilios = new BasicList();
    private AddButton btnAddPropietario = new AddButton();
    private ModifyButton btnModifyPropietario = new ModifyButton();
    private DeleteButton btnDelPropietario = new DeleteButton();
    private AddButton btnAddDomicilio = new AddButton();
    private ModifyButton btnModifyDomicilio = new ModifyButton();
    private DeleteButton btnDelDomicilio = new DeleteButton();
    private BasicLabel lblPropietariosTitle = new BasicLabel();
    private BasicLabel lblDireccionTitle = new BasicLabel();
    private BasicLabel lblDomiciliosTitle = new BasicLabel();
    private BasicPanel jpPropietarios = new BasicPanel();
    private BasicPanel jpDomicilios = new BasicPanel();
    private BasicPanel jpDeuda = new BasicPanel();
    private BasicLabel lblSuperficieTotal = new BasicLabel();
    private BasicLabel lblSuperficieCubierta = new BasicLabel();
    private BasicLabel lblPerimetro = new BasicLabel();
    private BasicLabel lblFrente = new BasicLabel();
    private BasicLabel lblEsquina = new BasicLabel();
    private BasicLabel lblOchava = new BasicLabel();
    private BasicLabel lblVereda = new BasicLabel();
    private BasicLabel lblEstado = new BasicLabel();
    private BasicLabel lblObra = new BasicLabel();
    private BasicLabel lblCodigoEdif = new BasicLabel();
    private BasicLabel lblComercio = new BasicLabel();
    private BasicLabel lblPadron = new BasicLabel();
    private BasicLabel lblBaldio = new BasicLabel();
    private BasicLabel lblMicrobasural = new BasicLabel();

    private String[] catastros;

    private JCombo cbCatastros = new JCombo();
    private BasicTabbedPane tabContainer = new BasicTabbedPane();
    private AssignButton btnShowPropietarios = new AssignButton();
    private boolean showingPropietarios = false;
    private int showingPropietariosWidth = 880;
    private int notShowingPropietariosWidth = 446;
    private JLabel jLabel1 = new JLabel();
    private JPanel jpSeparator = new JPanel();
    private BasicLabel lblDesdeTgs = new BasicLabel();
    private BasicLabel lblHastaTgs = new BasicLabel();
    private JMoneyEntry lblMontoTgs = new JMoneyEntry();
    private JMoneyEntry lblMontoTgsMF = new JMoneyEntry();
    private BasicLabel lblDesdeInmob = new BasicLabel();
    private BasicLabel lblHastaInmob = new BasicLabel();
    private JMoneyEntry lblMontoInmob = new JMoneyEntry();
    private JMoneyEntry lblMontoTotal = new JMoneyEntry();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout8 = new BorderLayout();
    private BorderLayout borderLayout9 = new BorderLayout();
    private BorderLayout borderLayout10 = new BorderLayout();
    private BorderLayout borderLayout11 = new BorderLayout();
    private BorderLayout borderLayout12 = new BorderLayout();
    private PrintButton btnPrintEstadoDeuda = new PrintButton();
    private Coordinador coordinador;
    private String nombrePropietario = "";
    private String dniPropietario = "";
    private int idCatastro = -1;
    private int idBonificacion = 0;
    private PrintButton btnPrintFichaCatastral = new PrintButton();
    private BasicDrawEngine drawEngine = null;

    public GaiaParcelPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(880, 196));
	this.setBounds(new Rectangle(10, 10, 880, 206));
	jpDatosPrincipales.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDatosPrincipales.setBackground(Color.white);
	jpDatosPrincipales.setLayout(null);

	jpDatosParcelarios.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDatosParcelarios.setBackground(Color.white);
	jpDatosParcelarios.setLayout(null);

	jpFichaTecnica.setBounds(new Rectangle(5, 5, 430, 25));
	jpFichaTecnica.setBackground(new Color(198, 198, 255));
	jpFichaTecnica.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpFichaTecnica.setLayout(borderLayout2);

	jpLocalidad.setBounds(new Rectangle(5, 5, 430, 25));
	jpLocalidad.setBackground(new Color(198, 198, 255));
	jpLocalidad.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpLocalidad.setLayout(borderLayout7);

	jpDeudaTitle.setBounds(new Rectangle(5, 5, 430, 25));
	jpDeudaTitle.setBackground(new Color(198, 198, 255));
	jpDeudaTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDeudaTitle.setLayout(borderLayout7);

	lblLocalidad.setText("Localidad: ");
	lblLocalidad.setFont(new Font("Dialog", 1, 12));
	lblLocalidad.setForeground(new Color(0, 99, 148));
	lblLocalidad.setHorizontalTextPosition(SwingConstants.CENTER);
	lblLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
	lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	lblFichaTecnica.setFont(new Font("Dialog", 1, 12));
	lblFichaTecnica.setForeground(new Color(0, 99, 148));
	lblFichaTecnica.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFichaTecnica.setHorizontalAlignment(SwingConstants.CENTER);
	lblCatastroTitle.setText("Nº Catastro:");
	lblCatastroTitle.setBounds(new Rectangle(5, 35, 90, 30));
	lblCatastroTitle.setFont(new Font("Dialog", 1, 14));
	lblCatastroTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCatastroTitle.setForeground(Color.black);
	lblCatastro.setBounds(new Rectangle(100, 35, 100, 30));
	lblCatastro.setFont(new Font("Dialog", 1, 14));
	lblCatastro.setHorizontalAlignment(SwingConstants.CENTER);
	lblCatastro.setForeground(Color.red);
	lblCatastro.setBackground(new Color(255, 255, 181));
	lblCatastro.setOpaque(true);
	lblCatastro.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblCuenta.setBounds(new Rectangle(100, 70, 100, 30));
	lblCuenta.setFont(new Font("Dialog", 1, 11));
	lblCuenta.setHorizontalAlignment(SwingConstants.CENTER);
	lblCuenta.setForeground(new Color(255, 255, 181));
	lblCuenta.setBackground(new Color(255, 90, 33));
	lblCuenta.setOpaque(true);
	lblCuenta.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblCuentaTitle.setText("Nº Cuenta:");
	lblCuentaTitle.setBounds(new Rectangle(15, 70, 80, 30));
	lblCuentaTitle.setFont(new Font("Dialog", 1, 14));
	lblCuentaTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCuentaTitle.setForeground(Color.black);
	lblSeccionTitle.setText("Sección");
	lblSeccionTitle.setFont(new Font("Dialog", 0, 13));
	lblSeccionTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblSeccionTitle.setOpaque(true);
	lblSeccionTitle.setBackground(new Color(255, 181, 99));
	lblSeccionTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblSeccionTitle.setForeground(Color.black);
	lblSeccion.setFont(new Font("Dialog", 1, 32));
	lblSeccion.setHorizontalAlignment(SwingConstants.CENTER);
	lblSeccion.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblSeccion.setBackground(new Color(255, 132, 0));
	lblSeccion.setOpaque(true);
	lblSeccion.setForeground(Color.black);
	jpDatosParcela.setBounds(new Rectangle(205, 35, 230, 65));
	jpDatosParcela.setLayout(null);
	jpDatosParcela.setBackground(new Color(247, 181, 0));
	jpDatosParcela.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpSeccion.setBackground(new Color(247, 181, 0));
	jpSeccion.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpSeccion.setBounds(new Rectangle(0, 0, 60, 65));
	jpSeccion.setLayout(borderLayout4);
	jpParcela.setBackground(new Color(247, 181, 0));
	jpParcela.setLayout(borderLayout6);
	jpParcela.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpParcela.setBounds(new Rectangle(145, 0, 85, 65));
	jpManzana.setBackground(new Color(247, 181, 0));
	jpManzana.setLayout(borderLayout5);
	jpManzana.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpManzana.setBounds(new Rectangle(60, 0, 85, 65));
	lblParcelaTitle.setText("Parcela");
	lblParcelaTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblParcelaTitle.setFont(new Font("Dialog", 0, 13));
	lblParcelaTitle.setOpaque(true);
	lblParcelaTitle.setBackground(new Color(255, 181, 99));
	lblParcelaTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblParcelaTitle.setForeground(Color.black);
	lblManzanaTitle.setText("Manzana");
	lblManzanaTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblManzanaTitle.setFont(new Font("Dialog", 0, 13));
	lblManzanaTitle.setOpaque(true);
	lblManzanaTitle.setBackground(new Color(255, 181, 99));
	lblManzanaTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblManzanaTitle.setForeground(Color.black);
	lblManzana.setFont(new Font("Dialog", 1, 24));
	lblManzana.setHorizontalAlignment(SwingConstants.CENTER);
	lblManzana.setBounds(new Rectangle(1, 19, 58, 45));
	lblManzana.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblManzana.setBackground(new Color(255, 132, 0));
	lblManzana.setOpaque(true);
	lblManzana.setForeground(Color.black);
	lblParcela.setFont(new Font("Dialog", 1, 24));
	lblParcela.setHorizontalAlignment(SwingConstants.CENTER);
	lblParcela.setBounds(new Rectangle(1, 19, 58, 45));
	lblParcela.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblParcela.setBackground(new Color(255, 132, 0));
	lblParcela.setOpaque(true);
	lblParcela.setForeground(Color.black);
	spPropietarios.setBounds(new Rectangle(5, 25, 420, 45));
	spDirecciones.setBounds(new Rectangle(5, 120, 420, 45));
	spDomicilios.setBounds(new Rectangle(5, 20, 425, 85));
	btnAddPropietario.setBounds(new Rectangle(320, 75, 25, 25));
	btnModifyPropietario.setBounds(new Rectangle(360, 75, 25, 25));
	btnDelPropietario.setBounds(new Rectangle(400, 75, 25, 25));
	btnAddDomicilio.setBounds(new Rectangle(325, 115, 25, 25));
	btnModifyDomicilio.setBounds(new Rectangle(365, 115, 25, 25));
	btnDelDomicilio.setBounds(new Rectangle(400, 115, 25, 25));
        jpSeccion.add(lblSeccionTitle, BorderLayout.NORTH);
	jpSeccion.add(lblSeccion, BorderLayout.CENTER);
	jpParcela.add(lblParcelaTitle, BorderLayout.NORTH);
	jpParcela.add(lblParcela, BorderLayout.CENTER);
	jpDatosParcela.add(jpSeccion, BorderLayout.WEST);
	jpDatosParcela.add(jpParcela, BorderLayout.EAST);
	jpDatosParcela.add(jpManzana, BorderLayout.CENTER);
	jpManzana.add(lblManzanaTitle, BorderLayout.NORTH);
	jpManzana.add(lblManzana, BorderLayout.CENTER);
	jpPropietarios.add(lblPropietariosTitle, null);
	jpPropietarios.add(lblDireccionTitle, null);
	jpPropietarios.add(btnAddPropietario, null);
	jpPropietarios.add(btnModifyPropietario, null);
	jpPropietarios.add(btnDelPropietario, null);
	spPropietarios.getViewport().add(jlPropietarios, null);
	jpPropietarios.add(spPropietarios, null);
	spDirecciones.getViewport().add(jlDirecciones, null);
	jpPropietarios.add(spDirecciones, null);
	jpDomicilios.add(btnAddDomicilio, null);
	jpDomicilios.add(btnModifyDomicilio, null);
	jpDomicilios.add(btnDelDomicilio, null);
	spDomicilios.getViewport().add(jlDomicilios, null);
	jpDomicilios.add(spDomicilios, null);
	jpDomicilios.add(lblDomiciliosTitle, null);
	jpDatosParcelarios.add(lblMicrobasural, null);
	jpDatosParcelarios.add(lblBaldio, null);
	jpDatosParcelarios.add(lblPadron, null);
	jpDatosParcelarios.add(lblComercio, null);
	jpDatosParcelarios.add(lblCodigoEdif, null);
	jpDatosParcelarios.add(lblObra, null);
	jpDatosParcelarios.add(lblEstado, null);
	jpDatosParcelarios.add(lblVereda, null);
	jpDatosParcelarios.add(lblOchava, null);
	jpDatosParcelarios.add(lblEsquina, null);
	jpDatosParcelarios.add(lblFrente, null);
	jpDatosParcelarios.add(lblPerimetro, null);
	jpDatosParcelarios.add(lblSuperficieCubierta, null);
	jpDatosParcelarios.add(lblSuperficieTotal, null);
        jpDatosPrincipales.add(btnPrintFichaCatastral, null);
        jpDatosPrincipales.add(jLabel1, null);
	jpDatosPrincipales.add(btnShowPropietarios, null);
	jpDatosPrincipales.add(jpDatosParcela, null);
	jpDatosPrincipales.add(lblCuentaTitle, null);
	jpDatosPrincipales.add(lblCuenta, null);
	//jpDatosPrincipales.add(jpFichaTecnica, null);
	jpDatosPrincipales.add(lblCatastro, null);
	jpDatosPrincipales.add(lblCatastroTitle, null);
	jpLocalidad.add(lblLocalidad, BorderLayout.CENTER);
	jpDatosPrincipales.add(jpLocalidad, null);
	//jpCenter.add(tabContainer, BorderLayout.CENTER);
	tabContainer.addTab("Datos Principales", jpDatosPrincipales);
	tabContainer.addTab("Datos Parcelarios", jpDatosParcelarios);
	//tabContainer.addTab("Propietarios", jpPropietarios);
	tabContainer.addTab("Domicilios", jpDomicilios);

	lblDeudaTitle.setText("DEUDA ACTUALIZADA A LA FECHA");
	lblDeudaTitle.setFont(new Font("Dialog", 1, 12));
	lblDeudaTitle.setBounds(new Rectangle(15, 10, 405, 30));
	lblDeudaTitle.setForeground(new Color(0, 99, 148));
	lblDeudaTitle.setHorizontalTextPosition(SwingConstants.CENTER);
	lblDeudaTitle.setHorizontalAlignment(SwingConstants.CENTER);
	jpDeudaTitle.add(lblDeudaTitle, null);
	jpDeuda.add(jpDeudaTitle, BorderLayout.CENTER);
	jpDeuda.add(lblTgsTitle, null);
	jpDeuda.add(lblInmobTitle, null);
	jpDeuda.add(lblTotalDeuda, null);
	jpDeuda.add(lblMontoTotal, null);
	lblTgsTitle.setText("Servicios Retrib.");
	lblTgsTitle.setBounds(new Rectangle(5, 80, 125, 25));
	lblTgsTitle.setFont(new Font("Dialog", 1, 14));
	lblTgsTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTgsTitle.setForeground(Color.black);
	lblInmobTitle.setText("Impuesto Inmob.");
	lblInmobTitle.setBounds(new Rectangle(5, 105, 125, 25));
	lblInmobTitle.setFont(new Font("Dialog", 1, 14));
	lblInmobTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblInmobTitle.setForeground(Color.black);

	lblTotalDeuda.setText("Total:");
	lblTotalDeuda.setBounds(new Rectangle(145, 130, 115, 20));
	lblTotalDeuda.setFont(new Font("Dialog", 1, 14));
	lblTotalDeuda.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalDeuda.setForeground(Color.black);

	jpDeuda.add(jpMontoTgs, BorderLayout.CENTER);
	jpDeuda.add(jpMontoInmob, BorderLayout.CENTER);
	jpDeuda.add(lblPeriodoTitle, null);
	jpDeuda.add(lblMontoTitle, null);
	jpDeuda.add(lblDesdeTitle, null);
	jpDeuda.add(lblHastaTitle, null);
	lblPeriodoTitle.setText("Periodo");
	lblPeriodoTitle.setFont(new Font("Dialog", 0, 13));
	lblPeriodoTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblPeriodoTitle.setOpaque(true);
	lblPeriodoTitle.setBackground(new Color(255, 181, 99));
	lblPeriodoTitle.setBounds(new Rectangle(145, 40, 140, 20));
	lblPeriodoTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblPeriodoTitle.setForeground(Color.black);
	lblMontoTitle.setText("Monto Adeudado");
	lblMontoTitle.setFont(new Font("Dialog", 0, 13));
	lblMontoTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblMontoTitle.setOpaque(true);
	lblMontoTitle.setBackground(new Color(255, 181, 99));
	lblMontoTitle.setBounds(new Rectangle(285, 40, 140, 40));
	lblMontoTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblMontoTitle.setForeground(Color.black);
	lblDesdeTitle.setText("Desde");
	lblDesdeTitle.setFont(new Font("Dialog", 0, 13));
	lblDesdeTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblDesdeTitle.setOpaque(true);
	lblDesdeTitle.setBackground(new Color(255, 181, 99));
	lblDesdeTitle.setBounds(new Rectangle(145, 60, 70, 20));
	lblDesdeTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblDesdeTitle.setForeground(Color.black);
	lblHastaTitle.setText("Hasta");
	lblHastaTitle.setFont(new Font("Dialog", 0, 13));
	lblHastaTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblHastaTitle.setOpaque(true);
	lblHastaTitle.setBackground(new Color(255, 181, 99));
	lblHastaTitle.setBounds(new Rectangle(215, 60, 70, 20));
	lblHastaTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	lblHastaTitle.setForeground(Color.black);
	//jpDesdeTgs.setBackground(new Color(247, 181, 0)); (87, 149, 29)
	jpDesdeTgs.setBackground(new Color(87, 149, 29));
	jpDesdeTgs.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpDesdeTgs.setBounds(new Rectangle(145, 80, 70, 25));
	//jpDesdeInmob.setBackground(new Color(247, 181, 0)); (149, 130, 11));
	jpDesdeTgs.setLayout(borderLayout9);
	jpDesdeInmob.setBackground(new Color(149, 130, 11));
	jpDesdeInmob.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpDesdeInmob.setBounds(new Rectangle(145, 105, 70, 25));
	//jpHastaTgs.setBackground(new Color(247, 181, 0));
	jpDesdeInmob.setLayout(borderLayout10);
	jpHastaTgs.setBackground(new Color(87, 149, 29));
	jpHastaTgs.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpHastaTgs.setBounds(new Rectangle(215, 80, 70, 25));
	//jpHastaInmob.setBackground(new Color(247, 181, 0));
	jpHastaTgs.setLayout(borderLayout11);
	jpHastaInmob.setBackground(new Color(149, 130, 11));
	jpHastaInmob.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpHastaInmob.setBounds(new Rectangle(215, 105, 70, 25));
	jpHastaInmob.setLayout(borderLayout12);
	jpMontoTgs.setBackground(new Color(255, 255, 181));
	jpMontoTgs.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpMontoTgs.setBounds(new Rectangle(285, 80, 140, 25));
	jpMontoTgs.setLayout(borderLayout8);
	jpMontoInmob.setBackground(new Color(255, 255, 181));
	jpMontoInmob.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jpMontoInmob.setBounds(new Rectangle(285, 105, 140, 25));
	jpMontoInmob.setLayout(borderLayout3);
	tabContainer.addTab("Deuda", jpDeuda);
	jpFichaTecnica.add(lblFichaTecnica, BorderLayout.CENTER);
	jpFichaTecnica.add(cbCatastros, BorderLayout.WEST);
	this.add(jpFichaTecnica, BorderLayout.NORTH);
	this.add(tabContainer, BorderLayout.WEST);
	this.add(jpPropietarios, BorderLayout.EAST);
	this.add(jpSeparator, BorderLayout.CENTER);
	btnAddPropietario.setShowText(false);
	btnModifyPropietario.setShowText(false);
	btnDelPropietario.setShowText(false);
	btnAddDomicilio.setShowText(false);
	btnModifyDomicilio.setShowText(false);
	btnDelDomicilio.setShowText(false);
	lblPropietariosTitle.setText("Propietario(s)");
	lblPropietariosTitle.setBounds(new Rectangle(160, 5, 110, 20));
	lblPropietariosTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblPropietariosTitle.setFont(new Font("Dialog", 1, 13));
	lblPropietariosTitle.setForeground(Color.black);
	lblDireccionTitle.setText("Dirección(es) (G.I.S.):");
	lblDireccionTitle.setBounds(new Rectangle(145, 95, 140, 20));
	lblDireccionTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblDireccionTitle.setFont(new Font("Dialog", 1, 13));
	lblDireccionTitle.setForeground(Color.black);
	jpPropietarios.setLayout(null);
	jpPropietarios.setBackground(new Color(144, 202, 220));
	jpPropietarios.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpPropietarios.setPreferredSize(new Dimension(430, 1));
	jpPropietarios.setMinimumSize(new Dimension(430, 1));
	jpPropietarios.setMaximumSize(new Dimension(430, 32767));
	//jpPropietarios.setVisible(false);
	jpDomicilios.setBounds(new Rectangle(5, 420, 430, 100));
	jpDeuda.setBounds(new Rectangle(5, 420, 430, 100));
	jpDomicilios.setLayout(null);
	jpDomicilios.setBackground(new Color(37, 220, 207));
	jpDomicilios.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDeuda.setLayout(null);
	//jpDeuda.setBackground(new Color(37, 220, 207));
	jpDeuda.setBackground(Color.WHITE);
	jpDeuda.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblSuperficieTotal.setText("Superficie total: N/A");
	lblSuperficieTotal.setBounds(new Rectangle(5, 20, 185, 25));
	lblSuperficieTotal.setForeground(Color.black);
	lblSuperficieTotal.setFont(new Font("Lucida Sans", 0, 10));
	lblSuperficieTotal.setMaximumSize(new Dimension(73, 13));
	lblSuperficieCubierta.setText("Superficie cubierta: N/A");
	lblSuperficieCubierta.setBounds(new Rectangle(5, 40, 185, 25));
	lblSuperficieCubierta.setFont(new Font("Lucida Sans", 0, 10));
	lblSuperficieCubierta.setForeground(Color.black);
	lblSuperficieCubierta.setMaximumSize(new Dimension(73, 13));
	lblPerimetro.setText("Perímetro: N/A");
	lblPerimetro.setBounds(new Rectangle(5, 0, 185, 25));
	lblPerimetro.setForeground(Color.black);
	lblPerimetro.setFont(new Font("Lucida Sans", 0, 10));
	lblPerimetro.setMaximumSize(new Dimension(73, 13));
	lblFrente.setText("Frente: N/A");
	lblFrente.setBounds(new Rectangle(5, 60, 185, 25));
	lblFrente.setForeground(Color.black);
	lblFrente.setFont(new Font("Lucida Sans", 0, 10));
	lblFrente.setMaximumSize(new Dimension(73, 13));
	lblEsquina.setText("Esquina: N/A");
	lblEsquina.setBounds(new Rectangle(195, 0, 185, 25));
	lblEsquina.setForeground(Color.black);
	lblEsquina.setFont(new Font("Lucida Sans", 0, 10));
	lblEsquina.setMaximumSize(new Dimension(73, 13));
	lblOchava.setText("Ochava: N/A");
	lblOchava.setBounds(new Rectangle(195, 20, 185, 25));
	lblOchava.setForeground(Color.black);
	lblOchava.setFont(new Font("Lucida Sans", 0, 10));
	lblOchava.setMaximumSize(new Dimension(73, 13));
	lblVereda.setText("Vereda: N/A");
	lblVereda.setBounds(new Rectangle(195, 40, 185, 25));
	lblVereda.setForeground(Color.black);
	lblVereda.setFont(new Font("Lucida Sans", 0, 10));
	lblVereda.setMaximumSize(new Dimension(73, 13));
	lblEstado.setText("Estado: N/A");
	lblEstado.setBounds(new Rectangle(195, 60, 185, 25));
	lblEstado.setForeground(Color.black);
	lblEstado.setFont(new Font("Lucida Sans", 0, 10));
	lblEstado.setMaximumSize(new Dimension(73, 13));
	lblObra.setText("Obra: N/A");
	lblObra.setBounds(new Rectangle(5, 80, 185, 25));
	lblObra.setFont(new Font("Lucida Sans", 0, 10));
	lblObra.setForeground(Color.black);
	lblObra.setMaximumSize(new Dimension(73, 13));
	lblCodigoEdif.setText("Código Edif.: N/A");
	lblCodigoEdif.setBounds(new Rectangle(5, 100, 185, 25));
	lblCodigoEdif.setForeground(Color.black);
	lblCodigoEdif.setFont(new Font("Lucida Sans", 0, 10));
	lblCodigoEdif.setMaximumSize(new Dimension(73, 13));
	lblComercio.setText("Comercio: N/A");
	lblComercio.setBounds(new Rectangle(195, 80, 185, 25));
	lblComercio.setForeground(Color.black);
	lblComercio.setFont(new Font("Lucida Sans", 0, 10));
	lblComercio.setMaximumSize(new Dimension(73, 13));
	lblPadron.setText("Padrón: N/A");
	lblPadron.setBounds(new Rectangle(195, 100, 185, 25));
	lblPadron.setForeground(Color.black);
	lblPadron.setFont(new Font("Lucida Sans", 0, 10));
	lblPadron.setMaximumSize(new Dimension(73, 13));
	lblBaldio.setText("Baldío: N/A");
	lblBaldio.setBounds(new Rectangle(5, 120, 185, 25));
	lblBaldio.setForeground(Color.black);
	lblBaldio.setFont(new Font("Lucida Sans", 0, 10));
	lblBaldio.setMaximumSize(new Dimension(73, 13));
	lblMicrobasural.setText("Microbasural: N/A");
	lblMicrobasural.setBounds(new Rectangle(195, 120, 185, 25));
	lblMicrobasural.setForeground(Color.black);
	lblMicrobasural.setFont(new Font("Lucida Sans", 0, 10));
	cbCatastros.setBounds(new Rectangle(403, 20, 25, 21));
	cbCatastros.setPreferredSize(new Dimension(120, 20));
	cbCatastros.setBackground(new Color(255, 255, 181));
	lblDomiciliosTitle.setText("Domicilio(s)");
	lblDomiciliosTitle.setBounds(new Rectangle(160, 0, 110, 20));
	lblDomiciliosTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblDomiciliosTitle.setFont(new Font("Dialog", 1, 13));
	this.setSize(notShowingPropietariosWidth, 206);
	cbCatastros.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadCatastro();
		    }
		}

	    });
	tabContainer.setBounds(new Rectangle(95, 290, 60, 40));
	tabContainer.setSize(new Dimension(440, 182));
	tabContainer.setPreferredSize(new Dimension(445, 57));
	tabContainer.setMaximumSize(new Dimension(445, 32767));
	tabContainer.setMinimumSize(new Dimension(445, 57));
	btnShowPropietarios.setBounds(new Rectangle(410, 110, 25, 30));
	btnShowPropietarios.addMouseListener(new MouseListener() {

		public void mouseClicked(MouseEvent e) {
		    if (showingPropietarios) {
			getParentInternalFrame().setSize(notShowingPropietariosWidth, getParentInternalFrame().getHeight());
		    } else {
			getParentInternalFrame().setSize(showingPropietariosWidth, getParentInternalFrame().getHeight());
		    }
		    showingPropietarios = !showingPropietarios;
		    jpPropietarios.setVisible(showingPropietarios);
		}

		public void mousePressed(MouseEvent e) {
		    getParentInternalFrame().setSize(showingPropietariosWidth, getParentInternalFrame().getHeight());
		}

		public void mouseReleased(MouseEvent e) {
		    getParentInternalFrame().setSize(notShowingPropietariosWidth, getParentInternalFrame().getHeight());
		}

		public void mouseEntered(MouseEvent e) {
		    if (!showingPropietarios) {
			getParentInternalFrame().setSize(showingPropietariosWidth, getParentInternalFrame().getHeight());
			jpPropietarios.setVisible(true);
		    }
		}

		public void mouseExited(MouseEvent e) {
		    if (!showingPropietarios) {
			jpPropietarios.setVisible(false);
			getParentInternalFrame().setSize(notShowingPropietariosWidth, getParentInternalFrame().getHeight());
		    }
		}

	    });
	jLabel1.setText("Mostrar/Ocultar propietarios");
	jLabel1.setBounds(new Rectangle(240, 110, 165, 30));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	lblDesdeTgs.setFont(new Font("Lucida Sans", 1, 12));
	lblDesdeTgs.setForeground(Color.yellow);
	lblDesdeTgs.setHorizontalAlignment(SwingConstants.CENTER);
	lblDesdeTgs.setHorizontalTextPosition(SwingConstants.CENTER);
	lblHastaTgs.setFont(new Font("Lucida Sans", 1, 12));
	lblHastaTgs.setForeground(Color.yellow);
	lblHastaTgs.setHorizontalAlignment(SwingConstants.CENTER);
	lblHastaTgs.setHorizontalTextPosition(SwingConstants.CENTER);
	lblMontoTgs.setEditable(false);
	lblMontoTgsMF.setEditable(false);

	lblDesdeInmob.setForeground(Color.yellow);
	lblMontoTgs.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblMontoTgs.setForeground(Color.red);
	lblMontoTgsMF.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblMontoTgsMF.setForeground(Color.red);
	lblMontoTgs.setPreferredSize(new Dimension(70, 14));
	lblMontoTgs.setBackground(new Color(255, 247, 214));
	lblMontoTgsMF.setOpaque(false);
	lblMontoTgsMF.setPreferredSize(new Dimension(70, 14));
	lblDesdeInmob.setFont(new Font("Lucida Sans", 1, 12));
	lblDesdeInmob.setHorizontalAlignment(SwingConstants.CENTER);
	lblDesdeInmob.setHorizontalTextPosition(SwingConstants.CENTER);
	lblHastaInmob.setFont(new Font("Lucida Sans", 1, 12));
	lblHastaInmob.setForeground(Color.yellow);
	lblHastaInmob.setHorizontalAlignment(SwingConstants.CENTER);
	lblHastaInmob.setHorizontalTextPosition(SwingConstants.CENTER);
	lblMontoInmob.setEditable(false);
	lblMontoInmob.setFont(new Font("Dialog", 1, 13));
	lblMontoInmob.setForeground(Color.red);
	lblMontoInmob.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	lblMontoInmob.setOpaque(false);
	lblMontoTgs.setFont(new Font("Lucida Sans", Font.BOLD, 10));
	lblMontoTgsMF.setFont(new Font("Lucida Sans", Font.BOLD, 10));
	lblMontoTotal.setEditable(false);
	lblMontoTotal.setFont(new Font("Dialog", 1, 13));
	lblMontoTotal.setBackground(Color.red);
	lblMontoTotal.setForeground(Color.yellow);
	lblMontoTotal.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblMontoTotal.setOpaque(true);
	lblMontoTotal.setBounds(new Rectangle(285, 130, 140, 20));

	jpMontoTgs.add(lblMontoTgs, BorderLayout.WEST);
	jpMontoTgs.add(lblMontoTgsMF, BorderLayout.EAST);
	jpMontoInmob.add(lblMontoInmob, BorderLayout.CENTER);
	jpHastaTgs.add(lblHastaTgs, BorderLayout.CENTER);
	jpDeuda.add(jpHastaTgs, BorderLayout.CENTER);
	jpHastaInmob.add(lblHastaInmob, BorderLayout.CENTER);
	jpDeuda.add(jpHastaInmob, BorderLayout.CENTER);
	jpDesdeTgs.add(lblDesdeTgs, BorderLayout.CENTER);
	jpDesdeInmob.add(lblDesdeInmob, BorderLayout.CENTER);
	jpDeuda.add(jpDesdeTgs, BorderLayout.CENTER);
	jpDeuda.add(jpDesdeInmob, BorderLayout.CENTER);

	btnPrintEstadoDeuda.setBounds(new Rectangle(175, 133, 35, 20));
	btnPrintEstadoDeuda.setToolTipText("Imprimir Estado de Deuda");
	btnPrintEstadoDeuda.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnPrintEstadoDeuda_actionPerformed(e);
		}
	    });
        btnPrintFichaCatastral.setToolTipText("Imprimir Ficha Catastral - Control para todos los catastros de la parcela - Shift para varios catastros a elección");
        btnPrintFichaCatastral.setBounds(new Rectangle(10, 110, 25, 30));
        btnPrintFichaCatastral.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnPrintFichaCatastral_actionPerformed(e);
                }
            });
        jpDeuda.add(btnPrintEstadoDeuda, null);
    }

    public void setContentObject(Object _contentObject) {
	super.setContentObject(_contentObject);
	if (_contentObject instanceof ESRIPolygon) {
	    loadParcela(((ESRIPolygon)_contentObject).getIdPolygon());
	}
    }

    private void loadParcela(int _idParcela) {
	lblCuenta.setText("");
	lblSeccion.setText("");
	lblManzana.setText("");
	lblParcela.setText("");
	lblCatastro.setText("");
	String _catastros = LibSQL.getString(GaiaEnvironment.getScheme() + ".getcatastrosporparcela", _idParcela);
	if (_catastros.length() > 0) {
	    catastros = _catastros.split(", ");
	} else {
	    catastros = new String[0];
	}
	cbCatastros.removeAllItems();
	for (int i = 0; i < catastros.length; i++) {
	    cbCatastros.addItem(catastros[i]);
	}

	ResultSet _parcela = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getparcela", _idParcela);

	try {
	    if (_parcela.next()) {
		lblCuenta.setText(_parcela.getString("idparcela"));
		lblLocalidad.setText("LOCALIDAD: " + _parcela.getString("localidad"));
		lblSeccion.setText(_parcela.getString("seccion"));
		lblManzana.setText(_parcela.getString("nmanzana") + _parcela.getString("lmanzana"));
		lblParcela.setText(_parcela.getString("nparcela") + _parcela.getString("lparcela"));
		if (_catastros.length() == 0) {
		    lblCuenta.setText(_parcela.getString("barcode"));
		} else {
		    lblCuenta.setText("");
		}
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}

	loadCatastro();

	ResultSet _direccion = LibSQL.exFunction("gis.getdireccion", _idParcela + ",'" + GaiaEnvironment.getScheme() + "'");
	Vector direcciones = new Vector();
	jlDirecciones.setListData(direcciones);
	try {
	    while (_direccion.next()) {
		direcciones.add(_direccion.getString("nombre") + " Nº " + _direccion.getString("number"));
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}

    }

    public void loadCatastro() {
	cbCatastros.setVisible(cbCatastros.getItemCount() > 1);
	Vector<String> propietarios = new Vector<String>();
	if (cbCatastros.getItemCount() > 0) {
	    lblCatastro.setText(cbCatastros.getSelectedItem().toString());
	    lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: " + cbCatastros.getSelectedItem().toString());
	    ResultSet _catastro = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getcatastro", cbCatastros.getSelectedItem().toString());
	    try {
		while (_catastro.next()) {
		    propietarios.add(_catastro.getString("domape") + " (DNI: " + _catastro.getString("domnudoc") + ")" + " - DOMINIO: " + _catastro.getString("dompor"));
		    lblCuenta.setText(_catastro.getString("cuentacorriente"));
		    ResultSet _deudas = LibSQL.exFunction("taxes.getDeudaCatastral", cbCatastros.getSelectedItem().toString());
		    if (_deudas.next()) {
			lblDesdeTgs.setText(_deudas.getString("desdetgs"));
			lblHastaTgs.setText(_deudas.getString("hastatgs"));
			lblMontoTgs.setValue(_deudas.getDouble("montototaltgs"));
			lblMontoTgsMF.setValue(_deudas.getDouble("montototaltgsmf"));
			lblDesdeInmob.setText(_deudas.getString("desdeinmob"));
			lblHastaInmob.setText(_deudas.getString("hastainmob"));
			lblMontoInmob.setValue(_deudas.getDouble("montototalinmob"));
		    } else {
			lblDesdeTgs.setText("-");
			lblHastaTgs.setText("-");
			lblMontoTgs.setValue(0.0);
			lblMontoTgsMF.setValue(0.0);
			lblDesdeInmob.setText("-");
			lblHastaInmob.setText("-");
			lblMontoInmob.setValue(0.0);
		    }
		    idCatastro = _catastro.getInt("idcatastro");
		    nombrePropietario = _catastro.getString("domape");
		    dniPropietario = _catastro.getString("domnudoc");
		}
	    } catch (SQLException ex) {
		lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ERROR");
		lblCatastro.setText("ERROR");
		lblDesdeTgs.setText("");
		lblHastaTgs.setText("");
		lblMontoTgs.setValue(0.0);
		lblMontoTgsMF.setValue(0.0);
		lblDesdeInmob.setText("");
		lblHastaInmob.setText("");
		lblMontoInmob.setValue(0.0);
		ex.printStackTrace();
	    }
	} else {
	    lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	    lblCatastro.setText("NO POSEE");
	    lblDesdeTgs.setText("");
	    lblHastaTgs.setText("");
	    lblMontoTgs.setValue(0.0);
	    lblMontoTgsMF.setValue(0.0);
	    lblDesdeInmob.setText("");
	    lblHastaInmob.setText("");
	    lblMontoInmob.setValue(0.0);
	}
	lblMontoTotal.setValue(lblMontoTgsMF.getAmount() + lblMontoInmob.getAmount());
	jlPropietarios.setListData(propietarios);
	if (jlPropietarios.getModel().getSize() > 0) {
	    jlPropietarios.setSelectedIndex(0);
	}
    }

    private void btnPrintEstadoDeuda_actionPerformed(ActionEvent e) {
	imprimirEstadoDeDeuda(1); //TGS
	imprimirEstadoDeDeuda(2); //Imp. Inm.
    }

    /**2010-02-25 (moraless)*/
    private void imprimirEstadoDeDeuda(int _tipoImpuesto) {
	int anticipoDesde = 1;
	int anioDesde = 2000;
	int anticipoHasta = 12;
	int anioHasta = 2009;
	int cantAnticipos = 1;
	String bonificacion = "";
	double montoDescuento = 0.0;
	double totalInteres = 0.0;
	double deudaParcial = 0.0;
	double subtotal = 0.0;
	double totalDeuda = 0.0;

	coordinador = new Coordinador();
	coordinador.setIdBien(idCatastro); //idcatastro
	coordinador.setIdTipoImpuesto(_tipoImpuesto); // 1: TGS 2: INMOB
	coordinador.setNroBien(lblCatastro.getText().trim()); //nrocatatro
	coordinador.setContribuyente(nombrePropietario);
	coordinador.setDni(dniPropietario);
	coordinador.loadObject();

	if (coordinador.getIdTipoImpuesto() != 4) {
	    if (coordinador.getIdTipoImpuesto() == 1 || coordinador.getIdTipoImpuesto() == 2) { /** Caso Impuesto TGS o Inmobiliario */
		BasicReport report = new BasicReport(PlansOfPaymentMgmt.class.getResource("xml/EstadoDeCuentaCatastral.xml"));
		//ResultSet result = LibSQL.exFunction("taxes.xmlGetEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
		ResultSet result = LibSQL.exFunction("taxes.xmlGetEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + idBonificacion);
		try {
		    if (result.next()) {
			anticipoDesde = result.getInt("anticipodesde");
			anioDesde = result.getInt("aniodesde");
			anticipoHasta = result.getInt("anticipohasta");
			anioHasta = result.getInt("aniohasta");
			cantAnticipos = result.getInt("cantanticipos");
			bonificacion = result.getString("bonificacion");
			montoDescuento = result.getDouble("totaldescuento");
			totalInteres = result.getDouble("totalinteres");
			deudaParcial = result.getDouble("deudaparcial");
			subtotal = result.getDouble("subtotal");
			totalDeuda = result.getDouble("totaldeuda");
		    }

		    /** Datos Encabezado */
		    if (coordinador.getIdTipoImpuesto() == 1) {
			report.setProperty("impuesto", "TASA GENERAL DE SERVICIOS");
		    } else if (coordinador.getIdTipoImpuesto() == 2) {
			report.setProperty("impuesto", "IMPUESTO INMOBILIARIO");
		    } else {
			report.setProperty("impuesto", "");
		    }

		    /** Datos del Catastro */
		    coordinador.getCatastro().retrieveApoderadoData();
		    report.setProperty("cuentacorriente", coordinador.getCatastro().getNroCuenta());
		    report.setProperty("titulobien", "Catastro:");
		    report.setProperty("titulovalorfiscal", "Val. Fisc.:");
		    report.setProperty("valorfiscal", Double.parseDouble(coordinador.getCatastro().getValfis()));
		    report.setProperty("bien", coordinador.getCatastro().getCatastro());
		    report.setProperty("apoderado", coordinador.getCatastro().getApoderadoLastName() + " " + coordinador.getCatastro().getApoderadoName());
		    report.setProperty("domicilio", coordinador.getCatastro().getDcalle() + " Nº " + coordinador.getCatastro().getDnumro());

		    /** Datos del Resumen */
		    if (cantAnticipos > 0) {
			report.setProperty("anticipos", anticipoDesde + "/" + anioDesde + " - " + anticipoHasta + "/" + anioHasta);
		    } else {
			report.setProperty("anticipos", "No adeuda períodos");
		    }
		    report.setProperty("bonificacion", bonificacion);
		    report.setProperty("montoBonificacion", montoDescuento);
		    report.setProperty("totalInteres", totalInteres);
		    report.setProperty("cantAnticipos", cantAnticipos);
		    report.setProperty("deudaParcial", deudaParcial);
		    report.setProperty("totalDeuda", totalDeuda);
		    report.setProperty("totaldeudatext", "( Son Pesos " + Format.NumberToText.numberToText(totalDeuda) + " .-)");
		    report.setProperty("subtotal", subtotal);

		    /** Datos del Detalle */
		    report.setProperty("contribuyente", coordinador.getContribuyente());
		    report.addTableModel("taxes.xmlGetDetalleEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + idBonificacion);
		    //report.addTableModel("taxes.xmlGetDetalleEstadoDeCuenta", "" + coordinador.getIdTipoImpuesto() + "," + coordinador.getIdBien() + "," + cbBonificaciones.getSelectedValue());
		    report.doReport();

		} catch (SQLException x) {
		    // TODO
		    Advisor.messageBox("Error al intentar generar el reporte", "Error");
		}

	    } else {
		Advisor.messageBox("Funcionalidad no disponible", "Mensaje");
	    }
	} else {
	    Advisor.messageBox("Funcionalidad no disponible", "Mensaje");
	}
    }

    private void btnPrintFichaCatastral_actionPerformed(ActionEvent e) {
	int _limit = 5;
	String[] catastros = new String[] {lblCatastro.getText()};
	if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
	    String _catastros = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese los catastros separados por comas", "Catastros", JOptionPane.QUESTION_MESSAGE);
	    if (_catastros != null) {
		catastros = _catastros.split(",");
	    }
	}
	if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
	    catastros = new String[cbCatastros.getItemCount()];
	    for (int i = 0; i < catastros.length; i++) {
		catastros[i] = cbCatastros.getItemsVector().elementAt(i).toString();
	    }
	}
	if (catastros.length > 5) {
	    Advisor.messageBox("El límite para los reportes múltiples es de 5 catastros", "Aviso");
	} else {
	    _limit = catastros.length;
	}
	for (int i = 0; i < _limit; i++) {
	    ResultSet _rsCatastro = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getcatastro", catastros[i]);
	    try {
	        if (_rsCatastro.next()) {
	            doReport(_rsCatastro.getString("idcatastro"));
	        }
	    } catch (SQLException x) {
	        x.printStackTrace();
	    }
	}

    }
    private void doReport(String _idCatastro) {
	BasicReport _report = new BasicReport(GaiaParcelPanel.class.getResource("xml/FichaCatastral.xml"));
        _report.setProperty("title", "Ficha Catastral");
	String _catastro = "-1";
        ResultSet _rsCatastro = LibSQL.exFunction("taxes.getCatastro", _idCatastro + ",''"); 
        try  {
            if (_rsCatastro.next())  {
		_catastro = _rsCatastro.getString("numero");
                _report.setProperty("departamento", _rsCatastro.getString("departamento"));
                _report.setProperty("localidad", _rsCatastro.getString("localidad"));
                _report.setProperty("catastro", _catastro);
                _report.setProperty("numdocumento", _rsCatastro.getString("domnudoc"));
                _report.setProperty("vigencia", _rsCatastro.getString("vigencia"));
                _report.setProperty("plano", _rsCatastro.getString("plano"));
                _report.setProperty("fechaasignacion", _rsCatastro.getString("tecfeasg"));
                _report.setProperty("seccion", _rsCatastro.getString("tecsec"));
                _report.setProperty("manzana", _rsCatastro.getString("tecman"));
                _report.setProperty("letramanzana", _rsCatastro.getString("tecmanl"));
                _report.setProperty("parcela", _rsCatastro.getString("tecpar"));
                _report.setProperty("letraparcela", _rsCatastro.getString("tecparl"));
                _report.setProperty("catoriginal1", _rsCatastro.getString("tecorg1"));
                _report.setProperty("catoriginal2", _rsCatastro.getString("tecorg2"));
                _report.setProperty("supurbana", _rsCatastro.getDouble("tecsuurb") + " m2");
                _report.setProperty("terreno", _rsCatastro.getString("terreno"));
                _report.setProperty("valorterreno", _rsCatastro.getDouble("terval"));
                _report.setProperty("valormejoras", _rsCatastro.getDouble("tervmej"));
                _report.setProperty("valorfiscal", _rsCatastro.getDouble("valfis"));
                _report.setProperty("direccion", _rsCatastro.getString("dcalle"));
                _report.setProperty("direccionnumero", _rsCatastro.getString("dnumro"));
                _report.setProperty("barrio", _rsCatastro.getString("ddesbarrio"));
		_report.setProperty("plano", _rsCatastro.getString("plano"));
		int _scale = 4;
		if (drawEngine != null) {
		    _report.setProperty("mapImage",drawEngine.getCadastralMapImage(new Dimension(250*_scale,250*_scale), LibSQL.getInt(GaiaEnvironment.getScheme() + ".getidparcelabycatastro", _rsCatastro.getString("numero"))));
		}

		_report.addTableModel("taxes.getDatosTitularPorCatastro", _catastro);
		
		System.out.println("Revisar las líneas comentadas");
		/*ResultSet zonificacion = LibSQL.exFunction(GaiaEnvironment.scheme + ".getDatosZonificacionPorCatastro", _catastro);
		if (zonificacion.next())  {
		    _report.setProperty("categoriatgs", zonificacion.getString("categoriatgs")); 
		}*/
		    
		ResultSet comercioPublicidad = LibSQL.exFunction("gea_salta.getComercioYPublicidadPorCatastro", _catastro);

		if (comercioPublicidad.next())  {
		    _report.setProperty("comercios", comercioPublicidad.getString("comercios")); 
		    _report.setProperty("cantcomercios", comercioPublicidad.getString("cantcomercios"));
		    _report.setProperty("carteles", comercioPublicidad.getString("carteles"));
		    _report.setProperty("cantcarteles", comercioPublicidad.getString("cantcarteles"));
		}

            }
        } catch (SQLException ex)  {
            ex.printStackTrace();
        }
        
        _report.doReport();
    }

    public void setDrawEngine(BasicDrawEngine _drawEngine) {
	drawEngine = _drawEngine;
    }
}
