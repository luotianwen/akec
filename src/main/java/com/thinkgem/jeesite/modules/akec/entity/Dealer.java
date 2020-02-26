/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 经销商信息Entity
 * @author 经销商信息
 * @version 2020-02-27
 */
public class Dealer extends DataEntity<Dealer> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 经销商编码
	private String isRecordIntegral;		// 是否计分
	private String originalCode;		// 原有编码
	private String businessRegion;		// 业务区域
	private String businessProvince;		// 业务省区
	private String market;		// 市场
	private String financeRegion;		// 财务区域
	private String financeProvince;		// 财务省区
	private String name;		// 经销商名称
	private String qualityValidity;		// 资质有效期
	private String status;		// 状态
	private String businessStaticsCode;		// 业务统计客户编码
	private String businessStaticsName;		// 业务统计客户名称
	
	public Dealer() {
		super();
	}

	public Dealer(String id){
		super(id);
	}

	@Length(min=0, max=100, message="经销商编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=1, message="是否计分长度必须介于 0 和 1 之间")
	public String getIsRecordIntegral() {
		return isRecordIntegral;
	}

	public void setIsRecordIntegral(String isRecordIntegral) {
		this.isRecordIntegral = isRecordIntegral;
	}
	
	@Length(min=0, max=100, message="原有编码长度必须介于 0 和 100 之间")
	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}
	
	@Length(min=0, max=100, message="业务区域长度必须介于 0 和 100 之间")
	public String getBusinessRegion() {
		return businessRegion;
	}

	public void setBusinessRegion(String businessRegion) {
		this.businessRegion = businessRegion;
	}
	
	@Length(min=0, max=100, message="业务省区长度必须介于 0 和 100 之间")
	public String getBusinessProvince() {
		return businessProvince;
	}

	public void setBusinessProvince(String businessProvince) {
		this.businessProvince = businessProvince;
	}
	
	@Length(min=0, max=100, message="市场长度必须介于 0 和 100 之间")
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
	
	@Length(min=0, max=100, message="财务区域长度必须介于 0 和 100 之间")
	public String getFinanceRegion() {
		return financeRegion;
	}

	public void setFinanceRegion(String financeRegion) {
		this.financeRegion = financeRegion;
	}
	
	@Length(min=0, max=100, message="财务省区长度必须介于 0 和 100 之间")
	public String getFinanceProvince() {
		return financeProvince;
	}

	public void setFinanceProvince(String financeProvince) {
		this.financeProvince = financeProvince;
	}
	
	@Length(min=0, max=100, message="经销商名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="资质有效期长度必须介于 0 和 10 之间")
	public String getQualityValidity() {
		return qualityValidity;
	}

	public void setQualityValidity(String qualityValidity) {
		this.qualityValidity = qualityValidity;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="业务统计客户编码长度必须介于 0 和 100 之间")
	public String getBusinessStaticsCode() {
		return businessStaticsCode;
	}

	public void setBusinessStaticsCode(String businessStaticsCode) {
		this.businessStaticsCode = businessStaticsCode;
	}
	
	@Length(min=0, max=100, message="业务统计客户名称长度必须介于 0 和 100 之间")
	public String getBusinessStaticsName() {
		return businessStaticsName;
	}

	public void setBusinessStaticsName(String businessStaticsName) {
		this.businessStaticsName = businessStaticsName;
	}
	
}