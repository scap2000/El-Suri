package org.digitall.common.geo.mapping.panels;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import org.digitall.lib.components.basic.BasicCombo;

import org.digitall.lib.components.basic.BasicPanel;

import org.jfree.ui.StrokeSample;

/**
 * A component for choosing a stroke from a list of available strokes.  This class needs work.
 */
public class StrokeChooserPanel extends BasicPanel {

    /** A combo for selecting the stroke. */
    private BasicCombo selector;

    /**
     * Creates a panel containing a combo-box that allows the user to select
     * one stroke from a list of available strokes.
     *
     * @param current  the current stroke sample.
     * @param available  an array of 'available' stroke samples.
     */
    public StrokeChooserPanel(final StrokeSample current, final StrokeSample[] available) {
        setLayout(new BorderLayout());
        this.selector = new BasicCombo(available);
        this.selector.setSelectedItem(current);
        this.selector.setRenderer(new StrokeSample(new BasicStroke(1)));
        add(this.selector);
        // Changes due to focus problems!! DZ
        selector.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                selector.transferFocus();
            }
        });
    }

    /**
     * Returns the selected stroke.
     *
     * @return the selected stroke.
     */
    public Stroke getSelectedStroke() {
        final StrokeSample sample = (StrokeSample) this.selector.getSelectedItem();
        return sample.getStroke();
    }

    public BasicCombo getCombo() {
	return selector;
    }

}
