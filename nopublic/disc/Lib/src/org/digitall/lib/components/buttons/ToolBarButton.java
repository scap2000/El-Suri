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
 * ToolBarButton.java
 *
 * */
package org.digitall.lib.components.buttons;

import java.awt.Cursor;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

//

public class ToolBarButton extends BasicButton {

    public static final int DRILLING_MODULE = 1;
    public static final int MAPPER_MODULE = 2;
    public static final int ADMIN_MODULE = 3;
    public static final int LOGISTIC_MODULE = 4;
    public static final int CALCULATOR_APP = 5;
    public static final int BLUETOOTH_APP = 6;
    public static final int CASHFLOWMGMT_MODULE = 7;
    public static final int FILES_MODULE = 8;
    public static final int CHWY_APP = 9;
    public static final int LOGOUT_ACTION = 10;
    public static final int EXIT_ACTION = 11;
    public static final int GEOCALC_APP = 12;
    public static final int WEBMAIL_ACTION = 13;
    public static final int LANGUAGE_ACTION = 14;
    public static final int TASK_MODULE = 15;
    public static final int RESOURCESCONTROL_MODULE = 16;
    public static final int CASHFLOWCLIENT_MODULE = 17;

    public ToolBarButton(int _type) {
	setRolloverEnabled(true);
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	switch (_type) {
	    case DRILLING_MODULE:
		setToolTipText("Drilling Module");
		setIcon(IconTypes.imgDrillingOff);
		setRolloverIcon(IconTypes.imgDrillingOn);
		break;
	    case MAPPER_MODULE:
		setToolTipText("Mapper Module");
		setIcon(IconTypes.imgMapperOff);
		setRolloverIcon(IconTypes.imgMapperOn);
		break;
	    case ADMIN_MODULE:
		setToolTipText("Administration Module");
		setIcon(IconTypes.imgAdminOff);
		setRolloverIcon(IconTypes.imgAdminOn);
		break;
	    case LOGISTIC_MODULE:
		setToolTipText("Logistic Module");
		setIcon(IconTypes.imgLogisticOff);
		setRolloverIcon(IconTypes.imgLogisticOn);
		break;
	    case CALCULATOR_APP:
		setToolTipText("Normal Calculator");
		setIcon(IconTypes.imgCalculatorOff);
		setRolloverIcon(IconTypes.imgCalculatorOn);
		break;
	    case GEOCALC_APP:
		setToolTipText("Geographic Calculator");
		setIcon(IconTypes.imgGeoCalculatorOff);
		setRolloverIcon(IconTypes.imgGeoCalculatorOn);
		break;
	    case BLUETOOTH_APP:
		break;
	    case CASHFLOWMGMT_MODULE:
		setToolTipText("Administrador de CashFlow");		
		break;
	    case CASHFLOWCLIENT_MODULE:
	        setToolTipText("CashFlow");            
	        break;
	    case FILES_MODULE:
		setToolTipText("Files Module");
		setIcon(IconTypes.imgFilesOff);
		setRolloverIcon(IconTypes.imgFilesOn);
		break;
	    case WEBMAIL_ACTION:
		setToolTipText("Check webmail on external browser");
		setIcon(IconTypes.imgWebMailOff);
		setRolloverIcon(IconTypes.imgWebMailOn);
		break;
	    case CHWY_APP:
		setToolTipText("Chat With You App");
		setIcon(IconTypes.imgChWYOff);
		setIcon(IconTypes.imgChWYOn);
		break;
	    case LOGOUT_ACTION:
		setToolTipText("Close Session");
		setIcon(IconTypes.imgSessionOff);
		setRolloverIcon(IconTypes.imgSessionOn);
		break;
	    case EXIT_ACTION:
		setToolTipText("Exit program");
		setIcon(IconTypes.imgExitOff);
		setRolloverIcon(IconTypes.imgExitOn);
		break;
	    case LANGUAGE_ACTION:
		setToolTipText("Change language");
		setIcon(IconTypes.languageIcon_32x32);
		//setRolloverIcon(IconTypes.imgExitOn);
		break;
	    case TASK_MODULE:
	        setToolTipText("Task Project");	        
	        break;
	    case RESOURCESCONTROL_MODULE:
	        setToolTipText("Administrador de Recursos");         
	        break;
	    default:
		break;
	}
    }

}
