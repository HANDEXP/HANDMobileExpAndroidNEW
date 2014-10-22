package com.hand.hrmexp.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.hand.R;
import com.hand.hrmexp.adapter.ContactsInfoAdapter;
import com.hand.hrmexp.application.HrmexpApplication;
import com.hand.hrmexp.dao.MOBILE_EXP_REPORT_LINE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.littlemvc.db.sqlite.FinalDb;

public class DetailListActivity extends SherlockActivity {

	private List<List<String>> group;
	private List<List<MOBILE_EXP_REPORT_LINE>> child;
	private ContactsInfoAdapter adapter;
	private ExpandableListView detailListView;
	private TextView amountView;
	private  FinalDb finalDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TAG",getBaseContext().toString());
		setContentView(R.layout.activity_detaillist);
		
		//加载ActionBar设置标题
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_titlebar));
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.abs_layout);
		TextView titleView = (TextView) findViewById(R.id.contextTitle);
		titleView.setText("报销明细");
		//绑定ActionBar按钮事件
		ImageView returnView = (ImageView) findViewById(R.id.returnImage);
		returnView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		ImageView addView = (ImageView) findViewById(R.id.addImage);
		addView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(DetailListActivity.this, DetailLineActivity.class);
				startActivity(intent);
			}
		});
		//获取总金额View
		amountView = (TextView) findViewById(R.id.Amount);
		//创建数据库链接
		finalDb   = HrmexpApplication.getApplication().finalDb;

		detailListView = (ExpandableListView) findViewById(R.id.list);

		detailListView.setOnGroupClickListener(new OnGroupClickListener() {	
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		detailListView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Integer[] itemPosition = getItemIndex(position);
				//console
				Log.d("X",itemPosition[0].toString());
				Log.d("Y",itemPosition[1].toString());	
				//判断是否汇总行
				if(itemPosition[1] == -1){
					return false;
				}
				getAlertDialog(getItemIndex(position));					
				return false;
			}
			
		});

		detailListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO 自动生成的方法存根
//				Toast.makeText(getApplicationContext(), child.get(groupPosition).get(childPosition)[3], Toast.LENGTH_SHORT).show();
				Integer detailId = child.get(groupPosition).get(childPosition).id;
				Intent intent = new Intent(DetailListActivity.this, DetailLineActivity.class);
				intent.putExtra("detailId", detailId);
				startActivity(intent);
		        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

				return false;
			}
		});
		
	}	
	
	/**
	 * 
	 * Activity加载完毕后检查汇总金额
	 * 
	 */
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
		adapter.checkSum();
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
//		getSherlock().getMenuInflater().inflate(R.menu.activity_loading, menu);
		return true;
	}
	
	/**
	 * 
	 * 插入测试数据
	 * 
	 * 
	 */
	private void loadTestingDate() {
		MOBILE_EXP_REPORT_LINE  line = 	 new MOBILE_EXP_REPORT_LINE();
		line.expense_amount = 100;
		line.expense_number = 1;
		line.expense_date ="2014-03-01";
		line.expense_date_to ="2014-03-01";
		line.expense_class_desc = "交通费";
		line.expense_type_desc = "地铁";
		line.expense_class_id = 1;
		line.expense_type_id = 1;
		line.expense_place = "上海市>上海市";
		
		line.local_status = "new";
		//描述
		line.description = "无备注";
		
		finalDb.save(line);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
//			loadTestingDate();
			initializeData();
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		adapter = new ContactsInfoAdapter(group,child,this,R.layout.activity_detail_child,amountView);
		detailListView.setAdapter(adapter);
		//打开每一个Group
		int groupCount = detailListView.getCount();
		for(int i =0; i<groupCount;i++){
			detailListView.expandGroup(i);
		}
	};
	
	/**
	 * 
	 * 初始化数据
	 * @throws ParseException 
	 * 
	 * 
	 */
	
	private void initializeData() throws ParseException {
		group = new ArrayList<List<String>>();
		child = new ArrayList<List<MOBILE_EXP_REPORT_LINE>>();
		String[] groupInfo = new String[2];
		List<MOBILE_EXP_REPORT_LINE> childInfo = new ArrayList<MOBILE_EXP_REPORT_LINE>();
		
		List<MOBILE_EXP_REPORT_LINE> resultList = finalDb.findAll(MOBILE_EXP_REPORT_LINE.class, "expense_date desc");
		String topDate = null; 
		Boolean flag = false;
		for(int i =0;i<resultList.size();i++){
			MOBILE_EXP_REPORT_LINE data = 	resultList.get(i);
			if(topDate == null){
				topDate = data.expense_date;
				groupInfo = new String[]{topDate,"累计：¥"};
			}
			flag = dateCompare(data.expense_date,topDate);
			if(flag){
					
					addInfo(groupInfo, childInfo);
					childInfo.clear();
					topDate = data.expense_date;
					groupInfo = new String[]{topDate,"累计：¥"};
			}
			childInfo.add(data);
		}
		if(childInfo.size() != 0){
			addInfo(groupInfo, childInfo);
		}
		
//		addInfo(new String[] {"2014-09-13","累计：¥600"},childInfo);
	}

	private void addInfo(String[] g, List<MOBILE_EXP_REPORT_LINE> c) {
		// TODO Auto-generated method stub
		List<String> groupitem = new ArrayList<String>();
		List<MOBILE_EXP_REPORT_LINE> childitem = new ArrayList<MOBILE_EXP_REPORT_LINE>();
		for (int i = 0; i < g.length; i += 1) {
			groupitem.add(g[i]);
		}
		group.add(groupitem);
		for (int j = 0; j < c.size(); j += 1) {
			childitem.add(c.get(j));
		}
		child.add(childitem);
	}


	
	//创建弹出框
	private void getAlertDialog(final Integer[] position){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("你确定要删除这条数据");
		dialog.setMessage("删除后不可撤销");
		dialog.setCancelable(true);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				int groupIndex = position[0];
				int childIndex = position[1];
				/**
				 *  待完善
				 */
				Integer detailId = child.get(groupIndex).get(childIndex).id;
				MOBILE_EXP_REPORT_LINE  line = 	 new MOBILE_EXP_REPORT_LINE();
				line.id = detailId;
				finalDb.deleteById(MOBILE_EXP_REPORT_LINE.class, line.id);
				child.get(groupIndex).remove(childIndex);
				//检查汇总
				if(adapter.checkSum(groupIndex) == 0){
					group.remove(groupIndex);
				};
				dialog.dismiss();
				
				adapter.notifyDataSetChanged();
				
			}
		});
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	//获得索引
	private Integer[] getItemIndex(int position){
		Integer groupLen = group.size();
		Integer total = 0;
		Integer temp = 0;
		for(int i = 0;i < groupLen;i += 1){
			temp = total;
			if(temp == position){
				return new Integer[]{i,-1};
			}
			total += 1 + child.get(i).size();
			if(total > position)
				return new Integer[]{i,position-1-temp};
		}
		
		return new Integer[]{-1,-1};
	}
	
	/**
	 * 比较日期,
	 * @throws ParseException 
	 * b < e return true
	 * 
	 */
	public static boolean dateCompare(String bDateString, String eDateString) throws ParseException {
		boolean flag = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
		Date bDate = format.parse(bDateString);
		Date eDate = format.parse(eDateString);
		try {
			if (bDate.before(eDate)) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

}
