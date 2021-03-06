package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.CatastroxProblema;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.sql.LibSQL;


public class CatastrosConProblemasMgmt extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private int[] sizeColumnList = {88, 65, 500};
    private Vector cadastralHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel cadastralPanel = new GridPanel(50000, sizeColumnList, "Problemas", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private AssignButton btnAssign = new AssignButton(true);
    private CloseButton btnClose = new CloseButton();
    private DeleteButton btnBorrar = new DeleteButton();
    
    private BasicTextArea taProblema = new BasicTextArea();
    private Cadastral cadastral;
    private BasicPrimitivePanel parentList;
    private BasicRadioButton rbtnProblema = new BasicRadioButton();
    private BasicRadioButton rbtnSolucion = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    private boolean addAction = true;
    private CatastroxProblema catastroxproblema;

    public CatastrosConProblemasMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 448));
	this.setPreferredSize(new Dimension(710, 515));
        cadastralPanel.setBounds(new Rectangle(5, 135, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 450));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 700, 120));
	searchPanel.setLayout(null);
        btnAssign.setBounds(new Rectangle(625, 55, 30, 25));
	btnAssign.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAssign_actionPerformed(e);
				  }

			      }
	);
        
        btnClose.addActionListener(new ActionListener() {

                                  public void actionPerformed(ActionEvent e) {
                                      btnClose_actionPerformed(e);
                                  }

                              }
        );
        
        btnBorrar.addActionListener(new ActionListener() {

                                  public void actionPerformed(ActionEvent e) {
                                      btnBorrar_actionPerformed(e);
                                  }

                              }
        );

        searchPanel.add(btnBorrar, null);
        searchPanel.add(rbtnSolucion, null);
        searchPanel.add(rbtnProblema, null);
        searchPanel.add(taProblema, null);
        searchPanel.add(btnAssign, null);
        contentPanel.add(cadastralPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
        
	cadastralPanel.getTable().setDragEnabled(true);
	cadastralPanel.getTable().setTransferHandler(new TableTransferHandler());

	setCadastralHeader();
        searchPanel.setBorder(BorderPanel.getBorderPanel("B??squeda"));
        searchPanel.setSize(new Dimension(700, 120));
        taProblema.setBounds(new Rectangle(135, 25, 480, 75));
        rbtnProblema.setText("Es Problema");
        rbtnProblema.setBounds(new Rectangle(15, 35, 115, 25));
        rbtnSolucion.setText("Es Soluci??n");
        rbtnSolucion.setBounds(new Rectangle(15, 70, 115, 25));
        btnBorrar.setBounds(new Rectangle(665, 55, 30, 25));
        addButton(btnClose);
        grupo.add(rbtnProblema);
        grupo.add(rbtnSolucion);
        rbtnProblema.setSelected(true);
        btnBorrar.setEnabled(false);
        btnAssign.setToolTipText("Agregar/Modificar");
    }
    
    public void setParentList(BasicPrimitivePanel _parentList) {
        parentList = _parentList;
    }
    
    private void setCadastralHeader() {
	
        cadastralHeader.removeAllElements();
        cadastralHeader.addElement("*"); //idcatastro
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("Fecha");
        cadastralHeader.addElement("Tipo");
        cadastralHeader.addElement("Problema");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
	 
	 cadastralPanel.getTable().addMouseListener(new MouseAdapter() {

	                                          public void mouseClicked(MouseEvent e) {
	                                              loadObjectSelected();
	                                              loadData();
	                                              if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							      
	                                              } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                          //loadUnit();
                                                          
                                                          //loadObjectSelected();
                                                          
	                                              }
	                                          }

	                                      }
	 );
	 String params = "-1";
	 cadastralPanel.setParams("taxes.getCatastroxProblemas", params, cadastralHeader);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {	
	super.setParentInternalFrame(_e);
    }

    public void setCadastral(Cadastral _cadastral) {
        cadastral = _cadastral;
        cadastralPanel.setTitle("Problemas del Catastro N?? "+ cadastral.getCatastro() +"  ("+ cadastral.getDomape() +")");
        refresh();
    }

    public void refresh() {
	String params = ""+ cadastral.getIdCatastro();
	cadastralPanel.refresh(params);
        btnBorrar.setEnabled(false);
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    if (catastroxproblema == null) {
	        catastroxproblema = new CatastroxProblema();
	        
	    }
	    /*else {
	        catastroxproblema.setIdcatastro(Integer.parseInt("" + dataRow.elementAt(0)));
	        catastroxproblema.retrieveData();
	    }*/
	    catastroxproblema.setIdcatastrosxproblemas(Integer.parseInt("" + dataRow.elementAt(0)));
	    catastroxproblema.retrieveData();
	    btnBorrar.setEnabled(true);
	}
    }
    
    private void loadData() {
        taProblema.setText(catastroxproblema.getProblema());
        if ( catastroxproblema.esproblema() ){
            rbtnProblema.setSelected(true);
        } else {
            rbtnSolucion.setSelected(true);
        }
        addAction = false;
    }
    
    private void clearData() {
        taProblema.setText("");
        addAction = true;
        rbtnProblema.setSelected(true);
    }
    
    private void btnAssign_actionPerformed(ActionEvent e) {
        if (addAction) {
            catastroxproblema = new CatastroxProblema();
        }
        if(!taProblema.getText().equals("")){
            catastroxproblema.setIdcatastro(cadastral.getIdCatastro());
            catastroxproblema.setEsproblema(rbtnProblema.isSelected());
            catastroxproblema.setProblema(taProblema.getText());
            if (catastroxproblema.saveData() != -1) {
                //refresh();
                clearData();
                refresh();    
            } else {
                Advisor.messageBox("Ocurri?? un error al guardar los datos", "Error");
            }
        }
        else{
            Advisor.messageBox("Debe ingresar descripci??n del Problema/Soluci??n", "Mensaje");
        }   
    }

    private void btnClose_actionPerformed(ActionEvent e){
        parentList.refresh();
        getParentInternalFrame().close();
    }
    
    private void btnBorrar_actionPerformed(ActionEvent e) {
        if (Advisor.question("Mensaje", "??Confirma la eliminaci??n del item seleccionado?")) {
            String params = ""+ dataRow.elementAt(0) + ","+dataRow.elementAt(1);
            if (LibSQL.getInt("taxes.delProblemaxCatastro", params) > -1) {
                //refresh();
                clearData();
                refresh();    
            } else {
                Advisor.messageBox("Ocurri?? un error al guardar los datos", "Error");
            }    
        }
    }
    
    private void controlBotones() {
        btnBorrar.setEnabled(false);
        clearData();
    }
    
    private void btnVerProblemas_actionPerformed(ActionEvent e) {
        
    }
    
}
