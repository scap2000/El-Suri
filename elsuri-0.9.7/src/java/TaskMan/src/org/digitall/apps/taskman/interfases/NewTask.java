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
 * NewTask.java
 *
 * */
package org.digitall.apps.taskman.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.taskman.classes.Tareas;
import org.digitall.apps.taskman.classes.errclasses.ErrTareas;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;


public class NewTask extends BasicPrimitivePanel {
//public class NewTask extends JDialog {

    private BasicPanel pContenedor = new BasicPanel();
    private TFInput tfNombreTarea = new TFInput(DataTypes.STRING, "Nombre", false);
    private BasicLabel lbDescripcion = new BasicLabel("Descripción");
    private JArea taDescripcion = new JArea();
    private TFInput tfTiempoEstimado = new TFInput(DataTypes.DOUBLE, "Tiempo estimado (Hs)", false);
    private SaveDataButton btnSaveData = new SaveDataButton();
    private CancelDataButton btnClose = new CancelDataButton();
    private Tareas tarea = new Tareas();
    private ErrTareas errorTarea;

    public NewTask() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(410, 346));
        this.setTitle("Nueva tarea");
        pContenedor.setBounds(new Rectangle(5, 5, 395, 300));
        pContenedor.setLayout(null);
        pContenedor.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Datos de tarea")));
        tfNombreTarea.setBounds(new Rectangle(10, 20, 375, 35));
        lbDescripcion.setBounds(new Rectangle(10, 60, 80, 20));
        taDescripcion.setBounds(new Rectangle(10, 80, 375, 170));
        tfTiempoEstimado.setBounds(new Rectangle(10, 255, 135, 35));
        //btnSaveData.setBounds(new Rectangle(330, 310, 35, 25));
        btnSaveData.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnSaveData_actionPerformed(e);
                }
            });
        //btnClose.setBounds(new Rectangle(365, 310, 35, 25));
        btnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnClose_actionPerformed(e);
                }
            });
        pContenedor.add(tfTiempoEstimado, null);
        pContenedor.add(taDescripcion, null);
        pContenedor.add(lbDescripcion, null);
        pContenedor.add(tfNombreTarea, null);
        pContenedor.setLayout(null);
        this.add(pContenedor, BorderLayout.CENTER);
        this.addButton(btnClose);
        this.addButton(btnSaveData);
    }

    private void btnSaveData_actionPerformed(ActionEvent e) {
        saveTaskData();
        
    }

    private void saveTaskData() {
        if ( control() ) {
            tarea.setNombreTarea(tfNombreTarea.getString());
            tarea.setObservaciones(taDescripcion.getText());
            tarea.setTiempoEstimado(tfTiempoEstimado.getDouble());
            if(tarea.saveData() != -1){
                getParentInternalFrame().close();
            } else {
                Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
            }
        } else {
            errorTarea.showMessage();
        }
        
    }

    private boolean control() {
        errorTarea = new ErrTareas();
        boolean retorno = false;
        if(!tfNombreTarea.getString().equals("")){
            if(!(tfTiempoEstimado.getDouble() == 0)){
                retorno = true;
            } else {
                errorTarea.setError(errorTarea.tiempoEstimado);
            }
        } else {
            errorTarea.setError(errorTarea.nombreTarea);
        }
        return retorno;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
        getParentInternalFrame().close();
    }
}
