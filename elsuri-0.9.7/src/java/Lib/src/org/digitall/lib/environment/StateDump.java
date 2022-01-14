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
 * StateDump.java
 *
 * */
package org.digitall.lib.environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// $Id: StateDump.java,v 1.2 2000/09/07 19:05:08 mito Exp $
/*

This class does a stack trace on every active thread.  It's included
here just as a useful utility that has been added as a console hotkey.

*/

public class StateDump
{
  synchronized public static void dump() {
    Thread current = Thread.currentThread();
    Thread threads[] = getAllThreads();
    Console.println( "Got "+threads.length+"  threads" );

    Console.println( "Reassigning out and err...." );
    PrintStream oldOut = System.out;
    PrintStream oldErr = System.err;
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    PrintStream pout = new PrintStream( bout );
    System.setOut( pout );
    System.setErr( pout );

    for (int i=0; i<threads.length; ++i) {
      Thread thread = threads[i];
      if (thread != current) {
        thread.suspend();
      }
    }

    for (int i=0; i<threads.length; ++i) {
      Thread thread = threads[i];
      if (thread != current) {
        System.out.println( i+" -- "+thread );
        thread.dumpStack();
      }
    }

    for (int i=0; i<threads.length; ++i) {
      Thread thread = threads[i];
      if (thread != current) {
        thread.resume();
      }
    }

    System.setOut( oldOut );
    System.setErr( oldErr );
    Console.println( "Restored" );

    Console.println( bout.toString() );
  }

  static private ThreadGroup getTopThreadGroup() {
    ThreadGroup tg = Thread.currentThread().getThreadGroup();
    ThreadGroup top=tg;
    while (true) {
      top = tg.getParent();
      if (top == null) {
        top = tg;
        break;
      } else {
        tg = top;
      }
    }
    return top;
  }

  static private Thread[] getAllThreads() {
    ThreadGroup top = getTopThreadGroup();
    int size = 2;
    while (true) {
      Thread threads[] = new Thread[size];
      int n = top.enumerate( threads );
      if (n>=size) {
        size *= 2;
      } else {
        Thread ret[] = new Thread[n];
        System.arraycopy( threads, 0, ret, 0, n );
        return ret;
      }
    }
  }
}
