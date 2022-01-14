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
