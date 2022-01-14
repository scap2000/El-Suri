package org.digitall.apps.calendar.interfaces;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class CoordinatorStickyNote {

    private Vector vectorIDs = new Vector();

    public CoordinatorStickyNote() {
    }

    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("calendar.getAllStickyNotes", "");
	if (vectorIDs.isEmpty()) {
	    try {
		while (data.next()) {
		    StickyNote stickyNote = new StickyNote();
		    stickyNote.setIdStickyNote(data.getInt("idstickynote"));
		    stickyNote.setPointX(data.getDouble("pointx"));
		    stickyNote.setPointY(data.getDouble("pointy"));
		    stickyNote.setDate(data.getString("date"));
		    stickyNote.setText(data.getString("text"));
		    stickyNote.setTitle(stickyNote.getDate());
		    stickyNote.setCoord(this);
		    stickyNote.relocate();
		    stickyNote.setIcon(true);
		    vectorIDs.addElement(data.getString("idstickynote"));
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	} else {
	    try {
		while (data.next()) {
		    if (!existsStickyNote(data.getInt("idstickynote"))) {
			StickyNote stickyNote = new StickyNote();
			stickyNote.setIdStickyNote(data.getInt("idstickynote"));
			stickyNote.setPointX(data.getDouble("pointx"));
			stickyNote.setPointY(data.getDouble("pointy"));
			stickyNote.setDate(data.getString("date"));
			stickyNote.setText(data.getString("text"));
			stickyNote.setCoord(this);
			stickyNote.relocate();
		        stickyNote.setIcon(true);
			vectorIDs.addElement(data.getString("idstickynote"));
		    }
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

    private boolean existsStickyNote(int _idStickyNote) {
	boolean found = false;
	int i = 0;
	while (i < vectorIDs.size() && !found) {
	    if (_idStickyNote == Integer.parseInt(vectorIDs.elementAt(i).toString())) {
		found = true;
	    }
	    i++;
	}
	return found;
    }

    public void remove(int _idStickyNote) {
	vectorIDs.remove(String.valueOf(_idStickyNote));
    }

}
