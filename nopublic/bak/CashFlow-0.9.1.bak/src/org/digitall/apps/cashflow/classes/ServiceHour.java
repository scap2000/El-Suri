package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Money;

public class ServiceHour extends Entity {

    private static final int entityType = EntityTypes.SERVICE_HOUR;
    /**
     * @associates <{org.digitall.libs.logistic.Money}>
     */
    private Money money;

    public ServiceHour(double _amount) {
        super(entityType);
        produce(_amount);
    }

    public boolean assignMoney(Money _money, double _amount) {
        return _money.doTransfer(money, _amount);
    }
}
