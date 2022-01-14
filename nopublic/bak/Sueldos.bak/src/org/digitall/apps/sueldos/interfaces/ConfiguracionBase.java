package org.digitall.apps.sueldos.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class ConfiguracionBase extends BasicTabContainer {

    private PanelEmpleados panelEmpleados; 
    private PanelSindicatos panelSindicatos; 
    private PanelCategorias panelCategorias; 
    private PanelArticulos panelArticulos;
    private PanelNovedades panelNovedades; 
    private PanelObrasSociales panelObrasSociales; 
    private PanelFamiliares panelFamiliares; 
    private PanelAportes panelAportes; 
    private PanelSociales panelSociales; 
    
    private BasicPrimitivePanel firstTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel secondTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel thirdTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel fourthTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel fifthTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel sixthTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel seventhTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel eightthTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel ninethTab = new BasicPrimitivePanel();
    
    private BasicContainerPanel firstPanel = new BasicContainerPanel();
    private BasicContainerPanel secondPanel = new BasicContainerPanel();
    private BasicContainerPanel thirdPanel = new BasicContainerPanel();
    private BasicContainerPanel fourthPanel = new BasicContainerPanel();
    private BasicContainerPanel fifthPanel = new BasicContainerPanel();
    private BasicContainerPanel sixthPanel = new BasicContainerPanel();
    private BasicContainerPanel seventhPanel = new BasicContainerPanel();
    private BasicContainerPanel eightthPanel = new BasicContainerPanel();
    private BasicContainerPanel ninethPanel = new BasicContainerPanel();
    
    private int checks = 0;
    
    
    public ConfiguracionBase() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panelEmpleados = new PanelEmpleados(this);
	panelSindicatos = new PanelSindicatos(this);
	panelCategorias = new PanelCategorias(this);
	panelArticulos = new PanelArticulos(this);
	panelNovedades = new PanelNovedades(this);
	panelObrasSociales = new PanelObrasSociales(this);
	panelAportes = new PanelAportes(this);
	panelFamiliares = new PanelFamiliares(this);
	panelSociales = new PanelSociales(this);
	this.setSize(new Dimension(693, 463));
	this.setPreferredSize(new Dimension(668, 470));
        
	firstPanel.setLayout(new BorderLayout());
	firstPanel.add(panelEmpleados, BorderLayout.CENTER);
	firstTab.setTitle("De Empleados");
	firstTab.setContent(firstPanel);
        
	secondPanel.setLayout(new BorderLayout());
	secondPanel.add(panelSindicatos, BorderLayout.CENTER);
	secondTab.setTitle("Sindicatos");
	secondTab.setContent(secondPanel);
        
        thirdPanel.setLayout(new BorderLayout());
        thirdPanel.add(panelCategorias, BorderLayout.CENTER);
        thirdTab.setTitle("Categorias");
        thirdTab.setContent(thirdPanel);
        
        fourthPanel.setLayout(new BorderLayout());
        fourthPanel.add(panelArticulos, BorderLayout.CENTER);
        fourthTab.setTitle("Articulos");
        fourthTab.setContent(fourthPanel);
        
        fifthPanel.setLayout(new BorderLayout());
        fifthPanel.add(panelNovedades, BorderLayout.CENTER);
        fifthTab.setTitle("Novedades");
        fifthTab.setContent(fifthPanel);
        
        sixthPanel.setLayout(new BorderLayout());
        sixthPanel.add(panelObrasSociales, BorderLayout.CENTER);
        sixthTab.setTitle("Obras Sociales");
        sixthTab.setContent(sixthPanel);
	
	seventhPanel.setLayout(new BorderLayout());
	seventhPanel.add(panelFamiliares, BorderLayout.CENTER);
	seventhTab.setTitle("De Familiares");
	seventhTab.setContent(seventhPanel);
	
	eightthPanel.setLayout(new BorderLayout());
	eightthPanel.add(panelAportes, BorderLayout.CENTER);
	eightthTab.setTitle("Aportes");
	eightthTab.setContent(eightthPanel);
	
	ninethPanel.setLayout(new BorderLayout());
	ninethPanel.add(panelSociales, BorderLayout.CENTER);
	ninethTab.setTitle("Sociales");
	ninethTab.setContent(ninethPanel);
	
	addTab(firstTab);
	addTab(secondTab);
	addTab(thirdTab);
	addTab(fourthTab);
	addTab(fifthTab);
        addTab(sixthTab);
	addTab(seventhTab);
	addTab(eightthTab);
	addTab(ninethTab);
	
	//iniTabs();
    }

    private void iniTabs() {
	panelEmpleados.started();
	panelSindicatos.started();
	panelCategorias.started();
	panelArticulos.started();
	panelNovedades.started();
	panelObrasSociales.started();
	panelFamiliares.started();
	panelAportes.started();
	panelSociales.started();
    }
    
    public void checkReady(){
	checks++;
	if (checks == this.getTabCount()) {
	    iniTabs();
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	panelCategorias.setParentInternalFrame(_e);
	panelObrasSociales.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Configuracion base para la Liquidación de Sueldos");
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    /*if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Complete los datos del pedido, luego los recursos solicitados y presione el botón \"Grabar\"");
	    } else {
		getParentInternalFrame().setInfo("Listado de Pedidos de Recursos realizados");
	    }*/
	}
    }

    public PanelEmpleados getPanelEmpleados() {
	return panelEmpleados;
    }

    public PanelSindicatos getPanelSindicatos() {
	return panelSindicatos;
    }

    public PanelCategorias getPanelCategorias() {
	return panelCategorias;
    }

    public PanelArticulos getPanelArticulos() {
	return panelArticulos;
    }

    public PanelNovedades getPanelNovedades() {
	return panelNovedades;
    }

    public PanelObrasSociales getPanelObrasSociales() {
	return panelObrasSociales;
    }

    public PanelFamiliares getPanelFamiliares() {
	return panelFamiliares;
    }

    public PanelAportes getPanelAportes() {
	return panelAportes;
    }

    public PanelSociales getPanelSociales() {
	return panelSociales;
    }
}
