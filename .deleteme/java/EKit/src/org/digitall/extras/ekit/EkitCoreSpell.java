/*
GNU Lesser General Public License

EkitCore - Base Java Swing HTML Editor & Viewer Class (Spellcheck Version)
Copyright (C) 2000 Howard Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package org.digitall.extras.ekit;

import java.net.URL;

import javax.swing.text.Document;

/** EkitCoreSpell
  * Extended main application class with additional spellchecking feature
  *
  * @author Howard Kistler
  * @version 0.9h
  *
  * REQUIREMENTS
  * Java 2 (JDK 1.3 or 1.4)
  * Swing Library
  */
public class EkitCoreSpell extends EkitCore implements SpellCheckListener {

    /* Spell Checker Settings */
    private static String dictFile;
    private SpellChecker spellCheck = null;
    private JSpellDialog spellDialog;

    /** Master Constructor
	  * @param sDocument         [String]  A text or HTML document to load in the editor upon startup.
	  * @param sStyleSheet       [String]  A CSS stylesheet to load in the editor upon startup.
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param urlStyleSheet     [URL]     A URL reference to the CSS style sheet.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  * @param base64            [boolean] Specifies whether the raw document is Base64 encoded or not.
	  * @param debugMode         [boolean] Specifies whether to show the Debug menu or not.
	  * @param hasSpellChecker   [boolean] Specifies whether or not this uses the SpellChecker module
	  * @param multiBar          [boolean] Specifies whether to use multiple toolbars or one big toolbar.
	  */
    public EkitCoreSpell(String sDocument, String sStyleSheet, String sRawDocument, URL urlStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64, boolean debugMode, boolean useSpellChecker, boolean multiBar) {
	super(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, debugMode, true, multiBar);
	/* Create spell checker */
	try {
	    dictFile = Translatrix.getTranslationString("DictionaryFile");
	    SpellDictionary dictionary = new SpellDictionary(dictFile);
	    // uses my custom loader in SpellDictionary
	    spellCheck = new SpellChecker(dictionary);
	    spellCheck.addSpellCheckListener(this);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	spellDialog = new JSpellDialog(this.getFrame(), Translatrix.getTranslationString("ToolSpellcheckDialog"), true);
    }

    /** Common Constructor
	  * @param sDocument         [String]  A text or HTML document to load in the editor upon startup.
	  * @param sStyleSheet       [String]  A CSS stylesheet to load in the editor upon startup.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  */
    public EkitCoreSpell(String sDocument, String sStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64) {
	this(sDocument, sStyleSheet, null, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, false, true, false);
    }

    /** Default Language Constructor
	  * @param sDocument         [String]  A text or HTML document to load in the editor upon startup.
	  * @param sStyleSheet       [String]  A CSS stylesheet to load in the editor upon startup.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  */
    public EkitCoreSpell(String sDocument, String sStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, boolean base64) {
	this(sDocument, sStyleSheet, null, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, null, null, base64, false, true, false);
    }

    /** Raw/Base64 Document & Style Sheet URL Constructor (Ideal for EkitApplet)
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  */
    public EkitCoreSpell(String sRawDocument, URL urlStyleSheet, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64, boolean multiBar) {
	this(null, null, sRawDocument, urlStyleSheet, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, false, true, multiBar);
    }

    /** Document Constructor
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  */
    public EkitCoreSpell(String sRawDocument, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry, boolean base64) {
	this(null, null, sRawDocument, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, base64, false, true, false);
    }

    /** Default Language & Document Constructor
	  * @param sRawDocument      [String]  A document encoded as a String to load in the editor upon startup.
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  */
    public EkitCoreSpell(String sRawDocument, boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, boolean base64) {
	this(null, null, sRawDocument, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, null, null, base64, false, true, false);
    }

    /** Flags & Language Constructor
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  * @param sLanguage         [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry          [String]  The country portion of the Internationalization Locale to run Ekit in.
	  */
    public EkitCoreSpell(boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive, String sLanguage, String sCountry) {
	this(null, null, null, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, sLanguage, sCountry, false, false, true, false);
    }

    /** Flags Constructor
	  * @param includeToolBar    [boolean] Specifies whether the app should include the toolbar.
	  * @param showViewSource    [boolean] Specifies whether or not to show the View Source window on startup.
	  * @param showMenuIcons     [boolean] Specifies whether or not to show icon pictures in menus.
	  * @param editModeExclusive [boolean] Specifies whether or not to use exclusive edit mode (recommended on).
	  */
    public EkitCoreSpell(boolean includeToolBar, boolean showViewSource, boolean showMenuIcons, boolean editModeExclusive) {
	this(null, null, null, null, includeToolBar, showViewSource, showMenuIcons, editModeExclusive, null, null, false, false, true, false);
    }

    /** Language & Debug Constructor
	  * @param sLanguage [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry  [String]  The country portion of the Internationalization Locale to run Ekit in.
	  * @param debugMode [boolean] Specifies whether to show the Debug menu or not.
	  */
    public EkitCoreSpell(String sLanguage, String sCountry, boolean debugMode) {
	this(null, null, null, null, true, false, true, true, sLanguage, sCountry, false, debugMode, true, false);
    }

    /** Language Constructor
	  * @param sLanguage [String]  The language portion of the Internationalization Locale to run Ekit in.
	  * @param sCountry  [String]  The country portion of the Internationalization Locale to run Ekit in.
	  */
    public EkitCoreSpell(String sLanguage, String sCountry) {
	this(null, null, null, null, true, false, true, true, sLanguage, sCountry, false, false, true, false);
    }

    /** Debug Constructor
	  * @param debugMode [boolean] Specifies whether to show the Debug menu or not.
	  */
    public EkitCoreSpell(boolean debugMode) {
	this(null, null, null, null, true, false, true, true, null, null, false, debugMode, true, false);
    }

    /** Empty Constructor
	  */
    public EkitCoreSpell() {
	this(null, null, null, null, true, false, true, true, null, null, false, false, true, false);
    }
    /* SpellCheckListener methods */

    public void spellingError(SpellCheckEvent event) {
	spellDialog.show(event);
    }
    /* Spell checking method (overrides empty method in basic core) */

    public void checkDocumentSpelling(Document doc) {
	spellCheck.checkSpelling(new DocumentWordTokenizer(doc));
    }

}
