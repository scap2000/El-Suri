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
