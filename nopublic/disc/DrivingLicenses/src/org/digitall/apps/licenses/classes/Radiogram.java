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
 * Radiogram.java
 *
 * */
package org.digitall.apps.licenses.classes;

import org.digitall.lib.sql.LibSQL;

public class Radiogram {

    private int idradiogram = -1;
    private int radiogramnumber = -1;
    private String datefrom = "";
    private String dateto = "";
    private String reason = "";  
    private String estado = "";

    private int ididentificationtype = -1;    
    private int identificationnumber = -1;
    private String lastname = "";
    private String name = "";

    public Radiogram() {
    
    }

    public void setIdradiogram(int idradiogram) {
        this.idradiogram = idradiogram;
    }

    public int getIdradiogram() {
        return idradiogram;
    }

    public void setRadiogramnumber(int radiogramnumber) {
        this.radiogramnumber = radiogramnumber;
    }

    public int getRadiogramnumber() {
        return radiogramnumber;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getDateto() {
        return dateto;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public int saveData(){
        int result = -1;
        String params = "";
        if (idradiogram == -1)  {
            params = ""+ radiogramnumber +","+ ididentificationtype +","+ identificationnumber +",'"+ lastname +"','"+ name +"','"+ 
                         datefrom +"','"+ dateto +"','"+ reason +"'";
            result = LibSQL.getInt("licenses.addRadiogram",params);
        } else  {
            params = ""+  idradiogram +","+radiogramnumber +","+ ididentificationtype +","+ identificationnumber +",'"+ lastname +"','"+ name +"','"+ 
                         datefrom +"','"+ dateto +"','"+ reason +"'";
            result = LibSQL.getInt("licenses.setRadiogram",params);
        }
        
        return result;
    }

    public void setIdidentificationtype(int ididentificationtype) {
        this.ididentificationtype = ididentificationtype;
    }

    public int getIdidentificationtype() {
        return ididentificationtype;
    }

    public void setIdentificationnumber(int identificationnumber) {
        this.identificationnumber = identificationnumber;
    }

    public int getIdentificationnumber() {
        return identificationnumber;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
