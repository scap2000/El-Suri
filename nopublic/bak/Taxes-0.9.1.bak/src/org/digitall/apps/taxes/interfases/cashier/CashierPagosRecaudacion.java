package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class CashierPagosRecaudacion extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpBusqueda = new BasicPanel();
    private BasicPanel jpRecaudacion = new BasicPanel();
    private BasicPanel jpPagos = new BasicPanel();

    private BasicLabel lblTotalEfectivoRecaudacion = new BasicLabel();
    private BasicLabel lblTotalChequesRecaudacion = new BasicLabel();
    private BasicLabel lblTotalRecaudacion = new BasicLabel();
    private BasicLabel lblTotalEfectivoPagos = new BasicLabel();
    private BasicLabel lblTotalChequesPagos = new BasicLabel();
    private BasicLabel lblTotalPagos = new BasicLabel();

    private TFInput tfFecha = new TFInput(DataTypes.SIMPLEDATE,"Fecha",false);

    private JMoneyEntry tfTotalEfectivoRecaudacion = new JMoneyEntry();
    private JMoneyEntry tfTotalChequesRecaudacion = new JMoneyEntry();
    private JMoneyEntry tfTotalRecaudacion = new JMoneyEntry();
    private JMoneyEntry tfTotalEfectivoPagos = new JMoneyEntry();
    private JMoneyEntry tfTotalChequesPagos = new JMoneyEntry();
    private JMoneyEntry tfTotalPagos = new JMoneyEntry();

    private PrintButton btnImprimirReporte = new PrintButton();
    private FindButton btnBuscar = new FindButton();
       
    public CashierPagosRecaudacion() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(744, 262));
	this.setPreferredSize(new Dimension(700, 537));
	this.setTitle("Anticipo de Haberes");
	content.setLayout(null);
	content.setPreferredSize(new Dimension(700, 500));
	content.setSize(new Dimension(744, 225));
        jpBusqueda.setLayout(null);
        jpRecaudacion.setLayout(null);
        jpPagos.setLayout(null);
	this.setContent(content);

        jpBusqueda.setBorder(BorderPanel.getBorderPanel("Buscar"));
	
	jpPagos.setBounds(new Rectangle(375, 60, 365, 160));
        jpPagos.setBorder(BorderPanel.getBorderPanel("Pagos"));
	jpRecaudacion.setBounds(new Rectangle(5, 60, 365, 160));
        jpRecaudacion.setBorder(BorderPanel.getBorderPanel("Recaudación"));
        jpRecaudacion.setSize(new Dimension(365, 160));
        addButton(btnImprimirReporte);
        btnImprimirReporte.setToolTipText("Imprimir Reporte");
        btnImprimirReporte.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnImprimirReporte_actionPerformed(e);
                }

            }
        );

        tfFecha.setBounds(new Rectangle(320, 15, 95, 35));
        btnBuscar.setBounds(new Rectangle(680, 20, 35, 25));
        btnBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBuscar_actionPerformed(e);
                }
            });
        jpBusqueda.setBounds(new Rectangle(5, 0, 735, 60));
        tfTotalEfectivoRecaudacion.setFont(new Font("Dialog", 1, 15));
	tfTotalEfectivoRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalEfectivoRecaudacion.setEditable(false);
	tfTotalEfectivoRecaudacion.setBounds(new Rectangle(190, 25, 165, 35));

	tfTotalEfectivoRecaudacion.setSize(new Dimension(160, 35));
	tfTotalChequesRecaudacion.setFont(new Font("Dialog", 1, 15));
	tfTotalChequesRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalChequesRecaudacion.setEditable(false);
	tfTotalChequesRecaudacion.setBounds(new Rectangle(190, 70, 165, 35));

        tfTotalPagos.setBounds(new Rectangle(190, 115, 165, 35));
        tfTotalPagos.setFont(new Font("Dialog", 1, 20));
        tfTotalPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
        tfTotalPagos.setEditable(false);
        tfTotalPagos.setBounds(new Rectangle(190, 115, 165, 35));
        
	tfTotalChequesRecaudacion.setSize(new Dimension(160, 35));
	tfTotalRecaudacion.setFont(new Font("Dialog", 1, 20));
	tfTotalRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalRecaudacion.setEditable(false);
	tfTotalRecaudacion.setBounds(new Rectangle(190, 115, 165, 35));

	tfTotalRecaudacion.setSize(new Dimension(160, 35));
	tfTotalEfectivoPagos.setFont(new Font("Dialog", 1, 15));
	tfTotalEfectivoPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalEfectivoPagos.setEditable(false);
	
	tfTotalChequesPagos.setSize(new Dimension(300, 35));
	tfTotalChequesPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalChequesPagos.setEditable(false);
	
	tfTotalChequesPagos.setFont(new Font("Dialog", 1, 15));
	tfTotalChequesPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	tfTotalChequesPagos.setEditable(false);

	tfTotalChequesPagos.setBounds(new Rectangle(190, 70, 165, 35));
	tfTotalEfectivoPagos.setBounds(new Rectangle(190, 25, 160, 35));

        tfTotalEfectivoPagos.setSize(new Dimension(165, 35));
        lblTotalEfectivoRecaudacion.setOpaque(true);
	lblTotalEfectivoRecaudacion.setFont(new Font("Dialog", 1, 13));
	lblTotalEfectivoRecaudacion.setBackground(Color.white);
	lblTotalEfectivoRecaudacion.setText("EFECTIVO");
	lblTotalEfectivoRecaudacion.setForeground(Color.black);
	lblTotalEfectivoRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalEfectivoRecaudacion.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalEfectivoRecaudacion.setHorizontalTextPosition(SwingConstants.CENTER);

	lblTotalEfectivoRecaudacion.setBounds(new Rectangle(10, 25, 185, 35));
	lblTotalEfectivoRecaudacion.setSize(new Dimension(180, 35));
	lblTotalChequesRecaudacion.setText("CHEQUES");
	lblTotalChequesRecaudacion.setOpaque(true);
	lblTotalChequesRecaudacion.setFont(new Font("Dialog", 1, 13));
	lblTotalChequesRecaudacion.setBackground(Color.white);
	lblTotalChequesRecaudacion.setForeground(Color.black);
	lblTotalChequesRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalChequesRecaudacion.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalChequesRecaudacion.setHorizontalTextPosition(SwingConstants.CENTER);

	lblTotalChequesRecaudacion.setBounds(new Rectangle(10, 70, 185, 35));
	lblTotalChequesRecaudacion.setSize(new Dimension(180, 35));
	lblTotalRecaudacion.setText("TOTAL RECAUDACIÓN");
	lblTotalRecaudacion.setOpaque(true);
	lblTotalRecaudacion.setFont(new Font("Dialog", 1, 13));
	lblTotalRecaudacion.setBackground(Color.white);
	lblTotalRecaudacion.setForeground(Color.black);
	lblTotalRecaudacion.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalRecaudacion.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalRecaudacion.setHorizontalTextPosition(SwingConstants.CENTER);

	lblTotalRecaudacion.setBounds(new Rectangle(10, 115, 185, 35));
	lblTotalRecaudacion.setSize(new Dimension(180, 35));
	lblTotalEfectivoPagos.setText("EFECTIVO");
	lblTotalEfectivoPagos.setOpaque(true);
	lblTotalEfectivoPagos.setFont(new Font("Dialog", 1, 13));
	lblTotalEfectivoPagos.setBackground(Color.white);
	lblTotalEfectivoPagos.setForeground(Color.black);
	lblTotalEfectivoPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalEfectivoPagos.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalEfectivoPagos.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTotalEfectivoPagos.setBounds(new Rectangle(10, 25, 180, 35));
	
	lblTotalChequesPagos.setText("CHEQUES");
	lblTotalChequesPagos.setOpaque(true);
	lblTotalChequesPagos.setFont(new Font("Dialog", 1, 13));
	lblTotalChequesPagos.setBackground(Color.white);
	lblTotalChequesPagos.setForeground(Color.black);
	lblTotalChequesPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalChequesPagos.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalChequesPagos.setHorizontalTextPosition(SwingConstants.CENTER);

	lblTotalChequesPagos.setBounds(new Rectangle(10, 70, 180, 35));
	lblTotalPagos.setText("TOTAL PAGOS");
	lblTotalPagos.setOpaque(true);
	lblTotalPagos.setFont(new Font("Dialog", 1, 13));
	lblTotalPagos.setBackground(Color.white);
	lblTotalPagos.setForeground(Color.black);
	lblTotalPagos.setBorder(BorderFactory.createLineBorder(new Color(12, 12, 33), 1));
	lblTotalPagos.setHorizontalAlignment(SwingConstants.CENTER);
	lblTotalPagos.setHorizontalTextPosition(SwingConstants.CENTER);

	lblTotalPagos.setBounds(new Rectangle(10, 115, 180, 35));

        lblTotalPagos.setSize(new Dimension(180, 35));
        jpRecaudacion.add(lblTotalEfectivoRecaudacion);
        jpRecaudacion.add(tfTotalEfectivoRecaudacion);
        jpRecaudacion.add(lblTotalChequesRecaudacion);
        jpRecaudacion.add(tfTotalChequesRecaudacion);
        jpRecaudacion.add(lblTotalRecaudacion);
        jpRecaudacion.add(tfTotalRecaudacion);
        jpPagos.add(lblTotalEfectivoPagos);
        jpPagos.add(tfTotalEfectivoPagos);
        jpPagos.add(lblTotalChequesPagos);
        jpPagos.add(tfTotalChequesPagos);
        jpPagos.add(lblTotalPagos);
        jpPagos.add(tfTotalPagos);
        jpBusqueda.add(btnBuscar, null);
        jpBusqueda.add(tfFecha, null);
        content.add(jpBusqueda, null);
        content.add(jpPagos, null);
        content.add(jpRecaudacion, null);
    }

    private void btnImprimirReporte_actionPerformed(ActionEvent e) {
        
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        cargarFormulario();
    }
    
    private void cargarFormulario() {
        
    }
}
