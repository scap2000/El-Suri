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
 * GradientColor.java
 *
 * */
package org.digitall.lib.components.basic;

/**Parece ser que sólo funciona en java 1.5*/

/*import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.LinearGradientPaint;
import java.awt.Paint;

public class GradientColor {

    public static final int LINEAR_HORIZONTAL= 1;
    public static final int LINEAR_HORIZONTAL_CENTRAL = 2;
    public static final int LINEAR_VERTICAL= 3;
    public static final int LINEAR_VERTICAL_CENTRAL = 4;
    public static final int LINEAR_TRANSVERSAL_IZQUIERDA = 5;
    public static final int LINEAR_TRANSVERSAL_IZQUIERDA_CENTRAL = 6;
    public static final int LINEAR_TRANSVERSAL_DERECHA = 7;
    public static final int LINEAR_TRANSVERSAL_DERECHA_CENTRAL = 8;

    private int aTipo;
    private int x1, x2, y1, y2;
    private Color color1 = new Color(0x666f7f), color2 = new Color(0x262d3d);
    private Component componente;

    public GradientColor(){

    }

    public GradientColor(int aTipo){
        setATipo(aTipo);
    }

    public GradientColor(int aTipo, Component componente){
        setATipo(aTipo);
        setComponente(componente);
    }

    public Paint getGradiente(){
        switch (getATipo()){
        case LINEAR_HORIZONTAL:
            x1=0;
            y1=0;
            x2=componente.getWidth();
            y2=0;
            return new GradientPaint(x1,y1,getColor1(), x2, y2,getColor2());
        case LINEAR_HORIZONTAL_CENTRAL:
            x1=0;
            y1=0;
            x2=componente.getWidth();
            y2=0;
            return new LinearGradientPaint((float)x1,(float)y1, (float)x2, (float)y2,
                    new float[]{0.0f,0.45f,0.55f,1.0f},
                    new Color[]{getColor1(),getColor2(), getColor2(), getColor1()});
        case LINEAR_TRANSVERSAL_IZQUIERDA:
            x1=0;
            y1=0;
            x2=componente.getWidth();
            y2=componente.getHeight();
            return new GradientPaint(x1,y1,getColor1(), x2, y2,getColor2());
        case LINEAR_TRANSVERSAL_IZQUIERDA_CENTRAL:
            x1=0;
            y1=0;
            x2=componente.getWidth();
            y2=componente.getHeight();
            return new LinearGradientPaint((float)x1,(float)y1, (float)x2, (float)y2,
                    new float[]{0.0f,0.40f,0.60f,1.0f},
                    new Color[]{getColor1(),getColor2(), getColor2(), getColor1()});
        case LINEAR_TRANSVERSAL_DERECHA:
            x1=componente.getWidth();
            y1=0;
            x2=0;
            y2=componente.getHeight();
            return new GradientPaint(x1,y1,getColor1(), x2, y2,getColor2());
        case LINEAR_TRANSVERSAL_DERECHA_CENTRAL:
            x1=componente.getWidth();
            y1=0;
            x2=0;
            y2=componente.getHeight();
            return new LinearGradientPaint((float)x1,(float)y1, (float)x2, (float)y2,
                    new float[]{0.0f,0.45f,0.5f,1.0f},
                    new Color[]{getColor1(),getColor2(), getColor2(), getColor1()});
        case LINEAR_VERTICAL:
            x1=0;
            y1=0;
            x2=0;
            y2=componente.getHeight();
            return new GradientPaint(x1,y1,getColor1(), x2, y2,getColor2());
        case LINEAR_VERTICAL_CENTRAL:
            x1=0;
            y1=0;
            x2=0;
            y2=componente.getHeight();
            return new LinearGradientPaint((float)x1,(float)y1, (float)x2, (float)y2,
                    new float[]{0.0f,0.40f,0.60f,1.0f},
                    new Color[]{getColor1(),getColor2(), getColor2(), getColor1()});
        default:
            return new GradientPaint(x1,y1,getColor1(), x2, y2,getColor2());
        }

    }

    public Component getComponente() {
        return componente;
    }

    public void setComponente(Component componente) {
        this.componente = componente;
        x1=0;
        y1=0;
        x2=componente.getWidth();
        y2=componente.getHeight();
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1.darker();
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2.darker();
    }

    public int getATipo() {
        return aTipo;
    }

    public void setATipo(int aTipo) {
        this.aTipo = aTipo;
    }

}*/