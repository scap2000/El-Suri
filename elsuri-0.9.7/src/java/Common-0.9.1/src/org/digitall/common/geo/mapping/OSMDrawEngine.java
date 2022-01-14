package org.digitall.common.geo.mapping;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import com.ibm.util.CoordinateConversion;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.common.geo.mapping.osm.Coordinate;
import org.digitall.common.geo.mapping.osm.MemoryTileCache;
import org.digitall.common.geo.mapping.osm.OsmMercator;
import org.digitall.common.geo.mapping.osm.Tile;
import org.digitall.common.geo.mapping.osm.TileController;
import org.digitall.common.geo.mapping.osm.interfaces.MapMarker;
import org.digitall.common.geo.mapping.osm.interfaces.MapRectangle;
import org.digitall.common.geo.mapping.osm.interfaces.TileCache;
import org.digitall.common.geo.mapping.osm.interfaces.TileLoader;
import org.digitall.common.geo.mapping.osm.interfaces.TileLoaderListener;
import org.digitall.common.geo.mapping.osm.interfaces.TileSource;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.OsnapPoints;
import org.digitall.lib.geo.mapping.classes.StyleConfig;
import org.digitall.lib.geo.shapefile.SHPPolygon;
import org.digitall.lib.geo.shapefile.ShapeTypes;

/**
 *
 * Provides a simple panel that displays pre-rendered map tiles loaded from the
 * OpenStreetMap project.
 *
 * @author Jan Peter Stotz
 *
 */
public class OSMDrawEngine extends DrawEnginePanel implements TileLoaderListener {

    private static final long serialVersionUID = 1L;

    /**
     * Vectors for clock-wise tile painting
     */
    protected static final Point[] move = { new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1) };

    public static final int MAX_ZOOM = 22;
    public static final int MIN_ZOOM = 0;

    protected List<MapMarker> mapMarkerList;
    protected List<MapRectangle> mapRectangleList;

    protected boolean mapMarkersVisible;
    protected boolean mapRectanglesVisible;

    protected boolean tileGridVisible;

    protected TileController tileController;

    /**
     * x- and y-position of the center of this map-panel on the world map
     * denoted in screen pixel regarding the current _currentZoomLevel level.
     */
    protected Point center;

    /**
     * Current _currentZoomLevel level
     */
    protected int _currentZoomLevel;

    protected JSlider zoomSlider;
    protected JButton zoomInButton;
    protected JButton zoomOutButton;

    private TileSource tileSource;

    // Attribution
    private Image attrImage;
    private String attrTermsUrl;
    public static final Font ATTR_FONT = new Font("Arial", Font.PLAIN, 10);
    public static final Font ATTR_LINK_FONT;



    private boolean wheelZoomEnabled = true;
    private Point lastDragPoint;
    private boolean movementEnabled = true;
    private int movementMouseButton = MouseEvent.BUTTON2;
    private int movementMouseButtonMask = MouseEvent.BUTTON2_DOWN_MASK;
    private boolean isMoving = false;
    private static final int MAC_MOUSE_BUTTON3_MASK = MouseEvent.CTRL_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
    private boolean doubleClickZoomEnabled = true;
    private static final int MOUSE_BUTTONS_MASK = MouseEvent.BUTTON3_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK
    | MouseEvent.BUTTON2_DOWN_MASK;
    private Vector<LayerGroup> layerGroups = new Vector<LayerGroup>();
    private Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
    private int containedShapeIDS[][] = new int[0][0];
    private Vector<Layer> layers = new Vector<Layer>();
    private OsnapPoints osnapPointsVector = new OsnapPoints();
    private BasicDrawEngineConfig engineConfig = new BasicDrawEngineConfig();
    private RuleViewer ruleViewer;
    private CoordinateViewer coordinateViewer;
    private Graphics2D labelGraphics2D = null;
    private Point2D[] mapExtents = new Point2D[0];
    private Point startDragPosition = null;
    protected Line2D panLine = new Line2D.Float();
    private Vector<Point2D.Double> drawingPointsVector = new Vector<Point2D.Double>();
    private Point currentPosition;
    private BufferedImage geoTiff = null;
    private String tfw[] = null;
    
    static {
        HashMap<TextAttribute, Integer> aUnderline = new HashMap<TextAttribute, Integer>();
        aUnderline.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        ATTR_LINK_FONT = ATTR_FONT.deriveFont(aUnderline);
    }

    /**
     * Creates a standard {@link JMapViewer}instance that can be controlled via
     * mouse: hold right mouse button for moving, double click left mouse button
     * or use mouse wheel for zooming. Loaded tiles are stored the
     * {@link org.digitall.common.geo.mapping.osm.MemoryTileCache}and the tile loader uses 4 parallel threads for
     * retrieving the tiles.
     */
    public OSMDrawEngine() {
        this(new MemoryTileCache(), 4);
        //new DefaultMapController(this);
    }

    public OSMDrawEngine(TileCache tileCache, int downloadThreadCount) {
        super();
        tileSource = new OsmTileSource.Mapnik();
        tileController = new TileController(tileSource, tileCache, this);
        mapMarkerList = new LinkedList<MapMarker>();
        mapRectangleList = new LinkedList<MapRectangle>();
        mapMarkersVisible = true;
        mapRectanglesVisible = true;
        tileGridVisible = false;
        setLayout(null);
        initializeZoomSlider();
        setMinimumSize(new Dimension(tileSource.getTileSize(), tileSource.getTileSize()));
        setPreferredSize(new Dimension(400, 400));
        setDisplayPositionByLatLon(50, 9, 3);
        //setToolTipText("");

	addMouseWheelListener(commonWheelListener);
	addMouseListener(commonMouseListener);
	addMouseMotionListener(commonMotionListener);

	addMouseListener(distanceMouseListener);
	addMouseMotionListener(distanceMotionListener);

    }

    @Override
    public String getToolTipText(MouseEvent event) {
        //        Point screenPoint = event.getLocationOnScreen();
        //        Coordinate c = getPosition(screenPoint);
        return super.getToolTipText(event);
    }

    protected void initializeZoomSlider() {
        zoomSlider = new JSlider(MIN_ZOOM, tileController.getTileSource().getMaxZoom());
        zoomSlider.setOrientation(JSlider.VERTICAL);
        zoomSlider.setBounds(10, 10, 30, 150);
        zoomSlider.setOpaque(false);
        zoomSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                set_currentZoomLevel(zoomSlider.getValue());
            }
        });
        add(zoomSlider);
        int size = 18;
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/plus.png"));
            zoomInButton = new JButton(icon);
        } catch (Exception e) {
            zoomInButton = new JButton("+");
            zoomInButton.setFont(new Font("sansserif", Font.BOLD, 9));
            zoomInButton.setMargin(new Insets(0, 0, 0, 0));
        }
        zoomInButton.setBounds(4, 155, size, size);
        zoomInButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });
        add(zoomInButton);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/minus.png"));
            zoomOutButton = new JButton(icon);
        } catch (Exception e) {
            zoomOutButton = new JButton("-");
            zoomOutButton.setFont(new Font("sansserif", Font.BOLD, 9));
            zoomOutButton.setMargin(new Insets(0, 0, 0, 0));
        }
        zoomOutButton.setBounds(8 + size, 155, size, size);
        zoomOutButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });
        add(zoomOutButton);
    }

    /**
     * Changes the map pane so that it is centered on the specified coordinate
     * at the given _currentZoomLevel level.
     *
     * @param lat
     * latitude of the specified coordinate
     * @param lon
     * longitude of the specified coordinate
     * @param zoom
     * {@link #MIN_ZOOM}<= _currentZoomLevel level <= {@link #MAX_ZOOM}
     */
    public void setDisplayPositionByLatLon(double lat, double lon, int zoom) {
        setDisplayPositionByLatLon(new Point(getWidth() / 2, getHeight() / 2), lat, lon, zoom);
    }

    /**
     * Changes the map pane so that the specified coordinate at the given _currentZoomLevel
     * level is displayed on the map at the screen coordinate
     * <code>mapPoint</code>.
     *
     * @param mapPoint
     * point on the map denoted in pixels where the coordinate should
     * be set
     * @param lat
     * latitude of the specified coordinate
     * @param lon
     * longitude of the specified coordinate
     * @param zoom
     * {@link #MIN_ZOOM}<= _currentZoomLevel level <=
     * {@link org.digitall.common.geo.mapping.osm.interfaces.TileSource#getMaxZoom()}
     */
    public void setDisplayPositionByLatLon(Point mapPoint, double lat, double lon, int zoom) {
        int x = OsmMercator.LonToX(lon, zoom);
        int y = OsmMercator.LatToY(lat, zoom);
        setDisplayPosition(mapPoint, x, y, zoom);
    }

    public void setDisplayPosition(int x, int y, int zoom) {
        setDisplayPosition(new Point(getWidth() / 2, getHeight() / 2), x, y, zoom);
    }

    public void setDisplayPosition(Point mapPoint, int x, int y, int zoom) {
        if (zoom > tileController.getTileSource().getMaxZoom() || zoom < MIN_ZOOM)
            return;

        // Get the plain tile number
        Point p = new Point();
        p.x = x - mapPoint.x + getWidth() / 2;
        p.y = y - mapPoint.y + getHeight() / 2;
        center = p;
        setIgnoreRepaint(true);
        try {
            int oldZoom = this._currentZoomLevel;
            this._currentZoomLevel = zoom;
            if (oldZoom != zoom) {
                zoomChanged(oldZoom);
            }
            if (zoomSlider.getValue() != zoom) {
                zoomSlider.setValue(zoom);
            }
        } finally {
            setIgnoreRepaint(false);
            repaint();
        }
    }

    /**
     * Sets the displayed map pane and _currentZoomLevel level so that all map markers are
     * visible.
     */
    public void setDisplayToFitMapMarkers() {
        if (mapMarkerList == null || mapMarkerList.size() == 0)
            return;
        int x_min = Integer.MAX_VALUE;
        int y_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int mapZoomMax = tileController.getTileSource().getMaxZoom();
        for (MapMarker marker : mapMarkerList) {
            int x = OsmMercator.LonToX(marker.getLon(), mapZoomMax);
            int y = OsmMercator.LatToY(marker.getLat(), mapZoomMax);
            x_max = Math.max(x_max, x);
            y_max = Math.max(y_max, y);
            x_min = Math.min(x_min, x);
            y_min = Math.min(y_min, y);
        }
        int height = Math.max(0, getHeight());
        int width = Math.max(0, getWidth());
        // System.out.println(x_min + " < x < " + x_max);
        // System.out.println(y_min + " < y < " + y_max);
        // System.out.println("tiles: " + width + " " + height);
        int newZoom = mapZoomMax;
        int x = x_max - x_min;
        int y = y_max - y_min;
        while (x > width || y > height) {
            // System.out.println("zoom: " + zoom + " -> " + x + " " + y);
            newZoom--;
            x >>= 1;
            y >>= 1;
        }
        x = x_min + (x_max - x_min) / 2;
        y = y_min + (y_max - y_min) / 2;
        int z = 1 << (mapZoomMax - newZoom);
        x /= z;
        y /= z;
        setDisplayPosition(x, y, newZoom);
    }

    /**
     * Sets the displayed map pane and _currentZoomLevel level so that all map markers are
     * visible.
     */
    public void setDisplayToFitMapRectangle() {
        if (mapRectangleList == null || mapRectangleList.size() == 0)
            return;
        int x_min = Integer.MAX_VALUE;
        int y_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;
        int y_max = Integer.MIN_VALUE;
        int mapZoomMax = tileController.getTileSource().getMaxZoom();
        for (MapRectangle rectangle : mapRectangleList) {
            x_max = Math.max(x_max, OsmMercator.LonToX(rectangle.getBottomRight().getLon(), mapZoomMax));
            y_max = Math.max(y_max, OsmMercator.LatToY(rectangle.getTopLeft().getLat(), mapZoomMax));
            x_min = Math.min(x_min, OsmMercator.LonToX(rectangle.getTopLeft().getLon(), mapZoomMax));
            y_min = Math.min(y_min, OsmMercator.LatToY(rectangle.getBottomRight().getLat(), mapZoomMax));
        }
        int height = Math.max(0, getHeight());
        int width = Math.max(0, getWidth());
        // System.out.println(x_min + " < x < " + x_max);
        // System.out.println(y_min + " < y < " + y_max);
        // System.out.println("tiles: " + width + " " + height);
        int newZoom = mapZoomMax;
        int x = x_max - x_min;
        int y = y_max - y_min;
        while (x > width || y > height) {
            // System.out.println("zoom: " + zoom + " -> " + x + " " + y);
            newZoom--;
            x >>= 1;
            y >>= 1;
        }
        x = x_min + (x_max - x_min) / 2;
        y = y_min + (y_max - y_min) / 2;
        int z = 1 << (mapZoomMax - newZoom);
        x /= z;
        y /= z;
        setDisplayPosition(x, y, newZoom);
    }

    /**
     * Calculates the latitude/longitude coordinate of the center of the
     * currently displayed map area.
     * 
     * @return latitude / longitude
     */
    public Coordinate getPosition() {
        double lon = OsmMercator.XToLon(center.x, _currentZoomLevel);
        double lat = OsmMercator.YToLat(center.y, _currentZoomLevel);
        return new Coordinate(lat, lon);
    }

    /**
     * Converts the relative pixel coordinate (regarding the top left corner of
     * the displayed map) into a latitude / longitude coordinate
     * 
     * @param mapPoint
     *            relative pixel coordinate regarding the top left corner of the
     *            displayed map
     * @return latitude / longitude
     */
    public Coordinate getPosition(Point mapPoint) {
        return getPosition(mapPoint.x, mapPoint.y);
    }

    /**
     * Converts the relative pixel coordinate (regarding the top left corner of
     * the displayed map) into a latitude / longitude coordinate
     * 
     * @param mapPointX
     * @param mapPointY
     * @return
     */
    public Coordinate getPosition(int mapPointX, int mapPointY) {
        int x = center.x + mapPointX - getWidth() / 2;
        int y = center.y + mapPointY - getHeight() / 2;
        double lon = OsmMercator.XToLon(x, _currentZoomLevel);
        double lat = OsmMercator.YToLat(y, _currentZoomLevel);
        return new Coordinate(lat, lon);
    }

    /**
     * Calculates the position on the map of a given coordinate
     * 
     * @param lat
     * @param lon
     * @param checkOutside
     * @return point on the map or <code>null</code> if the point is not visible
     *         and checkOutside set to <code>true</code>
     */
    public Point getMapPosition(double lat, double lon, boolean checkOutside) {
        int x = OsmMercator.LonToX(lon, _currentZoomLevel);
        int y = OsmMercator.LatToY(lat, _currentZoomLevel);
        x -= center.x - getWidth() / 2;
        y -= center.y - getHeight() / 2;
        if (checkOutside) {
            if (x < 0 || y < 0 || x > getWidth() || y > getHeight())
                return null;
        }
        return new Point(x, y);
    }

    /**
     * Calculates the position on the map of a given coordinate
     * 
     * @param lat
     * @param lon
     * @return point on the map or <code>null</code> if the point is not visible
     */
    public Point getMapPosition(double lat, double lon) {
        return getMapPosition(lat, lon, true);
    }

    /**
     * Calculates the position on the map of a given coordinate
     * 
     * @param coord
     * @return point on the map or <code>null</code> if the point is not visible
     */
    public Point getMapPosition(Coordinate coord) {
        if (coord != null)
            return getMapPosition(coord.getLat(), coord.getLon());
        else
            return null;
    }

    /**
     * Calculates the position on the map of a given coordinate
     * 
     * @param coord
     * @return point on the map or <code>null</code> if the point is not visible
     *         and checkOutside set to <code>true</code>
     */
    public Point getMapPosition(Coordinate coord, boolean checkOutside) {
        if (coord != null)
            return getMapPosition(coord.getLat(), coord.getLon(), checkOutside);
        else
            return null;
    }

    @Override
    protected void paintComponent(Graphics _graphics) {
        super.paintComponent(_graphics);

        int iMove = 0;

        int tilesize = tileSource.getTileSize();
        int tilex = center.x / tilesize;
        int tiley = center.y / tilesize;
        int off_x = (center.x % tilesize);
        int off_y = (center.y % tilesize);

        int w2 = getWidth() / 2;
        int h2 = getHeight() / 2;
        int posx = w2 - off_x;
        int posy = h2 - off_y;

        int diff_left = off_x;
        int diff_right = tilesize - off_x;
        int diff_top = off_y;
        int diff_bottom = tilesize - off_y;

        boolean start_left = diff_left < diff_right;
        boolean start_top = diff_top < diff_bottom;

        if (start_top) {
            if (start_left) {
                iMove = 2;
            } else {
                iMove = 3;
            }
        } else {
            if (start_left) {
                iMove = 1;
            } else {
                iMove = 0;
            }
        } // calculate the visibility borders
        int x_min = -tilesize;
        int y_min = -tilesize;
        int x_max = getWidth();
        int y_max = getHeight();

        // paint the tiles in a spiral, starting from center of the map
        boolean painted = true;
        int x = 0;
        while (painted) {
            painted = false;
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    x++;
                }
                for (int j = 0; j < x; j++) {
                    if (x_min <= posx && posx <= x_max && y_min <= posy && posy <= y_max) {
                        // tile is visible
                        Tile tile = tileController.getTile(tilex, tiley, _currentZoomLevel);
                        if (tile != null) {
                            painted = true;
                            tile.paint(_graphics, posx, posy);
                            if (tileGridVisible) {
                                _graphics.drawRect(posx, posy, tilesize, tilesize);
                            }
                        }
                    }
                    Point p = move[iMove];
                    posx += p.x * tilesize;
                    posy += p.y * tilesize;
                    tilex += p.x;
                    tiley += p.y;
                }
                iMove = (iMove + 1) % move.length;
            }
        }
        // outer border of the map
        int mapSize = tilesize << _currentZoomLevel;
        _graphics.drawRect(w2 - center.x, h2 - center.y, mapSize, mapSize);

        // g.drawString("Tiles in cache: " + tileCache.getTileCount(), 50, 20);

        if (mapRectanglesVisible && mapRectangleList != null) {
            for (MapRectangle rectangle : mapRectangleList) {
                Coordinate topLeft = rectangle.getTopLeft();
                Coordinate bottomRight = rectangle.getBottomRight();
                if (topLeft != null && bottomRight != null) {
                    Point pTopLeft = getMapPosition(topLeft.getLat(), topLeft.getLon(), false);
                    Point pBottomRight = getMapPosition(bottomRight.getLat(), bottomRight.getLon(), false);
                    if (pTopLeft != null && pBottomRight != null) {
                        rectangle.paint(_graphics, pTopLeft, pBottomRight);
                    }
                }
            }
        }

	if (geoTiff != null) {

	    /**
	    Ahora a escalar la imagen!!!
	    Calculo el tamaño
	    System.out.println(geoTiff.getWidth());
	    15494 --> Convertir calculando con tfw[0]
	    System.out.println(geoTiff.getHeight());
	    16581
	    
	    System.out.println(_ll.getLatitude());
	    -24.994747260332176
	    System.out.println(_ll.getLongitude());
	    -65.4468432747274
	    */
	    
	    LatLongCoord _startLatLong = CoordinateSystems.utm2geo(Double.parseDouble(tfw[4]), Double.parseDouble(tfw[5]), 20);
	    LatLongCoord _endLatLong = CoordinateSystems.utm2geo(
	                        Double.parseDouble(tfw[4])+(geoTiff.getWidth(this)*+Double.parseDouble(tfw[0])),
	                        Double.parseDouble(tfw[5])+(geoTiff.getHeight(this)*-Double.parseDouble(tfw[0])),
	                        20);

	    System.out.println("STARTLONG: " + _startLatLong.getLongD() + "º " + _startLatLong.getLongM() + "' " + _startLatLong.getLongS());
	    System.out.println("STARTLAT: " + _startLatLong.getLatD() + "º " + _startLatLong.getLatM() + "' " + _startLatLong.getLatS());
	    System.out.println("ENDLONG: " + _endLatLong.getLongD() + "º " + _endLatLong.getLongM() + "' " + _endLatLong.getLongS());
	    System.out.println("ENDLAT: " + _endLatLong.getLatD() + "º " + _endLatLong.getLatM() + "' " + _endLatLong.getLatS());
	    System.out.println("STARTLATLONG: " + _startLatLong.getLongitude() + "    " + _startLatLong.getLatitude());
	    System.out.println("ENDLATLONG: " + _endLatLong.getLongitude() + "    " + _endLatLong.getLatitude());



	    _startLatLong.setLatD(24);
	    _startLatLong.setLatM(59);
	    _startLatLong.setLatS(41.0901);
	    _startLatLong.setLongD(65);
	    _startLatLong.setLongM(26);
	    _startLatLong.setLongS(50.2919);


	    _endLatLong.setLatD(25);
	    _endLatLong.setLatM(01);
	    _endLatLong.setLatS(6.0344);
	    _endLatLong.setLongD(65);
	    _endLatLong.setLongM(25);
	    _endLatLong.setLongS(22.9792);

	    Point _imagexy = getMapPosition(_startLatLong.getLatitude(), _startLatLong.getLongitude(), false);
	    Point _imagewz = getMapPosition(_endLatLong.getLatitude(), _endLatLong.getLongitude(), false);
	    
	    
	    CoordinateConversion _calc = new CoordinateConversion();
	    double[] f = _calc.utm2LatLon("20 H " + tfw[4] + " " + tfw[5]);
	    System.out.println(f[0] + "     " + f[1]);
	    System.out.println(_startLatLong.getLatitude() + "   " + _startLatLong.getLongitude());
	    double[] g = _calc.utm2LatLon("20 H " + (Double.parseDouble(tfw[4])+geoTiff.getWidth(this)*Double.parseDouble(tfw[0])) + " " + (Double.parseDouble(tfw[5])+geoTiff.getHeight(this)*-Double.parseDouble(tfw[0])));
	    System.out.println(g[0] + "     " + g[1]);
	    Point _imageuv = getMapPosition(g[0], g[1], false);
	    System.out.println(_endLatLong.getLatitude() + "   " + _endLatLong.getLongitude());
	    _graphics.drawImage(geoTiff, _imagexy.x, _imagexy.y, _imagewz.x-_imagexy.x, _imagewz.y-_imagexy.y, this);
	    //_graphics.drawImage(geoTiff, _imagexy.x, _imagexy.y, _imageuv.x-_imagexy.x, _imageuv.y-_imagexy.y, this);

	}

        if (mapMarkersVisible && mapMarkerList != null) {
            for (MapMarker marker : mapMarkerList) {
                paintMarker(_graphics, marker);
            }
        }
	labelGraphics2D = (Graphics2D)_graphics.create();
	setEnvironment();
	Graphics2D _graphics2D = (Graphics2D)_graphics;
	drawLayers(_graphics2D, labelGraphics2D, engineConfig);
        paintAttribution(_graphics);
	_graphics2D.setColor(Color.yellow);
	_graphics2D.setStroke(new BasicStroke(3));
	_graphics2D.draw(panLine);
	_graphics2D.setStroke(new BasicStroke(3));
	//DIBUJO EL POLIGONO DE CALCULO DE DISTANCIAS
	//Try catch obligatorio por NullPointer
	//Cuando algún punto se sale del mapa da null
	if (drawingPointsVector.size() >= 1) {

	    try {
		_graphics2D.setColor(Color.red);
		if (drawingPointsVector.size() > 1) {
		    for (int i = 0; i < drawingPointsVector.size() - 1; i++) //Fijarse si es size()-1
		    {
			Point x0y0 = getMapPosition(drawingPointsVector.elementAt(i).getY(), drawingPointsVector.elementAt(i).getX());
			Point x1y1 = getMapPosition(drawingPointsVector.elementAt(i + 1).getY(), drawingPointsVector.elementAt(i + 1).getX());
			_graphics2D.drawLine(x0y0.x, x0y0.y, x1y1.x, x1y1.y);
		    }
		    Point x0y0 = getMapPosition(drawingPointsVector.elementAt(drawingPointsVector.size() - 1).getY(), drawingPointsVector.elementAt(drawingPointsVector.size() - 1).getX());
		    Point x1y1 = getMapPosition(drawingPointsVector.elementAt(0).getY(), drawingPointsVector.elementAt(0).getX());
		    //_graphics2D.drawLine(x0y0.x, x0y0.y, x1y1.x, x1y1.y);
		} else {
		}

		Point x0y0 = getMapPosition(drawingPointsVector.elementAt(drawingPointsVector.size() - 1).getY(), drawingPointsVector.elementAt(drawingPointsVector.size() - 1).getX());
		Point x1y1 = currentPosition;
		_graphics2D.drawLine(x0y0.x, x0y0.y, x1y1.x, x1y1.y);

		Point x2y2 = getMapPosition(drawingPointsVector.elementAt(0).getY(), drawingPointsVector.elementAt(0).getX());
		_graphics2D.drawLine(x1y1.x, x1y1.y, x2y2.x, x2y2.y);
	    } catch (NullPointerException e) {
		//e.printStackTrace();
	    }
	}
	_graphics2D.setStroke(new BasicStroke(1));
    }

    private void tryToDrawPic() {
	//Leo las 6 líneas del archivo tfw
	/*
	The format of the tfw file is 6 line text file:

	increment on latitude( I mean for exemple mxp on X coordinate)
	unused
	unused
	increment on longitude( I mean for exemple mxp on Y coordinate)
	longitude coordinate for the top left point of the image
	latitude coordinate for the top left point of the image

	For example suppose you ave an UTM geotiff (misure are expressed in meters)
	your tfw will be (it is from a geotiff of Rome);

	1.0 (1 meter for pixel along X)
	0 (unused)
	0 (unused)
	-1.0 (1 meter for pixel along Y: is negative cause latitude decrease along Y)
	280103.5 (X coord in meteres for top left)
	4650186.5 (Y coord in meteres for top left)

	0.15511
	0
	0
	-0.15511
	253030.65704
	7233404.74739

	*/
	
	tfw = new String[6];
	    // line is not visible here.
	int i = 0;
	boolean loaded = false;
	try {
	    BufferedReader br = new BufferedReader(new FileReader("/tmp/geotiff/b.tfw"));
	    for(String line; (line = br.readLine()) != null; ) {
		tfw[i] = line;
		i++;
		System.out.println(line);
	    }
	    loaded = true;
	    // process the line.

	    final BufferedImage image = ImageIO.read(new File("/tmp/geotiff/b.png"));

	    BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), 
							   BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = (Graphics2D) transparentImage.getGraphics();
	    g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); 
		     // set the transparency level in range 0.0f - 1.0f 
	    g2d.drawImage(image, 0, 0, null);

	    ImageFilter filter = new RGBImageFilter() {
	      // the color we are looking for... Alpha bits are set to opaque
	    Color color = Color.black;
	      public int markerRGB = color.getRGB() | 0xFF000000;

	      public final int filterRGB(int x, int y, int rgb) {
	        if ( ( rgb | 0xFF000000 ) == markerRGB ) {
	          // Mark the alpha bits as zero - transparent
	          return 0x00FFFFFF & rgb;
	          }
	        else {
	          // nothing to do
	          return rgb;
	          }
	        }
	      }; 

	    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    geoTiff = image;

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    geoTiff = null;
	    tfw = null;
	} catch (IOException e) {
	    e.printStackTrace();
	    geoTiff = null;
	    tfw = null;
	}
    }

    private static BufferedImage imageToBufferedImage(final Image image)  
    {  
       final BufferedImage bufferedImage =  
	  new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);  
       final Graphics2D g2 = bufferedImage.createGraphics();  
       g2.drawImage(image, 0, 0, null);  
       g2.dispose();  
       return bufferedImage;  
     }  
    
    private void drawLayers(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, BasicDrawEngineConfig _engineConfig) {
       try {
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
			    if (_layers.elementAt(j).isOn()) {
				Layer _layer = _layers.elementAt(j);
				if ((_pointDiameter * _engineConfig.getDrawFactor() >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _pointDiameter * _engineConfig.getDrawFactor() > 2)) && (_pointDiameter * _engineConfig.getDrawFactor() <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
				    boolean isSelectedLayer = true;//(_layer == selectedLayer);
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
					if (_drawPoint || isSelectedLayer) {
					    if (false) { //mouseActive && !rasterImageMatrixMode) {
						if (_styleConfig.getOutlineColor() != null) {
						    _graphics2D.setColor(_styleConfig.getOutlineColor());
						    //_graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
						    _graphics2D.draw(_point);
						    _drawn = true;
						} else {
						    if (_styleConfig.getFillColor() != null) {
							_graphics2D.setColor(_styleConfig.getFillColor());
							//_graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
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
							//_graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
							_graphics2D.draw(_point);
							_drawn = true;
						    }
						}
					    }
					    //addOsnapPoint(_points[i]);
					    if (false) {//mouseActive && !rasterImageMatrixMode) {
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
							   // _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
							    _graphics2D.draw(_point);
							    _drawn = true;
							}
						    }
						}
						/*if (foundGeometryID == i && _layer.getAlias().equals(foundLayer)) {
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
						}*/
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
			     if (_layers.elementAt(j).isOn()) {
				 Layer _layer = _layers.elementAt(j);
				 boolean isSelectedLayer = true; //(_layer == selectedLayer);
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
				     if (true) {
					 if (_drawPolyline) {
					     if (_engineConfig.isOsnapActive()) {
						 ESRIPolygon _polyline = getFakePolygon(_polylines[i], _engineConfig);
						 for (int k = 0; k < _polylines[i].getVertexCount(); k++) {
						     if (getBounds().contains(_polyline.getX(k), _polyline.getY(k))) {
							 //addOsnapPoint(new ESRIPoint(_polylines[i].getX(k), _polylines[i].getY(k)));
						     }
						 }
					     }
					     if (false) {//mouseActive || rasterImageMatrixMode) {
						 if (_polylines[i].getBounds2D().getWidth()> 20 && _polylines[i].getBounds2D().getHeight()> 20) {
						     _graphics2d.setStroke(new BasicStroke(1));
						     _drawn = true;
						 }
					     } else {
						 //_graphics2d.setStroke(getStyleConfigStroke(_styleConfig));
						 _drawn = true;
						 if (_polylines[i].getSymbol() != -1) {
						     _symbol = _polylines[i].getSymbol();
						 }
					     }
					     //_graphics2d.drawPolyline(_engineConfig.xtoPixel(_polylines[i].getXCoords()), _engineConfig.ytoPixel(_polylines[i].getYCoords()), _polylines[i].getVertexCount());
					     _graphics2d.drawPolyline(transformToPixelsX(_polylines[i].getXCoords()), transformToPixelsY(_polylines[i].getYCoords()), _polylines[i].getVertexCount());
					 } //fi layer.drawGeometries()
					 if (true) {
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

    private void drawPolygons(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
	ESRIPolygon _bounds = toSpace(getBounds(), _engineConfig);
	for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++)  {
	    for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++)  {
		if (true) {//_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
		    ESRIPolygon[] _polygons = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m,n));
		    for (int i = 0; i < _polygons.length; i++) {
			Vector<Layer> _layers = _geometrySet.getLayers();
			int j = _layers.size()-1;
			boolean _drawn = false;
			while (j >= 0 && !_drawn)  {
			    if (/*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
				Layer _layer = _layers.elementAt(j);
				boolean isSelectedLayer = true;//(_layer == selectedLayer);
				boolean _drawPolygon = _layer.drawGeometries();
				boolean _selectPolygon = false;
				if (_layer.isQueryable()) { // || (_layer == selectedLayer)) {
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
				if (true) {//_bounds.intersects(_polygons[i].getBounds2D())) {// && _polygons[i].getBounds2D().getWidth()*_engineConfig.getDrawFactor()>10) {
				    //double _width = _polygons[i].getBounds2D().getWidth() * _engineConfig.getDrawFactor();
				    //double _heigth = _polygons[i].getBounds2D().getHeight() * _engineConfig.getDrawFactor();
				    if (true) { //((_width >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _width > 2)) && (_heigth >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _heigth > 2)) && (_width <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0) && (_heigth <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
					if (/*_layer.drawGeometries() || */_drawPolygon || isSelectedLayer) {
					    ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
					    if (_engineConfig.isOsnapActive()) {
						/*if (_polygon.getBounds().contains(mousePosition)) {
						    addOsnapPoints(_polygons[i]);
						}*/
					    }
					    /*if (mouseActive && !rasterImageMatrixMode) {
						if (_polygon.getBounds2D().getWidth()> 20 && _polygon.getBounds2D().getHeight()> 20) {
						    _graphics2D.setColor(_styleConfig.getFillColor()!=null?_styleConfig.getFillColor():_styleConfig.getOutlineColor());
						    _graphics2D.setStroke(new BasicStroke(1));
						    _graphics2D.draw(_polygon);
						    _drawn = true;
						}
					    } else */{
						if (_styleConfig.getFillColor() != null) {
						    _graphics2D.setColor(_styleConfig.getFillColor());
						    _graphics2D.fill(_polygon);
						    _drawn = true;
						}
						if (_styleConfig.getOutlineColor() != null) {
						    _graphics2D.setColor(_styleConfig.getOutlineColor());
						    _graphics2D.setStroke(getStyleConfigStroke(_styleConfig));
						    
						    /************************/ //Temporales
						   /* _graphics2D.setStroke(new BasicStroke(3));
						    _graphics2D.setColor(Color.CYAN.brighter());
						    Composite originalComposite = _graphics2D.getComposite();
						    _graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
						    _graphics2D.fill(_polygon);
						    _graphics2D.setComposite(originalComposite);
						    _graphics2D.setColor(new Color(0,64,0));*/
						    /***********************/
					    
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
						    /** Obtengo el centroide del polígono para poner la etiqueta */
						    Point _centroid = getMapPosition(_polygons[i].getCentroid().getY(), _polygons[i].getCentroid().getX(), false);
						    double x = _centroid.x - (int)(metricBounds.getWidth() / 2.0);
						    double y = _centroid.y + (int)(metricBounds.getHeight() / 4.0);
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
							 /*else if (_polygon.contains(_totalBounds.getMaxX(), _totalBounds.getMaxY()) && bottomRightComponent != null) {
							    if (!bottomRightComponent.isVisible()) {
								x = _totalBounds.getMaxX() - metricBounds.getWidth();
								y = _totalBounds.getMaxY() - 10;
							    }
							}*/
						    }
						    _labelGraphics2D.drawString(_label, (int)x, (int)y);
						}
					    }
					    /*if (foundGeometryID == _polygons[i].getIdPolygon() && _layer.getAlias().equals(foundLayer)) {
						ESRIPolygon _polygon = getFakePolygon(_polygons[i], _engineConfig);
						_graphics2D.setColor(_styleConfig.getSelectedColor() != null?_styleConfig.getSelectedColor(): Color.orange);//--_polygon.getSelectedColor());
						_graphics2D.fill(_polygon);
						_graphics2D.setColor(_styleConfig.getSelectedOutlineColor() != null?_styleConfig.getSelectedOutlineColor(): Color.orange);//--_polygon.getSelectedColor());
						_graphics2D.setStroke(new BasicStroke(_styleConfig.getLineWidth()));
						_graphics2D.draw(_polygon);
						_drawn = true;
					    }*/
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

    /**
     * Wrap the given pixel value if appropriate and possible
     * @param inPx Pixel x coordinate
     * @param inWinWidth window width in pixels
     * @param inZoom zoom level
     * @return modified pixel x coordinate
     */
    private int wrapLongitudeValue(int inPx, int inWinWidth, int inZoom)
    {
	    if (inPx > inWinWidth)
	    {
		    // Pixel is too far right, could we wrap it back onto the screen?
		    int px = inPx;
		    while (px > inWinWidth) {
			    px -= (256 << inZoom);
		    }
		    if (px >= 0) {
			    return px; // successfully wrapped back onto the screen
		    }
	    }
	    else if (inPx < 0)
	    {
		    // Pixel is too far left, could we wrap it back onto the screen?
		    int px = inPx;
		    while (px < 0) {
			    px += (256 << inZoom);
		    }
		    if (px < inWinWidth) {
			    return px; // successfully wrapped back onto the screen
		    }
	    }
	    // Either it's already on the screen or couldn't be wrapped
	    return inPx;
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

    private ESRIPolygon getFakePolygon(ESRIPolygon _poly, BasicDrawEngineConfig _engineConfig) {
	double[] xy = new double[_poly.getVertexCount() * 2];
	for (int i = 0; i < _poly.getVertexCount() * 2; i += 2) {
	    /*xy[i] = transformToPixels(_poly.getX(i / 2) - _engineConfig.getXOffset());
	    xy[i + 1] = transformToPixels(_poly.getY(i / 2) - _engineConfig.getYOffset());*/
	    Point _p = getMapPosition(_poly.getY(i / 2), _poly.getX(i / 2), false);
	    xy[i] = _p.getX();
	    xy[i+1] = _p.getY();
	    //System.out.println("X,Y: " + xy[i] + "," + xy[i+1]);
	}
	return new ESRIPolygon.Double(xy);
    }

    private int transformToPixels(double inValue)
    {
	    /* (1 << inZoom) es igual a 2^inZoom */
	    return (int) (inValue * 256 * (1 << _currentZoomLevel));
    }

    public int[] transformToPixelsX(double[] _points) {
	int[] xpoints = new int[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    xpoints[i] = engineConfig.getFWidth()/2 + transformToPixels(_points[i] - engineConfig.getXOffset());
	    //wrapLongitudeValue(xpoints[i], engineConfig.getFWidth(), _currentZoomLevel);
	    //System.out.println(xpoints[i]);
	}
	return xpoints;
    }

    public int[] transformToPixelsY(double[] _points) {
	int[] xpoints = new int[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    xpoints[i] = engineConfig.getFHeight()/2 - transformToPixels(_points[i] - engineConfig.getYOffset());
	    //System.out.println(xpoints[i]);
	}
	return xpoints;
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


    /**
     * Paint a single marker.
     */
    protected void paintMarker(Graphics g, MapMarker marker) {
        Point p = getMapPosition(marker.getLat(), marker.getLon());
        if (p != null) {
            marker.paint(g, p);
        }
    }

    /**
     * Moves the visible map pane.
     * 
     * @param x
     *            horizontal movement in pixel.
     * @param y
     *            vertical movement in pixel
     */
    public void moveMap(int x, int y) {
        center.x += x;
        center.y += y;
        repaint();
    }

    /**
     * @return the current _currentZoomLevel level
     */
    public int get_currentZoomLevel() {
        return _currentZoomLevel;
    }

    /**
     * Increases the current _currentZoomLevel level by one
     */
    public void zoomIn() {
        set_currentZoomLevel(_currentZoomLevel + 1);
    }

    /**
     * Increases the current _currentZoomLevel level by one
     */
    public void zoomIn(Point mapPoint) {
        setZoom(_currentZoomLevel + 1, mapPoint);
    }

    /**
     * Decreases the current _currentZoomLevel level by one
     */
    public void zoomOut() {
        set_currentZoomLevel(_currentZoomLevel - 1);
    }

    /**
     * Decreases the current _currentZoomLevel level by one
     */
    public void zoomOut(Point mapPoint) {
        setZoom(_currentZoomLevel - 1, mapPoint);
    }

    public void setZoom(int zoom, Point mapPoint) {
        if (zoom > tileController.getTileSource().getMaxZoom() || zoom < tileController.getTileSource().getMinZoom()
                || zoom == this._currentZoomLevel)
            return;
        Coordinate zoomPos = getPosition(mapPoint);
        tileController.cancelOutstandingJobs(); // Clearing outstanding load
        // requests
        setDisplayPositionByLatLon(mapPoint, zoomPos.getLat(), zoomPos.getLon(), zoom);
    }

    public void set_currentZoomLevel(int zoom) {
        setZoom(zoom, new Point(getWidth() / 2, getHeight() / 2));
    }

    /**
     * Every time the _currentZoomLevel level changes this method is called. Override it in
     * derived implementations for adapting _currentZoomLevel dependent values. The new _currentZoomLevel
     * level can be obtained via {@link #get_currentZoomLevel()}.
     *
     * @param oldZoom
     * the previous _currentZoomLevel level
     */
    protected void zoomChanged(int oldZoom) {
        zoomSlider.setToolTipText("Zoom level " + _currentZoomLevel);
        zoomInButton.setToolTipText("Zoom to level " + (_currentZoomLevel + 1));
        zoomOutButton.setToolTipText("Zoom to level " + (_currentZoomLevel - 1));
        zoomOutButton.setEnabled(_currentZoomLevel > tileController.getTileSource().getMinZoom());
        zoomInButton.setEnabled(_currentZoomLevel < tileController.getTileSource().getMaxZoom());
    }

    public boolean isTileGridVisible() {
        return tileGridVisible;
    }

    public void setTileGridVisible(boolean tileGridVisible) {
        this.tileGridVisible = tileGridVisible;
        repaint();
    }

    public boolean getMapMarkersVisible() {
        return mapMarkersVisible;
    }

    /**
     * Enables or disables painting of the {@link org.digitall.common.geo.mapping.osm.interfaces.MapMarker}
     *
     * @param mapMarkersVisible
     * @see #addMapMarker(MapMarker)
     * @see #getMapMarkerList()
     */
    public void setMapMarkerVisible(boolean mapMarkersVisible) {
        this.mapMarkersVisible = mapMarkersVisible;
        repaint();
    }

    public void setMapMarkerList(List<MapMarker> mapMarkerList) {
        this.mapMarkerList = mapMarkerList;
        repaint();
    }

    public List<MapMarker> getMapMarkerList() {
        return mapMarkerList;
    }

    public void setMapRectangleList(List<MapRectangle> mapRectangleList) {
        this.mapRectangleList = mapRectangleList;
        repaint();
    }

    public List<MapRectangle> getMapRectangleList() {
        return mapRectangleList;
    }

    public void addMapMarker(MapMarker marker) {
        mapMarkerList.add(marker);
        repaint();
    }

    public void removeMapMarker(MapMarker marker) {
        mapMarkerList.remove(marker);
        repaint();
    }

    public void addMapRectangle(MapRectangle rectangle) {
        mapRectangleList.add(rectangle);
        repaint();
    }

    public void removeMapRectangle(MapRectangle rectangle) {
        mapRectangleList.remove(rectangle);
        repaint();
    }

    public void setZoomContolsVisible(boolean visible) {
        zoomSlider.setVisible(visible);
        zoomInButton.setVisible(visible);
        zoomOutButton.setVisible(visible);
    }

    public boolean getZoomContolsVisible() {
        return zoomSlider.isVisible();
    }

    public void setTileSource(TileSource tileSource) {
        if (tileSource.getMaxZoom() > MAX_ZOOM)
            throw new RuntimeException("Maximum zoom level too high");
        if (tileSource.getMinZoom() < MIN_ZOOM)
            throw new RuntimeException("Minumim zoom level too low");
        this.tileSource = tileSource;
        tileController.setTileSource(tileSource);
        zoomSlider.setMinimum(tileSource.getMinZoom());
        zoomSlider.setMaximum(tileSource.getMaxZoom());
        tileController.cancelOutstandingJobs();
        if (_currentZoomLevel > tileSource.getMaxZoom()) {
            set_currentZoomLevel(tileSource.getMaxZoom());
        }
        boolean requireAttr = tileSource.requiresAttribution();
        if (requireAttr) {
            attrImage = tileSource.getAttributionImage();
            attrTermsUrl = tileSource.getTermsOfUseURL();
        } else {
            attrImage = null;
            attrTermsUrl = null;
        }
        repaint();
    }

    public void tileLoadingFinished(Tile tile, boolean success) {
        repaint();
    }

    public boolean isMapRectanglesVisible() {
        return mapRectanglesVisible;
    }

    /**
     * Enables or disables painting of the {@link org.digitall.common.geo.mapping.osm.interfaces.MapRectangle}
     *
     * @param mapMarkersVisible
     * @see #addMapRectangle(MapRectangle)
     * @see #getMapRectangleList()
     */
    public void setMapRectanglesVisible(boolean mapRectanglesVisible) {
        this.mapRectanglesVisible = mapRectanglesVisible;
        repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.openstreetmap.gui.jmapviewer.interfaces.TileLoaderListener#getTileCache
     * ()
     */
    public TileCache getTileCache() {
        return tileController.getTileCache();
    }

    public void setTileLoader(TileLoader loader) {
        tileController.setTileLoader(loader);
    }

    private void paintAttribution(Graphics g) {
        if (!tileSource.requiresAttribution())
            return;
        // Draw attribution
        Font font = g.getFont();
        g.setFont(ATTR_LINK_FONT);

        Rectangle2D termsStringBounds = g.getFontMetrics().getStringBounds("Background Terms of Use", g);
        int textHeight = (int) termsStringBounds.getHeight() - 5;
        int termsTextY = getHeight() - textHeight;
        if (attrTermsUrl != null) {
            int x = 2;
            int y = getHeight() - textHeight;
            g.setColor(Color.black);
            g.drawString("Background Terms of Use", x + 1, y + 1);
            g.setColor(Color.white);
            g.drawString("Background Terms of Use", x, y);
        }

        // Draw attribution logo
        if (attrImage != null) {
            int x = 2;
            int height = attrImage.getHeight(null);
            int y = termsTextY - height - textHeight - 5;
            g.drawImage(attrImage, x, y, null);
        }

        g.setFont(ATTR_FONT);
        Coordinate topLeft = getPosition(0, 0);
        Coordinate bottomRight = getPosition(getWidth(), getHeight());
        String attributionText = tileSource.getAttributionText(_currentZoomLevel, topLeft, bottomRight);
        if (attributionText != null) {
            Rectangle2D stringBounds = g.getFontMetrics().getStringBounds(attributionText, g);
            int x = getWidth() - (int) stringBounds.getWidth();
            int y = getHeight() - textHeight;
            g.setColor(Color.black);
            g.drawString(attributionText, x + 1, y + 1);
            g.setColor(Color.white);
            g.drawString(attributionText, x, y);
        }

        g.setFont(font);
    }

    private MouseWheelListener commonWheelListener = new MouseWheelListener() {

	    public void mouseWheelMoved(MouseWheelEvent e) {
	        if (wheelZoomEnabled) {
	            setZoom(get_currentZoomLevel() - e.getWheelRotation(), e.getPoint());
	        }
		repaint();
	    }

	};
    protected MouseListener commonMouseListener = new MouseListener() {

	    public void mousePressed(MouseEvent e) {
	        if (e.getButton() == movementMouseButton || isPlatformOsx() && e.getModifiersEx() == MAC_MOUSE_BUTTON3_MASK) {
	            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            lastDragPoint = null;
	            isMoving = true;
	            startDragPosition = e.getPoint();
	        }
	    }

	    public void mouseReleased(MouseEvent e) {
	        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	        if (e.getButton() == movementMouseButton || isPlatformOsx() && e.getButton() == MouseEvent.BUTTON1) {
	            engineConfig.setXOffset(engineConfig.getXOffsetOriginal() + (engineConfig.xtoSpace(lastDragPoint.x) - engineConfig.xtoSpace(e.getX())));
	            engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (engineConfig.xtoSpace(lastDragPoint.y) - engineConfig.xtoSpace(e.getY())));
	            engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
	            engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
	            panLine = new Line2D.Float();
	            startDragPosition = null;
	            lastDragPoint = null;
	            isMoving = false;
	        }
		repaint();
	    }

	    public void mouseClicked(MouseEvent e) {
	        if (doubleClickZoomEnabled && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
		    zoomIn(e.getPoint());
		    tryToDrawPic();
	        }
	    }

	    public void mouseEntered(MouseEvent me) {

	    }

	    public void mouseExited(MouseEvent me) {

	    }

	};
    protected MouseMotionListener commonMotionListener = new MouseMotionListener() {

	    public void mouseMoved(MouseEvent e) {
		currentPosition = e.getPoint();
		updateCoordinates();

	        // Mac OSX simulates with  ctrl + mouse 1  the second mouse button hence no dragging events get fired.
	        //
	        if (isPlatformOsx()) {
	            if (!movementEnabled || !isMoving)
	                return;
	            // Is only the selected mouse button pressed?
	            if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
	                Point p = e.getPoint();
	                if (lastDragPoint != null) {
	                    int diffx = lastDragPoint.x - p.x;
	                    int diffy = lastDragPoint.y - p.y;
	                    moveMap(diffx, diffy);
	                }
	                lastDragPoint = p;
	            }

	        }
	    }

	public void mouseDragged(MouseEvent e) {
	    if (!movementEnabled || !isMoving)
	        return;
		// Is only the selected mouse button pressed?
		if ((e.getModifiersEx() & MOUSE_BUTTONS_MASK) == movementMouseButtonMask) {
		    Point p = e.getPoint();
		    if (lastDragPoint != null) {
		        engineConfig.setXOffset(engineConfig.getXOffsetOriginal() + (engineConfig.xtoSpace(lastDragPoint.x) - engineConfig.xtoSpace(p.x)));
		        engineConfig.setYOffset(engineConfig.getYOffsetOriginal() - (engineConfig.xtoSpace(lastDragPoint.y) - engineConfig.xtoSpace(p.y)));
		        engineConfig.setXOffsetOriginal(engineConfig.getXOffset());
		        engineConfig.setYOffsetOriginal(engineConfig.getYOffset());
			int diffx = lastDragPoint.x - p.x;
			int diffy = lastDragPoint.y - p.y;
			moveMap(diffx, diffy);
		    }
		    lastDragPoint = p;
		    if (lastDragPoint != null) {
		        //currentPosition = engineConfig.toSpace(mousePosition);
		        panLine.setLine(startDragPosition, e.getPoint());
		    }
		}
	    }

    };

    /**
     * Replies true if we are currently running on OSX
     *
     * @return true if we are currently running on OSX
     */
    public static boolean isPlatformOsx() {
	String os = System.getProperty("os.name");
	return os != null && os.toLowerCase().startsWith("mac os x");
    }

    public void initialize() {
	addGeometrySets(GaiaEnvironment.geometrySets);
	addLayerGroups(GaiaEnvironment.layerGroups);
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

    public void addLayerGroup(LayerGroup _layers) {
	layerGroups.add(_layers);
	for (int i = 0; i < _layers.size(); i++) {
	    addLayer(_layers.elementAt(i));
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

    public Vector getLayerGroups() {
	return layerGroups;
    }

    public void setRuleViewer(RuleViewer _ruleViewer) {
	ruleViewer = _ruleViewer;
	ruleViewer.setVisible(true);
    }

    public void setCoordinateViewer(CoordinateViewer _coordinateViewer) {
	coordinateViewer = _coordinateViewer;
	coordinateViewer.setVisible(true);
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
		    //engineConfig.setProjectionType(_layers.elementAt(0).getGeometrySetConfig().getProjectionType());
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

    }

    public void setMapExtents(double _x1, double _y1, double _x2, double _y2) {
	mapExtents = new Point2D[2];
	mapExtents[0] = new Point2D.Double(_x1, _y1);
	mapExtents[1] = new Point2D.Double(_x2, _y2);
    }

    public void setProjectionType(int _projectionType) {
	engineConfig.setProjectionType(_projectionType);
    }

    public int getProjectionType() {
	return engineConfig.getProjectionType();
    }

	
    protected MouseListener distanceMouseListener = new MouseListener() {
	    public void mouseClicked(MouseEvent me) {

	    }

	    public void mousePressed(MouseEvent me) {

	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.getButton() == me.BUTTON1) {
		    distance(true);
		    repaint();
		} else if (me.getButton() == me.BUTTON3) {
		    if ((me.getModifiers() & MouseEvent.SHIFT_MASK) == MouseEvent.SHIFT_MASK) {
		        drawingPointsVector.removeAllElements(); //restartEnvironment();
		    } else if ((me.getModifiers() & MouseEvent.CTRL_MASK) == MouseEvent.CTRL_MASK) {
			generateSHPPolygon();
		    } else if (drawingPointsVector.size() > 0) {
			drawingPointsVector.removeElementAt(drawingPointsVector.size()-1);
		    }
		    repaint();
		    distance(false);
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
	        currentPosition = me.getPoint();
	        updateCoordinates();
		if (drawingPointsVector.size() >= 1) {
		    /*setMouseActive(true);
		    labelxy.setText("X: " + me.getX() + ", Y: " + me.getY());*/
		    distance(false);
		    repaint();
		} else {
		}
	    }
	    //REVISAR

	    public void mouseDragged(MouseEvent me) {
	    }

	};

    public void distance(boolean _add) {
	double _distanciaParcial = 0;
	double _area = 0;
	double _angulo = 0;
	double _distanciaTotal = 0;
	int x = center.x + currentPosition.x - getWidth() / 2;
	int y = center.y + currentPosition.y - getHeight() / 2;
	double lon = OsmMercator.XToLon(x, _currentZoomLevel);
	double lat = OsmMercator.YToLat(y, _currentZoomLevel);
	Point2D.Double _currentPosition = new Point2D.Double(lon, lat);
	
	if (drawingPointsVector.size() < 99) //Fijarse si hay que mantener la restricción o no
	{
	    if (_add) {
		drawingPointsVector.add(_currentPosition);
	    }
	    if (drawingPointsVector.size() > 0) { //Un solo punto, por lo tanto distancia parcial
		_distanciaParcial = calcDistancia(drawingPointsVector.elementAt(drawingPointsVector.size() - 1), _currentPosition);
	    } else if (drawingPointsVector.size() == 2) { //Un solo segmento, por lo tanto distancia parcial
		if (!_add) {
		    //_distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 2)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		    //_distanciaParcial = calcDistancia(drawingPointsVector.elementAt(drawingPointsVector.size() - 2), drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		}
	    } 
	    if (drawingPointsVector.size() > 1) { //Por lo menos dos segmentos, por lo tanto distancia parcial y distancia total
		 for (int i = 0; i < drawingPointsVector.size()-1; i++)  {
		     //_distanciaTotal += (drawingPointsVector.elementAt(i)).distance(drawingPointsVector.elementAt(i+1));
		     _distanciaTotal += calcDistancia(drawingPointsVector.elementAt(i), drawingPointsVector.elementAt(i+1));
		 }
		if (_add) {
		    //_distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 3)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 2));
		} else {
		    //_distanciaParcial = (drawingPointsVector.elementAt(drawingPointsVector.size() - 2)).distance(drawingPointsVector.elementAt(drawingPointsVector.size() - 1));
		}
	        Vector<Point2D.Double> _d = (Vector<Point2D.Double>)drawingPointsVector.clone();
		_d.add(_currentPosition);
		_area = calcArea(_d, _add);
		/**angulo = calcAngulo(polypoints);*/
	    }
	    ruleViewer.setData(_distanciaParcial, _distanciaTotal, _area, _angulo);
	} else {
	    Advisor.messageBox("Maxima cantidad de puntos alcanzada, \nno debe superar los 100 puntos", "Error");
	}
    }

    private double calcDistancia(Point2D.Double x0, Point2D.Double x1) {
	int radius = 6371; // earth radius in km
	double phi1 = Math.toRadians(x0.getY());
	double phi2 = Math.toRadians(x1.getY());
	double dPhi = Math.toRadians(x1.getY()-x0.getY());
	double dLambda = Math.toRadians(x1.getX()-x0.getX());

	double a = Math.sin(dPhi/2.0) * Math.sin(dPhi/2.0) +
		Math.cos(phi1) * Math.cos(phi2) *
		Math.sin(dLambda/2.0) * Math.sin(dLambda/2.0);
	double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	return radius * c * 1000.0;
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

    private void updateCoordinates() {
	int x = center.x + currentPosition.x - getWidth() / 2;
	int y = center.y + currentPosition.y - getHeight() / 2;
	double lon = OsmMercator.XToLon(x, _currentZoomLevel);
	double lat = OsmMercator.YToLat(y, _currentZoomLevel);
	coordinateViewer.setLLCoord(new LatLongCoord(lat, lon));
	//UPDATE UTM ZONE HERE!!!
    }
    
    private void generateSHPPolygon() {
	SHPPolygon _poly = new SHPPolygon();
	Point2D.Double _points[] = new Point2D.Double[drawingPointsVector.size()];
	int i = 0;
	for (Point2D.Double _point :drawingPointsVector) {
	    _points[i] = _point;
	    i++;
	}
	_poly.setPoints(_points);
	_poly.closeRings();
	//SELECET addGeometryColumn('gis','lotes','the_geom','-1','POLYGON',2);
	System.out.println(_poly.getSQLString("gis", "lotes"));
    }
    
}