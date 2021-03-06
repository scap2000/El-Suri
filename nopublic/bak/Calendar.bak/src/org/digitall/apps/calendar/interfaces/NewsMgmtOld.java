package org.digitall.apps.calendar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class NewsMgmtOld extends BasicDialog implements KeyListener {

    private BasicPanel jPanel8 = new BasicPanel();
    private BasicPanel jPanel7 = new BasicPanel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private CloseButton bcancelar = new CloseButton();
    private AcceptButton benviar = new AcceptButton();
    private FindButton bbuscarobra = new FindButton();
    private JCombo jcprioridad = new JCombo();
    private JCombo jcestado = new JCombo();
    private BasicLabel jLabel123 = new BasicLabel();
    private BasicLabel jLabel124 = new BasicLabel();
    private BasicLabel jLabel18 = new BasicLabel();
    private BasicLabel jLabel19 = new BasicLabel();
    private BasicLabel jLabel125 = new BasicLabel();
    private BasicLabel lblobra = new BasicLabel();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private JTextArea jtinforme = new JTextArea();
    private BasicTextField jtobra = new BasicTextField();
    private BasicTextField jtidobra = new BasicTextField();
     
    //Variables
    private int idnovedad = 0;
    private String fecha = "";
    private int idemisor = 0;
    private int idprioridad = 0;
    private int idarea = 0;
    private int idresponsable = 0;

    public NewsMgmtOld() {
	try {
	    jbInit();
	} catch (SQLException e) {
	    Advisor.messageBox(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	getContentPane().setLayout(null);
	setSize(new Dimension(501, 397));
	setTitle("Enviar una Novedad");
	bcancelar.setText("Cancelar");
	bcancelar.setBounds(new Rectangle(387, 338, 98, 25));
	bcancelar.setMnemonic('C');
	bcancelar.setMargin(new Insets(2, 5, 2, 14));
	bcancelar.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     bcancelar_actionPerformed(e);
				 }

			     }
	);
	benviar.setText("Enviar");
	benviar.setBounds(new Rectangle(5, 338, 101, 25));
	benviar.setMnemonic('E');
	benviar.setMargin(new Insets(2, 5, 2, 14));
	benviar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   benviar_actionPerformed(e);
			       }

			   }
	);
	jPanel8.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
	jPanel8.setLayout(null);
	jPanel8.setBounds(new Rectangle(5, 15, 480, 45));
	jPanel7.setBounds(new Rectangle(715, 15, 10, 10));
	jPanel1.setBounds(new Rectangle(5, 125, 480, 165));
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel1.setLayout(null);
	jScrollPane1.setBounds(new Rectangle(5, 5, 470, 155));
	jtinforme.setLineWrap(true);
	jtinforme.setMargin(new Insets(5, 5, 5, 5));
	jLabel124.setText(" Informe (texto de la novedad): ");
	jLabel124.setBounds(new Rectangle(10, 115, 200, 15));
	jLabel124.setOpaque(true);
	jPanel3.setBounds(new Rectangle(5, 75, 480, 35));
	jPanel3.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
	jPanel3.setLayout(null);
	jcprioridad.setBounds(new Rectangle(70, 8, 145, 20));
	jcprioridad.setFont(new Font("Dialog", 1, 11));
	jcestado.setBounds(new Rectangle(290, 8, 125, 20));
	jcestado.setFont(new Font("Dialog", 1, 11));
	jLabel18.setText("Prioridad:");
	jLabel18.setBounds(new Rectangle(10, 11, 59, 15));
	jLabel18.setFont(new Font("Dialog", 1, 11));
	jLabel19.setText("Estado:");
	jLabel19.setBounds(new Rectangle(245, 11, 45, 15));
	jLabel19.setFont(new Font("Dialog", 1, 11));
	//    idbarrio = Proced.getCampo("SELECT idbarrio FROM barrios WHERE nombre = '"+ jcbarrios.getItemTexto() + "'");
	jLabel124.setForeground(Color.blue);
	jcestado.setForeground(Color.red);
	jcprioridad.setForeground(Color.red);
	benviar.setFont(new Font("Dialog", 1, 11));
	bcancelar.setFont(new Font("Dialog", 1, 11));
	jtinforme.setFont(new Font("Dialog", 1, 11));
	jtinforme.setForeground(Color.red);
	jLabel125.setText(" Datos de Registraci??????n: ");
	jLabel125.setBounds(new Rectangle(10, 65, 150, 15));
	jLabel125.setOpaque(true);
	jLabel125.setForeground(Color.blue);
	jLabel123.setText(" Datos Receptor: ");
	jLabel123.setBounds(new Rectangle(10, 5, 110, 15));
	jLabel123.setOpaque(true);
	jLabel123.setForeground(Color.blue);
	jPanel8.add(jPanel7, null);
	jPanel3.add(jLabel19, null);
	jPanel3.add(jLabel18, null);
	jPanel3.add(jcestado, null);
	jPanel3.add(jcprioridad, null);
	//
	this.getContentPane().add(jLabel124, null);
	this.getContentPane().add(jLabel123, null);
	this.getContentPane().add(jLabel125, null);
	this.getContentPane().add(benviar, null);
	this.getContentPane().add(bcancelar, null);
	this.getContentPane().add(jPanel3, null);
	this.getContentPane().add(jPanel8, null);
	jScrollPane1.getViewport().add(jtinforme, null);
	jPanel1.add(jScrollPane1, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(bbuscarobra, null);
	this.getContentPane().add(jtobra, null);
	this.getContentPane().add(lblobra, null);
	jcprioridad.addItemListener(new ItemListener() {

				 public void itemStateChanged(ItemEvent evt) {
				     if (evt.getStateChange() == ItemEvent.SELECTED) {
					 idprioridad = LibSQL.getInt("Select idtipo from vecinos.tiposprioridad where descripcion='" + jcprioridad.getItemTexto() + "'", "");
				     }
				 }

			     }
	);
	//CARGAR COMBO: (jcareas, "SELECT nombre FROM estadoobras.tipoareas WHERE estado <> '*' ORDER BY idtipo", "Secretario de Obras P??????blicas");
	//idarea = LibSQL.getInt("SELECT idtipo FROM estadoobras.tipoareas Where nombre LIKE '" + jcareas.getItemTexto() + "' ", "");
	idresponsable = LibSQL.getInt("SELECT idresponsable FROM estadoobras.tipoareas WHERE idtipo = '" + idarea + "' AND estado <> '*' ", "");
	//jtresponsable.setText(LibSQL.getString???("SELECT apellido||', '||nombre FROM personas WHERE idpersona = '" + idresponsable + "' AND estado <> '*' "));
	//CARGAR COMBO: (jcestado, "Select descripcion from vecinos.tablacombo where combo='jcestador'", "En Tramite");
	//CARGAR COMBO: (jcprioridad, "Select descripcion from vecinos.tiposprioridad where estado<>'*'", "MEDIA");
	//new Rectangle(5, 333, 480, 5));
	jtobra.setFont(new Font("Dialog", 1, 11));
	jtobra.setForeground(Color.red);
	bbuscarobra.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       bbuscarobra_actionPerformed(e);
				   }

			       }
	);
	lblobra.setFont(new Font("Dialog", 1, 11));
	lblobra.setBounds(new Rectangle(10, 290, 40, 15));
	lblobra.setText("Obra:");
	bbuscarobra.setFont(new Font("Dialog", 1, 11));
	bbuscarobra.setBounds(new Rectangle(350, 303, 125, 25));
	bbuscarobra.setText("Buscar Obra");
	jtobra.setBounds(new Rectangle(10, 303, 325, 25));
	fecha = Proced.FechaHora2(true, false);
	bbuscarobra.setMnemonic('B');
	jtobra.setBackground(Color.white);
	jtobra.setEditable(false);
	jcestado.setBackground(Color.white);
	jcprioridad.setBackground(Color.white);
    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void keyTyped(KeyEvent keyevent) {
    }

    public void keyPressed(KeyEvent k) {

    }

    private void benviar_actionPerformed(ActionEvent e) {
	try {
	    idemisor = LibSQL.getInt("SELECT idpersona FROM personas WHERE alias = '" + Environment.sessionUser + "' ", "");
	    if (true) {
		idnovedad = LibSQL.getInt("SELECT max(idnew) from TABLA NEWS", "");
		String Q = "Insert into TABLA NEWS Values(" + idnovedad + ",'fecha','hora',0" + idemisor + ",0" + idarea + ",0" + idresponsable + ",'" + jtinforme.getText() + "','" + jcestado.getItemTexto() + "'," + idprioridad + ",0" + jtidobra.getText() + ",'false','')";
		//System.out.println(Q);
		if (LibSQL.getBoolean(Q, "")) {
		    //CERRAR VENTANA
		} else {
		    //ERROR
		}
	    } else {
		Advisor.messageBox("Debe llenar los campos Informe y Responsable", "Aviso");
	    }
	} catch (Exception x) {
	    Advisor.messageBox(x.getMessage(), "ERROR Fecha: ");
	}
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
	idnovedad = -1;
	this.dispose();
    }

    private void setearCampos() {
	//String area = LibSQL.getString???("SELECT nombre FROM estadoobras.tipoareas WHERE idtipo = (Select idarea From estadoobras.obras Where estado <> '*' And idobra = " + jtidobra.getText() + " )");
    }

    private void bbuscarobra_actionPerformed(ActionEvent e) {
	/*BuscarObras obras = new BuscarObras(jtobra, jtidobra);
	Proced.CentraVentana(obras);
	obras.setModal(true);
	obras.show();
	System.out.println("idobra: " + jtidobra.getText());
	if (!jtobra.equals("")) {
	    setearCampos();
	}*/
    }

}
