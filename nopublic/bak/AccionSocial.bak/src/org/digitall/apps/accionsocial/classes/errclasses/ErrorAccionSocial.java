package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.data.Format;

public abstract class ErrorAccionSocial {

    private static int error = 0;
    /* ENCABEZADO */
    public static final int CANTIDAD_ASIGNADA_MAYOR_MAXIMO = 1;
    public static final int CANTIDAD_ASIGNADA_MENOR_MINIMO = 2;
    public static final int CANTIDAD_TOTAL_MAYOR_PERMITIDO = 3;
    public static final int CANTIDAD_FECHA_ASIGNACION_INVALIDA = 4;
    public static final int CANTIDAD_MAYOR_STOCK = 5;
    public static final int MAXIMO_PRESTACIONES_ASIGNADAS = 6;
    public static final int CONCEPTO = 7;
    public static final int TUTOR_SELECCIONADO = 8;
    public static final int PRESTACION_SELECCIONADA = 9;
    public static final int DESPACHANTE_SELECCIONADO = 10;
    public static final int CENTRO_SALUD_SELECCIONADO = 11;
    public static final int SECTOR_SELECCIONADO = 12;
    public static final int FECHA_ENTREGA = 13;
    public static final int BENEFICIARIO_SELECCIONADO = 14;
    public static final int CANTIDAD_TOTAL_MAYOR_STOCK = 15;
    public static final int PERIODO_ENTREGA = 16;
    public static final int FECHA_ALTA = 17;
    private static double cantidadMinima = 0;
    private static double cantidadMaxima = 0;
    private static double cantidadAEntregar = 0;
    private static double cantidadPosibleAAsignar = 0;
    
    public ErrorAccionSocial() {
    }

    public static void setError(int _error) {
	error = _error;
    }

    public static int getError() {
	return error;
    }
    
    public static void showMessage() {
	switch (error)  {
	    case CANTIDAD_ASIGNADA_MAYOR_MAXIMO: 
                Advisor.messageBox("No puede asignarse una cantidad mayor a " + Proced.DobleDec(String.valueOf(getCantidadMaxima())) + " \n de la prestación seleccionada." ,"Mensaje");
		break;
	    case CANTIDAD_ASIGNADA_MENOR_MINIMO: 
                Advisor.messageBox("No puede asignarse una cantidad menor a " + Proced.DobleDec(String.valueOf(getCantidadMinima())) + " \n de la prestación seleccionada." ,"Mensaje");
	        break;
	    case CANTIDAD_TOTAL_MAYOR_PERMITIDO : 
	        Advisor.messageBox("No puede asignarse una cantidad menor a " + Proced.DobleDec(String.valueOf(getCantidadMinima())) + " \n de la prestación seleccionada." ,"Mensaje");
	        break;
	    case CANTIDAD_FECHA_ASIGNACION_INVALIDA: 
                    Advisor.messageBox("La Fecha de Entrega debe ser menor o igual a la fecha actual","Mensaje");
		break;
	    case CANTIDAD_MAYOR_STOCK: 
                    Advisor.messageBox("La cantidad ingresada no debe superar al stock disponible","Mensaje");
	        break;
	    case MAXIMO_PRESTACIONES_ASIGNADAS: 
                    if (getCantidadPosibleAAsignar() == 0) {
                        Advisor.messageBox("No se pueden entregar mas recursos \n de la prestación seleccionada.","Mensaje");
                    } else {
                        Advisor.messageBox("Sólo le quedan " + getCantidadPosibleAAsignar() + " cantidades \n posible a asignar de la prestación seleccionada.","Mensaje");
                    }
	        break;
	    case CONCEPTO: 
                    Advisor.messageBox("El campo \"Concepto\" no debe estar vacío","Mensaje");
	        break;
	    case TUTOR_SELECCIONADO: 
	            Advisor.messageBox("Debe seleccionar un tutor","Mensaje");
	        break;
	    case PRESTACION_SELECCIONADA: 
	            Advisor.messageBox("Debe seleccionar una prestación","Mensaje");
	        break;
	    case DESPACHANTE_SELECCIONADO: 
	            Advisor.messageBox("Debe seleccionar un Despachante","Mensaje");
	        break;
	    case CENTRO_SALUD_SELECCIONADO: 
	            Advisor.messageBox("Debe seleccionar un Centro de Salud","Mensaje");
	        break;
	    case SECTOR_SELECCIONADO: 
	            Advisor.messageBox("Debe seleccionar un Sector","Mensaje");
	        break;
	    case FECHA_ENTREGA: 
	            Advisor.messageBox("El campo \"Fecha de Entrega\" no debe estar vacío","Mensaje");
	        break;
	    case BENEFICIARIO_SELECCIONADO: 
	            Advisor.messageBox("Debe seleccionar al menos un beneficiario","Mensaje");
	        break;
	    case CANTIDAD_TOTAL_MAYOR_STOCK: 
	            Advisor.messageBox("La cantidad acumulada de la prestación a entregar es mayor al stock disponible","Mensaje");
	        break;
	    case PERIODO_ENTREGA: 
                    Advisor.messageBox("El período de Entrega debe ser menor o igual al período actual","Mensaje");
	        break;
	    case FECHA_ALTA: 
	            Advisor.messageBox("El campo \"Fecha de Alta\" no debe estar vacío","Mensaje");
	        break;
	}
    }

    public static void setCantidadMinima(double cantidadMinima) {
        ErrorAccionSocial.cantidadMinima = cantidadMinima;
    }
    
    public static void setCantidadAEntregar(double cantidadAEntregar) {
        ErrorAccionSocial.cantidadAEntregar = cantidadAEntregar;
    }

    public static double getCantidadMinima() {
        return cantidadMinima;
    }

    public static void setCantidadMaxima(double cantidadMaxima) {
        ErrorAccionSocial.cantidadMaxima = cantidadMaxima;
    }

    public static double getCantidadMaxima() {
        return cantidadMaxima;
    }

    public static double getCantidadAEntregar() {
        return cantidadAEntregar;
    }

    public static void setCantidadPosibleAAsignar(double cantidadPosibleAAsignar) {
        ErrorAccionSocial.cantidadPosibleAAsignar = cantidadPosibleAAsignar;
    }

    public static double getCantidadPosibleAAsignar() {
        return cantidadPosibleAAsignar;
    }
}
