package com.hand.hrmexp.model;

import com.handexp.utl.ConstantsUtl;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.AsHttpRequestModel;
import com.littlemvc.model.request.LMRequestModel;

public class LoadingModel extends AsHttpRequestModel {

	
	public LoadingModel(LMModelDelegate delegate){
		this.modelDelegate = delegate;
	}
	
	public void load()
	{
		this.post(ConstantsUtl.requestUrl, null);
		
	}

}
