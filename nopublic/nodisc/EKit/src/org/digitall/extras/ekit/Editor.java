package org.digitall.extras.ekit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;

import java.util.EmptyStackException;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.data.Format;
import org.digitall.lib.resources.ResourcesManager;

public class Editor extends BasicDialog {

    String sDocument = null;
    String sStyleSheet = null;
    String sRawDocument = null;
    URL urlStyleSheet = null;
    boolean includeToolBar = true;
    boolean multibar = true;
    boolean includeViewSource = false;
    boolean includeMenuIcons = true;
    boolean modeExclusive = true;
    String sLang = null;
    String sCtry = null;
    boolean base64 = false;
    boolean debugOn = false;
    boolean spellCheck = false;
    /** KERNEL DE ESTE EDITOR ;)*/
    private EkitCore panel = new EkitCore(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar);
    private JMenuBar jMenuBar1 = new JMenuBar();
    private JToolBar jToolBarMain = new JToolBar();
    private JToolBar jToolBarFormat = new JToolBar();
    private JToolBar jToolBarStyle = new JToolBar();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private AcceptButton baceptar = new AcceptButton();
    private CloseButton bcerrar = new CloseButton();
    private String titulo = "", subtitulo = "", archivo = "";
    private boolean encabezado = false;
    protected String CodigoFuente = "";
    private int i = 1;
    private boolean nuevo = false;
    private String texto = "", textopase = "";

    /**
   * FORMULARIO DE EDITOR DE ARCHIVOS O PAGINAS HTML, ES UTILIZADO (POR AHORA), 
   * PARA REALIZAR EL TEXTO DE LOS PASES DE LOS DOCUMENTOS LEGALES. SE UTILIZO UN EDITOR POR LA NECESIDAD DE DAR FORMATO A LOS TEXTOS
   * 
   * @param TextoPase: TEXTO PURO DEL PASE
   * @param Nuevo: VBLE Q INDICA SI ES UN TEXTO NUEVO O A MODIFICAR
   * @param ArchivoHTML: NOMBRE DEL ARCHIVO HTML
   * @param Encabezado: TEXTO DEL ENCABEZADO
   * @param SubTitulo: SUB-TITULO DEL TEXTO
   * @param Titulo: TITULO DEL TEXTO
   */
    public Editor(String Titulo, String SubTitulo, boolean Encabezado, String ArchivoHTML, boolean Nuevo, String TextoPase) {
	try {
	    titulo = Titulo;
	    subtitulo = SubTitulo;
	    encabezado = Encabezado;
	    archivo = ArchivoHTML;
	    nuevo = Nuevo;
	    textopase = TextoPase;
	    if (includeToolBar)// PARAMETROS DE LA BARRA DE HERRAMIENTAS --> VER!!
	    {
		if (multibar) {
		    this.getContentPane().setLayout(new GridBagLayout());
		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.anchor = GridBagConstraints.NORTH;
		    gbc.gridheight = 1;
		    gbc.gridwidth = 1;
		    gbc.weightx = 1.0;
		    gbc.weighty = 0.0;
		    gbc.gridx = 1;
		    gbc.gridy = 1;
		    //this.add(panel.getToolBarMain(includeToolBar), gbc);
		    jToolBarMain = panel.getToolBarMain(includeToolBar);
		    gbc.gridy = 2;
		    //this.add(panel.getToolBarFormat(includeToolBar), gbc);
		    jToolBarFormat = panel.getToolBarFormat(includeToolBar);
		    gbc.gridy = 3;
		    //this.add(panel.getToolBarStyles(includeToolBar), gbc);
		    jToolBarStyle = panel.getToolBarStyles(includeToolBar);
		    gbc.anchor = GridBagConstraints.SOUTH;
		    gbc.fill = GridBagConstraints.BOTH;
		    gbc.weighty = 1.0;
		    gbc.gridy = 4;
		    this.getContentPane().add(panel, gbc);
		} else {
		    this.getContentPane().setLayout(new BorderLayout());
		    this.getContentPane().add(panel, BorderLayout.CENTER);
		    //this.add(panel.getToolBar(includeToolBar), BorderLayout.NORTH);
		    //  jToolBar =panel.getToolBar(includeToolBar);
		}
	    } else {
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);
	    }
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	baceptar.setMargin(new Insets(2, 5, 2, 14));
	bcerrar.setMargin(new Insets(2, 5, 2, 14));
	this.setSize(new Dimension(661, 543));
	this.getContentPane().setLayout(null);
	this.setTitle(titulo);
	panel.setBounds(new Rectangle(5, 5, 540, 405));
	jToolBarMain.setBounds(new Rectangle(1, 2, 35, 450));
	jToolBarFormat.setBounds(new Rectangle(35, 2, 35, 450));
	jToolBarStyle.setBounds(new Rectangle(5, 3, 540, 25));
	jPanel1.setBounds(new Rectangle(10, 10, 75, 455));
	jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	jPanel1.setLayout(null);
	jPanel2.setBounds(new Rectangle(90, 50, 550, 415));
	jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel2.setLayout(null);
	jPanel3.setBounds(new Rectangle(90, 10, 550, 30));
	jPanel3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	jPanel3.setLayout(null);
	baceptar.setToolTipText("Aceptar");
	baceptar.setBounds(new Rectangle(445, 485, 92, 25));
	baceptar.setMnemonic('a');
	baceptar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    baceptar_actionPerformed(e);
				}

			    }
	);
	bcerrar.setToolTipText("Cerrar");
	bcerrar.setBounds(new Rectangle(550, 485, 92, 25));
	bcerrar.setMnemonic('c');
	bcerrar.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bcerrar_actionPerformed(e);
			       }

			   }
	);
	this.getContentPane().add(bcerrar, null);
	this.getContentPane().add(baceptar, null);
	this.getContentPane().add(jPanel3, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(jPanel1, null);
	jPanel1.add(jToolBarFormat, null);
	jPanel1.add(jToolBarMain, null);
	jPanel2.add(panel, null);
	jPanel3.add(jToolBarStyle, null);
	panel.setDocumentText(textopase);
	HTMLTextoPase(nuevo);
	panel.loadDocument(new File(ResourcesManager.getReportsPath() + "\\" + File.pathSeparator + archivo + ".html"));
    }

    private void baceptar_actionPerformed(ActionEvent e) {
	HTMLTextoPase(false);
	CodigoFuente = panel.getDocumentText();
	System.out.println(panel.getDocumentText());
	System.out.println(panel.getDocumentBody());
	this.dispose();
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
	CodigoFuente = "";
	this.dispose();
    }

    /**
   * METODO QUE GENERA UN DOCUMENTOEN BLANCO
   * @param htmlFile: ARCHIVO DONDE SE GENERARA EL DOCUMENTO
   */
    private void NuevoDoc(FileWriter htmlFile) {
	try {
	    htmlFile.write("<HTML>\n");
	    htmlFile.write("<HEAD>\n");
	    htmlFile.write("<TITLE>" + titulo + "</TITLE>\n");
	    htmlFile.write("</HEAD>\n");
	    htmlFile.write("<BODY>\n");
	    // ENCABEZADO
	    if (encabezado) {
		htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
		htmlFile.write("<TR>\n");
		//CHECK!!!!
		//TODO
		//TASK
		htmlFile.write("<TD WIDTH=10%><img src='" + ResourcesManager.getIconsPath() + "\\" + File.pathSeparator + "logo.jpg' height=40 width=40></TD>\n");
		htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='#" + Format.color2Hex(Color.BLUE) + "'><B>" + titulo + "</B></FONT><BR>\n");
		htmlFile.write("<TD WIDTH=10%><img src='" + ResourcesManager.getIconsPath() + "\\" + File.pathSeparator + "logo.jpg' height=40 width=40></TD>\n");
		htmlFile.write("</TR>\n");
		htmlFile.write("</TABLE>\n");
	    }
	    // SUBTITULO
	    if (subtitulo.length() > 0) {
		htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
		htmlFile.write("<TR>\n");
		htmlFile.write("<TD ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='#" + Format.color2Hex(Color.BLUE) + "'><B>" + subtitulo + "</B></FONT><BR>\n");
		htmlFile.write("</TR>\n");
		htmlFile.write("</TABLE><BR>\n");
	    }
	} catch (EmptyStackException e) {
	    e.printStackTrace();
	} catch (IOException x) {
	    x.printStackTrace();
	}
    }

    /**
   * METODO PARA ASIGNAR EL TEXTO PURO A LA PAGINA, SI nuevo = TRUE GENERA UN DOCUMENTO EN BLANCO
   * @param nuevo
   */
    private void HTMLTextoPase(boolean nuevo) {
	try {
	    String htmlPath = ResourcesManager.getReportsPath() + "\\" + archivo + ".html";
	    FileWriter htmlFile = new FileWriter(htmlPath, false);
	    BufferedWriter log = new BufferedWriter(htmlFile);
	    if (nuevo) {
		NuevoDoc(htmlFile);
	    } else {
		//   System.out.println("A:" + panel.getDocumentBody().length());      
		if (panel.getDocumentBody().length() < 30) {
		    //   System.out.println("Generar nuevo");
		    NuevoDoc(htmlFile);
		} else {
		    htmlFile.write(panel.getDocumentBody());
		    /** cuerpo del HTML **/
		}
	    }
	    htmlFile.write("</BODY>\n");
	    htmlFile.write("</HTML>\n");
	    htmlFile.close();
	} catch (EmptyStackException e) {
	    e.printStackTrace();
	} catch (IOException x) {
	    x.printStackTrace();
	}
    }

    public String getCodigoFuente() {
	return CodigoFuente;
    }

}
