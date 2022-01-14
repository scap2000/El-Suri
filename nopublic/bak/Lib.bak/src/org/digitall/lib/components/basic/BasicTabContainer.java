package org.digitall.lib.components.basic;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BasicTabContainer extends BasicContainerPanel {

    private BasicTitleLabel lblTitle;
    private BasicTabbedPane tabbedPane;
    private ExtendedInternalFrame parentInternalFrame;

    public BasicTabContainer() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	lblTitle = new BasicTitleLabel();
	//lblTitle.setText("TITLE");
	tabbedPane = new BasicTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	this.setLayout(new BorderLayout());
	this.add(lblTitle, BorderLayout.NORTH);
	lblTitle.setHorizontalAlignment(JLabel.CENTER);
	lblTitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
	this.add(tabbedPane, BorderLayout.CENTER);
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent changeEvent) {
				      changeSelectedTab();
				  }

			      }
	);
    }

    public void addTab(BasicPrimitivePanel _panel) {
	addTab(_panel.getTitle(), _panel);
    }

    public void addTab(String _string, BasicPrimitivePanel _panel) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _panel);
    }

    public void addTab(String _string, Icon _icon, BasicPrimitivePanel _panel) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _icon, _panel);
    }

    public void addTab(String _string, Icon _icon, BasicPrimitivePanel _panel, String _toolTip) {
	_panel.setTabbedPane(this);
	tabbedPane.addTab(_string, _icon, _panel, _toolTip);
    }

    public void removeTab(int _index) {
	tabbedPane.removeTabAt(_index);
    }

    public int getTabCount() {
	return tabbedPane.getTabCount();
    }

    public int getSelectedTab() {
	return tabbedPane.getSelectedIndex();
    }

    public void setTitle(String _title) {
	lblTitle.setText(_title);
    }

    public void setTitleAt(int _index, String _title) {
	tabbedPane.setTitleAt(_index, _title);
    }

    public void changeSelectedTab() {
	//Solo para compatibilidad
    }

    public void refreshTab(int _index) {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(_index)).refresh();
    }

    public void reloadTab(int _index) {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(_index)).reload();
    }

    public void refresh() {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
	((BasicPrimitivePanel)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).refresh();
    }

    public void reload() {
	//Cuidado si se estan aceptando otros objetos distintos al BasicPrimitivePanel
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            ((BasicPrimitivePanel)tabbedPane.getComponentAt(i)).reload();
        }
    }

    public void setSelectedTab(int _index) {
	tabbedPane.setSelectedIndex(_index);
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

    public BasicTabbedPane getTabbedPane() {
	return tabbedPane;
    }

}
