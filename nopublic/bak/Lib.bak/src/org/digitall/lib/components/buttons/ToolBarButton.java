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
