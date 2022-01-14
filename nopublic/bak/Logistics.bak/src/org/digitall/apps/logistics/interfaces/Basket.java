package org.digitall.apps.logistics.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;

public class Basket extends BasicInternalFrame implements DropTargetListener {

    private BasicLabel lblIcon = new BasicLabel();
    private MouseListener dragListener;
    private int entityType;
    private BorderLayout borderLayout = new BorderLayout();

    public Basket(int _entityType) {
	try {
	    entityType = _entityType;
	    lblIcon = new BasicLabel(EntityTypes.getImageIcon(entityType));
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//putClientProperty("BasicInternalFrame.isPalette", Boolean.TRUE);
	setFrameIcon(null);
	setSize(new Dimension(100, 100));
	this.getContentPane().setLayout(borderLayout);
	lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
	lblIcon.setHorizontalTextPosition(SwingConstants.CENTER);
	lblIcon.setOpaque(false);
	add(lblIcon, BorderLayout.CENTER);
	dragListener = new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
		    TransferHandler handler = lblIcon.getTransferHandler();
		    handler.exportAsDrag(lblIcon, me, TransferHandler.COPY_OR_MOVE);
		    DNDCoordinator.setDragSourceType(entityType);
		}

	    }
	;
    }
    
    public BasicLabel getBasket() {
	return lblIcon;
    }
    
    public void setDragable() {
	lblIcon.setTransferHandler(new DNDSupport());
	addMouseListener(dragListener);
    }
    
    public void setDropable() {
	DropTarget dropTarget = new DropTarget(getBasket(), this);
    }

    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {

    }

    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {

    }

    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {

    }

    public void dragExit(DropTargetEvent dropTargetEvent) {

    }

    public void drop(DropTargetDropEvent dropTargetDropEvent) {
	DNDCoordinator.completeDrop(entityType);
    }

    public void setEntityType(int _entityType) {
	entityType = _entityType;
    }

}
