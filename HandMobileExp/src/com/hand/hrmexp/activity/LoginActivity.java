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
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LoginActivity extends Activity implements LMModelDelegate {
	
	TextView username;
	HashMap parm;
	TextView password;
	private void generateParm(){
		
		parm = new HashMap();
//		System.out.println(username.getText().toString());
		parm.put("user_name", username.getText().toString());
		parm.put("user_password", password.getText().toString());
		parm.put("device_type", "iphone");
		parm.put("push_token", "-1");
		parm.put("device_Id", "-1");	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		Button loginButton = (Button) findViewById(R.id.loginButton);
		final LoginModel model = new LoginModel(this);
		model.modelDelegate = this;
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(username.getText().toString().equals("")){
					Toast.makeText(LoginActivity.this, username.getHint(), Toast.LENGTH_SHORT).show();
					return;
				};
				if(password.getText().toString().equals("")){
					Toast.makeText(LoginActivity.this, password.getHint(), Toast.LENGTH_LONG).show();
					return;
				};
				generateParm();
				model.load(parm);
				
//				Toast.makeText(LoginActivity.this, foo, Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
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
				System.out.print(token+"\n");
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
		System.out.println("load");	
	}

	@Override
	public void modelDidStartLoad(LMModel model) {
		// TODO Auto-generated method stub
		System.out.println("start");

				
	}

	@Override
	public void modelDidFaildLoadWithError(LMModel model) {
		// TODO Auto-generated method stub
		System.out.println("error");
		Toast.makeText(LoginActivity.this, "网络繁忙请稍后再试", Toast.LENGTH_LONG).show();
	}
}
