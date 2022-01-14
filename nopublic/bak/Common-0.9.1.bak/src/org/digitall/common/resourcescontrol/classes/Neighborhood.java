package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class Neighborhood {

    private int idLocation = -1;
    private int idneighborhood = -1;
    private String name = "";
    private String estado = "";
    
    public Neighborhood() {
    }

    public void setIdLocation(int _idLocation) {
        idLocation = _idLocation;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdneighborhood(int _idneighborhood) {
        idneighborhood = _idneighborhood;
    }

    public int getIdneighborhood() {
        return idneighborhood;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public int saveData(){
        int result = -1;
        String params= "";
        if (idneighborhood == -1)  {
            params = ""+ idLocation +",'"+ name +"'";
            result = LibSQL.getInt("tabs.addNeighborhood",params);
            idneighborhood = result;
        } else  {
            params = ""+ idneighborhood +",'"+ name +"'";
            result = LibSQL.getInt("tabs.setNeighborhood",params);
        }
        return result;
    }
    
    public void retrieveData(){
        ResultSet result = LibSQL.exFunction("org.getNeighborhood",""+ idneighborhood +","+ idLocation);
        try  {
            if (result.next())  {
                System.out.println("no se desarrollo");
            } else  {
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        } 
    }
    
}
