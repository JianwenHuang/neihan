package com.fish.neihan.entity;

import org.json.JSONObject;

/**
 * 广告实体�?
 * @author Squirrelfish
 */
public class AdEntity {
	/**
	 * 类型�?广告
	 */
	private int type;
	private long displayTime;
	private long onlineTime;
	private String downloadUrl;
	private int displayImageHeight;
	private int displayImageWidth;
	private long adId;
	private String source;
	private String mPackage;
	private String title;
	private String openUrl;
	private int isAd;
	private String displayInfo;
	private String webUrl;
	private int displayType;
	private String buttonText;
	private String appleid;
	private String trackUrl;
	private String label;
	private String typeId;
	private long id;
	private String ipaUrl;
	private String displayImage;

	/**
	 * {"type": 5,"display_time": 1411878658,"online_time": 1411878658,"ad": {"display_image_height": 400,"ad_id": 3561092485,"display_image_width": 600,"source": "","package": "","title": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦�?,"open_url": "","download_url": "http://yihua.b0.upaiyun.com/neihan.apk","is_ad": 1,"display_info": "霜霜和阿伟都爱玩的游戏，还有iphone6等你拿哦�?,"web_url": "http://yihua.b0.upaiyun.com/neihan.apk","display_type": 3,"button_text": "立即下载","appleid": "","track_url": "","label": "推广","type": "app","id": 3561092485,"ipa_url": "","display_image": "http://p2.pstatp.com/large/1362/1075506622"}},
	 */
	public void parseJson(JSONObject jsonObject) throws JSONException {
		throw new UnsupportedOperationException();
	}

	public int getType() {
		return this.type;
	}

	public long getDisplayTime() {
		return this.displayTime;
	}

	public long getOnlineTime() {
		return this.onlineTime;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public int getDisplayImageHeight() {
		return this.displayImageHeight;
	}

	public int getDisplayImageWidth() {
		return this.displayImageWidth;
	}

	public long getAdId() {
		return this.adId;
	}

	public String getSource() {
		return this.source;
	}

	public String getMPackage() {
		return this.mPackage;
	}

	public String getTitle() {
		return this.title;
	}

	public String getOpenUrl() {
		return this.openUrl;
	}

	public int getIsAd() {
		return this.isAd;
	}

	public String getDisplayInfo() {
		return this.displayInfo;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public int getDisplayType() {
		return this.displayType;
	}

	public String getButtonText() {
		return this.buttonText;
	}

	public String getAppleid() {
		return this.appleid;
	}

	public String getTrackUrl() {
		return this.trackUrl;
	}

	public String getLabel() {
		return this.label;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public long getId() {
		return this.id;
	}

	public String getIpaUrl() {
		return this.ipaUrl;
	}

	public String getDisplayImage() {
		return this.displayImage;
	}
}