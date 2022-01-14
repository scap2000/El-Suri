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
 * MapBasicToolsPanel.java
 *
 * */
package org.digitall.common.geo.mapping.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import javax.swing.Timer;

import org.digitall.common.geo.mapping.BasicDrawEngine;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.icons.IconTypes;

public class MapBasicToolsPanel extends JPanel {

    public final static int ZOOM_IN_TOOL = 1;
    public final static int ZOOM_OUT_TOOL = 2;
    public final static int ZOOM_EXTENTS_TOOL = 3;
    public final static int RULE_TOOL = 4;
    public final static int QUERY_TOOL = 5;
    public final static int ADDRESSES_TOOL = 6;
    public final static int LAYER_EDITION_TOOL = 7;
    public final static int STREETS_EDITION_TOOL = 8;
    public final static int INFRASTRUCTURES_EDITION_TOOL = 9;
    public final static int PRINT_TOOL = 10;
    public final static int MULTIQUERY_TOOL = 11;
    public final static int FIXED_POLYGON_QUERY_TOOL = 12;

    private BasicButton btnZoomIn = new BasicButton(IconTypes.mapper_zoom_in);
    private BasicButton btnZoomOut = new BasicButton(IconTypes.mapper_zoom_out);
    private BasicButton btnZoomExtents = new BasicButton(IconTypes.mapper_zoom_extents);
    private BasicButton btnRule = new BasicButton(IconTypes.mapper_rule);
    private BasicButton btnQuery = new BasicButton(IconTypes.mapper_query);
    private BasicButton btnAddresses = new BasicButton(IconTypes.fileConfigIcon_On_22x22);
    private BasicButton btnEdition = new BasicButton(IconTypes.modify_16x16);
    private BasicButton btnStreets = new BasicButton(IconTypes.fileConfigIcon_On_22x22);
    private BasicButton btnInfrastructures = new BasicButton(IconTypes.fileConfigIcon_On_22x22);
    private BasicButton btnPrint = new BasicButton(IconTypes.print_16x16);
    private BasicButton btnMultiQuery = new BasicButton(IconTypes.mapper_query);
    private BasicButton btnFixedPolygonQuery = new BasicButton(IconTypes.mapper_query);
    private BasicButton btnOperation = btnZoomIn;
    private Color defaultBackgroundColor = btnOperation.getBackground();
    private Color selectedBackgroundColor = Color.orange;
    private BasicDrawEngine panel;
    private int btnQty = 0;
    private boolean horizontal = false;

    public MapBasicToolsPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//btnZoomIn.setBorderPainted(true);
	btnZoomIn.setToolTipText("Zoom in");
	//btnZoomIn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	setOpaque(false);
	btnZoomIn.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnZoomIn_actionPerformed(e);
				 }

			     }
	);
	//btnZoomOut.setBorderPainted(true);
	btnZoomOut.setToolTipText("Zoom out");
	//btnZoomOut.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnZoomOut.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnZoomOut_actionPerformed(e);
				  }

			      }
	);
	//btnZoomExtents.setBorderPainted(true);
	btnZoomExtents.setToolTipText("Zoom extents");
	//btnZoomExtents.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnZoomExtents.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnZoomExtents_actionPerformed(e);
				      }

				  }
	);
	//btnRule.setBorderPainted(true);
	btnRule.setToolTipText("Cálculo de distancias y áreas");
	//btnRule.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnRule.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnRule_actionPerformed(e);
			       }

			   }
	);
	//btnQuery.setBorderPainted(true);
	btnQuery.setToolTipText("Información de la entidad");
	//btnQuery.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnQuery_actionPerformed(e);
				}

			    }
	);
	//btnEdition.setBorderPainted(true);
	btnEdition.setToolTipText("Editar layer");
	//btnEdition.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnEdition.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnEditLayer_actionPerformed(e);
				}

			    }
	);

	//btnAddresses.setBorderPainted(true);
	btnAddresses.setToolTipText("Reasignar dirección");
	//btnAddresses.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnAddresses.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnReasignAddress_actionPerformed(e);
				}

			    }
	);


	//btnStreets.setBorderPainted(true);
	btnStreets.setToolTipText("Nombrar calles");
	//btnStreets.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnStreets.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnStreets_actionPerformed(e);
				  }

			      }
	);
	//btnInfrastructures.setBorderPainted(true);
	btnInfrastructures.setToolTipText("Infraestructura Urbana");
	//btnInfrastructures.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnInfrastructures.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnInfrastructures_actionPerformed(e);
					  }

				      }
	);

	btnPrint.setToolTipText("Imprimir Mapa de la vista actual");
	btnPrint.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnPrint_actionPerformed(e);
					  }

				      }
	);

	btnMultiQuery.setToolTipText("Información múltiple - Shift para configurar");
	btnMultiQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnMultiQuery_actionPerformed(e);
				}

			    }
	);

	btnFixedPolygonQuery.setToolTipText("Información múltiple dentro de un poligono");
	btnFixedPolygonQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnFixedPolygonQuery_actionPerformed(e);
				}

			    }
	);
    }

    private void setCurrentOperation(int _currentOperation, boolean _local) {
	btnZoomIn.setBackground(defaultBackgroundColor);
	btnZoomIn.setOpaque(false);
	btnZoomOut.setBackground(defaultBackgroundColor);
	btnZoomOut.setOpaque(false);
	btnZoomExtents.setBackground(defaultBackgroundColor);
	btnZoomExtents.setOpaque(false);
	btnRule.setBackground(defaultBackgroundColor);
	btnRule.setOpaque(false);
	btnQuery.setBackground(defaultBackgroundColor);
	btnQuery.setOpaque(false);
	btnAddresses.setBackground(defaultBackgroundColor);
	btnAddresses.setOpaque(false);
	btnEdition.setBackground(defaultBackgroundColor);
	btnEdition.setOpaque(false);
	btnStreets.setBackground(defaultBackgroundColor);
	btnStreets.setOpaque(false);
	btnInfrastructures.setBackground(defaultBackgroundColor);
	btnInfrastructures.setOpaque(false);
	btnPrint.setBackground(defaultBackgroundColor);
	btnPrint.setOpaque(false);
	btnMultiQuery.setBackground(defaultBackgroundColor);
	btnMultiQuery.setOpaque(false);
	btnFixedPolygonQuery.setBackground(defaultBackgroundColor);
	btnFixedPolygonQuery.setOpaque(false);
	
	if (_local) {
	    panel.setOperation(_currentOperation);
	}
	switch (_currentOperation) {
	    case BasicDrawEngineConfig.OPERATION_ZOOM_IN:
		btnOperation = btnZoomIn;
		break;
	    /*case BasicDrawEngineConfig.OPERATION_ZOOM_OUT:
		btnOperation = btnZoomOut;
		break;*/
	    case BasicDrawEngineConfig.OPERATION_ZOOM_EXTENTS:
		btnOperation = btnZoomExtents;
		break;
	    case BasicDrawEngineConfig.OPERATION_DISTANCE_AREA:
		btnOperation = btnRule;
		break;
	    case BasicDrawEngineConfig.OPERATION_QUERY:
		btnOperation = btnQuery;
		break;
	    case BasicDrawEngineConfig.OPERATION_ADDRESSES:
		btnOperation = btnAddresses;
		break;
	    case BasicDrawEngineConfig.OPERATION_EDITION:
		btnOperation = btnEdition;
		break;
	    case BasicDrawEngineConfig.OPERATION_STREETS:
		btnOperation = btnStreets;
		break;
	    case BasicDrawEngineConfig.OPERATION_INFRASTRUCTURES:
		btnOperation = btnInfrastructures;
		break;
	    case BasicDrawEngineConfig.OPERATION_PRINT:
		btnOperation = btnPrint;
		break;
	    case BasicDrawEngineConfig.OPERATION_MULTIQUERY:
		btnOperation = btnMultiQuery;
		break;
	    case BasicDrawEngineConfig.OPERATION_FIXED_POLYGON_QUERY:
		btnOperation = btnFixedPolygonQuery;
		break;
	    default:
		break;
	}
	btnOperation.setOpaque(true);
	btnOperation.setBackground(selectedBackgroundColor);
    }

    private void btnZoomIn_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_ZOOM_IN, true);
    }

    private void btnZoomOut_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_ZOOM_OUT, true);
    }

    private void btnZoomExtents_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_ZOOM_EXTENTS, true);
    }

    private void btnRule_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_DISTANCE_AREA, true);
    }

    private void btnQuery_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_QUERY, true);
    }

    private void btnMultiQuery_actionPerformed(ActionEvent e) {
	if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
	    setCurrentOperation(BasicDrawEngineConfig.OPERATION_CONFIGURE_REPORT, true);
	} else {
	    setCurrentOperation(BasicDrawEngineConfig.OPERATION_MULTIQUERY, true);
	}
    }

    private void btnFixedPolygonQuery_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_FIXED_POLYGON_QUERY, true);
    }

    private void btnReasignAddress_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_ADDRESSES, true);
    }

    private void btnEditLayer_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_EDITION, true);
    }

    public void setDrawPanel(BasicDrawEngine _panel) {
	panel = _panel;
	Timer paintOperationButtonTimer = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    if (panel != null) {
				setCurrentOperation(panel.getEngineConfig().getCurrentOperation(), false);
			    }
			}
		    });
	paintOperationButtonTimer.start();
    }

    private void btnStreets_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_STREETS, true);
    }

    private void btnInfrastructures_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_INFRASTRUCTURES, true);
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	setCurrentOperation(BasicDrawEngineConfig.OPERATION_PRINT, true);
    }
    
    public void setVertical() {
	horizontal = false;
	relocateComponents();
    }

    public void setHorizontal() {
	horizontal = true;
	relocateComponents();
    }

    public void addTool(int _tool) {
	switch (_tool) {
	    case ZOOM_IN_TOOL:
		add(btnZoomIn);
		break;
	    case ZOOM_OUT_TOOL:
		add(btnZoomOut);
		break;
	    case ZOOM_EXTENTS_TOOL:
		add(btnZoomExtents);
		break;
	    case RULE_TOOL:
		add(btnRule);
		break;
	    case QUERY_TOOL:
		add(btnQuery);
		break;
	    case ADDRESSES_TOOL:
		add(btnAddresses);
		break;
	    case LAYER_EDITION_TOOL:
		add(btnEdition);
		break;
	    case STREETS_EDITION_TOOL:
		add(btnStreets);
		break;
	    case INFRASTRUCTURES_EDITION_TOOL:
		add(btnInfrastructures);
		break;
	    case PRINT_TOOL:
	        add(btnPrint);
	        break;
	    case MULTIQUERY_TOOL:
	        add(btnMultiQuery);
	        break;
	    case FIXED_POLYGON_QUERY_TOOL:
	        add(btnFixedPolygonQuery);
	        break;
	}

	btnQty++;
	relocateComponents();
    }

    private void relocateComponents() {
	if (horizontal) {
	    this.setLayout(new GridLayout(1, btnQty));
	    this.setSize(new Dimension(32*btnQty, 40));
	} else {
	    this.setLayout(new GridLayout(btnQty, 1));
	    this.setSize(new Dimension(40,32*btnQty));
	}
    }

}
