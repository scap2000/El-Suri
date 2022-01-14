package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class frmLetras extends JDialog {
//public class frmLetras extends BasicPrimitivePanel {
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JComboBox jcorg = new JComboBox();
    private JTextField jtidtipo = new JTextField();
    private JTextField jtletra = new JTextField();
    private JTextField jtdescripcion = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private String org = "", Query = "", sidorganismo = "0";
    protected String idorganismo = "";
    private JPanel centralPanel = new JPanel();

    /**
   * FORMULARIO PARA CARGAR LAS LETRAS (INICIALES) REPRESENTATIVA DE CADA DEPENDENCIA, pej. GEH:GENERICO DE ECONOMIA Y HACIENDA - 
   * GOP:GENERICO DE SECRETARIA DE OBRAS Y SERVICIOS PUBLICOS
   * 
   * @param SQLQuery: INDICA MODIFICACION
   * @param Org: EL ORGANISMO AL CUAL PERTENECERA LA DEPENDENCIA REPRESENTADA POR LA LETRA INDICADA
   */
    public frmLetras(String Org, String SQLQuery) {
        try {
            org = Org;
            Query = SQLQuery;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(612, 180));
        //this.getContentPane().setLayout(null);
        this.setTitle("Agregar/Modificar Tipo Letra");
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(395, 120, 95, 25));
        baceptar.setMnemonic('a');
        baceptar.setMargin(new Insets(2, 5, 2, 14));
        baceptar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    baceptar_actionPerformed(e);
                }
            }
        );
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(505, 120, 95, 25));
        bcancelar.setMnemonic('c');
        bcancelar.setMargin(new Insets(2, 5, 2, 14));
        bcancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bcancelar_actionPerformed(e);
                }
            }
        );
        jcorg.setBounds(new Rectangle(5, 20, 595, 20));
        jtidtipo.setBounds(new Rectangle(5, 75, 55, 20));
        jtidtipo.setEditable(false);
        jtidtipo.setBackground(Color.white);
        jtletra.setBounds(new Rectangle(100, 75, 55, 20));
        jtdescripcion.setBounds(new Rectangle(195, 75, 405, 20));
        jLabel1.setText("ID Letra:");
        jLabel1.setBounds(new Rectangle(5, 60, 55, 15));
        jLabel2.setText("Letra:");
        jLabel2.setBounds(new Rectangle(100, 60, 35, 15));
        jLabel3.setText("Descripcion:");
        jLabel3.setBounds(new Rectangle(195, 60, 400, 15));
        jLabel4.setText("Organismo:");
        jLabel4.setBounds(new Rectangle(5, 5, 75, 15));
        centralPanel.setBounds(new Rectangle(0, 0, 610, 155));
        centralPanel.setLayout(null);
        centralPanel.add(jcorg, null);
        centralPanel.add(bcancelar, null);
        centralPanel.add(baceptar, null);
        centralPanel.add(jtdescripcion, null);
        centralPanel.add(jLabel3, null);
        centralPanel.add(jtletra, null);
        centralPanel.add(jLabel2, null);
        centralPanel.add(jtidtipo, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jLabel4, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtdescripcion.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jcorg.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        sidorganismo = OP_Proced.getCampo("SELECT idorganismo FROM files.tiposorganismo WHERE (idorganismo||'-'||descripcion)='" + jcorg.getSelectedItem() + "'");
                    }
                }
            }
        );
        OP_Proced.CargaCombo(jcorg, "SELECT idorganismo||'-'||descripcion FROM files.tiposorganismo WHERE estado <> '*'", org);
        if (Query.length() > 0) {
            ResultSet Reg = OP_Proced.exConsulta(Query);
            if (Reg.next()) {
                jtidtipo.setText(Reg.getString(1));
                idorganismo = Reg.getString(2);
                jtletra.setText(Reg.getString(3));
                jtdescripcion.setText(Reg.getString(4));
            }
        }
        jcorg.setEnabled(false);
    }

    private void baceptar_actionPerformed(ActionEvent e) {
        if (Query.length() > 0) {
            String Q = "UPDATE files.tiposletra SET letra=upper('" + jtletra.getText() + "'), descripcion=upper('" + jtdescripcion.getText() + "') WHERE idtipo = " + jtidtipo.getText() +" AND idorganismo = " + sidorganismo;
            if (OP_Proced.exActualizar('m', Q))
                this.dispose();
                //System.out.println(" ACA HAY QUE ARREGLAR !!!!!!!");
        } else {
            String Q = "INSERT into files.tiposletra VALUES(" + OP_Proced.Max("files.tiposletra", "idtipo", " WHERE idorganismo=" + sidorganismo) + "," + sidorganismo + ",upper('" + jtletra.getText() + "'), upper('" + jtdescripcion.getText() + "'),'')";
            if (OP_Proced.exActualizar('a', Q))
                this.dispose();
                // System.out.println(" ACA HAY QUE ARREGLAR !!!!!!!");
        }
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
