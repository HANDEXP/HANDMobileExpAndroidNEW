package com.hand.hrmexp.activity;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.util.List;

import ui.custom.component.NumberText;

import com.hand.R;
import com.hand.hrmexp.application.HrmexpApplication;
import com.hand.hrmexp.dao.MOBILE_EXP_REPORT_LINE;
import com.hand.hrmexp.dialogs.DatePickerWrapDialog;
import com.hand.hrmexp.popwindows.CalendarPopwindow;
import com.hand.hrmexp.popwindows.ExpenseTypePopwindow;
import com.handexp.utl.DialogUtl;
import com.handexp.utl.ViewUtl;
import com.littlemvc.db.sqlite.FinalDb;
import com.littlemvc.model.LMModel;
import com.littlemvc.model.LMModelDelegate;
import com.littlemvc.model.request.db.DbRequestModel;
import com.nineoldandroids.animation.ObjectAnimator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailLineActivity extends Activity implements
		View.OnClickListener, LMModelDelegate {

	// 费用类型
	private LinearLayout expenseTypell;

	private TextView expenseTypeTextView;

	private ExpenseTypePopwindow expenseTypePicker;

	// 地点
	private LinearLayout placell;

	private EditText placeEditText;
	// 日期
	private LinearLayout datell;

	private TextView dateToTextView;

	private TextView dateFromTextView;

	private DatePickerWrapDialog dateToDateDialog;

	private DatePickerWrapDialog dateFromDateDialog;
	// 返回
	private ImageButton returnImgBtn;
	// 拍照
	private ImageView photoImgView;
	// 照片实际的数据
	private byte[] mContent;

	// 备注
	private EditText commentEditText;

	// 数量
	private EditText quantityEditText;
	// 单价
	private ui.custom.component.NumberText priceNumerText;
	// 总金额
	private TextView amountTextView;

	// 保存按钮
	private Button saveBtn;

	// 根view
	private View rootView;

	// 数据库

	private Boolean flag;

	// 拍照
	public static final int IMAGE_CAPTURE = 0;

	// 相册
	public static final int ACTION_GET_CONTENT = 1;

	public DbRequestModel dbmodel;

	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail_line);

		buildAllviews();

		dbmodel = new DbRequestModel(this);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case IMAGE_CAPTURE:
			Bundle extras = data.getExtras();
			Bitmap bitmap = (Bitmap) extras.get("data");
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			photoImgView.setImageBitmap(bitmap);
			mContent = baos.toByteArray();
			break;
		case ACTION_GET_CONTENT:
			break;

		}

	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null) {
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
			expenseTypePicker.dismiss();
			return super.dispatchTouchEvent(event); 

		}
		
		return super.dispatchTouchEvent(event);
	}

	// ///////////////private //////////////////////////////////
	
	
	
	//对保存按钮进行动画操作
	private void btnAnimation()
	{

//		 ObjectAnimator.ofInt(new ViewWrapper(saveBtn), "width", saveBtn.getWidth(),saveBtn.getWidth()/2).setDuration(1000).start();
	}
	
	
	
	private void buildAllviews() {

		rootView = findViewById(R.id.detail_line_id);

		// 费用类型
		expenseTypell = (LinearLayout) findViewById(R.id.expense_type);
		expenseTypell.setOnClickListener(this);
		expenseTypeTextView = (TextView) findViewById(R.id.detailTypeLabel);
		expenseTypePicker = new ExpenseTypePopwindow(this, expenseTypeTextView);

		// 日期
		datell = (LinearLayout) findViewById(R.id.llcalendar_id);

		dateToTextView = (TextView) findViewById(R.id.dateToTextView);
		dateToTextView.setOnClickListener(this);
		dateFromTextView = (TextView) findViewById(R.id.dateFromTextView);
		dateFromTextView.setOnClickListener(this);

		dateToDateDialog = new DatePickerWrapDialog(this, dateToTextView);

		dateFromDateDialog = new DatePickerWrapDialog(this, dateFromTextView);
		// 照相

		photoImgView = (ImageView) findViewById(R.id.cameraImageView);
		photoImgView.setOnClickListener(this);

		new ExpenseTypePopwindow(this, expenseTypeTextView);

		// 数量单价总金额
		quantityEditText = (EditText) findViewById(R.id.quantityEditText);
		quantityEditText.addTextChangedListener(amountTextWatcher);

		priceNumerText = (NumberText) findViewById(R.id.priceNumberText);
		priceNumerText.addTextChangedListener(amountTextWatcher);

		amountTextView = (TextView) findViewById(R.id.amountTextView);

		// 地点
		placeEditText = (EditText) findViewById(R.id.placeEditText);

		// 备注
		commentEditText = (EditText) findViewById(R.id.expense_desc_id);

		// 保存
		saveBtn = (Button) findViewById(R.id.save_button);
		saveBtn.setOnClickListener(this);

	}

	// 保存逻辑
	private void save() {
		String priceNumber = priceNumerText.getText().toString();

		if (priceNumber.equalsIgnoreCase("")
				|| priceNumber.equalsIgnoreCase("0")) {
			
			return;
		}

		MOBILE_EXP_REPORT_LINE line = new MOBILE_EXP_REPORT_LINE();

		line.expense_amount = Integer.parseInt(priceNumerText.getText()
				.toString());

		if (!quantityEditText.getText().toString().equalsIgnoreCase("")) {
			line.expense_number = Integer.parseInt(quantityEditText.getText()
					.toString());
		} else {
			line.expense_number = 1;

		}
		// 日期
		line.expense_date = dateToTextView.getText().toString();
		line.expense_date_to = dateFromTextView.getText().toString();

		// 费用类型
		line.expense_class_desc = expenseTypePicker.expense_class_desc;
		line.expense_type_desc = expenseTypePicker.expense_type_desc;
		line.expense_class_id = expenseTypePicker.expense_class_id;
		line.expense_type_id = expenseTypePicker.expense_type_id;

		// 地点
		line.expense_place = placeEditText.getText().toString();

		line.local_status = "NEW";
		// 图片

		line.item1 = mContent;
		// 描述

		line.description = commentEditText.getText().toString();

		dbmodel.insert(line);
		btnAnimation();
	}

	// ////////////////////click////////////////////////////
	@Override
	public void onClick(View v) {

		// 点击费用类型，弹出picker选择
		if (v.equals(expenseTypell)) {
			expenseTypePicker.showAtLocation(rootView, Gravity.BOTTOM
					| Gravity.CENTER, 0, 0);

		} else if (v.equals(photoImgView)) {
			// 拍照逻辑
			final CharSequence[] items = { "相册", "拍照" };
			AlertDialog dlg = new AlertDialog.Builder(DetailLineActivity.this)
					.setTitle("选择图片")
					.setItems(items, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							// 这里item是根据选择的方式，
							// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
							if (item == 1) {
								Intent getImageByCamera = new Intent(
										"android.media.action.IMAGE_CAPTURE");
								startActivityForResult(getImageByCamera,
										IMAGE_CAPTURE);
							} else {
								Intent getImage = new Intent(
										Intent.ACTION_GET_CONTENT);
								getImage.addCategory(Intent.CATEGORY_OPENABLE);
								getImage.setType("image/jpeg");
								startActivityForResult(getImage,
										ACTION_GET_CONTENT);
							}
						}
					}).create();
			dlg.show();
		} else if (v.equals(saveBtn)) {
			// 保存按钮
			save();

		} else if (v.equals(dateToTextView)) {
			dateToDateDialog.showDateDialog();

		} else if (v.equals(dateFromTextView)) {

			dateFromDateDialog.showDateDialog();

		}

	}

	// ///////////////////////////text watch/////////////////////////
	// 当数量金额变化后动态改变总金额
	TextWatcher amountTextWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {

			// TODO 当两个输入框体没有任何值的时候，这些值为"",而不是Null
			float priceNumber, amount;
			int quantity;

			// 当还没输入值的时候 ，使用默认值
			if (priceNumerText.getText().toString().equalsIgnoreCase("")) {

				priceNumber = 0;
			} else {
				priceNumber = Float.parseFloat(priceNumerText.getText()
						.toString());

			}

			if (quantityEditText.getText().toString().equalsIgnoreCase("")) {

				quantity = 1;

			} else {
				quantity = Integer.parseInt(quantityEditText.getText()
						.toString());
			}

			amount = priceNumber * quantity;

			amountTextView.setText(String.format("%.2f", amount));

		}

	};

	// ////////////////////////lmdelegate/////////////////////////////////////////////////

	@Override
	public void modelDidFinshLoad(LMModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelDidStartLoad(LMModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelDidFaildLoadWithError(LMModel model) {
		// TODO Auto-generated method stub

	}
	
	private static class ViewWrapper {
	    private View mTarget;
	 
	    public ViewWrapper(View target) {
	        mTarget = target;
	    }
	 
	    public int getWidth() {
	        return mTarget.getLayoutParams().width;
	    }
	 
	    public void setWidth(int width) {
	        mTarget.getLayoutParams().width = width;
	        mTarget.requestLayout();
	    }

	}
}
