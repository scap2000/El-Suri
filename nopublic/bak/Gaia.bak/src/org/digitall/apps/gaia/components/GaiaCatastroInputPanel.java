package org.digitall.apps.gaia.components;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaCatastroInputPanel extends TFInput {

    private ESRIPoint point = null;

    public GaiaCatastroInputPanel() {
	super(DataTypes.INTEGER,"Cadastral", false);
	setEditable(false);
	setValue(0);
	getTextField().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
		    setValue(0);
		} else if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && point != null) {
		    fetchCatastro(point, true);
		}
	    }
	});
    }

    public void fetchCatastro(ESRIPoint _point) {
	fetchCatastro(_point, _point.getIdPoint() == -1);
    }

    public void fetchCatastro(ESRIPoint _point, boolean _force) {
	point = _point;
	if (_force) {
	    Vector catastros = LibSQL.getVector(GaiaEnvironment.getScheme() + ".getCatastrosAtPoint", point.getX() + "," + point.getY());
	    if (catastros.size() > 0) {
		catastros.insertElementAt("0",0);
		String _catastro = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione 0 para omitir", "Seleccione el Nº de catastro", JOptionPane.QUESTION_MESSAGE, null, catastros.toArray(), String.valueOf(getValue()));
		if (_catastro != null) {
		    setValue(_catastro);
		}
	    } else {
		setValue(0);
	    }
	}
    }
    
}
