package org.digitall.apps.licenses.classes;

import org.digitall.lib.sql.LibSQL;

public class Category {

    private int idcategory = -1;
    private String name = "";
    private double price = 0;  
    private String estado = "";
    
    public Category() {
    
    }
    
    public Category(int _idCategory) {
        idcategory = _idCategory;
    }
    
    public void setIdcategory(int _idcategory) {
        idcategory = _idcategory;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double _price) {
        price = _price;
    }

    public double getPrice() {
        return price;
    }

    public void setEstado(String _estado) {
        estado = _estado;
    }

    public String getEstado() {
        return estado;
    }
    
    public int saveData(){
        int result = -1;
        String params = "";
        if (idcategory == -1)  {
            params = "'" + name + "'," + price;
            result = LibSQL.getInt("licenses.addCategory",params);
        } else  {
            params = idcategory +",'"+ name + "'," + price;
            result = LibSQL.getInt("licenses.setCategory",params);
        }
        return result;
    }
    
}
