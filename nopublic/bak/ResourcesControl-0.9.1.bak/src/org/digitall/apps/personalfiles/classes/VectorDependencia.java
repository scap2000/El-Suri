package org.digitall.apps.personalfiles.classes;

import java.util.Vector;

public class VectorDependencia extends Vector {
    private Dependencia dependencia = null;    
    
    public void setDependencia(Dependencia _dependencia){	    
	dependencia = _dependencia;	    	   
    }
    
    public Dependencia getDependencia(){
	return dependencia;
    }	
}
