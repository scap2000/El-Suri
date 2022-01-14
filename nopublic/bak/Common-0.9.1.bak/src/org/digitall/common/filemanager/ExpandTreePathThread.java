package org.digitall.common.filemanager;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class ExpandTreePathThread extends Thread {

    private JTree tree;
    private TreePath path;
    private long interval = 2000;
    private long start = 0;

    public ExpandTreePathThread(JTree _tree) {
	tree = _tree;
    }

    public void run() {
	while ((System.currentTimeMillis() - start) < interval) {
	}
	tree.expandPath(path);
    }

    public void setPath(TreePath _path) {
	start = System.currentTimeMillis();
	path = _path;
    }

}
