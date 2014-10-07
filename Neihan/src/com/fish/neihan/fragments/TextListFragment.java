package com.fish.neihan.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.fish.neihan.R;
import com.fish.neihan.adapters.EssayAdapter;
import com.fish.neihan.client.ClientAPI;
import com.fish.neihan.entity.EntityList;
import com.fish.neihan.entity.TextEntity;
import com.fish.neihan.test.TestActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.graphics.AvoidXfermode.Mode;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 段子列表 1.列表界面，第一次启动，并且数据为空自动加载数据， 2.只要列表没有数据，进入这个界面的时候，就尝试加载数据
 * 3.只要有数据，就不进行数据的加载 4.进入这个界面，并且有数据的情况下，尝试检查新信息的个数
 * 
 * @author Squirrelfish
 * 
 */
public class TextListFragment extends Fragment implements OnClickListener,
		OnScrollListener, OnRefreshListener2<ListView> {
	
	private static final String TAG="TextListFragment";

	private View quickTools;

	private View quickPublish;
	private View quickReview;
	private TextView textNofity;

	private EssayAdapter adapter;
	private List<TextEntity> entities;

	/**
	 * 分类ID，1：文本，2：图片
	 */
	public static final int CATEGORY_TEXT = 1;
	public static final int CATEGORY_IMAGE = 2;

	private RequestQueue queue;
	private int itemCount = 30;
	private long lastTime = 0;
	/**
	 * 请求的分类类型ID
	 */
	private int requestCategory = CATEGORY_TEXT;

	public TextListFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (queue == null) {
			queue = Volley.newRequestQueue(getActivity());
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			Log.i(TAG, "--->>>lastTime:"+lastTime);
			lastTime = savedInstanceState.getLong("lastTime");
		}

		View view = inflater.inflate(R.layout.fragment_textlist, container,
				false);
		// 获取标题控件，增加点击，进行 新消息悬浮框显示的功能
		View titleView = view.findViewById(R.id.textlist_title);
		titleView.setOnClickListener(this);

		// TODO 获取listview并且设置数据
		PullToRefreshListView refreshListView = (PullToRefreshListView) view
				.findViewById(R.id.textlist_listview);
		// 设置上拉与下拉的事件监听
		refreshListView.setOnRefreshListener(this);

		refreshListView
				.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		ListView listView = refreshListView.getRefreshableView();

		header = inflater.inflate(R.layout.textlist_header_tools, listView,
				false);
		quickPublish = header.findViewById(R.id.quick_tools_publish);
		quickPublish.setOnClickListener(this);
		quickReview = header.findViewById(R.id.quick_tools_review);
		quickReview.setOnClickListener(this);

		listView.addHeaderView(header);

		if (entities == null) {
			entities = new ArrayList<TextEntity>();
		}

		adapter = new EssayAdapter(getActivity(), entities);
		listView.setAdapter(adapter);

		listView.setOnScrollListener(this);

		// TODO 获取快速的工具条（发布和审核），用于列表滚动的显示和隐藏
		quickTools = view.findViewById(R.id.textlist_quick_tools);
		quickTools.setVisibility(View.GONE);
		// TODO 获取 新条目通知控件，每次有新数据要显示，过一段时间隐藏
		textNofity = (TextView) view.findViewById(R.id.textlist_new_notify);
		textNofity.setVisibility(View.GONE);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong("lastTime", lastTime);
	};

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		this.adapter = null;
		this.header = null;
		this.quickTools = null;
		this.textNofity = null;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				// TODO what==1代表有新消息提醒
				textNofity.setVisibility(View.INVISIBLE);
			}
		};
	};

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textlist_title:
			textNofity.setVisibility(View.VISIBLE);
			handler.sendEmptyMessageDelayed(1, 3000);
			break;

		default:
			break;
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///列表向下滚动，显示快速工具条////

	private int lastVisibleIndex = 0;

	private View header;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (quickTools != null) {
			if (firstVisibleItem > lastVisibleIndex) {
				// 向上滑动或者到达顶部
				quickTools.setVisibility(View.INVISIBLE);
			} else if (firstVisibleItem < lastVisibleIndex) {
				// 向下滑动
				quickTools.setVisibility(View.VISIBLE);
				if (header.getVisibility() == View.VISIBLE) {
					header.setVisibility(View.INVISIBLE);
				}
			}
			lastVisibleIndex = firstVisibleItem;
		}

	}

	/**
	 * 列表网络获取回调部分，这个方法，是在Volley联网响应返回的是否调用， 是在主线程执行的，因此可以直接更新UI
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

				}
				// TODO 把entityList 这个段子的数据集合体，传递给ListView之类的Adapter即可显示
				List<TextEntity> ets = entityList.getEntities();
				if (ets != null) {
					if (!ets.isEmpty()) {
						// 把ets中的内容按照迭代器的顺序添加，需要验证一下，
						entities.addAll(0, ets);
						// 手动添加
						// //把object添加到指定的location位置，原有location以及以后的的内容向后移动
						// // entities.add(location, object);
						// for (int i = ets.size()-1; i >=0; i--) {
						//
						// }
						adapter.notifyDataSetChanged();
					} else {
						// TODO 没有更多的数据了，需要提示一下.
						String tip = entityList.getTip();
						Log.i("", "---->>>tip" + tip);
					}
				} else {
					// TODO 没有获取到网络数据，可能是数据解析错误、网络错误，需要提示一下
				}

			} else {
				// 失败
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///列表下拉刷新与上拉加载
	/**
	 * 从上向下拉动列表，那么就要进行加载新数据的操作
	 */
	@Override
	public void onPullDownToRefresh(
			final PullToRefreshBase<ListView> refreshView) {
		// 下拉
		ClientAPI.getList(queue, requestCategory, itemCount, 0,
				new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						refreshView.onRefreshComplete();
						// TODO 加载新数据
						listonResponse(arg0);

					}
				});
	}

	/**
	 * 从下向上拉动，那么就要就行加载旧数据的操作
	 */

	@Override
	public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		// 上拉
		ClientAPI.getList(queue, requestCategory, itemCount, lastTime,
				new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						refreshView.onRefreshComplete();
						// TODO 加载新数据
						listonResponse(arg0);

					}
				});
	}
}
