package org.digitall.lib.misc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Torta extends JComponent {

    private int n;

    public Torta(double[] valores, Color[] vcolor, Rectangle area) {
	PieValue[] slices = new PieValue[valores.length];
	for (int i = 0; i < valores.length; i++) {
	    //System.out.println(Color.decode(vcolor[i]));
	    slices[i] = new PieValue(valores[i], vcolor[i]);
	    /*slices[1] = new PieValue(33, Color.green);
      slices[2] = new PieValue(20, Color.pink);
      slices[3] = new PieValue(15, Color.blue);*/
	}
	//Proced.GuardaImagen(Proced.drawPie(area, slices),"/tmp/imagen.png");
    }
    // This method is called whenever the contents needs to be painted

    public void paint(Graphics g) {
	// Draw the pie
    }

}
