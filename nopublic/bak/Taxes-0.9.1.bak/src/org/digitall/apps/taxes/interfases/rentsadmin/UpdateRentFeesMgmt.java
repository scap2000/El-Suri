package org.digitall.apps.taxes.interfases.rentsadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Types;

import java.util.Vector;

import org.digitall.apps.taxes.classes.Rent;
import org.digitall.apps.taxes.interfases.TaxesRent;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.sql.LibSQL;

public class UpdateRentFeesMgmt extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfEmpresa = new TFInput(DataTypes.STRING, "Enterprise",false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING, "PersonCharge",false);
    private TFInput tfPredio = new TFInput(DataTypes.STRING, "Predio",false);

    private int[] sizeColumnList = { 28, 37 , 80 , 82 , 44 , 57 , 57 , 57 , 57 , 80 , 52 , 52 };
    private Vector tgsFeesHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel tgsGridPanel = new GridPanel(100, sizeColumnList, "Listado de Anticipos", dataRow);
    private SaveDataButton btnSaveData = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    private Rent rent;
    private TaxesRent parentMain;

    public UpdateRentFeesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfEmpresa.setBounds(new Rectangle(50, 10, 235, 35));
	tgsGridPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
	tfPredio.setBounds(new Rectangle(475, 10, 140, 35));
        
	btnClose.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnClose_actionPerformed(e);
			      }
			  }
	);
	
	btnSaveData.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnSaveData_actionPerformed(e);
				}

			    }
	);
	tfResponsable.setBounds(new Rectangle(310, 10, 140, 35));
        searchPanel.add(tfResponsable, null);
        searchPanel.add(tfPredio, null);
	searchPanel.add(tfEmpresa, null);
	
	contentPanel.add(tgsGridPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	//this.addButton(btnAdd);
        tgsGridPanel.setCellEditor(Types.DOUBLE, 4);
        
        tgsGridPanel.getTable().addKeyListener(new KeyAdapter() {

                 public void keyReleased(KeyEvent e) {
                     
                     double basicAmount = Double.parseDouble(tgsGridPanel.getTable().getValueAt(tgsGridPanel.getTable().getSelectedRow(), 5).toString());
                     if (basicAmount > 0) {
                         double mora = (basicAmount * Double.parseDouble(tgsGridPanel.getTable().getValueAt(tgsGridPanel.getTable().getSelectedRow(), 6).toString())) / 100;
                         double descuento = (basicAmount * Double.parseDouble(tgsGridPanel.getTable().getValueAt(tgsGridPanel.getTable().getSelectedRow(), 8).toString())) / 100;
                         double totalAmount = (basicAmount + mora - descuento) * (1 - (Double.parseDouble(tgsGridPanel.getTable().getValueAt(tgsGridPanel.getTable().getSelectedRow(), 12).toString()) / 100));
                         tgsGridPanel.getTable().setValueAt(String.valueOf(Format.toDouble(mora)), tgsGridPanel.getTable().getSelectedRow(), 7);
                         tgsGridPanel.getTable().setValueAt(String.valueOf(Format.toDouble(descuento)), tgsGridPanel.getTable().getSelectedRow(), 9);
                         tgsGridPanel.getTable().setValueAt(String.valueOf(Format.toDouble(totalAmount)), tgsGridPanel.getTable().getSelectedRow(), 11);
                     } else { 
                        Advisor.messageBox("No está permitido ingresar valores menores o igual a cero", "Error");
                     }
                 }
             }
        );
              

	tfEmpresa.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfPredio.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        tfEmpresa.setEditable(false);
        tfResponsable.setEditable(false);
        tfPredio.setEditable(false);
	setCadastralHeader();
        addButton(btnClose);
	addButton(btnSaveData);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	String params = "'"+ tfEmpresa.getString().trim() +"','"+ tfResponsable.getString().trim() +"','"+ tfPredio.getString().trim() +"'";
	tgsGridPanel.refresh(params);
    }

    private void setCadastralHeader() {
        tgsFeesHeader.removeAllElements();
        tgsFeesHeader.addElement("*");
        tgsFeesHeader.addElement("Nº");
        tgsFeesHeader.addElement("Año");
        tgsFeesHeader.addElement("Fecha");
        tgsFeesHeader.addElement("Vence");
        tgsFeesHeader.addElement("Valor");
        tgsFeesHeader.addElement("% Mora");
        tgsFeesHeader.addElement("$ Mora");
        tgsFeesHeader.addElement("% Desc.");
        tgsFeesHeader.addElement("$ Desc.");
        tgsFeesHeader.addElement("Fº Act.");
        tgsFeesHeader.addElement("$ Total");
        tgsFeesHeader.addElement("% Pagado");
        tgsFeesHeader.addElement("*");
        
        tgsGridPanel.getTable().addMouseListener(new MouseAdapter() {

                                                 public void mouseClicked(MouseEvent e) {

                                                     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                         
                                                     } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                        
                                                     }
                                                 }

                                             }
        );
        
        tgsGridPanel.setParams("taxes.getCuotasAlquiler", "-1", tgsFeesHeader);
    }

    private void getCuotas() {
        int resul = LibSQL.getInt("taxes.setRentFees",rent.getIdempresa());
        tgsGridPanel.refresh("" + rent.getIdempresa());
        tgsGridPanel.calculate();
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
        if (Advisor.question("Aviso","¿Esta seguro de actualizar las cuotas?")) {
            tgsGridPanel.selectAllItems(true);
            Vector aux = tgsGridPanel.getSelectedsVector();
            String params = "";
            boolean ok = true;
            for (int i = 0; i < aux.size(); i++) {
                Vector vec = (Vector)aux.elementAt(i);
                int anticipo = Integer.parseInt(vec.get(1).toString());
                int anio = Integer.parseInt(vec.get(2).toString());
                Double montoBase = Double.parseDouble(vec.get(5).toString());
                Double mora = Double.parseDouble(vec.get(7).toString());
                Double descuento = Double.parseDouble(vec.get(9).toString());
                Double montoTotal = Double.parseDouble(vec.get(11).toString());
                params = rent.getIdempresa() + "," + anticipo + "," + anio + "," + montoBase + "," + mora + "," + descuento + "," + montoTotal;
                if (LibSQL.getInt("taxes.updateRentFees", params) <= 0) {
                    ok = false;
                }
            }
            if (ok) { 
                parentMain.getCuotas();
                getParentInternalFrame().close();
            } else {
                Advisor.messageBox("Ocurrió un error al actualizar los datos", "Error");
            }
        }
    }

    private void btnClose_actionPerformed(ActionEvent e) {
         getParentInternalFrame().close();
     }

    public void setParentMain(TaxesRent _parentMain) {
        parentMain = _parentMain;
    }

    public void setRent(Rent _rent) {
        rent = _rent;
        if (rent.getIdempresa() != -1) {
            tfEmpresa.setValue(rent.getEmpresa());
            tfResponsable.setValue(rent.getResponsable());
            tfPredio.setValue(rent.getPredio());
            getCuotas();
        }
    }
}
