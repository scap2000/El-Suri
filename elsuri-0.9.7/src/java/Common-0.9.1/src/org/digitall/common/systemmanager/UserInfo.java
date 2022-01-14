package org.digitall.common.systemmanager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class UserInfo extends BasicPrimitivePanel {
    

    private String code = "";
    private String name = "";
    private String organization = "";
    private String role = "";
    private String email = "";
    private String website = "";
    private boolean receiveNews = false;
    private String preferredOS = "";
    private BasicPanel contentPane = new BasicPanel();
    private String country = "";
    private String province = "";
    private String city = "";

    private TFInput tfName = new TFInput(DataTypes.STRING, "Nombre y Apellido", false);
    private TFInput tfOrganization = new TFInput(DataTypes.STRING, "Organización/Empresa", false);
    private TFInput tfRole = new TFInput(DataTypes.STRING, "Cargo Jerárquico", false);
    private TFInput tfEmail = new TFInput(DataTypes.STRING, "Correo electrónico", false);
    private TFInput tfWebsite = new TFInput(DataTypes.STRING, "Sitio Web", false);
    private BasicCheckBox chkReceiveNews = new BasicCheckBox();
    private CBInput cbPreferredOS = new CBInput(0, "Sistema Operativo preferido", false);
    private CBInput cbCountry = new CBInput(CachedCombo.COUNTRY_TABS, "País", false);
    private TFInput tfProvince = new TFInput(DataTypes.STRING, "Provincia", false);
    private TFInput tfCity = new TFInput(DataTypes.STRING, "Ciudad", false);

    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnCancel = new CloseButton();

    public UserInfo() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(545, 256));
	contentPane.setLayout(null);
	chkReceiveNews.setText("Recibir novedades del sistema (vía email)");
	tfName.setBounds(new Rectangle(5, 10, 260, 35));
	tfOrganization.setBounds(new Rectangle(5, 50, 260, 35));
	tfRole.setBounds(new Rectangle(280, 50, 260, 35));
	tfEmail.setBounds(new Rectangle(280, 10, 260, 35));
	tfWebsite.setBounds(new Rectangle(5, 90, 260, 35));
	tfWebsite.setText("http://");
	chkReceiveNews.setBounds(new Rectangle(280, 180, 260, 25));
	cbPreferredOS.setBounds(new Rectangle(280, 90, 260, 35));
	cbPreferredOS.removeAllItems();
	cbPreferredOS.addItem("Linux (SuSE, RedHat, Ubuntu, etc.)");
	cbPreferredOS.addItem("Mac OS");
	cbPreferredOS.addItem("MS Windows (XP, Vista, Seven)");
	cbPreferredOS.addItem("Otro");
	cbCountry.setBounds(new Rectangle(5, 130, 260, 35));
	cbCountry.setSelectedValue("Argentina");
	tfProvince.setBounds(new Rectangle(280, 130, 260, 35));
	tfCity.setBounds(new Rectangle(5, 170, 260, 35));
	btnSave.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSave_actionPerformed(e);
		}
	    });
	btnCancel.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnCancel_actionPerformed(e);
		}
	    });
	contentPane.add(tfName, null);
	contentPane.add(tfOrganization, null);
	contentPane.add(tfRole, null);
	contentPane.add(tfEmail, null);
	contentPane.add(tfWebsite, null);
	contentPane.add(chkReceiveNews, null);
	contentPane.add(cbPreferredOS, null);
	contentPane.add(cbCountry, null);
	contentPane.add(tfProvince, null);
	contentPane.add(tfCity, null);
	this.addButton(btnSave);
	this.addButton(btnCancel);
	this.add(contentPane, BorderLayout.CENTER);
	if (!Environment.cfg.getProperty("usercode").equals("usercode")) {
	    code = Environment.cfg.getProperty("usercode");
	    retrieveData();
	}
    }

    public void setCode(String _code) {
	code = _code;
    }

    public String getCode() {
	return code;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setOrganization(String _organization) {
	organization = _organization;
    }

    public String getOrganization() {
	return organization;
    }

    public void setRole(String _role) {
	role = _role;
    }

    public String getRole() {
	return role;
    }

    public void setEmail(String _email) {
	email = _email;
    }

    public String getEmail() {
	return email;
    }

    public void setWebsite(String _website) {
	website = _website;
    }

    public String getWebsite() {
	return website;
    }

    public void setReceiveNews(boolean _notify) {
	receiveNews = _notify;
    }

    public boolean receiveNews() {
	return receiveNews;
    }

    public void setPreferredOS(String _preferredOS) {
	preferredOS = _preferredOS;
    }

    public String getPreferredOS() {
	return preferredOS;
    }

    public void setCountry(String _country) {
	country = _country;
    }

    public void setProvince(String _province) {
	province = _province;
    }

    public String getProvince() {
	return province;
    }

    public void setCity(String _city) {
	city = _city;
    }

    public String getCity() {
	return city;
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    getParentInternalFrame().setIcon(true);
	}
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	if (cancelSaveData()) {
	    getParentInternalFrame().setIcon(true);
	}
    }

    private boolean cancelSaveData() {
	return true;
    }

    public void retrieveData() {
	ResultSet _results = LibSQL.exFunction("org.getuserinfo", "'" + code + "'");
	try {
	    if (_results.next()) {
		code = _results.getString("code");
	        name = _results.getString("name");
		tfName.setText(name);
	        organization = _results.getString("organization");
		tfOrganization.setText(organization);
	        role = _results.getString("role");
		tfRole.setText(role);
	        email = _results.getString("email");
		tfEmail.setText(email);
	        website = _results.getString("website");
		tfWebsite.setText(website);
		receiveNews = _results.getBoolean("receivenews");
	        chkReceiveNews.setSelected(receiveNews);
		preferredOS = _results.getString("preferredos");
	        cbPreferredOS.setSelectedValue(preferredOS);
		country = _results.getString("country");
		cbCountry.setSelectedValue(country);
		province = _results.getString("province");
		tfProvince.setText(province);
		city = _results.getString("city");
		tfCity.setText(city);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean saveData() {
	boolean _returns = false;
	String _params = "'" + code + "'," + tfName.getStringForSQL() + "," + tfOrganization.getStringForSQL() + "," + tfRole.getStringForSQL()
	    + "," + tfEmail.getStringForSQL() + "," + tfWebsite.getStringForSQL() + ",'" + chkReceiveNews.isSelected() 
	    + "','" + cbPreferredOS.getSelectedItem() + "','" + cbCountry.getSelectedItem() + "',"
	    + tfProvince.getStringForSQL() + "," + tfCity.getStringForSQL();
	
	code = LibSQL.getString("org.adduserinfo", _params);
	Environment.cfg.setProperty("usercode", code);
	Advisor.messageBox("Su código de usuario es " + code, "Código de usuario");
	return _returns;
    }
}
