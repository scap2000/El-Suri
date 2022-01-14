package org.digitall.common.geo.mapping;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.text.DecimalFormat;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

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
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
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
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.OsnapPoints;
import org.digitall.lib.geo.mapping.classes.StyleConfig;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.geom.Polygon2D;
import org.digitall.lib.ssl.MD5;


/*import org.jposition.BasicMap;
import org.jposition.Coordinate;
import org.jposition.Dimension;*/

/** Motor Básico de Dibujo en 2D
 * Capaz de dibujar objetos de las siguientes clases
 * ESRIPoint
 * ESRIPolyline
 * ESRIPolygon
 * */
public class RasterizedBasicDrawEngine extends BasicDrawEngine {
    public RasterizedBasicDrawEngine(String _mapName, BasicLabel _statusBar) {
	super(_mapName, _statusBar);
    }
    
}
