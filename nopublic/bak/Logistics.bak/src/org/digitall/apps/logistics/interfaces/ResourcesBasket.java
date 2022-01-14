package org.digitall.apps.logistics.interfaces;

import org.digitall.common.cashflow.classes.EntityTypes;

public class ResourcesBasket extends Basket {

    public ResourcesBasket() {
	super(EntityTypes.RESOURCE);
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
