package org.digitall.apps.sueldos.interfaces;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import org.digitall.lib.components.JEntry;

public class JMyMoneyEntry extends JTextField {

    private double inf = -1;
    private double sup = -1;
    private double defaultValue = 0.0;
    private int decimales = 2;

    public JMyMoneyEntry() {
    
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public JMyMoneyEntry(double _inf, double _sup) {
	try {
	    inf = _inf;
	    sup = _sup;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public JMyMoneyEntry(double _default) {
	try {
	    defaultValue = _default;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	initText();
	super.setHorizontalAlignment(RIGHT);
	this.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
				    this_keyTyped(e);
				}
			    }
	);
	this.addFocusListener(new FocusAdapter() {
				  public void focusGained(FocusEvent e) {
				      this_focusGained(e);
				  }
	
				  public void focusLost(FocusEvent e) {
				      this_focusLost(e);
				  }
			      }
	);

    }
    
    private void initText(){
	this.setText("$" + defaultValue);
	this.setCaretPosition(this.getTexto().length());
    }

    private void this_keyTyped(KeyEvent e) {
        String numeros = "0123456789." + '\n';
        double numero = 0;
        char tecla = e.getKeyChar();
        this.requestFocus();
        validateKeyMove(e);
        if (numeros.indexOf(tecla) == -1) {
            e.consume();
        } else {
            if (this.getTexto().length() > 0) {
                String textAux =
                    this.getTexto().substring(0, this.getTexto().length());
                if ((this.getSelectedText() != null) &&
                    ((this.getSelectedText().length() - 1) > 0) &&
                    (tecla == '.')) {
                    e.consume();
                }
                if (((textAux.indexOf(".") > -1) && (tecla == '.')) ||
                    ((tecla == '.') && (textAux.equals("$")))) {
                    e.consume();
                } else {
                    if (!this.getTexto().equals("$")) {
                        numero =
                                Double.parseDouble(this.getTexto().substring(1,
                                                                             this.getTexto().length()) +
                                                   e.getKeyChar());
                    }
                    if (inf != sup) {
                        if ((numero < inf) || (numero > sup)) {
                            e.consume();
                        }
                    }
                }
            } else {
                if (tecla == '.') {
                    e.consume();
                }
            }
        }
        setBorder();
    }
    
    private void validateKeyMove(KeyEvent e){
    }

    private void setBorder(){
	if(!this.getText().trim().equals("")){
	    this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
	}else{
	    this.setBorder(BorderFactory.createLineBorder(Color.red, 1));
	}
    }

    private void this_focusGained(FocusEvent e) {
	this.select(1,this.getTexto().length());
	setBorder();
    }
    
    public void setTexto(String _texto){
	this.setText(_texto);
	setDecimales(_texto);
    }
    
    private void setDecimales(String _texto){
	BigDecimal numero;
	numero = new BigDecimal(_texto).setScale(2,BigDecimal.ROUND_UP);
	this.setText(numero.toString());
    }
    
    public void setLimites(double _inf,double _sup){
	inf = _inf;
	sup = _sup;
    }

    private void this_focusLost(FocusEvent e) {
	if(this.getTexto().equals("")){
	    if(defaultValue > -1){
		this.setTexto(String.valueOf(defaultValue));
	    }else{
		if(inf > -1){
		    this.setTexto(String.valueOf(inf));
		}
	    }
	}
	validateValue();
	this.setSelectionEnd(0);
    }
    
    private void validateValue(){
	String text = this.getTexto();
	if((text.equals("$"))||(text.equals(""))){
	    initText();
	}else{
	    String valueString = this.getTexto().substring(1,this.getTexto().length());
	    if((!valueString.equals(""))&&(!valueString.equals("."))){
		double value = Double.parseDouble(this.getTexto().substring(1,this.getTexto().length()));
		if(value == 0){
		    initText();
		}
	    }else{
		initText();	
	    }
	}
    }
    
    public void setDefaultValue(double _default){
	defaultValue = _default;
    }
    public void setDecimal(int _decimales){
	decimales = _decimales;
    }
    private Integer getPartDecimal(){
	return(Integer.parseInt(getTexto().substring(getTexto().indexOf("."),getTexto().length())));
    }
    
    public String getTexto(){
	return(this.getText().trim());
    }
    /*****************************************/

    public void setTextColor(Color _color) {
	this.setForeground(_color);
    }
    
    public double getAmount() {
	//return new Double(getValue().toString());
	return 0;
    }

}
