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
 * StyleConfigPanel.java
 *
 * */
package org.digitall.common.geo.mapping.panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.inputpanels.FontChooser;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.mapping.classes.LayerConfig;
import org.digitall.lib.geo.mapping.classes.StyleConfig;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

import org.jfree.ui.StrokeSample;

public class StyleConfigPanel extends BasicPrimitivePanel {

    private BasicPanel centralPanel = new BasicPanel();
    private BasicLabel fillColor = new BasicLabel();
    private BasicLabel outlineColor = new BasicLabel();

    private BasicLabel colorSample = new BasicLabel();
    private BasicLabel colorSampleHelper = new BasicLabel();
    private BasicLabel mouseOverColorSample = new BasicLabel();
    private BasicLabel mouseOverColorSampleHelper = new BasicLabel();
    private BasicLabel selectedColorSample = new BasicLabel();
    private BasicLabel selectedColorSampleHelper = new BasicLabel();

    private CBInput cbFillPatern = new CBInput(0, "Patrón de Relleno", false);
    private CBInput cbSymbol = new CBInput(0, "Símbolo", false);
    private BasicLabel lblSymbol = new BasicLabel();

    private StrokeChooserPanel cbStrokeStyle = new StrokeChooserPanel(StrokeSamples.strokeSamples[0], StrokeSamples.strokeSamples);

    private JSlider slLineWidth = new JSlider();
    private BasicLabel lblLineWidthTitle = new BasicLabel();
    private BasicLabel lblLineWidthValue = new BasicLabel();

    private BasicLabel mouseOverColor = new BasicLabel();
    private BasicLabel mouseOverOutlineColor = new BasicLabel();
    private BasicLabel selectedColor = new BasicLabel();
    private BasicLabel selectedOutlineColor = new BasicLabel();
    private BasicLabel lblStyle = new BasicLabel();
    private BasicLabel lblColor = new BasicLabel();
    private BasicLabel lblMouseOver = new BasicLabel();
    private BasicLabel lblSelected = new BasicLabel();
    private BasicLabel lblFillColor = new BasicLabel();
    private BasicLabel lblColorSample = new BasicLabel();
    private BasicLabel lblMouseOverColorSample = new BasicLabel();
    private BasicLabel lblSelectedColorSample = new BasicLabel();
    private BasicLabel lblOutlineColor = new BasicLabel();
    private BasicLabel lblMouseOverFillColor = new BasicLabel();
    private BasicLabel lblMouseOverOutlineColor = new BasicLabel();
    private BasicLabel lblSelectedFillColor = new BasicLabel();
    private BasicLabel lblSelectedOutlineColor = new BasicLabel();

    private StyleConfig styleConfig;
    private LayerConfig layerConfig;
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();
    private GridBagLayout gridBagLayout3 = new GridBagLayout();

    private AcceptButton btnAccept = new AcceptButton();
    private ModifyButton btnFontSelector = new ModifyButton();
    private BasicLabel lblFontSample = new BasicLabel();

    private JSlider slTransparency = new JSlider();
    private BasicLabel lblTransparencyTitle = new BasicLabel();
    private BasicLabel lblTransparencyValue = new BasicLabel();
    private BasicLabel lblFontTitle = new BasicLabel();

    public StyleConfigPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(610, 447));
	outlineColor.setBounds(new Rectangle(395, 265, 65, 25));
	outlineColor.setOpaque(true);
	outlineColor.setBackground(Color.white);
	outlineColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	outlineColor.addMouseListener(new MouseAdapter() {

				   public void mouseClicked(MouseEvent e) {
				       outlineColor_mouseClicked(e);
				   }

			       }
	);
	cbFillPatern.setBounds(new Rectangle(10, 110, 215, 35));
	cbSymbol.setBounds(new Rectangle(10, 70, 590, 35));
	lblSymbol.setBounds(new Rectangle(535, 5, 65, 65));
	lblSymbol.setBackground(Color.white);
	lblSymbol.setOpaque(true);
	lblSymbol.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblSymbol.setHorizontalAlignment(SwingConstants.CENTER);
	cbStrokeStyle.setBounds(new Rectangle(10, 150, 215, 35));
	//cbStrokeStyle.setSComponentName("Estilo de línea");
	slLineWidth.setBounds(new Rectangle(10, 245, 130, 20));
	slLineWidth.setOpaque(false);
	slLineWidth.setValue(0);
	slLineWidth.setMajorTickSpacing(1);
	slLineWidth.setMinorTickSpacing(1);
	slLineWidth.setPaintTicks(true);
	slLineWidth.setMaximum(10);
	slLineWidth.setMinimum(1);
	lblLineWidthTitle.setText("Espesor:");
	lblLineWidthTitle.setBounds(new Rectangle(10, 225, 90, 15));
	lblLineWidthValue.setBounds(new Rectangle(105, 225, 35, 15));
	lblLineWidthValue.setHorizontalAlignment(SwingConstants.CENTER);
	fillColor.setBounds(new Rectangle(395, 235, 65, 25));
	fillColor.setOpaque(true);
	fillColor.setBackground(Color.white);
	fillColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	fillColor.addMouseListener(new MouseAdapter() {

				    public void mouseClicked(MouseEvent e) {
					fillColor_mouseClicked(e);
				    }

				}
	);
	colorSample.setOpaque(true);
	colorSample.setBackground(Color.white);
	colorSample.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	colorSampleHelper.setBounds(new Rectangle(535, 235, 65, 55));
	colorSampleHelper.setOpaque(true);
	colorSampleHelper.setBackground(Color.white);
	colorSampleHelper.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	colorSampleHelper.setLayout(gridBagLayout1);
	colorSampleHelper.setText("88");
	colorSampleHelper.setForeground(Color.black);
	colorSampleHelper.setFont(new Font("Lucida Sans", 1, 14));
	colorSampleHelper.setHorizontalAlignment(SwingConstants.CENTER);
	mouseOverColorSampleHelper.setBounds(new Rectangle(220, 330, 65, 55));
	mouseOverColorSampleHelper.setOpaque(true);
	mouseOverColorSampleHelper.setBackground(Color.white);
	mouseOverColorSampleHelper.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	mouseOverColorSampleHelper.setLayout(gridBagLayout2);

	selectedColorSampleHelper.setBounds(new Rectangle(535, 330, 65, 55));
	selectedColorSampleHelper.setOpaque(true);
	selectedColorSampleHelper.setBackground(Color.white);
	selectedColorSampleHelper.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	selectedColorSampleHelper.setLayout(gridBagLayout3);

	mouseOverColorSample.setBounds(new Rectangle(220, 295, 65, 55));
	mouseOverColorSample.setOpaque(true);
	mouseOverColorSample.setBackground(Color.white);
	mouseOverColorSample.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	selectedColorSample.setBounds(new Rectangle(220, 385, 65, 55));
	selectedColorSample.setOpaque(true);
	selectedColorSample.setBackground(Color.white);
	selectedColorSample.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	mouseOverColor.setBounds(new Rectangle(80, 330, 65, 25));
	mouseOverColor.setOpaque(true);
	mouseOverColor.setBackground(Color.white);
	mouseOverColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	mouseOverColor.addMouseListener(new MouseAdapter() {

				     public void mouseClicked(MouseEvent e) {
					 mouseOverColor_mouseClicked(e);
				     }

				 }
	);
	mouseOverOutlineColor.setBounds(new Rectangle(80, 360, 65, 25));
	mouseOverOutlineColor.setOpaque(true);
	mouseOverOutlineColor.setBackground(Color.white);
	mouseOverOutlineColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	mouseOverOutlineColor.addMouseListener(new MouseAdapter() {

					    public void mouseClicked(MouseEvent e) {
						mouseOverOutlineColor_mouseClicked(e);
					    }

					}
	);
	selectedColor.setBounds(new Rectangle(395, 330, 65, 25));
	selectedColor.setOpaque(true);
	selectedColor.setBackground(Color.white);
	selectedColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	selectedColor.addMouseListener(new MouseAdapter() {

				    public void mouseClicked(MouseEvent e) {
					selectedColor_mouseClicked(e);
				    }

				}
	);
	selectedOutlineColor.setBounds(new Rectangle(395, 360, 65, 25));
	selectedOutlineColor.setOpaque(true);
	selectedOutlineColor.setBackground(Color.white);
	selectedOutlineColor.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	selectedOutlineColor.addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       selectedOutlineColor_mouseClicked(e);
					   }

				       }
	);
	lblStyle.setText("Estilo de las geometrías:");
	lblStyle.setBounds(new Rectangle(125, 25, 225, 25));
	lblStyle.setHorizontalAlignment(SwingConstants.LEFT);
	lblStyle.setFont(new Font("Lucida Sans", 1, 15));
	lblColor.setText("Color de Relleno (sólo para polígonos)");
	lblColor.setBounds(new Rectangle(320, 215, 295, 14));
	lblColor.setHorizontalAlignment(SwingConstants.CENTER);
	lblMouseOver.setText("Geometrías señaladas con el puntero del ratón");
	lblMouseOver.setBounds(new Rectangle(5, 310, 295, 15));
	lblMouseOver.setHorizontalAlignment(SwingConstants.CENTER);
	lblSelected.setText("Geometrías seleccionadas");
	lblSelected.setBounds(new Rectangle(320, 310, 295, 14));
	lblSelected.setHorizontalAlignment(SwingConstants.CENTER);

	lblFillColor.setText("Relleno:");
	lblFillColor.setBounds(new Rectangle(325, 240, 65, 15));
	lblFillColor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblOutlineColor.setText("Contorno:");
	lblOutlineColor.setBounds(new Rectangle(325, 270, 65, 15));
	lblOutlineColor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblColorSample.setText("Ejemplo:");
	lblColorSample.setBounds(new Rectangle(470, 255, 55, 15));
	lblColorSample.setHorizontalAlignment(SwingConstants.RIGHT);
	lblMouseOverColorSample.setText("Ejemplo:");
	lblMouseOverColorSample.setBounds(new Rectangle(155, 350, 55, 15));
	lblMouseOverColorSample.setHorizontalAlignment(SwingConstants.RIGHT);
	lblSelectedColorSample.setText("Ejemplo:");
	lblSelectedColorSample.setBounds(new Rectangle(470, 350, 55, 15));
	lblSelectedColorSample.setHorizontalAlignment(SwingConstants.RIGHT);

	lblMouseOverFillColor.setText("Relleno:");
	lblMouseOverFillColor.setBounds(new Rectangle(10, 335, 65, 15));
	lblMouseOverFillColor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblMouseOverOutlineColor.setText("Contorno:");
	lblMouseOverOutlineColor.setBounds(new Rectangle(10, 365, 65, 15));
	lblMouseOverOutlineColor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblSelectedFillColor.setText("Relleno:");
	lblSelectedFillColor.setBounds(new Rectangle(325, 335, 65, 15));
	lblSelectedFillColor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblSelectedOutlineColor.setText("Contorno:");
	lblSelectedOutlineColor.setBounds(new Rectangle(325, 365, 65, 15));
	lblSelectedOutlineColor.setHorizontalAlignment(SwingConstants.RIGHT);

	slTransparency.setBounds(new Rectangle(170, 240, 130, 20));
	lblTransparencyValue.setBounds(new Rectangle(265, 225, 35, 15));
	lblTransparencyValue.setHorizontalAlignment(SwingConstants.CENTER);
	slTransparency.setOpaque(false);
	slTransparency.setValue(0);
	slTransparency.setMajorTickSpacing(10);
	slTransparency.setMinorTickSpacing(1);
	slTransparency.setSize(new Dimension(130, 20));
	slTransparency.setPaintTicks(true);
	slTransparency.setEnabled(true);
	cbFillPatern.autoSize();
	cbSymbol.autoSize();
	//cbStrokeStyle.autoSize();
	centralPanel.setLayout(null);
	centralPanel.add(lblFontSample, null);
	centralPanel.add(btnFontSelector, null);
	centralPanel.add(lblStyle, null);
	centralPanel.add(lblColor, null);
	centralPanel.add(lblMouseOver, null);
	centralPanel.add(lblSelected, null);
	centralPanel.add(lblFillColor, null);
	centralPanel.add(fillColor, null);
	colorSampleHelper.add(colorSample, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(11, 11, 12, 12), 38, 28));
	centralPanel.add(colorSampleHelper, null);
	mouseOverColorSampleHelper.add(mouseOverColorSample, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(11, 11, 12, 12), 38, 28));
	centralPanel.add(mouseOverColorSampleHelper, null);
	selectedColorSampleHelper.add(selectedColorSample, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(11, 11, 12, 12), 38, 28));
	centralPanel.add(selectedColorSampleHelper, null);
	centralPanel.add(lblColorSample, null);
	centralPanel.add(lblMouseOverColorSample, null);
	centralPanel.add(lblSelectedColorSample, null);
	centralPanel.add(lblOutlineColor, null);
	centralPanel.add(outlineColor, null);
	centralPanel.add(cbFillPatern, null);
	centralPanel.add(cbSymbol, null);
	centralPanel.add(cbStrokeStyle, null);
	centralPanel.add(lblLineWidthTitle, null);
	centralPanel.add(lblLineWidthValue, null);
	centralPanel.add(slLineWidth, null);
	centralPanel.add(lblMouseOverFillColor, null);
	centralPanel.add(mouseOverColor, null);
	centralPanel.add(lblMouseOverOutlineColor, null);
	centralPanel.add(mouseOverOutlineColor, null);
	centralPanel.add(lblSelectedFillColor, null);
	centralPanel.add(selectedColor, null);
	centralPanel.add(lblSelectedOutlineColor, null);
	centralPanel.add(selectedOutlineColor, null);
	centralPanel.add(lblTransparencyTitle, null);
	centralPanel.add(lblFontTitle, null);
	centralPanel.add(lblTransparencyValue, null);
	centralPanel.add(slTransparency, null);
	centralPanel.add(lblSymbol, null);
	this.add(centralPanel, BorderLayout.CENTER);
	btnAccept.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnAccept_actionPerformed(e);
				}

			    }
	);
	btnFontSelector.setBounds(new Rectangle(500, 125, 80, 60));
	btnFontSelector.setToolTipText("Modificar estilo de fuente");
	btnFontSelector.setText("<html><p align=center>Seleccionar</html>");
	btnFontSelector.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnFontSelector_actionPerformed(e);
		    }

		});
	lblFontSample.setBounds(new Rectangle(270, 145, 225, 40));
	this.addButton(btnAccept);
	
	slLineWidth.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
		lblLineWidthValue.setText(String.valueOf(source.getValue()));
		updateSamples();
	    }
	
	});
	lblFontSample.setText("ABC#$%789");

	lblFontSample.setHorizontalAlignment(SwingConstants.CENTER);
	lblFontSample.setForeground(Color.black);
	lblFontSample.setOpaque(true);
	lblFontSample.setBackground(Color.white);
	lblFontSample.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	slTransparency.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		lblTransparencyValue.setText(String.valueOf(100-source.getValue()));
		updateSamples();
	    }
	
	});

	lblTransparencyTitle.setText("Transparencia:");
	lblTransparencyTitle.setBounds(new Rectangle(170, 225, 90, 15));
	
	lblFontTitle.setText("Tipo de letra:");
	lblFontTitle.setBounds(new Rectangle(270, 125, 90, 15));

	lblFontSample.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
		    styleConfig.setLabelColor(styleConfig.getOutlineColor());
		    lblFontSample.setForeground(styleConfig.getOutlineColor());
		}
                    lblFontSample_mouseClicked(e);
                }
	});

	cbSymbol.loadJCombo("tabs.getallinfrastructuretypes", "");

	cbStrokeStyle.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		   updateSamples();
		}
	    }
	});

	cbSymbol.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		   updateSymbol();
		}
	    }
	});

	cbSymbol.getCombo().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
			BasicDrawEngineConfig.loadSymbolsVector();
		}
	    }
	});

	lblSymbol.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	        if ( ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		    JFileChooser chooser = new JFileChooser(Environment.cfg.getProperty("SymbolsDirectory") + File.separator);
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int returnVal = chooser.showSaveDialog(chooser.getParent());
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        // IF Directory Selected
			Environment.cfg.setProperty("SymbolsDirectory", chooser.getSelectedFile().getAbsolutePath());
			String[] _fileList = chooser.getSelectedFile().list();
			String _groupName = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Nombre del Grupo", "Nombre del Grupo", JOptionPane.QUESTION_MESSAGE, null, null, "");
			if (_groupName != null) {
			//    Vector<String> list = new Vector<String>();
			    //agregar acá los iconos
			    //list.add("/tmp/icons/icono.png");
			    //String _symbolsDirectory = ""; //getPath();
			    for (int i = 0; i < _fileList.length; i++) {
			        String _fileName = _fileList[i];
			        try {
			            int idType = -1;
			            File symbolFile = new File(chooser.getSelectedFile() + File.separator + _fileName);
			            if (symbolFile != null) {
			                //LIMITES: WIDTH: 256, HEIGHT: 256, LENGTH: 64Kb (65535 bytes);
			                if (symbolFile.length() < 65535) {
			                    BufferedImage symbolImage = ImageIO.read(symbolFile);
			                    //System.out.println("Width, height: " + img.getWidth() + "," + img.getHeight());
			                    if ((symbolImage.getWidth() <= 100) && (symbolImage.getHeight() <= 100)) {
			                        int _result = -1;
			                        String params = idType + ",'" + _groupName + "-->" +  _fileName + "'";
			                        _result = LibSQL.getInt("tabs.setInfrastructureType", params);
			                        if (_result == -1) {
			                            Advisor.messageBox("Error al grabar los datos", "Error");
			                        } else {
			                            idType = _result;
			                            if (symbolImage != null) {
			                                if (!LibIMG.saveImage(symbolImage, "tabs.infrastructuretype_tabs", "symbol", "WHERE idtype = " + idType)) {
			                                    Advisor.messageBox("Error al grabar la imagen", "Error");
			                                }
			                            }
			                        }
			                    } else {
			                        Advisor.messageBox(_fileName + "\nTamaño de imagen no permitido.\nMáximo " + 100 + "x" + 100 + " píxeles", "Error");
			                    }
			                } else {
			                    Advisor.messageBox(_fileName + "\nTamaño de archivo no permitido.\nMáximo: " + (65535 / 1024.0) + " Kbytes", "Error");
			                }
			            }
			        } catch (IOException x) {
			            Advisor.messageBox("Error al leer el archivo " + _fileName, "Error");
			        }
			    }
			    //LibSQL.exQuery("UPDATE tabs.infrastructuretype_tabs SET name = replace(name,'" + _symbolsDirectory + "','" + _groupName + "');");
			} else {
			    Advisor.messageBox("El nombre del grupo no puede estar vacío", "Error");
			}
		    }
		}
	    }
	});

    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void fillColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setFillColor(pickColor(styleConfig.getFillColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setFillColor(null);
	}
	updateSamples();
    }

    private void outlineColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setOutlineColor(pickColor(styleConfig.getOutlineColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setOutlineColor(null);
	}
	updateSamples();
    }

    private void mouseOverColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setMouseOverColor(pickColor(styleConfig.getMouseOverColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setMouseOverColor(null);
	}
	updateSamples();
    }

    private void mouseOverOutlineColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setMouseOverOutlineColor(pickColor(styleConfig.getMouseOverOutlineColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setMouseOverOutlineColor(null);
	}
	updateSamples();
    }

    private void selectedColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setSelectedColor(pickColor(styleConfig.getSelectedColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setSelectedColor(null);
	}
	updateSamples();
    }

    private void selectedOutlineColor_mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    styleConfig.setSelectedOutlineColor(pickColor(styleConfig.getSelectedOutlineColor()));
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    styleConfig.setSelectedOutlineColor(null);
	}
	updateSamples();
    }

    public void setStyleConfig(StyleConfig _styleConfig) {
        styleConfig = _styleConfig;
        loadData();
    }
    
    public void setLayerConfig(LayerConfig _layerConfig) {
        layerConfig = _layerConfig;
        loadData();
    }
    
    private void updateSamples() {

	styleConfig.setFillColor(getAlphaColor(styleConfig.getFillColor()));
	styleConfig.setLineWidth(slLineWidth.getValue());

	fillColor.setBackground(styleConfig.getFillColor());
	outlineColor.setBackground(styleConfig.getOutlineColor());
	colorSample.setBackground(getAlphaColor(styleConfig.getFillColor()));
	colorSample.setBorder(BorderFactory.createLineBorder(styleConfig.getOutlineColor(), slLineWidth.getValue()));
	
	mouseOverColor.setBackground(styleConfig.getMouseOverColor());
	mouseOverOutlineColor.setBackground(styleConfig.getMouseOverOutlineColor());
	mouseOverColorSample.setBackground(styleConfig.getMouseOverColor());
	mouseOverColorSample.setBorder(BorderFactory.createLineBorder(styleConfig.getMouseOverOutlineColor(), slLineWidth.getValue()));

	selectedColor.setBackground(styleConfig.getSelectedColor());
	selectedOutlineColor.setBackground(styleConfig.getSelectedOutlineColor());
	selectedColorSample.setBackground(styleConfig.getSelectedColor());
	selectedColorSample.setBorder(BorderFactory.createLineBorder(styleConfig.getSelectedOutlineColor(), slLineWidth.getValue()));

	//styleConfig.setStrokeStyle(cbStrokeStyle.getCombo().getSelectedIndex());
	updateUI();
    }

    private void loadData() {
	slLineWidth.setValue(styleConfig.getLineWidth());
	slTransparency.setValue(styleConfig.getTransparency());
	lblLineWidthValue.setText(String.valueOf(styleConfig.getLineWidth()));
	cbFillPatern.setSelectedID(styleConfig.getFillPatern());
	cbSymbol.setSelectedID(styleConfig.getSymbol());
	fillColor.setBackground(styleConfig.getFillColor());
	mouseOverColor.setBackground(styleConfig.getMouseOverColor());
	mouseOverOutlineColor.setBackground(styleConfig.getMouseOverOutlineColor());
	outlineColor.setBackground(styleConfig.getOutlineColor());
	selectedColor.setBackground(styleConfig.getSelectedColor());
	selectedOutlineColor.setBackground(styleConfig.getSelectedOutlineColor());
	cbStrokeStyle.getCombo().setSelectedIndex(styleConfig.getStrokeStyle());
	lblFontSample.setFont(styleConfig.getLabelFont());
	lblFontSample.setForeground(styleConfig.getLabelColor());
	updateSamples();
    }

    public boolean saveData() {
	styleConfig.setFillPatern(cbFillPatern.getSelectedIndex());
	styleConfig.setLineWidth(slLineWidth.getValue());
	styleConfig.setTransparency(slTransparency.getValue());
	styleConfig.setStrokeStyle(cbStrokeStyle.getCombo().getSelectedIndex());
	styleConfig.setSymbol(new Integer(cbSymbol.getSelectedValue().toString()));
        layerConfig.saveData();
	getParentInternalFrame().close();
	return true;
    }

    private Color pickColor(Color _color) {
	Color _pickColor = JColorChooser.showDialog(this, "Seleccione el nuevo color", _color);
	if (_pickColor != null) {
	    return getAlphaColor(_pickColor);
	} else {
	    return null;//getAlphaColor(_color);
	}
    }

    private void btnFontSelector_actionPerformed(ActionEvent e) {
	FontChooser _fontChooser = new FontChooser(styleConfig.getLabelFont(), styleConfig.getLabelColor());
	_fontChooser.setVisible(true);
	styleConfig.setLabelFont(_fontChooser.getNewFont());
	styleConfig.setLabelColor(_fontChooser.getNewColor());
	lblFontSample.setFont(styleConfig.getLabelFont());
	lblFontSample.setForeground(styleConfig.getLabelColor());
    }

    private Color getAlphaColor(Color _color) {
	return _color!=null?new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), slTransparency.getValue()*255/100):null;
    }

    private void updateSymbol() {
	if (cbSymbol.getSelectedIndex() != -1) {
	    BufferedImage _symbol = BasicDrawEngineConfig.getSymbol(new Integer(cbSymbol.getSelectedValue().toString()));
	    if (_symbol != null) {
	        lblSymbol.setIcon(new ImageIcon(_symbol));
	    } else {
	        lblSymbol.setIcon(null);
	    }
	}
    }

    private void lblFontSample_mouseClicked(MouseEvent e) {
        FontChooser _fontChooser = new FontChooser(styleConfig.getLabelFont(), styleConfig.getLabelColor());
        _fontChooser.setVisible(true);
        styleConfig.setLabelFont(_fontChooser.getNewFont());
        styleConfig.setLabelColor(_fontChooser.getNewColor());
        lblFontSample.setFont(styleConfig.getLabelFont());
        lblFontSample.setForeground(styleConfig.getLabelColor());
    }
}
