package tim.prune.gui.map;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.awt.image.BufferedImage;

import java.beans.PropertyVetoException;

import java.io.File;
import java.io.IOException;

import java.sql.Types;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.common.geo.mapping.DrawEnginePanel;
import org.digitall.common.geo.mapping.osm.OsmMercator;
import org.digitall.common.geo.mapping.panels.ReportConfigPanel;
import org.digitall.common.geo.mapping.panels.StrokeSamples;
import org.digitall.common.mapper.CoordinateViewer;
import org.digitall.common.mapper.RuleViewer;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerFilter;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.StyleConfig;
import org.digitall.lib.geo.shapefile.ShapeTypes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import tim.prune.App;
import tim.prune.DataSubscriber;
import tim.prune.FunctionLibrary;
import tim.prune.I18nManager;
import tim.prune.UpdateMessageBroker;
import tim.prune.config.ColourScheme;
import tim.prune.config.Config;
import tim.prune.data.Checker;
import tim.prune.data.Coordinate;
import tim.prune.data.DataPoint;
import tim.prune.data.DoubleRange;
import tim.prune.data.Latitude;
import tim.prune.data.Longitude;
import tim.prune.data.MidpointData;
import tim.prune.data.Selection;
import tim.prune.data.TrackInfo;
import tim.prune.function.compress.MarkPointsInRectangleFunction;
import tim.prune.gui.IconManager;

/**
 * Class for the map canvas, to display a background map and draw on it
 */
public class MapCanvas extends DrawEnginePanel implements MouseListener, MouseMotionListener, DataSubscriber, KeyListener, MouseWheelListener {
    /** App object for callbacks */
    private App _app = null;
    /** Track object */
    //private Track _track = null;

    /** TrackInfo object */
    private TrackInfo _trackInfo = null;

    /** Selection object */
    private Selection _selection = null;

    /** Object to keep track of midpoints */
    private MidpointData _midpoints = null;

    /** Index of point clicked at mouseDown */
    private int _clickedPoint = -1;

    /** Previously selected point */
    private int _prevSelectedPoint = -1;

    /** Tile manager */
    private MapTileManager _tileManager = new MapTileManager(this);

    /** Image to display */
    private BufferedImage _mapImage = null;

    /** Slider for transparency */
    private JSlider _transparencySlider = null;

    /** Checkbox for scale bar */
    private JCheckBox _scaleCheckBox = null;

    /** Checkbox for maps */
    private JCheckBox _mapCheckBox = null;

    /** Checkbox for autopan */
    private JCheckBox _autopanCheckBox = null;

    /** Checkbox for connecting track points */
    private JCheckBox _connectCheckBox = null;

    /** Checkbox for enable edit mode */
    private JCheckBox _editmodeCheckBox = null;

    /** Right-click popup menu */
    private JPopupMenu _popup = null;

    /** Top component panel */
    private JPanel _topPanel = null;

    /** Side component panel */
    private JPanel _sidePanel = null;

    /** Scale bar */
    private ScaleBar _scaleBar = null;
    /* Data */
    private DoubleRange _latRange = null, _lonRange = null;
    private DoubleRange _xRange = null, _yRange = null;
    private boolean _recalculate = false;

    /** Flag to check bounds on next paint */
    private boolean _checkBounds = false;

    /** Map position */
    private MapPosition _mapPosition = null;

    /** coordinates of drag from point */
    private int _dragFromX = -1, _dragFromY = -1;

    /** coordinates of drag to point */
    private int _dragToX = -1, _dragToY = -1;

    /** coordinates of popup menu */
    private int _popupMenuX = -1, _popupMenuY = -1;

    /** Flag to prevent showing too often the error message about loading maps */
    private boolean _shownOsmErrorAlready = false;

    /** Current drawing mode */
    private int _drawMode = MODE_DEFAULT;

    /** Constant for click sensitivity when selecting nearest point */
    private static final int CLICK_SENSITIVITY = 10;

    /** Constant for pan distance from key presses */
    private static final int PAN_DISTANCE = 20;

    /** Constant for pan distance from autopan */
    private static final int AUTOPAN_DISTANCE = 75;

    // Colours
    private static final Color COLOR_MESSAGES = Color.GRAY;

    // Drawing modes
    private static final int MODE_DEFAULT = 0;
    private static final int MODE_ZOOM_RECT = 1;
    private static final int MODE_DRAW_POINTS_START = 2;
    private static final int MODE_DRAW_POINTS_CONT = 3;
    private static final int MODE_DRAG_POINT = 4;
    private static final int MODE_CREATE_MIDPOINT = 5;
    private static final int MODE_MARK_RECTANGLE = 6;

    private static final int INDEX_UNKNOWN = -2;
    private Point mousePosition = new Point(-1, -1);

    private CoordinateViewer coordinateViewer;
    
    private static Timer containedShapesTimer = null;
    private static Timer infoTimer = null;
    private static Timer hideToolTipTimer = null;
    private int containedShapeIDS[][] = new int[0][0];
    private MouseListener eraseListener;
    private MouseMotionListener eraseMotionListener;
    private int currentOperation = -1;
    private String operationStatus;
    private RuleViewer ruleViewer;
    private JWindow toolTip = new JWindow(new Frame());
    private BasicLabel label = new BasicLabel(" ");
    private static long lastShowingTime = System.currentTimeMillis();
    public static int timeout = 3;

    private Point2D.Double multiQueryStartDrawRectPosition;
    private Point2D.Double multiQueryEndDrawRectPosition;
    private Rectangle2D multiQueryRectangle;
    private Layer selectedLayer = null;
    private ConfigFile cfg = new ConfigFile("ddesktop.conf");
    private int selectedGeometryIndex = -1;

    /**
     * Constructor
     * @param inApp App object for callbacks
     * @param inTrackInfo track info object
     */
    public MapCanvas(App inApp, TrackInfo inTrackInfo) {
        _app = inApp;
        _trackInfo = inTrackInfo;
        //_track = inTrackInfo.getTrack();
        _selection = inTrackInfo.getSelection();
        _midpoints = new MidpointData();
        _mapPosition = new MapPosition();
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);

        // Make listener for changes to controls
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                _recalculate = true;
                repaint();
            }
        };
        // Make special listener for changes to map checkbox
        ItemListener mapCheckListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                _tileManager.clearMemoryCaches();
                _recalculate = true;
                Config.setConfigBoolean(Config.KEY_SHOW_MAP, e.getStateChange() == ItemEvent.SELECTED);
                UpdateMessageBroker.informSubscribers(); // to let menu know
                // If the track is only partially visible and you turn the map off, make the track fully visible again
                if (e.getStateChange() == ItemEvent.DESELECTED && _transparencySlider.getValue() < 0) {
                    _transparencySlider.setValue(0);
                }
            }
        };
        _topPanel = new OverlayPanel();
        _topPanel.setLayout(new FlowLayout());
        // Make slider for transparency
        _transparencySlider = new JSlider(-6, 6, 0);
        _transparencySlider.setPreferredSize(new Dimension(100, 20));
        _transparencySlider.setMajorTickSpacing(1);
        _transparencySlider.setSnapToTicks(true);
        _transparencySlider.setOpaque(false);
        _transparencySlider.setValue(0);
        _transparencySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int val = _transparencySlider.getValue();
                if (val == 1 || val == -1)
                    _transparencySlider.setValue(0);
                else {
                    _recalculate = true;
                    repaint();
                }
            }
        });
        _transparencySlider.setFocusable(false); // stop slider from stealing keyboard focus
        _topPanel.add(_transparencySlider);
        // Add checkbox button for enabling scale bar
        _scaleCheckBox = new JCheckBox(IconManager.getImageIcon(IconManager.SCALEBAR_BUTTON), true);
        _scaleCheckBox.setSelectedIcon(IconManager.getImageIcon(IconManager.SCALEBAR_BUTTON_ON));
        _scaleCheckBox.setOpaque(false);
        _scaleCheckBox.setToolTipText(I18nManager.getText("menu.map.showscalebar"));
        _scaleCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                _scaleBar.setVisible(_scaleCheckBox.isSelected());
            }
        });
        _scaleCheckBox.setFocusable(false); // stop button from stealing keyboard focus
        _topPanel.add(_scaleCheckBox);
        // Add checkbox button for enabling maps or not
        _mapCheckBox = new JCheckBox(IconManager.getImageIcon(IconManager.MAP_BUTTON), false);
        _mapCheckBox.setSelectedIcon(IconManager.getImageIcon(IconManager.MAP_BUTTON_ON));
        _mapCheckBox.setOpaque(false);
        _mapCheckBox.setToolTipText(I18nManager.getText("menu.map.showmap"));
        _mapCheckBox.addItemListener(mapCheckListener);
        _mapCheckBox.setFocusable(false); // stop button from stealing keyboard focus
        _mapCheckBox.setSelected(true);
        _topPanel.add(_mapCheckBox);
        // Add checkbox button for enabling autopan or not
        _autopanCheckBox = new JCheckBox(IconManager.getImageIcon(IconManager.AUTOPAN_BUTTON), true);
        _autopanCheckBox.setSelectedIcon(IconManager.getImageIcon(IconManager.AUTOPAN_BUTTON_ON));
        _autopanCheckBox.setOpaque(false);
        _autopanCheckBox.setToolTipText(I18nManager.getText("menu.map.autopan"));
        _autopanCheckBox.addItemListener(itemListener);
        _autopanCheckBox.setFocusable(false); // stop button from stealing keyboard focus
        //_topPanel.add(_autopanCheckBox);
        // Add checkbox button for connecting points or not
        _connectCheckBox = new JCheckBox(IconManager.getImageIcon(IconManager.POINTS_DISCONNECTED_BUTTON), true);
        _connectCheckBox.setSelectedIcon(IconManager.getImageIcon(IconManager.POINTS_CONNECTED_BUTTON));
        _connectCheckBox.setOpaque(false);
        _connectCheckBox.setToolTipText(I18nManager.getText("menu.map.connect"));
        _connectCheckBox.addItemListener(itemListener);
        _connectCheckBox.setFocusable(false); // stop button from stealing keyboard focus
        //_topPanel.add(_connectCheckBox);

        // Add checkbox button for edit mode or not
        _editmodeCheckBox = new JCheckBox(IconManager.getImageIcon(IconManager.EDIT_MODE_BUTTON), false);
        _editmodeCheckBox.setSelectedIcon(IconManager.getImageIcon(IconManager.EDIT_MODE_BUTTON_ON));
        _editmodeCheckBox.setOpaque(false);
        _editmodeCheckBox.setToolTipText(I18nManager.getText("menu.map.editmode"));
        _editmodeCheckBox.addItemListener(itemListener);
        _editmodeCheckBox.setFocusable(false); // stop button from stealing keyboard focus
        //_topPanel.add(_editmodeCheckBox);

        // Add zoom in, zoom out buttons
        _sidePanel = new OverlayPanel();
        _sidePanel.setLayout(new BoxLayout(_sidePanel, BoxLayout.Y_AXIS));
        JButton zoomInButton = new JButton(IconManager.getImageIcon(IconManager.ZOOM_IN_BUTTON));
        zoomInButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        zoomInButton.setContentAreaFilled(false);
        zoomInButton.setToolTipText(I18nManager.getText("menu.map.zoomin"));
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });
        zoomInButton.setFocusable(false); // stop button from stealing keyboard focus
        _sidePanel.add(zoomInButton);
        JButton zoomOutButton = new JButton(IconManager.getImageIcon(IconManager.ZOOM_OUT_BUTTON));
        zoomOutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        zoomOutButton.setContentAreaFilled(false);
        zoomOutButton.setToolTipText(I18nManager.getText("menu.map.zoomout"));
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });
        zoomOutButton.setFocusable(false); // stop button from stealing keyboard focus
        _sidePanel.add(zoomOutButton);

        // Bottom panel for scale bar
        _scaleBar = new ScaleBar();

        // add control panels to this one
        setLayout(new BorderLayout());
        _topPanel.setVisible(false);
        _sidePanel.setVisible(false);
        add(_topPanel, BorderLayout.NORTH);
        add(_sidePanel, BorderLayout.WEST);
        add(_scaleBar, BorderLayout.SOUTH);
        // Make popup menu
        makePopup();
        
        containedShapesTimer = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int i = geometrySets.size() - 1;
                            containedShapeIDS = new int[geometrySets.size()][0];
                            StringBuilder _labelText = new StringBuilder();
                            _labelText.append("<html>");
                            while (i >= 0) {
                                //containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(engineConfig.xtoSpace((int)mousePosition.getX()), engineConfig.ytoSpace((int)mousePosition.getY()));
                                containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(mousexToSpace(),mouseyToSpace());
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
                                double _X = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels((int)mousePosition.getX(), getWidth()));
                                double _Y = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels((int)mousePosition.getY(), getHeight()));
                                containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(_X,_Y);
                                //containedShapeIDS[i] = geometrySets.elementAt(i).getContainedShapeIDS(engineConfig.xtoSpace((int)mousePosition.getX()), engineConfig.ytoSpace((int)mousePosition.getY()));
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

        hideToolTipTimer = new Timer(1000, new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            if ((System.currentTimeMillis() - lastShowingTime) > (timeout * 1000)) {
                                showToolTip(false);
                                hideToolTipTimer.stop();
                            }
                        }

                    });
    }

    private double mousexToSpace() {
        return xToSpace((int)mousePosition.getX());
    }

    private double mouseyToSpace() {
        return yToSpace((int)mousePosition.getY());
    }

    private double xToSpace(int x) {
        return MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(x, getWidth()));
    }
    
    private double yToSpace(int y) {
        return MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(y, getHeight()));
    }

    /**
     * Make the popup menu for right-clicking the map
     */
    private void makePopup() {
        _popup = new JPopupMenu();
        JMenuItem zoomInItem = new JMenuItem(I18nManager.getText("menu.map.zoomin"));
        zoomInItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panMap((_popupMenuX - getWidth() / 2) / 2, (_popupMenuY - getHeight() / 2) / 2);
                zoomIn();
            }
        });
        _popup.add(zoomInItem);
        JMenuItem zoomOutItem = new JMenuItem(I18nManager.getText("menu.map.zoomout"));
        zoomOutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panMap(-(_popupMenuX - getWidth() / 2), -(_popupMenuY - getHeight() / 2));
                zoomOut();
            }
        });
        _popup.add(zoomOutItem);
        JMenuItem zoomFullItem = new JMenuItem(I18nManager.getText("menu.map.zoomfull"));
        zoomFullItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zoomToFit();
                _recalculate = true;
                repaint();
            }
        });
        _popup.add(zoomFullItem);
        _popup.addSeparator();
        // Set background
        JMenuItem setMapBgItem = new JMenuItem(I18nManager.getText(FunctionLibrary.FUNCTION_SET_MAP_BG.getNameKey()));
        setMapBgItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FunctionLibrary.FUNCTION_SET_MAP_BG.begin();
            }
        });
        _popup.add(setMapBgItem);
        // new point option
        JMenuItem newPointItem = new JMenuItem(I18nManager.getText("menu.map.newpoint"));
        newPointItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _app.createPoint(createPointFromClick(_popupMenuX, _popupMenuY));
            }
        });
        _popup.add(newPointItem);
        // draw point series
        JMenuItem drawPointsItem = new JMenuItem(I18nManager.getText("menu.map.drawpoints"));
        drawPointsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _drawMode = MODE_DRAW_POINTS_START;
            }
        });
        _popup.add(drawPointsItem);
    }


    /**
     * Zoom to fit the current data area
     */
    public void zoomToFit() {
        //_latRange = _track.getLatRange();
        //_lonRange = _track.getLonRange();
        _xRange = new DoubleRange(MapUtils.getXFromLongitude(_lonRange.getMinimum()), MapUtils.getXFromLongitude(_lonRange.getMaximum()));
        _yRange = new DoubleRange(MapUtils.getYFromLatitude(_latRange.getMinimum()), MapUtils.getYFromLatitude(_latRange.getMaximum()));
        _mapPosition.zoomToXY(_xRange.getMinimum(), _xRange.getMaximum(), _yRange.getMinimum(), _yRange.getMaximum(), getWidth(), getHeight());
    }


    /**
     * Paint method
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    public void paint(Graphics inG) {
        super.paint(inG);
        if (_mapImage != null && (_mapImage.getWidth() != getWidth() || _mapImage.getHeight() != getHeight())) {
            _mapImage = null;
        }
        /*if (_track.getNumPoints() > 0)
		{
			// Check for autopan if enabled / necessary
			if (_autopanCheckBox.isSelected())
			{
				int selectedPoint = _selection.getCurrentPointIndex();
				if (selectedPoint >= 0 && _dragFromX == -1 && selectedPoint != _prevSelectedPoint)
				{
					autopanToPoint(selectedPoint);
				}
				_prevSelectedPoint = selectedPoint;
			}

			// Draw the map contents if necessary
			if ((_mapImage == null || _recalculate))
			{
				paintMapContents();
				_scaleBar.updateScale(_mapPosition.getZoom(), _mapPosition.getYFromPixels(0, 0));
			}
			// Draw the prepared image onto the panel
			if (_mapImage != null) {
				inG.drawImage(_mapImage, 0, 0, getWidth(), getHeight(), null);
			}

			switch (_drawMode)
			{
				case MODE_DRAG_POINT:
					drawDragLines(inG, _selection.getCurrentPointIndex()-1, _selection.getCurrentPointIndex()+1);
					break;

				case MODE_CREATE_MIDPOINT:
					drawDragLines(inG, _clickedPoint-1, _clickedPoint);
					break;

				case MODE_ZOOM_RECT:
				case MODE_MARK_RECTANGLE:
					if (_dragFromX != -1 && _dragFromY != -1)
					{
						// Draw the zoom rectangle if necessary
						inG.setColor(Color.RED);
						inG.drawLine(_dragFromX, _dragFromY, _dragFromX, _dragToY);
						inG.drawLine(_dragFromX, _dragFromY, _dragToX, _dragFromY);
						inG.drawLine(_dragToX, _dragFromY, _dragToX, _dragToY);
						inG.drawLine(_dragFromX, _dragToY, _dragToX, _dragToY);
					}
					break;

				case MODE_DRAW_POINTS_CONT:
					// draw line to mouse position to show drawing mode
					inG.setColor(Config.getColourScheme().getColour(ColourScheme.IDX_POINT));
					int prevIndex = _track.getNumPoints()-1;
					int px = getWidth() / 2 + _mapPosition.getXFromCentre(_track.getX(prevIndex));
					int py = getHeight() / 2 + _mapPosition.getYFromCentre(_track.getY(prevIndex));
					inG.drawLine(px, py, _dragToX, _dragToY);
					break;
			}
		}
		else
		{
			inG.setColor(Config.getColourScheme().getColour(ColourScheme.IDX_BACKGROUND));
			inG.fillRect(0, 0, getWidth(), getHeight());
			inG.setColor(COLOR_MESSAGES);
			inG.drawString(I18nManager.getText("display.nodata"), 50, getHeight()/2);
			_scaleBar.updateScale(-1, 0);
		}*/
        paintMapContents();
        _scaleBar.updateScale(_mapPosition.getZoom(), _mapPosition.getYFromPixels(0, 0));
        inG.drawImage(_mapImage, 0, 0, getWidth(), getHeight(), null);
        Graphics2D _graphics2D = (Graphics2D)inG;
        labelGraphics2D = (Graphics2D)_graphics2D.create();
        drawLayers(_graphics2D, labelGraphics2D, engineConfig);

        switch (_drawMode)
        {
                case MODE_ZOOM_RECT:
                case MODE_MARK_RECTANGLE:
                        if (_dragFromX != -1 && _dragFromY != -1)
                        {
                                // Draw the zoom rectangle if necessary
                                inG.setColor(Color.RED);
                                inG.drawLine(_dragFromX, _dragFromY, _dragFromX, _dragToY);
                                inG.drawLine(_dragFromX, _dragFromY, _dragToX, _dragFromY);
                                inG.drawLine(_dragToX, _dragFromY, _dragToX, _dragToY);
                                inG.drawLine(_dragFromX, _dragToY, _dragToX, _dragToY);
                                inG.setColor(new Color(255,0,0,40));
                                inG.fillRect(Math.min(_dragFromX,_dragToX), Math.min(_dragFromY, _dragToY),
                                             Math.abs(_dragFromX - _dragToX), Math.abs(_dragFromY - _dragToY));
                        }
                        break;
        }

        // Draw slider etc on top
        paintChildren(inG);
    }

    /**
	 * @return true if the currently selected point is visible, false if off-screen or nothing selected
	 */
    /*private boolean isCurrentPointVisible()
	{
		if (_trackInfo.getCurrentPoint() == null) {return false;}
		final int selectedPoint = _selection.getCurrentPointIndex();
		final int xFromCentre = Math.abs(_mapPosition.getXFromCentre(_track.getX(selectedPoint)));
		if (xFromCentre > (getWidth()/2)) {return false;}
		final int yFromCentre = Math.abs(_mapPosition.getYFromCentre(_track.getY(selectedPoint)));
		return yFromCentre < (getHeight()/2);
	}*/

    /**
	 * If the specified point isn't visible, pan to it
	 * @param inIndex index of selected point
	 */
    /*private void autopanToPoint(int inIndex)
	{
		int px = getWidth() / 2 + _mapPosition.getXFromCentre(_track.getX(inIndex));
		int py = getHeight() / 2 + _mapPosition.getYFromCentre(_track.getY(inIndex));
		int panX = 0;
		int panY = 0;
		if (px < PAN_DISTANCE) {
			panX = px - AUTOPAN_DISTANCE;
		}
		else if (px > (getWidth()-PAN_DISTANCE)) {
			panX = AUTOPAN_DISTANCE + px - getWidth();
		}
		if (py < PAN_DISTANCE) {
			panY = py - AUTOPAN_DISTANCE;
		}
		if (py > (getHeight()-PAN_DISTANCE)) {
			panY = AUTOPAN_DISTANCE + py - getHeight();
		}
		if (panX != 0 || panY != 0) {
			_mapPosition.pan(panX, panY);
		}
	}*/

    /**
     * Paint the map tiles and the points on to the _mapImage
     */
    private void paintMapContents() {
        if (_mapImage == null || _mapImage.getWidth() != getWidth() || _mapImage.getHeight() != getHeight()) {
            _mapImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        // Clear map
        Graphics g = _mapImage.getGraphics();
        // Clear to background
        g.setColor(Config.getColourScheme().getColour(ColourScheme.IDX_BACKGROUND));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Check whether maps are on or not
        boolean showMap = _mapCheckBox.isSelected();
        //Config.getConfigBoolean(Config.KEY_SHOW_MAP);
        _mapCheckBox.setSelected(showMap);

        // reset error message
        if (!showMap) {
            _shownOsmErrorAlready = false;
        }
        _recalculate = false;
        // Only get map tiles if selected
        if (showMap) {
            // init tile cacher
            _tileManager.centreMap(_mapPosition.getZoom(), _mapPosition.getCentreTileX(), _mapPosition.getCentreTileY());
            //System.out.println("Pos: " + _mapPosition.getCentreTileX() + "," + _mapPosition.getCentreTileY());

            boolean loadingFailed = false;
            if (_mapImage == null)
                return;

            if (_tileManager.isOverzoomed()) {
                // display overzoom message
                g.setColor(COLOR_MESSAGES);
                g.drawString(I18nManager.getText("map.overzoom"), 50, getHeight() / 2);
            } else {
                int numLayers = _tileManager.getNumLayers();
                // Loop over tiles drawing each one
                int[] tileIndices = _mapPosition.getTileIndices(getWidth(), getHeight());
                int[] pixelOffsets = _mapPosition.getDisplayOffsets(getWidth(), getHeight());
                for (int tileX = tileIndices[0]; tileX <= tileIndices[1] && !loadingFailed; tileX++) {
                    int x = (tileX - tileIndices[0]) * 256 - pixelOffsets[0];
                    for (int tileY = tileIndices[2]; tileY <= tileIndices[3]; tileY++) {
                        int y = (tileY - tileIndices[2]) * 256 - pixelOffsets[1];
                        // Loop over layers
                        for (int l = 0; l < numLayers; l++) {
                            Image image = _tileManager.getTile(l, tileX, tileY);
                            if (image != null) {
                                g.drawImage(image, x, y, 256, 256, null);
                            }
                        }
                    }
                }

                // Make maps brighter / fainter according to slider
                final int brightnessIndex = Math.max(1, _transparencySlider.getValue()) - 1;
                if (brightnessIndex > 0) {
                    final int[] alphas = { 0, 40, 80, 120, 160, 210 };
                    Color bgColor = Config.getColourScheme().getColour(ColourScheme.IDX_BACKGROUND);
                    bgColor = new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), alphas[brightnessIndex]);
                    g.setColor(bgColor);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        }

        // Paint the track points on top
        int pointsPainted = 1;
        try {
            //pointsPainted = paintPoints(g);
        } catch (NullPointerException npe) {
        } // ignore, probably due to data being changed during drawing
        catch (ArrayIndexOutOfBoundsException obe) {
        } // also ignore

        // free g
        g.dispose();

        // Zoom to fit if no points found
        if (pointsPainted <= 0 && _checkBounds) {
            zoomToFit();
            _recalculate = true;
            repaint();
        }
        _checkBounds = false;
        // enable / disable transparency slider
        _transparencySlider.setEnabled(showMap);
    }


    /**
	 * Paint the points using the given graphics object
	 * @param inG Graphics object to use for painting
	 * @return number of points painted, if any
	 */
    /*private int paintPoints(Graphics inG)
	{
		// Set up colours
		final ColourScheme cs = Config.getColourScheme();
		final int[] opacities = {255, 190, 130, 80, 40, 0};
		int opacity = 255;
		if (_transparencySlider.getValue() < 0)
			opacity = opacities[-1 - _transparencySlider.getValue()];
		final Color pointColour  = makeTransparentColour(cs.getColour(ColourScheme.IDX_POINT), opacity);
		final Color rangeColour  = makeTransparentColour(cs.getColour(ColourScheme.IDX_SELECTION), opacity);
		final Color currentColour = makeTransparentColour(cs.getColour(ColourScheme.IDX_PRIMARY), opacity);
		final Color secondColour = makeTransparentColour(cs.getColour(ColourScheme.IDX_SECONDARY), opacity);
		final Color textColour   = makeTransparentColour(cs.getColour(ColourScheme.IDX_TEXT), opacity);

		final int winWidth  = getWidth();
		final int winHeight = getHeight();
		final int halfWinWidth  = winWidth / 2;
		final int halfWinHeight = winHeight / 2;

		final int numPoints = _track.getNumPoints();
		final int[] xPixels = new int[numPoints];
		final int[] yPixels = new int[numPoints];

		// try to set line width for painting
		if (inG instanceof Graphics2D)
		{
			int lineWidth = Config.getConfigInt(Config.KEY_LINE_WIDTH);
			if (lineWidth < 1 || lineWidth > 4) {lineWidth = 2;}
			((Graphics2D) inG).setStroke(new BasicStroke(lineWidth));
		}
		int pointsPainted = 0;
		// draw track points
		inG.setColor(pointColour);
		int prevX = -1, prevY = -1;
		boolean connectPoints = _connectCheckBox.isSelected();
		boolean prevPointVisible = false, currPointVisible = false;
		boolean anyWaypoints = false;
		boolean isWaypoint = false;
		for (int i=0; i<numPoints; i++)
		{
			// Calculate pixel position of point from its x, y coordinates
			int px = halfWinWidth  + _mapPosition.getXFromCentre(_track.getX(i));
			int py = halfWinHeight + _mapPosition.getYFromCentre(_track.getY(i));
			px = wrapLongitudeValue(px, winWidth, _mapPosition.getZoom());
			// Remember these calculated pixel values so they don't have to be recalculated
			xPixels[i] = px; yPixels[i] = py;

			currPointVisible = px >= 0 && px < winWidth && py >= 0 && py < winHeight;
			isWaypoint = _track.getPoint(i).isWaypoint();
			anyWaypoints = anyWaypoints || isWaypoint;
			if (currPointVisible)
			{
				if (!isWaypoint)
				{
					// Draw rectangle for track point
					if (_track.getPoint(i).getDeleteFlag()) {
						inG.setColor(currentColour);
					}
					else {
						inG.setColor(pointColour);
					}
					inG.drawRect(px-2, py-2, 3, 3);
					pointsPainted++;
				}
			}
			if (!isWaypoint)
			{
				// Connect track points if either of them are visible
				if (connectPoints && (currPointVisible || prevPointVisible)
				 && !(prevX == -1 && prevY == -1)
				 && !_track.getPoint(i).getSegmentStart())
				{
					inG.drawLine(prevX, prevY, px, py);
				}
				prevX = px; prevY = py;
			}
			prevPointVisible = currPointVisible;
		}

		// Loop over points, just drawing blobs for waypoints
		inG.setColor(textColour);
		FontMetrics fm = inG.getFontMetrics();
		int nameHeight = fm.getHeight();
		if (anyWaypoints)
		{
			int numWaypoints = 0;
			for (int i=0; i<_track.getNumPoints(); i++)
			{
				if (_track.getPoint(i).isWaypoint())
				{
					int px = xPixels[i];
					int py = yPixels[i];
					if (px >= 0 && px < winWidth && py >= 0 && py < winHeight)
					{
						inG.fillRect(px-3, py-3, 6, 6);
						pointsPainted++;
						numWaypoints++;
					}
				}
			}
			// Take more care with waypoint names if less than 100 are visible
			final int numNameSteps = (numWaypoints > 100 ? 1 : 4);
			final int numPointSteps = (numWaypoints > 1000 ? 2 : 1);

			// Loop over points again, now draw names for waypoints
			int[] nameXs = {0, 0, 0, 0};
			int[] nameYs = {0, 0, 0, 0};
			for (int i=0; i<_track.getNumPoints(); i += numPointSteps)
			{
				if (_track.getPoint(i).isWaypoint())
				{
					int px = xPixels[i];
					int py = yPixels[i];
					if (px >= 0 && px < winWidth && py >= 0 && py < winHeight)
					{
						// Figure out where to draw waypoint name so it doesn't obscure track
						String waypointName = _track.getPoint(i).getWaypointName();
						int nameWidth = fm.stringWidth(waypointName);
						boolean drawnName = false;
						// Make arrays for coordinates right left up down
						nameXs[0] = px + 2; nameXs[1] = px - nameWidth - 2;
						nameXs[2] = nameXs[3] = px - nameWidth/2;
						nameYs[0] = nameYs[1] = py + (nameHeight/2);
						nameYs[2] = py - 2; nameYs[3] = py + nameHeight + 2;
						for (int extraSpace = 0; extraSpace < numNameSteps && !drawnName; extraSpace++)
						{
							// Shift arrays for coordinates right left up down
							nameXs[0] += 3; nameXs[1] -= 3;
							nameYs[2] -= 3; nameYs[3] += 3;
							// Check each direction in turn right left up down
							for (int a=0; a<4; a++)
							{
								if (nameXs[a] > 0 && (nameXs[a] + nameWidth) < winWidth
									&& nameYs[a] < winHeight && (nameYs[a] - nameHeight) > 0
									&& !MapUtils.overlapsPoints(_mapImage, nameXs[a], nameYs[a], nameWidth, nameHeight, textColour))
								{
									// Found a rectangle to fit - draw name here and quit
									inG.drawString(waypointName, nameXs[a], nameYs[a]);
									drawnName = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		// Loop over points, drawing blobs for photo / audio points
		inG.setColor(secondColour);
		for (int i=0; i<_track.getNumPoints(); i++)
		{
			if (_track.getPoint(i).hasMedia())
			{
				int px = xPixels[i];
				int py = yPixels[i];
				if (px >= 0 && px < winWidth && py >= 0 && py < winHeight)
				{
					inG.drawRect(px-1, py-1, 2, 2);
					inG.drawRect(px-2, py-2, 4, 4);
					pointsPainted++;
				}
			}
		}

		// Draw selected range
		if (_selection.hasRangeSelected())
		{
			inG.setColor(rangeColour);
			for (int i=_selection.getStart(); i<=_selection.getEnd(); i++)
			{
				int px = xPixels[i];
				int py = yPixels[i];
				inG.drawRect(px-1, py-1, 2, 2);
			}
		}

		// Draw crosshairs at selected point
		int selectedPoint = _selection.getCurrentPointIndex();
		if (selectedPoint >= 0)
		{
			int px = xPixels[selectedPoint];
			int py = yPixels[selectedPoint];
			inG.setColor(currentColour);
			// crosshairs
			inG.drawLine(px, 0, px, winHeight);
			inG.drawLine(0, py, winWidth, py);
		}
		// Return the number of points painted
		return pointsPainted;
	}*/

    /**
     * Wrap the given pixel value if appropriate and possible
     * @param inPx Pixel x coordinate
     * @param inWinWidth window width in pixels
     * @param inZoom zoom level
     * @return modified pixel x coordinate
     */
    private static int wrapLongitudeValue(int inPx, int inWinWidth, int inZoom) {
        if (inPx > inWinWidth) {
            // Pixel is too far right, could we wrap it back onto the screen?
            int px = inPx;
            while (px > inWinWidth) {
                px -= (256 << inZoom);
            }
            if (px >= 0) {
                return px; // successfully wrapped back onto the screen
            }
        } else if (inPx < 0) {
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

    /**
	 * Draw the lines while dragging a point
	 * @param inG graphics object
	 * @param inPrevIndex index of point to draw from
	 * @param inNextIndex index of point to draw to
	 */
    /*private void drawDragLines(Graphics inG, int inPrevIndex, int inNextIndex)
	{
		inG.setColor(Config.getColourScheme().getColour(ColourScheme.IDX_POINT));
		// line from prev point to cursor
		if (inPrevIndex > -1 && !_track.getPoint(inPrevIndex+1).getSegmentStart())
		{
			final int px = getWidth() / 2 + _mapPosition.getXFromCentre(_track.getX(inPrevIndex));
			final int py = getHeight() / 2 + _mapPosition.getYFromCentre(_track.getY(inPrevIndex));
			inG.drawLine(px, py, _dragToX, _dragToY);
		}
		if (inNextIndex < _track.getNumPoints() && !_track.getPoint(inNextIndex).getSegmentStart())
		{
			final int px = getWidth() / 2 + _mapPosition.getXFromCentre(_track.getX(inNextIndex));
			final int py = getHeight() / 2 + _mapPosition.getYFromCentre(_track.getY(inNextIndex));
			inG.drawLine(px, py, _dragToX, _dragToY);
		}
	}*/

    /**
     * Make a semi-transparent colour for drawing with
     * @param inColour base colour (fully opaque)
     * @param inOpacity opacity where 0=invisible and 255=full
     * @return new colour object
     */
    private static Color makeTransparentColour(Color inColour, int inOpacity) {
        if (inOpacity > 240)
            return inColour;
        return new Color(inColour.getRed(), inColour.getGreen(), inColour.getBlue(), inOpacity);
    }

    /**
     * Inform that tiles have been updated and the map can be repainted
     * @param inIsOk true if data loaded ok, false for error
     */
    public synchronized void tilesUpdated(boolean inIsOk) {
        // Show message if loading failed (but not too many times)
        if (!inIsOk && !_shownOsmErrorAlready && _mapCheckBox.isSelected()) {
            _shownOsmErrorAlready = true;
            // use separate thread to show message about failing to load osm images
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                    _app.showErrorMessage("error.osmimage.dialogtitle", "error.osmimage.failed");
                }
            }).start();
        }
        _recalculate = true;
        repaint();
    }

    /**
     * Zoom out, if not already at minimum zoom
     */
    public void zoomOut() {
        _mapPosition.zoomOut();
        _recalculate = true;
        repaint();
    }

    /**
     * Zoom in, if not already at maximum zoom
     */
    public void zoomIn() {
        // See if selected point is currently visible, if so (and autopan on) then autopan after zoom to keep it visible
        //boolean wasVisible = _autopanCheckBox.isSelected() && isCurrentPointVisible();
        _mapPosition.zoomIn();
        /*if (wasVisible && !isCurrentPointVisible()) {
			autopanToPoint(_selection.getCurrentPointIndex());
		}*/
        _recalculate = true;
        repaint();
    }

    /**
     * Pan map
     * @param inDeltaX x shift
     * @param inDeltaY y shift
     */
    public void panMap(int inDeltaX, int inDeltaY) {
        _mapPosition.pan(inDeltaX, inDeltaY);
        _recalculate = true;
        repaint();
    }

    /**
     * Create a DataPoint object from the given click coordinates
     * @param inX x coordinate of click
     * @param inY y coordinate of click
     * @return DataPoint with given coordinates and no altitude
     */
    private DataPoint createPointFromClick(int inX, int inY) {
        double lat = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(inY, getHeight()));
        double lon = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(inX, getWidth()));
        return new DataPoint(new Latitude(lat, Coordinate.FORMAT_NONE), new Longitude(lon, Coordinate.FORMAT_NONE), null);
    }

    /**
	 * Move a DataPoint object to the given mouse coordinates
	 * @param startX start x coordinate of mouse
	 * @param startY start y coordinate of mouse
	 * @param endX end x coordinate of mouse
	 * @param endY end y coordinate of mouse
	 */
    /*private void movePointToMouse(int startX, int startY, int endX, int endY )
	{
		double lat1 = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(startY, getHeight()));
		double lon1 = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(startX, getWidth()));
		double lat_delta = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(endY, getHeight())) - lat1;
		double lon_delta = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(endX, getWidth())) - lon1;

		DataPoint point = _trackInfo.getCurrentPoint();
		if (point == null) {
			return;
		}

		// Make lists for edit and undo, and add each changed field in turn
		FieldEditList editList = new FieldEditList();
		FieldEditList undoList = new FieldEditList();

		// Check field list
		FieldList fieldList = _track.getFieldList();
		int numFields = fieldList.getNumFields();
		for (int i=0; i<numFields; i++)
		{
			Field field = fieldList.getField(i);
			if (field == Field.LATITUDE) {
				editList.addEdit(new FieldEdit(field, Double.toString(point.getLatitude().getDouble() + lat_delta)));
				undoList.addEdit(new FieldEdit(field, point.getFieldValue(Field.LATITUDE)));
			}
			else if (field == Field.LONGITUDE) {
				editList.addEdit(new FieldEdit(field, Double.toString(point.getLongitude().getDouble() + lon_delta)));
				undoList.addEdit(new FieldEdit(field, point.getFieldValue(Field.LONGITUDE)));
			}
		}
		_app.completePointEdit(editList, undoList);
	}*/


    /**
     * @see javax.swing.JComponent#getMinimumSize()
     */
    public Dimension getMinimumSize() {
        final Dimension minSize = new Dimension(512, 300);
        return minSize;
    }

    /**
     * @see javax.swing.JComponent#getPreferredSize()
     */
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }


    /**
     * Respond to mouse click events
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent inE) {
        if (true) { //_track != null && _track.getNumPoints() > 0)
            mousePosition = inE.getPoint();
            // select point if it's a left-click
            if (!inE.isMetaDown()) {
                if (inE.getClickCount() == 1) {
                    // single click
                    if (_drawMode == MODE_DEFAULT) {
                        int pointIndex = _clickedPoint;
                        if (pointIndex == INDEX_UNKNOWN) {
                            findPoints(mousePosition, geometrySets.elementAt(0), engineConfig);
                            // index hasn't been calculated yet
                            /*pointIndex = _track.getNearestPointIndex(
							 _mapPosition.getXFromPixels(inE.getX(), getWidth()),
							 _mapPosition.getYFromPixels(inE.getY(), getHeight()),
							 _mapPosition.getBoundsFromPixels(CLICK_SENSITIVITY), false);*/
                        }
                        // Extend selection for shift-click
                        if (inE.isShiftDown()) {
                            _trackInfo.extendSelection(pointIndex);
                        } else {
                            _trackInfo.selectPoint(pointIndex);
                        }
                    } else if (_drawMode == MODE_DRAW_POINTS_START) {
                        _app.createPoint(createPointFromClick(inE.getX(), inE.getY()));
                        _dragToX = inE.getX();
                        _dragToY = inE.getY();
                        _drawMode = MODE_DRAW_POINTS_CONT;
                    } else if (_drawMode == MODE_DRAW_POINTS_CONT) {
                        DataPoint point = createPointFromClick(inE.getX(), inE.getY());
                        _app.createPoint(point);
                        point.setSegmentStart(false);
                    }
                } else if (inE.getClickCount() == 2) {
                    // double click
                    if (_drawMode == MODE_DEFAULT) {
                        panMap(inE.getX() - getWidth() / 2, inE.getY() - getHeight() / 2);
                        zoomIn();
                    } else if (_drawMode == MODE_DRAW_POINTS_START || _drawMode == MODE_DRAW_POINTS_CONT) {
                        _drawMode = MODE_DEFAULT;
                    }
                }
            } else {
                // show the popup menu for right-clicks
                _popupMenuX = inE.getX();
                _popupMenuY = inE.getY();
                /* Evito mostrar el POPUP */
                //_popup.show(this, _popupMenuX, _popupMenuY);
            }
        }
        // Reset app mode
        _app.setCurrentMode(App.AppMode.NORMAL);
        if (_drawMode == MODE_MARK_RECTANGLE) {
            _drawMode = MODE_DEFAULT;
        }
    }

    /**
     * Ignore mouse enter events
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent inE) {
        // ignore
    }

    /**
     * Ignore mouse exited events
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent inE) {
        // ignore
    }

    /**
     * React to mouse pressed events to initiate a point drag
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent inE) {
        _clickedPoint = INDEX_UNKNOWN;
        if (true) //_track == null || _track.getNumPoints() <= 0)
            return;
        if (!inE.isMetaDown()) {
            // Left mouse drag - check if point is near; if so select it for dragging
            if (_drawMode == MODE_DEFAULT) {
                /* Drag points if edit mode is enabled OR ALT is pressed */
                if (_editmodeCheckBox.isSelected() || inE.isAltDown() || inE.isAltGraphDown()) {
                    final double clickX = _mapPosition.getXFromPixels(inE.getX(), getWidth());
                    final double clickY = _mapPosition.getYFromPixels(inE.getY(), getHeight());
                    final double clickSens = _mapPosition.getBoundsFromPixels(CLICK_SENSITIVITY);
                    //_clickedPoint = _track.getNearestPointIndex(clickX, clickY, clickSens, false);

                    if (_clickedPoint >= 0) {
                        // TODO: maybe use another color of the cross or remove the cross while dragging???

                        _trackInfo.selectPoint(_clickedPoint);
                        if (_trackInfo.getCurrentPoint() != null) {
                            _drawMode = MODE_DRAG_POINT;
                            _dragFromX = _dragToX = inE.getX();
                            _dragFromY = _dragToY = inE.getY();
                        }
                    } else {
                        // Not a click on a point, so check half-way between two (connected) trackpoints
                        int midpointIndex = _midpoints.getNearestPointIndex(clickX, clickY, clickSens);
                        if (midpointIndex > 0) {
                            _drawMode = MODE_CREATE_MIDPOINT;
                            _clickedPoint = midpointIndex;
                            _dragFromX = _dragToX = inE.getX();
                            _dragFromY = _dragToY = inE.getY();
                        }
                    }
                }
            }
        }
        // else right-press ignored
    }

    /**
     * Respond to mouse released events
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent inE) {
        _recalculate = true;

        if (_drawMode == MODE_DRAG_POINT) {
            if (Math.abs(_dragToX - _dragFromX) > 2 || Math.abs(_dragToY - _dragFromY) > 2) {
                //movePointToMouse(_dragFromX, _dragFromY, _dragToX, _dragToY );
            }
            _drawMode = MODE_DEFAULT;
        } else if (_drawMode == MODE_CREATE_MIDPOINT) {
            _drawMode = MODE_DEFAULT;
            _app.createPoint(createPointFromClick(_dragToX, _dragToY), _clickedPoint);
        } else if (_drawMode == MODE_ZOOM_RECT) {
            if (Math.abs(_dragToX - _dragFromX) > 20 && Math.abs(_dragToY - _dragFromY) > 20) {
                _mapPosition.zoomToPixels(_dragFromX, _dragToX, _dragFromY, _dragToY, getWidth(), getHeight());
            }
            _drawMode = MODE_DEFAULT;
        } else if (_drawMode == MODE_MARK_RECTANGLE) {
            // Reset app mode
            _app.setCurrentMode(App.AppMode.NORMAL);
            _drawMode = MODE_DEFAULT;
            // Call a function to mark the points
            MarkPointsInRectangleFunction marker = new MarkPointsInRectangleFunction(_app);
            double lon1 = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(_dragFromX, getWidth()));
            double lat1 = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(_dragFromY, getHeight()));
            double lon2 = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels(_dragToX, getWidth()));
            double lat2 = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels(_dragToY, getHeight()));
            // Invalidate rectangle if pixel coords are (-1,-1)
            if (_dragFromX < 0 || _dragFromY < 0) {
                lon1 = lon2;
                lat1 = lat2;
            }
            marker.setRectCoords(lon1, lat1, lon2, lat2);
            marker.begin();
        }
        _dragFromX = _dragFromY = -1;
        repaint();
    }

    /**
     * Respond to mouse drag events
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    public void mouseDragged(MouseEvent inE) {
        if (!inE.isMetaDown()) {
            // Left mouse drag - either drag the point or pan the map
            if (_drawMode == MODE_DRAG_POINT || _drawMode == MODE_CREATE_MIDPOINT) {
                // move point
                _dragToX = inE.getX();
                _dragToY = inE.getY();
                _recalculate = true;
                repaint();
            } else if (_drawMode == MODE_MARK_RECTANGLE) {
                // draw a rectangle for marking points
                if (_dragFromX == -1) {
                    _dragFromX = inE.getX();
                    _dragFromY = inE.getY();
                }
                _dragToX = inE.getX();
                _dragToY = inE.getY();
                repaint();
            } else {
                // regular left-drag pans map by appropriate amount
                if (_dragFromX != -1) {
                    panMap(_dragFromX - inE.getX(), _dragFromY - inE.getY());
                }
                _dragFromX = _dragToX = inE.getX();
                _dragFromY = _dragToY = inE.getY();
            }
        } else {
            // Right-click and drag - update rectangle
            _drawMode = MODE_ZOOM_RECT;
            if (_dragFromX == -1) {
                _dragFromX = inE.getX();
                _dragFromY = inE.getY();
            }
            _dragToX = inE.getX();
            _dragToY = inE.getY();
            repaint();
        }
    }

    /**
     * Respond to mouse move events without button pressed
     * @param inEvent ignored
     */
    public void mouseMoved(MouseEvent inEvent) {
        boolean useCrosshairs = false;
        boolean useResize = false;
        
        double _X = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels((int)inEvent.getX(), getWidth()));
        double _Y = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels((int)inEvent.getY(), getHeight()));
        LatLongCoord _ll = new LatLongCoord(_Y, _X);
        coordinateViewer.setLLCoord(_ll);
        //System.out.println(_ll.getLatitude() + " " + _ll.getLongitude());

        // Ignore unless we're drawing points
        if (_drawMode == MODE_DRAW_POINTS_CONT) {
            _dragToX = inEvent.getX();
            _dragToY = inEvent.getY();
            repaint();
        } else if (_drawMode == MODE_MARK_RECTANGLE) {
            useResize = true;
        } else if (_editmodeCheckBox.isSelected() || inEvent.isAltDown() || inEvent.isAltGraphDown()) {
            // Try to find a point or a midpoint at this location, and if there is one
            // then change the cursor to crosshairs
            final double clickX = _mapPosition.getXFromPixels(inEvent.getX(), getWidth());
            final double clickY = _mapPosition.getYFromPixels(inEvent.getY(), getHeight());
            final double clickSens = _mapPosition.getBoundsFromPixels(CLICK_SENSITIVITY);
            /*useCrosshairs = (_track.getNearestPointIndex(clickX, clickY, clickSens, false) >= 0
				|| _midpoints.getNearestPointIndex(clickX, clickY, clickSens) >= 0
			);*/
        }
        if (useCrosshairs && !isCursorSet()) {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else if (useResize && !isCursorSet()) {
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else if (!useCrosshairs && !useResize && isCursorSet()) {
            setCursor(null);
        }
    }

    /**
     * Respond to status bar message from broker
     * @param inMessage message, ignored
     */
    public void actionCompleted(String inMessage) {
        // ignore
    }

    /**
     * Respond to data updated message from broker
     * @param inUpdateType type of update
     */
    public void dataUpdated(byte inUpdateType) {
        _recalculate = true;
        if ((inUpdateType & DataSubscriber.DATA_ADDED_OR_REMOVED) > 0) {
            _checkBounds = true;
        }
        if ((inUpdateType & DataSubscriber.MAPSERVER_CHANGED) > 0) {
            _tileManager.resetConfig();
        }
        if ((inUpdateType & (DataSubscriber.DATA_ADDED_OR_REMOVED + DataSubscriber.DATA_EDITED)) > 0) {
            //_midpoints.updateData(_track);
        }
        // See if rect mode has been activated
        if (_app.getCurrentMode() == App.AppMode.DRAWRECT) {
            _drawMode = MODE_MARK_RECTANGLE;
            if (!isCursorSet()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            }
        }
        repaint();
        // enable or disable components
        boolean hasData = true; //_track.getNumPoints() > 0;
        _topPanel.setVisible(hasData);
        _sidePanel.setVisible(hasData);
        // grab focus for the key presses
        this.requestFocus();
    }

    /**
     * Respond to key presses on the map canvas
     * @param inE key event
     */
    public void keyPressed(KeyEvent inE) {
        int code = inE.getKeyCode();
        int currPointIndex = _selection.getCurrentPointIndex();
        // Check for Ctrl key (for Linux/Win) or meta key (Clover key for Mac)
        if (inE.isControlDown() || inE.isMetaDown()) {
            // Shift as well makes things faster
            final int pointIncrement = inE.isShiftDown() ? 3 : 1;
            // Check for arrow keys to zoom in and out
            if (code == KeyEvent.VK_UP)
                zoomIn();
            else if (code == KeyEvent.VK_DOWN)
                zoomOut();
            // Key nav for next/prev point
            else if (code == KeyEvent.VK_LEFT && currPointIndex > 0)
                _trackInfo.incrementPointIndex(-pointIncrement);
            else if (code == KeyEvent.VK_RIGHT)
                _trackInfo.incrementPointIndex(pointIncrement);
            else if (code == KeyEvent.VK_PAGE_UP)
                _trackInfo.selectPoint(Checker.getPreviousSegmentStart(_trackInfo.getTrack(), _trackInfo.getSelection().getCurrentPointIndex()));
            else if (code == KeyEvent.VK_PAGE_DOWN)
                _trackInfo.selectPoint(Checker.getNextSegmentStart(_trackInfo.getTrack(), _trackInfo.getSelection().getCurrentPointIndex()));
            // Check for home and end
            else if (code == KeyEvent.VK_HOME)
                _trackInfo.selectPoint(0);
            else if (code == KeyEvent.VK_END)
                _trackInfo.selectPoint(_trackInfo.getTrack().getNumPoints() - 1);
        } else {
            // Check for arrow keys to pan
            int upwardsPan = 0;
            if (code == KeyEvent.VK_UP)
                upwardsPan = -PAN_DISTANCE;
            else if (code == KeyEvent.VK_DOWN)
                upwardsPan = PAN_DISTANCE;
            int rightwardsPan = 0;
            if (code == KeyEvent.VK_RIGHT)
                rightwardsPan = PAN_DISTANCE;
            else if (code == KeyEvent.VK_LEFT)
                rightwardsPan = -PAN_DISTANCE;
            panMap(rightwardsPan, upwardsPan);
            // Check for escape
            if (code == KeyEvent.VK_ESCAPE)
                _drawMode = MODE_DEFAULT;
            // Check for backspace key to delete current point (delete key already handled by menu)
            else if (code == KeyEvent.VK_BACK_SPACE && currPointIndex >= 0) {
                _app.deleteCurrentPoint();
            }
        }
    }

    /**
     * @param inE key released event, ignored
     */
    public void keyReleased(KeyEvent e) {
        // ignore
    }

    /**
     * @param inE key typed event, ignored
     */
    public void keyTyped(KeyEvent inE) {
        // ignore
    }

    /**
     * @param inE mouse wheel event indicating scroll direction
     */
    public void mouseWheelMoved(MouseWheelEvent inE) {
        int clicks = inE.getWheelRotation();
        if (clicks < 0) {
            panMap((inE.getX() - getWidth() / 2) / 2, (inE.getY() - getHeight() / 2) / 2);
            zoomIn();
        } else if (clicks > 0) {
            panMap(-(inE.getX() - getWidth() / 2), -(inE.getY() - getHeight() / 2));
            zoomOut();
        }
    }

    /**
     * @return current map position
     */
    public MapPosition getMapPosition() {
        return _mapPosition;
    }


    /*************************************************** BASICDRAWENGINE *********************************************/

    private Vector<Layer> layers = new Vector<Layer>();
    //private Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
    private Vector<LayerGroup> layerGroups = new Vector<LayerGroup>();
    private Graphics2D labelGraphics2D = null;
    private BasicDrawEngineConfig engineConfig = new BasicDrawEngineConfig();
    public Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();

    private void drawLayers(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, BasicDrawEngineConfig _engineConfig) {
        try {
            /*if (_engineConfig.isAntiAlias() && !mouseActive) {
	       setAntiAlias(_graphics2D);
	       setAntiAlias(_labelGraphics2D);
	   } else {
	       unsetAntiAlias(_graphics2D);
	       unsetAntiAlias(_labelGraphics2D);
	    }
	    for (int i = 0; i < layers.size(); i++) {
		drawImageAttachments(_graphics2D, layers.elementAt(i));
	    }*/
            BufferedImage _labelImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
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

    public void setMapExtents(String _extents) {
        String extents[] = _extents.split(" ");
        setMapExtents(Double.parseDouble(extents[0]), Double.parseDouble(extents[1]), Double.parseDouble(extents[2]), Double.parseDouble(extents[3]));
    }

    public void setMapExtents(double lonMin, double latMin, double lonMax, double latMax) {
        _lonRange = new DoubleRange(lonMin, lonMax);
        _latRange = new DoubleRange(latMin, latMax);
    }

    public void initialize() {
        //addGeometrySets(GaiaEnvironment.geometrySets);
        //addLayerGroups(GaiaEnvironment.layerGroups);
        zoomToFit();
    }

    public void addGeometrySets(Vector<GeometrySet> _geometrySetVector) {
        for (int i = 0; i < _geometrySetVector.size(); i++) {
            addGeometrySet(_geometrySetVector.elementAt(i));
        }
    }

    public void addGeometrySet(GeometrySet _geometrySet) {
        if (_geometrySet.hasAccessPrivileges()) {
            geometrySets.add(_geometrySet);
            /*containedShapeIDS = new int[geometrySets.size()][0];
	    if (_geometrySet.getGeometrySetConfig().getServerURL().length() == 0 && _geometrySet.getGeometrySetConfig().isPropietary() && engineConfig.getPropietaryServerURL().length() > 0) {
		_geometrySet.getGeometrySetConfig().setConnectionParams(Base64Coder.decode(engineConfig.getPropietaryServerURL()), Base64Coder.decode(engineConfig.getPropietaryDatabase()), Base64Coder.decode(engineConfig.getPropietaryUser()), Base64Coder.decode(engineConfig.getPropietaryPassword()));
	    }*/
        } else {
            System.out.println("No tiene privilegios de acceso al grupo de geometrías " + _geometrySet.getGeometrySetConfig().getSqlScheme() + "." + _geometrySet.getGeometrySetConfig().getSqlTable());
        }
    }

    public void addLayerGroups(Vector<LayerGroup> _layerGroupVector) {
        for (int i = 0; i < _layerGroupVector.size(); i++) {
            addLayerGroup(_layerGroupVector.elementAt(i));
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
            //_layer.setOsnapPointsVector(osnapPointsVector);
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
        Timer autoUpdateTimer = new Timer(_layer.getLayerConfig().getAutoUpdateRateInSeconds() * 1000, new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                if (_layer.isOn()) {
                    System.out.println("Auto-Updating layer " + _layer.getAlias());
                    //_layer.reload();
                    //_layer.fetchValues();
                    _layer.getGeometrySet().reload();
                }
            }

        });
        autoUpdateTimer.start();
    }

    private void drawPoints(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
        ESRIPolygon _bounds = toSpace(getBounds());
        Vector<ESRIPoint> clickedPoints = new Vector<ESRIPoint>();
        for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
            for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
                if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
                    ESRIPoint[] _points = toPointsArray(_geometrySet.getGeometriesFromMatrix(m, n));
                    for (int i = 0; i < _points.length; i++) {
                        Vector<Layer> _layers = _geometrySet.getLayers();
                        int j = _layers.size() - 1;
                        boolean _drawn = false;
                        while (j >= 0 && !_drawn) {
                            if ( /*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
                                Layer _layer = _layers.elementAt(j);
                                int _pointDiameter = _layer.getPointDiameter();
                                if ((_pointDiameter * _engineConfig.getDrawFactor() >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _pointDiameter * _engineConfig.getDrawFactor() > 2)) && (_pointDiameter * _engineConfig.getDrawFactor() <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
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
                                        Shape _point = new Ellipse2D.Double(xtoPixel(_points[i].getX()) - (double)_pointDiameter / 2.0, ytoPixel(_points[i].getY()) + (double)_pointDiameter / 2.0, ((double)_pointDiameter * 1), ((double)_pointDiameter * 1));
                                        /*if (_point.contains(mousePosition)) {
					    //Borro la posición del mouse para evitar múltiples FireClicks
					    //mousePosition.setLocation(-1,-1);
					    //clickedPoints.add(_points[i]);
					    //System.out.println("Clicked " + _points[i]);
					    //_layer.fireClick(_points[i]);
					} else {
					}*/
                                        if ( /*_layer.drawGeometries() || */_drawPoint) { // || isSelectedLayer) {
                                            if (false) { //mouseActive && !rasterImageMatrixMode) {
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

                                                    int width = (int)(imagen.getWidth(this) / 1);
                                                    int height = (int)(imagen.getHeight(this) / 1);
                                                    //int width = (int)(imagen.getWidth(this) / 1 * _engineConfig.getDrawFactor());
                                                    //int height = (int)(imagen.getHeight(this) / 1 * _engineConfig.getDrawFactor());
                                                    //TODO REPARAR ESCALADO DEL SÍMBOLO
                                                    width = 5 * (imagen.getWidth(this) / (_mapPosition.getZoom()));
                                                    height = 5 * (imagen.getHeight(this) / (_mapPosition.getZoom()));
                                                    //width = (int)(imagen.getWidth(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
                                                    //height = (int)(imagen.getHeight(this) * _engineConfig.getDrawFactor() * _layer.getPointDiameter() / 10);
                                                    if (width > 0 || height > 0) {
                                                        int centX = xtoPixel(_points[i].getX()) - (width / 2);
                                                        int centY = ytoPixel(_points[i].getY()) + (height / 2);
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
                                            /*addOsnapPoint(_points[i]);
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
					    }*/
                                        }
                                        String _label = _layer.getLabelValue(_points[i].getIdPoint());
                                        if (_label.length() > 0 && _layer.getLayerConfig().paintLabels()) {
                                            Font _originalFont = _styleConfig.getLabelFont();
                                            //Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100 ? 100 : (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
                                            Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize()) > 100 ? 100 : (int)(_originalFont.getSize()));
                                            if (_labelFont.getSize() >= 6) {
                                                _labelGraphics2D.setFont(_labelFont);
                                                _labelGraphics2D.setColor(Color.WHITE);
                                                Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
                                                _labelGraphics2D.fillRect(xtoPixel(_points[i].getX()) - (int)(metricBounds.getWidth() / 2.0),
                                                                          ytoPixel(_points[i].getY()) - (int)(metricBounds.getHeight()/2.0),
                                                                          (int)metricBounds.getWidth(),
                                                                          (int)(metricBounds.getHeight()));
                                                _labelGraphics2D.setColor(Color.ORANGE);
                                                _labelGraphics2D.drawRect(xtoPixel(_points[i].getX()) - (int)(metricBounds.getWidth() / 2.0)-5,
                                                                          ytoPixel(_points[i].getY()) - (int)(metricBounds.getHeight()/2.0)-5,
                                                                          (int)metricBounds.getWidth()+10,
                                                                          (int)(metricBounds.getHeight())+3);
                                                _labelGraphics2D.setColor(_styleConfig.getLabelColor());
                                                _labelGraphics2D.drawString(_label, xtoPixel(_points[i].getX()) - (int)(metricBounds.getWidth() / 2.0), ytoPixel(_points[i].getY()) + (int)(metricBounds.getHeight() / 4.0));
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

    private void drawPolygons(Graphics2D _graphics2D, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
        ESRIPolygon _bounds = toSpace(getBounds());
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
                                            ESRIPolygon _polygon = getFakePolygon(_polygons[i]);
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
                                                    BufferedImage imagen = BasicDrawEngineConfig.getSymbol(_symbol);
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
                                                //Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 100 ? 100 : (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
                                                Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize()) > 100 ? 100 : (int)(_originalFont.getSize()));
                                                if (_labelFont.getSize() >= 6) {
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
                                                        ESRIPolygon _polygon = getFakePolygon(_polygons[i]);
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
                                                    _labelGraphics2D.drawString(_label, xtoPixel(_polygons[i].getCentroid().getX()) - (int)(metricBounds.getWidth() / 2.0), ytoPixel(_polygons[i].getCentroid().getY()) + (int)(metricBounds.getHeight() / 4.0));
                                                    //_labelGraphics2D.drawString(_label, (int)x, (int)y);
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
                                /*if (_selectPolygon) {
                                    int[] _containedShapes = containedShapeIDS[geometrySets.indexOf(_geometrySet)];
                                    for (int k = 0; k < _containedShapes.length; k++)  {
                                        if (_containedShapes[k] == _polygons[i].getIdPolygon()) {
                                            ESRIPolygon _polygon = getFakePolygon(_polygons[i]);
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
                                }*/
                            }//fi contains
                            j--;
                        }
                    }
                }
            }
        }
    }
    
    public Point getMapPosition(double lat, double lon, boolean checkOutside) {
        /*int x = OsmMercator.LonToX(lon, _currentZoomLevel);
        int y = OsmMercator.LatToY(lat, _currentZoomLevel);
        x -= center.x - getWidth() / 2;
        y -= center.y - getHeight() / 2;*/
        int x = OsmMercator.LonToX(lon, _mapPosition.getZoom());
        int y = OsmMercator.LatToY(lat, _mapPosition.getZoom());
        x -= _mapPosition.getXFromCentre(lon);
        y -= _mapPosition.getYFromCentre(lat);
        if (checkOutside) {
            if (x < 0 || y < 0 || x > getWidth() || y > getHeight())
                return null;
        }
        return new Point(x, y);
    }
    
    private void drawPolylines(Graphics2D _graphics2d, Graphics2D _labelGraphics2D, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
        ESRIPolygon _bounds = toSpace(getBounds());
        for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
            for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
                if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
                    ESRIPolygon[] _polylines = toPolygonsArray(_geometrySet.getGeometriesFromMatrix(m, n));
                    for (int i = 0; i < _polylines.length; i++) {
                        Vector<Layer> _layers = _geometrySet.getLayers();
                        int j = _layers.size() - 1;
                        boolean _drawn = false;
                        while (j >= 0 && !_drawn) {
                            if ( /*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
                                Layer _layer = _layers.elementAt(j);
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
                                    _graphics2d.setColor(_styleConfig.getOutlineColor() != null ? _styleConfig.getOutlineColor() : _styleConfig.getFillColor());
                                    if (true) { //(((_width >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _width > 2)) || (_heigth >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _heigth > 2))) && (_width <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0) && (_heigth <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
                                        if ( /*_layer.drawGeometries() || */_drawPolyline) {
                                            if (_engineConfig.isOsnapActive()) {
                                                ESRIPolygon _polyline = getFakePolygon(_polylines[i]);
                                                for (int k = 0; k < _polylines[i].getVertexCount(); k++) {
                                                    if (getBounds().contains(_polyline.getX(k), _polyline.getY(k))) {
                                                        //addOsnapPoint(new ESRIPoint(_polylines[i].getX(k), _polylines[i].getY(k)));
                                                    }
                                                }
                                            }
                                            if (false) { //mouseActive || rasterImageMatrixMode) {
                                                if (_polylines[i].getBounds2D().getWidth() > 20 && _polylines[i].getBounds2D().getHeight() > 20) {
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
                                            _graphics2d.drawPolyline(xtoPixel(_polylines[i].getXCoords()), ytoPixel(_polylines[i].getYCoords()), _polylines[i].getVertexCount());
                                            _graphics2d.setStroke(new BasicStroke(1));
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
                                        if (true) { //!mouseActive || rasterImageMatrixMode) {
                                            String _label = _layer.getLabelValue(_polylines[i].getIdPolygon());
                                            if (_label.length() > 0 && _layer.getLayerConfig().paintLabels()) { //start if
                                                Font _originalFont = _layer.getLayerConfig().getStyleConfig().getLabelFont();
                                                Font _labelFont = new Font(_originalFont.getName(), _originalFont.getStyle(), (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()) > 35 ? 35 : (int)(_originalFont.getSize() * _engineConfig.getDrawFactor()));
                                                if (_labelFont.getSize() > 6) {
                                                    _labelGraphics2D.setFont(_labelFont);
                                                    _labelGraphics2D.setColor(_styleConfig.getLabelColor());
                                                    Rectangle2D metricBounds = _labelGraphics2D.getFontMetrics().getStringBounds(_label, _labelGraphics2D);
                                                    int k = 0;
                                                    boolean _found = false;
                                                    ESRIPolygon _polyline = getFakePolygon(_polylines[i]);
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

    private ESRIPolygon[] toPolygonsArray(Vector _geometries) {
        Object[] _objects = _geometries.toArray();
        ESRIPolygon[] _polygons = new ESRIPolygon[_objects.length];
        for (int i = 0; i < _objects.length; i++) {
            _polygons[i] = (ESRIPolygon)_objects[i];
        }
        return _polygons;
    }

    private ESRIPoint[] toPointsArray(Vector _geometries) {
        Object[] _objects = _geometries.toArray();
        ESRIPoint[] _points = new ESRIPoint[_objects.length];
        for (int i = 0; i < _objects.length; i++) {
            _points[i] = (ESRIPoint)_objects[i];
        }
        return _points;
    }

    private ESRIPolygon.Double toSpace(Rectangle _rectangle) {
        double[] xy = new double[10];
        double _minX = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels((int)_rectangle.getMinX(), getWidth()));
        double _minY = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels((int)_rectangle.getMinY(), getHeight()));
        double _maxX = MapUtils.getLongitudeFromX(_mapPosition.getXFromPixels((int)_rectangle.getMaxX(), getWidth()));
        double _maxY = MapUtils.getLatitudeFromY(_mapPosition.getYFromPixels((int)_rectangle.getMaxY(), getHeight()));
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

    private ESRIPolygon getFakePolygon(ESRIPolygon _poly) {
        double[] xy = new double[_poly.getVertexCount() * 2];
        for (int i = 0; i < _poly.getVertexCount() * 2; i += 2) {

            int px = getWidth() / 2 + _mapPosition.getXFromCentre(MapUtils.getXFromLongitude(_poly.getX(i / 2)));
            int py = getHeight() / 2 + _mapPosition.getYFromCentre(MapUtils.getYFromLatitude(_poly.getY(i / 2)));
            px = wrapLongitudeValue(px, getWidth(), _mapPosition.getZoom());

            xy[i] = px;
            xy[i + 1] = py;
        }
        return new ESRIPolygon.Double(xy);
    }

    protected BasicStroke getStyleConfigStroke(StyleConfig _styleConfig) {
        if (_styleConfig.getStrokeStyle() == -1) {
            return new BasicStroke(_styleConfig.getLineWidth());
        } else {
            BasicStroke _stroke = (BasicStroke)StrokeSamples.strokeSamples[_styleConfig.getStrokeStyle()].getStroke();
            return new BasicStroke(_styleConfig.getLineWidth(), _stroke.getEndCap(), _stroke.getLineJoin(), _stroke.getMiterLimit(), _stroke.getDashArray(), _stroke.getDashPhase());
        }
    }

    public int[] xtoPixel(double[] _points) {
        int[] xpoints = new int[_points.length];
        for (int i = 0; i < _points.length; i++) {
            /*xpoints[i] = getWidth()/2  + _mapPosition.getXFromCentre(MapUtils.getXFromLongitude(_points[i]));
	    xpoints[i] = wrapLongitudeValue(xpoints[i], getWidth(), _mapPosition.getZoom());*/
            xpoints[i] = xtoPixel(_points[i]);
        }
        return xpoints;
    }

    public int[] ytoPixel(double[] _points) {
        int[] ypoints = new int[_points.length];
        for (int i = 0; i < _points.length; i++) {
            /*ypoints[i] = getHeight()/2 + _mapPosition.getYFromCentre(MapUtils.getYFromLatitude(_points[i]));*/
            ypoints[i] = ytoPixel(_points[i]);
        }
        return ypoints;
    }

    public int xtoPixel(double _point) {
        int xpoint = getWidth() / 2 + _mapPosition.getXFromCentre(MapUtils.getXFromLongitude(_point));
        xpoint = wrapLongitudeValue(xpoint, getWidth(), _mapPosition.getZoom());
        return xpoint;
    }

    public int ytoPixel(double _point) {
        int ypoint = getHeight() / 2 + _mapPosition.getYFromCentre(MapUtils.getYFromLatitude(_point));
        return ypoint;
    }

    public void setGeometrySets(Vector<GeometrySet> _geometrySets) {
        geometrySets = _geometrySets;
    }

    public Vector<GeometrySet> getGeometrySets() {
        return geometrySets;
    }

    public void setLayerGroups(Vector<LayerGroup> _layerGroups) {
        layerGroups = _layerGroups;
    }

    public Vector<LayerGroup> getLayerGroups() {
        return layerGroups;
    }

    /*public int[] getContainedShapeIDS(double _x, double _y) {
	boolean found = false;
	Vector<Integer> _containedShapeIDS = new Vector<Integer>();
	int i = 0;
	while (!found && i < getGeometrySets().size()) {
	    int j = 0;
	    while (!found && j < geometrySetConfig.getGridSize()) {
		int k = 0;
		while (!found && k < matrix[i][j].size()) {
		   ESRIPoint _point = (ESRIPoint)matrix[i][j].elementAt(k);
		   double _tolerance = Math.max((double)geometrySetConfig.getTolerance() , geometrySetConfig.getPointDiameter());
		   Shape point = new Ellipse2D.Double(_point.getX() -  _tolerance / 2.0, _point.getY() - _tolerance  / 2.0, _tolerance , _tolerance);
		   if (point.contains(new Point2D.Double(_x, _y))) {
		       _containedShapeIDS.add(_point.getIdPoint());
		   }
		   k++;
		}
		j++;
	    }
	    i++;
	}
	int[] _results = new int[_containedShapeIDS.size()];
	for (int i = 0; i < _results.length; i++)  {
	    _results[i] = _containedShapeIDS.elementAt(i);
	}
	return _results;
    }*/

    private void findPoints(Point mousePosition, GeometrySet _geometrySet, BasicDrawEngineConfig _engineConfig) {
        ESRIPolygon _bounds = toSpace(getBounds());
        Vector<ESRIPoint> clickedPoints = new Vector<ESRIPoint>();
        for (int m = 0; m < _geometrySet.getGeometrySetConfig().getGridSize(); m++) {
            for (int n = 0; n < _geometrySet.getGeometrySetConfig().getGridSize(); n++) {
                if (_bounds.intersects(_geometrySet.getGeometrySetConfig().getMatrixBounds()[0][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[1][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[2][m][n], _geometrySet.getGeometrySetConfig().getMatrixBounds()[3][m][n])) {
                    ESRIPoint[] _points = toPointsArray(_geometrySet.getGeometriesFromMatrix(m, n));
                    for (int i = 0; i < _points.length; i++) {
                        Vector<Layer> _layers = _geometrySet.getLayers();
                        int j = _layers.size() - 1;
                        boolean _drawn = false;
                        while (j >= 0 && !_drawn) {
                            if ( /*_geometrySet.contains(layers.elementAt(j)) && */_layers.elementAt(j).isOn()) {
                                Layer _layer = _layers.elementAt(j);
                                int _pointDiameter = _layer.getPointDiameter();
                                if ((_pointDiameter * _engineConfig.getDrawFactor() >= _layer.getLayerConfig().getMinScale() || (_layer.getLayerConfig().getMinScale() == 0.0 && _pointDiameter * _engineConfig.getDrawFactor() > 2)) && (_pointDiameter * _engineConfig.getDrawFactor() <= _layer.getLayerConfig().getMaxScale() || _layer.getLayerConfig().getMaxScale() == 0.0)) {
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
                                        Shape _point = new Ellipse2D.Double(xtoPixel(_points[i].getX()) - (double)_pointDiameter / 2.0, ytoPixel(_points[i].getY()) + (double)_pointDiameter / 2.0, ((double)_pointDiameter * 1), ((double)_pointDiameter * 1));
                                        if (_point.contains(mousePosition)) {
                                            //Borro la posición del mouse para evitar múltiples FireClicks
                                            //mousePosition.setLocation(-1,-1);
                                            clickedPoints.add(_points[i]);
                                            System.out.println("Clicked " + _points[i]);
                                            _layer.fireClick(_points[i]);
                                        } else {
                                        }
                                        if ( /*_layer.drawGeometries() || */_drawPoint) { // || isSelectedLayer) {
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

    public void setCoordinateViewer(CoordinateViewer _coordinateViewer) {
        coordinateViewer = _coordinateViewer;
        coordinateViewer.setVisible(true);
    }
    
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
    
    protected MouseMotionListener infoMotionListener = new MouseMotionListener() {
            public void mouseMoved(MouseEvent me) {
                updateToolTipPosition();
            }

            public void mouseDragged(MouseEvent me) {
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
                     doMultiQuery(createRectangleAsPolygon((multiQueryStartDrawRectPosition), (multiQueryEndDrawRectPosition)));
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
    
    public Point2D.Double toSpace(Point2D _point) {
        return new Point2D.Double(xToSpace((int)_point.getX()), yToSpace((int)_point.getY()));
    }

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
                    selectedGeometryIndex = selectedLayer.getGeometrySet().getContainedShapeID(mousexToSpace(), mouseyToSpace());
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

    private void restartEnvironment() {
        //labelinfo.setVisible(false);
        /*drawingPointsVector.removeAllElements();
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
        selectedLayer = null;*/
    }

    private ESRIPolygon createRectangleAsPolygon(Point2D.Double _startPosition, Point2D.Double _endPosition) {
        Rectangle _rectangle = null;
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
            _rectangle = new Rectangle(_drawRectX, _drawRectY, _drawRectWidth, _drawRectHeight);
        }
        return toSpace(_rectangle);
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

    public void setOperation(int _operation) {
        containedShapesTimer.stop();
        BasicDrawEngine x;
        infoTimer.stop();
        containedShapeIDS = new int[geometrySets.size()][0];
        removeMouseListener(eraseListener);
        removeMouseMotionListener(eraseMotionListener);
        boolean _ruleViewerVisible = ruleViewer.isVisible();
        switch (_operation) {
            /*case BasicDrawEngineConfig.OPERATION_DISTANCE_AREA:
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
                break;*/
            case BasicDrawEngineConfig.OPERATION_QUERY:
                eraseListener = infoMouseListener;
                eraseMotionListener = infoMotionListener;
                _ruleViewerVisible = false;
                restartEnvironment();
                operationStatus = "Ver Info: ";
                break;
            /*case BasicDrawEngineConfig.OPERATION_ZOOM_IN:
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
            //case BasicDrawEngineConfig.OPERATION_INFRASTRUCTURES:
            //    eraseListener = infrastructuresMouseListener;
            //    eraseMotionListener = infrastructuresMotionListener;
            //    _ruleViewerVisible = false;
            //    restartEnvironment();
            //    operationStatus = "Reasignando Infraestructura: ";
            //    break;
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
            */
            case BasicDrawEngineConfig.OPERATION_MULTIQUERY:
                eraseListener = multiQueryMouseListener;
                eraseMotionListener = multiQueryMotionListener;
                _ruleViewerVisible = false;
                restartEnvironment();
                operationStatus = "Ver MultiInfo: ";
                _drawMode = MODE_MARK_RECTANGLE;
                break;
            case BasicDrawEngineConfig.OPERATION_FIXED_POLYGON_QUERY:
                eraseListener = fixedPolygonQueryMouseListener;
                eraseMotionListener = fixedPolygonQueryMotionListener;
                _ruleViewerVisible = false;
                restartEnvironment();
                operationStatus = "Consulta de geometrias contenidas en un polígono: ";
                selectLayer();
                break;
            /*
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
            */
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

    private void showToolTip(boolean _visible) {
        lastShowingTime = System.currentTimeMillis();
        toolTip.setVisible(_visible);
        if (_visible) {
            hideToolTipTimer.start();
        } else {
        }
        repaint();
    }

    public void setRuleViewer(RuleViewer _ruleViewer) {
        ruleViewer = _ruleViewer;
    }


}
