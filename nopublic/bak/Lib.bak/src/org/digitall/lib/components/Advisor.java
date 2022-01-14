package org.digitall.lib.components;

import java.awt.Rectangle;

import java.sql.SQLException;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.environment.Console;
import org.digitall.lib.environment.Environment;

import org.postgresql.util.PSQLException;

public abstract class Advisor {

    public static void messageBox(String _message, String _title) {
	UIManager.put("OptionPane.yesButtonText", "Sí");
	UIManager.put("OptionPane.noButtonText", "No");
	UIManager.put("OptionPane.okButtonText", "Aceptar");
	UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	if (_message == null) {
	    _message = "Mensaje Vacío";
	}
	_message = "<html><p align=center>" + _message.replaceAll("\\n", "<br>") + "</p></html>";
	if (Environment.mainFrame != null) {
	    if (Environment.getActiveDesktop() != null && Environment.cfg.getProperty("RequestFocus").equalsIgnoreCase("True")) {
	        new JOptionPane().showInternalMessageDialog(Environment.getActiveDesktop(), _message, _title, JOptionPane.OK_OPTION);
	    } else {
		new JOptionPane().showMessageDialog(Environment.mainFrame, _message, _title, JOptionPane.OK_OPTION);
	    }
	} else {
	    new JOptionPane().showMessageDialog(new JFrame(), _message, _title, JOptionPane.OK_OPTION);
	}

    }

    public static void messagePopupWindowIFrame(String _mensaje, BasicInternalFrame _ventana) {
	Timer timerPopupHide = new Timer();
	_mensaje = "<html><p align=center>" + _mensaje.replaceAll("\\n", "<br>") + "</p></html>";
	final PopupWindow popupWindow = new PopupWindow(_mensaje, _ventana);
	popupWindow.activarPopupWindow();
	popupWindow.showMensaje(_mensaje);
	timerPopupHide.schedule(new TimerTask() {

			     public void run() {
				 popupWindow.hideWindow();
			     }

			 }
			 , 10000);
    }

    public static void messagePopupWindow(String _mensaje, String _archivo) {
	Timer timerPopupHide = new Timer();
	final PopupWindow popupWindow = new PopupWindow(_mensaje, _archivo);
	popupWindow.activarPopupWindow();
	popupWindow.showMensaje(_mensaje);
	timerPopupHide.schedule(new TimerTask() {

			     public void run() {
				 popupWindow.hideWindow();
			     }

			 }
			 , 10000);
    }

    public static boolean question(String _title, String _question) {
	UIManager.put("OptionPane.yesButtonText", "Sí");
	UIManager.put("OptionPane.noButtonText", "No");
	UIManager.put("OptionPane.okButtonText", "Aceptar");
	UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	_question = "<html><p align=center>" + _question.replaceAll("\\n", "<br>") + "</p></html>";
	if (Environment.mainFrame != null) {
	    if (Environment.getActiveDesktop() != null && Environment.cfg.getProperty("RequestFocus").equalsIgnoreCase("True")) {
	        return ((new JOptionPane().showInternalConfirmDialog(Environment.getActiveDesktop(), _question, _title, JOptionPane.YES_NO_OPTION))== JOptionPane.YES_OPTION);
	    } else {
		return ((new JOptionPane().showConfirmDialog(Environment.mainFrame, _question, _title, JOptionPane.YES_NO_OPTION))== JOptionPane.YES_OPTION);
	    }
	} else {
	    return ((new JOptionPane().showConfirmDialog(new JFrame(), _question, _title, JOptionPane.YES_NO_OPTION))== JOptionPane.YES_OPTION);
	}
    }

    public static void printException(Exception _exception) {
	if (_exception instanceof PSQLException) {
	    if (_exception.getMessage().contains("denegado") || _exception.getMessage().contains("denied")) {
		//messageBox(_exception.getMessage(), "Error: " + ((PSQLException)_exception).getErrorCode());
		//messageBox(_exception.getLocalizedMessage(), "Acceso denegado");
	        messageBox("Ud. no tiene el nivel de acceso para utilizar la opción seleccionada", "Acceso denegado");
	    } else {
	        if (question("Error", "Ha ocurrido el siguiente error:\n" + _exception.getMessage() + "\n¿Desea ver más detalles?")) {
		    Console.hookStandards();
		    _exception.printStackTrace();
		    Console.unhookStandards();
		}
	    }
	} else if (_exception instanceof SQLException) {
	    if (_exception.getMessage().contains("denegado") || _exception.getMessage().contains("denied")) {
		//messageBox(_exception.getMessage(), "Error: " + ((SQLException)_exception).getErrorCode());
		//messageBox(_exception.getLocalizedMessage(), "Acceso denegado");
		messageBox("Ud. no tiene el nivel de acceso para utilizar la opción seleccionada", "Acceso denegado");
	    }
	} else {
	    messageBox(_exception.getMessage(), "Error");
	}
	System.out.println(_exception.getMessage());
    }
    
    public static void closeApplication() {
	if (question("Salir del sistema", "¿Desea salir de " + Environment.SYSTEM_VERSION + "?")) {
	    Environment.cfg.setProperty("SelectedTab", String.valueOf(Environment.mainTabbedPane.getSelectedIndex()));
	    if (Environment.saveAll()) {
		System.exit(0);
	    } else {
		messageBox("No se puede salir del programa hasta que no se hayan completado o cancelado las operaciones pendientes", "Aviso");
	    }
	}
    }
    
    public static Object getValue(int _datatype, String _title) {
	UIManager.put("OptionPane.yesButtonText", "Sí");
	UIManager.put("OptionPane.noButtonText", "No");
	UIManager.put("OptionPane.okButtonText", "Aceptar");
	UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	Object _returns = null;
	TFInput _tfInput = new TFInput(_datatype, _title, false);
	boolean _accepted = false;
	if (Environment.mainFrame != null) {
	    if (Environment.getActiveDesktop() != null && Environment.cfg.getProperty("RequestFocus").equalsIgnoreCase("True")) {
	        _accepted = new JOptionPane().showInternalConfirmDialog(Environment.mainFrame, _tfInput, _title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	    } else {
		_accepted = new JOptionPane().showConfirmDialog(Environment.mainFrame, _tfInput, _title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	    }
	} else {
	    _accepted = new JOptionPane().showConfirmDialog(new JFrame(), _tfInput, _title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}
	if (_accepted) {
	    _returns = _tfInput.getValue();
	}
	return _returns;
    }

}
