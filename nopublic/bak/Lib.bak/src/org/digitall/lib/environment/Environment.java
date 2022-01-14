package org.digitall.lib.environment;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.StatusBar;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.data.Format;
import org.digitall.lib.dictionary.DictionaryConfig;
import org.digitall.lib.org.Persons;
import org.digitall.lib.resources.ResourcesManager;
import org.digitall.lib.sql.LibSQL;

public abstract class Environment {

    public static String SYSTEM_VERSION = "2009.08.002";

    public static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    //Modos de ejecucion de los programas
    public static int UNSETMODE = -1;
    public static int DESKTOPMODE = 0;
    public static int STANDALONEMODE = 1;
    public static StatusBar jpStatusBar = new StatusBar();
    public static String currentDate = "";
    public static String currentTime = "";
    public static String firstDayOfYear = "";
    public static String currentDay = "";
    public static String currentMonth = "";
    public static String currentYear = "";
    public static String currentDayNumber = "";
    public static String currentDayName = "";
    public static String currentDayNumberOfYear = "";
    public static String currentWeek = "";
    public static int popupCounter = 0;
    public static final Locale DEFAULT_LOCALE = Format.argentina;
    public static final char DEFAULT_MONETARY_SIGN = '$';
    public static BasicDesktop mainDesktop;
    public static BasicTabbedPane mainTabbedPane;
    public static String sessionUser = "";
    public static int idUser = -1;
    public static Persons person;
    public static boolean debugMode = true;
    public static BasicDesktop desktops[] = new BasicDesktop[0];
    public static int animationStep = 10;
    public static ConfigFile cfg = new ConfigFile("ddesktop.conf");
    public static ConfigFile defaultCfg = new ConfigFile("default.conf");
    public static ConfigFile lang = new ConfigFile(DictionaryConfig.getDictionaryURL());
    public static final String cfgNullProperty = cfg.nullProperty;
    public static String developer = "DIGITALL S.H.";
    public static String organization = OrganizationInfo.getOrgName();
    public static String MODULE_ASSETS = "<html><p align=center>Personal, Patrimonio y Sueldos</html>";
    public static String MODULE_CASHFLOW = "<html><p align=center>Administración de Fondos</html>";
    public static String MODULE_GAIA = "<html><p align=center>Sistema de Información Geográfica</html>";
    public static String MODULE_MAIN = "<html><p align=center>Principal</html>";
    public static String MODULE_REPORTS = "<html><p align=center>Generador<br>de Informes</html>";
    public static String MODULE_RESOURCES = "<html><p align=center>Administración<br>de pedidos de materiales</html>";
    public static String MODULE_STICKYNOTES = "<html><p align=center>Anotaciones</html>";
    public static String MODULE_TAXES = "<html><p align=center>Recaudación</html>";
    public static Vector<Integer> desktopHistory = new Vector<Integer>();
    private static GregorianCalendar serverTime = new GregorianCalendar();
    public static BasicLabel lblCalendar = new BasicLabel();
    public static BasicLabel lblMonthName = new BasicLabel();
    public static BasicLabel lblDayName = new BasicLabel();
    public static BasicLabel lblDayNumber = new BasicLabel();
    private static Vector<BasicInternalFrame> unfinishedTasksVector = new Vector();
    public static Class mainClass = ResourcesManager.class;
    public static JFrame mainFrame = null;
    public static String defaultCountry = "Argentina";
    public static String defaultProvince = "SALTA (Argentina)";
    public static String defaultLocation = "SALTA (SALTA)";
    private static Timer calendarTimer = null;
    public static final String[] daysArray = new String[7];
    public static final String[] monthsArray = new String[12];
    
    public static boolean isSQLConnected() {
	return LibSQL.isConnected();
    }

    public static void setIndeterminate(boolean _indeterminate) {
	jpStatusBar.setIndeterminate(_indeterminate);
    }

    public static void setMainDesktop(BasicDesktop _mainDesktop) {
	mainDesktop = _mainDesktop;
    }

    public static void setMainTabbedPane(BasicTabbedPane _mainTabbedPane) {
	mainTabbedPane = _mainTabbedPane;
    }

    public static void setDebugMode() {
	debugMode = true;
    }

    public static void setNormalMode() {
	debugMode = false;
    }

    public static void setDesktops(BasicDesktop[] _desktops) {
	mainDesktop = _desktops[0];
	desktops = _desktops;
    }

    public static BasicDesktop[] getDesktops() {
	return desktops;
    }

    public static BasicDesktop getDesktop(String _name) {
	BasicDesktop _return = new BasicDesktop();
	int i = 0;
	boolean found = false;
	while (i < desktops.length && !found) {
	    if (desktops[i].getName().equals(_name)) {
		_return = desktops[i];
		found = true;
	    }
	    i++;
	}
	if (!found) {
	    _return = mainDesktop;
	}
	return _return;
    }

    public static int getDesktopIndex(String _name) {
	int _return = -1;
	int i = 0;
	boolean found = false;
	while (i < desktops.length && !found) {
	    if (desktops[i].getName().equals(_name)) {
		_return = i;
		found = true;
	    }
	    i++;
	}
	if (!found) {
	    _return = 0;
	}
	return _return;
    }

    public static BasicDesktop getActiveDesktop() {
	try {
	    return desktops[mainTabbedPane.getSelectedIndex()];

	} catch (Exception ex) {
	    return null;
	}
    }

    public static void addDesktopIndexToHistory(int _index) {
	desktopHistory.add(_index);
    }

    public static void setUniversalTime() {
	ResultSet timeRecord = LibSQL.exQuery("select now() as now, extract(timezone_hour from now()) as timezone_hour");
	try {
	    if (timeRecord.next()) {
		serverTime.setTimeInMillis(timeRecord.getDate("now").getTime() + timeRecord.getTime("now").getTime() + (timeRecord.getInt("timezone_hour") * (60 * 60 * 1000)));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	}
	GregorianCalendar localTime = new GregorianCalendar();
	localTime.setTime(Calendar.getInstance().getTime());
	if (!(localTime.get(Calendar.DAY_OF_MONTH) == serverTime.get(Calendar.DAY_OF_MONTH) && localTime.get(Calendar.MONTH) == serverTime.get(Calendar.MONTH) && localTime.get(Calendar.YEAR) == serverTime.get(Calendar.YEAR))) {
	    Advisor.messageBox("El sistema de fechas es incorrecto\nLa fecha del servidor y la de la PC local son diferentes\nLa aplicación se cerrará","Error");
	    System.exit(0);
	}
	daysArray[0] = Environment.lang.getProperty("Sunday");
	daysArray[1] = Environment.lang.getProperty("Monday");
	daysArray[2] = Environment.lang.getProperty("Tuesday");
	daysArray[3] = Environment.lang.getProperty("Wednesday");
	daysArray[4] = Environment.lang.getProperty("Thursday");
	daysArray[5] = Environment.lang.getProperty("Friday");
	daysArray[6] = Environment.lang.getProperty("Saturday");
	monthsArray[0] = Environment.lang.getProperty("January");
	monthsArray[1] = Environment.lang.getProperty("February");
	monthsArray[2] = Environment.lang.getProperty("March");
	monthsArray[3] = Environment.lang.getProperty("April");
	monthsArray[4] = Environment.lang.getProperty("May");
	monthsArray[5] = Environment.lang.getProperty("June");
	monthsArray[6] = Environment.lang.getProperty("July");
	monthsArray[7] = Environment.lang.getProperty("August");
	monthsArray[8] = Environment.lang.getProperty("September");
	monthsArray[9] = Environment.lang.getProperty("October");
	monthsArray[10] = Environment.lang.getProperty("November");
	monthsArray[11] = Environment.lang.getProperty("December");
	//lblCalendar.setForeground(Color.black);
	if (calendarTimer == null) {
	    calendarTimer = new Timer(1000, new ActionListener() {
    
					 public void actionPerformed(ActionEvent e) {
					     serverTime.add(Calendar.SECOND, 1);
					     String day = daysArray[serverTime.get(Calendar.DAY_OF_WEEK)-1];
					     String month = monthsArray[serverTime.get(Calendar.MONTH)];
					     String time =  reformat(serverTime.get(Calendar.HOUR_OF_DAY)) + ":" + reformat(serverTime.get(Calendar.MINUTE)) + ":" + reformat(serverTime.get(Calendar.SECOND));
					     lblCalendar.setText(Format.dateToText(serverTime) + " - " + time);
					     lblMonthName.setText(month.toUpperCase());
					     lblDayName.setText(day.toUpperCase());
					     lblDayNumber.setText(currentDay);
					 }
					 
					 private String reformat(int _num) {
						    return (_num<10?"0"+_num:String.valueOf(_num));
					 }
    
				     }
		);
	    calendarTimer.start();
	}
    }

    public static void addUnfinishedTask(BasicInternalFrame _iFrame) {
	if (!Environment.unfinishedTasksVector.contains(_iFrame)) {
	    unfinishedTasksVector.add(_iFrame);
	}
    }

    public static void removeUnfinishedTask(BasicInternalFrame _iFrame) {
	if (Environment.unfinishedTasksVector.contains(_iFrame)) {
	    unfinishedTasksVector.remove(_iFrame);
	}
    }
    
    public static boolean saveAll() {
	boolean closable = true;
	int i = 0;
	while (unfinishedTasksVector.size() > 0 && i < unfinishedTasksVector.size()) {
	//for (int i = 0; i < unfinishedTasksVector.size(); i++) {
	    BasicDesktop _originalDesktop = unfinishedTasksVector.elementAt(i).getDesktop();
	    if (unfinishedTasksVector.elementAt(i) instanceof ExtendedInternalFrame) {
	        unfinishedTasksVector.elementAt(i).setDesktop(getActiveDesktop());
		unfinishedTasksVector.elementAt(i).show();
	    }
	    if (unfinishedTasksVector.elementAt(i).saveData()) {
	        //removeUnfinishedTask(getUnfinishedTasksVector().elementAt(i));
	    } else {
	        closable = false;
	        Advisor.messageBox("La ventana \"<U>" + unfinishedTasksVector.elementAt(i).getTitle() + "</U>\" tiene una operación pendiente", "Error");
	    }
	    unfinishedTasksVector.elementAt(i).setDesktop(_originalDesktop);
	    i++;
	}
	return closable;
    }

}
