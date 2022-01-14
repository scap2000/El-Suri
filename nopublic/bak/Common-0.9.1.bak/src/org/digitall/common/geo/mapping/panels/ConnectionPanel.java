package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.geo.mapping.classes.GeometrySetConfig;

public class ConnectionPanel extends BasicPrimitivePanel {

    private BasicContainerPanel centralPanel = new BasicContainerPanel();
    private TFInput tfServer = new TFInput(DataTypes.STRING, "Servidor", false);
    private TFInput tfDatabase = new TFInput(DataTypes.STRING, "Base de Datos", false);
    private TFInput tfUser = new TFInput(DataTypes.STRING, "Usuario", false);
    
    private BasicPasswordField tfPass = new BasicPasswordField();
    private SaveDataButton btnAccept = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();

    private GeometrySetConfig geometrySetConfig;
    private BasicLabel lbPass = new BasicLabel();

    public ConnectionPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(260, 207));
	tfServer.setBounds(new Rectangle(10, 5, 235, 35));
	tfDatabase.setBounds(new Rectangle(10, 125, 80, 35));
	tfDatabase.setBounds(new Rectangle(10, 45, 235, 35));
	tfUser.setBounds(new Rectangle(10, 85, 235, 35));
        tfPass.setBounds(new Rectangle(10, 140, 235, 20));
        centralPanel.add(lbPass, null);
        centralPanel.add(tfServer, null);
        centralPanel.add(tfDatabase, null);
        centralPanel.add(tfUser, null);
        centralPanel.add(tfPass, null);
        btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
        btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
        lbPass.setText("Contraseña");
        lbPass.setBounds(new Rectangle(10, 125, 235, 14));
        centralPanel.setLayout(null);
        this.add(centralPanel, BorderLayout.CENTER);
	this.addButton(btnAccept);
	this.addButton(btnCancel);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }

    public boolean saveData() {
	geometrySetConfig.setConnectionParams(tfServer.getString(), tfDatabase.getString(), tfUser.getString(), new String(tfPass.getPassword()));
	getParentInternalFrame().close();
	return true;
    }

    public void setGeometrySetConfig(GeometrySetConfig _geometrySetConfig) {
	geometrySetConfig = _geometrySetConfig;
	loadData();
    }

    private void loadData() {
	tfServer.setValue(geometrySetConfig.getServerURL());
        tfDatabase.setValue(geometrySetConfig.getDatabase());
        tfUser.setValue(geometrySetConfig.getUser());
        tfPass.setText(geometrySetConfig.getPassword());
	//getParentInternalFrame().setInfo(layerConfig.getAlias());
    }
}
