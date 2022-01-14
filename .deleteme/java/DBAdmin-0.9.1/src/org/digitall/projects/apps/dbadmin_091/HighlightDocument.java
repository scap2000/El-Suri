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
 * HighlightDocument.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/* 
 * Syntax Highlighting Test using a JTextPane
 *
 * By: Frank Hale <frankhale@gmail.com>
 * Date: 20 November 2006
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * A full copy of this license is at: http://www.gnu.org/licenses/gpl.txt
 *  
 */

public class HighlightDocument extends DefaultStyledDocument {

    private Element rootElement;
    private HashMap<String, Color> keywords;
    private MutableAttributeSet style;
    private Color commentColor = Color.gray;
    private Pattern singleLineCommentDelimter = Pattern.compile("--");
    private Pattern multiLineCommentDelimiterStart = Pattern.compile("/\\*");
    private Pattern multiLineCommentDelimiterEnd = Pattern.compile("\\*/");
    private Color stringColor = Color.green;
    private char stringDelimiter = '\"';
    private Color quoteColor = Color.green.darker();
    private char quoteDelimiter = '\'';
    protected UndoAction undoAction = new UndoAction();
    protected RedoAction redoAction = new RedoAction();
    final UndoManager undo = new UndoManager();

    public HighlightDocument() {
	putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
	rootElement = getDefaultRootElement();
	keywords = new HashMap<String, Color>();
	keywords.put("abstract", Color.blue);
	keywords.put("interface", Color.blue);
	keywords.put("class", Color.blue);
	keywords.put("extends", Color.blue);
	keywords.put("implements", Color.blue);
	keywords.put("package", new Color(0, 200, 0));
	keywords.put("import", new Color(0, 200, 0));
	keywords.put("private", new Color(0, 200, 0));
	keywords.put("protected", new Color(0, 200, 0));
	keywords.put("public", new Color(0, 200, 0));
	keywords.put("void", Color.orange);
	keywords.put("boolean", Color.orange);
	keywords.put("char", Color.orange);
	keywords.put("byte", Color.orange);
	keywords.put("float", Color.orange);
	keywords.put("double", Color.orange);
	keywords.put("long", Color.orange);
	keywords.put("short", Color.orange);
	keywords.put("int", Color.orange);
	keywords.put("true", Color.red);
	keywords.put("false", Color.red);
	keywords.put("const", Color.red);
	keywords.put("null", Color.red);
	keywords.put("break", Color.blue);
	keywords.put("case", Color.blue);
	keywords.put("catch", Color.blue);
	keywords.put("operator", Color.blue);
	keywords.put("continue", Color.blue);
	keywords.put("default", Color.blue);
	keywords.put("do", Color.blue);
	keywords.put("else", Color.blue);
	keywords.put("final", Color.blue);
	keywords.put("finally", Color.blue);
	keywords.put("for", Color.blue);
	keywords.put("if", Color.blue);
	keywords.put("instanceof", Color.blue);
	keywords.put("native", Color.blue);
	keywords.put("new", Color.blue);
	keywords.put("return", Color.blue);
	keywords.put("static", Color.blue);
	keywords.put("super", Color.blue);
	keywords.put("switch", Color.blue);
	keywords.put("synchronized", Color.blue);
	keywords.put("this", Color.blue);
	keywords.put("throw", Color.blue);
	keywords.put("throws", Color.blue);
	keywords.put("transient", Color.blue);
	keywords.put("try", Color.blue);
	keywords.put("volatile", Color.blue);
	keywords.put("while", Color.blue);
	style = new SimpleAttributeSet();
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	super.insertString(offset, str, attr);
	processChangedLines(offset, str.length());
    }

    public void remove(int offset, int length) throws BadLocationException {
	super.remove(offset, length);
	processChangedLines(offset, length);
    }

    public void processChangedLines(int offset, int length) throws BadLocationException {
	String text = getText(0, getLength());
	highlightString(Color.black, 0, getLength(), true, false, false);
	Set<String> keyw = keywords.keySet();
	for (String keyword : keyw) {
	    Color col = keywords.get(keyword);
	    Pattern p = Pattern.compile("\\b" + keyword + "\\b");
	    Matcher m = p.matcher(text);
	    while (m.find()) {
		highlightString(col, m.start(), keyword.length(), true, true, false);
	    }
	}
	/**MULTI LINE STRINGS*/
	int mlsStart = text.indexOf(stringDelimiter);
	while (mlsStart > -1) {
	    int mlsEnd = text.indexOf(stringDelimiter, mlsStart + 1);
	    mlsEnd = (mlsEnd < 0 ? text.length() - 1 : mlsEnd);
	    highlightString(stringColor, mlsStart, (mlsEnd - (mlsStart - 1)), true, false, false);
	    mlsStart = text.indexOf(stringDelimiter, mlsEnd + 1);
	}
	/**MULTI LINE QUOTATED TEXT*/
	int mlqStart = text.indexOf(quoteDelimiter);
	while (mlqStart > -1) {
	    int mlqEnd = text.indexOf(quoteDelimiter, mlqStart + 1);
	    mlqEnd = (mlqEnd < 0 ? text.length() - 1 : mlqEnd);
	    highlightString(quoteColor, mlqStart, (mlqEnd - (mlqStart - 1)), true, false, false);
	    mlqStart = text.indexOf(quoteDelimiter, mlqEnd + 1);
	}
	/**MULTI LINE COMMENTS*/
	Matcher mlcStart = multiLineCommentDelimiterStart.matcher(text);
	Matcher mlcEnd = multiLineCommentDelimiterEnd.matcher(text);
	while (mlcStart.find()) {
	    if (mlcEnd.find(mlcStart.end()))
		highlightString(commentColor, mlcStart.start(), (mlcEnd.end() - mlcStart.start()), true, false, true);
	    else
		highlightString(commentColor, mlcStart.start(), getLength(), true, false, true);
	}
	/**SINGLE LINE COMMENTS*/
	Matcher slc = singleLineCommentDelimter.matcher(text);
	while (slc.find()) {
	    int line = rootElement.getElementIndex(slc.start());
	    int endOffset = rootElement.getElement(line).getEndOffset() - 1;
	    highlightString(commentColor, slc.start(), (endOffset - slc.start()), true, false, true);
	}
    }

    public void highlightString(Color col, int begin, int length, boolean flag, boolean bold, boolean italic) {
	StyleConstants.setForeground(style, col);
	StyleConstants.setBold(style, bold);
	StyleConstants.setItalic(style, italic);
	setCharacterAttributes(begin, length, style, flag);
    }
    //public String getLine(String content, int line) 
    //{
    //        Element lineElement = rootElement.getElement( line );
    //        return content.substring(lineElement.getStartOffset(), lineElement.getEndOffset() - 1);
    //}

    public HighlightDocument(String[] args) {
	JFrame frame = new JFrame("Syntax Highlighting Test <Use Java Keywords>");
	NonWrappingTextPane textPane = new NonWrappingTextPane();
	textPane.setFont(new Font("Monospaced", Font.PLAIN, 10));
	JScrollPane scroll = new JScrollPane(textPane);
	textPane.setEditorKit(new highlightKit());
	textPane.getActionMap().put("Undo", undoAction);
	textPane.getActionMap().put("Redo", redoAction);
	InputMap inputMap = textPane.getInputMap();
	KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
	inputMap.put(key, "Undo");
	KeyStroke keyz = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK | Event.SHIFT_MASK);
	inputMap.put(keyz, "Redo");

	textPane.getDocument().addUndoableEditListener(new SQLUndoableEditListener());

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(scroll);
	frame.setSize(1024, 768);
	frame.setVisible(true);
    }

    public static void main(String[] args) {
	new HighlightDocument(args);
    }

    class highlightKit extends StyledEditorKit {

	public Document createDefaultDocument() {
	    return new HighlightDocument();
	}

    }
    // Because Swing's JTextPane is retarded by default and doesn't allow you to just flip a switch and turn off the FUCKING line-wrapping
    // This overridden method was coded by somebody much smarter than I, somebody probably with an EGO the size of SUN MICROSYSTEMS.
    // At SUN our motto is "Over-engineer everything so that nobody understands it..."

    class NonWrappingTextPane extends JTextPane {

	// The method below is coutesy of Core Swing Advanced Programming by Kim Topley
	// Override getScrollableTracksViewportWidth
	// to preserve the full width of the text

	public boolean getScrollableTracksViewportWidth() {
	    Component parent = getParent();
	    ComponentUI ui = getUI();
	    return parent != null ? (ui.getPreferredSize(this).width <= parent.getSize().width) : true;
	}

    }

    protected class SQLUndoableEditListener implements UndoableEditListener {

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

    class UndoAction extends AbstractAction {

	public UndoAction() {
	    super("Undo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    //System.out.println("undoing");
	    try {
		undo.undo();
	    } catch (CannotUndoException ex) {
		//System.out.println("Unable to undo: " + ex);
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
