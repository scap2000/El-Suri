package org.digitall.common.resourcesrequests.classes.errclasses;

import org.digitall.lib.components.Advisor;

public abstract class ErrResourcesRequestsApproveMgmt {

    public static final int TFCOMPAREDATESMINOR = 1;
    
    public static void showErrorMessage(int _error){
	switch (_error)  {
	    case TFCOMPAREDATESMINOR: Advisor.messageBox("La Fecha de inicio debe ser anterior a la Fecha de fin","Mensaje");
		break;
	}
    }
    
}
