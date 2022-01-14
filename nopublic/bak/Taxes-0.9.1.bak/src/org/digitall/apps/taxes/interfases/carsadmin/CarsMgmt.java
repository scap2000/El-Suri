package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.FeesxCategory;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class CarsMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel("");

    private CloseButton btnCerrar = new CloseButton();
    private SaveDataButton btnAceptar = new SaveDataButton();
    private FindButton btnAddCuotaxCategoria = new FindButton();
    private AcceptButton btnSeguridad = new AcceptButton();
    private ModifyButton btnModificarFechaBaja = new ModifyButton();

    private AcceptButton btnConfirmarDatos = new AcceptButton();
    private CloseButton btnCancelarDatos = new CloseButton();

    private TFInput tfDominio = new TFInput(DataTypes.STRING, "Domain", true);
    private TFInput tfPropietario = new TFInput(DataTypes.STRING, "OwnerDomain", true);
    private TFInput tfMarca = new TFInput(DataTypes.STRING, "Brand", false);
    private TFInput tfMotor = new TFInput(DataTypes.STRING, "Nº Motor", false);
    private TFInput tfCuotaAnual = new TFInput(DataTypes.MONEY, "AnnualFee", false);
    private TFInput tfCuotaBimestral = new TFInput(DataTypes.MONEY, "TwoMonthlyFee", false);
    private TFInput tfPagaDesde = new TFInput(DataTypes.DATE, "PaidSince", true);
    private TFInput tfFechaExencion = new TFInput(DataTypes.DATE, "ToExemptDate", false);
    private TFInput tfFechaBaja = new TFInput(DataTypes.DATE, "DropOffDate", false);
    private TFInput tfDni = new TFInput(DataTypes.STRING, "DNI/CUIT/CUIL", false);
    private TFInput tfObservaciones = new TFInput(DataTypes.STRING, "Observations", false);
    private TFInput tfDomicilio = new TFInput(DataTypes.STRING, "Domicilio Legal", false);
    private TFInput tfCategoria = new TFInput(DataTypes.STRING, "Category", false);

    private CBInput cbModelos = new CBInput(0, "Model", false);
    private CBInput cbEximido = new CBInput(0, "Eximido", false);
    private CBInput cbTipoDominio = new CBInput(0, "DomainType", false);
    private CBInput cbTipoVehiculo = new CBInput(CachedCombo.AUTOMOTORES_TABS, "Tipo Automotor", false);

    private Car car;
    private BasicPrimitivePanel parentList;
    private FeesxCategoryMgmt feesxCategoryMgmt;
    private FeesxCategory feesxCategory;
    private ModificarFechaBajaAutomotorMgmt modificarFechaBajaAutomotorMgmt;

    int error = 0;
    private static final int ok = 0;
    private static final int dominio = 1;
    private static final int titular = 2;
    private static final int pagaDesde = 3;
    //private static final int Fee = 4;
    private static final int formatoDominio = 5;
    private static final int fechaInvalida = 6;
    private static final int yaExiste = 7;
    private static final int fechaBajaInvalida = 8;
    private static final int categoriaInvalida = 9;

    private JLabel lblDominio = new JLabel();
    private JLabel lblTituloDominio = new JLabel();

    private BasicCheckBox chkDiscapacitado = new BasicCheckBox();

    private Vector components = new Vector();
    private TFInput tfValorAutomotor = new TFInput(DataTypes.MONEY, "Valor del Vehículo", false);
    private CBInput cbDescripcion = new CBInput(0, "Descripción", true);

    private final int FORMATO_PATENTE_AUTOS_NUEVA = 1;
    private final int FORMATO_PATENTE_AUTOS_VIEJA = 2;
    private final int FORMATO_DNI_CUIT_CUIL = 3;
    private final int FORMATO_PATENTE_MOTOS = 4;

    public CarsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//tfCategory.setText("0");
	this.setSize(new Dimension(565, 370));
	this.setPreferredSize(new Dimension(565, 300));
        content.setBounds(new Rectangle(5, 0, 555, 220));
        content.setLayout(null);
        
        lblDominio.setBounds(new Rectangle(10, 40, 185, 20));
        lblDominio.setFont(new Font("Dialog", 1, 16));
        lblDominio.setForeground(Color.red);
        lblDominio.setBackground(new Color(183, 215, 255));
        lblDominio.setText("TLP-929");
        lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
        lblDominio.setOpaque(true);
        lblDominio.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        lblTituloDominio.setText("Dominio Registrado");
        lblTituloDominio.setBounds(new Rectangle(10, 25, 185, 15));
        lblTituloDominio.setFont(new Font("Dialog", 1, 12));
        lblTituloDominio.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloDominio.setOpaque(true);
        lblTituloDominio.setBackground(new Color(255, 132, 0));
        lblTituloDominio.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        
	tfDominio.setBounds(new Rectangle(385, 25, 160, 35));
	tfCategoria.setBounds(new Rectangle(10, 240, 65, 35));
	tfPropietario.setBounds(new Rectangle(10, 65, 190, 35));
        tfFechaExencion.setBounds(new Rectangle(250, 285, 115, 35));
        tfFechaBaja.setBounds(new Rectangle(400, 285, 115, 35));
        tfDomicilio.setBounds(new Rectangle(10, 110, 280, 35));
        tfObservaciones.setBounds(new Rectangle(300, 110, 245, 35));
        tfMarca.setBounds(new Rectangle(215, 150, 175, 35));
        tfMotor.setBounds(new Rectangle(400, 150, 145, 35));
        tfCuotaAnual.setBounds(new Rectangle(250, 240, 115, 35));
        tfCuotaBimestral.setBounds(new Rectangle(400, 240, 115, 35));
        tfPagaDesde.setBounds(new Rectangle(10, 285, 115, 35));
        tfDni.setBounds(new Rectangle(210, 65, 145, 35));
        tfCuotaAnual.setEditable(false);
        tfCuotaBimestral.setEditable(false);
        tfCategoria.setEditable(false);
        tfValorAutomotor.setBounds(new Rectangle(355, 195, 115, 35));
        tfValorAutomotor.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        setCategoria();
                    }
                }
            });
        tfFechaExencion.setEnabled(false);
        tfCuotaAnual.getTextField().setBackground(Color.YELLOW);
        tfCuotaBimestral.getTextField().setBackground(Color.YELLOW);
        tfCategoria.getTextField().setBackground(Color.YELLOW);
        tfCategoria.getTextField().setHorizontalAlignment(JTextField.CENTER);
        tfCategoria.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
	cbEximido.setBounds(new Rectangle(190, 245, 60, 35));
	cbTipoDominio.setBounds(new Rectangle(210, 25, 145, 35));
        cbModelos.setBounds(new Rectangle(270, 195, 75, 35));
        cbEximido.getCombo().addItem("NO", "1");
        cbEximido.getCombo().addItem("SI", "2");
        cbModelos.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        getFee();
                        ;
                    }
                }
            });
        cbEximido.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        setFechaEximido();

                    }
                }
            });
        cbTipoDominio.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        formatDomain();
                    }
                }
            });
        cbTipoVehiculo.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        setCategoria();
                    }
                }
            });
        cbDescripcion.setBounds(new Rectangle(10, 150, 200, 35));
        cbEximido.setVisible(false);
        cbTipoVehiculo.setBounds(new Rectangle(10, 195, 250, 35));
        //cbTipoVehiculo.autoSize();
        cbTipoVehiculo.setVisible(true);

        btnCerrar.setBounds(new Rectangle(540, 535, 40, 25));
        btnCerrar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnCerrar_actionPerformed(e);
                }

            });
        btnAceptar.setBounds(new Rectangle(490, 535, 40, 25));
        btnAceptar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAceptar_actionPerformed(e);
                }

            });
        btnAddCuotaxCategoria.setBounds(new Rectangle(370, 215, 25, 20));
        btnAddCuotaxCategoria.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddCuotaxCategoria_actionPerformed(e);
                }
            });
        btnModificarFechaBaja.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnModificarFechaBaja_actionPerformed(e);
                }
            });
        btnModificarFechaBaja.setToolTipText("Modificar la Fecha de baja");
        btnModificarFechaBaja.setBounds(new Rectangle(525, 295, 30, 25));
        btnModificarFechaBaja.setSize(new Dimension(30, 25));
        btnSeguridad.setToolTipText("Desbloquear combos");
        btnSeguridad.setBounds(new Rectangle(190, 200, 40, 25));
        btnSeguridad.setSize(new Dimension(30, 25));
        btnSeguridad.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnSeguridad_actionPerformed(e);
                }
            });
        btnAddCuotaxCategoria.setEnabled(false);
        btnAddCuotaxCategoria.setVisible(false);
        btnSeguridad.setVisible(false);
        btnModificarFechaBaja.setVisible(true);
        btnConfirmarDatos.setBounds(new Rectangle(475, 205, 40, 25));
        btnConfirmarDatos.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnConfirmarDatos_actionPerformed(e);
                }
            });
        btnCancelarDatos.setBounds(new Rectangle(515, 205, 40, 25));
        btnConfirmarDatos.setToolTipText("Confirmar valor del vehículo");
        btnCancelarDatos.setToolTipText("Cancelar valor del vehículo");
        btnCancelarDatos.setEnabled(false);
        btnCancelarDatos.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnCancelarDatos_actionPerformed(e);
                }
            });
        btnAceptar.setEnabled(false);

        chkDiscapacitado.setText("Discapacitado");
        chkDiscapacitado.setBounds(new Rectangle(385, 75, 110, 25));

	content.add(cbDescripcion, null);
	content.add(tfValorAutomotor, null);
	content.add(btnModificarFechaBaja, null);
	content.add(btnSeguridad, null);
	content.add(chkDiscapacitado, null);
	content.add(cbTipoVehiculo, null);
	content.add(btnAddCuotaxCategoria, null);
	content.add(lblTituloDominio, null);
	content.add(lblDominio, null);
	content.add(tfDni, null);
	content.add(tfFechaBaja, null);
	content.add(tfFechaExencion, null);
	content.add(cbModelos, null);
	content.add(tfPropietario, null);
	content.add(tfDominio, null);
	content.add(tfCategoria, null);
	content.add(tfMotor, null);
	content.add(tfMarca, null);
	content.add(tfObservaciones, null);
	content.add(tfDomicilio, null);
	content.add(cbEximido, null);
	content.add(cbTipoDominio, null);
	content.add(tfPagaDesde, null);
	content.add(tfCuotaAnual, null);
	content.add(tfCuotaBimestral, null);
	content.add(btnConfirmarDatos, null);
	content.add(btnCancelarDatos, null);
        
        content.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar un Vehículo"));

	setContent(content); 
	addButton(btnCerrar);
	addButton(btnAceptar);

	components.add(cbTipoDominio);
	components.add(tfDominio);
	components.add(tfPropietario);
	components.add(tfDni);
	components.add(chkDiscapacitado);
	components.add(tfDomicilio);
	components.add(tfObservaciones);
	components.add(tfMarca);
	components.add(tfMotor);
	components.add(tfCategoria);
	components.add(cbModelos);
	components.add(cbEximido);
	components.add(tfFechaExencion);
	components.add(tfFechaBaja);
	components.add(tfCuotaAnual);
	components.add(tfCuotaBimestral);
	components.add(tfPagaDesde);
	
        loadCombos();
        setFechaEximido();        
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Complete los campos y presione el botón \"Grabar\" ");
    }

    private void loadDomainTypeCombo(boolean _valor) {
	if (_valor) {
	    cbTipoDominio.getCombo().addItem("Sin Asignar", "0");
	}
	cbTipoDominio.getCombo().addItem("Patente Nueva", FORMATO_PATENTE_AUTOS_NUEVA);
	cbTipoDominio.getCombo().addItem("Patente Vieja", FORMATO_PATENTE_AUTOS_VIEJA);
	cbTipoDominio.getCombo().addItem("DNI/CUIT/CUIL", FORMATO_DNI_CUIT_CUIL);
	cbTipoDominio.getCombo().addItem("Patente de Motos", FORMATO_PATENTE_MOTOS);
    }

    private void setFechaEximido() {
	/*if (cbEximido.getSelectedItem().equals("SI"))  {
	    tfFechaExencion.setEnabled(true);
	} else {
	    tfFechaExencion.setValue("");
	    tfFechaExencion.setEnabled(false);
	}
	*/
    }

    private void formatDomain() {
	remove(content);
	content.remove(tfDominio);
	int _tipoDominio = new Integer(cbTipoDominio.getSelectedValue().toString());
	switch (_tipoDominio) {
	    case FORMATO_PATENTE_AUTOS_NUEVA:
		tfDominio = new TFInput("Domain", true, DataTypes.FORMATO_PATENTE_AUTOS_NUEVA);
		break;
	    case FORMATO_PATENTE_AUTOS_VIEJA:
		tfDominio = new TFInput("Domain", true, DataTypes.FORMATO_PATENTE_AUTOS_VIEJA);
		break;
	    case FORMATO_PATENTE_MOTOS:
		tfDominio = new TFInput("Domain", true, DataTypes.FORMATO_PATENTE_MOTOS);
		break;
	    case FORMATO_DNI_CUIT_CUIL:
		tfDominio = new TFInput("Domain", true, DataTypes.FORMATO_CUIT_CUIL);
		break;
	}
	if (car != null) {
	    tfDominio.setValue((car.getTipoDominio() == _tipoDominio)?car.getDominio():"");
	}
	tfDominio.setBounds(new Rectangle(385, 25, 160, 35));
	content.add(tfDominio, null);
	add(content);
	tfDominio.getTextField().requestFocus();
    }

    private void loadCombos() {
	// carga combo models
	int actualYear = Integer.parseInt("0" + Environment.currentYear);
	int cont = 0;
	for (int i = 1960; i <= (actualYear + 1); i++) {
	    cont++;
	    cbModelos.getCombo().addItem(i, cont);
	}
	cbModelos.setSelectedID(cont - 1);
	cont = 0;
	cbDescripcion.loadJCombo(LibSQL.exFunction("taxes.getAllTiposAutomotor", ""));
	getFee();
    }

    private void getFee() {
	double montoAnual = 0.00;
	if (!((tfCategoria.getValue().toString().equals("0")) || (tfCategoria.getValue().toString().equals("")))) {
	    montoAnual = LibSQL.getDouble("taxes.getCuotaAnual", "" + cbModelos.getSelectedItem() + "," + tfCategoria.getValue());
	}
	tfCuotaAnual.setValue(montoAnual);
	tfCuotaBimestral.setValue((montoAnual / 6.0));
    }

    public void load() {
	loadData();
    }

    private void loadData() {
	formatDomain();
	if (car.getIddominio() != -1) {
	    loadDomainTypeCombo(true);
	    tfPagaDesde.setEnabled(false);
	    lblDominio.setText(car.getDominio());
	    cbTipoDominio.setSelectedID(car.getIdTipoAutomotor());
	    tfDominio.getTextField().setText(car.getDominio());
	    tfPropietario.setValue(car.getTitular());
	    //tfDescripcion.setValue(car.getTipo());
	    cbDescripcion.setSelectedValue(car.getTipo().trim().toUpperCase());
	    tfDni.setValue(car.getDni());
	    tfMarca.setValue(car.getMarca());
	    tfMotor.setValue(car.getNromotor());
	    tfDomicilio.setValue(car.getDomicilio());
	    tfObservaciones.setValue(car.getObservaciones());
	    cbTipoVehiculo.setSelectedID(car.getIdTipoAutomotor());
	    cbModelos.setSelectedValue(car.getModelo());

	    //setCategoria();

	    if (!car.getPagadesde().equals("null")) {
		tfPagaDesde.setValue("" + Proced.setFormatDate(car.getPagadesde(), true));
	    }
	    if (car.getFechaExencion() != null) {
		tfFechaExencion.setValue("" + Proced.setFormatDate(car.getFechaExencion(), true));
		cbEximido.setSelectedValue("SI");
	    } else {
		cbEximido.setSelectedValue("NO");
	    }
	    if (car.getFechaBaja() != null) {
		tfFechaBaja.setValue("" + Proced.setFormatDate(car.getFechaBaja(), true));
		setEnabledAll(false);
	    } else {
		enabledComponents();
	    }
	    chkDiscapacitado.setSelected(car.isDiscapacitado());

	    tfCategoria.setEditable(false);
	    cbModelos.setEnabled(false);
	    cbTipoVehiculo.setEnabled(false);
	    tfValorAutomotor.setEnabled(false);
	    getFee();
	    tfFechaBaja.setEnabled(false);
	    confirmar(true);
	    tfCategoria.setValue(car.getCategoria());
	    tfValorAutomotor.setValue(car.getValorAutomotor());
	    tfCuotaAnual.setValue(car.getValoranual());
	    tfCuotaBimestral.setValue(car.getCuota());
	    btnModificarFechaBaja.setEnabled(true);
	} else {
	    btnModificarFechaBaja.setEnabled(false);
	    confirmar(false);
	    loadDomainTypeCombo(false);
	    lblDominio.setVisible(false);
	    lblTituloDominio.setVisible(false);
	    tfFechaBaja.setEnabled(false);
	}
	tfCategoria.getTextField().setBackground(Color.YELLOW);
    }

    private void setEnabledAll(boolean _test) {
	for (int i = 0; i < components.size(); i++) {
	    ((JComponent)components.elementAt(i)).setEnabled(_test);
	}
    }

    private void enabledComponents() {
	for (int i = 0; i < 10; i++) {
	    ((JComponent)components.elementAt(i)).setEnabled(true);
	}
    }

    private void btnCerrar_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void setData() {
	car.setDominio(tfDominio.getString());
	car.setTitular(tfPropietario.getString());
	car.setDni(tfDni.getValue().toString());
	car.setTipo(cbDescripcion.getSelectedItem().toString());
	car.setMarca(tfMarca.getString());
	car.setNromotor(tfMotor.getString());
	car.setIdtipocategoria(tfCategoria.getValue().toString());
	car.setModelo(Integer.parseInt(cbModelos.getSelectedItem().toString()));
	if (cbEximido.getSelectedItem().equals("SI")) {
	    car.setEximido(true);
	} else {
	    car.setEximido(false);
	}
	car.setDomicilio(tfDomicilio.getString());
	car.setObservaciones(tfObservaciones.getString());
	car.setCuota(tfCuotaBimestral.getAmount());
	car.setValoranual(tfCuotaAnual.getAmount());
	if (!tfPagaDesde.getDate().equals("")) {
	    car.setPagadesde("" + Proced.setFormatDate(tfPagaDesde.getDate(), false));
	}
	if (!tfFechaExencion.getDate().equals("")) {
	    car.setFechaExencion("" + Proced.setFormatDate(tfFechaExencion.getDate(), false));
	}

	if (!tfFechaBaja.getDate().equals("")) {
	    car.setFechaBaja("" + Proced.setFormatDate(tfFechaBaja.getDate(), false));
	} else {
	    car.setFechaBaja("");
	}
	car.setTipoDominio(Integer.parseInt(cbTipoDominio.getSelectedValue().toString()));
	if (chkDiscapacitado.isSelected()) {
	    car.setDiscapacitado(true);
	} else {
	    car.setDiscapacitado(false);
	}
	car.setIdTipoAutomotor(Integer.parseInt(cbTipoVehiculo.getSelectedValue().toString()));
	car.setValorAutomotor(tfValorAutomotor.getAmount());
    }

    public boolean saveData() {
        boolean _return = false;
	if (control() == ok) {
            if (car == null) {
                car = new Car();
            }
	    setData();
	    if (car.saveData() >= 0) {
                _return = true;
	    }
	} else {
	    showMessage();
	}
        return _return;
    }

    private void btnAceptar_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }

    public void setParentList(BasicPrimitivePanel _parentList) {
	parentList = _parentList;

    }

    private void callFeesxCategory() {
	feesxCategory = new FeesxCategory();
	feesxCategory.setAnio(Integer.parseInt("0" + cbModelos.getSelectedItem()));
	feesxCategory.setIdtipocategoria(Integer.parseInt("0" + tfCategoria.getValue()));
	feesxCategory.setCategoria("" + tfCategoria.getValue());
	feesxCategory.setNuevo(true);

	feesxCategoryMgmt = new FeesxCategoryMgmt();
	feesxCategoryMgmt.setFeesxCategory(feesxCategory);
	//feesxCategoryMgmt.setParentMgmt(this);

	ExtendedInternalFrame feesxCategoryMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	feesxCategoryMgmtContainer.setCentralPanel(feesxCategoryMgmt);
	feesxCategoryMgmtContainer.show();
    }

    public void recalcCuota() {
	if (car.getIddominio() != -1) {
	    car.getFeesxCategory().retrieveData();
	} else {
	    feesxCategory = new FeesxCategory();
	    feesxCategory.setAnio(Integer.parseInt(cbModelos.getSelectedItem().toString()));
	    feesxCategory.setIdtipocategoria(Integer.parseInt(tfCategoria.getValue().toString()));
	    feesxCategory.retrieveData();
	    car.setFeesxCategory(feesxCategory);
	}
	if (car.getFeesxCategory().getAnual() > 0) {
	    tfCuotaAnual.setValue(car.getFeesxCategory().getAnual());
	    tfCuotaBimestral.setValue(car.getFeesxCategory().getCuota());
	    btnAddCuotaxCategoria.setEnabled(false);
	} else {
	    btnAddCuotaxCategoria.setEnabled(true);
	}
    }

    public void setCar(Car _car) {
	car = _car;
    }

    private int control() {
	error = ok;
	if (car.getIdAutomotor() == -1) {
	    if (LibSQL.getBoolean("taxes.existDomain", "'" + tfDominio.getTextField().getText().trim() + "'")) {
		error = yaExiste;
	    }
	}
	if (error != yaExiste) {
	    if (tfDominio.getTextField().getText().trim().equals("")) {
		error = dominio;
	    } else if (tfPropietario.getString().equals("")) {
		error = titular;
	    } else if (tfPagaDesde.getDate().equals("")) {
		error = pagaDesde;
	    } else if ((tfDominio.getTextField().getValue().toString() == "") || (tfDominio.getTextField().getValue().toString().contains("_"))) {
		error = formatoDominio;
	    } else if (getYear(Proced.setFormatDate(tfPagaDesde.getDate(), false)) < Integer.parseInt(cbModelos.getSelectedItem().toString())) {
		error = fechaInvalida;
	    } else if (Proced.compareDates(Proced.setFormatDate(tfPagaDesde.getDate(), false), Proced.setFormatDate(tfFechaBaja.getDate(), false)) == 2) {
		error = fechaBajaInvalida;
	    } else if (tfCategoria.getValue().toString().equals("") || tfCategoria.getValue().toString().equals("0")) {
		error = categoriaInvalida;
	    }
	}
	return error;
    }

    private int getYear(String _date) {
	return (Integer.parseInt(_date.substring(0, _date.indexOf("-"))));
    }

    private void showMessage() {
	switch (error) {
	    case 1:
		Advisor.messageBox("Debe ingresar el número de Dominio", "Mensaje");
		break;
	    case 2:
		Advisor.messageBox("Debe ingresar el nombre del Titular", "Mensaje");
		break;
	    case 3:
		Advisor.messageBox("El campo Paga desde no puede estar vacio", "Mensaje");
		break;
	    case 5:
		/*Advisor.messageBox("No existe el valor de la cuota, debe agregarlo para poder grabar los datos", "Mensaje");
		    btnAddCuotaxCategoria.setEnabled(true);*/
		Advisor.messageBox("El formato del dominio es incorrecto", "Mensaje");
		break;
	    case fechaInvalida:
		Advisor.messageBox("El campo Paga desde debe tener una fecha mayor o igual al año del modelo", "Mensaje");
		break;
	    case yaExiste:
		Advisor.messageBox("Ya existe el Dominio: " + tfDominio.getValue().toString() , "Mensaje");
		break;
	    case fechaBajaInvalida:
		Advisor.messageBox("La fecha de baja debe ser mayor o igual a la fecha de inicio.", "Mensaje");
		break;
	    case categoriaInvalida:
		Advisor.messageBox("El campo categoria no puede estar vacio ni ser igual a cero.", "Mensaje");
		break;
	}
    }

    public void setBtn(boolean _valor) {
	//btnAddFeesxCategory.setEnabled(_valor);
    }

    private void btnAddCuotaxCategoria_actionPerformed(ActionEvent e) {
	callFeesxCategory();
    }

    private void btnSeguridad_actionPerformed(ActionEvent e) {
	tfCategoria.setEnabled(true);
	cbModelos.setEnabled(true);
	car.setModifyModelCategory(true);
    }

    /**2010-03-09 (moraless)*/
    public void recargarDatos() {
        car.retrieveData();
        loadData();
    }

    /**2010-03-09 (moraless)*/
    private void btnModificarFechaBaja_actionPerformed(ActionEvent e) {
	//modificarFechaBajaAutomotorMgmt = new ModificarFechaBajaAutomotorMgmt(car.getIddominio());
        modificarFechaBajaAutomotorMgmt = new ModificarFechaBajaAutomotorMgmt();
	modificarFechaBajaAutomotorMgmt.setParentList(this);
        modificarFechaBajaAutomotorMgmt.setCar(car);

	ExtendedInternalFrame modificarFechaBajaAutomotorMgmtContainer = new ExtendedInternalFrame("Modificar");
	modificarFechaBajaAutomotorMgmtContainer.setCentralPanel(modificarFechaBajaAutomotorMgmt);
	modificarFechaBajaAutomotorMgmtContainer.show();
        
    }

    private void setCategoria() {
	//tfCategory.loadJCombo(LibSQL.exFunction("taxes.getAllCategoriasAutomotor", cbTipoVehiculo.getSelectedValue().toString() +","+ tfValorAutomotor.getAmount()));
	tfCategoria.setValue("" + LibSQL.getString("taxes.getNombreCategoriaAutomotor", cbTipoVehiculo.getSelectedValue().toString() + "," + tfValorAutomotor.getAmount()));
	getFee();
    }

    private void btnConfirmarDatos_actionPerformed(ActionEvent e) {
	confirmar(true);
    }

    private void confirmar(boolean _confirmar) {
	if (_confirmar && (car.getIddominio() == -1)) {
	    setCategoria();
	} else {
	    //tfCategory.setValue("0");
	    tfCuotaAnual.setValue(0.00);
	    tfValorAutomotor.setValue(0.00);
	    //cbTipoVehiculo.getCombo().setSelectedIndex(0);
	}

	cbTipoVehiculo.setEnabled(!_confirmar);
	cbModelos.setEnabled(!_confirmar);
	tfValorAutomotor.setEnabled(!_confirmar);
	btnAceptar.setEnabled(_confirmar);
	btnConfirmarDatos.setEnabled(!_confirmar);

	if (car.getIddominio() == -1) {
	    btnCancelarDatos.setEnabled(_confirmar);
	} else {
	    btnCancelarDatos.setEnabled(!_confirmar);
	}
    }

    private void btnCancelarDatos_actionPerformed(ActionEvent e) {
	confirmar(false);
    }
}
