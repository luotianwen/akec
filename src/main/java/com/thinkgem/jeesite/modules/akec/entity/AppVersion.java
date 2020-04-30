/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * App版本管理Entity
 * @author App版本管理
 * @version 2020-04-12
 */
public class AppVersion extends DataEntity<AppVersion> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private String name;		// 名称
	private String androidUrl;		// 安卓下载地址
	private String vdesc;		// 描述
	private String isCurrent;		// 是否最新
	private Date updateTime;		// 更新时间
	private String versionNum;		// 版本号
	private String iosUrl;		// ios下载地址
	
	public AppVersion() {
		super();
	}

	public AppVersion(String id){
		super(id);
	}

	@Length(min=0, max=10, message="编码长度必须介于 0 和 10 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=40, message="名称长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="安卓下载地址长度必须介于 0 和 255 之间")
	public String getAndroidUrl() {
		return androidUrl;
	}

	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}
	
	@Length(min=0, max=100, message="描述长度必须介于 0 和 100 之间")
	public String getVdesc() {
		return vdesc;
	}

	public void setVdesc(String vdesc) {
		this.vdesc = vdesc;
	}
	
	@Length(min=0, max=1, message="是否最新长度必须介于 0 和 1 之间")
	public String getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(String isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	
	@Length(min=0, max=255, message="ios下载地址长度必须介于 0 和 255 之间")
	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}
	
}