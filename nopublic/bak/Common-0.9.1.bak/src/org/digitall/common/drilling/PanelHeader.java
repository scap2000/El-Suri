package org.digitall.common.drilling;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;

public class PanelHeader extends BasicContainerPanel {

    private BasicLabel lblIconLeft = new BasicLabel();
    private BasicLabel lblTitle = new BasicLabel();
    private BasicLabel lblIconRight = new BasicLabel();

    public PanelHeader(int _nWidth, String _sTitle, ImageIcon _imgIconLeft, ImageIcon _imgIconRight) {
	try {
	    jbInit(_nWidth - 8, _sTitle, _imgIconRight, _imgIconLeft);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit(int nWidth, String sTitle, ImageIcon imgIconRight, ImageIcon imgIconLeft) throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(nWidth, 32));
	this.setBounds(new Rectangle(0, 0, nWidth, 32));
	int nWidthIconRigth = imgIconRight.getIconWidth() + 10;
	int nWidthIconLeft = imgIconLeft.getIconWidth() + 10;
	lblIconLeft.setIcon(imgIconLeft);
	lblIconLeft.setSize(new Dimension(nWidthIconLeft, 32));
	lblIconLeft.setBounds(new Rectangle(0, 0, nWidthIconLeft, 32));
	lblIconLeft.setOpaque(true);
	lblIconLeft.setBackground(new Color(48, 48, 48));
	lblIconLeft.setHorizontalAlignment(SwingConstants.CENTER);
	lblIconLeft.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTitle.setText(sTitle);
	lblTitle.setSize(new Dimension(nWidth - nWidthIconRigth - nWidthIconLeft, 32));
	lblTitle.setBounds(new Rectangle(nWidthIconLeft, 0, nWidth - nWidthIconRigth - nWidthIconLeft, 32));
	lblTitle.setOpaque(true);
	lblTitle.setBackground(new Color(48, 48, 48));
	lblTitle.setForeground(Color.WHITE);
	lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitle.setFont(new Font("Dialog", 1, 13));
	lblIconRight.setIcon(imgIconRight);
	lblIconRight.setSize(new Dimension(nWidthIconRigth, 32));
	lblIconRight.setBounds(new Rectangle(nWidth - nWidthIconRigth, 0, nWidthIconRigth, 32));
	lblIconRight.setOpaque(true);
	lblIconRight.setBackground(new Color(48, 48, 48));
	lblIconRight.setHorizontalAlignment(SwingConstants.CENTER);
	lblIconRight.setHorizontalTextPosition(SwingConstants.CENTER);
	this.add(lblIconLeft, null);
	this.add(lblTitle, null);
	this.add(lblIconRight, null);
    }

}
