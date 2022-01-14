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
 * BarCode.java
 *
 * */
package org.digitall.lib.misc;
/*
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.EAN13Writer;
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

public class BarCode {

    private String[][] tablaEANUPC = new String[10][3];
    private String[][] tablaCode39 = new String[44][2];
    private String[] juegos = new String[10];
    private int posy = 40;
    private int scale = 4;
    private int fWidth = 121 * scale;
    private int limitey = 28;
    private int fHeight = posy + limitey;

    public BufferedImage getBarCode(boolean EAN, String cadena) {
	Tabular();
	if (EAN) {
	    return CodificaEAN13(cadena);
	} else {
	    return CodificaCode39(cadena);
	}
    }

    public BufferedImage getBarCodeEAN13(String cadena) {
	Tabular();
	return CodificaEAN13(cadena);
    }

    public BufferedImage getBarEAN(String cadena) {
	Tabular();
	return CodificaEAN13(cadena);
    }
  
    private void Tabular() 
    {
	juegos[0] = "AAAAAA";
	juegos[1] = "AABABB";
	juegos[2] = "AABBAB";
	juegos[3] = "AABBBA";
	juegos[4] = "ABAABB";
	juegos[5] = "ABBAAB";
	juegos[6] = "ABBBAA";
	juegos[7] = "ABABAB";
	juegos[8] = "ABABBA";
	juegos[9] = "ABBABA";

	tablaEANUPC[0][0] = "0001101"; tablaEANUPC[0][1] = "0100111"; tablaEANUPC[0][2] = "1110010";
	tablaEANUPC[1][0] = "0011001"; tablaEANUPC[1][1] = "0110011"; tablaEANUPC[1][2] = "1100110";
	tablaEANUPC[2][0] = "0010011"; tablaEANUPC[2][1] = "0011011"; tablaEANUPC[2][2] = "1101100";
	tablaEANUPC[3][0] = "0111101"; tablaEANUPC[3][1] = "0100001"; tablaEANUPC[3][2] = "1000010";
	tablaEANUPC[4][0] = "0100011"; tablaEANUPC[4][1] = "0011101"; tablaEANUPC[4][2] = "1011100";
	tablaEANUPC[5][0] = "0110001"; tablaEANUPC[5][1] = "0111001"; tablaEANUPC[5][2] = "1001110";
	tablaEANUPC[6][0] = "0101111"; tablaEANUPC[6][1] = "0000101"; tablaEANUPC[6][2] = "1010000";
	tablaEANUPC[7][0] = "0111011"; tablaEANUPC[7][1] = "0010001"; tablaEANUPC[7][2] = "1000100";
	tablaEANUPC[8][0] = "0110111"; tablaEANUPC[8][1] = "0001001"; tablaEANUPC[8][2] = "1001000";
	tablaEANUPC[9][0] = "0001011"; tablaEANUPC[9][1] = "0010111"; tablaEANUPC[9][2] = "1110100";

	tablaCode39[0][0]  = "0"; tablaCode39[0][1]  = "000110100";
	tablaCode39[1][0]  = "1"; tablaCode39[1][1]  = "100100001";
	tablaCode39[2][0]  = "2"; tablaCode39[2][1]  = "001100001";
	tablaCode39[3][0]  = "3"; tablaCode39[3][1]  = "101100000";
	tablaCode39[4][0]  = "4"; tablaCode39[4][1]  = "000110001";
	tablaCode39[5][0]  = "5"; tablaCode39[5][1]  = "100110000";
	tablaCode39[6][0]  = "6"; tablaCode39[6][1]  = "001110000";
	tablaCode39[7][0]  = "7"; tablaCode39[7][1]  = "000100101";
	tablaCode39[8][0]  = "8"; tablaCode39[8][1]  = "100100100";
	tablaCode39[9][0]  = "9"; tablaCode39[9][1]  = "001100100";
	tablaCode39[10][0] = "A"; tablaCode39[10][1] = "100001001";
	tablaCode39[11][0] = "B"; tablaCode39[11][1] = "001001001";
	tablaCode39[12][0] = "C"; tablaCode39[12][1] = "101001000";
	tablaCode39[13][0] = "D"; tablaCode39[13][1] = "000011001";
	tablaCode39[14][0] = "E"; tablaCode39[14][1] = "100011000";
	tablaCode39[15][0] = "F"; tablaCode39[15][1] = "001011000";
	tablaCode39[16][0] = "G"; tablaCode39[16][1] = "000001101";
	tablaCode39[17][0] = "H"; tablaCode39[17][1] = "100001100";
	tablaCode39[18][0] = "I"; tablaCode39[18][1] = "001001100";
	tablaCode39[19][0] = "J"; tablaCode39[19][1] = "000011100";
	tablaCode39[20][0] = "K"; tablaCode39[20][1] = "100000011";
	tablaCode39[21][0] = "L"; tablaCode39[21][1] = "001000011";
	tablaCode39[22][0] = "M"; tablaCode39[22][1] = "101000010";
	tablaCode39[23][0] = "N"; tablaCode39[23][1] = "000010011";
	tablaCode39[24][0] = "O"; tablaCode39[24][1] = "100010010";
	tablaCode39[25][0] = "P"; tablaCode39[25][1] = "001010010";
	tablaCode39[26][0] = "Q"; tablaCode39[26][1] = "000000111";
	tablaCode39[27][0] = "R"; tablaCode39[27][1] = "100000110";
	tablaCode39[28][0] = "S"; tablaCode39[28][1] = "001000110";
	tablaCode39[29][0] = "T"; tablaCode39[29][1] = "000010110";
	tablaCode39[30][0] = "U"; tablaCode39[30][1] = "110000001";
	tablaCode39[31][0] = "V"; tablaCode39[31][1] = "011000001";
	tablaCode39[32][0] = "W"; tablaCode39[32][1] = "111000000";
	tablaCode39[33][0] = "X"; tablaCode39[33][1] = "010010001";
	tablaCode39[34][0] = "Y"; tablaCode39[34][1] = "110010000";
	tablaCode39[35][0] = "Z"; tablaCode39[35][1] = "011010000";
	tablaCode39[36][0] = "-"; tablaCode39[36][1] = "010000101";
	tablaCode39[37][0] = "."; tablaCode39[37][1] = "110000100";
	tablaCode39[38][0] = " "; tablaCode39[38][1] = "011000100";
	tablaCode39[39][0] = "$"; tablaCode39[39][1] = "010101000";
	tablaCode39[40][0] = "/"; tablaCode39[40][1] = "010100010";
	tablaCode39[41][0] = "+"; tablaCode39[41][1] = "010001010";
	tablaCode39[42][0] = "%"; tablaCode39[42][1] = "000101010";
	tablaCode39[43][0] = "*"; tablaCode39[43][1] = "010010100";
    }

    private String digverifEAN(String cadena) {
	cadena = cadena.trim();
	int SumaPar = 0;
	int SumaNon = 0;
	for (int i = 0; i < cadena.length(); i++) {
	    int num = Integer.parseInt(cadena.substring(i, i + 1));
	    if ((i % 2) == 0)
		SumaNon += num;
	    else
		SumaPar += num;
	}
	int Total = SumaPar * 3 + SumaNon;
	return String.valueOf((10 - (Total % 10)));
    }

    private String digverifCode39(String cadena) {
	cadena = cadena.trim();
	int SumaPar = 0;
	int SumaNon = 0;
	int i = 0;
	while (i < cadena.length()) {
	    //      System.out.println(":" + cadena.substring(i,i+1) + ":");
	    int num = Integer.parseInt(cadena.substring(i, i + 1));
	    i += 2;
	    SumaPar += num;
	}
	i = 1;
	while (i < cadena.length()) {
	    //      System.out.println(":" + cadena.substring(i,i+1) + ":");
	    int num = Integer.parseInt(cadena.substring(i, i + 1));
	    i += 2;
	    SumaNon += num;
	}
	int Total = SumaPar * 3 + SumaNon;
	return String.valueOf((10 - (Total % 10)));
    }

    private BufferedImage CodificaEAN13(String cadena) {
	/*EAN13Writer _ean13Test = new EAN13Writer();
	cadena += digverifEAN(cadena);
	BufferedImage bufferedImage;
	try {
	    bufferedImage = MatrixToImageWriter.toBufferedImage(_ean13Test.encode(cadena, BarcodeFormat.EAN_13, 500, 150));

	    ImageIO.write(MatrixToImageWriter.toBufferedImage(_ean13Test.encode(cadena, BarcodeFormat.EAN_13, 500, 150)),"png", new File("/tmp/ean13_zxing_" + cadena + ".png"));
	    ImageIO.write(oldEAN13Coder(cadena),"png", new File("/tmp/ean13_digitall_" + cadena + ".png"));

	    return bufferedImage;
	} catch (WriterException e) {
	    return oldEAN13Coder(cadena);
	} catch (IOException e) {
	    return oldEAN13Coder(cadena);
	}
    }
		
    private BufferedImage oldEAN13Coder(String cadena) {*/
	BufferedImage bufferedImage = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = bufferedImage.createGraphics();
	//Color de Fondo
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	g2d.setColor(Color.white);
	//Relleno la imagen con el color de fondo
	g2d.fillRect(0, 0, fWidth, fHeight);
	g2d.drawImage(bufferedImage, 0, 0, fWidth, fHeight, null);
	int posx = 0;
	int posx2 = 0;
	int x = 0;
	Font fnt = new Font("Arial", Font.PLAIN, 36);
	g2d.setFont(fnt);
	//Zona Muda
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, x + (13 * scale), posy);
	x = x + (13 * scale);
	//Separador    
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	String juego = juegos[Integer.parseInt(cadena.substring(0, 1))];
	//    System.out.println(cadena + ": " + juego);
	if (cadena.length() < 13) {
	    cadena = cadena + digverifEAN(cadena);
	}
        String _barcode = cadena.toString();
	cadena = cadena.substring(1);
	int index = 0;
	//Lado Izquierdo
	for (int i = 0; i < 6; i++) {
	    if (juego.substring(i, i + 1).equals("A"))
		index = 0;
	    else
		index = 1;
	    //      System.out.println(cadena.substring(i,i+1) + juego.substring(i,i+1) + " - " + tablaEANUPC[Integer.parseInt(cadena.substring(i,i+1))][index]);
	    g2d.setPaint(Color.black);
	    g2d.drawString(_barcode.substring(i, i + 1), (int)(x + 1.5 * scale), posy + limitey);
	    for (int j = 0; j < 7; j++) {
		if (tablaEANUPC[Integer.parseInt(cadena.substring(i, i + 1))][index].substring(j, j + 1).equals("0")) {
		    g2d.setColor(Color.white);
		} else {
		    g2d.setColor(Color.black);
		}
		g2d.fillRect(x, 0, scale, posy);
		x = x + scale;
	    }
	}
	//Separador Central
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	//Lado Derecho
	for (int i = 6; i < 12; i++) {
	    g2d.setPaint(Color.black);
	    g2d.drawString(_barcode.substring(i, i + 1), (int)(x + 0.8 * scale), posy + limitey);
	    for (int j = 0; j < 7; j++) {
		if (tablaEANUPC[Integer.parseInt(cadena.substring(i, i + 1))][2].substring(j, j + 1).equals("0")) {
		    g2d.setColor(Color.white);
		} else {
		    g2d.setColor(Color.black);
		}
		g2d.fillRect(x, 0, scale, posy);
		x = x + scale;
	    }
	}
	//Separador    
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	g2d.setColor(Color.black);
	g2d.fillRect(x, 0, scale, posy + limitey);
	x = x + scale;
	//Zona Muda
	g2d.setColor(Color.white);
	g2d.fillRect(x, 0, scale, posy);
	x = x + (13 * scale);
	g2d.dispose();
        return bufferedImage;
    }

    public BufferedImage CodificaCode39(String cadena) {
	BufferedImage bufferedImage = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_BYTE_INDEXED);
	Graphics2D g2d = bufferedImage.createGraphics();
	//Color de Fondo
	g2d.setColor(Color.white);
	//Relleno la imagen con el color de fondo
	g2d.fillRect(0, 0, fWidth, fHeight);
	g2d.drawImage(bufferedImage, 0, 0, fWidth, fHeight, null);
	int posx = 0;
	int posx2 = 0;
	int x = 0;
	int verif = 0;
	int index = 0;
	boolean found = false;
	for (int i = 0; i < cadena.length(); i++) {
	    int k = 0;
	    found = false;
	    boolean dibuja = true;
	    while (!found) {
		if (tablaCode39[k][0].equals(cadena.substring(i, i + 1))) {
		    found = true;
		    index = k;
		}
		k++;
	    }
	    verif = verif + index;
	}
	cadena = "*" + cadena + tablaCode39[verif % 43][0] + "*";
	System.out.println(cadena);
	int u = 0;
	g2d.setColor(Color.black);
	for (int i = 0; i < cadena.length(); i++) {
	    int k = 0;
	    found = false;
	    boolean dibuja = true;
	    while (!found) {
		if (tablaCode39[k][0].equals(cadena.substring(i, i + 1))) {
		    found = true;
		    index = k;
		}
		k++;
	    }
	    for (int j = 0; j < 9; j++) {
		if (tablaCode39[index][1].substring(j, j + 1).equals("0"))
		    u = 1;
		else
		    u = 3;
		for (int t = 0; t < u; t++) {
		    if (dibuja)
			g2d.drawLine(x, 0, x, posy);
		    x++;
		}
		dibuja = !dibuja;
	    }
	    x++;
	}
	g2d.dispose();
	return bufferedImage;
    }

}
