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
 * Advisor.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Dimension;
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
	    _message = "Error no especificado";
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
	    if (_exception.getMessage() != null) {
		messageBox(_exception.getMessage(), "Error");
	    } else {
		messageBox(_exception.getStackTrace()[0].toString(), "Error");
	    }
	    _exception.printStackTrace();
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

    public static void showInternalMessageDialog(String _message) {
	JArea tfAyuda = new JArea();
	tfAyuda.setEditable(false);
	tfAyuda.setContentType(JArea.CONTENT_HTML);
	tfAyuda.setText(_message);
	tfAyuda.getTextArea().setCaretPosition(0);
	tfAyuda.setPreferredSize(new Dimension(645, 400));
	JOptionPane.showInternalMessageDialog(Environment.getActiveDesktop(), tfAyuda);
    }

}
