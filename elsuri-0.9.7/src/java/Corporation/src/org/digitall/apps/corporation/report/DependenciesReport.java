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
 * DependenciesReport.java
 *
 * */
package org.digitall.apps.corporation.report;

import java.awt.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.digitall.apps.corporation.report.xml.XMLWorkBook;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class DependenciesReport {

    private Vector personalSubDeps = new Vector();
    int iddep = 0;
    private int tablas = 0;
    private int nroHojas = 1;
    private Vector WBQuerys[];
    private XMLWorkBook reporte;
    private String personalTotal = "";

    public DependenciesReport(int _iddep) {
	iddep = _iddep;
	informeDependenciaWorkBook(iddep);
    }

    public DependenciesReport(int _iddep, String _personalTotal) {
	iddep = _iddep;
	personalTotal = _personalTotal;
	informeDependenciaWorkBook(iddep);
    }

    private void informeDependenciaWorkBook(int _iddep) {
	int result = JOptionPane.showConfirmDialog((Component)null, "¿Desea un Reporte?", "Reporte", JOptionPane.YES_NO_OPTION);
	if (result == JOptionPane.YES_OPTION) {
	    tablas = 0;
	    reporte = new XMLWorkBook();
	    personalSubDeps.removeAllElements();
	    nroHojas = 1;
	    int estaHoja = 0;
	    WBQuerys = new Vector[nroHojas];
	    Vector encabezadoInforme = new Vector();
	    Vector tipoDatos = new Vector();
	    encabezadoInforme.add("Apellido y Nombre");
	    tipoDatos.add("String");
	    encabezadoInforme.add("DNI");
	    tipoDatos.add("String");
	    encabezadoInforme.add("Legajo");
	    tipoDatos.add("Number");
	    encabezadoInforme.add("Cargo");
	    tipoDatos.add("String");
	    encabezadoInforme.add("Depende de");
	    tipoDatos.add("String");
	    encabezadoInforme.add("Nivel");
	    tipoDatos.add("String");
	    encabezadoInforme.add("Dependencia");
	    tipoDatos.add("String");
	    WBQuerys[estaHoja] = new Vector();
	    reporte.setNroHojas(nroHojas);
	    ResultSet dependencyData =  LibSQL.exFunction("organigrama.xmlGetDependency","" + _iddep);
	    try {
		if (dependencyData.next()) {
		    reporte.setNombreLibro(dependencyData.getString("nombre"));
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	    reporte.setTituloHoja(estaHoja, "Listado de Dependencias");
	    reporte.setNombreHoja(estaHoja, "Listado de Dependencias");
	    reporte.setCabecera(estaHoja, encabezadoInforme);
	    reporte.setTipoDatos(estaHoja, tipoDatos);
	    ResultSet res = LibSQL.exFunction("organigrama.xmlGetDependency", "" + iddep);
	    try {
		if (res.next()) {
		    if (LibSQL.getInt("organigrama.xmlQtyPeopleOnDependency", "" + res.getString("iddep") + ",'" + res.getString("niveljerarquico") + "','" + res.getString("nombre") + "'," + _iddep) != 0) {
			WBQuerys[0].add("organigrama.xmlGetPeopleOnDependency (" + res.getString("iddep") + ",'" + res.getString("niveljerarquico") + "','" + res.getString("nombre") + "'," + _iddep + ")");
		    } else {
			WBQuerys[0].add("organigrama.xmlGetVacancyOnDependency(" + res.getString("iddep") + ",'" + res.getString("niveljerarquico") + "','" + res.getString("nombre") + "'," + _iddep + ")");
		    }
		    Vector vectorColores = new Vector();
		    vectorColores.add("Normal8");
		    vectorColores.add("Normal8");
		    vectorColores.add("Normal8");
		    vectorColores.add("Normal8");
		    vectorColores.add("Normal8");
		    String colorN = getColor(res.getInt("nivel"));
		    vectorColores.add(colorN);
		    vectorColores.add(colorN);
		    reporte.setColores(estaHoja, tablas, vectorColores);
		    tablas++;
		    if (LibSQL.getInt("organigrama.xmlQtyDependencies", "" + iddep) != 0) {
			informeSubDependenciasWorkBook(_iddep, reporte, colorN, estaHoja);
		    }
		}
	    } catch (SQLException x) {
		x.printStackTrace();
	    }
	    tablas = 0;
	    //informeIndumentariaDependenciaWorkBook(iddep, reporte);
	    tablas = 0;
	    //informeResumenIndumentariaDependenciaWorkBook(_iddep, reporte);
	    tablas = 0;
	    //informeResumenPersonalDependenciasWorkBook(reporte);
	    for (int i = 0; i < nroHojas; i++) {
		reporte.setQuerys(i, WBQuerys[i]);
		//System.out.println("querys: " + WBQuerys[i]);
	    }
	    reporte.createWorkBook();
	}
    }

    private String getColor(int nivel) {
	if (nivel == 0) {
	    return "s00";
	} else if (nivel == 1) {
	    return "s01";
	} else if (nivel == 2) {
	    return "Rojo";
	} else if (nivel == 3) {
	    return "Naranja";
	} else if (nivel == 4) {
	    return "Amarillo";
	} else if (nivel == 5) {
	    return "Verde";
	} else if (nivel == 6) {
	    return "Azul";
	} else if (nivel == 7) {
	    return "Violeta";
	} else if (nivel == 8) {
	    return "Celeste";
	} else if (nivel == 9) {
	    return "Fucsia";
	} else if (nivel == 10) {
	    return "Fucsia";
	} else if (nivel == 11) {
	    return "Gris";
	} else {
	    return "Normal8";
	}
    }

    private void informeSubDependenciasWorkBook(int _iddep, XMLWorkBook _workBook, String _color, int _estaHoja) {
	ResultSet reg = LibSQL.exFunction("organigrama.xmlGetSubdependencies", "" + _iddep);
	try {
	    while (reg.next()) {
		if (LibSQL.getInt("organigrama.xmlQtyPeopleOnDependency", "" + reg.getString("iddep") + ",'" + reg.getString("nombre") + "','" + reg.getString("niveljerarquico") + "'," + _iddep) > 0) {
		    WBQuerys[_estaHoja].add("organigrama.xmlGetPeopleOnDependency(" + reg.getString("iddep") + ",'" + reg.getString("nombre") + "','" + reg.getString("niveljerarquico") + "'," + reg.getString("iddep") + ")");
		} else {
		    WBQuerys[_estaHoja].add("organigrama.xmlGetVacancyOnDependency(" + reg.getString("iddep") + ",'" + reg.getString("nombre") + "','" + reg.getString("niveljerarquico") + "'," + _iddep +")");
		}
		Vector vectorColores = new Vector();
		vectorColores.add("Normal8");
		vectorColores.add("Normal8");
		vectorColores.add("Normal8");
		vectorColores.add("Normal8");
		vectorColores.add(_color);
		String colorN = getColor(reg.getInt("nivel"));
		vectorColores.add(colorN);
		vectorColores.add(colorN);
		_workBook.setColores(_estaHoja, tablas, vectorColores);
		tablas++;
		
		if (LibSQL.getInt("organigrama.xmlQtyDependencies", "" + reg.getInt("iddep")) != 0) {
		    informeSubDependenciasWorkBook(reg.getInt("iddep"), _workBook, colorN, _estaHoja);
		}
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	}
    }

    private void informeIndumentariaDependenciaWorkBook(int _iddep, XMLWorkBook reporte) {
	int estaHoja = 1;
	Vector encabezadoInforme = new Vector();
	Vector tipoDatos = new Vector();
	encabezadoInforme.add("Apellido y Nombre");
	tipoDatos.add("String");
	encabezadoInforme.add("DNI");
	tipoDatos.add("String");
	encabezadoInforme.add("Legajo");
	tipoDatos.add("Number");
	encabezadoInforme.add("Dependencia");
	tipoDatos.add("String");
	encabezadoInforme.add("Arnés");
	tipoDatos.add("String");
	encabezadoInforme.add("Botas de Goma (Par)");
	tipoDatos.add("String");
	encabezadoInforme.add("Botines (Par)");
	tipoDatos.add("String");
	encabezadoInforme.add("Camisa");
	tipoDatos.add("String");
	encabezadoInforme.add("Campera");
	tipoDatos.add("String");
	encabezadoInforme.add("Casco");
	tipoDatos.add("String");
	encabezadoInforme.add("Corbata");
	tipoDatos.add("String");
	encabezadoInforme.add("Eq. Lluvia");
	tipoDatos.add("String");
	encabezadoInforme.add("Guantes (Par)");
	tipoDatos.add("String");
	encabezadoInforme.add("Martillo");
	tipoDatos.add("String");
	encabezadoInforme.add("Pantalon");
	tipoDatos.add("String");
	encabezadoInforme.add("Zapatos (Par)");
	tipoDatos.add("String");
	WBQuerys[estaHoja] = new Vector();
	reporte.setTituloHoja(estaHoja, "Listado de Indumentaria");
	reporte.setNombreHoja(estaHoja, "Listado de Indumentaria");
	reporte.setCabecera(estaHoja, encabezadoInforme);
	reporte.setTipoDatos(estaHoja, tipoDatos);
	
	ResultSet res = LibSQL.exFunction("organigrama.xmlGetDependency", "" + iddep);
	try {
	    if (res.next()) {
		if (LibSQL.getInt("inventario.xmlQtyPeopleClothing", "" + _iddep) != 0) {
		    WBQuerys[estaHoja].add("inventario.xmlGetPeopleClothing(" + _iddep + ")");
		}
	    }
	    Vector vectorColores = new Vector();
	    for (int i = 0; i < 16; i++) {
		vectorColores.add("Normal8");
	    }
	    reporte.setColores(estaHoja, tablas, vectorColores);
	    tablas++;
	    if (LibSQL.getInt("organigrama.xmlQtyDependencies", "" + _iddep) != 0) {
		informeIndumentariaSubDependenciasWorkBook(_iddep, reporte, estaHoja);
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	}
    }

    private void informeIndumentariaSubDependenciasWorkBook(int _iddep, XMLWorkBook _workBook, int _estaHoja) {
	ResultSet reg = LibSQL.exFunction("organigrama.xmlgetsubdependencies", "" + _iddep);
	try {
	    while (reg.next()) {
		if (LibSQL.getInt("inventario.xmlQtyPeopleClothing", "" + reg.getString("iddep")) != 0) {
		    WBQuerys[_estaHoja].add("inventario.xmlGetPeopleClothing(" + reg.getString("iddep") + ")");
		}
		Vector vectorColores = new Vector();
		for (int i = 0; i < 16; i++) {
		    vectorColores.add("Normal8");
		}
		_workBook.setColores(_estaHoja, tablas, vectorColores);
		tablas++;

		if (LibSQL.getInt("organigrama.xmlQtyDependencies", "" + reg.getInt("iddep")) != 0) {
		    informeIndumentariaSubDependenciasWorkBook(reg.getInt("iddep"), _workBook, _estaHoja);
		}
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	}
    }

    private void informeResumenIndumentariaDependenciaWorkBook(int _iddep, XMLWorkBook reporte) {
	int estaHoja = 2;
	Vector encabezadoInforme = new Vector();
	Vector tipoDatos = new Vector();
	encabezadoInforme.add("Cantidad");
	tipoDatos.add("Number");
	encabezadoInforme.add("Tipo");
	tipoDatos.add("String");
	WBQuerys[estaHoja] = new Vector();
	reporte.setTituloHoja(estaHoja, "Resumen de Indumentaria");
	reporte.setNombreHoja(estaHoja, "Resumen de Indumentaria");
	reporte.setCabecera(estaHoja, encabezadoInforme);
	reporte.setTipoDatos(estaHoja, tipoDatos);
	WBQuerys[estaHoja].add("inventario.xmlGetPeopleClothingByPeople(" + _iddep + "," + personalTotal + ")");
	Vector vectorColores = new Vector();
	vectorColores.add("Normal8");
	vectorColores.add("Normal8");
	reporte.setColores(estaHoja, tablas, vectorColores);
	tablas++;
    }

    private void informeResumenPersonalDependenciasWorkBook(XMLWorkBook reporte) {
	//int estaHoja = 3;
	int estaHoja = 1;
	Vector encabezadoInforme = new Vector();
	Vector tipoDatos = new Vector();
	encabezadoInforme.add("Tipo");
	tipoDatos.add("String");
	encabezadoInforme.add("Dependencia");
	tipoDatos.add("String");
	encabezadoInforme.add("Cantidad");
	tipoDatos.add("Number");
	WBQuerys[estaHoja] = new Vector();
	reporte.setTituloHoja(estaHoja, "Resumen de Personal");
	reporte.setNombreHoja(estaHoja, "Resumen de Personal");
	reporte.setCabecera(estaHoja, encabezadoInforme);
	reporte.setTipoDatos(estaHoja, tipoDatos);
	WBQuerys[estaHoja].add("organigrama.xmlqtypeoplebydependencies()");
	Vector vectorColores = new Vector();
	vectorColores.add("Normal8");
	vectorColores.add("Normal8");
	vectorColores.add("Normal8");
	reporte.setColores(estaHoja, tablas, vectorColores);
	tablas++;
    }

}
