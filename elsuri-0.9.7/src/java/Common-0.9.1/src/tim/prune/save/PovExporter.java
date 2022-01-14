package tim.prune.save;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import tim.prune.App;
import tim.prune.DataSubscriber;
import tim.prune.I18nManager;
import tim.prune.UpdateMessageBroker;
import tim.prune.config.Config;
import tim.prune.data.NumberUtils;
import tim.prune.data.Track;
import tim.prune.function.Export3dFunction;
import tim.prune.gui.DialogCloser;
import tim.prune.gui.map.MapSource;
import tim.prune.gui.map.MapSourceLibrary;
import tim.prune.load.GenericFileFilter;
import tim.prune.threedee.ThreeDModel;

/**
 * Class to export a 3d scene of the track to a specified Pov file
 * Note: Subscriber interface only used for callback from image config dialog
 */
public class PovExporter extends Export3dFunction implements DataSubscriber
{
	private Track _track = null;
	private JDialog _dialog = null;
	private JFileChooser _fileChooser = null;
	private String _cameraX = null, _cameraY = null, _cameraZ = null;
	private JTextField _cameraXField = null, _cameraYField = null, _cameraZField = null;
	private JTextField _fontName = null, _altitudeFactorField = null;
	private JRadioButton _ballsAndSticksButton = null;
	private JLabel _baseImageLabel = null;
	private JButton _baseImageButton = null;
	private BaseImageConfigDialog _baseImageConfig = null;

	// defaults
	private static final double DEFAULT_CAMERA_DISTANCE = 30.0;
	private static final double MODEL_SCALE_FACTOR = 20.0;
	private static final String DEFAULT_FONT_FILE = "crystal.ttf";


	/**
	 * Constructor
	 * @param inApp App object
	 */
	public PovExporter(App inApp)
	{
		super(inApp);
		_track = inApp.getTrackInfo().getTrack();
		// Set default camera coordinates
		_cameraX = "17"; _cameraY = "13"; _cameraZ = "-20";
	}

	/** Get the name key */
	public String getNameKey() {
		return "function.exportpov";
	}

	/**
	 * Set the coordinates for the camera (can be any scale)
	 * @param inX X coordinate of camera
	 * @param inY Y coordinate of camera
	 * @param inZ Z coordinate of camera
	 */
	public void setCameraCoordinates(double inX, double inY, double inZ)
	{
		// calculate distance from origin
		double cameraDist = Math.sqrt(inX*inX + inY*inY + inZ*inZ);
		if (cameraDist > 0.0)
		{
			_cameraX = NumberUtils.formatNumberUk(inX / cameraDist * DEFAULT_CAMERA_DISTANCE, 5);
			_cameraY = NumberUtils.formatNumberUk(inY / cameraDist * DEFAULT_CAMERA_DISTANCE, 5);
			// Careful! Need to convert from java3d (right-handed) to povray (left-handed) coordinate system!
			_cameraZ = NumberUtils.formatNumberUk(-inZ / cameraDist * DEFAULT_CAMERA_DISTANCE, 5);
		}
	}


	/**
	 * Show the dialog to select options and export file
	 */
	public void begin()
	{
		// Make dialog window to select angles, colours etc
		if (_dialog == null)
		{
			_dialog = new JDialog(_parentFrame, I18nManager.getText(getNameKey()), true);
			_dialog.setLocationRelativeTo(_parentFrame);
			_dialog.getContentPane().add(makeDialogComponents());
		}
		// Make base image dialog
		if (_baseImageConfig == null)
		{
			_baseImageConfig = new BaseImageConfigDialog(this, _dialog, _track);
		}

		// Set angles
		_cameraXField.setText(_cameraX);
		_cameraYField.setText(_cameraY);
		_cameraZField.setText(_cameraZ);
		_altitudeFactorField.setText("" + _altFactor);
		updateBaseImageDetails();
		// Show dialog
		_dialog.pack();
		_dialog.setVisible(true);
	}


	/**
	 * Make the dialog components to select the export options
	 * @return Component holding gui elements
	 */
	private Component makeDialogComponents()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(4, 4));
		JLabel introLabel = new JLabel(I18nManager.getText("dialog.exportpov.text"));
		introLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 6, 4));
		panel.add(introLabel, BorderLayout.NORTH);
		// OK, Cancel buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton okButton = new JButton(I18nManager.getText("button.ok"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doExport();
				MapGrouter.clearMapImage();
				_dialog.dispose();
			}
		});
		buttonPanel.add(okButton);
		JButton cancelButton = new JButton(I18nManager.getText("button.cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				MapGrouter.clearMapImage();
				_dialog.dispose();
			}
		});
		buttonPanel.add(cancelButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		// central panel
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(0, 2, 10, 4));

		JLabel fontLabel = new JLabel(I18nManager.getText("dialog.exportpov.font"));
		fontLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		centralPanel.add(fontLabel);
		String defaultFont = Config.getConfigString(Config.KEY_POVRAY_FONT);
		if (defaultFont == null || defaultFont.equals("")) {
			defaultFont = DEFAULT_FONT_FILE;
		}
		_fontName = new JTextField(defaultFont, 12);
		_fontName.setAlignmentX(Component.LEFT_ALIGNMENT);
		_fontName.addKeyListener(new DialogCloser(_dialog));
		centralPanel.add(_fontName);
		//coordinates of camera
		JLabel cameraXLabel = new JLabel(I18nManager.getText("dialog.exportpov.camerax"));
		cameraXLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		centralPanel.add(cameraXLabel);
		_cameraXField = new JTextField("" + _cameraX);
		centralPanel.add(_cameraXField);
		JLabel cameraYLabel = new JLabel(I18nManager.getText("dialog.exportpov.cameray"));
		cameraYLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		centralPanel.add(cameraYLabel);
		_cameraYField = new JTextField("" + _cameraY);
		centralPanel.add(_cameraYField);
		JLabel cameraZLabel = new JLabel(I18nManager.getText("dialog.exportpov.cameraz"));
		cameraZLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		centralPanel.add(cameraZLabel);
		_cameraZField = new JTextField("" + _cameraZ);
		centralPanel.add(_cameraZField);
		// Altitude exaggeration
		JLabel altitudeCapLabel = new JLabel(I18nManager.getText("dialog.3d.altitudefactor"));
		altitudeCapLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		centralPanel.add(altitudeCapLabel);
		_altitudeFactorField = new JTextField("1.0");
		centralPanel.add(_altitudeFactorField);

		// Radio buttons for style - balls on sticks or tubes
		JPanel stylePanel = new JPanel();
		stylePanel.setLayout(new GridLayout(0, 2, 10, 4));
		JLabel styleLabel = new JLabel(I18nManager.getText("dialog.exportpov.modelstyle"));
		styleLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		stylePanel.add(styleLabel);
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		_ballsAndSticksButton = new JRadioButton(I18nManager.getText("dialog.exportpov.ballsandsticks"));
		_ballsAndSticksButton.setSelected(false);
		radioPanel.add(_ballsAndSticksButton);
		JRadioButton tubesButton = new JRadioButton(I18nManager.getText("dialog.exportpov.tubesandwalls"));
		tubesButton.setSelected(true);
		radioPanel.add(tubesButton);
		ButtonGroup group = new ButtonGroup();
		group.add(_ballsAndSticksButton); group.add(tubesButton);
		stylePanel.add(radioPanel);

		// Panel for the base image
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout(10, 4));
		imagePanel.add(new JLabel(I18nManager.getText("dialog.exportpov.baseimage") + ": "), BorderLayout.WEST);
		_baseImageLabel = new JLabel("Typical sourcename");
		imagePanel.add(_baseImageLabel, BorderLayout.CENTER);
		_baseImageButton = new JButton(I18nManager.getText("button.edit"));
		_baseImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changeBaseImage();
			}
		});
		imagePanel.add(_baseImageButton, BorderLayout.EAST);
		// Put these image controls inside a holder panel with an outline
		JPanel imageHolderPanel = new JPanel();
		imageHolderPanel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(4, 4, 4, 4))
		);
		imageHolderPanel.setLayout(new BorderLayout());
		imageHolderPanel.add(imagePanel, BorderLayout.NORTH);

		// add these panels to the holder panel
		JPanel holderPanel = new JPanel();
		holderPanel.setLayout(new BorderLayout(5, 5));
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		boxPanel.add(centralPanel);
		boxPanel.add(Box.createVerticalStrut(4));
		boxPanel.add(stylePanel);
		boxPanel.add(Box.createVerticalStrut(4));
		boxPanel.add(imageHolderPanel);
		holderPanel.add(boxPanel, BorderLayout.CENTER);

		panel.add(holderPanel, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Change the base image by calling the BaseImageConfigDialog
	 */
	private void changeBaseImage()
	{
		// Check if there is a cache to use
		if (BaseImageConfigDialog.isImagePossible())
		{
			// Show new dialog to choose image details
			_baseImageConfig.begin();
		}
		else {
			_app.showErrorMessage(getNameKey(), "dialog.exportimage.noimagepossible");
		}
	}

	/**
	 * Update the description label according to the selected base image details
	 */
	private void updateBaseImageDetails()
	{
		String desc = null;
		if (_baseImageConfig.useImage())
		{
			MapSource source = MapSourceLibrary.getSource(_baseImageConfig.getSourceIndex());
			if (source != null) {
				desc = source.getName() + " ("
					+ _baseImageConfig.getZoomLevel() + ")";
			}
		}
		if (desc == null) {
			desc = I18nManager.getText("dialog.about.no");
		}
		_baseImageLabel.setText(desc);
	}

	/**
	 * Select the file and export data to it
	 */
	private void doExport()
	{
		// Copy camera coordinates
		_cameraX = checkCoordinate(_cameraXField.getText());
		_cameraY = checkCoordinate(_cameraYField.getText());
		_cameraZ = checkCoordinate(_cameraZField.getText());

		// OK pressed, so choose output file
		if (_fileChooser == null)
		{
			_fileChooser = new JFileChooser();
			_fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			_fileChooser.setFileFilter(new GenericFileFilter("filetype.pov", new String[] {"pov"}));
			_fileChooser.setAcceptAllFileFilterUsed(false);
			// start from directory in config which should be set
			final String configDir = Config.getConfigString(Config.KEY_TRACK_DIR);
			if (configDir != null) {_fileChooser.setCurrentDirectory(new File(configDir));}
		}

		// Allow choose again if an existing file is selected
		boolean chooseAgain = false;
		do
		{
			chooseAgain = false;
			if (_fileChooser.showSaveDialog(_parentFrame) == JFileChooser.APPROVE_OPTION)
			{
				// OK pressed and file chosen
				File povFile = _fileChooser.getSelectedFile();
				if (!povFile.getName().toLowerCase().endsWith(".pov"))
				{
					povFile = new File(povFile.getAbsolutePath() + ".pov");
				}
				final int nameLen = povFile.getName().length() - 4;
				final File imageFile = new File(povFile.getParentFile(), povFile.getName().substring(0, nameLen) + "_base.png");
				final boolean imageExists = _baseImageConfig.useImage() && imageFile.exists();
				// Check if files exist and if necessary prompt for overwrite
				Object[] buttonTexts = {I18nManager.getText("button.overwrite"), I18nManager.getText("button.cancel")};
				if ((!povFile.exists() && !imageExists) || JOptionPane.showOptionDialog(_parentFrame,
						I18nManager.getText("dialog.save.overwrite.text"),
						I18nManager.getText("dialog.save.overwrite.title"), JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, buttonTexts, buttonTexts[1])
					== JOptionPane.YES_OPTION)
				{
					// Export the file
					if (exportFile(povFile, imageFile))
					{
						// file saved - store directory in config for later
						Config.setConfigString(Config.KEY_TRACK_DIR, povFile.getParentFile().getAbsolutePath());
					}
					else
					{
						// export failed so need to choose again
						chooseAgain = true;
					}
				}
				else
				{
					// overwrite cancelled so need to choose again
					chooseAgain = true;
				}
			}
		} while (chooseAgain);
	}


	/**
	 * Export the track data to the specified file
	 * @param inPovFile File object to save pov file to
	 * @param inImageFile file object to save image to
	 * @return true if successful
	 */
	private boolean exportFile(File inPovFile, File inImageFile)
	{
		FileWriter writer = null;
		// find out the line separator for this system
		final String lineSeparator = System.getProperty("line.separator");
		try
		{
			// create and scale model
			ThreeDModel model = new ThreeDModel(_track);
			model.setModelSize(MODEL_SCALE_FACTOR);
			try
			{
				// try to use given altitude cap
				double altFactor = Double.parseDouble(_altitudeFactorField.getText());
				model.setAltitudeFactor(altFactor);
			}
			catch (NumberFormatException nfe) { // parse failed, reset
				_altitudeFactorField.setText("1.0");
			}
			model.scale();

			boolean useImage = _baseImageConfig.useImage();
			if (useImage)
			{
				// Get base image from grouter
				MapSource mapSource = MapSourceLibrary.getSource(_baseImageConfig.getSourceIndex());
				GroutedImage baseImage = MapGrouter.getMapImage(_track, mapSource, _baseImageConfig.getZoomLevel());
				try
				{
					useImage = ImageIO.write(baseImage.getImage(), "png", inImageFile);
				}
				catch (IOException ioe) {
					System.err.println("Can't write image: " + ioe.getClass().getName());
					useImage = false;
				}
				if (!useImage) {
					_app.showErrorMessage(getNameKey(), "dialog.exportpov.cannotmakebaseimage");
				}
			}

			// Create file and write basics
			writer = new FileWriter(inPovFile);
			writeStartOfFile(writer, lineSeparator, useImage ? inImageFile : null);

			// write out points
			if (_ballsAndSticksButton.isSelected()) {
				writeDataPointsBallsAndSticks(writer, model, lineSeparator);
			}
			else {
				writeDataPointsTubesAndWalls(writer, model, lineSeparator);
			}

			// everything worked
			UpdateMessageBroker.informSubscribers(I18nManager.getText("confirm.save.ok1")
				 + " " + _track.getNumPoints() + " " + I18nManager.getText("confirm.save.ok2")
				 + " " + inPovFile.getAbsolutePath());
			return true;
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(_parentFrame, I18nManager.getText("error.save.failed") + " : " + ioe.getMessage(),
				I18nManager.getText("error.save.dialogtitle"), JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			// close file ignoring exceptions
			try
			{
				writer.close();
			}
			catch (Exception e) {}
		}
		return false;
	}


	/**
	 * Write the start of the Pov file, including base plane and lights
	 * @param inWriter Writer to use for writing file
	 * @param inLineSeparator line separator to use
	 * @param inImageFile image file to reference (or null if none)
	 * @throws IOException on file writing error
	 */
	private void writeStartOfFile(FileWriter inWriter, String inLineSeparator, File inImageFile)
	throws IOException
	{
		inWriter.write("// Pov file produced by GpsPrune - see http://activityworkshop.net/");
		inWriter.write(inLineSeparator);
		inWriter.write(inLineSeparator);
		// Select font based on user input
		String fontPath = _fontName.getText();
		if (fontPath == null || fontPath.equals(""))
		{
			fontPath = DEFAULT_FONT_FILE;
		}
		else {
			Config.setConfigString(Config.KEY_POVRAY_FONT, fontPath);
		}

		// Make the definition of the base plane depending on whether there's an image or not
		final boolean useImage = (inImageFile != null);
		final String boxDefinition = (inImageFile == null ?
			"   <-10.0, -0.15, -10.0>," + inLineSeparator
				+ "   <10.0, 0.15, 10.0>" + inLineSeparator
				+ "   pigment { color rgb <0.5 0.75 0.8> }"
			:
			"   <0, 0, 0>, <1, 1, 0.001>" + inLineSeparator
				+ "   pigment {image_map { png \"" + inImageFile.getName() + "\" map_type 0 interpolate 2 once } }" + inLineSeparator
				+ "   scale 20.0 rotate <90, 0, 0>" + inLineSeparator
				+ "   translate <-10.0, 0, -10.0>");
		// TODO: Maybe could use the same geometry for the imageless case, would simplify code a bit

		// Set up output
		String[] outputLines = {
		  "global_settings { ambient_light rgb <4, 4, 4> }", "",
		  "// Background and camera",
		  "background { color rgb <0, 0, 0> }",
		  // camera position
		  "camera {",
		  "  location <" + _cameraX + ", " + _cameraY + ", " + _cameraZ + ">",
		  "  look_at  <0, 0, 0>",
		  "}", "",
		// global declares
		  "// Global declares",
		  "#declare point_rod =",
		  "  cylinder {",
		  "   <0, 0, 0>,",
		  "   <0, 1, 0>,",
		  "   0.15",
		  "   open",
		  "   texture {",
		  "    pigment { color rgb <0.5 0.5 0.5> }",
		  useImage ? "   } no_shadow" : "   }",
		  "  }", "",
		  // MAYBE: Export rods to POV?  How to store in data?
		  "#declare waypoint_sphere =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.4",
		  "    texture {",
		  "       pigment {color rgb <0.1 0.1 1.0>}",
		  "       finish { phong 1 }",
		  useImage ? "    } no_shadow" : "    }",
		  "  }",
		  "#declare track_sphere0 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.1 0.6 0.1>}", // dark green
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere1 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.4 0.9 0.2>}", // green
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere2 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.7 0.8 0.2>}", // yellow
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere3 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.5 0.8 0.6>}", // greeny
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere4 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.2 0.9 0.9>}", // cyan
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere5 =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.3", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <1.0 1.0 1.0>}", // white
		  "      finish { phong 1 }",
		  "   }",
		  " }",
		  "#declare track_sphere_t =",
		  "  sphere {",
		  "   <0, 0, 0>, 0.25", // size should depend on model size
		  "   texture {",
		  "      pigment {color rgb <0.6 1.0 0.2>}",
		  "      finish { phong 1 }",
		  "   } no_shadow",
		  " }",
		  "#declare wall_colour = rgbt <0.5, 0.5, 0.5, 0.3>;", "",
		  "// Base plane",
		  "box {",
		  boxDefinition,
		  "}", "",
		// write cardinals
		  "// Cardinal letters N,S,E,W",
		  "text {",
		  "  ttf \"" + fontPath + "\" \"" + I18nManager.getText("cardinal.n") + "\" 0.3, 0",
		  "  pigment { color rgb <1 1 1> }",
		  "  translate <0, 0.2, 10.0>",
		  "}",
		  "text {",
		  "  ttf \"" + fontPath + "\" \"" + I18nManager.getText("cardinal.s") + "\" 0.3, 0",
		  "  pigment { color rgb <1 1 1> }",
		  "  translate <0, 0.2, -10.0>",
		  "}",
		  "text {",
		  "  ttf \"" + fontPath + "\" \"" + I18nManager.getText("cardinal.e") + "\" 0.3, 0",
		  "  pigment { color rgb <1 1 1> }",
		  "  translate <9.7, 0.2, 0>",
		  "}",
		  "text {",
		  "  ttf \"" + fontPath + "\" \"" + I18nManager.getText("cardinal.w") + "\" 0.3, 0",
		  "  pigment { color rgb <1 1 1> }",
		  "  translate <-10.3, 0.2, 0>",
		  "}", "",
		  // MAYBE: Light positions should relate to model size
		  "// lights",
		  "light_source { <-1, 9, -4> color rgb <0.5 0.5 0.5>}",
		  "light_source { <1, 6, -14> color rgb <0.6 0.6 0.6>}",
		  "light_source { <11, 12, 8> color rgb <0.3 0.3 0.3>}",
		  "",
		};
		// write strings to file
		int numLines = outputLines.length;
		for (int i=0; i<numLines; i++)
		{
			inWriter.write(outputLines[i]);
			inWriter.write(inLineSeparator);
		}
	}


	/**
	 * Write out all the data points to the file in the balls-and-sticks style
	 * @param inWriter Writer to use for writing file
	 * @param inModel model object for getting data points
	 * @param inLineSeparator line separator to use
	 * @throws IOException on file writing error
	 */
	private static void writeDataPointsBallsAndSticks(FileWriter inWriter, ThreeDModel inModel, String inLineSeparator)
	throws IOException
	{
		inWriter.write("// Data points:");
		inWriter.write(inLineSeparator);
		int numPoints = inModel.getNumPoints();
		for (int i=0; i<numPoints; i++)
		{
			// ball (different according to type)
			if (inModel.getPointType(i) == ThreeDModel.POINT_TYPE_WAYPOINT)
			{
				// waypoint ball
				inWriter.write("object { waypoint_sphere translate <" + inModel.getScaledHorizValue(i)
					+ "," + inModel.getScaledAltValue(i) + "," + inModel.getScaledVertValue(i) + "> }");
			}
			else
			{
				// normal track point ball
				inWriter.write("object { track_sphere" + checkHeightCode(inModel.getPointHeightCode(i))
					+ " translate <" + inModel.getScaledHorizValue(i) + "," + inModel.getScaledAltValue(i)
					+ "," + inModel.getScaledVertValue(i) + "> }");
			}
			inWriter.write(inLineSeparator);
			// vertical rod (if altitude positive)
			if (inModel.getScaledAltValue(i) > 0.0)
			{
				inWriter.write("object { point_rod translate <" + inModel.getScaledHorizValue(i) + ",0,"
					+ inModel.getScaledVertValue(i) + "> scale <1," + inModel.getScaledAltValue(i) + ",1> }");
				inWriter.write(inLineSeparator);
			}
		}
		inWriter.write(inLineSeparator);
	}


	/**
	 * Write out all the data points to the file in the tubes-and-walls style
	 * @param inWriter Writer to use for writing file
	 * @param inModel model object for getting data points
	 * @param inLineSeparator line separator to use
	 * @throws IOException on file writing error
	 */
	private static void writeDataPointsTubesAndWalls(FileWriter inWriter, ThreeDModel inModel, String inLineSeparator)
	throws IOException
	{
		inWriter.write("// Data points:");
		inWriter.write(inLineSeparator);
		int numPoints = inModel.getNumPoints();
		int numTrackPoints = 0;
		// Loop over all points and write out waypoints as balls
		for (int i=0; i<numPoints; i++)
		{
			if (inModel.getPointType(i) == ThreeDModel.POINT_TYPE_WAYPOINT)
			{
				// waypoint ball
				inWriter.write("object { waypoint_sphere translate <" + inModel.getScaledHorizValue(i)
					+ "," + inModel.getScaledAltValue(i) + "," + inModel.getScaledVertValue(i) + "> }");
				// vertical rod (if altitude positive)
				if (inModel.getScaledAltValue(i) > 0.0)
				{
					inWriter.write(inLineSeparator);
					inWriter.write("object { point_rod translate <" + inModel.getScaledHorizValue(i) + ",0,"
						+ inModel.getScaledVertValue(i) + "> scale <1," + inModel.getScaledAltValue(i) + ",1> }");
				}
				inWriter.write(inLineSeparator);
			}
			else {numTrackPoints++;}
		}
		inWriter.write(inLineSeparator);

		// Loop over all the track segments
		ArrayList<ModelSegment> segmentList = getSegmentList(inModel);
		Iterator<ModelSegment> segmentIterator = segmentList.iterator();
		while (segmentIterator.hasNext())
		{
			ModelSegment segment = segmentIterator.next();
			int segLength = segment.getNumTrackPoints();

			// if the track segment is long enough, do a cubic spline sphere sweep
			if (segLength <= 1)
			{
				// single point in segment - just draw sphere
				int index = segment.getStartIndex();
				inWriter.write("object { track_sphere_t"
					+ " translate <" + inModel.getScaledHorizValue(index) + "," + inModel.getScaledAltValue(index)
					+ "," + inModel.getScaledVertValue(index) + "> }");
				// maybe draw some kind of polygon too or rod?
			}
			else
			{
				writeSphereSweep(inWriter, inModel, segment, inLineSeparator);
			}

			// Write wall underneath segment
			if (segLength > 1)
			{
				writePolygonWall(inWriter, inModel, segment, inLineSeparator);
			}
		}
	}


	/**
	 * Write out a single sphere sweep using either cubic spline or linear spline
	 * @param inWriter Writer to use for writing file
	 * @param inModel model object for getting data points
	 * @param inSegment model segment to draw
	 * @param inLineSeparator line separator to use
	 * @throws IOException on file writing error
	 */
	private static void writeSphereSweep(FileWriter inWriter, ThreeDModel inModel, ModelSegment inSegment, String inLineSeparator)
	throws IOException
	{
		// 3d sphere sweep
		inWriter.write("// Sphere sweep:");
		inWriter.write(inLineSeparator);
		String splineType = inSegment.getNumTrackPoints() < 5?"linear_spline":"cubic_spline";
		inWriter.write("sphere_sweep { "); inWriter.write(splineType);
		inWriter.write(" " + inSegment.getNumTrackPoints() + ",");
		inWriter.write(inLineSeparator);
		// Loop over all points in this segment and write out sphere sweep
		for (int i=inSegment.getStartIndex(); i<=inSegment.getEndIndex(); i++)
		{
			if (inModel.getPointType(i) != ThreeDModel.POINT_TYPE_WAYPOINT)
			{
				inWriter.write("  <" + inModel.getScaledHorizValue(i) + "," + inModel.getScaledAltValue(i)
					+ "," + inModel.getScaledVertValue(i) + ">, 0.25");
				inWriter.write(inLineSeparator);
			}
		}
		inWriter.write("  tolerance 0.1");
		inWriter.write(inLineSeparator);
		inWriter.write("  texture { pigment {color rgb <0.6 1.0 0.2>}  finish {phong 1} }");
		inWriter.write(inLineSeparator);
		inWriter.write("  no_shadow");
		inWriter.write(inLineSeparator);
		inWriter.write("}");
		inWriter.write(inLineSeparator);
	}


	/**
	 * Write out a single polygon-based wall for the tubes-and-walls style
	 * @param inWriter Writer to use for writing file
	 * @param inModel model object for getting data points
	 * @param inSegment model segment to draw
	 * @param inLineSeparator line separator to use
	 * @throws IOException on file writing error
	 */
	private static void writePolygonWall(FileWriter inWriter, ThreeDModel inModel, ModelSegment inSegment, String inLineSeparator)
	throws IOException
	{
		// wall
		inWriter.write(inLineSeparator);
		inWriter.write("// wall between sweep and floor:");
		inWriter.write(inLineSeparator);
		// Loop over all points in this segment again and write out polygons
		int prevIndex = -1;
		for (int i=inSegment.getStartIndex(); i<=inSegment.getEndIndex(); i++)
		{
			if (inModel.getPointType(i) != ThreeDModel.POINT_TYPE_WAYPOINT)
			{
				if (prevIndex >= 0)
				{
					double xDiff = inModel.getScaledHorizValue(i) - inModel.getScaledHorizValue(prevIndex);
					double yDiff = inModel.getScaledVertValue(i) - inModel.getScaledVertValue(prevIndex);
					double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
					if (dist > 0)
					{
						inWriter.write("polygon {");
						inWriter.write("  5, <" + inModel.getScaledHorizValue(prevIndex) + ", 0.0, " + inModel.getScaledVertValue(prevIndex) + ">,");
						inWriter.write(" <" + inModel.getScaledHorizValue(prevIndex) + ", " + inModel.getScaledAltValue(prevIndex) + ", "
							+ inModel.getScaledVertValue(prevIndex) + ">,");
						inWriter.write(" <" + inModel.getScaledHorizValue(i) + ", " + inModel.getScaledAltValue(i) + ", "
							+ inModel.getScaledVertValue(i) + ">,");
						inWriter.write(" <" + inModel.getScaledHorizValue(i) + ", 0.0, " + inModel.getScaledVertValue(i) + ">,");
						inWriter.write(" <" + inModel.getScaledHorizValue(prevIndex) + ", 0.0, " + inModel.getScaledVertValue(prevIndex) + ">");
						inWriter.write("  pigment { color wall_colour } no_shadow");
						inWriter.write("}");
						inWriter.write(inLineSeparator);
					}
				}
				prevIndex = i;
			}
		}
	}


	/**
	 * @param inCode height code to check
	 * @return validated height code within range 0 to max
	 */
	private static byte checkHeightCode(byte inCode)
	{
		final byte maxHeightCode = 5;
		if (inCode < 0) return 0;
		if (inCode > maxHeightCode) return maxHeightCode;
		return inCode;
	}


	/**
	 * Check the given coordinate
	 * @param inString String entered by user
	 * @return validated String value
	 */
	private static String checkCoordinate(String inString)
	{
		double value = 0.0;
		try
		{
			value = Double.parseDouble(inString);
		}
		catch (Exception e) {} // ignore parse failures
		return "" + value;
	}

	/**
	 * Go through the points making a list of the segment starts and the number of track points in each segment
	 * @param inModel model containing data
	 * @return list of ModelSegment objects
	 */
	private static ArrayList<ModelSegment> getSegmentList(ThreeDModel inModel)
	{
		ArrayList<ModelSegment> segmentList = new ArrayList<ModelSegment>();
		if (inModel != null && inModel.getNumPoints() > 0)
		{
			ModelSegment currSegment = null;
			int numTrackPoints = 0;
			for (int i=0; i<inModel.getNumPoints(); i++)
			{
				if (inModel.getPointType(i) != ThreeDModel.POINT_TYPE_WAYPOINT)
				{
					if (inModel.getPointType(i) == ThreeDModel.POINT_TYPE_SEGMENT_START || currSegment == null)
					{
						// start of segment
						if (currSegment != null)
						{
							currSegment.setEndIndex(i-1);
							currSegment.setNumTrackPoints(numTrackPoints);
							segmentList.add(currSegment);
							numTrackPoints = 0;
						}
						currSegment = new ModelSegment(i);
					}
					numTrackPoints++;
				}
			}
			// Add last segment to list
			if (currSegment != null && numTrackPoints > 0)
			{
				currSegment.setEndIndex(inModel.getNumPoints()-1);
				currSegment.setNumTrackPoints(numTrackPoints);
				segmentList.add(currSegment);
			}
		}
		return segmentList;
	}

	/**
	 * Callback from base image config dialog
	 */
	public void dataUpdated(byte inUpdateType)
	{
		updateBaseImageDetails();
	}

	/** Not required */
	public void actionCompleted(String inMessage) {
	}
}
