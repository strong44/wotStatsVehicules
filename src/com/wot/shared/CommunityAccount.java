package com.wot.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.view.client.ProvidesKey;

public class CommunityAccount implements Serializable, Comparable<CommunityAccount>{

	
//	public CommunityAccount(String idUser, String nameAccount) {
//		
//		this.idUser = idUser;
//		this.nameAccount = nameAccount;
//	}

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
	private String status;
	private Integer count;
	

	private String idUser = "000000";

	String nameAccount ;

	//Map<String, DataCommunityAccountRatings> data;
	DataPlayerInfos data;

	public List<String> listDates = new ArrayList<String>()  ;
	public List<Integer> listbattles = new ArrayList<Integer>()  ;
	public List<Integer> listBattlesWins = new ArrayList<Integer>();  
	
	public List<DataCommunityAccount> listBattlesTanks = new ArrayList<DataCommunityAccount>()  ;
	//public List<Integer> listBattlesTanksWins = new ArrayList<Integer>();  
	
	public String getName() {
		return nameAccount;
	}

	public void setName(String name) {
		this.nameAccount = name;
	}

	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String id) {
		this.idUser = id;
	}
	//private DataCommunityAccount data;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	private List<DataCommunityAccountVehicules> listVehPlayedDay0;
	
    public List<DataCommunityAccountVehicules> getListVehPlayedDay0() {
		return listVehPlayedDay0;
	}

	public void setListVehPlayedDay0(List<DataCommunityAccountVehicules> listVehPlayed) {
		this.listVehPlayedDay0 = listVehPlayed;
	}

	private List<DataCommunityAccountVehicules> listVehPlayedDay1;
	
	public List< DataPlayerInfos>  listDataPlayerInfos = new ArrayList<DataPlayerInfos>();
	
    public List<DataCommunityAccountVehicules> getListVehPlayedDay1() {
		return listVehPlayedDay1;
	}

	public void setListVehPlayedDay1(List<DataCommunityAccountVehicules> listVehPlayed) {
		this.listVehPlayedDay1 = listVehPlayed;
	}
	
	public DataPlayerInfos getData() {
		return data;
	}

	public void setData(DataPlayerInfos data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setListVehPlayedSincePreviousDay0(List<DataCommunityAccountVehicules> listVehPlayed) {
		this.listVehPlayedDay0 = listVehPlayed;
	}
	
	public void setListVehPlayedSincePreviousDay1(List<DataCommunityAccountVehicules> listVehPlayed) {
		this.listVehPlayedDay1 = listVehPlayed;
	}
	
}

