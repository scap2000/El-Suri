package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.ReloadButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

import org.postgresql.util.PSQLException;

public class FunctionsList extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private int[] colSizes = { 233, 112, 332, 366, 150 };
    private GridPanel storeList = new GridPanel(0, colSizes, "Listado de SP", dataRow);
    private CBInput cbDatabases = new CBInput(CachedCombo.DATABASE,"DataBase",false);
    private CBInput cbSchemes = new CBInput(CachedCombo.SCHEME,"Scheme",false);
    private SQLTextPane textPane;
    private BasicScrollPane scrollPane;
    private SQLDocument doc = new SQLDocument();
    private DeleteButton btnDelete = new DeleteButton();
    private AddButton btnNew = new AddButton();
    private TFInput tfFind = new TFInput(DataTypes.STRING,"Find",false);
    private FindButton btnFind = new FindButton();
    private ProcedureClass procedure;
    private BasicPanel content = new BasicPanel();
    private BasicPanel toolsPanel = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicRadioButton rbtnFindSrc = new BasicRadioButton();
    private BasicRadioButton rbtnFindDescription = new BasicRadioButton();
    private AssignButton btnConnect = new AssignButton();
    private ReloadButton btnSearchDifferences = new ReloadButton();
    private SaveDataButton btnLog = new SaveDataButton();
    private JPanel jpNorth = new JPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private AssignButton btnExportSelecteds = new AssignButton();
    private AcceptButton btnNewFromSelected = new AcceptButton();
    private ModifyButton btnModify = new ModifyButton();

    public FunctionsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 1000, 750));
	this.setSize(new Dimension(1000, 731));
	textPane = new SQLTextPane();
	/**
	 * Cargo el listado de funciones en la configuracion del SQLDocument
	 * por ahora no lo cargo porque se pone muy pesado el editor
	 * */
	/*ResultSet list = SQLExecutor.exQuery(getAllProcs("",""));
	try {
	    while (list.next()) {
		doc.addSQLFunctionKeyword(list.getString(2));
	    }
	} catch (SQLException x) {
	    x.printStackTrace();
	}*/
	textPane.setDocument(doc);
	scrollPane = new BasicScrollPane(textPane);
	textPane.setEditable(false);
        scrollPane.setSize(new Dimension(1000, 650));
        cbDatabases.setBounds(new Rectangle(685, 5, 160, 35));	
	cbSchemes.setBounds(new Rectangle(10, 5, 175, 35));
	cbSchemes.addItemListener(new ItemListener() {

			       public void itemStateChanged(ItemEvent e) {
				   if (e.getStateChange() == ItemEvent.SELECTED) {
				       if (cbSchemes.getSelectedIndex() > -1) {
					   refresh();
				       }
				   }
			       }

			   }
	);
	btnDelete.setBounds(new Rectangle(975, 20, 20, 20));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	btnNew.setBounds(new Rectangle(950, 20, 20, 20));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	tfFind.setBounds(new Rectangle(395, 5, 255, 35));
	btnFind.setBounds(new Rectangle(655, 20, 20, 20));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);

	btnNewFromSelected.setBounds(new Rectangle(890, 20, 20, 20));
	btnNewFromSelected.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnNewFromSelected_actionPerformed(e);
					  }

				      }
	);
	btnModify.setBounds(new Rectangle(920, 20, 20, 20));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	toolsPanel.setLayout(null);
	toolsPanel.setPreferredSize(new Dimension(1, 45));
	rbtnFindSrc.setText("Codigo");
	rbtnFindSrc.setBounds(new Rectangle(205, 20, 77, 18));
	rbtnFindDescription.setText("Descripcion");
	rbtnFindDescription.setBounds(new Rectangle(290, 20, 104, 18));
	rbtnFindDescription.setSize(new Dimension(104, 18));
	rbtnFindDescription.setSelected(true);
	btnConnect.setBounds(new Rectangle(855, 20, 20, 20));
	btnConnect.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnConnect_actionPerformed(e);
				  }

			      }
	);
	btnSearchDifferences.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSearchDifferences_actionPerformed(e);
		    }

		});
	btnLog.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnLog_actionPerformed(e);
			      }

			  }
	);
	jpNorth.setLayout(borderLayout2);
	btnExportSelecteds.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnExportSelecteds_actionPerformed(e);
					  }

				      }
	);
	toolsPanel.add(btnConnect, null);
	toolsPanel.add(rbtnFindDescription, null);
	toolsPanel.add(rbtnFindSrc, null);
	toolsPanel.add(btnFind, null);
	toolsPanel.add(tfFind, null);
	toolsPanel.add(btnNew, null);
	toolsPanel.add(btnNewFromSelected, null);
	toolsPanel.add(btnModify, null);
	toolsPanel.add(btnDelete, null);
	toolsPanel.add(cbDatabases, null);
	toolsPanel.add(cbSchemes, null);
	content.add(scrollPane, BorderLayout.CENTER);
	jpNorth.add(toolsPanel, BorderLayout.NORTH);
	jpNorth.add(storeList, BorderLayout.CENTER);
	content.add(jpNorth, BorderLayout.NORTH);
	this.add(content, null);
	cbDatabases.autoSize();
	cbSchemes.autoSize();
	tfFind.getTextField().addKeyListener(new KeyAdapter() {

					  public void keyReleased(KeyEvent e) {
					      if (e.getKeyCode() == KeyEvent.VK_ENTER)
						  refresh();
					  }

				      }
	);
	ButtonGroup rbtn = new ButtonGroup();
	rbtn.add(rbtnFindDescription);
	rbtn.add(rbtnFindSrc);
	addButton(btnLog);
	addButton(btnSearchDifferences);
	setHeaderList();
	cbDatabases.setSelectedValue(SQLExecutor.getDataBase().split("/")[3]);
	storeList.setBorder(BorderPanel.getBorderPanel("Listado de SP en: " + SQLExecutor.getDataBase()));
	addButton(btnExportSelecteds);
	cbSchemes.setGeneralItem(true);
    }

    private void refreshProc() {
	procedure = new ProcedureClass();
	procedure.setID(Integer.parseInt(dataRow.elementAt(0).toString()));
	doc = new SQLDocument();
	textPane.setDocument(doc);
	textPane.setText("");
	textPane.setText(procedure.getProcedureString());
	textPane.setCaretPosition(0);
    }

    private void setHeaderList() {
	Vector<String> header = new Vector();
	header.add("*");
	header.add("Procedure");
	header.add("Devuelve");
	header.add("Parametros");
	header.add("@Parametros");
	header.add("*");
	header.add("Fecha");

	storeList.getTable().addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
		    refreshProc();
		}
	    }
	});
	
	storeList.getTable().addMouseListener(new MouseAdapter() {
	       public void mouseClicked(MouseEvent me) {
		   if (me.getClickCount() == 1 && me.getButton() == MouseEvent.BUTTON1) {
		       refreshProc();
		   } else if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1) {
			editSelectedProc();
		   }
	       }
	   }
	);
	
	storeList.setParams("", "", header);
    }

    public void refresh() {
	String _scheme = cbSchemes.getSelectedItem().toString();
	String _filter = tfFind.getString();
	storeList.setQuery(getAllProcs(_scheme, _filter));
	/*String params = "'"+ cbSchemes.getSelectedItem().toString() +"','"+ tfFind.getText() +"'";
	storeList.refresh(params);*/
    }

    public String getAllProcs(String _scheme, String _filter) {
	String _schemeFilter = (cbSchemes.getSelectedValue().toString().equals("-1")?"":" AND n.nspname = '" + _scheme + "' ");
	String query = "SELECT p.oid AS prooid, p.proname, pg_catalog.format_type(p.prorettype, NULL) AS proresult, " +
			"CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') " +
			"END AS proargnames, pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, pl.lanname AS prolanguage," +
			"CASE WHEN pg_catalog.obj_description(p.oid, 'pg_proc') IS NULL THEN '' " +
			"ELSE pg_catalog.obj_description(p.oid, 'pg_proc') END AS procomment, prosrc FROM pg_catalog.pg_proc p " +
			"INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace " +
			"INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " +
			"WHERE NOT p.proisagg  " + _schemeFilter + "AND lanname = 'plpgsql'" +
			"AND UPPER(proname) LIKE UPPER('%" + _filter + "%') ORDER BY p.proname, proresult;";
	
	if (rbtnFindSrc.isSelected()) {
	    query =  "SELECT p.oid AS prooid, p.proname, pg_catalog.format_type(p.prorettype, NULL) AS proresult,"+
		    " CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') "+
		    " END AS proargnames, pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, pl.lanname AS prolanguage,"+
		    " CASE WHEN pg_catalog.obj_description(p.oid, 'pg_proc') IS NULL THEN '' "+
		    " ELSE pg_catalog.obj_description(p.oid, 'pg_proc') END AS procomment, prosrc FROM pg_catalog.pg_proc p "+
		    " INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace "+
		    " INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang "+
		    "WHERE NOT p.proisagg  " + _schemeFilter + "AND lanname = 'plpgsql'" +
		    " AND upper(prosrc) like upper('%" + _filter +"%')"+
		    " ORDER BY p.proname, proresult;";
	}
	return query;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	ProcEditor editor = new ProcEditor(0);
	//editor.setDocument(doc);
        editor.setMaximizable(true);//linea agregada
        editor.setResizable(true);
        editor.setClosable(true);
        editor.setVisible(true);
        editor.show();//linea agregada
        
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	boolean delete = false;
	Vector selecteds = storeList.getSelectedsID();
	if (selecteds.size() > 0) {
	    if (Advisor.question("Borrar procedimiento", "¿Está seguro de borrar " + selecteds.size() +" procedimiento(s)?" )) {
	        for (int i = 0; i < selecteds.size(); i++) {
	            ProcedureClass proc = new ProcedureClass();
	            proc.setID(new Integer(selecteds.elementAt(i).toString()));
	            String query ="DROP FUNCTION " + proc.getDeclaration() + ";";
	            SQLExecutor.execute(query, true);
	        }
		delete = true;
	    }
	} else {
	    if (!dataRow.isEmpty()) {
	        String query ="DROP FUNCTION " + procedure.getDeclaration() + ";";
	        if (Advisor.question("Borrar procedimiento", "¿Está seguro de borrar " + procedure.getDeclaration() + "?" )) {
	            SQLExecutor.execute(query, true);
		    delete = true;
	        } else {
	            //Advisor.messageBox("Error al eliminar el procedimiento\n" + procedure.getSchemeName() + "." + procedure.getProcedureName(), "Error");
	        }
	    }
	}
	if (delete) {
	    refresh();
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnConnect_actionPerformed(ActionEvent e) {
	String dataBase = cbDatabases.getSelectedItem().toString();
	SQLExecutor.setDataBase(dataBase);
	SQLExecutor.closeConnection();
	if (SQLExecutor.isConnected()) {
	    //cbDatabases.loadJCombo("SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn ORDER BY datname");
	    cbDatabases.update();
	    cbDatabases.setSelectedValue(dataBase);
	    cbSchemes.update();
	    storeList.setBorder(BorderPanel.getBorderPanel("Listado de SP en: " + SQLExecutor.getDataBase()));
	    refresh();
	} else {
	    Advisor.messageBox("Error al intentar conectar a la base de datos " + cbDatabases.getSelectedItem().toString(), "Error");
	}
    }

    private void btnLog_actionPerformed(ActionEvent e) {
	SQLExecutor.showLog();
    }

    private void btnExportSelecteds_actionPerformed(ActionEvent e) {
	exportSelecteds();
    }

    private void exportSelecteds() {
	Vector selecteds = storeList.getSelectedsID();
	
	JArea taSQLLog = new JArea();
	//BasicDialog sqlDialog = new BasicDialog();
	BasicInternalFrame sqlDialog = new BasicInternalFrame("SQL Export");
        sqlDialog.setClosable(true);
	//sqlDialog.setTitle("SQL Export");
	sqlDialog.setTitle("SQL Export");
	sqlDialog.setLayout(new BorderLayout());
	sqlDialog.setSize(600, 600);
	sqlDialog.getContentPane().add(taSQLLog, BorderLayout.CENTER);
	StringBuffer _sql = new StringBuffer();	
	for (int i = 0 ; i < selecteds.size(); i++)  {
	    ProcedureClass _procedure = new ProcedureClass();
	    _procedure.setID(Integer.parseInt(selecteds.elementAt(i).toString()));
	    _sql.append(_procedure.getProcedureString() + _procedure.getCommentDeclaration());
	}
	taSQLLog.setText(new String(_sql));
	sqlDialog.setVisible(true);
	sqlDialog.show();
    }

    private void editSelectedProc() {
	if (procedure != null) {
	    //ProcEditor editor = new ProcEditor(procedure.getID());//linea comentada
	    ProcEditor editor = new ProcEditor(procedure.getDeclaration(),procedure.getID());
	    //editor.setDocument(doc);  
	    editor.setTitle(procedure.getDeclaration());
	    //editor.setVisible(true); //linea comentada
            editor.setMaximizable(true);//linea agregada
            editor.setResizable(true);
	    editor.setClosable(true);
            editor.show();//linea agregada
	}
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	editSelectedProc();
    }

    private void btnNewFromSelected_actionPerformed(ActionEvent e) {
	if (procedure != null) {
	    ProcEditor editor = new ProcEditor(0);
	    editor.setText(procedure.getProcedureString());
	    editor.setMaximizable(true);//linea agregada
	    editor.setResizable(true);
	    editor.setClosable(true);
	    editor.show();
	    editor.setVisible(true);
	}
    }

    private void btnSearchDifferences_actionPerformed(ActionEvent e) {
	String _scheme = cbSchemes.getSelectedItem().toString();
	String _schemeFilter = (cbSchemes.getSelectedValue().toString().equals("-1")?"":" AND n.nspname = '" + _scheme + "' ");
	Vector <ProcedureClass> proceduresServerOrigin = new Vector<ProcedureClass>();
	String query = "SELECT p.oid AS prooid/*, p.proname, pg_catalog.format_type(p.prorettype, NULL) AS proresult, " +
			"CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') " +
			"END AS proargnames, pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, pl.lanname AS prolanguage," +
			"CASE WHEN pg_catalog.obj_description(p.oid, 'pg_proc') IS NULL THEN '' " +
			"ELSE pg_catalog.obj_description(p.oid, 'pg_proc') END AS procomment, prosrc */ FROM pg_catalog.pg_proc p " +
			"INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace " +
			"INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " +
			"WHERE NOT p.proisagg  " + _schemeFilter + "AND lanname = 'plpgsql'" +
			"ORDER BY p.proname/*, proresult*/";

	ResultSet _results = LibSQL.exQuery(query);
	
	try {
	    while (_results.next()) {
		ProcedureClass _proc = new ProcedureClass();
		_proc.setID(_results.getInt("prooid"));
		proceduresServerOrigin.add(_proc);
	    }
	} catch (SQLException x) {
	    //ignore
	}
	for (int i = 0; i < proceduresServerOrigin.size(); i++) {
	 //   System.out.println(proceduresServerOrigin.elementAt(i).getDeclaration());
	}
	System.out.println(proceduresServerOrigin.size() + " procedimiento(s) encontrado(s)");

    }

}
