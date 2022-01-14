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
 * BasicConfig.java
 *
 * */
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
