package com.hand.hrmexp.activity;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.hand.R;
import com.hand.hrmexp.adapter.CurrencyListViewAdapter;
import com.hand.hrmexp.model.CurrencyModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CurrencyListActivity extends SherlockActivity{
	
	private ListView currencyListView;
	private List<CurrencyModel> dataList;
	private CurrencyListViewAdapter adapter;
	public int CURRENCY_CONTENT = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_currency_list);
		//加载ActionBar设置标题
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_titlebar));
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.abs_layout);
		TextView titleView = (TextView) findViewById(R.id.contextTitle);
		titleView.setText("币种选择");
		//绑定ActionBar按钮事件
		ImageView returnView = (ImageView) findViewById(R.id.returnImage);
		returnView.setVisibility(View.VISIBLE);
		returnView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		ImageView addView = (ImageView) findViewById(R.id.addImage);	
		addView.setVisibility(View.GONE);
		dataList = new ArrayList<CurrencyModel>();
		bindAllViews();
	}
	
	private void bindAllViews(){
		currencyListView = (ListView) findViewById(R.id.currencyListView);
		dataList.add(new CurrencyModel("CNY-人民币", "1.00"));
		dataList.add(new CurrencyModel("USD-美元", "6.67"));
		adapter = new CurrencyListViewAdapter(getApplicationContext(), dataList);
		currencyListView.setAdapter(adapter);
		
		currencyListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				String currency = dataList.get(position).getCurrency();
				String exchangeRate = dataList.get(position).getExchangeRate();
				intent.putExtra("currency", currency);
				intent.putExtra("exchangeRate", exchangeRate);
				setResult(CURRENCY_CONTENT, intent);
				
				finish();				
			}
		});
	}
}
