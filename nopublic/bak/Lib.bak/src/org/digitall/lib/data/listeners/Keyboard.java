package org.digitall.lib.data.listeners;

import java.awt.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class Keyboard {

    private static Set keyInit = new HashSet();
    private static Set keyFun = new HashSet();

    public static void keyboardInit() {
	int[] keysFun = new int[] {  KeyEvent.VK_ENTER, KeyEvent.VK_DELETE, KeyEvent.VK_ESCAPE, KeyEvent.VK_SPACE, KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_ALT_GRAPH, 65406, KeyEvent.VK_HOME, KeyEvent.VK_END, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_KP_LEFT, KeyEvent.VK_KP_UP, KeyEvent.VK_KP_RIGHT, KeyEvent.VK_KP_DOWN};
	for (int i = 0; i < keysFun.length; i++) {
	    keyFun.add("" + keysFun[i]);
	}
	int[] keysInt = new int[] { KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_NUMPAD0, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9 };
	for (int i = 0; i < keysInt.length; i++) {
	    keyInit.add("" + keysInt[i]);
	}
    }

    public static Set getKeyInit() {
	return keyInit;
    }

    public static Set getKeyFun() {
	return keyFun;
    }

}
