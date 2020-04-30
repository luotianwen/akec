/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 通知中心Entity
 * @author 通知中心
 * @version 2020-03-10
 */
public class TNotice extends DataEntity<TNotice> {
	
	private static final long serialVersionUID = 1L;
	private String seqno;		// 序号
	private String title;		// 标题
	private String content;		// 内容
	private String status;		// 状态
	private String range;		// 1:业务员角色，2:人员，3:全部
	private String rangeName;		// 名称连接以逗号分隔
	private String rangeValue;		// ID连接以逗号分隔
	private Date releaseDate;		// 生成日期
	
	public TNotice() {
		super();
	}

	public TNotice(String id){
		super(id);
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	@Length(min=0, max=100, message="标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="1:业务员角色，2:人员，3:全部长度必须介于 0 和 1 之间")
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}
	
	public String getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}