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
 * Car.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class Car {

    private int iddominio = -1;
    private String dominio = "";
    private String titular = "";
    private String tipo = "";
    private String marca = "";
    private String nromotor = "";
    private String idtipocategoria = "0";
    private String categoria = "";
    private int modelo = -1;
    private boolean eximido = false; /** aca*/
    private double cuota = -1;
    private double valoranual = -1;
    private String pagadesde = "";
    private String estado = "";
    private String fechaExencion = "";
    private String fechaBaja = "";
    private String domicilio = "";
    private String observaciones = ""; 
    private int tipoDominio = 0;
    private int idTipoAutomotor = -1;
    private boolean discapacitado = false;
    private double valorAutomotor = 0.0;
    
    private String dni = "";
    private int idAutomotor = -1;
    private FeesxCategory feesxCategory;
    private boolean modifyModelCategory = false;
    
    public Car() {

    }
    
    public Car(int _iddominio) {
	iddominio = _iddominio;
	retrieveData();
    }

    public void setIddominio(int _iddominio) {
        iddominio = _iddominio;
    }

    public int getIddominio() {
        return iddominio;
    }

    public void setDominio(String _dominio) {
        dominio = _dominio;
    }

    public String getDominio() {
        return dominio;
    }

    public void setTitular(String _titular) {
        titular = _titular;
    }

    public String getTitular() {
        return titular;
    }

    public void setTipo(String _tipo) {
        tipo = _tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setMarca(String _marca) {
        marca = _marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setNromotor(String _nromotor) {
        nromotor = _nromotor;
    }

    public String getNromotor() {
        return nromotor;
    }

    public void setIdtipocategoria(String _idtipocategoria) {
        idtipocategoria = _idtipocategoria;
    }

    public String getIdtipocategoria() {
        return idtipocategoria;
    }

    public void setModelo(int _modelo) {
        modelo = _modelo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setEximido(boolean _eximido) {
        eximido = _eximido;
    }

    public boolean isEximido() {
        return eximido;
    }

    public void setCuota(double _cuota) {
        cuota = _cuota;
    }

    public double getCuota() {
        return cuota;
    }

    public void setValoranual(double _valoranual) {
        valoranual = _valoranual;
    }

    public double getValoranual() {
        return valoranual;
    }

    public void setPagadesde(String _pagadesde) {
        pagadesde = _pagadesde;
    }

    public String getPagadesde() {
        return pagadesde;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setCategoria(String _categoria) {
        categoria = _categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setFechaExencion(String _fechaEximido) {
        fechaExencion = _fechaEximido;
    }

    public String getFechaExencion() {
        return fechaExencion;
    }

    public void setFechaBaja(String _fechaBaja) {
        fechaBaja = _fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }
    
    public void setDni(String _dni) {
        dni = _dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setObservaciones(String _observaciones) {
        observaciones = _observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setTipoDominio(int _tipoDominio) {
        tipoDominio = _tipoDominio;
    }

    public int getTipoDominio() {
        return tipoDominio;
    }
    public void setIdAutomotor(int _idAutomotor) {
        idAutomotor = _idAutomotor;
    }

    public int getIdAutomotor() {
        return idAutomotor;
    }
    
    public int saveData(){
	int result = -1;
	String params = "";
	if (iddominio == -1)  {
	    params = "'"+ dominio +"','"+ titular +"','"+ tipo +"','"+ marca +"','"+ nromotor +"','"+ 
	                  idtipocategoria +"',"+ modelo +",'" + eximido +"'," + cuota + ","+ valoranual +",'"+ 
                          pagadesde +"','"+ dni +"', '" + domicilio + "','" + observaciones + "',"+
                          tipoDominio +","+ discapacitado +","+ idTipoAutomotor +","+ valorAutomotor; 
	    result = LibSQL.getInt("taxes.addCar", params);
	    iddominio = result; 
	} else {
	    params = ""+ iddominio + ",'"+ dominio +"','"+ titular +"','"+ tipo +"','"+ marca +"','"+ nromotor +"','"+
	                 idtipocategoria +"',"+ modelo +",'" + eximido +"'," + cuota + ","+ valoranual +",'" +
                         pagadesde +"','"+ dni +"','" + fechaExencion + "','" + fechaBaja + "','" + domicilio + "','" +
			 observaciones + "',"+ tipoDominio + ","+ discapacitado;
	    result = LibSQL.getInt("taxes.setCar", params);
	}
	 return result;
    }

    public boolean deleteCar(){
	return LibSQL.getBoolean("taxes.delCar", ""+ iddominio);
    }


    public void retrieveData(){
	
	try {
	    ResultSet rs = LibSQL.exFunction("taxes.getCar", ""+ iddominio);
	    if(rs.next()){
		dominio = rs.getString("dominio");
		titular = rs.getString("titular");
                dni = rs.getString("dni");
		tipo = rs.getString("tipo");
		marca = rs.getString("marca");
		nromotor = rs.getString("nromotor");
		idtipocategoria = rs.getString("idtipocategoria");
	        categoria = rs.getString("idtipocategoria");
		modelo = rs.getInt("modelo");
		//eximido = rs.getBoolean("eximido");
		cuota = rs.getDouble("cuota");
		valoranual = rs.getDouble("valoranual");
		pagadesde = rs.getString("pagadesde");
		fechaExencion = rs.getString("fechaexencion");
		fechaBaja = rs.getString("fechabaja");
                estado = rs.getString("estado");
	        domicilio = rs.getString("domicilio");
	        observaciones = rs.getString("observaciones");
	        tipoDominio = rs.getInt("tipodominio");
	        idAutomotor = rs.getInt("idautomotor");
	        idTipoAutomotor = rs.getInt("idtipoautomotor");
		discapacitado = rs.getBoolean("discapacitado");
                valorAutomotor = rs.getDouble("valorautomotor");
		retrieveFeesxCategory();
		
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public int setIdboletaAutomotorOnCuotaAutomotor(int _anticipo, int _anio, int _idBoletaAutomotor){
        int result = LibSQL.getInt("taxes.setCuotasAutomotorByIdboletaAutomotor",iddominio +","+ _anticipo +","+ _anio +","+ _idBoletaAutomotor);
        return result;
    }
    
    public void retrieveIdDominio() {
	    iddominio = LibSQL.getInt("taxes.getIdDominio", ""+ idAutomotor);
    }

    public void setIdTipoAutomotor(int idTipoAutomotor) {
	this.idTipoAutomotor = idTipoAutomotor;
    }

    public int getIdTipoAutomotor() {
	return idTipoAutomotor;
    }

    public void setFeesxCategory(FeesxCategory feesxCategory) {
	this.feesxCategory = feesxCategory;
    }

    public FeesxCategory getFeesxCategory() {
	return feesxCategory;
    }

    private void retrieveFeesxCategory() {
	feesxCategory = new FeesxCategory();
	feesxCategory.setAnio(getModelo());
	feesxCategory.setIdtipocategoria(Integer.parseInt(getIdtipocategoria()));
	feesxCategory.retrieveData();
    }

    public void setDiscapacitado(boolean discapacitado) {
	this.discapacitado = discapacitado;
    }

    public boolean isDiscapacitado() {
	return discapacitado;
    }

    public void setModifyModelCategory(boolean modifyModelCategory) {
	this.modifyModelCategory = modifyModelCategory;
    }

    public boolean isModifyModelCategory() {
	return modifyModelCategory;
    }
    
    public String getLeyendaLibreDeuda(boolean _conBaja){
	return "\t\tLa " + OrganizationInfo.getOrgName() + ", a través de la Dirección de Rentas - Sección Automotores, "
	    + " certifica que el automotor con dominio Nº " + (dominio.length()>0?dominio.toUpperCase():"-") + ", según R.N.P.A."
	    + "; Tipo: " + (tipo.length()>0?tipo.toUpperCase():"-") + "; Marca: " + (marca.length()>0?marca.toUpperCase():"-") 
	    + "; Modelo: " + (modelo>0?modelo:"-") + "; Categoría: " + (categoria.length()>0?categoria:"-")
	    + "; Motor Nº: " + (nromotor.length()>0?nromotor.toUpperCase():"-")
            + ", a nombre de "+ (titular.length()>0?titular.toUpperCase():"-") + "; DNI/CUIT/CUIL Nº: " + (dni.length()>0?dni.toUpperCase():"-") + ""
	    + ", se encuentra LIBRE DE TODO GRAVAMEN EN CUANTO A IMPUESTO AUTOMOTOR" 
	    + " (hasta el día " + Proced.setFormatDate(LibSQL.getDate("taxes.getfechafinlibredeuda", "3, " + idAutomotor).toString(), true) + ")" +
	       (_conBaja?" y LIBRE DE TODO GRAVAMEN EN CUANTO A FALTAS Y/O MULTAS DE TRÁNSITO (hasta el día " 
			 + Proced.setFormatDate(Environment.currentDate, true) + ") en el municipio de " + OrganizationInfo.getLocation() 
			 + " (Provincia de " + OrganizationInfo.getProvince() + ")."
			 + " En este mismo acto se certifica que el vehículo antes mencionado ha sido dado de baja de este municipio":"") + "."
	    + "\n\t\tSe extiende el presente a solicitud de la parte interesada para ser presentado ante las autoridades que así lo solicitan, a los " + Environment.currentDay + " días" + " del mes de " + Environment.monthsArray[(Integer.parseInt(Environment.currentMonth) - 1)] + " del año " + Environment.currentYear + ".-";
    }
    
    public void setValorAutomotor(double _valorAutomotor) {
        valorAutomotor = _valorAutomotor;
    }

    public double getValorAutomotor() {
        return valorAutomotor;
    }
}
