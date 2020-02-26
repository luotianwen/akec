/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品信息Entity
 * @author 产品信息
 * @version 2020-02-27
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 产品编码
	private String materialCode;		// 物料编号
	private String materialDesc;		// 物料描述
	private String barCode;		// 条形码
	private String materialSpeDesc;		// 物料规格说明
	private String baseTypeId;		// 系统参数表分类类型
	private String typeName;		// 产品大类名称
	private String baseSeriesId;		// 参照系统参数表
	private String seriesName;		// 产品系列
	private String batchCode;		// 产品批号
	private String standerSaleprice;		// 标准售价
	private String integral;		// 积分
	private String isRecordUnit;		// 是否记台
	private String isVerifyIndividualcode;		// 是否校验个体码
	private String note;		// 备注
	private String status;		// 状态
	
	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	@Length(min=0, max=50, message="产品编码长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=50, message="物料编号长度必须介于 0 和 50 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Length(min=0, max=100, message="物料描述长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=100, message="物料规格说明长度必须介于 0 和 100 之间")
	public String getMaterialSpeDesc() {
		return materialSpeDesc;
	}

	public void setMaterialSpeDesc(String materialSpeDesc) {
		this.materialSpeDesc = materialSpeDesc;
	}
	
	@Length(min=0, max=32, message="系统参数表分类类型长度必须介于 0 和 32 之间")
	public String getBaseTypeId() {
		return baseTypeId;
	}

	public void setBaseTypeId(String baseTypeId) {
		this.baseTypeId = baseTypeId;
	}
	
	@Length(min=0, max=20, message="产品大类名称长度必须介于 0 和 20 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Length(min=0, max=32, message="参照系统参数表长度必须介于 0 和 32 之间")
	public String getBaseSeriesId() {
		return baseSeriesId;
	}

	public void setBaseSeriesId(String baseSeriesId) {
		this.baseSeriesId = baseSeriesId;
	}
	
	@Length(min=0, max=30, message="产品系列长度必须介于 0 和 30 之间")
	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
	@Length(min=0, max=30, message="产品批号长度必须介于 0 和 30 之间")
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	
	public String getStanderSaleprice() {
		return standerSaleprice;
	}

	public void setStanderSaleprice(String standerSaleprice) {
		this.standerSaleprice = standerSaleprice;
	}
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	@Length(min=0, max=1, message="是否记台长度必须介于 0 和 1 之间")
	public String getIsRecordUnit() {
		return isRecordUnit;
	}

	public void setIsRecordUnit(String isRecordUnit) {
		this.isRecordUnit = isRecordUnit;
	}
	
	@Length(min=0, max=1, message="是否校验个体码长度必须介于 0 和 1 之间")
	public String getIsVerifyIndividualcode() {
		return isVerifyIndividualcode;
	}

	public void setIsVerifyIndividualcode(String isVerifyIndividualcode) {
		this.isVerifyIndividualcode = isVerifyIndividualcode;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}