package org.digitall.apps.calendar.interfaces;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class StickyNotesList {
    StickyNote stickyNote;
    

    public StickyNotesList() {
	
	ResultSet data = LibSQL.exFunction("calendar.getAllStickyNotes", "");

	try {
	    while (data.next()) {
		stickyNote = new StickyNote();
		stickyNote.setIdStickyNote(data.getInt ("idstickynote"));
	        //stickyNote.setIdUser(user.getIdUser());
	        stickyNote.setPointX(data.getDouble("pointx"));
	        stickyNote.setPointY(data.getDouble("pointy"));
	        stickyNote.setDate(data.getString("date"));
	        stickyNote.setText(data.getString("text"));
		
	        /*
		LibSQL.setDateTime();
	        param = Proced.Hora(Environment.currentTime,true,true);
		System.out.println("Hora : " + param);
		param = Environment.currentDate;
		System.out.println("Fecha : " + param);
	        */
					
	    }
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	
    }

}
