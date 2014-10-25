//package com.hand.hrmexp.activity;
//
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
//import com.hand.R;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.SeekBar;
//import android.widget.SeekBar.OnSeekBarChangeListener;
//
//public class PieCharActivity extends Activity  implements OnSeekBarChangeListener,
//OnChartValueSelectedListener{
//	
//	
//	PieChart mChart;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		
//		super.onCreate(savedInstanceState);
//	
//		setContentView(R.layout.activity_pie_char);
//		buildAllViews();
//		
//	}
//	
//	@Override
//	protected void onResume() {
//		super.onResume();
//	}
//	
//	/////////////////////private/////////////////
//	private void buildAllViews()
//	{
//		
//		mChart = 	(PieChart) findViewById(R.id.piechat);
//		
//		
//	}
//	
//	
//	
//	
/////////////////////char  //////////////////////////////
//	@Override
//	public void onValueSelected(Entry e, int dataSetIndex) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onNothingSelected() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onProgressChanged(SeekBar seekBar, int progress,
//			boolean fromUser) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onStartTrackingTouch(SeekBar seekBar) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onStopTrackingTouch(SeekBar seekBar) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//	
//}
