package org.digitall.apps.taxes092.interfases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.taxes092.classes.Coordinador;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class TaxesMain092 extends BasicPrimitivePanel {

    private Coordinador coordinador = new Coordinador();
    private BasicPanel panelNorte = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelSur = new BasicPanel();
    private BasicPanel panelContenedor = new BasicPanel();
    private TFInput tfContribuyente = new TFInput(DataTypes.STRING,"Contribuyente",false);
    private TFInput tfImpuesto = new TFInput(DataTypes.STRING,"Impuesto",false);
    private TFInput tfCatastroDominio = new TFInput(DataTypes.STRING,"Catastro/Dominio",false);
    private AssignButton btnNext = new AssignButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private UnAssignButton btnBack = new UnAssignButton();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel panelSurEste = new BasicPanel();
    private Color colorAlerta = Color.red;
    private Timer timerAlerta;
    private BasicPanel panelAlerta;
    private BasicTextArea taDescripcionProblema = new BasicTextArea();

    public TaxesMain092() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
        panelAlerta = new BasicPanel() {
        
            @Override
            public void paint(Graphics _graphics) {
                _graphics.setColor(colorAlerta);
                _graphics.fillOval(15, 02, 35, 35);
                _graphics.setColor(Color.black);
                _graphics.drawOval(15, 02, 35, 35);
                super.paintComponents(_graphics);
            }
        };
        timerAlerta = new Timer(200, new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                colorAlerta= (colorAlerta == Color.red?Color.yellow:Color.red);
                panelAlerta.repaint();
                panelAlerta.getParent().repaint();
            }
            
        });
	this.setLayout( null );
        panelAlerta.setBounds(new Rectangle(0, 5, 605, 40));
        taDescripcionProblema.setBounds(new Rectangle(70, 0, 535, 35));
        taDescripcionProblema.setVisible(false);
        taDescripcionProblema.setEditable(false);
        panelAlerta.setLayout(null);
        panelAlerta.setVisible(false);
	panelContenedor.setLayout( borderLayout2 );
	this.setSize(new Dimension(845, 590));
	panelContenedor.setSize(new Dimension(845, 590));
	panelNorte.setBounds(new Rectangle(0, 0, 845, 65));
	panelNorte.setPreferredSize(new Dimension(845, 65));
	panelNorte.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelNorte.setLayout(null);
	panelSur.setBounds(new Rectangle(0, 525, 845, 50));
	panelSur.setPreferredSize(new Dimension(845, 50));
	panelSur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        panelAlerta.add(taDescripcionProblema, null);
        panelSur.add(panelAlerta, null);
	panelSur.setLayout(null);
	panelCentral.setLayout(null);
	panelCentral.setSize(new Dimension(845, 475));
	panelCentral.setPreferredSize(new Dimension(845, 475));
	panelCentral.setBounds(new Rectangle(0, 65, 845, 475));
	tfContribuyente.setBounds(new Rectangle(15, 5, 275, 35));
	tfContribuyente.setEditable(false);
	tfImpuesto.setBounds(new Rectangle(320, 5, 275, 35));
	tfImpuesto.setEditable(false);
	tfCatastroDominio.setBounds(new Rectangle(630, 5, 140, 35));
	tfCatastroDominio.setEditable(false);
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
	panelSurEste.setBounds(new Rectangle(610, 1, 235, 48));
	panelSurEste.setBackground(new Color(185, 95, 0));
	panelSurEste.setLayout(null);
	panelSurEste.setSize(new Dimension(235, 48));
	btnFirst.setBounds(new Rectangle(20, 10, 30, 25));
	btnFirst.setSize(new Dimension(30, 25));
	btnFirst.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFirst_actionPerformed(e);
		}
	    }
	);
	panelNorte.add(tfCatastroDominio, null);
	panelNorte.add(tfImpuesto, null);
	panelNorte.add(tfContribuyente, null);
	panelSurEste.add(btnNext, null);
	panelSurEste.add(btnBack, null);
	panelSurEste.add(btnFirst, null);
	panelContenedor.add(panelNorte, borderLayout2.NORTH);
	panelContenedor.add(panelCentral, borderLayout2.CENTER);
	panelSur.add(panelSurEste, null);
	panelContenedor.add(panelSur, borderLayout2.SOUTH);
	this.add(panelContenedor, null);
	coordinador.setTaxesMgmt(this);
	coordinador.start();
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
    public void setPanelCental(BasicPanel _panel){
	_panel.setVisible(true);
    }
    
    public void addPanelCental(BasicPanel _panel){
	panelCentral.add(_panel, null);
    }

    public void setCoordinador(Coordinador _coordinador) {
	coordinador = _coordinador;
    }

    public Coordinador getCoordinador() {
	return coordinador;
    }

    public TFInput getTfContribuyente() {
	return tfContribuyente;
    }

    public TFInput getTfImpuesto() {
	return tfImpuesto;
    }

    public TFInput getTfCatastroDominio() {
	return tfCatastroDominio;
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
    
    //método que muestra información del problema del CATASTRO seleccionado
    public void mostrarProblema(){
        panelAlerta.setVisible(false);
        timerAlerta.stop();
        taDescripcionProblema.setVisible(false);
        if (coordinador.getBien().esCatastro()) {
            ResultSet result = LibSQL.exFunction("taxes.getUltimoProblemaxCatastro", coordinador.getBien().getIdBien());
            try {
                if (result.next()) {
                    if (result.getBoolean("esproblema")) {
                        taDescripcionProblema.setText(result.getString("problema"));
                        panelAlerta.setVisible(true);
                        timerAlerta.start();
                        timerAlerta.setDelay(400);
                        taDescripcionProblema.setVisible(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }    
        }
    }
}
