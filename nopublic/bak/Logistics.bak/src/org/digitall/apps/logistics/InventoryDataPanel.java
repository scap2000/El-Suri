package org.digitall.apps.logistics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTabbedPane;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.JOutry;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class InventoryDataPanel extends BasicContainerPanel {

    private BorderLayout layBorder = new BorderLayout();
    private JTabbedPane jtabPanels = new JTabbedPane();
    private BasicPanel jpButtons = new BasicPanel();
    private AddButton bAdd = new AddButton();
    private CloseButton bCancel = new CloseButton();
    private AcceptButton bOk = new AcceptButton();
    private BasicPanel jpRecurso = new BasicPanel();
    private JOutry jtIDResource = new JOutry();
    private BasicLabel lblIDResource = new BasicLabel();
    private JEntry jtCode = new JEntry();
    private BasicLabel lblCode = new BasicLabel();
    private BasicLabel lblName = new BasicLabel();
    private JEntry jtName = new JEntry();
    private BasicScrollPane scpDescription = new BasicScrollPane();
    private JArea jtDescription = new JArea();
    private BasicLabel lblDescription = new BasicLabel();
    private JEntry jtDestination = new JEntry();
    private JEntry jtLocation = new JEntry();
    private BasicLabel lblDestination = new BasicLabel();
    private BasicLabel lblLocation = new BasicLabel();
    private JDecEntry jtAmount = new JDecEntry();
    private BasicLabel lblAmount = new BasicLabel();
    private JEntry jtStatus = new JEntry();
    private BasicLabel lblStatus = new BasicLabel();
    private JTFecha jtInventoryDate = new JTFecha();
    private BasicLabel lblInventoryDate = new BasicLabel();
    private BasicLabel lblAquisitionDate = new BasicLabel();
    private JTFecha jtAcquisitionDate = new JTFecha();
    private JOutry jtSaveDate = new JOutry();
    private BasicLabel lblSaveDate = new BasicLabel();
    private Component parent;
    private BasicPanel jpExtra = new BasicPanel();
    private BasicScrollPane scpPeople = new BasicScrollPane();
    private BasicLabel lblPeople = new BasicLabel();
    private JArea jtPeople = new JArea();
    private BasicLabel lblObservations = new BasicLabel();
    private BasicScrollPane scpObservations = new BasicScrollPane();
    private JArea jtObservations = new JArea();
    private boolean update = false;
    private boolean firstLoad = true;
    private String idResource = "";
    private int mode = Environment.UNSETMODE;

    public InventoryDataPanel(BasicDialog _parent) {
	try {
	    parent = _parent;
	    mode = Environment.STANDALONEMODE;
	    update = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public InventoryDataPanel(BasicDialog _parent, String _idResource) {
	try {
	    parent = _parent;
	    idResource = _idResource;
	    mode = Environment.STANDALONEMODE;
	    update = true;
	    jbInit();
	    refreshPanel();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public InventoryDataPanel(BasicInternalFrame _parent, String _idResource) {
	try {
	    parent = _parent;
	    idResource = _idResource;
	    mode = Environment.DESKTOPMODE;
	    update = true;
	    jbInit();
	    refreshPanel();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public InventoryDataPanel(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    mode = Environment.DESKTOPMODE;
	    update = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(layBorder);
	this.setSize(new Dimension(554, 380));
	jpButtons.setLayout(null);
	jpButtons.setSize(new Dimension(438, 40));
	jpButtons.setBounds(new Rectangle(0, 330, 438, 40));
	jpButtons.setPreferredSize(new Dimension(438, 30));
	//bAdd.setText("Agregar");
	bAdd.setMnemonic('g');
	//bCancel.setText("Cancelar");
	bAdd.setBounds(new Rectangle(465, 3, 40, 25));
	bAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAdd_actionPerformed(e);
		    }

		});
	bCancel.setMnemonic('C');
	bCancel.setPreferredSize(new Dimension(40, 25));
	//bOk.setText("Aceptar");
	bCancel.setBounds(new Rectangle(415, 3, 40, 25));
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	bOk.setSize(new Dimension(47, 22));
	bOk.setMnemonic('A');
	bOk.setBounds(new Rectangle(514, 3, 40, 25));
	bOk.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bOk_actionPerformed(e);
		    }

		});
	jpRecurso.setLayout(null);
	jtIDResource.setBounds(new Rectangle(5, 25, 90, 20));
	jtIDResource.setEditable(false);
	lblIDResource.setText("# Recurso:");
	lblIDResource.setBounds(new Rectangle(5, 10, 90, 14));
	lblIDResource.setFont(new Font("Dialog", 1, 11));
	jtCode.setBounds(new Rectangle(305, 25, 80, 20));
	lblCode.setText("C??digo:");
	lblCode.setBounds(new Rectangle(305, 10, 80, 15));
	lblCode.setFont(new Font("Dialog", 1, 11));
	lblName.setText("Nombre:");
	lblName.setBounds(new Rectangle(105, 10, 80, 15));
	lblName.setFont(new Font("Dialog", 1, 11));
	jtName.setBounds(new Rectangle(105, 25, 190, 20));
	scpDescription.setBounds(new Rectangle(5, 70, 380, 100));
	lblDescription.setText("Descripci??n:");
	lblDescription.setBounds(new Rectangle(5, 55, 380, 15));
	lblDescription.setFont(new Font("Dialog", 1, 11));
	jtDestination.setBounds(new Rectangle(5, 195, 185, 20));
	jtLocation.setBounds(new Rectangle(200, 195, 185, 20));
	lblDestination.setText("Destino:");
	lblDestination.setBounds(new Rectangle(5, 180, 185, 15));
	lblDestination.setFont(new Font("Dialog", 1, 11));
	lblLocation.setText("Ubicaci??n Real:");
	lblLocation.setBounds(new Rectangle(200, 180, 185, 15));
	lblLocation.setFont(new Font("Dialog", 1, 11));
	jtAmount.setBounds(new Rectangle(5, 240, 80, 20));
	lblAmount.setText("Cantidad:");
	lblAmount.setBounds(new Rectangle(5, 225, 80, 15));
	lblAmount.setFont(new Font("Dialog", 1, 11));
	jtStatus.setBounds(new Rectangle(95, 240, 80, 20));
	lblStatus.setText("Estado:");
	lblStatus.setBounds(new Rectangle(95, 225, 80, 15));
	lblStatus.setFont(new Font("Dialog", 1, 11));
	jtInventoryDate.setBounds(new Rectangle(5, 285, 80, 20));
	jtInventoryDate.setToolTipText("Fecha del Inventario - Click para abrir el calendario");
	lblInventoryDate.setText("Fecha Inventario:");
	lblInventoryDate.setBounds(new Rectangle(5, 270, 80, 15));
	lblInventoryDate.setFont(new Font("Dialog", 1, 11));
	lblAquisitionDate.setText("Fecha Adquisici??n:");
	lblAquisitionDate.setBounds(new Rectangle(95, 270, 80, 15));
	lblAquisitionDate.setFont(new Font("Dialog", 1, 11));
	jtAcquisitionDate.setBounds(new Rectangle(95, 285, 80, 20));
	jtAcquisitionDate.setToolTipText("Fecha de Adquisici??n del Recurso - Click para abrir el calendario");
	jtSaveDate.setBounds(new Rectangle(185, 285, 80, 20));
	lblSaveDate.setText("Fecha Carga:");
	lblSaveDate.setBounds(new Rectangle(185, 270, 80, 15));
	lblSaveDate.setFont(new Font("Dialog", 1, 11));
	jpExtra.setLayout(null);
	scpPeople.setBounds(new Rectangle(5, 25, 380, 100));
	lblPeople.setText("Personas:");
	lblPeople.setBounds(new Rectangle(5, 10, 380, 15));
	lblPeople.setFont(new Font("Dialog", 1, 11));
	lblObservations.setText("Observaciones:");
	lblObservations.setBounds(new Rectangle(5, 135, 380, 15));
	lblObservations.setFont(new Font("Dialog", 1, 11));
	scpObservations.setBounds(new Rectangle(5, 150, 380, 100));
	jpRecurso.add(lblSaveDate, null);
	jpRecurso.add(jtSaveDate, null);
	jpRecurso.add(jtAcquisitionDate, null);
	jpRecurso.add(lblAquisitionDate, null);
	jpRecurso.add(lblInventoryDate, null);
	jpRecurso.add(jtInventoryDate, null);
	jpRecurso.add(lblStatus, null);
	jpRecurso.add(jtStatus, null);
	jpRecurso.add(lblAmount, null);
	jpRecurso.add(jtAmount, null);
	jpRecurso.add(lblLocation, null);
	jpRecurso.add(lblDestination, null);
	jpRecurso.add(jtLocation, null);
	jpRecurso.add(jtDestination, null);
	jpRecurso.add(lblDescription, null);
	scpDescription.getViewport().add(jtDescription, null);
	jpRecurso.add(scpDescription, null);
	jpRecurso.add(jtName, null);
	jpRecurso.add(lblName, null);
	jpRecurso.add(lblCode, null);
	jpRecurso.add(jtCode, null);
	jpRecurso.add(lblIDResource, null);
	jpRecurso.add(jtIDResource, null);
	jtabPanels.addTab("Recurso", jpRecurso);
	jtabPanels.addTab("Personas y Observaciones", jpExtra);
	this.add(jtabPanels, BorderLayout.CENTER);
	jpButtons.add(bAdd, null);
	jpButtons.add(bOk, null);
	jpButtons.add(bCancel, null);
	this.add(jpButtons, BorderLayout.SOUTH);
	jpExtra.add(lblObservations, null);
	jpExtra.add(lblPeople, null);
	scpPeople.getViewport().add(jtPeople, null);
	jpExtra.add(scpPeople, null);
	scpObservations.getViewport().add(jtObservations, null);
	jpExtra.add(scpObservations, null);
	clearPanel();
    }

    private void bOk_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    dispose();
	}
    }

    public boolean saveData() {
	if (update) {
	    String consulta =
		//" idresource = ,"
		"UPDATE inventory.resources SET " + " idtype = 0," + " barcode = 0," + " name = '" + jtName.getText() + "'," + " code = '" + jtCode.getText() + "'," + " description = '" + jtDescription.getText() + "'," + " destination = '" + jtDestination.getText() + "'," + " location = '" + jtLocation.getText() + "'," + " amount = 0" + jtAmount.getText() + "," + " status = '" + jtStatus.getText() + "'," + " acquisitiondate = " + (jtAcquisitionDate.getText().equals("") ? (new String("null")) : "'" + Proced.setFormatDate(jtAcquisitionDate.getText(), false) + "'") + "," + " inventorydate = " + (jtInventoryDate.getText().equals("") ? (new String("null")) : "'" + Proced.setFormatDate(jtInventoryDate.getText(), false) + "'") + "," + " savedate = now()," + " people = '" + jtPeople.getText() + "'," + " observations = '" + jtObservations.getText() + "'" + " WHERE idresource = 0" + jtIDResource.getText();
	    return LibSQL.exActualizar('m', consulta);
	} else {
	    String consulta = //idresource
		//idtype
		//barcode
		//name
		//code
		//description
		//destination
		//location
		//amount
		//status
		//acquisitiondate
		//inventorydate
		//savedate
		//people
		//observaciones
		//estado
		"INSERT INTO inventory.resources VALUES(" + "(SELECT MAX(idresource)+1 FROM inventory.resources)," + "0," + "0," + "'" + jtName.getText() + "'," + "'" + jtCode.getText() + "'," + "'" + jtDescription.getText() + "'," + "'" + jtDestination.getText() + "'," + "'" + jtLocation.getText() + "'," + "0" + jtAmount.getText() + "," + "'" + jtStatus.getText() + "'," + "" + (jtAcquisitionDate.getText().equals("") ? (new String("null")) : "'" + Proced.setFormatDate(jtAcquisitionDate.getText(), false) + "'") + "," + "" + (jtInventoryDate.getText().equals("") ? (new String("null")) : "'" + Proced.setFormatDate(jtInventoryDate.getText(), false) + "'") + "," + "now()," + "'" + jtPeople.getText() + "'," + "'" + jtObservations.getText() + "'," + "''" + ");";
	    return LibSQL.exActualizar('a', consulta);
	}
    }

    private void clearPanel() {
	jtIDResource.setText(org.digitall.lib.sql.LibSQL.getCampo("SELECT MAX(idresource)+1 FROM inventory.resources"));
	jtName.setText("");
	jtCode.setText("");
	jtDescription.setText("");
	jtDestination.setText("");
	jtLocation.setText("");
	jtAmount.setText("0");
	jtStatus.setText("");
	jtInventoryDate.setText(Proced.FechaHora2(true, false));
	jtSaveDate.setText(Proced.FechaHora2(true, false));
	jtAcquisitionDate.setText(Proced.FechaHora2(true, false));
	jtPeople.setText("");
	jtObservations.setText("");
	firstLoad = false;
    }

    private void bAdd_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    clearPanel();
	}
    }

    private void dispose() {
	if (mode == Environment.STANDALONEMODE) {
	    ((BasicDialog)parent).dispose();
	} else if (mode == Environment.DESKTOPMODE) {
	    ((BasicInternalFrame)parent).dispose();
	}
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void refreshPanel() {
	ResultSet datos = org.digitall.lib.sql.LibSQL.exQuery("SELECT * FROM inventory.resources WHERE idresource = " + idResource);
	try {
	    if (datos.next()) {
		jtIDResource.setText(idResource);
		jtName.setText(datos.getString("name"));
		jtCode.setText(datos.getString("code"));
		jtDescription.setText(datos.getString("description"));
		jtDestination.setText(datos.getString("destination"));
		jtLocation.setText(datos.getString("location"));
		jtAmount.setText(datos.getString("amount"));
		jtStatus.setText(datos.getString("status"));
		jtInventoryDate.setValue(Proced.setFormatDate(datos.getString("inventorydate"), true));
		jtAcquisitionDate.setValue(Proced.setFormatDate(datos.getString("acquisitiondate"), true));
		jtSaveDate.setText(Proced.setFormatDate(datos.getString("savedate"), true));
		jtPeople.setText(datos.getString("people"));
		jtObservations.setText(datos.getString("observations"));
		setTitle(jtName.getText());
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

   /* private void setTitle(String _title) {
	if (mode == Environment.STANDALONEMODE) {
	    ((BasicDialog)parent).setTitle(_title);
	} else if (mode == Environment.DESKTOPMODE) {
	    ((BasicInternalFrame)parent).setTitle(_title);
	}
    }*/

}
