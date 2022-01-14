package org.digitall.apps.sueldos.interfaces;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.math.BigDecimal;

public class JEntryChangeDec extends JTextField{
    private double inf = -1;
    private double sup = -1;
    private double defaultValue = -1;
    private int decimales = 2;
    public JEntryChangeDec() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JEntryChangeDec(double _inf, double _sup) {
        try {
            inf = _inf;
            sup = _sup;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getTexto(){
        return(this.getText().trim());
    }

    private void jbInit() throws Exception {
        setBorder();
        this.setBorder(BorderFactory.createLineBorder(Color.red, 1));
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

    private void this_keyTyped(KeyEvent e) {
        String numeros = "0123456789." + '\n';
        char tecla = e.getKeyChar();
        this.requestFocus();
        if (numeros.indexOf(tecla) == -1) {
            e.consume();
        } else {
            if (this.getTexto().length() > 0) {
                String textAux =
                    this.getTexto().substring(0, this.getTexto().length());
                if ((this.getSelectedText() != null) &&
                    (this.getSelectedText().length() > 0) && (tecla == '.')) {
                    e.consume();
                }
                if ((textAux.indexOf(".") > -1) && (tecla == '.')) {
                    e.consume();
                } else {
                    double numero =
                        Double.parseDouble(this.getTexto() + e.getKeyChar());
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

    private void setBorder(){
        if(!this.getText().trim().equals("")){
            this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        }else{
            this.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        }
    }

    private void this_focusGained(FocusEvent e) {
	this.selectAll();
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
	this.setSelectionEnd(0);
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
}
