package org.digitall.apps.taxes.interfases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;

public class FeeOptions extends BasicDialog {

    private JPanel OptionPanel = new JPanel();
    private JRadioButton rbOpcion1 = new JRadioButton();
    private JRadioButton rbOpcion2 = new JRadioButton();
    //private JRadioButton rbOpcion3 = new JRadioButton();
    private ButtonGroup grupoBtn = new ButtonGroup();
    private int idTipoImpuesto = -1;
    
    private JLabel lblTitle = new JLabel();
    private JButton btnAccept = new JButton();
    private JTextField tfOption;
    private JLabel lblSubTitle = new JLabel();

    public FeeOptions() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(414, 217));
	this.setTitle("Aviso!");
	this.setPreferredSize(new Dimension(565, 300));
	
	rbOpcion1.setText("<html>Registrar el último pago. Luego de registrarlo deberá<br>recalcular la deuda del catastro/dominio</html>");
	rbOpcion1.setBounds(new Rectangle(15, 10, 360, 35));
	//rbOpcion2.setText("Calcular la deuda a partir de la Fecha de Registro");
	rbOpcion2.setText("Cancelar");
	rbOpcion2.setBounds(new Rectangle(15, 55, 360, 20));
	rbOpcion2.setFont(new Font("Dialog", 1, 12));
	/*rbOpcion3.setText("Continuar");
	rbOpcion3.setBounds(new Rectangle(15, 70, 495, 15));
	rbOpcion3.setLayout(null);
	rbOpcion3.setFont(new Font("Dialog", 1, 12));*/
	lblTitle.setText("El Catastro/Dominio no registra último pago");
	lblTitle.setBounds(new Rectangle(17, 5, 375, 20));
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitle.setFont(new Font("Dialog", 1, 14));
	btnAccept.setText("Aceptar");
	btnAccept.setBounds(new Rectangle(157, 160, 95, 25));
	btnAccept.setMnemonic('A');
	btnAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAccept_actionPerformed(e);
				}

			    }
	);
	lblSubTitle.setText("¿Qué desea hacer?");
	lblSubTitle.setBounds(new Rectangle(17, 35, 375, 20));
	lblSubTitle.setToolTipText("null");
	lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblSubTitle.setFont(new Font("Dialog", 1, 14));
	OptionPanel.setBounds(new Rectangle(9, 60, 390, 90));
	OptionPanel.setLayout(null);
	OptionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	grupoBtn.add(rbOpcion1);
	grupoBtn.add(rbOpcion2);
	//grupoBtn.add(rbOpcion3);
	rbOpcion1.setSelected(true);
	rbOpcion1.setFont(new Font("Dialog", 1, 12));
	//OptionPanel.add(rbOpcion3, null);
	OptionPanel.add(rbOpcion2, null);
	OptionPanel.add(rbOpcion1, null);
	this.getContentPane().add(lblSubTitle, null);
	this.getContentPane().add(OptionPanel, null);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(lblTitle, null);
    }
 
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (rbOpcion1.isSelected())  {
	    tfOption.setText("1");
	} else if (rbOpcion2.isSelected()) {
	    tfOption.setText("2");
	} /*else if (rbOpcion3.isSelected()) {
	    tfOption.setText("3");
	}*/
	this.dispose();
    }


    public void setTfOption(JTextField _tfOption) {
	tfOption = _tfOption;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
	loadTitle();
	
    }

    private void loadTitle() {
	switch (idTipoImpuesto){
	    case 1: {
		    lblTitle.setText("el Catastro no registra último Pago de");
		    lblSubTitle.setText(" la Tasa General de Servicios, ¿Qué desea hacer?");
	    }
		break;
	    case 2: {
		    lblTitle.setText("el Catastro no registra último Pago del");
		    lblSubTitle.setText("Impuesto Inmobiliario, ¿Qué desea hacer?");
		}
		break;
	    case 3: {
		    lblTitle.setText("el Dominio no registra último Pago del");
		    lblSubTitle.setText("Impuesto Automotor, ¿Qué desea hacer?");
		}
		break;  
	}
    }

}
