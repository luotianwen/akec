/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评分类型Entity
 * @author 评分类型
 * @version 2020-03-14
 */
public class Grade extends DataEntity<Grade> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 评分名称
	private String level;		// 等级
	private String status;		// 状态
	private String seqno;		// 排序号
	
	public Grade() {
		super();
	}

	public Grade(String id){
		super(id);
	}

	@Length(min=0, max=30, message="评分名称长度必须介于 0 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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