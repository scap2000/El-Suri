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
 * PFPageSetupDialog.java
 *
 * */
package org.digitall.lib.print;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class: PFPageSetupDialog <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see JDialog
 * @see ActionListener
 */

/**
 * Class: PFPageSetupDialog <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see JDialog
 * @see ActionListener
 */
public class PFPageSetupDialog extends JDialog implements ActionListener {

    //--- Private instances declarations
    private PFPageFormat pageFormat = new PFPageFormat();
    //--- Panels
    private FormPanel fieldPanel = new FormPanel(5, 5, 5, 5);
    private JPanel buttonPanel = new JPanel();
    //--- Layouts
    private BorderLayout dialogLayout = new BorderLayout();
    private FlowLayout buttonLayout = new FlowLayout();
    //--- Labels
    private JLabel labelPaperSize = new JLabel("Paper size:");
    private JLabel labelPaperSource = new JLabel("Paper source:");
    private JLabel labelLeftMargin = new JLabel("Left margin:");
    private JLabel labelRightMargin = new JLabel("Right margin:");
    private JLabel labelTopMargin = new JLabel("Top margin:");
    private JLabel labelBottomMargin = new JLabel("Bottom margin:");
    private JLabel labelPageOrientation = new JLabel("Page orientation");
    //--- Field
    private JComboBox comboPaperSize;
    private JComboBox comboPaperSource = new JComboBox();
    private JTextField textLeftMargin = new JTextField(10);
    private JTextField textRightMargin = new JTextField(10);
    private JTextField textTopMargin = new JTextField(10);
    private JTextField textBottomMargin = new JTextField(10);
    private JComboBox comboPageOrientation;
    //--- Buttons
    private JButton buttonAccept = new JButton("Accept");
    private JButton buttonCancel = new JButton("Cancel");

    /**
    * Constructor: PFPageSetup <p>
    *
    */
    public PFPageSetupDialog(PFPageFormat parPageFormat) {
	super(new JFrame(), "Page dialog", true);
	if (parPageFormat != null)
	    pageFormat = parPageFormat;
	init();
    }

    /**
    * Method: init <p>
    *
    * Initialise this dialog box, panels and fields
    *
    */
    private void init() {
	//--- Init this dialog
	this.getContentPane().setLayout(dialogLayout);
	this.getContentPane().add(fieldPanel, BorderLayout.CENTER);
	this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	this.setSize(new Dimension(500, 300));
	//--- Init the field
	setFields();
	fieldPanel.add(labelPaperSize, comboPaperSize, 0, 0);
	fieldPanel.add(labelPaperSource, comboPaperSource, 0, 1);
	fieldPanel.add(labelLeftMargin, textLeftMargin, 2, 0);
	fieldPanel.add(labelRightMargin, textRightMargin, 2, 1);
	fieldPanel.add(labelTopMargin, textTopMargin, 3, 0);
	fieldPanel.add(labelBottomMargin, textBottomMargin, 3, 1);
	//--- Disable the paper source field
	comboPaperSource.setEnabled(false);
	//--- Init buttons
	buttonPanel.add(buttonAccept);
	buttonPanel.add(buttonCancel);
	buttonAccept.setActionCommand("Accept");
	buttonCancel.setActionCommand("Cancel");
	buttonAccept.addActionListener(this);
	buttonCancel.addActionListener(this);
    }

    /**
    * Method: setFields <p>
    *
    * This method will set the field from the pageFormat object
    * to the display fields.<p>
    *
    */
    private void setFields() {
	comboPaperSize = new JComboBox(pageFormat.getPageSizeDefinition());
	comboPaperSize.setSelectedIndex(pageFormat.getPageSizeIndex());
	textLeftMargin.setText(pageFormat.getLeftMargin().toString());
	textRightMargin.setText(pageFormat.getRightMargin().toString());
	textTopMargin.setText(pageFormat.getTopMargin().toString());
	textBottomMargin.setText(pageFormat.getBottomMargin().toString());
	comboPageOrientation = new JComboBox(pageFormat.getPageOrientationDefinition());
	comboPageOrientation.setSelectedIndex(pageFormat.getPageOrientation());
    }

    /**
    * Method: getFields <p>
    *
    * Set the pageFormat properties from the user settings<p>
    *
    */
    public void getFields() {
	pageFormat.setPageSize(comboPaperSize.getSelectedIndex());
	pageFormat.setLeftMargin(new PFInchUnit(new Double(textLeftMargin.getText()).doubleValue()));
	pageFormat.setRightMargin(new PFInchUnit(new Double(textRightMargin.getText()).doubleValue()));
	pageFormat.setTopMargin(new PFInchUnit(new Double(textTopMargin.getText()).doubleValue()));
	pageFormat.setBottomMargin(new PFInchUnit(new Double(textBottomMargin.getText()).doubleValue()));
	pageFormat.setPageOrientation(comboPageOrientation.getSelectedIndex());
    }

    /**
     * Method: showDialog <p>
     * 
     * Show this dialog and return the pageFormat settings
     * made by the user. Note that <code>pageFormat</code> propeties
     * are set in the <code>ActionListener</code> <code>actionPerformaed</code> method<p>
     * 
     * @return a value of shapeTypeName PFPageFormat
     */
    public PFPageFormat showDialog() {
	this.pack();
	this.show();
	return (pageFormat);
    }

    /**
     * Method: actionPerformed <p>
     * 
     * Listen to button click events. If the "ok" button is clicked,
     * the <code>getFields ()</code> method is called to save the
     * user selection in the <code>pageFormat</code> object. Else the
     * dialog is disposed.
     * 
     * @param parEvent a value of shapeTypeName ActionEvent
     */
    public void actionPerformed(ActionEvent parEvent) {
	String actionCommand = parEvent.getActionCommand();
	if (actionCommand.equals("Accept")) {
	    getFields();
	    this.dispose();
	} else {
	    this.dispose();
	}
    }

}// PFPageSetupDialog












