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
 * BasicHeaderRenderer.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class BasicHeaderRenderer extends DefaultTableCellRenderer implements MouseListener, MouseMotionListener {

    private Rectangle paintingRect = null;
    private Rectangle lastPaintingRect = null;
    private JTableHeader header = null;
    private boolean isOnCol = false;
    private boolean highlightClickedCol = false;
    private int paintingCol = -1;
    private int clickedCol = -1;
    private int currentCol = -1;
    /**
     * Buffer gradient paint.
     */
    GradientPaint gp = null, hoverGradient, columnGradient;

    /**
     * The current sorting state of the columns
     */
    public BasicHeaderRenderer() {
	super();
	setFont(getFont().deriveFont(Font.BOLD));
	setOpaque(false);
	//setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 230)), BorderFactory.createEmptyBorder(4, 7, 4, 4)));
	setBorder(BorderFactory.createLineBorder(new Color(200, 200, 230)));
	setHighlightClickedColumn(true);
	//SortOrder sortOrder = null;
	setToolTipText("Click para ordenar");
	setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    }

    public void setColumnGradient(GradientPaint gp) {
	this.columnGradient = gp;
    }

    public void setHoverGradient(GradientPaint gp) {
	this.hoverGradient = gp;
    }

    public GradientPaint getColumnGradient() {
	return columnGradient;
    }

    public GradientPaint getHoverGradient() {
	return hoverGradient;
    }

    public void setHighlightClickedColumn(boolean b) {
	highlightClickedCol = b;
    }

    public void paintComponent(Graphics g) {
	Rectangle rect = paintingRect;
	Graphics2D g2 = (Graphics2D)g;
	g2.setPaint(gp);
	g2.fillRect(0, 0, rect.width, rect.height);
	//FontMetrics fm = g.getFontMetrics();
	//int strWidth = fm.stringWidth(getText());
	if (currentCol == clickedCol) {
	    /*if( sortOrder == SortOrder.ASCENDING )
                new ArrowIcon( ArrowIcon.UP ).paintIcon(this, g, strWidth + 15, 8);
            else if(sortOrder == SortOrder.DESCENDING )
                new ArrowIcon( ArrowIcon.DOWN ).paintIcon(this, g, strWidth + 15, 8);*/
	}
	//sortOrder = null;
	super.paintComponent(g);
    }

    public void attachListener() {
	header.addMouseListener(this);
	header.addMouseMotionListener(this);
    }

    public void mouseEntered(MouseEvent e) {
	isOnCol = true;
    }

    public void mouseExited(MouseEvent e) {
	isOnCol = false;
	paintingCol = -1;
	header.repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
	clickedCol = header.columnAtPoint(e.getPoint());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
	//isOnRow = true;
	paintingCol = header.columnAtPoint(e.getPoint());
	paintingRect = header.getHeaderRect(paintingCol);
	header.repaint(paintingRect.x, paintingRect.y, paintingRect.width, paintingRect.height);
	if (lastPaintingRect != null) {
	    header.repaint(lastPaintingRect.x, lastPaintingRect.y, lastPaintingRect.width, lastPaintingRect.height);
	}
	lastPaintingRect = paintingRect;
    }

    public void mouseDragged(MouseEvent e) {
	//isOnRow = true;
	paintingCol = header.columnAtPoint(e.getPoint());
	paintingRect = header.getHeaderRect(paintingCol);
	header.repaint(paintingRect.x, paintingRect.y, paintingRect.width, paintingRect.height);
	if (lastPaintingRect != null) {
	    header.repaint(lastPaintingRect.x, lastPaintingRect.y, lastPaintingRect.width, lastPaintingRect.height);
	}
	lastPaintingRect = paintingRect;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
	currentCol = col;
	if (header == null) {
	    header = table.getTableHeader();
	    attachListener();
	}
	/*if(table.getRowSorter() != null && table.getRowSorter().getSortKeys().size() > 0 ) {
            java.util.List<? extends RowSorter.SortKey> keys = table.getRowSorter().getSortKeys();
            for(RowSorter.SortKey key: keys) {
                if(key.getColumn() == col) {
                    sortOrder = key.getSortOrder();
                }
            }
 
        }*/
	Rectangle rect = table.getTableHeader().getHeaderRect(col);
	if ((isOnCol && paintingCol == col) || (clickedCol == col && highlightClickedCol)) {
	    gp = new GradientPaint(rect.x, rect.y + rect.height, BasicConfig.TABLE_HEADER_SELECTED_GRADIENT_START_COLOR, rect.x, rect.y, BasicConfig.TABLE_HEADER_SELECTED_GRADIENT_END_COLOR);
	    setForeground(BasicConfig.TABLE_HEADER_SELECTED_FOREGROUND_COLOR);
	} else {
	    gp = new GradientPaint(rect.x, rect.y + rect.height, BasicConfig.TABLE_HEADER_NOT_SELECTED_GRADIENT_START_COLOR, rect.x, rect.y, BasicConfig.TABLE_HEADER_NOT_SELECTED_GRADIENT_END_COLOR);
	    setForeground(BasicConfig.TABLE_HEADER_NOT_SELECTED_FOREGROUND_COLOR);
	}
	paintingRect = rect;
	setText(value == null ? "" : value.toString());
	//setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), brdr));
	return this;
    }

}
