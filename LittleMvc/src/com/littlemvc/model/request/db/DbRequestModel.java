package com.littlemvc.model.request.db;

import com.littlemvc.db.sqlite.FinalDb;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.LMRequestModel;

public class DbRequestModel extends LMRequestModel{
	
	//上下文共用一个工具
	public static FinalDb finalDb;
	
	public DbRequestModel(LMModelDelegate delegate)
	{
		super();
		this.modelDelegate = delegate;
		
	}
	
	public void insert(Object obj)
	{
		finalDb.save(obj);
		
	}
	
}
