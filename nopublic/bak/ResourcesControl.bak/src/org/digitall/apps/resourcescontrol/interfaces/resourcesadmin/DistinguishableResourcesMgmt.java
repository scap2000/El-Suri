package org.digitall.apps.resourcescontrol.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

import org.digitall.apps.resourcescontrol.classes.DistinguishableResource;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class DistinguishableResourcesMgmt extends BasicPrimitivePanel{
    
    private BasicPanel bpCentro = new BasicPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel bpCentroNorte = new BasicPanel();
    
    private CBInput cbMarca = new CBInput(0,"Brand",false);
    private CBInput cbDeposito = new CBInput(0,"Depots",false);
    private CBInput cbResponsable = new CBInput(0,"PersonCharge",false);
    private CBInput cbTipoIdentificacion = new CBInput(0,"Identification",false);
    private CBInput cbRecurso = new CBInput(0,"Resource",false);
    
    private TFInput tfFechaAdquisicion = new TFInput(DataTypes.DATE,"AcquisitionDate",false);
    private TFInput tfFechaBaja = new TFInput(DataTypes.DATE,"DropOffDate",false);
    private TFInput tfNroIdentificacion = new TFInput(DataTypes.STRING,"IdentificationNumber",false);
    private TFInput tfDesgaste = new TFInput(DataTypes.DOUBLE,"LifeTime",false);
    private TFInput tfTipoDesgaste = new TFInput(DataTypes.STRING,"LifeTimeTypes",false);
    private TFInput tfBuscarResponsable = new TFInput(DataTypes.STRING,"SearchButton",false);       
    private TFInput tfBuscarRecurso = new TFInput(DataTypes.STRING,"SearchButton",false);
    private TFInput tfBuscarMarca = new TFInput(DataTypes.STRING,"SearchButton",false);
    private TFInput tfPrecio = new TFInput(DataTypes.DOUBLE,"Price",false);
    private TFInput tfStock = new TFInput(DataTypes.INTEGER,"Qty",false);
    
    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    private int idTipoDesgaste = -1; 
    
    private DistinguishableResource distinguishableResource;
    private DistinguishableResourcesList parent;
    private TFInput tfCode = new TFInput(DataTypes.STRING,"Code",false);
    private TFInput tfNumber = new TFInput(DataTypes.STRING,"Number",false);
    private JLabel lblGuion = new JLabel();

    public DistinguishableResourcesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	//this.setLayout(borderLayout1);
	this.setSize(new Dimension(585, 339));
	this.setPreferredSize(new Dimension(585, 290));
	bpCentroNorte.setBorder(BorderPanel.getBorderPanel("Agregar / modificar  un recurso distinguible"));
	bpCentroNorte.setSize(new Dimension(580, 245));
	bpCentroNorte.setPreferredSize(new Dimension(560, 280));
	bpCentroNorte.setLayout(null);
	cbMarca.setBounds(new Rectangle(150, 65, 260, 35));	
	tfFechaAdquisicion.setBounds(new Rectangle(20, 165, 125, 35));
	tfFechaBaja.setBounds(new Rectangle(225, 165, 125, 35));
	cbDeposito.setBounds(new Rectangle(20, 210, 330, 35));
	cbResponsable.setBounds(new Rectangle(150, 255, 410, 35));
	tfDesgaste.setBounds(new Rectangle(500, 210, 60, 35));	
	tfTipoDesgaste.setEnabled(false);
	tfTipoDesgaste.setBounds(new Rectangle(370, 210, 120, 35));
	cbTipoIdentificacion.setBounds(new Rectangle(20, 115, 155, 35));
	tfNroIdentificacion.setBounds(new Rectangle(440, 115, 120, 35));
	bpCentro.setLayout(borderLayout2);
        bpCentroNorte.add(lblGuion, null);
        bpCentroNorte.add(tfNumber, null);
        bpCentroNorte.add(tfCode, null);
        bpCentroNorte.add(tfStock, null);
	bpCentroNorte.add(tfPrecio, null);
	bpCentroNorte.add(tfBuscarMarca, null);
	bpCentroNorte.add(tfBuscarRecurso, null);
	bpCentroNorte.add(cbRecurso, null);
        bpCentroNorte.add(tfBuscarResponsable, null);
        bpCentroNorte.add(tfNroIdentificacion, null);
        bpCentroNorte.add(cbTipoIdentificacion, null);
        bpCentroNorte.add(tfTipoDesgaste, null);
        bpCentroNorte.add(tfDesgaste, null);
        bpCentroNorte.add(cbResponsable, null);
        bpCentroNorte.add(cbDeposito, null);
        bpCentroNorte.add(tfFechaBaja, null);
        bpCentroNorte.add(tfFechaAdquisicion, null);
        bpCentroNorte.add(cbMarca, null);
        this.addButton(btnClose);
	this.addButton(btnSaveData);
	bpCentro.add(bpCentroNorte, BorderLayout.CENTER);
        this.add(bpCentro, BorderLayout.CENTER);
        btnSaveData.setBounds(new Rectangle(510, 245, 30, 25));
	btnSaveData.addActionListener(new ActionListener() {

						 public void actionPerformed(ActionEvent e) {
						     btnSaveData_actionPerformed(e);
						 }

					     }
	);
	btnClose.setBounds(new Rectangle(540, 245, 30, 25));
	btnClose.addActionListener(new ActionListener() {

						    public void actionPerformed(ActionEvent e) {
							btnClose_actionPerformed(e);
						    }

						}
	);
        tfCode.setBounds(new Rectangle(225, 115, 65, 35));
        tfNumber.setBounds(new Rectangle(305, 115, 95, 35));
        lblGuion.setText("-");
        lblGuion.setBounds(new Rectangle(290, 130, 15, 20));
        lblGuion.setForeground(Color.red);
        lblGuion.setHorizontalAlignment(SwingConstants.CENTER);
        lblGuion.setHorizontalTextPosition(SwingConstants.LEADING);
        lblGuion.setFont(new Font("Dialog", 1, 16));
        cbRecurso.setBounds(new Rectangle(150, 25, 260, 35));
	tfBuscarResponsable.setBounds(new Rectangle(20, 255, 120, 35));
	
	tfBuscarRecurso.setBounds(new Rectangle(20, 25, 120, 35));
	tfBuscarRecurso.getTextField().addKeyListener(new KeyAdapter() {

				     public void keyReleased(KeyEvent e) {
					 tfiBuscarRecurso_keyReleased(e);
				     }

				 }
	);
	tfBuscarMarca.setBounds(new Rectangle(20, 65, 120, 35));
	tfBuscarMarca.getTextField().addKeyListener(new KeyAdapter() {

				   public void keyReleased(KeyEvent e) {
				       tfiBuscarMarca_keyReleased(e);
				   }

			       }
	);
	tfPrecio.setBounds(new Rectangle(480, 65, 80, 35));
	tfStock.setBounds(new Rectangle(480, 25, 80, 35));
	tfStock.setEnabled(false);
	tfBuscarResponsable.getTextField().addKeyListener(new KeyAdapter() {
			
					 public void keyReleased(KeyEvent e) {					     
					     tfiBuscarResponsable_keyReleased(e);
					 }

				     }
	);
        cbTipoIdentificacion.getCombo().addItemListener(new ItemListener() {

                                        public void itemStateChanged(ItemEvent evt) {
                                            if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                tfCode.setValue(cbTipoIdentificacion.getSelectedValueRef());
                                            }
                                        }

                                    }
        );
        
	cbDeposito.autoSize();
	cbResponsable.autoSize();	
	cbTipoIdentificacion.autoSize();	
	cbRecurso.autoSize();
	cbMarca.autoSize();	
	loadCombo();
        tfFechaBaja.setEnabled(false);
        tfDesgaste.setEnabled(false);
        tfCode.setEnabled(false);
        tfNroIdentificacion.setEnabled(false);
        
    }
    
    
    
    public void loadCombo() {
	cbTipoIdentificacion.loadJCombo(LibSQL.exFunction("personalfiles.getAllIdentificationTypeResources", ""));   	
	cbDeposito.loadJCombo(LibSQL.exFunction("personalfiles.getAllDepots", ""));     
	cbResponsable.loadJCombo(LibSQL.exFunction("personalfiles.getAllPersonsCombo", "''"));               
	/*cbRecurso.loadJCombo(LibSQL.exFunction("personalfiles.getAllResourcesCombo", "''"));      	
	cbRecurso.setSelectedValue("Todos");*/
	cbRecurso.loadJCombo(LibSQL.exFunction("resourcescontrol.getDistinguishableResources", "'"+ tfBuscarRecurso.getValue() +"'" ));
	cbMarca.loadJCombo(LibSQL.exFunction("personalfiles.getAllBrandsCombo", "''"));
	//cbMarca.setSelectedValue("Todas");
    }
    
   
    private void tfiBuscarResponsable_keyReleased(KeyEvent e) {	
	if (e.getKeyCode() == KeyEvent.VK_ENTER){	   
	    String params = "'"+tfBuscarResponsable.getValue().toString()+"'";
	    cbResponsable.loadJCombo(LibSQL.exFunction("personalfiles.getAllPersonsCombo", params));   		
	}
    }
    
    public void setIdRecurso(int _idRecurso) {		
	cbRecurso.setSelectedID(_idRecurso);			
    }
    
    public void setIdMarca (int _idMarca) {		
	cbMarca.setSelectedID(_idMarca);
    }
    
    public void setNombreTipoVidaUtil(String _nombreTipoDesgaste) {    
	tfTipoDesgaste.setValue(_nombreTipoDesgaste);
    }

    public void setIdTipoVidaUtil (int _idTipoDesgaste) {
	idTipoDesgaste = _idTipoDesgaste;
    }
    
    private void btnSaveData_actionPerformed(ActionEvent e) {
	if (saveData()) {   
	    clearData();
            parent.refresh();
            getParentInternalFrame().close();
	    //distinguishableResources = null;
	    
	}
    }
  
    public boolean saveData() {
	if (tfNroIdentificacion.getString().length() > 0) {
	    if (!cbMarca.getSelectedValue().equals("0"))  {//NO PUEDE ELEJIR MARCAS IGUAL A TODAS
	        if (distinguishableResource == null) {
	            distinguishableResource = new DistinguishableResource();
	        }
                loadData();
	        int result = distinguishableResource.saveData();
	        //int result = -1;
	        if (result > 0) {
	           return true;
	        } else {
	            Advisor.messageBox("Ocurrio un error al grabar los datos", "Aviso");
	            return false;
	        }	    
	    }else {
	        Advisor.messageBox("Debe elejir una marca valida", "Aviso");
	        return false;
	    }	    
		 
	} else {
	    Advisor.messageBox("Debe al menos completar tipo y n?? de identificaci??n", "Aviso");
	    return false;
	}	
    }

    private void clearData() {
	tfFechaAdquisicion.setValue("");
	tfFechaBaja.setValue("");
	//cbiDeposito
	//cbiResponsable
	tfDesgaste.setValue(0);
	//cbiTipoDesgaste
	//cbiTipoIdentificacion
	tfNroIdentificacion.setValue("");		
	tfBuscarResponsable.setValue("");
        tfCode.setValue("");
        tfNumber.setValue("");
	tfPrecio.setValue(0);	
    }

    

    private void loadData() {		
        distinguishableResource.getResource().setIdResource(Integer.parseInt(cbRecurso.getSelectedValue().toString()));
        distinguishableResource.getBrands().setIdBrand(Integer.parseInt(cbMarca.getSelectedValue().toString()));
        distinguishableResource.setAcquisitionDate(Proced.setFormatDate(tfFechaAdquisicion.getString(), false));
        distinguishableResource.setDropOffDate(Proced.setFormatDate(tfFechaBaja.getString(), false));
        distinguishableResource.getDepot().setIdDepot(Integer.parseInt(cbDeposito.getSelectedValue().toString()));
        distinguishableResource.getPerson().setIdPerson(Integer.parseInt(cbResponsable.getSelectedValue().toString()));
        distinguishableResource.setWaste(Double.parseDouble(tfDesgaste.getValue().toString()));
        //distinguishableResource.setIdLifeTimeType(idTipoDesgaste);
        distinguishableResource.setIdIdentificationType(Integer.parseInt(cbTipoIdentificacion.getSelectedValue().toString()));
        distinguishableResource.setIdentificationTypeNumber(cbTipoIdentificacion.getSelectedValueRef() +"-"+ tfNumber.getValue().toString());
        distinguishableResource.setPrice(Double.parseDouble(tfPrecio.getValue().toString()));
    }

    private void btnClose_actionPerformed(ActionEvent e) {		
	getParentInternalFrame().close();
    }

    private void bBuscar_actionPerformed(ActionEvent e) {
	
	String params = cbRecurso.getSelectedValue()  +","+cbMarca.getSelectedValue();        
	ResultSet data = LibSQL.exFunction("personalfiles.getPriceStockDeMarcaPorRecurso", params);  
	try {
	    if (data.next()) {      
		tfPrecio.setValue(data.getDouble("price"));
		tfStock.setValue(data.getInt("stock"));		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	
	params = cbRecurso.getSelectedValue()+"";        
	data = LibSQL.exFunction("personalfiles.getLifeTimeTypeDeRecursoPorMarca", params);  
	try {
	    if (data.next()) {      
		tfTipoDesgaste.setValue(data.getString("name"));
		idTipoDesgaste = data.getInt("idlifetimetype");      
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    private void tfiBuscarRecurso_keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER){         
	    String params = "'"+tfBuscarRecurso.getValue().toString()+"'";
	    cbRecurso.loadJCombo(LibSQL.exFunction("personalfiles.getAllResourcesCombo", params));  
	}
    }

    private void tfiBuscarMarca_keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER){         
	    String params = "'"+tfBuscarMarca.getValue().toString()+"'";
	    cbMarca.loadJCombo(LibSQL.exFunction("personalfiles.getAllBrandsCombo", params));                            	    	   
	}
    }
    
    
    public void setDistinguishableResource(DistinguishableResource _distinguishableResource) {           
        distinguishableResource = _distinguishableResource;
        if (distinguishableResource.getIdDistinguishableResource() != -1)  {
            cbRecurso.setSelectedID(distinguishableResource.getResource().getIdResource());
            cbMarca.setSelectedID(distinguishableResource.getBrands().getIdBrand());
            if (distinguishableResource.getAcquisitionDate() != null) {
                tfFechaAdquisicion.setValue(Proced.setFormatDate(distinguishableResource.getAcquisitionDate(), true));
            }
            if (distinguishableResource.getDropOffDate() != null) {
                tfFechaBaja.setValue(Proced.setFormatDate(distinguishableResource.getDropOffDate().toString(),true));
            }
            cbDeposito.setSelectedID(distinguishableResource.getDepot().getIdDepot());
            cbResponsable.setSelectedID(distinguishableResource.getPerson().getIdPerson());
            tfDesgaste.setValue(distinguishableResource.getWaste());
            idTipoDesgaste = Integer.valueOf(distinguishableResource.getIdWasteUnit());
            cbTipoIdentificacion.setSelectedID(distinguishableResource.getIdIdentificationType());
            tfCode.setValue(cbTipoIdentificacion.getSelectedValueRef());
            tfNroIdentificacion.setValue(distinguishableResource.getIdentificationTypeNumber());
            tfPrecio.setValue(distinguishableResource.getPrice());
            tfStock.setValue(1);
            tfNumber.setValue(distinguishableResource.getIdentificationTypeNumber().substring(4));
            tfBuscarRecurso.setEnabled(false);
            cbRecurso.setEnabled(false);
            tfStock.setEnabled(false);
        } else  {
            // es para un alta
        }
    }
    
    public void setParent(DistinguishableResourcesList _distinguishableResourcesList) {
        parent = _distinguishableResourcesList;
    }

    

}
