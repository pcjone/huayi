package com.huayi.wei.model;

import com.huayi.common.base.BaseModel;

import java.util.Date;

public class WxMenu extends BaseModel {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公众号appId
	 */
	private String appId;

	/**
	 * 父menuId
	 */

	private Long parentId;

	/**
	 * 菜单名称
	 */
	private String text;

	/**
	 * 关键字
	 */
	private String keyValue;

	/**
	 * 链接
	 */
	private String link;

	/**
	 * 菜单类型
	 */
	private String type;

	/**
	 * 排序
	 */

	private Long sort;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

}