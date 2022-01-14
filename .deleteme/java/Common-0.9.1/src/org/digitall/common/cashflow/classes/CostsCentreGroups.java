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
 * CostsCentreGroups.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;

public class CostsCentreGroups extends Vector {

    public CostsCentreGroups() {
    }

    public void sort() {
	Object ccTmp;
	for (int i = 0; i < size() - 1; i++) {
	    for (int j = i + 1; j < size(); j++) {
		int idCCi = ((CostsCentre)elementAt(i)).getIdCostCentre();
		int idCCj = ((CostsCentre)elementAt(j)).getIdCostCentre();
		if (idCCi > idCCj) {
		    ccTmp = elementAt(i);
		    setElementAt(elementAt(j), i);
		    setElementAt(ccTmp, j);
		}
	    }
	}
    }

    public CostsCentre getElement(int _idCostCentre) {
	int initIndex = 0;
	int endIndex = size() - 1;
	int midIndex = 0;
	while (initIndex <= endIndex) {
	    midIndex = (initIndex + endIndex) / 2;
	    int idCC = ((CostsCentre)elementAt(midIndex)).getIdCostCentre();
	    if (_idCostCentre == idCC) {
		break;
	    } else if (_idCostCentre > idCC) {
		initIndex = midIndex + 1;
	    } else if (_idCostCentre < idCC) {
		endIndex = midIndex - 1;
	    }
	}
	if (initIndex > endIndex) {
	    return null;
	} else {
	    return (CostsCentre)elementAt(midIndex);
	}
    }

}
