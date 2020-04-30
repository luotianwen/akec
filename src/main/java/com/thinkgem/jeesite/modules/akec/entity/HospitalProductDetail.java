/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 医院管理Entity
 * @author 医院管理
 * @version 2020-04-04
 */
public class HospitalProductDetail extends DataEntity<HospitalProductDetail> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// 是否入院
	private String type;		// 产品类型
	private String typename;		// 类型名称
	private Hospital h;		// 医院 父类
	
	public HospitalProductDetail() {
		super();
	}

	public HospitalProductDetail(String id){
		super(id);
	}

	public HospitalProductDetail(Hospital h){
		this.h = h;
	}

	@Length(min=1, max=1, message="是否入院长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=255, message="产品类型长度必须介于 1 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=255, message="类型名称长度必须介于 1 和 255 之间")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	@Length(min=1, max=32, message="医院长度必须介于 1 和 32 之间")
	public Hospital getH() {
		return h;
	}

	public void setH(Hospital h) {
		this.h = h;
	}
	
}