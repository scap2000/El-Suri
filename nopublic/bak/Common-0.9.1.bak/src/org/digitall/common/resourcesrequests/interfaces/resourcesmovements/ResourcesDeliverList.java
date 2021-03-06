package org.digitall.common.resourcesrequests.interfaces.resourcesmovements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.common.resourcesrequests.classes.ResourcesMovementDetail;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverMgmt;
import org.digitall.common.resourcesrequests.reports.resourcesmovements.ResourcesDeliverListReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesDeliverList extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel content = new BasicPanel();
    private int[] sizeColumnList = {125, 95, 90, 245, 265 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Remitos Internos", dataRow);
    private Vector headerList = new Vector();
    private FindButton btnSearch = new FindButton();
    private AcceptButton btnSave = new AcceptButton();
    private TFInput tfSearchVoucher = new TFInput(DataTypes.STRING,"DespatchNoteNumber",false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE,"CostsCentre",false);
    //VAR
    private int voucherNumber = -1;
    private Vector<Vector> selectedsVector;
    //OBJECTS
    private Voucher voucherSelected;
    private CostsCentre costCentre;
    private ResourcesMovementDetail resourcesMovementDetail;
    private ResourceMovements resourceMovements;
    private Voucher voucher;
    private int[] sizeColumnDetailList = { 269, 146, 110, 100, 52 };
    private Vector detailDataRow = new Vector();
    private GridPanel listResourcesPanel = new GridPanel(50000, sizeColumnDetailList, "Detalle del Remito Interno", detailDataRow);
    private Vector detailList = new Vector();
    private JPanel jPanel1 = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public ResourcesDeliverList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 730, 500));
	findPanel.setBounds(new Rectangle(5, 0, 720, 60));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 50));
	cbCostsCentre.setBounds(new Rectangle(285, 10, 250, 35));
	jPanel1.setPreferredSize(new Dimension(10, 100));
	jPanel1.setMinimumSize(new Dimension(10, 100));
	jPanel1.setLayout(gridLayout1);
	gridLayout1.setRows(2);
	content.setLayout(null);
	content.setPreferredSize(new Dimension(730, 60));
	content.setMinimumSize(new Dimension(730, 263));
	content.setMaximumSize(new Dimension(730, 263));
	btnSearch.setBounds(new Rectangle(545, 25, 30, 20));
	btnSearch.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	tfSearchVoucher.setBounds(new Rectangle(170, 10, 100, 35));
//	content.add(findPanel, null);
        findPanel.add(cbCostsCentre, null);
        findPanel.add(tfSearchVoucher, null);
        findPanel.add(btnSearch, null);
        this.add(findPanel, BorderLayout.NORTH);
	tfSearchVoucher.getTextField().addKeyListener(new KeyAdapter() {

						   public void keyTyped(KeyEvent e) {
						       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							   refresh();
						       }
						   }

					       }
	);
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	cbCostsCentre.getCombo().addItemListener(new ItemListener() {

					      public void itemStateChanged(ItemEvent evt) {
						  if (evt.getStateChange() == ItemEvent.SELECTED) {
						      refresh();
						  }
					      }

					  }
	);
	jPanel1.add(listPanel, null);
	jPanel1.add(listResourcesPanel, null);
	this.add(jPanel1, BorderLayout.CENTER);
	cbCostsCentre.autoSize();
	cbCostsCentre.setGeneralItem(true);
	cbCostsCentre.update();
	setHeaderList();
	setDetailList();
	addButton(btnSave);
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Remitos"));
    }
       
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Nota de Recepci??n");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("InternalDespatchNoteNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");	
	headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement(Environment.lang.getProperty("Description"));
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				loadObjectSelected();
			     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
				//parentContainer.getTabbedPane().setSelectedIndex(1);
			     }
			 }
	});
	
	String params = ""+ cbCostsCentre.getSelectedValue() +",-1";
	listPanel.setParams("cashflow.getAllDespatchNotesForDelivery", params, headerList);	
    }

    public void refresh() {
	voucherNumber = -1;
	if (!tfSearchVoucher.getString().equals(""))  {
	    voucherNumber = Integer.parseInt(tfSearchVoucher.getString());
	}
	String params = ""+ cbCostsCentre.getSelectedValue() + "," + voucherNumber;
	listPanel.refresh(params);
	voucherSelected = new Voucher();
	//if(voucherSelected.getIdVoucher() > -1){
	    refreshDetail();   
	//}
    }
  
    private void loadObjectSelected(){
	voucherSelected = new Voucher();
	voucherSelected.setIdVoucher(Integer.parseInt(dataRow.elementAt(0).toString()));
	voucherSelected.setIdVoucherType(Integer.parseInt(dataRow.elementAt(2).toString()));
	voucherSelected.setNumber(Long.parseLong("0"+ dataRow.elementAt(3).toString()));
	voucherSelected.setDate(dataRow.elementAt(4).toString());
	costCentre = new CostsCentre(Integer.parseInt(dataRow.elementAt(7).toString()));
	costCentre.setName(dataRow.elementAt(8).toString());
	voucherSelected.setCostsCentre(costCentre);
	refreshDetail();
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	selectedsVector = listPanel.getSelectedsVector();
	if ( selectedsVector.size() > 0 ) {
	    ResourcesDeliverMgmt resourcesMovementsToDeliveredMgmt = new ResourcesDeliverMgmt(this);
	    resourcesMovementsToDeliveredMgmt.setCostCentre(cbCostsCentre.getSelectedItem().toString());
	    ExtendedInternalFrame resourcesMovementsToDeliveredContainer = new ExtendedInternalFrame("Datos de la entrega");
	    resourcesMovementsToDeliveredContainer.setCentralPanel(resourcesMovementsToDeliveredMgmt);
	    resourcesMovementsToDeliveredContainer.setInfo("Datos complementarios de la Entrega de Recursos");
	    resourcesMovementsToDeliveredContainer.show();
	} else{
	    Advisor.messageBox("No hay Remitos seleccionados","Error");
	}
    }
  
    public int save(){
	String saveParams = "";
	int result = -1;
	resourceMovements.saveData();
	boolean newMovement = false;
	for (int i = 0 ;  i < selectedsVector.size() ; i++)  {
	    Vector vectorSelected = selectedsVector.elementAt(i);
	    voucher = new Voucher(Integer.parseInt(vectorSelected.elementAt(0).toString()));
	    resourcesMovementDetail = new ResourcesMovementDetail();
	    resourcesMovementDetail.setResourceMovements(resourceMovements);
	    resourcesMovementDetail.setVoucher(voucher);
	    if (resourcesMovementDetail.saveData() != -1)  {
		newMovement = true;		
	    }
	    
	    saveParams = ""+ voucher.getIdVoucher() +",0,0";
	    ResultSet detail = LibSQL.exFunction("cashflow.getAllVoucherDetail",saveParams);
	    try  {
		while (detail.next())  {
		    String detailParams = ""+ detail.getInt("idvoucherdetail") + "," + detail.getInt("idresource") + "," + 
					    detail.getInt("idbrand");
		    result = LibSQL.getInt("cashflow.setInternalDespatchNoteDetailToDelivery",detailParams);
		    if (result == -1)  {
			/* Entrega directa */
		    }
		}
		if (newMovement)  {
		    saveParams = ""+ voucher.getIdVoucher();
		    if ( LibSQL.getInt("resourcescontrol.setPurchaseOrderRequestStatus",saveParams) != -1 )  {
			String closeVoucherParam = "" + voucher.getIdVoucher() + ",true";
		        if (LibSQL.getInt("cashflow.setCloseVoucher",closeVoucherParam) == 0)  {
			    refresh();
		        }
		    }
		}
	    } catch (Exception ex)  {
		ex.printStackTrace();
	    }
	}
	if (newMovement)  {
	    /*if (Advisor.question("Pregunta","??Desea imprimir el Remito Interno?"))*/  {
		new ResourcesDeliverListReport(selectedsVector, resourceMovements);    	    
	    }
	}
	
	return result;
    }

    public void setResourceMovements(ResourceMovements _resourceMovements) {
	resourceMovements = _resourceMovements;
	if (save() == -1)  {
	    Advisor.messageBox("Ocurri?? un error al grabar el movimiento","Error");
	}
    }
    
    public void setCostCentre(CostsCentre _costsCentre) {
	cbCostsCentre.setSelectedID(""+ _costsCentre.getIdCostCentre());
	refresh();
    }

    private void setDetailList() {
	detailList.removeAllElements();
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Resource"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Brand"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("FinalQty"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Unit"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("P.M. N??");
	listResourcesPanel.getTable().addMouseListener(new MouseAdapter() {

						    public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							    //parentContainer.getTabbedPane().setSelectedIndex(1);
							}
						    }

						}
	);
	int idvoucher = -1;
	if (voucherSelected != null) {
	    idvoucher = voucherSelected.getIdVoucher();
	}
	String params = "" + idvoucher;
	listResourcesPanel.setParams("cashflow.getAllVoucherDetail", params, detailList);
    }

    public void refreshDetail() {
	int idvoucher = -1;
	if (voucherSelected != null && voucherSelected.getNumber() != -1) {
	    idvoucher = voucherSelected.getIdVoucher();
	}
	String params = "" + idvoucher;
	listResourcesPanel.refresh(params);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Para realizar una entrega tilde uno o varios Remitos Internos y presione el bot??n \"Aceptar\"");
    }

}
