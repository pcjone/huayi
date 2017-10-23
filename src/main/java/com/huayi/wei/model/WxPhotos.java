package com.huayi.wei.model;

import com.huayi.common.base.BaseModel;

import java.util.Date;

public class WxPhotos extends BaseModel{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	private String path;	
	    
	
	    
	/**
	*
	*/
	private String tableName;	
	    
	
	    
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	

}