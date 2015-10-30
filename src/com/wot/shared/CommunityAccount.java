package com.wot.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.view.client.ProvidesKey;

public class CommunityAccount implements Serializable, Comparable<CommunityAccount>{

	
    public static final ProvidesKey<CommunityAccount> KEY_PROVIDER = new ProvidesKey<CommunityAccount>() {
        @Override
        public Object getKey(CommunityAccount item) {
          return item == null ? null : item.getIdUser();
        }
      };
	/**
	 * 
	 */
	private static final long serialVersionUID = -1393222615715486990L;
	
	private String idUser = "000000";

	String name ;

	public List< DataPlayerTankRatingsStatistics>  listTankStatistics = new ArrayList<DataPlayerTankRatingsStatistics>();
	

	private String dateCommunityAccount;
	private List<DataPlayerTankRatingsStatistics> listTankPlayedFromLastDay;
	
	//////////////////////////////// getter ....
	

	public String getDateCommunityAccount() {
		return dateCommunityAccount;
	}

	public void setDateCommunityAccount(String dateCurrent) {
		this.dateCommunityAccount = dateCurrent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String id) {
		this.idUser = id;
	}
	

	public List<DataPlayerTankRatingsStatistics> getListTankStatistics() {
		return listTankStatistics;
	}

	public void setListTankStatistics(List<DataPlayerTankRatingsStatistics> listTankStatistics) {
		this.listTankStatistics = listTankStatistics;
	}

	@Override
	public int compareTo(CommunityAccount o) {
		return (o == null || o.getName() == null) ? -1 : o.getName().compareTo(getName());
	}

	@Override
    public boolean equals(Object o) {
      if (o instanceof CommunityAccount) {
        return getIdUser().equalsIgnoreCase(((CommunityAccount) o).getIdUser());
      }
      return false;
    }

	
	public List<DataPlayerTankRatingsStatistics> getListTankPlayedFromLastDay() {
		return listTankPlayedFromLastDay;
	}

	public void setListTankPlayedFromLastDay(List<DataPlayerTankRatingsStatistics> listTankPlayedFromLastDay) {
		this.listTankPlayedFromLastDay = listTankPlayedFromLastDay;
	}

	
}

