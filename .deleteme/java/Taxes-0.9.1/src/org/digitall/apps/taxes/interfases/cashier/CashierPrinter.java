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
 * CashierPrinter.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.apps.taxes.classes.AlicuotaContribucion;
import org.digitall.apps.taxes.classes.BoletaAutomotor;
import org.digitall.apps.taxes.classes.BoletaContribucion;
import org.digitall.apps.taxes.classes.BoletaInmob;
import org.digitall.apps.taxes.classes.BoletaPlanesDePago;
import org.digitall.apps.taxes.classes.BoletaTgs;
import org.digitall.apps.taxes.classes.Cadastral;
import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.Certificado;
import org.digitall.apps.taxes.classes.ContratoPlanDePago;
import org.digitall.apps.taxes.interfases.TaxesTGS;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.misc.BarCode;
import org.digitall.lib.sql.LibSQL;


public class CashierPrinter {

    public CashierPrinter() {
    
    }
    
    public static void printVoucher(int _idpayment,boolean _reimprime) {
	/* hacer un ciclo que recupere los id de boletas y los tipos de boletas correspondientes para hacer la impresion */
	boolean imprimirTicket = false;
	int idboleta = -1;
	int idTipoImpuesto = -1;
	double monto = 0.0;
	String fechaPago = "";
	String nroTicket = "";
	String atendidoPor = "";
	String paymentBarCode = "";
	int nroCaja = -1;
        int idContrato = -1;
	ResultSet result = LibSQL.exFunction("cashier.xmlGetAllBoletasPagadas", _idpayment);
        Vector impresos = new Vector();
	try {
    	    while (result.next()) {
		idboleta = result.getInt("idboleta");
		idTipoImpuesto = result.getInt("idtipoimpuesto");
		monto = result.getDouble("monto");
		fechaPago = result.getString("fecha");
		nroTicket = result.getString("numero");
		paymentBarCode = result.getString("barcode");
		atendidoPor = result.getString("usuario");
	        nroCaja = result.getInt("nroCaja");
		imprimirBoleta(idboleta, idTipoImpuesto);
                if ( (idTipoImpuesto == 0) && (LibSQL.getBoolean("taxes.getPagaCuotaUno",""+ idboleta)) && (!LibSQL.getBoolean("taxes.fueImpresoContrato",""+ idboleta) && (LibSQL.getInt("taxes.generaplandepagoconentrega",""+ idboleta + "," + idTipoImpuesto) == -1)) ) {
                    if (!LibSQL.getBoolean("taxes.fueImpresoContrato",""+ idboleta)) {
                        imprimirContrato(idboleta);
                    }
                } else {
                    if (LibSQL.getBoolean("taxes.getPagaCuotaUno",""+ idboleta) && _reimprime && (idTipoImpuesto == 0) && (LibSQL.getInt("taxes.generaplandepagoconentrega",""+ idboleta + "," + idTipoImpuesto) == -1) ) {
                        imprimirContrato(idboleta);
                    } else {
                        idContrato = LibSQL.getInt("taxes.generaplandepagoconentrega",""+ idboleta + "," + idTipoImpuesto);
                        ContratoPlanDePago contratoPlanDePago = new ContratoPlanDePago();
                        contratoPlanDePago.setidContrato(idContrato);
                        contratoPlanDePago.retrieveData();
                        if (idContrato != -1 && !contratoPlanDePago.isImpreso()){
                            imprimirContratoExistente(contratoPlanDePago);
                        } else {
                            if (idContrato != -1 && _reimprime && !estaImpreso(idContrato,impresos) && (idTipoImpuesto != 0) ){
                                impresos.add("" + idContrato);
                                imprimirContratoExistente(contratoPlanDePago);
                            }   
                        }
                    }
                }
		imprimirTicket = true;
	    }
            
	    /*int idContrato = LibSQL.getInt("taxes.generaplandepagoconentrega",""+ _idpayment);
            if (idContrato != -1){
                imprimirContrato2(idContrato);
            }*/
                
	    if (imprimirTicket) {
		imprimirTicket(_idpayment, monto, fechaPago, nroTicket, paymentBarCode, atendidoPor,nroCaja);
	    }

	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
    
    private static boolean estaImpreso(int _idContrato, Vector _impresos){
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < _impresos.size()) {
            if (_impresos.elementAt(i).toString().equals("" + _idContrato)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public static void imprimirContrato(int _idBoletaMoratoria) {
    /**
     * 2009-09-21 (cesar)
     * 
     * 
     * */
	ContratoPlanDePago contratoPlanDePago = new ContratoPlanDePago();
	contratoPlanDePago.setIdBoletaPlanDePago(_idBoletaMoratoria);
	contratoPlanDePago.retrieveIdContratoByIdBoletaMoratoria();
	contratoPlanDePago.retrieveData();
        if ( contratoPlanDePago.getidContrato() != -1) {
                String impuesto = "";
                String tipoDeBien = "";
                int idTipoImpuesto = Integer.parseInt("0" + contratoPlanDePago.getTipoImpuesto().toString());
                if (idTipoImpuesto == 1) {
                    impuesto = "TASA GENERAL DE SERVICIOS";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 2) {
                    impuesto = "IMPUESTO INMOBILIARIO";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 3) {
                    impuesto = "IMPUESTO AUTOMOTOR";
                    tipoDeBien = "Dominio: ";
                } else {
                    impuesto = "N/A";
                    tipoDeBien = "N/A: ";
                }
                BasicReport report = new BasicReport(CashierButtons.class.getResource("xml/ContratoMoratoria.xml"));
                report.addTableModel("taxes.xmlGetContratoMoratoria", contratoPlanDePago.getidContrato());
                report.addTableModel("taxes.xmlgetCuotasPlanDePago", contratoPlanDePago.getIdPlandePago());
                report.setProperty("impuesto", impuesto);
                report.setProperty("titlebien", tipoDeBien);
                report.setProperty("ordenanza", contratoPlanDePago.getOrdenanza());
                report.setProperty("rescision", contratoPlanDePago.getRescision());
                report.setProperty("porcentajebonificacioncontado", ""+ Format.toDouble(contratoPlanDePago.getPorcentajeBonificacionContado()) +" %");
                report.setProperty("bonificacioncontado", Format.toDouble(contratoPlanDePago.getBonificacionContado()));
                report.setProperty("lblinteresporfinanciacion", "Interés por Financiación");
                report.setProperty("porcentajeinteresporfinanciacion", ""+ Format.toDouble(contratoPlanDePago.getPorcentajeIntFin() * 100.0) +" %");
                report.setProperty("montointfin", contratoPlanDePago.getMontoIntFin());
                report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(contratoPlanDePago.getMontoTotal()) + ".- )");
                BarCode code = new BarCode();
                report.setProperty("barcode", code.getBarCodeEAN13(contratoPlanDePago.getBarcode()));
                report.doReport();
                contratoPlanDePago.setImpreso(true);
                contratoPlanDePago.saveData();
        }
    }
    
    public static void imprimirContratoExistente(ContratoPlanDePago _contratoPlanDePago) {
    /**
     * 2010-06-29 (Matias)
     * 
     * 
     * */
        
        if ( _contratoPlanDePago.getidContrato() != -1) {
                String impuesto = "";
                String tipoDeBien = "";
                int idTipoImpuesto = Integer.parseInt("0" + _contratoPlanDePago.getTipoImpuesto().toString());
                if (idTipoImpuesto == 1) {
                    impuesto = "TASA GENERAL DE SERVICIOS";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 2) {
                    impuesto = "IMPUESTO INMOBILIARIO";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 3) {
                    impuesto = "IMPUESTO AUTOMOTOR";
                    tipoDeBien = "Dominio: ";
                } else {
                    impuesto = "N/A";
                    tipoDeBien = "N/A: ";
                }
                BasicReport report = new BasicReport(CashierButtons.class.getResource("xml/ContratoMoratoria.xml"));
                report.addTableModel("taxes.xmlGetContratoMoratoria", _contratoPlanDePago.getidContrato());
                report.addTableModel("taxes.xmlgetCuotasPlanDePago", _contratoPlanDePago.getIdPlandePago());
                report.setProperty("impuesto", impuesto);
                report.setProperty("titlebien", tipoDeBien);
                report.setProperty("ordenanza", _contratoPlanDePago.getOrdenanza());
                report.setProperty("rescision", _contratoPlanDePago.getRescision());
                report.setProperty("porcentajebonificacioncontado", ""+ Format.toDouble(_contratoPlanDePago.getPorcentajeBonificacionContado()) +" %");
                report.setProperty("bonificacioncontado", Format.toDouble(_contratoPlanDePago.getBonificacionContado()));
                report.setProperty("lblinteresporfinanciacion", "Interés por Financiación");
                report.setProperty("porcentajeinteresporfinanciacion", ""+ Format.toDouble(_contratoPlanDePago.getPorcentajeIntFin() * 100.0) +" %");
                report.setProperty("montointfin", _contratoPlanDePago.getMontoIntFin());
                report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(_contratoPlanDePago.getMontoTotal()) + ".- )");
                BarCode code = new BarCode();
                report.setProperty("barcode", code.getBarCodeEAN13(_contratoPlanDePago.getBarcode()));
                report.doReport();
                _contratoPlanDePago.setImpreso(true);
                _contratoPlanDePago.saveData();
        }
    }
     
    public static void imprimirTicket(int _idpayment, double _monto, String _fechaPago, String _nroTicket, String _paymentBarcode, String _atendidoPor, int _nroCaja) {
	BasicReport report = new BasicReport(CashierMgmt.class.getResource("xml/TicketCaja.xml"));
	String param = "" + _idpayment;
	report.addTableModel("cashier.xmlGetTicketCaja", param);
	report.setProperty("nroticket", _nroTicket);
	report.setProperty("nrocaja", _nroCaja);
	report.setProperty("leatendio", _atendidoPor);
	report.setProperty("fechapago", Proced.setFormatDate(_fechaPago,true));
	report.setProperty("nrooperacion", _idpayment);
	report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(_monto) + ".- )");
	BarCode code = new BarCode();
	report.setProperty("barcode", code.getBarCodeEAN13(_paymentBarcode));
	report.doReport();
    }

    public static void imprimirBoleta(int _idBoleta, int _idTipoImpuesto) {
	switch (_idTipoImpuesto) {
	    case 0:
		loadPrintBoletaMoratoria(_idBoleta);
                //imprimirContrato(_idBoleta);
		break;
	    case 1:
		loadPrintBoletaTGS(_idBoleta);
                ////imprimirContrato(_idBoleta,_idTipoImpuesto);
		break;
	    case 2:
		loadPrintBoletaInmob(_idBoleta);
                ////imprimirContrato(_idBoleta,_idTipoImpuesto);
		break;
	    case 3:
		loadPrintBoletaAutomotor(_idBoleta);
                ////imprimirContrato(_idBoleta,_idTipoImpuesto);
		break;
	    case 4:
		Advisor.messageBox("Funcionalidad No Disponible", "Aviso");
		break;
	    case 5:
		Advisor.messageBox("Funcionalidad No Disponible", "Aviso");
		break;
		//case 6 : loadPrintBoletaContribucion(_idBoleta, _idTipoImpuesto);
	    case 6:
		loadPrintBoletaContribucion(_idBoleta);
		break;
	    default:
		Advisor.messageBox("Impresion no Disponible", "Aviso");
		break;
	}
    }
    
    public static void imprimirContrato(int _idBoleta, int _idTipoImpuesto) {
    /**
     * 2010-04-29 (Matias)
     * 
     * 
     * */
        ContratoPlanDePago contratoPlanDePago = new ContratoPlanDePago();
        contratoPlanDePago.retrieveIdContratoByBoleta(_idBoleta,_idTipoImpuesto);
        contratoPlanDePago.retrieveData();
        if ( contratoPlanDePago.getidContrato() != -1) {
//            if (!contratoPlanDePago.isImpreso()) {
                String impuesto = "";
                String tipoDeBien = "";
                int idTipoImpuesto = Integer.parseInt("0" + contratoPlanDePago.getTipoImpuesto().toString());
                if (idTipoImpuesto == 1) {
                    impuesto = "TASA GENERAL DE SERVICIOS";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 2) {
                    impuesto = "IMPUESTO INMOBILIARIO";
                    tipoDeBien = "Catastro: ";
                } else if (idTipoImpuesto == 3) {
                    impuesto = "IMPUESTO AUTOMOTOR";
                    tipoDeBien = "Dominio: ";
                } else {
                    impuesto = "N/A";
                    tipoDeBien = "N/A: ";
                }
                BasicReport report = new BasicReport(CashierButtons.class.getResource("xml/ContratoMoratoria.xml"));
                report.addTableModel("taxes.xmlGetContratoMoratoria", contratoPlanDePago.getidContrato());
                report.addTableModel("taxes.xmlgetCuotasPlanDePago", contratoPlanDePago.getIdPlandePago());
                report.setProperty("impuesto", impuesto);
                report.setProperty("titlebien", tipoDeBien);
                report.setProperty("ordenanza", contratoPlanDePago.getOrdenanza());
                report.setProperty("rescision", contratoPlanDePago.getRescision());
                report.setProperty("porcentajebonificacioncontado", ""+ Format.toDouble(contratoPlanDePago.getPorcentajeBonificacionContado()) +" %");
                report.setProperty("bonificacioncontado", Format.toDouble(contratoPlanDePago.getBonificacionContado()));
                report.setProperty("lblinteresporfinanciacion", "Interés por Financiación");
                report.setProperty("porcentajeinteresporfinanciacion", ""+ Format.toDouble(contratoPlanDePago.getPorcentajeIntFin()) +" %");
                report.setProperty("montointfin", contratoPlanDePago.getMontoIntFin());
                report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(contratoPlanDePago.getMontoTotal()) + ".- )");
                BarCode code = new BarCode();
                report.setProperty("barcode", code.getBarCodeEAN13(contratoPlanDePago.getBarcode()));
                report.doReport();
                contratoPlanDePago.setImpreso(true);
                contratoPlanDePago.saveData();
            /*} else {
                System.out.println("No se imprime contrato.....!!!!");
            }*/
        }
    }

    private static void loadPrintBoletaMoratoria(int _idBoleta) {
	BoletaPlanesDePago boletaMoratoria = new BoletaPlanesDePago();
	boletaMoratoria.setIdBoletaPlanDePago(_idBoleta);
	boletaMoratoria.retrieveData();
	String impuesto = "";
	String tipoDeBien = "";
	if (boletaMoratoria.getIdTipoImpuesto() == 1) {
	    impuesto = "IMPUESTO TGS";
	    tipoDeBien = "Catastro: ";
	} else if (boletaMoratoria.getIdTipoImpuesto() == 2) {
	    impuesto = "IMPUESTO INMOBILIARIO";
	    tipoDeBien = "Catastro: ";
	} else if (boletaMoratoria.getIdTipoImpuesto() == 3) {
	    impuesto = "IMPUESTO AUTOMOTOR";
	    tipoDeBien = "Dominio: ";
	} else {
	    impuesto = "N/A";
	    tipoDeBien = "N/A";
	}
	BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/PaymentPlanVoucher.xml"));
	report.addTableModel("taxes.getBoletaPlanDePago", boletaMoratoria.getIdBoletaPlanDePago());
	report.addTableModel("taxes.getDetalleBoletaPlanDePago", boletaMoratoria.getIdBoletaPlanDePago());
	report.setProperty("titlebien", tipoDeBien);
	report.setProperty("nrocomprobante", boletaMoratoria.getNumero());
	report.setProperty("impuesto", impuesto);
	report.setProperty("plandepago", boletaMoratoria.getConcepto());
	report.setProperty("cantcuotas", boletaMoratoria.getCantCuotas());
	report.setProperty("nombredescuento", boletaMoratoria.getNombreDescuento());
	report.setProperty("textamount", "( Son Pesos " + Format.NumberToText.numberToText(boletaMoratoria.getTotal()) + ".- )");
	BarCode code = new BarCode();
	report.setProperty("barcode", code.getBarCodeEAN13(boletaMoratoria.getBarcode()));
	report.doReport();
    }   

    private static void loadPrintBoletaTGS(int _idBoletaTgs) {
	BoletaTgs boletaTgs = new BoletaTgs();
	boletaTgs.setIdboletatgs(_idBoletaTgs);
	boletaTgs.retrieveData();
	String xml = "";
	if (boletaTgs.isPagoAnual()) {
	    xml = "xml/BoletaTgsPorPagoAnual.xml";
	} else {
	    xml = "xml/TgsVoucher.xml";
	}
	BasicReport report = new BasicReport(TaxesTGS.class.getResource(xml));
	String param = ""+ boletaTgs.getIdboletatgs();
	report.addTableModel("taxes.getBoletaTgs", param);
	report.addTableModel("taxes.getDetalleBoletaTgs", param);
	report.addTableModel("taxes.getPieDetalleBoletaTgs", param);
	report.setProperty("cantperiodos",boletaTgs.getCantAnticipos());
	report.setProperty("nombredescuento", boletaTgs.getNombreDescuento());
	report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(boletaTgs.getTotal()) + ".- )");
	BarCode code = new BarCode();
	report.setProperty("barcode", code.getBarCodeEAN13(boletaTgs.getBarCode()));
	report.setProperty("fechaproximovto", Proced.setFormatDate(boletaTgs.getFechaProximoVto(),true));
	report.doReport(); 
    }

    private static void loadPrintBoletaInmob(int _idboletaInmob) {
	BoletaInmob boletaInmob = new BoletaInmob();
	boletaInmob.setIdboletainmob(_idboletaInmob);
	boletaInmob.retrieveData();
	String xml = "";
	if (boletaInmob.isPagoAnual()) {
	    xml = "xml/BoletaInmobPorPagoAnual.xml";
	} else {
	    xml = "xml/InmobVoucher.xml";
	}
	BasicReport report = new BasicReport(TaxesTGS.class.getResource(xml));
	String param = ""+ boletaInmob.getIdboletainmob();
	report.addTableModel("taxes.getBoletaInmob", param);
	report.addTableModel("taxes.getDetalleBoletaInmob", param);
	report.addTableModel("taxes.getPieDetalleBoletaInmob", param);
	report.setProperty("cantperiodos", boletaInmob.getCantAnticipos());
	report.setProperty("nombredescuento", boletaInmob.getNombreDescuento());
	report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(boletaInmob.getTotal()) + ".- )");
	BarCode code = new BarCode(); 
	report.setProperty("barcode", code.getBarCodeEAN13(boletaInmob.getBarCode()));
	report.setProperty("fechaproximovto", Proced.setFormatDate(boletaInmob.getFechaProximoVto(),true));
	report.doReport();
    }

    private static void loadPrintBoletaAutomotor(int _idBoletaAutomotor) {
	BoletaAutomotor boletaAutomotor = new BoletaAutomotor();
	boletaAutomotor.setIdboletaautomotor(_idBoletaAutomotor);
	boletaAutomotor.retrieveData();
	String xml = "";
	if (boletaAutomotor.isPagoAnual()) {
	    xml = "xml/BoletaAutomotorPorPagoAnual.xml";
	} else {
	    xml = "xml/CarVoucher.xml";
	}
	BasicReport report = new BasicReport(TaxesTGS.class.getResource(xml));
	String param = ""+ boletaAutomotor.getIdboletaautomotor();
	report.addTableModel("taxes.getBoletaAutomotor", param);
	report.addTableModel("taxes.getDetalleBoletaAutomotor", param);
	report.addTableModel("taxes.getPieDetalleBoletaAutomotor", param);
	BarCode code = new BarCode();
	report.setProperty("cantperiodos", boletaAutomotor.getCantAnticipos());
	report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(boletaAutomotor.getTotal()) + ".- )");
	report.setProperty("barcode", code.getBarCodeEAN13(boletaAutomotor.getBarCode()));
	report.setProperty("fechaproximovto", Proced.setFormatDate(boletaAutomotor.getFechaProximoVto(),true));
	report.doReport();
    }

    private static void loadPrintBoletaContribucion(int _idBoletaContribucion) {
	AlicuotaContribucion alicuotaContribucion;
	BoletaContribucion boletaContribucion = new BoletaContribucion();
	boletaContribucion.setIdBoletaContribucion(_idBoletaContribucion);
	boletaContribucion.retrieveData();
	
        if (boletaContribucion.getIdTipoImpuesto() != 6 ) {
            ResultSet result = LibSQL.exFunction("taxes.getDetalleBoletaContribucion",""+ boletaContribucion.getIdBoletaContribucion() );
            try  {
                while (result.next())  {
                    alicuotaContribucion = new AlicuotaContribucion();
                    alicuotaContribucion.setIdalicuotacontribucion(result.getInt("idalicuotacontribucion"));
                    alicuotaContribucion.retrieveData();
                    if (alicuotaContribucion.isLibredeuda() && !alicuotaContribucion.isBaja() && !alicuotaContribucion.isMulta())  { // LIBRE DEUDA
                        imprimirCertificado(boletaContribucion, result.getInt("iddetalleboletacontribucion"), result.getInt("idtipoimpuesto"),alicuotaContribucion);
                     } else if (alicuotaContribucion.isLibredeuda() && alicuotaContribucion.isBaja() && !alicuotaContribucion.isMulta())  {  // LIBRE DEUDA CON BAJA
                         // desarrollar certificado libre deuda con baja
                     } else if (!alicuotaContribucion.isLibredeuda() && !alicuotaContribucion.isBaja() && alicuotaContribucion.isMulta())  {  // MULTA
                         // desarrollar multa
                     } 
                }
                
            } catch (SQLException e) {
                // TODO 
                System.out.println("error");
                e.printStackTrace();
            }    
        } else {
            imprimirRecibo(boletaContribucion);
        }
    }
    
    public static void imprimirCertificado(BoletaContribucion _boletaContribucion, int _idboletaContribucion, int _idtipoimpuesto, AlicuotaContribucion _alicuotaContribucion) {
	Certificado certificado = new Certificado();
	certificado.setIdDetalleBoletaContribucion(_idboletaContribucion);
	certificado.retrieveDataByIdDetalleBoletaContribucion();
	String xml = _alicuotaContribucion.getXml();
	String tipoImpuesto = "";

	BasicReport report = new BasicReport(TaxesTGS.class.getResource("xml/" + xml));
	String param = "" + _idboletaContribucion;
	report.addTableModel("taxes.xmlGetCertificadoByIdDetalleBoletaContribucion", param);
	report.setProperty("impuesto", certificado.getTitulo2());
	report.setProperty("contribuyente", certificado.getContribuyente());
	BarCode code = new BarCode();

	report.setProperty("barcode", code.getBarCodeEAN13(_boletaContribucion.getBarcode()));
	report.setProperty("bien", certificado.getNroBien());
	report.setProperty("fechalibredeuda",Proced.setFormatDate(Environment.currentDate,true));

	if(_alicuotaContribucion.getIdcontribucion() == 1) {  // Se trata de un libre deuda de un automotor
	    report.setProperty("titulobien", "Dominio:");
	    report.setProperty("titulovalorfiscal", "Modelo:");   
	    report.setProperty("apoderado", certificado.getContribuyente());
	    Car car = new Car();
	    car.setIdAutomotor(certificado.getIdBien());
	    car.retrieveIdDominio();
	    car.retrieveData();
	    report.setProperty("leyenda", car.getLeyendaLibreDeuda());
	    report.setProperty("fechainscr", Proced.setFormatDate(car.getPagadesde(),true));
	    report.setProperty("modelo", car.getModelo());
	    report.setProperty("domicilio", car.getDomicilio());
	} else if(_alicuotaContribucion.getIdcontribucion() == 2) {	
		 if(_idtipoimpuesto == 1){
		     tipoImpuesto = "Tasa General de Servicios";
		 } else if (_idtipoimpuesto == 2){
		         tipoImpuesto = "Impuesto Inmobiliario";
		 }
		
		Cadastral catastro = new Cadastral();
		catastro.setIdCatastro(certificado.getIdBien());
		catastro.retrieveData();
		catastro.retrieveApoderadoData();
		report.setProperty("titulobien", "Catastro:"); 
		report.setProperty("titulovalorfiscal", "Valor fiscal:"); 
		report.setProperty("leyenda", catastro.getLeyendaLibreDeuda(tipoImpuesto));
		report.setProperty("cuentacorriente", catastro.getNroCuenta());   
		report.setProperty("apoderado", catastro.getApoderadoLastName()+" "+catastro.getApoderadoName());   
		report.setProperty("domicilio", catastro.getDcalle() + " " +catastro.getDnumro());
		report.setProperty("valorfiscal", Double.parseDouble(catastro.getValfis()));   
	}
	report.doReport();
    }
    
    private static void imprimirRecibo(BoletaContribucion _boletaContribucion) {
        
        String _xml = "xml/ReciboContribucion.xml";
        BasicReport report = new BasicReport(TaxesTGS.class.getResource(_xml));
        String param = ""+ _boletaContribucion.getIdBoletaContribucion();
        report.addTableModel("taxes.xmlgetDetalleBoletaContribucion", param);
        report.setProperty("anio", _boletaContribucion.getAnio());
        report.setProperty("numeroformateado", _boletaContribucion.getNumeroFormateado());
        report.setProperty("contribuyente", _boletaContribucion.getContribuyente());
        report.setProperty("dni", _boletaContribucion.getNroDocumento());
        report.setProperty("concepto", _boletaContribucion.getConcepto());
        report.setProperty("nombrealicuota","Contribución");
        report.setProperty("montototal",_boletaContribucion.getImporte()); 
        report.setProperty("usuario", _boletaContribucion.getNombreUsuario()); 
        report.setProperty("fechaboleta",Proced.setFormatDate(_boletaContribucion.getFechaPago(), true) ); 
        report.setProperty("textamount","( Son Pesos "+ Format.NumberToText.numberToText(_boletaContribucion.getImporte()) + ".- )");
        BarCode code = new BarCode();
        report.setProperty("barcode", code.getBarCodeEAN13(_boletaContribucion.getBarcode()));
        report.doReport(); 
    }

}
