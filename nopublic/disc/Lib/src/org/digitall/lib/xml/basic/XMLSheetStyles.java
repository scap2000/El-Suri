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
 * XMLSheetStyles.java
 *
 * */
package org.digitall.lib.xml.basic;

public class XMLSheetStyles {

    public static String getStyles() {
	StringBuffer seccion3 = new StringBuffer();
	seccion3.append("<Styles>\n");
	seccion3.append("   <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
	seccion3.append("      <Alignment ss:Vertical=\"Bottom\"/>\n");
	seccion3.append("     <Borders/>\n");
	seccion3.append("     <Font/>\n");
	seccion3.append("     <Interior/>\n");
	seccion3.append("     <NumberFormat/>\n");
	seccion3.append("     <Protection/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"s16\" ss:Name=\"Hipervinculo\">\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Color=\"#0000FF\" ss:Underline=\"Single\"/>\n");
	seccion3.append("  </Style>\n");	
	//----------------------  COVER STYLES  ----------------------------
     seccion3.append("   <Style ss:ID=\"m26445296\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Eras Medium ITC\" x:Family=\"Swiss\" ss:Color=\"#FF0000\" ss:Bold=\"1\"/>\n");
    seccion3.append("     <Interior/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444892\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Verdana\" x:Family=\"Swiss\" ss:Color=\"#808080\"/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444902\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Verdana\" x:Family=\"Swiss\" ss:Color=\"#808080\"/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444912\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Eras Medium ITC\" x:Family=\"Swiss\" ss:Color=\"#FF0000\" ss:Bold=\"1\"/>\n");
    seccion3.append("     <Interior/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444922\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"BankGothic Md BT\" x:Family=\"Swiss\" ss:Size=\"33\" ss:Color=\"#808080\" ss:Bold=\"1\"/>\n");
    seccion3.append("     <Interior/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444932\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Eras Demi ITC\" x:Family=\"Swiss\" ss:Size=\"16\" ss:Color=\"#808080\" ss:Bold=\"1\"/>\n");
    seccion3.append("     <Interior/>\n");
    seccion3.append("  </Style>\n");
    seccion3.append("   <Style ss:ID=\"m26444942\" ss:Parent=\"s16\">\n");
    seccion3.append("      <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\"/>\n");
    seccion3.append("     <Borders>\n");
    seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
    seccion3.append("     </Borders>\n");
    seccion3.append("     <Font ss:FontName=\"Eras Light ITC\" x:Family=\"Swiss\" ss:Size=\"12\" ss:Color=\"#0000FF\" ss:Bold=\"1\" ss:Underline=\"Single\"/>\n");
    seccion3.append("     <Interior/>\n");
    seccion3.append("  </Style>\n");
	
	
	//--------------------- COLOURS ---------------------//
	
	seccion3.append("  <Style ss:ID=\"amarillo\">\n");
	seccion3.append("     <Interior ss:Color=\"#FEFE8A\" ss:Pattern=\"Solid\"/>\n");
	seccion3.append("  </Style>\n");
	
	//--------------------- LETTER TYPES ---------------------//
	
	seccion3.append("  <Style ss:ID=\"sDefaultLetterLeft\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"sDefaultLetterRight\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Top\"/>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"sDefaultLetterCenter\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Top\"/>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	
	//--------------------- MONEY TYPES ---------------------//
	 seccion3.append("  <Style ss:ID=\"sArgentinianMoney\">\n");
	 seccion3.append("     <NumberFormat ss:Format=\"[$$-2C0A]\\ #,##0.00\" />\n");
	 seccion3.append("  </Style>\n");
	 
	seccion3.append("  <Style ss:ID=\"sAmericanMoney\">\n");
	seccion3.append("     <NumberFormat ss:Format=\"\\U\\$\\D\\ 0.00\"/>\n");
	seccion3.append("  </Style>\n");
		
	seccion3.append("  <Style ss:ID=\"sBorderArgMoney\" ss:Parent=\"sArgentinianMoney\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"sBorderUsaMoney\" ss:Parent=\"sAmericanMoney\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\"/>\n");
	seccion3.append("  </Style>\n");
	
	//--------------------------------
	
	seccion3.append("  <Style ss:ID=\"sLetterCenter\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" />\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"monedaArgAmarilla\" ss:Parent=\"amarillo\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <NumberFormat ss:Format=\"[$$-2C0A]\\ #,##0.00\" />\n");
	seccion3.append("  </Style>\n");	
	
	seccion3.append("  <Style ss:ID=\"monedaArg\">\n");
	seccion3.append("     <NumberFormat ss:Format=\"[$$-2C0A]\\ #,##0.00\" />\n");
	seccion3.append("  </Style>\n");  
	
	seccion3.append("  <Style ss:ID=\"firma\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"fecha\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" />\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"encabezado\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("     <Interior ss:Color=\"#c6c6c6\" ss:Pattern=\"Solid\"/>\n");
	seccion3.append("  </Style>\n");

	seccion3.append("  <Style ss:ID=\"totalText\" ss:Parent=\"amarillo\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"totalNumberYellow\" ss:Parent=\"monedaArgAmarilla\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"totalNumber\" ss:Parent=\"monedaArg\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\"/>\n");
	seccion3.append("  </Style>\n");
	

	
	seccion3.append("  <Style ss:ID=\"s17\"  ss:Parent=\"s16\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"sBorderWithUnderlineBlue\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Color=\"#0000FF\" ss:Underline=\"Single\"/>\n");
	seccion3.append("  </Style>\n");
	
	seccion3.append("  <Style ss:ID=\"s18\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        </Borders>\n");
	seccion3.append("        <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s19\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s20\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s21\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s22\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s23\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\"/>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s24\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s25\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s26\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:Rotate=\"90\"/>\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s27\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Bottom\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s28\">\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s29\">\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s30\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s31\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\" ss:Rotate=\"90\"/>\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s32\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s33\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s34\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s36\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"1\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s35\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s37\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"s38\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Center\" ss:WrapText=\"1\" />\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font x:Family=\"Swiss\" ss:Bold=\"0\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("  <Style ss:ID=\"Normal8DateTime\">\n");
	seccion3.append("     <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Center\" ss:WrapText=\"1\"/>\n");
	seccion3.append("     <Borders>\n");
	seccion3.append("        <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("        <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"1\"/>\n");
	seccion3.append("     </Borders>\n");
	seccion3.append("     <Font ss:FontName=\"Times New Roman\" x:Family=\"Roman\" ss:Size=\"8\" />\n");
	seccion3.append("     <NumberFormat ss:Format=\"yyyy-mm-dd;@\"/>\n");
	seccion3.append("  </Style>\n");
	seccion3.append("</Styles>\n");
	return seccion3.toString();
    }

}
