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
 * TaxesMain092.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class TaxesMain092 extends BasicPrimitivePanel {

    private Coordinador coordinador = new Coordinador();
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelSur = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"Contribuyente",false);
    private TFInput tfImpuesto = new TFInput(DataTypes.STRING,"Impuesto",false);
    private TFInput tfCatastroDominio = new TFInput(DataTypes.STRING,"Catastro/Dominio",false);
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel panelSurEste = new BasicPanel();
    private Color colorAlerta = Color.red;
    private Timer timerAlerta;
    private BasicPanel panelAlerta;
    private BasicTextArea taDescripcionProblema = new BasicTextArea();

    public TaxesMain092() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        panelAlerta = new BasicPanel() {
        
            @Override
            public void paint(Graphics _graphics) {
                _graphics.setColor(colorAlerta);
                _graphics.fillOval(15, 02, 35, 35);
                _graphics.setColor(Color.black);
                _graphics.drawOval(15, 02, 35, 35);
                super.paintComponents(_graphics);
            }
        };
        timerAlerta = new Timer(200, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                colorAlerta= (colorAlerta == Color.red?Color.yellow:Color.red);
                panelAlerta.repaint();
                panelAlerta.getParent().repaint();
            }
            
        });
	this.setLayout( null );
        panelAlerta.setBounds(new Rectangle(0, 5, 605, 40));
        taDescripcionProblema.setBounds(new Rectangle(70, 0, 535, 35));
        taDescripcionProblema.setVisible(false);
        taDescripcionProblema.setEditable(false);
        panelAlerta.setLayout(null);
        panelAlerta.setVisible(false);
	panelContenedor.setLayout( borderLayout2 );
	this.setSize(new Dimension(845, 590));
	panelContenedor.setSize(new Dimension(845, 590));
	panelNorte.setBounds(new Rectangle(0, 0, 845, 65));
	panelNorte.setPreferredSize(new Dimension(845, 65));
	panelNorte.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelNorte.setLayout(null);
	panelSur.setBounds(new Rectangle(0, 525, 845, 50));
	panelSur.setPreferredSize(new Dimension(845, 50));
	panelSur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelAlerta.add(taDescripcionProblema, null);
        panelSur.add(panelAlerta, null);
	panelSur.setLayout(null);
	panelCentral.setLayout(null);
	panelCentral.setSize(new Dimension(845, 475));
	panelCentral.setPreferredSize(new Dimension(845, 475));
	panelCentral.setBounds(new Rectangle(0, 65, 845, 475));
	tfContribuyente.setBounds(new Rectangle(15, 5, 275, 35));
	tfContribuyente.setEditable(false);
	tfContribuyente.setHorizontalAlignment(TFInput.CENTER);
	tfImpuesto.setBounds(new Rectangle(320, 5, 275, 35));
	tfImpuesto.setEditable(false);
	tfImpuesto.setHorizontalAlignment(TFInput.CENTER);
	tfCatastroDominio.setBounds(new Rectangle(630, 5, 140, 35));
	tfCatastroDominio.setEditable(false);
	tfCatastroDominio.setHorizontalAlignment(TFInput.CENTER);
	btnNext.setBounds(new Rectangle(190, 10, 30, 25));
	btnNext.setSize(new Dimension(30, 25));
	btnNext.setFont(new Font("Dialog", 1, 11));
        btnNext.setToolTipText("Siguiente");
	btnNext.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnNext_actionPerformed(e);
		}
	    }
	);
        btnBack.setToolTipText("Atras");
	btnBack.setBounds(new Rectangle(105, 10, 30, 25));
	btnBack.setSize(new Dimension(30, 25));
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnBack_actionPerformed(e);
		}
	    }
	);
	panelSurEste.setBounds(new Rectangle(610, 1, 235, 48));
	panelSurEste.setBackground(new Color(185, 95, 0));
	panelSurEste.setLayout(null);
	panelSurEste.setSize(new Dimension(235, 48));
	btnFirst.setBounds(new Rectangle(20, 10, 30, 25));
	btnFirst.setSize(new Dimension(30, 25));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
	panelNorte.add(tfCatastroDominio, null);
	panelNorte.add(tfImpuesto, null);
	panelNorte.add(tfContribuyente, null);
	panelSurEste.add(btnNext, null);
	panelSurEste.add(btnBack, null);
	panelSurEste.add(btnFirst, null);
	panelContenedor.add(panelNorte, borderLayout2.NORTH);
	panelContenedor.add(panelCentral, borderLayout2.CENTER);
	panelSur.add(panelSurEste, null);
	panelContenedor.add(panelSur, borderLayout2.SOUTH);
	this.add(panelContenedor, null);
	coordinador.setTaxesMgmt(this);
	coordinador.start();
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
	 coordinador.siguiente();
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
	coordinador.atras();
    }
    
    private void btnFirst_actionPerformed(ActionEvent e) {
	coordinador.inicio();
    }
    
    /**
     * Métodos públicos
     */
    
    /**
     * Queda visible el panel que se pasa por parámetro además de cargarse con los datos necesarios obtenido de los objetos del coordinador
     * @param _panel
     */
    public void setPanelCental(BasicPanel _panel){
	_panel.setVisible(true);
    }
    
    public void addPanelCental(BasicPanel _panel){
	panelCentral.add(_panel, null);
    }

    public void setCoordinador(Coordinador _coordinador) {
	coordinador = _coordinador;
    }

    public Coordinador getCoordinador() {
	return coordinador;
    }

    public TFInput getTfContribuyente() {
	return tfContribuyente;
    }

    public TFInput getTfImpuesto() {
	return tfImpuesto;
    }

    public TFInput getTfCatastroDominio() {
	return tfCatastroDominio;
    }

    public void setBtnNext(AssignButton btnNext) {
	this.btnNext = btnNext;
    }

    public AssignButton getBtnNext() {
	return btnNext;
    }

    public void setBtnFirst(FirstGridButton btnFirst) {
	this.btnFirst = btnFirst;
    }

    public FirstGridButton getBtnFirst() {
	return btnFirst;
    }

    public void setBtnBack(UnAssignButton btnBack) {
	this.btnBack = btnBack;
    }

    public UnAssignButton getBtnBack() {
	return btnBack;
    }
    
    //método que muestra información del problema del CATASTRO seleccionado
    public void mostrarProblema(){
        panelAlerta.setVisible(false);
        timerAlerta.stop();
        taDescripcionProblema.setVisible(false);
        if (coordinador.getBien().esCatastro()) {
            ResultSet result = LibSQL.exFunction("taxes.getUltimoProblemaxCatastro", coordinador.getBien().getIdBien());
            try {
                if (result.next()) {
                    if (result.getBoolean("esproblema")) {
                        taDescripcionProblema.setText(result.getString("problema"));
                        panelAlerta.setVisible(true);
                        timerAlerta.start();
                        timerAlerta.setDelay(400);
                        taDescripcionProblema.setVisible(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }    
        }
    }
}
