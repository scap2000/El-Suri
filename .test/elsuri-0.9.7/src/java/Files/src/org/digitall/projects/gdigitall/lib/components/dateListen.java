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
 * dateListen.java
 *
 * */
//Validador de Fechas
package org.digitall.projects.gdigitall.lib.components;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class dateListen implements KeyListener
{
  private Set habil = new HashSet();
  private int tecla=0;
  private String separador="/";
  private int cant_caracter=0;
  private boolean fecha;
  
  public dateListen(boolean Fecha)
  {
      OP_Proced.IniciaTeclas();
      fecha=Fecha;
      if (fecha)
      { 
        separador="/"; 
        cant_caracter=8;
      } else 
      { 
        separador=":";
        cant_caracter=5;
      }    
  }
 
  public void keyTyped(KeyEvent key) 
  {
    habil.clear();
    habil.addAll(OP_Proced.getTeclasInt());
    //System.out.println(Proced.teclasInt);
    JTextField c = (JTextField)key.getSource();
//    String info = c.getName();
//    int longcampo = Integer.parseInt(info);
    char ch = key.getKeyChar();
    
    if (c.getText().length()<cant_caracter & habil.contains( "" + tecla))
    {
      if (c.getText().length()==1 | c.getText().length()==4)
      {
        if (c.getText().length()==4 & fecha)
        {
          c.setText(c.getText() + ch + separador);
        } else 
        {
          c.setText(c.getText() + ch);
        }
        key.consume();
      } else if (c.getText().length()==2 | c.getText().length()==5)
      {
          c.setText(c.getText() + separador + ch);
          key.consume();  
           
      } else if (c.getText().length()>=cant_caracter & tecla != 8)
      {
        key.consume();
      }
    } else if (tecla != 8 && tecla != 10)
    {
      //c.setText(""+ ch);      
      key.consume();
    } else 
    {
    }
  }

  public void keyReleased(KeyEvent key) 
  {

  }

  public void keyPressed(KeyEvent key) 
  {
    tecla = key.getKeyCode();
    habil.clear();
    habil.addAll(OP_Proced.getTeclasFun());
    if (habil.contains("" + tecla))
    {
      key.consume();
    } else 
    {
    }
    if (tecla == 10) 
    {
      ((Component)key.getSource()).transferFocus();
    }
  }
}


