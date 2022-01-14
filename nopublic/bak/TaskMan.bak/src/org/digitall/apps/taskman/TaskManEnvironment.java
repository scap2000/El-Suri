package org.digitall.apps.taskman;

import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.sql.LibSQLMini;

public abstract class TaskManEnvironment {

    private static String sqlPass = "dGFza21hbmFnZXI=";
    public static final LibSQLMini libSQLMini = new LibSQLMini("digitallsh.com.ar", "logsdatabase", "taskmanager", Base64Coder.decode(sqlPass));
	
}
