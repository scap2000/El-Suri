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
 * ExpenditureAccount.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.awt.Color;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.sql.LibSQL;

public class ExpenditureAccount {

    private int idExpenditureAccount;
    private int idParent;
    private String name;
    private String startDate;
    private String endDate;
    private String code;
    private String description;
    private int idPeriodType;
    private double initialAmount = 0;
    private double initialAmountP = 0;
    private double assignedAmountToET = 0;
    private double assignedAmountToETp = 0;
    private double assignedAmountToCC = 0;
    private double assignedAmountToCCp = 0;
    private int valueBlock;
    private double spentAmount;
    private double spentAmountP;
    private double availableAmount;
    private double modifiedAmount;
    private boolean closed;
    private int idBudget;
    private int idBudgetExpenditureAccount;
    private Color initialAmountColor = Color.BLUE;
    private Color assignedAmountToETColor = Color.ORANGE;
    private Color assignedAmountToCCColor = Color.ORANGE;

    public ExpenditureAccount() {

    }

    public ExpenditureAccount(int _idExpenditureAccount) {
	idExpenditureAccount = _idExpenditureAccount;
    }

    public ExpenditureAccount(int _idExpenditureAccount, String _name) {
	idExpenditureAccount = _idExpenditureAccount;
	name = _name;
    }

    public void setStartDate(String _startDate) {
	startDate = _startDate;
    }

    public String getStartDate() {
	if (startDate != null)
	    return startDate;
	else
	    return "";
    }

    public void setEndDate(String _endDate) {
	endDate = _endDate;
    }

    public String getEndDate() {
	if (endDate != null)
	    return endDate;
	else
	    return "";
    }

    public void setIdPeriodType(int _idPeriodType) {
	idPeriodType = _idPeriodType;
    }

    public int getIdPeriodType() {
	return idPeriodType;
    }

    public void setInitialAmount(double _initialAmount) {
	initialAmount = _initialAmount;
    }

    public double getInitialAmount() {
	return initialAmount;
    }

    public void setClosed(boolean _closed) {
	closed = _closed;
    }

    public boolean isClosed() {
	return closed;
    }

    public void setIDExpenditureAccount(int _idExpenditureAccount) {
	idExpenditureAccount = _idExpenditureAccount;
    }

    public int getIDExpenditureAccount() {
	return idExpenditureAccount;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setAssignedAmountToET(double _assignedAmountToET) {
	assignedAmountToET = _assignedAmountToET;
    }

    public double getAssignedAmountToET() {
	return assignedAmountToET;
    }

    public void setAssignedAmountToCC(double _assignedAmountToCC) {
	assignedAmountToCC = _assignedAmountToCC;
    }

    public double getAssignedAmountToCC() {
	return assignedAmountToCC;
    }

    public void setInitialAmountColor(Color initialAmountColor) {
	this.initialAmountColor = initialAmountColor;
    }

    public Color getInitialAmountColor() {
	return initialAmountColor;
    }

    public void setAssignedAmountToETColor(Color assignedAmountToETColor) {
	this.assignedAmountToETColor = assignedAmountToETColor;
    }

    public Color getAssignedAmountToETColor() {
	return assignedAmountToETColor;
    }

    public void setAssignedAmountToCCColor(Color assignedAmountToCCColor) {
	this.assignedAmountToCCColor = assignedAmountToCCColor;
    }

    public Color getAssignedAmountToCCColor() {
	return assignedAmountToCCColor;
    }

    public void setIdBudget(int idBudget) {
	this.idBudget = idBudget;
    }

    public int getIdBudget() {
	return idBudget;
    }

    public void setIdBudgetExpenditureAccount(int _idBudgetExpenditureAccount) {
	this.idBudgetExpenditureAccount = _idBudgetExpenditureAccount;
    }

    public int getIdBudgetExpenditureAccount() {
	return idBudgetExpenditureAccount;
    }

    public void setIdParent(int idParent) {
	this.idParent = idParent;
    }

    public int getIdParent() {
	return idParent;
    }

    public void setSpentAmount(double spentAmount) {
	this.spentAmount = spentAmount;
    }

    public double getSpentAmount() {
	return spentAmount;
    }

    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

    public double getAvailableAmount() {
	return availableAmount;
    }

    public void setInitialAmountP(double initialAmountP) {
	this.initialAmountP = initialAmountP;
    }

    public double getInitialAmountP() {
	return initialAmountP;
    }

    public void setAssignedAmountToETp(double assignedAmountToETp) {
	this.assignedAmountToETp = assignedAmountToETp;
    }

    public double getAssignedAmountToETp() {
	return assignedAmountToETp;
    }

    public void setAssignedAmountToCCp(double assignedAmountToCCp) {
	this.assignedAmountToCCp = assignedAmountToCCp;
    }

    public double getAssignedAmountToCCp() {
	return assignedAmountToCCp;
    }

    public void setValueBlock(int valueBlock) {
	this.valueBlock = valueBlock;
    }

    public int getValueBlock() {
	return valueBlock;
    }

    public void setSpentAmountP(double spentAmountP) {
	this.spentAmountP = spentAmountP;
    }

    public double getSpentAmountP() {
	return spentAmountP;
    }

    public void setModifiedAmount(double _modifiedAmount) {
	this.modifiedAmount = _modifiedAmount;
    }

    public double getModifiedAmount() {
	return modifiedAmount;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public static class CCItem extends ExpenditureAccount {

	private CostsCentre costCentre;
	private int idCostCentreDetail;
	private double initialAmount;
	private double initialAmountP;
	private double spentAmount;
	private double spentAmountP;
	private double availableCCAmount;
	private double modifiedCCAmount;

	public CCItem(int _idExpenditureAccount, String _name) {
	    super(_idExpenditureAccount, _name);
	}

	public CCItem(int _idExpenditureAccount) {
	    super(_idExpenditureAccount);
	}

	public CCItem() {
	}

	public CostsCentre getCostCentre() {
	    return costCentre;
	}

	public CCItem(CostsCentre _costCentre) {
	    costCentre = _costCentre;
	}

	public double getInitialAmount() {
	    return initialAmount;
	}

	public void setInitalAmount(double initialAmount) {
	    this.initialAmount = initialAmount;
	}

	public double getInitialAmountP() {
	    return initialAmountP;
	}

	public void setInitalAmountP(double initialAmountP) {
	    this.initialAmountP = initialAmountP;
	}

	public double getSpentAmount() {
	    return spentAmount;
	}

	public void setSpentAmount(double spentAmount) {
	    this.spentAmount = spentAmount;
	}

	public double getSpentAmountP() {
	    return spentAmountP;
	}

	public void setSpentAmountP(double spentAmountP) {
	    this.spentAmountP = spentAmountP;
	}

	public double getAvailableCCAmount() {
	    return availableCCAmount;
	}

	public void setAvailableCCAmount(double availableCCAmount) {
	    this.availableCCAmount = availableCCAmount;
	}

	public double getModifiedCCAmount() {
	    return modifiedCCAmount;
	}

	public void setModifiedCCAmount(double _modifiedCCAmount) {
	    this.modifiedCCAmount = _modifiedCCAmount;
	}

	public int getIdCostCentreDetail() {
	    return idCostCentreDetail;
	}

	public void setIdCostCentreDetail(int idCostCentreDetail) {
	    this.idCostCentreDetail = idCostCentreDetail;
	}

	public void updateSpentAmount(double _spentAmount) {
	    /* LibSQL.getInt("cashflow.setCostsCentreSpentAmountDetail",""+ idCostCentreDetail +","+ _spentAmount);
	    int idParentTmp = super.idExpenditureAccount;
	    while (idParentTmp != 0) {             
		idParentTmp = LibSQL.getInt("cashflow.addCostsCentreAmountDetailForParent",costCentre.getIdCostCentre() +","+ idParentTmp +","+ _spentAmount);
	    }	
	    LibSQL.getInt("cashflow.setCostsCentreSpentAmount",""+ costCentre.getIdCostCentre());
	    */
	}

    }

}
