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
 * Console.java
 *
 * */
package org.digitall.lib.environment;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.PrintStream;

import javax.swing.JFrame;

public class Console extends JFrame {
    // We only ever create one instance, and we hold it here
    private static Console dw;
    // Text area of the console
    private TextArea ta;
    // If we are hooking stdout and stderr, keep the old ones here
    private static PrintStream out, err;
    // Lock for hooking stdout and stderr
    private static Object hookLock = new Object();
    // Default to a reasonable size

    public Console() {
	this(500, 400);
    }

    public Console(int width, int height) {
	// Lay the GUI out
	ta = new TextArea();
	setLayout(new BorderLayout());
	add("Center", ta);
	setSize(width, height);
	setVisible(true);
	addWindowListener(new WindowAdapter() {

		    public void windowClosing(WindowEvent e) {
			closeit();
		    }

		}); // Install a listener for implementing utility keys
	ta.addKeyListener(new KeyListener() {

		    public void keyPressed(KeyEvent ke) {
			//Console.println( "Got key "+ke.getKeyChar() );
			switch (ke.getKeyChar()) {
			    case 'd':
				StateDump.dump();
				break;
			    case 'c':
				ta.setText("");
				break;
			}
			ke.consume();
		    }

		    public void keyReleased(KeyEvent ke) {

		    }

		    public void keyTyped(KeyEvent ke) {

		    }

		});
    }
    // print methods for each type

    public static void println(int i) {
	println("" + i);
    }
    // print methods for each type

    public static void println(long l) {
	println("" + l);
    }
    // print methods for each type

    public static void println(double d) {
	println("" + d);
    }
    // print methods for string

    public static void println(String s) {
	println((Object)s);
    }
    // this is the print method called by the others

    public static void println(Object o) {
	// Create (and open) a new Console object,
	// if we haven't already
	if (dw == null)
	    dw = new Console();
	// Send the object to it
	dw.showString(o.toString() + "\n");
    }
    // print methods for string

    public static void print(int i) {
	print("" + i);
    }
    // print methods for string

    public static void print(long l) {
	print("" + l);
    }
    // print methods for string

    public static void print(double d) {
	print("" + d);
    }
    // print methods for string

    public static void print(String s) {
	print((Object)s);
    }
    // this is the print method called by the others

    public static void print(Object o) {
	// Create (and open) a new Console object,
	// if we haven't already
	if (dw == null)
	    dw = new Console();
	// Send the object to it
	dw.showString(o.toString());
    }
    // non-static method for actually displaying new text

    private void showString(Object o) {
	ta.append(o.toString());
    }
    // we have to unhook stdout and stderr if we close

    private void closeit() {
	unhookStandards();
	setVisible(false);
    }
    // public close method -- close and remove our single instance
    // of the console

    public void close() {
	if (dw != null) {
	    dw.closeit();
	    dw = null;
	}
    }
    // hook stdout and stderr, and redirect output to the console
    // keep the originals around, of course

    public static void hookStandards() {
	synchronized (hookLock) {
	    if (out != null)
		return;
	    out = System.out;
	    err = System.err;
	    PrintStream dwout = new PrintStream(new ConsoleOutputStream());
	    System.setOut(dwout);
	    System.setErr(dwout);
	}
    }
    // undo the hooking of stdout and stderr

    public static void unhookStandards() {
	synchronized (hookLock) {
	    if (out == null)
		return;
	    if (dw != null) {
		dw.setVisible(true);
	    }
	    System.setOut(out);
	    System.setErr(err);
	    out = null;
	    err = null;
	}
    }

}

/*public class Console extends BasicInternalFrame {

    // We only ever create one instance, and we hold it here
    private static Console dw;
    // Text area of the console  
    private JTextArea ta;
    // If we are hooking stdout and stderr, keep the old ones here
    private static PrintStream out, err;
    // Lock for hooking stdout and stderr
    private static Object hookLock = new Object();
    // Default to a reasonable size
    private int width = -1;
    private int height = -1;
    private BasicScrollPane scrollPane;

    public Console(int _width, int _height) {
	width = _width;
	height = _height;
	try {
	    jbInit();
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }

    private void jbInit() {
	// Lay the GUI out
	setTitle("Debug console");
	this.setBounds(0, 0, width, height);
	ta = new JTextArea();
	this.getContentPane().setLayout(new BorderLayout());
	scrollPane = new BasicScrollPane(ta);
	this.getContentPane().add("Center", scrollPane);
	//Environment.mainDesktop.add(this);
	setIconifiable(true);
	setResizable(true);
	setMaximizable(true);
	setClosable(false);
	setVisible(true);
	// Install a listener for implementing utility keys
	ta.setEditable(false);
	ta.setBackground(Color.WHITE);
	ta.addKeyListener(new KeyListener() {

		       public void keyPressed(KeyEvent ke) {
			   //Console.println( "Got key "+ke.getKeyChar() );
			   switch (ke.getKeyChar()) {
			       case 'd' :
				   StateDump.dump();
				   break;
			       case 'c' :
			           ta.setText("");
			           break;
			       case 'X' :
			           System.exit(-1);
			           break;
			   }
			   ke.consume();
		       }

		       public void keyReleased(KeyEvent ke) {

		       }

		       public void keyTyped(KeyEvent ke) {

		       }

		   }
	);
    }
    // print methods for each type

    public static void println(int i) {
	println("" + i);
    }
    // print methods for each type

    public static void println(long l) {
	println("" + l);
    }
    // print methods for each type

    public static void println(double d) {
	println("" + d);
    }
    // print methods for string

    public static void println(String s) {
	println((Object)s);
    }
    // this is the print method called by the others

    public static void println(Object o) {
	// Create (and open) a new Console object,
	// if we haven't already
	if (dw == null)
	    dw = new Console(500, 400);
	// Send the object to it
	dw.showString(o.toString() + "\n");
    }
    // print methods for string

    public static void print(int i) {
	print("" + i);
    }
    // print methods for string

    public static void print(long l) {
	print("" + l);
    }
    // print methods for string

    public static void print(double d) {
	print("" + d);
    }
    // print methods for string

    public static void print(String s) {
	print((Object)s);
    }
    // this is the print method called by the others

    public static void print(Object o) {
	// Create (and open) a new Console object,
	// if we haven't already
	if (dw == null)
	    dw = new Console(500, 400);
	// Send the object to it
	dw.showString(o.toString());
    }
    // non-static method for actually displaying new text

    private void showString(Object o) {
	ta.append(o.toString());
	//scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	ta.setCaretPosition(ta.getDocument().getLength());
    }
    // we have to unhook stdout and stderr if we close

    private void closeit() {
	unhookStandards();
	setVisible(false);
    }
    // public close method -- close and remove our single instance
    // of the console

    public static void closeConsole() {
	if (dw != null) {
	    dw.closeit();
	    dw = null;
	}
    }
    // hook stdout and stderr, and redirect output to the console
    // keep the originals around, of course

    public static void hookStandards() {
	synchronized (hookLock) {
	    if (out != null)
		return;
	    out = System.out;
	    err = System.err;
	    PrintStream dwout = new PrintStream(new ConsoleOutputStream());
	    System.setOut(dwout);
	    System.setErr(dwout);
	}
    }
    // undo the hooking of stdout and stderr

    public static void unhookStandards() {
	synchronized (hookLock) {
	    if (out == null)
		return;
	    System.setOut(out);
	    System.setErr(err);
	    out = null;
	    err = null;
	}
    }

}
*/