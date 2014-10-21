package com.hand.hrmexp.activity;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hand.R;
import com.hand.hrmexp.model.LoginModel;
import com.littlemvc.model.LMModel;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.AsHttpRequestModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LoginActivity extends Activity implements LMModelDelegate {
	
	EditText usernameEditText;
	EditText passwordEditText;
	
	Button loginButton;
	
	LoginModel model;
	
	HashMap<String, String> loginParm;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		buildAllViews();
		
		model = new LoginModel(this);
		loginParm = new HashMap<String, String>();
		
		
		
	}

	
///////////////////////////////buildAllViews/////////////////////////
	private void buildAllViews(){
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.loginButton);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(usernameEditText.getText().toString().equals("")){
					Toast.makeText(LoginActivity.this, usernameEditText.getHint(), Toast.LENGTH_SHORT).show();
					return;
				};
				if(passwordEditText.getText().toString().equals("")){
					Toast.makeText(LoginActivity.this, passwordEditText.getHint(), Toast.LENGTH_LONG).show();
					return;
				};
				generateParm();
				model.load(loginParm);
				
			}
		});
	}
	
/**
 * 生成登录参数 	
 */
	private void generateParm(){
		
		loginParm.put("user_name", usernameEditText.getText().toString());
		loginParm.put("user_password", passwordEditText.getText().toString());
		loginParm.put("device_type", "Android");
		loginParm.put("push_token", "-1");
		loginParm.put("device_Id", "-1");	
	}

	@Override
	public void modelDidFinshLoad(LMModel model) {
		// TODO Auto-generated method stub

		AsHttpRequestModel reponseModel = (AsHttpRequestModel)model;
		String json = new String(reponseModel.mresponseBody);
		try {
			JSONObject jsonobj = new JSONObject(json);
			String code = ((JSONObject) jsonobj.get("head")).get("code").toString();
			if(code.equals("ok")){
				String token = ((JSONObject)jsonobj.get("body")).get("token").toString();

				Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
				startActivity(intent);
				finish();
			}else if (code.equals("failure")) {
				Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Toast.makeText(LoginActivity.this, "网络繁忙请稍后再试", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		} finally{
			
		};		
	}

	@Override
	public void modelDidStartLoad(LMModel model) {
		// TODO Auto-generated method stub
		

				
	}

	@Override
	public void modelDidFaildLoadWithError(LMModel model) {
		// TODO Auto-generated method stub
		
		Toast.makeText(LoginActivity.this, "网络繁忙请稍后再试", Toast.LENGTH_LONG).show();
	}
}
