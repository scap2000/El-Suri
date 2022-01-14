package org.digitall.apps.cashflow.classes;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;

public class Task extends Entity {

    private static final int entityType = EntityTypes.TASK;
    /**
     * @associates <{org.digitall.libs.logistic.ServiceHour}>
     */
    private Vector serviceHour;
    /**
     * @associates <{org.digitall.libs.logistic.ManHour}>
     */
    private Vector manHour;
    /**
     * @associates <{org.digitall.libs.logistic.ConsumerGood}>
     */
    private Vector consumerGood;
    /**
     * @associates <{org.digitall.libs.logistic.UserGood}>
     */
    private Vector userGood;

    public Task() {
        super(entityType);
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

    public boolean addEntity(Entity _entity) {
        boolean _add = false;
        if (!haveEntity(_entity)) {
            _add = getContainerVector(_entity).add(_entity);
        }
        return _add;
    }

    private Vector getContainerVector(Entity _entity) {
        Vector _entitiesVector = new Vector();
        switch (_entity.getEntityType()) {
            case EntityTypes.SERVICE_HOUR :
                _entitiesVector = serviceHour;
                break;
            case EntityTypes.MAN_HOUR :
                _entitiesVector = manHour;
                break;
            case EntityTypes.CONSUMPTION_GOOD :
                _entitiesVector = consumerGood;
                break;
            case EntityTypes.USE_GOOD :
                _entitiesVector = userGood;
                break;
        }
        return _entitiesVector;
    }
}
