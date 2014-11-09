package com.hand.hrmexp.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mikephil.charting.utils.Utils;
import com.hand.R;

import com.hand.hrmexp.model.ImageItem;
import com.handexp.utl.BimpUtl;
import com.handexp.utl.BitmapUtl;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageItemAdapter extends BaseAdapter {

	private TextCallback textcallback = null;
	private Context context;
	private List<ImageItem> dataList;
	Map<String, String> map = new HashMap<String, String>();
	private Handler mHandler;
	private int selectTotal = 0;

	public static interface TextCallback {
		public void onListen(int count);
	}

	public void setTextCallback(TextCallback listener) {
		textcallback = listener;
	}

	public ImageItemAdapter(Context context, List<ImageItem> dataList,
			Handler mHandler) {
		this.context = context;
		this.dataList = dataList;
		this.mHandler = mHandler;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		int count = 0;
		if (dataList != null) {
			count = dataList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	class Holder {
		private ImageView iv;
		private ImageView selected;
		private TextView text;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = View.inflate(context, R.layout.item_image_grid, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.image);
			holder.selected = (ImageView) convertView
					.findViewById(R.id.isselected);
			holder.text = (TextView) convertView
					.findViewById(R.id.item_image_grid_text);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
			holder.selected.setImageResource(-1);
			holder.text.setBackgroundColor(0x00000000);
		}
		final ImageItem item = dataList.get(position);
//		Bitmap bm = getBitmapFromUri(item.getImageUri());
		Bitmap bm = BitmapUtl.bytesToBitmap(item.getmContent(), null);
		holder.iv.setImageBitmap(bm);
		
		if (item.getIsSelected()) {
			// badge
		} else {
			// unbadge
		}

		holder.iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String path = dataList.get(position).getImagePath();
				// badge
				if ((BimpUtl.drr.size() + selectTotal) < 9) {
					item.setIsSelected(!item.getIsSelected());
					if (item.getIsSelected()) {
						holder.selected
								.setImageResource(R.drawable.icon_data_select);
						holder.text
								.setBackgroundResource(R.drawable.bgd_relatly_line);
						selectTotal++;
						if (textcallback != null) {
							textcallback.onListen(selectTotal);
						}
						map.put(path, path);

					} else if (!item.getIsSelected()) {
						holder.selected.setImageResource(-1);
						holder.text.setBackgroundColor(0x00000000);
						selectTotal--;
						if (textcallback != null) {
							textcallback.onListen(selectTotal);
						}
						map.remove(path);
					}
				} else if ((BimpUtl.drr.size() + selectTotal) >= 9) {
					if (item.getIsSelected() == true) {
						item.setIsSelected(false);
						holder.selected.setImageResource(-1);
						selectTotal--;
						map.remove(path);
					} else {
						Message message = Message.obtain(mHandler, 0);
						message.sendToTarget();
					}
				}
			}
		});

		return convertView;

	}

	public Map<String, String> getMap() {
		return map;
	}

	private Bitmap getBitmapFromUri(Uri uri) {
		try {
			// 读取uri所在的图片
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					context.getContentResolver(), uri);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void initSelectedTotal(){
		this.selectTotal = 0;
	}
}
