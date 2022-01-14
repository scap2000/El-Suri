package org.digitall.projects.apps.dbadmin_091;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class TextComponentDemo extends JFrame {
    JTextPane textPane;
    LimitedStyledDocument lpd;
    JTextArea changeLog;
    String newline;
    static final int MAX_CHARACTERS = 300;
    Hashtable actions;

    //undo helpers
    protected UndoAction undoAction;
    protected RedoAction redoAction;
    protected UndoManager undo = new UndoManager();

    public TextComponentDemo () {

        //Some initial setup
        super("TextComponentDemo");
        newline = System.getProperty("line.separator");

        //Create the document for the text area
        lpd = new LimitedStyledDocument(MAX_CHARACTERS);
        lpd.addDocumentListener(new MyDocumentListener());

        //Create the text pane and configure it
        textPane = new JTextPane();
        textPane.setDocument(lpd);
        textPane.setCaretPosition(0);
        textPane.setMargin(new Insets(5,5,5,5));
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        //Create the text area for the status log and configure it
        changeLog = new JTextArea(5, 30);
        changeLog.setEditable(false);
        JScrollPane scrollPaneForLog = new JScrollPane(changeLog);

        //Create a split pane for the change log and the text area
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                              scrollPane, scrollPaneForLog);
        splitPane.setOneTouchExpandable(true);

        //Create the status area
        JPanel statusPane = new JPanel(new GridLayout(1, 1));
        CaretListenerLabel caretListenerLabel = new CaretListenerLabel("Caret Status");
        statusPane.add(caretListenerLabel);

        //Add the components to the frame 
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(splitPane, BorderLayout.CENTER);
        contentPane.add(statusPane, BorderLayout.SOUTH);
        setContentPane(contentPane);

        //Set up the menu bar
        createActionTable(textPane);
        JMenu editMenu = createEditMenu();
        JMenu styleMenu = createStyleMenu();
        JMenuBar mb = new JMenuBar();
        mb.add(editMenu);
        mb.add(styleMenu);
        setJMenuBar(mb);

        //Add some key bindings to the keymap
        addKeymapBindings();

        //Put the initial text into the text pane.
	initDocument();

        //Start watching for undoable edits and caret changes
        lpd.addUndoableEditListener(new MyUndoableEditListener());
        textPane.addCaretListener(caretListenerLabel);
    }

    //This listens for and reports caret movements.
    protected class CaretListenerLabel extends JLabel implements CaretListener {
        public CaretListenerLabel (String label) {
            super(label);
        }
        public void caretUpdate(CaretEvent e) {
            //Get the location in the text
            int dot = e.getDot();
            int mark = e.getMark();
            if (dot == mark) {  // no selection 
                try {
                    Rectangle caretCoords = textPane.modelToView(dot);
                    //Convert it to view coordinates
                    setText("caret: text position: " + dot +
                            ", view location = [" + 
                            caretCoords.x + ", " + caretCoords.y + "]" +
                            newline);
                } catch (BadLocationException ble) {
                    setText("caret: text position: " + dot + newline);
                }
            } else if (dot < mark) {
                setText("selection from: " + dot + " to " + mark + newline);
            } else {
                setText("selection from: " + mark + " to " + dot + newline);
            }
        }
    }

    //This one listens for edits that can be undone.
    protected class MyUndoableEditListener implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            //Remember the edit and update the menus
            undo.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }

    //And this one listens for any changes to the document.
    protected class MyDocumentListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            update(e);
        }
        public void removeUpdate(DocumentEvent e) {
            update(e);
        }
        public void changedUpdate(DocumentEvent e) {
            //Display the type of edit that occurred
            changeLog.append(e.getType().toString() + 
                             ": from " + e.getOffset() + 
                             " to " + (e.getOffset() + e.getLength() - 1) +
                             newline);
            changeLog.setCaretPosition(changeLog.getDocument().getLength() - 1);
        }
        private void update(DocumentEvent e) {
            //Display the type of edit that occurred and
            //the resulting text length
            changeLog.append(e.getType().toString() + 
                             ": text length = " + 
			     e.getDocument().getLength() + newline);
            changeLog.setCaretPosition(changeLog.getDocument().getLength() - 1);
        }
    }  

    //Add a couple of emacs key bindings to the key map for navigation
    protected void addKeymapBindings() {
        //Add a new key map to the keymap hierarchy
        Keymap keymap = textPane.addKeymap("MyEmacsBindings",
					   textPane.getKeymap());

        //Ctrl-b to go backward one character
        Action action = getActionByName(DefaultEditorKit.backwardAction);
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

        //Ctrl-f to go forward one character
        action = getActionByName(DefaultEditorKit.forwardAction);
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

        //Ctrl-p to go up one line
        action = getActionByName(DefaultEditorKit.upAction);
        key = KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

        //Ctrl-n to go down one line
        action = getActionByName(DefaultEditorKit.downAction);
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

	textPane.setKeymap(keymap);
    }

    //Create the edit menu
    protected JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");

        //Undo and redo are actions of our own creation
        undoAction = new UndoAction();
        menu.add(undoAction);

        redoAction = new RedoAction();
        menu.add(redoAction);

        menu.addSeparator();

        //These actions come from the default editor kit.
        //Get the ones we want and stick them in the menu.
        menu.add(getActionByName(DefaultEditorKit.cutAction));
        menu.add(getActionByName(DefaultEditorKit.copyAction));
        menu.add(getActionByName(DefaultEditorKit.pasteAction));

        menu.addSeparator();

        menu.add(getActionByName(DefaultEditorKit.selectAllAction));
        return menu;
    }

    //Create the style menu
    protected JMenu createStyleMenu() {
        JMenu menu = new JMenu("Style");

        Action action = new StyledEditorKit.BoldAction();
	action.putValue(Action.NAME, "Bold");
        menu.add(action);

        action = new StyledEditorKit.ItalicAction();
	action.putValue(Action.NAME, "Italic");
        menu.add(action);

        action = new StyledEditorKit.UnderlineAction();
	action.putValue(Action.NAME, "Underline");
        menu.add(action);

        menu.addSeparator();

        menu.add(new StyledEditorKit.FontSizeAction("12", 12));
        menu.add(new StyledEditorKit.FontSizeAction("14", 14));
        menu.add(new StyledEditorKit.FontSizeAction("18", 18));

        menu.addSeparator();

        menu.add(new StyledEditorKit.FontFamilyAction("Serif", "Serif"));
        menu.add(new StyledEditorKit.FontFamilyAction("SansSerif", "SansSerif"));

        menu.addSeparator();

        menu.add(new StyledEditorKit.ForegroundAction("Red", Color.red));
        menu.add(new StyledEditorKit.ForegroundAction("Green", Color.green));
        menu.add(new StyledEditorKit.ForegroundAction("Blue", Color.blue));
        menu.add(new StyledEditorKit.ForegroundAction("Black", Color.black));

        return menu;
    }

    protected void initDocument() {
        String initString[] =
		{ "Use the mouse to place the caret.",
		  "Use the edit menu to cut, copy, paste, and select text.",
		  "Also to undo and redo changes.",
		  "Use the style menu to change the style of the text.",
		  "Use these emacs key bindings to move the caret:",
		  "ctrl-f, ctrl-b, ctrl-n, ctrl-p." };

	String[] styleNames = initStyles();

        try {
	    for (int i = 0; i < initString.length; i ++) {
                lpd.insertString(lpd.getLength(), initString[i] + newline,
			textPane.getStyle(styleNames[i]));
	    }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text.");
        }
    }

    protected String[] initStyles() {
	//Initialize some styles
	String styleNames[] = { "default", "bold", "italic",
				"large", "small", "red" };

	Style def = textPane.addStyle(styleNames[0],
					  textPane.getLogicalStyle());
	StyleConstants.setFontSize(def, 16);
	StyleConstants.setFontFamily(def, "SansSerif");

	Style s = textPane.addStyle(styleNames[1], def);
	StyleConstants.setBold(s, true);

	s = textPane.addStyle(styleNames[2], def);
	StyleConstants.setItalic(s, true);

	s = textPane.addStyle(styleNames[3], def);
	StyleConstants.setFontSize(s, 20);

	s = textPane.addStyle(styleNames[4], def);
	StyleConstants.setFontSize(s, 12);

	s = textPane.addStyle(styleNames[5], def);
	StyleConstants.setForeground(s, Color.red);

	return styleNames;
    }


    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private void createActionTable(JTextComponent textComponent) {
        actions = new Hashtable();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
    }

    private Action getActionByName(String name) {
        return (Action)(actions.get(name));
    }

    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }
          
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
               // System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            update();
            redoAction.update();
        }
          
        protected void update() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }      
    }    

    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                //System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            update();
            undoAction.update();
        }

        protected void update() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }    

    //The standard main method.
    public static void main(String[] args) {

        final TextComponentDemo frame = new TextComponentDemo();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            public void windowActivated(WindowEvent e) {
                frame.textPane.requestFocus();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}