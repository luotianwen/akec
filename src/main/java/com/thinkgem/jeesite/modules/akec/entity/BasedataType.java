/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 常用参数Entity
 * @author 常用参数
 * @version 2020-02-27
 */
public class BasedataType extends DataEntity<BasedataType> {
	
	private static final long serialVersionUID = 1L;
	private String baseTypeCode;		// 编码
	private String baseTypeName;		// 名称
	
	public BasedataType() {
		super();
	}

	public BasedataType(String id){
		super(id);
	}

	@Length(min=0, max=30, message="编码长度必须介于 0 和 30 之间")
	public String getBaseTypeCode() {
		return baseTypeCode;
	}

	public void setBaseTypeCode(String baseTypeCode) {
		this.baseTypeCode = baseTypeCode;
	}
	
	@Length(min=0, max=20, message="名称长度必须介于 0 和 20 之间")
	public String getBaseTypeName() {
		return baseTypeName;
	}

	public void setBaseTypeName(String baseTypeName) {
		this.baseTypeName = baseTypeName;
	}
	
}