package com.wot.shared.dossiertojson;

import java.util.Comparator;

import com.wot.shared.dossiertojson.DossierToJsonTanks.EnumAttrToSort;
import com.wot.shared.dossiertojson.DossierToJsonTanks.EnumOrder;

public class DossierToJsonTanksComparator implements Comparator<DossierToJsonTanks>  {
	private EnumAttrToSort sortAttrCode ;
	//{"tankid": 0, "countryid": 3, "compDescr": 49, "active": 1, "type": 2, "type_name": "MT", "tier": 8, "premium": 1, "title": "Type 59", 
	//"icon": "ch01_type59", "icon_orig": "Ch01_Type59"},
	private EnumOrder order;
	
	public DossierToJsonTanksComparator(EnumAttrToSort attrCode, EnumOrder desc) {
		super();
		this.sortAttrCode = attrCode;
		this.order = desc;
	}

	@Override
	public int compare(DossierToJsonTanks obj1, DossierToJsonTanks obj2) {
		// TODO Auto-generated method stub
		String valueAttrObj1 = "";
		String valueAttrObj2 = "";
		
		if (obj1 != null && obj2 != null ) {
			switch(sortAttrCode) {
				case tankid : 
					valueAttrObj1 = String.valueOf(obj1.getId());
					valueAttrObj2 = String.valueOf(obj2.getId());
					break ;
					
				case countryid : 
					valueAttrObj1 = String.valueOf(obj1.getCountry());
					valueAttrObj2 = String.valueOf(obj2.getCountry());
					break ;
					
				case compDescr : 
					valueAttrObj1 = String.valueOf(obj1.getCompDescr());
					valueAttrObj2 = String.valueOf(obj2.getCompDescr());
					break ;	
					
				case active : 
					valueAttrObj1 = String.valueOf(obj1.getActive());
					valueAttrObj2 = String.valueOf(obj2.getActive());
					break ;
					
				case type : 
					valueAttrObj1 = String.valueOf(obj1.getType());
					valueAttrObj2 = String.valueOf(obj2.getType());
					break ;
					
				case type_name :
					valueAttrObj1 = String.valueOf(obj1.getType_name());
					valueAttrObj2 = String.valueOf(obj2.getType_name());
					break ;
	
				case tier : 
					valueAttrObj1 = String.valueOf(obj1.getTier());
					valueAttrObj2 = String.valueOf(obj2.getTier());
					break ;				
	
				case premium : 
					valueAttrObj1 = String.valueOf(obj1.getPremium());
					valueAttrObj2 = String.valueOf(obj2.getPremium());
					break ;				
	
				case title : 
					valueAttrObj1 = String.valueOf(obj1.getTitle());
					valueAttrObj2 = String.valueOf(obj2.getTitle());
					break ;				
	
				case icon : 
					valueAttrObj1 = String.valueOf(obj1.getIcon());
					valueAttrObj2 = String.valueOf(obj2.getIcon());
					break ;				
	
				case icon_orig : 
					valueAttrObj1 = String.valueOf(obj1.getIcon_orig());
					valueAttrObj2 = String.valueOf(obj2.getIcon_orig());
					break ;				
	
					
			}
		
			if(valueAttrObj1 != null && valueAttrObj2 != null  ) {
				if(order == EnumOrder.asc)
					return (valueAttrObj1.compareTo(valueAttrObj2));
				else
					return (valueAttrObj2.compareTo(valueAttrObj1));
			}else
				return 0;
		}else
			return 0;
		
	}

}
