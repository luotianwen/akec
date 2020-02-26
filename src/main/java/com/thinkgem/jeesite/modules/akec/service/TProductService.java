/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.akec.entity.TProduct;
import com.thinkgem.jeesite.modules.akec.dao.TProductDao;

/**
 * 产品信息Service
 * @author 产品信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class TProductService extends CrudService<TProductDao, TProduct> {

	public TProduct get(String id) {
		return super.get(id);
	}
	
	public List<TProduct> findList(TProduct tProduct) {
		return super.findList(tProduct);
	}
	
	public Page<TProduct> findPage(Page<TProduct> page, TProduct tProduct) {
		return super.findPage(page, tProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(TProduct tProduct) {
		super.save(tProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(TProduct tProduct) {
		super.delete(tProduct);
	}
	
}