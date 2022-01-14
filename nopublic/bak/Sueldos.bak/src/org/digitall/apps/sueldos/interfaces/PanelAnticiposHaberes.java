package org.digitall.apps.sueldos.interfaces;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Types;
import java.util.Vector;
import javax.swing.JPanel;

import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PanelAnticiposHaberes extends BasicPrimitivePanel {

    private JPanel jPanel1 = new JPanel();
    private BasicPanel searchPanel = new BasicPanel("Buscar");
    private BorderLayout borderLayout1 = new BorderLayout();

    private int[] sizeColumnList = { 65, 253, 155, 119};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Anticipos de Haberes", dataRow);
    private Vector headerList = new Vector();
    
    private TFInput tfSearch = new TFInput(DataTypes.STRING, "Empleado", false);
    private BasicLabel lblPeriodo = new BasicLabel();
    private String periodoLiquidacion = "";
    private FindButton btnSearch = new FindButton();
    private PrintButton btnPrint = new PrintButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    public PanelAnticiposHaberes() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(660, 418));
	this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        jPanel1.setLayout(borderLayout1);
	searchPanel.add(btnSearch, null);
	searchPanel.add(tfSearch, null);
        searchPanel.add(lblPeriodo, null);
        jPanel1.add(searchPanel, BorderLayout.NORTH);
        jPanel1.add(listPanel, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.CENTER);
        searchPanel.setLayout(null);
        searchPanel.setPreferredSize(new Dimension(1, 70));
        tfSearch.setBounds(new Rectangle(10, 20, 295, 35));
        lblPeriodo.setBounds(new Rectangle(345, 25, 310, 30));
        tfSearch.getTextField().addKeyListener(new KeyAdapter() {
					    public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
						    buscar();
					    }
					}
	);      
        btnSearch.setBounds(new Rectangle(310, 30, 35, 30));
	btnSearch.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
				     btnSearch_actionPerformed(e);
				 }
			     }
	);
        addButton(btnClose);
        addButton(btnSave);
        addButton(btnPrint);
        btnPrint.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnPrint_actionPerformed(e);
                                }
                            }
        );
        btnSave.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnSave_actionPerformed(e);
                                }
                            }
        );
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }
                            }
        );
        btnSave.setToolTipText("Grabar Descuentos");
        btnPrint.setToolTipText("Imprimir Listado de Anticipos de Haberes");
        btnSave.setEnabled(true);
        btnClose.setEnabled(true);
	listPanel.setCellEditor(Types.DOUBLE,3);
        periodoLiquidacion = "Período de Liquidación: " + Environment.lblMonthName.getText() + " " + Environment.currentYear;
        lblPeriodo.setText(periodoLiquidacion);
        lblPeriodo.setFont(new Font("Lucida Sans", 1, 14));
        setheaderList();
        refresh();
    }
    
    private void setheaderList() {
        headerList.removeAllElements();
        headerList.addElement("*"); //idlegajo
        headerList.addElement("*"); //idperson
        headerList.addElement("Legajo"); //numero legajo
        headerList.addElement("Apellido y Nombres");//apellido y nombres
        headerList.addElement("($) Anticipos Haberes");//saldo anticipos de haberes
        headerList.addElement("($) A Descontar"); //A descontar
        
        listPanel.setParams("sueldos.getallAnticiposDeHaberesADescontar", tfSearch.getStringForSQL(), headerList);
    }
    
    public void refresh() {
        listPanel.refresh(tfSearch.getStringForSQL());
    }

    private void btnSearch_actionPerformed(ActionEvent e) {
	buscar();
    }
    
    private void buscar(){
        refresh();
    }

    private void btnSave_actionPerformed(ActionEvent e) {
        if (control()) {
            save();
        }    
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
        BasicReport report = new BasicReport(LiquidacionSueldosMain.class.getResource("xml/ListadoAnticiposHaberes.xml"));
        report.addTableModel("sueldos.xmlGetAnticiposHaberes", "");
        report.setProperty("periodo", periodoLiquidacion);
        report.doReport();
    }
    
    public boolean control(){
        boolean result = true;
        double saldoAnticipoHaberes = 0.0;
        double aDescontar = 0.0;
        int i = 0;
        while (result && i < listPanel.getAllIDs().size()){
            saldoAnticipoHaberes = Double.parseDouble((listPanel.getValuesAt(4)).elementAt(i).toString());
            aDescontar = Double.parseDouble((listPanel.getValuesAt(5)).elementAt(i).toString());
            i++;
            if (aDescontar > saldoAnticipoHaberes) {
                result = false;
            }    
        }
        
        if (!result) {
            Advisor.messageBox("No puede descontar anticipos de haberes por un monto mayor al mismo", "Error");
        }
        return result;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().setIcon(true);
    }
    
    private void save(){
        if (Advisor.question("Pregunta", "Está seguro de registrar el anticipo de haberes")) {
            if (LibSQL.getBoolean("sueldos.grabarDescuentosHaberes", "'" + listPanel.getAllIDs().toString().replace("[", "{").replace("]", "}") + "',"
                                                                          + "'"+ listPanel.getValuesAt(5).toString().replace("[", "{").replace("]", "}") +"',"
                                                                          + listPanel.getAllIDs().size())) {
                Advisor.messageBox("Se grabó con éxito el descuento de haberes","Éxito");
                refresh();
            } else {
                Advisor.messageBox("Se produjo un error al grabar los datos","Error");
            }
        }
    }
}

