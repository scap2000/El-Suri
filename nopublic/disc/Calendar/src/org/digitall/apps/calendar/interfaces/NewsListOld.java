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
 * NewsListOld.java
 *
 * */
package org.digitall.apps.calendar.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.Timer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.Grilla;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class NewsListOld extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel11 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private DefaultTableModel modelo = new DefaultTableModel();
    private BasicButton bcerrar = new BasicButton(new ImageIcon(Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private BasicButton beliminar = new BasicButton(new ImageIcon(Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private BasicButton bresponder = new BasicButton(new ImageIcon(Proced.class.getResource("iconos/16x16/modificar.gif")));
    private BasicButton bagregar = new BasicButton(new ImageIcon(Proced.class.getResource("iconos/16x16/agregar.gif")));
    private BasicLabel jLabel10 = new BasicLabel();
    private BasicLabel lblNovedad = new BasicLabel();
    private BasicLabel lbltitulo = new BasicLabel();
    private BasicLabel lblprioridad = new BasicLabel();
    private BasicCheckBox jcheckvisto = new BasicCheckBox();
    private BasicScrollPane jScrollPane3 = new BasicScrollPane();
    private JTextArea jtinforme = new JTextArea();
    private JTextArea jtdictamen1 = new JTextArea();
    private JTextArea jtobserv1 = new JTextArea();
    private JTextArea jtdictamen2 = new JTextArea();
    private JTextArea jtobserv2 = new JTextArea();
    private BasicTextField jtaltaprioridad = new BasicTextField();
    private BasicTextField jtprioridadmedia = new BasicTextField();
    private BasicTextField jtbajaprioridad = new BasicTextField();
    private BasicTextField jtcantidadalta = new BasicTextField();
    private BasicTextField jtcantidadbaja = new BasicTextField();
    private BasicTextField jtcantidadmedia = new BasicTextField();
    private BasicCheckBox jchektodos = new BasicCheckBox();
    /** Variables */
    private String ConsultaCount = "", Consulta = "", cfiltro = "";
    private String filtroTodos = "", idnovedad = "";
    private int idreceptor = 0;
    private String idsede;
    private Vector datos1, datosx = new Vector();
    private boolean selec = false;
    private Timer timer1 = new Timer();
    private int Liminf = 0, CantReg1 = 200;
    private int[] tcol = { };
    //no se ven  
    private int[] tamcol = { };
    private Grilla jgNovedades = new Grilla(CantReg1, tcol, tamcol, false, false);
    private Vector cabecera = new Vector();

    public NewsListOld() {
	try {
	    jbInit();
	} catch (SQLException e) {
	    Advisor.messageBox(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
	    this.dispose();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getContentPane().setLayout(null);
	this.setSize(new Dimension(577, 524));
	this.setTitle("Bandeja de Entrada: Novedades");
	jLabel10.setOpaque(true);
	jLabel10.setFont(new Font("Dialog", 1, 15));
	jLabel10.setText(" Listado de Novedades: ");
	jLabel10.setBounds(new Rectangle(15, 70, 190, 15));
	jgNovedades.setBounds(new Rectangle(10, 5, 525, 155));
	jgNovedades.setBorder(BorderFactory.createLineBorder(Color.red, 1));
	jgNovedades.setLayout(null);
	jPanel11.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel11.setLayout(null);
	jScrollPane3.setBounds(new Rectangle(10, 10, 530, 150));
	jtinforme.setEditable(false);
	bcerrar.setText("Cerrar");
	bcerrar.setBounds(new Rectangle(5, 465, 92, 25));
	bcerrar.setMnemonic('C');
	beliminar.setText("Eliminar");
	beliminar.setBounds(new Rectangle(450, 465, 105, 25));
	beliminar.setMnemonic('E');
	bresponder.setText("Responder");
	bresponder.setBounds(new Rectangle(330, 465, 115, 25));
	bresponder.setMnemonic('M');
	bagregar.setText("Agregar");
	bagregar.setBounds(new Rectangle(220, 465, 105, 25));
	bagregar.setMnemonic('A');
	jScrollPane3.getViewport();
	jPanel1.add(lblprioridad, null);
	jPanel1.add(lbltitulo, null);
	jPanel1.add(jtcantidadmedia, null);
	jPanel1.add(jtcantidadbaja, null);
	jPanel1.add(jtcantidadalta, null);
	jPanel1.add(jtbajaprioridad, null);
	jPanel1.add(jtprioridadmedia, null);
	jPanel1.add(jtaltaprioridad, null);
	jPanel2.add(jgNovedades, null);
	this.getContentPane().add(jLabel10, null);
	jPanel2.add(jchektodos, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(lblNovedad, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(jtobserv2, null);
	this.getContentPane().add(jtdictamen2, null);
	this.getContentPane().add(bagregar, null);
	this.getContentPane().add(bresponder, null);
	this.getContentPane().add(beliminar, null);
	this.getContentPane().add(bcerrar, null);
	jScrollPane3.getViewport().add(jtinforme, null);
	jPanel11.add(jScrollPane3, null);
	jPanel11.add(jcheckvisto, null);
	this.getContentPane().add(jPanel11, null);
	//    jtdesde.addKeyListener(new dateListen(true));
	//    jthasta.addKeyListener(new dateListen(true));
	jgNovedades.Redimensiona();
	jtobserv2.setBounds(new Rectangle(0, 0, 0, 15));
	jtobserv2.setEditable(false);
	jtdictamen2.setBounds(new Rectangle(0, 0, 0, 15));
	jtdictamen2.setEditable(false);
	jtobserv1.setEditable(false);
	jtdictamen1.setEditable(false);
	datos1 = jgNovedades.VDatos();
	jPanel11.setBounds(new Rectangle(5, 275, 550, 185));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel1.setBounds(new Rectangle(10, 10, 545, 55));
	jchektodos.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      jchektodos_actionPerformed(e);
				  }

			      }
	);
	jchektodos.setMnemonic('t');
	jchektodos.setBounds(new Rectangle(10, 160, 85, 20));
	jchektodos.setText("Ver Todos");
	bcerrar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bcerrar_actionPerformed(e);
			       }

			   }
	);
	beliminar.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     beliminar_actionPerformed(e);
				 }

			     }
	);
	bresponder.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      bresponder_actionPerformed(e);
				  }

			      }
	);
	bagregar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bagregar_actionPerformed(e);
				}

			    }
	);
	jtinforme.setWrapStyleWord(true);
	jtinforme.setMargin(new Insets(5, 5, 5, 5));
	jtinforme.setLineWrap(true);
	jLabel10.setForeground(Color.blue);
	/*timer1.scheduleAtFixedRate(new TimerTask(){
      public void run() {
          try 
          {     
              datosx = jgNovedades.VDatos();
              if (!datos1.equals(datosx))
              {    
                datos1=datosx;
                idnovedad = datos1.elementAt(0).toString();
                verificaCheck(idnovedad);
                jtinforme.setText(datos1.elementAt(12).toString());
                beliminar.setEnabled(true);
                bresponder.setEnabled(true);
              } 
          } catch (Exception x)
          {
             Proced.Mensaje(x.getMessage(),"ERROR dato:");
          }
        }
      },0, 500);*/
	/** 
     * Accion que se dispara cuando se hace "UN CLICK" en la grilla
     * */
	jgNovedades.getTable().addMouseListener(new MouseAdapter() {

					     public void mouseClicked(MouseEvent e) {
						 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						     datosx = jgNovedades.VDatos();
						     idnovedad = datosx.elementAt(0).toString();
						     verificaCheck(idnovedad);
						     jtinforme.setText(datosx.elementAt(12).toString());
						     beliminar.setEnabled(true);
						     bresponder.setEnabled(true);
						 } else
						     beliminar.setEnabled(false);
					     }

					 }
	);
	/** 
     * Accion que se dispara cuando se hace "DOBLE CLICK" en la grilla
     * */
	jgNovedades.getTable().addMouseListener(new MouseAdapter() {

					     public void mouseClicked(MouseEvent e) {
						 if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						     datosx = jgNovedades.VDatos();
						     verificaCheck(idnovedad);
						     jtinforme.setText(datosx.elementAt(12).toString());
						     idnovedad = datosx.elementAt(0).toString();
						     //ABRIR EL NewsMgmt
						     /*frmRespNovedades nov = new frmRespNovedades(idnovedad);
						     Proced.CentraVentana(nov);
						     nov.setModal(true);
						     nov.setVisible(true);*/
						     ActualizaTabla();
						 }
					     }

					 }
	);
	jgNovedades.getTable().addKeyListener(new KeyAdapter() {

					   public void keyPressed(KeyEvent e) {
					       jgNovedades_keyTyped(e);
					   }

					   public void keyReleased(KeyEvent e) {
					       jgNovedades_keyTyped(e);
					   }

					   public void keyTyped(KeyEvent e) {
					       jgNovedades_keyTyped(e);
					   }

				       }
	);
	idreceptor = LibSQL.getInt("Select idpersona From personas Where alias = '" + Environment.sessionUser + "' ", "");
	jtinforme.setForeground(Color.blue);
	jtinforme.setFont(new Font("Dialog", 1, 11));
	jcheckvisto.setText("Checkeado");
	jcheckvisto.setBounds(new Rectangle(10, 160, 90, 20));
	jcheckvisto.setMnemonic('t');
	jcheckvisto.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       jcheckvisto_actionPerformed(e);
				   }

			       }
	);
	jtaltaprioridad.setHorizontalAlignment(BasicTextField.CENTER);
	jtprioridadmedia.setHorizontalAlignment(BasicTextField.CENTER);
	jtbajaprioridad.setHorizontalAlignment(BasicTextField.CENTER);
	jtbajaprioridad.setBackground(new Color(0, 201, 0));
	jtbajaprioridad.setEditable(false);
	jtbajaprioridad.setBounds(new Rectangle(415, 30, 70, 20));
	jtprioridadmedia.setBounds(new Rectangle(280, 30, 65, 20));
	jtprioridadmedia.setEditable(false);
	jtprioridadmedia.setBackground(Color.orange);
	jtaltaprioridad.setBackground(new Color(255, 12, 31));
	jtaltaprioridad.setEditable(false);
	jtaltaprioridad.setBounds(new Rectangle(135, 30, 70, 20));
	bresponder.setEnabled(false);
	beliminar.setEnabled(false);
	bcerrar.setFont(new Font("Dialog", 1, 11));
	beliminar.setFont(new Font("Dialog", 1, 11));
	bresponder.setFont(new Font("Dialog", 1, 11));
	bagregar.setFont(new Font("Dialog", 1, 11));
	lblNovedad.setForeground(Color.blue);
	lblNovedad.setBounds(new Rectangle(15, 265, 85, 15));
	lblNovedad.setText(" Novedad: ");
	lblNovedad.setFont(new Font("Dialog", 1, 15));
	lblNovedad.setOpaque(true);
	jcheckvisto.setHorizontalAlignment(SwingConstants.LEFT);
	jchektodos.setHorizontalAlignment(SwingConstants.LEFT);
	jchektodos.setFont(new Font("Dialog", 1, 11));
	jcheckvisto.setFont(new Font("Dialog", 1, 11));
	filtroTodos = "AND chequeado = 'false' ";
	lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblprioridad.setFont(new Font("Dialog", 1, 13));
	lbltitulo.setFont(new Font("Dialog", 1, 13));
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel2.setLayout(null);
	jPanel2.setBounds(new Rectangle(10, 75, 545, 185));
	lblprioridad.setHorizontalAlignment(SwingConstants.CENTER);
	lblprioridad.setBounds(new Rectangle(15, 30, 75, 20));
	lblprioridad.setText("Prioridad:");
	lbltitulo.setForeground(Color.blue);
	lbltitulo.setBounds(new Rectangle(132, 5, 280, 20));
	lbltitulo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jtcantidadmedia.setFont(new Font("Dialog", 1, 11));
	jtcantidadbaja.setFont(new Font("Dialog", 1, 11));
	jtcantidadalta.setFont(new Font("Dialog", 1, 11));
	jtbajaprioridad.setFont(new Font("Dialog", 1, 11));
	jtprioridadmedia.setFont(new Font("Dialog", 1, 11));
	jtaltaprioridad.setFont(new Font("Dialog", 1, 11));
	jtaltaprioridad.setText("ALTA");
	jtbajaprioridad.setText("BAJA");
	jtprioridadmedia.setText("MEDIA");
	jtcantidadmedia.setHorizontalAlignment(BasicTextField.CENTER);
	jtcantidadmedia.setBackground(Color.white);
	jtcantidadmedia.setEditable(false);
	jtcantidadmedia.setBounds(new Rectangle(251, 30, 30, 20));
	jtcantidadbaja.setHorizontalAlignment(BasicTextField.CENTER);
	jtcantidadbaja.setBackground(Color.white);
	jtcantidadbaja.setEditable(false);
	jtcantidadbaja.setBounds(new Rectangle(386, 30, 30, 20));
	jtcantidadalta.setBounds(new Rectangle(106, 30, 30, 20));
	jtcantidadalta.setEditable(false);
	jtcantidadalta.setBackground(Color.white);
	jtcantidadalta.setHorizontalAlignment(BasicTextField.CENTER);
	setearMsj();
	setearCabecera();
	ActualizaTabla();
    }

    /** 
   * Funciï¿½n que selecciona el tï¿½tulo correcto segun las opciones
   * que se seleccionen en los combos
   * */
    private void setearCabecera() {
	cabecera.removeAllElements();
	cabecera.addElement("*");
	//idnovedad
	cabecera.addElement("*");
	//idprioridad
	cabecera.addElement("Prioridad");
	cabecera.addElement("Fecha");
	cabecera.addElement("Hora");
	cabecera.addElement("*");
	//idemisor
	cabecera.addElement("Emisor");
	cabecera.addElement("*");
	//idarea
	cabecera.addElement("*");
	//cabecera.addElement("Area");
	cabecera.addElement("Estado");
	cabecera.addElement("*");
	//idobrarelacionado
	cabecera.addElement("Obra");
	cabecera.addElement("*");
	//informe
    }

    public void ActualizaTabla() {
	Consulta = "SELECT idnovedad,idprioridad,(Select descripcion From vecinos.tiposprioridad Where idtipo = novedades.idprioridad)" + " as prioridad,fecha,hora,idemisor,(Select apellido||', '||nombre From personas Where idpersona = novedades.idemisor)" + " as emisor, idarea,(Select nombre From estadoobras.tipoareas Where idtipo = novedades.idarea) as area, estador," + " idobrarelacionado,(Select nombre From estadoobras.obras Where idobra = novedades.idobrarelacionado) as obra, " + " informe FROM sopsalta.novedades WHERE estado <> '*' AND idreceptor = 0" + idreceptor + filtroTodos;
	ConsultaCount = "Select count(*) From (" + Consulta + ") as mat";
	//System.out.println(ConsultaCount);
	jgNovedades.ActualizaTabla((BasicDialog)this, Consulta, ConsultaCount, cabecera);
	jcheckvisto.setSelected(false);
	beliminar.setEnabled(false);
	bresponder.setEnabled(false);
	setearMsj();
	idnovedad = "0";
    }

    private void setearMsj() {
	int total = 0;
	int altaprioridad = 0;
	int mediaprioridad = 0;
	int bajaprioridad = 0;
	total = LibSQL.getInt("Select count(*) FROM sopsalta.novedades WHERE estado <> '*' AND idreceptor = 0" + idreceptor + " AND NOT chequeado", "");
	altaprioridad = LibSQL.getInt("Select count(*) FROM sopsalta.novedades WHERE estado <> '*' AND idreceptor = 0" + idreceptor + " AND chequeado = false AND idprioridad = 1", "");
	mediaprioridad = LibSQL.getInt("Select count(*) FROM sopsalta.novedades WHERE estado <> '*' AND idreceptor = 0" + idreceptor + " AND chequeado = false AND idprioridad = 2", "");
	bajaprioridad = LibSQL.getInt("Select count(*) FROM sopsalta.novedades WHERE estado <> '*' AND idreceptor = 0" + idreceptor + " AND chequeado = false AND idprioridad = 3", "");
	lbltitulo.setText("USTED TIENE " + total + " MENSAJES SIN LEER");
	if (total == 0) {
	    lbltitulo.setForeground(Color.BLUE);
	} else {
	    lbltitulo.setForeground(Color.RED);
	}
	if (altaprioridad == 0) {
	    jtaltaprioridad.setBackground(Color.WHITE);
	} else {
	    jtaltaprioridad.setBackground(Color.RED);
	}
	if (mediaprioridad == 0) {
	    jtprioridadmedia.setBackground(Color.WHITE);
	} else {
	    jtprioridadmedia.setBackground(Color.YELLOW);
	}
	if (bajaprioridad == 0) {
	    jtbajaprioridad.setBackground(Color.WHITE);
	} else {
	    jtbajaprioridad.setBackground(Color.GREEN);
	}
	jtcantidadalta.setText(String.valueOf(altaprioridad));
	jtcantidadmedia.setText(String.valueOf(mediaprioridad));
	jtcantidadbaja.setText(String.valueOf(bajaprioridad));
    }

    private void verificaCheck(String _idnovedad) {
	if (!idnovedad.equals("")) {
	    //REVISAR!!!
	    jcheckvisto.setSelected(LibSQL.getBoolean("SELECT chequeado FROM sopsalta.novedades WHERE idnovedad = " + _idnovedad, ""));
	} else
	    Advisor.messageBox("Debe seleccionar un mensaje", "Error");
    }

    private void bimprimir_actionPerformed(ActionEvent e) {
	//GENERAR REPORTE!!!
    }

    private void bagregar_actionPerformed(ActionEvent e) {
	//AGREGAR UNA NOVEDAD
    }

    private void bresponder_actionPerformed(ActionEvent e) {
	//RESPONDER UNA NOVEDAD
    }

    private void beliminar_actionPerformed(ActionEvent e) {
	if (!datosx.isEmpty()) {
	    String Q = "UPDATE TABLA NEWS set estado='*' where idnew=" + datosx.elementAt(0).toString();
	    if (LibSQL.getBoolean(Q, "")) {
		ActualizaTabla();
	    } else {
		//ERROR
	    }
	} else {
	    Advisor.messageBox("Debe Seleccionar una fila para poder borrar", "Mensaje");
	}
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void jchektodos_actionPerformed(ActionEvent e) {
	if (jchektodos.isSelected()) {
	    filtroTodos = "";
	} else {
	    filtroTodos = "AND NOT chequeado";
	}
	ActualizaTabla();
    }

    private void jcheckvisto_actionPerformed(ActionEvent e) {
	if (jcheckvisto.isSelected()) {
	    if (!idnovedad.equals("0")) {
		String Q = "UPDATE TABLA NEWS SET chequeado = 'true' WHERE idnew = " + idnovedad;
		if (LibSQL.getBoolean(Q, "")) {
		    ActualizaTabla();
		    setearMsj();
		    jtinforme.setText("");
		} else {
		    //ERROR
		}
	    } else
		Advisor.messageBox("Debe Seleccionar una fila para poder borrar", "Mensaje");
	}
    }

    private void jgNovedades_keyTyped(KeyEvent e) {
	try {
	    if (datosx != jgNovedades.VDatos()) {
		datosx = jgNovedades.VDatos();
		idnovedad = datos1.elementAt(0).toString();
		//verificaCheck(idnovedad);
		//jtinforme.setText(datos1.elementAt(12).toString());
	    }
	} catch (ArrayIndexOutOfBoundsException x) {
	    e.consume();
	}
    }

}
