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
 * AccionSocialPrincipal.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class AccionSocialPrincipal extends BasicPrimitivePanel {
    
    private BasicPanel jpNorte = new BasicPanel();
    private BasicPanel jpCentral = new BasicPanel();
    private BasicPanel jpSur = new BasicPanel();
    private BasicPanel jpSurEste = new BasicPanel();
    private BasicPanel jpContenedor = new BasicPanel();
    
    private TFInput tfBeneficiario = new TFInput(DataTypes.STRING,"Apellido y Nombres",false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING,"CUIL.",false);

    private AcceptButton btnRecibirRecursos = new AcceptButton();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    
    private Coordinador coordinador = new Coordinador();
    private RecepcionarPrestacionesMgmt recepcionarPrestacionesMgmt;

    public AccionSocialPrincipal() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	jpContenedor.setLayout( borderLayout2 );
	this.setSize(new Dimension(845, 590));
	jpContenedor.setSize(new Dimension(845, 590));
	jpNorte.setBounds(new Rectangle(0, 0, 845, 65));
	jpNorte.setPreferredSize(new Dimension(845, 65));
	jpNorte.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpNorte.setLayout(null);
	jpSur.setBounds(new Rectangle(0, 525, 845, 50));
	jpSur.setPreferredSize(new Dimension(845, 50));
	jpSur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpSur.setLayout(null);
	jpCentral.setLayout(null);
	jpCentral.setSize(new Dimension(845, 475));
	jpCentral.setPreferredSize(new Dimension(845, 475));
	jpCentral.setBounds(new Rectangle(0, 65, 845, 475));
	tfBeneficiario.setBounds(new Rectangle(145, 5, 400, 35));
	tfBeneficiario.setEditable(false);
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
	jpSurEste.setBounds(new Rectangle(610, 1, 235, 48));
	jpSurEste.setBackground(new Color(185, 95, 0));
	jpSurEste.setLayout(null);
	jpSurEste.setSize(new Dimension(235, 48));
	btnFirst.setBounds(new Rectangle(20, 10, 30, 25));
	btnFirst.setSize(new Dimension(30, 25));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
        jpNorte.add(tfCuil, null);
        jpNorte.add(tfBeneficiario, null);
        jpSurEste.add(btnNext, null);
	jpSurEste.add(btnBack, null);
	jpSurEste.add(btnFirst, null);
	jpContenedor.add(jpNorte, borderLayout2.NORTH);
	jpContenedor.add(jpCentral, borderLayout2.CENTER);
	jpSur.add(jpSurEste, null);
	jpContenedor.add(jpSur, borderLayout2.SOUTH);
	this.add(jpContenedor, null);
        coordinador.setAccionSocialMgmt(this);
        tfCuil.setBounds(new Rectangle(575, 5, 120, 35));
        coordinador.start();
        tfCuil.setEditable(false);
        btnRecibirRecursos.setText("Recibir Recursos");
        btnRecibirRecursos.setToolTipText("Recibir Recursos");
        btnRecibirRecursos.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnRecibirRecursos_actionPerformed(e);
                                }

                            }
        );
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().getGeneralButtons().addButton(btnRecibirRecursos);
        getParentInternalFrame().setInfo("Asistente para la Administración de Acción Social, siga las instrucciones del panel de ayuda");
    }
    
    private void btnRecibirRecursos_actionPerformed(ActionEvent e) {
        recepcionarPrestacionesMgmt = new RecepcionarPrestacionesMgmt();
        ExtendedInternalFrame recepcionarPrestacionesMgmtContainer = new ExtendedInternalFrame("Recepcionar Recursos");
        recepcionarPrestacionesMgmtContainer.setCentralPanel(recepcionarPrestacionesMgmt);
        recepcionarPrestacionesMgmt.load();
        recepcionarPrestacionesMgmtContainer.show();
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
    public void setPanelCental(BasicPanel _panel) {
	_panel.setVisible(true);
    }
    
    public void addPanelCental(BasicPanel _panel) {
	jpCentral.add(_panel, null);
    }

    public TFInput gettfBeneficiario() {
	return tfBeneficiario;
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

    public void settfCuil(TFInput tfCuil) {
        this.tfCuil = tfCuil;
    }

    public TFInput gettfCuil() {
        return tfCuil;
    }
}
