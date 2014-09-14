/**
 * 
 */
package com.hand.hrmexp.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.hand.R;
import com.hand.hrmexp.model.ExpenseTypeModel;
import com.hand.hrmexp.model.LoadingModel;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.SherlockActivity;
import com.littlemvc.model.LMModel;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.AsHttpRequestModel;

import com.hand.hrmexp.activity.SettingsActivity;
import com.hand.hrmexp.application.HrmexpApplication;
import com.hand.hrms4android.exception.ParseException;
import com.hand.hrms4android.parser.ConfigReader;
import com.hand.hrms4android.parser.Expression;
import com.hand.hrms4android.parser.xml.XmlConfigReader;
import com.handexp.utl.AsNetWorkUtl;
import com.handexp.utl.ConstantsUtl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract.Constants;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author jiang titeng
 *
 * All right reserve
 */
public class LoadingActivity extends SherlockActivity implements LMModelDelegate{
	
	private SharedPreferences mPreferences;
	private String  baseUrl;
	private ActionBarSherlock mActionBarHelper; 
	
	private ExpenseTypeModel model;
	
	private Button reloadButton;
	private TextView informationTextView;
	private ImageView alertImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		 
		buildAllviews();
		
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		baseUrl = mPreferences.getString("sys_basic_url", "");
		
		model = new ExpenseTypeModel(this);
		
		
		if(!checkBaseUrl(baseUrl)){
			
			startSettingsActivity();
		}

		
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		setViewAsNew();
//		startLoginActivity();
		
		baseUrl = mPreferences.getString("sys_basic_url", "");
		System.out.println("base is " + baseUrl);
		if (checkBaseUrl(baseUrl)) {
			AsHttpRequestModel.utl =  AsNetWorkUtl.getAsNetWorkUtl(baseUrl);
			
			doReload();
		}
	}
	
	public void doReload() {
		
		setViewAsNew();
		this.model.load();
		


	}
	
	@Override
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
		if (item.getItemId() == R.id.menu_settings) {
			startSettingsActivity();
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		getSherlock().getMenuInflater().inflate(R.menu.activity_loading, menu);
		return true;
	}
	
	
	private boolean checkBaseUrl(String url) {
		if (url == null) {
			return false;
		}

		if (url.length() == 0) {
			return false;
		}

		if (url.equals("http://")) {
			return false;
		}

		return true;
	}
	

	private void  buildAllviews(){
		informationTextView = (TextView) findViewById(R.id.activity_loading_infomation);
		reloadButton = (Button) findViewById(R.id.activity_loading_reload_button);
		alertImage = (ImageView) findViewById(R.id.activity_loading_alert);


	}
	
	private void setViewAsNew() {
		informationTextView.setText(R.string.activity_loading_text);
		reloadButton.setVisibility(View.INVISIBLE);
		alertImage.setVisibility(View.INVISIBLE);
	}
	
	private void startSettingsActivity() {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
		
	}
	
	private void startLoginActivity(){
		Intent i = new Intent(this,MenuActivity.class);
		startActivity(i);
		finish();
	}

/*
 * model delegate
 */
	@Override
	public void modelDidFinshLoad(LMModel model ) {
		// TODO Auto-generated method stub
		
		
		AsHttpRequestModel model1 = (AsHttpRequestModel)model;
		
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        try {
			editor.putString(ConstantsUtl.tmp ,new String(model1.mresponseBody,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        editor.commit();
		
        startLoginActivity();
//		File dir = HrmexpApplication.getApplication().getDir(ConstantsUtl.SYS_PREFRENCES_CONFIG_FILE_DIR_NAME,
//		        Context.MODE_PRIVATE);
//		File configFile = new File(dir, ConstantsUtl.requestUrl);
//		System.out.println(" file url is " + configFile.getAbsolutePath());
//		FileOutputStream fileOutputStream = null;
//		
//		try {
//			fileOutputStream = new FileOutputStream(configFile);
//			fileOutputStream.write(model1.mresponseBody);
//		} catch (Exception ex) {
//	
//			
//			return;
//		}finally{
//			try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//		}
//		
//
//		try {
//			ConfigReader reader = XmlConfigReader.getInstance();
//			reader.getAttr(new Expression("/backend-config", ""));
//		} catch (Exception e) {
//			
//			return;
//		}
//		
//		startLoginActivity();
		
	}


	@Override
	public void modelDidStartLoad(LMModel model) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modelDidFaildLoadWithError(LMModel model) {
		// TODO Auto-generated method stub
		
	}




}
