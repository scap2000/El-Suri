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
 * Entity.java
 *
 * */
package org.digitall.common.cashflow.classes;

public class Entity {

    private int entityType = -1;
    /** Datos referentes al consumo */
    private boolean consumer = false;
    //Es consumidor?
    private boolean consumable = false;
    //Es consumible?
    private boolean consumed = false;
    //Ha sido consumido alguna vez?
    //Sï¿½lo si es Consumible: consumable = true;
    private boolean consuming = false;
    //Estï¿½ consumiendo?
    //Sï¿½lo si es Consumidor: consumer = true;
    private boolean beingConsumed = false;
    //Estï¿½ siendo consumido?
    //Sï¿½lo si es Consumible: consumable = true;
    /** Datos referentes a la producciï¿½n */
    private boolean producer = false;
    //Es productor?
    private boolean produceable = false;
    //Es producible?
    private boolean produced = false;
    //Ha sido producido alguna vez?
    //Sï¿½lo si es Producible: produceable = true;
    private boolean producing = false;
    //Estï¿½ produciendo?
    //Sï¿½lo si es Productor: producer = true;
    private boolean beingProduced = false;
    //Estï¿½ siendo producido?
    //Sï¿½lo si es Producible: produceable = true;
    /** Datos referentes a solicitudes */
    private boolean requester = false;
    //Es solicitante?
    private boolean requestable = false;
    //Es solicitable?
    private boolean requested = false;
    //Ha sido solicitado alguna vez?
    //Sï¿½lo si es Solicitable: requestable = true;
    private boolean requesting = false;
    //Estï¿½ solicitando?
    //Sï¿½lo si es Solicitante: requester = true;
    private boolean beingRequested = false;
    //Estï¿½ siendo solicitado?
    //Sï¿½lo si es Solicitable: requestable = true;
    /** Otras propiedades */
    private boolean divisible = false;
    //Es divisible?
    private boolean divided = false;
    //Ha sido dividido alguna vez?
    //Sï¿½lo si es Divisible: divisible = true;
    private boolean beingDivided = false;
    //Estï¿½ siendo dividido?
    //Sï¿½lo si es Divisible: divisible = true;
    private boolean groupable = false;
    //Es agrupable?
    private boolean grouped = false;
    //Ha sido agrupado alguna vez?
    //Sï¿½lo si es Agrupable: groupable = true;
    private boolean beingGrouped = false;
    //Estï¿½ siendo agrupado?
    //Sï¿½lo si es Agrupable: groupable = true;
    private boolean measurable = false;
    //Es medible?
    private boolean measured = false;
    //Ha sido medido alguna vez?
    //Sï¿½lo si es Medible: measurable = true;
    private boolean beingMeasured = false;
    //Estï¿½ siendo medido?
    //Sï¿½lo si es Medible: measurable = true;
    private boolean user = false;
    //Es usuario?
    private boolean usable = false;
    //Es usable?
    private boolean used = false;
    //Ha sido usado alguna vez?
    private boolean using = false;
    //Estï¿½ usando?
    //Sï¿½lo si es Usable: usable = true;
    private boolean beingUsed = false;
    //Estï¿½ siendo usado?
    //Sï¿½lo si es Usable: usable = true;
    private boolean wearable = false;
    //Es desgastable?
    private boolean wear = false;
    //Ha sido desgastado alguna vez?
    private boolean beingWear = false;
    //Estï¿½ siendo desgastado?
    //Sï¿½lo si es Desgastable: wearable = true;
    private boolean acquirableByContract = false;
    //Es adquirible por contrato?
    private boolean acquiredByContract = false;
    //Ha sido adquirido por contrato alguna vez?
    //Sï¿½lo si es Adquirible por contrato: acquirableByContract = true;
    private boolean beingAcquiredByContract = false;
    //Estï¿½ siendo adquirido por contrato?
    //Sï¿½lo si es Adquirible por contrato: acquirableByContract = true;
    private boolean acquirableByShop = false;
    //Es adquirible por compra?
    private boolean acquiredByShop = false;
    //Ha sido adquirido por compra alguna vez?
    //Sï¿½lo si es Adquirible por compra: acquirableByShop = true;
    private boolean beingAcquiredByShop = false;
    //Estï¿½ siendo adquirido por compra?
    //Sï¿½lo si es Adquirible por compra: acquirableByShop = true;
    private boolean acquirableByDonation = false;
    //Es adquirible por donaciï¿½n?
    private boolean acquiredByDonation = false;
    //Ha sido adquirido por donaciï¿½n alguna vez?
    //Sï¿½lo si es Adquirible por Donaciï¿½n: acquirableByDonation = true;
    private boolean beingAcquiredByDonation = false;
    //Estï¿½ siendo adquirido por donaciï¿½n?
    //Sï¿½lo si es Adquirible por Donaciï¿½n: acquirableByDonation = true;
    private boolean acquirableByBorrow = false;
    //Es adquirible por prï¿½stamo?
    private boolean acquiredByBorrow = false;
    //Ha sido adquirido por prï¿½stamo alguna vez?
    //Sï¿½lo si es Adquirible por Prï¿½stamo: acquirableByBorrow = true;
    private boolean beingAcquiredByBorrow = false;
    //Estï¿½ siendo adquirido por prï¿½stamo?
    //Sï¿½lo si es Adquirible por Prï¿½stamo: acquirableByBorrow = true;
    private boolean evaluator = false;
    //Es evaluador?
    private boolean evaluative = false;
    //Es evaluable?
    private boolean evaluating = false;
    //Estï¿½ evaluando?
    //Sï¿½lo si es Evaluable: evaluative = true;
    private boolean evaluated = false;
    //Ha sido evaluado alguna vez?
    //Sï¿½lo si es Evaluable: evaluative = true;
    private boolean beingEvaluated = false;
    //Estï¿½ siendo evaluado?
    //Sï¿½lo si es Evaluable: evaluative = true;
    private boolean periodic = false;
    //Es periï¿½dico?
    private boolean amortizable = false;
    //Es amortizable?
    private boolean amortized = false;
    //Ha sido amortizado alguna vez?
    //Sï¿½lo si es Amortizable: amortizable = true;
    private boolean beingAmortized = false;
    //Estï¿½ siendo amortizado?
    //Sï¿½lo si es Amortizable: amortizable = true;
    private boolean distinguishable = false;
    //Es distinguible?
    private boolean perpetual = false;
    //Es perpetuo?
    private boolean transferableByContract = false;
    //Es transferible por contrato?
    private boolean trasferredByContract = false;
    //Ha sido transferido por contrato alguna vez?
    //Sï¿½lo si es Transferible por Contrato: transferableByContract = true;
    private boolean beingTransferredByContract = false;
    //Estï¿½ siendo transferido por contrato?
    //Sï¿½lo si es Transferible por Contrato: transferableByContract = true;
    private boolean transferableBySell = false;
    //Es transferible por venta?
    private boolean trasferredBySell = false;
    //Ha sido transferido por venta alguna vez?
    //Sï¿½lo si es Transferible por Venta: transferableBySell = true;
    private boolean beingTransferredBySell = false;
    //Estï¿½ siendo transferido por venta?
    //Sï¿½lo si es Transferible por Venta: transferableBySell = true;
    private boolean transferableByDonation = false;
    //Es transferible por donaciï¿½n?
    private boolean transferredByDonation = false;
    //Ha sido transferido por donaciï¿½n alguna vez?
    //Sï¿½lo si es Transferible por Donaciï¿½n: transferableByDonation = true;
    private boolean beingTransferredByDonation = false;
    //Estï¿½ siendo transferido por donaciï¿½n?
    //Sï¿½lo si es Transferible por Donaciï¿½n: transferableByDonation = true;
    private boolean transferableByBorrow = false;
    //Es transferible por prï¿½stamo?
    private boolean trasferredByBorrow = false;
    //Ha sido transferido por prï¿½stamo alguna vez?
    //Sï¿½lo si es Transferible por Prï¿½stamo: transferableByBorrow = true;
    private boolean beingTransferredByBorrow = false;
    //Estï¿½ siendo transferido por prï¿½stamo?
    //Sï¿½lo si es Transferible por Prï¿½stamo: transferableByBorrow = true;
    /** Stock handling variables */
    private boolean initiated = false;
    private double stock = 0;
    private double minStock = 0;
    private double maxStock = 0;
    /** Consuming variables */
    private double minConsumptionLimit = 0;
    private double maxConsumptionLimit = 0;
    private double minConsumptionLimitAtOnce = 0;
    private double maxConsumptionLimitAtOnce = 0;
    private double totalConsumed = 0;
    /** Producting variables */
    private double minProductionLimit = 0;
    private double maxProductionLimit = 0;
    private double minProductionLimitAtOnce = 0;
    private double maxProductionLimitAtOnce = 0;
    private double totalProduced = 0;
    /** Requesting variables */
    private double minRequestLimit = 0;
    private double maxRequestLimit = 0;
    private double minRequestLimitAtOnce = 0;
    private double maxRequestLimitAtOnce = 0;
    private double totalRequested = 0;
    /** Using variables */
    private double minUsageLimit = 0;
    private double maxUsageLimit = 0;
    private double minUsageLimitAtOnce = 0;
    private double maxUsageLimitAtOnce = 0;
    private double totalUsed = 0;
    /** Evaluation variables */
    private double minEvaluationLimit = 0;
    private double maxEvaluationLimit = 0;
    private double minEvaluationLimitAtOnce = 0;
    private double maxEvaluationLimitAtOnce = 0;
    private double totalEvaluation = 0;

    /**
     * Default Constructor
     * @param _entityType
     */
    public Entity(int _entityType) {
	setEntityType(_entityType);
	switch (_entityType) {
		/** Entity's behavior definition */
	    case EntityTypes.INVESTOR :
		producer = true;
		evaluator = true;
		break;
	    case EntityTypes.INVESTMENT :
		consumable = true;
		producer = true;
		produceable = true;
		measurable = true;
		break;
	    case EntityTypes.BUDGET :
		consumable = true;
		producer = true;
		divisible = true;
		periodic = true;
		break;
	    case EntityTypes.MONEY :
		consumable = true;
		produceable = true;
		measurable = true;
		usable = true;
		break;
	    case EntityTypes.COSTS_CENTRE :
		consumer = true;
		producer = true;
		user = true;
		evaluative = true;
		break;
	    case EntityTypes.SERVICES_PROVIDER :
		consumer = true;
		producer = true;
		evaluative = true;
		break;
	    case EntityTypes.GOODS_PROVIDER :
		consumer = true;
		producer = true;
		evaluative = true;
		break;
	    case EntityTypes.SERVICE :
		consumer = true;
		producer = true;
		produceable = true;
		usable = true;
		acquirableByContract = true;
		evaluative = true;
		periodic = true;
		break;
	    case EntityTypes.SERVICE_HOUR :
		consumer = true;
		consumable = true;
		producer = true;
		produceable = true;
		requestable = true;
		divisible = true;
		measurable = true;
		acquirableByContract = true;
		evaluative = true;
		break;
		/*
            case EntityTypes.SERVICE_PACKAGE :
                consumer = true;
                consumable = true;
                producer = true;
                requestable = true;
                divisible = true;
                acquirableByContract = true;
                evaluative = true;
                periodic = true;
                break;
            */
	    case EntityTypes.UTILITY_OR_SATISFACTION :
		produceable = true;
		measurable = true;
		evaluative = true;
		break;
	    case EntityTypes.CONSUMPTION_GOOD :
		consumer = true;
		consumable = true;
		producer = true;
		produceable = true;
		requestable = true;
		measurable = true;
		usable = true;
		acquirableByShop = true;
		evaluative = true;
		periodic = true;
		distinguishable = false;
		transferableBySell = true;
		break;
	    case EntityTypes.USE_GOOD :
		consumer = true;
		producer = true;
		produceable = true;
		requestable = true;
		measurable = true;
		usable = true;
		acquirableByContract = true;
		acquirableByShop = true;
		evaluative = true;
		periodic = true;
		amortizable = true;
		distinguishable = true;
		perpetual = true;
		wearable = true;
		transferableByContract = true;
		transferableBySell = true;
		transferableByDonation = true;
		transferableByBorrow = true;
		break;
	    case EntityTypes.BRAND :
		evaluative = true;
		break;
	    case EntityTypes.PERSON :
		consumer = true;
		producer = true;
		requestable = true;
		measurable = true;
		user = true;
		evaluative = true;
		periodic = true;
		distinguishable = true;
		perpetual = true;
		break;
	    case EntityTypes.MAN_HOUR :
		consumer = true;
		consumable = true;
		producer = true;
		produceable = true;
		requestable = true;
		divisible = true;
		measurable = true;
		usable = true;
		break;
	    case EntityTypes.TASK :
		consumer = true;
		producer = true;
		requestable = true;
		measurable = true;
		evaluative = true;
		break;
	    default :
		//TODO
	}
    }

    public int getEntityType() {
	return entityType;
    }

    private void setEntityType(int _entityType) {
	entityType = _entityType;
    }

    public void setConsumer(boolean _consumer) {
	consumer = _consumer;
    }

    public boolean isConsumer() {
	return consumer;
    }

    public void setConsumable(boolean _consumable) {
	consumable = _consumable;
    }

    public boolean isConsumable() {
	return consumable;
    }

    private void setConsumed(boolean _consumed) {
	consumed = _consumed;
    }

    public boolean isConsumed() {
	return consumed;
    }

    private void setConsuming(boolean _consuming) {
	consuming = _consuming;
    }

    public boolean isConsuming() {
	return consuming;
    }

    private void setBeingConsumed(boolean _beingConsumed) {
	beingConsumed = _beingConsumed;
    }

    public boolean isBeingConsumed() {
	return beingConsumed;
    }

    public void setProducer(boolean _producer) {
	producer = _producer;
    }

    public boolean isProducer() {
	return producer;
    }

    public void setProduceable(boolean _produceable) {
	produceable = _produceable;
    }

    public boolean isProduceable() {
	return produceable;
    }

    private void setProduced(boolean _produced) {
	produced = _produced;
    }

    public boolean isProduced() {
	return produced;
    }

    private void setProducing(boolean _producing) {
	producing = _producing;
    }

    public boolean isProducing() {
	return producing;
    }

    private void setBeingProduced(boolean _beingProduced) {
	beingProduced = _beingProduced;
    }

    public boolean isBeingProduced() {
	return beingProduced;
    }

    public void setRequester(boolean _requester) {
	requester = _requester;
    }

    public boolean isRequester() {
	return requester;
    }

    public void setRequestable(boolean _requestable) {
	requestable = _requestable;
    }

    public boolean isRequestable() {
	return requestable;
    }

    private void setRequested(boolean _requested) {
	requested = _requested;
    }

    public boolean isRequested() {
	return requested;
    }

    private void setRequesting(boolean _requesting) {
	requesting = _requesting;
    }

    public boolean isRequesting() {
	return requesting;
    }

    private void setBeingRequested(boolean _beingRequested) {
	beingRequested = _beingRequested;
    }

    public boolean isBeingRequested() {
	return beingRequested;
    }

    public void setDivisible(boolean _divisible) {
	divisible = _divisible;
    }

    public boolean isDivisible() {
	return divisible;
    }

    private void setDivided(boolean _divided) {
	divided = _divided;
    }

    public boolean isDivided() {
	return divided;
    }

    private void setBeingDivided(boolean _beingDivided) {
	beingDivided = _beingDivided;
    }

    public boolean isBeingDivided() {
	return beingDivided;
    }

    public void setGroupable(boolean _groupable) {
	groupable = _groupable;
    }

    public boolean isGroupable() {
	return groupable;
    }

    private void setGrouped(boolean _grouped) {
	grouped = _grouped;
    }

    public boolean isGrouped() {
	return grouped;
    }

    private void setBeingGrouped(boolean _beingGrouped) {
	beingGrouped = _beingGrouped;
    }

    public boolean isBeingGrouped() {
	return beingGrouped;
    }

    public void setMeasurable(boolean _measurable) {
	measurable = _measurable;
    }

    public boolean isMeasurable() {
	return measurable;
    }

    private void setMeasured(boolean _measured) {
	measured = _measured;
    }

    public boolean isMeasured() {
	return measured;
    }

    private void setBeingMeasured(boolean _beingMeasured) {
	beingMeasured = _beingMeasured;
    }

    public boolean isBeingMeasured() {
	return beingMeasured;
    }

    public void setUser(boolean _user) {
	user = _user;
    }

    public boolean isUser() {
	return user;
    }

    public void setUsable(boolean _usable) {
	usable = _usable;
    }

    public boolean isUsable() {
	return usable;
    }

    private void setUsed(boolean _used) {
	used = _used;
    }

    public boolean isUsed() {
	return used;
    }

    private void setUsing(boolean _using) {
	using = _using;
    }

    public boolean isUsing() {
	return using;
    }

    private void setBeingUsed(boolean _beingUsed) {
	beingUsed = _beingUsed;
    }

    public boolean isBeingUsed() {
	return beingUsed;
    }

    public void setWearable(boolean _wearable) {
	wearable = _wearable;
    }

    public boolean isWearable() {
	return wearable;
    }

    private void setWear(boolean _wear) {
	wear = _wear;
    }

    public boolean isWear() {
	return wear;
    }

    private void setBeingWear(boolean _beingWear) {
	beingWear = _beingWear;
    }

    public boolean isBeingWear() {
	return beingWear;
    }

    public void setAcquirableByContract(boolean _acquirableByContract) {
	acquirableByContract = _acquirableByContract;
    }

    public boolean isAcquirableByContract() {
	return acquirableByContract;
    }

    private void setAcquiredByContract(boolean _acquiredByContract) {
	acquiredByContract = _acquiredByContract;
    }

    public boolean isAcquiredByContract() {
	return acquiredByContract;
    }

    private void setBeingAcquiredByContract(boolean _beingAcquiredByContract) {
	beingAcquiredByContract = _beingAcquiredByContract;
    }

    public boolean isBeingAcquiredByContract() {
	return beingAcquiredByContract;
    }

    public void setAcquirableByShop(boolean _acquirableByShop) {
	acquirableByShop = _acquirableByShop;
    }

    public boolean isAcquirableByShop() {
	return acquirableByShop;
    }

    private void setAcquiredByShop(boolean _acquiredByShop) {
	acquiredByShop = _acquiredByShop;
    }

    public boolean isAcquiredByShop() {
	return acquiredByShop;
    }

    private void setBeingAcquiredByShop(boolean _beingAcquiredByShop) {
	beingAcquiredByShop = _beingAcquiredByShop;
    }

    public boolean isBeingAcquiredByShop() {
	return beingAcquiredByShop;
    }

    public void setAcquirableByDonation(boolean _acquirableByDonation) {
	acquirableByDonation = _acquirableByDonation;
    }

    public boolean isAcquirableByDonation() {
	return acquirableByDonation;
    }

    private void setAcquiredByDonation(boolean _acquiredByDonation) {
	acquiredByDonation = _acquiredByDonation;
    }

    public boolean isAcquiredByDonation() {
	return acquiredByDonation;
    }

    private void setBeingAcquiredByDonation(boolean _beingAcquiredByDonation) {
	beingAcquiredByDonation = _beingAcquiredByDonation;
    }

    public boolean isBeingAcquiredByDonation() {
	return beingAcquiredByDonation;
    }

    public void setAcquirableByBorrow(boolean _acquirableByBorrow) {
	acquirableByBorrow = _acquirableByBorrow;
    }

    public boolean isAcquirableByBorrow() {
	return acquirableByBorrow;
    }

    private void setAcquiredByBorrow(boolean _acquiredByBorrow) {
	acquiredByBorrow = _acquiredByBorrow;
    }

    public boolean isAcquiredByBorrow() {
	return acquiredByBorrow;
    }

    private void setBeingAcquiredByBorrow(boolean _beingAcquiredByBorrow) {
	beingAcquiredByBorrow = _beingAcquiredByBorrow;
    }

    public boolean isBeingAcquiredByBorrow() {
	return beingAcquiredByBorrow;
    }

    public void setEvaluator(boolean _evaluator) {
	evaluator = _evaluator;
    }

    public boolean isEvaluator() {
	return evaluator;
    }

    public void setEvaluative(boolean _evaluative) {
	evaluative = _evaluative;
    }

    public boolean isEvaluative() {
	return evaluative;
    }

    private void setEvaluating(boolean _evaluating) {
	evaluating = _evaluating;
    }

    public boolean isEvaluating() {
	return evaluating;
    }

    private void setEvaluated(boolean _evaluated) {
	evaluated = _evaluated;
    }

    public boolean isEvaluated() {
	return evaluated;
    }

    private void setBeingEvaluated(boolean _beingEvaluated) {
	beingEvaluated = _beingEvaluated;
    }

    public boolean isBeingEvaluated() {
	return beingEvaluated;
    }

    public void setPeriodic(boolean _periodic) {
	periodic = _periodic;
    }

    public boolean isPeriodic() {
	return periodic;
    }

    public void setAmortizable(boolean _amortizable) {
	amortizable = _amortizable;
    }

    public boolean isAmortizable() {
	return amortizable;
    }

    private void setAmortized(boolean _amortized) {
	amortized = _amortized;
    }

    public boolean isAmortized() {
	return amortized;
    }

    private void setBeingAmortized(boolean _beingAmortized) {
	beingAmortized = _beingAmortized;
    }

    public boolean isBeingAmortized() {
	return beingAmortized;
    }

    public void setDistinguishable(boolean _distinguishable) {
	distinguishable = _distinguishable;
    }

    public boolean isDistinguishable() {
	return distinguishable;
    }

    public void setPerpetual(boolean _perpetual) {
	perpetual = _perpetual;
    }

    public boolean isPerpetual() {
	return perpetual;
    }

    public void setTransferableByContract(boolean _transferableByContract) {
	transferableByContract = _transferableByContract;
    }

    public boolean isTransferableByContract() {
	return transferableByContract;
    }

    private void setTrasferredByContract(boolean _trasferredByContract) {
	trasferredByContract = _trasferredByContract;
    }

    public boolean isTrasferredByContract() {
	return trasferredByContract;
    }

    private void setBeingTransferredByContract(boolean _beingTransferredByContract) {
	beingTransferredByContract = _beingTransferredByContract;
    }

    public boolean isBeingTransferredByContract() {
	return beingTransferredByContract;
    }

    public void setTransferableBySell(boolean _transferableBySell) {
	transferableBySell = _transferableBySell;
    }

    public boolean isTransferableBySell() {
	return transferableBySell;
    }

    private void setTrasferredBySell(boolean _trasferredBySell) {
	trasferredBySell = _trasferredBySell;
    }

    public boolean isTrasferredBySell() {
	return trasferredBySell;
    }

    private void setBeingTransferredBySell(boolean _beingTransferredBySell) {
	beingTransferredBySell = _beingTransferredBySell;
    }

    public boolean isBeingTransferredBySell() {
	return beingTransferredBySell;
    }

    public void setTransferableByDonation(boolean _transferableByDonation) {
	transferableByDonation = _transferableByDonation;
    }

    public boolean isTransferableByDonation() {
	return transferableByDonation;
    }

    private void setTransferredByDonation(boolean _transferredByDonation) {
	transferredByDonation = _transferredByDonation;
    }

    public boolean isTransferredByDonation() {
	return transferredByDonation;
    }

    private void setBeingTransferredByDonation(boolean _beingTransferredByDonation) {
	beingTransferredByDonation = _beingTransferredByDonation;
    }

    public boolean isBeingTransferredByDonation() {
	return beingTransferredByDonation;
    }

    public void setTransferableByBorrow(boolean _transferableByBorrow) {
	transferableByBorrow = _transferableByBorrow;
    }

    public boolean isTransferableByBorrow() {
	return transferableByBorrow;
    }

    private void setTrasferredByBorrow(boolean _trasferredByBorrow) {
	trasferredByBorrow = _trasferredByBorrow;
    }

    public boolean isTrasferredByBorrow() {
	return trasferredByBorrow;
    }

    private void setBeingTransferredByBorrow(boolean _beingTransferredByBorrow) {
	beingTransferredByBorrow = _beingTransferredByBorrow;
    }

    public boolean isBeingTransferredByBorrow() {
	return beingTransferredByBorrow;
    }
    /** MAIN METHODS */
    /** Consumption, decreases stock when permitted */

    /**
     *
     * @param _amount must be greather or equal than 0
     * @return
     */
    public boolean consume(double _amount) {
	boolean _consumed = false;
	setBeingConsumed(true);
	if (_amount > maxConsumptionLimitAtOnce) {
	    maxConsumptionLimitAtOnce = _amount;
	}
	if (_amount < minConsumptionLimitAtOnce) {
	    minConsumptionLimitAtOnce = _amount;
	}
	if (totalConsumed + _amount > maxConsumptionLimit) {
	    maxConsumptionLimit = totalConsumed + _amount;
	}
	if ((isConsumable()) && ((_amount <= maxConsumptionLimitAtOnce) && (_amount >= minConsumptionLimitAtOnce))) {
	    if (totalConsumed + _amount <= maxConsumptionLimit  && stock - _amount >= 0) { //patch && stock - _amount >= minStock NO RESTRINJO AUNQUE SE PASE DEL MINIMO PERMITIDO
		stock -= _amount;
		_consumed = true;
		setConsumed(true);
	    }
	}
	setBeingConsumed(false);
	return _consumed;
    }

    public boolean doConsumption(int _entityType, double _amount) {
	boolean _consumingDone = false;
	setConsuming(true);
	/** If can consume items of _entityType then GO! */
	//TODO
	setConsuming(false);
	return _consumingDone;
    }
    /** End of consumption methods */
    /** Production, increases stock when permitted */

    /**
     *
     * @param _amount must be greather or equal than 0
     * @return
     */
    public boolean produce(double _amount) {
	boolean _produced = false;
	setBeingProduced(true);
	if (_amount > maxProductionLimitAtOnce) {
	    maxProductionLimitAtOnce = _amount;
	}
	if (_amount < minProductionLimitAtOnce) {
	    minProductionLimitAtOnce = _amount;
	}
	if (totalProduced + _amount > maxProductionLimit) {
	    maxProductionLimit = totalProduced + _amount;
	}
	if ((isProduceable()) && ((_amount <= maxProductionLimitAtOnce) && (_amount >= minProductionLimitAtOnce))) {
	    if (totalProduced + _amount <= maxProductionLimit) { //patch && stock + _amount <= maxStock) { NO RESTRINJO AUNQUE SE PASE DEL MAXIMO PERMITIDO
		stock += _amount;
		_produced = true;
		setProduced(true);
	    }
	}
	setBeingProduced(false);
	return _produced;
    }

    public boolean doProduction(int _entityType, double _amount) {
	boolean _producingDone = false;
	setProducing(true);
	/** If can produce items of _entityType then GO! */
	//TODO
	setProducing(false);
	return _producingDone;
    }
    /** End of production methods /*

    /** Stock handling methods */

    /**
     * method setStock(double _amount) is available only on init, see produce() or consume()!
     */
    public void setStock(double _stock) {
	if (!initiated) {
	    stock = _stock;
	    initiated = true;
	}
    }

    public double getStock() {
	return stock;
    }

    public void setMinStock(double _minStock) {
	if (_minStock >= 0 && _minStock <= maxStock) {
	    minStock = _minStock;
	} else if (_minStock >= 0) {
	    minStock = _minStock;
	    maxStock = _minStock;
	}
    }

    public double getMinStock() {
	return minStock;
    }

    public void setMaxStock(double _maxStock) {
	if (_maxStock >= 0 && _maxStock >= minStock) {
	    maxStock = _maxStock;
	}
    }

    public double getMaxStock() {
	return maxStock;
    }

    public void setMinConsumptionLimitAtOnce(double _minConsumptionLimitAtOnce) {
	if (_minConsumptionLimitAtOnce >= 0 && _minConsumptionLimitAtOnce >= minConsumptionLimit && _minConsumptionLimitAtOnce <= maxConsumptionLimit) {
	    minConsumptionLimitAtOnce = _minConsumptionLimitAtOnce;
	}
    }

    public double getMinConsumptionLimitAtOnce() {
	return minConsumptionLimitAtOnce;
    }

    public void setMaxConsumptionLimitAtOnce(double _maxConsumptionLimitAtOnce) {
	if (_maxConsumptionLimitAtOnce >= 0 && _maxConsumptionLimitAtOnce <= maxConsumptionLimit && _maxConsumptionLimitAtOnce >= minConsumptionLimit) {
	    maxConsumptionLimitAtOnce = _maxConsumptionLimitAtOnce;
	}
    }

    public double getMaxConsumptionLimitAtOnce() {
	return maxConsumptionLimitAtOnce;
    }

    public void setMinProductionLimitAtOnce(double _minProductionLimitAtOnce) {
	if (_minProductionLimitAtOnce >= 0 && _minProductionLimitAtOnce >= minProductionLimit && _minProductionLimitAtOnce <= maxProductionLimit) {
	    minProductionLimitAtOnce = _minProductionLimitAtOnce;
	}
    }

    public double getMinProductionLimitAtOnce() {
	return minProductionLimitAtOnce;
    }

    public void setMaxProductionLimitAtOnce(double _maxProductionLimitAtOnce) {
	if (_maxProductionLimitAtOnce >= 0 && _maxProductionLimitAtOnce <= maxProductionLimit && _maxProductionLimitAtOnce >= minProductionLimit) {
	    maxProductionLimitAtOnce = _maxProductionLimitAtOnce;
	}
    }

    public double getMaxProductionLimitAtOnce() {
	return maxProductionLimitAtOnce;
    }

    /** End of stock handling methods */
    public void setMinConsumptiontLimit(double _minConsumptionLimit) {
	if (_minConsumptionLimit >= 0 && _minConsumptionLimit <= maxConsumptionLimit) {
	    minConsumptionLimit = _minConsumptionLimit;
	}
    }

    public double getMinConsumptiontLimit() {
	return minConsumptionLimit;
    }

    public void setMaxConsumptionLimit(double _maxConsumptionLimit) {
	if (_maxConsumptionLimit >= 0 && _maxConsumptionLimit >= minConsumptionLimit) {
	    maxConsumptionLimit = _maxConsumptionLimit;
	}
    }

    public double getMaxConsumptionLimit() {
	return maxConsumptionLimit;
    }

    public double getTotalConsumed() {
	return totalConsumed;
    }

    public void setMinProductionLimit(double _minProductionLimit) {
	if (_minProductionLimit >= 0 && _minProductionLimit <= maxProductionLimit) {
	    minProductionLimit = _minProductionLimit;
	}
    }

    public double getMinProductionLimit() {
	return minProductionLimit;
    }

    public void setMaxProductionLimit(double _maxProductionLimit) {
	if (_maxProductionLimit >= 0 && _maxProductionLimit >= minProductionLimit) {
	    maxProductionLimit = _maxProductionLimit;
	}
    }

    public double getMaxProductionLimit() {
	return maxProductionLimit;
    }

    public double getTotalProduced() {
	return totalProduced;
    }

    public void setMinRequestLimit(double _minRequestLimit) {
	if (_minRequestLimit >= 0 && _minRequestLimit <= maxRequestLimit) {
	    minRequestLimit = _minRequestLimit;
	}
    }

    public double getMinRequestLimit() {
	return minRequestLimit;
    }

    public void setMaxRequestLimit(double _maxRequestLimit) {
	if (_maxRequestLimit >= 0 && _maxRequestLimit >= minRequestLimit) {
	    maxRequestLimit = _maxRequestLimit;
	}
    }

    public double getMaxRequestLimit() {
	return maxRequestLimit;
    }

    public void setMinRequestLimitAtOnce(double _minRequestLimitAtOnce) {
	if (_minRequestLimitAtOnce >= 0 && _minRequestLimitAtOnce <= maxRequestLimit && _minRequestLimitAtOnce >= minRequestLimit) {
	    minRequestLimitAtOnce = _minRequestLimitAtOnce;
	}
    }

    public double getMinRequestLimitAtOnce() {
	return minRequestLimitAtOnce;
    }

    public void setMaxRequestLimitAtOnce(double _maxRequestLimitAtOnce) {
	if (_maxRequestLimitAtOnce >= 0 && _maxRequestLimitAtOnce <= maxRequestLimit && _maxRequestLimitAtOnce >= minRequestLimit) {
	    maxRequestLimitAtOnce = _maxRequestLimitAtOnce;
	}
    }

    public double getMaxRequestLimitAtOnce() {
	return maxRequestLimitAtOnce;
    }

    public double getTotalRequested() {
	return totalRequested;
    }
    /** Requesting */

    /**
     * Check this method for implementation
     * @param _amount must be greather or equal than 0
     * @return
     */
    public boolean request(double _amount) {
	boolean _requested = false;
	setBeingRequested(true);
	//Implementation goes here
	//setRequested(true);
	//TODO
	setBeingRequested(false);
	return _requested;
    }

    public boolean doRequest(int _entityType, double _amount) {
	boolean _requestingDone = false;
	setRequesting(true);
	/** If can produce items of _entityType then GO! */
	//TODO
	setRequesting(false);
	return _requestingDone;
    }
    /** End of requesting methods */
    /** Dividing */

    /**
     * Check this method for implementation
     * @param divisor
     * @return
     */
    public boolean divide(double divisor) {
	boolean _divided = false;
	setBeingDivided(true);
	//Implementation goes here
	//setDivided(true);
	//TODO
	setBeingDivided(false);
	return _divided;
    }
    /** End of dividing methods */
    /** Grouping */

    /**
     * Check this method for implementation
     * @return
     */
    public boolean group() {
	boolean _grouped = false;
	setBeingGrouped(true);
	//Implementation goes here
	//setGrouped(true);
	//TODO
	setBeingGrouped(false);
	return _grouped;
    }
    /** End of grouping methods */
    /** Measuring */

    /**
     * Check this method for implementation
     * @return
     */
    public boolean measure() {
	boolean _measured = false;
	setBeingMeasured(true);
	//Implementation goes here
	//setGrouped(true);
	//TODO
	setBeingMeasured(false);
	return _measured;
    }
    /** End of measuring methods */
    /** Usage, blocks the entity */

    /**
     *
     * @param _amount must be greather or equal than 0
     * @return
     */
    public boolean use(double _amount) {
	boolean _used = false;
	setBeingUsed(true);
	if ((isUsable()) && ((_amount <= maxUsageLimitAtOnce) && (_amount >= minUsageLimitAtOnce))) {
	    if (totalUsed + _amount < maxUsageLimit) {
		_used = true;
		//Check wearing!! //Ver Desgaste!!
		setUsed(true);
	    }
	}
	setBeingUsed(false);
	return _used;
    }

    /**
     * @param _entityType
     * @param _amount
     * @return
     */
    public boolean doUse(int _entityType, double _amount) {
	boolean _usingDone = false;
	setUsing(true);
	/** If can use items of _entityType then GO! */
	//TODO
	setUsing(false);
	return _usingDone;
    }
    /** End of using methods */

    /** Evaluation of the entity value/satisfaction */
    public boolean evaluate(double _amount) {
	boolean _evaluated = false;
	setBeingEvaluated(true);
	if ((isEvaluative()) && ((_amount <= maxEvaluationLimitAtOnce) && (_amount >= minEvaluationLimitAtOnce))) {
	    if (totalEvaluation + _amount < maxEvaluationLimit) {
		_evaluated = true;
		//Check wearing!! //Ver Desgaste!!
		setEvaluated(true);
	    }
	}
	setBeingEvaluated(false);
	return _evaluated;
    }

    public boolean doEvaluation(int _entityType, double _amount) {
	boolean _usingDone = false;
	setUsing(true);
	/** If can evaluate items of _entityType then GO! */
	//TODO
	setUsing(false);
	return _usingDone;
    }

    /** End of evaluation methods */
    public void setMinUsageLimit(double _minUsageLimit) {
	if (_minUsageLimit >= 0 && _minUsageLimit <= maxUsageLimit) {
	    minUsageLimit = _minUsageLimit;
	}
    }

    public double getMinUsageLimit() {
	return minUsageLimit;
    }

    public void setMaxUsageLimit(double _maxUsageLimit) {
	if (_maxUsageLimit >= 0 && _maxUsageLimit >= minUsageLimit) {
	    maxUsageLimit = _maxUsageLimit;
	}
    }

    public double getMaxUsageLimit() {
	return maxUsageLimit;
    }

    public void setMinUsageLimitAtOnce(double _minUsageLimitAtOnce) {
	if (_minUsageLimitAtOnce >= 0 && _minUsageLimitAtOnce <= maxUsageLimit && _minUsageLimitAtOnce >= minUsageLimit) {
	    minUsageLimitAtOnce = _minUsageLimitAtOnce;
	}
    }

    public double getMinUsageLimitAtOnce() {
	return minUsageLimitAtOnce;
    }

    public void setMaxUsageLimitAtOnce(double _maxUsageLimitAtOnce) {
	if (_maxUsageLimitAtOnce >= 0 && _maxUsageLimitAtOnce <= maxUsageLimit && _maxUsageLimitAtOnce >= minUsageLimit) {
	    maxUsageLimitAtOnce = _maxUsageLimitAtOnce;
	}
    }

    public double getMaxUsageLimitAtOnce() {
	return maxUsageLimitAtOnce;
    }

    public double getTotalUsed() {
	return totalUsed;
    }

    public void setMinEvaluationLimit(double _minEvaluationLimit) {
	if (_minEvaluationLimit >= 0 && _minEvaluationLimit <= maxEvaluationLimit) {
	    minEvaluationLimit = _minEvaluationLimit;
	}
    }

    public double getMinEvaluationLimit() {
	return minEvaluationLimit;
    }

    public void setMaxEvaluationLimit(double _maxEvaluationLimit) {
	if (_maxEvaluationLimit >= 0 && _maxEvaluationLimit >= minEvaluationLimit) {
	    maxEvaluationLimit = _maxEvaluationLimit;
	}
    }

    public double getMaxEvaluationLimit() {
	return maxEvaluationLimit;
    }

    public void setMinEvaluationLimitAtOnce(double _minEvaluationLimitAtOnce) {
	if (_minEvaluationLimitAtOnce >= 0 && _minEvaluationLimitAtOnce <= maxEvaluationLimit && _minEvaluationLimitAtOnce >= minEvaluationLimit) {
	    minEvaluationLimitAtOnce = _minEvaluationLimitAtOnce;
	}
    }

    public double getMinEvaluationLimitAtOnce() {
	return minEvaluationLimitAtOnce;
    }

    public void setMaxEvaluationLimitAtOnce(double _maxEvaluationLimitAtOnce) {
	if (_maxEvaluationLimitAtOnce >= 0 && _maxEvaluationLimitAtOnce <= maxEvaluationLimit && _maxEvaluationLimitAtOnce >= minEvaluationLimit) {
	    maxEvaluationLimitAtOnce = _maxEvaluationLimitAtOnce;
	}
    }

    public double getMaxEvaluationLimitAtOnce() {
	return maxEvaluationLimitAtOnce;
    }

    public double getTotalEvaluation() {
	return totalEvaluation;
    }

    public void setInitiated(boolean _initiated) {
	initiated = _initiated;
    }
}
