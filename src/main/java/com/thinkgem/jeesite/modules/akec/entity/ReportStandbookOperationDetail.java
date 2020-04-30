/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 手术报数Entity
 * @author 手术报数
 * @version 2020-03-27
 */
public class ReportStandbookOperationDetail extends DataEntity<ReportStandbookOperationDetail> {
	
	private static final long serialVersionUID = 1L;
	private ReportStandbookOperation operate;		// 外键参照报台主表 父类
	private String doctor;		// 医生
	private String akCount;		// 爱康总数
	private String type;		// 类型
	private String typename;		// 类型名称
	
	public ReportStandbookOperationDetail() {
		super();
	}

	public ReportStandbookOperationDetail(String id){
		super(id);
	}

	public ReportStandbookOperationDetail(ReportStandbookOperation operate){
		this.operate = operate;
	}

	@Length(min=0, max=32, message="外键参照报台主表长度必须介于 0 和 32 之间")
	public ReportStandbookOperation getOperate() {
		return operate;
	}

	public void setOperate(ReportStandbookOperation operate) {
		this.operate = operate;
	}
	
	@Length(min=0, max=255, message="医生长度必须介于 0 和 255 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	public String getAkCount() {
		return akCount;
	}

	public void setAkCount(String akCount) {
		this.akCount = akCount;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="类型名称长度必须介于 0 和 255 之间")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}