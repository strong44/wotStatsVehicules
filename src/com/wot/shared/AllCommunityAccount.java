package com.wot.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllCommunityAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -725335839446286960L;
	/**
	 * 
	 */
	
	private List<CommunityAccount>  listCommunityAccount = new ArrayList<CommunityAccount>();

	public List<CommunityAccount> getListCommunityAccount() {
		return listCommunityAccount;
	}

	public void setListCommunityAccount(List<CommunityAccount> listCommunityAccount) {
		this.listCommunityAccount = listCommunityAccount;
	}
	


	
	
	
}

