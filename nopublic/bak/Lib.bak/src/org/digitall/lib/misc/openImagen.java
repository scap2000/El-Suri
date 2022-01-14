package org.digitall.lib.misc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;

import java.sql.PreparedStatement;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.sql.LibSQL;

public class openImagen extends BasicDialog {

    private BasicLabel panel = new BasicLabel();
    private FindButton Abrir = new FindButton();
    private AcceptButton Insertar = new AcceptButton();
    private CloseButton Cerrar = new CloseButton();
    private BasicLabel tamaniobytes = new BasicLabel();
    private BasicLabel nombrecompleto = new BasicLabel();
    private BufferedImage image = null;
    private BasicLabel tamaniopixels = new BasicLabel();
    private String nameimg = "";
    private BasicLabel nombre = new BasicLabel();

    public openImagen(String nameImg) {
	this.setSize(new Dimension(400, 300));
	this.getContentPane().setLayout(null);
	try {
	    nameimg = nameImg;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panel = new BasicLabel() {

		    public void paintComponent(Graphics g) {
			if (image != null) {
			    panel.setHorizontalAlignment(CENTER);
			    panel.setVerticalAlignment(CENTER);
			    panel.setHorizontalAlignment(SwingConstants.CENTER);
			    g.setColor(Color.white);
			    g.fillRect(0, 0, this.getWidth(), this.getHeight());
			    g.drawImage(image, panel.getWidth() / 2 - image.getWidth() / 2, panel.getHeight() / 2 - image.getHeight() / 2, image.getWidth(), image.getHeight(), this);
			    super.paintComponent(g);
			}
		    }

		};
	this.getContentPane().setLayout(null);
	this.setTitle("Cargador de Imagenes");
	this.setSize(new Dimension(461, 311));
	panel.setBounds(new Rectangle(10, 5, 215, 220));
	panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panel.setHorizontalTextPosition(SwingConstants.CENTER);
	Abrir.setText("Abrir");
	Abrir.setBounds(new Rectangle(160, 250, 88, 25));
	Abrir.setMnemonic('A');
	Abrir.setMargin(new Insets(2, 5, 2, 14));
	Abrir.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			Abrir_actionPerformed(e);
		    }

		});
	Insertar.setText("Guardar");
	Insertar.setBounds(new Rectangle(255, 250, 94, 25));
	Insertar.setMnemonic('I');
	Insertar.setMargin(new Insets(2, 5, 2, 14));
	Insertar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			Insertar_actionPerformed(e);
		    }

		});
	Cerrar.setText("Cerrar");
	Cerrar.setBounds(new Rectangle(355, 250, 88, 25));
	Cerrar.setMnemonic('C');
	Cerrar.setMargin(new Insets(2, 5, 2, 14));
	Cerrar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			Cerrar_actionPerformed(e);
		    }

		});
	tamaniobytes.setText("Tamaño:");
	tamaniobytes.setBounds(new Rectangle(230, 190, 215, 15));
	tamaniobytes.setHorizontalTextPosition(SwingConstants.CENTER);
	tamaniobytes.setHorizontalAlignment(SwingConstants.CENTER);
	nombrecompleto.setText("Nombre:");
	nombrecompleto.setBounds(new Rectangle(230, 5, 215, 15));
	nombrecompleto.setHorizontalAlignment(SwingConstants.CENTER);
	nombrecompleto.setHorizontalTextPosition(SwingConstants.CENTER);
	tamaniopixels.setText("Tamaño:");
	tamaniopixels.setBounds(new Rectangle(230, 210, 215, 15));
	tamaniopixels.setHorizontalTextPosition(SwingConstants.CENTER);
	tamaniopixels.setHorizontalAlignment(SwingConstants.CENTER);
	nombre.setText("nombrec");
	nombre.setBounds(new Rectangle(230, 65, 215, 15));
	nombre.setHorizontalAlignment(SwingConstants.CENTER);
	this.getContentPane().add(nombre, null);
	this.getContentPane().add(tamaniopixels, null);
	this.getContentPane().add(nombrecompleto, null);
	this.getContentPane().add(tamaniobytes, null);
	this.getContentPane().add(Cerrar, null);
	this.getContentPane().add(Insertar, null);
	this.getContentPane().add(Abrir, null);
	this.getContentPane().add(panel, null);
    }

    private void Abrir_actionPerformed(ActionEvent e) {
	String filename = File.separator + "jpg";
	JFileChooser fc = new JFileChooser(new File(filename));
	// Show open dialog; this method does not return until the dialog is closed
	fc.showOpenDialog(this);
	File selFile = fc.getSelectedFile();
	try {
	    nombrecompleto.setText(selFile.getAbsolutePath());
	    File file = new File(nombrecompleto.getText());
	    if (nameimg.length() > 0) {
		nombre.setText(nameimg);
	    } else {
		nombre.setText(file.getName());
	    }
	    image = ImageIO.read(file);
	    tamaniobytes.setText(String.valueOf(file.length()) + " bytes");
	    tamaniopixels.setText(image.getWidth() + " x " + image.getHeight() + " píxels");
	    repaint();
	} catch (Exception x) {
	}
    }

    private void Insertar_actionPerformed(ActionEvent e) {
	try {
	    File file = new File(nombrecompleto.getText());
	    if (nameimg.length() > 0) {
		nombre.setText(nameimg);
	    } else {
		nombre.setText(file.getName());
	    }
	    FileInputStream fis = new FileInputStream(file);
	    PreparedStatement ps;
	    if (LibSQL.getCampo("SELECT COUNT(*) FROM images WHERE imgname='" + nombre.getText() + "'").equals("0")) {
		ps = LibSQL.getConnection().prepareStatement("INSERT INTO images VALUES (?, ?)");
		ps.setString(1, nombre.getText());
		ps.setBinaryStream(2, fis, (int)file.length());
	    } else {
		ps = LibSQL.getConnection().prepareStatement("UPDATE images SET img =? WHERE imgname='" + nombre.getText() + "'");
		ps.setBinaryStream(1, fis, (int)file.length());
	    }
	    /*        ps.setString(1, nameimg);
      } else
      {*/
	    //}
	    ps.executeUpdate();
	    ps.close();
	    fis.close();
	} catch (Exception x) {
	}
    }

    private void recuperaImagen() {
	/*    File file = new File(archivo);
    Connection conn = Proced.CCon();
    FileOutputStream fis = new FileOutputStream(file);
    Statement stat = conn.createStatement();
    PreparedStatement ps = conn.prepareStatement("SELECT img FROM images WHERE imgname=?");
    ps.setString(1, archivo);
    //ResultSet rs = ps.executeQuery();
    ResultSet rs = stat.executeQuery("SELECT img FROM images WHERE imgname='code39.jpg'");
    BufferedOutputStream output = new BufferedOutputStream(fis);
    if (rs != null)
    {
      while(rs.next())
      {
          byte[] imgBytes =rs.getBytes(1);
          output.write(imgBytes);
      }
      output.close();
      rs.close();
    }
    ps.close();*/
    }

    private void Cerrar_actionPerformed(ActionEvent e) {
	this.dispose();
    }

}
