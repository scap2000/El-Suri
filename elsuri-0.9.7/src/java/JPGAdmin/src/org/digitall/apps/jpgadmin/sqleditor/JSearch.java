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
 * JSearch.java
 *
 * */
package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;

public class JSearch extends JDialog {
    private JLabel lbBuscar = new JLabel();
    private JComboBox cbSearch = new JComboBox(); 
    private JComboBox cbReplace = new JComboBox(); 
    private JCheckBox chReplace = new JCheckBox();
    private JPanel pOpciones = new JPanel();
    private JRadioButton rbCoinMay = new JRadioButton();
    private JRadioButton rbSinDist = new JRadioButton();
    private JCheckBox chBuscarPrinc = new JCheckBox();
    private JPanel pReemplazo = new JPanel();
    private JTextComponent editor;
    private JRadioButton rbUnaVez = new JRadioButton();
    private JRadioButton rbPreguntar = new JRadioButton();
    private JRadioButton rbReempTodas = new JRadioButton();
    private JButton bAceptar = new JButton();
    private JButton bCancel = new JButton();
    private ButtonGroup grupoOpciones = new ButtonGroup();
    private ButtonGroup grupoReemplazo = new ButtonGroup();
    private int opcion = 0;//0:Sin distinciones
                           //1:Coincidir Mayusculas
    private int principio = 0;//0:No Buscar desde el comienzo
                              //1:Buscar desde el comienzo
    private int opcionReempl = 0;//0:Una vez
                                 //1:Preguntar
                                 //2:Todas
    private int reemplazar = 0;//0:No Reemplazar
                               //1:Reemplazar
    private String ultimaPista = "";
    private Vector backSearch = new Vector();
    private Vector backReplace = new Vector();
    
    public JSearch(JTextComponent _editor) {
        try {
            editor = _editor;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JSearch() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setModal(true);
        this.setVisible(false);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(375, 253));
        this.setTitle("Buscar Texto");
        lbBuscar.setText("Buscar:");
        lbBuscar.setBounds(new Rectangle(5, 10, 90, 15));
        cbSearch.setBounds(new Rectangle(120, 5, 235, 20));
        cbReplace.setBounds(new Rectangle(120, 35, 235, 20));
        chReplace.setText("Reemplazar por:");
        chReplace.setBounds(new Rectangle(5, 35, 110, 20));
        pOpciones.setBounds(new Rectangle(5, 70, 170, 110));
        pOpciones.setLayout(null);
        pOpciones.setBorder(BorderFactory.createTitledBorder("Busqueda"));
        rbCoinMay.setText("Coincidir Mayúsculas");
        rbCoinMay.setBounds(new Rectangle(10, 50, 145, 20));
        rbCoinMay.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            rbCoinMay_actionPerformed(e);
                                        }
                                    }
        );
        rbSinDist.setText("Sin distinciones");
        rbSinDist.setBounds(new Rectangle(10, 20, 145, 20));
        chBuscarPrinc.setText("Desde el principio");
        chBuscarPrinc.setBounds(new Rectangle(10, 80, 145, 20));
        chBuscarPrinc.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                chBuscarPrinc_actionPerformed(e);
                                            }
                                        }
        );
        pReemplazo.setBounds(new Rectangle(185, 70, 170, 110));
        pReemplazo.setBorder(BorderFactory.createTitledBorder("Reemplazo"));
        pReemplazo.setLayout(null);
        rbUnaVez.setText("Una vez");
        rbUnaVez.setBounds(new Rectangle(15, 20, 145, 20));
        rbPreguntar.setText("Preguntar");
        rbPreguntar.setBounds(new Rectangle(15, 50, 145, 20));
        rbPreguntar.addActionListener(new ActionListener() {
                                          public void actionPerformed(ActionEvent e) {
                                              rbPreguntar_actionPerformed(e);
                                          }
                                      }
        );
        rbReempTodas.setText("Reemplazar Todas ");
        rbReempTodas.setBounds(new Rectangle(15, 80, 145, 20));
        rbReempTodas.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent e) {
                                               rbReempTodas_actionPerformed(e);
                                           }
                                       }
        );
        bAceptar.setText("Aceptar");
        bAceptar.setBounds(new Rectangle(155, 195, 95, 23));
        bAceptar.setMnemonic('A');
        bAceptar.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           bAceptar_actionPerformed(e);
                                       }
                                   }
        );
        bCancel.setText("Cancelar");
        bCancel.setBounds(new Rectangle(260, 195, 95, 23));
        bCancel.setMnemonic('C');
        bCancel.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent e) {
                                          bCancel_actionPerformed(e);
                                      }
                                  }
        );
        pOpciones.add(chBuscarPrinc, null);
        pOpciones.add(rbSinDist, null);
        pOpciones.add(rbCoinMay, null);
        pReemplazo.add(rbReempTodas, null);
        pReemplazo.add(rbPreguntar, null);
        pReemplazo.add(rbUnaVez, null);
        grupoOpciones.add(rbCoinMay);
        grupoOpciones.add(rbSinDist);
        grupoReemplazo.add(rbUnaVez);
        grupoReemplazo.add(rbPreguntar);
        grupoReemplazo.add(rbReempTodas);
        cbSearch.setEditable(true);
	cbSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                                   public void keyPressed(KeyEvent e) {
                                       cb_keyPressed(e);
                                   }
                               }
        );
        cbReplace.setEditable(true);
        chReplace.setSelected(false);
        chReplace.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            chReplace_actionPerformed(e);
                                        }
                                    }
        );
	cbReplace.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
				   public void keyPressed(KeyEvent e) {
				       cb_keyPressed(e);
				   }
			       }
	);
        editor.addKeyListener(new KeyAdapter() {
                                   public void keyPressed(KeyEvent e) {
                                       editor_keyPressed(e);
                                   }

                                   public void keyTyped(KeyEvent e) {
                                       editor_keyTyped(e);
                                   }
                               }
        );
        cbReplace.setEnabled(false);
        rbSinDist.setSelected(true);
        rbSinDist.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            rbSinDist_actionPerformed(e);
                                        }
                                    }
        );
        rbUnaVez.setSelected(true);
        rbUnaVez.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           rbUnaVez_actionPerformed(e);
                                       }
                                   }
        );
        rbUnaVez.setEnabled(false);
        rbPreguntar.setEnabled(false);
        rbReempTodas.setEnabled(false);
        this.getContentPane().add(bCancel, null);
        this.getContentPane().add(bAceptar, null);
        this.getContentPane().add(pReemplazo, null);
        this.getContentPane().add(pOpciones, null);
        this.getContentPane().add(chReplace, null);
        this.getContentPane().add(cbReplace, null);
        this.getContentPane().add(cbSearch, null);
        this.getContentPane().add(lbBuscar, null);
        centerScreen(this);
    }
    
    public void centerScreen(JDialog _dialog) {
        Dimension dim = _dialog.getToolkit().getScreenSize();
        Rectangle abounds = _dialog.getBounds();
        _dialog.setLocation((dim.width - abounds.width) / 2,
            (dim.height - abounds.height) / 2);
        //super.setVisible(true);
        _dialog.requestFocus();
    }
    
    private void bCancel_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    private void chReplace_actionPerformed(ActionEvent e) {
        if(chReplace.isSelected()){
            cbReplace.setEnabled(true);
            rbUnaVez.setEnabled(true);
            rbPreguntar.setEnabled(true);
            rbReempTodas.setEnabled(true);
            reemplazar = 1;
        }else{
            rbUnaVez.setEnabled(false);
            rbPreguntar.setEnabled(false);
            rbReempTodas.setEnabled(false);
            cbReplace.setEnabled(false);
            reemplazar = 0;
        }
    }
    void editor_keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F2){
        }
    }
    private void editor_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F2){
            this.setVisible(true);
	    cbSearch.requestFocus();
	    cbSearch.getEditor().selectAll();
        }else{
            if(e.getKeyCode() == KeyEvent.VK_F3){
                buscarSiguiente();
            }   
        }
    }
    
    private void cb_keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_ENTER){
	    ((Component)e.getSource()).hasFocus();
	    busqueda();
	}
    }

    private void rbSinDist_actionPerformed(ActionEvent e) {
        if(rbSinDist.isSelected()){
            opcion = 0;
        }else{
            opcion = 1;
        }
    }

    private void rbCoinMay_actionPerformed(ActionEvent e) {
        if(rbCoinMay.isSelected()){
            opcion = 1;
        }else{
            opcion = 0;
        }
    }

    private void chBuscarPrinc_actionPerformed(ActionEvent e) {
        if(chBuscarPrinc.isSelected()){
            principio = 1;
        }else{
            principio = 0;
        }
    }

    private void rbUnaVez_actionPerformed(ActionEvent e) {
        if(rbUnaVez.isSelected()){
            opcionReempl = 0;
        }
    }

    private void rbPreguntar_actionPerformed(ActionEvent e) {
        if(rbPreguntar.isSelected()){
            opcionReempl = 1;
        }
    }

    private void rbReempTodas_actionPerformed(ActionEvent e) {
        if(rbReempTodas.isSelected()){
            opcionReempl = 2;
        }
    }

    private void bAceptar_actionPerformed(ActionEvent e) {
        busqueda();
    }
    private void busqueda(){
        ultimaPista = cbSearch.getSelectedItem().toString();
        if(!existe(ultimaPista,cbSearch)){
            cbSearch.addItem(ultimaPista);
            cbSearch.setSelectedItem(ultimaPista);
        }
        if(reemplazar == 1){
            if(!existe(cbReplace.getSelectedItem().toString(),cbReplace)){
                cbReplace.addItem(cbReplace.getSelectedItem().toString());
                cbReplace.setSelectedItem(cbReplace.getSelectedItem().toString());
            }
        }
        buscar();
    }
    private boolean existe(String _elemento,JComboBox _combo){
        boolean encontrado = false;
        int i = 0;
        while((!encontrado)&&(i < _combo.getItemCount())){
            if(_elemento.equals(_combo.getItemAt(i).toString())){
                encontrado = true;
            }
            i++;
        }
        return(encontrado);
    }
    private void buscar(){
        int posicion = 0;
        Vector opciones = new Vector();
        String pista = ultimaPista;
        String texto = editor.getText();
        texto = texto.replaceAll("\n"," ");
        texto = texto.replaceAll("\t"," ");
        opciones = setOpciones(texto,pista,posicion);
        texto = opciones.elementAt(0).toString();
        pista = opciones.elementAt(1).toString();
        posicion = Integer.parseInt(opciones.elementAt(2).toString());
        if(reemplazar == 0){
            posicion = texto.indexOf(pista, posicion);
            if(posicion == -1){
                JOptionPane.showMessageDialog(this,"\tNo se encontró la palabra "+ultimaPista,"Sin Coincidencias",JOptionPane.INFORMATION_MESSAGE);
            }else{
                this.setVisible(false);
                editor.requestFocus();
                editor.select(posicion,posicion+pista.length());
            }
        }else{
            if(!cbSearch.getSelectedItem().toString().equals("")){
                reemplazar();
            }else{
                JOptionPane.showMessageDialog(this,"\tNo indicó una pista de búsqueda "+ultimaPista,"Sin Pista",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void reemplazar(){
        Vector opciones = new Vector();
        int posicion = 0;
        String pista = ultimaPista;
        String texto = editor.getText();
        String reemplaza = cbReplace.getSelectedItem().toString();
        opciones = setOpciones(texto,pista,posicion);
        texto = opciones.elementAt(0).toString();
        pista = opciones.elementAt(1).toString();
        posicion = Integer.parseInt(opciones.elementAt(2).toString());
        int exito = 0;//0: no encontro
                      //1: se encontro  
        switch(opcionReempl){
                case 0: exito = reemplazarUna(texto,pista,reemplaza,posicion);
                break;
                case 1: exito = reemplazarQuestion(texto,pista,reemplaza);
                break;
                case 2: exito = reemplazarTodas(texto,pista,reemplaza);
                break;
        }
        if(exito == 0){
            JOptionPane.showMessageDialog(this,"\tNo se encontró la palabra "+ultimaPista,"Sin Coincidencias",JOptionPane.INFORMATION_MESSAGE);
        }else{
            this.setVisible(false);
        }
    }
    private int reemplazarUna(String _texto,String _pista,String _reemplaza,int _posicion){
                int resultado = 0;
                _posicion = _texto.indexOf(_pista,_posicion);
                if(_posicion != -1){
                    editor.setText(editor.getText().substring(0,_posicion)+_reemplaza+editor.getText().substring(_posicion+_pista.length(),editor.getText().length()));
                    editor.requestFocus();
                    editor.select(_posicion,_posicion+_reemplaza.length());
                    resultado = 1;
                }else{
                    resultado = 0;
                }
                return(resultado);
    }
    private int reemplazarQuestion(String _texto,String _pista,String _reemplaza){
        int resultado = 0;
        int posicion = 0;
        int question = 0;
        Vector opciones = new Vector();
        posicion = _texto.indexOf(_pista,posicion);
        while(posicion != -1){
            resultado = 1;
            this.setVisible(false);
            editor.requestFocus();
            editor.select(posicion,posicion+_pista.length());
            question = JOptionPane.showOptionDialog(this,"Reemplazar?","Reemplazo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if(question == 0){
                reemplazarUna(_texto,_pista,_reemplaza,posicion);    
            }
            opciones = setOpciones(_texto,_pista);
            _texto = opciones.elementAt(0).toString();
            posicion = _texto.indexOf(_pista,posicion+_pista.length());
        }
        return(resultado);
    }
    private int reemplazarTodas(String _texto,String _pista,String _reemplaza){
        int resultado = 0;
        int posicion = 0;
        Vector opciones = new Vector();
        posicion = _texto.indexOf(_pista,posicion);
        while(posicion != -1){
            resultado = 1;
            reemplazarUna(_texto,_pista,_reemplaza,posicion);
            opciones = setOpciones(_texto,_pista);
            _texto = opciones.elementAt(0).toString();
            posicion = _texto.indexOf(_pista,posicion+_pista.length());
        }
        return(resultado);
    }
    private void buscarSiguiente(){
        int posicion = editor.getCaretPosition();
        Vector opciones = new Vector();
        String pista = ultimaPista;
        String texto = editor.getText();
        texto = texto.replaceAll("\n"," ");
        texto = texto.replaceAll("\t"," ");
        opciones = setOpciones(texto,pista);
        texto = opciones.elementAt(0).toString();
        pista = opciones.elementAt(1).toString();
        posicion = texto.indexOf(pista,posicion);
        if(posicion == -1){
		JOptionPane.showMessageDialog(this,"\tNo se encontró la palabra "+ultimaPista,"Sin Coincidencias",JOptionPane.INFORMATION_MESSAGE);

        }else{
            this.setVisible(false);
            editor.requestFocus();
            editor.select(posicion,posicion+pista.length());
        }
    }
    private Vector setOpciones(String _texto,String _pista, int _posicion){
        Vector resultado = new Vector();
        if(principio == 1){
            _posicion = 0;
        }else{
            _posicion = editor.getCaretPosition();
        }
        if(opcion == 0){
            _pista = _pista.toUpperCase();
            _texto = editor.getText().toUpperCase();
        }else{
            _texto = editor.getText();
        }
        resultado.add(_texto);
        resultado.add(_pista);
        resultado.add(""+_posicion);
        return(resultado);
    }
    private Vector setOpciones(String _texto,String _pista){
        Vector resultado = new Vector();
        if(opcion == 0){
            _pista = _pista.toUpperCase();
            _texto = editor.getText().toUpperCase();
        }else{
            _texto = editor.getText();
        }
        resultado.add(_texto);
        resultado.add(_pista);
        return(resultado);
    }

    public void setEditor(JTextComponent editor) {
        this.editor = editor;
    }

    public JTextComponent getEditor() {
        return editor;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }
}
final class JSearch_editor_keyAdapter extends KeyAdapter {
    private JSearch adaptee;

    JSearch_editor_keyAdapter(JSearch adaptee) {
        this.adaptee = adaptee;
    }

    public void keyTyped(KeyEvent e) {
        adaptee.editor_keyTyped(e);
    }
}

