package org.digitall.lib.misc;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;

import java.util.EmptyStackException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.icons.IconTypes;

public class HTMLBrowser extends BasicDialog {

    private PrintableEditorPane htmled;
    private BasicScrollPane htmlpanel = new BasicScrollPane();
    private BasicButton bimprimir = new BasicButton(IconTypes.PRINT_ICON_16x16);
    private CloseButton bcerrar = new CloseButton();
    private String command = "", Archivo = "";
    private BasicButton bvisorexterno = new BasicButton();

    public HTMLBrowser(String file) throws FileNotFoundException {
	try {
	    Archivo = file;
	    //      System.out.println("HTMLBrowser: " + Archivo);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//htmled = new PrintableEditorPane(new URL("http","192.168.2.6",""));
	htmled = new PrintableEditorPane(new URL("file", "", Archivo));
	htmled.setEditable(false);
	this.setSize(new Dimension(800, 575));
	this.getContentPane().setLayout(null);
	this.setTitle("Visor de Informes");
	htmled.setText("");
	htmlpanel.setBounds(new Rectangle(5, 5, 780, 505));
	bimprimir.setText("Imprimir");
	bimprimir.setBounds(new Rectangle(5, 515, 108, 25));
	bimprimir.setMnemonic('I');
	bimprimir.setEnabled(false);
	bcerrar.setText("Cerrar");
	bcerrar.setBounds(new Rectangle(693, 515, 92, 25));
	bcerrar.setMnemonic('C');
	bvisorexterno.setText("Visor Externo p/imprimir");
	bvisorexterno.setBounds(new Rectangle(120, 515, 210, 25));
	bvisorexterno.setMnemonic('V');
	htmlpanel.getViewport().add(htmled, null);
	this.getContentPane().add(bvisorexterno, null);
	this.getContentPane().add(bcerrar, null);
	this.getContentPane().add(bimprimir, null);
	this.getContentPane().add(htmlpanel, null);
	htmled.addHyperlinkListener(new HyperlinkListener() {

		    public void hyperlinkUpdate(HyperlinkEvent link) {
			if (link.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			    try {
				htmled.setPage(link.getURL());
			    } catch (EmptyStackException e) {
				System.out.println("ErrorSTack01");
			    } catch (IOException x) {
				Advisor.messageBox("Error: no se pudo abrir el archivo " + link.getURL().toExternalForm() + "\n" + x.getMessage(), "true");
			    }
			}
		    }

		});
	bimprimir.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bimprimir_actionPerformed(e);
		    }

		});
	bcerrar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bcerrar_actionPerformed(e);
		    }

		});
	bvisorexterno.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bvisorexterno_actionPerformed(e);
		    }

		});
	//  panelCenter.add(htmlpanel, null);
	//  this.getContentPane().add(panelCenter, BorderLayout.NORTH);
    }

    private void benviar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bimprimir_actionPerformed(ActionEvent e) {
	htmled.doPrintActions();
    }

    private void bvisorexterno_actionPerformed(ActionEvent e) {
	try {
	    if (System.getProperty("os.name").equals("Linux")) {
		command = "konqueror " + Archivo;
	    } else {
		command = "c:" + File.separator + "archivos de programa" + File.separator + "internet explorer" + File.separator + "iexplore " + Archivo;
	    }
	    //      System.out.println(command);
	    Process child = Runtime.getRuntime().exec(command);
	} catch (EmptyStackException x) {
	    System.out.println("ErrorSTack02");
	} catch (IOException x) {
	    Advisor.messageBox("Error al intentar utilizar el visor externo", "true");
	}
    }

}
