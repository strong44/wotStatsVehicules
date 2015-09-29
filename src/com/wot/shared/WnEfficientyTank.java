package com.wot.shared;

import java.io.Serializable;
import java.util.List;

public class WnEfficientyTank implements Serializable{

	
	/**url : http://www.wnefficiency.net/exp/expected_tank_values_latest.json
	 * {"header":{"version":14},
	 * "data":
	 * [{"IDNum":"3089","expFrag":"2.11","expDamage":"278.00","expSpot":"2.35","expDef":"1.84","expWinRate":"59.54"},
	 * {"IDNum":"3329","expFrag":"2.10","expDamage":"270.00","expSpot":"1.55","expDef":"1.81","expWinRate":"60.46"},
	 */
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -6928793578933365756L;
	/**
	 * 
	 */
	
	
	List< DataWnEfficientyTank> data;
	public List<DataWnEfficientyTank> getData() {
		return data;
	}
	public void setData(List<DataWnEfficientyTank> data) {
		this.data = data;
	}

	
	
	


	
}

