package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicToolBar;

public class ProjectToolBar extends BasicToolBar {

    private BasicDesktop desktop;
    private BasicButton btnTaskMagmt = new BasicButton();
    private BasicButton btnTaskByEmployeeMgmt = new BasicButton();
    private BasicButton btnReport = new BasicButton();

    public ProjectToolBar(BasicDesktop _desktop) {
	try {
	    desktop = _desktop;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(901, 38));
	btnTaskMagmt.setText("Proyectos");
	btnTaskMagmt.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnTaskMagmt_actionPerformed(e);
				    }

				}
	);
	btnTaskByEmployeeMgmt.setText("Asignar Tareas");
	btnTaskByEmployeeMgmt.addActionListener(new ActionListener() {

					     public void actionPerformed(ActionEvent e) {
						 btnTaskByEmployeeMgmt_actionPerformed(e);
					     }

					 }
	);
	btnReport.setText("Reportes");
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	this.add(btnTaskMagmt, null);
	this.add(btnTaskByEmployeeMgmt, null);
	this.add(btnReport, null);
    }

    private void btnTaskMagmt_actionPerformed(ActionEvent e) {
	ProjectFrame projectFrame = new ProjectFrame();
	projectFrame.setVisible(true);
    }

    private void btnTaskByEmployeeMgmt_actionPerformed(ActionEvent e) {
	UserListJDialog userListBasicDialog = new UserListJDialog();
	userListBasicDialog.setModal(true);
	userListBasicDialog.setVisible(true);
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	ReportByUserJDialog reportByUserBasicDialog = new ReportByUserJDialog();
	reportByUserBasicDialog.setModal(true);
	reportByUserBasicDialog.setVisible(true);
    }

}
