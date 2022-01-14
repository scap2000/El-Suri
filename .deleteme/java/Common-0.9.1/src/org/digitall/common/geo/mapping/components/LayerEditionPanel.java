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
 * LayerEditionPanel.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.inputpanels.TAInputPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.gaia.GaiaPrimitiveForm;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.shapefile.ShapeTypes;
import org.digitall.lib.sql.LibSQL;

public class LayerEditionPanel extends GaiaPrimitiveForm {

    private BasicPanel panelData = new BasicPanel();
    private String sqlScheme = "";
    private String sqlTable = "";
    private Layer layer;
    private Object geometry;
    private GridLayout gridLayout = new GridLayout(0,1,0,10);
    private Vector<Component> inputPanels = new Vector<Component>();
    private RefreshGridButton btnTableEditor = new RefreshGridButton();
    private boolean validGeometry = false;
    private HashMap<String, Integer> primaryKeys = new HashMap<String, Integer>();
    private Vector<String> columns = new Vector<String>();
    private int idPolygon = -1;

    public LayerEditionPanel(Layer _layer) {
	super();
	if (_layer.getGeometrySetConfig().getSqlScheme().equalsIgnoreCase("gis")) { 
	//Sólo permitimos por ahora los layers experimentales
	    layer = _layer;
	    setSqlScheme(layer.getGeometrySetConfig().getSqlScheme());
	    setSqlTable(layer.getGeometrySetConfig().getSqlTable());
	    try {
	        jbInit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	} else {
	    Advisor.messageBox("Sólo se permite trabajar con los layers experimentales", "Aviso");
	}
    } 

    private void jbInit() throws Exception {
	setTitle("Agregar/Modificar geometría");
	this.setSize(new Dimension(432, 308));
	panelData.setBounds(new Rectangle(10, 10, 290, 10));
	panelData.setLayout(gridLayout);
        panelData.setPreferredSize(new Dimension(270, 1));
        panelData.setSize(new Dimension(270, 10));
	this.setCentralPanel(panelData);
	setVisiblePrintButton(false);
	btnTableEditor.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnTableEditor_actionPerformed(e);
		}
	    });
	addButton(btnTableEditor);
    }

    private void clearData() {
        setEnabledDeleteButton(false);
    }

    public void delete() {
	if (LibSQL.executeQuery("DELETE FROM " + sqlScheme + "." + sqlTable + getSQLCondition())) {
	    layer.getGeometrySet().removeGeometry(geometry);
	    Advisor.messageBox("Geometría borrada con éxito", "Borrar geometría");
	} else {
	    Advisor.messageBox("Ha ocurrido un error al borrar la geometría", "Borrar geometría");
	}
    }

    public void saveData() {
	boolean _save = validGeometry;
	if (!validGeometry) {
	    _save = (Advisor.question("Geometría no válida", "La geometría no es válida\n¿Desea guardarla de todos modos?"));
	}
	if (_save) {
	    StringBuilder _query = new StringBuilder();
	    if (idPolygon != -1) {
	        _query = new StringBuilder("UPDATE " + sqlScheme + "." +
	                sqlTable + " SET ");
	        for (int i = 0; i < columns.size()-1; i++) {
	            _query.append(columns.elementAt(i) + " = " + getSQLStringFromComponent(inputPanels.elementAt(i)) + ",\n");
	        }
	        _query.append(columns.elementAt(columns.size()-1) + " = " + getSQLStringFromComponent(inputPanels.elementAt(inputPanels.size()-1)));
		_query.append(getSQLCondition());
	    } else {
	        _query = new StringBuilder("INSERT INTO " + sqlScheme + "." +
	                sqlTable + " VALUES ( ");
	        for (int i = 0; i < inputPanels.size()-1; i++) {
	            _query.append(getSQLStringFromComponent(inputPanels.elementAt(i)) + ", ");
	        }
	        _query.append(getSQLStringFromComponent(inputPanels.elementAt(inputPanels.size()-1)) + ");");
	    }
	    if (LibSQL.executeQuery(_query.toString())) {
		Advisor.messageBox("Geometría grabada con éxito", "Grabar geometría");
		if (idPolygon == -1) {
		    Advisor.messageBox("OJO! Si la geometría no es dibujada habrá que recargar el layer", "Cuidado!");
		    layer.getGeometrySet().addGeometry(geometry);
		}
		close();
	    } else {
		Advisor.messageBox("Ha ocurrido un error al grabar la geometría", "Grabar geometría");
	    }
	}
    }

    private void loadData() {
        if (idPolygon != -1)  {
            setEnabledDeleteButton(true);
	    try {
		ResultSet _results = LibSQL.exQuery("SELECT * FROM " + sqlScheme + "." + sqlTable +
							" WHERE " + layer.getGeometrySetConfig().getIDColumn() 
						    + " = " + idPolygon + ";");
		ResultSetMetaData _rsMetaData = _results.getMetaData();
		if (_results.next()) {
		    for (int i = 0; i < _rsMetaData.getColumnCount(); i++) {
		        if (inputPanels.elementAt(i) instanceof TFInput) {
		            ((TFInput)inputPanels.elementAt(i)).getTextField().setValue(_results.getObject(i+1));
		        } else if (inputPanels.elementAt(i) instanceof BasicCheckBox) {
		            ((BasicCheckBox)inputPanels.elementAt(i)).setSelected(_results.getBoolean(i+1));
		        } else if (inputPanels.elementAt(i) instanceof TAInputPanel) {
		            ((TAInputPanel)inputPanels.elementAt(i)).getTextArea().setText(_results.getString(i+1));
		        } else {
		            inputPanels.elementAt(i).setName(_results.getString(i));
		        }
		        if (primaryKeys.containsKey(_rsMetaData.getColumnName(i+1))) {
		            primaryKeys.put(_rsMetaData.getColumnName(i+1), _results.getInt(i+1));
		        }
		    }
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
        } else  {
            setEnabledDeleteButton(false);
        }
    }

    @Override
    public void setContentObject(Object _contentObject) {
	geometry = _contentObject;
	if (ESRIPoint.class.isInstance(geometry)) {
	    idPolygon = ((ESRIPoint)geometry).getIdPoint();
	} else if (ESRIPolygon.class.isInstance(geometry)) {
	    if (layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
		idPolygon = ((ESRIPolygon)geometry).getIdPolygon();
	    } else if (layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
		idPolygon = ((ESRIPolygon)geometry).getIdPolygon();
	    }
	}
	String _geometryString = "GEOMETRY";
	if (ESRIPoint.class.isInstance(_contentObject)) {
	    _geometryString = ((ESRIPoint)_contentObject).asPostGISPointString();
	} else if (ESRIPolygon.class.isInstance(_contentObject)) {
	    if (layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYGON) {
	        _geometryString = ((ESRIPolygon)_contentObject).asPostGISPolygonString();
	    } else if (layer.getGeometrySetConfig().getShapeType() == ShapeTypes.POLYLINE) {
		_geometryString = ((ESRIPolygon)_contentObject).asPostGISPolylineString();
	    }
	}
	try {
	    ResultSet _results = LibSQL.exQuery("SELECT isvalid(geometryFromText('" + _geometryString + "')) AS valid;");
	    _results.next();
	    validGeometry = _results.getBoolean("valid");
	} catch (SQLException e) {
	    
	}
	if (!validGeometry) {
	    Advisor.messageBox("La geometría no es válida", "Error al dibujar");
	}

	try {
	    ResultSet _result = LibSQL.exQuery("SELECT * FROM " + sqlScheme + "." + sqlTable + " LIMIT 0");
	    ResultSetMetaData _rsMetaData = _result.getMetaData();

	    fetchPrimaryKeys();
	    
	    columns.removeAllElements();
	    for (int i = 1; i <= _rsMetaData.getColumnCount(); i++) {
	        System.out.println(_rsMetaData.getColumnName(i) + ": " + _rsMetaData.getColumnTypeName(i) + "(" + _rsMetaData.getColumnType(i) + ")");
		columns.add(_rsMetaData.getColumnName(i));
	        switch (_rsMetaData.getColumnType(i)) {
	            case Types.VARCHAR :
	            case Types.CHAR :
			TFInput _tfVarChar = new TFInput(DataTypes.STRING, _rsMetaData.getColumnName(i), true);
			inputPanels.add(_tfVarChar);
	                break;
	            case 1111 :
			//GEOMETRY
			TFInput _tfGeometry = new TFInput(DataTypes.GEOMETRY, _rsMetaData.getColumnName(i), true);
			_tfGeometry.setValue(_geometryString);
			_tfGeometry.setEditable(false);
			_tfGeometry.getTextField().setOpaque(true);
			_tfGeometry.getTextField().setBackground(new Color(1f,.8f,0f,0.7f));
			inputPanels.add(_tfGeometry);
			break;
	            case -7 :
	                //BOOLEAN
			inputPanels.add(new BasicCheckBox(_rsMetaData.getColumnName(i)));
			break;
	            case Types.DATE :
	            case Types.TIMESTAMP :
	            case Types.TIME :
			inputPanels.add(new TFInput(DataTypes.DATE, _rsMetaData.getColumnName(i), true));
	                break;
	            case Types.LONGVARCHAR :
			TAInputPanel _jtArea = new TAInputPanel(DataTypes.STRING, _rsMetaData.getColumnName(i), true);
			inputPanels.add(_jtArea);
	                break;
	            case Types.BIGINT :
	            case Types.INTEGER :
	            case Types.SMALLINT :
	            case Types.TINYINT :
			TFInput _tfInteger = new TFInput(DataTypes.INTEGER, _rsMetaData.getColumnName(i), true);
			if (primaryKeys.containsKey(_rsMetaData.getColumnName(i))) {
			    _tfInteger.getTextField().setOpaque(true);
			    _tfInteger.getTextField().setBackground(new Color(1f,.5f,0f,.3f));
			    if (idPolygon != -1) {
			        _tfInteger.setEditable(false);
			    }
			}
			inputPanels.add(_tfInteger);
	                break;
	            case Types.DECIMAL :
	            case Types.DOUBLE :
	            case Types.FLOAT :
	            case Types.NUMERIC :
	            case Types.REAL :
			inputPanels.add(new TFInput(DataTypes.DOUBLE_EXTENDED, _rsMetaData.getColumnName(i), true));
	                break;
	            default :
			TFInput _tfNotSupported = new TFInput(DataTypes.STRING, _rsMetaData.getColumnName(i), true);
			_tfNotSupported.setEnabled(false);
			_tfNotSupported.setEditable(false);
			inputPanels.add(_tfNotSupported);
	                break;
	        }
	    }
	    _result = LibSQL.exQuery("SELECT typname AS datatype, " +
	                "CASE WHEN " + 
	                "    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
	                "    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
	                "    ELSE col_description(ta.attrelid,ta.attnum) " + 
	                "END " + 
	                "AS coldescription, attname AS column FROM pg_class bc, pg_attribute ta, " +
	                "pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0 " + 
	                " AND NOT ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" +
	                sqlScheme + "' and relam = 0 AND bc.relname = '" +
	                sqlTable + "' " +
	                " and nspname not like 'pg_%' and ta.atttypid = ty.oid ORDER BY ta.attnum");

	    /*_result = LibSQL.exQuery("SELECT typname AS datatype, " +
	                "CASE WHEN " + 
	                "    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
	                "    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
	                "    ELSE col_description(ta.attrelid,ta.attnum) " + 
	                "END " + 
	                "AS coldescription, attname AS column FROM pg_class bc, pg_attribute ta, " +
	                "pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0 " + 
	                " AND NOT ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" +
	                "eyc_salta" + "' and relam = 0 AND bc.relname = '" +
	                "nbi_1" + "' " +
	                " and nspname not like 'pg_%' and ta.atttypid = ty.oid ORDER BY ta.attnum");*/

	    int i = 0;
	    gridLayout.setRows(inputPanels.size());
	    panelData.removeAll();
	    panelData.setSize(new Dimension(panelData.getWidth(), inputPanels.size()*15));
	    panelData.setPreferredSize(new Dimension(panelData.getWidth(), inputPanels.size()*40));
	    while (_result.next()) {
		//System.out.println(_result.getString("coldescription"));
		//inputPanels[i].setComponentName(_result.getString("coldescription"));
	        i++;
	    }
	    addInputPanels();
	    loadData();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void scrollToVisible(FocusEvent focusEvent) {
	panelData.scrollRectToVisible(focusEvent.getComponent().getParent().getBounds());
	repaint();
    }

    @Override
    public Object getContentObject() {
	return geometry;
    }

    @Override
    public void printReport() {
        /*BasicReport report = new BasicReport(LayerEditionPanel.class.getResource("xml/TurismoReport.xml"));
        report.doReport(); */
    }

    public void setSqlScheme(String sqlScheme) {
	this.sqlScheme = sqlScheme;
    }

    public String getSqlScheme() {
	return sqlScheme;
    }

    public void setSqlTable(String sqlTable) {
	this.sqlTable = sqlTable;
    }

    public String getSqlTable() {
	return sqlTable;
    }
    
    private void addInputPanels() {
	for (int i = 0; i < inputPanels.size(); i++) {
	    Component _component;
	    if (inputPanels.elementAt(i) instanceof TFInput) {
		_component = ((TFInput)inputPanels.elementAt(i)).getTextField();
	        panelData.add(inputPanels.elementAt(i));
	    } else if (inputPanels.elementAt(i) instanceof BasicCheckBox) {
		_component = inputPanels.elementAt(i);
		BasicPanel _jpCheckBox = new BasicPanel(new BorderLayout());
		_jpCheckBox.add(inputPanels.elementAt(i), BorderLayout.CENTER);
	        panelData.add(_jpCheckBox);
	    } else if (inputPanels.elementAt(i) instanceof TAInputPanel) {
		_component = ((TAInputPanel)inputPanels.elementAt(i)).getTextArea();
	        panelData.add(inputPanels.elementAt(i));
	    } else {
		_component = inputPanels.elementAt(i);
	        panelData.add(inputPanels.elementAt(i));
	    }
	    _component.addFocusListener(new FocusListener() {

		public void focusGained(FocusEvent focusEvent) {
		    scrollToVisible(focusEvent);
		}

		public void focusLost(FocusEvent focusEvent) {
		}

	    });
	}
    }
    
    private String getSQLStringFromComponent(Component _component) {
	String _returns = "";
	//Evaluar tipo de datos y devolver con apóstrofos si es necesario
	//Cuidado con caracteres de escape y comas
	//booleanos
	if (_component instanceof TFInput) {
	    TFInput _tfInput = ((TFInput)_component);
	    switch (_tfInput.getDataType()) {
	        case DataTypes.INTEGER :
	        case DataTypes.DOUBLE :
		case DataTypes.MONEY :
		case DataTypes.PERCENT :
		case DataTypes.DOUBLE_EXTENDED :
		case DataTypes.MONEY_EXTENDED :
		    _returns = _tfInput.getValue().toString();
		    break;
		case DataTypes.DATE :
		case DataTypes.SIMPLEDATE :
		    _returns = _tfInput.getDate().length()>0?"'" + _tfInput.getDate() + "'":"null";
		    break;
	        case DataTypes.STRING :
		    _returns = "'" + _tfInput.getValue() + "'";
		    break;
	        case DataTypes.GEOMETRY :
		        _returns = "GeometryFromText('" + _tfInput.getValue().toString()  + "')";
	            break;
		default:
		    _returns = "";
		    break;
		
	    }
	} else if (_component instanceof BasicCheckBox) {
	    _returns = ((BasicCheckBox)_component).isSelected()?"true":"false";
	} else if (_component instanceof TAInputPanel) {
	    _returns = "'" + ((TAInputPanel)_component).getText() + "'";
	} else {
	    _returns = "null";
	}
    	return _returns;
    }

    private void btnTableEditor_actionPerformed(ActionEvent e) {
	TableEditionPanel _tableEditionPanel = new TableEditionPanel(sqlScheme, sqlTable);

	ExtendedInternalFrame _tableEditionInternalFrame = new ExtendedInternalFrame("Editor de tablas");
	_tableEditionInternalFrame.setCentralPanel(_tableEditionPanel);
	_tableEditionInternalFrame.setClosable(true);
	_tableEditionInternalFrame.setDesktop(Environment.getActiveDesktop());
	_tableEditionInternalFrame.setIconifiable(false);
	_tableEditionInternalFrame.show();
	_tableEditionInternalFrame.setMaximizable(true);
    }

    private void fetchPrimaryKeys() {
	ResultSet _primaryKeys = LibSQL.exQuery("SELECT pg_constraint.oid, pg_constraint.conname, " + 
				 "(SELECT pg_attribute.attname FROM pg_attribute WHERE pg_attribute.attrelid = conrelid " + 
				 "AND pg_attribute.attnum = any(conkey)) AS column FROM pg_constraint, pg_class, pg_namespace nsp " + 
				 "WHERE nsp.nspname = '" + sqlScheme + "' AND pg_class.relname = '" + sqlTable + "' " +
				 "AND pg_class.relnamespace = nsp.oid AND pg_constraint.conrelid = pg_class.oid " + 
				 "AND pg_constraint.contype = 'p';");
	primaryKeys.clear();
	try {
	    while (_primaryKeys.next()) {
		primaryKeys.put(_primaryKeys.getString("column"), 0);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private String getSQLCondition() {
	StringBuilder _sqlCondition = new StringBuilder();
	_sqlCondition.append(" WHERE " );
	Set<String> _keySet = primaryKeys.keySet();
	for (int i = 0; i < _keySet.size()-1; i++) {
	    _sqlCondition.append(_keySet.toArray()[i].toString() + " = " + primaryKeys.get(_keySet.toArray()[i].toString()) + " AND ");
	}
	_sqlCondition.append(_keySet.toArray()[_keySet.size()-1].toString() + " = " + primaryKeys.get(_keySet.toArray()[_keySet.size()-1].toString()));
	return _sqlCondition.toString();
    }

}
