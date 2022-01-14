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
