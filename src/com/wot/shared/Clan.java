package com.wot.shared;

import java.io.Serializable;
import java.util.List;


public class Clan implements Serializable{

	/** return in 9.10 
	 * with url : 
	 * https://api.worldoftanks.eu/wgn/clans/list/?application_id=d0a293dc77667c9328783d489c8cef73&search=NOVA%20SNAIL
	 * {
    "status": "ok",
    "meta": {
        "count": 1,
        "total": 1
    },
    "data": [
        {
            "members_count": 62,
            "name": "NOVA SNAIL",
            "color": "#525E4F",
            "created_at": 1328978449,
            "tag": "NVS",
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
            "clan_id": 500006074
        }
    ]
}
	 */
	private static final long serialVersionUID = 9216784168895883666L;
	private String status;
	//private String status_code;
	//private XmlWiki wiki;
	private XmlWiki wiki;
	
	/////////////////
	public XmlWiki getWiki() {
		return wiki;
	}

	public void setWiki(XmlWiki wiki) {
		this.wiki = wiki;
	}

//	private DataClan data;
//	
//    public DataClan getData() {
//		return data;
//	}
//
//	public void setData(DataClan data) {
//		this.data = data;
//	}

	/**
	 * 
	 */
	
	List<ItemsDataClan> data; //données du clan 

	public List<ItemsDataClan> getItems() {
		return data;
	}

	public void setItems(List<ItemsDataClan> items) {
		this.data = items;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
	
	
	
}

