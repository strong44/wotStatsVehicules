package com.wot.shared;

import java.io.Serializable;

public class DataCommunityMembers implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4956114545804795075L;
	////////// not use =======
	String account_id; 
    String created_at; 
    String updated_at; 
    String role; 
    String account_name;
	public String getAccount_id() {
		return account_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public String getRole() {
		return role;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
    
}
