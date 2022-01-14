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
 * Cadastral.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class Cadastral {

    private int idCatastro = -1;
    private int dpto = -1;
    private String departamento = "";
    private String localidad = "";
    private int tecloc = -1;
    private int catastro = -1;
    private String domcon = "";
    private String domtidoc = "";
    private String domnudoc = "";
    private String domape = "";
    private String domfis = "";
    private String dompor = "";
    private int tecbavig = 1;
    private String vigencia = "Vigente";
    private int plano = -1;
    private String tecfeasg = "null";
    private String tecsec = "";
    private String tecman = "";
    private String tecmanl = "";
    private String tecpar = "";
    private String tecparl = "";
    private String tecunif = "";
    private int tecurru = 0;
    private int tecorg1 = 0;
    private int tecorg2 = 0;
    private int tecderd = 0;
    private int tecderh = 0;
    private String tecobs = "";
    private double tecsuurb = 0;
    private String terbe = "";
    private String terreno = "";
    private String terval = "";
    private String tervmej = "";
    private String tervcom = "";
    private String valfis = "";
    private String resol = "";
    private String resolfe = "";
    private String dcalle = "";
    private String dnumro = "";
    private String ddesbarrio = "";
    private boolean urbano = false;
    
    private int catastroAux = -1;
    private String domnudocAux = "";
    private String domapeAux = "";
    
    private final int addCadastral = 1;
    private final int addOwner = 2;
    private final int editCadastral = 3;
    private String apoderadoName = "";
    private String apoderadoLastName = "";
    private String apoderadoTipoDoc = "-1";
    private String apoderadoDoc = "";
    
    private String fechaBaja = "";
    private String nroCuenta ="";
    
    private double metrosDeFrente = 0.0;
    private int idCategoria = 0;
    private boolean esEsquina = false;

    private Moratoria moratoriaTGS;
    private Moratoria moratoriaInmob;
    private boolean conProblema;
    
    public Cadastral() {
    
    }
    
    public Cadastral(int _catastro) {
        catastro = _catastro;
    }

    public void setDpto(int dpto) {
        this.dpto = dpto;
    }

    public int getDpto() {
        return dpto;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setTecloc(int tecloc) {
        this.tecloc = tecloc;
    }

    public int getTecloc() {
        return tecloc;
    }

    public void setCatastro(int catastro) {
        this.catastro = catastro;
    }

    public int getCatastro() {
        return catastro;
    }

    public void setDomcon(String domcon) {
        this.domcon = domcon;
    }

    public String getDomcon() {
        return domcon;
    }

    public void setDomtidoc(String domtidoc) {
        this.domtidoc = domtidoc;
    }

    public String getDomtidoc() {
        return domtidoc;
    }

    public void setDomnudoc(String domnudoc) {
        this.domnudoc = domnudoc;
    }

    public String getDomnudoc() {
        return domnudoc;
    }

    public void setDomape(String domape) {
        this.domape = domape;
    }

    public String getDomape() {
        return domape;
    }

    public void setDomfis(String domfis) {
        this.domfis = domfis;
    }

    public String getDomfis() {
        return domfis;
    }

    public void setDompor(String dompor) {
        this.dompor = dompor;
    }

    public String getDompor() {
        return dompor;
    }

    public void setTecbavig(int tecbavig) {
        this.tecbavig = tecbavig;
    }

    public int getTecbavig() {
        return tecbavig;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setPlano(int plano) {
        this.plano = plano;
    }

    public int getPlano() {
        return plano;
    }

    public void setTecfeasg(String tecfeasg) {
        this.tecfeasg = tecfeasg;
    }

    public String getTecfeasg() {
        return tecfeasg;
    }

    public void setTecsec(String tecsec) {
        this.tecsec = tecsec;
    }

    public String getTecsec() {
        return tecsec;
    }

    public void setTecman(String tecman) {
        this.tecman = tecman;
    }

    public String getTecman() {
        return tecman;
    }

    public void setTecmanl(String tecmanl) {
        this.tecmanl = tecmanl;
    }

    public String getTecmanl() {
        return tecmanl;
    }

    public void setTecpar(String tecpar) {
        this.tecpar = tecpar;
    }

    public String getTecpar() {
        return tecpar;
    }

    public void setTecparl(String tecparl) {
        this.tecparl = tecparl;
    }

    public String getTecparl() {
        return tecparl;
    }

    public void setTecunif(String tecunif) {
        this.tecunif = tecunif;
    }

    public String getTecunif() {
        return tecunif;
    }

    public void setTecurru(int tecurru) {
        this.tecurru = tecurru;
    }

    public int getTecurru() {
        return tecurru;
    }

    public void setTecorg1(int tecorg1) {
        this.tecorg1 = tecorg1;
    }

    public int getTecorg1() {
        return tecorg1;
    }

    public void setTecorg2(int tecorg2) {
        this.tecorg2 = tecorg2;
    }

    public int getTecorg2() {
        return tecorg2;
    }

    public void setTecderd(int tecderd) {
        this.tecderd = tecderd;
    }

    public int getTecderd() {
        return tecderd;
    }

    public void setTecderh(int tecderh) {
        this.tecderh = tecderh;
    }

    public int getTecderh() {
        return tecderh;
    }

    public void setTecobs(String tecobs) {
        this.tecobs = tecobs;
    }

    public String getTecobs() {
        return tecobs;
    }

    public void setTecsuurb(double tecsuurb) {
        this.tecsuurb = tecsuurb;
    }

    public double getTecsuurb() {
        return tecsuurb;
    }

    public void setTerbe(String terbe) {
        this.terbe = terbe;
    }

    public String getTerbe() {
        return terbe;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerval(String terval) {
        this.terval = terval;
    }

    public String getTerval() {
        return terval;
    }

    public void setTervmej(String tervmej) {
        this.tervmej = tervmej;
    }

    public String getTervmej() {
        return tervmej;
    }

    public void setTervcom(String tervcom) {
        this.tervcom = tervcom;
    }

    public String getTervcom() {
        return tervcom;
    }

    public void setValfis(String valfis) {
        this.valfis = valfis;
    }

    public String getValfis() {
        return valfis;
    }

    public void setResol(String resol) {
        this.resol = resol;
    }

    public String getResol() {
        return resol;
    }

    public void setResolfe(String resolfe) {
        this.resolfe = resolfe;
    }

    public String getResolfe() {
        return resolfe;
    }

    public void setDcalle(String dcalle) {
        this.dcalle = dcalle;
    }

    public String getDcalle() {
        return dcalle;
    }

    public void setDnumro(String dnumro) {
        this.dnumro = dnumro;
    }

    public String getDnumro() {
        return dnumro;
    }

    public void setDdesbarrio(String ddesbarrio) {
        this.ddesbarrio = ddesbarrio;
    }

    public String getDdesbarrio() {
        return ddesbarrio;
    }

    public void setUrbano(boolean urbano) {
        this.urbano = urbano;
    }

    public boolean isUrbano() {
        return urbano;
    }

    public void setIdCatastro(int idCatastro) {
        this.idCatastro = idCatastro;
    }

    public int getIdCatastro() {
        return idCatastro;
    }
    
    public int saveData(int _action) {
        int result = -1;
        String params = "";
        if (_action == addCadastral)  {
            params = "'" + dpto +"','"+ departamento +"','"+ localidad +"',"+ tecloc +","+ catastro +",'"+ 
                     domcon +"','"+ domtidoc +"','" + domnudoc +"','" + domape + "','"+ domfis +"','" + 
                     dompor +"',"+ tecbavig + ",'" + vigencia + "'," + plano + ",'" + tecfeasg + "','" + 
                     tecsec +"','" + tecman +"','"+ tecmanl +"','"+ tecpar +"','"+ tecparl +"','"+ 
                     tecunif +"',"+ tecurru +","+ tecorg1 +","+ tecorg2 +","+ tecderd +",'"+ tecderh +"','"+ tecobs +"',"+ 
                     tecsuurb +",'"+ terbe +"','"+ terreno +"','"+ terval +"','"+ tervmej +"','"+ 
                     tervcom +"','"+ valfis +"','"+ resol +"','"+ resolfe +"','"+ dcalle +"','"+ 
                     dnumro +"','"+ ddesbarrio +"','"+ urbano +"','" +
		     apoderadoName + "','" + apoderadoLastName + "'," + apoderadoTipoDoc + ",'" + apoderadoDoc + "',"+
		     idCategoria +","+ metrosDeFrente +","+ esEsquina ;
            result = LibSQL.getInt("taxes.addCadastral", params);
        } else if(_action == addOwner) {
            params = "'" + dpto +"','"+ departamento +"','"+ localidad +"',"+ tecloc +","+ catastro +",'"+ 
                     domcon +"','"+ domtidoc +"','" + domnudoc +"','" + domape + "','"+ domfis +"','" +     
                     dompor +"',"+ tecbavig + ",'" + vigencia + "'," + plano + ",'" + tecfeasg + "','" +    
                     tecsec +"','" + tecman +"','"+ tecmanl +"','"+ tecpar +"','"+ tecparl +"','"+          
                     tecunif +"',"+ tecurru +","+ tecorg1 +","+ tecorg2 +","+ tecderd +",'"+ tecderh +"','"+ tecobs +"',"+
                     tecsuurb +",'"+ terbe +"','"+ terreno +"','"+ terval +"','"+ tervmej +"','"+
                     tervcom +"','"+ valfis +"','"+ resol +"','"+ resolfe +"','"+ dcalle +"','"+ 
                     dnumro +"','"+ ddesbarrio +"','"+ urbano +"'" ;
            result = LibSQL.getInt("taxes.addOwner", params);
        } else if (_action == editCadastral){
            params = "'" + dpto +"','"+ departamento +"','"+ localidad +"',"+ tecloc +","+ catastro +",'"+ 
                     domcon +"','"+ domtidoc +"','" + domnudoc +"','" + domape + "','"+ domfis +"','" + 
                     dompor +"',"+ tecbavig + ",'" + vigencia + "'," + plano + ",'" + tecfeasg + "','" + 
                     tecsec +"','" + tecman +"','"+ tecmanl +"','"+ tecpar +"','"+ tecparl +"','"+
                     tecunif +"',"+ tecurru +","+ tecorg1 +","+ tecorg2 +","+ tecderd +",'"+ tecderh +"','"+ tecobs +"',"+ 
                     tecsuurb +",'"+ terbe +"','"+ terreno +"','"+ terval +"','"+ tervmej +"','"+ 
                     tervcom +"','"+ valfis +"','"+ resol +"','"+ resolfe +"','"+ dcalle +"','"+ 
                     dnumro +"','"+ ddesbarrio +"','"+ urbano +"',"+ catastroAux +",'"+ domapeAux +"','"+ domnudocAux +"','" +
		     apoderadoName + "','" + apoderadoLastName + "'," + apoderadoTipoDoc + ",'" + apoderadoDoc + "','"+ fechaBaja +"',"+
		     idCategoria +","+ metrosDeFrente +","+ esEsquina;
            result = LibSQL.getInt("taxes.setCadastral", params);
        }
        return result;
    }

    public void setCatastroAux(int catastroAux) {
        this.catastroAux = catastroAux;
    }

    public int getCatastroAux() {
        return catastroAux;
    }

    public void setDomnudocAux(String domnudocAux) {
        this.domnudocAux = domnudocAux;
    }

    public String getDomnudocAux() {
        return domnudocAux;
    }

    public void setDomapeAux(String domapeAux) {
        this.domapeAux = domapeAux;
    }

    public String getDomapeAux() {
        return domapeAux;
    }

    public void setApoderadoName(String apoderadoName) {
	this.apoderadoName = apoderadoName;
    }

    public String getApoderadoName() {
	return apoderadoName;
    }

    public void setApoderadoLastName(String apoderadoLastName) {
	this.apoderadoLastName = apoderadoLastName;
    }

    public String getApoderadoLastName() {
	return apoderadoLastName;
    }

    public void setApoderadoTipoDoc(String apoderadoTipoDoc) {
	this.apoderadoTipoDoc = apoderadoTipoDoc;
    }

    public String getApoderadoTipoDoc() {
	return apoderadoTipoDoc;
    }

    public void setApoderadoDoc(String apoderadoDoc) {
	this.apoderadoDoc = apoderadoDoc;
    }

    public String getApoderadoDoc() {
	return apoderadoDoc;
    }
    
    public void setFechaBaja(String _fechaBaja) {
        fechaBaja = _fechaBaja;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void retrieveApoderadoData() {
	ResultSet apoderado = LibSQL.exFunction("taxes.getapoderado", idCatastro);
	try {
	    if (apoderado.next()) {
	        apoderadoName = apoderado.getString("nombre");
	        apoderadoLastName = apoderado.getString("apellido");
	        apoderadoTipoDoc = apoderado.getString("tipodoc");
	        apoderadoDoc = apoderado.getString("documento");
	    }
	} catch (SQLException e) {
	    // TODO
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getCatastro", idCatastro + ",'" + domnudoc + "'");

	try {
            if (result.next()) {
                dpto = result.getInt("dpto");
                departamento = result.getString("departamento");
                localidad = result.getString("localidad");
                tecloc = result.getInt("tecloc");
                catastro = result.getInt("numero");
                domcon = result.getString("domcon");
                domtidoc = result.getString("domtidoc");
                domnudoc = result.getString("domnudoc");
                domape = result.getString("propietario");
                domfis = result.getString("domfis");
                dompor = result.getString("dompor");
                tecbavig = result.getInt("tecbavig");
                vigencia = result.getString("vigencia");
                plano = result.getInt("plano");
                tecfeasg = result.getString("tecfeasg");
                tecsec = result.getString("tecsec");
                tecman = result.getString("tecman");
                tecmanl = result.getString("tecmanl");
                tecpar = result.getString("tecpar");
                tecparl = result.getString("tecparl");
                tecunif = result.getString("tecunif"); 
                tecurru = result.getInt("tecurru"); 
                tecorg1 = result.getInt("tecorg1");
                tecorg2 = result.getInt("tecorg2");
                tecderd = result.getInt("tecderd");
                tecderh = result.getInt("tecderh");
                tecobs = result.getString("tecobs");
                tecsuurb = result.getInt("tecsuurb");
                terbe = result.getString("terbe");
                terreno = result.getString("terreno");
                terval = result.getString("terval");
                tervmej = result.getString("tervmej");
                tervcom = result.getString("tervcom");
                valfis = result.getString("valfis");
                resol = result.getString("resol");
                resolfe = result.getString("resolfe");
                dcalle = result.getString("dcalle");
                dnumro = result.getString("dnumro");
                ddesbarrio = result.getString("ddesbarrio");
                urbano = result.getBoolean("urbano");
                nroCuenta = result.getString("barcode");
		idCategoria = result.getInt("idcategoria");
		metrosDeFrente = result.getDouble("metrosfrente");
		esEsquina = result.getBoolean("esquina");
                conProblema = result.getBoolean("conproblema");
            }
            
        } catch (SQLException e) {
            // TODO
	    e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO
        }
    }
    
    public int setIdboletatgsOnCuotaTgs(int _anticipo, int _anio, int _idBoletaTgs){
        int result = LibSQL.getInt("taxes.setCuotasTgsByIdboletaTgs",idCatastro +","+ _anticipo +","+ _anio +","+ _idBoletaTgs);
        return result;
    }
    
    public int setIdboletaInmobOnCuotaInmob(int _anticipo, int _anio, int _idBoletaInmob){
        int result = LibSQL.getInt("taxes.setCuotasInmobByIdboletaInmob",idCatastro +","+ _anticipo +","+ _anio +","+ _idBoletaInmob);
        return result;
    }

    public void setNroCuenta(String _nroCuenta) {
        nroCuenta = _nroCuenta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setMetrosDeFrente(double metrosDeFrente) {
	this.metrosDeFrente = metrosDeFrente;
    }

    public double getMetrosDeFrente() {
	return metrosDeFrente;
    }

    public void setIdCategoria(int idCategoria) {
	this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
	return idCategoria;
    }

    public void setEsEsquina(boolean esEsquina) {
	this.esEsquina = esEsquina;
    }

    public boolean isEsEsquina() {
	return esEsquina;
    }
    
    public String getCategoria(){
	//Sugerencia: Deberia cambiarse y crear la clase categorias y hacer un retieveData dentro de este retrieveData().
	return LibSQL.getString("taxes.getNameCategoria",""+this.getIdCategoria());
    }
    
       
    public String getLeyendaLibreDeuda(int _idtipoimpuesto){
	String manzana = tecman + tecmanl;
	String parcela = tecpar + tecparl;

	String tipoImpuesto = "";
	if(_idtipoimpuesto == 1) {
	    tipoImpuesto = "Tasa General de Servicios";
	} else if (_idtipoimpuesto == 2) {
	    tipoImpuesto = "Impuesto Inmobiliario";
	}
	
	return "\t\tLa " + OrganizationInfo.getOrgName() + ", a través de la Dirección de Rentas - Sección Inmuebles, certifica que el " 
            + "catastro Nº " + (catastro>0?catastro:"-") + ", según Padrón General de Inmuebles de la Provincia de " + OrganizationInfo.getProvince() 
	    + "; Sección: " + (tecsec.length()>0?tecsec.toUpperCase():"-") + "; Manzana: " + (manzana.length()>0?manzana.toUpperCase():"-") 
	    + "; Parcela: " + (parcela.length()>0?parcela.toUpperCase():"-") + "; Categoría: " + (idCategoria>0?idCategoria:"-") 
            + ", a nombre de "+ (domape.length()>0?domape.toUpperCase():"-") + "; DNI/CUIT/CUIL Nº: " + (domnudoc.length()>0?domnudoc.toUpperCase():"-")
	    + ", se encuentra LIBRE DE TODO GRAVAMEN EN CUANTO A " + tipoImpuesto.toUpperCase()
	    + " (hasta el día " + Proced.setFormatDate(LibSQL.getDate("taxes.getfechafinlibredeuda", _idtipoimpuesto + ", " + idCatastro).toString(), true) + ")."
	    + "\n\t\tSe extiende el presente a solicitud de la parte interesada para ser presentado ante las autoridades que así lo solicitan, a los " + Environment.currentDay + " días" + " del mes de " + Environment.monthsArray[(Integer.parseInt(Environment.currentMonth) - 1)] + " del año " + Environment.currentYear + ".-";
    }
    
    public void setMoratoriaTGS(Moratoria moratoriaTGS) {
	this.moratoriaTGS = moratoriaTGS;
    }

    public Moratoria getMoratoriaTGS() {
	return moratoriaTGS;
    }

    public void setMoratoriaInmob(Moratoria moratoriaInmob) {
	this.moratoriaInmob = moratoriaInmob;
    }

    public Moratoria getMoratoriaInmob() {
	return moratoriaInmob;
    }
    
    
   /* ERROR EN LAS SIGUIENTES LINEAS
    * No se utilizan
    * */ 
    public void retrieveMoratoriaTGS() {
	moratoriaTGS = new Moratoria();
	moratoriaTGS.setIdBien(getIdCatastro());
	moratoriaTGS.retrieveDataByIdBien();
    }

    public void retrieveMoratoriaInmob() {
	moratoriaTGS.retrieveDataByIdBien();
    }
    /* FIN ERROR */

    public void setConProblema(boolean conProblema) {
        this.conProblema = conProblema;
    }

    public boolean isConProblema() {
        return conProblema;
    }
}
