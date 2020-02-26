/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 已售产品信息Entity
 * @author 已售产品信息
 * @version 2020-02-27
 */
public class Sellproduct extends DataEntity<Sellproduct> {
	
	private static final long serialVersionUID = 1L;
	private String materialDesc;		// 产品名称
	private String barCode;		// 条形码
	private String materialSpecificationDesc;		// 产品规格
	private String typeCode;		// 产品类别
	private String series;		// 产品系列
	private String batchCode;		// 产品批号
	private String dealerCode;		// 经销商编码
	private String dealerName;		// 经销商名称
	private String businessDealerCode;		// 业务统计伙计编码
	private String businessDealerName;		// 业务统计伙计名称
	private String individualcode;		// 产品个体码
	private String proudctCode;		// 产品编码
	private String materialCode;		// 材料编码
	private String comments;		// 备注
	private String saleType;		// 销售类型
	
	public Sellproduct() {
		super();
	}

	public Sellproduct(String id){
		super(id);
	}

	@Length(min=0, max=300, message="产品名称长度必须介于 0 和 300 之间")
	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	
	@Length(min=0, max=50, message="条形码长度必须介于 0 和 50 之间")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@Length(min=0, max=50, message="产品规格长度必须介于 0 和 50 之间")
	public String getMaterialSpecificationDesc() {
		return materialSpecificationDesc;
	}

	public void setMaterialSpecificationDesc(String materialSpecificationDesc) {
		this.materialSpecificationDesc = materialSpecificationDesc;
	}
	
	@Length(min=0, max=50, message="产品类别长度必须介于 0 和 50 之间")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@Length(min=0, max=56, message="产品系列长度必须介于 0 和 56 之间")
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
	
	@Length(min=0, max=56, message="产品批号长度必须介于 0 和 56 之间")
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	
	@Length(min=0, max=50, message="经销商编码长度必须介于 0 和 50 之间")
	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	
	@Length(min=0, max=200, message="经销商名称长度必须介于 0 和 200 之间")
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	@Length(min=0, max=50, message="业务统计伙计编码长度必须介于 0 和 50 之间")
	public String getBusinessDealerCode() {
		return businessDealerCode;
	}

	public void setBusinessDealerCode(String businessDealerCode) {
		this.businessDealerCode = businessDealerCode;
	}
	
	@Length(min=0, max=300, message="业务统计伙计名称长度必须介于 0 和 300 之间")
	public String getBusinessDealerName() {
		return businessDealerName;
	}

	public void setBusinessDealerName(String businessDealerName) {
		this.businessDealerName = businessDealerName;
	}
	
	@Length(min=0, max=56, message="产品个体码长度必须介于 0 和 56 之间")
	public String getIndividualcode() {
		return individualcode;
	}

	public void setIndividualcode(String individualcode) {
		this.individualcode = individualcode;
	}
	
	@Length(min=0, max=56, message="产品编码长度必须介于 0 和 56 之间")
	public String getProudctCode() {
		return proudctCode;
	}

	public void setProudctCode(String proudctCode) {
		this.proudctCode = proudctCode;
	}
	
	@Length(min=0, max=56, message="材料编码长度必须介于 0 和 56 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Length(min=0, max=800, message="备注长度必须介于 0 和 800 之间")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Length(min=0, max=50, message="销售类型长度必须介于 0 和 50 之间")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
}