package com.hand.hrmexp.model;

import com.hand.hrms4android.parser.ConfigReader;
import com.hand.hrms4android.parser.Expression;
import com.hand.hrms4android.parser.xml.XmlConfigReader;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.LMRequestModel;

public class MenuModel extends LMRequestModel{
	
	public MenuModel(LMModelDelegate delegate){
		
	  
	}
	
	public void load()
	{
		
		try {
			
			String queryUrl = configReader
			        .getAttr(new Expression(
			                "/config/application/activity[@name='function_list_activity']/request/url[@name='function_query_url']",
			                "value"));
			
			
		} catch (Exception e) {
			
			return;
		}
		
		
	}

}
