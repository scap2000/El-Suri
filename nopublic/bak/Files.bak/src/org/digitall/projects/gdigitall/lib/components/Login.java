package org.digitall.projects.gdigitall.lib.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.projects.gdigitall.lib.misc.OP_LibSQL;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;


public class Login extends JDialog implements ActionListener, KeyListener
{
  private JLabel lusuario = new JLabel(new ImageIcon(OP_Proced.class.getResource("iconos/22x22/sesion01.gif")));
  private JLabel lclave = new JLabel(new ImageIcon(OP_Proced.class.getResource("iconos/22x22/sesion02.gif")));
  protected JEntry jtUsuario = new JEntry();
  private JPasswordField jtClave = new JPasswordField();
  private JButton BAceptar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
  private JButton BCancelar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/salir.gif")));
  private String usuario,titulo,dep;
  static boolean validado=false;
  private JLabel jltitulo = new JLabel();
  private JLabel jldep = new JLabel();
  private JLabel escudo = new JLabel(new ImageIcon(OP_Proced.class.getResource("iconos/64x64/escudo002.gif")));
  private JPanel jPanel1 = new JPanel();
  private boolean inicio=false;
  private JSeparator jSeparator1 = new JSeparator();
  private JLabel jLabel1 = new JLabel();
  private int intentos = 0;
  private String SQLUserIntruder = "";
  private String SQLPassIntruder = "";

  public Login(String Titulo,String Dependencia, boolean Inicio)
  {
    try
    {
      titulo = Titulo;
      dep = Dependencia;
      inicio = Inicio;
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    this.setSize(new Dimension(489, 311));
    this.getContentPane().setLayout(null);
    this.setTitle("Sistema General - Inicio de Sesión");
    lusuario.setText("Usuario:");
    lusuario.setBounds(new Rectangle(15, 20, 110, 25));
    lusuario.setFont(new Font("Dialog", 1, 14));
    lusuario.setHorizontalAlignment(SwingConstants.RIGHT);
    lclave.setText("Clave:");
    lclave.setBounds(new Rectangle(15, 65, 110, 25));
    lclave.setFont(new Font("Dialog", 1, 14));
    lclave.setHorizontalAlignment(SwingConstants.RIGHT);
    jtUsuario.setBounds(new Rectangle(130, 20, 130, 25));
    jtUsuario.setFont(new Font("Dialog", 0, 15));
    jtClave.setBounds(new Rectangle(130, 65, 130, 25));
    jtClave.setFont(new Font("Dialog", 0, 15));
    BAceptar.setText("Ingresar");
    BAceptar.setBounds(new Rectangle(240, 230, 105, 25));
    BAceptar.setMnemonic('A');
    BCancelar.setText("Salir");
    BCancelar.setBounds(new Rectangle(360, 230, 105, 25));
    BCancelar.setMnemonic('S');
 //   jltitulo.setText("Sistema General");
    jltitulo.setBounds(new Rectangle(4, 5, 470, 25));
    jltitulo.setFont(new Font("Dialog", 1, 16));
    jltitulo.setHorizontalAlignment(SwingConstants.CENTER);
 //   jldep.setText("Secretaría de Obras y Servicios Públicos");
    jldep.setBounds(new Rectangle(4, 25, 470, 25));
    jldep.setFont(new Font("Dialog", 0, 14));
    jldep.setHorizontalAlignment(SwingConstants.CENTER);
    escudo.setBounds(new Rectangle(30, 80, 100, 110));
    escudo.setFont(new Font("Dialog", 1, 16));
    jPanel1.setBounds(new Rectangle(175, 80, 290, 115));
    jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jPanel1.setLayout(null);
    jSeparator1.setBounds(new Rectangle(5, 260, 470, 10));
    jLabel1.setText("Para cualquier consulta dirigirse al Dpto. SIG (Sistema de Informacion " + "Geografica)");
    jLabel1.setBounds(new Rectangle(20, 265, 434, 15));
    jLabel1.setFont(new Font("Dialog", 0, 11));
    jLabel1.setForeground(Color.blue);
    jPanel1.add(jtClave, null);
    jPanel1.add(jtUsuario, null);
    jPanel1.add(lclave, null);
    jPanel1.add(lusuario, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jSeparator1, null);
    this.getContentPane().add(jPanel1, null);
    this.getContentPane().add(jldep, null);
    this.getContentPane().add(jltitulo, null);
    this.getContentPane().add(BCancelar, null);
    this.getContentPane().add(BAceptar, null);
    this.getContentPane().add(escudo, null);
    jtUsuario.addKeyListener(this);
    jtClave.addKeyListener(this);
    BAceptar.addActionListener(this);
    BCancelar.addActionListener(this);
    BAceptar.addKeyListener(this);
    BCancelar.addKeyListener(this);
    
    jltitulo.setText(titulo);
    jldep.setText(dep);
  }

  public void actionPerformed(ActionEvent e)
  {
      if (e.getSource() == BAceptar) 
      {
        Acceso();
      } else if (e.getSource() == BCancelar) 
      {
        if (inicio)
        {
          System.exit(0);
        } else 
        {
          this.dispose();
        }
      }      
  }

  private void Acceso()
  {
    String SQLUser="",SQLPass="";
    SQLUser = jtUsuario.getText();
    
    SQLUserIntruder += SQLUser + " "; 
    SQLPassIntruder += new String(jtClave.getPassword()) + " ";
    if (jtUsuario.getText().equals("admin"))
    {
      SQLPass = new String(jtClave.getPassword());
    } else 
    {
      SQLPass = OP_LibSQL.getMD5(new String(jtClave.getPassword()));
    }
      /*else 
    {
      try 
      {
        ResultSet res = Proced.CreateConnection().createStatement().executeQuery("Select md5('"+ new String(jtClave.getPassword()) +"')");
        if (res.next()) 
        {
          SQLUser = jtUsuario.getText();
          SQLPass = res.getString(1);
          //System.out.println(res.getString(1));
        }
        //Proced.setSQLUser("md5");
        //Proced.setSQLPass("pa55w0rd");
        //SQLUser = jtUsuario.getText();
        //SQLPass = Proced.getCampo("Select md5('"+ new String(jtClave.getPassword()) +"')");
      } catch (SQLException x)
      {
        x.printStackTrace();
      }
    }*/
    //Proced.setSQLUser(SQLUser);
    //Proced.setSQLPass(SQLPass);       
    CheqUsuario(SQLUser,SQLPass);
  }
   
  private void CheqUsuario(String Usuario, String Clave) 
  {    
    validado = false;
    validado = OP_Proced.tryToConnect(Usuario, Clave);
    if (validado) 
    {
            OP_Proced.setSQLUser(Usuario);
            OP_Proced.setSQLPass(Clave);
      this.dispose();
    } else
    {
            OP_Proced
            .Mensaje("Acceso denegado, Usuario o Contraseña incorrecta","Error de acceso"); 
      jtClave.setText("");
      lusuario.transferFocus();
      intentos++;
      if (intentos == 3) 
      {
        try 
        {
                    OP_Proced.setSQLUser("md5");
                    OP_Proced.setSQLPass("pa55w0rd");
          String Query = "INSERT INTO auditorias.erroresdeacceso values('" +OP_Proced
                        .getCampo("SELECT current_date")
          + "','" + OP_Proced.Hora(OP_Proced
                                                                                  .getCampo("SELECT current_time"), false, false)
          + "','" +OP_Proced
                        .ObtieneHost()
          + "','" + SQLUserIntruder
          + "','" + SQLPassIntruder + "')";
                    OP_Proced.exActualizar('a',Query);
                    OP_Proced
                    .Mensaje("Su intrusión será reportada al Administrador del Sistema", "Advertencia Legal");
        } catch (Exception x) 
        {
          x.printStackTrace();
        }
        System.exit(0);
      }
    }
/*    
    if (!Usuario.equals("") & !Clave.equals("")) 
    {        
      usuario = Proced.getCampo("SELECT * from pg_catalog.pg_user where usename='"+ Usuario +"'");        
      if (usuario.equals("")) 
      {
        Proced.Mensaje("Acceso denegado, Usuario o Contraseña incorrecta","Error de acceso"); 
        jtClave.setText("");
        lusuario.transferFocus();
      }
    } else 
    {
      Proced.Mensaje("Acceso denegado, debe ser un usuario registrado","Usuario no valido"); 
      jtClave.setText("");
    }  
*/
  }
  
  public void keyReleased(KeyEvent key) 
  {

  }

  public void keyPressed(KeyEvent key) 
  {
   if (key.getKeyCode() == KeyEvent.VK_ENTER) 
    {
      Component c = (Component)key.getSource();
      if (key.getSource() == jtUsuario) 
      {
        c.transferFocus();
      } else if (key.getSource() == jtClave)
      {
        /*Proced.SQLUser = jtUsuario.getText();
        Proced.SQLPass = new String(jtClave.getPassword());   
        CheqUsuario(Proced.SQLUser,Proced.SQLPass);*/
        Acceso();
      }
    }
  }

  public void keyTyped(KeyEvent key) 
  {

  }

  public boolean getValidado() 
  {
    return validado;
  }
  
  public void setValidado(boolean _validado) 
  {
    validado = _validado;
  }
  
  public String getUsuario() 
  {
    return jtUsuario.getTexto();
  }

}

