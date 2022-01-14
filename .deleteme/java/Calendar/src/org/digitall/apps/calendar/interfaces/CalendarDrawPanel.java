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
 * CalendarDrawPanel.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.calendar.classes.DayRectangle;
import org.digitall.apps.calendar.classes.MonthRectangle;
import org.digitall.apps.calendar.classes.WeekDayRectangle;
import org.digitall.apps.calendar.classes.WeekRectangle;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.environment.Debug;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class CalendarDrawPanel extends BasicContainerPanel {

    private BasicButton bInfo = new BasicButton();
    private String operationStatus;
    private BasicButton bZoom = new BasicButton();
    //Constantes de operacion
    private final int INFO = 8;
    private final int ZOOM = 10;
    //Variables de Posicion
    private Point mousePosition = new Point();
    private Point2D.Double currentPosition = new Point2D.Double();
    private Point startDragPosition = null;
    //Servira de algo?
    private int containedShape = -1;
    protected Line2D panLine = new Line2D.Float();
    private Point2D.Double startDrawRectPosition;
    private Point2D.Double endDrawRectPosition;
    private Rectangle2D drawRectangle;
    double scaleFactor = 1.05d;
    //Variables del Universo
    private double extents = -1;
    private double drawFactor = 0;
    private double drawFactorOriginal = 0;
    private double xOffset = 0;
    private double yOffset = 0;
    private double xOffsetOriginal = 0;
    private double yOffsetOriginal = 0;
    private double xOffsetPosta = 0;
    private double yOffsetPosta = 0;
    private int fWidth = 0;
    private int fHeight = 0;
    private double drawScale = 1;
    //Variables del momento
    private double base = 1000.0;
    //1000 metros
    private double altura = 1000.0;
    //1000 metros
    private double area = base * altura;
    //100 has ??? :=0 !!!
    //Variables de trabajo
    private MouseListener eraseListener;
    private MouseMotionListener eraseMotionListener;
    private MouseWheelListener eraseWheelListener;
    private int pointDiameter = 16;
    private Vector daysVector = new Vector();
    private Vector weekDaysVector = new Vector();
    private Vector monthsVector = new Vector();
    private Vector weeksVector = new Vector();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicCheckBox chkExpired = new BasicCheckBox();
    private BasicLabel jLabel2 = new BasicLabel(IconTypes.CRFileBlack_16x16);
    private BasicLabel jLabel4 = new BasicLabel(IconTypes.CRFileRed_16x16);
    private BasicLabel jLabel5 = new BasicLabel(IconTypes.CRFileYellow_16x16);
    private BasicLabel jLabel6 = new BasicLabel(IconTypes.CRFileGreen_16x16);
    private BasicLabel jLabel7 = new BasicLabel(IconTypes.CRFileBlue_16x16);
    private BasicLabel jLabel8 = new BasicLabel(IconTypes.CRFileTotal_16x16);
    private BasicCheckBox chkCustom = new BasicCheckBox();
    private BasicCheckBox chkDanger = new BasicCheckBox();
    private BasicCheckBox chkOk = new BasicCheckBox();
    private BasicCheckBox chkTotal = new BasicCheckBox();
    private BasicCheckBox chkWarned = new BasicCheckBox();
    private FindButton btnFind = new FindButton();
    private GregorianCalendar calendar = new GregorianCalendar();
    private ESRIPolygon limitBounds;
    private int dayWidth = 17;
    private int dayHeight = 15;
    private int monthWidth = dayWidth * 4;
    private int containedDay = -1;

    public CalendarDrawPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	/**mPoints = new Point2D[2];*/
	//        mousePosition.setLocation(0, 0);
	//        currentPosition.setLocation(0, 0);
	//this.setSize(new Dimension(800, 600));
	jPanel4.setBounds(new Rectangle(495, 135, 300, 65));
	jPanel4.setLayout(null);
	jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	chkExpired.setBounds(new Rectangle(40, 15, 16, 15));
	chkExpired.setSize(new Dimension(16, 15));
	chkExpired.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      chkExpired_actionPerformed(e);
				  }

			      }
	);
	jLabel2.setText("");
	jLabel2.setBounds(new Rectangle(20, 15, 18, 14));
	jLabel2.setSize(new Dimension(18, 14));
	jLabel4.setText("");
	jLabel4.setBounds(new Rectangle(80, 15, 18, 15));
	jLabel5.setText("");
	jLabel5.setBounds(new Rectangle(140, 15, 18, 15));
	jLabel6.setText("");
	jLabel6.setBounds(new Rectangle(20, 40, 18, 15));
	jLabel7.setText("");
	jLabel7.setBounds(new Rectangle(80, 40, 18, 15));
	jLabel8.setText("");
	jLabel8.setBounds(new Rectangle(140, 40, 18, 15));
	chkCustom.setBounds(new Rectangle(100, 40, 16, 15));
	chkCustom.setSize(new Dimension(16, 17));
	chkCustom.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     chkCustom_actionPerformed(e);
				 }

			     }
	);
	chkDanger.setBounds(new Rectangle(100, 15, 16, 15));
	chkDanger.setSize(new Dimension(16, 17));
	chkDanger.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     chkDanger_actionPerformed(e);
				 }

			     }
	);
	chkOk.setBounds(new Rectangle(40, 40, 16, 15));
	chkOk.setSize(new Dimension(16, 17));
	chkOk.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 chkOk_actionPerformed(e);
			     }

			 }
	);
	chkTotal.setBounds(new Rectangle(160, 40, 16, 15));
	chkTotal.setSize(new Dimension(16, 17));
	chkTotal.setSelected(true);
	chkTotal.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    chkTotal_actionPerformed(e);
				}

			    }
	);
	chkWarned.setBounds(new Rectangle(160, 15, 15, 15));
	chkWarned.setSize(new Dimension(16, 17));
	chkWarned.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     chkWarned_actionPerformed(e);
				 }

			     }
	);
	btnFind.setText("Buscar");
	btnFind.setBounds(new Rectangle(210, 20, 80, 25));
	btnFind.setSize(new Dimension(80, 25));
	bInfo.setText("Info");
	bInfo.setBounds(new Rectangle(710, 355, 77, 22));
	bInfo.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 bInfo_actionPerformed(e);
			     }

			 }
	);
	bZoom.setText("Zoom");
	bZoom.setBounds(new Rectangle(695, 95, 90, 25));
	bZoom.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 bZoom_actionPerformed(e);
			     }

			 }
	);
	this.add(bInfo, null);
	this.add(bZoom, null);
	add(jPanel4, null);
	addMouseWheelListener(commonWheelListener);
	addMouseListener(commonMouseListener);
	addMouseMotionListener(commonMotionListener);
	setBackground(Color.WHITE);
	jPanel4.add(btnFind, null);
	jPanel4.add(chkExpired, null);
	jPanel4.add(jLabel2, null);
	jPanel4.add(jLabel4, null);
	jPanel4.add(jLabel5, null);
	jPanel4.add(jLabel6, null);
	jPanel4.add(jLabel7, null);
	jPanel4.add(jLabel8, null);
	jPanel4.add(chkCustom, null);
	jPanel4.add(chkDanger, null);
	jPanel4.add(chkOk, null);
	jPanel4.add(chkTotal, null);
	jPanel4.add(chkWarned, null);
    }

    public void initCalendar() {
	fWidth = 0;
	fHeight = 0;
	setDays3();
	setOperation(INFO);
    }

    public void paint(Graphics _graphics) {
	super.paint(_graphics);
	Graphics2D graphics2d = (Graphics2D)_graphics;
	//Recorro el vector layer para dibujar uno por uno
	//en el orden en que fueron agregados al vector
	setEnvironment();
	drawCalendar(graphics2d);
	graphics2d.setColor(Color.yellow);
	graphics2d.draw(panLine);
	int faja = 3;
	int zona = 19;
	/*if (currentPosition != null) {
	    //ESTAS DOS LINEAS CLAVAN EL PROGRAMA!!!
	    //LatLongCoord latlon = CoordinateSystems.gk2geo(currentPosition.getX(), currentPosition.getY(), faja);
	    //String latlong = CoordinateSystems.dec2gms(latlon.getLatitude(), 4) + " lat " + CoordinateSystems.dec2gms(latlon.getLongitude(), 4) + " long";
	    //POR ESO ESTï¿½N COMENTADAS
	    graphics2d.setColor(Color.black);
	    graphics2d.drawString("(" + decimalFormat(currentPosition.getX(), 4) + " " + decimalFormat(currentPosition.getY(), 4) + ")", (int)mousePosition.getX(), (int)mousePosition.getY());
	}*/
	/** DIBUJO EL POLIGONO DE ZOOM */
	if (drawRectangle != null) {
	    graphics2d.setColor(Color.BLUE);
	    graphics2d.draw(drawRectangle);
	    Composite originalComposite = graphics2d.getComposite();
	    graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
	    graphics2d.fill(drawRectangle);
	    graphics2d.setComposite(originalComposite);
	}
    }

    public ESRIPolygon getFakePolyFromPoly(ESRIPolygon _poly) {
	double[] xy = new double[_poly.getVertexCount() * 2];
	for (int i = 0; i < _poly.getVertexCount() * 2; i += 2) {
	    xy[i] = ((_poly.getX(i / 2) - xOffset) * drawFactor);
	    xy[i + 1] = (fHeight - ((_poly.getY(i / 2) - yOffset) * drawFactor));
	}
	return new ESRIPolygon.Double(xy);
    }

    private Point toPixel(double xd, double yd) {
	int x = (int)(((xd - xOffset) * drawFactor));
	int y = (int)((fHeight - ((yd - yOffset) * drawFactor)));
	Point xy = new Point(x, y);
	return xy;
    }

    private Point toPixel(Point2D.Double xy) {
	int x = (int)(((xy.getX() - xOffset) * drawFactor));
	int y = (int)((fHeight - ((xy.getY() - yOffset) * drawFactor)));
	return new Point(x, y);
    }

    private int xtoPixel(double x) {
	return (int)(((x - xOffset) * drawFactor));
    }

    private int ytoPixel(double y) {
	return (int)((fHeight - ((y - yOffset) * drawFactor)));
    }

    private Point2D.Double toSpace(int x, int y) {
	double xd = x / drawFactor + xOffset;
	double yd = (fHeight - y) / drawFactor + yOffset;
	return new Point2D.Double(xd, yd);
    }

    private Point2D.Double toSpace(Point _point) {
	double xd = _point.getX() / drawFactor + xOffset;
	double yd = (fHeight - _point.getY()) / drawFactor + yOffset;
	return new Point2D.Double(xd, yd);
    }

    private Point2D.Double toSpace(Point2D _point) {
	double xd = _point.getX() / drawFactor + xOffset;
	double yd = (fHeight - _point.getY()) / drawFactor + yOffset;
	return new Point2D.Double(xd, yd);
    }

    private double xtoSpace(int x) {
	return (x / drawFactor + xOffset);
    }

    private double ytoSpace(int y) {
	return ((fHeight - y) / drawFactor + yOffset);
    }

    private void bZoom_actionPerformed(ActionEvent e) {
	setOperation(ZOOM);
    }

    public void setOperation(int _operacion) {
	removeMouseListener(eraseListener);
	removeMouseMotionListener(eraseMotionListener);
	restartEnvironment();
	switch (_operacion) {
	    case INFO :
		eraseListener = infoMouseListener;
		eraseMotionListener = infoMotionListener;
		operationStatus = "Show Info: ";
		break;
	    case ZOOM :
		eraseListener = zoomMouseListener;
		eraseMotionListener = zoomMotionListener;
		operationStatus = "Zoom window: ";
		break;
	}
	addMouseListener(eraseListener);
	addMouseMotionListener(eraseMotionListener);
    }
    /**REVISAR EL WHEELLISTENER*/
    protected MouseWheelListener commonWheelListener = new MouseWheelListener() {

	    public void mouseWheelMoved(MouseWheelEvent me) {
		//Diferencia entre el punto y el offset
		double originX = xtoSpace(me.getX());
		double originY = ytoSpace(me.getY());
		if (me.getWheelRotation() < 0) {
		    //drawScale = drawScale * (-scaleFactor * me.getWheelRotation());
		    yOffset += me.getWheelRotation() * 30;
		    if (yOffset < limitBounds.getBounds().getMinY()) {
			yOffset = limitBounds.getBounds().getMinY();
		    }
		} else {
		    //drawScale = drawScale / (scaleFactor * me.getWheelRotation());
		    yOffset += me.getWheelRotation() * 30;
		    if (yOffset > limitBounds.getBounds().getMaxY()) {
			yOffset = limitBounds.getBounds().getMaxY();
		    }
		}
		drawFactor = drawFactorOriginal * drawScale;
		double destX = xtoSpace(me.getX());
		double destY = ytoSpace(me.getY());
		xOffset = xOffsetOriginal + (originX - destX);
		yOffset = yOffsetOriginal - (originY - destY);
		xOffsetOriginal = xOffset;
		yOffsetOriginal = yOffset;
		repaint();
	    }

	}
    ;
    protected MouseListener commonMouseListener = new MouseListener() {

	    public void mousePressed(MouseEvent me) {
		if (me.getButton() == me.BUTTON2) {
		    startDragPosition = mousePosition;
		}
	    }

	    public void mouseReleased(MouseEvent me) {
		if (startDragPosition != null) {
		    xOffset = xOffsetOriginal + (xtoSpace(startDragPosition.x) - xtoSpace(mousePosition.x));
		    yOffset = yOffsetOriginal - (xtoSpace(startDragPosition.y) - xtoSpace(mousePosition.y));
		    xOffsetOriginal = xOffset;
		    yOffsetOriginal = yOffset;
		    panLine = new Line2D.Float();
		    startDragPosition = null;
		    repaint();
		}
	    }

	    public void mouseClicked(MouseEvent me) {
		if ((me.getButton() == 2) && (me.getClickCount() == 2)) {
		    flyTo(1.0d, xOffsetPosta, yOffsetPosta);
		}
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	}
    ;
    protected MouseMotionListener commonMotionListener = new MouseMotionListener() {

	    public void mouseMoved(MouseEvent me) {
		mousePosition = me.getPoint();
		repaint();
	    }

	    public void mouseDragged(MouseEvent me) {
		if (startDragPosition != null) {
		    mousePosition = me.getPoint();
		    currentPosition = toSpace(mousePosition);
		    xOffset = xOffsetOriginal + (xtoSpace(startDragPosition.x) - xtoSpace(mousePosition.x));
		    yOffset = yOffsetOriginal - (xtoSpace(startDragPosition.y) - xtoSpace(mousePosition.y));
		    panLine.setLine(startDragPosition, mousePosition);
		    repaint();
		}
	    }

	}
    ;
    protected MouseListener zoomMouseListener = new MouseListener() {

	    public void mousePressed(MouseEvent me) {
	    }

	    public void mouseReleased(MouseEvent me) {
	    }

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    if (startDrawRectPosition == null) {
			startDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		    } else {
			endDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
			//ZOOM
			createDrawRectangle();
			setEnvironment(drawRectangle);
			startDrawRectPosition = null;
			drawRectangle = null;
		    }
		    repaint();
		}
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	}
    ;
    protected MouseMotionListener zoomMotionListener = new MouseMotionListener() {

	    public void mouseMoved(MouseEvent me) {
		mouseDraggedOrMoved(me);
	    }

	    public void mouseDragged(MouseEvent me) {
		mouseDraggedOrMoved(me);
	    }

	    public void mouseDraggedOrMoved(MouseEvent me) {
		endDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		createDrawRectangle();
		repaint();
	    }

	}
    ;

    private void bInfo_actionPerformed(ActionEvent e) {
	setOperation(INFO);
    }
    protected MouseListener infoMouseListener = new MouseListener() {

	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (containedDay != -1) {
		    //containedDay = -1;
		    DayRectangle _day = (DayRectangle)daysVector.elementAt(containedDay);
		    _day.fireClick(me);
		    repaint();
		}
		if (containedShape != -1) {
		    /*for (int i = 0; i < layers.size(); i++) {
			Layer _layer = (Layer)layers.elementAt(i);
			if (_layer.getShapeType() == ShapeTypes.POINT) {
			    ESRIPoint _point = (ESRIPoint)(_layer.getGeometries().toArray()[containedShape]);
			    _point.fireClick(me);
			} else if (_layer.getShapeType() == ShapeTypes.POLYGON) {
			    ESRIPolygon _polygon = (ESRIPolygon)(_layer.getGeometries().toArray()[containedShape]);
			    _polygon.fireClick(me);
			}
			//TODO ELSE
		    }*/
		}
	    }
	    //REVISAR

	    public void mousePressed(MouseEvent me) {

	    }
	    //REVISAR

	    public void mouseReleased(MouseEvent me) {

	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	}
    ;
    protected MouseMotionListener infoMotionListener = new MouseMotionListener() {

	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		mousePosition = me.getPoint();
		containedDay = -1;
		for (int i = 0; i < daysVector.size(); i++) {
		    ESRIPolygon _day = ((DayRectangle)daysVector.elementAt(i)).getPolygon();
		    if (getFakePolyFromPoly(_day).contains(me.getPoint())) {
			containedDay = i;
		    }
		}
		repaint();
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	}
    ;

    private void setEnvironment() {
	if ((fWidth != this.getWidth()) || (fHeight != this.getHeight())) {
	    fWidth = this.getWidth();
	    fHeight = this.getHeight();
	    extents = (8 * dayWidth) + monthWidth;
	    drawFactor = (this.getHeight() - 20) / extents;
	    drawFactorOriginal = drawFactor;
	    drawFactor = drawFactorOriginal * drawScale;
	    xOffset = 0;
	    // - 1000;
	    xOffsetOriginal = xOffset;
	    xOffsetPosta = xOffsetOriginal;
	    //Por quÃ©?
	    yOffset = 0;
	    // + 1000;
	    yOffsetOriginal = yOffset;
	    yOffsetPosta = yOffsetOriginal;
	}
    }

    private void setEnvironment(Rectangle2D _bounds) {
	double _width = xtoSpace((int)_bounds.getMaxX()) - xtoSpace((int)_bounds.getMinX());
	double _height = ytoSpace((int)_bounds.getMaxY()) - ytoSpace((int)_bounds.getMinY());
	double _extents = Math.max(_width, _height);
	double minX = xtoSpace((int)_bounds.getMinX());
	double minY = ytoSpace((int)_bounds.getMaxY());
	if (_extents < 0.001) {
	    _extents = 0.001;
	}
	double _drawFactor = (this.getHeight() - 20) / _extents;
	double _drawScale = _drawFactor / drawFactorOriginal;
	flyTo(_drawScale, minX, minY);
    }

    private void flyTo(double _drawScale, double _xOffset, double _yOffset) {
	final double _scale = _drawScale;
	final double _xOff = _xOffset;
	final double _yOff = _yOffset;
	Thread drawScaleThread = new Thread();
	drawScaleThread = new Thread(new Runnable() {

				  public void run() {
				      int sleep = 12;
				      if (drawScale < _scale) {
					  while ((drawScale * scaleFactor) < _scale) {
					      try {
						  drawScale = drawScale * (scaleFactor);
						  drawFactor = drawFactorOriginal * drawScale;
						  repaint();
						  Thread.currentThread().sleep(sleep);
					      } catch (InterruptedException e) {
						  Debug.printStackTrace(e);
					      }
					  }
				      } else {
					  while ((drawScale / scaleFactor) > _scale) {
					      try {
						  drawScale = drawScale / (scaleFactor);
						  drawFactor = drawFactorOriginal * drawScale;
						  repaint();
						  Thread.currentThread().sleep(sleep);
					      } catch (InterruptedException e) {
						  Debug.printStackTrace(e);
					      }
					  }
				      }
				      drawScale = _scale;
				      drawFactor = drawFactorOriginal * drawScale;
				      repaint();
				  }

			      }
	    );
	drawScaleThread.start();
	Thread xOffsetThread = new Thread();
	xOffsetThread = new Thread(new Runnable() {

				public void run() {
				    double xoffsetFactor = (xOffset <= _xOff) ? (_xOff - xOffset) / 2 : (xOffset - _xOff) / 2;
				    int sleep = 12;
				    try {
					if (xOffset <= _xOff) {
					    while (xOffset / _xOff > 1.0005) {
						if ((xOffset + xoffsetFactor) < _xOff) {
						    xOffset += xoffsetFactor;
						} else {
						    xOffset = _xOff;
						}
						xoffsetFactor = (_xOff - xOffset) / 2;
						xOffsetOriginal = xOffset;
						repaint();
						Thread.currentThread().sleep(sleep);
					    }
					} else {
					    while (_xOff / xOffset > 1.0005) {
						if ((xOffset - xoffsetFactor) > _xOff) {
						    xOffset -= xoffsetFactor;
						} else {
						    xOffset = _xOff;
						}
						xoffsetFactor = (xOffset - _xOff) / 2;
						xOffsetOriginal = xOffset;
						repaint();
						Thread.currentThread().sleep(sleep);
					    }
					}
				    } catch (InterruptedException e) {
					Debug.printStackTrace(e);
				    }
				}

			    }
	    );
	xOffsetThread.start();
	Thread yOffsetThread = new Thread();
	yOffsetThread = new Thread(new Runnable() {

				public void run() {
				    double yoffsetFactor = (yOffset <= _yOff) ? (_yOff - yOffset) / 2 : (yOffset - _yOff) / 2;
				    int sleep = 12;
				    try {
					if (yOffset <= _yOff) {
					    while (yOffset / _yOff > 1.0005) {
						if ((yOffset + yoffsetFactor) < _yOff) {
						    yOffset += yoffsetFactor;
						} else {
						    yOffset = _yOff;
						}
						yoffsetFactor = (_yOff - yOffset) / 2;
						yOffsetOriginal = yOffset;
						repaint();
						Thread.currentThread().sleep(sleep);
					    }
					} else {
					    while (_yOff / yOffset > 1.0005) {
						if ((yOffset - yoffsetFactor) > _xOff) {
						    yOffset -= yoffsetFactor;
						} else {
						    yOffset = _yOff;
						}
						yoffsetFactor = (yOffset - _yOff) / 2;
						yOffsetOriginal = yOffset;
						repaint();
						Thread.currentThread().sleep(sleep);
					    }
					}
				    } catch (InterruptedException e) {
					Debug.printStackTrace(e);
				    }
				}

			    }
	    );
	yOffsetThread.start();
    }

    private void restartEnvironment() {
	startDrawRectPosition = null;
	endDrawRectPosition = null;
    }

    private ESRIPolygon getTruePolyFromPoints(ESRIPoint[] _points) {
	int cantidad = _points.length;
	if (cantidad > 0) {
	    double[] xy = new double[cantidad * 2];
	    for (int i = 0; i < cantidad * 2; i += 2) {
		xy[i] = _points[i / 2].getX();
		xy[i + 1] = _points[i / 2].getY();
	    }
	    ESRIPolygon poly = new ESRIPolygon.Double(xy);
	    return poly;
	} else {
	    return new ESRIPolygon.Double(0, 0);
	}
    }

    private void createDrawRectangle() {
	if (startDrawRectPosition != null) {
	    int drawRectWidth = (int)(endDrawRectPosition.getX() - startDrawRectPosition.getX());
	    int drawRectHeight = (int)(endDrawRectPosition.getY() - startDrawRectPosition.getY());
	    int drawRectX = (int)startDrawRectPosition.getX();
	    int drawRectY = (int)startDrawRectPosition.getY();
	    if (drawRectWidth < 0) {
		drawRectWidth *= -1;
		drawRectX -= drawRectWidth;
		if (drawRectX < 0) {
		    drawRectWidth += drawRectX;
		    drawRectX = 0;
		}
	    }
	    if (drawRectHeight < 0) {
		drawRectHeight *= -1;
		drawRectY -= drawRectHeight;
		if (drawRectY < 0) {
		    drawRectHeight += drawRectY;
		    drawRectY = 0;
		}
	    }
	    drawRectangle = new Rectangle(drawRectX, drawRectY, drawRectWidth, drawRectHeight);
	}
    }

    private void drawCalendar(Graphics2D _graphics2d) {
	int border = 2;
	ESRIPoint[] boundsPoints = new ESRIPoint[4];
	boundsPoints[0] = new ESRIPoint(border, border);
	boundsPoints[1] = new ESRIPoint(getWidth() - border, border);
	boundsPoints[2] = new ESRIPoint(getWidth() - border, getHeight() - border);
	boundsPoints[3] = new ESRIPoint(border, getHeight() - border);
	ESRIPolygon boundsSpace = getTruePolyFromPoints(boundsPoints);
	Font _dayF = new Font("Courier", Font.PLAIN, (int)(11 * drawScale));
	_graphics2d.setFont(_dayF);
	FontMetrics _dayFM = _graphics2d.getFontMetrics();
	for (int i = 0; i < daysVector.size(); i++) {
	    boolean _filled = false;
	    DayRectangle _dayRect = (DayRectangle)daysVector.elementAt(i);
	    boolean _dark = (_dayRect.getTomonth() % 2 == 0);
	    ESRIPolygon _polygon = getFakePolyFromPoly(_dayRect.getPolygon());
	    if (boundsSpace.intersects(_polygon.getBounds())) {
		if (_dayRect.isToday()) {
		    _graphics2d.setColor(Color.BLUE);
		    _graphics2d.fill(_polygon);
		    _filled = true;
		}
		if (_dayRect.isHoliday()) {
		    _graphics2d.setColor(Color.ORANGE);
		    _graphics2d.fill(_polygon);
		}
		if (_dayRect.getDayOfWeek() == Calendar.SUNDAY) {
		    _graphics2d.setColor(_dark ? (new Color(255, 0, 0)) : (new Color(220, 0, 0)));
		    _graphics2d.fill(_polygon);
		    _filled = true;
		}
		if (!_filled) {
		    _graphics2d.setColor(_dark ? (new Color(255, 255, 255)) : (new Color(220, 220, 220)));
		    _graphics2d.fill(_polygon);
		}
		if (containedDay == i) {
		    _graphics2d.setColor(Color.MAGENTA);
		    _graphics2d.fill(_polygon);
		}
		_graphics2d.setColor(Color.BLACK);
		_graphics2d.draw(_polygon);
		String _dayString = String.valueOf(_dayRect.getDay());
		int _strWidth = _dayFM.stringWidth(_dayString);
		int _strHeight = _dayFM.getHeight();
		_graphics2d.drawString(_dayString, xtoPixel(_dayRect.getPolygon().getBounds2D().getCenterX()) - (_strWidth / 2), ytoPixel(_dayRect.getPolygon().getBounds2D().getCenterY()) + (_strHeight / 2));
	    }
	}
	Font _weekF = new Font("Courier", Font.PLAIN, (int)(10 * drawScale));
	_graphics2d.setFont(_weekF);
	FontMetrics _weekFM = _graphics2d.getFontMetrics();
	for (int i = 0; i < weeksVector.size(); i++) {
	    WeekRectangle _weekRect = (WeekRectangle)weeksVector.elementAt(i);
	    ESRIPolygon _polygon = getFakePolyFromPoly(_weekRect.getPolygon());
	    if (boundsSpace.intersects(_polygon.getBounds())) {
		_graphics2d.setColor(Color.CYAN);
		_graphics2d.fill(_polygon);
		_graphics2d.setColor(Color.BLACK);
		_graphics2d.draw(_polygon);
		String _weekString = String.valueOf(_weekRect.getWeekOfYear());
		int _strWidth = _weekFM.stringWidth(_weekString);
		int _strHeight = _weekFM.getHeight();
		_graphics2d.drawString(_weekString, xtoPixel(_weekRect.getPolygon().getBounds2D().getCenterX()) - (_strWidth / 2), ytoPixel(_weekRect.getPolygon().getBounds2D().getCenterY()) + (_strHeight / 2));
	    }
	}
	//
	/*Font _monthF = new Font("Courier", Font.BOLD, (int)(14*drawScale));
	_graphics2d.setFont(_monthF);
	FontMetrics _monthFM = _graphics2d.getFontMetrics();
	// clockwise 90 degrees
	AffineTransform at = new AffineTransform();
	AffineTransform at2 = new AffineTransform();
	at.setToRotation(Math.PI / 2.0, fWidth / 2.0, fHeight / 2.0);
	at2.setToRotation(0, fWidth / 2.0, fHeight / 2.0);
	//
	for (int i = 0; i < monthsVector.size(); i++) {
	    MonthRectangle _monthRect = ((MonthRectangle)monthsVector.elementAt(i));
	    //ESRIPolygon _polygon = getFakePolyFromPoly(_monthRect.getPolygon());
	    //_graphics2d.setColor(Color.CYAN);
	    //_graphics2d.draw(_polygon);
	    _graphics2d.setColor(Color.BLACK);
	    String _monthString = _monthRect.getMonthString() + " -" + _monthRect.getToyear();
	    int _strWidth = _monthFM.stringWidth(_monthString);
	    int _strHeight = _monthFM.getHeight();
	    _graphics2d.setTransform(at);

	     //xtoPixel(_dayRect.getPolygon().getBounds2D().getCenterX()) - (_strWidth / 2)
	     //ytoPixel(_dayRect.getPolygon().getBounds2D().getCenterY()) + (_strHeight / 2));
	      int x = ytoPixel(_monthRect.getPolygon().getBounds2D().getMaxY()) + _strHeight / 2;
	      int y = fHeight - (xtoPixel(_monthRect.getPolygon().getBounds2D().getCenterX())) - (_strWidth / 2);
	    //int x = (ytoPixel(_monthRect.getPolygon().getBounds2D().getMaxY()) + _strWidth / 2);
	    //int y = fHeight - (xtoPixel(_monthRect.getPolygon().getBounds2D().getCenterX())) - (_strHeight / 2);
	    _graphics2d.drawString(_monthString, x, y);
	    _graphics2d.setTransform(at2);
	}*/
	//
	Font _monthF = new Font("Courier", Font.BOLD, (int)(14 * drawScale));
	_graphics2d.setFont(_monthF);
	FontMetrics _monthFM = _graphics2d.getFontMetrics();
	for (int i = 0; i < monthsVector.size(); i++) {
	    MonthRectangle _monthRect = ((MonthRectangle)monthsVector.elementAt(i));
	    ESRIPolygon _polygon = getFakePolyFromPoly(_monthRect.getPolygon());
	    _graphics2d.setColor(Color.CYAN);
	    _graphics2d.draw(_polygon);
	    _graphics2d.setColor(Color.BLACK);
	    String _monthString = _monthRect.getMonthString() + " - " + _monthRect.getToyear();
	    int _strWidth = _monthFM.stringWidth(_monthString);
	    int _strHeight = _monthFM.getHeight();
	    _graphics2d.drawString(_monthString, xtoPixel(_monthRect.getPolygon().getBounds2D().getCenterX()) - (_strWidth / 2), ytoPixel(_monthRect.getPolygon().getBounds2D().getMaxY()) + _strHeight);
	}
	Font _weekDayF = new Font("Courier", Font.BOLD, (int)(12 * drawScale));
	_graphics2d.setFont(_weekDayF);
	FontMetrics _weekDayFM = _graphics2d.getFontMetrics();
	//
	for (int i = 0; i < weekDaysVector.size(); i++) {
	    WeekDayRectangle _weekRect = ((WeekDayRectangle)weekDaysVector.elementAt(i));
	    /*ESRIPolygon _polygon = getFakePolyFromPoly(_weekRect.getPolygon());
	    _graphics2d.setColor(Color.ORANGE);
	    _graphics2d.draw(_polygon);*/
	    _graphics2d.setColor(Color.BLACK);
	    int _strWidth = _weekDayFM.stringWidth(_weekRect.getDayShortString());
	    int _strHeight = _weekDayFM.getHeight();
	    _graphics2d.drawString(_weekRect.getDayShortString(), xtoPixel(_weekRect.getPolygon().getBounds2D().getCenterX()) - (_strWidth / 2), ytoPixel(_weekRect.getPolygon().getBounds2D().getMaxY()));
	}
	_graphics2d.setColor(Color.ORANGE);
	_graphics2d.draw(getFakePolyFromPoly(limitBounds));
    }

    public void setDays3() {
	//Help:
	//calendar.set(int field, int value)
	//calendar.set(int year, int month, int date)
	//calendar.set(int year, int month, int date, int hour, int minute)
	//calendar.set(int year, int month, int date, int hour, int minute, int second)
	setEnvironment();
	int dayX = dayWidth;
	int dayY = 0;
	int dayOffset = 0;
	int monthOffset = 10;
	int weekOffset = 10;
	Rectangle _limitBounds = new Rectangle();
	ResultSet timeRecord = LibSQL.exQuery("select now() as now, extract(timezone_hour from now()) as timezone_hour");
	try {
	    if (timeRecord.next()) {
		calendar.setTimeInMillis(timeRecord.getDate("now").getTime() + timeRecord.getTime("now").getTime() + (timeRecord.getInt("timezone_hour") * (60 * 60 * 1000)));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	int toDay = calendar.get(Calendar.DAY_OF_MONTH);
	int toMonth = calendar.get(Calendar.MONTH);
	int toYear = calendar.get(Calendar.YEAR);
	int toHour = calendar.get(Calendar.HOUR);
	int toMinute = calendar.get(Calendar.MINUTE);
	int toSecond = calendar.get(Calendar.SECOND);
	int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);

	GregorianCalendar startDate = new GregorianCalendar();
	startDate.set(Calendar.YEAR, toYear);
	startDate.set(Calendar.MONTH, Calendar.JANUARY);
	startDate.set(Calendar.DAY_OF_MONTH, 1);
	GregorianCalendar endDate = new GregorianCalendar();
	endDate.set(Calendar.YEAR, toYear);
	endDate.set(Calendar.MONTH, Calendar.DECEMBER);
	endDate.set(Calendar.DAY_OF_MONTH, 31);

	//calendar.set();
	calendar.setTimeInMillis(startDate.getTimeInMillis());
	int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
	int months = 0;
	int monthWeeks = 0;
	int totalWeeks = 0;
	int monthX = dayX;
	int monthY = dayY;
	int monthWidth = 0;
	int monthHeight = 0;
	int weekDays = 0;
	int i = 0;
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.SUNDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.MONDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.TUESDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.WEDNESDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.THURSDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.FRIDAY));
	weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.SATURDAY));
	do {
	    //dibujar los días de la semana (sun,mon,tue,wed,thu,fri,sat)
	    int _year = calendar.get(Calendar.YEAR);
	    int _month = calendar.get(Calendar.MONTH);
	    int actualMonth = _month;
	    for (int j = Calendar.SUNDAY; j < weekDay; j++) {
		//drawEmptyDay();
		//days.add(new Rectangle(dayX, dayY, dayWidth, dayHeight));
		//_graphics2d.draw(new Rectangle(dayX, dayY, dayWidth, dayHeight));
		dayX += dayWidth + dayOffset;
	    }
	    do {
		int _day = calendar.get(Calendar.DAY_OF_MONTH);
		DayRectangle _dayRectangle = new DayRectangle(getTruePolyFromRect(new Rectangle(dayX, dayY, dayWidth, dayHeight)), _day);
		if (_day == toDay && _month == toMonth && _year == toYear) {
		    //es el dÃ­a actual, pintar de otro color
		    _dayRectangle.setToday();
		} else {
		    //dÃ­a normal
		}
		if (weekDay == Calendar.SATURDAY) {
		    //imprimir linea en blanco o pasar a la siguiente lÃ­nea/semana
		    dayY -= (dayHeight + dayOffset);
		    dayX = dayWidth;
		    monthWeeks++;
		    WeekRectangle _weekRectangle = new WeekRectangle(getTruePolyFromRect(new Rectangle(0, dayY + dayHeight, dayWidth, dayHeight)), calendar.get(Calendar.WEEK_OF_YEAR));
		    _weekRectangle.setWeekOfMonth(calendar.get(Calendar.WEEK_OF_MONTH));
		    weeksVector.add(_weekRectangle);
		} else {
		    dayX += dayWidth + dayOffset;
		}
		_dayRectangle.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		_dayRectangle.setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
		_dayRectangle.setTomonth(calendar.get(Calendar.MONTH));
		_dayRectangle.setToyear(calendar.get(Calendar.YEAR));
		_dayRectangle.setWeekOfYear(calendar.get(Calendar.WEEK_OF_YEAR));
		daysVector.add(_dayRectangle);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		_month = calendar.get(Calendar.MONTH);
	    } while (_month == actualMonth);
	    months++;
	    totalWeeks += monthWeeks;
	    //dibujar el mes
	    MonthRectangle _monthRectangle = new MonthRectangle(getTruePolyFromRect(new Rectangle(dayWidth * 8, dayY + dayHeight, dayWidth * 7, dayHeight * monthWeeks)), actualMonth);
	    _monthRectangle.setToyear(_year);
	    monthsVector.add(_monthRectangle);
	    //con esto separo los meses
	    // dayY -= (dayHeight + dayOffset);
	    actualMonth = _month;
	    dayX = dayWidth;
	    monthWeeks = 0;
	    //dayY = fHeight;
	    weekDays = 0;
	} while (calendar.before(endDate));
	totalWeeks++;
	WeekRectangle _weekRectangle = new WeekRectangle(getTruePolyFromRect(new Rectangle(0, dayY, dayWidth, dayHeight)), calendar.get(Calendar.WEEK_OF_YEAR));
	_weekRectangle.setWeekOfMonth(calendar.get(Calendar.WEEK_OF_MONTH));
	weeksVector.add(_weekRectangle);
	_limitBounds.setBounds(0, -(dayHeight * (totalWeeks - 1)), dayWidth * 8, dayHeight * totalWeeks);
	if (weekDay != Calendar.SUNDAY) {
	    //println() ???
	}
	limitBounds = getTruePolyFromRect(_limitBounds);
    }

    public void setDays2() {
	//Help:
	//calendar.set(int field, int value)
	//calendar.set(int year, int month, int date)
	//calendar.set(int year, int month, int date, int hour, int minute)
	//calendar.set(int year, int month, int date, int hour, int minute, int second)
	GregorianCalendar startDate = new GregorianCalendar();
	startDate.set(Calendar.YEAR, 2007);
	startDate.set(Calendar.MONTH, Calendar.JULY);
	startDate.set(Calendar.DAY_OF_MONTH, 1);
	GregorianCalendar endDate = new GregorianCalendar();
	endDate.set(Calendar.YEAR, 2008);
	endDate.set(Calendar.MONTH, Calendar.JANUARY);
	endDate.set(Calendar.DAY_OF_MONTH, 1);
	setEnvironment();
	int dayWidth = 24;
	int dayHeight = 24;
	int dayX = 0;
	int dayY = fHeight;
	int dayOffset = 0;
	int monthOffset = 10;
	int weekOffset = 10;
	GregorianCalendar calendar = new GregorianCalendar();
	ResultSet timeRecord = LibSQL.exQuery("select now() as now, extract(timezone_hour from now()) as timezone_hour");
	try {
	    if (timeRecord.next()) {
		calendar.setTimeInMillis(timeRecord.getDate("now").getTime() + timeRecord.getTime("now").getTime() + (timeRecord.getInt("timezone_hour") * (60 * 60 * 1000)));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	int toDay = calendar.get(Calendar.DAY_OF_MONTH);
	int toMonth = calendar.get(Calendar.MONTH);
	int toYear = calendar.get(Calendar.YEAR);
	int toHour = calendar.get(Calendar.HOUR);
	int toMinute = calendar.get(Calendar.MINUTE);
	int toSecond = calendar.get(Calendar.SECOND);
	int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
	//calendar.set();
	calendar.setTimeInMillis(startDate.getTimeInMillis());
	int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
	int months = 0;
	int weeks = 0;
	int monthX = dayX;
	int monthY = dayY;
	int monthWidth = 0;
	int monthHeight = 0;
	int weekDays = 0;
	do {
	    //dibujar los días de la semana (sun,mon,tue,wed,thu,fri,sat)
	    int i = 0;
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.SUNDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.MONDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.TUESDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.WEDNESDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.THURSDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.FRIDAY));
	    weekDaysVector.add(new WeekDayRectangle(getTruePolyFromRect(new Rectangle(dayX + (dayWidth * i++), dayY - (dayHeight * 5), dayWidth, dayHeight * (7))), Calendar.SATURDAY));
	    int _year = calendar.get(Calendar.YEAR);
	    int _month = calendar.get(Calendar.MONTH);
	    int actualMonth = _month;
	    //dibujar el mes
	    monthsVector.add(new MonthRectangle(getTruePolyFromRect(new Rectangle(dayX - (monthOffset / 2), (dayY - dayHeight * 5) - (monthOffset / 2), (dayWidth * 7) + monthOffset, (dayHeight * (9)))), actualMonth));
	    for (int j = Calendar.SUNDAY; j < weekDay; j++) {
		//drawEmptyDay();
		//days.add(new Rectangle(dayX, dayY, dayWidth, dayHeight));
		//_graphics2d.draw(new Rectangle(dayX, dayY, dayWidth, dayHeight));
		dayX += dayWidth + dayOffset;
	    }
	    do {
		int _day = calendar.get(Calendar.DAY_OF_MONTH);
		DayRectangle _dayRect = new DayRectangle(getTruePolyFromRect(new Rectangle(dayX, dayY, dayWidth, dayHeight)), _day);
		if (_day == toDay && _month == toMonth && _year == toYear) {
		    //es el dÃ­a actual, pintar de otro color
		    _dayRect.setToday();
		} else {
		    //dÃ­a normal
		}
		if (weekDay == Calendar.SATURDAY) {
		    //imprimir linea en blanco o pasar a la siguiente lÃ­nea/semana
		    dayY -= (dayHeight + dayOffset);
		    dayX = 24 * 7 * months + (monthOffset * months);
		    weeks++;
		} else {
		    dayX += dayWidth + dayOffset;
		}
		_dayRect.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		_dayRect.setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
		_dayRect.setTomonth(calendar.get(Calendar.MONTH));
		_dayRect.setToyear(calendar.get(Calendar.YEAR));
		_dayRect.setWeekOfYear(calendar.get(Calendar.WEEK_OF_YEAR));
		daysVector.add(_dayRect);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		_month = calendar.get(Calendar.MONTH);
	    } while (_month == actualMonth);
	    months++;
	    actualMonth = _month;
	    dayX = 24 * 7 * months + (monthOffset * months);
	    weeks = 0;
	    dayY = fHeight;
	    weekDays = 0;
	} while (calendar.before(endDate));
	if (weekDay != Calendar.SUNDAY) {
	    //println() ???
	}
    }

    public void setDays() {
	setEnvironment();
	int dayWidth = 24;
	int dayHeight = 24;
	int dayX = 0;
	int dayY = fHeight;
	double dayOffset = 0d;
	GregorianCalendar calendar = new GregorianCalendar();
	ResultSet timeRecord = LibSQL.exQuery("select now() as now, extract(timezone_hour from now()) as timezone_hour");
	try {
	    if (timeRecord.next()) {
		calendar.setTimeInMillis(timeRecord.getDate("now").getTime() + timeRecord.getTime("now").getTime() + (timeRecord.getInt("timezone_hour") * (60 * 60 * 1000)));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	int toDay = calendar.get(Calendar.DAY_OF_MONTH);
	int toMonth = calendar.get(Calendar.MONTH);
	int toYear = calendar.get(Calendar.YEAR);
	int toHour = calendar.get(Calendar.HOUR);
	int toMinute = calendar.get(Calendar.MINUTE);
	int toSecond = calendar.get(Calendar.SECOND);
	int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
	//calendar.set();
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
	for (int i = Calendar.SUNDAY; i < weekDay; i++) {
	    //drawEmptyDay();
	    //days.add(new Rectangle(dayX, dayY, dayWidth, dayHeight));
	    //_graphics2d.draw(new Rectangle(dayX, dayY, dayWidth, dayHeight));
	    dayX += dayWidth + dayOffset;
	}
	do {
	    int day = calendar.get(Calendar.DAY_OF_MONTH);
	    daysVector.add(new DayRectangle(getTruePolyFromRect(new Rectangle(dayX, dayY, dayWidth, dayHeight)), day));
	    if (day == toDay) {
		//es el dÃ­a actual, pintar de otro color
		Debug.println(getTruePolyFromRect(new Rectangle(dayX, dayY, dayWidth, dayHeight)).getBounds().getMinX());
		Debug.println(getTruePolyFromRect(new Rectangle(dayX, dayY, dayWidth, dayHeight)).getBounds().getMaxX());
		//_graphics2d.draw(new Rectangle(dayX, dayY, dayWidth, dayHeight));
	    } else {
		//dÃ­a normal
		//_graphics2d.draw(new Rectangle(dayX, dayY, dayWidth, dayHeight));
	    }
	    if (weekDay == Calendar.SATURDAY) {
		//imprimir linea en blanco o pasar a la siguiente lÃ­nea/semana
		dayY -= (dayHeight + dayOffset);
		dayX = 0;
	    } else {
		dayX += dayWidth + dayOffset;
	    }
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    weekDay = calendar.get(Calendar.DAY_OF_WEEK);
	} while (calendar.get(Calendar.MONTH) == toMonth);
	if (weekDay != Calendar.SUNDAY) {
	    //println() ???
	}
    }

    private ESRIPolygon getTruePolyFromRect(Rectangle _rect) {
	int cantidad = 4;
	double[] xy = new double[cantidad * 2];
	xy[0] = _rect.getMinX();
	xy[1] = _rect.getMinY();
	xy[2] = _rect.getMaxX();
	xy[3] = _rect.getMinY();
	xy[4] = _rect.getMaxX();
	xy[5] = _rect.getMaxY();
	xy[6] = _rect.getMinX();
	xy[7] = _rect.getMaxY();
	ESRIPolygon poly = new ESRIPolygon.Double(xy);
	return poly;
    }

    private void chkExpired_actionPerformed(ActionEvent e) {

    }

    private void chkCustom_actionPerformed(ActionEvent e) {

    }

    private void chkDanger_actionPerformed(ActionEvent e) {

    }

    private void chkOk_actionPerformed(ActionEvent e) {

    }

    private void chkTotal_actionPerformed(ActionEvent e) {

    }

    private void chkWarned_actionPerformed(ActionEvent e) {

    }

}
