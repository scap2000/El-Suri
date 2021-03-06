package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcesrequests.classes.errclasses.ErrResourcesRequestsApproveMgmt;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesRequestsApproveMgmt extends BasicPrimitivePanel {

    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel panelSur = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    
    private int[] sizeColumnList = { 72, 88, 164, 183, 97, 163 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Pedidos de Materiales Autorizados", dataRow) {

	@Override
	public void finishLoading() {
	    detailListPanel.refresh(-1);
	}
    };

    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE, "Fecha inicio", false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE, "Fecha fin", false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    private int[] sizeColumnDetailList = {310, 135, 105, 101, 75};
    private Vector detailDataRow = new Vector();
    private GridPanel detailListPanel = new GridPanel(50000, sizeColumnDetailList, "Detalle", detailDataRow);
    
    private AcceptButton btnApprove = new AcceptButton();
    private DeleteButton btnDelete = new DeleteButton();
    private FindButton btnFind = new FindButton();

    private TFInput tfLiberationDate = new TFInput(DataTypes.SIMPLEDATE,"Liberaci??n",true);
    private BasicLabel lblInfo = new BasicLabel();

    public ResourcesRequestsApproveMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(780, 512));
	this.setPreferredSize(new Dimension(780, 512));

        listPanel.setPreferredSize(new Dimension(400, 200));
        detailListPanel.setSize(new Dimension(780, 200));
        detailListPanel.setPreferredSize(new Dimension(400, 178));
        
	panelNorte.setBounds(new Rectangle(0, 0, 750, 70));
	panelNorte.setPreferredSize(new Dimension(1, 70));
	panelNorte.setSize(new Dimension(780, 70));
        panelNorte.add(btnFind, null);
        panelNorte.add(cbCostsCentre, null);
        panelNorte.add(tfStartDate, null);
        panelNorte.add(tfEndDate, null);
        panelNorte.setLayout(null);

        panelCentro.setLayout(new BorderLayout());
        panelCentro.add(listPanel, BorderLayout.CENTER);
        panelCentro.add(detailListPanel, BorderLayout.SOUTH);

        panelSur.setBounds(new Rectangle(0, 0, 780, 60));
        panelSur.setPreferredSize(new Dimension(1, 60));
        panelSur.setSize(new Dimension(780, 60));
	panelSur.add(lblInfo, null);
	panelSur.add(btnApprove);
	panelSur.add(btnDelete);
	panelSur.add(tfLiberationDate, null);
	panelSur.setLayout(null);

        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBounds(new Rectangle(0, 0, 780, 600));
        panelContenedor.setSize(new Dimension(780, 600));
        panelContenedor.setPreferredSize(new Dimension(780, 600));
        panelContenedor.add(panelNorte, BorderLayout.NORTH);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);
        panelContenedor.add(panelSur, BorderLayout.SOUTH);
        
        this.setContent(panelContenedor);
	cbCostsCentre.setBounds(new Rectangle(435, 20, 245, 35));
	btnFind.setBounds(new Rectangle(695, 30, 30, 25));
	btnFind.setSize(new Dimension(30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

        }  
	);
	lblInfo.setBounds(new Rectangle(10, 30, 585, 20));
        cbCostsCentre.autoSize();
        cbCostsCentre.getCombo().addItem("Todos", "-1");
        cbCostsCentre.setSelectedID("-1");

	btnDelete.setEnabled(false);
	btnApprove.setEnabled(false);
	btnApprove.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnApprove_actionPerformed(e);
					}

				    }
	);
	btnApprove.setToolTipText("Aprobar Pedido(s) de Materiales seleccionado(s)");
        btnApprove.setBounds(new Rectangle(690, 30, 30, 20));

	btnDelete.setToolTipText("Rechazar Pedido(s) de Materiales seleccionado(s)");
	btnDelete.setBounds(new Rectangle(740, 30, 30, 20));

	btnDelete.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnDelete_actionPerformed(e);
		}
	    });
	tfStartDate.setBounds(new Rectangle(135, 20, 95, 35));
	tfEndDate.setBounds(new Rectangle(280, 20, 95, 35));
	tfStartDate.setFirstDayOfYear();
	tfEndDate.setToday();
        panelNorte.setBorder(BorderPanel.getBorderPanel("Buscar"));
        lblInfo.setText("Recuerde que s??lo se podr?? generar la Orden de Compra desde la fecha de liberaci??n");
	lblInfo.setBackground(new Color(255, 132, 0));
	lblInfo.setOpaque(true);
	lblInfo.setForeground(Color.black);
	lblInfo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblInfo.setFont(new Font("Lucida Sans", 1, 12));
	lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
	panelSur.setBorder(BorderPanel.getBorderPanel("Asignar Fecha de Liberaci??n"));
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    loadSelectedObject();
		    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    }
		}

	    });
	tfLiberationDate.setBounds(new Rectangle(600, 15, 90, 35));
	tfLiberationDate.setToday();
	setHeaderList();
	setDetailList();
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("RequestNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Solicitor"));
        headerList.addElement(Environment.lang.getProperty("CostsCentre"));
        headerList.addElement("*");//Environment.lang.getProperty("Status"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("($) Estimado"));
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Motivo del pedido"));
        headerList.addElement("*");
        headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	listPanel.setParams("resourcescontrol.getAllResourcesRequestsAuthorized", "-1,'','',''", headerList);
    }

    public void refresh() {
	String params = cbCostsCentre.getSelectedValue() + ",''," + tfStartDate.getDateForSQL() + "," + tfEndDate.getDateForSQL();
	listPanel.refresh(params);
    }

    private boolean control() {
        boolean _returns = true;
        if (!tfStartDate.getDate().equals("") && !tfEndDate.getDate().equals("")) {
            if ( (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false), Proced.setFormatDate(tfEndDate.getDate(),false))) == 2 ) {
                ErrResourcesRequestsApproveMgmt.showErrorMessage(ErrResourcesRequestsApproveMgmt.TFCOMPAREDATESMINOR);
                _returns = false;  
	    }
	}
        return _returns;
    }

    private void loadSelectedObject() {
	btnApprove.setEnabled(false);
	btnDelete.setEnabled(false);
	if (!dataRow.isEmpty()) {
	    detailListPanel.refresh(dataRow.elementAt(0));
	    btnApprove.setEnabled(true);
	    btnDelete.setEnabled(true);
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	if (control()) {
	    refresh();
	}
    }
    
    private void btnApprove_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    refresh();
	}
    }
    
    @Override
    public boolean saveData() {
	boolean _returns = false;
	String _selectedIDS = "";
	if (listPanel.getSelectedsIDSeparatedByComma().length() > 0) {
	    if (Advisor.question("Aprobar Pedido(s) de Materiales", "??Est?? seguro de aprobar los Pedido(s) seleccionado(s)\ny liberarlos en la fecha " +
				tfLiberationDate.getDate() + "?")) {
	        _selectedIDS = listPanel.getSelectedsIDSeparatedByComma();
	    }
	} else if (!dataRow.isEmpty()) {
	    if (Advisor.question("Aprobar Pedido de Materiales", 
				"??Est?? seguro de aprobar el Pedido seleccionado\ny liberarlo en la fecha " +
				tfLiberationDate.getDate() + "?")) {
		_selectedIDS = dataRow.elementAt(0).toString();
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar alg??n Pedido de Materiales", "Aviso");
	}
	if (_selectedIDS.length() > 0) {
	    if (LibSQL.getInt("resourcescontrol.approveResourcesRequestsAuth", "'" + _selectedIDS + "'," + tfLiberationDate.getDateForSQL()) >= 0)  {
	        _returns = true;
	    } else {
	        Advisor.messageBox("Ocurri?? un error al grabar los datos","Error");
	    }
	}
	return _returns;
    }
    
    private void setDetailList() {
	Vector detailList = new Vector();
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Material"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Brand"));
	detailList.addElement(Environment.lang.getProperty("Quantity"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Unit"));
        detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Costo"));
        detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
        detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
        detailList.addElement("*");
        detailListPanel.setParams("resourcescontrol.getAllResourcesRequestDetailAuthorized", "-1", detailList);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione uno o varios Pedidos, asigne su Fecha de Liberaci??n y presione el bot??n Aceptar");
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	String _selectedIDS = "";
	if (listPanel.getSelectedsIDSeparatedByComma().length() > 0) {
	    if (Advisor.question("Rechazar Pedido(s) de Materiales", "??Est?? seguro de rechazar los Pedido(s) seleccionado(s)?")) {
		_selectedIDS = listPanel.getSelectedsIDSeparatedByComma();
	    }
	} else if (!dataRow.isEmpty()) {
	    if (Advisor.question("Rechazar Pedido de Materiales", "??Est?? seguro de rechazar el Pedido seleccionado?")) {
		_selectedIDS = dataRow.elementAt(0).toString();
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar alg??n Pedido de Materiales", "Aviso");
	}
	if (_selectedIDS.length() > 0) {
	    if (LibSQL.getBoolean("resourcescontrol.overrideResourcesRequests", "'" + _selectedIDS + "'")) {
		refresh();
	    } else {
		Advisor.messageBox("Ocurri?? un error al grabar los datos", "Error");
	    }
	}
    }
}
