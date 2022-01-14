package org.digitall.projects.gdigitall.lib.components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class Carga2Campo extends JDialog implements ActionListener {
    private JTextField jtcampo1 = new JTextField();
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JLabel titulo1 = new JLabel();
    private JLabel titulo = new JLabel();
    private JTextField jtcodigo = new JTextField();
    //Variable
    private String LocalQuery, tabla, id, tituloc1, tituloc2, tituloventana, ncampo1, ncampo2, esquema;
    protected String campo1, campo2, idtipo;
    private JTextField jtcampo2 = new JTextField();
    private JLabel titulo2 = new JLabel();

    public Carga2Campo(String Query, String Tabla, String NomCampoID, String Campo1, String Campo2, String TituloCampo1, String TituloCampo2, String TituloVentana) {
        try {
            id = NomCampoID;
            ncampo1 = Campo1;
            ncampo2 = Campo2;
            tabla = Tabla;
            LocalQuery = Query;
            tituloc1 = TituloCampo1;
            tituloc2 = TituloCampo2;
            tituloventana = TituloVentana;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //   Proced.CentrarVentana(this);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(540, 138));
        this.setTitle(tituloventana);
        jtcampo1.setBounds(new Rectangle(105, 35, 285, 20));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(300, 75, 101, 25));
        baceptar.setMnemonic('A');
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(413, 75, 107, 25));
        bcancelar.setMnemonic('C');
        titulo1.setText(tituloc1);
        titulo1.setBounds(new Rectangle(105, 15, 285, 15));
        titulo.setText("Codigo:");
        titulo.setBounds(new Rectangle(15, 15, 48, 15));
        jtcodigo.setBounds(new Rectangle(15, 35, 80, 20));
        jtcodigo.setEditable(false);
        jtcampo2.setBounds(new Rectangle(405, 35, 115, 20));
        titulo2.setText(tituloc2);
        titulo2.setBounds(new Rectangle(405, 15, 115, 15));
        this.getContentPane().add(titulo2, null);
        this.getContentPane().add(jtcampo2, null);
        this.getContentPane().add(jtcodigo, null);
        this.getContentPane().add(titulo, null);
        this.getContentPane().add(titulo1, null);
        this.getContentPane().add(bcancelar, null);
        this.getContentPane().add(baceptar, null);
        this.getContentPane().add(jtcampo1, null);
        baceptar.addActionListener(this);
        bcancelar.addActionListener(this);
        if (LocalQuery.length() > 0) {
            // System.out.println(LocalQuery); 
            ResultSet Resul1 = OP_Proced.exConsulta(LocalQuery);
            if (Resul1.next()) {
                jtcodigo.setText(Resul1.getString(1).toString());
                jtcampo1.setText(Resul1.getString(2).toString());
                jtcampo2.setText(Resul1.getString(3).toString());
            }
        } else {
            // jtcodigo.setText(Proced.Max(tabla,id,""));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == baceptar) {
            if (jtcampo1.getText().trim().length() > 0) {
                campo1 = jtcampo1.getText();
                campo2 = jtcampo2.getText();
                idtipo = jtcodigo.getText();
                if (LocalQuery.length() == 0) {
                    idtipo = OP_Proced.Max(tabla, id, "");
                    String Q = "INSERT INTO " + tabla + " VALUES(" + idtipo + ",UPPER('" + jtcampo2.getText() + "'),UPPER('" + jtcampo1.getText() + "'),'')";
                    //    System.out.println(Q);
                    if (OP_Proced.exActualizar('a', Q))
                        this.dispose();
                } else {
                    String Q = "UPDATE " + tabla + " SET " + ncampo1 + "= UPPER('" + jtcampo1.getText() + "')," + ncampo2 + "= UPPER('" + jtcampo2.getText() + "')  WHERE " + id + "=" + jtcodigo.getText();
                    //       System.out.println(Q);
                    if (OP_Proced.exActualizar('m', Q))
                        this.dispose();
                }
            }
        } else if (e.getSource() == bcancelar) {
            campo1 = "";
            campo2 = "";
            this.dispose();
        }
    }

    public String getCampo1() {
        return campo1;
    }

    public String getCampo2() {
        return campo2;
    }
}
