/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 常用参数Entity
 * @author 常用参数
 * @version 2020-03-10
 */
public class Basedata extends DataEntity<Basedata> {
	
	private static final long serialVersionUID = 1L;
	private BasedataType base;		// 外键 父类
	private String paramCode;		// 参数编码
	private String paramName;		// 参数名称
	private String status;		// 状态
	private String seqno;		// 序号
	
	public Basedata() {
		super();
	}

	public Basedata(String id){
		super(id);
	}

	public Basedata(BasedataType base){
		this.base = base;
	}

	@Length(min=1, max=32, message="外键长度必须介于 1 和 32 之间")
	public BasedataType getBase() {
		return base;
	}

	public void setBase(BasedataType base) {
		this.base = base;
	}
	
	@Length(min=0, max=20, message="参数编码长度必须介于 0 和 20 之间")
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	@Length(min=0, max=20, message="参数名称长度必须介于 0 和 20 之间")
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
}