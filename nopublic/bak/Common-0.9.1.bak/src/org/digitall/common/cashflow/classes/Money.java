package org.digitall.common.cashflow.classes;

import org.digitall.common.cashflow.classes.Entity;

public class Money extends Entity {

    private static final int entityType = EntityTypes.MONEY;

    public Money(double _amount) {
	super(entityType);
	produce(_amount);
    }

    public Money() {
	super(entityType);
    }

    /**
     * Makes a money transferral between this (origin) and _destination (destination)
     * @param _destination
     * @param _amount
     * @return
     */
    public boolean doTransfer(Money _destination, double _amount) {
	boolean _transfer = false;
	if (_destination.doProduction(EntityTypes.MONEY, _amount) && (doConsumption(EntityTypes.MONEY, _amount))) {
	    _transfer = (_destination.produce(_amount) && this.consume(_amount));
	}
	return _transfer;
    }

}
