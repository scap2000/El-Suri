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
 * DNDCoordinator.java
 *
 * */
package org.digitall.apps.logistics.interfaces;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.lib.environment.Environment;

public abstract class DNDCoordinator {

    private static int dragSourceType = -1;
    private static int dropTargetType = -1;
    private static LFrameContainer humanResourcesContainer = new LFrameContainer("Recursos Humanos Asignados");
    private static AssignedHumanResourcesList assignedHumanResourcesList = new AssignedHumanResourcesList();
    private static LFrameContainer materialResourcesContainer = new LFrameContainer("Recursos Materiales Asignados");
    private static AssignedMaterialResourcesList assignedMaterialResourcesList = new AssignedMaterialResourcesList();

    public static void setDragSourceType(int _dragSourceType) {
	dragSourceType = _dragSourceType;
    }

    public static int getDragSourceType() {
	return dragSourceType;
    }

    public static void setDropTargetType(int _dropTargetType) {
	dropTargetType = _dropTargetType;
    }

    public static void completeDrop(int _dropTargetType) {
	dropTargetType = _dropTargetType;
	switch (dropTargetType) {
	    case EntityTypes.COSTS_CENTRE:
		switch (dragSourceType) {
		    case EntityTypes.PERSON:
			//TRANSFERENCIA DE PERSONAS A CENTROS DE COSTOS
			    if (Environment.mainDesktop.getIndexOf(humanResourcesContainer) == -1){
				Environment.mainDesktop.add(humanResourcesContainer);
			    }
			    assignedHumanResourcesList.setParentContainer(humanResourcesContainer);
			    humanResourcesContainer.setPanel(assignedHumanResourcesList);
			    humanResourcesContainer.setVisible(true);
			break;
		    case EntityTypes.RESOURCE:
			//TRANSFERENCIA DE RECURSOS A CENTROS DE COSTOS
			 if (Environment.mainDesktop.getIndexOf(materialResourcesContainer) == -1){
			     Environment.mainDesktop.add(materialResourcesContainer);
			 }
			 assignedMaterialResourcesList.setParentContainer(materialResourcesContainer);
			 materialResourcesContainer.setPanel(assignedMaterialResourcesList);
			 materialResourcesContainer.setVisible(true);
			break;
		}
	    case EntityTypes.RESOURCE:
	        switch (dragSourceType) {
	            case EntityTypes.PERSON:
	                //TRANSFERENCIA DE PERSONAS A RECURSOS - IDEM: RECURSOS A PERSONAS
	                break;
	            case EntityTypes.RESOURCE:
	                //TRANSFERENCIA DE RECURSOS A RECURSOS - PARA HACER COMPUESTOS (COMPOSITE)
	                break;
	        }
	    case EntityTypes.PERSON:
	        switch (dragSourceType) {
	            case EntityTypes.RESOURCE:
	                //TRANSFERENCIA DE RECURSOS A PERSONAS - IDEM: PERSONAS A RECURSOS
	                break;
	        }
	    default: 
		break;
	}
    }

    public static int getDropTargetType() {
	return dropTargetType;
    }
}
