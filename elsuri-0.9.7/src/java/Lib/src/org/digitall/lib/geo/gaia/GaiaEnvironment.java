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
 * GaiaEnvironment.java
 *
 * */
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
