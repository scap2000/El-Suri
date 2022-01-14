package org.digitall.apps.sueldos.classes;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class ContentConceptArticulos {
    
    private Vector conceptos = new Vector();
    private int tipoConcepto = 4;

    public ContentConceptArticulos() {
	conceptos.removeAllElements();
    }
    
    public void addConcepto(Object _concepto){
	conceptos.add(_concepto);
    }
    
    public boolean equals(ContentConceptArticulos _contentConcept){
	boolean hayDistinto = false;
	int i = 0;
	Vector conceptosCompare = _contentConcept.getConceptos();
	if(conceptos.size() != conceptosCompare.size()){
	    hayDistinto = true;
	}else{
	    while((i < conceptos.size())&&(!hayDistinto)){
	        ConceptosArticulos conceptoArticulo = (ConceptosArticulos)(conceptos.elementAt(i));
	        ConceptosArticulos conceptoArticuloA = (ConceptosArticulos)(conceptosCompare.elementAt(i));
		/* 2009-09-11 (santiago) Mensaje para el codificador
		 * Si las líneas comentadas no se usarán en el futuro, hay que borrarlas
		 * */
		if(!equals(conceptoArticulo,conceptoArticuloA)){
		    hayDistinto = true;    
		}
		i++;
	    }
	}
	return(!hayDistinto);
    }
    
    private boolean equals(ConceptosArticulos _conceptA, ConceptosArticulos _conceptB){
	boolean testId = (_conceptA.getIdconceptoreferencia() == _conceptB.getIdconceptoreferencia());
	boolean testName = (_conceptA.getName().equals(_conceptB.getName()));
	boolean testValue = (_conceptA.getValue() == _conceptB.getValue());
	boolean testPorcentaje = (_conceptA.getPercentage() == _conceptB.getPercentage());
	boolean testDebeHaber  = (_conceptA.getDebehaber() == _conceptB.getDebehaber());
	boolean testEstado = (_conceptA.getEstado().equals(_conceptB.getEstado()));
	boolean testOrden = (_conceptA.getOrden() == _conceptB.getOrden());
	return ((testId) && (testName) && (testValue) && (testPorcentaje) && (testDebeHaber) && (testEstado) && (testOrden));
    }
    
    public boolean save(){
	boolean resultado = true;
	for(int i = 0; i < conceptos.size(); i++){
	    ConceptosArticulos conceptoArticulo = (ConceptosArticulos)(conceptos.elementAt(i));
	    if(conceptoArticulo.isSetgeneral()){
		resultado = (conceptoArticulo.saveData() != -1);
		saveValuesToLegajo(conceptoArticulo.getIdconceptoreferencia(), conceptoArticulo.getValue());
	    }
	    }
	    return(resultado);
	    }
	    
    private boolean saveValuesToLegajo(int _idConceptoReference, double _value){
	String params = "" + tipoConcepto + "," + _idConceptoReference;
	int idConcepto = LibSQL.getInt("sueldos.getIdConceptFromReference",params);
	params = ""+ idConcepto + "," + _value;
	boolean resultado =  LibSQL.getBoolean("sueldos.upDateValuesConcept",params);
	return (resultado);
    }
    
    public Vector getConceptos(){
	return(conceptos);
    }
    
    public void clear(){
	conceptos.removeAllElements();
    }
}
