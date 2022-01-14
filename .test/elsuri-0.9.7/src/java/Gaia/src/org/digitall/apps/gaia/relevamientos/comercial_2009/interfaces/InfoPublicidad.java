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
 * InfoPublicidad.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.digitall.apps.gaia.relevamientos.comercial_2009.interfaces.FormPublicidad;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class InfoPublicidad extends GaiaConfigPanel {
// public class InfoPublicidad extends BasicPrimitivePanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jpDatosPrincipales = new BasicPanel();
    private BasicPanel jpFichaTecnica = new BasicPanel();
    private BasicLabel lblFichaTecnica = new BasicLabel();
    private BasicPanel jpTitle = new BasicPanel();
    private BasicLabel lblTitle = new BasicLabel();
    private BasicLabel lblCatastroTitle = new BasicLabel();
    private BasicLabel lblTextoAnuncioTitle = new BasicLabel();
    private BasicLabel lblCatastro = new BasicLabel();
    private BasicLabel lblTextoAnuncio = new BasicLabel();
    private BasicLabel lblAnchoTitle = new BasicLabel();
    private BasicLabel lblAncho = new BasicLabel();
    private BasicLabel lblTotalTitle = new BasicLabel();
    private BasicLabel lblTotal = new BasicLabel();

    private BasicLabel lblAlto = new BasicLabel();
    private BasicLabel lblAltoTitle = new BasicLabel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel jpData = new BasicPanel();
    private BasicTabbedPane tabContainer = new BasicTabbedPane();
    private AssignButton btnShowData = new AssignButton();
    private boolean showingData = false;
    private int showingDataWidth = 880;
    private int notShowingDataWidth = 446;
    private JLabel lblShowData = new JLabel();
    private JPanel jpSeparator = new JPanel();
    private BufferedImage photoImage = null;
    
    private ImageLabel lblFoto = new ImageLabel();
    private BasicScrollPane spFoto = new BasicScrollPane();

    private ModifyButton btnModifyFunction = new ModifyButton();
    private int idDetalleRelevamiento = -1;

    public InfoPublicidad() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(880, 241));
	this.setBounds(new Rectangle(10, 10, 880, 241));
	/** Por el momento oculto la posibilidad de mostrar el panel de datos extra */
	lblShowData.setVisible(true);
	btnShowData.setVisible(true);
	lblShowData.setEnabled(true);
	btnShowData.setEnabled(true);
	
	jpDatosPrincipales.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpDatosPrincipales.setBackground(Color.white);
	jpDatosPrincipales.setLayout(null);
	jpFichaTecnica.setBounds(new Rectangle(5, 5, 430, 25));
	jpFichaTecnica.setBackground(new Color(198, 198, 255));
	jpFichaTecnica.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpFichaTecnica.setLayout(borderLayout2);
        jpTitle.setBounds(new Rectangle(5, 5, 430, 25));
        jpTitle.setBackground(new Color(198, 198, 255));
        jpTitle.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblTitle.setText("Título: ");
        lblTitle.setFont(new Font("Dialog", 1, 12));
        lblTitle.setForeground(new Color(0, 99, 148));
        lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	lblFichaTecnica.setFont(new Font("Dialog", 1, 12));
	lblFichaTecnica.setForeground(new Color(0, 99, 148));
	lblFichaTecnica.setHorizontalTextPosition(SwingConstants.CENTER);
	lblFichaTecnica.setHorizontalAlignment(SwingConstants.CENTER);
	lblCatastroTitle.setText("Nº Catastro:");
	lblCatastroTitle.setBounds(new Rectangle(5, 40, 90, 30));
	lblCatastroTitle.setFont(new Font("Dialog", 1, 14));
	lblCatastroTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCatastroTitle.setForeground(Color.black);
	lblCatastro.setBounds(new Rectangle(100, 40, 100, 30));
	lblCatastro.setFont(new Font("Dialog", 1, 14));
	lblCatastro.setHorizontalAlignment(SwingConstants.CENTER);
	lblCatastro.setForeground(Color.red);
	lblCatastro.setBackground(new Color(255, 255, 181));
	lblCatastro.setOpaque(true);
	lblCatastro.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	lblTextoAnuncioTitle.setText("Texto del Anuncio:");
	lblTextoAnuncioTitle.setBounds(new Rectangle(0, 80, 140, 30));
	lblTextoAnuncioTitle.setFont(new Font("Dialog", 1, 14));
	lblTextoAnuncioTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTextoAnuncioTitle.setForeground(Color.black);
	lblTextoAnuncio.setBounds(new Rectangle(145, 80, 285, 30));
	lblTextoAnuncio.setFont(new Font("Dialog", 1, 14));
	lblTextoAnuncio.setHorizontalAlignment(SwingConstants.CENTER);
	lblTextoAnuncio.setForeground(Color.red);
	lblTextoAnuncio.setBackground(new Color(181, 231, 255));
	lblTextoAnuncio.setOpaque(true);
	lblTextoAnuncio.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	lblAlto.setBounds(new Rectangle(45, 120, 70, 30));
	lblAlto.setFont(new Font("Dialog", 1, 11));
	lblAlto.setHorizontalAlignment(SwingConstants.CENTER);
	lblAlto.setForeground(new Color(0, 0, 0));
	lblAlto.setBackground(new Color(231, 255, 181));
	lblAlto.setOpaque(true);
	lblAlto.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblAltoTitle.setText("Alto:");
	lblAltoTitle.setBounds(new Rectangle(10, 120, 45, 30));
	lblAltoTitle.setFont(new Font("Dialog", 1, 14));
	//lblAltoTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblAltoTitle.setForeground(Color.black);

	jpDatosPrincipales.add(btnModifyFunction, null);
	jpDatosPrincipales.add(lblTotalTitle, null);
	jpDatosPrincipales.add(lblAncho, null);
	jpDatosPrincipales.add(lblAnchoTitle, null);
	jpDatosPrincipales.add(lblShowData, null);
	jpDatosPrincipales.add(btnShowData, null);
	jpDatosPrincipales.add(lblCatastro, null);
	jpDatosPrincipales.add(lblCatastroTitle, null);
	jpDatosPrincipales.add(lblTextoAnuncio, null);
	jpDatosPrincipales.add(lblTextoAnuncioTitle, null);
	jpDatosPrincipales.add(lblAlto, null);
	jpDatosPrincipales.add(lblAltoTitle, null);
	jpTitle.add(lblTitle, BorderLayout.CENTER);
	jpDatosPrincipales.add(jpTitle, null);
	jpDatosPrincipales.add(lblTotal, null);
	jpFichaTecnica.add(lblFichaTecnica, BorderLayout.NORTH);
	this.add(jpFichaTecnica, BorderLayout.NORTH);
	tabContainer.addTab("Datos Principales", jpDatosPrincipales);
	this.add(tabContainer, BorderLayout.WEST);
	spFoto = new BasicScrollPane(lblFoto);
	spFoto.setBounds(new Rectangle(90, 20, 255, 180));
	spFoto.setAutoscrolls(true);
	jpData.setLayout(new BorderLayout());
	jpData.setVisible(false);
	jpData.add(spFoto, BorderLayout.CENTER);
	this.add(jpData, BorderLayout.EAST);
	this.add(jpSeparator, BorderLayout.CENTER);
	jpData.setBackground(new Color(144, 202, 220));
	jpData.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpData.setPreferredSize(new Dimension(430, 1));
	jpData.setMinimumSize(new Dimension(430, 1));
	jpData.setMaximumSize(new Dimension(430, 32767));
	
	//this.setSize(notShowingDataWidth, 206);
	 //this.setSize(showingDataWidth, 206);
	//this.setSize(showingDataWidth, 241);
	this.setSize(notShowingDataWidth, 241);
	
	tabContainer.setBounds(new Rectangle(95, 290, 60, 40));
	tabContainer.setSize(new Dimension(440, 182));
	tabContainer.setPreferredSize(new Dimension(445, 57));
	tabContainer.setMaximumSize(new Dimension(445, 32767));
	tabContainer.setMinimumSize(new Dimension(445, 57));
	btnShowData.setBounds(new Rectangle(410, 160, 25, 30));
	btnShowData.addMouseListener(new MouseListener() {

					  public void mouseClicked(MouseEvent e) {
					      if (showingData) {
						  getParentInternalFrame().setSize(notShowingDataWidth, getParentInternalFrame().getHeight());
					      } else {
						  getParentInternalFrame().setSize(showingDataWidth, getParentInternalFrame().getHeight());
					      }
					      showingData = !showingData;
					      jpData.setVisible(showingData);
					  }

					  public void mousePressed(MouseEvent e) {
					      getParentInternalFrame().setSize(showingDataWidth, getParentInternalFrame().getHeight());
					  }

					  public void mouseReleased(MouseEvent e) {
					      getParentInternalFrame().setSize(notShowingDataWidth, getParentInternalFrame().getHeight());
					  }

					  public void mouseEntered(MouseEvent e) {
					      if (!showingData) {
						  getParentInternalFrame().setSize(showingDataWidth, getParentInternalFrame().getHeight());
						  jpData.setVisible(true);
					      }
					  }

					  public void mouseExited(MouseEvent e) {
					      if (!showingData) {
						  jpData.setVisible(false);
						  getParentInternalFrame().setSize(notShowingDataWidth, getParentInternalFrame().getHeight());
					      }
					  }

				      }
	);
	lblShowData.setText("Mostrar/Ocultar más datos");
	lblShowData.setBounds(new Rectangle(240, 160, 165, 30));
	lblShowData.setFont(new Font("Dialog", 1, 11));

	//lblFoto.setHorizontalTextPosition(SwingConstants.LEADING);
	lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
	lblAnchoTitle.setBounds(new Rectangle(140, 120, 50, 30));
	lblAnchoTitle.setText("Ancho:");
	lblAnchoTitle.setFont(new Font("Dialog", 1, 14));
	//lblAnchoTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblAnchoTitle.setForeground(Color.black);
	
	lblAncho.setBounds(new Rectangle(190, 120, 70, 30));
	lblAncho.setFont(new Font("Dialog", 1, 11));
	lblAncho.setHorizontalAlignment(SwingConstants.CENTER);
	lblAncho.setForeground(new Color(0, 0, 0));
	lblAncho.setBackground(new Color(231, 255, 181));
	lblAncho.setOpaque(true);
	lblAncho.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	
	lblTotal.setBounds(new Rectangle(360, 120, 70, 30));
	lblTotal.setFont(new Font("Dialog", 1, 11));
	lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotal.setForeground(new Color(0, 0, 0));
	lblTotal.setBackground(new Color(181, 255, 181));
	lblTotal.setOpaque(true);
	lblTotal.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	//lblFoto.setBackground(Color.WHITE);	
	lblTotalTitle.setText("Superficie:");
	lblTotalTitle.setFont(new Font("Dialog", 1, 14));
	//lblAnchoTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTotalTitle.setForeground(Color.black);
	lblTotalTitle.setBounds(new Rectangle(284, 120, 80, 30));
	spFoto.setBackground(Color.white);
	btnModifyFunction.setBounds(new Rectangle(10, 165, 25, 30));
	btnModifyFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnModifyFunction_actionPerformed(e);
		}
	    }
	);
    }

    public void setContentObject(Object _contentObject) {
        super.setContentObject(_contentObject);
        if (_contentObject instanceof ESRIPoint) {
            loadPublicidad(((ESRIPoint)_contentObject).getIdPoint());
        }
    }

    private void loadPublicidad(int _idPoint){
	lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	lblTitle.setText("CARACTERÍSTICAS DEL ANUNCIO");
	lblCatastro.setText("");
	lblTextoAnuncio.setText("");
	lblAlto.setText("");
        ResultSet _publicidad = LibSQL.exFunction("gis.getpublicidadrelevamiento_2009", _idPoint + ",'" + GaiaEnvironment.getScheme() + "'");
	//ResultSet _publicidad = LibSQL.exQuery("SELECT f3c AS alto, f3d AS ancho, f3f AS total, f3g AS textoanuncio, f3i as catastro FROM gea_salta.relevamientopublicidad_2009_detalle WHERE relevamientopublicidad_2009_detalle.estado <> '*' AND relevamientopublicidad_2009_detalle.iddetallerelevamiento = 29");
        try {
            if (_publicidad.next()) {
		lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: " + _idPoint);
		lblCatastro.setText(_publicidad.getString("catastro"));
		lblTextoAnuncio.setText(_publicidad.getString("textoanuncio"));
		lblAlto.setText("" + _publicidad.getDouble("alto"));
		lblAncho.setText("" + _publicidad.getDouble("ancho"));
		lblTotal.setText("" + _publicidad.getDouble("total"));
		photoImage = LibIMG.getImage("gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE iddetallerelevamiento = " + _idPoint);
		lblFoto.setImage(photoImage);
		idDetalleRelevamiento = _idPoint;
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    private void btnModifyFunction_actionPerformed(ActionEvent e) {
	FormPublicidad formPublicidad = new FormPublicidad(idDetalleRelevamiento);
	formPublicidad.showForm();
    }
}
