/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.Sellproduct;
import com.thinkgem.jeesite.modules.akec.dao.SellproductDao;

/**
 * 已售产品信息Service
 * @author 已售产品信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class SellproductService extends CrudService<SellproductDao, Sellproduct> {

	public Sellproduct get(String id) {
		return super.get(id);
	}
	
	public List<Sellproduct> findList(Sellproduct sellproduct) {
		return super.findList(sellproduct);
	}
	
	public Page<Sellproduct> findPage(Page<Sellproduct> page, Sellproduct sellproduct) {
		return super.findPage(page, sellproduct);
	}
	
	@Transactional(readOnly = false)
	public void save(Sellproduct sellproduct) {
		super.save(sellproduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(Sellproduct sellproduct) {
		super.delete(sellproduct);
	}

	public List<Sellproduct> getByIndividualcode(Sellproduct sellproduct) {
		return dao.getByIndividualcode(sellproduct);
	}
}