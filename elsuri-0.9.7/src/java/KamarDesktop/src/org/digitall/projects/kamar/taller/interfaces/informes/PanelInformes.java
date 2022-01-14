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

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteArticulosNoVinculados;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteComprobantes;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteMecanicosPorOrdenServicio;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteOrdenesServicio;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteServiciosEjecutados;
import org.digitall.projects.kamar.taller.interfaces.informes.PanelReporteTareasEjecutadas;

public class PanelInformes extends BasicTabContainer {

    private PanelReporteOrdenesServicio reporteOrdenesServicio = new PanelReporteOrdenesServicio();
    private PanelReporteComprobantes reporteComprobantes = new PanelReporteComprobantes();
    private PanelReporteArticulosNoVinculados reporteArticulosNoVinculados = new PanelReporteArticulosNoVinculados();
    private PanelReporteServiciosEjecutados reporteServiciosEjecutados = new PanelReporteServiciosEjecutados();
    private PanelReporteTareasEjecutadas reporteTareasEjecutadas = new PanelReporteTareasEjecutadas();
    private PanelReporteMecanicosPorOrdenServicio reporteMecanicosPorOrdenServicio = new PanelReporteMecanicosPorOrdenServicio();
	
    public PanelInformes() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(829, 325));
	this.setPreferredSize(new Dimension(800, 460));
	addTab("Órdenes de Servicio", reporteOrdenesServicio);
	addTab("Comprobantes", reporteComprobantes);
	addTab("Artículos no vinculados", reporteArticulosNoVinculados);
	addTab("Servicios Ejecutados", reporteServiciosEjecutados);
	addTab("Tareas Ejecutadas", reporteTareasEjecutadas);
	addTab("Mecánicos", reporteMecanicosPorOrdenServicio);
    }
    
    public void changeSelectedTab() {
	
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Informes");
    }
        
}
