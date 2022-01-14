package org.digitall.projects.gdigitall.expedientes;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;

import javax.swing.UIManager;

import org.digitall.projects.gdigitall.lib.components.Login;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class simex {
    public simex() {
        //------------------------------RUTAS
        OP_Proced.setSeparador(System.getProperty("file.separator"));
        if (System.getProperty("os.name").equals("Linux")) {
            OP_Proced.setRuta("/tmp/SistemaOPSalta");
        } else {
            OP_Proced.setRuta("c:" + OP_Proced.getSeparador() + "SistemaOPSalta");
        }
        boolean success = (new File(OP_Proced.getRuta())).mkdirs();
        if (!success) {
        }
        //Proced.Mensaje("El Directorio ya existe, o no se pudo crear",""); }
        OP_Proced.setRutaIcono(OP_Proced.getRuta() + OP_Proced.getSeparador() + "iconos");
        success = (new File(OP_Proced.getRutaIcono())).mkdirs();
        if (!success) {
        }
        OP_Proced.setRutaGrafica(OP_Proced.getRuta() + OP_Proced.getSeparador() + "graficas");
        success = (new File(OP_Proced.getRutaGrafica())).mkdirs();
        if (!success) {
        }
        //Proced.Mensaje("El Directorio ya existe, o no se pudo crear",""); }
        OP_Proced.setRutaInforme(OP_Proced.getRuta() + OP_Proced.getSeparador() + "informes");
        success = (new File(OP_Proced.getRutaInforme())).mkdirs();
        if (!success) {
        }
        //Proced.Mensaje("El Directorio ya existe, o no se pudo crear",""); }
        //------------------------------------
        Login inicio = new Login("Sistema General", "Secretaría de Obras y Servicios Públicos", true);
        //  Login inicio = new Login("SIMEX Sistema Informático Municipal de Expedientes","Tribunal de Cuenta Municipal (T.C.M)");
        OP_Proced.CentraVentana(inicio);
        inicio.setResizable(false);
        inicio.setModal(true);
        inicio.setVisible(true);
        if (inicio.getValidado())
        //if (true)
        
        {
            /*Frame frame = new principal_simex();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
            );
            frame.setVisible(true);*/
        } else {
            System.exit(0);
        }
    }

    /**
   * 
   * @param args
   */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new simex();
    }
}
