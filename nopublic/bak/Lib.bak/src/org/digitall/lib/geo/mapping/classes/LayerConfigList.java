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
