package com.wot.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityClanMembers implements Serializable {

	private static final long serialVersionUID = 6538645333499345875L;
	
	
	
	
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	public Key getKey() {
		return key;
	}

	/*
	 * 
	 * 
            "leader_id": 504713110,
            "color": "#525E4F",
            "updated_at": 1441105226,
            "private": null,
            "tag": "NVS",
            "members_count": 62,
            "description_html": "<p><i>* Age minimum : 18 ans et +\r\n<br/></i>* Vocal actif ! Mumble exigé\r\n<br/><i>*1000 de win8 minimum\r\n<br/></i>* PRESENCE REGULIERES sur le canal,aux bastions et sur le forum ( 1 à 3 fois par semaine )\r\n<br/><i>* pas de language SMS , attitude mature exigée ! \r\n<br/></i>* Période d&#39;essai de 1 mois \r\n</p><p>\r\n<br/>Toutes les candidatures se font via un formulaire de candidature sur <a href=\"http://nova.snail.soforums.com/f47-Recrutement.htm\" target=\"_blank\">notre forum ici</a>\r\n<br/>Si vous désirez plus d’informations sur les candidatures, prenez contact avec un officier merci\r\n<br/>.\r\n<br/>Au plaisir de vous revoir ( ou de vous baver dessus ^^ ) sur le champ de bataille ! toute l&#39;équipe NVS .\r\n</p><p>\r\n<br/>stadetoulousain &amp; firefreez\n</p>",
            "creator_id": 502116599,
            "leader_name": "stadetoulousain",
            "emblems": {
                "x32": {
                    "portal": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_32x32.png"
                },
                "x24": {
                    "portal": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_24x24.png"
                },
                "x256": {
                    "wowp": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_256x256.png"
                },
                "x64": {
                    "wot": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_64x64_tank.png",
                    "portal": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_64x64.png"
                },
                "x195": {
                    "portal": "http://eu.wargaming.net/clans/media/clans/emblems/cl_074/500006074/emblem_195x195.png"
                }
            },
            "clan_id": 500006074,
            "renamed_at": 0,
            "old_tag": "",
            "description": "_* Age minimum : 18 ans et +\r\n_* Vocal actif ! Mumble exigé\r\n_*1000 de win8 minimum\r\n_* PRESENCE REGULIERES sur le canal,aux bastions et sur le forum ( 1 à 3 fois par semaine )\r\n_* pas de language SMS , attitude mature exigée ! \r\n_* Période d'essai de 1 mois \r\n\r\n\r\nToutes les candidatures se font via un formulaire de candidature sur \"notre forum ici\":http://nova.snail.soforums.com/f47-Recrutement.htm\r\nSi vous désirez plus d’informations sur les candidatures, prenez contact avec un officier merci\r\n.\r\nAu plaisir de vous revoir ( ou de vous baver dessus ^^ ) sur le champ de bataille ! toute l'équipe NVS .\r\n\r\nstadetoulousain & firefreez",
            "members": [
                {
                    "role": "private",
                    "role_i18n": "Private",
                    "joined_at": 1413985584,
                    "account_id": 500278612,
                    "account_name": "GladXX"
                },
                {
                    "role": "reservist",
                    "role_i18n": "Reservist",
                    "joined_at": 1417819449,
                    "account_id": 500515334,
                    "account_name": "Tilutin"
                },
                
                ...
	 * 
	 */
	
	
	
	
	
	
//	public String getAccount_name() {
//		return account_name;
//	}
//
//	public void setAccount_name(String account_name) {
//		this.account_name = account_name;
//	}

	public void setKey(Key key) {
		this.key = key;
	}

	Integer leader_id; /* 504713110*/ 
	String color; /* "#525E4F"*/
	Integer updated_at; /* 1441105226*/
	//Boolean private;
	String tag; /* NVS*/
	Integer  members_count; /*62*/
	//String description_html; /* <p><i>* Age minimum : 18 ans et +\ ...*/ 
	Integer creator_id; /*502116599 */
	String leader_name; /* stadetoulousain */
	
	//Map<String, > emblems;
	
	
	@Persistent
	private String clan_id;
	public Integer getUpdated_at() {
		return updated_at;
	}

	public Integer getCreated_at() {
		return created_at;
	}

	public void setUpdated_at(Integer updated_at) {
		this.updated_at = updated_at;
	}

	public void setCreated_at(Integer created_at) {
		this.created_at = created_at;
	}

	Integer renamed_at;
	String old_tag;
	//String description ; /* _* Age minimum : 18 ans et +\ ...*/
	
	String old_name;
    Boolean is_clan_disbanded;
    String motto ; /* "Un escargot qui avale un obus a confiance en sa coquille.", */
    String name; /* : "NOVA SNAIL", */
    String creator_name; /* ": "hentz44", */
    Integer created_at; /* ": 1328978449, */ 
    Boolean accepts_join_requests; /* ": true*/
    
	
	public Integer getLeader_id() {
		return leader_id;
	}

	public String getTag() {
		return tag;
	}

	public Integer getMembers_count() {
		return members_count;
	}

//	public String getDescription_html() {
//		return description_html;
//	}

	public String getLeader_name() {
		return leader_name;
	}

	public String getClan_id() {
		return clan_id;
	}

//	public String getDescription() {
//		return description;
//	}

	public String getMotto() {
		return motto;
	}

	public String getName() {
		return name;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setLeader_id(Integer leader_id) {
		this.leader_id = leader_id;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setMembers_count(Integer members_count) {
		this.members_count = members_count;
	}

//	public void setDescription_html(String description_html) {
//		this.description_html = description_html;
//	}

	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}

	public void setClan_id(String clan_id) {
		this.clan_id = clan_id;
	}

//	public void setDescription(String description) {
//		this.description = description;
//	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	///ajout test
	
	@Persistent
	List<DaoDataCommunityMembers> members;
	
	@Persistent
	Map<String, String> membersAdded = new HashMap<String, String>();
	
	@Persistent
	Map<String, String> membersDeleted = new HashMap<String, String>();
	
	
	public Map<String, String> getMembersAdded() {
		return membersAdded;
	}

	public Map<String, String> getMembersDeleted() {
		return membersDeleted;
	}

	public void setMembersAdded(Map<String, String> membersAdded) {
		this.membersAdded = membersAdded;
	}

	public void setMembersDeleted(Map<String, String> membersDeleted) {
		this.membersDeleted = membersDeleted;
	}

	public List< DaoDataCommunityMembers> getMembers() {
		return members;
	}

	public void setMembers(List< DaoDataCommunityMembers> members) {
		this.members = members;
	}


	
}