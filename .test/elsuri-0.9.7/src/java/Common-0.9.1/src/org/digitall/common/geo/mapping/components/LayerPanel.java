/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * LayerPanel.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;

import org.digitall.common.geo.mapping.panels.LayerConfigPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.mapping.classes.Layer;

public class LayerPanel extends BasicPanel {

    private BasicCheckBox chkOn = new BasicCheckBox();
    private BasicCheckBox chkActive = new BasicCheckBox();
    private BasicLabel lblLayerName = new BasicLabel();
    private RefreshGridButton btnReload = new RefreshGridButton();
    private PrintButton btnConfigure = new PrintButton();
    private RefreshGridButton jButton3 = new RefreshGridButton();
    private SaveDataButton jButton4 = new SaveDataButton();
    private Layer layer;
    private LayerTree layerTree;
    private BasicLabel lblColor = new BasicLabel();

    public LayerPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public LayerPanel(LayerTree _layerTree, Layer _layer) {
	try {
	    jbInit();
	    layerTree = _layerTree;
	    setLayer(_layer);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(378, 20));
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	this.setLayout(gridBagLayout1);
	this.setMinimumSize(new Dimension(378, 20));
	//this.setBounds(new Rectangle(10, 10, 378, 25));
	this.setOpaque(false);
	chkOn.setHorizontalAlignment(SwingConstants.CENTER);
	chkOn.addActionListener(new ActionListener() {

			     public void actionPerformed(ActionEvent e) {
				 chkOn_actionPerformed(e);
			     }

			 }
	);
	chkActive.setHorizontalAlignment(SwingConstants.CENTER);
	chkActive.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     chkActive_actionPerformed(e);
				 }

			     }
	);
	lblLayerName.setText("Layer");
	lblLayerName.setForeground(Color.black);
	btnReload.setSize(new Dimension(20, 20));
	btnReload.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReload_actionPerformed(e);
				 }

			     }
	);
	btnConfigure.setSize(new Dimension(20, 20));
	btnConfigure.setToolTipText("Configurar estilo del layer");
	btnConfigure.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnConfigure_actionPerformed(e);
				    }

				}
	);
	jButton3.setBounds(new Rectangle(330, 2, 20, 20));
	jButton3.setSize(new Dimension(20, 20));
	jButton4.setBounds(new Rectangle(355, 2, 20, 20));
	jButton4.setSize(new Dimension(20, 20));
	lblColor.addMouseListener(new MouseAdapter() {

			       public void mouseClicked(MouseEvent e) {
				   lblColor_mouseClicked(e);
			       }

			   }
	);
	btnReload.setToolTipText("Recargar layer");
	this.add(lblColor, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 4, 0), 12, 12));
	this.add(lblLayerName, new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 83), 149, 5));
	this.add(chkActive, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), -3, -6));
	this.add(chkOn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), -3, -6));
	this.add(btnReload, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), -8, -13));
	this.add(btnConfigure, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), -8, -13));
	//this.add(jButton3, null);
	//this.add(jButton4, null);
    }

    public void setLayer(Layer _layer) {
	layer = _layer;
	repaint();
    }

    public void paint(Graphics _g) {
	if (layer != null) {
	    chkOn.setSelected(layer.getLayerConfig().isOn());
	    chkActive.setSelected(layer.getLayerConfig().isActive());
	    lblLayerName.setText(layer.getLayerConfig().getAlias() + " (" + layer.getGeometrySet().getGeometriesCount() + ")");
	    lblLayerName.setToolTipText(layer.getGeometrySet().getGeometriesCount() + " objetos");
	    //lblLayerName.setText((layer.getLayerConfig().getAlias().length()>20?layer.getLayerConfig().getAlias().substring(0, 20) + "...":layer.getLayerConfig().getAlias()) + " (" + layer.getGeometriesCount() + ")");
	    lblColor.setOpaque(true);
	    lblColor.setBackground(layer.getLayerConfig().getFillColor());
	    lblColor.setBorder(BorderFactory.createLineBorder(layer.getLayerConfig().getOutlineColor(), layer.getLayerConfig().getStyleConfig().getLineWidth()));
	    lblColor.setToolTipText("R: " + lblColor.getBackground().getRed() + " G: " + lblColor.getBackground().getGreen() + " B: " + lblColor.getBackground().getBlue());
	    //setToolTipText("Layer: " + layer.getLayerConfig().getName() + " - " + "R: " + lblColor.getBackground().getRed() + " G: " + lblColor.getBackground().getGreen() + " B: " + lblColor.getBackground().getBlue());
	}
	super.paint(_g);
    }

    public Layer getLayer() {
	return layer;
    }

    private void chkOn_actionPerformed(ActionEvent e) {
	if (layer != null) {
	    layer.setOn(chkOn.isSelected());
	}
	if (!layer.getLayerConfig().isOn()) {
	    layer.getLayerConfig().setActive(false);
	    chkActive.setSelected(false);
	}
	layerTree.setSelectedLayer(layer);
	layerTree.updateLayers();
	//layer.getLayerConfig().saveData();
    }

    private void chkActive_actionPerformed(ActionEvent e) {
	if (layer != null) {
	    layer.getLayerConfig().setActive(chkActive.isSelected());
	    if (layer.getLayerConfig().isActive()) {
		if (!layer.isOn()) {
		    layer.setOn();
		    chkOn.setSelected(true);
		} else {
		    layer.getLayerConfig().saveData();
		}
	    } else {
	        layer.getLayerConfig().saveData();
	    }
	}
	layerTree.setSelectedLayer(layer);
	layerTree.updateLayers();
    }

    private void btnReload_actionPerformed(ActionEvent e) {
	if (layer != null) {
	    if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
		layer.reload();
	    }
	    if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
		long time = System.currentTimeMillis();
	        layer.getGeometrySet().compressFile();
	        System.out.println("Compress file in " + (System.currentTimeMillis()-time));
	        time = System.currentTimeMillis();
	        layer.getGeometrySet().compressObject();
	        System.out.println("Compress object in " + (System.currentTimeMillis()-time));
	        time = System.currentTimeMillis();
	        layer.getGeometrySet().decompressFile();
	        System.out.println("DECompress file in " + (System.currentTimeMillis()-time));
	        time = System.currentTimeMillis();
	        System.out.println("DECompress object in " + (System.currentTimeMillis()-time));
	        layer.getGeometrySet().decompressObject();
	    }
	    layer.fetchValues();
	    layerTree.rebuildJTree();
	}
    }

    private void lblColor_mouseClicked(MouseEvent e) {
	if (layer != null) {
	    if (e.getButton() == e.BUTTON3) {
		Color _color = JColorChooser.showDialog(this, "Color de Relleno " + layer.getLayerConfig().getAlias(), layer.getLayerConfig().getFillColor());
		if (_color != null) {
		    layer.getLayerConfig().setFillColor(_color);
		}
	    } else if (e.getButton() == e.BUTTON1) {
		Color _color = JColorChooser.showDialog(this, "Color de Contorno " + layer.getLayerConfig().getAlias(), layer.getLayerConfig().getOutlineColor());
		if (_color != null) {
		    layer.getLayerConfig().setOutlineColor(_color);
		}
	    }
	}
	lblColor.setBackground(layer.getLayerConfig().getFillColor());
	lblColor.setBorder(BorderFactory.createLineBorder(layer.getLayerConfig().getOutlineColor(), layer.getLayerConfig().getStyleConfig().getLineWidth()));
	chkOn.setSelected(true);
	layer.setOn(true);
	lblColor.setToolTipText("R: " + lblColor.getBackground().getRed() + " G: " + lblColor.getBackground().getGreen() + " B: " + lblColor.getBackground().getBlue());
    }

    private void btnConfigure_actionPerformed(ActionEvent e) {
	if (layer != null) {
	    ExtendedInternalFrame _layerConfigInternalFrame = new ExtendedInternalFrame("Configuración del layer " + layer.getAlias());
	    LayerConfigPanel _layerConfigPanel = new LayerConfigPanel();
	    _layerConfigInternalFrame.setCentralPanel(_layerConfigPanel);
	    _layerConfigPanel.setLayer(layer);
	    _layerConfigInternalFrame.setClosable(true);
	    _layerConfigInternalFrame.setDesktop(Environment.getActiveDesktop());
	    _layerConfigInternalFrame.setIconifiable(false);
	    _layerConfigInternalFrame.show();
	}
    }
}
