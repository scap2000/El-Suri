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
 * Configuration.java
 *
 * */
package org.digitall.apps.licenses.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Configuration {

    private int idconfiguration = -1;
    private int yearsqty = -1; 
    private int idaccounttodebit = -1;
    private int idaccounttocredit = -1;
    private String estado = "";
    private int idcostcentre = -1;

    public Configuration() {
    }

    public Configuration(int _idconfiguration) {
        idconfiguration = _idconfiguration;
    }

    public void setIdconfiguration(int _idconfiguration) {
        idconfiguration = _idconfiguration;
    }

    public int getIdconfiguration() {
        return idconfiguration;
    }

    public void setYearsqty(int _yearsqty) {
        yearsqty = _yearsqty;
    }

    public int getYearsqty() {
        return yearsqty;
    }

    public void setIdaccounttodebit(int _idaccounttodebit) {
        idaccounttodebit = _idaccounttodebit;
    }

    public int getIdaccounttodebit() {
        return idaccounttodebit;
    }

    public void setIdaccounttocredit(int _idaccounttocredit) {
        idaccounttocredit = _idaccounttocredit;
    }

    public int getIdaccounttocredit() {
        return idaccounttocredit;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }
    public void setIdcostcentre(int _idcostcentre) {
        idcostcentre = _idcostcentre;
    }

    public int getIdcostcentre() {
        return idcostcentre;
    }
    
    public int saveData() {
        int result = -1;
        String params = "";
        if (idconfiguration == -1)  {
            // hay que desarrollarla (aun no es necesaria)
        } else  {   //actualizacion
            params = "" + idconfiguration + "," + yearsqty + "," + idaccounttodebit + "," 
                        + idaccounttocredit +","+ idcostcentre;
            result = LibSQL.getInt("licenses.setConfiguration",params);
        }
        return result;
    }
    
    public int retrieveData() {
        int result = -1;
        ResultSet data = LibSQL.exFunction("licenses.getConfiguration","" + idconfiguration);
        try  {
            if (data.next()) {
                idconfiguration = data.getInt("idconfiguration");
                yearsqty = data.getInt("yearsQty");
                idaccounttodebit = data.getInt("idaccounttodebit");
                idaccounttocredit = data.getInt("idaccounttocredit");
                estado = data.getString("estado");
                idcostcentre = data.getInt("idcostcentre");
            }
            
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        
        return result;
    }

    
}
