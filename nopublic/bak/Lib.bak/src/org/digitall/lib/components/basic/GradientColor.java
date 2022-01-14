package org.digitall.lib.components.basic;

/**Parece ser para java 1.5.0*/

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