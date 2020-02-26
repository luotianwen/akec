/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 知识分类Entity
 * @author 知识分类
 * @version 2020-02-27
 */
public class KnowlegerKind extends DataEntity<KnowlegerKind> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 知识分类
	private String seqno;		// 序号
	private String status;		// 状态
	private List<Studycenter> studycenterList = Lists.newArrayList();		// 子表列表
	
	public KnowlegerKind() {
		super();
	}

	public KnowlegerKind(String id){
		super(id);
	}

	@Length(min=0, max=20, message="知识分类长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Studycenter> getStudycenterList() {
		return studycenterList;
	}

	public void setStudycenterList(List<Studycenter> studycenterList) {
		this.studycenterList = studycenterList;
	}
}