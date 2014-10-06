package com.fish.neihan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户实体类
 * @author Squirrelfish
 *
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
	 * 用户是否加V了
	 */
	private boolean userVerified;
//  "user": {
	//  "avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454",
	//  "user_id": 3080520868,
	//  "name": "请叫我梓安哥",
	//  "user_verified": false
//	},
	
	public void parseJson(JSONObject jsonObject) throws JSONException{
		if(jsonObject!=null){
			this.avatarUrl = jsonObject.getString("avatar_url");
			this.userId = jsonObject.getLong("user_id");
			name = jsonObject.getString("name");
			userVerified = jsonObject.getBoolean("user_verified");
		}
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public boolean isUserVerified() {
		return userVerified;
	}
	
}
