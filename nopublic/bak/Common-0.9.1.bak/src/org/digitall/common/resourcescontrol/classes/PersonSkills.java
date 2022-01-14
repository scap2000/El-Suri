package org.digitall.common.resourcescontrol.classes;

import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.org.Persons;

public class PersonSkills {

    private Persons person;
    private Skills skill;
    
    public PersonSkills() {

    }

    public void setPerson(Persons person) {
	this.person = person;
    }

    public Persons getPerson() {
	return person;
    }

    public void setSkill(Skills skill) {
	this.skill = skill;
    }

    public Skills getSkill() {
	return skill;
    }

    public int assignSkill(){
	String params = person.getIdPerson() + "," + skill.getIdSkill();
	return LibSQL.getInt("org.addPersonSkill", params);
    }
    
    public int unAssignSkill(){
	String params = person.getIdPerson() + "," + skill.getIdSkill();
	return LibSQL.deleteQuery("org.delPersonSkill", params);
    }
}
