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
 * LayerConfigList.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Vector;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;

public class LayerConfigList extends Vector<LayerConfig> implements Serializable {
    
    private static final long serialVersionUID = 10000095L;
    
    private String fileName = "";
    private String name = "";
    
    public LayerConfigList() {
        super();
    }
    
    public LayerConfigList(String _name) {
        super();
        name = _name;
    }
    
    public synchronized void importLayerConfigList(String _fileName) {
        File _cacheFile = new File(_fileName);
        if (_cacheFile.exists()) {
            try {
                // Read from disk using FileInputStream
                FileInputStream inFile = new FileInputStream(_cacheFile);

                // Read object using ObjectInputStream
                ObjectInputStream inObject = new ObjectInputStream(inFile);

                // Read an object
                Object cachedObject = inObject.readObject();

                if (cachedObject instanceof LayerConfigList) {
                    LayerConfigList _layerConfigList = (LayerConfigList)cachedObject;
		    if (_layerConfigList.size() > 1) {
			if (_layerConfigList.elementAt(1).getName().equals(elementAt(0).getName())) {
			    for(int i = 1; i< _layerConfigList.size(); i++){
				int j = 1;
				boolean encontrada = false;
				while((!encontrada)&&(j < this.size())){
				    if(_layerConfigList.elementAt(i).getNombreConfiguracion().equals(this.elementAt(j).getNombreConfiguracion())){
					if (Advisor.question("Importar configuración", "La configuración \"" + _layerConfigList.elementAt(i).getNombreConfiguracion() + "\" ya existe.\n¿Desea reemplazar la configuración existente?")) {
					    this.setElementAt(_layerConfigList.elementAt(i), j);
					}
					encontrada = true;
				    }
				    j++;
				}
				if(!encontrada){
				    this.add(_layerConfigList.elementAt(i));
				}
			    }
			} else {
		            Advisor.messageBox("Se ha intentado importar un archivo del layer <u>" + _layerConfigList.elementAt(1).getName() + "</u> al layer <u>" + elementAt(0).getName() + "</u>", "Importar configuración");
		        }
		    } else {
			Advisor.messageBox("El archivo a importar está vacío", "Importar configuración");
		    }
                } else {
		    Advisor.messageBox("No ha seleccionado un archivo de configuraciones válido", "Importar configuración");
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
        }
    }
    
    public void exportLayerConfig(){
        final Object _recordable = this;
        Thread _thread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Write to disk with FileOutputStream
                    FileOutputStream outFile = new FileOutputStream(getFileName()+ ".listconfig");
                    // Write object with ObjectOutputStream
                    ObjectOutputStream outObject = new ObjectOutputStream(outFile);
                    // Write object out to disk
                    outObject.writeObject(_recordable);
                    outObject.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        _thread.setPriority(Thread.MIN_PRIORITY);
        _thread.start();
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public String getFileName() {
        return fileName;
    }
    
    public void saveConfigData() {
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
                FileOutputStream outFile = new FileOutputStream(GaiaEnvironment.getCacheFileName(getName())+ ".lcl");
                // Write object with ObjectOutputStream
                ObjectOutputStream outObject = new ObjectOutputStream(outFile);
                // Write object out to disk
                outObject.writeObject(_recordable);
                outObject.close();
		System.out.println(elementAt(0).getName() + " configuration saved");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setLayerConfig(LayerConfig _layerConfig, int _posicion){
        setElementAt(_layerConfig, _posicion);
    }

    @Override
    public boolean remove(Object _layerConfig) {
	if (super.remove(_layerConfig)) {
	    if(size() == 1){//solo queda la configuración base
	        Advisor.messageBox("Se va a generar la configuración por defecto", "Mensaje");
	        LayerConfig layerConfig = elementAt(0).getCopy();
	        //agrego nuevo layerConfig a la lista del layer
	        add(layerConfig);
	        //agrego la lista al nuevo layerConfig
	        layerConfig.setLayerConfigList(this);
	        //asigno el ID al layerConfig
	        layerConfig.setIdLayerConfig(1);
	        //asigno nombre de la nueva configuracion
	        layerConfig.setNombreConfiguracion("Por defecto");
	        layerConfig.setListadoQueryFilter((HashMap)layerConfig.getListadoQueryFilter().clone());
	        layerConfig.setListadoQueryLabel((HashMap)layerConfig.getListadoQueryLabel().clone());
	    }
	    //guardo los cambios
	    saveConfigData();
	    return true;
	} else {
	    return false;
	}
    }
}
