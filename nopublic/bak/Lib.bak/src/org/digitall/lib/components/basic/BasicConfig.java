package org.digitall.lib.components.basic;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public abstract class BasicConfig {

    /*TRANSPARENT ColorUIResource
    //int MASK = 0x66ffffff;
    //ColorUIResource(r,g,b).getRGB() & MASK
    */
    public static Color RESALTADOR_COLOR = new Color(229, 124, 10);
    public static ColorUIResource RESALTADOR_COLOR_UIRESOURCE = new ColorUIResource(229, 124, 10);
    public static Color TABLE_HEADER_BACKGROUND_COLOR = Color.WHITE;
    public static Color TABLE_HEADER_SELECTED_FOREGROUND_COLOR = new Color(255, 255, 255);
    public static Color TABLE_HEADER_NOT_SELECTED_FOREGROUND_COLOR = new Color(255, 255, 255);
    public static Color TABLE_HEADER_SELECTED_GRADIENT_START_COLOR = new Color(0, 100, 140);
    public static Color TABLE_HEADER_SELECTED_GRADIENT_END_COLOR = new Color(0, 50, 70);
    public static Color TABLE_HEADER_NOT_SELECTED_GRADIENT_START_COLOR = new Color(0, 50, 70);
    public static Color TABLE_HEADER_NOT_SELECTED_GRADIENT_END_COLOR = new Color(0, 100, 140);
    public static Color TABLE_BASIC_BACKGROUND_COLOR = new Color(254, 254, 254);
    public static Color TABLE_ALTERNATE_BACKGROUND_COLOR = new Color(222, 222, 222);
    public static Color TABLE_SELECTED_BACKGROUND_COLOR = new Color(179, 202, 227);
    public static Color TABLE_SELECTED_FOREGROUND_COLOR = Color.BLACK;
    public static Color TABLE_NOT_SELECTED_FOREGROUND_COLOR = Color.BLACK;
    public static Color LABEL_FOREGROUND_COLOR = Color.WHITE;
    public static Color LABEL_BACKGROUND_COLOR = new Color(0, 59, 101);
    public static Color LABELINFO_FOREGROUND_COLOR = Color.WHITE;
    public static Color LABELINFO_BACKGROUND_COLOR = new Color(220,0,0);
    public static Color TITLELABEL_FOREGROUND_COLOR = Color.WHITE;
    public static Color TITLELABEL_BACKGROUND_COLOR = new Color(0, 59, 101);
    public static Color BUTTON_GRADIENT_START_COLOR = new Color(237, 237, 237);
    public static Color BUTTON_GRADIENT_END_COLOR = new Color(181, 181, 181);
    public static Color INTERNALFRAME_BACKGROUND_COLOR = Color.WHITE;
    public static Color INTERNALFRAME_BORDER_COLOR = new Color(222, 222, 222);
    public static Color PANEL_GRADIENT_START_COLOR = new Color(150, 150, 150);
    public static Color PANEL_GRADIENT_END_COLOR = new Color(60, 60, 60);
    public static Color PANELCONTAINER_BACKGROUND_COLOR = new Color(21, 21, 21);
    public static Color PANEL_BACKGROUND_COLOR = new Color(55, 55, 55);
    public static Color SCROLLPANE_BACKGROUND_COLOR = new Color(100, 100, 100);
    public static Color TEXTFIELD_ENABLED_BORDER_COLOR = new Color(12, 12, 33);
    public static Color TEXTFIELD_ENABLED_BACKGROUND_COLOR = Color.WHITE;
    public static Color TEXTFIELD_ENABLED_FOREGROUND_COLOR = Color.BLACK;
    public static Color TEXTFIELD_DISABLED_BORDER_COLOR = new Color(12, 12, 33);
    public static Color TEXTFIELD_DISABLED_BACKGROUND_COLOR = new Color(222, 222, 222);
    public static Color TEXTFIELD_DISABLED_FOREGROUND_COLOR = Color.BLACK;
    public static Color TEXTFIELD_EDITABLE_BORDER_COLOR = new Color(12, 12, 33);
    public static Color TEXTFIELD_EDITABLE_BACKGROUND_COLOR = Color.WHITE;
    public static Color TEXTFIELD_EDITABLE_FOREGROUND_COLOR = Color.BLACK;
    public static Color TEXTFIELD_UNEDITABLE_BORDER_COLOR = new Color(12, 12, 33);
    public static Color TEXTFIELD_UNEDITABLE_BACKGROUND_COLOR = new Color(170,180,190);
    public static Color TEXTFIELD_UNEDITABLE_FOREGROUND_COLOR = Color.BLACK;
    public static Color DESKTOP_BACKGROUND_COLOR = new Color(12, 12, 33);
    public static Color DESKTOP_GRADIENT_START_COLOR = new Color(16, 27, 58);
    public static Color DESKTOP_GRADIENT_END_COLOR = new Color(35, 35, 35);
    public static Color INTERNALFRAME_NORTH_PANE_GRADIENT_START_COLOR = new Color(0, 131, 215);
    public static Color INTERNALFRAME_NORTH_PANE_GRADIENT_END_COLOR = new Color(0, 75, 109);
    public static Color GENERALBUTTONS_CONTAINER_GRADIENT_START_COLOR = new Color(115, 115, 115);
    public static Color GENERALBUTTONS_CONTAINER_GRADIENT_END_COLOR = new Color(0, 50, 71);
    public static Color GENERALBUTTONS_BORDER_COLOR = new Color(150, 150, 150);
    public static ColorUIResource TABBEDPANE_UNSELECTED_BACKGROUND = new ColorUIResource(229, 124, 10);
    public static ColorUIResource TABBEDPANE_FOREGROUND = new ColorUIResource(255, 255, 255);
    public static ColorUIResource TABBEDPANE_TAB_AREA_BACKGROUND = new ColorUIResource(94,127,141);

}
