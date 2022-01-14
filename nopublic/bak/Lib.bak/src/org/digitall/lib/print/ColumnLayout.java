package org.digitall.lib.print;

import java.awt.Component;

import java.util.Enumeration;
import java.util.Vector;

/*------------------------------------------------------------------------------
Author:         Byron Hawkins
Date:           2/12/99
Purpose:        An abstract layout manager to set the vertical position and height
				of a set of components based on unique, ordered, non-sequential 
				column numbers, layout size, and component minimum and preferred
				sizes.  
Basic Design:   Each ColumnLayout is responsible for a single column of components,
				which may be in the form of label/field pairs, or single components
				spanning the entire column.  Each component or pair of components
				is stored in a SegmentLayout.  Columns are linked together by their
				SegmentLayouts' links to other Columns' SegmentLayouts.  Each Column
				calculates its minimum and preferred locations as the maximum of 
				its Segments' minimum and preferred locations.  ColumnLayout sets 
				its actual location on the basis of a percentage distance from
				its minimum location to its preferred location; the percentage
				is calculated by ColumnLeader (see FormLayout inner class).  
------------------------------------------------------------------------------
Copyright 1999 HawkinsSoftware
This code is free for distribution and/or modification.
Please do not remove the copyright.
------------------------------------------------------------------------------*/
//import com.HawkinsSoftware.visual.shared.*;

class ColumnLayout
{

   protected int m_minLocation = 0;
   protected int m_prefLocation = 0;   
   protected int m_currLocation = 0;   // last layed out position
   private int m_labelWidth = 0;
   private int m_columnIndex = 0;

   protected FormLayout m_containingLayout = null;

   protected Vector m_segments = null; // SegmentLayout

   // debug setting 
   //	0  - critical errors only
   //	3  - constructors, add methods, and dependents
   //	6  - size methods and dependents
   //	8  - layout methods and dependents
   //	10 - everything but the kitchen sink
   private final int DEBUG_LEVEL = 0;

   public ColumnLayout(int index, FormLayout containingLayout)
   {
      debug("ColumnLayout.ColumnLayout(int, FormLayout): index " + index, 3);    

      m_segments = new Vector();
      m_columnIndex = index;
      m_containingLayout = containingLayout;
   }

   private SegmentLayout addSegment(int segmentIndex)
   {
      debug("ColumnLayout.addSegment(int): index " + m_columnIndex, 3);    
        
      SegmentLayout newSegment = new SegmentLayout(segmentIndex);
      m_segments.add(newSegment);
      return newSegment;
   }

   // max of segments' min locations
   protected void findMinimumLocation()
   {
      debug("ColumnLayout.findMinimumLocation(): index " + m_columnIndex, 6);    

      calculateLabelWidth();

      Enumeration e = m_segments.elements();
      int loc = 0;
      int segmentIndexLoc = 0;

      while (e.hasMoreElements())
         {
            segmentIndexLoc = ((SegmentLayout)e.nextElement()).getMinimumLocation();
            if (segmentIndexLoc > loc)
               {
                  loc = segmentIndexLoc;
               }
         }
      setMinimumLocation(loc);
      debug("ColumnLayout.findMinimumLocation(): [index " + m_columnIndex + "] setting m_minLocation to " + m_minLocation, 6);    
   }
    
   // max of segments' pref locations
   protected void findPreferredLocation()
   {
      debug("ColumnLayout.findPreferredLocation(): index " + m_columnIndex, 6);    

      calculateLabelWidth();

      Enumeration e = m_segments.elements();
      int loc = 0;
      int segmentLoc = 0;

      while (e.hasMoreElements())
         {
            segmentLoc = ((SegmentLayout)e.nextElement()).getPreferredLocation();
            if (segmentLoc > loc)
               {
                  loc = segmentLoc;
               }
         }
      setPreferredLocation(loc);
      debug("ColumnLayout.findPreferredLocation(): [index " + m_columnIndex + "] setting m_prefLocation to " + m_prefLocation, 6);    
   }

   // "pct" of the way from min to pref location
   // findMinimumLocation() and findPreferredLocation() are not called here, because they 
   // are recursive and lengthy; these are called by ColumnLeader in preparation for the
   // call to this method
   protected void findIntermediateLocation(double pct)
   {
      debug("ColumnLayout.findIntermediateLocation(double): pct " + pct + ", index " + m_columnIndex, 8);    

      m_currLocation = m_minLocation + (int)((m_prefLocation - m_minLocation) * pct);
      debug("ColumnLayout.findIntermediateLocation(): setting m_currLocation to " + m_currLocation, 8);    
   }

   // hook for subclasses that behave differently here
   protected void setMinimumLocation(int minLoc)
   {
      debug("ColumnLayout.setMinimumLocation(int): index " + m_columnIndex, 6);    
      m_minLocation = minLoc;
   }

   // hook for subclasses that behave differently here
   protected void setPreferredLocation(int prefLoc)
   {
      debug("ColumnLayout.setPreferredLocation(int): index " + m_columnIndex, 6);    
      m_prefLocation = prefLoc;// + m_gap;
   }

   protected void doLayout()
   {
      debug("ColumnLayout.doLayout(): index " + m_columnIndex, 8);    

      long currentTime = System.currentTimeMillis();
		
      Enumeration e = m_segments.elements();
      while (e.hasMoreElements())
         {
            ((SegmentLayout)e.nextElement()).doLayout();
         }
      debugTimer("ColumnLayout.doLayout() ", currentTime);
   }

   public int getMinimumLocation()
   {
      debug("ColumnLayout.getMinimumLocation(): m_minLocation " + m_minLocation + ", index " + m_columnIndex, 6);    

      return m_minLocation;
   }

   public int getPreferredLocation()
   {
      debug("ColumnLayout.getPreferredLocation(): m_prefLocation " + m_prefLocation + ", index " + m_columnIndex, 6);    

      return m_prefLocation;
   }

   private int getCurrentLocation()
   {
      debug("ColumnLayout.getCurrentLocation(): m_currLocation " + m_currLocation + ", index " + m_columnIndex, 8);    

      return m_currLocation;
   }

   public int getIndex()
   {
      debug("ColumnLayout.getIndex(): index " + m_columnIndex, 10);
      return m_columnIndex;
   }
    
   private void calculateLabelWidth()
   {
      m_labelWidth = 0;
		
      Enumeration e = m_segments.elements();
      SegmentLayout nextSegment;
		
      while (e.hasMoreElements())
         {
            nextSegment = (SegmentLayout)e.nextElement();
            if (nextSegment.m_label == null)
               {
                  continue;
               }
            if (nextSegment.m_labelMode == FormLayout.FREE_FIELD)
               {
                  continue;
               }
            if (nextSegment.m_label.getPreferredSize().width > m_labelWidth)
               {
                  m_labelWidth = nextSegment.m_label.getPreferredSize().width;
               }
         }
   }

   // This class does all the work here.
   public class SegmentLayout
   {
      // May not be a label, but it will be treated like one regardless.
      // m_labelMode will be set to FREE_FIELD if m_label is not a label, so
      // it will not be forced to line up inside the other fields in this Column.
      private Component m_label = null;

      // the rest of the components.  Their positions are always treated as relative
      // to the space available; only the first of m_components will be aligned with
      // any other component on the layout.
      private Vector m_components = null; // Component

      // corresponds to the absolute row number in FormLayout
      private int m_segmentIndex = 0;

      // FormPanel constants.  Allow labels to behave specially for layout compression.
      private int m_labelMode = 0;

      // the segment to the right of this 
      private SegmentLayout m_followingSegment = null;

      // the segment to the left of this 
      private SegmentLayout m_previousSegment = null;

      // Ghost components return boundaries but do not layout the components.
      // They are for multi-row components.
      private boolean m_isGhost = false;

      // determines how far the Segment will stretch to the right to fill 
      // the gap up to the next Segment.  It constitues the percentage of the 
      // difference of the minimum and preferred sizes; if said value is >=
      // the amount of extra space, then this Segment is willing to stretch out
      // and right-justify.
      private double m_pct = 0;

      // segment spans multiple rows: will need to check all following rows
      // to determine layout boundary
      private int m_lastGhost = Integer.MAX_VALUE;

      public SegmentLayout(int segmentIndex)
      {
         debug("SegmentLayout.SegmentLayout(int): segmentIndex " + segmentIndex + ", column " + m_columnIndex, 3);

         m_components = new Vector();
         // prev and following columns will be set via ColumnLeader

         m_segmentIndex = segmentIndex;
      }

      public void add(Component component, double pct)
      {
         debug("SegmentLayout.add(Component): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);

         add(component, m_labelMode, pct);
      }

      public void add(Component component, int mode, double pct)
      {
         debug("SegmentLayout.add(Component, int): mode " + mode + ", segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);

         setPct(pct);

         if (m_label == null)
            {
               m_label = component;
               m_labelMode = mode;
               if ((mode == FormLayout.DEFAULT) && (component.getPreferredSize().width > m_labelWidth))
                  {
                     debug("SegmentLayout.add(Component): setting label width to " + component.getPreferredSize().width + ", segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);
                     m_labelWidth = component.getPreferredSize().width;
                  }
            }
         else
            {
               m_components.add(component);
               if (m_isGhost)
                  {
                     m_labelMode = mode;
                  }
            }
      }

      public void addGhost(Component component)
      {
         debug("SegmentLayout.addGhost(Component): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);
         m_isGhost = true;
         add(component, 0);
      }

      public void addGhost(Component component, int mode)
      {
         debug("SegmentLayout.addGhost(Component, int): mode " + mode + ", segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);
         m_isGhost = true;
         add(component, mode, 0);
      }

      public void setLastGhost(int lastGhost)
      {
         debug("SegmentLayout.setLastGhost(int): setting last ghost to " + lastGhost, 3);
         m_lastGhost = lastGhost;
      }

      protected void removeLayoutComponent(Component component)
      {
         debug("SegmentLayout.removeLayoutComponent(Component): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);
         // removing the label
         if (m_label == component)
            {
               if (m_components.size() == 0)
                  {
                     // nothing left in this segmentIndex
                     removeThisSegment();
                  }
               else
                  {
                     // make the first component the new label
                     m_label = (Component)m_components.remove(0);
                     m_labelMode = 0;
                     if (m_label.getPreferredSize().width > m_labelWidth)
	                {
                           m_labelWidth = m_label.getPreferredSize().width;
	                }
                  }
               return;
            }
         if (m_components.remove(component))
            {
               return;
            }
         // not here.  check leftwards
         if (m_previousSegment != null)
            {
               m_previousSegment.removeLayoutComponent(component);
            }
      }

      private void removeThisSegment()
      {
         debug("SegmentLayout.removeThisSegment(Component): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 3);
         if (m_previousSegment == null)
            {
               m_followingSegment.m_previousSegment = null;
            }
         else
            {
               m_followingSegment.m_previousSegment = m_previousSegment;
               m_previousSegment.m_followingSegment = m_followingSegment;
            }
         // this will be cleaned up, right?
      }

      private void setPct(double pct)
      {
         if (pct > 1)
            {
               pct = 1;
            }
         if (pct < 0)
            {
               pct = 0;
            }
         m_pct = pct;
      }

      public SegmentLayout getSegment(ColumnLayout column)
      {
         debug("SegmentLayout.getSegment(ColumnLayout): column " + column.getIndex() + ", segmentIndex " + m_segmentIndex, 3);
         // looking for this Segment
         if (column.getIndex() == m_columnIndex)
            {
               return this;     
            }
         // it belongs somewhere to the left of here
         else if (column.getIndex() < m_columnIndex) 
            {
				// it does not exist: create it and link it up
               if (m_previousSegment == null)
                  {
                     debug("SegmentLayout.getSegment(ColumnLayout): adding segmentIndex to the left of column " + m_columnIndex + ", new column " + column.getIndex() + ", segmentIndex " + m_segmentIndex, 3);

                     // it's new, and is the first in this segmentIndex
                     SegmentLayout newSegment = column.addSegment(m_segmentIndex);

                     newSegment.m_previousSegment = null;
                     newSegment.m_followingSegment = this;
                     m_previousSegment = newSegment;

                     return newSegment;
                  }
				// it might exist: let the leftward neighbor deal with it
               else
                  {
                     return m_previousSegment.getSegment(column);
                  }
            }
         // it belongs to the right of here and does not exist: create it and link it up
         else
            {
               debug("SegmentLayout.getSegment(ColumnLayout): adding segmentIndex to the right of column " + m_columnIndex + ", new column " + column.getIndex() + ", segmentIndex " + m_segmentIndex, 3);

               SegmentLayout newSegment = column.addSegment(m_segmentIndex);

               newSegment.m_followingSegment = m_followingSegment;
               m_followingSegment.m_previousSegment = newSegment;
               newSegment.m_previousSegment = this;
               m_followingSegment = newSegment;

               return newSegment;
            }
      }

      public void doLayout()
      {
         debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", m_pct " + m_pct, 8);

         // don't layout ghosts
         if (m_isGhost)
            {
               return;
            }

         // must have m_label, even if it isn't a label per se
         if (m_label == null)
            {
               debug("SegmentLayout.doLayout(): m_label is null", 8);
               return;
            }

         int labelLocation = 0;

         if (m_labelMode == FormLayout.FREE_LABEL)
            {
				// FREE_LABELs will always go as far to the left as they possibly can; 
				// the whole column will move right to accomodate if necessary
               int offset = m_label.getPreferredSize().width - m_labelWidth;
               debug("SegmentLayout.doLayout(): m_label.getPreferredSize().width " + m_label.getPreferredSize().width + ", m_labelWidth " + m_labelWidth + ", segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 8);
               if (offset < 0)
                  {
                     // this label doesn't need any freedom!
                     offset = 0;
                  }
               labelLocation = m_currLocation - offset;
               debug("SegmentLayout.doLayout(): offset " + offset + ", labelLocation " + labelLocation + ", segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 8);
            }
         else
            {
               labelLocation = m_currLocation;
            }

         if ((m_labelMode == FormLayout.FREE_FIELD) && (m_components.size() == 0))
            {
               debug("SegmentLayout.doLayout(): FREE_FIELD and no components, so treating as a big field", 8);

				// This is the only component in this Segment; treat it like a field.
				// Identify the amount of space available up to the right neighbor's
				// left boundary.

               int followingLeftBoundary = m_followingSegment.getLeftBoundary();
               if (m_lastGhost < Integer.MAX_VALUE)
                  {
                     debug("SegmentLayout.doLayout(): has ghosts", 8);
                     // There are ghosts of this component in Segments below this one.
                     // Account for the left boundaries of the segments to the right of
                     // the ghosts
                     SegmentLayout nextSegment = null;
                     Enumeration e = m_segments.elements();
                     while (e.hasMoreElements())
                        {
                           nextSegment = (SegmentLayout)e.nextElement();
                           if ((nextSegment.m_segmentIndex > m_segmentIndex) && (nextSegment.m_segmentIndex <= m_lastGhost))
                              {
                                 debug("SegmentLayout.doLayout(): checking ghost for row " + nextSegment.m_segmentIndex, 8);
                                 if (nextSegment.m_followingSegment.getLeftBoundary() < followingLeftBoundary)
                                    {
                                       followingLeftBoundary = nextSegment.m_followingSegment.getLeftBoundary();
                                       debug("SegmentLayout.doLayout(): resetting left boundary to " + followingLeftBoundary, 8);
                                    }
                              }
                        }
                  }
               int room = followingLeftBoundary - m_currLocation;
               debug("SegmentLayout.doLayout(): room: " + room, 8);

               int labelWidth;
               if (m_label.getMinimumSize().width >= room)
                  {
                     debug("SegmentLayout.doLayout(): not enough room for min; forcing min: " + m_label.getMinimumSize().width, 8);
                     labelWidth = m_label.getMinimumSize().width;
                  }
               if (m_pct == 1)
                  {
                     // always stretching all the way
                     if ((room <= m_label.getMaximumSize().width) || (m_label.getMaximumSize().width <= m_label.getPreferredSize().width))
                        {
                           debug("SegmentLayout.doLayout(): m_pct is 1; setting size to room: " + room, 8);
                           labelWidth = room;
                        }
                     else
                        {
                           debug("SegmentLayout.doLayout(): m_pct is 1; room > max; setting size to max: " + m_label.getMaximumSize().width, 8);
                           labelWidth = m_label.getMaximumSize().width;
                        }
                  }
               else
                  {
                     if (m_label.getPreferredSize().width >= room) // squeezing 
                        {
                           debug("SegmentLayout.doLayout(): no extra room; setting size to: " + room, 8);
                           labelWidth = room;
                        }
                     else // have extra space beyond preferred width
                        {
                           // can I stretch that far?
                           if ( (m_label.getPreferredSize().width + ((m_label.getPreferredSize().width - m_label.getMinimumSize().width) * m_pct)) >= room)
                              {
                                 // yes
                                 if ((room <= m_label.getMaximumSize().width) || (m_label.getMaximumSize().width <= m_label.getPreferredSize().width))
                                    {
                                       debug("SegmentLayout.doLayout(): stretch reaches; setting size to : " + room, 8);
                                       labelWidth = room;
                                    }
                                 else
                                    {
                                       debug("SegmentLayout.doLayout(): stretch reaches, but beyond max; setting size to max: " + m_label.getMaximumSize().width, 8);
                                       labelWidth = m_label.getMaximumSize().width;
                                    }
                              }
                           else
                              {
                                 // no, stop at preferred width
                                 debug("SegmentLayout.doLayout(): stretch doesn't reach; setting size to preferred0: " + m_label.getPreferredSize().width, 8);
                                 labelWidth = m_label.getPreferredSize().width;
                              }
                        }
                  }
               m_label.setSize(labelWidth, m_label.getSize().height);
               if ((labelWidth < m_labelWidth) && (m_label.getAlignmentX() == Component.RIGHT_ALIGNMENT))
                  {
                     labelLocation += (m_labelWidth - labelWidth);
                  }
               debug("SegmentLayout.doLayout(): setting labelLocation " + labelLocation + ", label width " + m_label.getPreferredSize().width, 8);
               m_label.setLocation(labelLocation, m_label.getLocation().y);
               return;
            }

         // labels never change size (unless they change content, but that's
         // static to this perspective)
         m_label.setSize(m_label.getPreferredSize().width, m_label.getSize().height);
         if ((m_label.getPreferredSize().width < m_labelWidth) && (m_label.getAlignmentX() == Component.RIGHT_ALIGNMENT))
            {
               labelLocation += (m_labelWidth - m_label.getPreferredSize().width);
            }
         m_label.setLocation(labelLocation, m_label.getLocation().y);

         int minSizeSum = 0; 
         int prefSizeSum = 0;

         if (m_components.size() == 0)
            {
				// all done
               return;
            }

         // total min and pref widths
         Enumeration e = m_components.elements();
         Component nextComponent = null;
         while (e.hasMoreElements())
            {
               nextComponent = (Component)e.nextElement();
               minSizeSum += nextComponent.getMinimumSize().width;
               prefSizeSum += nextComponent.getPreferredSize().width;
            }
         int gapSum = (m_components.size() - 1) * m_containingLayout.getInternalHGap();
         minSizeSum += gapSum;
         prefSizeSum += gapSum;

         int followingLeftBoundary = m_followingSegment.getLeftBoundary();
         debug("SegmentLayout.doLayout(): initial following left boundary is " + followingLeftBoundary , 8);
         if (m_lastGhost < Integer.MAX_VALUE)
            {
				// There are ghosts of this component in Segments below this one.
				// Account for the left boundaries of the segments to the right of
				// the ghosts
               SegmentLayout nextSegment = null;
               e = m_segments.elements();
               while (e.hasMoreElements())
                  {
                     nextSegment = (SegmentLayout)e.nextElement();
                     if ((nextSegment.m_followingSegment.getLeftBoundary() < followingLeftBoundary) && 
                         ( (nextSegment.m_segmentIndex > m_segmentIndex) && (nextSegment.m_segmentIndex <= m_lastGhost) ) )
                        {
                           followingLeftBoundary = nextSegment.m_followingSegment.getLeftBoundary();
                           debug("SegmentLayout.doLayout(): resetting left boundary to " + followingLeftBoundary, 8);
                        }
                  }
            }
         int room = followingLeftBoundary - m_currLocation;
         debug("SegmentLayout.doLayout(): room: " + room, 8);
         int startx = m_currLocation;
         if (m_labelMode == FormLayout.FREE_FIELD)
            {
               room -= m_label.getPreferredSize().width;
               startx += m_label.getPreferredSize().width;
				// label might be blank
               if (m_label.getPreferredSize().width > 0)
                  {
                     room -= m_containingLayout.getInternalHGap();
                     startx += m_containingLayout.getInternalHGap();
                  }
            }
         else
            {
               room -= m_labelWidth;
               startx += m_labelWidth;
               if ((m_labelWidth > 0) || (m_labelMode == FormLayout.FREE_LABEL))
                  {
                     room -= m_containingLayout.getInternalHGap();
                     startx += m_containingLayout.getInternalHGap();
                  }
            }

         // check if this component can reach the right boundary with (preferred size + (stretchability * m_pct))
         // where stretchability is (prefSizeSum - minSizeSum)
         if ( (room > prefSizeSum) && ( (prefSizeSum + ((prefSizeSum - minSizeSum) * m_pct)) < room ) && (m_pct < 1) )
            {
				// can't reach -- set to preferred and leave space to the right
               room = prefSizeSum;
            }

         double pctEx = 0;
         if ((prefSizeSum - minSizeSum) > 0)
            {
               pctEx = (double)((double)(room - minSizeSum) / (double)(prefSizeSum - minSizeSum));
            }

         e = m_components.elements();
         Component previous = null;
         Component current = null;

         if (e.hasMoreElements())
            {
				// place the first component
               current = (Component)e.nextElement();
                
               if (m_components.size() > 1)
                  {
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; setting component location to " + startx, 8);
                     current.setLocation(startx, current.getLocation().y);
                  }
            }

         while (e.hasMoreElements())
            {
               previous = current;
               current = (Component)e.nextElement();
				// set the starting location of the next component
               if (previous.getMinimumSize().width < previous.getPreferredSize().width) // stretchability > 0
                  {
                     // account for stretch 
                     startx += previous.getMinimumSize().width + ((previous.getPreferredSize().width - previous.getMinimumSize().width) * pctEx);
                  }
               else
                  {
                     // can't stretch this one
                     startx += previous.getMinimumSize().width;
                  }
               if (previous.getMinimumSize().width > 0)
                  {
                     // no gaps for invisible componentry
                     startx += m_containingLayout.getInternalHGap();
                  }
               current.setLocation(startx, current.getLocation().y);
               int width = startx - previous.getLocation().x - m_containingLayout.getInternalHGap();
               debug("SegmentLayout.doLayout(): maximum width " + current.getMaximumSize().width + ", preferred with " + current.getPreferredSize().width, 8);
               if ((width <= current.getMaximumSize().width) || (current.getMaximumSize().width <= current.getPreferredSize().width))
                  {
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; setting component size to " + width, 8);
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; setting component location to " + startx, 8);
                     previous.setSize(width, previous.getSize().height);
                  }
               else
                  {
                     width = current.getMaximumSize().width;
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; setting component size to max " + width, 8);
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; setting component location to " + startx, 8);
                     previous.setSize(width, previous.getSize().height);
                  }
            }

         int currentWidth = 0;
			
         // will there be roundoff error?
         if (pctEx == 1)
            {
				// no
               debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; final component leaving space, so setting component size to preferred: " + (current.getPreferredSize()), 8);
               currentWidth = current.getPreferredSize().width;
            }
         else
            {
				// yes, so stretch per right neighbor's left boundary, regardless 
				// of m_pct, etc.
               currentWidth = (followingLeftBoundary - startx);
               if ((currentWidth <= current.getMaximumSize().width) || (current.getMaximumSize().width <= current.getPreferredSize().width))
                  {
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", followingLeftBoundary " + followingLeftBoundary + ", startx " + startx, 8);
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; final component meets following boundary, so setting component size to room left: " + currentWidth, 8);
                  }
               else
                  {
                     currentWidth = current.getMaximumSize().width;
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", followingLeftBoundary " + followingLeftBoundary + ", startx " + startx, 8);
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; final component meets following boundary, which exceeds max, so setting component size to max: " + currentWidth, 8);
                  }
            }
         current.setSize(currentWidth, current.getSize().height);
			
         // if there's only one component and extra space, account for its X alignment
         if (m_components.size() == 1) 
            {
               if ( ((currentWidth + startx) < followingLeftBoundary) && (current.getAlignmentX() == Component.RIGHT_ALIGNMENT) )
                  {
                     startx += (followingLeftBoundary - (currentWidth + startx));
                     debug("SegmentLayout.doLayout(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + "; xalign " + current.getAlignmentX() + "; setting component location to " + startx, 0);
                  }
               current.setLocation(startx, current.getLocation().y);
            }
      }

      // this may not be the minimumLocation of the Segment: it is only the
      // minimumLocation the Segment would choose if it had it's say
      public int getMinimumLocation()
      {
         debug ("SegmentLayout.getMinimumLocation(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 6);
         if (m_previousSegment == null)
            {
               return m_containingLayout.getLeftInset();
            }
         if (m_followingSegment == null)
            {
               return m_previousSegment.getMinRightBoundary() + getLabelOffset() + m_containingLayout.getInternalHGap();
            }
         return m_previousSegment.getMinRightBoundary() + getLabelOffset() + m_containingLayout.getInternalHGap();
      }

      // ditto regarding preferred
      public int getPreferredLocation()
      {
         debug ("SegmentLayout.getPreferredLocation(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 6);
         if (m_previousSegment == null)
            {
               return m_containingLayout.getLeftInset();
            }
         if (m_followingSegment == null)
            {
               return m_previousSegment.getPrefRightBoundary() + getLabelOffset();
            }
         return m_previousSegment.getPrefRightBoundary() + getLabelOffset() + m_containingLayout.getInternalHGap();
      }

      // accounts for the Column's chosen location and m_label's behavior per m_labelMode
      public int getLeftBoundary()
      {
         debug("SegmentLayout.getLeftBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 8);
         if (m_followingSegment == null)
            {
               debug("SegmentLayout.getLeftBoundary(): m_followingSegment is null -- returning column's current location", 8);
               return m_currLocation;
            }
         return m_currLocation - getLabelOffset() - m_containingLayout.getInternalHGap();
      }

      // right boundary in the minimum scenario, given the Column's min location
      public int getMinRightBoundary()
      {
         debug("SegmentLayout.getMinRightBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 6);
            
         int width = m_labelWidth;
         if ((m_labelMode == FormLayout.FREE_FIELD) && (m_label.getMinimumSize().width > m_labelWidth))
            {
               width = m_label.getMinimumSize().width;
            }
         debug("SegmentLayout.getMinRightBoundary(): starting with " + width + " for label", 6);

         Enumeration e = m_components.elements();
         while (e.hasMoreElements())
            {
               Component nextComponent = (Component)e.nextElement();
               width += nextComponent.getMinimumSize().width;
               debug("SegmentLayout.getMinRightBoundary(): adding " + nextComponent.getMinimumSize().width + " for component to get " + width, 6);
               if (nextComponent.getMinimumSize().width > 0)
                  {
                     width += m_containingLayout.getInternalHGap();
                  }
            }
         debug("SegmentLayout.getMinRightBoundary(): returning " + (m_minLocation + width), 6);
         return m_minLocation + width;
      }

      // ditto for preferred
      public int getPrefRightBoundary()
      {
         debug("SegmentLayout.getPrefRightBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", mode " + m_labelMode, 6);

         if (m_label == null)
            {
               debug("SegmentLayout.getPrefRightBoundary(): doh!  m_label is null!", 6);
            }

         int width = m_labelWidth;
         if ( (m_labelMode == FormLayout.FREE_FIELD) || ((m_label.getPreferredSize().width == 0) && !m_isGhost) )
            {
               width = m_label.getPreferredSize().width;
               debug("SegmentLayout.getPrefRightBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", starting with " + width + " for label", 6);
            }
         else
            {
               debug("SegmentLayout.getPrefRightBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", starting with " + width + " for label", 6);
            }

         Enumeration e = m_components.elements();
         while (e.hasMoreElements())
            {
               Component nextComponent = (Component)e.nextElement();
               width += nextComponent.getPreferredSize().width;
               debug("SegmentLayout.getPrefRightBoundary(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex + ", adding " + nextComponent.getPreferredSize().width + " for component (not including gap)", 6);
               if ((nextComponent.getPreferredSize().width > 0) && !m_isGhost) 
                  {
                     width += m_containingLayout.getInternalHGap();
                  }
            }
         debug("SegmentLayout.getPrefRightBoundary(): returning " + (m_prefLocation + width), 6);
         return m_prefLocation + width;
      }

      // how far left the label would go if it were free
      private int getLabelOffset()
      {
         debug("SegmentLayout.getLabelOffset(): segmentIndex " + m_segmentIndex + ", column " + m_columnIndex, 6);

         int offset = 0;
         if (m_labelMode == FormLayout.FREE_LABEL)
            {
               if (m_label.getPreferredSize().width > m_labelWidth)
                  {
                     offset = m_label.getPreferredSize().width - m_labelWidth;
                  }
            }
         debug("SegmentLayout.getLabelOffset(): returning " + offset, 10);
         return offset;
      }

      public int getIndex()
      {
         return m_segmentIndex;
      }
   }

   private void debugTimer(String method, long startTime)
   {
      debug(method + " total time: " + (System.currentTimeMillis() - startTime), 1);
   }
	
   private void debug(String message, int level)
   {
      if (DEBUG_LEVEL >= level)
         {
            for(int index = 0; index < level; index++)
               {
                  message = "-" + message;
               }
            System.out.println(message);
         }
   }
}
