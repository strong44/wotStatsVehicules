package com.wot.server;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoCommunityClan2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853517466442689107L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getIdClan() {
		return idClan;
	}

	public Date getDateCommunityClan() {
		return dateCommunityClan;
	}

	public void setIdClan(String idClan) {
		this.idClan = idClan;
	}

	public void setDateCommunityClan(Date dateCommunityClan) {
		this.dateCommunityClan = dateCommunityClan;
	}

	@Persistent
	private String idClan;
	
	@Persistent
	private Date dateCommunityClan;
	
	
	/*
	 * {
    "status": "ok",
    "meta": {
        "count": 1
    },
    "data": {
        "500006074": {
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

	 */
	private String status;

	@Persistent
	private String userAdded;
	
	@Persistent
	private String userDeleted;
	
	@Persistent
	Map<String, DaoDataCommunityClanMembers> data;  /* id clan , {"leader_id":, etc... } */ 

	
	public String getUserAdded() {
		return userAdded;
	}

	public String getUserDeleted() {
		return userDeleted;
	}

	public void setUserAdded(String userAdded) {
		this.userAdded = userAdded;
	}

	public void setUserDeleted(String userDeleted) {
		this.userDeleted = userDeleted;
	}


	
	public Map<String, DaoDataCommunityClanMembers> getData() {
		return data;
	}

	public void setData(Map<String, DaoDataCommunityClanMembers> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}

