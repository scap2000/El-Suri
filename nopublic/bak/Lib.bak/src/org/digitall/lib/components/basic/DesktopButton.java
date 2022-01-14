package org.digitall.lib.components.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.digitall.lib.data.Format;
import org.digitall.lib.icons.IconTypes;

public class DesktopButton extends BasicButton {

    private String title;
    private ImageIcon animationIcon;
    private Icon originalIcon;
    private Timer animation;
    private int period = 25;
    private boolean shrinking = true;

    public DesktopButton(String _title) {
	this(_title, null);
    }

    public DesktopButton(String _title, ImageIcon _icon) {
	super(Format.toHtmlCentered(_title), _icon);
	title = _title;
	animation = new Timer(period, new ActionListener() {

			   public void actionPerformed(ActionEvent e) {
			       animationIcon = (ImageIcon)getIcon();
			       if (shrinking) {
				   setIcon(IconTypes.getScaledIcon(animationIcon, (int)(animationIcon.getIconWidth() * 1.1), (int)(animationIcon.getIconHeight() * 0.9)));
				   shrinking = animationIcon.getIconHeight() * 1.5 > originalIcon.getIconHeight();
			       } else {
				   setIcon(IconTypes.getScaledIcon(animationIcon, (int)(animationIcon.getIconWidth() * 0.9), (int)(animationIcon.getIconHeight() * 1.1)));
				   shrinking = animationIcon.getIconHeight() > originalIcon.getIconHeight();
			       }
			   }

		       }
	    );
	addMouseListener(new MouseAdapter() {

		      public void mousePressed(MouseEvent e) {
			  setText(Format.toHtmlCenteredUnderline(title));
		      }

		      public void mouseReleased(MouseEvent e) {
			  setText(Format.toHtmlCentered(title));
		      }

		      public void mouseEntered(MouseEvent e) {
			  setText(Format.toHtmlCenteredUnderline(title));
		      }

		      public void mouseExited(MouseEvent e) {
			  setText(Format.toHtmlCentered(title));
		      }

		  }
	);
	
	
	addKeyListener(new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent keyEvent) {
		    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			doClick();
		    }
		}

	    }
	);

	
    }
    
    public void setTitle(String _title) {
	title = _title;
	setText(Format.toHtmlCentered(title));
    }

}
