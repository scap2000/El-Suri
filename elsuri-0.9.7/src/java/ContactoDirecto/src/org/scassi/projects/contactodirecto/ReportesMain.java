package org.scassi.projects.contactodirecto;

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
 * ServiciosMain.java
 *
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.URL;

import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes.classes.Commerce;
import org.digitall.apps.taxes.interfases.commercesadmin.CommercesMgmt;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicMenuItem;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.LayerGroup;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.image.ImageLabel;
import org.digitall.lib.sql.LibSQL;

import org.scassi.projects.contactodirecto.classes.Report;

import tim.prune.gui.map.MapCanvas;

public class ReportesMain extends BasicPrimitivePanel {

    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, new int[] { 63, 134, 80, 237, 189, 45, 36, 44 }, "Mobile Reports", dataRow) {

        @Override
        public void finishLoading() {
        }


        @Override
        public void refresh(Object _params) {
            btnAddNew.setEnabled(false);
            super.refresh(_params);
        }
    };

    private TFInput tfDesde = new TFInput(DataTypes.SIMPLEDATE, "Since", false);
    private TFInput tfHasta = new TFInput(DataTypes.SIMPLEDATE, "Until", false);
    private CBInput cbType = new CBInput(0, "Type", false);
    private CBInput cbStatus = new CBInput(0, "Status", false);
    private NextGridButton btnPlay = new NextGridButton();

    private BasicDialog parentContainer;
    private FindButton btnFind = new FindButton();
    private RefreshGridButton btnImport = new RefreshGridButton();

    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpEast = new BasicPanel();

    private MapCanvas mapCanvas;
    private LayerGroup layerGroup;
    private String audioPath;
    private String picturePath;
    private ImageLabel lblPhoto = new ImageLabel();
    private AddButton btnAddNew =  new AddButton();
    private Report report;
    private JPopupMenu popup;
    private BasicPanel content = new BasicPanel();
    private BasicCheckBox chkNotConverted = new BasicCheckBox("Not converted");

    public ReportesMain(MapCanvas _mapCanvas, LayerGroup _layerGroup) {
        mapCanvas = _mapCanvas;
        layerGroup = _layerGroup;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setPreferredSize(new Dimension(860, 333));
        this.setSize(new Dimension(860, 333));
        this.add(content, BorderLayout.CENTER);
        content.setLayout(new BorderLayout());
        jpCenter.setLayout(null);
        jpEast.setLayout(new BorderLayout());
        jpEast.setPreferredSize(new Dimension(200, 1));
        jpCenter.setBorder(BorderPanel.getBorderPanel("Search panel"));
        jpCenter.setSize(new Dimension(726, 313));
        btnPlay.setEnabled(false);
        btnPlay.setText("Play audio");
        btnPlay.setToolTipText("Play audio");
        btnAddNew.setText("Convert report");
        btnAddNew.setToolTipText("Convert report");

        listPanel.setBounds(new Rectangle(10, 55, 640, 220));
        tfDesde.setBounds(new Rectangle(10, 15, 80, 35));
        tfHasta.setBounds(new Rectangle(95, 15, 80, 35));
        btnFind.setBounds(new Rectangle(455, 25, 30, 25));
        btnFind.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnFind_actionPerformed(e);
            }

        });

        btnImport.setBounds(new Rectangle(475, 15, 120, 40));
        btnImport.setText("IMPORT");
        btnImport.setToolTipText("Import ALL reports from Mobile App");
        btnImport.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnImport_actionPerformed(e);
            }

        });

        cbType.setBounds(new Rectangle(180, 15, 135, 35));
        cbType.autoSize();
        cbType.setGeneralItem(true);
        cbType.loadJCombo("cdirecto.getallreporttypes", "");

        chkNotConverted.setBounds(new Rectangle(320, 22, 125, 35));
        chkNotConverted.setSelected(true);

        cbStatus.setBounds(new Rectangle(320, 15, 95, 35));
        jpCenter.add(tfDesde, null);
        jpCenter.add(tfHasta, null);
        jpCenter.add(cbType, null);
        jpCenter.add(chkNotConverted, null);
        jpCenter.add(btnFind, null);
        jpCenter.add(btnImport, null);
        jpCenter.add(listPanel, null);
        btnPlay.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnPlay_actionPerformed(e);
            }

        });
        jpEast.add(btnPlay, BorderLayout.NORTH);
        //tfDesde.setToday();
        tfDesde.setValue("");
        tfHasta.setToday();

        setHeaderList();

        lblPhoto.setBounds(new Rectangle(20, 5, 175, 175));
        lblPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoto.setSize(new Dimension(175, 175));
        jpEast.add(lblPhoto, BorderLayout.CENTER);

        addButton(btnAddNew);

        btnAddNew.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                popup.show(me.getComponent(), me.getX(), me.getY());
            }
        });
        
        BasicMenuItem menuCommerce =  new BasicMenuItem("Commerce");
        BasicMenuItem menuGreenPlace =  new BasicMenuItem("Park");
        BasicMenuItem menuBusStop =  new BasicMenuItem("Bus stop");
        BasicMenuItem menuElectricPole =  new BasicMenuItem("Electricity pole");
        BasicMenuItem menuElectricBox =  new BasicMenuItem("Power box");
        BasicMenuItem menuSemaphore =  new BasicMenuItem("Traffic light");
        BasicMenuItem menuBuilding =  new BasicMenuItem("Building");

        menuCommerce.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 addCommerce();
            }
        });

        ActionListener notEnabled = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Advisor.messageBox("Su institución no tiene permisos de ejecutar esta opción", "Error");
            }
        };

        menuGreenPlace.addActionListener(notEnabled);
        menuBusStop.addActionListener(notEnabled);
        menuElectricPole.addActionListener(notEnabled);
        menuElectricBox.addActionListener(notEnabled);
        menuSemaphore.addActionListener(notEnabled);
        menuBuilding.addActionListener(notEnabled);

        popup = new JPopupMenu();
        popup.add(menuCommerce);
        popup.add(menuGreenPlace);
        popup.add(menuBusStop);
        popup.add(menuElectricPole);
        popup.add(menuElectricBox);
        popup.add(menuSemaphore);
        popup.add(menuBuilding);

        content.add(jpCenter, BorderLayout.CENTER);
        content.add(jpEast, BorderLayout.EAST);
    }

    private void setHeaderList() {
        Vector<String> headerList = new Vector<String>();
        headerList.addElement("Report ID");
        headerList.addElement("Date");
        headerList.addElement("Type");
        headerList.addElement("Address");
        headerList.addElement("Description");
        headerList.addElement("* Color");
        headerList.addElement("* Value");
        headerList.addElement("Photo");
        headerList.addElement("Audio");
        headerList.addElement("HD Photo");

        listPanel.getTable().addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                    loadSelectedObject();
                } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                    //loadData();
                }
            }
        });

        listPanel.getTable().addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent key) {
                if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_ENTER || key.getKeyCode() == key.VK_PAGE_DOWN || key.getKeyCode() == key.VK_PAGE_UP) {
                    loadSelectedObject();
                }
            }
        });

        listPanel.setParams("cdirecto.getAllReports", "'''', false, '', ''", headerList);
        //listPanel.refresh("-1, -1, '', ''");
        loadData();
    }

    public void refresh() {
        clearData();
        listPanel.refresh( (cbType.getSelectedValue().equals("-1")?"''":"'"+ cbType.getSelectedItem() + "'") + "," + chkNotConverted.isSelected() + "," + tfDesde.getDateForSQL() + "," + tfHasta.getDateForSQL());
    }

    private void loadData() {
        //if (dataRow.size() > 0) {
        GeometrySet gsReports = new GeometrySet("cdirecto", "reports", "the_geom", "1 = 1", "idreport");
        gsReports.getGeometrySetConfig().getGeometryTypeFromSQL(null);
        gsReports.setParent(mapCanvas);
        gsReports.reload();
        //Layer layer_test02 = new Layer(dataRow.elementAt(2) + " - " + dataRow.elementAt(7), gset_test02);
        Layer lyReports = new Layer("Reports", gsReports);

        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(100);
        int b = rand.nextInt(100);
        Color randomColor = new Color(r, g, b);

        lyReports.setColor(Color.black);
        //lyReports.setFillColor(randomColor);
        lyReports.setFillColor(Color.red);
        lyReports.setMouseOverColor(Color.ORANGE);

        lyReports.setOn();

        layerGroup.add(lyReports);

        mapCanvas.addGeometrySet(gsReports);

    }

    private void clearData() {
    }

    public void setParentContainer(BasicDialog parentContainer) {
        this.parentContainer = parentContainer;
    }

    private void btnFind_actionPerformed(ActionEvent e) {
        refresh();
    }

    private void btnImport_actionPerformed(ActionEvent e) {
        if (LibSQL.getBoolean("cdirecto.copy_remote_reports", "0")) {
            Advisor.messageBox("Reportes importados con éxito", "Importación de reportes");
            refresh();
        } else {
            Advisor.messageBox("Error al intentar importar los reportes", "Importación de reportes");
        }
    }

    private void btnUpdate_actionPerformed(ActionEvent e) {
    }

    private void addCommerce() {
        Commerce  commerce = new Commerce();
        
        if (report  != null) {
            commerce.setIdReport(report.getIdReport());
            commerce.setDomicilio(report.getAddress());
            if (report.getMapLocation() != null) {
                commerce.setMapLocation(report.getMapLocation());
            } else {
                commerce.setMapLocation(report.getGpsLocation());
            }
            commerce.setFechainicio(report.getEvent_datetime());
            commerce.setDescription(report.getDescription());
            commerce.setPictureFile(report.getPicturePath());
        }
        CommercesMgmt commercesMgmt = new CommercesMgmt();
        commercesMgmt.setCommerce(commerce);
        commercesMgmt.setParentList(this);
        ExtendedInternalFrame commerceMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
        commerceMgmtContainer.setIconifiable(false);
        commerceMgmtContainer.setCentralPanel(commercesMgmt);
        commerceMgmtContainer.show();
    }

    private void btnPlay_actionPerformed(ActionEvent e) {
        //Environment.cfg.setProperty("showMP3license", "true");
        if (!Environment.cfg.getProperty("showMP3license").equals("false")) {
            final BasicDialog _licenseDialog = new BasicDialog(null, "Exención de responsabilidad") {
                public void closeBasicDialog() {
                    BrowserLauncher.openURL(Environment.mediaServer + audioPath);
                    dispose();
                }
            };
            JArea _jaLicense = new JArea();
            _jaLicense.setContentType("text/html");
            _jaLicense.setPage(Environment.mainClass.getResource("MP3license.txt"));
            _jaLicense.setEditable(false);
            _licenseDialog.setLayout(new BorderLayout());
            _licenseDialog.add(_jaLicense, BorderLayout.CENTER);
            _licenseDialog.setModal(true);
            BasicPanel _bp = new BasicPanel(new BorderLayout());
            BasicButton _ok = new BasicButton("Entiendo");
            _ok.setBackground(Color.orange);
            _ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent actionEvent) {
                        _licenseDialog.closeBasicDialog();                      
                    }
                });
            _licenseDialog.add(_ok, BorderLayout.SOUTH);
            BasicButton _okNoMostrar = new BasicButton("Ok, no mostrar de nuevo");
            _okNoMostrar.setBackground(Color.green);
            _okNoMostrar.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent actionEvent) {
                        Environment.cfg.setProperty("showMP3license", "false");
                        _licenseDialog.closeBasicDialog();                      
                    }
                });
            _bp.add(_ok, BorderLayout.WEST);
            _bp.add(_okNoMostrar, BorderLayout.EAST);
            _licenseDialog.add(_bp, BorderLayout.SOUTH);
            _licenseDialog.setSize(new Dimension(500,300));
            ComponentsManager.centerWindow(_licenseDialog);
            _licenseDialog.setVisible(true);
        } else {
            BrowserLauncher.openURL(Environment.mediaServer + audioPath);
        }
    }

    public BasicTable getLinkedTable() {
        return listPanel.getTable();
    }

    private void loadSelectedObject() {
        Thread _thread = new Thread(new Runnable() {
            public void run()  {
                loadSelectedObjectThread();
            }
        });
        _thread.start();
    }
    
    private void loadSelectedObjectThread() {
        report = null;
        listPanel.getTable().setEnabled(false);
        btnPlay.setEnabled(false);
        lblPhoto.setIcon(IconTypes.busy);
        if (!dataRow.isEmpty()) {
            report = new Report(Integer.parseInt("" + dataRow.elementAt(0)));
            if (report.fetchData()) {
                if (report.getMapLocation() != null) {
                    LatLongCoord _ll = report.getMapLocation();
                    mapCanvas.setMapExtents(_ll.getLongitude(), _ll.getLatitude(), _ll.getLongitude(), _ll.getLatitude());
                    mapCanvas.zoomToFit();
                } else if (report.getGpsLocation() != null) {
                    LatLongCoord _ll = report.getGpsLocation();
                    mapCanvas.setMapExtents(_ll.getLongitude(), _ll.getLatitude(), _ll.getLongitude(), _ll.getLatitude());
                    mapCanvas.zoomToFit();
                }
                audioPath = report.getAudioPath();
                if (report.getPicturePath() != null) {
                    picturePath = report.getPicturePath();
                    BufferedImage img;
                    try {
                        img = ImageIO.read(new URL(Environment.mediaServer + picturePath));
                        lblPhoto.setImage(img);
                    } catch (IOException e) {   
                        e.printStackTrace();
                        lblPhoto.setImage(null);
                    }
                } else {
                    lblPhoto.setImage(null);
                }
            }
        }
        listPanel.getTable().setEnabled(true);
        btnPlay.setEnabled(audioPath != null);
        btnAddNew.setEnabled(report != null);
    }

}
