package org.digitall.apps.resourcesrequests.interfaces.resourcesrequests.classes.errclasses;


import org.digitall.lib.components.Advisor;

public abstract class ErrorsResourcesRequestsMgmt {

    /* ENCABEZADO */
    public static final int EMPTY_NUMBER = 1;
    public static final int EMPTY_DATE = 2;
    public static final int INVALID_DATE = 3;
    public static final int EMPTY_SOLICITOR = 4;
    public static final int EMPTY_COSTSCENTRE = 5;

    /* DETALLE */    
    public static final int EMPTY_RESOURCE = 6;
    public static final int EMPTY_BRAND = 7;
    public static final int INVALID_QTY = 8;
    public static final int EMPTY_DETAIL = 9;
    public static final int EMPTY_ITEM = 10;
    public static final int DUPLICATE_DETAIL = 11;
    
    public static void showErrorMessage(int _error) {
	switch (_error)  {
	    case EMPTY_NUMBER:
		Advisor.messageBox("El campo \"Nº\" no debe estar vacío","Error");
		break;
	    case EMPTY_DATE:
		Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Error");
		break;
	    case INVALID_DATE:
		Advisor.messageBox("La Fecha de Solicitud no debe ser superior a la Fecha Actual","Error");
	        break;
	    case EMPTY_SOLICITOR:
		Advisor.messageBox("No se ha seleccionado un Solicitante","Error");
	        break;
	    case EMPTY_COSTSCENTRE:
		Advisor.messageBox("No se ha seleccionado un Centro de Costos","Error");
	        break;
	    case EMPTY_RESOURCE:
		Advisor.messageBox("No se ha seleccionado ningún Material", "Error");
		break;
	    case EMPTY_BRAND:
		Advisor.messageBox("No se ha seleccionado una Marca", "Error");
		break;
	    case INVALID_QTY:
	        Advisor.messageBox("La cantidad debe ser mayor que cero", "Error");
		break;
	    case EMPTY_DETAIL:
		Advisor.messageBox("No se puede grabar un Pedido de Materiales sin detalle", "Error");
		break;
	    case EMPTY_ITEM:
		Advisor.messageBox("Debe seleccionar un material para quitar", "Error");
		break;
	    case DUPLICATE_DETAIL:
		Advisor.messageBox("El Material ya se encuentra en el Detalle", "Error");
		break;
	    default:
		Advisor.messageBox("Error no especificado en Pedido de Materiales", "Error");
		break;
	}
    }
    
}
