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
 * frmAnexos_InstLegal.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;


public class frmAnexos_InstLegal extends JDialog implements ActionListener
{
  private JButton baceptar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
  private JButton bcancelar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel3 = new JPanel();
  
  private JTextArea jtcontenido = new JTextArea();
  private JTextArea jtobservaciones = new JTextArea();
  private JTextField jtfecha = new JTextField();
  private JTextField jtestado = new JTextField();
  private JTextField jtcatastro = new JTextField();
  private JTextField jtnroexp = new JTextField();
  
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel15 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JLabel jLabel18 = new JLabel();
  private JLabel jtpalabra5 = new JLabel();
  
  private JComboBox jcestadoanexo = new JComboBox();
  private JComboBox jctiporef = new JComboBox();
  
  private JScrollPane jScrollPane1 = new JScrollPane();
    
  private String expte,catastro="",Query="",idinst="0", idinstref="0",tiporef="",idtiporef="";
   
  /**
   * FORMULARIO PARA AGREGAR O MODIFICAR UN ADJUNTO O ANEXO A UN DOCUMENTO
   * 
   * @param TipoRef: INDICA SI ES ADJUNTO O ANEXO
   * @param IdInstRef: INDICA EL DOCUMENTO DE REFERENCIA
   * @param IdInst: INDICA EL DOCUMENTO AL CUAL SE VA REALIZAR ADJUNTOS O ANEXOS
   * @param Catastro: NUMERO DE CATASTRO RELACIONADO AL DOCUMENTO EN CUESTION
   * @param Expte: NUMERO DE DOCUMENTO EN CUESTION
   * @param SQLQuery: CONSULTA Q ME INDICA LA MODIFICACION
   */
   
  public frmAnexos_InstLegal(String SQLQuery,String Expte, String Catastro, String IdInst,String IdInstRef,String TipoRef)
  {
    try
    {
      expte = Expte;
      catastro = Catastro;
      idinst = IdInst;
      idinstref = IdInstRef;
      Query = SQLQuery;
      tiporef = TipoRef;
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    this.setSize(new Dimension(598, 330));
    this.getContentPane().setLayout(null);
    this.setTitle("Agregar/Modificar - Adjuntos/Anexos");
    this.getContentPane().add(jPanel3, null);
    this.getContentPane().add(bcancelar, null);
    this.getContentPane().add(baceptar, null);
    this.getContentPane().add(jtcontenido, null);
    this.getContentPane().add(jPanel1, null);
    
    baceptar.setText("Aceptar");
    baceptar.setBounds(new Rectangle(360, 270, 101, 25));
    baceptar.setMnemonic('a');
    bcancelar.setText("Cancelar");
    bcancelar.setBounds(new Rectangle(473, 270, 107, 25));
    bcancelar.setMnemonic('c');
    
    jPanel1.setBounds(new Rectangle(10, 65, 570, 190));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jPanel1.setLayout(null);
    jPanel1.add(jLabel18, null);
    jPanel1.add(jScrollPane1, null);
    jPanel1.add(jctiporef, null);
    jPanel1.add(jLabel17, null);
    jPanel1.add(jLabel16, null);
    jPanel1.add(jLabel15, null);
    jPanel1.add(jtfecha, null);
    jPanel1.add(jcestadoanexo, null);
    jPanel3.setLayout(null);
    jPanel3.setBorder(BorderFactory.createLineBorder(new Color(5, 25, 135), 1));
    jPanel3.setBounds(new Rectangle(10, 15, 570, 35));
    jPanel3.add(jtpalabra5, null);
    jPanel3.add(jtestado, null);
    jPanel3.add(jtcatastro, null);
    jPanel3.add(jLabel13, null);
    jPanel3.add(jtnroexp, null);
    jPanel3.add(jLabel11, null);
    
    jcestadoanexo.setBounds(new Rectangle(160, 30, 160, 20));
    jctiporef.setBounds(new Rectangle(400, 30, 160, 20));
    jtcontenido.setBounds(new Rectangle(10, 185, 0, 15));
    
    jtfecha.setBackground(Color.white);
    jtfecha.setBounds(new Rectangle(10, 30, 70, 20));
    jtobservaciones.setMargin(new Insets(5, 5, 5, 5));
    jtobservaciones.setLineWrap(true);
    jtobservaciones.setWrapStyleWord(true);
    jtpalabra5.setText("Estado:");
    jtpalabra5.setBounds(new Rectangle(707, 10, 45, 15));
    jtpalabra5.setToolTipText("null");
    jtestado.setEditable(false);
    jtestado.setBounds(new Rectangle(707, 25, 68, 20));
    jtcatastro.setBackground(Color.white);
    jtcatastro.setEditable(false);
    jtcatastro.setBounds(new Rectangle(450, 7, 115, 20));
    jtnroexp.setBackground(Color.white);
    jtnroexp.setEditable(false);
    jtnroexp.setBounds(new Rectangle(110, 7, 175, 20));
    
    jLabel11.setBounds(new Rectangle(15, 10, 90, 15));
    jLabel11.setText("Nº Doc./Expte:");
    jLabel11.setToolTipText("null");
    jLabel13.setText("Nº Catastro:");
    jLabel13.setBounds(new Rectangle(370, 10, 75, 15));
    jLabel15.setBounds(new Rectangle(10, 10, 38, 20));
    jLabel15.setText("Fecha:");
    jLabel16.setBounds(new Rectangle(160, 10, 45, 20));
    jLabel16.setText("Estado:");
    jLabel17.setBounds(new Rectangle(400, 10, 31, 20));
    jLabel17.setText("Tipo:");
    jLabel18.setBounds(new Rectangle(10, 65, 94, 20));
    jLabel18.setText("Observaciones:");
    
    jScrollPane1.setBounds(new Rectangle(10, 85, 550, 100));
    jScrollPane1.getViewport().add(jtobservaciones, null);
      
    //jtfecha.addKeyListener(new dateListen(true));
    jtfecha.addMouseListener(new MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1)
          {
            JTextField kk = new JTextField(((JTextField)e.getSource()).getText());
            SelectorFecha c = new SelectorFecha(kk);
                                             OP_Proced.CentraVentana(c);
            c.setModal(true);
            c.show();
            jtfecha.setText(kk.getText());
            {
              
            }
          }
        }
      });
    baceptar.addActionListener(this);
    bcancelar.addActionListener(this);
    
    jtnroexp.setText(expte);
    jtcatastro.setText(catastro);
    
    jctiporef.addItemListener(new ItemListener()
      {
        public void itemStateChanged(ItemEvent evt)
        {
          if (evt.getStateChange() == ItemEvent.SELECTED) 
          {
            idtiporef = OP_Proced
                                                  .getCampo("Select idtipo from sim.tiposreferencia where descripcion='" + jctiporef.getSelectedItem() + "'");
          }
        }
      }); 
    
    if (Query.length()>0)
    {
      ResultSet Reg = OP_Proced.exConsulta(Query);
      if (Reg.next())
      {        
        idinst = Reg.getString(1);
        idinstref = Reg.getString(2);
                OP_Proced.CargaCombo(jctiporef,"Select descripcion from sim.tiposreferencia where estado<>'*'",OP_Proced
                                     .getCampo("Select descripcion from sim.tiposreferencia where idtipo="+ Reg.getString(3)));
        idtiporef = Reg.getString(3);
        jtfecha.setText(OP_Proced.Fecha2(Reg.getString(4),true));
        jtobservaciones.setText(Reg.getString(5));
                OP_Proced
                .CargaCombo(jcestadoanexo,"Select descripcion from sim.tablacombo where estado<>'*' and combo='jcestadoanexo'",Reg.getString(6));
      } 
    } else 
    {
        jtfecha.setText(OP_Proced.FechaHora(true,false));
            OP_Proced
            .CargaCombo(jctiporef,"Select descripcion from sim.tiposreferencia where estado<>'*'",tiporef);      
        switch (Integer.parseInt(OP_Proced
                                     .getCampo("Select idtipo from sim.tiposreferencia where descripcion='"+ tiporef +"'")))
         {
            case 2:
                OP_Proced
                .CargaCombo(jcestadoanexo,"Select descripcion from sim.tablacombo where estado<>'*' and combo='jcestadoanexo'","P/Anexar");  
              break;
            case 4:
                OP_Proced
                .CargaCombo(jcestadoanexo,"Select descripcion from sim.tablacombo where estado<>'*' and combo='jcestadoanexo'","P/Adjuntar");  
              break;  
         }        
    }
    
    
  }
  
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      if (e.getSource() == bcancelar) 
      { 
        this.dispose();
      } else if (e.getSource() == baceptar)
      {
        if (Query.length()>0)
        {
          String Q ="Update sim.instxinst set idtiporef="+ idtiporef +", fecha='" +OP_Proced
                        .Fecha(jtfecha.getText(),false) 
                   +"', observaciones='"+ jtobservaciones.getText() +"', estadoanexo='"+ jcestadoanexo.getSelectedItem() 
                   +"' Where idinst="+ idinst +" and idinstref="+ idinstref ;
          System.out.println("si query > 0: "+Q);
          if (OP_Proced.exActualizar('m',Q)) { this.dispose();}
        } else 
        {
          String Q="";
          
          if (OP_Proced
                        .getCampo("SELECT count(*) FROM sim.instxinst WHERE idinst="+ idinst +" AND idtiporef = "+ idtiporef +" AND idinstref = "+ idinstref).equals("0"))
          {
              Q ="Insert into sim.instxinst values("+ idinst +","+ idinstref +","+ idtiporef +",'" + OP_Proced
                            .Fecha(jtfecha.getText(),false) 
                +"','"+ jtobservaciones.getText() +"','"+ jcestadoanexo.getSelectedItem() +"','')";
              System.out.println("Si count = 0: "+Q);
          } else 
          {
              /*Q ="Update sim.instxinst set idtiporef="+ idtiporef +", fecha='"+ Proced.Fecha(jtfecha.getText(),false) 
                +"', observaciones='"+ jtobservaciones.getText() +"', estadoanexo='"+ jcestadoanexo.getSelectedItem() 
                +"' Where idinst="+ idinst +" and idinstref="+ idinstref ;*/
              Q ="UPDATE sim.instxinst SET estado = '', fecha='" + OP_Proced
                            .Fecha(jtfecha.getText(),false) 
                +"', observaciones='"+ jtobservaciones.getText() +"', estadoanexo='"+ jcestadoanexo.getSelectedItem() 
                +"' WHERE idinst = "+ idinst +" AND idtiporef = "+ idtiporef +" AND idinstref = "+ idinstref ;  
              System.out.println("Update: "+Q);  
          }
          if (OP_Proced.exActualizar('a',Q)) { this.dispose();}
        }
      }
    } catch (Exception x)
    {
            OP_Proced.Mensaje(x.getMessage(),"ERROR 01: ");
    }
  }
}