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
 * BasicReport.java
 *
 * */
package org.digitall.common.reports;

import java.awt.Dimension;

import java.net.URL;

import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

import org.jfree.report.modules.gui.base.PreviewExtendedInternalFrame;
import org.jfree.ui.RefineryUtilities;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.TableDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewDialog;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewFrame;
import org.pentaho.reporting.engine.classic.core.modules.misc.tablemodel.JoiningTableModel;
import org.pentaho.reporting.engine.classic.core.modules.misc.tablemodel.ResultSetTableModelFactory;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

public class BasicReport {

    private URL url;
    private String dataFactoryName = "default";
    private Vector<TableModel> tableModels = new Vector<TableModel>();
    private Properties properties = new Properties();

    public BasicReport(URL _url) {
	url = _url;
    }

    public void addTableModel(String _function, String _params) {
	try {
	    tableModels.add(ResultSetTableModelFactory.getInstance().createTableModel(LibSQL.exFunction(_function, _params)));
	} catch (SQLException e) {
	    Advisor.messageBox(e.getMessage(), "Error al intentar definir el reporte");
	}
    }

    public void addTableModel(String _function, Object _params) {
        try {
            tableModels.add(ResultSetTableModelFactory.getInstance().createTableModel(LibSQL.exFunction(_function, _params)));
        } catch (SQLException e) {
            Advisor.messageBox(e.getMessage(), "Error al intentar definir el reporte");
        }
    }


    public void addTableModel(DefaultTableModel _tableModel) {
	tableModels.add(_tableModel);
    }

    public JComponent getPresentationComponent() {
	return new JPanel();
    }


    /**CODIGO CLAVE PARA GENERAR UN REPORTE
    BasicReport repo = new BasicReport(BudgetList.class.getResource("xml/basicReport.xml"));
    repo.addTableModel("resourcescontrol.xmlGetResourcesRequest", "3");
    repo.addTableModel("resourcescontrol.xmlGetAllResourcesRequestDetail", "3");
    repo.doReport();
    */
    public void doReport() {
	if (!(tableModels.size() > 0)) {
	    Advisor.messageBox("Debe agregar al menos un tablemodel", "Error");
	} else {
	    
	    final ResourceManager resourceManager = new ResourceManager();
	    resourceManager.registerDefaults();

	    try {
	        Resource directly = resourceManager.createDirectly(url, MasterReport.class);
	        MasterReport report = (MasterReport) directly.getResource();
		report.getParameterValues().put("location", OrganizationInfo.getOrgName());
	        report.getParameterValues().put("orgname", OrganizationInfo.getOrgName());
	        report.getParameterValues().put("orgtitle", OrganizationInfo.getTitle());
		report.getParameterValues().put("shortname", OrganizationInfo.getShortName());
		report.getParameterValues().put("orgdata", OrganizationInfo.getOrgName() + " (C.U.I.T. Nº " + OrganizationInfo.getCuit() + ")");
		report.getParameterValues().put("address", OrganizationInfo.getAddress());
		report.getParameterValues().put("shortaddress", OrganizationInfo.getShortAddress());
		report.getParameterValues().put("country", OrganizationInfo.getCountry());
		report.getParameterValues().put("cuit", OrganizationInfo.getCuit());
		report.getParameterValues().put("description", OrganizationInfo.getDescription());
		report.getParameterValues().put("city", OrganizationInfo.getLocation());
	        report.getParameterValues().put("province", OrganizationInfo.getProvince());
	        report.getParameterValues().put("locationdata", "Provincia de " + OrganizationInfo.getProvince() + " - " + OrganizationInfo.getCountry());
		report.getParameterValues().put("zipcode", OrganizationInfo.getZipCode());
		report.getParameterValues().put("webaddress",OrganizationInfo.getWebAddress()); 
		report.getParameterValues().put("phonenumber1",OrganizationInfo.getPhoneNumber1()); 
		report.getParameterValues().put("phonenumber2",OrganizationInfo.getPhoneNumber2()); 
    
	        report.getParameterValues().put("logo", OrganizationInfo.getLeftLogo());
		report.getParameterValues().put("escudo", OrganizationInfo.getRightLogo());
	        //report.getParameterValues().put("transparencylogo", OrganizationInfo.getTransparencyLogo());
		report.getParameterValues().put("actualdate", Proced.setFormatDate(Environment.currentDate, true));
		
		Enumeration propNames = properties.propertyNames();
		for (; propNames.hasMoreElements(); ) {
		    // Get property name
		    String propName = (String)propNames.nextElement();
		    // Get property value
		    Object propValue = properties.get(propName);
		    report.getParameterValues().put(propName, propValue);
		}
    
		JoiningTableModel joiningTableModel = new JoiningTableModel();
		for (int i = 0; i < tableModels.size(); i++) {
		    joiningTableModel.addTableModel("table_" + i, tableModels.elementAt(i));
		}
		report.setDataFactory(new TableDataFactory(dataFactoryName, joiningTableModel));
		attemptPreviewIFrame(report);
	    } catch (ResourceException e) {
		if (Advisor.question(e.getMessage(), "Error al intentar generar el reporte, quizás el motor no esté iniciado, ¿Desea iniciarlo ahora?")) {
		    ClassicEngineBoot.getInstance().start();
		    if (Advisor.question("Reporte", "¿Desea reintentar la generación del reporte?")) {
			doReport();
		    }
		}
	    } catch (Exception e) {
		Advisor.messageBox(e.getMessage(), "Error al intentar generar el reporte");
	    }
	}
    }

    public URL getDemoDescriptionSource() {
	return null;
    }

    public String getTitulo() {
	return "Titulo";
    }

    public void setDataFactoryName(String _dataFactoryName) {
	dataFactoryName = _dataFactoryName;
    }

    public void attemptPreview(MasterReport _report) {
	final PreviewFrame frame = new PreviewFrame(_report);
	frame.pack();
	frame.setVisible(true);
	frame.requestFocus();
    }

    public void attemptPreviewDialog(MasterReport _report) {
	final PreviewDialog frame = new PreviewDialog(_report);
	frame.pack();
	RefineryUtilities.positionFrameRandomly(frame);
	frame.setVisible(true);
	frame.requestFocus();
    }


    public void attemptPreviewIFrame(MasterReport _report) {
	final PreviewExtendedInternalFrame iframe = new PreviewExtendedInternalFrame(_report);
	iframe.pack();
	iframe.requestFocus();
	iframe.setResizable(true);
	iframe.setIconifiable(true);
	iframe.setMaximizable(true);
	iframe.setInfo(_report.getName());
	System.out.println(url);
	iframe.show();
	iframe.setMaximum(false);
	iframe.setSize(new Dimension(700, iframe.getDesktop().getHeight()));
	iframe.setMaximum(true);
	iframe.setMaximum(false);
    }

    public void setProperty(String key, Object value){
        properties.put(key, value);
    }
    
    public void testReport() {
	/*if (!(tableModels.size() > 0)) {
	    Advisor.messageBox("Debe agregar al menos un tablemodel", "Error");
	} else {
	    ReportGenerator _generator = ReportGenerator.createInstance();
	    MasterReport report;
	    try {
		report = _generator.parseReport(url);

	        final PageHeader _header = new PageHeader(true,true);
	        _header.getStyle().setStyleProperty(ElementStyleSheet.MINIMUMSIZE, new FloatDimension(0, 24));
	        _header.setName("header");
	        
	        TextFieldElementFactory _location = new TextFieldElementFactory();
	        _location.setFieldname("location");
	        //_location.setAbsolutePosition(new Point2D.Double(0,4));
	        _location.setHorizontalAlignment(ElementAlignment.LEFT);
	        _location.setVerticalAlignment(ElementAlignment.MIDDLE);
	        _location.setY(0f);
	        //_location.setHeight(100f);
	        _location.setMinimumSize(new FloatDimension(100, 24));
	        _header.addElement(_location.createElement());

		
		report.getParameterValues().put("location", OrganizationInfo.getOrgName());
		report.getParameterValues().put("orgname", OrganizationInfo.getOrgName());
		report.getParameterValues().put("orgtitle", OrganizationInfo.getTitle());
		report.getParameterValues().put("shortname", OrganizationInfo.getShortName());
		report.getParameterValues().put("orgdata", OrganizationInfo.getOrgName() + " (C.U.I.T. Nº " + OrganizationInfo.getCuit() + ")");
		report.getParameterValues().put("address", OrganizationInfo.getAddress());
		report.getParameterValues().put("shortaddress", OrganizationInfo.getShortAddress());
		report.getParameterValues().put("country", OrganizationInfo.getCountry());
		report.getParameterValues().put("cuit", OrganizationInfo.getCuit());
		report.getParameterValues().put("description", OrganizationInfo.getDescription());
		report.getParameterValues().put("city", OrganizationInfo.getLocation());
		report.getParameterValues().put("province", OrganizationInfo.getProvince());
		report.getParameterValues().put("zipcode", OrganizationInfo.getZipCode());
		report.getParameterValues().put("webaddress",OrganizationInfo.getWebAddress()); 
		report.getParameterValues().put("phonenumber1",OrganizationInfo.getPhoneNumber1()); 
		report.getParameterValues().put("phonenumber2",OrganizationInfo.getPhoneNumber2()); 
	
		report.getParameterValues().put("logo", OrganizationInfo.getLeftLogo());
		report.getParameterValues().put("escudo", OrganizationInfo.getRightLogo());
	        //report.getParameterValues().put("transparencylogo", OrganizationInfo.getTransparencyLogo());
		report.getParameterValues().put("actualdate", Proced.setFormatDate(Environment.currentDate, true));
		
		Enumeration propNames = properties.propertyNames();
		for (; propNames.hasMoreElements(); ) {
		    // Get property name
		    String propName = (String)propNames.nextElement();
		    // Get property value
		    Object propValue = properties.get(propName);
		    report.getParameterValues().put(propName, propValue);
		}
	
		JoiningTableModel joiningTableModel = new JoiningTableModel();
		for (int i = 0; i < tableModels.size(); i++) {
		    joiningTableModel.addTableModel("table_" + i, tableModels.elementAt(i));
		}
		report.setDataFactory(new TableDataFactory(dataFactoryName, joiningTableModel));

	        CountryReportAPIDemoHandler a = new CountryReportAPIDemoHandler();
	        /*while (report.getPageHeader().getElementArray().length>0) {
	            report.getPageHeader().removeElement(report.getPageHeader().getElementArray()[i]);
	        }
	        while (report.getGroup(0).getHeader().getElementArray().length>0) {
	            report.getGroup(0).getHeader().removeElement(report.getGroup(0).getHeader().getElementArray()[i]);
	        }*/
		//report.setPageHeader(_header);
	     /*   report.setReportHeader(a.createReport().getReportHeader());
		attemptPreviewIFrame(report);
	    } catch (ResourceException e) {
		Advisor.messageBox(e.getMessage(), "Error al intentar generar el reporte");
	    } catch (IOException e) {
		Advisor.messageBox(e.getMessage(), "Error al intentar generar el reporte");
	    } catch (Exception e) {
		Advisor.messageBox(e.getMessage(), "Error al intentar generar el reporte");
	    }
	}*/
    }
    
/*    private void addDefaultFooter(JFreeReport _report) {
	
	 PageFooter _footer = new PageFooter(true,true);
	 _footer.getStyle().setStyleProperty(ElementStyleSheet.MINIMUMSIZE, new FloatDimension(0, 24));
	 _footer.setName("footer");
	 
	 TextFieldElementFactory _location = new TextFieldElementFactory();
	 _location.setFieldname("location");
	 //_location.setAbsolutePosition(new Point2D.Double(0,4));
	 _location.setHorizontalAlignment(ElementAlignment.LEFT);
	 _location.setVerticalAlignment(ElementAlignment.MIDDLE);
	 _location.setY(0f);
	 //_location.setHeight(100f);
	 _location.setMinimumSize(new FloatDimension(100, 24));
	 _footer.addElement(_location.createElement());
	 report.setPageFooter(_footer);
	<report>
	    <pagefooter height="24">
		<rectangle name="PF0" x="0" y="0" width="100%" height="100%" color="#FFFFFF" weight="0" draw="false" fill="true"/>
		<line x1="0" y1="0" x2="100%" y2="0" weight="0.5"/>
		<string-field fieldname="location" x="0" y="4" width="100%" height="100%" alignment="left" vertical-alignment="middle"/>
		<string-field fieldname="pageXofY" x="0" y="4" width="100%" height="100%" alignment="right" vertical-alignment="middle"/>
	    </pagefooter>

	    <functions>
		<property-ref name="report.date"/>
		    <function class="org.jfree.report.function.PageOfPagesFunction" name="pageXofY">
			<properties>
				<property name="format">Página {0} de {1}</property>
			</properties>
		    </function>
	    
	    </functions>
	</report>    
	
    }*/
}

