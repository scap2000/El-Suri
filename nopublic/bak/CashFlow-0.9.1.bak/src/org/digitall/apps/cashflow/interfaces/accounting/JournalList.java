package org.digitall.apps.cashflow.interfaces.accounting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class JournalList extends BasicPrimitivePanel{

    private BasicPanel findPanel = new BasicPanel();
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE,"From",false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE,"To",false);
    private TFInput tfFindAccount1 = new TFInput(DataTypes.STRING,"FindFromAccounting",false);
    private CBInput cbStartAccount = new CBInput(0,"Accounting",false);
    private PrintButton btnPrint = new PrintButton();
    private PrintButton btnReport = new PrintButton();
    private CBInput cbEndAccount = new CBInput(0,"Accounting",false);
    private TFInput tfFindAccount2 = new TFInput(0,"FindEvenAccounting",false);
    private DeleteButton btnClear1 = new DeleteButton();
    private DeleteButton btnClear2 = new DeleteButton();

    public JournalList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(537, 228));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 115));
	findPanel.setSize(new Dimension(739, 70));
	tfStartDate.setBounds(new Rectangle(15, 40, 95, 35));
	tfEndDate.setBounds(new Rectangle(170, 40, 95, 35));
	tfFindAccount1.setBounds(new Rectangle(15, 90, 140, 35));
	cbStartAccount.setBounds(new Rectangle(170, 90, 320, 35));
	btnPrint.setBounds(new Rectangle(235, 195, 28, 33));
	btnPrint.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnPrint_actionPerformed(e);
					 }

				     }
	);
	cbEndAccount.setBounds(new Rectangle(170, 130, 320, 35));
	tfFindAccount2.setBounds(new Rectangle(15, 130, 140, 35));
	btnClear1.setBounds(new Rectangle(500, 105, 20, 20));
	btnClear1.setSize(new Dimension(20, 20));
	btnClear1.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnClear1_actionPerformed(e);
				 }

			     }
	);
	btnClear2.setBounds(new Rectangle(500, 145, 20, 20));
	btnClear2.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnClear2_actionPerformed(e);
				 }

			     }
	);
	addButton(btnPrint);
	findPanel.add(btnClear2, null);
	findPanel.add(btnClear1, null);
	findPanel.add(tfFindAccount2, null);
	findPanel.add(cbEndAccount, null);
	findPanel.add(cbStartAccount, null);
	findPanel.add(tfFindAccount1, null);
	findPanel.add(tfEndDate, null);
	findPanel.add(tfStartDate, null);
	this.add(findPanel, BorderLayout.CENTER);
	cbStartAccount.autoSize();
	cbEndAccount.autoSize();
	
	tfFindAccount1.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 loadComboAccount(tfFindAccount1.getString(), cbStartAccount);
		     }
		 }

	     }
	);
	
	tfFindAccount2.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 loadComboAccount(tfFindAccount2.getString(), cbEndAccount);
		     }
		 }

	     }
	);
	
	tfStartDate.setValue(Proced.setFormatDate(Environment.currentYear +"-01-01", true));
	tfEndDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	btnClear1.setToolTipText("Limpiar Cuenta \"Desde\"");
	btnClear2.setToolTipText("Limpiar Cuenta \"Hasta\"");
	btnPrint.setToolTipText("Imprimir Libro Mayor");
        btnReport.setToolTipText("Imprimir Gráfico de cuenta(s) contable(s)");
	btnReport.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnReport_actionPerformed(e);
		}
	    });
	findPanel.setBorder(BorderPanel.getBorderPanel("Generar libro mayor"));
	addButton(btnReport);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede generar el Libro para una sola Cuenta o para un rango de Cuentas");
    }
    
    private void loadComboAccount(String _text, CBInput _combo){
	String param = "-1,'" + _text + "'";
	_combo.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsToJournal", param)); 
    }
    
    private Account getAccount(CBInput _combo){
	Account account = new Account();
	if (!_combo.getSelectedValue().equals("-1")){
	    account.setIDAccount(Integer.parseInt(""+ _combo.getSelectedValue()));
	    account.setCode(Integer.parseInt(""+ _combo.getSelectedValueRef()));	
	} else {
	    account.setCode(0); 
	}
	
	return account;
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	if (tfStartDate.getDate().length()>0 && tfEndDate.getDate().length()>0){
	    if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false),Proced.setFormatDate(tfEndDate.getDate(),false)) == 2) {
	        Advisor.messageBox("La fecha desde no puede ser mayor a la fecha hasta","Error");
	    }else{     
		if (!cbStartAccount.getSelectedValue().equals("-1")){
		    BasicReport report = new BasicReport(JournalList.class.getResource("xml/journal.xml"));
		    String params = getAccount(cbStartAccount).getCode() +","+ getAccount(cbEndAccount).getCode() +",'"+ Proced.setFormatDate(tfStartDate.getDate(), false) +"','"+ Proced.setFormatDate(tfEndDate.getDate(), false) +"', 0, 0";
		    report.setProperty("startdate", tfStartDate.getDate());
		    report.setProperty("enddate", tfEndDate.getDate());
                    report.setProperty("accountname", cbStartAccount.getSelectedItem());
		    report.setProperty("accountcode", cbStartAccount.getSelectedValueRef());
		    report.addTableModel("accounting.getJournal", params);
		    report.doReport();
		} else{
		    Advisor.messageBox("Cuenta(s) requerida(s)","Aviso");
		}
	    }
	    
	} else{
	    Advisor.messageBox("Rango de fechas requerido","Aviso");
	}
    }

    private void btnClear1_actionPerformed(ActionEvent e) {
	cbStartAccount.removeAllItems();
    }

    private void btnClear2_actionPerformed(ActionEvent e) {
	cbEndAccount.removeAllItems();
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	if (tfStartDate.getDate().length()>0 && tfEndDate.getDate().length()>0) {
	    if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false),Proced.setFormatDate(tfEndDate.getDate(),false)) == 2) {
		Advisor.messageBox("La fecha desde no puede ser mayor a la fecha hasta","Error");
	    }else {     
		if (cbStartAccount.getSelectedIndex() != -1) {
		    ExtendedInternalFrame _multiQueryDialog = new ExtendedInternalFrame("Gráfico de cuenta(s) contable(s)");
		    _multiQueryDialog.setSize(400, 300);
		    JArea _multiQuery = new JArea();
		    _multiQuery.setContentType("text/html");
		    _multiQuery.setEditable(false);

		    String _params = getAccount(cbStartAccount).getCode() +",'"+ Proced.setFormatDate(tfStartDate.getDate(), false) +"','"+ Proced.setFormatDate(tfEndDate.getDate(), false) +"', 0, 0";
		    ResultSet _results = LibSQL.exFunction("accounting.getMonthlyJournal", _params);

		    TimeSeries _serie1 = new TimeSeries(cbStartAccount.getSelectedItem().toString(), Month.class);

		    try {
			while (_results.next()) {
			    _serie1.add(new Month(_results.getInt("month"), _results.getInt("year")), _results.getInt("monto"));
			}
		    } catch (SQLException x) {
			x.printStackTrace();
		    }

		    TimeSeriesCollection _dataset = new TimeSeriesCollection();
		    _dataset.addSeries(_serie1);

		    if (cbEndAccount.getSelectedIndex() != -1) {
			TimeSeries _serie2 = new TimeSeries(cbEndAccount.getSelectedItem().toString(), Month.class);
			_params = getAccount(cbEndAccount).getCode() +",'"+ Proced.setFormatDate(tfStartDate.getDate(), false) +"','"+ Proced.setFormatDate(tfEndDate.getDate(), false) +"', 0, 0";
			_results = LibSQL.exFunction("accounting.getMonthlyJournal", _params);
    
			try {
			    while (_results.next()) {
				_serie2.add(new Month(_results.getInt("month"), _results.getInt("year")), _results.getInt("monto"));
			    }
			} catch (SQLException x) {
			    x.printStackTrace();
			}
		        _dataset.addSeries(_serie2);
		    }

		    JFreeChart _timeSeriesChart = ChartFactory.createTimeSeriesChart("Gráfico de cuenta(s) contable(s)", "Mes", "Monto", _dataset, true, true, false);

		    ChartPanel _chartPanel = new ChartPanel(_timeSeriesChart);

		    XYPlot plot = (XYPlot) _timeSeriesChart.getPlot();
		    plot.setDomainGridlinePaint(Color.black);
		    //plot.setRangeGridlinePaint(Color.black);
		    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		    plot.setDomainCrosshairVisible(true);
		    plot.setRangeCrosshairVisible(true);

		    XYItemRenderer r = plot.getRenderer();
		    if (r instanceof XYLineAndShapeRenderer) {
		        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
		        renderer.setShapesVisible(true);
		        renderer.setShapesFilled(true);
		    }
		    
		    /*Cuentas más utilizadas
		     * SELECT count(bookkeepingentrydetail.idaccount), name
			    FROM accounting.bookkeepingentrydetail, accounting.accounts
			    WHERE bookkeepingentrydetail.idaccount = accounts.idaccount
			    GROUP BY name
			    ORDER BY count(bookkeepingentrydetail.idaccount) DESC;
		     */

		    _multiQueryDialog.setCentralPanel(_chartPanel);
		    
		    _multiQueryDialog.setMaximizable(true);
		    _multiQueryDialog.setClosable(true);
		    _multiQueryDialog.setResizable(true);
		    _multiQueryDialog.setVisible(true);

		} else{
		    Advisor.messageBox("Cuenta(s) requerida(s)","Aviso");
		}
	    }
	    
	} else{
	    Advisor.messageBox("Rango de fechas requerido","Aviso");
	}
    }
}
