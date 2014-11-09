package com.hand.hrmexp.model;

import java.io.Serializable;

import android.net.Uri;



public class ImageItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237591079111920268L;
	private String imageId;
	private String thumbnailPath;
	private String imagePath;
//	private Uri imageUri;
	private byte[] mContent;
	private Boolean isSelected = false;
	
	public ImageItem(String imageId,String thumbnailPath,String imagePath,byte[] mContent2){
		this.imageId = imageId;
		this.thumbnailPath = thumbnailPath;
		this.imagePath = imagePath;
//		this.imageUri = imageUri;
		this.mContent = mContent2;
		this.isSelected = false;
	}
	
	public String getImageId(){
		return imageId;
		
	}
	
	public String getThumbnailPath(){
		return thumbnailPath;
	}
	
	public String getImagePath(){
		return imagePath;
		
	}
	
//	public Uri getImageUri(){
//		return imageUri;
//	}
		
	public Boolean getIsSelected() {
		return isSelected;
		
	}
	
	public byte[] getmContent(){
		return mContent;
	}

	public void setIsSelected(boolean flag) {
		this.isSelected = flag;
	}
}
