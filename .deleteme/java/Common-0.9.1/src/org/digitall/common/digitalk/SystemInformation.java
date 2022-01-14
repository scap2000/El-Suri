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
 * SystemInformation.java
 *
 * */
package org.digitall.common.digitalk;

import java.awt.GraphicsDevice;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;

import java.net.SocketException;

import java.net.UnknownHostException;

import java.text.ParseException;

import java.util.Iterator;
import java.util.List;

import java.util.StringTokenizer;

import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.sql.LibSQLMini;

public class SystemInformation {
    
    private String nombreUsuarioActualSistema = "";
    private String fechaHora = "";
    private String nombreSistema = "";
    private String nombreUsuarioSO = "";
    private String database = "";
    private String direccionIP = "";
    private String direccionMAC = "";
    private String versionJVM = "";
    private String nombreSO = "";
    private String nombrePC = "";
    private String memoriaRAM = "";
    private String procesador = "";
    private String resolucion = "";
    private String nombreOrganizacion = "";
    private byte[] macAddress;
    
    public static final String USERSYSTEM = "usuarioSistema:";
    public static final String DATETIME = "fechahora:";
    public static final String NAMESYSTEM = "nombreSistema:";
    public static final String USERSO = "usuarioSO:";
    public static final String DATABASE = "database:";
    public static final String IPADDRESS = "direccionIP:";
    public static final String MACADDRESS = "direccionMAC:";
    public static final String VERSIONJVM = "versionJVM:";
    public static final String SO = "nombreSO:";
    public static final String HOSTNAME = "nombrePC:";
    public static final String RAM = "ram:";
    public static final String PROCESSOR = "procesador:";
    public static final String  RESOLUTION = "nombrePC:";
    public static final String  ORGANIZATION = "orgName:";
    public static String parametros = "";
    
    
    public SystemInformation() {
        nombreUsuarioActualSistema = Environment.sessionUser;
        nombreSistema = Environment.SYSTEM_VERSION;
        nombreUsuarioSO = System.getProperty("user.name");
        String[] paramsDataBase = LibSQL.getDataBase().split("/");
        database = paramsDataBase[paramsDataBase.length-1]; 
        String[] commandMAC = {"/bin/bash","-c","/sbin/ifconfig | grep 'HWaddr' | grep -v '127.0.0' | tr -s ' ' | cut -d ' ' -f5"};
        String[] commandIP = {"/bin/bash","-c","/sbin/ifconfig | grep 'inet addr:'| grep -v '127.0.0' | cut -d: -f2 | sort | awk '{print $1}'"};
        try {
            //direccionIP = InetAddress.getLocalHost().getHostAddress();
            
            direccionMAC = "N/C";
            nombrePC = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            direccionMAC = "N/C";
            direccionIP = "N/C";
        } catch (IOException e) {
            e.printStackTrace();
        }
        versionJVM = System.getProperty("java.runtime.version");
        procesador = String.valueOf(Runtime.getRuntime().availableProcessors());
        memoriaRAM = String.valueOf(Runtime.getRuntime().totalMemory());
        nombreSO = System.getProperty("os.name") + " v." + System.getProperty("os.version");
        GraphicsDevice device = Environment.graphicsDevice;
        resolucion = ""+device.getDefaultConfiguration().getBounds().width + "x" + device.getConfigurations()[0].getBounds().height + "@" + device.getDefaultConfiguration().getColorModel().getPixelSize() + " bit";
        nombreOrganizacion = Environment.organization;

        if(nombreSO.toUpperCase().startsWith("LINUX")){
            try {
                //direccionMAC = linuxParseMacAddress(linuxRunIfConfigCommand());
                direccionMAC = linuxRunIfConfigCommand(commandMAC);
                direccionIP = linuxRunIfConfigCommand(commandIP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if(nombreSO.toUpperCase().startsWith("WINDOWS")){
                try {
                    direccionIP = InetAddress.getLocalHost().getHostAddress();
                    direccionMAC = windowsParseMacAddress(windowsRunIpConfigCommand());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }    
            }
        }
        /*if(versionJVM.startsWith("1.6")){

            Enumeration<NetworkInterface> interfaces = null;
            try {
                interfaces = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while (interfaces.hasMoreElements()) {
                NetworkInterface nif = interfaces.nextElement();
                try {
                    macAddress = nif.getHardwareAddress();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                if (macAddress != null) {
                    for (byte b : macAddress) {
                        direccionMAC = String.format("%1$02X ", b);
                    }
                } else {
                    direccionMAC = "N/C";
                }
            }
        } else {
            if(SO.toUpperCase().startsWith("LINUX")){

            } else {
                try {
                    direccionMAC = getMacWindows();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
        //ResultSet result = LibSQLMini.exQuery("SELECT now() AS fechahora");
        /*
        ResultSet result = LibSQL.exQuery("SELECT now() AS fechahora");
        try {
            if(result.next()){
                fechaHora = result.getString("fechahora");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
    
    private static String linuxRunIfConfigCommand(String[] _command) throws IOException {
        
        //String[] command = {"/bin/bash","-c","/sbin/ifconfig | grep 'HWaddr' | grep -v '127.0.0' | tr -s ' ' | cut -d ' ' -f5"};
        //String[] command = {"/bin/bash","-c","/sbin/ifconfig | grep 'inet addr:'| grep -v '127.0.0' | cut -d: -f2 | sort | awk '{print $1}'"};
        Process p = Runtime.getRuntime().exec(_command);
        //Process p = Runtime.getRuntime().exec("/sbin/ifconfig | grep \'eth\' | tr -s \' \' | cut -d \' \' -f5");
        //Process p = Runtime.getRuntime().exec("/sbin/ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
        StringBuffer buffer= new StringBuffer();
        for (;;) {
                int c = stdoutStream.read();
                if (c == -1) break;
                buffer.append((char)c);
        }
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }
    
    public static void main(String args[]){
        
        /*try {
            System.out.println("***");
            //System.out.println(" = "+SystemInformation.linuxParseMacAddress(SystemInformation.linuxRunIfConfigCommand()));
            //System.out.println(" = "+SystemInformation.linuxRunIfConfigCommand());
        } catch (IOException e) {
        
        }*/
    }
    
    public static String getMacWindows() throws java.io.IOException {
        Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
        String line;
        line = in.readLine();
        String[] result = line.split(",");
        return result[0].replace('"', ' ').trim();
    }
    
    private String windowsRunIpConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
        StringBuffer buffer= new StringBuffer();
        for (;;) {
                int c = stdoutStream.read();
                if (c == -1) break;
                buffer.append((char)c);
        }
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }
    
    private String windowsParseMacAddress(String ipConfigResponse) throws ParseException {
        String localHost = null;
        try {
                localHost = InetAddress.getLocalHost().getHostAddress();
                System.out.println(localHost);
                //localHost = InetAddress.getByName(NetworkConfig.getServerIP()).getHostAddress();
        } catch(java.net.UnknownHostException ex) {
                ex.printStackTrace();
                throw new ParseException(ex.getMessage(), 0);
        }
        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String lastMacAddress = null;
        while(tokenizer.hasMoreTokens()) {
                String line = tokenizer.nextToken().trim();
                
                // see if line contains IP address
                if(line.endsWith(localHost) && lastMacAddress != null) {
                        return lastMacAddress;
                }
                
                // see if line contains MAC address
                int macAddressPosition = line.indexOf(":");
                if(macAddressPosition <= 0) continue;
                
                String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
                /*
                if(windowsIsMacAddress(macAddressCandidate)) {
                        lastMacAddress = macAddressCandidate;
                        continue;
                }*/
                lastMacAddress = macAddressCandidate;
        }
        ParseException ex = new ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
        ex.printStackTrace();
        throw ex;
    }
    
    private static String linuxParseMacAddress(String ipConfigResponse) throws ParseException {
        String localHost = null;
        try {
                localHost = InetAddress.getLocalHost().getHostAddress();
            System.out.println(localHost);
                //localHost = InetAddress.getByName(NetworkConfig.getServerIP()).getHostAddress();
                //localHost = direccionIP;
        } catch(java.net.UnknownHostException ex) {
                ex.printStackTrace();
                throw new ParseException(ex.getMessage(), 0);
        }
        
        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String lastMacAddress = null;
        
        while(tokenizer.hasMoreTokens()) {
                String line = tokenizer.nextToken().trim();
                boolean containsLocalHost = line.indexOf(localHost) >= 0;
                
                // see if line contains IP address
                if(containsLocalHost && lastMacAddress != null) {
                        return lastMacAddress;
                }
                
                // see if line contains MAC address
                int macAddressPosition = line.indexOf("HWaddr");
                if(macAddressPosition <= 0) continue;
                
                String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
                /*
                if(linuxIsMacAddress(macAddressCandidate)) {
                        lastMacAddress = macAddressCandidate;
                        continue;
                }*/
                lastMacAddress = macAddressCandidate;
        }
        
        ParseException ex = new ParseException
                ("cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
        ex.printStackTrace();
        throw ex;
    }
    
    public String getParametros(){
        parametros = "";
        parametros = USERSYSTEM + nombreUsuarioActualSistema + ";" + DATETIME + fechaHora + ";" + NAMESYSTEM + nombreSistema + ";" + 
                      HOSTNAME + nombrePC + ";" + DATABASE + database + ";" + IPADDRESS + direccionIP + ";" + MACADDRESS + direccionMAC + ";" + 
                      VERSIONJVM + versionJVM + ";" + SO + nombreSO + ";" + RAM + memoriaRAM + ";" + PROCESSOR + procesador + ";" +
                      RESOLUTION + resolucion + ";";
        return parametros;
    }
    
    public static void printParameter(NetworkInterface ni) throws SocketException {
        System.out.println(" Nombre = " + ni.getName());
        System.out.println(" Nombre a mostrar= " + ni.getDisplayName());
        System.out.println(" Está activa = " + ni.isUp());
        System.out.println(" Soporte para multicast = " + ni.supportsMulticast());
        System.out.println(" Es loopback = " + ni.isLoopback());
        System.out.println(" Es virtual = " + ni.isVirtual());
        System.out.println(" Es punto a punto = " + ni.isPointToPoint());
        
        System.out.println(" Dirección MAC = " + ni.getHardwareAddress());    
        System.out.println(" MTU = " + ni.getMTU());
        System.out.println("Lista de direcciones de interfaz:");
        List<InterfaceAddress> list = ni.getInterfaceAddresses();
        Iterator<InterfaceAddress> it = list.iterator();

        while (it.hasNext()) {
          InterfaceAddress ia = it.next();
          System.out.println(" Dirección = " + ia.getAddress());
          System.out.println(" Broadcast = " + ia.getBroadcast());
          System.out.println(" Longitud de prefijo de red = " + ia.getNetworkPrefixLength());
          System.out.println("");
        }
      }
    
    public static String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}

