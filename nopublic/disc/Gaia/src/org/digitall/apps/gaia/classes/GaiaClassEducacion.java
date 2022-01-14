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
 * GaiaClassEducacion.java
 *
 * */
package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassEducacion {

        private int idEducacion = -1;
        private double x;
        private double y;
        private int catastro = 0;
        private String nroestablecimiento = "";
        private String nombre = "";
        private int docentes = 0;
        private int alumnos = 0;
        private int idniveleducativo = 0;
        private int idOrientacionEducativa = 0;
        private String cantegresados = "";

    public GaiaClassEducacion() {
    }

    public void retrieveData() {
        if (idEducacion != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getEducacionObject", idEducacion);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setNroestablecimiento(result.getString("nroestablecimiento"));
                    setNombre(result.getString("nombre"));
                    setDocentes(result.getInt("docentes"));
                    setAlumnos(result.getInt("alumnos"));
                    setIdniveleducativo(result.getInt("idniveleducativo"));
                    setIdOrientacionEducativa(result.getInt("idorientacioneducativa"));
                    setCantegresados(result.getString("cantegresados"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
        return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delEducacionObject", idEducacion);
    }

    public int saveData() {
        int _result = -1;
        String params = idEducacion + ", " + x + ", " + y + ", " + catastro + ",'" + nroestablecimiento + "','" +
                        nombre +"',"+ docentes + ", " + alumnos +" , "+ idniveleducativo +" , "+ idOrientacionEducativa + ", '" + cantegresados + "'";
        _result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setEducacionObject", params);
        if (_result == -1) {
            Advisor.messageBox("Error al grabar los datos", "Error");
        } else {
            idEducacion = _result;  
        }
        return _result;
    }

    public void setIdEducacion(int idEducacion) {
        this.idEducacion = idEducacion;
    }

    public int getIdEducacion() {
        return idEducacion;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setNroestablecimiento(String nroestablecimiento) {
        this.nroestablecimiento = nroestablecimiento;
    }

    public String getNroestablecimiento() {
        return nroestablecimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDocentes(int docentes) {
        this.docentes = docentes;
    }

    public int getDocentes() {
        return docentes;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setIdniveleducativo(int idniveleducativo) {
        this.idniveleducativo = idniveleducativo;
    }

    public int getIdniveleducativo() {
        return idniveleducativo;
    }

    public void setIdOrientacionEducativa(int idOrientacionEducativa) {
        this.idOrientacionEducativa = idOrientacionEducativa;
    }

    public int getIdOrientacionEducativa() {
        return idOrientacionEducativa;
    }

    public void setCantegresados(String cantegresados) {
        this.cantegresados = cantegresados;
    }

    public String getCantegresados() {
        return cantegresados;
    }
}
