package org.digitall.apps.accionsocial.interfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import org.digitall.apps.accionsocial.classes.Coordinador;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.sql.LibSQL;


public class PopupAltaGrupos extends JPopupMenu{

    // se ocupan items para cada menu o columna

    private JMenuItem miEmbarazos  = new JMenuItem("Embarazos");
    private JMenuItem miCeliacos  = new JMenuItem("Celíacos");
    private JMenuItem miMenores = new JMenuItem("Menores");
    private JMenuItem miPersonaEscasoRecurso = new JMenuItem("Escasos Recursos");
    private JMenuItem miTBC = new JMenuItem("TBC");
    private JMenuItem miExit = new JMenuItem("Salir");
    Coordinador coordinador;
    
    public PopupAltaGrupos(Coordinador _coordinador) {
        coordinador = _coordinador;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBackground(new Color(0, 132, 198));
        this.add(miCeliacos);
	this.add(miEmbarazos);
        this.add(miPersonaEscasoRecurso);
	this.add(miMenores);
        this.add(miTBC);
        this.add(miExit);
	miEmbarazos.setForeground(Color.white);
	miCeliacos.setForeground(Color.white);
	miMenores.setForeground(Color.white);
        miTBC.setForeground(Color.white);
        miPersonaEscasoRecurso.setForeground(Color.white);
	miExit.setForeground(Color.white);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	miEmbarazos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickEmbarazos();
		}
	    });
	miCeliacos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickCeliacos();
		}
	    });
	miMenores.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    clickMenores();
		}
	    });
        miPersonaEscasoRecurso.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickPersonasEscasosRecursos();
                }
            });
        miTBC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickTBC();
                }
            });
        
	miExit.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
		    clickExit(e);              
		}
	    }
	});
        controlOpciones();
    }
    
    private void clickEmbarazos() {
        ExtendedInternalFrame panelEmbarazoContainer = new ExtendedInternalFrame("Alta en Grupo Embarazo");
        PanelEmbarazo panelEmbarazo = new PanelEmbarazo(coordinador); // se envía el coordinador
        panelEmbarazoContainer.setCentralPanel(panelEmbarazo);
        panelEmbarazoContainer.show();
    }
    
    private void clickCeliacos() {
        ExtendedInternalFrame panelCeliacosContainer = new ExtendedInternalFrame("Alta en Grupo Celíacos");
        PanelCeliacos panelCeliacos = new PanelCeliacos(coordinador); // se envía el coordinador
        panelCeliacosContainer.setCentralPanel(panelCeliacos);
        panelCeliacosContainer.show();
    }
    
    private void clickMenores() {
        ExtendedInternalFrame panelMenorContainer = new ExtendedInternalFrame("Alta en Grupo Menores");
        PanelMenor panelCeliacos = new PanelMenor(coordinador); // se envía el coordinador
        panelMenorContainer.setCentralPanel(panelCeliacos);
        panelMenorContainer.show();
    }
    
    private void clickTBC() {
        ExtendedInternalFrame panelTBCContainer = new ExtendedInternalFrame("Alta en Grupo TBC");
        PanelTBC panelTBC = new PanelTBC(coordinador); // se envía el coordinador
        panelTBCContainer.setCentralPanel(panelTBC);
        panelTBCContainer.show();
    }
    
    private void clickPersonasEscasosRecursos() {
        ExtendedInternalFrame panelPersonaEscasosRecursosContainer = new ExtendedInternalFrame("Alta en Grupo Escasos Recursos");
        PanelPersonaEscasosRecursos panelPersonaEscasosRecursos = new PanelPersonaEscasosRecursos(coordinador); // se envía el coordinador
        panelPersonaEscasosRecursosContainer.setCentralPanel(panelPersonaEscasosRecursos);
        panelPersonaEscasosRecursosContainer.show();
    }
    
    private void clickExit(MouseEvent e) {
	
    }
    
    public int getAlto(){
	return(this.getComponentCount() * 21);
    }

    private void controlOpciones() {
        if ((coordinador.getPersona().getSexo().equals("M"))||(coordinador.getPersona().getSexo().trim().equals(""))) {
            miEmbarazos.setEnabled(false);
        } else {
            miEmbarazos.setEnabled(true);
        }
        ResultSet resultado = LibSQL.exFunction("accionsocial.getallgruposporpersona",""+coordinador.getPersona().getIdPersona());
        try {
            while (resultado.next()) {
                if (resultado.getInt("idceliaco") != -1) {
                    miCeliacos.setEnabled(false);
                }
                if (resultado.getInt("idembarazo") != -1) {
                    miEmbarazos.setEnabled(false);
                }
                if (resultado.getInt("idmenor") != -1) {
                    miMenores.setEnabled(false);
                }
                if (resultado.getInt("idpersonaescasosrecursos") != -1) {
                    miPersonaEscasoRecurso.setEnabled(false);
                }
                if (resultado.getInt("idtbc") != -1) {
                    miTBC.setEnabled(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

