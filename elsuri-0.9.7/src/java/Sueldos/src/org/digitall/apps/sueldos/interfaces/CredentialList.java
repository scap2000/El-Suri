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
 * CredentialList.java
 *
 * */
package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JComboBox;

import org.digitall.apps.sueldos.classes.Credencial;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class CredentialList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfPersonName = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfNumeroLegajo = new TFInput(DataTypes.INTEGER, "Nº Legajo",false);

    private int[] sizeColumnList = {75, 190, 75, 85, 80};
    private Vector credentialHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel credentialPanel = new GridPanel(50000, sizeColumnList, "Listado de Credenciales", dataRow) {
	public void calculate() {
	    controlBotones();
	}
    };
    
    private FindButton btnFind = new FindButton();
    private PrintButton btnPrint = new PrintButton();
    private AcceptButton btnAccept = new AcceptButton();
    private DeleteButton btnDelete = new DeleteButton();
    
    private LegajosSearchPanel legajosSearchPanel = new LegajosSearchPanel();
    private Credencial credencial;

    private CBInput cbDependencias = new CBInput(0,"Dependencia",false);
    private String itemAll = "TODAS";

    public CredentialList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(576, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfPersonName.setBounds(new Rectangle(10, 20, 100, 35));
	credentialPanel.setBounds(new Rectangle(5, 75, 565, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 565, 65));
	searchPanel.setLayout(null);
        searchPanel.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Buscar")));
	btnFind.setBounds(new Rectangle(530, 30, 30, 25));
	btnPrint.setToolTipText("Imprimir credencial de la persona seleccionada");
	btnAccept.setToolTipText("Generar todas las credenciales");
	btnDelete.setToolTipText("Borrar todas las credenciales");
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	
	btnAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAccept_actionPerformed(e);
				}

			    }
	);
	btnDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnDelete_actionPerformed(e);
				}

			    }
	);
	cbDependencias.setBounds(new Rectangle(120, 20, 320, 35));
	tfNumeroLegajo.setBounds(new Rectangle(450, 20, 70, 35));
        searchPanel.add(cbDependencias, null);
        searchPanel.add(tfNumeroLegajo, null);
        searchPanel.add(tfPersonName, null);
        searchPanel.add(btnFind, null);
        contentPanel.add(credentialPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
	credentialPanel.getTable().setDragEnabled(true);
	credentialPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfPersonName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfNumeroLegajo.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	setCredentialHeader();
	loadCombo();
	cbDependencias.setSelectedValue(itemAll);
	cbDependencias.autoSize();
	addButton(btnPrint);
	addButton(btnAccept);
	addButton(btnDelete);
        this.getParentInternalFrame().setIcon(true);
    }
    
    private void loadCombo(){
	cbDependencias.loadJCombo(LibSQL.exFunction("personalfiles.getAllDependency",""));
	cbDependencias.addItem(itemAll);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	int iddependencia = -1;
	if(!cbDependencias.getSelectedItem().equals(itemAll)){
	    iddependencia = Integer.parseInt(cbDependencias.getSelectedValue().toString());
	}
	String params = "'"+ tfPersonName.getString().trim() +"',"+ tfNumeroLegajo.getString().trim()+","+iddependencia;
	credentialPanel.refresh(params);
    }

    private void setCredentialHeader() {
	credentialHeader.removeAllElements();
	credentialHeader.addElement("*");         //idCredencial
	credentialHeader.addElement("*");         //idTipoCredencial
	credentialHeader.addElement("*");         //idPerson
	credentialHeader.addElement("*");         //credenciales.fechaini,
	credentialHeader.addElement("*");         //credenciales.fechaFin,
	credentialHeader.addElement("*");         //credenciales.barcode,
	credentialHeader.addElement("Apellido");         //credenciales.apellido,
	credentialHeader.addElement("Nombres");         //credenciales.nombres,
	credentialHeader.addElement("D.N.I./C.U.I.L.");         //credenciales.dni,
	credentialHeader.addElement("Cargo");            //credenciales.cargo,
	credentialHeader.addElement("Nº Legajo"); //credenciales.nrolegajo,
	credentialHeader.addElement("*");            //credenciales.leyenda1,
	credentialHeader.addElement("*");            //credenciales.estado,
	credentialHeader.addElement("*");            //credenciales.dependencia

	credentialPanel.getTable().addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			
			}
		    }
		});
		
	String params = "'',0,0" ;
		credentialPanel.setParams("credenciales.getAllCredenciales", params, credentialHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    credencial = new Credencial();
	    credencial.setIdCredencial(Integer.parseInt("" + dataRow.elementAt(0)));
	    credencial.retrieveData();
	    //btnPrint.setEnabled(true);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    
    private void btnPrint_actionPerformed(ActionEvent e) {
	//Imprimmir Credencial
	 if(credentialPanel.getSelectedsID().size() > 0){
	     BasicReport report = new BasicReport(CredentialList.class.getResource("xml/Credencial.xml"));
	     String params = "'" + vectorToParams(credentialPanel.getSelectedsID()) + "'";
	     report.addTableModel("credenciales.xmlGetCredencial", ""+ params);
             report.doReport();    
	 }else{
	         Advisor.messageBox("Debe seleccionar al menos un empleado\npara generar credenciales. ","Aviso");
         }
    }
    
    private String vectorToParams(Vector _vector) {
	String resultado = "";
	int tam = _vector.size();
	for (int i = 0 ; i < tam; i++) {
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	resultado = resultado.substring(0,resultado.length() - 1);
	return(resultado);
    }
    
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	//Generar Credenciales
        generarCredenciales();
	/*if ( generarCredenciales()) {
	    btnAccept.setEnabled(false);
	}*/
    }
    
    private void btnDelete_actionPerformed(ActionEvent e) {
	//Borrar todas la Credenciales
	if (Advisor.question("Borrar Credenciales","¿Está seguro que desea borrar todas las Credenciales Municipales?")) {
	    if (LibSQL.getBoolean("credenciales.removeAllCredencialesMunicipales","")) {
		Advisor.messageBox("Se borraron todas las credenciales municipales con éxito.","Exito");
		credentialPanel.refresh("'',0,0");
                btnDelete.setEnabled(false);
                btnAccept.setEnabled(true);
	    } else {
	        Advisor.messageBox("Se produjo un error al grabar los datos.","Error");
	    }
	}
    }
    
    private void generarCredenciales() {
        
        if (Advisor.question("Generar Credenciales","¿Está seguro que desea generar todas las Credenciales Municipales?")) {
            Thread threadTask = new Thread(new Runnable() {
            
                                        public void run() {
                                            boolean resultado = true;
                                            Environment.jpStatusBar.setIndeterminate(true);
                                            Environment.jpStatusBar.setAction("Retrieving data...");
                                            legajosSearchPanel.inicializar();
                                            int primerLegajo = legajosSearchPanel.getStartLegajos();
                                            int ultimoLegajo = legajosSearchPanel.getEndLegajos();
                                            Credencial cred = new Credencial();
                                            int i = primerLegajo;
                                            String fechaInicio = Environment.currentDate;
                                            String fechaFin = LibSQL.getDate("public.sumaranios", "'" + fechaInicio + "'," + 1).toString();
                                            while( i <= ultimoLegajo && resultado) {
                                                cred = new Credencial();
                                                cred.setIdTipoCredencial(1);
                                                cred.setIdPerson(legajosSearchPanel.getLegajo().getPerson().getIdPerson());
                                                cred.setFechaIni(fechaInicio);
                                                cred.setFechaFin(fechaFin);
                                                cred.setApellido(legajosSearchPanel.getLegajo().getPerson().getLastName());
                                                cred.setNombres(legajosSearchPanel.getLegajo().getPerson().getFirstName());
                                                cred.setDni(legajosSearchPanel.getLegajo().getPerson().getIdentificationNumber());
                                                cred.setCargo(legajosSearchPanel.getLegajo().getCargo());
                                                cred.setNrolegajo(legajosSearchPanel.getLegajo().getNumero());
                                                cred.setDependencia(legajosSearchPanel.getLegajo().getDependenciaName());
                                                if (cred.saveData() != -1) {
                                                    BarCode code = new BarCode();
                                                    cred.setPhotoBarCode(code.getBarCodeEAN13(cred.getBarcode()));
                                                    cred.saveCodePicture();
                                                    resultado = true;
                                                    legajosSearchPanel.pressNext();
                                                    i = legajosSearchPanel.getLegajo().getNumero();
                                                    if(i == ultimoLegajo) {
                                                        i ++;
                                                    }
                                                } else {
                                                    resultado = false;
                                                }
                                            }
                                            if (resultado) {
                                                Advisor.messageBox("Se generaron todas las credenciales municipales con éxito.","Exito");
                                                btnAccept.setEnabled(false);
                                                btnDelete.setEnabled(true);
                                                credentialPanel.refresh("'',0,0");
                                            } else {
                                                Advisor.messageBox("Se produjo un error al generar las credenciales municipales.","Error");
                                            }
                                            Environment.jpStatusBar.setAction("Data retrieved succesfuly");
                                            Environment.jpStatusBar.setIndeterminate(false);
                                        }
            });
            threadTask.start();
        } 
    }
    
    private void controlBotones() {
	//btnPrint.setEnabled(false);
    }
}
