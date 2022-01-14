package org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses;

import java.awt.image.BufferedImage;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class ClassAnuncio {

    private boolean insert = false;
    private int idDetalleRelevamiento = 0;
    private int idRelevamientoPublicidad = 0;
    private String f3a = "";
    private String f3b = "";
    private String f3c = "";
    private String f3d = "";
    private String f3e = "";
    private String f3f = "";
    private String f3g = "";
    private String f3h = "";
    
    private BufferedImage photoImage = null;

    public ClassAnuncio() {
    }

    public void setIdDetalleRelevamiento(int idDetalleRelevamiento) {
	this.idDetalleRelevamiento = idDetalleRelevamiento;
    }

    public int getIdDetalleRelevamiento() {
	return idDetalleRelevamiento;
    }

    public void setIdRelevamientoPublicidad(int idRelevamientoPublicidad) {
	this.idRelevamientoPublicidad = idRelevamientoPublicidad;
    }

    public int getIdRelevamientoPublicidad() {
	return idRelevamientoPublicidad;
    }

    public void setF3a(String f3a) {
	this.f3a = f3a;
    }

    public String getF3a() {
	return f3a;
    }

    public void setF3b(String f3b) {
	this.f3b = f3b;
    }

    public String getF3b() {
	return f3b;
    }

    public void setF3c(String f3c) {
	this.f3c = f3c;
    }

    public String getF3c() {
	return f3c;
    }

    public void setF3d(String f3d) {
	this.f3d = f3d;
    }

    public String getF3d() {
	return f3d;
    }

    public void setF3e(String f3e) {
	this.f3e = f3e;
    }

    public String getF3e() {
	return f3e;
    }

    public void setF3f(String f3f) {
	this.f3f = f3f;
    }

    public String getF3f() {
	return f3f;
    }

    public void setF3g(String f3g) {
	this.f3g = f3g;
    }

    public String getF3g() {
	return f3g;
    }

    public void setF3h(String f3h) {
	this.f3h = f3h;
    }

    public String getF3h() {
	return f3h;
    }

    public void setPhotoImage(BufferedImage photoImage) {
	this.photoImage = photoImage;
    }

    public BufferedImage getPhotoImage() {
	return photoImage;
    }
    
    public int saveData() {             
	String params = insert +","+ idDetalleRelevamiento +","+ idRelevamientoPublicidad +",'"+ f3a +"','"+ f3b +"','"+ f3c +"','"+ f3d + "','" + f3e + "','" + f3f + "','"+ f3g +"','"+ f3h +"'" ;
	int result = -1;
	result = LibSQL.getInt("gea_salta.addRelevamientoPublicidadDetalle",params);
	idDetalleRelevamiento = result;
	
	if (result != -1 && photoImage != null) {
	    if (!LibIMG.saveImage(photoImage, "	gea_salta.relevamientopublicidad_2009_detalle", "foto", "WHERE ididdetallerelevamiento = " + idDetalleRelevamiento)) {
		//result = -2;
	    }
	}
	return result;
    }

    public void setInsert(boolean insert) {
	this.insert = insert;
    }

    public boolean isInsert() {
	return insert;
    }
}
