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
 * SQLTextPane.java
 *
 * */
package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class SQLTextPane extends JEditorPane {

    protected UndoAction undoAction = new UndoAction();
    protected RedoAction redoAction = new RedoAction();
    final UndoManager undo = new UndoManager();
    

    public SQLTextPane() {
	this("");
    }

    public SQLTextPane(String _text) {
	setFont(new Font("Monospaced", Font.PLAIN, 13));
	setEditorKit(new SQLEditorKit());
	getActionMap().put("Undo", undoAction);
	getActionMap().put("Redo", redoAction);
	InputMap inputMap = getInputMap();
	KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
	inputMap.put(key, "Undo");
	KeyStroke keyz = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK | Event.SHIFT_MASK);
	inputMap.put(keyz, "Redo");

	Action copy = new DefaultEditorKit.CopyAction();
	Action cut = new DefaultEditorKit.CutAction();
	Action paste = new DefaultEditorKit.PasteAction();
	getActionMap().put("Copy", copy);
	getActionMap().put("Cut", cut);
	getActionMap().put("Paste", paste);

	KeyStroke keycp = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.CTRL_MASK);
	inputMap.put(keycp, "Copy");
	KeyStroke keycut = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Event.SHIFT_MASK);
	inputMap.put(keycut, "Cut");
	KeyStroke keypt = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK);
	inputMap.put(keypt, "Paste");

	setText(_text);
	getDocument().addUndoableEditListener(new SQLEditListener());
    }

    public boolean getScrollableTracksViewportWidth() {
	Component parent = getParent();
	ComponentUI ui = getUI();
	return parent != null ? (ui.getPreferredSize(this).width <= parent.getSize().width) : true;
    }

    private class SQLEditorKit extends StyledEditorKit {

	public Document createDefaultDocument() {
	    return new SQLDocument();
	}

    }

    protected class SQLEditListener implements UndoableEditListener {

	public void undoableEditHappened(UndoableEditEvent ev) {
	    UndoableEdit e = ev.getEdit();
	    //if (ignoreStyleChanges)
	    {
		// Ignore style changes
		if (((AbstractDocument.DefaultDocumentEvent)ev.getEdit()).getType() == DocumentEvent.EventType.CHANGE)
		    return;
	    }
	    undo.addEdit(ev.getEdit());
	    undoAction.updateUndoState();
	    redoAction.updateRedoState();
	}

    }

    private class UndoAction extends AbstractAction {

	public UndoAction() {
	    super("Undo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    //System.out.println("undoing");
	    try {
		undo.undo();
	    } catch (CannotUndoException ex) {
		System.out.println("Unable to undo: " + ex);
		ex.printStackTrace();
	    }
	    updateUndoState();
	    redoAction.updateRedoState();
	}

	protected void updateUndoState() {
	    //System.out.println("updateUndo");
	    if (undo.canUndo()) {
		setEnabled(true);
		putValue(Action.NAME, undo.getUndoPresentationName());
	    } else {
		setEnabled(false);
		putValue(Action.NAME, "Undo");
	    }
	}

    }

    private class RedoAction extends AbstractAction {

	public RedoAction() {
	    super("Redo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.redo();
	    } catch (CannotRedoException ex) {
		System.out.println("Unable to redo: " + ex);
		ex.printStackTrace();
	    }
	    updateRedoState();
	    undoAction.updateUndoState();
	}

	protected void updateRedoState() {
	    if (undo.canRedo()) {
		setEnabled(true);
		putValue(Action.NAME, undo.getRedoPresentationName());
	    } else {
		setEnabled(false);
		putValue(Action.NAME, "Redo");
	    }
	}

    }

}
