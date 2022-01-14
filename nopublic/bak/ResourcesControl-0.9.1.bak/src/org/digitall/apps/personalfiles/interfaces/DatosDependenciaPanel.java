package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;

public class DatosDependenciaPanel extends BasicPanel{

    private BasicPanel bPEste = new BasicPanel();
    private BasicPanel bPCenter = new BasicPanel();
    private BasicPanel bPCenterNorte = new BasicPanel();
    private JPanel bPCenterCentro = new JPanel();
    private BasicPanel bPCenterSur = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    
    private BasicLabel blNombre = new BasicLabel();   
    private BasicLabel blNJerarquico = new BasicLabel();        
    private Dependencia dependencia = null;
    private int niveles = 0;
    private int cantPadres = 0;
    private BasicLabel blIdDep = new BasicLabel();
    private String nombreTipoDependencia = "";

    public DatosDependenciaPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public DatosDependenciaPanel(Dependencia _dependencia) {
	try {
	    dependencia = _dependencia;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {	
	this.setLayout(borderLayout1);	
	this.setPreferredSize(new Dimension(276, 21));
	this.setSize(new Dimension(276, 21));
	this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	
	bPEste.setLayout(null);
	bPEste.setSize(new Dimension(271, 16));
	bPEste.setPreferredSize(new Dimension(272, 16));
	bPEste.setBackground(new Color(214, 231, 255));
	bPEste.setBorder(BorderFactory.createLineBorder(new Color(165, 165, 165), 1));
	bPCenter.setBackground(new Color(255, 214, 214));
	bPCenter.setLayout(borderLayout2);
	bPCenter.setPreferredSize(new Dimension(12, 17));
	bPCenterNorte.setSize(new Dimension(12, 8));
	bPCenterNorte.setPreferredSize(new Dimension(12, 8)); 
	bPCenterNorte.setBackground(Color.white);
	//bPCenterCentro.setPreferredSize(new Dimension(0, 1));	
	bPCenterCentro.setBackground(new Color(165, 198, 255));
	//bPCenterCentro.setSize(new Dimension(12, 1));
	bPCenterSur.setPreferredSize(new Dimension(12, 8));	
	bPCenterSur.setSize(new Dimension(12, 8));
	bPCenterSur.setBackground(Color.white);
	bPCenter.add(bPCenterNorte,BorderLayout.NORTH);	
	bPCenter.add(bPCenterCentro,BorderLayout.CENTER);
	bPCenter.add(bPCenterSur,BorderLayout.SOUTH);
	blNombre.setText("jLabel2");
	blNombre.setBounds(new Rectangle(50, 1, 220, 15));
	blNJerarquico.setBounds(new Rectangle(5, 1, 15, 15));
	blNJerarquico.setPreferredSize(new Dimension(20, 15));
	blNJerarquico.setFont(new Font("Lucida Sans", 0, 10));
	blNJerarquico.setForeground(new Color(165, 165, 165));
	blIdDep.setBounds(new Rectangle(20, 1, 30, 15));
	blIdDep.setPreferredSize(new Dimension(35, 15));
	blIdDep.setSize(new Dimension(30, 15));
	blIdDep.setFont(new Font("Lucida Sans", 0, 10));
	blIdDep.setForeground(new Color(99, 99, 99));
	blNombre.setFont(new Font("Lucida Sans", 0, 10));
	blNombre.setForeground(Color.black);
	bPEste.add(blNombre, null);
	bPEste.add(blNJerarquico, null);
	bPEste.add(blIdDep, null);
	this.add(bPEste, BorderLayout.EAST);
	this.add(bPCenter, BorderLayout.CENTER);
	
    }
	
   public void loaddata() {           	
       String text  = "";
       if ((dependencia.getNombre().equals("ORGANIGRAMA GENERAL"))||(dependencia.getNombreNivelJerarquico().equals("INTENDENCIA"))||(!dependencia.isMostrarNombreTipDep())) {
	   text = dependencia.getNombre();
       } else {
	   text = dependencia.getNombreNivelJerarquico() + " " + dependencia.getNombre();   
       }       
       blNombre.setText(text);               
       blNJerarquico.setText(String.valueOf(dependencia.getNivelJerarquico()));                     
       blIdDep.setText(String.valueOf(dependencia.getIdDep()));              
   }
    
   public void paintSelectedPanel(){
       bPEste.setBorder(BorderFactory.createLineBorder(Color.RED, 1));       
       bPEste.setBackground(Color.white);
       blNombre.setForeground(Color.black);       
       blNJerarquico.setForeground(new Color(165, 165, 165));
       bPCenterCentro.setBackground(Color.RED);
   }
    
   public void setPreferredSize() {	
	int nivelJerarquicoPadre;
	int nivelJerarquicoHijo;
	int nivel = 0;                 
	int width;
	
	Dependencia dependenciaPadre = dependencia.getDependenciaPadre();
	if ((dependencia.getNivelJerarquico() != 0)&&(dependencia.getNivelJerarquico() != dependenciaPadre.getNivelJerarquico())) {
	    niveles = 0;
	    cantPadres = 0;
	    getNiveles(dependencia);	  
	    niveles = niveles - cantPadres -1;	     	    
	    //niveles--;
	    width = niveles * 20 + 276;
	    this.setPreferredSize(new Dimension(width, 21));
	    this.setSize(new Dimension(width, 21));     
	}if (dependencia.getNivelJerarquico() != 0){
	    niveles = 0;
	    cantPadres = 0;
	    getNiveles(dependencia);
	    niveles = niveles - 1;
	    width = niveles * 20 + 276;
	    this.setPreferredSize(new Dimension(width, 21));
	    this.setSize(new Dimension(width, 21));     
	}
		
/*	 if (dependencia.getNivelJerarquico() != 0) {
	     niveles = 0;
	     cantPadres = 0;
	     getNiveles(dependencia);
	     getCantDePadres(dependencia);
	     Dependencia dependenciaPadre = dependencia.getDependenciaPadre();
	     if (dependenciaPadre.getNivelJerarquico() == dependencia.getNivelJerarquico())  {
		niveles = dependencia.getNivelJerarquico() - cantPadres + 1;	    
	     } else {
	         niveles = dependencia.getNivelJerarquico() - cantPadres;
	     }		
	      
	     //niveles--;
	     width = niveles * 20 + 276;
	     this.setPreferredSize(new Dimension(width, 21));
	     this.setSize(new Dimension(width, 21));     
	 }*/
    }
    //Obtiene nivel que deberia de tener en el arbol si no se ubieran salteado del arbol
    //ningun nivel gerarquico; por otro lado obtiene la cantidad de nodos que existen
    //por arriba del nodo que se desea agregar
   public void getNiveles(Dependencia _dependencia) {
	Dependencia dependenciaPadre = _dependencia.getDependenciaPadre();
	int nivelJerarquicoPadre = dependenciaPadre.getNivelJerarquico();
	if (nivelJerarquicoPadre != 0) {	    
	    int nivelJerarquicoHijo = _dependencia.getNivelJerarquico();
	    niveles = niveles + (nivelJerarquicoHijo - nivelJerarquicoPadre);	    	    
	    cantPadres++;
	    getNiveles(dependenciaPadre);	    
	}else {	    	    
	    int nivelJerarquicoHijo = _dependencia.getNivelJerarquico();
	    niveles = niveles + (nivelJerarquicoHijo - nivelJerarquicoPadre);  	  
	}	
    }    
    
    public void getCantDePadres(Dependencia _dependencia) {
	Dependencia dependenciaPadre = _dependencia.getDependenciaPadre();
	int nivelJerarquicoPadre = dependenciaPadre.getNivelJerarquico();
	if (nivelJerarquicoPadre != 0) {            
	    cantPadres++;
	    getNiveles(dependenciaPadre);           
	}else {             
	    cantPadres++;
	}
    }
}
