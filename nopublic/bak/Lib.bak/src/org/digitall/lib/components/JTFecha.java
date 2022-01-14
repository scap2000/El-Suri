package org.digitall.lib.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.calendar.SelectorFecha;
import org.digitall.lib.components.basic.BasicTextInput;

//

public class JTFecha extends BasicTextInput {

    public JTFecha() {
	super(new SimpleDateFormat("dd/MM/yyyy"));
	setValue("");
	setEditable(false);
	//setBackground(new Color(255, 255, 255));
	//setBorder(BorderFactory.createLineBorder(new Color(196, 50, 50), 2));
	setHorizontalAlignment(CENTER);
	setToolTipText("Botón izquierdo: seleccionar fecha; Botón derecho: borrar fecha");
	addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (isEnabled()) {
			    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				BasicTextInput kk = new BasicTextInput();
				kk.setValue(((BasicTextInput)e.getSource()).getText());
				SelectorFecha c = new SelectorFecha(kk);
				org.digitall.lib.components.ComponentsManager.centerWindow(c);
				c.setModal(true);
				c.show();
				setText(kk.getText());
			    } else {
				setText("");
			    }
			}
		    }

		});
    }

   /* public JTFecha() {
	super(new DateFormat());
	setValue(new Date());
	// Definición de la máscara de DNI
	MaskFormatter maskDNI = null;
	try {
	  maskDNI = new MaskFormatter("##/##/####");
	} catch (ParseException e) {
	    // De momento, no hacemos nada
	  }
	// El caràcter a mostrar en las posiciones escribibles es el
	// subrayado.
	maskDNI.setPlaceholderCharacter('_');
	maskDNI.install(this);
	setFormatter(maskDNI);
	maskDNI.setOverwriteMode(true);
	setEditable(true);
	//setBackground(new Color(255, 255, 255));
	//setBorder(BorderFactory.createLineBorder(new Color(196, 50, 50), 2));
	setHorizontalAlignment(CENTER);
	setToolTipText("Botón izquierdo: seleccionar fecha; Botón derecho: borrar fecha");
	addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (isEnabled()) {
			    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				BasicTextInput kk = new BasicTextInput(((BasicTextInput)e.getSource()).getText());
				SelectorFecha c = new SelectorFecha(kk);
				org.digitall.lib.components.ComponentsManager.centerWindow(c);
				c.setModal(true);
				c.show();
				setText(kk.getText());
			    } else {
				setText("");
			    }
			}
		    }

		});
    }*/


    public String getSQLFecha(String _campo) {
	String fecha = super.getText();
	if (fecha.length() > 0) {
	    return _campo + " = '" + Proced.setFormatDate(fecha, false) + "'";
	} else {
	    return _campo + " = null";
	}
    }
    
    public void setValue(Object _object) {
	//super.setValue(_object);
	setText(_object.toString());
	//System.out.println("FECHA: "  + _object);
	if (_object != null) {
	    if (_object.toString().length() != 0) {
		//super.setValue(_object);
	    }
	}
    }

    public Date getDate() {
	Calendar cal = Calendar.getInstance();
	String fecha = super.getText();
	if (fecha.length() > 0) {
	    String[] dateParts = fecha.split("/");
    	    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
	    cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1]));
    	    cal.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
	    return cal.getTime();
    	} else {
	    return null;
	}
    }

}
