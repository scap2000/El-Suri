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
 * RecursosPanel.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.apps.resourcescontrol_091.classes.Resources;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class RecursosPanel extends BasicContainerPanel{
    private BasicPanel bpCentro = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private TFInput tfiNombre = new TFInput(DataTypes.STRING,"Name",false);
    private CBInput cbiUnidad = new CBInput(CachedCombo.UNITS_TABS, "Unit",false);
    private CBInput cbiHabilidadProveedor = new CBInput(CachedCombo.SKILLPROVIDER_TABS, "Skill",false);
    private CBInput tipoGasto = new CBInput(0,"",false);
    private BasicCheckBox bcbIsCompuesto = new BasicCheckBox();
    private TFInput tfiDescripcion = new TFInput(DataTypes.STRING,"Observations",false);
    private TFInput tfiPeriodoVidaUtil = new TFInput(DataTypes.INTEGER,"",false);
    private CBInput cbiPeriodoVidaUtil = new CBInput(CachedCombo.LIFETIMETYPES, "",false);
    private TFInput tfiCodigoBarra = new TFInput(DataTypes.STRING,"Code",false);
    private TFInput tfiStock = new TFInput(DataTypes.INTEGER,"Stock",false);
    private Resources resources = null;
    private CBInput cbiEsPara = new CBInput(0,"",false);

    public RecursosPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }    
    
    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(new Dimension(580, 600));
	this.setPreferredSize(new Dimension(580, 600));
	bpCentro.setBorder(BorderPanel.getBorderPanel("Agregar recurso"));
	bpCentro.setLayout(null);
	tfiNombre.setBounds(new Rectangle(155, 25, 355, 35));
	cbiUnidad.setBounds(new Rectangle(20, 115, 195, 35));
	cbiHabilidadProveedor.setBounds(new Rectangle(20, 70, 305, 35));
	tipoGasto.setBounds(new Rectangle(20, 205, 210, 35));
	bcbIsCompuesto.setText("Compuesto (ó Simple)");
	bcbIsCompuesto.setBounds(new Rectangle(240, 120, 160, 30));
	tfiDescripcion.setBounds(new Rectangle(20, 295, 540, 35));
	tfiPeriodoVidaUtil.setBounds(new Rectangle(235, 205, 80, 35));
	cbiPeriodoVidaUtil.setBounds(new Rectangle(20, 160, 210, 35));
	tfiCodigoBarra.setBounds(new Rectangle(20, 25, 130, 35));
	tfiStock.setBounds(new Rectangle(345, 205, 70, 35));
	cbiEsPara.setBounds(new Rectangle(20, 250, 140, 35));
	bpCentro.add(cbiEsPara, null);
	bpCentro.add(tfiStock, null);
	bpCentro.add(tfiCodigoBarra, null);
	bpCentro.add(cbiPeriodoVidaUtil, null);
	bpCentro.add(tfiPeriodoVidaUtil, null);
	bpCentro.add(tfiDescripcion, null);
	bpCentro.add(bcbIsCompuesto, null);
	bpCentro.add(tipoGasto, null);
	bpCentro.add(cbiHabilidadProveedor, null);
	bpCentro.add(cbiUnidad, null);
	bpCentro.add(tfiNombre, BorderLayout.CENTER);
	this.add(bpCentro, BorderLayout.CENTER);
	
	cbiUnidad.autoSize();
	cbiHabilidadProveedor.autoSize();
	tipoGasto.autoSize();
	cbiPeriodoVidaUtil.autoSize();
	cbiEsPara.autoSize();
	
	loadCombo();
    }
    
    public void loadCombo() {
	tipoGasto.loadJCombo(LibSQL.exFunction("personalfiles.getAllWasteUnit", ""));	
	cbiEsPara.getCombo().addItem("Personal","1");
	cbiEsPara.getCombo().addItem("Dependencia","2");
	cbiEsPara.getCombo().addItem("Ambos","3");
    }

    public void setResource(Resources _resources) {
	resources = _resources;
	loadData();
    }
        
    public void loadData() {
	tfiNombre.setValue(resources.getName());
	cbiUnidad.setSelectedID(resources.getIdUnit());
	cbiHabilidadProveedor.setSelectedID(resources.getIdSkillToProvider());
	tipoGasto.setSelectedID(resources.getIdAccount());
	bcbIsCompuesto.setSelected(resources.isComposite());
	tfiDescripcion.setValue(resources.getDescription());
	tfiPeriodoVidaUtil.setValue(resources.getLifeTime());
	cbiPeriodoVidaUtil.setSelectedID(resources.getIdLifeTimeType());
	tfiCodigoBarra.setValue(resources.getBarCode());
	tfiStock.setValue(resources.getTotalStock());
    }
    
    public boolean saveData() {
	if (tfiNombre.getString().length() > 0) {
	    if (resources == null) {
		resources = new Resources();
	    }
	    
	    resources.setName(tfiNombre.getValue().toString());
	    resources.setIdUnit(Integer.parseInt(cbiUnidad.getSelectedValue().toString()));
	    resources.setIdSkillToProvider(Integer.parseInt(cbiHabilidadProveedor.getSelectedValue().toString()));
	    resources.setIdAccount(Integer.parseInt(tipoGasto.getSelectedValue().toString()));
	    resources.setComposite(bcbIsCompuesto.isSelected());
	    resources.setDescription(tfiDescripcion.getValue().toString());
	    resources.setLifeTime(Integer.parseInt(tfiPeriodoVidaUtil.getValue().toString()));
	    resources.setIdLifeTimeType(Integer.parseInt(cbiPeriodoVidaUtil.getSelectedValue().toString()));
	    resources.setBarCode(Integer.parseInt(tfiCodigoBarra.getValue().toString()));
	    resources.setTotalStock(Double.parseDouble(tfiStock.getValue().toString()));
	    
	  
	    int result = resources.saveData();
	    if (result > 0) {
		
	        return true;
	    } else {
	        return false;
	    }
	    	 
	} else {
	    Advisor.messageBox("Debe al menos completar los campos Apellido y Nombre", "Aviso");
	    return false;
	}
    }
}
