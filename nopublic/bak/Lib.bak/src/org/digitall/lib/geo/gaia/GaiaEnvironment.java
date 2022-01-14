package org.digitall.lib.geo.gaia;

import java.awt.Component;
import java.awt.geom.Point2D;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import java.util.Vector;

import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.ssl.MD5;

public abstract class GaiaEnvironment {

    /** Descripción correspondiente a las extensiones de los archivos
     * que se guardan en el caché
    public static String gaiaEngineProfile = "gep";
    public static String drawEngineConfig = "dec";
    public static String goemetrySetCache = "gsc";
    public static String layerConfigList = "lcl";
    public static String layerFilterValues = "lfv";
    public static String layerLabelValues = "llv";
    **/
    
    public static Component layerListPanel = null;
    public static Component layerListFrame = null; //Para guardar su ubicación
    public static BasicPanel formPanel = null;
    public static ExtendedInternalFrame formsFrame = null;
    public static BasicPanel drawEngine = null;
    public static Component mapBasicToolsPanel = null;
    public static Component mapBasicToolsFrame = null;
    public static Vector<GeometrySet> geometrySets = new Vector<GeometrySet>();
    public static Vector<LayerGroup> layerGroups = new Vector<LayerGroup>();
    public static Vector<Layer> nomencladorLayers = new Vector<Layer>();
    public static Vector<Layer> cadastralLayers = new Vector<Layer>();
    public static File tempDir = new File(System.getProperty("user.home") + File.separator + ".ddesktop.cache");
    public static String profileFileName = "gaia";
    public static GaiaEngine gaiaEngine = null;
    public static Point2D.Double point = null;

    public static String getCacheFileName(String _name) {
	return tempDir + File.separator + MD5.getMD5(_name + " - " + OrganizationInfo.getOrgName() + " - " + Environment.sessionUser);
    }
    
    public static void initialize(){
        gaiaEngine = new GaiaEngine();
        fetchGaiaProfileFromCache();   
    }
    
    private static synchronized void fetchGaiaProfileFromCache() {
        File _cacheFile = new File(GaiaEnvironment.getCacheFileName(GaiaEnvironment.profileFileName) + ".gep");
        if (_cacheFile.exists()) {
            try {
                System.out.println("Fetching  " + GaiaEnvironment.profileFileName + " configuration");
                // Read from disk using FileInputStream
                FileInputStream inFile = new FileInputStream(_cacheFile);

                // Read object using ObjectInputStream
                ObjectInputStream inObject = new ObjectInputStream(inFile);

                // Read an object
                Object cachedObject = inObject.readObject();

                if (cachedObject instanceof GaiaEngine) {
                    // Cast object to a Vector
                    gaiaEngine = (GaiaEngine)cachedObject;
                    
                }
                
            } catch (ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidClassException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("Fetching " + GaiaEnvironment.profileFileName + " configuration done...");
        }
    }
    
    public static String getCadastralLayer() {
	return gaiaEngine.getCadastralLayer();
    }

    public static void setCadastralLayer(String _cadastralLayer) {
	gaiaEngine.setCadastralLayer(_cadastralLayer);
    }

    public static String getScheme() {
	return gaiaEngine.getScheme();
    }

    public static void setScheme(String _scheme) {
	gaiaEngine.setScheme(_scheme);
    }

    public static void setStreetsLayer(String _streetsLayer) {
	gaiaEngine.setStreetsLayer(_streetsLayer);
    }

    public static String getStreetsLayer() {
	return gaiaEngine.getStreetsLayer();
    }

    public static void setSelectedStreetName(String _selectedStreetName) {
	gaiaEngine.setSelectedStreetName(_selectedStreetName);
    }

    public static String getSelectedStreetName() {
	return gaiaEngine.getSelectedStreetName();
    }

    public static void setAddressLayer(String _addressLayer) {
	gaiaEngine.setAddressLayer(_addressLayer);
    }

    public static String getAddressLayer() {
	return gaiaEngine.getAddressLayer();
    }

    public static void setInfrastructuresLayer(String _infrastructuresLayer) {
	gaiaEngine.setInfrastructuresLayer(_infrastructuresLayer);
    }

    public static String getInfrastructuresLayer() {
	return gaiaEngine.getInfrastructuresLayer();
    }

    public static void setSelectedStreetID(int _selectedStreetID) {
	gaiaEngine.setSelectedStreetID(_selectedStreetID);
    }

    public static int getSelectedStreetID() {
	return gaiaEngine.getSelectedStreetID();
    }

}
