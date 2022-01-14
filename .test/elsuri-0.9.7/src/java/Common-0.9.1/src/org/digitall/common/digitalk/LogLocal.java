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
 * LogLocal.java
 *
 * */
package org.digitall.common.digitalk;

import java.net.BindException;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.sql.LibSQLMini;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayClient;

public class LogLocal {
    
    private SSLRelayClient pgClient = null;
    private boolean validado = false;
    private String nombreUsuario = "";
    private String clave = "";
    static String ServerIPLocal = "";
    static String DBNameLocal = "";
    static String DBStringLocal = "jdbc:postgresql://" + ServerIPLocal + "/" + DBNameLocal;
    private String datosEnviar = "";
    private String ultimaFechaHora = "";
    private LibSQLMini libSQL = new LibSQLMini();
    
    public LogLocal() {
        iniciarConexionLocal();
        //conectar();
    }
    
    public LogLocal(String _serverLocal, String _dbNameLocal, String _nombreUsuario, String _clave) {
        ServerIPLocal = _serverLocal;
        DBNameLocal = _dbNameLocal;
        DBStringLocal = "jdbc:postgresql://" + ServerIPLocal + "/" + DBNameLocal;
        nombreUsuario = _nombreUsuario;
        clave = _clave;
        iniciarConexionLocal();
    }
    
    public void iniciarConexionLocal(){
        if (pgClient == null) {
            int tries = 0;
            while (tries < 3 && pgClient == null) {
                try {
                    pgClient = new SSLRelayClient(SSLConfig.PG_SERVER_SECURED_PORT, SSLConfig.PG_CLIENT_PORT, SSLConfig.PG_SERVICE);
                } catch (BindException x) {
                    SSLConfig.setPG_CLIENT_PORT((int)(Math.random() * 10000));
                    System.out.println("PG Port already open, trying on port: " + SSLConfig.PG_CLIENT_PORT);
                    tries++;
                    continue;
                }
            }
        }
    }
    
    public boolean tryToConnect() {
        libSQL.setDataBaseString(DBStringLocal);
        validado = libSQL.tryToConnect(nombreUsuario, clave);
        if (validado) {
            libSQL.setSQLUser(nombreUsuario);
            libSQL.setSQLPass(clave);
            if (libSQL.isConnected()) {
            
            } else {
                
            }
        } else {
            
        }
        return validado;
    }

    private void conectar() {
        libSQL.closeConnection();
        libSQL.setDataBaseString(DBStringLocal);
        validado = libSQL.tryToConnect(nombreUsuario, clave);
        validado = true;
        if (validado) {
            libSQL.setSQLUser(nombreUsuario);
            libSQL.setSQLPass(clave);
            if (libSQL.isConnected()) {
                System.out.println("Conexion Establecida");
            } else {
                System.out.println("No se pudo conectar");
            }
        } else {
            System.out.println("No se pudo conectar");
        }
    }
    
    public String getInformacionEnviar(String _ultimaFechaHora){
        ResultSet resultados = null;
        String result = "";
        String filtro = "";
        conectar();
        
            System.out.println("** ** ** base de datos local = " + libSQL.getDataBase());
            if(!_ultimaFechaHora.equals("")){
                filtro = " AND fechahora > '" + _ultimaFechaHora + "'";
            }
            resultados = libSQL.exQuery("SELECT 'INSERT INTO auditorias.logssystem VALUES(' || oidusuario ||','''||usuario ||''','''|| fechahora || ''',''' || nombresistema || ''',''' || nombrehost || ''',''' || basededatos || ''',''' || direccionip || ''',''' || direccionmac || ''',''' || versionjvm || ''',''' || nombreso || ''',''' || memoriaram || ''',''' || procesador || ''',''' || resolucionpantalla || ''',''' || estado || ''',''' || nombreservidor || ''')' AS registros FROM auditorias.logssystem WHERE estado <> '*'" + filtro + " AND nombreservidor = '" + SystemInformation.getHostName() + "';");
            try {
                if(!(resultados == null)){
                    while(resultados.next()){
                        result += resultados.getString("registros")+ ";\n";
                    }    
                } else{
                    System.out.println("Sin resultados");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            libSQL.closeConnection();    
        //pgClient.close();
        return result;
    }
    
    public void desconectar(){
        libSQL.closeConnection();
    }

    public void setDatosEnviar(String datosEnviar) {
        this.datosEnviar = datosEnviar;
    }

    public String getDatosEnviar() {
        return datosEnviar;
    }

    public void setUltimaFechaHora(String ultimaFechaHora) {
        this.ultimaFechaHora = ultimaFechaHora;
    }

    public String getUltimaFechaHora() {
        return ultimaFechaHora;
    }
    
}
