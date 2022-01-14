package org.digitall.common.cashflow.classes;


import javax.swing.ImageIcon;

import org.digitall.lib.icons.IconTypes;

public abstract class EntityTypes {

    /** Write here the available classes, entities, clasification
     * and all other stuff to define a new type of entity
     * */
    //INVERSOR
    public static final int INVESTOR = 1;
    //INVERSION
    public static final int INVESTMENT = 2;
    //PARTIDA PRESUPUESTARIA
    public static final int BUDGET = 3;
    //DINERO
    public static final int MONEY = 4;
    //CENTRO DE COSTOS
    public static final int COSTS_CENTRE = 5;
    //PROVEEDORES
    public static final int GOODS_PROVIDER = 6;
    public static final int SERVICES_PROVIDER = 7;
    public static final int PROVIDER = 67;
    //SERVICIO
    public static final int SERVICE = 8;
    //HORA DE SERVICIO
    public static final int SERVICE_HOUR = 9;
    //PAQUETE DE SERVICIOS
    //public static final int SERVICE_PACKAGE = 10;
    //UTILIDAD O SATISFACCION
    public static final int UTILITY_OR_SATISFACTION = 11;
    //BIENES
    public static final int CONSUMPTION_GOOD = 12;
    public static final int USE_GOOD = 13;
    public static final int RESOURCE = 1213;
    //MARCA O DISTINCIï¿½N
    public static final int BRAND = 14;
    //PERSONAS
    public static final int PERSON = 15;
    //HORA HOMBRE
    public static final int MAN_HOUR = 16;
    //TAREA
    public static final int TASK = 17;

    public static ImageIcon getImageIcon(int _entityType) {
	ImageIcon _imageIcon = IconTypes.close_16x16;
	switch (_entityType) {
	    case RESOURCE :
		_imageIcon = IconTypes.imgLogisticOn;
		break;
	    case PERSON :
		_imageIcon = IconTypes.imgChWYOn;
		break;
	    case COSTS_CENTRE :
		_imageIcon = IconTypes.imgMapperOn;
		break;
	}
	return _imageIcon;
    }

}
