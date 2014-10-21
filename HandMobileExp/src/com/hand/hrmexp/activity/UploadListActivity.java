package com.hand.hrmexp.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.hand.R;
import com.hand.hrmexp.activity.DetailListActivity;
import com.hand.hrmexp.adapter.ContactsInfoAdapter;
import com.hand.hrmexp.application.HrmexpApplication;
import com.hand.hrmexp.dao.MOBILE_EXP_REPORT_LINE;
import com.littlemvc.db.sqlite.FinalDb;

import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;

public class UploadListActivity extends ExpandableListActivity{
	
	List<List<String>> group;
	List<List<String[]>> child;
	List<Integer[]> flagList = new ArrayList<Integer[]>();
	ContactsInfoAdapter adapter;
	private FinalDb finalDb;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_uploadlist);
		finalDb   = HrmexpApplication.getApplication().finalDb;
		try {
			initializeData();
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ExpandableListView uploadListView = getExpandableListView();
		final ContactsInfoAdapter adapter = new ContactsInfoAdapter(group, child, UploadListActivity.this,R.layout.activity_upload_child,null);
		uploadListView.setAdapter(adapter);
		uploadListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View view, int groupPosition,
					int childPosition, long id) {
				// TODO Auto-generated method stub
				Integer[] array = new Integer[] {groupPosition,childPosition};
				ImageView selectImageView = (ImageView) view.findViewById(R.id.isSelectImage);
				//检查ChildlistView是否已经被选中
				for (Integer[] curreyArray : flagList) {
					if(curreyArray[0] == array[0] && curreyArray[1] == array[1]){
						flagList.remove(curreyArray);
						view.setBackgroundColor(Color.rgb(255, 255, 255));
						selectImageView.setImageResource(R.drawable.unselected);
						return false;
					};
				}
				flagList.add(array);
				view.setBackgroundColor(Color.rgb(255,255,204));
				selectImageView.setImageResource(R.drawable.selected);
				return true;
			}
		});
		
		//打开每一个Group
		int groupCount = uploadListView.getCount();
		for(int i =0; i<groupCount;i++){
			uploadListView.expandGroup(i);
		}
		//无法收缩
		uploadListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		Button testbutton = (Button) findViewById(R.id.testButton);
		testbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				Toast.makeText(UploadListActivity.this, "test", Toast.LENGTH_SHORT).show();
			}
		});
//		uploadListView.setGroupIndicator(this.getResources().getDrawable((Integer) null));
	}
	
	/**
	 * 
	 * 初始化数据
	 * @throws ParseException 
	 * 
	 * 
	 */
	
	private void initializeData() throws ParseException {
		group = new ArrayList<List<String>>();
		child = new ArrayList<List<String[]>>();
		String[] groupInfo = new String[2];
		List<String[]> childInfo = new ArrayList<String[]>();
		
		List<MOBILE_EXP_REPORT_LINE> resultList = finalDb.findAll(MOBILE_EXP_REPORT_LINE.class, "expense_date desc");
		String topDate = null; 
		Boolean flag = false;
		
		for(int i =0;i<resultList.size();i++){
			MOBILE_EXP_REPORT_LINE data = 	resultList.get(i);
			if(topDate == null){
				topDate = data.expense_date;
				groupInfo = new String[]{topDate,"累计：¥"};
			}
			flag = DetailListActivity.dateCompare(data.expense_date,topDate);
			if(flag){
					
					addInfo(groupInfo, childInfo);
					childInfo.clear();
					topDate = data.expense_date;
					groupInfo = new String[]{topDate,"累计：¥"};
			}
			
			
			childInfo.add(new String[]{data.expense_class_desc+'>'+data.expense_type_desc,data.description,"¥"+data.expense_amount});
//			childInfo.add(new String[]{"行车交通>公交地铁","无备注啊","¥50"});
		}
		addInfo(groupInfo, childInfo);

	}

	/**
	 * 构建绑定适配器的List
	 * @param g groupList
	 * @param c childList
	 */
	
	private void addInfo(String[] g, List<String[]> c) {
		// TODO Auto-generated method stub
		List<String> groupitem = new ArrayList<String>();
		List<String[]> childitem = new ArrayList<String[]>();
		for (int i = 0; i < g.length; i += 1) {
			groupitem.add(g[i]);
		}
		group.add(groupitem);
		for (int j = 0; j < c.size(); j += 1) {
			childitem.add(c.get(j));
		}
		child.add(childitem);
	}
	
}
