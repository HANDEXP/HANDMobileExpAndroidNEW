package com.hand.hrmexp.popwindows;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;  
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

import com.hand.R;
import com.handexp.utl.ConstantsUtl;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ExpenseTypePicker extends PopupWindow{
	public View Picker_city_province;
	 private TextView typelable; 
	
	private TextView done;
	private Context context;
	private WheelView province;
	private WheelView city;
	private JSONObject expense_type_data;
	private JSONArray expense_class;
	private JSONArray expense_type;
	
	boolean scrolling;
	boolean cityscrolling;
	
	//当前值
	public String expense_type_desc;
	public String expense_class_desc;
	public int expense_type_id;
	public int expense_class_id;
	
	public ExpenseTypePicker(Context context ,TextView typelable)
	{
		 
		this.context = context;
		this.typelable = typelable;

		
		 LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 Picker_city_province = inflater.inflate(R.layout.popwindows_picker_city_province, null);
		 
		 setContentView(Picker_city_province);
		 setWidth(LayoutParams.FILL_PARENT);
		 setHeight(LayoutParams.WRAP_CONTENT);
		
		init();
		
	}
	
	private void init()
	{	
		scrolling = false;
		
		done = 	(TextView) Picker_city_province.findViewById(R.id.done);
		done.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				ExpenseTypePicker.this.dismiss();
				
			}
			
			
		});
		
	    province = (WheelView) Picker_city_province.findViewById(R.id.province);
	    province.setWheelBackground(R.drawable.wheelview_shape);
//	    province.setDrawShadows(false);
	    province.setWheelForeground(R.drawable.wheelview_val);
	    
		city = (WheelView) Picker_city_province.findViewById(R.id.city);
		city.setWheelBackground(R.drawable.wheelview_shape);
		city.setDrawShadows(false);
		city.setWheelForeground(R.drawable.wheelview_val);
		
		SharedPreferences  mPreferences = PreferenceManager.getDefaultSharedPreferences ( context);
		
		try {
			expense_type_data= new JSONObject(mPreferences.getString(ConstantsUtl.tmp, ""));
			expense_class =  expense_type_data.getJSONObject("body").getJSONArray("list");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		

		
		

		province.setVisibleItems(4);
		province.setViewAdapter(new expenseclassAdapter(context));
		
		province.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!scrolling) {
		
					updatecity(newValue);
				}
			}
		});
		
		province.addScrollingListener( new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
				scrolling = true;
			}
			@Override
			public void onScrollingFinished(WheelView wheel) {
				scrolling = false;
				updatecity(wheel.getCurrentItem());
			}
		});
		
		province.setCurrentItem(1);
		
		
		city.addScrollingListener( new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
				cityscrolling = true;
			}
			@Override
			public void onScrollingFinished(WheelView wheel) {
				cityscrolling = false;
				updatelabel();
			}
		});

		
		city.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!cityscrolling) {
		
					updatelabel();
				}
			}
		});

	}
	
	private void updatecity(int index)
	{
		
		try {
			expense_type = expense_class.getJSONObject(index).getJSONArray("expense_types");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		city.setViewAdapter(new expensetypeAdapter(context));
		city.setCurrentItem(expense_type.length() / 2);
		updatelabel();
	}
	
	private void updatelabel()
	{
		int index = province.getCurrentItem();
		int index1 = city.getCurrentItem();

		try {
			expense_class_desc = expense_class.getJSONObject(index).getString("expense_class_desc");
			expense_type_desc = expense_type.getJSONObject(index1).getString("expense_type_desc");
			
			expense_class_id = expense_class.getJSONObject(index).getInt("expense_class_id");
			expense_type_id = expense_type.getJSONObject(index1).getInt("expense_type_id");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		typelable.setText( expense_class_desc + ">"+ expense_type_desc);
		
		
	}
	
	
	private class expenseclassAdapter extends AbstractWheelTextAdapter {
		
		// Countries flags

		/**
		 * Constructor
		 */
		protected expenseclassAdapter(Context context) {
			super(context, R.layout.picker_province, NO_RESOURCE);

			setItemTextResource(R.id.province_name);
			
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);

			return view;
		}

		@Override
		public int getItemsCount() {
			return expense_class.length();
		}

		@Override
		protected CharSequence getItemText(int index) {
			try {
				return expense_class.getJSONObject(index).getString("expense_class_desc");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "";
			
		}
	}
	
	
	
private class expensetypeAdapter extends AbstractWheelTextAdapter {
		
		// Countries flags

		/**
		 * Constructor
		 */
		protected expensetypeAdapter(Context context) {
			super(context, R.layout.picker_city, NO_RESOURCE);

			setItemTextResource(R.id.city_name);
			
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);

			return view;
		}

		@Override
		public int getItemsCount() {
			System.out.println(expense_type.length());
			return expense_type.length();
		}

		@Override
		protected CharSequence getItemText(int index) {
			try {
				return expense_type.getJSONObject(index).getString("expense_type_desc");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "";
			
		}
	}
}
