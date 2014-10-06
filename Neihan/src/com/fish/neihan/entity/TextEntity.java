package com.fish.neihan.entity;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 文本段子实体类
 * @author Squirrelfish
 *
 */
public class TextEntity{
	protected int type; // 类型，1：文本，5：广告
	protected long createTime; // 创建时间
	/**
	 * 上线时间
	 */
	protected long onlineTime;
	/**
	 * 显示时间
	 */
	protected long displayTime;

	protected int commentCount; // 评论数

	/**
	 * 状态，其中的可选值3 需要分析是什么类型
	 */
	protected int status;
	/**
	 * TODO 需要去了解其含义
	 */
	protected int userDigg;

	/**
	 * TODO digg的个数
	 */
	protected int diggCount;
	/**
	 * 段子ID,访问详情和评论时，作为借口的参数
	 */
	protected long groupId;
	/**
	 * 内容分类类型，1，文本，2图片
	 */
	protected int categoryId;
	
	
	protected int favoriteCount; // 赞的个数
	protected int userBury; // 当前用户是否踩了，0没有，1可能代表踩了
	protected int userFavorite; // 当前用户是否赞了，0没有，1可能代表踩了
	protected int buryCount; // 踩的个数
	protected String shareUrl; // 分享网址，用于第三方分享
	protected int label; // TODO 分析这个字段的含义
	protected String content; // 文本段子内容（完整内容）
	
	
	/**
	 * 状态描述信息<br/>
	 * 可选值：<br/>
	 * <ul>
	 * <li>已发表到热门列表</li>
	 * </ul>
	 */
	protected String statusDesc;
	protected int hasComments; // 当前用户是否评论
	/**
	 * TODO 需要分析
	 */
	protected int goDetailCount;
	
	
	/**
	 * TODO 需要去了解其含义,在两处地方出现<br/>
	 * 1.获取列表接口level = 6<br/>
	 * 2.文本段子实体中level=4
	 */
	protected int level;
	/**
	 * 用户是否repin,<br/>
	 * 0:没有
	 */
	protected int userRepin;
	/**
	 * TODO 分析含义
	 */
	protected int repinCount;
	/**
	 * 是否含有热门评论，1：有
	 */
	protected int hasHotComments;
	

	// TODO 需要去分析comments 这个json数组中的内容是什么？
	

	protected UserEntity user;
	public void parseJson(JSONObject jsonObject) throws JSONException{
		if(jsonObject!=null){
			type = jsonObject.getInt("type");
			onlineTime = jsonObject.getLong("online_time");
			displayTime = jsonObject.getLong("display_time");
			JSONObject group = jsonObject.getJSONObject("group");
			createTime = group.getLong("create_time");
			favoriteCount = group.getInt("favorite_count");
			userBury = group.getInt("user_bury");
			userFavorite = group.getInt("user_favorite");
			buryCount = group.getInt("bury_count");
			shareUrl  = group.getString("share_url");
			label = group.optInt("label",0);
			content = group.getString("content");
			commentCount = group.getInt("comment_count");
			status = group.getInt("status");
			hasComments = group.getInt("has_comments");
			goDetailCount = group.getInt("go_detail_count");
			statusDesc = group.getString("status_desc");
			user = new UserEntity();
			user.parseJson(group.getJSONObject("user"));
			userDigg = group.getInt("user_digg");
			groupId = group.getLong("group_id");
			level = group.getInt("level");
			repinCount =group.getInt("repin_count");
			diggCount = group.getInt("digg_count");
			hasHotComments = group.optInt("has_hot_comments",0);
			userRepin = group.getInt("user_repin");
			categoryId = group.getInt("category_id");
			
		}
	}

	public int getType() {
		return type;
	}

	public long getCreateTime() {
		return createTime;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public int getUserBury() {
		return userBury;
	}

	public int getUserFavorite() {
		return userFavorite;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public int getLabel() {
		return label;
	}

	public String getContent() {
		return content;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public int getHasComments() {
		return hasComments;
	}

	public int getGoDetailCount() {
		return goDetailCount;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public long getGroupId() {
		return groupId;
	}

	public int getLevel() {
		return level;
	}

	public int getUserRepin() {
		return userRepin;
	}

	public int getRepinCount() {
		return repinCount;
	}

	public int getHasHotComments() {
		return hasHotComments;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public UserEntity getUser() {
		return user;
	}

}
