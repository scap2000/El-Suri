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
 * InfoComercios.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class InfoComercios extends GaiaConfigPanel {

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jpDatosPrincipales = new BasicPanel();
    private BasicPanel jpFichaTecnica = new BasicPanel();
    private BasicLabel lblFichaTecnica = new BasicLabel();
    private BasicPanel jpTitle = new BasicPanel();
    private BasicLabel lblTitle = new BasicLabel();
    private BasicLabel lblCatastroTitle = new BasicLabel();
    private BasicLabel lblNombreFantasiaTitle = new BasicLabel();
    private BasicLabel lblCatastro = new BasicLabel();
    private BasicLabel lblNombreFantasia = new BasicLabel();
    private BasicLabel lblPadron = new BasicLabel();
    private BasicLabel lblPadronTitle = new BasicLabel();
    private BasicLabel lblRubro = new BasicLabel();
    private BasicLabel lblRubroTitle = new BasicLabel();
    private BasicLabel lblDescripcionTitle = new BasicLabel();
    private BasicLabel lblDescripcion = new BasicLabel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel jpData = new BasicPanel();
    private BasicTabbedPane tabContainer = new BasicTabbedPane();
    private AssignButton btnShowData = new AssignButton();
    private boolean showingData = false;
    private int showingDataWidth = 880;//880;
    private int notShowingDataWidth = 446;//446
    private JLabel lblShowData = new JLabel();
    private JPanel jpSeparator = new JPanel();

    private ModifyButton btnModifyFunction = new ModifyButton();
    private int idEncuestaComercial = -1;

    public InfoComercios() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(880, 265));
	this.setBounds(new Rectangle(10, 10, 880, 265));
	/** Por el momento oculto la posibilidad de mostrar el panel de datos extra */
	lblShowData.setVisible(false);
	btnShowData.setVisible(false);
	lblShowData.setEnabled(false);
	btnShowData.setEnabled(false);
	
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

	lblNombreFantasiaTitle.setText("Nombre Fantasía:");
	lblNombreFantasiaTitle.setBounds(new Rectangle(5, 75, 130, 30));
	lblNombreFantasiaTitle.setFont(new Font("Dialog", 1, 14));
	lblNombreFantasiaTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNombreFantasiaTitle.setForeground(Color.black);
	lblNombreFantasia.setBounds(new Rectangle(140, 75, 290, 30));
	lblNombreFantasia.setFont(new Font("Dialog", 1, 14));
	lblNombreFantasia.setHorizontalAlignment(SwingConstants.CENTER);
	lblNombreFantasia.setForeground(Color.red);
	lblNombreFantasia.setBackground(new Color(181, 231, 255));
	lblNombreFantasia.setOpaque(true);
	lblNombreFantasia.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));

	lblRubro.setBounds(new Rectangle(60, 115, 370, 30));
	lblRubro.setFont(new Font("Dialog", 1, 11));
	lblRubro.setHorizontalAlignment(SwingConstants.CENTER);
	lblRubro.setForeground(new Color(0, 0, 0));
	lblRubro.setBackground(new Color(231, 255, 181));
	lblRubro.setOpaque(true);
	lblRubro.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblRubroTitle.setText("Rubro:");
	lblRubroTitle.setBounds(new Rectangle(5, 115, 50, 30));
	lblRubroTitle.setFont(new Font("Dialog", 1, 14));
	lblRubroTitle.setHorizontalAlignment(SwingConstants.RIGHT);
	lblRubroTitle.setForeground(Color.black);

	lblPadron.setBounds(new Rectangle(290, 35, 140, 30));
	lblPadron.setFont(new Font("Dialog", 1, 11));
	lblPadron.setHorizontalAlignment(SwingConstants.CENTER);
	lblPadron.setForeground(new Color(255, 255, 181));
	lblPadron.setBackground(new Color(255, 90, 33));
	lblPadron.setOpaque(true);
	lblPadron.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblPadronTitle.setText("Nº Padrón:");
	lblPadronTitle.setBounds(new Rectangle(205, 35, 80, 30));
	lblPadronTitle.setFont(new Font("Dialog", 1, 14));
	lblPadronTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPadronTitle.setForeground(Color.black);
        jpDatosPrincipales.add(btnModifyFunction, null);
	jpDatosPrincipales.add(lblDescripcion, null);
	jpDatosPrincipales.add(lblDescripcionTitle, null);
	jpDatosPrincipales.add(lblShowData, null);
	jpDatosPrincipales.add(btnShowData, null);
	jpDatosPrincipales.add(lblPadronTitle, null);
	jpDatosPrincipales.add(lblPadron, null);
	jpDatosPrincipales.add(lblCatastro, null);
	jpDatosPrincipales.add(lblCatastroTitle, null);
	jpDatosPrincipales.add(lblNombreFantasia, null);
	jpDatosPrincipales.add(lblNombreFantasiaTitle, null);
	jpDatosPrincipales.add(lblRubro, null);
	jpDatosPrincipales.add(lblRubroTitle, null);
	jpTitle.add(lblTitle, BorderLayout.CENTER);
	jpDatosPrincipales.add(jpTitle, null);
	tabContainer.addTab("Datos Principales", jpDatosPrincipales);
	jpFichaTecnica.add(lblFichaTecnica, BorderLayout.CENTER);
	this.add(jpFichaTecnica, BorderLayout.NORTH);
	this.add(tabContainer, BorderLayout.WEST);
	this.add(jpData, BorderLayout.EAST);
	this.add(jpSeparator, BorderLayout.CENTER);
	jpData.setLayout(null);
	jpData.setBackground(new Color(144, 202, 220));
	jpData.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jpData.setPreferredSize(new Dimension(430, 1));
	jpData.setMinimumSize(new Dimension(430, 1));
	jpData.setMaximumSize(new Dimension(430, 32767));
	//this.setSize(notShowingDataWidth, 206);
	this.setSize(notShowingDataWidth, 265);
	//tabContainer.setBounds(new Rectangle(95, 290, 60, 40));
	 tabContainer.setBounds(new Rectangle(440, 229, 60, 40));
	tabContainer.setSize(new Dimension(440, 229));
	tabContainer.setPreferredSize(new Dimension(440, 229));
	tabContainer.setMaximumSize(new Dimension(440, 32767));
	tabContainer.setMinimumSize(new Dimension(440, 57));
	/*tabContainer.setSize(new Dimension(440, 182));
	tabContainer.setPreferredSize(new Dimension(445, 57));
	tabContainer.setMaximumSize(new Dimension(445, 32767));
	tabContainer.setMinimumSize(new Dimension(445, 57));*/
	btnShowData.setBounds(new Rectangle(410, 110, 25, 30));
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
	lblShowData.setBounds(new Rectangle(240, 110, 165, 30));
	lblShowData.setFont(new Font("Dialog", 1, 11));
	btnModifyFunction.setBounds(new Rectangle(5, 195, 35, 25));
	btnModifyFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnModifyFunction_actionPerformed(e);
		}
	    }
	);
        lblDescripcionTitle.setText("Descripción:");
	lblDescripcionTitle.setBounds(new Rectangle(5, 155, 90, 30));
	lblDescripcionTitle.setForeground(Color.black);
	lblDescripcionTitle.setFont(new Font("Dialog", 1, 14));
	lblDescripcion.setBounds(new Rectangle(95, 155, 335, 30));
	lblDescripcion.setFont(new Font("Dialog", 1, 11));
	lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
	lblDescripcion.setOpaque(true);
	lblDescripcion.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	lblDescripcion.setForeground(Color.black);
    }

    public void setContentObject(Object _contentObject) {
        super.setContentObject(_contentObject);
        if (_contentObject instanceof ESRIPoint) {
            loadComercio(((ESRIPoint)_contentObject).getIdPoint());
        }
    }

    /**2009-12-11(moraless)*/
    private void loadComercio(int _idPoint) {
	lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: ---");
	lblTitle.setText("Razón Social: ");
	lblPadron.setText("");
	lblCatastro.setText("");
	lblNombreFantasia.setText("");
	lblRubro.setText("");
        ResultSet _comercio = LibSQL.exFunction("gis.getcomerciorelevamiento_2009", _idPoint + ",'" + GaiaEnvironment.getScheme() + "'");
        
        try {
            if (_comercio.next()) {
		lblFichaTecnica.setText("Ficha Técnica - Identificador Nº: " + _idPoint);
		lblTitle.setText("Razón Social: " + _comercio.getString("razonsocial"));
		lblCatastro.setText(_comercio.getString("catastro"));
		lblPadron.setText(_comercio.getString("padron"));
		lblNombreFantasia.setText(_comercio.getString("nombrefantasia"));
		lblRubro.setText(_comercio.getString("rubro"));
		lblDescripcion.setText(_comercio.getString("descripcion"));
		idEncuestaComercial = _idPoint;
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    private void btnModifyFunction_actionPerformed(ActionEvent e) {
	FormCensoComercial2009 formComercios = new FormCensoComercial2009(idEncuestaComercial);
	formComercios.showForm();
    }
    
    
}
