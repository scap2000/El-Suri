package org.digitall.apps.projecttask.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Task {
    
    private int idTask = -1;
    private String name = "";
    private int idParent = -1;
    private int estimationTime = -1;
    private int priority = -1;
    private String startDate = "";
    
    

    public Task () {
    
    }

    public Task(int _idtask) {
          loadData(_idtask); 
    }

    public void setIdtask(int _idTask) {
	idTask = _idTask;
    }

    public int getIdtask() {
	return idTask;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setEstimationTime(int _estimationTime) {
	estimationTime = _estimationTime;
    }

    public int getEstimationTime() {
	return estimationTime;
    }

    public void setPriority(int _priority) {
	priority = _priority;
    }

    public int getPriority() {
	return priority;
    }

    public void setStartDate(String _startDate) {
	startDate = _startDate;
    }

    public String getStartDate() {
	return startDate;
    }

   public void  loadData(int _idTask){

	//OBTENER DATOS DE LA BASE DE DATOS
	idTask = _idTask;
	String params = String.valueOf(_idTask);
	ResultSet data = LibSQL.exFunction("task.gettask", params);

	try {
	    if (data.next()) {
	        
		name = data.getString("name");
		idParent = data.getInt("idparent");
	        estimationTime = data.getInt("estimationtime");
	        priority = data.getInt("priority");
	        startDate = data.getString("startdate");
	        //Proced.setFormatDate(data.getString("startdate"),true)
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
   
   }
   
    public void  loadDataDefault(){

	idTask = -1;
	name = "";
	idParent = -1;
	estimationTime = -1;
	priority = -1;
	startDate = "";  
    
    }
   
   private void load () {
       
       idTask = -1;
       name = "";
       idParent = -1;
       estimationTime = -1;
       priority = -1;
       startDate = "";
   
   }
   
   public void saveData () {
    
       StringBuffer params = new StringBuffer();
       
       params.append("'"+ name + "'," +  idParent + ",'" + estimationTime + "','" + priority + "','" + startDate + "'");
       idTask = LibSQL.getInt("task.addtask", params.toString());
    
   }

    public void setIdParent(int _idParent) {
	idParent = _idParent;
    }

    public int getParent() {
	return idParent;
    }

}
