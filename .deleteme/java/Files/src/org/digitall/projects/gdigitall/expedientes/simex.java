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
 * simex.java
 *
 * */
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
