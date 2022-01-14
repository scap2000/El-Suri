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
 * PopupAltaGrupos.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.sql.LibSQL;


public class PopupAltaGrupos extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miEmbarazos  = new JMenuItem("Embarazos");
    private JMenuItem miCeliacos  = new JMenuItem("Celíacos");
    private JMenuItem miMenores = new JMenuItem("Menores");
    private JMenuItem miPersonaEscasoRecurso = new JMenuItem("Escasos Recursos");
    private JMenuItem miTBC = new JMenuItem("TBC");
    private JMenuItem miExit = new JMenuItem("Salir");
    Coordinador coordinador;
    
    public PopupAltaGrupos(Coordinador _coordinador) {
        coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
        this.add(miCeliacos);
	this.add(miEmbarazos);
        this.add(miPersonaEscasoRecurso);
	this.add(miMenores);
        this.add(miTBC);
        this.add(miExit);
	miEmbarazos.setForeground(Color.white);
	miCeliacos.setForeground(Color.white);
	miMenores.setForeground(Color.white);
        miTBC.setForeground(Color.white);
        miPersonaEscasoRecurso.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miEmbarazos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickEmbarazos();
		}
	    });
	miCeliacos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickCeliacos();
		}
	    });
	miMenores.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickMenores();
		}
	    });
        miPersonaEscasoRecurso.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickPersonasEscasosRecursos();
                }
            });
        miTBC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickTBC();
                }
            });
        
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
        controlOpciones();
    }
    
    private void clickEmbarazos() {
        ExtendedInternalFrame panelEmbarazoContainer = new ExtendedInternalFrame("Alta en Grupo Embarazo");
        PanelEmbarazo panelEmbarazo = new PanelEmbarazo(coordinador); // se envía el coordinador
        panelEmbarazoContainer.setCentralPanel(panelEmbarazo);
        panelEmbarazoContainer.show();
    }
    
    private void clickCeliacos() {
        ExtendedInternalFrame panelCeliacosContainer = new ExtendedInternalFrame("Alta en Grupo Celíacos");
        PanelCeliacos panelCeliacos = new PanelCeliacos(coordinador); // se envía el coordinador
        panelCeliacosContainer.setCentralPanel(panelCeliacos);
        panelCeliacosContainer.show();
    }
    
    private void clickMenores() {
        ExtendedInternalFrame panelMenorContainer = new ExtendedInternalFrame("Alta en Grupo Menores");
        PanelMenor panelCeliacos = new PanelMenor(coordinador); // se envía el coordinador
        panelMenorContainer.setCentralPanel(panelCeliacos);
        panelMenorContainer.show();
    }
    
    private void clickTBC() {
        ExtendedInternalFrame panelTBCContainer = new ExtendedInternalFrame("Alta en Grupo TBC");
        PanelTBC panelTBC = new PanelTBC(coordinador); // se envía el coordinador
        panelTBCContainer.setCentralPanel(panelTBC);
        panelTBCContainer.show();
    }
    
    private void clickPersonasEscasosRecursos() {
        ExtendedInternalFrame panelPersonaEscasosRecursosContainer = new ExtendedInternalFrame("Alta en Grupo Escasos Recursos");
        PanelPersonaEscasosRecursos panelPersonaEscasosRecursos = new PanelPersonaEscasosRecursos(coordinador); // se envía el coordinador
        panelPersonaEscasosRecursosContainer.setCentralPanel(panelPersonaEscasosRecursos);
        panelPersonaEscasosRecursosContainer.show();
    }
    
    private void clickExit(MouseEvent e) {
	
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }

    private void controlOpciones() {
        if ((coordinador.getPersona().getSexo().equals("M"))||(coordinador.getPersona().getSexo().trim().equals(""))) {
            miEmbarazos.setEnabled(false);
        } else {
            miEmbarazos.setEnabled(true);
        }
        ResultSet resultado = LibSQL.exFunction("accionsocial.getallgruposporpersona",""+coordinador.getPersona().getIdPersona());
        try {
            while (resultado.next()) {
                if (resultado.getInt("idceliaco") != -1) {
                    miCeliacos.setEnabled(false);
                }
                if (resultado.getInt("idembarazo") != -1) {
                    miEmbarazos.setEnabled(false);
                }
                if (resultado.getInt("idmenor") != -1) {
                    miMenores.setEnabled(false);
                }
                if (resultado.getInt("idpersonaescasosrecursos") != -1) {
                    miPersonaEscasoRecurso.setEnabled(false);
                }
                if (resultado.getInt("idtbc") != -1) {
                    miTBC.setEnabled(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

