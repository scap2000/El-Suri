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
