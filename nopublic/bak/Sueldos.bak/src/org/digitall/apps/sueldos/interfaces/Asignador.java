package org.digitall.apps.sueldos.interfaces;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Asignador {
    private PanelReciboPreview pReciboPreview;
    private PanelAsignedHaberes pAsignedHaberes;
    private PanelAsignedDescuentos pAsignedDescuentos;
    
    public Asignador(PanelReciboPreview _pReciboPreview,PanelAsignedHaberes _pAsignedHaberes,PanelAsignedDescuentos _pAsignedDescuentos) {
	pReciboPreview = _pReciboPreview;
	pAsignedHaberes = _pAsignedHaberes;
	pAsignedDescuentos = _pAsignedDescuentos;
	cargarListener();
    }
    
    private void cargarListener(){
	pAsignedHaberes.getListAsignados().addListSelectionListener(new ListSelectionListener() {
					       public void valueChanged(ListSelectionEvent e) {
						   listHaberesAsignados_valueChanged(e);
					       }
					   }
	);
	pAsignedDescuentos.getListAsignados().addListSelectionListener(new ListSelectionListener() {
					       public void valueChanged(ListSelectionEvent e) {
						   listDescuentosAsignados_valueChanged(e);
					       }
					   }
	);
    }
    
    private void listHaberesAsignados_valueChanged(ListSelectionEvent e) {
	DefaultListModel listModel = new DefaultListModel();
	for(int i= 0; i < pAsignedHaberes.getListAsignados().getModel().getSize(); i++){
	    String elemento = pAsignedHaberes.getListAsignados().getModel().getElementAt(i).toString();
		listModel.addElement(elemento);
	}
	pReciboPreview.getListHaberes().setModel(listModel);
    }
    
    private void listDescuentosAsignados_valueChanged(ListSelectionEvent e) {
	DefaultListModel listModel = new DefaultListModel();
	for(int i= 0; i < pAsignedDescuentos.getListAsignados().getModel().getSize(); i++){
	    String elemento = pAsignedDescuentos.getListAsignados().getModel().getElementAt(i).toString();
		listModel.addElement(elemento);
	}
	pReciboPreview.getListDescuentos().setModel(listModel);
    }
}
