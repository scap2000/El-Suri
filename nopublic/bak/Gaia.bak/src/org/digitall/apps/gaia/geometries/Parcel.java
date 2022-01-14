package org.digitall.apps.gaia.geometries;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.lib.geom.Polygon2D;

public class Parcel extends Polygon2D.Double {

    /* Una parcela de la ciudad, contiene los métodos para
     * interactuar con la base de datos, modificar sus datos, 
     * vincularla con registros, etc. */
    
    private Vector<Cadastral> catastros;

    public Parcel() {

    }
    
    public void retrieveData() {

    }

    public void linkTo(int _idparcela) {
	
    }
    
    public void unLink() {
	
    }
    
    public boolean saveData() {
	boolean returns = false;
	return returns;
    }

}
