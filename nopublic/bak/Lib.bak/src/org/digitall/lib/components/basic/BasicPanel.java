package org.digitall.lib.components.basic;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.digitall.lib.components.BorderPanel;

public class BasicPanel extends JPanel {

    private String title = "";

    public BasicPanel() {
	this("");
    }

    public BasicPanel(String _title) {
	super();
	title = _title;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicPanel(LayoutManager _layout) {
	super(_layout);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(BasicConfig.PANEL_BACKGROUND_COLOR);
	if (title.trim().length() > 0) {
	    setTitle(title);
	}
    }


    public void setTitle(String _title) {
	setBorder(BorderPanel.getBorderPanel(_title));
	//setBorder(BorderPanel.getBorderPanel(_title, BasicConfig.LABEL_FOREGROUND_COLOR, this.getBackground()));
    }

}


/*public class BasicPanel extends JXLayer {

    private Painter translucentPainter = new TranslucentPainter();
    private JPanel panel = new JPanel();
    private String title = "";
    private LayoutManager layout = null;

    public BasicPanel() {
	this("");
    }

    public BasicPanel(String _title) {
	super();
	title = _title;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setPainter(translucentPainter);
	panel.setLayout(null);
	setView(panel);
	this.setBackground(BasicConfig.PANEL_BACKGROUND_COLOR);
	if (title.trim().length() > 0) {
	    setTitle(title);
	}
    }

    public Component add(Component component) {
	panel.add(component);
	setView(panel);
	return component;
    }

    public void add(Component component, Object object) {
	panel.add(component, object);
	setView(panel);
    }

    public void setLayout(LayoutManager layoutManager) {
	if (layoutManager != null) {
	    super.setLayout(layoutManager);
	}
    }

    static class TranslucentPainter<V extends JComponent> extends AbstractPainter<V> {

	public void paint(Graphics2D g2, JXLayer<V> l) {
	    l.paint(g2);
	    if (l.isLocked()) {
		// the view is hidden, so we need to paint it manually,
		// it is important to call print() instead of paint() here
		// because print() doesn't affect the frame's double buffer
		l.getView().print(g2);
		g2.setColor(new Color(0, 128, 128, 128));
		g2.fillRect(0, 0, l.getWidth(), l.getHeight());
	    }
	}

    }

    public void setTitle(String _title) {
	panel.setBorder(BorderPanel.getBorderPanel(_title, BasicConfig.LABEL_FOREGROUND_COLOR, this.getBackground()));
    }

}
*/