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
 * FontChooser.java
 *
 * */
package org.digitall.lib.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.ParseException;

import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
// FontChooser.java
// A font chooser that allows users to pick a font by name, size, style, and
// color.  The color selection is provided by a JColorChooser pane.  This
// dialog builds an AttributeSet suitable for use with JTextPane.
//

public class FontChooser extends JDialog implements ActionListener {

    private JColorChooser colorChooser = new JColorChooser(Color.black);
    private BasicCombo fontName;
    private BasicCheckBox fontBold = new BasicCheckBox("Negrita", false);
    private BasicCheckBox fontItalic = new BasicCheckBox("Cursiva", false);
    private JIntEntry fontSize = new JIntEntry();
    private BasicLabel previewLabel = new BasicLabel("Texto de MUESTRA (0987654321?=/&%$) de la fuente seleccionada...");
    private SimpleAttributeSet attributes = new SimpleAttributeSet();
    private Font newFont;
    private Color newColor;
    private BasicLabel lblSize = new BasicLabel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private CloseButton cancelButton = new CloseButton();
    private BasicPanel previewPanel = new BasicPanel(new BorderLayout());
    private BasicPanel fontPanel = new BasicPanel();
    private AcceptButton okButton = new AcceptButton();
    private BasicPanel controlPanel = new BasicPanel();

    public FontChooser(Font _font, Color _color) {
	super(new JDialog(), "Seleccione el tipo de letra", true);
	if (_font == null) {
	    newFont = new Font("Arial", Font.PLAIN, 12);
	} else {
	    newFont = _font;
	}
	newColor = _color;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void jbInit() {
	this.setSize(new Dimension(450, 369));
	// Make sure that any way the user cancels the window does the right thing
	addWindowListener(new WindowAdapter() {

		    public void windowClosing(WindowEvent e) {
			closeAndCancel();
		    }

		});
	// Start the long process of setting up our interface
	colorChooser.setPreferredSize(new Dimension(1, 300));
	colorChooser.setSize(new Dimension(445, 300));
	String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	Vector vcFontnames = new Vector(fonts.length - 5);
	for (int i = 0; i < fonts.length; i++) {
	    //if (!fonts[i].equals("Dialog") && !fonts[i].equals("DialogInput") && !fonts[i].equals("Monospaced") && !fonts[i].equals("SansSerif") && !fonts[i].equals("Serif")) {
	    vcFontnames.add(fonts[i]);
	    //}
	}
	fontName = new BasicCombo(vcFontnames);
	fontName.setSelectedItem(newFont.getName());
	fontName.setBounds(new Rectangle(5, 5, 175, 20));
	fontName.addActionListener(this);
	fontSize.setValue(newFont.getSize());
	fontSize.setHorizontalAlignment(SwingConstants.RIGHT);
	fontSize.setBounds(new Rectangle(245, 5, 30, 20));
	fontSize.addActionListener(this);
	fontSize.addKeyListener(new KeyAdapter() {

		    public void keyReleased(KeyEvent e) {
			fontSize_keyReleased(e);
		    }

		});
	fontBold.setSelected(newFont.isBold());
	fontBold.setBounds(new Rectangle(280, 5, 80, 20));
	fontBold.addActionListener(this);
	fontItalic.setSelected(newFont.isItalic());
	fontItalic.setBounds(new Rectangle(360, 5, 80, 20));
	fontItalic.addActionListener(this);
	fontPanel.add(lblSize);
	fontPanel.add(fontName);
	// Set up the color chooser panel and attach a change listener so that color
	// updates get reflected in our preview label.
	fontPanel.add(fontSize);
	fontPanel.add(fontBold);
	fontPanel.add(fontItalic);
	colorChooser.setColor(newColor);
	colorChooser.setBounds(new Rectangle(0, 40, 445, 300));
	colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {

		    public void stateChanged(ChangeEvent e) {
			updatePreviewColor();
		    }

		});
	getContentPane().setLayout(borderLayout1);
	getContentPane().add(fontPanel, BorderLayout.NORTH);
	getContentPane().add(colorChooser, BorderLayout.CENTER);
	previewLabel.setForeground(colorChooser.getColor());
	lblSize.setBounds(new Rectangle(185, 5, 60, 20));
	lblSize.setText("Tamaño:");
	fontPanel.setLayout(null);
	fontPanel.setPreferredSize(new Dimension(1, 40));
	previewPanel.add(previewLabel, BorderLayout.CENTER);
	// Add in the Ok and Cancel buttons for our dialog box
	okButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent ae) {
			closeAndSave();
		    }

		});
	cancelButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent ae) {
			closeAndCancel();
		    }

		});
	controlPanel.add(okButton);
	controlPanel.add(cancelButton);
	previewPanel.add(controlPanel, BorderLayout.SOUTH);
	// Give the preview label room to grow.
	previewPanel.setMinimumSize(new Dimension(100, 100));
	previewPanel.setPreferredSize(new Dimension(100, 75));
	previewPanel.setSize(new Dimension(445, 75));
	previewLabel.setFont(newFont);
	getContentPane().add(previewPanel, BorderLayout.SOUTH);
    }
    // Ok, something in the font changed, so figure that out and make a
    // new font for the preview label

    public void actionPerformed(ActionEvent ae) {
	// Check the name of the font
	if (!StyleConstants.getFontFamily(attributes).equals(fontName.getSelectedItem())) {
	    StyleConstants.setFontFamily(attributes, (String)fontName.getSelectedItem());
	}
	// Check the font size (no error checking yet)
	if (StyleConstants.getFontSize(attributes) != Integer.parseInt("0" + fontSize.getText())) {
	    StyleConstants.setFontSize(attributes, Integer.parseInt("0" + fontSize.getText()));
	}
	// Check to see if the font should be bold
	if (StyleConstants.isBold(attributes) != fontBold.isSelected()) {
	    StyleConstants.setBold(attributes, fontBold.isSelected());
	}
	// Check to see if the font should be italic
	if (StyleConstants.isItalic(attributes) != fontItalic.isSelected()) {
	    StyleConstants.setItalic(attributes, fontItalic.isSelected());
	}
	// and update our preview label
	updatePreviewFont();
    }
    // Get the appropriate font from our attributes object and update
    // the preview label

    protected void updatePreviewFont() {
	String name = StyleConstants.getFontFamily(attributes);
	boolean bold = StyleConstants.isBold(attributes);
	boolean ital = StyleConstants.isItalic(attributes);
	int size = StyleConstants.getFontSize(attributes);
	//Bold and italic donât work properly in beta 4.
	Font f = new Font(name, (bold ? Font.BOLD : 0) + (ital ? Font.ITALIC : 0), size);
	previewLabel.setFont(f);
    }
    // Get the appropriate color from our chooser and update previewLabel

    protected void updatePreviewColor() {
	previewLabel.setForeground(colorChooser.getColor());
	// Manually force the label to repaint
	previewLabel.repaint();
    }

    public Font getNewFont() {
	return newFont;
    }

    public Color getNewColor() {
	return newColor;
    }

    public AttributeSet getAttributes() {
	return attributes;
    }

    public void closeAndSave() {
	// Save font & color information
	newFont = previewLabel.getFont();
	newColor = previewLabel.getForeground();
	// Close the window
	setVisible(false);
    }

    public void closeAndCancel() {
	// Erase any font information and then close the window
	setVisible(false);
    }

    private void fontSize_keyReleased(KeyEvent e) {
	if (StyleConstants.getFontSize(attributes) != Integer.parseInt("0" + fontSize.getText())) {
	    StyleConstants.setFontSize(attributes, Integer.parseInt("0" + fontSize.getText()));
	}
	updatePreviewFont();
    }

}
