package com.fish.neihan.adapters;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

import com.fish.neihan.R;
import com.fish.neihan.entity.TextEntity;
import com.fish.neihan.entity.UserEntity;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EssayAdapter extends BaseAdapter {
	private Context context;
	private List<TextEntity> entities;
	private LayoutInflater inflater;

	public EssayAdapter(Context context, List<TextEntity> entities) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.entities = entities;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return entities.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(R.layout.item_essay, parent, false);

		}
		if (view != null) {
			ViewHolder holder = (ViewHolder) view.getTag();
			if (holder == null) {
				holder = new ViewHolder();
				holder.btnGifPlay = (TextView) view
						.findViewById(R.id.btnGifPlay);
				holder.btnShare = (ImageButton) view
						.findViewById(R.id.item_share);
				holder.chbBuryCount = (CheckBox) view
						.findViewById(R.id.item_bury_count);
				holder.chbDiggCount = (CheckBox) view
						.findViewById(R.id.item_digg_count);
				holder.gifImageView = (GifImageView) view
						.findViewById(R.id.gifView);
				holder.imgProfileImage = (ImageView) view
						.findViewById(R.id.item_profile_image);
				holder.pbDownloadProgressBar = (ProgressBar) view
						.findViewById(R.id.item_image_download_progress);
				holder.txtCommentCount = (TextView) view
						.findViewById(R.id.item_comment_count);
				holder.txtContent = (TextView) view
						.findViewById(R.id.item_content);
				holder.txtProfileNick = (TextView) view
						.findViewById(R.id.item_profile_nick);
				view.setTag(holder);
			}

			TextEntity entity = entities.get(position);
			// 1.先设置文本内容的数据
			UserEntity user = entity.getUser();
			String nick = "";
			if (user != null) {
				nick = user.getName();
			}
			holder.txtProfileNick.setText(nick);

			String content = "";
			content = entity.getContent();
			holder.txtContent.setText(content);

			int diggCount = entity.getDiggCount();
			holder.chbDiggCount.setText(Integer.toString(diggCount));

			int userDigg = entity.getUserDigg(); // 当前用户是否赞过,1赞过，0没赞过
			// 如果userDigg 是1的话，代表已经赞过，那么chbDiggCount 必须禁用 所以用！=1
			holder.chbDiggCount.setEnabled(userDigg != 1);

			int buryCount = entity.getBuryCount();
			holder.chbBuryCount.setText(Integer.toString(buryCount));

			int userBury = entity.getUserBury();// 当前用户是否踩过,1踩过，0没踩过
			// 如果userBury 是1的话，代表已经踩过，那么chbBuryCount 必须禁用 ,所以用！=1
			holder.chbBuryCount.setEnabled(userBury != 1);

			int commentCount = entity.getCommentCount();
			holder.txtCommentCount.setText(Integer.toString(commentCount));

			// 2.设置图片数据
			// TODO 需要加载各种图片数据
		}

		return view;
	}

	private static class ViewHolder {
		/**
		 * 用户头像
		 */
		public ImageView imgProfileImage;
		/**
		 * 昵称
		 */
		public TextView txtProfileNick;
		/**
		 * 文本内容
		 */
		public TextView txtContent;
		/**
		 * 下载进度
		 */
		public ProgressBar pbDownloadProgressBar;
		/**
		 * 图片显示
		 */
		public GifImageView gifImageView;
		/**
		 * 图片显示部分按钮，开启Gif播放
		 */
		public TextView btnGifPlay;
		/**
		 * 赞，包含个数，如果已经赞过了，那么就禁用这个控件
		 */
		public CheckBox chbDiggCount;
		/**
		 * 踩，包含个数，如果已经踩过了，那么就禁用这个控件
		 */
		public CheckBox chbBuryCount;
		/**
		 * 评论，包含个数，如果已经评论过了，那么就禁用这个控件
		 */
		public TextView txtCommentCount;
		/*
		 * 分享
		 */
		public ImageButton btnShare;
	}

}
