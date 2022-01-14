package org.digitall.lib.print;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*------------------------------------------------------------------------------
Author:         Byron Hawkins
Date:           2/12/99
Purpose:        A panel that uses the FormLayout by default, and exposes its
				methods directly on the panel.  This panel is a convenience 
				object only, and is simply a compound of JPanel and FormLayout.
------------------------------------------------------------------------------*/

public class FormPanel extends JPanel
{
   // debug setting 
   //	0  - critical errors only
   //	3  - constructors, add methods, and dependents
   //	6  - size methods and dependents
   //	8  - layout methods and dependents
   //	10 - everything but the kitchen sink
   private static final int DEBUG_LEVEL = 0;

   public FormPanel()
   {
      debug("FormPanel.FormPanel()", 3);

      if (DEBUG_LEVEL >= 10)
         {
            setBorder(BorderFactory.createLineBorder(Color.blue));
         }

      super.setLayout(new FormLayout());
   }

   public FormPanel(int internalHGap, int internalVGap, int externalHGap, int externalVGap)
   {
      debug("FormPanel.FormPanel(int, int, int, int): internalHGap " + internalHGap + ", internalVGap " + internalVGap + ", externalHGap " + externalHGap + ", externalVGap " + externalVGap, 3);

      if (DEBUG_LEVEL >= 10)
         {
            setBorder(BorderFactory.createLineBorder(Color.blue));
         }

      super.setLayout(new FormLayout(internalHGap, internalVGap, externalHGap, externalVGap));
   }

   public int getInternalHGap()
   {
      return ((FormLayout)getLayout()).getInternalHGap();
   }

   public int getInternalVGap()
   {
      return ((FormLayout)getLayout()).getInternalVGap();
   }

   public int getExternalHGap()
   {
      return ((FormLayout)getLayout()).getExternalHGap();
   }

   public int getExternalVGap()
   {
      return ((FormLayout)getLayout()).getExternalVGap();
   }

   public void setInternalVGap(int gap)
   {
      ((FormLayout)getLayout()).setInternalVGap(gap);
   }

   public void setInternalHGap(int gap)
   {
      ((FormLayout)getLayout()).setInternalHGap(gap);
   }

   public void setExternalVGap(int gap)
   {
      ((FormLayout)getLayout()).setExternalVGap(gap);
   }

   public void setExternalHGap(int gap)
   {
      ((FormLayout)getLayout()).setExternalHGap(gap);
   }

   public Dimension getPreferredSize()
   {
      debug("FormPanel.getPreferredSize()", 3);

      long currentTime = System.currentTimeMillis();
      Dimension preferredSize = ((FormLayout)getLayout()).preferredLayoutSize(this);
      debug("FormPanel.getPreferredSize(): " + preferredSize, 3);
      debugTimer("FormPanel.getPreferredSize()", currentTime);
      return preferredSize;
   }
  
   // all add() methods add components to the JPanel and the FormLayout

   public void add(Component component, int row, int column)
   {
      debug("FormPanel.add(Component, int, int): add to row " + row + ", column " + column, 3);

      super.add(component);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(component, row, column);
      debugTimer("FormPanel.add(Component, int, int)", currentTime);
   }

   public void add(Component label, Component field, int row, int column)
   {
      debug("FormPanel.add(Component, Component, int, int): add field to row " + row + ", column " + column, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(label, field, row, column);
      debugTimer("FormPanel.add(Component, Component, int, int)", currentTime);
   }
  
   public void add(Component label, Component field, int row, int column, int mode)
   {
      debug("FormPanel.add(Component, Component, int, int, int): add field to row " + row + ", column " + column + ", mode " + mode, 3);
      
      if ((mode < FormLayout.DEFAULT) || (mode > FormLayout.LABEL_ON_TOP))
         {
            debug("FormPanel.add: Invalid mode: " + mode + "!  Using default.", 0);

            add(label, field, row, column);
         }

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(label, field, row, column, mode);
      debugTimer("FormPanel.add(Component, Component, int, int, int)", currentTime);
   }

   // all addMultiRow() methods add components that span multiple rows

   public void addMultiRow(Component component, int startRow, int endRow, int column)
   {
      debug("FormPanel.addMultiRow(Component, int, int, int): add to rows " + startRow + " - " + endRow + ", column " + column, 3);

      super.add(component);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(component, startRow, endRow, column);
      debugTimer("FormPanel.addMultiRow(Component, int, int, int)", currentTime);
   }

   public void addMultiRow(Component label, Component field, int startRow, int endRow, int column)
   {
      debug("FormPanel.addMultiRow(Component, Component, int, int, int): add to rows " + startRow + " - " + endRow + ", column " + column, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(label, field, startRow, endRow, column);
      debugTimer("FormPanel.addMultiRow(Component, Component, int, int, int)", currentTime);
   }

   public void addMultiRow(Component label, Component field, int startRow, int endRow, int column, int mode)
   {
      debug("FormPanel.addMultiRow(Component, Component, int, int, int, int): add to rows " + startRow + " - " + endRow + ", column " + column + ", mode " + mode, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(label, field, startRow, endRow, column, mode);
      debugTimer("FormPanel.addMultiRow(Component, Component, int, int, int, int)", currentTime);
   }

   public void add(Component component, int row, int column, double fillRightPct)
   {
      debug("FormPanel.add(Component, int, int, double): add to row " + row + ", column " + column, 3);

      super.add(component);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(component, row, column, fillRightPct);
      debugTimer("FormPanel.add(Component, int, int, double)", currentTime);
   }

   public void add(Component label, Component field, int row, int column, double fillRightPct)
   {
      debug("FormPanel.add(Component, Component, int, int, double): add field to row " + row + ", column " + column, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(label, field, row, column, fillRightPct);
      debugTimer("FormPanel.add(Component, Component, int, int, double)", currentTime);
   }
  
   public void add(Component label, Component field, int row, int column, int mode, double fillRightPct)
   {
      debug("FormPanel.add(Component, Component, int, int, int, double): add field to row " + row + ", column " + column + ", mode " + mode, 3);
      
      if ((mode < FormLayout.DEFAULT) || (mode > FormLayout.LABEL_ON_TOP))
         {
            debug("FormPanel.add: Invalid mode: " + mode + "!  Using default.", 0);

            add(label, field, row, column);
         }

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).add(label, field, row, column, mode, fillRightPct);
      debugTimer("FormPanel.add(Component, Component, int, int, int, double)", currentTime);
   }

   public void addMultiRow(Component component, int startRow, int endRow, int column, double fillRightPct)
   {
      debug("FormPanel.addMultiRow(Component, int, int, int, double): add to rows " + startRow + " - " + endRow + ", column " + column, 3);

      super.add(component);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(component, startRow, endRow, column, fillRightPct);
      debugTimer("FormPanel.addMultiRow(Component, int, int, int, double)", currentTime);
   }

   public void addMultiRow(Component label, Component field, int startRow, int endRow, int column, double fillRightPct)
   {
      debug("FormPanel.addMultiRow(Component, Component, int, int, int, double): add to rows " + startRow + " - " + endRow + ", column " + column, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(label, field, startRow, endRow, column, fillRightPct);
      debugTimer("FormPanel.addMultiRow(Component, Component, int, int, int, double)", currentTime);
   }

   public void addMultiRow(Component label, Component field, int startRow, int endRow, int column, int mode, double fillRightPct)
   {
      debug("FormPanel.addMultiRow(Component, Component, int, int, int, int, double): add to rows " + startRow + " - " + endRow + ", column " + column + ", mode " + mode, 3);

      super.add(label);
      super.add(field);

      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).addMultiRow(label, field, startRow, endRow, column, mode, fillRightPct);
      debugTimer("FormPanel.addMultiRow(Component, Component, int, int, int, int, double)", currentTime);
   }

   // removes from the JPanel and the FormLayout
   public void remove(Component comp)
   {
      debug("FormPanel.remove(Component)", 3);

      super.remove(comp);
      long currentTime = System.currentTimeMillis();
      ((FormLayout)getLayout()).removeLayoutComponent(comp);
      debugTimer("FormPanel.remove(Component)", currentTime);
   }

   // fillRightPct will be used from now on when an add() method is called that does not
   // include a (double)fillRightPct parameter
   public void setDefaultFillRightPct(double fillRightPct)
   {
      debug("FormPanel.setDefaultFillRightPct(double): pct " + fillRightPct, 3);
      ((FormLayout)getLayout()).setDefaultFillRightPct(fillRightPct);
   }

   // terminate generic add methods that are not used by this panel

   public Component add(Component comp)
   {
      debug("FormPanel.FormPanel.add(Component): Warning!  Use of unsupported add method", 0); 
      return null;
   }
   public Component add(String name, Component comp) 
   {
      debug("FormPanel.FormPanel.add(String, Component): Warning!  Use of unsupported add method", 0); 
      return null;
   }
   public Component add(Component comp, int index) 
   {
      debug("FormPanel.FormPanel.add(Component, int): Warning!  Use of unsupported add method", 0); 
      return null;
   }
   public void add(Component comp, Object constraints) { debug("FormPanel.FormPanel.add(Component, Object): Warning!  Use of unsupported add method", 0); }
   public void add(Component comp, Object constraints, int index) { debug("FormPanel.FormPanel.add(Component, Object, int): Warning!  Use of unsupported add method", 0); }
   // --------------------------- //

    // debug out
   protected void debug(String message, int level)
   {
      if (level <= DEBUG_LEVEL)
         {
            for(int index = 0; index < level; index++)
               {
                  message = "-" + message;
               }
            System.out.println(message);
         }
   }

   protected void debugTimer(String method, long startTime)
   {
      debug(method + " total time: " + (System.currentTimeMillis() - startTime), 3);
   }
}
