package com.fish.neihan.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * 评论接口返回的data ：{} 数据部分的实体定义<br/>
 * 包含了top_comments 和 recent_comments 两个数组<br>
 * JSON 格式如下：<br>
 * 
 * <pre>
 * "data": {
 *         "recent_comments": [], 
 *         "top_comments": [ ]
 *     }
 * </pre>
 * 
 * @author Squirrelfish
 * 
 */
public class CommentList {
	private List<Comment> topComments;
	private List<Comment> recentComments;

	private long groupId;
	private boolean hasMore;
	private String message;
	private int totalNumber;
	
	public long getGroupId() {
		return groupId;
	}


	public boolean isHasMore() {
		return hasMore;
	}


	public String getMessage() {
		return message;
	}


	public int getTotalNumber() {
		return totalNumber;
	}


	public List<Comment> getTopComments() {
		return topComments;
	}


	public List<Comment> getRecentComments() {
		return recentComments;
	}


	public void parseJson(JSONObject json) throws JSONException {
		if (json != null) {
			hasMore = json.getBoolean("has_more");
			message = json.getString("message");
			groupId = json.getLong("group_id");
			totalNumber = json.getInt("total_number");
	
			JSONObject data =  json.getJSONObject("data");
			JSONArray recentArray = data.optJSONArray("recent_comments");
			if (recentArray != null) {
				recentComments = new ArrayList<Comment>();
				if (recentArray.length() > 0) {
					for (int i = 0; i < recentArray.length(); i++) {
						JSONObject object = recentArray.getJSONObject(i);
						Comment comment = new Comment();
						comment.parseJson(object);
						recentComments.add(comment);
					}
				}

			}
			JSONArray topArray = data.optJSONArray("top_comments");
			if (topArray != null) {
				topComments = new ArrayList<Comment>();
				if (topArray.length() > 0) {
					for (int i = 0; i < topArray.length(); i++) {
						JSONObject object = topArray.getJSONObject(i);
						Comment comment = new Comment();
						comment.parseJson(object);
						topComments.add(comment);
					}
				}

			}
			
		}
	}
	
}
