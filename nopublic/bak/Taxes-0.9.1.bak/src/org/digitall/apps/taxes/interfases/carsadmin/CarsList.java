package org.digitall.apps.taxes.interfases.carsadmin;

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

import org.digitall.apps.taxes.classes.Car;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;


public class CarsList extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel jpBuscar = new BasicPanel();

    private TFInput tfNombre = new TFInput(DataTypes.STRING, "Name",false);
    private TFInput tfDominio = new TFInput(DataTypes.STRING, "Domain",false);
    private TFInput tfDni = new TFInput(DataTypes.INTEGER, "DNI",false);
    private TFInput tfModelo = new TFInput(DataTypes.INTEGER, "Modelo",false);

    private int[] sizeColumnList = {75, 190, 75, 85, 135, 35 , 55 , 80};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Listado de Vehículos", dataRow) {
	public void finishLoading() {
	    controlBotones();
	}
    };
    
    private FindButton btnBuscar = new FindButton();
    private AddButton btnAgregar = new AddButton();
    private ModifyButton btnEditar = new ModifyButton();
    private PrintButton btnImprimir = new PrintButton();
    
    private Car car;

    public CarsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfNombre.setBounds(new Rectangle(15, 20, 170, 35));
	listPanel.setBounds(new Rectangle(5, 75, 700, 270));
	content.setBounds(new Rectangle(5, 5, 700, 500));
	content.setLayout(null);
	content.setSize(new Dimension(700, 515));
	jpBuscar.setBounds(new Rectangle(3, 0, 700, 65));
	jpBuscar.setLayout(null);
	tfDominio.setBounds(new Rectangle(380, 20, 130, 35));
	btnBuscar.setBounds(new Rectangle(660, 30, 30, 25));
	btnBuscar.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAgregar.setBounds(new Rectangle(560, 525, 40, 25));
	btnAgregar.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        btnAgregar.setToolTipText("Agregar nuevo Dominio");
	btnImprimir.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
	
	btnImprimir.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickPrint(e);
		}
	    }
	});
	
	tfDni.setBounds(new Rectangle(230, 20, 105, 35));
        jpBuscar.setBorder(BorderPanel.getBorderPanel("Buscar"));
        jpBuscar.add(tfModelo, null);
        jpBuscar.add(tfDni, null);
        jpBuscar.add(tfDominio, null);
        jpBuscar.add(tfNombre, null);
        jpBuscar.add(btnBuscar, null);
        btnEditar.setBounds(new Rectangle(610, 525, 40, 25));
	btnEditar.setToolTipText("Modificar el Dominio seleccionado");
	btnEditar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	content.add(listPanel, null);
        content.add(jpBuscar, null);
        this.add(content, BorderLayout.CENTER);
	addButton(btnEditar);
	addButton(btnAgregar); 
	
	listPanel.getTable().setDragEnabled(true);
	listPanel.getTable().setTransferHandler(new TableTransferHandler());
	tfNombre.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfDominio.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfDni.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
	tfModelo.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
        btnEditar.setEnabled(false);
	tfModelo.setBounds(new Rectangle(555, 20, 70, 35));
	setCadastralHeader();
	addButton(btnImprimir);
        listPanel.getTable().addMouseListener(new MouseAdapter() { 
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                            loadObjectSelected();    
                        } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                            loadMgmt(false);
                        }
                    }
       });
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Busqueda, Alta, Modificación e Impresión de Dominios");
    }
    

    public void refresh() {
	String params = "'"+ tfNombre.getString() +"','"+ tfDni.getString() +"','"+ tfDominio.getString() +"',"+ tfModelo.getInteger();
	listPanel.refresh(params);
	btnEditar.setEnabled(false);
	btnImprimir.setVisible(true);
    }

    private void setCadastralHeader() {
        Vector carslHeader = new Vector();
	//carslHeader.removeAllElements();
	carslHeader.addElement("*");    //iddominio
	carslHeader.addElement(Environment.lang.getProperty("Domain"));
	carslHeader.addElement(Environment.lang.getProperty("Name"));
        carslHeader.addElement(Environment.lang.getProperty("DNI"));
	carslHeader.addElement(Environment.lang.getProperty("Type"));
	carslHeader.addElement(Environment.lang.getProperty("Brand"));
	carslHeader.addElement("*");    //motor
	carslHeader.addElement("Cat.");    //categoria
	carslHeader.addElement(Environment.lang.getProperty("Model"));
        carslHeader.addElement("*");    //cuota
        carslHeader.addElement("*");    //valor anual
        carslHeader.addElement("*");    //pagadesde
	carslHeader.addElement(Environment.lang.getProperty("Eximido"));
	carslHeader.addElement("*");    //fechabaja;		-->
	carslHeader.addElement("*");    //estado
	carslHeader.addElement("*");    //idautomotor
	carslHeader.addElement("*");    //anticipoautomotor
	carslHeader.addElement("*");    //domicilio
	carslHeader.addElement("*");    //observaciones
	carslHeader.addElement("*");    //tipo dominio
	
		
	String params = "'','','',0" ;
		listPanel.setParams("taxes.getAllCars", params, carslHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    car = new Car();
	    car.setIddominio(Integer.parseInt("" + dataRow.elementAt(0)));
	    car.setDominio("" + dataRow.elementAt(1));
	    car.retrieveData();
	    btnEditar.setEnabled(true);
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction) {
	    car = new Car();
	}
	CarsMgmt carsMgmt = new CarsMgmt();
	carsMgmt.setCar(car);
	carsMgmt.setParentList(this);
	
	ExtendedInternalFrame carsMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	carsMgmtContainer.setCentralPanel(carsMgmt);
	carsMgmtContainer.show();
	carsMgmt.load();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	//Falta una funcion que haga controles. Por ej. la fecha del modelo no debe ser superior a la fecha de inicio de pago
	loadObjectSelected();
	loadMgmt(false);
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	boolean vigente = true;
	BasicReport report = new BasicReport(CarsList.class.getResource("xml/PadronAutomotor.xml"));
	report.addTableModel("taxes.xmlGetPadronAutomotor", ""+ vigente );
	if (vigente) {
	    report.setProperty("title", "Padrón Automotor Vigente");
	    report.setProperty("impuesto", "TASA GENERAL DE SERVICIOS");
	} else {
	    report.setProperty("title", "Padrón Automotor con baja");
	}
	report.doReport();
    }
    
    private void controlBotones(){
	btnEditar.setEnabled(false);
    }
    
    private void btnDel_actionPerformed(ActionEvent e) {
	// Funcion que podra borrar automotores, solo en modo admin
	if (Advisor.question("Advertencia","¿Está seguro de eliminar el Dominio "+ car.getDominio() +"?")) {
	    if (car.deleteCar()) {
		Advisor.messageBox("Dominio Borrado exitosamente","Mensaje");
		refresh();
	    } else {
	        Advisor.messageBox("Ocurrió un error al borrar el Dominio","Error");
	    }
	}
    }
    
    private void clickPrint(MouseEvent e){
	PopupPrinterCars popupPrint = new  PopupPrinterCars();
	int x = (int)e.getPoint().getX();
	int y = (int)e.getPoint().getY() - popupPrint.getAlto();
	popupPrint.show(btnImprimir,x,y);
    }
    
}
