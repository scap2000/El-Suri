package org.digitall.common.geo.mapping.osm.tilesources;

import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import org.digitall.common.geo.mapping.osm.Coordinate;
import org.digitall.common.geo.mapping.osm.interfaces.TileSource;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class BingAerialTileSource extends AbstractOsmTileSource {
    private static String API_KEY = "Arzdiw4nlOJzRwOz__qailc8NiR31Tt51dN2D7cm57NrnceZnCpgOkmJhNpGoppU";
    private static Future<List<Attribution>> attributions;

    public BingAerialTileSource() {
        super("Bing Aerial Maps", "http://ecn.t2.tiles.virtualearth.net/tiles/");

        if (attributions == null) {
            attributions = Executors.newSingleThreadExecutor().submit(new Callable<List<Attribution>>() {
                public List<Attribution> call() throws Exception {
                    return loadAttributionText();
                }
            });
        }
    }

    class Attribution {
        String attribution;
        int minZoom;
        int maxZoom;
        Coordinate min;
        Coordinate max;
    }

    class AttrHandler extends DefaultHandler {

        private String string;
        private Attribution curr;
        private List<Attribution> attributions = new ArrayList<Attribution>();
        private double southLat;
        private double northLat;
        private double eastLon;
        private double westLon;
        private boolean inCoverage = false;

        @Override
        public void startElement(String uri, String stripped, String tagName, Attributes attrs) throws SAXException {
            if ("ImageryProvider".equals(tagName)) {
                curr = new Attribution();
            } else if ("CoverageArea".equals(tagName)) {
                inCoverage = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            string = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String stripped, String tagName) throws SAXException {
            if ("ImageryProvider".equals(tagName)) {
                attributions.add(curr);
            } else if ("Attribution".equals(tagName)) {
                curr.attribution = string;
            } else if (inCoverage && "ZoomMin".equals(tagName)) {
                curr.minZoom = Integer.parseInt(string);
            } else if (inCoverage && "ZoomMax".equals(tagName)) {
                curr.maxZoom = Integer.parseInt(string);
            } else if (inCoverage && "SouthLatitude".equals(tagName)) {
                southLat = Double.parseDouble(string);
            } else if (inCoverage && "NorthLatitude".equals(tagName)) {
                northLat = Double.parseDouble(string);
            } else if (inCoverage && "EastLongitude".equals(tagName)) {
                eastLon = Double.parseDouble(string);
            } else if (inCoverage && "WestLongitude".equals(tagName)) {
                westLon = Double.parseDouble(string);
            } else if ("BoundingBox".equals(tagName)) {
                curr.min = new Coordinate(southLat, westLon);
                curr.max = new Coordinate(northLat, eastLon);
            } else if ("CoverageArea".equals(tagName)) {
                inCoverage = false;
            }
            string = "";
        }
    }

    private List<Attribution> loadAttributionText() {
        try {
            URL u = new URL("http://dev.virtualearth.net/REST/v1/Imagery/Metadata/Aerial/0,0?zl=1&mapVersion=v1&key="
                    + API_KEY + "&include=ImageryProviders&output=xml");
            URLConnection conn = u.openConnection();

            // This is not JOSM! Do not use anything other than standard JRE classes within this package!
            // See package.html for details
            //conn.setConnectTimeout(Main.pref.getInteger("imagery.bing.load-attribution-text.timeout", 4000));

            InputStream stream = conn.getInputStream();

            XMLReader parser = XMLReaderFactory.createXMLReader();
            AttrHandler handler = new AttrHandler();
            parser.setContentHandler(handler);
            parser.parse(new InputSource(stream));
            //System.err.println("Added " + handler.attributions.size() + " attributions.");
            return handler.attributions;
        } catch (IOException e) {
            System.err.println("Could not open Bing aerials attribution metadata.");
        } catch (SAXException e) {
            System.err.println("Could not parse Bing aerials attribution metadata.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getMaxZoom() {
        return 22;
    }

    @Override
    public String getExtension() {
        return ("jpeg");
    }

    @Override
    public String getTilePath(int zoom, int tilex, int tiley) throws IOException {
        try {
            if (attributions.get() == null)
                throw new IOException("Cannot load Bing attribution");
            String quadtree = computeQuadTree(zoom, tilex, tiley);
            return "/tiles/a" + quadtree + "." + getExtension() + "?g=587";
        } catch (Exception e) {
            throw new IOException("Cannot load Bing attribution", e);
        }
    }

    public TileUpdate getTileUpdate() {
        return TileSource.TileUpdate.IfNoneMatch;
    }

    @Override
    public boolean requiresAttribution() {
        return true;
    }

    @Override
    public Image getAttributionImage() {
        try {
            return ImageIO.read(getClass().getResourceAsStream("images/bing_maps.png"));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String getAttributionLinkURL() {
        //return "http://bing.com/maps"
        // FIXME: I've set attributionLinkURL temporarily to ToU URL to comply with bing ToU
        // (the requirement is that we have such a link at the bottom of the window)
        return "http://go.microsoft.com/?linkid=9710837";
    }

    @Override
    public String getTermsOfUseURL() {
        return "http://opengeodata.org/microsoft-imagery-details";
    }

    @Override
    public String getAttributionText(int zoom, Coordinate topLeft, Coordinate botRight) {
        try {
            if (!attributions.isDone())
                return "Loading Bing attribution data...";
            if (attributions.get() == null)
                return "Error loading Bing attribution data";
            StringBuilder a = new StringBuilder();
            for (Attribution attr : attributions.get()) {
                if (zoom <= attr.maxZoom && zoom >= attr.minZoom) {
                    if (topLeft.getLon() < attr.max.getLon() && botRight.getLon() > attr.min.getLon()
                            && topLeft.getLat() > attr.min.getLat() && botRight.getLat() < attr.max.getLat()) {
                        a.append(attr.attribution);
                        a.append(" ");
                    }
                }
            }
            return a.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error loading Bing attribution data";
    }

    static String computeQuadTree(int zoom, int tilex, int tiley) {
        StringBuilder k = new StringBuilder();
        for (int i = zoom; i > 0; i--) {
            char digit = 48;
            int mask = 1 << (i - 1);
            if ((tilex & mask) != 0) {
                digit += 1;
            }
            if ((tiley & mask) != 0) {
                digit += 2;
            }
            k.append(digit);
        }
        return k.toString();
    }
}