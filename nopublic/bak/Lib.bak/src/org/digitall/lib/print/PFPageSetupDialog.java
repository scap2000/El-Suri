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












