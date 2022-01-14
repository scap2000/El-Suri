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
 * CoordinateSystems.java
 *
 * */
package org.digitall.lib.geo.coordinatesystems;

import java.text.DecimalFormat;

/**
 * Geodesia.java
 * Contiene métodos para convertir coordenadas entre GK-UTM-LL
 * Contiene métodos para formatear GºM'S" en Decimales
 */
public abstract class CoordinateSystems {

    public static final int LL = 0;
    public static final int UTM = 1;
    public static final int GK = 2;

    public static LatLongCoord utm2geo(UTMCoord _utmCoord) {
	return utm2geo(_utmCoord.getEasting(), _utmCoord.getNorthing(), _utmCoord.getZone());
    }

    public static LatLongCoord utm2geo(double UTMEast, double UTMNorth, double UTMZone) {
	//double UTMEast = 680764.0;
	//double UTMNorth = 7184023.0;
	double aa = 6378137.0;
	double bb = 6356752.31424518;
	double B = UTMEast;
	double C = UTMNorth;
	double M = C - Math.pow(10.0, 7);
	double ee = Math.sqrt(Math.pow(aa, 2) - Math.pow(bb, 2)) / aa;
	double eeprima = Math.sqrt(Math.pow(aa, 2) - Math.pow(bb, 2)) / bb;
	double f0 = ee;
	double f1 = Math.pow(f0, 2);
	double f2 = Math.pow(f0, 4);
	double f3 = Math.pow(f0, 6);
	double f4 = Math.pow(f0, 8);
	double f5 = Math.pow(f0, 10);
	double a = 1.0 + (3.0 / 4.0) * f1 + (45.0 / 64.0) * f2 + (175.0 / 256.0) * f3 + (11025.0 / 16384.0) * f4 + (43659.0 / 65536.0) * f5;
	double b = (3.0 / 4.0) * f1 + (15.0 / 16.0) * f2 + (525.0 / 512.0) * f3 + (2205.0 / 2048.0) * f4 + (72765.0 / 65536.0) * f5;
	double c = (15.0 / 64.0) * f2 + (105.0 / 256.0) * f3 + (2205.0 / 4096.0) * f4 + (10395.0 / 16384.0) * f5;
	double d = (35.0 / 512.0) * f3 + (315.0 / 2048.0) * f4 + (31185.0 / 131072.0) * f5;
	double e = (315.0 / 16384.0) * f4 + (3465.0 / 65536.0) * f5;
	double f = (639.0 / 131072.0) * f5;
	double alfa = (a * aa * (1 - Math.pow(ee, 2))) * (Math.PI / 180.0);
	double beta = b * aa * (1 - Math.pow(ee, 2)) / 2.0;
	double gamma = c * aa * (1 - Math.pow(ee, 2)) / 4.0;
	double delta = d * aa * (1 - Math.pow(ee, 2)) / 6.0;
	double epsilon = e * aa * (1 - Math.pow(ee, 2)) / 8.0;
	double psi = f * aa * (1 - Math.pow(ee, 2)) / 10.0;
	double rad = 180.0 / (Math.atan(1) * 4.0);
	double N = B - 500000.0;
	double O = M / (0.9996 * alfa * rad);
	double P = alfa * rad - 2.0 * beta * Math.cos(2.0 * O) + 4.0 * gamma * Math.cos(4.0 * O) - 6.0 * delta * Math.cos(6.0 * O) + 8.0 * epsilon * Math.cos(8.0 * O) - 10.0 * psi * Math.cos(10.0 * O);
	double Q = (-1.0 / P) * (2.0 * beta * Math.sin(2.0 * O) - 8.0 * gamma * Math.sin(4.0 * O) + 18.0 * delta * Math.sin(6.0 * O));
	double R = (-1.0 / P) * (4.0 / 3.0 * beta * Math.cos(2.0 * O) - 32.0 / 3.0 * gamma * Math.cos(4.0 * O) + 36.0 * delta * Math.cos(6.0 * O));
	double S = (-1.0 / P) * (2.0 / 3.0 * beta * Math.sin(2.0 * O) + 32.0 / 3.0 * gamma * Math.sin(4.0 * O) - 54.0 * delta * Math.sin(6.0 * O));
	double T = (-1.0 / P) * (4.0 / 15.0 * beta * Math.cos(2.0 * O) + 128.0 / 15.0 * gamma * Math.cos(4.0 * O) - 324.0 / 5.0 * delta * Math.cos(6.0 * O));
	double U = (-1.0 / P) * (4.0 / 15.0 * beta * Math.sin(2.0 * O) - 128.0 / 15.0 * gamma * Math.sin(4.0 * O) + 324.0 / 5.0 * delta * Math.sin(6.0 * O));
	double V = (-1.0 / P) * (2.0 * beta * Math.sin(2.0 * O) - 8.0 * gamma * Math.sin(4.0 * O) + 18.0 * delta * Math.sin(6.0 * O));
	double W = R + 2.0 * Math.pow(Q, 2);
	double X = S + 5.0 * Q * R + 5.0 * Math.pow(Q, 3);
	double Y = T + 6.0 * Q * S + 3.0 * Math.pow(R, 2) + 21.0 * Math.pow(Q, 2) * R + 14.0 * Math.pow(Q, 4);
	double Z = U + 7.0 * (Q * T + R * S) + 28.0 * (Math.pow(Q, 2) * S + Q * Math.pow(R, 2)) + 84.0 * Math.pow(Q, 3) * R + 42.0 * Math.pow(Q, 5);
	double AA = (beta * Math.sin(2.0 * O) - gamma * Math.sin(4.0 * O) + delta * Math.sin(6.0 * O) - epsilon * Math.sin(8.0 * O) + psi * Math.sin(10.0 * O)) / P;
	double AB = AA + V * Math.pow(AA, 2) + W * Math.pow(AA, 3) + X * Math.pow(AA, 4) + Y * Math.pow(AA, 5) + Z * Math.pow(AA, 6);
	double seno1seg = Math.sin(Math.toRadians(1.0 / 3600.0));
	double k = 0.9996;
	double AC = O + AB;
	double AE = Math.tan(AC);
	double AD = aa / Math.sqrt(1 - Math.pow(ee, 2) * Math.pow(Math.sin(AC), 2));
	double AF = Math.sqrt(Math.pow(eeprima, 2)) * Math.cos(AC);
	double AG = ((AE * (1 + Math.pow(AF, 2))) / (2 * Math.pow(AD, 2) * seno1seg * Math.pow(k, 2))) * Math.pow(N, 2);
	double AH = ((AE * (5.0 + 3.0 * Math.pow(AE, 2) + 6.0 * Math.pow(AF, 2) - 6.0 * Math.pow(AF, 2) * Math.pow(AE, 2) - 3.0 * Math.pow(AF, 4) - 9.0 * Math.pow(AF, 4) * Math.pow(AE, 2))) / (24.0 * Math.pow(AD, 4) * seno1seg * Math.pow(k, 4))) * Math.pow(N, 4);
	double AI = ((AE * (61.0 + 90.0 * Math.pow(AE, 2) + 45.0 * Math.pow(AE, 4) + 107.0 * Math.pow(AF, 2) - 162.0 * Math.pow(AF, 2) * Math.pow(AE, 2) - 45.0 * Math.pow(AE, 4) * Math.pow(AF, 2))) / (720.0 * Math.pow(AD, 6) * seno1seg * Math.pow(k, 6))) * Math.pow(N, 6);
	double AN = ((((AC * 45.0) / Math.atan(1.0)) * 3600.0) - AG + AH - AI) / 3600.0;
	// Calculo Meridiano Central
	double MC = UTMZone * 6 - 183;
	double AJ = (1.0 / (AD * Math.cos(AC) * seno1seg * k)) * N;
	double AK = ((-1.0) * (1.0 + 2.0 * Math.pow(AE, 2) + Math.pow(AF, 2)) / (6.0 * Math.pow(AD, 3) * Math.cos(AC) * seno1seg * Math.pow(k, 3))) * Math.pow(N, 3);
	double AL = ((5.0 + 28.0 * Math.pow(AE, 2) + 24.0 * Math.pow(AE, 4) + 6.0 * Math.pow(AF, 2) + 8.0 * Math.pow(AF, 2) * Math.pow(AE, 2)) / (120.0 * Math.pow(AD, 5) * Math.cos(AC) * seno1seg * Math.pow(k, 5))) * Math.pow(N, 5);
	double AM = ((AJ + AK + AL) / 3600.0) * (Math.atan(1) / 45.0);
	double AO = ((MC * Math.atan(1.0) / 45.0) + AM) * (45.0 / Math.atan(1));
	/*        System.out.println("Latitud: " + AN);
        System.out.println("Longitud: " + AO);
        System.out.println("B: " + B);
        System.out.println("C: " + C);
        System.out.println("M: " + M);
        System.out.println("N: " + N);
        System.out.println("O: " + O);
        System.out.println("P: " + P);
        System.out.println("Q: " + Q);
        System.out.println("R: " + R);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("U: " + U);
        System.out.println("V: " + V);
        System.out.println("W: " + W);
        System.out.println("X: " + X);
        System.out.println("Y: " + Y);
        System.out.println("Z: " + Z);
        System.out.println("AA: " + AA);
        System.out.println("AB: " + AB);
        System.out.println("AC: " + AC);
        System.out.println("AD: " + AD);
        System.out.println("AE: " + AE);
        System.out.println("AF: " + AF);
        System.out.println("AG: " + AG);
        System.out.println("AH: " + AH);
        System.out.println("AI: " + AI);
        System.out.println("AJ: " + AJ);
        System.out.println("AK: " + AK);
        System.out.println("AL: " + AL);
        System.out.println("AM: " + AM);
        System.out.println("AN: " + AN);
        System.out.println("AO: " + AO);
        System.out.println("Alfa: " + alfa);
        System.out.println("Beta: " + beta);
        System.out.println("Gamma: " + gamma);
        System.out.println("Delta: " + delta);
        System.out.println("Epsilon: " + epsilon);
        System.out.println("Psi: " + psi);
        System.out.println("Radianes: " + rad);
        System.out.println("a: " + a);
        System.out.println("ee: " + ee);
        System.out.println("ee2: " + ee*ee);
        System.out.println("Sin(1/3600): " + seno1seg);*/
	return new LatLongCoord(AN, AO);
    }

    public static UTMCoord geo2utm(LatLongCoord _llCoord) {
	return geo2utm(_llCoord.getLatitude(), _llCoord.getLongitude());
    }

    public static UTMCoord geo2utm(double LatDec, double LongDec) {
	//double LatDec = -25.44982815;
	//double LongDec = -67.20224218;
	double aa = 6378137.0;
	double bb = 6356752.31424518;
	double ee = Math.sqrt(Math.pow(aa, 2) - Math.pow(bb, 2)) / aa;
	double eeprima = Math.sqrt(Math.pow(aa, 2) - Math.pow(bb, 2)) / bb;
	double f0 = ee;
	double f1 = Math.pow(f0, 2);
	double f2 = Math.pow(f0, 4);
	double f3 = Math.pow(f0, 6);
	double f4 = Math.pow(f0, 8);
	double f5 = Math.pow(f0, 10);
	double a = 1.0 + (3.0 / 4.0) * f1 + (45.0 / 64.0) * f2 + (175.0 / 256.0) * f3 + (11025.0 / 16384.0) * f4 + (43659.0 / 65536.0) * f5;
	double b = (3.0 / 4.0) * f1 + (15.0 / 16.0) * f2 + (525.0 / 512.0) * f3 + (2205.0 / 2048.0) * f4 + (72765.0 / 65536.0) * f5;
	double c = (15.0 / 64.0) * f2 + (105.0 / 256.0) * f3 + (2205.0 / 4096.0) * f4 + (10395.0 / 16384.0) * f5;
	double d = (35.0 / 512.0) * f3 + (315.0 / 2048.0) * f4 + (31185.0 / 131072.0) * f5;
	double e = (315.0 / 16384.0) * f4 + (3465.0 / 65536.0) * f5;
	double f = (639.0 / 131072.0) * f5;
	double AP = LatDec;
	double BD = LongDec;
	// double MC = -69.0; //APRENDER A CALCULARLO!!!
	double mcfaj = 0.0;
	double longr = Math.toRadians(LongDec);
	double signol = signo(longr);
	double mcr = 0.0;
	double zonau = 0.0;
	double g15u = Math.toRadians(3.0);
	for (int i = 0; i <= 60; i++) {
	    mcfaj = Math.toRadians(i * 6.0 - 3.0);
	    if (mcfaj - Math.abs(longr) < g15u) {
		mcr = mcfaj * signol;
		zonau = 31 - i;
	    }
	}
	if (mcr > 0) {
	    zonau = 61 - zonau;
	}
	double MC = Math.toDegrees(mcr);
	//System.out.println("MCCalc: " + MC);
	//System.out.println("ZonaUCalc: " + zonau);
	double N = aa / Math.sqrt(1 - Math.pow(ee, 2) * Math.pow(Math.sin(AP * Math.PI / 180.0), 2));
	double APP = Math.toRadians(AP) * signo(AP);
	double APPD = AP * signo(AP);
	double AQ = 0.9996 * (((aa * (1 - Math.pow(ee, 2)) * (a * AP * Math.PI / 180.0 - 1.0 / 2.0 * b * Math.sin(2.0 * Math.toRadians(AP)) + 1.0 / 4.0 * c * Math.sin(4.0 * Math.toRadians(AP)) - 1.0 / 6.0 * d * Math.sin(6.0 * Math.toRadians(AP)) + 1.0 / 8.0 * e * Math.sin(8.0 * Math.toRadians(AP)) - 1.0 / 10.0 * f * Math.sin(10.0 * Math.toRadians(AP))))) * signo(AP));
	double BDMC = ((BD - MC) * 3600.0 * 0.0001) * signo((BD - MC) * 3600.0 * 0.0001);
	double MCBD = 0.0001 * (MC * signo(MC) - BD * signo(BD)) * 3600.0;
	double AR = ((N * Math.sin(APP) * Math.cos(APP) * 2.35044305389E-11) / 2) * (0.9996E8 * Math.pow(BDMC, 2));
	double AS = (5.5245825495862E-22 * N) * (Math.sin(APP) * Math.pow(Math.cos(APP), 3) / 24.0) * (5.0 - Math.pow(Math.tan(APP), 2) + 9.0 * Math.pow(eeprima, 2) * Math.pow(Math.cos(APP), 2) + 4.0 * Math.pow(eeprima, 2) * Math.pow(Math.cos(APP), 4)) * 0.9996E16 * Math.pow(BDMC, 4);
	double AV = (1.29852166793243E-32 * N) * (Math.sin(APP) * Math.pow(Math.cos(APP), 5) / 720.0) * ((61.0 - 58.0 * Math.pow(Math.tan(APP), 2) + Math.pow(Math.tan(APP), 4) + 270.0 * Math.pow(ee, 2) * Math.pow(Math.cos(APPD), 2) - 330.0 * Math.pow(ee, 2) * Math.pow(Math.sin(APPD), 2)) * 0.9996E24 * Math.pow(MCBD * signo(MCBD), 6));
	double AX = AQ + AR + AS + AV;
	double E = 10000000.0 - AX;
	double AT = (N * Math.cos(APP) * 4.84813681107636E-6 * 0.9996E4) * (BDMC);
	double AU = (1.13952694919095E-16 * N) * ((Math.pow(Math.cos(APP), 3) / 6.0) * (1.0 - Math.pow(Math.tan(APP), 2) + Math.pow(eeprima, 2) * Math.pow(Math.cos(APP), 2)) * 0.9996E12 * (Math.pow(MCBD, 3) * signo(MCBD)));
	double AW = (2.6783932024479E-27 * N) * ((Math.pow(Math.cos(APP), 5) / 120.0) * (5.0 - 18.0 * Math.pow(Math.tan(APP), 2) + Math.pow(Math.tan(APP), 4) + 14.0 * Math.pow(eeprima, 2) * Math.pow(Math.cos(APP), 2) - 58.0 * Math.pow(eeprima, 2) * Math.pow(Math.sin(APP), 2)) * 0.9996E20 * (Math.pow(MCBD, 5) * signo(MCBD)));
	double AY = AT + AU + AW;
	double F = (BD < MC?500000.0 - AY: 500000.0 + AY);
	//System.out.println("E (North): " + E);
	//System.out.println("F (East): " + F);
	UTMCoord utm = new UTMCoord();
	utm.setZone((int)zonau);
	utm.setEasting(F);
	utm.setNorthing(E);
	utm.setBand((int)signo(LatDec));
	return utm;
    }

    private static double arco(double aa, double ff) {
	double n, pii, fi, n2, n4, n3;
	pii = Math.PI;
	fi = 90.0 * pii / 180.0;
	n = ff / (2 - ff);
	n2 = n * n;
	n3 = n2 * n;
	n4 = n2 * n2;
	double a0 = 1.0 + n2 / 4.0 + n4 / 64.0;
	double arc = aa / (1 + n) * (a0 * fi);
	return arc;
    }

    private static double signo(double _num) {
	if (_num < 0) {
	    return -1.0;
	} else {
	    return 1.0;
	}
    }

    public static LatLongCoord gk2geo(GKCoord _gkCoord) throws BadCoordinatesException {
	return gk2geo(_gkCoord.getX(), _gkCoord.getY(), _gkCoord.getFaja());
    }

    public static LatLongCoord gk2geo(double East, double North, int faja) throws BadCoordinatesException {
	//double XNorth = 7185536.043121210;
	//double YEast = 3379073.509311160;
	//5079073.5094693336 NO TIENE QUE DAR -64G
	if (East < 8000000) {
	    double y0 = 10000000.0;
	    double x0 = 500000.0;
	    double piii = Math.PI;
	    double contador = 0;
	    double xxxx = North;
	    double yyyy = East;
	    boolean GK = true;
	    //        var b=" "
	    //  Gauss-Kruger
	    double k0 = 1.0;
	    //Para GK
	    double a = 6378137.0;
	    double fff = 298.2572236;
	    double f = 1.0 / fff;
	    double xxx = arco(a, f);
	    //Para GK
	    //double xxx = y0; //Para UTM
	    double falsonorte = xxx;
	    xxx = xxx - y0;
	    contador++;
	    double x = xxxx;
	    double y = yyyy;
	    // Eleccion de faja Gauss-Kruger
	    double mc = 0.0;
	    double ifaja = 0;
	    if (y >= 1000000) {
		//Calculo la faja
		do {
		    yyyy = yyyy - 1000000.0;
		    ifaja++;
		} while (yyyy >= 1000000.0);
	    }
	    if ((faja >= 1) && (faja <= 7)) {
		ifaja = faja;
	    }
	    mc = -75.0 + ifaja * 3.0;
	    xxxx = xxxx - xxx - y0;
	    double e2 = 2.0 * f - f * f;
	    double n = f / (2.0 - f);
	    double n2 = n * n;
	    double n3 = n2 * n;
	    double n4 = n3 * n;
	    double a0 = 1.0 + n2 / 4.0 + n4 / 64.0;
	    double a2 = 3.0 / 2.0 * (n - n3 / 8.0);
	    double a4 = 15.0 / 16.0 * (n2 - n4 / 4.0);
	    double a6 = 35.0 / 48.0 * n3;
	    double a8 = 315.0 / 512.0 * n4;
	    double fif = xxxx / k0 * (1 + n) / a / a0;
	    double fif0 = fif;
	    double ffif = 0.0;
	    for (int i = 1; i <= 6; i++) {
		ffif = -a2 * Math.sin(2.0 * fif) + a4 * Math.sin(4.0 * fif) - a6 * Math.sin(6.0 * fif) + a8 * Math.sin(8.0 * fif);
		fif = fif0 - ffif / a0;
	    }
	    double t = Math.tan(fif);
	    double t2 = t * t;
	    double t4 = t2 * t2;
	    double eta2 = e2 / (1 - e2) * Math.pow(Math.cos(fif), 2);
	    double eta4 = eta2 * eta2;
	    double u = Math.sqrt(1 - e2 * Math.pow(Math.sin(fif), 2));
	    double nn = a / u;
	    double ykn = (yyyy - x0) / k0 / nn;
	    double ykn2 = ykn * ykn;
	    double ykn3 = ykn2 * ykn;
	    double ykn4 = ykn3 * ykn;
	    double ykn5 = ykn4 * ykn;
	    double ykn6 = ykn5 * ykn;
	    double fi1 = -t / 2.0 * (1.0 + eta2) * ykn2;
	    double fi2 = t / 24.0 * (5.0 + 3.0 * t2 + 6.0 * eta2 - 6.0 * eta2 * t2 - 3.0 * eta4 - 9.0 * t2 * eta4) * ykn4;
	    double fi3 = -t / 720.0 * (61.0 + 90.0 * t2 + 45.0 * t4 + 107.0 * eta2 - 162.0 * t2 * eta2 - 45.0 * t4 * eta2) * ykn6;
	    double fir = fif + fi1 + fi2 + fi3;
	    double long1 = ykn3 / 6.0 * (1.0 + 2.0 * t2 + eta2);
	    double longi2 = ykn5 / 120.0 * (5.0 + 28.0 * t2 + 24.0 * t4 + 6.0 * eta2 + 8.0 * t2 * eta2);
	    double longr = (ykn - long1 + longi2) / Math.cos(fif);
	    double fi0 = fir * 180.0 / piii;
	    double longi = longr * 180.0 / piii + mc;
	    double fgrados = Math.floor(Math.abs(fi0)) * signo(fi0);
	    double fmin = Math.floor(Math.abs(fi0 - fgrados) * 60.0);
	    double fseg = (Math.abs(fi0) - Math.abs(fgrados) - fmin / 60.0) * 3600.0;
	    double latg = fgrados;
	    double latm = fmin;
	    double lats = fseg;
	    double lgrados = Math.floor(Math.abs(longi)) * signo(longi);
	    double lmin = Math.floor(Math.abs(longi - lgrados) * 60.0);
	    double lseg = (Math.abs(longi) - Math.abs(lgrados) - lmin / 60.0) * 3600.0;
	    double longg = lgrados;
	    double lonm = lmin;
	    double lons = lseg;
	    xxxx = x;
	    yyyy = y;
	    //System.out.println("Latitud: " + latg + "Âº " + latm + "\'" + lats + "\"" + "--->" + fi0);
	    //System.out.println("Longitud: " + longg + "Âº " + lonm + "\'" + lons + "\"" + "--->" + longi);
	    //System.out.println("Faja gk2geo: " + ifaja);
	    //System.out.println("MC gk2geo: " + mc);
	    return new LatLongCoord(fi0, longi);
	} else {
	    throw new BadCoordinatesException("Malformed coordinates - East: " + East + ", North: " + North + ", Strip: " + faja);
	}
    }

    public static GKCoord geo2gk(LatLongCoord _llCoord) {
	return geo2gk(_llCoord.getLatitude(), _llCoord.getLongitude());
    }

    public static GKCoord geo2gk(double latitud, double longitud) {
	//   Obtiene coordenadas Easting y Norting  UTM
	//   a partir de Lat/Lon geodesicas
	//   para elipsoide WGS84 o Internacional 1924
	//   Elige la faja por proximidad
	//
	//   Autor: J.L.Hormaechea
	//   abril 1994.
	//
	//   Modificacion: elige faja por proximidad (nov 1995)
	//   si Meridiano Central = 0
	double x0 = 500000.0;
	double y0 = 10000000.0;
	double pii = Math.PI;
	// semi ancho faja G-K  (nov 1995)
	double g15 = Math.toRadians(1.5);
	//System.out.println("G15: " + g15);
	//        double fir = (Math.abs(latg)+Math.abs(latm)/60.0+Math.abs(lats)/3600.0)*signo(latg);
	//        double longr = (Math.abs(longg)+Math.abs(lonm)/60.0+Math.abs(lons)/3600.0)*signo(longg);
	double fir = latitud;
	double longr = longitud;
	fir = Math.toRadians(fir);
	longr = Math.toRadians(longr);
	double fff = 298.2572236;
	double a = 6378137.0;
	double aaa = a;
	double ff = 1.0 / fff;
	double falsonor = arco(aaa, ff);
	//Para GK
	//double falsonor = y0; //Para UTM
	double falsonorte = falsonor;
	falsonor = falsonor - y0;
	//////////////////
	//Elige faja por proximidad
	double signol = signo(longr);
	double mcr = 0.0;
	// Para Gauss-Kruger
	double mcfaj = 0.0;
	for (int i = 0; i <= 60; i++) {
	    mcfaj = i * 3.0 * pii / 180.0;
	    if (mcfaj - Math.abs(longr) < g15) {
		mcr = mcfaj * signol;
	    }
	}
	double mce = mcr * 180.0 / pii;
	double mc = mce;
	mce = mc;
	mcr = Math.toRadians(mce);
	// fin define faja  mcr sale en radianes
	////////////////////////
	longr = longr - mcr;
	double k0 = 1.0;
	//Para GK
	//double k0 = 0.9996; //Para UTM
	double e2 = 2.0 * ff - Math.pow(ff, 2);
	double e = Math.sqrt(e2);
	double n = ff / (2.0 - ff);
	double n2 = n * n;
	double n3 = n2 * n;
	double n4 = n3 * n;
	double a0 = 1.0 + n2 / 4.0 + n4 / 64.0;
	double a2 = 1.5 * (n - n3 / 8.0);
	double a4 = (15.0 / 16.0) * (n2 - n4 / 4.0);
	double a6 = (35.0 / 48.0) * n3;
	double a8 = (315.0 / 512.0) * n4;
	double nn = aaa / (Math.sqrt(1 - e2 * Math.pow(Math.sin(fir), 2)));
	double mm = aaa * (1 - e2) / Math.pow(Math.sqrt(1 - e2 * Math.pow(Math.sin(fir), 2)), 3);
	double t = Math.tan(fir);
	double t2 = t * t;
	double t4 = t2 * t2;
	double eta2 = e2 / (1 - e2) * Math.pow(Math.cos(fir), 2);
	double fia = Math.abs(fir);
	double s = aaa / (1.0 + n) * (a0 * fir - a2 * Math.sin(2.0 * fir) + a4 * Math.sin(4.0 * fir) - a6 * Math.sin(6.0 * fir) + a8 * Math.sin(8.0 * fir));
	double tx1 = longr * Math.cos(fir);
	double tx2 = Math.pow(longr * Math.cos(fir), 3) * (1.0 - t2 + eta2) / 6.0;
	double tx3 = Math.pow(longr * Math.cos(fir), 5) * (5.0 - 18.0 * t2 + t4 + 14.0 * eta2 - 58.0 * t2 * eta2) / 120.0;
	double ty1 = s / nn;
	double ty2 = Math.pow(longr, 2) * Math.sin(fir) * Math.cos(fir) / 2.0;
	double ty3 = Math.pow(longr, 4) * Math.sin(fir) * Math.pow(Math.cos(fir), 3) * (5.0 - t2 + 9.0 * eta2 + 4.0 * eta2 * eta2) / 24.0;
	double ty4 = Math.pow(longr, 6) * Math.sin(fir) * Math.pow(Math.cos(fir), 5) * (61.0 - 58.0 * t2 + t4 + 270.0 * eta2 - 30.0 * t2 * eta2) / 720.0;
	double xx = (tx1 + tx2 + tx3) * k0 * nn + x0;
	double yy = (ty1 + ty2 + ty3 + ty4) * k0 * nn + y0 + falsonor;
	//determinacion de faja
	//Para GK
	double fajan = (mc + 75.0) / 3.0;
	//        if (Math.floor(fajan)==fajan)
	{
	    xx = Math.round(fajan) * 1000000.0 + xx;
	}
	double fi0 = fir * 180.0 / pii;
	double longi = longr * 180.0 / pii + mc;
	double fgrados = Math.floor(Math.abs(fi0)) * signo(fi0);
	double fmin = Math.floor(Math.abs(fi0 - fgrados) * 60.0);
	double fseg = (Math.abs(fi0) - Math.abs(fgrados) - fmin / 60.0) * 3600.0;
	double lgrados = Math.floor(Math.abs(longi)) * signo(longi);
	double lmin = Math.floor(Math.abs(longi - lgrados) * 60.0);
	double lseg = (Math.abs(longi) - Math.abs(lgrados) - lmin / 60.0) * 3600.0;
	double xxxx = xx;
	double yyyy = yy;
	//System.out.println("X: " + xxxx + ",Y: " + yyyy);
	//System.out.println("Faja geo2gk: " + fajan);
	//System.out.println("MC geo2gk: " + mc);
	GKCoord gk = new GKCoord();
	gk.setFaja((int)fajan);
	gk.setX(xxxx);
	gk.setY(yyyy);
	return gk;
    }

    public static UTMCoord gk2utm(double East, double North, int faja) {
    
	try {
	    LatLongCoord geod = gk2geo(East, North, faja);
	    UTMCoord utm = geo2utm(geod.getLatitude(), geod.getLongitude());
	    return utm;
	} catch (BadCoordinatesException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    public static GKCoord utm2gk(double UTMEast, double UTMNorth, double UTMZone) {
	LatLongCoord geod = utm2geo(UTMEast, UTMNorth, UTMZone);
	GKCoord gk = geo2gk(geod.getLatitude(), geod.getLongitude());
	return gk;
    }

    public static String dec2gms(double dec) {
	double g = Math.floor(Math.abs(dec)) * signo(dec);
	double m = Math.floor(Math.abs(dec - g) * 60.0);
	double s = (Math.abs(dec) - Math.abs(g) - m / 60.0) * 3600.0;
	String gms = String.valueOf(g) + "º  " + String.valueOf(m) + "'  " + String.valueOf(s) + "\"";
	return gms;
    }

    public static String decimalFormat(double _numero, int _prec) {
	String df = "0.";
	for (int i = 0; i < _prec; i++) {
	    df += "0";
	}
	return (new DecimalFormat(df)).format(_numero);
    }

    public static String dec2gms(double dec, int prec) {
	double g = Math.floor(Math.abs(dec)) * signo(dec);
	double m = Math.floor(Math.abs(dec - g) * 60.0);
	String s = decimalFormat(((Math.abs(dec) - Math.abs(g) - m / 60.0) * 3600.0), prec);
	String gms = String.valueOf((int)g) + "º  " + String.valueOf((int)m) + "'  " + String.valueOf(s) + "\"";
	return gms;
    }

    public static String getProjectionType(int _projectionType) {
	String _result = "";
	switch (_projectionType) {
	    case LL:
		_result = "Latitud/Longitud";
		break;
	    case GK:
		_result = "Gauss Krüger";
		break;
	    case UTM:
		_result = "Universal Transverse Mercator";
		break;
	}
	return _result;
    }

    public static class BadCoordinatesException extends Throwable {
	public BadCoordinatesException() {
	    super();
	    System.out.println("Malformed coordinates");
	}

	public BadCoordinatesException(String s) {
	    //super(s);
	    System.out.println(s);
	    //printStackTrace();
	}
    }

}
