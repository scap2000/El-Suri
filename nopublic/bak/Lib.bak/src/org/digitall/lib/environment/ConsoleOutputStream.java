package org.digitall.lib.environment;

import java.io.IOException;
import java.io.OutputStream;

// $Id$
/*

This class provides an output stream that redirects to our console.
This is used for hooking stdout and stderr.

*/

public class ConsoleOutputStream extends OutputStream
{
  // we keep a buffer around for creating 1-char strings, to
  // avoid the potential horror of thousads of array allocations
  // per second
  private byte littlebuf[] = new byte[1];

  // Redirect output to the console
  public void write( int b ) throws IOException {
    littlebuf[0] = (byte)b;
    String s = new String( littlebuf, 0, 1 );
    Console.print( s );
  }

  // Redirect output to the console
  public void write( byte b[] ) throws IOException {
    String s = new String( b, 0, b.length );
    Console.print( s );
  }

  // Redirect output to the console
  public void write( byte b[], int off, int len ) throws IOException {
    String s = new String( b, off, len );
    Console.print( s );
  }

  // nothing need be done here
  public void flush() throws IOException {
  }

  // nothing need be done here
  public void close() throws IOException {
  }
}
