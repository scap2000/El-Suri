package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

public class QueryTextArea extends BasicDialog {
//public class QueryTextArea extends JDialog {

    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicButton dbConnect = new BasicButton();

    private BasicScrollPane spDescripcion = new BasicScrollPane();
    private BasicPanel jPanel2 = new BasicPanel("Exportar Privilegios");

    private BasicLabel lblDescripcion = new BasicLabel();

    private JTextArea taDescripcion = new JTextArea();
    
    private Vector dbVector = new Vector();
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();
    private String _dbName = "";
    private String selectedDB = "";
    int numeroFuncion = -1;
    int opcion = -1;
    private StringBuilder query;

    public QueryTextArea(){
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public QueryTextArea(StringBuilder _query){
	query = _query;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(665, 510));
	this.setTitle("DBAdmin ");
	this.setBackground(Color.black);
	btnAccept.setBounds(new Rectangle(535, 425, 60, 50));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
        btnAccept.setText("Ejecutar\nScript");
	spDescripcion.getViewport().add(taDescripcion, null);
	taDescripcion.setEditable(false);
	jPanel2.add(dbConnect, null);
	jPanel2.add(spDescripcion, null);
	jPanel2.add(lblDescripcion, null);
        this.getContentPane().add(btnClose, null);
        this.getContentPane().add(jPanel2, null);
        this.getContentPane().add(btnAccept, null);
        jPanel2.setBounds(new Rectangle(0, 0, 655, 420));
	jPanel2.setLayout(null);
	dbConnect.setText("Conectar");
	dbConnect.setBounds(new Rectangle(245, 40, 80, 20));
	dbConnect.setToolTipText("null");
	dbConnect.setSize(new Dimension(80, 20));
        btnClose.setBounds(new Rectangle(600, 417, 60, 50));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
        btnClose.setText("Cerrar");
	lblDescripcion.setText("Consulta");
	lblDescripcion.setBounds(new Rectangle(10, 25, 145, 15));

	spDescripcion.setBounds(new Rectangle(10, 45, 635, 365));
	taDescripcion.setText(query.toString());
        
        taDescripcion.select(0,taDescripcion.getText().length());
    }

    private void loadStuff() {
	/*cbDatabases.loadJCombo("SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn");
	dbVector = cbDatabases.getItemsVector();
	cbDatabases.setSelectedItem(LibSQL.getDataBase().split("/")[3]);*/
	//loadQuery();
    }
       
    private void btnAccept_actionPerformed(ActionEvent e) {
	if(!taDescripcion.getText().equals("")){
	    if (Advisor.question("DBAdmin", "¿Confirma la ejecución del script?")) {
		if(LibSQL.exActualizar('a',taDescripcion.getText())){
		    Advisor.messageBox("Consulta ejecutada exitosamente", "Aviso");
		}
	    }
	}
	else{
	    Advisor.messageBox("Consulta vacía", "Aviso");
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void limpiarCampos() {
	taDescripcion.setText("");
    }
    
   private void btnClear_actionPerformed(ActionEvent e) {
	taDescripcion.setText("");
    }

    private void btnGetQuery_actionPerformed(ActionEvent e) {
	loadQuery();
    }

    private void loadQuery() {
	StringBuilder query = new StringBuilder();
	//ResultSet _securityDefiner = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname) AS foo;");
	ResultSet _securityDefiner = LibSQL.exQuery("SELECT 'ALTER FUNCTION '|| n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ') SECURITY DEFINER' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname;");
	
	try {
	    while (_securityDefiner.next()) {
		//query.append("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
		query.append(_securityDefiner.getString("declaration")+";\n");
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	taDescripcion.setText(query.toString());
    }
}
