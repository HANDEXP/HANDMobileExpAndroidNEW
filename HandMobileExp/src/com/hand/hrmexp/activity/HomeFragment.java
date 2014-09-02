package com.hand.hrmexp.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hand.R;

public class HomeFragment extends Fragment{
	 private View parentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		
		return   inflater.inflate(R.layout.activity_home, container, false);
		
		
	}
	
}
