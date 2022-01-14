package org.digitall.apps.cashflow.interfaces.accounting;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class GraphicsMain  extends BasicTabContainer {

    private CashflowGraphics providersList = new CashflowGraphics();
    private ResourcesGraphics resourcesAmountGraphics = new ResourcesGraphics();
    private ResourcesQtyGraphics resourcesQtyGraphics = new ResourcesQtyGraphics();
    
    public GraphicsMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(538, 245));
	this.setPreferredSize(new Dimension(620, 390));
	addTab("Cuentas Contables", providersList);
	addTab("Recursos ($)", resourcesAmountGraphics);
        addTab("Recursos (Cant.)", resourcesQtyGraphics);
    }
    
    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0)  {
		getParentInternalFrame().setInfo("Seleccione una o varias cuentas y presione el botón \"Imprimir\" ");
	    } else  {
	        getParentInternalFrame().setInfo("Seleccione uno o varios recursos y presione el botón \"Imprimir\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione una o varias cuentas y presione el botón \"Imprimir\"");
    }

}

