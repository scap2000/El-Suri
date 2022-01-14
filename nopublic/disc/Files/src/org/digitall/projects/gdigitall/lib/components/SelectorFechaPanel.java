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
 * SelectorFechaPanel.java
 *
 * */
package org.digitall.projects.gdigitall.lib.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public final class SelectorFechaPanel extends JPanel
{
    private SelectorFecha parent;
 
    public SelectorFechaPanel()
    {
        days = new int[7][6];
        startYear = 1901;
        endYear = 2099;
        firstDay = 0;
        showCurrentDate = false;
        initializeVariables();
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    }

    public SelectorFechaPanel(int firstDay, boolean showCurrentDate, SelectorFecha _parent)
    {
        days = new int[7][6];
        startYear = 1901;
        endYear = 2099;
        this.firstDay = firstDay;
        this.showCurrentDate = showCurrentDate;
        parent = _parent;
        initializeVariables();
    }

    public SelectorFechaPanel(int firstDay, boolean showCurrentDate, int startYear, int endYear, SelectorFecha _parent)
    {
        days = new int[7][6];
        this.startYear = startYear;
        this.endYear = endYear;
        this.firstDay = firstDay;
        this.showCurrentDate = showCurrentDate;
        parent = _parent;
        initializeVariables();
    }

    public void dispose() 
    {
      parent.setFechaX(getYear() +"-"+ getMonth() +"-"+ getDay());
    }

    private void initializeVariables() 
    {
        botonItemListener = new SelectorFechaListener(this);
        comboBoxListener = new SelectorFechaComboListener(this);
        calendar = Calendar.getInstance();
        showForYear = calendar.get(1);
        showForMonth = calendar.get(2);
        daySelected = (new Integer(calendar.get(5))).toString();
        monthSelected = (new Integer(calendar.get(2) + 1)).toString();
        yearSelected = (new Integer(calendar.get(1))).toString();
        datePanel = new JPanel();
        datePanel.setLayout(new BorderLayout());
        datePanel.setBackground(new Color(112, 145, 204));
        yearCombo = createYearCombo();
        yearCombo.setFont(yearCombo.getFont().deriveFont(1, 11F));
        monthCombo = createMonthCombo();
        monthCombo.setFont(monthCombo.getFont().deriveFont(1, 11F));
        datePanel.add(yearCombo, "East");
        datePanel.add(monthCombo, "Center");
        centrePanel = new JPanel();
        centrePanel.setLayout(new BorderLayout(5, 5));
        centrePanel.setBackground(new Color(112, 145, 204));
        centrePanel.add(datePanel, "North");
        if(showCurrentDate)
        {
            String Fecha="";
            try
            {
               Fecha = OP_Proced
                    .Fecha2(yearSelected + "-" + monthSelected + "-" + daySelected,true);  
            }catch (Exception x)
            {
              x.printStackTrace();
            }
            currentDateLabel = new JLabel("Fecha de Hoy : " + Fecha);
            currentDateLabel.setFont(currentDateLabel.getFont().deriveFont(1, 11F));
            currentDatePanel = new JPanel();
            currentDatePanel.setLayout(new FlowLayout(1));
            currentDatePanel.setBorder(BorderFactory.createEtchedBorder());
            currentDatePanel.add(currentDateLabel);
            centrePanel.add(currentDatePanel, "South");
        }
        add(centrePanel);
        yearCombo.addItemListener(comboBoxListener);
        monthCombo.addItemListener(comboBoxListener);
        bandera = "";
    }

    protected final void initializeCalendar()
    {
        monthCombo.removeItemListener(comboBoxListener);
        yearCombo.removeItemListener(comboBoxListener);
        if(showCalendarForDateSelected)
            monthCombo.setSelectedIndex(showForMonth - 1);
        else
            monthCombo.setSelectedIndex(showForMonth);
        yearCombo.setSelectedItem(new Integer(showForYear));
        if(showCalendarForDateSelected)
            days = createCalendar(showForYear, showForMonth - 1);
        else
            days = createCalendar(showForYear, showForMonth);
        if(daysPanel != null)
            centrePanel.remove(daysPanel);
        createDaysPanel();
        centrePanel.add(daysPanel, "Center");
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        monthCombo.addItemListener(comboBoxListener);
        yearCombo.addItemListener(comboBoxListener);
        bandera = "";
    }

    private final int[][] createCalendar(int year, int month)
    {
        boolean calendarCompleted = false;
        days = new int[7][6];
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        int number = calendar.get(7);
        int k = 1;
        for(int j = 0; j < 6; j++)
        {
            int i = 0;
            if(j == 0)
                if(firstDay == 0)
                {
                    i = number - 1;
                } else
                {
                    i = number - 2;
                    if(i < 0)
                        i = 6;
                }
            for(; i < 7; i++)
            {
                days[i][j] = k;
                if(month <= 6)
                {
                    if(month % 2 == 0)
                    {
                        if(k == 31)
                        {
                            calendarCompleted = true;
                            break;
                        }
                    } else
                    if(month == 1)
                    {
                        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                        {
                            if(k == 29)
                            {
                                calendarCompleted = true;
                                break;
                            }
                        } else
                        if(k == 28)
                        {
                            calendarCompleted = true;
                            break;
                        }
                    } else
                    if(k == 30)
                    {
                        calendarCompleted = true;
                        break;
                    }
                } else
                if(month % 2 == 0)
                {
                    if(k == 30)
                    {
                        calendarCompleted = true;
                        break;
                    }
                } else
                if(k == 31)
                {
                    calendarCompleted = true;
                    break;
                }
                k++;
            }

            if(calendarCompleted)
                break;
        }

        return days;
    }

    private final JComboBox createYearCombo()
    {
        JComboBox combo = new JComboBox();
        combo.setBackground(Color.white);
        for(int i = startYear; i <= endYear; i++)
            combo.addItem(new Integer(i));
        return combo;
    }

    private final JComboBox createMonthCombo()
    {
        JComboBox list = new JComboBox();
        list.setBackground(Color.white);
        list.addItem("E n e r o");
        list.addItem("F e b r e r o");
        list.addItem("M a r z o");
        list.addItem("A b r i l");
        list.addItem("M a y o");
        list.addItem("J u n i o");
        list.addItem("J u l i o");
        list.addItem("A g o s t o");
        list.addItem("S e p t i e m b r e");
        list.addItem("O c t u b r e");
        list.addItem("N o v i e m b r e");
        list.addItem("D i c i e m b r e");
        return list;
    }
   private String getNroMes(String Mes)
   {
     String nromes="";
     if (Mes.equals("E n e r o"))
     {
       nromes="01";
     } else if (Mes.equals("F e b r e r o"))
     {
       nromes="02";
     } else if (Mes.equals("M a r z o"))
     {
       nromes="03";
     } else if (Mes.equals("A b r i l"))
     {
       nromes="04";
     } else if (Mes.equals("M a y o"))
     {
       nromes="05";
     } else if (Mes.equals("J u n i o"))
     {
       nromes="06";
     } else if (Mes.equals("J u l i o"))
     {
       nromes="07";
     } else if (Mes.equals("A g o s t o"))
     {
       nromes="08";
     } else if (Mes.equals("S e p t i e m b r e"))
     {
       nromes="09";
     } else if (Mes.equals("O c t u b r e"))
     {
       nromes="10";
     } else if (Mes.equals("N o v i e m b r e"))
     {
       nromes="11";
     } else if (Mes.equals("D i c i e m b r e"))
     {
       nromes="12";
     }
     
     return nromes;   
   }
    private final void createDaysPanel()
    {
        daysPanel = new JPanel();
        daysPanel.setBackground(Color.white);
        daysPanel.setLayout(new GridLayout(7, 7));
        JDayLabel sunday = new JDayLabel("Domingo");
        sunday.setForeground(Color.red);
        JDayLabel monday = new JDayLabel("Lunes");
        JDayLabel tuesday = new JDayLabel("Martes");
        JDayLabel wednesday = new JDayLabel("Miercoles");
        JDayLabel thursday = new JDayLabel("Jueves");
        JDayLabel friday = new JDayLabel("Viernes");
        JDayLabel saturday = new JDayLabel("Sabado");
        saturday.setForeground(Color.blue);
        if(firstDay == 0)
        {
            daysPanel.add(sunday);
            daysPanel.add(monday);
            daysPanel.add(tuesday);
            daysPanel.add(wednesday);
            daysPanel.add(thursday);
            daysPanel.add(friday);
            daysPanel.add(saturday);
        } else
        {
            daysPanel.add(monday);
            daysPanel.add(tuesday);
            daysPanel.add(wednesday);
            daysPanel.add(thursday);
            daysPanel.add(friday);
            daysPanel.add(saturday);
            daysPanel.add(sunday);
        }
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i = 0; i < 6; i++)
        {
            for(int k = 0; k < 7; k++)
                if(days[k][i] == 0)
                {
                    JDayButton dayButton = new JDayButton();
                    daysPanel.add(dayButton);
                    
                } else
                {
                    JDayButton dayButton = new JDayButton((new Integer(days[k][i])).toString());
                    dayButton.addMouseListener(new MouseAdapter()
                    {
                       public void mouseClicked(MouseEvent e)
                        {
                          if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1)
                          {
                            bandera = "T";
                          } else
                          {
                            bandera = "";
                          }
                        }
                    });
                    /** los sig. if son para poner el color azul para los dias sabados y rojo para los domingos*/
                    if (k == 5)
                    {
                      dayButton.setForeground(Color.blue);
                    } else if (k ==6)
                    {
                      dayButton.setForeground(Color.red);
                    }
                    //COMIENZO: ACA CAPTURO EL MES Y EL AÑO PARA PODER PINTAR DE ROJO LOS DIAS FERIADOS
                    String numMes = getNroMes(monthCombo.getSelectedItem().toString().trim());
                    String anio = "2005";
                    if (feriado(days[k][i],numMes,anio))
                    {
                      dayButton.setForeground(Color.red);
                    }
                    // FIN: YA ESTAN PINTADOS DICHOS DIAS
                    
                    Integer selectedYear = (Integer)yearCombo.getSelectedItem();
                    if(days[k][i] == (new Integer(daySelected)).intValue() && monthCombo.getSelectedIndex() == (new Integer(monthSelected)).intValue() - 1 && selectedYear.intValue() == (new Integer(yearSelected)).intValue())
                        dayButton.setSelected(false);//Aquí puse FALSE para poder seleccionar la fecha de hoy
                    dayButton.addItemListener(botonItemListener);
                    buttonGroup.add(dayButton);
                    daysPanel.add(dayButton);
                }
        }

    }
    

    protected boolean feriado(int Dia,String Mes,String Anio)
    {
        String Q = "";
        String nro = "";
        if (Dia > 9)
        {
          Q ="SELECT count(*) FROM calendario WHERE fecha = '"+ Anio +"-"+ Mes +"-"+ Dia +"' AND descripcion <> 'Sabado' AND descripcion <> 'Domingo' ";  
        } else
        {
          Q ="SELECT count(*) FROM calendario WHERE fecha = '"+ Anio +"-"+ Mes +"-0"+ Dia +"' AND descripcion <> 'Sabado' AND descripcion <> 'Domingo' ";  
        }
        //System.out.println(Q);
        nro = OP_Proced.getCampo(Q);
        if (nro.equals("0"))
        {
          return false;
        } else
        {
          return true;          
        }

    }
    protected void showCalendarForDate(int year, int month)
    {
        showForMonth = month;
        showForYear = year;
        showCalendarForDateSelected = true;
    }

    protected void setDay(String daySelected)
    {
        this.daySelected = daySelected;
    }

    protected void setMonth(String monthSelected)
    {
        this.monthSelected = monthSelected;
    }

    protected void setYear(String yearSelected)
    {
        this.yearSelected = yearSelected;
    }

    protected String getDay()
    {
        //System.out.println(daySelected);
        return daySelected;
    }

    protected String getMonth()
    {
        return monthSelected;
    }

    protected String getYear()
    {
        return yearSelected;
    }
    
    protected String getSeleccion()
    {
        return bandera;
    }    
  
    private int days[][];
    private JPanel daysPanel;
    private JPanel datePanel;
    private JPanel currentDatePanel;
    private String daySelected;
    private String monthSelected;
    private String yearSelected;
    private JPanel centrePanel;
    public JComboBox yearCombo;
    public JComboBox monthCombo;
    private Calendar calendar;
    protected SelectorFechaListener botonItemListener;
    private int showForYear;
    private int showForMonth;
    protected SelectorFechaComboListener comboBoxListener;
    private boolean showCalendarForDateSelected;
    private boolean showCurrentDate;
    private int firstDay;
    private JLabel currentDateLabel;
    private int startYear;
    private int endYear;
    public String bandera;

  private void jbInit() throws Exception
  {
    this.setBackground(new Color(112, 145, 204));
  }
  
}