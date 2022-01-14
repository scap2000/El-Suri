package org.digitall.lib.environment;

public abstract class Debug {

    public static void printStackTrace(Exception x) {
	if (Environment.debugMode) {
	    x.printStackTrace();
	}
    }

    public static void printMessage(Exception x) {
	println(x.getMessage());
    }

    public static void println(boolean _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(char _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(char[] _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(double _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(float _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(int _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(long _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(Object _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println(String _object) {
	if (Environment.debugMode) {
	    System.out.println(_object);
	}
    }

    public static void println() {
	if (Environment.debugMode) {
	    System.out.println();
	}
    }

    public static void printlnError(String _message) {
	if (Environment.debugMode) {
	    System.err.println(_message);
	}
    }

}
