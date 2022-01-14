package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Plazos extends JDialog implements ActionListener {
public class Plazos extends BasicPrimitivePanel implements ActionListener {
    //private JPanel jPanel1 = new JPanel();
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 3 };
    private int[] tcol = { 30, 680, 40 };
    private CRegistros jPanel1 = new CRegistros(30, "files.", "files.plazos", vcol, tcol);
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipoinst = "";
    private JButton bseleccionar = new JButton();//new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JButton bcerrar = new JButton();//new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    static String dia = "", idtipo = "";
    private JPanel centralPanel = new JPanel();
    //*************************************
    private frmExpedientes parentMain;
    private principal_simex parent;

    /**
   * FORMULARIO PARA VISUALIZAR LOS DIFERENTES TIPOS DE PLAZO
   */
    public Plazos() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(789, 294));
        //this.getContentPane().setLayout(null);
        this.setTitle("Listado de Tipos de Plazos");
        jPanel1.setBounds(new Rectangle(5, 5, 775, 215));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        bseleccionar.setText("Seleccionar");
        bseleccionar.setBounds(new Rectangle(5, 235, 130, 25));
        bseleccionar.setMnemonic('s');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(685, 235, 95, 25));
        bcerrar.setMnemonic('c');
        centralPanel.add(bcerrar, null);
        centralPanel.add(bseleccionar, null);
        centralPanel.add(jPanel1, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jPanel1.Redimensiona();
        datos1 = jPanel1.VDatos();
        bcerrar.addActionListener(this);
        centralPanel.setBounds(new Rectangle(0, 0, 785, 270));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        bseleccionar.addActionListener(this);
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel1.VDatos();
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            // System.out.println(datos1);
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        ActualizaTabla();
    }

    /*public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("");
    }*/

    public void ActualizaTabla() {
        Consulta = "SELECT * FROM files.tiposplazo ORDER BY descripcion";
        ConsultaCount = "SELECT count(*) FROM files.tiposplazo";
        jPanel1.ActualizaTabla(this, Consulta, ConsultaCount);
        datos1.clear();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcerrar) {
            //this.dispose();
            getParentInternalFrame().close();
        } else if (e.getSource() == bseleccionar) {
            dia = datos1.elementAt(2).toString();
            idtipo = datos1.elementAt(0).toString();
            //this.dispose();
            parentMain.setTipoPlazo(idtipo);
            parentMain.setDia(dia);
            getParentInternalFrame().close();
        }
    }

    public void setParentMain(frmExpedientes _parentMain) {
        parentMain = _parentMain;
    }
    
    public void setParent(principal_simex _parent) {
        parent = _parent;
    }

    public String getDia() {
        return dia;
    }

    public String getIdtipo() {
        return idtipo;
    }
}
