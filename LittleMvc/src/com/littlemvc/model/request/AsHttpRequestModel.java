/**
 * 
 */
package com.littlemvc.model.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;

import android.R.string;

import com.littlemvc.utl.ILMAsNetworkUtl;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author jiang titeng
 *
 * All right reserve
 */
public class AsHttpRequestModel extends LMRequestModel{
	
	public int mstatusCode;
	public Header[] mheaders;
	public byte[] mresponseBody;

	
	public static ILMAsNetworkUtl utl;//上下共用一个工具
	
	public AsHttpRequestModel()  {
		super();
		
		
	}
	
	public RequestParams  PackParam(HashMap param)
	{
		RequestParams requestParams = new RequestParams();
		
		Iterator it = param.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry  entry = (Map.Entry)it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			requestParams.put(key, value);
			
		}
		
		
		return requestParams;
		
		
	}
	
	public void get(String url,HashMap param)
	{	

		
		RequestParams params = null;
		
		if(param != null){
			params  =  this.PackParam(param);
		}
		
		
		utl.get(url, params, new AsyncHttpResponseHandler () {
		    public void onStart() {
		    	requestDidStartLoad(AsHttpRequestModel.this);
		    }
			
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				mstatusCode = statusCode;
				mheaders = headers;
				mresponseBody = responseBody;
				
				requestDidFinishLoad(AsHttpRequestModel.this);
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				mstatusCode = statusCode;
				mheaders = headers;
				mresponseBody = responseBody;
				
				
				requestDidFailLoadWithError(AsHttpRequestModel.this);
				
			}


			


		});
		
	}
	
	
	public void post(String url,HashMap param)
	{
		RequestParams requestParams = null;
		if(param !=null){
			requestParams = this.PackParam(param);
		}
		
		
		utl.post(url, requestParams, new AsyncHttpResponseHandler () {
		    public void onStart() {
		    	requestDidStartLoad(AsHttpRequestModel.this);
		    }
			
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				mstatusCode = statusCode;
				mheaders = headers;
				mresponseBody = responseBody;
				
				requestDidFinishLoad(AsHttpRequestModel.this);
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				mstatusCode = statusCode;
				mheaders = headers;
				mresponseBody = responseBody;
				
				
				requestDidFailLoadWithError(AsHttpRequestModel.this);
				
			}
		
		});

}
	
}
