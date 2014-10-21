package com.hand.hrmexp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hand.R;
import com.hand.hrmexp.application.HrmexpApplication;
import com.mas.customview.ImageViewPager;

public class HomeFragment extends Fragment{
	 private View rootview;
	 private Button btn; 
	 private LinearLayout expDetailLinell;
	 private LinearLayout uploadListll;
	 private android.support.v4.app.FragmentTransaction transaction;
	 
	 private ImageViewPager imageViewPager;   
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview  = inflater.inflate(R.layout.activity_home, container, false);
		buildAllViews();
		
		
		return   rootview;
		
		
	}
	
///////////////////private//////////////////////	
	private void buildAllViews()
	{
		transaction = 	HrmexpApplication.getApplication().transaction;
		
		imageViewPager = (ImageViewPager) rootview.findViewById(R.id.imageViewPager);
		
		imageViewPager.setDrawables(new int[]{R.drawable.display1,R.drawable.display2,R.drawable.display3});
		
		btn = (Button) rootview.findViewById(R.id.writeBtn);
		btn.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				
				Intent intent = 	new Intent(getActivity(),DetailLineActivity.class);
				startActivity(intent);
				getActivity().finish();
				
//				transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//				transaction.replace(R.id.main_fragment, new DetailLineFragment()).commit();
				
			}
		});
		
		expDetailLinell = (LinearLayout) rootview.findViewById(R.id.expDetailLinell);
		expDetailLinell.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent =  new Intent(getActivity(),DetailListActivity.class);
				startActivity(intent);
				
			}
		});
		uploadListll = (LinearLayout) rootview.findViewById(R.id.uploadListll);
		uploadListll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent =  new Intent(getActivity(),UploadListActivity.class);
				startActivity(intent);				
			}
		});
		
	}
	
}
