package com.handexp.utl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtl {
	
	public static Bitmap bytesToBitmap ( byte[] bytes , BitmapFactory.Options opts )
	{
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}
	
}
