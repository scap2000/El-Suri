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
 * TaskManList.java
 *
 * */
package org.digitall.apps.taskman.interfases;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import org.digitall.apps.taskman.TaskManEnvironment;
import org.digitall.apps.taskman.TaskManMain;
import org.digitall.apps.taskman.classes.Tareas;
import org.digitall.apps.taskman.classes.TiemposPorTarea;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;


public class TaskManList extends BasicPrimitivePanel {
    
    private BasicPanel pContenedor = new BasicPanel();
    private TFInput tfBuscar = new TFInput(DataTypes.STRING, "Buscar", false);
    private CBInput cbTareas = new CBInput(0, "Tareas");
    private BasicCheckBox chkMisTareas = new BasicCheckBox();
    private AddComboButton btnAddTarea = new AddComboButton();
    private TFInput tfPorcentaje = new TFInput(DataTypes.DOUBLE, "% (0-100)", false);
    private BasicButton btnPlay = new BasicButton();
    private BasicButton btnStop = new BasicButton();
    private TiemposPorTarea tiempoPorTarea = new TiemposPorTarea();
    private Tareas tarea = new Tareas();
    private Timer timerTarea;
    private Thread threadCronometro;
    
    private int tiempoActualizacion = 360000; //cada 6 minutos actualiza el tiempo ejecutado
    private int minutos=0;
    private int segundos=0; 
    private int horas=0; 
    private int idTareaCorriendo = -1;
    private double porcentaje = 0;
    private String tareaSeleccionada = "";
    private String tareaPorIniciar = "";
    private boolean trabajando = false;
    private boolean iniciandoTarea = false;
    private TaskManMain taskManMain;
    private String selectedItem = "";

    public TaskManList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public TaskManList(TaskManMain _taskManMain) {
        taskManMain = _taskManMain;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	/** Corroborar que haya conexión con el servidor
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
        this.setLayout(null);
        this.setSize(new Dimension(696, 70));
        this.setTitle("Administrador de Tareas");
        pContenedor.setBounds(new Rectangle(0, 0, 690, 70));
        pContenedor.setLayout(null);
        pContenedor.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Opciones")));
        tfBuscar.setBounds(new Rectangle(10, 20, 170, 35));
        tfBuscar.getTextField().addKeyListener(new KeyAdapter() {

		public void keyReleased(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER)
			loadCombo();

		}
	    }
	);
        cbTareas.setBounds(new Rectangle(190, 20, 195, 35));
        cbTareas.setCombo(new JCombo(){
                @Override
                public void update() {
                    loadCombo();
                }
            });
        cbTareas.addItemListener(new ItemListener() {
                                                public void itemStateChanged(ItemEvent e) {
                                                    if (e.getStateChange() == ItemEvent.SELECTED) {
                                                        if (cbTareas.getSelectedIndex() > -1) {
                                                            actualizarPorcentaje();
                                                        }
                                                    }
                                                }

                                            }
                );
        chkMisTareas.setText("Mis Tareas");
        chkMisTareas.setBounds(new Rectangle(390, 35, 95, 20));
        chkMisTareas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    chkMisTareas_actionPerformed(e);
                }
            });
        btnAddTarea.setBounds(new Rectangle(485, 35, 35, 20));
        btnAddTarea.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddTarea_actionPerformed(e);
                }
            });
        tfPorcentaje.setBounds(new Rectangle(525, 20, 65, 35));
        btnPlay.setText("Play");
        btnPlay.setBounds(new Rectangle(595, 35, 45, 21));
        btnPlay.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnPlay_actionPerformed(e);
                }
            });
        btnStop.setText("Stop");
        btnStop.setBounds(new Rectangle(640, 36, 45, 20));
        btnStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnStop_actionPerformed(e);
                }
            });
        pContenedor.add(btnStop, null);
        pContenedor.add(btnPlay, null);
        pContenedor.add(tfPorcentaje, null);
        pContenedor.add(btnAddTarea, null);
        pContenedor.add(chkMisTareas, null);
        pContenedor.add(cbTareas, null);
        pContenedor.add(tfBuscar, null);
        this.add(pContenedor, null);
        loadCombo();
        btnStop.setEnabled(false);
        pContenedor.setBounds(new Rectangle(0, 0, 690, 65));
        timerTarea = new Timer(tiempoActualizacion, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            saveTaskData();
                        }
        });
        btnAddTarea.setToolTipText("Nueva tarea");  
        tfPorcentaje.setEditable(false);
	
    }

    private void btnAddTarea_actionPerformed(ActionEvent e) {
        showNewTaskPanel();
        
        /*
        NewTask _newTaskPanel = new NewTask();
	JOptionPane _op = new JOptionPane();
	_newTaskPanel.setPreferredSize(_newTaskPanel.getSize());
	_op.showConfirmDialog(Environment.getActiveDesktop(), _newTaskPanel, "Nueva tarea", JOptionPane.DEFAULT_OPTION);
        loadCombo();
	*/
	
        /*
	JOptionPane _op = new JOptionPane();
	TaskManList _tm = new TaskManList();
	
	_tm.setPreferredSize(_tm.getSize());
	_op.showInternalConfirmDialog(Environment.getActiveDesktop(), _tm, "Cadena", JOptionPane.OK_CANCEL_OPTION);
	*/
    }
    
    private void showNewTaskPanel() {
        ExtendedInternalFrame _newTaskInternalFrame = new ExtendedInternalFrame("Nueva tarea");
        NewTask _newTaskPanel = new NewTask();
        _newTaskInternalFrame.setCentralPanel(_newTaskPanel);
        _newTaskInternalFrame.setClosable(true);
        _newTaskInternalFrame.setDesktop(Environment.getActiveDesktop());
        _newTaskInternalFrame.setIconifiable(false);
        _newTaskInternalFrame.show();    
    }

    private void chkMisTareas_actionPerformed(ActionEvent e) {
        loadCombo();  
    }

    private void btnPlay_actionPerformed(ActionEvent e) {
        tareaPorIniciar = cbTareas.getSelectedItem().toString();
        iniciarTarea();
    }

    private void btnStop_actionPerformed(ActionEvent e) {
        detenerTarea();
    }

    private void loadCombo() {
        if (cbTareas.getCombo().getItemCount() > 0) {
            selectedItem = cbTareas.getSelectedItem().toString();    
        }
        String usuario = "";
        if(chkMisTareas.isSelected()){
            usuario = Environment.sessionUser;
        }
        String param = "'"+tfBuscar.getString()+ "','" + usuario+ "'";
        cbTareas.loadJCombo(TaskManEnvironment.libSQLMini.exFunction("taskman.getAlltareas", param));
        cbTareas.setSelectedValue(selectedItem);
        if(cbTareas.getCombo().getItemCount() > 0){
            btnPlay.setEnabled(true);
            actualizarPorcentaje();
        } else {
            btnPlay.setEnabled(false);
            //tfPorcentaje.setValue("");
        }
    }
    
    private void actualizarPorcentaje() {
        if(!trabajando){
            tfPorcentaje.setValue(cbTareas.getSelectedValueRef());
            porcentaje = tfPorcentaje.getDouble();
        }
    }
    
    //Método que inicia una tarea. En caso de estar corriendo una tarea
    //intenta terminar la tarea actual y luego iniciar la nueva tarea
    private void iniciarTarea() {
        //se indica el intento de iniciar nueva tarea
        iniciandoTarea = true;
        if (!trabajando) {
            cbTareas.setSelectedValue(tareaPorIniciar);
            idTareaCorriendo = Integer.parseInt(cbTareas.getSelectedValue().toString());
            tareaSeleccionada = cbTareas.getSelectedItem().toString();
            tarea.setIdtarea(idTareaCorriendo);
            tarea.retrieveData();
            porcentaje = tarea.getPorcentaje();
            saveTaskData();
            timerTarea.start();
            iniciarCronometro();
            habilitarDeshabilitarComponentes(true);
        } else {
            detenerTarea();
            //si se detuvo la tarea que estaba corriendo, intenta iniciar la nueva tarea
            if(iniciandoTarea){
                iniciarTarea();  
            }
        }
    }
    
    private void detenerTarea() {
        Object _valorObtenido = Advisor.getValue(DataTypes.DOUBLE, "Ingrese el porcentaje de avance de la tarea\n \"" + tareaSeleccionada + "\"");
        if(!(_valorObtenido == null)){
            double _porcentaje = Double.parseDouble(_valorObtenido.toString());
            if(control(_porcentaje)){
                timerTarea.stop();
                //se resguarda el porcentaje para su posterior almacenamiento
                porcentaje = _porcentaje;
                saveTaskData();
                habilitarDeshabilitarComponentes(false);
                tiempoPorTarea = new TiemposPorTarea();
                detenerCronometro();
                loadCombo();
            } else {
                //se indica que no se pudo detener la tarea que estaba en ejecución
                //por lo tanto no se puede iniciar la nueva tarea (en caso de que se haya intentado iniciar la nueva tarea)
                iniciandoTarea = false;
            }      
        } else {
            iniciandoTarea = false;
        }
    }
    
    private void saveTaskData() {
        //tiempoPorTarea.setIdTarea(Integer.parseInt(cbTareas.getSelectedValue().toString()));
        tiempoPorTarea.setIdTarea(idTareaCorriendo);
        tiempoPorTarea.setPorcentaje(porcentaje);
        tiempoPorTarea.saveData();
        tiempoPorTarea.retrieveData();
        if(!(tiempoPorTarea.getFechaHoraFin() == null)){//la primera vez la fechahorafin es igual a null
            String params = "" + tiempoPorTarea.getidTiempoPorTarea() + "," + tarea.getTiempoEjecutado();
            TaskManEnvironment.libSQLMini.getInt("taskman.settiempoejecutado",params);
        }
    }

    private boolean control(double _porcentaje) {
        boolean retorno = true;
        if (_porcentaje > 100) {
            Advisor.messageBox("El porcentaje de avance de la tarea no puede ser mayor a 100% ","Mensaje");
            retorno = false;
        } else {
            if ( _porcentaje <= tiempoPorTarea.getPorcentaje()) {
                Advisor.messageBox("El porcentaje de avance de la tarea debe ser mayor que el porcentaje actual ","Mensaje");
                retorno = false;
            }
        }
        return retorno;
    }
    
    private boolean esNumero(String _number){
        boolean retorno = true;
        try{
            Double.parseDouble(_number);
        } catch( NumberFormatException e) {
            retorno = false;
        }
        return retorno;
    }
    
    private void iniciarCronometro(){
        threadCronometro = new Thread(){
            public void run() {
                for(;;) {
                    if(segundos==59) { 
                        segundos=-1; 
                        minutos++; 
                    }
                    if(minutos==59) {
                        minutos=0; 
                        horas++; 
                    }
                    segundos++;
                    actualizarTiempo(completarDigito(horas)+":"+completarDigito(minutos)+":"+completarDigito(segundos) + " (Tarea: " + tareaSeleccionada + ") - Administrador de Tareas");
                    try {
                        threadCronometro.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }    
            }
        };
        threadCronometro.start();
    }
    
    private void detenerCronometro() {
        threadCronometro.stop();
        inicializarCronometro();
    }
    
    private void actualizarTiempo(String _tiempo){
        //this.setTitle(_tiempo);
        //taskManMain.setTitle(_tiempo);
        if(taskManMain == null){
            this.setTitle(_tiempo);    
            this.getParentInternalFrame().setInfo(_tiempo);
        } else {
            taskManMain.setTitle(_tiempo);
        }
    }
    
    private void inicializarCronometro(){
        segundos = 0;
        minutos = 0;
        horas = 0;
    }
    
    private String completarDigito(int _tiempo){
        String tiempo = ""+_tiempo;
        if(tiempo.length() == 1){
            tiempo = "0" + tiempo;
        }
        return tiempo;
    }
    
    private void habilitarDeshabilitarComponentes(boolean _enabled){
        btnStop.setEnabled(_enabled);
        //tfPorcentaje.setEditable(!_enabled);
        btnAddTarea.setEnabled(!_enabled);
        trabajando = _enabled;
    }
}

