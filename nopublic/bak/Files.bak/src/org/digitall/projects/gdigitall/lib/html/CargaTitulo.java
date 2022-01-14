package org.digitall.projects.gdigitall.lib.html;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;


public class CargaTitulo extends JDialog implements ActionListener {
    protected JTextField jtcampo = new JTextField();

    private JButton bcancelar = new JButton();

    private JLabel titulo1 = new JLabel();

    private JTextField jtfecha = new JTextField();

    private JLabel jlfecha = new JLabel();

    private JButton baceptar = new JButton();

    //Variable

    private String titulo, tituloventana, textocampo;

    private static String campo, resaltar;

    private boolean activafecha, activachek;

    private JCheckBox jchekreparado = new JCheckBox();

    private JPanel jPanel1 = new JPanel();

    public CargaTitulo(String Titulo, String TituloVentana, String TextoCampo,
                       boolean ActivaFecha, boolean ActivarCheck) {
        try {
            titulo = Titulo;
            textocampo = TextoCampo;
            activafecha = ActivaFecha;
            tituloventana = TituloVentana;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        OP_Proced.IniciaTeclas();
        //Proced.CentrarVentana(this);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(455, 172));
        this.setTitle(tituloventana);
        jtcampo.setBounds(new Rectangle(15, 35, 330, 20));
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(345, 110, 87, 25));
        bcancelar.setMnemonic('C');
        titulo1.setText(titulo);
        titulo1.setBounds(new Rectangle(15, 15, 285, 15));
        jtfecha.setBounds(new Rectangle(360, 35, 70, 20));
        jlfecha.setText("Fecha:");
        jlfecha.setBounds(new Rectangle(360, 15, 38, 15));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(235, 110, 90, 25));
        baceptar.setMnemonic('A');
        jchekreparado.setText("Resaltar los Baches Reparados");
        jchekreparado.setBounds(new Rectangle(99, 3, 214, 15));
        jchekreparado
        .setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBounds(new Rectangle(15, 70, 415, 20));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jPanel1.add(jchekreparado, null);
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(baceptar, null);
        this.getContentPane().add(jlfecha, null);
        this.getContentPane().add(jtfecha, null);
        this.getContentPane().add(titulo1, null);
        this.getContentPane().add(bcancelar, null);
        this.getContentPane().add(jtcampo, null);
        bcancelar.addActionListener(this);
        baceptar.addActionListener(this);
        jtfecha.addMouseListener(new MouseAdapter() {
                                     public void mouseClicked(MouseEvent e) {
                                         if (e.getClickCount() == 1 &&
                                             e.getButton() == e.BUTTON1) {
                                             JTextField kk = new JTextField();
                                             SelectorFecha c =
                                                 new SelectorFecha(kk);
                                             OP_Proced.CentraVentana(c);
                                             c.setModal(true);
                                             c.show();
                                             jtfecha.setText(kk.getText());
                                             {

                                             }
                                         }
                                     }
                                 }
        );
        //    jtfecha.addKeyListener(new dateListen(true));
        jtfecha.setText(OP_Proced.FechaHora2(true, false));

        jlfecha.setVisible(activafecha);
        jtfecha.setVisible(activafecha);
        jtcampo.setText(textocampo);
        jchekreparado.setVisible(activachek);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == baceptar) {
            if (activafecha) {
                campo = jtcampo.getText() + " - " + jtfecha.getText();
                resaltar = OP_Proced.CharCheckBox(jchekreparado);
            } else {
                campo = jtcampo.getText();
            }
            this.dispose();
        }
    }

    public void setCampo(String _campo) {
        campo = _campo;
    }

    public static String getCampo() {
        return campo;
    }

    public static String getResaltar() {
        return resaltar;
    }
}
