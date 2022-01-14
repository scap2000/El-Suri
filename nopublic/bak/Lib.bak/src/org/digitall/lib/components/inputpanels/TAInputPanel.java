package org.digitall.lib.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

public class TAInputPanel extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private BasicLabel lblValidator = new BasicLabel();
    private BasicTextArea textArea = new BasicTextArea();
    private String componentName;
    private int dataType;
    private boolean required = true;
    private BorderLayout borderLayout1 = new BorderLayout();

    public TAInputPanel() {
	this(DataTypes.STRING, "na", false);
    }

    /**
     *
     * @param _dataType
     * @param _componentName
     * @param _required
     */
    public TAInputPanel(int _dataType, String _componentName, boolean _required) {
	try {
	    componentName = _componentName;
	    dataType = _dataType;
	    required = _required;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() throws Exception {
	this.setSize(new Dimension(171, 80));
	this.setLayout(borderLayout1);
	lblValidator.setText("(*)");
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Default", 1, 11));
	lblValidator.setBorder(null);
	String sLabelText = Environment.lang.getProperty(componentName) + ":";
	lbl.setText(sLabelText);
	if (required) {
	    this.add(lblValidator, BorderLayout.EAST);
	    lblValidator.setVisible(true);
	}
	textArea.setName(componentName);
	textArea.setBackground(Color.white);
	textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));

	this.add(lbl, BorderLayout.NORTH);
	this.add(textArea, BorderLayout.CENTER);
    }

    public void setText(String _text) {
	textArea.setText(_text);
    }

    public void setBackgroundColor(Color _color) {
	textArea.setBackground(_color);	
    }
    
    public void setDisabledTextColor(Color _color){
	textArea.setDisabledTextColor(_color);
    }
    
    public String getText() {
	return textArea.getText();
    }

    public void setEditable(boolean _editable) {
	textArea.setEditable(_editable);
    }

    public void setEnabled(boolean _valor) {
	textArea.setEnabled(_valor);
	if (!_valor) {
	    textArea.setOpaque(false);
	    textArea.setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
	    textArea.setDisabledTextColor(new Color(255,255,255));
	} else {
	    textArea.setOpaque(true);
	}
    }

    public BasicTextArea getTextArea() {
	return textArea;
    }

    public int getDataType() {
	return dataType;
    }
}
