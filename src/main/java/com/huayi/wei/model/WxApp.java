package com.huayi.wei.model;

import com.huayi.common.base.BaseModel;

import java.util.Date;

public class WxApp extends BaseModel {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公众号id
	 */
	private String appId;

	/**
	 * 公众号名称
	 */
	private String name;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}