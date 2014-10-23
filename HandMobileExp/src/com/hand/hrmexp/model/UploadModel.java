package com.hand.hrmexp.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;

import org.apache.http.Header;

import com.hand.hrmexp.dao.MOBILE_EXP_REPORT_LINE;
import com.hand.hrms4android.exception.ParseExpressionException;
import com.hand.hrms4android.parser.Expression;
import com.hand.hrms4android.parser.xml.XmlConfigReader;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.AsHttpRequestModel;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UploadModel extends AsHttpRequestModel{

	private  XmlConfigReader configReader;	
	
	public UploadModel(LMModelDelegate delegate) {
		super(delegate);
		configReader = XmlConfigReader.getInstance();
	}
	
	public HashMap<String,String> packParam(final MOBILE_EXP_REPORT_LINE data)
	{
		HashMap <String,String> param = new HashMap<String,String>(){
			{
				put("expense_class_id",String.format("%d",data.expense_class_id));
				put("expense_type_id",String.format("%d",data.expense_type_id));
				put("expense_amount",String.format("%f", data.expense_amount));
				put("expense_number",String.format("%d", data.expense_number));
				put("expense_date",data.expense_date);
				put("expense_date_to",data.expense_date_to);
				put("expense_place",data.expense_place);
				put("description",data.description);
				put("local_id",String.format("%d",data.id));
				
			}
			
		};
		return param;
	}
	public void upload(MOBILE_EXP_REPORT_LINE data){
				
		String queryUrl = null;
		try {
			queryUrl = configReader
			        .getAttr(new Expression(
			                "/backend-config/url[@name='hmb_expense_detail_insert']",
			                "value"));
		} catch (ParseExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data.item1  != null){
			uploadBytes(queryUrl, packParam(data), data.item1, "upload1");
		}else {
			uploadBytes(queryUrl, packParam(data), data.item1, "");

			
		}
		
		
		
		
	}

}
