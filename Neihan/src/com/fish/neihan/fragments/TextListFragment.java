package com.fish.neihan.fragments;

import java.util.ArrayList;
import java.util.List;

import com.fish.neihan.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 段子列表
 * 
 * @author Squirrelfish
 * 
 */
public class TextListFragment extends Fragment implements OnClickListener,
		OnScrollListener, OnRefreshListener2<ListView> {

	private View quickTools;

	private View quickPublish;
	private View quickReview;
	private TextView textNofity;

	public TextListFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

		List<String> strings = new ArrayList<String>();
		strings.add("JAVA");
		strings.add("C");
		strings.add("C++");
		strings.add("PHP");
		strings.add("SLDK");
		strings.add("SDFS");
		strings.add("SDF");
		strings.add("WERV");
		strings.add("LSK");
		strings.add("ABC");
		strings.add("DEF");
		strings.add("GSA");
		strings.add("SDFS");
		strings.add("123");
		strings.add("456");
		strings.add("789");
		strings.add("321");
		strings.add("546");
		strings.add("122");
		strings.add("519");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(),
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				strings);

		header = inflater.inflate(R.layout.textlist_header_tools,
				listView, false);
		quickPublish = header.findViewById(R.id.quick_tools_publish);
		quickPublish.setOnClickListener(this);
		quickReview = header.findViewById(R.id.quick_tools_review);
		quickReview.setOnClickListener(this);

		listView.addHeaderView(header);

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
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
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

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////列表下拉刷新与上拉加载
	/**
	 * 从上向下拉动列表，那么就要进行加载新数据的操作
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// 下拉
		
	}
	/**
	 * 从下向上拉动，那么就要就行加载旧数据的操作
	 */

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		// 上拉
	}
}
