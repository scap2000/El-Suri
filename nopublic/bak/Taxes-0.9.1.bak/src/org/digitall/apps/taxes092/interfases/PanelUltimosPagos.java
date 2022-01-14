package org.digitall.apps.taxes092.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;

public class PanelUltimosPagos extends BasicPrimitivePanel {
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private CloseButton btnClose= new CloseButton();
    private PrintButton btnImprimirPagos = new PrintButton();
    
    private int[] sizeColumnList = {  147, 89, 121, 124, 114, 298, 298, 298 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Últimos Pagos", dataRow);
    private Vector headerList = new Vector();
    
    public PanelUltimosPagos() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(713, 441));
        this.setPreferredSize(new Dimension(660, 512));
        listPanel.setPreferredSize(new Dimension(400, 250));
        listPanel.setBounds(new Rectangle(5, 15, 700, 380));
        panelCentro.setLayout(null);
        panelCentro.setBounds(new Rectangle(0, 1, 660, 300));
        panelCentro.setBorder(BorderPanel.getBorderPanel(""));
        panelContenedor.setLayout(borderLayout1);
        panelCentro.add(listPanel, BorderLayout.CENTER);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);
        this.add(panelContenedor, BorderLayout.CENTER);
        addButton(btnClose);
        addButton(btnImprimirPagos);
        btnImprimirPagos.setToolTipText("Imprimir listado de pagos realizados");
        btnClose.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    btnClose_actionPerformed(e);
                                }
                            }
        );
        btnImprimirPagos.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    
                                }
                            }
        );
        
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   
                                               } 
                                           }

                                       }
        );
        setheaderList();
        listPanel.refresh("");
    }
    
    private void setheaderList() {
        headerList.removeAllElements();
        headerList.addElement("*"); //idBien
        headerList.addElement("Catastro/ Dominio"); 
        headerList.addElement("Tipo");
        headerList.addElement("T.G.S");
        headerList.addElement("Inmobiliario");
        headerList.addElement("Automotor");
        headerList.addElement("Plan de Pago (T.G.S)");  
        headerList.addElement("Plan de Pago (Inmob)");
        headerList.addElement("Plan de Pago (Automotor)");
        
        listPanel.setParams("taxes.getUltimosPagos", "", headerList);
    }

    private void btnImprimirPagos_actionPerformed(ActionEvent e) {
        
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        this.getParentInternalFrame().close();
    }
    
}
