/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * GoodsProvider.java
 *
 * */
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
