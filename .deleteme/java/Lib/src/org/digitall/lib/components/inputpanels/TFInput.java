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
 * TFInput.java
 *
 * */
package org.digitall.lib.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DecimalFormat;
import java.text.ParseException;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.JTDate;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextInput;
import org.digitall.lib.components.textfields.JTColor;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;

//

public class TFInput extends BasicPanel {

    public static final int CENTER = JTextField.CENTER;
    public static final int LEFT = JTextField.LEFT;
    public static final int RIGHT = JTextField.RIGHT;

    private BasicLabel lbl = new BasicLabel();
    private BasicLabel lblValidator = new BasicLabel();
    private BasicTextInput textField = new BasicTextInput();
    private String componentName;
    private String format;
    private int dataType;
    private boolean required = true;
    private ActionListener actionListener;
    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private KeyListener keyListener;
    private BorderLayout borderLayout = new BorderLayout();
    private Object value = "";

    /**
     *
     * @param _dataType
     * @param _componentName
     * @param _required
     */
     public TFInput(int _dataType, String _componentName, boolean _required) {
	 try {
	     componentName = _componentName;
	     dataType = _dataType;
	     required = _required;
	     jbInit();
	 } catch (Exception e) {
	     e.printStackTrace();
	 }
     }

    public TFInput(String _componentName, boolean _required, String _format) {
	try {
	    componentName = _componentName;
	    dataType = DataTypes.CUSTOM_FORMAT;
	    required = _required;
	    format = _format;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TFInput() {
	try {
	    componentName = "na";
	    dataType = DataTypes.STRING;
	    required = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setLayout(borderLayout);
	setSize(new Dimension(171, 37));
	this.setBounds(new Rectangle(10, 10, 171, 40));
	lblValidator.setText("(*)");
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Default", 1, 11));
	lblValidator.setBorder(null);
	lbl.setBounds(new Rectangle(10, 10, 40, 20));
	textField.setBounds(new Rectangle(10, 30, 120, 20));
	switch (dataType) {
	    case DataTypes.INTEGER:
		textField = new JIntEntry();
		value = 0;
		break;
	    case DataTypes.DATE:
		textField = new JTFecha();
		value = Environment.currentDate;
		break;
	    case DataTypes.SIMPLEDATE:
		textField = new JTDate();
		value = new Date();
		break;
	    case DataTypes.STRING:
		textField = new JEntry();
		value = "";
		break;
	    case DataTypes.DOUBLE:
	        textField = new JDecEntry();
		value = 0.0;
	        break;
	    case DataTypes.DOUBLE_EXTENDED:
	        textField = new JDecEntry("#0.0000");
		value = 0.0;
	        break;
	    case DataTypes.PERCENT:
		//Tengo que poner % entre apóstrofos para evitar
		//que el textField calcule solo el porcentaje
		textField = new JMoneyEntry("\'%\' #0.00");
		value = 0.0;
		break;
	    case DataTypes.MONEY:
	        textField = new JMoneyEntry();
		value = 0.0;
	        break;
	    case DataTypes.MONEY_EXTENDED:
	        textField = new JMoneyEntry(true);
		value = 0.0;
	        break;
	    case DataTypes.COLOR:
		textField = new JTColor(this);
		value = "#FFFFFF";
		break;
	    case DataTypes.GEOMETRY:
	        textField = new JEntry();
		value = "0, 0";
	        break;
	    case DataTypes.CUSTOM_FORMAT:
		try {
		    textField = new BasicTextInput(format);
		} catch (ParseException _x) {
		    Advisor.printException(_x);
		}
	        value = "";
	        break;
	    default:
		textField = new JEntry();
		value = "";
		break;
	}
	setComponentName(componentName);
	textField.setName(componentName);
	textField.setBounds(new Rectangle(0, 15, 50, 20));
	textField.setFont(textField.getFont().deriveFont(Font.BOLD));
	add(lbl, BorderLayout.NORTH);
	add(textField, BorderLayout.CENTER);

	textField.addKeyListener(new KeyAdapter() {

			      public void keyReleased(KeyEvent e) {
				  if (true) { //e.getKeyCode() == KeyEvent.VK_ENTER) {
				      try {
					  textField.commitEdit();
				      } catch (ParseException x) {
					  //x.printStackTrace();
				      }
				  } else  if (e.getKeyChar() == '\'') {
				      e.consume();
				  }
			          if (e.getKeyCode() == KeyEvent.VK_F5 && dataType == DataTypes.GEOMETRY) {
			              retrieveGaiaPoint();
			          }
			      }

			      public void keyTyped(KeyEvent e) {
				  if (e.getKeyChar() == '\'') {
				      e.consume();
				  }
			      }
			  }
	);
	textField.addPropertyChangeListener(new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent e) {
		    //System.out.println(e.getPropertyName());
		    if (e.getPropertyName().equalsIgnoreCase("value")) {
		        if (textField.getText().length() > 0) {
		            setToolTipText(lbl.getText() + " " + textField.getText().toString());
		        } else {
		            setToolTipText(null);
		        }
		    }
		}
	    });
	if (required) {
	    /*add(lblValidator, BorderLayout.EAST);
	    lblValidator.setVisible(true);*/
	    textField.setBackgroundColor(new Color(148,0,0));
	    textField.setForegroundColor(Color.white);
	} else {
	    textField.setBackgroundColor(Color.white);
	    textField.setForegroundColor(Color.black);
	}
    }

    public void retrieveGaiaPoint() {
        if (GaiaEnvironment.point != null) {
            setValue(GaiaEnvironment.point);
        } else {
            setValue(new Point2D.Double(0.0,0.0));
        }
    }

    public void setBackgroundColor(Color _color) {
	textField.setBackgroundColor(_color);
    }

    public void setDisabledTextColor(Color _color) {
	textField.setDisabledTextColor(_color);
    }

    /**
     * @deprecated
     */
    public void setActionListener(ActionListener _actionListener) {
	actionListener = _actionListener;
	textField.addActionListener(actionListener);
    }

    /**
     * @deprecated
     */
    public void setKeyListener(KeyAdapter _keyAdapter) {
	keyAdapter = _keyAdapter;
	textField.addKeyListener(keyAdapter);
    }

    /**
     * @deprecated
     */
    public void setKeyListener(KeyListener _keyListener) {
	keyListener = _keyListener;
	textField.addKeyListener(keyListener);
    }

    /**
     * @deprecated
     */
    public void setMouseListener(MouseAdapter _mouseAdapter) {
	mouseAdapter = _mouseAdapter;
	textField.addMouseListener(mouseAdapter);
    }

    public void setEditable(boolean _valor) {
	textField.setEditable(_valor);
    }

    public void setEnabled(boolean _valor) {
	textField.setEnabled(_valor);
	if (!_valor) {
	    textField.setOpaque(false);
	    textField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
	    textField.setDisabledTextColor(new Color(255, 255, 255));
	} else {
	    textField.setOpaque(true);
	}
    }

    public BasicTextInput getTextField() {
	return textField;
    }

    public void setValue(Object _value) {
        value = _value;
	if (value != null) {
	    try {
		switch (dataType) {
		    case DataTypes.INTEGER:
			textField.setValue(value.toString().length()==0?BigInteger.valueOf(0):new BigInteger(value.toString()));
			break;
		    case DataTypes.DATE:
			textField.setValue(value);
			break;
		    case DataTypes.SIMPLEDATE:
			textField.setValue(value);
			break;
		    case DataTypes.STRING:
			textField.setValue(value);
			break;
		    case DataTypes.DOUBLE:
		    case DataTypes.PERCENT:
		    case DataTypes.MONEY:
			textField.setValue((value.toString().length()==0?new BigDecimal("0.0"):new BigDecimal(value.toString())).setScale(2,BigDecimal.ROUND_HALF_UP));
			break;
		    case DataTypes.COLOR:
			textField.setValue(value);
			break;
		    case DataTypes.DOUBLE_EXTENDED:
		    case DataTypes.MONEY_EXTENDED:
		        textField.setValue((value.toString().length()==0?new BigDecimal("0.0"):new BigDecimal(value.toString())).setScale(4,BigDecimal.ROUND_HALF_UP));
		        break;
		    case DataTypes.GEOMETRY:
                        if (value instanceof Point2D.Double) {
                            textField.setValue("("+ (new DecimalFormat("0.0000")).format(((Point2D.Double)value).getX()) +"; "+ (new DecimalFormat("0.0000")).format(((Point2D.Double)value).getY()) +")");
                        } else {
                            textField.setValue(value);
                        }
		        break;
		    default:
			textField.setValue(value);
			break;
		}
		if (textField.getText().length() > 0) {
		    //setToolTipText(textField.getText().toString());
		    setToolTipText(lbl.getText() + " " + textField.getText().toString());
		} else {
		    setToolTipText(null);
		}
	    } catch (Exception e) {
		Advisor.messageBox("<html><p align=center>Error al intentar asignar el valor \"" + value + "\",<br>se asignará como texto.</p></html>", "Error");
		setText(value.toString());
		e.printStackTrace();
	    }
	}
    }

    public void setText(String _text) {
	textField.setText(_text);
    }

    public Object getValue() {
	return textField.getValue();
    }

    public String getString() {
        if (dataType == DataTypes.DATE || dataType == DataTypes.SIMPLEDATE) {
	    System.err.println("WARNING: Utilizar getDate() en lugar de getString() para DATE/SIMPLEDATE");
            return getDate();
        } else {
            try {
                return textField.getValue().toString().trim().replaceAll("\'","\\\\'").trim().replaceAll("  "," ");
            } catch (NullPointerException e) {
                return textField.getText().trim();
            }
        }
    }

    public String getStringForSQL() {
	return "'" + getString() + "'";
    }

    public double getDouble() {
	if (dataType == DataTypes.DOUBLE || dataType == DataTypes.DOUBLE_EXTENDED) {
	    return new Double(textField.getValue().toString());
	} else {
	    System.err.println("Se ha pedido un DOUBLE a un panel de otro tipo");//, "Error TFInput");
	    return -1;
	}
    }

    public int getInteger() {
	if (dataType == DataTypes.INTEGER) {
	    return new Integer(textField.getValue().toString());
	} else {
	    System.err.println("Se ha pedido un INTEGER a un panel de otro tipo");//, "Error TFInput");
	    return -1;
	}
    }

    public double getAmount() {
	switch (dataType) {
	    case DataTypes.MONEY:
	    case DataTypes.MONEY_EXTENDED:
	    case DataTypes.PERCENT:
	    case DataTypes.DOUBLE:
	    case DataTypes.DOUBLE_EXTENDED:
		return new Double(textField.getValue().toString());
	    default:
		System.err.println("Se ha pedido un DOUBLE a un panel de otro tipo");//, "Error TFInput");
		return -1;
	}
    }

    public BigDecimal getBigDecimal() {
	switch (dataType) {
	    case DataTypes.MONEY:
	    case DataTypes.MONEY_EXTENDED:
	    case DataTypes.PERCENT:
	    case DataTypes.DOUBLE:
	    case DataTypes.DOUBLE_EXTENDED:
		return new BigDecimal(textField.getValue().toString());
	    default:
		System.err.println("Se ha pedido un DOUBLE a un panel de otro tipo");//, "Error TFInput");
		return new BigDecimal("-1");
	}
    }

    public BigDecimal calculatePercentage(BigDecimal _value) {
	if (dataType == DataTypes.PERCENT) {
	    return _value.multiply(new BigDecimal(String.valueOf(getAmount() / 100.0)));
	} else {
	    System.err.println("Se ha pedido un PORCENTAJE a un panel de otro tipo");//, "Error TFInput");
	    return new BigDecimal("-1");
	}
    }

    public BigDecimal addPercentage(BigDecimal _value) {
	if (dataType == DataTypes.PERCENT) {
	    return _value.add(calculatePercentage(_value));
	} else {
	    System.err.println("Se ha pedido un PORCENTAJE a un panel de otro tipo");//, "Error TFInput");
	    return new BigDecimal("-1");
	}
    }

    public BigDecimal substractPercentage(BigDecimal _value) {
	if (dataType == DataTypes.PERCENT) {
	    return _value.subtract(calculatePercentage(_value));
	} else {
	    System.err.println("Se ha pedido un PORCENTAJE a un panel de otro tipo");//, "Error TFInput");
	    return new BigDecimal("-1");
	}
    }


    public String getDate() {
	if (dataType == DataTypes.SIMPLEDATE || dataType == DataTypes.DATE) {
	    return getTextField().getText().equalsIgnoreCase(JTDate.NULLDATE)?"":getTextField().getText();
	} else {
	    System.err.println("Se ha pedido una FECHA a un panel de otro tipo");//, "Error TFInput");
	    return "";
	}
    }

    public String getDateForSQL() {
	if (dataType == DataTypes.SIMPLEDATE || dataType == DataTypes.DATE) {
	    return "'" + Proced.setFormatDate(getDate(),false) + "'";
	} else {
	    System.out.println("Se ha pedido una FECHA a un panel de otro tipo");//, "Error TFInput");
	    return "";
	}
    }
    
    public void focus() {
	getTextField().requestFocus();
    }

    public void setComponentName(String _componentName) {
	this.componentName = _componentName;
	String sLabelText = Environment.lang.getProperty(componentName) + ":";
	lbl.setText(sLabelText);
    }
    
    public BasicLabel getLabel() {
	return lbl;
    }

    public int getDataType() {
	return dataType;
    }
    
    public String getGeometryAsText() {
        String _returns = "";
        if (dataType == DataTypes.GEOMETRY) {
            if (value instanceof Point2D.Double) {
                _returns = (new ESRIPoint(((Point2D.Double)value).getX(),((Point2D.Double)value).getY())).asPostGISPointString();
            } else {
                Advisor.messageBox("Tipo de geometría no soportada", "Error en GeometryAsText");
            }
        }
        return _returns;
    }
    
    public Object getValueObject() {
        return value;
    }

    /*public void setTextField(BasicTextInput _textField) {
	textField.setEnabled(false);
	textField.setVisible(false);
	remove(textField);
	//textField = _textField;
	repaint();
	textField.setBackgroundColor(Color.yellow);
	//add(textField, BorderLayout.CENTER);
    }*/
    
    public void setHorizontalAlignment(int _alignment) {
	textField.setHorizontalAlignment(_alignment);
    }

    public void setToday() {
	if (dataType == DataTypes.SIMPLEDATE) {
	    ((JTDate)textField).setToday();
	} else {
	    System.err.println("Se ha pedido una FECHA a un panel de otro tipo");//, "Error TFInput");
	}
    }

    public void setFirstDayOfYear() {
	if (dataType == DataTypes.SIMPLEDATE) {
	    ((JTDate)textField).setFirstDayOfYear();
	} else {
	    System.err.println("Se ha pedido una FECHA a un panel de otro tipo");//, "Error TFInput");
	}
    }

}
