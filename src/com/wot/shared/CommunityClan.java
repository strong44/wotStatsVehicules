package com.wot.shared;

import java.io.Serializable;
import java.util.Date;


public class CommunityClan implements Serializable{

	/**
	 * query.setFilter("dateField < dateParam");
	query.declareParameters("java.util.Date dateParam");
	List<...> results = (List<...>) query.execute(new java.util.Date());
	 */
	private static final long serialVersionUID = 1244678860855383625L;
	private String status;
	private String status_code;
	
	private String idClan;
	private Date dateCommunityClan;
	
	public String getIdClan() {
		return idClan;
	}

	public void setIdClan(String idClan) {
		this.idClan = idClan;
	}

	public Date getDateCommunityClan() {
		return dateCommunityClan;
	}

	public void setDateCommunityClan(Date dateCommunityClan) {
		this.dateCommunityClan = dateCommunityClan;
	}

	///ajout test
	//Map<String, DataCommunityClanMembers> data;
	
	private DataCommunityClan data;
	
    public DataCommunityClan getData() {
		return data;
	}

	public void setData(DataCommunityClan data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	
	
	
	
	
	
	
	
	
}

