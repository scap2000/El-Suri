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
 * PreviewExtendedInternalFrame.java
 *
 * */
/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2001 - 2009 Object Refinery Ltd, Pentaho Corporation and Contributors..  All rights reserved.
 */

package org.jfree.report.modules.gui.base;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.icons.IconTypes;

import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewPane;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.ReportController;
import org.pentaho.reporting.engine.classic.core.modules.gui.common.IconTheme;
import org.pentaho.reporting.engine.classic.core.modules.gui.commonswing.JStatusBar;
import org.pentaho.reporting.engine.classic.core.modules.gui.commonswing.ReportProgressBar;
import org.pentaho.reporting.engine.classic.core.modules.gui.commonswing.RequestFocusHandler;

/**
 * Creation-Date: 11.11.2006, 19:35:09
 *
 * @author Thomas Morgner
 */
public class PreviewExtendedInternalFrame extends ExtendedInternalFrame {

  private class PreviewPanePropertyChangeHandler implements PropertyChangeListener
  {

    protected PreviewPanePropertyChangeHandler()
    {
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
     */

    public void propertyChange(final PropertyChangeEvent evt)
    {
      final String propertyName = evt.getPropertyName();
      if (PreviewPane.MENU_PROPERTY.equals(propertyName))
      {
        // Update the menu
        final JMenu[] menus = previewPane.getMenu();
        if (menus != null && menus.length > 0)
        {
          final JMenuBar menuBar = new JMenuBar();
          for (int i = 0; i < menus.length; i++)
          {
            final JMenu menu = menus[i];
	      getNorthPane().addMenu(menu);
            menuBar.add(menu);
          }
          setJMenuBar(menuBar);
        }
        else
        {
          setJMenuBar(null);
        }
        return;
      }

      if (PreviewPane.TITLE_PROPERTY.equals(propertyName))
      {
        setTitle(previewPane.getTitle());
        return;
      }

      if (PreviewPane.STATUS_TEXT_PROPERTY.equals(propertyName)
          || PreviewPane.STATUS_TYPE_PROPERTY.equals(propertyName))
      {
        statusBar.setStatus(previewPane.getStatusType(), previewPane.getStatusText());
        return;
      }

      if (PreviewPane.ICON_THEME_PROPERTY.equals(propertyName))
      {
        statusBar.setIconTheme(previewPane.getIconTheme());
        return;
      }

      if (PreviewPane.PAGINATING_PROPERTY.equals(propertyName))
      {
        if (Boolean.TRUE.equals(evt.getNewValue()))
        {
          progressBar.setOnlyPagination(true);
          progressBar.setVisible(true);
          pageLabel.setVisible(false);
        }
        else
        {
          progressBar.setOnlyPagination(true);
          progressBar.setVisible(false);
          pageLabel.setVisible(true);
        }
        progressBar.revalidate();
        return;
      }

      if (PreviewPane.PAGE_NUMBER_PROPERTY.equals(propertyName)
          || PreviewPane.NUMBER_OF_PAGES_PROPERTY.equals(propertyName))
      {
        pageLabel.setText(previewPane.getPageNumber() + "/" + previewPane.getNumberOfPages()); //$NON-NLS-1$
        return;
      }

      if (PreviewPane.CLOSED_PROPERTY.equals(propertyName))
      {
        if (previewPane.isClosed())
        {
          try
          {
            setClosed(true);
          }
          catch (PropertyVetoException e)
          {
            // Ignored ..
          }
          dispose();
        }
        else
        {
          setVisible(true);
        }
      }
    }
  }

  private static class TriggerPaginationListener extends ComponentAdapter
  {
    private PreviewPane pane;

    private TriggerPaginationListener(final PreviewPane pane)
    {
      this.pane = pane;
    }

    /**
     * Invoked when the component has been made visible.
     */
    public void componentShown(final ComponentEvent e)
    {
      if (pane.isDeferredRepagination())
      {
        pane.startPagination();
      }
    }
  }

  private PreviewPane previewPane;
  private JStatusBar statusBar;
  private ReportProgressBar progressBar;
  private BasicLabel pageLabel;

  /**
   * Creates a non-modal dialog without a title and without a specified <code>Frame</code> owner.  A shared, hidden
   * frame will be set as the owner of the dialog.
   * <p/>
   * This constructor sets the component's locale property to the value returned by
   * <code>JComponent.getDefaultLocale</code>.
   *
   * @throws java.awt.HeadlessException if GraphicsEnvironment.isHeadless() returns true.
   * @see java.awt.GraphicsEnvironment#isHeadless
   * @see javax.swing.JComponent#getDefaultLocale
   */
  public PreviewExtendedInternalFrame()
  {
    super("Reporte", IconTypes.reportIcon_22x22);
    init();
  }

  /**
   * Constructs a new frame that is initially invisible.
   * <p/>
   * This constructor sets the component's locale property to the value returned by
   * <code>JComponent.getDefaultLocale</code>.
   *
   * @see java.awt.GraphicsEnvironment#isHeadless
   * @see java.awt.Component#setSize
   * @see java.awt.Component#setVisible
   * @see javax.swing.JComponent#getDefaultLocale
   */
  public PreviewExtendedInternalFrame(final MasterReport report)
  {
	super(report.getName(), IconTypes.reportIcon_32x32);
	setInfo(report.getName());
	init();
	setReportJob(report);
  }

  protected void init()
  {
    addComponentListener(new RequestFocusHandler());

    previewPane = new PreviewPane();
    previewPane.setDeferredRepagination(true);
    addComponentListener(new TriggerPaginationListener(previewPane));
    statusBar = new JStatusBar(previewPane.getIconTheme());

    progressBar = new ReportProgressBar();
    progressBar.setVisible(false);
    previewPane.addReportProgressListener(progressBar);

    pageLabel = new BasicLabel();
    previewPane.addPropertyChangeListener(new PreviewExtendedInternalFrame.PreviewPanePropertyChangeHandler());

    final JComponent extensionArea = statusBar.getExtensionArea();
    extensionArea.setLayout(new BoxLayout(extensionArea, BoxLayout.X_AXIS));
    extensionArea.add(progressBar);
    extensionArea.add(pageLabel);

    final BasicPanel contentPane = new BasicPanel();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(previewPane, BorderLayout.CENTER);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    setCentralPanel(contentPane);

    updateMenu(previewPane.getMenu());
    setTitle(previewPane.getTitle());
    statusBar.setIconTheme(previewPane.getIconTheme());
    statusBar.setStatus(previewPane.getStatusType(), previewPane.getStatusText());
    setMaximizable(true);
  }

  private void updateMenu(final JMenu[] menus)
  {
    if (menus != null && menus.length > 0)
    {
      final JMenuBar menuBar = new JMenuBar();
      for (int i = 0; i < menus.length; i++)
      {
        final JMenu menu = menus[i];
        menuBar.add(menu);
      }
      setJMenuBar(menuBar);
    }
    else
    {
      setJMenuBar(null);
    }
  }

  public ReportController getReportController()
  {
    return previewPane.getReportController();
  }

  public void setReportController(final ReportController reportController)
  {
    previewPane.setReportController(reportController);
  }

  public IconTheme getIconTheme()
  {
    return previewPane.getIconTheme();
  }

  /*public void setIconTheme(final IconTheme theme)
  {
    previewPane.setIconTheme(theme);
  }*/

  public MasterReport getReportJob()
  {
    return previewPane.getReportJob();
  }

  public void setReportJob(final MasterReport reportJob)
  {
    previewPane.setReportJob(reportJob);
  }

  public void dispose()
  {
    super.dispose();
    previewPane.setClosed(true);
  }

  public PreviewPane getPreviewPane()
  {
    return previewPane;
  }

  public boolean isToolbarFloatable()
  {
    return previewPane.isToolbarFloatable();
  }

  public void setToolbarFloatable(final boolean toolbarFloatable)
  {
    previewPane.setToolbarFloatable(toolbarFloatable);
  }

  public double getZoom()
  {
    return previewPane.getZoom();
  }

  public void setZoom(final double zoom)
  {
    previewPane.setZoom(zoom);
  }
}


/*
 * 
 * 
 */