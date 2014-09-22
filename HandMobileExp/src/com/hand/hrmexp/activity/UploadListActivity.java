package com.hand.hrmexp.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hand.R;
import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class UploadListActivity extends ExpandableListActivity{
	
	List<List<String>> group;
	List<List<String[]>> child;
	List<Integer[]> flag;
	ContactsInfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_uploadlist);
		initializeData();
		ExpandableListView uploadListView = getExpandableListView();
		final ContactsInfoAdapter adapter = new ContactsInfoAdapter();
		uploadListView.setAdapter(adapter);
		uploadListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View view, int groupPosition,
					int childPosition, long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(UploadListActivity.this, adapter.getChild(groupPosition, childPosition),Toast.LENGTH_SHORT).show();
				Integer[] array = new Integer[] {groupPosition,childPosition};
				//检查ChildlistView是否已经被选中
				for (Integer[] curreyArray : flag) {
					if(curreyArray[0] == array[0] && curreyArray[1] == array[1]){
//						Toast.makeText(getBaseContext(), "ddd", Toast.LENGTH_SHORT).show();
						flag.remove(curreyArray);
						view.setBackgroundColor(Color.rgb(255, 255, 255));
						return false;
					};
				}
				flag.add(array);
				view.setBackgroundColor(Color.rgb(153, 153, 204));
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
	}
	

	
	private void initializeData() {
		group = new ArrayList<List<String>>();
		child = new ArrayList<List<String[]>>();
		flag = new ArrayList<Integer[]>();
		
		addInfo(new String[] {"2014-09-13","累计：¥600"}, new String[][] { {"行车交通>公交地铁","无备注啊","¥50"},{"行车交通>公交地铁","无备注啊","¥500"},{"行车交通>公交地铁","无备注啊","¥50"} });
		addInfo(new String[] {"2014-09-10","累计：¥200"}, new String[][] { {"行车交通>公交地铁","无备注啊","¥150"},{"行车交通>公交地铁","无备注啊","¥20"},{"行车交通>公交地铁","无备注啊","¥30"} });

	}

	private void addInfo(String[] g, String[][] c) {
		// TODO Auto-generated method stub
		List<String> groupitem = new ArrayList<String>();
		List<String[]> childitem = new ArrayList<String[]>();
		for (int i = 0; i < g.length; i += 1) {
			groupitem.add(g[i]);
		}
		group.add(groupitem);
		for (int j = 0; j < c.length; j += 1) {
			childitem.add(c[j]);
		}
		child.add(childitem);
	}	
	
	class ContactsInfoAdapter extends BaseExpandableListAdapter{

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
				ViewGroup parents) {
			// TODO Auto-generated method stub
			String[] childInfo = child.get(groupPosition).get(childPosition);
			String typeString = childInfo[0];
			String descString = childInfo[1];
			String amountString = childInfo[2];
			convertView = (View) LayoutInflater.from(UploadListActivity.this).inflate(R.layout.activity_list2,null);
			TextView type = (TextView) convertView.findViewById(R.id.typeText);
			type.setText(typeString);
			TextView desc = (TextView) convertView.findViewById(R.id.descText);
			desc.setText(descString);
			TextView amount = (TextView) convertView.findViewById(R.id.detailAmount);
			amount.setText(amountString);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return group.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return group.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			List<String> string = group.get(groupPosition);
			return getGenericView(string);
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
		//重载以构建expandList
		
		public View getGenericView(List<String> s) {

			RelativeLayout view = new RelativeLayout(UploadListActivity.this);
//			view.setOrientation(0);
			TextView dateTextView = getTextView(s.get(0));
			RelativeLayout.LayoutParams lpLeft = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);  
			lpLeft.leftMargin = 20;
			lpLeft.addRule(RelativeLayout.CENTER_VERTICAL);
			dateTextView.setLayoutParams(lpLeft);
			dateTextView.setTextSize(12);
			view.addView(dateTextView);
//			//累计
			TextView sumTextView = getTextView(s.get(1));
			RelativeLayout.LayoutParams lpRight = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);  
			lpRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			lpRight.addRule(RelativeLayout.CENTER_VERTICAL);
			lpRight.rightMargin = 20;
			sumTextView.setLayoutParams(lpRight);
			sumTextView.setTextSize(12);
			sumTextView.setTextColor(Color.rgb(102, 102, 102));
			view.addView(sumTextView);			
			return view;
		}
		
		// 自己定义一个获得文字信息的方法
		TextView getTextView(String s) {
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 40);
			TextView text = new TextView(UploadListActivity.this);
			text.setLayoutParams(lp);

			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			text.setPadding(36, 0, 0, 0);
			text.setText(s);
			return text;
		}		
	}
}
