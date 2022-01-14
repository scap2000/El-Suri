package org.digitall.lib.print;

import java.awt.Component;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;

/*------------------------------------------------------------------------------
Author:         Byron Hawkins
Date:           2/12/99
Purpose:        An abstract layout manager to set the horizontal positions of 
				a set of components based on unique, ordered, non-sequential
				row numbers, layout size, and component minimum and preferred
				sizes.
Basic Design:   Each RowLayout is responsible for a single row of components.
				Each component is stored in a component layout, which performs
				the actual layout functions.  Rows are linked together in a 
				doubly linked list.  Rows use the same minimum and preferred
				location floating point scheme as ColumnLayout, except that they
				will not expand to fill extra space.
------------------------------------------------------------------------------
Copyright 1999 HawkinsSoftware
This code is free for distribution and/or modification.
Please do not remove the copyright.
------------------------------------------------------------------------------*/
//import com.HawkinsSoftware.visual.shared.*;
// for debugging only

class RowLayout
{
   // the components in this layout
   private Vector m_components = null; // ComponentLayout

	// the row below this
   private RowLayout m_previousRow = null;
   // the row above this
   private RowLayout m_followingRow = null;
    
   FormLayout m_containingLayout = null;

	// these are set before doLayout() is called to avoid recursive excess
   protected int m_minLocation = 0;
   protected int m_prefLocation = 0;
   protected int m_currLocation = 0;

   private int m_minSize = 0;
   private int m_prefSize = 0;
   private int m_currSize = 0;

    // how to position components that are allotted extra space (components
	// will never fill the extra space)
   private int m_justification = ALIGN_CENTER;

   // row index
   private int m_index = 0;

   // constants for m_justification
   public final static int ALIGN_TOP = 0;
   public final static int ALIGN_CENTER = 1;
   public final static int ALIGN_BOTTOM = 2;

   // debug setting 
   //	0  - critical errors only
   //	3  - constructors, add methods, and dependents
   //	6  - size methods and dependents
   //	8  - layout methods and dependents
   //	10 - everything but the kitchen sink
   private static final int DEBUG_LEVEL = 0;

   public RowLayout(int index, FormLayout containingLayout)
   {
      debug("RowLayout.RowLayout(int, FormLayout): row " + index, 3);

      m_index = index;
      m_components = new Vector();
      m_containingLayout = containingLayout;
   }

   public int getIndex()
   {
      debug("RowLayout.getIndex(): " + m_index, 10);
      return m_index;
   }

   private void debugLabel(Component label)
   {
      try
         {
            debug("RowLayout.addComponent(Component): Label is " + ((JLabel)label).getText(), 3);
         }
      catch (ClassCastException e)
         {
         }
   }

   public void add(Component addMe)
   {
      debug("RowLayout.add(Component): row " + m_index, 3);
      debugLabel(addMe);

      m_components.add(new ComponentLayout(addMe));
   }

   public void addFloater(Component addMe, int anchorRow)
   {
      debug("RowLayout.addFloater(Component, int): anchorRow " + anchorRow + ", row " + m_index, 3);
      debugLabel(addMe);

      if (anchorRow == m_index)
         {
            try
               {
                  ((JComponent)addMe).setAlignmentY(Component.TOP_ALIGNMENT);
                  add(addMe);
                  return;
               }
            catch (ClassCastException e)
               {}
         }

      ComponentLayout componentLayout = new ComponentLayout(addMe);
      m_components.add(componentLayout);

      getRow(anchorRow).setAnchor(componentLayout);
   }

   private void setAnchor(ComponentLayout addMe)
   {
      debug("RowLayout.setAnchor(ComponentLayout): row " + m_index, 3);

      addMe.setAnchor(this);
      m_components.add(addMe);
   }

   // may create the row requested
   public RowLayout getRow(int row)
   {
      debug("RowLayout.getRow(int): row " + row + ", this row " + m_index, 3);
      if (row == m_index)
         {
            return this;     
         }

      if (row < m_index)
         {
            // requested row belongs above this
            if (m_previousRow == null)
               {
                  // row does not exist: create it as first row 
                  RowLayout rowLayout = new RowLayout(row, m_containingLayout);
                  rowLayout.m_previousRow = null;
                  rowLayout.m_followingRow = this;
                  m_previousRow = rowLayout;

                  return rowLayout;
               }
            else
               {
				// it's above the this' previous row -- let it deal with it
                  return m_previousRow.getRow(row);
               }
         }
      else
         {
            // requested row belongs below and does not exist -- create it
            RowLayout rowLayout = new RowLayout(row, m_containingLayout);
            m_followingRow.m_previousRow = rowLayout;
            rowLayout.m_followingRow = m_followingRow;
            m_followingRow = rowLayout;
            rowLayout.m_previousRow = this;

            return rowLayout;
         }
   }

   public void removeLayoutComponent(Component removeMe)
   {
      debug("RowLayout.removeComponent(Component): row " + m_index, 3);

      Enumeration e = m_components.elements();
      while (e.hasMoreElements())
         {
            ComponentLayout componentLayout = (ComponentLayout)e.nextElement();
            if ( componentLayout.hasComponent(removeMe) )
               {
                  m_components.remove(componentLayout);
               }
         }
      removeIfEmpty();
   }

   // if this has no more components, remove this
   protected void removeIfEmpty()
   {
      debug("RowLayout.removeIfEmpty(): row " + m_index, 3);
      if (m_components.size() == 0)
         {
            if (m_previousRow == null)
               {
                  m_followingRow.m_previousRow = null;
               }
            else
               {
                  m_followingRow.m_previousRow = m_previousRow;
                  m_previousRow.m_followingRow = m_followingRow;
               }
         }
   }

   // determine m_prefLocation and m_prefSize
   protected void findPreferredLocation()
   {
      debug("RowLayout.findPreferredLocation(): row " + m_index, 6);

      if (m_previousRow != null)
         {
            m_previousRow.findPreferredLocation();
            int prefLocation = m_previousRow.getPrefLowerBoundary();
            if (m_followingRow != null)
               {
                  prefLocation += m_containingLayout.getInternalVGap();
               }
            setPreferredLocation(prefLocation);
         }
      else
         {
            m_prefLocation = m_containingLayout.getTopInset();
         }
      debug("RowLayout.findPreferredLocation(): m_prefLocation set to " + m_prefLocation + ", row " + m_index, 6);
      // ComponentLayout.getPreferredSize() relies on m_prefLocation!

      Enumeration e = m_components.elements();
      m_prefSize = 0;

      while (e.hasMoreElements())
         {
            ComponentLayout nextComponent;

            nextComponent = (ComponentLayout)e.nextElement();
            if (nextComponent.getPreferredSize(this) > m_prefSize)
               {
                  m_prefSize = nextComponent.getPreferredSize(this);
               }
         }
      debug("RowLayout.findPreferredLocation(): m_prefSize set to " + m_prefSize + ", row " + m_index, 6);
   }

   // determine m_minLocation and m_minSize
   protected void findMinimumLocation()
   {
      debug("RowLayout.findMinimumLocation(): row " + m_index, 6);

      if (m_previousRow != null)
         {
            m_previousRow.findMinimumLocation();
            int minLoc = m_previousRow.getMinLowerBoundary();
            if (m_followingRow != null) // RowLeader uses external gap
               {
                  minLoc += m_containingLayout.getInternalVGap();
               }
            setMinimumLocation(minLoc);
         }
      else
         {
            m_minLocation = m_containingLayout.getTopInset();
         }
      debug("RowLayout.findMinimumLocation(): m_minLocation set to " + m_minLocation + ", row " + m_index, 6);
      // ComponentLayout.getMinimumSize() relies on m_minLocation!

      Enumeration e = m_components.elements();
      m_minSize = 0;

      while (e.hasMoreElements())
         {
            ComponentLayout nextComponent;

            nextComponent = (ComponentLayout)e.nextElement();
            if (nextComponent.getMinimumSize(this) > m_minSize)
               {
                  m_minSize = nextComponent.getMinimumSize(this);
               }
         }
   }

   // hook for subclasses
   protected void setPreferredLocation(int prefLoc)
   {
      debug("RowLayout.setPreferredLocation(int): row " + m_index + ", location " + prefLoc, 6);
      m_prefLocation = prefLoc;
   }

   // hook for subclasses
   protected void setMinimumLocation(int minLoc)
   {
      debug("RowLayout.setMinimumLocation(int): row " + m_index + ", location " + minLoc, 6);
      m_minLocation = minLoc;
   }

   protected void setLocation(double pct)
   {
      debug("RowLayout.setLocation(double): pct " + pct + ", row " + m_index, 8);

      if (m_previousRow != null)
         {
            m_previousRow.setLocation(pct);
         }
      debug("RowLayout.setLocation(double): m_minLocation is " + m_minLocation + ", m_prefLocation is " + m_prefLocation, 8);

      m_currLocation = (int) (m_minLocation + ((m_prefLocation - m_minLocation) * pct));
      debug("RowLayout.setLocation(double): m_currLocation set to " + m_currLocation, 8);
   }

   protected int getPrefLowerBoundary()
   {
      debug("RowLayout.getPrefLowerBoundary(): row " + m_index, 6);
      return m_prefLocation + m_prefSize;
   }

   protected int getMinLowerBoundary()
   {
      debug("RowLayout.getMinLowerBoundary(): row " + m_index, 6);
      if (m_minSize == 0)
         {
            return m_minLocation;
         }
      return m_minLocation + m_minSize;
   }

   protected int getUpperBoundary()
   {
      debug("RowLayout.getUpperBoundary(): row " + m_index, 8);
      return m_currLocation;
   }

   public void doLayout()	
   {
      debug("RowLayout.doLayout(): row " + m_index, 8);

      if (m_previousRow != null)
         {
            m_previousRow.doLayout();
         }

      if (m_followingRow != null)
         {
            int room = (m_followingRow.getUpperBoundary() - m_containingLayout.getInternalVGap()) - m_currLocation;
            debug("RowLayout.doLayout(): row " + m_index + ", following upper bound - gap " + (m_followingRow.getUpperBoundary() - m_containingLayout.getInternalVGap()), 8);
            debug("RowLayout.doLayout(): row " + m_index + ", m_currLocation " + m_currLocation, 8);
            debug("RowLayout.doLayout(): row " + m_index + ", room " + room, 8);
    
            Enumeration e = m_components.elements();
    
            while (e.hasMoreElements())
               {
                  ((ComponentLayout)e.nextElement()).doLayout(room, this);
               }
         }
   }

   class ComponentLayout
   {
      private Component m_component = null;

      // m_component spans multiple rows, m_anchor is the first, and this is 
      // the last; intermediate rows do not contain or know about m_component
      private RowLayout m_anchor = null;

      public ComponentLayout(Component component)
      {
         debug("ComponentLayout.ComponentLayout(Component)", 3);
         m_component = component;
      }

      public void setAnchor(RowLayout thisRow)
      {
         debug("ComponentLayout.setAnchor(RowLayout): index " + getIndex(), 3);
         m_anchor = thisRow;
      }

      public boolean hasComponent(Component component)
      {
         // address comparison
         return (m_component == component);
      }

      public void doLayout(int room, RowLayout thisRow)
      {
         debug("ComponentLayout.doLayout(int, RowLayout): room " + room + ", thisRow index " + thisRow.getIndex() + ", this index " + getIndex(), 8);
         if (m_anchor != null)
            {
               debug("ComponentLayout.doLayout(int, RowLayout): m_anchor " + m_anchor.getIndex(), 8);
            }
         // don't set the location on the last component in a multi-row
         if ((m_anchor == null) || (m_anchor.getIndex() == thisRow.getIndex())) 	//  no anchor || this is the anchor
            {
               int location = thisRow.m_currLocation;
               if (m_component.getPreferredSize().height <= room)
                  {
                     location += ((room - m_component.getPreferredSize().height) * m_component.getAlignmentY());
                  }
               debug("ComponentLayout.doLayout(int, RowLayout): setting location to " + location, 8);
               m_component.setLocation(m_component.getLocation().x, location);
            }
         // don't set size on an anchor
         if ((m_anchor == null) || (m_anchor.getIndex() != thisRow.getIndex()))	//	no anchor || this is not the anchor
            {
               if ((m_anchor != null) && (m_anchor.getIndex() != thisRow.getIndex())) //  this is the end of the multiRow
                  {
                     room += (thisRow.m_currLocation - m_anchor.getUpperBoundary());	
                     debug("ComponentLayout.doLayout(int, RowLayout): m_currLocation " + thisRow.m_currLocation + ", anchor top " + 
                           m_anchor.getUpperBoundary() + "; adding " + (thisRow.m_currLocation - m_anchor.getUpperBoundary()) + " to room", 8);
                     debug("ComponentLayout.doLayout(int, RowLayout): room is now " + room, 8);
                  }
               if (m_component.getPreferredSize().height <= room)
                  {
                     debug("ComponentLayout.doLayout(int, RowLayout): setting size to " + m_component.getPreferredSize().height, 8);
                     m_component.setSize(m_component.getSize().width, m_component.getPreferredSize().height);
                  }
               else if (m_component.getMinimumSize().height < room)
                  {
                     debug("ComponentLayout.doLayout(int, RowLayout): setting size to " + room, 8);
                     m_component.setSize(m_component.getSize().width, room);
                  }
               else
                  {
                     debug("ComponentLayout.doLayout(int, RowLayout): setting size to " + m_component.getMinimumSize().height, 8);
                     m_component.setSize(m_component.getSize().width, m_component.getMinimumSize().height);
                  }
            }
      }

      public int getMinimumSize(RowLayout thisRow)
      {
         debug("ComponentLayout.getMinimumSize(RowLayout): row " + getIndex() + ", thisRow " + thisRow.getIndex(), 6);
         if ((m_anchor != null) && (m_anchor.getIndex() == thisRow.getIndex()))	//	anchor
            {
				// anchor expects no size allocation (will take it per below)
               return 0;
            }
         else if ((m_anchor != null) && (m_anchor.getIndex() != thisRow.getIndex()))	// last component in multi-row
            {
				// find out how much of m_component is contained in the above rows, and then
				// how much space we need to give it in this row
               debug("ComponentLayout.getMinimumSize(): found buoy: component height " + m_component.getMinimumSize().height + ", anchor location " + m_anchor.m_minLocation + ", thisRow location " + thisRow.m_minLocation, 8);
               int portionInThisRow = m_component.getMinimumSize().height - (thisRow.m_minLocation - m_anchor.m_minLocation);
               if (portionInThisRow <= 0)
                  {
                     // it fits in the above rows
                     return 0;
                  }
               return portionInThisRow;
            }
         else
            {
				// single row
               return m_component.getMinimumSize().height;
            }
      }

      // ditto for preferred
      public int getPreferredSize(RowLayout thisRow)
      {
         debug("ComponentLayout.getPreferredSize(RowLayout): row " + getIndex() + ", thisRow " + thisRow.getIndex(), 6);
         if ((m_anchor != null) && (m_anchor.getIndex() == thisRow.getIndex()))	//	(m_mode == FLOAT_ANCHOR)
            {
               return 0;
            }
         else if ((m_anchor != null) && (m_anchor.getIndex() != thisRow.getIndex()))	//   (m_mode == FLOAT_BUOY)
            {
               int portionInThisRow = m_component.getPreferredSize().height - (thisRow.m_prefLocation - m_anchor.m_prefLocation);
               if (portionInThisRow <= 0)
                  {
                     return 0;
                  }
               return portionInThisRow;
            }
         else
            {
               return m_component.getPreferredSize().height;
            }
      }
   }

   public static void debug(String message, int level)
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
}
