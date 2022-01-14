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
 * CompanyPanel.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces.providers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.interfaces.CompanyTreePanel;
import org.digitall.common.components.AddressList;
import org.digitall.common.components.PanelAddress;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CompanyPanel extends BasicContainerPanel {

    private BasicPanel panelData = new BasicPanel();
    private TFInput tfIdentifcationNumber = new TFInput(DataTypes.INTEGER, "IdentificationNumber", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "TradeName", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private CBInput cbIdentifiactionType = new CBInput(CachedCombo.IDENTIFICATION_TABS, "Identification");
    private CBInput cbTributaryCondition = new CBInput(CachedCombo.TRIBUTARYCONDITION_TABS, "TributaryCondition");
    private CBInput cbPersonCharge = new CBInput(0, "PersonCharge");
    private CBInput cbSuffix = new CBInput(CachedCombo.SUFFIXCOMPANY_TABS, "Suffix");
    private CBInput cbCommunicationType = new CBInput(CachedCombo.COMMUNICATIONTYPE_TABS, "CommunicationType");
    private CBInput cbCompanyType = new CBInput(CachedCombo.COMPANYTYPE_TABS, "CompanyType");
    private CBInput cbCompanySkill = new CBInput(CachedCombo.SKILLCOMPANY_TABS, "Skill");
    private AcceptButton btnAccept = new AcceptButton();
    private BasicPanel panelPhoto = new BasicPanel();
    private BasicPanel panelLogo = new BasicPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private int idCompanyType;
    private int idCompany;
    private int idParent;
    private Component parent;
    private Object panelCompanyList;
    private PanelAddress panelAddress = new PanelAddress();
    private JDesktopPane parentDesktop;
    private boolean addAction = true;
    private CancelDataButton btnCancel = new CancelDataButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private BasicPanel jPanel3 = new BasicPanel();
    private int[] sizeColumnList = { 509 };
    private Vector dataRowList = new Vector();
    private GridPanel panelSkillList = new GridPanel(30, sizeColumnList, "Habilidades", dataRowList);
    private Vector headerSkillList = new Vector();
    private BasicPanel panelSkill = new BasicPanel();
    private AssignButton btnAddSkill = new AssignButton(true);
    private DeleteButton btnDeleteSkill = new DeleteButton();
    private int actionReturnType = COMPANYTREE;
    private ProvidersMain parentMain;
    public static final int COMPANYTREE = 1;
    public static final int COMPANYENTITY = 2;
    public static final int PROVIDERLIST = 3;
    private Provider provider;
    private TFInput tfFindAccounting = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private CBInput cbAccount = new CBInput(0, "Accounting", false);
    private BorderLayout borderLayout1 = new BorderLayout();

    private BasicCheckBox chkMultilateralAgreement = new BasicCheckBox();
    private TFInput tfMultilateralAgreementNumber = new TFInput(DataTypes.STRING,"MultilateralAgreementNumber",false);

    public CompanyPanel(Component _parent, int _actionReturnType) {
	try {
	    parent = _parent;
	    actionReturnType = _actionReturnType;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(560, 334));
	panelData.setLayout(null);
	panelData.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	btnAccept.setBounds(new Rectangle(440, 300, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfIdentifcationNumber.setBounds(new Rectangle(110, 95, 150, 35));
	tfName.setBounds(new Rectangle(15, 15, 430, 35));
	tfStartDate.setBounds(new Rectangle(440, 135, 95, 35));
	tfDescription.setBounds(new Rectangle(15, 215, 520, 35));
	btnCancel.setBounds(new Rectangle(515, 300, 40, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnSave.setBounds(new Rectangle(480, 300, 40, 25));
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	jPanel3.setLayout(null);
	jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	panelSkillList.setBounds(new Rectangle(7, 85, 530, 175));
	panelSkill.setBounds(new Rectangle(7, 10, 530, 65));
	panelSkill.setLayout(null);
	cbCompanySkill.setBounds(new Rectangle(30, 20, 325, 35));
	btnAddSkill.setBounds(new Rectangle(430, 30, 40, 25));
        btnAddSkill.setToolTipText("Agregar habilidad");
	btnAddSkill.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnAddSkill_actionPerformed(e);
				   }

			       }
	);
	btnDeleteSkill.setBounds(new Rectangle(480, 30, 40, 25));
	btnDeleteSkill.setPreferredSize(new Dimension(40, 25));
	btnDeleteSkill.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnDeleteSkill_actionPerformed(e);
				      }

				  }
	);
	tfFindAccounting.setBounds(new Rectangle(15, 175, 145, 35));
	cbAccount.setBounds(new Rectangle(170, 175, 365, 35));
        chkMultilateralAgreement.setText("Conv. Mult.");
        chkMultilateralAgreement.setBounds(new Rectangle(175, 150, 95, 20));
        tfMultilateralAgreementNumber.setBounds(new Rectangle(295, 135, 95, 35));
        cbCompanyType.setBounds(new Rectangle(15, 135, 145, 35));
	panelPhoto.setBounds(new Rectangle(10, 10, 250, 240));
	panelPhoto.setLayout(null);
	panelPhoto.setBorder(BorderPanel.getBorderPanel("Foto", Color.black, Color.black));
	panelLogo.setBounds(new Rectangle(285, 10, 250, 240));
	panelLogo.setLayout(null);
	panelLogo.setBorder(BorderPanel.getBorderPanel("Logo", Color.black, Color.black));
	tabbedPane.setBounds(new Rectangle(5, 0, 550, 290));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel2.setLayout(borderLayout1);
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	cbIdentifiactionType.setBounds(new Rectangle(15, 95, 85, 35));
	cbTributaryCondition.setBounds(new Rectangle(355, 95, 180, 35));
	cbPersonCharge.setBounds(new Rectangle(15, 55, 380, 35));
	cbSuffix.setBounds(new Rectangle(465, 15, 70, 35));
	cbCommunicationType.setBounds(new Rectangle(405, 55, 130, 35));
        panelData.add(tfMultilateralAgreementNumber, null);
        panelData.add(chkMultilateralAgreement, null);
        panelData.add(cbAccount, null);
	panelData.add(tfFindAccounting, null);
	panelData.add(cbCommunicationType, null);
	panelData.add(cbSuffix, null);
	panelData.add(cbPersonCharge, null);
	panelData.add(cbTributaryCondition, null);
	panelData.add(tfStartDate, null);
	panelData.add(tfName, null);
	panelData.add(cbIdentifiactionType, null);
	panelData.add(tfIdentifcationNumber, null);
	panelData.add(cbCompanyType, null);
	panelData.add(tfDescription, null);
	jPanel1.add(panelPhoto, null);
	jPanel1.add(panelLogo, null);
	tabbedPane.addTab("Razón Social", panelData);
	tabbedPane.addTab("Dirección", jPanel2);
	tabbedPane.addTab("Foto & Logo", jPanel1);
	panelSkill.add(btnDeleteSkill, null);
	panelSkill.add(btnAddSkill, null);
	panelSkill.add(cbCompanySkill, null);
	panelSkill.setBorder(BorderPanel.getBorderPanel("Agregar Habilidad"));
	jPanel3.add(panelSkill, null);
	jPanel3.add(panelSkillList, null);
	tabbedPane.addTab("Habilidades", jPanel3);
	jPanel2.add(panelAddress, BorderLayout.CENTER);
	this.add(btnSave, null);
	this.add(btnCancel, null);
	this.add(tabbedPane, null);
	this.add(btnAccept, null);
	cbCommunicationType.autoSize();
	cbIdentifiactionType.autoSize();
	cbPersonCharge.autoSize();
	cbSuffix.autoSize();
	cbTributaryCondition.autoSize();
	cbCompanyType.autoSize();
	cbCompanySkill.autoSize();
	cbAccount.autoSize();
	setHeaderSkillList();
	cbPersonCharge.getCombo().addItem("N/A", "0");
	clearData();
	tfFindAccounting.getTextField().addKeyListener(new KeyAdapter() {

						    public void keyTyped(KeyEvent e) {
							if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							    loadComboAccounting();
							}
						    }

						}
	);
        
        chkMultilateralAgreement.addActionListener(new ActionListener() {

                                      public void actionPerformed(ActionEvent e) {
                                          chkMultilateralAgreement_actionPerformed(e);
                                      }

                                  }
        );
        
    }

    private void loadComboAccounting() {
	String param = "2000 ,'" + tfFindAccounting.getString() + "'";
	/* Sólo cuentas del Pasivo */
	cbAccount.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByFilter", param));
    }

    private void loadSkillCombo() {
	cbCompanySkill.loadJCombo(LibSQL.exFunction("org.getallCompanySkillsAvailable", String.valueOf(idCompany)));
    }

    private void setHeaderSkillList() {
	headerSkillList.removeAllElements();
	headerSkillList.addElement("*");
	headerSkillList.addElement("*");
	headerSkillList.addElement("Nombre");
	headerSkillList.addElement("*");
	panelSkillList.getTable().addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
						    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						    }
						}

					    }
	);
	String params = "-1";
	panelSkillList.setParams("org.getallCompanySkills", params, headerSkillList);
    }

    public void refresh() {
	String params = String.valueOf(idCompany);
	panelSkillList.refresh(params);
    }

    public void clearData() {
	addAction = true;
	tfName.setValue("");
	tfIdentifcationNumber.setValue("");
	tfStartDate.setValue("");
	tfDescription.setValue("");
	cbCommunicationType.setSelectedID("0");
	cbCompanyType.setSelectedID("0");
	cbIdentifiactionType.setSelectedID("0");
	cbPersonCharge.setSelectedID("0");
	cbSuffix.setSelectedID("0");
	cbTributaryCondition.setSelectedID("0");
	cbIdentifiactionType.setSelectedID("2");
	panelAddress.clearData();
	tabbedPane.setSelectedIndex(0);
	tabbedPane.setEnabledAt(2, false);
	tabbedPane.setEnabledAt(3, false);
        chkMultilateralAgreement.setSelected(false);
        tfMultilateralAgreementNumber.setValue("");
        tfMultilateralAgreementNumber.setEnabled(false);
    }

    public void setData(ResultSet _rs) {
	try {
	    idCompany = _rs.getInt("idcompany");
	    idParent = _rs.getInt("idparent");
	    addAction = false;
	    tabbedPane.setEnabledAt(3, true);
	    loadSkillCombo();
	    refresh();
	    tfName.setValue(_rs.getString("name"));
	    tfIdentifcationNumber.setValue(_rs.getString("identificationnumber"));
	    tfStartDate.setValue(Proced.setFormatDate(_rs.getString("startdate"), true));
	    tfDescription.setValue(_rs.getString("description"));
	    cbCommunicationType.setSelectedID(_rs.getString("idcommunicationtype"));
	    cbCompanyType.setSelectedID(_rs.getString("idcompanytype"));
	    cbIdentifiactionType.setSelectedID(_rs.getString("ididentificationtype"));
	    cbPersonCharge.setSelectedID(_rs.getString("idpersoncharge"));
	    cbSuffix.setSelectedID(_rs.getString("idsuffix"));
	    cbTributaryCondition.setSelectedID(_rs.getString("idtributarycondition"));
	    setAddressData(idCompany);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void setData() {
	idCompany = provider.getIdProvider();
	idParent = provider.getIdParent();
	addAction = false;
	tabbedPane.setEnabledAt(3, true);
	loadSkillCombo();
	refresh();
	tfName.setValue(provider.getName());
	tfIdentifcationNumber.setValue("" + provider.getIdentificationNumber());
	tfStartDate.setValue(Proced.setFormatDate(provider.getStartDate(), true));
	tfDescription.setValue(provider.getDescription());
	cbCommunicationType.setSelectedID(provider.getIdCommunicationType());
	cbCompanyType.setSelectedID(provider.getIdCompanyType());
	cbIdentifiactionType.setSelectedID(provider.getIdIdentificationType());
	cbPersonCharge.setSelectedID(provider.getPersonCharge().getIdPerson());
	cbSuffix.setSelectedID(provider.getIdSuffix());
	cbTributaryCondition.setSelectedID(provider.getIdTributaryCondition());
	cbAccount.removeAllItems();
	if (provider.getAccounting().getIDAccount() != -1) {
	    provider.getAccounting().retrieveData();
	    cbAccount.getCombo().addItem(provider.getAccounting().getName(), "" + provider.getAccounting().getIDAccount());
	}
	setAddressData(idCompany);
        chkMultilateralAgreement.setSelected(provider.isMultilateralAggrement());
        tfMultilateralAgreementNumber.setValue(provider.getMultilateralAggrementNumber());
        if (provider.isMultilateralAggrement()) {
            tfMultilateralAgreementNumber.setEnabled(true);
        } else {
            tfMultilateralAgreementNumber.setEnabled(false);
        } 
        
    }

    private void setAddressData(int _idCompany) {
	try {
	    ResultSet rsAddresses = LibSQL.exFunction("org.getCompanyAddressByDefault", String.valueOf(_idCompany));
	    if (rsAddresses.next()) {
		panelAddress.setData(rsAddresses.getInt("idaddress"), true);
	    } else {
		panelAddress.setData(0, true);
	    }
	    panelAddress.setParentDesktop(Environment.getActiveDesktop());
	    panelAddress.setObjectParent(_idCompany, AddressList.COMPANYTYPE);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void addSetData() {
	if (cbAccount.getSelectedIndex() != -1) {
	    String name = tfName.getString();
	    String idsuffix = cbSuffix.getSelectedValue().toString();
	    String ididentifcationtype = cbIdentifiactionType.getSelectedValue().toString();
	    String identificationnumber = tfIdentifcationNumber.getString();
	    String idtributarycondition = cbTributaryCondition.getSelectedValue().toString();
	    String idpersoncharge = "0";
	    String idcommunicationtype = cbCommunicationType.getSelectedValue().toString();
	    String idcompanytype = cbCompanyType.getSelectedValue().toString();
	    String startdate = Proced.setFormatDate(tfStartDate.getString(), false);
	    String photo = "null";
	    String logo = "null";
	    String description = tfDescription.getString();
	    String query = "";
	    boolean multilateral = false;
	    String multilateralnumber = "";
            if (chkMultilateralAgreement.isSelected()) {
                multilateral = true;
                multilateralnumber = tfMultilateralAgreementNumber.getString().trim();
            }
            
	    if (addAction) {
		query = " SELECT org.addCompany('" + name + "'," + idsuffix + "," + ididentifcationtype + ",'" + identificationnumber + "'," + idtributarycondition + "," 
                                                   + idpersoncharge + "," + idcommunicationtype + ",'" + startdate + "'," + idParent + "," + photo + "," + logo + ",'" 
                                                   + description + "'," + idcompanytype +","+ multilateral +",'" + multilateralnumber +"')";
		idCompany = Integer.parseInt(LibSQL.insertQuery(query));
		if (actionReturnType == COMPANYENTITY) {
		    String Query2 = "SELECT file.addRefencedEntity(" + idCompany + ", " + false + ")";
		    LibSQL.insertQuery(Query2);
		}
	    } else {
		query = " SELECT org.setCompany(" + idCompany + ",'" + name + "'," + idsuffix + "," + ididentifcationtype + ",'" + identificationnumber + "'," 
                                                  + idtributarycondition + "," + idpersoncharge + "," + idcommunicationtype + ",'" + startdate + "', " 
                                                  + idParent + "," + photo + "," + logo + ",'" + description + "'," + idcompanytype +","+ multilateral +",'"
                                                  + multilateralnumber +"')";
		LibSQL.updateQuery(query);
	    }
	    if (provider == null) {
		provider = new Provider();
	    }
	    provider.setIdProvider(idCompany);
	    provider.setAccount(new Account(Integer.parseInt("" + cbAccount.getSelectedValue())));
	    provider.saveAccount();
	    panelAddress.setObjectParent(idCompany, AddressList.COMPANYTYPE);
	    panelAddress.saveData("true");
	    actionReturn();
	} else {
	    Advisor.messageBox("Debe seleccionar una cuenta para imputaciones de Pasivo", "Aviso");
	}
    }

    private void actionReturn() {
	switch (actionReturnType) {
	    case COMPANYTREE :
		{
		    clearData();
		    ((CompanyTreePanel)panelCompanyList).drawTreeCompany();
		}
		break;
	    case COMPANYENTITY :
		{
		    //((AddNewEntity)parent).setIdReference(idCompany);
		    parent.setVisible(false);
		}
		break;
	    case PROVIDERLIST :
		clearData();
		parentMain.refresh();
		break;
	    default :
		{

		}
		break;
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	addSetData();
    }

    public void setIdCompanyType(int _idCompanyType) {
	idCompanyType = _idCompanyType;
	addAction = true;
	panelAddress.setIdAddress(0);
	tabbedPane.setEnabledAt(3, false);
	cbCompanyType.setSelectedID("" + idCompanyType);
    }

    public void setPanelCompanyList(Object _panelCompanyList) {
	panelCompanyList = _panelCompanyList;
    }

    public void setParentDesktop(JDesktopPane _parentDesktop) {
	parentDesktop = _parentDesktop;
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	addSetData();
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	actionReturn();
    }

    public void setIdParent(int _idParent) {
	idParent = _idParent;
    }

    private void btnAddSkill_actionPerformed(ActionEvent e) {
	String idSkill = cbCompanySkill.getSelectedValue().toString();
	if (!idSkill.equals("0")) {
	    String query = "SELECT org.addcompanyskill(" + String.valueOf(idCompany) + "," + idSkill + ");";
	    LibSQL.insertQuery(query);
	    loadSkillCombo();
	    refresh();
	}
    }

    public void setParent(Component _parent) {
	parent = _parent;
    }

    private void btnDeleteSkill_actionPerformed(ActionEvent e) {
	if (!dataRowList.isEmpty()) {
	    //String query = "SELECT org.delcompanyskill(" + idCompany + "," + dataRowList.elementAt(1) + ")";
	    Vector idSelecteds = new Vector();
	    idSelecteds = panelSkillList.getSelectedsValuesAt(1);
	    String params = "";
	    for(int i = 0; i < idSelecteds.size(); i++){
		params = "" + idCompany + "," + idSelecteds.elementAt(i).toString();
	        System.out.println("params: "+params);
	        LibSQL.getInt("org.delcompanyskill",params);
	    }
	    //LibSQL.deleteQuery(query);
	    loadSkillCombo();
	    refresh();
	}
    }

    public int getIdCompany() {
	return idCompany;
    }

    public void setParentMain(ProvidersMain parentMain) {
	this.parentMain = parentMain;
	btnAccept.setVisible(false);
    }

    public void setProvider(Provider _provider) {
	provider = _provider;
	clearData();
	if (provider.getIdProvider() != -1) {
	    setData();
	}
    }
    
    private void chkMultilateralAgreement_actionPerformed(ActionEvent e) {
        if (chkMultilateralAgreement.isSelected()) {
            tfMultilateralAgreementNumber.setEnabled(true);
        } else {
            tfMultilateralAgreementNumber.setEnabled(false);
        }
        
    }


}
