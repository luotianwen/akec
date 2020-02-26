/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * APP用户管理Entity
 * @author APP用户管理
 * @version 2020-02-27
 */
public class AppUser extends DataEntity<AppUser> {
	
	private static final long serialVersionUID = 1L;
	private String account;		// 账号名
	private String name;		// 用户名
	private String pass;		// 密码
	private String dealerId;		// 经销商
	private String registerDealerName;		// 注册所属单位名字
	private String dealerName;		// 经销商名字
	private String baseReportId;		// 系统参数表
	private String auditStatus;		// 状态
	private String note;		// 备注
	private Date auditDate;		// 审核时间
	private String adminName;		// 审核人
	private String createType;		// 创建类型
	private String inputFlag;		// 允许输入
	
	public AppUser() {
		super();
	}

	public AppUser(String id){
		super(id);
	}

	@Length(min=0, max=20, message="账号名长度必须介于 0 和 20 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=20, message="用户名长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=40, message="密码长度必须介于 0 和 40 之间")
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Length(min=0, max=32, message="经销商长度必须介于 0 和 32 之间")
	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
	@Length(min=0, max=80, message="注册所属单位名字长度必须介于 0 和 80 之间")
	public String getRegisterDealerName() {
		return registerDealerName;
	}

	public void setRegisterDealerName(String registerDealerName) {
		this.registerDealerName = registerDealerName;
	}
	
	@Length(min=0, max=40, message="经销商名字长度必须介于 0 和 40 之间")
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	@Length(min=0, max=32, message="系统参数表长度必须介于 0 和 32 之间")
	public String getBaseReportId() {
		return baseReportId;
	}

	public void setBaseReportId(String baseReportId) {
		this.baseReportId = baseReportId;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Length(min=0, max=20, message="审核人长度必须介于 0 和 20 之间")
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Length(min=0, max=1, message="创建类型长度必须介于 0 和 1 之间")
	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}
	
	@Length(min=0, max=1, message="允许输入长度必须介于 0 和 1 之间")
	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}
	
}