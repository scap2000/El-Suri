package org.digitall.apps.filemanager;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JToolBar;

import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.UnAssignButton;

public class FileManToolBar extends JToolBar {

    private AddButton addRemoteDir = new AddButton();
    private DeleteButton delRemoteDir = new DeleteButton();
    private FindButton findRemoteDir = new FindButton();
    private AssignButton assignButton1 = new AssignButton(true);
    private UnAssignButton unAssignButton1 = new UnAssignButton(true);
    private ModifyButton modifyButton1 = new ModifyButton();
    private Object source;

    public FileManToolBar() {
	try {
	    jbInit();
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    private void jbInit() {
	this.setSize(new Dimension(33, 153));
	this.setOrientation(1);
	addRemoteDir.setBounds(new Rectangle(10, 480, 40, 25));
	add(addRemoteDir, null);
	add(modifyButton1, null);
	add(delRemoteDir, null);
	add(findRemoteDir, null);
	add(unAssignButton1, null);
	add(assignButton1, null);
    }

    public void setSource(Object _source) {
	source = _source;
	if (LocalFileTreePanel.class.isInstance(_source)) {
	}
    }

}
