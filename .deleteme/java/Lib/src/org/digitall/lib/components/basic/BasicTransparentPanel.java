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
 * BasicTransparentPanel.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import org.digitall.lib.components.buttons.AcceptButton;

public final class BasicTransparentPanel extends BasicPanel {

    /* Private Fields: For use only by this class.
	 *  These fields hold information needed by more
	 *  than one method of this class.
	 */
    private boolean fullTransparencyEnabled;
    private Color baseColor;
    private Color outerBorderColor;
    private Color innerBorderColor;
    private int obw;
    private int ibw;
    private int cbw;
    /* -- START OF METHODS -- */
    /* public TransparentContainer(Color color, boolean fullTrans)
	 *   Initiallizes the transparent container object
	 *   with 'color' as its base color.
	 */

    public BasicTransparentPanel(Color color, boolean fullTrans) {
	fullTransparencyEnabled = fullTrans;
	baseColor = color;
	Color borders[] = findBorderColors();
	outerBorderColor = borders[0];
	innerBorderColor = borders[1];
	obw = 3;
	ibw = 1;
	cbw = obw + ibw;
    }
    /* private Color[] findBorderColors(Color base)
	 *   Calculates the colors for the outer and inner
	 *   borders of the object based on the base color.
	 */

    private Color[] findBorderColors() {
	Color borders[] = new Color[2];
	int colorData[] = new int[9];
	colorData[0] = getBaseColor().getRed();
	colorData[1] = getBaseColor().getGreen();
	colorData[2] = getBaseColor().getBlue();
	colorData[3] = colorData[0] - 50;
	// outerBorder red
	colorData[4] = colorData[1] - 45;
	// outerBorder green
	colorData[5] = colorData[2] - 35;
	// outerBorder blue
	colorData[6] = colorData[0] + 30;
	// innerBorder red
	colorData[7] = colorData[1] + 30;
	// innerBorder green
	colorData[8] = colorData[2] + 20;
	// innerBorder blue
	/* Make sure the new color data is not out of bounds: */
	for (int i = 3; i < colorData.length; i++) {
	    if (colorData[i] > 255) {
		colorData[i] = 255;
	    } else if (colorData[i] < 0) {
		colorData[i] = 0;
	    }
	}
	borders[0] = new Color(colorData[3], colorData[4], colorData[5]);
	borders[1] = new Color(colorData[6], colorData[7], colorData[8]);
	return borders;
    }
    /* public Color getBaseColor()
	 *   Returns the baseColor of this object.
	 */

    public Color getBaseColor() {
	return baseColor;
    }
    /* public Color getOuterColor()
	 *   Returns the outerBorderColor of this object.
	 */

    public Color getOuterColor() {
	return outerBorderColor;
    }
    /* public Color getInnerColor()
	 *   Returns the innerBorderColor of this object.
	 */

    public Color getInnerColor() {
	return innerBorderColor;
    }
    /* public boolean getFullTransEnabled()
	 *   Returns whether or not this object will render
	 *   with all of its transparency effects.
	 */

    public boolean getFullTransEnabled() {
	return fullTransparencyEnabled;
    }
    /* protected void paintComponent(Graphics g)
	 *   Paints the component with the borders and colors
	 *   that were set up in above methods.
	 */

    protected void paintComponent(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
	AlphaComposite alphaComp;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	g2d.setColor(getBaseColor());
	/* Draw the main body of the component */
	if (getFullTransEnabled()) {
	    alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
	    g2d.setComposite(alphaComp);
	} else {
	    alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
	    g2d.setComposite(alphaComp);
	}
	g2d.fillRect(cbw, cbw, super.getWidth() - 2 * cbw, super.getHeight() - 2 * cbw);
	alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
	g2d.setComposite(alphaComp);
	/* Draw the inner border: */
	g2d.setColor(getInnerColor());
	g2d.fillRect(obw, obw, ibw, super.getHeight() - obw * 2);
	// left border
	g2d.fillRect(obw, obw, super.getWidth() - obw, ibw);
	// top border
	g2d.fillRect(super.getWidth() - cbw, obw, ibw, super.getHeight() - obw * 2);
	// right border
	g2d.fillRect(obw, super.getHeight() - cbw, super.getWidth() - obw * 2, ibw);
	// bottom border
	/* Draw the outer border: */
	g2d.setColor(getOuterColor());
	g2d.fillRect(0, 0, obw, super.getHeight());
	// left border
	g2d.fillRect(0, 0, super.getWidth() + obw, obw);
	// top border
	g2d.fillRect(super.getWidth() - obw, 0, obw, super.getHeight());
	// right border
	g2d.fillRect(0, super.getHeight() - obw, super.getWidth(), obw);
	// bottom border
	alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
	g2d.setComposite(alphaComp);
    }

}
