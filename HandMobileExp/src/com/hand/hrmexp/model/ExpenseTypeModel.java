package com.hand.hrmexp.model;

import com.handexp.utl.ConstantsUtl;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.AsHttpRequestModel;

public class ExpenseTypeModel extends AsHttpRequestModel {
	
	
	public ExpenseTypeModel(LMModelDelegate delegate){
		super(delegate);
		
		this.modelDelegate = delegate;
	}
	
	public void load()
	{
		this.post(ConstantsUtl.tmp, null);
		
	}

}
