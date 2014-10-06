package com.fish.neihan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图片段子实体类
 * 
 * @author Squirrelfish
 * 
 */
public class ImageEntity extends TextEntity{
	private ImageUrlList largeImageUrlList;
	private ImageUrlList middleImageUrlList;

	public ImageUrlList getLargeImageUrlList() {
		return largeImageUrlList;
	}

	public ImageUrlList getMiddleImageUrlList() {
		return middleImageUrlList;
	}

	/**
	 * 解析出图片段子
	 * 
	 * @param item
	 * @throws JSONException
	 */
	public void parseJson(JSONObject item) throws JSONException {
		super.parseJson(item);
		try {
			JSONObject group = item.getJSONObject("group");

			JSONObject largeImage = group.optJSONObject("large_image");
			largeImageUrlList = new ImageUrlList();
			if (largeImage!=null) {
				largeImageUrlList.parseJson(largeImage);
			}
			

			JSONObject middleImage = group.optJSONObject("middle_image");
			middleImageUrlList = new ImageUrlList();
			if (middleImage!=null) {
				middleImageUrlList.parseJson(middleImage);
			}
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
