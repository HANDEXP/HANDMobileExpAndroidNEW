package com.hand.hrmexp.application;

import com.handexp.utl.AsNetWorkUtl;

import android.app.Application;

public class HrmexpApplication extends Application{
	private static 	HrmexpApplication instance;
	
	
	public static HrmexpApplication getApplication(){
		return instance;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;

		
		
	}
	
	
}
