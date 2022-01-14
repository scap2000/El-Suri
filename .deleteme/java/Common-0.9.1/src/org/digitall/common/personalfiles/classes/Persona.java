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
 * Persona.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class Persona {
    
    private int idPerson = -1;
    private int idIdentification = -1;
    private String identificationNumber = "";
    private String firstName = "";
    private String lastName = "";
    private String marriedLastName = "";
    private String bloodGroup = "";
    private String bloodFactor = "";    
    private int idBornCountry = -1;
    private int idPrefix = -1;
    private int idSuffix = -1;   
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * idformatview --> idFormatView
     * idcontacttype --> idContactType
     * */
    private int idformatview = -1;
    private int idcontacttype = -1;
    private int idProfession = -1;
    private String title = "";
    private String birthDate = "";
    private String digitalsign = "";
    private String photo = "null";
    private String logo = "null";
    private int idCivilState = -1;	
    private int idCommunicationType = -1;
    private String description = "";    
    private String sex = "";
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Borrar líneas innecesarias
     * */
    //private String live = "";
     /* 2009-09-17 (santiago) Mensaje para el codificador
      * live --> alive
      * */

    private boolean live = false;
    private int idTypeQualification = -1;
    private String studyLevel = "";
    private boolean isPersonal = false;
    
    //private byte[] photoBytes = null;
    private BufferedImage photoImage = null;
    private byte[] logoBytes = null;
    private BufferedImage logoImage = null;
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Todo en plural o todo en singular...
     * deberia ser direccionesVector, trabajosVector, estudiosVector tal como familiaresVector
     * ya que al ser de la clase Persona es obvio que las direcciones y lo demas es
     * de la persona...
     * Revisar además las clases TrabajoPersona, DireccionPersona
     * */
    private Vector<DireccionPersona> direccionPersonaVector = new Vector<DireccionPersona>();    
    private Vector<TrabajoPersona> trabajoPersonaVector = new Vector<TrabajoPersona>();  
    private Vector<QualificationPerson> estudioPersonaVector = new Vector<QualificationPerson>();       
    private Vector<Relatives> familiaresVector = new Vector<Relatives>(); 
    
    public Persona() {
	this(-1);
    }

    public Persona(int _idPerson) {
	idPerson = _idPerson;
    }

    public int getIdIdentification() {
	return idIdentification;
    }

    public void setIdIdentification(int idIdentificationType) {
	this.idIdentification = idIdentificationType;
    }

    public String getIdentificationNumber() {
	return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
	this.identificationNumber = identificationNumber;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getFirstName() {
	return firstName;
    }
    
    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getMarriedLastName() {
	return marriedLastName;
    }

    public void setMarriedLastName(String marriedLastName) {
	this.marriedLastName = marriedLastName;
    }

     public int getIdBornCountry() {
	return idBornCountry;
    }

    public void setIdBornCountry(int idBornCountry) {
	this.idBornCountry = idBornCountry;
    }

    public int getIdPrefix() {
	return idPrefix;
    }

    public void setIdPrefix(int idPrefix) {
	this.idPrefix = idPrefix;
    }

    public int getIdSuffix() {
	return idSuffix;
    }

    public void setIdSuffix(int idSuffix) {
	this.idSuffix = idSuffix;
    }

   public int getIdProfession() {
	return idProfession;
    }

    public void setIdProfession(int idProfession) {
	this.idProfession = idProfession;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public String getPhoto() {
	return photo;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getLogo() {
	return logo;
    }

    public void setLogo(String logo) {
	this.logo = logo;
    }

    public int getIdCivilState() {
	return idCivilState;
    }

    public void setIdCivilState(int idCivilState) {
	this.idCivilState = idCivilState;
    }

    public int getIdCommunicationType() {
	return idCommunicationType;
    }

    public void setIdCommunicationType(int idCommunicationType) {
	this.idCommunicationType = idCommunicationType;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public BufferedImage getPhotoImage() {
	return photoImage;
    }

    public void setPhotoImage(BufferedImage photoImage) {
	this.photoImage = photoImage;
    }

    public byte[] getLogoBytes() {
	return logoBytes;
    }

    public void setLogoBytes(byte[] logoBytes) {
	this.logoBytes = logoBytes;
    }

    public BufferedImage getLogoImage() {
	return logoImage;
    }

    public void setLogoImage(BufferedImage logoImage) {
	this.logoImage = logoImage;
    }

    public void setIdPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setAddressesVector(Vector<DireccionPersona> addressesVector) {
	this.direccionPersonaVector = addressesVector;
    }

    public Vector getAddressesVector() {
	return direccionPersonaVector;
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Borrar líneas innecesarias
     * */

/////??????
   // public void setRelativesVector(Vector<Familiar> relativesVector) {
	//this.relativesVector = relativesVector;
    //}

   // public Vector<Familiar> getRelativesVector() {
//	return relativesVector;
  //  }

   /* 2009-09-17 (santiago) Mensaje para el codificador
    * getLive --> isAlive
    * */

    public boolean getLive() {
	return live;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * setLive(boolean) --> setAlive(boolean)
     * */

    public void setLive(boolean _live) {
	live = _live;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */

    public int getIdcontacttype() {
	return idcontacttype;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */

    public void setIdcontacttype(int idcontacttype) {
	this.idcontacttype = idcontacttype;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */

    public int getIdformatview() {
	return idformatview;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */
    public void setIdformatview(int idformatview) {
	this.idformatview = idformatview;
    }

    public String getBloodGroup() {
	return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
	this.bloodGroup = bloodGroup;
    }

    public String getBloodFactor() {
	return bloodFactor;
    }

    public void setBloodFactor(String bloodFactor) {
	this.bloodFactor = bloodFactor;
    }
    
    public String getStudylevel() {
	return studyLevel;
    }

    public void setStudylevel(String _studyLevel) {
	this.studyLevel = _studyLevel;
    }

    public int getIdTypeQualification() {
	return idTypeQualification;
    }

    public void setIdTypeQualification(int idTypeQualification) {
	this.idTypeQualification = idTypeQualification;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */
    public String getDigitalsign() {
	return digitalsign;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */
    public void setDigitalsign(String digitalsign) {
	this.digitalsign = digitalsign;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * getIsPersonal --> isPersonal
     * */
    public boolean getIsPersonal() {
	return isPersonal;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * setIsPersonal(boolean) --> setPersonal(boolean)
     * */
    public void setIsPersonal(boolean _isPersonal) {
	isPersonal = _isPersonal;
    }
    
    //METODOS PARA EL VECTOR DIRECCION PERSONA-->INICIO
    
    public void loadDirecciones() {
	direccionPersonaVector.removeAllElements();	
	ResultSet data = LibSQL.exFunction("personalfiles.getAllIdPersonAddresses", idPerson);
	try {
	    while (data.next()) {
		direccionPersonaVector.add(new DireccionPersona(data.getInt("idAddress")));
	   }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} 
	for (int i = 0; i < direccionPersonaVector.size() ; i++ )  {
	    direccionPersonaVector.elementAt(i).retrieveData();
	}
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * loadDireccion(DireccionPersona) --> addDireccion(DireccionPersona)
     * ya que el cuerpo no hace un "retrieve" como el metodo loadDirecciones()
     * sino que agrega una direccion al vector
     * */

    public void loadDireccion(DireccionPersona _direccionPersona) {
	direccionPersonaVector.add(_direccionPersona);
    } 

    public DireccionPersona getAddress(int _idAddress) {
	DireccionPersona _result = null;
	int i = 0;
	int vecSize = direccionPersonaVector.size();
	while ((direccionPersonaVector.elementAt(i).getIdAddress() != _idAddress) && (i < vecSize)){
	    i++;
	}
	if (i != vecSize) {
	    _result = direccionPersonaVector.elementAt(i);
	}
	return _result;
    }

    public DireccionPersona getDefaultAddress() {
	DireccionPersona _result = null;
	int i = 0;
	int vecSize = direccionPersonaVector.size();
	while ((direccionPersonaVector.elementAt(i).isDefault() != true) && (i < vecSize)){
	    i++;
	}
	if (i != vecSize) {
	    _result = direccionPersonaVector.elementAt(i);
	}
	return _result;
    }    
    //METODOS PARA EL VECTOR DIRECCION PERSONA--> FIN
    
    //METODOS PARA EL VECTOR TRABAJO PERSONA-->INICIO
     public void loadTrabajos() {
	 trabajoPersonaVector.removeAllElements();     
	 ResultSet data = LibSQL.exFunction("personalfiles.getAllIdJobsPersonal", idPerson);
	 try {
	     while (data.next()) {
		 trabajoPersonaVector.add(new TrabajoPersona(data.getInt("idJobPerson")));
	    }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	 } 
	 for (int i = 0; i < trabajoPersonaVector.size() ; i++ )  {
	     trabajoPersonaVector.elementAt(i).retrieveData();
	 }
     }   
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * loadTrabajo(TrabajoPersona) --> addTrabajo(TrabajoPersona)
     * */
     public void loadTrabajo(TrabajoPersona _trabajoPersonaVector) {
	  trabajoPersonaVector.add(_trabajoPersonaVector);
     } 
     
    public TrabajoPersona getTrabajo(int _idTrabajo) {
	TrabajoPersona _result = null;
	int i = 0;
	int vecSize = trabajoPersonaVector.size();
	while ((trabajoPersonaVector.elementAt(i).getIdJobPerson() != _idTrabajo) && (i < vecSize)){
	    i++;
	}
	if (i != vecSize) {
	    _result = trabajoPersonaVector.elementAt(i);
	}
	return _result;
    }
    //METODOS PARA EL VECTOR TRABAJO PERSONA-->FIN
    
    //METODOS PARA EL VECTOR ESTUDIOS PERSONA-->INICIO
     public void loadEstudios() {
	estudioPersonaVector.removeAllElements();     
	ResultSet data = LibSQL.exFunction("personalfiles.getAllIdQualificationPerson", idPerson);
	try {
	 while (data.next()) {
	     estudioPersonaVector.add(new QualificationPerson(data.getInt("idqualificationperson")));
	}
	} catch (Exception ex) {
	 ex.printStackTrace();
	} 
	for (int i = 0; i < estudioPersonaVector.size() ; i++ )  {
	 estudioPersonaVector.elementAt(i).retrieveData();
	}
     }   
     
     public void loadEstudio(QualificationPerson _estudio) {
	  estudioPersonaVector.add(_estudio);
     } 
     
     public QualificationPerson getEstudioPersona(int _idEstudio) {
	QualificationPerson _result = null;
	int i = 0;
	int vecSize = estudioPersonaVector.size();
	while ((estudioPersonaVector.elementAt(i).getIdQualificationPerson() != _idEstudio) && (i < vecSize)){
	    i++;
	}
	if (i != vecSize) {
	    _result = estudioPersonaVector.elementAt(i);
	}
	return _result;
     }
    //METODOS PARA EL VECTOR ESTUDIOS PERSONA-->FIN
    
    //METODOS PARA EL VECTOR FAMILIAR PERSONA-->INICIO
    public void loadFamiliares() {
     familiaresVector.removeAllElements();     
     ResultSet data = LibSQL.exFunction("personalfiles.getAllIdRelatives", idPerson);
     try {
      while (data.next()) {
	  familiaresVector.add(new Relatives(data.getInt("idrelatives")));
     }
     } catch (Exception ex) {
      ex.printStackTrace();
     } 
     for (int i = 0; i < familiaresVector.size() ; i++ )  {
      familiaresVector.elementAt(i).retrieveData();
     }
    }   
    
    public void loadFamiliar(Relatives _familiar) {
       familiaresVector.add(_familiar);
    } 
    
    public Relatives getFamiliar(int _idFamiliar) {
	Relatives _result = null;
     int i = 0;
     int vecSize = familiaresVector.size();
     while ((familiaresVector.elementAt(i).getIdRelative() != _idFamiliar) && (i < vecSize)){
	 i++;
     }
     if (i != vecSize) {
	 _result = familiaresVector.elementAt(i);
     }
     return _result;
    }
    //METODOS PARA EL VECTOR FAMILIAR PERSONA-->FIN
    
    
    public int saveData() {             
	String params = idIdentification+",'"+identificationNumber+"','"+firstName+"','"+lastName+"','"+marriedLastName+"','" + bloodGroup + "','" + bloodFactor + "',"+idBornCountry+","+idPrefix+","+idSuffix+"," + idformatview + ","+idcontacttype+","+idProfession+
	",'"+title+"','" + birthDate + "','" + digitalsign + "',"+photo+","+logo+","+idCivilState+","+idCommunicationType+",'"+description+"','"+sex+"',"+ live +"," + getIdTypeQualification() + ",'"+studyLevel+"',"+ isPersonal ;
	int result = -1;
	if (idPerson == -1){
	    result = LibSQL.getInt("personalfiles.addPerson",params);    	   
	    idPerson = result;	    
	} else {            
	    params = idPerson+","+ params;
	    result = LibSQL.getInt("personalfiles.setPerson",params);
	}               
	if (result != -1 && photoImage != null) {
	    //if (!IMGLib.saveImage(photoImage, "personalfiles.persons", "photo", "WHERE idperson = " + idPerson)) {
	    if (!LibIMG.saveImage(photoImage, "org.persons", "photo", "WHERE idperson = " + idPerson)) {
		result = -2;
	    }
	}
	return result;
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * No se entiende qué es "saveDataOcult()", tampoco se sabe
     * si está en uso o desuso, el único lugar donde se utiliza
     * es en org.digitall.apps.personalFiles.interfaces.PersonMgmt línea 470
     * */

    public int saveDataOcult() {             
	String params = idIdentification+",'"+identificationNumber+"','"+firstName+"','"+lastName+"','"+marriedLastName+"','" + bloodGroup + "','" + bloodFactor + "',"+idBornCountry+","+idPrefix+","+idSuffix+"," + idformatview + ","+idcontacttype+","+idProfession+
	",'"+title+"','" + birthDate + "','" + digitalsign + "',"+photo+","+logo+","+idCivilState+","+idCommunicationType+",'"+description+"','"+sex+"',"+ live +"," + getIdTypeQualification() + ",'"+studyLevel+"',"+ isPersonal ;
	int result = -1;
	if (idPerson == -1){
	    result = LibSQL.getInt("personalfiles.addPerson",params);              
	    idPerson = result;      
	} else {            
	    params = idPerson+","+ params;
	    result = LibSQL.getInt("personalfiles.setPerson",params);
	}               
	if (result != -1 && photoImage != null) {
	    //if (!IMGLib.saveImage(photoImage, "personalfiles.persons", "photo", "WHERE idperson = " + idPerson)) {
	    if (!LibIMG.saveImage(photoImage, "org.persons", "photo", "WHERE idperson > -1 ")) {
		result = -2;
	    }
	}
	return result;
    }
    
    public void retrieveData() {
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * La llamada debería ser directamente LibSQL.exFunction("personalfiles.getPerson",idPerson);
	 * no hay necesidad de instanciar un String params, ni de adjuntarle "" al parámetro
	 * ya que LibSQL.exFunction(String, Object) acepta objetos como segundo parámetro
	 * */
    
	String params = ""+idPerson;
	ResultSet data = LibSQL.exFunction("personalfiles.getperson", params);
	try {
	    if (data.next()) {
		idIdentification = data.getInt("ididentificationtype");
		identificationNumber = data.getString("identificationnumber");
		firstName = data.getString("name");
		lastName = data.getString("lastname");
		marriedLastName = data.getString("marriedlastname");
		setBloodGroup(data.getString("bloodgroup"));
		setBloodFactor(data.getString("bloodfactor"));
		idBornCountry = data.getInt("idborncountry");
		idPrefix = data.getInt("idprefix");
		idSuffix = data.getInt("idsuffix");
		idProfession = data.getInt("idprofession");
		title = data.getString("title");
		birthDate = data.getString("birthdate");                
		idCivilState = data.getInt("idcivilstate");
		idCommunicationType = data.getInt("idcommunicationtype");
		description = data.getString("description");
		sex = data.getString("sex");                            
		live = data.getBoolean("live");
		isPersonal = data.getBoolean("ispersonal");    
		idcontacttype = 0;
		idformatview = 0;
		idTypeQualification = -1;
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void retrievePicture() {	
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * Borrar líneas innecesarias
	 * */
	//photoImage = IMGLib.getImage("personalfiles.persons", "photo", "WHERE idPerson = " + idPerson);
        photoImage = LibIMG.getImage("org.persons", "photo", "WHERE idPerson = " + idPerson);
    }
}
