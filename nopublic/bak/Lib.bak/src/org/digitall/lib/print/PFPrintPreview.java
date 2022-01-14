package org.digitall.lib.print;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;

import org.digitall.lib.icons.IconTypes;

/**
 * Class: PFPrintPreview <p>
 *
 * Provide a print preview window for the print framework. 
 * This class will take a <code>PFDocument</code> object 
 * and display all the pages in the document on screen.<p>
 *
 * Here is a list of the functionnality offer to the user:
 *
 * <ul>
 * <li>Navigation between pages, first, previous, next and last page</li>
 * <li>Zoom-in/out, zoom the page in and out (NOT IMPLEMENTED YET)</li>
 * <li>Print the document directly with the print button</li>
 * </ul>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see JFrame
 */

/**
 * Class: PFPrintPreview <p>
 *
 * Provide a print preview window for the print framework. 
 * This class will take a <code>PFDocument</code> object 
 * and display all the pages in the document on screen.<p>
 *
 * Here is a list of the functionnality offer to the user:
 *
 * <ul>
 * <li>Navigation between pages, first, previous, next and last page</li>
 * <li>Zoom-in/out, zoom the page in and out (NOT IMPLEMENTED YET)</li>
 * <li>Print the document directly with the print button</li>
 * </ul>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see JFrame
 */
public class PFPrintPreview extends JFrame {

    //--- Private instances declarations
    //----------------------------------
    private JPanel viewPanel = new JPanel();
    private PagePanel pagePanel = new PagePanel();
    private BorderLayout mainLayout = new BorderLayout();
    private BorderLayout pageLayout = new BorderLayout();
    private JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
    private JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
    private PFPrintPreviewToolBar toolbar = new PFPrintPreviewToolBar(this);
    private Dimension preferredSize = new Dimension(612+30, 700);
    private PFPage currentPage = null;
    private PFDocument document = null;
    private int pageIndex = 0;
    private int zoom = 1;

    /**
    * Constructor: PFPrintPreview <p>
    *
    */
    public PFPrintPreview(PFDocument parDocument) {
	super();
	document = parDocument;
	init();
    }

    /**
     * Method: setDocument <p>
     * 
     * Set the document to preview
     * 
     * @param parDocument a value of shapeTypeName PFDocument
     */
    public void setDocument() {

    }

    /**
     * Method: zoom <p>
     * 
     * Not implemented yet
     * 
     * @param parZoomFactor a value of shapeTypeName int
     */
    public void setZoomIn(int parZoom) {
	zoom = parZoom;
    }

    /**
     * Method: setZoomOut <p>
     * 
     * Not implemented yet
     * 
     * @param parZoom a value of shapeTypeName int
     */
    public void setZoomOut(int parZoom) {
	zoom = parZoom;
    }

    /**
     * Method: getZoom <p>
     * 
     * @return a value of shapeTypeName int
     */
    public int getZoom() {
	return (zoom);
    }

    /**
    * Method: nextPage <p>
    *
    * Preview the next page in the document
    *
    */
    public void nextPage() {
	pageIndex++;
	if (pageIndex > document.getPageCount() - 1)
	    pageIndex--;
	renderPage();
    }

    /**
    * Method: previousPage <p>
    *
    * Preview the previous page in the document
    *
    */
    public void previousPage() {
	pageIndex--;
	if (pageIndex < 0)
	    pageIndex = 0;
	renderPage();
    }

    /**
    * Method: firstPage <p>
    *
    * Preview the first page in the document
    *    
    */
    public void firstPage() {
	pageIndex = 0;
	renderPage();
    }

    /**
    * Method: lastPage <p>
    *
    * Preview the last page in the document   
    *
    */
    public void lastPage() {
	pageIndex = document.getPageCount() - 1;
	renderPage();
    }

    /**
    * Method: print <p>
    *
    * Print the document to the printer
    *
    */
    public void print() {
	document.print();
    }

    /**
    * Method: renderPage <p>
    *
    * Ask the <code>pagePanel</code> to 
    * render the current page
    *
    */
    private void renderPage() {
	pagePanel.renderPage(document.getPage(pageIndex));
    }

    /**
     * Method: getPreferredSize <p>
     * 
     * @return a value of shapeTypeName Dimension
     */
    public Dimension getPreferredSize() {
	return (preferredSize);
    }

    /**
    * Method: init <p>
    *
    * Init this class and all the Swing controls
    *
    */
    private void init() {
	//--- Init this frame
	this.getContentPane().setLayout(mainLayout);
	this.getContentPane().add(toolbar, BorderLayout.NORTH);
	this.getContentPane().add(pagePanel, BorderLayout.CENTER);
	this.getContentPane().add(verticalScrollBar, BorderLayout.EAST);
	this.getContentPane().add(horizontalScrollBar, BorderLayout.SOUTH);
	this.setTitle("Vista preliminar boleta de impuestos: " + document.getDocumentName());
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setPreferredSize(document.getPreferredSizePixels());
	this.setSize(document.getPreferredSizePixels());
	//this.pack();
	this.setVisible(true);
	//--- Init the pagePanel
	pagePanel.setBackground(Color.white);
	pagePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
	pagePanel.setPreferredSize(document.getPreferredSizePixels());
	renderPage();
    }

    /**
    * Class: PagePanel <p>
    *
    * Represent a page in the preview window
    *
    * @author Jean-Pierre Dube <jpdube@videotron.ca>
    * @version 1.0
    * @since 1.0
    * @see JPanel
    */
    private class PagePanel extends JPanel {

	//--- Private instances declarations
	//----------------------------------
	private PFPage page;
	private int zoom = 1;

	/**
       * Constructor: PagePanel <p>
       *
       */
	public PagePanel() {
	    super();
	}

	/**
	 * Method: setZoomIn <p>
	 * 
	 * @param parZoom a value of shapeTypeName int
	 */
	public void setZoomIn(int parZoom) {
	    zoom = parZoom;
	    this.setSize(this.getSize().width * zoom, this.getSize().height * zoom);
	    repaint();
	}

	/**
	 * Method: setZoomOut <p>
	 * 
	 * @param parZoom a value of shapeTypeName int
	 */
	public void setZoomOut(int parZoom) {
	    zoom = parZoom;
	    this.setSize(this.getSize().width / zoom, this.getSize().height / zoom);
	    repaint();
	}

	/**
	 * Method: renderPage <p>
	 * 
	 * @param parPage a value of shapeTypeName PFPage
	 */
	public void renderPage(PFPage parPage) {
	    page = parPage;
	    repaint();
	}

	/**
	 * Method: paint <p>
	 * 
	 * @param parG a value of shapeTypeName Graphics
	 */
	public void paint(Graphics parG) {
	    super.paint(parG);
	    Dimension size = this.getSize();
	    double scaleFactor = 0.77;
	    BufferedImage doubleBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2d = (Graphics2D)doubleBuffer.getGraphics();
	    g2d.setColor(Color.white);
	    g2d.fillRect(0, 0, size.width, size.height);
	    g2d.scale(scaleFactor, scaleFactor);
	    g2d.setColor(Color.red);
	    //g2d.drawRect(0, 0, size.width, size.height);
	    g2d.setColor(Color.black);
	    //g2d.drawRect(0,0,(int)page.getPageFormat().getPageSize().getHeight().getPoints(),(int)page.getPageFormat().getPageSize().getWidth().getPoints());
	    if (page != null) {
		PageFormat pf = page.getPageFormat().getPageFormat();
		page.print(g2d, pf, 0);
		//System.out.println("G2DSIZE: " + pf.getWidth() + "-" + pf.getHeight());
	    }
	    if (doubleBuffer != null) {
		parG.drawImage(doubleBuffer, 0, 0, this);
	    }
	}

    }

    /**
    * Class: PFPrintPreviewToolBar <p>
    *
    * This class responsability is to display the
    * toolbar and handle the user requests
    *
    * @author Jean-Pierre Dube <jpdube@videotron.ca>
    * @version 1.0
    * @since 1.0
    * @see JToolBar
    * @see ActionListener
    */
    public class PFPrintPreviewToolBar extends JToolBar implements ActionListener {

	//--- Private instances declarations
	//----------------------------------
	//--- Buttons
	private JButton firstPage = new JButton();
	private JButton lastPage = new JButton();
	private JButton nextPage = new JButton();
	private JButton previousPage = new JButton();
	private JButton zoomIn = new JButton();
	private JButton zoomOut = new JButton();
	private JButton print = new JButton();
	PFPrintPreview preview;

	/**
	 * Constructor: PFPrintPreviewToolBar <p>
	 * 
	 * @param parPreview a value of shapeTypeName PFPrintPreview
	 */
	public PFPrintPreviewToolBar(PFPrintPreview parPreview) {
	    super();
	    preview = parPreview;
	    init();
	}

	/**
       * Method: init <p>
       *
       * Init this class and all the Swing controls
       *
       */
	private void init() {
	    //--- Init the buttons
	    firstPage.setIcon(IconTypes.getIcon("iconos/printfw/FirstPage.gif"));
	    firstPage.setActionCommand("firstPage");
	    firstPage.addActionListener(this);
	    previousPage.setIcon(IconTypes.getIcon("iconos/printfw/PreviousPage.gif"));
	    previousPage.setActionCommand("previousPage");
	    previousPage.addActionListener(this);
	    nextPage.setIcon(IconTypes.getIcon("iconos/printfw/NextPage.gif"));
	    nextPage.setActionCommand("nextPage");
	    nextPage.addActionListener(this);
	    lastPage.setIcon(IconTypes.getIcon("iconos/printfw/LastPage.gif"));
	    lastPage.setActionCommand("lastPage");
	    lastPage.addActionListener(this);
	    zoomIn.setIcon(IconTypes.getIcon("iconos/printfw/ZoomIn.gif"));
	    zoomIn.setActionCommand("zoomIn");
	    zoomIn.addActionListener(this);
	    zoomIn.setEnabled(false);
	    zoomOut.setIcon(IconTypes.getIcon("iconos/printfw/ZoomOut.gif"));
	    zoomOut.setActionCommand("zoomOut");
	    zoomOut.addActionListener(this);
	    zoomOut.setEnabled(false);
	    print.setIcon(IconTypes.getIcon("iconos/printfw/Print.gif"));
	    print.setActionCommand("print");
	    print.addActionListener(this);
	    //--- Init the toolbar
	    this.add(firstPage);
	    this.add(previousPage);
	    this.add(nextPage);
	    this.add(lastPage);
	    this.add(zoomIn);
	    this.add(zoomOut);
	    this.add(print);
	}

	/**
	 * Method: actionPerformed <p>
	 * 
	 * @param parEvent a value of shapeTypeName ActionEvent
	 */
	public void actionPerformed(ActionEvent parEvent) {
	    String command = parEvent.getActionCommand();
	    if (command.equals("nextPage"))
		preview.nextPage();
	    else if (command.equals("previousPage"))
		preview.previousPage();
	    else if (command.equals("firstPage"))
		preview.firstPage();
	    else if (command.equals("lastPage"))
		preview.lastPage();
	    else if (command.equals("print"))
		preview.print();
	}

    }

}// PFPrintPreview
