package org.digitall.lib.misc;

import java.awt.Window;
import java.awt.event.WindowEvent;

/**
 * <p>Title: Interface for modal component.</p>
 * <p>Description: Enhancements for javax.swing</p>
 * <p>Copyright: Copyright (c) 2001-2004</p>
 *
 * <p>This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.</p>
 *
 * <p>This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.</p>
 *
 * <p>You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA</p>
 *
 * @author Jene Jasper
 * @version 1.0
 */
public interface InputBlocker {

    /**
     * <p>Set status for modal component to busy and thus blocked or unblocked.</p>
     *
     * @param busy modal status.
     * @param blockingWindow child window that blocks owner.
     */
    public void setBusy(boolean busy, Window blockingWindow);

    /**
     * <p>Get an indication if the modal component is blocked or not.</p>
     *
     * @return modal status.
     */
    public boolean isBusy();

    /**
     * <p>Add an additional window that should be blocked at the same time as the owner/parent is blocked.</p>
     *
     * @param window Window
     */
    public void addAdditionalModalToWindow(Window window);

    /**
     * <p>Activate the first window in the chain of blocking windows that isn't busy.</p>
     *
     * @param windowEvent WindowEvent optional window event that triggered this call.
     * @return boolean Indication if the window or one in the chain of blocking windows is activated.
     */
    public boolean activateFirstAvailableBlockingWindow(WindowEvent windowEvent);

}
