package org.digitall.apps.cashflow.classes;

import java.util.Date;
import java.util.Vector;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Money;

public class UserGood extends Entity {

    private static final int entityType = EntityTypes.USE_GOOD;
    /**
     * @associates <{org.digitall.libs.logistic.Money}>
     */
    private Money money;
    /**
     * @associates <{org.digitall.libs.logistic.ConsumerGood}>
     */
    private Vector consumerGood;
    
    /**ATTRIBUTES*/
    private Date aquisitionDate;

    public UserGood(double _amount) {
        super(entityType);
        produce(_amount);
    }

    public boolean assignMoney(Money _money, double _amount) {
        return _money.doTransfer(money, _amount);
    }

    private boolean consumeEntity(Entity _entity, double _amount) {
        boolean _consume = false;
        if (doConsumption(_entity.getEntityType(), _amount)) {
            int index = getEntityIndex(_entity);
            if (index >= 0) {
                _consume = ((Entity)getContainerVector(_entity).elementAt(index)).consume(_amount);
            }
        }
        return _consume;
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

    private boolean haveEntity(Entity _entity) {
        return getContainerVector(_entity).contains(_entity);
    }

    public Vector getContainerVector(Entity _entity) {
        Vector _entitiesVector = new Vector();
        switch (_entity.getEntityType()) {
            case EntityTypes.CONSUMPTION_GOOD :
                _entitiesVector = consumerGood;
                break;
        }
        return _entitiesVector;
    }

    public boolean addEntity(Entity _entity) {
        boolean _add = false;
        if (!haveEntity(_entity)) {
            _add = getContainerVector(_entity).add(_entity);
        }
        return _add;
    }
    
    private boolean useEntity(Entity _entity, double _amount) {
        boolean _use = false;
        if (doUse(_entity.getEntityType(), _amount)) {
            int index = getEntityIndex(_entity);
            if (index >= 0) {
                _use = ((Entity)getContainerVector(_entity).elementAt(index)).use(_amount);
            }
        }
        return _use;
    }

    public void setAquisitionDate(Date _aquisitionDate) {
        aquisitionDate = _aquisitionDate;
    }

    public Date getAquisitionDate() {
        return aquisitionDate;
    }
}
