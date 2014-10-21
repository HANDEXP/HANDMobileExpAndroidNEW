/**
 * 
 */
package com.handexp.utl;

import com.littlemvc.utl.ILMAsNetworkUtl;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * @author jiang titeng
 *
 * All right reserve
 */
public class AsNetWorkUtl   extends ILMAsNetworkUtl{
	
	private static AsNetWorkUtl utl;

	public static AsNetWorkUtl getAsNetWorkUtl(String url)
	{
		if(utl == null){
			
			utl = new AsNetWorkUtl(url);
			
		}
		
		return utl;
			
		
	}
	
	public  String getAbsoluteUrl(String relativeUrl) {

		return getBaseUrl() + relativeUrl;
	}
	
	public  AsNetWorkUtl( String url) {
		setBaseUrl(url);
		
	}


	@Override
	public void get(String url, RequestParams param,
			AsyncHttpResponseHandler handler) {
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get(getAbsoluteUrl(url), param, handler);
	}



	@Override
	public void post(String url, RequestParams param,
			AsyncHttpResponseHandler handler) {
		// TODO Auto-generated method stub
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.post(getAbsoluteUrl(url), param, handler);
		
	}

}
