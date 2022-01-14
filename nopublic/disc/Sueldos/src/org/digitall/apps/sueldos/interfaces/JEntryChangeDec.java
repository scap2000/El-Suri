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
 * JEntryChangeDec.java
 *
 * */
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
