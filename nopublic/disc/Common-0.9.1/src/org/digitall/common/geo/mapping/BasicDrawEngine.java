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
 * BasicDrawEngine.java
 *
 * */
package org.digitall.common.geo.mapping;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;

import java.beans.PropertyVetoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.sql.Types;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.common.geo.mapping.components.LayerEditionPanel;
import org.digitall.common.geo.mapping.panels.ReportConfigPanel;
import org.digitall.common.geo.mapping.panels.StrokeSamples;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.esri.ESRIPolyline;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.ImageAttachment;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.OsnapPoints;
import org.digitall.lib.geo.mapping.classes.StyleConfig;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.geom.Polygon2D;
import org.digitall.lib.ssl.MD5;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/** Motor Básico de Dibujo en 2D
 * Capaz de dibujar objetos de las siguientes clases
 * ESRIPoint
 * ESRIPolyline
 * ESRIPolygon
 * */
public class BasicDrawEngine extends BasicPrimitivePanel {

    private BasicLabel statusBar;
    private String operationStatus;
    //private BasicButton bpoligono = new BasicButton();
    //private BasicButton bZoom = new BasicButton();
    private BasicLabel labelxy = new BasicLabel();
    private BasicLabel labelinfo = new BasicLabel();
    //Constantes de operacion
    private int currentOperation = -1;
    //Variables de Posicion
    private Point mousePosition = new Point();
    private Point2D.Double currentPosition = new Point2D.Double();

    private Point startDragPosition = null;
    private Point imageStartDragPosition = null;
    private String testString = "";
    //Variables de Dibujo
    private Vector<Point2D.Double> drawingPointsVector = new Vector<Point2D.Double>();
    private OsnapPoints osnapPointsVector = new OsnapPoints();
    //Servira de algo?
    private RectangularShape osnapRectangle;
    private int containedShapeIDS[][] = new int[0][0];
    protected Line2D panLine = new Line2D.Float();
    private Point2D.Double startDrawRectPosition;
    private Point2D.Double endDrawRectPosition;

    private Point2D.Double multiQueryStartDrawRectPosition;
    private Point2D.Double multiQueryEndDrawRectPosition;
    private Rectangle2D multiQueryRectangle;

    private Rectangle2D zoomRectangle;
    private double scaleFactor = 1.05d;
    //Variables del momento
    //private double base = 1000.0;
    //1000 metros
    //private double altura = 1000.0;
    //1000 metros
    //private double area = base * altura;
    //100 has ??? :=0 !!!
    //private double areaParcial = area;
    //Variables de trabajo
    private MouseListener eraseListener;
    private MouseMotionListener eraseMotionListener;
    //private MouseWheelListener eraseWheelListener;
    private int osnapRectSize = 8;
    private Vector<Layer> layers = new Vector<Layer>();
    private Vector<LayerGroup> layerGroups = new Vector<LayerGroup>();
    private Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
    private CoordinateViewer coordinateViewer;
    private RuleViewer ruleViewer;
    private BasicLabel label = new BasicLabel(" ");
    private JWindow toolTip = new JWindow(new Frame());
    private int selectedGeometryIndex = -1;
    private int foundGeometryID = -1;
    private String foundLayer = "";
    private static long lastShowingTime = System.currentTimeMillis();
    private static long lastActivityTime = System.currentTimeMillis();
    private static int activityTimeout = 1;
    private static Timer hideToolTipTimer = null;
    private static Timer infoTimer = null;
    private static Timer containedShapesTimer = null;
    private static Timer activityTimer = null;
    private Point2D[] mapExtents = new Point2D[0];
    /* 3 segundos para ocultar la ventana de tooltip */
    public static int timeout = 3;
    private ConfigFile cfg = new ConfigFile("ddesktop.conf");
    private BasicDrawEngineConfig engineConfig = new BasicDrawEngineConfig();
    private ImageAttachment selectedImageAttachment = null;
    double osnapTolerance = 5;
    private boolean isPainting = false;
    private Layer selectedLayer = null;
    private MapImage rasterImage = null;
    private Vector<MapImage> rasterImageMatrix2 = new Vector<MapImage>();
    private Graphics2D labelGraphics2D = null;
    private int zoomLevel = 1;
    private Thread _xThread = null;
    private boolean mouseActive = false;
    private boolean rasterImageMatrixMode = false;
    private boolean needRepaint = false;

    private static final int OPERATION_SET_BOTTOM_LEFT_COMPONENT_ON_OFF = KeyEvent.VK_LEFT;
    private static final int OPERATION_SET_TOP_LEFT_COMPONENT_ON_OFF = KeyEvent.VK_UP;
    private static final int OPERATION_SET_TOP_RIGHT_COMPONENT_ON_OFF = KeyEvent.VK_RIGHT;
    private static final int OPERATION_SET_BOTTOM_RIGHT_COMPONENT_ON_OFF = KeyEvent.VK_DOWN;
    private Component bottomRightComponent = null;
    private Component bottomLeftComponent = null;
    private Component topRightComponent = null;
    private Component topLeftComponent = null;

    private double orthoX = -1;
    private double orthoY = -1;
    
    public BasicDrawEngine(String _mapName, BasicLabel _statusBar) {
	fetchEngineConfigFromCache(_mapName);
	if (!engineConfig.isPropietaryDataSet()) {
	    configurePropietaryData();
	}
        engineConfig.loadSymbolsVector();
	setIgnoreRepaint(true);
	try {
	    statusBar = _statusBar;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        activityTimer = new Timer(1000, new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            if ((System.currentTimeMillis() - lastActivityTime) > (activityTimeout * 1000)) {
                                setMouseActive(false);
                            }
                        }

                    });

	hideToolTipTimer = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    if ((System.currentTimeMillis() - lastShowingTime) > (timeout * 1000)) {
				showToolTip(false);
				hideToolTipTimer.stop();
			    }
			}

		    });

	containedShapesTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int i = geometrySets.size() - 1;
			    containedShapeIDS = new int[geometrySets.size()][0];
			    StringBuilder _labelText = new StringBuilder();
			    _labelText.append("<html>");
			    while (i >= 0) {
			        containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(engineConfig.xtoSpace((int)mousePosition.getX()), engineConfig.ytoSpace((int)mousePosition.getY()));
			        i--;
			    }
			    repaint();
			}
	});

	infoTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int i = geometrySets.size() - 1;
			    containedShapeIDS = new int[geometrySets.size()][0];
			    boolean found = false;
			    StringBuilder _labelText = new StringBuilder();
			    _labelText.append("<html>");
			    while (i >= 0) {
			        containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(engineConfig.xtoSpace((int)mousePosition.getX()), engineConfig.ytoSpace((int)mousePosition.getY()));
				for (int k = geometrySets.elementAt(i).getLayers().size() - 1; k >= 0; k--) {
				    Layer _layer = geometrySets.elementAt(i).getLayers().elementAt(k);
				    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
					if (_layer.isQueryable()) {
					    for (int j = 0; j < containedShapeIDS[i].length; j++)  {
						if (containedShapeIDS[i][j] != -1 && _layer.getToolTipText() != null) {
						    toolTip.getContentPane().setLayout(new BorderLayout());
						    toolTip.getContentPane().add(label, BorderLayout.CENTER);
						    _labelText.append(_layer.getToolTipText(containedShapeIDS[i][j]).toString());
						    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
						    label.setFont(new Font("Arial", Font.PLAIN, 12));
						    found = true;
						} else {
						}
					    }
					}
				    }
				}
				i--;
			    }
			    _labelText.append("</html>");
			    label.setText(_labelText.toString());
			    toolTip.pack();
			    showToolTip(found);
			}

		    });
	//this.setLayout(null);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	/**mPoints = new Point2D[2];*/
	//        mousePosition.setLocation(0, 0);
	//        currentPosition.setLocation(0, 0);
	//this.setSize(new Dimension(800, 600));
	//labelxy.setText("labelxy");
	labelxy.setForeground(Color.black);
	//labelxy.setBounds(new Rectangle(15, 20, 515, 20));
	//labelinfo.setText("labelinfo");
	//labelinfo.setForeground(Color.BLACK);
	//labelinfo.setBackground(Color.ORANGE);
	//labelinfo.setOpaque(true);
	//labelinfo.setVisible(false);
	//labelinfo.setBounds(new Rectangle(0, 0, this.getWidth(), 20));
	label.setBackground(Color.WHITE);
	label.setForeground(Color.BLACK);
	/*bpoligono.setText("Poligono");
	bZoom.setText("Zoom");
	bpoligono.setBounds(new Rectangle(695, 65, 90, 25));
	bZoom.setBounds(new Rectangle(695, 95, 90, 25));
	bpoligono.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     bpoligono_actionPerformed(e);
				 }

			     }
	);
	bZoom.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 bZoom_actionPerformed(e);
			     }

			 }
	);
	//this.add(bpoligono, null);
	//this.add(bZoom, null);
	*/
	//add(labelinfo, BorderLayout.NORTH);
	add(labelxy, BorderLayout.SOUTH);
	addMouseWheelListener(commonWheelListener);
	addMouseListener(commonMouseListener);
	addMouseMotionListener(commonMotionListener);
	createKeyBindings();
    }

    /*private void createBackBuffer() {
	GraphicsConfiguration gc = getGraphicsConfiguration();
	volatileImg = gc.createCompatibleVolatileImage(getWidth(), getHeight());
    }*/

    public void paintComponent(Graphics _graphics) {
	//requestFocus();
	long startTime = System.currentTimeMillis();
	//createBackBuffer();
//  do {
	//isPainting = false;

	    /*GraphicsConfiguration gc = this.getGraphicsConfiguration();
	    int valCode = volatileImg.validate(gc);

	    if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
	     createBackBuffer(); // recreate the hardware accelerated image.
	    }*/
	    

	if (!isPainting) {
	    //super.paint(_graphics);
	    isPainting = true;
	    osnapTolerance = 10;
	    
	    Graphics2D _graphics2D = (Graphics2D)_graphics;
	    //Graphics2D _graphics2D = volatileImg.createGraphics();

	    //RasterImage labelImage = new RasterImage(getBounds().width, getBounds().height, RasterImage.TYPE_4BYTE_ABGR);
	    //_labelGraphics2D = labelImage.createGraphics();
	    labelGraphics2D = (Graphics2D)_graphics2D.create();
	    setEnvironment();
	    osnapPointsVector.removeAllElements();
	    //Recorro el vector layer para dibujar uno por uno
	    //en el orden en que fueron agregados al vector
	    _graphics2D.setColor(Color.WHITE);
	    _graphics2D.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

	    if (rasterImageMatrixMode) {
	        if (rasterImageMatrix2.size() > 0) {
	            for (int i = 0; i < rasterImageMatrix2.size(); i++)  {
	                MapImage _image = rasterImageMatrix2.elementAt(i);
	                if (_image != null) {
	                    if (_image.isLoaded()) {
	                        double _scale = rasterImage.getWidth()/(engineConfig.xtoSpace(engineConfig.getFWidth())-engineConfig.xtoSpace(0));
	                        Rectangle _kk = new Rectangle(engineConfig.xtoPixel(_image.getX()), engineConfig.ytoPixel(_image.getY()), (int)(_image.getBounds().getWidth()*_scale), (int)(_image.getBounds().getHeight()*_scale));
	                        if (_kk.intersects(getBounds())) {
	                            if (i == 0) {
	                                //setAntiAlias(_graphics2D);
	                                _graphics2D.drawImage(_image, engineConfig.xtoPixel(_image.getX()), engineConfig.ytoPixel(_image.getY()), (int)(_image.getBounds().getWidth()*_scale), (int)(_image.getBounds().getHeight()*_scale), this);
	                                //unsetAntiAlias(_graphics2D);
	                            } else {
	                                _graphics2D.drawImage(_image, engineConfig.xtoPixel(_image.getX()), engineConfig.ytoPixel(_image.getY()), (int)(_image.getBounds().getWidth()*_scale), (int)(_image.getBounds().getHeight()*_scale), this);
	                            }
	                        }
	                        //System.out.println(engineConfig.xtoPixel(_image.getX()));
	                        //System.out.println(engineConfig.xtoPixel(_image.getY()));
	                        //System.out.println(engineConfig.xtoPixel(_image.getWidth()));
	                        //System.out.println(engineConfig.xtoPixel(_image.getHeight()));
	                    }
	                }
	            }
	        } else {
	            try {
	                drawRasterImageVector();
	            } catch (Exception x) {
	                x.printStackTrace();
	            }
	        }
	    } else {
	        drawLayers(_graphics2D, labelGraphics2D, engineConfig);
	        //evaluarRendimiento(System.currentTimeMillis()-startTime);
	    }


	    _graphics2D.setColor(Color.yellow);
	    _graphics2D.setStroke(new BasicStroke(1));
	    _graphics2D.draw(panLine);
	    //Dibujo el texto del puntero del mouse
	    if (currentPosition != null) {
		//ESTAS DOS LINEAS CLAVAN EL PROGRAMA!!!
		//LatLongCoord latlon = CoordinateSystems.gk2geo(currentPosition.getX(), currentPosition.getY(), faja);
		//String latlong = CoordinateSystems.dec2gms(latlon.getLatitude(), 4) + " lat " + CoordinateSystems.dec2gms(latlon.getLongitude(), 4) + " long";
		//POR ESO ESTÁN COMENTADAS
		if (engineConfig.paintCoordinates()) {
		    _graphics2D.setColor(Color.black);
		    Font thisFont = new Font("Arial", Font.PLAIN, 12);
		    _graphics2D.setFont(thisFont);
		    _graphics2D.drawString("(" + decimalFormat(currentPosition.getX(), 4) + " " + decimalFormat(currentPosition.getY(), 4) + ")", (int)mousePosition.getX(), (int)mousePosition.getY());
		}
	    }
	    //Dibujo el texto del puntero del mouse
	    if (startDragPosition != null) {
		_graphics2D.setColor(Color.blue);
		_graphics2D.drawString(testString, 200, 200);
	    }
	    //Dibujo el Rectángulo del osnap
	    if (osnapRectangle != null) {
		_graphics2D.setColor(Color.cyan);
		_graphics2D.fill(osnapRectangle);
	    }
	    
	    
	    _graphics2D.setColor(Color.black);
	    _graphics2D.setStroke(StrokeSamples.strokeSamples[1].getStroke());
	    if (orthoX != -1) {
	        _graphics2D.drawLine(engineConfig.xtoPixel(orthoX), 0, engineConfig.xtoPixel(orthoX), getHeight());
	    }

	    if (orthoY != -1) {
	        _graphics2D.drawLine(0, engineConfig.ytoPixel(orthoY), getWidth(), engineConfig.ytoPixel(orthoY));
	    }
	    
	    /*//Grid para SNAP to Grid
	    double _gridGap = 10; //10 metros
	    int _horizontalDotsQty = (int)Math.abs( engineConfig.getExtents() / _gridGap );
	    _graphics2D.setColor(Color.BLACK);
	    System.out.println(_horizontalDotsQty);
	    if (false) { //_horizontalDotsQty < 2500) {
		for (int i = 0; i <= _horizontalDotsQty; i++) {
		    double _x = mapExtents[0].getX() + (_gridGap*i);
		    for (int j = 0; j <= _horizontalDotsQty; j++) {
		        double _y = mapExtents[0].getY() + (_gridGap*j);
		        Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(_x), engineConfig.ytoPixel(_y), 1, 1);
		        _graphics2D.draw(point);
		    }
		}
	    }*/

	    if (engineConfig.isSnapToGridActive()) {
	        //System.out.println((Math.floor(Math.log10(_newExtents))+1) + " - " + _newExtents);
	        _graphics2D.setColor(Color.black);
	        _graphics2D.setStroke(StrokeSamples.strokeSamples[0].getStroke());
	        int _scaleWidth = getWidth()-((int)getWidth()/20)*2;
	        double _gridGap = Math.pow(10,Math.floor(Math.log10(_scaleWidth / engineConfig.getDrawFactor()))-1);
	        //System.out.println(Math.pow(10,Math.floor(Math.log10(_scaleWidth / engineConfig.getDrawFactor()))-1));
	        double _firstXGridGap = engineConfig.xtoSpace(0)-engineConfig.xtoSpace(0)%_gridGap;
	        double _lastXGridGap = (engineConfig.xtoSpace(getWidth())-engineConfig.xtoSpace(getWidth())%_gridGap)+_gridGap;
	        double _firstYGridGap = engineConfig.ytoSpace(0)-engineConfig.ytoSpace(0)%_gridGap;
	        double _lastYGridGap = (engineConfig.ytoSpace(getWidth())-engineConfig.ytoSpace(getHeight())%_gridGap)+_gridGap;
	        double _actualXGridGap = _firstXGridGap;
	        while (_actualXGridGap <= _lastXGridGap) {
	            double _actualYGridGap = _firstYGridGap;
	            while (_actualYGridGap >= _lastYGridGap) {
	                Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(_actualXGridGap)-1, engineConfig.ytoPixel(_actualYGridGap)-1, 2, 2);
	                _graphics2D.draw(point);
	                _actualYGridGap -= _gridGap;
	            }
	            _actualXGridGap += _gridGap;
	        }
	    }

	    _graphics2D.setStroke(new BasicStroke(1));
	    //DIBUJO EL POLIGONO DE CALCULO DE DISTANCIAS
	    if (drawingPointsVector.size() > 1) {
		_graphics2D.setColor(Color.GREEN.darker());
		try {
		    for (int i = 0; i < drawingPointsVector.size() - 1; i++) //Fijarse si es size()-1
		    {
			Point2D x0y0 = drawingPointsVector.elementAt(i);
			Point2D x1y1 = drawingPointsVector.elementAt(i + 1);
			_graphics2D.drawLine(engineConfig.xtoPixel(x0y0.getX()), engineConfig.ytoPixel(x0y0.getY()), engineConfig.xtoPixel(x1y1.getX()), engineConfig.ytoPixel(x1y1.getY()));
		    }
		    Point2D x0y0 = drawingPointsVector.elementAt(drawingPointsVector.size() - 1);
		    Point2D x1y1 = drawingPointsVector.elementAt(0);
		    _graphics2D.drawLine(engineConfig.xtoPixel(x0y0.getX()), engineConfig.ytoPixel(x0y0.getY()), engineConfig.xtoPixel(x1y1.getX()), engineConfig.ytoPixel(x1y1.getY()));
		} catch (NullPointerException x) {
		    x.printStackTrace();
		}
	    } else if (drawingPointsVector.size() == 0) {
		//drawingPointsVector.add(new Point2D.Double());
	    }
	    // DIBUJO EL POLIGONO DE ZOOM
	    if (zoomRectangle != null) {
		_graphics2D.setColor(Color.BLUE);
		_graphics2D.draw(zoomRectangle);
		Composite originalComposite = _graphics2D.getComposite();
		_graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
		_graphics2D.fill(zoomRectangle);
		_graphics2D.setComposite(originalComposite);
	    }

	    if (currentOperation == BasicDrawEngineConfig.OPERATION_MULTIQUERY && multiQueryRectangle != null) {
		_graphics2D.setColor(Color.orange);
		_graphics2D.setStroke(new BasicStroke(1));
		_graphics2D.draw(multiQueryRectangle);
	        Composite originalComposite = _graphics2D.getComposite();
	        _graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
	        _graphics2D.fill(multiQueryRectangle);
	        _graphics2D.setComposite(originalComposite);
	    }

	    // DIBUJO EL PUNTO DE REFERENCIA
	    if (engineConfig.hasReferencePosition()) {
		Point2D.Double referencePosition = engineConfig.getReferencePosition();
		Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(referencePosition.getX() - (double)16 / 2.0), engineConfig.ytoPixel(referencePosition.getY() + (double)16 / 2.0), ((double)16 * engineConfig.getDrawFactor()), ((double)16 * engineConfig.getDrawFactor()));
		_graphics2D.setColor(Color.CYAN);
		_graphics2D.fill(point);
		_graphics2D.setColor(Color.BLACK);
		_graphics2D.draw(point);
	    }

	    // DIBUJO EL PUNTO DE REFERENCIA
	    if (GaiaEnvironment.point != null) {
		double _diametro = 5;
	        Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(GaiaEnvironment.point.getX() - (double)_diametro / 2.0), engineConfig.ytoPixel(GaiaEnvironment.point.getY() + (double)_diametro / 2.0), ((double)5 * engineConfig.getDrawFactor()), ((double)_diametro * engineConfig.getDrawFactor()));
	        _graphics2D.setColor(Color.RED);
	        _graphics2D.fill(point);
	        _graphics2D.setColor(Color.BLACK);
	        _graphics2D.draw(point);
	    }

	    // INCRUSTO EL LOGO DE LA ORGANIZACION 
	    if (engineConfig.paintLogo()) {
		Image logo = OrganizationInfo.getLeftLogo();
		double _logoScale = Math.min(90 / (double)logo.getHeight(this), 90 / (double)logo.getWidth(this));
		_graphics2D.drawImage(logo, 20, getHeight() - (int)(logo.getHeight(this) * _logoScale) - 20, (int)(logo.getWidth(this) * _logoScale), (int)(logo.getHeight(this) * _logoScale), this);
	    }
	    
	    if (engineConfig.paintScaleBar()) {
		drawScaleBar(_graphics2D);
	    }
	    isPainting = false;
	    //_graphics.drawImage(volatileImg, 0, 0, this);
	    if (rasterImageMatrixMode) {
	        _graphics2D.setColor(Color.black);
	        Font thisFont = new Font("Arial", Font.BOLD, 12);
	        _graphics2D.setFont(thisFont);
	        _graphics2D.drawString("[MODO RASTER]", 20, (int)getBounds().getMaxY()-5);
	    }

	    super.paintComponents(_graphics2D);
	}
	//} while(volatileImg.contentsLost());
    }

    private void setAntiAlias(Graphics2D _graphics2D) {
	_graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	_graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	_graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	_graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	_graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	_graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	_graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }

    private void unsetAntiAlias(Graphics2D _graphics2D) {
	_graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	_graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
	_graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
	_graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
	_graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
	_graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	_graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }


    private void drawRasterImageVector() {
	long startTime = System.currentTimeMillis();
	rasterImageMatrix2.removeAllElements();
	rasterImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
	rasterImage.setLoaded(false);
	rasterImage.setBounds(new Rectangle2D.Double(engineConfig.xtoSpace(0), engineConfig.ytoSpace(0), engineConfig.xtoSpace(engineConfig.getFWidth()) - engineConfig.xtoSpace(0), engineConfig.ytoSpace(0) - engineConfig.ytoSpace(engineConfig.getFHeight())));
	MapImage labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
	labelGraphics2D = labelImage.createGraphics();
	Graphics2D _imageGraphics = rasterImage.createGraphics();
	drawLayers(_imageGraphics, labelGraphics2D, engineConfig);
	_imageGraphics.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
	rasterImage.setLoaded(true);
	rasterImageMatrix2.add(rasterImage);
	repaint();
	//evaluarRendimiento(System.currentTimeMillis()-startTime);

	// Armo la Matriz de imagenes:
	// [ img0             img1              img2 ]
	// [ img3       img4=rasterimage        img5 ]
	// [ img6             img7              img8 ]
	if (_xThread != null) {
	    if (_xThread.isAlive()) {
		System.out.println(_xThread + " interrupted");
		_xThread.interrupt();
	    }
	}
	_xThread = new Thread(new Runnable() {

			   public void run() {
			       double _xOffset = engineConfig.getXOffset();
			       double _yOffset = engineConfig.getYOffset();
			       Graphics2D _test;
			       MapImage labelImage;
			       MapImage _tempImage;
			       // Primero debo setear el offset, luego dibujar y volver el offset al original porque
			       // el método toSpace depende del offset

			       // 1ra FILA

			       BasicDrawEngineConfig _engineConfig = engineConfig.getCopy();
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 1) {
				   Environment.jpStatusBar.setIndeterminate(true);
				   Environment.jpStatusBar.setAction("1/8");
				   _engineConfig.setXOffset(_engineConfig.xtoSpace(0 - _engineConfig.getFWidth()));
				   _engineConfig.setYOffset(_engineConfig.ytoSpace(0));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));
				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.red);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("2/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setXOffset(_xOffset);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 2) {

				   _engineConfig.setYOffset(_engineConfig.ytoSpace(0));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.YELLOW);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("3/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 3) {

				   _engineConfig.setXOffset(_engineConfig.xtoSpace(_engineConfig.getFWidth()));
				   _engineConfig.setYOffset(_engineConfig.ytoSpace(0));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.cyan);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("4/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setXOffset(_xOffset);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       // 2da FILA
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 4) {

				   _engineConfig.setXOffset(_engineConfig.xtoSpace(0 - _engineConfig.getFWidth()));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.ORANGE);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("5/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setXOffset(_xOffset);
				   repaint();
			       }

			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 5) {
				   _engineConfig.setXOffset(_engineConfig.xtoSpace(_engineConfig.getFWidth()));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.BLUE);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("6/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setXOffset(_xOffset);
				   repaint();
			       }
			       // 3ra FILA
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 6) {

				   _engineConfig.setXOffset(_engineConfig.xtoSpace(0 - _engineConfig.getFWidth()));
				   _engineConfig.setYOffset(_engineConfig.ytoSpace(_engineConfig.getFHeight() * 2));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));
				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.MAGENTA);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("7/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setXOffset(_xOffset);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 7) {

				   _engineConfig.setYOffset(_engineConfig.ytoSpace(_engineConfig.getFHeight() * 2));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.lightGray);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   Environment.jpStatusBar.setAction("8/8");
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			       if (rasterImageMatrix2.size() == 8) {

				   _engineConfig.setXOffset(_engineConfig.xtoSpace(_engineConfig.getFWidth()));
				   _engineConfig.setYOffset(_engineConfig.ytoSpace(_engineConfig.getFHeight() * 2));
				   _tempImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   _tempImage.setLoaded(false);
				   _tempImage.setBounds(new Rectangle2D.Double(_engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0), _engineConfig.xtoSpace(_engineConfig.getFWidth()) - _engineConfig.xtoSpace(0), _engineConfig.ytoSpace(0) - _engineConfig.ytoSpace(_engineConfig.getFHeight())));

				   _test = _tempImage.createGraphics();
				   //_test.setColor(Color.PINK);
				   _test.fillRect(0, 0, getBounds().width, getBounds().height);
				   labelImage = new MapImage(getBounds().width, getBounds().height, MapImage.TYPE_4BYTE_ABGR);
				   labelGraphics2D = labelImage.createGraphics();
				   drawLayers(_test, labelGraphics2D, _engineConfig);
				   _test.drawImage(labelImage, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
				   _tempImage.setLoaded(true);
				   rasterImageMatrix2.add(_tempImage);

				   _engineConfig.setXOffset(_xOffset);
				   _engineConfig.setYOffset(_yOffset);
				   repaint();
			       }
			       Environment.jpStatusBar.setAction("Listo...");
			       System.out.println("Rasterizing done...");
			       setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			       Environment.jpStatusBar.setIndeterminate(false);
			   }

		       }
	    );
	//_xThread.setPriority(Thread.NORM_PRIORITY);
	    if (rasterImageMatrixMode) {
		try {
		    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    System.out.println("Rasterizing");
		    _xThread.start();
		} catch (Exception x) {
		    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    //x.printStackTrace();
		}
	    }
    }

    private void drawScaleBar(Graphics2D _graphics2D) {
	BufferedImage _scaleImage = new BufferedImage(getWidth(), 17, BufferedImage.TYPE_4BYTE_ABGR);
	Graphics2D _scaleImageGraphics = _scaleImage.createGraphics();
	int _insets = _scaleImage.getWidth()/20;
	int _scaleWidth = _scaleImage.getWidth()-_insets*2;
	Composite originalComposite = _scaleImageGraphics.getComposite();
	_scaleImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
	_scaleImageGraphics.setColor(Color.WHITE);
	_scaleImageGraphics.fillRect(0, 0,_scaleImage.getWidth(), _scaleImage.getHeight());
	_scaleImageGraphics.setComposite(originalComposite);
	Font thisFont = new Font("Arial", Font.PLAIN, 12);
	_scaleImageGraphics.setFont(thisFont);
	String _scales[] = new String[6];
	double _offset = engineConfig.xtoSpace(0.0);
	_scales[0] = "" + decimalFormat(0, 2);
	_scales[1] = "" + decimalFormat(engineConfig.xtoSpace(1*_scaleWidth/5)-_offset,2);
	_scales[2] = "" + decimalFormat(engineConfig.xtoSpace(2*_scaleWidth/5)-_offset,2);
	_scales[3] = "" + decimalFormat(engineConfig.xtoSpace(3*_scaleWidth/5)-_offset,2);
	_scales[4] = "" + decimalFormat(engineConfig.xtoSpace(4*_scaleWidth/5)-_offset,2);
	_scales[5] = "" + decimalFormat(engineConfig.xtoSpace(5*_scaleWidth/5)-_offset,2);
	_scaleImageGraphics.setColor(Color.WHITE);
	_scaleImageGraphics.fillRect(0*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[0],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[0],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[0],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.fillRect(1*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[1],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[1],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[1],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.fillRect(2*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[2],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[2],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[2],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.fillRect(3*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[3],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[3],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[3],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.fillRect(4*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[4],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[4],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[4],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.fillRect(5*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[5],_scaleImageGraphics).getWidth()/2), 0, (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[5],_scaleImageGraphics).getWidth(), (int)_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[5],_scaleImageGraphics).getHeight());
	_scaleImageGraphics.setColor(Color.BLACK);
	_scaleImageGraphics.drawString(_scales[0], 0*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[0],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.drawString(_scales[1], 1*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[1],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.drawString(_scales[2], 2*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[2],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.drawString(_scales[3], 3*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[3],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.drawString(_scales[4], 4*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[4],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.drawString(_scales[5], 5*_scaleWidth/5+_insets-(int)(_scaleImageGraphics.getFontMetrics().getStringBounds(_scales[5],_scaleImageGraphics).getWidth()/2), 9);
	_scaleImageGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
	_scaleImageGraphics.setColor(Color.BLACK);
	_scaleImageGraphics.fillRect(_insets, 10,_scaleWidth, 10);
	_scaleImageGraphics.setColor(Color.YELLOW);
	_scaleImageGraphics.fillRect(_scaleWidth/5+_insets, 10,_scaleWidth/5, 10);
	_scaleImageGraphics.fillRect(3*_scaleWidth/5+_insets, 10, _scaleWidth/5, 10);
	_graphics2D.drawImage(_scaleImage, 0, 22, _scaleImage.getWidth(), _scaleImage.getHeight(), this);
	_scaleImageGraphics.setColor(Color.BLACK);

    }

    private Polygon2D pointsArraysToPolygon(double[] xp, double[] yp) {
	if (xp.length > 0) {
	    double[] xy = new double[xp.length * 2];
	    for (int i = 0; i < xp.length * 2; i += 2) {
		xy[i] = xp[i / 2];
		xy[i + 1] = yp[i / 2];
	    }
	    return new Polygon2D.Double(xy);
	} else {
	    return new Polygon2D.Double(0, 0);
	}
    }

    private Polygon2D pointsVectorToPolygon(Vector _points) {
	if (_points.size() > 0) {
	    double[] xy = new double[_points.size() * 2];
	    for (int i = 0; i < _points.size() * 2; i += 2) {
		Point2D _punto = (Point2D.Double)_points.elementAt(i / 2);
		xy[i] = _punto.getX();
		xy[i + 1] = _punto.getY();
	    }
	    return new Polygon2D.Double(xy);
	} else {
	    return new Polygon2D.Double(0, 0);
	}
    }

    private Polygon2D pointsArrayToPolygon(Point2D.Double[] _xy) {
	if (_xy.length > 0) {
	    double[] xy = new double[_xy.length * 2];
	    for (int i = 0; i < _xy.length * 2; i += 2) {
		xy[i] = _xy[i / 2].getX();
		xy[i + 1] = _xy[i / 2].getY();
	    }
	    return new Polygon2D.Double(xy);
	} else {
	    return new Polygon2D.Double(0, 0);
	}
    }

    private ESRIPolygon getFakePolygon(Rectangle2D _rect, BasicDrawEngineConfig _engineConfig) {
	double[] xy = new double[8];
	xy[0] = _engineConfig.xtoPixel(_rect.getMinX());
	xy[1] = _engineConfig.ytoPixel(_rect.getMinY());
	xy[2] = _engineConfig.xtoPixel(_rect.getMaxX());
	xy[3] = _engineConfig.ytoPixel(_rect.getMinY());
	xy[4] = _engineConfig.xtoPixel(_rect.getMaxX());
	xy[5] = _engineConfig.ytoPixel(_rect.getMaxY());
	xy[6] = _engineConfig.xtoPixel(_rect.getMinX());
	xy[7] = _engineConfig.ytoPixel(_rect.getMaxY());
	return new ESRIPolygon.Double(xy);
    }

    private ESRIPolygon getFakePolygon(ESRIPolygon _poly, BasicDrawEngineConfig _engineConfig) {
	double[] xy = new double[_poly.getVertexCount() * 2];
	for (int i = 0; i < _poly.getVertexCount() * 2; i += 2) {
	    xy[i] = _engineConfig.xtoPixel(_poly.getX(i / 2));
	    xy[i + 1] = _engineConfig.ytoPixel(_poly.getY(i / 2));
	}
	return new ESRIPolygon.Double(xy);
    }

    private ESRIPolygon.Double toSpace(Rectangle _rectangle, BasicDrawEngineConfig _engineConfig) {
	double[] xy = new double[10];
	double _minX = _engineConfig.xtoSpace(_rectangle.getMinX());
	double _minY = _engineConfig.ytoSpace(_rectangle.getMinY());
	double _maxX = _engineConfig.xtoSpace(_rectangle.getMaxX());
	double _maxY = _engineConfig.ytoSpace(_rectangle.getMaxY());
	xy[0] = _minX;
	xy[1] = _minY;
	xy[2] = _minX;
	xy[3] = _maxY;
	xy[4] = _maxX;
	xy[5] = _maxY;
	xy[6] = _maxX;
	xy[7] = _minY;
	xy[8] = _minX;
	xy[9] = _minY;
	return new ESRIPolygon.Double(xy);
    }
    /*
    private ESRIPolyline getFakePolyline(ESRIPolyline _poly) {
	ESRIPoint[] xy = new ESRIPoint[_poly.getgetVertexCount()];
	for (int i = 0; i < _poly.getVertexCount() * 2; i += 2) {
	    xy[i] = new ESRIPoint(engineConfig.xtoPixel(_poly.getX(i / 2)),engineConfig.ytoPixel(_poly.getY(i / 2)));
	}
	return new ESRIPolyline(xy);
    }
    */

    private ESRIPolygon getFakePolygon(double[] xp, double[] yp) {
	int cantidad = xp.length;
	if (cantidad > 0) {
	    double[] xy = new double[cantidad * 2];
	    for (int i = 0; i < cantidad * 2; i += 2) {
		xy[i] = engineConfig.xtoPixel(xp[i / 2]);
		xy[i + 1] = engineConfig.ytoPixel(yp[i / 2]);
	    }
	    return new ESRIPolygon.Double(xy);
	} else {
	    return new ESRIPolygon.Double(0, 0);
	}
    }

    public void distance(boolean _add) {
	double _distanciaParcial = 0;
	double _area = 0;
	double _angulo = 0;
	double _distanciaTotal = 0;
	if (drawingPointsVector.size() < 99) //Fijarse si hay que mantener la restricción o no
	{
	    if (_add) {
		drawingPointsVector.add(new Point2D.Double(currentPosition.getX(), currentPosition.getY()));
	    }
	    if (drawingPointsVector.size() == 2) { //Un solo segmento, por lo tanto distancia parcial
		if (!_add) {
		    //distancia = distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 2), drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		    _distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 2)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		}
	    } else if (drawingPointsVector.size() > 2) { //Por lo menos dos segmentos, por lo tanto distancia parcial y distancia total
		 for (int i = 0; i < drawingPointsVector.size()-2; i++)  {
		     _distanciaTotal += (drawingPointsVector.elementAt(i)).distance(drawingPointsVector.elementAt(i+1));
		 }
		if (_add) {
		    //distancia = distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 3), drawingPointsVector.elementAt(drawingPointsVector.size() - 2));
		    _distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 3)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 2));
		} else {
		    //distancia = distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 2), drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		    _distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 2)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		}
		_area = calcArea(drawingPointsVector, _add);
		/**angulo = calcAngulo(polypoints);*/
	    }
	    ruleViewer.setData(_distanciaParcial, _distanciaTotal, _area, _angulo);
	} else {
	    Advisor.messageBox("Maxima cantidad de puntos alcanzada, \nno debe superar los 100 puntos", "Error");
	}
    }

    private double distance(Point2D.Double _sourcePoint, Point2D.Double _destinationPoint) {
	/*if (engineConfig.getProjectionType() == CoordinateSystems.LL) {
	    GKCoord gk0 = CoordinateSystems.geo2gk(x0.getY(), x0.getX());
	    GKCoord gk1 = CoordinateSystems.geo2gk(x1.getY(), x1.getX());
	    double catetox = gk1.getX() - gk0.getX();
	    double catetoy = gk1.getY() - gk0.getY();
	    double cats = catetox * catetox + catetoy * catetoy;
	    double dist = Math.sqrt(cats);
	    return dist;
	} else {
	    double catetox = x1.getX() - x0.getX();
	    double catetoy = x1.getY() - x0.getY();
	    double cats = catetox * catetox + catetoy * catetoy;
	    double dist = Math.sqrt(cats);
	    return dist;
	}*/
	double deltaX = deltaX(_sourcePoint, _destinationPoint);
	double deltaY = deltaY(_sourcePoint, _destinationPoint);
	return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private double deltaX(Point2D.Double sourcePoint, Point2D.Double destinationPoint) {
	double deltaX;
	if (engineConfig.getProjectionType() == CoordinateSystems.LL) {
	    GKCoord gk0 = CoordinateSystems.geo2gk(sourcePoint.getY(), sourcePoint.getX());
	    GKCoord gk1 = CoordinateSystems.geo2gk(destinationPoint.getY(), destinationPoint.getX());
	    deltaX = gk1.getX() - gk0.getX();
	} else {
	    deltaX = destinationPoint.getX() - sourcePoint.getX();
	}
	return Math.abs(deltaX);
    }

    private double deltaY(Point2D.Double sourcePoint, Point2D.Double destinationPoint) {
	double deltaY;
	if (engineConfig.getProjectionType() == CoordinateSystems.LL) {
	    GKCoord gk0 = CoordinateSystems.geo2gk(sourcePoint.getY(), sourcePoint.getX());
	    GKCoord gk1 = CoordinateSystems.geo2gk(destinationPoint.getY(), destinationPoint.getX());
	    deltaY = gk1.getY() - gk0.getY();
	} else {
	    deltaY = destinationPoint.getY() - sourcePoint.getY();
	}
	return Math.abs(deltaY);
    }

    private double calcArea(Vector _poly, boolean nuevo) {
	if (engineConfig.getProjectionType() == CoordinateSystems.LL) {
	    double area = 0;
	    int dif = 1;
	    if (nuevo)
		dif = 2;
	    for (int i = 0; i < _poly.size() - dif; i++) {
		GKCoord gkip = CoordinateSystems.geo2gk(((Point2D.Double)_poly.elementAt(i + 1)).getY(), ((Point2D.Double)_poly.elementAt(i + 1)).getX());
		GKCoord gki = CoordinateSystems.geo2gk(((Point2D.Double)_poly.elementAt(i)).getY(), ((Point2D.Double)_poly.elementAt(i)).getX());
		area += ((gkip.getX() * gki.getY()) - (gki.getX() * gkip.getY()));
	    }
	    GKCoord gk0 = CoordinateSystems.geo2gk(((Point2D.Double)_poly.elementAt(0)).getY(), ((Point2D.Double)_poly.elementAt(0)).getX());
	    GKCoord gks = CoordinateSystems.geo2gk(((Point2D.Double)_poly.elementAt(_poly.size() - dif)).getY(), ((Point2D.Double)_poly.elementAt(_poly.size() - dif)).getX());
	    area += ((gk0.getX() * gks.getY()) - (gks.getX() * gk0.getY()));
	    return Math.abs(area / 2);
	} else {
	    double area = 0;
	    int dif = 1;
	    if (nuevo)
		dif = 2;
	    for (int i = 0; i < _poly.size() - dif; i++) {
		area += ((((Point2D.Double)_poly.elementAt(i + 1)).getX() * ((Point2D.Double)_poly.elementAt(i)).getY()) - (((Point2D.Double)_poly.elementAt(i)).getX() * ((Point2D.Double)_poly.elementAt(i + 1)).getY()));
	    }
	    area += ((((Point2D.Double)_poly.elementAt(0)).getX() * ((Point2D.Double)_poly.elementAt(_poly.size() - dif)).getY()) - (((Point2D.Double)_poly.elementAt(_poly.size() - dif)).getX() * ((Point2D.Double)_poly.elementAt(0)).getY()));
	    return Math.abs(area / 2);
	}
    }

    private double angle(double x0, double y0, double x1, double y1) {
	return Math.abs((Math.atan((y1 - y0) / (x1 - x0)) * 180) / Math.PI);
    }
    /*
    private void bpoligono_actionPerformed(ActionEvent e) {
	setOperation(OPERATION_DISTANCE_AREA);
    }

    private void bZoom_actionPerformed(ActionEvent e) {
	setOperation(OPERATION_ZOOM_IN);
    }
    */

    private void zoomOut() {
	double _newDrawScale = engineConfig.getDrawScale() / scaleFactor;
	double originX = engineConfig.xtoSpace(engineConfig.getFWidth()/2);
	double originY = engineConfig.ytoSpace(engineConfig.getFHeight()/2);
	engineConfig.setDrawScale(_newDrawScale);
	engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
	double destX = engineConfig.xtoSpace(engineConfig.getFWidth()/2);
	double destY = engineConfig.ytoSpace(engineConfig.getFHeight()/2);
	engineConfig.setXOffset(engineConfig.getXOffsetOriginal() - (destX - originX));
	engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (destY - originY));
	engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
	engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
	repaint();
    }
    
    private void zoomExtents() {
	//setMapExtents(3548172.1937, 7249881.0068, 3571654.0741, 7268573.5261);
	//engineConfig.setFWidth(0);
	flyTo(1.0d, engineConfig.getXOffsetPosta(), engineConfig.getYOffsetPosta());
    }
    
    public void setOperation(int _operation) {
	containedShapesTimer.stop();
	infoTimer.stop();
	containedShapeIDS = new int[geometrySets.size()][0];
	removeMouseListener(eraseListener);
	removeMouseMotionListener(eraseMotionListener);
	boolean _ruleViewerVisible = ruleViewer.isVisible();
	switch (_operation) {
	    case BasicDrawEngineConfig.OPERATION_DISTANCE_AREA:
		eraseListener = distanceMouseListener;
		eraseMotionListener = distanceMotionListener;
		_ruleViewerVisible = true;
		restartEnvironment();
		operationStatus = "Herramienta de Medición: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_ESCAPE:
		switch (currentOperation) { //Por si quiero especificar acciones para cada operación
			default: 
			    restartEnvironment();
			    break;
		}
		break;
	    case BasicDrawEngineConfig.OPERATION_QUERY:
		eraseListener = infoMouseListener;
		eraseMotionListener = infoMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Ver Info: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_ZOOM_IN:
		eraseListener = zoomMouseListener;
		eraseMotionListener = zoomMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Zoom Ventana: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_ZOOM_OUT:
		zoomOut();
	        break;
	    case BasicDrawEngineConfig.OPERATION_ADDRESSES:
		eraseListener = addressesMouseListener;
		eraseMotionListener = addressesMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Reasignando Dirección: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_STREETS:
		eraseListener = streetsMouseListener;
		eraseMotionListener = streetsMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Reasignando Calle: ";
		break;
	    /*case BasicDrawEngineConfig.OPERATION_INFRASTRUCTURES:
		eraseListener = infrastructuresMouseListener;
		eraseMotionListener = infrastructuresMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Reasignando Infraestructura: ";
		break;*/
	    case BasicDrawEngineConfig.OPERATION_EDITION:
		eraseListener = editionMouseListener;
		eraseMotionListener = editionMotionListener;
		_ruleViewerVisible = true;
		restartEnvironment();
		operationStatus = "Editando layer: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_MANAGE_IMAGE_ATTACHMENTS:
		for (int i = 0; i < layers.size(); i++) {
		    layers.elementAt(i).getLayerConfig().getImageAttachments().removeAllElements();
		    layers.elementAt(i).getImageAttachments().removeAllElements();
		}

		eraseListener = imageManagerMouseListener;
		eraseMotionListener = imageManagerMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		System.out.println("Restarted");
		operationStatus = "Image manager: ";
		break;
	    case BasicDrawEngineConfig.OPERATION_ZOOM_EXTENTS:
		zoomExtents();
		break;
	    case BasicDrawEngineConfig.OPERATION_PRINT:
		doReport();
		break;
	    case BasicDrawEngineConfig.OPERATION_SET_OSNAP_ON_OFF:
		engineConfig.setOsnapActive(!engineConfig.isOsnapActive());
		System.out.println("OSNAP: " + (engineConfig.isOsnapActive()?"ON":"OFF"));
		Advisor.messagePopupWindow("Imán " + (engineConfig.isOsnapActive()?"encendido":"apagado"), "");
		break;
	    case BasicDrawEngineConfig.OPERATION_SET_ORTHO_ON_OFF:
	        engineConfig.setOrthoActive(!engineConfig.isOrthoActive());
	        System.out.println("ORTHO: " + (engineConfig.isOrthoActive()?"ON":"OFF"));
	        Advisor.messagePopupWindow("Ortonormal " + (engineConfig.isOrthoActive()?"encendido":"apagado"), "");
	        break;
	    case BasicDrawEngineConfig.OPERATION_SET_SNAPTOGRID_ON_OFF:
	        engineConfig.setSnapToGridActive(!engineConfig.isSnapToGridActive());
	        System.out.println("SNAP2GRID: " + (engineConfig.isSnapToGridActive()?"ON":"OFF"));
	        Advisor.messagePopupWindow("Snap to Grid " + (engineConfig.isSnapToGridActive()?"encendido":"apagado"), "");
	        break;
	    case BasicDrawEngineConfig.OPERATION_SET_LOGO_ON_OFF:
		engineConfig.setPaintLogo(!engineConfig.paintLogo());
		repaint();
		System.out.println("LOGO: " + (engineConfig.paintLogo()?"ON":"OFF"));
		Advisor.messagePopupWindow("Logo " + (engineConfig.paintLogo()?"encendido":"apagado"), "");
		break;
	    case BasicDrawEngineConfig.OPERATION_SET_ANTIALIAS_ON_OFF:
		engineConfig.setAntiAlias(!engineConfig.isAntiAlias());
		repaint();
		System.out.println("ANTIALIAS: " + (engineConfig.isAntiAlias()?"ON":"OFF"));
		Advisor.messagePopupWindow("Suavizado " + (engineConfig.isAntiAlias()?"encendido":"apagado"), "");
		break;
	    case BasicDrawEngineConfig.OPERATION_SAVE_MAP_IMAGE:
		saveMapImage();
		break;
	    case BasicDrawEngineConfig.OPERATION_SHOW_HELP:
		showHelp();
		break;
	    case BasicDrawEngineConfig.OPERATION_MULTIQUERY:
	        eraseListener = multiQueryMouseListener;
	        eraseMotionListener = multiQueryMotionListener;
	        _ruleViewerVisible = false;
		restartEnvironment();
	        operationStatus = "Ver MultiInfo: ";
	        break;
	    case BasicDrawEngineConfig.OPERATION_FIXED_POLYGON_QUERY:
		eraseListener = fixedPolygonQueryMouseListener;
		eraseMotionListener = fixedPolygonQueryMotionListener;
		_ruleViewerVisible = false;
		restartEnvironment();
		operationStatus = "Consulta de geometrias contenidas en un polígono: ";
		selectLayer();
	        break;
	    case BasicDrawEngineConfig.OPERATION_NOMENCLADOR:
		doNomenclador();
		break;
	    case BasicDrawEngineConfig.OPERATION_SET_SCALEBAR_ON_OFF:
	        engineConfig.setPaintScaleBar(!engineConfig.paintScaleBar());
		drawRasterImageVector();
		Advisor.messagePopupWindow("Barra de escala " + (engineConfig.paintScaleBar()?"encendida":"apagada"), "");
	        break;
	    case BasicDrawEngineConfig.OPERATION_SET_COORDINATES_ON_OFF:
		engineConfig.setPaintCoordinates(!engineConfig.paintCoordinates());
		repaint();
		break;
	    case BasicDrawEngineConfig.OPERATION_LIST_LAYERS:
		doListLayers();
		break;
	    case BasicDrawEngineConfig.OPERATION_SET_COORDINATE_VIEWER_ON_OFF:
	        coordinateViewer.setVisible(!coordinateViewer.isVisible());
		engineConfig.setCoordinateViewerVisible(coordinateViewer.isVisible());
	        break;
	    case BasicDrawEngineConfig.OPERATION_SET_RASTER_MODE_ON_OFF:
	        rasterImageMatrixMode = !rasterImageMatrixMode;
	        drawRasterImageVector();
		Advisor.messagePopupWindow("Modo raster (imagen) " + (rasterImageMatrixMode?"encendido":"apagado"), "");
	        break;
	    case BasicDrawEngine.OPERATION_SET_BOTTOM_LEFT_COMPONENT_ON_OFF:
		if (bottomLeftComponent != null) {
		    bottomLeftComponent.setVisible(!bottomLeftComponent.isVisible());
		}
	        repaint();
	        break;
	    case BasicDrawEngine.OPERATION_SET_TOP_LEFT_COMPONENT_ON_OFF:
	        if (topLeftComponent != null) {
	            topLeftComponent.setVisible(!topLeftComponent.isVisible());
	        }
	        repaint();
	        break;
	    case BasicDrawEngine.OPERATION_SET_TOP_RIGHT_COMPONENT_ON_OFF:
	        if (topRightComponent != null) {
	            topRightComponent.setVisible(!topRightComponent.isVisible());
	        }
	        repaint();
	        break;
	    case BasicDrawEngine.OPERATION_SET_BOTTOM_RIGHT_COMPONENT_ON_OFF:
	        if (bottomRightComponent != null) {
	            bottomRightComponent.setVisible(!bottomRightComponent.isVisible());
	        }
	        repaint();
	        break;
	    case BasicDrawEngineConfig.OPERATION_CONFIGURE_REPORT:
		ExtendedInternalFrame _reportConfigInternalFrame = new ExtendedInternalFrame("Configuración de reportes");
		ReportConfigPanel _reportConfigPanel = new ReportConfigPanel();
		_reportConfigInternalFrame.setCentralPanel(_reportConfigPanel);
		_reportConfigPanel.setLayers(layers);
		_reportConfigInternalFrame.setClosable(true);
		_reportConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
		_reportConfigInternalFrame.setIconifiable(false);
		_reportConfigInternalFrame.show();
		repaint();
	        break;
	}
	addMouseListener(eraseListener);
	addMouseMotionListener(eraseMotionListener);
	currentOperation = _operation;
	engineConfig.setCurrentOperation(currentOperation);
	Environment.jpStatusBar.setAction(operationStatus);
	ruleViewer.setVisible(_ruleViewerVisible);
    }

    private void setReferencePosition(Point2D.Double _position) {
	engineConfig.setReferencePosition(_position);
    }

    private void saveCurrentPosition() {
	//BasicDrawEngineConfig.OPERATION_SAVE_CURRENT_POSITION:
	GaiaEnvironment.point = currentPosition;
	    operationStatus = "Punto guardado";
	    repaint();
    }

    private void flyToCurrentPosition() {
	if (GaiaEnvironment.point != null) {
	    if (GaiaEnvironment.point.getX() != 0 && GaiaEnvironment.point.getY()  != 0) {
		setPointEnvironment(GaiaEnvironment.point, 50, true);
	    }
	}
    }
    
    protected MouseWheelListener commonWheelListener = new MouseWheelListener() {

	    public void mouseWheelMoved(MouseWheelEvent me) {
	    
		setMouseActive(true);

		//Diferencia entre el punto y el offset
		double originX = engineConfig.xtoSpace(me.getX());
		double originY = engineConfig.ytoSpace(me.getY());

	        double _newDrawScale = 0.0;
	        
		if (me.getWheelRotation() < 0) {
		    //Rotación desde abajo hacia arriba
		    _newDrawScale = engineConfig.getDrawScale() * scaleFactor;
		} else {
		    _newDrawScale = engineConfig.getDrawScale() / scaleFactor;
		}

	        if ((me.getModifiers() & MouseWheelEvent.SHIFT_MASK) == MouseWheelEvent.SHIFT_MASK || (me.getModifiers() & MouseWheelEvent.CTRL_MASK) == MouseWheelEvent.CTRL_MASK) {
		    int _scaleWidth = getWidth()-((int)getWidth()/20)*2;
		    double _extents = Math.round(_scaleWidth / engineConfig.getDrawFactor());
		    double _newExtents = (int)(_extents/10)*10;
		    //Si SHIFT --> 10; si CTRL --> 50
		    double _amount = (((me.getModifiers() & MouseWheelEvent.SHIFT_MASK) == MouseWheelEvent.SHIFT_MASK)?10.0:50.0);
		    if (_newExtents == _extents) {
			_newExtents = _newExtents + (_amount*me.getWheelRotation());
		    } else {
		        if (me.getWheelRotation() > 0) { //Sumo _amount sólo si acerco, sino dejo tal cual está
		            _newExtents = _newExtents + _amount;
		        }
		    }
		    //Controlo porque si pasa a negativo se invierte el dibujo
		    if (_newExtents < 10) {
			_newExtents = 10;
		    } else if (_newExtents > 10000000) {
			_newExtents = 10000000;
		    }
		    double _drawFactor = _scaleWidth / _newExtents;
		    double _drawScale = _drawFactor / engineConfig.getDrawFactorOriginal();
		    engineConfig.setDrawScale(_drawScale);
		} else {
		    engineConfig.setDrawScale(_newDrawScale);
		}
	        engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
	        double destX = engineConfig.xtoSpace(me.getX());
	        double destY = engineConfig.ytoSpace(me.getY());
		engineConfig.setXOffset(engineConfig.getXOffsetOriginal() - (destX - originX));
		engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (destY - originY));
		engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
		engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
		repaint();
	    }

	};

    private void setMouseActive(boolean _active) {
        if (_active && !mouseActive) {
            activityTimer.start();
        } else if (!_active) {
            activityTimer.stop();
            repaint();
        } else {
            lastActivityTime = System.currentTimeMillis();
        }
        mouseActive = _active;
    }

    protected MouseListener commonMouseListener = new MouseListener() {

	    public void mousePressed(MouseEvent me) {
                //setMouseActive(true);
		if (me.getButton() == me.BUTTON2) {
		    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    startDragPosition = mousePosition;
		}
	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == me.BUTTON2) {
		    if (startDragPosition != null) {
			engineConfig.setXOffset(engineConfig.getXOffsetOriginal() + (engineConfig.xtoSpace(startDragPosition.x) - engineConfig.xtoSpace(mousePosition.x)));
			engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (engineConfig.xtoSpace(startDragPosition.y) - engineConfig.xtoSpace(mousePosition.y)));
			engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
			engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
			panLine = new Line2D.Float();
			startDragPosition = null;
		        //mouseActive = false;
		        drawRasterImageVector();
		    }
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	        //mouseActive = false;
	    }

	    public void mouseClicked(MouseEvent me) {
	        //setMouseActive(true);
		requestFocus();
	        if ((me.getButton() == me.BUTTON1) && (me.getClickCount() == 1)) {
	            //saveCurrentPosition();
	        } else  if ((me.getButton() == me.BUTTON2) && (me.getClickCount() == 2)) {
		    zoomExtents();
		} else if ((me.getButton() == me.BUTTON3) && (me.getClickCount() == 1)) {
		    if ((me.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK) {
			setReferencePosition(null);
		    } else if ((me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
			setReferencePosition(new Point2D.Double(currentPosition.getX(), currentPosition.getY()));
		    }
		    repaint();
		} else if ((me.getButton() == me.BUTTON3) && (me.getClickCount() == 2) && ((me.getModifiers() & MouseEvent.SHIFT_MASK) != 0)) {
		    //coordinateViewer.setVisible(false);
		    //setVisible(false);
		}
		//mouseActive = false;
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener commonMotionListener = new MouseMotionListener() {

	    public void mouseMoved(MouseEvent me) {
	        mousePosition = me.getPoint();
	        osnap(mousePosition);
	        if (engineConfig.getProjectionType() == CoordinateSystems.UTM) {
	            int zona = 19;
	            coordinateViewer.setUTMCoord(new UTMCoord(currentPosition.getX(), currentPosition.getY(), zona));
	            System.out.println(currentPosition.getX());
	        } else if (engineConfig.getProjectionType() == CoordinateSystems.GK || engineConfig.getProjectionType() == -1) {
	            int faja = 0;
	            coordinateViewer.setGKCoord(new GKCoord(currentPosition.getX(), currentPosition.getY(), faja));
	        } else if (engineConfig.getProjectionType() == CoordinateSystems.LL) {
	            coordinateViewer.setLLCoord(new LatLongCoord(currentPosition.getY(), currentPosition.getX()));
	        }
	    }

	    public void mouseDragged(MouseEvent me) {
                setMouseActive(true);
	        mousePosition = me.getPoint();
	        osnap(mousePosition);
	        if (engineConfig.hasReferencePosition()) {
	            labelxy.setOpaque(true);
	            Point2D.Double referencePosition = engineConfig.getReferencePosition();
	            labelxy.setText("<html><b><font color=ff0000><u>dX</u></font></b>: " + decimalFormat(deltaX(currentPosition, referencePosition),4) + " - <b><font color=ff0000><u>dY</u></font></b>: " + decimalFormat(deltaY(currentPosition, referencePosition),4) + "</html>");
	        } else {
	            labelxy.setOpaque(false);
	            labelxy.setText("");
	        }
	        if (startDragPosition != null) {
	            engineConfig.setXOffset(engineConfig.getXOffsetOriginal() + (engineConfig.xtoSpace(startDragPosition.x) - engineConfig.xtoSpace(mousePosition.x)));
	            engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (engineConfig.xtoSpace(startDragPosition.y) - engineConfig.xtoSpace(mousePosition.y)));
	            currentPosition = engineConfig.toSpace(mousePosition);
	            panLine.setLine(startDragPosition, mousePosition);
	        }
		repaint();
	    }
	};

    protected MouseListener zoomMouseListener = new MouseListener() {

	    public void mousePressed(MouseEvent me) {

	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    if (startDrawRectPosition == null) {
		        setMouseActive(true);
			startDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		    } else {
			endDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
			//ZOOM
			createDrawRectangle();
			setPixelEnvironment(zoomRectangle);
			startDrawRectPosition = null;
			zoomRectangle = null;
		        //mouseActive = false;
		        repaint();
		    }
		}
	    }

	    public void mouseClicked(MouseEvent me) {

	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener zoomMotionListener = new MouseMotionListener() {

	    public void mouseMoved(MouseEvent me) {
	        endDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
	        createDrawRectangle();
	        if (zoomRectangle != null) {
	            setMouseActive(true);
	            repaint();
	        } else {
	            //mouseActive = false;
	        }
	    }

	    public void mouseDragged(MouseEvent me) {

	    }

	};
	
    protected MouseListener distanceMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {

	    }

	    public void mousePressed(MouseEvent me) {

	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    if ((me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
			restartEnvironment();
			//labelinfo.setVisible(true);
		    } else {
			distance(true);
		    }
		    repaint();
		} else if (me.getButton() == me.BUTTON3) {
		    if (drawingPointsVector.size() > 0) {
			drawingPointsVector.removeElementAt(drawingPointsVector.size()-1);
			repaint();
		    }
		}
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener distanceMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		if (drawingPointsVector.size() > 1) {
		    setMouseActive(true);
		    labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());
		    distance(false);
		    //currentPosition = drawingPointsVector.elementAt(drawingPointsVector.size()-1);
		    repaint();
		} else {
		    //mouseActive = false;
		}
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	};

    protected MouseListener infoMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    for (int i = 0; i < geometrySets.size(); i++)  {
			for (int k = geometrySets.elementAt(i).getLayers().size() - 1; k >= 0; k--) {
			    Layer _layer = geometrySets.elementAt(i).getLayers().elementAt(k);
			    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
				for (int j = 0; j < containedShapeIDS[i].length; j++)  {
				    if (containedShapeIDS[i][j] != -1) {
					if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
					    ESRIPoint _point = _layer.getGeometrySet().getPoint(containedShapeIDS[i][j]);
					    _layer.fireClick(_point);
					} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
					    ESRIPolygon _polygon = _layer.getGeometrySet().getPolygon(containedShapeIDS[i][j]);
					    _layer.fireClick(_polygon);
					} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYLINE) {
					    ESRIPolygon _polyline = _layer.getGeometrySet().getPolygon(containedShapeIDS[i][j]);
					    _layer.fireClick(_polyline);
					}
				    }
				}
			    }
			}
		    }
		}
	    }

	    public void mousePressed(MouseEvent me) {
		/*if (me.getButton() == me.BUTTON1) {
		    if (startDrawRectPosition == null) {
			startDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		    }
		    repaint();
		}*/
	    }
	    //REVISAR

	    public void mouseReleased(MouseEvent me) {
		/*if (me.getButton() == me.BUTTON1) {
		    if (startDrawRectPosition != null) {
			endDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
			//ZOOM
			createDrawRectangle();
			//setPixelEnvironment(zoomRectangle);
			int i = 0;
			boolean found = false;
			//for (int i = 0; i < layers.size(); i++) {
			while (i < layers.size() && !found) {
			    Layer2 _layer = layers.elementAt(i);
			    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
				found = true;
				double _x = engineConfig.xtoSpace((int)zoomRectangle.getMinX());
				double _y = engineConfig.ytoSpace((int)zoomRectangle.getMinY());
				double _w = engineConfig.xtoSpace((int)zoomRectangle.getMaxX()) - _x;
				double _h = engineConfig.ytoSpace((int)zoomRectangle.getMaxY()) - _y;
				_layer.fireClick(me, new Rectangle2D.Double(_x, _y, _w, _h));
			    }
			    //TODO ELSE
			    i++;
			}
			startDrawRectPosition = null;
			zoomRectangle = null;
		    }
		    repaint();
		}*/
	    }

	    public void mouseEntered(MouseEvent me) {
		infoTimer.start();
	    }

	    public void mouseExited(MouseEvent me) {
		infoTimer.stop();
		//toolTip.setVisible(false);
	    }

	};
	
    private void updateToolTipPosition() {
	int x = mousePosition.x + 5;
	int y = mousePosition.y - toolTip.getHeight() - 5;
	if (x + toolTip.getWidth() > getBounds().getMaxX()) {
	    if (toolTip.getWidth() > getBounds().getMaxX()) {
		x = (int)(getBounds().getMinX());
	    } else {
		x = (int)(getBounds().getMaxX() - toolTip.getWidth() - 5);
	    }
	}
	if (y < 0 || toolTip.getHeight() > getBounds().getHeight()) {
	    y = (int)(getBounds().getMinY());
	}
	Point _location = new Point(x,y);
	SwingUtilities.convertPointToScreen(_location, Environment.getActiveDesktop());
	toolTip.setLocation(_location);
    }

    protected MouseMotionListener infoMotionListener = new MouseMotionListener() {
	    public void mouseMoved(MouseEvent me) {
		updateToolTipPosition();
	    }

	    public void mouseDragged(MouseEvent me) {
	    }

	};

    protected MouseListener addressesMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    if (GaiaEnvironment.getSelectedStreetID() == -1 && selectedGeometryIndex != -1) {
			//CALLE SELECCIONADA!!!
			
			int _idPolygon = getLayer(GaiaEnvironment.getStreetsLayer()).getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon();

		    GaiaEnvironment.setSelectedStreetID(_idPolygon);
		    GaiaEnvironment.setSelectedStreetName(getLayer(GaiaEnvironment.getStreetsLayer()).getLabelValue(_idPolygon));

			hideToolTipTimer.stop();
		    } else {
			//DAR DE ALTA O MODIFICAR DIRECCION!!!
			int i = 0;
			Layer _layer = getLayer(GaiaEnvironment.getAddressLayer());
			if (_layer != null) {
			    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
				if (selectedGeometryIndex != -1) {
				    if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
				        //ESRIPoint _point = (ESRIPoint)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				        ESRIPoint _point = _layer.getGeometrySet().getPoint(selectedGeometryIndex);
					_layer.fireClick(_point, false);
				    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
				        //ESRIPolygon _polygon = (ESRIPolygon)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				        ESRIPolygon _polygon = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
					_layer.fireClick(_polygon, false);
				    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
				        //ESRIPolygon _polyline = (ESRIPolygon)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				        ESRIPolygon _polyline = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
					_layer.fireClick(_polyline, false);
				    }
				} else {
				    if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT && _layer.isModifiable()) {
					//AGREGO UN PUNTO!
					ESRIPoint _point = new ESRIPoint(engineConfig.xtoSpace(me.getX()), engineConfig.ytoSpace(me.getY()));
					_layer.fireClick(_point, false);
				    }
				}
				//TODO ELSE
			    }
			}
		    }
		} else if (me.getButton() == me.BUTTON3) {
		    GaiaEnvironment.setSelectedStreetID(-1);
		    hideToolTipTimer.start();
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

	};
    protected MouseMotionListener addressesMotionListener = new MouseMotionListener() {
	    public void mouseMoved(MouseEvent me) {
		if (GaiaEnvironment.getSelectedStreetID() == -1) {
		    //Seleccionando calle
		    //labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());
		    //labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		    mousePosition = me.getPoint();
		    Layer _layer = getLayer(GaiaEnvironment.getStreetsLayer());
		    if (_layer != null) {
			if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			    if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
				selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
				if (_layer.isQueryable()) {
				    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
					toolTip.getContentPane().add(label);
					label.setText(_layer.getToolTipText().toString());
					label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					label.setFont(new Font("Arial", Font.PLAIN, 12));
					toolTip.pack();
					showToolTip(true);
					Point p = me.getPoint();
					SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
					toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				    } else {
					showToolTip(false);
				    }
				}
			    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYLINE) {
				selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
				if (_layer.isQueryable()) {
				    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
					toolTip.getContentPane().add(label);
					label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon()));
					label.setFont(new Font("Arial", Font.BOLD, 16));
					toolTip.pack();
					showToolTip(true);
					Point p = me.getPoint();
					SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
					toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				    } else {
					showToolTip(false);
				    }
				}
			    }
			}
		    }
		} else {
		    //Asignando dirección
		    selectedGeometryIndex = -1;
		    Layer _layer = getLayer(GaiaEnvironment.getAddressLayer());
		    if (_layer != null) {
			if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			    if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
				/*int j = 0;
				while (j < _layer.getGeometries().size()) {
				    // && !found) {
				    ESRIPoint _point = (ESRIPoint)_layer.getGeometries().elementAt(j);
				    Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(_point.getX() - (double)_layer.getPointDiameter() / 2.0), engineConfig.ytoPixel(_point.getY() + (double)_layer.getPointDiameter() / 2.0), ((double)_layer.getPointDiameter() * engineConfig.getDrawFactor()), ((double)_layer.getPointDiameter() * engineConfig.getDrawFactor()));
				    if (point.contains(me.getPoint())) {
					selectedGeometryIndex = j;
				    }
				    j++;
				}*/
				 selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
				selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
				if (_layer.isQueryable()) {
				    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
					///if (!toolTip.isVisible()) {
					toolTip.getContentPane().add(label);
					label.setText(_layer.getToolTipText().toString());
					label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					label.setFont(new Font("Arial", Font.PLAIN, 12));
					toolTip.pack();
					showToolTip(true);
					//}
					Point p = me.getPoint();
					SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
					toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				    } else {
					showToolTip(false);
				    }
				}
			    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
				selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
				if /*queryLayer*/(_layer.isQueryable()) {
				    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
					///if (!toolTip.isVisible()) {
					toolTip.getContentPane().add(label);
				        label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon()));
					label.setBorder(null);
					label.setFont(new Font("Arial", Font.BOLD, 16));
					toolTip.pack();
					showToolTip(true);
					//}
					Point p = me.getPoint();
					SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
					toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				    } else {
					showToolTip(false);
				    }
				}
			    }
			}
		    }
		}
		//repaint();
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	};
	
    protected MouseListener streetsMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    //DAR DE ALTA O MODIFICAR DIRECCION!!!
		    int i = 0;
		    Layer _layer = getLayer(GaiaEnvironment.getStreetsLayer());
		    if (_layer != null) {
			if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			    if (selectedGeometryIndex != -1) {
				if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
				    ESRIPoint _point = _layer.getGeometrySet().getPoint(selectedGeometryIndex);
				    _layer.fireClick(_point, true);
				} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
				    ESRIPolygon _polygon = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
				    _layer.fireClick(_polygon, true);
				} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
				    ESRIPolygon _polyline = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
				    _layer.fireClick(_polyline, false);
				}
			    } else {
				if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT && _layer.isModifiable()) {
				    //AGREGO UN PUNTO!
				    ESRIPoint _point = new ESRIPoint(engineConfig.xtoSpace(me.getX()), engineConfig.ytoSpace(me.getY()));
				    _layer.fireClick(_point, true);
				}
			    }
			    //TODO ELSE
			}
		    }
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

	};
    protected MouseMotionListener streetsMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		//Seleccionando calle
		//labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());
		//labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		mousePosition = me.getPoint();
		Layer _layer = getLayer(GaiaEnvironment.getStreetsLayer());
		if (_layer != null) {
		    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getToolTipText().toString());
				    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				    label.setFont(new Font("Arial", Font.PLAIN, 12));
				    toolTip.pack();
				    showToolTip(true);
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon()));
				    label.setBorder(null);
				    label.setFont(new Font("Arial", Font.BOLD, 16));
				    toolTip.pack();
				    showToolTip(true);
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			}
		    }
		}
		//repaint();
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	};
	
    protected MouseListener infrastructuresMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    //DAR DE ALTA O MODIFICAR INFRAESTRUCTURA!!!
		    int i = 0;
		    Layer _layer = getLayer(GaiaEnvironment.getInfrastructuresLayer());
		    if (_layer != null) {
			if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			    if (selectedGeometryIndex != -1) {
				if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
				    //ESRIPoint _point = (ESRIPoint)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				    ESRIPoint _point = _layer.getGeometrySet().getPoint(selectedGeometryIndex);
				    _layer.fireClick(_point, false);
				} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
				    //ESRIPolygon _polygon = (ESRIPolygon)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				    ESRIPolygon _polygon = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
				    _layer.fireClick(_polygon, false);
				} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
				    //ESRIPolygon _polyline = (ESRIPolygon)(_layer.getGeometries().toArray()[selectedGeometryIndex]);
				    ESRIPolygon _polyline = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
				    _layer.fireClick(_polyline, false);
				}
			    } else {
				if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT && _layer.isModifiable()) {
				    //AGREGO UN PUNTO!
				    ESRIPoint _point = new ESRIPoint(engineConfig.xtoSpace(me.getX()), engineConfig.ytoSpace(me.getY()));
				    _layer.fireClick(_point, false);
				}
			    }
			    //TODO ELSE
			}
		    }
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

	};
    protected MouseMotionListener infrastructuresMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		//labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());
		//labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		//mousePosition = me.getPoint();
		selectedGeometryIndex = -1;
		Layer _layer = getLayer(GaiaEnvironment.getInfrastructuresLayer());
		boolean found = false;
		if (_layer != null) {
		    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				toolTip.getContentPane().add(label);
				label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPoint(selectedGeometryIndex).getIdPoint()));
				label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				label.setFont(new Font("Arial", Font.PLAIN, 12));
				toolTip.pack();
				Point p = me.getPoint();
				SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				found = true;
				showToolTip(true);
			    } else {
				showToolTip(false);
			    }
			} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getToolTipText().toString());
				    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				    label.setFont(new Font("Arial", Font.PLAIN, 12));
				    toolTip.pack();
				    showToolTip(true);
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    ///if (!toolTip.isVisible()) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon()));
				    label.setBorder(null);
				    label.setFont(new Font("Arial", Font.BOLD, 16));
				    toolTip.pack();
				    showToolTip(true);
				    //}
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			}
		    }
		}
		repaint();
	    }

	    public void mouseDragged(MouseEvent me) {

	    }

	};

    protected MouseListener drawingMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {

	    }

	    public void mousePressed(MouseEvent me) {

	    }

	    public void mouseReleased(MouseEvent me) {
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener drawingMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	};
	
    protected MouseListener editionMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		//DAR DE ALTA O MODIFICAR GEOMETRÍAS!!!
		int i = 0;
		boolean found = false;
		while (i < layers.size() && !found) {
		    if (layers.elementAt(i).isActive()) {
			found = true;
			Layer _layer = layers.elementAt(i);
			if (_layer.isOn() && (_layer.isQueryable() || _layer.isEditable() || _layer.isActive())) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (selectedGeometryIndex != -1 && drawingPointsVector.size() <= 1 && (me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
				//EDITO UNA GEOMETRIA
				if (me.getButton() == me.BUTTON1) {
				    if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
					ESRIPoint _point = _layer.getGeometrySet().getPoint(selectedGeometryIndex);
				        if (_layer.getEditionForm() != null) {
				            _layer.edit(_point);
				        } else {
				            LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
				            _layerEditionPanel.setContentObject(_point);

				            ExtendedInternalFrame _layerEditionInternalFrame = new ExtendedInternalFrame("Editando geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
				            _layerEditionInternalFrame.setCentralPanel(_layerEditionPanel);
				            _layerEditionInternalFrame.setClosable(true);
				            _layerEditionInternalFrame.setDesktop(Environment.getActiveDesktop());
				            _layerEditionInternalFrame.setIconifiable(false);
				            _layerEditionInternalFrame.show();
				            _layerEditionInternalFrame.setMaximizable(true);
				            repaint();
				        }
				    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
					ESRIPolygon _polygon = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
				        if (_layer.getEditionForm() != null) {
				            _layer.edit(_polygon);
				        } else {
				            LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
				            _layerEditionPanel.setContentObject(_polygon);

				            ExtendedInternalFrame _layerEditionInternalFrame = new ExtendedInternalFrame("Editando geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
				            _layerEditionInternalFrame.setCentralPanel(_layerEditionPanel);
				            _layerEditionInternalFrame.setClosable(true);
				            _layerEditionInternalFrame.setDesktop(Environment.getActiveDesktop());
				            _layerEditionInternalFrame.setIconifiable(false);
				            _layerEditionInternalFrame.show();
				            _layerEditionInternalFrame.setMaximizable(true);
				            repaint();
				        }
				    } else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
					ESRIPolygon _polyline = _layer.getGeometrySet().getPolygon(selectedGeometryIndex);
					//_layer.fireClick(_polyline, false);
				        if (_layer.getEditionForm() != null) {
				            _layer.edit(_polyline);
				        } else {
				            LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
				            _layerEditionPanel.setContentObject(_polyline);

				            ExtendedInternalFrame _layerEditionInternalFrame = new ExtendedInternalFrame("Editando geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
				            _layerEditionInternalFrame.setCentralPanel(_layerEditionPanel);
				            _layerEditionInternalFrame.setClosable(true);
				            _layerEditionInternalFrame.setDesktop(Environment.getActiveDesktop());
				            _layerEditionInternalFrame.setIconifiable(false);
				            _layerEditionInternalFrame.show();
				            _layerEditionInternalFrame.setMaximizable(true);
				            repaint();
				        }
				    }
				}
			    } else {
				//AGREGO UNA GEOMETRIA
				if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {// && _layer.isEditable()) {
				    //AGREGO UN PUNTO
				    ESRIPoint _point = new ESRIPoint(engineConfig.xtoSpace(me.getX()), engineConfig.ytoSpace(me.getY()));
				    if (_layer.getEditionForm() != null) {
					_layer.edit(_point);
				    } else {
				        LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
				        _layerEditionPanel.setContentObject(_point);

				        ExtendedInternalFrame _layerEditionInternalFrame = new ExtendedInternalFrame("Nueva geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
				        _layerEditionInternalFrame.setCentralPanel(_layerEditionPanel);
				        _layerEditionInternalFrame.setClosable(true);
				        _layerEditionInternalFrame.setDesktop(Environment.getActiveDesktop());
				        _layerEditionInternalFrame.setIconifiable(false);
				        _layerEditionInternalFrame.show();
				        _layerEditionInternalFrame.setMaximizable(true);
				        repaint();
				    }
				} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {// && _layer.isEditable()) {
				    if (me.getButton() == me.BUTTON1) {
					if ((me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
					    restartEnvironment();
					    //labelinfo.setVisible(true);
					} else if ((me.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK) {
					    double[] xd = new double[drawingPointsVector.size()];
					    double[] yd = new double[drawingPointsVector.size()];
					    for (int j = 0; j < drawingPointsVector.size(); j++)
					    {
						Point2D _xy = drawingPointsVector.elementAt(j);
						xd[j] = _xy.getX();
						yd[j] = _xy.getY();
					    }

					    ESRIPolygon _polygon = ESRIPolygon.constructPolygon(xd, yd);
					    if (_layer.getEditionForm() != null) {
						_layer.edit(_polygon);
					    } else {
						LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
						_layerEditionPanel.setContentObject(_polygon);
    
						ExtendedInternalFrame _reportConfigInternalFrame = new ExtendedInternalFrame("Nueva geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
						_reportConfigInternalFrame.setCentralPanel(_layerEditionPanel);
						_reportConfigInternalFrame.setClosable(true);
						_reportConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
						_reportConfigInternalFrame.setIconifiable(false);
						_reportConfigInternalFrame.show();
						_reportConfigInternalFrame.setMaximizable(true);
						repaint();
					    }
					} else {
					    distance(true);
					}
				    } else if (me.getButton() == me.BUTTON3) {
					if (drawingPointsVector.size() > 1) {
					    drawingPointsVector.removeElementAt(drawingPointsVector.size()-1);
					}
				    }
				} /*else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) { // && _layer.isEditable()) {
				    if (me.getButton() == me.BUTTON1) {
					if ((me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
					    restartEnvironment();
					    //labelinfo.setVisible(true);
					} else if ((me.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK) {
					    double[] xd = new double[drawingPointsVector.size()];
					    double[] yd = new double[drawingPointsVector.size()];
					    for (int j = 0; j < drawingPointsVector.size(); j++) {
						Point2D _xy = drawingPointsVector.elementAt(j);
						xd[j] = _xy.getX();
						yd[j] = _xy.getY();
					    }
    
					    ESRIPolygon _polygon = ESRIPolygon.constructPolygon(xd, yd);
					    LayerEditionPanel _layerEditionPanel = new LayerEditionPanel(_layer);
					    _layerEditionPanel.setContentObject(_polygon);
    
					    ExtendedInternalFrame _reportConfigInternalFrame = new ExtendedInternalFrame("Nueva geometría " + _layer.getGeometrySetConfig().getSqlScheme() + "." + _layer.getGeometrySetConfig().getSqlTable());
					    _reportConfigInternalFrame.setCentralPanel(_layerEditionPanel);
					    _reportConfigInternalFrame.setClosable(true);
					    _reportConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
					    _reportConfigInternalFrame.setIconifiable(false);
					    _reportConfigInternalFrame.show();
					    _reportConfigInternalFrame.setMaximizable(true);
					    repaint();
    
					    //grabar
					} else {
					    distance(true);
					}
				    } else if (me.getButton() == me.BUTTON3) {
					if (drawingPointsVector.size() > 1) {
					    drawingPointsVector.removeElementAt(drawingPointsVector.size() - 1);
					}
				    }
				}*/
			    }
			    //TODO ELSE
			}
		    }
		    i++;
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

	};
    protected MouseMotionListener editionMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {

	        if (drawingPointsVector.size() > 1) {
	            distance(false);
	        }

		labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());
		//labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		mousePosition = me.getPoint();
		selectedGeometryIndex = -1;
		Layer _layer = getLayer(GaiaEnvironment.getInfrastructuresLayer());
		boolean found = false;
		if (_layer != null) {
		    if (_layer.isOn() && (_layer.isQueryable() || _layer.isModifiable())) {
			if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POINT) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				found = true;
				toolTip.getContentPane().add(label);
			        label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPoint(selectedGeometryIndex).getIdPoint()));
				label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				label.setFont(new Font("Arial", Font.PLAIN, 12));
				toolTip.pack();
				Point p = me.getPoint();
				SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				showToolTip(true);
			    } else {
				showToolTip(false);
			    }
			} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || _layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if (_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    ///if (!toolTip.isVisible()) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getToolTipText().toString());
				    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				    label.setFont(new Font("Arial", Font.PLAIN, 12));
				    toolTip.pack();
				    showToolTip(true);
				    //}
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			} else if (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
			    selectedGeometryIndex = _layer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
			    if /*queryLayer*/(_layer.isQueryable()) {
				if (selectedGeometryIndex != -1 && _layer.getToolTipText() != null) {
				    ///if (!toolTip.isVisible()) {
				    toolTip.getContentPane().add(label);
				    label.setText(_layer.getLabelValue(_layer.getGeometrySet().getPolygon(selectedGeometryIndex).getIdPolygon()));
				    label.setBorder(null);
				    label.setFont(new Font("Arial", Font.BOLD, 16));
				    toolTip.pack();
				    showToolTip(true);
				    //}
				    Point p = me.getPoint();
				    SwingUtilities.convertPointToScreen(p, Environment.getActiveDesktop());
				    toolTip.setLocation(p.x + 5, p.y - toolTip.getHeight() - 5);
				} else {
				    showToolTip(false);
				}
			    }
			}
		    }
		}
		repaint();
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {

	    }

	};

    protected MouseListener imageManagerMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON2) {
		    if (selectedImageAttachment != null) {
			//selectedImageAttachment.setMinX(engineConfig.xtoSpace(0));
			//selectedImageAttachment.setMinY(engineConfig.ytoSpace(0));
		    }
		} else if (me.getButton() == MouseEvent.BUTTON3) {
		    ImageAttachment _imageAttachment = new ImageAttachment();
		    if (_imageAttachment.selectImageFile()) {
			System.out.println(new Rectangle2D.Double(engineConfig.xtoSpace(0), engineConfig.ytoSpace(0), engineConfig.xtoSpace(getWidth())-engineConfig.xtoSpace(0), engineConfig.ytoSpace(0)-engineConfig.ytoSpace(getHeight())));
			_imageAttachment.setSpaceBounds(new Rectangle2D.Double(engineConfig.xtoSpace(0), engineConfig.ytoSpace(0), engineConfig.xtoSpace(getWidth())-engineConfig.xtoSpace(0), engineConfig.ytoSpace(0)-engineConfig.ytoSpace(getHeight())));
			_imageAttachment.setScale(1);
		        boolean found = false;
			int i = 0;
		        while (i < layers.size() && !found) {
		            Layer _layer = layers.elementAt(i);
		            if (_layer.isOn() && _layer.isActive()) {
				found = true;
				_layer.getLayerConfig().getImageAttachments().add(_imageAttachment);
				_layer.retrieveImageAttachments();
			    }
			    i++;
			}
		    }
		}
	    }

	    public void mousePressed(MouseEvent me) {
		if (me.getButton() == me.BUTTON3) {
		    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    imageStartDragPosition = mousePosition;
		} else if (me.getButton() == me.BUTTON1) {
		    if (selectedImageAttachment != null) {
			Rectangle2D[] controlPoints = selectedImageAttachment.getControlPoints(engineConfig);
			boolean found = false;
			int i = 0;
			while (i < controlPoints.length && !found) {
			    found = controlPoints[i].contains(me.getPoint());
			    i++;
			}
			if (found) {
			    imageStartDragPosition = selectedImageAttachment.getCentroid(engineConfig);
			}
			double scale = selectedImageAttachment.getScale();
		        String _scale = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), scale, "Seleccione el layer con polígonos", JOptionPane.QUESTION_MESSAGE);
			if (_scale != null) {
			    selectedImageAttachment.setScale(Double.parseDouble(_scale));
			}

		    }
		}
	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON3) {
		    if (imageStartDragPosition != null) {
			panLine = new Line2D.Float();
			if (selectedImageAttachment != null) {
			     selectedImageAttachment.setMinX(selectedImageAttachment.getMinX() + (engineConfig.xtoSpace(mousePosition.x)) - engineConfig.xtoSpace(imageStartDragPosition.x));
			     selectedImageAttachment.setMinY(selectedImageAttachment.getMinY() - (engineConfig.ytoSpace(imageStartDragPosition.y) - engineConfig.ytoSpace(mousePosition.y)));
			}
		    } else if (me.getButton() == MouseEvent.BUTTON1) {
		    }
		    imageStartDragPosition = null;
		    repaint();
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener imageManagerMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		int i = 0;
		boolean found = false;
		while (i < layers.size() && !found) {
		    Layer _layer = layers.elementAt(i);
		    if (_layer.isOn()) {
			int j = 0;
			while (j < _layer.getLayerConfig().getImageAttachments().size() && !found) {
			    ImageAttachment _imageAttachment = _layer.getLayerConfig().getImageAttachments().elementAt(j);
			    if (_imageAttachment.isOn()) {
				BufferedImage _imageAttachmentImage = _layer.getImageAttachments().elementAt(j);
				if (_imageAttachmentImage != null) {
				    Rectangle2D _bounds = _imageAttachment.getBounds(engineConfig);
				    selectedImageAttachment = _bounds.contains(me.getPoint()) ? _imageAttachment : null;
				}
			    }
			    found = (selectedImageAttachment != null);
			    j++;
			}
		    }
		    i++;
		}
		repaint();
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {
	        if (SwingUtilities.isRightMouseButton(me)) {
		    if (imageStartDragPosition != null) {
			if (selectedImageAttachment != null) {
			    selectedImageAttachment.setMinX(selectedImageAttachment.getMinX() + (engineConfig.xtoSpace(mousePosition.x)) - engineConfig.xtoSpace(imageStartDragPosition.x));
			    selectedImageAttachment.setMinY(selectedImageAttachment.getMinY() - (engineConfig.ytoSpace(imageStartDragPosition.y) - engineConfig.ytoSpace(mousePosition.y)));
			    imageStartDragPosition = me.getPoint();
			}
			mousePosition = me.getPoint();
			panLine.setLine(imageStartDragPosition, mousePosition);
			repaint();
		    }
	        } else if (SwingUtilities.isLeftMouseButton(me)) {
		    if (selectedImageAttachment != null) {
			Rectangle2D[] controlPoints = selectedImageAttachment.getControlPoints(engineConfig);
			boolean found = false;
			int i = 0;
			while (i < controlPoints.length && !found) {
			    found = controlPoints[i].contains(me.getPoint());
			    if (found) {
			        //selectedImageAttachment.getImage().getWidth()
			        double _xScale = (selectedImageAttachment.getImage().getWidth() + (engineConfig.xtoSpace(mousePosition.x)) - engineConfig.xtoSpace(imageStartDragPosition.x))/selectedImageAttachment.getImage().getWidth();
			        double _yScale = (selectedImageAttachment.getImage().getHeight() + (engineConfig.ytoSpace(imageStartDragPosition.y) - engineConfig.ytoSpace(mousePosition.y)))/selectedImageAttachment.getImage().getHeight();
			        _xScale = Math.abs(mousePosition.getX()-selectedImageAttachment.getCentroid(engineConfig).getX())/(selectedImageAttachment.getImage().getWidth()/2.0);
			        System.out.println(_xScale + " - " + _yScale);
				System.out.println(selectedImageAttachment.getCentroid(engineConfig).getX() - (selectedImageAttachment.getImage().getWidth()/2.0));
			        System.out.println(engineConfig.xtoSpace(selectedImageAttachment.getCentroid(engineConfig).getX() - (selectedImageAttachment.getImage().getWidth()/2.0)));
			        selectedImageAttachment.setScale(1.0);
			        /*selectedImageAttachment.setSpaceBounds(new Rectangle2D.Double(
			            engineConfig.xtoPixel(selectedImageAttachment.getCentroid(engineConfig).getX() - (selectedImageAttachment.getImage().getWidth()/2.0)),
			            engineConfig.ytoPixel(selectedImageAttachment.getCentroid(engineConfig).getY() - (selectedImageAttachment.getImage().getHeight()/2.0)),
			            (selectedImageAttachment.getImage().getWidth() + (engineConfig.xtoSpace(mousePosition.x)) - engineConfig.xtoSpace(imageStartDragPosition.x)),
			            (selectedImageAttachment.getImage().getHeight() + (engineConfig.ytoSpace(imageStartDragPosition.y) - engineConfig.ytoSpace(mousePosition.y)))
			        ));*/
			    }
			    i++;
			}
		    }
		}
	    }

	};

    protected MouseListener multiQueryMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
	    }
	    //REVISAR

	    public void mousePressed(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    if (multiQueryStartDrawRectPosition == null) {
			multiQueryStartDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		    }
		    repaint();
		}
	    }
	    //REVISAR

	     public void mouseReleased(MouseEvent me) {
	         if (me.getButton() == me.BUTTON1 && multiQueryStartDrawRectPosition != null && multiQueryEndDrawRectPosition != null) {
	             multiQueryRectangle = createRectangle(multiQueryStartDrawRectPosition, multiQueryEndDrawRectPosition);
		     doMultiQuery(createRectangleAsPolygon(engineConfig.toSpace(multiQueryStartDrawRectPosition), engineConfig.toSpace(multiQueryEndDrawRectPosition)));
		     multiQueryStartDrawRectPosition = null;
		     multiQueryEndDrawRectPosition = null;
		     multiQueryRectangle = null;
		     repaint();
		 }
	     }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener multiQueryMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		//labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		/*mousePosition = me.getPoint();
		repaint();*/
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {
		if (multiQueryStartDrawRectPosition != null) {
		    multiQueryEndDrawRectPosition = new Point2D.Double(me.getPoint().getX(), me.getPoint().getY());
		    multiQueryRectangle = createRectangle(multiQueryStartDrawRectPosition, multiQueryEndDrawRectPosition);
		    /*for (int i = 0; i < layers.size(); i++) {
			if (layers.elementAt(i).isOn()) {
			    Layer2 _layer = layers.elementAt(i);
			    Vector _geometries = _layer.getGeometries();
			    int[] _filtered = new int[_layer.getLayerConfig().getFilters().size()+1];
			    for (int j = 0; j < _filtered.length; j++) {
				_filtered[j] = 0;
			    }
			    switch (_layer.getGeometrySetConfig().getShapeType()) {
				case ShapeTypes.POLYGON:
				case ShapeTypes.MULTIPOLYGON:
				    for (int j = 0; j < _geometries.size(); j++) {
					ESRIPolygon _polygon = (ESRIPolygon)_geometries.elementAt(j);
					if (_polygon.intersects(createRectangle(engineConfig.toSpace(multiQueryStartDrawRectPosition), engineConfig.toSpace(multiQueryEndDrawRectPosition)))) {
					    //System.out.println("Layer: " + _layer.getName() + ", id: " + _polygon.getIdPolygon() + ", filter: " + _polygon.getIdFilterMatch());
					    _filtered[_polygon.getIdFilterMatch()+1]++;
					}
				    }
				    System.out.println("Sin filtro: " + _filtered[0]);
				    for (int j = 1; j < _filtered.length; j++) {
				        System.out.println("Filtro " + _layer.getLayerConfig().getFilters().elementAt(j-1).getExpression() + ": " + _filtered[j]);
				    }
				    break;
			    }
			}
		    }*/
		    repaint();
		}
	    }

	};

    protected MouseListener fixedPolygonQueryMouseListener = new MouseListener() {
	    //REVISAR

	    public void mouseClicked(MouseEvent me) {
	    }
	    //REVISAR

	    public void mousePressed(MouseEvent me) {
	    }
	    //REVISAR

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1 && selectedLayer != null) {
		    selectedGeometryIndex = selectedLayer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
		    if (selectedGeometryIndex != -1) {
			doMultiQuery(selectedLayer.getGeometrySet().getPolygon(selectedGeometryIndex));
		    }
		} else if (me.getButton() == MouseEvent.BUTTON3) {
		    selectLayer();
		}
	    }

	    public void mouseEntered(MouseEvent me) {
		containedShapesTimer.start();
	    }

	    public void mouseExited(MouseEvent me) {
	        containedShapesTimer.stop();
	    }

	};
    protected MouseMotionListener fixedPolygonQueryMotionListener = new MouseMotionListener() {
	    //REVISAR

	    public void mouseMoved(MouseEvent me) {
		//labelinfo.setText(String.valueOf(engineConfig.getDrawScale()));
		//mousePosition = me.getPoint();
		if (selectedLayer != null) {
		    /*selectedGeometryIndex = selectedLayer.getGeometrySet().getContainedShapeID(engineConfig.xtoSpace((int)me.getPoint().getX()), engineConfig.ytoSpace((int)me.getPoint().getY()));
		    if (selectedGeometryIndex != -1) {
		        repaint();
		    };*/
		    updateToolTipPosition();
		}
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {
	    }

	};

    private void doMultiQuery(ESRIPolygon _selectedPolygon) {
	BasicTabbedPane _multiQueryTabbedPane = new BasicTabbedPane();
	HashMap _chartsList = new HashMap();
	StringBuilder _queryResult = new StringBuilder("<html>");
	if (selectedLayer != null) {
	    String _label = selectedLayer.getLabelValue(_selectedPolygon.getIdPolygon());
	    _queryResult.append("<p align=center><font size=8px><b>Layer \"" + selectedLayer.getName() + "\"<br>Nombre \"" + (_label.length()>0?_label:String.valueOf(_selectedPolygon.getIdPolygon())) + "\"</b><br></font></p><br>" );
	}

	for (int i = 0; i < layers.size(); i++) {
	    Layer _layer = layers.elementAt(i);
	    if (_layer.getLayerConfig().isSelectedForReport()) {
		int[] _filtered = new int[_layer.getLayerConfig().getFilters().size()];
		//le sumo dos, uno para el total filtrado y otro para el total acumulado
		double[] _sum = new double[_layer.getLayerConfig().getFilters().size()+2];
		int _total = 0;
		for (int j = 0; j < _filtered.length; j++) {
		    _filtered[j] = 0;
		}
		boolean _summable = false;
		switch (_layer.getLayerConfig().getLabelColumnDataType()) {
		    case Types.BIGINT:
		    case Types.DECIMAL:
		    case Types.DOUBLE:
		    case Types.FLOAT:
		    case Types.INTEGER:
		    case Types.NUMERIC:
		    case Types.REAL:
		    case Types.SMALLINT:
		    case Types.TINYINT:
			_summable = true;
			for (int j = 0; j < _sum.length; j++) {
			    _sum[j] = new Double("0");
			}
			break;
		    default: 
			_summable = false;
			break;
		}
		for (int m = 0; m < _layer.getGeometrySetConfig().getGridSize(); m++)  {
		    for (int n = 0; n < _layer.getGeometrySetConfig().getGridSize(); n++)  {
			Vector _geometries = _layer.getGeometrySet().getGeometriesFromMatrix(m,n);
			switch (_layer.getGeometrySetConfig().getShapeType()) {
			    case ShapeTypes.POLYGON:
			    case ShapeTypes.MULTIPOLYGON:
				for (int j = 0; j < _geometries.size(); j++) {
				    ESRIPolygon _polygon = (ESRIPolygon)_geometries.elementAt(j);
				    ESRIPoint[] _polygonPoints = _polygon.getPoints();
				    boolean _intersects = false;
				    int k = 0;
				    if (_polygon.intersects(_selectedPolygon.getBounds())) {
					while (!_intersects && k < _polygonPoints.length) {
					    _intersects = _selectedPolygon.contains(_polygonPoints[k]);
					    k++;
					}
					if (_intersects) {
					    int _idFilterMatch = _layer.getFilterValue(_polygon.getIdPolygon());
                                            int _index = _layer.getLayerConfig().getFilters().indexOf(_layer.getLayerConfig().getFilter(_idFilterMatch));
					    String _label = _layer.getLabelValue(_polygon.getIdPolygon());
					    if (_label.length() == 0 || _label.equals("-1")) {
						_label = "0";
					    }
					    if (_idFilterMatch != -1) {
						_filtered[_index]++;
					        if (_summable) {
					            _sum[_index] += new Double(_label);
					            _sum[_sum.length-2] += new Double(_label);
					        }
					    }
					    if (_summable) {
					        _sum[_sum.length-1] += new Double(_label);
					    }
					    _total++;
					}
				    }
				}
				break;
			    case ShapeTypes.POINT:
			    case ShapeTypes.MULTIPOINT:
				for (int j = 0; j < _geometries.size(); j++) {
				    ESRIPoint _point = (ESRIPoint)_geometries.elementAt(j);
				    if (_selectedPolygon.contains(_point)) {
				        int _idFilterMatch = _layer.getFilterValue(_point.getIdPoint());
				        int _index = _layer.getLayerConfig().getFilters().indexOf(_layer.getLayerConfig().getFilter(_idFilterMatch));
				        String _label = _layer.getLabelValue(_point.getIdPoint());
				        if (_label.length() == 0 || _label.equals("-1")) {
				            _label = "0";
				        }
					if (_idFilterMatch != -1) {
					    if (_layer.getLayerFilter(_idFilterMatch).isActive()) {
						_filtered[_index]++;
						if (_summable) {
						    _sum[_index] += new Double(_label);
						}
					    }
					} else {
					    if (_summable) {
						_sum[_sum.length-2] += new Double(_label);
					    }
					}
				        if (_summable) {
				            _sum[_sum.length-1] += new Double(_label);
				        }
					_total++;
				    }
				}
				break;
			}
		    }
		}
		if (_total > 0) {
		    _queryResult.append("<br><p align=center>Layer: " + _layer.getAlias() + "<br>");

		    DefaultPieDataset _pieDataset = new DefaultPieDataset();
		    DefaultCategoryDataset _barDataset = new DefaultCategoryDataset();
		    int _totalFiltered = 0;
		    
		    if (_filtered.length > 0) {
			
			if (_summable) {
			    _queryResult.append("<table border=1 align=center>");
			    _queryResult.append("<tr><th>Filtro</th><th>\u03A3</th><th>% \u03A3</th><th>Total</th><th>% Total</th></t>");
			}
			for (int j = 0; j < _filtered.length; j++) {
			    if (_filtered[j] > 0 && _layer.getLayerConfig().getFilters().elementAt(j).isActive()) {
				//System.out.println(Format.toDouble((double)_filtered[j]/_total*100.0));
				if (_summable) {
				    _queryResult.append("<tr>");
				    _queryResult.append("<td bgcolor=" + Format.color2Hex(_layer.getLayerConfig().getFilters().elementAt(j).getStyleConfig().getFillColor()) + "><font color=#FFFFFF><b>" + _layer.getLayerConfig().getFilters().elementAt(j).getName() + "</b></font></td>");
				    _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[j]) + "</td>");
				    _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[j] / _sum[_sum.length-1] * 100.0) + " % </td>");
				    _queryResult.append("<td align=center><b>" + _filtered[j] + "</b></td>");
				    _queryResult.append("<td align=right>" + Format.moneyWithOutSign((double)_filtered[j] / _total * 100.0) + " % </td>");
				    _queryResult.append("</tr>");
				    _pieDataset.setValue(_layer.getLayerConfig().getFilters().elementAt(j).getName() + ": \u03A3 " + Format.toDouble(_sum[j]) + "; (" + Format.toDouble((double)_filtered[j] / _total * 100.0) + "%)", Format.toDouble((double)_filtered[j] / _total * 100.0));
				} else {
				    _queryResult.append(_layer.getLayerConfig().getFilters().elementAt(j).getName() + ": " + Format.toDouble((double)_filtered[j] / _total * 100.0) + "% ( <b>" + _filtered[j] + "</b> )<br>");
				    _pieDataset.setValue(_layer.getLayerConfig().getFilters().elementAt(j).getName() + " (" + Format.toDouble((double)_filtered[j] / _total * 100.0) + "%)", Format.toDouble((double)_filtered[j] / _total * 100.0));
				}
				//_barDataset.setValue(_filtered[j], "Cantidad", _layer.getLayerConfig().getFilters().elementAt(j).getToolTipValue());
				_barDataset.setValue(_filtered[j], _layer.getLayerConfig().getFilters().elementAt(j).getName(), "");
				_totalFiltered += _filtered[j];
			    }
			}

			if (_summable) {
			    /*if (_totalFiltered != _total) {
			        //Computar los "Sin Filtrar"
			        _queryResult.append("<tr>");
			        _queryResult.append("<td><b>SIN FILTRAR</b></td>");
			        _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[_sum.length-2]) + "</td>");
			        //Es estupida la division: _sum[_sum.length-1] / _sum[_sum.length-1], pero es representativa
			        _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[_sum.length-2] / _sum[_sum.length-1] * 100.0) + " % </td>");
			        _queryResult.append("<td align=center><b>" + (_total - _totalFiltered) + "</b></td>");
			        //Es estupida la division: _total / _total, pero es representativa
			        _queryResult.append("<td align=right>" + Format.moneyWithOutSign( (double)(_total - _totalFiltered) / _total * 100.0) + " % </td>");
			        _queryResult.append("</tr>");
			    }*/
			    _queryResult.append("<tr>");
			    _queryResult.append("<td><b>TOTALES</b></td>");
			    _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[_sum.length-1]) + "</td>");
			    //Es estupida la division: _sum[_sum.length-1] / _sum[_sum.length-1], pero es representativa
			    _queryResult.append("<td align=right>" + Format.moneyWithOutSign(_sum[_sum.length-1] / _sum[_sum.length-1] * 100.0) + " % </td>");
			    _queryResult.append("<td align=center><b>" + _total + "</b></td>");
			    //Es estupida la division: _total / _total, pero es representativa
			    _queryResult.append("<td align=right>" + Format.moneyWithOutSign((double)_total / _total * 100.0) + " % </td>");
			    _queryResult.append("</tr>");
			    _queryResult.append("</table></p><br><p align=center>");
			}
		    } else {
		    }
		    if (!_summable) {
		        if (selectedLayer != null) {
		            _queryResult.append("Total: " + _total + ((_layer == selectedLayer)?" <i>(Colindantes)</i>":"") + "<br>");
		        } else {
		            _queryResult.append("Total: " + _total + "<br>");
		        }
		    }
		    _queryResult.append("</p>");
		    if (_pieDataset.getItemCount() > 0) {
			if (_total != _totalFiltered) {
			    //Computar los "Sin Filtrar"
			    //_pieDataset.setValue("SIN FILTRAR" + " (" + Format.moneyWithOutSign((double)(_total - _totalFiltered) / _total * 100.0) + "%)", Format.toDouble((double)(_total - _totalFiltered) / _total * 100.0));
			}
			JFreeChart _pieChart = // Title
			    // Dataset
			    // Muestra referencias
			    // Show legend  
			    // Show legend  
			    ChartFactory.createPieChart3D("", _pieDataset, true, false, false);
			    if (_filtered.length > 0) {
			        List _keys = _pieDataset.getKeys();
				int k = 0;
			        for (int j = 0; j < _layer.getLayerConfig().getFilters().size(); j++)
			        {
				    if (_filtered[j]>0) {
					((PiePlot)_pieChart.getPlot()).setSectionPaint((Comparable)_keys.get(k), _layer.getLayerConfig().getFilters().elementAt(j).getStyleConfig().getFillColor());
					k++;
				    }
			        }
			    }
	
			_chartsList.put(_layer.getAlias() + " (Torta)", _pieChart);
		    }
	
		    if (_barDataset.getRowCount() > 0) {
			if (_total != _totalFiltered) {
			    //Computar los "Sin Filtrar"
			    //_barDataset.setValue(Format.toDouble((double)(_total - _totalFiltered)), "Sin filtrar", "");
			}
			JFreeChart _barChart = ChartFactory.createBarChart("", "", "Cantidad", _barDataset, PlotOrientation.VERTICAL, true, false, false);
		        if (_filtered.length > 0) {
		            List _keys = _barDataset.getRowKeys();
		            CategoryItemRenderer barRenderer = new BarRenderer();
		            int k = 0;
		            for (int j = 0; j < _layer.getLayerConfig().getFilters().size(); j++)
		            {
		                if (_filtered[j]>0) {
				    barRenderer.setSeriesPaint(k, _layer.getLayerConfig().getFilters().elementAt(j).getStyleConfig().getFillColor());
				    //((CategoryPlot)_barChart.getPlot()).set(_keys.get(j), _layer.getLayerConfig().getFilters().elementAt(j).getStyleConfig().getFillColor());
				    k++;
				}
		            }
			    ((CategoryPlot)_barChart.getPlot()).setRenderer(barRenderer);
		        }
			_chartsList.put(_layer.getAlias() + " (Barras)", _barChart);
		    }
		}
	
	    }
	}
	_queryResult.append("</html>");
	
	repaint();
	
	BasicInternalFrame _multiQueryDialog = new BasicInternalFrame("Resultado de Consulta Múltiple");
	_multiQueryDialog.setLayout(new BorderLayout());
	_multiQueryDialog.setSize(400, 300);
	JArea _multiQuery = new JArea();
	_multiQuery.setContentType("text/html");
	_multiQuery.setEditable(false);
	BasicPanel _jpMultiQuery = new BasicPanel();
	_jpMultiQuery.setLayout(new BorderLayout());
	_jpMultiQuery.add(_multiQuery, BorderLayout.CENTER);
	_multiQueryTabbedPane.insertTab("Resumen", null, _jpMultiQuery, null, 0);
	
	for (int i = 0; i < _chartsList.size(); i++) {
	    String _chartName = _chartsList.keySet().toArray()[i].toString();
	    final BufferedImage _chartImage = ((JFreeChart)_chartsList.get(_chartName)).createBufferedImage(800, 500);
	
	    BasicLabel _lblChart = new BasicLabel();
	    _lblChart.setIcon(new ImageIcon(_chartImage));
	    _lblChart.setHorizontalAlignment(_lblChart.CENTER);
	
	    BasicPanel _jpChart = new BasicPanel();
	    _jpChart .setLayout(new BorderLayout());
	    _jpChart.add(_lblChart, BorderLayout.CENTER);
	    PrintButton _btnPrint = new PrintButton();
	    _btnPrint.setText("Guardar imagen");
	    _btnPrint.setToolTipText("Guardar imagen como...");
	    _btnPrint.addActionListener(new ActionListener () {

				     public void actionPerformed(ActionEvent actionEvent) {
				         JFileChooser chooser = new JFileChooser(cfg.getProperty("ChartImage") + File.separator);
				         chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				         int returnVal = chooser.showSaveDialog(chooser.getParent());
				         if (returnVal == JFileChooser.APPROVE_OPTION) {
				             // IF File Selected
				             try {
				                 String path = chooser.getSelectedFile().getAbsolutePath();
				                 if (!path.endsWith(".png")) {
				                     path += ".png";
				                 }
				                 cfg.setProperty("ChartImage", chooser.getSelectedFile().getParent());
				                 File file = new File(path);
				                 ImageIO.write(_chartImage, "png", file);
				                 Advisor.messageBox("La imagen se grabó con éxito", "Grabar Imagen");
				             } catch (IOException x) {
				                 Advisor.messageBox("No se pudo grabar la imagen", "Error de E/S");
				                 x.printStackTrace();
				             }
				         }
				     }

				 }
	    );
	    
	    _jpChart.add(_btnPrint, BorderLayout.SOUTH);
	    BasicScrollPane _spChart = new BasicScrollPane(_jpChart);
	    _multiQueryTabbedPane.addTab(_chartName, _spChart);
	}
	
	_multiQueryDialog.add(_multiQueryTabbedPane, BorderLayout.CENTER);
	
	_multiQuery.setText(_queryResult.toString());
	_multiQueryDialog.setMaximizable(true);
	_multiQueryDialog.setClosable(true);
	_multiQueryDialog.setResizable(true);
	_multiQueryDialog.setVisible(true);
	try {
	    _multiQueryDialog.setMaximum(true);
	} catch (PropertyVetoException e) {
	    // TODO
	}
	
    }

    private void selectLayer() {
	Vector<String> _layerNames = new Vector();
	for (int i = 0; i < layers.size(); i++)  {
	    if (layers.elementAt(i).getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON || layers.elementAt(i).getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON) {
		_layerNames.add(layers.elementAt(i).getName());
	    }
	}
	String _selectedLayerName = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Seleccione el layer con polígonos", "Seleccionar layer", JOptionPane.QUESTION_MESSAGE, null, _layerNames.toArray(), _layerNames.toArray()[0]);
	if (_selectedLayerName != null) {
	    selectedLayer = getLayer(_selectedLayerName);
	} else {
	    selectedLayer = null;
	    Advisor.messageBox("Debe seleccionar un layer", "Error");
	}
    }
    
    private String decimalFormat(double _numero, int _decimales) {
	String df = "0.";
	for (int i = 0; i < _decimales; i++) {
	    df += "0";
	}
	return (new DecimalFormat(df)).format(_numero);
    }

    private void osnap(Point _p) {
	int _scaleWidth = getWidth()-((int)getWidth()/20)*2;
	double _gridGap = Math.pow(10,Math.floor(Math.log10(_scaleWidth / engineConfig.getDrawFactor()))-1);
	double _osnapTolerance = _gridGap>1?_gridGap/10:_gridGap;
	osnapTolerance = _osnapTolerance;
	currentPosition = engineConfig.toSpace(_p);
	osnapRectangle = null;
	orthoX = -1;
	orthoY = -1;
	if (engineConfig.isOsnapActive() || engineConfig.isOrthoActive()) {
	    if (engineConfig.isOrthoActive()) {
	        if (drawingPointsVector.size() > 0) {
		    drawingPointsVector.setElementAt(currentPosition, drawingPointsVector.size() - 1);
		} else {
		    drawingPointsVector.add(currentPosition);
		}
	        if (drawingPointsVector.size() > 1) {
	            //calcular el mayor recorrido (segun x o segun y);
	            //System.out.println("DeltaX: " + deltaX(drawingPointsVector.elementAt(drawingPointsVector.size()-2), currentPosition));
	            //System.out.println("DeltaY: " + deltaY(drawingPointsVector.elementAt(drawingPointsVector.size()-2), currentPosition));
	            double _x = currentPosition.getX();
	            double _y = currentPosition.getY();
	            double deltaX = 1;
	            double deltaY = 1;
		    for (int i = drawingPointsVector.size() - 2; i >= 0; i--) {
		        double _deltaX = deltaX(drawingPointsVector.elementAt(i), currentPosition);
		        double _deltaY = deltaY(drawingPointsVector.elementAt(i), currentPosition);
		        if (_deltaX <= deltaX) {
		            deltaX = _deltaX;
		            _x = drawingPointsVector.elementAt(i).getX();
			    orthoX = _x;
		        }
		        if (_deltaY <= deltaY) {
		            deltaY = _deltaY;
		            _y = drawingPointsVector.elementAt(i).getY();
		            orthoY = _y;
		        }
		    }
		    drawingPointsVector.setElementAt(new Point2D.Double(_x, _y), drawingPointsVector.size() - 1);
	        }
	    }
	    
	    if (engineConfig.isSnapToGridActive()) {
	        //System.out.println((Math.floor(Math.log10(_newExtents))+1) + " - " + _newExtents);
	        //System.out.println(Math.pow(10,Math.floor(Math.log10(_scaleWidth / engineConfig.getDrawFactor()))-1));
	        double _firstXGridGap = engineConfig.xtoSpace(0)-engineConfig.xtoSpace(0)%_gridGap;
	        double _lastXGridGap = (engineConfig.xtoSpace(getWidth())-engineConfig.xtoSpace(getWidth())%_gridGap)+_gridGap;
	        double _firstYGridGap = engineConfig.ytoSpace(0)-engineConfig.ytoSpace(0)%_gridGap;
	        double _lastYGridGap = (engineConfig.ytoSpace(getWidth())-engineConfig.ytoSpace(getHeight())%_gridGap)+_gridGap;
	        double _actualXGridGap = _firstXGridGap;
	        while (_actualXGridGap <= _lastXGridGap) {
	            double _actualYGridGap = _firstYGridGap;
	            while (_actualYGridGap >= _lastYGridGap) {
	                /*Shape point = new Ellipse2D.Double(engineConfig.xtoPixel(_actualXGridGap), engineConfig.ytoPixel(_actualYGridGap), 1, 1);
	                _graphics2D.draw(point);*/
	                addOsnapPoint(new ESRIPoint(_actualXGridGap, _actualYGridGap));
	                _actualYGridGap -= _gridGap;
	            }
	            _actualXGridGap += _gridGap;
	        }
	    }
	    
	    if (engineConfig.isOsnapActive()) {
		if (drawingPointsVector.size() > 0) {
		    drawingPointsVector.setElementAt(currentPosition, drawingPointsVector.size() - 1);
		    int i = 0;
		    boolean found = false;
		    while (i < osnapPointsVector.size() && !found && _osnapTolerance > 0.1) {
			ESRIPoint xy = osnapPointsVector.elementAt(i);
			Point2D.Double _point = currentPosition;
			if ( xy.distance(_point) < _osnapTolerance ) {
			    _osnapTolerance = xy.distance(_point);
			    osnapRectangle = new Rectangle2D.Double(engineConfig.xtoPixel(xy.getX()) - osnapRectSize / 2, engineConfig.ytoPixel(xy.getY()) - osnapRectSize / 2, osnapRectSize, osnapRectSize);
			    drawingPointsVector.setElementAt(new Point2D.Double(xy.getX(), xy.getY()), drawingPointsVector.size() - 1);
			    currentPosition = new Point2D.Double(xy.getX(), xy.getY());
			}
			i++;
		    }

		    while (i < drawingPointsVector.size()-1 && !found && _osnapTolerance > 0.1) {
		        Point2D.Double xy = drawingPointsVector.elementAt(i);
		        Point2D.Double _point = currentPosition;
		        if ( xy.distance(_point) < _osnapTolerance ) {
		            _osnapTolerance = xy.distance(_point);
		            osnapRectangle = new Rectangle2D.Double(engineConfig.xtoPixel(xy.getX()) - osnapRectSize / 2, engineConfig.ytoPixel(xy.getY()) - osnapRectSize / 2, osnapRectSize, osnapRectSize);
		            drawingPointsVector.setElementAt(new Point2D.Double(xy.getX(), xy.getY()), drawingPointsVector.size() - 1);
		            currentPosition = new Point2D.Double(xy.getX(), xy.getY());
		        }
		        i++;
		    }
		} else {
		    drawingPointsVector.add(currentPosition);
		    for (int i = 0; i < osnapPointsVector.size(); i++) {
			ESRIPoint xy = osnapPointsVector.elementAt(i);
			Point2D.Double _point = currentPosition;
			if ( xy.distance(_point) < _osnapTolerance ) {
			    _osnapTolerance = xy.distance(_point);
			    osnapRectangle = new Rectangle2D.Double(engineConfig.xtoPixel(xy.getX()) - osnapRectSize / 2, engineConfig.ytoPixel(xy.getY()) - osnapRectSize / 2, osnapRectSize, osnapRectSize);
			    drawingPointsVector.setElementAt(new Point2D.Double(xy.getX(), xy.getY()), 0);
			    currentPosition = new Point2D.Double(xy.getX(), xy.getY());
			}
		    }
		}
	    }
	} else {
	    if (drawingPointsVector.size() > 0) {
	        drawingPointsVector.setElementAt(currentPosition, drawingPointsVector.size() - 1);
	    } else {
	        drawingPointsVector.add(currentPosition);
	    }
	}
    }

    private void bInfo_actionPerformed(ActionEvent e) {
	setOperation(BasicDrawEngineConfig.OPERATION_QUERY);
    }

    public void addLayerGroup(LayerGroup _layers) {
	layerGroups.add(_layers);
	for (int i = 0; i < _layers.size(); i++) {
	    addLayer((Layer)_layers.elementAt(i));
	}
    }

    public void addGeometrySets(Vector<GeometrySet> _geometrySetVector) {
	for (int i = 0; i < _geometrySetVector.size(); i++) {
	    addGeometrySet(_geometrySetVector.elementAt(i));
	}
    }

    public void addLayerGroups(Vector<LayerGroup> _layerGroupVector) {
	for (int i = 0; i < _layerGroupVector.size(); i++) {
	    addLayerGroup(_layerGroupVector.elementAt(i));
	}
    }

    public void addGeometrySet(GeometrySet _geometrySet) {
	if (_geometrySet.hasAccessPrivileges()) {
	    geometrySets.add(_geometrySet);
	    containedShapeIDS = new int[geometrySets.size()][0];
	    if (_geometrySet.getGeometrySetConfig().getServerURL().length() == 0 && _geometrySet.getGeometrySetConfig().isPropietary() && engineConfig.getPropietaryServerURL().length() > 0) {
		_geometrySet.getGeometrySetConfig().setConnectionParams(Base64Coder.decode(engineConfig.getPropietaryServerURL()), Base64Coder.decode(engineConfig.getPropietaryDatabase()), Base64Coder.decode(engineConfig.getPropietaryUser()), Base64Coder.decode(engineConfig.getPropietaryPassword()));
	    }
	} else {
	    System.out.println("No tiene privilegios de acceso al grupo de geometrías " + _geometrySet.getGeometrySetConfig().getSqlScheme() + "." + _geometrySet.getGeometrySetConfig().getSqlTable());
	}
    }

    public void addLayer(Layer _layer) {
	if (_layer != null) {
	    layers.add(_layer);
	    _layer.setParent(this);
	    _layer.setOsnapPointsVector(osnapPointsVector);
	    //Comentado para agilizar la carga
	    //_layer.getLayerConfig().getGeometryTypeFromSQL();
	    if (_layer.getLayerConfig().getAutoUpdateRateInSeconds() > 0) {
		startAutoUpdate(_layer);
	    }
	    if (_layer.isOn()) {
		_layer.load();
		repaint();
	    }
	}
    }

    public void removeLayer(String _name) {
	int i = 0;
	while (i < layers.size()) {
	    if (layers.elementAt(i).getAlias().equals(_name)) {
		layers.removeElementAt(i);
	    } else {
		i++;
	    }
	}
	repaint();
    }

    private void drawImageAttachments(Graphics2D _graphics2d, Layer _layer) {
	if (_layer.isOn()) {
	    for (int i = 0; i < _layer.getLayerConfig().getImageAttachments().size(); i++) {
		ImageAttachment _imageAttachment = _layer.getLayerConfig().getImageAttachments().elementAt(i);
		if (_imageAttachment.isOn()) {
		    BufferedImage _imageAttachmentImage = _layer.getImageAttachments().elementAt(i);
		    if (_imageAttachmentImage != null) {
		        _graphics2d.drawImage(_imageAttachmentImage, engineConfig.xtoPixel(_imageAttachment.getMinX()), engineConfig.ytoPixel(_imageAttachment.getMinY()), (int)(_imageAttachmentImage.getWidth() * _imageAttachment.getScale() * engineConfig.getDrawScale()), (int)(_imageAttachmentImage.getHeight() * _imageAttachment.getScale() * engineConfig.getDrawScale()), this);
			if (_imageAttachment == selectedImageAttachment) {
			    _graphics2d.setPaint(Color.RED);
			    _graphics2d.draw(_imageAttachment.getBounds(engineConfig));
			    Rectangle2D[] controlPoints = _imageAttachment.getControlPoints(engineConfig);
			    for (int j = 0; j < controlPoints.length; j++) {
			        _graphics2d.setPaint(controlPoints[j].contains(mousePosition)?Color.red:Color.blue);
			        _graphics2d.fill(controlPoints[j]);
			    }
			}
		    }
		}
	    }
	}
    }


     private void drawLayers(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, BasicDrawEngineConfig _engineConfig) {
	try {
	     if (engineConfig.isAntiAlias() && !mouseActive) {
		setAntiAlias(_graphics2D);
		setAntiAlias(_labelGraphics2D);
	    } else {
		unsetAntiAlias(_graphics2D);
		unsetAntiAlias(_labelGraphics2D);
	     }
	     for (int i = 0; i < layers.size(); i++) {
		 drawImageAttachments(_graphics2D, layers.elementAt(i));
	     }
	     BufferedImage _labelImage = new BufferedImage(engineConfig.getFWidth(), engineConfig.getFHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	     _labelGraphics2D = _labelImage.createGraphics();
	     for (int i = 0; i < geometrySets.size(); i++) {
		try {
		    GeometrySet _geometrySet = geometrySets.elementAt(i);
		    if (_geometrySet.isLoaded() && _geometrySet.getGeometrySetConfig().getBounds() != null) {
			drawGeometrySet(_graphics2D, _labelGraphics2D, _geometrySet, _engineConfig);
		    }
		} catch (Exception x) {
	            System.err.println("Error al dibujar el layer " + layers.elementAt(i).getName() + "\nError: " + x.getMessage());
		    continue;
	        }
	     }
	     _graphics2D.drawImage(_labelImage, 0, 0, _labelImage.getWidth(), _labelImage.getHeight(), this);
	     /*for (int i = 0; i < layers.size(); i++) {
	        try {
	            Layer _layer = layers.elementAt(i);
	            if (_layer.getGeometrySet().isLoaded()) {
	                drawLayer(_graphics2D, _labelGraphics2D, _layer, _engineConfig);
	            }
	        } catch (Exception x) {
	            //x.printStackTrace();
	            System.err.println("Error al dibujar el layer " + layers.elementAt(i).getName());
	        }
	     }*/
	 } catch (Exception x) {
	     x.printStackTrace();
	 }
     }

    private void drawGeometrySet(Graphics2D _graphics2d, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
	switch (_geometrySet.getGeometrySetConfig().getShapeType()) {
	    case ShapeTypes.POINT:
	    case ShapeTypes.MULTIPOINT:
		drawPoints(_graphics2d, _labelGraphics2D, _geometrySet, _engineConfig);
		break;
	    case ShapeTypes.POLYGON:
	    case ShapeTypes.MULTIPOLYGON:
		drawPolygons(_graphics2d, _labelGraphics2D, _geometrySet, _engineConfig);
		break;
	    case ShapeTypes.POLYLINE:
	    case ShapeTypes.MULTIPOLYLINE:
		drawPolylines(_graphics2d, _labelGraphics2D, _geometrySet, _engineConfig);
		break;
            default:
                System.out.println("GeometrySet " + _geometrySet.getGeometrySetConfig().getSqlScheme() + "." + _geometrySet.getGeometrySetConfig().getSqlTable() + " no tiene un ShapeType definido y no será dibujado");
                break;
	}
    }

    private void drawLayerForExport(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, Layer _layer, BasicDrawEngineConfig _engineConfig) {
	double _extents = _engineConfig.getExtents()/_engineConfig.getDrawScale();
	if (_engineConfig.isAntiAlias()) {
	    setAntiAlias(_graphics2D);
	    setAntiAlias(_labelGraphics2D);
	}
	if (_extents >= _layer.getLayerConfig().getMinScale() && _extents <= _layer.getLayerConfig().getMaxScale()) {
	    if (toSpace(getBounds(), _engineConfig).intersects(_layer.getGeometrySetConfig().getBounds())) {
		switch (_layer.getGeometrySetConfig().getShapeType()) {
		    case ShapeTypes.POINT:
		    case ShapeTypes.MULTIPOINT:
			drawPointsForExport(_graphics2D, _labelGraphics2D, _layer, _engineConfig);
			break;
		    case ShapeTypes.POLYGON:
		    case ShapeTypes.MULTIPOLYGON:
			drawPolygonsForExport(_graphics2D, _labelGraphics2D, _layer, _engineConfig);
			break;
		    case ShapeTypes.POLYLINE:
		    case ShapeTypes.MULTIPOLYLINE:
			drawPolylinesForExport(_graphics2D, _labelGraphics2D, _layer, _engineConfig);
			break;
		}
	    }
	}
    }

    private ESRIPoint[] toPointsArray(Vector _geometries) {
	Object[] _objects = _geometries.toArray();
	ESRIPoint[] _points = new ESRIPoint[_objects.length];
	for (int i = 0; i < _objects.length; i++) {
	    _points[i] = (ESRIPoint)_objects[i];
	}
	return _points;
    }

    private ESRIPolygon[] toPolygonsArray(Vector _geometries) {
	Object[] _objects = _geometries.toArray();
	ESRIPolygon[] _polygons = new ESRIPolygon[_objects.length];
	for (int i = 0; i < _objects.length; i++) {
	    _polygons[i] = (ESRIPolygon)_objects[i];
	}
	return _polygons;
    }

    private ESRIPolyline[] toPolylinesArray(Vector _geometries) {
	Object[] _objects = _geometries.toArray();
	ESRIPolyline[] _polylines = new ESRIPolyline[_objects.length];
	for (int i = 0; i < _objects.length; i++) {
	    _polylines[i] = (ESRIPolyline)_objects[i];
	}
	return _polylines;
    }

    private void setEnvironment() {
	if ((engineConfig.getFWidth() != getWidth()) || (engineConfig.getFHeight() != getHeight())) {
	    if (mapExtents.length == 2) {
		engineConfig.setFWidth(getWidth());
		engineConfig.setFHeight(getHeight());
		//System.out.println(Math.max(Math.max(mapExtents[0].getX(), mapExtents[1].getX()) - Math.min(mapExtents[0].getX(), mapExtents[1].getX()), Math.max(mapExtents[0].getY(), mapExtents[1].getY()) - Math.min(mapExtents[0].getY(), mapExtents[1].getY())));
	        //System.out.println(engineConfig.getFWidth() + "x" + engineConfig.getFHeight());
		engineConfig.setExtents(Math.max(Math.max(mapExtents[0].getX(), mapExtents[1].getX()) - Math.min(mapExtents[0].getX(), mapExtents[1].getX()), Math.max(mapExtents[0].getY(), mapExtents[1].getY()) - Math.min(mapExtents[0].getY(), mapExtents[1].getY())));
		engineConfig.setDrawFactor(Math.max(engineConfig.getFWidth(), engineConfig.getFHeight()) / engineConfig.getExtents());
		double centerXOffset = (getWidth()) / engineConfig.getDrawFactor();
		double centerYOffset = (getHeight()) / engineConfig.getDrawFactor();
		engineConfig.setDrawFactorOriginal(engineConfig.getDrawFactor());
		engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
		if (engineConfig.getXOffset() == 0) {
		    engineConfig.setXOffset(Math.min(mapExtents[0].getX(), mapExtents[1].getX()) + ((Math.max(mapExtents[0].getX(), mapExtents[1].getX()) - Math.min(mapExtents[0].getX(), mapExtents[1].getX())) / 2 - centerXOffset / 2));
		    engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
		    engineConfig.setXOffsetPosta(engineConfig.getXOffsetOriginal());
		}
		if (engineConfig.getYOffset() == 0) {
		    engineConfig.setYOffset(Math.min(mapExtents[0].getY(), mapExtents[1].getY()) + ((Math.max(mapExtents[0].getY(), mapExtents[1].getY()) - Math.min(mapExtents[0].getY(), mapExtents[1].getY())) / 2 - centerYOffset / 2));
		    engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
		    engineConfig.setYOffsetPosta(engineConfig.getYOffsetOriginal());
		}
	    } else {
		Vector<Layer> _layers = new Vector();
		for (int i = 0; i < layers.size(); i++) {
		    if (layers.elementAt(i).isOn()) {
			_layers.add(layers.elementAt(i));
		    }
		}
		if (_layers.size() > 0) {
		    double max = _layers.elementAt(0).getExtents();
		    engineConfig.setProjectionType(_layers.elementAt(0).getGeometrySetConfig().getProjectionType());
		    int index = 0;
		    for (int i = 1; i < _layers.size(); i++) {
			if (_layers.elementAt(i).getExtents() > max) {
			    index = i;
			}
		    }
		    Layer _layer = _layers.elementAt(index);
		    engineConfig.setFWidth(getWidth());
		    engineConfig.setFHeight(getHeight());
		    engineConfig.setExtents(_layer.getExtents());
		    engineConfig.setDrawFactor(Math.max(engineConfig.getFWidth(), engineConfig.getFHeight()) / engineConfig.getExtents());
		    double centerXOffset = (getWidth()) / engineConfig.getDrawFactor();
		    double centerYOffset = (getHeight()) / engineConfig.getDrawFactor();
		    engineConfig.setDrawFactorOriginal(engineConfig.getDrawFactor());
		    engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
		    //xOffset = _layer.getXMin() - centerOffset/2.6666667;
		    if (engineConfig.getXOffset() == 0) {
			engineConfig.setXOffset(_layer.getXMin() + ((_layer.getXMax() - _layer.getXMin()) / 2 - centerXOffset / 2));
		    }
		    engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
		    engineConfig.setXOffsetPosta(engineConfig.getXOffsetOriginal());
		    //Por qué?
		    if (engineConfig.getYOffset() == 0) {
			engineConfig.setYOffset(_layer.getYMin() + ((_layer.getYMax() - _layer.getYMin()) / 2 - centerYOffset / 2));
		    }
		    //yOffset = _layer.getYMin();
		    // + 1000;
		    engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
		    engineConfig.setYOffsetPosta(engineConfig.getYOffsetOriginal());
		}
	    }
	}

	double scale = engineConfig.xtoSpace(getWidth())-engineConfig.xtoSpace(0);
	int _zoomLevel = zoomLevel;
	if (scale < 10) {
	    _zoomLevel = 27;
	} else if (scale < 33) {
	    _zoomLevel = 26;
	} else if (scale < 66) {
	    _zoomLevel = 25;
	} else if (scale < 100) {
	    _zoomLevel = 24;
	} else if (scale < 200) {
	    _zoomLevel = 23;
	} else if (scale < 300) {
	    _zoomLevel = 22;
	} else if (scale < 400) {
	    _zoomLevel = 21;
	} else if (scale < 500) {
	    _zoomLevel = 20;
	} else if (scale < 600) {
	    _zoomLevel = 19;
	} else if (scale < 700) {
	    _zoomLevel = 18;
	} else if (scale < 700*Math.pow(2,1)) {
	    _zoomLevel = 17;
	} else if (scale < 700*Math.pow(2,2)) {
	    _zoomLevel = 16;
	} else if (scale < 700*Math.pow(2,3)) {
	    _zoomLevel = 15;
	} else if (scale < 700*Math.pow(2,4)) {
	    _zoomLevel = 14;
	} else if (scale < 700*Math.pow(2,5)) {
	    _zoomLevel = 13;
	} else if (scale < 700*Math.pow(2,6)) {
	    _zoomLevel = 12;
	} else if (scale < 700*Math.pow(2,7)) {
	    _zoomLevel = 11;
	} else if (scale < 700*Math.pow(2,8)) {
	    _zoomLevel = 10;
	} else if (scale < 700*Math.pow(2,9)) {
	    _zoomLevel = 9;
	} else if (scale < 700*Math.pow(2,10)) {
	    _zoomLevel = 8;
	} else if (scale < 700*Math.pow(2,11)) {
	    _zoomLevel = 7;
	} else if (scale < 700*Math.pow(2,12)) {
	    _zoomLevel = 6;
	} else if (scale < 700*Math.pow(2,13)) {
	    _zoomLevel = 5;
	} else if (scale < 700*Math.pow(2,14)) {
	    _zoomLevel = 4;
	} else if (scale < 700*Math.pow(2,15)) {
	    _zoomLevel = 3;
	} else if (scale < 700*Math.pow(2,16)) {
	    _zoomLevel = 2;
	} else {
	    _zoomLevel = 1;
	}
	if (_zoomLevel != zoomLevel) {
	    zoomLevel = _zoomLevel;
	    drawRasterImageVector();

/*
	      // Implementación de prueba para rasterización del mapa
	      // a ver si mejoramos el rendimiento del motor eh?
	      double minX = -1;
	      double minY = -1;
	      double maxX = -1;
	      double maxY = -1;
	      for (int i = 0; i < layers.size(); i++)  {
	         Rectangle2D _bounds = layers.elementAt(i).getLayerConfig().getBounds();
	         if (_bounds != null) {
	             if ((minX == -1 || _bounds.getMinX() < minX) && _bounds.getMinX() != 0.0) {
	                 minX = _bounds.getMinX();
	             }
	             if ((minY == -1 || _bounds.getMinY() < minY) && _bounds.getMinY() != 0.0) {
	                 minY = _bounds.getMinY();
	             }
	             if ((maxX == -1 || _bounds.getMaxX() > maxX) && _bounds.getMaxX() != 0.0) {
	                 maxX = _bounds.getMaxX();
	             }
	             if ((maxY == -1 || _bounds.getMaxY() > maxY) && _bounds.getMaxY() != 0.0) {
	                 maxY = _bounds.getMaxY();
	             }
	         }
	      }


	     //System.out.println("Zoom level: " + _zoom);
	     int _minX = engineConfig.xtoPixel(minX);
	     int _minY = engineConfig.ytoPixel(minY);
	     int _maxX = engineConfig.xtoPixel(maxX);
	     int _maxY = engineConfig.ytoPixel(maxY);
	     
	     //rasterImage = new BufferedImage(_maxX - _minX, _maxY - _minY, BufferedImage.TYPE_4BYTE_ABGR);

	    double _rectWidth = maxX - minX;
	    double _rectHeight = maxY - minY;
	    for (int i = 0; i < 27 ; i++)  { // zoomlevels
		if (rasterImageMatrix3[i] == null) {
		    rasterImageMatrix3[i] = new RasterImage[i][i];
		}
	    }

	    for (int i = 0; i < rasterImageMatrix3[zoomLevel].length; i++)  {
		for (int j = 0; j < rasterImageMatrix3[zoomLevel][i].length; j++)  {
		    if (rasterImageMatrix3[zoomLevel][i][j] == null) {
		        rasterImageMatrix3[zoomLevel][i][j] = new RasterImage(getBounds().width, getBounds().height, RasterImage.TYPE_4BYTE_ABGR);
		        rasterImageMatrix3[zoomLevel][i][j].setBounds(new Rectangle2D.Double(_minX + i * (int)(_rectWidth * engineConfig.drawFactor / zoomLevel), _maxY + j * (int)(_rectHeight * engineConfig.drawFactor / zoomLevel), (int)(_rectWidth * engineConfig.drawFactor / zoomLevel), (int)(_rectHeight * engineConfig.drawFactor / zoomLevel)));
		    }
		}
		
	    }
	     //System.out.println("zoom: " + _zoom + ",minX: " + _minX + ", maxX: " + _maxX + ", width: " + (int)(_rectWidth*engineConfig.drawFactor/_zoom) + ", height: " + (int)(_rectHeight*engineConfig.drawFactor/_zoom));
	     //System.out.println(_rectWidth/_zoom);
	     //_graphics2D.fillRect(_minX, _maxY,  _rectWidth, _rectHeight);
	     //System.out.println("minX: " + minX + "minY: " + minY + "maxX: " + maxX + "maxY: " + maxY);
*/
	} else {
	 //   repaint();
	}
	saveEnvironment();

    }

    public void setPixelEnvironment(Rectangle2D _bounds) {
	double _width = engineConfig.xtoSpace((int)_bounds.getMaxX()) - engineConfig.xtoSpace((int)_bounds.getMinX());
	double _height = engineConfig.ytoSpace((int)_bounds.getMaxY()) - engineConfig.ytoSpace((int)_bounds.getMinY());
	double _extents = Math.max(_width, _height);
	if (_extents < 10.001) {
	    _extents = 10.001;
	}
	/*
	if (_extents > 900.001) {
	    _extents = 900.001;
	}*/
	double _drawFactor = (this.getHeight() - 20) / _extents;
	double _drawScale = _drawFactor / engineConfig.getDrawFactorOriginal();
	double minX = engineConfig.xtoSpace((int)_bounds.getMinX());
	double minY = engineConfig.ytoSpace((int)_bounds.getMaxY());
	flyTo(_drawScale, minX, minY);
    }
    
    private void goTo(double _drawScale, double _xOffset, double _yOffset) {
	engineConfig.setDrawScale(_drawScale);
	engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
	engineConfig.setXOffset(_xOffset);
	engineConfig.setYOffset(_yOffset);
	repaint();
    }

    public void setSpaceEnvironment(Rectangle2D _bounds, boolean _flying) {
	double _extents = Math.max(_bounds.getWidth(), _bounds.getHeight());
	_extents = _extents * 4.0;
	if (_extents < 10.001) {
	    _extents = 10.001;
	}
	/*
	if (_extents > 900.001) {
	    _extents = 900.001;
	}
	*/
	double _drawFactor = (this.getHeight() - 20) / _extents;
	double _drawScale = _drawFactor / engineConfig.getDrawFactorOriginal();
	double minX = _bounds.getMinX() - (getWidth() / _drawFactor) / 2.0 + (_bounds.getWidth() / 2.0);
	//double minX = (getWidth()*_drawScale)/2 - _bounds.getWidth()/2;
	//minX = _bounds.getMinX() + _bounds.getWidth()/2;
	//double minY = _bounds.getMinY();
	double minY = _bounds.getMinY() - (getHeight() / _drawFactor) / 2.0 + (_bounds.getHeight() / 2.0);
	if (_flying) {
	    flyTo(_drawScale, minX, minY);
	} else {
	    goTo(_drawScale, minX, minY);
	}
    }

    private void flyTo(double _drawScale, double _xOffset, double _yOffset) {
	final double _scale = _drawScale;
	final double _xOff = _xOffset;
	final double _yOff = _yOffset;
	Thread drawScaleThread = new Thread();
	drawScaleThread = new Thread(new Runnable() {

			public void run() {
			    int sleep = 12;
			    if (engineConfig.getDrawScale() < _scale) {
				while ((engineConfig.getDrawScale() * scaleFactor) < _scale) {
				    setMouseActive(true);
				    try {
					engineConfig.setDrawScale(engineConfig.getDrawScale() * scaleFactor);
					engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
					repaint();
					Thread.currentThread().sleep(sleep);
				    } catch (InterruptedException e) {
					System.out.println(e);
				    }
				}
			    } else {
				while ((engineConfig.getDrawScale() / scaleFactor) > _scale) {
				    setMouseActive(true);
				    try {
					engineConfig.setDrawScale(engineConfig.getDrawScale() / scaleFactor);
					engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
					repaint();
					Thread.currentThread().sleep(sleep);
				    } catch (InterruptedException e) {
					System.out.println(e);
				    }
				}
			    }
			    engineConfig.setDrawScale(_scale);
			    engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
			    //mouseActive = false;
			    drawRasterImageVector();
			}

		    });
	Thread xOffsetThread = new Thread();
	xOffsetThread = new Thread(new Runnable() {

			public void run() {
			    double xoffsetFactor = (engineConfig.getXOffset() <= _xOff) ? (_xOff - engineConfig.getXOffset()) / 2 : (engineConfig.getXOffset() - _xOff) / 2;
			    int sleep = 12;
			    try {
				if (engineConfig.getXOffset() < _xOff) {
				    //while (xOffset / _xOff > 1.0005) {
				    while (engineConfig.getXOffset() < _xOff) {
				        setMouseActive(true);
					if ((engineConfig.getXOffset() + xoffsetFactor) < _xOff) {
					    engineConfig.setXOffset(engineConfig.getXOffset() + xoffsetFactor);
					} else {
					    engineConfig.setXOffset(_xOff);
					}
					xoffsetFactor = (_xOff - engineConfig.getXOffset()) / 2;
					if (xoffsetFactor < 0.01)
					    xoffsetFactor = 0.01;
					engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
					Thread.currentThread().sleep(sleep);
				    }
				} else {
				    //while (_xOff / xOffset > 1.0005) {
				    while (engineConfig.getXOffset() > _xOff) {
				        setMouseActive(true);
					if ((engineConfig.getXOffset() - xoffsetFactor) > _xOff) {
					    engineConfig.setXOffset(engineConfig.getXOffset() - xoffsetFactor);
					} else {
					    engineConfig.setXOffset(_xOff);
					}
					xoffsetFactor = (engineConfig.getXOffset() - _xOff) / 2;
					if (xoffsetFactor < 0.01)
					    xoffsetFactor = 0.01;
					engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
					Thread.currentThread().sleep(sleep);
				    }
				}
			        //mouseActive = false;
			        drawRasterImageVector();
			    } catch (InterruptedException e) {
				System.out.println(e);
			    }
			}

		    });
	Thread yOffsetThread = new Thread();
	yOffsetThread = new Thread(new Runnable() {

			public void run() {
			    double yoffsetFactor = (engineConfig.getYOffset() <= _yOff) ? (_yOff - engineConfig.getYOffset()) / 2 : (engineConfig.getYOffset() - _yOff) / 2;
			    int sleep = 12;
			    try {
				if (engineConfig.getYOffset() < _yOff) {
				    //while (yOffset / _yOff > 1.0005) {
				    while (engineConfig.getYOffset() < _yOff) {
				        setMouseActive(true);
					if ((engineConfig.getYOffset() + yoffsetFactor) < _yOff) {
					    engineConfig.setYOffset(engineConfig.getYOffset() + yoffsetFactor);
					} else {
					    engineConfig.setYOffset(_yOff);
					}
					yoffsetFactor = (_yOff - engineConfig.getYOffset()) / 2;
					if (yoffsetFactor < 0.01)
					    yoffsetFactor = 0.01;
					engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
					Thread.currentThread().sleep(sleep);
				    }
				} else {
				    //while (_yOff / yOffset > 1.0005) {
				    while (engineConfig.getYOffset() > _yOff) {
				        setMouseActive(true);
					if ((engineConfig.getYOffset() - yoffsetFactor) > _yOff) {
					    engineConfig.setYOffset(engineConfig.getYOffset() - yoffsetFactor);
					} else {
					    engineConfig.setYOffset(_yOff);
					}
					yoffsetFactor = (engineConfig.getYOffset() - _yOff) / 2;
					if (yoffsetFactor < 0.01)
					    yoffsetFactor = 0.01;
					engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
					Thread.currentThread().sleep(sleep);
				    }
				}
			        //mouseActive = false;
			        drawRasterImageVector();
			    } catch (InterruptedException e) {
				System.out.println(e);
			    }
			}

		    });
	xOffsetThread.start();
	yOffsetThread.start();
	drawScaleThread.start();
    }

    public void setPolygonEnvironment(String _layer, int _id, Color _color, boolean _flying) {
	foundGeometryID = _id;
	foundLayer = _layer;
	try {
	    //--getLayer(foundLayer).getGeometrySet().getPolygon(foundGeometryID).setFillColor(_color);
	    setSpaceEnvironment(getLayer(foundLayer).getGeometrySet().getPolygon(foundGeometryID).getBounds(), _flying);
	} catch (NullPointerException x) {
	    Advisor.messageBox("No se ha encontrado el polígono", "Aviso");
	}
    }

    public void setPointEnvironment(Point2D.Double _point, int _pointDiameter, boolean _flying) {
	Rectangle2D _bounds = new Rectangle2D.Double(_point.getX() - _pointDiameter/2.0,
						     _point.getY() - _pointDiameter/2.0,
						     _pointDiameter, _pointDiameter);
	setSpaceEnvironment(_bounds, _flying);
    }

    public void setPolygonEnvironment(Layer _layer, int _id, Color _color, boolean _flying) {
	foundGeometryID = _id;
	try {
	    //--_layer.getGeometrySet().getPolygon(foundGeometryID).setFillColor(_color);
	    setSpaceEnvironment(_layer.getGeometrySet().getPolygon(foundGeometryID).getBounds(), _flying);
	} catch (NullPointerException x) {
	    Advisor.messageBox("No se ha encontrado el polígono", "Aviso");
	}
    }

    private void drawPoints(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++)  {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++)  {
		if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPoint[] _points = toPointsArray(_geometrySet.getGeometriesFromMatrix(m,n));
		    int _pointDiameter = _geometrySet.getGeometrySetConfig().getPointDiameter();
		    for (int i = 0; i < _points.length; i++) {
			Vector<Layer> _layers = _geometrySet.getLayers();
			int j = _layers.size()-1;
			boolean _drawn = false;
			while (j >= 0 && !_drawn)  {
			    if (/*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
				Layer _layer = _layers.elementAt(j);
				if ((_pointDiameter * _engineConfig.getDrawFactor() >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _pointDiameter * _engineConfig.getDrawFactor() > 2)) && (_pointDiameter * _engineConfig.getDrawFactor() <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
				    boolean isSelectedLayer = (_layer == selectedLayer);
				    boolean _drawPoint = _layer.drawGeometries();
				    int _symbol = _layer.getSymbol();
				    StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
				    int _idFilterMatch = _layer.getFilterValue(_points[i].getIdPoint());
				    if (_idFilterMatch != -1) { //override original StyleConfig
					try {
					    LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
					    _styleConfig = _filter.getStyleConfig();
					    _drawPoint = _filter.isActive() && _layer.drawGeometries();
					} catch (NullPointerException e) {
					}
				    }
		    
				    if (getBounds().contains(_engineConfig.toPixel(_points[i]))) {
					Shape _point = new Ellipse2D.Double(_engineConfig.xtoPixel(_points[i].getX() - (double)_pointDiameter / 2.0), _engineConfig.ytoPixel(_points[i].getY() + (double)_pointDiameter / 2.0), ((double)_pointDiameter * _engineConfig.getDrawFactor()), ((double)_pointDiameter * _engineConfig.getDrawFactor()));
					if (/*_layer.drawGeometries() || */_drawPoint || isSelectedLayer) {
					    if (mouseActive && !rasterImageMatrixMode) {
					        if (_styleConfig.getOutlineColor() != null) {
					            _graphics2D.setColor(_styleConfig.getOutlineColor());
					            _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
					            _graphics2D.draw(_point);
					            _drawn = true;
					        } else {
						    if (_styleConfig.getFillColor() != null) {
						        _graphics2D.setColor(_styleConfig.getFillColor());
						        //_graphics2D.setStroke(new BasicStroke(_styleConfig.getLineWidth()));
						        _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
						        _graphics2D.draw(_point);
						        _drawn = true;
						    }
						}
					    } else {
					        if (_points[i].getSymbol() != -1) {
					            _symbol = _points[i].getSymbol();
					        }
					        _symbol = _styleConfig.getSymbol();
					        if (_symbol != -1) {
                                                    /**
                                                     * En caso de que el punto (prioridad cero)
                                                     * o el layer (prioridad uno)
                                                     * tengan alguna imagen asociada, se dibuja
                                                     * la imagen con el punto como centro
                                                     */
					            //BufferedImage imagen = LibIMG.scale((double)25 / (double)_layer.getSymbol(symbol).getWidth(this), _layer.getSymbol(symbol));
					            BufferedImage imagen = _engineConfig.getSymbol(_symbol);
					            int width = (int)(imagen.getWidth(this) / 1 * _engineConfig.getDrawFactor());
					            int height = (int)(imagen.getHeight(this) / 1 * _engineConfig.getDrawFactor());
					            width = (int)(imagen.getWidth(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
					            height = (int)(imagen.getHeight(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
					            if (width > 0 || height > 0) {
					                int centX = _engineConfig.xtoPixel(_points[i].getX()) - (width / 2);
					                int centY = _engineConfig.ytoPixel(_points[i].getY()) - (height / 2);
					                _graphics2D.drawImage(imagen, centX, centY, width, height, this);
					            }
                                                } else {
                                                    if (_styleConfig.getFillColor() != null) {
                                                        _graphics2D.setColor(_styleConfig.getFillColor());
                                                        
                                                        /*
                                                        Paint p = new RadialGradientPaint(_engineConfig.toPixel(_points[i]), getWidth() / 2.0f,
                                                                        new float[] { 0.0f, 1.0f },
                                                                        new Color[] { new Color(_styleConfig.getFillColor().getRed(),_styleConfig.getFillColor().getGreen(),_styleConfig.getFillColor().getBlue(), 255),
                                                                            new Color(0, 0, 255, 50*255/100) });
                                                        _graphics2d.setPaint(p);*/
                                                        _graphics2D.fill(_point);
                                                    }
                                                    if (_styleConfig.getOutlineColor() != null) {
                                                        _graphics2D.setColor(_styleConfig.getOutlineColor());
                                                        _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
                                                        _graphics2D.draw(_point);
                                                        _drawn = true;
                                                    }
                                                }
					    }
					    addOsnapPoint(_points[i]);
                                            if (mouseActive && !rasterImageMatrixMode) {
                                            } else {
                                                int[] _containedShapes = containedShapeIDS[geometrySets.indexOf(_geometrySet)];
                                                for (int k = 0; k < _containedShapes.length; k++)  {
                                                    if (_containedShapes[k] == _points[i].getIdPoint()) {
                                                        if (_styleConfig.getMouseOverColor() != null) {
                                                            _graphics2D.setColor(_styleConfig.getMouseOverColor());
                                                            Composite originalComposite = _graphics2D.getComposite();
                                                            _graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                                                            _graphics2D.fill(_point);
                                                            _graphics2D.setComposite(originalComposite);
                                                        }
                                                        if (_styleConfig.getMouseOverOutlineColor() != null) {
                                                            _graphics2D.setColor(_styleConfig.getMouseOverOutlineColor());
                                                            _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
                                                            _graphics2D.draw(_point);
                                                            _drawn = true;
                                                        }
                                                    }
                                                }
                                                if (foundGeometryID == i && _layer.getAlias().equals(foundLayer)) {
                                                    if (_styleConfig.getSelectedColor() != null) {
                                                        _graphics2D.setColor(_styleConfig.getSelectedColor());
                                                        _graphics2D.fill(_point);
                                                    }
                                                    if (_styleConfig.getSelectedOutlineColor() != null) {
                                                        _graphics2D.setColor(_styleConfig.getSelectedOutlineColor());
                                                        _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
                                                        _graphics2D.draw(_point);
                                                        _drawn = true;
                                                    }
                                                }
                                            }
                                        }
				        String _label = _layer.getLabelValue(_points[i].getIdPoint());
				        if (_label.length()>0 && _layer.getLayerConfig().paintLabels()) {
				            Font _originalFont = _styleConfig.getLabelFont();
				            Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100 ? 100 : (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
				            if (_labelFont.getSize() > 6) {
				                _labelGraphics2D.setFont(_labelFont);
				                _labelGraphics2D.setColor(_styleConfig.getLabelColor());
				                Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
				                _labelGraphics2D.drawString(_label, _engineConfig.xtoPixel(_points[i].getX()) - (int)(metricBounds.getWidth() / 2.0), _engineConfig.ytoPixel(_points[i].getY()) + (int)(metricBounds.getHeight() / 4.0));
				            }
				        }
				    }
				}
			    }
			    j--;
			}
		    }
		}
	    }
	}
    }

    private void drawPointsForExport(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, Layer _layer, BasicDrawEngineConfig _engineConfig) {
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	GeometrySet _geometrySet = _layer.getGeometrySet();
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
		if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPoint[] _points = toPointsArray(_geometrySet.getGeometriesFromMatrix(m, n));
		    int _pointDiameter = _geometrySet.getGeometrySetConfig().getPointDiameter();
		    for (int i = 0; i < _points.length; i++) {
			boolean _drawn = false;
			if ((_pointDiameter * _engineConfig.getDrawFactor() >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _pointDiameter * _engineConfig.getDrawFactor() > 2)) && (_pointDiameter * _engineConfig.getDrawFactor() <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
			    int _symbol = _layer.getSymbol();
			    StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
			    int _idFilterMatch = _layer.getFilterValue(_points[i].getIdPoint());
			    if (_idFilterMatch != -1) { //override original StyleConfig
				try {
				    LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
				    _styleConfig = _filter.getStyleConfig();
				} catch (NullPointerException e) {
				}
			    }

			    if (getBounds().contains(_engineConfig.toPixel(_points[i]))) {
				Shape _point = new Ellipse2D.Double(_engineConfig.xtoPixel(_points[i].getX() - (double)_pointDiameter / 2.0), _engineConfig.ytoPixel(_points[i].getY() + (double)_pointDiameter / 2.0), ((double)_pointDiameter * _engineConfig.getDrawFactor()), ((double)_pointDiameter * _engineConfig.getDrawFactor()));
				//FORCE
				if (true) {
				    if (_points[i].getSymbol() != -1) {
					_symbol = _points[i].getSymbol();
				    }
				    _symbol = _styleConfig.getSymbol();
				    if (_symbol != -1) {
					BufferedImage imagen = _engineConfig.getSymbol(_symbol);
					int width = (int)(imagen.getWidth(this) / 1 * _engineConfig.getDrawFactor());
					int height = (int)(imagen.getHeight(this) / 1 * _engineConfig.getDrawFactor());
					width = (int)(imagen.getWidth(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
					height = (int)(imagen.getHeight(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
					if (width > 0 || height > 0) {
					    int centX = _engineConfig.xtoPixel(_points[i].getX()) - (width / 2);
					    int centY = _engineConfig.ytoPixel(_points[i].getY()) - (height / 2);
					    _graphics2D.drawImage(imagen, centX, centY, width, height, this);
					}
				    } else {
					if (_styleConfig.getFillColor() != null) {
					    _graphics2D.setColor(_styleConfig.getFillColor());
					    _graphics2D.fill(_point);
					}
					if (_styleConfig.getOutlineColor() != null) {
					    _graphics2D.setColor(_styleConfig.getOutlineColor());
					    _graphics2D.setStroke(new BasicStroke(3));
					    _graphics2D.draw(_point);
					    _drawn = true;
					}
				    }
				    if (foundGeometryID == i && _layer.getAlias().equals(foundLayer)) {
					if (_styleConfig.getSelectedColor() != null) {
					    _graphics2D.setColor(_styleConfig.getSelectedColor());
					    _graphics2D.fill(_point);
					}
					if (_styleConfig.getSelectedOutlineColor() != null) {
					    _graphics2D.setColor(_styleConfig.getSelectedOutlineColor());
					    _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
					    _graphics2D.draw(_point);
					    _drawn = true;
					}
				    }
				}
				String _label = _layer.getLabelValue(_points[i].getIdPoint());
				if (_label.length() > 0) {
				    Font _originalFont = _styleConfig.getLabelFont();
				    Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100?100:(int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
				    //FORCE
				    if (_labelFont.getSize() > 6 || true) {
					_labelGraphics2D.setFont(_labelFont);
					_labelGraphics2D.setColor(_styleConfig.getLabelColor());
					Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
					_labelGraphics2D.drawString(_label, _engineConfig.xtoPixel(_points[i].getX()) - (int)(metricBounds.getWidth() / 2.0), _engineConfig.ytoPixel(_points[i].getY()) + (int)(metricBounds.getHeight() / 4.0));
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    private void drawPolygons(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++)  {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++)  {
		if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPolygon[] _polygons = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m,n));
		    for (int i = 0; i < _polygons.length; i++) {
			Vector<Layer> _layers = _geometrySet.getLayers();
			int j = _layers.size()-1;
			boolean _drawn = false;
			while (j >= 0 && !_drawn)  {
			    if (/*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
				Layer _layer = _layers.elementAt(j);
			        boolean isSelectedLayer = (_layer == selectedLayer);
			        boolean _drawPolygon = _layer.drawGeometries();
			        boolean _selectPolygon = false;
				if (_layer.isQueryable() || (_layer == selectedLayer)) {
				    _selectPolygon = true;
				}
			        int _symbol = _layer.getSymbol();
			        StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
			        int _idFilterMatch = _layer.getFilterValue(_polygons[i].getIdPolygon());
			        if (_idFilterMatch != -1) {
			            try {
			                LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
			                _styleConfig = _filter.getStyleConfig();
			                _drawPolygon = _filter.isActive() && _layer.drawGeometries();
			            } catch (NullPointerException e) {
			            }
			        }
				if (_bounds.intersects(_polygons[i].getBounds2D())) {// && _polygons[i].getBounds2D().getWidth()*_engineConfig.getDrawFactor()>10) {
				    //double _width = _polygons[i].getBounds2D().getWidth() * _engineConfig.getDrawFactor();
				    //double _heigth = _polygons[i].getBounds2D().getHeight() * _engineConfig.getDrawFactor();
				    if (true) { //((_width >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _width > 2)) && (_heigth >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _heigth > 2)) && (_width <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0) && (_heigth <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
					if (/*_layer.drawGeometries() || */_drawPolygon || isSelectedLayer) {
					    ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
					    if (_engineConfig.isOsnapActive()) {
					        if (_polygon.getBounds().contains(mousePosition)) {
					            addOsnapPoints(_polygons[i]);
					        }
					    }
					    if (mouseActive && !rasterImageMatrixMode) {
						if (_polygon.getBounds2D().getWidth()> 20 && _polygon.getBounds2D().getHeight()> 20) {
						    _graphics2D.setColor(_styleConfig.getFillColor()!=null?_styleConfig.getFillColor():_styleConfig.getOutlineColor());
						    _graphics2D.setStroke(new BasicStroke(1));
						    _graphics2D.draw(_polygon);
						    _drawn = true;
						}
					    } else {
						if (_styleConfig.getFillColor() != null) {
						    _graphics2D.setColor(_styleConfig.getFillColor());
						    _graphics2D.fill(_polygon);
						    _drawn = true;
						}
						if (_styleConfig.getOutlineColor() != null) {
						    _graphics2D.setColor(_styleConfig.getOutlineColor());
						    _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
						    _graphics2D.draw(_polygon);
						    _drawn = true;
						}
						if (_polygons[i].getSymbol() != -1) {
						    _symbol = _polygons[i].getSymbol();
						}
						if (_symbol != -1) {
						    //BufferedImage imagen = LibIMG.scale((double)25 / (double)_layer.getSymbol(symbol).getWidth(this), _layer.getSymbol(symbol));
						    BufferedImage imagen = _engineConfig.getSymbol(_symbol);
						    int width = (int)(imagen.getWidth(this) / 10 * _engineConfig.getDrawFactor());
						    int height = (int)(imagen.getHeight(this) / 10 * _engineConfig.getDrawFactor());
						    if (width > 20 || height > 20) {
							int centX = _engineConfig.xtoPixel(_polygons[i].getCentroid().getX()) - (width / 2);
							int centY = _engineConfig.ytoPixel(_polygons[i].getCentroid().getY()) - (height / 2);
							_graphics2D.drawImage(imagen, centX, centY, width, height, this);
						    }
						}
					    }
					} //fi layer.drawGeometries()
					String _label = _layer.getLabelValue(_polygons[i].getIdPolygon());
					if (true) { //!mouseActive || rasterImageMatrixMode) {
					    if (_label.length() > 0 && _layer.getLayerConfig().paintLabels() && _drawPolygon) {
                                                Font _originalFont = _styleConfig.getLabelFont();
					        Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100?100: (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
						if (_labelFont.getSize() > 6) {
						    _labelGraphics2D.setFont(_labelFont);
						    _labelGraphics2D.setColor(_styleConfig.getLabelColor());
						    Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
						    double x = _engineConfig.xtoPixel(_polygons[i].getCentroid().getX()) - (int)(metricBounds.getWidth() / 2.0);
						    double y = _engineConfig.ytoPixel(_polygons[i].getCentroid().getY()) + (int)(metricBounds.getHeight() / 4.0);
						    if (!getBounds().contains(x, y)) {
							/*
						        if (_polygons[i].contains(_engineConfig.toSpace(new Point2D.Double(getBounds().getWidth()/2.0 - (int)(metricBounds.getWidth() / 2.0), getBounds().getHeight()/2.0 + (int)(metricBounds.getHeight() / 4.0))))) {
						            x = (int)(getBounds().getWidth()/2.0) - (int)(metricBounds.getWidth() / 2.0);
						            y = (int)(getBounds().getHeight()/2.0) + (int)(metricBounds.getHeight() / 4.0);
						        }*/
							ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
							Rectangle _totalBounds = getBounds();
						        if (_polygon.contains(_totalBounds.getWidth()/2.0, _totalBounds.getHeight()/2.0)) {
							    x = _totalBounds.getWidth()/2.0 - metricBounds.getWidth();
							    y = _totalBounds.getHeight()/2.0 + metricBounds.getHeight() / 2.0;
							} else if (_polygon.contains(_totalBounds.getMinX(), _totalBounds.getMinY())) {
							    x = _totalBounds.getMinX() + 10;
							    y = _totalBounds.getMinY() + metricBounds.getHeight();
							} else if (_polygon.contains(_totalBounds.getMaxX(), _totalBounds.getMinY())) {
							    x = _totalBounds.getMaxX() - metricBounds.getWidth();
							    y = _totalBounds.getMinY() + metricBounds.getHeight();
							}
							//Comentado porque no vale la pena intentar dibujarlo en la zona que está tapada por el logo de la organizacion
							else if (_polygon.contains(_totalBounds.getMinX(), _totalBounds.getMaxY()) && !_engineConfig.paintLogo()) {
							    x = _totalBounds.getMinX() + 10;
							    y = _totalBounds.getMaxY() - 10;
							}
							//Comentado porque no vale la pena intentar dibujarlo en la zona que está tapada por el panel de búsqueda catastral
							 else if (_polygon.contains(_totalBounds.getMaxX(), _totalBounds.getMaxY()) && bottomRightComponent != null) {
							    if (!bottomRightComponent.isVisible()) {
								x = _totalBounds.getMaxX() - metricBounds.getWidth();
								y = _totalBounds.getMaxY() - 10;
							    }
							}
						    }
						    _labelGraphics2D.drawString(_label, (int)x, (int)y);
						}
					    }
					    if (foundGeometryID == _polygons[i].getIdPolygon() && _layer.getAlias().equals(foundLayer)) {
						ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
						_graphics2D.setColor(_styleConfig.getSelectedColor() != null?_styleConfig.getSelectedColor(): Color.orange);//--_polygon.getSelectedColor());
						_graphics2D.fill(_polygon);
						_graphics2D.setColor(_styleConfig.getSelectedOutlineColor() != null?_styleConfig.getSelectedOutlineColor(): Color.orange);//--_polygon.getSelectedColor());
						_graphics2D.setStroke(new BasicStroke(_styleConfig.getLineWidth()));
						_graphics2D.draw(_polygon);
					        _drawn = true;
					    }
					}
				    }
				} //fi _bounds.intersects
				if (_selectPolygon) {
				    int[] _containedShapes = containedShapeIDS[geometrySets.indexOf(_geometrySet)];
				    for (int k = 0; k < _containedShapes.length; k++)  {
				        if (_containedShapes[k] == _polygons[i].getIdPolygon()) {
				            ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
				            _graphics2D.setColor(Color.GRAY);
				            Composite originalComposite = _graphics2D.getComposite();
				            _graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
				            _graphics2D.fill(_polygon);
				            _graphics2D.setComposite(originalComposite);
				            _graphics2D.setStroke(new BasicStroke(1));
				            _graphics2D.setColor(Color.RED);
				            _graphics2D.draw(_polygon);
				         }
				    }
				}
			    }//fi contains
			    j--;
			}
		    }
		}
	    }
	}
    }

    private void drawPolygonsForExport(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, Layer _layer, BasicDrawEngineConfig _engineConfig) {
	//FUERZA EL DIBUJADO DE polígonos, etiquetas y colores
	//NO PINTA el polígono seleccionado con el mouse
	//FUERZA EL PINTADO DE foundGeometryID
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	GeometrySet _geometrySet = _layer.getGeometrySet();
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
		if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPolygon[] _polygons = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m, n));
		    for (int i = 0; i < _polygons.length; i++) {
			boolean _drawn = false;
			//FORCE
			int _symbol = _layer.getSymbol();
			StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
			int _idFilterMatch = _layer.getFilterValue(_polygons[i].getIdPolygon());
			if (_idFilterMatch != -1) {
			    try {
				LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
				_styleConfig = _filter.getStyleConfig();
			    } catch (NullPointerException e) {
			    }
			}
			if (true) {
			    //FORCE
			    if (true) {
				//FORCE
				if (true) {
				    ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
				    if (_styleConfig.getFillColor() != null) {
					_graphics2D.setColor(_styleConfig.getFillColor());
					_graphics2D.fill(_polygon);
					_drawn = true;
				    }
				    if (_styleConfig.getOutlineColor() != null) {
					_graphics2D.setColor(_styleConfig.getOutlineColor());
					_graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
					_graphics2D.setStroke(new BasicStroke(3));
					_graphics2D.draw(_polygon);
					_drawn = true;
				    }
				    if (_polygons[i].getSymbol() != -1) {
					_symbol = _polygons[i].getSymbol();
				    }
				    if (_symbol != -1) {
					BufferedImage imagen = _engineConfig.getSymbol(_symbol);
					int width = (int)(imagen.getWidth(this) / 10 * _engineConfig.getDrawFactor());
					int height = (int)(imagen.getHeight(this) / 10 * _engineConfig.getDrawFactor());
					if (width > 20 || height > 20) {
					    int centX = _engineConfig.xtoPixel(_polygons[i].getCentroid().getX()) - (width / 2);
					    int centY = _engineConfig.ytoPixel(_polygons[i].getCentroid().getY()) - (height / 2);
					    _graphics2D.drawImage(imagen, centX, centY, width, height, this);
					}
				    }
				} //fi layer.drawGeometries()
				String _label = _layer.getLabelValue(_polygons[i].getIdPolygon());
				if (true) {
				    if (_label.length() > 0) {
					Font _originalFont = _styleConfig.getLabelFont();
					Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100?100:(int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
					//FORCE
					if (_labelFont.getSize() > 6 || true) {
					    //FORCED BOLD
					    _labelGraphics2D.setFont(_labelFont.deriveFont(Font.BOLD));
					    _labelGraphics2D.setColor(_styleConfig.getLabelColor());
					    Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
					    double x = _engineConfig.xtoPixel(_polygons[i].getCentroid().getX()) - (int)(metricBounds.getWidth() / 2.0);
					    double y = _engineConfig.ytoPixel(_polygons[i].getCentroid().getY()) + (int)(metricBounds.getHeight() / 4.0);
					    if (!getBounds().contains(x, y)) {
						ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
						Rectangle _totalBounds = getBounds();
						if (_polygon.contains(_totalBounds.getWidth() / 2.0, _totalBounds.getHeight() / 2.0)) {
						    x = _totalBounds.getWidth() / 2.0 - metricBounds.getWidth();
						    y = _totalBounds.getHeight() / 2.0 + metricBounds.getHeight() / 2.0;
						} else if (_polygon.contains(_totalBounds.getMinX(), _totalBounds.getMinY())) {
						    x = _totalBounds.getMinX() + 10;
						    y = _totalBounds.getMinY() + metricBounds.getHeight();
						} else if (_polygon.contains(_totalBounds.getMaxX(), _totalBounds.getMinY())) {
						    x = _totalBounds.getMaxX() - metricBounds.getWidth();
						    y = _totalBounds.getMinY() + metricBounds.getHeight();
						}
						//Comentado porque no vale la pena intentar dibujarlo en la zona que está tapada por el logo de la organizacion
						else if (_polygon.contains(_totalBounds.getMinX(), _totalBounds.getMaxY()) && !_engineConfig.paintLogo()) {
						    x = _totalBounds.getMinX() + 10;
						    y = _totalBounds.getMaxY() - 10;
						}
						//Comentado porque no vale la pena intentar dibujarlo en la zona que está tapada por el panel de búsqueda catastral
						else if (_polygon.contains(_totalBounds.getMaxX(), _totalBounds.getMaxY()) && bottomRightComponent != null) {
						    if (!bottomRightComponent.isVisible()) {
							x = _totalBounds.getMaxX() - metricBounds.getWidth();
							y = _totalBounds.getMaxY() - 10;
						    }
						}
					    }
					    _labelGraphics2D.drawString(_label, (int)x, (int)y);
					}
				    }
				    if (foundGeometryID == _polygons[i].getIdPolygon() && _layer.getAlias().equals(foundLayer)) {
					ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
					_graphics2D.setColor(_styleConfig.getSelectedColor() != null?_styleConfig.getSelectedColor():Color.orange); //--_polygon.getSelectedColor());
					_graphics2D.fill(_polygon);
					_graphics2D.setColor(_styleConfig.getSelectedOutlineColor() != null?_styleConfig.getSelectedOutlineColor():Color.orange); //--_polygon.getSelectedColor());
					_graphics2D.setStroke(new BasicStroke(_styleConfig.getLineWidth()));
					_graphics2D.draw(_polygon);
					_drawn = true;
				    }
				}
			    }
			} //fi _bounds.intersects
		    }
		}
	    }
	}
    }

    private void drawPolylines(Graphics2D _graphics2d, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
	 ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	 for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++)  {
	     for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++)  {
		 if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		     ESRIPolygon[] _polylines = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m,n));
		     for (int i = 0; i < _polylines.length; i++) {
		         Vector<Layer> _layers = _geometrySet.getLayers();
		         int j = _layers.size()-1;
		         boolean _drawn = false;
		         while (j >= 0 && !_drawn)  {
		             if (/*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
				 Layer _layer = _layers.elementAt(j);
				 boolean isSelectedLayer = (_layer == selectedLayer);
				 boolean _drawPolyline = _layer.drawGeometries();
				 int _symbol = _layer.getSymbol();
				 StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
				 int _idFilterMatch = _layer.getFilterValue(_polylines[i].getIdPolygon());
				 if (_idFilterMatch != -1) {
				     try {
					 LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
					 _styleConfig = _filter.getStyleConfig();
					 _drawPolyline = _filter.isActive() && _layer.drawGeometries();
				     } catch (NullPointerException e) {
				     
				     }
				 }
				 if (_bounds.intersects(_polylines[i].getBounds2D())) {
				     _graphics2d.setColor(_styleConfig.getOutlineColor()!=null?_styleConfig.getOutlineColor():_styleConfig.getFillColor());
				     //double _width = _polylines[i].getBounds2D().getWidth() * _engineConfig.getDrawFactor();
				     //double _heigth = _polylines[i].getBounds2D().getHeight() * _engineConfig.getDrawFactor();
				     if (true) { //(((_width >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _width > 2)) || (_heigth >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _heigth > 2))) && (_width <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0) && (_heigth <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
					 if (/*_layer.drawGeometries() || */_drawPolyline) {
					     if (_engineConfig.isOsnapActive()) {
					         ESRIPolygon _polyline = getFakePolygon(_polylines[i], _engineConfig);
					         for (int k = 0; k < _polylines[i].getVertexCount(); k++) {
					             if (getBounds().contains(_polyline.getX(k), _polyline.getY(k))) {
					                 addOsnapPoint(new ESRIPoint(_polylines[i].getX(k), _polylines[i].getY(k)));
					             }
					         }
					     }
					     if (mouseActive || rasterImageMatrixMode) {
						 if (_polylines[i].getBounds2D().getWidth()> 20 && _polylines[i].getBounds2D().getHeight()> 20) {
						     _graphics2d.setStroke(new BasicStroke(1));
						     _drawn = true;
						 }
					     } else {
						 _graphics2d.setStroke(getStyleConfigStroke(_styleConfig));
						 _drawn = true;
						 if (_polylines[i].getSymbol() != -1) {
						     _symbol = _polylines[i].getSymbol();
						 }
					     }
					     _graphics2d.drawPolyline(_engineConfig.xtoPixel(_polylines[i].getXCoords()), _engineConfig.ytoPixel(_polylines[i].getYCoords()), _polylines[i].getVertexCount());
					     /* Todavía no funciona, falta calcular el punto de inserción del symbol
					  * if (_layer.getSymbol(_symbol) != null) {
					     //BufferedImage imagen = LibIMG.scale((double)25 / (double)_layer.getSymbol(symbol).getWidth(this), _layer.getSymbol(symbol));
					     BufferedImage imagen = _layer.getSymbol(_symbol);
					     int width = (int)(imagen.getWidth(this) / 10 * _engineConfig.getDrawFactor());
					     int height = (int)(imagen.getHeight(this) / 10 * _engineConfig.getDrawFactor());
					     if (width > 20 || height > 20) {
						 int centX = _engineConfig.xtoPixel(_polylines[i].getCentroid().getX()) - (width / 2);
						 int centY = _engineConfig.ytoPixel(_polylines[i].getCentroid().getY()) - (height / 2);
						 _graphics2d.drawImage(imagen, centX, centY, width, height, this);
					     }
					 }
					 */
					     //  System.out.println(i + ".." + osnapPointsVector.size());
					 } //fi layer.drawGeometries()
					 if (true) {//!mouseActive || rasterImageMatrixMode) {
					     String _label = _layer.getLabelValue(_polylines[i].getIdPolygon());
					     if (_label.length()>0 && _layer.getLayerConfig().paintLabels()) { //start if
						 Font _originalFont = _layer.getLayerConfig().getStyleConfig().getLabelFont();
						 Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 35 ? 35 : (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
						 if (_labelFont.getSize() > 6) {
						     _labelGraphics2D.setFont(_labelFont);
						     _labelGraphics2D.setColor(_styleConfig.getLabelColor());
						     Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
						     int k = 0;
						     boolean _found = false;
						     ESRIPolygon _polyline = getFakePolygon(_polylines[i], _engineConfig);
						     while (k < _polyline.getVertexCount() - 1 && !_found) {
							 if (getBounds().intersectsLine(_polyline.getX(k), _polyline.getY(k), _polyline.getX(k + 1), _polyline.getY(k + 1))) {
							     _found = true;
							     double x1 = _engineConfig.xtoPixel(_polylines[i].getX(k));
							     double x2 = _engineConfig.xtoPixel(_polylines[i].getX(k + 1));
							     double y1 = _engineConfig.ytoPixel(_polylines[i].getY(k));
							     double y2 = _engineConfig.ytoPixel(_polylines[i].getY(k + 1));
							     Point2D.Double _intersectionA = new Point2D.Double(x1, y1);
							     if (!getBounds().contains(x1, y1)) {
								 if (x1 < 0) {
								     if (y1 < 0) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
									 } else {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
									 }
								     } else if (y1 > _engineConfig.getFHeight()) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
									 } else {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 }
								     } else {
									 _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								     }
								 } else if (x1 > _engineConfig.getFWidth()) {
								     if (y1 < 0) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, _engineConfig.getFWidth(), 0, _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 } else {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
									 }
								     } else if (y1 > _engineConfig.getFHeight()) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, _engineConfig.getFHeight(), _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 } else {
									     _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 }
								     } else {
									 _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								     }
								 } else {
								     if (y1 < 0) {
									 _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								     } else {
									 _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								     }
								 }
							     }
							     Point2D.Double _intersectionB = new Point2D.Double(x2, y2);
							     if (!getBounds().contains(x2, y2)) {
								 if (x2 < 0) {
								     if (y2 < 0) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
									 } else {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
									 }
								     } else if (y2 > _engineConfig.getFHeight()) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
									 } else {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 }
								     } else {
									 _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								     }
								 } else if (x2 > _engineConfig.getFWidth()) {
								     if (y2 < 0) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, _engineConfig.getFWidth(), 0, _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 } else {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
									 }
								     } else if (y2 > _engineConfig.getFHeight()) {
									 if (Line2D.linesIntersect(x1, y1, x2, y2, 0, _engineConfig.getFHeight(), _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 } else {
									     _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
									 }
								     } else {
									 _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								     }
								 } else {
								     if (y2 < 0) {
									 _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								     } else {
									 _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								     }
								 }
							     }
							     x1 = _intersectionA.getX();
							     y1 = _intersectionA.getY();
							     x2 = _intersectionB.getX();
							     y2 = _intersectionB.getY();
							     int xl = (int)(x1 / 2.0 + x2 / 2.0);
							     int yl = (int)(y1 / 2.0 + y2 / 2.0);
							     //Cálculo del ángulo del label
							     double lblbase = x1 - x2;
							     double lblaltura = y1 - y2;
							     double lblangulo = Math.atan(lblaltura / lblbase);
							     int lHeight = _labelGraphics2D.getFontMetrics().getHeight();
							     int lWidth = (int)metricBounds.getWidth();
							     if (lblangulo * 180.0 / Math.PI >= -360 && lblangulo * 180.0 / Math.PI <= 360) {
								 AffineTransform at = AffineTransform.getRotateInstance(lblangulo, xl, yl);
								 _labelGraphics2D.setTransform(at);
								 _labelGraphics2D.drawString(_label, (int)(xl - lWidth / 2.0), (int)(yl + lHeight / 2.0));
								 at = at.getRotateInstance(0, xl, yl);
								 _labelGraphics2D.setTransform(at);
							     }
							 }
							 k++;
						     }
						 }
					     } // end if
					 } //end if mouseActive
				     }
				 }
			     }
			     j--;
			 }
		     }
		 }
	     }
	 }
     }

    private void drawPolylinesForExport(Graphics2D _graphics2d, Graphics2D _labelGraphics2D, Layer _layer, BasicDrawEngineConfig _engineConfig) {
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	GeometrySet _geometrySet = _layer.getGeometrySet();
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
		if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPolygon[] _polylines = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m, n));
		    for (int i = 0; i < _polylines.length; i++) {
			Vector<Layer> _layers = _geometrySet.getLayers();
			boolean _drawn = false;
			int _symbol = _layer.getSymbol();
			StyleConfig _styleConfig = _layer.getLayerConfig().getStyleConfig();
			int _idFilterMatch = _layer.getFilterValue(_polylines[i].getIdPolygon());
			if (_idFilterMatch != -1) {
			    try {
				LayerFilter _filter = _layer.getLayerFilter(_idFilterMatch);
				_styleConfig = _filter.getStyleConfig();
			    } catch (NullPointerException e) {

			    }
			}
			if (_bounds.intersects(_polylines[i].getBounds2D())) {
			    if (true) {
				//FORCE
				if (true) {
				    if (_engineConfig.isOsnapActive()) {
					ESRIPolygon _polyline = getFakePolygon(_polylines[i], _engineConfig);
					for (int k = 0; k < _polylines[i].getVertexCount(); k++) {
					    if (getBounds().contains(_polyline.getX(k), _polyline.getY(k))) {
						addOsnapPoint(new ESRIPoint(_polylines[i].getX(k), _polylines[i].getY(k)));
					    }
					}
				    }

				    _graphics2d.setColor(_styleConfig.getOutlineColor() != null?_styleConfig.getOutlineColor():_styleConfig.getFillColor());
				    _graphics2d.setStroke(getStyleConfigStroke(_styleConfig));
				    _graphics2d.setStroke(new BasicStroke(2));
				    _graphics2d.drawPolyline(_engineConfig.xtoPixel(_polylines[i].getXCoords()), _engineConfig.ytoPixel(_polylines[i].getYCoords()), _polylines[i].getVertexCount());
				    _drawn = true;
				    if (_polylines[i].getSymbol() != -1) {
					_symbol = _polylines[i].getSymbol();
				    }
				}
				if (true) {
				    String _label = _layer.getLabelValue(_polylines[i].getIdPolygon());
				    //FORCE
				    if (_label.length() > 0) {
					Font _originalFont = _layer.getLayerConfig().getStyleConfig().getLabelFont();
					Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 35?35:(int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
					//FORCE
					if (true) {
					    _labelGraphics2D.setFont(_labelFont.deriveFont(Font.BOLD));
					    _labelGraphics2D.setColor(_styleConfig.getLabelColor());
					    Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
					    int k = 0;
					    boolean _found = false;
					    ESRIPolygon _polyline = getFakePolygon(_polylines[i], _engineConfig);
					    while (k < _polyline.getVertexCount() - 1 && !_found) {
						if (getBounds().intersectsLine(_polyline.getX(k), _polyline.getY(k), _polyline.getX(k + 1), _polyline.getY(k + 1))) {
						    _found = true;
						    double x1 = _engineConfig.xtoPixel(_polylines[i].getX(k));
						    double x2 = _engineConfig.xtoPixel(_polylines[i].getX(k + 1));
						    double y1 = _engineConfig.ytoPixel(_polylines[i].getY(k));
						    double y2 = _engineConfig.ytoPixel(_polylines[i].getY(k + 1));
						    Point2D.Double _intersectionA = new Point2D.Double(x1, y1);
						    if (!getBounds().contains(x1, y1)) {
							if (x1 < 0) {
							    if (y1 < 0) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								} else {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								}
							    } else if (y1 > _engineConfig.getFHeight()) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								} else {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								}
							    } else {
								_intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
							    }
							} else if (x1 > _engineConfig.getFWidth()) {
							    if (y1 < 0) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, _engineConfig.getFWidth(), 0, _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								} else {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								}
							    } else if (y1 > _engineConfig.getFHeight()) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, _engineConfig.getFHeight(), _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								} else {
								    _intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								}
							    } else {
								_intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
							    }
							} else {
							    if (y1 < 0) {
								_intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
							    } else {
								_intersectionA = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
							    }
							}
						    }
						    Point2D.Double _intersectionB = new Point2D.Double(x2, y2);
						    if (!getBounds().contains(x2, y2)) {
							if (x2 < 0) {
							    if (y2 < 0) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								} else {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								}
							    } else if (y2 > _engineConfig.getFHeight()) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, 0, 0, _engineConfig.getFHeight())) {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
								} else {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								}
							    } else {
								_intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(0, _engineConfig.getFHeight()));
							    }
							} else if (x2 > _engineConfig.getFWidth()) {
							    if (y2 < 0) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, _engineConfig.getFWidth(), 0, _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								} else {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
								}
							    } else if (y2 > _engineConfig.getFHeight()) {
								if (Line2D.linesIntersect(x1, y1, x2, y2, 0, _engineConfig.getFHeight(), _engineConfig.getFWidth(), _engineConfig.getFHeight())) {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								} else {
								    _intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
								}
							    } else {
								_intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(_engineConfig.getFWidth(), 0), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
							    }
							} else {
							    if (y2 < 0) {
								_intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, 0), new Point2D.Double(_engineConfig.getFWidth(), 0));
							    } else {
								_intersectionB = getIntersection(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(0, _engineConfig.getFHeight()), new Point2D.Double(_engineConfig.getFWidth(), _engineConfig.getFHeight()));
							    }
							}
						    }
						    x1 = _intersectionA.getX();
						    y1 = _intersectionA.getY();
						    x2 = _intersectionB.getX();
						    y2 = _intersectionB.getY();
						    int xl = (int)(x1 / 2.0 + x2 / 2.0);
						    int yl = (int)(y1 / 2.0 + y2 / 2.0);
						    //Cálculo del ángulo del label
						    double lblbase = x1 - x2;
						    double lblaltura = y1 - y2;
						    double lblangulo = Math.atan(lblaltura / lblbase);
						    int lHeight = _labelGraphics2D.getFontMetrics().getHeight();
						    int lWidth = (int)metricBounds.getWidth();
						    if (lblangulo * 180.0 / Math.PI >= -360 && lblangulo * 180.0 / Math.PI <= 360) {
							AffineTransform at = AffineTransform.getRotateInstance(lblangulo, xl, yl);
							_labelGraphics2D.setTransform(at);
							_labelGraphics2D.drawString(_label, (int)(xl - lWidth / 2.0), (int)(yl + lHeight / 2.0));
							at = at.getRotateInstance(0, xl, yl);
							_labelGraphics2D.setTransform(at);
						    }
						}
						k++;
					    }
					}
				    } // end if
				} //end if mouseActive
			    }
			}
		    }
		}
	    }
	}
    }

    public void setCoordinateViewer(CoordinateViewer _coordinateViewer) {
	coordinateViewer = _coordinateViewer;
	coordinateViewer.setVisible(true);
    }

    public void removeAllLayers() {
	layers.removeAllElements();
	osnapPointsVector.removeAllElements();
    }

    public void removeAllLayersExcept(String _name) {
	int i = 0;
	while (i < layers.size()) {
	    if (!layers.elementAt(i).getAlias().equals(_name)) {
		layers.removeElementAt(i);
	    }
	    i++;
	}
	repaint();
    }

    private void restartEnvironment() {
	//labelinfo.setVisible(false);
	drawingPointsVector.removeAllElements();
	startDrawRectPosition = null;
	endDrawRectPosition = null;
	zoomRectangle = null;
	hideToolTipTimer.start();
	for (int i = 0; i < layers.size(); i++) {
	    layers.elementAt(i).getGeometrySet().setContainedShapeID(-1);
	}
	GaiaEnvironment.setSelectedStreetID(-1);
	GaiaEnvironment.setSelectedStreetName("");
	foundGeometryID = -1;
	foundLayer = "";
	selectedLayer = null;
    }

    public Vector getLayers() {
	return layers;
    }

    public Layer getLayer(String _layerName) {
	Layer _layer = null;
	boolean found = false;
	int i = 0;
	while (i < layers.size() && !found) {
	    System.out.println(layers.elementAt(i).getName());
	    if (layers.elementAt(i).getName().equalsIgnoreCase(_layerName)) {
		found = true;
		_layer = layers.elementAt(i);
	    }
	    i++;
	}
	return _layer;
    }

    private ESRIPolygon getFakePolygon(ESRIPoint[] _points) {
	int cantidad = _points.length;
	if (cantidad > 0) {
	    double[] xy = new double[cantidad * 2];
	    for (int i = 0; i < cantidad * 2; i += 2) {
		xy[i] = engineConfig.xtoPixel(_points[i / 2].getX());
		xy[i + 1] = engineConfig.ytoPixel(_points[i / 2].getY());
	    }
	    return new ESRIPolygon.Double(xy);
	} else {
	    return new ESRIPolygon.Double(0, 0);
	}
    }

    private ESRIPolygon pointsArrayToPolygon(ESRIPoint[] _points) {
	int cantidad = _points.length;
	if (cantidad > 0) {
	    double[] xy = new double[cantidad * 2];
	    for (int i = 0; i < cantidad * 2; i += 2) {
		xy[i] = _points[i / 2].getX();
		xy[i + 1] = _points[i / 2].getY();
	    }
	    return new ESRIPolygon.Double(xy);
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
	    zoomRectangle = new Rectangle(drawRectX, drawRectY, drawRectWidth, drawRectHeight);
	}
    }

    private ESRIPolygon createRectangleAsPolygon(Point2D.Double _startPosition, Point2D.Double _endPosition) {
	return new ESRIPolygon.Double(createRectangle(_startPosition, _endPosition));
    }

    private Rectangle2D.Double createRectangle(Point2D.Double _startPosition, Point2D.Double _endPosition) {
	Rectangle2D.Double _rectangle = null;
	if (_startPosition != null && _endPosition != null) {
	    int _drawRectWidth = (int)(_endPosition.getX() - _startPosition.getX());
	    int _drawRectHeight = (int)(_endPosition.getY() - _startPosition.getY());
	    int _drawRectX = (int)_startPosition.getX();
	    int _drawRectY = (int)_startPosition.getY();
	    if (_drawRectWidth < 0) {
		_drawRectWidth *= -1;
		_drawRectX -= _drawRectWidth;
		if (_drawRectX < 0) {
		    _drawRectWidth += _drawRectX;
		    _drawRectX = 0;
		}
	    }
	    if (_drawRectHeight < 0) {
		_drawRectHeight *= -1;
		_drawRectY -= _drawRectHeight;
		if (_drawRectY < 0) {
		    _drawRectHeight += _drawRectY;
		    _drawRectY = 0;
		}
	    }
	    _rectangle = new Rectangle2D.Double(_drawRectX, _drawRectY, _drawRectWidth, _drawRectHeight);
	}
	return _rectangle;
    }

    private void showToolTip(boolean _visible) {
	lastShowingTime = System.currentTimeMillis();
	toolTip.setVisible(_visible);
	if (_visible) {
	    hideToolTipTimer.start();
	} else {
	}
	repaint();
    }

    public void setMapExtents(Point2D[] mapExtents) {
	this.mapExtents = mapExtents;
    }

    public void setMapExtents(Point2D _minExtents, Point2D _maxExtents) {
	mapExtents = new Point2D[2];
	mapExtents[0] = _minExtents;
	mapExtents[1] = _maxExtents;
    }

    public void setMapExtents(double _x1, double _y1, double _x2, double _y2) {
	mapExtents = new Point2D[2];
	mapExtents[0] = new Point2D.Double(_x1, _y1);
	mapExtents[1] = new Point2D.Double(_x2, _y2);
    }

    public Point2D[] getMapExtents() {
	return mapExtents;
    }

    public void setProjectionType(int _projectionType) {
	engineConfig.setProjectionType(_projectionType);
    }

    public int getProjectionType() {
	return engineConfig.getProjectionType();
    }

    public Vector getLayerGroups() {
	return layerGroups;
    }

    private boolean saveMapImage() {
	JFileChooser chooser = new JFileChooser(cfg.getProperty("MapImage") + File.separator);
	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF File Selected
	    try {
		String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.endsWith(".png")) {
		    path += ".png";
		}
		cfg.setProperty("MapImage", chooser.getSelectedFile().getParent());
		File file = new File(path);
		BufferedImage bufferedImage = new BufferedImage(engineConfig.getFWidth(), engineConfig.getFHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D _graphics2D = bufferedImage.createGraphics();
	        Graphics2D labelGraphics2D = (Graphics2D)_graphics2D.create();
	
		drawLayers(_graphics2D, labelGraphics2D, engineConfig);
	        if (engineConfig.paintScaleBar()) {
	            drawScaleBar(_graphics2D);
	        }
		_graphics2D.dispose();

		ImageIO.write(bufferedImage, "png", file);
		Advisor.messageBox("La imagen se grabó con éxito", "Grabar Imagen");
		return true;
	    } catch (IOException x) {
		Advisor.messageBox("No se pudo grabar la imagen", "Error de E/S");
		x.printStackTrace();
		return false;
	    }
	} else {
	    return false;
	}
    }

    private void doReport() {
	BasicReport report = new BasicReport(BasicDrawEngine.class.getResource("xml/MapImageReport.xml"));

	String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Título del Mapa", "MAPA", JOptionPane.QUESTION_MESSAGE);
	if (_title != null) {
	    BufferedImage bufferedImage = new BufferedImage(engineConfig.getFWidth(), engineConfig.getFHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    Graphics2D _graphics2D = bufferedImage.createGraphics();
	    Graphics2D labelGraphics2D = (Graphics2D)_graphics2D.create();

	    drawLayers(_graphics2D, labelGraphics2D, engineConfig);
	    if (engineConfig.paintScaleBar()) {
	        drawScaleBar(_graphics2D);
	    }
	    _graphics2D.dispose();

	    Image logo = OrganizationInfo.getLeftLogo();
	    double _logoScale = Math.min(90 / (double)logo.getHeight(this), 90 / (double)logo.getWidth(this));
	    report.setProperty("headerlogo", logo.getScaledInstance((int)(logo.getWidth(this) * _logoScale), (int)(logo.getHeight(this) * _logoScale), Image.SCALE_SMOOTH));

	    report.setProperty("title", _title);
	    report.setProperty("mapImage", bufferedImage);
	    report.addTableModel("org.getSessionData", "");

	    report.doReport();
	}
    }

    private void doNomenclador() {
    /*
	JFileChooser chooser = new JFileChooser(cfg.getProperty("Nomenclador") + File.separator);
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF Directory Selected
	    try {
		String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.endsWith("/")) {
		    path += "/";
		}
		cfg.setProperty("Nomenclador", chooser.getSelectedFile().getAbsolutePath());
		if (GaiaEnvironment.nomencladorLayers.size() > 0) {
		    for (int i = 0; i < layers.size(); i++) {
			layers.elementAt(i).setOff();
		    }
		    for (int i = 0; i < GaiaEnvironment.nomencladorLayers.size(); i++) {
			GaiaEnvironment.nomencladorLayers.elementAt(i).setOn();
		    }
		    Layer2 _layer = GaiaEnvironment.nomencladorLayers.elementAt(0);
		    if ((_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) || (_layer.getGeometrySetConfig().getShapeType() == ShapeTypes.MULTIPOLYGON)) {
		        for (int m = 0; m < _layer.getGeometrySetConfig().getGridSize(); m++)  {
		            for (int n = 0; n < _layer.getGeometrySetConfig().getGridSize(); n++)  {
				Vector geometries = _layer.getGeometriesFromMatrix(m,n);
				Vector<String> labels = new Vector<String>();
				int counter = 0;
				for (int i = 0; i < geometries.size(); i++) {
				    if (true) {
				    //if (((ESRIPolygon)geometries.elementAt(i)).getLabel().startsWith("B") && counter < 10) {
					counter++;
					Rectangle2D _bounds = ((ESRIPolygon)geometries.elementAt(i)).getBounds2D();
					double _extents = Math.max(_bounds.getWidth(), _bounds.getHeight());
					//_extents = _extents * 4.0;
					if (_extents < 10.001) {
					    _extents = 10.001;
					}
					double _drawFactor = (this.getHeight() - 20) / _extents;
					double _drawScale = _drawFactor / engineConfig.getDrawFactorOriginal();
					double minX = _bounds.getMinX() - (getWidth() / _drawFactor) / 2.0 + (_bounds.getWidth() / 2.0);
					double minY = _bounds.getMinY() - (getHeight() / _drawFactor) / 2.0 + (_bounds.getHeight() / 2.0);
					engineConfig.setDrawScale(_drawScale);
					engineConfig.setDrawFactor(engineConfig.getDrawFactorOriginal() * engineConfig.getDrawScale());
					engineConfig.setXOffset(minX);
					engineConfig.setYOffset(minY);
					repaint();
					//goTo(_drawScale, minX, minY);
					//String _title = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Manzana: ", "NOMENCLADOR", JOptionPane.QUESTION_MESSAGE, null, null, ((ESRIPolygon)geometries.elementAt(i)).getLabel());
					//setPolygonEnvironment(_layer, ((ESRIPolygon)geometries.elementAt(i)).getIdPolygon(), Color.black, false);
					BufferedImage bufferedImage = new BufferedImage(engineConfig.getFWidth(), engineConfig.getFHeight(), BufferedImage.TYPE_4BYTE_ABGR);
					Graphics2D graphics2D = bufferedImage.createGraphics();
					Graphics2D labelGraphics2D = (Graphics2D)graphics2D.create();
					drawLayers(graphics2D, labelGraphics2D, engineConfig);
					graphics2D.dispose();
					String _label = ((ESRIPolygon)geometries.elementAt(i)).getLabel();
					System.out.println(i + ": " + _label);
					if (labels.contains(_label) || _label.length() == 0) {
					    _label += "[" + ((ESRIPolygon)geometries.elementAt(i)).getIdPolygon() + "]";
					}
					String filePath = path + _label + ".png";
					File file = new File(filePath);
					ImageIO.write(bufferedImage, "png", file);
					//Advisor.messageBox("Grabar Imagen", "La imagen se grabó con exito");
				    }
				}
			    }
			}
		    }
		}
	    } catch (IOException x) {
		if (Advisor.question("Error de E/S", "No se pudo generar el nomenclador\n¿Desea ver los detalles del error?")) {
		    Advisor.printException(x);
		}
	        x.printStackTrace();
	    } catch (NullPointerException x) {
	        if (Advisor.question("Error de E/S", "No se pudo generar el nomenclador\n¿Desea ver los detalles del error?")) {
	            Advisor.printException(x);
	        }
	        x.printStackTrace();
	    }
	}
    */
    }

    private Point2D.Double getIntersection(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
	double a = 0;
	if (p1.getX() - p0.getX() == 0) {
	    a = 900;
	} else {
	    a = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
	}
	double b = -1.0;
	double c = a * p0.getX() - p0.getY();
	double d = 0;
	if (p3.getX() - p2.getX() == 0) {
	    d = 1000000;
	} else {
	    d = (p3.getY() - p2.getY()) / (p3.getX() - p2.getX());
	}
	double e = -1.0;
	double f = d * p2.getX() - p2.getY();
	double D = a * e - b * d;
	double x = (c * e - f * b) / D;
	double y = (a * f - d * c) / D;
	return new Point2D.Double(x, y);
    }

    public synchronized void fetchEngineConfigFromCache(String _mapName) {
	String _userHome = System.getProperty("user.home");
	final File _cacheFile = new File(_userHome + File.separator + ".ddesktop.cache" + File.separator + MD5.getMD5(_mapName + " GIS Engine - " + OrganizationInfo.getOrgName()) + ".dec");
	engineConfig.setMapName(_mapName);
	if (!(_cacheFile.exists())) {
	    engineConfig.saveData();
	} else {
	    try {
		System.out.println("Fetching engine configuration");
		// Read from disk using FileInputStream
		FileInputStream inFile = new FileInputStream(_cacheFile);
		// Read object using ObjectInputStream
		ObjectInputStream inObject = new ObjectInputStream(inFile);
		// Read an object
		Object cachedObject = inObject.readObject();
		if (cachedObject instanceof BasicDrawEngineConfig) {
		    engineConfig = (BasicDrawEngineConfig)cachedObject;
		}
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
		engineConfig.saveData();
	    } catch (InvalidClassException e) {
		System.out.println("Error: " + e.getMessage());
		engineConfig.saveData();
	    } catch (FileNotFoundException e) {
		System.out.println("Error: " + e.getMessage());
		engineConfig.saveData();
	    } catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
		engineConfig.saveData();
	    }
	}
    }
    
    private void showHelp() {
	String _helpMessage = "<html>";
	_helpMessage += "Escape: Cancela la operación actual<br>";
	_helpMessage += "Enter: Guarda la posición del puntero en memoria<br>";
	_helpMessage += "F1: Muestra esta ventana de ayuda<br>";
	//_helpMessage += "F2: Listado de Layers<br>";
	_helpMessage += "F3: OSNAP on/off<br>";
	_helpMessage += "F4: Mostrar/Ocultar Ventana de Coordenadas<br>";
	_helpMessage += "F5: COORDENADAS si/no<br>";
	_helpMessage += "F6: ORTHO si/no<br>";
	_helpMessage += "F7: SNAP TO GRID si/no<br>";
	_helpMessage += "F8: ANTIALIAS si/no<br>";
	_helpMessage += "F9: RASTER/VECTOR<br>";
	//_helpMessage += "F10: LOGO si/no<br>";
	_helpMessage += "F11: Pantalla completa<br>";
	_helpMessage += "F12: Barra de escala si/no<br>";
	_helpMessage += "";
	_helpMessage += "Ctrl+P: Imprimir reporte de la vista actual<br>";
	_helpMessage += "Ctrl+S: Grabar imagen de la vista actual<br>";
	_helpMessage += "Ctrl+1: Configurar servidor del GIS<br>";
	_helpMessage += "Ctrl+0: Limpiar el caché del GIS<br>";
	_helpMessage += "Ctrl+Flechas: Mostrar/Ocultar ventanas de Herramientas del GIS<br>";
	//_helpMessage += "Shift+Ctrl+N: Nomenclador<br>";
	_helpMessage += "</html>";
	Advisor.messageBox(_helpMessage, "Ayuda");
    }

    private void createKeyBindings() {
	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "escape");
	getActionMap().put("escape", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_ESCAPE);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, true), "show_help");
	getActionMap().put("show_help", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SHOW_HELP);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, true), "list_layers");
	getActionMap().put("list_layers", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_LIST_LAYERS);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, true), "set_osnap_on_off");
	getActionMap().put("set_osnap_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_OSNAP_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, true), "set_coordinate_viewer_on_off");
	getActionMap().put("set_coordinate_viewer_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_COORDINATE_VIEWER_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, true), "set_coordinates_on_off");
	getActionMap().put("set_coordinates_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_COORDINATES_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, true), "set_ortho_on_off");
	getActionMap().put("set_ortho_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_ORTHO_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, true), "set_snaptogrid_on_off");
	getActionMap().put("set_snaptogrid_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_SNAPTOGRID_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0, true), "set_antialias_on_off");
	getActionMap().put("set_antialias_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_ANTIALIAS_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0, true), "set_raster_mode_on_off");
	getActionMap().put("set_raster_mode_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_RASTER_MODE_ON_OFF);
		//Advisor.messagePopupWindow("Los modos RASTER y VECTOR se activan automáticamente", "");
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, true), "set_logo_on_off");
	getActionMap().put("set_logo_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_LOGO_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0, true), "set_scalebar_on_off");
	getActionMap().put("set_scalebar_on_off", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SET_SCALEBAR_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK & InputEvent.SHIFT_DOWN_MASK, true), "nomenclador");
	getActionMap().put("nomenclador", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_NOMENCLADOR);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK, true), "print");
	getActionMap().put("print", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_PRINT);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK, true), "save_map_image");
	getActionMap().put("save_map_image", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_SAVE_MAP_IMAGE);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.CTRL_DOWN_MASK, true), "show_hide_bottom_left_component");
	getActionMap().put("show_hide_bottom_left_component", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngine.OPERATION_SET_BOTTOM_LEFT_COMPONENT_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK, true), "show_hide_top_left_component");
	getActionMap().put("show_hide_top_left_component", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngine.OPERATION_SET_TOP_LEFT_COMPONENT_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.CTRL_DOWN_MASK, true), "show_hide_top_right_component");
	getActionMap().put("show_hide_top_right_component", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngine.OPERATION_SET_TOP_RIGHT_COMPONENT_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK, true), "show_hide_bottom_right_component");
	getActionMap().put("show_hide_bottom_right_component", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngine.OPERATION_SET_BOTTOM_RIGHT_COMPONENT_ON_OFF);
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('1', InputEvent.CTRL_DOWN_MASK, true), "configuracion_servidor_propietario");
	getActionMap().put("configuracion_servidor_propietario", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		configurePropietaryData();
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('0', InputEvent.CTRL_DOWN_MASK, true), "limpiar_cache");
	getActionMap().put("limpiar_cache", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		limpiarCache();
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "enter");
	getActionMap().put("enter", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
	        saveCurrentPosition();
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, true), "control_enter");
	getActionMap().put("control_enter", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		flyToCurrentPosition();
	    }
	}
	);

	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('I', InputEvent.CTRL_DOWN_MASK, true), "manage_images");
	getActionMap().put("manage_images", new AbstractAction() {
	    public void actionPerformed(ActionEvent evt) {
		setOperation(BasicDrawEngineConfig.OPERATION_MANAGE_IMAGE_ATTACHMENTS);
	    }
	}
	);

    }

    private BasicStroke getStyleConfigStroke(StyleConfig _styleConfig) {
	if (_styleConfig.getStrokeStyle() == -1) {
	    return new BasicStroke(_styleConfig.getLineWidth());
	} else {
	    BasicStroke _stroke = (BasicStroke)StrokeSamples.strokeSamples[_styleConfig.getStrokeStyle()].getStroke();
	    return new BasicStroke(_styleConfig.getLineWidth(), _stroke.getEndCap(), _stroke.getLineJoin(), _stroke.getMiterLimit(), _stroke.getDashArray(), _stroke.getDashPhase());
	}
    }

    private void startAutoUpdate(final Layer _layer) {
	    Timer autoUpdateTimer = new Timer(_layer.getLayerConfig().getAutoUpdateRateInSeconds()*1000, new ActionListener() {

		    public void actionPerformed(ActionEvent actionEvent) {
			if (_layer.isOn()) {
			    System.out.println("Auto-Updating layer " + _layer.getAlias());
			    _layer.reload();
			}
		    }

		});
	    autoUpdateTimer.start();
	}

    private void addOsnapPoints(ESRIPolygon _esriPolygon) {
	for (int i = 0; i < _esriPolygon.getVertexCount() - 1; i++) {
	    addOsnapPoint(new ESRIPoint(_esriPolygon.getX(i), _esriPolygon.getY(i)));
	}
    }

    private void addOsnapPoint(ESRIPoint _esriPoint) {
	Point2D.Double _point = engineConfig.toSpace(mousePosition);
	if ( _esriPoint.distance(_point) < osnapTolerance ) {
	    osnapTolerance = _esriPoint.distance(_point);
	    osnapPointsVector.addOsnapPoint(_esriPoint);
	}
    }
    
    private void doListLayers() {
	BasicDialog _layerListDialog = new BasicDialog(new BasicDialog(), "Listado de Layers", true);
	_layerListDialog.setLayout(new BorderLayout());
	_layerListDialog.setSize(400,200);
	JArea _layerList = new JArea();
	_layerList.setEditable(false);
	_layerListDialog.add(_layerList, BorderLayout.CENTER);
	StringBuilder _list = new StringBuilder();
	for (int i = 0; i < layerGroups.size(); i++)  {
	    LayerGroup _group = ((LayerGroup)layerGroups.elementAt(i));
	    _list.append("Grupo: " + _group.getName() + "\n");
	    for (int j = 0; j < _group.size(); j++)  {
	        _list.append("\t" + ((Layer)_group.elementAt(j)).getName() + "\n");
	    }
	}
	_layerList.setText(_list.toString());
	ComponentsManager.centerWindow(_layerListDialog);
	_layerListDialog.setVisible(true);
    }

    private void saveEnvironment() {
	engineConfig.saveData();
    }

    public void setRuleViewer(RuleViewer _ruleViewer) {
	ruleViewer = _ruleViewer;
    }

    public BasicDrawEngineConfig getEngineConfig() {
	return engineConfig;
    }

    public void setBottomRightComponent(Component _bottomRightComponent) {
	bottomRightComponent = _bottomRightComponent;
    }

    public void setBottomLeftComponent(Component _bottomLeftComponent) {
	bottomLeftComponent = _bottomLeftComponent;
    }

    public void setTopRightComponent(Component _topRightComponent) {
	topRightComponent = _topRightComponent;
    }

    public void setTopLeftComponent(Component _topLeftComponent) {
	topLeftComponent = _topLeftComponent;
    }

    private void evaluarRendimiento(long _drawingTime) {
	//System.out.println("Rendimiento " + (rasterImageMatrixMode?"RASTER":"VECTOR") + ": " + (_drawingTime));
	if (rasterImageMatrixMode) {
	    if (_drawingTime < 300) {
	        rasterImageMatrixMode = false;
	        drawRasterImageVector();
	        System.out.println("Rendimiento " + (rasterImageMatrixMode?"RASTER":"VECTOR") + ": " + (_drawingTime));
	        Advisor.messagePopupWindow("Modo raster (imagen) " + (rasterImageMatrixMode?"encendido":"apagado"), "");
	    }
	} else {
	    if (_drawingTime > 1500) {
	        rasterImageMatrixMode = true;
	        drawRasterImageVector();
	        System.out.println("Rendimiento " + (rasterImageMatrixMode?"RASTER":"VECTOR") + ": " + (_drawingTime));
	        Advisor.messagePopupWindow("Modo raster (imagen) " + (rasterImageMatrixMode?"encendido":"apagado"), "");
	    }
	}
    }

    private void drawLayers(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, Vector<Layer> _layers, BasicDrawEngineConfig _engineConfig) {
       try {
	    if (_engineConfig.isAntiAlias()) {
	        setAntiAlias(_graphics2D);
	        setAntiAlias(_labelGraphics2D);
	    }
	   
	    for (int i = 0; i < _layers.size(); i++) {
		drawImageAttachments(_graphics2D, _layers.elementAt(i));
	    }
	    BufferedImage _labelImage = new BufferedImage(_engineConfig.getFWidth(), _engineConfig.getFHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	    _labelGraphics2D = _labelImage.createGraphics();
	    for (int i = 0; i < geometrySets.size(); i++) {
	       try {
		   GeometrySet _geometrySet = geometrySets.elementAt(i);
		   if (_geometrySet.isLoaded() && _geometrySet.getGeometrySetConfig().getBounds() != null) {
		       drawGeometrySet(_graphics2D, _labelGraphics2D, _geometrySet, _engineConfig);
		   }
	       } catch (Exception x) {
		   System.err.println("Error al dibujar el layer " + _layers.elementAt(i).getName() + "\nError: " + x.getMessage());
		   continue;
	       }
	    }
	    _graphics2D.drawImage(_labelImage, 0, 0, _labelImage.getWidth(), _labelImage.getHeight(), this);
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    public BufferedImage getCadastralMapImage(Dimension _dimension, int _idPolygon) {
	BufferedImage bufferedImage = new BufferedImage(_dimension.width, _dimension.height, BufferedImage.TYPE_4BYTE_ABGR);
	boolean _found = false;
	int i = 0;
	while (i < GaiaEnvironment.cadastralLayers.size() && !_found) {
	    if (GaiaEnvironment.cadastralLayers.elementAt(i).getName().equals(GaiaEnvironment.getCadastralLayer())) {
		_found = true;
	    } else {
		i++;
	    }
	}
	if (_found) {
	    Rectangle2D _bounds = GaiaEnvironment.cadastralLayers.elementAt(i).getGeometrySet().getPolygon(_idPolygon).getBounds2D();

	    double _extents = Math.max(_dimension.getWidth(), _dimension.getHeight());
	    //_extents = _extents * 4.0;
	    if (_extents < 10.001) {
	        _extents = 10.001;
	    }
	    BasicDrawEngineConfig _engineConfig = new BasicDrawEngineConfig();
	    _engineConfig.setFWidth(_dimension.width);
	    _engineConfig.setFHeight(_dimension.height);
	    _engineConfig.setExtents(_extents);

	    double _drawFactor = Math.max(_engineConfig.getFWidth(), _engineConfig.getFHeight()) / _extents*4;
	    _engineConfig.setDrawFactor(_drawFactor);
	    _engineConfig.setDrawFactorOriginal(_drawFactor);

	    double _drawScale = _drawFactor / _engineConfig.getDrawFactorOriginal();
	    _engineConfig.setDrawScale(_drawScale);

	    double minX = _bounds.getMinX() - (_dimension.getWidth() / _drawFactor) / 2.0 + (_bounds.getWidth() / 2.0);
	    double minY = _bounds.getMinY() - (_dimension.getHeight() / _drawFactor) / 2.0 + (_bounds.getHeight() / 2.0);
	    _engineConfig.setXOffset(minX);
	    _engineConfig.setYOffset(minY);

	    _engineConfig.setAntiAlias(true);

	    Graphics2D _graphics2D = bufferedImage.createGraphics();
	    Graphics2D _labelGraphics2D = (Graphics2D)_graphics2D.create();
	    //_graphics2D.setColor(Color.WHITE);
	    //_graphics2D.fillRect(0, 0, _dimension.width, _dimension.height);
	    int _foundGeometryID = foundGeometryID;
	    String _foundLayer = foundLayer;
	    foundGeometryID =  _idPolygon;
	    foundLayer = GaiaEnvironment.getCadastralLayer();
	    
	    for (int j = 0; j < GaiaEnvironment.cadastralLayers.size(); j++) {
	        drawLayerForExport(_graphics2D, _labelGraphics2D, GaiaEnvironment.cadastralLayers.elementAt(j), _engineConfig);
	    }

	    foundGeometryID = _foundGeometryID;
	    foundLayer = _foundLayer;
	    
	    _graphics2D.dispose();
	    
	    /*
	    String filePath = "/tmp/" + _idPolygon + ".png";
	    File file = new File(filePath);
	    try {
		ImageIO.write(bufferedImage, "png", file);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    System.out.println(GaiaEnvironment.cadastralLayers.elementAt(i).getGeometrySet().getPolygon(_idPolygon).getBounds());
	    */
	}
	return bufferedImage;
    }

    private void configurePropietaryData() {
	//Solicitamos los datos del servidor propietario
	if (Advisor.question("Configuración", "¿Desea configurar los datos del servidor del GIS?")) {
	    String _serverURL = null;
	    while (_serverURL == null) {
		_serverURL = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el URL o Nombre del Servidor", "Nombre del Servidor", JOptionPane.QUESTION_MESSAGE, null, null, "www.digitallsh.com.ar");
	    }
	    String _database = null;
	    while (_database == null) {
		_database = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el nombre de la Base de Datos", "Base de Datos", JOptionPane.QUESTION_MESSAGE, null, null, "salta");
	    }
	    String _user = null;
	    while (_user == null) {
		_user = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Nombre de Usuario", "Usuario", JOptionPane.QUESTION_MESSAGE, null, null, "demo");
	    }
	    String _password = "";
	    while (_password.length() == 0) {
	        JPasswordField _jtPassword = new JPasswordField();
	        if (Environment.cfg.getProperty("RequestFocus").equalsIgnoreCase("True")) {
		    _jtPassword.requestFocus();
		}
	        JOptionPane.showInternalConfirmDialog(Environment.getActiveDesktop(), _jtPassword, "Contraseña", JOptionPane.OK_CANCEL_OPTION);
	        _password = new String(_jtPassword.getPassword());
	    }
	    engineConfig.setPropietaryConnectionParams(_serverURL, _database, _user, _password);
	    for (int i = 0; i < geometrySets.size(); i++) {
	        if (geometrySets.elementAt(i).getGeometrySetConfig().isPropietary()) {
		    geometrySets.elementAt(i).getGeometrySetConfig().setConnectionParams(_serverURL, _database, _user, _password);
		}
	    }
	} else {
	    engineConfig.setPropietaryConnectionParams("", "", "", "");
	    Advisor.messageBox("Puede configurarlo después presionando Ctrl-1", "Configuración");
	}
    }
    
    private void limpiarCache() {
	if (Advisor.question("Configuración", "¿Desea limpiar el caché del GIS?\nSe borarrán los archivos en el directorio " + GaiaEnvironment.tempDir)) {
	    File[] files = GaiaEnvironment.tempDir.listFiles();
	    StringBuilder _failures = new StringBuilder("");
	    for (File file:files) {
		if (!file.delete()) {
		    _failures.append(file + "\n");
		}
	    }
	    if (_failures.length() > 0) {
	        Advisor.messageBox(_failures.insert(0,"No se pudieron borrar los siguientes archivos:\n").toString(), "Limpieza de caché");
	    } else {
	        Advisor.messageBox("Limpieza de caché exitosa", "Limpieza de caché");
	    }
	}
    }
    
    public void initialize() {
	addGeometrySets(GaiaEnvironment.geometrySets);
	addLayerGroups(GaiaEnvironment.layerGroups);
    }
}
