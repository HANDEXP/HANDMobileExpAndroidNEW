package com.hand.hrmexp.model;

import com.hand.hrmexp.dao.MOBILE_EXP_REPORT_LINE;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.db.DbRequestModel;

public class PieCharModel extends DbRequestModel{
	
	public PieCharModel(LMModelDelegate delegate)
	{
		super(delegate);
		
	}
	
	public void load(String _date)
	{
		query(MOBILE_EXP_REPORT_LINE.class, "expense_date like  '" + _date + "%'" , null);
		System.out.println("expense_date like  '" + _date + "%'");
	}
	
}
