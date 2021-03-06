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
	btnRule.setToolTipText("C??lculo de distancias y ??reas");
	//btnRule.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	btnRule.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnRule_actionPerformed(e);
			       }

			   }
	);
	//btnQuery.setBorderPainted(true);
	btnQuery.setToolTipText("Informaci??n de la entidad");
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
	btnAddresses.setToolTipText("Reasignar direcci??n");
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

	btnMultiQuery.setToolTipText("Informaci??n m??ltiple - Shift para configurar");
	btnMultiQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnMultiQuery_actionPerformed(e);
				}

			    }
	);

	btnFixedPolygonQuery.setToolTipText("Informaci??n m??ltiple dentro de un poligono");
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
