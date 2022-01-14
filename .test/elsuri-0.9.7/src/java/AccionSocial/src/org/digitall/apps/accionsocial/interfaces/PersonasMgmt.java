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
 * PersonasMgmt.java
 *
 * */
package org.digitall.apps.accionsocial.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.apps.accionsocial.classes.Persona;
import org.digitall.apps.accionsocial.classes.errclasses.ErrorFormPersonasMgmt;
import org.digitall.common.components.NeighborhoodList;
import org.digitall.common.components.StreetList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Neighborhood;
import org.digitall.common.resourcescontrol.classes.Street;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PersonasMgmt extends BasicPrimitivePanel {

    private JTabbedPane tabbedPane = new JTabbedPane();

    private BasicPanel jpDatos = new BasicPanel();
    private BasicPanel jpDireccion = new BasicPanel();
    
    private TFInput tfNumeroDocumento = new TFInput("Número", true,DataTypes.FORMATO_DNI);
    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Nombres", true);
    private TFInput tfFechaNacimiento = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Nacimiento", true);
    private TFInput tfCuil = new TFInput("CUIL", true,DataTypes.FORMATO_CUIT_CUIL);
    private TFInput tfDomicilio = new TFInput(DataTypes.STRING, "Domicilio", true);
    private TFInput tfApellido = new TFInput(DataTypes.STRING, "LastName", true);
    private TFInput tfCoordenadas = new TFInput(DataTypes.GEOMETRY,"COORDENADAS",false);
    private TFInput tfCodigoPostal = new TFInput(DataTypes.STRING, "PostalCode", false);
    private TFInput tfDpto = new TFInput(DataTypes.STRING, "Appt", false);
    private TFInput tfPiso = new TFInput(DataTypes.INTEGER, "Floor", false);
    private TFInput tfSectorBloque = new TFInput(DataTypes.STRING, "Block", false);
    private TFInput tfNumeracion = new TFInput(DataTypes.INTEGER, "Number", false);
    private TFInput tfManzana = new TFInput(DataTypes.STRING, "Mza.", false);
    private TFInput tfCasa = new TFInput(DataTypes.STRING, "Casa", false);
    private TFInput tfMedidor = new TFInput(DataTypes.STRING, "Medidor", false);
    
    private CBInput cbTipoDocumento = new CBInput(CachedCombo.IDENTIFICATION_TABS, "Identification");
    private CBInput cbSexo = new CBInput(0, "Sex");
    private CBInput cbPaises = new CBInput(CachedCombo.COUNTRY_TABS, "Country");
    private CBInput cbProvincias = new CBInput(CachedCombo.PROVINCE_TABS, "ProvinceState");
    private CBInput cbLocalidades = new CBInput(CachedCombo.LOCATION_TABS, "Location");
    private CBInput cbBarrios = new CBInput(CachedCombo.NEIGHBORHOOD_TABS, "Neighborhood");
    private CBInput cbCalles = new CBInput(CachedCombo.STREET_TABS, "Street");
    private CBInput cbVive = new CBInput(0, "Vive");
    
    private BasicCheckBox chkTodasProvincias = new BasicCheckBox();
    private BasicCheckBox chkTodasLocalidades = new BasicCheckBox();
    
    private ItemListener countryItemListener;
    private ItemListener provinceItemListener;
    private ItemListener locationItemListener;
    private ItemListener neighborhoodItemListener;
    
    private BasicCheckBox chkAllNeighborhood = new BasicCheckBox();
    private BasicCheckBox chkAllStreet = new BasicCheckBox();
    
    private boolean hasItemListener = false;
    
    private AddComboButton btnAgregarBarrio = new AddComboButton();
    private AddComboButton btnAgregarCalle = new AddComboButton();
    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private JButton btnRecuperarPunto = new JButton();
    
    private Point2D.Double point = null;
    private Persona persona;
    private ErrorFormPersonasMgmt errorFormPersonasMgmt;
    private Coordinador coordinador;

    public PersonasMgmt(Coordinador _coordinador) {
	try {
	    coordinador = _coordinador;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(559, 288));
	this.setPreferredSize(new Dimension(565, 330));
	jpDatos.setLayout(null);
	btnSaveData.setBounds(new Rectangle(465, 330, 40, 25));
	btnSaveData.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSaveData_actionPerformed(e);
				   }

			       }
	);
	tfNumeroDocumento.setBounds(new Rectangle(130, 70, 95, 35));
	tfNombre.setBounds(new Rectangle(210, 20, 260, 35));
	tfFechaNacimiento.setBounds(new Rectangle(240, 70, 130, 35));
	tfCuil.setBounds(new Rectangle(10, 115, 105, 35));

	tfApellido.setBounds(new Rectangle(10, 20, 185, 35));
	btnClose.setBounds(new Rectangle(515, 330, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        
        btnRecuperarPunto.setText("<html><p align=center>Obtener coordenada <br><p align=center> del punto del GIS</html>");
        btnRecuperarPunto.setToolTipText("Obtener último Punto(x,y) grabado en el GIS");
        btnRecuperarPunto.setSize(new Dimension(145, 45));
        btnRecuperarPunto.setPreferredSize(new Dimension(30, 20));
        btnRecuperarPunto.setOpaque(true);
        btnRecuperarPunto.setFont(new Font("Dialog", 1, 10));
        btnRecuperarPunto.setForeground(Color.white);
        btnRecuperarPunto.setBackground(new Color(165, 41, 0));
        btnRecuperarPunto.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        btnRecuperarPunto.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnRecuperarPunto_actionPerformed(e);
                                }

                            }
        );
        
	cbSexo.setBounds(new Rectangle(485, 20, 55, 35));
        tfDomicilio.setBounds(new Rectangle(130, 115, 410, 35));
	tabbedPane.setBounds(new Rectangle(7, 8, 550, 315));
        tfCoordenadas.setBounds(new Rectangle(10, 160, 225, 35));
        cbTipoDocumento.setBounds(new Rectangle(10, 70, 105, 35));
        jpDatos.add(cbVive, null);
        jpDatos.add(btnRecuperarPunto, null);
        jpDatos.add(tfCoordenadas, null);
        jpDatos.add(cbSexo, null);
        jpDatos.add(tfDomicilio, null);
        jpDatos.add(tfApellido, null);
        jpDatos.add(tfFechaNacimiento, null);
        jpDatos.add(tfNombre, null);
        jpDatos.add(cbTipoDocumento, null);
        jpDatos.add(tfNumeroDocumento, null);
        jpDatos.add(tfCuil, null);
        tabbedPane.addTab("Datos Personales", jpDatos);
	this.add(tabbedPane, null);
	this.addButton(btnClose);
	this.addButton(btnSaveData);
	cbSexo.getCombo().addItem("M", 0);
	cbSexo.getCombo().addItem("F", 1);
        cbSexo.getCombo().addItem(" ", 2);
        cbVive.getCombo().addItem("SI", 0);
        cbVive.getCombo().addItem("NO", 1);
	cbSexo.getCombo().setSelectedIndex(0);
	btnClose.setToolTipText("Cancelar");
        cbTipoDocumento.setSelectedID(1);
        cbTipoDocumento.setEnabled(false);
        tfCoordenadas.setEditable(false);
        btnRecuperarPunto.setBounds(new Rectangle(255, 165, 125, 40));
        cbTipoDocumento.addItemListener(new ItemListener() {
              public void itemStateChanged(ItemEvent evt) {
                  if (evt.getStateChange() == ItemEvent.SELECTED) {
                      
                  }
              }
        });
        
        cbPaises.setBounds(new Rectangle(15, 20, 185, 35));
        cbProvincias.setBounds(new Rectangle(210, 20, 300, 35));
        cbBarrios.setBounds(new Rectangle(15, 100, 495, 35));
        cbLocalidades.setBounds(new Rectangle(15, 60, 495, 35));
        cbCalles.setBounds(new Rectangle(15, 140, 385, 35));
        tfCodigoPostal.setBounds(new Rectangle(400, 180, 110, 35));
        tfDpto.setBounds(new Rectangle(135, 180, 40, 35));
        tfDpto.setSize(new Dimension(40, 35));
        tfPiso.setBounds(new Rectangle(270, 220, 135, 20));
        tfPiso.setBounds(new Rectangle(80, 180, 35, 35));
        tfSectorBloque.setBounds(new Rectangle(75, 215, 155, 20));
        tfSectorBloque.setBounds(new Rectangle(15, 180, 45, 35));
        
        btnAgregarCalle.setBounds(new Rectangle(405, 155, 25, 25));
        btnAgregarCalle.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e){
                                    btnAgregarCalle_actionPerformed(e);
                                }
        });
        
        tfNumeracion.setBounds(new Rectangle(440, 140, 70, 35));

        chkTodasProvincias.setBounds(new Rectangle(525, 35, 15, 15));
        chkTodasProvincias.setSize(new Dimension(15, 15));
        chkTodasProvincias.setHorizontalAlignment(SwingConstants.CENTER);
        chkTodasProvincias.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          chkTodasProvincias_actionPerformed(e);
                                      }

                                  }
        );
        chkTodasLocalidades.setBounds(new Rectangle(525, 75, 15, 15));
        chkTodasLocalidades.setHorizontalAlignment(SwingConstants.CENTER);
        chkTodasLocalidades.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          chkTodasLocalidades_actionPerformed(e);
                                      }

                                  }
        );
        jpDireccion.setLayout(null);
        jpDireccion.add(tfMedidor, null);
        jpDireccion.add(tfCasa, null);
        jpDireccion.add(tfManzana, null);
        jpDireccion.add(btnAgregarCalle, null);
        jpDireccion.add(btnAgregarBarrio, null);
        jpDireccion.add(chkTodasLocalidades, null);
        jpDireccion.add(chkTodasProvincias, null);
        jpDireccion.add(cbPaises, null);
        jpDireccion.add(cbProvincias, null);
        jpDireccion.add(cbBarrios, null);
        jpDireccion.add(cbLocalidades, null);
        jpDireccion.add(cbCalles, null);
        jpDireccion.add(tfCodigoPostal, null);
        jpDireccion.add(tfSectorBloque, null);
        jpDireccion.add(tfNumeracion, null);
        jpDireccion.add(tfDpto, null);
        jpDireccion.add(tfPiso, null);
        tfCodigoPostal.setEditable(true);
        chkTodasProvincias.setToolTipText("Mostrar sólo Provincias/Estados del País seleccionado");
        chkTodasLocalidades.setToolTipText("Mostrar sólo Localidades de la Provincia/Estado seleccionada/o");
        btnAgregarBarrio.setBounds(new Rectangle(520, 110, 25, 25));
        btnAgregarBarrio.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e){
                                    btnAgregarBarrio_actionPerformed(e);
                                }
        }
        );
        tfManzana.setBounds(new Rectangle(195, 180, 40, 35));
        tfCasa.setBounds(new Rectangle(255, 180, 45, 35));
        tfMedidor.setBounds(new Rectangle(315, 180, 60, 35));
        cbVive.setBounds(new Rectangle(385, 70, 80, 35));
        tabbedPane.addTab("Dirección", jpDireccion);
        cbPaises.setSelectedValue("Argentina");
        cbProvincias.setSelectedValue("SALTA (Argentina)");
        tfDomicilio.setVisible(true);
        jpDatos.setBorder(BorderPanel.getBorderPanel("Completar datos"));
        jpDireccion.setBorder(BorderPanel.getBorderPanel("Completar dirección"));
    }
    

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }
    
    private void loadData() {
            tfApellido.setValue(persona.getApellidos());
	    tfNombre.setValue(persona.getNombres());
	    cbTipoDocumento.setSelectedID("" + persona.getTipoDocumento());
	    tfNumeroDocumento.setValue("" + persona.getNroDocumento());
	    tfDomicilio.setValue(persona.getDomicilio());
            tfFechaNacimiento.setValue(Proced.setFormatDate(persona.getFechaNacimiento(), true));
	    tfCuil.setValue(persona.getCuil());
            if (persona.getSexo().equals("")) {
                cbSexo.setSelectedValue(" ");
            } else {
                cbSexo.setSelectedValue(persona.getSexo());
            }
            if (persona.isVive()) {
                cbVive.setSelectedValue("SI");
            } else {
                cbVive.setSelectedValue("NO");
            }
            point = new Point2D.Double(persona.getX(),persona.getY());
            tfCoordenadas.setValue(point);
            cbPaises.setSelectedID(persona.getIdNacionalidad());
            cbProvincias.setSelectedID(persona.getIdProvincia());
            cbLocalidades.setSelectedID(persona.getIdLocalidad());
            cbBarrios.setSelectedID(persona.getIdBarrio());
            cbCalles.setSelectedID(persona.getIdCalle());
            tfNumeracion.setValue(persona.getNumeracion());
            tfCasa.setValue(persona.getCasa());
            tfManzana.setValue(persona.getManzana());
            tfMedidor.setValue(persona.getMedidor());
            tfSectorBloque.setValue(persona.getSectorBloque());
            tfPiso.setValue(persona.getPiso());
            tfDpto.setValue(persona.getDepartamento());
            tfCodigoPostal.setValue(persona.getCodigoPostal());
    }

    public boolean saveData() {
        if (control()) {
            if (persona == null) {
                persona = new Persona();
            }
            persona.setApellidos(tfApellido.getString());
            persona.setNombres(tfNombre.getString());
            persona.setSexo(cbSexo.getSelectedItem().toString());
            persona.setTipoDocumento(cbSexo.getSelectedValue().toString());
            persona.setTipoDocumento(cbTipoDocumento.getSelectedValue().toString());
            persona.setNroDocumento(tfNumeroDocumento.getString());
            persona.setFechaNacimiento(Proced.setFormatDate(tfFechaNacimiento.getDate(),false));
            persona.setCuil(tfCuil.getString());
            persona.setDomicilio(tfDomicilio.getValue().toString());
            point = (Point2D.Double)tfCoordenadas.getValueObject();
            persona.setX(point.getX());
            persona.setY(point.getY());
            //datos de dirección
            persona.setIdNacionalidad(Integer.parseInt(cbPaises.getSelectedValue().toString()));
            persona.setIdProvincia(Integer.parseInt(cbProvincias.getSelectedValue().toString()));
            persona.setIdLocalidad(Integer.parseInt(cbLocalidades.getSelectedValue().toString()));
            persona.setNumeracion(tfNumeracion.getString());
            persona.setPiso(tfPiso.getString());
            persona.setDepartamento(tfDpto.getString());
            persona.setSectorBloque(tfSectorBloque.getString());
            persona.setIdBarrio(Integer.parseInt(cbBarrios.getSelectedValue().toString()));
            persona.setIdCalle(Integer.parseInt(cbCalles.getSelectedValue().toString()));
            persona.setManzana(tfManzana.getString());
            persona.setCasa(tfCasa.getString());
            persona.setMedidor(tfMedidor.getString());
            persona.setCodigoPostal(tfCodigoPostal.getString());
            if (cbVive.getSelectedItem().toString().equals("SI")) {
                persona.setVive(true);
            } else {
                persona.setVive(false);
            }
            

            if (persona.saveData() > 0) {
                return true;
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Aviso");
                return false;
            }
        } else {
            errorFormPersonasMgmt.showMessage();
            return false;
        }
    }

    private boolean control() {
        boolean valor = true;
        errorFormPersonasMgmt = new ErrorFormPersonasMgmt();
        if (tfApellido.getString().length() <= 0) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FApellido);
        } else if (tfNombre.getString().length() <= 0) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FNombres);
        } else if (cbSexo.getSelectedItem().toString().trim().equals("")) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FSexo);
        } else if (tfNumeroDocumento.getString().equals("")) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FDni);
        } else if (tfFechaNacimiento.getDate().length() <= 0) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FFechaNacimiento);
        } else if ((tfCuil.getString().length() <= 0) || (tfCuil.getString().equals("__-________-_")) || (tfCuil.getString().equals("00-00000000-0")) ) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FCuil);
        } else if (tfDomicilio.getString().length() <= 0) {
            valor = false;
            errorFormPersonasMgmt.setError(errorFormPersonasMgmt.FDomicilio);
        }
        return valor;
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {
            coordinador.inicio();
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    public void setPersona(Persona _persona) {
	persona = _persona;
	loadData();
    }
    
    private void btnRecuperarPunto_actionPerformed(ActionEvent e) {
        tfCoordenadas.retrieveGaiaPoint();
    }
    
    private void btnAgregarBarrio_actionPerformed(ActionEvent e){
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setIdLocation(Integer.parseInt(cbLocalidades.getSelectedValue().toString()));
        ExtendedInternalFrame neighborhoodListContainer = new ExtendedInternalFrame("Barrios");
        NeighborhoodList neighborhoodList = new NeighborhoodList();
        neighborhoodList.setNeighborhoodObject(neighborhood);
        neighborhoodListContainer.setCentralPanel(neighborhoodList);
        neighborhoodListContainer.show();
        neighborhoodListContainer.setClosable(true);
    }
    
    private void btnAgregarCalle_actionPerformed(ActionEvent e){
        Street street = new Street();
        street.setType(0);
        street.setIdLocation(Integer.parseInt(cbLocalidades.getSelectedValue().toString()));
        ExtendedInternalFrame streetListContainer = new ExtendedInternalFrame("Calles");
        StreetList streetList = new StreetList();
        streetList.setStreetObject(street);
        streetListContainer.setCentralPanel(streetList);
        streetListContainer.show();
        streetListContainer.setClosable(true);
    }
    
    private void chkTodasProvincias_actionPerformed(ActionEvent e) {
        if (chkTodasProvincias.isSelected()) {
            cbProvincias.setFilter(cbPaises.getSelectedValue());
        } else {
            cbProvincias.removeFilter();
        }
        cbProvincias.updateUI();
    }

    private void chkTodasLocalidades_actionPerformed(ActionEvent e) {
        mostrarLocalidades();
    }
    
    private void mostrarLocalidades() {
        if (chkTodasLocalidades.isSelected()) {
            cbLocalidades.setFilter(cbProvincias.getSelectedValue());
        } else {
            cbLocalidades.removeFilter();
        }
        cbLocalidades.updateUI();
    }
    private void comboItemListeners() {
        countryItemListener = new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        loadProvince(cbPaises.getSelectedValue());
                    }
                }

            }
        ;
        provinceItemListener = new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        loadLocation(cbProvincias.getSelectedValue());
                    }
                }

            }
        ;
        locationItemListener = new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        loadNeighborhood();
                        loadLocationStreet();
                    }
                }

            }
        ;
        neighborhoodItemListener = new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        loadStreet();
                    }
                }

            }
        ;
    }
    
    private void loadProvince(Object _idCountry) {
        chkTodasProvincias.setSelected(true);
        cbProvincias.setFilter(_idCountry);
    }

    private void loadLocation(Object _idProvince) {
        chkTodasLocalidades.setSelected(true);
        cbLocalidades.setFilter(_idProvince);
    }

    private void loadNeighborhood() {
        chkAllNeighborhood.setSelected(false);
        cbBarrios.removeFilter();
    }

    private void loadLocationStreet() {
        chkAllStreet.setSelected(false);
        cbCalles.removeFilter();
    }

    private void loadStreet() {
        chkAllStreet.setSelected(false);
        cbCalles.removeFilter();
    }
    
    public void addComboItemListener() {
        hasItemListener = true;
        cbPaises.addItemListener(countryItemListener);
        cbProvincias.addItemListener(provinceItemListener);
        cbLocalidades.addItemListener(locationItemListener);
        cbBarrios.addItemListener(neighborhoodItemListener);
    }

    public void removeComboItemListener() {
        if (hasItemListener) {
            hasItemListener = false;
            cbPaises.removeItemListener(countryItemListener);
            cbProvincias.removeItemListener(provinceItemListener);
            cbLocalidades.removeItemListener(locationItemListener);
            cbBarrios.removeItemListener(neighborhoodItemListener);
        }
    }
}
