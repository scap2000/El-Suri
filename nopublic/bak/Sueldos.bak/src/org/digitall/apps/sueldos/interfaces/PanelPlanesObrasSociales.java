package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class PanelPlanesObrasSociales extends BasicPrimitivePanel {

    private JPanel jPanel1 = new JPanel();
    private BasicPanel searchPanel = new BasicPanel("Buscar");
    private BorderLayout borderLayout1 = new BorderLayout();

    private int[] sizeColumnList = { 106, 221, 211, 77};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Planes", dataRow);
    private Vector headerList = new Vector();
    
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "SearchButton", false);
    
    private FindButton btnSearch = new FindButton();
    private AddButton btnAddPlan = new AddButton();
    private ModifyButton btnEditPlan = new ModifyButton();
    private DeleteButton btnDeletePlan = new DeleteButton();

    public PanelPlanesObrasSociales() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(660, 354));
	this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        jPanel1.setLayout(borderLayout1);
        searchPanel.add(btnSearch, null);
        searchPanel.add(tfNumber, null);
        jPanel1.add(searchPanel, BorderLayout.NORTH);
        jPanel1.add(listPanel, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.CENTER);
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(1, 70));
        tfNumber.setBounds(new Rectangle(75, 15, 295, 35));
        btnSearch.setBounds(new Rectangle(410, 25, 35, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
	setHeaderList();
        addButton(btnDeletePlan);
        addButton(btnEditPlan);
        addButton(btnAddPlan);
	btnAddPlan.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAddPlan_actionPerformed(e);
				}

			    }
	);
        btnEditPlan.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnEditPlan_actionPerformed(e);
                                }

                            }
        );
        btnDeletePlan.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnDeletePlan_actionPerformed(e);
                                }

                            }
        );
	
        btnAddPlan.setToolTipText("Agregar");
        
        btnAddPlan.setEnabled(false);
        btnEditPlan.setEnabled(false);
        btnDeletePlan.setEnabled(false);
  }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("Código");
	headerList.addElement("Obra Social");
	headerList.addElement("Plan");
	headerList.addElement("$ Importe");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       }
					   }

				       }
	);
	String params = "";
	listPanel.setParams("sueldos.getallplanesobrassociales", params, headerList);
	listPanel.refresh(params);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnAddPlan_actionPerformed(ActionEvent e) {
	
    }

    private void btnEditPlan_actionPerformed(ActionEvent e) {
        
    }
    private void btnDeletePlan_actionPerformed(ActionEvent e) {
        
    }


}
