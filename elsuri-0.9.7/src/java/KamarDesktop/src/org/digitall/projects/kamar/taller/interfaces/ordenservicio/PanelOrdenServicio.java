/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * PanelOrdenServicio.java
 *
 * */
package org.digitall.projects.kamar.taller.interfaces.ordenservicio;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.projects.kamar.taller.classes.OrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.ArticulosMain;
import org.digitall.projects.kamar.taller.interfaces.ServiciosMain;
import org.digitall.projects.kamar.taller.interfaces.TareasMain;

public class PanelOrdenServicio extends BasicTabContainer {

    private PanelDatosPrincipales datosPrincipales = new PanelDatosPrincipales();
    private PanelDatosServicio datosServicio = new PanelDatosServicio();
    private OrdenServicio ordenServicio;
	
    public PanelOrdenServicio() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setOrdenServicio(OrdenServicio _ordenServicio) {
	ordenServicio = _ordenServicio;
	initOrdenServicio();
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(759, 543));
	this.setPreferredSize(new Dimension(800, 460));
	addTab("(F5) Datos Principales", datosPrincipales);
	addTab("(F5) Servicio", datosServicio);
    }
    
    private void initOrdenServicio() {
	ordenServicio.retrieveData();
	datosPrincipales.requestFocus();
	datosPrincipales.setOrdenServicio(ordenServicio);
	datosServicio.setOrdenServicio(ordenServicio);
	if (ordenServicio.getIdOrdenServicio() != -1) {
	    setSelectedTab(1);
	}
	registerKeyboardAction(
	    new ActionListener()  {
		public void actionPerformed(ActionEvent actionEvent) {
		    if (getSelectedTab() == 1) {
			setSelectedTab(0);
		    } else {
		        setSelectedTab(1);			
		    }
		}
	    },
	    "Tab",
	    KeyStroke.getKeyStroke(KeyEvent.VK_F5 , 0),
	    JComponent.WHEN_IN_FOCUSED_WINDOW 
	);
    }

    public void changeSelectedTab() {
	
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Orden de Servicio");
    }
    
    public PanelDatosPrincipales getDatosPrincipales() {
	return datosPrincipales;
    }
    
}
