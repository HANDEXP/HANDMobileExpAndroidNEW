package com.hand.hrmexp.application;

import com.handexp.utl.AsNetWorkUtl;
import com.littlemvc.db.sqlite.FinalDb;
import com.littlemvc.model.request.db.DbRequestModel;

import android.app.Application;

public class HrmexpApplication extends Application{
	private static 	HrmexpApplication instance;
	public android.support.v4.app.FragmentTransaction  transaction;
	
	
	public  FinalDb finalDb ;
	
	
	public static HrmexpApplication getApplication(){
		return instance;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		finalDb = FinalDb.create(this);
		DbRequestModel.finalDb = finalDb;
		System.out.println(getFilesDir().getAbsolutePath());
	}
	
	
}
