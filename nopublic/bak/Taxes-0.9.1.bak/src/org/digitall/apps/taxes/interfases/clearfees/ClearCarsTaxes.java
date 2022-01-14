package org.digitall.apps.taxes.interfases.clearfees;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.interfases.carsadmin.CarsMgmt_old;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ClearCarsTaxes extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar");
    private BasicPanel impuestosPanel = new BasicPanel();
    
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private TFInput tfTitular = new TFInput(DataTypes.STRING,"OwnerDomain", false);
    private TFInput tfDNI = new TFInput(DataTypes.STRING, "DNI", false);
    private TFInput tfDominio = new TFInput(DataTypes.STRING, "Domain", false);
    
    private int[] propertiesColumnSize = { 80 , 197 , 81 , 90 , 116 , 83};
    private Vector propertiesDataRow = new Vector();
    private GridPanel propertiesPanel = new GridPanel(50000, propertiesColumnSize, "Automotores", propertiesDataRow);
    private Vector propertiesHeader = new Vector();

    private FindButton btnSearch = new FindButton();
    private DeleteButton btnDeleteCarFees = new DeleteButton();
    private RefreshGridButton btnRecalcCarFees = new RefreshGridButton();
    private PrintButton btnPrintCarFees = new PrintButton();
    private AddButton btnAddCar = new AddButton();
    private ModifyButton btnModifyCar = new ModifyButton();
    
    private CBInput cbCarMonth = new CBInput(0,"TwoMonthly",false);
    private CBInput cbCarYear = new CBInput(0,"FileYear",false);
    
    private int idautomotor = -1;
    
    private BasicPanel carPanel = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private BasicTitleLabel lblCarTitle = new BasicTitleLabel();
    
    private Car car;
    private CarsMgmt_old carsMgmt;

    public ClearCarsTaxes() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 479));
	tfDNI.setBounds(new Rectangle(320, 20, 110, 35));
	tfDominio.setBounds(new Rectangle(475, 20, 125, 35));
        
	tfTitular.setBounds(new Rectangle(50, 20, 220, 35));
	btnSearch.setBounds(new Rectangle(645, 25, 30, 30));
	btnSearch.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }

			     }
	);
        impuestosPanel.setLayout(gridLayout1);
        cbCarMonth.setBounds(new Rectangle(40, 35, 85, 35));
        cbCarMonth.setOpaque(false);
        cbCarYear.setBounds(new Rectangle(140, 35, 100, 35));
        cbCarYear.setOpaque(false);
        carPanel.setLayout(null);
        carPanel.setBackground(new Color(195, 104, 179));
        lblCarTitle.setText("IMPUESTO AUTOMOTOR");
        lblCarTitle.setBounds(new Rectangle(0, 5, 700, 25));
        lblCarTitle.setFont(new Font("Lucida Sans", 1, 15));
        lblCarTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        impuestosPanel.setBounds(new Rectangle(5, 300, 405, 200));
        impuestosPanel.setPreferredSize(new Dimension(405, 80));
        carPanel.add(lblCarTitle, null);
        carPanel.add(cbCarYear, null);
        carPanel.add(cbCarMonth, null);
        carPanel.add(btnDeleteCarFees, null);
        carPanel.add(btnRecalcCarFees,null);
        this.addButton(btnPrintCarFees);
        this.addButton(btnModifyCar);
        this.addButton(btnAddCar);
        cbCarMonth.autoSize();
        cbCarYear.autoSize();
        findPanel.setBounds(new Rectangle(5, 5, 685, 45));
        findPanel.setLayout(null);
        findPanel.setPreferredSize(new Dimension(1, 65));
        propertiesPanel.setBounds(new Rectangle(5, 55, 685, 155));
        propertiesPanel.setPreferredSize(new Dimension(400, 400));
        btnRecalcCarFees.setBounds(new Rectangle(430, 45, 235, 25));
        btnRecalcCarFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnRecalcCarFees_actionPerformed(e);
                }
            }
        );
        btnDeleteCarFees.setBounds(new Rectangle(285, 45, 140, 25));
        btnDeleteCarFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDeleteCarFees_actionPerformed(e);
                }
            }
        );
        content.setLayout(borderLayout4);
        findPanel.add(tfTitular, null);
        findPanel.add(btnSearch, null);
        findPanel.add(tfDominio, null);
        findPanel.add(tfDNI, null);
        this.add(content, null);
        content.add(findPanel, BorderLayout.NORTH);
	content.add(propertiesPanel, BorderLayout.CENTER);
        impuestosPanel.add(carPanel, null);
        content.add(impuestosPanel, BorderLayout.SOUTH);
	tfDominio.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       char caracter = e.getKeyChar();
					       if ((caracter == KeyEvent.VK_ENTER)) {
						   refresh();
					       }
					   }

				       }
	);
	tfTitular.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      char caracter = e.getKeyChar();
							      if ((caracter == KeyEvent.VK_ENTER)) {
								  refresh();
							      }
							  }

						      }
	);
	tfDNI.getTextField().addKeyListener(new KeyAdapter() {

					       public void keyTyped(KeyEvent e) {
						   char caracter = e.getKeyChar();
						   if ((caracter == KeyEvent.VK_ENTER)) {
						       refresh();
						   }
					       }

					   }
	);
        btnPrintCarFees.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnPrintCarFees_actionPerformed(e);
                }
            }
        );
        btnAddCar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddCar_actionPerformed(e);
                }
            }
        );
        btnModifyCar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnModifyCar_actionPerformed(e);
                }
            }
        );
        
        setPropertiesHeader();
        loadCombos();
        setEnabledCombosAndButtons(false);
	btnRecalcCarFees.setToolTipText("<html>Restaurar los anticipos <br>para el vehículo seleccionado</html>");
        btnPrintCarFees.setToolTipText("<html><center>Imprimir los anticipos del Imp. Automotor<br>para el vehículo seleccionado</center></html>");
        btnDeleteCarFees.setText("<html><p align=right>Borrar anticipos</html>");
        btnDeleteCarFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnDeleteCarFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRecalcCarFees.setText("<html><p align=right>Restaurar cuotas</html>");
        btnRecalcCarFees.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnRecalcCarFees.setHorizontalAlignment(SwingConstants.LEFT);
        btnAddCar.setToolTipText("<html><p align=center>Agregar nuevo vehículo</html>");
        btnModifyCar.setToolTipText("<html><p align=center>Modificar datos del\nvehículo seleccionado</html>");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void loadCombos(){
        int actualYear = Integer.parseInt("0" + Environment.currentYear);
        int cont = 0;
        for (int i = 2000 ; i <= (actualYear) ; i++)  {
            cont++;
            cbCarYear.getCombo().addItem(i,cont);
        }
        cbCarYear.setSelectedID(cont);
        cont = 1;
        while (cont <= 6)  {
            int ref = (cont * 2) -1;
            cbCarMonth.getCombo().addItem(cont,cont, ref);
            cont = cont + 1;
        }
    }

    private void setEnabledCombosAndButtons(boolean _valor){
        cbCarMonth.setEnabled(_valor);
        cbCarYear.setEnabled(_valor);
        btnDeleteCarFees.setEnabled(_valor);
        btnRecalcCarFees.setEnabled(_valor);
        btnPrintCarFees.setEnabled(_valor);
        btnModifyCar.setEnabled(_valor);
    }

    private void setPropertiesHeader() {
	propertiesHeader.removeAllElements();
	propertiesHeader.addElement("*"); //iddominio
        propertiesHeader.addElement(Environment.lang.getProperty("Domain"));
	propertiesHeader.addElement(Environment.lang.getProperty("OwnerDomain"));
	propertiesHeader.addElement(Environment.lang.getProperty("DNI"));
        propertiesHeader.addElement("Tipo");
        propertiesHeader.addElement("Marca");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("Ult. Bimestre");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*");
        propertiesHeader.addElement("*"); //tipo dominio
        
	propertiesPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectSelected();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                     
						     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        setEnabledCombosAndButtons(true);
						     }
						 }

					     }
	);
	String params = "'"+ tfTitular.getString() +"','" + tfDNI.getString() + "','" + tfDominio.getString() +"',0";
	propertiesPanel.setParams("taxes.getAllCars", params, propertiesHeader);
    }

    public void refresh() {
	String params = "'"+ tfTitular.getString() +"','" + tfDNI.getString() + "','" + tfDominio.getString() +"',0"; 
	propertiesPanel.refresh(params);
        setEnabledCombosAndButtons(false);
    }

    private void loadObjectSelected() {
	if (!propertiesDataRow.isEmpty()) {
	    idautomotor = Integer.parseInt("" + propertiesDataRow.elementAt(15));
            car = new Car();
            car.setIddominio(Integer.parseInt("" + propertiesDataRow.elementAt(0)));
            car.setDominio("" + propertiesDataRow.elementAt(1));
	    car.retrieveData();
        }
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnDeleteCarFees_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso!","¿Está seguro de borrar los anticipos?")) {
            String params = idautomotor + ","+ cbCarMonth.getSelectedValueRef() +","+ cbCarYear.getSelectedItem();
            if (LibSQL.getInt("taxes.deleteCarsFees",params) > 0) {
                refresh();
            }
        }
    }

    private void btnRecalcCarFees_actionPerformed(ActionEvent e) {
            if (Advisor.question("Aviso!","¿Está seguro de regenerar los anticipos\npara el automotor seleccionado?")) {
                if (LibSQL.getInt("taxes.generarCuotaAutomotor",idautomotor) == 0) {
                    refresh();
                }
            }
    }
    
    private void btnPrintCarFees_actionPerformed(ActionEvent e) {
        if (!propertiesDataRow.isEmpty()) {
            int iddominio = Integer.parseInt(propertiesDataRow.elementAt(0).toString());
            BasicReport report = new BasicReport(ClearCarsTaxes.class.getResource("xml/CarFeesReport.xml"));
            report.addTableModel("taxes.xmlGetCar", iddominio);
            report.addTableModel("taxes.xmlGetCarFees", idautomotor);
            report.doReport();
        } else {
            Advisor.messageBox("Debe seleccionar una Empresa", "Error");
        }
    }
    
    private void btnAddCar_actionPerformed(ActionEvent e) {
        loadMgmt(true);
    }
    
    private void btnModifyCar_actionPerformed(ActionEvent e) {
        loadMgmt(false);
    }
    
    private void loadMgmt(boolean _addAction){
        if (_addAction){
            car = new Car();
        }
        carsMgmt = new CarsMgmt_old();
        carsMgmt.setCar(car);
        carsMgmt.setParentList(this);
        
        ExtendedInternalFrame carsMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
        carsMgmtContainer.setCentralPanel(carsMgmt);
        carsMgmtContainer.show();
	carsMgmt.load();
    }
    
}
