package org.digitall.apps.taxes.interfases.contribution;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import org.digitall.apps.taxes.classes.AlicuotaContribucion;
import org.digitall.apps.taxes.classes.BoletaContribucion;
import org.digitall.apps.taxes.classes.DetalleBoletaContribucion;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ContributionsMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpEncabezadoBoleta = new BasicPanel();

    private int[] sizeColumnList = { 520, 96 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Detalle de la Boleta", dataRow) {
        @Override
        public void calculate() {
            tfMontoTotal.setValue(listPanel.getSum(2)); 
        }

        @Override
        public void finishLoading() {
            btnGrabarBoleta.setEnabled(hasItems());
        }
    };

    private AssignButton btnRegistrarEncabezadoBoleta = new AssignButton(true);
    private SaveDataButton btnGrabarBoleta = new SaveDataButton();
    private CloseButton btnAnularBoleta = new CloseButton();
    private AssignButton btnAgregarItem = new AssignButton(true);
    private DeleteButton btnBorrarItem = new DeleteButton();
    private JButton btnVerTextoOrdenado = new JButton();

    private TFInput tfNumeroComprobante = new TFInput(DataTypes.STRING, "Comprobante N??", false);

    private TFInput tfBuscarContribucion = new TFInput(DataTypes.STRING, "Buscar Contribuci??n", false);
    private TFInput tfBuscarAlicuota = new TFInput(DataTypes.STRING, "Buscar Al??cuota", false);
    private TFInput tfBuscarArtTasa = new TFInput(DataTypes.STRING, "Buscar Art??culo/Tasa", false);

    private TFInput tfConcepto = new TFInput(DataTypes.STRING, "Concepto", true);
    private TFInput tfBuscarContribuyente = new TFInput(DataTypes.STRING, "Buscar Contribuyente", false);
    private TFInput tfMultiplicador = new TFInput(DataTypes.DOUBLE, "Multiplicador", false);
    private TFInput tfValorModulo = new TFInput(DataTypes.MONEY, "Valor del M??dulo", false);
    private TFInput tfMontoBase = new TFInput(DataTypes.MONEY, "Monto Base", false);
    private TFInput tfPorcentaje = new TFInput(DataTypes.PERCENT, "Porcentaje", false);
    private TFInput tfMontoFijo = new TFInput(DataTypes.MONEY, "Monto Fijo", false);
    private TFInput tfMontoAlicuota = new TFInput(DataTypes.MONEY, "Monto Al??cuota", false);
    
    private JMoneyEntry tfMontoTotal = new JMoneyEntry();
    
    private CBInput cbContribuyentes = new CBInput(0, "Contribuyente (DNI/CUIT/CUIL)", true) {
	@Override
	public void performAddButtonAction() {
	    agregarContribuyente();
	}
    };
    private CBInput cbContribucion = new CBInput(0,"Contribuci??n",false);
    private CBInput cbAlicuotaContribucion = new CBInput(0,"Al??cuota",false);
    private CBInput cbArticuloTasa = new CBInput(0,"Art??culo/Tasa",true);
    
    private BasicRadioButton rbtnMultiplicador = new BasicRadioButton();
    private BasicRadioButton rbtnPorcentaje = new BasicRadioButton();
    private BasicRadioButton rbtnMontoFijo = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();

    private BasicLabel lblMontoTotal = new BasicLabel();
    
    private AlicuotaContribucion alicuotaContribucion;
    private BoletaContribucion boletaContribucion;
    private DetalleBoletaContribucion detalleBoletaContribucion;

    public ContributionsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(675, 536));
	this.setTitle("Contribuciones");
        content.setLayout(null);
        content.setSize(new Dimension(675, 532));
        listPanel.setPreferredSize(new Dimension(665, 220));
	listPanel.setBounds(new Rectangle(0, 340, 665, 145));
	listPanel.setBounds(new Rectangle(2, 335, 665, 145));
	jpEncabezadoBoleta.setBorder(BorderPanel.getBorderPanel("Boleta Contribuci??n"));
        jpEncabezadoBoleta.setLayout(null);
	btnAgregarItem.setBounds(new Rectangle(636, 305, 30, 25));
	btnAgregarItem.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAgregarItem_actionPerformed(e);
		}

	    }
	);
        btnAgregarItem.setToolTipText("Registrar Art??culo/Tasa");
        jpEncabezadoBoleta.add(tfMontoAlicuota, null);
        jpEncabezadoBoleta.add(btnRegistrarEncabezadoBoleta, null);
        jpEncabezadoBoleta.add(tfNumeroComprobante, null);
        jpEncabezadoBoleta.add(tfBuscarContribuyente, null);
        jpEncabezadoBoleta.add(cbContribuyentes, null);
        jpEncabezadoBoleta.add(tfConcepto, null);
        jpEncabezadoBoleta.add(tfBuscarArtTasa, null);
        jpEncabezadoBoleta.add(cbArticuloTasa, null);
        jpEncabezadoBoleta.add(btnVerTextoOrdenado, null);
        jpEncabezadoBoleta.add(tfMontoFijo, null);
        jpEncabezadoBoleta.add(rbtnMontoFijo, null);
        jpEncabezadoBoleta.add(rbtnPorcentaje, null);
        jpEncabezadoBoleta.add(rbtnMultiplicador, null);
        jpEncabezadoBoleta.add(tfPorcentaje, null);
        jpEncabezadoBoleta.add(tfMontoBase, null);
        jpEncabezadoBoleta.add(tfValorModulo, null);
        jpEncabezadoBoleta.add(tfMultiplicador, null);
        jpEncabezadoBoleta.add(cbAlicuotaContribucion, null);
        jpEncabezadoBoleta.add(cbContribucion, null);
        jpEncabezadoBoleta.add(tfBuscarContribucion, null);
        jpEncabezadoBoleta.add(tfBuscarAlicuota, null);
        jpEncabezadoBoleta.add(btnAgregarItem, null);
        content.add(jpEncabezadoBoleta, null);
        content.add(listPanel, null);
	content.add(lblMontoTotal, null);
	content.add(tfMontoTotal, null);
        content.add(btnGrabarBoleta, null);
        content.add(btnAnularBoleta, null);
        content.add(btnBorrarItem, null);
        setContent(content);
        tfConcepto.setBounds(new Rectangle(10, 60, 625, 35));
        btnAnularBoleta.setBounds(new Rectangle(635, 495, 30, 25));
        btnAnularBoleta.setPreferredSize(new Dimension(30, 25));
        btnAnularBoleta.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAnularBoleta_actionPerformed(e);
		}

	    }
	);
        btnAnularBoleta.setToolTipText("Anular boleta");
        btnBorrarItem.setToolTipText("Borrar el ??tem seleccionado");
	btnBorrarItem.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnBorrarItem_actionPerformed(e);
		}

	    }
	);
	setHeaderList();
	tfBuscarContribucion.setBounds(new Rectangle(10, 25, 130, 35));
	cbContribuyentes.setBounds(new Rectangle(275, 20, 390, 35));
	tfBuscarAlicuota.setBounds(new Rectangle(10, 65, 130, 35));
	cbContribucion.setBounds(new Rectangle(155, 20, 470, 35));
	cbAlicuotaContribucion.setBounds(new Rectangle(155, 60, 470, 35));
	
	tfBuscarContribucion.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboContribucion(tfBuscarContribucion.getStringForSQL());
		    }
		}

	    }
	);
        tfBuscarContribuyente.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboContribuyentes(tfBuscarContribuyente.getStringForSQL());
                    }
                }

            }
        );
	tfBuscarAlicuota.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
		        loadComboAlicuota(tfBuscarAlicuota.getStringForSQL());
		    }
		}

	    }
	);
        tfBuscarArtTasa.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboArticuloTasa(tfBuscarArtTasa.getStringForSQL());
                    }
                }

            }
        );
        tfMontoBase.getTextField().addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e) {
		    calcularMontoAlicuota();
                }

            }
        );
        tfMultiplicador.getTextField().addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e) {
		    calcularMontoAlicuota();
                }

            }
        );
        tfPorcentaje.getTextField().addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e) {
		    calcularMontoAlicuota();
                }

            }
        );
        tfMontoFijo.getTextField().addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e) {
		    calcularMontoAlicuota();
                }

            }
        );
        tfConcepto.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        registrarEncabezadoBoleta();
                    }
                }

            }
        );
	cbContribucion.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        loadComboAlicuota(tfBuscarAlicuota.getStringForSQL());
		    }
		}
	    }
	);
	cbAlicuotaContribucion.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        loadDataAlicuota();
		    }
		}
	    }
	);
        rbtnMultiplicador.setText("Multiplicador");
        rbtnMultiplicador.setBounds(new Rectangle(10, 225, 105, 15));
        rbtnPorcentaje.setText("Porcentaje");
        rbtnPorcentaje.setBounds(new Rectangle(150, 225, 95, 15));
        rbtnPorcentaje.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    rbtnPorcentaje_itemStateChanged(e);
                }
            });
        rbtnMontoFijo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    rbtnMontoFijo_itemStateChanged(e);
                }
            });
        rbtnMontoFijo.setText("Monto Fijo");
        rbtnMontoFijo.setBounds(new Rectangle(295, 225, 95, 15));
        tfMontoFijo.setBounds(new Rectangle(300, 250, 105, 35));
        grupo.add(rbtnMultiplicador);
        grupo.add(rbtnPorcentaje);
        grupo.add(rbtnMontoFijo);
        rbtnMultiplicador.setSelected(true);
        rbtnMultiplicador.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    rbtnMultiplicador_itemStateChanged(e);
                }
            });
        tfMultiplicador.setBounds(new Rectangle(10, 250, 105, 35));
	tfValorModulo.setBounds(new Rectangle(10, 290, 105, 35));
	tfMontoBase.setBounds(new Rectangle(155, 250, 105, 35));
        tfMontoBase.getTextField().addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    calcularMontoAlicuota();
                }
            });
        tfMultiplicador.getTextField().addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    calcularMontoAlicuota();
                }
            });
        tfPorcentaje.getTextField().addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    calcularMontoAlicuota();
                }
            });
        tfMontoFijo.getTextField().addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    calcularMontoAlicuota();
                }
            });
        tfPorcentaje.setBounds(new Rectangle(155, 290, 105, 35));
	tfBuscarContribuyente.setBounds(new Rectangle(130, 20, 130, 35));
	tfValorModulo.setEnabled(false);
        tfBuscarArtTasa.setBounds(new Rectangle(10, 105, 130, 35));
        tfNumeroComprobante.setBounds(new Rectangle(10, 20, 110, 35));
        tfMontoTotal.setBounds(new Rectangle(455, 495, 125, 25));
        tfMontoTotal.setFont(new Font("Dialog", 1, 15));
        lblMontoTotal.setText("Monto Total:");
        lblMontoTotal.setBounds(new Rectangle(345, 495, 110, 25));
        lblMontoTotal.setFont(new Font("Lucida Sans", 1, 15));
        tfMontoAlicuota.setBounds(new Rectangle(500, 290, 135, 35));
        tfMontoAlicuota.setEditable(false);
        btnGrabarBoleta.setBounds(new Rectangle(600, 495, 30, 25));
        btnGrabarBoleta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnGrabarBoleta_actionPerformed(e);
                }
            });
        btnGrabarBoleta.setToolTipText("Grabar Boleta");
        btnRegistrarEncabezadoBoleta.setBounds(new Rectangle(639, 75, 25, 25));
        btnRegistrarEncabezadoBoleta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRegistrarEncabezadoBoleta_actionPerformed(e);
                }
            });
        btnRegistrarEncabezadoBoleta.setToolTipText("Agregar una nueva Boleta");
        btnBorrarItem.setBounds(new Rectangle(5, 495, 30, 25));
        cbArticuloTasa.setBounds(new Rectangle(155, 105, 500, 35));
        tfBuscarArtTasa.setBounds(new Rectangle(10, 180, 130, 35));
	cbContribucion.setBounds(new Rectangle(155, 100, 480, 35));
	cbAlicuotaContribucion.setBounds(new Rectangle(155, 140, 480, 35));
	cbArticuloTasa.setBounds(new Rectangle(155, 180, 510, 35));
	tfNumeroComprobante.setBounds(new Rectangle(10, 20, 110, 35));
        jpEncabezadoBoleta.setBounds(new Rectangle(0, 0, 670, 335));
        btnVerTextoOrdenado.setText("<html><p align=center>Ver texto<br><p align=center>ordenado</html>");
        btnVerTextoOrdenado.setToolTipText("Ver el texto ordenado de la Al??cuota seleccionada");
        btnVerTextoOrdenado.setSize(new Dimension(145, 45));
        btnVerTextoOrdenado.setPreferredSize(new Dimension(30, 20));
        btnVerTextoOrdenado.setOpaque(true);
        btnVerTextoOrdenado.setFont(new Font("Dialog", 1, 10));
        btnVerTextoOrdenado.setForeground(Color.white);
        btnVerTextoOrdenado.setBackground(new Color(165, 41, 0));
        btnVerTextoOrdenado.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        btnVerTextoOrdenado.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnVerArticulo_actionPerformed(e);
                                }

                            }
        );
        cbContribuyentes.getAddButton().setToolTipText("Agregar Contribuyente");
        tfBuscarContribuyente.setBounds(new Rectangle(135, 20, 130, 35));
        btnVerTextoOrdenado.setEnabled(false);
        btnVerTextoOrdenado.setBounds(new Rectangle(530, 230, 105, 35));
        tfBuscarContribucion.setBounds(new Rectangle(10, 100, 130, 35));
        tfBuscarAlicuota.setBounds(new Rectangle(10, 140, 130, 35));
        tfValorModulo.setValue(LibSQL.getDouble("taxes.getvalormodulo", "''"));
	
        listPanel.removeControls();
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        loadSelectedObject();
                    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

                    }
                }
            }
        );
        tfMontoTotal.setEditable(false);
        tfMontoTotal.setUneditableForegroundColor(Color.white);
        tfMontoTotal.setUneditableBackgroundColor(Color.red);
        setEnabledEncabezado(true);
        setEnabledDetalle(false);
        tfNumeroComprobante.setEditable(false);
        tfNumeroComprobante.setVisible(false);
        btnGrabarBoleta.setEnabled(false);
        btnBorrarItem.setEnabled(false);
        loadComboContribuyentes(tfBuscarContribuyente.getStringForSQL());
        loadComboContribucion(tfBuscarContribucion.getStringForSQL());
        restart();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Complete los datos de la Boleta y presione el bot??n \"Grabar Boleta\"");
    }
    
    private void setEnabledEncabezado(boolean _valor) {
        tfBuscarContribuyente.setEnabled(_valor);
        cbContribuyentes.setEnabled(_valor);
	tfConcepto.setEnabled(_valor);
        btnRegistrarEncabezadoBoleta.setEnabled(_valor);
    }
    
    private void setEnabledDetalle(boolean _valor) {
        tfBuscarContribucion.setEnabled(_valor);
        cbContribucion.setEnabled(_valor);
        tfBuscarAlicuota.setEnabled(_valor);
        cbAlicuotaContribucion.setEnabled(_valor);
        tfBuscarArtTasa.setEnabled(_valor);
        cbArticuloTasa.setEnabled(_valor);
        tfMultiplicador.setEnabled(_valor);
        tfPorcentaje.setEnabled(_valor);
        tfMontoBase.setEnabled(_valor);
        tfMontoFijo.setEnabled(_valor);
        rbtnMultiplicador.setEnabled(_valor);
        rbtnPorcentaje.setEnabled(_valor);
        rbtnMontoFijo.setEnabled(_valor);
        btnVerTextoOrdenado.setEnabled(_valor);
        btnAgregarItem.setEnabled(_valor);
        btnAnularBoleta.setEnabled(_valor);
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("Art??culo/Tasa");
        headerList.addElement("($) Monto");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        
        listPanel.setParams("taxes.getAllDetalleBoletaContribucion", "-1", headerList);
    }
    
    /**  Funci??n que reinicia la carga de datos */
    private void restart() {
        boletaContribucion = new BoletaContribucion();
        detalleBoletaContribucion = new DetalleBoletaContribucion();
        Environment.removeUnfinishedTask(getParentInternalFrame());
        tfMontoBase.setValue(0.0);
        tfMontoFijo.setValue(0.0);
        tfMultiplicador.setValue(0.0);
        tfPorcentaje.setValue(0.0);
        tfMontoAlicuota.setValue(0.0);
        if (boletaContribucion.getIdBoletaContribucion() == -1) {
            tfNumeroComprobante.setValue("");
            tfNumeroComprobante.setVisible(false);
            tfConcepto.setValue("");
            setEnabledEncabezado(true);
            setEnabledDetalle(false);
        }
        habilitarComponentes();
    }
    
    private void habilitarComponentes() {
        if (boletaContribucion.getIdBoletaContribucion() != -1 ) {
            if (rbtnMultiplicador.isSelected()) {
                tfMultiplicador.setEnabled(true);
                tfMontoBase.setEnabled(false);
                tfPorcentaje.setEnabled(false);
                tfMontoFijo.setEnabled(false);
            } else if (rbtnPorcentaje.isSelected()) {
                tfMultiplicador.setEnabled(false);
                tfMontoBase.setEnabled(true);
                tfPorcentaje.setEnabled(true);
                tfMontoFijo.setEnabled(false);
            } else {
                tfMultiplicador.setEnabled(false);
                tfMontoBase.setEnabled(false);
                tfPorcentaje.setEnabled(false);
                tfMontoFijo.setEnabled(true);
            }
        } else {
            setEnabledEncabezado(true);
            setEnabledDetalle(false);
        }
    }
    
    private void loadSelectedObject(){
        if (!dataRow.isEmpty()){
            detalleBoletaContribucion = new DetalleBoletaContribucion();
            detalleBoletaContribucion.setIdDetalleBoletaContribucion(Integer.parseInt("" + dataRow.elementAt(0)));
            btnBorrarItem.setEnabled(true);
        } else {
            btnBorrarItem.setEnabled(false);
        }
    }
    
    private void btnBorrarItem_actionPerformed(ActionEvent e) {
        if (Advisor.question("Borrar ??tem","??Est?? seguro de borrar el ??tem seleccionado?")) {
            if (detalleBoletaContribucion.delete()) {
                refresh();
            } else  {
                Advisor.messageBox("Ocurri?? un error, no se pudo borrar el ??tem seleccionado", "Error");
            }
        }
    }
    
    private void btnAnularBoleta_actionPerformed(ActionEvent e) {
        if (boletaContribucion.getIdBoletaContribucion() != -1) {
            if (Advisor.question("Anular boleta","??Est?? seguro de anular la registraci??n de\nla Boleta N?? "+ boletaContribucion.getAnio() +"-"+ boletaContribucion.getNumeroFormateado() +"?")) {
                if (boletaContribucion.delete()) {
                    restart();
                    refresh();
                } else {
                    Advisor.messageBox("Ocurri?? un error, no se pudo borrar la boleta seleccionada","Error");
                }
            }
        }
    }
    
    private void btnAgregarItem_actionPerformed(ActionEvent e) {
        if (listPanel.getItemCount() < 8) {     // Contro que no permite cargar m??s de 8 ??tmes (l??mite del reporte)
            if (tfMontoAlicuota.getAmount() > 0)  {
                detalleBoletaContribucion = new DetalleBoletaContribucion();
                detalleBoletaContribucion.setIdBoletaContribucion(boletaContribucion.getIdBoletaContribucion());
                detalleBoletaContribucion.setAnio(boletaContribucion.getAnio());
                detalleBoletaContribucion.setNumero(boletaContribucion.getNumero());
                detalleBoletaContribucion.setIdContribucion(Integer.parseInt(cbContribucion.getSelectedValue().toString()));
                detalleBoletaContribucion.setNombreContribucion(cbContribucion.getSelectedItem().toString());
                detalleBoletaContribucion.setIdAlicuotaContribucion(Integer.parseInt(cbAlicuotaContribucion.getSelectedValue().toString()));
                detalleBoletaContribucion.setNombreAlicuota(cbAlicuotaContribucion.getSelectedItem().toString());
                if (rbtnMultiplicador.isSelected()) {
                    detalleBoletaContribucion.setCalculo(1);
                } else if (rbtnPorcentaje.isSelected()) {
                    detalleBoletaContribucion.setCalculo(2);
                } else if (rbtnMontoFijo.isSelected()) {
                    detalleBoletaContribucion.setCalculo(3);
                }
                detalleBoletaContribucion.setMultiplicador(tfMultiplicador.getDouble());
                detalleBoletaContribucion.setValorModulo(tfValorModulo.getAmount());
                detalleBoletaContribucion.setMontoBase(tfMontoBase.getAmount());
                detalleBoletaContribucion.setPorcentaje(tfPorcentaje.getAmount());
                detalleBoletaContribucion.setMontoFijo(tfMontoFijo.getAmount());
                
                detalleBoletaContribucion.setMonto(tfMontoAlicuota.getAmount());
                detalleBoletaContribucion.setIdtipoImpuesto(boletaContribucion.getIdTipoImpuesto());
                detalleBoletaContribucion.setEstado("p");
                detalleBoletaContribucion.setConcepto(cbArticuloTasa.getSelectedItem().toString());
                
                if (!detalleBoletaContribucion.itemCargado(cbArticuloTasa.getSelectedItem().toString())) {
                    if (Advisor.question("Agregar ??tem","??Est?? seguro de registrar el Art??culo/Tasa\n\""+ cbArticuloTasa.getSelectedItem() +"\"?")) {
                        if (detalleBoletaContribucion.saveData() > 0) {
                            refresh();
                        } else {
                            Advisor.messageBox("Ocurri?? un error, no se pudo agregar el Art??culo/Tasa seleccionado","Error");
                        }
                    }
                } else {
                    Advisor.messageBox("El Art??culo/Tasa \""+ cbArticuloTasa.getSelectedItem() +"\"\nya est?? cargado en el detalle de la Boleta", "Error");
                }
            } else {
                Advisor.messageBox("El monto debe ser mayor que cero ($ 0,00)", "Error");
            }    
        } else {
            Advisor.messageBox("No es posible registrar m??s de 8 Art??culos/Tasas", "Aviso");
        }
    }

    private void rbtnMultiplicador_itemStateChanged(ItemEvent e) {
        controladorRadioButtons();
    }

    private void rbtnPorcentaje_itemStateChanged(ItemEvent e) {
        controladorRadioButtons();
    }
    
    private void rbtnMontoFijo_itemStateChanged(ItemEvent e) {
        controladorRadioButtons();
    }
    
    private void loadComboContribuyentes(String _filtro) {
        cbContribuyentes.loadJCombo("taxes.getAllContribuyentesByFilter", _filtro);
    }
    
    private void loadComboContribucion(String _filtro) {
        cbContribucion.loadJCombo("taxes.getAllContribucionesByFilter", _filtro);
        if (cbContribucion.getCombo().getItemCount() > 0) {
            btnAgregarItem.setEnabled(true);
        } else {
            btnAgregarItem.setEnabled(false);
        }
	loadComboAlicuota(tfBuscarAlicuota.getStringForSQL());
    }

    private void loadComboAlicuota(String _filtro) {
        cbAlicuotaContribucion.loadJCombo("taxes.getAllAlicuotasContribucionesByFilter", cbContribucion.getSelectedValue() + "," + _filtro );
        if (cbAlicuotaContribucion.getSelectedIndex() != -1) {
            btnAgregarItem.setEnabled(true);
        } else {
            btnAgregarItem.setEnabled(false);
        }
        loadDataAlicuota();
        loadComboArticuloTasa(tfBuscarArtTasa.getStringForSQL());
    }
    
    private void loadComboArticuloTasa(String _filtro) {
        cbArticuloTasa.loadJCombo("taxes.getAllArticulosTasasPorContribuciones",_filtro);
    }

    private double calcularMontoAlicuota() {
        double monto = 0.0;
        if (rbtnMontoFijo.isSelected()) {
            monto = tfMontoFijo.getAmount();
        } else if (rbtnMultiplicador.isSelected()) {
            monto = tfMultiplicador.getBigDecimal().multiply(tfValorModulo.getBigDecimal()).doubleValue();
        } else {
            monto = tfPorcentaje.calculatePercentage(tfMontoBase.getBigDecimal()).doubleValue();
        }
        tfMontoAlicuota.setValue(monto);
        return monto;
    }
    
    private void btnGrabarBoleta_actionPerformed(ActionEvent e) {
        saveData();
    }
    
    public boolean saveData() {
        boolean _returns = false;
        if (Advisor.question("Registrar boleta","??Est?? seguro de registrar la Boleta por un Monto Total de " + Format.money(tfMontoTotal.getAmount()) + "?")) {
            if (tfMontoTotal.getAmount() > 0)  {
                if (boletaContribucion.approveBoletaContribucion())  {
                    _returns = true;
                    restart();
                    refresh();
                } else  {
                    Advisor.messageBox("Ocurri?? un error al grabar los datos", "Error");
                }    
            } else {
                Advisor.messageBox("El monto total debe ser mayor que cero ($ 0,00)", "Error");
            }
        }
        return _returns;
    }

    public void refresh() {
        btnBorrarItem.setEnabled(false);
        listPanel.refresh(boletaContribucion.getIdBoletaContribucion());
    }
    
    private void loadDataAlicuota() {
        alicuotaContribucion = new AlicuotaContribucion();
        if (cbAlicuotaContribucion.getSelectedIndex() > -1) {
            alicuotaContribucion.setIdalicuotacontribucion(Integer.parseInt(cbAlicuotaContribucion.getSelectedValue().toString()));
            alicuotaContribucion.retrieveData();
            tfMultiplicador.setValue(alicuotaContribucion.getMultiplicador());
            tfPorcentaje.setValue(alicuotaContribucion.getPorcentaje());
            tfMontoFijo.setValue(alicuotaContribucion.getMontofijo());
            calcularMontoAlicuota();
            btnVerTextoOrdenado.setEnabled(true);
        } else {
            tfMontoAlicuota.setValue(0.0);
            btnVerTextoOrdenado.setEnabled(false);
        }
    }
    
    private void agregarContribuyente() {
        String _nombreContribuyente = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el Nombre del Contribuyente", "Nombre del Contribuyente", JOptionPane.QUESTION_MESSAGE, null, null, "");
        if ((_nombreContribuyente != null) && (_nombreContribuyente.trim().length() > 0)) {
            String _dniContribuyente = (String)JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese el DNI/CUIT/CUIL del Contribuyente", "DNI/CUIT/CUIL del Contribuyente", JOptionPane.QUESTION_MESSAGE, null, null, "");
            if ((_dniContribuyente != null) && (_dniContribuyente.trim().length() > 0)) {
                cbContribuyentes.addItem(_nombreContribuyente.toUpperCase().trim() + " (" + _dniContribuyente.toUpperCase().trim() + ")");
                cbContribuyentes.getCombo().setSelectedIndex(cbContribuyentes.getCombo().getItemCount()-1);
            } else {
                Advisor.messageBox("El DNI/CUIT/CUIL del Contribuyente no debe ser vac??o", "Error");
            }
        } else {
            Advisor.messageBox("El Nombre del Contribuyente no debe ser vac??o", "Error");
        }
    }

    private void btnVerArticulo_actionPerformed(ActionEvent e) {
        JArea _jaTextoOrdenado = new JArea();
	_jaTextoOrdenado.setEditable(false);
	_jaTextoOrdenado.setContentType(JArea.CONTENT_HTML); 
        _jaTextoOrdenado.setText("<html>" +
		    "<p align=center><font size=5><b>" + cbContribucion.getSelectedItem() + "</b></font></p>" +
		    "<p align=center><u><b>" + cbAlicuotaContribucion.getSelectedItem() + "</b></u></p><br>" +
		    alicuotaContribucion.getTextoOrdenado().replaceAll("Art.", "<b>Art??culo N??</b>") +
		    "</html>");
	_jaTextoOrdenado.getTextArea().setCaretPosition(0);
        _jaTextoOrdenado.setSize(new Dimension(400,250));

        ExtendedInternalFrame _articulosContainer = new ExtendedInternalFrame("Ver texto ordenado (Al??cuotas)", _jaTextoOrdenado);
        ComponentsManager.setConfigurable(_articulosContainer);
        _articulosContainer.setResizable(true);
        _articulosContainer.setIconifiable(false);

        _articulosContainer.show();
    }

    private boolean controlEncabezado() {
        boolean _return = false;
        if (cbContribuyentes.getSelectedIndex() == -1) {
            Advisor.messageBox("El campo Contribuyente no debe estar vac??o", "Mensaje");
        } else if (tfConcepto.getString().equals("")) {
            Advisor.messageBox("El campo Concepto no debe estar vac??o", "Mensaje");
        } else {
            _return = true;
        }
        return _return;
    }

    private void registrarEncabezadoBoleta() {
        if (controlEncabezado()) {
            String _nombreContribuyente = cbContribuyentes.getSelectedItem().toString();
            String _nroDocumentoContribuyente = _nombreContribuyente.substring(_nombreContribuyente.indexOf("(") + 1, _nombreContribuyente.indexOf(")"));
            _nombreContribuyente = _nombreContribuyente.substring(0,_nombreContribuyente.indexOf("("));
            boletaContribucion.setAnio(Integer.parseInt(Environment.currentYear.toString()));
            boletaContribucion.setConcepto(tfConcepto.getString());
            boletaContribucion.setContribuyente(_nombreContribuyente.trim());
            boletaContribucion.setNroDocumento(_nroDocumentoContribuyente.trim());
            boletaContribucion.setIdTipoImpuesto(6);
            boletaContribucion.setEstado("p");
 
            if (boletaContribucion.saveData() > 0)  {
                Environment.addUnfinishedTask(getParentInternalFrame());
                boletaContribucion.retrieveData();
                tfNumeroComprobante.setText(boletaContribucion.getAnio() +"-"+ boletaContribucion.getNumeroFormateado());
                tfNumeroComprobante.setVisible(true);
                setEnabledEncabezado(false);
                setEnabledDetalle(true);
                controladorRadioButtons();
            } else  {
                Advisor.messageBox("Ocurri?? un error al grabar los datos del encabezado de la Boleta", "Mensaje");
            }
        }
    }

    private void btnRegistrarEncabezadoBoleta_actionPerformed(ActionEvent e) {
        registrarEncabezadoBoleta();
    }
    
    private void controladorRadioButtons() {
        habilitarComponentes();
        calcularMontoAlicuota();
    }
}
