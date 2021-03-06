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

import org.digitall.apps.sueldos.classes.Conceptos;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ConceptosList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name",false);

    private int[] sizeColumnList = {227, 112, 136, 235};
    private Vector conceptosHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel conceptoslPanel = new GridPanel(50000, sizeColumnList, "Listado de Conceptos", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    private CloseButton btnClose = new CloseButton();
    private CBInput cbTipos = new CBInput(0,"Tipo");
    
    private Conceptos concepto;

    public ConceptosList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public ConceptosList(Conceptos _concepto) {
        try {
            concepto = _concepto;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        cbTipos.setBounds(new Rectangle(340, 10, 300, 35));
        cbTipos.setPreferredSize(new Dimension(250, 50));
        cbTipos.autoSize();
        cbTipos.setGeneralItem(true);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfNombre.setBounds(new Rectangle(15, 10, 300, 35));
	conceptoslPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
        btnFind.setBounds(new Rectangle(660, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAdd.setBounds(new Rectangle(560, 525, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        searchPanel.add(tfNombre, null);
	searchPanel.add(btnFind, null);
        searchPanel.add(cbTipos, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
        btnEdit.setToolTipText("Modificar Concepto");
        btnEdit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnEdit_actionPerformed(e);
                }

            });
        btnClose.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnClose_actionPerformed(e);
                }

            });
        contentPanel.add(conceptoslPanel, BorderLayout.CENTER);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        addButton(btnClose); 
	addButton(btnEdit);
	addButton(btnAdd); 
	
	conceptoslPanel.getTable().setDragEnabled(true);
	conceptoslPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEdit.setEnabled(false);
        setConceptosHeader();
        loadComboTipos();
    }
    
    private void loadComboTipos(){
        cbTipos.loadJCombo("tabs.getAllTiposConceptos","" );
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    

    public void refresh() {
        String params = "'" + tfNombre.getString() + "'," + Integer.parseInt(cbTipos.getSelectedValue().toString());
	conceptoslPanel.refresh(params);
	btnEdit.setEnabled(false);
    }

    private void setConceptosHeader() {
	conceptosHeader.removeAllElements();
	conceptosHeader.addElement("*");    //idconcepto
	conceptosHeader.addElement("Concepto");
	conceptosHeader.addElement("Tipo");
        conceptosHeader.addElement("Haber/Descuento");
	conceptosHeader.addElement("Cuenta");
	conceptosHeader.addElement("*");    //orden
	conceptoslPanel.getTable().addMouseListener(new MouseAdapter() { 
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    loadMgmt(false);
			}
		    }
		});
		
	String params = "'',-1" ;
		conceptoslPanel.setParams("sueldos.getallconceptos", params, conceptosHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    concepto = new Conceptos();
            int id = Integer.parseInt(dataRow.elementAt(0).toString());
            concepto.setIdconcepto(id);
	    concepto.retrieveData();
	    btnEdit.setEnabled(true);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction) {
	    concepto = new Conceptos();
	}
	ConceptosMgmt conceptosMgmt = new ConceptosMgmt(concepto);
	conceptosMgmt.setParentList(this);
	ExtendedInternalFrame conceptosMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	conceptosMgmtContainer.setCentralPanel(conceptosMgmt);
	conceptosMgmtContainer.show();
	conceptosMgmt.load();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadObjectSelected();
	loadMgmt(false);
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
        getParentInternalFrame().setIcon(true);
    }

    private void controlBotones(){
	btnEdit.setEnabled(false);
    }
    
}
