package org.digitall.apps.resourcescontrol_091.classes;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;

public class ExportResources extends JFrame{

    private JButton bAbrirArchivo = new JButton();
    private String textoExportar = "";

    public ExportResources() {

	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private String abrirArchivo(){
	String resultado = "";
	String Text="";
	try{
	   JFileChooser fc=new JFileChooser(System.getProperty("user.dir"));
	   fc.showOpenDialog(this);
	   File Abrir=fc.getSelectedFile(); //Devuelve el File que vamos a leerName
	   if(Abrir!=null){
	      FileReader Fichero=new FileReader(Abrir);
	      BufferedReader leer=new BufferedReader(Fichero);
	      while((Text=leer.readLine())!=null){
		 resultado+= (Text+ "\n"); //append Concatena la linea leida
		}
	      leer.close();
	    }                 
	  }
	  catch(IOException ioe){
	    System.out.println(ioe);
	   }
	   System.out.println("archivo: "+"\n"+resultado);
	   return resultado;
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(387, 149));
	this.getContentPane().setLayout(null);
	bAbrirArchivo.setText("Abrir Archivo");
	bAbrirArchivo.setBounds(new Rectangle(95, 50, 190, 35));
	bAbrirArchivo.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    bAbrirArchivo_actionPerformed(e);
		}
	    }
	);
	this.getContentPane().add(bAbrirArchivo, null);
    }

    private void bAbrirArchivo_actionPerformed(ActionEvent e) {
	textoExportar = abrirArchivo();
	exportar(textoExportar);
    }
    
    private void exportar(String _textExport){
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
	recurso.setDescription("t");
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
}
