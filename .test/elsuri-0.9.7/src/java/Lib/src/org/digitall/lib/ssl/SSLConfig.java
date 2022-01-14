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
 * SSLConfig.java
 *
 * */
package org.digitall.lib.ssl;

public abstract class SSLConfig {
    //Common configuration
    public static final String KEY_FILE_PATH = "certs/serverStore.cer";
    public static final String TRUST_FILE_PATH = "certs/clientStore.cer";
    public static final char[] STORE_PASS = new String("digitallsh").toCharArray();
    public static final char[] KEY_PASS = new String("digitallsh").toCharArray();
    //public static final String SERVER_IP = "172.16.4.253";
    //End of common configuration
    //ChatWithYou Module configuration
    public static final int CHWY_SERVICE = 1;
    public static final String CHWY_LOG_FILE_PATH = "/tmp/chwy_log";
    public static int CHWY_CLIENT_PORT = 1502;
    public static final int CHWY_SERVER_SECURED_PORT = 2002;
    public static final int CHWY_SERVER_UNSECURED_PORT = 5431;
    //End of module configuration
    //FileManager Module configuration
    public static final int FM_SERVICE = 2;
    public static final String FM_LOG_FILE_PATH = "/tmp/fman_log";
    public static int FM_CLIENT_PORT = 1501;
    public static final int FM_SERVER_SECURED_PORT = 2001;
    public static final int FM_SERVER_UNSECURED_PORT = 5430;
    //End of module configuration
    //PostgreSQL SSL Connection configuration
    public static final int PG_SERVICE = 3;
    public static final String PG_LOG_FILE_PATH = "/tmp/sql_log";
    public static int PG_CLIENT_PORT = 1503;
    public static final int PG_SERVER_SECURED_PORT = 2003;
    public static final int PG_SERVER_UNSECURED_PORT = 5432;
    //End of module configuration
    //LogsSystem SSL Connection configuration
    public static final int LOG_SERVICE = 4; // el valor 1 le indica al cliente que se debe conectar a digitall en lugar de localhost
    public static final String LOGSYSTEM_FILE_PATH = "/tmp/logsystem_log";
    public static int LOG_CLIENT_PORT = 1504;
    public static final int LOG_SERVER_SECURED_PORT = 2003;
    public static final int LOG_SERVER_UNSECURED_PORT = 5432;
    public static String LOG_SERVER_REMOTE_IP = "localhost";
    //GaiaClient Configurations
    public static final int GAIA_SERVICE = 5;
    public static int GAIA_CLIENT_PORT = 1505;
    public static final int GAIA_SERVER_SECURED_PORT = 2003;
    public static final int GAIA_SERVER_UNSECURED_PORT = 5432;
    //LibSQLMini Configurations
    public static final int SQLMINI_SERVICE = 6;
    public static int SQLMINI_CLIENT_PORT = 1506;
    public static final int SQLMINI_SERVER_SECURED_PORT = 2003;
    public static final int SQLMINI_SERVER_UNSECURED_PORT = 5432;
    //End of module configuration

     public static void setFM_CLIENT_PORT(int _port) {
	 FM_CLIENT_PORT = _port;
     }

    public static void setPG_CLIENT_PORT(int _port) {
	PG_CLIENT_PORT = _port;
    }

    public static void setCHWY_CLIENT_PORT(int _port) {
	CHWY_CLIENT_PORT = _port;
    }
    
    public static void setLOG_CLIENT_PORT(int _port) {
        LOG_CLIENT_PORT = _port;
    }

    public static void setLOG_SERVER_REMOTE_IP(String _remoteIP) {
	LOG_SERVER_REMOTE_IP = _remoteIP;
    }

    public static void setGAIA_CLIENT_PORT(int _port) {
	GAIA_CLIENT_PORT = _port;
    }

    public static void setSQLMINI_CLIENT_PORT(int _port) {
	SQLMINI_CLIENT_PORT = _port;
    }
}
