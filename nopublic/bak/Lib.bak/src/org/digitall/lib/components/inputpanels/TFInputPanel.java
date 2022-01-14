package org.digitall.lib.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.components.textfields.JTColor;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

public class TFInputPanel extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private BasicLabel lblValidator = new BasicLabel();
    private BasicTextInput textField = new BasicTextInput();
    private String componentName;
    private int dataType;
    private boolean required = true;
    private BorderLayout borderLayout1 = new BorderLayout();

    /**
     * @deprecated
     * No es necesario enviarle el parametro Editable porque
     * ya existen los metodos setEditable minY setEnabled
     */
    public TFInputPanel(int _dataType, String _componentName, boolean _required, boolean _editable) {
	this(_dataType, _componentName, _required);
	textField.setEditable(_editable);
    }

    /**
     *
     * @param _dataType
     * @param _componentName
     * @param _required
     */
    public TFInputPanel(int _dataType, String _componentName, boolean _required) {
	try {
	    componentName = _componentName;
	    dataType = _dataType;
	    required = _required;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TFInputPanel() {
	this(DataTypes.STRING, "N/A", false);
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(171, 40));
	this.setLayout(borderLayout1);
	lblValidator.setText("(*)");
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Default", 1, 11));
	lblValidator.setBorder(null);
	switch (dataType) {
	    case DataTypes.INTEGER:
		textField = new JIntEntry();
		break;
	    case DataTypes.DATE:
		textField = new JTFecha();
		break;
	    case DataTypes.STRING:
		textField = new JEntry();
		break;
	    case DataTypes.DOUBLE:
		textField = new JDecEntry();
		break;
	    case DataTypes.COLOR:
	        textField = new JTColor(this);
	        break;
	    default:
		textField = new JEntry();
		break;
	}
	String sLabelText = Environment.lang.getProperty(componentName) + ":";
	lbl.setText(sLabelText);
	if (required) {
	    this.add(lblValidator, BorderLayout.EAST);
	    lblValidator.setVisible(true);
	}
	textField.setName(componentName);
	textField.setBackground(Color.white);
	textField.setFont(textField.getFont().deriveFont(Font.BOLD));
	this.add(lbl, BorderLayout.NORTH);
	this.add(textField, BorderLayout.CENTER);
    }

    public void setText(String _text) {
	textField.setText(_text);
    }

    public void setBackgroundColor(Color _color) {
	textField.setBackground(_color);	
    }
    
    public void setDisabledTextColor(Color _color){
	textField.setDisabledTextColor(_color);
    }
    
    public String getText() {
	if (dataType == DataTypes.DOUBLE)
	    textField.setText(textField.getText().replaceAll(",", ""));
	return textField.getText().replaceAll("\'", "\'''");
    }

    public void setEditable(boolean _valor) {
	textField.setEditable(_valor);
    }

    public void setEnabled(boolean _valor) {
	textField.setEnabled(_valor);
	if (!_valor) {
	    textField.setOpaque(false);
	    textField.setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
	    textField.setDisabledTextColor(new Color(255,255,255));
	} else {
	    textField.setOpaque(true);
	}
    }

    public BasicTextField getTextField() {
	return new BasicTextField(textField.getText());
    }

    public void setEditableTxt(boolean _editable) {
	textField.setEditable(_editable);
    }

}
