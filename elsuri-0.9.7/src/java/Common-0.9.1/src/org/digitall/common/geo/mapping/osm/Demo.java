package org.digitall.common.geo.mapping.osm;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.digitall.common.geo.mapping.osm.interfaces.TileLoader;
import org.digitall.common.geo.mapping.osm.interfaces.TileSource;
import org.digitall.common.geo.mapping.osm.tilesources.BingAerialTileSource;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource.CycleMap;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource.Mapnik;
import org.digitall.common.geo.mapping.osm.tilesources.OsmTileSource.TilesAtHome;

/**
 *
 * Demonstrates the usage of {@link org.digitall.common.geo.mapping.osm.JMapViewer}
 *
 * @author Jan Peter Stotz
 *
 */
public class Demo extends JFrame {

    private static final long serialVersionUID = 1L;

    public Demo() {
        super("JMapViewer Demo");
        setSize(400, 400);
        final JMapViewer _mapViewer = new JMapViewer();
        // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
        // map.setTileLoader(new OsmFileCacheTileLoader(map));
        // new DefaultMapController(map);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel();
        JPanel helpPanel = new JPanel();
        add(panel, BorderLayout.NORTH);
        add(helpPanel, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Use right mouse button to move,\n "
                + "left double click or mouse wheel to zoom.");
        helpPanel.add(helpLabel);
        JButton button = new JButton("setDisplayToFitMapMarkers");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _mapViewer.setDisplayToFitMapMarkers();
            }
        });
        JComboBox tileSourceSelector = new JComboBox(new TileSource[] { new OsmTileSource.Mapnik(),
                new OsmTileSource.TilesAtHome(), new OsmTileSource.CycleMap(), new BingAerialTileSource() });
        tileSourceSelector.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
		try {
		    _mapViewer.setTileSource((TileSource) e.getItem());
		} catch  (Exception x) {
		    
		}
            }
        });
        JComboBox tileLoaderSelector;
        try {
            tileLoaderSelector = new JComboBox(new TileLoader[] { new OsmFileCacheTileLoader(_mapViewer),
                    new OsmTileLoader(_mapViewer) });
        } catch (IOException e) {
            tileLoaderSelector = new JComboBox(new TileLoader[] { new OsmTileLoader(_mapViewer) });
        }
        tileLoaderSelector.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                _mapViewer.setTileLoader((TileLoader) e.getItem());
            }
        });
        _mapViewer.setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());
        panel.add(tileSourceSelector);
        panel.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Map markers visible");
        showMapMarker.setSelected(_mapViewer.getMapMarkersVisible());
        showMapMarker.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _mapViewer.setMapMarkerVisible(showMapMarker.isSelected());
            }
        });
        panel.add(showMapMarker);
        final JCheckBox showTileGrid = new JCheckBox("Tile grid visible");
        showTileGrid.setSelected(_mapViewer.isTileGridVisible());
        showTileGrid.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _mapViewer.setTileGridVisible(showTileGrid.isSelected());
            }
        });
        panel.add(showTileGrid);
        final JCheckBox showZoomControls = new JCheckBox("Show zoom controls");
        showZoomControls.setSelected(_mapViewer.getZoomContolsVisible());
        showZoomControls.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _mapViewer.setZoomContolsVisible(showZoomControls.isSelected());
            }
        });
        panel.add(showZoomControls);
        panel.add(button);
        add(_mapViewer, BorderLayout.CENTER);

        //
        _mapViewer.addMapMarker(new MapMarkerDot(49.814284999, 8.642065999));
        _mapViewer.addMapMarker(new MapMarkerDot(49.91, 8.24));
        _mapViewer.addMapMarker(new MapMarkerDot(49.71, 8.64));
        _mapViewer.addMapMarker(new MapMarkerDot(48.71, -1));
        _mapViewer.addMapMarker(new MapMarkerDot(49.8588, 8.643));

        // map.setDisplayPositionByLatLon(49.807, 8.6, 11);
        // map.setTileGridVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // java.util.Properties systemProperties = System.getProperties();
        // systemProperties.setProperty("http.proxyHost", "localhost");
        // systemProperties.setProperty("http.proxyPort", "8008");
        new Demo().setVisible(true);
    }

}
