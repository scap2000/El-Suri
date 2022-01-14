package org.digitall.apps.logistics.interfaces;

import org.digitall.common.cashflow.classes.EntityTypes;

public class HumanResourcesBasket extends Basket {

    public HumanResourcesBasket() {
	super(EntityTypes.PERSON);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setDragable();
	setDropable();
    }

}
