package org.digitall.projects.gdigitall.lib.components;

import java.awt.Dimension;
import java.awt.Insets;
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

public class CargaCampo extends JDialog implements ActionListener {
    private JTextField jtcampo = new JTextField();
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JLabel titulo1 = new JLabel();
    private JLabel titulo2 = new JLabel();
    private JTextField jtcodigo = new JTextField();
    //Variable
    private String LocalQuery, tabla, id, nombre, titulo, tituloventana, esquema;
    private String campo = "", codigo = "";
    //*******************************************
    //private frmExpedientes parentMain;

    public CargaCampo(String Query, String Tabla, String NomCampoID, String CampoDescripcion, String Titulo, String TituloVentana) {
        try {
            id = NomCampoID;
            nombre = CampoDescripcion;
            tabla = Tabla;
            LocalQuery = Query;
            titulo = Titulo;
            tituloventana = TituloVentana;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        baceptar.setMargin(new Insets(2, 5, 2, 14));
        //   Proced.CentrarVentana(this);
        bcancelar.setMargin(new Insets(2, 5, 2, 14));
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(411, 140));
        this.setTitle(tituloventana);
        jtcampo.setBounds(new Rectangle(105, 35, 285, 20));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(185, 75, 92, 25));
        baceptar.setMnemonic('A');
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(290, 75, 98, 25));
        bcancelar.setMnemonic('C');
        titulo1.setText(titulo);
        titulo1.setBounds(new Rectangle(105, 15, 285, 15));
        titulo2.setText("Codigo:");
        titulo2.setBounds(new Rectangle(15, 15, 48, 15));
        jtcodigo.setBounds(new Rectangle(15, 35, 80, 20));
        jtcodigo.setEditable(false);
        this.getContentPane().add(jtcodigo, null);
        this.getContentPane().add(titulo2, null);
        this.getContentPane().add(titulo1, null);
        this.getContentPane().add(bcancelar, null);
        this.getContentPane().add(baceptar, null);
        this.getContentPane().add(jtcampo, null);
        baceptar.addActionListener(this);
        bcancelar.addActionListener(this);
        if (LocalQuery.length() > 0) {
            //System.out.println(LocalQuery); 
            ResultSet Resul1 = OP_Proced.exConsulta(LocalQuery);
            if (Resul1.next()) {
                jtcodigo.setText(Resul1.getString(1).toString());
                jtcampo.setText(Resul1.getString(2).toString());
            }
        } else {
            //jtcodigo.setText(Proced.Max(tabla,id,""));
        }
    }

    public void actionPerformed(ActionEvent e) {
        /*try 
   {*/
        //Connection Con1=Proced.CCon();
        if (e.getSource() == baceptar) {
            if (jtcampo.getText().trim().length() > 0) {
                campo = jtcampo.getText();
                codigo = jtcodigo.getText();
                if (LocalQuery.length() == 0) {
                    codigo = OP_Proced.Max(tabla, id, "");
                    String Q = "Insert into " + tabla + " values(" + codigo + ",upper('" + jtcampo.getText() + "'),'')";
                    //System.out.println(Q);
                    if (OP_Proced.exActualizar('a', Q))
                        this.dispose();
                } else {
                    String Q = "Update " + tabla + " set " + nombre + "=upper('" + jtcampo.getText() + "') where " + id + "=" + jtcodigo.getText();
                    // System.out.println(Q);
                    if (OP_Proced.exActualizar('m', Q))
                        this.dispose();
                }
            }
        } else if (e.getSource() == bcancelar) {
            campo = "";
            this.dispose();
        }
        /*} catch (SQLException x)
   {
     System.out.println(x.getMessage());
   }*/
    }

    public String getCampo() {
        return campo;
    }

    public String getCodigo() {
        return codigo;
    }
}
