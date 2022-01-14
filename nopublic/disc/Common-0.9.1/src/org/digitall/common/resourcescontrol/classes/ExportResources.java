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
 * ExportResources.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class ExportResources extends JFrame {

    private JButton bAbrirArchivo = new JButton();
    private String textoImportar = "";
    private JButton bGenerarArchivo = new JButton();
    //private JButton bRegular = new JButton();
    private JButton btnAux = new JButton();

    private JButton btnInitSueldos = new JButton();

    public ExportResources() {
	   
	try {
	    jbInit();
	} catch (Exception e) {
	    System.out.println("Error");
	    e.printStackTrace();
	}
    }

    private String abrirArchivo(){
	String resultado = "";
	String Text="";
	try{
	   JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
	   int opcion = fc.showOpenDialog(this);
	   if(opcion != fc.CANCEL_OPTION){
	       File Abrir=fc.getSelectedFile(); //Devuelve el File que vamos a leerName
	       if(Abrir!=null){
		  FileReader Fichero=new FileReader(Abrir);
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
 	 System.out.println(ioe);
        }
	   System.out.println("archivo: "+"\n"+resultado);
	   return resultado;
    }
    
    public void stringToFile(String _texto,File _file){
	    FileWriter fWriter;
	    BufferedWriter bWriter;
	    try{
	    fWriter = new FileWriter(_file);
	    bWriter = new BufferedWriter(fWriter);
	    bWriter.write(_texto);
	    bWriter.close();
	    fWriter.close();
	    }catch(IOException e){
		e.printStackTrace();
	    }
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(387, 258));
	this.getContentPane().setLayout(null);
	bAbrirArchivo.setText("Importar Archivo");
	bAbrirArchivo.setBounds(new Rectangle(95, 20, 190, 35));
	bAbrirArchivo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    bAbrirArchivo_actionPerformed(e);
		}
	    }
	);
	bGenerarArchivo.setText("Generar Archivo");
	bGenerarArchivo.setBounds(new Rectangle(95, 75, 190, 35));
	bGenerarArchivo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    bGenerarArchivo_actionPerformed(e);
		}
	    }
	);
	/*bRegular.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
		    bRegular_actionPerformed(e);
		}
	    }
	);
	bRegular.setText("Regular");
	bRegular.setBounds(new Rectangle(95, 130, 190, 35));*/
	//this.getContentPane().add(bRegular, null);
	 /*btnAux.addActionListener(new ActionListener() {
	         
	                 public void actionPerformed(ActionEvent e) {
	                     bRegular_actionPerformed(e);
	                 }
	             }
	         );*/
	this.getContentPane().add(btnInitSueldos, null);
	this.getContentPane().add(bGenerarArchivo, null);
	this.getContentPane().add(bAbrirArchivo, null);
	btnAux.setText("Regular");
	btnAux.setBounds(new Rectangle(95, 130, 190, 35));
	btnAux.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAux_actionPerformed(e);
		}
	    }
	);
	btnInitSueldos.setText("Init Sueldos");
	btnInitSueldos.setBounds(new Rectangle(95, 185, 190, 35));
	btnInitSueldos.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnInitSueldos_actionPerformed(e);
		}
	    }
	);
	this.getContentPane().add(btnAux, null);
    }

    private void bAbrirArchivo_actionPerformed(ActionEvent e) {
	textoImportar = abrirArchivo();
	importar(textoImportar);
    }
    
    private void bGenerarArchivo_actionPerformed(ActionEvent e) {
	int idDesdeResources = 270;
	int idHastaResources = 889;
	Resource recurso;
	JFileChooser fc = new JFileChooser();
	fc.showOpenDialog(this);
	File file=fc.getSelectedFile(); //Devuelve el File que vamos a escribir
	String texto = "";
	//Genera recursos
	System.out.println("Para Tabla Recursos:\n");
	for(int i = idDesdeResources;i<=idHastaResources;i++){
	    recurso = new Resource();
	    recurso.setIdResource(i);
	    recurso.retrieveData();
	    String linea = ""+recurso.getName()+"\t"+recurso.getTotalStock()+"\t"+recurso.getExpenditureAccount().getIDExpenditureAccount()+"\t"+recurso.getSkillToProvider().getIdSkill()+"\t"+recurso.getUnit().getIdUnit()+"\n";
	    System.out.println(linea);
	    texto += linea;
	}
	stringToFile(texto,file);
    }
    
    /*private void bRegular_actionPerformed(ActionEvent e) {
	 int i = 1;
	 boolean hay = true;
	 if(Advisor.question("Aviso","Desea regular?")){
	    while(hay){
		Resource recurso = new Resource();
		recurso.setIdResource(i);
		recurso.retrieveData();
		int id = recurso.getIdResource();
		int cantidad = (Integer)recurso.getTotalStock();
		if(id != -1){
		    if(recurso.isDistinguishable()){
			grabarRecursosDist(id,cantidad);
		    }
		    i++;
		}else{
		    hay = false;
		}
		hay = false;
	    }
	}
    }
    */
    private void grabarRecursosDist(int _idResource,int _cantidad){
	for(int i = 1; i <= _cantidad ; i++){
	    LibSQL.getInt("resourcescontrol.adddistinguishableresources",""+_idResource + ", 1 ,'','',1, -1,0, 0,1, '', 0")   ;
	}
    }
    
    private void importar(String _textExport){
	String text = _textExport;
	int desde = 0;
	while(!text.equals("")){
	    desde = text.indexOf("\n",0) + 1;
	    String linea = text.substring(0,desde);
	    guardarLineaDB(linea);
	    text = text.substring(desde,text.length());
	    System.out.println("resto archivo: \n"+text);
	}
    }
    
    private void guardarLineaDB(String _linea){
	System.out.println("Linea a cargar: ____  "+_linea);
	int desde = 0;
	int hasta = _linea.indexOf("\t",desde);
	String concepto = _linea.substring(desde,hasta);
	desde = hasta + 1;
	hasta = _linea.indexOf("\t",desde);
	double cantidad = Double.parseDouble(_linea.substring(desde,hasta));
	desde = hasta + 1;
	hasta = _linea.indexOf("\t",desde);
	int idCuenta = Integer.parseInt(_linea.substring(desde,hasta));
	desde = hasta + 1;
	hasta = _linea.indexOf("\t",desde);
	int idProveedor = Integer.parseInt(_linea.substring(desde,hasta));
	desde = hasta + 1;
	hasta = _linea.indexOf("\n",desde);
	int idUnit = Integer.parseInt(_linea.substring(desde,hasta));
	System.out.println("------------------");
	System.out.println("Concepto: "+concepto+"\n");
	System.out.println("Cantidad: "+cantidad+"\n");
	System.out.println("idCuenta: "+idCuenta+"\n");
	System.out.println("idProveedor: "+idProveedor+"\n");
	System.out.println("idUnit: "+idUnit+"\n");
	Resource recurso = new Resource();
	Skills proveedor = new Skills();
	Units unit = new Units();
	ExpenditureAccount cuenta = new ExpenditureAccount();
	cuenta.setIDExpenditureAccount(idCuenta);
	unit.setIdUnit(idUnit);
	proveedor.setIdSkill(idProveedor);
	recurso.setUnit(unit);
	recurso.setSkillToProvider(proveedor);
	recurso.setExpenditureAccount(cuenta);
	recurso.setName(concepto);
	recurso.setStock(cantidad);
	recurso.setIdUnit(idUnit); 
	recurso.setDistinguishable(true);
	recurso.setDestined("t");
	recurso.saveData();
	//Distinguibles
	ResourceBrands resourceBrand = new ResourceBrands(recurso);
	resourceBrand.setBrands(new Brands(1));
	resourceBrand.setPrice(0);
	resourceBrand.setMinStock(0);
        resourceBrand.setConsumable(true);
        resourceBrand.consume(resourceBrand.getStock());
        resourceBrand.setProduceable(true);
        resourceBrand.produce(cantidad);
	if (resourceBrand.saveData() >= 0) {
	    resourceBrand.setIdResourceBrand(-1);
	} else {
	    Advisor.messageBox("Ya tiene asignada esta Marca", "Marca no válida");
	}
    }
    /*public static void main(String[] args){
	ExportResources er = new ExportResources();
	er.setVisible(true);
    }*/

    private void btnAux_actionPerformed(ActionEvent e) {
	int i = 256;
	int veces = 0;
	boolean hay = true;
	if(Advisor.question("Aviso","Desea regular?")){
	   while(hay){
	       Resource recurso = new Resource();
	       recurso.setIdResource(i);
	       recurso.retrieveData();
	       int id = recurso.getIdResource();
	       if(id != -1){
		  if((recurso.isDistinguishable())&&(!recurso.getEstado().equals("*"))){
		       String totalStock = "" + recurso.getTotalStock();
		       totalStock = totalStock.substring(0,totalStock.indexOf("."));
		       int cantidad = Integer.parseInt(totalStock);
		       System.out.println("id: "+id +" - cantidad: "+ cantidad);
		       grabarRecursosDist(id,cantidad);
		   }
		   i++;
	       }else{
		   hay = false;
	       }
	       veces++;
	   }
	}
	System.out.println("cantidad de recursos: "+ (veces - 1));
    }

    private void btnInitSueldos_actionPerformed(ActionEvent e) {
	boolean initTipos = LibSQL.executeQuery("INSERT INTO tabs.concepto_tabs(idtipo, descripcion,estado) VALUES (-1, 'Sin Asignar', '')");
	String textArchivo = "";
	if(initTipos){
	    Advisor.messageBox("CARGAR HABERES, ELIJA ARCHIVO","HABERES");
	    textArchivo = abrirArchivo();
	    cargarHaberes(textArchivo);
	    Advisor.messageBox("CARGAR DESCUENTOS, ELIJA ARCHIVO","DESCUENTOS");
	    textArchivo = abrirArchivo();
	    cargarDescuentos(textArchivo);
	}else{
	    Advisor.messageBox("ERROR AL GRABAR LOS DATOS","ERROR");
	}
    }
    
    private void cargarHaberes(String _text){
	//Conceptos concepto = new Conceptos();	
    }
    
    private void cargarDescuentos(String _text){
	
    }
}
