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
 * Proced.java
 *
 * */
package org.digitall.deprecatedlibs;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public abstract class Proced {
    
    public static void expandAll(JTree tree, TreePath parent, boolean expand) 
    {
      if (parent != null){
	  // Traverse children
	  TreeNode node = (TreeNode)parent.getLastPathComponent();
	  if (node.getChildCount() >= 0) {
	      for (Enumeration e=node.children(); e.hasMoreElements(); ) {
		  TreeNode n = (TreeNode)e.nextElement();
		  TreePath path = parent.pathByAddingChild(n);
		  expandAll(tree, path, expand);
	      }
	  }
    
	  // Expansion or collapse must be done bottom-up
	  if (expand) {
	      tree.expandPath(parent);
	  } else {
	      tree.collapsePath(parent);
	  }
      } else {
	  Advisor.messageBox("Seleccione un item.","Selecionar un Item");
      }
    }

    
    //Si fecha es verdadero devuelve la fecha actual, si no devuelve la hora actual
    public static String SumResFechaHora(String Parametro, boolean fecha, String operacion, String cantidad, String valor, boolean HoraCompleta) throws Exception {
	String SQLQuery;
	if (fecha) {
	    return setFormatDate(LibSQL.getCampo("Select date '" + Parametro + "' " + operacion + " Interval '" + cantidad + " " + valor + "' as string"), true);
	} else {
	    return Hora(LibSQL.getCampo("Select time '" + Parametro + "' " + operacion + " Interval '" + cantidad + " " + valor + "' as string"), false, HoraCompleta);
	}
    }

    public static String Hora(String hora, boolean mostrar, boolean HoraCompleta) throws Exception {
	Date date;
	SimpleDateFormat formatter;
	if (hora.length() > 0) {
	    if (mostrar) {
		formatter = new SimpleDateFormat("HH:mm:ss");
		date = (Date)formatter.parse(hora);
		String fecharet = formatter.format(date);
		if (HoraCompleta) {
		    formatter.applyPattern("HH:mm:ss");
		} else {
		    formatter.applyPattern("HH:mm");
		}
	    } else {
		formatter = new SimpleDateFormat("HH:mm:ss");
		date = (Date)formatter.parse(hora);
		String fecharet = formatter.format(date);
		formatter.applyPattern("HH:mm:ss");
	    }
	    //   System.out.println(formatter.format(date).toString());
	    return formatter.format(date).toString();
	} else {
	    return "";
	}
    }

    public static String FechaHora2(boolean fecha, boolean HoraCompleta) {
	try {
	    if (fecha) {
		return setFormatDate(Environment.currentDate, true);
	    } else {
		return Hora(Environment.currentTime, true, HoraCompleta);
	    }
	} catch (Exception x) {
	    x.printStackTrace();
	    return "";
	}
    }

    /**
     * 
     * @param _dateText
     * @param _view
     * View: true --> Para visualizar, false --> para insertar en la BD
     * @return
     */
    public static String setFormatDate(String _dateText, boolean _view) {
	try {
	    Date date;
	    SimpleDateFormat formatter;
	    if (_dateText.length() > 0) {
		if (_view) {
		    formatter = new SimpleDateFormat("yyyy-MM-dd");
		    date = (Date)formatter.parse(_dateText);
		    formatter.applyPattern("dd/MM/yyyy");
		} else {
		    formatter = new SimpleDateFormat("dd/MM/yyyy");
		    date = (Date)formatter.parse(_dateText);
		    formatter.applyPattern("yyyy-MM-dd");
		}
		return formatter.format(date).toString();
	    } else {
		return "";
	    }
	} catch (NullPointerException x) {
	    return "";
	} catch (Exception x) {
	    x.printStackTrace();
	    return "";
	}
    }

    public static String TransformaNull_Texto(String _string) {
	try {
	    if (_string == null) {
		return "";
	    } else {
		return _string;
	    }
	} catch (NullPointerException x) {
	    return "";
	}
    }

    public static String TransformaTexto_Null(String _string) {
	if (_string.length() > 0) {
	    return "'" + _string + "'";
	} else {
	    return "null";
	}
    }

    public static String DobleDec(String numero) {
	double n = 0;
	try {
	    n = Double.parseDouble(numero);
	} catch (Exception x) {
	    n = 0;
	}
	NumberFormat formatter = new DecimalFormat("#0.00");
	return formatter.format(n).replace(',', '.');
    }

    /**
     *
     * @param _firstDate
     * @param _secondDate
     * @return 
     *          -1 o cualquier otro valor: Error!
     * 		0: Iguales;
     *          1: _firstDate < _secondDate;
     *          2: _firstDate > _secondDate;
     */
    public static int compareDates(String _firstDate, String _secondDate) {
	int _returns = -1;
	try {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date d1 = dateFormat.parse(_firstDate);
	    Date d2 = dateFormat.parse(_secondDate);
	    if (d1.equals(d2))
		_returns = 0;
	    //"the same date as";
	    else if (d1.before(d2))
		_returns = 1;
	    //"before";
	    else
		_returns = 2;
	} catch (Exception e) {
	}
	return _returns;
    }

}
