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
 * DespatchNoteMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.apps.cashflow.interfaces.CFFrameContainer;
import org.digitall.common.cashflow.interfaces.costscentre.CCExpenditureAccountsTree;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.common.resourcesrequests.classes.ResourcesMovementDetail;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class DespatchNoteMgmt extends BasicContainerPanel {

    private BasicPanel jPanel1 = new BasicPanel();
    private TFInput tfFindEntity = new TFInput(DataTypes.STRING, "FindEntity", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfDespatchNoteNumber = new TFInput(DataTypes.INTEGER, "DespatchNoteNumber", true);
    private CBInput cbEntityType = new CBInput(CachedCombo.ENTITYTYPE, "EntityType");
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private CFFrameContainer parentContainer;
     
    private CBInput cbEntity = new CBInput(0,"Entity",false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private VouchersList parentList;
    private FindButton btnFindEntity = new FindButton();
    private BasicCheckBox chkDetail = new BasicCheckBox();
    private Voucher voucher = new Voucher();
    private CostsCentre costsCentre = new CostsCentre();
    private String entityName;
    private CashMovement cashMovement;
    private CCExpenditureAccountsTree parentTree;
    
    private ResourcesMovementDetail resourcesMovementDetail = new ResourcesMovementDetail();
    private ResourceMovements resourceMovements;
    
    public DespatchNoteMgmt(CFFrameContainer _parentContainer) {
	try {
	    parentContainer = _parentContainer;
	    jbInit();
	    parentContainer.setPanel(this);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(705, 220));
	jPanel1.setBounds(new Rectangle(5, 0, 695, 180));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar Remito"));
	tfFindEntity.setBounds(new Rectangle(195, 80, 130, 35));
	tfDate.setBounds(new Rectangle(595, 30, 85, 35));
	cbEntityType.setBounds(new Rectangle(15, 80, 155, 35));
	btnAccept.setBounds(new Rectangle(600, 190, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(655, 190, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	cbEntity.setBounds(new Rectangle(335, 80, 295, 35));
	tfDescription.setBounds(new Rectangle(15, 130, 560, 35));
	btnFindEntity.setBounds(new Rectangle(640, 90, 40, 25));
	chkDetail.setBounds(new Rectangle(590, 145, 85, 20));
	chkDetail.setFont(new Font("Default", 0, 11));
	chkDetail.setMargin(new Insets(1, 0, 1, 1));
	chkDetail.setText("Con Detalle");
	chkDetail.setHorizontalAlignment(SwingConstants.LEFT);
	tfDespatchNoteNumber.setBounds(new Rectangle(15, 30, 175, 35));
	jPanel1.add(tfDespatchNoteNumber, null);
	jPanel1.add(chkDetail, null);
	jPanel1.add(btnFindEntity, null);
	jPanel1.add(tfDescription, null);
	jPanel1.add(cbEntity, null);
	jPanel1.add(cbEntityType, null);
	jPanel1.add(tfDate, null);
	jPanel1.add(tfFindEntity, null);
	this.add(jPanel1, null);
	this.add(btnAccept, null);
	this.add(btnClose, null);
	cbEntityType.autoSize();
	cbEntity.autoSize();
	setEnabledEntity(false);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindEntity.getTextField().addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						loadComboEntity();
					    }
					}

				    }
	);
	cbEntityType.setItemListener(new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    if (cbEntityType.getSelectedValue().equals("-1")){
			        setEnabledEntity(false);
			        cbEntity.getCombo().removeAllItems();
			    } else {
			        setEnabledEntity(true);				
			        cbEntity.getCombo().removeAllItems();
			        if (voucher != null){
			            if (cbEntityType.getSelectedValue().equals(""+ voucher.getIdEntitytype())){
			               cbEntity.getCombo().addItem(entityName, ""+ voucher.getIdEntity()); 
			            }
			        }
			    }
			}
		    }
		});
    }
   
    private void setEnabledEntity(boolean _enabled){
	tfFindEntity.setEnabled(_enabled);
	cbEntity.setEnabled(_enabled);
	btnFindEntity.setEnabled(_enabled);	
    }
    
    private void loadComboEntity(){
	JCombo combo = new JCombo();
	String param = "'" + tfFindEntity.getString() + "'";
	switch (Integer.parseInt(""+ cbEntityType.getSelectedValue()))  {
	    case EntityTypes.PERSON: 
		combo.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));    
		break;
	    case EntityTypes.PROVIDER: 
		combo.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
	        break;
	    default:
		break;
	}
	 	
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			   
			}
		    }
		};
	cbEntity.setCombo(combo);        
	cbEntity.setItemListener(itemListener);
	cbEntity.updateUI();         
    }
    
    public void loadData(){
	tfDate.setValue(Proced.setFormatDate(voucher.getDate(), true));
	tfDescription.setValue(voucher.getDescription());
	tfDespatchNoteNumber.setValue(voucher.getNumber());
	cbEntityType.setSelectedID(""+ voucher.getIdEntitytype());
	chkDetail.setSelected(voucher.isWithDetail());
	loadComboEntity();
	cbEntity.setSelectedID("" + voucher.getIdEntity());
    }
    private void setObjectData(){
	voucher.setDate(Proced.setFormatDate(tfDate.getString(), false));
	voucher.setDescription(tfDescription.getString());
	voucher.setIdEntitytype(Integer.parseInt(""+ cbEntityType.getSelectedValue()));
	voucher.setIdEntity(Integer.parseInt("0"+ cbEntity.getSelectedValue()));
	voucher.setIdVoucherType(7);	//--> id de remito = 7,m mas adelante cambiarlo para que lo tome desde un store procedure
	voucher.setNumber(Long.parseLong("0"+ tfDespatchNoteNumber.getString()));
	voucher.setWithDetail(chkDetail.isSelected());
	voucher.setCostsCentre(costsCentre);
    }
    public boolean saveData(){
	setObjectData();
	boolean savedata = false;
	int idVoucher = -1;
	if (voucher.getIdVoucher() == -1) {
	    idVoucher = voucher.addData();
	    if (idVoucher != -1)  {
		resourcesMovementDetail.setResourceMovements(resourceMovements);
		resourcesMovementDetail.setVoucher(new Voucher(idVoucher));
	        int idresourcesMovementDetail = resourcesMovementDetail.saveData();
		if (idresourcesMovementDetail != -1)  {
		    savedata = true;
		}
	    }
	} else {
	    idVoucher = voucher.setData();
	    savedata = true;
	}
	return savedata;
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if ( Control() )  {
	    if (saveData())  {
	        parentContainer.close();        
	    } else {
	        Advisor.messageBox("Ocurrio un error, los datos no se registraron","Error");
	    }
	} else  {
	    Advisor.messageBox("No debe haber campos vacios", "Mensaje");
	}
    }
    private boolean Control() {
	if (!tfDespatchNoteNumber.getString().equals("") 
	    && !tfDate.getString().equals("") && !tfDescription.getString().equals("") 
	    && !cbEntity.getSelectedValue().toString().equals("0") ) {
	    return true;
	} else  {
	    return false;
	}
	
    }
    public void setParentList(VouchersList parentList) {
	this.parentList = parentList;
    }

    public void setVoucher(Voucher _voucher) {
	voucher = _voucher;
	parentContainer.setTitle("Agregar Remito");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setEntityName(String entityName) {
	this.entityName = entityName;
    }

    public void setCashMovement(CashMovement cashMovement) {
	this.cashMovement = cashMovement;
    }
   
    public void setParentTree(CCExpenditureAccountsTree parentTree) {
	this.parentTree = parentTree;
    }
    public void setResourceMovements(ResourceMovements _resourceMovements) {
	resourceMovements = _resourceMovements;
    }

    public void setResourcesMovementDetail(ResourcesMovementDetail _resourcesMovementDetail) {
	resourcesMovementDetail = _resourcesMovementDetail;
	voucher.setIdVoucher(resourcesMovementDetail.getVoucher().getIdVoucher());
	voucher.retrieveData();
	parentContainer.setTitle("Modificar Remito");
	loadData();
    }

}
