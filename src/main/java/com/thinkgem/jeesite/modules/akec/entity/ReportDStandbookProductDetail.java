/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 待提交报台Entity
 * @author 待提交报台
 * @version 2020-03-26
 */
public class ReportDStandbookProductDetail extends DataEntity<ReportDStandbookProductDetail> {
	
	private static final long serialVersionUID = 1L;
	private ReportDStandbook reportId;		// 外键参照报台主表 父类
	private String productId;		// 产品ID（参照产品表主键）
	private String individualcode;		// 产品个体码
	private String integral;		// 积分(根据经销商是否记分)
	private String note;		// 备注
	private String isRecordUnit;		// 1：是，0：否
	private String isVerifyIndividualcode;		// 1：是，0：否
	private String scanCode;		// scan_code
	private String produceDate;		// produce_date
	private String outdate;		// outdate

	private Product product;		// 产品ID（参照产品表主键）

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ReportDStandbookProductDetail() {
		super();
	}

	public ReportDStandbookProductDetail(String id){
		super(id);
	}

	public ReportDStandbookProductDetail(ReportDStandbook reportId){
		this.reportId = reportId;
	}

	@Length(min=0, max=32, message="外键参照报台主表长度必须介于 0 和 32 之间")
	public ReportDStandbook getReportId() {
		return reportId;
	}

	public void setReportId(ReportDStandbook reportId) {
		this.reportId = reportId;
	}
	
	@Length(min=0, max=32, message="产品ID（参照产品表主键）长度必须介于 0 和 32 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=100, message="产品个体码长度必须介于 0 和 100 之间")
	public String getIndividualcode() {
		return individualcode;
	}

	public void setIndividualcode(String individualcode) {
		this.individualcode = individualcode;
	}
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=1, message="1：是，0：否长度必须介于 0 和 1 之间")
	public String getIsRecordUnit() {
		return isRecordUnit;
	}

	public void setIsRecordUnit(String isRecordUnit) {
		this.isRecordUnit = isRecordUnit;
	}
	
	@Length(min=0, max=1, message="1：是，0：否长度必须介于 0 和 1 之间")
	public String getIsVerifyIndividualcode() {
		return isVerifyIndividualcode;
	}

	public void setIsVerifyIndividualcode(String isVerifyIndividualcode) {
		this.isVerifyIndividualcode = isVerifyIndividualcode;
	}
	
	@Length(min=0, max=100, message="scan_code长度必须介于 0 和 100 之间")
	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}
	
	@Length(min=0, max=6, message="produce_date长度必须介于 0 和 6 之间")
	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	
	@Length(min=0, max=6, message="outdate长度必须介于 0 和 6 之间")
	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	
}