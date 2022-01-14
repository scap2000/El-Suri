package org.digitall.apps.logistics.interfaces;

import org.digitall.common.cashflow.classes.EntityTypes;

public class CostCentreBasket extends Basket {

    public CostCentreBasket() {
	super(EntityTypes.COSTS_CENTRE);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setDropable();
    }

}
