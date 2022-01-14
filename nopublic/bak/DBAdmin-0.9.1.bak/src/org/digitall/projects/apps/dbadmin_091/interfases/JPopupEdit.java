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
