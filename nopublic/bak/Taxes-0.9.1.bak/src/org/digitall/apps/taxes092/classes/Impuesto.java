package org.digitall.apps.taxes092.classes;

import org.digitall.apps.taxes.classes.TipoImpuesto;

public class Impuesto {

    private TipoImpuesto tipoImpuesto = new TipoImpuesto();
    public static int TIPO_IMPUESTO_TGS = 1;
    public static int TIPO_IMPUESTO_INMOB = 2;
    public static int TIPO_IMPUESTO_AUTOMOTOR = 3;
    private int tipoDeImpuesto = -1;

    public Impuesto() {
    
    }

    public void setTipoDeImpuesto(int _tipoImpuesto) {
	tipoDeImpuesto = _tipoImpuesto;
	tipoImpuesto.setIdTipoImpuesto(tipoDeImpuesto);
	tipoImpuesto.retrieveData();
    }

    public int getTipoDeImpuesto() {
	return tipoDeImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
	this.tipoImpuesto = tipoImpuesto;
    }

    public TipoImpuesto getTipoImpuesto() {
	return tipoImpuesto;
    }
}
