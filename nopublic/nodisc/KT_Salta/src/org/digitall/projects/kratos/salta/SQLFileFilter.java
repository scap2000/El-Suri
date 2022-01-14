package org.digitall.projects.kratos.salta;


import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SQLFileFilter extends FileFilter {

    public SQLFileFilter() {
    }

    public boolean accept(File f) {
	return f.getName().toLowerCase().endsWith("sql") || f.isDirectory();
    }

    public String getDescription() {
	return "SQL file";
    }

}
