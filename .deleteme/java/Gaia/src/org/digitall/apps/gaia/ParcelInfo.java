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
 * ParcelInfo.java
 *
 * */
package org.digitall.apps.gaia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;

public class ParcelInfo extends JFrame {

    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel12 = new JPanel();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    private JPanel jPanel6 = new JPanel();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BorderLayout borderLayout5 = new BorderLayout();
    private JLabel jLabel8 = new JLabel();
    private BorderLayout borderLayout6 = new BorderLayout();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JList jList1 = new JList();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JList jList2 = new JList();
    private AddButton jButton1 = new AddButton();
    private ModifyButton jButton2 = new ModifyButton();
    private DeleteButton jButton3 = new DeleteButton();
    private AddButton jButton4 = new AddButton();
    private ModifyButton jButton5 = new ModifyButton();
    private DeleteButton jButton6 = new DeleteButton();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JPanel jPanel7 = new JPanel();
    private JPanel jPanel8 = new JPanel();
    private JLabel jLabel15 = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel17 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JLabel jLabel19 = new JLabel();
    private JLabel jLabel110 = new JLabel();
    private JLabel jLabel111 = new JLabel();
    private JLabel jLabel112 = new JLabel();
    private JLabel jLabel113 = new JLabel();
    private JLabel jLabel114 = new JLabel();
    private JLabel jLabel20 = new JLabel();
    private JLabel jLabel21 = new JLabel();
    private JLabel jLabel115 = new JLabel();
    private JLabel jLabel22 = new JLabel();

    public ParcelInfo() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(445, 524));
	this.getContentPane().setLayout(borderLayout1);
	jPanel1.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jPanel1.setBackground(Color.white);
	jPanel1.setLayout(null);
	jPanel2.setBounds(new Rectangle(5, 5, 430, 25));
	jPanel2.setBackground(new Color(198, 198, 255));
	jPanel2.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jPanel2.setLayout(borderLayout2);
	jPanel12.setBounds(new Rectangle(5, 255, 430, 25));
	jPanel12.setBackground(new Color(198, 198, 255));
	jPanel12.setLayout(borderLayout3);
	jPanel12.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jLabel1.setText("Ficha Técnica - Identificador Nº: 23578");
	jLabel1.setFont(new Font("Dialog", 1, 12));
	jLabel1.setForeground(new Color(0, 99, 148));
	jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel12.setText("Otros Datos");
	jLabel12.setFont(new Font("Dialog", 1, 12));
	jLabel12.setForeground(new Color(0, 99, 148));
	jLabel12.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel2.setText("Nº Catastro:");
	jLabel2.setBounds(new Rectangle(5, 35, 90, 30));
	jLabel2.setFont(new Font("Dialog", 1, 14));
	jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel4.setText("8888888");
	jLabel4.setBounds(new Rectangle(100, 35, 100, 30));
	jLabel4.setFont(new Font("Dialog", 1, 14));
	jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel4.setForeground(Color.red);
	jLabel4.setBackground(new Color(255, 255, 181));
	jLabel4.setOpaque(true);
	jLabel4.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jLabel5.setText("77777777");
	jLabel5.setBounds(new Rectangle(100, 70, 100, 30));
	jLabel5.setFont(new Font("Dialog", 1, 14));
	jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel5.setForeground(new Color(255, 255, 181));
	jLabel5.setBackground(new Color(255, 90, 33));
	jLabel5.setOpaque(true);
	jLabel5.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jLabel3.setText("Nº Cuenta:");
	jLabel3.setBounds(new Rectangle(15, 70, 80, 30));
	jLabel3.setFont(new Font("Dialog", 1, 14));
	jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel6.setText("Sección");
	jLabel6.setFont(new Font("Dialog", 0, 13));
	jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel6.setOpaque(true);
	jLabel6.setBackground(new Color(255, 181, 99));
	jLabel6.setBounds(new Rectangle(1, 1, 83, 16));
	jLabel6.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel7.setText("A");
	jLabel7.setFont(new Font("Dialog", 1, 32));
	jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel7.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel7.setBackground(new Color(255, 132, 0));
	jLabel7.setOpaque(true);
	jPanel3.setBounds(new Rectangle(205, 35, 230, 65));
	jPanel3.setLayout(null);
	jPanel3.setBackground(new Color(247, 181, 0));
	jPanel3.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jPanel4.setLayout(borderLayout4);
	jPanel4.setBackground(new Color(247, 181, 0));
	jPanel4.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jPanel4.setBounds(new Rectangle(0, 0, 60, 65));
	jPanel5.setBackground(new Color(247, 181, 0));
	jPanel5.setLayout(borderLayout6);
	jPanel5.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jPanel5.setBounds(new Rectangle(145, 0, 85, 65));
	jPanel6.setBackground(new Color(247, 181, 0));
	jPanel6.setLayout(borderLayout5);
	jPanel6.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jPanel6.setBounds(new Rectangle(60, 0, 85, 65));
	jLabel8.setText("Parcela");
	jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel8.setFont(new Font("Dialog", 0, 13));
	jLabel8.setOpaque(true);
	jLabel8.setBackground(new Color(255, 181, 99));
	jLabel8.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel9.setText("Manzana");
	jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel9.setFont(new Font("Dialog", 0, 13));
	jLabel9.setOpaque(true);
	jLabel9.setBackground(new Color(255, 181, 99));
	jLabel9.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel10.setText("999Z");
	jLabel10.setFont(new Font("Dialog", 1, 24));
	jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel10.setBounds(new Rectangle(1, 19, 58, 45));
	jLabel10.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel10.setBackground(new Color(255, 132, 0));
	jLabel10.setOpaque(true);
	jLabel11.setText("888W");
	jLabel11.setFont(new Font("Dialog", 1, 24));
	jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel11.setBounds(new Rectangle(1, 19, 58, 45));
	jLabel11.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 1));
	jLabel11.setBackground(new Color(255, 132, 0));
	jLabel11.setOpaque(true);
	jScrollPane1.setBounds(new Rectangle(5, 20, 390, 75));
	jScrollPane2.setBounds(new Rectangle(5, 20, 390, 75));
	jButton1.setBounds(new Rectangle(400, 20, 25, 25));
	jButton2.setBounds(new Rectangle(400, 45, 25, 25));
	jButton3.setBounds(new Rectangle(400, 70, 25, 25));
	jButton4.setBounds(new Rectangle(400, 20, 25, 25));
	jButton5.setBounds(new Rectangle(400, 45, 25, 25));
	jButton6.setBounds(new Rectangle(400, 70, 25, 25));
	jPanel4.add(jLabel6, BorderLayout.NORTH);
	jPanel4.add(jLabel7, BorderLayout.CENTER);
	jPanel5.add(jLabel8, BorderLayout.NORTH);
	jPanel5.add(jLabel11, BorderLayout.CENTER);
	jPanel3.add(jPanel4, BorderLayout.WEST);
	jPanel3.add(jPanel5, BorderLayout.EAST);
	jPanel3.add(jPanel6, BorderLayout.CENTER);
	jPanel6.add(jLabel9, BorderLayout.NORTH);
	jPanel6.add(jLabel10, BorderLayout.CENTER);
	jPanel7.add(jLabel13, null);
	jPanel7.add(jButton1, null);
	jPanel7.add(jButton2, null);
	jPanel7.add(jButton3, null);
	jScrollPane1.getViewport().add(jList1, null);
	jPanel7.add(jScrollPane1, null);
	jPanel8.add(jButton4, null);
	jPanel8.add(jButton5, null);
	jPanel8.add(jButton6, null);
	jScrollPane2.getViewport().add(jList2, null);
	jPanel8.add(jScrollPane2, null);
	jPanel8.add(jLabel14, null);
	jPanel1.add(jLabel22, null);
	jPanel1.add(jLabel115, null);
	jPanel1.add(jLabel21, null);
	jPanel1.add(jLabel20, null);
	jPanel1.add(jLabel114, null);
	jPanel1.add(jLabel113, null);
	jPanel1.add(jLabel112, null);
	jPanel1.add(jLabel111, null);
	jPanel1.add(jLabel110, null);
	jPanel1.add(jLabel19, null);
	jPanel1.add(jLabel18, null);
	jPanel1.add(jLabel17, null);
	jPanel1.add(jLabel16, null);
	jPanel1.add(jLabel15, null);
	jPanel1.add(jPanel7, null);
	jPanel1.add(jPanel8, null);
	jPanel1.add(jPanel3, null);
	jPanel1.add(jLabel3, null);
	jPanel1.add(jLabel5, null);
	jPanel1.add(jLabel4, null);
	jPanel1.add(jLabel2, null);
	jPanel2.add(jLabel1, BorderLayout.CENTER);
	jPanel1.add(jPanel2, null);
	jPanel12.add(jLabel12, BorderLayout.CENTER);
	jPanel1.add(jPanel12, null);
	this.getContentPane().add(jPanel1, BorderLayout.CENTER);
	Object[] listData = new Object[] {"Pérez, Juan (DNI Nº 12345678)", "García, Pedro M. (DNI Nº 22457358)","Pérez, Juan (DNI Nº 12345678)", "García, Pedro M. (DNI Nº 22457358)","Pérez, Juan (DNI Nº 12345678)", "García, Pedro M. (DNI Nº 22457358)"};
	Object[] listData2 = new Object[] {"Av. Colón Nº 437", "Los Peteribíes Nº 178", "Av. Colón Nº 437", "Los Peteribíes Nº 178", "Av. Colón Nº 437", "Los Peteribíes Nº 178"};
	jList1.setListData(listData);
	jList2.setListData(listData2);
	jButton1.setShowText(false);
	jButton2.setShowText(false);
	jButton3.setShowText(false);
	jButton4.setShowText(false);
	jButton5.setShowText(false);
	jButton6.setShowText(false);
	jLabel13.setText("Propietario(s)");
	jLabel13.setBounds(new Rectangle(160, 0, 110, 20));
	jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel13.setFont(new Font("Dialog", 1, 13));
	jPanel7.setBounds(new Rectangle(5, 285, 430, 100));
	jPanel7.setLayout(null);
	jPanel7.setBackground(new Color(144, 202, 220));
	jPanel7.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jPanel8.setBounds(new Rectangle(5, 390, 430, 100));
	jPanel8.setLayout(null);
	jPanel8.setBackground(new Color(37, 220, 207));
	jPanel8.setBorder(BorderFactory.createLineBorder(new Color(148, 107, 0), 2));
	jLabel15.setText("Superficie total: 300 m²");
	jLabel15.setBounds(new Rectangle(15, 130, 185, 25));
	jLabel16.setText("Superficie cubierta: 80 m²");
	jLabel16.setBounds(new Rectangle(15, 150, 185, 25));
	jLabel17.setText("Perímetro: 60 m");
	jLabel17.setBounds(new Rectangle(15, 110, 185, 25));
	jLabel18.setText("Frente: 13 m");
	jLabel18.setBounds(new Rectangle(15, 170, 185, 25));
	jLabel19.setText("Esquina: SI");
	jLabel19.setBounds(new Rectangle(205, 110, 185, 25));
	jLabel110.setText("Ochava: 2.52 m");
	jLabel110.setBounds(new Rectangle(205, 130, 185, 25));
	jLabel111.setText("Vereda: SI");
	jLabel111.setBounds(new Rectangle(205, 150, 185, 25));
	jLabel112.setText("Estado: Regular");
	jLabel112.setBounds(new Rectangle(205, 170, 185, 25));
	jLabel113.setText("Obra: SI");
	jLabel113.setBounds(new Rectangle(15, 190, 185, 25));
	jLabel114.setText("Código Edif.: Art. 18");
	jLabel114.setBounds(new Rectangle(15, 210, 185, 25));
	jLabel20.setText("Comercio: SI");
	jLabel20.setBounds(new Rectangle(205, 190, 185, 25));
	jLabel21.setText("Padrón: X12 - 2008");
	jLabel21.setBounds(new Rectangle(205, 210, 185, 25));
	jLabel115.setText("Baldío: NO");
	jLabel115.setBounds(new Rectangle(15, 230, 185, 25));
	jLabel22.setText("Microbasural: NO");
	jLabel22.setBounds(new Rectangle(205, 230, 185, 25));
	jLabel14.setText("Domicilio(s)");
	jLabel14.setBounds(new Rectangle(160, 0, 110, 20));
	jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel14.setFont(new Font("Dialog", 1, 13));

    }

}
