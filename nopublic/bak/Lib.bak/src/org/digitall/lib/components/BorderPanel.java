package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BorderPanel {

    private static final Color defaultTitleColor = Color.WHITE;
    private static final Color defaultBorderColor = Color.WHITE;

    public static Border getBorderPanel(String _titulo, Color _textColor, Color _borderColor) {
	if (_titulo.length()>0){
	    _titulo = " " + _titulo + " ";
	}
	TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(_borderColor, 1), _titulo, 0, 0, new Font("Dialog", Font.BOLD, 12));
	borde.setTitleColor(defaultTitleColor);
	return borde;
    }

    public static Border getBorderPanel(String _titulo) {
	if (_titulo.length()>0){
	    _titulo = " " + _titulo + " ";
	}
	TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(defaultBorderColor, 1), _titulo, 0, 0, new Font("Dialog", Font.BOLD, 12));
	borde.setTitleColor(defaultTitleColor);
	return borde;
    }

}
