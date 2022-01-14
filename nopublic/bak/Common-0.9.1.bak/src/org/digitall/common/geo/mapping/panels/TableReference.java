package org.digitall.common.geo.mapping.panels;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class TableReference {

    private boolean backward = false;
    private String id = "";
    private String sourceScheme = "";
    private String sourceTable = "";
    private String sourceColumn = "";
    private String destScheme = "";
    private String destTable = "";
    private String destColumn = "";
    private Vector<String> columns = new Vector<String>();

    public TableReference(String _id) {
	id = _id;
    }

    public void setBackward() {
	this.backward = true;
    }

    public void setForward() {
	this.backward = false;
    }

    public boolean isBackward() {
	return backward;
    }

    public boolean isForward() {
	return !backward;
    }

    public void setDestScheme(String _destScheme) {
	this.destScheme = _destScheme;
    }

    public String getDestScheme() {
	return destScheme;
    }

    public void setDestTable(String _destTable) {
	this.destTable = _destTable;
    }

    public String getDestTable() {
	return destTable;
    }

    public void setDestColumn(String _destColumn) {
	this.destColumn = _destColumn;
    }

    public String getDestColumn() {
	return destColumn;
    }

    public String getId() {
	return id;
    }

    public String getReference() {
	return destScheme + "." + destTable + "." + destColumn;
    }

    public String getReferenceCondition() {
	return sourceScheme + "." + sourceTable + "." + sourceColumn + " = " + destScheme + "." + destTable + "." + destColumn;
    }

    public void setSourceColumn(String _sourceColumn) {
	this.sourceColumn = _sourceColumn;
    }

    public String getSourceColumn() {
	return sourceColumn;
    }

    public void setSourceScheme(String _sourceScheme) {
	this.sourceScheme = _sourceScheme;
    }

    public String getSourceScheme() {
	return sourceScheme;
    }

    public void setSourceTable(String _sourceTable) {
	this.sourceTable = _sourceTable;
    }

    public String getSourceTable() {
	return sourceTable;
    }
    
    public void loadColumns() {
	columns = LibSQL.getVector("SELECT attname /*AS columna*/ || '&' || typname /*AS tipodatos*/ || '&' || " +
	"CASE WHEN " + 
	"    col_description(ta.attrelid,ta.attnum) IS NULL THEN attname " + 
	"    WHEN col_description(ta.attrelid,ta.attnum) = '' THEN attname " + 
	"    ELSE col_description(ta.attrelid,ta.attnum) " + 
	"END " + 
	"    FROM pg_class bc, pg_attribute ta, pg_namespace ns, " + " pg_type ty WHERE ta.attrelid = bc.oid and ta.attnum > 0  and not ta.attisdropped and bc.relnamespace = ns.oid " + " and ns.nspname = '" + destScheme + "' and relam = 0 AND bc.relname = '" + destTable + "' " + " and nspname not like 'pg_%' and ta.atttypid = ty.oid order by attnum");
    }

    public Vector<String> getColumns() {
	return columns;
    }

}
