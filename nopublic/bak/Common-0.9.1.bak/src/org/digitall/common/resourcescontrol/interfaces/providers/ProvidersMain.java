package org.digitall.common.resourcescontrol.interfaces.providers;

import java.awt.Dimension;

import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersList;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMgmt;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class ProvidersMain  extends BasicTabContainer {

    private ProvidersList providersList = new ProvidersList(this);
    private ProvidersMgmt providersMgmt = new ProvidersMgmt(this);
    
    public ProvidersMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(620, 390));
	this.setPreferredSize(new Dimension(620, 390));
	addTab("Proveedores", providersList);
	addTab("Nuevo Proveedor", providersMgmt);
    }
    
    public void refresh(){
	providersMgmt.setTitle("Nuevo Proveedor");
	providersList.refresh();
	setSelectedTab(0);
    }

    public ProvidersList getProvidersList() {
	return providersList;
    }

    public ProvidersMgmt getProvidersMgmt() {
	return providersMgmt;
    }
    
    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0)  {
		getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar un proveedor");
	    } else  {
	        getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar un proveedor");
    }

}
