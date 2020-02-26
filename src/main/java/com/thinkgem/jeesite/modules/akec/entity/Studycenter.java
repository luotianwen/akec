/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 知识分类Entity
 * @author 知识分类
 * @version 2020-02-27
 */
public class Studycenter extends DataEntity<Studycenter> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String href;		// 链接地址
	private String seqno;		// 序号
	private String status;		// 状态
	private Date releaseDate;		// 发布日期
	private KnowlegerKind kk;		// kkid 父类
	private Date beginReleaseDate;		// 开始 发布日期
	private Date endReleaseDate;		// 结束 发布日期
	
	public Studycenter() {
		super();
	}

	public Studycenter(String id){
		super(id);
	}

	public Studycenter(KnowlegerKind kk){
		this.kk = kk;
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1000, message="链接地址长度必须介于 0 和 1000 之间")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Length(min=0, max=32, message="kkid长度必须介于 0 和 32 之间")
	public KnowlegerKind getKk() {
		return kk;
	}

	public void setKk(KnowlegerKind kk) {
		this.kk = kk;
	}
	
	public Date getBeginReleaseDate() {
		return beginReleaseDate;
	}

	public void setBeginReleaseDate(Date beginReleaseDate) {
		this.beginReleaseDate = beginReleaseDate;
	}
	
	public Date getEndReleaseDate() {
		return endReleaseDate;
	}

	public void setEndReleaseDate(Date endReleaseDate) {
		this.endReleaseDate = endReleaseDate;
	}
		
}