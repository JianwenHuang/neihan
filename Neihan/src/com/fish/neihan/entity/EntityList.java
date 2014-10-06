package com.fish.neihan.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

/**
 * 请求列表
 * 
 * @author Squirrelfish
 * 
 */
public class EntityList {
	private boolean hasMore;
	private long minTime;
	private String tip;

	private long maxTime;

	private List<TextEntity> entities; // 端子列表

	/*
	 * "data": { "has_more": true, "min_time": 1411887357, "tip": "50条新内容",
	 * "data": [], "max_time": 1411872657 }
	 */
	public void parseJson(JSONObject json) throws JSONException {
		if (json != null) {
			hasMore = json.getBoolean("has_more");
			tip = json.getString("tip");
			if(hasMore){
				minTime = json.getLong("min_time");
			}
			
			maxTime = json.optLong("max_time");
			// 从data对象中，获取名称为data的数组，它代表的是段子列表
			JSONArray data = json.getJSONArray("data");
			if (data.length() > 0) {

				entities = new ArrayList<TextEntity>();
				// 遍历数组中的每一条段子信息
				for (int i = 0; i < data.length(); i++) {
					JSONObject item = data.getJSONObject(i);
					int type = item.getInt("type");// 获取类型，1段子，5广告
					if (type == 5) {
						// TODO 处理广告内容

						AdEntity entity = new AdEntity();
						entity.parseJson(item);
						Log.i("TestActivity", "---->>Received group id:"
								+ entity.getDownloadUrl());

					} else if (type == 1) {
						// TODO 处理段子类型
						JSONObject group = item.getJSONObject("group");
						int cid = group.getInt("category_id");
						TextEntity entity = null;
						if (cid == 1) {
							// TODO 解析文本段子
							entity = new TextEntity();

						} else if (cid == 2) {
							// TODO 解析图片段子
							entity = new ImageEntity();
						}
						entity.parseJson(item);
						// 添加到列表中
						entities.add(entity);

						Log.i("TestActivity", "----->>>Received group id:"
								+ entity.getGroupId());
					}
				}
			}
		}
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public long getMinTime() {
		return minTime;
	}

	public String getTip() {
		return tip;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public List<TextEntity> getEntities() {
		return entities;
	}
}
