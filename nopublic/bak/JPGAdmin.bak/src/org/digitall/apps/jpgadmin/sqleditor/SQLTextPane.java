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
