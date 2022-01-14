package org.digitall.lib.print;

import java.util.Vector;

public class InvoiceFieldsList extends Vector<InvoiceField>{

    public InvoiceFieldsList() {
	
    }
    
    public InvoiceField getInvoiceField(String _name) {
	int i = 0;
	boolean found = false;
	InvoiceField _field = null;
	while (i < size() && !found) {
	    if (elementAt(i).getName().equalsIgnoreCase(_name)) {
		_field = elementAt(i);
		found = true;
	    }
	}
	return null;
    }

}
