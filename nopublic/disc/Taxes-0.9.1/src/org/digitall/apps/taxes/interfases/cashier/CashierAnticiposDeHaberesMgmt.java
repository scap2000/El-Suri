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
 * CashierAnticiposDeHaberesMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.AnticipoDeHaberes;
import org.digitall.common.personalfiles.classes.Legajo;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;

public class CashierAnticiposDeHaberesMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpBuscarEmpleado = new BasicPanel();

    private int[] anticiposColumnSize = { 95, 111, 99, 94, 68, 75 };
    private Vector anticiposDataRow = new Vector();
    private GridPanel anticiposListPanel = new GridPanel(1000, anticiposColumnSize, "Anticipos de Haberes", anticiposDataRow){
        public void finishLoading() {
            habilitarBotonBorrar();   
        }
    };

    private TFInput tfBuscarEmpleado = new TFInput(DataTypes.STRING,"Buscar Empleado",false);
    private TFInput tfDNI = new TFInput(DataTypes.STRING,"DNI/CUIL",true);
    private TFInput tfLegajo = new TFInput(DataTypes.STRING,"Legajo",true);
    private TFInput tfFechaEmision = new TFInput(DataTypes.SIMPLEDATE,"Fecha de Emisión",true);
    private TFInput tfFechaDescuento = new TFInput(DataTypes.SIMPLEDATE,"Fecha de Dto.",true);
    private TFInput tfMonto = new TFInput(DataTypes.MONEY,"Monto del Anticipo",true);

    private CBInput cbEmpleado = new CBInput(0,"Empleado",false);
    
    private PrintSaveButton btnRegistrarAnticipoDeHaber = new PrintSaveButton();
    private PrintButton btnImprimirAnticipoDeHaberes = new PrintButton();
    
    private ImageLabel lblFoto = new ImageLabel();
    
    private Legajo legajo;
    private CashierMain parentMain;
    private AnticipoDeHaberes anticipoDeHaberes;

    public CashierAnticiposDeHaberesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(614, 535));
	this.setPreferredSize(new Dimension(614, 415));
	this.setTitle("Anticipo de Haberes");
	content.setLayout(null);
	content.setSize(new Dimension(614, 485));
        jpBuscarEmpleado.setLayout(null);
        jpBuscarEmpleado.add(tfBuscarEmpleado, null);
        jpBuscarEmpleado.add(cbEmpleado, null);
        jpBuscarEmpleado.add(tfLegajo, null);
        jpBuscarEmpleado.add(tfDNI, null);
        jpBuscarEmpleado.add(tfFechaEmision, null);
        jpBuscarEmpleado.add(tfFechaDescuento, null);
        jpBuscarEmpleado.add(tfMonto, null);
        jpBuscarEmpleado.add(lblFoto, null);
        anticiposListPanel.setBounds(new Rectangle(5, 195, 605, 285));
	lblFoto.setBounds(new Rectangle(435, 20, 155, 155));
	lblFoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
	lblFoto.setSize(new Dimension(155, 155));
        tfMonto.setBounds(new Rectangle(285, 30, 120, 35));
        tfFechaEmision.setBounds(new Rectangle(10, 130, 110, 35));
	tfFechaEmision.setPreferredSize(new Dimension(110, 35));
	tfFechaDescuento.setBounds(new Rectangle(140, 130, 110, 35));
	tfFechaDescuento.setPreferredSize(new Dimension(110, 35));
	tfMonto.setBounds(new Rectangle(285, 130, 135, 35));
	tfMonto.setFont(new Font("Dialog", 1, 12));
        cbEmpleado.setBounds(new Rectangle(145, 30, 280, 35));
        tfBuscarEmpleado.setBounds(new Rectangle(10, 25, 110, 35));
	tfBuscarEmpleado.setFont(new Font("Dialog", 0, 11));
	tfBuscarEmpleado.setPreferredSize(new Dimension(104, 35));
	tfDNI.setBounds(new Rectangle(285, 75, 135, 35));
	tfLegajo.setBounds(new Rectangle(140, 75, 70, 35));
	cbEmpleado.setBounds(new Rectangle(140, 25, 280, 35));
	jpBuscarEmpleado.setBounds(new Rectangle(5, 0, 605, 190));
	jpBuscarEmpleado.setBorder(BorderPanel.getBorderPanel("Anticipo de Haberes"));
        btnRegistrarAnticipoDeHaber.setToolTipText("Registrar Anticipo de Haberes");
	btnRegistrarAnticipoDeHaber.setBounds(new Rectangle(455, 95, 40, 25));
	btnRegistrarAnticipoDeHaber.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnRegistrarAnticipoDeHaber_actionPerformed(e);
                }

            }
        );
        btnImprimirAnticipoDeHaberes.setToolTipText("Imprimir Anticipo de Haberes seleccionado");
        btnImprimirAnticipoDeHaberes.setBounds(new Rectangle(455, 95, 40, 25));
        btnImprimirAnticipoDeHaberes.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnImprimirAnticipoDeHaberes_actionPerformed(e);
                }

            }
        );
        
        
	tfDNI.setPreferredSize(new Dimension(37, 35));
        tfDNI.setEditable(false);
        tfLegajo.setEditable(false);
        addButton(btnImprimirAnticipoDeHaberes);
        addButton(btnRegistrarAnticipoDeHaber);
        tfBuscarEmpleado.getTextField().addKeyListener(new KeyAdapter() {

                                           public void keyTyped(KeyEvent e) {
                                               char caracter = e.getKeyChar();
                                               if ((caracter == KeyEvent.VK_ENTER)) {
                                                   loadCombo();
                                               }
                                           }

                                       }
        );
	cbEmpleado.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbEmpleado.getSelectedIndex() > -1) {
						    cargarDatosEmpleado();
						}
					    }
					}

				    }
	);
        tfMonto.getTextField().addKeyListener(new KeyAdapter() {

                                           public void keyTyped(KeyEvent e) {
                                               char caracter = e.getKeyChar();
                                               if ((caracter == KeyEvent.VK_ENTER)) {
                                                   grabarAnticipoDeHaberes();
                                               }
                                           }

                                       }
        );
        tfLegajo.getTextField().setHorizontalAlignment(tfLegajo.getTextField().CENTER);
        tfDNI.getTextField().setHorizontalAlignment(tfDNI.getTextField().CENTER);
        content.add(jpBuscarEmpleado, null);
        content.add(anticiposListPanel, null);
        this.setContent(content);
	anticiposListPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     habilitarBotonBorrar();
                                                     cargarObjeto();
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							  
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     }
						 }

					     } 
	);
	btnImprimirAnticipoDeHaberes.setEnabled(false);
        setAnticiposHeader();
        controlBotones();
    }

    public void setParentMain(CashierMain parentMain) {
	this.parentMain = parentMain;
    }

    private void setAnticiposHeader() {
        Vector anticiposHeader = new Vector();
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("Número");
        anticiposHeader.addElement("*");
        anticiposHeader.addElement("Fecha Emisión"); 
        anticiposHeader.addElement("Fecha Pago"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("Fecha Dto."); 
        anticiposHeader.addElement("Estado"); 
        anticiposHeader.addElement("($) Monto"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        anticiposHeader.addElement("*"); 
        
        anticiposListPanel.setParams("sueldos.getallAnticiposDeHaberesPorLegajo", "-1", anticiposHeader);
    }

    private void cargarObjeto() {
        anticipoDeHaberes = new AnticipoDeHaberes(Integer.parseInt(anticiposDataRow.get(0).toString()));
    }
    

    private void refreshAnticipos() {
        anticiposListPanel.refresh(legajo.getidLegajo());
    }

    private void controlBotones() {
        if (cbEmpleado.getSelectedIndex() == -1) {
            btnRegistrarAnticipoDeHaber.setEnabled(false);
        } else {
            btnRegistrarAnticipoDeHaber.setEnabled(true);
        }
    }
    
    private void loadCombo(){
        cbEmpleado.loadJCombo(LibSQL.exFunction("personalfiles.getAllPersonalByFilter", tfBuscarEmpleado.getStringForSQL()));
	cargarDatosEmpleado();
        controlBotones();
    }
    
    private void cargarDatosEmpleado() {
	/** Método para cargar los datos del empleado seleccionado */
	legajo = new Legajo();
        legajo.retriveDataForNumber(Integer.parseInt(cbEmpleado.getSelectedValueRef().toString()));
	tfDNI.setValue(legajo.getPerson().getIdentificationNumber());
	tfLegajo.setValue(legajo.getNumero());
	 if (legajo.getPerson().getPhotoImage() != null) {
	     LibIMG.escalaImg(legajo.getPerson().getPhotoImage(), lblFoto);
	     lblFoto.setImage(legajo.getPerson().getPhotoImage());   
	 } else {
	     lblFoto.setImage(null);
	     lblFoto.setText("No posee foto.");
	 } 
         refreshAnticipos();
    }
    
    private void btnRegistrarAnticipoDeHaber_actionPerformed(ActionEvent e) {
        grabarAnticipoDeHaberes();
    }

    private void habilitarBotonBorrar(){
        if (anticiposDataRow.size() > 0) {
            btnImprimirAnticipoDeHaberes.setEnabled(true);
        } else {
            btnImprimirAnticipoDeHaberes.setEnabled(false);
        }
    }

    private void btnImprimirAnticipoDeHaberes_actionPerformed(ActionEvent e) {
        anticipoDeHaberes = new AnticipoDeHaberes(Integer.parseInt(anticiposDataRow.get(0).toString()));
        imprimirAnticipoDeHaberes(anticipoDeHaberes.getIdAnticipoDeHaberes());
    }
    
    private void grabarAnticipoDeHaberes(){
      if (control()) {
            if (Advisor.question("Confirmar", "¿Está seguro de registrar un Anticipo de Haberes \n por la suma de "+ Format.money(tfMonto.getAmount()) +" "+
                                              "para\n "+ legajo.getPerson().getLastName() +", "+ legajo.getPerson().getFirstName() + "?")) {
                cargarAnticipoDeHaberes();
                if (anticipoDeHaberes.saveData() != -1) {
                    Advisor.messageBox("Se registró exitosamente el Anticipo de Haberes","Aviso");
                    imprimirAnticipoDeHaberes(anticipoDeHaberes.getIdAnticipoDeHaberes());
                    refreshAnticipos();
                    tfMonto.setValue(0.00);
                } else {
                    Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
                }
            }
        }
        //imprimirAnticipoDeHaberes(58);
    }
    
    private boolean control() {
        boolean _returns = false;
        if (cbEmpleado.getSelectedIndex() == -1) {
            Advisor.messageBox("Debe seleccionar un Empleado","Error");
        } else if (tfFechaEmision.getDate().equals("")) {
            Advisor.messageBox("La fecha de Emisión no puede ser vacía","Error");
        } else if (Proced.compareDates(Proced.setFormatDate(tfFechaEmision.getDate(), false), Environment.currentDate) == 2) {
            Advisor.messageBox("La fecha de Emisión no debe ser mayor a la fecha Actual","Error");
        } else if (tfFechaDescuento.getDate().equals("")) {
            Advisor.messageBox("La fecha de Descuento no puede ser vacía","Error");
        } else if (Proced.compareDates(Proced.setFormatDate(tfFechaEmision.getDate(), false), Proced.setFormatDate(tfFechaDescuento.getDate(), false)) == 2) {
            Advisor.messageBox("La fecha de Emisión no debe ser mayor a la fecha Descuento","Error");
        } else if (tfMonto.getAmount() <= 0) {
            Advisor.messageBox("El Monto del Anticipo debe ser mayor que $ 0.00 (cero) ","Error");
        } else if (!LibSQL.getBoolean("sueldos.esMontoCorrectoParaAnticipo", ""+ tfMonto.getAmount())) {
            Advisor.messageBox("Monto no permitido","Error"); 
        } else {
            _returns = true;
        }
        return _returns;
    }
    
    private void cargarAnticipoDeHaberes() {
        anticipoDeHaberes = new AnticipoDeHaberes();
        anticipoDeHaberes.setIdPersona(legajo.getidPerson());
        anticipoDeHaberes.setIdLegajo(legajo.getidLegajo());
        anticipoDeHaberes.setLegajo(legajo.getNumero()); 
        anticipoDeHaberes.setApellido(legajo.getPerson().getLastName());
        anticipoDeHaberes.setNombres(legajo.getPerson().getFirstName());
        anticipoDeHaberes.setFechaEmision(Proced.setFormatDate(tfFechaEmision.getDate(),false));
        anticipoDeHaberes.setFechaDescuento(Proced.setFormatDate(tfFechaDescuento.getDate(),false));
        anticipoDeHaberes.setMonto(tfMonto.getAmount());
    }
    
    private void imprimirAnticipoDeHaberes(int _idAnticipoDeHaberes) {
        AnticipoDeHaberes _anticipoDeHaberes = new AnticipoDeHaberes(_idAnticipoDeHaberes);
        _anticipoDeHaberes.retrieveData();
        BasicReport report = new BasicReport(CashierButtons.class.getResource("xml/AnticipoDeHaberes.xml"));
        report.addTableModel("sueldos.xmlGetAnticipoDeHaberes", _anticipoDeHaberes.getIdAnticipoDeHaberes());
        report.setProperty("anio", ""+ _anticipoDeHaberes.getAnio());
        report.setProperty("numero", _anticipoDeHaberes.getNumero());
        report.setProperty("legajo", legajo.getNumero());
        String fechaTexto = "" + Format.dateToText(_anticipoDeHaberes.getFechaDescuento()).toLowerCase();
        report.setProperty("fechadelanota", ""+ OrganizationInfo.getLocation() +", "+ Format.dateToText(_anticipoDeHaberes.getFechaEmision()).toLowerCase());
        String textonota = "El/la que suscribe, Sr./Sra. "+ legajo.getPerson().getLastName() +" "+ legajo.getPerson().getFirstName() + ", " +
                           "legajo Nº "+ legajo.getNumero() + ", se dirige al Sr. Intendente de "+ OrganizationInfo.getLocation() +" "+
                           "con el objeto de solicitarle tenga a bien concederle un ANTICIPO DE HABERES por la suma de "+ Format.money(_anticipoDeHaberes.getMonto()) +" " +
                           "(pesos "+ Format.NumberToText.numberToText(_anticipoDeHaberes.getMonto()) +") para ser descontado de los haberes "+
                           "correspondientes al primer pago que se efectúe a partir del mes de "+ fechaTexto.substring(fechaTexto.indexOf("de") + 2, fechaTexto.length()) +"."+
                           "\n\nQuedando a la espera de una respuesta favorable, saluda a Ud. muy atte.";

        report.setProperty("textonota", textonota);
        String textoautorizacion = "El que suscribe, Intendente Municipal, autoriza el ANTICIPO DE HABERES "+
                                   "para el/la empleado/a "+ legajo.getPerson().getLastName() +" "+ legajo.getPerson().getFirstName() + ", "+
                                   "por la suma de " + Format.money(_anticipoDeHaberes.getMonto()) + " (pesos "+ Format.NumberToText.numberToText(_anticipoDeHaberes.getMonto()) + ") " +
                                   "para ser descontado de los haberes correspondientes al primer pago que se efectúe a partir del mes de " +
                                    fechaTexto.substring(fechaTexto.indexOf("de") + 2, fechaTexto.length()) +".";
        report.setProperty("textoautorizacion", textoautorizacion);
        BarCode code = new BarCode();
        report.setProperty("barcode", code.getBarCodeEAN13(_anticipoDeHaberes.getBarcode()));
        report.doReport();
    }

}
