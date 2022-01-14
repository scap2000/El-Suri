package org.digitall.common.cashflow.classes;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.environment.Environment;

public abstract class CashFlowEnvironment {

    public static CostsCentre costsCentre;
    
    public static void setCostsCentre(CostsCentre _costsCentre) {
	costsCentre = _costsCentre;
    }

    public static CostsCentre getCostsCentre() {
	if (costsCentre == null){
	    costsCentre = new CostsCentre(Environment.sessionUser);	    
	}
	
	return costsCentre;
    }

}
