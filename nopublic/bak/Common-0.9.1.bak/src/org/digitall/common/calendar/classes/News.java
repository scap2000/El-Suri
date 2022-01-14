package org.digitall.common.calendar.classes;

import org.digitall.lib.sql.LibSQL;

public class News {

    private int idnew = -1;
    private int idsenderuser = -1;
    private int idrecipientuser = -1;
    private int idpriority = -1;
    private int idstatus = -1;
    private String text = "";
    private boolean statusread = false;
    private boolean statusanswer = false;
    private String date = "";
    private String subject = "";
    private String estado = "";


    public News() {

    }

    public void setIdnew(int _idnew) {
	idnew = _idnew;
    }

    public int getIdnew() {
	return idnew;
    }

    public void setIdsenderuser(int _idsenderuser) {
	idsenderuser = _idsenderuser;
    }

    public int getIdsenderuser() {
	return idsenderuser;
    }

    public void setIdrecipientuser(int _idrecipientuser) {
	idrecipientuser = _idrecipientuser;
    }

    public int getIdrecipientuser() {
	return idrecipientuser;
    }

    public void setIdpriority(int _idpriority) {
	idpriority = _idpriority;
    }

    public int getIdpriority() {
	return idpriority;
    }

    public void setIdstatus(int _idstatus) {
	idstatus = _idstatus;
    }

    public int getIdstatus() {
	return idstatus;
    }

    public void setText(String _text) {
	text = _text;
    }

    public String getText() {
	return text;
    }

    public void setStatusread(boolean _statusread) {
	statusread = _statusread;
    }

    public boolean isStatusread() {
	return statusread;
    }

    public void setStatusanswer(boolean _statusanswer) {
	statusanswer = _statusanswer;
    }

    public boolean isStatusanswer() {
	return statusanswer;
    }

    public void setDate(String _date) {
	date = _date;
    }

    public String getDate() {
	return date;
    }

    public void setSubject(String _subject) {
	subject = _subject;
    }

    public String getSubject() {
	return subject;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData(){
	int result = -1;
	String params = ""+ idrecipientuser +","+ idpriority +","+ 
			idstatus +",'"+ text + "','"+ subject + "'";
	if (idnew == -1)  {
	    result = LibSQL.getInt("calendar.addNews", params);
	    idnew = result;
	} else {
	    params = idnew + params;
	    result = LibSQL.getInt("calendar.setNews", params); //falta crearla
	}
	return result;
    }

}
