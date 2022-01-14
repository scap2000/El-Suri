package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Money;

public class Service extends Entity {

    private static final int entityType = EntityTypes.SERVICE;
    /**
     * @associates <{org.digitall.libs.logistic.Money}>
     */
    private Money money;
    /**
     * @associates <{org.digitall.libs.logistic.ServiceHours}>
     */
    private ServiceHour serviceHours;

    public Service(double _amount) {
        super(entityType);
        produceServiceHours(_amount);
    }

    private boolean produceServiceHours(double _amount) {
        boolean _produce = false;
        if (doProduction(EntityTypes.SERVICE_HOUR, _amount)) {
            serviceHours = new ServiceHour(_amount);
        }
        return _produce;
    }

    private boolean consumeMoney(double _amount) {
        boolean _consume = false;
        if (doConsumption(EntityTypes.MONEY, _amount)) {
            _consume = money.consume(_amount);
        }
        return _consume;
    }

    public boolean assignMoney(Money _money, double _amount) {
        return _money.doTransfer(money, _amount);
    }
}
