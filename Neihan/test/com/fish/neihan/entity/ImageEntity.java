package com.fish.neihan.entity;

import org.json.JSONObject;

/**
 * 图片段子实体�?
 * 
 * @author Squirrelfish
 */
public class ImageEntity extends DuanziEntity {
	private int type;
	private int commentCount;
	private long groupid;
	private String content;
	private ImageUrlList largeImageUrlList;
	private ImageUrlList middleImageUrlList;
	private UserEntity user;
	public UserEntity unnamed_UserEntity_;

	/**
	 * 解析出图片段�?
	 * @return @throws JSONException
	 */
	public void parseJson(JSONObject item) throws JSONException {
		throw new UnsupportedOperationException();
	}

	public int getType() {
		return this.type;
	}

	public int getCommentCount() {
		return this.commentCount;
	}

	public long getGroupid() {
		return this.groupid;
	}

	public String getContent() {
		return this.content;
	}

	public ImageUrlList getLargeImageUrlList() {
		return this.largeImageUrlList;
	}

	public ImageUrlList getMiddleImageUrlList() {
		return this.middleImageUrlList;
	}
}