package com.fish.neihan.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图片网址列表
 * 
 * @author Squirrelfish
 * 
 */
public class ImageUrlList {
	private List<String> largeImageUrls;
	

	private String uri;
	private int width;
	private int height;
	
	public List<String> getLargeImageUrls() {
		return largeImageUrls;
	}

	public String getUri() {
		return uri;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void parseJson(JSONObject jsonObject) {
		try {
			largeImageUrls = parseImageUrlList(jsonObject);
			uri = jsonObject.getString("uri");
			width = jsonObject.getInt("width");
			height = jsonObject.getInt("height");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private List<String> parseImageUrlList(JSONObject largeImage)
			throws JSONException {
		List<String> largeImageUrls = new ArrayList<String>();

		JSONArray urls = largeImage.getJSONArray("url_list");

		for (int j = 0; j < urls.length(); j++) {
			JSONObject uobj = urls.getJSONObject(j);
			String url = uobj.getString("url");
			largeImageUrls.add(url);
		}
		return largeImageUrls;
	}
}
