package com.fish.neihan.entity;

import org.json.JSONObject;

/**
 * 文本段子实体�?
 * @author Squirrelfish
 */
public class TextEntity extends DuanziEntity {
	private int type;
	/**
	 * 类型�?：文本，5：广�?
	 */
	private long createTime;
	/**
	 * 创建时间
	 */
	private int favoriteCount;
	/**
	 * 赞的个数
	 */
	private int userBury;
	/**
	 * 当前用户是否踩了�?没有�?可能代表踩了
	 */
	private int userFavorite;
	/**
	 * 当前用户是否赞了�?没有�?可能代表踩了
	 */
	private int buryCount;
	/**
	 * 踩的个数
	 */
	private String shareUrl;
	/**
	 * 分享网址，用于第三方分享
	 */
	private int label;
	/**
	 * TODO 分析这个字段的含�?
	 */
	private String content;
	/**
	 * 文本段子内容（完整内容）
	 */
	private int commentCount;
	/**
	 * 状�?，其中的可�?�? �?��分析是什么类�?
	 */
	private int status;
	/**
	 * 状�?描述信息<br/>
	 * 可�?值：<br/>
	 * <ul>
	 * <li>已发表到热门列表</li>
	 * </ul>
	 */
	private String statusDesc;
	private int hasComments;
	/**
	 * TODO �?��分析
	 */
	private int goDetailCount;
	/**
	 * TODO �?��去了解其含义
	 */
	private int userDigg;
	/**
	 * TODO digg的个�?
	 */
	private int diggCount;
	/**
	 * 段子ID,访问详情和评论时，作为�?口的参数
	 */
	private long groupId;
	/**
	 * TODO �?��去了解其含义,在两处地方出�?br/>
	 * 1.获取列表接口level = 6<br/>
	 * 2.文本段子实体中level=4
	 */
	private int level;
	/**
	 * 用户是否repin,<br/>0:没有
	 */
	private int userRepin;
	/**
	 * TODO 分析含义
	 */
	private int repinCount;
	/**
	 * 是否含有热门评论�?：有
	 */
	private int hasHotComments;
	/**
	 * 内容分类类型�?，文本，2图片
	 */
	private int categoryId;
	/**
	 * 上线时间
	 */
	private long onlineTime;
	/**
	 * 显示时间
	 */
	private long displayTime;
	private UserEntity user;

	public void parseJson(JSONObject jsonObject) throws JSONException {
		throw new UnsupportedOperationException();
	}

	public int getType() {
		return this.type;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	public int getFavoriteCount() {
		return this.favoriteCount;
	}

	public int getUserBury() {
		return this.userBury;
	}

	public int getUserFavorite() {
		return this.userFavorite;
	}

	public int getBuryCount() {
		return this.buryCount;
	}

	public String getShareUrl() {
		return this.shareUrl;
	}

	public int getLabel() {
		return this.label;
	}

	public String getContent() {
		return this.content;
	}

	public int getCommentCount() {
		return this.commentCount;
	}

	public int getStatus() {
		return this.status;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public int getHasComments() {
		return this.hasComments;
	}

	public int getGoDetailCount() {
		return this.goDetailCount;
	}

	public int getUserDigg() {
		return this.userDigg;
	}

	public int getDiggCount() {
		return this.diggCount;
	}

	public long getGroupId() {
		return this.groupId;
	}

	public int getLevel() {
		return this.level;
	}

	public int getUserRepin() {
		return this.userRepin;
	}

	public int getRepinCount() {
		return this.repinCount;
	}

	public int getHasHotComments() {
		return this.hasHotComments;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public long getOnlineTime() {
		return this.onlineTime;
	}

	public long getDisplayTime() {
		return this.displayTime;
	}

	public UserEntity getUser() {
		return this.user;
	}
}