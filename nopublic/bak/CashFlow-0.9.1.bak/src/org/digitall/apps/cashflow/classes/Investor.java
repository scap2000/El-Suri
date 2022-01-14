package org.digitall.apps.cashflow.classes;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;


public class Investor extends Entity {

    private static final int entityType = EntityTypes.INVESTOR;
    /**
     * @associates <{org.digitall.libs.logistic.Investment}>
     */
    private Vector investment;

    public Investor() {
        super(entityType);
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

    public Vector getContainerVector(Entity _entity) {
        Vector _entitiesVector = new Vector();
        switch (_entity.getEntityType()) {
            case EntityTypes.INVESTMENT :
                _entitiesVector = investment;
                break;
        }
        return _entitiesVector;
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
}
