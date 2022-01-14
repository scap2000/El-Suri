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
 * AdministracionHoras.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import java.sql.ResultSet;

import java.sql.SQLException;

import javax.imageio.ImageIO;

import javax.swing.JFileChooser;

import javax.swing.JSeparator;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import java.sql.*;


public class AdministracionHoras extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private PrintButton btnPrint = new PrintButton();
    private CloseButton btnClose = new CloseButton();
    private BasicCheckBox chCargarArchivo = new BasicCheckBox("Cargar Fichadas");
    private AcceptButton btnCargarArchivo = new AcceptButton();
    private String contenidoArchivo = "";
    private JSeparator separador = new JSeparator();
    private AssignButton btnConectarDB1 = new AssignButton();
    private AssignButton btnConectarDB2 = new AssignButton();
    private BasicLabel lbConectarDB1 = new BasicLabel();
    private BasicLabel lbConectarDB2 = new BasicLabel();
    private AccessExtractData accessExtract = new AccessExtractData();
    private UnAssignButton btnImportarTarjetas = new UnAssignButton();
    private TFInput tfFechaDesde = new TFInput(DataTypes.DATE, "Fecha Desde", false);
    private TFInput tfFechaHasta = new TFInput(DataTypes.DATE, "Fecha Hasta", false);

    public AdministracionHoras() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	content.setLayout(null);
	content.setPreferredSize(new Dimension(200, 200));
	content.setSize(new Dimension(200, 200));
        this.setSize(new Dimension(257, 199));
        chCargarArchivo.setSize(new Dimension(100, 20));
	chCargarArchivo.setBounds(new Rectangle(20, 130, 155, 20));
	chCargarArchivo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    chCargarArchivo_actionPerformed(e);
		}
	    }
	);
        btnCargarArchivo.setBounds(new Rectangle(210, 130, 20, 20));
        tfFechaDesde.setBounds(new Rectangle(15, 85, 95, 35));
        tfFechaHasta.setBounds(new Rectangle(145, 85, 95, 35));
	btnCargarArchivo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnCargarArchivo_actionPerformed(e);
		}
	    }
	);
        btnImportarTarjetas.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnImportar_actionPerformed(e);
                }
            }
        );
        separador.setBounds(new Rectangle(0, 75, 255, 10));
        btnConectarDB1.setBounds(new Rectangle(5, 10, 30, 25));
        btnConectarDB2.setBounds(new Rectangle(5, 45, 30, 25));
        btnImportarTarjetas.setBounds(new Rectangle(5, 10, 30, 25));
        btnConectarDB1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConectarDB1_actionPerformed(e);
                }
            });
        btnConectarDB2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConectarDB2_actionPerformed(e);
                }
            });
        lbConectarDB1.setBounds(new Rectangle(40, 10, 210, 25));
        lbConectarDB2.setBounds(new Rectangle(40, 45, 210, 25));
        lbConectarDB1.setText("Conectar con reloj (SUPE)");
        lbConectarDB2.setText("Conectar con reloj (PERSONAL)");
        btnConectarDB1.setToolTipText("Conectar con reloj (SUPE)");
        btnConectarDB2.setToolTipText("Conectar con reloj (PERSONAL)");
        btnImportarTarjetas.setToolTipText("Importar tarjetas");
        btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    btnClose_actionPerformed(e);
					}
				    }
	);

        btnPrint.setToolTipText("Imprimir listado de horas trabajadas");
        btnPrint.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnPrint_actionPerformed(e);
		}
	    }
	);
        content.add(separador, null);
        content.add(chCargarArchivo);
        content.add(btnCargarArchivo);
        content.add(tfFechaDesde);
        content.add(tfFechaHasta);
        content.add(btnConectarDB1);
        content.add(btnConectarDB2);
        content.add(lbConectarDB1);
        content.add(lbConectarDB2);
        add(content, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnPrint);
        addButton(btnImportarTarjetas);
	enabledLoadFile(false);
        loadDefaultDate();
        btnCargarArchivo.setEnabled(false);
        btnImportarTarjetas.setEnabled(false);
        chCargarArchivo.setEnabled(false);
        btnPrint.setEnabled(false);
    }
    
    /**
     * Habilita o deshabilita la carga del archivo segun el parametro
     * @param _enabled
     */
    private void enabledLoadFile(boolean _enabled){
	btnCargarArchivo.setEnabled(_enabled);
        btnImportarTarjetas.setEnabled(_enabled);
    }
    
    /**
     * Carga los campos mes y anio con los valores correspondientes a la fecha actual
     */
    private void loadDefaultDate (){
	/*tfDia.setValue(Integer.parseInt(Environment.currentDay));
	tfMes.setValue(Integer.parseInt(Environment.currentMonth));
	tfAnio.setValue(Integer.parseInt(Environment.currentYear));*/
    }

    private void chCargarArchivo_actionPerformed(ActionEvent e) {
	enabledLoadFile(chCargarArchivo.isSelected());
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }

    
    /**
     * Abre el archivo seleccionado
     * @return
     */
  /*  
    private String abrirArchivo(){
	String resultado = "";
	String Text="";
	try{
	   JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
	   int opcion = fc.showOpenDialog(this);
	   if (!Environment.cfg.getProperty("pictures").equalsIgnoreCase(ConfigFile.nullProperty))  {
	       fc.setCurrentDirectory(new File(Environment.cfg.getProperty("pictures")));
	   }
	   if(opcion != fc.CANCEL_OPTION){
	       abrir=fc.getSelectedFile(); //Devuelve el File que vamos a leerName
	       if(abrir!=null){
		  FileReader Fichero=new FileReader(abrir);
		  BufferedReader leer=new BufferedReader(Fichero);
		  while((Text=leer.readLine())!=null){
		     resultado+= (Text+ "\n"); //append Concatena la linea leida
		    }
		  leer.close();
		  Fichero.close();
		}      
	   }
       }
       catch(IOException ioe){
	}
	   return resultado;
    }
*/
    private void btnCargarArchivo_actionPerformed(ActionEvent e) {
	//setArchivo();
	cargarFichada();
    }
    
    private void btnImportar_actionPerformed(ActionEvent e){
        importarTarjetas();
    }
    
    private void importarTarjetas(){
        ResultSet rs =  null;
        if (accessExtract.getConnPERS() != null) {
            rs = accessExtract.extraerTarjetas(accessExtract.getConnPERS());
        }
        try {
            while(rs.next()){
                LibSQL.getInt("sueldos.addTarjeta",""+rs.getString(1)+","+rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (accessExtract.getConnSUPE() != null) {
            rs = accessExtract.extraerTarjetas(accessExtract.getConnSUPE());
        }
        try {
            while(rs.next()){
                LibSQL.getInt("sueldos.addTarjeta",""+rs.getString(1)+","+rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	imprimirListado();
    }
    
    private void imprimirListado() {
	//if ((tfAnio.getInteger() > 0) && (tfMes.getInteger() > 0)) {
	if (true) {
	    BasicReport report = new BasicReport(AdministracionHoras.class.getResource("xml/ListadoHorasTrabajadas.xml"));
	    /* si va
            report.addTableModel("sueldos.xmlGetListadoHorasTrabajadas", tfMes.getInteger() +"," +tfAnio.getInteger());
	    report.setProperty("mes",tfMes.getInteger());
	    report.setProperty("anio",tfAnio.getInteger());
            */
	    report.doReport();          
	} else {
	    Advisor.messageBox("Los campos Mes y Año no pueden estar vacios.","Aviso");
	}
    }
    
    private void setArchivo(){
	if (control()) { 
	    cargarArchivo();
	} 
    }
    
    private void cargarFichada(){
	boolean resultado = (Proced.compareDates(Proced.setFormatDate(tfFechaDesde.getString(),false),Proced.setFormatDate(tfFechaHasta.getString(),false)) != 2) ;
	if (resultado) {
	    if (accessExtract.getConnPERS() != null) {
                saveFichadas(accessExtract.getConnPERS());
            }
	    if (accessExtract.getConnSUPE() != null) {
	        saveFichadas(accessExtract.getConnSUPE());
	    }
	} else {
	        Advisor.messageBox("La fecha de inicio no puede ser mayor que la fecha de fin.","Error");
	    }
    }

    private void saveFichadas(Connection _conn) {
        boolean primeraVez = true;
        boolean aceptaContinuar = true;
        boolean error = false;
        String params = "";
        String linea = "";
        //Armar la consulta que levante los registros de la tabla fichada con la fecha seleccionada
        ResultSet rs = null;
        rs = accessExtract.extraerFichadas(tfFechaDesde.getDate() , tfFechaHasta.getDate() ,_conn); //cargar
        try {
            while ((rs.next()) && (!error)) {
                //armar la linea
                linea = armarLinea(rs);
                if (primeraVez) {
                    primeraVez = false;
                    params = "" + tfFechaDesde.getDate() + "," + tfFechaHasta.getDate();
                    
                    if (LibSQL.getBoolean("sueldos.existeRegistro", params)) {
                        if (Advisor.question("Registros grabados",
                                             "Durante ya fue efectuado. ¿Desea sobreescribir?")) {
                            error =
                                    limpiarDetalle(tfFechaDesde.getDate() , tfFechaHasta.getDate());
                            agregarDetalle(linea, -1);
                        } else {
                            aceptaContinuar = false;
                        }
                    } else {
                        //id = agregarEncabezado();//esta linea deberia sacarse
                        agregarDetalle(linea, -1);
                    }
                    /*
	     if (id == -1) {
	         error = true;
	     }
	     agregarDetalle(linea, id);// esta linea deberia sacarse();*/
                } else {
                    agregarDetalle(linea, -1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!error) {
            if (aceptaContinuar) {
                /*String params2 =
                    "" + tfAnio.getInteger() + "," + tfMes.getInteger() + "," +
                    tfDia.getInteger();
                */
                String params2 = "" + tfFechaDesde.getDate() + "," + tfFechaHasta.getDate();
                if (LibSQL.getBoolean("sueldos.approveEncabezadosPeriodo", params2)) {
                    Advisor.messageBox("Se grabaron con éxito las fichadas.",
                                       "Mensaje");
                } else {
                    Advisor.messageBox("Ha ocurrido un error al grabar los datos.",
                                       "Error");
                }
                //FICHADAS.APPROVE();
            }
        } else {
            Advisor.messageBox("Ha ocurrido un error al grabar los datos.",
                               "Error");
        }
    }
    
    private String armarLinea(ResultSet _rs){
        String resultado = "";
        try {
            resultado = resultado + _rs.getString(1) + "," + _rs.getString(2) + "," + _rs.getString(3) + "," + _rs.getString(4) + "," + _rs.getString(5) + "," + _rs.getString(6) + "," + _rs.getString(7) + "," + _rs.getString(8) + "," + _rs.getString(9);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return resultado;
    }
    private boolean  control(){
	//boolean resultado = ((tfDia.getInteger() != 0) && (tfMes.getInteger() != 0) && (tfAnio.getInteger() != 0) && (tfMes.getInteger() <= 12));
	boolean resultado = true;
	if (resultado) {
	    if (contenidoArchivo.equals("")) {
		resultado = false;
	        Advisor.messageBox("Debe cargar un archivo.","Aviso");
	    }
	} else {
	    //if (tfMes.getInteger() > 12) {
        if (true) {
	        Advisor.messageBox("El mes ingresado no puede ser mayor a 12.","Aviso");
	    } else {
	        Advisor.messageBox("Los campos dias, mes y año deber estar ingresados.","Aviso");
	    }
	}
	return resultado;
    }
    
    /**
     * Carga el archivo en la base de datos
     */
    private void cargarArchivo(){
	boolean primeraVez = true;
	boolean aceptaContinuar = true;
	boolean error = false;
	int id = -1;
	
	String contenArchivo = contenidoArchivo ;
	String linea = "";
	String params = "";
	while ((contenArchivo.length() != 0) && (aceptaContinuar) && (!error)) {
	    linea = contenArchivo.substring(0,contenArchivo.indexOf("\n"));
	    contenArchivo = contenArchivo.substring(contenArchivo.indexOf("\n") + 1,contenArchivo.length());
	    if (linea != "") {
		if (primeraVez) {
		    primeraVez = false;
		    //OBJETO FICHADAS = NEW OBJETO FICHADAS
		    //SI (ANIO, MES Y DIA YA ESTAN GUARDADOS EN BASE DE DATOS) { // SE OBTIENE CANTIDAD DE REGISTROS GRABADOS ESE ANIO MES Y DIA
		    //	PREGUNTAR SI DESEA SOBREESCRIBIR
		    //	SI (DESEA SOBREESCRIBIR){
		    //		FICHADAS.SETID(ID);
		    //		FICHADAS.RETRIEVEDATA();
		    //		FICHADAS.LIMPIARDETALLE();
		    //		agregarDetalle(linea);
		    //	} SI NO {
		    //          aceptaContinuar = false;
		    //	} SI NO {
		    //		FICHADAS.SAVE(); // SE AGREGA CON LOS VALORES POR DEFECTO
		    //	}
		    //}
		    //params =  "" + tfAnio.getInteger() + "," + tfMes.getInteger() + "," + tfDia.getInteger();
		    params =  "";
		    if (LibSQL.getBoolean("sueldos.existeRegistro",params)) {
			/*if (Advisor.question("Registro grabado","El registro del dia " + tfDia.getInteger() + "/" + tfMes.getInteger() + "/" + tfAnio.getInteger() + " ya fue efectuado. ¿Desea sobreescribir?")) {
			        error = limpiarDetalle(tfAnio.getInteger() , tfMes.getInteger() , tfDia.getInteger());
				agregarDetalle(linea, -1);
			} else {
			    aceptaContinuar = false;
			}*/
		    }else{
		        //id = agregarEncabezado();//esta linea deberia sacarse
		        agregarDetalle(linea, -1);
		    }
		    /*
		    if (id == -1) {
			error = true;
		    }
		    agregarDetalle(linea, id);// esta linea deberia sacarse();*/
		} else {
		    agregarDetalle(linea, -1);
		}
	    }
	}
	if (!error) {
	    if (aceptaContinuar) {
	        //String params2 = ""+tfAnio.getInteger()+","+tfMes.getInteger()+","+tfDia.getInteger();
	        String params2 = "";
                if (LibSQL.getBoolean("sueldos.approveEncabezados", params2)) {
                    Advisor.messageBox("Se grabaron con éxito las fichadas.","Mensaje");
                } else{
                    Advisor.messageBox("Ha ocurrido un error al grabar los datos.","Error");
                }
	        //FICHADAS.APPROVE();
	    }    
	}else {
	    Advisor.messageBox("Ha ocurrido un error al grabar los datos.","Error");
	}
	
    }
    
    /**
     * Devuelve -1 si el registro no esta en la base de datos en base a los parametro _anio , _mes y _dia
     * @param _anio
     * @param _mes
     * @param _dia
     * @return
     */
    private int estaFichada(int _anio, int _mes, int _dia){
	int resultado = -1;
	return resultado;
    }
    
    private boolean limpiarDetalle(String _fechaDesde, String _fechaHasta){
	String params =  "" + _fechaDesde + "," + _fechaHasta ;
	return !LibSQL.getBoolean("sueldos.limpiarDetalle",params);
    }
    
    /**
     * Dada una linea se la desarma y se la agrega al objeto detalle y se agrega a la base de datos
     * @param _linea
     */
    private void agregarDetalle(String _linea){
	//DETALLEFICHADAS DETALLE_FICHADAS = NEW DETALLEFICHADAS();
	//Agregar a la base de datos en la tabla detalle
	String[] lineaDesarmada = _linea.split("/t");
	for (int i = 0; i < lineaDesarmada.length; i++) {
	    //DETALLE_FICHADAS.SETLEGAJO(lineaDesarmada[0]);
	    //DETALLE_FICHADAS.SETIDTARJETA(lineaDesarmada[1]);
	    //DETALLE_FICHADAS.SETFECHA(lineaDesarmada[2]);
	    //DETALLE_FICHADAS.SETHORA(lineaDesarmada[3]);
	    //DETALLE_FICHADAS.SETTIPOFICHADA(lineaDesarmada[4]);
	    //DETALLE_FICHADAS.SETRELOJ(lineaDesarmada[5]);
	    //DETALLE_FICHADAS.SETUNKNOW(lineaDesarmada[6]);
	    //DETALLE_FICHADAS.SETUNKNOW2(lineaDesarmada[7]);
	    //DETALLE_FICHADAS.SETIDFICHADAS(FICHADAS.GETID());    
	}
    }
    
     /**
      * Dada una linea se la desarma y se la agrega al objeto detalle y se agrega a la base de datos
      * @param _linea
      */
     private int agregarDetalle(String _linea, int _id){ // Deberia usarse el otro metodo agregarDetalle(String _linea);
	String params =  "";
	int resultado = 1;
	 //DETALLEFICHADAS DETALLE_FICHADAS = NEW DETALLEFICHADAS();
	 //Agregar a la base de datos en la tabla detalle
	 String[] lineaDesarmada = _linea.split(",");
         params = "" + lineaDesarmada[2] + ",'" + lineaDesarmada[3].toString() + "','" +lineaDesarmada[4]+ "','" +lineaDesarmada[5]+
	     "','" +lineaDesarmada[6]+ "','" +lineaDesarmada[7]+ "','" +lineaDesarmada[8]+ "'," + _id;
	 resultado =  LibSQL.getInt("sueldos.addDetalleFichada",params);
	 return resultado;
     }
    
    private int agregarEncabezado(){
	//String params = "-1,-1,"+tfAnio.getInteger()+","+tfMes.getInteger()+",0,0";
        String params = "";
	int resultado = LibSQL.getInt("sueldos.addEncabezadoFichadas",params);
	return resultado;
    }

    private void btnConectarDB1_actionPerformed(ActionEvent e) {
        conectarDBAccesDB1();
    }
    
    private void btnConectarDB2_actionPerformed(ActionEvent e) {
        conectarDBAccesDB2();
    }
    
    private void conectarDBAccesDB1() {
        if (accessExtract.conectarDB1()) {
            btnConectarDB1.setEnabled(false);
            chCargarArchivo.setEnabled(true);
            btnPrint.setEnabled(true);
            Advisor.messageBox("Se ha conectado exitosamente al reloj (SUPE).", "Aviso");
        } else {
            Advisor.messageBox("No se pudo conectar al reloj (SUPE).", "Error");
        }
    }
    
    private void conectarDBAccesDB2(){
        if (accessExtract.conectarDB2()) {
            btnConectarDB1.setEnabled(false);
            chCargarArchivo.setEnabled(true);
            btnPrint.setEnabled(true);
            Advisor.messageBox("Se ha conectado exitosamente al reloj (PERSONAL).", "Aviso");
        } else {
            Advisor.messageBox("No se pudo conectar al reloj (PERSONAL).", "Error");
        }
    }
}
