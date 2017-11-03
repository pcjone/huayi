package com.huayi.wei.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.huayi.common.base.BaseModel;
/**
 * 
* @ClassName: WeiUser 
* @Description: 微信用户
* @author panlei
* @date 2017年8月8日 下午12:28:03 
*
 */
public class WeiUser extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;
	
	private String openId;
	private String unionId;
	private String nickname;
	private String sex;
	private Integer sexId;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date subscribeTime;
	private Integer groupId;
	private Boolean subscribe;
	private Double latitude;//纬度
	private Double longitude;//经度
	private Double precision;//精度
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getSexId() {
		return sexId;
	}
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Date getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public Boolean getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getPrecision() {
		return precision;
	}
	public void setPrecision(Double precision) {
		this.precision = precision;
	}
	
}
