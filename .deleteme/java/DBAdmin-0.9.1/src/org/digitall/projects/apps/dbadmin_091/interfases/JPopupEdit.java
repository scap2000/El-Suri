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
 * JPopupEdit.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class JPopupEdit extends PopupMenu{
    private JTextComponent editor;
    private MenuItem menuCopiar,menuPegar,menuCortar,menuEliminar,menuSelAll;
    private OyenteSelTodo oyenteSelTodo = new OyenteSelTodo();
    private OyenteCopiar oyenteCopiar = new OyenteCopiar();
    private OyentePegar oyentePegar = new OyentePegar();
    private OyenteEliminar oyenteEliminar = new OyenteEliminar();
    private OyenteCortar oyenteCortar = new OyenteCortar();
    
    public JPopupEdit(JTextComponent _editor) {
        editor = _editor;
        init();
    }
    public JPopupEdit() {
    }
    
    private void init(){
        menuCopiar = new MenuItem("Copiar");
        menuPegar = new MenuItem("Pegar");
        menuCortar = new MenuItem("Cortar");
        menuSelAll = new MenuItem("Seleccionar todo");
        menuEliminar = new MenuItem("Eliminar");
        menuCopiar.addActionListener(oyenteCopiar);
        menuPegar.addActionListener(oyentePegar);
        menuSelAll.addActionListener(oyenteSelTodo);
        menuEliminar.addActionListener(oyenteEliminar);
        menuCortar.addActionListener(oyenteCortar);
        this.add(menuCopiar);
        this.addSeparator();
        this.add(menuPegar);
        this.addSeparator();
        this.add(menuCortar);
        this.addSeparator();
        this.add(menuSelAll);
        this.addSeparator();
        this.add(menuEliminar);
        editor.addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent e) {
                                        editor_mouseCliked(e);
                                    }
                                }
        );
    }
    private void editor_mouseCliked(MouseEvent e) {
        editor.setCaretColor(Color.BLACK);
        if(e.getButton() == 3){
            if(editor.getSelectedText() == null){
                menuCortar.setEnabled(false);
                menuCopiar.setEnabled(false);
                menuEliminar.setEnabled(false);
            }else{
                menuCortar.setEnabled(true);
                menuCopiar.setEnabled(true);
                menuEliminar.setEnabled(true);
            }
            if(!editor.isEditable()){
                menuCortar.setEnabled(false);
                menuEliminar.setEnabled(false);
            }
            this.show(editor,e.getX(),e.getY());
        }
    }

    public void setEditor(JTextComponent editor) {
        this.editor = editor;
        init();
    }

    public JTextComponent getEditor() {
        return editor;
    }

    class OyenteSelTodo implements ActionListener{
                     public void actionPerformed(ActionEvent e){
                             editor.requestFocus();
                             editor.select(0,editor.getText().length());
                     }
             }
             class OyenteCopiar implements ActionListener{
                     public void actionPerformed(ActionEvent e){
                                 editor.copy();
                     }
             }
             class OyenteCortar implements ActionListener{
                     public void actionPerformed(ActionEvent e){
                         if(editor.isDisplayable()){
                             editor.cut();
                         }
                  }
             }
             class OyentePegar implements ActionListener{
                     public void actionPerformed(ActionEvent e){
                         //if(!editor.getSelectedText().equals("")){
                             editor.paste();
                         //}
                     }
             }
             class OyenteEliminar implements ActionListener{
                     public void actionPerformed(ActionEvent e){
                         if(editor.isDisplayable()){
                             editor.replaceSelection("");
                         }  
                     }
             }
}
