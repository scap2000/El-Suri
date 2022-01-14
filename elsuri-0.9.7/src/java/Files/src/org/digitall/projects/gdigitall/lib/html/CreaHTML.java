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
 * CreaHTML.java
 *
 * */
package org.digitall.projects.gdigitall.lib.html;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.sql.ResultSet;

import java.util.EmptyStackException;

import javax.swing.JFrame;

import org.digitall.projects.gdigitall.lib.html.CargaTitulo;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class CreaHTML extends JFrame {
    static String idrendicion, database, Query = "";
    static int i = 1;

    public static boolean HTMLRendicion(String IDRendicion) {
        String Fecha = "";
        idrendicion = IDRendicion;
        try {
            /** Abro el Archivo */
            String htmlPath = "./rendicion.html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            Fecha = OP_Proced.FechaHora2(true, false);
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Informe</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Listado de Rendiciones</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR><BR>\n");
            if (idrendicion.length() > 0) {
                Query = "Select idrendicion, fecha, fechavenc, usuarios.apellido || ', ' || usuarios.nombre, psolicitada, prendida, saldo" + " from compras.rendicion, usuarios where usuarios.idusuario=compras.rendicion.idusuario and  idrendicion=" + idrendicion + " order by fecha, idrendicion";
            } else {
                Query = "Select idrendicion, fecha, fechavenc, usuarios.apellido || ', ' || usuarios.nombre, psolicitada, prendida, saldo" + " from compras.rendicion, usuarios where usuarios.idusuario=compras.rendicion.idusuario order by fecha, idrendicion";
            }
            ResultSet Resulx = OP_Proced.exConsulta(Query);
            //  Se escribe el encabezado del movimiento
            while (Resulx.next()) {
                htmlFile.write("<P ALIGN=CENTER><FONT size=4 FACE='Arial'><i><u>Rendicion Nro.: " + Resulx.getString(1) + "</i></u></FONT></P>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha Vto.</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=45% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Personal</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>($) Solicitado</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>($) Rendido</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>($) Saldo</B></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(2) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(3) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(4) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(5) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(6) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(7) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE><BR>\n");
                //  Se escribe el detalle del movimiento
                htmlFile.write("<P ALIGN=CENTER><FONT SIZE=4><U>Listado de Comprobantes</U></FONT></P>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Comercio</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=5%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Cbte.</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=2%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Tipo</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Nro. Cbte.</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha Vto.</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=8%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Total ($)</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=25%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Personal</B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=20%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Area</B></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                Query = "Select comercios.nombre, tipocomprobante, tipo, compras.comprobantes.nro, fecha, fechavenc, total, " + " usuarios.apellido || ', ' || usuarios.nombre  , areas.descripcion " + " from comercios, compras.comprobantes, usuarios, areas where compras.comprobantes.estado<>'*'" + " and usuarios.idusuario=compras.comprobantes.idpersonal and areas.idarea=compras.comprobantes.idarea" + " and comercios.idcomercio=compras.comprobantes.idcomercio and idrendicion=" + Resulx.getString(1) + "order by fecha";
                ResultSet Resuly = OP_Proced.exConsulta(Query);
                while (Resuly.next()) {
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(1) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(2) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(3) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(4) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(5) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(6) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(7) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(8) + "</FONT></P></TD>\n");
                    htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resuly.getString(9) + "</FONT></P></TD>\n");
                    htmlFile.write("</TR>\n");
                }
                htmlFile.write("</TABLE>\n");
                htmlFile.write("<BR><HR><BR>\n");
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLInfoCajaChica(String FechaIni, String FechaFin) {
        String fechaini = FechaIni;
        String fechafin = FechaFin;
        try {
            /** Abro el Archivo */
            String htmlPath = "/basedato/opsalta/Programas/Rendicion/informes/infocajachica.html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            // <TD WIDTH=50%><P ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Secretaria de Obras y Servicios Publicos</B></FONT></P></TD>      
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Informe de Caja Chica</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80%><P ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Informe de Caja Chica</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR><BR>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Nro. Orden</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Nro. Cbte.</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=32%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Proveedor</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=13%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Importe ($)</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=25%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>P/Contabilidad Impuntacion</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            Query = "Select fecha,nro,nombre,importe from compras.comprobantes, comercios where compras.comprobantes.estado<>'*'" + "  and compras.comprobantes.idcomercio=comercios.idcomercio and fecha between '" + fechaini + "' and '" + fechafin + "'" + " order by compras.comprobantes.fecha,compras.comprobantes.idrendicion,compras.comprobantes.idcomprobante";
            //      System.out.println(Query);
            ResultSet Resulx = OP_Proced.exConsulta(Query);
            int i = 1;
            while (Resulx.next()) {
                //        String idtema = Resulx.getString(2);
                //  Se escribe el encabezado del movimiento    
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + i + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(Resulx.getString(1), true) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(2) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(3) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + OP_Proced.DobleDec(Resulx.getString(4)) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                i = i + 1;
            }
            htmlFile.write("</TABLE>\n");
            String TotalImporte = OP_Proced.getCampo("Select sum(importe) from compras.comprobantes where estado<>'*' and fecha between '" + fechaini + "' and '" + fechafin + "'");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=32%><P ALIGN=right><FONT size=4 FACE='Arial'><B>Total ($):</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=13% height=45><P ALIGN=center><FONT size=4 FACE='Arial'><B>" + OP_Proced.DobleDec(TotalImporte) + "</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=27%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR><BR>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Fecha</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=15%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Nro. Retencion</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=40%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Proveedor</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Importe ($)</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            Query = "Select fecha,nroretencion,nombre,retencion from compras.comprobantes, comercios where compras.comprobantes.estado<>'*'" + "  and importe>200 and fecha between '" + fechaini + "' and '" + fechafin + "' and compras.comprobantes.idcomercio=comercios.idcomercio " + " order by compras.comprobantes.fecha,compras.comprobantes.idrendicion,compras.comprobantes.idcomprobante";
            //      System.out.println(Query);
            Resulx = OP_Proced.exConsulta(Query);
            while (Resulx.next()) {
                //        String idtema = Resulx.getString(2);
                //  Se escribe el encabezado del movimiento    
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(Resulx.getString(1), true) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(2) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + Resulx.getString(3) + "</FONT></P></TD>\n");
                htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>" + OP_Proced.DobleDec(Resulx.getString(4)) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                i = i + 1;
            }
            htmlFile.write("</TABLE>\n");
            String TotalRetencion = OP_Proced.getCampo("Select sum(retencion) from compras.comprobantes where estado<>'*' and importe>200 and fecha between '" + fechaini + "' and '" + fechafin + "'");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=30%><P ALIGN=left><FONT size=2 FACE='Arial'><B></B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=45%><P ALIGN=right><FONT size=4 FACE='Arial'><B>Total ($):</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=15% height=45><P ALIGN=center><FONT size=4 FACE='Arial'><B>" + OP_Proced.DobleDec(TotalRetencion) + "</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            String TotalRendicion = String.valueOf(Double.parseDouble(TotalImporte) - Double.parseDouble(TotalRetencion));
            String SaldoCaja = String.valueOf(Double.parseDouble(OP_Proced.getCampo("Select psolicitada from compras.rendicion where idrendicion=0")) - Double.parseDouble(TotalRendicion));
            htmlFile.write("<P ALIGN=left><FONT size=4 FACE='Arial'><u>Resumen:</u></FONT></P>\n");
            htmlFile.write("<TABLE WIDTH=50% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><P ALIGN=right><FONT size=2 FACE='Arial'><B>Saldo en Caja ($):</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=30%><P ALIGN=left><FONT size=2 FACE='Arial'><B>" + OP_Proced.DobleDec(SaldoCaja) + "</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=45%><P ALIGN=right><FONT size=2 FACE='Arial'><B>Presente Rendicion ($):</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=15%><P ALIGN=left><FONT size=2 FACE='Arial'><B>" + OP_Proced.DobleDec(TotalRendicion) + "</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=45%><P ALIGN=right><FONT size=2 FACE='Arial'><B>Total Asignado ($):</B></FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=15%><P ALIGN=left><FONT size=2 FACE='Arial'><B>" + OP_Proced.DobleDec(OP_Proced.getCampo("Select psolicitada from compras.rendicion where idrendicion=0")) + "</B></FONT></P></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            if (Double.parseDouble(SaldoCaja) < (Double.parseDouble(OP_Proced.getCampo("Select psolicitada from compras.rendicion where idrendicion=0")) * 0.3)) {
                htmlFile.write("<P ALIGN=left><FONT size=5 FACE='Arial' color=#ff0000><I><u>ATENCION!!!: </U></I><B>HA SUPERADO EL 70% DE CAJA CHICA, DEBE REALIZAR LAS RENDICIONES CORRESPONDIENTES</B></FONT></P>\n");
            } else if (Double.parseDouble(SaldoCaja) < (Double.parseDouble(OP_Proced.getCampo("Select psolicitada from compras.rendicion where idrendicion=0")) * 0.5)) {
                htmlFile.write("<P ALIGN=left><FONT size=5 FACE='Arial' color=#0000ff><I><u>ATENCION!!!: </U></I><B>HA SUPERADO EL 50% DE CAJA CHICA, DEBE SOLICITAR MAS IMPORTE</B></FONT></P>\n");
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLCbteRendicion(String IDRendicion) {
        String Fecha = "";
        idrendicion = IDRendicion;
        try {
            /** Abro el Archivo */
            String htmlPath = "/basedato/opsalta/Programas/Rendicion/informes/cbterendicion.html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            Fecha = OP_Proced.FechaHora2(true, false);
            // <TD WIDTH=50%><P ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Secretaria de Obras y Servicios Publicos</B></FONT></P></TD>      
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Informe</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("\n");
            if (idrendicion.length() > 0) {
                Query = "Select idrendicion, fecha, fechavenc, psolicitada, usuarios.apellido || ', ' || usuarios.nombre, areas.descripcion, concepto " + " from compras.rendicion, usuarios, areas where usuarios.idarea=areas.idarea and usuarios.idusuario=compras.rendicion.idusuario and  idrendicion=" + idrendicion + " order by fecha, idrendicion";
            }
            //      System.out.println(Query);
            ResultSet Resulx = OP_Proced.exConsulta(Query);
            while (Resulx.next()) {
                //        String idtema = Resulx.getString(2);
                //  Se escribe el encabezado del movimiento
                htmlFile.write("<TABLE WIDTH=70% align=center BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0 bordercolor=#FFFFFF>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD colspan=2 WIDTH=3% height=70 style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=CENTER><FONT size=4 FACE='Arial'><u><B>Comprobante de Solicitud de Importe</B><u></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=20% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Rendicion Nro.: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=80% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(1) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Fecha: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(2) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Fecha Vto.: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(3) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Imp. Solicitado ($): </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + OP_Proced.DobleDec(Resulx.getString(4)) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Personal: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(5) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Area: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(6) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=RIGHT><FONT size=2 FACE='Arial'><B>Concepto: </B></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=LEFT><FONT size=2 FACE='Arial'>" + Resulx.getString(7) + "</FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% height=80 style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=CENTER><FONT size=2 FACE='Arial'></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=CENTER><FONT size=2 FACE='Arial'></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TD WIDTH=2% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=CENTER><FONT size=2 FACE='Arial'></FONT></P></TD>\n");
                htmlFile.write("<TD WIDTH=10% style='border-top:none;border-bottom:none;border-left:none;border-right:none'><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Firma</B></FONT></P></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE>\n");
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLBachesCompleto(String Consulta) {
        //    String fechaini=FechaIni;
        //   String fechafin=FechaFin;
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRuta() + OP_Proced.getSeparador() + "baches_completo.html";
            //      System.out.println(htmlPath);
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Listado de Baches Existentes</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80%><P ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Listado de Baches Existentes</B></FONT><BR>\n");
            htmlFile.write("<FONT size=3>Listado de Baches Existentes</FONT></P></TD>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            //////////////////////////////////////////////Inicio de Tabla de Datos
            htmlFile.write("<P ALIGN=CENTER><FONT size=5>" + CargaTitulo.getCampo() + "</FONT></P><BR>\n");
            Query = "Select idperimetro,nombre from rurbano.perimetros";
            //System.out.println(Query);
            ResultSet Resulx = OP_Proced.exConsulta(Query);
            while (Resulx.next()) {
                Query = "Select * from (" + Consulta + ") as temp where idperimetro=" + Resulx.getString(1);
                /*rurbano.baches,calles,rurbano.detperimetros"
               +" where calles.idcalle=baches.idcalle and  baches.idcalle=detperimetros.idcalle and baches.estado<>'*' and idperimetro="+ Resulx.getString(1);*/
                //System.out.println(Query);     
                ResultSet Resuly = OP_Proced.exConsulta(Query);
                if (Resuly.next()) {
                    htmlFile.write("<P ALIGN=CENTER><FONT size=2 FACE='Arial'>Seccion: " + Resulx.getString(2) + "</FONT></P>\n");
                    htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Fecha</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Calle</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Nro.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=13% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>c/Bca. Calle (Esq.)</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Largo</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Ancho</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Sup.(m2)</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Pavimento</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>S.Juntas</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Tipo</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>P.c/Raja.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>P.Dilat.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>P.Comp.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>P.Parc.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Est.de Agua</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Alcanta.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>C.Cuneta</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Peligro.</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Observaciones</B></FONT></TD>\n");
                    htmlFile.write("<TD WIDTH=25% ALIGN=CENTER><FONT size=1 FACE='Arial'><B>Inspector</B></FONT></TD>\n");
                    htmlFile.write("</TR>\n");
                }
                Resuly.previous();
                while (Resuly.next()) {
                    if (CargaTitulo.getResaltar().equals("s")) {
                        if (Resuly.getString(21).equals("s")) {
                            htmlFile.write("<TR bgcolor=#FCFCD8>\n");
                        } else {
                            htmlFile.write("<TR>\n");
                        }
                    } else {
                        htmlFile.write("<TR>\n");
                    }
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + OP_Proced.Fecha2(Resuly.getString(2), true) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(3) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(4) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(5) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(7) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(8) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(9) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(11) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(12).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(28) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(14).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(15).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(16).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(17).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(18).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(19) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(20).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(21).replace('n', ' ') + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(22) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resuly.getString(23) + "</FONT></TD>\n");
                    //htmlFile.write("<TD  ALIGN=CENTER><FONT size=1 FACE='Arial'>"+ Resuly.getString(27) +"</FONT></TD>\n");       
                    htmlFile.write("</TR>\n");
                }
                htmlFile.write("</TABLE>\n");
            }
            htmlFile.write("<P ALIGN=LEFT><FONT size=4><u>Tipos de Baches</u></FONT>\n");
            Query = "Select * from rurbano.tipobache where estado<>'*'";
            ResultSet Resulxx = OP_Proced.exConsulta(Query);
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            while (Resulxx.next()) {
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=50%>" + Resulxx.getString(1) + ": " + Resulxx.getString(2) + "</FONT></TD>\n");
                if (Resulxx.next()) {
                    htmlFile.write("<TD WIDTH=50% ALIGN=CENTER><FONT size=1 FACE='Arial'>" + Resulxx.getString(1) + ": " + Resulxx.getString(2) + "</FONT></TD>\n");
                } else {
                    htmlFile.write("<TD WIDTH=50% ALIGN=CENTER><FONT size=1 FACE='Arial'>-</FONT></TD>\n");
                }
                htmlFile.write("</TR>\n");
            }
            htmlFile.write("</TABLE>\n");
            htmlFile.write("<P ALIGN=LEFT><FONT size=4><u>Resumen:</u></FONT>\n");
            htmlFile.write("<TABLE WIDTH=90% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            ResultSet Resulxy = OP_Proced.exConsulta("Select count(*) from (" + Consulta + ") as temp where reparado='s'");
            htmlFile.write("<TR>\n");
            if (Resulxy.next()) {
                htmlFile.write("<TD WIDTH=50%>Cant. de Baches Reparados: <B>" + Resulxy.getString(1) + "</B></FONT></TD>\n");
            }
            Resulxy = OP_Proced.exConsulta("Select count(*) from (" + Consulta + ") as temp");
            htmlFile.write("<TR>\n");
            if (Resulxy.next()) {
                htmlFile.write("<TD WIDTH=50%>Cant. de Baches Encontrados: <B>" + Resulxy.getString(1) + "</B></FONT></TD>\n");
            }
            Resulxy = OP_Proced.exConsulta("Select count(*) from (" + Consulta + ") as temp where peligroso='s'");
            htmlFile.write("<TR>\n");
            if (Resulxy.next()) {
                htmlFile.write("<TD WIDTH=50%>Cant. de Baches Peligrosos: <B>" + Resulxy.getString(1) + "</B></FONT></TD>\n");
            }
            Resulxx = OP_Proced.exConsulta("Select * from rurbano.tipobache where estado<>'*'");
            while (Resulxx.next()) {
                htmlFile.write("<TR>\n");
                ResultSet Resulxz = OP_Proced.exConsulta("Select count(*) from (" + Consulta + ") as temp where idtipobache='" + Resulxx.getString(1) + "'");
                //        System.out.println("Select count(*) from ("+ Consulta +") as temp where tipopavimento='"+ Resulxx.getString(1) +"'");
                if (Resulxz.next()) {
                    htmlFile.write("<TD WIDTH=50%>Cant. de Baches Encontrados del Tipo (" + Resulxx.getString(1) + "): <B>" + Resulxz.getString(1) + "</B></FONT></TD>\n");
                }
                htmlFile.write("</TR>\n");
            }
            Resulxy = OP_Proced.exConsulta("Select sum(superficie) from (" + Consulta + ") as temp");
            htmlFile.write("<TR>\n");
            if (Resulxy.next()) {
                htmlFile.write("<TD WIDTH=50%>Total de Superficie (m²) de Baches: <B>" + OP_Proced.DobleDec(Resulxy.getString(1)) + "</B></FONT></TD>\n");
            }
            htmlFile.write("</TABLE>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            //Proced.Mensaje(x.getMessage(),"");
            x.printStackTrace();
            return false;
        }
    }
    /// LISTADO COMPLETO DEL CONSUMO DE COMBUSTIBLE POR AREA
    ///LISTADO COMPLETO DEL CONSUMO DE COMBUSTIBLE POR AUTO EN UN RANGO DE FECHA DEFINIDO POR EL USUARIO
    ///

    public static boolean HTMLLCompletoUrb(String Expte, String nombre, String Campos, int TCampo, String[] VEtiquetas, int[] VIndices) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Listado de Permisos por Aperturas</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Factibilidad Localizacion</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            /*if (Desde.length()>0)
      {
        htmlFile.write("<P ALIGN=CENTER><FONT size=3>Periodo: <B>" + Proced.Fecha(Desde,true) + "</B> Hasta <B>"+ Proced.Fecha(Hasta,true) +"</B></FONT></P>\n");  
      }*/
            //      int a=0;
            //////////////////////////////////////////////Inicio de Tabla de Datos
            ResultSet Reg = OP_Proced.exConsulta("Select " + Campos + "'' from sopsalta.zzfactibilidad_localizacion where clave_factibilidad='" + Expte + "'");
            while (Reg.next()) {
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' color='#ffffff'><B>Datos del Expediente</B></FONT></TD></TR>\n");
                for (int i = 0; i < TCampo; i++) {
                    htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>" + VEtiquetas[VIndices[i]] + ": " + Reg.getString(i + 1) + "</B></FONT></TD></TR>\n");
                }
                /*htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>clave_factibilidad: "+ Reg.getString(2) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>anio: "+ Reg.getString(3) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>expte_anexos: "+ Reg.getString(4) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>barrio: "+ Reg.getString(5) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>ubicacion: "+ Reg.getString(6) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>dominio: "+ Reg.getString(7) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>profesional: "+ Reg.getString(8) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>superficie: "+ Reg.getString(9) +"</B></FONT></TD></TR>\n");        
              htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>factibilidad_72hs: "+ Reg.getString(10) +"</B></FONT></TD>*/
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE><BR>\n");
            }
            Reg = OP_Proced.exConsulta("Select * from sopsalta.zzvisado_plano_conjunto where clave_conjunto='" + Expte + "'");
            while (Reg.next()) {
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' color='#ffffff'><B>Visado Plano Conjunto</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Nro: " + Reg.getString(1) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Expte_Anexo: " + Reg.getString(2) + "</B></FONT></TD></TR>\n");
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>clave_conjunto: "+ Reg.getString(3) +"</B></FONT></TD></TR>\n");        
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>anio_clave: "+ Reg.getString(4) +"</B></FONT></TD></TR>\n");        
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Cedula Parcelaria: " + Reg.getString(5) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Certificado no inundabilidad: " + Reg.getString(6) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Factibilidad aguas cloaca: " + Reg.getString(7) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Factibilidad energia electrica: " + Reg.getString(8) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Factibilidad gas: " + Reg.getString(9) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano mensura aprob: " + Reg.getString(10) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Ordenanza excepcion: " + Reg.getString(11) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano conjunto: " + Reg.getString(12) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Notificacion: " + Reg.getString(13) + "</B></FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR>\n");
            }
            Reg = OP_Proced.exConsulta("Select * from sopsalta.zzvisado_plano_infraestructura where clave_visado='" + Expte + "'");
            while (Reg.next()) {
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' Color='#ffffff'><B>Visado Plano Infraestructura</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Nro: " + Reg.getString(1) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Expte Anexos: " + Reg.getString(2) + "</B></FONT></TD></TR>\n");
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>clave_visado: "+ Reg.getString(3) +"</B></FONT></TD></TR>\n");        
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>anio_clave: "+ Reg.getString(4) +"</B></FONT></TD></TR>\n");        
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano redvial: " + Reg.getString(5) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano alumbrado_publico: " + Reg.getString(6) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano forestacion: " + Reg.getString(7) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano obra: " + Reg.getString(8) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano electrico: " + Reg.getString(9) + "</B></FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR>\n");
            }
            Reg = OP_Proced.exConsulta("Select * from sopsalta.zzaprobacion_urbanizacion where clave_aprobacion='" + Expte + "'");
            while (Reg.next()) {
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' Color='#ffffff'><B>Aprobacion Urbanizacion</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Nro: " + Reg.getString(1) + "</B></FONT></TD></TR>\n");
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>clave_aprobacion: "+ Reg.getString(2) +"</B></FONT></TD></TR>\n");        
                //htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>anio_clave: "+ Reg.getString(3) +"</B></FONT></TD></TR>\n");        
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Expte Anexos: " + Reg.getString(4) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano alumbrado publico: " + Reg.getString(5) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano obra: " + Reg.getString(6) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano electrico: " + Reg.getString(7) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Pago derecho alumbrado: " + Reg.getString(8) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Pago derecho construccion: " + Reg.getString(9) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Ordenanza donacion calle: " + Reg.getString(10) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Final obra: " + Reg.getString(11) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Sanciones: " + Reg.getString(12) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Habilitacion parcial: " + Reg.getString(13) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano estructura: " + Reg.getString(14) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Observaciones: " + Reg.getString(15) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Plano aprobado: " + Reg.getString(16) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Decretos: " + Reg.getString(17) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Estudio suelo: " + Reg.getString(18) + "</B></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' ><B>Nro plano aprobado: " + Reg.getString(19) + "</B></FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR>\n");
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLLCompletoUrbDominio(String Filtro, String nombre) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Listado de Permisos por Aperturas</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Factibilidad Localizacion</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            /*if (Desde.length()>0)
      {
        htmlFile.write("<P ALIGN=CENTER><FONT size=3>Periodo: <B>" + Proced.Fecha(Desde,true) + "</B> Hasta <B>"+ Proced.Fecha(Hasta,true) +"</B></FONT></P>\n");  
      }*/
            //      int a=0;
            //////////////////////////////////////////////Inicio de Tabla de Datos
            ResultSet Reg = OP_Proced.exConsulta("Select distinct dominio from sopsalta.zzfactibilidad_localizacion");
            while (Reg.next()) {
                if (!OP_Proced.getCampo("Select count(*) from sopsalta.zzfactibilidad_localizacion where dominio='" + Reg.getString(1) + "' " + Filtro).equals("0")) {
                    htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                    htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                    htmlFile.write("<TD  ALIGN=LEFT colspan=9><FONT size=2 FACE='Arial' color='#ffffff'><B>Dominio: " + Reg.getString(1) + "</B></FONT></TD></TR>\n");
                    //htmlFile.write("<TR><TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' ><B>nro</B></FONT></TD>\n");        
                    htmlFile.write("<TD  ALIGN=LEFT width=10%><FONT size=2 FACE='Arial' ><B>Nº Expedientes</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' ><B>Año</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=15%><FONT size=2 FACE='Arial' ><B>Nº Exptes. Anexos</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' ><B>Barrio</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' ><B>Ubicacion</B></FONT></TD>\n");
                    //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' ><B>dominio</B></FONT></TD>\n");        
                    htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' ><B>Profesional</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' ><B>Sup.(m²)</B></FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' ><B>Fact.(72hs)</B></FONT></TD></TR>\n");
                    ResultSet Reg1 = OP_Proced.exConsulta("Select * from sopsalta.zzfactibilidad_localizacion where dominio='" + Reg.getString(1) + "' " + Filtro);
                    while (Reg1.next()) {
                        //            htmlFile.write("<TR><TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >"+ Reg1.getString(1) +"</FONT></TD>\n");        
                        htmlFile.write("<TD  ALIGN=LEFT width=10%><FONT size=2 FACE='Arial' >" + Reg1.getString(2) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' >" + Reg1.getString(3) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=15%><FONT size=2 FACE='Arial' >" + Reg1.getString(4) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' >" + Reg1.getString(5) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' >" + Reg1.getString(6) + "</FONT></TD>\n");
                        //          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >"+ Reg1.getString(7) +"</FONT></TD>\n");        
                        htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' >" + Reg1.getString(8) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' >" + Reg1.getString(9) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' >" + Reg1.getString(10) + "</FONT></TD></TR>\n");
                    }
                }
                htmlFile.write("</TABLE><BR>\n");
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLVisPConjunto(String nombre, String idurban) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Datos del Visado de Plano Conjunto</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Datos del Visado de Plano Conjunto</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            /*if (Desde.length()>0)
      {
        htmlFile.write("<P ALIGN=CENTER><FONT size=3>Periodo: <B>" + Proced.Fecha(Desde,true) + "</B> Hasta <B>"+ Proced.Fecha(Hasta,true) +"</B></FONT></P>\n");  
      }*/
            //////////////////////////////////////////////Inicio de Tabla de Datos
            String Consulta = "SELECT * from urban.vispconjunto where idurban=" + idurban;
            // System.out.println(Consulta);                 
            ResultSet Resuly = OP_Proced.exConsulta(Consulta);
            while (Resuly.next()) {
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos Principal</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Nº Orden: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(1) + "</FONT></TD>\n");
                /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvispconj
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >nordexcepcion
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >cedulaparce
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >certlocaliz

htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fplanimetria
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >pplanimetria
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >tipomensura
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fmensura
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >pmensura

htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fpconjunto
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >pconjunto
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvencpc
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fcertinundab
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ncertinundab
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvenccertinundab

htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ffactagua
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ncertfactagua
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvencfactagua
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ffactenergelec
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ncertfactenergelec
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvencfactenergelec

htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ffactcloaca
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ncertfactcloaca
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvencfactcloaca
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ffactgas
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >ncertfactgas
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >fvencfactgas
htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >observacion*/
                htmlFile.write("</TABLE><BR></TR>\n");
                //Catastro
                String Q = "Select catastro,secciones.nombre as seccion,nmanzana||lmanzana as manzana,nparcela||lparcela as parcela," + "propietarios.nombre as porpietario" + " from cepax.catastros,cepax.parcelas,secciones,cepax.catsxprops,cepax.propietarios" + " where catastros.idparcela=parcelas.idparcela and parcelas.idseccion=secciones.nombre " + " and catastros.idcatastro=" + Resuly.getString(5) + " and catastros.idcatastro=catsxprops.idcatastro " + " and catsxprops.idpropietario=propietarios.idpropietario";
                //System.out.println(Q);                 
                ResultSet Reg = OP_Proced.exConsulta(Q);
                if (Reg.next()) {
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                    htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                    htmlFile.write("<TD  ALIGN=LEFT colspan=6><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Catastro</B></FONT></TD></TR>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<TD  ALIGN=LEFT WIDTH=15%><FONT size=2 FACE='Arial' >Nº Catastro: </FONT><FONT size=3 FACE='Arial' >" + Reg.getString(1) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT WIDTH=15%><FONT size=2 FACE='Arial' >Seccion: </FONT><FONT size=3 FACE='Arial' >" + Reg.getString(2) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT WIDTH=15%><FONT size=2 FACE='Arial' >Manzana: </FONT><FONT size=3 FACE='Arial' >" + Reg.getString(3) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT WIDTH=15%><FONT size=2 FACE='Arial' >Parcela: </FONT><FONT size=3 FACE='Arial' >" + Reg.getString(4) + "</FONT></TD>\n");
                }
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=30%><FONT size=2 FACE='Arial' >Domicilio: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(6) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=10%><FONT size=2 FACE='Arial' >Nº: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(7) + "</FONT></TD>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Propietario
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Propietario del Catastro</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >Propietario: </FONT><FONT size=3 FACE='Arial' >" + Reg.getString(5) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Iniciador
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Iniciador del Expediente</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >Iniciador: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(8) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Responsable Obra
                htmlFile.write("<TR>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Responsable de Obra</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >Responsable: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(9) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Tipo Conexion
                htmlFile.write("<TR>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=8><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Tipo de Conexion</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Agua: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(21)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Nº Tramite: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(22) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Cloaca: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(23)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Nº Tramite: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(24) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Gas: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(25)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Nº Tramite: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(26) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Reparado: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(27)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' >Fecha Reparado: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(28) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Calzada
                htmlFile.write("<TR>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=7><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Tipo De Calzada</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=10%><FONT size=2 FACE='Arial' >Asfalto: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(10)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=10%><FONT size=2 FACE='Arial' >HºAº: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(11)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=10%><FONT size=2 FACE='Arial' >Adoquin: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(12)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=10%><FONT size=2 FACE='Arial' >Tierra: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(13)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >Fecha Rtgro: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.Fecha2(Resuly.getString(14), true) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >Apertura(m²): </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(15) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >SubTotal($): </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.DobleDec(Resuly.getString(16)) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Vereda
                htmlFile.write("<TR>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos de la Vereda</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >Vereda: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.TransformaCaracter(Resuly.getString(17)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=30%><FONT size=2 FACE='Arial' >Fecha Rtgro: </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.Fecha2(Resuly.getString(18), true) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=30%><FONT size=2 FACE='Arial' >Apertura(m²): </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(19) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >SubTotal($): </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.DobleDec(Resuly.getString(20)) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
                //Deposito
                htmlFile.write("<TR>\n");
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' Color='#ffffff'><B>Datos del Fondo de Garantia</B></FONT></TD></TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT colspan=4><FONT size=2 FACE='Arial' >Observaciones: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(31) + "</FONT></TD></tr>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >Nº Boleta: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(29) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=30%><FONT size=2 FACE='Arial' >Fecha Boleta: </FONT><FONT size=3 FACE='Arial' >" + Resuly.getString(30) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=30%><FONT size=2 FACE='Arial' >Derecho Apertura($): </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.DobleDec(Resuly.getString(32)) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20%><FONT size=2 FACE='Arial' >Fondo Garantia($): </FONT><FONT size=3 FACE='Arial' >" + OP_Proced.DobleDec(Resuly.getString(33)) + "</FONT></TD></TR>\n");
                htmlFile.write("</TABLE><BR></TR>\n");
            }
            htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLNotificacion_Urban(String idurban, String idnotificacion, String nombre) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + idnotificacion + ".html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            ResultSet Reg = OP_Proced.exConsulta("Select * from urban.urbanizacion where idurban=" + idurban);
            ResultSet Reg1 = OP_Proced.exConsulta("Select * from urban.notificaciones where idnotif=" + idnotificacion);
            if (Reg.next() & Reg1.next()) {
                ResultSet Reg2 = OP_Proced.exConsulta("Select * from cepax.propietarios where idpropietario=" + Reg.getString(24));
                if (Reg2.next()) {
                    htmlFile.write("<HTML>\n");
                    htmlFile.write("<HEAD>\n");
                    htmlFile.write("<TITLE>Cedula de Notificacion</TITLE>\n");
                    htmlFile.write("</HEAD>\n");
                    htmlFile.write("<BODY LANG='es-ES'>\n");
                    htmlFile.write("<table align='center' width=100% cellspacing='0' cellpadding='0' border=0>\n");
                    htmlFile.write("<tr>\n");
                    htmlFile.write("<td align='center' width=5% valign=top><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60 height=70></td>\n");
                    htmlFile.write("<td align='center' width=90% height=60><p><font face='arial' size=6 color='#14326c'><b>Cedula de Notificacion</b></font>\n");
                    htmlFile.write("<br><font face='arial' size='2' color='#14326c'>Municipio de Salta - Rep&uacute;blica Argentina</font></p></td>\n");
                    htmlFile.write("<td align='center' width=5% valign=top><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60 height=70></td></tr>\n");
                    htmlFile.write("</Table>\n");
                    htmlFile.write("<BR>\n");
                    htmlFile.write("<table border=0 width=100%>\n");
                    htmlFile.write("<tr>\n");
                    htmlFile.write("<td width=17%>REFERENCIA: Expediente Nº</td>\n");
                    htmlFile.write("<td width=6%>" + Reg.getString(10) + "</td>\n");
                    htmlFile.write("<td width=67%></td>\n");
                    htmlFile.write("<td width=2%>Salta,</TD>\n");
                    htmlFile.write("<TD width=2%>" + Reg1.getString(3).substring(8, 10) + "</TD>\n");
                    htmlFile.write("<TD width=2%>de </TD>\n");
                    htmlFile.write("<TD width=2%>" + OP_Proced.getCampo("Select nombre from mes where id=" + Reg1.getString(3).substring(5, 7)) + "</TD>\n");
                    htmlFile.write("<TD width=2%>de </TD>\n");
                    htmlFile.write("<TD width=32%>" + Reg1.getString(3).substring(0, 4) + ".-</TD>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("</table>\n");
                    htmlFile.write("<BR><BR>\n");
                    htmlFile.write("<table border=0 width=30%>\n");
                    htmlFile.write("<tr><td>Sr./a:</td></tr>\n");
                    htmlFile.write("<tr><td>" + Reg2.getString(5) + "</td></tr>\n");
                    htmlFile.write("<tr><td>--</td></tr>\n");
                    htmlFile.write("<tr><td>__________S__________/__________D__________</td></tr>\n");
                    htmlFile.write("</table>\n");
                    htmlFile.write("<BR><BR>\n");
                    htmlFile.write("<table border=0 width=100%>\n");
                    htmlFile.write("<tr>\n");
                    htmlFile.write("<td width=5%>Domicilio:</td>\n");
                    htmlFile.write("<td width=95%>" + OP_Proced.getCampo("Select nombre from calles where idcalle=" + Reg.getString(25)) + " Nº " + Reg.getString(26) + " - " + Reg.getString(27) + "</td></tr>\n");
                    htmlFile.write("</table>\n");
                    htmlFile.write("<BR><BR>\n");
                    htmlFile.write("<table border=0 width=100%>\n");
                    htmlFile.write("<tr>\n");
                    htmlFile.write("<td width=50%>Por la presente Cedula NOTIFICO a Ud.haciendole saber que se ha dictado la siguiente</td>\n");
                    htmlFile.write("<td width=8%>" + Reg1.getString(6) + " :</td>\n");
                    htmlFile.write("<td width=2%>Salta,</TD>\n");
                    htmlFile.write("<TD width=2%>" + Reg1.getString(3).substring(8, 10) + "</TD>\n");
                    htmlFile.write("<TD width=2%>de </TD>\n");
                    htmlFile.write("<TD width=2%>" + OP_Proced.getCampo("Select nombre from mes where id=" + Reg1.getString(3).substring(5, 7)) + "</TD>\n");
                    htmlFile.write("<TD width=2%>de </TD>\n");
                    htmlFile.write("<TD width=32%>" + Reg1.getString(3).substring(0, 4) + ".-</TD>\n");
                    htmlFile.write("</TR>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<td COLSPAN=8 height=100 valign=top>" + Reg1.getString(5) + "</td>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<td COLSPAN=7></td>\n");
                    htmlFile.write("<td >_____IMAGEN FIRMA______________</td>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<td COLSPAN=7></td>\n");
                    htmlFile.write("<td ALIGN=CENTER>Firma</td>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<td COLSPAN=7></td>\n");
                    htmlFile.write("<td ALIGN=CENTER>(" + OP_Proced.getCampo("Select descripcion from areas where idarea=" + Reg1.getString(8)) + ")</td>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("<TR>\n");
                    htmlFile.write("<td COLSPAN=7></td>\n");
                    htmlFile.write("<td ALIGN=CENTER>" + OP_Proced.getCampo("Select apellido||', '||nombre from personas where idpersona=" + Reg1.getString(7)) + "</td>\n");
                    htmlFile.write("</tr>\n");
                    htmlFile.write("</table>\n");
                    htmlFile.write("<BR><BR>\n");
                    htmlFile.write("<table border=0 width=100%>\n");
                    htmlFile.write("<tr><td>Asimismo, comunicole conforme lo preve el art. l47 de la Ley de Procedimiento Administrativos N 5348/78 ,que  el acto que se notifica por la presente puede ser");
                    htmlFile.write("objeto de los recursos y plazos que se detallan a continuacion: RECURSO DE REVOCATORA O RECONSIDERACION ( 10 DIAS) RECURSOS");
                    htmlFile.write("JERARQUICO ( l0 dias) CONTANDO DESDE EL SIGUIENTE  DIA DE LA NOTIFICACION  DE LA DENEGATORIA, O RECHAZO  DE  REVOCATORIA  O DEL");
                    htmlFile.write("VENCIMIENTO DEL PLAZO  PARA RESOLVER ESTA RECURSO DE ALZADA ( l0dias) DESDE QUE LA DECISION  RECURRIDA FUE NOTIFICADA AL INTERESADO.-</td></tr>\n");
                    htmlFile.write("<TR><TD HEIGHT=20></TD></TR>\n");
                    htmlFile.write("<TR><TD ALIGN=RIGHT>QUEDA UD. LEGALMENTE NOTIFICADO.</TD></TR>\n");
                    htmlFile.write("</table>\n");
                    htmlFile.write("</BODY>\n");
                    htmlFile.write("</HTML>\n");
                    htmlFile.close();
                    HTMLBrowser temp = new HTMLBrowser(htmlPath);
                    temp.setModal(true);
                    temp.setVisible(true);
                }
            }
            return true;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLListadoExptes(String Filtro, String nombre) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Listado de Documentos/Expedientes</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Listado de Documentos/Expedientes</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            //////////////////////////////////////////////Inicio de Tabla de Datos
            htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=15%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Nº Expediente</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=10%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Nº Catastro</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=5%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fecha</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=20%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Iniciante</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=4%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Folios</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=8%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Estado</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=23%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Oficina Actual</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER width=5%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fch.Rec</FONT></TD></TR>\n");
            String Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," + "catastros.catastro,anio,nroexp,fechaexp,idtipoiniciador,idtipoasunto,tiposiniciador.descripcion as iniciador,tiposasunto.descripcion as asunto," + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro," + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.estado,tiposletra.letra " + " FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra" + " WHERE instlegal.estado<>'*' " + Filtro + " and instlegal.idtipodoc=tiposletra.idtipo " + " and instlegal.idorganismo=tiposletra.idorganismo " + " and instlegal.idorganismo=tiposorganismo.idorganismo " + " and instlegal.idtipoiniciador=tiposiniciador.idtipo  " + " and instlegal.idtipoasunto=tiposasunto.idtipo " + " and instlegal.idcatastro=catastros.idcatastro " + " and instlegal.idprofesional=profesionales.idprofesional " + " order by nroexp,anio,fechaexp";
            ResultSet Reg = OP_Proced.exConsulta(Consulta);
            while (Reg.next()) {
                htmlFile.write("<TR><TD  ALIGN=CENTER width=15%><FONT size=2 FACE='Arial' >" + Reg.getString(4) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER width=10%><FONT size=2 FACE='Arial' >" + Reg.getString(5) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER width=5%><FONT size=2 FACE='Arial' >" + OP_Proced.Fecha2(Reg.getString(8), true) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=20%><FONT size=2 FACE='Arial' >" + Reg.getString(13) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER width=4%><FONT size=2 FACE='Arial' >" + Reg.getString(16) + "</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER width=8%><FONT size=2 FACE='Arial' >" + Reg.getString(17) + "</FONT></TD>\n");
                //---------------LUGAR ACTUAL --> OFICINA RECEPTORA
                ResultSet Reg3 = OP_Proced.exConsulta("Select max(fechapase||' '||horapase) from files.pases where estado<>'*' and idinst=" + Reg.getString(1));
                if (Reg3.next()) {
                    String Q = "SELECT idoficrec,idreceptor," + "(Select descripcion from files.tiposoficina where idtipo=idoficrec) as ofic_rec," + "(Select apellido||', '||nombre from personas where idpersona=idreceptor) as receptor," + "estadopase,fecha_rec,hora_rec " + " FROM files.pases  WHERE estado<>'*' and idinst=" + Reg.getString(1) + " and fechapase||' '||horapase='" + Reg3.getString(1) + "'";
                    //   System.out.println(Q);
                    ResultSet Reg2 = OP_Proced.exConsulta(Q);
                    if (Reg2.next()) {
                        htmlFile.write("<TD  ALIGN=LEFT width=23%><FONT size=2 FACE='Arial' >" + Reg2.getString(3) + "</FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=CENTER width=5%><FONT size=2 FACE='Arial' >" + OP_Proced.Fecha2(OP_Proced.TransformaNull_Texto(Reg2.getString(6)), true) + "</FONT></TD>\n");
                    }
                }
            }
            htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (Exception x) {
            x.printStackTrace();
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLExpteSeleccionado(String idinst, String nombre, boolean ConPases) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Datos del Documento/Expediente</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Datos del Documento/Expediente</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            //////////////////////////////////////////////Inicio de Tabla de Datos
            String Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," + "catastros.catastro,anio,nroexp,fechaexp,idtipoiniciador,idtipoasunto," + "'('||tiposiniciador.abrev||') - '||tiposiniciador.descripcion as iniciador," + "'('||tiposasunto.abrev||') - '||tiposasunto.descripcion as asunto," + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro," + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.estado,tiposletra.letra " + " FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra" + " WHERE instlegal.estado<>'*' AND idinst=" + idinst + " and instlegal.idtipodoc=tiposletra.idtipo " + " and instlegal.idorganismo=tiposletra.idorganismo " + " and instlegal.idorganismo=tiposorganismo.idorganismo " + " and instlegal.idtipoiniciador=tiposiniciador.idtipo  " + " and instlegal.idtipoasunto=tiposasunto.idtipo " + " and instlegal.idcatastro=catastros.idcatastro " + " and instlegal.idprofesional=profesionales.idprofesional ";
            //System.out.println(Consulta);
            ResultSet Reg = OP_Proced.exConsulta(Consulta);
            if (Reg.next()) {
                //DATOS DEL EXPEDIENTE
                htmlFile.write("<TABLE WIDTH=80% ALIGN=CENTER BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Nº Expediente:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80%><FONT size=4 FACE='Arial' ><b>&nbsp " + Reg.getString(4) + "</b></FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Nº Catastro:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(5) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fecha:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + OP_Proced.Fecha2(Reg.getString(8), true) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Iniciador:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(11) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Asunto:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(12) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Iniciante:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(13) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Contenido:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(14) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Observaciones:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(15) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Folios:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(16) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Estado:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(17) + "</FONT></TD></TR>\n");
                htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Profesional:&nbsp</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg.getString(20) + "</FONT></TD></TR>\n");
            }
            htmlFile.write("</TABLE><BR><BR>\n");
            if (ConPases) {
                //ULTIMO PASE
                Reg = OP_Proced.exConsulta("Select max(fechapase||' '||horapase) from files.pases where estado<>'*' and idinst=" + idinst);
                if (Reg.next()) {
                    String Q = "SELECT idinst,fechapase,horapase,idoficemi,idemisor," + "(Select descripcion FROM files.tiposoficina where idtipo=idoficemi) as ofic_emi," + "(Select apellido||', '||nombre from personas where idpersona=idemisor) as emisor," + "observemi,idoficrec,idreceptor,(Select descripcion from files.tiposoficina where idtipo=idoficrec) as ofic_rec," + "(Select apellido||', '||nombre from personas where idpersona=idreceptor) as receptor," + "observrec,cfoliorec,estadopase,fecha_rec,hora_rec,estado " + " FROM files.pases  WHERE estado<>'*' and idinst=" + idinst + " and fechapase||' '||horapase='" + Reg.getString(1) + "'";
                    //     System.out.println(Q);        
                    ResultSet Reg2 = OP_Proced.exConsulta(Q);
                    if (Reg2.next()) {
                        htmlFile.write("<TABLE WIDTH=80% ALIGN=CENTER BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=center colspan=2><FONT size=4 FACE='Arial' COLOR='#FFFFFF' ><B>Ultimo Pase Registrado</b></FONT></TD>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fecha:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + OP_Proced.Fecha2(Reg2.getString(2), true) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Hora:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(3) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Oficina Emisora:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(6) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Emisor:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(7) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Texto del Pase:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(8) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Oficina Receptora:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(11) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Receptor:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(12) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Observ.Receptor:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(13) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Folio:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(14) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Estado:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + Reg2.getString(15) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fecha Rec.:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + OP_Proced.Fecha2(OP_Proced.TransformaNull_Texto(Reg2.getString(16)), true) + "</FONT></TD></TR>\n");
                        htmlFile.write("<TR><TD  BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "' ALIGN=RIGHT width=20% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Hora Rec.:&nbsp</b></FONT></TD>\n");
                        htmlFile.write("<TD  ALIGN=LEFT width=80% ><FONT size=2 FACE='Arial' >&nbsp " + OP_Proced.Hora(OP_Proced.TransformaNull_Texto(Reg2.getString(17)), true, false) + "</FONT></TD></TR>\n");
                        htmlFile.write("</TABLE><BR>\n");
                    }
                }
                //PASES DEL EXPEDIENTE
                htmlFile.write("<TABLE WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=CENTER colspan=8><FONT size=4 FACE='Arial' COLOR='#FFFFFF' ><B>Pases Registrados</b></FONT></TD></TR>\n");
                htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=7%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fecha</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=7%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Hora</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=27%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Oficina Emisora</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=31%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Oficina Receptora</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=5%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Folio</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=9%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Estado</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=7%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Fcha.Rec.</b></FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=LEFT width=7%><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Hrs.Rec.</b></FONT></TD></TR>\n");
                Consulta = "SELECT idinst,fechapase,horapase,idoficemi,idemisor," + "(Select descripcion FROM files.tiposoficina where idtipo=idoficemi) as ofic_emi," + "(Select apellido||', '||nombre from personas where idpersona=idemisor) as emisor," + "observemi,idoficrec,idreceptor,(Select descripcion from files.tiposoficina where idtipo=idoficrec) as ofic_rec," + "(Select apellido||', '||nombre from personas where idpersona=idreceptor) as receptor," + "observrec,cfoliorec,estadopase,fecha_rec,hora_rec,estado " + " FROM files.pases  WHERE estado<>'*' and idinst=" + idinst + " order by fechapase,horapase";
                // System.out.println(Consulta);        
                Reg = OP_Proced.exConsulta(Consulta);
                while (Reg.next()) {
                    //htmlFile.write("<TR><TD  ALIGN=LEFT width=10%><FONT size=2 FACE='Arial'>"+ Reg.getString(1) +"</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(Reg.getString(2), true) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Hora(Reg.getString(3), true, false) + "</FONT></TD>\n");
                    /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(4) +"</FONT></TD>\n");
              htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(5) +"</FONT></TD>\n");*/
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(6) + "</FONT></TD>\n");
                    //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(7) +"</FONT></TD>\n");
                    /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(8) +"</FONT></TD>\n");
              htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(9) +"</FONT></TD>\n");
              htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(10) +"</FONT></TD>\n");*/
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(11) + "</FONT></TD>\n");
                    // htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(12) +"</FONT></TD>\n");
                    //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(13) +"</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(14) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(15) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(OP_Proced.TransformaNull_Texto(Reg.getString(16)), true) + "</FONT></TD>\n");
                    htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Hora(OP_Proced.TransformaNull_Texto(Reg.getString(17)), true, false) + "</FONT></TD></TR>\n");
                }
            }
            htmlFile.write("</TABLE><BR><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (EmptyStackException e) {
            System.out.println("ErrorSTack");
            return false;
        } catch (Exception x) {
            x.printStackTrace();
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLTextoPase(String idinst, String nombre, String TextoPase, String Titulo) {
        try {
            /** Abro el Archivo */
            //Text t="";
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Texto del Pase</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Texto del Pase</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            //////////////////////////////////////////////Inicio de Tabla de Datos
            htmlFile.write("<TABLE align=center WIDTH=70% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR><TD align=right>Correspondiente a Expediente Nº " + idinst + "<BR><BR></TD></TR>\n");
            htmlFile.write("<TR><TD>____ " + Titulo + "____<BR><BR></TD></TR>\n");
            htmlFile.write("<TR><TD>" + TextoPase.replaceAll("\n", "<BR>") + "</TD></TR>\n");
            htmlFile.write("</TABLE><BR><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (EmptyStackException e) {
            return false;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLListadoLM(String nombre, String Filtro) {
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            //System.out.println(Proced.getRutaInforme() + Proced.getSeparador() +"permisos-apertura.html");
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Listado de Lineas Municipales ingresadas</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>Listado de Lineas Municipales ingresadas</B></FONT><BR>\n");
            htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            //-------------------------
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR BGCOLOR='" + OP_Proced.getColorCelesteOscuro() + "'>\n");
            //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >NºOrden</FONT></TD>\n");
            //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idcatastro</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Catastro</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Seccion</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Mza</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Parcela</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Fecha</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >FechaInforme</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Vda.Prop.</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Czada.Pro.</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Platabanda</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Czada.Op.</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Vda.Op.</FONT></TD>\n");
            //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idcalle2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Esquina</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Nº</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Vda.Prop.2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Czada.Pro.2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Platabanda2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Czada.Op.2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Vda.Op.2</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Ochava</FONT></TD>\n");
            /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idtipomatlinmun</FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idlegislacion</FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idcota</FONT></TD>\n");*/
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Materializacion</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Legislacion</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Cota</FONT></TD>\n");
            /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idprofresp</FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idinspector</FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >idprofesional</FONT></TD>\n");*/
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Responsable</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Inspector</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Profesional</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial' COLOR='#FFFFFF' >Informe General</FONT></TD></TR>\n");
            // ----------------------------------------------------
            String Consulta = "SELECT idlinea,lineas.idcatastro,catastro,idseccion,nmanzana||lmanzana as mza," + "nparcela||lparcela as parcela,fechaalta,fechainforme,veredapropia,calzadapropia,platabanda,calzadaopuesta," + "veredaopuesta,idcalle2,calles.nombre as esq,numero2,veredapropia2,calzadapropia2,platabanda2,calzadaopuesta2," + "veredaopuesta2,ochava,idtipomatlinmun,lineas.idlegislacion,idcota,tiposmatlinmun.nombre,legislaciones.nombre," + "tiposcota.nombre,idprofresp,idinspector,lineas.idprofesional,(Select apellido||', '||nombre from personas where idpersona=lineas.idprofresp) as respo," + "(Select apellido||', '||nombre from personas where idpersona=lineas.idinspector) as insp,profesionales.apellido||', '||profesionales.nombre as prof," + "informe,lineas.estado" + " FROM cepax.catastros,cepax.parcelas,lineamunicipal.lineas,sopsalta.profesionales," + "lineamunicipal.tiposmatlinmun,lineamunicipal.legislaciones,lineamunicipal.tiposcota,calles" + " WHERE lineas.estado<>'*'" + Filtro + " AND lineas.idcatastro=catastros.idcatastro" + " AND cepax.catastros.idparcela = cepax.parcelas.idparcela" + " AND lineas.idprofesional=profesionales.idprofesional" + " AND lineas.idtipomatlinmun=tiposmatlinmun.idtipo" + " AND lineas.idlegislacion=legislaciones.idlegislacion" + " AND lineas.idcota=tiposcota.idtipo" + " AND lineas.idcalle2=calles.idcalle" + " order by idseccion,nmanzana,lmanzana,nparcela, lparcela,fechaalta";
            ResultSet Reg = OP_Proced.exConsulta(Consulta);
            while (Reg.next()) {
                // htmlFile.write("<TR><TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(1) +"</FONT></TD>\n"); //idlinea
                //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(2) +"</FONT></TD>\n");   //idcatastro
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(3) + "</FONT></TD>\n");
                //catastro
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(4) + "</FONT></TD>\n");
                //idseccion
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(5) + "</FONT></TD>\n");
                //mza
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(6) + "</FONT></TD>\n");
                //parcela
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(Reg.getString(7), true) + "</FONT></TD>\n");
                //fechaalta
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + OP_Proced.Fecha2(Reg.getString(8), true) + "</FONT></TD>\n");
                //fechainforme
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(9) + "</FONT></TD>\n");
                //veredapropia
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(10) + "</FONT></TD>\n");
                //calzadapropia
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(11) + "</FONT></TD>\n");
                ////platabanda
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(12) + "</FONT></TD>\n");
                //calzadaopuesta
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(13) + "</FONT></TD>\n");
                //veredaopuesta
                //htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(14) +"</FONT></TD>\n");   //idcalle2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(15) + "</FONT></TD>\n");
                //esq
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(16) + "</FONT></TD>\n");
                //numero2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(17) + "</FONT></TD>\n");
                //veredapropia2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(18) + "</FONT></TD>\n");
                //calzadapropia2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(19) + "</FONT></TD>\n");
                ////platabanda2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(20) + "</FONT></TD>\n");
                //calzadaopuesta2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(21) + "</FONT></TD>\n");
                //veredaopuesta2
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(22) + "</FONT></TD>\n");
                //ochava
                /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(23) +"</FONT></TD>\n");  //idtipomatlinmun
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(24) +"</FONT></TD>\n");   //idlegislacion
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(25) +"</FONT></TD>\n"); *///idcota
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(26) + "</FONT></TD>\n");
                //tipomatlinmun
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(27) + "</FONT></TD>\n");
                //legislacion
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(28) + "</FONT></TD>\n");
                //cota
                /*htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(29) +"</FONT></TD>\n");    //idprofresp
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(30) +"</FONT></TD>\n");   //idinspector
          htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>"+ Reg.getString(31) +"</FONT></TD>\n"); *///idprofesional
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(32) + "</FONT></TD>\n");
                //respo
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(33) + "</FONT></TD>\n");
                //insp
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(34) + "</FONT></TD>\n");
                //prof
                htmlFile.write("<TD  ALIGN=LEFT ><FONT size=2 FACE='Arial'>" + Reg.getString(35) + "</FONT></TD></TR>\n");
                //informe
            }
            //////////////////////////////////////////////Inicio de Tabla de Datos
            htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
            return true;
        } catch (EmptyStackException e) {
            return false;
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
    }

    public static boolean HTMLDetallePedidoSolicitado(String idpedido, String nombre) {
        String nropedido = "";
        String fecha = "";
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            /** Traigo el numero de pedido */
            nropedido = OP_Proced.getCampo("SELECT nropedido FROM control.pedmateriales WHERE estado <> '*' " + " AND pedmateriales.idpedido = " + idpedido);
            /** Obtengo el monto estimado */
            int parteentera = 0;
            String monto = OP_Proced.getCampo("SELECT monto FROM control.pedmateriales WHERE idpedido = " + idpedido);
            //System.out.println("monto "+monto.indexOf(".")+1;
            if (monto.indexOf(".") > -1) {
                parteentera = Integer.parseInt(monto.substring(0, monto.indexOf(".")));
            } else {
                parteentera = Integer.parseInt(monto);
            }
            String cadenanumero = OP_Proced.DobleDec(monto);
            String partedecimal = cadenanumero.substring(cadenanumero.indexOf(".") + 1);
            String cadenaentera = "Pesos " + OP_Proced.convertirLetras(parteentera).replaceAll("  ", " ");
            String cadenadecimal = " con " + partedecimal + "/100";
            // System.out.print(Proced.convertirLetras(parteentera).replaceAll("  "," ") + " pesos");    
            // System.out.println(" con " + partedecimal + "/100");                                                              
            /** Traigo la fecha de hoy */
            fecha = "SALTA, " + OP_Proced.ObtieneDiaSemana(OP_Proced.FechaHora2(true, false)) + " " + OP_Proced.getCampo("SELECT EXTRACT(Day FROM current_date)") + " de " + OP_Proced.ObtieneMes(OP_Proced.FechaHora2(true, false)) + " de " + OP_Proced.getCampo("SELECT EXTRACT(year FROM current_date)") + ".- ";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>");
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Solicitud de Materiales</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><FONT size=1px FACE='Arial' COLOR='#000000'><B>MUNICIPALIDAD DE DIGITALL</B></FONT><BR></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><FONT size=1px FACE='Arial' COLOR='#000000'>Secretaría de Obras y Servicios Públicos </FONT><BR></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=RIGHT><FONT size=2px FACE='Arial' COLOR='#000000'>" + fecha + "</FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5% </TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=CENTER><FONT size=3px FACE='Arial' COLOR='#000000'><B>Pedido de Material Nº " + nropedido + " </B></FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5% </TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            //---------------------------------tabla del encabezado
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Al Sr. jefe de</FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write(" <TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Control y Gestión</FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>S_____ / _____D</FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%><BR></TD>\n");
            htmlFile.write("<TD WIDTH=90%></TD>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=90% ALIGN=RIGHT><FONT size=2px FACE='Arial' COLOR='#000000'>Me dirijo a usted con el objeto de remitir para su conocimiento e intervención lo siguiente </FONT></TD>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            String Consulta = "SELECT fundamentacion,destino,plazo,monto" + " FROM control.pedmateriales WHERE pedmateriales.estado<>'*' " + " AND idpedido = " + idpedido;
            //System.out.println("la gran consulta--> "+Consulta);
            ResultSet Reg1 = OP_Proced.exConsulta(Consulta);
            while (Reg1.next()) {
                htmlFile.write("<BR>\n");
                htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Fundamentacion: " + Reg1.getString(1).replaceAll("\n", "<BR>") + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Destino: " + Reg1.getString(2) + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Plazo: " + Reg1.getString(3) + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Monto estimado: $ " + cadenanumero + " (" + cadenaentera + " " + cadenadecimal + ") </FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE>\n");
                htmlFile.write("<BR><TABLE align=center WIDTH=90% BORDER=1  STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                //htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=4 FACE='Arial' COLOR='#000000' >Item</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=70% ><FONT size=2px FACE='Arial' COLOR='#000000' >Detalle</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial' COLOR='#000000' >Cant Solicitada</FONT></TD>\n");
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial' COLOR='#000000' >Unidad</FONT></TD></TR>\n");
            }
            Consulta = "SELECT nroitem,materiales.descripcion,cant_solicitada,tiposunidad.descripcion" + " FROM control.detpedmaterial,materiales WHERE detpedmaterial.estado<>'*' " + " AND idpedido = " + idpedido + " AND  materiales.idmaterial = detpedmaterial.idmaterial" + " AND materiales.idunidad = tiposunidad.idtipo ORDER BY materiales.descripcion";
            //System.out.println("la gran consulta--> "+Consulta);
            ResultSet Reg = OP_Proced.exConsulta(Consulta);
            while (Reg.next()) {
                //htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2 FACE='Arial'>"+ Reg.getString(1) +"</FONT></TD>\n");        //item
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=70% ><FONT size=2px FACE='Arial'>" + Reg.getString(2) + "</FONT></TD>\n");
                //material
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial'>" + Reg.getString(3) + "</FONT></TD>\n");
                //cantidad
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial'>" + Reg.getString(4) + "</FONT></TD></TR>\n");
                //Unidad
            }
            htmlFile.write("</TABLE><BR>\n");
            // ---------------- Cierro la conexion ------------------------------------
            // ---------------------------------------------------- tabla para la despedida
            htmlFile.write("<BR>\n");
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=5%></TD>\n");
            htmlFile.write("<TD WIDTH=83% ALIGN=RIGHT><FONT size=2px FACE='Arial' COLOR='#000000'>Sin otro particular, saludo a Ud. muy atentamente .-  </FONT></TD>\n");
            htmlFile.write("<TD WIDTH=12%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            //////////////////////////////////////////////Inicio de Tabla de Datos
            //htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
        return true;
    }

    public static boolean HTMLImprimePedidoAutorizado(String idpedido, String nombre) {
        String nropedido = "";
        String fecha = "";
        try {
            /** Abro el Archivo */
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + nombre + ".html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            /** Leo el Registro */
            String Cant = "0";
            /** Traigo el numero de pedido */
            nropedido = OP_Proced.getCampo("SELECT nropedido FROM control.pedmateriales WHERE estado <> '*' " + " AND pedmateriales.idpedido = " + idpedido);
            /** Obtengo el monto estimado */
            int parteentera = 0;
            String monto = OP_Proced.getCampo("SELECT monto FROM control.pedmateriales WHERE idpedido = " + idpedido);
            //System.out.println("monto "+monto.indexOf(".")+1;
            if (monto.indexOf(".") > -1) {
                parteentera = Integer.parseInt(monto.substring(0, monto.indexOf(".")));
            } else {
                parteentera = Integer.parseInt(monto);
            }
            String cadenanumero = OP_Proced.DobleDec(monto);
            String partedecimal = cadenanumero.substring(cadenanumero.indexOf(".") + 1);
            String cadenaentera = OP_Proced.convertirLetras(parteentera).replaceAll("  ", " ") + " pesos";
            String cadenadecimal = " con " + partedecimal + "/100";
            /** Traigo la fecha de hoy */
            fecha = OP_Proced.ObtieneDiaSemana(OP_Proced.FechaHora2(true, false)) + " " + OP_Proced.getCampo("SELECT EXTRACT(Day FROM current_date)") + " de " + OP_Proced.ObtieneMes(OP_Proced.FechaHora2(true, false)) + " de " + OP_Proced.getCampo("SELECT EXTRACT(year FROM current_date)") + ".- ";
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>");
            htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>Pedido de Materiales Autorizado</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            // logo de la municipalidad  
            htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg" + "' width=60></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><FONT size=1px FACE='Arial' COLOR='#000000'><B>MUNICIPALIDAD DE DIGITALL</B></FONT><BR></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><FONT size=1px FACE='Arial' COLOR='#000000'>Secretaría de Obras y Servicios Públicos </FONT><BR></TD>\n");
            htmlFile.write("<TD WIDTH=70%></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            htmlFile.write("<BR>\n");
            htmlFile.write("<TABLE align=center WIDTH=90% BORDER = 0 CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD  WIDTH=60%></TD>\n");
            htmlFile.write("<TD WIDTH=40% ALIGN=RIGHT><FONT size=2px FACE='Arial' COLOR='#000000'>" + fecha + "</FONT><TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            htmlFile.write("<BR>\n");
            htmlFile.write("<BR>\n");
            htmlFile.write("<TABLE align=center WIDTH=30% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=30% ALIGN=CENTER><FONT size=2px FACE='Arial' COLOR='#000000'><B> PEDIDO DE MATERIALES </B></FONT></TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            htmlFile.write("<BR>\n");
            htmlFile.write("<BR>\n");
            htmlFile.write("<TABLE align=center WIDTH=90% STYLE='border:none' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD WIDTH=60% STYLE='border:1px;border-bottom:solid;border-top:solid;border-left:solid;border-right:solid' ALIGN=CENTER ><FONT size=2px FACE='Arial' COLOR='#000000'>DEPENDENCIA: Sistema de Informacion Geográfica</FONT></TD>\n");
            htmlFile.write("<TD STYLE='border:none; border-bottom:none' WIDTH=20%></TD>\n");
            htmlFile.write("<TD WIDTH=20% STYLE='border:1px;border-bottom:solid;border-top:solid;border-left:solid;border-right:solid' ALIGN=CENTER><FONT size=2px FACE='Arial' COLOR='#000000'>PEDIDO Nº " + idpedido + "</FONT><TD>\n");
            htmlFile.write("</TR>\n");
            htmlFile.write("</TABLE>\n");
            htmlFile.write("<BR>\n");
            // tabla de Fundamentacio, destino,plazo y monto estimado
            String Consulta = "SELECT fundamentacion,destino,plazo,monto" + " FROM control.pedmateriales WHERE pedmateriales.estado<>'*' " + " AND idpedido = " + idpedido;
            //System.out.println("la gran consulta--> "+Consulta);
            ResultSet Reg1 = OP_Proced.exConsulta(Consulta);
            while (Reg1.next()) {
                htmlFile.write("<BR>\n");
                htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Fundamentacion: " + Reg1.getString(1).replaceAll("\n", "<BR>") + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Destino: " + Reg1.getString(2) + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Plazo: " + Reg1.getString(3) + "</FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("<TD WIDTH=90% ALIGN=LEFT><FONT size=2px FACE='Arial' COLOR='#000000'>Monto estimado: $ " + cadenanumero + " (" + cadenaentera + " " + cadenadecimal + ") </FONT></TD>\n");
                htmlFile.write("<TD WIDTH=5%></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE>\n");
            }
            htmlFile.write("<BR><TABLE align=center WIDTH=90% BORDER=1  STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
            htmlFile.write("<TR>\n");
            htmlFile.write("<TD  ALIGN=CENTER WIDTH=5%  ><FONT size=2px FACE='Arial' COLOR='#000000' >Item</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER WIDTH=55% ><FONT size=2px FACE='Arial' COLOR='#000000' >Detalle</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER WIDTH=20% ><FONT size=2px FACE='Arial' COLOR='#000000' >Tipo Marca</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial' COLOR='#000000' >Cantidad</FONT></TD>\n");
            htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial' COLOR='#000000' >Unidad</FONT></TD></TR>\n");
            Consulta = "SELECT nroitem,materiales.descripcion,replace((SELECT tiposmarca.descripcion WHERE tiposmarca.idtipo = idmarca_solicitada[1])||' - '||" + " (select tiposmarca.descripcion WHERE tiposmarca.idtipo = idmarca_solicitada[2])||' - '|| (select tiposmarca.descripcion " + " WHERE tiposmarca.idtipo = idmarca_solicitada[3]), ' - Sin Asignar','') as marcas,cant_solicitada,tiposunidad.descripcion" + " FROM control.detpedmaterial,materiales WHERE detpedmaterial.estado<>'*' " + " AND idpedido = " + idpedido + " AND  materiales.idmaterial = detpedmaterial.idmaterial" + " AND materiales.idunidad = tiposunidad.idtipo AND nroitem <> 0" + " ORDER BY nroitem";
            System.out.println("la gran consulta--> " + Consulta);
            ResultSet Reg = OP_Proced.exConsulta(Consulta);
            while (Reg.next()) {
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=5% ><FONT size=2 FACE='Arial'>" + Reg.getString(1) + "</FONT></TD>\n");
                //item
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=55% ><FONT size=2px FACE='Arial'>" + Reg.getString(2) + "</FONT></TD>\n");
                //material
                htmlFile.write("<TD  ALIGN=LEFT WIDTH=20% ><FONT size=2px FACE='Arial'>" + Reg.getString(3) + "</FONT></TD>\n");
                //material
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial'>" + Reg.getString(4) + "</FONT></TD>\n");
                //cantidad
                htmlFile.write("<TD  ALIGN=CENTER WIDTH=10% ><FONT size=2px FACE='Arial'>" + Reg.getString(5) + "</FONT></TD></TR>\n");
                //Unidad
            }
            // ---------------- Cierro la conexion ------------------------------------
            // ---------------------------------------------------- tabla para la despedida
            /*    htmlFile.write("<BR>\n");
      htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD WIDTH=5%></TD>\n");
      htmlFile.write("<TD WIDTH=83% ALIGN=RIGHT><FONT size=2px FACE='Arial' COLOR='#000000'>Sin otro particular, saludo a Ud. muy atentamente .-  </FONT></TD>\n");
      htmlFile.write("<TD WIDTH=12%></TD>\n");
      htmlFile.write("</TR>\n");
      htmlFile.write("</TABLE>\n");*/
            //////////////////////////////////////////////Inicio de Tabla de Datos
            //htmlFile.write("</TABLE><BR>\n");
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
            HTMLBrowser temp = new HTMLBrowser(htmlPath);
            temp.setModal(true);
            temp.setVisible(true);
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
            return false;
        }
        return true;
    }
}
