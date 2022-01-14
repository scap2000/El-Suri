package org.digitall.common.resourcesrequests.interfaces.purchaseorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class PurchaseOrderGenerateList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 233, 75, 101, 92, 125, 84};
    private Vector headerDataRow = new Vector();
    private GridPanel listHeaderPanel = new GridPanel(50000, sizeColumnList, "Listado de Pedidos de Materiales Aprobados", headerDataRow){

	@Override
	public void finishLoading() {
	    btnAccept.setEnabled(false);
	    detailPanel.refresh("-1, 'true'");
	}
    } ;
    private BasicPanel findPanel = new BasicPanel();
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbPriority = new CBInput(CachedCombo.PRIORITYREQUEST, "Priority", false);
    private FindButton btnFind = new FindButton();
    private int[] sizeColumnListAuth = { 269, 75, 103, 86, 85, 89, 197 };
    private Vector detailDataRow = new Vector();
    private GridPanel detailPanel = new GridPanel(50000, sizeColumnListAuth, "Materiales Aprobados", detailDataRow);
    private BasicPanel contenPanel = new BasicPanel();
    private AcceptButton btnAccept = new AcceptButton();

    public PurchaseOrderGenerateList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(780, 500));
	setTitle("Generar nueva Orden de Compra");
	listHeaderPanel.setPreferredSize(new Dimension(400, 200));
	listHeaderPanel.setSize(new Dimension(780, 200));
	listHeaderPanel.setMinimumSize(new Dimension(2, 500));
	listHeaderPanel.setBounds(new Rectangle(0, 60, 780, 200));
	//findPanel.add(cbPriority, null);
	findPanel.add(btnFind, null);
	findPanel.add(cbCostsCentre, null);
	findPanel.setLayout(null);
	
	findPanel.setBounds(new Rectangle(0, 0, 770, 100));
	
	cbCostsCentre.setBounds(new Rectangle(195, 15, 395, 35));
	cbPriority.setBounds(new Rectangle(465, 10, 85, 35));
	btnFind.setBounds(new Rectangle(595, 25, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	detailPanel.setSize(new Dimension(780, 200));
	detailPanel.setMinimumSize(new Dimension(173, 200));
	detailPanel.setPreferredSize(new Dimension(400, 200));
	contenPanel.setBounds(new Rectangle(85, 45, 770, 270));
	
	contenPanel.setLayout(new BorderLayout());
	contenPanel.add(findPanel,BorderLayout.NORTH);
	contenPanel.add(listHeaderPanel,BorderLayout.CENTER);
	contenPanel.add(detailPanel,BorderLayout.SOUTH);
	
	this.setContent(contenPanel);
	cbPriority.addGeneralItem();
	cbCostsCentre.addGeneralItem();
	btnAccept.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }
			     }
	);
	addButton(btnAccept);
	btnAccept.setEnabled(false);
	btnAccept.setToolTipText("Generar la Orden de Compra para el Pedido de Materiales seleccionado");
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Pedido de Materiales"));
	findPanel.setPreferredSize(new Dimension(780, 60));
	setHeaderList();
	setDetailHeaderList();
	listHeaderPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
							 loadSelectedObject();
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							 tryToGeneratePurchaseOrder();
						     }
						 }

					     }
	);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede generar una Orden de Compra con sólo doble click sobre un Pedido de Materiales Aprobado");
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement(Environment.lang.getProperty("RequestNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
        headerList.addElement(Environment.lang.getProperty("Liberación"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Solicitor"));
	headerList.addElement("*");//Environment.lang.getProperty("Cost"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");//Environment.lang.getProperty("Priority"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Costo"));
	headerList.addElement("*");
	listHeaderPanel.setParams("resourcescontrol.getAllResourcesRequestsApproved", "-1,'','','',15,-1,-1", headerList);
    }
    
    public void refresh() {
	btnAccept.setEnabled(false);
	btnAccept.setToolTipText("Generar la Orden de Compra para el Pedido de Materiales seleccionado");
	//String params = cbCostsCentre.getSelectedValue() + ",'','','',3," + cbPriority.getSelectedValue() + ",-1";
	//Quito el filtro de prioridad porque casi no se usa
	String params = cbCostsCentre.getSelectedValue() + ",'','','',15,-1,-1";
	listHeaderPanel.refresh(params);
    }

    private void loadSelectedObject() {
	btnAccept.setEnabled(false);
	btnAccept.setToolTipText("Generar la Orden de Compra para el Pedido de Materiales seleccionado");
	if (!headerDataRow.isEmpty()) {
	    detailPanel.refresh(headerDataRow.elementAt(0) + ", 'true'");
	    btnAccept.setToolTipText("<html><center><u>Generar la Orden de Compra</u><br>Pedido de Materiales Nº " + headerDataRow.elementAt(3) + " - " + headerDataRow.elementAt(2) + "</center></html>");
	    btnAccept.setEnabled(true);
	}
    }

    private void tryToGeneratePurchaseOrder() {
	if (!headerDataRow.isEmpty()) {
            if (Proced.compareDates(Proced.setFormatDate(headerDataRow.elementAt(5).toString(), false), Environment.currentDate) < 2)  {
                int requestStatus = Integer.parseInt(headerDataRow.elementAt(9).toString());
                switch (requestStatus) {
                    case 15: /* Aprobado */
			ExtendedInternalFrame purchaseOrdersMgmtContainer = new ExtendedInternalFrame("Nueva Orden de Compra (Pedido Nº: " + headerDataRow.elementAt(3) + ")");
			PurchaseOrdersMgmt purchaseOrdersMgmt = new PurchaseOrdersMgmt();
			purchaseOrdersMgmt.setPurchaseOrderGenerateList(this);
			purchaseOrdersMgmt.setIDresourcesRequest(Integer.parseInt(headerDataRow.elementAt(0).toString()));
			purchaseOrdersMgmtContainer.setCentralPanel(purchaseOrdersMgmt);
			purchaseOrdersMgmtContainer.show();
                        break;
                    case 4:
			Advisor.messageBox("Ya se ha generado la Orden de Compra\npara este Pedido de Materiales", "Orden de Compra existente");
                        break;
                    default:
			Advisor.messageBox("El estado del Pedido de Materiales debe ser \"Aprobado\"", "Imposible generar la Orden de Compra");
                        break;
                }
	     } else  {
	         Advisor.messageBox("No es posible generar la Orden de Compra\nLa fecha de liberación es posterior a la fecha de hoy", "Aviso");
	     }
	} else {
	    Advisor.messageBox("Seleccionar un Pedido de Materiales Aprobado", "Error");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void setDetailHeaderList() {
	Vector detailHeaderList = new Vector();
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Material"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Brand"));
	detailHeaderList.addElement(Environment.lang.getProperty("Quantity"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Unit"));
	detailHeaderList.addElement(Environment.lang.getProperty("Price"));
	detailHeaderList.addElement(Environment.lang.getProperty("Cost"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");//Environment.lang.getProperty("Destination"));
	detailHeaderList.addElement("*");//Environment.lang.getProperty("QuantityTo"));
	detailHeaderList.addElement(Environment.lang.getProperty("Observations"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailPanel.setParams("resourcescontrol.getAllResourcesRequestDetailAuth", "-1, 'true'", detailHeaderList);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	tryToGeneratePurchaseOrder();
    }

}
