package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ReempadronamientoMaterialesMgmt extends BasicPrimitivePanel {

    private BasicPanel jpBuscar = new BasicPanel();
    private BasicPanel panelCentro = new BasicPanel();
    private BasicPanel jpAsignarCuenta = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    
    private int[] sizeColumnList = { 726 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Materiales sin Cuentas Asignadas", dataRow) {
	@Override
	public void finishLoading() {
            controlBotones();
	}
    };

    private TFInput tfRecurso = new TFInput(DataTypes.STRING, "Recurso", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    
    private AcceptButton btnAsignarCuenta = new AcceptButton();
    private FindButton btnBuscar = new FindButton();
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);

    public ReempadronamientoMaterialesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(780, 512));
	this.setPreferredSize(new Dimension(780, 512));

        listPanel.setPreferredSize(new Dimension(400, 200));

        tfRecurso.setBounds(new Rectangle(200, 20, 365, 35));
        jpBuscar.setBounds(new Rectangle(0, 0, 750, 70));
	jpBuscar.setPreferredSize(new Dimension(1, 70));
	jpBuscar.setSize(new Dimension(780, 70));
        jpBuscar.add(btnBuscar, null);
        jpBuscar.add(tfRecurso, null);
        jpBuscar.setLayout(null);

        panelCentro.setLayout(new BorderLayout());
        panelCentro.add(listPanel, BorderLayout.CENTER);

        jpAsignarCuenta.setPreferredSize(new Dimension(1, 60));
        jpAsignarCuenta.setSize(new Dimension(780, 60));
        jpAsignarCuenta.add(tfFindAccount, null);
        jpAsignarCuenta.add(cbAccounting, null);
        jpAsignarCuenta.add(btnAsignarCuenta);
        jpAsignarCuenta.setLayout(null);

        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBounds(new Rectangle(0, 0, 780, 600));
        panelContenedor.setSize(new Dimension(780, 600));
        panelContenedor.setPreferredSize(new Dimension(780, 600));
        panelContenedor.add(jpBuscar, BorderLayout.NORTH);
        panelContenedor.add(panelCentro, BorderLayout.CENTER);

        panelContenedor.add(jpAsignarCuenta, BorderLayout.SOUTH);
        this.setContent(panelContenedor);
	btnBuscar.setBounds(new Rectangle(630, 30, 30, 25));
	btnBuscar.setSize(new Dimension(30, 25));
	btnBuscar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

        }  
	);
        cbAccounting.setBounds(new Rectangle(335, 15, 380, 35));
        tfFindAccount.setBounds(new Rectangle(115, 15, 195, 35));
        btnAsignarCuenta.setEnabled(false);
	btnAsignarCuenta.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnAsingarCuenta_actionPerformed(e);
					}

				    }
	);
	btnAsignarCuenta.setToolTipText("Asignar Cuenta a los Materiales seleccionados");
        btnAsignarCuenta.setBounds(new Rectangle(740, 30, 30, 20));

        jpBuscar.setBorder(BorderPanel.getBorderPanel("Buscar"));
        jpAsignarCuenta.setBorder(BorderPanel.getBorderPanel("Asignar Cuenta"));
	listPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
                    controlBotones();
		    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    }
		}

	    });
        
        tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {

                    public void keyReleased(KeyEvent e) {
                        if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                            loadComboAccounting();
                        }
                    }

                });
        setHeaderList();
        btnAsignarCuenta.setEnabled(false);
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Recurso"));
        
	listPanel.setParams(" personalfiles.getAllRecursosSinCuentas", "''", headerList);
    }

    public void refresh() {
	listPanel.refresh(tfRecurso.getStringForSQL());
    }

    private void btnFind_actionPerformed(ActionEvent e) {
        refresh();
    }
    
    private void btnAsingarCuenta_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    public boolean saveData() {
	String _selectedIDS = "";
        boolean _return = false;
	if (listPanel.getSelectedsIDSeparatedByComma().length() > 0 ) {
	    if (cbAccounting.getSelectedIndex() != -1) {
                if (Advisor.question("Asignar Cuenta","¿Está seguro de asignar la Cuenta " + cbAccounting.getSelectedItem() + " a los Materiales seleccionados?")) {
                    _selectedIDS = listPanel.getSelectedsIDSeparatedByComma();
                    if (LibSQL.getBoolean("resourcescontrol.setCuentaxRecursos","'" + _selectedIDS + "'," + cbAccounting.getSelectedValueRef())) {
                        refresh();
                        _return = true;
                    } else {
                        Advisor.messageBox("Ocurrió un error al grabar los datos",
                                           "Error");
                    }
                }
            } else {
                Advisor.messageBox("El combo \"Cuenta\" no debe estar vacío", "Datos Requeridos");
            }
	} else {
	    Advisor.messageBox("Debe seleccionar por lo menos un Material", "Datos Requeridos");
	}
        return _return;
    }
    
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione uno o varios Materiales, asigne su Cuenta y presione el botón Aceptar");
    }

    private void loadComboAccounting() {
        String param = "5000,'" + tfFindAccount.getString() + "'";
        cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByFilter", param));
    }
    
    private void controlBotones() {
        if (listPanel.getSelectedsID().size() > 0) {
            btnAsignarCuenta.setEnabled(true);
        } else {
            btnAsignarCuenta.setEnabled(false);
        }
    }
    
}
