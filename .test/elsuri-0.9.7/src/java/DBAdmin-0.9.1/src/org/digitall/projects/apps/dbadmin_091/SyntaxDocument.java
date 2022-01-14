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
 * SyntaxDocument.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;

import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class SyntaxDocument extends DefaultStyledDocument {

    private DefaultStyledDocument doc;
    private Element rootElement;
    private boolean multiLineComment;
    private MutableAttributeSet normal;
    private MutableAttributeSet keyword;
    private MutableAttributeSet comment;
    private MutableAttributeSet quote;
    private HashSet keywords;

    public SyntaxDocument() {
	doc = this;
	rootElement = doc.getDefaultRootElement();
	putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
	normal = new SimpleAttributeSet();
	StyleConstants.setForeground(normal, Color.black);
	comment = new SimpleAttributeSet();
	StyleConstants.setForeground(comment, Color.gray);
	StyleConstants.setItalic(comment, true);
	keyword = new SimpleAttributeSet();
	StyleConstants.setForeground(keyword, Color.blue);
	quote = new SimpleAttributeSet();
	StyleConstants.setForeground(quote, Color.red);
	keywords = new HashSet();
	/**
	 * JAVA KEYWORDS
	keywords.add("abstract");
	keywords.add("boolean");
	keywords.add("break");
	keywords.add("byte");
	keywords.add("byvalue");
	keywords.add("case");
	keywords.add("cast");
	keywords.add("catch");
	keywords.add("char");
	keywords.add("class");
	keywords.add("const");
	keywords.add("continue");
	keywords.add("default");
	keywords.add("do");
	keywords.add("double");
	keywords.add("else");
	keywords.add("extends");
	keywords.add("false");
	keywords.add("final");
	keywords.add("finally");
	keywords.add("float");
	keywords.add("for");
	keywords.add("future");
	keywords.add("generic");
	keywords.add("goto");
	keywords.add("if");
	keywords.add("implements");
	keywords.add("import");
	keywords.add("inner");
	keywords.add("instanceof");
	keywords.add("int");
	keywords.add("interface");
	keywords.add("long");
	keywords.add("native");
	keywords.add("new");
	keywords.add("null");
	keywords.add("operator");
	keywords.add("outer");
	keywords.add("package");
	keywords.add("private");
	keywords.add("protected");
	keywords.add("public");
	keywords.add("rest");
	keywords.add("return");
	keywords.add("short");
	keywords.add("static");
	keywords.add("super");
	keywords.add("switch");
	keywords.add("synchronized");
	keywords.add("this");
	keywords.add("throw");
	keywords.add("throws");
	keywords.add("transient");
	keywords.add("true");
	keywords.add("try");
	keywords.add("var");
	keywords.add("void");
	keywords.add("volatile");
	keywords.add("while");
	*/
	/**
	 * POSTGRESQL KEYWORDS
	 * */
	keywords.add("ABORT");
	keywords.add("ABS");
	keywords.add("ABSOLUTE");
	keywords.add("ACCESS");
	keywords.add("ACTION");
	keywords.add("ADA");
	keywords.add("ADD");
	keywords.add("ADMIN");
	keywords.add("AFTER");
	keywords.add("AGGREGATE");
	keywords.add("ALIAS");
	keywords.add("ALL");
	keywords.add("ALLOCATE");
	keywords.add("ALTER");
	keywords.add("ANALYSE");
	keywords.add("ANALYZE");
	keywords.add("AND");
	keywords.add("ANY");
	keywords.add("ARE");
	keywords.add("ARRAY");
	keywords.add("AS");
	keywords.add("ASC");
	keywords.add("ASENSITIVE");
	keywords.add("ASSERTION");
	keywords.add("ASSIGNMENT");
	keywords.add("ASYMMETRIC");
	keywords.add("AT");
	keywords.add("ATOMIC");
	keywords.add("AUTHORIZATION");
	keywords.add("AVG");
	keywords.add("BACKWARD");
	keywords.add("BEFORE");
	keywords.add("BEGIN");
	keywords.add("BETWEEN");
	keywords.add("BIGINT");
	keywords.add("BINARY");
	keywords.add("BIT");
	keywords.add("BITVAR");
	keywords.add("BIT_LENGTH");
	keywords.add("BLOB");
	keywords.add("BOOLEAN");
	keywords.add("BOTH");
	keywords.add("BREADTH");
	keywords.add("BY");
	keywords.add("C");
	keywords.add("CACHE");
	keywords.add("CALL");
	keywords.add("CALLED");
	keywords.add("CARDINALITY");
	keywords.add("CASCADE");
	keywords.add("CASCADED");
	keywords.add("CASE");
	keywords.add("CAST");
	keywords.add("CATALOG");
	keywords.add("CATALOG_NAME");
	keywords.add("CHAIN");
	keywords.add("CHAR");
	keywords.add("CHARACTER");
	keywords.add("CHARACTERISTICS");
	keywords.add("CHARACTER_LENGTH");
	keywords.add("CHARACTER_SET_CATALOG");
	keywords.add("CHARACTER_SET_NAME");
	keywords.add("CHARACTER_SET_SCHEMA");
	keywords.add("CHAR_LENGTH");
	keywords.add("CHECK");
	keywords.add("CHECKED");
	keywords.add("CHECKPOINT");
	keywords.add("CLASS");
	keywords.add("CLASS_ORIGIN");
	keywords.add("CLOB");
	keywords.add("CLOSE");
	keywords.add("CLUSTER");
	keywords.add("COALESCE");
	keywords.add("COBOL");
	keywords.add("COLLATE");
	keywords.add("COLLATION");
	keywords.add("COLLATION_CATALOG");
	keywords.add("COLLATION_NAME");
	keywords.add("COLLATION_SCHEMA");
	keywords.add("COLUMN");
	keywords.add("COLUMN_NAME");
	keywords.add("COMMAND_FUNCTION");
	keywords.add("COMMAND_FUNCTION_CODE");
	keywords.add("COMMENT");
	keywords.add("COMMIT");
	keywords.add("COMMITTED");
	keywords.add("COMPLETION");
	keywords.add("CONDITION_NUMBER");
	keywords.add("CONNECT");
	keywords.add("CONNECTION");
	keywords.add("CONNECTION_NAME");
	keywords.add("CONSTRAINT");
	keywords.add("CONSTRAINTS");
	keywords.add("CONSTRAINT_CATALOG");
	keywords.add("CONSTRAINT_NAME");
	keywords.add("CONSTRAINT_SCHEMA");
	keywords.add("CONSTRUCTOR");
	keywords.add("CONTAINS");
	keywords.add("CONTINUE");
	keywords.add("CONVERSION");
	keywords.add("CONVERT");
	keywords.add("COPY");
	keywords.add("CORRESPONDING");
	keywords.add("COUNT");
	keywords.add("CREATE");
	keywords.add("CREATEDB");
	keywords.add("CREATEUSER");
	keywords.add("CROSS");
	keywords.add("CUBE");
	keywords.add("CURRENT");
	keywords.add("CURRENT_DATE");
	keywords.add("CURRENT_PATH");
	keywords.add("CURRENT_ROLE");
	keywords.add("CURRENT_TIME");
	keywords.add("CURRENT_TIMESTAMP");
	keywords.add("CURRENT_USER");
	keywords.add("CURSOR");
	keywords.add("CURSOR_NAME");
	keywords.add("CYCLE");
	keywords.add("DATA");
	keywords.add("DATABASE");
	keywords.add("DATE");
	keywords.add("DATETIME_INTERVAL_CODE");
	keywords.add("DATETIME_INTERVAL_PRECISION");
	keywords.add("DAY");
	keywords.add("DEALLOCATE");
	keywords.add("DEC");
	keywords.add("DECIMAL");
	keywords.add("DECLARE");
	keywords.add("DEFAULT");
	keywords.add("DEFERRABLE");
	keywords.add("DEFERRED");
	keywords.add("DEFINED");
	keywords.add("DEFINER");
	keywords.add("DELETE");
	keywords.add("DELIMITER");
	keywords.add("DELIMITERS");
	keywords.add("DEPTH");
	keywords.add("DEREF");
	keywords.add("DESC");
	keywords.add("DESCRIBE");
	keywords.add("DESCRIPTOR");
	keywords.add("DESTROY");
	keywords.add("DESTRUCTOR");
	keywords.add("DETERMINISTIC");
	keywords.add("DIAGNOSTICS");
	keywords.add("DICTIONARY");
	keywords.add("DISCONNECT");
	keywords.add("DISPATCH");
	keywords.add("DISTINCT");
	keywords.add("DO");
	keywords.add("DOMAIN");
	keywords.add("DOUBLE");
	keywords.add("DROP");
	keywords.add("DYNAMIC");
	keywords.add("DYNAMIC_FUNCTION");
	keywords.add("DYNAMIC_FUNCTION_CODE");
	keywords.add("EACH");
	keywords.add("ELSE");
	keywords.add("ENCODING");
	keywords.add("ENCRYPTED");
	keywords.add("END");
	keywords.add("END-EXEC");
	keywords.add("EQUALS");
	keywords.add("ESCAPE");
	keywords.add("EVERY");
	keywords.add("EXCEPT");
	keywords.add("EXCEPTION");
	keywords.add("EXCLUSIVE");
	keywords.add("EXEC");
	keywords.add("EXECUTE");
	keywords.add("EXISTING");
	keywords.add("EXISTS");
	keywords.add("EXPLAIN");
	keywords.add("EXTERNAL");
	keywords.add("EXTRACT");
	keywords.add("FALSE");
	keywords.add("FETCH");
	keywords.add("FINAL");
	keywords.add("FIRST");
	keywords.add("FLOAT");
	keywords.add("FOR");
	keywords.add("FORCE");
	keywords.add("FOREIGN");
	keywords.add("FORTRAN");
	keywords.add("FORWARD");
	keywords.add("FOUND");
	keywords.add("FREE");
	keywords.add("FREEZE");
	keywords.add("FROM");
	keywords.add("FULL");
	keywords.add("FUNCTION");
	keywords.add("G");
	keywords.add("GENERAL");
	keywords.add("GENERATED");
	keywords.add("GET");
	keywords.add("GLOBAL");
	keywords.add("GO");
	keywords.add("GOTO");
	keywords.add("GRANT");
	keywords.add("GRANTED");
	keywords.add("GROUP");
	keywords.add("GROUPING");
	keywords.add("HANDLER");
	keywords.add("HAVING");
	keywords.add("HIERARCHY");
	keywords.add("HOLD");
	keywords.add("HOST");
	keywords.add("HOUR");
	keywords.add("IDENTITY");
	keywords.add("IGNORE");
	keywords.add("ILIKE");
	keywords.add("IMMEDIATE");
	keywords.add("IMMUTABLE");
	keywords.add("IMPLEMENTATION");
	keywords.add("IMPLICIT");
	keywords.add("IN");
	keywords.add("INCREMENT");
	keywords.add("INDEX");
	keywords.add("INDICATOR");
	keywords.add("INFIX");
	keywords.add("INHERITS");
	keywords.add("INITIALIZE");
	keywords.add("INITIALLY");
	keywords.add("INNER");
	keywords.add("INOUT");
	keywords.add("INPUT");
	keywords.add("INSENSITIVE");
	keywords.add("INSERT");
	keywords.add("INSTANCE");
	keywords.add("INSTANTIABLE");
	keywords.add("INSTEAD");
	keywords.add("INT");
	keywords.add("INTEGER");
	keywords.add("INTERSECT");
	keywords.add("INTERVAL");
	keywords.add("INTO");
	keywords.add("INVOKER");
	keywords.add("IS");
	keywords.add("ISNULL");
	keywords.add("ISOLATION");
	keywords.add("ITERATE");
	keywords.add("JOIN");
	keywords.add("K");
	keywords.add("KEY");
	keywords.add("KEY_MEMBER");
	keywords.add("KEY_TYPE");
	keywords.add("LANCOMPILER");
	keywords.add("LANGUAGE");
	keywords.add("LARGE");
	keywords.add("LAST");
	keywords.add("LATERAL");
	keywords.add("LEADING");
	keywords.add("LEFT");
	keywords.add("LENGTH");
	keywords.add("LESS");
	keywords.add("LEVEL");
	keywords.add("LIKE");
	keywords.add("LIMIT");
	keywords.add("LISTEN");
	keywords.add("LOAD");
	keywords.add("LOCAL");
	keywords.add("LOCALTIME");
	keywords.add("LOCALTIMESTAMP");
	keywords.add("LOCATION");
	keywords.add("LOCATOR");
	keywords.add("LOCK");
	keywords.add("LOWER");
	keywords.add("M");
	keywords.add("MAP");
	keywords.add("MATCH");
	keywords.add("MAX");
	keywords.add("MAXVALUE");
	keywords.add("MESSAGE_LENGTH");
	keywords.add("MESSAGE_OCTET_LENGTH");
	keywords.add("MESSAGE_TEXT");
	keywords.add("METHOD");
	keywords.add("MIN");
	keywords.add("MINUTE");
	keywords.add("MINVALUE");
	keywords.add("MOD");
	keywords.add("MODE");
	keywords.add("MODIFIES");
	keywords.add("MODIFY");
	keywords.add("MODULE");
	keywords.add("MONTH");
	keywords.add("MORE");
	keywords.add("MOVE");
	keywords.add("MUMPS");
	keywords.add("NAME");
	keywords.add("NAMES");
	keywords.add("NATIONAL");
	keywords.add("NATURAL");
	keywords.add("NCHAR");
	keywords.add("NCLOB");
	keywords.add("NEW");
	keywords.add("NEXT");
	keywords.add("NO");
	keywords.add("NOCREATEDB");
	keywords.add("NOCREATEUSER");
	keywords.add("NONE");
	keywords.add("NOT");
	keywords.add("NOTHING");
	keywords.add("NOTIFY");
	keywords.add("NOTNULL");
	keywords.add("NULL");
	keywords.add("NULLABLE");
	keywords.add("NULLIF");
	keywords.add("NUMBER");
	keywords.add("NUMERIC");
	keywords.add("OBJECT");
	keywords.add("OCTET_LENGTH");
	keywords.add("OF");
	keywords.add("OFF");
	keywords.add("OFFSET");
	keywords.add("OIDS");
	keywords.add("OLD");
	keywords.add("ON");
	keywords.add("ONLY");
	keywords.add("OPEN");
	keywords.add("OPERATION");
	keywords.add("OPERATOR");
	keywords.add("OPTION");
	keywords.add("OPTIONS");
	keywords.add("OR");
	keywords.add("ORDER");
	keywords.add("ORDINALITY");
	keywords.add("OUT");
	keywords.add("OUTER");
	keywords.add("OUTPUT");
	keywords.add("OVERLAPS");
	keywords.add("OVERLAY");
	keywords.add("OVERRIDING");
	keywords.add("OWNER");
	keywords.add("PAD");
	keywords.add("PARAMETER");
	keywords.add("PARAMETERS");
	keywords.add("PARAMETER_MODE");
	keywords.add("PARAMETER_NAME");
	keywords.add("PARAMETER_ORDINAL_POSITION");
	keywords.add("PARAMETER_SPECIFIC_CATALOG");
	keywords.add("PARAMETER_SPECIFIC_NAME");
	keywords.add("PARAMETER_SPECIFIC_SCHEMA");
	keywords.add("PARTIAL");
	keywords.add("PASCAL");
	keywords.add("PASSWORD");
	keywords.add("PATH");
	keywords.add("PENDANT");
	keywords.add("PLACING");
	keywords.add("PLI");
	keywords.add("POSITION");
	keywords.add("POSTFIX");
	keywords.add("PRECISION");
	keywords.add("PREFIX");
	keywords.add("PREORDER");
	keywords.add("PREPARE");
	keywords.add("PRESERVE");
	keywords.add("PRIMARY");
	keywords.add("PRIOR");
	keywords.add("PRIVILEGES");
	keywords.add("PROCEDURAL");
	keywords.add("PROCEDURE");
	keywords.add("PUBLIC");
	keywords.add("READ");
	keywords.add("READS");
	keywords.add("REAL");
	keywords.add("RECHECK");
	keywords.add("RECURSIVE");
	keywords.add("REF");
	keywords.add("REFERENCES");
	keywords.add("REFERENCING");
	keywords.add("REINDEX");
	keywords.add("RELATIVE");
	keywords.add("RENAME");
	keywords.add("REPEATABLE");
	keywords.add("REPLACE");
	keywords.add("RESET");
	keywords.add("RESTRICT");
	keywords.add("RESULT");
	keywords.add("RETURN");
	keywords.add("RETURNED_LENGTH");
	keywords.add("RETURNED_OCTET_LENGTH");
	keywords.add("RETURNED_SQLSTATE");
	keywords.add("RETURNS");
	keywords.add("REVOKE");
	keywords.add("RIGHT");
	keywords.add("ROLE");
	keywords.add("ROLLBACK");
	keywords.add("ROLLUP");
	keywords.add("ROUTINE");
	keywords.add("ROUTINE_CATALOG");
	keywords.add("ROUTINE_NAME");
	keywords.add("ROUTINE_SCHEMA");
	keywords.add("ROW");
	keywords.add("ROWS");
	keywords.add("ROW_COUNT");
	keywords.add("RULE");
	keywords.add("SAVEPOINT");
	keywords.add("SCALE");
	keywords.add("SCHEMA");
	keywords.add("SCHEMA_NAME");
	keywords.add("SCOPE");
	keywords.add("SCROLL");
	keywords.add("SEARCH");
	keywords.add("SECOND");
	keywords.add("SECTION");
	keywords.add("SECURITY");
	keywords.add("SELECT");
	keywords.add("SELF");
	keywords.add("SENSITIVE");
	keywords.add("SEQUENCE");
	keywords.add("SERIALIZABLE");
	keywords.add("SERVER_NAME");
	keywords.add("SESSION");
	keywords.add("SESSION_USER");
	keywords.add("SET");
	keywords.add("SETOF");
	keywords.add("SETS");
	keywords.add("SHARE");
	keywords.add("SHOW");
	keywords.add("SIMILAR");
	keywords.add("SIMPLE");
	keywords.add("SIZE");
	keywords.add("SMALLINT");
	keywords.add("SOME");
	keywords.add("SOURCE");
	keywords.add("SPACE");
	keywords.add("SPECIFIC");
	keywords.add("SPECIFICTYPE");
	keywords.add("SPECIFIC_NAME");
	keywords.add("SQL");
	keywords.add("SQLCODE");
	keywords.add("SQLERROR");
	keywords.add("SQLEXCEPTION");
	keywords.add("SQLSTATE");
	keywords.add("SQLWARNING");
	keywords.add("STABLE");
	keywords.add("START");
	keywords.add("STATE");
	keywords.add("STATEMENT");
	keywords.add("STATIC");
	keywords.add("STATISTICS");
	keywords.add("STDIN");
	keywords.add("STDOUT");
	keywords.add("STORAGE");
	keywords.add("STRICT");
	keywords.add("STRUCTURE");
	keywords.add("STYLE");
	keywords.add("SUBCLASS_ORIGIN");
	keywords.add("SUBLIST");
	keywords.add("SUBSTRING");
	keywords.add("SUM");
	keywords.add("SYMMETRIC");
	keywords.add("SYSID");
	keywords.add("SYSTEM");
	keywords.add("SYSTEM_USER");
	keywords.add("TABLE");
	keywords.add("TABLE_NAME");
	keywords.add("TEMP");
	keywords.add("TEMPLATE");
	keywords.add("TEMPORARY");
	keywords.add("TERMINATE");
	keywords.add("THAN");
	keywords.add("THEN");
	keywords.add("TIME");
	keywords.add("TIMESTAMP");
	keywords.add("TIMEZONE_HOUR");
	keywords.add("TIMEZONE_MINUTE");
	keywords.add("TO");
	keywords.add("TOAST");
	keywords.add("TRAILING");
	keywords.add("TRANSACTION");
	keywords.add("TRANSACTIONS_COMMITTED");
	keywords.add("TRANSACTIONS_ROLLED_BACK");
	keywords.add("TRANSACTION_ACTIVE");
	keywords.add("TRANSFORM");
	keywords.add("TRANSFORMS");
	keywords.add("TRANSLATE");
	keywords.add("TRANSLATION");
	keywords.add("TREAT");
	keywords.add("TRIGGER");
	keywords.add("TRIGGER_CATALOG");
	keywords.add("TRIGGER_NAME");
	keywords.add("TRIGGER_SCHEMA");
	keywords.add("TRIM");
	keywords.add("TRUE");
	keywords.add("TRUNCATE");
	keywords.add("TRUSTED");
	keywords.add("TYPE");
	keywords.add("UNCOMMITTED");
	keywords.add("UNDER");
	keywords.add("UNENCRYPTED");
	keywords.add("UNION");
	keywords.add("UNIQUE");
	keywords.add("UNKNOWN");
	keywords.add("UNLISTEN");
	keywords.add("UNNAMED");
	keywords.add("UNNEST");
	keywords.add("UNTIL");
	keywords.add("UPDATE");
	keywords.add("UPPER");
	keywords.add("USAGE");
	keywords.add("USER");
	keywords.add("USER_DEFINED_TYPE_CATALOG");
	keywords.add("USER_DEFINED_TYPE_NAME");
	keywords.add("USER_DEFINED_TYPE_SCHEMA");
	keywords.add("USING");
	keywords.add("VACUUM");
	keywords.add("VALID");
	keywords.add("VALIDATOR");
	keywords.add("VALUE");
	keywords.add("VALUES");
	keywords.add("VARCHAR");
	keywords.add("VARIABLE");
	keywords.add("VARYING");
	keywords.add("VERBOSE");
	keywords.add("VERSION");
	keywords.add("VIEW");
	keywords.add("VOLATILE");
	keywords.add("WHEN");
	keywords.add("WHENEVER");
	keywords.add("WHERE");
	keywords.add("WITH");
	keywords.add("WITHOUT");
	keywords.add("WORK");
	keywords.add("WRITE");
	keywords.add("YEAR");
	keywords.add("ZONE");
    }
    /*
	 * Override to apply syntax highlighting after the document has been updated
	 */

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
	if (str.equals("{"))
	    str = addMatchingBrace(offset);
	super.insertString(offset, str, a);
	processChangedLines(offset, str.length());
    }
    /*
	 * Override to apply syntax highlighting after the document has been updated
	 */

    public void remove(int offset, int length) throws BadLocationException {
	super.remove(offset, length);
	processChangedLines(offset, 0);
    }
    /*
	 * Determine how many lines have been changed,
	 * then apply highlighting to each line
	 */

    public void processChangedLines(int offset, int length) throws BadLocationException {
	String content = doc.getText(0, doc.getLength());
	// The lines affected by the latest document update
	int startLine = rootElement.getElementIndex(offset);
	int endLine = rootElement.getElementIndex(offset + length);
	// Make sure all comment lines prior to the start line are commented
	// and determine if the start line is still in a multi line comment
	setMultiLineComment(commentLinesBefore(content, startLine));
	// Do the actual highlighting
	for (int i = startLine; i <= endLine; i++) {
	    applyHighlighting(content, i);
	}
	// Resolve highlighting to the next end multi line delimiter
	if (isMultiLineComment())
	    commentLinesAfter(content, endLine);
	else
	    highlightLinesAfter(content, endLine);
    }
    /*
	 * Highlight lines when a multi line comment is still 'open'
	 * (ie. matching end delimiter has not yet been encountered)
	 */

    private boolean commentLinesBefore(String content, int line) {
	int offset = rootElement.getElement(line).getStartOffset();
	// Start of comment not found, nothing to do
	int startDelimiter = lastIndexOf(content, getStartDelimiter(), offset - 2);
	if (startDelimiter < 0)
	    return false;
	// Matching start/end of comment found, nothing to do
	int endDelimiter = indexOf(content, getEndDelimiter(), startDelimiter);
	if (endDelimiter < offset & endDelimiter != -1)
	    return false;
	// End of comment not found, highlight the lines
	doc.setCharacterAttributes(startDelimiter, offset - startDelimiter + 1, comment, false);
	return true;
    }
    /*
	 * Highlight comment lines to matching end delimiter
	 */

    private void commentLinesAfter(String content, int line) {
	int offset = rootElement.getElement(line).getEndOffset();
	// End of comment not found, nothing to do
	int endDelimiter = indexOf(content, getEndDelimiter(), offset);
	if (endDelimiter < 0)
	    return;
	// Matching start/end of comment found, comment the lines
	int startDelimiter = lastIndexOf(content, getStartDelimiter(), endDelimiter);
	if (startDelimiter < 0 || startDelimiter <= offset) {
	    doc.setCharacterAttributes(offset, endDelimiter - offset + 1, comment, false);
	}
    }
    /*
	 * Highlight lines to start or end delimiter
	 */

    private void highlightLinesAfter(String content, int line) throws BadLocationException {
	int offset = rootElement.getElement(line).getEndOffset();
	// Start/End delimiter not found, nothing to do
	int startDelimiter = indexOf(content, getStartDelimiter(), offset);
	int endDelimiter = indexOf(content, getEndDelimiter(), offset);
	if (startDelimiter < 0)
	    startDelimiter = content.length();
	if (endDelimiter < 0)
	    endDelimiter = content.length();
	int delimiter = Math.min(startDelimiter, endDelimiter);
	if (delimiter < offset)
	    return;
	//	Start/End delimiter found, reapply highlighting
	int endLine = rootElement.getElementIndex(delimiter);
	for (int i = line + 1; i < endLine; i++) {
	    Element branch = rootElement.getElement(i);
	    Element leaf = doc.getCharacterElement(branch.getStartOffset());
	    AttributeSet as = leaf.getAttributes();
	    if (as.isEqual(comment))
		applyHighlighting(content, i);
	}
    }
    /*
	 * Parse the line to determine the appropriate highlighting
	 */

    private void applyHighlighting(String content, int line) throws BadLocationException {
	int startOffset = rootElement.getElement(line).getStartOffset();
	int endOffset = rootElement.getElement(line).getEndOffset() - 1;
	int lineLength = endOffset - startOffset;
	int contentLength = content.length();
	if (endOffset >= contentLength)
	    endOffset = contentLength - 1;
	// check for multi line comments
	// (always set the comment attribute for the entire line)
	if (endingMultiLineComment(content, startOffset, endOffset) || isMultiLineComment() || startingMultiLineComment(content, startOffset, endOffset)) {
	    doc.setCharacterAttributes(startOffset, endOffset - startOffset + 1, comment, false);
	    return;
	}
	// set normal attributes for the line
	doc.setCharacterAttributes(startOffset, lineLength, normal, true);
	// check for single line comment
	int index = content.indexOf(getSingleLineDelimiter(), startOffset);
	if ((index > -1) && (index < endOffset)) {
	    doc.setCharacterAttributes(index, endOffset - index + 1, comment, false);
	    endOffset = index - 1;
	}
	// check for tokens
	checkForTokens(content, startOffset, endOffset);
    }
    /*
	 * Does this line contain the start delimiter
	 */

    private boolean startingMultiLineComment(String content, int startOffset, int endOffset) throws BadLocationException {
	int index = indexOf(content, getStartDelimiter(), startOffset);
	if ((index < 0) || (index > endOffset))
	    return false;
	else {
	    setMultiLineComment(true);
	    return true;
	}
    }
    /*
	 * Does this line contain the end delimiter
	 */

    private boolean endingMultiLineComment(String content, int startOffset, int endOffset) throws BadLocationException {
	int index = indexOf(content, getEndDelimiter(), startOffset);
	if ((index < 0) || (index > endOffset))
	    return false;
	else {
	    setMultiLineComment(false);
	    return true;
	}
    }
    /*
	 * We have found a start delimiter
	 * and are still searching for the end delimiter
	 */

    private boolean isMultiLineComment() {
	return multiLineComment;
    }

    private void setMultiLineComment(boolean value) {
	multiLineComment = value;
    }
    /*
	 *	Parse the line for tokens to highlight
	 */

    private void checkForTokens(String content, int startOffset, int endOffset) {
	while (startOffset <= endOffset) {
	    // skip the delimiters to find the start of a new token
	    while (isDelimiter(content.substring(startOffset, startOffset + 1))) {
		if (startOffset < endOffset)
		    startOffset++;
		else
		    return;
	    }
	    // Extract and process the entire token
	    if (isQuoteDelimiter(content.substring(startOffset, startOffset + 1)))
		startOffset = getQuoteToken(content, startOffset, endOffset);
	    else
		startOffset = getOtherToken(content, startOffset, endOffset);
	}
    }
    /*
	 *
	 */

    private int getQuoteToken(String content, int startOffset, int endOffset) {
	String quoteDelimiter = content.substring(startOffset, startOffset + 1);
	String escapeString = getEscapeString(quoteDelimiter);
	int index;
	int endOfQuote = startOffset;
	// skip over the escape quotes in this quote
	index = content.indexOf(escapeString, endOfQuote + 1);
	while ((index > -1) && (index < endOffset)) {
	    endOfQuote = index + 1;
	    index = content.indexOf(escapeString, endOfQuote);
	}
	// now find the matching delimiter
	index = content.indexOf(quoteDelimiter, endOfQuote + 1);
	if ((index < 0) || (index > endOffset))
	    endOfQuote = endOffset;
	else
	    endOfQuote = index;
	doc.setCharacterAttributes(startOffset, endOfQuote - startOffset + 1, quote, false);
	return endOfQuote + 1;
    }
    /*
	 *
	 */

    private int getOtherToken(String content, int startOffset, int endOffset) {
	int endOfToken = startOffset + 1;
	while (endOfToken <= endOffset) {
	    if (isDelimiter(content.substring(endOfToken, endOfToken + 1)))
		break;
	    endOfToken++;
	}
	String token = content.substring(startOffset, endOfToken);
	if (isKeyword(token)) {
	    doc.setCharacterAttributes(startOffset, endOfToken - startOffset, keyword, false);
	}
	return endOfToken + 1;
    }
    /*
	 * Assume the needle will the found at the start/end of the line
	 */

    private int indexOf(String content, String needle, int offset) {
	int index;
	while ((index = content.indexOf(needle, offset)) != -1) {
	    String text = getLine(content, index).trim();
	    if (text.startsWith(needle) || text.endsWith(needle))
		break;
	    else
		offset = index + 1;
	}
	return index;
    }
    /*
	 * Assume the needle will the found at the start/end of the line
	 */

    private int lastIndexOf(String content, String needle, int offset) {
	int index;
	while ((index = content.lastIndexOf(needle, offset)) != -1) {
	    String text = getLine(content, index).trim();
	    if (text.startsWith(needle) || text.endsWith(needle))
		break;
	    else
		offset = index - 1;
	}
	return index;
    }

    private String getLine(String content, int offset) {
	int line = rootElement.getElementIndex(offset);
	Element lineElement = rootElement.getElement(line);
	int start = lineElement.getStartOffset();
	int end = lineElement.getEndOffset();
	return content.substring(start, end - 1);
    }

    protected boolean isDelimiter(String character) {
	String operands = ";:{}()[]+-/%<=>!&|^~*";
	if (Character.isWhitespace(character.charAt(0)) || operands.indexOf(character) != -1)
	    return true;
	else
	    return false;
    }
    protected boolean isQuoteDelimiter(String character) {
	String quoteDelimiters = "\"'";
	if (quoteDelimiters.indexOf(character) < 0)
	    return false;
	else
	    return true;
    }

    protected boolean isKeyword(String token) {
	return keywords.contains(token.toUpperCase());
    }

    protected String getStartDelimiter() {
	return "/*";
    }
    protected String getEndDelimiter() {
	return "*/";
    }
    protected String getSingleLineDelimiter() {
	return "--";
    }
    protected String getEscapeString(String quoteDelimiter) {
	return "\\" + quoteDelimiter;
    }
    protected String addMatchingBrace(int offset) throws BadLocationException {
	StringBuffer whiteSpace = new StringBuffer();
	int line = rootElement.getElementIndex(offset);
	int i = rootElement.getElement(line).getStartOffset();
	while (true) {
	    String temp = doc.getText(i, 1);
	    if (temp.equals(" ") || temp.equals("\t")) {
		whiteSpace.append(temp);
		i++;
	    } else
		break;
	}
	return "{\n" + whiteSpace.toString() + "\t\n" + whiteSpace.toString() + "}";
    }

    public static void main(String[] a) {
	EditorKit editorKit = new StyledEditorKit() {

		public Document createDefaultDocument() {
		    return new SyntaxDocument();
		}

	    }
	;
	final JEditorPane edit = new JEditorPane();
	edit.setEditorKitForContentType("text/java", editorKit);
	edit.setContentType("text/java");
	//		edit.setEditorKit(new StyledEditorKit());
	//		edit.setDocument(new SyntaxDocument());
	JButton button = new JButton("Load SyntaxDocument.java");
	button.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  try {
				      FileInputStream fis = new FileInputStream("SyntaxDocument.java");
				      //					FileInputStream fis = new FileInputStream( "C:\\Java\\jdk1.4.1\\src\\javax\\swing\\JComponent.java" );
				      edit.read(fis, null);
				      edit.requestFocus();
				  } catch (Exception e2) {

				  }
			      }

			  }
	);
	JFrame frame = new JFrame("Syntax Highlighting");
	frame.getContentPane().add(new JScrollPane(edit));
	frame.getContentPane().add(button, BorderLayout.SOUTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800, 300);
	frame.setVisible(true);
    }

}
