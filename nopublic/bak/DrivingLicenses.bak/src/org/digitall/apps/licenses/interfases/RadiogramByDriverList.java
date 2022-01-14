package org.digitall.apps.licenses.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;
import org.digitall.apps.licenses.classes.Driver;
import org.digitall.apps.licenses.classes.Radiogram;


public class RadiogramByDriverList extends BasicPrimitivePanel {

    private BasicPanel mainPanel = new BasicPanel();

    private BasicPanel dataPanel = new BasicPanel();

    private int[] sizeColumnList = { 55, 180, 85, 100, 100 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Radiogramas recibidos", dataRow);
    private Vector headerList = new Vector();

    private BasicTextArea taReason = new BasicTextArea();
    
    private CloseButton btnClose = new CloseButton();

    private Driver driverSelected;
    private Radiogram radiogram;

    public RadiogramByDriverList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(582, 412));
        mainPanel.setLayout(null);
        listPanel.setSize(new Dimension(505, 100));
        listPanel.setPreferredSize(new Dimension(400, 100));
        listPanel.setBounds(new Rectangle(5, 0, 565, 210));
        dataPanel.setBounds(new Rectangle(0, 0, 580, 370));
        
        dataPanel.setLayout(null);
        dataPanel.add(taReason, null);
        dataPanel.add(listPanel, BorderLayout.NORTH);
        dataPanel.add(taReason, BorderLayout.CENTER);
        mainPanel.add(dataPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
        btnClose.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnClose_actionPerformed(e);
                                 }

                             }
        );

        taReason.setBounds(new Rectangle(5, 220, 565, 140));
        
        addButton(btnClose);
        taReason.setEnabled(false);
        setHeaderList();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Visualización de Radiogramas Recibidos");
    }
    
    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.addElement("*");         //idradiogram
        headerList.addElement(Environment.lang.getProperty("Number"));
        headerList.addElement("*");         //lastname
        headerList.addElement("*");         //name
        headerList.addElement(Environment.lang.getProperty("Name"));
        headerList.addElement("*");         //identificationtype
        headerList.addElement(Environment.lang.getProperty("DNI"));
        headerList.addElement(Environment.lang.getProperty("StartDate"));
        headerList.addElement(Environment.lang.getProperty("ExpirationDate"));
        headerList.addElement("*");         //reason
        headerList.addElement("*");         //estado
        
        listPanel.getTable().addMouseListener(new MouseAdapter() {

                                           public void mouseClicked(MouseEvent e) {
                                               if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                   loadObjectSelected();
                                                   taReason.setText(radiogram.getReason());
                                               } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
                                                   int index = listPanel.getTable().rowAtPoint(e.getPoint());
                                                   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
                                               }
                                           }

                                       }
        );
        String params = "'','',-1";
        listPanel.setParams("licenses.getAllRadiograms", params, headerList);
    }

    private void btnFindDriver_actionPerformed(ActionEvent e) {
        refreshRadiograms();
    }


    private void loadObjectSelected() {
        if (!dataRow.isEmpty()) {
            radiogram = new Radiogram();
            radiogram.setIdradiogram(Integer.parseInt("" + dataRow.elementAt(0)));
            radiogram.setRadiogramnumber(Integer.parseInt("" + dataRow.elementAt(1)));
            radiogram.setLastname(dataRow.elementAt(2).toString());
            radiogram.setName(dataRow.elementAt(3).toString());
            radiogram.setIdidentificationtype(Integer.parseInt(""+ dataRow.elementAt(5).toString()));
            radiogram.setIdentificationnumber(Integer.parseInt(""+ dataRow.elementAt(6).toString()));
            radiogram.setDatefrom(Proced.setFormatDate(dataRow.elementAt(7).toString(),false));
            radiogram.setDateto(Proced.setFormatDate(dataRow.elementAt(8).toString(),false));
            radiogram.setReason(dataRow.elementAt(9).toString());
        }
    }

    public void refreshRadiograms() {
        String params = "'','',"+ driverSelected.getIdentificationNumber();
        listPanel.refresh(params);
        taReason.setText("");
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close(true);
    }

    public void setDriverSelected(Driver _driverSelected) {
        driverSelected = _driverSelected;
        refreshRadiograms();
    }
}
