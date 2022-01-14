package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;
import java.awt.Frame;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

public class ReportDetailJDialog extends BasicDialog {

    ReportDetail reportDetail;
    private ComponentsManager compManager;
    private int idTask = -1;
    private int idTaskByUser = -1;

    public ReportDetailJDialog(int _idTask, int _idTaskByUser) {
	idTask = _idTask;
	idTaskByUser = _idTaskByUser;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ReportDetailJDialog(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	reportDetail = new ReportDetail(this, idTask, idTaskByUser);
	this.setSize(new Dimension(585, 535));
	this.getContentPane().setLayout(null);
	this.getContentPane().add(reportDetail);
	this.setTitle("Progreso diario de la tarea");
	compManager.centerWindow(this);
    }

}
