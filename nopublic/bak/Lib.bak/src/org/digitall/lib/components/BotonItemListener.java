package org.digitall.lib.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Observable;

import javax.swing.JToggleButton;

import org.digitall.lib.calendar.JCalendario;

public class BotonItemListener extends Observable implements ItemListener {

    BotonItemListener(JCalendario calendar) {
	this.calendar = calendar;
    }

    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == 1) {
	    JToggleButton button = (JToggleButton)e.getItem();
	    calendar.setDay(button.getText());
	    Integer temp = (Integer)calendar.yearCombo.getSelectedItem();
	    calendar.setYear(temp.toString());
	    temp = new Integer(calendar.monthCombo.getSelectedIndex() + 1);
	    calendar.setMonth(temp.toString());
	    setChanged();
	    notifyObservers();
	}
    }
    JCalendario calendar;

}
