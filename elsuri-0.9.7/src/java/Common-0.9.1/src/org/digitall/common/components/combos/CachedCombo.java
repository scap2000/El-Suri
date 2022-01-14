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
 * CachedCombo.java
 *
 * */
package org.digitall.common.components.combos;

import org.digitall.common.cashflow.interfaces.costscentre.ActivitiesList;
import org.digitall.common.resourcescontrol.interfaces.BrandsList;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.common.resourcescontrol.interfaces.UnitsList;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.geo.gaia.components.infrastructure.GaiaInfrastructureTypesList;
import org.digitall.lib.geo.gaia.components.infrastructure.GaiaTiposActividadesAdminyServList;
import org.digitall.lib.geo.gaia.components.infrastructure.GaiaTiposAdminyServList;
import org.digitall.lib.geo.gaia.components.infrastructure.GaiaTiposCultosyCulturaList;
import org.digitall.lib.resources.ResourcesManager;
import org.digitall.lib.sql.LibSQL;


//

/**
 * query: necesita 3 parametros,
 * por ej. Select idfield, name, iddependency or 0  From destTable
 *
 *
 */
public abstract class CachedCombo {

    public static final int MINERALIZATION_TABS = 1;
    public static final int ALTERATION_TABS = 2;
    public static final int STRUCTURE_TABS = 3;
    public static final int COUNTRY_TABS = 4;
    public static final int PROVINCE_TABS = 5;
    public static final int LOCATION_TABS = 6;
    public static final int NEIGHBORHOOD_TABS = 7;
    public static final int STREET_TABS = 8;
    public static final int ENTITIES_ALLPERSONLIST = 9;
    public static final int APPLICATION_TABS = 10;
    public static final int IDENTIFICATION_TABS = 11;
    public static final int PERIODTYPES_LIST = 12;
    public static final int TRIBUTARYCONDITION_TABS = 13;
    public static final int SUFFIXPERSON_TABS = 14;
    public static final int SUFFIXCOMPANY_TABS = 15;
    public static final int COMMUNICATIONTYPE_TABS = 16;
    public static final int COMPANYTYPE_TABS = 17;
    public static final int ADDRESSTYPE_TABS = 18;
    public static final int SKILLCOMPANY_TABS = 19;
    public static final int SKILLPERSON_TABS = 20;
    public static final int PREFIX_TABS = 21;
    public static final int FORMATVIEW_TABS = 22;
    public static final int CONTACTTYPE_TABS = 23;
    public static final int CIVILSTATE_TABS = 24;
    public static final int PROFESSION_TABS = 25;
    public static final int ENTITIES_ALLCOMPANIESLIST = 35;
    public static final int COSTSCENTRE = 36;
    public static final int TASK_LIST = 37;
    public static final int USERS = 38;
    public static final int BANK = 39;
    public static final int MONEYTYPE = 40;
    public static final int ACCOUNTTYPE = 41;
    public static final int TRANSACTIONTYPE = 42;
    public static final int OPERATIONMETHOD = 43;
    public static final int VOUCHERTYPE = 44;
    public static final int VALUEBLOCK = 45;
    public static final int RESOURCES = 46;
    public static final int UNITS_TABS = 47;
    public static final int BRANDS = 48;
    public static final int SKILLPROVIDER_TABS = 49;
    public static final int SUBJECT = 50;
    public static final int LIFETIMETYPES = 51;
    public static final int CONTRACTTYPE = 52;
    public static final int PROVIDERS = 53;
    public static final int RESOURCES_REQUEST = 54;
    public static final int CASHMOVEMENTTYPEALL = 55;
    public static final int PRIORITY = 56;
    public static final int STATUS = 57;
    public static final int ENTITYTYPE = 58;
    public static final int CASHMOVEMENTTYPE = 59;
    public static final int BANKACCOUNTS = 60;
    public static final int CHECKSTATUS = 61;
    public static final int CHECKTYPE = 62;
    public static final int RESOURCESMOVEMENTSTYPES = 63;
    public static final int ALLDEPOTS = 64;
    public static final int REQUESTSTATUS = 65;
    public static final int PRIORITYREQUEST = 66;
    public static final int RESOURCESMOVEMENTSTYPESBYDELIVERY = 67;
    public static final int BUDGETS = 68;
    public static final int VOUCHERTYPE_INVOICES = 69;
    public static final int ACCOUNTTYPE_ISHERITAGE = 70;
    public static final int ACCOUNTTYPE_ISRESULT = 71;
    public static final int CATEGORYTYPES = 72;
    public static final int MODELS = 73;
    public static final int DATABASE = 74;
    public static final int SCHEME = 75;
    public static final int VOUCHERTYPE_PAYABLE = 76;
    public static final int ACTIVITY = 77;
    public static final int PERSON = 78;
    public static final int DEBITCASHMOVEMENTTYPES = 79;
    public static final int CREDITCASHMOVEMENTTYPES = 80;
    public static final int INFRASTRUCTURETYPE_TABS = 81;
    public static final int SEGURIDAD_TABS = 82;
    public static final int SERVICIOSURBANOS_TABS = 83;
    public static final int CULTOYCULTURA_TABS = 84;
    public static final int ADMINISTRACIONYSERVICIOS_TABS = 85;
    public static final int ACTIVIDADES_TABS = 86;
    public static final int CATEGORIASCOMERCIALES_TABS = 87;
    public static final int RUBROSCOMERCIALES_TABS = 88;
    public static final int INDUSTRIAS_TABS = 89;
    public static final int PRODUCCION_TABS = 90;
    public static final int ESTABLECIMIENTOS_TABS = 91;
    public static final int NIVELESEDUCATIVOS_TABS = 92;
    public static final int ORIENTACIONESEDUCATIVAS_TABS = 93;
    public static final int INSTALACIONESTURISTICAS_TABS = 94;
    public static final int CATEGORIASTURISTICAS_TABS = 95;
    public static final int AUTOMOTORES_TABS = 96;
    public static final int ENFERMEDADES_TABS = 97;
    public static final int CASOSENFERMEDADES_TABS = 98;
    public static final int TIPOSPREVENCIONENFERMEDADES_TABS = 99;
    public static final int MONTHS = 100;
    public static JCombo cbMineralizations = new JCombo();
    public static JCombo cbAlterations = new JCombo();
    public static JCombo cbStructures = new JCombo();
    public static JCombo cbCountries = new JCombo();
    public static JCombo cbProvinces = new JCombo();
    public static JCombo cbLocations = new JCombo();
    public static JCombo cbNeighborhoods = new JCombo();
    public static JCombo cbStreets = new JCombo();
    public static JCombo cbEntities = new JCombo();
    public static JCombo cbApplications = new JCombo();
    public static JCombo cbIdentifications = new JCombo();
    public static JCombo cbPeriodType = new JCombo();
    public static JCombo cbTributaryCondition = new JCombo();
    public static JCombo cbPrefix = new JCombo();
    public static JCombo cbCommunicationType = new JCombo();
    public static JCombo cbCompanyType = new JCombo();
    public static JCombo cbAddressType = new JCombo();
    public static JCombo cbSkill = new JCombo();
    public static JCombo cbSkillProvider = new JCombo();
    public static JCombo cbSuffix = new JCombo();
    public static JCombo cbFormatView = new JCombo();
    public static JCombo cbContactType = new JCombo();
    public static JCombo cbCivilState = new JCombo();
    public static JCombo cbProfession = new JCombo();
    public static JCombo cbRefProvinces = new JCombo();
    public static JCombo cbRefLocations = new JCombo();
    public static JCombo cbRefNeighborhoods = new JCombo();
    public static JCombo cbRefStreets = new JCombo();
    public static JCombo cbRefLocationStreets = new JCombo();
    public static JCombo cbAllProvinces = new JCombo();
    public static JCombo cbAllLocations = new JCombo();
    public static JCombo cbAllNeighborhoods = new JCombo();
    public static JCombo cbAllStreets = new JCombo();
    public static JCombo cbAllCompaniesList = new JCombo();
    public static JCombo cbCostsCentre = new JCombo();
    public static JCombo cbTasks = new JCombo();
    public static JCombo cbUsers = new JCombo();
    public static JCombo cbBank = new JCombo();
    public static JCombo cbMoneyType = new JCombo();
    public static JCombo cbAccountType = new JCombo();
    public static JCombo cbTransactionType = new JCombo();
    public static JCombo cbOperationMethod = new JCombo();
    public static JCombo cbVourcherType = new JCombo();
    public static JCombo cbValueBlock = new JCombo();
    public static JCombo cbResources = new JCombo();
    public static JCombo cbUnits = new JCombo();
    public static JCombo cbBrands = new JCombo();
    public static JCombo cbSubject = new JCombo();
    public static JCombo cbLifeTimeTypes = new JCombo();
    public static JCombo cbContractType = new JCombo();
    public static JCombo cbProviders = new JCombo();
    public static JCombo cbResourcesRequest = new JCombo();
    public static JCombo cbCashMovementTypeAll = new JCombo();
    public static JCombo cbPriority = new JCombo();
    public static JCombo cbStatus = new JCombo();
    public static JCombo cbEntityType = new JCombo();
    public static JCombo cbCashMovementType = new JCombo();
    public static JCombo cbDebitCashMovementTypes = new JCombo();
    public static JCombo cbCreditCashMovementTypes = new JCombo();
    public static JCombo cbAccount = new JCombo();
    public static JCombo cbCheckType = new JCombo();
    public static JCombo cbCheckStatus = new JCombo();
    public static JCombo cbResourcesMovementsTypes = new JCombo();
    public static JCombo cbAllDepots = new JCombo();
    public static JCombo cbRequestStatus = new JCombo();
    public static JCombo cbPriorityRequest = new JCombo();
    public static JCombo cbResourcesMovementsTypesByDelivery = new JCombo();
    public static JCombo cbBudget = new JCombo();
    public static JCombo cbVoucherTypeInvoice = new JCombo();
    public static JCombo cbAccountingType_isHeritage = new JCombo();
    public static JCombo cbAccountingType_isResult = new JCombo();
    public static JCombo cbCategoryTypes = new JCombo();
    public static JCombo cbModels = new JCombo();
    public static JCombo cbDB = new JCombo();
    public static JCombo cbScheme = new JCombo();
    public static JCombo cbAccountType_isHeritage = new JCombo();
    public static JCombo cbAccountType_isResult = new JCombo();
    public static JCombo cbVoucherType_Payable = new JCombo();
    public static JCombo cbActivity = new JCombo();
    public static JCombo cbPerson = new JCombo();
    public static JCombo cbInfrastructureType = new JCombo();
    public static JCombo cbSeguridadType = new JCombo();
    public static JCombo cbServUrbanosType = new JCombo();
    public static JCombo cbCultoyCulturaType = new JCombo();
    public static JCombo cbAdminyServ = new JCombo();
    public static JCombo cbActividades = new JCombo();
    public static JCombo cbCategoriaComercial = new JCombo();
    public static JCombo cbRubrosComerciales = new JCombo();
    public static JCombo cbIndustrias = new JCombo();
    public static JCombo cbProduccion = new JCombo();
    public static JCombo cbEstablecimientos = new JCombo();
    public static JCombo cbNivelesEducativos = new JCombo();
    public static JCombo cbOrientacionesEducativas = new JCombo();
    public static JCombo cbInstalacionesTuristicas = new JCombo();
    public static JCombo cbCategoriasTuristicas = new JCombo();
    public static JCombo cbAutomotores = new JCombo();
    public static JCombo cbEnfermedades = new JCombo();
    public static JCombo cbCasosEnfermedades = new JCombo();
    public static JCombo cbTiposPrevencionEnfermedades = new JCombo();
    public static JCombo cbMonths = new JCombo();
    public static ConfigFile commonCfg = new ConfigFile(ResourcesManager.class.getResourceAsStream("common/default.conf"));

    public static JCombo getCachedCombo(int _type) {
	JCombo _temp = new JCombo();
	switch (_type) {
	    case SCHEME :
		if (cbScheme.isUpdateNeeded()) {
		    cbLoadData(SCHEME);
		}
		_temp = cbScheme.getCopy();
		break;
	    case DATABASE :
		if (cbDB.isUpdateNeeded()) {
		    cbLoadData(DATABASE);
		}
		_temp = cbDB.getCopy();
		break;
	    case ACCOUNTTYPE_ISHERITAGE :
		if (cbAccountType_isHeritage.isUpdateNeeded()) {
		    cbLoadData(ACCOUNTTYPE_ISHERITAGE);
		}
		_temp = cbAccountType_isHeritage.getCopy();
		break;
	    case ACCOUNTTYPE_ISRESULT :
		if (cbAccountType_isResult.isUpdateNeeded()) {
		    cbLoadData(ACCOUNTTYPE_ISRESULT);
		}
		_temp = cbAccountType_isResult.getCopy();
		break;
	    case VOUCHERTYPE_INVOICES :
		if (cbVoucherTypeInvoice.isUpdateNeeded()) {
		    cbLoadData(VOUCHERTYPE_INVOICES);
		}
		_temp = cbVoucherTypeInvoice.getCopy();
		break;
	    case BUDGETS :
		if (cbBudget.isUpdateNeeded()) {
		    cbLoadData(BUDGETS);
		}
		_temp = cbBudget.getCopy();
		break;
	    case PRIORITYREQUEST :
		if (cbPriorityRequest.isUpdateNeeded()) {
		    cbLoadData(PRIORITYREQUEST);
		}
		_temp = cbPriorityRequest.getCopy();
		break;
	    case MINERALIZATION_TABS :
		if (cbMineralizations.isUpdateNeeded()) {
		    cbLoadData(MINERALIZATION_TABS);
		}
		_temp = cbMineralizations.getCopy();
		break;
	    case COUNTRY_TABS :
		if (cbCountries.isUpdateNeeded()) {
		    cbLoadData(COUNTRY_TABS);
		}
		_temp = cbCountries.getCopy();
		_temp.setSelectedID(commonCfg.getProperty("CountryDefaultValue"));
		break;
	    case PROVINCE_TABS :
		if (cbProvinces.isUpdateNeeded()) {
		    cbLoadData(PROVINCE_TABS);
		}
		_temp = cbProvinces.getCopy();
		//_temp.setSelectedID(commonCfg.getProperty("ProvinceDefaultValue"));
		break;
	    case LOCATION_TABS :
		if (cbLocations.isUpdateNeeded()) {
		    cbLoadData(LOCATION_TABS);
		}
		_temp = cbLocations.getCopy();
		//_temp.setSelectedID(commonCfg.getProperty("LocationDefaultValue"));
		break;
	    case NEIGHBORHOOD_TABS :
		if (cbNeighborhoods.isUpdateNeeded()) {
		    cbLoadData(NEIGHBORHOOD_TABS);
		}
		_temp = cbNeighborhoods.getCopy();
		break;
	    case STREET_TABS :
		if (cbStreets.isUpdateNeeded()) {
		    cbLoadData(STREET_TABS);
		}
		_temp = cbStreets.getCopy();
		break;
	    case ENTITIES_ALLPERSONLIST :
		if (cbEntities.isUpdateNeeded()) {
		    cbLoadData(ENTITIES_ALLPERSONLIST);
		}
		_temp = cbEntities.getCopy();
		break;
	    case APPLICATION_TABS :
		if (cbApplications.isUpdateNeeded()) {
		    cbLoadData(APPLICATION_TABS);
		}
		_temp = cbApplications.getCopy();
		break;
	    case IDENTIFICATION_TABS :
		if (cbIdentifications.isUpdateNeeded()) {
		    cbLoadData(IDENTIFICATION_TABS);
		}
		_temp = cbIdentifications.getCopy();
		break;
	    case PERIODTYPES_LIST :
		if (cbPeriodType.isUpdateNeeded()) {
		    cbLoadData(PERIODTYPES_LIST);
		}
		_temp = cbPeriodType.getCopy();
		break;
	    case TRIBUTARYCONDITION_TABS :
		if (cbTributaryCondition.isUpdateNeeded()) {
		    cbLoadData(TRIBUTARYCONDITION_TABS);
		}
		_temp = cbTributaryCondition.getCopy();
		break;
	    case SUFFIXPERSON_TABS :
		if (cbSuffix.isUpdateNeeded()) {
		    cbLoadData(SUFFIXPERSON_TABS);
		}
		_temp = cbSuffix.getCopy();
		break;
	    case SUFFIXCOMPANY_TABS :
		if (cbSuffix.isUpdateNeeded()) {
		    cbLoadData(SUFFIXCOMPANY_TABS);
		}
		_temp = cbSuffix.getCopy();
		break;
	    case COMMUNICATIONTYPE_TABS :
		if (cbCommunicationType.isUpdateNeeded()) {
		    cbLoadData(COMMUNICATIONTYPE_TABS);
		}
		_temp = cbCommunicationType.getCopy();
		break;
	    case COMPANYTYPE_TABS :
		if (cbCompanyType.isUpdateNeeded()) {
		    cbLoadData(COMPANYTYPE_TABS);
		}
		_temp = cbCompanyType.getCopy();
		break;
	    case ADDRESSTYPE_TABS :
		if (cbAddressType.isUpdateNeeded()) {
		    cbLoadData(ADDRESSTYPE_TABS);
		}
		_temp = cbAddressType.getCopy();
		break;
	    case SKILLCOMPANY_TABS :
		if (cbSkill.isUpdateNeeded()) {
		    cbLoadData(SKILLCOMPANY_TABS);
		}
		_temp = cbSkill.getCopy();
		break;
	    case SKILLPERSON_TABS :
		if (cbSkill.isUpdateNeeded()) {
		    cbLoadData(SKILLPERSON_TABS);
		}
		_temp = cbSkill.getCopy();
		break;
	    case PREFIX_TABS :
		if (cbPrefix.isUpdateNeeded()) {
		    cbLoadData(PREFIX_TABS);
		}
		_temp = cbPrefix.getCopy();
		break;
	    case FORMATVIEW_TABS :
		if (cbFormatView.isUpdateNeeded()) {
		    cbLoadData(FORMATVIEW_TABS);
		}
		_temp = cbFormatView.getCopy();
		break;
	    case CONTACTTYPE_TABS :
		if (cbContactType.isUpdateNeeded()) {
		    cbLoadData(CONTACTTYPE_TABS);
		}
		_temp = cbContactType.getCopy();
		break;
	    case CIVILSTATE_TABS :
		if (cbCivilState.isUpdateNeeded()) {
		    cbLoadData(CIVILSTATE_TABS);
		}
		_temp = cbCivilState.getCopy();
		break;
	    case PROFESSION_TABS :
		if (cbProfession.isUpdateNeeded()) {
		    cbLoadData(PROFESSION_TABS);
		}
		_temp = cbProfession.getCopy();
		break;
	    case ENTITIES_ALLCOMPANIESLIST :
		if (cbAllCompaniesList.isUpdateNeeded()) {
		    cbLoadData(ENTITIES_ALLCOMPANIESLIST);
		}
		_temp = cbAllCompaniesList.getCopy();
		break;
	    case COSTSCENTRE :
		if (cbCostsCentre.isUpdateNeeded()) {
		    cbLoadData(COSTSCENTRE);
		}
		_temp = cbCostsCentre.getCopy();
		break;
	    case TASK_LIST :
		if (cbTasks.isUpdateNeeded()) {
		    cbLoadData(TASK_LIST);
		}
		_temp = cbTasks.getCopy();
		break;
	    case USERS :
		if (cbUsers.isUpdateNeeded()) {
		    cbLoadData(USERS);
		}
		_temp = cbUsers.getCopy();
		break;
	    case BANK :
		if (cbBank.isUpdateNeeded()) {
		    cbLoadData(BANK);
		}
		_temp = cbBank.getCopy();
		break;
	    case MONEYTYPE :
		if (cbMoneyType.isUpdateNeeded()) {
		    cbLoadData(MONEYTYPE);
		}
		_temp = cbMoneyType.getCopy();
		break;
	    case ACCOUNTTYPE :
		if (cbAccountType.isUpdateNeeded()) {
		    cbLoadData(ACCOUNTTYPE);
		}
		_temp = cbAccountType.getCopy();
		break;
	    case TRANSACTIONTYPE :
		if (cbTransactionType.isUpdateNeeded()) {
		    cbLoadData(TRANSACTIONTYPE);
		}
		_temp = cbTransactionType.getCopy();
		break;
	    case OPERATIONMETHOD :
		if (cbOperationMethod.isUpdateNeeded()) {
		    cbLoadData(OPERATIONMETHOD);
		}
		_temp = cbOperationMethod.getCopy();
		break;
	    case VOUCHERTYPE :
		if (cbVourcherType.isUpdateNeeded()) {
		    cbLoadData(VOUCHERTYPE);
		}
		_temp = cbVourcherType.getCopy();
		break;
	    case VALUEBLOCK :
		if (cbValueBlock.isUpdateNeeded()) {
		    if (cbValueBlock.isUpdateNeeded()) {
			cbValueBlock.setUpdateNeeded(false);
			cbValueBlock.removeAllItems();
			cbValueBlock.addItem("%", "1");
			cbValueBlock.addItem("$", "0");
		    }
		}
		_temp = cbValueBlock.getCopy();
		break;
	    case RESOURCES :
		if (cbResources.isUpdateNeeded()) {
		    cbLoadData(RESOURCES);
		}
		_temp = cbResources.getCopy();
		break;
	    case UNITS_TABS :
		if (cbUnits.isUpdateNeeded()) {
		    cbLoadData(UNITS_TABS);
		}
		_temp = cbUnits.getCopy();
		break;
	    case BRANDS :
		if (cbBrands.isUpdateNeeded()) {
		    cbLoadData(BRANDS);
		}
		_temp = cbBrands.getCopy();
		break;
	    case SKILLPROVIDER_TABS :
		if (cbSkillProvider.isUpdateNeeded()) {
		    cbLoadData(SKILLPROVIDER_TABS);
		}
		_temp = cbSkillProvider.getCopy();
		break;
	    case SUBJECT :
		if (cbSubject.isUpdateNeeded()) {
		    cbLoadData(SUBJECT);
		}
		_temp = cbSubject.getCopy();
		break;
	    case LIFETIMETYPES :
		if (cbLifeTimeTypes.isUpdateNeeded()) {
		    cbLoadData(LIFETIMETYPES);
		}
		_temp = cbLifeTimeTypes.getCopy();
		break;
	    case CONTRACTTYPE :
		if (cbContractType.isUpdateNeeded()) {
		    cbLoadData(CONTRACTTYPE);
		}
		_temp = cbContractType.getCopy();
		break;
	    case PROVIDERS :
		if (cbProviders.isUpdateNeeded()) {
		    cbLoadData(PROVIDERS);
		}
		_temp = cbProviders.getCopy();
		break;
	    case RESOURCES_REQUEST :
		if (cbResourcesRequest.isUpdateNeeded()) {
		    cbLoadData(RESOURCES_REQUEST);
		}
		_temp = cbResourcesRequest.getCopy();
		break;
	    case CASHMOVEMENTTYPEALL :
		if (cbCashMovementTypeAll.isUpdateNeeded()) {
		    cbLoadData(CASHMOVEMENTTYPEALL);
		}
		_temp = cbCashMovementTypeAll.getCopy();
		break;
	    case PRIORITY :
		if (cbPriority.isUpdateNeeded()) {
		    cbLoadData(PRIORITY);
		}
		_temp = cbPriority.getCopy();
		break;
	    case STATUS :
		if (cbStatus.isUpdateNeeded()) {
		    cbLoadData(STATUS);
		}
		_temp = cbStatus.getCopy();
		break;
	    case ENTITYTYPE :
		if (cbEntityType.isUpdateNeeded()) {
		    cbLoadData(ENTITYTYPE);
		}
		_temp = cbEntityType.getCopy();
		break;
	    case CASHMOVEMENTTYPE :
	        if (cbCashMovementType.isUpdateNeeded()) {
	            cbLoadData(CASHMOVEMENTTYPE);
	        }
	        _temp = cbCashMovementType.getCopy();
	        break;
	    case DEBITCASHMOVEMENTTYPES :
	        if (cbDebitCashMovementTypes.isUpdateNeeded()) {
	            cbLoadData(DEBITCASHMOVEMENTTYPES);
	        }
	        _temp = cbDebitCashMovementTypes.getCopy();
	        break;
	    case BANKACCOUNTS :
		if (cbAccount.isUpdateNeeded()) {
		    cbLoadData(BANKACCOUNTS);
		}
		_temp = cbAccount.getCopy();
		break;
	    case CHECKSTATUS :
		if (cbCheckStatus.isUpdateNeeded()) {
		    cbLoadData(CHECKSTATUS);
		}
		_temp = cbCheckStatus.getCopy();
		break;
	    case CHECKTYPE :
		if (cbCheckType.isUpdateNeeded()) {
		    cbLoadData(CHECKTYPE);
		}
		_temp = cbCheckType.getCopy();
		break;
	    case RESOURCESMOVEMENTSTYPES :
		if (cbResourcesMovementsTypes.isUpdateNeeded()) {
		    cbLoadData(RESOURCESMOVEMENTSTYPES);
		}
		_temp = cbResourcesMovementsTypes.getCopy();
		break;
	    case ALLDEPOTS :
		if (cbAllDepots.isUpdateNeeded()) {
		    cbLoadData(ALLDEPOTS);
		}
		_temp = cbAllDepots.getCopy();
		break;
	    case REQUESTSTATUS :
		if (cbRequestStatus.isUpdateNeeded()) {
		    cbLoadData(REQUESTSTATUS);
		}
		_temp = cbRequestStatus.getCopy();
		break;
	    case RESOURCESMOVEMENTSTYPESBYDELIVERY :
		if (cbResourcesMovementsTypesByDelivery.isUpdateNeeded()) {
		    cbLoadData(RESOURCESMOVEMENTSTYPESBYDELIVERY);
		}
		_temp = cbResourcesMovementsTypesByDelivery.getCopy();
		break;
	    case CATEGORYTYPES :
		if (cbCategoryTypes.isUpdateNeeded()) {
		    cbLoadData(CATEGORYTYPES);
		}
		_temp = cbCategoryTypes.getCopy();
		break;
	    case MODELS :
		if (cbModels.isUpdateNeeded()) {
		    cbLoadData(MODELS);
		}
		_temp = cbModels.getCopy();
		break;
	    case VOUCHERTYPE_PAYABLE :
		if (cbVoucherType_Payable.isUpdateNeeded()) {
		    cbLoadData(VOUCHERTYPE_PAYABLE);
		}
		_temp = cbVoucherType_Payable.getCopy();
		break;
	    case ACTIVITY :
	        if (cbActivity.isUpdateNeeded()) {
	            cbLoadData(ACTIVITY);
	        }
	        _temp = cbActivity.getCopy();
	        break;
	    case PERSON :
	        if (cbPerson.isUpdateNeeded()) {
	            cbLoadData(PERSON);
	        }
	        _temp = cbPerson.getCopy();
	        break;
	    case CREDITCASHMOVEMENTTYPES :
	        if (cbCreditCashMovementTypes.isUpdateNeeded()) {
	            cbLoadData(CREDITCASHMOVEMENTTYPES);
	        }
	        _temp = cbCreditCashMovementTypes.getCopy();
	        break;
	    case INFRASTRUCTURETYPE_TABS :
	        if (cbInfrastructureType.isUpdateNeeded()) {
	            cbLoadData(INFRASTRUCTURETYPE_TABS);
	        }
	        _temp = cbInfrastructureType.getCopy();
	        break;
	    case SEGURIDAD_TABS :
	        if (cbSeguridadType.isUpdateNeeded()) {
	            cbLoadData(SEGURIDAD_TABS);
	        }
	        _temp = cbSeguridadType.getCopy();
	        break;
	    case SERVICIOSURBANOS_TABS :
	        if (cbServUrbanosType.isUpdateNeeded()) {
	            cbLoadData(SERVICIOSURBANOS_TABS);
	        }
	        _temp = cbServUrbanosType.getCopy();
	        break;
	    case CULTOYCULTURA_TABS :
	        if (cbCultoyCulturaType.isUpdateNeeded()) {
	            cbLoadData(CULTOYCULTURA_TABS);
	        }
	        _temp = cbCultoyCulturaType.getCopy();
	        break;
	    case ADMINISTRACIONYSERVICIOS_TABS :
	        if (cbAdminyServ.isUpdateNeeded()) {
	            cbLoadData(ADMINISTRACIONYSERVICIOS_TABS);
	        }
	        _temp = cbAdminyServ.getCopy();
	        break;
	    case ACTIVIDADES_TABS :
	        if (cbActividades.isUpdateNeeded()) {
	            cbLoadData(ACTIVIDADES_TABS);
	        }
	        _temp = cbActividades.getCopy();
	        break;
	    case CATEGORIASCOMERCIALES_TABS :
	        if (cbCategoriaComercial.isUpdateNeeded()) {
	            cbLoadData(CATEGORIASCOMERCIALES_TABS);
	        }
	        _temp = cbCategoriaComercial.getCopy();
	        break;
	    case RUBROSCOMERCIALES_TABS :
	        if (cbRubrosComerciales.isUpdateNeeded()) {
	            cbLoadData(RUBROSCOMERCIALES_TABS);
	        }
	        _temp = cbRubrosComerciales.getCopy();
	        break;
	    case INDUSTRIAS_TABS :
	        if (cbIndustrias.isUpdateNeeded()) {
	            cbLoadData(INDUSTRIAS_TABS);
	        }
	        _temp = cbIndustrias.getCopy();
	        break;
	    case PRODUCCION_TABS :
	        if (cbProduccion.isUpdateNeeded()) {
	            cbLoadData(PRODUCCION_TABS);
	        }
	        _temp = cbProduccion.getCopy();
	        break;
	    case ESTABLECIMIENTOS_TABS :
	        if (cbEstablecimientos.isUpdateNeeded()) {
	            cbLoadData(ESTABLECIMIENTOS_TABS);
	        }
	        _temp = cbEstablecimientos.getCopy();
	        break;
	    case NIVELESEDUCATIVOS_TABS :
	        if (cbNivelesEducativos.isUpdateNeeded()) {
	            cbLoadData(NIVELESEDUCATIVOS_TABS);
	        }
	        _temp = cbNivelesEducativos.getCopy();
	        break;
	    case ORIENTACIONESEDUCATIVAS_TABS :
	        if (cbOrientacionesEducativas.isUpdateNeeded()) {
	            cbLoadData(ORIENTACIONESEDUCATIVAS_TABS);
	        }
	        _temp = cbOrientacionesEducativas.getCopy();
	        break;
	    case INSTALACIONESTURISTICAS_TABS :
	        if (cbInstalacionesTuristicas.isUpdateNeeded()) {
	            cbLoadData(INSTALACIONESTURISTICAS_TABS);
	        }
	        _temp = cbInstalacionesTuristicas.getCopy();
	        break;
	    case CATEGORIASTURISTICAS_TABS :
	        if (cbCategoriasTuristicas.isUpdateNeeded()) {
	            cbLoadData(CATEGORIASTURISTICAS_TABS);
	        }
	        _temp = cbCategoriasTuristicas.getCopy();
	        break;
	    case AUTOMOTORES_TABS :
	        if (cbAutomotores.isUpdateNeeded()) {
	            cbLoadData(AUTOMOTORES_TABS);
	        }
	        _temp = cbAutomotores.getCopy();
	        break;
	    case ENFERMEDADES_TABS :
	        if (cbEnfermedades.isUpdateNeeded()) {
	            cbLoadData(ENFERMEDADES_TABS);
	        }
	        _temp = cbEnfermedades.getCopy();
	        break;
	    case CASOSENFERMEDADES_TABS :
	        if (cbCasosEnfermedades.isUpdateNeeded()) {
	            cbLoadData(CASOSENFERMEDADES_TABS);
	        }
	        _temp = cbCasosEnfermedades.getCopy();
	        break;
	    case TIPOSPREVENCIONENFERMEDADES_TABS :
	        if (cbTiposPrevencionEnfermedades.isUpdateNeeded()) {
	            cbLoadData(TIPOSPREVENCIONENFERMEDADES_TABS);
	        }
	        _temp = cbTiposPrevencionEnfermedades.getCopy();
	        break;
	    case MONTHS :
	        if (cbMonths.isUpdateNeeded()) {
	            cbLoadData(MONTHS);
	        }
	        _temp = cbMonths.getCopy();
	        break;
	    default :
	}
	_temp.setType(_type);
	return _temp;
    }

    private static void cbLoadData(int _type) {
	JCombo combo = new JCombo();
	switch (_type) {
	    case SCHEME :
		combo = cbScheme;
		cbScheme.setGeneralItem(true);
		break;
	    case DATABASE :
		combo = cbDB;
		break;
	    case ACCOUNTTYPE_ISHERITAGE :
		combo = cbAccountType_isHeritage;
		break;
	    case ACCOUNTTYPE_ISRESULT :
		combo = cbAccountType_isResult;
		break;
	    case VOUCHERTYPE_INVOICES :
		combo = cbVoucherTypeInvoice;
		break;
	    case BUDGETS :
		combo = cbBudget;
		break;
	    case PRIORITYREQUEST :
		combo = cbPriorityRequest;
		break;
	    case MINERALIZATION_TABS :
		combo = cbMineralizations;
		break;
	    case COUNTRY_TABS :
		combo = cbCountries;
		break;
	    case PROVINCE_TABS :
		combo = cbProvinces;
		break;
	    case LOCATION_TABS :
		combo = cbLocations;
		break;
	    case NEIGHBORHOOD_TABS :
		combo = cbNeighborhoods;
		break;
	    case STREET_TABS :
		combo = cbStreets;
		break;
	    case ENTITIES_ALLPERSONLIST :
		combo = cbEntities;
		break;
	    case APPLICATION_TABS :
		combo = cbApplications;
		break;
	    case IDENTIFICATION_TABS :
		combo = cbIdentifications;
		break;
	    case PERIODTYPES_LIST :
		combo = cbPeriodType;
		break;
	    case TRIBUTARYCONDITION_TABS :
		combo = cbTributaryCondition;
		break;
	    case SUFFIXPERSON_TABS :
	    case SUFFIXCOMPANY_TABS :
		combo = cbSuffix;
		break;
	    case COMMUNICATIONTYPE_TABS :
		combo = cbCommunicationType;
		break;
	    case COMPANYTYPE_TABS :
		combo = cbCompanyType;
		break;
	    case ADDRESSTYPE_TABS :
		combo = cbAddressType;
		break;
	    case SKILLCOMPANY_TABS :
	    case SKILLPERSON_TABS :
		combo = cbSkill;
		break;
	    case PREFIX_TABS :
		combo = cbPrefix;
		break;
	    case FORMATVIEW_TABS :
		combo = cbFormatView;
		break;
	    case CONTACTTYPE_TABS :
		combo = cbContactType;
		break;
	    case CIVILSTATE_TABS :
		combo = cbCivilState;
		break;
	    case PROFESSION_TABS :
		combo = cbProfession;
		break;
	    case ENTITIES_ALLCOMPANIESLIST :
		combo = cbAllCompaniesList;
		break;
	    case COSTSCENTRE :
		combo = cbCostsCentre;
		break;
	    case TASK_LIST :
		combo = cbTasks;
		break;
	    case USERS :
		combo = cbUsers;
		break;
	    case BANK :
		combo = cbBank;
		break;
	    case MONEYTYPE :
		combo = cbMoneyType;
		break;
	    case ACCOUNTTYPE :
		combo = cbAccountType;
		break;
	    case TRANSACTIONTYPE :
		combo = cbTransactionType;
		break;
	    case OPERATIONMETHOD :
		combo = cbOperationMethod;
		break;
	    case VOUCHERTYPE :
		combo = cbVourcherType;
		break;
	    case RESOURCES :
		combo = cbResources;
		break;
	    case UNITS_TABS :
		combo = cbUnits;
		break;
	    case BRANDS :
		combo = cbBrands;
		break;
	    case SKILLPROVIDER_TABS :
		combo = cbSkillProvider;
		break;
	    case SUBJECT :
		combo = cbSubject;
		break;
	    case LIFETIMETYPES :
		combo = cbLifeTimeTypes;
		break;
	    case CONTRACTTYPE :
		combo = cbContractType;
		break;
	    case PROVIDERS :
		combo = cbProviders;
		break;
	    case RESOURCES_REQUEST :
		combo = cbResourcesRequest;
		break;
	    case CASHMOVEMENTTYPEALL :
		combo = cbCashMovementTypeAll;
		break;
	    case PRIORITY :
		combo = cbPriority;
		break;
	    case STATUS :
		combo = cbStatus;
		break;
	    case ENTITYTYPE :
		combo = cbEntityType;
		break;
	    case CASHMOVEMENTTYPE :
	        combo = cbCashMovementType;
	        break;
	    case DEBITCASHMOVEMENTTYPES :
	        combo = cbDebitCashMovementTypes;
	        break;
	    case BANKACCOUNTS :
		combo = cbAccount;
		break;
	    case CHECKSTATUS :
		combo = cbCheckStatus;
		break;
	    case CHECKTYPE :
		combo = cbCheckType;
		break;
	    case RESOURCESMOVEMENTSTYPES :
		combo = cbResourcesMovementsTypes;
		break;
	    case ALLDEPOTS :
		combo = cbAllDepots;
		break;
	    case REQUESTSTATUS :
		combo = cbRequestStatus;
		break;
	    case RESOURCESMOVEMENTSTYPESBYDELIVERY :
		combo = cbResourcesMovementsTypesByDelivery;
		break;
	    case CATEGORYTYPES :
		combo = cbCategoryTypes;
		break;
	    case MODELS :
		combo = cbModels;
		break;
	    case VOUCHERTYPE_PAYABLE :
		combo = cbVoucherType_Payable;
		break;
	    case ACTIVITY :
	        combo = cbActivity;
	        break;
	    case PERSON :
	        combo = cbPerson;
	        break;
	    case CREDITCASHMOVEMENTTYPES :
	        combo = cbCreditCashMovementTypes;
	        break;
	    case INFRASTRUCTURETYPE_TABS :
	        combo = cbInfrastructureType;
	        break;
	    case SEGURIDAD_TABS :
	        combo = cbSeguridadType;
	        break;
	    case SERVICIOSURBANOS_TABS :
	        combo = cbServUrbanosType;
	        break;
	    case CULTOYCULTURA_TABS :
	        combo = cbCultoyCulturaType;
	        break;
	    case ADMINISTRACIONYSERVICIOS_TABS :
	        combo = cbAdminyServ;
	        break;
	    case ACTIVIDADES_TABS :
	        combo = cbActividades;
	        break;
	    case CATEGORIASCOMERCIALES_TABS :
	        combo = cbCategoriaComercial;
	        break;
	    case RUBROSCOMERCIALES_TABS :
	        combo = cbRubrosComerciales;
	        break;
	    case INDUSTRIAS_TABS :
	        combo = cbIndustrias;
	        break;
	    case PRODUCCION_TABS :
	        combo = cbProduccion;
	        break;
	    case ESTABLECIMIENTOS_TABS :
	        combo = cbEstablecimientos;
	        break;
	    case NIVELESEDUCATIVOS_TABS :
	        combo = cbNivelesEducativos;
	        break;
	    case ORIENTACIONESEDUCATIVAS_TABS :
	        combo = cbOrientacionesEducativas;
	        break;
	    case INSTALACIONESTURISTICAS_TABS :
	        combo = cbInstalacionesTuristicas;
	        break;
	    case CATEGORIASTURISTICAS_TABS :
	        combo = cbCategoriasTuristicas;
	   break;
	    case AUTOMOTORES_TABS :
	        combo = cbAutomotores;
	    break;
	    case ENFERMEDADES_TABS :
	        combo = cbEnfermedades;
	    break;
	    case CASOSENFERMEDADES_TABS :
	        combo = cbCasosEnfermedades;
	    break;
	    case TIPOSPREVENCIONENFERMEDADES_TABS :
	        combo = cbTiposPrevencionEnfermedades;
	    break;
	    case MONTHS :
	        combo = cbMonths;
	    break;
	    default :
		break;
	}
	loadJCombo(combo, getSQL(_type));
    }

    public static String getSQL(int _type) {
	String query = "";
	switch (_type) {
	    case SCHEME :
		query = "SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' ORDER BY nspname";
		break;
	    case DATABASE :
		query = "SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn ORDER BY datname";
		break;
	    case ACCOUNTTYPE_ISHERITAGE :
		query = "SELECT idaccounttype, name, 0 FROM  accounting.accounttypes WHERE estado<>'*' AND isheritage=true";
		break;
	    case ACCOUNTTYPE_ISRESULT :
		query = "SELECT idaccounttype, name, 0 FROM accounting.accounttypes WHERE estado<>'*' AND isheritage=false";
		break;
	    case VOUCHERTYPE_INVOICES :
		query = "SELECT idvouchertype, name, 0 FROM  tabs.vouchertype_tabs WHERE estado<>'*' AND isinvoice=true";
		break;
	    case BUDGETS :
		query = "SELECT idbudget, name , (initialamount - assignedamounttocc) AS limitamount FROM cashflow.budgets WHERE estado <> '*' ORDER BY name";
		break;
	    case PRIORITYREQUEST :
		query = "SELECT idpriorityrequest, name, 0 FROM tabs.priorityrequest_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case REQUESTSTATUS :
		query = "SELECT idrequeststatus, name, 0 FROM tabs.requeststatus_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case MINERALIZATION_TABS :
		query = "SELECT idmin_tab, name, 0 FROM tabs.mineralization_tabs WHERE status <> '*' AND idmin_tab > 0 ORDER BY name";
		break;
	    case COUNTRY_TABS :
		query = "SELECT idcountry, name, 0 FROM tabs.country_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case PROVINCE_TABS :
		query = "SELECT idprovince, province_tabs.name || ' (' ||\n" + "country_tabs.name || ')', province_tabs.idcountry FROM tabs.province_tabs \n" + "INNER JOIN tabs.country_tabs ON province_tabs.idcountry = country_tabs.idcountry\n" + "WHERE province_tabs.estado <> '*' ORDER BY province_tabs.name ";
		break;
	    case LOCATION_TABS :
		query = "SELECT idlocation, location_tabs.name || ' (' ||\n" + "province_tabs.name || ')', location_tabs.idprovince FROM tabs.location_tabs \n" + "INNER JOIN tabs.province_tabs ON location_tabs.idprovince = province_tabs.idprovince\n" + "WHERE location_tabs.estado <> '*' ORDER BY location_tabs.name";
		break;
	    case NEIGHBORHOOD_TABS :
		query = "SELECT idneighborhood, name, idlocation FROM tabs.neighborhood_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case STREET_TABS :
		query = "SELECT idstreet, name, idlocation FROM tabs.street_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case ENTITIES_ALLPERSONLIST :
		query = "SELECT persons.idperson, (persons.lastname||', '||persons.name) as name, 0 FROM file.entities LEFT JOIN org.persons persons ON entities.idreference = persons.idperson WHERE person = 'true'";
		break;
	    case APPLICATION_TABS :
		query = "SELECT idapplication_tab, name AS name, 0 FROM tabs.application_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case IDENTIFICATION_TABS :
		query = "SELECT ididentification, name AS name, 0 FROM tabs.identification_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case PERIODTYPES_LIST :
		query = "SELECT idperiodtype, name AS name, 0 FROM tabs.periodtype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case TRIBUTARYCONDITION_TABS :
		query = "SELECT idtributarycondition,name AS name, 0 FROM tabs.tributarycondition_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case SUFFIXPERSON_TABS :
		query = "SELECT idsuffix,name AS name, 0 FROM tabs.suffix_tabs WHERE estado <> '*' AND iscompany=FALSE ORDER BY name";
		break;
	    case SUFFIXCOMPANY_TABS :
		query = "SELECT idsuffix,name AS name, 0 FROM tabs.suffix_tabs WHERE estado <> '*' AND iscompany=TRUE ORDER BY name";
		break;
	    case COMMUNICATIONTYPE_TABS :
		query = "SELECT idcommunicationtype,name AS name, 0 FROM tabs.communicationtype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case COMPANYTYPE_TABS :
		query = "SELECT idcompanytype,name AS name, 0 FROM tabs.companytype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case ADDRESSTYPE_TABS :
		query = "SELECT idaddresstype,name AS name, 0 FROM tabs.addresstype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case SKILLCOMPANY_TABS :
		query = "SELECT idskill,name AS name, 0 FROM tabs.skill_tabs WHERE estado <> '*' AND tocompany=true OR toprovider=true ORDER BY name";
		break;
	    case SKILLPERSON_TABS :
		query = "SELECT idskill,name AS name, 0 FROM tabs.skill_tabs WHERE estado <> '*' AND toperson=true ORDER BY name";
		break;
	    case PREFIX_TABS :
		query = "SELECT idprefix,name AS name, 0 FROM tabs.prefix_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case FORMATVIEW_TABS :
		query = "SELECT idformatview,name AS name, 0 FROM tabs.formatview_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case CONTACTTYPE_TABS :
		query = "SELECT idcontacttype,name AS name, 0 FROM tabs.contacttype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case CIVILSTATE_TABS :
		query = "SELECT idcivilstate,name AS name, 0 FROM tabs.civilstate_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case PROFESSION_TABS :
		query = "SELECT idprofession,name AS name, 0 FROM tabs.profession_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case ENTITIES_ALLCOMPANIESLIST :
		query = "SELECT comp.idcompany, comp.name AS name,0 FROM file.entities LEFT JOIN org.companies comp ON entities.idreference = comp.idcompany WHERE person = 'false'";
		break;
	    case COSTSCENTRE :
		query = "SELECT idcostcentre, code ||' - '|| name AS name,0 FROM cashflow.costscentres WHERE estado<>'*' ORDER BY name";
		break;
	    case TASK_LIST :
		query = "SELECT idtask, name AS name,0 FROM task.task WHERE estado<>'*'";
		break;
	    case USERS :
		query = "SELECT iduser, username AS name,0 FROM org.users WHERE estado<>'*'";
		break;
	    case BANK :
		query = "SELECT org.companies.idcompany, org.companies.name, 0 FROM  org.companyskills,org.companies WHERE companies.idcompany = companyskills.idcompany AND idskill=6 /* HABILIDAD BANKING */AND companies.estado<>'*'";
		break;
	    case MONEYTYPE :
		query = "SELECT idmoneytype, name, 0 FROM  tabs.moneytype_tabs WHERE estado<>'*'";
		break;
	    case ACCOUNTTYPE :
		query = "SELECT idaccounttype, name, 0 FROM  tabs.accounttype_tabs WHERE estado<>'*' AND idaccounttype > 0";
		break;
	    case TRANSACTIONTYPE :
		query = "SELECT idtransactiontype, name, 0 FROM  tabs.transactiontype_tabs WHERE estado<>'*'";
		break;
	    case OPERATIONMETHOD :
		query = "SELECT idoperationmethod, name, 0 FROM  tabs.operationmethod_tabs WHERE estado<>'*'";
		break;
	    case VOUCHERTYPE :
		query = "SELECT idvouchertype, name, 0 FROM  tabs.vouchertype_tabs WHERE estado<>'*'";
		break;
	    case RESOURCES :
		query = "SELECT idresource, name As name, 0 FROM  resourcescontrol.resources WHERE estado <> '*' ORDER BY name";
		break;
	    case UNITS_TABS :
		query = "SELECT idunit, name, 0 FROM  tabs.units_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case BRANDS :
		query = "SELECT idbrand, name, 0 FROM  resourcescontrol.brands WHERE estado <> '*' ORDER BY name ";
		break;
	    case SKILLPROVIDER_TABS :
		query = "SELECT idskill,name AS name, 0 FROM tabs.skill_tabs WHERE estado <> '*' AND toprovider=true ORDER BY name";
		break;
	    case SUBJECT :
		query = "SELECT iddestination, name As name, 0 FROM  resourcescontrol.destinations WHERE estado <> '*' ORDER BY name";
		break;
	    case LIFETIMETYPES :
		query = "SELECT idlifetimetype, name As name, 0 FROM  tabs.lifetimetype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case CONTRACTTYPE :
		query = "SELECT idcontracttype, name As name, 0 FROM  tabs.contracttype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case PROVIDERS :
		query = "SELECT idcompany, name As name, 0 FROM  org.companies WHERE estado <> '*' ORDER BY name";
		break;
	    case RESOURCES_REQUEST :
		query = "SELECT idresourcesrequest, number AS name, 0 FROM  resourcescontrol.resourcesrequests WHERE estado <> '*' ORDER BY name";
		break;
	    case CASHMOVEMENTTYPEALL :
		query = "SELECT idcashmovementtype, name, 0 FROM  cashflow.cashmovementtypes WHERE estado <> '*' AND idcashmovementtype<>0 AND idparent<>0 ORDER BY name";
		break;
	    case PRIORITY :
		query = "SELECT idpriority, name, 0 FROM  calendar.newspriorities WHERE estado <> '*' ORDER BY idpriority";
		break;
	    case STATUS :
		query = "SELECT idstatus, name, 0 FROM  calendar.newsstatus WHERE estado <> '*' ORDER BY name";
		break;
	    case ENTITYTYPE :
		query = "SELECT identitytype, name, 0 FROM  tabs.entitytype_tabs WHERE estado <> '*' ORDER BY name";
		break;
	    case CASHMOVEMENTTYPE :
		query = "SELECT idcashmovementtype, name || CASE WHEN sign > 0 THEN ' (Ingreso)' ELSE ' (Egreso)' END AS name, idparent||'#'||sign FROM cashflow.cashmovementtypes WHERE estado <> '*' AND idcashmovementtype<>0 /*AND idcashmovementtype NOT IN (SELECT idparent FROM cashflow.cashmovementtypes WHERE estado <> '*')*/ ORDER BY idcashmovementtype";
		break;
	    case DEBITCASHMOVEMENTTYPES :
	        query = "SELECT idcashmovementtype, name || CASE WHEN sign > 0 THEN ' (Ingreso)' ELSE ' (Egreso)' END AS name, idparent||'#'||sign FROM cashflow.cashmovementtypes WHERE estado <> '*' AND idcashmovementtype<>0 AND sign < 0 /*AND idcashmovementtype NOT IN (SELECT idparent FROM cashflow.cashmovementtypes WHERE estado <> '*')*/ ORDER BY idcashmovementtype";
	        break;
	    case BANKACCOUNTS :
		query = "SELECT idbankaccount, name, remaining FROM (SELECT idbankaccount, accounttype_tabs.name ||' Nº '||number AS name, (SELECT 100-PUBLIC.ISNULL(SUM(amountp),0)::integer  FROM cashflow.budgetbankaccounts WHERE budgetbankaccounts.idbankaccount = bankaccounts.idbankaccount) AS remaining FROM cashflow.bankaccounts, tabs.accounttype_tabs WHERE bankaccounts.idaccounttype = accounttype_tabs.idaccounttype AND bankaccounts.estado<>'*') as foo WHERE remaining > 0";
		break;
	    case CHECKSTATUS :
		query = "SELECT idcheckstatus, name, 0 FROM tabs.checkstatus_tabs WHERE estado<>'*'";
		break;
	    case CHECKTYPE :
		query = "SELECT idchecktype, name, 0 FROM tabs.checktype_tabs WHERE estado<>'*'";
		break;
	    case RESOURCESMOVEMENTSTYPES :
		query = "SELECT idmovementtype, name, 0 FROM resourcescontrol.movementtypes WHERE estado <> '*' ORDER BY name";
		break;
	    case ALLDEPOTS :
		query = "SELECT iddepot, name, 0 FROM resourcescontrol.depots WHERE estado <> '*' ORDER BY name ";
		break;
	    case RESOURCESMOVEMENTSTYPESBYDELIVERY :
		query = "SELECT	idmovementtype, name, 0 FROM resourcescontrol.movementtypes WHERE movementtypes.estado <> '*' AND movementtypes.idmovementtype in (2,3)";
		break;
	    case CATEGORYTYPES :
		query = "SELECT idcategoriaautomotor, categoria AS name, idtipoautomotor FROM taxes.categoriasautomotores WHERE categoriasautomotores.estado <> '*' ORDER BY categoria";
		break;
	    case MODELS :
		query = "SELECT distinct(anio) AS name, 0 FROM impuestos.cuotasxcategoria WHERE cuotasxcategoria.estado <> '*' ORDER BY anio";
		break;
	    case VOUCHERTYPE_PAYABLE :
		query = "SELECT idvouchertype, name, 0 FROM tabs.vouchertype_tabs WHERE vouchertype_tabs.estado <> '*' AND ispayable";
		break;
	    case ACTIVITY :
	        query = "SELECT idactivity, name, 0 FROM tabs.activity_tabs WHERE estado <> '*'";
	        break;
	    case PERSON :
	        query = "SELECT idperson, lastname ||', '|| name AS name, 0 FROM org.persons WHERE estado <> '*'";
	        break;
	    case CREDITCASHMOVEMENTTYPES :
	        query = "SELECT idcashmovementtype, name || CASE WHEN sign > 0 THEN ' (Ingreso)' ELSE ' (Egreso)' END AS name, idparent||'#'||sign FROM cashflow.cashmovementtypes WHERE estado <> '*' AND idcashmovementtype <> 0 AND sign > 0 /*AND idcashmovementtype NOT IN (SELECT idparent FROM cashflow.cashmovementtypes WHERE estado <> '*')*/ ORDER BY idcashmovementtype";
	        break;
	    case INFRASTRUCTURETYPE_TABS :
	        query = "SELECT idtype, name, 0 FROM tabs.infrastructuretype_tabs WHERE estado <> '*' ORDER BY name";
	        break;
	    case SEGURIDAD_TABS :
                //query =  "SELECT idtype, name, 0 FROM " + GaiaEnvironment.scheme + ".tiposseguridad WHERE estado <> '*' ORDER BY nombre";
                query =  "SELECT idtipo, nombre AS name, 0 FROM tabs.tiposseguridad WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case SERVICIOSURBANOS_TABS :
	        query =  "SELECT idtiposervurbano, nombre AS name, 0 FROM tabs.tiposserviciosurbanos WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case CULTOYCULTURA_TABS :
	        query =  "SELECT idtipocultoycultura, nombre AS name, 0 FROM tabs.tiposcultoycultura WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case ADMINISTRACIONYSERVICIOS_TABS :
	        query =  "SELECT idtipoadminyserv, nombre AS name, 0 FROM tabs.tiposadministracionyservicios WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case ACTIVIDADES_TABS :
	        query =  "SELECT idtipoactividad, nombre AS name, 0 FROM tabs.tiposactividades WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case CATEGORIASCOMERCIALES_TABS :
	        query =  "SELECT idcategoriacomercial, nombre AS name, 0 FROM tabs.categoriascomerciales WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case RUBROSCOMERCIALES_TABS :
	        query =  "SELECT idrubrocomercial, nombre AS name, idcategoriacomercial FROM tabs.rubroscomerciales WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case INDUSTRIAS_TABS :
	        query =  "SELECT idtipoindustria, nombre AS name, 0 FROM tabs.tiposindustrias WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case PRODUCCION_TABS :
	        query =  "SELECT idtipoproduccion, nombre AS name, 0 FROM tabs.tiposproduccion WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case ESTABLECIMIENTOS_TABS :
	        query =  "SELECT idtipoestablecimiento, nombre AS name, 0 FROM tabs.tiposestablecimientos WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case NIVELESEDUCATIVOS_TABS :
	        query =  "SELECT idniveleducativo, nombre AS name, 0 FROM tabs.niveleseducativos WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case ORIENTACIONESEDUCATIVAS_TABS :
	        query =  "SELECT idorientacion, nombre AS name, 0 FROM tabs.orientacioneseducativas WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case INSTALACIONESTURISTICAS_TABS :
	        query =  "SELECT idtipoinsttur, nombre AS name, 0 FROM tabs.tiposinstalacionesturisticas WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case CATEGORIASTURISTICAS_TABS :
	        query =  "SELECT idcattur, nombre AS name, 0 FROM tabs.categoriasturisticas WHERE estado <> '*' ORDER BY nombre";
	        break;
	    case AUTOMOTORES_TABS :
	        query =  "SELECT idtipoautomotor AS id, nombre AS name, 0 FROM taxes.tiposautomotores WHERE estado <> '*' AND idtipoautomotor > 0 ORDER BY idtipoautomotor";
	        break;
	    case ENFERMEDADES_TABS :
	        query =  "SELECT idtipo AS id, nombre AS name, 0 FROM tabs.enfermedades_tabs WHERE estado <> '*' AND idtipo > 0 ORDER BY nombre";
	        break;
	    case CASOSENFERMEDADES_TABS :
	        query =  "SELECT idtipo AS id, nombre AS name, 0 FROM tabs.casosenfermedades_tabs WHERE estado <> '*' AND idtipo > 0 ORDER BY nombre";
	        break;
	    case TIPOSPREVENCIONENFERMEDADES_TABS :
	        query =  "SELECT idtipo AS id, nombre AS name, 0 FROM tabs.tiposprevencionenfermedades_tabs WHERE estado <> '*' AND idtipo > 0 ORDER BY nombre";
	        break;
	    case MONTHS :
	        query =  "SELECT idmes AS id, mes AS name, 0 FROM tabs.meses_tabs WHERE estado <> '*' ORDER BY idmes";
	        break;
	    default :
		break;
	}
	return query;
    }

    public static void loadJCombo(JCombo combo, String query) {
	if (LibSQL.isConnected())
	    combo.loadJCombo(query);
    }

    public static ExtendedInternalFrame getAddItemBasicDialog(int _type) {
	ExtendedInternalFrame _dialog;
	switch (_type) {
	    case SKILLPROVIDER_TABS :
		_dialog = new ExtendedInternalFrame("Habilidades");
		SkillList skillList = new SkillList();
		_dialog.setCentralPanel(skillList);
		return _dialog;
	    case UNITS_TABS :
		_dialog = new ExtendedInternalFrame("Unidades");
		UnitsList unitListPanel = new UnitsList();
		_dialog.setCentralPanel(unitListPanel);
		return _dialog;
	    case BRANDS :
		_dialog = new ExtendedInternalFrame("Marcas");
		BrandsList brandsListPanel = new BrandsList();
		_dialog.setCentralPanel(brandsListPanel);
		return _dialog;
	    case ACTIVITY :
		_dialog = new ExtendedInternalFrame("Actividades");
		ActivitiesList activitiesListPanel = new ActivitiesList();
		_dialog.setCentralPanel(activitiesListPanel);
		return _dialog;
	    case INFRASTRUCTURETYPE_TABS :
	        _dialog = new ExtendedInternalFrame("Infraestructuras Urbanas");
	        GaiaInfrastructureTypesList infrastructuresTypesList = new GaiaInfrastructureTypesList();
	        _dialog.setCentralPanel(infrastructuresTypesList);
	        return _dialog;
	   /* case SEGURIDADTYPE_TABS :
	        _dialog = new ExtendedInternalFrame("Infraestructuras Urbanas");
	        GaiaSeguridadTypesList seguridadTypesList = new GaiaSeguridadTypesList();
	        _dialog.setCentralPanel(seguridadTypesList);
	        return _dialog;*/
	    case ADMINISTRACIONYSERVICIOS_TABS :
	         _dialog = new ExtendedInternalFrame("Tipos Administración y Servicios");
	         GaiaTiposAdminyServList tiposAdminyServList = new GaiaTiposAdminyServList();
	         _dialog.setCentralPanel(tiposAdminyServList);
	         return _dialog;
	    case ACTIVIDADES_TABS :
	         _dialog = new ExtendedInternalFrame("Tipos Actividades Administración/Servicios");
	         GaiaTiposActividadesAdminyServList tiposActividadesAdminyServList = new GaiaTiposActividadesAdminyServList();
	         _dialog.setCentralPanel(tiposActividadesAdminyServList);
	         return _dialog;
	    case CULTOYCULTURA_TABS :
	         _dialog = new ExtendedInternalFrame("Tipos Cultos y Culturas");
	         GaiaTiposCultosyCulturaList tiposCultosyculturaList = new GaiaTiposCultosyCulturaList();
	         _dialog.setCentralPanel(tiposCultosyculturaList);
	         return _dialog;
	    default :
		return null;
	}
    }

    public static void refreshCombo(JCombo _combo, int _type) {
	_combo.setUpdateNeeded(true);
	loadJCombo(_combo, getSQL(_type));
	switch (_type) {
	    case SCHEME :
		cbScheme.setUpdateNeeded(true);
		cbScheme = _combo.getCopy();
		break;
	    case DATABASE :
		cbDB.setUpdateNeeded(true);
		cbDB = _combo.getCopy();
		break;
	    case VOUCHERTYPE_INVOICES :
		cbVoucherTypeInvoice.setUpdateNeeded(true);
		cbVoucherTypeInvoice = _combo.getCopy();
		break;
	    case ACCOUNTTYPE_ISHERITAGE :
		cbAccountType_isHeritage.setUpdateNeeded(true);
		cbAccountType_isHeritage = _combo.getCopy();
		break;
	    case ACCOUNTTYPE_ISRESULT :
		cbAccountType_isResult.setUpdateNeeded(true);
		cbAccountType_isResult = _combo.getCopy();
		break;
	    case BUDGETS :
		cbBudget.setUpdateNeeded(true);
		cbBudget = _combo.getCopy();
		break;
	    case PRIORITYREQUEST :
		cbPriorityRequest.setUpdateNeeded(true);
		cbPriorityRequest = _combo.getCopy();
		break;
	    case REQUESTSTATUS :
		cbRequestStatus.setUpdateNeeded(true);
		cbRequestStatus = _combo.getCopy();
		break;
	    case MINERALIZATION_TABS :
		cbMineralizations.setUpdateNeeded(true);
		cbMineralizations = _combo.getCopy();
		break;
	    case COUNTRY_TABS :
		cbCountries.setUpdateNeeded(true);
		cbCountries = _combo.getCopy();
		break;
	    case PROVINCE_TABS :
		cbProvinces.setUpdateNeeded(true);
		cbProvinces = _combo.getCopy();
		break;
	    case LOCATION_TABS :
		cbLocations.setUpdateNeeded(true);
		cbLocations = _combo.getCopy();
		break;
	    case NEIGHBORHOOD_TABS :
		cbNeighborhoods.setUpdateNeeded(true);
		cbNeighborhoods = _combo.getCopy();
		break;
	    case STREET_TABS :
		cbStreets.setUpdateNeeded(true);
		cbStreets = _combo.getCopy();
		break;
	    case ENTITIES_ALLPERSONLIST :
		cbEntities.setUpdateNeeded(true);
		cbEntities = _combo.getCopy();
		break;
	    case APPLICATION_TABS :
		cbApplications.setUpdateNeeded(true);
		cbApplications = _combo.getCopy();
		break;
	    case IDENTIFICATION_TABS :
		cbIdentifications.setUpdateNeeded(true);
		cbIdentifications = _combo.getCopy();
		break;
	    case PERIODTYPES_LIST :
		cbPeriodType.setUpdateNeeded(true);
		cbPeriodType = _combo.getCopy();
		break;
	    case TRIBUTARYCONDITION_TABS :
		cbTributaryCondition.setUpdateNeeded(true);
		cbTributaryCondition = _combo.getCopy();
		break;
	    case SUFFIXPERSON_TABS :
	    case SUFFIXCOMPANY_TABS :
		cbSuffix.setUpdateNeeded(true);
		cbSuffix = _combo.getCopy();
		break;
	    case COMMUNICATIONTYPE_TABS :
		cbCommunicationType.setUpdateNeeded(true);
		cbCommunicationType = _combo.getCopy();
		break;
	    case COMPANYTYPE_TABS :
		cbCompanyType.setUpdateNeeded(true);
		cbCompanyType = _combo.getCopy();
		break;
	    case ADDRESSTYPE_TABS :
		cbAddressType.setUpdateNeeded(true);
		cbAddressType = _combo.getCopy();
		break;
	    case SKILLCOMPANY_TABS :
	    case SKILLPERSON_TABS :
		cbSkill.setUpdateNeeded(true);
		cbSkill = _combo.getCopy();
		break;
	    case PREFIX_TABS :
		cbPrefix.setUpdateNeeded(true);
		cbPrefix = _combo.getCopy();
		break;
	    case FORMATVIEW_TABS :
		cbFormatView.setUpdateNeeded(true);
		cbFormatView = _combo.getCopy();
		break;
	    case CONTACTTYPE_TABS :
		cbContactType.setUpdateNeeded(true);
		cbContactType = _combo.getCopy();
		break;
	    case CIVILSTATE_TABS :
		cbCivilState.setUpdateNeeded(true);
		cbCivilState = _combo.getCopy();
		break;
	    case PROFESSION_TABS :
		cbProfession.setUpdateNeeded(true);
		cbProfession = _combo.getCopy();
		break;
	    case ENTITIES_ALLCOMPANIESLIST :
		cbAllCompaniesList.setUpdateNeeded(true);
		cbAllCompaniesList = _combo.getCopy();
		break;
	    case COSTSCENTRE :
		cbCostsCentre.setUpdateNeeded(true);
		cbCostsCentre = _combo.getCopy();
		break;
	    case TASK_LIST :
		cbTasks.setUpdateNeeded(true);
		cbTasks = _combo.getCopy();
		break;
	    case BANK :
		cbBank.setUpdateNeeded(true);
		cbBank = _combo.getCopy();
		break;
	    case MONEYTYPE :
		cbMoneyType.setUpdateNeeded(true);
		cbMoneyType = _combo.getCopy();
		break;
	    case ACCOUNTTYPE :
		cbAccountType.setUpdateNeeded(true);
		cbAccountType = _combo.getCopy();
		break;
	    case TRANSACTIONTYPE :
		cbTransactionType.setUpdateNeeded(true);
		cbTransactionType = _combo.getCopy();
		break;
	    case OPERATIONMETHOD :
		cbOperationMethod.setUpdateNeeded(true);
		cbOperationMethod = _combo.getCopy();
		break;
	    case VOUCHERTYPE :
		cbVourcherType.setUpdateNeeded(true);
		cbVourcherType = _combo.getCopy();
		break;
	    case VALUEBLOCK :
		cbValueBlock.setUpdateNeeded(true);
		cbValueBlock = _combo.getCopy();
		break;
	    case RESOURCES :
		cbResources.setUpdateNeeded(true);
		cbResources = _combo.getCopy();
		break;
	    case UNITS_TABS :
		cbUnits.setUpdateNeeded(true);
		cbUnits = _combo.getCopy();
		break;
	    case BRANDS :
		cbBrands.setUpdateNeeded(true);
		cbBrands = _combo.getCopy();
		break;
	    case SKILLPROVIDER_TABS :
		cbSkillProvider.setUpdateNeeded(true);
		cbSkillProvider = _combo.getCopy();
		break;
	    case LIFETIMETYPES :
		cbLifeTimeTypes.setUpdateNeeded(true);
		cbLifeTimeTypes = _combo.getCopy();
		break;
	    case CONTRACTTYPE :
		cbContractType.setUpdateNeeded(true);
		cbContractType = _combo.getCopy();
		break;
	    case PROVIDERS :
		cbProviders.setUpdateNeeded(true);
		cbProviders = _combo.getCopy();
		break;
	    case RESOURCES_REQUEST :
		cbResourcesRequest.setUpdateNeeded(true);
		cbResourcesRequest = _combo.getCopy();
		break;
	    case CASHMOVEMENTTYPEALL :
		cbCashMovementTypeAll.setUpdateNeeded(true);
		cbCashMovementTypeAll = _combo.getCopy();
		break;
	    case PRIORITY :
		cbPriority.setUpdateNeeded(true);
		cbPriority = _combo.getCopy();
		break;
	    case STATUS :
		cbStatus.setUpdateNeeded(true);
		cbStatus = _combo.getCopy();
		break;
	    case ENTITYTYPE :
		cbEntityType.setUpdateNeeded(true);
		cbEntityType = _combo.getCopy();
		break;
	    case CASHMOVEMENTTYPE :
	        cbCashMovementType.setUpdateNeeded(true);
	        cbCashMovementType = _combo.getCopy();
	        break;
	    case DEBITCASHMOVEMENTTYPES :
	        cbDebitCashMovementTypes.setUpdateNeeded(true);
	        cbDebitCashMovementTypes = _combo.getCopy();
	        break;
	    case BANKACCOUNTS :
		cbAccount.setUpdateNeeded(true);
		cbAccount = _combo.getCopy();
		break;
	    case CHECKSTATUS :
		cbCheckStatus.setUpdateNeeded(true);
		cbCheckStatus = _combo.getCopy();
		break;
	    case CHECKTYPE :
		cbCheckType.setUpdateNeeded(true);
		cbCheckType = _combo.getCopy();
		break;
	    case RESOURCESMOVEMENTSTYPES :
		cbResourcesMovementsTypes.setUpdateNeeded(true);
		cbResourcesMovementsTypes = _combo.getCopy();
		break;
	    case ALLDEPOTS :
		cbAllDepots.setUpdateNeeded(true);
		cbAllDepots = _combo.getCopy();
		break;
	    case RESOURCESMOVEMENTSTYPESBYDELIVERY :
		cbResourcesMovementsTypesByDelivery.setUpdateNeeded(true);
		cbResourcesMovementsTypesByDelivery = _combo.getCopy();
		break;
	    case CATEGORYTYPES :
		cbCategoryTypes.setUpdateNeeded(true);
		cbCategoryTypes = _combo.getCopy();
		break;
	    case MODELS :
		cbModels.setUpdateNeeded(true);
		cbModels = _combo.getCopy();
		break;
	    case VOUCHERTYPE_PAYABLE :
		cbVoucherType_Payable.setUpdateNeeded(true);
		cbVoucherType_Payable = _combo.getCopy();
		break;
	    case ACTIVITY :
	        cbActivity.setUpdateNeeded(true);
	        cbActivity = _combo.getCopy();
	        break;
	    case PERSON :
	        cbPerson.setUpdateNeeded(true);
	        cbPerson = _combo.getCopy();
	        break;
	    case CREDITCASHMOVEMENTTYPES :
	        cbCreditCashMovementTypes.setUpdateNeeded(true);
	        cbCreditCashMovementTypes = _combo.getCopy();
	        break;
	    case INFRASTRUCTURETYPE_TABS :
	        cbInfrastructureType.setUpdateNeeded(true);
	        cbInfrastructureType = _combo.getCopy();
	        break;
	    case SEGURIDAD_TABS :
	        cbSeguridadType.setUpdateNeeded(true);
	        cbSeguridadType = _combo.getCopy();
	        break;
	    case SERVICIOSURBANOS_TABS :
	        cbServUrbanosType.setUpdateNeeded(true);
	        cbServUrbanosType = _combo.getCopy();
	        break;
	    case CULTOYCULTURA_TABS :
	        cbCultoyCulturaType.setUpdateNeeded(true);
	        cbCultoyCulturaType = _combo.getCopy();
	        break;
	    case ADMINISTRACIONYSERVICIOS_TABS :
	        cbAdminyServ.setUpdateNeeded(true);
	        cbAdminyServ = _combo.getCopy();
	        break;
	    case ACTIVIDADES_TABS :
	        cbActividades.setUpdateNeeded(true);
	        cbActividades = _combo.getCopy();
	        break;
	    case CATEGORIASCOMERCIALES_TABS :
	        cbCategoriaComercial.setUpdateNeeded(true);
	        cbCategoriaComercial = _combo.getCopy();
	        break;
	    case RUBROSCOMERCIALES_TABS :
	        cbRubrosComerciales.setUpdateNeeded(true);
	        cbRubrosComerciales = _combo.getCopy();
	        break;
	    case INDUSTRIAS_TABS :
	        cbIndustrias.setUpdateNeeded(true);
	        cbIndustrias = _combo.getCopy();
	        break;
	    case PRODUCCION_TABS :
	        cbProduccion.setUpdateNeeded(true);
	        cbProduccion = _combo.getCopy();
	        break;
	    case ESTABLECIMIENTOS_TABS :
	        cbEstablecimientos.setUpdateNeeded(true);
	        cbEstablecimientos = _combo.getCopy();
	        break;
	    case NIVELESEDUCATIVOS_TABS :
	        cbNivelesEducativos.setUpdateNeeded(true);
	        cbNivelesEducativos = _combo.getCopy();
	        break;
	    case ORIENTACIONESEDUCATIVAS_TABS :
	        cbOrientacionesEducativas.setUpdateNeeded(true);
	        cbOrientacionesEducativas = _combo.getCopy();
	        break;
	    case INSTALACIONESTURISTICAS_TABS :
	        cbInstalacionesTuristicas.setUpdateNeeded(true);
	        cbInstalacionesTuristicas = _combo.getCopy();
	        break;
	    case CATEGORIASTURISTICAS_TABS :
	        cbCategoriasTuristicas.setUpdateNeeded(true);
	        cbCategoriasTuristicas = _combo.getCopy();
	        break;
	    case AUTOMOTORES_TABS :
	        cbAutomotores.setUpdateNeeded(true);
	        cbAutomotores = _combo.getCopy();
	        break;
	    case ENFERMEDADES_TABS :
	        cbEnfermedades.setUpdateNeeded(true);
	        cbEnfermedades = _combo.getCopy();
	        break;
	    case CASOSENFERMEDADES_TABS :
	        cbCasosEnfermedades.setUpdateNeeded(true);
	        cbCasosEnfermedades = _combo.getCopy();
	        break;
	    case TIPOSPREVENCIONENFERMEDADES_TABS :
	        cbTiposPrevencionEnfermedades.setUpdateNeeded(true);
	        cbTiposPrevencionEnfermedades = _combo.getCopy();
	        break;
	    case MONTHS :
	        cbMonths.setUpdateNeeded(true);
	        cbMonths = _combo.getCopy();
	        break;
	    default :
		break;
	}
    }

}
