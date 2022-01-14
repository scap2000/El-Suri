package org.digitall.apps.systemmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.NextWizardButton;
import org.digitall.lib.icons.IconTypes;

//

public class ControlMain extends JFrame {

    private BasicPanel panelCenter = new BasicPanel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout layoutMain = new BorderLayout();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel lbl = new BasicLabel();
    private NextWizardButton bNext = new NextWizardButton();
    private CloseButton bExit = new CloseButton();
    private JRadioButton rbSystems = new JRadioButton();
    private JRadioButton rbUsers = new JRadioButton();
    private JRadioButton rbGroups = new JRadioButton();
    private JRadioButton rbFiles = new JRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    final String TEXTO = "Systems Administrator";
    private BasicLabel jLabel2 = new BasicLabel(IconTypes.SYSTEM_ICON_22x22);
    private BasicLabel jLabel4 = new BasicLabel(IconTypes.USER_ICON_22x22);
    private BasicLabel jLabel5 = new BasicLabel(IconTypes.FILE_ICON_22x22);

    public ControlMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(363, 216));
	this.setTitle("Systems Administrator");
	this.getContentPane().setLayout(layoutMain);
	panelCenter.setLayout(null);
	bNext.setBounds(new Rectangle(305, 155, 40, 25));
	bNext.setMnemonic('N');
	bNext.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNext_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(5, 65, 345, 55));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	rbSystems.setText("Systems");
	rbSystems.setBounds(new Rectangle(35, 20,70, 15));
	rbSystems.setFont(new Font("Dialog", 1, 11));
	rbUsers.setText("Users");
	rbUsers.setBounds(new Rectangle(150, 20, 70, 15));
	rbUsers.setFont(new Font("Dialog", 1, 11));
	rbUsers.setMnemonic('U');
	jLabel1.setText(" Options");
	jLabel1.setBounds(new Rectangle(10, 55, 55, 15));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	jLabel1.setOpaque(true);
	rbGroups.setText("Groups");
	rbGroups.setBounds(new Rectangle(35, 30, 70, 15));
	rbGroups.setFont(new Font("Dialog", 1, 11));
	rbGroups.setMnemonic('G');
	rbFiles.setText("Files");
	rbFiles.setBounds(new Rectangle(255, 20, 70, 15));
	rbFiles.setFont(new Font("Dialog", 1, 11));
	rbFiles.setMnemonic('F');
	jLabel2.setBounds(new Rectangle(10, 15, 20, 25));
	jLabel2.setSize(new Dimension(22, 22));
	jLabel2.setBackground(Color.white);
	jLabel2.setOpaque(true);
	jLabel4.setBounds(new Rectangle(125, 15, 22, 22));
	jLabel4.setSize(new Dimension(24, 24));
	jLabel4.setBackground(Color.white);
	jLabel4.setOpaque(true);
	jLabel5.setBounds(new Rectangle(230, 15, 22, 22));
	jLabel5.setSize(new Dimension(24, 24));
	jLabel5.setBackground(Color.white);
	jLabel5.setOpaque(true);
	lbl.setBounds(new Rectangle(0, 0, 55, 35));
	bExit.setBounds(new Rectangle(10, 155, 40, 25));
	bExit.setMnemonic('E');
	bExit.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bExit_actionPerformed(e);
		    }

		});
	grupo.add(rbGroups);
	grupo.add(rbUsers);
	grupo.add(rbSystems);
	grupo.add(rbFiles);
	jPanel1.add(rbGroups, null);
	jPanel1.add(rbUsers, null);
	jPanel1.add(rbSystems, null);
	jPanel1.add(rbFiles, null);
	jPanel1.add(jLabel2, null);
	jPanel1.add(jLabel4, null);
	jPanel1.add(jLabel5, null);
	panelCenter.add(jLabel1, null);
	panelCenter.add(bExit, null);
	panelCenter.add(jPanel1, null);
	panelCenter.add(bNext, null);
	panelCenter.add(lbl, null);
	this.getContentPane().add(panelCenter, BorderLayout.CENTER);
	rbSystems.setSelected(true);
	rbSystems.setMnemonic('S');
	rbGroups.setVisible(false);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bExit_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bNext_actionPerformed(ActionEvent e) {
	if (rbSystems.isSelected()) {
	    Systems sistemas = new Systems();
	    sistemas.setVisible(true);
	    sistemas.setModal(true);
	} else if (rbFiles.isSelected()) {
	    ManagementFiles files = new ManagementFiles();
	    files.setModal(true);
	    files.setVisible(true);
	} else if (rbUsers.isSelected()) {
	    FindUser buscarUsuario = new FindUser();
	    buscarUsuario.setModal(true);
	    buscarUsuario.setVisible(true);
	} else if (rbGroups.isSelected()) {
	    Advisor.messageBox("Sorry, button under construction!", "Message");
	}
    }

}
