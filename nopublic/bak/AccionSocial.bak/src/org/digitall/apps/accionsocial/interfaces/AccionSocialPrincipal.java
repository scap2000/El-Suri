package org.digitall.apps.accionsocial.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class AccionSocialPrincipal extends BasicPrimitivePanel {
    
    private BasicPanel jpNorte = new BasicPanel();
    private BasicPanel jpCentral = new BasicPanel();
    private BasicPanel jpSur = new BasicPanel();
    private BasicPanel jpSurEste = new BasicPanel();
    private BasicPanel jpContenedor = new BasicPanel();
    
    private TFInput tfBeneficiario = new TFInput(DataTypes.STRING,"Apellido y Nombres",false);
    private TFInput tfCuil = new TFInput(DataTypes.STRING,"CUIL.",false);

    private AcceptButton btnRecibirRecursos = new AcceptButton();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    
    private Coordinador coordinador = new Coordinador();
    private RecepcionarPrestacionesMgmt recepcionarPrestacionesMgmt;

    public AccionSocialPrincipal() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	jpContenedor.setLayout( borderLayout2 );
	this.setSize(new Dimension(845, 590));
	jpContenedor.setSize(new Dimension(845, 590));
	jpNorte.setBounds(new Rectangle(0, 0, 845, 65));
	jpNorte.setPreferredSize(new Dimension(845, 65));
	jpNorte.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpNorte.setLayout(null);
	jpSur.setBounds(new Rectangle(0, 525, 845, 50));
	jpSur.setPreferredSize(new Dimension(845, 50));
	jpSur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpSur.setLayout(null);
	jpCentral.setLayout(null);
	jpCentral.setSize(new Dimension(845, 475));
	jpCentral.setPreferredSize(new Dimension(845, 475));
	jpCentral.setBounds(new Rectangle(0, 65, 845, 475));
	tfBeneficiario.setBounds(new Rectangle(145, 5, 400, 35));
	tfBeneficiario.setEditable(false);
        btnNext.setBounds(new Rectangle(190, 10, 30, 25));
	btnNext.setSize(new Dimension(30, 25));
	btnNext.setFont(new Font("Dialog", 1, 11));
        btnNext.setToolTipText("Siguiente");
	btnNext.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnNext_actionPerformed(e);
		}
	    }
	);
        btnBack.setToolTipText("Atras");
	btnBack.setBounds(new Rectangle(105, 10, 30, 25));
	btnBack.setSize(new Dimension(30, 25));
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnBack_actionPerformed(e);
		}
	    }
	);
	jpSurEste.setBounds(new Rectangle(610, 1, 235, 48));
	jpSurEste.setBackground(new Color(185, 95, 0));
	jpSurEste.setLayout(null);
	jpSurEste.setSize(new Dimension(235, 48));
	btnFirst.setBounds(new Rectangle(20, 10, 30, 25));
	btnFirst.setSize(new Dimension(30, 25));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
        jpNorte.add(tfCuil, null);
        jpNorte.add(tfBeneficiario, null);
        jpSurEste.add(btnNext, null);
	jpSurEste.add(btnBack, null);
	jpSurEste.add(btnFirst, null);
	jpContenedor.add(jpNorte, borderLayout2.NORTH);
	jpContenedor.add(jpCentral, borderLayout2.CENTER);
	jpSur.add(jpSurEste, null);
	jpContenedor.add(jpSur, borderLayout2.SOUTH);
	this.add(jpContenedor, null);
        coordinador.setAccionSocialMgmt(this);
        tfCuil.setBounds(new Rectangle(575, 5, 120, 35));
        coordinador.start();
        tfCuil.setEditable(false);
        btnRecibirRecursos.setText("Recibir Recursos");
        btnRecibirRecursos.setToolTipText("Recibir Recursos");
        btnRecibirRecursos.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnRecibirRecursos_actionPerformed(e);
                                }

                            }
        );
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().getGeneralButtons().addButton(btnRecibirRecursos);
        getParentInternalFrame().setInfo("Asistente para la Administración de Acción Social, siga las instrucciones del panel de ayuda");
    }
    
    private void btnRecibirRecursos_actionPerformed(ActionEvent e) {
        recepcionarPrestacionesMgmt = new RecepcionarPrestacionesMgmt();
        ExtendedInternalFrame recepcionarPrestacionesMgmtContainer = new ExtendedInternalFrame("Recepcionar Recursos");
        recepcionarPrestacionesMgmtContainer.setCentralPanel(recepcionarPrestacionesMgmt);
        recepcionarPrestacionesMgmt.load();
        recepcionarPrestacionesMgmtContainer.show();
    }
    
    private void btnNext_actionPerformed(ActionEvent e) {
        coordinador.siguiente();
    }
    
    private void btnBack_actionPerformed(ActionEvent e) {
        coordinador.atras();
    }
    
    private void btnFirst_actionPerformed(ActionEvent e) {
        coordinador.inicio();
    }
    
    /**
     * Métodos públicos
     */
    
    /**
     * Queda visible el panel que se pasa por parámetro además de cargarse con los datos necesarios obtenido de los objetos del coordinador
     * @param _panel
     */
    public void setPanelCental(BasicPanel _panel) {
	_panel.setVisible(true);
    }
    
    public void addPanelCental(BasicPanel _panel) {
	jpCentral.add(_panel, null);
    }

    public TFInput gettfBeneficiario() {
	return tfBeneficiario;
    }

    public void setBtnNext(AssignButton btnNext) {
	this.btnNext = btnNext;
    }

    public AssignButton getBtnNext() {
	return btnNext;
    }

    public void setBtnFirst(FirstGridButton btnFirst) {
	this.btnFirst = btnFirst;
    }

    public FirstGridButton getBtnFirst() {
	return btnFirst;
    }

    public void setBtnBack(UnAssignButton btnBack) {
	this.btnBack = btnBack;
    }

    public UnAssignButton getBtnBack() {
	return btnBack;
    }

    public void settfCuil(TFInput tfCuil) {
        this.tfCuil = tfCuil;
    }

    public TFInput gettfCuil() {
        return tfCuil;
    }
}
