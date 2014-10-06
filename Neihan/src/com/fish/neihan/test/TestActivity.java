package com.fish.neihan.test;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.fish.neihan.entity.AdEntity;
import com.fish.neihan.entity.Comment;
import com.fish.neihan.entity.CommentList;
import com.fish.neihan.entity.EntityList;
import com.fish.neihan.entity.ImageEntity;
import com.fish.neihan.entity.ImageUrlList;
import com.fish.neihan.entity.TextEntity;

import android.os.Bundle;
import android.app.Activity;
import android.app.DownloadManager.Query;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 这个文件就是一个测试入口，所有的测试内容，都可以放在这里
 * 
 * @author Squirrelfish
 * 
 */
public class TestActivity extends Activity implements Listener<String>,OnClickListener {
	private Button refreshButton;
	/**
	 * 分类ID，1：文本，2：图片
	 */
	public static final int CATEGORY_TEXT = 1;
	public static final int CATEGORY_IMAGE = 2;
	private RequestQueue queue;
	private int itemCount = 30;
	private long lastTime = 0;
	
	private int offset = 0;
	private long groupId = 3034244609l;
	private boolean hasMore = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		refreshButton = (Button) findViewById(R.id.buttonrefresh);
		refreshButton.setOnClickListener(this);

		queue = Volley.newRequestQueue(this);
		
	}

	@Override
	public void onResponse(String arg0) {
		// TODO Auto-generated method stub
		try {
			JSONObject object = new JSONObject(arg0);
			Log.i("", "--->>>offset:" +offset);
			//解析获取到的评论列表
			CommentList commentList = new CommentList();
			//评论列表包含两组评论，热门评论和新鲜评论
			//都可能是空的
			commentList.parseJson(object);
			
			//表示评论列表是否还可以继续加载，是否还有数据
			hasMore = commentList.isHasMore();
			Log.i("", "--->>>hasMore:" +hasMore);
			//评论总数
			int totalNumber = commentList.getTotalNumber();
			//热门评论列表（可能为空，第一次offset为0时，可能有数据）
			List<Comment> topComments = commentList.getTopComments();
			//新鲜评论,可能有数据
			List<Comment> recentComments = commentList.getRecentComments();
			
			//TODO 直接把CommentList提交给ListView 的Adatper,这样可以进行是否还有内容的判断
			//利用Adapter更新数据
			
			//分页标识，要求服务器每次返回20条评论，通过hasMore 来进行判断是否还需要分离
			offset +=20;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	/**
	 * 列表网络获取回调部分
	 * 
	 * @param response
	 *            列表JSON数据字符串
	 */
	public void listonResponse(String response) {
		// TODO Auto-generated method stub
		try {
			JSONObject json = new JSONObject(response);
			Log.i("", "----->>json" + json.toString());
			if ("success".equals(json.getString("message"))) {
				// 成功

				// 获取根节点下面的data对象
				JSONObject object = json.getJSONObject("data");
				// 解析段子列表的完整数据
				EntityList entityList = new EntityList();
				// 这个方法是解析JSON的方法，其中包含的支持图片，文本，广告的解析
				entityList.parseJson(object);
				// 如果isHasMore 返回true，代表还可以更新一次数据
				if (entityList.isHasMore()) {
					// 获取更新时间标识
					lastTime = entityList.getMinTime();
					Log.i("", "-----lastTime>>>" + lastTime);
				} else {
					// 没有更新数据，提示
					Toast.makeText(TestActivity.this, entityList.getTip(),
							Toast.LENGTH_SHORT).show();
				}
				// TODO 把entityList 这个段子的数据集合体，传递给ListView之类的Adapter即可显示
			} else {
				// 失败
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonrefresh:
			if (hasMore) {
				ClientAPI.getComments(queue, groupId, offset, this);
			}else {
				Toast.makeText(this, "sorry,there is no data!", Toast.LENGTH_SHORT).show();
			}
			break;

		}
	}

	

}
