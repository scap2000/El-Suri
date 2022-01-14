/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * CashflowGraphics.java
 *
 * */
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

import java.util.Vector;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
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


public class CashflowGraphics extends BasicPrimitivePanel{

    private BasicPanel findPanel = new BasicPanel();
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE,"From",false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE,"To",false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING,"Buscar Cuenta",false);
    private CBInput cbStartAccount = new CBInput(0,"Accounting",false);
    private CBInput cbSelectedAccounts = new CBInput(0,"Cuentas seleccionadas",false);
    private PrintButton btnAccountsGraphics = new PrintButton();
    private AddComboButton btnAddAccount = new AddComboButton();
    private DeleteButton btnDeleteAccount = new DeleteButton();
    private Vector idCuentasFavoritas = new Vector();

    public CashflowGraphics() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(537, 204));
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(1, 115));
	findPanel.setSize(new Dimension(739, 70));
	tfStartDate.setBounds(new Rectangle(15, 25, 95, 35));
	btnAccountsGraphics.setBounds(new Rectangle(500, 170, 20, 20));
	tfEndDate.setBounds(new Rectangle(170, 25, 95, 35));
	tfFindAccount.setBounds(new Rectangle(15, 75, 140, 35));
	cbStartAccount.setBounds(new Rectangle(170, 75, 320, 35));
	cbSelectedAccounts.setBounds(new Rectangle(170, 115, 320, 35));
        btnAddAccount.setBounds(new Rectangle(500, 90, 20, 20));
	btnAddAccount.setSize(new Dimension(20, 20));
        btnAddAccount.setToolTipText("Agregar Cuenta Contable para reporte gráfico");
	btnAddAccount.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAddAccount_actionPerformed(e);
				 }

			     }
	);
	btnDeleteAccount.setBounds(new Rectangle(500, 130, 20, 20));
        //btnDeleteAccount.setToolTipText("");
	btnDeleteAccount.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDeleteAccount_actionPerformed(e);
				 }

			     }
	);
        findPanel.add(btnDeleteAccount, null);
        findPanel.add(btnAddAccount, null);
        findPanel.add(cbSelectedAccounts, null);
        findPanel.add(cbStartAccount, null);
        findPanel.add(tfFindAccount, null);
        findPanel.add(tfEndDate, null);
	findPanel.add(tfStartDate, null);
	//findPanel.add(btnAccountsGraphics, null);
	this.add(findPanel, BorderLayout.CENTER);
        this.addButton(btnAccountsGraphics);
	cbStartAccount.autoSize();
	cbSelectedAccounts.autoSize();
	
	tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			 loadComboAccount(tfFindAccount.getString(), cbStartAccount);
		     }
		 }

	     }
	);

        tfStartDate.setValue(Proced.setFormatDate(Environment.currentYear +"-01-01", true));
	tfEndDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	btnAddAccount.setToolTipText("Agregar cuenta");
	btnDeleteAccount.setToolTipText("Borrar cuenta seleccionada");
        btnAccountsGraphics.setToolTipText("Imprimir Gráfico de cuenta(s) contable(s)");
	btnAccountsGraphics.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAccountsGraphics_actionPerformed(e);
		}
	    });
	findPanel.setBorder(BorderPanel.getBorderPanel("Gráfico de cuenta(s) contable(s)"));
        btnAddAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);
        cargarCuentasFavoritas();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede generar gráficos de uno o varios ítems combinados");
    }
    
    private void loadComboAccount(String _text, CBInput _combo){
	String param = "-1,'" + _text + "'";
	_combo.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsToJournal", param)); 
        if (_combo.getCombo().getItemCount() > 0) {
            btnAddAccount.setEnabled(true);
        } else {
            btnAddAccount.setEnabled(false);
        }
    }
    
    private Account getAccount(CBInput _combo) {
	Account account = new Account();
	if (!_combo.getSelectedValue().equals("-1")){
	    account.setIDAccount(Integer.parseInt(""+ _combo.getSelectedValue()));
	    account.setCode(Integer.parseInt(""+ _combo.getSelectedValueRef()));	
	} else {
	    account.setCode(0); 
	}
	
	return account;
    }
    
    private Account getAccount(int _idAccount) {
        Account account = new Account();
        if (_idAccount > 0) {
            account.setIDAccount(_idAccount);
        } else {
            account.setCode(0); 
        }
        return account;
    }
    
    private void btnAddAccount_actionPerformed(ActionEvent e) {
	agregarCuentaFavorita(e);
    }

    private void btnDeleteAccount_actionPerformed(ActionEvent e) {
        borrarCuentaSeleccionada(e);
    }

    private void btnAccountsGraphics_actionPerformed(ActionEvent e) {
	generarGraficoDeCuentas();
    }
    
    private void generarGraficoDeCuentas() {
        idCuentasFavoritas = cbSelectedAccounts.getCombo().getValuesVector();
        if (tfStartDate.getDate().length()>0 && tfEndDate.getDate().length()>0) {
            if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getDate(),false),Proced.setFormatDate(tfEndDate.getDate(),false)) == 2) {
                Advisor.messageBox("La fecha desde no puede ser mayor a la fecha hasta","Error");
            } else {     
                if (idCuentasFavoritas.size() > 0) {
                    ExtendedInternalFrame _multiQueryDialog = new ExtendedInternalFrame("Gráfico de cuenta(s) contable(s)");
                    _multiQueryDialog.setSize(400, 300);
                    JArea _multiQuery = new JArea();
                    _multiQuery.setContentType("text/html");
                    _multiQuery.setEditable(false);
                    Vector _accountCodeVector = cbSelectedAccounts.getCombo().getRefValuesVector();
                    //guardar cuentas favoritas
                    TimeSeriesCollection _dataset = new TimeSeriesCollection();
                    for (int i = 0; i < _accountCodeVector.size();i++) {
                        String _params = _accountCodeVector.elementAt(i) +",'"+ Proced.setFormatDate(tfStartDate.getDate(), false) +"','"+ Proced.setFormatDate(tfEndDate.getDate(), false) +"', 0, 0";
                        ResultSet _results = LibSQL.exFunction("accounting.getMonthlyJournal", _params);
                        cbSelectedAccounts.getCombo().setSelectedIndex(i);
                        TimeSeries _serie = new TimeSeries(cbSelectedAccounts.getSelectedItem().toString(), Month.class);
                        try {
                            while (_results.next()) {
                                _serie.add(new Month(_results.getInt("month"), _results.getInt("year")), _results.getInt("monto"));
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                        }
                        _dataset.addSeries(_serie);
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

                } else {
                    Advisor.messageBox("Cuenta(s) requerida(s)","Aviso");
                }
            }
            
        } else{
            Advisor.messageBox("Rango de fechas requerido","Aviso");
        }
    }

    public void setidCuentasFavoritas(Vector idCuentasFavoritas) {
        this.idCuentasFavoritas = idCuentasFavoritas;
    }

    public Vector getidCuentasFavoritas() {
        return idCuentasFavoritas;
    }

    private void agregarCuentaFavorita(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            if (Advisor.question("Aviso", "¿Está seguro de agregar todas las cuentas?")) {
                cbSelectedAccounts.removeAllItems();
                idCuentasFavoritas = cbStartAccount.getCombo().getValuesVector();
                Vector _refCuentasFavoritas = cbStartAccount.getCombo().getRefValuesVector();
                Vector _cuentasFavoritas = cbStartAccount.getCombo().getItemsVector();
                for (int i = 0; i < cbStartAccount.getCombo().getItemCount(); i++) {
                    cbSelectedAccounts.getCombo().addItem(_cuentasFavoritas.elementAt(i), idCuentasFavoritas.elementAt(i), _refCuentasFavoritas.elementAt(i));
                }
                guardarCuentasFavoritas();
                btnDeleteAccount.setEnabled(true);
                btnAccountsGraphics.setEnabled(true);
            }
        } else {
            if (!cbSelectedAccounts.getCombo().getItemsVector().contains(cbStartAccount.getSelectedItem())) {
                cbSelectedAccounts.getCombo().addItem(cbStartAccount.getSelectedItem(), cbStartAccount.getSelectedValue(), cbStartAccount.getSelectedValueRef());
                btnDeleteAccount.setEnabled(true);
                btnAccountsGraphics.setEnabled(true);
                idCuentasFavoritas = cbSelectedAccounts.getCombo().getValuesVector();
                guardarCuentasFavoritas();
            } else {
                Advisor.messageBox("La cuenta \"" + cbStartAccount.getSelectedItem() +  "\" ya fue agregada","Aviso");
            }
        }
    }

    private void borrarCuentaSeleccionada(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            if (Advisor.question("Aviso", "¿Está seguro de borrar todas las cuentas seleccionadas?")) {
                cbSelectedAccounts.removeAllItems();
                guardarCuentasFavoritas();
            }
        } else {
            cbSelectedAccounts.removeItemAt(cbSelectedAccounts.getSelectedIndex());
        }
        if (cbSelectedAccounts.getCombo().getItemCount() <= 0) {
            btnDeleteAccount.setEnabled(false);
            btnAccountsGraphics.setEnabled(false);
            guardarCuentasFavoritas();
        }
    }

    private void guardarCuentasFavoritas() {
        String _cuentasFavoritas = "";
        for (int i = 0; i < idCuentasFavoritas.size();i++) {
            _cuentasFavoritas += idCuentasFavoritas.elementAt(i).toString() + ",";
        }
        Environment.cfg.setProperty("cuentasfavoritas",_cuentasFavoritas);
    }

    private void cargarCuentasFavoritas() {
	if (Environment.cfg.hasProperty("cuentasfavoritas")) {
	    String _cuentasFavoritas = Environment.cfg.getProperty("cuentasfavoritas");
	    if (_cuentasFavoritas.length() > 0) {
		String[] _idCuentasFavoritas = _cuentasFavoritas.split(",");
		for (int i=0; i < _idCuentasFavoritas.length; i++) {
		    idCuentasFavoritas.add(_idCuentasFavoritas[i]);
		    Account _account = new Account();
		    _account.setIDAccount(Integer.parseInt(_idCuentasFavoritas[i]));
		    _account.retrieveData();
		    cbSelectedAccounts.getCombo().addItem("(" + _account.getCode() + ") - " + _account.getFullName(), _account.getIDAccount(), _account.getCode());
		}
		btnDeleteAccount.setEnabled(true);
	    }
	}
    }
}
