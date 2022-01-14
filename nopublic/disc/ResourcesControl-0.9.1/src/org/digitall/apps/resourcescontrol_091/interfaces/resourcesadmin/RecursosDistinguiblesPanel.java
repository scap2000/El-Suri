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
 * RecursosDistinguiblesPanel.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.JTextField;

import org.digitall.apps.resourcescontrol_091.classes.DistinguishableResources;
import org.digitall.apps.resourcescontrol_091.classes.Resources;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class RecursosDistinguiblesPanel extends BasicContainerPanel{
    private BasicPanel bpCentro = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private int[] sizeColumnList = {187,89,70, 93, 106,215};
    private Vector dataRow = new Vector();
    private GridPanel jpList = new GridPanel(30, sizeColumnList, "Recursos distinguibles", dataRow);
    private Vector headerList = new Vector();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel bpCentroNorte = new BasicPanel();
    private CBInput cbiMarca = new CBInput(0,"Brand",false);
    private TFInput tfiFechaAdquisicion = new TFInput(DataTypes.DATE,"AcquisitionDate",false);
    private TFInput tfiFechaBaja = new TFInput(DataTypes.DATE,"DropOffDate",false);
    private CBInput cbiDeposito = new CBInput(0,"Depots",false);
    private CBInput cbiResponsable = new CBInput(0,"PersonCharge",false);
    private TFInput tfiDesgaste = new TFInput(DataTypes.DOUBLE,"LifeTime",false);
    private TFInput tfiTipoDesgaste = new TFInput(DataTypes.STRING,"LifeTimeTypes",false);
    private CBInput cbiTipoIdentificacion = new CBInput(0,"Identification",false);
    private TFInput tfiNroIdentificacion = new TFInput(DataTypes.STRING,"IdentificationNumber",false);
    private BasicLabel jLabel3 = new BasicLabel();
    private AssignButton btnAddRecursoDistinguible = new AssignButton(true);
    private DeleteButton btnDeleteRecursoDistinguible = new DeleteButton();
    private AddButton btnNewAssign = new AddButton();
    private CBInput cbiRecurso = new CBInput(0,"Resource",false);
    private TFInput tfiBuscarResponsable = new TFInput(DataTypes.STRING,"SearchButton",false);       
    private DistinguishableResources distinguishableResources = null;
    private String idRecursodistinguible = null;
    private FindButton bBuscar = new FindButton();    
    private int idTipoDesgaste = -1;
    private TFInput tfiBuscarRecurso = new TFInput(DataTypes.STRING,"SearchButton",false);
    private TFInput tfiBuscarMarca = new TFInput(DataTypes.STRING,"SearchButton",false);
    private TFInput tfiPrecio = new TFInput(DataTypes.DOUBLE,"Price",false);
    private TFInput tfiStock = new TFInput(DataTypes.INTEGER,"Stock",false);

    public RecursosDistinguiblesPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(585, 538));
	this.setPreferredSize(new Dimension(585, 510));
	bpCentroNorte.setBorder(BorderPanel.getBorderPanel("Agregar / modificar  un recurso distinguible"));
	bpCentroNorte.setSize(new Dimension(580, 245));
	bpCentroNorte.setPreferredSize(new Dimension(560, 280));
	bpCentroNorte.setLayout(null);
	cbiMarca.setBounds(new Rectangle(150, 65, 260, 35));	
	tfiFechaAdquisicion.setBounds(new Rectangle(340, 115, 105, 35));
	tfiFechaBaja.setBounds(new Rectangle(455, 115, 105, 35));
	cbiDeposito.setBounds(new Rectangle(20, 160, 255, 35));
	cbiResponsable.setBounds(new Rectangle(150, 205, 280, 35));
	tfiDesgaste.setBounds(new Rectangle(500, 160, 60, 35));	
	tfiTipoDesgaste.setEnabled(false);
	tfiTipoDesgaste.setBounds(new Rectangle(370, 160, 120, 35));
	cbiTipoIdentificacion.setBounds(new Rectangle(20, 115, 155, 35));
	tfiNroIdentificacion.setBounds(new Rectangle(185, 115, 120, 35));
	bpCentro.setLayout(borderLayout2);
	bpCentroNorte.add(tfiStock, null);
	bpCentroNorte.add(tfiPrecio, null);
	bpCentroNorte.add(tfiBuscarMarca, null);
	bpCentroNorte.add(tfiBuscarRecurso, null);
	bpCentroNorte.add(cbiRecurso, null);
	bpCentroNorte.add(tfiBuscarResponsable, null);
	bpCentroNorte.add(tfiNroIdentificacion, null);
	bpCentroNorte.add(cbiTipoIdentificacion, null);
	bpCentroNorte.add(tfiTipoDesgaste, null);
	bpCentroNorte.add(tfiDesgaste, null);
	bpCentroNorte.add(cbiResponsable, null);
	bpCentroNorte.add(cbiDeposito, null);
	bpCentroNorte.add(tfiFechaBaja, null);
	bpCentroNorte.add(tfiFechaAdquisicion, null);
	bpCentroNorte.add(cbiMarca, null);
	bpCentroNorte.add(jLabel3, null);
	bpCentroNorte.add(btnDeleteRecursoDistinguible, null);
	bpCentroNorte.add(btnAddRecursoDistinguible, null);
	bpCentroNorte.add(btnNewAssign, null);
	bpCentroNorte.add(bBuscar, null);
	btnDeleteRecursoDistinguible.setEnabled(false);
	bpCentro.add(bpCentroNorte, BorderLayout.NORTH);
	bpCentro.add(jpList, BorderLayout.CENTER);
	this.add(bpCentro, BorderLayout.CENTER);
	jLabel3.setBounds(new Rectangle(15, 250, 470, 15));
	jLabel3.setText("Puede agregar o borrar recursos distinguibles con los botones a su derecha -->");
	jLabel3.setMinimumSize(new Dimension(28, 33));
	btnAddRecursoDistinguible.setBounds(new Rectangle(510, 245, 30, 25));
	btnAddRecursoDistinguible.addActionListener(new ActionListener() {

						 public void actionPerformed(ActionEvent e) {
						     btnAddRecursoDistinguible_actionPerformed(e);
						 }

					     }
	);
	btnDeleteRecursoDistinguible.setBounds(new Rectangle(540, 245, 30, 25));
	btnDeleteRecursoDistinguible.addActionListener(new ActionListener() {

						    public void actionPerformed(ActionEvent e) {
							btnDeleteRecursoDistinguible_actionPerformed(e);
						    }

						}
	);
	cbiRecurso.setBounds(new Rectangle(150, 25, 260, 35));
	tfiBuscarResponsable.setBounds(new Rectangle(20, 205, 120, 35));
	btnNewAssign.setBounds(new Rectangle(480, 245, 30, 25));
	btnNewAssign.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnNewAssign_actionPerformed(e);
				    }

				}
	);
	bBuscar.setBounds(new Rectangle(415, 80, 30, 20));
	bBuscar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bBuscar_actionPerformed(e);
			       }

			   }
	);
	tfiBuscarRecurso.setBounds(new Rectangle(20, 25, 120, 35));
	tfiBuscarRecurso.getTextField().addKeyListener(new KeyAdapter() {

				     public void keyReleased(KeyEvent e) {
					 tfiBuscarRecurso_keyReleased(e);
				     }

				 }
	);
	tfiBuscarMarca.setBounds(new Rectangle(20, 65, 120, 35));
	tfiBuscarMarca.getTextField().addKeyListener(new KeyAdapter() {

				   public void keyReleased(KeyEvent e) {
				       tfiBuscarMarca_keyReleased(e);
				   }

			       }
	);
	tfiPrecio.setBounds(new Rectangle(480, 65, 80, 35));
	tfiStock.setBounds(new Rectangle(480, 25, 80, 35));
	tfiStock.setEnabled(false);
	tfiBuscarResponsable.getTextField().addKeyListener(new KeyAdapter() {
			
					 public void keyReleased(KeyEvent e) {					     
					     tfiBuscarResponsable_keyReleased(e);
					 }

				     }
	);
	setHeaderList();
		
	cbiDeposito.autoSize();
	cbiResponsable.autoSize();	
	cbiTipoIdentificacion.autoSize();	
	cbiRecurso.autoSize();
	cbiMarca.autoSize();	
	
	loadCombo();
    }
    
    public void loadCombo() {
	cbiTipoIdentificacion.loadJCombo(LibSQL.exFunction("personalfiles.getAllIdentificationTypeResources", ""));   	
	cbiDeposito.loadJCombo(LibSQL.exFunction("personalfiles.getAllDepots", ""));     
	cbiResponsable.loadJCombo(LibSQL.exFunction("personalfiles.getAllPersonsCombo", "''"));               
	cbiRecurso.loadJCombo(LibSQL.exFunction("personalfiles.getAllResourcesCombo", "''"));      	
	cbiRecurso.setSelectedValue("Todos");
	cbiMarca.loadJCombo(LibSQL.exFunction("personalfiles.getAllBrandsCombo", "''"));               			
	cbiMarca.setSelectedValue("Todas");
    }
    
    private void setHeaderList() {      
	
	headerList.removeAllElements();
	
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("*");
	headerList.addElement("Marca");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Tip. ID");	
	headerList.addElement("ID"); 
	headerList.addElement("*");
	headerList.addElement("Deposito");
	headerList.addElement("*");
	headerList.addElement("Responsable");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	jpList.getTable().addMouseListener(new MouseAdapter() {    
	                      public void mouseClicked(MouseEvent e) {
	                          idRecursodistinguible = dataRow.elementAt(0).toString();
	                          jpList_mouseClicked(e);
	                      }

	                  }
	 );
		
	String params = "-1";
	jpList.setParams("personalfiles.getAllDistinguishableResources", params, headerList);      
    }
    
    public void refresh() {		
	String params = cbiRecurso.getSelectedValue()  +","+cbiMarca.getSelectedValue();	
	jpList.refresh(params);	
    }

    private void tfiBuscarResponsable_keyReleased(KeyEvent e) {	
	if (e.getKeyCode() == KeyEvent.VK_ENTER){	   
	    String params = "'"+tfiBuscarResponsable.getValue().toString()+"'";
	    cbiResponsable.loadJCombo(LibSQL.exFunction("personalfiles.getAllPersonsCombo", params));   		
	}
    }
    
    public void setIdRecurso(int _idRecurso) {		
	cbiRecurso.setSelectedID(_idRecurso);			
    }
    
    public void setIdMarca (int _idMarca) {		
	cbiMarca.setSelectedID(_idMarca);
    }
    
    public void setNombreTipoVidaUtil(String _nombreTipoDesgaste) {    
	tfiTipoDesgaste.setValue(_nombreTipoDesgaste);
    }

    public void setIdTipoVidaUtil (int _idTipoDesgaste) {
	idTipoDesgaste = _idTipoDesgaste;
    }
    
    private void btnAddRecursoDistinguible_actionPerformed(ActionEvent e) {
	if (saveData()) {   
	    clearData();	    
	    distinguishableResources = null;
	    refresh();
	}
    }
  
    public boolean saveData() {
	if (tfiNroIdentificacion.getString().length() > 0) {
	    if (!cbiMarca.getSelectedValue().equals("0"))  {//NO PUEDE ELEJIR MARCAS IGUAL A TODAS
	        if (distinguishableResources == null) {
	            distinguishableResources = new DistinguishableResources();
	        }       
	        distinguishableResources.setIdResource(Integer.parseInt(cbiRecurso.getSelectedValue().toString()));
	        distinguishableResources.setIdBrand(Integer.parseInt(cbiMarca.getSelectedValue().toString()));
	        distinguishableResources.setAquisitionDate(Proced.setFormatDate(tfiFechaAdquisicion.getString(), false));
	        distinguishableResources.setDropOffDate(Proced.setFormatDate(tfiFechaBaja.getString(), false));
	        distinguishableResources.setIdDepot(Integer.parseInt(cbiDeposito.getSelectedValue().toString()));
	        distinguishableResources.setIdPerson(Integer.parseInt(cbiResponsable.getSelectedValue().toString()));
	        distinguishableResources.setWaste(Double.parseDouble(tfiDesgaste.getValue().toString()));
	        distinguishableResources.setIdLifeTimeType(idTipoDesgaste);
	        distinguishableResources.setIdIdentificationType(Integer.parseInt(cbiTipoIdentificacion.getSelectedValue().toString()));
	        distinguishableResources.setIdentificationNumber(tfiNroIdentificacion.getValue().toString());
	        distinguishableResources.setPrice(Double.parseDouble(tfiPrecio.getValue()+""));
	        
	        int result = distinguishableResources.saveData();
	        if (result > 0) {
	           return true;
	        } else {
	            Advisor.messageBox("Ocurrio un error al grabar los datos", "Aviso");
	            return false;
	        }	    
	    }else {
	        Advisor.messageBox("Debe elegir una marca valida", "Aviso");
	        return false;
	    }	    
		 
	} else {
	    Advisor.messageBox("Debe al menos completar tipo y nº de identificación", "Aviso");
	    return false;
	}	
    }

    private void clearData() {
	tfiFechaAdquisicion.setValue("");
	tfiFechaBaja.setValue("");
	//cbiDeposito
	//cbiResponsable
	tfiDesgaste.setValue(0);
	//cbiTipoDesgaste
	//cbiTipoIdentificacion
	tfiNroIdentificacion.setValue("");		
	btnDeleteRecursoDistinguible.setEnabled(false);	
	tfiBuscarResponsable.setValue("");	
	tfiPrecio.setValue(0);	
    }

    private void jpList_mouseClicked(MouseEvent e) {
	if (e.getClickCount() == 2) {   
	    distinguishableResources = new DistinguishableResources();
	    distinguishableResources.setIdDistinguishableResource(Integer.valueOf(dataRow.elementAt(0)+""));
	    loadData();
	    btnDeleteRecursoDistinguible.setEnabled(true);
	}
    }

    private void loadData() {		
	cbiRecurso.setSelectedID(Integer.valueOf(dataRow.elementAt(1)+""));
	cbiMarca.setSelectedID(Integer.valueOf(dataRow.elementAt(3)+""));
	tfiFechaAdquisicion.setValue(dataRow.elementAt(5));
	tfiFechaBaja.setValue(dataRow.elementAt(6));
	cbiDeposito.setSelectedID(Integer.valueOf(dataRow.elementAt(10)+""));
	cbiResponsable.setSelectedID(Integer.valueOf(dataRow.elementAt(12)+""));
	tfiDesgaste.setValue(Double.parseDouble(dataRow.elementAt(14)+""));
	tfiTipoDesgaste.setValue(dataRow.elementAt(15)+"");
	idTipoDesgaste = Integer.valueOf(dataRow.elementAt(16)+"");
	cbiTipoIdentificacion.setSelectedID(Integer.valueOf(dataRow.elementAt(7)+""));
	tfiNroIdentificacion.setValue(dataRow.elementAt(9)+"");              
	btnDeleteRecursoDistinguible.setEnabled(false);    	
	tfiPrecio.setValue(Double.parseDouble(dataRow.elementAt(17).toString()));              
    }

    private void btnDeleteRecursoDistinguible_actionPerformed(ActionEvent e) {		
	if (idRecursodistinguible != null) {	
	    if (Advisor.question("Borrar Patrimonio", "¿Está seguro de borrar el patrimonio seleccionado?")) {        
		if (LibSQL.getInt("personalfiles.delDistinguishableResources", "" + idRecursodistinguible) == 0) {
		    idRecursodistinguible = null;
		    distinguishableResources = null;
		    clearData();
		    refresh();       
		    btnDeleteRecursoDistinguible.setEnabled(false);
		}
	    }
	}
    }

    private void bBuscar_actionPerformed(ActionEvent e) {
	refresh();
	String params = cbiRecurso.getSelectedValue()  +","+cbiMarca.getSelectedValue();        
	ResultSet data = LibSQL.exFunction("personalfiles.getPriceStockDeMarcaPorRecurso", params);  
	try {
	    if (data.next()) {      
		tfiPrecio.setValue(data.getDouble("price"));
		tfiStock.setValue(data.getInt("stock"));		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	
	params = cbiRecurso.getSelectedValue()+"";        
	data = LibSQL.exFunction("personalfiles.getLifeTimeTypeDeRecursoPorMarca", params);  
	try {
	    if (data.next()) {      
		tfiTipoDesgaste.setValue(data.getString("name"));
		idTipoDesgaste = data.getInt("idlifetimetype");      
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    private void btnNewAssign_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void tfiBuscarRecurso_keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER){         
	    String params = "'"+tfiBuscarRecurso.getValue().toString()+"'";
	    cbiRecurso.loadJCombo(LibSQL.exFunction("personalfiles.getAllResourcesCombo", params));  
	}
    }

    private void tfiBuscarMarca_keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER){         
	    String params = "'"+tfiBuscarMarca.getValue().toString()+"'";
	    cbiMarca.loadJCombo(LibSQL.exFunction("personalfiles.getAllBrandsCombo", params));                            	    	   
	}
    }

}
