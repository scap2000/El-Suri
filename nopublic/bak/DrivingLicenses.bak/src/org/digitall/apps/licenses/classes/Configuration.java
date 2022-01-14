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
