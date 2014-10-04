package com.fish.neihan.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.fish.neihan.R;
import com.fish.neihan.client.ClientAPI;
import com.fish.neihan.entity.ImageEntity;
import com.fish.neihan.entity.ImageUrlList;

import android.os.Bundle;
import android.app.Activity;
import android.app.DownloadManager.Query;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

/**
 * 这个文件就是一个测试入口，所有的测试内容，都可以放在这里
 * 
 * @author Squirrelfish
 * 
 */
public class TestActivity extends Activity implements Listener<String> {
	/**
	 * 分类ID，1：文本，2：图片
	 */
	public static final int CATEGORY_TEXT = 1;
	public static final int CATEGORY_IMAGE = 2;
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		int itemCount = 30;

		queue = Volley.newRequestQueue(this);

		ClientAPI.getList(queue, CATEGORY_TEXT, itemCount, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public void onResponse(String response) {
		// TODO Auto-generated method stub
		try {
			JSONObject json = new JSONObject(response);
			response = json.toString(4);
			Log.i("TestActivity", "-----List:" + response.toString());
			//获取根节点下面的data对象
			JSONObject object =  json.getJSONObject("data");
			//从data对象中，获取名称为data的数组，它代表的是段子列表
			JSONArray data =object.getJSONArray("data");
			if(data.length()>0){
				//遍历数组中的每一条图片段子信息
				for (int i = 0; i < data.length(); i++) {
					JSONObject item = data.getJSONObject(i);
					ImageEntity imageEntity  = new ImageEntity();
					imageEntity.parseJson(item);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
