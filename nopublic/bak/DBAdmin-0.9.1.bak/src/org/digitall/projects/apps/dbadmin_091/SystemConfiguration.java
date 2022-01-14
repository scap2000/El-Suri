package org.digitall.projects.apps.dbadmin_091;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public abstract class SystemConfiguration {

    public static final int ZERO_PRIV = -1;
    public static final int ADMIN_PRIV = 0;
    public static final int USER_PRIV = 1;
    public static final int QUERY_PRIV = 2;
    public static final int EXEC_PRIV = 3;
    public static final Vector tablesExclusionList = new Vector();
}
