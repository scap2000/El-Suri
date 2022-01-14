package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.Timer;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class CadastralList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();
    private TFInput tfPersonName = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfIdentificationNumber = new TFInput(DataTypes.INTEGER, "DNI",false);
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral",false);
    
    private int[] sizeColumnList = {60, 230, 70, 50, 155, 50, 170,60};
    private Vector cadastralHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel cadastralPanel = new GridPanel(50000, sizeColumnList, "Listado de Catastros", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    private ModifyButton btnAddOwner = new ModifyButton();
    private ModifyButton btnVerProblemas = new ModifyButton();
    private DeleteButton btnBajaCatastro = new DeleteButton();
    private AssignButton btnDesvincularTitulares = new AssignButton(true);

    private Cadastral cadastral;
    
    private final int addCadastral = 1;
    private final int addOwner = 2;
    private final int editCadastral = 3;
    private Color alertColor = Color.red;
    private BasicPanel panelAlerta;
    private Timer alertTimer;

    public CadastralList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        panelAlerta = new BasicPanel() {
    
            @Override
            public void paint(Graphics _graphics) {
                _graphics.setColor(alertColor);
                _graphics.fillOval(8, 0, 30, 30);
                _graphics.setColor(Color.black);
                _graphics.drawOval(8, 0, 30, 30);
                super.paintComponents(_graphics);
            }
        };
        alertTimer = new Timer(400, new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                alertColor = (alertColor==Color.red?Color.yellow:Color.red);
                panelAlerta.repaint();
                panelAlerta.getParent().repaint();
            }
            
        });
	//this.setLayout(null);
	this.setSize(new Dimension(710, 448));
	this.setPreferredSize(new Dimension(710, 515));
	tfPersonName.setBounds(new Rectangle(65, 15, 220, 35));
	cadastralPanel.setBounds(new Rectangle(5, 70, 700, 335));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 450));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 5, 700, 60));
	searchPanel.setLayout(null);
	tfIdentificationNumber.setBounds(new Rectangle(330, 15, 120, 35));
	tfCadastral.setBounds(new Rectangle(505, 15, 110, 35));
	btnFind.setBounds(new Rectangle(655, 20, 30, 25));
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
	/*btnAddCadastralByYear.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAddCadastralByYear_actionPerformed(e);
			      }

			  }
	);
	btnAddCadastralByYear.setText("Administrar Catastros\npor año");
	btnAddInterestedByYear.setText("Administrar Intereses\npor año");
	btnAddInterestedByYear.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAddInterestedByYear_actionPerformed(e);
			      }

			  }
	);*/
        btnAddOwner.addActionListener(new ActionListener() {

                              public void actionPerformed(ActionEvent e) {
                                  btnAddOwner_actionPerformed(e);
                              }

                          }
        );
        btnVerProblemas.addActionListener(new ActionListener() {

                              public void actionPerformed(ActionEvent e) {
                                  btnVerProblemas_actionPerformed(e);
                              }

                          }
        );
        btnBajaCatastro.addActionListener(new ActionListener() {

                              public void actionPerformed(ActionEvent e) {
                                  btnBajaCatastro_actionPerformed(e);
                              }

                          }
        );
        btnBajaCatastro.setToolTipText("Dar de Baja al Catastro seleccionado");
        
        btnDesvincularTitulares.addActionListener(new ActionListener() {

                              public void actionPerformed(ActionEvent e) {
                                  btnDesvincularTitulares_actionPerformed(e);
                              }

                          }
        );
        btnDesvincularTitulares.setToolTipText("Desvincular Titulares");
        searchPanel.add(panelAlerta, null);
        searchPanel.add(tfCadastral, null);
        searchPanel.add(tfIdentificationNumber, null);
        searchPanel.add(tfPersonName, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(cadastralPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, BorderLayout.CENTER);
        this.addButton(btnVerProblemas);
        this.addButton(btnAddOwner);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
        this.addButton(btnDesvincularTitulares);
        this.addButton(btnBajaCatastro);
        
	cadastralPanel.getTable().setDragEnabled(true);
	cadastralPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfPersonName.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	
	tfIdentificationNumber.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});

	tfCadastral.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});	
        btnVerProblemas.setToolTipText("Ver problemas del Catastro seleccionado");
        btnAddOwner.setToolTipText("Agregar Titular al Catastro seleccionado");
        btnEdit.setToolTipText("Modificar datos del Catastro seleccionado");
	setCadastralHeader();
	btnEdit.setEnabled(false);
        btnVerProblemas.setEnabled(false);
	btnAddOwner.setEnabled(false);
        btnBajaCatastro.setEnabled(false);
        btnDesvincularTitulares.setEnabled(false);
        searchPanel.setBorder(BorderPanel.getBorderPanel("Buscar"));
        searchPanel.setSize(new Dimension(700, 60));
        panelAlerta.setBounds(new Rectangle(10, 20, 45, 35));
        panelAlerta.setVisible(false);
    }
    
    private void setCadastralHeader() {
	
        cadastralHeader.removeAllElements();
        cadastralHeader.addElement("*"); //idcatastro
        cadastralHeader.addElement("Catastro");
        cadastralHeader.addElement(Environment.lang.getProperty("Name"));
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");
        cadastralHeader.addElement(Environment.lang.getProperty("DNI"));
        cadastralHeader.addElement("*");
        cadastralHeader.addElement(Environment.lang.getProperty("%"));
        cadastralHeader.addElement("*");
        
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement(Environment.lang.getProperty("Street"));
        cadastralHeader.addElement(Environment.lang.getProperty("Number"));
        cadastralHeader.addElement(Environment.lang.getProperty("Neighborhood"));
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
        cadastralHeader.addElement("*");cadastralHeader.addElement("*");
	cadastralHeader.addElement("Es Rural");
	 
	 cadastralPanel.getTable().addMouseListener(new MouseAdapter() {
                                                    
	                                          public void mouseClicked(MouseEvent e) {
	                                              loadObjectSelected();
	                                              if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							      loadMgmt(editCadastral);
	                                              } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
	                                                  btnVerProblemas.setEnabled(true);
	                                                  btnBajaCatastro.setEnabled(true);
	                                                  btnDesvincularTitulares.setEnabled(true);
	                                              }
	                                          }

	                                      }
	 );
	 String params = "'" + tfPersonName.getString() + "','" + tfIdentificationNumber.getString() + "',0" + tfCadastral.getInteger();
	 cadastralPanel.setParams("taxes.getAllCatastros", params, cadastralHeader);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {	
	super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Administración de Catastros");
    }

    
    public void refresh() {
	String params = "'"+ tfPersonName.getString() +"','"+ tfIdentificationNumber.getString() +"',0"+ tfCadastral.getInteger();
	cadastralPanel.refresh(params);
        btnVerProblemas.setEnabled(false);
        btnBajaCatastro.setEnabled(false);
        btnDesvincularTitulares.setEnabled(false);
	btnAddOwner.setEnabled(false);
	btnEdit.setEnabled(false);
        panelAlerta.setVisible(false);
        alertTimer.stop();
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    cadastral = new Cadastral();
	    cadastral.setIdCatastro(Integer.parseInt(dataRow.elementAt(0).toString()));
	    cadastral.setDomnudoc(dataRow.elementAt(9).toString());
	    cadastral.retrieveData();
	    btnAddOwner.setEnabled(true);
	    btnEdit.setEnabled(true);
	    btnVerProblemas.setEnabled(true);
	    btnBajaCatastro.setEnabled(true);
	    btnDesvincularTitulares.setEnabled(true);
            panelAlerta.setVisible(false);
            alertTimer.stop();
            if (cadastral.isConProblema()) {
                panelAlerta.setVisible(true);
                alertTimer.start();
            }
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(int _Action){
        if (_Action == addCadastral){
	    cadastral = new Cadastral();
        } else{
	    loadObjectSelected();    
	}
        CadastralMgmt cadastralMgmt = new CadastralMgmt();
        cadastralMgmt.setAction(_Action);
	cadastralMgmt.setCadastral(cadastral);
	cadastralMgmt.setParentList(this);
	
	ExtendedInternalFrame cadastralMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	cadastralMgmtContainer.setCentralPanel(cadastralMgmt);
	cadastralMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(addCadastral);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(editCadastral);
    }
    private void btnAddOwner_actionPerformed(ActionEvent e) {
        loadMgmt(addOwner);
    }
    
    private void controlBotones(){
	btnEdit.setEnabled(false);
	btnAddOwner.setEnabled(false);
        btnVerProblemas.setEnabled(false);
        btnBajaCatastro.setEnabled(false);
        btnDesvincularTitulares.setEnabled(false);
    }
    
    private void btnVerProblemas_actionPerformed(ActionEvent e) {
        CatastrosConProblemasMgmt catastrosConProblemasMgmt = new CatastrosConProblemasMgmt();
        catastrosConProblemasMgmt.setCadastral(cadastral);
        catastrosConProblemasMgmt.setParentList(this);
        
        ExtendedInternalFrame catastrosConProblemasMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar Problemas");
        catastrosConProblemasMgmtContainer.setCentralPanel(catastrosConProblemasMgmt);
        catastrosConProblemasMgmtContainer.show();
    }
    
    private void btnBajaCatastro_actionPerformed(ActionEvent e) {
        darBaja();
    }
    
    private void btnDesvincularTitulares_actionPerformed(ActionEvent e) {
        desvincular();
    }
    
    private void desvincular(){
        if (LibSQL.getInt("taxes.getCantidadTitulares", cadastral.getIdCatastro()) > 1 ) {
          PanelDesvincularTitulares panelDesvinculacion = new PanelDesvincularTitulares(cadastral, this);
          ExtendedInternalFrame container = new ExtendedInternalFrame("Desvincular Titulares");
          container.setCentralPanel(panelDesvinculacion);
          container.show();
        } else {
            Advisor.messageBox("No puede desvincular titulares al catastro " +  cadastral.getCatastro() + "\n por que tiene un solo titular", "Aviso");
        }
    }
    
    private void darBaja(){
        PanelBaja panelBaja = new PanelBaja(cadastral, this);
        ExtendedInternalFrame container = new ExtendedInternalFrame("Baja de Catastro");
        container.setCentralPanel(panelBaja);
        container.show();
    }
    
}
