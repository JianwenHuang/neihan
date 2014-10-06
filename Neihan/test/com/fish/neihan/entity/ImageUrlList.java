package com.fish.neihan.entity;

import java.util.List;
import org.json.JSONObject;

/**
 * 图片网址列表
 * 
 * @author Squirrelfish
 */
public class ImageUrlList {
	private java.util.List<String> largeImageUrls;
	private String uri;
	private int width;
	private int height;

	public void parseJson(JSONObject jsonObject) throws JSONException {
		throw new UnsupportedOperationException();
	}

	private java.util.List<String> parseImageUrlList(JSONObject largeImage) throws JSONException {
		throw new UnsupportedOperationException();
	}

	public java.util.List<String> getLargeImageUrls() {
		return this.largeImageUrls;
	}

	public String getUri() {
		return this.uri;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}