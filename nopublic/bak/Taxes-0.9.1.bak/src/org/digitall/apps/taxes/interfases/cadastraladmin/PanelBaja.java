package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class PanelBaja extends BasicPrimitivePanel{

    private JLabel lblCatastro = new JLabel();
    private JLabel lblTituloCatastro = new JLabel();
    private TFInput tfFechaBaja = new TFInput(DataTypes.SIMPLEDATE, "Fecha de Baja",false);
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Cadastral cadastral;
    private CadastralList parent;

    public PanelBaja(Cadastral _cadastral, CadastralList _parent) {
	try {
            cadastral = _cadastral;
            parent = _parent;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	content.setLayout(null);
	btnSave.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }
			   }
	);
        btnSave.setToolTipText("Grabar Baja");
	btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }   
	);
	this.setSize(new Dimension(369, 104));
	tfFechaBaja.setPreferredSize(new Dimension(105, 35));
	tfFechaBaja.setBounds(new Rectangle(230, 15, 105, 35));
        lblCatastro.setBounds(new Rectangle(10, 30, 185, 20));
        lblCatastro.setFont(new Font("Dialog", 1, 16));
        lblCatastro.setForeground(Color.red);
        lblCatastro.setBackground(new Color(183, 215, 255));
        lblCatastro.setText("" + cadastral.getCatastro());
        lblCatastro.setHorizontalAlignment(SwingConstants.CENTER);
        lblCatastro.setOpaque(true);
        lblCatastro.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        lblTituloCatastro.setText("Catastro Registrado");
        lblTituloCatastro.setBounds(new Rectangle(10, 15, 185, 15));
        lblTituloCatastro.setFont(new Font("Dialog", 1, 12));
        lblTituloCatastro.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloCatastro.setOpaque(true);
        lblTituloCatastro.setBackground(new Color(255, 132, 0));
        lblTituloCatastro.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        content.add(tfFechaBaja, null);
        content.add(lblTituloCatastro);
        content.add(lblCatastro);
        addButton(btnClose);
	addButton(btnSave);
	this.add(content,null);
    }
    
    
    private void btnSave_actionPerformed(ActionEvent e) {
        if (Advisor.question("Baja", "Está seguro de dar de baja al catastro " + cadastral.getCatastro())) {
            if ( LibSQL.getBoolean("taxes.darbajacatastro", cadastral.getIdCatastro() + "," + tfFechaBaja.getDateForSQL())) {
                Advisor.messageBox("Se dió de baja el catastro " + cadastral.getCatastro(), "Aviso");
            } else {
                Advisor.messageBox("No puede darse de baja el catastro por que adeudada \n anticipos regulares o cuotas de plan de pago", "Aviso");
            }
            parent.refresh();
            getParentInternalFrame().close();
        }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      
        parent.refresh();
	getParentInternalFrame().close();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {      
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Baja de Catastro");
    }
}
