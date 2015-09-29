package com.wot.shared;

import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;


public class ItemsDataClan implements Serializable, Comparable<ItemsDataClan>{

	/**
	 * 
	 */
	
	
	 public static final ProvidesKey<ItemsDataClan> KEY_PROVIDER = new ProvidesKey<ItemsDataClan>() {
	        @Override
	        public Object getKey(ItemsDataClan item) {
	          return item == null ? null : item.getId();
	        }
	      };
	      
	private static final long serialVersionUID = -5761913518505341471L;

	/*
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
	Integer members_count;
    String name;
    String color;
    Integer created_at;
    String tag;
    /* emblems */ 
	
    Integer clan_id;
	
   
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Integer created_at) {
		this.created_at = created_at;
	}
	public Integer getMember_count() {
		return members_count;
	}
	public void setMember_count(Integer member_count) {
		this.members_count = member_count;
	}
	
	public Integer getId() {
		return clan_id;
	}
	public void setId(Integer id) {
		this.clan_id = id;
	}
	public String getClan_color() {
		return color;
	}
	public void setClan_color(String clan_color) {
		this.color = clan_color;
	}
	
	@Override
	public int compareTo(ItemsDataClan o) {
		// TODO Auto-generated method stub
		return (o == null || o.getName() == null) ? -1 : o.getName().compareTo(getName());
	}

	@Override
    public boolean equals(Object o) {
      if (o instanceof ItemsDataClan) {
        return getId() == ((ItemsDataClan) o).getId();
      }
      return false;
    }
	
	
}