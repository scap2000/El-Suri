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
 * GaiaEngine.java
 *
 * */
package org.digitall.lib.geo.gaia;

import java.awt.Color;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Vector;

import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.GeometrySetConfig;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.geo.mapping.classes.LayerProfile;


public class GaiaEngine implements Serializable{
    
    private static final long serialVersionUID = 10000095L;

    private Vector<LayerProfile> layerProfileList = new Vector<LayerProfile>();
    private Vector<GeometrySetConfig> geometrySetConfigList = new Vector<GeometrySetConfig>();
    private Vector<String> layerGroupsNamesList = new Vector<String>();
        
    private String cadastralLayer = "parcelas_vinculadas";
    private String scheme = "gis";
    private String streetsLayer = "streets";
    private String selectedStreetName = "";
    private String addressLayer = "nros_puerta";
    private String infrastructuresLayer  = "infrastructures";
    private int selectedStreetID = -1;
	
    public GaiaEngine() {
        super();
    }
    
    public boolean initialize() {
	boolean returns = false;
	try {
            if (geometrySetConfigList.size() > 0) {
                //1. Cargar listado de GeometrySet
		GaiaEnvironment.geometrySets = getGeometrySetList();
                //2. Cargar listado de LayerGroup
		GaiaEnvironment.layerGroups = getLayerGroupList();
                //3. Cargar listado de Layer definidos por el usuario
                loadLayerProfileList();
                //4. Cargar listado de GeometrySet a la clase BasicDrawEngine
                //5. Cargar listado de LayerGroup a la clase BasicDrawEngine
                returns = true;    
            }
	} catch (Exception e) {
	    // TODO: Add catch code
	    e.printStackTrace();
	}
	return returns;
    }

    public void setLayerProfileList(Vector<LayerProfile> _layerProfileList) {
        layerProfileList = _layerProfileList;
    }

    public Vector<LayerProfile> getLayerProfileList() {
        return layerProfileList;
    }
    
    public Layer addLayerProfile(LayerProfile _layerProfile) {
        layerProfileList.add(_layerProfile);
        return loadLayerProfile(_layerProfile);
    }
    
    public void setLayerProfile(LayerProfile _layerProfile, int _posicion) {
        layerProfileList.setElementAt(_layerProfile,_posicion);
    }
    
    public boolean removeLayerProfile(Object _layerProfile) {
        if (layerProfileList.remove(_layerProfile)) {
            //guardo los cambios
            saveProfile();
            return true;
        } else {
            return false;
        }
    }
    
    public void addGeometrySetConfig(GeometrySetConfig _geometrySetConfig) {
        geometrySetConfigList.add(_geometrySetConfig);
    }
    
    public void setGeometrySetConfig(GeometrySetConfig _geometrySetConfig, int _posicion) {
        geometrySetConfigList.setElementAt(_geometrySetConfig,_posicion);
    }
    
    public boolean removeGeometrySetConfig(Object _geometrySetConfig) {
        if (geometrySetConfigList.remove(_geometrySetConfig)) {
            //guardo los cambios
            saveProfile();
            return true;
        } else {
            return false;
        }
    }
    
    public void saveProfile() {
        final Object _recordable = this;
        boolean _enabled = true;
        if (!(GaiaEnvironment.tempDir.exists() && GaiaEnvironment.tempDir.isDirectory())) {
            if (!GaiaEnvironment.tempDir.mkdir()) {
                System.err.println("Error al crear el directorio temporal");
                _enabled = false;
            } else {
                //System.out.println("El directorio temporal se encuentra en " + _tmpDir);
            }
        } else {
            //System.out.println("El directorio temporal se encuentra en " + _tmpDir);
        }
        if (_enabled) {
            try {
                // Write to disk with FileOutputStream
                FileOutputStream outFile = new FileOutputStream(GaiaEnvironment.getCacheFileName(GaiaEnvironment.profileFileName)+ ".gep");
                // Write object with ObjectOutputStream
                ObjectOutputStream outObject = new ObjectOutputStream(outFile);
                // Write object out to disk
                outObject.writeObject(_recordable);
                outObject.close();
                System.out.println(" Gaia profile saved");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Layer loadLayerProfile(LayerProfile _newLayerProfile) {
        int geometrySetIndex = getGeometrySetIndex(_newLayerProfile.getGeometrySetName());
        if ( geometrySetIndex != -1 ) {
            //creación de layer
	    Layer _layer = new Layer(_newLayerProfile.getLayerName(), GaiaEnvironment.geometrySets.elementAt(geometrySetIndex));
	    if (!_layer.haveConfig()) { //le seteo una configuración por defecto...
		_layer.setColor(new Color(204, 204, 204));
	    }
            int layerGroupIndex = getLayerGroupNameIndex(_newLayerProfile.getLayerGroupName());
            if ( layerGroupIndex != -1 ) {
                //agregar nuevo layer al grupo de layer
		GaiaEnvironment.layerGroups.elementAt(layerGroupIndex).add(_layer);
            }
	    return _layer;
	} else {
	    return null;
	}
    }
    
    public void loadLayerProfileList() {
        for(int j = 0 ; j < layerProfileList.size();j++){
            loadLayerProfile(layerProfileList.elementAt(j));
        }
    }
    
    //método que devuelve la posicion del GeometrySet con nombre _name
    private int getGeometrySetIndex(String _name) {
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < GaiaEnvironment.geometrySets.size() )) {
            if (GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getName().equals(_name)) {
                encontrado = true;        
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    public int getGeometrySetIndex(String _scheme, String _table) {
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < GaiaEnvironment.geometrySets.size() )) {
            if (GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlScheme().equals(_scheme)) {
                if (GaiaEnvironment.geometrySets.elementAt(i).getGeometrySetConfig().getSqlTable().equals(_table)) {
                    encontrado = true;
                }
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    //método que devuelve la posicion del LayerGroup con nombre _name
    private int getLayerGroupNameIndex(String _name) {
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < GaiaEnvironment.layerGroups.size() )) {
            if (GaiaEnvironment.layerGroups.elementAt(i).getName().equals(_name)) {
                encontrado = true;        
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    public void setGeometrySetConfigList(Vector<GeometrySetConfig> geometrySetConfigList) {
        this.geometrySetConfigList = geometrySetConfigList;
    }

    public Vector<GeometrySetConfig> getGeometrySetConfigList() {
        return geometrySetConfigList;
    }

    public Vector<GeometrySet> getGeometrySetList() {
        Vector<GeometrySet> geometrySetList = new Vector<GeometrySet>();
        for (int i = 0; i < geometrySetConfigList.size();i++) {
	    GeometrySet geometrySet = new GeometrySet(geometrySetConfigList.elementAt(i));
            geometrySetList.add(geometrySet);
        }
        return geometrySetList;
    }
    
    public void setLayerGroupList(Vector<LayerGroup> _layerGroupList) {
        //this.layerGroupList = layerGroupList;
        for(int i = 0; i < _layerGroupList.size();i++){
            layerGroupsNamesList.add(_layerGroupList.elementAt(i).getName());
        }
        saveProfile();
    }

    public Vector<LayerGroup> getLayerGroupList() {
        Vector<LayerGroup> _layerGroupList = new Vector<LayerGroup>();
        for (int i = 0; i < layerGroupsNamesList.size();i++) {
	    LayerGroup _group = new LayerGroup(getLayerGroupNameList().elementAt(i));
            _layerGroupList.add(_group);
        }
        return _layerGroupList;
    }

    public void setLayerGroupNameList(Vector<String> _layerGroupNameList) {
        layerGroupsNamesList = _layerGroupNameList;
    }

    public Vector<String> getLayerGroupNameList() {
        return layerGroupsNamesList;
    }
    
    public void addLayerGroup(String _layerGroupName){
        getLayerGroupNameList().add(_layerGroupName);
        GaiaEnvironment.layerGroups.add(new LayerGroup(_layerGroupName));
    }
    
    public GeometrySet loadNewGeometrySet(String _scheme, String _table, String _column, String _keyColumn, boolean _propietary){
        GeometrySet newGeometrySet = new GeometrySet(_scheme, _table, _column, "1=1", _keyColumn, _propietary);
        GaiaEnvironment.geometrySets.add(newGeometrySet);
        return newGeometrySet;
    }
    
    public GeometrySet loadNewGeometrySet(String _scheme, String _table, String _column, String _keyColumn, boolean _propietary, String _serverURL, String _database, String _user, String _pass){
        GeometrySet newGeometrySet = new GeometrySet(_scheme, _table, _column, "1=1", _keyColumn, _propietary);
        newGeometrySet.getGeometrySetConfig().setConnectionParams(_serverURL, _database, _user, _pass);
        GaiaEnvironment.geometrySets.add(newGeometrySet);
        return newGeometrySet;
    }
    
    //método que mueve un Layer de un LayerGroup a otro indicado
    public void moveLayerToGroup(String _layerGroupFrom, String _layerName, String _layerGroupTo) {
        if (!_layerGroupFrom.equals(_layerGroupTo)) {
            int layerProfileIndex = getLayerProfileIndex(_layerName);
            if (layerProfileIndex != -1) {
                layerProfileList.elementAt(layerProfileIndex).setLayerGroupName(_layerGroupTo);
                //obtener índice del layerGroup del cual se debe remover el layer
                int _layerGroupIndex = getLayerGroupIndex(_layerGroupFrom);
                //obtener índice del layer que se encuentra en el layerGroup encontrado
                int _layerIndex = getLayerIndex(_layerName,_layerGroupIndex);
                //resguardar layer a mover
                Layer _layer = GaiaEnvironment.layerGroups.elementAt(_layerGroupIndex).elementAt(_layerIndex);
                //remover layer del grupo original
                GaiaEnvironment.layerGroups.elementAt(_layerGroupIndex).remove(_layerIndex);
                _layerGroupIndex = getLayerGroupIndex(_layerGroupTo);
                _layerIndex = getLayerIndex(_layerName,_layerGroupIndex);
                GaiaEnvironment.layerGroups.elementAt(_layerGroupIndex).add(_layer);
            }
            saveProfile();
        }
    }
    
    //Método que retorna la ubicación del LayerGroup 
    public int getLayerGroupIndex(String _layerGroupName) {
        Vector<LayerGroup> _vectorLayerGroup = GaiaEnvironment.layerGroups;
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < _vectorLayerGroup.size() )) {
            if (_vectorLayerGroup.elementAt(i).getName().equals(_layerGroupName)) {
                encontrado = true;
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    //Método que retorna la ubicación del Layer que se encuentra en un layerGroup determinado
    public int getLayerIndex(String _layerName, int _layerGroupIndex) {
        LayerGroup _layers = GaiaEnvironment.layerGroups.elementAt(_layerGroupIndex);
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < _layers.size() )) {
            if (_layers.elementAt(i).getName().equals(_layerName)) {
                encontrado = true;
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    private int getLayerProfileIndex(String _layerName) {
        Vector<LayerProfile> _vectorLayerProfile = getLayerProfileList();
        int i = 0;
        boolean encontrado = false;
        while (( !encontrado ) && ( i < _vectorLayerProfile.size() )) {
            if (_vectorLayerProfile.elementAt(i).getLayerName().equals(_layerName)) {
                encontrado = true;
            }
            i++;
        }
        if ( encontrado ) {
            i--;
        } else {
            i = -1;
        }
        return i;
    }
    
    public void renameLayerGroup(String _layerGroupOldName, String _layerGroupNewName) {
        int _layerGroupIndex = getLayerGroupIndex(_layerGroupOldName);
        if (_layerGroupIndex != -1) {
            layerGroupsNamesList.setElementAt(_layerGroupNewName, _layerGroupIndex);
            GaiaEnvironment.layerGroups.elementAt(_layerGroupIndex).setName(_layerGroupNewName);
            for (int i = 0; i < layerProfileList.size(); i++) {
                LayerProfile _layerProfile = layerProfileList.elementAt(i);
                if (_layerProfile.getLayerGroupName().equals( _layerGroupOldName)) {
                    _layerProfile.setLayerGroupName(_layerGroupNewName);
                }
            }
        }
    }

    public void setCadastralLayer(String _cadastralLayer) {
	cadastralLayer = _cadastralLayer;
    }

    public String getCadastralLayer() {
	return cadastralLayer;
    }

    public void setScheme(String _scheme) {
	scheme = _scheme;
    }

    public String getScheme() {
	return scheme;
    }

    public void setStreetsLayer(String _streetsLayer) {
	streetsLayer = _streetsLayer;
    }

    public String getStreetsLayer() {
	return streetsLayer;
    }

    public void setSelectedStreetName(String _selectedStreetName) {
	selectedStreetName = _selectedStreetName;
    }

    public String getSelectedStreetName() {
	return selectedStreetName;
    }

    public void setAddressLayer(String _addressLayer) {
	addressLayer = _addressLayer;
    }

    public String getAddressLayer() {
	return addressLayer;
    }

    public void setInfrastructuresLayer(String _infrastructuresLayer) {
	infrastructuresLayer = _infrastructuresLayer;
    }

    public String getInfrastructuresLayer() {
	return infrastructuresLayer;
    }

    public void setSelectedStreetID(int _selectedStreetID) {
	selectedStreetID = _selectedStreetID;
    }

    public int getSelectedStreetID() {
	return selectedStreetID;
    }
}
