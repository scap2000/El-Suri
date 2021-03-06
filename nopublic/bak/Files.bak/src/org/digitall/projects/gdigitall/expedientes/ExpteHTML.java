package org.digitall.projects.gdigitall.expedientes;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.sql.ResultSet;

import javax.swing.JDialog;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;


//public class ExpteHTML extends JDialog 
public class ExpteHTML extends BasicPrimitivePanel
{
  /**
   * INFORME DE EXPEDIENTES VENCIDOS SEGUN EL TIPO DE PLAZO Y LA FECHA DE VENCIMIENTO. Ver de modificarlo segun las necesidades
   * @return 
   * @param Fecha: INDICA LA FECHA DE VTO DEL CUAL SE QUIERE GENERAR EL INFORME
   * @param TipoPlazo: 
   * @param Filtro: FILTRO DE LA CONSULTA PARA GERNERAR EL INFORME
   */
  static boolean HTMLExpVdosGral(String Filtro,String TipoPlazo,String Fecha)
  {
    try
    {
    /** Abro el Archivo */
      String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + "ExpVdosGral.html";
      FileWriter htmlFile = new FileWriter(htmlPath,false);
      BufferedWriter log = new BufferedWriter(htmlFile);

     /** Leo el Registro */
      htmlFile.write("<HTML>\n");
      htmlFile.write("<HEAD>\n");
      htmlFile.write("<TITLE>Listado de Documentos/Expedientes Vencidos (Gral.)</TITLE>\n");
      htmlFile.write("</HEAD>\n");
      htmlFile.write("<BODY>\n");
      
      htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD WIDTH=10% ALIGN=LEFT><img src='../iconos/logo.jpg' width=60></TD>\n");
      htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" +OP_Proced.getColorAzul() +"'><B>Listado de Documentos/Expedientes Vencidos - (Gral.)</B></FONT><BR>\n");
      htmlFile.write("<FONT size=3>a la Fecha: <B>" + Fecha + "</B></FONT>\n");
      htmlFile.write("<TD WIDTH=10% ALIGN=RIGHT><img src='../iconos/logo.jpg' width=60></TD>\n");
      htmlFile.write("</TR>\n");
      htmlFile.write("</TABLE><BR>\n");
      
      htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD ALIGN=RIGHT width=10% bgcolor='" + OP_Proced.getColorCelesteOscuro() +"'><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Tipo Plazo:&nbsp;</B></FONT></TD>\n");
      htmlFile.write("<TD ALIGN=LEFT  width=90% >&nbsp;<B>"+ TipoPlazo +"</B></TD></TR></TABLE><BR>\n");

//////////////////////////////////////////////Inicio de Tabla de Datos
      
     String Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte,"
                    +"fechaexp,(date '" + OP_Proced.Fecha2(Fecha,false) +"' - fechaexp) as diasvenc,catastros.catastro,anio,"
                    +"nroexp,idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto,"
                    +"iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro,"
                    +"instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.cantanexo,instlegal.estado,tiposletra.letra"
                    +" FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra,files.tiposplazo"
                    +" WHERE instlegal.estado<>'*' And (date '" +OP_Proced
                .Fecha(Fecha,false) +"' - fechaexp) > plazo " + Filtro 
                    +" and instlegal.idtipoplazo=tiposplazo.idtipo "
                    +" and instlegal.idtipodoc=tiposletra.idtipo "
                    +" and instlegal.idorganismo=tiposletra.idorganismo "
                    +" and instlegal.idorganismo=tiposorganismo.idorganismo "
                    +" and instlegal.idtipoiniciador=tiposiniciador.idtipo  "
                    +" and instlegal.idtipoasunto=tiposasunto.idtipo "
                    +" and instlegal.idcatastro=catastros.idcatastro "
                    +" and instlegal.idprofesional=profesionales.idprofesional "
                    +" order by nroexp,anio,fechaexp";
      // Listado
      htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR BGCOLOR='" + OP_Proced
                           .getColorCelesteOscuro() +"'>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=20%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>N?? Expediente</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=9% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Fecha</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=9% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Dias Vdos</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=10%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>N?? Catastro</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=34%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Iniciante</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=10% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Estado</B></FONT></TD>\n");
      htmlFile.write("<TD  ALIGN=CENTER width=6% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>C.Anexos</B></FONT></TD></TR>\n");              
          
      ResultSet Reg = OP_Proced.exConsulta(Consulta);
      while (Reg.next())
      {
          htmlFile.write("<TR>");
          htmlFile.write("<TD  ALIGN=LEFT  ><FONT size=2 FACE='Arial' >&nbsp;"+ Reg.getString(4)  +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(5)  +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(6)  +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=LEFT  ><FONT size=2 FACE='Arial' >"+ Reg.getString(7)  +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=LEFT  ><FONT size=2 FACE='Arial' >"+ Reg.getString(14) +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(18) +"</FONT></TD>\n");        
          htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(22) +"</FONT></TD></TR>\n");        
      }
      htmlFile.write("</TABLE><BR>\n");  
      htmlFile.write("</BODY>\n");
      htmlFile.write("</HTML>\n");
      htmlFile.close();
            OP_Proced.AbreInforme(htmlPath);
      return true;
    } catch (Exception x) 
    {
      x.printStackTrace();
            OP_Proced.Mensaje(x.getMessage(),"");
      return false;
    }
  }
  
  /** Ver de modificarlo segun las necesidades  */

  static boolean HTMLExpVdosDetallado(String Filtro,String TipoPlazo,String Fecha)
  {
    try
    {
    /** Abro el Archivo */
      String htmlPath = OP_Proced.getRutaInforme() + OP_Proced
                .getSeparador() + "ExpVdosDet.html";
      FileWriter htmlFile = new FileWriter(htmlPath,false);
      BufferedWriter log = new BufferedWriter(htmlFile);

     /** Leo el Registro */
      htmlFile.write("<HTML>\n");
      htmlFile.write("<HEAD>\n");
      htmlFile.write("<TITLE>Listado de Documentos/Expedientes Vencidos (Detallado)</TITLE>\n");
      htmlFile.write("</HEAD>\n");
      htmlFile.write("<BODY>\n");
      
      htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD WIDTH=10% ALIGN=LEFT><img src='../iconos/logo.jpg' width=60></TD>\n");
      htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" +OP_Proced
                           .getColorAzul() +"'><B>Listado de Documentos/Expedientes Vencidos - (Detallado)</B></FONT><BR>\n");
      htmlFile.write("<FONT size=3>a la Fecha: <B>" + Fecha + "</B></FONT>\n");
      htmlFile.write("<TD WIDTH=10% ALIGN=RIGHT><img src='../iconos/logo.jpg' width=60></TD>\n");
      htmlFile.write("</TR>\n");
      htmlFile.write("</TABLE><BR>\n");
      
      htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD ALIGN=RIGHT width=10% bgcolor='" + OP_Proced
                           .getColorCelesteOscuro() +"'><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Tipo Plazo:&nbsp;</B></FONT></TD>\n");
      htmlFile.write("<TD ALIGN=LEFT  width=90% >&nbsp;<B>"+ TipoPlazo +"</B></TD></TR></TABLE><BR>\n");

//////////////////////////////////////////////Inicio de Tabla de Datos
      
      String Consulta = "SELECT (date '" + OP_Proced
                .Fecha(Fecha,false) +"' - fechaexp) as diasvenc,count(*)"
                       +" FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra,files.tiposplazo"
                       +" WHERE instlegal.estado<>'*' And (date '" +OP_Proced
                .Fecha(Fecha,false) +"' - fechaexp) > plazo " + Filtro 
                       +" and instlegal.idtipoplazo=tiposplazo.idtipo "
                       +" and instlegal.idtipodoc=tiposletra.idtipo "
                       +" and instlegal.idorganismo=tiposletra.idorganismo "
                       +" and instlegal.idorganismo=tiposorganismo.idorganismo "
                       +" and instlegal.idtipoiniciador=tiposiniciador.idtipo  "
                       +" and instlegal.idtipoasunto=tiposasunto.idtipo "
                       +" and instlegal.idcatastro=catastros.idcatastro "
                       +" and instlegal.idprofesional=profesionales.idprofesional "
                       +" Group by diasvenc"
                       +" Order by diasvenc";
                       System.out.println("Consulta 1: " + Consulta);
      ResultSet RegDias = OP_Proced.exConsulta(Consulta);
      while (RegDias.next())
      {
          htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
          htmlFile.write("<TR>\n");
          htmlFile.write("<TD Width=35% ALIGN=CENTER bgcolor='" + OP_Proced
                               .getColorCelesteOscuro() +"'><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Expedientes con Dias "+ RegDias.getString(1) +" de Vencimiento &nbsp;</B></FONT></TD>\n");
          htmlFile.write("<TD Width=40%></TD>\n"); 
          htmlFile.write("<TD Width=25% ALIGN=CENTER bgcolor='" + OP_Proced
                               .getColorCelesteOscuro() +"'><FONT size=2 FACE='Arial' COLOR='#FFFFFF' ><B>Total de Expte. encontrados: &nbsp;"+ RegDias.getString(2) +"</B></TD></TR></TABLE>\n");
          // Listado
          htmlFile.write("<TABLE ALIGN=CENTER WIDTH=800 BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
          htmlFile.write("<TR BGCOLOR='" + OP_Proced
                               .getColorCelesteOscuro() +"'>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=20%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>N?? Expediente</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=9% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Fecha</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=9% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Dias Vdos.</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=10%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>N?? Catastro</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=34%><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Iniciante</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=10% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>Estado</B></FONT></TD>\n");
          htmlFile.write("<TD  ALIGN=CENTER width=8% ><FONT size=2 FACE='Arial' COLOR='#FFFFFF'><B>C.Anexos</B></FONT></TD></TR>\n");              
          
          Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte,"
                    +"fechaexp,(date '" +OP_Proced
                    .Fecha(Fecha,false) +"' - fechaexp) as diasvenc,catastros.catastro,anio,"
                    +"nroexp,idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto,"
                    +"iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro,"
                    +"instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.cantanexo,instlegal.estado,tiposletra.letra"
                    +" FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra,files.tiposplazo"
                    +" WHERE instlegal.estado<>'*' And (date '" +OP_Proced
                    .Fecha(Fecha,false) +"' - fechaexp) > plazo " 
                    +" AND (date '" +OP_Proced
                    .Fecha(Fecha,false) +"' - fechaexp)="+ RegDias.getString(1) + Filtro 
                    +" and instlegal.idtipoplazo=tiposplazo.idtipo "
                    +" and instlegal.idtipodoc=tiposletra.idtipo "
                    +" and instlegal.idorganismo=tiposletra.idorganismo "
                    +" and instlegal.idorganismo=tiposorganismo.idorganismo "
                    +" and instlegal.idtipoiniciador=tiposiniciador.idtipo  "
                    +" and instlegal.idtipoasunto=tiposasunto.idtipo "
                    +" and instlegal.idcatastro=catastros.idcatastro "
                    +" and instlegal.idprofesional=profesionales.idprofesional "
                    +" order by nroexp,anio,fechaexp";
          System.out.println("Consulta 2: " + Consulta);
          ResultSet Reg = OP_Proced.exConsulta(Consulta);
          while (Reg.next())
          {
              htmlFile.write("<TR>");
              htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >&nbsp;"+ Reg.getString(4) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(5) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(6) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >"+ Reg.getString(7) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=LEFT><FONT size=2 FACE='Arial' >"+ Reg.getString(14) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(18) +"</FONT></TD>\n");        
              htmlFile.write("<TD  ALIGN=CENTER><FONT size=2 FACE='Arial' >"+ Reg.getString(22) +"</FONT></TD></TR>\n");        
           }
           htmlFile.write("</TABLE><BR>\n");  
      }
      htmlFile.write("</BODY>\n");
      htmlFile.write("</HTML>\n");
      htmlFile.close();
            OP_Proced.AbreInforme(htmlPath);
      return true;
    } catch (Exception x) 
    {
      x.printStackTrace();
            OP_Proced.Mensaje(x.getMessage(),"");
      return false;
    }
  }
}