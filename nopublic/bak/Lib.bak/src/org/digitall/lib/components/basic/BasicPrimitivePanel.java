package org.digitall.lib.components.basic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class BasicPrimitivePanel extends BasicContainerPanel {


    private int buttonsTopBorder = 4;
    private BasicPanel jpButtons = new BasicPanel();
    private Component content;
    private String title = "TITULO";
    private BasicTabContainer tabContainer;
    private ExtendedInternalFrame parentInternalFrame;

    public BasicPrimitivePanel() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicPrimitivePanel(BasicTabContainer _tabContainer) {
	super();
	tabContainer = _tabContainer;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(new BorderLayout());
	this.add(jpButtons, BorderLayout.SOUTH);
	jpButtons.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.LINE_AXIS));
	jpButtons.setBorder(BorderFactory.createMatteBorder(buttonsTopBorder, 0, 0, 0, BasicConfig.PANELCONTAINER_BACKGROUND_COLOR));
    }

    public void setContent(Component _content) {
	if (content != null) {
	    remove(content);
	}
	content = _content;
	add(content, BorderLayout.CENTER);
    }
    
    public void addButton(BasicButton _button) {
	_button.setMaximumSize(_button.getPreferredSize());
	jpButtons.add(_button);
    }

    public void setTitle(String _title) {
	title = _title;
    }

    public String getTitle() {
	return title;
    }

    public void refresh() {
	//Solo para compatibilidad
    }

     public void reload() {
        //Solo para compatibilidad
     }

    public BasicTabContainer getTabContainer() {
	return tabContainer;
    }
    
    public ExtendedInternalFrame getParentInternalFrame() {
	return parentInternalFrame;
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _parent) {
	parentInternalFrame = _parent;
    }
    
    public BasicDesktop getDesktop() {
	return parentInternalFrame.getDesktop();
    }

    public void setTabbedPane(BasicTabContainer _tabContainer) {
	tabContainer = _tabContainer;
    }
}
