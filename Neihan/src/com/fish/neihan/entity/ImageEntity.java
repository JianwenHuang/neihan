package com.fish.neihan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图片段子实体类
 * 
 * @author Squirrelfish
 * 
 */
public class ImageEntity {
	private int type;
	private int commentCount;
	private long groupid;
	private String content;
	private ImageUrlList largeImageUrlList;
	private ImageUrlList middleImageUrlList;

	public int getType() {
		return type;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public long getGroupid() {
		return groupid;
	}

	public String getContent() {
		return content;
	}

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
		try {
			type = item.getInt("type");

			JSONObject group = item.getJSONObject("group");

			commentCount = group.getInt("comment_count");

			JSONObject largeImage = group.getJSONObject("large_image");
			largeImageUrlList = new ImageUrlList();
			largeImageUrlList.parseJson(largeImage);

			JSONObject middleImage = group.getJSONObject("middle_image");
			middleImageUrlList = new ImageUrlList();
			middleImageUrlList.parseJson(middleImage);

			groupid = group.getLong("group_id");
			content = group.getString("content");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
