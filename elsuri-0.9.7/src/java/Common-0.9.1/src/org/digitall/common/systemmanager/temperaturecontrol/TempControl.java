/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * TempControl.java
 *
 * */

package org.digitall.common.systemmanager.temperaturecontrol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class TempControl extends JPanel {

    private JButton btnStart = new JButton();
    private JButton btnStop = new JButton();
    private JPanel jpCenter = new JPanel();
    private Timer tempTimer;
    private String cmdInterpreter = "/bin/sh";
    private String cmdParameter = "-c";
    private String currentFreqCmd = "cat /sys/devices/system/cpu/cpu$number/cpufreq/scaling_cur_freq";
    private String currentTempCmd = "cat /proc/acpi/thermal_zone/THZN/temperature | cut -b 26-28";
    private String availableFreqsCmd = "cat /sys/devices/system/cpu/cpu$number/cpufreq/scaling_available_frequencies";
    private String shiftFreqFile = "/sys/devices/system/cpu/cpu$number/cpufreq/scaling_max_freq";
    private String shiftFreqCmd = "echo $freq > " + shiftFreqFile;
    private String processorsQtyCmd = "cat /sys/devices/system/cpu/present";
    private String topProcessesCmd = "top -b | head -12 | tail -6";
    private String grantAccessCmd = "kdesu chmod 666";
    private String logFilePath = "/tmp/suriTempCtrl.log";
    private transient FileOutputStream logFile;
    private int minTemp = 65;
    private int maxTemp = 85;
    private long forcedFreq = 0;
    private int processorsQty = 0;

    private JLabel lblTemp = new JLabel();
    private JProgressBar[] progressBars;
    private JLabel[] lblProcessors;
    private transient Processor[] processors = new Processor[processorsQty];
    private JLabel lblService = new JLabel();
    private boolean active = false;

    private transient Thread[] burnThreads;
    private JButton btnStartBurning = new JButton();
    private JButton btnStopBurning = new JButton();
    private boolean burning = false;
    private boolean autoStart = false;

    private String strServiceStopped = "El servicio está inactivo";
    private String strServiceRunning = "El servicio se ha activado";
    private JLabel lblTempTitle = new JLabel();
    private JSeparator jSeparator2 = new JSeparator();
    private JTextField tfMinTemp = new JTextField();
    private JTextField tfMaxTemp = new JTextField();
    private JLabel lblMinTemp = new JLabel();
    private JLabel lblMaxTemp = new JLabel();
    private JTextArea taLog = new JTextArea();
    private JScrollPane spLog = new JScrollPane(taLog);
    private JCheckBox showLog = new JCheckBox();
    private JDialog logDialog = new JDialog();
    private static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    private Properties configuration = new Properties();
    private File configFile;
    private JButton btnCfgWizard = new JButton();
    private JButton btnForceFreq = new JButton();

    public static void main(String[] args) {
	JDialog _dialog = new JDialog();
	_dialog.setTitle("Regulador de Temperatura de El Suri");
	_dialog.setLayout(new BorderLayout());
	_dialog.add(new TempControl(), BorderLayout.CENTER);
	_dialog.setSize(new Dimension(413, 318));
	_dialog.setLocation(((graphicsDevice.getDefaultConfiguration().getBounds().width - _dialog.getWidth()) / 2) + graphicsDevice.getDefaultConfiguration().getBounds().x, ((graphicsDevice.getDefaultConfiguration().getBounds().height - _dialog.getHeight()) / 2) + GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().y);
	_dialog.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent windowEvent) {
		    System.exit(0);
		}

		@Override
		public void windowIconified(WindowEvent windowEvent) {
		    //TrayIcon
		}
	    });
	_dialog.setVisible(true);
    }

    public TempControl() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(412, 318));
	this.setPreferredSize(new Dimension(398, 318));
	updateInterface();
	initConf();
	fetchConf();
	logDialog.setTitle("Registro de eventos");
	logDialog.setLayout(new BorderLayout());
	logDialog.add(spLog, BorderLayout.CENTER);
	logDialog.setSize(new Dimension(640, 200));
	logDialog.setLocation(((GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().width - logDialog.getWidth()) / 2) + graphicsDevice.getDefaultConfiguration().getBounds().x, ((graphicsDevice.getDefaultConfiguration().getBounds().height - logDialog.getHeight()) / 2) + GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().y);
	logDialog.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent windowEvent) {
		    showLog.setSelected(false);
		}
	    });
	btnCfgWizard.setText("Conf.");
	btnCfgWizard.setToolTipText("Configurar los parámetros de la aplicación");
	btnCfgWizard.setBounds(new Rectangle(5, 70, 80, 20));
	btnCfgWizard.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnCfgWizard_actionPerformed(e);
		}
	    });
	btnForceFreq.setText("Forzar procesadores a una frecuencia");
	btnForceFreq.setToolTipText("Forzar los procesadores a una frecuencia específica");
	btnForceFreq.setBounds(new Rectangle(24, 175, 365, 20));
	btnForceFreq.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnForceFreq_actionPerformed(e);
		}
	    });
	btnForceFreq.setBackground(Color.YELLOW);
	btnForceFreq.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	btnStart.setText("Iniciar");
	btnStart.setBounds(new Rectangle(5, 10, 80, 21));
	btnStart.setToolTipText("Iniciar el servicio");
	btnStart.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnStart_actionPerformed(e);
		}
	    });
	jpCenter.setLayout(null);
	jpCenter.add(btnCfgWizard, null);
	jpCenter.add(btnForceFreq, null);
	jpCenter.add(showLog, null);
	jpCenter.add(lblMaxTemp, null);
	jpCenter.add(lblMinTemp, null);
	jpCenter.add(tfMaxTemp, null);
	jpCenter.add(tfMinTemp, null);
	jpCenter.add(jSeparator2, null);
	jpCenter.add(lblTempTitle, null);
	jpCenter.add(btnStopBurning, null);
	jpCenter.add(btnStartBurning, null);
	jpCenter.add(lblService, null);
	jpCenter.add(btnStop, null);
	jpCenter.add(lblTemp, null);
	jpCenter.add(btnStart, null);
	taLog.setEditable(false);
	setLayout(new BorderLayout());
	this.add(jpCenter, BorderLayout.CENTER);
	showLog.setText("Ver registro");
	showLog.setBounds(new Rectangle(5, 105, 100, 20));
	showLog.setHorizontalAlignment(SwingConstants.CENTER);
	showLog.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    showLog_actionPerformed(e);
		}
	    });
	tfMinTemp.setBounds(new Rectangle(135, 95, 75, 25));
	tfMinTemp.setHorizontalAlignment(JTextField.CENTER);
	tfMaxTemp.setBounds(new Rectangle(320, 95, 75, 25));
	tfMaxTemp.setHorizontalAlignment(JTextField.CENTER);
	tfMinTemp.addKeyListener(new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
		    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			updateTempParameters();
		    }
		}
	    });
	tfMaxTemp.addKeyListener(new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
		    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			updateTempParameters();
		    }
		}

	    });
	lblMinTemp.setText("Mínima");
	lblMinTemp.setBounds(new Rectangle(135, 75, 75, 14));
	lblMinTemp.setHorizontalAlignment(SwingConstants.CENTER);
	lblMaxTemp.setText("Máxima");
	lblMaxTemp.setBounds(new Rectangle(320, 75, 75, 14));
	lblMaxTemp.setHorizontalAlignment(SwingConstants.CENTER);
	jSeparator2.setBounds(new Rectangle(0, 165, 410, 2));
	lblTempTitle.setText("Temperatura general");
	lblTempTitle.setBounds(new Rectangle(190, 50, 150, 15));
	lblTempTitle.setHorizontalAlignment(SwingConstants.CENTER);
	btnStartBurning.setText("Estresar :(");
	btnStartBurning.setBounds(new Rectangle(5, 140, 120, 20));
	btnStartBurning.setToolTipText("Fuerza los procesadores para calentar el equipo");
	btnStartBurning.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnStartBurning_actionPerformed(e);
		}

	    });
	btnStopBurning.setText("Relajar :)");
	btnStopBurning.setBounds(new Rectangle(135, 140, 120, 20));
	btnStopBurning.setToolTipText("Deja de forzar los procesadores");
	btnStopBurning.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnStopBurning_actionPerformed(e);
		}
	    });
	lblService.setBounds(new Rectangle(95, 10, 305, 20));
	lblService.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 2));
	lblService.setText(strServiceStopped);
	lblService.setFont(new Font("Dialog", 1, 11));
	lblService.setOpaque(true);
	lblService.setHorizontalAlignment(SwingConstants.CENTER);
	btnStop.setText("Parar");
	btnStop.setBounds(new Rectangle(5, 40, 80, 21));
	btnStop.setToolTipText("Parar el servicio");
	btnStop.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnStop_actionPerformed(e);
		}
	    });
	lblTemp.setBounds(new Rectangle(225, 70, 85, 50));
	lblTemp.setHorizontalTextPosition(SwingConstants.CENTER);
	lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
	lblTemp.setFont(new Font("Dialog", 1, 19));
	lblTemp.setBackground(new Color(0, 214, 0));
	lblTemp.setOpaque(true);
	lblTemp.setBorder(BorderFactory.createLineBorder(SystemColor.windowText, 3));
	tfMinTemp.setText(String.valueOf(minTemp));
	tfMaxTemp.setText(String.valueOf(maxTemp));
	if (autoStart) {
	    startService();
	}
    }

    private void updateTempParameters() {
	try {
	    int _minTemp = Integer.parseInt(tfMinTemp.getText());
	    if (_minTemp >= maxTemp) {
		messageBox("No se puede asignar una temperatura mínima mayor a la máxima", "Aviso");
	    } else if (_minTemp < 40) {
		messageBox("No se puede asignar una temperatura tan baja (menor a 40º),\nel procesador nunca se enfría tanto\n(a menos que esté apagado el equipo)", "Aviso");
	    } else {
		minTemp = _minTemp;
	    }
	} catch (NumberFormatException nfe) {
	    messageBox("Error al intentar asignar la temperatura mínima\nSólo se permiten números enteros entre 40 y 95", "Aviso");
	}
	try {
	    int _maxTemp = Integer.parseInt(tfMaxTemp.getText());
	    if (minTemp >= _maxTemp) {
		messageBox("No se puede asignar una temperatura máxima menor a la mínima", "Aviso");
	    } else if (_maxTemp > 95) {
		messageBox("No se puede asignar una temperatura tan alta (mayor a 95º),\nse puede quemar el procesador\n(y eso sería una pena)", "Aviso");
	    } else {
		maxTemp = _maxTemp;
	    }
	} catch (NumberFormatException nfe) {
	    messageBox("Error al intentar asignar la temperatura máxima\nSólo se permiten números enteros entre 40 y 95", "Aviso");
	}
	tfMinTemp.setText(String.valueOf(minTemp));
	tfMaxTemp.setText(String.valueOf(maxTemp));
	saveConf();
    }

    private void btnStart_actionPerformed(ActionEvent e) {
	startService();
    }

    private void snapshot() {
	String _warn = executeCommand(topProcessesCmd);
	logEvent("*************************** ¡CUIDADO! ***************************\n" +
		"Temperatura Actual: " + lblTemp.getText() + " - Mínima: " + minTemp + "º - Máxima: " + maxTemp + "º\n" +
		_warn);
    }

    private void startService() {
	try {
	    logFile = new FileOutputStream(logFilePath, true);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	initProcessors();

	boolean canWrite = true;
	String _readOnlyFiles = "";
	for (int i = 0; i < processorsQty; i++) {
	    String _fileName = shiftFreqFile.replaceAll("\\$number", (processorsQty == 1?"":String.valueOf(i)));
	    File _file = new File(_fileName);
	    if (!_file.exists() || !_file.canWrite()) {
		if (question("Permisos de escritura", "El servicio necesita permisos de escritura en el archivo\n" +
			_fileName + "\n¿Desea intentar darle esos permisos desde aquí?")) {
		    executeCommand(grantAccessCmd + " " + _fileName);
		    if (!_file.exists() || !_file.canWrite()) {
			messageBox("Se intentó pero no se pudo dar permisos de escritura al archivo\n" +
				_fileName + "\nInténtelo manualmente", "Aviso");
			logEvent("Error al intentar dar permisos de escritura al archivo " + _fileName);
			canWrite = false;
			_readOnlyFiles += "\n" +
				_fileName;
		    } else {
			messageBox("Se dieron permisos de escritura al archivo\n" +
				_fileName, "Aviso");
			logEvent("Permisos de escritura asignados al archivo " + _fileName);
		    }
		} else {
		    canWrite = false;
		    _readOnlyFiles += "\n" +
			    _fileName;
		}
	    }
	}
	if (!canWrite) {
	    messageBox("El servicio no se iniciará porque se necesita acceso de escritura a los archivos" + _readOnlyFiles, "Aviso");
	    logEvent("No se pudo iniciar el servicio por privilegios de acceso insuficientes");
	} else {
	    if (processorsQty > 0) {
		tempTimer = new Timer(1000, new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
				updateStatus();
			    }
			});
		active = true;
		logEvent("Servicio iniciado, monitoreando " + processorsQty + (processorsQty != 1?" procesadores":" procesador"));
		tempTimer.start();
	    } else {
		messageBox("Error al intentar iniciar el servicio\nNo se han encontrado procesadores", "Error");
		logEvent("Error al intentar iniciar el servicio, no se han encontrado procesadores para monitorear");
	    }
	}
	updateInterface();
    }

    private void btnStartBurning_actionPerformed(ActionEvent e) {
	startBurning();
    }

    private void startBurning() {
	if (!active) {
	    messageBox("No se recomienda estresar el equipo sin el servicio funcionando", "Aviso");
	}
	burning = true;
	burnThreads = new Thread[processorsQty];
	for (int i = 0; i < burnThreads.length; i++) {
	    Thread _burnThread = new Thread(new Runnable() {

		    public void run() {
			while (burning) {
			    Math.pow(Math.log(Math.random()), Math.random());
			}
		    }
		});
	    burnThreads[i] = _burnThread;
	    logEvent("Comenzando a estresar el procesador " + i);
	    burnThreads[i].start();
	}
	updateInterface();
    }

    private void btnStopBurning_actionPerformed(ActionEvent e) {
	stopBurning();
    }

    private int getTemperature() {
	try {
	    return Integer.parseInt(executeCommand(currentTempCmd));
	} catch (NumberFormatException nfe) {
	    return -1;
	}
    }

    private String executeCommand(String _cmd) {
	String _returns = "";
	try {
	    String[] _command = { cmdInterpreter, cmdParameter, _cmd };
	    Process p = Runtime.getRuntime().exec(_command);
	    BufferedReader _inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    BufferedReader _errorStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	    StringBuilder _output = new StringBuilder();
	    String _line = _inputStream.readLine();
	    while (_line != null) {
		_line = _line.trim();
		_output.append(_line + "\n");
		_line = _inputStream.readLine();
	    }
	    _line = _errorStream.readLine();
	    while (_line != null) {
		_line = _line.trim();
		_output.append("Error: " + _line + "\n");
		_line = _errorStream.readLine();
	    }
	    _returns = _output.toString();
	    _inputStream.close();
	    _errorStream.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return _returns.trim();
    }

    private void logEvent(String _event) {
	if (_event.length() > 0) {
	    if (logFile != null) {
		_event += "\n";
		try {
		    logFile.write(("[" + new Date() + "]: " + _event).getBytes());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    taLog.append("[" + new Date() + "]: " + _event);
	    taLog.setCaretPosition(taLog.getDocument().getLength());
	}
    }

    private void updateStatus() {
	int _temp = getTemperature();
	lblTemp.setText(_temp + "º");

	if (_temp > (maxTemp)) {
	    //Por encima de lo permitido
	    lblTemp.setBackground(Color.RED);
	    lblTemp.setToolTipText("Demasiado caliente");
	    snapshot();
	    //Alarma sonora!!!
	    alarm();
	} else if (_temp >= (int)(maxTemp * .95)) {
	    //Muy caliente
	    lblTemp.setBackground(Color.ORANGE);
	    lblTemp.setToolTipText("Muy caliente");
	} else if (_temp >= (int)(maxTemp * .9)) {
	    //Caliente
	    lblTemp.setBackground(Color.YELLOW);
	    lblTemp.setToolTipText("Caliente");
	} else if (_temp > minTemp) {
	    //Normal
	    lblTemp.setBackground(Color.GREEN);
	    lblTemp.setToolTipText("Normal");
	} else {
	    //Frío
	    lblTemp.setBackground(Color.WHITE);
	    lblTemp.setToolTipText("Helada");
	}

	for (int i = 0; i < processorsQty; i++) {
	    if (forcedFreq == 0) {
		processors[i].updateFreq(_temp);
	    } else {
	        processors[i].setFrequency(forcedFreq);
	    }
	}
	btnForceFreq.setBackground(forcedFreq!=0?Color.RED:Color.YELLOW);
	btnForceFreq.setText(forcedFreq!=0?"Frecuencia forzada a " + forcedFreq:"Forzar procesadores a una frecuencia");
    }

    private void initProcessors() {
	String[] _processorsQty = executeCommand(processorsQtyCmd).split("-");
	try {
	    processorsQty = Integer.parseInt(_processorsQty[_processorsQty.length - 1]);
	} catch (NumberFormatException nfe) {
	    messageBox("No se pudo obtener la cantidad de procesadores\nRevise la configuración de comandos", "Aviso");
	}
	processorsQty++;
	processors = new Processor[processorsQty];
	progressBars = new JProgressBar[processorsQty];
	lblProcessors = new JLabel[processorsQty];
	for (int i = 0; i < processorsQty; i++) {
	    progressBars[i] = new JProgressBar();
	    processors[i] = new Processor(i, progressBars[i]);
	    jpCenter.add(progressBars[i], null);
	    progressBars[i].setBounds(new Rectangle(160, 205 + (25 * i), 195, 20));
	    lblProcessors[i] = new JLabel("Procesador #" + i + ":");
	    lblProcessors[i].setFont(new Font("Dialog", Font.PLAIN, 10));
	    lblProcessors[i].setHorizontalAlignment(SwingConstants.RIGHT);
	    jpCenter.add(lblProcessors[i], null);
	    //lblProcessors[i].setText("Procesador #" + i + ":");
	    lblProcessors[i].setBounds(new Rectangle(60, 205 + (25 * i), 90, 20));
	}

    }

    private void btnStop_actionPerformed(ActionEvent e) {
	stopService();
    }

    private void stopService() {
	tempTimer.stop();
	active = false;
	lblService.setOpaque(true);
	lblTemp.setText("N/A");
	if (burning) {
	    messageBox("Se relajarán los procesadores", "Aviso");
	    stopBurning();
	}
	logEvent("El servicio de monitoreo ha cesado");
	updateInterface();
    }

    public static void messageBox(String _message, String _title) {
	UIManager.put("OptionPane.yesButtonText", "Sí");
	UIManager.put("OptionPane.noButtonText", "No");
	UIManager.put("OptionPane.okButtonText", "Aceptar");
	UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	if (_message == null) {
	    _message = "Mensaje Vacío";
	}
	_message = "<html><p align=center>" + _message.replaceAll("\\n", "<br>") + "</p></html>";
	new JOptionPane().showMessageDialog(new JFrame(), _message, _title, JOptionPane.OK_OPTION);
    }

    public static boolean question(String _title, String _question) {
	UIManager.put("OptionPane.yesButtonText", "Sí");
	UIManager.put("OptionPane.noButtonText", "No");
	UIManager.put("OptionPane.okButtonText", "Aceptar");
	UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	_question = "<html><p align=center>" + _question.replaceAll("\\n", "<br>") + "</p></html>";
	return ((new JOptionPane().showConfirmDialog(new JFrame(), _question, _title, JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION);
    }

    private void alarm() {
	//Reproducir una alarma .ogg/.mp3
    }

    private void stopBurning() {
	burning = false;
	logEvent("Relajando los procesadores");
	updateInterface();
    }

    private void updateInterface() {
	btnStart.setEnabled(!active);
	btnStop.setEnabled(active);
	lblService.setText(active?strServiceRunning:strServiceStopped);
	lblService.setBackground(active?Color.GREEN:Color.RED);
	btnStartBurning.setEnabled(processorsQty > 0 && active && !burning);
	btnStopBurning.setEnabled(processorsQty > 0 && active && burning);
	btnForceFreq.setEnabled(active);
    }

    private void showLog_actionPerformed(ActionEvent e) {
	logDialog.setVisible(showLog.isSelected());
    }

    private String tryToSet(String _message, String _title, String _value, String _expectedValue, boolean _command, boolean _writable) {
	String _result = (String)new JOptionPane().showInputDialog(new JFrame(), _message, _title, JOptionPane.OK_OPTION, null, null, _value);
	if (_result != null) {
	    if (_command) {
		String _cmd = _result.replaceAll("\\$number", (processorsQty == 1?"":"0"));
		if (question("Pregunta", "Se ejecutó el comando " + _cmd + "\nEl valor esperado era: \"" + _expectedValue + "\"\nEl resultado obtenido es\n" +
			executeCommand(_cmd) + "\n¿Desea grabar la configuración?")) {
		    return _result;
		} else {
		    return null;
		}
	    } else {
		String _fileName = _result.replaceAll("\\$number", (processorsQty == 1?"":"0"));
		File _file = new File(_fileName);
		if (_file.exists() && _file.canRead()) {
		    if (!_writable) {
			System.out.println("El archivo " + _file + " ha sido encontrado");
			return _result;
		    } else if (_file.canWrite()) {
			System.out.println("El archivo " + _file + " ha sido encontrado");
			return _result;
		    } else {
			if (question("Error", "El archivo " + _file + " ha sido encontrado pero no se tienen los privilegios suficientes\n¿Desea continuar de todas maneras?")) {
			    return _result;
			} else {
			    return null;
			}
		    }
		} else {
		    if (question("Error", "El archivo " + _file + " no ha sido encontrado o no se tienen los privilegios suficientes\n¿Desea continuar de todas maneras?")) {
			return _result;
		    } else {
			return null;
		    }
		}
	    }
	} else {
	    return null;
	}
    }

    private void fetchConf() {
	try {
	    FileInputStream _inputFile = new FileInputStream(configFile);
	    configuration.load(_inputFile);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String _autoStart = configuration.getProperty("autoStart");
	if (_autoStart != null) {
	    autoStart = new Boolean(_autoStart);
	}
	String _cmdInterpreter = configuration.getProperty("cmdInterpreter");
	if (_cmdInterpreter != null) {
	    cmdInterpreter = _cmdInterpreter;
	}
	String _cmdParameter = configuration.getProperty("cmdParameter");
	if (_cmdParameter != null) {
	    cmdParameter = _cmdParameter;
	}
	String _currentFreqCmd = configuration.getProperty("currentFreqCmd");
	if (_currentFreqCmd != null) {
	    currentFreqCmd = _currentFreqCmd;
	}
	String _currentTempCmd = configuration.getProperty("currentTempCmd");
	if (_currentTempCmd != null) {
	    currentTempCmd = _currentTempCmd;
	}
	String _availableFreqsCmd = configuration.getProperty("availableFreqsCmd");
	if (_availableFreqsCmd != null) {
	    availableFreqsCmd = _availableFreqsCmd;
	}
	String _shiftFreqFile = configuration.getProperty("shiftFreqFile");
	if (_shiftFreqFile != null) {
	    shiftFreqFile = _shiftFreqFile;
	}
	String _shiftFreqCmd = configuration.getProperty("shiftFreqCmd");
	if (_shiftFreqCmd != null) {
	    shiftFreqCmd = _shiftFreqCmd;
	}
	String _processorsQtyCmd = configuration.getProperty("processorsQtyCmd");
	if (_processorsQtyCmd != null) {
	    processorsQtyCmd = _processorsQtyCmd;
	}
	String _topProcessesCmd = configuration.getProperty("topProcessesCmd");
	if (_topProcessesCmd != null) {
	    topProcessesCmd = _topProcessesCmd;
	}
	String _grantAccessCmd = configuration.getProperty("grantAccessCmd");
	if (_grantAccessCmd != null) {
	    grantAccessCmd = _grantAccessCmd;
	}
	String _logFilePath = configuration.getProperty("logFilePath");
	if (_logFilePath != null) {
	    logFilePath = _logFilePath;
	}
	String _maxTemp = configuration.getProperty("maxTemp");
	if (_maxTemp != null) {
	    try {
		maxTemp = Integer.parseInt(_maxTemp);
	    } catch (NumberFormatException nfe) {
		nfe.printStackTrace();
	    }
	}
	tfMaxTemp.setText(String.valueOf(maxTemp));
	String _minTemp = configuration.getProperty("minTemp");
	if (_minTemp != null) {
	    try {
		minTemp = Integer.parseInt(_minTemp);
	    } catch (NumberFormatException nfe) {
		nfe.printStackTrace();
	    }
	}
	tfMinTemp.setText(String.valueOf(minTemp));
    }

    private boolean initConf() {
	boolean _returns = false;
	String _userHome = System.getProperty("user.home");
	File _configFile = new File(_userHome + File.separator + "tempControl.props");
	if (!(_configFile.exists())) {
	    try {
		_configFile.createNewFile();
		configFile = _configFile;
		_returns = true;
	    } catch (IOException e) {
		messageBox("No se pudo crear el archivo de configuración\n" +
			_configFile, "Error");
	    }
	} else {
	    configFile = _configFile;
	    _returns = true;
	}
	return _returns;
    }

    private void btnCfgWizard_actionPerformed(ActionEvent e) {
	configWizard();
    }

    private void btnForceFreq_actionPerformed(ActionEvent e) {
	String[] _freqs = new String[processors[0].availableFreqs.length+1];
	for (int i = 0; i < _freqs.length-1; i++) {
	    _freqs[i] = String.valueOf(processors[0].availableFreqs[i]);
	}
	_freqs[_freqs.length-1] = "No forzar";
	String _freq = ((String)JOptionPane.showInputDialog(this, "Seleccione la frecuencia para forzar", "Frecuencia", JOptionPane.QUESTION_MESSAGE, null, _freqs, ""));
	if (_freq != null) {
	    try {
		forcedFreq = Long.parseLong(_freq);
	    } catch (NumberFormatException nfe) {
		forcedFreq = 0;
	    }
	}
    }

    private void configWizard() {
	autoStart = question("Inicio automático", "¿Desea que el servicio se active al iniciar la aplicación?");
	configuration.put("autoStart", String.valueOf(autoStart));

	String _cmdInterpreter = tryToSet("Intérprete de comandos - Shell interpreter", "Configuración", cmdInterpreter, "", false, false);
	if (_cmdInterpreter != null) {
	    configuration.put("cmdInterpreter", cmdInterpreter);
	    cmdInterpreter = _cmdInterpreter;
	}

	String _cmdParameter = tryToSet("Parámetros del intérprete de comandos - Shell parameters", "Configuración", cmdParameter, "Error: la opción requiere un argumento/Error: argument required", true, false);
	if (_cmdParameter != null) {
	    configuration.put("cmdParameter", _cmdParameter);
	    cmdParameter = _cmdParameter;
	}

	String _currentFreqCmd = tryToSet("Comando para obtener la frecuencia actual de cada procesador - Get actual proc. freqs", "Configuración", currentFreqCmd, "Un número entero (por ejemplo 2200000)/A big integer (like 2200000)", true, false);
	if (_currentFreqCmd != null) {
	    configuration.put("currentFreqCmd", _currentFreqCmd);
	    currentFreqCmd = _currentFreqCmd;
	}

	String _currentTempCmd = tryToSet("Comando para obtener la temperatura actual", "Configuración", currentTempCmd, "Un número entero (por ejemplo 60)/A small integer (like 60)", true, false);
	if (_currentTempCmd != null) {
	    configuration.put("currentTempCmd", _currentTempCmd);
	    currentTempCmd = _currentTempCmd;
	}

	String _availableFreqsCmd = tryToSet("Comando para obtener las frecuencia disponibles de cada procesador", "Configuración", availableFreqsCmd, "Uno o varios números enteros (por ejemplo 2200000 1100000 550000)/Just one either several big integers (like 2200000 1100000 550000)", true, false);
	if (_availableFreqsCmd != null) {
	    configuration.put("availableFreqsCmd", _availableFreqsCmd);
	    availableFreqsCmd = _availableFreqsCmd;
	}

	String _shiftFreqFile = tryToSet("Archivo limitador de frecuencia", "Configuración", shiftFreqFile, "", false, true);
	if (_shiftFreqFile != null) {
	    configuration.put("shiftFreqFile", _shiftFreqFile);
	    shiftFreqFile = _shiftFreqFile;
	}

	String _shiftFreqCmd = tryToSet("Comando limitador de frecuencia", "Configuración", shiftFreqCmd, "echo: error de escritura: Argumento inválido/echo: write error: Invalid argument", true, false);
	if (_shiftFreqCmd != null) {
	    configuration.put("shiftFreqCmd", _shiftFreqCmd);
	    shiftFreqCmd = _shiftFreqCmd;
	}

	String _processorsQtyCmd = tryToSet("Comando para obtener la cantidad de procesadores", "Configuración", processorsQtyCmd, "Uno o varios números separados por un guión (por ejemplo 0-1)/Just one either several dash separated numbers (like 0-1)", true, false);
	if (_processorsQtyCmd != null) {
	    configuration.put("processorsQtyCmd", _processorsQtyCmd);
	    processorsQtyCmd = _processorsQtyCmd;
	}

	String _topProcessesCmd = tryToSet("Comando para obtener los cinco procesos más consumidores al momento de temperatura crítica", "Configuración", topProcessesCmd, "Un listado de procesos activos/An active proccesses list", true, false);
	if (_topProcessesCmd != null) {
	    configuration.put("topProcessesCmd", _topProcessesCmd);
	    topProcessesCmd = _topProcessesCmd;
	}

	String _grantAccessCmd = tryToSet("Comando para dar acceso al sistema para ejecutar un comando como SuperUsuario", "Configuración", grantAccessCmd, "Una ventana que solicite la contraseña de root/A window asking for root password", true, false);
	if (_grantAccessCmd != null) {
	    configuration.put("grantAccessCmd", _grantAccessCmd);
	    grantAccessCmd = _grantAccessCmd;
	}

	String _logFilePath = tryToSet("Archivo Log", "Configuración", logFilePath, "", false, true);
	if (_logFilePath != null) {
	    configuration.put("logFilePath", _logFilePath);
	    logFilePath = _logFilePath;
	}

	configuration.put("minTemp", String.valueOf(minTemp));
	configuration.put("maxTemp", String.valueOf(maxTemp));
	saveConf();
	fetchConf();
    }

    private void saveConf() {
	if (configFile.exists() && configFile.canWrite()) {
	    try {
		FileOutputStream _outputFile = new FileOutputStream(configFile);
		configuration.store(_outputFile, "El Suri Temperature Control Configuration File");
		_outputFile.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    messageBox("No se puede grabar el archivo de configuración", "Error");
	}
    }

    private class Processor {

	private long[] availableFreqs = new long[0];
	private int number = -1;
	private JProgressBar progressBar;
	private long minFreq = 0;
	private long maxFreq = 0;
	private long currentFreq = -1;

	public Processor(int _number, JProgressBar _progressBar) {
	    number = _number;
	    fetchAvailableFreqs();
	    progressBar = _progressBar;
	    if (availableFreqs.length > 1) {
		if (availableFreqs[0] > availableFreqs[availableFreqs.length - 1]) {
		    //minFreq = availableFreqs[availableFreqs.length - 1];
		    minFreq = availableFreqs[1];
		    maxFreq = availableFreqs[0];
		} else {
		    maxFreq = availableFreqs[availableFreqs.length - 1];
		    minFreq = availableFreqs[availableFreqs.length - 2];
		    //minFreq = availableFreqs[0];
		}
		progressBar.setMinimum((int)minFreq);
		progressBar.setMaximum((int)maxFreq);
	    } else {
		messageBox("Este procesador no admite variaciones de frecuencia", "Aviso");
	    }

	}

	private void fetchAvailableFreqs() {
	    String[] _availableFreqs = executeCommand(availableFreqsCmd.replaceAll("\\$number", (processorsQty == 1?"":String.valueOf(number)))).split(" ");
	    availableFreqs = new long[_availableFreqs.length];
	    for (int j = 0; j < _availableFreqs.length; j++) {
		availableFreqs[j] = Long.parseLong(_availableFreqs[j]);
	    }
	}

	protected void updateFreq(int _temp) {
	    if (_temp > 0) {
		if (_temp > maxTemp) {
		    shiftDown();
		} else if (_temp <= minTemp) {
		    shiftUp();
		}
	    } else {
		messageBox("Error al intentar obtener la temperatura actual\nRevise la configuración de comandos", "Aviso");
	    }
	    progressBar.setValue((int)Long.parseLong(executeCommand(currentFreqCmd.replaceAll("\\$number", (processorsQty == 1?"":String.valueOf(number))))));
	    progressBar.setMinimum(0);
	    progressBar.setString(String.valueOf(progressBar.getValue()));
	    progressBar.setStringPainted(true);
	}

	protected void shiftDown() {
	    setFrequency(minFreq);
	}

	protected void shiftUp() {
	    setFrequency(maxFreq);
	}

	protected void setFrequency(long _freq) {
	    if (currentFreq != _freq) {
		executeCommand(shiftFreqCmd.replaceAll("\\$freq", String.valueOf(_freq)).replaceAll("\\$number", (processorsQty == 1?"":String.valueOf(number))) + "\n");
		logEvent("Fijando la frecuencia máxima del procesador " + number + " a " + _freq);
		progressBar.setToolTipText("Procesador limitado a la frecuencia máxima de " + _freq);
		currentFreq = _freq;
	    }
	}

    }

}
