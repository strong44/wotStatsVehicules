package com.wot.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoItemsDataClan implements Serializable{

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	private static final long serialVersionUID = 2005352612781524708L;
	/*
	 * "abbreviation": "NVS", 
        "created_at": 1328978449.00, 
        "name": "NOVA SNAIL", 
        "member_count": 57, 
        "owner": "hentz44", 
        "motto": "Un escargot qui avale un obus a confiance en sa coquille.", 
        "clan_emblem_url": "http://cw.worldoftanks.eu/media/clans/emblems/clans_5/500006074/emblem_64x64.png", 
        "id": 500006074, 
        "clan_color": "#4a426c"
	 */
	@Persistent
	String abbreviation;
	
	@Persistent
    String name;
	
	@Persistent
    Integer created_at;
	
	@Persistent
    Integer member_count;
	
	@Persistent
    String owner;
	
	@Persistent
    String motto;
	
	@Persistent
    String clan_emblem_url;
	
	@Persistent
    Integer id;
	
	@Persistent
    String clan_color;
    
    
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Integer integer) {
		this.created_at = integer;
	}
	public Integer getMember_count() {
		return member_count;
	}
	public void setMember_count(Integer integer) {
		this.member_count = integer;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getClan_emblem_url() {
		return clan_emblem_url;
	}
	public void setClan_emblem_url(String clan_emblem_url) {
		this.clan_emblem_url = clan_emblem_url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer integer) {
		this.id = integer;
	}
	public String getClan_color() {
		return clan_color;
	}
	public void setClan_color(String clan_color) {
		this.clan_color = clan_color;
	}
	
}