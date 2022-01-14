package org.digitall.apps.cashflow.classes;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Money;

public class GoodsProvider extends Entity {

    private static final int entityType = EntityTypes.GOODS_PROVIDER;
    /**
     * @associates <{org.digitall.libs.logistic.Money}>
     */
    private Money money;
    /**
     * @associates <{org.digitall.libs.logistic.UserGood}>
     */
    private Vector userGood;
    /**
     * @associates <{org.digitall.libs.logistic.ConsumerGood}>
     */
    private Vector consumerGood;

    public GoodsProvider() {
        super(entityType);
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

    private boolean requestMoney(double _amount) {
        boolean _request = false;
        if (doRequest(EntityTypes.MONEY, _amount)) {
            _request = money.request(_amount);
        }
        return _request;
    }

    public Vector getContainerVector(Entity _entity) {
        Vector _entitiesVector = new Vector();
        switch (_entity.getEntityType()) {
            case EntityTypes.USE_GOOD :
                _entitiesVector = userGood;
                break;
            case EntityTypes.CONSUMPTION_GOOD :
                _entitiesVector = consumerGood;
                break;
        }
        return _entitiesVector;
    }

    private boolean produceEntity(Entity _entity, double _amount) {
        boolean _produce = false;
        if (doConsumption(_entity.getEntityType(), _amount)) {
            int index = getEntityIndex(_entity);
            if (index >= 0) {
                _produce = ((Entity)getContainerVector(_entity).elementAt(index)).produce(_amount);
            }
        }
        return _produce;
    }

    /**
     * Returns the index of the Entity in any Vector
     * @param _entity
     * @return
     */
    private int getEntityIndex(Entity _entity) {
        int index = -1;
        if (haveEntity(_entity)) {
            Vector _entitiesVector = getContainerVector(_entity);
            int i = 0;
            boolean found = false;
            while (i < _entitiesVector.size() && !found) {
                if (_entity == (Entity)_entitiesVector.elementAt(i)) {
                    index = i;
                    found = true;
                }
                i++;
            }
        }
        return index;
    }

    public boolean addEntity(Entity _entity) {
        boolean _add = false;
        if (!haveEntity(_entity)) {
            _add = getContainerVector(_entity).add(_entity);
        }
        return _add;
    }

    private boolean haveEntity(Entity _entity) {
        return getContainerVector(_entity).contains(_entity);
    }
}
