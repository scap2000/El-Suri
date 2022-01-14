package org.digitall.apps.resourcescontrol.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class ResourcesAdminList extends BasicPrimitivePanel{

    private int[] sizeColumnList = {207, 54, 69, 80, 119, 82, 75, 174};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Stock de Recursos Existentes", dataRow);
    private CBInput cbSkillToProvider = new CBInput(CachedCombo.SKILLPROVIDER_TABS, "ResourceTypes");
    private TFInput tfFind = new TFInput(DataTypes.STRING, "FindResource",false);
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar Recurso");
    private FindButton btnFind = new FindButton();
    private ResourcesAdminMain parentMain;
    private Resource resource;

    public ResourcesAdminList(ResourcesAdminMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(790, 390));
	this.setPreferredSize(new Dimension(790, 390));
	setTitle("Recursos Existentes");
	listPanel.setBounds(new Rectangle(5, 80, 780, 305));
	content.setBounds(new Rectangle(5, 5, 660, 425));
	content.setLayout(null);
	findPanel.setBounds(new Rectangle(5, 5, 780, 70));
	findPanel.setLayout(null);
	tfFind.setBounds(new Rectangle(120, 25, 190, 35));
	cbSkillToProvider.setBounds(new Rectangle(325, 25, 235, 35));
	btnFind.setBounds(new Rectangle(570, 35, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	content.add(listPanel, null);
	content.add(findPanel, null);
	findPanel.add(btnFind, null);
	findPanel.add(tfFind, null);
	findPanel.add(cbSkillToProvider, null);
	this.add(content, null);
	
	cbSkillToProvider.autoSize();
	
	cbSkillToProvider.getCombo().addItem("Todos","-1");
	cbSkillToProvider.setSelectedID("-1");
	
	tfFind.getTextField().addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			    refresh();
		    }
		});
		
	setHeaderList();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("ReceivedQty"));
	headerList.addElement(Environment.lang.getProperty("DeliveredQty"));
	headerList.addElement(Environment.lang.getProperty("TotalInReserveQty"));
	headerList.addElement(Environment.lang.getProperty("AvailableQty"));
	headerList.addElement(Environment.lang.getProperty("TotalStock"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Type"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		loadObject();
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    
		}
	    }
	});
	
	listPanel.setParams("resourcescontrol.getAllResourcesExist", "'',-1", headerList);
    }

    public void refresh() {
	String params = "'"+ tfFind.getString() +"',"+ cbSkillToProvider.getSelectedValue();
	listPanel.refresh(params);
    }

    private void loadObject(){
	if (!dataRow.isEmpty()){
	    resource = new Resource();
	    resource.setIdResource(Integer.parseInt(""+ dataRow.elementAt(0)));
	    resource.setName(""+ dataRow.elementAt(1));
	    resource.setSkillToProvider(new Skills(Integer.parseInt(""+ dataRow.elementAt(9))));
	    resource.setIdUnit(Integer.parseInt(""+ dataRow.elementAt(2)));
	    
	    parentMain.setResource(resource);
	    setHeaderTitle();
	}
    }
    
    private void setHeaderTitle(){
	String title = Environment.lang.getProperty("Resource") +": "+ resource.getName();
	parentMain.setTitle(title);
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    public void setParentMain(ResourcesAdminMain parentMain) {
	this.parentMain = parentMain;
    }

}
