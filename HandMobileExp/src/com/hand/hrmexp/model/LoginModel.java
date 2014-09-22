package com.hand.hrmexp.model;

import java.util.HashMap;

import android.util.Log;

import com.handexp.utl.AsNetWorkUtl;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.*;

public class LoginModel extends AsHttpRequestModel{
		
	public LoginModel(LMModelDelegate delegate){
		this.modelDelegate = delegate;
		AsHttpRequestModel.utl = new AsNetWorkUtl("http://m.hand-china.com/dev/");
	}
	public void load(HashMap parm)
	{
		this.post("modules/mobile_um/client/commons/login/mbs_login.svc", parm);
		
	}

	
}
