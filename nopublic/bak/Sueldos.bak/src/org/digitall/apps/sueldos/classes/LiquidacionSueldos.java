package org.digitall.apps.sueldos.classes;

import java.util.Vector;

import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.lib.sql.LibSQL;

public class LiquidacionSueldos {

    /* 2009-09-18 (santiago) Mensaje para el codificador
     * Un poco tosca la clase, 3 métodos y no devuelve muchos resultados
     * ¿Cuál es el objetivo de la misma?
     * */

    public LiquidacionSueldos() {
    
    }
    
    public int asignarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
	int resultado = -1;
	Vector idsHaberes = LibSQL.getVector("sueldos.getLisIdHaberes","'"+vectorToParams(_haberes)+"'");
	Vector idsDescuentos = LibSQL.getVector("sueldos.getLisIdDescuentos","'"+vectorToParams(_descuentos)+"'");
	String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(idsHaberes)+"','"+vectorToParams(idsDescuentos)+"'";
	resultado = LibSQL.getInt("sueldos.asignarconceptoslegajo",params);
	return resultado;
    }
    
    public int quitarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
        int resultado = -1;
        /*
        Vector idsHaberes = LibSQL.getVector("sueldos.getLisIdHaberes","'"+vectorToParams(_haberes)+"'");
        Vector idsDescuentos = LibSQL.getVector("sueldos.getLisIdDescuentos","'"+vectorToParams(_descuentos)+"'");
        */
        String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(_haberes)+"','"+vectorToParams(_descuentos)+"'";
        resultado = LibSQL.getInt("sueldos.quitarConceptosLegajo",params);
        return resultado;
    }
    
    public int agregarConceptosAPersonas(Vector _idsPersons,Vector _haberes, Vector _descuentos){
        int resultado = -1;
        String params = "'"+vectorToParams(_idsPersons)+"','"+vectorToParams(_haberes)+"','"+vectorToParams(_descuentos)+"'";
        resultado = LibSQL.getInt("sueldos.agregarconceptoslegajo",params);
        return resultado;
    }
    
    /* 2009-09-18 (santiago) Mensaje para el codificador
     * Al final del ciclo, queda siempre una coma suelta
     * */
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	/* 2009-09-18 (santiago) Mensaje para el codificador
	 * return es una palabra reservada y no necesita parámetros,
	 * por lo tanto el resultado no debe estar (o no es necesario que esté) entre paréntesis
	 * */
	return resultado;
    }
    
    public boolean grabarLiquidacionMensualForLegajo(Legajo _legajo, String _fecha){
	boolean resultado = false;
	int idLegajo = _legajo.getidLegajo();
	int idLiquidacionParcial = LibSQL.getInt("sueldos.getIdLiquidacionParcial",idLegajo + ",'" +_fecha+"'");
	String params = ""+idLiquidacionParcial + ",'"+_fecha+"'";
	resultado = LibSQL.getBoolean("sueldos.pasarALiquidacionMensual",params);
	return resultado;
    }
    
    public boolean puedeHacerseLiquidacionMensual(String _fecha){
	if (LibSQL.getBoolean("sueldos.sepuedehacerliquidacion","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public boolean estanTodasLiquidacionesParciales(String _fecha){
	if (LibSQL.getBoolean("sueldos.estanrealizadastodaslasliquidaciones","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public boolean grabarLiquidacionesFinales(String _fecha){
	if (LibSQL.getBoolean("sueldos.grabarLiquidacionFinal","'"+_fecha+"'")) {
	    return true;
	} else {
	    return false;
	}
    }
}
