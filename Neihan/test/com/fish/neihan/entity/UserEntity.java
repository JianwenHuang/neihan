package com.fish.neihan.entity;

import org.json.JSONObject;

/**
 * 用户实体�?
 * @author Squirrelfish
 */
public class UserEntity {
	/**
	 * 图像网址
	 */
	private String avatarUrl;
	/**
	 * 用户Id
	 */
	private long userId;
	/**
	 * 用户昵称
	 */
	private String name;
	/**
	 * 用户是否加V�?
	 */
	private boolean userVerified;
	public ImageEntity unnamed_ImageEntity_;

	/**
	 * "user": {"avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454","user_id": 3080520868,"name": "请叫我梓安哥","user_verified": false},
	 */
	public void parseJson(JSONObject jsonObject) throws JSONException {
		throw new UnsupportedOperationException();
	}

	public boolean isUserVerified() {
		return this.userVerified;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public long getUserId() {
		return this.userId;
	}

	public String getName() {
		return this.name;
	}
}